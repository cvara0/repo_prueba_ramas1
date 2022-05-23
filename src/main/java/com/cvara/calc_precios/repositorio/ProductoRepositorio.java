package com.cvara.calc_precios.repositorio;

import com.cvara.calc_precios.entidad.Producto;
import com.cvara.calc_precios.entidad.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductoRepositorio extends JpaRepository<Producto,Integer> {

    boolean existeProducto(String nombre);

    Optional<Usuario> buscarPorNombre(String nombre);

    @Query("SELECT p FROM Producto p WHERE p.id= :id")
    public Producto buscarPorId(@Param("id") Integer id);
}
