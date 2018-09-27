package org.ayaic.web.administrarseguros;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Seguros;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ConfirmarSeguroControlador {

    private final MiFacade mi;

    public ConfirmarSeguroControlador (MiFacade mi) {
        this.mi = mi;
    }
    
    @RequestMapping("/ConfirmarSeguro.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String accion1 = request.getParameter("accion1");

        String id_seguro = request.getParameter("id_seguro");
        String seguro = request.getParameter("seguro");
        String id_estado = request.getParameter("id_estado");
        Seguros pai = new Seguros();

        if ("Adicionar".equals(accion)) {

//     pai.setId_seguro(Integer.parseInt(id_seguro)) ;
            pai.setSeguro(seguro);

            modelo.put("dato", pai);
            modelo.put("accion", accion);
            modelo.put("accion1", accion1);
        }
        if ("Modificar".equals(accion)) {

            pai.setId_seguro(Integer.parseInt(id_seguro));
            pai.setSeguro(seguro);
            pai.setId_estado(id_estado);

            modelo.put("dato", pai);
            modelo.put("sw", request.getParameter("sw"));
            modelo.put("accion", accion);
            modelo.put("accion1", accion1);
        }
        if ("Eliminar".equals(accion)) {
            Seguros buscarseguro = this.mi.getDatosSeguro(Integer.parseInt(id_seguro)); // saca un registro a ser modificado
            modelo.put("dato", buscarseguro);

            modelo.put("accion", accion);
            modelo.put("sw1", request.getParameter("sw1"));
            modelo.put("id_parentesco", id_seguro);
        }
        return new ModelAndView("administrarseguros/ConfirmarSeguro", modelo);

    }
}
