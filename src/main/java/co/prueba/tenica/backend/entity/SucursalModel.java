package co.prueba.tenica.backend.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Representa una sucursal en el sistema.
 * Cada sucursal pertenece a una franquicia y tiene un nombre.
 */
@Table("sucursal")
@Data
public class SucursalModel {

    /**
     * Identificador único de la sucursal.
     * Se genera automáticamente al insertar en la base de datos.
     */
    @Id
    private Long id;

    /**
     * Nombre descriptivo de la sucursal.
     * No puede ser nulo ni vacío y su longitud máxima es de 100 caracteres.
     */
    @Column("nombre")
    private String nombre;

    /**
     * Identificador de la franquicia a la que pertenece esta sucursal.
     * Hace referencia a {@code FranquiciaModel.id}.
     */
    @Column("franquicia_id")
    private Long franquiciaId;

}
