package org.ayaic.web.administrarpersonas;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Localidades;
import org.ayaic.domain.Menues;
import org.ayaic.domain.Personas;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ListarPersonasControlador {

    private final MiFacade mi;

    public ListarPersonasControlador(MiFacade mi) {
        this.mi = mi;
    }
    
    @RequestMapping("/ListarPersonas.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");

        String _nombres = cliente.getNombres();
        String boton = request.getParameter("boton");
        String nombres = request.getParameter("nombre");
        String id_estado = request.getParameter("id_estado");

        /*Corvus Aqui dato para el paginador*/
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
        /*Corvus Fin dato para el paginador*/

        Localidades local = new Localidades();
        List Estab1 = this.mi.getListarEsta(local);
        Localidades datoestab = (Localidades) Estab1.get(0);
        modelo.put("codesta", Integer.toString(cliente.getCod_esta()));
        modelo.put("id_rol", Integer.toString(cliente.getId_rol2()));

        Personas persona = new Personas();

        /*Corvus Aqui dato para el paginador*/
        persona.setPagina(pagina);
        /*Corvus Fin dato para el paginador*/

        persona.setCod_esta(cliente.getCod_esta());
        List listarPersonas = this.mi.getListarPersonas(persona);
        modelo.put("listarPersonas", listarPersonas);
        Menues usuario_rol = new Menues();
        usuario_rol.setId_usuario(cliente.getId_usuario());
        List listaUsrRoles = this.mi.getListaUsrRoles(usuario_rol);
        for (int i = 0; i < listaUsrRoles.size(); i++) {
            Menues datorol = (Menues) listaUsrRoles.get(i);
            if (datorol.getId_rol() == 27 || datorol.getId_rol() == 32) {
                persona.setDip("E");  ///getListarPersonasEstab
                List listarPersonas2 = this.mi.getListarPersonasEstab(persona);
                modelo.put("listarPersonas", listarPersonas2);
            }
        }
        /*Corvus Aqui el Listado*/

        if (!listarPersonas.isEmpty()) { ///|| listarPersonas2
            Personas datoper = (Personas) listarPersonas.get(0);
            modelo.put("total", Integer.toString(datoper.getSuma1()));
            if ("Ultimo".equals(boton)) {
                if (pagina == 0) {
                    pagina = 10;
                } else {
                    pagina = 10 * (int) (datoper.getSuma1() / 10);
                }
                modelo.put("pagina", Integer.toString(10 * (int) (datoper.getSuma1() / 10)));
                persona.setPagina(pagina);
                List listarPersonas2 = this.mi.getListarPersonas(persona);
                modelo.put("listarPersonas", listarPersonas2);
            }
        }

        if ("BuscarE".equals(boton)) {
            if (nombres != null || "".equals(nombres)) {
                nombres = nombres.replaceAll("\\s", "%");
                nombres = "%" + nombres + "%";
                persona.setNombres(nombres);
                persona.setDip("N");
                List listarPer = this.mi.getListarPersonasNombre(persona);
                modelo.put("listarPersonas", listarPer);
                for (int i = 0; i < listaUsrRoles.size(); i++) {
                    Menues datorol = (Menues) listaUsrRoles.get(i);
                    if (datorol.getId_rol() == 27) {
                        persona.setDip("M");
                        List listarPer2 = this.mi.getListarPersonasNombreLocal(persona);
                        modelo.put("listarPersonas", listarPer2);
                    }
                }

            }
        }

        return new ModelAndView("administrarpersonas/ListarPersonas", modelo);

    }
}
