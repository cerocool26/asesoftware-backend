package com.asesoftware.shifts.service;

import com.asesoftware.shifts.request.ShiftDTO;

import java.time.LocalDate;
import java.util.List;

public interface ShiftServiceInterface {

    public List<ShiftDTO> generarTurnos(LocalDate fechaInicio, LocalDate fechaFin, Long idServicio);
}
