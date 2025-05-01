package co.prueba.tenica.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class InCrearFranquiciaDto {

    @NotBlank(message = "El nombre de la franquicia no puede estar vacío")
    @Size(max = 100, message = "El nombre de la franquicia no puede superar los 100 caracteres")
    private String nombreFranquicia;

}
