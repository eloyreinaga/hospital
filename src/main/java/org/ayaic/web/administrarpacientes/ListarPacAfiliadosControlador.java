package org.ayaic.web.administrarpacientes;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Localidades;
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ListarPacAfiliadosControlador {

    private final MiFacade mi;

    public ListarPacAfiliadosControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/ListarAsegurados.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String boton = request.getParameter("boton");

        Localidades localidad = new Localidades();
        List Estab1 = this.mi.getListarEsta(localidad);
        Localidades datoestab = (Localidades) Estab1.get(0);

        if ("Buscar Asegurado".equals(boton)) {
            String nombres = request.getParameter("nombre");
            String nombre = request.getParameter("nombre");
            nombres = nombres.trim();
            nombres = nombres.replaceAll(" +", " ");///quita espacios en blanco entre cadenas
            nombre = nombres;
            nombres = nombres.replaceAll("\\s", ":*&");///:*&
            nombres = nombres.replaceAll("ñ", "n");
            nombres = nombres.replaceAll("Ñ", "N");
            if (nombres.length() > 0) {
                nombres = nombres + ":*";
            }
            Pacientes paciente = new Pacientes();
            paciente.setNombres(nombres);
            List listarPaciente = this.mi.getListarPacAfiliados(paciente);
            modelo.put("listaPacientes", listarPaciente);
            modelo.put("nombres", nombre);
        }

        return new ModelAndView("administrarpacientes/ListarPacAfiliados", modelo);

    }
}
