package co.prueba.tenica.backend.controller;

import co.prueba.tenica.backend.dto.in.InCrearFranquiciaDto;
import co.prueba.tenica.backend.dto.in.InModificarNombreDto;
import co.prueba.tenica.backend.dto.resp.RespuestaGeneralDto;
import co.prueba.tenica.backend.service.ICrearFranquiciaService;
import co.prueba.tenica.backend.service.IModificarFranquiciaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import static co.prueba.tenica.backend.utils.Constantes.*;

/**
 * Controlador REST para gestionar entidades Franquicia.
 * Proporciona endpoints para crear y modificar el nombre de una Franquicia.
 */
@RestController
@RequestMapping(FRANQUICIA_BASE_PATH)
@RequiredArgsConstructor
@Validated
public class FranquiciaController {

    private final ICrearFranquiciaService crearFranquiciaService;
    private final IModificarFranquiciaService modificarFranquiciaService;

    /**
     * Crea una nueva franquicia.
     *
     * @param franquiciaDto DTO con los datos de la franquicia a crear.
     * @return Mono que envuelve un ResponseEntity con el resultado de la operación.
     */
    @PostMapping(CREATE_PATH)
    public Mono<ResponseEntity<RespuestaGeneralDto<Void>>> crearFranquicia(
            @Valid @RequestBody InCrearFranquiciaDto franquiciaDto) {
        return crearFranquiciaService.crearFranquicia(franquiciaDto)
                .map(respuesta -> {
                    if (respuesta.isError()) {
                        return ResponseEntity.badRequest().body(respuesta);
                    } else {
                        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
                    }
                });
    }

    /**
     * Modifica el nombre de una franquicia existente.
     *
     * @param inModificarNombreDto DTO con el id de la franquicia y el nuevo nombre.
     * @return Mono que envuelve un ResponseEntity con el resultado de la operación.
     */
    @PutMapping(MODIFY_NAME_PATH)
    public Mono<ResponseEntity<RespuestaGeneralDto<Void>>> modificarNombre(
            @Valid @RequestBody InModificarNombreDto inModificarNombreDto) {
        return modificarFranquiciaService.modificarFranquicia(inModificarNombreDto)
                .map(respuesta -> {
                    if (respuesta.isError()) {
                        return ResponseEntity.badRequest().body(respuesta);
                    } else {
                        return ResponseEntity.ok(respuesta);
                    }
                });
    }
}
