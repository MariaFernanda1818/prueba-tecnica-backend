package co.prueba.tenica.backend.service;

import co.prueba.tenica.backend.dto.resp.RespuestaGeneralDto;
import reactor.core.publisher.Mono;

/**
 * Servicio para la eliminación de productos en el sistema.
 * Permite eliminar un producto globalmente o de forma específica
 * en una sucursal determinada.
 */
public interface IEliminarProductoService {

    /**
     * Elimina un producto, bien de todas las sucursales (si idSucursal es null),
     * o sólo de la sucursal indicada.
     *
     * @param codigoProducto Código único del producto a eliminar.
     * @param idSucursal     Identificador de la sucursal; si es null,
     *                       se elimina el producto globalmente.
     * @return Mono que envuelve un RespuestaGeneralDto sin contenido de datos,
     *         indicando si la operación fue exitosa o si ocurrió un error.
     */
    Mono<RespuestaGeneralDto<Void>> eliminarProductoSucursal(String codigoProducto, Long idSucursal);

}