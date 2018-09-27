package org.ayaic.web.administrarcarpetas;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Carpetas;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ListarCarpetasControlador {

    private final MiFacade mi;

    public ListarCarpetasControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/ListarCarpetas.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String boton = request.getParameter("boton");
        String id_carpeta = request.getParameter("id_carpeta");
        String id_segurado = request.getParameter("id_segurado");
        String nombres = request.getParameter("nombre");

        if (nombres == null) {
            nombres = "AL";
        }
        nombres = nombres.replaceAll("\\s", "%");
        nombres = "%" + nombres + "%";
        Carpetas paciente = new Carpetas();
        paciente.setNombres(nombres);

        if ("BuscarNombre".equals(boton)) {
            paciente.setId_estado("A");
            List listarCarpetas = this.mi.getListarCarpetas(paciente);
            modelo.put("listarCarpetas", listarCarpetas);
        }

        if ("BuscarCarpeta".equals(boton)) {
            paciente.setId_estado("I");
            paciente.setId_carpeta(Integer.parseInt(id_carpeta));
            List listarCarpetas = this.mi.getListarCarpetasId(paciente);
            modelo.put("listarCarpetas", listarCarpetas);
        }

        if ("BuscarAsegurado".equals(boton)) {
            paciente.setId_estado("S");
            id_segurado = "%" + id_segurado + "%";
            paciente.setNro_registro(id_segurado);
            List listarCarpetas = this.mi.getListarCarpetasAse(paciente);
            modelo.put("listarCarpetas", listarCarpetas);
        }

        modelo.put("area", cliente.getArea());

        return new ModelAndView("administrarcarpetas/ListarCarpetas", modelo);

    }
}
