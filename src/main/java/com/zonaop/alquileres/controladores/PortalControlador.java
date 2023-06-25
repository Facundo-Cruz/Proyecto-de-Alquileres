package com.zonaop.alquileres.controladores;

// @author lauty
import com.zonaop.alquileres.entidades.Propiedad;
import com.zonaop.alquileres.servicios.PropiedadServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/")
public class PortalControlador {

    @Autowired
    private PropiedadServicio propiedadServicio;
    
    @GetMapping("/registrar")
    public String registrar() {

        return "formulario-registro-usuario.html";

    }

    @GetMapping("/")
    public String index(ModelMap model) {
        List<Propiedad> propiedades = propiedadServicio.listarPropiedades();
        model.put("propiedades", propiedades);
        return "mainPage.html";
    }

<<<<<<< HEAD
=======
  
>>>>>>> 51e9a03044662d2bd6b7584d94167d09b7030389
}
