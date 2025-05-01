package co.prueba.tenica.backend.service;

import co.prueba.tenica.backend.dto.RespuestaGeneralDto;
import reactor.core.publisher.Mono;

public interface IEliminarProductoService {

    Mono<RespuestaGeneralDto<Void>> eliminarProductoSucursal(String codigoProducto, Long idSucursal);

}
