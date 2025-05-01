package co.prueba.tenica.backend.service.impl;

import co.prueba.tenica.backend.dto.FranquiciaDto;
import co.prueba.tenica.backend.dto.InModificarNombreDto;
import co.prueba.tenica.backend.dto.RespuestaGeneralDto;
import co.prueba.tenica.backend.repository.IFranquiciaRepository;
import co.prueba.tenica.backend.service.IModificarFranquiciaService;
import co.prueba.tenica.backend.utils.mapper.FranquiciaMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class ModificarFranquiciaService implements IModificarFranquiciaService {


    private final IFranquiciaRepository iFranquiciaRepository;

    private final FranquiciaMapper franquiciaMapper;

    @Override
    public Mono<RespuestaGeneralDto<Void>> modificarFranquiciaReact(InModificarNombreDto inModificarNombreDto) {
        return iFranquiciaRepository.save(
                franquiciaMapper.toModel(FranquiciaDto.builder().id(inModificarNombreDto.getId()).nombre(inModificarNombreDto.getNombre()).build())
        ).then(Mono.fromSupplier(() -> {
            RespuestaGeneralDto<Void> respuesta = new RespuestaGeneralDto<>();
            respuesta.setRespuesta("Se modificó correctamente el nombre de la franquicia");
            respuesta.setError(false);
            respuesta.setData(null);
            return respuesta;
        })).onErrorResume(ex -> {
            log.error("Error modificando franquicia: {}", ex.getMessage());
            RespuestaGeneralDto<Void> errorRespuesta = new RespuestaGeneralDto<>();
            errorRespuesta.setRespuesta("Hubo un error al modificar el nombre de la franquicia");
            errorRespuesta.setError(true);
            errorRespuesta.setData(null);
            return Mono.just(errorRespuesta);
        });
    }
}
