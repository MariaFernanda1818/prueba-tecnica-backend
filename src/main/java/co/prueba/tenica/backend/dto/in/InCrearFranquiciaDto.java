package co.prueba.tenica.backend.dto.in;

import static co.prueba.tenica.backend.utils.Constantes.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * DTO para la creación de una nueva franquicia.
 * Contiene los datos necesarios para crear una franquicia en el sistema.
 */
@Data
public class InCrearFranquiciaDto {

    /**
     * Nombre de la franquicia.
     * - No puede estar vacío.
     * - No puede ser null.
     * - Longitud máxima de 100 caracteres.
     */
    @NotBlank(message = MSG_NOMBRE_FRANQUICIA_NOT_BLANK)
    @NotNull(message = MSG_NOMBRE_FRANQUICIA_NOT_NULL)
    @Size(max = 100, message = MSG_NOMBRE_FRANQUICIA_MAX_100)
    private String nombreFranquicia;
}
