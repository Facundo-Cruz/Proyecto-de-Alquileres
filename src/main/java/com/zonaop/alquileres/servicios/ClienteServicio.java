package com.zonaop.alquileres.servicios;

import com.zonaop.alquileres.entidades.Cliente;
import com.zonaop.alquileres.entidades.Imagen;
import com.zonaop.alquileres.enumeraciones.Rol;
import com.zonaop.alquileres.excepciones.MiException;
import com.zonaop.alquileres.repositorios.ClienteRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    public void registrar(String nombre,String apellido, String nombreUsuario, String email, String contrasena, MultipartFile archivo, String rol) throws MiException {

        usuarioServicio.validar(nombre,apellido, nombreUsuario, email, contrasena);

        Cliente cliente = new Cliente();

        cliente.setNombre(nombre);
        cliente.setNombreUsuario(nombreUsuario);
        cliente.setEmail(email);
        cliente.setContrasena(new BCryptPasswordEncoder().encode(contrasena));
        cliente.setEstado(Boolean.TRUE);

        Imagen imagen = imagenServicio.guardar(archivo);

        cliente.setFoto(imagen);

        cliente.setRol(Rol.CLIENTE);

        clienteRepositorio.save(cliente);

    }

    @Transactional
    public void modificar(String id, String nombre, String apellido,String nombreUsuario, String email, String contrasena, MultipartFile archivo) throws MiException {

        Optional<Cliente> respuesta = clienteRepositorio.findById(id);

        if (respuesta.isPresent()) {

            Cliente cliente = respuesta.get();

            usuarioServicio.validar(nombre, apellido,nombreUsuario, email, contrasena);

            cliente.setNombre(nombre);
            cliente.setNombreUsuario(apellido);
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
    
    public List<Cliente> listarClientes(){
        return clienteRepositorio.findAll();
    }
    
    @Transactional
    public void eliminarCliente(String id){
        clienteRepositorio.deleteById(id);
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

}
