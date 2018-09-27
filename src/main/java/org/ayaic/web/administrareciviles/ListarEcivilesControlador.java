package org.ayaic.web.administrareciviles;

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
public class ListarEcivilesControlador {

    private final MiFacade mi;

    public ListarEcivilesControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/ListarEciviles.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");

        List listarEciviles = this.mi.getListarEciviles();
        modelo.put("listarEciviles", listarEciviles);

        return new ModelAndView("administrareciviles/ListarEciviles", modelo);

    }
}
