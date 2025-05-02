package co.prueba.tenica.backend.service;

import co.prueba.tenica.backend.dto.SucursalDto;
import co.prueba.tenica.backend.dto.in.InCrearSucursalDto;
import co.prueba.tenica.backend.dto.resp.RespuestaGeneralDto;
import co.prueba.tenica.backend.entity.SucursalModel;
import co.prueba.tenica.backend.repository.ISucursalRepository;
import co.prueba.tenica.backend.service.impl.CrearSucursalService;
import co.prueba.tenica.backend.utils.mapper.SucursalMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static co.prueba.tenica.backend.utils.Constantes.MSG_SUCURSAL_CREACION_ERROR;
import static co.prueba.tenica.backend.utils.Constantes.MSG_SUCURSAL_CREADA_EXITOSA;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CrearSucursalServiceTest {

    @Mock
    private ISucursalRepository sucursalRepository;

    @Mock
    private SucursalMapper sucursalMapper;

    @InjectMocks
    private CrearSucursalService service;

    private InCrearSucursalDto inDto;
    private SucursalModel model;

    @BeforeEach
    void setUp() {
        inDto = new InCrearSucursalDto();
        inDto.setNombre("Suc Test");
        inDto.setIdFranquicia(10L);

        model = new SucursalModel();
        model.setId(1L);
        model.setNombre("Suc Test");
        model.setFranquiciaId(10L);
    }

    @Test
    void crearSucursalSuccessReturnsResponseWithoutError() {
        when(sucursalMapper.toModel(any(SucursalDto.class))).thenReturn(model);
        when(sucursalRepository.save(any(SucursalModel.class))).thenReturn(Mono.just(model));

        Mono<RespuestaGeneralDto<Void>> result = service.crearSucursal(inDto);

        StepVerifier.create(result)
                .assertNext(resp -> {
                    assert !resp.isError();
                    assert resp.getRespuesta().equals(MSG_SUCURSAL_CREADA_EXITOSA);
                })
                .verifyComplete();
    }

    @Test
    void crearSucursalErrorReturnsErrorResponse() {
        when(sucursalMapper.toModel(any(SucursalDto.class))).thenReturn(model);
        when(sucursalRepository.save(any(SucursalModel.class)))
                .thenReturn(Mono.error(new RuntimeException("DB fail")));

        Mono<RespuestaGeneralDto<Void>> result = service.crearSucursal(inDto);

        StepVerifier.create(result)
                .assertNext(resp -> {
                    assert resp.isError();
                    assert resp.getRespuesta().equals(MSG_SUCURSAL_CREACION_ERROR);
                })
                .verifyComplete();
    }
}
