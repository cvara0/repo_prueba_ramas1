package com.cvara.calc_precios.repositorio;

import com.cvara.calc_precios.entidad.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VentaRepositorio extends JpaRepository<Venta,Integer> {

}
