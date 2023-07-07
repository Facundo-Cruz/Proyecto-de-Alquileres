
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
    
    @OneToMany
    private List<Reserva> reservas;

    public Propietario() {
        
    }

    public List<Propiedad> getPropiedades() {
        return propiedades;
    }

    public void setPropiedades(List<Propiedad> propiedades) {
        this.propiedades = propiedades;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }
    
    
    
}
