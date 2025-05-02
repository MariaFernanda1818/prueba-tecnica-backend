package co.prueba.tenica.backend.service;

import co.prueba.tenica.backend.dto.in.InCrearProductoDto;
import co.prueba.tenica.backend.dto.resp.RespuestaGeneralDto;
import reactor.core.publisher.Mono;

/**
 * Servicio para la creación de nuevos productos en el sistema.
 * Define operaciones que permiten registrar productos y asociarlos a sucursales.
 */
public interface ICrearProductoService {

    /**
     * Crea un nuevo producto con los datos proporcionados.
     *
     * @param inCrearProductoDto DTO que contiene el nombre del producto
     *                           y las sucursales con sus stocks a adjuntar.
     * @return Mono que envuelve un RespuestaGeneralDto sin contenido de datos,
     *         indicando si la operación fue exitosa o si ocurrió un error.
     */
    Mono<RespuestaGeneralDto<Void>> crearProducto(InCrearProductoDto inCrearProductoDto);

}
