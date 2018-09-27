package org.ayaic.web.administrarcuadernos;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Cuadernos;
import org.ayaic.domain.Historiales;
import org.ayaic.domain.Localidades;
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.Sexos;
import org.ayaic.domain.Usuarios;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Cuaderno5Controlador {

    private final MiFacade mi;

    public Cuaderno5Controlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/Cuaderno5.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        Usuarios usuario = this.mi.getDatosUsuario(cliente.getId_usuario());
        String accion = request.getParameter("accion");
        String swinter = request.getParameter("swinter");
        String id_paciente = request.getParameter("id_paciente");
        String id_persona = request.getParameter("id_persona");
        String id_reservacion = request.getParameter("id_reservacion");
        String id_consultorio = request.getParameter("id_consultorio");
        String id_pedido = request.getParameter("id_pedido");
        String nombres = request.getParameter("nombres");
        String id_sexo = request.getParameter("id_sexo");
        String edad = request.getParameter("edad");
        String sw = request.getParameter("sw");
        String tipo_medico = request.getParameter("tipo_medico");
        String expedido = request.getParameter("expedido");
        String[] lingreso = {"Expontaneo", "Refererido"};
        String[] dias = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
        String[] meses = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        String[] horas = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"};
        String[] minutos = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"};
        Date fecha1 = new Date();
        int anioq = fecha1.getYear() + 1900;
        String[] anios = {(Integer.toString(anioq)), (Integer.toString(anioq - 1))};
        String ip = request.getRemoteAddr();
        String host = request.getRemoteHost();
        int diasInter = 0;

        Localidades local = new Localidades();
        List Estab1 = this.mi.getListarEsta(local);
        Localidades datol = (Localidades) Estab1.get(0);
        modelo.put("localidades", datol);

        Pacientes buscarPaciente = this.mi.getDatosPaciente(Integer.parseInt(id_paciente)); // saca un registro a ser modificado
        modelo.put("datos", buscarPaciente);

        if ("Cuaderno5".equals(accion)) {
            Cuadernos dato5 = new Cuadernos();
            dato5.setCod_esta(cliente.getCod_esta());
            dato5.setId_paciente(Integer.parseInt(id_paciente));
            List listaUni = this.mi.getVerCuaderno5Uni(dato5);
            modelo.put("listaCobros", listaUni);
            return new ModelAndView(new VerCuaderno5PDF(), modelo);
        }
        Cuadernos datoc5 = new Cuadernos();
        datoc5.setCod_esta(cliente.getCod_esta());
        datoc5.setId_historial(Integer.parseInt(id_reservacion));
        datoc5.setCod_esta(cliente.getCod_esta());
        List C5 = this.mi.getPacienteCuaderno5(datoc5);
        if (C5.size() == 0) {
            return new ModelAndView("Aviso", "mensaje", "Este Paciente no fue Internado");
        }
        Cuadernos c51 = (Cuadernos) C5.get(0);
        //Buscar Sexo
        Sexos buscarsexo = this.mi.getDatosSexo(buscarPaciente.getId_tipo_sexo()); // saca un registro a ser modificado
        modelo.put("buscarSexo", buscarsexo);

        modelo.put("id_paciente", id_paciente);
        modelo.put("id_reservacion", id_reservacion);
        modelo.put("id_consultorio", id_consultorio);
        modelo.put("id_persona", id_persona);
        modelo.put("expedido", expedido);
        modelo.put("tipo_medico", tipo_medico);
        modelo.put("swinter", swinter);
        modelo.put("anios", anios);
        modelo.put("meses", meses);
        modelo.put("dias", dias);
        modelo.put("horas", horas);
        modelo.put("minutos", minutos);
        modelo.put("anio", Integer.toString(c51.getFec_ingreso().getYear() + 1900));
        modelo.put("mes", Integer.toString(c51.getFec_ingreso().getMonth() + 1));
        modelo.put("dia", Integer.toString(c51.getFec_ingreso().getDate()));
        modelo.put("hora", Integer.toString(c51.getFec_ingreso().getHours()));
        modelo.put("minuto", Integer.toString(c51.getFec_ingreso().getMinutes()));
        modelo.put("anioe", Integer.toString(c51.getFec_egreso().getYear() + 1900));
        modelo.put("mese", Integer.toString(c51.getFec_egreso().getMonth() + 1));
        modelo.put("diae", Integer.toString(c51.getFec_egreso().getDate()));
        modelo.put("horae", Integer.toString(c51.getFec_egreso().getHours()));
        modelo.put("minutoe", Integer.toString(c51.getFec_egreso().getMinutes()));
        
        Historiales datohi = new Historiales();
        datohi.setId_historial(Integer.parseInt(id_reservacion));
        datohi.setCod_esta(cliente.getCod_esta());
        Historiales datosHistorial = this.mi.getDatosHistorial(datohi);
        modelo.put("datoHisto", datosHistorial); 
        
        if ("Agregar".equals(accion)) {
            // String id_historia     = request.getParameter("id_historia");
            // String id_cama        = request.getParameter("id_cama");
            String tipo = request.getParameter("tipo");

            datosHistorial.setRepetida(datosHistorial.getRepetida());
            datosHistorial.setId_historial(Integer.parseInt(id_reservacion));
            datosHistorial.setCod_esta(cliente.getCod_esta());
            datosHistorial.setId_por(cliente.getId_persona());
            int iResultado = this.mi.setRegistrarHistorial(datosHistorial);
            String tingreso = request.getParameter("tingreso");
            String dingreso = request.getParameter("CieI1");
            String diagnostico = request.getParameter("diagnostico");
            String cirugia = request.getParameter("cirugia");
            String fec_egreso = request.getParameter("fec_egreso");
            String anastecia = request.getParameter("anastecia");
            String degreso = request.getParameter("CieE2");
            String diasi = request.getParameter("diasi");
            String diasc = request.getParameter("diasi");
            String emenor5 = request.getParameter("emenor5");
            String tipoegreso = request.getParameter("tipoegreso");
            String fallecido = request.getParameter("fallecido");
            String fallecidom5 = request.getParameter("fallecidom5");
            String mmaterna = request.getParameter("mmaterna");
            String motros = request.getParameter("motros");
            String ref = request.getParameter("ref");
            String cref = request.getParameter("cref");

            Cuadernos dato = new Cuadernos();
            dato.setCod_esta(cliente.getCod_esta());
            dato.setTipoconsulta(tipo);
            dato.setId_historial(Integer.parseInt(id_reservacion));
            dato.setServicio(Integer.parseInt(id_consultorio));
            dato.setReferido(request.getParameter("ref"));
            dato.setContraref(request.getParameter("cref"));
            dato.setDiagnostico_ing(dingreso);
            dato.setDiagnostico(degreso);
            dato.setDiasi(Integer.parseInt(diasi));
            dato.setDiasc(Integer.parseInt(diasc));
            dato.setId_estado("A");

            if ("R".equals(tingreso)) {
                dato.setIngreso(0);
            }
            if ("E".equals(tingreso)) {
                dato.setIngreso(1);
            }
            if ("N".equals(cirugia)) {
                dato.setCirugia(0);
            }
            if ("M".equals(cirugia)) {
                dato.setCirugia(1);
            }
            if ("Y".equals(cirugia)) {
                dato.setCirugia(2);
            }
            if ("N".equals(anastecia)) {
                dato.setAnastecia(0);
            }
            if ("G".equals(anastecia)) {
                dato.setAnastecia(1);
            }
            if ("R".equals(anastecia)) {
                dato.setAnastecia(2);
            }
            if ("G".equals(emenor5)) {
                dato.setEgreso(0);
            }
            if ("D".equals(emenor5)) {
                dato.setEgreso(1);
            }
            if ("N".equals(emenor5)) {
                dato.setEgreso(2);
            }
            if ("O".equals(emenor5)) {
                dato.setEgreso(3);
            }
            if ("G".equals(tipoegreso)) {
                dato.setTipo_egreso(1);
            }
            if ("D".equals(tipoegreso)) {
                dato.setTipo_egreso(2);
            }
            if ("N".equals(tipoegreso)) {
                dato.setTipo_egreso(3);
            }
            if ("F".equals(tipoegreso)) {
                dato.setTipo_egreso(4);
            }
            if ("G".equals(fallecido)) {
                dato.setFallecido(0);
            }
            if ("A".equals(fallecido)) {
                dato.setFallecido(1);
            }
            if ("M".equals(fallecido)) {
                dato.setFallecido(2);
            }
            if ("G".equals(fallecidom5)) {
                dato.setFallecidom5(0);
            }
            if ("R".equals(fallecidom5)) {
                dato.setFallecidom5(1);
            }
            if ("A".equals(fallecidom5)) {
                dato.setFallecidom5(2);
            }
            if ("D".equals(fallecidom5)) {
                dato.setFallecidom5(3);
            }
            if ("N".equals(fallecidom5)) {
                dato.setFallecidom5(4);
            }
            if ("T".equals(fallecidom5)) {
                dato.setFallecidom5(5);
            }
            if ("O".equals(fallecidom5)) {
                dato.setFallecidom5(6);
            }
            if ("1".equals(mmaterna)) {
                dato.setFallecidoy5(1);
            }
            if ("1".equals(motros)) {
                dato.setFallecidoy5(2);
            }

            if (buscarPaciente.getEdad() < 5) {
                dato.setEdad(1);
            }
            if (buscarPaciente.getEdad() >= 5 && buscarPaciente.getEdad() <= 9) {
                dato.setEdad(1);
            }
            if (buscarPaciente.getEdad() >= 10 && buscarPaciente.getEdad() <= 20) {
                dato.setEdad(1);
            }
            if (buscarPaciente.getEdad() >= 21 && buscarPaciente.getEdad() <= 59) {
                dato.setEdad(1);
            }
            if (buscarPaciente.getEdad() > 59) {
                dato.setEdad(1);
            }
            // grabar los datos introducidos en la ventana       
            this.mi.setModificarCuaderno5(dato);

            Historiales datoi = new Historiales();
            datoi.setId_persona(Integer.parseInt(id_persona));
            datoi.setAccion("R");
            datoi.setId_historial(Integer.parseInt(id_reservacion));
            datoi.setId_cargo(3);
            datoi.setCod_esta(cliente.getCod_esta());
            this.mi.setModificarInternado(datoi);
            return new ModelAndView("Aviso", "mensaje", "El Paciente fue dado de alta Satisfactoriamente");
        }

        if ("Cambiar".equals(accion)) {
            String diai = request.getParameter("diai");
            String mesi = request.getParameter("mesi");
            String anioi = request.getParameter("anioi");
            String horai = request.getParameter("horai");
            String minutoi = request.getParameter("minutoi");
            String diae = request.getParameter("diae");
            String mese = request.getParameter("mese");
            String anioe = request.getParameter("anioe");
            String horae = request.getParameter("horae");
            String minutoe = request.getParameter("minutoe");

            String CIE10i = "";
            String CIE10e = "";
            Cuadernos datomo = new Cuadernos();
            datomo.setCod_esta(cliente.getCod_esta());
            datomo.setAspecto("M");
            datomo.setId_historial(Integer.parseInt(id_reservacion));
            List listaCie = this.mi.getVerCuaderno1CieMorbi(datomo);
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
            //Cuadernos datoc5=new Cuadernos();
            datoc5.setCod_esta(cliente.getCod_esta());
            datoc5.setId_historial(Integer.parseInt(id_reservacion));
            datoc5.setCod_esta(cliente.getCod_esta());
            List listaOdon = this.mi.getPacienteCuaderno5(datoc5);
            //List listaOdon = this.mi.getPacienteCuaderno5(Integer.parseInt(id_reservacion));
            Cuadernos cuaderno5 = (Cuadernos) listaOdon.get(0);

            int diax = Integer.parseInt(diai);
            int mesx = Integer.parseInt(mesi) - 1;
            int aniox = Integer.parseInt(anioi) - 1900;
            int horax = Integer.parseInt(horai);
            int minutox = Integer.parseInt(minutoi);
            Date fec_ing = new Date(aniox, mesx, diax, horax, minutox, 00);
            int diay = Integer.parseInt(diae);
            int mesy = Integer.parseInt(mese) - 1;
            int anioy = Integer.parseInt(anioe) - 1900;
            int horay = Integer.parseInt(horae);
            int minutoy = Integer.parseInt(minutoe);
            Date fec_egr = new Date(anioy, mesy, diay, horay, minutoy, 00);

            Cuadernos datoi = new Cuadernos();
            datoi.setCod_esta(cliente.getCod_esta());
            datoi.setId_historial(Integer.parseInt(id_reservacion));
            datoi.setAccion("A");   /////setModificarInternadoFecha
            datoi.setFec_ingreso(fec_ing);
            datoi.setFec_egreso(fec_egr);
            datoi.setCod_esta(cliente.getCod_esta());
            datoi.setCama(ip);///18/11/2016
            datoi.setSala(host);///18/11/2016
            datoi.setEstimc("M");///18/11/2016
            datoi.setId_provincia(cliente.getId_persona());///18/11/2016

            this.mi.setModificarInternadoFecha(datoi);
            //Sacar los datos de internaciones
            List lista5 = this.mi.getPacienteCuaderno5(datoi);
            Cuadernos cuaderno51 = (Cuadernos) lista5.get(0);
            Date ding = cuaderno51.getFec_ingreso();
            Date degr = cuaderno51.getFec_egreso();
            long fechaInicialMs = ding.getTime();
            long fechaFinalMs = degr.getTime();
            long diferencia = fechaFinalMs - fechaInicialMs;
            double diass = Math.floor(diferencia / (1000 * 60 * 60 * 24));
            diasInter = (int) (Math.round(((float) diass)));

            modelo.put("anios", anios);
            modelo.put("meses", meses);
            modelo.put("dias", dias);
            modelo.put("horas", horas);
            modelo.put("minutos", minutos);
            modelo.put("anio", Integer.toString(cuaderno51.getFec_ingreso().getYear() + 1900));
            modelo.put("mes", Integer.toString(cuaderno51.getFec_ingreso().getMonth() + 1));
            modelo.put("dia", Integer.toString(cuaderno51.getFec_ingreso().getDate()));
            modelo.put("hora", Integer.toString(cuaderno51.getFec_ingreso().getHours()));
            modelo.put("minuto", Integer.toString(cuaderno51.getFec_ingreso().getMinutes()));
            modelo.put("anioe", Integer.toString(cuaderno51.getFec_egreso().getYear() + 1900));
            modelo.put("mese", Integer.toString(cuaderno51.getFec_egreso().getMonth() + 1));
            modelo.put("diae", Integer.toString(cuaderno51.getFec_egreso().getDate()));
            modelo.put("horae", Integer.toString(cuaderno51.getFec_egreso().getHours()));
            modelo.put("minutoe", Integer.toString(cuaderno51.getFec_egreso().getMinutes()));

            modelo.put("diasInter", Integer.toString(diasInter));
            modelo.put("listaExter", lista5);
            modelo.put("nombres", buscarPaciente.getNombres());

            return new ModelAndView("administrarcuadernos/Cuaderno5", modelo);

        }

        if ("Eliminar".equals(accion)) {
            String id_cuaderno = request.getParameter("id_cuaderno");
            Cuadernos datox = new Cuadernos();
            datox.setCod_esta(cliente.getCod_esta());
            datox.setId_cuaderno(Integer.parseInt(id_cuaderno));
            datox.setId_historial(Integer.parseInt(id_reservacion));
            this.mi.setEliminarCuaderno5(datox);
        }
        if ("Terminar".equals(accion)) {
            Historiales datoi = new Historiales();
            datoi.setId_persona(Integer.parseInt(id_persona));
            datoi.setAccion("R");
            datoi.setId_historial(Integer.parseInt(id_reservacion));
            datoi.setId_cargo(3);
            datoi.setCod_esta(cliente.getCod_esta());
            this.mi.setModificarInternado(datoi);
            return new ModelAndView("Aviso", "mensaje", "El Paciente fue dado de alta Satisfactoriamente");
            //   if("inter".equals(swinter)) 
            //       return new ModelAndView("administrarhistoriales/PlanAccionPacienteI", modelo);
            //   else
            //      return new ModelAndView("administrarhistoriales/PlanAccionPaciente", modelo);
        }

        Cuadernos datomo = new Cuadernos();
        datomo.setCod_esta(cliente.getCod_esta());
        datomo.setAspecto("M");
        datomo.setId_historial(Integer.parseInt(id_reservacion));
        List listaCie = this.mi.getVerCuaderno1CieMorbi(datomo);
        String CIE10i = "";
        String CIE10e = "";
        for (int i = 0; i < listaCie.size(); i++) {
            Cuadernos cie10 = (Cuadernos) listaCie.get(i);
            if ("B".equals(cie10.getRegistro())) {
                CIE10i = cie10.getTipodent() + "[" + cie10.getObservaciones() + "]" + ";" + CIE10i;
                modelo.put("CieI", CIE10i);
            } else {
                CIE10e = cie10.getTipodent() + "[" + cie10.getObservaciones() + "]" + ";" + CIE10e;
                modelo.put("CieE", CIE10e);
            }
        }
        //Sacar los datos de internaciones
        //Cuadernos datoc5=new Cuadernos();
        datoc5.setCod_esta(cliente.getCod_esta());
        datoc5.setId_historial(Integer.parseInt(id_reservacion));
        datoc5.setCod_esta(cliente.getCod_esta());
        List listaOdon = this.mi.getPacienteCuaderno5(datoc5);
        //List listaOdon = this.mi.getPacienteCuaderno5(Integer.parseInt(id_reservacion));
        modelo.put("listaExter", listaOdon);
        modelo.put("nombres", buscarPaciente.getNombres());
        modelo.put("lingreso", lingreso);

        return new ModelAndView("administrarcuadernos/Cuaderno5", modelo);
    }
}
