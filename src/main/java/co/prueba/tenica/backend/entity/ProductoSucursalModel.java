package co.prueba.tenica.backend.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("producto_sucursal")
@Data
public class ProductoSucursalModel {

    @Id
    private Long id;

    @Column("producto_id")
    private String productoId;

    @Column("sucursal_id")
    private Long sucursalId;

    @Column("stock")
    private Long stock;

}
