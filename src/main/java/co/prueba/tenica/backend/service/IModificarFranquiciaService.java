package co.prueba.tenica.backend.service;

import co.prueba.tenica.backend.dto.in.InModificarNombreDto;
import co.prueba.tenica.backend.dto.resp.RespuestaGeneralDto;
import reactor.core.publisher.Mono;

/**
 * Servicio para la eliminación de productos en el sistema.
 * Permite eliminar un producto globalmente o de forma específica
 * en una sucursal determinada.
 */
public interface IModificarFranquiciaService {

    /**
     * Modifica los datos de una franquicia existente.
     *
     * @param inModificarNombreDto DTO que contiene el identificador de la franquicia
     *                             y el nuevo nombre a asignar.
     * @return Mono que envuelve un RespuestaGeneralDto sin contenido de datos,
     *         indicando si la operación fue exitosa o si ocurrió un error.
     */
    Mono<RespuestaGeneralDto<Void>> modificarFranquicia(InModificarNombreDto inModificarNombreDto);

}
