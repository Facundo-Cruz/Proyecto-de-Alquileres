/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaop.alquileres.entidades;

import com.zonaop.alquileres.enumeraciones.TipoServicio;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;


@Entity
public class Servicio {
//    
//       
//
    @Id
    private String id;
    private String nombre;
    private String descripcion;
    private TipoServicio tp;
    @OneToOne
    private double precio;
    @OneToOne
    private boolean disponibilidad;
    

    public Servicio() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public boolean isDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    @Override
    public String toString() {
        return "Servicio{" + "id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", tp=" + tp + ", precio=" + precio + ", disponibilidad=" + disponibilidad + '}';
    }
    
    

   
    
    
}
