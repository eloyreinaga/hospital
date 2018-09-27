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
public class Cuaderno6Controlador {

    private final MiFacade mi;

    public Cuaderno6Controlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/Cuaderno6.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String accionE = request.getParameter("accionE");
        String id_paciente = request.getParameter("id_paciente");
        String id_persona = request.getParameter("id_persona");
        String id_reservacion = request.getParameter("id_reservacion");
        String id_consultorio = request.getParameter("id_consultorio");
        String id_pedido = request.getParameter("id_pedido");
        String fechaenf = request.getParameter("fechaenf");
        String nombres = request.getParameter("nombres");
        String id_sexo = request.getParameter("id_sexo");
        String edad = request.getParameter("edad");
        String swinter = request.getParameter("swinter");
        String sw = request.getParameter("sw");
        String tenfer = request.getParameter("tenfer");
        String tipo_medico = request.getParameter("tipo_medico");
        String expedido = request.getParameter("expedido");
        String swenfer = request.getParameter("swenfer");
        String ip = request.getRemoteAddr();
        String host = request.getRemoteHost();
        String[] dias = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
        String[] meses = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        Date fecha1 = new Date();
        int anioq = fecha1.getYear() + 1900;
        String[] anios = {Integer.toString(anioq), Integer.toString(anioq - 1), Integer.toString(anioq - 2), Integer.toString(anioq - 3), Integer.toString(anioq - 4), Integer.toString(anioq - 5), Integer.toString(anioq - 6), Integer.toString(anioq - 7), Integer.toString(anioq - 8), Integer.toString(anioq - 9), Integer.toString(anioq - 10), Integer.toString(anioq - 11), Integer.toString(anioq - 12), Integer.toString(anioq - 13), Integer.toString(anioq - 14), Integer.toString(anioq - 15), Integer.toString(anioq - 16), Integer.toString(anioq - 17), Integer.toString(anioq - 18), Integer.toString(anioq - 19), Integer.toString(anioq - 20), Integer.toString(anioq - 21), Integer.toString(anioq - 22), Integer.toString(anioq - 23), Integer.toString(anioq - 24), Integer.toString(anioq - 25), Integer.toString(anioq - 26), Integer.toString(anioq - 27), Integer.toString(anioq - 28), Integer.toString(anioq - 29), Integer.toString(anioq - 30), Integer.toString(anioq - 31), Integer.toString(anioq - 32), Integer.toString(anioq - 33), Integer.toString(anioq - 34), Integer.toString(anioq - 35), Integer.toString(anioq - 36), Integer.toString(anioq - 37), Integer.toString(anioq - 38), Integer.toString(anioq - 39), Integer.toString(anioq - 40), Integer.toString(anioq - 41), Integer.toString(anioq - 42), Integer.toString(anioq - 43), Integer.toString(anioq - 44), Integer.toString(anioq - 45), Integer.toString(anioq - 46), Integer.toString(anioq - 47), Integer.toString(anioq - 48), Integer.toString(anioq - 49), Integer.toString(anioq - 50), Integer.toString(anioq - 51), Integer.toString(anioq - 52), Integer.toString(anioq - 53), Integer.toString(anioq - 54), Integer.toString(anioq - 55), Integer.toString(anioq - 56), Integer.toString(anioq - 57), Integer.toString(anioq - 58), Integer.toString(anioq - 59), Integer.toString(anioq - 60), Integer.toString(anioq - 61), Integer.toString(anioq - 62), Integer.toString(anioq - 63), Integer.toString(anioq - 64), Integer.toString(anioq - 65), Integer.toString(anioq - 66), Integer.toString(anioq - 67), Integer.toString(anioq - 68), Integer.toString(anioq - 69), Integer.toString(anioq - 70), Integer.toString(anioq - 71), Integer.toString(anioq - 72), Integer.toString(anioq - 73), Integer.toString(anioq - 74), Integer.toString(anioq - 75), Integer.toString(anioq - 76), Integer.toString(anioq - 77), Integer.toString(anioq - 78), Integer.toString(anioq - 79), Integer.toString(anioq - 80), Integer.toString(anioq - 81), Integer.toString(anioq - 82), Integer.toString(anioq - 83), Integer.toString(anioq - 84), Integer.toString(anioq - 85), Integer.toString(anioq - 86), Integer.toString(anioq - 87), Integer.toString(anioq - 88), Integer.toString(anioq - 89), Integer.toString(anioq - 90), Integer.toString(anioq - 91), Integer.toString(anioq - 92), Integer.toString(anioq - 93), Integer.toString(anioq - 94), Integer.toString(anioq - 95), Integer.toString(anioq - 96), Integer.toString(anioq - 97), Integer.toString(anioq - 98), Integer.toString(anioq - 99), Integer.toString(anioq - 100), Integer.toString(anioq - 101), Integer.toString(anioq - 102), Integer.toString(anioq - 103), Integer.toString(anioq - 104), Integer.toString(anioq - 105), Integer.toString(anioq - 106), Integer.toString(anioq - 107), Integer.toString(anioq - 108), Integer.toString(anioq - 109), Integer.toString(anioq - 110)};

