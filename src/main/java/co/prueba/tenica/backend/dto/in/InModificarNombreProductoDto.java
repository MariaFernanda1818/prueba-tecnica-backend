package co.prueba.tenica.backend.dto.in;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import static co.prueba.tenica.backend.utils.Constantes.*;

/**
 * DTO para modificar el código y el nombre de un producto existente.
 * Se utiliza para transferir el código actual y el nuevo nombre
 * en operaciones de actualización.
 */
@Data
public class InModificarNombreProductoDto {

    /**
     * Código único del producto.
     * - Obligatorio.
     */
    @NotEmpty(message = MSG_CODIGO_NOT_EMPTY)
    private String codigo;

    /**
     * Nuevo nombre a asignar al producto.
     * - Obligatorio.
     */
    @NotEmpty(message = MSG_NOMBRE_NOT_EMPTY)
    private String nombre;
}
