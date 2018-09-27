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
public class ConfirmarRolControlador {

    private final MiFacade mi;

    public ConfirmarRolControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/ConfirmarRol.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String accion1 = request.getParameter("accion1");

        String id_rol = request.getParameter("id_rol");
        String rol = request.getParameter("rol");
        String descripcion = request.getParameter("descripcion");
        String id_estado = request.getParameter("id_estado");
        Roles pai = new Roles();

        if ("Adicionar".equals(accion)) {

            //  pai.setId_rol(Integer.parseInt(id_rol)) ;
            pai.setRol(rol);
            pai.setDescripcion(descripcion);

            modelo.put("dato", pai);
            modelo.put("accion", accion);
            modelo.put("accion1", accion1);

        }

        if ("Modificar".equals(accion)) {

            pai.setId_rol(Integer.parseInt(id_rol));
            pai.setRol(rol);
            pai.setDescripcion(descripcion);
            pai.setId_estado(id_estado);

            modelo.put("dato", pai);
            modelo.put("sw", request.getParameter("sw"));
            modelo.put("accion", accion);
            modelo.put("accion1", accion1);
        }

        if ("Eliminar".equals(accion)) {
            Roles aux = new Roles();
            aux.setId_rol(Integer.parseInt(id_rol));

            Roles buscarrol = this.mi.getBuscarRol(aux); // saca un registro a ser modificado
            modelo.put("dato", buscarrol);

            modelo.put("accion", accion);
            modelo.put("sw1", request.getParameter("sw1"));
            modelo.put("id_rol", id_rol);
        }

        return new ModelAndView("administrarroles/ConfirmarRol", modelo);

    }
}
