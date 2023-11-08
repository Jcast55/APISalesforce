package com.web.app.Model;

import java.util.Date;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
@Table(name = "informe")
public class Informe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idInforme;

    private String idInforme1;

    private String idContact;

    private double horasTrabajadas;

    @Temporal(TemporalType.TIMESTAMP)
    private Date horaInicio;

    @Temporal(TemporalType.TIMESTAMP)
    private Date horaFinalizado;
}