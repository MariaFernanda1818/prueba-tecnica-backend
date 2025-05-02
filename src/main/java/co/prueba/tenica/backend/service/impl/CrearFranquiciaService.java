package co.prueba.tenica.backend.service.impl;

import co.prueba.tenica.backend.dto.FranquiciaDto;
import co.prueba.tenica.backend.dto.in.InCrearFranquiciaDto;
import co.prueba.tenica.backend.dto.resp.RespuestaGeneralDto;
import co.prueba.tenica.backend.repository.IFranquiciaRepository;
import co.prueba.tenica.backend.service.ICrearFranquiciaService;
import co.prueba.tenica.backend.utils.mapper.FranquiciaMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static co.prueba.tenica.backend.utils.Constantes.*;

/**
 * Servicio que implementa la lógica de creación de franquicias.
 * Recibe un DTO con los datos de la franquicia, la transforma en entidad,
 * la persiste y devuelve un DTO genérico con el resultado de la operación.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class CrearFranquiciaService implements ICrearFranquiciaService {

    private final IFranquiciaRepository franquiciaRepository;
    private final FranquiciaMapper franquiciaMapper;

    /**
     * Crea una nueva franquicia en el sistema.
     *
     * @param franquiciaDto DTO que contiene el nombre de la franquicia a crear.
     * @return Mono que envuelve un RespuestaGeneralDto<Void> indicando
     *         el éxito o fallo de la operación.
     */
    @Override
    public Mono<RespuestaGeneralDto<Void>> crearFranquicia(InCrearFranquiciaDto franquiciaDto) {
        return franquiciaRepository
                .save(franquiciaMapper.toModel(
                        FranquiciaDto.builder()
                                .nombre(franquiciaDto.getNombreFranquicia())
                                .build()
                ))
                .map(saved -> {
                    RespuestaGeneralDto<Void> resp = new RespuestaGeneralDto<>();
                    resp.setRespuesta(MSG_FRANQUICIA_CREADA_EXITOSA);
                    return resp;
                })
                .onErrorResume(ex -> {
                    log.error(MSG_ERROR_CREAR_FRANQUICIA, ex);
                    RespuestaGeneralDto<Void> resp = new RespuestaGeneralDto<>();
                    resp.setError(true);
                    resp.setRespuesta(MSG_FRANQUICIA_CREACION_ERROR);
                    return Mono.just(resp);
                });
    }
}
