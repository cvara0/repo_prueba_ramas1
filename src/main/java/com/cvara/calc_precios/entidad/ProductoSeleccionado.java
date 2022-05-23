package com.cvara.calc_precios.entidad;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

public class ProductoSeleccionado {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nombre",length = 80,nullable = false)
    private String nombre;

    @Column(name = "precio",nullable = false)
    private Double precio;

    @Column(name = "cantidad",columnDefinition = "SMALLINT",nullable = false)
    private Short cantidad;

}
