/*

 */
package com.zonaop.alquileres.servicios;

import com.zonaop.alquileres.entidades.Imagen;
import com.zonaop.alquileres.entidades.Opinion;
import com.zonaop.alquileres.excepciones.MiException;
import com.zonaop.alquileres.repositorios.OpinionRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OpinionServicio {
    
    @Autowired 
    private  OpinionRepositorio opinionrepo;
    
  
    @Transactional
    public void crearOpinion(String id,String huesped,String comentario,int calificacion,List<Imagen>fotos) throws MiException{
    
        validarOpinion(id,huesped,comentario,calificacion,fotos);
    
        Optional<Opinion>opi=opinionrepo.findById(id);
        
        Opinion opinion= new Opinion();
        
        if(opi.isPresent()){
    
         opinion=opi.get();
    
    
      }
       
        opinion.setHuesped(huesped);
        opinion.setComentario(comentario);
        opinion.setCalificacion(calificacion);
        opinion.setFotos(fotos);
        
        
        opinionrepo.save(opinion);
        
    }   
    
    
    @Transactional(readOnly=true)
    public List<Opinion>listarOpiniones(){

        List<Opinion> op= new ArrayList();
        
        op=opinionrepo.findAll();
        
        return op;
        
     }
   
    public List<Opinion>listarOpinionesPorCalificacionDesc(){

        List<Opinion> opi= new ArrayList();
        
        opi=opinionrepo.findAllOrderByCalificacionDesc();
        
        return opi;
        
     }
    
    
    
    
    
    
    @Transactional
    public void modificarOpinion(String id,String huesped,String comentario,int calificacion,List<Imagen>fotos) throws MiException{
            
       
        validarOpinion(id,huesped,comentario,calificacion,fotos);
        
        
        Optional<Opinion>opi=opinionrepo.findById(id);
        
        Opinion o= new Opinion();
        
        if(opi.isPresent()){
    
         o=opi.get();
    
    
      }
            
        o.setHuesped(huesped);
        o.setComentario(comentario);
        o.setCalificacion(calificacion);
        o.setFotos(fotos);
        
        
        opinionrepo.save(o);
        
            
       }
    
    @Transactional
    public void eliminarOpinion(String id){
        
    Optional<Opinion> opiniones=opinionrepo.findById(id);
        
     if(opiniones.isPresent()){
    
     Opinion opinion=opiniones.get();
     
     opinionrepo.delete(opinion);
 
    }
      
   }
    
    
    
    @Transactional(readOnly = true)
    public Opinion getOne(String id){
    
       return opinionrepo.getOne(id);
        
        
    }
    
    
    
    
    
    private void validarOpinion(String id,String huesped,String comentario,int calificacion,List<Imagen>fotos) throws MiException{
    
    if(id==null){
        
        throw new MiException("el id no puede ser nulo");
        
    }
    
     if(huesped==null || huesped.isEmpty()){
        
        throw new MiException("el huesped no puede ser nulo o estar vacio");
        
    }
     
      if(comentario==null || comentario.isEmpty()){
        
        throw new MiException("el comentario no puede ser nulo o estar vacio");
        
    }
      
       if(calificacion==0){
        
        throw new MiException("la calificacion no puede ser cero");
        
    }
       
        if(fotos==null || fotos.isEmpty()){
        
        throw new MiException("las fotos no pueden ser nulas o vacias");
        
    }

    
}
    
    
    
}
