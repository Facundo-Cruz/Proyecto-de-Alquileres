package com.zonaop.alquileres.controladores;

import com.zonaop.alquileres.entidades.Usuario;
import com.zonaop.alquileres.servicios.UsuarioServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
public class UsuarioControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping("/usuarios")
    public String listarUsuarios(ModelMap model) {

        List<Usuario> usuarios = usuarioServicio.listarUsuarios();
        model.put("usuarios", usuarios);

        return "lista-usuarios.html";

    }

    @GetMapping("/eliminar/{id}")
    public String eliminarUsuario(@PathVariable String id, RedirectAttributes redirectAttributes) {

        try {
            usuarioServicio.eliminarPorId(id);
            redirectAttributes.addFlashAttribute("exito", "¡El usuario ha sido "
                    + "eliminado con éxito!");
        } catch (Exception error) {
            redirectAttributes.addFlashAttribute("error", error.getMessage());
        } finally {
            return "redirect:/usuarios";
        }
        
    }
}
