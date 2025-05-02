package co.prueba.tenica.backend.service;

import co.prueba.tenica.backend.dto.resp.RespProductoMaxStockDto;
import co.prueba.tenica.backend.dto.resp.RespuestaGeneralDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Servicio para consultar el producto con mayor stock en cada sucursal.
 * Proporciona un método que devuelve, por cada sucursal,
 * el producto que tenga la mayor cantidad de stock disponible.
 */
public interface IConsultarMaxStockProductoService {

    /**
     * Obtiene para cada sucursal el producto con stock máximo.
     *
     * @return Mono que envuelve un RespuestaGeneralDto con un Flux
     *         de RespProductoMaxStockDto, donde cada elemento representa
     *         el producto de mayor stock en una sucursal.
     */
    Mono<RespuestaGeneralDto<Flux<RespProductoMaxStockDto>>> productosMaxStockSucursal();

}
