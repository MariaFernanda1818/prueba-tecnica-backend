package co.prueba.tenica.backend.service.impl;

import co.prueba.tenica.backend.dto.in.InModificarNombreDto;
import co.prueba.tenica.backend.dto.resp.RespuestaGeneralDto;
import co.prueba.tenica.backend.repository.ISucursalRepository;
import co.prueba.tenica.backend.service.IModificarSucursalService;
import co.prueba.tenica.backend.utils.mapper.SucursalMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static co.prueba.tenica.backend.utils.Constantes.*;

/**
 * Servicio que implementa la lógica para modificar el nombre de una sucursal.
 * Recibe un DTO con el identificador y el nuevo nombre,
 * actualiza la entidad y devuelve el resultado en un DTO genérico.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ModificarSucursalService implements IModificarSucursalService {

    private final ISucursalRepository sucursalRepository;
    private final SucursalMapper sucursalMapper;

    /**
     * Modifica el nombre de una sucursal existente.
     *
     * @param dto DTO que contiene el id de la sucursal y el nuevo nombre.
     * @return Mono que envuelve un RespuestaGeneralDto<Void> indicando el éxito o fallo.
     */
    @Override
    public Mono<RespuestaGeneralDto<Void>> modificarNombreSucursal(InModificarNombreDto dto) {
        return sucursalRepository.actualizarNombreSucursal(dto.getNombre(), dto.getId())
                // Al completar, devolvemos mensaje de éxito
                .then(Mono.fromSupplier(() -> {
                    RespuestaGeneralDto<Void> resp = new RespuestaGeneralDto<>();
                    resp.setRespuesta(MSG_SUCURSAL_NOMBRE_MODIFICADO_EXITOSO);
                    return resp;
                }))
                // Logueo en caso de error
                .doOnError(ex -> log.error(LOG_ERROR_MODIFICAR_NOMBRE_SUCURSAL, ex.getMessage(), ex))
                // En caso de fallo, devolvemos DTO con mensaje de error
                .onErrorResume(ex -> Mono.just(new RespuestaGeneralDto<Void>() {{
                    setError(true);
                    setRespuesta(MSG_SUCURSAL_NOMBRE_MODIFICACION_ERROR);
                }}));
    }
}


