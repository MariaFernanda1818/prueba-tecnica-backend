package co.prueba.tenica.backend.entity.r2dbc;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("producto")
@Data
public class ProductoModel {

    @Id
    private Long id;

    @Column("nombre")
    private String nombre;

    @Column("stock")
    private Integer stock;

    @Column("sucursal_id")
    private Long sucursalId;

}
