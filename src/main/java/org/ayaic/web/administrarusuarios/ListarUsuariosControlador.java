package org.ayaic.web.administrarusuarios;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Localidades;
import org.ayaic.domain.Menues;
import org.ayaic.domain.Usuarios;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ListarUsuariosControlador {

    private final MiFacade mi;

    public ListarUsuariosControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/ListarUsuarios.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String nombres = request.getParameter("nombre");
        String boton = request.getParameter("boton");

        if (cliente.getId_cargo() != 1 && cliente.getId_cargo() != 7) {
            return new ModelAndView("Aviso", "mensaje", "Ud. no esta autorizado Listar Usuarios");
        }

        int pagina = 0;
        try {
            pagina = Integer.parseInt(request.getParameter("pagina"));

        } catch (Exception e) {
        }
        if ("Primero".equals(boton)) {
            pagina = 0;
        }
        if ("Anterior".equals(boton)) {
            if (pagina == 0) {
                pagina = 0;
            } else {
                pagina = pagina - 10;
            }
        }
        if ("Siguiente".equals(boton)) {
            if (pagina == 0) {
                pagina = 10;
            } else {
                pagina = pagina + 10;
            }
        }
        if ("Ultimo".equals(boton)) {
            if (pagina == 0) {
                pagina = 10;
            } else {
                pagina = pagina + 10;
            }
        }
        modelo.put("pagina", Integer.toString(pagina));

        Usuarios usuario = new Usuarios();
        usuario.setId_usuario(cliente.getId_usuario());
        usuario.setCod_esta(cliente.getCod_esta());
        Menues usuario_rol = new Menues();
        usuario_rol.setId_usuario(cliente.getId_usuario());
        List listaUsrRoles = this.mi.getListaUsrRoles(usuario_rol);
        usuario.setPagina(pagina);
        List listarUsuarios = this.mi.getListaUsuarios(usuario);
        modelo.put("listarUsuarios", listarUsuarios);
        for (int i = 0; i < listaUsrRoles.size(); i++) {
            Menues datorol = (Menues) listaUsrRoles.get(i);
            if (datorol.getId_rol() == 27) {
                List listarUsuarios2 = this.mi.getListaUsuariosLocal(usuario);
                modelo.put("listarUsuarios", listarUsuarios2);
            }
        }

        if (!listarUsuarios.isEmpty()) {
            Usuarios datoper = (Usuarios) listarUsuarios.get(0);
            modelo.put("total", Integer.toString(datoper.getSuma1()));
            if ("Ultimo".equals(boton)) {
                if (pagina == 0) {
                    pagina = 10;
                } else {
                    pagina = 10 * (int) (datoper.getSuma1() / 10);
                }
                modelo.put("pagina", Integer.toString(10 * (int) (datoper.getSuma1() / 10)));
                usuario.setPagina(pagina);
                List listarUsuarios2 = this.mi.getListaUsuarios(usuario);
                modelo.put("listarUsuarios", listarUsuarios2);
            }
        }

        Localidades local = new Localidades();
        List Estab1 = this.mi.getListarEsta(local);
        Localidades datoestab = (Localidades) Estab1.get(0);
        modelo.put("codesta", Integer.toString(cliente.getCod_esta()));
        modelo.put("id_rol", Integer.toString(cliente.getId_rol2()));

        if ("BuscarE".equals(boton)) {
            if (nombres != null || "".equals(nombres)) {
                nombres = nombres.replaceAll("\\s", "%");
                nombres = "%" + nombres + "%";
                usuario.setNombres(nombres);
                List listarUser = this.mi.getListaUsuariosNombre(usuario);
                modelo.put("listarUsuarios", listarUser);
                for (int i = 0; i < listaUsrRoles.size(); i++) {
                    Menues datorol = (Menues) listaUsrRoles.get(i);
                    if (datorol.getId_rol() == 27) {
                        List listarUser2 = this.mi.getListaUsuariosNombreLocal(usuario);
                        modelo.put("listarUsuarios", listarUser2);
                    }
                }

            }
        }

        return new ModelAndView("administrarusuarios/ListarUsuarios", modelo);

    }
}
