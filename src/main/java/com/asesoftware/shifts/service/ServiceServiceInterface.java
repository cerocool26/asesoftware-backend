package com.asesoftware.shifts.service;

import com.asesoftware.shifts.response.ServiceResponse;

import java.util.List;

public interface ServiceServiceInterface {
    public List<ServiceResponse> obtenerServiceByIdComerce(Long idComerce);
}
