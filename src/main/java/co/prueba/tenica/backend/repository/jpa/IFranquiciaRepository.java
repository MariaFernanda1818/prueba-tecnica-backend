package co.prueba.tenica.backend.repository.jpa;

import co.prueba.tenica.backend.entity.jpa.FranquiciaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFranquiciaRepository extends JpaRepository<FranquiciaEntity, Long> {
}
