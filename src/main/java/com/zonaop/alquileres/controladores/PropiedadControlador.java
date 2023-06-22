package com.zonaop.alquileres.controladores;

import com.zonaop.alquileres.excepciones.MiException;
import com.zonaop.alquileres.servicios.PropiedadServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/propiedad")
public class PropiedadControlador {

    @Autowired
    public PropiedadServicio propiedadServicio;

    @GetMapping("/registrar")
    public String registrarPropiedad() {

        return "formulario-registro-propiedad.html";
    }

    @PostMapping("/registro")
    public String subir(@RequestParam MultipartFile archivo, ModelMap modelo) {

        try {

            propiedadServicio.crearPropiedad(archivo);

            return "mainPage.html";

        } catch (MiException ex) {

            return "mainPage.html";

        }

    }

}
