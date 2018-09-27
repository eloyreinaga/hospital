package org.ayaic.web.administrarlaboratorio;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Cuadernos;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ConfigurarEcosControlador {

    private final MiFacade mi;

    public ConfigurarEcosControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/ConfigurarEcos.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion1 = request.getParameter("accion1");
        String accion = request.getParameter("accion");
        String id_laboratorio = request.getParameter("id_laboratorio");
        String id_historial = request.getParameter("id_historial");
        String id_cuaderno = request.getParameter("id_cuaderno");
        String aspecto = request.getParameter("aspecto");
        String detalle = request.getParameter("detalle");

        modelo.put("id_laboratorio", id_laboratorio);
        modelo.put("id_historial", id_historial);
        modelo.put("id_cuaderno", id_cuaderno);
        modelo.put("aspecto", aspecto);
        modelo.put("detalle", detalle);
        Cuadernos dato = new Cuadernos();

        if ("Modificar".equals(accion)) {
            modelo.put("realizaeco", "si");
        }
        if ("Grabar".equals(accion)) {
            String resultado = request.getParameter("resultado");
            modelo.put("realizaeco", "no");
            dato.setId_cuaderno(Integer.parseInt(id_cuaderno));
            dato.setDiagnostico(resultado);
            dato.setAccion("G");  /// G llama a setModificarEcoDetalle
            dato.setCod_esta(cliente.getCod_esta());
            this.mi.setModificarEcoDetalle(dato);
        }

        if ("Configurar".equals(accion1)) {
            modelo.put("realizaeco", "no");
        }

        dato.setId_cuaderno(Integer.parseInt(id_laboratorio));
        List detalleecos = this.mi.getDetalleEcos(dato);
        modelo.put("detalleecos", detalleecos);
        modelo.put("id_laboratorio", id_laboratorio);

        return new ModelAndView("administrarlaboratorio/ConfigurarEcos", modelo);
    }
}
