package com.zonaop.alquileres.controladores;

import com.zonaop.alquileres.entidades.Propiedad;
import com.zonaop.alquileres.entidades.Propietario;
import com.zonaop.alquileres.excepciones.MiException;
import com.zonaop.alquileres.servicios.PropiedadServicio;
import com.zonaop.alquileres.servicios.PropietarioServicio;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/propiedad")
public class PropiedadControlador {

    @Autowired
    public PropiedadServicio propiedadServicio;

    @Autowired
    public PropietarioServicio propietarioServicio;

    @GetMapping("/registrar")
    public String registrarPropiedad() {

        return "formulario-registro-propiedad.html";
    }

    @PostMapping("/registro")

    public String subir(@RequestParam String nombre, @RequestParam String direccion,
            @RequestParam String localidad, @RequestParam String codigoPostal,
            @RequestParam String descripcion, @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaDesde,
            @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaHasta, @RequestParam Double precio,
            @RequestParam String tipoPropiedad, @RequestParam("archivos[]") List<MultipartFile> archivos,
            ModelMap modelo, HttpSession session, @RequestParam long telefono,
            @RequestParam(value = "serviciosSeleccionados", required = false) List<String> serviciosSeleccionados,
            @RequestParam(value = "preciosServicios", required = false) List<Integer> preciosServicios,
            @RequestParam(value = "redesSociales", required = false) List<String> redesSociales,
            @RequestParam String email) {

        Propietario propietario = (Propietario) session.getAttribute("usuariosession");
        String idPropietario = propietario.getId();
        
      
        try {

            propiedadServicio.crearPropiedad(nombre, direccion, localidad, codigoPostal, descripcion, fechaDesde, fechaHasta, precio, tipoPropiedad, archivos, idPropietario, telefono, serviciosSeleccionados, preciosServicios, redesSociales, email);
            return "redirect:/";

        } catch (MiException ex) {

            return "redirect:/";

        }

    }

    @PostMapping("/filtrar")
    public String index(ModelMap model, @RequestParam(required = false) String tipo) {
        List<Propiedad> propiedades = propiedadServicio.listarPropiedadPorTipo(tipo);
        model.put("propiedades", propiedades);
        return "mainPage.html";
    }

    @GetMapping("/modificar/{id}")
    public String modificarPropiedad(@PathVariable("id") String idPropiedad, ModelMap modelo) {

        modelo.put("propiedad", propiedadServicio.buscarPropiedadPorId(idPropiedad));

        return "formulario-modificar-propiedad.html";
    }
    
    @GetMapping("/listar")
    public String listarUsuarios(ModelMap model) {

        List<Propiedad> propiedades = propiedadServicio.listarPropiedades();
        model.put("propiedades", propiedades);

        return "lista-propiedades.html";

    }
    
    @GetMapping("/mostrar/{id}")
    public String mostrarPropiedad(ModelMap model, @PathVariable String id) {

        model.put("propiedad", propiedadServicio.buscarPropiedadPorId(id));
        
        return "precompra-info.html";

    }
}
