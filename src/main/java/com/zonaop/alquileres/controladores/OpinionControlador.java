/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaop.alquileres.controladores;

import com.zonaop.alquileres.entidades.Imagen;
import com.zonaop.alquileres.entidades.Opinion;
import com.zonaop.alquileres.excepciones.MiException;
import com.zonaop.alquileres.servicios.OpinionServicio;
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
@RequestMapping("/opinion")
public class OpinionControlador {
    
    @Autowired
    private OpinionServicio opinionservicio;
    
    @GetMapping("/opinion")
    public String Opinion(){
        
        return "forumularioOpinion.html";

    }
    
    @PostMapping("/registroOpinion")
    public String registroOpinion(@RequestParam(required =false) String id,@RequestParam String huesped,@RequestParam String comentario,
            @RequestParam List<Imagen>Fotos,@RequestParam boolean disponibilidad,ModelMap modelo){
        
        try {
            
            opinionservicio.crearOpinion(id, huesped, comentario, 0, Fotos);
            modelo.put("excelente","su opinion ha sido emitida satisfactoriamente");
            
            
            
        } catch (MiException ex) {
            
            
            modelo.put("error", ex.getMessage());
 
            return "forumularioOpinion.html";
            
        }
        
        
        return  "mainPage.html";

    }
    
    
    
    @GetMapping("/listarOpinion")
    public String listarOpiniones(ModelMap modelo){
        
        List<Opinion>opiniones=opinionservicio.listarOpiniones();
        
        modelo.addAttribute("opiniones", opiniones);
        
        return "formularioOpinion.html";
        
    }
    
    @GetMapping("/listarOpinion")
    public String listarOpinionesDesc(ModelMap modelo){
        
        List<Opinion>opiniones=opinionservicio.listarOpinionesPorCalificacionDesc();
        
        modelo.addAttribute("opiniones", opiniones);
        
        return "formularioOpinion.html";
        
    }
    
    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable String id,ModelMap modelo){
        
        
        modelo.put("opinion", opinionservicio.getOne(id));
        
        List<Opinion>opinion=opinionservicio.listarOpiniones();
        modelo.addAttribute("opinion",opinion );
        
        return "formularioModificarOpinion.html";
        
        
        
    }
    
    
    //metodo de getmapping para modificar las opiniones segun la query, a falta de revision
    
    
//    @GetMapping("/modificar/{id}")
//    public String modificar(@PathVariable String id,ModelMap modelo){
//        
//        
//        modelo.put("opinion", opinionservicio.getOne(id));
//       
//        List<Opinion>opinion=opinionservicio.listarOpinionesPorCalificacionDesc();
//        modelo.addAttribute("opinion",opinion );
//        
//        return "formularioModificarOpinion.html";
//        
//        
//        
//    }
//    
    
    @PostMapping("/modificar/{id}")
    public String modificar(@PathVariable String id,String huesped,String comentario,List<Imagen>Fotos,double calificacion,ModelMap modelo){
        try {
            
            List<Opinion>opiniones=opinionservicio.listarOpiniones();
            modelo.addAttribute("opinion",opiniones);
            
            opinionservicio.modificarOpinion(id, huesped, comentario, 0, Fotos);
            
            return "redirect:../listarOpinion";
            
        } catch (MiException ex){
            
            List<Opinion>opiniones=opinionservicio.listarOpiniones();
            modelo.addAttribute("error",ex.getMessage());
            
             return "formularioModificarOpinion.html";
            
        }
    }
    
    
    @GetMapping("/eliminar/{id}")
    public String eliminarOpinion(@PathVariable String id,ModelMap modelo){
 
        modelo.put("eliminar", opinionservicio.getOne(id));
        
        return "eliminarOpinion.html";

    }
    
  
//    @PostMapping("/eliminar/{id}")
//    public String eliminarOpinion(@PathVariable String id,RedirectAttributes o){
// 
//        opinionservicio.eliminarOpinion(id);
//        o.addFlashAttribute("exito", "comentarioeliminado");
//        
//      return "redirect:../listarOpinion";
//        
//    }
//    
    
    //     @DeleteMapping("/eliminar/{id}")
//     public boolean eliminarOpinion(@PathVariable ("id"),String id){
//        
//        
//        return opinionservicio.EliminarOpinion(id);
//        
//        
//    }
//        
    
    
}
