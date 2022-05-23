package com.cvara.calc_precios.repositorio;

import com.cvara.calc_precios.entidad.Cliente;
import com.cvara.calc_precios.entidad.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente,Integer> {

    boolean existeCliente(String nombre);

    Optional<Cliente> buscarPorNombre(String nombre);

    @Query("SELECT c FROM Cliente c WHERE c.id= :id")
    public Cliente buscarPorId(@Param("id") Integer id);
}
