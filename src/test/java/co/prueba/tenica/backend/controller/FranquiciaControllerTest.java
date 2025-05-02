package co.prueba.tenica.backend.controller;

import co.prueba.tenica.backend.dto.in.InCrearFranquiciaDto;
import co.prueba.tenica.backend.dto.in.InModificarNombreDto;
import co.prueba.tenica.backend.dto.resp.RespuestaGeneralDto;
import co.prueba.tenica.backend.service.ICrearFranquiciaService;
import co.prueba.tenica.backend.service.IModificarFranquiciaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

import static co.prueba.tenica.backend.utils.Constantes.CREATE_PATH;
import static co.prueba.tenica.backend.utils.Constantes.MODIFY_NAME_PATH;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FranquiciaControllerTest {

    @Mock
    private ICrearFranquiciaService crearService;

    @Mock
    private IModificarFranquiciaService modificarService;

    @InjectMocks
    private FranquiciaController controller;

    private InCrearFranquiciaDto crearDto;
    private InModificarNombreDto modificarDto;

    @BeforeEach
    void setUp() {
        crearDto = new InCrearFranquiciaDto();
        modificarDto = new InModificarNombreDto();
    }

    @Test
    void crearFranquiciaSuccessReturnsCreated() {
        RespuestaGeneralDto<Void> dto = org.mockito.Mockito.mock(RespuestaGeneralDto.class);
        when(dto.isError()).thenReturn(false);
        when(crearService.crearFranquicia(any(InCrearFranquiciaDto.class)))
                .thenReturn(Mono.just(dto));

        ResponseEntity<RespuestaGeneralDto<Void>> response =
                controller.crearFranquicia(crearDto).block();

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(dto, response.getBody());
    }

    @Test
    void crearFranquiciaErrorReturnsBadRequest() {
        RespuestaGeneralDto<Void> dto = org.mockito.Mockito.mock(RespuestaGeneralDto.class);
        when(dto.isError()).thenReturn(true);
        when(crearService.crearFranquicia(any(InCrearFranquiciaDto.class)))
                .thenReturn(Mono.just(dto));

        ResponseEntity<RespuestaGeneralDto<Void>> response =
                controller.crearFranquicia(crearDto).block();

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(dto, response.getBody());
    }

    @Test
    void modificarNombreSuccessReturnsOk() {
        RespuestaGeneralDto<Void> dto = org.mockito.Mockito.mock(RespuestaGeneralDto.class);
        when(dto.isError()).thenReturn(false);
        when(modificarService.modificarFranquicia(any(InModificarNombreDto.class)))
                .thenReturn(Mono.just(dto));

        ResponseEntity<RespuestaGeneralDto<Void>> response =
                controller.modificarNombre(modificarDto).block();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dto, response.getBody());
    }

    @Test
    void modificarNombreErrorReturnsBadRequest() {
        RespuestaGeneralDto<Void> dto = org.mockito.Mockito.mock(RespuestaGeneralDto.class);
        when(dto.isError()).thenReturn(true);
        when(modificarService.modificarFranquicia(any(InModificarNombreDto.class)))
                .thenReturn(Mono.just(dto));

        ResponseEntity<RespuestaGeneralDto<Void>> response =
                controller.modificarNombre(modificarDto).block();

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(dto, response.getBody());
    }
}
