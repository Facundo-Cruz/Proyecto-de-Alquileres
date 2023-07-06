package com.zonaop.alquileres.repositorios;

import com.zonaop.alquileres.entidades.Reporte;
import com.zonaop.alquileres.entidades.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReporteRepositorio extends JpaRepository<Reporte, String> {
    
}
