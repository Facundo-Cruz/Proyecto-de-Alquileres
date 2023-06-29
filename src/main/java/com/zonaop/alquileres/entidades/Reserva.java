/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaop.alquileres.entidades;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
public class Reserva {
   
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
 
  private String id;
  private String huesped;
  @OneToOne
  private Opinion opinion;
  @Temporal(TemporalType.DATE)
  private Date fechaHasta;
  @Temporal(TemporalType.DATE)
  private Date fechaDesde;
  @OneToOne
  private Propiedad propiedad;
  @OneToOne
  private Cliente cliente;
  @OneToMany
  private List <Servicio>servicios;
  private Double total;
  private boolean estado;
  
  
   public Reserva() {
      
   }

    public Reserva(String id, String huesped, Opinion opinion, Date fechaHasta, Date fechaDesde, Propiedad propiedad, Cliente cliente, List<Servicio> servicios, Double total) {
        this.id = id;
        this.huesped = huesped;
        this.opinion = opinion;
        this.fechaHasta = fechaHasta;
        this.fechaDesde = fechaDesde;
        this.propiedad = propiedad;
        this.cliente = cliente;
        this.servicios = servicios;
        this.total = total;
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

    public Opinion getOpinion() {
        return opinion;
    }

    public void setOpinion(Opinion opinion) {
        this.opinion = opinion;
    }

    public Date getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    public Date getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public Propiedad getPropiedad() {
        return propiedad;
    }

    public void setPropiedad(Propiedad propiedad) {
        this.propiedad = propiedad;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Servicio> getServicios() {
        return servicios;
    }

    public void setServicios(List<Servicio> servicios) {
        this.servicios = servicios;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }


    
    
    
    
}
