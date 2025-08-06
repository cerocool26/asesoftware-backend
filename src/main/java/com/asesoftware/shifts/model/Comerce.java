package com.asesoftware.shifts.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "COMERCIOS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comerce {
    @Id

    @Column(name = "id_comercio", nullable = false)
    private Long idComercio;

    @Column(name = "nom_comercio", nullable = false, length = 100)
    private String nombreComercio;

    @Column(name = "aforo_maximo", nullable = false)
    private Integer aforoMaximo;

    @OneToMany(mappedBy = "comercio", fetch = FetchType.LAZY)
    private List<Service> servicios;
}
