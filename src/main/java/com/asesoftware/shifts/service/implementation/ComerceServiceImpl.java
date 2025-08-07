package com.asesoftware.shifts.service.implementation;

import com.asesoftware.shifts.model.Comerce;
import com.asesoftware.shifts.repository.ComerceRepository;
import com.asesoftware.shifts.response.ComerceResponse;
import com.asesoftware.shifts.service.ComerceServiceInterface;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementación del servicio para la gestión de comercios.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ComerceServiceImpl implements ComerceServiceInterface {

    private final ComerceRepository comerceRepository;

    /**
     * Obtiene la lista de todos los comercios registrados.
     *
     * @return Lista de objetos {@link Comerce}.
     * @throws RuntimeException si ocurre un error al consultar los datos.
     */
    @Override
    public List<ComerceResponse> obtenerTodos() {
        log.info("Iniciando consulta de todos los comercios...");

        try {
            List<Comerce> comercios = comerceRepository.findAll();
            List<ComerceResponse> comerceResponseList = comercios.stream()
                    .map(comercio -> new ComerceResponse(
                            comercio.getIdComercio(),
                            comercio.getNombreComercio(),
                            comercio.getAforoMaximo()
                    ))
                    .toList();
            log.info("Consulta exitosa. Se encontraron {} comercios.", comerceResponseList.size());
            return comerceResponseList;
        } catch (Exception e) {
            log.error("Error al consultar los comercios", e);
            throw new RuntimeException("Error al obtener la lista de comercios", e);
        }
    }
}
