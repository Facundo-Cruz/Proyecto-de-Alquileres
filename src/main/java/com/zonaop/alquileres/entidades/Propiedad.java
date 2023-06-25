package com.zonaop.alquileres.entidades;

import com.zonaop.alquileres.enumeraciones.TipoPropiedad;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
    private String direccion;
    private String localidad;
    private String codigoPostal;
    private String descripcion;
    @OneToOne
    //Especifica que una propiedad necesita si osi de un propietario para ser persistida
    @JoinColumn(name = "propietario_id")
    private Propietario propietario;
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
    @OneToOne
    private Imagen foto;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public Imagen getFoto() {
        return foto;
    }

    public void setFoto(Imagen foto) {
        this.foto = foto;
    }

    public Propietario getPropietario() {
        return propietario;
    }

    public void setPropietario(Propietario propietario) {
        this.propietario = propietario;
    }
    
    
    
}
