package org.ayaic.web.administrarcuadernos;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Consultorios;
import org.ayaic.domain.Cuadernos;
import org.ayaic.domain.Menues;
import org.ayaic.domain.Personas;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ReporteCIE10Controlador {

    private final MiFacade mi;

    public ReporteCIE10Controlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/ReporteCIE10.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String id_consultorio = request.getParameter("id_consultorio");
        String id_persona = request.getParameter("id_persona");
        String sId_estado = request.getParameter("id_estado");
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
        String sAccion = request.getParameter("boton");
        String accion = request.getParameter("accion");

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

        Consultorios datosconsul = this.mi.getDatosConsultorio(cliente.getId_consultorio()); // saca un registro a ser modificado
        modelo.put("tipo_medico", Integer.toString(datosconsul.getId_cargo()));
        Personas persona = this.mi.getBuscarPersona(cliente.getId_persona()); // saca un registro a ser modificado
        persona.setCod_esta(cliente.getCod_esta());
        Menues usuario_rol = new Menues();
        usuario_rol.setId_usuario(cliente.getId_usuario());
        List listaUsrRoles = this.mi.getListaUsrRoles(usuario_rol);
        persona.setDip("C");  ////getDatosPersonaConsulCIE
        List listarPaises = this.mi.getDatosPersonaConsulCIE(persona);
        modelo.put("listaPersonas", listarPaises);
        for (int i = 0; i < listaUsrRoles.size(); i++) {
            Menues datorol = (Menues) listaUsrRoles.get(i);
            if (datorol.getId_rol() == 27) {
                persona.setDip("L");
                List listarPaises2 = this.mi.getDatosPersonaConsulLabos(persona);
                modelo.put("listaPersonas", listarPaises2);
            }
        }

        if (cliente.getId_rol() == 36) {
            persona.setDip("E");  ////getDatosPersonaConsulEmerg
            List listarPaises2 = this.mi.getDatosPersonaConsulEmerg(persona);
            modelo.put("listaPersonas", listarPaises2);
        }
        Consultorios a = new Consultorios();
        a.setCod_esta(cliente.getCod_esta());
        a.setSelec(1);
        a.setId_estado("E");
        List listarCargos = this.mi.getListarConsultoriosEmerg(a);
        modelo.put("listarCargos", listarCargos);

        modelo.put("rol", Integer.toString(cliente.getId_rol()));
        modelo.put("id_consultorio", id_consultorio);

        if (!"".equals(id_consultorio) && !"0".equals(id_consultorio) && id_consultorio != null) {
            persona.setId_consultorio(Integer.parseInt(id_consultorio));
            persona.setCod_esta(cliente.getCod_esta());
            persona.setDip("U");   ////getDatosPersonaConsulUrgen
            List buscarEmpleado = this.mi.getDatosPersonaConsulUrgen(persona);
            modelo.put("listaPersonas", buscarEmpleado);
        }

        if ("Buscar".equals(sAccion)) {
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
                return new ModelAndView("administrarmedicamentos/BuscarPedidos", modelo);
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

                modelo.put("dato", cliente);

                Cuadernos dato = new Cuadernos();

                dato.setFecha_ini(dFechaini1);
                dato.setFecha_fin(dFechafin1);
                dato.setCod_esta(cliente.getCod_esta());
                dato.setAccion("N");
                List listaCobros = this.mi.getReporteCIE10(dato);
                modelo.put("listaCobros", listaCobros);
                if (cliente.getId_rol() == 36) {
                    dato.setAccion("M");
                    List listaCobros2 = this.mi.getReporteCIE10Urgencia(dato);
                    modelo.put("listaCobros", listaCobros2);
                }
                if (!"".equals(id_persona) && !"0".equals(id_persona) && id_persona != null) {
                    dato.setAccion("Q");
                    dato.setId_persona(Integer.parseInt(id_persona));
                    List listaCobros2 = this.mi.getReporteCIE10xMedico(dato);
                    modelo.put("listaCobros", listaCobros2);
                }

                return new ModelAndView(new VerReporteCIE10PDF(), modelo);
            }
        }

        if ("CEmerg".equals(sAccion) || "C1".equals(sAccion) || "C2".equals(sAccion) || "C3".equals(sAccion) || "C4".equals(sAccion) || "C7".equals(sAccion)) {
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
                return new ModelAndView("administrarcuadernos/ReporteCIE10", modelo);
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

                modelo.put("dato", cliente);

                Cuadernos dato = new Cuadernos();

                dato.setFecha_ini(dFechaini1);
                dato.setFecha_fin(dFechafin1);
                dato.setCod_esta(cliente.getCod_esta());
                List listaCobros = this.mi.getReporteCIE10(dato);
                modelo.put("listaCobros", listaCobros);
                if ("C1".equals(sAccion)) {
                    dato.setAccion("1");    ///getReporteCIE10_C1
                    List listaCobros2 = this.mi.getReporteCIE10_C1(dato);
                    modelo.put("listaCobros", listaCobros2);
                }
                if ("C2".equals(sAccion)) {
                    dato.setAccion("2");    ///getReporteCIE10_C2
                    List listaCobros2 = this.mi.getReporteCIE10_C2(dato);
                    modelo.put("listaCobros", listaCobros2);
                }
                if ("C3".equals(sAccion)) {
                    dato.setAccion("3");    ///getReporteCIE10_C3
                    List listaCobros2 = this.mi.getReporteCIE10_C3(dato);
                    modelo.put("listaCobros", listaCobros2);
                }
                if ("C4".equals(sAccion)) {
                    dato.setAccion("4");    ///getReporteCIE10_C4
                    List listaCobros2 = this.mi.getReporteCIE10_C4(dato);
                    modelo.put("listaCobros", listaCobros2);
                }
                if ("C7".equals(sAccion)) {
                    dato.setAccion("7");    ///getReporteCIE10_C7
                    List listaCobros2 = this.mi.getReporteCIE10_C7(dato);
                    modelo.put("listaCobros", listaCobros2);
                }
                if ("CEmerg".equals(sAccion)) {
                    dato.setAccion("0");    ///getReporteCIE10_CEmerg
                    List listaCobros2 = this.mi.getReporteCIE10_CEmerg(dato);
                    modelo.put("listaCobros", listaCobros2);
                }
                return new ModelAndView(new VerReporteCIE10PDF(), modelo);
            }
        }

        if ("Ingreso Internados".equals(sAccion)) {
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
                return new ModelAndView("administrarcuadernos/ReporteCIE10", modelo);
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
                Date dFechafin2 = new Date(iAnio2, iMes2, iDia2, iHor2, iMin2, 59);

                modelo.put("dato", cliente);

                Cuadernos dato = new Cuadernos();

                dato.setFecha_ini(dFechaini1);
                dato.setFecha_fin(dFechafin2);
                dato.setCod_esta(cliente.getCod_esta());
                dato.setAccion("I");

                List listaCobros = this.mi.getReporteCIE10IngInter(dato);
                modelo.put("listaCobros", listaCobros);

                return new ModelAndView(new VerReporteCIE10PDF(), modelo);
            }
        }

        if ("Egreso Internados".equals(sAccion)) {
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
                return new ModelAndView("administrarcuadernos/ReporteCIE10", modelo);
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
                Date dFechafin2 = new Date(iAnio2, iMes2, iDia2, iHor2, iMin2, 59);

                modelo.put("dato", cliente);

                Cuadernos dato = new Cuadernos();

                dato.setFecha_ini(dFechaini1);
                dato.setFecha_fin(dFechafin2);
                dato.setCod_esta(cliente.getCod_esta());
                dato.setAccion("G");

                List listaCobros = this.mi.getReporteCIE10EgrInter(dato);
                modelo.put("listaCobros", listaCobros);

                return new ModelAndView(new VerReporteCIE10PDF(), modelo);
            }
        }

        if ("S/Personal".equals(sAccion)) {
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
            String numes1 = request.getParameter("numes1");
            String numes2 = request.getParameter("numes2");
            String num1 = request.getParameter("num1");
            String num2 = request.getParameter("num2");
            if ("".equals(id_persona) || id_persona == null) {
                id_persona = Integer.toString(cliente.getId_persona());
            }
            if (("".equals(diai)) || ("".equals(mesi)) || ("".equals(anioi))) {
                return new ModelAndView("administrarcuadernos/ReporteCIE10", modelo);
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

                Personas persona1 = this.mi.getBuscarPersona(Integer.parseInt(id_persona)); // saca un registro a ser modificado
                modelo.put("Nombre", persona1.getNombres());
                modelo.put("Paterno", persona1.getPaterno());
                modelo.put("Materno", persona1.getMaterno());
                modelo.put("dato", cliente);
                modelo.put("num1", num1);
                modelo.put("num2", num2);
                modelo.put("numes1", numes1);
                modelo.put("numes2", numes2);
                modelo.put("fecha1", dFechaini1);
                modelo.put("fecha2", dFechafin1);
                modelo.put("id_persona", id_persona);

                Cuadernos dato = new Cuadernos();

                dato.setFecha_ini(dFechaini1);
                dato.setFecha_fin(dFechafin1);
                dato.setCod_esta(cliente.getCod_esta());
                dato.setAccion("A");
                dato.setExodoncia(Integer.parseInt(num1));
                dato.setEndodoncia(Integer.parseInt(num2));
                dato.setAnastecia(Integer.parseInt(numes1));
                dato.setAnestesia(Integer.parseInt(numes2));
                dato.setId_persona(Integer.parseInt(id_persona));
                List lista1 = this.mi.getReporteCIE10edadPersonal(dato);
                int sum = 0, sumf = 0, summ = 0, sumn = 0, sumr = 0;
                for (int i = 0; i < lista1.size(); i++) {//////////////////////////
                    Cuadernos lista = (Cuadernos) lista1.get(i);
                    summ = summ + lista.getSuma1();
                    sumf = sumf + lista.getSuma2();
                    sum = sum + lista.getSuma3();
                    sumn = sumn + lista.getSuma4();
                    sumr = sumr + lista.getSuma5();
                }
                modelo.put("lista1", lista1);
                modelo.put("sum", Integer.toString(sum));
                modelo.put("summ", Integer.toString(summ));
                modelo.put("sumf", Integer.toString(sumf));
                modelo.put("sumn", Integer.toString(sumn));
                modelo.put("sumr", Integer.toString(sumr));
                modelo.put("tipo", "P");

                return new ModelAndView("administrarcuadernos/Cie10edad", modelo);
            }
        }

        if ("ImprimirCie".equals(sAccion)) {
            String numero = request.getParameter("numero");
            String tipo = request.getParameter("tipo");
            String num1 = request.getParameter("num1");
            String num2 = request.getParameter("num2");
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

            Cuadernos dato = new Cuadernos();

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

            dato.setFecha_ini(dFechaini1);
            dato.setFecha_fin(dFechafin1);
            dato.setCod_esta(cliente.getCod_esta());
            dato.setAccion(tipo);
            dato.setExodoncia(Integer.parseInt(num1));
            dato.setEndodoncia(Integer.parseInt(num2));
            dato.setId_persona(Integer.parseInt(id_persona));

            List lista1 = this.mi.getReporteCIE10(dato);
            modelo.put("lista1", lista1);
            modelo.put("num", numero);
            modelo.put("dato", cliente);

            return new ModelAndView(new VerReporte2CIE10PDF(), modelo);
        }

        if ("SSPAM".equals(sAccion) || "SSPAM_1".equals(sAccion) || "SSPAM_2".equals(sAccion) || "SSPAM_3".equals(sAccion) || "SSPAM_4".equals(sAccion)) {
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
            String id_seguro = request.getParameter("id_seguro");
            String internado = request.getParameter("radio");
            String num1 = request.getParameter("num1");
            String num2 = request.getParameter("num2");
            if (("".equals(diai)) || ("".equals(mesi)) || ("".equals(anioi)) || ("".equals(num1)) || ("".equals(num2)) || ("0".equals(id_persona))) {
                return new ModelAndView("administrarcuadernos/ReporteCIE10", modelo);
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
                Date dFechafin2 = new Date(iAnio2, iMes2, iDia2, iHor2, iMin2, 59);

                if (id_persona == null) {
                    id_persona = Integer.toString(cliente.getId_persona());
                }
                Personas persona1 = this.mi.getBuscarPersona(Integer.parseInt(id_persona)); // saca un registro a ser modificado
                modelo.put("Nombre", persona1.getNombres());
                modelo.put("Paterno", persona1.getPaterno());
                modelo.put("Materno", persona1.getMaterno());
                modelo.put("dato", cliente);
                modelo.put("num1", num1);
                modelo.put("num2", num2);
                modelo.put("fecha1", dFechaini1);
                modelo.put("fecha2", dFechafin2);

                Cuadernos dato = new Cuadernos();

                dato.setFecha_ini(dFechaini1);
                dato.setFecha_fin(dFechafin2);
                dato.setCod_esta(cliente.getCod_esta());
                dato.setAccion("M");
                dato.setExodoncia(60);
                dato.setEndodoncia(130);
                dato.setId_persona(Integer.parseInt(id_persona));

                List lista1 = this.mi.getReporteCIE10Urgencia(dato);
                modelo.put("lista1", lista1);

                if ((id_seguro == null || "0".equals(id_seguro)) && datosconsul.getId_especialidad() == 0) {
                    return new ModelAndView("Aviso", "mensaje", "Debe elegir un tipo de seguro");
                }
            }
        }

        if ("Edades".equals(sAccion)) {
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
            String num1 = request.getParameter("num1");
            String num2 = request.getParameter("num2");
            String numes1 = request.getParameter("numes1");
            String numes2 = request.getParameter("numes2");
            if (("".equals(diai)) || ("".equals(mesi)) || ("".equals(anioi))) {
                return new ModelAndView("administrarcuadernos/ReporteCIE10", modelo);
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
                Date dFechafin2 = new Date(iAnio2, iMes2, iDia2, iHor2, iMin2, 59);

                modelo.put("dato", cliente);
                modelo.put("num1", num1);
                modelo.put("num2", num2);
                modelo.put("numes1", numes1);
                modelo.put("numes2", numes2);
                modelo.put("fecha1", dFechaini1);
                modelo.put("fecha2", dFechafin2);

                Cuadernos dato = new Cuadernos();

                dato.setFecha_ini(dFechaini1);
                dato.setFecha_fin(dFechafin2);
                dato.setCod_esta(cliente.getCod_esta());
                dato.setAccion("E");  ///getReporteCIE10edad
                dato.setExodoncia(Integer.parseInt(num1));
                dato.setEndodoncia(Integer.parseInt(num2));
                dato.setAnastecia(Integer.parseInt(numes1));
                dato.setAnestesia(Integer.parseInt(numes2));

                List lista1 = this.mi.getReporteCIE10edad(dato);
                modelo.put("lista1", lista1);
                modelo.put("tipo", "E");
                modelo.put("id_persona", "0");

                return new ModelAndView("administrarcuadernos/Cie10edad", modelo);
            }
        }
        return new ModelAndView("administrarcuadernos/ReporteCIE10", modelo);
    }
}
