package org.ayaic.web.administrarenlaces;

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
public class ListarEnlacesControlador {

    private final MiFacade mi;

    public ListarEnlacesControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/ListarEnlaces.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");

        if (cliente.getId_cargo() != 1 && cliente.getId_cargo() != 7) {
            return new ModelAndView("Aviso", "mensaje", "Ud. no esta autorizado");
        }

        List listarEnlaces = this.mi.getListar_Enlaces();
        modelo.put("listarEnlaces", listarEnlaces);

        return new ModelAndView("administrarenlaces/ListarEnlaces", modelo);

    }
}
