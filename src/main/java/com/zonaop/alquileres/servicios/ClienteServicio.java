package com.zonaop.alquileres.servicios;

import com.zonaop.alquileres.entidades.Cliente;
import com.zonaop.alquileres.entidades.Usuario;
import com.zonaop.alquileres.excepciones.MiException;
import com.zonaop.alquileres.repositorios.ClienteRepositorio;
import java.util.Optional;
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
    
    @Autowired
    public UsuarioServicio usuarioServicio;
    
    @Transactional
    public void registrar(Usuario user) throws MiException {
        
        Cliente cliente = (Cliente) user;
        
        clienteRepositorio.save(cliente);
        
    }
    
    @Transactional
    public void modificar(String id, String nombre, String apellido, String email, String contrasena, MultipartFile archivo) throws MiException {
        
        Optional<Cliente> respuesta = clienteRepositorio.findById(id);
        
        if (respuesta.isPresent()) {
            
            Cliente cliente = respuesta.get();
            
            usuarioServicio.modificar(cliente, nombre, apellido, email, contrasena, archivo);
            
        }
        
    }
    @Transactional
    public void darBaja(String id) throws MiException{
        
        usuarioServicio.darBaja(getOne(id));
        
    }
    
    public Cliente getOne(String id){
        
        return  clienteRepositorio.getOne(id);
        
    }
}