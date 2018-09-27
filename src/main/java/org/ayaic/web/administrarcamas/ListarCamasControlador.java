package org.ayaic.web.administrarcamas;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Camas;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Salas;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ListarCamasControlador {

    private final MiFacade mi;

    public ListarCamasControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/ListarCamas.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion2 = request.getParameter("accion2");
        String id_cama = request.getParameter("id_cama");
        String id_sala = request.getParameter("id_sala");
        String id_sala2 = request.getParameter("id_sala2");
        String id_piso2 = request.getParameter("id_piso2");
        String sala = request.getParameter("sala");
        String id_rubro = request.getParameter("id_rubro");

        Salas dsala = new Salas();
        dsala.setId_piso(cliente.getId_piso());
        dsala.setCod_esta(cliente.getCod_esta());
        dsala.setTipo("T");   /////getListarPisosTotal
        List listarPisos = this.mi.getListarPisosTotal(dsala);
        modelo.put("listarPisos", listarPisos);
        //modelo.put("id_piso", "0");
        List listarSalas = this.mi.getListarSalasLibres(dsala);
        modelo.put("listarSalas", listarSalas);
        modelo.put("id_piso2", id_piso2);
        modelo.put("id_sala2", id_sala2);

        if ("CamaOcupada".equals(accion2) || "CamaLibre".equals(accion2)) {
            Camas modcama = new Camas();
            modcama.setId_cama(Integer.parseInt(id_cama));
            modcama.setCod_esta(cliente.getCod_esta());
            modcama.setTipo("M");  ////setModificarCamaVacia
            modcama.setEstado(0);
            if ("CamaLibre".equals(accion2)) {
                modcama.setEstado(1);
            }
            this.mi.setModificarCamaVacia(modcama);

            Camas listcama = new Camas();
            listcama.setCod_esta(cliente.getCod_esta());
            if (id_sala2 != null && !"0".equals(id_sala2)) {
                listcama.setCod_esta(cliente.getCod_esta());
                listcama.setId_sala(Integer.parseInt(id_sala2));
                listcama.setTipo("T");    ////getListarCamasTotal
                List listarCamasSala2 = this.mi.getListarCamasTotal(listcama);  ///getListarCamas
                modelo.put("listarCamasSala", listarCamasSala2);
            }
        }

        if (id_piso2 != null && !"0".equals(id_piso2)) {
            modelo.put("id_piso", id_piso2);
            dsala.setId_piso(Integer.parseInt(id_piso2));
            dsala.setTipo("T"); ////getListarSalaPisoTotal
            List listarSalas2 = this.mi.getListarSalaPisoTotal(dsala);
            modelo.put("listarSalas", listarSalas2);

            Camas listcama = new Camas();
            //listcama.setCod_esta(cliente.getCod_esta()) ;
            //listcama.setId_piso(Integer.parseInt(id_piso2));
            //listcama.setTipo("P");    ////getListarCamaPiso
            //List listarCamasSala = this.mi.getListarCamaPiso(listcama);
            //modelo.put("listarCamasSala", listarCamasSala);
            modelo.put("id_piso2", id_piso2);

            if (id_sala2 != null && !"0".equals(id_sala2)) {
                listcama.setCod_esta(cliente.getCod_esta());
                listcama.setId_sala(Integer.parseInt(id_sala2));
                listcama.setTipo("T");    ////getListarCamasTotal
                List listarCamasSala2 = this.mi.getListarCamasTotal(listcama);  ///getListarCamas
                modelo.put("listarCamasSala", listarCamasSala2);
                modelo.put("id_sala2", id_sala2);
            }
        }

        return new ModelAndView("administrarcamas/ListarCamas", modelo);

    }
}
