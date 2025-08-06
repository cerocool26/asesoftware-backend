package com.asesoftware.shifts.repository;

import com.asesoftware.shifts.request.ShiftDTO;

import java.time.LocalDate;
import java.util.List;

public interface ShiftRepository {
    List<ShiftDTO> generarTurnosPorServicio(LocalDate fechaInicio, LocalDate fechaFin, Long idServicio);
}

