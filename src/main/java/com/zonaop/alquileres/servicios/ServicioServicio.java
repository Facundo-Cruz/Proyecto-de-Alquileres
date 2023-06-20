/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaop.alquileres.servicios;


import com.zonaop.alquileres.entidades.Servicio;
import com.zonaop.alquileres.excepciones.MiException;
import com.zonaop.alquileres.repositorios.ServicioRepositorio;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author JOAQUIN
 */


@Service
public class ServicioServicio {
    
    @Autowired
    private ServicioRepositorio serviciorepo;
    
    @Transactional
    public void crearServicio(String id,String nombre,String descripcion,Double precio,boolean disponibilidad,boolean limpieza,boolean desayuno,boolean accesoPiscina,boolean transporte) throws MiException{
    
      validarServicio(id,nombre,descripcion,precio,disponibilidad,limpieza,desayuno,accesoPiscina,transporte);  
        
        
    Optional<Servicio>servi=serviciorepo.findById(id);
        
        
    Servicio servicio= new Servicio();
    
    servicio.setNombre(nombre);
    servicio.setDescripcion(descripcion);
    servicio.setPrecio(0);
    servicio.setDisponibilidad(disponibilidad);
    servicio.setAccesoPiscina(accesoPiscina);
    servicio.setDesayuno(desayuno);
    servicio.setLimpieza(limpieza);
    servicio.setTransporte(transporte);
    
    serviciorepo.save(servicio);
    
    };
    

   @Transactional
   public void modificarServicio(String id,String nombre,String descripcion,Double precio,boolean disponibilidad,boolean limpieza,boolean desayuno,boolean accesoPiscina,boolean transporte) throws MiException{
   
    validarServicio(id,nombre,descripcion,precio,disponibilidad,limpieza,desayuno,accesoPiscina,transporte);  
       
       
    Optional<Servicio>servi=serviciorepo.findById(id);
    
    if(servi.isPresent()){
    
        Servicio servicio=servi.get();
        
        servicio.setNombre(nombre);
        servicio.setDescripcion(descripcion);
        servicio.setPrecio(0);
        servicio.setDisponibilidad(disponibilidad);
        servicio.setAccesoPiscina(accesoPiscina);
        servicio.setDesayuno(desayuno);
        servicio.setTransporte(transporte);
        servicio.setLimpieza(limpieza);
        
        serviciorepo.save(servicio);
    
    };
    
   }
   
   @Transactional
    public void eliminarServicio(String id){
        
        Optional<Servicio>ser=serviciorepo.findById(id);
        
        
      if(ser.isPresent()){
        
          Servicio servicio=ser.get();
          
          serviciorepo.delete(servicio);
      
      }
   
    };
    
    @Transactional(readOnly = true)
    public Servicio getOne(String id){
    
       return serviciorepo.getOne(id);
        
        
    }
  
    private void validarServicio(String id,String nombre,String descripcion,Double precio,boolean disponibilidad,boolean limpieza,boolean desayuno,boolean accesoPiscina,boolean transporte)  throws MiException{
        
        
        
     if(id==null){
        throw new MiException("el id no puede ser nulo");
   
    }
        
    if(nombre==null || nombre.isEmpty()){
        throw new MiException("el nombre no puede ser nulo o estar vacio");
   
    }

   if(descripcion==null || descripcion.isEmpty()){
        throw new MiException("la descripcion no puede ser nula o estar vacia");
   
    }

    if(precio==0){
        throw new MiException("el precio no puede ser cero");
   
    }
 
    if(disponibilidad==true || disponibilidad ==false){
    
       throw new MiException("la disponibilidad del servicio puede ser verdadera o falsa");
    
    };
    
    
 
    if(transporte==true || transporte==false){
    
       throw new MiException("el transporte puede ser verdadero o falso");
    
    };
    
     if(limpieza==true || limpieza==false){
    
       throw new MiException("el transporte puede ser verdadero o falso");
    
    };
    
     if(accesoPiscina==true || accesoPiscina==false){
    
       throw new MiException("el transporte puede ser verdadero o falso");
    
    };
    
     if(desayuno==true || desayuno==false){
    
       throw new MiException("el transporte puede ser verdadero o falso");
    
    };
    
  
    
  } 
    
    
}
