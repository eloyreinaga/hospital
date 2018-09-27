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
public class ListarAfiliadosSumiControlador {

    private final MiFacade mi;

    public ListarAfiliadosSumiControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/ListarAfiliadosSumi.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String sAccion = request.getParameter("boton");
        String[] dias = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
        String[] meses = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        String[] horas = {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"};
        String[] minutos = {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"};
        String diaq1 = request.getParameter("diaq1");
        String mesq1 = request.getParameter("mesq1");
        String ip = request.getRemoteAddr();
        String host = request.getRemoteHost();
        Date fechaact = new Date();
        Date fecha1 = new Date();
        int anioq = fecha1.getYear() + 1900;
        String[] anios = {Integer.toString(anioq), Integer.toString(anioq - 1), Integer.toString(anioq - 2), Integer.toString(anioq - 3), Integer.toString(anioq - 4), Integer.toString(anioq - 5), Integer.toString(anioq - 6), Integer.toString(anioq - 7), Integer.toString(anioq - 8), Integer.toString(anioq - 9), Integer.toString(anioq - 10)};

        Localidades local = new Localidades();
        List Estab1 = this.mi.getListarEsta(local);
        Localidades datoestab = (Localidades) Estab1.get(0);
        modelo.put("dato", cliente);

        modelo.put("anios", anios);
        modelo.put("meses", meses);
        modelo.put("dias", dias);
        modelo.put("horas", horas);
        modelo.put("minutos", minutos);
        modelo.put("anio", Integer.toString(fecha1.getYear() + 1900));
        if (fecha1.getMonth() + 1 < 10) {
            mesq1 = "0" + Integer.toString(fecha1.getMonth() + 1);
        } else {
            mesq1 = Integer.toString(fecha1.getMonth() + 1);
        }

        if (fecha1.getDate() < 10) {
            diaq1 = "0" + Integer.toString(fecha1.getDate());
        } else {
            diaq1 = Integer.toString(fecha1.getDate());
        }
        modelo.put("mes", mesq1);
        modelo.put("dia", diaq1);
        modelo.put("anio2", Integer.toString(fecha1.getYear() + 1900));
        modelo.put("mes2", mesq1);
        modelo.put("dia2", diaq1);
        modelo.put("hora", "00");
        modelo.put("minuto", "00");
        modelo.put("hora2", "23");
        modelo.put("minuto2", "59");

        if ("HCL vacias".equals(sAccion)) {
            Pacientes dato = new Pacientes();

            List listaFaltan = this.mi.getDatosFaltaHcl(dato);
            modelo.put("listaf", listaFaltan);
            return new ModelAndView("administrarpacientes/ListarFaltasHcl", modelo);
        }
        if ("HCL vacias Des".equals(sAccion)) {
            Pacientes dato = new Pacientes();
            dato.setTipo("A");
            List listaFaltan = this.mi.getDatosFaltaHcl2(dato);
            modelo.put("listaf", listaFaltan);
            return new ModelAndView("administrarpacientes/ListarFaltasHcl", modelo);
        }

        if ("SIIS Ninio".equals(sAccion) || "SIIS Mujer".equals(sAccion) || "SIIS Mujer Fertil".equals(sAccion) || "SIIS > 60".equals(sAccion) || "SIIS Discapacidad".equals(sAccion) || "Otros Seguros".equals(sAccion) || "Buscar Todos".equals(sAccion)) {
            String diai = request.getParameter("diai");
            String mesi = request.getParameter("mesi");
            String anioi = request.getParameter("anioi");
            String horai = request.getParameter("horai");
            String minutoi = request.getParameter("minutoi");
            String diaf = request.getParameter("diaf");
            String mesf = request.getParameter("mesf");
            String aniof = request.getParameter("aniof");
            String horaf = request.getParameter("horaf");
            String minutof = request.getParameter("minutof");
            if (("".equals(diai)) || ("".equals(mesi)) || ("".equals(anioi))) {
                return new ModelAndView("administrarpacientes/ListarAfiliadosSumi", modelo);
            } else {
                int iAnio1 = Integer.parseInt(anioi) - 1900;
                int iMes1 = Integer.parseInt(mesi) - 1;
                int iDia1 = Integer.parseInt(diai);
                int iHor1 = Integer.parseInt(horai);
                int iMin1 = Integer.parseInt(minutoi);

                int iAnio2 = Integer.parseInt(aniof) - 1900;
                int iMes2 = Integer.parseInt(mesf) - 1;
                int iDia2 = Integer.parseInt(diaf);
                int iHor2 = Integer.parseInt(horaf);
                int iMin2 = Integer.parseInt(minutof);

                Date dFechaini1 = new Date(iAnio1, iMes1, iDia1, iHor1, iMin1, 00);
                Date dFechafin1 = new Date(iAnio2, iMes2, iDia2, iHor2, iMin2, 59);

                Pacientes dato = new Pacientes();
                dato.setCod_esta(cliente.getCod_esta());
                dato.setFecha_ini(dFechaini1);
                dato.setFecha_fin(dFechafin1);
                dato.setId_estado("T");
                if (datoestab.getCod_esta() == 200010) {
                    dato.setId_estado("C");
                }

                dato.setId_pais(0);
                dato.setId_localidad(99999);
                List listaAfiliados = this.mi.getReporteAfiliados(dato);
                modelo.put("listaAfiliados", listaAfiliados);
                if ("SIIS Ninio".equals(sAccion)) {
                    dato.setId_estado("S");
                    dato.setId_pais(1);
                    dato.setId_localidad(1);
                    List listaAfiliados2 = this.mi.getReporteAfiliadosSumi(dato);
                    modelo.put("listaAfiliados", listaAfiliados2);
                }
                if ("SIIS Mujer".equals(sAccion)) {
                    dato.setId_estado("S");
                    dato.setId_pais(2);
                    dato.setId_localidad(2);
                    List listaAfiliados2 = this.mi.getReporteAfiliadosSumi(dato);
                    modelo.put("listaAfiliados", listaAfiliados2);
                }
                if ("SIIS Mujer Fertil".equals(sAccion)) {
                    dato.setId_estado("S");
                    dato.setId_pais(3);
                    dato.setId_localidad(3);
                    List listaAfiliados2 = this.mi.getReporteAfiliadosSumi(dato);
                    modelo.put("listaAfiliados", listaAfiliados2);
                }
                if ("SIIS > 60".equals(sAccion)) {
                    dato.setId_estado("S");
                    dato.setId_pais(4);
                    dato.setId_localidad(4);
                    List listaAfiliados2 = this.mi.getReporteAfiliadosSumi(dato);
                    modelo.put("listaAfiliados", listaAfiliados2);
                }
                if ("SIIS Discapacidad".equals(sAccion)) {
                    dato.setId_estado("S");
                    dato.setId_pais(5);
                    dato.setId_localidad(5);
                    List listaAfiliados2 = this.mi.getReporteAfiliadosSumi(dato);
                    modelo.put("listaAfiliados", listaAfiliados2);
                }

                if ("Otros Seguros".equals(sAccion)) {
                    modelo.put("tipo", "Otros Seguros");
                    dato.setId_estado("O");
                    dato.setId_pais(6);
                    dato.setId_localidad(99999);
                    List listaAfiliados2 = this.mi.getOtrosAfiliados(dato);
                    modelo.put("listaAfiliados", listaAfiliados2);

                    return new ModelAndView(new ListarOtrosAfiliadosPDF(), modelo);

                }

                if (datoestab.getCod_esta() == 200010) {
                    return new ModelAndView(new ListarAfiliadosCajaPDF(), modelo);
                }

                return new ModelAndView(new ListarAfiliadosSumiPDF(), modelo);
            }
        }
        return new ModelAndView("administrarpacientes/ListarAfiliadosSumi", modelo);
    }
}
