package org.ayaic.web.administrarpacientes;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.ayaic.domain.Historiales;
import org.ayaic.domain.Localidades;
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.Personas;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ListarPacientesInterControlador {

    private final MiFacade mi;

    public ListarPacientesInterControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/fichas")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        //Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String boton = request.getParameter("boton");   
        String accion = request.getParameter("accion");
        String accion1 = request.getParameter("accion1");
        String swci = request.getParameter("swci");
        String[] dias = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
        String[] meses = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        Date fecha1 = new Date();
        int anioq = fecha1.getYear() + 1900;
        String[] anios = {Integer.toString(anioq), Integer.toString(anioq - 1), Integer.toString(anioq - 2), Integer.toString(anioq - 3), Integer.toString(anioq - 4), Integer.toString(anioq - 5), Integer.toString(anioq - 6), Integer.toString(anioq - 7), Integer.toString(anioq - 8), Integer.toString(anioq - 9), Integer.toString(anioq - 10), Integer.toString(anioq - 11), Integer.toString(anioq - 12), Integer.toString(anioq - 13), Integer.toString(anioq - 14), Integer.toString(anioq - 15), Integer.toString(anioq - 16), Integer.toString(anioq - 17), Integer.toString(anioq - 18), Integer.toString(anioq - 19), Integer.toString(anioq - 20), Integer.toString(anioq - 21), Integer.toString(anioq - 22), Integer.toString(anioq - 23), Integer.toString(anioq - 24), Integer.toString(anioq - 25), Integer.toString(anioq - 26), Integer.toString(anioq - 27), Integer.toString(anioq - 28), Integer.toString(anioq - 29), Integer.toString(anioq - 30), Integer.toString(anioq - 31), Integer.toString(anioq - 32), Integer.toString(anioq - 33), Integer.toString(anioq - 34), Integer.toString(anioq - 35), Integer.toString(anioq - 36), Integer.toString(anioq - 37), Integer.toString(anioq - 38), Integer.toString(anioq - 39), Integer.toString(anioq - 40), Integer.toString(anioq - 41), Integer.toString(anioq - 42), Integer.toString(anioq - 43), Integer.toString(anioq - 44), Integer.toString(anioq - 45), Integer.toString(anioq - 46), Integer.toString(anioq - 47), Integer.toString(anioq - 48), Integer.toString(anioq - 49), Integer.toString(anioq - 50), Integer.toString(anioq - 51), Integer.toString(anioq - 52), Integer.toString(anioq - 53), Integer.toString(anioq - 54), Integer.toString(anioq - 55), Integer.toString(anioq - 56), Integer.toString(anioq - 57), Integer.toString(anioq - 58), Integer.toString(anioq - 59), Integer.toString(anioq - 60), Integer.toString(anioq - 61), Integer.toString(anioq - 62), Integer.toString(anioq - 63), Integer.toString(anioq - 64), Integer.toString(anioq - 65), Integer.toString(anioq - 66), Integer.toString(anioq - 67), Integer.toString(anioq - 68), Integer.toString(anioq - 69), Integer.toString(anioq - 70), Integer.toString(anioq - 71), Integer.toString(anioq - 72), Integer.toString(anioq - 73), Integer.toString(anioq - 74), Integer.toString(anioq - 75), Integer.toString(anioq - 76), Integer.toString(anioq - 77), Integer.toString(anioq - 78), Integer.toString(anioq - 79), Integer.toString(anioq - 80), Integer.toString(anioq - 81), Integer.toString(anioq - 82), Integer.toString(anioq - 83), Integer.toString(anioq - 84), Integer.toString(anioq - 85), Integer.toString(anioq - 86), Integer.toString(anioq - 87), Integer.toString(anioq - 88), Integer.toString(anioq - 89), Integer.toString(anioq - 90), Integer.toString(anioq - 91), Integer.toString(anioq - 92), Integer.toString(anioq - 93), Integer.toString(anioq - 94), Integer.toString(anioq - 95), Integer.toString(anioq - 96), Integer.toString(anioq - 97), Integer.toString(anioq - 98), Integer.toString(anioq - 99), Integer.toString(anioq - 100), Integer.toString(anioq - 101), Integer.toString(anioq - 102), Integer.toString(anioq - 103), Integer.toString(anioq - 104), Integer.toString(anioq - 105), Integer.toString(anioq - 106), Integer.toString(anioq - 107), Integer.toString(anioq - 108), Integer.toString(anioq - 109), Integer.toString(anioq - 110)};
        String[] horas = {"00", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"};
        String[] minutos = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"};
        Date dFechaini1 = new Date();
        
        modelo.put("anio", Integer.toString(fecha1.getYear() + 1900));
        modelo.put("mes", Integer.toString(fecha1.getMonth() + 1));
        modelo.put("dia", Integer.toString(fecha1.getDate()));
        modelo.put("anios", anios);
        modelo.put("meses", meses);
        modelo.put("dias", dias);
        modelo.put("horas", horas);
        modelo.put("minutos", minutos);

        Localidades local = new Localidades();
        List Estab1 = this.mi.getListarEsta(local);
        Localidades datoestab = (Localidades) Estab1.get(0); ////el que esta en id_estado='A' para saber de que entidad es
        modelo.put("estab", Integer.toString(datoestab.getCod_esta()));

        if ("Buscar Paciente".equals(boton)){
            String nombres = request.getParameter("nombre");
            String nombreb = request.getParameter("nombre");
            String carnet = request.getParameter("carnet");
            String carnetb = request.getParameter("carnet");
            
            if ("".equals(nombres) || nombres==null ){
                return new ModelAndView("administrarpacientes/ListarPacientesInter", modelo);
            }
            if ("".equals(carnet) || carnet==null ){
                return new ModelAndView("administrarpacientes/ListarPacientesInter", modelo);
            }

            nombres = nombres.trim();
            nombres = nombres.replaceAll(" +", " ");///quita espacios en blanco entre cadenas
            nombreb = nombres;
            carnet = carnet.trim();
            carnet = carnet.replaceAll(" +", " ");///quita espacios en blanco entre cadenas
            carnetb = carnet;
            nombres = nombres.trim()+" "+carnet.trim();
            nombres = nombres.replaceAll("\\s", ":*&");///:*&
            nombres = nombres.replaceAll("ñ", "n");
            nombres = nombres.replaceAll("Ñ", "N");
            
            Historiales dato = new Historiales();
            dato.setNombres(nombres);
            List listarInter = this.mi.getListarReservacionesInternet(dato);/// ya se tiene fichas por internet
            modelo.put("listaFichas", listarInter);
            
             Pacientes paciente = new Pacientes();
            if(listarInter.isEmpty()){
                paciente.setNombres(nombres);
               List listarPac = this.mi.getListarPacientesFichas(paciente);
               modelo.put("listaPacientes", listarPac); 
            }
            
            modelo.put("nombres", nombreb);
            modelo.put("carnetb", carnetb);
        } 
        
        if ("Ficha".equals(accion) || "Si".equals(swci)  || "Aceptar".equals(accion) ){
            String id_paciente = request.getParameter("id_paciente");
            String anio = "0"; String mes = "0"; String dia = "0"; 

            Pacientes buscarPaciente = this.mi.getDatosPaciente(Integer.parseInt(id_paciente)); // saca un registro a ser modificado
            modelo.put("datos", buscarPaciente);
            
            Personas buscarEmp = new Personas();
            if(buscarPaciente.getId_policlinico()==0){
                return new ModelAndView("Aviso", "mensaje", "No tiene asignado un consultorio, actualice sus datos en Vigencia o Admisiones");
            }else{
                buscarEmp = this.mi.getDatosPersona(buscarPaciente.getId_policlinico());
                modelo.put("buscarPersona", buscarEmp);
                buscarEmp.setCod_esta(buscarEmp.getCod_esta());
                buscarEmp.setId_persona(buscarPaciente.getId_policlinico());
                List listarPersonas = this.mi.getListarPersonasFichas(buscarEmp);
                modelo.put("listarFichas", listarPersonas);
            
                modelo.put("i_consultorio", buscarEmp.getId_consultorio());
                modelo.put("id_persona", buscarEmp.getId_persona());
                modelo.put("Consultorio", buscarEmp.getConsultorio());
                modelo.put("nombres", buscarEmp.getNombres());
            }
            
            Date fecha_nac = buscarPaciente.getFec_nacimiento();
            int xanio = fecha_nac.getYear() + 1900;
            int xmes = fecha_nac.getMonth() + 1;
            int xdia = fecha_nac.getDate();
            String fecha_nacimiento = Integer.toString(xdia) + "/" + Integer.toString(xmes) + "/" + Integer.toString(xanio);
            modelo.put("fec_nacimiento", fecha_nacimiento);

            modelo.put("id_paciente", id_paciente);
            xanio = fecha1.getYear() + 1900;
            xmes = fecha1.getMonth()+1;
            xdia = fecha1.getDate();
            if(xmes<10) mes="0"+xmes; else mes=Integer.toString(xmes); 
            if(xdia<10) dia="0"+xdia; else dia=Integer.toString(xdia);
            modelo.put("fecha", dia+ "-" +mes+ "-" +xanio);
            modelo.put("accion", accion);
            
            if ("Aceptar".equals(accion) ){
                String numf = "0";
                
                int iAnio1 = fecha1.getYear();
                int iMes1 = fecha1.getMonth();
                int iDia1 = fecha1.getDate();
                int iHor1 = Integer.parseInt(accion1.substring(4, 6));
                int iMin1 = Integer.parseInt(accion1.substring(7, 9));

                dFechaini1 = new Date(iAnio1, iMes1, iDia1, iHor1, iMin1, 00);
                
                if (buscarEmp != null) {
                    if (1 + buscarEmp.getSuma2() < 10) {
                        numf = "0" + (1 + buscarEmp.getSuma2());
                    }
                    if (1 + buscarEmp.getSuma2() > 9) {
                        numf = Integer.toString(1 + buscarEmp.getSuma2());
                    }
                } else {
                    numf = "01";
                }
                
                Historiales reserva = new Historiales();
                reserva.setId_paciente(Integer.parseInt(id_paciente));
                reserva.setId_consultorio(buscarEmp.getId_consultorio());
                reserva.setUlt_usuario(buscarEmp.getId_persona());
                reserva.setId_seguro(buscarPaciente.getId_seguro());
                reserva.setEdad(buscarPaciente.getEdad());
                reserva.setExpedido("P");
                reserva.setId_estado("C");
                reserva.setFecha(dFechaini1);
                reserva.setCod_esta(buscarEmp.getCod_esta());
                reserva.setId_persona(0);
                reserva.setCadena2(buscarEmp.getCadena2());
                reserva.setCadena3(numf + " - " + buscarEmp.getCadena2());
                reserva.setTipoconsult(100);////para fichas por internet
                this.mi.setCrearReservacion(reserva);
                this.mi.setCrearReservacionFicha(reserva);
                return new ModelAndView("administrarpacientes/ListarPacientesInter", modelo);
            }
            
            return new ModelAndView("administrarpacientes/ConfirmarPacienteInter", modelo);
        }

        return new ModelAndView("administrarpacientes/ListarPacientesInter", modelo);

    }
}
