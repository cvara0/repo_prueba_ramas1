package com.cvara.calc_precios.servicio;

import com.cvara.calc_precios.entidad.Cliente;
import com.cvara.calc_precios.entidad.Venta;
import com.cvara.calc_precios.error.ErrorServicio;
import com.cvara.calc_precios.repositorio.ClienteRepositorio;
import com.cvara.calc_precios.repositorio.VentaRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VentaServicio {

    private final VentaRepositorio ventaRepositorio;

    @Transactional
    public void crear(Venta dto) throws ErrorServicio {

        Venta venta = new Venta();
        venta.setFecha(dto.getFecha());
        venta.setAnticipo(dto.getAnticipo());
        venta.setPrecioTotal(dto.getPrecioTotal());
        ventaRepositorio.save(venta);

    }

    @Transactional
    public void eliminarPorId(Integer id) {
        ventaRepositorio.deleteById(id);
    }

    @Transactional
    public void modificar(Venta dto) throws ErrorServicio{

        Optional<Venta> respuesta=ventaRepositorio.findById(dto.getId());

        if(respuesta.isPresent()){
            Venta venta = new Venta();
            venta.setFecha(dto.getFecha());
            venta.setAnticipo(dto.getAnticipo());
            venta.setPrecioTotal(dto.getPrecioTotal());
            ventaRepositorio.save(venta);
        }else {
            throw new ErrorServicio("No se encontro venta con dicho id");
        }
    }

    @Transactional(readOnly = true)
    public List<Venta> traerTodo() {
        return ventaRepositorio.findAll();
    }

    @Transactional(readOnly = true)
    public Venta traerPorId(Integer id) {
        return ventaRepositorio.findById(id).get();
    }

}
