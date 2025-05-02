package co.prueba.tenica.backend.repository;

import co.prueba.tenica.backend.entity.SucursalModel;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

/**
 * Repositorio reactivo para gestionar operaciones CRUD y consultas específicas
 * de la entidad SucursalModel en la base de datos.
 * Extiende ReactiveCrudRepository para proporcionar métodos reativos básicos
 * como save, findById, findAll y deleteById.
 */
@Repository
public interface ISucursalRepository extends ReactiveCrudRepository<SucursalModel, Long> {

    /**
     * Actualiza el nombre de una sucursal.
     *
     * @param nuevoNombre Nuevo nombre que se asignará a la sucursal.
     * @param idSucursal  Identificador de la sucursal a modificar.
     * @return Mono vacío que completa cuando la actualización finaliza.
     */
    @Modifying
    @Query("UPDATE sucursal SET nombre = :nuevoNombre WHERE id = :idSucursal")
    Mono<Void> actualizarNombreSucursal(
            @Param("nuevoNombre") String nuevoNombre,
            @Param("idSucursal") Long idSucursal
    );
}
