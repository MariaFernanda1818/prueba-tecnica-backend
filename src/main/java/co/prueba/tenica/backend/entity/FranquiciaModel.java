package co.prueba.tenica.backend.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("franquicia")
@Data
public class FranquiciaModel {

    @Id
    private Long id;

    @Column("nombre")
    private String nombre;


}
