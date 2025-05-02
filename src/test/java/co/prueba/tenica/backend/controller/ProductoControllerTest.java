package co.prueba.tenica.backend.controller;

import co.prueba.tenica.backend.dto.AdjuntarSucursalesDto;
import co.prueba.tenica.backend.dto.in.InAgregarProductoSucursalDto;
import co.prueba.tenica.backend.dto.in.InCrearProductoDto;
import co.prueba.tenica.backend.dto.in.InModificarNombreProductoDto;
import co.prueba.tenica.backend.dto.in.InModificarProductoStockDto;
import co.prueba.tenica.backend.dto.resp.RespProductoMaxStockDto;
import co.prueba.tenica.backend.dto.resp.RespuestaGeneralDto;
import co.prueba.tenica.backend.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

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
    private IAgregarProductoSucursalService agregarProductoSucursalService;

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
        // Arrange
        RespProductoMaxStockDto item = new RespProductoMaxStockDto();
        item.setNombreSucursal("Sucursal A");
        item.setNombreProducto("Producto X");
        item.setStock(50L);

        List<RespProductoMaxStockDto> data = List.of(item);

        RespuestaGeneralDto<List<RespProductoMaxStockDto>> responseDto = new RespuestaGeneralDto<>();
        responseDto.setError(false);
        responseDto.setRespuesta("Consulta exitosa");
        responseDto.setData(data);

        when(consultarService.productosMaxStockSucursal()).thenReturn(Mono.just(responseDto));

        // Act
        ResponseEntity<RespuestaGeneralDto<List<RespProductoMaxStockDto>>> response = controller.productoStockMax().block();

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertFalse(response.getBody().isError());

        // Validar contenido del Flux
        List<RespProductoMaxStockDto> resultList = response.getBody().getData();
        assertNotNull(resultList);
        assertEquals(1, resultList.size());
        assertEquals("Sucursal A", resultList.get(0).getNombreSucursal());
    }

    @Test
    void productoStockMaxErrorReturnsInternalServerError() {
        // Arrange
        RespuestaGeneralDto<List<RespProductoMaxStockDto>> responseDto = new RespuestaGeneralDto<>();
        responseDto.setError(true);
        responseDto.setRespuesta("Ocurrió un error");

        when(consultarService.productosMaxStockSucursal()).thenReturn(Mono.just(responseDto));

        // Act
        ResponseEntity<RespuestaGeneralDto<List<RespProductoMaxStockDto>>> response = controller.productoStockMax().block();

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().isError());
    }

    @Test
    void agregarProductoSucursalSuccessReturnsOk() {
        // Arrange
        InAgregarProductoSucursalDto dto = new InAgregarProductoSucursalDto();
        dto.setCodigoProducto("P021");
        dto.setSucursalesAdjuntar(List.of(
                new AdjuntarSucursalesDto(2L, 10000L),
                new AdjuntarSucursalesDto(3L, 10000L)
        ));

        RespuestaGeneralDto<Void> resp = Mockito.mock(RespuestaGeneralDto.class);
        when(resp.isError()).thenReturn(false);
        when(agregarProductoSucursalService.agregarProductoSucursal(any()))
                .thenReturn(Mono.just(resp));

        // Act
        ResponseEntity<RespuestaGeneralDto<Void>> response = controller
                .agregarProductoSucursal(dto)
                .block();

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(resp, response.getBody());
    }

    @Test
    void agregarProductoSucursalErrorReturnsInternalServerError() {
        // Arrange
        InAgregarProductoSucursalDto dto = new InAgregarProductoSucursalDto();
        dto.setCodigoProducto("P021");
        dto.setSucursalesAdjuntar(List.of(
                new AdjuntarSucursalesDto(2L, 10000L)
        ));

        RespuestaGeneralDto<Void> resp = Mockito.mock(RespuestaGeneralDto.class);
        when(resp.isError()).thenReturn(true);
        when(agregarProductoSucursalService.agregarProductoSucursal(any()))
                .thenReturn(Mono.just(resp));

        // Act
        ResponseEntity<RespuestaGeneralDto<Void>> response = controller
                .agregarProductoSucursal(dto)
                .block();

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(resp, response.getBody());
    }


}