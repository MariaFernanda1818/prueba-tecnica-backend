package co.prueba.tenica.backend.dto;

import lombok.Data;

import java.util.List;

@Data
public class InCrearProductoDto {

    private String nombre;

    private List<AdjuntarSucursalesDto> sucursalesAdjuntar;

}
