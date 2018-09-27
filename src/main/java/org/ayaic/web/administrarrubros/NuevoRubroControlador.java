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
public class NuevoRubroControlador {

    private final MiFacade mi;

    public NuevoRubroControlador(MiFacade mi) {
        this.mi = mi;
    }
    
    @RequestMapping("/NuevoRubro.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");

        String accion = request.getParameter("accion");
        String id_rubro = request.getParameter("id_rubro");

        modelo.put("id_rubro", request.getParameter("id_rubro"));

        //Para la primera vez que entra a la pagina
        if ((request.getParameter("id_rubro") != null) && ("Modificar".equals(accion)) && (request.getParameter("recargado") == null)) {
            //Sacamos los datos del rubro
            Rubros buscarrubro = this.mi.getDatosRubro(Integer.parseInt(id_rubro)); // saca un registro a ser modificado
            modelo.put("buscarrubro", buscarrubro);
            modelo.put("id_estado", buscarrubro.getId_estado());
            modelo.put("sw", request.getParameter("sw"));
        }
        modelo.put("accion", accion);
        return new ModelAndView("administrarrubros/NuevoRubro", modelo);
    }
}
