package org.ayaic.web.administrarcamas;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Camas;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NuevoCamaControlador {

    private final MiFacade mi;

    public NuevoCamaControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/NuevoCama.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String accion2 = request.getParameter("accion2");
        String id_cama = request.getParameter("id_cama");
        String id_sala = request.getParameter("id_sala");
        String sala = request.getParameter("sala");
        String id_rubro = request.getParameter("id_rubro");

        if (id_sala == null || "".equals(id_sala)) {
            id_sala = "0";
        }
        Camas listcama = new Camas();
        listcama.setId_sala(Integer.parseInt(id_sala));
        listcama.setTipo("T");
        List listarCamasSala = this.mi.getListarCamas(listcama);
        modelo.put("listarCamasSala", listarCamasSala);
        modelo.put("rol", Integer.toString(cliente.getId_rol2()));

        //Para la primera vez que entra a la pagina
        if ((request.getParameter("id_cama") != null) && ("Modificar".equals(accion)) && (request.getParameter("recargado") == null)) {
            //Sacamos los datos del cama
            Camas buscarCama = this.mi.getDatosCama(Integer.parseInt(id_cama)); // saca un registro a ser modificado
            modelo.put("buscarCama", buscarCama);
            modelo.put("sw", request.getParameter("sw"));
        }

        if ("CamaOcupada".equals(accion2) || "CamaLibre".equals(accion2)) {
            Camas modcama = new Camas();
            modcama.setId_cama(Integer.parseInt(id_cama));
            modcama.setCod_esta(cliente.getCod_esta());
            modcama.setTipo("M");
            modcama.setEstado(0);
            if ("CamaLibre".equals(accion2)) {
                modcama.setEstado(1);
            }
            this.mi.setModificarCama(modcama);
            Camas listcama2 = new Camas();
            listcama2.setId_sala(Integer.parseInt(id_sala));
            listcama2.setCod_esta(cliente.getCod_esta());
            listcama2.setTipo("T");
            List listarCamasSala2 = this.mi.getListarCamas(listcama2);
            modelo.put("listarCamasSala", listarCamasSala);
        }

        List listarCamasSala3 = this.mi.getListarCamas(listcama);
        modelo.put("listarCamasSala", listarCamasSala3);

        modelo.put("id_cama", id_cama);
        modelo.put("sala", sala);
        modelo.put("id_sala", id_sala);
        modelo.put("accion", accion);
        return new ModelAndView("administrarcamas/NuevoCama", modelo);
    }
}
