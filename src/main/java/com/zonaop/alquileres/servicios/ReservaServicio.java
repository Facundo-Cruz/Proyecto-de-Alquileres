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

    @Transactional
    public void crearReserva(String idCliente, String idPropiedad, String huesped,
            Date fechaDeste, Date fechaHasta, List<Servicio> servicios,
            double total, String comentario)throws MiException {

        validarFechaCreacion(fechaDeste, fechaHasta, idPropiedad);

        Optional<Cliente> clienteOp = clienterepo.findById(idCliente);
        Optional<Propiedad> propiedadOp = propiedadrepo.findById(idPropiedad);

        if (clienteOp.isPresent() && propiedadOp.isPresent()) {

            Propiedad propiedad = propiedadOp.get();
            Cliente cliente = clienteOp.get();

            Reserva reserva = new Reserva();
            reserva.setHuesped(huesped);
            reserva.setCliente(cliente);
            reserva.setPropietario(propiedad.getPropietario());
            reserva.setFechaDesde(fechaDeste);
            reserva.setFechaHasta(fechaHasta);
            reserva.setPropiedad(propiedad);
            reserva.setTotal(total);
            reserva.setEstado(EstadoReserva.Pendiente);
            if (comentario != null & !comentario.trim().isEmpty()) {
                reserva.setComentario(comentario);
            }
            if (servicios != null) {
                reserva.setServicios(servicios);
            }
            reservaRepositorio.save(reserva);
        }
    }

    @Transactional(readOnly = true)
    public List<Reserva> listarReservas() {
        List<Reserva> reservas = reservaRepositorio.findAll();
        return reservas;
    }

    @Transactional
    public void modificarReserva(String id, Date fechaDeste,
            Date fechaHasta, List<Servicio> servicios, double total, String comentario)
            throws MiException {

        Optional<Reserva> reservaOp = reservaRepositorio.findById(id);

        if (reservaOp.isPresent()) {
            Reserva reserva = reservaOp.get();
            validarFechaCreacion(fechaDeste, fechaHasta, reserva.getPropiedad()
                    .getId());
            
            reserva.setComentario(comentario);
            reserva.setServicios(servicios);
            reserva.setTotal(total);
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
    public List<Reserva> listarPorClienteCancelada(String id) {
        List<Reserva> reservas = reservaRepositorio.buscarPorClienteCancelada(id);
        return reservas;
    }
    public List<Reserva> listarPorClienteFinalizada(String id) {
        List<Reserva> reservas = reservaRepositorio.buscarPorClienteFinalizada(id);
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

    public void aceptarReserva(String id) {
        Reserva reserva = getOne(id);
        reserva.setEstado(EstadoReserva.Activa);
        reservaRepositorio.save(reserva);
    }

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

    @Transactional
    public void eliminarReserva(String id) throws MiException {
        Optional<Reserva> reservaOp = reservaRepositorio.findById(id);
        if (reservaOp.isPresent()) {
            Reserva reserva = reservaOp.get();
            reservaRepositorio.delete(reserva);
        }
    }

    public void validarFechaCreacion(Date fechaDesde, Date fechaHasta,
            String propiedadId) throws MiException {
        List<Reserva> reservas = reservaRepositorio.
                verificarDisponibilidad(fechaDesde, fechaHasta, propiedadId);

        if (!reservas.isEmpty()) {
            throw new MiException("La fecha de reserva debe estar dentro del "
                    + "rango de disponibilidad.");
        }
    }
}
