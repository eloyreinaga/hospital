package org.ayaic.web.login;

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
public class CambiarRol {

    private final MiFacade mi;

    public CambiarRol(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/cambiarRol.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String sId_rol = request.getParameter("id_rol");
        String sEncabezado = request.getParameter("encabezado");
        Roles rol = new Roles();
        rol.setId_rol(Integer.parseInt(sId_rol));
        rol.setId_usuario(cliente.getId_usuario());
        rol = this.mi.getBuscarRolCliente(rol);
        cliente.setId_rol(rol.getId_rol());
        cliente.setRol(rol.getRol());
        cliente.setId_facultad(rol.getId_facultad());
        cliente.setId_programa(rol.getId_programa());
        cliente.setId_departamento(rol.getId_departamento());
        cliente.setId_ubicacion_organica(rol.getId_ubicacion_organica());
        cliente.setId_almacen(rol.getId_almacen());
        cliente.setFiltro(rol.getFiltro());
        cliente.setPermiso(rol.getPermiso());
        //Sacamos el listado de almacenes
        cliente.setAlmacenes(this.mi.getListarAlmacenesCliente(rol));
        if ("si".equals(sEncabezado)) {
            if (cliente.getAlmacenes().size() > 0) {
                Roles aux = (Roles) cliente.getAlmacenes().get(0);
                cliente.setId_almacen(aux.getId_almacen());
                cliente.setPermiso(aux.getPermiso());
                cliente.setAlmacen(aux.getAlmacen());
            }
            if (cliente.getAlmacenes().size() == 0) {
                cliente.setAlmacen("");
            }
        }
        request.getSession().setAttribute("__sess_cliente", cliente);
        if ((cliente.getAlmacenes().size() > 1) && (!"si".equals(sEncabezado))) { // tiene mas de 1 rol
            return new ModelAndView("Distro", "url", "/elegirAlmacen.do"); //Elegir un almacen de los tantos
        }
        return new ModelAndView("Distro", "url", ".' target='_top'");
    }
}
