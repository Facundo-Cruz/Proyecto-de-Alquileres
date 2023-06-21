/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaop.alquileres.repositorios;

import com.zonaop.alquileres.entidades.Reserva;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



@Repository
public interface ReservaRepositorio extends JpaRepository<Reserva, String> {
    
    
    @Query(value="Select * from reserva  where id order by desc" , nativeQuery=true)
    List<Reserva>findAllOrderByidDesc();
    
//    @Query("SELECT r FROM Reserva l WHERE r.huesped = :huesped")
//    public Reserva buscarPorHuesped(@Param("titulo") String huesped);
//    
//    
    
    
}
