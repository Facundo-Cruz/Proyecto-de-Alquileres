package com.zonaop.alquileres.servicios;


import com.zonaop.alquileres.entidades.Usuario;
import com.zonaop.alquileres.excepciones.MiException;
import com.zonaop.alquileres.repositorios.UsuarioRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


@Service
public class UsuarioServicio implements UserDetailsService{

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    public void validar(String nombre, String apellido,String nombreUsuario, String email, String contrasena) throws MiException {

    }

    public List<Usuario> listarUsuarios(){
        return usuarioRepositorio.findAll();
    }
    public List<Usuario> listarUsuariosPorNombre(String nombre){
        return usuarioRepositorio.buscarPorNombreUsuario(nombre);
    }
    
    @Transactional
    public void eliminarPorId(String id){
        usuarioRepositorio.deleteById(id);
    }
    
    @Transactional
    public void cambiarEstadoPorId(String id){
        Optional<Usuario> respuesta = usuarioRepositorio.findById(id);
        
        if (respuesta.isPresent()) {
            Usuario usuario = respuesta.get();
            usuario.setEstado(!usuario.getEstado());
            usuarioRepositorio.save(usuario);
        }
    }
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepositorio.buscarPorEmailUser(email);
        System.out.println(usuario.getEmail());
        String encodedPassword = usuario.getContrasena();

            if (bCryptPasswordEncoder.matches("123123A!", encodedPassword)) {
                System.out.println("COINCIDEN");

                usuarioRepositorio.save(usuario);
            } else {
                System.out.println("no COINCIDEN");
            }
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

    
     @Transactional(readOnly = true)
    public Usuario getOne(String id){
     
        return usuarioRepositorio.getOne(id);
        
    }
    
    
}
