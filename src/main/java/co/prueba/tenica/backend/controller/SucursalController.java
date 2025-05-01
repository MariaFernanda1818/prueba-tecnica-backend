package co.prueba.tenica.backend.controller;

import co.prueba.tenica.backend.dto.FranquiciaDto;
import co.prueba.tenica.backend.dto.InModificarNombreDto;
import co.prueba.tenica.backend.dto.ProductoDto;
import co.prueba.tenica.backend.dto.SucursalDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/sucursal")
@RequiredArgsConstructor
public class SucursalController {

    @PostMapping("/crear")
    public ResponseEntity<String> crearSucursal(@RequestBody SucursalDto sucursalDto){
        return ResponseEntity.ok("Se creo correctamente");
    }

    @PutMapping("/modificar-nombre")
    public ResponseEntity<String> modificarNombre(@RequestBody InModificarNombreDto inModificarNombreDto){
        return ResponseEntity.ok("Se modifico correctamente el nombre");
    }

}
