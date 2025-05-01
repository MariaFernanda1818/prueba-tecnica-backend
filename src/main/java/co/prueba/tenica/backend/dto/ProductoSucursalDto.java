package co.prueba.tenica.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductoSucursalDto {

    private String productoId;

    private Long sucursalId;

    private Long stock;
}
