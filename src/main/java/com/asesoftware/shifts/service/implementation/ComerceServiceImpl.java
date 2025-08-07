package com.asesoftware.shifts.service.implementation;

import com.asesoftware.shifts.model.Comerce;
import com.asesoftware.shifts.repository.ComerceRepository;
import com.asesoftware.shifts.service.ComerceServiceInterface;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class ComerceServiceImpl implements ComerceServiceInterface {

    private ComerceRepository comerceRepository;

    public List<Comerce> obtenerTodos() {
        return comerceRepository.findAll();
    }
}
