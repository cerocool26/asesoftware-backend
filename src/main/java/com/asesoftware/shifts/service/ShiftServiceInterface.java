package com.asesoftware.shifts.service;

import com.asesoftware.shifts.response.ShiftResponse;

import java.time.LocalDate;
import java.util.List;

public interface ShiftServiceInterface {

    public List<ShiftResponse> generarTurnos(LocalDate fechaInicio, LocalDate fechaFin, Long idServicio);
}
