package co.prueba.tenica.backend.repository;

import co.prueba.tenica.backend.entity.ProductoSucursalModel;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface IProductoSucursalRepository extends R2dbcRepository<ProductoSucursalModel, Long> {

    @Modifying
    @Query("DELETE FROM producto_sucursal WHERE producto_id = :codigoProducto and  sucursal_id = :idSucursal")
    Mono<Void> eliminarProductoSucursal(@Param("codigoProducto") String codigoProducto, @Param("idSucursal") Long idSucursal);
}
