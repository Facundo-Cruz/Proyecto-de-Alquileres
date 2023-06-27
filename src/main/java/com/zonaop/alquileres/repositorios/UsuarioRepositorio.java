
package com.zonaop.alquileres.repositorios;

import com.zonaop.alquileres.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface UsuarioRepositorio extends JpaRepository <Usuario,String> {
    
    @Query("SELECT u FROM Usuario u WHERE u.email = :email")
    public Usuario buscarPorEmailUser(@Param("email")String email);
    
}
