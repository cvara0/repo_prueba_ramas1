package com.cvara.calc_precios.entidad;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "usuario", indexes = {@Index(name = "idx_nombre", columnList = "nombre")})
@Getter
@Setter
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nombre",length = 80,nullable = false)
    private String nombre;

    @Column(name = "email", length = 60, unique = true, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

}
