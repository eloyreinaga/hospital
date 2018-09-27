package org.ayaic.web.administrarsalas;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Salas;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ConfirmarSalaControlador {

    private final MiFacade mi;

    public ConfirmarSalaControlador(MiFacade mi) {
        this.mi = mi;
    }
    
    @RequestMapping("/ConfirmarSala.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String accion1 = request.getParameter("accion1");

        String id_sala = request.getParameter("id_sala");
        String sala = request.getParameter("sala");
        String id_estado = request.getParameter("id_estado");
        Salas pai = new Salas();

        if ("Adicionar".equals(accion)) {

//      pai.setId_sala(Integer.parseInt(id_sala)) ;
            pai.setSala(sala);

            modelo.put("dato", pai);
            modelo.put("accion", accion);
            modelo.put("accion1", accion1);

        }

        if ("Modificar".equals(accion)) {

            pai.setId_sala(Integer.parseInt(id_sala));
            pai.setSala(sala);
            pai.setId_estado(id_estado);

            modelo.put("dato", pai);
            modelo.put("sw", request.getParameter("sw"));
            modelo.put("accion", accion);
            modelo.put("accion1", accion1);
        }

        if ("Eliminar".equals(accion)) {
            Salas buscarsala = this.mi.getDatosSala(Integer.parseInt(id_sala)); // saca un registro a ser modificado
            modelo.put("dato", buscarsala);

            modelo.put("accion", accion);
            modelo.put("sw1", request.getParameter("sw1"));
            modelo.put("id_parentesco", id_sala);
        }

        return new ModelAndView("administrarsalas/ConfirmarSala", modelo);

    }
}
