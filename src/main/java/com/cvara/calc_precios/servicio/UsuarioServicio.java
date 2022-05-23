package com.cvara.calc_precios.servicio;

import com.cvara.calc_precios.entidad.Usuario;
import com.cvara.calc_precios.error.ErrorServicio;
import com.cvara.calc_precios.repositorio.UsuarioRepositorio;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioServicio {

    private final UsuarioRepositorio usuarioRepositorio;

    @Transactional
    public void crear(Usuario dto) throws ErrorServicio {
        if (usuarioRepositorio.existeEmail(dto.getEmail()))
            throw new ErrorServicio("Ya existe un email asociado a este usuario");
        if (dto.getNombre()==null || dto.getNombre().isEmpty() || dto.getEmail()==null || dto.getEmail().isEmpty() || dto.getPassword()==null || dto.getPassword().isEmpty()){
            throw new ErrorServicio("Email , contrasenia y nombre no pueden ser nulos");
        }
        Usuario usuario = new Usuario();
        usuario.setNombre(dto.getNombre());
        usuario.setEmail(dto.getEmail());
        usuario.setPassword((dto.getPassword()));
        usuarioRepositorio.save(usuario);
    }

    @Transactional
    public void eliminarPorId(Integer id) {
        usuarioRepositorio.deleteById(id);
    }

    @Transactional
    public void modificar(Usuario dto) throws ErrorServicio{
        if (usuarioRepositorio.existeEmail(dto.getEmail()))
            throw new ErrorServicio("Ya existe un email asociado a este usuario");
        if (dto.getNombre()==null || dto.getNombre().isEmpty() || dto.getEmail()==null || dto.getEmail().isEmpty() || dto.getPassword()==null || dto.getPassword().isEmpty()){
            throw new ErrorServicio("Email , contrasenia y nombre no pueden ser nulos");
        }
        Optional<Usuario> respuesta=usuarioRepositorio.findById(dto.getId());

        if(respuesta.isPresent()){
            Usuario usuario=respuesta.get();
        usuario.setNombre(dto.getNombre());
        usuario.setEmail(dto.getEmail());
        usuario.setPassword(dto.getPassword());
        usuarioRepositorio.save(usuario);
        }else {
            throw new ErrorServicio("No se encontro al usuario con dicho id");
        }
    }

    @Transactional(readOnly = true)
    public List<Usuario> traerTodo() {
        return usuarioRepositorio.findAll();
    }

    @Transactional(readOnly = true)
    public Usuario traerPorId(Integer id) {
        return usuarioRepositorio.findById(id).get();
    }


}
