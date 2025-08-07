package com.asesoftware.shifts.service.implementation;

import com.asesoftware.shifts.repository.ServiceRepository;
import com.asesoftware.shifts.response.ServiceResponse;
import com.asesoftware.shifts.service.ServiceServiceInterface;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ServiceServiceImpl implements ServiceServiceInterface {

    private final ServiceRepository serviceRepository;

    @Override
    public List<ServiceResponse> obtenerServiceByIdComerce(Long idComerce){
        var services = serviceRepository.findByComercioIdComercio(idComerce);
        return services.stream()
                .map(service -> new ServiceResponse(
                        service.getIdServicio(),
                        service.getNombreServicio()
                ))
                .toList();
    }
}
