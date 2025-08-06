package com.asesoftware.shifts.mapper;

import com.asesoftware.shifts.request.ShiftDTO;

import java.sql.Date;
import java.sql.Timestamp;

public class ShiftMapper {

    /**
     * Mapea una fila (Object[], en el orden: id_turno, id_servicio, fecha_turno, hora_inicio, hora_fin, estado)
     */
    public static ShiftDTO mapRow(Object row) {
        Object[] cols;
        if (row instanceof Object[]) {
            cols = (Object[]) row;
        } else {
            cols = new Object[] { row };
        }

        ShiftDTO t = new ShiftDTO();

        if (cols.length > 0 && cols[0] != null) {
            t.setIdTurno(((Number) cols[0]).longValue());
        }

        if (cols.length > 1 && cols[1] != null) {
            t.setIdServicio(((Number) cols[1]).longValue());
        }

        if (cols.length > 2 && cols[2] != null) {
            if (cols[2] instanceof Date) {
                t.setFechaTurno(((Date) cols[2]).toLocalDate());
            } else if (cols[2] instanceof java.util.Date) {
                t.setFechaTurno(((java.util.Date) cols[2]).toInstant()
                        .atZone(java.time.ZoneId.systemDefault()).toLocalDate());
            }
        }

        if (cols.length > 3 && cols[3] != null) {
            if (cols[3] instanceof Timestamp) {
                t.setHoraInicio(((Timestamp) cols[3]).toLocalDateTime());
            } else if (cols[3] instanceof java.util.Date) {
                t.setHoraInicio(((java.util.Date) cols[3]).toInstant()
                        .atZone(java.time.ZoneId.systemDefault()).toLocalDateTime());
            }
        }

        if (cols.length > 4 && cols[4] != null) {
            if (cols[4] instanceof Timestamp) {
                t.setHoraFin(((Timestamp) cols[4]).toLocalDateTime());
            } else if (cols[4] instanceof java.util.Date) {
                t.setHoraFin(((java.util.Date) cols[4]).toInstant()
                        .atZone(java.time.ZoneId.systemDefault()).toLocalDateTime());
            }
        }

        if (cols.length > 5 && cols[5] != null) {
            t.setEstado(cols[5].toString());
        }

        return t;
    }
}
