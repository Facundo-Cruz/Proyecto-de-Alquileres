/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaop.alquileres.servicios;

import com.zonaop.alquileres.entidades.Cliente;
import com.zonaop.alquileres.entidades.Opinion;
import com.zonaop.alquileres.entidades.Propiedad;
import com.zonaop.alquileres.entidades.Propietario;
import com.zonaop.alquileres.entidades.Reserva;
import com.zonaop.alquileres.entidades.Servicio;
import com.zonaop.alquileres.enumeraciones.EstadoReserva;
import com.zonaop.alquileres.excepciones.MiException;
import com.zonaop.alquileres.repositorios.ClienteRepositorio;
import com.zonaop.alquileres.repositorios.OpinionRepositorio;
import com.zonaop.alquileres.repositorios.PropiedadRepositorio;
import com.zonaop.alquileres.repositorios.PropietarioRepositorio;
import com.zonaop.alquileres.repositorios.ReservaRepositorio;

import com.zonaop.alquileres.repositorios.ServicioRepositorio;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReservaServicio {
    
    @Autowired
    public PropietarioRepositorio propietarioRepositorio;
    
    @Autowired
    public ReservaRepositorio reservaRepositorio;
    
    @Autowired
    private PropiedadRepositorio propiedadrepo;
    
    @Autowired
    private ClienteRepositorio clienterepo;
    
    @Autowired
    private OpinionRepositorio opinionrepo;
    
    @Autowired
    private ServicioRepositorio serviciorepo;

//@RequestParam String idCliente,
//            @RequestParam String idCasa, @RequestParam String huesped, 
//            @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaDesde,
//            @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaHasta,
//            @RequestParam(required = false) List<Servicio> servicios,
    @Transactional
    public void crearReserva(String idCliente, String idPropiedad, String huesped,
            Date fechaDeste, Date fechaHasta, List<Servicio> servicios, double total) throws MiException {
        validarFechaCreacion(fechaDeste, fechaHasta, idPropiedad);
        Optional<Cliente> clienteOp = clienterepo.findById(idCliente);
        Optional<Propiedad> propiedadOp = propiedadrepo.findById(idPropiedad);
        Optional<Propietario> propietrarioOP = propietarioRepositorio.findById(propiedadOp.get().getPropietario().getId());
        
        Propiedad propiedad = propiedadOp.get();
        Cliente cliente = clienteOp.get();
        Propietario propietario = propietrarioOP.get();
        Reserva reserva = new Reserva();
        
        reserva.setHuesped(huesped);
        reserva.setCliente(cliente);
        reserva.setPropietario(propietario);
        reserva.setFechaDesde(fechaDeste);
        reserva.setFechaHasta(fechaHasta);
//        reserva.setOpinion(opinion);
        reserva.setPropiedad(propiedad);
//        reserva.setServicios(servicios);
        reserva.setTotal(total);
        reserva.setEstado(EstadoReserva.Pendiente);
        if (servicios != null) {
            reserva.setServicios(servicios);
        }
        
        reservaRepositorio.save(reserva);
        
    }
    
    @Transactional(readOnly = true)
    public List<Reserva> listarReservas() {
        
        List<Reserva> Reservas = new ArrayList();
        
        Reservas = reservaRepositorio.findAll();
        
        return Reservas;
        
    }
    
    @Transactional
    public void modificarReserva(String id, String huesped, List<Servicio> servicios, Double total, String idOpinion, String idPropiedad, String idCliente, String idServicio) throws MiException {

//        validarReserva(id, huesped, servicios, total, idOpinion, idCliente, idPropiedad, idServicio);
        Optional<Reserva> a = reservaRepositorio.findById(id);
        
        Optional<Propiedad> b = propiedadrepo.findById(idPropiedad);
        Optional<Cliente> c = clienterepo.findById(idCliente);
        Optional<Opinion> d = opinionrepo.findById(idOpinion);
        Optional<Servicio> e = serviciorepo.findById(idServicio);
        
        Cliente cliente = new Cliente();
        Opinion opinion = new Opinion();
        Propiedad propiedad = new Propiedad();
        Servicio servicio = new Servicio();
        
        if (a.isPresent()) {
            
            Reserva reserva = a.get();
            
            reserva.setHuesped(huesped);
            reserva.setCliente(cliente);
            reserva.setServicios((List<Servicio>) servicios);
            reserva.setTotal(total);
            
            reservaRepositorio.save(reserva);
            
            reservaRepositorio.save(reserva);
            
        }
        
    }
    
    @Transactional
    public void cancelarPorId(String id) {
        
        Optional<Reserva> respuesta = reservaRepositorio.findById(id);
        
        if (respuesta.isPresent()) {
            Reserva reserva = respuesta.get();
            reserva.setEstado(EstadoReserva.Cancelada);
            reservaRepositorio.save(reserva);
        }
    }
//    

    @Transactional
    public void EliminarReserva(String id) throws MiException {
        
        Optional<Reserva> r = reservaRepositorio.findById(id);
        
        if (r.isPresent()) {
            
            Reserva reserva = r.get();
            
            reservaRepositorio.delete(reserva);
            
            reservaRepositorio.delete(reserva);
            
        }
        
    }
//    

    @Transactional(readOnly = true)
    public Reserva getOne(String id) {
        
        return reservaRepositorio.getOne(id);
        
    }
    
    public List<Reserva> listarPorClienteActiva(String id) {
        List<Reserva> reservas = reservaRepositorio.buscarPorClienteActiva(id);
        return reservas;
    }
    
    public List<Reserva> listarPorClientePendiente(String id) {
        List<Reserva> reservas = reservaRepositorio.buscarPorClientePendiente(id);
        return reservas;
    }
    
    public List<Reserva> listarPorPropietarioActiva(String id) {
        List<Reserva> reservas = reservaRepositorio.buscarPorPropietarioActiva(id);
        return reservas;
    }
    
    public List<Reserva> listarPorPropietarioCancelada(String id) {
        List<Reserva> reservas = reservaRepositorio.buscarPorPropietarioCancelada(id);
        return reservas;
    }
    
    public List<Reserva> listarPorPropietarioFinalizada(String id) {
        List<Reserva> reservas = reservaRepositorio.buscarPorPropietarioFinalizada(id);
        return reservas;
    }

    public List<Reserva> listarPorPropietarioPendiente(String id) {
        List<Reserva> reservas = reservaRepositorio.buscarPorPropietarioPendiente(id);
        return reservas;
    }

    public void aceptarReserva(String id){
        Reserva reserva = getOne(id);
        reserva.setEstado(EstadoReserva.Activa);
        reservaRepositorio.save(reserva);
    }
//    
//    private void validarReserva (String id,String huesped,List<Servicio>servicios,Double total, String idOpinion,String idPropiedad,String idCliente,String idServicio) throws MiException{
//       
//    
//    
//                
//       if (id == null) {
//           throw new MiException("el id no puede ser nulo"); //
//       }
//
//
//        }
//
//    }
//
//    @Transactional
//    public void EliminarReserva(String id) throws MiException {
//
//        Optional<Reserva> r = reservaRepositorio.findById(id);
//        if (r.isPresent()) {
//
//            Reserva reserva = r.get();
//
//            reservaRepositorio.delete(reserva);
//        }
//
//    }
//
//    @Transactional(readOnly = true)
//    public Reserva getOne(String id) {
//
//        return reservaRepositorio.getOne(id);
//
//    }
//
//    private void validarReserva(String id, String huesped, List<Servicio> servicios, Double total, String idOpinion, String idPropiedad, String idCliente, String idServicio) throws MiException {
//
//        if (id == null) {
//            throw new MiException("el id no puede ser nulo"); //
//        }
//
//        if (huesped.isEmpty() || huesped == null) {
//            throw new MiException("el huesped no puede ser nulo o estar vacio");
//        }
//
//        if (servicios == null) {
//            throw new MiException("los servicios no pueden ser nulos");
//        }
//
//        if (idOpinion.isEmpty() || idOpinion == null) {
//            throw new MiException("la opinion no puede ser nula o estar vacia");
//        }
//
//        if (idCliente.isEmpty() || idCliente == null) {
//
//            throw new MiException("el cliente no puede ser nulo o estar vacio");
//
//        }
//
//        if (total.isInfinite() || total == null) {
//
//            throw new MiException("el total no puede ser infinito o ser vacio");
//
//        }
//
//        if (idServicio.isEmpty() || idServicio == null) {
//
//            throw new MiException("los servicios no pueden ser nulos o estar vacios");
//
//        }
//
//        if (idPropiedad.isEmpty() || idPropiedad == null) {
//
//            throw new MiException("la propiedad no puede ser nula o estar vacia");
//
//        }
//
//    }
    public List<String> traerFechasDesde(String propiedadId) {
        List<Date> fechasDesde = reservaRepositorio.buscarFechasDesde(propiedadId);
        List<String> fechasDesdeISO = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        for (Date fecha : fechasDesde) {
            String fechaISO = formatter.format(fecha);
            fechasDesdeISO.add(fechaISO);
        }
        return fechasDesdeISO;
    }
    
    public List<String> traerFechasHasta(String propiedadId) {
        List<Date> fechasHasta = reservaRepositorio.buscarFechasHasta(propiedadId);
        List<String> fechasHastaISO = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        for (Date fecha : fechasHasta) {
            String fechaISO = formatter.format(fecha);
            fechasHastaISO.add(fechaISO);
        }
        return fechasHastaISO;
    }
    
    public void validarFechaCreacion(Date fechaDesde, Date fechaHasta, String propiedadId) throws MiException {
        
        List<Reserva> reservas = reservaRepositorio.verificarDisponibilidad(fechaDesde, fechaHasta, propiedadId);
        
        if (!reservas.isEmpty()) {
            throw new MiException("La fecha de reserva debe estar dentro del rango de disponibilidad.");
        }
    }
    
    @Transactional
    public void eliminarReservasDePropiedad(String idPropiedad) throws MiException {
        
        try {
            List<Reserva> reservas = reservaRepositorio.buscarPorPropiedad(idPropiedad);
            
            for (Reserva reserva : reservas) {
                reservaRepositorio.delete(reserva);
            }
        } catch (Exception e) {
            throw new MiException("Error borrando las reservas de la propiedad.");
        }
        
    }
    
    @Transactional
    public void eliminarReservasDeCliente(String idCliente) throws MiException {
        
        try {
            List<Reserva> reservas = reservaRepositorio.buscarPorCliente(idCliente);
            
            for (Reserva reserva : reservas) {
                reservaRepositorio.delete(reserva);
            }
        } catch (Exception e) {
            throw new MiException("Error borrando las reservas de la propiedad.");
        }
        
    }
    
    public int contarReservas() {
        return reservaRepositorio.contarReservas();
    }
}