        Localidades local = new Localidades();
        List Estab1 = this.mi.getListarEsta(local);
        Localidades datol = (Localidades) Estab1.get(0);
        modelo.put("localidades", datol);
        modelo.put("swenfer", swenfer);
        modelo.put("accionE", accionE);
        modelo.put("anio", Integer.toString(fecha1.getYear() + 1900));
        modelo.put("mes", Integer.toString(fecha1.getMonth() + 1));
        modelo.put("dia", Integer.toString(fecha1.getDate()));
        modelo.put("anios", anios);
        modelo.put("meses", meses);
        modelo.put("dias", dias);
        modelo.put("fechaenf", fechaenf);

        Pacientes buscarPaciente = new Pacientes();
        if (Integer.parseInt(id_paciente) != 0) {
            buscarPaciente = this.mi.getDatosPaciente(Integer.parseInt(id_paciente)); // saca un registro a ser modificado
            //nombres = buscarPaciente.getNombres();
            nombres = buscarPaciente.getPaterno() + ' ' + buscarPaciente.getMaterno() + ' ' + buscarPaciente.getNombre();
            edad = Integer.toString(buscarPaciente.getEdad());
            id_sexo = Integer.toString(buscarPaciente.getId_tipo_sexo());
        }

        Consultorios datosconsulto = this.mi.getDatosConsultorio(cliente.getId_consultorio());

        if (datosconsulto.getId_especialidad() > 0) {
            modelo.put("tenfer", "EmergenciaMedica");
        } else {
            modelo.put("tenfer", "EmergenciaEnfermeria");
        }

        if ("EmergenciaMedica".equals(accion) || "EmergenciaEnfermeria".equals(accion)) {
            modelo.put("tenfer", accion);
        }

        if ("Cuaderno6".equals(accion)) {
            Cuadernos dato6 = new Cuadernos();
            dato6.setCod_esta(cliente.getCod_esta());
            dato6.setId_paciente(Integer.parseInt(id_paciente));
            List listaUni = this.mi.getVerCuaderno6Uni(dato6);
            modelo.put("listaCobros", listaUni);
            return new ModelAndView(new VerCuaderno6PDF(), modelo);
        }

        modelo.put("id_paciente", id_paciente);
        modelo.put("id_persona", id_persona);
        modelo.put("id_pedido", id_pedido);
        modelo.put("id_reservacion", id_reservacion);
        modelo.put("id_consultorio", id_consultorio);
        modelo.put("nombres", nombres);
        modelo.put("id_sexo", id_sexo);
        modelo.put("edad", edad);
        modelo.put("sw", sw);
        modelo.put("tipo_medico", tipo_medico);
        modelo.put("expedido", expedido);
        modelo.put("swinter", swinter);
        modelo.put("swenfer", swenfer);
        modelo.put("accionE", accionE);
        modelo.put("tipo_medico", Integer.toString(datosconsulto.getId_cargo()));
        
        Historiales datohi = new Historiales();
        datohi.setId_historial(Integer.parseInt(id_reservacion));
        datohi.setCod_esta(cliente.getCod_esta());
        Historiales datosHistorial = this.mi.getDatosHistorial(datohi);
        modelo.put("datoHisto", datosHistorial); 
        
