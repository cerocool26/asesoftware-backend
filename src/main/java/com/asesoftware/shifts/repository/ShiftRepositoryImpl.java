package com.asesoftware.shifts.repository;

import com.asesoftware.shifts.mapper.ShiftMapper;
import com.asesoftware.shifts.response.ShiftResponse;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
@Slf4j
public class ShiftRepositoryImpl implements ShiftRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional(readOnly = true)
    public List<ShiftResponse> generarTurnosPorServicio(LocalDate fechaInicio, LocalDate fechaFin, Long idServicio) {
        log.info("Ejecutando procedimiento almacenado 'generar_turnos_por_servicio' con parámetros: fechaInicio={}, fechaFin={}, idServicio={}",
                fechaInicio, fechaFin, idServicio);

        try {
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

            List<ShiftResponse> salida = new ArrayList<>();
            if (resultList != null) {
                for (Object row : resultList) {
                    salida.add(ShiftMapper.mapRow(row));
                }
            }

            log.debug("Se obtuvieron {} registros del procedimiento almacenado.", salida.size());
            return salida;

        } catch (Exception e) {
            log.error("Error al ejecutar el procedimiento almacenado: {}", e.getMessage(), e);
            throw new RuntimeException("Error al generar los turnos desde la base de datos", e);
        }
    }

}
