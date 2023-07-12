package com.zonaop.alquileres.repositorios;

import com.zonaop.alquileres.entidades.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicioRepositorio extends JpaRepository<Servicio, String> {

//    @Query("SELECT s FROM Servicio s WHERE s.tp = :tp")
//    public List<Servicio> buscarPorTipo(@Param("tp") TipoServicio tp);

}
