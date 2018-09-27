package org.ayaic.web.administrareciviles;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Eciviles;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ConfirmarEcivilControlador {

    private final MiFacade mi;

    public ConfirmarEcivilControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/ConfirmarEcivil.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String accion1 = request.getParameter("accion1");

        String id_ecivil = request.getParameter("id_ecivil");
        String ecivil = request.getParameter("ecivil");
        String id_estado = request.getParameter("id_estado");
        Eciviles pai = new Eciviles();

        if ("Adicionar".equals(accion)) {

            pai.setId_ecivil(Integer.parseInt(id_ecivil));
            pai.setEcivil(ecivil);

            modelo.put("dato", pai);
            modelo.put("accion", accion);
            modelo.put("accion1", accion1);

        }

        if ("Modificar".equals(accion)) {

            pai.setId_ecivil(Integer.parseInt(id_ecivil));
            pai.setEcivil(ecivil);
            pai.setId_estado(id_estado);

            modelo.put("dato", pai);
            modelo.put("sw", request.getParameter("sw"));
            modelo.put("accion", accion);
            modelo.put("accion1", accion1);
        }

        if ("Eliminar".equals(accion)) {
            Eciviles buscarecivil = this.mi.getDatosEcivil(Integer.parseInt(id_ecivil)); // saca un registro a ser modificado
            modelo.put("dato", buscarecivil);

            modelo.put("accion", accion);
            modelo.put("sw1", request.getParameter("sw1"));
            modelo.put("id_ecivil", id_ecivil);
        }

        return new ModelAndView("administrareciviles/ConfirmarEcivil", modelo);

    }
}
