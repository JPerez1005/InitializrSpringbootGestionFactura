package com.api.gestion.api_gestion_factura.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * @author perez
 */

//Lo siguiente es una consulta de JPA
//busca el usuario a partir de la direccion de correo electronico
//esto me permite reutilizar esa consulta cuando yo quiera
@NamedQuery(name="User.findByEmail",query="select u from User u where u.email=:email")
@Data
@Entity
@DynamicUpdate//solo actualiza las columnas que cambiaron
@DynamicInsert//solo inserta las columnas que no son nulas
@Table(name="users")
public class User{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name="nombre")
    private String nombre;
    
    @Column(name="numeroDeContacto")
    private String numeroDeContacto;
    
    @Column(name="email")
    private String email;
    
    @Column(name="password")
    private String password;
    
    @Column(name="status")
    private String status;
    
    @Column(name="role")
    private String role;
    
}
