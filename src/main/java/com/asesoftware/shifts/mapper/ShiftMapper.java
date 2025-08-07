package com.asesoftware.shifts.mapper;

import com.asesoftware.shifts.response.ShiftResponse;

import java.sql.Date;
import java.sql.Timestamp;

public class ShiftMapper {

    /**
     * Mapea una fila (Object[], en el orden: id_turno, id_servicio, fecha_turno, hora_inicio, hora_fin, estado, nom_servicio, nom_comercio)
     */
    public static ShiftResponse mapRow(Object row) {
        Object[] cols;
        if (row instanceof Object[]) {
            cols = (Object[]) row;
        } else {
            cols = new Object[]{row};
        }

        ShiftResponse t = new ShiftResponse();

        if (cols.length > 0 && cols[0] != null) {
            t.setIdTurno(((Number) cols[0]).longValue());
        }

        if (cols.length > 1 && cols[1] != null) {
            t.setIdServicio(((Number) cols[1]).longValue());
        }

        if (cols.length > 2 && cols[2] != null) {
            t.setNombreServicio(cols[2].toString());
        }

        if (cols.length > 3 && cols[3] != null) {
            t.setNombreComercio(cols[3].toString());
        }

        if (cols.length > 4 && cols[4] != null) {
            if (cols[4] instanceof Date) {
                t.setFechaTurno(((Date) cols[4]).toLocalDate());
            } else if (cols[4] instanceof java.util.Date) {
                t.setFechaTurno(((java.util.Date) cols[4]).toInstant()
                        .atZone(java.time.ZoneId.systemDefault()).toLocalDate());
            }
        }

        if (cols.length > 5 && cols[5] != null) {
            if (cols[5] instanceof Timestamp) {
                t.setHoraInicio(((Timestamp) cols[5]).toLocalDateTime());
            } else if (cols[5] instanceof java.util.Date) {
                t.setHoraInicio(((java.util.Date) cols[5]).toInstant()
                        .atZone(java.time.ZoneId.systemDefault()).toLocalDateTime());
            }
        }

        if (cols.length > 6 && cols[6] != null) {
            if (cols[6] instanceof Timestamp) {
                t.setHoraFin(((Timestamp) cols[6]).toLocalDateTime());
            } else if (cols[6] instanceof java.util.Date) {
                t.setHoraFin(((java.util.Date) cols[6]).toInstant()
                        .atZone(java.time.ZoneId.systemDefault()).toLocalDateTime());
            }
        }

        if (cols.length > 7 && cols[7] != null) {
            t.setEstado(cols[7].toString());
        }

        return t;
    }
}
