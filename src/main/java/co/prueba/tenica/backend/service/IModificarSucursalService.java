package co.prueba.tenica.backend.service;

import co.prueba.tenica.backend.dto.in.InModificarNombreDto;
import co.prueba.tenica.backend.dto.resp.RespuestaGeneralDto;
import reactor.core.publisher.Mono;

/**
 * Servicio para modificar datos de sucursales en el sistema.
 * Permite actualizar el nombre de una sucursal existente.
 */
public interface IModificarSucursalService {

    /**
     * Actualiza el nombre de una sucursal.
     *
     * @param inModificarNombreDto DTO que contiene el identificador de la sucursal
     *                             y el nuevo nombre a asignar.
     * @return Mono que envuelve un RespuestaGeneralDto sin contenido de datos,
     *         indicando si la operación fue exitosa o si ocurrió un error.
     */
    Mono<RespuestaGeneralDto<Void>> modificarNombreSucursal(InModificarNombreDto inModificarNombreDto);

}
