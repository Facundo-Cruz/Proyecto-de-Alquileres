package com.zonaop.alquileres.repositorios;

import com.zonaop.alquileres.entidades.Propiedad;
import com.zonaop.alquileres.entidades.Servicio;
import com.zonaop.alquileres.enumeraciones.Localidad;
import com.zonaop.alquileres.enumeraciones.TipoPropiedad;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.Predicate;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
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
    
     List<Propiedad> findAll(Specification<Propiedad> specification, Sort sort);

    default List<Propiedad> findByFilters(TipoPropiedad tipo, Localidad localidad, List<Servicio> servicios, Sort sort) {
        return findAll((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (tipo != null) {
                predicates.add(criteriaBuilder.equal(root.get("tipo"), tipo));
            }

            if (localidad != null) {
                predicates.add(criteriaBuilder.equal(root.get("localidad"), localidad));
            }

            if (servicios != null && !servicios.isEmpty()) {
                predicates.add(root.get("servicio").in(servicios));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        }, sort);
    }
    

}
    
//    @Query("SELECT p FROM Propiedad p WHERE p.localidad = :localidad")
//    public List<Propiedad> buscarPorLocalidad(@Param("localidad") Localidad tipo);

