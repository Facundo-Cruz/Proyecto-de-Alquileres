package com.zonaop.alquileres.entidades;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Opinion {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator( name = "uuid", strategy = "uuid2")
    private String id;
    private String huesped;
    @Lob
    private String comentario;
    @OneToMany
    private List<Imagen> fotos;
    private double calificacion;
    
    public Opinion() {
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

}
