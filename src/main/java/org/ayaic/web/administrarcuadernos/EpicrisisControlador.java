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
import org.ayaic.domain.Salas;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EpicrisisControlador {

    private final MiFacade mi;

    public EpicrisisControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/Epicrisis.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();
        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String id_persona = request.getParameter("id_persona");
        String id_paciente = request.getParameter("id_paciente");
        String id_historial = request.getParameter("id_historial");
        String id_consultorio = request.getParameter("id_consultorio");
        String clinicos = request.getParameter("clinicos");
        String diagnostico = request.getParameter("diagnostico");
        String diagnosticoegr = request.getParameter("diagnosticoegr");
        String condicion = request.getParameter("condicion");
        String causa = request.getParameter("causa");
        String examenes = request.getParameter("examenes");
        String tratquirurgico = request.getParameter("tratquirurgico");
        String tratmedico = request.getParameter("tratmedico");
        String complicaciones = request.getParameter("complicaciones");
        String pronosticovital = request.getParameter("pronosticovital");
        String pronosticofuncional = request.getParameter("pronosticofuncional");
        String control = request.getParameter("control");
        String policlinico = request.getParameter("policlinico");
        String hospital = request.getParameter("hospital");
        String recomendaciones = request.getParameter("recomendaciones");
        String id_piso = request.getParameter("id_piso");
        String id_sala = request.getParameter("id_sala");
        String id_cama = request.getParameter("id_cama");
        String id_epi = request.getParameter("id_epi");
        String[] dias = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
        String[] meses = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        String[] horas = {"00", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"};
        String[] minutos = {"00", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"};
        Date fecha1 = new Date();
        int anioq = fecha1.getYear() + 1900;
        String[] anios = {Integer.toString(anioq), Integer.toString(anioq + 1),};

        //Listar Departamentos
        List listaDepartamentos = mi.getListarPaisDep(1);
        modelo.put("listaDepartamentos", listaDepartamentos);
        Localidades local = new Localidades();
        List Estab1 = mi.getListarEsta(local);
        Localidades datoestab = (Localidades) Estab1.get(0);
        local.setId_departamento(datoestab.getId_departamento());
        local.setId_persona(10);
        local.setArea("C");
        List Estab = mi.getEstabTransCns(local);
        modelo.put("listaEstab", Estab);
        modelo.put("datoestab", datoestab);
        modelo.put("id_rol", Integer.toString(cliente.getId_rol2()));
        Consultorios a = new Consultorios();
        a.setCod_esta(cliente.getCod_esta());
        a.setId_estado("T");
        List listarCargos = mi.getListarConsultoriosTransf(a);
        modelo.put("listarCargos", listarCargos);
        modelo.put("cod_esta", Integer.toString(cliente.getCod_esta()));
        modelo.put("id_red", Integer.toString(datoestab.getId_red()));
        modelo.put("id_localidad", Integer.toString(datoestab.getId_localidad()));
        modelo.put("id_departamento", Integer.toString(datoestab.getId_departamento()));
        modelo.put("id_consultorio", Integer.toString(cliente.getId_consultorio()));
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
        modelo.put("clinicos", clinicos);
        modelo.put("diagnostico", diagnostico);
        modelo.put("diagnosticoegr", diagnosticoegr);
        modelo.put("condicion", condicion);
        modelo.put("causa", causa);
        modelo.put("examenes", examenes);
        modelo.put("tratquirurgico", tratquirurgico);
        modelo.put("tratmedico", tratmedico);
        modelo.put("complicaciones", complicaciones);
        modelo.put("pronosticovital", pronosticovital);
        modelo.put("pronosticofuncional", pronosticofuncional);
        modelo.put("control", control);
        modelo.put("policlinico", policlinico);
        modelo.put("hospital", hospital);
        modelo.put("recomendaciones", recomendaciones);
        Pacientes buscarPacienteh = mi.getDatosPaciente(Integer.parseInt(id_paciente));
        modelo.put("urgencias", Integer.toString(cliente.getId_almacen()));
        modelo.put("datos", buscarPacienteh);
        modelo.put("datosp", buscarPacienteh);
        Historiales datohi = new Historiales();
        datohi.setId_historial(Integer.parseInt(id_historial));
        datohi.setCod_esta(cliente.getCod_esta());
        Historiales datosHistorial = mi.getDatosHistorial(datohi);
        Personas buscarEmpleado = mi.getDatosPersonaInt(cliente.getId_persona());
        modelo.put("empleado", buscarEmpleado);
        modelo.put("id_epi", id_epi);
        modelo.put("tipo_medico", Integer.toString(cliente.getId_cargo()));
        modelo.put("expedido", datosHistorial.getExpedido());
        List listarSeguros = mi.getListarSeguros("A");
        modelo.put("listarSeguros", listarSeguros);

        Cuadernos datot = new Cuadernos();
        datot.setId_historial(Integer.parseInt(id_historial));
        datot.setCod_esta(cliente.getCod_esta());
        List C5 = this.mi.getPacienteCuaderno5(datot);

        datot.setTipoconsulta("P");  ////getListarEpicrisis
        datot.setCod_esta(cliente.getCod_esta());
        datot.setId_persona(cliente.getId_persona());
        datot.setId_paciente(Integer.parseInt(id_paciente));
        datot.setId_historial(Integer.parseInt(id_historial));
        List listarEpis = mi.getListarEpicrisis(datot);
        modelo.put("listarEpis", listarEpis);
        datot.setTipoconsulta("E");   ////getVerEpicrisis
        List listaEpi = mi.getVerEpicrisis(datot);
        modelo.put("condicion", "1");
        modelo.put("causa", "1");

        if (listaEpi.isEmpty()) {
            modelo.put("diagnostico", datosHistorial.getDiagnostico());
            datot.setId_historial(Integer.parseInt(id_historial));
            datot.setCod_esta(cliente.getCod_esta());
            List listalab = mi.getPacienteLaboratorio(datot);
            modelo.put("listalab", listalab);
            if (listalab.size() > 0) {
                examenes = "";
                for (int v = 0; v < listalab.size(); v++) {
                    Cuadernos labos = (Cuadernos) listalab.get(v);
                    examenes = labos.getLaboratorio() + "[" + labos.getResultado().replaceAll("null", ".").replaceAll("</u>", "").replaceAll("<u>", "").replaceAll("</p>", "").replaceAll("<p>", "") + "];" + examenes;
                    modelo.put("examenes", examenes);
                }
            }

            if (C5.size() > 0) {
                Cuadernos cuaderno5 = (Cuadernos) C5.get(0);
                id_persona = Integer.toString(cuaderno5.getId_persona());
                id_consultorio = Integer.toString(cuaderno5.getId_consultorio());
                id_cama = Integer.toString(cuaderno5.getId_cama());
                id_sala = Integer.toString(cuaderno5.getId_sala());
                id_piso = Integer.toString(cuaderno5.getId_piso());
                if (id_piso != Integer.toString(cuaderno5.getId_piso())) {
                    ////////pendiente para que pueda buscar piso cuando este vacio la epicrisis
                }

                modelo.put("anio", Integer.toString(datosHistorial.getFecha().getYear() + 1900));
                modelo.put("mes", Integer.toString(datosHistorial.getFecha().getMonth() + 1));
                modelo.put("dia", Integer.toString(datosHistorial.getFecha().getDate()));
                modelo.put("hora", Integer.toString(datosHistorial.getFecha().getHours()));
                modelo.put("minuto", Integer.toString(datosHistorial.getFecha().getMinutes()));
                modelo.put("anio2", Integer.toString(fecha1.getYear() + 1900));
                modelo.put("mes2", Integer.toString(fecha1.getMonth() + 1));
                modelo.put("dia2", Integer.toString(fecha1.getDate()));
                modelo.put("hora2", Integer.toString(fecha1.getHours()));
                modelo.put("minuto2", Integer.toString(fecha1.getMinutes()));
                modelo.put("condicion", "1");
                modelo.put("causa", "1");
            }
        }

        Salas dsala = new Salas();
        dsala.setTipo("T");
        dsala.setCod_esta(cliente.getCod_esta());
        List listarPisos = this.mi.getListarPisos(dsala);
        modelo.put("listarPisos", listarPisos);
        if (id_piso != null) {
            dsala.setId_piso(Integer.parseInt(id_piso));
            List listarSalas2 = this.mi.getListarSalasLibres(dsala);
            modelo.put("listarSalas", listarSalas2);
            modelo.put("id_piso", id_piso);
        }
        if (id_sala != null && !"0".equals(id_sala)) {
            dsala.setId_sala(Integer.parseInt(id_sala));
            List listarSalas2 = mi.getListarSalasLibres(dsala);
            modelo.put("listarSalas", listarSalas2);
            Camas lcama = new Camas();
            lcama.setTipo("T");
            lcama.setId_sala(Integer.parseInt(id_sala));
            List listarCama = mi.getListarCamas(lcama);
            modelo.put("listarCama", listarCama);
            modelo.put("id_cama", id_cama);
            modelo.put("id_sala", id_sala);
            modelo.put("id_piso", id_piso);
        }

        if ("ModificaEpi".equals(accion)) {
            Cuadernos dato = new Cuadernos();
            dato.setTipoconsulta("Q");  ////getDatoEpicrisis
            dato.setCod_esta(cliente.getCod_esta());
            dato.setId_laboratorio(Integer.parseInt(id_epi));
            dato.setId_historial(Integer.parseInt(id_historial));
            dato.setId_paciente(Integer.parseInt(id_paciente));
            List listaEpis = mi.getDatoEpicrisis(dato);
            Cuadernos listep = (Cuadernos) listaEpis.get(0);
            modelo.put("listaEpi", listaEpis);
            modelo.put("Listep", listep);
            modelo.put("anio", Integer.toString(listep.getFechap().getYear() + 1900));
            modelo.put("mes", Integer.toString(listep.getFechap().getMonth() + 1));
            modelo.put("dia", Integer.toString(listep.getFechap().getDate()));
            modelo.put("hora", Integer.toString(listep.getFechap().getHours()));
            modelo.put("minuto", Integer.toString(listep.getFechap().getMinutes()));
            modelo.put("anio2", Integer.toString(listep.getFechae().getYear() + 1900));
            modelo.put("mes2", Integer.toString(listep.getFechae().getMonth() + 1));
            modelo.put("dia2", Integer.toString(listep.getFechae().getDate()));
            modelo.put("hora2", Integer.toString(listep.getFechae().getHours()));
            modelo.put("minuto2", Integer.toString(listep.getFechae().getMinutes()));
            modelo.put("clinicos", listep.getCadena1());
            modelo.put("diagnostico", listep.getCadena2());
            modelo.put("diagnosticoegr", listep.getCadena3());
            modelo.put("condicion", Integer.toString(listep.getSuma5()));
            modelo.put("causa", Integer.toString(listep.getSuma6()));
            modelo.put("examenes", listep.getCadena6());
            modelo.put("tratquirurgico", listep.getCadena7());
            modelo.put("tratmedico", listep.getCadena8());
            modelo.put("complicaciones", listep.getCadena9());
            modelo.put("pronosticovital", listep.getCadena10());
            modelo.put("pronosticofuncional", listep.getCadena11());
            modelo.put("control", listep.getCadena12());
            modelo.put("policlinico", listep.getCadena13());
            modelo.put("hospital", listep.getCadena14());
            modelo.put("recomendaciones", listep.getCadena15());
            modelo.put("id_piso", Integer.toString(listep.getSuma1()));
            modelo.put("id_sala", Integer.toString(listep.getSuma2()));
            modelo.put("id_cama", Integer.toString(listep.getSuma3()));
            modelo.put("id_consultorio", Integer.toString(listep.getId_consultorio()));
            modelo.put("id_epi", id_epi);
            dsala.setId_piso(listep.getSuma1());
            List listarSalas2 = mi.getListarSalasLibres(dsala);
            modelo.put("listarSalas", listarSalas2);
            Camas lcama = new Camas();
            lcama.setTipo("T");
            lcama.setId_sala(listep.getSuma2());
            List listarCama = mi.getListarCamas(lcama);
            modelo.put("listarCama", listarCama);
            return new ModelAndView("administrarcuadernos/Epicrisis", modelo);
        }

        if ("ImprimirEpi".equals(accion)) {

            modelo.put("dato", cliente);
            Cuadernos dato = new Cuadernos();
            dato.setTipoconsulta("Q");
            dato.setCod_esta(cliente.getCod_esta());
            dato.setId_laboratorio(Integer.parseInt(id_epi));
            dato.setId_historial(Integer.parseInt(id_historial));
            dato.setId_paciente(Integer.parseInt(id_paciente));
            List listaEpis = mi.getDatoEpicrisis(dato);  ////getDatoEpicrisis
            Cuadernos datoep = (Cuadernos) listaEpi.get(0);
            modelo.put("listaEpi", listaEpis);
            Camas buscarCama = mi.getDatosCama(datoep.getSuma3());
            modelo.put("buscarCama", buscarCama);
            Personas buscarEmpleadoi = mi.getDatosPersonaInt(datoep.getId_persona());
            modelo.put("empleado", buscarEmpleadoi);
            Personas buscaResid = mi.getDatosPersonaInt(datoep.getId_por());
            modelo.put("emplResid", buscaResid);
            local.setArea("E");   ////getDatosEstable
            local.setCod_esta(datosHistorial.getCod_esta());
            Localidades buscarestab = this.mi.getDatosEstable(local);
            modelo.put("datoestab", buscarestab);

            return new ModelAndView(new EpicrisisPDF(), modelo);
        }

        if ("Guardar".equals(accion)) {
            String cod_esta = request.getParameter("cod_esta");
            String diai = request.getParameter("diai");
            String mesi = request.getParameter("mesi");
            String anioi = request.getParameter("anioi");
            String horai = request.getParameter("horai");
            String minutoi = request.getParameter("minutoi");
            String diai2 = request.getParameter("diai2");
            String mesi2 = request.getParameter("mesi2");
            String anioi2 = request.getParameter("anioi2");
            String horai2 = request.getParameter("horai2");
            String minutoi2 = request.getParameter("minutoi2");

            Date fecha = new Date();
            int diax = Integer.parseInt(diai);
            int mesx = Integer.parseInt(mesi) - 1;
            int aniox = Integer.parseInt(anioi) - 1900;
            int horax = Integer.parseInt(horai);
            int minutox = Integer.parseInt(minutoi);
            Date fechai = new Date(aniox, mesx, diax, horax, minutox, 0);
            int diax2 = Integer.parseInt(diai2);
            int mesx2 = Integer.parseInt(mesi2) - 1;
            int aniox2 = Integer.parseInt(anioi2) - 1900;
            int horax2 = Integer.parseInt(horai2);
            int minutox2 = Integer.parseInt(minutoi2);
            Date fechai2 = new Date(aniox2, mesx2, diax2, horax2, minutox2, 0);

            Personas buscarEmpleado2 = mi.getBuscarPersona(Integer.parseInt(id_persona));
            Consultorios c = mi.getDatosConsultorio(cliente.getId_consultorio());

            modelo.put("dato", cliente);
            Cuadernos dato = new Cuadernos();
            dato.setCod_esta(cliente.getCod_esta());  ///16-01-2016
            dato.setFechap(fechai);
            dato.setFechae(fechai2);
            dato.setId_paciente(Integer.parseInt(id_paciente));
            dato.setId_historial(Integer.parseInt(id_historial));
            dato.setId_persona(cliente.getId_persona());
            dato.setId_consultorio(Integer.parseInt(id_consultorio));
            if (cliente.getId_especialidad() == 99) {
                dato.setId_persona(Integer.parseInt(id_persona));
                dato.setId_consultorio(Integer.parseInt(id_consultorio));
            }
            dato.setId_piso(Integer.parseInt(id_piso));
            dato.setId_sala(Integer.parseInt(id_sala));
            dato.setId_cama(Integer.parseInt(id_cama));
            dato.setBacterias(c.getConsultorio());
            dato.setCadena1(clinicos);
            dato.setCadena2(diagnostico);
            dato.setCadena3(diagnosticoegr);
            dato.setSuma5(Integer.parseInt(condicion));
            dato.setSuma6(Integer.parseInt(causa));
            dato.setCadena6(examenes);
            dato.setCadena7(tratquirurgico);
            dato.setCadena8(tratmedico);
            dato.setCadena9(complicaciones);
            dato.setCadena10(pronosticovital);
            dato.setCadena11(pronosticofuncional);
            dato.setCadena12(control);
            dato.setCadena13(policlinico);
            dato.setCadena14(hospital);
            dato.setCadena15(recomendaciones);
            dato.setId_seguro(datosHistorial.getId_seguro());
            dato.setSuma22(cliente.getId_persona());
            dato.setCod_esta(cliente.getCod_esta());
            dato.setTipoconsulta("E");
            List listaepic = mi.getVerEpicrisis(dato);
            if (listaepic.isEmpty()) {
                dato.setAccion("E");
                mi.setCrearEpicrisis(dato);
            } else {
                //try{
                dato.setId_laboratorio(Integer.parseInt(id_epi));
                mi.setModificarEpicrisis(dato);   ////setModificarEpicrisis
                // }catch (Exception e){ 
                //   return new ModelAndView("Aviso","mensaje","Consulte Administrador Epicrisis");
                //}     
            }
            dato.setTipoconsulta("E");
            List listaEpi2 = mi.getVerEpicrisis(dato);
            Cuadernos datoad = (Cuadernos) listaEpi2.get(0);
            modelo.put("listaEpis", listaEpi2);
        }
        List listarEpi3 = mi.getVerEpicrisis(datot);
        modelo.put("listarEpis", listarEpi3);
        modelo.put("accion", accion);
        return new ModelAndView("administrarcuadernos/Epicrisis", modelo);
    }

}
