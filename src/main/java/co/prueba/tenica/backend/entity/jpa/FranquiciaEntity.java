package co.prueba.tenica.backend.entity.jpa;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "franquicia")
@Data
public class FranquiciaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 100, unique = true)
    private String nombre;


}
