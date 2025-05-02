package co.prueba.tenica.backend.service;

import co.prueba.tenica.backend.dto.resp.RespProductoMaxStockDto;
import co.prueba.tenica.backend.dto.resp.RespuestaGeneralDto;
import co.prueba.tenica.backend.entity.ProductoModel;
import co.prueba.tenica.backend.entity.ProductoSucursalModel;
import co.prueba.tenica.backend.entity.SucursalModel;
import co.prueba.tenica.backend.repository.IProductoRepository;
import co.prueba.tenica.backend.repository.IProductoSucursalRepository;
import co.prueba.tenica.backend.repository.ISucursalRepository;
import co.prueba.tenica.backend.service.impl.ConsultarMaxStockProductoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ConsultarMaxStockProductoServiceTest {

    @Mock
    private ISucursalRepository sucursalRepository;

    @Mock
    private IProductoSucursalRepository productoSucursalRepository;

    @Mock
    private IProductoRepository productoRepository;

    @InjectMocks
    private ConsultarMaxStockProductoService service;

    private SucursalModel sucursal1;
    private ProductoSucursalModel ps1a;
    private ProductoSucursalModel ps1b;
    private ProductoModel prodA;
    private ProductoModel prodB;

    @BeforeEach
    void setUp() {
        sucursal1 = new SucursalModel();
        sucursal1.setId(1L);
        sucursal1.setNombre("Sucursal A");

        ps1a = new ProductoSucursalModel();
        ps1a.setSucursalId(1L);
        ps1a.setProductoId("AAA54");
        ps1a.setStock(5L);

        ps1b = new ProductoSucursalModel();
        ps1b.setSucursalId(1L);
        ps1b.setProductoId("AAA55");
        ps1b.setStock(10L);

        prodA = new ProductoModel();
        prodA.setCodigo("AAA56");
        prodA.setNombre("Producto A");

        prodB = new ProductoModel();
        prodB.setCodigo("AAA57");
        prodB.setNombre("Producto B");
    }
    @Test
    void productosMaxStockSucursalReturnsMaxStockDto() {
        // Arrange
        when(sucursalRepository.findAll()).thenReturn(Flux.just(sucursal1));
        when(productoSucursalRepository.findAllBySucursalId(1L))
                .thenReturn(Flux.just(ps1a, ps1b));

        when(productoRepository.findById(ps1a.getProductoId()))
                .thenReturn(Mono.just(prodA));
        when(productoRepository.findById(ps1b.getProductoId()))
                .thenReturn(Mono.just(prodB));

        // Act
        Mono<RespuestaGeneralDto<List<RespProductoMaxStockDto>>> resultMono = service.productosMaxStockSucursal();

        // Assert
        StepVerifier.create(resultMono)
                .assertNext(respuesta -> {
                    assertNotNull(respuesta.getRespuesta());
                    List<RespProductoMaxStockDto> data = respuesta.getData();
                    assertNotNull(data);
                    assertEquals(1, data.size());

                    RespProductoMaxStockDto dto = data.get(0);
                    assertEquals("Sucursal A", dto.getNombreSucursal());
                    assertEquals(10, dto.getStock());
                    assertEquals("Producto B", dto.getNombreProducto());
                })
                .verifyComplete();
    }

    @Test
    void productosMaxStockSucursalEmptyWhenNoSucursales() {
        // Arrange
        when(sucursalRepository.findAll()).thenReturn(Flux.empty());

        // Act
        Mono<RespuestaGeneralDto<List<RespProductoMaxStockDto>>> resultMono = service.productosMaxStockSucursal();

        // Assert
        StepVerifier.create(resultMono)
                .assertNext(respuesta -> {
                    List<RespProductoMaxStockDto> data = respuesta.getData();
                    assertNotNull(data);
                    assertTrue(data.isEmpty());
                })
                .verifyComplete();
    }

}
