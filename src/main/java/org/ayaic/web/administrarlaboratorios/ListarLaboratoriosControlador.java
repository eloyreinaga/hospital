package org.ayaic.web.administrarlaboratorios;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Laboratorios;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ListarLaboratoriosControlador {

    private final MiFacade mi;

    public ListarLaboratoriosControlador(MiFacade mi) {
        this.mi = mi;
    }
    
    @RequestMapping("/ListarLaboratorios.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");

        Laboratorios pai = new Laboratorios();
        pai.setId_estado("%");
        List listarLaboratorios = this.mi.getListarLaboratorios(pai);
        modelo.put("listarLaboratorios", listarLaboratorios);

        return new ModelAndView("administrarlaboratorios/ListarLaboratorios", modelo);

    }
}
