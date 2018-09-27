package org.ayaic.web.administrarrubros;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Rubros;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ListarRubrosControlador {

    private final MiFacade mi;

    public ListarRubrosControlador(MiFacade mi) {
        this.mi = mi;
    }
    
    @RequestMapping("/ListarRubros.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");

        Rubros aux = new Rubros();
        List listarRubros = this.mi.getListarRubros(aux);
        modelo.put("listarRubros", listarRubros);

        return new ModelAndView("administrarrubros/ListarRubros", modelo);

    }
}
