package org.ayaic.web.administrarusuarios;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Menues;
import org.ayaic.domain.Personas;
import org.ayaic.domain.Usuarios;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NuevoUsuarioControlador {

    private final MiFacade mi;

    public NuevoUsuarioControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/NuevoUsuario.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String _id_usuario = Integer.toString(cliente.getId_usuario());

        String accion = request.getParameter("accion");
        String id_usuario = request.getParameter("id_usuario");
        String id_persona = request.getParameter("id_persona");

        modelo.put("id_usuario", request.getParameter("id_usuario"));
        modelo.put("id_persona", request.getParameter("id_persona"));
        //lista de persona que pueden ser ususarios del sistema
        Personas persona = new Personas();
        persona.setCod_esta(cliente.getCod_esta());
        Menues usuario_rol = new Menues();
        usuario_rol.setId_usuario(cliente.getId_usuario());
        List listaUsrRoles = this.mi.getListaUsrRoles(usuario_rol);
        persona.setDip("U");  ///getListarPersonasUsua
        persona.setCod_esta(cliente.getCod_esta());
        List listarPersonas = this.mi.getListarPersonasUsua(persona);
        modelo.put("listarPersonas", listarPersonas);
        for (int i = 0; i < listaUsrRoles.size(); i++) {
            Menues datorol = (Menues) listaUsrRoles.get(i);
            if (datorol.getId_rol() == 27) {
                persona.setDip("O");///getListarPersonasUsuaLocal
                List listarPersonas2 = this.mi.getListarPersonasUsuaLocal(persona);
                modelo.put("listarPersonas", listarPersonas2);
            }
        }

        //Para la primera vez que entra a la pagina
        if ((request.getParameter("id_usuario") != null) && ("Modificar".equals(accion)) && (request.getParameter("recargado") == null)) {
            //Sacamos los datos del usuario
            Usuarios buscarusuario = this.mi.getDatosUsuario(Integer.parseInt(id_usuario)); // saca un registro a ser modificado
            modelo.put("buscarusuario", buscarusuario);
            modelo.put("id_estado", buscarusuario.getId_estado());
            modelo.put("sw", request.getParameter("sw"));
            persona.setDip("B");  ///getListarPersonaUnico
            persona.setId_persona(Integer.parseInt(id_persona));
            List listarPersonas2 = this.mi.getListarPersonaUnico(persona);
            modelo.put("listarPersonas", listarPersonas2);
        }
        modelo.put("accion", accion);
        return new ModelAndView("administrarusuarios/NuevoUsuario", modelo);
    }
}
