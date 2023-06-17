
package com.zonaop.alquileres.servicios;

import com.zonaop.alquileres.entidades.Imagen;
import com.zonaop.alquileres.entidades.Propietario;
import com.zonaop.alquileres.enumeraciones.Rol;
import com.zonaop.alquileres.excepciones.MiException;
import com.zonaop.alquileres.repositorios.PropietarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PropietarioServicio {
    
    @Autowired
    public PropietarioRepositorio propietarioRepositorio;
    
    @Autowired
    public ImagenServicio imagenServicio;
    
    @Transactional
    public void registrar(String nombre,String apellido, String email, String contrasena, MultipartFile archivo) throws MiException{
        Propietario propietario= new Propietario();
        
       propietario.setNombre(nombre);
       propietario.setApellido(apellido);
       propietario.setEmail(email);
       propietario.setContrasena(contrasena);
       
       propietario.setRol(Rol.PROPIETARIO);
       
       Imagen imagen= imagenServicio.guardar(archivo);
       
       propietario.setFoto(imagen);
       
       propietarioRepositorio.save(propietario);
        
        
    }
    
}
