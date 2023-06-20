package com.zonaop.alquileres.repositorios;

import com.zonaop.alquileres.entidades.Propiedad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropiedadRepositorio extends JpaRepository <Propiedad,String> {
    
}
