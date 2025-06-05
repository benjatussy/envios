package com.api_envios.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Data
@Entity
@Table(name = "envios")
@NoArgsConstructor
@AllArgsConstructor
public class Envios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_envio")
    private Integer idEnvio;

    @Column(name = "id_venta")
    private Integer idVenta;

    private String Direccion_Envio;
    private String Estado_Envio;
    private Date Fecha_Envio;
    private Date Fecha_Entrega;

}
