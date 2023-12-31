package com.zonaop.alquileres.servicios;


import com.zonaop.alquileres.entidades.Usuario;
import com.zonaop.alquileres.excepciones.MiException;
import com.zonaop.alquileres.repositorios.UsuarioRepositorio;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


@Service
public class UsuarioServicio implements UserDetailsService{

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    
    public void validar(String nombre, String apellido,String nombreUsuario, String email, String contrasena) throws MiException {

    }

    public List<Usuario> listarUsuarios(){
        return usuarioRepositorio.findAll();
    }
    
    @Transactional
    public void eliminarPorId(String id){
        usuarioRepositorio.deleteById(id);
    }
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepositorio.buscarPorEmailUser(email);

        if (usuario != null && usuario.getEstado()) {

            List<GrantedAuthority> permisos = new ArrayList();
            GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" + usuario.getRol().toString());
            permisos.add(p);

            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpSession session = attr.getRequest().getSession(true);
            session.setAttribute("usuariosession", usuario);

            User user = new User(usuario.getEmail(), usuario.getContrasena(), permisos);
            return user;
        } else {
            return null;
        }
    }

    public Usuario getOne(String id){
        
        
        return usuarioRepositorio.getOne(id);
        
    }
    
    
}
