package com.api_envios.controller;

import com.api_envios.dto.EnviosDTO;
import com.api_envios.service.EnvioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.Link;

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

        @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        envioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }




    @GetMapping("/hateoas/{id}")
    public EnviosDTO obtenerHATEOAS(@PathVariable Integer id) {
        EnviosDTO dto = envioService.obtenerPorId(id);
        if (dto == null) {
            return null;
        }

        dto.add(linkTo(methodOn(EnvioController.class).obtenerHATEOAS(id)).withSelfRel());
        dto.add(linkTo(methodOn(EnvioController.class).obtenerTodosHATEOAS()).withRel("todos"));
        dto.add(linkTo(methodOn(EnvioController.class).eliminar(id)).withRel("eliminar"));

        
        dto.add(Link.of("http://localhost:8091/api/proxy/envios/" + dto.getId()).withSelfRel());
        dto.add(Link.of("http://localhost:8091/api/proxy/envios/" + dto.getId()).withRel("Modificar HATEOAS").withType("PUT"));
        dto.add(Link.of("http://localhost:8091/api/proxy/envios/" + dto.getId()).withRel("Eliminar HATEOAS").withType("DELETE"));

        return dto;
    }

    @GetMapping("/hateoas")
    public List<EnviosDTO> obtenerTodosHATEOAS() {
        List<EnviosDTO> lista = envioService.listar();

        for (EnviosDTO dto : lista) {
         
            dto.add(linkTo(methodOn(EnvioController.class).obtenerHATEOAS(dto.getId())).withSelfRel());

          
            dto.add(Link.of("http://localhost:8091/api/proxy/envios").withRel("Get todos HATEOAS"));
            dto.add(Link.of("http://localhost:8091/api/proxy/envios/" + dto.getId()).withRel("Crear HATEOAS").withType("POST"));
        }

        return lista;
    }



}

