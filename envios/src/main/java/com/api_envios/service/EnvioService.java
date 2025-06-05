package com.api_envios.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api_envios.dto.EnviosDTO;
import com.api_envios.model.Envios;
import com.api_envios.repository.EnvioRepository;

@Service
public class EnvioService {

    @Autowired
    private EnvioRepository envioRepository;

    private EnviosDTO toDTO(Envios envio){
        return new EnviosDTO(
            envio.getIdEnvio(),
            envio.getIdVenta(),
            envio.getDireccion_Envio(),
            envio.getEstado_Envio(),
            envio.getFecha_Envio(),
            envio.getFecha_Entrega()
        );
    }

    private Envios toEntity(EnviosDTO dto) {
        Envios envio = new Envios();
        envio.setIdEnvio(dto.getIdEnvio());
        envio.setIdVenta(dto.getIdVenta());
        envio.setDireccion_Envio(dto.getDireccionEnvio());
        envio.setEstado_Envio(dto.getEstadoEnvio());
        envio.setFecha_Envio(dto.getFechaEnvio());
        envio.setFecha_Entrega(dto.getFechaEntrega());
        return envio;
    }

    public EnviosDTO crear(EnviosDTO dto) {
        Envios envio = toEntity(dto);
        return toDTO(envioRepository.save(envio));
    }

    public List<EnviosDTO> listar() {
        return envioRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public EnviosDTO obtenerPorId(Integer id) {
        Envios envio = envioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Envío no encontrado"));
        return toDTO(envio);
    }

    public EnviosDTO actualizar(Integer id, EnviosDTO dto) {
        Envios existente = envioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Envío no encontrado"));

        existente.setIdVenta(dto.getIdVenta());
        existente.setDireccion_Envio(dto.getDireccionEnvio());
        existente.setEstado_Envio(dto.getEstadoEnvio());
        existente.setFecha_Envio(dto.getFechaEnvio());
        existente.setFecha_Entrega(dto.getFechaEntrega());

        return toDTO(envioRepository.save(existente));
    }

    public void eliminar(Integer id) {
        envioRepository.deleteById(id);
    }
}