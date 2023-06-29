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
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/Reserva")
public class ReservaControlador {

    @Autowired
    private ReservaServicio reservaservi;
    
    @Autowired
    private PropiedadServicio propiedadservicio;
    
    

    //ruta para el registro de reserva
    @GetMapping("/registrar")
    public String registrarReserva() {
  
//        List<Propiedad>propiedades=propiedadservicio.listarPropiedades();
//        
//        modelo.addAttribute("propiedades", propiedades);
//        
        
        return "formulario-registro-reserva.html";

    }

    //ruta para el registro de reserva con sus respectivos datos
    @PostMapping("/registroReserva")
    public String registroReserva(@RequestParam(required = false) String id, @RequestParam String huesped, @RequestParam Cliente cliente, @RequestParam Opinion opinion, @RequestParam Propiedad propiedad, @RequestParam List<Servicio> servicios, ModelMap modelo) {

        try {

            reservaservi.crearReserva(id, huesped, servicios, Double.NaN, id, huesped, id, id);
            modelo.put("exito", "la reserva se relizo correctamente");

        } catch (MiException ex) {
              
//          List<Propiedad>propiedades=propiedadservicio.listarPropiedades();
//            
//           modelo.addAttribute("propiedades", propiedades);
            
            modelo.put("error", ex.getMessage());

              return "formulario-registro-reserva.html";

        }

        return "mainPage.html";

    }

    //ruta para listar las reservas de las propiedades
    @GetMapping("/lista")

    public String listar(ModelMap modelo) {

        List<Reserva> reserva = reservaservi.listarReservas();
        modelo.addAttribute("reservas", reserva);

        return "formularioreservalista.html";

    }

    //ruta para modificar el id de una reserva en especifico
    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, ModelMap modelo) {

        modelo.put("reserva", reservaservi.getOne(id));

        List<Reserva> rese = reservaservi.listarReservas();
        modelo.addAttribute("reserva", rese);

        return "formularioModificarReserva.html";

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

            return "redirect:../lista";

        } catch (MiException ex) {

            List<Reserva> reserva = reservaservi.listarReservas();
            modelo.put("error", ex.getMessage());

            modelo.addAttribute("reserva", reserva);

            return "formularioModificarReserva.html";

        }

    }
    
    
    
    

    //ruta para eliminar una reserva por id 
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable String id, ModelMap modelo) {
        modelo.put("editorial", reservaservi.getOne(id));

        return "reserva_eliminar.html";
    }
        
       
//    //ruta para eliminar una reserva por id 
//     @PostMapping("/eliminar/{id}")
//    public String eliminar(@PathVariable String id, RedirectAttributes p) {
//
//
//         try {
//             reservaservi.EliminarReserva(id);
//             p.addFlashAttribute("exito","eliminado");
//             
//             return "redirect:../listaReserva";
//             
//         } catch (MiException ex) {
//   
//             
//             p.addFlashAttribute("error","intente de nuevo");
//             
//            return "redirect:../listaReserva";
//             
//         }
//
//   }
    
         
         
//     @DeleteMapping("/eliminar/{id}")
//     public boolean eliminarReserva(@PathVariable ("id"),String id){
//        
//        
//        return reservaservi.EliminarReserva(id);
//        
//        
//    }
//        
        
  
    
   
    
  

    //ruta para eliminar una reserva por id 
    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable String id, RedirectAttributes p) {

        try {
            reservaservi.EliminarReserva(id);
            p.addFlashAttribute("exito", "eliminado");

            return "redirect:../lista";

        } catch (MiException ex) {

            p.addFlashAttribute("error", "intente de nuevo");

            return "redirect:../listaReserva";

        }

    }

}

