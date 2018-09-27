package org.ayaic.web.login;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Roles;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.web.servlet.ModelAndView;

public class CambiarAlmacen {

    private final MiFacade mi;

    public CambiarAlmacen(MiFacade mi) {
        this.mi = mi;
    }

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String sId_almacen = request.getParameter("id_almacen");
        Roles rol = new Roles();
        rol.setId_almacen(Integer.parseInt(sId_almacen));
        rol.setId_rol(cliente.getId_rol());
        rol.setId_usuario(cliente.getId_usuario());
        //Buscamos los datos del almacen
        rol = this.mi.getBuscarAlmacenCliente(rol);
        cliente.setId_almacen(rol.getId_almacen());
        cliente.setAlmacen(rol.getAlmacen());
        cliente.setPermiso(rol.getPermiso());
        request.getSession().setAttribute("__sess_cliente", cliente);
        return new ModelAndView("Distro", "url", ".");
    }

}
