package co.prueba.tenica.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO que representa la asociación de un producto con una sucursal,
 * incluyendo la cantidad de stock disponible en dicha sucursal.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductoSucursalDto {

    /**
     * Identificador del producto.
     * Hace referencia a {@code ProductoDto.codigo}.
     */
    private String productoId;

    /**
     * Identificador de la sucursal.
     * Hace referencia a {@code SucursalDto.id}.
     */
    private Long sucursalId;

    /**
     * Cantidad de unidades disponibles del producto en la sucursal.
     * Debe ser un valor mayor o igual a 0.
     */
    private Long stock;
}