package co.prueba.tenica.backend.dto.in;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import static co.prueba.tenica.backend.utils.Constantes.*;

/**
 * DTO para la creación de una nueva sucursal.
 * Contiene los datos necesarios para registrar una sucursal
 * asociada a una franquicia.
 */
@Data
public class InCrearSucursalDto {

    /**
     * Nombre de la sucursal.
     * - Obligatorio.
     * - Longitud máxima: 100 caracteres.
     */
    @NotBlank(message = MSG_NOMBRE_SUCURSAL_OBLIGATORIO)
    @Size(max = 100, message = MSG_NOMBRE_SUCURSAL_MAX_100)
    private String nombre;

    /**
     * Identificador de la franquicia a la que pertenece la sucursal.
     * - Obligatorio.
     */
    @NotNull(message = MSG_ID_FRANQUICIA_OBLIGATORIO)
    private Long idFranquicia;
}
