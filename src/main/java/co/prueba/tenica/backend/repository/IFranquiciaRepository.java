package co.prueba.tenica.backend.repository;

import co.prueba.tenica.backend.entity.FranquiciaModel;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio Reactivo para gestionar operaciones CRUD de FranquiciaModel.
 * Este repositorio expone métodos reactivoss como save, findById, findAll y deleteById
 * sobre la tabla "franquicia".
 */
@Repository
public interface IFranquiciaRepository extends ReactiveCrudRepository<FranquiciaModel, Long> {

}
