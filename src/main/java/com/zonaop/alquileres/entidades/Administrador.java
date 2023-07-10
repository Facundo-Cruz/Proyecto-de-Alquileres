package com.zonaop.alquileres.entidades;

import javax.persistence.Entity;

@Entity
public class Administrador extends Usuario {

    public Administrador() {
    }
    //Lista de reportes de clientes o propietarios para comentar algun fallo, o mal comportamiento de algun usuario para solicitar la baja del perfil.
    //@OneToMany
    //private List <Reporte> reportes;
    
    
}
