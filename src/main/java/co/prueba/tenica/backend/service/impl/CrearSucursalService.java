package co.prueba.tenica.backend.service.impl;


import co.prueba.tenica.backend.dto.SucursalDto;
import co.prueba.tenica.backend.dto.in.InCrearSucursalDto;
import co.prueba.tenica.backend.dto.resp.RespuestaGeneralDto;
import co.prueba.tenica.backend.repository.ISucursalRepository;
import co.prueba.tenica.backend.service.ICrearSucursalService;
import co.prueba.tenica.backend.utils.mapper.SucursalMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static co.prueba.tenica.backend.utils.Constantes.*;

/**
 * Servicio que implementa la lógica de creación de sucursales.
 * Recibe un DTO con los datos de la sucursal, la transforma en entidad,
 * la persiste y devuelve un DTO genérico con el resultado de la operación.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class CrearSucursalService implements ICrearSucursalService {

    private final ISucursalRepository sucursalRepository;
    private final SucursalMapper sucursalMapper;

    /**
     * Crea una nueva sucursal asociada a una franquicia.
     *
     * @param inCrearSucursalDto DTO que contiene el nombre de la sucursal
     *                           y el identificador de la franquicia.
     * @return Mono que envuelve un RespuestaGeneralDto<Void> con el resultado de la operación.
     */
    @Override
    public Mono<RespuestaGeneralDto<Void>> crearSucursal(InCrearSucursalDto inCrearSucursalDto) {
        SucursalDto dto = SucursalDto.builder()
                .nombre(inCrearSucursalDto.getNombre())
                .franquiciaId(inCrearSucursalDto.getIdFranquicia())
                .build();

        return sucursalRepository
                .save(sucursalMapper.toModel(dto))
                .map(saved -> {
                    RespuestaGeneralDto<Void> resp = new RespuestaGeneralDto<>();
                    resp.setRespuesta(MSG_SUCURSAL_CREADA_EXITOSA);
                    return resp;
                })
                .doOnError(ex -> log.error(LOG_ERROR_CREADO_SUCURSAL, ex))
                .onErrorResume(ex -> {
                    RespuestaGeneralDto<Void> resp = new RespuestaGeneralDto<>();
                    resp.setError(true);
                    resp.setRespuesta(MSG_SUCURSAL_CREACION_ERROR);
                    return Mono.just(resp);
                });
    }
}
