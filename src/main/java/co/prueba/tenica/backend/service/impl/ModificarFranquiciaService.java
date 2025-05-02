package co.prueba.tenica.backend.service.impl;

import co.prueba.tenica.backend.dto.FranquiciaDto;
import co.prueba.tenica.backend.dto.in.InModificarNombreDto;
import co.prueba.tenica.backend.dto.resp.RespuestaGeneralDto;
import co.prueba.tenica.backend.repository.IFranquiciaRepository;
import co.prueba.tenica.backend.service.IModificarFranquiciaService;
import co.prueba.tenica.backend.utils.mapper.FranquiciaMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static co.prueba.tenica.backend.utils.Constantes.*;

/**
 * Servicio que implementa la lógica para modificar el nombre de una franquicia.
 * Recibe un DTO con el identificador y el nuevo nombre,
 * persiste el cambio y devuelve un DTO genérico con el resultado.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ModificarFranquiciaService implements IModificarFranquiciaService {

    private final IFranquiciaRepository franquiciaRepository;
    private final FranquiciaMapper franquiciaMapper;

    /**
     * Modifica el nombre de una franquicia existente.
     *
     * @param inModificarNombreDto DTO que contiene el id y el nuevo nombre de la franquicia.
     * @return Mono que envuelve un RespuestaGeneralDto<Void> indicando el éxito o fallo.
     */
    @Override
    public Mono<RespuestaGeneralDto<Void>> modificarFranquicia(InModificarNombreDto inModificarNombreDto) {
        // Construir entidad a partir del DTO
        FranquiciaDto dto = FranquiciaDto.builder()
                .id(inModificarNombreDto.getId())
                .nombre(inModificarNombreDto.getNombre())
                .build();

        return franquiciaRepository.save(franquiciaMapper.toModel(dto))
                // Al completarse, devolver DTO con mensaje de éxito
                .then(Mono.fromSupplier(() -> {
                    RespuestaGeneralDto<Void> resp = new RespuestaGeneralDto<>();
                    resp.setRespuesta(MSG_FRANQUICIA_MODIFICADA_EXITOSA);
                    resp.setError(false);
                    return resp;
                }))
                // En caso de error, capturar y devolver DTO con mensaje de fallo
                .doOnError(ex -> log.error(LOG_ERROR_MODIFICAR_FRANQUICIA, ex.getMessage(), ex))
                .onErrorResume(ex -> Mono.just(
                        new RespuestaGeneralDto<Void>() {{
                            setError(true);
                            setRespuesta(MSG_FRANQUICIA_MODIFICACION_ERROR);
                        }}
                ));
    }
}