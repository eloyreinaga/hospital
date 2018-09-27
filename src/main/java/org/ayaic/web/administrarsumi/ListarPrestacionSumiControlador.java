package org.ayaic.web.administrarsumi;

import java.util.HashMap;
import java.util.Map;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;

import org.ayaic.domain.Prestaciones;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ListarPrestacionSumiControlador {

    private final MiFacade mi;

    public ListarPrestacionSumiControlador(MiFacade mi) {
        this.mi = mi;
    }
    
    @RequestMapping("/ListarPrestacionSumi.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();
        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String accion2 = request.getParameter("accion2");
        String nombresPres = request.getParameter("nombresPres");
        String nombres = request.getParameter("nombres");
        String boton = request.getParameter("boton");
        String expedido = request.getParameter("expedido");

        if (nombresPres == null) {
            nombresPres = "";
        }
        if ("BuscarNom".equals(boton) && !"".equals(accion2)) {
            accion = accion2;
        }

        nombres = nombresPres;
        nombresPres = nombresPres.replaceAll("\\s", "%");
        nombresPres = "%" + nombresPres + "%";

        Prestaciones datopres = new Prestaciones();
        datopres.setPrestacion(nombresPres);
        datopres.setCod_esta(cliente.getCod_esta());
        List listPrestacion = this.mi.getListarPrestacionesSumi(datopres);
        modelo.put("listarPrestaciones", listPrestacion);

        if ("Habilitar".equals(accion)) {
            datopres.setReferido("H");  ////getListarPrestacionesSumiHabilitados
            List listPrestacion2 = this.mi.getListarPrestacionesSumiH(datopres);
            modelo.put("listarPrestaciones", listPrestacion2);
            accion2 = accion;
        }

        if ("habilita".equals(accion) || "deshabilita".equals(accion)) {
            String id_prestacion = request.getParameter("id_prestacion");

            //Prestaciones datopres = new Prestaciones();
            datopres.setId_prestacion(Integer.parseInt(request.getParameter("id_prestacion")));
            datopres.setReferido("H");
            if ("habilita".equals(accion)) {
                datopres.setDescripcion("N2012");
            } else {
                datopres.setDescripcion("N20XXX");
            }
            this.mi.setHabilitaPrestacion(datopres);
            datopres.setReferido("H");  ////getListarPrestacionesSumiH
            List listPrestacion2 = this.mi.getListarPrestacionesSumiH(datopres);
            modelo.put("listarPrestaciones", listPrestacion2);
            accion = accion2;
        }

        modelo.put("nombresPres", nombres);
        modelo.put("expedido", expedido);
        modelo.put("accion2", accion2);
        modelo.put("accion", accion);

        return new ModelAndView("administrarsumi/ListarPrestacionSumi", modelo);
    }
}
