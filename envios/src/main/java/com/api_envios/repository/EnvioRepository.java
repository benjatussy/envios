package com.api_envios.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.api_envios.model.Envios;

@Repository
public interface EnvioRepository extends JpaRepository<Envios, Integer> {

}
