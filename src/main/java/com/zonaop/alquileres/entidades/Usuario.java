
package com.zonaop.alquileres.entidades;

import com.zonaop.alquileres.enumeraciones.Rol;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import org.hibernate.annotations.GenericGenerator;

//Anotacion para persistir la entidad
@Entity
//Anotacón para decir cual es la estrategia de la herencia y que se cree tambien una tabla de usuarioen la base de datos.
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Usuario {
    @Id
    //Genera id de forma automatica
    @GeneratedValue(generator = "uuid")
    //Segunda estrategia en caso de que el primer generador repita un id
    @GenericGenerator( name = "uuid", strategy = "uuid2")
    protected String id;
    @Basic
    protected String nombre;
    protected String apellido;
    protected String nombreUsuario;
    protected Long telefono;
    protected String email;
    protected String contrasena;
    protected Boolean estado;
    
    //El usuario posee una imagen de perfil
    @OneToOne
    protected Imagen foto;
    
    @Enumerated(EnumType.STRING)
    protected Rol rol;

    public Usuario() {
    }
    
    //Getters y Setters que utilizaran las hijas a la hora de recibir datos mediante el formulario
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Long getTelefono() {
        return telefono;
    }

    public void setTelefono(Long telefono) {
        this.telefono = telefono;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Imagen getFoto() {
        return foto;
    }

    public void setFoto(Imagen foto) {
        this.foto = foto;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
    
    
    
}
