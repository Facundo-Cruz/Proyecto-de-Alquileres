/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaop.alquileres.controladores;

import com.zonaop.alquileres.entidades.Propiedad;
import com.zonaop.alquileres.entidades.Reserva;
import com.zonaop.alquileres.excepciones.MiException;
import com.zonaop.alquileres.servicios.PropiedadServicio;
import com.zonaop.alquileres.servicios.ReservaServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Joaquin
 */
@Controller
@RequestMapping("/admin")
public class AdministradorControlador {
    
    @Autowired
    ReservaServicio reservaservi;
    
    @Autowired
    private PropiedadServicio propiedadServicio;
    
    @GetMapping("/dashboard")
     public String panelAdministrativo(ModelMap model){
         
        List<Propiedad> propiedades = propiedadServicio.listarPropiedades();
         model.put("propiedades", propiedades);
         return "panelAdmin.html";
     }
     
         //ruta para listar las reservas de las propiedades
    @GetMapping("/listarReservas")

    public String listarReservas(ModelMap modelo) {

        List<Reserva> reserva = reservaservi.listarReservas();
        modelo.addAttribute("reservas", reserva);

        return "lista-reservas.html";

    }
    
        //ruta para eliminar una reserva por id 
    @GetMapping("/eliminarReserva/{id}")
    public String eliminarReserva(@PathVariable String id, RedirectAttributes p) {

        try {
            reservaservi.EliminarReserva(id);
            p.addFlashAttribute("exito", "eliminado");

            return "redirect:../listarReservas";

        } catch (MiException ex) {

            p.addFlashAttribute("error", "intente de nuevo");

            return "redirect:../listarReservas";

        }

    }
}
