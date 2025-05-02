package co.prueba.tenica.backend.repository;

import co.prueba.tenica.backend.entity.ProductoSucursalModel;
import org.reactivestreams.Publisher;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Repositorio reactivo para gestionar la relación Producto-Sucursal.
 * Permite realizar operaciones CRUD y consultas específicas sobre la tabla
 * "producto_sucursal" que asocia productos con sucursales y su stock.
 */
@Repository
public interface IProductoSucursalRepository extends R2dbcRepository<ProductoSucursalModel, Long> {

    /**
     * Elimina la asociación de un producto en una sucursal.
     *
     * @param codigoProducto Código único del producto.
     * @param idSucursal     Identificador de la sucursal.
     * @return Mono que completa cuando la operación finaliza.
     */
    @Modifying
    @Query("DELETE FROM producto_sucursal WHERE producto_id = :codigoProducto and sucursal_id = :idSucursal")
    Mono<Void> eliminarProductoSucursal(
            @Param("codigoProducto") String codigoProducto,
            @Param("idSucursal") Long idSucursal
    );

    /**
     * Actualiza la cantidad de stock de un producto en una sucursal.
     *
     * @param codigoProducto Código único del producto.
     * @param idSucursal     Identificador de la sucursal.
     * @param nuevoStock     Nuevo valor de stock a asignar.
     * @return Mono que completa cuando la operación finaliza.
     */
    @Modifying
    @Query("UPDATE producto_sucursal SET stock = :nuevoStock WHERE producto_id = :codigoProducto and sucursal_id = :idSucursal")
    Mono<Void> actualizarStockProductoSucursal(
            @Param("codigoProducto") String codigoProducto,
            @Param("idSucursal") Long idSucursal,
            @Param("nuevoStock") Long nuevoStock
    );

    /**
     * Obtiene todas las asociaciones producto-sucursal para una sucursal dada.
     *
     * @param idSucursal Identificador de la sucursal.
     * @return Flux con todos los registros de ProductoSucursalModel para la sucursal.
     */
    Flux<ProductoSucursalModel> findAllBySucursalId(Long idSucursal);

    /**
     * Verifica si ya existe una asociación entre un producto y una sucursal.
     *
     * @param idSucursal     Identificador de la sucursal.
     * @param codigoProducto Código único del producto.
     * @return Mono que emite true si existe al menos una asociación, o false en caso contrario.
     */
    Mono<Boolean> existsBySucursalIdAndProductoId(Long idSucursal, String codigoProducto);
}