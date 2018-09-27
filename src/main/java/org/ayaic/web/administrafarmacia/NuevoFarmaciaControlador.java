package org.ayaic.web.administrafarmacia;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Farmacias;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NuevoFarmaciaControlador {

    private final MiFacade mi;

    public NuevoFarmaciaControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/NuevoFarmacia.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String _id_usuario = Integer.toString(cliente.getId_usuario());

        String accion = request.getParameter("accion");
        String id_farmacia = request.getParameter("id_farmacia");

        modelo.put("id_farmacia", request.getParameter("id_farmacia"));

        //Para la primera vez que entra a la pagina
        if ((request.getParameter("id_farmacia") != null) && ("Modificar".equals(accion)) && (request.getParameter("recargado") == null)) {
            //Sacamos los datos del farmacia
            Farmacias datofarmacia = new Farmacias();
            datofarmacia.setCod_esta(cliente.getCod_esta());
            datofarmacia.setId_farmacia(Integer.parseInt(id_farmacia));
            Farmacias buscarfarmacia = this.mi.getDatosFarmacia(datofarmacia); // saca un registro a ser modificado
            modelo.put("buscarfarmacia", buscarfarmacia);
            modelo.put("id_estado", buscarfarmacia.getId_estado());
            modelo.put("sw", request.getParameter("sw"));
        }
        modelo.put("accion", accion);
        return new ModelAndView("administrafarmacias/NuevoFarmacia", modelo);
    }
}
