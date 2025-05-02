package co.prueba.tenica.backend.dto.resp;

import lombok.Data;

/**
 * DTO de respuesta que contiene información del producto con mayor stock
 * en una sucursal determinada.
 */
@Data
public class RespProductoMaxStockDto {

    /**
     * Nombre de la sucursal donde se calcula el stock máximo.
     */
    private String nombreSucursal;

    /**
     * Nombre del producto que tiene el stock máximo en la sucursal.
     */
    private String nombreProducto;

    /**
     * Cantidad de stock del producto con stock máximo.
     */
    private Long stock;

}
