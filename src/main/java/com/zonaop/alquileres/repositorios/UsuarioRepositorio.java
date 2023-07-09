package com.zonaop.alquileres.repositorios;

import com.zonaop.alquileres.entidades.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsuarioRepositorio extends JpaRepository<Usuario, String> {

    @Query("SELECT u FROM Usuario u WHERE u.email = :email")
    public Usuario buscarPorEmailUser(@Param("email") String email);

    @Query("SELECT u FROM Usuario u WHERE u.nombre = :nombre")
    public List<Usuario> buscarPorNombreUsuario(@Param("nombre") String nombre);

    @Query("SELECT u FROM Usuario u WHERE u.rol = 'ADMIN'")
    public List<Usuario> buscarAdmins();

    @Query("SELECT COUNT(u) FROM Usuario u WHERE u.rol = 'PROPIETARIO'")
    public int contarPropietarios();
    
    @Query("SELECT COUNT(u) FROM Usuario u WHERE u.rol = 'CLIENTE'")
    public int contarClientes();

}
