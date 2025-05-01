package co.prueba.tenica.backend.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("producto")
@Data
public class ProductoModel {

    @Id
    private String codigo;

    @Column("nombre")
    private String nombre;


}
