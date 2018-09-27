package org.ayaic.web.administrarsalas;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Salas;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ListarSalasControlador {

    private final MiFacade mi;

    public ListarSalasControlador(MiFacade mi) {
        this.mi = mi;
    }
    
    @RequestMapping("/ListarSalas.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String id_piso = request.getParameter("id_piso");

        Salas dsala = new Salas();
        dsala.setCod_esta(cliente.getCod_esta());
        dsala.setTipo("T");   /////getListarPisosTotal
        List listarPisos = this.mi.getListarPisosTotal(dsala);
        modelo.put("listarPisos", listarPisos);

        if (id_piso != null && !"0".equals(id_piso)) {
            modelo.put("id_piso", id_piso);
            dsala.setId_piso(Integer.parseInt(id_piso));
            dsala.setTipo("T"); ////getListarSalaPisoTotal
            List listarSalas2 = this.mi.getListarSalaPisoTotal(dsala);
            modelo.put("listarSalas", listarSalas2);

        }

        modelo.put("rol", Integer.toString(cliente.getId_rol2()));

        return new ModelAndView("administrarsalas/ListarSalas", modelo);

    }
}
