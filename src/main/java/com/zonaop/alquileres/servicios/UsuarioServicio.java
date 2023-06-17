package com.zonaop.alquileres.servicios;

import com.zonaop.alquileres.excepciones.MiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UsuarioServicio {
    
    @Autowired
    public AdministradorServicio administradorServicio;
    
    @Autowired
    public ClienteServicio clienteServicio;
    
    @Autowired
    public PropietarioServicio propietarioServicio;
    

    public void registrar(String nombre, String apellido, String email, String contrasena, MultipartFile archivo, Integer rol) throws MiException {

        validar(nombre, apellido, email, contrasena);
        
        switch (rol) {
            case 0:
                administradorServicio.registrar(nombre, apellido, email, contrasena, archivo);
                break;
            case 1:
                clienteServicio.registrar(nombre, apellido, email, contrasena, archivo);
                break;
            case 2:
                propietarioServicio.registrar(nombre, apellido, email, contrasena, archivo);
                break;
            default:
                break;
        }
    }

    public void validar(String nombre, String apellido, String email, String contrasena) throws MiException {

    }
}
