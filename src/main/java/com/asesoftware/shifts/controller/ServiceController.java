package com.asesoftware.shifts.controller;

import com.asesoftware.shifts.response.ServiceResponse;
import com.asesoftware.shifts.service.ServiceServiceInterface;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/servicios")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Servicios", description = "Operaciones relacionadas con los servicios")
public class ServiceController {

    private final ServiceServiceInterface service;

    @GetMapping("/comercio/{idComercio}")
    public List<ServiceResponse> getServicesByComerce(@PathVariable Long idComercio){
        return service.obtenerServiceByIdComerce(idComercio);
    }


}
