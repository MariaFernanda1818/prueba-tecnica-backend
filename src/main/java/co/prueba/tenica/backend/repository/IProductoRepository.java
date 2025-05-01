package co.prueba.tenica.backend.repository;

import co.prueba.tenica.backend.entity.ProductoModel;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductoRepository extends ReactiveCrudRepository<ProductoModel, Long> {
}
