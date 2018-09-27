package org.ayaic.web.administrarcarpetas;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NuevoCarpetaControlador {

    private final MiFacade mi;

    public NuevoCarpetaControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/NuevoCarpeta.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String boton = request.getParameter("boton");
        String accion = request.getParameter("accion");

        if ("BuscarN".equals(boton)) {
            String nombres = request.getParameter("nombre");
            nombres = nombres.trim();
            nombres = nombres.replaceAll(" +", " ");///quita espacios en blanco entre cadenas
            nombres = nombres.replaceAll("\\s", ":*&");
            nombres = nombres.replaceAll("ñ", "n");
            nombres = nombres.replaceAll("Ñ", "N");
            if (nombres.length() > 0) {
                nombres = nombres + ":*";
            }
            Pacientes paciente = new Pacientes();
            paciente.setNombres(nombres);

            paciente.setId_estado(request.getParameter("id_estado"));

            List listarPaises = this.mi.getListarPacientes(paciente);
            modelo.put("listaPacientes", listarPaises);
        }
        if ("BuscarH".equals(boton)) {

            String hcl = request.getParameter("hcl");
            Pacientes paciente = new Pacientes();
            paciente.setHcl(Integer.parseInt(hcl));

            List listarPaises = this.mi.getListarPacientesHC(paciente);
            modelo.put("listaPacientes", listarPaises);
        }

        modelo.put("accion", accion);
        return new ModelAndView("administrarcarpetas/NuevoCarpeta", modelo);
    }
}
