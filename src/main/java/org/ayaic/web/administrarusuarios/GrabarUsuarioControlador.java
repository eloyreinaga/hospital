package org.ayaic.web.administrarusuarios;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Menues;
import org.ayaic.domain.Usuarios;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GrabarUsuarioControlador {

    private final MiFacade mi;

    public GrabarUsuarioControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/GrabarUsuario.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String _nombres = cliente.getNombres();
        String _id_usuario = Integer.toString(cliente.getId_usuario());
        String __ult_usuario = Integer.toString(cliente.getId_usuario());
        String accion1 = request.getParameter("accion1");
        String accion = request.getParameter("accion");
        String id_persona = request.getParameter("id_persona");
        String id_usuario = request.getParameter("id_usuario");
        String apodo = request.getParameter("apodo");
        String clave = request.getParameter("clave");
        String rep_clave = request.getParameter("rep_clave");
        String recordatorio = request.getParameter("recordatorio");

        if ("Adicionar".equals(accion) && "Aceptar".equals(accion1)) {
            Usuarios repetida = this.mi.getDatosUsuario(Integer.parseInt(request.getParameter("id_usuario")));  // SI EL OBJETO EXISTE ENTONCES NO ES NULO
            if (repetida == null) {
                if (("".equals(id_usuario)) || ("".equals(id_persona))) {
                    return new ModelAndView("Aviso", "mensaje", "Faltan Introducir Datos");
                }
                Usuarios datousuario = new Usuarios();
                datousuario.setId_usuario(Integer.parseInt(id_usuario));
                datousuario.setId_persona(Integer.parseInt(id_persona));
                datousuario.setUlt_usuario(Integer.parseInt(__ult_usuario));
                datousuario.setApodo(apodo);
                datousuario.setClave(clave);
                datousuario.setRecordatorio(recordatorio);
                try {
                    this.mi.setCrearUsuario(datousuario);
                } catch (Exception e) {
                    return new ModelAndView("Aviso", "mensaje", "La actualización no se cumplio, verifique los datos, el susuario y clave ya existe");
                }
                //listar Usuarios
                datousuario.setCod_esta(cliente.getCod_esta());
                Menues usuario_rol = new Menues();
                usuario_rol.setId_usuario(cliente.getId_usuario());
                List listaUsrRoles = this.mi.getListaUsrRoles(usuario_rol);
                for (int i = 0; i < listaUsrRoles.size(); i++) {
                    Menues datorol = (Menues) listaUsrRoles.get(i);
                    if (datorol.getId_rol() == 27) {
                        datousuario.setId_estado("L");
                    }
                }
                List listarUsuarios = this.mi.getListaUsuarios(datousuario);
                modelo.put("listarUsuarios", listarUsuarios);
                return new ModelAndView("administrarusuarios/ListarUsuarios", modelo);
            } else {
                return new ModelAndView("Aviso", "mensaje", "Ya existe un id_usuario igual");
            }
        }

        if ("Modificar".equals(accion) && "Aceptar".equals(accion1)) {
            if (("".equals(id_usuario)) || ("".equals(id_persona))) {
                return new ModelAndView("Aviso", "mensaje", "Faltan Introducir Datos");
            }
            Usuarios categoria_m = new Usuarios();
            categoria_m.setId_usuario(Integer.parseInt(request.getParameter("id_usuario")));
            categoria_m.setId_persona(Integer.parseInt(request.getParameter("id_persona")));
            categoria_m.setUlt_usuario(Integer.parseInt(__ult_usuario));
            categoria_m.setApodo(apodo);
            categoria_m.setClave(clave);
            categoria_m.setRecordatorio(recordatorio);

            if ("A".equals(request.getParameter("id_estado"))) {
                categoria_m.setId_estado("A");
            } else {
                categoria_m.setId_estado("B");
            }

            try {
                this.mi.setModificarUsuario(categoria_m);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "La actualización no se cumplio, verifique los datos, el susuario y clave ya existe");
            }

            //listar categorias
            Usuarios usuario = new Usuarios();
            usuario.setId_usuario(cliente.getId_usuario());
            usuario.setCod_esta(cliente.getCod_esta());
            Menues usuario_rol = new Menues();
            usuario_rol.setId_usuario(cliente.getId_usuario());
            List listaUsrRoles = this.mi.getListaUsrRoles(usuario_rol);
            for (int i = 0; i < listaUsrRoles.size(); i++) {
                Menues datorol = (Menues) listaUsrRoles.get(i);
                if (datorol.getId_rol() == 27) {
                    usuario.setId_estado("L");
                    List listarUsuarios2 = this.mi.getListaUsuariosLocal(usuario);
                    modelo.put("listarUsuarios", listarUsuarios2);
                }else{
                    List listarUsuarios = this.mi.getListaUsuarios(usuario);
                    modelo.put("listarUsuarios", listarUsuarios);
                }
            }
            
            return new ModelAndView("administrarusuarios/ListarUsuarios", modelo);
        }

        if ("Eliminar".equals(accion) && "Aceptar".equals(accion1)) {
            if ("".equals(request.getParameter("id_usuario"))) {
                return new ModelAndView("Aviso", "mensaje", "Faltan datos");
            }
            Usuarios elimina = new Usuarios();
            elimina.setId_usuario(Integer.parseInt(request.getParameter("id_usuario")));

            try {
                this.mi.setEliminarUsuario(elimina);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "El registro de usuarios a eliminar tiene dependencias");
            }

            //listar categorias
            Usuarios usuario = new Usuarios();
            usuario.setId_usuario(cliente.getId_usuario());
            usuario.setCod_esta(cliente.getCod_esta());
            Menues usuario_rol = new Menues();
            usuario_rol.setId_usuario(cliente.getId_usuario());
            List listaUsrRoles = this.mi.getListaUsrRoles(usuario_rol);
            for (int i = 0; i < listaUsrRoles.size(); i++) {
                Menues datorol = (Menues) listaUsrRoles.get(i);
                if (datorol.getId_rol() == 27) {
                    usuario.setId_estado("L");
                }
            }
            List listarUsuarios = this.mi.getListaUsuarios(usuario);
            modelo.put("listarUsuarios", listarUsuarios);
            return new ModelAndView("administrarusuarios/ListarUsuarios", modelo);
        }
        return new ModelAndView("administrarusuarios/ListarUsuarios", modelo);
    }
}
