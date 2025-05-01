package co.prueba.tenica.backend.controller;

import co.prueba.tenica.backend.dto.*;
import co.prueba.tenica.backend.service.ICrearProductoService;
import co.prueba.tenica.backend.service.IEliminarProductoService;
import co.prueba.tenica.backend.service.IModificarProductoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/producto")
@RequiredArgsConstructor
public class ProductoController {

    private final IModificarProductoService iModificarProductoService;
    private final IEliminarProductoService iEliminarProductoService;
    private final ICrearProductoService iCrearProductoService;

    @PutMapping("/modificar-nombre")
    public Mono<ResponseEntity<RespuestaGeneralDto<Void>>> modificarNombreProducto(@Valid @RequestBody InModificarNombreDto inModificar){
        return iModificarProductoService.modificarNombreProducto(inModificar).map(respuesta -> {
            if(respuesta.isError()){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
            }else{
                return ResponseEntity.ok(respuesta);
            }
        });
    }

    @DeleteMapping("/eliminar")
    public Mono<ResponseEntity<RespuestaGeneralDto<Void>>> eliminarProductoSucursal(@RequestParam String codigoProducto, @RequestParam Long idSucursal){
        return iEliminarProductoService.eliminarProductoSucursal(codigoProducto, idSucursal).map(respuesta -> {
            if(respuesta.isError()){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
            }else{
                return ResponseEntity.ok(respuesta);
            }
        });
    }

    @PostMapping("/crear")
    public Mono<ResponseEntity<RespuestaGeneralDto<Void>>>  crearProducto(@RequestBody InCrearProductoDto inCrearProductoDto){
        return iCrearProductoService.crearProducto(inCrearProductoDto).map(respuesta -> {
            if(respuesta.isError()){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
            }else{
                return ResponseEntity.ok(respuesta);
            }
        });
    }

    @PutMapping("/modificar-stock")
    public Mono<ResponseEntity<String>> modificarStockProducto(@RequestParam Long idProducto, @RequestParam Integer nuevoStock){
        return Mono.just(ResponseEntity.ok("Se modifico correctamente"));
    }

    @GetMapping("productos-stock-max")
    public Flux<ResponseEntity<String>> productoStockMax(){
        return Flux.just(ResponseEntity.ok("Se consulto correctamente el producto"));
    }



}
