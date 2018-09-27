package org.ayaic.web.administrarrubros;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Rubros;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ConfirmarRubroControlador {

    private final MiFacade mi;

    public ConfirmarRubroControlador(MiFacade mi) {
        this.mi = mi;
    }
    
    @RequestMapping("/ConfirmarRubro.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");

        String accion = request.getParameter("accion");
        String accion1 = request.getParameter("accion1");

        String id_rubro = request.getParameter("id_rubro");
        String rubro = request.getParameter("rubro");
        String id_estado = request.getParameter("id_estado");
        Rubros pai = new Rubros();

        if ("Adicionar".equals(accion)) {

//      pai.setId_rubro(Integer.parseInt(id_rubro)) ;
            pai.setRubro(rubro);

            modelo.put("dato", pai);
            modelo.put("accion", accion);
            modelo.put("accion1", accion1);

        }

        if ("Modificar".equals(accion)) {

            pai.setId_rubro(Integer.parseInt(id_rubro));
            pai.setRubro(rubro);
            pai.setId_estado(id_estado);

            modelo.put("dato", pai);
            modelo.put("sw", request.getParameter("sw"));
            modelo.put("accion", accion);
            modelo.put("accion1", accion1);
        }

        if ("Eliminar".equals(accion)) {
            Rubros buscarrubro = this.mi.getDatosRubro(Integer.parseInt(id_rubro)); // saca un registro a ser modificado
            modelo.put("dato", buscarrubro);

            modelo.put("accion", accion);
            modelo.put("sw1", request.getParameter("sw1"));
            modelo.put("id_parentesco", id_rubro);
        }

        return new ModelAndView("administrarrubros/ConfirmarRubro", modelo);

    }
}
