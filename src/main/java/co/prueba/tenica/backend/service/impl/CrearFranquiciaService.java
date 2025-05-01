package co.prueba.tenica.backend.service.impl;

import co.prueba.tenica.backend.dto.FranquiciaDto;
import co.prueba.tenica.backend.dto.InCrearFranquiciaDto;
import co.prueba.tenica.backend.dto.RespuestaGeneralDto;
import co.prueba.tenica.backend.repository.IFranquiciaRepository;
import co.prueba.tenica.backend.service.ICrearFranquiciaService;
import co.prueba.tenica.backend.utils.mapper.FranquiciaMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class CrearFranquiciaService implements ICrearFranquiciaService {

    private final IFranquiciaRepository iFranquiciaRepository;

    private final FranquiciaMapper franquiciaMapper;

    @Override
    public Mono<RespuestaGeneralDto<Void>> crearFranquicia(InCrearFranquiciaDto franquiciaDto) {
        return iFranquiciaRepository
                .save(franquiciaMapper.toModel(
                        FranquiciaDto.builder()
                                .nombre(franquiciaDto.getNombreFranquicia())
                                .build()
                ))
                .map(saved -> {
                    RespuestaGeneralDto<Void> resp = new RespuestaGeneralDto<>();
                    resp.setRespuesta("Se creó correctamente la franquicia");
                    return resp;
                })
                .onErrorResume(ex -> {
                    log.error("Error creando franquicia", ex);
                    RespuestaGeneralDto<Void> resp = new RespuestaGeneralDto<>();
                    resp.setError(true);
                    resp.setRespuesta("Hubo un error al crear la franquicia");
                    return Mono.just(resp);
                });
    }
}
