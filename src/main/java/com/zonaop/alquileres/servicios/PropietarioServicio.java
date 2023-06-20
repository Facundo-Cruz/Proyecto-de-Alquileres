package com.zonaop.alquileres.servicios;

import com.zonaop.alquileres.entidades.Propietario;
import com.zonaop.alquileres.entidades.Usuario;
import com.zonaop.alquileres.excepciones.MiException;
import com.zonaop.alquileres.repositorios.PropietarioRepositorio;
import java.util.Optional;
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

    @Autowired
    public UsuarioServicio usuarioServicio;

    @Transactional
    public void registrar(Usuario user) throws MiException {

        Propietario propietario = (Propietario) user;

        propietarioRepositorio.save(propietario);

    }

    @Transactional
    public void modificar(String id, String nombre, String apellido, String email, String contrasena, MultipartFile archivo) throws MiException {

        Optional<Propietario> respuesta = propietarioRepositorio.findById(id);

        if (respuesta.isPresent()) {

            Propietario propietario = respuesta.get();

            usuarioServicio.modificar(propietario, nombre, apellido, email, contrasena, archivo);

        }

    }

    @Transactional
    public void darBaja(String id) throws MiException {

        usuarioServicio.darBaja(getOne(id));

    }

    public Propietario getOne(String id) {

        return propietarioRepositorio.getOne(id);

    }
}
