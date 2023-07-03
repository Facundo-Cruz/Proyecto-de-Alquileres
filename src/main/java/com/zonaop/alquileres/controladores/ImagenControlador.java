package com.zonaop.alquileres.controladores;

import com.zonaop.alquileres.entidades.Propiedad;
import com.zonaop.alquileres.entidades.Usuario;
import com.zonaop.alquileres.servicios.PropiedadServicio;
import com.zonaop.alquileres.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/imagen")

public class ImagenControlador {

    @Autowired
    private PropiedadServicio propiedadServicio;
    
    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> imagenPropiedad(@PathVariable String id) {
        Propiedad propiedad = propiedadServicio.buscarPropiedadPorId(id);

        byte[] imagen = propiedad.getFotos().get(0).getContenido();
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.IMAGE_JPEG);

        return new ResponseEntity<>(imagen, headers, HttpStatus.OK);
    }
    
    @GetMapping("/perfil/{id}")
    public ResponseEntity<byte[]> imagenUsuario(@PathVariable String id) {
        Usuario usuario = usuarioServicio.getOne(id);

        byte[] imagen = usuario.getFoto().getContenido();

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.IMAGE_JPEG);

        return new ResponseEntity<>(imagen, headers, HttpStatus.OK);
    }
}
