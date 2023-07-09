/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaop.alquileres.controladores;

import com.zonaop.alquileres.entidades.Imagen;
import com.zonaop.alquileres.entidades.Opinion;
import com.zonaop.alquileres.excepciones.MiException;
import com.zonaop.alquileres.servicios.ClienteServicio;
import com.zonaop.alquileres.servicios.OpinionServicio;
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
import org.springframework.web.multipart.MultipartFile;
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

    @Autowired
    private ClienteServicio clienteServicio;

    @Autowired
    private ReservaServicio reservaServicio;

    @GetMapping("/registro/{idReserva}")
    public String Opinion(@PathVariable String idReserva, ModelMap model) {
        model.put("reserva", reservaServicio.getOne(idReserva));
        return "formulario-registro-opinion.html";
    }

    @PostMapping("/registrar/{idReserva}")
    public String registroOpinion(@RequestParam(value = "archivos[]",
            required = false) List<MultipartFile> archivos,
            @RequestParam double rating, @RequestParam String comentario,
            @RequestParam String huesped, @RequestParam String idPropiedad,
            ModelMap modelo, @PathVariable String idReserva) {

        try {

            opinionservicio.crearOpinion(idPropiedad, huesped, comentario, rating, archivos, idReserva);
            modelo.put("excelente", "su opinion ha sido emitida satisfactoriamente");

        } catch (MiException ex) {

            modelo.put("error", ex.getMessage());

            return "forumularioOpinion.html";

        }

        return "redirect:/";

    }

    @GetMapping("/listar")
    public String listarOpiniones(ModelMap modelo) {

        List<Opinion> opiniones = opinionservicio.listarOpiniones();

        modelo.addAttribute("opiniones", opiniones);

        return "lista-opiniones.html";

    }

    @GetMapping("/listarOpinion2")
    public String listarOpinionesDesc(ModelMap modelo) {

        List<Opinion> opiniones = opinionservicio.listarOpinionesPorCalificacionDesc();

        modelo.addAttribute("opiniones", opiniones);

        return "formularioOpinion.html";

    }

    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, ModelMap modelo) {

        modelo.put("opinion", opinionservicio.getOne(id));

        List<Opinion> opinion = opinionservicio.listarOpiniones();
        modelo.addAttribute("opinion", opinion);

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
    public String modificar(@RequestParam(required = false) String id, @RequestParam String huesped, @RequestParam String comentario,
            List<MultipartFile> archivos, @RequestParam double calificacion, ModelMap modelo) {
        try {

            List<Opinion> opiniones = opinionservicio.listarOpiniones();
            modelo.addAttribute("opinion", opiniones);

            opinionservicio.modificarOpinion(id, huesped, comentario, 0, archivos);

            return "redirect:../listarOpinion";

        } catch (MiException ex) {

            List<Opinion> opiniones = opinionservicio.listarOpiniones();
            modelo.addAttribute("error", ex.getMessage());

            return "formularioModificarOpinion.html";

        }
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarOpinion(@PathVariable String id, RedirectAttributes o) {

        opinionservicio.eliminarOpinion(id);
        o.addFlashAttribute("exito", "¡El comentario fue eliminado exitosamente!");
        return "redirect:../listar";

    }
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
