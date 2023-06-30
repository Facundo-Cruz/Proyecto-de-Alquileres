package com.zonaop.alquileres.controladores;

import com.zonaop.alquileres.entidades.Cliente;
import com.zonaop.alquileres.entidades.Imagen;
import com.zonaop.alquileres.entidades.Propiedad;
import com.zonaop.alquileres.entidades.Usuario;
import com.zonaop.alquileres.enumeraciones.Rol;
import com.zonaop.alquileres.repositorios.PropiedadRepositorio;
import com.zonaop.alquileres.servicios.ClienteServicio;
import com.zonaop.alquileres.servicios.PropietarioServicio;
import com.zonaop.alquileres.servicios.UsuarioServicio;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/usuario")
public class UsuarioControlador {
    
    @Autowired
    public PropiedadRepositorio propiedadRepositorio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private ClienteServicio clienteServicio;

    @Autowired
    private PropietarioServicio propietarioServicio;

    @GetMapping("/listar")
    public String listarUsuarios(ModelMap model) {

        List<Usuario> usuarios = usuarioServicio.listarUsuarios();
        model.put("usuarios", usuarios);

        return "lista-usuarios.html";

    }

    @GetMapping("/perfil")
    public String mostrarPerfil(HttpSession session, ModelMap modelo) {

        Usuario usuario = (Usuario) session.getAttribute("usuariosession");
        Usuario perfil;
        if (usuario.getRol().name().equals("PROPIETARIO")) {

            perfil = propietarioServicio.getOne(usuario.getId());
            
            List<Propiedad> propiedadesUsuario= propiedadRepositorio.propiedadesDePropietario(perfil.getId());
            
            modelo.put("propiedades", propiedadesUsuario);

        } else {

            perfil = clienteServicio.getOne(usuario.getId());

        }

        modelo.put("usuario", perfil);

        return "userInterface.html";

    }

    @GetMapping("/modificar")
    public String modificarUsuario(HttpSession session, ModelMap modelo) {

        Usuario usuario = (Usuario) session.getAttribute("usuariosession");
        Usuario perfil;
        if (usuario.getRol().name().equals("PROPIETARIO")) {

            perfil = propietarioServicio.getOne(usuario.getId());

        } else {

            perfil = clienteServicio.getOne(usuario.getId());

        }

        modelo.put("usuario", perfil);

        List<Usuario> listausuario = usuarioServicio.listarUsuarios();

        modelo.addAttribute("usuarios", listausuario);

        return "formulario-modificar-usuario.html";

    }

    @PostMapping("/modificar")
    public String modificarUsuario(String id, String nombre, String apellido, String nombreUsuario, String email, String contrasena, Imagen foto, String rol, ModelMap modelo, MultipartFile archivo, RedirectAttributes redirectAttributes) {

        try {

            if (rol.equalsIgnoreCase("cliente")) {

                clienteServicio.modificar(id, nombre, apellido, nombreUsuario, email, contrasena, archivo);

            } else {

                propietarioServicio.modificar(id, nombre, apellido, nombreUsuario, email, contrasena, archivo);

            }
            redirectAttributes.addFlashAttribute("exito", "¡Ha modificado con éxito!");
            return "redirect:/usuario/perfil";
        } catch (Exception ex) {
            modelo.put("error", ex.getMessage());
            modelo.put("email", email);
            modelo.put("alias", apellido);
            modelo.put("rol", rol);
            return "formulario-registro-usuario.html";

        }
<<<<<<< HEAD

    }

=======
        
    } 
    
>>>>>>> a63aaf99f923b39ec5d19da82cfe6672ec01e4be
    @GetMapping("/eliminar/{id}")
    public String eliminarUsuario(@PathVariable String id, RedirectAttributes redirectAttributes) {

        try {
            usuarioServicio.eliminarPorId(id);
            redirectAttributes.addFlashAttribute("exito", "¡El usuario ha sido "
                    + "eliminado con éxito!");
        } catch (Exception error) {
            redirectAttributes.addFlashAttribute("error", error.getMessage());
        } finally {
            return "redirect:/usuario/listar";
        }

    }
<<<<<<< HEAD

=======
    
    @GetMapping("/cambiarEstado/{id}")
    public String cambiarEstadoUsuario(@PathVariable String id, RedirectAttributes redirectAttributes) {

        try {
            usuarioServicio.cambiarEstadoPorId(id);
            redirectAttributes.addFlashAttribute("exito", "¡El usuario ha sido "
                    + "cambiado con éxito!");
        } catch (Exception error) {
            redirectAttributes.addFlashAttribute("error", error.getMessage());
        } finally {
            return "redirect:/usuario/listar";
        }
        
    }
    
>>>>>>> a63aaf99f923b39ec5d19da82cfe6672ec01e4be
}
