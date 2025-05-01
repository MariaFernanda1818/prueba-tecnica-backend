package co.prueba.tenica.backend.entity.jpa;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "sucursal")
@Data
public class SucursalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "franquicia_id", nullable = false, foreignKey = @ForeignKey(name = "fk_franquicia"))
    private FranquiciaEntity franquicia;

}
