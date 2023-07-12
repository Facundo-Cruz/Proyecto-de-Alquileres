/*

 */
package com.zonaop.alquileres.servicios;

import com.zonaop.alquileres.entidades.Imagen;
import com.zonaop.alquileres.entidades.Opinion;
import com.zonaop.alquileres.entidades.Propiedad;
import com.zonaop.alquileres.entidades.Reserva;
import com.zonaop.alquileres.enumeraciones.EstadoReserva;
import com.zonaop.alquileres.excepciones.MiException;
import com.zonaop.alquileres.repositorios.OpinionRepositorio;
import com.zonaop.alquileres.repositorios.PropiedadRepositorio;
import com.zonaop.alquileres.repositorios.ReservaRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class OpinionServicio {

    @Autowired
    private OpinionRepositorio opinionRepositorio;

    @Autowired
    private ImagenServicio imagenServicio;

    @Autowired
    private ReservaRepositorio reservaRepositorio;

    @Autowired
    private PropiedadRepositorio propiedadRepositorio;

    @Transactional
    public void crearOpinion(String idPropiedad, String huesped, String comentario,
            double calificacion, List<MultipartFile> archivos, String idReserva)
            throws MiException {

        validarOpinion(huesped, comentario, calificacion, archivos);

        Opinion opinion = new Opinion();
        Optional<Propiedad> propiedadOp = propiedadRepositorio.findById(idPropiedad);
        Optional<Reserva> reservaOp = reservaRepositorio.findById(idReserva);

        if (propiedadOp.isPresent() && reservaOp.isPresent()) {

            Propiedad propiedad = propiedadOp.get();
            List<Imagen> imagenes = imagenServicio.guardarList(archivos);
            opinion.setFotos(imagenes);
            opinion.setHuesped(huesped);
            opinion.setComentario(comentario);
            opinion.setCalificacion(calificacion);
            opinion.setPropiedad(propiedad);
            opinionRepositorio.save(opinion);

            List<Opinion> opiniones = propiedad.getOpiniones();
            opiniones.add(opinion);
            int cantidad = opinionRepositorio.contarPropiedades(propiedad.getId());
            int total = (int) (propiedad.getCalificacion()+calificacion);
            propiedad.setCalificacion(total/cantidad);
            propiedadRepositorio.save(propiedad);

            Reserva reserva = reservaOp.get();
            reserva.setEstado(EstadoReserva.Finalizada);
            reservaRepositorio.save(reserva);
        }
    }

    public List<Opinion> listarOpiniones() {
        List<Opinion> opiniones = opinionRepositorio.findAll();
        return opiniones;
    }

    public List<Opinion> listarOpinionesPorCalificacionDesc() {
        List<Opinion> opiniones = opinionRepositorio.findAllOrderByCalificacionDesc();
        return opiniones;
    }

    public int contarOpinionesDePropiedad(String id){
        return opinionRepositorio.contarPropiedades(id);
    }
    @Transactional
    public void modificarOpinion(String id, String huesped, String comentario,
            int calificacion, List<MultipartFile> archivos) throws MiException {

        validarOpinion(huesped, comentario, calificacion, archivos);

        Optional<Opinion> opinionOp = opinionRepositorio.findById(id);

        if (opinionOp.isPresent()) {

            Opinion opinion = opinionOp.get();
            opinion.setHuesped(huesped);
            opinion.setComentario(comentario);
            opinion.setCalificacion(calificacion);
            opinion.setFotos(imagenServicio.guardarList(archivos));
            opinionRepositorio.save(opinion);
        }
    }

    @Transactional
    public void eliminarOpinion(String id) {
        Optional<Opinion> opiniones = opinionRepositorio.findById(id);
        if (opiniones.isPresent()) {
            Opinion opinion = opiniones.get();
            opinionRepositorio.delete(opinion);
        }
    }

    @Transactional(readOnly = true)
    public Opinion getOne(String id) {
        return opinionRepositorio.getOne(id);
    }

    private void validarOpinion(String huesped, String comentario,
            double calificacion, List<MultipartFile> archivos) throws MiException {

        if (huesped == null || huesped.trim().isEmpty()) {
            throw new MiException("El nombre del huésped no puede ser nulo o vacío.");
        }

        if (comentario == null || comentario.trim().isEmpty()) {
            throw new MiException("El comentario no puede ser nulo o vacío.");
        }

        if (calificacion < 0 || calificacion > 5) {
            throw new MiException("La calificación debe estar entre 0 y 5.");
        }

        if (archivos == null) {
            throw new MiException("Los archivos adjuntados no puede ser nulos.");
        }
    }
}
