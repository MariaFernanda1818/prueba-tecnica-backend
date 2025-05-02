package co.prueba.tenica.backend.service;

import co.prueba.tenica.backend.dto.resp.RespuestaGeneralDto;
import co.prueba.tenica.backend.repository.IProductoRepository;
import co.prueba.tenica.backend.repository.IProductoSucursalRepository;
import co.prueba.tenica.backend.service.impl.EliminarProductoService;
import co.prueba.tenica.backend.utils.Constantes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EliminarProductoServiceTest {

    @Mock
    private IProductoSucursalRepository productoSucursalRepository;

    @Mock
    private IProductoRepository productoRepository;

    @InjectMocks
    private EliminarProductoService service;

    private String codigo;
    private Long idSucursal;

    @BeforeEach
    void setUp() {
        codigo = "PROD1";
        idSucursal = 5L;
    }

    @Test
    void eliminarGlobalSuccessShouldReturnSuccessMessage() {
        when(productoRepository.eliminarProducto(codigo)).thenReturn(Mono.empty());

        Mono<RespuestaGeneralDto<Void>> result = service.eliminarProductoSucursal(codigo, null);

        StepVerifier.create(result)
                .assertNext(resp -> {
                    assert !resp.isError();
                    String expected = String.format(Constantes.MSG_PRODUCTO_ELIMINADO_GLOBAL, codigo);
                    assert resp.getRespuesta().equals(expected);
                })
                .verifyComplete();
    }

    @Test
    void eliminarGlobalErrorShouldReturnErrorMessage() {
        when(productoRepository.eliminarProducto(codigo)).thenReturn(Mono.error(new RuntimeException("fail")));

        Mono<RespuestaGeneralDto<Void>> result = service.eliminarProductoSucursal(codigo, null);

        StepVerifier.create(result)
                .assertNext(resp -> {
                    assert resp.isError();
                    String expected = String.format(Constantes.MSG_PRODUCTO_ELIMINACION_ERROR_GLOBAL, codigo);
                    assert resp.getRespuesta().equals(expected);
                })
                .verifyComplete();
    }

    @Test
    void eliminarSucursalSuccessShouldReturnSuccessMessage() {
        when(productoSucursalRepository.eliminarProductoSucursal(codigo, idSucursal)).thenReturn(Mono.empty());

        Mono<RespuestaGeneralDto<Void>> result = service.eliminarProductoSucursal(codigo, idSucursal);

        StepVerifier.create(result)
                .assertNext(resp -> {
                    assert !resp.isError();
                    String expected = String.format(Constantes.MSG_PRODUCTO_ELIMINADO_SUCURSAL, codigo, idSucursal);
                    assert resp.getRespuesta().equals(expected);
                })
                .verifyComplete();
    }

    @Test
    void eliminarSucursalErrorShouldReturnErrorMessage() {
        when(productoSucursalRepository.eliminarProductoSucursal(codigo, idSucursal)).thenReturn(Mono.error(new RuntimeException("fail")));

        Mono<RespuestaGeneralDto<Void>> result = service.eliminarProductoSucursal(codigo, idSucursal);

        StepVerifier.create(result)
                .assertNext(resp -> {
                    assert resp.isError();
                    String expected = String.format(Constantes.MSG_PRODUCTO_ELIMINACION_ERROR_SUCURSAL, codigo, idSucursal);
                    assert resp.getRespuesta().equals(expected);
                })
                .verifyComplete();
    }
}