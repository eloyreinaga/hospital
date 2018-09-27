package org.ayaic.web.administrarparentescos;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Parentescos;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ConfirmarParentescoControlador {

    private final MiFacade mi;

    public ConfirmarParentescoControlador(MiFacade mi) {
        this.mi = mi;
    }
    
    @RequestMapping("ConfirmarParentesco.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String accion1 = request.getParameter("accion1");

        String id_parentesco = request.getParameter("id_parentesco");
        String parentesco = request.getParameter("parentesco");
        String id_estado = request.getParameter("id_estado");
        Parentescos pai = new Parentescos();

        if ("Adicionar".equals(accion)) {

            pai.setId_parentesco(Integer.parseInt(id_parentesco));
            pai.setParentesco(parentesco);

            modelo.put("dato", pai);
            modelo.put("accion", accion);
            modelo.put("accion1", accion1);

        }

        if ("Modificar".equals(accion)) {

            pai.setId_parentesco(Integer.parseInt(id_parentesco));
            pai.setParentesco(parentesco);
            pai.setId_estado(id_estado);

            modelo.put("dato", pai);
            modelo.put("sw", request.getParameter("sw"));
            modelo.put("accion", accion);
            modelo.put("accion1", accion1);
        }

        if ("Eliminar".equals(accion)) {
            Parentescos buscarparentesco = this.mi.getDatosParentesco(Integer.parseInt(id_parentesco)); // saca un registro a ser modificado
            modelo.put("dato", buscarparentesco);

            modelo.put("accion", accion);
            modelo.put("sw1", request.getParameter("sw1"));
            modelo.put("id_parentesco", id_parentesco);
        }

        return new ModelAndView("administrarparentescos/ConfirmarParentesco", modelo);

    }
}
