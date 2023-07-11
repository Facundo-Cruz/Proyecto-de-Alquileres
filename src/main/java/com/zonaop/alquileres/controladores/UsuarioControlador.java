package com.zonaop.alquileres.controladores;

import com.zonaop.alquileres.entidades.Cliente;
import com.zonaop.alquileres.entidades.Imagen;
import com.zonaop.alquileres.entidades.Propiedad;
import com.zonaop.alquileres.entidades.Reserva;
import com.zonaop.alquileres.entidades.Usuario;
import com.zonaop.alquileres.enumeraciones.Rol;
import com.zonaop.alquileres.enumeraciones.TipoPropiedad;
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
    public PropiedadServicio propiedadServicio;

    @Autowired
    public ReservaServicio reservaServicio;

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

    @PostMapping("/listar/nombres")
    public String listarUsuariosPorNombre(@RequestParam String nombre, ModelMap model) {

        List<Usuario> usuarios = usuarioServicio.listarUsuariosPorNombre(nombre);
        model.put("usuarios", usuarios);

        return "lista-usuarios.html";

    }

    @GetMapping("/perfil")
    public String mostrarPerfil(HttpSession session, ModelMap modelo) {

        Usuario usuario = (Usuario) session.getAttribute("usuariosession");
        Usuario perfil;
        List<Reserva> reservasActivas;
        List<Reserva> reservasPendientes;
        List<Reserva> reservasCanceladas;
        List<Reserva> reservasFinalizadas;
        if (usuario.getRol().name().equals("PROPIETARIO")) {

            perfil = propietarioServicio.getOne(usuario.getId());

            List<Propiedad> propiedades = propiedadServicio.listarPorPropietario(usuario.getId());

            reservasActivas = reservaServicio.listarPorPropietarioActiva(usuario.getId());
            reservasPendientes = reservaServicio.listarPorPropietarioPendiente(usuario.getId());
            reservasCanceladas = reservaServicio.listarPorPropietarioCancelada(usuario.getId());
            reservasFinalizadas = reservaServicio.listarPorPropietarioFinalizada(usuario.getId());
            modelo.put("propiedades", propiedades);

        } else {

            perfil = clienteServicio.getOne(usuario.getId());
//            reservas activas
            reservasActivas = reservaServicio.listarPorClienteActiva(usuario.getId());
            reservasPendientes = reservaServicio.listarPorClientePendiente(usuario.getId());
            reservasCanceladas = reservaServicio.listarPorClienteCancelada(usuario.getId());
            reservasFinalizadas = reservaServicio.listarPorClienteFinalizada(usuario.getId());

        }

        modelo.put("reservasActivas", reservasActivas);
        modelo.put("reservasPendientes", reservasPendientes);
        modelo.put("reservasCanceladas", reservasCanceladas);
        modelo.put("reservasFinalizadas", reservasFinalizadas);

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
    public String modificarUsuario(String id, String nombre, String apellido,
            String nombreUsuario, String email, Long telefono, String password, Imagen foto,
            String rol, ModelMap modelo,RedirectAttributes redirectAttributes, String passwordActual) {

        try {

            if (rol.equalsIgnoreCase("cliente")) {

                clienteServicio.modificar(id, nombre, apellido, nombreUsuario, email, telefono, password, passwordActual);

            } else {

                propietarioServicio.modificar(id, nombre, apellido, nombreUsuario, email, telefono, password, passwordActual);

            }
            redirectAttributes.addFlashAttribute("exito", "¡Ha modificado con éxito!");
            return "redirect:/usuario/perfil";
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("error", ex.getMessage());
//            redirectAttributes.addFlashAttribute("email", email);
//            redirectAttributes.addFlashAttribute("alias", apellido);
//            redirectAttributes.addFlashAttribute("rol", rol);
            return "redirect:/usuario/modificar";

        }

    }

    @PostMapping("/modificarImagen")
    public String modificarImagen(String id, String rol, MultipartFile archivo, RedirectAttributes redirectAttributes) {
        try {

            if (rol.equalsIgnoreCase("cliente")) {

                clienteServicio.modificarImagen(id, archivo);

            } else {

                propietarioServicio.modificarImagen(id, archivo);

            }
            redirectAttributes.addFlashAttribute("exito", "¡Ha modificado con éxito!");
            return "redirect:/usuario/perfil";
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("error", "La imagen no se ha modificado :c");

            return "redirect:/usuario/perfil";

        }
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarUsuario(@PathVariable String id, RedirectAttributes redirectAttributes) {

        try {
            reservaServicio.eliminarReservasDeCliente(id);
            usuarioServicio.eliminarPorId(id);
            redirectAttributes.addFlashAttribute("exito", "¡El usuario ha sido "
                    + "eliminado con éxito!");
        } catch (Exception error) {
            redirectAttributes.addFlashAttribute("error", error.getMessage());
        } finally {
            return "redirect:/usuario/listar";
        }

    }

    @GetMapping("/cambiarEstado/{id}")
    public String cambiarEstadoUsuario(@PathVariable String id, RedirectAttributes redirectAttributes) {

        try {
            usuarioServicio.cambiarEstadoPorId(id);

        } catch (Exception error) {
            redirectAttributes.addFlashAttribute("error", error.getMessage());
        } finally {
            return "redirect:/usuario/listar";
        }

    }

}
