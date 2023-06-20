package com.zonaop.alquileres.servicios;


import com.zonaop.alquileres.excepciones.MiException;
import org.springframework.stereotype.Service;


@Service
public class UsuarioServicio {

<<<<<<< HEAD
    public void validar(String nombre, String apellido, String email, String contrasena) throws MiException {

    }
=======
//    @Autowired
//    public ImagenServicio imagenServicio;
//
//    @Autowired
//    public AdministradorServicio administradorServicio;
//
//    @Autowired
//    public ClienteServicio clienteServicio;
//
//    @Autowired
//    public PropietarioServicio propietarioServicio;
//
//    public void registrar(String nombre, String apellido, String email, String contrasena, MultipartFile archivo, Integer rol) throws MiException {
//
//        validar(nombre, apellido, email, contrasena);
//
//        Usuario usuario = new Usuario();
//
//        usuario.setNombre(nombre);
//        usuario.setApellido(apellido);
//        usuario.setEmail(email);
//        usuario.setContrasena(contrasena);
//        usuario.setEstado(Boolean.TRUE);
//
//        Imagen imagen = imagenServicio.guardar(archivo);
//
//        usuario.setFoto(imagen);
//
//        guardar(usuario, rol);
//
//    }
//
//    public void guardar(Usuario usuario, Integer rol) throws MiException {
//
//        switch (rol) {
//            case 0:
//                usuario.setRol(Rol.ADMIN);
//                administradorServicio.registrar(usuario);
//                break;
//            case 1:
//                usuario.setRol(Rol.CLIENTE);
//                clienteServicio.registrar(usuario);
//                break;
//            case 2:
//                usuario.setRol(Rol.PROPIETARIO);
//                propietarioServicio.registrar(usuario);
//                break;
//            default:
//                break;
//        }
//    }
//
//    public void modificar(Usuario usuario, String nombre, String apellido, String email, String contrasena, MultipartFile archivo) throws MiException {
//
//        validar(nombre, apellido, email, contrasena);
//
//        usuario.setNombre(nombre);
//        usuario.setApellido(apellido);
//        usuario.setEmail(email);
//        usuario.setContrasena(contrasena);
//
//        String idImagen = null;
//
//        if (usuario.getFoto() != null) {
//
//            idImagen = usuario.getFoto().getId();
//
//        }
//
//        Imagen imagen = imagenServicio.actualizar(archivo, idImagen);
//
//        usuario.setFoto(imagen);
//
//        //El usar la palabra "ordinal" traera el numero de la posicion dentro del enum dependiendo el rol que tenga el usuario
//        guardar(usuario, usuario.getRol().ordinal());
//
//    }
//
//    public void darBaja(Usuario usuario) throws MiException {
//
//        usuario.setEstado(Boolean.FALSE);
//        guardar(usuario, usuario.getRol().ordinal());
//
//    }
//
//    public void validar(String nombre, String apellido, String email, String contrasena) throws MiException {
//
//    }
>>>>>>> 62cfdbf0c8ceff4bac6025b1a821425e2a64d72e
}
