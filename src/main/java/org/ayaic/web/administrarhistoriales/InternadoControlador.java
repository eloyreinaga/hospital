package org.ayaic.web.administrarhistoriales;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Date;
import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Consultorios;
import org.ayaic.domain.Cuadernos;
import org.ayaic.domain.Historiales;
import org.ayaic.domain.Localidades;
import org.ayaic.domain.Medicamentos;
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.Personas;
import org.ayaic.domain.Recetas;
import org.ayaic.domain.Sexos;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class InternadoControlador {

    private final MiFacade mi;

    public InternadoControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/InternarPaciente.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws UnknownHostException {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String accion1 = request.getParameter("accion1");
        String swinter = request.getParameter("swinter");
        String nombres = request.getParameter("nombres");
        String id_paciente = request.getParameter("id_paciente");
        String id_reservacion = request.getParameter("id_reservacion");
        String id_historial = request.getParameter("id_historial");
        String id_historia = request.getParameter("id_historia");
        String id_consultorio = request.getParameter("id_consultorio");
        String id_persona = request.getParameter("id_persona");
        String id_persona3 = request.getParameter("id_persona");
        String expedido = request.getParameter("expedido");
        String subjetivo = request.getParameter("subjetivo");
        String objetivo = request.getParameter("objetivo");
        String diagnostico = request.getParameter("diagnostico");
        String miaccion = request.getParameter("miaccion");
        String codigo = request.getParameter("codigo");
        String literal = request.getParameter("literal");
        String boton = request.getParameter("boton");
        String estimc = request.getParameter("estimc");
        String[] dias = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
        String[] meses = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        Date fecha1 = new Date();
        int anioq = fecha1.getYear() + 1900;
        String[] anios = {(Integer.toString(anioq)), (Integer.toString(anioq - 1))};

        String[] horas = {"00", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"};
        String[] minutos = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"};
        String[] aniosq = {(Integer.toString(anioq)), (Integer.toString(anioq - 1))};
        String ip = request.getRemoteAddr();
        String host = request.getRemoteHost();
        InetAddress ip2;
        ip2 = InetAddress.getLocalHost();

        Localidades local = new Localidades();
        local.setCod_esta(cliente.getCod_esta());  /////////////
        List Estab1 = this.mi.getListarEsta(local);
        Localidades datoestab = (Localidades) Estab1.get(0);

        local.setArea("E");   ////getDatosEstable        
        Localidades buscarestab = this.mi.getDatosEstable(local);
        modelo.put("datoestab", buscarestab);
        
        Medicamentos medid = new Medicamentos();
        medid.setCod_esta(cliente.getCod_esta());
        medid.setId_persona(Integer.parseInt(id_persona));
        List listarEnfermedadesCot = this.mi.getListarEnfermedadesCot(medid);
        modelo.put("listarEnfermedadesCot", listarEnfermedadesCot);
        modelo.put("nombres", nombres);

        Pacientes buscarPaciente = this.mi.getDatosPaciente(Integer.parseInt(id_paciente)); // saca un registro a ser modificado
        modelo.put("datos", buscarPaciente);
        modelo.put("id_paciente", id_paciente);
        modelo.put("dato", cliente);

        if (id_reservacion == null || "".equals(id_reservacion)) {
            id_reservacion = id_historial;
        }

        Historiales datohi = new Historiales();
        datohi.setId_historial(Integer.parseInt(id_reservacion));
        datohi.setCod_esta(cliente.getCod_esta());
        Historiales datosHistorial = this.mi.getDatosHistorial(datohi);
        modelo.put("datoshisto", datosHistorial);
        modelo.put("id_historia", id_historia);

        Consultorios datosconsulta = this.mi.getDatosConsultorio(Integer.parseInt(id_consultorio)); // saca un registro a ser modificado
        modelo.put("tipo_medico", Integer.toString(datosconsulta.getId_cargo()));

        String id_sexo_e = Integer.toString(buscarPaciente.getId_tipo_sexo());          //Sacar id_sexo

        Date fecha_nac = buscarPaciente.getFec_nacimiento();
        int xanio = fecha_nac.getYear() + 1900;
        int xmes = fecha_nac.getMonth() + 1;
        int xdia = fecha_nac.getDate();
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

        //Buscar Sexo
        Sexos buscarsexo = this.mi.getDatosSexo(Integer.parseInt(id_sexo_e)); // saca un registro a ser modificado
        modelo.put("buscarSexo", buscarsexo);

        String a = "/";
        String fecha_nacimiento = Integer.toString(xdia) + a + Integer.toString(xmes) + a + Integer.toString(xanio);
        modelo.put("fec_nacimiento", fecha_nacimiento);

        modelo.put("accion", accion);
        modelo.put("sw1", request.getParameter("sw1"));
        modelo.put("id_paciente", id_paciente);
        modelo.put("id_reservacion", id_reservacion);
        modelo.put("id_historial", id_reservacion);
        modelo.put("id_consultorio", id_consultorio);
        modelo.put("id_persona", id_persona);
        modelo.put("expedido", expedido);

        modelo.put("subjetivo", subjetivo);
        modelo.put("objetivo", objetivo);
        modelo.put("diagnostico", diagnostico);
        modelo.put("miaccion", miaccion);
        modelo.put("codigo", codigo);
        modelo.put("literal", literal);
        modelo.put("estimc", estimc);
        modelo.put("swinter", swinter);

        Historiales datoint = new Historiales();
        datoint.setId_paciente(Integer.parseInt(id_paciente));
        datoint.setId_historial(Integer.parseInt(id_reservacion));
        datoint.setCod_esta(cliente.getCod_esta());

        if ("GrabarModificacion".equals(accion)) {
            String talla = request.getParameter("talla");
            String peso = request.getParameter("peso");
            String fc = request.getParameter("fc");
            String pa = request.getParameter("pa");
            String fr = request.getParameter("fr");
            String diai = request.getParameter("diai");
            String mesi = request.getParameter("mesi");
            String anioi = request.getParameter("anioi");
            String horai = request.getParameter("horai");
            String minutoi = request.getParameter("minutoi");
            String temperatura = request.getParameter("temperatura");

            int diax = Integer.parseInt(diai);
            int mesx = Integer.parseInt(mesi) - 1;
            int aniox = Integer.parseInt(anioi) - 1900;
            int horax = Integer.parseInt(horai);
            int minutox = Integer.parseInt(minutoi);
            Date fechaa = new Date(aniox, mesx, diax, horax, minutox, 00);

            Historiales reserva = new Historiales();

            reserva.setId_historia(Integer.parseInt(id_historia));
            reserva.setId_historial(Integer.parseInt(id_reservacion));
            reserva.setId_consultorio(Integer.parseInt(id_consultorio));
            if (cliente.getId_consultorio() == 6) {
                reserva.setId_consultorio(cliente.getId_consultorio());///////2/05/2016 para las enfermeras
            }
            reserva.setId_persona(Integer.parseInt(id_persona));
            reserva.setCod_esta(cliente.getCod_esta());
            reserva.setFecha(fechaa);
            reserva.setDiagnostico(diagnostico);
            reserva.setTemperatura(Double.parseDouble(temperatura));
            reserva.setTalla(Double.parseDouble(talla));
            reserva.setPeso(Double.parseDouble(peso));
            reserva.setPa(pa);
            reserva.setFc(fc);
            reserva.setFr(fr);
            reserva.setId_por(cliente.getId_persona());
            reserva.setCadena1(host);
            reserva.setCadena2(ip2.getHostName());
            reserva.setAccion("F");    ///setModificarInterFecha  22-06-2015
            try {
                this.mi.setModificarInterFecha(reserva);
                List listasI = this.mi.getListarHistoriaI(datoint);
                modelo.put("milistaI", listasI);
                datoint.setAccion("M");
                List listasInter = this.mi.getHistoriaInterMedico(datoint);  ///getHistoriaInterMedico
                modelo.put("milistaInter", listasInter);
                datoint.setAccion("E");
                List listasInterEnf = this.mi.getHistoriaInterEnfer(datoint);///getHistoriaInterEnfer
                modelo.put("milistaInterEnf", listasInterEnf);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "El registro no se grabo");
            }
            return new ModelAndView("administrarhistoriales/HistoriaInternado", modelo);
        }

        if ("Editar".equals(accion)) {
            String talla = request.getParameter("talla");
            String peso = request.getParameter("peso");
            String temperatura = request.getParameter("temperatura");
            String fc = request.getParameter("fc");
            String pa = request.getParameter("pa");
            String fr = request.getParameter("fr");

            Historiales datoact2 = new Historiales();
            datoact2.setAccion("I");
            datoact2.setId_historia(Integer.parseInt(id_historia));
            datoact2.setCod_esta(cliente.getCod_esta());
            List listasIn2 = this.mi.getHistoriaInterIndi(datoact2);       ////getHistoriaInterIndi

            if (!listasIn2.isEmpty()) {
                Historiales datohis = (Historiales) listasIn2.get(0);
                modelo.put("datoshisto", datohis);

                if (datohis.getId_por() != cliente.getId_persona()) {
                    return new ModelAndView("Aviso", "mensaje", "No esta autorizado para modificar esta evolucion");
                }
                int xanio1 = datohis.getFecha().getYear() + 1900;
                int xmes1 = datohis.getFecha().getMonth() + 1;
                int xdia1 = datohis.getFecha().getDate();
                int xhora1 = datohis.getFecha().getHours();
                int xminuto1 = datohis.getFecha().getMinutes();
                modelo.put("anio", Integer.toString(xanio1));
                modelo.put("mes", Integer.toString(xmes1));
                modelo.put("dia", Integer.toString(xdia1));
                modelo.put("hora", Integer.toString(xhora1));
                modelo.put("minuto", Integer.toString(xminuto1));
                modelo.put("id_historia", Integer.toString(datohis.getId_historia()));
            }

            List listasI = this.mi.getListarHistoriaI(datoint);
            modelo.put("milistaI", listasI);
            datoint.setAccion("M");
            List listasInter = this.mi.getHistoriaInter(datoint);
            modelo.put("milistaInter", listasInter);
            datoint.setAccion("E");
            List listasInterEnf = this.mi.getHistoriaInterEnfer(datoint);///getHistoriaInterEnfer
            modelo.put("milistaInterEnf", listasInterEnf);
            modelo.put("editar", "editar");
            return new ModelAndView("administrarhistoriales/HistoriaInternado", modelo);
        }

        if ("Internado".equals(accion)) {
            List listasI = this.mi.getListarHistoriaI(datoint);
            modelo.put("milistaI", listasI);
            datoint.setAccion("M");
            List listasInter = this.mi.getHistoriaInterMedico(datoint);///getHistoriaInterMedico
            modelo.put("milistaInter", listasInter);

            datoint.setAccion("E");
            List listasInterEnf = this.mi.getHistoriaInterEnfer(datoint);///getHistoriaInterEnfer
            modelo.put("milistaInterEnf", listasInterEnf);

            if (!listasInter.isEmpty()) {
                if (listasInter.size() > 1) {
                    Historiales datohis = (Historiales) listasInter.get(listasInter.size() - 1);
                    datohis.setTalla(0);
                    datohis.setPeso(0);
                    datohis.setTemperatura(0);
                    datohis.setFc("0");
                    datohis.setFr("0");
                    datohis.setPa("0");
                    datohis.setDiagnostico("");
                    modelo.put("datoshisto", datohis);
                } else {
                    modelo.put("datoshisto", datosHistorial);
                }
            }
            return new ModelAndView("administrarhistoriales/HistoriaInternado", modelo);
        }

        if ("Imprimir Evoluciones".equals(accion1)) {
            Pacientes buscarPacienteh = this.mi.getDatosPaciente(Integer.parseInt(id_paciente)); // saca un registro a ser modificado
            modelo.put("datosp", buscarPacienteh);

            List listasI = this.mi.getListarHistoriaI(datoint);
            modelo.put("milistaI", listasI);
            List listasInter = this.mi.getHistoriaInter(datoint);   ////getHistoriaInter
            modelo.put("milistaInter", listasInter);

            return new ModelAndView(new HojaEvolucionTotPDF(), modelo);
        }

        if ("ImprimirEvolucionCabecera".equals(accion1) || "ImprimirEvolucionIndi".equals(accion1)) {
            String id_fila = request.getParameter("id_fila");

            Pacientes buscarPacienteh = this.mi.getDatosPaciente(Integer.parseInt(id_paciente)); // saca un registro a ser modificado
            modelo.put("datosp", buscarPacienteh);

            datoint.setAccion("I");
            datoint.setId_historia(Integer.parseInt(id_historia));
            datoint.setId_paciente(Integer.parseInt(id_paciente));
            datoint.setCod_esta(cliente.getCod_esta());
            List listasI = this.mi.getHistoriaInterIndi(datoint);   /////getListarHistoriaInterHoy
            Historiales datoHisInter = (Historiales) listasI.get(0);
            modelo.put("milistaInter", listasI);
            modelo.put("id_fila", id_fila);
            Personas persona = this.mi.getDatosPersona(datoHisInter.getId_persona()); // saca un registro a ser modificado
            modelo.put("persona", persona);
            Personas personares = this.mi.getDatosPersona(datoHisInter.getId_por()); // saca un registro a ser modificado
            modelo.put("personares", personares);

            Recetas midato = new Recetas();
            midato.setId_estado("H"); ///getListarRepRecetaCNS
            midato.setId_historia(Integer.parseInt(id_historia));
            midato.setCod_esta(cliente.getCod_esta());
            List listarRecetas = this.mi.getListarRepRecetaCNS(midato);
            modelo.put("listarReceta", listarRecetas);

            Cuadernos dato = new Cuadernos();
            dato.setTipo("A");
            dato.setId_historia(Integer.parseInt(id_historia));  ////getLabPendienteEpi solicitados con este id_historia
            dato.setCod_esta(cliente.getCod_esta());
            List listalab = this.mi.getLabPendienteEpi(dato);
            modelo.put("listalab", listalab);

            if ("ImprimirEvolucionCabecera".equals(accion1)) {
                return new ModelAndView(new HojaEvolucionTotPDF(), modelo);
            }
            if ("ImprimirEvolucionIndi".equals(accion1)) {
                return new ModelAndView(new HojaEvolucion2PDF(), modelo);
            }
        }

        if ("Imprimir Notas Enfermeria".equals(accion1)) {
            Pacientes buscarPacienteh = this.mi.getDatosPaciente(Integer.parseInt(id_paciente)); // saca un registro a ser modificado
            modelo.put("datosp", buscarPacienteh);

            datoint.setAccion("E");
            List listasI = this.mi.getListarHistoriaI(datoint);
            modelo.put("milistaI", listasI);
            List listasInter = this.mi.getHistoriaInterEnfer(datoint);   /////getHistoriaInterEnfer
            modelo.put("milistaInter", listasInter);

            return new ModelAndView(new HojaEnfermeriaPDF(), modelo);
        }

        if ("ImprimirNotaCabecera".equals(accion1) || "ImprimirNotaIndi".equals(accion1)) {
            String id_fila = request.getParameter("id_fila");

            Pacientes buscarPacienteh = this.mi.getDatosPaciente(Integer.parseInt(id_paciente)); // saca un registro a ser modificado
            modelo.put("datosp", buscarPacienteh);

            datoint.setAccion("I");
            datoint.setId_historia(Integer.parseInt(id_historia));
            datoint.setId_paciente(Integer.parseInt(id_paciente));
            datoint.setCod_esta(cliente.getCod_esta());
            List listasI = this.mi.getListarHistoriaInterHoy(datoint);   /////getListarHistoriaInterHoy
            Historiales datoHisInter = (Historiales) listasI.get(0);
            modelo.put("milistaInter", listasI);
            modelo.put("id_fila", id_fila);
            Personas persona = this.mi.getDatosPersona(datoHisInter.getId_persona()); // saca un registro a ser modificado
            modelo.put("persona", persona);
            Personas personares = this.mi.getDatosPersona(datoHisInter.getId_por()); // saca un registro a ser modificado
            modelo.put("personares", personares);

            if ("ImprimirNotaCabecera".equals(accion1)) { //////////para enfermeria
                return new ModelAndView(new HojaEnfermeriaPDF(), modelo);
            }
            if ("ImprimirNotaIndi".equals(accion1)) {
                return new ModelAndView(new HojaEvolucion2EnferPDF(), modelo);
            }
        }

        if ("HclInternado".equals(accion)) {
            List listasI = this.mi.getListarHistoriaI(datoint);
            modelo.put("milistaI", listasI);
            List listasInter = this.mi.getHistoriaInter(datoint);
            modelo.put("milistaInter", listasInter);

            return new ModelAndView("administrarhistoriales/HistoriaPacienteInter", modelo);
        }

        if ("Elegir".equals(accion)) {
            codigo = request.getParameter("ubicacion");
            literal = request.getParameter("concentra");
            modelo.put("codigo", codigo);
            modelo.put("literal", literal);
            modelo.put("terminar", "si");
            modelo.put("estimc", estimc);
            return new ModelAndView("administrarhistoriales/AtenderPaciente3", modelo);
        }

        if ("Buscar Enfermedad".equals(boton)) {
            List listarEnfermedades = this.mi.getListarEnfermedades(nombres);
            modelo.put("listarEnfermedades", listarEnfermedades);
            return new ModelAndView("administrarhistoriales/AtenderPaciente3", modelo);
        }

        if ("Buscar CIE10".equals(boton)) {
            List listarEnfermedades = this.mi.getListarEnfermedadesCod(nombres);
            modelo.put("listarEnfermedades", listarEnfermedades);
            modelo.put("estimc", estimc);
            return new ModelAndView("administrarhistoriales/AtenderPaciente3", modelo);
        }

        if ("Historial".equals(accion)) {
            Historiales dato = new Historiales();
            dato.setId_paciente(Integer.parseInt(id_paciente));
            dato.setCod_esta(cliente.getCod_esta());
            List listas = this.mi.getListarHistoria(dato);
            modelo.put("milista", listas);
            List listarSeguros = this.mi.getListarSeguros("A");
            modelo.put("listaPacAfi", listarSeguros);
            return new ModelAndView("administrarhistoriales/HistoriaPaciente", modelo);
        }

        if ("Laboratorio".equals(accion)) {
            Cuadernos dato = new Cuadernos();
            dato.setCod_esta(cliente.getCod_esta());  ///16-01-2016
            dato.setId_persona(Integer.parseInt(id_paciente));
            List listasLab = this.mi.getPacienteResultadoLab(dato);
            modelo.put("milista", listasLab);
            return new ModelAndView("administrarhistoriales/LaboratorioPaciente", modelo);
        }

        if ("Repetir Ultimos Datos".equals(accion)) {
            String talla = request.getParameter("talla");
            String peso = request.getParameter("peso");
            String temperatura = request.getParameter("temperatura");
            String fc = request.getParameter("fc");
            String pa = request.getParameter("pa");
            String fr = request.getParameter("fr");

            Historiales datoact2 = new Historiales();
            datoact2.setAccion("B");
            datoact2.setId_paciente(Integer.parseInt(id_paciente));
            datoact2.setId_historial(Integer.parseInt(id_reservacion));
            datoact2.setId_consultorio(cliente.getId_consultorio());
            if (cliente.getId_especialidad() == 99) {
                datoact2.setId_consultorio(Integer.parseInt(id_consultorio));
            }
            datoact2.setCod_esta(cliente.getCod_esta());
            List listasIn2 = this.mi.getUltHistoriaInter(datoact2);////getUltHistoriaInter

            if (!listasIn2.isEmpty()) {
                Historiales datohis = (Historiales) listasIn2.get(0);
                modelo.put("datoshisto", datohis);
            }

            List listasI = this.mi.getListarHistoriaI(datoint);
            modelo.put("milistaI", listasI);
            datoint.setAccion("M");
            List listasInter = this.mi.getHistoriaInter(datoint);
            modelo.put("milistaInter", listasInter);
            datoint.setAccion("E");
            List listasInterEnf = this.mi.getHistoriaInterEnfer(datoint);///getHistoriaInterEnfer
            modelo.put("milistaInterEnf", listasInterEnf);
            return new ModelAndView("administrarhistoriales/HistoriaInternado", modelo);
        }

        if ("Grabar Evolucion Internado".equals(accion)) {
            String talla = request.getParameter("talla");
            String peso = request.getParameter("peso");
            String temperatura = request.getParameter("temperatura");
            String fc = request.getParameter("fc");
            String pa = request.getParameter("pa");
            String fr = request.getParameter("fr");
            String id_cama = request.getParameter("id_cama");
            String id_sala = request.getParameter("id_sala");
            String id_piso = request.getParameter("id_piso");

            if (diagnostico.contains("Office") || diagnostico.contains("Word")) {
                return new ModelAndView("Aviso", "mensaje", "No puede realizar copias de Word o Excel");
            }

            Historiales reserva = new Historiales();
            reserva.setId_historial(Integer.parseInt(id_reservacion));
            reserva.setCod_esta(cliente.getCod_esta());
            reserva.setAccion("M");
            List listasInterna = this.mi.getHistoriaInterMedico(reserva);   /////getHistoriaInterMedico

            if (listasInterna.size() > 0) {   ////////////saca el ultimo historiainter y sus valores pero era mejor del ciuaderno5
                Historiales datoIntera = (Historiales) listasInterna.get(0);
                id_historia = Integer.toString(datoIntera.getId_historia());
            }

            Cuadernos datoc5 = new Cuadernos();
            datoc5.setId_historial(Integer.parseInt(id_reservacion));
            datoc5.setCod_esta(cliente.getCod_esta());
            List C5 = this.mi.getPacienteCuaderno5(datoc5);

            if (C5.size() > 0) {
                Cuadernos cuaderno5 = (Cuadernos) C5.get(0);
                id_persona = Integer.toString(cuaderno5.getId_persona());
                id_consultorio = Integer.toString(cuaderno5.getId_consultorio());
                id_cama = Integer.toString(cuaderno5.getId_cama());
                id_sala = Integer.toString(cuaderno5.getId_sala());
                id_piso = Integer.toString(cuaderno5.getId_piso());
                if ("0".equals(id_persona)) {
                    id_persona = id_persona3;//// lo llenaba en cero cuando cuaderno5 esta en 0
                }
            }

            reserva.setId_consultorio(Integer.parseInt(request.getParameter("id_consultorio")));
            reserva.setId_persona(cliente.getId_persona());
            reserva.setId_consultorio(cliente.getId_consultorio());
            if (cliente.getId_especialidad() == 99) {  /////15-09-2015
                reserva.setId_persona(Integer.parseInt(id_persona));
                reserva.setId_consultorio(Integer.parseInt(id_consultorio));
                //}if(cliente.getId_especialidad()!=99 && cliente.getId_persona()!=Integer.parseInt(id_persona) ){  /////5-01-2016
                //    Personas persona = this.mi.getDatosPersona(Integer.parseInt(id_persona));
                //    reserva.setId_persona(persona.getId_persona()); 
                //    reserva.setId_consultorio(persona.getId_consultorio()); 
            }

            reserva.setDiagnostico(diagnostico);
            reserva.setTemperatura(Double.parseDouble(temperatura));
            reserva.setTalla(Double.parseDouble(talla));
            reserva.setPeso(Double.parseDouble(peso));
            reserva.setPa(pa);
            reserva.setFc(fc);
            reserva.setFr(fr);
            reserva.setId_estado("A");
            reserva.setId_sala(Integer.parseInt(id_sala));
            reserva.setId_cama(Integer.parseInt(id_cama));
            reserva.setId_piso(Integer.parseInt(id_piso));
            reserva.setId_por(cliente.getId_persona());
            try {
                this.mi.setCrearInternado(reserva);
                List listasInter2 = this.mi.getHistoriaInter(reserva);
                Historiales datoin2 = (Historiales) listasInter2.get(listasInter2.size() - 1);
                modelo.put("id_historia", id_historia);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "El registro del historial internado no se grabo");
            }
            datosHistorial.setCod_esta(cliente.getCod_esta());
            datosHistorial.setId_por(cliente.getId_persona());
            datosHistorial.setSo2("0");
            datosHistorial.setGlicemia("0");
            datosHistorial.setFechalab(fecha1);

            int iResultado = this.mi.setRegistrarHistorial(datosHistorial);

            Consultorios datosconsul = this.mi.getDatosConsultorio(cliente.getId_consultorio()); // saca un registro a ser modificado
            modelo.put("tipo_medico", Integer.toString(datosconsul.getId_cargo()));

            return new ModelAndView("administrarhistoriales/PlanAccionPacienteI", modelo);
        }
        return new ModelAndView("administrarhistoriales/AtenderPaciente", modelo);

    }
}
