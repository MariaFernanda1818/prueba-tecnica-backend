package co.prueba.tenica.backend.controller;

import co.prueba.tenica.backend.dto.in.InCrearProductoDto;
import co.prueba.tenica.backend.dto.in.InModificarNombreProductoDto;
import co.prueba.tenica.backend.dto.in.InModificarProductoStockDto;
import co.prueba.tenica.backend.dto.resp.RespProductoMaxStockDto;
import co.prueba.tenica.backend.dto.resp.RespuestaGeneralDto;
import co.prueba.tenica.backend.service.ICrearProductoService;
import co.prueba.tenica.backend.service.IEliminarProductoService;
import co.prueba.tenica.backend.service.IModificarProductoService;
import co.prueba.tenica.backend.service.IConsultarMaxStockProductoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductoControllerTest {

    @Mock
    private IModificarProductoService modificarService;

    @Mock
    private IEliminarProductoService eliminarService;

    @Mock
    private ICrearProductoService crearService;

    @Mock
    private IConsultarMaxStockProductoService consultarService;

    @InjectMocks
    private ProductoController controller;

    private InModificarNombreProductoDto modNameDto;
    private InCrearProductoDto crearDto;
    private InModificarProductoStockDto stockDto;
    private String codigo;
    private Long idSucursal;

    @BeforeEach
    void setUp() {
        modNameDto = new InModificarNombreProductoDto();
        crearDto = new InCrearProductoDto();
        stockDto = new InModificarProductoStockDto();
        codigo = "ABC123";
        idSucursal = 1L;
    }

    @Test
    void modificarNombreProductoSuccessReturnsOk() {
        RespuestaGeneralDto<Void> resp = org.mockito.Mockito.mock(RespuestaGeneralDto.class);
        when(resp.isError()).thenReturn(false);
        when(modificarService.modificarNombreProducto(any())).thenReturn(Mono.just(resp));

        ResponseEntity<RespuestaGeneralDto<Void>> response = controller
                .modificarNombreProducto(modNameDto).block();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(resp, response.getBody());
    }

    @Test
    void modificarNombreProductoErrorReturnsInternalServerError() {
        RespuestaGeneralDto<Void> resp = org.mockito.Mockito.mock(RespuestaGeneralDto.class);
        when(resp.isError()).thenReturn(true);
        when(modificarService.modificarNombreProducto(any())).thenReturn(Mono.just(resp));

        ResponseEntity<RespuestaGeneralDto<Void>> response = controller
                .modificarNombreProducto(modNameDto).block();

        assertNotNull(response);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(resp, response.getBody());
    }

    @Test
    void eliminarProductoSucursalSuccessReturnsOk() {
        RespuestaGeneralDto<Void> resp = org.mockito.Mockito.mock(RespuestaGeneralDto.class);
        when(resp.isError()).thenReturn(false);
        when(eliminarService.eliminarProductoSucursal(eq(codigo), eq(idSucursal)))
                .thenReturn(Mono.just(resp));

        ResponseEntity<RespuestaGeneralDto<Void>> response = controller
                .eliminarProductoSucursal(codigo, idSucursal).block();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(resp, response.getBody());
    }

    @Test
    void eliminarProductoSucursalErrorReturnsInternalServerError() {
        RespuestaGeneralDto<Void> resp = org.mockito.Mockito.mock(RespuestaGeneralDto.class);
        when(resp.isError()).thenReturn(true);
        when(eliminarService.eliminarProductoSucursal(anyString(), any()))
                .thenReturn(Mono.just(resp));

        ResponseEntity<RespuestaGeneralDto<Void>> response = controller
                .eliminarProductoSucursal(codigo, null).block();

        assertNotNull(response);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(resp, response.getBody());
    }

    @Test
    void crearProductoSuccessReturnsOk() {
        RespuestaGeneralDto<Void> resp = org.mockito.Mockito.mock(RespuestaGeneralDto.class);
        when(resp.isError()).thenReturn(false);
        when(crearService.crearProducto(any())).thenReturn(Mono.just(resp));

        ResponseEntity<RespuestaGeneralDto<Void>> response = controller
                .crearProducto(crearDto).block();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(resp, response.getBody());
    }

    @Test
    void crearProductoErrorReturnsInternalServerError() {
        RespuestaGeneralDto<Void> resp = org.mockito.Mockito.mock(RespuestaGeneralDto.class);
        when(resp.isError()).thenReturn(true);
        when(crearService.crearProducto(any())).thenReturn(Mono.just(resp));

        ResponseEntity<RespuestaGeneralDto<Void>> response = controller
                .crearProducto(crearDto).block();

        assertNotNull(response);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(resp, response.getBody());
    }

    @Test
    void modificarStockProductoSuccessReturnsOk() {
        RespuestaGeneralDto<Void> resp = org.mockito.Mockito.mock(RespuestaGeneralDto.class);
        when(resp.isError()).thenReturn(false);
        when(modificarService.modificarStockProducto(any())).thenReturn(Mono.just(resp));

        ResponseEntity<RespuestaGeneralDto<Void>> response = controller
                .modificarStockProducto(stockDto).block();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(resp, response.getBody());
    }

    @Test
    void modificarStockProductoErrorReturnsInternalServerError() {
        RespuestaGeneralDto<Void> resp = org.mockito.Mockito.mock(RespuestaGeneralDto.class);
        when(resp.isError()).thenReturn(true);
        when(modificarService.modificarStockProducto(any())).thenReturn(Mono.just(resp));

        ResponseEntity<RespuestaGeneralDto<Void>> response = controller
                .modificarStockProducto(stockDto).block();

        assertNotNull(response);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(resp, response.getBody());
    }

    @Test
    void productoStockMaxSuccessReturnsOkWithFlux() {
        RespProductoMaxStockDto item = new RespProductoMaxStockDto();
        Flux<RespProductoMaxStockDto> dataFlux = Flux.just(item);
        RespuestaGeneralDto<Flux<RespProductoMaxStockDto>> resp = org.mockito.Mockito.mock(RespuestaGeneralDto.class);
        when(resp.isError()).thenReturn(false);
        when(resp.getData()).thenReturn(dataFlux);
        when(consultarService.productosMaxStockSucursal()).thenReturn(Mono.just(resp));

        ResponseEntity<RespuestaGeneralDto<Flux<RespProductoMaxStockDto>>> response = controller
                .productoStockMax().block();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(resp, response.getBody());
        // Opcional: verificar contenido del Flux
        assertEquals(1, response.getBody().getData().collectList().block().size());
    }

    @Test
    void productoStockMaxErrorReturnsInternalServerError() {
        RespuestaGeneralDto<Flux<RespProductoMaxStockDto>> resp = org.mockito.Mockito.mock(RespuestaGeneralDto.class);
        when(resp.isError()).thenReturn(true);
        when(consultarService.productosMaxStockSucursal()).thenReturn(Mono.just(resp));

        ResponseEntity<RespuestaGeneralDto<Flux<RespProductoMaxStockDto>>> response = controller
                .productoStockMax().block();

        assertNotNull(response);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(resp, response.getBody());
    }
}