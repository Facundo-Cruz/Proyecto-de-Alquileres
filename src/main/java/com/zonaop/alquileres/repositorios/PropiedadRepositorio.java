package com.zonaop.alquileres.repositorios;

import com.zonaop.alquileres.entidades.Propiedad;
import com.zonaop.alquileres.enumeraciones.TipoPropiedad;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PropiedadRepositorio extends JpaRepository<Propiedad, String> {

    @Query("SELECT p FROM Propiedad p WHERE p.tipo = :tipo")
    public List<Propiedad> buscarPorTipo(@Param("tipo") TipoPropiedad tipo);

    @Query("SELECT p FROM Propiedad p WHERE p.propietario.id = :propietarioId")
    public List<Propiedad> buscarPorPropietario(@Param("propietarioId") String propietarioId);
    
//    @Query("SELECT p FROM Propiedad p WHERE p.localidad = :localidad")
//    public List<Propiedad> buscarPorLocalidad(@Param("localidad") Localidad tipo);

}
