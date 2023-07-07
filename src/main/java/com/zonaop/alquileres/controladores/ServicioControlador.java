/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaop.alquileres.controladores;

import com.zonaop.alquileres.entidades.Servicio;
import com.zonaop.alquileres.enumeraciones.TipoServicio;
import com.zonaop.alquileres.excepciones.MiException;
import com.zonaop.alquileres.servicios.ServicioServicio;
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
@RequestMapping("/servicio")
public class ServicioControlador {

    @Autowired
    private ServicioServicio servicioservi;

    @GetMapping("/servicio")
    public String Servicio() {

        return "index.html";

    }

    @PostMapping("/contratoservicio")
    public String contratarServicio(@RequestParam String nombre, @RequestParam String descripcion,
            @RequestParam double precio, ModelMap modelo, @RequestParam String tipo) {
        try {
            Servicio servicio = new Servicio();
            descripcion = "Servicio de animación que incluye actividades interactivas y entretenidas para eventos, como juegos, espectáculos, payasos o magos, con el objetivo de entretener a los asistentes.";
            nombre = "Animación";
            precio = 400;
            tipo = "Animacion";
            servicioservi.crearServicio(servicio.getId(), nombre, descripcion, precio,tipo);
            modelo.put("excelente", "los servicios han sido adquiridos");

        } catch (MiException ex) {

            modelo.put("error", ex.getMessage());

            return "formularioContratoServicio.html";

        }

        return "redirect:/";

    }

    @GetMapping("/listarServicio")
    public String listarServicios(ModelMap modelo) {

        List<Servicio> listaServi = servicioservi.listarServicios();

        modelo.addAttribute("servicios", listaServi);

        return "formularioContratoServicio.html";

    }

    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable String id,ModelMap modelo){
        
     modelo.put("servicio", servicioservi.getOne(id));
     
    List<Servicio>listaSer=servicioservi.listarServicios();
    
    modelo.addAttribute("servicios", listaSer);
        
        
    return "formulariomodificarServicioReserva.html";    
    
    }
    
    
//    @PostMapping("/modificar/{id}")
//    public String modificarServicio(@RequestParam(required = false) String id, @RequestParam String nombre, @RequestParam String descripcion, @RequestParam TipoServicio tp,
//<<<<<<< HEAD
//            @RequestParam double precio , ModelMap modelo){
//=======
//            @RequestParam double precio, ModelMap modelo){
//>>>>>>> a63aaf99f923b39ec5d19da82cfe6672ec01e4be
//        
//        try {
//            
//            List<Servicio>listaServi=servicioservi.listarServicios();
//            modelo.addAttribute("servicio",listaServi);
//            
//            servicioservi.modificarServicio(id, nombre, descripcion, precio);
//            
//            return "redirect:../listarServicio";
//            
//        } catch (MiException ex) {
//            
//            List<Servicio>listaServi=servicioservi.listarServicios();
//            modelo.addAttribute("error",ex.getMessage());
//        
//          return "formulariomodificarServicioReserva.html";    
//            
//        }
//        
//    }
//     
//     @GetMapping("/eliminar/{id}") 
//     public String eliminarServicio(@PathVariable String id,ModelMap modelo){
//         
//         modelo.put("servicios", servicioservi.getOne(id));
//         
//         return "servicio_eliminar.html";
//         
//     }
//        
//      @PostMapping("/eliminar/{id}")
//      public String eliminarServicio(@PathVariable String id,RedirectAttributes a){
//          
//
//          servicioservi.eliminarServicio(id);
//          a.addFlashAttribute("exito","servicio eliminado");
//          return "redirect:../listarServicio";
//          
//      }

     
    
            //@DeleteMapping("/eliminar/{id}")
//     public boolean eliminarServicio(@PathVariable ("id"),String id){
//        
//        
//        return servicioservi.EliminarServicio(id);
//        
//        
//    }
//       
        
        
        
    }
    

//        
//       try {
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

