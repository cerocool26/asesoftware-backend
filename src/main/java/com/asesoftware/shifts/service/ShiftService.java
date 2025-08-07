package com.asesoftware.shifts.service;

import com.asesoftware.shifts.repository.ShiftRepositoryImpl;
import com.asesoftware.shifts.request.ShiftDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ShiftService {

    private final ShiftRepositoryImpl shiftRepository;

    public List<ShiftDTO> generarTurnos(LocalDate fechaInicio, LocalDate fechaFin, Long idServicio) {
        log.info("Generando turnos desde {} hasta {} para el servicio {}", fechaInicio, fechaFin, idServicio);

        try {
            List<ShiftDTO> turnos = shiftRepository.generarTurnosPorServicio(fechaInicio, fechaFin, idServicio);
            log.debug("Se generaron {} turnos", turnos.size());
            return turnos;
        } catch (Exception ex) {
            log.error("Error al generar turnos para el servicio {} entre {} y {}: {}",
                    idServicio, fechaInicio, fechaFin, ex.getMessage(), ex);
            throw new RuntimeException("Ocurrió un error al generar los turnos. Por favor intente nuevamente.");
        }
    }
}
