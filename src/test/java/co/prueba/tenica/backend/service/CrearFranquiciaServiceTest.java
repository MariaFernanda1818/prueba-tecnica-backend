package co.prueba.tenica.backend.service;

import co.prueba.tenica.backend.dto.FranquiciaDto;
import co.prueba.tenica.backend.dto.in.InCrearFranquiciaDto;
import co.prueba.tenica.backend.dto.resp.RespuestaGeneralDto;
import co.prueba.tenica.backend.entity.FranquiciaModel;
import co.prueba.tenica.backend.repository.IFranquiciaRepository;
import co.prueba.tenica.backend.utils.mapper.FranquiciaMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static co.prueba.tenica.backend.utils.Constantes.MSG_FRANQUICIA_CREACION_ERROR;
import static co.prueba.tenica.backend.utils.Constantes.MSG_FRANQUICIA_CREADA_EXITOSA;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CrearFranquiciaServiceTest {

    @Mock
    private IFranquiciaRepository franquiciaRepository;

    @Mock
    private FranquiciaMapper franquiciaMapper;

    @InjectMocks
    private co.prueba.tenica.backend.service.impl.CrearFranquiciaService service;

    private InCrearFranquiciaDto inDto;
    private FranquiciaModel model;

    @BeforeEach
    void setUp() {
        inDto = new InCrearFranquiciaDto();
        inDto.setNombreFranquicia("Fran Test");

        model = new FranquiciaModel();
        model.setNombre("Fran Test");
    }

    @Test
    void crearFranquiciaSuccessReturnsResponseWithoutError() {
        when(franquiciaMapper.toModel(any(FranquiciaDto.class))).thenReturn(model);
        when(franquiciaRepository.save(any(FranquiciaModel.class)))
                .thenReturn(Mono.just(model));

        Mono<RespuestaGeneralDto<Void>> result = service.crearFranquicia(inDto);

        StepVerifier.create(result)
                .assertNext(resp -> {
                    assert !resp.isError();
                    assert resp.getRespuesta().equals(MSG_FRANQUICIA_CREADA_EXITOSA);
                })
                .verifyComplete();
    }

    @Test
    void crearFranquiciaErrorReturnsErrorResponse() {
        when(franquiciaMapper.toModel(any(FranquiciaDto.class))).thenReturn(model);
        when(franquiciaRepository.save(any(FranquiciaModel.class)))
                .thenReturn(Mono.error(new RuntimeException("DB fail")));

        Mono<RespuestaGeneralDto<Void>> result = service.crearFranquicia(inDto);

        StepVerifier.create(result)
                .assertNext(resp -> {
                    assert resp.isError();
                    assert resp.getRespuesta().equals(MSG_FRANQUICIA_CREACION_ERROR);
                })
                .verifyComplete();
    }
}