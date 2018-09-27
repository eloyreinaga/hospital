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
import org.ayaic.domain.Historiales;
import org.ayaic.domain.Localidades;
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.Personas;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TransferenciaControlador {

    private final MiFacade mi;

    public TransferenciaControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/Transferencia.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();
        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String id_trans = request.getParameter("id_trans");
        String id_persona = request.getParameter("id_persona");
        String swinter = request.getParameter("swinter");
        String id_paciente = request.getParameter("id_paciente");
        String id_historial = request.getParameter("id_historial");
        String[] dias = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
        String[] meses = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        String[] horas = {"00", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"};
        String[] minutos = {"00", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"};
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
        modelo.put("id_trans", id_trans);
        modelo.put("swinter", swinter);

        Consultorios a = new Consultorios();
        a.setCod_esta(cliente.getCod_esta());
        a.setId_estado("T");
        List listarCargos = this.mi.getListarConsultoriosTransf(a);
        modelo.put("listarCargos", listarCargos);

        modelo.put("cod_esta", Integer.toString(cliente.getCod_esta()));
        modelo.put("id_red", Integer.toString(datoestab.getId_red()));
        modelo.put("id_localidad", Integer.toString(datoestab.getId_localidad()));
        modelo.put("id_departamento", Integer.toString(cliente.getCod_esta()).substring(0, 1));
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

        Pacientes buscarPacienteh = this.mi.getDatosPaciente(Integer.parseInt(id_paciente)); // saca un registro a ser modificado
        modelo.put("urgencias", Integer.toString(cliente.getId_almacen()));
        modelo.put("datos", buscarPacienteh);
        modelo.put("datosp", buscarPacienteh);

        Historiales datohi = new Historiales();
        datohi.setId_historial(Integer.parseInt(id_historial));
        datohi.setCod_esta(cliente.getCod_esta());
        Historiales datosHistorial = this.mi.getDatosHistorial(datohi);
        Personas buscarEmpleado = this.mi.getDatosPersonaInt(datosHistorial.getId_persona());   //11/06/2015  
        modelo.put("empleado", buscarEmpleado);
        modelo.put("tipo_medico", Integer.toString(cliente.getId_cargo()));
        modelo.put("expedido", datosHistorial.getExpedido());

        Cuadernos datot = new Cuadernos();
        datot.setTipoconsulta("L");    ////getListarTransferencia
        datot.setCod_esta(cliente.getCod_esta());
        datot.setId_persona(cliente.getId_persona());
        datot.setId_paciente(Integer.parseInt(id_paciente));
        List listarTrans2 = this.mi.getListarTransferencia(datot);
        modelo.put("listarTransfer", listarTrans2);

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

        if ("ModificaTrans".equals(accion)) {
            Cuadernos dato = new Cuadernos();
            dato.setTipoconsulta("D");   //////getDatoTransferencia
            dato.setCod_esta(cliente.getCod_esta());
            dato.setId_laboratorio(Integer.parseInt(id_trans));
            List listaTrans = this.mi.getDatoTransferencia(dato);
            Cuadernos listat = (Cuadernos) listaTrans.get(0);
            modelo.put("listaTrans", listaTrans);
            modelo.put("Listat", listat);
            modelo.put("id_trans", id_trans);
            modelo.put("anio", Integer.toString(listat.getFecha().getYear() + 1900));
            modelo.put("mes", Integer.toString(listat.getFecha().getMonth() + 1));
            modelo.put("dia", Integer.toString(listat.getFecha().getDate()));
            modelo.put("hora", Integer.toString(listat.getFecha().getHours()));
            modelo.put("minuto", Integer.toString(listat.getFecha().getMinutes()));
            Localidades localidad = new Localidades();
            localidad.setArea("E");
            localidad.setCod_esta(listat.getCondon());
            Localidades buscarEstab = this.mi.getDatosEstable(localidad);
            modelo.put("id_departamento", Integer.toString(buscarEstab.getId_departamento()));

            localidad.setId_departamento(buscarEstab.getId_departamento());
            localidad.setId_persona(10);
            localidad.setArea("C");
            List Estab2 = this.mi.getEstabTransCns(localidad);  /////getEstabTransCns
            modelo.put("listaEstab", Estab2);
            return new ModelAndView("administrarcuadernos/TransferenciaMod", modelo);
        }

        if ("ImprimirTrans".equals(accion)) {
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
            Cuadernos datoll = new Cuadernos();
            datoll.setId_historial(Integer.parseInt(id_historial));
            datoll.setCod_esta(cliente.getCod_esta());
            List listalab = this.mi.getPacienteLaboratorio(datoll);
            modelo.put("listalab", listalab);

            modelo.put("dato", cliente);

            Cuadernos dato = new Cuadernos();
            dato.setTipoconsulta("D");   //////getDatoTransferencia
            dato.setCod_esta(cliente.getCod_esta());
            dato.setId_laboratorio(Integer.parseInt(id_trans));
            List listaTrans = this.mi.getDatoTransferencia(dato);
            modelo.put("listaTrans", listaTrans);
            Cuadernos datotra = (Cuadernos) listaTrans.get(0);
            Personas buscaResid = mi.getDatosPersonaInt(datotra.getId_por());
            modelo.put("emplResid", buscaResid);
            return new ModelAndView(new TransferenciaPDF(), modelo);
        }

        if ("CronogramaUCA".equals(accion)) {
            Date dFechaini1 = new Date();
            Date dFechafin1 = new Date();

            Historiales dato = new Historiales();
            dato.setCod_esta(200115);
            dato.setId_estado("%");
            dato.setId_historia(0);
            dato.setId_historial(2);
            dato.setId_persona(1369);
            dato.setId_localidad(1369);
            dato.setId_consultorio(23);
            dato.setId_departamento(23);
            dato.setAccion("2"); /////getListarReservacionesUcaLuo
            List listarPaises2 = this.mi.getListarReservacionesUcaLuo(dato);
            modelo.put("milista", listarPaises2);
            List listarSeguros = this.mi.getListarSeguros("A");
            modelo.put("listaPacAfi", listarSeguros);

            Cuadernos datofe = new Cuadernos();
            datofe.setTipoconsulta("1");   //////getListarFechas
            datofe.setCod_esta(cliente.getCod_esta());
            List listaFechas = this.mi.getListarFechas(datofe);
            modelo.put("listaFechas", listaFechas);
            datofe.setTipoconsulta("2");   //////getListarFechasCount
            datofe.setCod_esta(cliente.getCod_esta());
            List listaFechasCount = this.mi.getListarFechasCount(datofe);
            modelo.put("listaFechasCount", listaFechasCount);

            modelo.put("id_persona", "1369");
            return new ModelAndView("administrarpacientes/ListarReservasUcaLuo", modelo);
        }

        if ("Guardar".equals(accion) || "Modificar".equals(accion)) {
            String id_d = request.getParameter("id_departamento");
            String cod_esta = request.getParameter("cod_esta");
            String id_servicio = request.getParameter("id_servicio");
            String id_consultorio = request.getParameter("id_consultorio");
            String diai = request.getParameter("diai");
            String mesi = request.getParameter("mesi");
            String anioi = request.getParameter("anioi");
            String horai = request.getParameter("horai");
            String minutoi = request.getParameter("minutoi");
            String diagnostico = request.getParameter("diagnostico");
            String laboratorio = request.getParameter("laboratorio");
            String tratamiento = request.getParameter("tratamiento");
            String recomienda = request.getParameter("recomienda");
            Date fecha = new Date();

            int diax = Integer.parseInt(diai);
            int mesx = Integer.parseInt(mesi) - 1;
            int aniox = Integer.parseInt(anioi) - 1900;
            int horax = Integer.parseInt(horai);
            int minutox = Integer.parseInt(minutoi);
            Date fechai = new Date(aniox, mesx, diax, horax, minutox, 00);

            Personas buscarEmpleado2 = this.mi.getBuscarPersona(Integer.parseInt(id_persona));
            Consultorios datosconsul = this.mi.getDatosConsultorio(Integer.parseInt(id_consultorio));
            Localidades localidad = new Localidades();
            localidad.setArea("E");   ////getDatosEstable
            localidad.setCod_esta(Integer.parseInt(cod_esta));
            Localidades buscarestab = this.mi.getDatosEstable(localidad);

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
            Cuadernos datoll = new Cuadernos();
            datoll.setId_historial(Integer.parseInt(id_historial));
            datoll.setCod_esta(cliente.getCod_esta());
            List listalab = this.mi.getPacienteLaboratorio(datoll);
            modelo.put("listalab", listalab);
            modelo.put("dato", cliente);

            Cuadernos dato = new Cuadernos();
            dato.setCod_esta(cliente.getCod_esta());  ///16-01-2016
            dato.setFecha(fechai);
            dato.setId_departamento(Integer.parseInt(id_d));
            dato.setCondon(Integer.parseInt(cod_esta)); ///cod_esta_ref
            dato.setId_paciente(Integer.parseInt(id_paciente));
            dato.setId_historial(Integer.parseInt(id_historial));
            dato.setId_persona(cliente.getId_persona());
            if (cliente.getId_especialidad() == 99) {
                dato.setId_persona(Integer.parseInt(id_persona));
                dato.setId_consultorio(Integer.parseInt(id_consultorio));
            }
            dato.setId_cuaderno(Integer.parseInt(id_servicio));  ///servicio_ref
            dato.setId_cargo(Integer.parseInt(id_consultorio));  ///Consultorio_ref
            dato.setId_consultorio(cliente.getId_consultorio());
            if ("0".equals(id_servicio)) {
                dato.setAnti("CONSULTA EXTERNA");
            }
            if ("1".equals(id_servicio)) {
                dato.setAnti("EMERGENCIAS");
            }
            if ("2".equals(id_servicio)) {
                dato.setAnti("HOSPITALIZACION");
            }
            if ("3".equals(id_servicio)) {
                dato.setAnti("UTI");
            }
            if ("4".equals(id_servicio)) {
                dato.setAnti("UCIN");
            }
            if ("5".equals(id_servicio)) {
                dato.setAnti("OTRO");
            }
            dato.setBacterias(datosconsul.getConsultorio());
            dato.setBilirrubina(buscarestab.getEstablecimiento());
            dato.setDiagnostico(diagnostico);
            dato.setLaboratorio(laboratorio);
            dato.setAspecto(tratamiento);
            dato.setCetonas(recomienda);
            dato.setExpedido(datosHistorial.getExpedido());
            dato.setEdad(datosHistorial.getEdad());
            dato.setEmaties(cliente.getEstablecimiento());
            dato.setId_seguro(datosHistorial.getId_seguro());
            dato.setId_por(cliente.getId_persona());
            dato.setCod_esta(cliente.getCod_esta());
            dato.setTipoconsulta("T");  ////getVerTransferencia
            List listaTrans = mi.getVerTransferencia(dato);
            if ("Modificar".equals(accion)) {
                dato.setAccion("M");
                dato.setId_laboratorio(Integer.parseInt(id_trans));
                mi.setModificarTranferencia(dato);
            } else if (listaTrans.isEmpty()) {
                dato.setAccion("T");
                mi.setCrearTransferencia(dato);  ////setCrearTransferencia
            } else {
                return new ModelAndView("Aviso", "mensaje", "Ya existe una transferencia para este Paciente y consultorio");
            }
            dato.setTipoconsulta("T");
            List listaTrans2 = mi.getVerTransferencia(dato);
            modelo.put("listaTrans", listaTrans2);
            Cuadernos datotra = (Cuadernos) listaTrans2.get(0);
            Personas buscaResid = mi.getDatosPersonaInt(datotra.getId_por());
            modelo.put("emplResid", buscaResid);
            return new ModelAndView(new TransferenciaPDF(), modelo);

        }
        modelo.put("accion", accion);
        return new ModelAndView("administrarcuadernos/Transferencia", modelo);
    }

}
