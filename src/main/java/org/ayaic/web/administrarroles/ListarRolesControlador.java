package org.ayaic.web.administrarroles;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ListarRolesControlador {

    private final MiFacade mi;

    public ListarRolesControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/ListarRoles.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");

        List listarRoles = this.mi.getListarRoles();
        modelo.put("listarRoles", listarRoles);
        modelo.put("datos", cliente);

        return new ModelAndView("administrarroles/ListarRoles", modelo);

    }
}
