package co.prueba.tenica.backend.repository;

import co.prueba.tenica.backend.entity.ProductoModel;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

/**
 * Repositorio reactivo para gestionar operaciones CRUD y consultas personalizadas
 * de la entidad ProductoModel en la base de datos.
 *<p>
 * Métodos principales heredados de ReactiveCrudRepository:
 * <ul>
 *   <li>save(ProductoModel entity): guarda o actualiza un producto.</li>
 *   <li>findById(String id): busca un producto por su código.</li>
 *   <li>findAll(): obtiene todos los productos.</li>
 *   <li>deleteById(String id): elimina un producto por su código.</li>
 * </ul>
 * Además, define operaciones reactivas específicas:
 * <ul>
 *   <li>eliminarProducto: elimina un producto usando SQL nativo.</li>
 *   <li>actualizarNombreProducto: actualiza el nombre de un producto usando SQL nativo.</li>
 * </ul>
 */
@Repository
public interface IProductoRepository extends ReactiveCrudRepository<ProductoModel, String> {

    /**
     * Elimina un producto de la tabla "producto" por su código.
     *
     * @param codigoProducto Código único del producto a eliminar.
     * @return Mono vacío que completa cuando la eliminación finaliza.
     */
    @Modifying
    @Query("DELETE FROM producto WHERE codigo = :codigoProducto")
    Mono<Void> eliminarProducto(@Param("codigoProducto") String codigoProducto);

    /**
     * Actualiza el nombre de un producto en la tabla "producto".
     *
     * @param codigoProducto Código único del producto a modificar.
     * @param nuevoNombre     Nuevo nombre que se asignará al producto.
     * @return Mono vacío que completa cuando la actualización finaliza.
     */
    @Modifying
    @Query("UPDATE producto SET nombre = :nuevoNombre WHERE codigo = :codigoProducto")
    Mono<Void> actualizarNombreProducto(
            @Param("codigoProducto") String codigoProducto,
            @Param("nuevoNombre") String nuevoNombre
    );
}
