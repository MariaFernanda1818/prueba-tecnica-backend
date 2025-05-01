package co.prueba.tenica.backend.repository.jpa;

import co.prueba.tenica.backend.entity.jpa.SucursalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISucursalRepository extends JpaRepository<SucursalEntity, Long> {
}
