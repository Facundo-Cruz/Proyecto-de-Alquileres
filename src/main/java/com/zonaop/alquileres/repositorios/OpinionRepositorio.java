/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaop.alquileres.repositorios;

import com.zonaop.alquileres.entidades.Opinion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OpinionRepositorio extends JpaRepository<Opinion, String> {
    
    
    @Query(value="Select * from Opinion where calificacion order by desc" , nativeQuery=true)
    List<Opinion>findAllOrderByidDesc();
    
}
