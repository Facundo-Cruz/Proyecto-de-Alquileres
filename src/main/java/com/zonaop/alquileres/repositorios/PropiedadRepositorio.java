package com.zonaop.alquileres.repositorios;

import com.zonaop.alquileres.entidades.Propiedad;
import com.zonaop.alquileres.entidades.Servicio;
import com.zonaop.alquileres.enumeraciones.Localidad;
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

    @Query("SELECT p FROM Propiedad p WHERE "
        + "(:tipo IS NULL OR p.tipo = :tipo) "
        + "AND (:localidad IS NULL OR p.localidad = :localidad) "
        + "AND (:servicios IS NULL OR :servicios MEMBER OF p.servicios) "
        + "ORDER BY "
        + "    CASE WHEN :precio IS NOT NULL THEN CASE WHEN :precio = true THEN p.precio END ELSE NULL END ASC, "
        + "    CASE WHEN :precio IS NOT NULL THEN CASE WHEN :precio = false THEN p.precio END ELSE NULL END DESC, "
        + "    CASE WHEN :calificacion IS NOT NULL THEN p.calificacion END DESC") 
List<Propiedad> findByFilters(@Param("tipo") TipoPropiedad tipo,
        @Param("localidad") Localidad localidad,
        @Param("servicios") List<Servicio> servicios,
        @Param("precio") Boolean precio,
        @Param("calificacion") Boolean calificacion);
}

//    @Query("SELECT p FROM Propiedad p WHERE p.localidad = :localidad")
//    public List<Propiedad> buscarPorLocalidad(@Param("localidad") Localidad tipo);

