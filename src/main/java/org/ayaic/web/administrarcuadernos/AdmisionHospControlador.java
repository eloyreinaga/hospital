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
public class AdmisionHospControlador {

    private final MiFacade mi;

    public AdmisionHospControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/AdmisionHosp.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();
        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String id_persona = request.getParameter("id_persona");
        String id_paciente = request.getParameter("id_paciente");
        String id_historial = request.getParameter("id_historial");
        String id_consultorio = request.getParameter("id_consultorio");
        String pa = request.getParameter("pa");
        String fc = request.getParameter("fc");
        String fr = request.getParameter("fr");
        String swinter = request.getParameter("swinter");
        String temperatura = request.getParameter("temperatura");
        String talla = request.getParameter("talla");
        String peso = request.getParameter("peso");
        String diagnostico = request.getParameter("diagnostico");
        String estadogen = request.getParameter("estadogen");
        String prescripcion = request.getParameter("prescripcion");
        String nombreacom = request.getParameter("nombreacom");
        String id_piso = request.getParameter("id_piso");
        String id_sala = request.getParameter("id_sala");
        String seguro = request.getParameter("seguro");
        String id_cama = request.getParameter("id_cama");
        String id_admi = request.getParameter("id_admi");
        String padre = request.getParameter("padre");
        String madre = request.getParameter("madre");
        String conyugue = request.getParameter("conyugue");
        String conviviente = request.getParameter("conviviente");
        String direccion = request.getParameter("direccion");
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
        local.setId_departamento(Integer.parseInt(Integer.toString(cliente.getCod_esta()).substring(0, 1)));
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
        modelo.put("swinter", swinter);
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
        modelo.put("pa", pa);
        modelo.put("fc", fc);
        modelo.put("fr", fr);
        modelo.put("temperatura", temperatura);
        modelo.put("talla", talla);
        modelo.put("peso", peso);
        modelo.put("diagnostico", diagnostico);
        modelo.put("estadogen", estadogen);
        modelo.put("prescripcion", prescripcion);
        modelo.put("nombreacom", nombreacom);
        modelo.put("id_admi", id_admi);
        modelo.put("seguro", seguro);
        modelo.put("padre", padre);
        modelo.put("madre", madre);
        modelo.put("conyugue", conyugue);
        modelo.put("conviviente", conviviente);
        modelo.put("direccion", direccion);
        Pacientes buscarPacienteh = mi.getDatosPaciente(Integer.parseInt(id_paciente));
        modelo.put("urgencias", Integer.toString(cliente.getId_almacen()));
        modelo.put("datos", buscarPacienteh);
        modelo.put("datosp", buscarPacienteh);
        Historiales datohi = new Historiales();
        datohi.setId_historial(Integer.parseInt(id_historial));
        datohi.setCod_esta(cliente.getCod_esta());
        Historiales datosHistorial = mi.getDatosHistorial(datohi);
        modelo.put("Historia", datosHistorial);
        Personas buscarEmpleado = mi.getDatosPersonaInt(cliente.getId_persona());
        modelo.put("empleado", buscarEmpleado);
        modelo.put("tipo_medico", Integer.toString(cliente.getId_cargo()));
        modelo.put("expedido", datosHistorial.getExpedido());
        List listarSeguros = mi.getListarSeguros("A");
        modelo.put("listarSeguros", listarSeguros);
        Cuadernos datot = new Cuadernos();
        datot.setTipoconsulta("M");
        datot.setCod_esta(cliente.getCod_esta());
        datot.setId_persona(cliente.getId_persona());
        datot.setId_paciente(Integer.parseInt(id_paciente));
        datot.setId_historial(Integer.parseInt(id_historial));
        datot.setId_consultorio(datosHistorial.getId_consultorio());
        List listarAdm = mi.getCuadernoC1(datot);
        modelo.put("listarAdmi", listarAdm);
        datot.setTipoconsulta("A");
        List listaAdmin = mi.getVerAdmisiones(datot);   ///getVerAdmisiones
        if (listaAdmin.isEmpty()) {
            modelo.put("pa", datosHistorial.getPa());
            modelo.put("fc", datosHistorial.getFc());
            modelo.put("fr", datosHistorial.getFr());
            modelo.put("temperatura", Double.toString(datosHistorial.getTemperatura()));
            modelo.put("talla", Double.toString(datosHistorial.getTalla()));
            modelo.put("peso", Double.toString(datosHistorial.getPeso()));
            modelo.put("diagnostico", datosHistorial.getDiagnostico());
        }
        if (request.getParameter("id_consultorio") != null) {
            if (Integer.parseInt(id_consultorio) != cliente.getId_consultorio()) {
                modelo.put("id_consultorio", id_consultorio);
            }

        }

