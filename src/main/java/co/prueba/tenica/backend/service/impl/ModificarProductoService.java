package co.prueba.tenica.backend.service.impl;

import co.prueba.tenica.backend.dto.in.InModificarNombreProductoDto;
import co.prueba.tenica.backend.dto.in.InModificarProductoStockDto;
import co.prueba.tenica.backend.dto.resp.RespuestaGeneralDto;
import co.prueba.tenica.backend.repository.IProductoRepository;
import co.prueba.tenica.backend.repository.IProductoSucursalRepository;
import co.prueba.tenica.backend.service.IModificarProductoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static co.prueba.tenica.backend.utils.Constantes.*;


/**
 * Servicio para modificar datos de productos en el sistema.
 * Ofrece métodos reactivos para actualizar el nombre de un producto
 * y modificar el stock de un producto en una sucursal.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ModificarProductoService implements IModificarProductoService {

    private final IProductoRepository productoRepository;
    private final IProductoSucursalRepository productoSucursalRepository;

    /**
     * Actualiza el nombre de un producto.
     *
     * @param dto DTO que contiene el código del producto y el nuevo nombre.
     * @return Mono que envuelve un RespuestaGeneralDto<Void> con el resultado.
     */
    @Override
    public Mono<RespuestaGeneralDto<Void>> modificarNombreProducto(InModificarNombreProductoDto dto) {
        return productoRepository.actualizarNombreProducto(dto.getCodigo(), dto.getNombre())
                .then(Mono.fromSupplier(() -> {
                    RespuestaGeneralDto<Void> resp = new RespuestaGeneralDto<>();
                    resp.setRespuesta(MSG_PRODUCTO_NOMBRE_MODIFICADO_EXITOSO);
                    return resp;
                }))
                .doOnError(ex -> log.error(LOG_ERROR_MODIFICAR_NOMBRE_PRODUCTO, ex.getMessage(), ex))
                .onErrorResume(ex -> Mono.just(new RespuestaGeneralDto<Void>() {{
                    setError(true);
                    setRespuesta(MSG_PRODUCTO_NOMBRE_MODIFICACION_ERROR);
                }}));
    }

    /**
     * Modifica el stock de un producto en una sucursal específica.
     *
     * @param dto DTO que contiene el código del producto, id de la sucursal y nuevo stock.
     * @return Mono que envuelve un RespuestaGeneralDto<Void> con el resultado.
     */
    @Override
    public Mono<RespuestaGeneralDto<Void>> modificarStockProducto(InModificarProductoStockDto dto) {
        return productoSucursalRepository.actualizarStockProductoSucursal(
                        dto.getCodigoProducto(), dto.getIdSucursal(), dto.getNuevoStock()
                )
                .then(Mono.fromSupplier(() -> {
                    RespuestaGeneralDto<Void> resp = new RespuestaGeneralDto<>();
                    resp.setRespuesta(MSG_PRODUCTO_STOCK_MODIFICADO_EXITOSO);
                    return resp;
                }))
                .doOnError(ex -> log.error(LOG_ERROR_MODIFICAR_STOCK_PRODUCTO, ex.getMessage(), ex))
                .onErrorResume(ex -> Mono.just(new RespuestaGeneralDto<Void>() {{
                    setError(true);
                    setRespuesta(MSG_PRODUCTO_STOCK_MODIFICACION_ERROR);
                }}));
    }
}

