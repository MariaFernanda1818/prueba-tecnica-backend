package co.prueba.tenica.backend.dto;

import lombok.Data;

/**
 * DTO utilizado para adjuntar un producto a una sucursal
 * con la cantidad de stock correspondiente.
 */
@Data
public class AdjuntarSucursalesDto {

    /**
     * Identificador de la sucursal donde se adjunta el producto.
     */
    private Long idSucursal;

    /**
     * Cantidad de stock del producto que se quiere asignar a la sucursal.
     * Debe ser un valor mayor o igual a 0.
     */
    private Long stock;
}