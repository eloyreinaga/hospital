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
public class NuevoSeguroControlador {

    private final MiFacade mi;

    public NuevoSeguroControlador(MiFacade mi) {
        this.mi = mi;
    }
    
    @RequestMapping("/NuevoSeguro.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String id_seguro = request.getParameter("id_seguro");

        modelo.put("id_seguro", request.getParameter("id_seguro"));

        //Para la primera vez que entra a la pagina
        if ((request.getParameter("id_seguro") != null) && ("Modificar".equals(accion)) && (request.getParameter("recargado") == null)) {
            //Sacamos los datos del seguro
            Seguros buscarseguro = this.mi.getDatosSeguro(Integer.parseInt(id_seguro)); // saca un registro a ser modificado
            modelo.put("buscarseguro", buscarseguro);
            modelo.put("id_estado", buscarseguro.getId_estado());
            modelo.put("sw", request.getParameter("sw"));
        }
        modelo.put("accion", accion);
        return new ModelAndView("administrarseguros/NuevoSeguro", modelo);
    }
}
