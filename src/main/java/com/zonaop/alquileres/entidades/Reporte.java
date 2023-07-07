package com.zonaop.alquileres.entidades;

import com.zonaop.alquileres.enumeraciones.TipoReporte;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Reporte {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator( name = "uuid", strategy = "uuid2")
    private String id;
    @ManyToOne
    private Cliente cliente;
    @OneToOne
    @JoinColumn(name="opinion_id")
    private Opinion opinion;
    @ManyToOne
    @JoinColumn(name="propiedad_id")
    private Propiedad propiedad;
    private String texto;
    @OneToOne
    private Imagen foto;
    @Temporal(TemporalType.DATE)
    private Date fechaHasta;
    private String idGenerico;
    private Boolean estadoAviso;
    private String aviso;
    
    @Enumerated (EnumType.STRING)
    private TipoReporte tipoReporte;

    public Reporte() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Opinion getOpinion() {
        return opinion;
    }

    public void setOpinion(Opinion opinion) {
        this.opinion = opinion;
    }

    public Propiedad getPropiedad() {
        return propiedad;
    }

    public void setPropiedad(Propiedad propiedad) {
        this.propiedad = propiedad;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Imagen getFoto() {
        return foto;
    }

    public void setFoto(Imagen foto) {
        this.foto = foto;
    }

    public Date getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    public String getIdGenerico() {
        return idGenerico;
    }

    public void setIdGenerico(String idGenerico) {
        this.idGenerico = idGenerico;
    }

    public Boolean getEstadoAviso() {
        return estadoAviso;
    }

    public void setEstadoAviso(Boolean estadoAviso) {
        this.estadoAviso = estadoAviso;
    }
    

    public TipoReporte getTipoReporte() {
        return tipoReporte;
    }

    public void setTipoReporte(TipoReporte tipoReporte) {
        this.tipoReporte = tipoReporte;
    }

    public String getAviso() {
        return aviso;
    }

    public void setAviso(String aviso) {
        this.aviso = aviso;
    }   
       
}
