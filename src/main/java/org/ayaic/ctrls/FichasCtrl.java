package org.ayaic.ctrls;

import org.ayaic.domain.logic.MiFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FichasCtrl {

    @Autowired
    MiFacade mi;

    @RequestMapping("/ListarFichas")
    public ModelAndView handleRequest() {

        return new ModelAndView("fichas/ListarFichas");

    }
}
