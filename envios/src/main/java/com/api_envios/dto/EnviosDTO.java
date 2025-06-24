package com.api_envios.dto;

import org.springframework.hateoas.RepresentationModel;
import lombok.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnviosDTO extends RepresentationModel<EnviosDTO> {

    private Integer id;//id envio
    private Integer idVenta;
    private String direccionEnvio;
    private String estadoEnvio;
    private Date fechaEnvio;
    private Date fechaEntrega;

}
