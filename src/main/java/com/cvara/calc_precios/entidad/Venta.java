package com.cvara.calc_precios.entidad;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name="venta",indexes = {@Index(name="idx_precio_total",columnList = "precio_total")})
@Getter
@Setter
public class Venta {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name = "fecha",columnDefinition = "DATETIME")
    private LocalDate fecha;

    @Column(name = "anticipo",nullable = false)
    private Double anticipo;

    @Column(name = "precio_total",nullable = false)
    private Double precioTotal;

}
