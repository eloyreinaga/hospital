package org.ayaic.web.administrarpacientes;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Carpetas;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Consultorios;
import org.ayaic.domain.Cuadernos;
import org.ayaic.domain.Localidades;
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.Personas;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ListarVigenciaAtControlador {

    private final MiFacade mi;

    public ListarVigenciaAtControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/ListarVigenciaAt.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String accion1 = request.getParameter("accion1");
        String boton = request.getParameter("boton");
        String id_consultorio = request.getParameter("id_consultorio");
        String id_persona = request.getParameter("id_persona");
        String id_paciente = request.getParameter("id_paciente");
        String id_reservacion = request.getParameter("id_reservacion");
        String id_historial = request.getParameter("id_historial");
        String codigo = request.getParameter("codigo");
        String[] dias = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
        String[] meses = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        String[] horas = {"00", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"};
        String[] minutos = {"00", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"};
        Date fecha1 = new Date();
        int anioq = fecha1.getYear() + 1900;
        String[] anios = {Integer.toString(anioq), Integer.toString(anioq + 1),};

        Localidades local = new Localidades();
        List Estab1 = this.mi.getListarEsta(local);
        Localidades datoestab = (Localidades) Estab1.get(0);
        local.setArea("R");
        local.setId_persona(1);
        List EstabRef = this.mi.getListarEstabRef(local);
        modelo.put("listarestab", EstabRef);
        modelo.put("datoestab", datoestab);

        if ("Configurar".equals(accion)) {
            Cuadernos dato = new Cuadernos();
            dato.setTipo_egreso(20);  ///getConfigurarImpresionRiesgo
            List datoriesgo = this.mi.getConfigurarImpresionRiesgo(dato);
            modelo.put("datoriesgo", datoriesgo);
            modelo.put("tipoimpresion", "20");
            modelo.put("tipod", "RIESGOS EXTRAORDINARIOS");
            return new ModelAndView("administrarpacientes/AdmImpresionVig", modelo);
        }

        if ("Mora".equals(accion) || "Accidente".equals(accion) || "SinDocumen".equals(accion) || "Riesgos".equals(accion)) {
            Cuadernos dato = new Cuadernos();

            if ("Mora".equals(accion)) {
                modelo.put("tipod", "EMPRESAS EN MORA");
                modelo.put("tipoimpresion", "21");
                dato.setBcg(21);
            }
            if ("Accidente".equals(accion)) {
                modelo.put("tipod", "ACCIDENTES DE TRABAJO");
                modelo.put("tipoimpresion", "22");
                dato.setBcg(22);
            }
            if ("SinDocumen".equals(accion)) {
                modelo.put("tipod", "PACIENTES SIN DOCUMENTOS");
                modelo.put("tipoimpresion", "23");
                dato.setBcg(23);
            }
            if ("Riesgos".equals(accion)) {
                modelo.put("tipod", "RIESGOS EXTRAORDINARIOS");
                modelo.put("tipoimpresion", "20");
                dato.setBcg(20);
            }
            dato.setTipo_egreso(20);
            List datoriesgo = this.mi.getConfigurarImpresionRiesgo(dato);
            modelo.put("datoriesgo", datoriesgo);
            return new ModelAndView("administrarpacientes/AdmImpresionVig", modelo);
        }

        if ("BuscarVig".equals(boton)) {
            String id_vigencia = request.getParameter("id_vigencia");
            String nombres = request.getParameter("nombre");
            nombres = nombres.replaceAll("\\s", "%");
            nombres = "%" + nombres + "%";
            Pacientes listavig = new Pacientes();
            listavig.setTipo("B");
            listavig.setNombres(nombres);
            listavig.setCod_esta(cliente.getCod_esta());
            listavig.setVeces(1);
            listavig.setDial(100);
            List listarVigencia = this.mi.getListarPacientesHC(listavig);
            modelo.put("listaVigencia", listarVigencia);
            return new ModelAndView("administrarpacientes/ListarDetVigencia", modelo);
        }

        if ("Guardar".equals(boton)) {
            String tipoimpresion = request.getParameter("tipoimpresion");
            String senior_x = request.getParameter("senior_x");
            String senior_y = request.getParameter("senior_y");
            String senior_t = request.getParameter("senior_t");
            String fecha_x = request.getParameter("fecha_x");
            String fecha_y = request.getParameter("fecha_y");
            String fecha_t = request.getParameter("fecha_t");
            String nombres_x = request.getParameter("nombres_x");
            String nombres_y = request.getParameter("nombres_y");
            String nombres_t = request.getParameter("nombres_t");
            String matricula_x = request.getParameter("matricula_x");
            String matricula_y = request.getParameter("matricula_y");
            String matricula_t = request.getParameter("matricula_t");
            String benefic_x = request.getParameter("benefic_x");
            String benefic_y = request.getParameter("benefic_y");
            String benefic_t = request.getParameter("benefic_t");
            String codigo_x = request.getParameter("codigo_x");
            String codigo_y = request.getParameter("codigo_y");
            String codigo_t = request.getParameter("codigo_t");
            String empresa_x = request.getParameter("empresa_x");
            String empresa_y = request.getParameter("empresa_y");
            String empresa_t = request.getParameter("empresa_t");
            String patronal_x = request.getParameter("patronal_x");
            String patronal_y = request.getParameter("patronal_y");
            String patronal_t = request.getParameter("patronal_t");
            String poli_x = request.getParameter("poli_x");
            String poli_y = request.getParameter("poli_y");
            String poli_t = request.getParameter("poli_t");
            String riesgo_x = request.getParameter("riesgo_x");
            String riesgo_y = request.getParameter("riesgo_y");
            String riesgo_t = request.getParameter("riesgo_t");
            String zona_x = request.getParameter("zona_x");
            String zona_y = request.getParameter("zona_y");
            String zona_t = request.getParameter("zona_t");
            String yo_x = request.getParameter("yo_x");
            String yo_y = request.getParameter("yo_y");
            String yo_t = request.getParameter("yo_t");
            String ci_x = request.getParameter("ci_x");
            String ci_y = request.getParameter("ci_y");
            String ci_t = request.getParameter("ci_t");
            String zona2_x = request.getParameter("zona2_x");
            String zona2_y = request.getParameter("zona2_y");
            String zona2_t = request.getParameter("zona2_t");
            String calleg_x = request.getParameter("calleg_x");
            String calleg_y = request.getParameter("calleg_y");
            String calleg_t = request.getParameter("calleg_t");
            String telefg_x = request.getParameter("telefg_x");
            String telefg_y = request.getParameter("telefg_y");
            String telefg_t = request.getParameter("telefg_t");
            String atendido_x = request.getParameter("atendido_x");
            String atendido_y = request.getParameter("atendido_y");
            String atendido_t = request.getParameter("atendido_t");
            String trasfer_x = request.getParameter("trasfer_x");
            String trasfer_y = request.getParameter("trasfer_y");
            String trasfer_t = request.getParameter("trasfer_t");
            String internado_x = request.getParameter("internado_x");
            String internado_y = request.getParameter("internado_y");
            String internado_t = request.getParameter("internado_t");
            String picasa_x = request.getParameter("picasa_x");
            String picasa_y = request.getParameter("picasa_y");
            String picasa_t = request.getParameter("picasa_t");
            String fechdoc_x = request.getParameter("fechdoc_x");
            String fechdoc_y = request.getParameter("fechdoc_y");
            String fechdoc_t = request.getParameter("fechdoc_t");
            String nume_x = request.getParameter("nume_x");
            String nume_y = request.getParameter("nume_y");
            String nume_t = request.getParameter("nume_t");

            Cuadernos dato = new Cuadernos();
            dato.setSuma1(Integer.parseInt(senior_x));
            dato.setSuma2(Integer.parseInt(senior_y));
            dato.setSuma3(Integer.parseInt(senior_t));
            dato.setSuma4(Integer.parseInt(fecha_x));
            dato.setSuma5(Integer.parseInt(fecha_y));
            dato.setSuma6(Integer.parseInt(fecha_t));
            dato.setSuma7(Integer.parseInt(nombres_x));
            dato.setSuma8(Integer.parseInt(nombres_y));
            dato.setSuma9(Integer.parseInt(nombres_t));
            dato.setSuma10(Integer.parseInt(matricula_x));
            dato.setSuma11(Integer.parseInt(matricula_y));
            dato.setSuma12(Integer.parseInt(matricula_t));
            dato.setSuma13(Integer.parseInt(benefic_x));
            dato.setSuma14(Integer.parseInt(benefic_y));
            dato.setSuma15(Integer.parseInt(benefic_t));
            dato.setSuma16(Integer.parseInt(codigo_x));
            dato.setSuma17(Integer.parseInt(codigo_y));
            dato.setSuma18(Integer.parseInt(codigo_t));
            dato.setSuma19(Integer.parseInt(empresa_x));
            dato.setSuma20(Integer.parseInt(empresa_y));
            dato.setSuma21(Integer.parseInt(empresa_t));
            dato.setSuma22(Integer.parseInt(patronal_x));
            dato.setSuma23(Integer.parseInt(patronal_y));
            dato.setSuma24(Integer.parseInt(patronal_t));
            dato.setSuma25(Integer.parseInt(poli_x));
            dato.setSuma26(Integer.parseInt(poli_y));
            dato.setSuma27(Integer.parseInt(poli_t));
            dato.setSuma28(Integer.parseInt(riesgo_x));
            dato.setSuma29(Integer.parseInt(riesgo_y));
            dato.setSuma30(Integer.parseInt(riesgo_t));
            dato.setSuma31(Integer.parseInt(zona_x));
            dato.setSuma32(Integer.parseInt(zona_y));
            dato.setSuma33(Integer.parseInt(zona_t));
            dato.setSuma34(Integer.parseInt(yo_x));
            dato.setSuma35(Integer.parseInt(yo_y));
            dato.setSuma36(Integer.parseInt(yo_t));
            dato.setSuma37(Integer.parseInt(ci_x));
            dato.setSuma38(Integer.parseInt(ci_y));
            dato.setSuma39(Integer.parseInt(ci_t));
            dato.setSuma40(Integer.parseInt(zona2_x));
            dato.setSuma41(Integer.parseInt(zona2_y));
            dato.setSuma42(Integer.parseInt(zona2_t));
            dato.setSuma43(Integer.parseInt(calleg_x));
            dato.setSuma44(Integer.parseInt(calleg_y));
            dato.setSuma45(Integer.parseInt(calleg_t));
            dato.setSuma46(Integer.parseInt(telefg_x));
            dato.setSuma47(Integer.parseInt(telefg_y));
            dato.setSuma48(Integer.parseInt(telefg_t));
            dato.setSuma49(Integer.parseInt(atendido_x));
            dato.setSuma50(Integer.parseInt(atendido_y));
            dato.setSuma51(Integer.parseInt(atendido_t));
            dato.setSuma52(Integer.parseInt(trasfer_x));
            dato.setSuma53(Integer.parseInt(trasfer_y));
            dato.setSuma54(Integer.parseInt(trasfer_t));
            dato.setSuma55(Integer.parseInt(internado_x));
            dato.setSuma56(Integer.parseInt(internado_y));
            dato.setSuma57(Integer.parseInt(internado_t));
            dato.setSuma58(Integer.parseInt(picasa_x));
            dato.setSuma59(Integer.parseInt(picasa_y));
            dato.setSuma60(Integer.parseInt(picasa_t));
            dato.setSuma61(Integer.parseInt(fechdoc_x));
            dato.setSuma62(Integer.parseInt(fechdoc_y));
            dato.setSuma63(Integer.parseInt(fechdoc_t));
            dato.setSuma64(Integer.parseInt(nume_x));
            dato.setSuma65(Integer.parseInt(nume_y));
            dato.setSuma66(Integer.parseInt(nume_t));

            dato.setAccion("R");     ///setModificarImpRiesgo
            dato.setBcg(Integer.parseInt(tipoimpresion));
            this.mi.setModificarImpRiesgo(dato);

            return new ModelAndView("Aviso", "mensaje", "Se grabo correctamente");
        }

        if ("ModificarDocumento".equals(accion) || "ModificarDocumentoA".equals(accion) || "ModificarDocumentoR".equals(accion) || "ModificarDocumentoM".equals(accion)) {
            String id_vigencia = request.getParameter("id_vigencia");
            String nombres = request.getParameter("nombres");
            String matricula = request.getParameter("matricula");
            String carnet = request.getParameter("carnet");
            String numvig = request.getParameter("num");
            String otros = request.getParameter("otros");
            String beneficiario = request.getParameter("beneficiario");
            //String residencia      = request.getParameter("residencia");
            String observacion = request.getParameter("observacion");
            String empresa = request.getParameter("empresa");
            String codigop = request.getParameter("codigop");
            String patronal = request.getParameter("patronal");
            String nropac = request.getParameter("nropac");
            String policlinico = request.getParameter("policlinico");
            String diagnostico = request.getParameter("diagnostico");
            String consultorio = request.getParameter("consultorio");
            String transferencia = request.getParameter("transferencia");
            String id_hospital = request.getParameter("id_hospital");
            String servicio = request.getParameter("servicio");
            String hospital = request.getParameter("hospital");
            String zona = request.getParameter("zona");
            String telefono = request.getParameter("telefono");
            String calle = request.getParameter("calle");
            String documento = request.getParameter("documento");
            String nombreg = request.getParameter("nombreg");
            String matriculag = request.getParameter("matriculag");
            String empresag = request.getParameter("empresag");
            String patronalg = request.getParameter("patronalg");
            String zonag = request.getParameter("zonag");
            String calleg = request.getParameter("calleg");
            String nrog = request.getParameter("nrog");
            String telefonog = request.getParameter("telefonog");
            String cig = request.getParameter("cig");
            String diai = request.getParameter("diai");
            String mesi = request.getParameter("mesi");
            String anioi = request.getParameter("anioi");
            String horai = request.getParameter("horai");
            String minutoi = request.getParameter("minutoi");
            String interna = request.getParameter("interna");
            String piso = request.getParameter("piso");
            String sala = request.getParameter("sala");
            String cama = request.getParameter("cama");

            Pacientes buscarPaciente = this.mi.getDatosPaciente(Integer.parseInt(id_paciente)); // saca un registro a ser modificado

            if ("".equals(diagnostico) || diagnostico == null) {
                diagnostico = "";
            }
            if ("".equals(hospital) || hospital == null) {
                hospital = "";
            }
            if ("".equals(id_hospital) || id_hospital == null) {
                id_hospital = "0";
            }
            if ("".equals(matriculag) || matriculag == null) {
                matriculag = "";
            }
            if ("".equals(empresag) || empresag == null) {
                empresag = "";
            }
            if ("".equals(patronalg) || patronalg == null) {
                patronalg = "";
            }
            if ("".equals(nombreg) || nombreg == null) {
                nombreg = "";
            }
            if ("".equals(zonag) || zonag == null) {
                zonag = "";
            }
            if ("".equals(calleg) || calleg == null) {
                calleg = "";
            }
            if ("".equals(nrog) || nrog == null) {
                nrog = "";
            }
            if ("".equals(telefonog) || telefonog == null) {
                telefonog = "";
            }
            if ("".equals(cig) || cig == null) {
                cig = "";
            }
            if ("".equals(interna) || interna == null) {
                interna = "0";
            }
            if ("".equals(piso) || piso == null) {
                piso = "0";
            }
            if ("".equals(sala) || sala == null) {
                sala = "0";
            }
            if ("".equals(cama) || cama == null) {
                cama = "0";
            }
            if ("".equals(horai) || horai == null) {
                horai = "0";
            }
            if ("".equals(minutoi) || minutoi == null) {
                minutoi = "0";
            }

            int diax = Integer.parseInt(diai);
            int mesx = Integer.parseInt(mesi) - 1;
            int aniox = Integer.parseInt(anioi) - 1900;
            int horax = Integer.parseInt(horai);
            int minutox = Integer.parseInt(minutoi);
            Date fecha_dia = new Date(aniox, mesx, diax, horax, minutox, 00);

            Pacientes datopaciente = new Pacientes();
            datopaciente.setId_paciente(Integer.parseInt(id_paciente));
            datopaciente.setId_consultorio(Integer.parseInt(id_consultorio));
            datopaciente.setId_escolaridad(Integer.parseInt(id_hospital));///
            datopaciente.setVeces(Integer.parseInt(numvig));///
            datopaciente.setHcl(buscarPaciente.getHcl());///
            datopaciente.setNombres(nombres);
            datopaciente.setCodigo(codigop);
            datopaciente.setDireccion(matricula);
            datopaciente.setExpedido(beneficiario);
            datopaciente.setCarnet(buscarPaciente.getCarnet());
            datopaciente.setFactor_riesgo(empresa);
            datopaciente.setId_estado(patronal);
            datopaciente.setMaterno(policlinico);
            datopaciente.setNit(consultorio);
            datopaciente.setCadena4(observacion);
            datopaciente.setOcupacion(zona);
            datopaciente.setPaterno(calle);
            datopaciente.setRegistro(telefono);
            datopaciente.setDocumento(documento);
            datopaciente.setFec_registro(fecha_dia);
            datopaciente.setNombre(nombreg);
            datopaciente.setSeguro(matriculag);
            datopaciente.setTelefono(empresag);
            datopaciente.setTipo_sanguineo(patronalg);
            datopaciente.setResultado(zonag);
            datopaciente.setCadena(calleg);
            datopaciente.setNro(nrog);
            datopaciente.setCadena1(telefonog);
            datopaciente.setCadena2(cig);
            datopaciente.setCadena10(nropac);
            datopaciente.setCadena5(otros);///otros
            datopaciente.setCadena3(diagnostico);///diagnostico
            datopaciente.setId_rubro(Integer.parseInt(interna));///inter
            datopaciente.setId_seguro(Integer.parseInt(piso));///piso
            datopaciente.setId_tipo_documento(Integer.parseInt(sala));///sala
            datopaciente.setId_tipo_far(Integer.parseInt(cama));///cama
            ///datopaciente.setId_tipo_sexo(1);///tipo 1=sin documentos, 
            datopaciente.setTipo("V");///setModificarVigencia
            datopaciente.setId_vigencia(Integer.parseInt(id_vigencia));
            datopaciente.setCod_esta(cliente.getCod_esta());
            this.mi.setModificarVigencia(datopaciente);
            return new ModelAndView("administrarpacientes/ListarDetVigencia", modelo);
        }

        if (("".equals(id_paciente) || id_paciente == null) && !"ImprimirVig".equals(accion1)) {
            Pacientes listavig = new Pacientes();
            listavig.setTipo("V");
            listavig.setCod_esta(cliente.getCod_esta());
            listavig.setVeces(1);
            listavig.setDial(100);
            List listarVigencia = this.mi.getListarPacientesVigencia(listavig);///getListarPacientesVigencia
            modelo.put("listaVigencia", listarVigencia);
            return new ModelAndView("administrarpacientes/ListarDetVigencia", modelo);
        }

        Personas persona = this.mi.getBuscarPersona(cliente.getId_persona()); // saca un registro a ser modificado
        Consultorios datosconsul = this.mi.getDatosConsultorio(persona.getId_consultorio()); // saca un registro a ser modificado
        Pacientes buscarPaciente = this.mi.getDatosPaciente(Integer.parseInt(id_paciente)); // saca un registro a ser modificado
        modelo.put("datos", buscarPaciente);

        Consultorios aa = new Consultorios();
        aa.setCod_esta(cliente.getCod_esta());
        List listarCargos = this.mi.getListarConsultorios(aa);
        modelo.put("listarCargos", listarCargos);

        //List listarPagos = this.mi.getListarDetallePago(buscarPaciente.getId_empresa());
        //modelo.put("listarpagos", listarPagos);
        //modelo.put("cod_esta", Integer.toString(cliente.getCod_esta()));
        Carpetas carpe = new Carpetas();
        carpe.setId_estado("C");
        carpe.setId_carpeta(buscarPaciente.getId_carpeta());
        List listarC = this.mi.getListarCarpetasCaja(carpe);////////tarda al ejecutarse
        modelo.put("listaPacientesD", listarC);

        if ("ModificarVig".equals(accion)) {
            String id_vigencia = request.getParameter("id_vigencia");
            String tipovig = request.getParameter("tipovig");

            Pacientes listavig = new Pacientes();
            listavig.setId_vigencia(Integer.parseInt(id_vigencia));
            listavig.setCod_esta(cliente.getCod_esta());
            listavig.setTipo("S");
            List listarVigencia = this.mi.getListarPacientesVigenciaSolo(listavig);///getListarPacientesVigenciaSolo
            modelo.put("listaVigencia", listarVigencia);
            Pacientes pacvig = (Pacientes) listarVigencia.get(0);
            fecha1 = pacvig.getFecha_ini();
            modelo.put("pacvig", pacvig);
            modelo.put("vigmod", "modifvig");
            modelo.put("dato", cliente);
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
            modelo.put("id_consultorio", Integer.toString(pacvig.getId_consultorio()));
            modelo.put("cod_esta", Integer.toString(pacvig.getId_escolaridad()));
            modelo.put("id_paciente", id_paciente);
            modelo.put("id_vigencia", id_vigencia);
            modelo.put("id_persona", id_persona);
            modelo.put("id_reservacion", id_reservacion);

            if ("4".equals(tipovig)) {
                return new ModelAndView("administrarpacientes/VigenciaModAccidentes", modelo);
            } else if ("3".equals(tipovig)) {
                return new ModelAndView("administrarpacientes/VigenciaModMora", modelo);
            } else if ("2".equals(tipovig)) {
                return new ModelAndView("administrarpacientes/VigenciaModRiesgos", modelo);
            } else {
                return new ModelAndView("administrarpacientes/VigenciaModSinDoc", modelo);
            }
        }

        if ("GuardarDocumento".equals(accion) || "GuardarDocumentoR".equals(accion) || "GuardarDocumentoM".equals(accion)) {
            String nombres = request.getParameter("nombres");
            String matricula = request.getParameter("matricula");
            String carnet = request.getParameter("carnet");
            String numvig = request.getParameter("num");
            String beneficiario = request.getParameter("beneficiario");
            String residencia = request.getParameter("residencia");
            String observacion = request.getParameter("observacion");
            String empresa = request.getParameter("empresa");
            String codigop = request.getParameter("codigop");
            String patronal = request.getParameter("patronal");
            String nropac = request.getParameter("nropac");
            String policlinico = request.getParameter("policlinico");
            String consultorio = request.getParameter("consultorio");
            String diagnostico = request.getParameter("diagnostico");
            String transferencia = request.getParameter("transferencia");
            String id_hospital = request.getParameter("id_hospital");
            String servicio = request.getParameter("servicio");
            String hospital = request.getParameter("hospital");
            String zona = request.getParameter("zona");
            String telefono = request.getParameter("telefono");
            String calle = request.getParameter("calle");
            String documento = request.getParameter("documento");
            String nombreg = request.getParameter("nombreg");
            String matriculag = request.getParameter("matriculag");
            String empresag = request.getParameter("empresag");
            String patronalg = request.getParameter("patronalg");
            String zonag = request.getParameter("zonag");
            String calleg = request.getParameter("calleg");
            String nrog = request.getParameter("nrog");
            String telefonog = request.getParameter("telefonog");
            String cig = request.getParameter("cig");
            String otros = request.getParameter("otros");
            String diai = request.getParameter("diai");
            String mesi = request.getParameter("mesi");
            String anioi = request.getParameter("anioi");
            String horai = request.getParameter("horai");
            String minutoi = request.getParameter("minutoi");
            String interna = request.getParameter("interna");
            String piso = request.getParameter("piso");
            String sala = request.getParameter("sala");
            String cama = request.getParameter("cama");

            if ("".equals(documento)) {
                return new ModelAndView("Aviso", "mensaje", "Falta datos para llenar los campos");
            }
            if ("".equals(servicio) || servicio == null) {
                servicio = "";
            }
            if ("".equals(diagnostico) || diagnostico == null) {
                diagnostico = "";
            }
            if ("".equals(hospital) || hospital == null) {
                hospital = "";
            }
            if ("".equals(id_hospital) || id_hospital == null) {
                id_hospital = "0";
            }
            if ("".equals(matriculag) || matriculag == null) {
                matriculag = "";
            }
            if ("".equals(empresag) || empresag == null) {
                empresag = "";
            }
            if ("".equals(patronalg) || patronalg == null) {
                patronalg = "";
            }
            if ("".equals(nombreg) || nombreg == null) {
                nombreg = "";
            }
            if ("".equals(zonag) || zonag == null) {
                zonag = "";
            }
            if ("".equals(calleg) || calleg == null) {
                calleg = "";
            }
            if ("".equals(nrog) || nrog == null) {
                nrog = "";
            }
            if ("".equals(telefonog) || telefonog == null) {
                telefonog = "";
            }
            if ("".equals(cig) || cig == null) {
                cig = "";
            }
            if ("".equals(interna) || interna == null) {
                interna = "0";
            }
            if ("".equals(piso) || piso == null) {
                piso = "0";
            }
            if ("".equals(sala) || sala == null) {
                sala = "0";
            }
            if ("".equals(cama) || cama == null) {
                cama = "0";
            }
            if ("".equals(horai) || horai == null) {
                horai = "0";
            }
            if ("".equals(minutoi) || minutoi == null) {
                minutoi = "0";
            }

            int diax = Integer.parseInt(diai);
            int mesx = Integer.parseInt(mesi) - 1;
            int aniox = Integer.parseInt(anioi) - 1900;
            int horax = Integer.parseInt(horai);
            int minutox = Integer.parseInt(minutoi);
            Date fecha_dia = new Date(aniox, mesx, diax, horax, minutox, 00);

            Pacientes datopaciente = new Pacientes();
            datopaciente.setId_paciente(Integer.parseInt(id_paciente));
            datopaciente.setDial(Integer.parseInt(id_reservacion));
            datopaciente.setId_empresa(buscarPaciente.getId_empresa());
            datopaciente.setId_carpeta(buscarPaciente.getId_carpeta());
            datopaciente.setId_consultorio(Integer.parseInt(id_consultorio));
            datopaciente.setId_escolaridad(Integer.parseInt(id_hospital));///
            datopaciente.setVeces(Integer.parseInt(numvig));///
            datopaciente.setHcl(buscarPaciente.getHcl());///
            datopaciente.setNombres(nombres);
            datopaciente.setCodigo(codigop);
            datopaciente.setDireccion(matricula);
            datopaciente.setExpedido(beneficiario);
            datopaciente.setCarnet(buscarPaciente.getCarnet());
            datopaciente.setFactor_riesgo(empresa);
            datopaciente.setId_estado(patronal);
            datopaciente.setMaterno(policlinico);
            datopaciente.setNit(consultorio);
            datopaciente.setNro_registro(servicio);
            datopaciente.setNum_cladoc(hospital);
            datopaciente.setOcupacion(zona);
            datopaciente.setPaterno(calle);
            datopaciente.setRegistro(telefono);
            datopaciente.setDocumento(documento);
            datopaciente.setFec_registro(fecha_dia);
            datopaciente.setNombre(nombreg);
            datopaciente.setSeguro(matriculag);
            datopaciente.setTelefono(empresag);
            datopaciente.setTipo_sanguineo(patronalg);
            datopaciente.setResultado(zonag);
            datopaciente.setCadena(calleg);
            datopaciente.setNro(nrog);
            datopaciente.setCadena1(telefonog);
            datopaciente.setCadena2(cig);
            datopaciente.setCadena10(nropac);
            datopaciente.setCadena3(diagnostico);///diagnostico
            datopaciente.setDia(0);///dias_baja
            datopaciente.setId_costo(0);///recetas
            datopaciente.setId_departamento(0);///labos
            datopaciente.setId_dispensa(0);///rx
            datopaciente.setId_ecivil(0);///ecos
            datopaciente.setId_factura(0);///alcohol
            datopaciente.setId_localidad(0);///tac
            datopaciente.setId_pais(0);///trans_a
            datopaciente.setId_pedido(0);///trans_de
            datopaciente.setId_provincia(0);///endo
            datopaciente.setId_rubro(Integer.parseInt(interna));///inter
            datopaciente.setId_seguro(Integer.parseInt(piso));///piso
            datopaciente.setId_tipo_documento(Integer.parseInt(sala));///sala
            datopaciente.setId_tipo_far(Integer.parseInt(cama));///cama
            datopaciente.setId_tipo_parentesco(0);///baja
            datopaciente.setId_tipo_sexo(1);///tipo 1=sin documentos, 
            if ("GuardarDocumentoR".equals(accion)) {
                datopaciente.setId_tipo_sexo(2);///tipo 2=riesgos,     
            }
            if ("GuardarDocumentoM".equals(accion)) {
                datopaciente.setId_tipo_sexo(3);///tipo 3=empresa mora,     
            }
            if ("GuardarDocumentoA".equals(accion)) {
                datopaciente.setId_tipo_sexo(4);///tipo 4=accidentes de trabajo,     
            }
            datopaciente.setCadena4(observacion);///observacion
            datopaciente.setCadena5(otros);///otros
            datopaciente.setCadena6(piso);///piso
            datopaciente.setCadena7(sala);///sala
            datopaciente.setCadena8(cama);///cama
            datopaciente.setCadena9(nropac);///cama
            datopaciente.setId_persona(cliente.getId_persona());
            datopaciente.setCod_esta(cliente.getCod_esta());
            datopaciente.setTipo("V");///tipo 1=sin documentos,  setCrearVigencia
            try {
                this.mi.setCrearVigencia(datopaciente);
                Pacientes listavig = new Pacientes();
                listavig.setTipo("V");
                listavig.setCod_esta(cliente.getCod_esta());
                listavig.setVeces(1);
                listavig.setDial(1);
                if ("GuardarDocumentoR".equals(accion)) {
                    listavig.setVeces(2);
                    listavig.setDial(2);
                }
                if ("GuardarDocumentoM".equals(accion)) {
                    listavig.setVeces(3);
                    listavig.setDial(3);
                }
                List listarVigencia = this.mi.getListarPacientesHC(listavig);
                modelo.put("listaVigencia", listarVigencia);
                return new ModelAndView("administrarpacientes/ListarDetVigencia", modelo);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "La insercion no se cumplio, verifique los datos");
            }
        }///fin guardarDocumento

        if ("ImpresionVig".equals(accion1)) {
            String id_vigencia = request.getParameter("id_vigencia");
            String tipov = request.getParameter("tipovig");

            Pacientes listavig = new Pacientes();
            listavig.setId_vigencia(Integer.parseInt(id_vigencia));
            listavig.setCod_esta(cliente.getCod_esta());
            listavig.setTipo("S");
            List listarVigencia = this.mi.getListarPacientesHC(listavig);
            modelo.put("listaVigencia", listarVigencia);
            modelo.put("dato", cliente);
            Cuadernos dato = new Cuadernos();
            dato.setTipo_egreso(20);   ////getConfigurarImpresionRiesgo
            if ("1".equals(tipov)) {
                dato.setBcg(23);           /////getConfigurarImpresionSinDoc
            }
            if ("2".equals(tipov)) {
                dato.setBcg(20);           /////getConfigurarImpresionRiesgo
            }
            if ("3".equals(tipov)) {
                dato.setBcg(21);           /////getConfigurarImpresionMora
            }
            if ("4".equals(tipov)) {
                dato.setBcg(22);           /////getConfigurarImpresionAccidentes
            }
            List datosgral = this.mi.getConfigurarImpresionRiesgo(dato);
            modelo.put("datosgral", datosgral);
            if ("1".equals(tipov)) {///sin documento
                return new ModelAndView(new ImprimirSinDocPDF(), modelo);
            }
            if ("2".equals(tipov)) {////riesgos
                return new ModelAndView(new ImprimirRiesgoPDF(), modelo);
            }
            if ("3".equals(tipov)) {///mora
                return new ModelAndView(new ImprimirMoraPDF(), modelo);
            }
            if ("4".equals(tipov)) {////accidentes trabajo
                return new ModelAndView(new ImprimirAccidentePDF(), modelo);
            }
        }

        if ("ImprimirVig".equals(accion1)) {
            String id_vigencia = request.getParameter("id_vigencia");
            String tipov = request.getParameter("tipovig");

            Pacientes listavig = new Pacientes();
            listavig.setId_vigencia(Integer.parseInt(id_vigencia));
            listavig.setCod_esta(cliente.getCod_esta());
            listavig.setTipo("S");
            List listarVigencia = this.mi.getListarPacientesHC(listavig);
            modelo.put("listaVigencia", listarVigencia);
            modelo.put("dato", cliente);
            if ("1".equals(tipov)) {
                return new ModelAndView(new VigenciaSinDocumentoPDF(), modelo);
            }
            if ("2".equals(tipov)) {
                return new ModelAndView(new VigenciaRiesgosPDF(), modelo);
            }
            if ("3".equals(tipov)) {
                return new ModelAndView(new VigenciaMoraPDF(), modelo);
            }
            if ("4".equals(tipov)) {
                return new ModelAndView(new VigenciaAccidentesPDF(), modelo);
            }
        }

        modelo.put("area", datoestab.getArea());
        modelo.put("tipo_medico", Integer.toString(datosconsul.getId_cargo()));
        modelo.put("dato", cliente);
        modelo.put("anios", anios);
        modelo.put("meses", meses);
        modelo.put("dias", dias);
        modelo.put("horas", horas);
        modelo.put("minutos", minutos);
        modelo.put("codigo", codigo);
        modelo.put("hora", Integer.toString(fecha1.getHours()));
        modelo.put("minuto", Integer.toString(fecha1.getMinutes()));
        modelo.put("anio", Integer.toString(fecha1.getYear() + 1900));
        modelo.put("mes", Integer.toString(fecha1.getMonth() + 1));
        modelo.put("dia", Integer.toString(fecha1.getDate()));
        modelo.put("id_consultorio", id_consultorio);
        modelo.put("id_reservacion", id_reservacion);
        modelo.put("id_persona", id_persona);
        modelo.put("id_paciente", id_paciente);
        modelo.put("hospi", cliente.getEstablecimiento());
        modelo.put("id_empresa", Integer.toString(buscarPaciente.getId_empresa()));
        modelo.put("id_carpeta", Integer.toString(buscarPaciente.getId_carpeta()));

        if ("Riesgos Extraordinarios".equals(accion)) {
            return new ModelAndView("administrarpacientes/VigenciaRiesgos", modelo);
        }
        if ("Accidentes Trabajo".equals(accion)) {
            return new ModelAndView("administrarpacientes/VigenciaAccidentes", modelo);
        }
        if ("Empresa Mora".equals(accion)) {
            return new ModelAndView("administrarpacientes/VigenciaMora", modelo);
        }

        return new ModelAndView("administrarpacientes/VigenciaSinDoc", modelo);
    }
}
