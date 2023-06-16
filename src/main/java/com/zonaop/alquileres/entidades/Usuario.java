/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
//@Inheritance se utiliza para especificar la estrategia de herencia que se va a utilizar. En este caso, se utiliza InheritanceType.TABLE_PER_CLASS, que significa que cada clase concreta que herede de la entidad abstracta tendrá su propia tabla en la base de datos.
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Usuario {
    @Id
    //Genera id de forma automatica
    @GeneratedValue(generator = "uuid")
    //Segunda estrategia en caso de que el primer generador repita un id
    @GenericGenerator( name = "uuid", strategy = "uuid2")
    protected String id;
    @Basic
    protected String nombre;
    protected String apellido;
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
    
    
    
}
