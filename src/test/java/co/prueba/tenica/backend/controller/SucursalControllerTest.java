package co.prueba.tenica.backend.controller;

import co.prueba.tenica.backend.dto.in.InCrearSucursalDto;
import co.prueba.tenica.backend.dto.in.InModificarNombreDto;
import co.prueba.tenica.backend.dto.resp.RespuestaGeneralDto;
import co.prueba.tenica.backend.service.ICrearSucursalService;
import co.prueba.tenica.backend.service.IModificarSucursalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SucursalControllerTest {

    @Mock
    private ICrearSucursalService crearService;

    @Mock
    private IModificarSucursalService modificarService;

    @InjectMocks
    private SucursalController controller;

    private InCrearSucursalDto crearDto;
    private InModificarNombreDto modificarDto;

    @BeforeEach
    void setUp() {
        crearDto = new InCrearSucursalDto();
        modificarDto = new InModificarNombreDto();
    }

    @Test
    void crearSucursalSuccessReturnsCreated() {
        RespuestaGeneralDto<Void> resp = Mockito.mock(RespuestaGeneralDto.class);
        when(resp.isError()).thenReturn(false);
        when(crearService.crearSucursal(any(InCrearSucursalDto.class)))
                .thenReturn(Mono.just(resp));

        ResponseEntity<RespuestaGeneralDto<Void>> response = controller
                .crearSucursal(crearDto).block();

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(resp, response.getBody());
    }

    @Test
    void crearSucursalErrorReturnsInternalServerError() {
        RespuestaGeneralDto<Void> resp = org.mockito.Mockito.mock(RespuestaGeneralDto.class);
        when(resp.isError()).thenReturn(true);
        when(crearService.crearSucursal(any(InCrearSucursalDto.class)))
                .thenReturn(Mono.just(resp));

        ResponseEntity<RespuestaGeneralDto<Void>> response = controller
                .crearSucursal(crearDto).block();

        assertNotNull(response);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(resp, response.getBody());
    }

    @Test
    void modificarNombreSucursalSuccessReturnsOk() {
        RespuestaGeneralDto<Void> resp = org.mockito.Mockito.mock(RespuestaGeneralDto.class);
        when(resp.isError()).thenReturn(false);
        when(modificarService.modificarNombreSucursal(any(InModificarNombreDto.class)))
                .thenReturn(Mono.just(resp));

        ResponseEntity<RespuestaGeneralDto<Void>> response = controller
                .modificarNombreSucursal(modificarDto).block();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(resp, response.getBody());
    }

    @Test
    void modificarNombreSucursalErrorReturnsInternalServerError() {
        RespuestaGeneralDto<Void> resp = Mockito.mock(RespuestaGeneralDto.class);
        when(resp.isError()).thenReturn(true);
        when(modificarService.modificarNombreSucursal(any(InModificarNombreDto.class)))
                .thenReturn(Mono.just(resp));

        ResponseEntity<RespuestaGeneralDto<Void>> response = controller
                .modificarNombreSucursal(modificarDto).block();

        assertNotNull(response);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(resp, response.getBody());
    }
}