        if ("Agregar".equals(accion)) {
            String estado = request.getParameter("estado");
            String laboratorio = request.getParameter("laboratorio");
            String resultado = request.getParameter("resultado");
            String tipo = request.getParameter("tipo");
            String tipoconsulta = request.getParameter("tipoconsulta");
            String inyectable = request.getParameter("inyectable");
            String sueros = request.getParameter("sueros");
            String curaciones = request.getParameter("curaciones");
            String tcuracion = request.getParameter("tcuracion");
            String biopsia = request.getParameter("biopsia");
            String sonda = request.getParameter("sonda");
            String actividad = request.getParameter("actividad");
            String hidrata = request.getParameter("hidrata");
            String oxigeno = request.getParameter("oxigeno");
            String lipomas = request.getParameter("lipomas");
            String suturas = request.getParameter("suturas");
            String retpunto = request.getParameter("retpunto");
            String colocaciones = request.getParameter("colocaciones");
            String tratante = request.getParameter("tratante");
            String accionmed = request.getParameter("accionmed");
            String tinyecta = request.getParameter("tinyecta");
            String tinyectable = request.getParameter("tinyectable");
            String tsuero = request.getParameter("tsuero");
            String cirugia = request.getParameter("cirugia");
            String trabquiro = request.getParameter("trabquiro");
            String bloqueo = request.getParameter("bloqueo");
            String drenaje = request.getParameter("drenaje");
            String oninitomia = request.getParameter("oninitomia");
            String taponamiento = request.getParameter("taponamiento");
            String inmovilizacion = request.getParameter("inmovilizacion");
            String frenectomia = request.getParameter("frenectomia");
            String tendinorrafia = request.getParameter("tendinorrafia");
            String infeccion = request.getParameter("infeccion");

            Date ahora = new Date();

            if ("".equals(id_reservacion) || id_reservacion == null) { ////20-10-2015
                id_reservacion = "0";
            }
            if ("".equals(id_pedido) || id_pedido == null) { ////20-10-2015
                id_pedido = "0";
            }
            Pacientes paciente1 = new Pacientes();
            paciente1.setId_pedido(Integer.parseInt(id_pedido));
            paciente1.setCod_esta(cliente.getCod_esta());
            Pacientes pacienteh = this.mi.getDatosPedido(paciente1); //////////////

            if (pacienteh != null) {
                if (pacienteh.getId_carpeta() != 0) {
                    id_reservacion = Integer.toString(pacienteh.getId_carpeta());
                }
            }

            Historiales datoh = new Historiales();
            datoh.setId_paciente(Integer.parseInt(id_paciente));
            datoh.setId_historial(Integer.parseInt(id_reservacion));
            datoh.setCod_esta(cliente.getCod_esta());
            datoh.setAccion("H");
            List listas = this.mi.getListarHistoriaHoy(datoh);
            datoh.setAccion("I");
            List listasi = this.mi.getListarHistoriaInterHoy(datoh);
            if (listasi.size() > 0) {
                Historiales listaHi = (Historiales) listasi.get(listasi.size() - 1);
                id_reservacion = Integer.toString(listaHi.getId_historial());
                id_persona = Integer.toString(listaHi.getId_persona());
            }
            if (listas.size() > 0) {
                Historiales listaH = (Historiales) listas.get(listas.size() - 1);
                id_reservacion = Integer.toString(listaH.getId_historial());
                id_persona = Integer.toString(listaH.getId_persona());
            }
            if (tipo == null) {
                tipo = "V";
            }
            if (tipoconsulta == null) {
                tipoconsulta = "";
            }
            if (infeccion == null) {
                infeccion = "0";
            }
            if (inyectable == null) {
                inyectable = "0";
            }
            if (sueros == null) {
                sueros = "0";
            }
            if (curaciones == null) {
                curaciones = "0";
            }
            if (tinyecta == null) {
                tinyecta = "0";
            }
            if (tinyectable == null) {
                tinyectable = "0";
            }
            if (tsuero == null) {
                tsuero = "0";
            }
            if (lipomas == null) {
                lipomas = "0";
            }
            if (suturas == null) {
                suturas = "0";
            }
            if (retpunto == null) {
                retpunto = "0";
            }
            if (colocaciones == null) {
                colocaciones = "0";
            }
            if (oninitomia == null) {
                oninitomia = "0";
            }
            if (drenaje == null) {
                drenaje = "0";
            }
            if (bloqueo == null) {
                bloqueo = "0";
            }
            if (biopsia == null) {
                biopsia = "0";
            }
            if (sonda == null) {
                sonda = "0";
            }
            if (hidrata == null) {
                hidrata = "0";
            }
            if (oxigeno == null) {
                oxigeno = "0";
            }
            if (cirugia == null) {
                cirugia = "0";
            }
            if (trabquiro == null) {
                trabquiro = "0";
            }
            if (actividad == null) {
                actividad = "0";
            }
            if (frenectomia == null) {
                frenectomia = "0";
            }
            if (tendinorrafia == null) {
                tendinorrafia = "0";
            }
            if (taponamiento == null) {
                taponamiento = "0";
            }
            if (inmovilizacion == null) {
                inmovilizacion = "0";
            }
            if (tratante == null) {
                tratante = "";
            }
            if (accionmed == null) {
                accionmed = "";
            }

            if ("EnferExt".equals(swenfer)) {
                if (!(listasi.size() > 0 || listas.size() > 0)) {
                    Personas buscarEmpleado = this.mi.getDatosPersona(cliente.getId_persona());
                    Historiales reserva = new Historiales();
                    reserva.setId_paciente(Integer.parseInt(id_paciente));
                    reserva.setId_consultorio(buscarEmpleado.getId_consultorio());
                    reserva.setEdad(buscarPaciente.getEdad());
                    reserva.setCod_esta(cliente.getCod_esta());
                    reserva.setFecha(fecha1);
                    if ("S".equals(buscarPaciente.getId_estado())) {
                        reserva.setExpedido("S");
                        reserva.setId_estado("C");
                        expedido = "S";
                    } else if ("P".equals(buscarPaciente.getId_estado())) {
                        reserva.setExpedido("P");
                        reserva.setId_estado("C");
                        expedido = "P";
                    } else {
                        reserva.setExpedido("E");
                        reserva.setId_estado("C");
                        expedido = "E";
                    }
                    reserva.setId_persona(0);
                    reserva.setCod_esta(cliente.getCod_esta());
                    reserva.setUlt_usuario(cliente.getId_persona());///crea una reserva en tabla reservaciones
                    this.mi.setCrearReservacion(reserva);

                    reserva.setId_estado("%");
                    reserva.setAccion("Z");////04/05/2014  crea y borra un historial solo de ese paciente con id_paciente
                    List listarHG = this.mi.getListarCobroReservaUni(reserva);
                    Historiales listaHG = (Historiales) listarHG.get(listarHG.size() - 1);
                    id_reservacion = Integer.toString(listaHG.getId_reservacion());
                    modelo.put("id_reservacion", id_reservacion);

                    Historiales datoa = new Historiales();
                    datoa.setId_historial(Integer.parseInt(id_reservacion));
                    datoa.setId_persona(cliente.getId_persona());////03/05/2014 id_persona
                    datoa.setId_paciente(Integer.parseInt(id_paciente));
                    datoa.setEdad(buscarPaciente.getEdad());
                    datoa.setId_consultorio(cliente.getId_consultorio());
                    datoa.setTalla(0);
                    datoa.setPeso(0);
                    datoa.setEstimc("0");
                    datoa.setTemperatura(0);
                    datoa.setExpedido("E");
                    datoa.setFc("0");
                    datoa.setPa("0");
                    datoa.setFr("0");
                    datoa.setCodigo("");
                    datoa.setRepetida("N");
                    datoa.setSubjetivo("Enfermeria Cobranza");
                    datoa.setDiagnostico("Enfermeria");
                    datoa.setObjetivo("");
                    datoa.setCodigo("Z718");
                    datoa.setId_seguro(buscarPaciente.getId_seguro());
                    datoa.setCod_esta(cliente.getCod_esta());
                    datoa.setId_por(cliente.getId_persona());
                    int iResultado = this.mi.setRegistrarHistorial(datoa);
                    //crea el historial nuevo en la tabla y elimina el de reservaciones
                    Historiales elimina = new Historiales();
                    elimina.setId_reservacion(Integer.parseInt(id_reservacion));
                    elimina.setAccion(ip);
                    elimina.setCodigo(host);
                    elimina.setExpedido("A");
                    elimina.setCod_esta(cliente.getCod_esta());
                    elimina.setId_persona(cliente.getId_persona());
                    this.mi.setEliminarReserva(elimina);

                    ///Cambia el estado de pedido a realizado creado ///04-05-2014 para pacientes de enfermeria
                    ///19-02-2018 solo actualiza el id_historial
                    //Pacientes paciente1= new Pacientes() ;
                    paciente1.setId_pedido(Integer.parseInt(id_pedido));
                    paciente1.setCod_esta(cliente.getCod_esta());
                    Pacientes paciente = this.mi.getDatosPedido(paciente1); //////////////
                    paciente.setNum_cladoc(paciente.getNum_cladoc());
                    paciente.setNit(paciente.getNit());
                    if ("C".equals(paciente.getId_estado())) {
                        //paciente.setNum_cladoc(paciente.getNum_cladoc());
                        paciente.setId_estado("C");
                    } else {
                        paciente.setId_estado("A"); ///19-02-2018 no actualiza el estado de pago solo historial
                    }

                    paciente.setId_persona(paciente.getId_persona());////se cobrara con una persona despues se cambiaba con el de enfermeria
                    paciente.setPrecio_total(paciente.getPrecio_total());
                    paciente.setFecha_fin(paciente.getFec_registro());
                    paciente.setId_carpeta(Integer.parseInt(id_reservacion));
                    this.mi.setModificarPedido(paciente);
                }
            }

            if (("SPS".equals(accionE) || "Seguro".equals(accionE)) && listas.isEmpty()) {

                Personas buscarEmpleado = this.mi.getDatosPersona(Integer.parseInt(id_persona));
                Historiales reserva = new Historiales();
                reserva.setId_paciente(Integer.parseInt(id_paciente));
                reserva.setId_consultorio(buscarEmpleado.getId_consultorio());
                reserva.setEdad(buscarPaciente.getEdad());
                reserva.setFecha(fecha1);
                if ("S".equals(buscarPaciente.getId_estado())) {
                    reserva.setExpedido("S");
                    reserva.setId_estado("C");
                } else {
                    reserva.setExpedido("P");
                    reserva.setId_estado("C");
                }
                reserva.setId_persona(0);
                reserva.setCod_esta(cliente.getCod_esta());
                reserva.setUlt_usuario(cliente.getId_persona());///crea una reserva en tabla reservaciones
                this.mi.setCrearReservacion(reserva);

                reserva.setId_estado("%");
                reserva.setAccion("Z");
                List listarHG = this.mi.getListarCobroReservaUni(reserva);
                Historiales listaHG = (Historiales) listarHG.get(listarHG.size() - 1);

                Historiales dato = new Historiales();
                dato.setId_historial(listaHG.getId_reservacion());
                dato.setId_persona(Integer.parseInt(id_persona));
                dato.setId_paciente(listaHG.getId_paciente());
                dato.setEdad(listaHG.getEdad());
                dato.setId_consultorio(listaHG.getId_consultorio());
                dato.setTalla(0);
                dato.setPeso(0);
                dato.setEstimc("0");
                dato.setTemperatura(0);
                dato.setExpedido(listaHG.getExpedido());
                dato.setFc("0");
                dato.setPa("0");
                dato.setFr("0");
                dato.setCodigo("");
                dato.setRepetida("N");
                dato.setSubjetivo("Enfermeria");
                dato.setDiagnostico("Enfermeria");
                dato.setObjetivo("");
                dato.setId_seguro(buscarPaciente.getId_seguro());
                dato.setCod_esta(cliente.getCod_esta());
                dato.setId_por(cliente.getId_persona());
                int iResultado = this.mi.setRegistrarHistorial(dato);
                //crea el historial nuevo en la tabla y elimina el de reservaciones
                Historiales elimina = new Historiales();
                elimina.setId_reservacion(listaHG.getId_reservacion());
                id_reservacion = Integer.toString(listaHG.getId_reservacion());
                elimina.setCod_esta(cliente.getCod_esta());
                this.mi.setEliminarReserva(elimina);
            }
            int laedad = Integer.parseInt(edad);
            Cuadernos dato = new Cuadernos();
            dato.setCod_esta(cliente.getCod_esta());
            dato.setNombres(nombres);
            dato.setEdad(laedad);
            dato.setId_cuaderno(Integer.parseInt(id_sexo));

            if (!"".equals(id_reservacion)) {
                dato.setId_historial(Integer.parseInt(id_reservacion));
            }

            dato.setEstado(estado);
            dato.setLaboratorio(laboratorio);
            dato.setResultado(resultado);
            dato.setAspecto(tratante);
            dato.setBacterias(accionmed);

            dato.setSuma20(Integer.parseInt(tcuracion));
            dato.setSuma21(Integer.parseInt(biopsia));
            dato.setSuma22(Integer.parseInt(sonda));
            dato.setSuma23(Integer.parseInt(actividad));
            dato.setSuma24(Integer.parseInt(hidrata));
            dato.setSuma25(Integer.parseInt(oxigeno));
            dato.setSuma26(Integer.parseInt(lipomas));
            dato.setSuma27(Integer.parseInt(suturas));
            dato.setSuma28(Integer.parseInt(retpunto));
            dato.setSuma29(Integer.parseInt(colocaciones));
            dato.setSuma30(Integer.parseInt(tinyecta));
            dato.setSuma31(Integer.parseInt(tsuero));
            dato.setSuma32(Integer.parseInt(cirugia));
            dato.setSuma33(Integer.parseInt(trabquiro));
            dato.setSuma34(Integer.parseInt(bloqueo));
            dato.setSuma35(Integer.parseInt(drenaje));
            dato.setSuma36(Integer.parseInt(oninitomia));
            dato.setSuma37(Integer.parseInt(taponamiento));
            dato.setSuma38(Integer.parseInt(inmovilizacion));
            dato.setSuma39(Integer.parseInt(frenectomia));
            dato.setSuma40(Integer.parseInt(tendinorrafia));
            dato.setSuma41(Integer.parseInt(infeccion));
            dato.setSuma42(Integer.parseInt(tinyectable));
            dato.setReferido(request.getParameter("ref"));
            dato.setContraref(request.getParameter("cref"));
            dato.setInyectable(Integer.parseInt(inyectable));
            //Historiales datohi = new Historiales();
            //datohi.setId_historial(Integer.parseInt(id_reservacion));
            //datohi.setCod_esta(cliente.getCod_esta());
            //Historiales datosHistorial = this.mi.getDatosHistorial(datohi);

            /*if (("S".equals(expedido) || "SPS".equals(accionE))  && Integer.parseInt(inyectable)>0){
            Prestaciones datop = new Prestaciones();
            datop.setId_historial(Integer.parseInt(id_reservacion)) ;
            datop.setId_prestacion(2347);           
            datop.setId_persona(Integer.parseInt(id_persona));           
            datop.setPrestacion("PC95");
            if("7".equals(tipo_medico)){
               int diax = Integer.parseInt(fechaenf.substring(0,2));
               int mesx = Integer.parseInt(fechaenf.substring(3,5))-1;
               int aniox = Integer.parseInt(fechaenf.substring(6,10))-1900;     
               Date enfer = new Date(aniox, mesx, diax); 
               datop.setFecha_ini(enfer);
               dato.setFechap(enfer);
            }else{
                 datop.setFecha_ini(ahora);
                 dato.setFechap(ahora);
            }    
            datop.setCod_esta(cliente.getCod_esta()); 
            this.mi.setCrearRecetaSumi(datop);
        } */
            dato.setSueros(Integer.parseInt(sueros));

            dato.setCuraciones(Integer.parseInt(curaciones));

            dato.setTipo(tipo);
            dato.setTipoconsulta(tipoconsulta);

            if (laedad < 1) {
                dato.setSuma1(1);
            }
            if (laedad >= 1 && laedad <= 4) {
                dato.setSuma2(1);
            }
            if (laedad >= 5 && laedad <= 9) {
                dato.setSuma3(1);
            }
            if (laedad >= 10 && laedad <= 20) {
                dato.setSuma4(1);
            }
            if (laedad >= 21 && laedad <= 59) {
                dato.setSuma5(1);
            }
            if (laedad > 59) {
                dato.setSuma6(1);
            }

            if ("7".equals(tipo_medico)) {
                if (!"".equals(fechaenf)) {
                    int diax = Integer.parseInt(fechaenf.substring(0, 2));
                    int mesx = Integer.parseInt(fechaenf.substring(3, 5)) - 1;
                    int aniox = Integer.parseInt(fechaenf.substring(6, 10)) - 1900;
                    Date denfer = new Date(aniox, mesx, diax);
                    dato.setFechap(denfer);
                } else {
                    dato.setFechap(datosHistorial.getFecha());
                }
                dato.setId_persona(Integer.parseInt(id_persona));
                dato.setId_consultorio(Integer.parseInt(id_consultorio));
            } else {
                dato.setFechap(ahora);
                dato.setId_persona(cliente.getId_persona());
                dato.setId_consultorio(cliente.getId_consultorio());
            }

            // grabar los datos introducidos en la ventana    
            dato.setCod_esta(cliente.getCod_esta());
            this.mi.setCrearCuaderno6(dato);
        }

