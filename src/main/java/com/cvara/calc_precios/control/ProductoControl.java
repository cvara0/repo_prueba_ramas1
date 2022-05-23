package com.cvara.calc_precios.control;

import com.cvara.calc_precios.entidad.Producto;
import com.cvara.calc_precios.error.ErrorServicio;
import com.cvara.calc_precios.servicio.ProductoServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/producto")
@RequiredArgsConstructor
public class ProductoControl {
    private final ProductoServicio productoServicio;

    @GetMapping("/tabla")
    public ModelAndView mostrarTabla(){
        ModelAndView mav=new ModelAndView("producto-tabla.html");
        mav.addObject("lista_producto",productoServicio.traerTodo());
        return mav;
    }

    @GetMapping("/formulario")
    public ModelAndView mostrarFormulario(HttpServletRequest request){
        ModelAndView mav=new ModelAndView("producto-formulario.html");
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);

        if (inputFlashMap != null) {
            mav.addObject("producto", inputFlashMap.get("producto"));
            mav.addObject("error", inputFlashMap.get("error"));
        } else {
            mav.addObject("producto", new Producto());
        }

        mav.addObject("accion", "crear");
        return mav;
    }

    @GetMapping("/formulario/{id}")
    public ModelAndView mostrarFormulario(@PathVariable Integer id){
        ModelAndView mav=new ModelAndView("producto-formulario.html");
        mav.addObject("producto", productoServicio.traerPorId(id));
        mav.addObject("accion", "modificar");
        return mav;
    }
////////
    @PostMapping("/crear")
    public RedirectView crear(Producto dto, RedirectAttributes attributes) {
        RedirectView redirect = new RedirectView("/formulario");
        try {
            productoServicio.crear(dto);
            attributes.addFlashAttribute("exito", "Producto agregado");
        } catch (ErrorServicio e) {
            attributes.addFlashAttribute("author", dto);
            attributes.addFlashAttribute("error", e.getMessage());
            redirect.setUrl("/producto/formulario");
        }
        return redirect;
    }

    @PostMapping("/modificar")
    public RedirectView modificar(Producto dto, RedirectAttributes attributes) throws ErrorServicio {
        RedirectView redirect = new RedirectView("/producto/tabla");
        productoServicio.modificar(dto);
        attributes.addFlashAttribute("exito", "Producto modificado");
        return redirect;
    }

    @PostMapping("/eliminar/{id}")
    public RedirectView eliminar(@PathVariable Integer id) {
        RedirectView redirect = new RedirectView("/producto/tabla");
        productoServicio.eliminarPorId(id);
        return redirect;
    }


/*
* llamo al formulario que a su vez llama a crear, que luego me redirije a donde quera
* */
}
