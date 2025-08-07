package com.asesoftware.shifts.controller;

import com.asesoftware.shifts.model.Comerce;
import com.asesoftware.shifts.service.ComerceServiceInterface;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/comercios")
@RequiredArgsConstructor
@Slf4j
public class ComercioController {

    private ComerceServiceInterface comerceService;

    @GetMapping
    public List<Comerce> obtenerTodos() {
        return comerceService.obtenerTodos();
    }

}
