package org.ayaic.web.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.springframework.web.servlet.ModelAndView;


public class ElegirAlmacen {

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView("login/ElegirAlmacen", "cliente", (Clientes) request.getSession().getAttribute("__sess_cliente"));
    }
}
