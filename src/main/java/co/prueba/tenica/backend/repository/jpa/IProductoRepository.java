package co.prueba.tenica.backend.repository.jpa;

import co.prueba.tenica.backend.entity.jpa.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductoRepository extends JpaRepository<ProductoEntity, Long> {
}
