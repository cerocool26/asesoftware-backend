package com.asesoftware.shifts.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ShiftDTO {
    private Long idTurno;
    private Long idServicio;
    private LocalDate fechaTurno;
    private LocalDateTime horaInicio;
    private LocalDateTime horaFin;
    private String estado;
}
