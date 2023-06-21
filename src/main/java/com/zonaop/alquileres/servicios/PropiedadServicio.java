package com.zonaop.alquileres.servicios;

import com.zonaop.alquileres.entidades.Propiedad;
import com.zonaop.alquileres.repositorios.PropiedadRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PropiedadServicio {
    
    @Autowired 
    private PropiedadRepositorio propiedadRepositorio;
    
    public List<Propiedad> listarPropiedadPorTipo(String tipo){
        List<Propiedad> propiedades = propiedadRepositorio.buscarPorTipo(tipo);
        return propiedades;
    }
    
    public List<Propiedad> listarPropiedades(){
        List<Propiedad> propiedades = propiedadRepositorio.findAll();
        return propiedades;
    }
}
