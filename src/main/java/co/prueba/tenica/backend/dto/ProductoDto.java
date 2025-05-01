package co.prueba.tenica.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoDto {

    private Long id;
    private String nombre;
    private Integer stock;
    private SucursalDto sucursal;

}
