
package com.zonaop.alquileres.servicios;

import com.zonaop.alquileres.entidades.Cliente;
import com.zonaop.alquileres.entidades.Imagen;
import com.zonaop.alquileres.enumeraciones.Rol;
import com.zonaop.alquileres.excepciones.MiException;
import com.zonaop.alquileres.repositorios.ClienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ClienteServicio {
    
    @Autowired
    public ClienteRepositorio clienteRepositorio;
    
    @Autowired
    public ImagenServicio imagenServicio;
    
    @Transactional
    public void registrar(String nombre,String apellido, String email, String contrasena, MultipartFile archivo) throws MiException{
        
       Cliente cliente= new Cliente();
        
       cliente.setNombre(nombre);
       cliente.setApellido(apellido);
       cliente.setEmail(email);
       cliente.setContrasena(contrasena);
       
       cliente.setRol(Rol.CLIENTE);
       
       Imagen imagen= imagenServicio.guardar(archivo);
       
       cliente.setFoto(imagen);
       
       clienteRepositorio.save(cliente);
       
    }
}
