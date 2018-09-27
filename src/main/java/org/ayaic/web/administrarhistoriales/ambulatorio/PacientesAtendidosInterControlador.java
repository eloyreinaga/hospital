package org.ayaic.web.administrarhistoriales.ambulatorio;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Consultorios;
import org.ayaic.domain.Historiales;
import org.ayaic.domain.Personas;
import org.ayaic.domain.Prestaciones;
import org.ayaic.domain.Salas;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PacientesAtendidosInterControlador {

    private final MiFacade mi;

    public PacientesAtendidosInterControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/ListarPacientesAtendidosInter.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
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

        Personas persona = this.mi.getBuscarPersona(cliente.getId_persona()); // saca un registro a ser modificado
        Consultorios datosconsul = this.mi.getDatosConsultorio(persona.getId_consultorio()); // saca un registro a ser modificado
        modelo.put("tipo_medico", Integer.toString(datosconsul.getId_cargo()));
        //Listar Paises
        List listarPaises = this.mi.getListarPersonas(persona);
        modelo.put("listaPersonas", listarPaises);
        Salas dsala = new Salas();
        dsala.setId_piso(0);
        List listarSalas = this.mi.getListarSalasLibres(dsala);
        //List listarSalas = this.mi.getListarSalasLibres();
        modelo.put("listarSalas", listarSalas);
        //Recuperamos variables del jsp

        String sId_estado = request.getParameter("id_estado");
        String sAccion = request.getParameter("boton");
        String accion = request.getParameter("boton");
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

        List listarSeguros = this.mi.getListarSeguros("A");
        modelo.put("listaPacAfi", listarSeguros);

        if ("ResumenSUMI".equals(accion)) {
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

            Historiales datoSumi = new Historiales();
            datoSumi.setFecha_ini(dFechaini1);
            datoSumi.setFecha_fin(dFechafin2);

            List listarPacientes = this.mi.getSumi(datoSumi);
            modelo.put("milistaSumi", listarPacientes);
            modelo.put("anio2", aniof);
            modelo.put("mes2", mesf);
            modelo.put("dato", cliente);
            return new ModelAndView(new ListarPacientesSumiPDF(), modelo);
        }

        if ("Resumen".equals(sAccion) || "Neonatos".equals(sAccion) || "Pediatricos".equals(sAccion) || "Mujer".equals(sAccion)) {
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
                return new ModelAndView("administrarhistoriales/PacientesAtendidosInter", modelo);
            } else {
                String id_persona = request.getParameter("id_persona");
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

                modelo.put("mes", Integer.toString(iMes2 + 1));
                modelo.put("annio", Integer.toString(iAnio2 + 1900));
                modelo.put("id_persona", id_persona);

                Prestaciones datopre = new Prestaciones();
                //       if (id_persona == null) 
                //          datopre.setId_persona(cliente.getId_persona());
                //       else
                //           datopre.setId_persona(Integer.parseInt(id_persona));

                datopre.setFecha_ini(dFechaini1);
                datopre.setFecha_fin(dFechafin2);

                Historiales datohis = new Historiales();
                //         if (id_persona == null) 
                //               datohis.setId_persona(cliente.getId_persona());
                //         else
                //               datohis.setId_persona(Integer.parseInt(id_persona));    
                datohis.setFecha_ini(dFechaini1);
                datohis.setFecha_fin(dFechafin2);
                List prestacionesDadas = this.mi.getListarPrestacionesDadasNeo(datopre);
                modelo.put("prestacionesDadas", prestacionesDadas);
                if ("Neonatos".equals(sAccion)) {
                    datohis.setAccion("N");
                    datopre.setPrestacion("N");
                    modelo.put("soloedad", "si");
                    List prestacionesDadas2 = this.mi.getListarPrestacionesDadasNeo(datopre);
                    modelo.put("prestacionesDadas", prestacionesDadas2);
                }
                if ("Pediatricos".equals(sAccion)) {
                    datohis.setAccion("P");
                    datopre.setPrestacion("P");
                    modelo.put("soloedad", "no");
                    List prestacionesDadas2 = this.mi.getListarPrestacionesDadasPedi(datopre);
                    modelo.put("prestacionesDadas", prestacionesDadas2);
                }
                if ("Mujer".equals(sAccion)) {
                    datohis.setAccion("M");
                    datopre.setPrestacion("M");
                    modelo.put("soloedad", "no");
                    List prestacionesDadas2 = this.mi.getListarPrestacionesDadasMuje(datopre);
                    modelo.put("prestacionesDadas", prestacionesDadas2);
                }
                if ("Resumen".equals(sAccion)) {
                    String id_sala = request.getParameter("id_sala");
                    if (Integer.parseInt(id_sala) == 0) {
                        datohis.setAccion("T");
                        List listarAtendidos = this.mi.getAtendidosInter(datohis);
                        modelo.put("milistaAten", listarAtendidos);
                    } else {
                        datohis.setAccion("S");
                        datohis.setId_cargo(Integer.parseInt(id_sala));
                        List listarAtendidos = this.mi.getAtendidosInterSala(datohis);
                        modelo.put("milistaAten", listarAtendidos);
                    }

                    return new ModelAndView("administrarhistoriales/ListarAtendidosInter", modelo);
                }
                modelo.put("dato", cliente);

                List listaConsultorios = this.mi.getListarPacientesHisto(datohis);
                modelo.put("listaPacientes", listaConsultorios);

                List pacientePrestacion = this.mi.getListarPacientesPrestacionesGen(datopre);
                modelo.put("pacientePrestacion", pacientePrestacion);

                return new ModelAndView(new ListarConsultaPDF(), modelo);
            }
        }

        return new ModelAndView("administrarhistoriales/PacientesAtendidosInter", modelo);
    }
}
