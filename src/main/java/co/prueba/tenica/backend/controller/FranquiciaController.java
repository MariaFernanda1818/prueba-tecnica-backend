package co.prueba.tenica.backend.controller;

import co.prueba.tenica.backend.dto.FranquiciaDto;
import co.prueba.tenica.backend.dto.InCrearFranquiciaDto;
import co.prueba.tenica.backend.dto.InModificarNombreDto;
import co.prueba.tenica.backend.dto.RespuestaGeneralDto;
import co.prueba.tenica.backend.service.ICrearFranquiciaService;
import co.prueba.tenica.backend.service.IModificarFranquiciaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/franquicia")
@RequiredArgsConstructor
public class FranquiciaController {

    private final ICrearFranquiciaService iCrearFranquiciaService;

    private final IModificarFranquiciaService iModificarFranquiciaService;

    @PostMapping("/crear")
    public Mono<ResponseEntity<RespuestaGeneralDto<Void>>> crearFranquicia(@Valid @RequestBody InCrearFranquiciaDto franquiciaDto){
        return iCrearFranquiciaService.crearFranquicia(franquiciaDto).map(respuesta -> {
           if(respuesta.isError()){
               return ResponseEntity.badRequest().body(respuesta);
           }else{
               return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
           }
        });
    }

    @PutMapping("/modificar")
    public Mono<ResponseEntity<RespuestaGeneralDto<Void>>> modificarNombre(@Valid @RequestBody InModificarNombreDto inModificarNombreDto) {
        return iModificarFranquiciaService.modificarFranquiciaReact(inModificarNombreDto).map(respuesta -> {
            if(respuesta.isError()){
                return ResponseEntity.badRequest().body(respuesta);
            }else{
                return ResponseEntity.ok(respuesta);
            }
        });
    }


}
