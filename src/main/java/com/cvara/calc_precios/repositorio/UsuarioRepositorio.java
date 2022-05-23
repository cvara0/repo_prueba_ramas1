package com.cvara.calc_precios.repositorio;

import com.cvara.calc_precios.entidad.Producto;
import com.cvara.calc_precios.entidad.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario,Integer> {

    boolean existeEmail(String email);

    Optional<Usuario> buscarPorEmail(String email);

    @Query("SELECT u FROM Usuario u WHERE u.email= :email")
    public Producto buscarPorId(@Param("email") String email);
}