        //Cuando se eligio n departamento
        if (request.getParameter("id_departamento") != null) {
            int id_depto = Integer.parseInt(request.getParameter("id_departamento"));
            modelo.put("id_departamento", Integer.toString(id_depto));

            Localidades localidadred = new Localidades();
            localidadred.setId_departamento(id_depto);
            localidadred.setId_persona(10);
            localidadred.setArea("C");
            List Estab2 = this.mi.getEstabTransCns(localidadred);
            modelo.put("listaEstab", Estab2);
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
            modelo.put("id_sala", id_sala);
            modelo.put("id_piso", id_piso);
        }

        if ("ModificaAdm".equals(accion)) {
            Cuadernos dato = new Cuadernos();
            dato.setTipoconsulta("N");
            dato.setCod_esta(cliente.getCod_esta());
            dato.setId_laboratorio(Integer.parseInt(id_admi));
            List listaAdm = mi.getCuadernoC1(dato);
            Cuadernos listat = (Cuadernos) listaAdm.get(0);
            modelo.put("listaAdm", listaAdm);
            modelo.put("Listat", listat);
            modelo.put("anio", Integer.toString(listat.getFecha().getYear() + 1900));
            modelo.put("mes", Integer.toString(listat.getFecha().getMonth() + 1));
            modelo.put("dia", Integer.toString(listat.getFecha().getDate()));
            modelo.put("hora", Integer.toString(listat.getFecha().getHours()));
            modelo.put("minuto", Integer.toString(listat.getFecha().getMinutes()));
            modelo.put("pa", listat.getPa());
            modelo.put("fc", listat.getFc());
            modelo.put("fr", listat.getFr());
            modelo.put("temperatura", Double.toString(datosHistorial.getTemperatura()));
            modelo.put("talla", Double.toString(datosHistorial.getTalla()));
            modelo.put("peso", Double.toString(datosHistorial.getPeso()));
            modelo.put("diagnostico", listat.getDiagnostico().replaceAll("<p>", "").replaceAll("</p>", "\n"));
            modelo.put("estadogen", listat.getAspecto().replaceAll("<p>", "").replaceAll("</p>", "\n"));
            modelo.put("padre", listat.getCadena1());
            modelo.put("madre", listat.getCadena2());
            modelo.put("conyugue", listat.getCadena3());
            modelo.put("conviviente", listat.getCadena4());
            modelo.put("direccion", listat.getCadena5());
            modelo.put("prescripcion", listat.getCetonas().replaceAll("<p>", "").replaceAll("</p>", "\n"));
            modelo.put("nombreacom", listat.getCilindros());
            modelo.put("id_consultorio", Integer.toString(listat.getId_consultorio()));
            modelo.put("cod_esta", Integer.toString(listat.getCod_esta()));
            modelo.put("id_piso", Integer.toString(listat.getSuma1()));
            modelo.put("id_sala", Integer.toString(listat.getSuma2()));
            modelo.put("id_cama", Integer.toString(listat.getSuma3()));
            modelo.put("seguro", Integer.toString(listat.getSuma25()));
            modelo.put("id_admi", id_admi);
            dsala.setId_piso(listat.getSuma1());
            List listarSalas2 = mi.getListarSalasLibres(dsala);
            modelo.put("listarSalas", listarSalas2);
            Camas lcama = new Camas();
            lcama.setTipo("T");
            lcama.setId_sala(listat.getSuma2());
            List listarCama = mi.getListarCamas(lcama);
            modelo.put("listarCama", listarCama);

            datot.setTipoconsulta("M");
            if (cliente.getId_rol2() == 22 || cliente.getId_rol2() == 28) {
                datot.setTipoconsulta("G");
                datot.setNombres("%" + buscarPacienteh.getNombres().replaceAll(" ", "%") + "%");
            }
            datot.setSuma22(cliente.getId_persona());
            datot.setCod_esta(cliente.getCod_esta());
            List listarAdm3 = mi.getListarAdmisionTot(datot);  ///getListarAdmisionTot
            modelo.put("listarAdmi", listarAdm3);
            modelo.put("accion", accion);

            return new ModelAndView("administrarcuadernos/AdmisionHosp", modelo);

        }

