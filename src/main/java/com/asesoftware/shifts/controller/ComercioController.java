package com.asesoftware.shifts.controller;

import com.asesoftware.shifts.response.ComerceResponse;
import com.asesoftware.shifts.service.ComerceServiceInterface;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controlador REST para la gestión de comercios.
 * Proporciona endpoints para consultar información relacionada con los comercios.
 */
@RestController
@RequestMapping("/comercios")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Comercios", description = "Operaciones relacionadas con los comercios")
public class ComercioController {

    private final ComerceServiceInterface comerceService;

    /**
     * Obtiene todos los comercios registrados en el sistema.
     *
     * @return Lista de comercios.
     */
    @Operation(summary = "Obtener todos los comercios", description = "Retorna una lista con todos los comercios disponibles.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comercios encontrados correctamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping
    public List<ComerceResponse> obtenerTodos() {
        log.info("Solicitud recibida: obtener todos los comercios");
        List<ComerceResponse> comercios = comerceService.obtenerTodos();
        log.info("Se encontraron {} comercios", comercios.size());
        return comercios;
    }
}
