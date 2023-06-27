package com.zonaop.alquileres.controladores;

import com.zonaop.alquileres.entidades.Cliente;
import com.zonaop.alquileres.entidades.Imagen;
import com.zonaop.alquileres.entidades.Usuario;
import com.zonaop.alquileres.enumeraciones.Rol;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/usuario")
public class UsuarioControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private ClienteServicio clienteServicio;

    @Autowired
    private PropietarioServicio propietarioservicio;

    @GetMapping("/listar")
    public String listarUsuarios(ModelMap model) {

        List<Usuario> usuarios = usuarioServicio.listarUsuarios();
        model.put("usuarios", usuarios);

        return "lista-usuarios.html";

    }
    
    @GetMapping("/perfil")
    public String mostrarPerfil(HttpSession session,ModelMap modelo){
        
        

        return "userInterface.html";
        
    }

    @GetMapping("/modificar/{id}")
    public String modificarUsuario(@PathVariable String id,ModelMap modelo){
        
        modelo.put("usuario", usuarioServicio.getOne(id));
        
        List<Usuario>listausuario=usuarioServicio.listarUsuarios();
        
        modelo.addAttribute("usuarios", listausuario);
        

        return "formulario-modificar-usuario.html";

       
    }
    
    @PostMapping("/modificar/{id}")
    public String modificarUsuario(@PathVariable String id,String nombre,String apellido,String nombreUsuario,String email,String contraseña,Imagen foto,@PathVariable String rol,ModelMap modelo, MultipartFile archivo,RedirectAttributes redirectAttributes){
        
        try {
  
            if (rol.equalsIgnoreCase("cliente")) {

               
                clienteServicio.modificar(id, nombre, apellido, nombreUsuario, email, contraseña, archivo);
                

            } else {

              
             propietarioservicio.modificar(id, nombre, apellido, nombreUsuario, email, contraseña, archivo);
                
            }
            redirectAttributes.addFlashAttribute("exito", "¡Ha modificado con éxito!");
            return "redirect:../mainPage";
        } catch (Exception ex) {
            modelo.put("error", ex.getMessage());
            modelo.put("email", email);
            modelo.put("alias", apellido);
            modelo.put("rol", rol);
<<<<<<< HEAD
            return "formulario-registro-usuario.html";
=======
            return "formulario-modificar-usuario.html";
>>>>>>> ead89008b4b198661cd8167f447d59e3b8ec75e0
        }
        
        
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
            return "redirect:/usuario/listar";
        }
        
    }
    
    
    
}
