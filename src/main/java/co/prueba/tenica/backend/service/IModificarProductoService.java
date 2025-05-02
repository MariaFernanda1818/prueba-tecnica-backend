package co.prueba.tenica.backend.service;

import co.prueba.tenica.backend.dto.in.InModificarNombreProductoDto;
import co.prueba.tenica.backend.dto.in.InModificarProductoStockDto;
import co.prueba.tenica.backend.dto.resp.RespuestaGeneralDto;
import reactor.core.publisher.Mono;

/**
 * Servicio para modificar datos de productos en el sistema.
 * Proporciona operaciones reactivas para actualizar el nombre
 * y el stock de los productos.
 */
public interface IModificarProductoService {

    /**
     * Actualiza el nombre de un producto existente.
     *
     * @param inModificarNombreDto DTO que contiene el código del producto
     *                             y el nuevo nombre a asignar.
     * @return Mono que envuelve un RespuestaGeneralDto sin contenido de datos,
     *         indicando si la operación fue exitosa o si ocurrió un error.
     */
    Mono<RespuestaGeneralDto<Void>> modificarNombreProducto(
            InModificarNombreProductoDto inModificarNombreDto);

    /**
     * Actualiza el stock de un producto en una sucursal específica.
     *
     * @param inModificarProductoStockDto DTO que contiene el código del producto,
     *                                     el identificador de la sucursal y el nuevo stock.
     * @return Mono que envuelve un RespuestaGeneralDto sin contenido de datos,
     *         indicando si la operación fue exitosa o si ocurrió un error.
     */
    Mono<RespuestaGeneralDto<Void>> modificarStockProducto(
            InModificarProductoStockDto inModificarProductoStockDto);

}