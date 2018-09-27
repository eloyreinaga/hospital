package org.ayaic.web.administrarusuarios;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Menues;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NuevoRolUsuarioControlador {

    private final MiFacade mi;

    public NuevoRolUsuarioControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/NuevoRolUsuario.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");

        String accion = request.getParameter("accion");
        String id_usuario = request.getParameter("id_usuario");
        String id_rol = request.getParameter("id_rol");
        //Listamos los roles
        List listaRoles1 = this.mi.getListarRoles();
        modelo.put("listaRoles", listaRoles1);

        modelo.put("id_usuario", request.getParameter("id_usuario"));

        //Para la primera vez que entra a la pagina
        if ((request.getParameter("id_usuario") != null) && ("Modificar".equals(accion)) && (request.getParameter("recargado") == null)) {
            //Sacamos los datos del usuario
            Menues modifica = new Menues();
            modifica.setId_usuario(Integer.parseInt(id_usuario));
            modifica.setId_rol(Integer.parseInt(id_rol));
            Menues buscarRolUsuario = this.mi.getUsrRol(modifica);   // saca un registro a ser modificado
            modelo.put("buscarRol", buscarRolUsuario);
            try {
                modelo.put("anio", Integer.toString(buscarRolUsuario.getFec_expiracion().getYear() + 1900));
                modelo.put("mes", Integer.toString(buscarRolUsuario.getFec_expiracion().getMonth() + 1));
                modelo.put("dia", Integer.toString(buscarRolUsuario.getFec_expiracion().getDate()));
            } catch (Exception e) {
                modelo.put("anio", "");
                modelo.put("mes", "");
                modelo.put("dia", "");
            }
            modelo.put("id_usuario", Integer.toString(buscarRolUsuario.getId_usuario()));
            modelo.put("id_rol", Integer.toString(buscarRolUsuario.getId_rol()));
            modelo.put("id_estado", buscarRolUsuario.getId_estado());
            modelo.put("sw", request.getParameter("sw"));
        }
        modelo.put("accion", accion);
        return new ModelAndView("administrarusuarios/NuevoRolUsuario", modelo);
    }
}
