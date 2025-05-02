package co.prueba.tenica.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO que representa los datos de una sucursal.
 * Se utiliza para transferir información de sucursal
 * entre las diferentes capas de la aplicación.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SucursalDto {

    /**
     * Identificador único de la sucursal.
     */
    private Long id;

    /**
     * Nombre descriptivo de la sucursal.
     * No puede ser nulo ni vacío y su longitud máxima es de 100 caracteres.
     */
    private String nombre;

    /**
     * Identificador de la franquicia a la que pertenece la sucursal.
     * Hace referencia a {@code FranquiciaDto.id}.
     */
    private Long franquiciaId;
}
