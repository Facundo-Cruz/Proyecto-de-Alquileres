/*

 */
package com.zonaop.alquileres.servicios;

import com.zonaop.alquileres.entidades.Imagen;
import com.zonaop.alquileres.entidades.Opinion;
import com.zonaop.alquileres.entidades.Propiedad;
import com.zonaop.alquileres.entidades.Reserva;
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
    private OpinionRepositorio opinionrepo;

    @Autowired
    private ImagenServicio imagenServicio;
    
    @Autowired
    private PropiedadRepositorio propiedadRepositorio;

    @Transactional
    public void crearOpinion(String idPropiedad, String huesped, String comentario, double calificacion, List<MultipartFile> archivos) throws MiException {

        validarOpinion(huesped, comentario, calificacion, archivos);

        Opinion opinion = new Opinion();
        Optional<Propiedad> propiedadOp = propiedadRepositorio.findById(idPropiedad);
        Propiedad propiedad = propiedadOp.get();
        List<Imagen> imagenes = imagenServicio.guardarList(archivos);
        opinion.setFotos(imagenes);
        opinion.setHuesped(huesped);
        opinion.setComentario(comentario);
        opinion.setCalificacion(calificacion);
        opinionrepo.save(opinion);
        List<Opinion> opiniones = propiedad.getOpiniones();
        opiniones.add(opinion);
        propiedadRepositorio.save(propiedad);
    }

    @Transactional(readOnly = true)
    public List<Opinion> listarOpiniones() {

        List<Opinion> op = new ArrayList();

        op = opinionrepo.findAll();

        return op;

    }

    public List<Opinion> listarOpinionesPorCalificacionDesc() {

        List<Opinion> opi = new ArrayList();

        opi = opinionrepo.findAllOrderByCalificacionDesc();

        return opi;

    }

    @Transactional
    public void modificarOpinion(String id, String huesped, String comentario, int calificacion, List<MultipartFile> archivos) throws MiException {

        validarOpinion(huesped, comentario, calificacion, archivos);

        Optional<Opinion> opi = opinionrepo.findById(id);

        Opinion o = new Opinion();

        if (opi.isPresent()) {

            o = opi.get();

        }

        o.setHuesped(huesped);
        o.setComentario(comentario);
        o.setCalificacion(calificacion);
//        o.setFotos(fotos);

        opinionrepo.save(o);

    }

    @Transactional
    public void eliminarOpinion(String id) {

        Optional<Opinion> opiniones = opinionrepo.findById(id);

        if (opiniones.isPresent()) {

            Opinion opinion = opiniones.get();

            opinionrepo.delete(opinion);

        }

    }

    @Transactional(readOnly = true)
    public Opinion getOne(String id) {

        return opinionrepo.getOne(id);

    }

    private void validarOpinion(String huesped, String comentario, double calificacion, List<MultipartFile> archivos) throws MiException {

        if (huesped == null || huesped.isEmpty()) {

            throw new MiException("el huesped no puede ser nulo o estar vacio");

        }

        if (comentario == null || comentario.isEmpty()) {

            throw new MiException("el comentario no puede ser nulo o estar vacio");

        }

        if (calificacion == 0) {

            throw new MiException("la calificacion no puede ser cero");

        }

//        if(fotos==null || fotos.isEmpty()){
//        
//        throw new MiException("las fotos no pueden ser nulas o vacias");
//        
//    }
    }

}
