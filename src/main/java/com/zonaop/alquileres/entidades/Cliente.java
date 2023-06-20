
package com.zonaop.alquileres.entidades;

//Entidad cliente que hereda de usuario

import java.util.List;
import javax.persistence.Entity;
//import javax.persistence.OneToMany;

@Entity
public class Cliente extends Usuario {
    
    //Un usuario puede tener muchas reservas
    //@OneToMany
    //private List <Reserva> reservas;

    public Cliente() {
        
    }

    //public List<Reserva> getReservas() {
    //    return reservas;
    //}

    //public void setReservas(List<Reserva> reservas) {
    //    this.reservas = reservas;
    //}
    
    
        
}
