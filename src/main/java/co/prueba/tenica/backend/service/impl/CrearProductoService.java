package co.prueba.tenica.backend.service.impl;

import co.prueba.tenica.backend.dto.*;
import co.prueba.tenica.backend.entity.ProductoModel;
import co.prueba.tenica.backend.repository.IProductoRepository;
import co.prueba.tenica.backend.repository.IProductoSucursalRepository;
import co.prueba.tenica.backend.service.ICrearProductoService;
import co.prueba.tenica.backend.utils.mapper.ProductoMapper;
import co.prueba.tenica.backend.utils.mapper.ProductoSucursalMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CrearProductoService implements ICrearProductoService {

    private final IProductoRepository iProductoRepository;

    private final IProductoSucursalRepository iProductoSucursalRepository;

    private final ProductoSucursalMapper productoSucursalMapper;

    private final ProductoMapper productoMapper;

    @Override
    public Mono<RespuestaGeneralDto<Void>> crearProducto(InCrearProductoDto inCrearProductoDto) {
        ProductoDto productoDto =  ProductoDto.builder().nombre(inCrearProductoDto.getNombre()).build();
        return iProductoRepository.save(productoMapper.toModel(productoDto))
                .flatMap(savedProducto -> {
                    List<AdjuntarSucursalesDto> sucursales = inCrearProductoDto.getSucursalesAdjuntar();
                    if (sucursales == null || sucursales.isEmpty()) {
                        return Mono.just(savedProducto);
                    }
                    return Flux.fromIterable(sucursales)
                            .flatMap(adj -> {
                                ProductoSucursalDto productoSucursalDto = ProductoSucursalDto.builder()
                                        .productoId(savedProducto.getCodigo())
                                        .sucursalId(adj.getIdSucursal())
                                        .stock(adj.getStock())
                                        .build();
                                return iProductoSucursalRepository.save(productoSucursalMapper.toModel(productoSucursalDto));
                            })
                            .then(Mono.just(savedProducto));
                })
                .map(p -> {
                    var resp = new RespuestaGeneralDto<Void>();
                    resp.setRespuesta("Se creó correctamente el producto");
                    return resp;
                })
                .doOnError(ex -> log.error("Error creando producto completo", ex))
                .onErrorResume(ex -> {
                    var resp = new RespuestaGeneralDto<Void>();
                    resp.setError(true);
                    resp.setRespuesta("Hubo un error al crear el producto");
                    return Mono.just(resp);
                });
    }

}
