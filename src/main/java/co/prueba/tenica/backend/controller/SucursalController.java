package co.prueba.tenica.backend.controller;

import co.prueba.tenica.backend.dto.in.InCrearSucursalDto;
import co.prueba.tenica.backend.dto.in.InModificarNombreDto;
import co.prueba.tenica.backend.dto.SucursalDto;
import co.prueba.tenica.backend.dto.resp.RespuestaGeneralDto;
import co.prueba.tenica.backend.service.ICrearSucursalService;
import co.prueba.tenica.backend.service.IModificarProductoService;
import co.prueba.tenica.backend.service.IModificarSucursalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import static co.prueba.tenica.backend.utils.Constantes.*;

/**
 * Controlador REST para gestionar entidades Sucursal.
 * Proporciona endpoints para crear y modificar el nombre de sucursales.
 */
@RestController
@RequestMapping(SUCURSAL_BASE_PATH)
@RequiredArgsConstructor
@Validated
public class SucursalController {

    private final ICrearSucursalService crearSucursalService;
    private final IModificarSucursalService modificarSucursalService;

    /**
     * Crea una nueva sucursal asociada a una franquicia.
     *
     * @param dto DTO con los datos de la sucursal a crear.
     * @return Mono con ResponseEntity describiendo el resultado.
     */
    @PostMapping(SUCURSAL_CREATE_PATH)
    public Mono<ResponseEntity<RespuestaGeneralDto<Void>>> crearSucursal(
            @Valid @RequestBody InCrearSucursalDto dto) {
        return crearSucursalService.crearSucursal(dto)
                .map(res -> res.isError()
                        ? ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res)
                        : ResponseEntity.status(HttpStatus.CREATED).body(res)
                );
    }

    /**
     * Modifica el nombre de una sucursal existente.
     *
     * @param dto DTO con el id de la sucursal y el nuevo nombre.
     * @return Mono con ResponseEntity describiendo el resultado.
     */
    @PutMapping(SUCURSAL_MODIFY_NAME_PATH)
    public Mono<ResponseEntity<RespuestaGeneralDto<Void>>> modificarNombreSucursal(
            @Valid @RequestBody InModificarNombreDto dto) {
        return modificarSucursalService.modificarNombreSucursal(dto)
                .map(res -> res.isError()
                        ? ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res)
                        : ResponseEntity.ok(res)
                );
    }

}
