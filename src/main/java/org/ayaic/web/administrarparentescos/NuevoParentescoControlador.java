package org.ayaic.web.administrarparentescos;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Parentescos;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NuevoParentescoControlador {

    private final MiFacade mi;

    public NuevoParentescoControlador(MiFacade mi) {
        this.mi = mi;
    }
    
    @RequestMapping("/NuevoParentesco.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String id_parentesco = request.getParameter("id_parentesco");

        modelo.put("id_parentesco", request.getParameter("id_parentesco"));

        //Para la primera vez que entra a la pagina
        if ((request.getParameter("id_parentesco") != null) && ("Modificar".equals(accion)) && (request.getParameter("recargado") == null)) {
            //Sacamos los datos del parentesco
            Parentescos buscarparentesco = this.mi.getDatosParentesco(Integer.parseInt(id_parentesco)); // saca un registro a ser modificado
            modelo.put("buscarparentesco", buscarparentesco);
            modelo.put("id_estado", buscarparentesco.getId_estado());
            modelo.put("sw", request.getParameter("sw"));
        }
        modelo.put("accion", accion);
        return new ModelAndView("administrarparentescos/NuevoParentesco", modelo);
    }
}
