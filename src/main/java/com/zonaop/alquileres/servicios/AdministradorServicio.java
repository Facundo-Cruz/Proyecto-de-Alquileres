package com.zonaop.alquileres.servicios;

import com.zonaop.alquileres.entidades.Administrador;
import com.zonaop.alquileres.entidades.Imagen;
import com.zonaop.alquileres.enumeraciones.Rol;
import com.zonaop.alquileres.excepciones.MiException;
import com.zonaop.alquileres.repositorios.AdministradorRepositorio;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class AdministradorServicio {

<<<<<<< HEAD
    @Autowired
    public AdministradorRepositorio administradorRepositorio;

    @Autowired
    public ImagenServicio imagenServicio;

    @Autowired
    public UsuarioServicio usuarioServicio;

    @Transactional
    public void registrar(String nombre, String apellido, String email, String contrasena, MultipartFile archivo, Integer rol) throws MiException {

        usuarioServicio.validar(nombre, apellido, email, contrasena);

        Administrador administrador = new Administrador();

        administrador.setNombre(nombre);
        administrador.setApellido(apellido);
        administrador.setEmail(email);
        administrador.setContrasena(contrasena);
        administrador.setEstado(Boolean.TRUE);

        Imagen imagen = imagenServicio.guardar(archivo);

        administrador.setFoto(imagen);

        administrador.setRol(Rol.ADMIN);

        administradorRepositorio.save(administrador);

    }

    @Transactional
    public void modificar(String id, String nombre, String apellido, String email, String contrasena, MultipartFile archivo) throws MiException {

        Optional<Administrador> respuesta = administradorRepositorio.findById(id);

        if (respuesta.isPresent()) {

            Administrador administrador = respuesta.get();

            usuarioServicio.validar(nombre, apellido, email, contrasena);

            administrador.setNombre(nombre);
            administrador.setApellido(apellido);
            administrador.setEmail(email);
            administrador.setContrasena(contrasena);

            String idImagen = null;

            if (administrador.getFoto() != null) {

                idImagen = administrador.getFoto().getId();

            }

            Imagen imagen = imagenServicio.actualizar(archivo, idImagen);

            administrador.setFoto(imagen);

            administrador.setRol(Rol.ADMIN);

            administradorRepositorio.save(administrador);

        }

    }

    @Transactional
    public void darBaja(String id) throws MiException {

        Administrador administrador=getOne(id);
        
        administrador.setEstado(Boolean.FALSE);
        
        administradorRepositorio.save(administrador);

    }

    public Administrador getOne(String id) {

        return administradorRepositorio.getOne(id);

    }
=======
//    @Autowired
//    public AdministradorRepositorio administradorRepositorio;
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
//        Administrador administrador = (Administrador) user;
//
//        administradorRepositorio.save(administrador);
//
//    }
//
//    @Transactional
//    public void modificar(String id, String nombre, String apellido, String email, String contrasena, MultipartFile archivo) throws MiException {
//
//        Optional<Administrador> respuesta = administradorRepositorio.findById(id);
//
//        if (respuesta.isPresent()) {
//
//            Administrador administrador = respuesta.get();
//
//            usuarioServicio.modificar(administrador, nombre, apellido, email, contrasena, archivo);
//
//        }
//
//    }
//
//    @Transactional
//    public void darBaja(String id) throws MiException {
//
//        usuarioServicio.darBaja(getOne(id));
//
//    }
//
//    public Administrador getOne(String id) {
//
//        return administradorRepositorio.getOne(id);
//
//    }
>>>>>>> 62cfdbf0c8ceff4bac6025b1a821425e2a64d72e
}
