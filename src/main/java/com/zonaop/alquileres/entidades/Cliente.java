package com.zonaop.alquileres.entidades;


import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

//Entidad cliente que hereda de usuario
@Entity
public class Cliente extends Usuario {

    //Un usuario puede tener muchas reservas
    @OneToMany
    private List<Reserva> reservas;

    public Cliente() {

    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

}