        if ("ConsentimientoInformado".equals(accion)) {
            modelo.put("dato", cliente);
            return new ModelAndView(new ConsentimientoPDF(), modelo);
        }

        if ("ImprimirAdm".equals(accion) || "ImprimirAdm2".equals(accion)) {
            Historiales datoh = new Historiales();
            datoh.setId_historial(Integer.parseInt(id_historial));
            datoh.setId_paciente(Integer.parseInt(id_paciente));
            datoh.setCod_esta(cliente.getCod_esta());
            datoh.setAccion("E");
            List listas = mi.getListarHistoriaEmergen(datoh);
            modelo.put("milistas", listas);
            datoh.setAccion("H");

            List listasBas = mi.getListarHistoriaHoy(datoh);
            modelo.put("listaBas", listasBas);
            Cuadernos datoll = new Cuadernos();
            datoll.setId_historial(Integer.parseInt(id_historial));
            datoll.setCod_esta(cliente.getCod_esta());
            List listalab = mi.getPacienteLaboratorio(datoll);
            modelo.put("listalab", listalab);
            modelo.put("dato", cliente);
            Cuadernos dato = new Cuadernos();
            dato.setTipoconsulta("N");   ////getDatoAdmision
            dato.setCod_esta(cliente.getCod_esta());
            dato.setId_laboratorio(Integer.parseInt(id_admi));
            List listaAdmi = mi.getDatoAdmision(dato);
            Cuadernos datoad = (Cuadernos) listaAdmi.get(0);
            modelo.put("listaAdmi", listaAdmi);
            Personas buscarEmpleado3 = mi.getDatosPersonaInt(datoad.getId_persona());
            modelo.put("empleado", buscarEmpleado3);
            Camas buscarCama = mi.getDatosCama(datoad.getSuma3());
            modelo.put("buscarCama", buscarCama);
            if ("ImprimirAdm2".equals(accion)) {
                if (cliente.getId_especialidad() > 0) {
                    return new ModelAndView(new AdmisionHospPDF(), modelo);//// medico sin la parte del medio 
                }
                return new ModelAndView(new AdmisionHosp2PDF(), modelo);////admision solo la parte del medio 
            }
            return new ModelAndView(new AdmisionHosp3PDF(), modelo);  /////todo
        }

