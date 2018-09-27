package org.ayaic.web.administrarusuarios;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Menues;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GrabarRolUsuarioControlador {

    private final MiFacade mi;

    public GrabarRolUsuarioControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/GrabarRolUsuario.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response){
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion1 = request.getParameter("accion1");
        String accion = request.getParameter("accion");
        String id_rol = request.getParameter("id_rol");
        String id_estado = request.getParameter("id_estado");
        String aniox = request.getParameter("anio");
        String mesx = request.getParameter("mes");
        String diax = request.getParameter("dia");
        String id_usuario = request.getParameter("id_usuario");
        String id_triaje = request.getParameter("id_triaje");

        if ("Adicionar".equals(accion) && "Aceptar".equals(accion1)) {
            Menues verificando = new Menues();
            verificando.setId_usuario(Integer.parseInt(request.getParameter("id_usuario")));
            verificando.setId_rol(Integer.parseInt(request.getParameter("id_rol")));

            Menues Verificando = this.mi.getUsrRol(verificando);  // SI EL OBJETO EXISTE ENTONCES NO ES NULO  
            if (Verificando == null) {
                if (("".equals(request.getParameter("id_usuario"))) || ("".equals(request.getParameter("id_rol")))
                        || ("".equals(request.getParameter("anio")))
                        || ("".equals(request.getParameter("mes"))) || ("".equals(request.getParameter("dia")))) {
                    return new ModelAndView("Aviso", "mensaje", "Faltan Introducir Datos");
                }
                int dia = Integer.parseInt(request.getParameter("dia"));
                int mes = Integer.parseInt(request.getParameter("mes")) - 1;
                int anio = Integer.parseInt(request.getParameter("anio")) - 1900;
                Date fec_expiracion = new Date(anio, mes, dia);

                Menues usuario = new Menues();
                usuario.setId_usuario(Integer.parseInt(request.getParameter("id_usuario")));
                usuario.setId_rol(Integer.parseInt(request.getParameter("id_rol")));
                usuario.setFec_expiracion(fec_expiracion);
                usuario.setUlt_usuario(cliente.getId_usuario());

                try {
                    this.mi.setCrearUsrRol(usuario);
                } catch (Exception e) {
                    return new ModelAndView("Aviso", "mensaje", "El Registro no se cumplio, verifique los datos");
                }
                //lista los roles de los Menues
                Menues usuario_rol = new Menues();
                usuario_rol.setId_usuario(Integer.parseInt(id_usuario));
                List listaUsrRoles = this.mi.getListaUsrRoles(usuario_rol);
                modelo.put("listaUsrRoles", listaUsrRoles);

                modelo.put("id_usuario", id_usuario);
                return new ModelAndView("administrarusuarios/ListarRolUsuarios", modelo);
            } else {
                return new ModelAndView("Aviso", "mensaje", "Ya existe un usuario igual");
            }
        }

        if ("Modificar".equals(accion) && "Aceptar".equals(accion1)) {
            if (("".equals(request.getParameter("id_usuario"))) || ("".equals(request.getParameter("id_rol")))
                    || ("".equals(request.getParameter("anio")))
                    || ("".equals(request.getParameter("mes"))) || ("".equals(request.getParameter("dia")))) {
                return new ModelAndView("Aviso", "mensaje", "Faltan Introducir Datos");
            }

            int dia = Integer.parseInt(request.getParameter("dia"));
            int mes = Integer.parseInt(request.getParameter("mes")) - 1;
            int anio = Integer.parseInt(request.getParameter("anio")) - 1900;
            Date fec_expiracion = new Date(anio, mes, dia);

            Menues usuario = new Menues();
            usuario.setId_usuario(Integer.parseInt(request.getParameter("id_usuario")));
            usuario.setFec_expiracion(fec_expiracion);
            if ("A".equals(request.getParameter("id_estado"))) {
                usuario.setId_estado("A");
            } else {
                usuario.setId_estado("B");
            }
            usuario.setId_rol(Integer.parseInt(request.getParameter("id_rol")));
            usuario.setUlt_usuario(cliente.getId_usuario());

            try {
                this.mi.setModificarUsrRol(usuario);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "La actualización no se cumplio, verifique los datos");
            }

            //lista los roles de los Menues
            Menues usuario_rol = new Menues();
            usuario_rol.setId_usuario(Integer.parseInt(id_usuario));
            List listaUsrRoles = this.mi.getListaUsrRoles(usuario_rol);
            modelo.put("listaUsrRoles", listaUsrRoles);
            modelo.put("id_usuario", id_usuario);

            return new ModelAndView("administrarusuarios/ListarRolUsuarios", modelo);
        }

        if ("Eliminar".equals(accion) && "Aceptar".equals(accion1)) {
            if ("".equals(request.getParameter("id_usuario"))) {
                return new ModelAndView("Aviso", "mensaje", "Faltan datos");
            }
            Menues elimina = new Menues();
            elimina.setId_usuario(Integer.parseInt(request.getParameter("id_usuario")));
            elimina.setId_rol(Integer.parseInt(request.getParameter("id_rol")));
            try {
                this.mi.setEliminarUsrRol(elimina);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "La eliminacion no se produjo, verifique los datos");
            }

            //lista los roles de los Menues
            Menues usuario_rol = new Menues();
            usuario_rol.setId_usuario(Integer.parseInt(id_usuario));
            List listaUsrRoles = this.mi.getListaUsrRoles(usuario_rol);
            modelo.put("listaUsrRoles", listaUsrRoles);
            modelo.put("id_usuario", id_usuario);

            return new ModelAndView("administrarusuarios/ListarRolUsuarios", modelo);
        }
        return new ModelAndView("administrarusuarios/ListarRolUsuarios", modelo);
    }
}
