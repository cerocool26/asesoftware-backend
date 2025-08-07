package com.asesoftware.shifts.repository;

import com.asesoftware.shifts.request.ShiftDTO;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ShiftRepository {
    List<ShiftDTO> generarTurnosPorServicio(LocalDate fechaInicio, LocalDate fechaFin, Long idServicio);
}

