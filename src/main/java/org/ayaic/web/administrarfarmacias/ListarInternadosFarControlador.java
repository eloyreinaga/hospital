package org.ayaic.web.administrarfarmacias;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Historiales;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ListarInternadosFarControlador {

    private final MiFacade mi;

    public ListarInternadosFarControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/ListarInternadosFar.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String id_pedido = request.getParameter("id_pedido");
        String nombres = request.getParameter("nombres");
        String direccion = request.getParameter("direccion");
        String num_cladoc = request.getParameter("num_cladoc");
        String sw = request.getParameter("sw");

        Historiales dato = new Historiales();
        dato.setAccion("R");
        dato.setCod_esta(cliente.getCod_esta());
        List listarAtendidosI = this.mi.getInternados(dato);
        modelo.put("listarAtendidosI", listarAtendidosI);

        return new ModelAndView("administrarfarmacias/ListarInternadosFar", modelo);
    }
}
