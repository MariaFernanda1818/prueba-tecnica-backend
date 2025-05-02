package co.prueba.tenica.backend.entity;

import lombok.Data;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Representa la relación entre un producto y una sucursal,
 * incluyendo la cantidad de stock disponible en dicha sucursal.
 */
@Table("producto_sucursal")
@Data
public class ProductoSucursalModel {

    /**
     * Identificador del producto al que pertenece el stock.
     * Hace referencia a {@code ProductoModel.codigo}.
     */
    @Column("producto_id")
    private String productoId;

    /**
     * Identificador de la sucursal donde se registra el stock.
     * Hace referencia a {@code SucursalModel.id}.
     */
    @Column("sucursal_id")
    private Long sucursalId;

    /**
     * Cantidad de unidades disponibles del producto en la sucursal.
     * No puede ser negativo.
     */
    @Column("stock")
    private Long stock;

}
