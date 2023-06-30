package com.zonaop.alquileres.servicios;

import com.zonaop.alquileres.entidades.Imagen;
import com.zonaop.alquileres.entidades.Propiedad;

import com.zonaop.alquileres.enumeraciones.TipoPropiedad;

import com.zonaop.alquileres.excepciones.MiException;

import com.zonaop.alquileres.repositorios.PropiedadRepositorio;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PropiedadServicio {

    @Autowired
    public PropiedadRepositorio propiedadRepositorio;

    @Autowired
    public ImagenServicio imagenServicio;
    
    @Autowired
    public PropietarioServicio propietarioServicio;

    @Transactional()

    public void crearPropiedad(String nombre, String direccion, String localidad, String codigoPostal, String descripcion, Date fechaDesde, Date fechaHasta, Double precio, String tipoPropiedad, MultipartFile archivo, String idPropietario, long telefono) throws MiException {


        validar(nombre, direccion, localidad, codigoPostal, descripcion, fechaDesde, fechaHasta, precio, tipoPropiedad);

        Propiedad propiedad = new Propiedad();

        propiedad.setNombre(nombre);
        propiedad.setDireccion(direccion);
        propiedad.setLocalidad(localidad);
        propiedad.setCodigoPostal(codigoPostal);
        propiedad.setDescripcion(descripcion);
        propiedad.setFechaDesde(fechaDesde);
        propiedad.setFechaHasta(fechaHasta);
        propiedad.setPrecio(precio);
        propiedad.setTipo(TipoPropiedad.valueOf(tipoPropiedad));
        propiedad.setTelefono(telefono);
        
        propiedad.setPropietario(propietarioServicio.getOne(idPropietario));
        
        Imagen imagen = imagenServicio.guardar(archivo);

        propiedad.setFoto(imagen);

        propiedadRepositorio.save(propiedad);

    }

    public void modificarPropiedad(String id, String nombre, String direccion, String localidad, String codigoPostal, String descripcion, Date fechaDesde, Date fechaHasta, Double precio, String tipoPropiedad, MultipartFile archivo,String idPropietario) throws MiException {

        Optional<Propiedad> respuesta = propiedadRepositorio.findById(id);

        if (respuesta.isPresent()) {

            Propiedad propiedad = respuesta.get();

            validar(nombre, direccion, localidad, codigoPostal, descripcion, fechaDesde, fechaHasta, precio, tipoPropiedad);

            propiedad.setNombre(nombre);
            propiedad.setDireccion(direccion);
            propiedad.setLocalidad(localidad);
            propiedad.setCodigoPostal(tipoPropiedad);
            propiedad.setDescripcion(descripcion);
            propiedad.setFechaDesde(fechaDesde);
            propiedad.setFechaHasta(fechaHasta);
            propiedad.setPrecio(precio);
            propiedad.setTipo(TipoPropiedad.valueOf(tipoPropiedad));
            
            propiedad.setPropietario(propietarioServicio.getOne(idPropietario));

            String idImagen = null;

            if (propiedad.getFoto() != null) {

                idImagen = propiedad.getFoto().getId();

            }

            Imagen imagen = imagenServicio.actualizar(archivo, idImagen);
            
            propiedad.setFoto(imagen);
            
            propiedadRepositorio.save(propiedad);

        }

    }
    
    @Transactional
    public void eliminarPropiedad(String id){
        propiedadRepositorio.deleteById(id);
    }

    public List<Propiedad> listarPropiedadPorTipo(String tipo) {
        List<Propiedad> propiedades = propiedadRepositorio.buscarPorTipo(TipoPropiedad.valueOf(tipo));
        return propiedades;
    }

    public List<Propiedad> listarPropiedades() {
        List<Propiedad> propiedades = propiedadRepositorio.findAll();
        return propiedades;
    }

    public Propiedad buscarPropiedadPorId(String id) {
        return propiedadRepositorio.getById(id);
    }

    public void validar(String nombre, String direccion, String localidad, String codigoPostal, String descripcion, Date fechaDesde, Date fechaHasta, Double precio, String tipoPropiedad) {

    }

}
