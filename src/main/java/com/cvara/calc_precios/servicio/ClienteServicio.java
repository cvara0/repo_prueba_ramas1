package com.cvara.calc_precios.servicio;

import com.cvara.calc_precios.entidad.Cliente;
import com.cvara.calc_precios.entidad.Producto;
import com.cvara.calc_precios.error.ErrorServicio;
import com.cvara.calc_precios.repositorio.ClienteRepositorio;
import com.cvara.calc_precios.repositorio.ProductoRepositorio;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteServicio {

    private final ClienteRepositorio clienteRepositorio;

    @Transactional
    public void crear(Cliente dto) throws ErrorServicio {
        if (clienteRepositorio.existeCliente(dto.getNombre()))
            throw new ErrorServicio("Ya existe cliente");
        if (dto.getNombre()==null || dto.getNombre().isEmpty()){
            throw new ErrorServicio("Nombre de cliente no puede ser nulo");
        }
        Cliente cliente = new Cliente();
        cliente.setNombre(dto.getNombre());
        clienteRepositorio.save(cliente);
    }

    @Transactional
    public void eliminarPorId(Integer id) {
        clienteRepositorio.deleteById(id);
    }

    @Transactional
    public void modificar(Cliente dto) throws ErrorServicio{
        if (clienteRepositorio.existeCliente(dto.getNombre()))
            throw new ErrorServicio("Ya existe cliente");
        if (dto.getNombre()==null || dto.getNombre().isEmpty()){
            throw new ErrorServicio("Nombre de cliente no puede ser nulo");
        }
        Optional<Cliente> respuesta=clienteRepositorio.findById(dto.getId());

        if(respuesta.isPresent()){
            Cliente cliente = new Cliente();
            cliente.setNombre(dto.getNombre());
            clienteRepositorio.save(cliente);
        }else {
            throw new ErrorServicio("No se encontro cliente con dicho id");
        }
    }

    @Transactional(readOnly = true)
    public List<Cliente> traerTodo() {
        return clienteRepositorio.findAll();
    }

    @Transactional(readOnly = true)
    public Cliente traerPorId(Integer id) {
        return clienteRepositorio.findById(id).get();
    }

}
