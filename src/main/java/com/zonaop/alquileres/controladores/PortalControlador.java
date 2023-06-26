package com.zonaop.alquileres.controladores;

// @author lauty
import com.zonaop.alquileres.entidades.Propiedad;
import com.zonaop.alquileres.servicios.ClienteServicio;
import com.zonaop.alquileres.servicios.PropiedadServicio;
import com.zonaop.alquileres.servicios.PropietarioServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
public class PortalControlador {

    @Autowired
    private PropiedadServicio propiedadServicio;

    @Autowired
    private ClienteServicio clienteServicio;

    @Autowired
    private PropietarioServicio propietarioServicio;

    @GetMapping("/registrar")
    public String registrar() {

        return "formulario-registro-usuario.html";

    }

    @GetMapping("/login")
    public String login() {

        return "login.html";

    }

    @GetMapping("/")
    public String index(ModelMap model) {
        List<Propiedad> propiedades = propiedadServicio.listarPropiedades();
        model.put("propiedades", propiedades);
        return "mainPage.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam String nombre,@RequestParam String apellido,
            @RequestParam String email, @RequestParam String nombreUsuario,
            @RequestParam String contrasena, String rol,
            @RequestParam(required = false) MultipartFile archivo,
            ModelMap model, RedirectAttributes redirectAttributes) {

        try {
            rol = "cliente";
            if (rol.equalsIgnoreCase("cliente")) {

                clienteServicio.registrar(nombre, apellido,nombreUsuario, email, contrasena, archivo, rol);

            } else {
                
                propietarioServicio.registrar(nombre, apellido,nombreUsuario, email, contrasena, archivo, rol);
                
            }
            redirectAttributes.addFlashAttribute("exito", "¡Has sido registrado con éxito!");
            return "redirect:/login";
        } catch (Exception ex) {
            model.put("error", ex.getMessage());
            model.put("email", email);
            model.put("alias", apellido);
            model.put("rol", rol);
            return "registrar.html";
        }

    }
}
