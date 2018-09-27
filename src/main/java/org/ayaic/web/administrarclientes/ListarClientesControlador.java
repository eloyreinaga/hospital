package org.ayaic.web.administrarclientes;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clieentes;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ListarClientesControlador {

    private final MiFacade mi;

    public ListarClientesControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/ListarClientes.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");

        Clieentes clientes = new Clieentes();
        clientes.setCod_esta(cliente.getCod_esta());
        List listarCliente = this.mi.getListarClientes(clientes);
        modelo.put("listarClientes", listarCliente);

        return new ModelAndView("administrarclientes/ListarClientes", modelo);

    }
}
