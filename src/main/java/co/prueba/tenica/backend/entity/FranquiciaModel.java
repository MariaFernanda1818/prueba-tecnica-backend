package co.prueba.tenica.backend.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Representa una franquicia en el sistema.
 * Cada franquicia tiene un identificador único y un nombre.
 */
@Table("franquicia")
@Data
public class FranquiciaModel {

    /**
     * Identificador único de la franquicia.
     * Se genera automáticamente al insertar en la base de datos.
     */
    @Id
    private Long id;

    /**
     * Nombre de la franquicia.
     * No puede ser nulo ni vacío y tiene un máximo de 100 caracteres.
     */
    @Column("nombre")
    private String nombre;

}
