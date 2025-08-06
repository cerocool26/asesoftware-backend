package com.asesoftware.shifts.repository;

import com.asesoftware.shifts.mapper.ShiftMapper;
import com.asesoftware.shifts.request.ShiftDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ShiftRepositoryImpl implements ShiftRepository{

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional(readOnly = true)
    public List<ShiftDTO> generarTurnosPorServicio(LocalDate fechaInicio, LocalDate fechaFin, Long idServicio) {
        StoredProcedureQuery query = em.createStoredProcedureQuery("generar_turnos_por_servicio");

        query.registerStoredProcedureParameter(1, java.sql.Date.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(2, java.sql.Date.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(3, Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(4, void.class, ParameterMode.REF_CURSOR);

        query.setParameter(1, Date.valueOf(fechaInicio));
        query.setParameter(2, Date.valueOf(fechaFin));
        query.setParameter(3, idServicio);

        query.execute();

        @SuppressWarnings("unchecked")
        List<Object> resultList = query.getResultList();

        List<ShiftDTO> salida = new ArrayList<>();
        if (resultList != null) {
            for (Object row : resultList) {
                salida.add(ShiftMapper.mapRow(row));
            }
        }

        return salida;
    }

}
