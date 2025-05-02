package co.prueba.tenica.backend.service;

import co.prueba.tenica.backend.dto.in.InModificarNombreDto;
import co.prueba.tenica.backend.dto.resp.RespuestaGeneralDto;
import co.prueba.tenica.backend.repository.ISucursalRepository;
import co.prueba.tenica.backend.service.impl.ModificarSucursalService;
import co.prueba.tenica.backend.utils.mapper.SucursalMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static co.prueba.tenica.backend.utils.Constantes.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ModificarSucursalServiceTest {

    @Mock
    private ISucursalRepository sucursalRepository;

    @Mock
    private SucursalMapper sucursalMapper;

    @InjectMocks
    private ModificarSucursalService service;

    private InModificarNombreDto inDto;

    @BeforeEach
    void setUp() {
        inDto = new InModificarNombreDto();
        inDto.setId(3L);
        inDto.setNombre("Sucursal Nueva");
    }

    @Test
    void modificarNombreSucursalSuccessShouldReturnSuccessResponse() {
        when(sucursalRepository.actualizarNombreSucursal(anyString(), anyLong()))
                .thenReturn(Mono.empty());

        Mono<RespuestaGeneralDto<Void>> result = service.modificarNombreSucursal(inDto);

        StepVerifier.create(result)
                .assertNext(resp -> {
                    assert !resp.isError();
                    assert resp.getRespuesta().equals(MSG_SUCURSAL_NOMBRE_MODIFICADO_EXITOSO);
                })
                .verifyComplete();
    }

    @Test
    void modificarNombreSucursalErrorShouldReturnErrorResponse() {
        when(sucursalRepository.actualizarNombreSucursal(anyString(), anyLong()))
                .thenReturn(Mono.error(new RuntimeException("fail")));

        Mono<RespuestaGeneralDto<Void>> result = service.modificarNombreSucursal(inDto);

        StepVerifier.create(result)
                .assertNext(resp -> {
                    assert resp.isError();
                    assert resp.getRespuesta().equals(MSG_SUCURSAL_NOMBRE_MODIFICACION_ERROR);
                })
                .verifyComplete();
    }
}
