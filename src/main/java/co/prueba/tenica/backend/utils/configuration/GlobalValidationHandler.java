package co.prueba.tenica.backend.utils.configuration;

import co.prueba.tenica.backend.dto.resp.RespuestaGeneralDto;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

import static co.prueba.tenica.backend.utils.Constantes.*;

/**
 * Manejador global de errores de validación para endpoints WebFlux.
 * Captura tanto errores en el cuerpo de la petición (@Valid)
 * como en parámetros (@RequestParam, @PathVariable).
 */
@RestControllerAdvice
public class GlobalValidationHandler {

    /**
     * Maneja errores de validación del cuerpo de la petición.
     *
     * @param ex excepción que contiene los errores de campos
     * @return un Mono con ResponseEntity conteniendo un DTO con los mensajes de error
     */
    @ExceptionHandler(WebExchangeBindException.class)
    public Mono<ResponseEntity<RespuestaGeneralDto<List<String>>>> handleBodyValidationError(
            WebExchangeBindException ex) {

        List<String> errores = ex.getFieldErrors().stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .collect(Collectors.toList());

        RespuestaGeneralDto<List<String>> dto = new RespuestaGeneralDto<>();
        dto.setError(true);
        dto.setRespuesta(MSG_VALIDATION_ERRORS_BODY);
        dto.setData(errores);

        return Mono.just(ResponseEntity.badRequest().body(dto));
    }

    /**
     * Maneja errores de validación de parámetros de la petición.
     *
     * @param ex excepción que contiene las violaciones de restricciones
     * @return un Mono con ResponseEntity conteniendo un DTO con los mensajes de error
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public Mono<ResponseEntity<RespuestaGeneralDto<List<String>>>> handleParamValidationError(
            ConstraintViolationException ex) {

        List<String> errores = ex.getConstraintViolations().stream()
                .map(v -> v.getPropertyPath() + ": " + v.getMessage())
                .collect(Collectors.toList());

        RespuestaGeneralDto<List<String>> dto = new RespuestaGeneralDto<>();
        dto.setError(true);
        dto.setRespuesta(MSG_VALIDATION_ERRORS_PARAMS);
        dto.setData(errores);

        return Mono.just(ResponseEntity.badRequest().body(dto));
    }
}
