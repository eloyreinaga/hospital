package org.ayaic.web.administrarmenu;

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
public class AdministrarMenuControlador {

    private final MiFacade mi;

    public AdministrarMenuControlador(MiFacade mi) {
        this.mi = mi;
    }
    
    @RequestMapping("/administrarMenues.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        String id_categoria;
        String id_usuario;
        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String boton = request.getParameter("boton");		//recupera el valor del boton

        if(cliente.getId_cargo() != 1 && cliente.getId_cargo() != 7){
            return new ModelAndView("Aviso", "mensaje", "Ud. no esta autorizado");
        }

        try {
            if ("Agregar".equals(boton)) {
                Menues menu = new Menues();
                menu.setId_usuario(Integer.parseInt(request.getParameter("id_usuario_rol_s")));
                menu.setId_rol(Integer.parseInt(request.getParameter("id_rol_s")));
                menu.setUlt_usuario(cliente.getId_usuario());
                String[] id_enlace_s = request.getParameterValues("id_enlace_s");
                if (id_enlace_s != null) {
                    for (int i = 0; i < id_enlace_s.length; i++) {
                        menu.setId_enlace(Integer.parseInt(id_enlace_s[i]));
                        if (this.mi.getUsrRolEnlace(menu) == null) {
                            this.mi.setCrearMenu(menu);	//AGREGA UN ENLACE A UN MENU
                        }
                    }
                }
            }
        } catch (Exception e) {
        }

        if ("Eliminar".equals(boton)) {
            Menues menu = new Menues();
            menu.setId_usuario(Integer.parseInt(request.getParameter("id_usuario_rol_s")));
            menu.setId_rol(Integer.parseInt(request.getParameter("id_rol_s")));
            String[] id_enlace_u = request.getParameterValues("id_enlace_u");
            if (id_enlace_u != null) {
                for (int i = 0; i < id_enlace_u.length; i++) {
                    menu.setId_enlace(Integer.parseInt(id_enlace_u[i]));
                    this.mi.setEliminarMenu(menu);	//AGREGA UN ENLACE A UN MENU
                }
            }
        }
        // LISTA DE ENLACES DE UNA CATEGORIA
        if (request.getParameter("id_usuario_rol_s") != null && request.getParameter("id_categoria_s") != null && request.getParameter("id_rol_s") != null) {		//PREGUNTA SI UNA CATEGORIA A SIDO ELEGIDA
            id_categoria = request.getParameter("id_categoria_s");	//RECUPERA LA CATEGORIA SELECCIONADA

            List listaEnlaces = this.mi.getListaEnlaces(Integer.parseInt(id_categoria));
            modelo.put("listaEnlaces", listaEnlaces);

            modelo.put("id_categoria", id_categoria);

            Menues menu_usr = new Menues();
            menu_usr.setId_usuario(Integer.parseInt(request.getParameter("id_usuario_rol_s")));
            menu_usr.setId_rol(Integer.parseInt(request.getParameter("id_rol_s")));
            menu_usr.setId_categoria(Integer.parseInt(request.getParameter("id_categoria_s")));

            List listaUsrRolEnlaces = this.mi.getListaUsrRolEnlaces(menu_usr);
            modelo.put("listaUsrRolEnlaces", listaUsrRolEnlaces);
        }

        // LISTA DE ROLES DEL USUARIO    
        if (request.getParameter("id_usuario_rol_s") != null) {

            id_usuario = request.getParameter("id_usuario_rol_s");

            Menues usuario_rol = new Menues();
            usuario_rol.setId_usuario(Integer.parseInt(id_usuario));

            List listaUsrRoles = this.mi.getListaUsrRoles(usuario_rol);
            modelo.put("listaUsrRoles", listaUsrRoles);

            modelo.put("id_usuario", id_usuario);
        }

        // PARA SELECCIONAR EL ROL ELEGIDO
        modelo.put("id_rol", request.getParameter("id_rol_s"));

        // LISTA DE CATEGORIAS
        List listaCategorias = this.mi.getListaCategorias();
        modelo.put("listaCategorias", listaCategorias);

        // LISTA DE USUARIOS
        Usuarios usuario = new Usuarios();
        usuario.setId_usuario(cliente.getId_usuario());
        usuario.setCod_esta(cliente.getCod_esta());
        List listaUsuarios = this.mi.getListaUsuarios(usuario);
        modelo.put("listaUsuarios", listaUsuarios);
        if (cliente.getId_rol2() != 1) {
            List listaUsuarios2 = this.mi.getListaUsuariosLocal2(usuario);
            modelo.put("listaUsuarios", listaUsuarios2);
        }

        return new ModelAndView("administrarmenu/AdministrarMenu", modelo);
    }
}
