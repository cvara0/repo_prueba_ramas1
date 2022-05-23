package com.cvara.calc_precios.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PrincipalControl {

        public ModelAndView getIndex() {
            return new ModelAndView("index.html");
        }

}
