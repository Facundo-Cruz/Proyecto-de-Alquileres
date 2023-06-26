package com.zonaop.alquileres.controladores;

import com.zonaop.alquileres.entidades.Propiedad;
import com.zonaop.alquileres.excepciones.MiException;
import com.zonaop.alquileres.servicios.PropiedadServicio;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
<<<<<<< HEAD
    public String subir(@RequestParam String nombre, @RequestParam String direccion,
            @RequestParam String localidad, @RequestParam Integer codigoPostal,
            @RequestParam String descripcion, @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaDesde,
            @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaHasta, @RequestParam Double precio,
            @RequestParam String tipoPropiedad, @RequestParam MultipartFile archivo,
            ModelMap modelo) {
=======
    public String subir(@RequestParam String nombre, @RequestParam String direccion, @RequestParam String localidad, @RequestParam Integer codigoPostal, @RequestParam String descripcion, @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") Date fechaDesde, @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") Date fechaHasta, @RequestParam Double precio, @RequestParam String tipoPropiedad, @RequestParam MultipartFile archivo, ModelMap modelo) {
>>>>>>> bd10e9229f6435e4dd27d382d7f7f75fbb398863

        try {

            propiedadServicio.crearPropiedad(nombre, direccion, localidad, codigoPostal, descripcion, fechaDesde, fechaHasta, precio, tipoPropiedad, archivo);

            return "mainPage.html";

        } catch (MiException ex) {

            return "formulario-registro-propiedad.html";

        }

    }

    @PostMapping("/filtrar")
    public String index(ModelMap model, @RequestParam String tipo) {
        List<Propiedad> propiedades = propiedadServicio.listarPropiedadPorTipo(tipo);
        model.put("propiedades", propiedades);
        return "mainPage.html";
    }
}
