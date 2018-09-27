package org.ayaic.web.administrareciviles;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Eciviles;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NuevoEcivilControlador {

    private final MiFacade mi;

    public NuevoEcivilControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/NuevoEcivil.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String id_ecivil = request.getParameter("id_ecivil");

        modelo.put("id_ecivil", request.getParameter("id_ecivil"));

        //Para la primera vez que entra a la pagina
        if ((request.getParameter("id_ecivil") != null) && ("Modificar".equals(accion)) && (request.getParameter("recargado") == null)) {
            //Sacamos los datos del ecivil
            Eciviles buscarecivil = this.mi.getDatosEcivil(Integer.parseInt(id_ecivil)); // saca un registro a ser modificado
            modelo.put("buscarecivil", buscarecivil);
            modelo.put("id_estado", buscarecivil.getId_estado());
            modelo.put("sw", request.getParameter("sw"));
        }
        modelo.put("accion", accion);
        return new ModelAndView("administrareciviles/NuevoEcivil", modelo);
    }
}
