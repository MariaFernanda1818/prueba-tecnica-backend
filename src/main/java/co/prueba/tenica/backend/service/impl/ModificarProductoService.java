package co.prueba.tenica.backend.service.impl;

import co.prueba.tenica.backend.dto.*;
import co.prueba.tenica.backend.repository.IProductoRepository;
import co.prueba.tenica.backend.service.IModificarProductoService;
import co.prueba.tenica.backend.utils.mapper.ProductoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class ModificarProductoService implements IModificarProductoService {

    private final IProductoRepository iProductoRepository;

    private final ProductoMapper productoMapper;

    @Override
    public Mono<RespuestaGeneralDto<Void>> modificarNombreProducto(InModificarNombreDto inModificarNombreDto) {
        return iProductoRepository.save(productoMapper.toModel(ProductoDto.builder()
                .nombre(inModificarNombreDto.getNombre())
                .build()))
                .map(saved -> {
                    RespuestaGeneralDto<Void> respuesta = new RespuestaGeneralDto<>();
                    respuesta.setRespuesta("Se modifico correctamente el nombre del producto " + inModificarNombreDto.getId());
                    return respuesta;
                }).onErrorResume(ex -> {
                    log.error(ex.getMessage());
                    RespuestaGeneralDto<Void> respuesta = new RespuestaGeneralDto<>();
                    respuesta.setRespuesta("Hubo un error en modificar el nombre del producto " + inModificarNombreDto.getId());
                    respuesta.setError(true);
                    return Mono.just(respuesta);
                });
    }
}
