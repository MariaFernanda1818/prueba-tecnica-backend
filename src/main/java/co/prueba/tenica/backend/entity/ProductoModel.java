package co.prueba.tenica.backend.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Representa un producto en el sistema.
 * Cada producto está identificado por un código alfanumérico único
 * y posee un nombre descriptivo.
 */
@Table("producto")
@Data
public class ProductoModel {

    /**
     * Código único del producto.
     * Generado automáticamente si no se proporciona.
     * Tiene una longitud máxima de 10 caracteres.
     */
    @Id
    private String codigo;

    /**
     * Nombre descriptivo del producto.
     * No puede ser nulo ni vacío y su longitud máxima es de 100 caracteres.
     */
    @Column("nombre")
    private String nombre;

}
