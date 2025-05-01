package co.prueba.tenica.backend.dto;

import lombok.Data;

@Data
public class RespuestaGeneralDto<T> {

    private String respuesta;

    private boolean error;

    private T data;

}
