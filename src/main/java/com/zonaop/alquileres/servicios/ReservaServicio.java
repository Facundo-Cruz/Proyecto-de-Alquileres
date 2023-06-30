/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaop.alquileres.servicios;

import com.zonaop.alquileres.entidades.Cliente;
import com.zonaop.alquileres.entidades.Opinion;
import com.zonaop.alquileres.entidades.Propiedad;
import com.zonaop.alquileres.entidades.Reserva;
import com.zonaop.alquileres.entidades.Servicio;
import com.zonaop.alquileres.excepciones.MiException;
import com.zonaop.alquileres.repositorios.ClienteRepositorio;
import com.zonaop.alquileres.repositorios.OpinionRepositorio;
import com.zonaop.alquileres.repositorios.PropiedadRepositorio;
import com.zonaop.alquileres.repositorios.ReservaRepositorio;
import com.zonaop.alquileres.repositorios.ServicioRepositorio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ReservaServicio {
    
    
    @Autowired 
    private  ReservaRepositorio reservarepo;
    
    @Autowired 
    private  PropiedadRepositorio propiedadrepo;
    
    @Autowired 
    private  ClienteRepositorio clienterepo;
    
    @Autowired 
    private  OpinionRepositorio opinionrepo;
    
    @Autowired
    private ServicioRepositorio serviciorepo;
    
    
    
    @Transactional
    public void crearReserva(String id,String huesped,List<Servicio>servicios,Double total, String idOpinion,String idPropiedad,String idCliente,String idServicio) throws MiException{
        
        
       validarReserva(id,huesped,servicios,total,idOpinion,idCliente,idPropiedad,idServicio);
        
        
       Optional<Reserva> a=reservarepo.findById(id);
       Optional<Opinion> opi=opinionrepo.findById(idOpinion);
       Optional<Cliente> ab=clienterepo.findById(idCliente);
       Optional<Propiedad> ac=propiedadrepo.findById(idPropiedad);
       Optional<Servicio> ad=serviciorepo.findById(idServicio);
        
        
        Cliente cliente= new Cliente();
        Opinion opinion=new Opinion();
        Propiedad propiedad= new Propiedad();
        Servicio servicio=new Servicio();
       
        
        if(ab.isPresent()){
          
            cliente=ab.get();
           
        }
        
        
        if(ac.isPresent()){
    
          propiedad=ac.get();
            
        }
        
        
        if(opi.isPresent()){
       
            opinion=opi.get();
     
       }
        
        
        if(ad.isPresent()){
      
            servicio=ad.get();
    
         }
        
        
        Reserva reserva= new Reserva();
        
        reserva.setHuesped(huesped);
        reserva.setCliente(cliente);
        reserva.setFechaDesde(new Date());
        reserva.setFechaHasta(new Date());
        reserva.setOpinion(opinion);
        reserva.setPropiedad(propiedad);
        reserva.setServicios(servicios);
        reserva.setTotal(total);
        
        reservarepo.save(reserva);
        
        
    }
    
    
    @Transactional(readOnly=true)
    public List<Reserva>listarReservas(){
        
        List<Reserva>Reservas= new ArrayList();
        
        Reservas=reservarepo.findAll();
        
        return Reservas;
   
    }
    
    
    
    public List<Reserva>listarReservaDesc(){
    
    List<Reserva> r= new ArrayList();
    r=reservarepo.findAllOrderByidDesc();
    
    return r;
    
   }
    
    
    @Transactional
    public void modificarReserva(String id,String huesped,List<Servicio>servicios,Double total, String idOpinion,String idPropiedad,String idCliente,String idServicio) throws MiException{
        
       
        validarReserva(id,huesped,servicios,total,idOpinion,idCliente,idPropiedad,idServicio);
        
        
        Optional<Reserva> a=reservarepo.findById(id);
        Optional<Propiedad> b=propiedadrepo.findById(idPropiedad);
        Optional<Cliente> c=clienterepo.findById(idCliente);
        Optional<Opinion>d=opinionrepo.findById(idOpinion);
        Optional<Servicio>e=serviciorepo.findById(idServicio);
        
       
        
        Cliente cliente= new Cliente();
        Opinion opinion=new Opinion();
        Propiedad propiedad= new Propiedad();
        Servicio servicio=new Servicio();
        
        
        if (a.isPresent()){
            
            Reserva reserva=a.get();
           
            reserva.setHuesped(huesped);
            reserva.setCliente(cliente);
            reserva.setServicios((List<Servicio>) servicios);
            reserva.setTotal(total);
            
            reservarepo.save(reserva);
            
        }
        
        
    }
    
    @Transactional
    public void cancelarPorId(String id){
        Optional<Reserva> respuesta = reservarepo.findById(id);
        
        if (respuesta.isPresent()) {
            Reserva reserva = respuesta.get();
            reserva.setEstado(false);
            reservarepo.save(reserva);
        }
    }
    
    @Transactional
    public void EliminarReserva(String id) throws MiException{
        
     Optional<Reserva> r=reservarepo.findById(id);
        if(r.isPresent()){
       
        Reserva reserva=r.get();
      
        reservarepo.delete(reserva);
        }
  
      }
    
     @Transactional(readOnly = true)
    public Reserva getOne(String id){
    
       return reservarepo.getOne(id);
        
        
    }
    
    
    
    private void validarReserva (String id,String huesped,List<Servicio>servicios,Double total, String idOpinion,String idPropiedad,String idCliente,String idServicio) throws MiException{
       
    
    
                
       if (id == null) {
           throw new MiException("el id no puede ser nulo"); //
       }


        if (huesped.isEmpty() || huesped == null) {
           throw new MiException("el huesped no puede ser nulo o estar vacio");
        }
        
        if (servicios == null) {
            throw new MiException("los servicios no pueden ser nulos");
        }
        
       if (idOpinion.isEmpty() || idOpinion == null) {
            throw new MiException("la opinion no puede ser nula o estar vacia");
        }

     
       if (idCliente.isEmpty() || idCliente == null) {
           
        throw new MiException("el cliente no puede ser nulo o estar vacio");

        }
       
       if(total.isInfinite() || total==null){
    
        throw new MiException("el total no puede ser infinito o ser vacio");    
    
    }
       
       
       if(idServicio.isEmpty() || idServicio==null){

       throw new MiException("los servicios no pueden ser nulos o estar vacios");

     }

       
       if(idPropiedad.isEmpty() || idPropiedad==null){
    
    
        throw new MiException("la propiedad no puede ser nula o estar vacia");
    
    
     }
        
        
    
    }
   
   
            
    
}
