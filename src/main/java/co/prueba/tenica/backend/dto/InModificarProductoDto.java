package co.prueba.tenica.backend.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class InModificarProductoDto {

    @NotNull(message = "El ID del producto no puede ser nulo")
    private Long id;

    private String nombre;

    @Min(value = 0, message = "El stock no puede ser negativo")
    private Integer stock;

    private Long idSucursal;
}
