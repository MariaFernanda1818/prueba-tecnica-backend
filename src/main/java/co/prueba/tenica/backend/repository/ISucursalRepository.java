package co.prueba.tenica.backend.repository;

import co.prueba.tenica.backend.entity.SucursalModel;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISucursalRepository extends ReactiveCrudRepository<SucursalModel, Long> {
}
