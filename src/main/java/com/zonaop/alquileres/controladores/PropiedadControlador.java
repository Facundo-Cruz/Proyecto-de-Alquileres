package com.zonaop.alquileres.controladores;

import com.zonaop.alquileres.entidades.Propiedad;
import com.zonaop.alquileres.entidades.Propietario;
import com.zonaop.alquileres.entidades.Servicio;
import com.zonaop.alquileres.enumeraciones.Localidad;
import com.zonaop.alquileres.enumeraciones.TipoPropiedad;
import com.zonaop.alquileres.excepciones.MiException;
import com.zonaop.alquileres.servicios.OpinionServicio;
import com.zonaop.alquileres.servicios.PropiedadServicio;
import com.zonaop.alquileres.servicios.PropietarioServicio;
import com.zonaop.alquileres.servicios.ReservaServicio;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/propiedad")
public class PropiedadControlador {

    @Autowired
    public PropiedadServicio propiedadServicio;

    @Autowired
    public PropietarioServicio propietarioServicio;

    @Autowired
    public ReservaServicio reservaServicio;
    
    @Autowired
    public OpinionServicio opinionServicio;

    @GetMapping("/registrar")
    @PreAuthorize("hasRole('PROPIETARIO')")
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
            @RequestParam String email, @RequestParam Integer banos,
            @RequestParam Integer habitaciones) {

        Propietario propietario = (Propietario) session.getAttribute("usuariosession");
        String idPropietario = propietario.getId();

        try {

            propiedadServicio.crearPropiedad(nombre, direccion, localidad,
                    codigoPostal, descripcion, fechaDesde, fechaHasta,
                    precio, tipoPropiedad, archivos, idPropietario,
                    telefono, serviciosSeleccionados, preciosServicios,
                    redesSociales, email, banos, habitaciones);
            return "redirect:/";

        } catch (MiException ex) {

            return "redirect:/";

        }

    }

//    @PostMapping("/filtrar")
//    public String index(ModelMap model, @RequestParam(required = false) String tipo) {
//        List<Propiedad> propiedades = propiedadServicio.listarPropiedadPorTipo(tipo);
//        model.put("propiedades", propiedades);
//        return "mainPage.html";
//    }
    @PostMapping("/filtrar")
    public String filtrarPropiedad(@RequestParam(required = false) TipoPropiedad tipo,
            @RequestParam(required = false) Localidad localidad,
            @RequestParam(value = "servicios[]", required = false) List<Servicio> servicios,
            @RequestParam(required = false) Boolean precio,
            @RequestParam(required = false) Boolean calificacion,
            ModelMap modelo) {

        List<Propiedad> propiedadesFiltradas = propiedadServicio.filtrarPropiedades(tipo, localidad, servicios, precio, calificacion);
        modelo.addAttribute("propiedades", propiedadesFiltradas);
        return "mainPage.html";
    }

    @GetMapping("/modificar/{idPropiedad}")
    @PreAuthorize("hasRole('PROPIETARIO')")
    public String modificarPropiedad(@PathVariable String idPropiedad, ModelMap modelo) {

        modelo.put("propiedad", propiedadServicio.buscarPropiedadPorId(idPropiedad));

        return "formulario-modificar-propiedad.html";
    }
    
    @PostMapping("/modificar")
    public String modificarPropiedad(@RequestParam String id,@RequestParam String nombre,
            @RequestParam String descripcion, @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaDesde,
            @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaHasta, @RequestParam Double precio,
            @RequestParam("archivos[]") List<MultipartFile> archivos,
            ModelMap modelo, HttpSession session, @RequestParam long telefono,
            @RequestParam(value = "serviciosSeleccionados", required = false) List<String> serviciosSeleccionados,
            @RequestParam(value = "preciosServicios", required = false) List<Integer> preciosServicios,
            @RequestParam(value = "redesSociales", required = false) List<String> redesSociales,
            @RequestParam String email, @RequestParam Integer banos,
            @RequestParam Integer habitaciones,RedirectAttributes redirectAttributes) {
        
        
        
        
         try {
             
             propiedadServicio.modificarPropiedad(id, nombre, descripcion, fechaDesde, fechaHasta, precio, archivos, telefono, serviciosSeleccionados, preciosServicios, redesSociales, email, banos, habitaciones);
            redirectAttributes.addFlashAttribute("exito", "¡Ha modificado con éxito!");
            return "redirect:/usuario/perfil";
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("error", ex.getMessage());
//            redirectAttributes.addFlashAttribute("email", email);
//            redirectAttributes.addFlashAttribute("alias", apellido);
//            redirectAttributes.addFlashAttribute("rol", rol);
            return "redirect:/propiedad/modificar";

        }
        
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
        model.put("cantCalificaciones", opinionServicio.contarOpinionesDePropiedad(id));
        return "precompra-info.html";

    }

    @GetMapping("/eliminar/{id}")
    public String eliminarPropiedad(@PathVariable String id, RedirectAttributes redirect) {

        try {
            reservaServicio.eliminarReservasDePropiedad(id);
            propiedadServicio.eliminarPropiedad(id);
            redirect.addFlashAttribute("exito", "¡Propiedad eliminada correctamente!");
            return "redirect:../listar";
        } catch (MiException ex) {
            redirect.addFlashAttribute("error", ex.getMessage());
            return "redirect:../listar";
        }

    }
}
