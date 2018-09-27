package org.ayaic.web.administrarroles;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Roles;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NuevoRolControlador {

    private final MiFacade mi;

    public NuevoRolControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/NuevoRol.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String id_rol = request.getParameter("id_rol");

        modelo.put("id_rol", request.getParameter("id_rol"));

        //Para la primera vez que entra a la pagina
        if ((request.getParameter("id_rol") != null) && ("Modificar".equals(accion)) && (request.getParameter("recargado") == null)) {
            //Sacamos los datos del rol
            Roles aux = new Roles();
            aux.setId_rol(Integer.parseInt(id_rol));

            Roles buscarrol = this.mi.getBuscarRol(aux); // saca un registro a ser modificado
            modelo.put("buscarrol", buscarrol);
            modelo.put("id_estado", buscarrol.getId_estado());
            modelo.put("sw", request.getParameter("sw"));
        }
        modelo.put("accion", accion);
        return new ModelAndView("administrarroles/NuevoRol", modelo);
    }
}
