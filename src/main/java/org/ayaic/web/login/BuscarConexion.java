package org.ayaic.web.login;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Roles;
import org.ayaic.domain.Usuarios;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BuscarConexion {

    private final MiFacade mi;

    public BuscarConexion(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/buscarConexion.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        String sHora = request.getParameter("hora");
        String sApodo = request.getParameter("apodo" + sHora);
        String sClave = request.getParameter("clave" + sHora);
        String ip = request.getRemoteAddr();
        String host = request.getRemoteHost();
        Usuarios usuario = new Usuarios();
        usuario.setApodo(sApodo);
        usuario.setClave(sClave);
        usuario.setCadena1(ip);
        usuario.setCadena2(host);        
        this.mi.setCrearRegUsuario(usuario);
        Clientes cliente = this.mi.getBuscarConexion(usuario);
        modelo.put("cliente", cliente);
        if (cliente == null) {
            return new ModelAndView("login/LoginEntrada", "mensaje", "No puedo encontrar al usuario");
        }else{
            usuario.setId_usuario(cliente.getId_persona());        
            this.mi.setCrearRegUsuario(usuario);
        }
        if (cliente.getId_rol() == 1) { // es Administrativo
            Roles rol = new Roles();
            rol.setId_usuario(cliente.getId_usuario());
            cliente.setRoles(this.mi.getListarRolesCliente(rol));
            if (cliente.getRoles().size() == 0) {  // expiraron sus roles
                return new ModelAndView("login/LoginEntrada", "mensaje", "No puede ingresar, porque termino su periodo de acceso al sistema");
            }
            Roles aux = (Roles) cliente.getRoles().get(0);
            cliente.setId_rol(aux.getId_rol());
            cliente.setRol(aux.getRol());
        }
        
        request.getSession().setAttribute("__sess_cliente", cliente); // Subimos 'cliente' a la sesion
        if (cliente.getRoles().size() > 1) { // tiene mas de 1 rol
            return new ModelAndView("Distro", "url", "/elegirRol.do"); //Elegir un rol de los tantos
        } else {
            Roles rol = new Roles();
            rol.setId_usuario(cliente.getId_usuario());
            rol.setId_rol(cliente.getId_rol());
            rol = this.mi.getBuscarRolCliente(rol);
            cliente.setId_rol(rol.getId_rol());
            cliente.setRol(rol.getRol());
            ///Sacamos el listado de almacenes
            cliente.setAlmacenes(this.mi.getListarAlmacenesCliente(rol));
            if (cliente.getAlmacenes().size() == 1) {
                Roles aux = (Roles) cliente.getAlmacenes().get(0);
                cliente.setId_almacen(aux.getId_almacen());
                cliente.setPermiso(aux.getPermiso());
                cliente.setAlmacen(aux.getAlmacen());
            }
        }
        request.getSession().setAttribute("__sess_cliente", cliente); // Subimos 'cliente' a la sesion
        if (cliente.getAlmacenes().size() > 1) { // tiene mas de 1 rol
            return new ModelAndView("Distro", "url", "/elegirAlmacen.do"); //Elegir un almacen de los tantos
        }

        // Mostramos el menï¿½ correspondiente
        return new ModelAndView("Distro", "url", ".' target='_top'");
    }       

}
