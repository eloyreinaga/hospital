package org.ayaic.web.verPie;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class VerPie {
    
    @RequestMapping("/verPie.do")
    public ModelAndView index() {
        return new ModelAndView("verPie/VerPie");
    }
}
