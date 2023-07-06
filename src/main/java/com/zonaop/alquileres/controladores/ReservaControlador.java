/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaop.alquileres.controladores;

import com.zonaop.alquileres.entidades.Cliente;
import com.zonaop.alquileres.entidades.Opinion;
import com.zonaop.alquileres.entidades.Propiedad;
import com.zonaop.alquileres.entidades.Reserva;
import com.zonaop.alquileres.entidades.Servicio;
import com.zonaop.alquileres.excepciones.MiException;
import com.zonaop.alquileres.servicios.PropiedadServicio;
import com.zonaop.alquileres.servicios.ReservaServicio;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Joaquin
 */
@Controller
@RequestMapping("/reserva")
public class ReservaControlador {

    @Autowired
    private ReservaServicio reservaservi;

    @Autowired
    private PropiedadServicio propiedadServicio;

    //ruta para el registro de reserva
    @GetMapping("/registrar/{idCasa}")
    public String registrarReserva(@PathVariable String idCasa, ModelMap modelo, HttpSession session) {
        Cliente cliente = (Cliente) session.getAttribute("usuariosession");
        modelo.put("propiedad", propiedadServicio.buscarPropiedadPorId(idCasa));
        modelo.put("cliente", cliente);
        modelo.put("fechasDesde", reservaservi.traerFechasDesde(idCasa));
        modelo.put("fechasHasta", reservaservi.traerFechasHasta(idCasa));
        return "formulario-registro-reserva.html";

    }

    //ruta para el registro de reserva con sus respectivos datos
    @PostMapping("/registro")
    public String registroReserva(@RequestParam String idCliente,
            @RequestParam String idPropiedad, @RequestParam String huesped,
            @RequestParam double total,
            @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaDesde,
            @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaHasta, ModelMap modelo,
            @RequestParam(value = "servicios", required = false) List<Servicio> servicios,
            RedirectAttributes redirectAttributes) {

        try {
            reservaservi.crearReserva(idCliente, idPropiedad, huesped, fechaDesde, fechaHasta,servicios, total);
            modelo.put("exito", "la reserva se relizo correctamente");

        } catch (MiException ex) {

            redirectAttributes.addFlashAttribute("error", ex.getMessage());

            return "redirect:/reserva/registrar/" + idPropiedad;

        }

        return "redirect:/";

    }

    //ruta para listar las reservas de las propiedades
    @GetMapping("/listar")

    public String listar(ModelMap modelo) {

        List<Reserva> reserva = reservaservi.listarReservas();
        modelo.addAttribute("reservas", reserva);

        return "lista-reservas.html";

    }

    //ruta para modificar el id de una reserva en especifico
    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, ModelMap modelo) {

        modelo.put("reserva", reservaservi.getOne(id));

        List<Reserva> rese = reservaservi.listarReservas();
        modelo.addAttribute("reserva", rese);

        return "formularioModificarReserva.html";

    }
    
    @GetMapping("/cancelar/{id}")
    public String cancelar(@PathVariable String id, RedirectAttributes redirectAttributes) {

        try {
            reservaservi.cancelarPorId(id);
            redirectAttributes.addFlashAttribute("exito", "¡La reserva ha sido "
                    + "cancelada con éxito!");
        } catch (Exception error) {
            redirectAttributes.addFlashAttribute("error", error.getMessage());
        } finally {
            return "redirect:../listar";
        }
        
    }

//    //ruta para modificar la reserva y sus datos correspondientes
//    @PostMapping("/modificar/{id}")
//    public String modificar(@PathVariable String id, String huesped, List<Servicio> servicios, Double total, String idOpinion, String idPropiedad, String idCliente, String idServicio, ModelMap modelo) {
//
//        try {
//
//            List<Reserva> reserva = reservaservi.listarReservas();
//            modelo.addAttribute("reserva", reserva);
//
//            reservaservi.modificarReserva(id, huesped, servicios, total, idOpinion, idPropiedad, idCliente, idServicio);
//
//            return "redirect:../lista";
//
//        } catch (MiException ex) {
//
//            List<Reserva> reserva = reservaservi.listarReservas();
//            modelo.put("error", ex.getMessage());
//
//            modelo.addAttribute("reserva", reserva);
//
//            return "formularioModificarReserva.html";
//
//        }
//
//    }
    //ruta para modificar la reserva y sus datos correspondientes
    @PostMapping("/modificar/{id}")
    public String modificar(@RequestParam(required = false) String id, @RequestParam String huesped, @RequestParam Cliente cliente, @RequestParam Opinion opinion, @RequestParam Propiedad propiedad, @RequestParam List<Servicio> servicios, ModelMap modelo) {

        try {

            List<Reserva> reserva = reservaservi.listarReservas();
            modelo.addAttribute("reserva", reserva);

            reservaservi.modificarReserva(id, huesped, servicios, Double.NaN, id, huesped, id, id);

            return "redirect:../listar";

        } catch (MiException ex) {

            List<Reserva> reserva = reservaservi.listarReservas();
            modelo.put("error", ex.getMessage());

            modelo.addAttribute("reserva", reserva);

            return "formularioModificarReserva.html";

        }

    }
      
    //ruta para eliminar una reserva por id 
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable String id, RedirectAttributes p) {

        try {
            reservaservi.EliminarReserva(id);
            p.addFlashAttribute("exito", "eliminado");

            return "redirect:../listar";

        } catch (MiException ex) {

            p.addFlashAttribute("error", "intente de nuevo");

            return "redirect:../listar";

        }

    }

}
