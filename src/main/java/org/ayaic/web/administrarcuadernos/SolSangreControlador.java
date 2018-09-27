package org.ayaic.web.administrarcuadernos;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Camas;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Consultorios;
import org.ayaic.domain.Cuadernos;
import org.ayaic.domain.Historiales;
import org.ayaic.domain.Localidades;
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.Personas;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SolSangreControlador {

    private final MiFacade mi;

    public SolSangreControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/SolSangre.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();
        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String accionl = request.getParameter("accionl");
        String swinter = request.getParameter("swinter");
        String id_sangre = request.getParameter("id_sangre");
        String id_persona = request.getParameter("id_persona");
        String id_paciente = request.getParameter("id_paciente");
        String id_historial = request.getParameter("id_historial");
        String id_consultorio = request.getParameter("id_consultorio");
        String[] dias = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
        String[] meses = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        String[] horas = {"00", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"};
        String[] minutos = {"00", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"};
        String[] notra = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"};
        Date fecha1 = new Date();
        int anioq = fecha1.getYear() + 1900;
        String[] anios = {Integer.toString(anioq), Integer.toString(anioq + 1),};

        //Listar Departamentos
        List listaDepartamentos = this.mi.getListarPaisDep(1);
        modelo.put("listaDepartamentos", listaDepartamentos);

        Localidades local = new Localidades();
        List Estab1 = this.mi.getListarEsta(local);
        Localidades datoestab = (Localidades) Estab1.get(0);
        local.setId_departamento(Integer.parseInt(Integer.toString(cliente.getCod_esta()).substring(0, 1)));
        local.setId_persona(10);
        local.setArea("C");
        List Estab = this.mi.getEstabTransCns(local);  /////getEstabTransCns
        modelo.put("listaEstab", Estab);
        modelo.put("datoestab", datoestab);
        modelo.put("id_sangre", id_sangre);
        modelo.put("dato", cliente);
        modelo.put("notra", notra);
        modelo.put("swinter", swinter);

        if ("Recomendacion".equals(accion)) {

        }
        if ("Uso Clinico".equals(accion)) {

        }

        Consultorios a = new Consultorios();
        a.setCod_esta(cliente.getCod_esta());
        a.setId_estado("T");
        List listarCargos = this.mi.getListarConsultoriosTransf(a);
        modelo.put("listarCargos", listarCargos);

        modelo.put("direccion", datoestab.getDireccion());
        modelo.put("anios", anios);
        modelo.put("meses", meses);
        modelo.put("dias", dias);
        modelo.put("horas", horas);
        modelo.put("minutos", minutos);
        modelo.put("anio", Integer.toString(fecha1.getYear() + 1900));
        modelo.put("mes", Integer.toString(fecha1.getMonth() + 1));
        modelo.put("dia", Integer.toString(fecha1.getDate()));
        modelo.put("hora", Integer.toString(fecha1.getHours()));
        modelo.put("minuto", Integer.toString(fecha1.getMinutes()));
        modelo.put("id_paciente", id_paciente);
        modelo.put("id_persona", id_persona);
        modelo.put("id_historial", id_historial);
        modelo.put("id_reservacion", id_historial);
        modelo.put("id_consultorio", id_consultorio);

        Pacientes buscarPacienteh = this.mi.getDatosPaciente(Integer.parseInt(id_paciente)); // saca un registro a ser modificado
        modelo.put("urgencias", Integer.toString(cliente.getId_almacen()));
        modelo.put("datosp", buscarPacienteh);

        Historiales datohi = new Historiales();
        datohi.setId_historial(Integer.parseInt(id_historial));
        datohi.setCod_esta(cliente.getCod_esta());
        Historiales datosHistorial = this.mi.getDatosHistorial(datohi);
        Personas buscarEmpleado = this.mi.getDatosPersonaInt(datosHistorial.getId_persona());   //11/06/2015  
        Personas buscarEmpleado2 = this.mi.getDatosPersonaInt(Integer.parseInt(id_persona));
        id_consultorio = Integer.toString(buscarEmpleado2.getId_consultorio());
        modelo.put("empleado", buscarEmpleado);
        modelo.put("tipo_medico", Integer.toString(cliente.getId_cargo()));
        modelo.put("expedido", datosHistorial.getExpedido());
        datosHistorial.setDiagnostico(datosHistorial.getDiagnostico().replaceAll("<p>", " "));
        datosHistorial.setDiagnostico(datosHistorial.getDiagnostico().replaceAll("</p>", " "));
        datosHistorial.setDiagnostico(datosHistorial.getDiagnostico().replaceAll("&nbsp;", ""));
        datosHistorial.setDiagnostico(datosHistorial.getDiagnostico().replaceAll("<strong>", " "));
        datosHistorial.setDiagnostico(datosHistorial.getDiagnostico().replaceAll("</strong>", " "));
        datosHistorial.setDiagnostico(datosHistorial.getDiagnostico().replaceAll("<br />", " "));
        datosHistorial.setDiagnostico(datosHistorial.getDiagnostico().replaceAll("<u>", " "));
        datosHistorial.setDiagnostico(datosHistorial.getDiagnostico().replaceAll("</u>", " "));
        datosHistorial.setDiagnostico(datosHistorial.getDiagnostico().replaceAll("<ul>", " "));
        datosHistorial.setDiagnostico(datosHistorial.getDiagnostico().replaceAll("</ul>", " "));
        datosHistorial.setDiagnostico(datosHistorial.getDiagnostico().replaceAll("<ol>", " "));
        datosHistorial.setDiagnostico(datosHistorial.getDiagnostico().replaceAll("</ol>", " "));
        datosHistorial.setDiagnostico(datosHistorial.getDiagnostico().replaceAll("<li>", " "));
        datosHistorial.setDiagnostico(datosHistorial.getDiagnostico().replaceAll("</li>", " "));
        modelo.put("diagnostico", datosHistorial.getDiagnostico());

        Cuadernos datot = new Cuadernos();
        datot.setId_historial(Integer.parseInt(id_historial));
        datot.setId_paciente(Integer.parseInt(id_paciente));
        datot.setCod_esta(cliente.getCod_esta());
        List C5 = this.mi.getPacienteCuaderno5(datot);

        if (C5.size() > 0) {
            Cuadernos cuaderno5 = (Cuadernos) C5.get(0);
            id_persona = Integer.toString(cuaderno5.getId_persona());
            id_consultorio = Integer.toString(cuaderno5.getId_consultorio());
            Camas lcama = new Camas();
            lcama.setTipo("U");///getListarCamaUnit 
            lcama.setId_cama(cuaderno5.getId_cama());
            lcama.setId_sala(cuaderno5.getId_sala());
            List listarCama = mi.getListarCamaUnit(lcama); //////getListarCamaUnit 
            modelo.put("listarCama", listarCama);
            if (!listarCama.isEmpty()) {
                Camas licama = (Camas) listarCama.get(0);
                modelo.put("licama", licama);
            }

        }

        //Cuadernos datot = new Cuadernos();
        datot.setCod_esta(cliente.getCod_esta());
        datot.setId_paciente(Integer.parseInt(id_paciente));
        List listarSangre = this.mi.getListarSolSangres(datot);
        modelo.put("listarSangre", listarSangre);

        if ("ModificaSangre".equals(accion)) {
            Cuadernos dato = new Cuadernos();
            dato.setCod_esta(cliente.getCod_esta());
            dato.setId_laboratorio(Integer.parseInt(id_sangre));
            List listaSangre = this.mi.getDatoSolSangre(dato);
            Cuadernos listaS = (Cuadernos) listaSangre.get(0);
            modelo.put("listaSangre", listaSangre);
            modelo.put("listaS", listaS);
            modelo.put("id_sangre", id_sangre);
            modelo.put("anio", Integer.toString(listaS.getFecha().getYear() + 1900));
            modelo.put("mes", Integer.toString(listaS.getFecha().getMonth() + 1));
            modelo.put("dia", Integer.toString(listaS.getFecha().getDate()));
            modelo.put("hora", Integer.toString(listaS.getFecha().getHours()));
            modelo.put("minuto", Integer.toString(listaS.getFecha().getMinutes()));
            modelo.put("hg", Integer.toString(listaS.getSuma5()));
            modelo.put("plaqueta", Integer.toString(listaS.getSuma6()));
            modelo.put("ttpa", Integer.toString(listaS.getSuma7()));
            modelo.put("hto", Integer.toString(listaS.getSuma8()));
            modelo.put("notras", Integer.toString(listaS.getSuma33()));
            modelo.put("leucocito", Integer.toString(listaS.getSuma9()));
            modelo.put("protombina", Integer.toString(listaS.getSuma10()));
            modelo.put("diagnostico", listaS.getDiagnostico());
            modelo.put("otros", listaS.getCadena3());
            modelo.put("especifica", listaS.getCadena2());
            modelo.put("motivo", listaS.getCadena1());
            modelo.put("accionl", accion);
            return new ModelAndView("administrarcuadernos/SolSangre", modelo);
        }

        if ("ImprimirSangre".equals(accion)) {
            Historiales datoh = new Historiales();
            datoh.setId_paciente(Integer.parseInt(id_paciente));
            datoh.setId_historial(Integer.parseInt(id_historial));
            datoh.setCod_esta(cliente.getCod_esta());
            datoh.setAccion("E");
            List listas = this.mi.getListarHistoriaEmergen(datoh);
            modelo.put("milistas", listas);
            datoh.setAccion("H");
            datoh.setId_paciente(Integer.parseInt(id_paciente));
            List listasBas = this.mi.getListarHistoriaHoy(datoh);////// obrero 8/05/2015
            modelo.put("listaBas", listasBas);

            Cuadernos dato = new Cuadernos();
            dato.setCod_esta(cliente.getCod_esta());
            dato.setId_laboratorio(Integer.parseInt(id_sangre));
            List listaSangre = this.mi.getDatoSolSangre(dato);
            modelo.put("listasolsangre", listaSangre);
            return new ModelAndView(new SolSangrePDF(), modelo);
        }

        if ("Guardar".equals(accion) || "Modificar".equals(accion)) {
            String id_servicio = request.getParameter("id_servicio");
            String diai = request.getParameter("diai");
            String mesi = request.getParameter("mesi");
            String anioi = request.getParameter("anioi");
            String horai = request.getParameter("horai");
            String minutoi = request.getParameter("minutoi");
            String urgencia = request.getParameter("urgencia");
            String endia = request.getParameter("endia");
            String programa = request.getParameter("programa");
            String quirofano = request.getParameter("quirofano");
            String hb = request.getParameter("hb");
            String plaqueta = request.getParameter("plaqueta");
            String ttpa = request.getParameter("ttpa");
            String hto = request.getParameter("hto");
            String leucocito = request.getParameter("leucocito");
            String protombina = request.getParameter("protombina");
            String trans = request.getParameter("trans");
            String notrans = request.getParameter("notrans");
            String reaccion = request.getParameter("reaccion");
            String embarazo = request.getParameter("embarazo");
            String ehnr = request.getParameter("ehnr");
            String diagnostico = request.getParameter("diagnostico");
            String st = request.getParameter("st");
            String volst = request.getParameter("volst");
            String pg = request.getParameter("pg");
            String volpg = request.getParameter("volpg");
            String pgl = request.getParameter("pgl");
            String volpgl = request.getParameter("volpgl");
            String cp = request.getParameter("cp");
            String volcp = request.getParameter("volcp");
            String fpc = request.getParameter("fpc");
            String volfpc = request.getParameter("volfpc");
            String pr = request.getParameter("pr");
            String volpr = request.getParameter("volpr");
            String crio = request.getParameter("crio");
            String volcrio = request.getParameter("volcrio");
            String au = request.getParameter("au");
            String volau = request.getParameter("volau");
            String otr = request.getParameter("otr");
            String volotr = request.getParameter("volotr");
            String especifica = request.getParameter("especifica");
            String motivo = request.getParameter("motivo");
            String otros = request.getParameter("otros");
            Date fecha = new Date();

            int diax = Integer.parseInt(diai);
            int mesx = Integer.parseInt(mesi) - 1;
            int aniox = Integer.parseInt(anioi) - 1900;
            int horax = Integer.parseInt(horai);
            int minutox = Integer.parseInt(minutoi);
            Date fechai = new Date(aniox, mesx, diax, horax, minutox, 00);

            if (hb == null || "".equals(hb)) {
                hb = "0";
            }
            if (plaqueta == null || "".equals(plaqueta)) {
                plaqueta = "0";
            }
            if (ttpa == null || "".equals(ttpa)) {
                ttpa = "0";
            }
            if (hto == null || "".equals(hto)) {
                hto = "0";
            }
            if (leucocito == null || "".equals(leucocito)) {
                leucocito = "0";
            }
            if (protombina == null || "".equals(protombina)) {
                protombina = "0";
            }

            Cuadernos dato = new Cuadernos();
            dato.setFecha(fechai);
            dato.setId_paciente(Integer.parseInt(id_paciente));
            dato.setId_historial(Integer.parseInt(id_historial));
            dato.setId_persona(Integer.parseInt(id_persona));
            dato.setId_consultorio(Integer.parseInt(id_consultorio));
            dato.setSuma1(Integer.parseInt(urgencia));   ////urgencia
            dato.setSuma2(Integer.parseInt(endia));  ////endia
            dato.setSuma3(Integer.parseInt(programa));  ////programa
            dato.setSuma4(Integer.parseInt(quirofano));  ////quirofano
            dato.setSuma5(Integer.parseInt(hb));  ////hb
            dato.setSuma6(Integer.parseInt(plaqueta));  ////plaqueta
            dato.setSuma7(Integer.parseInt(ttpa));  ////ttpa
            dato.setSuma8(Integer.parseInt(hto));  ////hto
            dato.setSuma9(Integer.parseInt(leucocito)); ////leucocito
            dato.setSuma10(Integer.parseInt(protombina));  ////protombina
            dato.setSuma11(Integer.parseInt(trans));  ////trans
            dato.setSuma33(Integer.parseInt(notrans));  ////notrans
            dato.setSuma12(Integer.parseInt(reaccion));  ////reaccion
            dato.setSuma13(Integer.parseInt(embarazo));  ////embarazo
            dato.setSuma14(Integer.parseInt(ehnr));  ////ehnr
            dato.setSuma15(Integer.parseInt(st)); ////st
            dato.setSuma16(Integer.parseInt(volst)); ////volst
            dato.setSuma17(Integer.parseInt(pg));  ////pg
            dato.setSuma18(Integer.parseInt(volpg));  ////volpg
            dato.setSuma19(Integer.parseInt(pgl));  ////pgl
            dato.setSuma20(Integer.parseInt(volpgl));  ////volpgl
            dato.setSuma21(Integer.parseInt(cp));  ////cp
            dato.setSuma22(Integer.parseInt(volcp));  ////volcp
            dato.setSuma23(Integer.parseInt(fpc));  ////fpc
            dato.setSuma24(Integer.parseInt(volfpc));  ////volfpc
            dato.setSuma25(Integer.parseInt(pr));  ////pr
            dato.setSuma26(Integer.parseInt(volpr));  ////volpr        
            dato.setSuma27(Integer.parseInt(crio));  ////crio
            dato.setSuma28(Integer.parseInt(volcrio));  ////volcrio
            dato.setSuma29(Integer.parseInt(au));  ////au
            dato.setSuma30(Integer.parseInt(volau));  ////volau
            dato.setSuma31(Integer.parseInt(otr));  ////otr
            dato.setSuma32(Integer.parseInt(volotr));  ////volotr
            dato.setCadena1(motivo);
            dato.setCadena2(especifica);
            dato.setCadena3(otros);
            dato.setDiagnostico(diagnostico);
            dato.setSuma35(cliente.getId_persona());
            dato.setCod_esta(cliente.getCod_esta());
            List listaSolSangre = mi.getVerSolSangre(dato);
            if ("ModificaSangre".equals(accionl)) {
                dato.setId_laboratorio(Integer.parseInt(id_sangre));
                mi.setModificarSangre(dato);
            } else if (listaSolSangre.isEmpty()) {
                dato.setAccion("S");
                mi.setCrearSolSangre(dato);
            } else {
                return new ModelAndView("Aviso", "mensaje", "Ya existe una Solicitud de Sangre");
            }
            List listaSolSan = mi.getVerSolSangre(dato);
            modelo.put("listasolsangre", listaSolSan);
            return new ModelAndView(new SolSangrePDF(), modelo);
        }

        modelo.put("accion", accion);
        return new ModelAndView("administrarcuadernos/SolSangre", modelo);
    }

}
