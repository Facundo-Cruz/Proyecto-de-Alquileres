
package com.zonaop.alquileres.servicios;

import com.zonaop.alquileres.entidades.Administrador;
import com.zonaop.alquileres.entidades.Imagen;
import com.zonaop.alquileres.enumeraciones.Rol;
import com.zonaop.alquileres.excepciones.MiException;
import com.zonaop.alquileres.repositorios.AdministradorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class AdministradorServicio {
    
    @Autowired
    public AdministradorRepositorio administradorRepositorio;
    
    @Autowired
    public ImagenServicio imagenServicio;
    
    @Transactional
    public void registrar(String nombre,String apellido, String email, String contrasena, MultipartFile archivo) throws MiException{
        
        Administrador administrador= new Administrador();
        
       administrador.setNombre(nombre);
       administrador.setApellido(apellido);
       administrador.setEmail(email);
       administrador.setContrasena(contrasena);
       
       administrador.setRol(Rol.ADMIN);
       
       Imagen imagen= imagenServicio.guardar(archivo);
       
       administrador.setFoto(imagen);
       
       administradorRepositorio.save(administrador);
                
    }
}
