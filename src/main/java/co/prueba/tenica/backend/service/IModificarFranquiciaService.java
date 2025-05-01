package co.prueba.tenica.backend.service;

import co.prueba.tenica.backend.dto.InModificarNombreDto;
import co.prueba.tenica.backend.dto.RespuestaGeneralDto;
import reactor.core.publisher.Mono;

public interface IModificarFranquiciaService {


    Mono<RespuestaGeneralDto<Void>> modificarFranquiciaReact(InModificarNombreDto inModificarNombreDto);

}
