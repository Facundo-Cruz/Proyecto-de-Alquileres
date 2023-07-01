package com.zonaop.alquileres.controladores;

import com.zonaop.alquileres.entidades.Cliente;
import com.zonaop.alquileres.entidades.Imagen;
import com.zonaop.alquileres.entidades.Usuario;
import com.zonaop.alquileres.enumeraciones.Rol;
import com.zonaop.alquileres.excepciones.MiException;
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
    public String mostrarPerfil(HttpSession session,ModelMap model){
        
        Usuario usuario = (Usuario) session.getAttribute("usuariosession");
        model.put("usuario", usuario);
        

        return "userInterface.html";
        
    }

    @GetMapping("/modificar/{id}")
    public String modificarUsuario(@PathVariable String id,ModelMap model){
        
        model.put("usuario", usuarioServicio.getOne(id));
        
        List<Usuario>listausuario=usuarioServicio.listarUsuarios();
        
        model.addAttribute("usuarios", listausuario);
        

        return "formulario-modificar-usuario.html";

       
    }
    
    @PostMapping("/modificar/{id}")
    public String modificarUsuario(@RequestParam(required = false) String id, @RequestParam String nombre, @RequestParam String apellido, @RequestParam String nombreUsuario,
            @RequestParam String email, @RequestParam String contraseña,@RequestParam Imagen foto,@RequestParam String rol,@RequestParam (required=false) MultipartFile archivo,ModelMap model,RedirectAttributes redirectAttributes){
        
        try {
  
            if (rol.equalsIgnoreCase("cliente")) {

               
                clienteServicio.modificar(id, nombre, apellido, nombreUsuario, email, contraseña, archivo);
                

            } else {

              
             propietarioservicio.modificar(id, nombre, apellido, nombreUsuario, email, contraseña, archivo);
                
            }
            redirectAttributes.addFlashAttribute("exito", "¡Ha modificado con éxito!");
            return "redirect:../mainPage";
        } catch (MiException ex) {

            model.put("error", ex.getMessage());
            model.put("email", email);
            model.put("alias", apellido);
            model.put("rol", rol);


            return "formulario-modificar-usuario.html";
        }
          


        }
        
    
      @GetMapping("/eliminar/{id}")
    public String eliminarUsuario(@PathVariable String id, RedirectAttributes redirectAttributes){

        try {
            usuarioServicio.eliminarPorId(id);
            redirectAttributes.addFlashAttribute("exito", "¡El usuario ha sido " + "eliminado con éxito!");
        } catch (Exception error) {
            redirectAttributes.addFlashAttribute("error", error.getMessage());
        } finally {
            return "redirect:/usuario/listar";
        }
     
    }    
  
}

