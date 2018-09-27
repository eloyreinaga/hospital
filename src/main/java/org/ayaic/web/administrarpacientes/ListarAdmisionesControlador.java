package org.ayaic.web.administrarpacientes;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Cuadernos;
import org.ayaic.domain.Localidades;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ListarAdmisionesControlador {

    private final MiFacade mi;

    public ListarAdmisionesControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/ListarAdmisiones.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String accion1 = request.getParameter("accion1");
        String boton = request.getParameter("boton");
        String id_consultorio = request.getParameter("id_consultorio");
        String id_persona = request.getParameter("id_persona");
        String id_paciente = request.getParameter("id_paciente");
        String id_reservacion = request.getParameter("id_reservacion");
        String id_historial = request.getParameter("id_historial");
        String id_admi = request.getParameter("id_admi");
        String codigo = request.getParameter("codigo");
        String[] dias = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
        String[] meses = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        String[] horas = {"00", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"};
        String[] minutos = {"00", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"};
        Date fecha1 = new Date();
        int anioq = fecha1.getYear() + 1900;
        String[] anios = {Integer.toString(anioq), Integer.toString(anioq + 1),};

        Localidades local = new Localidades();
        List Estab1 = this.mi.getListarEsta(local);
        Localidades datoestab = (Localidades) Estab1.get(0);
        local.setArea("R");
        local.setId_persona(1);
        List EstabRef = this.mi.getListarEstabRef(local);
        modelo.put("listarestab", EstabRef);
        modelo.put("datoestab", datoestab);
        List listarSeguros = this.mi.getListarSeguros("A");
        modelo.put("listarSeguros", listarSeguros);

        Cuadernos datot = new Cuadernos();
        datot.setTipoconsulta("G");    ////getListarAdmisionTot
        datot.setCod_esta(cliente.getCod_esta());
        datot.setNombres("%");
        datot.setId_persona(cliente.getId_persona());
        List listarAdm = this.mi.getListarAdmisionTot(datot);
        modelo.put("listarAdmi", listarAdm);

        if ("BuscarAdm".equals(boton)) {
            String nombres = request.getParameter("nombre");
            nombres = nombres.replaceAll("\\s", "%");
            nombres = "%" + nombres + "%";
            datot.setNombres(nombres);
            List listarAdm2 = this.mi.getCuadernoC1(datot);
            modelo.put("listarAdmi", listarAdm2);

        }

        modelo.put("id_consultorio", id_consultorio);
        modelo.put("id_reservacion", id_reservacion);
        modelo.put("id_persona", id_persona);
        modelo.put("id_paciente", id_paciente);
        modelo.put("dato", cliente);

        return new ModelAndView("administrarpacientes/ListarAdmisiones", modelo);
    }
}
