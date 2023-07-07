package com.zonaop.alquileres.servicios;

import com.zonaop.alquileres.entidades.Imagen;
import com.zonaop.alquileres.entidades.Propiedad;
import com.zonaop.alquileres.entidades.Reserva;
import com.zonaop.alquileres.entidades.Servicio;

import com.zonaop.alquileres.enumeraciones.TipoPropiedad;
import com.zonaop.alquileres.enumeraciones.TipoServicio;

import com.zonaop.alquileres.excepciones.MiException;

import com.zonaop.alquileres.repositorios.PropiedadRepositorio;
import com.zonaop.alquileres.repositorios.ServicioRepositorio;
import java.util.ArrayList;
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

    @Autowired
    public ServicioServicio servicioServicio;

    @Autowired
    public ServicioRepositorio servicioRepositorio;

    @Transactional()

    public void crearPropiedad(String nombre, String direccion, String localidad,
            String codigoPostal, String descripcion, Date fechaDesde,
            Date fechaHasta, Double precio, String tipoPropiedad,
            List<MultipartFile> archivos, String idPropietario, long telefono,
            List<String> serviciosSeleccionados, List<Integer> preciosServicios,
            List<String> redesSociales, String email) throws MiException {

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
        propiedad.setEmail(email);
        if (redesSociales != null) {
            propiedad.setRedesSociales(redesSociales);
        }
        propiedad.setPropietario(propietarioServicio.getOne(idPropietario));

        List<Imagen> imagenes = imagenServicio.guardarList(archivos);

        propiedad.setFotos(imagenes);

        if (serviciosSeleccionados != null) {
            List<Servicio> servicios = new ArrayList();
            for (int i = 0; i < serviciosSeleccionados.size(); i++) {
                Servicio servicio = new Servicio();

                servicio.setNombre(serviciosSeleccionados.get(i));
                servicio.setPrecio(preciosServicios.get(i));
                servicio.setTp(TipoServicio.valueOf(serviciosSeleccionados.get(i)));

                switch (serviciosSeleccionados.get(i)) {
                    case "Transporte":
                        servicio.setDescripcion("");

                        break;
                    case "Seguridad":
                        servicio.setDescripcion("Servicio de seguridad para garantizar la protección y el orden en eventos, fiestas u otras situaciones donde se requiera control de acceso y vigilancia.");
                        break;
                    case "DJ":
                        servicio.setDescripcion("Servicio de un DJ profesional que se encarga de reproducir música y animar eventos, fiestas o bodas, creando una atmósfera agradable y entretenida.");
                        break;
                    case "Catering":
                        servicio.setDescripcion("Servicio de catering que ofrece comida y bebidas para eventos especiales, como bodas, fiestas o conferencias, con opciones personalizadas de menús.");
                        break;
                    case "Fotografo":
                        servicio.setDescripcion("Servicio de fotografía que incluye uno o mas fotógrafos que lograran capturar los mejores momentos de tu evento y tener recuerdos de un momento inolvidable.");
                        break;
                    case "Bar":
                        servicio.setDescripcion("Servicio de bar que ofrece bebidas y cócteles para eventos y fiestas, con opciones de barra libre o servicio de bartender profesional.");
                        break;
                    case "Show":
                        servicio.setDescripcion("Servicio de entretenimiento que ofrece espectáculos en vivo para eventos, como música en vivo, artistas circenses, baile, comedia u otras formas de entretenimiento.");
                        break;
                    case "Guarderia":
                        servicio.setDescripcion("Servicio de cuidado infantil que proporciona un entorno seguro y supervisado para niños durante eventos, bodas o celebraciones, permitiendo que los padres disfruten del evento con tranquilidad mientras sus hijos son atendidos y entretenidos.");
                        break;
                    case "Asador":
                        servicio.setDescripcion("Servicio de asado a la parrilla o barbacoa, donde un experto asador se encarga de preparar carnes y otros alimentos a la parrilla, creando sabores ahumados y jugosos.");
                        break;
                    default:
                        // Código para manejar un caso no reconocido
                        break;
                }
                servicioRepositorio.save(servicio);
                servicios.add(servicio);
            }
            propiedad.setServicios(servicios);
        }
        propiedadRepositorio.save(propiedad);

    }

    public void modificarPropiedad(String id, String nombre, String direccion, String localidad, String codigoPostal, String descripcion, Date fechaDesde, Date fechaHasta, Double precio, String tipoPropiedad, MultipartFile archivo, String idPropietario) throws MiException {

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

//            if (propiedad.getFoto() != null) {
//
//                idImagen = propiedad.getFoto().getId();
//
//            }

            Imagen imagen = imagenServicio.actualizar(archivo, idImagen);

//            propiedad.setFoto(imagen);

            propiedadRepositorio.save(propiedad);

        }

    }

    @Transactional
    public void eliminarPropiedad(String id) {
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

    public List<Propiedad> listarPorPropietario(String id) {
        List<Propiedad> propiedades = propiedadRepositorio.buscarPorPropietario(id);
        return propiedades;
    }

    public void validar(String nombre, String direccion, String localidad, String codigoPostal, String descripcion, Date fechaDesde, Date fechaHasta, Double precio, String tipoPropiedad) {

    }

}
