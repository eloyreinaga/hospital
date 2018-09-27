package org.ayaic.web.administrarquirofanos;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Quirofanos;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ListarQuirofanosControlador {

    private final MiFacade mi;

    public ListarQuirofanosControlador(MiFacade mi) {
        this.mi = mi;
    }
    
    @RequestMapping("/ListarQuirofanos.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");

        Quirofanos datoqui = new Quirofanos();
        datoqui.setCod_esta(cliente.getCod_esta());
        List listarQuirofanos = this.mi.getListarQuirofanos(datoqui);
        modelo.put("listarQuirofanos", listarQuirofanos);

        return new ModelAndView("administrarquirofanos/ListarQuirofanos", modelo);

    }
}