        if ("Eliminar".equals(accion)) {
            String id_cuaderno = request.getParameter("id_cuaderno");
            Cuadernos datox = new Cuadernos();
            datox.setId_cuaderno(Integer.parseInt(id_cuaderno));
            datox.setId_historial(Integer.parseInt(id_reservacion));
            datox.setCod_esta(cliente.getCod_esta());
            this.mi.setEliminarCuaderno6(datox);
        }

        if ("Terminar".equals(accion)) {

            // grabar los datos introducidos en la ventana     
            if ("N".equals(sw)) {
                // pacientes a cobrar enviados por odontologia
                Pacientes paciente = new Pacientes();
                paciente.setId_estado("A");
                paciente.setId_rubro(5);
                paciente.setId_localidad(cliente.getCod_esta());
                List listarCo = this.mi.getListarCobroRubroEnfer(paciente);
                modelo.put("milista", listarCo);

                // pacientes a cobrar enviados por odontologia
                //paciente.setId_estado("C");
                //List listarOdon = this.mi.getListarCobroRubro(paciente);
                //modelo.put("listaOdon", listarOdon);
                Cuadernos cua6 = new Cuadernos();
                cua6.setCod_esta(cliente.getCod_esta());
                cua6.setId_persona(cliente.getId_persona());
                List listarAtendidos = this.mi.getListaPacientesCuaderno6(cua6);
                modelo.put("listarAtendidos", listarAtendidos);

                return new ModelAndView("administrarenfermeria/ListarCobroReserva", modelo);
            }
            if ("E".equals(sw)) {
                Pacientes paciente1 = new Pacientes();
                paciente1.setId_pedido(Integer.parseInt(id_pedido));
                paciente1.setCod_esta(cliente.getCod_esta());
                Pacientes paciente = this.mi.getDatosPedido(paciente1); //////////////
                paciente.setId_estado("E");
                //this.mi.setModificarPedido(paciente); elimino 2018-02-19 para enfermeria no debe cambiar estado cuando llena cuaderno
                // pacientes a cobrar enviados por odontologia
                paciente.setId_estado("A");
                paciente.setId_rubro(5);
                paciente.setId_localidad(cliente.getCod_esta());
                List listarCo = this.mi.getListarCobroRubroEnfer(paciente);
                modelo.put("listaEnfer", listarCo);

                paciente.setId_persona(cliente.getId_persona());
                List listarpedenf = this.mi.getListarCobroEnfer(paciente);
                modelo.put("listacobenfer", listarpedenf);

                Cuadernos cua6 = new Cuadernos();
                cua6.setCod_esta(cliente.getCod_esta());
                cua6.setId_persona(cliente.getId_persona());
                List listarAtendidos = this.mi.getListaPacientesCuaderno6(cua6);
                modelo.put("listarAtendidos", listarAtendidos);

                return new ModelAndView("administrarenfermeria/ListarCobroReserva", modelo);
            }

            modelo.put("id_reservacion", id_reservacion);
            if ("inter".equals(swinter)) {
                return new ModelAndView("administrarhistoriales/PlanAccionPacienteI", modelo);
            } else {
                return new ModelAndView("administrarhistoriales/PlanAccionPaciente", modelo);
            }
        }
        //Sacar los datos de enfermeria
        if (id_reservacion == null || "".equals(id_reservacion) || Integer.parseInt(id_reservacion) == 0) {
            Cuadernos dato = new Cuadernos();
            dato.setCod_esta(cliente.getCod_esta());
            dato.setId_paciente(Integer.parseInt(id_paciente));
            dato.setId_persona(Integer.parseInt(id_persona));
            List listaEnfer = this.mi.getPacienteCuaderno6(dato);
            modelo.put("listaExter", listaEnfer);
            modelo.put("id_reservacion", id_reservacion);
        } else {
            Cuadernos dato = new Cuadernos();
            dato.setCod_esta(cliente.getCod_esta());
            dato.setId_historial(Integer.parseInt(id_reservacion));
            List listaenfer = this.mi.getPacienteCuaderno6H(dato);
            modelo.put("listaExter", listaenfer);
            modelo.put("id_reservacion", id_reservacion);
        }
        return new ModelAndView("administrarcuadernos/Cuaderno6", modelo);
    }
}
