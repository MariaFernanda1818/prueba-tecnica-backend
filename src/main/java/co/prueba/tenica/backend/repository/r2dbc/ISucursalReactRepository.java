package co.prueba.tenica.backend.repository.r2dbc;

import co.prueba.tenica.backend.entity.r2dbc.SucursalModel;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISucursalReactRepository extends ReactiveCrudRepository<SucursalModel, Long> {
}
