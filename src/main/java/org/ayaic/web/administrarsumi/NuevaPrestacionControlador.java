package org.ayaic.web.administrarsumi;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Prestaciones;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NuevaPrestacionControlador {

    private final MiFacade mi;

    public NuevaPrestacionControlador(MiFacade mi) {
        this.mi = mi;
    }
    
    @RequestMapping("/NuevaPrestacion.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String id_prestacion = request.getParameter("id_prestacion");

        modelo.put("id_prestacion", request.getParameter("id_prestacion"));

        //Para la primera vez que entra a la pagina
        if ((request.getParameter("id_prestacion") != null) && ("Modificar".equals(accion)) && (request.getParameter("recargado") == null)) {
            //Sacamos los datos del pais
            Prestaciones buscarPres = this.mi.getDatosPrestacion(Integer.parseInt(id_prestacion)); // saca un registro a ser modificado
            modelo.put("buscarPres", buscarPres);
            modelo.put("sw", request.getParameter("sw"));
        }
        modelo.put("accion", accion);
        return new ModelAndView("administrarsumi/NuevaPrestacion", modelo);
    }
}
