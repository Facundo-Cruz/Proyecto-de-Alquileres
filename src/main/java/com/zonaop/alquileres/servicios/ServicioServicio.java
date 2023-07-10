package com.zonaop.alquileres.servicios;

import com.zonaop.alquileres.entidades.Servicio;
import com.zonaop.alquileres.enumeraciones.TipoServicio;
import com.zonaop.alquileres.excepciones.MiException;
import com.zonaop.alquileres.repositorios.ServicioRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServicioServicio {

    @Autowired
    private ServicioRepositorio servicioRepositorio;

    @Transactional
    public void crearServicio(String nombre, String descripcion, Double precio,
            String tipo) throws MiException {

        validarServicio(nombre, descripcion, precio, tipo);
        Servicio servicio = new Servicio();
        servicio.setNombre(nombre);
        servicio.setDescripcion(descripcion);
        servicio.setPrecio(precio);
        servicio.setTp(TipoServicio.valueOf(tipo));
        servicioRepositorio.save(servicio);
    }

    @Transactional
    public void modificarServicio(String id, String nombre, String descripcion, Double precio,
            String tipo) throws MiException {

        validarServicio(nombre, descripcion, precio, tipo);

        Optional<Servicio> servicioOp = servicioRepositorio.findById(id);

        if (servicioOp.isPresent()) {
            Servicio servicio = servicioOp.get();
            servicio.setNombre(nombre);
            servicio.setDescripcion(descripcion);
            servicio.setPrecio(precio);
            servicio.setTp(TipoServicio.valueOf(tipo));
            servicioRepositorio.save(servicio);
        }
    }

    @Transactional(readOnly = true)
    public List<Servicio> listarServicios() {
        List<Servicio> servicios = servicioRepositorio.findAll();
        return servicios;
    }

    @Transactional
    public void eliminarServicio(String id) {
        Optional<Servicio> servicioOp = servicioRepositorio.findById(id);
        if (servicioOp.isPresent()) {
            Servicio servicio = servicioOp.get();
            servicioRepositorio.delete(servicio);
        }
    }

    @Transactional(readOnly = true)
    public Servicio getOne(String id) {
        return servicioRepositorio.getOne(id);
    }

    private void validarServicio(String nombre, String descripcion, Double precio,
            String tipo) throws MiException {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new MiException("El nombre no puede ser nulo o vacío.");
        }

        if (descripcion == null || descripcion.trim().isEmpty()) {
            throw new MiException("La descripción no puede ser nula o vacía.");
        }

        if (precio == null || precio <= 0) {
            throw new MiException("El precio debe ser un valor válido y mayor que cero.");
        }

        if (tipo == null || tipo.trim().isEmpty()) {
            throw new MiException("El tipo de servicio no puede ser nulo o vacío.");
        }
    }
}
