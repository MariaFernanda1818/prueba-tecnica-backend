package co.prueba.tenica.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO que representa una Franquicia de manera genérica.
 * Utilizado para transferir datos de franquicia entre capas.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FranquiciaDto {

    /**
     * Identificador único de la franquicia.
     */
    private Long id;

    /**
     * Nombre de la franquicia.
     * Debe ser un valor no nulo y con longitud máxima de 100 caracteres.
     */
    private String nombre;

}
