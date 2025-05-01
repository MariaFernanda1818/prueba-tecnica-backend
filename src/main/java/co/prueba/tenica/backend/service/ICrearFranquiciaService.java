package co.prueba.tenica.backend.service;

import co.prueba.tenica.backend.dto.FranquiciaDto;
import co.prueba.tenica.backend.dto.InCrearFranquiciaDto;
import co.prueba.tenica.backend.dto.RespuestaGeneralDto;
import reactor.core.publisher.Mono;

public interface ICrearFranquiciaService {

    Mono<RespuestaGeneralDto<Void>> crearFranquicia(InCrearFranquiciaDto franquiciaDto);

}
