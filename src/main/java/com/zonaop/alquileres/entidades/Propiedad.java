package com.zonaop.alquileres.entidades;

import com.zonaop.alquileres.enumeraciones.TipoPropiedad;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Propiedad {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String nombre;
    private String ubicacion;
    @Temporal(TemporalType.DATE)
    private Date fechaDesde;
    @Temporal(TemporalType.DATE)
    private Date fechaHasta;
//    @OneToMany
//    private List<String> redesSociales;
//    @OneToMany
//    private List<String> telefonos;
//    @OneToMany
//    private List<String> mails;
    @OneToMany
    private List<Imagen> fotos;
    private double precio;
    private boolean estado;
    @Enumerated(EnumType.STRING)
    private TipoPropiedad tipo;
    private int calificacion;

    public Propiedad() {
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

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Date getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public Date getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

//    public List<String> getRedesSociales() {
//        return redesSociales;
//    }

//    public void setRedesSociales(List<String> redesSociales) {
//        this.redesSociales = redesSociales;
//    }
//
//    public List<String> getTelefonos() {
//        return telefonos;
//    }
//
//    public void setTelefonos(List<String> telefonos) {
//        this.telefonos = telefonos;
//    }
//
//    public List<String> getMails() {
//        return mails;
//    }
//
//    public void setMails(List<String> mails) {
//        this.mails = mails;
//    }

    public List<Imagen> getFotos() {
        return fotos;
    }

    public void setFotos(List<Imagen> fotos) {
        this.fotos = fotos;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public TipoPropiedad getTipo() {
        return tipo;
    }

    public void setTipo(TipoPropiedad tipo) {
        this.tipo = tipo;
    }
    
    
}
