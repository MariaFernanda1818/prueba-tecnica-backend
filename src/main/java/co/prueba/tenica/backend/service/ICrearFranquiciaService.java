package co.prueba.tenica.backend.service;

import co.prueba.tenica.backend.dto.in.InCrearFranquiciaDto;
import co.prueba.tenica.backend.dto.resp.RespuestaGeneralDto;
import reactor.core.publisher.Mono;

/**
 * Servicio para la creación de nuevas franquicias en el sistema.
 * Define operaciones que permiten registrar y gestionar franquicias.
 */
public interface ICrearFranquiciaService {

    /**
     * Crea una nueva franquicia con los datos proporcionados.
     *
     * @param franquiciaDto DTO que contiene el nombre de la franquicia a crear.
     * @return Mono que envuelve un RespuestaGeneralDto sin contenido de datos,
     *         indicando si la operación fue exitosa o se produjo un error.
     */
    Mono<RespuestaGeneralDto<Void>> crearFranquicia(InCrearFranquiciaDto franquiciaDto);

}
