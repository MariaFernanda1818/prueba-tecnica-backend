package co.prueba.tenica.backend.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class InModificarNombreDto {

    @NotNull(message = "Debe tener obligatoriamente un id")
    private Long id;

    @NotEmpty(message = "Debe tener algun contenido el nombre")
    @Size(max = 100, message = "El nombre de la franquicia no puede superar los 100 caracteres")
    private String nombre;

}
