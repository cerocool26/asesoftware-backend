package com.asesoftware.shifts.repository;

import com.asesoftware.shifts.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<Service,Long> {
    List<Service> findByComercioIdComercio(Long idComercio);
}
