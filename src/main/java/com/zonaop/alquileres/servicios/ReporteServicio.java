package com.zonaop.alquileres.servicios;

import com.zonaop.alquileres.entidades.Imagen;
import com.zonaop.alquileres.entidades.Propietario;
import com.zonaop.alquileres.entidades.Reporte;
import com.zonaop.alquileres.excepciones.MiException;
import com.zonaop.alquileres.repositorios.PropietarioRepositorio;
import com.zonaop.alquileres.repositorios.ReporteRepositorio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReporteServicio {
    
    @Autowired
    public PropietarioRepositorio propietarioRepositorio;

    @Autowired
    private ReporteRepositorio reporteRepositorio;

    @Transactional
    public void crearReporte(String id, String idUsuario, String texto, Imagen foto, Date fechaHasta, String idGenerico) throws MiException {

        validarReporte(id, idUsuario, texto);

        Reporte reporte = new Reporte();

        reporte.setTexto(texto);
        reporte.setFoto(foto);
        reporte.setFechaHasta(fechaHasta);

        reporteRepositorio.save(reporte);

    }

    @Transactional(readOnly = true)
    public List<Reporte> listarReporte() {

        List<Reporte> reportes = new ArrayList();

        reportes = reporteRepositorio.findAll();

        return reportes;

    }
    
    @Transactional
    public void enviarAviso(String idUsuario,String idReporte){

    }

    @Transactional
    public void modificarReporte(String id, String idUsuario, String texto, Imagen foto, Date fechaHasta, String idGenerico) throws MiException {

        validarReporte(id, idUsuario, texto);

        Optional<Reporte> respuesta = reporteRepositorio.findById(id);

        Reporte reporte = new Reporte();

        if (respuesta.isPresent()) {

            reporte = respuesta.get();

        }

        reporte.setTexto(texto);
        reporte.setFoto(foto);
        reporte.setFechaHasta(fechaHasta);

        reporteRepositorio.save(reporte);

    }

    @Transactional
    public void eliminarReporte(String id) {

        Optional<Reporte> reportes = reporteRepositorio.findById(id);

        if (reportes.isPresent()) {

            Reporte reporte = reportes.get();

            reporteRepositorio.delete(reporte);

        }

    }

    @Transactional(readOnly = true)
    public Reporte getOne(String id) {

        return reporteRepositorio.getOne(id);

    }

    private void validarReporte(String id, String idUsuario, String texto) throws MiException {

        if (id == null) {

            throw new MiException("el id no puede ser nulo");

        }

        if (idUsuario == null || idUsuario.isEmpty()) {

            throw new MiException("el usuario no puede ser nulo o estar vacio");

        }

        if (texto == null || texto.isEmpty()) {

            throw new MiException("el texto no puede ser nulo o estar vacio");

        }

    }
}
