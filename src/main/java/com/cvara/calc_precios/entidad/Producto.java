package com.cvara.calc_precios.entidad;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name="producto",indexes = {@Index(name="idx_nombre",columnList = "nombre")})
@Getter
@Setter
public class Producto {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nombre",length = 80,nullable = false)
    private String nombre;

    @Column(name = "precio",nullable = false)
    private Double precio;

}
