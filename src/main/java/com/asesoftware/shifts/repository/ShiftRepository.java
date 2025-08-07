package com.asesoftware.shifts.repository;

import com.asesoftware.shifts.response.ShiftResponse;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ShiftRepository {
    List<ShiftResponse> generarTurnosPorServicio(LocalDate fechaInicio, LocalDate fechaFin, Long idServicio);
}

