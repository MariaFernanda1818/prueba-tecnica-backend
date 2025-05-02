package co.prueba.tenica.backend.service.impl;

import co.prueba.tenica.backend.dto.resp.RespProductoMaxStockDto;
import co.prueba.tenica.backend.dto.resp.RespuestaGeneralDto;
import co.prueba.tenica.backend.entity.ProductoModel;
import co.prueba.tenica.backend.entity.ProductoSucursalModel;
import co.prueba.tenica.backend.entity.SucursalModel;
import co.prueba.tenica.backend.repository.IProductoRepository;
import co.prueba.tenica.backend.repository.IProductoSucursalRepository;
import co.prueba.tenica.backend.repository.ISucursalRepository;
import co.prueba.tenica.backend.service.IConsultarMaxStockProductoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuples;

import java.util.Comparator;

import static co.prueba.tenica.backend.utils.Constantes.MSG_CONSULTA_MAX_STOCK_EXITOSA;


/**
 * Servicio que implementa la lógica para consultar el producto con mayor stock en cada sucursal.
 * Recopila todas las sucursales y, para cada una, determina el producto
 * que posee la mayor cantidad de stock disponible.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ConsultarMaxStockProductoService implements IConsultarMaxStockProductoService {


    private final ISucursalRepository sucursalRepository;
    private final IProductoSucursalRepository productoSucursalRepository;
    private final IProductoRepository productoRepository;

    /**
     * Obtiene para cada sucursal el producto con la cantidad de stock más alta.
     *
     * @return Mono que envuelve un RespuestaGeneralDto con un Flux de
     *         RespProductoMaxStockDto, donde cada elemento contiene la sucursal,
     *         el producto de mayor stock y la cantidad correspondiente.
     */
    @Override
    public Mono<RespuestaGeneralDto<Flux<RespProductoMaxStockDto>>> productosMaxStockSucursal() {
        // Creamos el DTO principal y asignamos el mensaje de éxito
        RespuestaGeneralDto<Flux<RespProductoMaxStockDto>> respuestaGeneral = new RespuestaGeneralDto<>();
        respuestaGeneral.setRespuesta(MSG_CONSULTA_MAX_STOCK_EXITOSA);

        // Flujo reactivo que calcula el producto con máximo stock por sucursal
        Flux<RespProductoMaxStockDto> productosMaxSucursal =
                sucursalRepository.findAll()
                        .flatMap(sucursal ->
                                productoSucursalRepository.findAllBySucursalId(sucursal.getId())
                                        .flatMap(ps -> productoRepository.findById(ps.getProductoId())
                                                .map(prod -> Tuples.of(ps, prod))
                                        )
                                        .collectList()
                                        .map(listaTuplas -> {
                                            RespProductoMaxStockDto dto = new RespProductoMaxStockDto();
                                            dto.setNombreSucursal(sucursal.getNombre());
                                            listaTuplas.stream()
                                                    .max(Comparator.comparing(tuple -> tuple.getT1().getStock()))
                                                    .ifPresent(max -> {
                                                        dto.setStock(max.getT1().getStock());
                                                        dto.setNombreProducto(max.getT2().getNombre());
                                                    });
                                            return dto;
                                        })
                        );

        respuestaGeneral.setData(productosMaxSucursal);
        return Mono.just(respuestaGeneral);
    }
}
