package org.ayaic.web.administrarcostos;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Costos;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ListarCostosControlador {

    private final MiFacade mi;

    public ListarCostosControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/ListarCostos.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");

        Costos datocosto = new Costos();
        datocosto.setCod_esta(cliente.getCod_esta());
        List listarCostosRubro = this.mi.getListarCostosRubro(datocosto);
        modelo.put("listarCostosRubro", listarCostosRubro);

        return new ModelAndView("administrarcostos/ListarCostos", modelo);

    }
}
