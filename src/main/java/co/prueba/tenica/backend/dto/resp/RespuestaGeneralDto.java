package co.prueba.tenica.backend.dto.resp;

import lombok.Data;

/**
 * DTO genérico para respuestas de la API.
 * Permite encapsular un mensaje de respuesta, un indicador de error
 * y un objeto de datos de tipo genérico.
 *
 * @param <T> Tipo de dato que se incluirá en el campo data.
 */
@Data
public class RespuestaGeneralDto<T> {

    /**
     * Mensaje descriptivo de la operación realizada.
     */
    private String respuesta;

    /**
     * Indicador de si ocurrió un error durante la operación.
     * - {@code true}: hubo un error.
     * - {@code false}: la operación fue exitosa.
     */
    private boolean error;

    /**
     * Datos adicionales de la respuesta, de tipo genérico.
     * Puede contener cualquier objeto asociado al resultado.
     */
    private T data;

}
