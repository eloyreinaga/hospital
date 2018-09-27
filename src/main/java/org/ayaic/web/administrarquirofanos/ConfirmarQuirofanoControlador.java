package org.ayaic.web.administrarquirofanos;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Quirofanos;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ConfirmarQuirofanoControlador {

    private final MiFacade mi;

    public ConfirmarQuirofanoControlador(MiFacade mi) {
        this.mi = mi;
    }
    
    @RequestMapping("/ConfirmarQuirofano.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String accion1 = request.getParameter("accion1");

        String id_quirofano = request.getParameter("id_quirofano");
        String quirofano = request.getParameter("quirofano");
        String id_estado = request.getParameter("id_estado");
        Quirofanos pai = new Quirofanos();

        if ("Adicionar".equals(accion)) {
            pai.setQuirofano(quirofano);

            modelo.put("dato", pai);
            modelo.put("accion", accion);
            modelo.put("accion1", accion1);
        }

        if ("Modificar".equals(accion)) {

            pai.setId_quirofano(Integer.parseInt(id_quirofano));
            pai.setQuirofano(quirofano);
            pai.setId_estado(id_estado);

            modelo.put("dato", pai);
            modelo.put("sw", request.getParameter("sw"));
            modelo.put("accion", accion);
            modelo.put("accion1", accion1);
        }

        if ("Eliminar".equals(accion)) {
            Quirofanos datoqui = new Quirofanos();
            datoqui.setId_quirofano(Integer.parseInt(id_quirofano));
            Quirofanos buscarquirofano = this.mi.getDatosQuirofano(datoqui); // saca un registro a ser modificado
            modelo.put("dato", buscarquirofano);

            modelo.put("accion", accion);
            modelo.put("sw1", request.getParameter("sw1"));
            modelo.put("id_parentesco", id_quirofano);
        }

        return new ModelAndView("administrarquirofanos/ConfirmarQuirofano", modelo);

    }
}
