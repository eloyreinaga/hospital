package org.ayaic.web.administrarroles;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Roles;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GrabarRolControlador {

    private final MiFacade mi;

    public GrabarRolControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/GrabarRol.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion1 = request.getParameter("accion1");
        String accion = request.getParameter("accion");

        String rol = request.getParameter("rol");
        String descripcion = request.getParameter("descripcion");
        String id_rol = request.getParameter("id_rol");

        if ("Adicionar".equals(accion) && "Aceptar".equals(accion1)) {
            Roles aux = new Roles();
            aux.setId_rol(Integer.parseInt(id_rol));

            Roles repetida = this.mi.getBuscarRol(aux); // saca un registro a ser modificado  

            //  if (repetida == null) {
            if (("".equals(id_rol)) || ("".equals(rol)) || ("".equals(descripcion))) {
                return new ModelAndView("Aviso", "mensaje", "Faltan Introducir Datos");
            }
            Roles datorol = new Roles();
            //   datorol.setId_rol(Integer.parseInt(id_rol)) ;
            datorol.setRol(rol);
            datorol.setDescripcion(descripcion);
            datorol.setUlt_usuario(cliente.getId_usuario());

            try {
                this.mi.setCrearRol(datorol);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "La actualización no se cumplio, verifique los datos");
            }
            //listar Roles
            List listarRoles = this.mi.getListarRoles();
            return new ModelAndView("administrarroles/ListarRoles", modelo);

            //  }
            //else {
            //  return new ModelAndView("Aviso","mensaje","Ya existe un id_rol igual");	
            //}
        }

        if ("Modificar".equals(accion) && "Aceptar".equals(accion1)) {
            if (("".equals(id_rol)) || ("".equals(rol)) || ("".equals(descripcion))) {
                return new ModelAndView("Aviso", "mensaje", "Faltan Introducir Datos");
            }
            Roles categoria_m = new Roles();
            categoria_m.setId_rol(Integer.parseInt(request.getParameter("id_rol")));
            categoria_m.setRol(request.getParameter("rol"));
            categoria_m.setDescripcion(request.getParameter("descripcion"));
            categoria_m.setUlt_usuario(cliente.getId_usuario());

            if ("A".equals(request.getParameter("id_estado"))) {
                categoria_m.setId_estado("A");
            } else {
                categoria_m.setId_estado("B");
            }

            try {
                this.mi.setModificarRol(categoria_m);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "La actualización no se cumplio, verifique los datos");
            }

            //listar categorias
            List listarRoles = this.mi.getListarRoles();
            modelo.put("listarRoles", listarRoles);
            return new ModelAndView("administrarroles/ListarRoles", modelo);

        }

        if ("Eliminar".equals(accion) && "Aceptar".equals(accion1)) {
            if ("".equals(request.getParameter("id_rol"))) {
                return new ModelAndView("Aviso", "mensaje", "Faltan datos");
            }
            Roles elimina = new Roles();
            elimina.setId_rol(Integer.parseInt(request.getParameter("id_rol")));

            try {
                this.mi.setEliminarRol(elimina);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "El registro a eliminar tiene dependencias");
            }

            //listar categorias
            List listarRoles = this.mi.getListarRoles();
            modelo.put("listarRoles", listarRoles);

            return new ModelAndView("administrarroles/ListarRoles", modelo);
        }
        return new ModelAndView("administrarroles/ListarRoles", modelo);
    }
}
