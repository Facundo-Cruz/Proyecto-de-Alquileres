/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaop.alquileres.repositorios;

import com.zonaop.alquileres.entidades.Servicio;
import com.zonaop.alquileres.enumeraciones.TipoServicio;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicioRepositorio extends JpaRepository<Servicio, String> {

//    @Query("SELECT s FROM Servicio s WHERE s.tp = :tp")
//    public List<Servicio> buscarPorTipo(@Param("tp") TipoServicio tp);

}
