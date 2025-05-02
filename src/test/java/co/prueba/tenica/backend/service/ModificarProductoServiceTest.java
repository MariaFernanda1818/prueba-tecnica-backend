package co.prueba.tenica.backend.service;

import co.prueba.tenica.backend.dto.in.InModificarNombreProductoDto;
import co.prueba.tenica.backend.dto.in.InModificarProductoStockDto;
import co.prueba.tenica.backend.dto.resp.RespuestaGeneralDto;
import co.prueba.tenica.backend.repository.IProductoRepository;
import co.prueba.tenica.backend.repository.IProductoSucursalRepository;
import co.prueba.tenica.backend.service.impl.ModificarProductoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static co.prueba.tenica.backend.utils.Constantes.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ModificarProductoServiceTest {

    @Mock
    private IProductoRepository productoRepository;

    @Mock
    private IProductoSucursalRepository productoSucursalRepository;

    @InjectMocks
    private ModificarProductoService service;

    private InModificarNombreProductoDto nombreDto;
    private InModificarProductoStockDto stockDto;

    @BeforeEach
    void setUp() {
        nombreDto = new InModificarNombreProductoDto();
        nombreDto.setCodigo("CODE1");
        nombreDto.setNombre("Nuevo Nombre");

        stockDto = new InModificarProductoStockDto();
        stockDto.setCodigoProducto("CODE1");
        stockDto.setIdSucursal(2L);
        stockDto.setNuevoStock(50L);
    }

    @Test
    void modificarNombreProductoSuccessShouldReturnSuccessResponse() {
        when(productoRepository.actualizarNombreProducto(anyString(), anyString()))
                .thenReturn(Mono.empty());

        Mono<RespuestaGeneralDto<Void>> result = service.modificarNombreProducto(nombreDto);

        StepVerifier.create(result)
                .assertNext(resp -> {
                    assert !resp.isError();
                    assert resp.getRespuesta().equals(MSG_PRODUCTO_NOMBRE_MODIFICADO_EXITOSO);
                })
                .verifyComplete();
    }

    @Test
    void modificarNombreProductoErrorShouldReturnErrorResponse() {
        when(productoRepository.actualizarNombreProducto(anyString(), anyString()))
                .thenReturn(Mono.error(new RuntimeException("fail")));

        Mono<RespuestaGeneralDto<Void>> result = service.modificarNombreProducto(nombreDto);

        StepVerifier.create(result)
                .assertNext(resp -> {
                    assert resp.isError();
                    assert resp.getRespuesta().equals(MSG_PRODUCTO_NOMBRE_MODIFICACION_ERROR);
                })
                .verifyComplete();
    }

    @Test
    void modificarStockProductoSuccessShouldReturnSuccessResponse() {
        when(productoSucursalRepository.actualizarStockProductoSucursal(anyString(), anyLong(), anyLong()))
                .thenReturn(Mono.empty());

        Mono<RespuestaGeneralDto<Void>> result = service.modificarStockProducto(stockDto);

        StepVerifier.create(result)
                .assertNext(resp -> {
                    assert !resp.isError();
                    assert resp.getRespuesta().equals(MSG_PRODUCTO_STOCK_MODIFICADO_EXITOSO);
                })
                .verifyComplete();
    }

    @Test
    void modificarStockProductoErrorShouldReturnErrorResponse() {
        when(productoSucursalRepository.actualizarStockProductoSucursal(anyString(), anyLong(), anyLong()))
                .thenReturn(Mono.error(new RuntimeException("fail")));

        Mono<RespuestaGeneralDto<Void>> result = service.modificarStockProducto(stockDto);

        StepVerifier.create(result)
                .assertNext(resp -> {
                    assert resp.isError();
                    assert resp.getRespuesta().equals(MSG_PRODUCTO_STOCK_MODIFICACION_ERROR);
                })
                .verifyComplete();
    }
}
