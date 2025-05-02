package co.prueba.tenica.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO que representa los datos de un producto.
 * Se utiliza para transferir información de producto
 * entre las diferentes capas de la aplicación.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductoDto {

    /**
     * Código alfanumérico único del producto.
     * Generado automáticamente si no se proporciona,
     * con longitud máxima de 10 caracteres.
     */
    private String codigo;

    /**
     * Nombre descriptivo del producto.
     * No puede ser nulo ni vacío y su longitud máxima es de 100 caracteres.
     */
    private String nombre;
}
