package co.prueba.tenica.backend.dto.in;

import co.prueba.tenica.backend.dto.AdjuntarSucursalesDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

import static co.prueba.tenica.backend.utils.Constantes.*;

/**
 * DTO para la creación de un nuevo producto.
 * Contiene el nombre del producto y las sucursales
 * a las que se debe adjuntar con su stock correspondiente.
 */
@Data
public class InCrearProductoDto {

    /**
     * Nombre del producto.
     * - Obligatorio.
     * - Longitud máxima: 100 caracteres.
     */
    @NotBlank(message = MSG_NOMBRE_PRODUCTO_OBLIGATORIO)
    @Size(max = 100, message = MSG_NOMBRE_PRODUCTO_MAX_100)
    private String nombre;

    /**
     * Lista de sucursales donde se adjuntará el producto,
     * con la cantidad de stock que se desea asignar.
     */
    private List<AdjuntarSucursalesDto> sucursalesAdjuntar;
}
