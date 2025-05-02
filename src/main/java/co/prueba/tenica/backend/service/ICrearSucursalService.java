package co.prueba.tenica.backend.service;

import co.prueba.tenica.backend.dto.in.InCrearSucursalDto;
import co.prueba.tenica.backend.dto.resp.RespuestaGeneralDto;
import reactor.core.publisher.Mono;

/**
 * Servicio para la creación de nuevas sucursales en el sistema.
 * Define operaciones que permiten registrar sucursales asociadas a franquicias existentes.
 */
public interface ICrearSucursalService {

    /**
     * Crea una nueva sucursal con los datos proporcionados.
     *
     * @param inCrearSucursalDto DTO que contiene el nombre de la sucursal
     *                           y el identificador de la franquicia asociada.
     * @return Mono que envuelve un RespuestaGeneralDto sin contenido de datos,
     *         indicando si la operación fue exitosa o si ocurrió un error.
     */
    Mono<RespuestaGeneralDto<Void>> crearSucursal(InCrearSucursalDto inCrearSucursalDto);

}
