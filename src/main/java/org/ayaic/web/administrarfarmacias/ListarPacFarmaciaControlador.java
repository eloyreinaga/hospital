package org.ayaic.web.administrarfarmacias;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Consultorios;
import org.ayaic.domain.Historiales;
import org.ayaic.domain.Localidades;
import org.ayaic.domain.Medicamentos;
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.Personas;
import org.ayaic.domain.Prestaciones;
import org.ayaic.domain.Recetas;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ListarPacFarmaciaControlador {

    private final MiFacade mi;

    public ListarPacFarmaciaControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/ListarPacFarmacia.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String id_consultorio = request.getParameter("id_consultorioa");
        String id_historial = request.getParameter("id_historial");
        String id_paciente = request.getParameter("id_paciente");
        String sig_centro = request.getParameter("sig_centro");
        String servicio = request.getParameter("servicio");
        String boton = request.getParameter("boton");
        String accion = request.getParameter("accion");
        String sw1 = request.getParameter("sw1");
        String swfar = request.getParameter("swfar");
        String[] dias = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
        String[] meses = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        String[] horas = {"00", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"};
        String[] minutos = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"};
        Date fechaact = new Date();
        String ip = request.getRemoteAddr();
        String host = request.getRemoteHost();
        //InetAddress ip;
        //ip = InetAddress.getLocalHost();

        Date fecha1 = new Date();
        int anioq = fecha1.getYear() + 1900;
        String[] anios = {Integer.toString(anioq), Integer.toString(anioq - 1), Integer.toString(anioq - 2), Integer.toString(anioq - 3), Integer.toString(anioq - 4), Integer.toString(anioq - 5), Integer.toString(anioq - 6), Integer.toString(anioq - 7), Integer.toString(anioq - 8), Integer.toString(anioq - 9), Integer.toString(anioq - 10), Integer.toString(anioq - 11), Integer.toString(anioq - 12), Integer.toString(anioq - 13), Integer.toString(anioq - 14), Integer.toString(anioq - 15), Integer.toString(anioq - 16), Integer.toString(anioq - 17), Integer.toString(anioq - 18), Integer.toString(anioq - 19), Integer.toString(anioq - 20), Integer.toString(anioq - 21), Integer.toString(anioq - 22), Integer.toString(anioq - 23), Integer.toString(anioq - 24), Integer.toString(anioq - 25), Integer.toString(anioq - 26), Integer.toString(anioq - 27), Integer.toString(anioq - 28), Integer.toString(anioq - 29), Integer.toString(anioq - 30), Integer.toString(anioq - 31), Integer.toString(anioq - 32), Integer.toString(anioq - 33), Integer.toString(anioq - 34), Integer.toString(anioq - 35), Integer.toString(anioq - 36), Integer.toString(anioq - 37), Integer.toString(anioq - 38), Integer.toString(anioq - 39), Integer.toString(anioq - 40), Integer.toString(anioq - 41), Integer.toString(anioq - 42), Integer.toString(anioq - 43), Integer.toString(anioq - 44), Integer.toString(anioq - 45), Integer.toString(anioq - 46), Integer.toString(anioq - 47), Integer.toString(anioq - 48), Integer.toString(anioq - 49), Integer.toString(anioq - 50), Integer.toString(anioq - 51), Integer.toString(anioq - 52), Integer.toString(anioq - 53), Integer.toString(anioq - 54), Integer.toString(anioq - 55), Integer.toString(anioq - 56), Integer.toString(anioq - 57), Integer.toString(anioq - 58), Integer.toString(anioq - 59), Integer.toString(anioq - 60), Integer.toString(anioq - 61), Integer.toString(anioq - 62), Integer.toString(anioq - 63), Integer.toString(anioq - 64), Integer.toString(anioq - 65), Integer.toString(anioq - 66), Integer.toString(anioq - 67), Integer.toString(anioq - 68), Integer.toString(anioq - 69), Integer.toString(anioq - 70), Integer.toString(anioq - 71), Integer.toString(anioq - 72), Integer.toString(anioq - 73), Integer.toString(anioq - 74), Integer.toString(anioq - 75), Integer.toString(anioq - 76), Integer.toString(anioq - 77), Integer.toString(anioq - 78), Integer.toString(anioq - 79), Integer.toString(anioq - 80), Integer.toString(anioq - 81), Integer.toString(anioq - 82), Integer.toString(anioq - 83), Integer.toString(anioq - 84), Integer.toString(anioq - 85), Integer.toString(anioq - 86), Integer.toString(anioq - 87), Integer.toString(anioq - 88), Integer.toString(anioq - 89), Integer.toString(anioq - 90), Integer.toString(anioq - 91), Integer.toString(anioq - 92), Integer.toString(anioq - 93), Integer.toString(anioq - 94), Integer.toString(anioq - 95), Integer.toString(anioq - 96), Integer.toString(anioq - 97), Integer.toString(anioq - 98), Integer.toString(anioq - 99), Integer.toString(anioq - 100), Integer.toString(anioq - 101), Integer.toString(anioq - 102), Integer.toString(anioq - 103), Integer.toString(anioq - 104), Integer.toString(anioq - 105), Integer.toString(anioq - 106), Integer.toString(anioq - 107), Integer.toString(anioq - 108), Integer.toString(anioq - 109), Integer.toString(anioq - 110)};

        Localidades local = new Localidades();
        List Estab1 = this.mi.getListarEsta(local);
        Localidades datoestab = (Localidades) Estab1.get(0);
        modelo.put("estab", Integer.toString(datoestab.getCod_esta()));
        modelo.put("id_consultoriofar", Integer.toString(cliente.getId_consultorio()));
        modelo.put("sig_centro", sig_centro);
        modelo.put("servicio", servicio);

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
        modelo.put("anioe", Integer.toString(fecha1.getYear() + 1900));
        modelo.put("mese", Integer.toString(fecha1.getMonth() + 1));
        modelo.put("diae", Integer.toString(fecha1.getDate()));
        modelo.put("horae", Integer.toString(fecha1.getHours()));
        modelo.put("minutoe", Integer.toString(fecha1.getMinutes()));

        if (id_paciente != null && !"".equals(id_paciente)) {
            Pacientes buscarPaciente = this.mi.getDatosPaciente(Integer.parseInt(id_paciente)); // saca un registro a ser modificado
            modelo.put("datos", buscarPaciente);
        }

        Consultorios aa = new Consultorios();
        aa.setCod_esta(cliente.getCod_esta());
        if (sig_centro != null && !"".equals(sig_centro)) {
            aa.setCod_esta(Integer.parseInt(sig_centro)); //// para llenar recetas de otro centro pero no jala
        }
        List listarCargos = this.mi.getListarConsultorios(aa);
        modelo.put("listarCargos", listarCargos);
        aa.setId_estado("R");      //////getListarTipoRecetaCNS
        List listarTipReceta = this.mi.getListarTipoRecetaCNS(aa);
        modelo.put("listarTipReceta", listarTipReceta);
        aa.setId_estado("F");     /////getListarCentroCNSFar
        aa.setId_especialidad(Integer.parseInt(Integer.toString(cliente.getCod_esta()).substring(0, 1)));
        List listarCentros = this.mi.getListarCentroCNSFar(aa);
        modelo.put("listarCentros", listarCentros);
        aa.setId_estado("H");     /////getListarServicioCNSFar
        List listarServicio = this.mi.getListarServicioCNSFar(aa);
        modelo.put("listarServicio", listarServicio);
        ///modelo.put("descripcionc", "78");
        modelo.put("cod_esta", Integer.toString(cliente.getCod_esta()));
        modelo.put("accion", accion);
        modelo.put("id_paciente", id_paciente);
		modelo.put("seguro_estab", Integer.toString(cliente.getInst_id()));

        if ("KardexMed".equals(accion)) {
            Recetas datoreceta = new Recetas();
            datoreceta.setId_paciente(Integer.parseInt(id_paciente));
            datoreceta.setId_estado("I");     ////getListarKardexIndi
            List listarKardexMedicamento = this.mi.getListarKardexIndi(datoreceta);
            modelo.put("datokardex", listarKardexMedicamento);
            return new ModelAndView(new ListarKardexIndPDF(), modelo);
        }

        if ("BuscarN".equals(boton)) {
            String nombres = request.getParameter("nombre");
            String nombre = request.getParameter("nombre");
            nombres = nombres.trim();
            nombres = nombres.replaceAll(" +", " ");///quita espacios en blanco entre cadenas
            nombre = nombres.replaceAll("\\s", "%");
            nombre = "%" + nombre.toUpperCase() + "%";
            nombres = nombres.replaceAll("\\s", ":*&");
            //nombres = "%" + nombres + "%";
            if (nombres.length() > 0) {
                nombres = nombres + ":*";
            }
            Pacientes paciente = new Pacientes();
            paciente.setNombres(nombres);
            paciente.setNombre(nombre);
            paciente.setId_estado(request.getParameter("id_estado"));
            if (datoestab.getCod_esta() == 200010) {
                paciente.setTipo("C");  ///getListarPacientesNombreCns
            }
            List listarPacientes = this.mi.getListarPacientesNombreCns(paciente);
            modelo.put("listaPacientes", listarPacientes);

            List listarSeguros = this.mi.getListarSeguros("A");
            modelo.put("listaPacAfi", listarSeguros);

            return new ModelAndView("administrarfarmacias/ListarPacFarmacia", modelo);
        }

        if ("BuscarH".equals(boton)) {
            String hcl = request.getParameter("hcl");

            if ("".equals(hcl)) {
                return new ModelAndView("Aviso", "mensaje", "Debe colocar un dato : Numero Historia, Carnet, Matricula");
            }

            Pacientes paciente = new Pacientes();
            paciente.setCod_esta(cliente.getCod_esta());
            if (datoestab.getCod_esta() == 200010) {
                hcl = hcl.replaceAll("\\s", "%");
                hcl = "%" + hcl + "%";
                paciente.setNombres(hcl);
                paciente.setTipo("H");  ///getListarPacientesNombreCns
                List listarPaises = this.mi.getListarPacientesNombreCns(paciente);
                modelo.put("listaPacientes", listarPaises);
            } else {
                paciente.setHcl(Integer.parseInt(hcl));
                paciente.setTipo("W");
                List listarPaises = this.mi.getListarPacientesHC(paciente);
                modelo.put("listaPacientes", listarPaises);
            }

            List listarSeguros = this.mi.getListarSeguros("%");
            modelo.put("listaPacAfi", listarSeguros);

            return new ModelAndView("administrarfarmacias/ListarPacFarmacia", modelo);
        }

        if ("BuscarF".equals(boton)) {
            String aux_dia = request.getParameter("dia");
            String aux_mes = request.getParameter("mes");
            String aux_anio = request.getParameter("anio");
            if ("".equals(aux_dia) || "".equals(aux_mes) || "".equals(aux_anio)) {
                return new ModelAndView("Aviso", "mensaje", "Debe colocar alguna fecha");
            }
            if ((aux_dia == null) || (aux_mes == null) || (aux_anio == null)) {
                return new ModelAndView("administrarpacientes/ListarPacientes", modelo);
            } else {
                int dia = Integer.parseInt(aux_dia);
                int mes = Integer.parseInt(aux_mes) - 1;
                int anio = Integer.parseInt(aux_anio) - 1900;

                Date fecha = new Date(anio, mes, dia);

                Pacientes paciente = new Pacientes();
                paciente.setFec_nacimiento(fecha);
                List listaDePacientes = this.mi.getListarPacientesFN(paciente);
                modelo.put("listaPacientes", listaDePacientes);

            }
            return new ModelAndView("administrarfarmacias/ListarPacFarmacia", modelo);
        }

        if ("SiguienteMed".equals(accion)) {
            Historiales dato = new Historiales();
            dato.setId_estado("A");
            dato.setExpedido("%");
            dato.setAccion("P");
            dato.setCod_esta(cliente.getCod_esta());
            dato.setId_persona(cliente.getId_persona());
            List listarAtendidos = this.mi.getListarAtendidos(dato);/////getListarAtendidosPend
            // if(listarAtendidos.size()>0){
            //     Historiales datohi = (Historiales) listarAtendidos.get(0);
            //     return new ModelAndView("Aviso","mensaje","Ud. tiene registros pendientes de hoy : "+datohi.getNombres()+"  entregue o elimine");
            // }

            modelo.put("swfar", swfar);
            return new ModelAndView("administrarfarmacias/ConfirmarPaciente", modelo);
        }

        if ("Siguiente".equals(accion)) {
            String id_persona = request.getParameter("id_persona");
            String id_reservacion = request.getParameter("id_reservacion");
            String id_tipo = request.getParameter("id_tipo");
            String diai = request.getParameter("diai");
            String mesi = request.getParameter("mesi");
            String anioi = request.getParameter("anioi");
            String horai = request.getParameter("horai");
            String minutoi = request.getParameter("minutoi");
            String[] diasf = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "20", "25", "30", "40", "50", "60", "70", "80", "90"};

            String a = "/";
            String fecha_a = (diai) + a + (mesi) + a + (anioi);
            Date fechaa = new Date((Integer.parseInt(anioi) - 1900), (Integer.parseInt(mesi) - 1), Integer.parseInt(diai), Integer.parseInt(horai), Integer.parseInt(minutoi));
            modelo.put("dias", diasf);

            if (cliente.getId_cargo() == 33) {
                long fechalab = fechaa.getTime();
                long fechanow = fechaact.getTime();
                long diferencia = fechanow - fechalab;
                double horasex = Math.floor(diferencia / (1000 * 60 * 60));
                if (horasex > 10) {
                    return new ModelAndView("Aviso", "mensaje", "Ud. ya NO puede introducir esta receta con esta fecha");
                }
            }

            if(datoestab.getInst_id()==10){ ////para CNS
			   if(id_consultorio==null || "".equals(id_consultorio)  || "".equals(id_persona)) {
				   return new ModelAndView("Aviso","mensaje","Debe selecionar especilidad y medico");
			   }
		    }else{
			   id_consultorio="0";
			   id_persona="0";
			   id_tipo="0";
		    }

            Pacientes buscarPaciente = new Pacientes();
            buscarPaciente = this.mi.getDatosPaciente(Integer.parseInt(id_paciente)); // saca un registro a ser modificado
            Historiales reserva = new Historiales();
            reserva.setId_paciente(Integer.parseInt(id_paciente));
            reserva.setId_consultorio(Integer.parseInt(id_consultorio));
            reserva.setEdad(buscarPaciente.getEdad());
            reserva.setId_estado("C");
            reserva.setExpedido("Z");
            reserva.setId_persona(0);
            reserva.setId_seguro(buscarPaciente.getId_seguro());
            reserva.setFecha(fechaa); ////graba la fecha de atencion 4/12/2013
            reserva.setUlt_usuario(cliente.getId_usuario());
            reserva.setCod_esta(cliente.getCod_esta());
            //if(sig_centro!=null && !"".equals(sig_centro)){
            //    reserva.setCod_esta(Integer.parseInt(sig_centro)) ;  ////para llenar recetas de otro centro pero no jala
            //}
            this.mi.setCrearReservacion(reserva); ///crea una reserva en tabla reservaciones

            reserva.setId_estado("%");
            reserva.setAccion("Z");//03-03-2014
            List listarHG = this.mi.getListarCobroReservaUni(reserva);
            Historiales listaHG = (Historiales) listarHG.get(0);

            Historiales dato = new Historiales();
            dato.setId_historial(listaHG.getId_reservacion());
            dato.setId_persona(Integer.parseInt(id_persona));
            dato.setId_paciente(listaHG.getId_paciente());
            dato.setEdad(listaHG.getEdad());
            dato.setId_consultorio(Integer.parseInt(id_consultorio));
            dato.setTalla(0);
            dato.setPeso(0);
            dato.setEstimc("0");
            dato.setTemperatura(0);
            dato.setId_estado("B");
            if ("Z".equals(listaHG.getExpedido())) {
                dato.setExpedido("P");
            } else {
                dato.setExpedido(listaHG.getExpedido());
            }

            dato.setFc("0");
            dato.setPa("0");
            dato.setFr("0");
            dato.setCodigo("");
            dato.setSo2("0");
            dato.setGlicemia("0");
            dato.setRepetida("N");
            dato.setSubjetivo("Desde Farmacia");
            dato.setDiagnostico("Desde Farmacia");
            dato.setObjetivo("Desde Farmacia");
            dato.setCodigo("Z000");
            dato.setFechalab(listaHG.getFecha());
            dato.setId_seguro(buscarPaciente.getId_seguro());
            dato.setCod_esta(cliente.getCod_esta());
            //if(sig_centro!=null && !"".equals(sig_centro)){
            //   dato.setCod_esta(Integer.parseInt(sig_centro)) ;
            // }
            dato.setId_por(cliente.getId_persona());
            int iResultado = this.mi.setRegistrarHistorial(dato);
            //crea el historial nuevo en la tabla y elimina el de reservaciones
            Historiales elimina = new Historiales();
            elimina.setId_reservacion(listaHG.getId_reservacion());
            elimina.setAccion(ip);
            elimina.setCodigo(host);
            elimina.setExpedido("A");
            elimina.setId_persona(cliente.getId_persona());
            id_reservacion = Integer.toString(listaHG.getId_reservacion());
            elimina.setCod_esta(cliente.getCod_esta());
            //if(sig_centro!=null && !"".equals(sig_centro)){
            //    elimina.setCod_esta(Integer.parseInt(sig_centro)) ;
            // }
            this.mi.setEliminarReserva(elimina);

            modelo.put("id_paciente", id_paciente);
            modelo.put("id_persona", id_persona);
            modelo.put("id_personafar", id_persona);
            modelo.put("id_reservacion", id_reservacion);
            modelo.put("id_consultorio", id_consultorio);
            modelo.put("fechamodif", fechaa);
            modelo.put("fechamodifi", fecha_a);
            modelo.put("swfar", swfar);
            modelo.put("id_tipointer", id_tipo);
            modelo.put("expedido", buscarPaciente.getId_estado());

            dato.setFecha(fechaa);/////cambia kla fecha del historial
            dato.setId_estado("A");
            dato.setCod_esta(cliente.getCod_esta());
            //if(sig_centro!=null && !"".equals(sig_centro)){
            //   dato.setCod_esta(Integer.parseInt(sig_centro)) ;
            //}
            this.mi.setModificarEstadoHistorial(dato);

            Historiales datoi = new Historiales();
            datoi.setId_historial(Integer.parseInt(id_reservacion));
            datoi.setId_cargo(14); ////14 internado por farmacia
            datoi.setExpedido("B");
            datoi.setCod_esta(cliente.getCod_esta());
            this.mi.setModificarInternado(datoi); ////cambia a 14 el historial, internado por farmacia       

            //Hasta aqui se ha creado un numero de historia clinica nuevo y se ha eliminado en reservaciones
            Medicamentos datomed = new Medicamentos();
            datomed.setId_persona(Integer.parseInt(id_persona));
            datomed.setId_farmacia(cliente.getId_farmacia());
            datomed.setCod_esta(cliente.getCod_esta());
            datomed.setExpedido("%");
            List listarMedicamentosCot = this.mi.getListarMedicamentosCot(datomed);
            modelo.put("listarMedicamentosCot", listarMedicamentosCot);
            /**
             * crea una historia internado cuando se mete en farmacia una
             * receta, pero ya no es necesario solo que diga internado y listo
             * if (datoestab.getCod_esta()==200010 && "1".equals(id_tipo) ) {
             * Cuadernos dato5=new Cuadernos(); dato5.setAspecto("M");
             * dato5.setId_historial(Integer.parseInt(id_reservacion)); List
             * listaCie = this.mi.getVerCuaderno1Cie(dato5); String CIE10i="";
             * String CIE10e=""; *
             * dato5.setId_historial(Integer.parseInt(id_reservacion));
             * dato5.setServicio(Integer.parseInt(id_consultorio));
             * dato5.setReferido(""); dato5.setContraref("");
             * dato5.setDiagnostico_ing(CIE10i); dato5.setDiagnostico("");
             * dato5.setDiasi(0); dato5.setDiasc(0); dato5.setId_estado("A");
             * dato5.setIngreso(0); dato5.setCirugia(0); dato5.setAnastecia(0);
             * dato5.setEgreso(0); dato5.setTipo_egreso(0);
             * dato5.setFallecido(0); dato5.setFallecidom5(0);
             * dato5.setFallecidoy5(9);
             * dato5.setCod_esta(cliente.getCod_esta()); //if(sig_centro!=null
             * && !"".equals(sig_centro)){ //
             * dato5.setCod_esta(Integer.parseInt(sig_centro)) ; //}
             *
             * if (buscarPaciente.getEdad()<5)
             * dato5.setEdad(1);
             * if (buscarPaciente.getEdad()>=5 && buscarPaciente.getEdad()<=9)
             * dato5.setEdad(1);
             * if (buscarPaciente.getEdad()>=10 && buscarPaciente.getEdad()<=20)
             * dato5.setEdad(1);
             * if (buscarPaciente.getEdad()>=21 && buscarPaciente.getEdad()<=59)
             * dato5.setEdad(1);
             * if (buscarPaciente.getEdad()>59) dato5.setEdad(1);
             * this.mi.setCrearCuaderno5(dato5);
             *
             * Historiales datoi=new Historiales();
             * datoi.setId_persona(Integer.parseInt(id_persona));
             * datoi.setAccion("R");
             * datoi.setId_historial(Integer.parseInt(id_reservacion));
             * datoi.setId_cargo(13); ////13 alta por farmacia
             * datoi.setExpedido("B"); datoi.setCod_esta(cliente.getCod_esta());
             * //if(sig_centro!=null && !"".equals(sig_centro)){ //
             * datoi.setCod_esta(Integer.parseInt(sig_centro)) ; //}
             * this.mi.setModificarMorbilidad(datoi);
             * this.mi.setModificarInternado(datoi); *
             * datoi.setId_consultorio(Integer.parseInt(request.getParameter("id_consultorio"))
             * ); datoi.setId_persona(Integer.parseInt(id_persona) );
             * datoi.setDiagnostico("Desde Farmacia"); datoi.setId_estado("A");
             * datoi.setId_cargo(0); datoi.setId_sexo(0); datoi.setAccion("A");
             * datoi.setId_por(cliente.getId_persona());
             * datoi.setId_persona(Integer.parseInt(id_persona));
             * this.mi.setCrearInternado(datoi); /////setCrearInternado2 }
             *////
            modelo.put("datos", buscarPaciente);
            int cuaderno = 0;
            Historiales datoc = new Historiales();
            datoc.setId_historial(Integer.parseInt(id_reservacion));

            Prestaciones prestac = new Prestaciones();
            prestac.setId_historial(Integer.parseInt(id_reservacion));
            prestac.setId_persona(cliente.getId_persona());
            prestac.setCod_esta(cliente.getCod_esta());
            List listarRecetas = this.mi.getListarSumiRecetas(prestac);
            modelo.put("listarRecetas", listarRecetas);
            
            List listPrestacionCot = this.mi.getListarPrestacionesCot(prestac);
            modelo.put("listarPrestacionesCot", listPrestacionCot);

            Recetas datore = new Recetas();
            datore.setId_historial(Integer.parseInt(id_reservacion));
            datore.setId_estado("%");
            datore.setId_farmacia(cliente.getId_farmacia());
            datore.setCod_esta(cliente.getCod_esta());
            List listarRecetaSumi = this.mi.getListarRecetas(datore);
            modelo.put("listarRecetaSumi", listarRecetaSumi);
            
            modelo.put("codesta", Integer.toString(datoestab.getCod_esta()));
            modelo.put("medica", "si");

            if ("S".equals(buscarPaciente.getId_estado())) {
                return new ModelAndView("administrarhistoriales/RecetarSumiPaciente", modelo);
            } else {
                return new ModelAndView("administrarhistoriales/RecetarPaciente", modelo);
            }
        }

        if (sig_centro != null && !"".equals(sig_centro)) {

            modelo.put("cod_esta", sig_centro);
            if (id_consultorio != null && !"".equals(id_consultorio) && !"0".equals(id_consultorio)) {
                Personas persona = new Personas();
                persona.setId_consultorio(Integer.parseInt(id_consultorio));
                persona.setCod_esta(cliente.getCod_esta());
                if (sig_centro != null && !"".equals(sig_centro)) {
                    persona.setCod_esta(Integer.parseInt(sig_centro));
                }
                List buscarEmpleado = this.mi.getDatosPersonaConsul(persona);
                Personas emp1 = (Personas) buscarEmpleado.get(0);
                modelo.put("listaPersonas", buscarEmpleado);
                modelo.put("id_consultorio", id_consultorio);
                modelo.put("descrip", emp1.getCadena1());
                modelo.put("swfar", swfar);
                return new ModelAndView("administrarfarmacias/ConfirmarPaciente", modelo);
            }

            return new ModelAndView("administrarfarmacias/ConfirmarPaciente", modelo);
        }

        return new ModelAndView("administrarfarmacias/ListarPacFarmacia", modelo);

    }
}
