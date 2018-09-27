package org.ayaic.web.administrafarmacia;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Farmacias;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ListarFarmaciasControlador {

    private final MiFacade mi;

    public ListarFarmaciasControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/ListarFarmacias.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");

        Farmacias datofar = new Farmacias();
        datofar.setCod_esta(cliente.getCod_esta());
        List listarFarmacia = this.mi.getListarFarmacias(datofar);
        modelo.put("listarFarmacia", listarFarmacia);

        return new ModelAndView("administrafarmacias/ListarFarmacias", modelo);

    }
}
