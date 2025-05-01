package co.prueba.tenica.backend.service;

import co.prueba.tenica.backend.dto.InModificarNombreDto;
import co.prueba.tenica.backend.dto.InModificarProductoDto;
import co.prueba.tenica.backend.dto.RespuestaGeneralDto;
import reactor.core.publisher.Mono;

public interface IModificarProductoService {

    Mono<RespuestaGeneralDto<Void>> modificarNombreProducto(InModificarNombreDto inModificarNombreDto);


}
