
package com.zonaop.alquileres.entidades;

//Entidad propietario que hereda de usuario

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Propietario extends Usuario{
    
    //Un propietario puede tener multiples propiedades
    @OneToMany
    private List <Propiedad> propiedades;

    public Propietario() {
        
    }

    public List<Propiedad> getPropiedades() {
        return propiedades;
    }

    public void setPropiedades(List<Propiedad> propiedades) {
        this.propiedades = propiedades;
    }
    
    
}
