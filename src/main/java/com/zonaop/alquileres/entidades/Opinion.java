/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaop.alquileres.entidades;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Opinion {
    
    
    @Id
    private String id;
    private String huesped;
    private String comentario;
    @OneToMany
    private List<Imagen> fotos;
    private double calificacion;
    
    
    public Opinion() {
    }

    public Opinion(String id, String huesped, String comentario, List<Imagen> fotos, double calificacion) {
        this.id = id;
        this.huesped = huesped;
        this.comentario = comentario;
        this.fotos = fotos;
        this.calificacion = calificacion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHuesped() {
        return huesped;
    }

    public void setHuesped(String huesped) {
        this.huesped = huesped;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public List<Imagen> getFotos() {
        return fotos;
    }

    public void setFotos(List<Imagen> fotos) {
        this.fotos = fotos;
    }

    public double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(double calificacion) {
        this.calificacion = calificacion;
    }

    @Override
    public String toString() {
        return "Opinion{" + "id=" + id + ", huesped=" + huesped + ", comentario=" + comentario + ", fotos=" + fotos + ", calificacion=" + calificacion + '}';
    }
    

    
    
}
