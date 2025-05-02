package co.prueba.tenica.backend.service.impl;

import co.prueba.tenica.backend.dto.AdjuntarSucursalesDto;
import co.prueba.tenica.backend.dto.ProductoDto;
import co.prueba.tenica.backend.dto.ProductoSucursalDto;
import co.prueba.tenica.backend.dto.in.InCrearProductoDto;
import co.prueba.tenica.backend.dto.resp.RespuestaGeneralDto;
import co.prueba.tenica.backend.repository.IProductoRepository;
import co.prueba.tenica.backend.repository.IProductoSucursalRepository;
import co.prueba.tenica.backend.service.ICrearProductoService;
import co.prueba.tenica.backend.utils.mapper.ProductoMapper;
import co.prueba.tenica.backend.utils.mapper.ProductoSucursalMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static co.prueba.tenica.backend.utils.Constantes.*;

/**
 * Servicio que implementa la lógica de creación de productos.
 * Recibe un DTO con los datos del producto, lo persiste,
 * y opcionalmente adjunta stock en múltiples sucursales.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class CrearProductoService implements ICrearProductoService {

    private final IProductoRepository productoRepository;
    private final IProductoSucursalRepository productoSucursalRepository;
    private final ProductoMapper productoMapper;
    private final ProductoSucursalMapper productoSucursalMapper;

    /**
     * Crea un nuevo producto con su stock en sucursales.
     *
     * @param inCrearProductoDto DTO que contiene el nombre del producto
     *                           y una lista de sucursales con su stock.
     * @return Mono que envuelve un RespuestaGeneralDto<Void>
     *         indicando el éxito o fallo de la operación.
     */
    @Override
    public Mono<RespuestaGeneralDto<Void>> crearProducto(InCrearProductoDto inCrearProductoDto) {
        ProductoDto pDto = ProductoDto.builder()
                .nombre(inCrearProductoDto.getNombre())
                .build();

        return productoRepository.save(productoMapper.toModel(pDto))
                .flatMap(savedProd -> {
                    List<AdjuntarSucursalesDto> sucursales = inCrearProductoDto.getSucursalesAdjuntar();
                    if (sucursales == null || sucursales.isEmpty()) {
                        return Mono.just(savedProd);
                    }
                    return Flux.fromIterable(sucursales)
                            .flatMap(adj -> {
                                ProductoSucursalDto psDto = ProductoSucursalDto.builder()
                                        .productoId(savedProd.getCodigo())
                                        .sucursalId(adj.getIdSucursal())
                                        .stock(adj.getStock())
                                        .build();
                                return productoSucursalRepository.save(
                                        productoSucursalMapper.toModel(psDto)
                                );
                            })
                            .then(Mono.just(savedProd));
                })
                .map(p -> {
                    RespuestaGeneralDto<Void> resp = new RespuestaGeneralDto<>();
                    resp.setRespuesta(MSG_PRODUCTO_CREADO_EXITOSO);
                    return resp;
                })
                .doOnError(ex -> log.error(LOG_ERROR_CREADO_PRODUCTO, ex))
                .onErrorResume(ex -> {
                    RespuestaGeneralDto<Void> resp = new RespuestaGeneralDto<>();
                    resp.setError(true);
                    resp.setRespuesta(MSG_PRODUCTO_CREACION_ERROR);
                    return Mono.just(resp);
                });
    }
}

