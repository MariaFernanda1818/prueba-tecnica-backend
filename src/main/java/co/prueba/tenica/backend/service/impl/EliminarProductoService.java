package co.prueba.tenica.backend.service.impl;

import co.prueba.tenica.backend.dto.resp.RespuestaGeneralDto;
import co.prueba.tenica.backend.repository.IProductoRepository;
import co.prueba.tenica.backend.repository.IProductoSucursalRepository;
import co.prueba.tenica.backend.service.IEliminarProductoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static co.prueba.tenica.backend.utils.Constantes.*;

/**
 * Servicio que implementa la lógica para eliminar productos,
 * ya sea de forma global o específica en una sucursal.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class EliminarProductoService implements IEliminarProductoService {

    private final IProductoSucursalRepository productoSucursalRepository;
    private final IProductoRepository productoRepository;

    /**
     * Elimina un producto: globalmente si idSucursal es null,
     * o sólo de la sucursal indicada.
     *
     * @param codigo      Código único del producto a eliminar.
     * @param idSucursal  Identificador de la sucursal; si es null,
     *                    se elimina de todas las sucursales.
     * @return Mono que envuelve un RespuestaGeneralDto<Void>
     *         indicando el éxito o el fallo de la operación.
     */
    @Override
    public Mono<RespuestaGeneralDto<Void>> eliminarProductoSucursal(String codigo, Long idSucursal) {
        boolean esGlobal = idSucursal == null;
        RespuestaGeneralDto<Void> response = new RespuestaGeneralDto<>();

        Mono<Void> op = esGlobal
                ? productoRepository.eliminarProducto(codigo)
                : productoSucursalRepository.eliminarProductoSucursal(codigo, idSucursal);

        return op
                .then(Mono.fromSupplier(() -> {
                    String mensaje = esGlobal
                            ? String.format(MSG_PRODUCTO_ELIMINADO_GLOBAL, codigo)
                            : String.format(MSG_PRODUCTO_ELIMINADO_SUCURSAL, codigo, idSucursal);
                    response.setRespuesta(mensaje);
                    return response;
                }))
                .doOnError(ex -> log.error(LOG_ERROR_ELIMINAR_PRODUCTO, codigo, idSucursal, ex))
                .onErrorResume(ex -> {
                    String errorMsg = esGlobal
                            ? String.format(MSG_PRODUCTO_ELIMINACION_ERROR_GLOBAL, codigo)
                            : String.format(MSG_PRODUCTO_ELIMINACION_ERROR_SUCURSAL, codigo, idSucursal);
                    response.setError(true);
                    response.setRespuesta(errorMsg);
                    return Mono.just(response);
                });
    }
}
