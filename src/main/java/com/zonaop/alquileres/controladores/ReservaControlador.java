/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaop.alquileres.controladores;

import com.zonaop.alquileres.entidades.Cliente;
import com.zonaop.alquileres.entidades.Opinion;
import com.zonaop.alquileres.entidades.Propiedad;
import com.zonaop.alquileres.entidades.Servicio;
import com.zonaop.alquileres.excepciones.MiException;
import com.zonaop.alquileres.servicios.ReservaServicio;
import java.util.List;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Joaquin
 */

@Controller
@RequestMapping("/reserva")
public class ReservaControlador {
    
    @Autowired
    private ReservaServicio reservaservi;
    
    @GetMapping("/registrar")
    public String registrarReserva(){
        
        return "registro_reserva.html";
  
   }
    
    @PostMapping("/registroReserva")
    public String registroReserva(@RequestParam String huesped, @RequestParam Cliente cliente, @RequestParam Opinion opinion,@RequestParam Propiedad,ModelMap Modelo, List<Servicio> servicios){
      
        
        try {
              
        reservaservi.crearReserva(huesped, huesped, servicios, Double.NaN, huesped, huesped, huesped, huesped);
        Modelo.put("exito", "la reserva se relizo correctamente");
            
            
        } catch (Exception e) {
         
            Modelo.put("error", e.getMessage());
            
            return "registro_reserva.html";
            
        }
        
        
        
        return "mainPage.html";
        
        
    }
   
    
    
}
