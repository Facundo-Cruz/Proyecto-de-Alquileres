

package com.zonaop.alquileres.controladores;

// @author lauty

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

 @Controller
 @RequestMapping("/")
public class PortalControlador {
     
     @GetMapping("/")
     public String index() {
     
         return "mainPage.html";
 } 
     
       @GetMapping("/registrar")
    public String registrar() {

        return "formulario-registro-usuario.html";

    }
    
}
