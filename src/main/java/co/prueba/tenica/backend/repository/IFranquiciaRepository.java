package co.prueba.tenica.backend.repository;

import co.prueba.tenica.backend.entity.FranquiciaModel;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFranquiciaRepository extends ReactiveCrudRepository<FranquiciaModel, Long> {
}
