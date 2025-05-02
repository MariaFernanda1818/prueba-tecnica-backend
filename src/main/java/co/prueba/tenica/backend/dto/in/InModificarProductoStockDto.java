package co.prueba.tenica.backend.dto.in;

import lombok.Data;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import static co.prueba.tenica.backend.utils.Constantes.*;

/**
 * DTO para modificar el stock de un producto en una sucursal.
 * Contiene el código del producto, el identificador de la sucursal
 * y el nuevo valor de stock a asignar.
 */
@Data
public class InModificarProductoStockDto {

    /**
     * Código del producto a modificar.
     * - Obligatorio.
     * - Longitud máxima: 10 caracteres.
     */
    @NotBlank(message = MSG_CODIGO_OBLIGATORIO)
    @Size(max = 10, message = MSG_CODIGO_MAX_10)
    private String codigoProducto;

    /**
     * Identificador de la sucursal donde se modificará el stock.
     * - Obligatorio.
     */
    @NotNull(message = MSG_ID_SUCURSAL_OBLIGATORIO)
    private Long idSucursal;

    /**
     * Nuevo valor de stock para el producto en la sucursal.
     * - Obligatorio.
     * - No puede ser negativo.
     */
    @NotNull(message = MSG_NUEVO_STOCK_OBLIGATORIO)
    @Min(value = 0, message = MSG_NUEVO_STOCK_NO_NEGATIVO)
    private Long nuevoStock;
}
