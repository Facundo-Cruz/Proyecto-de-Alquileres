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
    public String registro(@RequestParam String email, @RequestParam String alias,
            @RequestParam String clave, @RequestParam String clave2,  @RequestParam(required = false) String rol,
            MultipartFile archivo,
            ModelMap model, RedirectAttributes redirectAttributes) {

        try {
            rol = "cliente";
            if (rol.equalsIgnoreCase("cliente")) {

                clienteServicio.registrar(alias, alias, email, clave, archivo, 0);

            } else {
                
                propietarioServicio.registrar(alias, alias, email, clave, archivo, 0);
                
            }
            redirectAttributes.addFlashAttribute("exito", "¡Has sido registrado con éxito!");
            return "redirect:/login";
        } catch (Exception ex) {
            model.put("error", ex.getMessage());
            model.put("email", email);
            model.put("alias", alias);
            model.put("rol", rol);
            return "registrar.html";
        }

    }
}
