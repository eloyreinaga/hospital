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
import org.ayaic.domain.Quirofanos;
import org.ayaic.domain.Salas;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProtocoloOperaControlador {

    private final MiFacade mi;

    public ProtocoloOperaControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/ProtocoloOpera.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();
        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String id_persona = request.getParameter("id_persona");
        String id_paciente = request.getParameter("id_paciente");
        String id_historial = request.getParameter("id_historial");
        String id_consultorio = request.getParameter("id_consultorio");
        String swinter = request.getParameter("swinter");
        String diagnosticopost = request.getParameter("diagnosticopost");
        String riesgo = request.getParameter("riesgo");
        String anestecia = request.getParameter("anestecia");
        String instrumental = request.getParameter("instrumental");
        String guantes = request.getParameter("guantes");
        String cirujano = request.getParameter("cirujano");
        String instrumentador = request.getParameter("instrumentador");
        String ayudante = request.getParameter("ayudante");
        String circulante = request.getParameter("circulante");
        String ayudante2 = request.getParameter("ayudante2");
        String anestecista = request.getParameter("anestecista");
        String ayudante3 = request.getParameter("ayudante3");
        String ayudantea = request.getParameter("ayudantea");
        String material = request.getParameter("material");
        String insicion = request.getParameter("insicion");
        String cierre = request.getParameter("cierre");
        String quien = request.getParameter("quien");
        String nombretecnico = request.getParameter("nombretecnico");
        String duracion = request.getParameter("duracion");
        String sangre = request.getParameter("sangre");
        String plasma = request.getParameter("plasma");
        String suero = request.getParameter("suero");
        String otros = request.getParameter("otros");
        String drenaje = request.getParameter("drenaje");
        String hallasgo = request.getParameter("hallasgo");
        String diagnostico = request.getParameter("diagnostico");
        String descripcion = request.getParameter("descripcion");

        String id_piso = request.getParameter("id_piso");
        String id_sala = request.getParameter("id_sala");
        String id_cama = request.getParameter("id_cama");
        String id_opera = request.getParameter("id_opera");
        String id_quirofano = request.getParameter("id_quirofano");
        String[] dias = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
        String[] meses = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        String[] horas = {"00", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"};
        String[] minutos = {"00", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"};
        Date fecha1 = new Date();
        int anioq = fecha1.getYear() + 1900;
        String[] anios = {Integer.toString(anioq), Integer.toString(anioq + 1),};

        if (id_persona == null || "".equals(id_persona)) {
            id_persona = "0";
        }
        if (id_consultorio == null || "".equals(id_consultorio)) {
            id_consultorio = "0";
        }

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
        modelo.put("id_opera", id_opera);
        modelo.put("id_paciente", id_paciente);
        modelo.put("id_persona", id_persona);
        modelo.put("id_historial", id_historial);
        modelo.put("id_reservacion", id_historial);
        modelo.put("id_quirofano", id_quirofano);
        modelo.put("diagnostico", diagnostico);
        modelo.put("diagnosticopost", diagnosticopost);
        modelo.put("riesgo", riesgo);
        modelo.put("anestecia", anestecia);
        modelo.put("instrumental", instrumental);
        modelo.put("guantes", guantes);
        modelo.put("cirujano", cirujano);
        modelo.put("instrumentador", instrumentador);
        modelo.put("ayudante", ayudante);
        modelo.put("circulante", circulante);
        modelo.put("ayudante2", ayudante2);
        modelo.put("anestecista", anestecista);
        modelo.put("ayudante3", ayudante3);
        modelo.put("ayudantea", ayudantea);
        modelo.put("material", material);
        modelo.put("insicion", insicion);
        modelo.put("cierre", cierre);
        modelo.put("quien", quien);
        modelo.put("drenaje", drenaje);
        modelo.put("hallasgo", hallasgo);
        modelo.put("diagnostico", diagnostico);
        modelo.put("descripcion", descripcion);
        modelo.put("nombretecnico", nombretecnico);
        modelo.put("duracion", duracion);
        modelo.put("sangre", sangre);
        modelo.put("plasma", plasma);
        modelo.put("suero", suero);
        modelo.put("otros", otros);
        modelo.put("swinter", swinter);

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
        modelo.put("tipo_medico", Integer.toString(cliente.getId_cargo()));
        modelo.put("expedido", datosHistorial.getExpedido());
        List listarSeguros = mi.getListarSeguros("A");
        modelo.put("listarSeguros", listarSeguros);

        Quirofanos datoqui = new Quirofanos();
        datoqui.setCod_esta(cliente.getCod_esta());
        List listarQuirofanos = this.mi.getListarQuirofanos(datoqui);
        modelo.put("listarQuirofanos", listarQuirofanos);

        Cuadernos datot = new Cuadernos();
        datot.setId_historial(Integer.parseInt(id_historial));
        datot.setCod_esta(cliente.getCod_esta());
        List C5 = this.mi.getPacienteCuaderno5(datot);

        datot.setTipoconsulta("R");  ////getListarProtocolos todos los procolos del medico tratante
        datot.setCod_esta(cliente.getCod_esta());
        datot.setId_persona(cliente.getId_persona());
        datot.setId_por(cliente.getId_persona());
        datot.setId_paciente(Integer.parseInt(id_paciente));
        datot.setId_historial(Integer.parseInt(id_historial));
        List listarProt = mi.getListarProtocolos(datot);
        modelo.put("listarProt", listarProt);
        datot.setTipoconsulta("I");   ////getVerProtocolos solo el protocolo del paciente en cuestion
        List listaPro = mi.getVerProtocolos(datot);

        if (id_piso == null) {
            if (listaPro.isEmpty()) {
                modelo.put("diagnostico", datosHistorial.getDiagnostico());
                if (C5.size() > 0) {
                    Cuadernos cuaderno5 = (Cuadernos) C5.get(0);
                    id_persona = Integer.toString(cuaderno5.getId_persona());
                    id_consultorio = Integer.toString(cuaderno5.getId_consultorio());
                    id_cama = Integer.toString(cuaderno5.getId_cama());
                    id_sala = Integer.toString(cuaderno5.getId_sala());
                    id_piso = Integer.toString(cuaderno5.getId_piso());
                    modelo.put("guantes", "1");
                    modelo.put("riesgo", "1");
                    modelo.put("anestecia", "1");
                    modelo.put("instrumental", "1");
                }
            } else {
                Cuadernos listPr = (Cuadernos) listaPro.get(0);
                datot.setId_persona(listPr.getId_persona());
            }
        } else {
            if (C5.size() > 0 && cliente.getId_especialidad() == 99 && !"0".equals(id_consultorio)) {
                Cuadernos cuaderno5 = (Cuadernos) C5.get(0);
                id_persona = Integer.toString(cuaderno5.getId_persona());
                id_consultorio = Integer.toString(cuaderno5.getId_consultorio());
                id_cama = Integer.toString(cuaderno5.getId_cama());
                id_sala = Integer.toString(cuaderno5.getId_sala());
                id_piso = Integer.toString(cuaderno5.getId_piso());
                modelo.put("guantes", "1");
                modelo.put("riesgo", "1");
                modelo.put("anestecia", "1");
                modelo.put("instrumental", "1");
            }

        }

        //Cuando se eligio n departamento
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

        if ("ModificaProt".equals(accion)) {
            Cuadernos dato = new Cuadernos();
            dato.setTipoconsulta("S");
            dato.setCod_esta(cliente.getCod_esta());
            dato.setId_laboratorio(Integer.parseInt(id_opera));
            dato.setId_historial(Integer.parseInt(id_historial));
            List listaProt = mi.getDatoProtocolo(dato);
            Cuadernos listPr = (Cuadernos) listaProt.get(0);   ////getDatoProtocolo
            modelo.put("anio", Integer.toString(listPr.getFecha().getYear() + 1900));
            modelo.put("mes", Integer.toString(listPr.getFecha().getMonth() + 1));
            modelo.put("dia", Integer.toString(listPr.getFecha().getDate()));
            modelo.put("hora", Integer.toString(listPr.getFecha().getHours()));
            modelo.put("minuto", Integer.toString(listPr.getFecha().getMinutes()));
            modelo.put("diagnosticopost", listPr.getCadena1());
            modelo.put("cirujano", listPr.getCadena2());
            modelo.put("instrumentador", listPr.getCadena6());
            modelo.put("ayudante", listPr.getCadena3());
            modelo.put("ayudante2", listPr.getCadena4());
            modelo.put("ayudante3", listPr.getCadena5());
            modelo.put("circulante", listPr.getCadena7());
            modelo.put("anestecista", listPr.getCadena8());
            modelo.put("ayudantea", listPr.getCadena9());
            modelo.put("material", listPr.getCadena10());
            modelo.put("insicion", listPr.getCadena11());
            modelo.put("cierre", listPr.getCadena12());
            modelo.put("quien", listPr.getCadena13());
            modelo.put("drenaje", listPr.getCadena14());
            modelo.put("hallasgo", listPr.getCadena15());
            modelo.put("diagnostico", listPr.getCadena16());
            modelo.put("descripcion", listPr.getCadena17());
            modelo.put("nombretecnico", listPr.getCadena18());
            modelo.put("duracion", listPr.getCadena19());
            modelo.put("sangre", listPr.getCadena20());
            modelo.put("plasma", listPr.getCadena21());
            modelo.put("suero", listPr.getCadena22());
            modelo.put("otros", listPr.getCadena23());
            modelo.put("riesgo", Integer.toString(listPr.getSuma2()));
            modelo.put("anestecia", Integer.toString(listPr.getSuma3()));
            modelo.put("instrumental", Integer.toString(listPr.getSuma4()));
            modelo.put("guantes", Integer.toString(listPr.getSuma5()));
            modelo.put("id_piso", Integer.toString(listPr.getId_piso()));
            modelo.put("id_sala", Integer.toString(listPr.getId_sala()));
            modelo.put("id_cama", Integer.toString(listPr.getId_cama()));
            modelo.put("id_quirofano", Integer.toString(listPr.getSuma1()));
            modelo.put("id_opera", id_opera);
            dsala.setId_piso(listPr.getId_piso());
            List listarSalas2 = mi.getListarSalasLibres(dsala);
            modelo.put("listarSalas", listarSalas2);
            Camas lcama = new Camas();
            lcama.setTipo("T");
            lcama.setId_sala(listPr.getId_sala());
            List listarCama = mi.getListarCamas(lcama);
            modelo.put("listarCama", listarCama);

            datot.setCod_esta(cliente.getCod_esta());
            datot.setId_persona(cliente.getId_persona());
            datot.setTipoconsulta("R");////R
            List listarPro = mi.getListarProtocolos(datot);  ////getListarProtocolos
            modelo.put("listarProt", listarPro);
            return new ModelAndView("administrarcuadernos/ProtocoloOpera", modelo);
        }

        if ("ImprimirProt".equals(accion)) {

            modelo.put("dato", cliente);
            Cuadernos dato = new Cuadernos();
            dato.setTipoconsulta("S");  ////getDatoProtocolo
            dato.setCod_esta(cliente.getCod_esta());
            dato.setId_laboratorio(Integer.parseInt(id_opera));
            dato.setId_historial(Integer.parseInt(id_historial));
            List listaProt = mi.getDatoProtocolo(dato);
            Cuadernos datopr = (Cuadernos) listaProt.get(0);
            modelo.put("listaProt", listaProt);
            Camas buscarCama = mi.getDatosCama(datopr.getId_cama());
            modelo.put("buscarCama", buscarCama);
            Quirofanos datoquir = new Quirofanos();
            datoquir.setId_quirofano(datopr.getSuma1());
            Quirofanos buscarquirofano = this.mi.getDatosQuirofano(datoquir);
            modelo.put("buscarquirofano", buscarquirofano);
            Personas buscarEmpleadoi = mi.getDatosPersonaInt(datopr.getId_persona());
            modelo.put("empleado", buscarEmpleadoi);
            Personas buscaResid = mi.getDatosPersonaInt(datopr.getId_por());
            modelo.put("emplResid", buscaResid);
            return new ModelAndView(new ProtocoloOperaPDF(), modelo);
        }

        if ("Guardar".equals(accion)) {
            String cod_esta = request.getParameter("cod_esta");
            String diai = request.getParameter("diai");
            String mesi = request.getParameter("mesi");
            String anioi = request.getParameter("anioi");
            String horai = request.getParameter("horai");
            String minutoi = request.getParameter("minutoi");

            Date fecha = new Date();
            int diax = Integer.parseInt(diai);
            int mesx = Integer.parseInt(mesi) - 1;
            int aniox = Integer.parseInt(anioi) - 1900;
            int horax = Integer.parseInt(horai);
            int minutox = Integer.parseInt(minutoi);
            Date fechai = new Date(aniox, mesx, diax, horax, minutox, 0);

            Personas buscarEmpleado2 = mi.getBuscarPersona(Integer.parseInt(id_persona));
            Consultorios c = mi.getDatosConsultorio(cliente.getId_consultorio());
            modelo.put("dato", cliente);

            Cuadernos dato = new Cuadernos();
            dato.setCod_esta(cliente.getCod_esta());  ///16-01-2016
            dato.setFecha(fechai);
            dato.setId_paciente(Integer.parseInt(id_paciente));
            dato.setId_historial(Integer.parseInt(id_historial));
            dato.setId_persona(cliente.getId_persona());
            dato.setId_consultorio(cliente.getId_consultorio());
            if (cliente.getId_especialidad() == 99) {
                dato.setId_persona(Integer.parseInt(id_persona));
                dato.setId_consultorio(Integer.parseInt(id_consultorio));
            }
            dato.setId_piso(Integer.parseInt(id_piso));
            dato.setId_sala(Integer.parseInt(id_sala));
            dato.setId_cama(Integer.parseInt(id_cama));
            dato.setSuma1(Integer.parseInt(id_quirofano));
            dato.setSuma2(Integer.parseInt(riesgo));
            dato.setSuma3(Integer.parseInt(anestecia));
            dato.setSuma4(Integer.parseInt(instrumental));
            dato.setSuma5(Integer.parseInt(guantes));
            dato.setSuma6(cliente.getId_persona());//////////////maL
            dato.setBacterias(c.getConsultorio());
            dato.setCadena1(diagnosticopost);
            dato.setCadena2(cirujano);
            dato.setCadena3(instrumentador);
            dato.setCadena4(ayudante);
            dato.setCadena5(ayudante2);
            dato.setCadena6(ayudante3);
            dato.setCadena7(circulante);
            dato.setCadena8(anestecista);
            dato.setCadena9(ayudantea);
            dato.setCadena10(material);
            dato.setCadena11(insicion);
            dato.setCadena12(cierre);
            dato.setCadena13(quien);
            dato.setCadena14(drenaje);
            dato.setCadena15(hallasgo);
            dato.setCadena16(diagnostico);
            dato.setCadena17(descripcion);
            dato.setCadena18(nombretecnico);
            dato.setCadena19(duracion);
            dato.setCadena20(sangre);
            dato.setCadena21(plasma);
            dato.setCadena22(suero);
            dato.setCadena23(otros);
            dato.setId_seguro(datosHistorial.getId_seguro());
            dato.setCod_esta(cliente.getCod_esta());
            dato.setTipoconsulta("I");
            List listaAdmi = mi.getVerProtocolos(dato);
            if (listaAdmi.isEmpty()) {
                dato.setAccion("P");
                mi.setCrearProtocolo(dato);
            } else {
                try {
                    dato.setId_laboratorio(Integer.parseInt(id_opera));
                    mi.setModificarPrococolos(dato);   ////setModificarPrococolos
                } catch (Exception e) {
                    return new ModelAndView("Aviso", "mensaje", "Consulte Administrador Protocolo Operatorio");
                }
            }
            dato.setTipoconsulta("I");
            List listaProt2 = mi.getVerProtocolos(dato);
            Cuadernos datoad = (Cuadernos) listaProt2.get(0);
            modelo.put("listarProt", listaProt2);
        }
        datot.setTipoconsulta("R");////
        datot.setCod_esta(cliente.getCod_esta());
        datot.setId_persona(cliente.getId_persona());
        List listarPro = mi.getListarProtocolos(datot);  ////getListarProtocolos
        modelo.put("listarProt", listarPro);
        modelo.put("accion", accion);

        return new ModelAndView("administrarcuadernos/ProtocoloOpera", modelo);
    }

}
