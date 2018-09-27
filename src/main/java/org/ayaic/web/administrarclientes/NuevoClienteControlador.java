package org.ayaic.web.administrarclientes;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clieentes;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NuevoClienteControlador {

    private final MiFacade mi;

    public NuevoClienteControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/NuevoCliente.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String id_cliente = request.getParameter("id_cliente");

        modelo.put("id_cliente", request.getParameter("id_cliente"));

        //Para la primera vez que entra a la pagina
        if ((request.getParameter("id_cliente") != null) && ("Modificar".equals(accion)) && (request.getParameter("recargado") == null)) {
            //Sacamos los datos del cliente
            Clieentes clientes = new Clieentes();
            clientes.setCod_esta(cliente.getCod_esta());
            clientes.setId_cliente(Integer.parseInt(id_cliente));
            Clieentes datocliente = this.mi.getDatosCliente(clientes);
            modelo.put("datocliente", datocliente);
            modelo.put("sw", request.getParameter("sw"));
        }
        modelo.put("accion", accion);
        return new ModelAndView("administrarclientes/NuevoCliente", modelo);
    }
}
