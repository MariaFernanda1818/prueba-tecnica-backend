package co.prueba.tenica.backend.dto.in;

import co.prueba.tenica.backend.dto.AdjuntarSucursalesDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

import static co.prueba.tenica.backend.utils.Constantes.*;

/**
 * DTO para asociar un producto con una o varias sucursales,
 * indicando el stock a asignar en cada una.
 */
@Data
public class InAgregarProductoSucursalDto {

    /**
     * Código único del producto a asociar.
     */
    @NotBlank(message = MSG_CODIGO_PRODUCTO_OBLIGATORIO)
    @Size(max = 10, message = MSG_CODIGO_PRODUCTO_MAX)
    private String codigoProducto;

    /**
     * Lista de sucursales (con su ID y stock) a las que se asignará el producto.
     */
    @NotNull(message = MSG_SUCURSALES_OBLIGATORIO)
    @Size(min = 1, message = MSG_SUCURSALES_NO_VACIO)
    private List<@Valid AdjuntarSucursalesDto> sucursalesAdjuntar;

}
