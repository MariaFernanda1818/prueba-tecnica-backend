package co.prueba.tenica.backend.service;

import co.prueba.tenica.backend.dto.InCrearProductoDto;
import co.prueba.tenica.backend.dto.RespuestaGeneralDto;
import reactor.core.publisher.Mono;

public interface ICrearProductoService {

    Mono<RespuestaGeneralDto<Void>> crearProducto(InCrearProductoDto inCrearProductoDto);

}
