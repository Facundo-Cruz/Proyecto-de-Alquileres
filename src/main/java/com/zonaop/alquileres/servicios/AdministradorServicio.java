package com.zonaop.alquileres.servicios;

import com.zonaop.alquileres.entidades.Administrador;
import com.zonaop.alquileres.entidades.Usuario;
import com.zonaop.alquileres.excepciones.MiException;
import com.zonaop.alquileres.repositorios.AdministradorRepositorio;
import java.util.Optional;
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

    @Autowired
    public UsuarioServicio usuarioServicio;

    @Transactional
    public void registrar(Usuario user) throws MiException {

        Administrador administrador = (Administrador) user;

        administradorRepositorio.save(administrador);

    }

    @Transactional
    public void modificar(String id, String nombre, String apellido, String email, String contrasena, MultipartFile archivo) throws MiException {

        Optional<Administrador> respuesta = administradorRepositorio.findById(id);

        if (respuesta.isPresent()) {

            Administrador administrador = respuesta.get();

            usuarioServicio.modificar(administrador, nombre, apellido, email, contrasena, archivo);

        }

    }

    @Transactional
    public void darBaja(String id) throws MiException {

        usuarioServicio.darBaja(getOne(id));

    }

    public Administrador getOne(String id) {

        return administradorRepositorio.getOne(id);

    }
}
