package com.zonaop.alquileres.servicios;

import com.zonaop.alquileres.entidades.Cliente;
import com.zonaop.alquileres.entidades.Imagen;
import com.zonaop.alquileres.enumeraciones.Rol;
import com.zonaop.alquileres.excepciones.MiException;
import com.zonaop.alquileres.repositorios.ClienteRepositorio;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ClienteServicio {
<<<<<<< HEAD

    @Autowired
    public ClienteRepositorio clienteRepositorio;

    @Autowired
    public ImagenServicio imagenServicio;

    @Autowired
    public UsuarioServicio usuarioServicio;

    @Transactional
    public void registrar(String nombre, String apellido, String email, String contrasena, MultipartFile archivo, Integer rol) throws MiException {

        usuarioServicio.validar(nombre, apellido, email, contrasena);

        Cliente cliente = new Cliente();

        cliente.setNombre(nombre);
        cliente.setApellido(apellido);
        cliente.setEmail(email);
        cliente.setContrasena(contrasena);
        cliente.setEstado(Boolean.TRUE);

        Imagen imagen = imagenServicio.guardar(archivo);

        cliente.setFoto(imagen);

        cliente.setRol(Rol.CLIENTE);

        clienteRepositorio.save(cliente);

    }

    @Transactional
    public void modificar(String id, String nombre, String apellido, String email, String contrasena, MultipartFile archivo) throws MiException {

        Optional<Cliente> respuesta = clienteRepositorio.findById(id);

        if (respuesta.isPresent()) {

            Cliente cliente = respuesta.get();

            usuarioServicio.validar(nombre, apellido, email, contrasena);

            cliente.setNombre(nombre);
            cliente.setApellido(apellido);
            cliente.setEmail(email);
            cliente.setContrasena(contrasena);

            String idImagen = null;

            if (cliente.getFoto() != null) {

                idImagen = cliente.getFoto().getId();

            }

            Imagen imagen = imagenServicio.actualizar(archivo, idImagen);

            cliente.setFoto(imagen);

            cliente.setRol(Rol.CLIENTE);

            clienteRepositorio.save(cliente);

        }

    }

    @Transactional
    public void darBaja(String id) throws MiException {

        Cliente cliente=getOne(id);
        
        cliente.setEstado(Boolean.FALSE);
        
        clienteRepositorio.save(cliente);

    }

    public Cliente getOne(String id) {

        return clienteRepositorio.getOne(id);

    }
=======
    
//    @Autowired
//    public ClienteRepositorio clienteRepositorio;
//    
//    @Autowired
//    public ImagenServicio imagenServicio;
//    
//    @Autowired
//    public UsuarioServicio usuarioServicio;
//    
//    @Transactional
//    public void registrar(Usuario user) throws MiException {
//        
//        Cliente cliente = (Cliente) user;
//        
//        clienteRepositorio.save(cliente);
//        
//    }
//    
//    @Transactional
//    public void modificar(String id, String nombre, String apellido, String email, String contrasena, MultipartFile archivo) throws MiException {
//        
//        Optional<Cliente> respuesta = clienteRepositorio.findById(id);
//        
//        if (respuesta.isPresent()) {
//            
//            Cliente cliente = respuesta.get();
//            
//            usuarioServicio.modificar(cliente, nombre, apellido, email, contrasena, archivo);
//            
//        }
//        
//    }
//    @Transactional
//    public void darBaja(String id) throws MiException{
//        
//        usuarioServicio.darBaja(getOne(id));
//        
//    }
//    
//    public Cliente getOne(String id){
//        
//        return  clienteRepositorio.getOne(id);
//        
//    }
>>>>>>> 62cfdbf0c8ceff4bac6025b1a821425e2a64d72e
}
