package co.prueba.tenica.backend.dto.in;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import static co.prueba.tenica.backend.utils.Constantes.*;

/**
 * DTO para modificar el nombre de una entidad.
 * Se utiliza para transferir el identificador y el nuevo nombre
 * en operaciones de actualización.
 */
@Data
public class InModificarNombreDto {

    /**
     * Identificador de la entidad a modificar.
     * - Obligatorio.
     */
    @NotNull(message = MSG_ID_OBLIGATORIO)
    private Long id;

    /**
     * Nuevo nombre a asignar.
     * - No puede estar vacío.
     * - Longitud máxima: 100 caracteres.
     */
    @NotEmpty(message = MSG_NOMBRE_NOT_EMPTY)
    @Size(max = 100, message = MSG_NOMBRE_MAX_100)
    private String nombre;
}
