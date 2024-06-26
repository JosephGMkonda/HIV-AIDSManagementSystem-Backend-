package com.codebuddy.POJO;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serial;
import java.io.Serializable;

@NamedQuery(name="User.findByEmailId", query = "select u from User u where u.email=:email")

@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "user")
@Data


public class User implements Serializable {
    @Serial
    private  static  final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "id")
    private Integer Id;

    @Column( name = "name")
    private String name;

    @Column( name = "contactNumber")
    private String phoneNumber;

    @Column( name = "email")
    private String email;

    @Column( name = "password")
    private String password;

    @Column( name = "status")
    private String status;

    @Column( name = "role")
    private String role;






}
