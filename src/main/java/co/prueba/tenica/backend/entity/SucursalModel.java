package co.prueba.tenica.backend.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("sucursal")
@Data
public class SucursalModel {

    @Id
    private Long id;

    @Column("nombre")
    private String nombre;

    @Column("franquicia_id")
    private Long franquiciaId;


}
