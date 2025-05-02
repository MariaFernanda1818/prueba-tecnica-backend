package co.prueba.tenica.backend.service.impl;

import co.prueba.tenica.backend.dto.AdjuntarSucursalesDto;
import co.prueba.tenica.backend.dto.ProductoSucursalDto;
import co.prueba.tenica.backend.dto.in.InAgregarProductoSucursalDto;
import co.prueba.tenica.backend.dto.resp.RespuestaGeneralDto;
import co.prueba.tenica.backend.entity.ProductoModel;
import co.prueba.tenica.backend.entity.ProductoSucursalModel;
import co.prueba.tenica.backend.repository.IProductoRepository;
import co.prueba.tenica.backend.repository.IProductoSucursalRepository;
import co.prueba.tenica.backend.utils.mapper.ProductoSucursalMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static co.prueba.tenica.backend.utils.Constantes.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AgregarProductoSucursalServiceTest {

    @Mock
    private IProductoRepository productoRepo;

    @Mock
    private IProductoSucursalRepository productoSucursalRepo;

    @Mock
    private ProductoSucursalMapper mapper;

    @InjectMocks
    private AgregarProductoSucursalService service;

    private InAgregarProductoSucursalDto dto;
    private ProductoModel producto;
    private AdjuntarSucursalesDto suc1;
    private AdjuntarSucursalesDto suc2;

    @BeforeEach
    void setUp() {
        dto = new InAgregarProductoSucursalDto();
        dto.setCodigoProducto("P001");
        suc1 = new AdjuntarSucursalesDto();
        suc1.setIdSucursal(10L);
        suc1.setStock(5L);
        suc2 = new AdjuntarSucursalesDto();
        suc2.setIdSucursal(20L);
        suc2.setStock(8L);
        dto.setSucursalesAdjuntar(List.of(suc1, suc2));

        producto = new ProductoModel();
        producto.setCodigo("P001");
    }

    @Test
    void agregarProductoSucursalWhenProductNotFoundReturnsNotFoundError() {
        when(productoRepo.findById(anyString())).thenReturn(Mono.empty());

        RespuestaGeneralDto<Void> result = service.agregarProductoSucursal(dto).block();

        assertNotNull(result);
        assertTrue(result.isError());
        assertEquals(MSG_PRODUCTO_NO_ENCONTRADO, result.getRespuesta());
    }

    @Test
    void agregarProductoSucursalWhenAllSucursalesValidSavesAndReturnsSuccess() {
        when(productoRepo.findById(anyString())).thenReturn(Mono.just(producto));
        when(productoSucursalRepo.existsBySucursalIdAndProductoId(anyLong(), anyString()))
                .thenReturn(Mono.just(false));
        // Mapper returns model
        when(mapper.toModel(any())).thenAnswer(inv -> {
            ProductoSucursalDto dtoArg = inv.getArgument(0);
            ProductoSucursalModel model = new ProductoSucursalModel();
            model.setProductoId(dtoArg.getProductoId());
            model.setSucursalId(dtoArg.getSucursalId());
            model.setStock(dtoArg.getStock());
            return model;
        });
        when(productoSucursalRepo.save(any(ProductoSucursalModel.class)))
                .thenAnswer(inv -> Mono.just((ProductoSucursalModel) inv.getArgument(0)));

        RespuestaGeneralDto<Void> result = service.agregarProductoSucursal(dto).block();

        assertNotNull(result);
        assertFalse(result.isError());
        assertEquals(MSG_TODAS_SUCURSALES_OK, result.getRespuesta());
    }
}
