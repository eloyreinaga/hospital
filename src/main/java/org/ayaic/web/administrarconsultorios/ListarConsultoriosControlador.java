package org.ayaic.web.administrarconsultorios;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Consultorios;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ListarConsultoriosControlador {

    private final MiFacade mi;

    public ListarConsultoriosControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/ListarConsultorios.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");

        Consultorios a = new Consultorios();
        a.setCod_esta(cliente.getCod_esta());
        List listarCargos = this.mi.getListarConsultorios(a);
        modelo.put("listarCargos", listarCargos);

        return new ModelAndView("administrarconsultorios/ListarConsultorios", modelo);

    }
}
