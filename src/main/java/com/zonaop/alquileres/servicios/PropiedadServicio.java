package com.zonaop.alquileres.servicios;

import com.zonaop.alquileres.entidades.Imagen;
import com.zonaop.alquileres.entidades.Propiedad;
import com.zonaop.alquileres.excepciones.MiException;
import com.zonaop.alquileres.repositorios.PropiedadRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PropiedadServicio {
    
    @Autowired 
    public PropiedadRepositorio propiedadRepositorio;
    
    @Autowired
    public ImagenServicio imagenServicio;
    

    @Transactional()
    public void crearPropiedad(MultipartFile archivo) throws MiException{
        
        Propiedad propiedad=new Propiedad();
        
         Imagen imagen = imagenServicio.guardar(archivo);

        propiedad.setFoto(imagen);
        
        propiedadRepositorio.save(propiedad);
        
    }
    public List<Propiedad> listarPropiedadPorTipo(String tipo){
        List<Propiedad> propiedades = propiedadRepositorio.buscarPorTipo(tipo);
        return propiedades;
    }
    
    public List<Propiedad> listarPropiedades(){
        List<Propiedad> propiedades = propiedadRepositorio.findAll();
        return propiedades;
    }

}
