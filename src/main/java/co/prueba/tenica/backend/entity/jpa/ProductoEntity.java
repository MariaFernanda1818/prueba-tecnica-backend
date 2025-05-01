package co.prueba.tenica.backend.entity.jpa;

import jakarta.persistence.*;

@Entity
@Table(name = "producto")
public class ProductoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "stock", nullable = false)
    private Integer stock;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "sucursal_id", nullable = false, foreignKey = @ForeignKey(name = "fk_sucursal"))
    private SucursalEntity sucursal;

}
