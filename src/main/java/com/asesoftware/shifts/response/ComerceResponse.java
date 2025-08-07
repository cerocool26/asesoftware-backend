package com.asesoftware.shifts.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ComerceResponse {
    private Long idComercio;
    private String nombreComercio;
    private Integer aforoMaximo;
}
