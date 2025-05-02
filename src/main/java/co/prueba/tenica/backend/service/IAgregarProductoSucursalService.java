package co.prueba.tenica.backend.service;

import co.prueba.tenica.backend.dto.in.InAgregarProductoSucursalDto;
import co.prueba.tenica.backend.dto.resp.RespuestaGeneralDto;
import reactor.core.publisher.Mono;

/**
 * Servicio reactivo para gestionar la asociación de productos a sucursales.
 * <p>
 * Define la operación de agregar uno o varios productos a distintas sucursales,
 * con sus respectivos stocks, asegurando validaciones de existencia de producto
 * y de duplicidad de la relación producto–sucursal.
 */
public interface IAgregarProductoSucursalService {

    /**
     * Asocia un producto a una o varias sucursales.
     * <p>
     * Valida que el producto exista; si no existe, emite un error.
     * Valida que no exista previamente la combinación producto–sucursal; si existe,
     * emite un error. En caso contrario, guarda las asociaciones con los stocks indicados.
     *
     * @param inAgregarProductoSucursalDto DTO que contiene:
     *                                     <ul>
     *                                       <li>{@code codigoProducto}: código único del producto.</li>
     *                                       <li>{@code sucursalesAdjuntar}: lista de objetos
     *                                       con {@code idSucursal} y {@code stock} a asignar.</li>
     *                                     </ul>
     * @return un {@link Mono} que emite un {@link RespuestaGeneralDto}{@code <Void>} con:
     *         <ul>
     *           <li>{@link RespuestaGeneralDto} = true y mensaje de error si el producto
     *               no existe o alguna combinación ya está registrada.</li>
     *           <li>{@link RespuestaGeneralDto} = false y mensaje de éxito si todas las
     *               asociaciones se guardaron correctamente.</li>
     *         </ul>
     */
    Mono<RespuestaGeneralDto<Void>> agregarProductoSucursal(InAgregarProductoSucursalDto inAgregarProductoSucursalDto);

}
