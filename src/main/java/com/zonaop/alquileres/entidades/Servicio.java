/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaop.alquileres.entidades;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;


@Entity
public class Servicio {
    
       

    @Id
    private String id;
    private String nombre;
    private String descripcion;
    private boolean limpieza;
    private boolean desayuno;
    private boolean transporte;
    private boolean accesoPiscina;
    @OneToOne
    private double precio;
    @OneToOne
    private boolean disponibilidad;
    

    public Servicio() {
    }

    public Servicio(String id, String nombre, String descripcion, boolean Limpieza, boolean Desayuno, boolean Transporte, boolean accesoPiscina, double precio, boolean disponibilidad) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.limpieza = Limpieza;
        this.desayuno = Desayuno;
        this.transporte = Transporte;
        this.accesoPiscina = accesoPiscina;
        this.precio = precio;
        this.disponibilidad = disponibilidad;
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

    public boolean isLimpieza() {
        return limpieza;
    }

    public void setLimpieza(boolean limpieza) {
        this.limpieza = limpieza;
    }

    public boolean isDesayuno() {
        return desayuno;
    }

    public void setDesayuno(boolean desayuno) {
        this.desayuno = desayuno;
    }

    public boolean isTransporte() {
        return transporte;
    }

    public void setTransporte(boolean transporte) {
        this.transporte = transporte;
    }

    public boolean isAccesoPiscina() {
        return accesoPiscina;
    }

    public void setAccesoPiscina(boolean accesoPiscina) {
        this.accesoPiscina = accesoPiscina;
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
        return "Servicio{" + "id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", limpieza=" + limpieza + ", desayuno=" + desayuno + ", transporte=" + transporte + ", accesoPiscina=" + accesoPiscina + ", precio=" + precio + ", disponibilidad=" + disponibilidad + '}';
    }


    
    
    
}
