package com.api_envios.controller;

import com.api_envios.dto.EnviosDTO;
import com.api_envios.service.EnvioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/envios")
public class EnvioController {

    @Autowired
    private EnvioService envioService;

 
    @PostMapping
    public ResponseEntity<EnviosDTO> crear(@RequestBody EnviosDTO dto) {
        return ResponseEntity.ok(envioService.crear(dto));
    }


    @GetMapping
    public ResponseEntity<List<EnviosDTO>> listar() {
        return ResponseEntity.ok(envioService.listar());
    }

  
    @GetMapping("/{id}")
    public ResponseEntity<EnviosDTO> obtener(@PathVariable Integer id) {
        return ResponseEntity.ok(envioService.obtenerPorId(id));
    }


    @PutMapping("/{id}")
    public ResponseEntity<EnviosDTO> actualizar(@PathVariable Integer id, @RequestBody EnviosDTO dto) {
        return ResponseEntity.ok(envioService.actualizar(id, dto));
    }


    @PutMapping("/{id}/estado")
    public ResponseEntity<EnviosDTO> actualizarEstado(@PathVariable Integer id, @RequestBody String nuevoEstado) {
        if (nuevoEstado != null && nuevoEstado.startsWith("\"") && nuevoEstado.endsWith("\"")) {
            nuevoEstado = nuevoEstado.substring(1, nuevoEstado.length() - 1);
        }
        EnviosDTO actualizado = envioService.actualizarEstado(id, nuevoEstado);
        return ResponseEntity.ok(actualizado);
    }
}

