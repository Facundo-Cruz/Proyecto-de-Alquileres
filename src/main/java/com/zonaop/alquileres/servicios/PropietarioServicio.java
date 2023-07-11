package com.zonaop.alquileres.servicios;

import com.zonaop.alquileres.entidades.Imagen;
import com.zonaop.alquileres.entidades.Propietario;
import com.zonaop.alquileres.enumeraciones.Rol;
import com.zonaop.alquileres.excepciones.MiException;
import com.zonaop.alquileres.repositorios.PropietarioRepositorio;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public void registrar(String nombre, String apellido, String nombreUsuario,
            String email, Long telefono, String contrasena, MultipartFile archivo,
            String rol) throws MiException {

        usuarioServicio.validar(nombre, apellido, nombreUsuario, email, contrasena);

        if (telefono == null) {
            throw new MiException("El número de teléfono no puede ser nulo.");
        }

        Propietario propietario = new Propietario();
        propietario.setNombre(nombre);
        propietario.setApellido(apellido);
        propietario.setNombreUsuario(nombreUsuario);
        propietario.setEmail(email);
        propietario.setTelefono(telefono);
        propietario.setContrasena(new BCryptPasswordEncoder().encode(contrasena));
        propietario.setEstado(Boolean.TRUE);
        Imagen imagen = imagenServicio.guardar(archivo);
        propietario.setFoto(imagen);
        propietario.setRol(Rol.PROPIETARIO);
        propietarioRepositorio.save(propietario);
    }

    @Transactional
    public void modificar(String id, String nombre, String apellido,
            String nombreUsuario, String email, Long telefono, String contrasena,
            MultipartFile archivo, String passwordActual) throws MiException {

        Optional<Propietario> respuesta = propietarioRepositorio.findById(id);

        if (respuesta.isPresent()) {

            Propietario propietario = respuesta.get();

            String encodedPassword = propietario.getContrasena();

            if (!bCryptPasswordEncoder.matches(passwordActual, encodedPassword)) {
                throw new MiException("Las contraseña actual no es correcta.");
            }

            usuarioServicio.validar(nombre, apellido, nombreUsuario, email, contrasena);

            if (telefono == null) {
                throw new MiException("El número de teléfono no puede ser nulo.");
            }

            propietario.setNombre(nombre);
            propietario.setApellido(apellido);
            propietario.setNombreUsuario(nombreUsuario);
            propietario.setEmail(email);
            propietario.setTelefono(telefono);
            propietario.setContrasena(new BCryptPasswordEncoder().encode(contrasena));

            String idImagen = null;

            if (propietario.getFoto() != null) {
                idImagen = propietario.getFoto().getId();
            }

            Imagen imagen = imagenServicio.actualizar(archivo, idImagen);
            propietario.setFoto(imagen);
            propietario.setRol(Rol.PROPIETARIO);
            propietarioRepositorio.save(propietario);
        }
    }

    @Transactional
    public void eliminarPropietario(String id) {
        propietarioRepositorio.deleteById(id);
    }

    @Transactional
    public void darBaja(String id) throws MiException {
        Propietario propietario = getOne(id);
        propietario.setEstado(Boolean.FALSE);
        propietarioRepositorio.save(propietario);
    }

    public Propietario getOne(String id) {
        return propietarioRepositorio.getOne(id);
    }
}
