package org.ayaic.web.administrarenfermeria;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EntregaMedicamentosControlador {

    private final MiFacade mi;

    public EntregaMedicamentosControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/EntregaMedicamentos.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");

        if ("adicionar".equals(accion)) {
            modelo.put("nombres", "NOMBRE");
            modelo.put("nit", "NIT");
            modelo.put("id_historial", "0");
            modelo.put("sw", request.getParameter("sw"));
            if ("SUMI".equals(request.getParameter("sw"))) {
                modelo.put("num_cladoc", "0");
            } else {
                modelo.put("num_cladoc", "0");
            }

            return new ModelAndView("administrarenfermeria/EntregaNuevoPaciente", modelo);
        }

        if ("Terminar".equals(accion)) {

            String id_pedido = request.getParameter("id_pedido");
            // recupera el paciente a entregar
            Pacientes paciente1 = new Pacientes();
            paciente1.setId_pedido(Integer.parseInt(id_pedido));
            paciente1.setCod_esta(cliente.getCod_esta());
            Pacientes buscarPaciente = this.mi.getDatosPedido(paciente1); ////////////// 
            modelo.put("datos", buscarPaciente);
            buscarPaciente.setId_estado("E");
            buscarPaciente.setId_dispensa(cliente.getId_persona());
            this.mi.setModificarPedido(buscarPaciente);

        }

        return new ModelAndView("administrarenfermeria/EntregaMedicamentos", modelo);

    }
}
