package co.prueba.tenica.backend.service.impl;

import co.prueba.tenica.backend.dto.AdjuntarSucursalesDto;
import co.prueba.tenica.backend.dto.ProductoSucursalDto;
import co.prueba.tenica.backend.dto.in.InAgregarProductoSucursalDto;
import co.prueba.tenica.backend.dto.resp.RespuestaGeneralDto;
import co.prueba.tenica.backend.entity.ProductoModel;
import co.prueba.tenica.backend.entity.ProductoSucursalModel;
import co.prueba.tenica.backend.repository.IProductoRepository;
import co.prueba.tenica.backend.repository.IProductoSucursalRepository;
import co.prueba.tenica.backend.service.IAgregarProductoSucursalService;
import co.prueba.tenica.backend.utils.mapper.ProductoSucursalMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

import static co.prueba.tenica.backend.utils.Constantes.*;

/**
 * Servicio reactivo que asocia productos a sucursales con un stock determinado.
 * <p>
 * - Verifica que el producto exista; si no, devuelve un error.<br>
 * - Verifica que no exista ya la combinación producto–sucursal; si existe, devuelve un error.<br>
 * - En caso contrario, guarda cada asignación de manera secuencial.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AgregarProductoSucursalService implements IAgregarProductoSucursalService {

    private final IProductoSucursalRepository productoSucursalRepo;
    private final ProductoSucursalMapper mapper;
    private final IProductoRepository productoRepo;

    /**
     * Asocia un producto a una o varias sucursales con el stock indicado.
     *
     * @param dto DTO que contiene:
     *            <ul>
     *              <li>{@code codigoProducto}: código del producto a asignar.</li>
     *              <li>{@code sucursalesAdjuntar}: lista de {@link AdjuntarSucursalesDto}
     *              con {@code idSucursal} y {@code stock} a asignar.</li>
     *            </ul>
     * @return un {@link Mono} que emite un {@link RespuestaGeneralDto}{@code <Void>}:
     *         <ul>
     *           <li>{@link RespuestaGeneralDto} = true y mensaje de error si:
     *             <ul>
     *               <li>el producto no existe</li>
     *               <li>alguna combinación producto–sucursal ya existe</li>
     *             </ul>
     *           </li>
     *           <li>{@link RespuestaGeneralDto} = false y mensaje de éxito
     *                si todas las asignaciones fueron exitosas.</li>
     *         </ul>
     */
    @Override
    public Mono<RespuestaGeneralDto<Void>> agregarProductoSucursal(InAgregarProductoSucursalDto dto) {
        RespuestaGeneralDto<Void> notFound = new RespuestaGeneralDto<>();
        notFound.setError(true);
        notFound.setRespuesta(MSG_PRODUCTO_NO_ENCONTRADO);

        RespuestaGeneralDto<Void> alreadyExists = new RespuestaGeneralDto<>();
        alreadyExists.setError(true);

        return productoRepo.findById(dto.getCodigoProducto())
                .flatMap(producto ->
                        Flux.fromIterable(dto.getSucursalesAdjuntar())
                                .concatMap(adj ->
                                        productoSucursalRepo
                                                .existsBySucursalIdAndProductoId(adj.getIdSucursal(), producto.getCodigo())
                                                .flatMap(existe -> {
                                                    if (existe) {
                                                        // Formatea el mensaje con idSucursal y código de producto
                                                        String msg = String.format(
                                                                MSG_COMBINACION_EXISTE,
                                                                adj.getIdSucursal(),
                                                                producto.getCodigo()
                                                        );
                                                        return Mono.error(new IllegalArgumentException(msg));
                                                    }
                                                    ProductoSucursalModel m = mapper.toModel(
                                                            new ProductoSucursalDto(
                                                                    producto.getCodigo(),
                                                                    adj.getIdSucursal(),
                                                                    adj.getStock()
                                                            )
                                                    );
                                                    return productoSucursalRepo.save(m);
                                                })
                                )
                                .collectList()
                                .flatMap(list -> {
                                    RespuestaGeneralDto<Void> ok = new RespuestaGeneralDto<>();
                                    ok.setError(false);
                                    ok.setRespuesta(MSG_TODAS_SUCURSALES_OK);
                                    return Mono.just(ok);
                                })
                )
                .switchIfEmpty(Mono.just(notFound))
                .onErrorResume(IllegalArgumentException.class, e -> {
                    alreadyExists.setRespuesta(e.getMessage());
                    return Mono.just(alreadyExists);
                });
    }
}
