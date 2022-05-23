package com.cvara.calc_precios.servicio;

import com.cvara.calc_precios.entidad.Producto;
import com.cvara.calc_precios.entidad.Usuario;
import com.cvara.calc_precios.error.ErrorServicio;
import com.cvara.calc_precios.repositorio.ProductoRepositorio;
import com.cvara.calc_precios.repositorio.UsuarioRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductoServicio {

    private final ProductoRepositorio productoRepositorio;

    @Transactional
    public void crear(Producto dto) throws ErrorServicio {
        if (productoRepositorio.existeProducto(dto.getNombre()))
            throw new ErrorServicio("Ya existe este producto");
        if (dto.getNombre()==null || dto.getNombre().isEmpty() || dto.getPrecio()==null){
            throw new ErrorServicio("Nombre de producto y precio no pueden ser nulos");
        }
        Producto producto = new Producto();
        producto.setNombre(dto.getNombre());
        producto.setPrecio(dto.getPrecio());
        productoRepositorio.save(producto);
    }

    @Transactional
    public void eliminarPorId(Integer id) {
        productoRepositorio.deleteById(id);
    }

    @Transactional
    public void modificar(Producto dto) throws ErrorServicio{
        if (productoRepositorio.existeProducto(dto.getNombre()))
            throw new ErrorServicio("Ya existe este producto");
        if (dto.getNombre()==null || dto.getNombre().isEmpty() || dto.getPrecio()==null){
            throw new ErrorServicio("Nombre de producto y precio no pueden ser nulos");
        }
        Optional<Producto> respuesta=productoRepositorio.findById(dto.getId());

        if(respuesta.isPresent()){
            Producto producto = new Producto();
            producto.setNombre(dto.getNombre());
            producto.setPrecio(dto.getPrecio());
            productoRepositorio.save(producto);
        }else {
            throw new ErrorServicio("No se encontro al producto con dicho id");
        }
    }

    @Transactional(readOnly = true)
    public List<Producto> traerTodo() {
        return productoRepositorio.findAll();
    }

    @Transactional(readOnly = true)
    public Producto traerPorId(Integer id) {
        return productoRepositorio.findById(id).get();
    }


}
