package co.prueba.tenica.backend.service;

import co.prueba.tenica.backend.dto.in.InModificarNombreDto;
import co.prueba.tenica.backend.dto.resp.RespuestaGeneralDto;
import co.prueba.tenica.backend.entity.FranquiciaModel;
import co.prueba.tenica.backend.repository.IFranquiciaRepository;
import co.prueba.tenica.backend.service.impl.ModificarFranquiciaService;
import co.prueba.tenica.backend.utils.mapper.FranquiciaMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static co.prueba.tenica.backend.utils.Constantes.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ModificarFranquiciaServiceTest {

    @Mock
    private IFranquiciaRepository franquiciaRepository;

    @Mock
    private FranquiciaMapper franquiciaMapper;

    @InjectMocks
    private ModificarFranquiciaService service;

    private InModificarNombreDto inDto;
    private FranquiciaModel model;

    @BeforeEach
    void setUp() {
        inDto = new InModificarNombreDto();
        inDto.setId(42L);
        inDto.setNombre("Nuevo Nombre");

        model = new FranquiciaModel();
        model.setId(42L);
        model.setNombre("Nuevo Nombre");
    }

    @Test
    void modificarFranquiciaSuccessShouldReturnSuccessResponse() {
        when(franquiciaMapper.toModel(any())).thenReturn(model);
        when(franquiciaRepository.save(any(FranquiciaModel.class))).thenReturn(Mono.just(model));

        Mono<RespuestaGeneralDto<Void>> result = service.modificarFranquicia(inDto);

        StepVerifier.create(result)
                .assertNext(resp -> {
                    assert !resp.isError();
                    assert resp.getRespuesta().equals(MSG_FRANQUICIA_MODIFICADA_EXITOSA);
                })
                .verifyComplete();
    }

    @Test
    void modificarFranquiciaErrorShouldReturnErrorResponse() {
        when(franquiciaMapper.toModel(any())).thenReturn(model);
        when(franquiciaRepository.save(any(FranquiciaModel.class)))
                .thenReturn(Mono.error(new RuntimeException("fail")));

        Mono<RespuestaGeneralDto<Void>> result = service.modificarFranquicia(inDto);

        StepVerifier.create(result)
                .assertNext(resp -> {
                    assert resp.isError();
                    assert resp.getRespuesta().equals(MSG_FRANQUICIA_MODIFICACION_ERROR);
                })
                .verifyComplete();
    }
}
