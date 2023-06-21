package com.zonaop.alquileres.controladores;

// @author lauty
import com.zonaop.alquileres.entidades.Propiedad;
import com.zonaop.alquileres.servicios.PropiedadServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    

    @Autowired
    private PropiedadServicio propiedadServicio;
    @GetMapping("/")
    public String index(ModelMap model) {
        List<Propiedad> propiedades = propiedadServicio.listarPropiedades();
        model.put("propiedades", propiedades);
        return "mainPage.html";
    }
    
    @PostMapping("/filtrar")
    public String index(ModelMap model, @RequestParam String tipo) {
        List<Propiedad> propiedades = propiedadServicio.listarPropiedadPorTipo(tipo);
        model.put("propiedades", propiedades);
        return "mainPage.html";
    }
}
