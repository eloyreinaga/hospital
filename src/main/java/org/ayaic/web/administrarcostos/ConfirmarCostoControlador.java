package org.ayaic.web.administrarcostos;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Costos;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ConfirmarCostoControlador {

    private final MiFacade mi;

    public ConfirmarCostoControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/ConfirmarCosto.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String accion1 = request.getParameter("accion1");

        String id_costo = request.getParameter("id_costo");
        String costo = request.getParameter("costo");
        String costo_unit = request.getParameter("costo_unit");
        String id_rubro = request.getParameter("id_rubro");
        String rubro = request.getParameter("rubro");
        String normales = request.getParameter("normales");
        String defecto = request.getParameter("defecto");
        String muestra = request.getParameter("muestra");
        String id_laboratorio = request.getParameter("id_laboratorio");
        String id_estado = request.getParameter("id_estado");
        String id_prestacion = request.getParameter("id_prestacion");
        String id_nivel = request.getParameter("id_color");
        String urgencias = request.getParameter("urgencias");

        if (defecto != null) {
            defecto = defecto.trim();
        }

        Costos pai = new Costos();

        if ("Adicionar".equals(accion)) {
            pai.setId_rubro(Integer.parseInt(id_rubro));
            //    pai.setId_costo(Integer.parseInt(id_costo));
            pai.setCosto(costo);
            pai.setCosto_unit(Float.parseFloat(costo_unit));
            pai.setNormales(normales);
            pai.setDefecto(defecto);
            pai.setMuestra(muestra);
            if ("".equals(id_prestacion) || id_prestacion == null) {
                id_prestacion = "0";
            }
            if ("".equals(urgencias) || urgencias == null) {
                urgencias = "0";
            }
            if ("6".equals(id_rubro)) {
                pai.setId_laboratorio(Integer.parseInt(id_laboratorio));
                pai.setId_estado(id_estado);
                pai.setId_prestacion(Integer.parseInt(id_prestacion));
                pai.setId_nivel(Integer.parseInt(id_nivel));
                pai.setEmergencias(Integer.parseInt(urgencias));
            } else {
                pai.setId_laboratorio(0);
                pai.setId_estado("A");
                pai.setId_prestacion(0);
                pai.setId_nivel(0);
                pai.setEmergencias(0);
            }
            pai.setRubro(rubro);
            modelo.put("dato", pai);
            modelo.put("accion", accion);
            modelo.put("accion1", accion1);
        }
        if ("Modificar".equals(accion)) {
            pai.setId_costo(Integer.parseInt(id_costo));
            pai.setId_rubro(Integer.parseInt(id_rubro));
            pai.setCosto(costo);
            pai.setNormales(normales);
            pai.setDefecto(defecto);
            pai.setMuestra(muestra);
            if ("6".equals(id_rubro)) {
                if ("".equals(urgencias) || urgencias == null) {
                    urgencias = "0";
                }
                pai.setId_laboratorio(Integer.parseInt(id_laboratorio));
                pai.setId_estado(id_estado);
                pai.setId_prestacion(Integer.parseInt(id_prestacion));
                pai.setId_nivel(Integer.parseInt(id_nivel));
                pai.setEmergencias(Integer.parseInt(urgencias));
            } else {
                pai.setId_laboratorio(0);
                pai.setId_estado("A");
                pai.setId_prestacion(0);
                pai.setId_nivel(0);
                pai.setEmergencias(0);
            }
            pai.setRubro(rubro);
            pai.setCosto_unit(Float.parseFloat(costo_unit));
            modelo.put("dato", pai);
            modelo.put("rubro", rubro);
            modelo.put("id_rubro", id_rubro);
            modelo.put("sw", request.getParameter("sw"));
            modelo.put("accion", accion);
            modelo.put("accion1", accion1);
        }

        if ("Eliminar".equals(accion)) {
            Costos costo1 = new Costos();
            costo1.setId_costo(Integer.parseInt(id_costo));
            costo1.setCod_esta(cliente.getCod_esta());
            Costos buscarCosto = this.mi.getDatosCosto(costo1);
            modelo.put("dato", buscarCosto);
            modelo.put("accion", accion);
            modelo.put("sw1", request.getParameter("sw1"));
            modelo.put("id_costo", id_costo);
        }
        return new ModelAndView("administrarcostos/ConfirmarCosto", modelo);

    }
}
