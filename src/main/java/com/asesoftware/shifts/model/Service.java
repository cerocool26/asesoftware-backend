package com.asesoftware.shifts.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "SERVICIOS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Service {
    @Id
    @Column(name = "id_servicio", nullable = false)
    private Long idServicio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_comercio", nullable = false)
    private Comerce comercio;

    @Column(name = "nom_servicio", nullable = false, length = 100)
    private String nombreServicio;

    @Column(name = "hora_apertura", nullable = false)
    private LocalTime horaApertura;

    @Column(name = "hora_cierre", nullable = false)
    private LocalTime horaCierre;

    @Column(name = "duracion", nullable = false)
    private Integer duracion;

    @OneToMany(mappedBy = "servicio", fetch = FetchType.LAZY)
    private List<Shift> turnos;

}
