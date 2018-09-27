package org.ayaic.web.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ElegirRol {

    @RequestMapping("/elegirRol.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView("login/ElegirRol", "cliente", (Clientes) request.getSession().getAttribute("__sess_cliente"));
    }
}
