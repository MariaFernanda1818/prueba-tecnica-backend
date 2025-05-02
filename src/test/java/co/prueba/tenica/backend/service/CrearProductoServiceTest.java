package co.prueba.tenica.backend.service;

import co.prueba.tenica.backend.dto.AdjuntarSucursalesDto;
import co.prueba.tenica.backend.dto.ProductoDto;
import co.prueba.tenica.backend.dto.ProductoSucursalDto;
import co.prueba.tenica.backend.dto.in.InCrearProductoDto;
import co.prueba.tenica.backend.dto.resp.RespuestaGeneralDto;
import co.prueba.tenica.backend.repository.IProductoRepository;
import co.prueba.tenica.backend.repository.IProductoSucursalRepository;
import co.prueba.tenica.backend.service.impl.CrearProductoService;
import co.prueba.tenica.backend.utils.mapper.ProductoMapper;
import co.prueba.tenica.backend.utils.mapper.ProductoSucursalMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static co.prueba.tenica.backend.utils.Constantes.*;
import co.prueba.tenica.backend.dto.AdjuntarSucursalesDto;
import co.prueba.tenica.backend.dto.ProductoDto;
import co.prueba.tenica.backend.dto.ProductoSucursalDto;
import co.prueba.tenica.backend.dto.in.InCrearProductoDto;
import co.prueba.tenica.backend.dto.resp.RespuestaGeneralDto;
import co.prueba.tenica.backend.entity.ProductoModel;
import co.prueba.tenica.backend.entity.ProductoSucursalModel;
import co.prueba.tenica.backend.repository.IProductoRepository;
import co.prueba.tenica.backend.repository.IProductoSucursalRepository;
import co.prueba.tenica.backend.utils.mapper.ProductoMapper;
import co.prueba.tenica.backend.utils.mapper.ProductoSucursalMapper;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CrearProductoServiceTest {

    @Mock
    private IProductoRepository productoRepository;

    @Mock
    private IProductoSucursalRepository productoSucursalRepository;

    @Mock
    private ProductoMapper productoMapper;

    @Mock
    private ProductoSucursalMapper productoSucursalMapper;

    @InjectMocks
    private CrearProductoService service;

    private InCrearProductoDto inDto;
    private ProductoModel productoModel;

    @BeforeEach
    void setUp() {
        inDto = new InCrearProductoDto();
        inDto.setNombre("Producto X");
        // Caso sin sucursales
        inDto.setSucursalesAdjuntar(null);

        productoModel = new ProductoModel();
        productoModel.setCodigo("AAA52");
        productoModel.setNombre("Producto X");
    }

    @Test
    void crearProductoWithoutSucursalesReturnsSuccess() {
        // Mocks
        when(productoMapper.toModel(any(ProductoDto.class))).thenReturn(productoModel);
        when(productoRepository.save(any(ProductoModel.class))).thenReturn(Mono.just(productoModel));

        // Ejecutar
        Mono<RespuestaGeneralDto<Void>> result = service.crearProducto(inDto);

        // Verificar
        StepVerifier.create(result)
                .assertNext(resp -> {
                    assert !resp.isError();
                    assert resp.getRespuesta().equals(MSG_PRODUCTO_CREADO_EXITOSO);
                })
                .verifyComplete();
    }

    @Test
    void crearProductoWithSucursalesReturnsSuccess() {
        // Preparar sucursales
        AdjuntarSucursalesDto adj1 = new AdjuntarSucursalesDto();
        adj1.setIdSucursal(1L);
        adj1.setStock(5L);
        inDto.setSucursalesAdjuntar(List.of(adj1));

        ProductoSucursalModel psModel = new ProductoSucursalModel();
        psModel.setProductoId("AAA52");
        psModel.setSucursalId(1L);
        psModel.setStock(5L);

        when(productoMapper.toModel(any(ProductoDto.class))).thenReturn(productoModel);
        when(productoRepository.save(any(ProductoModel.class))).thenReturn(Mono.just(productoModel));
        when(productoSucursalMapper.toModel(any(ProductoSucursalDto.class))).thenReturn(psModel);
        when(productoSucursalRepository.save(any(ProductoSucursalModel.class))).thenReturn(Mono.just(psModel));

        Mono<RespuestaGeneralDto<Void>> result = service.crearProducto(inDto);

        StepVerifier.create(result)
                .assertNext(resp -> {
                    assert !resp.isError();
                    assert resp.getRespuesta().equals(MSG_PRODUCTO_CREADO_EXITOSO);
                })
                .verifyComplete();
    }

    @Test
    void crearProductoErrorOnSaveProductReturnsError() {
        when(productoMapper.toModel(any(ProductoDto.class))).thenReturn(productoModel);
        when(productoRepository.save(any(ProductoModel.class))).thenReturn(Mono.error(new RuntimeException("fail")));

        Mono<RespuestaGeneralDto<Void>> result = service.crearProducto(inDto);

        StepVerifier.create(result)
                .assertNext(resp -> {
                    assert resp.isError();
                    assert resp.getRespuesta().equals(MSG_PRODUCTO_CREACION_ERROR);
                })
                .verifyComplete();
    }
}