        if ("Guardar".equals(accion)) {
            String cod_esta = request.getParameter("cod_esta");
            String diai = request.getParameter("diai");
            String mesi = request.getParameter("mesi");
            String anioi = request.getParameter("anioi");
            String horai = request.getParameter("horai");
            String minutoi = request.getParameter("minutoi");
            if ("".equals(talla)) {
                talla = "0";
            }
            if ("".equals(peso)) {
                peso = "0";
            }
            if ("".equals(temperatura)) {
                temperatura = "0";
            }
            Date fecha = new Date();
            int diax = Integer.parseInt(diai);
            int mesx = Integer.parseInt(mesi) - 1;
            int aniox = Integer.parseInt(anioi) - 1900;
            int horax = Integer.parseInt(horai);
            int minutox = Integer.parseInt(minutoi);
            Date fechai = new Date(aniox, mesx, diax, horax, minutox, 0);
            Personas buscarEmpleado2 = mi.getBuscarPersona(Integer.parseInt(id_persona));
            Consultorios datosconsul = mi.getDatosConsultorio(Integer.parseInt(id_consultorio));
            Localidades localidad = new Localidades();
            localidad.setArea("E");
            localidad.setCod_esta(Integer.parseInt(cod_esta));
            Localidades buscarestab = mi.getDatosEstable(localidad);
            Historiales datoh = new Historiales();
            datoh.setId_historial(Integer.parseInt(id_historial));
            datoh.setAccion("E");
            List listas = mi.getListarHistoriaEmergen(datoh);
            modelo.put("milistas", listas);
            datoh.setAccion("H");
            datoh.setId_paciente(Integer.parseInt(id_paciente));
            List listasBas = mi.getListarHistoriaHoy(datoh);
            modelo.put("listaBas", listasBas);
            Cuadernos datoll = new Cuadernos();
            datoll.setId_historial(Integer.parseInt(id_historial));
            datoll.setCod_esta(cliente.getCod_esta());
            List listalab = mi.getPacienteLaboratorio(datoll);
            modelo.put("listalab", listalab);
            modelo.put("dato", cliente);
            Cuadernos dato = new Cuadernos();
            dato.setFecha(fechai);
            dato.setCod_esta(cliente.getCod_esta());  ///16-01-2016
            dato.setId_paciente(Integer.parseInt(id_paciente));
            dato.setId_historial(Integer.parseInt(id_historial));
            dato.setId_persona(cliente.getId_persona());
            dato.setId_consultorio(cliente.getId_consultorio());
            if (cliente.getId_especialidad() == 99 || cliente.getId_cargo() == 0) {
                dato.setId_persona(datosHistorial.getId_persona());
                dato.setId_consultorio(datosHistorial.getId_consultorio());
            }
            dato.setId_piso(Integer.parseInt(id_piso));
            dato.setId_sala(Integer.parseInt(id_sala));
            dato.setId_cama(Integer.parseInt(id_cama));
            dato.setPa(pa);
            dato.setFc(fc);
            dato.setFr(fr);
            dato.setTalla(Double.parseDouble(talla));
            dato.setPeso(Double.parseDouble(peso));
            dato.setTemperatura(Double.parseDouble(temperatura));
            dato.setBacterias(datosconsul.getConsultorio());
            dato.setBilirrubina(buscarestab.getEstablecimiento());
            dato.setDiagnostico(diagnostico);
            dato.setAspecto(estadogen);
            dato.setCetonas(prescripcion);
            dato.setCilindros(nombreacom);
            dato.setCadena1(padre);
            dato.setCadena2(madre);
            dato.setCadena3(conyugue);
            dato.setCadena4(conviviente);
            dato.setCadena5(direccion);
            dato.setSuma20(Integer.parseInt(id_consultorio));
            dato.setSuma21(Integer.parseInt(cod_esta));
            dato.setSuma22(cliente.getId_persona());
            dato.setSuma25(Integer.parseInt(seguro));
            dato.setId_seguro(datosHistorial.getId_seguro());
            dato.setCod_esta(cliente.getCod_esta());
            dato.setTipoconsulta("A");
            List listaAdmi = mi.getVerAdmisiones(dato);
            if (listaAdmi.isEmpty()) {
                dato.setAccion("A");
                mi.setCrearAdmision(dato);
            } else {
                try {
                    dato.setAccion("A");   ////setModificarAdmision
                    Cuadernos admis = (Cuadernos) listaAdmi.get(0);
                    dato.setId_laboratorio(admis.getId_laboratorio());
                    mi.setModificarAdmision(dato);
                } catch (Exception e) {
                    return new ModelAndView("Aviso", "mensaje", "No se creo Hoja de Admision, Consulte Administrador");
                }
            }

            dato.setTipoconsulta("A");
            List listaAdmi2 = mi.getVerAdmisiones(dato);
            Cuadernos datoad = (Cuadernos) listaAdmi2.get(0);
            modelo.put("listaAdmi", listaAdmi2);
            Camas buscarCama = mi.getDatosCama(datoad.getSuma3());
            modelo.put("buscarCama", buscarCama);
            modelo.put("id_cama", id_cama);
            //return new ModelAndView(new AdmisionHospPDF(), modelo);
        }
        datot.setTipoconsulta("M");
        if (cliente.getId_rol2() == 22 || cliente.getId_rol2() == 28) {
            datot.setTipoconsulta("G");
            datot.setNombres("%" + buscarPacienteh.getNombres().replaceAll(" ", "%") + "%");
        }
        datot.setSuma22(cliente.getId_persona());
        List listarAdm3 = mi.getListarAdmision(datot);  ///getListarAdmision
        modelo.put("listarAdmi", listarAdm3);
        modelo.put("accion", accion);
        return new ModelAndView("administrarcuadernos/AdmisionHosp", modelo);
    }

}
