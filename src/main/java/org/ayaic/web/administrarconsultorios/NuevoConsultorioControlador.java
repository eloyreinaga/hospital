package org.ayaic.web.administrarconsultorios;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Consultorios;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NuevoConsultorioControlador {

    private final MiFacade mi;

    public NuevoConsultorioControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/NuevoConsultorio.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String id_cargo = request.getParameter("id_cargo");

        String accion = request.getParameter("accion");
        String id_consultorio = request.getParameter("id_consultorio");

        modelo.put("id_consultorio", request.getParameter("id_consultorio"));

        //Para la primera vez que entra a la pagina
        if ((request.getParameter("id_consultorio") != null) && ("Modificar".equals(accion)) && (request.getParameter("recargado") == null)) {
            //Sacamos los datos del pais
            Consultorios buscarEtel = this.mi.getDatosConsultorio(Integer.parseInt(id_consultorio)); // saca un registro a ser modificado
            modelo.put("datosCargo", buscarEtel);
            modelo.put("id_estado", buscarEtel.getId_estado());
            modelo.put("id_cargo", id_cargo);
            modelo.put("sw", request.getParameter("sw"));
        }
        modelo.put("accion", accion);
        return new ModelAndView("administrarconsultorios/NuevoConsultorio", modelo);
    }
}
