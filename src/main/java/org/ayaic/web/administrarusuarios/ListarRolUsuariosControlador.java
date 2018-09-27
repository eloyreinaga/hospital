package org.ayaic.web.administrarusuarios;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Menues;
import org.ayaic.domain.Personas;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ListarRolUsuariosControlador {

    private final MiFacade mi;

    public ListarRolUsuariosControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/ListarRolUsuarios.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String id_usuario = request.getParameter("id_usuario");
        String id_persona = request.getParameter("id_persona");

        if (id_usuario == null) {
            id_usuario = "";
        }

        if (!"".equals(id_usuario)) {
            Menues usuario_rol = new Menues();
            usuario_rol.setId_usuario(Integer.parseInt(id_usuario));
            List listaUsrRoles = this.mi.getListaUsrRoles(usuario_rol);
            modelo.put("listaUsrRoles", listaUsrRoles);
        }

        Personas persona = this.mi.getBuscarPersona(Integer.parseInt(id_persona));

        modelo.put("id_usuario", id_usuario);
        modelo.put("nombreusr", persona.getPaterno() + " " + persona.getMaterno() + " " + persona.getNombres());
        return new ModelAndView("administrarusuarios/ListarRolUsuarios", modelo);

    }
}
