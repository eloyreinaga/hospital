package org.ayaic.web.administrarhistoriales;

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
import org.ayaic.domain.Medicamentos;
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.Personas;
import org.ayaic.domain.Prestaciones;
import org.ayaic.domain.Recetas;
import org.ayaic.domain.Salas;
import org.ayaic.domain.Sexos;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PlanAccionIControlador {

    private final MiFacade mi;

    public PlanAccionIControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/PlanAccionI.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String accion1 = request.getParameter("accion1");
        String sw10 = request.getParameter("sw10");
        String id_paciente = request.getParameter("id_paciente");
        String id_reservacion = request.getParameter("id_reservacion");
        String id_consultorio = request.getParameter("id_consultorio");
        String id_historia = request.getParameter("id_historia");
        String nombres = request.getParameter("nombre");
        String modify = request.getParameter("modify");
        String revision = request.getParameter("revision");
        String id_persona = request.getParameter("id_persona");
        String expedido = request.getParameter("expedido");
        String tipo_medico = request.getParameter("tipo_medico");
        String swinter = request.getParameter("swinter");
        String swx = request.getParameter("swx");
        String[] dias = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
        String[] meses = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        Date fecha1 = new Date();
        int inter = 0;
        int anioq = fecha1.getYear() + 1900;
        String[] anios = {Integer.toString(anioq), Integer.toString(anioq - 1), Integer.toString(anioq - 2), Integer.toString(anioq - 3), Integer.toString(anioq - 4), Integer.toString(anioq - 5), Integer.toString(anioq - 6), Integer.toString(anioq - 7), Integer.toString(anioq - 8), Integer.toString(anioq - 9), Integer.toString(anioq - 10), Integer.toString(anioq - 11), Integer.toString(anioq - 12), Integer.toString(anioq - 13), Integer.toString(anioq - 14), Integer.toString(anioq - 15), Integer.toString(anioq - 16), Integer.toString(anioq - 17), Integer.toString(anioq - 18), Integer.toString(anioq - 19), Integer.toString(anioq - 20), Integer.toString(anioq - 21), Integer.toString(anioq - 22), Integer.toString(anioq - 23), Integer.toString(anioq - 24), Integer.toString(anioq - 25), Integer.toString(anioq - 26), Integer.toString(anioq - 27), Integer.toString(anioq - 28), Integer.toString(anioq - 29), Integer.toString(anioq - 30), Integer.toString(anioq - 31), Integer.toString(anioq - 32), Integer.toString(anioq - 33), Integer.toString(anioq - 34), Integer.toString(anioq - 35), Integer.toString(anioq - 36), Integer.toString(anioq - 37), Integer.toString(anioq - 38), Integer.toString(anioq - 39), Integer.toString(anioq - 40), Integer.toString(anioq - 41), Integer.toString(anioq - 42), Integer.toString(anioq - 43), Integer.toString(anioq - 44), Integer.toString(anioq - 45), Integer.toString(anioq - 46), Integer.toString(anioq - 47), Integer.toString(anioq - 48), Integer.toString(anioq - 49), Integer.toString(anioq - 50), Integer.toString(anioq - 51), Integer.toString(anioq - 52), Integer.toString(anioq - 53), Integer.toString(anioq - 54), Integer.toString(anioq - 55), Integer.toString(anioq - 56), Integer.toString(anioq - 57), Integer.toString(anioq - 58), Integer.toString(anioq - 59), Integer.toString(anioq - 60), Integer.toString(anioq - 61), Integer.toString(anioq - 62), Integer.toString(anioq - 63), Integer.toString(anioq - 64), Integer.toString(anioq - 65), Integer.toString(anioq - 66), Integer.toString(anioq - 67), Integer.toString(anioq - 68), Integer.toString(anioq - 69), Integer.toString(anioq - 70), Integer.toString(anioq - 71), Integer.toString(anioq - 72), Integer.toString(anioq - 73), Integer.toString(anioq - 74), Integer.toString(anioq - 75), Integer.toString(anioq - 76), Integer.toString(anioq - 77), Integer.toString(anioq - 78), Integer.toString(anioq - 79), Integer.toString(anioq - 80), Integer.toString(anioq - 81), Integer.toString(anioq - 82), Integer.toString(anioq - 83), Integer.toString(anioq - 84), Integer.toString(anioq - 85), Integer.toString(anioq - 86), Integer.toString(anioq - 87), Integer.toString(anioq - 88), Integer.toString(anioq - 89), Integer.toString(anioq - 90), Integer.toString(anioq - 91), Integer.toString(anioq - 92), Integer.toString(anioq - 93), Integer.toString(anioq - 94), Integer.toString(anioq - 95), Integer.toString(anioq - 96), Integer.toString(anioq - 97), Integer.toString(anioq - 98), Integer.toString(anioq - 99), Integer.toString(anioq - 100), Integer.toString(anioq - 101), Integer.toString(anioq - 102), Integer.toString(anioq - 103), Integer.toString(anioq - 104), Integer.toString(anioq - 105), Integer.toString(anioq - 106), Integer.toString(anioq - 107), Integer.toString(anioq - 108), Integer.toString(anioq - 109), Integer.toString(anioq - 110)};

        String[] horas = {"00", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"};
        String[] minutos = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"};
        String[] aniosq = {(Integer.toString(anioq)), (Integer.toString(anioq - 1))};
        String[] aniosqq = {(Integer.toString(anioq)), (Integer.toString(anioq + 1))};

        Localidades local = new Localidades();
        List Estab1 = this.mi.getListarEsta(local);
        Localidades datoestab = (Localidades) Estab1.get(0);
        modelo.put("codesta", Integer.toString(datoestab.getCod_esta()));
        modelo.put("cod_esta", Integer.toString(cliente.getCod_esta()));
        modelo.put("urgencia", Integer.toString(cliente.getId_almacen()));
        modelo.put("id_consultoriofar", Integer.toString(cliente.getId_consultorio()));

        modelo.put("id_especialidad", Integer.toString(cliente.getId_especialidad()));

        Pacientes buscarPaciente = this.mi.getDatosPaciente(Integer.parseInt(id_paciente)); // saca un registro a ser modificado
        modelo.put("datos", buscarPaciente);
        Consultorios datosconsultorio = this.mi.getDatosConsultorio(cliente.getId_consultorio()); // saca un registro a ser modificado
        modelo.put("tipo_medico", Integer.toString(datosconsultorio.getId_cargo()));
        modelo.put("carnet", buscarPaciente.getCarnet());

        String id_sexo_e = Integer.toString(buscarPaciente.getId_tipo_sexo());          //Sacar id_sexo

        Date fecha_nac = buscarPaciente.getFec_nacimiento();
        int xanio = fecha_nac.getYear() + 1900;
        int xmes = fecha_nac.getMonth() + 1;
        int xdia = fecha_nac.getDate();

        //Buscar Sexo
        Sexos buscarsexo = this.mi.getDatosSexo(Integer.parseInt(id_sexo_e)); // saca un registro a ser modificado
        modelo.put("buscarSexo", buscarsexo);

        String a = "/";
        String fecha_nacimiento = Integer.toString(xdia) + a + Integer.toString(xmes) + a + Integer.toString(xanio);
        modelo.put("fec_nacimiento", fecha_nacimiento);

        Cuadernos datoc5v = new Cuadernos();  ////saca datos de internados id_historia  15-09-2015
        datoc5v.setId_historial(Integer.parseInt(id_reservacion));
        datoc5v.setCod_esta(cliente.getCod_esta());
        List C5 = this.mi.getPacienteCuaderno5(datoc5v);

        if (!C5.isEmpty()) {
            Cuadernos Inter = (Cuadernos) C5.get(0);
            id_persona = Integer.toString(Inter.getId_persona());
            id_consultorio = Integer.toString(Inter.getId_consultorio());
            inter = Inter.getId_historia();
            modelo.put("inter", Integer.toString(Inter.getId_historia()));
        }

        modelo.put("accion", accion);
        modelo.put("sw1", request.getParameter("sw1"));
        modelo.put("id_paciente", id_paciente);
        modelo.put("id_historia", id_historia);
        modelo.put("id_reservacion", id_reservacion);
        modelo.put("id_consultorio", id_consultorio);
        modelo.put("id_persona", id_persona);
        modelo.put("expedido", expedido);
        modelo.put("modify", modify);
        modelo.put("swinter", swinter);
        modelo.put("swx", swx);
        modelo.put("dias", dias);
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

        if ("Recetar".equals(accion)) {

            Recetas datore = new Recetas();
            datore.setId_historial(Integer.parseInt(id_reservacion));
            datore.setCod_esta(cliente.getCod_esta());
            datore.setId_farmacia(cliente.getId_farmacia());
            datore.setId_estado("%");
            List listarRecetas = this.mi.getListarRecetas(datore);
            modelo.put("listarRecetas", listarRecetas);

            Medicamentos dato = new Medicamentos();
            dato.setId_persona(Integer.parseInt(id_persona));
            dato.setId_farmacia(cliente.getId_farmacia());
            dato.setCod_esta(cliente.getCod_esta());
            dato.setExpedido(expedido);
            List listarMedicamentosCot = this.mi.getListarMedicamentosCot(dato);
            modelo.put("listarMedicamentosCot", listarMedicamentosCot);
            return new ModelAndView("administrarhistoriales/RecetarPacienteExtInt", modelo);
        }

        if ("DarAltaPaciente".equals(accion)) {
            modelo.put("id_persona", id_persona);
            modelo.put("nombres", buscarPaciente.getNombres());
            modelo.put("hcl", Integer.toString(buscarPaciente.getHcl()));
            Medicamentos medid = new Medicamentos();
            medid.setCod_esta(cliente.getCod_esta());
            medid.setId_persona(Integer.parseInt(id_persona));
            List listarEnfermedadesCot = this.mi.getListarEnfermedadesCot(medid);
            modelo.put("listarEnfermedadesCot", listarEnfermedadesCot);
            List listarSalas = this.mi.getListarSalas();
            modelo.put("listarSalas", listarSalas);
            modelo.put("accionc", "cama");
            Historiales datomorbi = new Historiales();
            datomorbi.setId_reservacion(Integer.parseInt(id_reservacion));
            datomorbi.setCod_esta(cliente.getCod_esta());
            List listarmorbi = this.mi.getListaMorbi(datomorbi);    
            modelo.put("morbi", listarmorbi);
            Cuadernos datoc5 = new Cuadernos();
            datoc5.setId_historial(Integer.parseInt(id_reservacion));
            datoc5.setCod_esta(cliente.getCod_esta());
            List listaOdon = this.mi.getPacienteCuaderno5(datoc5);
            //List listaOdon = this.mi.getPacienteCuaderno5(Integer.parseInt(id_reservacion));
            modelo.put("listaExter", listaOdon);
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
            if (listarmorbi.isEmpty() != true && CIE10e.length() > 2) {
                modelo.put("terminar", "si");
            }

            return new ModelAndView("administrarhistoriales/DarAltaPaciente", modelo);
        }

        if ("SPS (exSUMII)".equals(accion)) {
            Historiales datoc = new Historiales();
            datoc.setId_persona(Integer.parseInt(id_persona));
            datoc.setAccion("R");

            Prestaciones prestac = new Prestaciones();
            prestac.setId_historial(Integer.parseInt(id_reservacion));
            prestac.setId_persona(cliente.getId_persona());
            prestac.setCod_esta(cliente.getCod_esta());
            List listarRecetas = this.mi.getListarSumiRecetasI(prestac);
            modelo.put("listarRecetas", listarRecetas);
            Recetas datore = new Recetas();
            datore.setId_historial(Integer.parseInt(id_reservacion));
            datore.setCod_esta(cliente.getCod_esta());
            datore.setId_farmacia(cliente.getId_farmacia());
            datore.setId_estado("%");
            List listarRecetaSumi = this.mi.getListarRecetas(datore);
            modelo.put("listarRecetaSumi", listarRecetaSumi);

            List listPrestacionCot = this.mi.getListarPrestacionesCot(prestac);
            modelo.put("listarPrestacionesCot", listPrestacionCot);
            modelo.put("medica", "si");
            return new ModelAndView("administrarhistoriales/RecetarInternado", modelo);
        }

        if ("Recetar Asegurado".equals(accion)) {
            String id_seguro = request.getParameter("id_seguro");
            String[] dias2 = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "20", "25", "30", "40", "50", "60", "70", "80", "90"};

            Recetas datore = new Recetas();
            datore.setId_historial(Integer.parseInt(id_reservacion));
            datore.setCod_esta(cliente.getCod_esta());
            datore.setId_farmacia(cliente.getId_farmacia());
            datore.setId_estado("%");
            List listarRecetas = this.mi.getListarRecetas(datore);
            modelo.put("listarRecetas", listarRecetas);

            datore.setId_estado("C");   //////getListarRecetasCaros
            List listarRecetaCaro = this.mi.getListarRecetasCaros(datore);
            if (listarRecetaCaro.size() > 0) {
                modelo.put("restrig", "si");
            }
            modelo.put("listarRecetaCaro", listarRecetaCaro);

            Medicamentos dato = new Medicamentos();
            dato.setId_persona(Integer.parseInt(id_persona));
            dato.setCod_esta(cliente.getCod_esta());
            dato.setId_farmacia(cliente.getId_farmacia());
            dato.setExpedido(expedido);
            List listarMedicamentosCot = this.mi.getListarMedicamentosCotb1(dato);
            modelo.put("listarMedicamentosCot", listarMedicamentosCot);
            modelo.put("spam", "si");
            modelo.put("dias", dias2);

            return new ModelAndView("administrarhistoriales/RecetarPacienteExtInt", modelo);
        }

        if ("Terminar".equals(accion)) {
            Personas persona = this.mi.getBuscarPersona(cliente.getId_persona()); // saca un registro a ser modificado

            Historiales datox = new Historiales();
            datox.setId_persona(cliente.getId_persona());
            datox.setId_por(cliente.getId_persona());
            datox.setAccion("R");                ////getHistorialAtendidos
            datox.setFecha_ini(fecha1);
            datox.setFecha_fin(fecha1);
            datox.setAccion("R");

            datox.setId_consultorio(persona.getId_consultorio());
            datox.setId_estado("C");
            List listarPaises = this.mi.getListarReservaciones(datox);
            modelo.put("milista", listarPaises);

            List listarAtendidos = this.mi.getHistorialAtendidos(datox);
            modelo.put("milistaAten", listarAtendidos);

            Salas dsala = new Salas();
            dsala.setId_piso(0);
            List listarSalas = this.mi.getListarSalasLibres(dsala);
            modelo.put("listarSalas", listarSalas);
            dsala.setCod_esta(cliente.getCod_esta());
            dsala.setTipo("T");   /////getListarPisosTotal
            List listarPisos = this.mi.getListarPisosTotal(dsala);
            modelo.put("listarPisos", listarPisos);
            modelo.put("id_piso", Integer.toString(cliente.getId_piso()));

            //datox.setNombres("%"+buscarPaciente.getNombres()+"%"); 
            //datox.setAccion("M");  ////getInternadosSalaCajaNombre
            //datox.setCod_esta(cliente.getCod_esta());
            //List listarPacInter = this.mi.getInternadosSalaCajaNombre(datox);  ////getInternadosSalaCajaNombre
            //modelo.put("listarPacInter", listarPacInter);
            nombres = buscarPaciente.getNombres().trim();
            nombres = nombres.replaceAll(" +", " ");///quita espacios en blanco entre cadenas
            nombres = nombres.replaceAll("\\s", ":*&");///:*&
            nombres = nombres.replaceAll("ñ", "n");
            nombres = nombres.replaceAll("Ñ", "N");
            if (nombres.length() > 0) {
                nombres = nombres + ":*";
            }
            datox.setCadena(nombres);
            datox.setCod_esta(cliente.getCod_esta());
            List listarPacInter2 = this.mi.getInternadosSalaCajaNombre(datox);
            modelo.put("listarPacInter", listarPacInter2);

            Camas listcama = new Camas();
            List listarCamasSala = this.mi.getListarCamasSala(listcama);
            modelo.put("listarCamasSala", listarCamasSala);

            modelo.put("id_persona", Integer.toString(cliente.getId_persona()));
            modelo.put("id_consultorio", Integer.toString(persona.getId_consultorio()));
            return new ModelAndView("administrarhistoriales/ListarInternados", modelo);
        }

        if ("cie10".equals(revision)) {
            modelo.put("id_consultorio", Integer.toString(cliente.getId_consultorio()));
            modelo.put("tipo_medico", "23");
            modelo.put("revision", revision);
        }

        return new ModelAndView("administrarhistoriales/PlanAccionPacienteI", modelo);
    }
}
