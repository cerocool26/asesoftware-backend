package com.asesoftware.shifts.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.FetchType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "TURNOS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Shift {

    @Id
    @Column(name = "id_turno", nullable = false)
    private Long idTurno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_servicio", nullable = false)
    private Service servicio;

    @Column(name = "fecha_turno", nullable = false)
    private LocalDate fechaTurno;

    @Column(name = "hora_inicio", nullable = false)
    private LocalDateTime horaInicio;

    @Column(name = "hora_fin", nullable = false)
    private LocalDateTime horaFin;

    @Column(name = "estado", nullable = false, length = 20)
    private String estado;
}
