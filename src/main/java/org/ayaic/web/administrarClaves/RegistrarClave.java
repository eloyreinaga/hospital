package org.ayaic.web.administrarClaves;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ayaic.domain.Clientes;
import org.ayaic.domain.Usuarios;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegistrarClave {

    private final MiFacade mi;

    public RegistrarClave(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/registrarClave.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        modelo.put("nombres", cliente.getNombres());

        Usuarios buscarusuario = this.mi.getDatosUsuario(cliente.getId_usuario()); // saca un registro a ser modificado
        modelo.put("apodo", buscarusuario.getApodo());

        String sBoton = request.getParameter("boton");
        String sNuevaClave = request.getParameter("nueva_clave");
        String sConfirmacionClave = request.getParameter("confirmacion_clave");

        modelo.put("id_persona", Integer.toString(buscarusuario.getId_persona()));

        if ("Aceptar".equals(sBoton)) {
            if (!sNuevaClave.equals(sConfirmacionClave)) {
                return new ModelAndView("Aviso", "mensaje", "La confirmación de la clave no coincide");
            }
            if (((sNuevaClave.trim()).length() >= 3) && ((sNuevaClave.trim()).length() <= 10)) {
                Usuarios usuario = new Usuarios();
                usuario.setId_usuario(cliente.getId_usuario());
                usuario.setClave(sNuevaClave);
                int iResultado = this.mi.setRegistrarNuevaClave(usuario);
                if (iResultado == 0) {
                    return new ModelAndView("Aviso", "mensaje", "La nueva clave no se registró");
                } else {
                    return new ModelAndView("Aviso", "mensaje", "La clave fué registrada correctamente");
                }
            } else {
                return new ModelAndView("Aviso", "mensaje", "La nueva clave debe tener un mínimo de 3 caracteres y un máximo de 10");
            }
        }
        return new ModelAndView("administrarClaves/RegistrarClave", modelo);
    }
}
