package co.prueba.tenica.backend.controller;

import co.prueba.tenica.backend.dto.AdjuntarSucursalesDto;
import co.prueba.tenica.backend.dto.in.*;
import co.prueba.tenica.backend.dto.resp.RespProductoMaxStockDto;
import co.prueba.tenica.backend.dto.resp.RespuestaGeneralDto;
import co.prueba.tenica.backend.service.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static co.prueba.tenica.backend.utils.Constantes.*;

/**
 * Controlador REST para gestionar entidades Producto.
 * Proporciona endpoints para crear, modificar, eliminar
 * y consultar stock máximo de productos en sucursales.
 */
@RestController
@RequestMapping(PRODUCTO_BASE_PATH)
@RequiredArgsConstructor
@Validated
public class ProductoController {

    private final IModificarProductoService modificarProductoService;
    private final IEliminarProductoService eliminarProductoService;
    private final ICrearProductoService crearProductoService;
    private final IConsultarMaxStockProductoService consultarMaxStockService;
    private final IAgregarProductoSucursalService agregarProductoSucursalService;

    /**
     * Modifica el nombre de un producto existente.
     *
     * @param dto DTO con el código del producto y el nuevo nombre.
     * @return Mono con ResponseEntity describiendo el resultado.
     */
    @PutMapping(MODIFY_NAME_PATH)
    public Mono<ResponseEntity<RespuestaGeneralDto<Void>>> modificarNombreProducto(
            @Valid @RequestBody InModificarNombreProductoDto dto) {
        return modificarProductoService.modificarNombreProducto(dto)
                .map(res -> res.isError()
                        ? ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res)
                        : ResponseEntity.ok(res)
                );
    }

    /**
     * Elimina un producto globalmente o de una sucursal.
     *
     * @param codigoProducto Código del producto a eliminar.
     * @param idSucursal     Id de sucursal (opcional). Si se omite, se elimina globalmente.
     * @return Mono con ResponseEntity describiendo el resultado.
     */
    @DeleteMapping(DELETE_PATH)
    public Mono<ResponseEntity<RespuestaGeneralDto<Void>>> eliminarProductoSucursal(
            @RequestParam
            @NotBlank(message = MSG_CODIGO_OBLIGATORIO)
            @Size(max = 10, message = MSG_CODIGO_MAX_10)
            String codigoProducto,

            @RequestParam(required = false)
            Long idSucursal
    ) {
        return eliminarProductoService.eliminarProductoSucursal(codigoProducto, idSucursal)
                .map(res -> res.isError()
                        ? ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res)
                        : ResponseEntity.ok(res)
                );
    }

    /**
     * Crea un nuevo producto.
     *
     * @param dto DTO con los datos del producto a crear y sucursales a adjuntar.
     * @return Mono con ResponseEntity describiendo el resultado.
     */
    @PostMapping(CREATE_PATH)
    public Mono<ResponseEntity<RespuestaGeneralDto<Void>>> crearProducto(
            @Valid @RequestBody InCrearProductoDto dto) {
        return crearProductoService.crearProducto(dto)
                .map(res -> res.isError()
                        ? ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res)
                        : ResponseEntity.ok(res)
                );
    }

    /**
     * Modifica el stock de un producto en una sucursal.
     *
     * @param dto DTO con el código del producto, id de sucursal y nuevo stock.
     * @return Mono con ResponseEntity describiendo el resultado.
     */
    @PutMapping(MODIFY_STOCK_PATH)
    public Mono<ResponseEntity<RespuestaGeneralDto<Void>>> modificarStockProducto(
            @Valid @RequestBody InModificarProductoStockDto dto) {
        return modificarProductoService.modificarStockProducto(dto)
                .map(res -> res.isError()
                        ? ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res)
                        : ResponseEntity.ok(res)
                );
    }

    /**
     * Obtiene el producto con stock máximo para cada sucursal.
     *
     * @return Mono con ResponseEntity que envuelve un Flux de DTOs con el stock máximo.
     */
    @GetMapping(MAX_STOCK_PATH)
    public Mono<ResponseEntity<RespuestaGeneralDto<List<RespProductoMaxStockDto>>>> productoStockMax() {
        return consultarMaxStockService.productosMaxStockSucursal()
                .map(res -> res.isError()
                        ? ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res)
                        : ResponseEntity.ok(res)
                );
    }

    /**
     * Asocia un producto a una o varias sucursales con la cantidad de stock indicada.
     * <p>
     * Valida que el producto exista; si no existe, devuelve un error.
     * Valida que la asociación producto-sucursal no exista previamente; si existe, devuelve un error.
     * En caso contrario, guarda la relación y su stock en la base de datos.
     *
     * @param agregarProductoSucursalDto DTO que contiene el código de producto y la lista de
     *                                  objetos {@link AdjuntarSucursalesDto} con id de sucursal
     * @return un {@link Mono} que emite un {@link ResponseEntity} con un
     *         {@link RespuestaGeneralDto}{@code <Void>}:
     *         <ul>
     *           <li>error = true y mensaje de error si el producto no existe o la combinación ya existe</li>
     *           <li>error = false y mensaje de éxito si todas las asociaciones se guardaron correctamente</li>
     *         </ul>
     */
    @PutMapping(AGREGAR_PRODUCTO_SUCURSAL_PATH)
    public Mono<ResponseEntity<RespuestaGeneralDto<Void>>> agregarProductoSucursal(@Valid @RequestBody InAgregarProductoSucursalDto agregarProductoSucursalDto) {
        return agregarProductoSucursalService.agregarProductoSucursal(agregarProductoSucursalDto)
                .map(res -> res.isError()
                        ? ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res)
                        : ResponseEntity.ok(res)
                );
    }
}

