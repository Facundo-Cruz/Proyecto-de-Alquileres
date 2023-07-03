/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaop.alquileres.servicios;

import com.zonaop.alquileres.entidades.Servicio;
import com.zonaop.alquileres.enumeraciones.TipoServicio;
import com.zonaop.alquileres.excepciones.MiException;
import com.zonaop.alquileres.repositorios.ServicioRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author JOAQUIN
 */
@Service
public class ServicioServicio {

    @Autowired
    private ServicioRepositorio serviciorepo;

    @Transactional
    public void crearServicio(String id, String nombre, String descripcion, Double precio, String tipo) throws MiException {

        validarServicio("2", nombre, descripcion, precio);

        Servicio servicio = new Servicio();

        servicio.setNombre(nombre);
        servicio.setDescripcion(descripcion);
        servicio.setPrecio(precio);
        servicio.setTp(TipoServicio.valueOf(tipo));
        serviciorepo.save(servicio);

    }

    ;
    

   @Transactional

    public void modificarServicio(String id, String nombre, String descripcion, Double precio) throws MiException {

        validarServicio(id, nombre, descripcion, precio);

        Optional<Servicio> servi = serviciorepo.findById(id);

        if (servi.isPresent()) {

            Servicio servicio = servi.get();

            servicio.setNombre(nombre);
            servicio.setDescripcion(descripcion);
            servicio.setPrecio(0);

            serviciorepo.save(servicio);

        };

    }

    @Transactional(readOnly = true)
    public List<Servicio> listarServicios() {

        List<Servicio> servicios = new ArrayList();

        servicios = serviciorepo.findAll();

        return servicios;

    }

//    public Servicio buscarPorTipo(String tipo) {
//        System.out.println(tipo);
//        List<Servicio> servicios = serviciorepo.buscarPorTipo(TipoServicio.valueOf(tipo));
//        return servicios.get(0);
//    }

    @Transactional
    public void eliminarServicio(String id) {

        Optional<Servicio> ser = serviciorepo.findById(id);

        if (ser.isPresent()) {

            Servicio servicio = ser.get();

            serviciorepo.delete(servicio);

        }

    }

    ;
    
    @Transactional(readOnly = true)
    public Servicio getOne(String id) {

        return serviciorepo.getOne(id);

    }

    private void validarServicio(String id, String nombre, String descripcion, Double precio) throws MiException {

        if (id == null) {
            throw new MiException("el id no puede ser nulo");

        }

        if (nombre == null || nombre.isEmpty()) {
            throw new MiException("el nombre no puede ser nulo o estar vacio");

        }

        if (descripcion == null || descripcion.isEmpty()) {
            throw new MiException("la descripcion no puede ser nula o estar vacia");

        }

        if (precio == 0) {
            throw new MiException("el precio no puede ser cero");

        }

    }

}
