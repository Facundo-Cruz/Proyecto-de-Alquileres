package com.zonaop.alquileres.controladores;

// @author lauty
import com.zonaop.alquileres.entidades.Propiedad;
import com.zonaop.alquileres.entidades.Usuario;
import com.zonaop.alquileres.servicios.ClienteServicio;
import com.zonaop.alquileres.servicios.PropiedadServicio;
import com.zonaop.alquileres.servicios.PropietarioServicio;
import com.zonaop.alquileres.servicios.ReservaServicio;
import com.zonaop.alquileres.servicios.UsuarioServicio;
import java.util.List;
import javax.servlet.http.HttpSession;
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
    
    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping("/pruebas")
    public String pruebas(ModelMap model) {
        List<Usuario> usuarios = usuarioServicio.listarUsuarios();
        model.put("usuarios", usuarios);
        return "pruebas.html";
    }

    @PostMapping("/pruebass")
    public String pruebas2(@RequestParam(value = "archivos[]", required = false) List<MultipartFile> archivos
          ) {

        if (archivos == null) {
            System.out.println("LLEGA NULL");
        }
        if (archivos.isEmpty()) {
            System.out.println("es vacío");
        }
        System.out.println(archivos);
        return "redirect:/";
    }

    @GetMapping("/registrar")
    public String registrar() {

        return "formulario-registro-usuario.html";

    }

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, ModelMap model) {
        if (error != null) {
            model.put("error", "¡Usuario o contraseña invalidos!");
        }
        return "login.html";

    }

    @GetMapping("/")
    public String index(ModelMap model, HttpSession session) {
        List<Propiedad> propiedades = propiedadServicio.listarPropiedades();
        Usuario usuario = (Usuario) session.getAttribute("usuariosession");
        if (usuario != null) {
            if (usuario.getRol().toString().equals("ADMIN")) {
                return "redirect:/admin/dashboard";
            }
        }

        model.put("usuario", usuario);
        model.put("propiedades", propiedades);
        return "mainPage.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam String nombre, @RequestParam String apellido,
            @RequestParam String email, @RequestParam Long telefono, @RequestParam String alias,
            @RequestParam String contrasena, @RequestParam String rol,
            @RequestParam MultipartFile archivo,
            ModelMap model, RedirectAttributes redirectAttributes) {

        try {
            if (rol.equalsIgnoreCase("cliente")) {

                clienteServicio.registrar(nombre, apellido, alias, email, telefono, contrasena, archivo, rol);

            } else {

                propietarioServicio.registrar(nombre, apellido, alias, email, telefono, contrasena, archivo, rol);

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
