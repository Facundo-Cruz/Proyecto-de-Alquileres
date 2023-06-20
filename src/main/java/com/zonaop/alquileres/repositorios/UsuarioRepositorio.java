
package com.zonaop.alquileres.repositorios;

import com.zonaop.alquileres.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsuarioRepositorio extends JpaRepository <Usuario,String> {
    
    
    
}
