package com.asesoftware.shifts.controller;

import com.asesoftware.shifts.request.GenerateShiftRequest;
import com.asesoftware.shifts.request.ShiftDTO;
import com.asesoftware.shifts.service.ShiftServiceInterface;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/turnos")
@RequiredArgsConstructor
@Slf4j
public class ShiftController {
    private final ShiftServiceInterface service;

    @PostMapping("/generar")
    @Operation(
            summary = "Generar turnos",
            description = "Genera turnos para un servicio específico entre dos fechas dadas, de acuerdo con la configuración del servicio y comercios asociados."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Turnos generados exitosamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ShiftDTO.class))),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })
    public ResponseEntity<List<ShiftDTO>> generarTurnos(@RequestBody GenerateShiftRequest req) {
        log.info("Solicitud para generar turnos: fechaInicio={}, fechaFin={}, idServicio={}",
                req.getFechaInicio(), req.getFechaFin(), req.getIdServicio());
        var resultados = service.generarTurnos(req.getFechaInicio(), req.getFechaFin(), req.getIdServicio());
        return ResponseEntity.ok(resultados);
    }

}
