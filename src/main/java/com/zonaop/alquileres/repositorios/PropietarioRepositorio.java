
package com.zonaop.alquileres.repositorios;

import com.zonaop.alquileres.entidades.Propietario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropietarioRepositorio extends JpaRepository <Propietario,String> {
    
}
