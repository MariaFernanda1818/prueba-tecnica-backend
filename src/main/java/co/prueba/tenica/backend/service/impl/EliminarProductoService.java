package co.prueba.tenica.backend.service.impl;

import co.prueba.tenica.backend.dto.RespuestaGeneralDto;
import co.prueba.tenica.backend.repository.IProductoRepository;
import co.prueba.tenica.backend.repository.IProductoSucursalRepository;
import co.prueba.tenica.backend.service.IEliminarProductoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class EliminarProductoService implements IEliminarProductoService {

    private final IProductoSucursalRepository iProductoSucursalRepository;

    @Override
    public Mono<RespuestaGeneralDto<Void>> eliminarProductoSucursal(String codigoProducto, Long idSucursal) {
        return iProductoSucursalRepository
                .eliminarProductoSucursal(codigoProducto, idSucursal).map(delete -> {
                    RespuestaGeneralDto<Void> resp = new RespuestaGeneralDto<>();
                    resp.setRespuesta("Se creó elimino correctamente el producto de la sucursal");
                    return resp;
                }).onErrorResume(ex -> {
                    log.error("Error en eliminar el producto", ex);
                    RespuestaGeneralDto<Void> resp = new RespuestaGeneralDto<>();
                    resp.setError(true);
                    resp.setRespuesta("Hubo un error en eliminar el producto");
                    return Mono.just(resp);
                });
    }

}
