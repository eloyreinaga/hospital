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
public class VerificarUsuario {

    private final MiFacade mi;

    public VerificarUsuario(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/verificarUsuario.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        modelo.put("nombres", cliente.getNombres());

        String sBoton = request.getParameter("boton");
        String sClave = request.getParameter("clave");

        if ("Buscar".equals(sBoton)) {
            Usuarios usuario = new Usuarios();
            usuario.setId_usuario(cliente.getId_usuario());
            usuario.setClave(sClave);
            int iResultado = this.mi.getVerificarUsuario(usuario);
            if (iResultado == 0) {
                return new ModelAndView("Aviso", "mensaje", "Clave incorrecta");
            } else {
                return new ModelAndView("administrarClaves/RecomendacionesClave", modelo);
            }
        }
        return new ModelAndView("administrarClaves/VerificarUsuario", modelo);
    }
}
