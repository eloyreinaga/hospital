package org.ayaic.web.administrarhistoriales;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Consultorios;
import org.ayaic.domain.Cuadernos;
import org.ayaic.domain.Historiales;
import org.ayaic.domain.Medicamentos;
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DarAltaPacControlador {

    private final MiFacade mi;

    public DarAltaPacControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/DarAltaPac.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String swinter = request.getParameter("swinter");
        String accionc = request.getParameter("accionc");
        String boton = request.getParameter("boton");
        String nombres = request.getParameter("nombres");
        String id_paciente = request.getParameter("id_paciente");
        String id_reservacion = request.getParameter("id_reservacion");
        String id_consultorio = request.getParameter("id_consultorio");
        String id_persona = request.getParameter("id_persona");
        String codigo = request.getParameter("codigo");
        String literal = request.getParameter("literal");
        String id_cama = request.getParameter("id_cama");
        String id_historial = request.getParameter("id_historial");
        String id_historia = request.getParameter("id_historia");
        String cama = request.getParameter("cama");
        String cama_unit = request.getParameter("cama_unit");
        String id_sala = request.getParameter("id_sala");
        String[] dias = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
        String[] meses = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        String[] horas = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"};
        String[] minutos = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"};
        Date fecha1 = new Date();
        int anioq = fecha1.getYear() + 1900;
        String[] anios = {(Integer.toString(anioq)), (Integer.toString(anioq - 1))};

        int diasInter = 0;
        
        Medicamentos medid = new Medicamentos();
        medid.setCod_esta(cliente.getCod_esta());
        medid.setId_persona(Integer.parseInt(id_persona));
        List listarEnfermedadesCot = this.mi.getListarEnfermedadesCot(medid);
        modelo.put("listarEnfermedadesCot", listarEnfermedadesCot);

        Pacientes buscarPaciente = this.mi.getDatosPaciente(Integer.parseInt(id_paciente)); // saca un registro a ser modificado
        modelo.put("datos", buscarPaciente);

        modelo.put("accion", accion);
        modelo.put("sw1", request.getParameter("sw1"));
        modelo.put("id_paciente", id_paciente);
        modelo.put("id_reservacion", id_reservacion);
        modelo.put("id_consultorio", id_consultorio);
        modelo.put("id_persona", id_persona);
        modelo.put("codigo", codigo);
        modelo.put("literal", literal);
        modelo.put("id_sala", id_sala);
        modelo.put("id_cama", id_cama);
        modelo.put("swinter", swinter);

        Consultorios datosconsultorio = this.mi.getDatosConsultorio(cliente.getId_consultorio()); // saca un registro a ser modificado
        modelo.put("id_consultorio", Integer.toString(datosconsultorio.getId_consultorio()));

        Historiales datomorbi = new Historiales();
        datomorbi.setId_reservacion(Integer.parseInt(id_reservacion));
        datomorbi.setCod_esta(cliente.getCod_esta());
        List listarmorbi1 = this.mi.getListaMorbi(datomorbi);
        modelo.put("morbi", listarmorbi1);

        if (datosconsultorio.getId_cargo() == 33) {  ///
            Historiales datoi = new Historiales();
            datoi.setId_persona(Integer.parseInt(id_persona));
            datoi.setAccion("R");
            datoi.setId_historial(Integer.parseInt(id_reservacion));
            datoi.setId_cargo(3);
            datoi.setCod_esta(cliente.getCod_esta());
            this.mi.setModificarInternado(datoi);
            return new ModelAndView("Aviso", "mensaje", "El Paciente fue dado de alta Satisfactoriamente");
        }

        Cuadernos dato5 = new Cuadernos();
        dato5.setCod_esta(cliente.getCod_esta());  ///16-01-2016
        dato5.setAspecto("M");
        dato5.setId_historial(Integer.parseInt(id_reservacion));
        List listaCie = this.mi.getVerCuaderno1CieMorbi(dato5);
        String CIE10i = "";
        String CIE10e = "";
        for (int i = 0; i < listaCie.size(); i++) {
            Cuadernos cie10 = (Cuadernos) listaCie.get(i);
            if ("B".equals(cie10.getRegistro())) {
                CIE10i = cie10.getTipodent() + ";" + CIE10i;
                modelo.put("CieI", CIE10i);
            } else {
                CIE10e = cie10.getTipodent() + ";" + CIE10e;
                modelo.put("CieE", CIE10e);
            }
        }

        if (listarmorbi1.isEmpty() != true && CIE10e.length() > 2) {
            modelo.put("terminar", "si");
        }

        if ("Elegir".equals(accion)) {
            codigo = request.getParameter("ubicacion");
            literal = request.getParameter("concentra");
            int w = 0;
            int ws = 0;
            for (int i = 0; i < listarmorbi1.size(); i++) {
                Historiales datopac = (Historiales) listarmorbi1.get(i);
                if (i + 1 != datopac.getId_cargo() && w == 0) {
                    w = i + 1;
                }
                if (codigo.equals(datopac.getNombres()) && CIE10e.length() > 2) {
                    ws = 1;
                }
            }
            if (ws == 1) {
                return new ModelAndView("Aviso", "mensaje", "Este Codigo CIE10 ya fue seleccionado");
            }
            Historiales morbi = new Historiales();
            morbi.setId_historial(Integer.parseInt(id_reservacion));
            morbi.setId_persona(cliente.getId_persona());
            if (cliente.getId_especialidad() == 99) {
                morbi.setId_persona(Integer.parseInt(id_persona));
            }
            morbi.setCodigo(codigo);
            morbi.setTipo("C");
            morbi.setCod_esta(cliente.getCod_esta());
            if (w == 0) {
                morbi.setId_cargo(1 + listarmorbi1.size());
            } else {
                morbi.setId_cargo(w);
            }
            this.mi.setCrearMorbilidad(morbi);

            ///Historiales datomorbi= new Historiales();  
            datomorbi.setId_reservacion(Integer.parseInt(id_reservacion));
            datomorbi.setCod_esta(cliente.getCod_esta());
            List listarmorbi = this.mi.getListaMorbi(datomorbi);
            modelo.put("morbi", listarmorbi);
            modelo.put("accion", "Otro");

            Cuadernos dato51 = new Cuadernos();
            dato51.setCod_esta(cliente.getCod_esta());  ///16-01-2016
            dato51.setAspecto("M");
            dato51.setId_historial(Integer.parseInt(id_reservacion));
            List listaCie1 = this.mi.getVerCuaderno1CieMorbi(dato51);
            CIE10i = "";
            CIE10e = "";
            for (int i = 0; i < listaCie1.size(); i++) {
                Cuadernos cie10 = (Cuadernos) listaCie1.get(i);
                if ("B".equals(cie10.getRegistro())) {
                    CIE10i = cie10.getTipodent() + ";" + CIE10i;
                    modelo.put("CieI", CIE10i);
                } else {
                    CIE10e = cie10.getTipodent() + ";" + CIE10e;
                    modelo.put("CieE", CIE10e);
                }
            }
            if (listarmorbi.isEmpty() != true && CIE10e.length() > 2) {
                modelo.put("terminar", "si");
            }
        }

        if ("Buscar Enfermedad".equals(boton)) {
            List listarEnfermedades = this.mi.getListarEnfermedades(nombres);
            modelo.put("listarEnfermedades", listarEnfermedades);
        }
        if ("Buscar CIE10".equals(boton)) {
            List listarEnfermedades = this.mi.getListarEnfermedadesCod(nombres);
            modelo.put("listarEnfermedades", listarEnfermedades);
        }
        if ("Seguir Cuaderno.5 Internaciones".equals(accion)) {

            Cuadernos datomo = new Cuadernos();
            datomo.setCod_esta(cliente.getCod_esta());  ///16-01-2016
            datomo.setAspecto("M");
            datomo.setId_historial(Integer.parseInt(id_reservacion));
            List listaCie53 = this.mi.getVerCuaderno1CieMorbi(datomo);
            CIE10i = "";
            CIE10e = "";
            String CIE10i1 = "";
            String CIE10e2 = "";
            for (int i = 0; i < listaCie.size(); i++) {
                Cuadernos cie10 = (Cuadernos) listaCie.get(i);
                if ("B".equals(cie10.getRegistro())) {
                    CIE10i = cie10.getTipodent() + "[" + cie10.getObservaciones() + "]" + ";" + CIE10i;
                    CIE10i1 = cie10.getTipodent() + ";" + CIE10i1;
                    modelo.put("CieI", CIE10i);
                    modelo.put("CieI1", CIE10i1);
                } else {
                    CIE10e = cie10.getTipodent() + "[" + cie10.getObservaciones() + "]" + ";" + CIE10e;
                    CIE10e2 = cie10.getTipodent() + ";" + CIE10e2;
                    modelo.put("CieE", CIE10e);
                    modelo.put("CieE2", CIE10e2);
                }
            }
            //Sacar los datos de internaciones
            Cuadernos datoc5 = new Cuadernos();
            datoc5.setId_historial(Integer.parseInt(id_reservacion));
            datoc5.setCod_esta(cliente.getCod_esta());
            List listaOdon = this.mi.getPacienteCuaderno5(datoc5);
            //List listaOdon = this.mi.getPacienteCuaderno5(Integer.parseInt(id_reservacion));
            Cuadernos cuaderno5 = (Cuadernos) listaOdon.get(0);
            Date ding = cuaderno5.getFec_ingreso();
            long fechaInicialMs = ding.getTime();
            long fechaFinalMs = fecha1.getTime();
            long diferencia = fechaFinalMs - fechaInicialMs;
            double diass = Math.floor(diferencia / (1000 * 60 * 60 * 24));
            diasInter = (int) (Math.round(((float) diass)));

            modelo.put("anios", anios);
            modelo.put("meses", meses);
            modelo.put("dias", dias);
            modelo.put("horas", horas);
            modelo.put("minutos", minutos);
            modelo.put("anio", Integer.toString(cuaderno5.getFec_ingreso().getYear() + 1900));
            modelo.put("mes", Integer.toString(cuaderno5.getFec_ingreso().getMonth() + 1));
            modelo.put("dia", Integer.toString(cuaderno5.getFec_ingreso().getDate()));
            modelo.put("hora", Integer.toString(cuaderno5.getFec_ingreso().getHours()));
            modelo.put("minuto", Integer.toString(cuaderno5.getFec_ingreso().getMinutes()));
            modelo.put("anioe", Integer.toString(fecha1.getYear() + 1900));
            modelo.put("mese", Integer.toString(fecha1.getMonth() + 1));
            modelo.put("diae", Integer.toString(fecha1.getDate()));
            modelo.put("horae", Integer.toString(fecha1.getHours()));
            modelo.put("minutoe", Integer.toString(fecha1.getMinutes()));

            modelo.put("diasInter", Integer.toString(diasInter));
            modelo.put("listaExter", listaOdon);
            modelo.put("nombres", buscarPaciente.getNombres());

            return new ModelAndView("administrarcuadernos/Cuaderno5", modelo);
        }

        if ("EliminarCie10".equals(boton)) {
            String id_historiahh = request.getParameter("morbilidad");

            Historiales morbielimina = new Historiales();
            morbielimina.setId_historia(Integer.parseInt(id_historiahh));
            morbielimina.setCod_esta(cliente.getCod_esta());
            this.mi.setEliminarMorbilidad(morbielimina);
            //Historiales datomorbi= new Historiales();  
            datomorbi.setId_reservacion(Integer.parseInt(id_reservacion));
            datomorbi.setCod_esta(cliente.getCod_esta());
            List listarmorbi = this.mi.getListaMorbi(datomorbi);
            modelo.put("morbi", listarmorbi);

            Cuadernos dato51 = new Cuadernos();
            dato51.setCod_esta(cliente.getCod_esta());  ///16-01-2016
            dato51.setAspecto("M");
            dato51.setId_historial(Integer.parseInt(id_reservacion));
            List listaCie1 = this.mi.getVerCuaderno1CieMorbi(dato51);
            CIE10i = "";
            CIE10e = "";
            for (int i = 0; i < listaCie1.size(); i++) {
                Cuadernos cie10 = (Cuadernos) listaCie1.get(i);
                if ("B".equals(cie10.getRegistro())) {
                    CIE10i = cie10.getTipodent() + ";" + CIE10i;
                    modelo.put("CieI", CIE10i);
                } else {
                    CIE10e = cie10.getTipodent() + ";" + CIE10e;
                    modelo.put("CieE", CIE10e);
                }
            }

            if (listarmorbi.isEmpty() != true && CIE10e.length() > 2) {
                modelo.put("terminar", "si");
            } else {
                modelo.put("terminar", "no");
            }
        }

        Cuadernos datoc5 = new Cuadernos();
        datoc5.setId_historial(Integer.parseInt(id_reservacion));
        datoc5.setCod_esta(cliente.getCod_esta());
        List listaOdon = this.mi.getPacienteCuaderno5(datoc5);
        ///List listaOdon = this.mi.getPacienteCuaderno5(Integer.parseInt(id_reservacion));
        modelo.put("listaExter", listaOdon);

        modelo.put("nombres", nombres);
        modelo.put("accionc", accionc);
        modelo.put("id_sala", id_sala);
        modelo.put("id_cama", id_cama);
        modelo.put("id_historial", id_historial);
        modelo.put("id_historia", id_historia);

        return new ModelAndView("administrarhistoriales/DarAltaPaciente", modelo);
    }
}
