package org.ayaic.web.administrarhistoriales;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import org.ayaic.domain.Prestaciones;
import org.ayaic.domain.Recetas;
import org.ayaic.domain.Salas;
import org.ayaic.domain.Sexos;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PlanAccionPacienteControlador {

    private final MiFacade mi;

    public PlanAccionPacienteControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/PlanAccionPaciente.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String accioncobro = request.getParameter("accioncobro");
        String accionE = request.getParameter("accionE");
        String sig_centro = request.getParameter("sig_centro");
        String id_tipointer = request.getParameter("id_tipointer");
        String id_paciente = request.getParameter("id_paciente");
        String id_pedido = request.getParameter("id_pedido");
        String id_reservacion = request.getParameter("id_reservacion");
        String id_consultorio = request.getParameter("id_consultorio");
        String id_tipo_far = request.getParameter("id_tipo_far");
        String id_persona = request.getParameter("id_persona");
        String id_personafar = request.getParameter("id_personafar");
        String expedido = request.getParameter("expedido");
        String revision = request.getParameter("revision");
        String tipo_medico = request.getParameter("tipo_medico");
        String swinter = request.getParameter("swinter");
        String estimc = request.getParameter("estimc");
        String sweco = request.getParameter("sweco");
        String fecha = request.getParameter("fecha");
        String swenfer = request.getParameter("swenfer");
        String swemerg = request.getParameter("swemerg");
        String[] dias = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
        String[] meses = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        Date fecha1 = new Date();
        int anioq = fecha1.getYear() + 1900;
        String[] anios = {Integer.toString(anioq), Integer.toString(anioq - 1), Integer.toString(anioq - 2), Integer.toString(anioq - 3), Integer.toString(anioq - 4), Integer.toString(anioq - 5), Integer.toString(anioq - 6), Integer.toString(anioq - 7), Integer.toString(anioq - 8), Integer.toString(anioq - 9), Integer.toString(anioq - 10), Integer.toString(anioq - 11), Integer.toString(anioq - 12), Integer.toString(anioq - 13), Integer.toString(anioq - 14), Integer.toString(anioq - 15), Integer.toString(anioq - 16), Integer.toString(anioq - 17), Integer.toString(anioq - 18), Integer.toString(anioq - 19), Integer.toString(anioq - 20), Integer.toString(anioq - 21), Integer.toString(anioq - 22), Integer.toString(anioq - 23), Integer.toString(anioq - 24), Integer.toString(anioq - 25), Integer.toString(anioq - 26), Integer.toString(anioq - 27), Integer.toString(anioq - 28), Integer.toString(anioq - 29), Integer.toString(anioq - 30), Integer.toString(anioq - 31), Integer.toString(anioq - 32), Integer.toString(anioq - 33), Integer.toString(anioq - 34), Integer.toString(anioq - 35), Integer.toString(anioq - 36), Integer.toString(anioq - 37), Integer.toString(anioq - 38), Integer.toString(anioq - 39), Integer.toString(anioq - 40), Integer.toString(anioq - 41), Integer.toString(anioq - 42), Integer.toString(anioq - 43), Integer.toString(anioq - 44), Integer.toString(anioq - 45), Integer.toString(anioq - 46), Integer.toString(anioq - 47), Integer.toString(anioq - 48), Integer.toString(anioq - 49), Integer.toString(anioq - 50), Integer.toString(anioq - 51), Integer.toString(anioq - 52), Integer.toString(anioq - 53), Integer.toString(anioq - 54), Integer.toString(anioq - 55), Integer.toString(anioq - 56), Integer.toString(anioq - 57), Integer.toString(anioq - 58), Integer.toString(anioq - 59), Integer.toString(anioq - 60), Integer.toString(anioq - 61), Integer.toString(anioq - 62), Integer.toString(anioq - 63), Integer.toString(anioq - 64), Integer.toString(anioq - 65), Integer.toString(anioq - 66), Integer.toString(anioq - 67), Integer.toString(anioq - 68), Integer.toString(anioq - 69), Integer.toString(anioq - 70), Integer.toString(anioq - 71), Integer.toString(anioq - 72), Integer.toString(anioq - 73), Integer.toString(anioq - 74), Integer.toString(anioq - 75), Integer.toString(anioq - 76), Integer.toString(anioq - 77), Integer.toString(anioq - 78), Integer.toString(anioq - 79), Integer.toString(anioq - 80), Integer.toString(anioq - 81), Integer.toString(anioq - 82), Integer.toString(anioq - 83), Integer.toString(anioq - 84), Integer.toString(anioq - 85), Integer.toString(anioq - 86), Integer.toString(anioq - 87), Integer.toString(anioq - 88), Integer.toString(anioq - 89), Integer.toString(anioq - 90), Integer.toString(anioq - 91), Integer.toString(anioq - 92), Integer.toString(anioq - 93), Integer.toString(anioq - 94), Integer.toString(anioq - 95), Integer.toString(anioq - 96), Integer.toString(anioq - 97), Integer.toString(anioq - 98), Integer.toString(anioq - 99), Integer.toString(anioq - 100), Integer.toString(anioq - 101), Integer.toString(anioq - 102), Integer.toString(anioq - 103), Integer.toString(anioq - 104), Integer.toString(anioq - 105), Integer.toString(anioq - 106), Integer.toString(anioq - 107), Integer.toString(anioq - 108), Integer.toString(anioq - 109), Integer.toString(anioq - 110)};
        String[] horas = {"00", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"};
        String[] minutos = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"};
        String[] aniosq = {(Integer.toString(anioq)), (Integer.toString(anioq - 1))};
        String[] aniosqq = {(Integer.toString(anioq)), (Integer.toString(anioq + 1))};
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date fechaact = new Date();
        int gsw = 1;

        Consultorios datosconsultorio = this.mi.getDatosConsultorio(cliente.getId_consultorio()); // saca un registro a ser modificado
        modelo.put("administra", Integer.toString(datosconsultorio.getId_cargo()));
        modelo.put("urgencias", Integer.toString(cliente.getId_almacen()));
        modelo.put("id_consultoriofar", Integer.toString(cliente.getId_consultorio()));
        modelo.put("id_tipointer", id_tipointer);
        modelo.put("id_personafar", id_personafar);
        modelo.put("revision", revision);

        if (id_paciente == null || "".equals(id_paciente)) {
            id_paciente = "0";
        }
        
        Pacientes buscarPaciente = this.mi.getDatosPaciente(Integer.parseInt(id_paciente)); // saca un registro a ser modificado
        modelo.put("datos", buscarPaciente);
        modelo.put("carnet", buscarPaciente.getCarnet());

        if (id_reservacion == null || "".equals(id_reservacion)) {
            id_reservacion = "1"; /////////////fallas enfermeria
        }

        Historiales datohi = new Historiales();
        datohi.setId_historial(Integer.parseInt(id_reservacion));
        datohi.setId_paciente(Integer.parseInt(id_paciente));
        datohi.setCod_esta(cliente.getCod_esta());
        Historiales datosHistorial = this.mi.getDatosHistorial(datohi);

        String id_sexo_e = Integer.toString(buscarPaciente.getId_tipo_sexo());          //Sacar id_sexo
        Date fecha_nac = buscarPaciente.getFec_nacimiento();
        int xanio = fecha_nac.getYear() + 1900;
        int xmes = fecha_nac.getMonth() + 1;
        int xdia = fecha_nac.getDate();

        Localidades local = new Localidades();
        List Estab2 = this.mi.getListarEsta(local);
        local.setCod_esta(cliente.getCod_esta());
        local.setArea("U");  ///getListarEstaUsua el estab del usuario
        List Estab1 = this.mi.getListarEstaUsua(local);
        Localidades datoest = (Localidades) Estab2.get(0);  //////datoest muestra del CNS

        Localidades datoestab = (Localidades) Estab1.get(0);
        modelo.put("sig_centro", sig_centro);
        modelo.put("codesta", Integer.toString(datoest.getCod_esta()));
        modelo.put("cod_esta", Integer.toString(cliente.getCod_esta()));
        modelo.put("urgencia", Integer.toString(cliente.getId_almacen()));
        modelo.put("id_tipo_far", id_tipo_far);
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
        modelo.put("area", datoestab.getArea());

        if ("T".equals(datoestab.getArea()) || "P".equals(datoestab.getArea())) {
            gsw = 0; // saca tipo de estab Privado es "P" y no obliga a <5 deben ser SUMI
        }
        //Buscar Sexo
        Sexos buscarsexo = this.mi.getDatosSexo(Integer.parseInt(id_sexo_e)); // saca un registro a ser modificado
        modelo.put("buscarSexo", buscarsexo);
        modelo.put("sweco", sweco);
        modelo.put("swemerg", swemerg);
        modelo.put("fecha", fecha);
        modelo.put("estab", datoestab.getArea());

        if ("".equals(id_consultorio) || id_consultorio == null) {
            id_consultorio = Integer.toString(cliente.getId_consultorio());
        }
        if ("".equals(expedido) || expedido == null) {
            expedido = buscarPaciente.getId_estado();
        }
        String a = "/";
        String fecha_nacimiento = Integer.toString(xdia) + a + Integer.toString(xmes) + a + Integer.toString(xanio);
        Consultorios datosconsulta = this.mi.getDatosConsultorio(Integer.parseInt(id_consultorio));
        modelo.put("fec_nacimiento", fecha_nacimiento);

        modelo.put("accion", accion);
        modelo.put("accionE", accionE);
        modelo.put("sw1", request.getParameter("sw1"));
        modelo.put("id_paciente", id_paciente);
        modelo.put("id_reservacion", id_reservacion);
        modelo.put("id_consultorio", id_consultorio);
        modelo.put("tipo", datoestab.getArea());
        modelo.put("id_persona", id_persona);
        modelo.put("expedido", expedido);
        modelo.put("tipo_medico", Integer.toString(cliente.getId_cargo()));
        modelo.put("estimc", estimc);
        modelo.put("swinter", swinter);
        modelo.put("swenfer", swenfer);

        if (tipo_medico == null || "".equals(tipo_medico)) {
            tipo_medico = Integer.toString(cliente.getId_cargo());
        }

        if ("Recetar".equals(accion)) {
            String[] diasf = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "20", "25", "30", "40", "50", "60", "70", "80", "90"};
            int cuaderno = 0;

            Historiales datoc = new Historiales();
            datoc.setId_historial(Integer.parseInt(id_reservacion));
            modelo.put("dias", diasf);

            List listaH = this.mi.getHistorialLibros(datoc);

            Historiales datopac = (Historiales) listaH.get(0);
            modelo.put("fechamodifi", datopac.getFecha().getDate() + "/" + (datopac.getFecha().getMonth() + 1) + "/" + (datopac.getFecha().getYear() + 1900) + "     " + datopac.getFecha().getHours() + ":" + datopac.getFecha().getMinutes() + ":" + datopac.getFecha().getSeconds()); ///enviar la fechad e atencion
            if (datopac.getId_historial() == Integer.parseInt(id_reservacion)) {
                cuaderno = datopac.getId_tipo_documento() + datopac.getId_tipo_parentesco() + datopac.getId_pais() + datopac.getId_departamento() + datopac.getNum() + datopac.getId_provincia() + datopac.getId_localidad() + datopac.getId_reservacion() + datopac.getEmbarazo();
            }
            if (gsw == 1 && cuaderno == 0 && (datosconsulta.getId_cargo() < 10 || datosconsulta.getId_cargo() > 11 || datosconsulta.getId_cargo() > 12)) {
                return new ModelAndView("Aviso", "mensaje", "Primeramente debe llenar los Cuadernos del SNIS que corresponden");
            }
            Recetas datore = new Recetas();
            datore.setId_historial(Integer.parseInt(id_reservacion));
            datore.setCod_esta(cliente.getCod_esta());
            datore.setId_estado("%");
            datore.setId_farmacia(cliente.getId_farmacia());
            List listarRecetas = this.mi.getListarRecetas(datore);
            modelo.put("listarRecetas", listarRecetas);
            if ("P".equals(datoestab.getArea())) {
                datore.setId_estado("P");  //////////////para clinicas privadas
                List listarRecetas2 = this.mi.getListarRecetasMedico(datore);
                modelo.put("listarRecetas", listarRecetas2);
            }
            
            modelo.put("id_pedido", id_pedido);
            modelo.put("area", datoestab.getArea());

            datore.setId_estado("C");   //////getListarRecetasCaros
            List listarRecetaCaro = this.mi.getListarRecetasCaros(datore);
            if (listarRecetaCaro.size() > 0) {
                modelo.put("restrig", "si");
            }
            modelo.put("listarRecetaCaro", listarRecetaCaro);

            Medicamentos dato = new Medicamentos();
            dato.setId_persona(Integer.parseInt(id_persona));
            dato.setExpedido(expedido);
            dato.setCod_esta(cliente.getCod_esta());
            dato.setId_farmacia(cliente.getId_farmacia());
            List listarMedicamentosCot = this.mi.getListarMedicamentosCot(dato);
            modelo.put("listarMedicamentosCot", listarMedicamentosCot);
            return new ModelAndView("administrarhistoriales/RecetarPaciente", modelo);
        }

        if ("InternarPaciente".equals(accion)) {
            modelo.put("id_persona", id_persona);
            modelo.put("nombres", buscarPaciente.getNombres());
            modelo.put("hcl", Integer.toString(buscarPaciente.getHcl()));
            Medicamentos medid = new Medicamentos();
            medid.setCod_esta(cliente.getCod_esta());
            medid.setId_persona(Integer.parseInt(id_persona));
            List listarEnfermedadesCot = this.mi.getListarEnfermedadesCot(medid);
            modelo.put("listarEnfermedadesCot", listarEnfermedadesCot);
            List listarSalas = this.mi.getListarSalas();
            modelo.put("listarSalas", listarSalas);
            modelo.put("accionc", "cama");
            Historiales datomorbi = new Historiales();
            datomorbi.setId_reservacion(Integer.parseInt(id_reservacion));
            datomorbi.setCod_esta(cliente.getCod_esta());
            List listarmorbi = this.mi.getListaMorbi(datomorbi);   
            modelo.put("morbi", listarmorbi);
            modelo.put("id_sala", "0");
            modelo.put("id_cama", "0");

            Salas dsala = new Salas();
            dsala.setId_piso(cliente.getId_piso());
            dsala.setCod_esta(cliente.getCod_esta());
            List listarPisos = this.mi.getListarPisos(dsala);
            modelo.put("listarPisos", listarPisos);

            Consultorios aa = new Consultorios();
            aa.setCod_esta(cliente.getCod_esta());
            aa.setId_estado("E");
            aa.setSelec(1);
            List listarCargos = this.mi.getListarConsultoriosEmerg(aa);
            modelo.put("listarCargos", listarCargos);

            if (listarmorbi.isEmpty() != true) {
                modelo.put("terminar", "si");
            }
            return new ModelAndView("administrarhistoriales/InternarPaciente", modelo);
        }

        if ("SPS (exSUMI)".equals(accion)) {
            int cuaderno = 0;

            if (("".equals(id_reservacion) || id_reservacion == null) && "Enfer".equals(swenfer)) {
                return new ModelAndView("Aviso", "mensaje", "Primeramente debe llenar los Cuadernos del SNIS que corresponden");
            }

            Historiales datoc = new Historiales();
            datoc.setId_historial(Integer.parseInt(id_reservacion));
            List listaH = this.mi.getHistorialLibros(datoc);

            Historiales datopac = (Historiales) listaH.get(0);
            if (datopac.getId_historial() == Integer.parseInt(id_reservacion)) {
                cuaderno = datopac.getId_tipo_documento() + datopac.getId_tipo_parentesco() + datopac.getId_pais() + datopac.getId_departamento() + datopac.getNum() + datopac.getId_provincia() + datopac.getId_localidad() + datopac.getId_reservacion() + datopac.getEmbarazo();
            }
            if (cuaderno == 0 && (datosconsulta.getId_cargo() < 11 || datosconsulta.getId_cargo() > 12) && datosconsulta.getId_cargo() != 33) {
                return new ModelAndView("Aviso", "mensaje", "Primeramente debe llenar los Cuadernos del SNIS que corresponden");
            }

            Prestaciones prestac = new Prestaciones();
            prestac.setId_historial(Integer.parseInt(id_reservacion));
            prestac.setId_persona(cliente.getId_persona());
            prestac.setCod_esta(cliente.getCod_esta());

            if (datosconsultorio.getId_cargo() == 7) {
                List listarRecetas = this.mi.getListarSumiRecetasI(prestac);
                modelo.put("listarRecetas", listarRecetas);
            } else {
                List listarRecetas = this.mi.getListarSumiRecetas(prestac);
                modelo.put("listarRecetas", listarRecetas);
            }
            Recetas datore = new Recetas();
            datore.setId_historial(Integer.parseInt(id_reservacion));
            datore.setCod_esta(cliente.getCod_esta());
            datore.setId_estado("%");
            datore.setId_farmacia(cliente.getId_farmacia());
            List listarRecetaSumi = this.mi.getListarRecetas(datore);
            modelo.put("listarRecetaSumi", listarRecetaSumi);
            modelo.put("edad", Integer.toString(datopac.getEdad()));
            
            List listPrestacionCot = this.mi.getListarPrestacionesCot(prestac);
            modelo.put("listarPrestacionesCot", listPrestacionCot);
            modelo.put("medica", "si");
            return new ModelAndView("administrarhistoriales/RecetarSumiPaciente", modelo);
        }

        if ("Recepcion".equals(accion)) {
            String valor_1 = request.getParameter("valor_1");
            Pacientes paciente1 = new Pacientes();
            paciente1.setId_pedido(Integer.parseInt(id_pedido));
            paciente1.setCod_esta(cliente.getCod_esta());
            Pacientes paciente = this.mi.getDatosPedido(paciente1);
            String anio_r = Integer.toString(paciente.getFec_registro().getYear() + 1900);
            String mes_r = Integer.toString(paciente.getFec_registro().getMonth() + 1);
            String dia_r = Integer.toString(paciente.getFec_registro().getDate());
            valor_1 = anio_r + "-" + mes_r + "-" + dia_r;

            Recetas midato = new Recetas();
            midato.setId_pedido(Integer.parseInt(id_pedido));
            midato.setId_prestacion(cliente.getCod_esta());
            midato.setCod_esta(cliente.getCod_esta());
            midato.setId_farmacia(cliente.getId_farmacia());
            midato.setExpedido("A");
            Medicamentos medid = new Medicamentos();
            medid.setCod_esta(cliente.getCod_esta());
            List listarprog = this.mi.getListarProgramas(medid);
            modelo.put("listarProg", listarprog);
            List listarKardex = this.mi.getListarKardex(midato);////getListarKardexAjus
            modelo.put("listarKardex", listarKardex);
            modelo.put("id_pedido", id_pedido);
            modelo.put("recepcion", "no");
            modelo.put("valor_1", valor_1);
            modelo.put("id_tipo_far", id_tipo_far);
            modelo.put("datos", paciente);
            if ("4".equals(id_tipo_far)) {
                Recetas dator = (Recetas) listarKardex.get(0);
                modelo.put("id_farmacia", Integer.toString(dator.getId_farmacia()));
                modelo.put("expedido", dator.getExpedido()); ///29/01/2016 se auamenta porque grababa en kardex con expedido = 'R'
            }
            return new ModelAndView("administrarfarmacias/RecepcionMedicamento", modelo);
        }

        if ("Recetar Asegurado".equals(accion)) {
            String id_seguro = request.getParameter("id_seguro");
            String[] dias2 = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "20", "25", "30", "40", "50", "60", "70", "80", "90"};
            int cuaderno = 0;
            int cant = 0;
            if (("".equals(id_reservacion) || id_reservacion == null) && "Enfer".equals(swenfer)) {
                return new ModelAndView("Aviso", "mensaje", "Primeramente debe llenar los Cuadernos del SNIS que corresponden");
            }

            Historiales datoc = new Historiales();
            datoc.setId_historial(Integer.parseInt(id_reservacion));
            List listaH = this.mi.getHistorialLibros(datoc);

            Historiales datopac = (Historiales) listaH.get(0);
            if (datopac.getId_historial() == Integer.parseInt(id_reservacion)) {
                cuaderno = datopac.getId_tipo_documento() + datopac.getId_tipo_parentesco() + datopac.getId_pais() + datopac.getId_departamento() + datopac.getNum() + datopac.getId_provincia() + datopac.getId_localidad() + datopac.getId_reservacion() + datopac.getEmbarazo();
            }
            if (gsw == 1 && cuaderno == 0 && (datosconsulta.getId_cargo() < 10 || datosconsulta.getId_cargo() > 11 || datosconsulta.getId_cargo() > 12)) {
                if (datoest.getCod_esta() == 200010) {
                    Consultorios datosconsul = this.mi.getDatosConsultorio(cliente.getId_consultorio());///para residentes 21/09/2014 
                    if (cliente.getId_almacen() == 1) {
                        return new ModelAndView("Aviso", "mensaje", "Primeramente debe llenar los Cuadernos 'C.Emergencias' que esta a lado");
                    } else {
                        return new ModelAndView("Aviso", "mensaje", "Primeramente debe llenar los Cuadernos del SNISs que corresponden");
                    }
                } else {
                    return new ModelAndView("Aviso", "mensaje", "Primeramente debe llenar los Cuadernos del SNIS que corresponden");
                }
            }
            Recetas datore = new Recetas();
            datore.setId_historial(Integer.parseInt(id_reservacion));
            datore.setId_paciente(Integer.parseInt(id_paciente));
            datore.setId_persona(Integer.parseInt(id_persona));
            datore.setId_farmacia(cliente.getId_farmacia());
            datore.setCod_esta(cliente.getCod_esta());
            datore.setId_programa(cliente.getId_consultorio());
            datore.setId_estado("%");
            List listarRecetas = this.mi.getListarRecetas(datore);
            modelo.put("listarRecetas", listarRecetas);

            if (listarRecetas.size() > 0 || cliente.getId_especialidad() == 98) {
                cant = 1; //// si existe recetas cambia a 1  
            }
            //////datoest muestra del CNS
            if (datoest.getCod_esta() == 200010 && cant > 0 && (cliente.getId_almacen() == 1 || cliente.getId_especialidad() == 99 || cliente.getId_especialidad() == 98)) {
                double minutex = 0;

                if (cliente.getId_especialidad() == 98) {
                    minutex = 40;
                } else {
                    Recetas datorec = (Recetas) listarRecetas.get(0);

                    long fecharec = datorec.getFecha().getTime();
                    long fechanow = fechaact.getTime();
                    long diferencia = fechanow - fecharec;
                    String reportDate = df.format(datorec.getFecha());
                    double horasex = Math.floor(diferencia / (1000 * 60 * 60));
                    minutex = Math.floor(diferencia / (1000 * 60));
                }

                if (minutex > 30) {

                    Cuadernos datoc5 = new Cuadernos();
                    datoc5.setId_historial(Integer.parseInt(id_reservacion));
                    datoc5.setCod_esta(cliente.getCod_esta());
                    List C5 = this.mi.getPacienteCuaderno5(datoc5);
                    // List C5 = this.mi.getPacienteCuaderno5(Integer.parseInt(id_reservacion));
                    if (C5.isEmpty() != true) {
                        return new ModelAndView("Aviso", "mensaje", "El Paciente ha sido PRE-INTERNADO, busquelo pacientes en Evaluacion");
                    }

                    Cuadernos dato5 = new Cuadernos();
                    dato5.setCod_esta(cliente.getCod_esta());  ///16-01-2016
                    dato5.setAspecto("M");
                    dato5.setId_historial(Integer.parseInt(id_reservacion));
                    List listaCie = this.mi.getVerCuaderno1CieMorbi(dato5);
                    String CIE10i = "";
                    String CIE10e = "";
                    for (int i = 0; i < listaCie.size(); i++) {
                        Cuadernos cie10 = (Cuadernos) listaCie.get(i);
                        if ("B".equals(cie10.getRegistro())) {
                            CIE10i = cie10.getTipodent() + ";" + CIE10i;
                            modelo.put("CieI", CIE10i);
                        } else {
                            CIE10e = cie10.getTipodent() + ";" + CIE10e;
                            modelo.put("CieE", CIE10e);
                        }
                    }
                    if ("0".equals(id_persona)) {
                        id_persona = Integer.toString(datosHistorial.getId_persona());
                    }
                    if ("0".equals(id_consultorio)) {
                        id_consultorio = Integer.toString(datosHistorial.getId_consultorio());
                    }
                    if (datoestab.getCod_esta() == 200010) {  /////31-08-2015
                        dato5.setId_persona(Integer.parseInt(id_persona));
                        dato5.setId_consultorio(Integer.parseInt(id_consultorio));
                    } else {
                        if (dato5.getId_persona() == 0) {  /////23-01-2016
                            dato5.setId_persona(datosHistorial.getId_persona());
                        } else {
                            dato5.setId_persona(cliente.getId_persona());
                        }
                    }
                    dato5.setId_historial(Integer.parseInt(id_reservacion));
                    dato5.setServicio(Integer.parseInt(id_consultorio));
                    dato5.setReferido("");
                    dato5.setContraref("");
                    dato5.setDiagnostico_ing(CIE10i);
                    dato5.setDiagnostico("");
                    dato5.setDiasi(0);
                    dato5.setDiasc(0);
                    dato5.setId_estado("A");
                    dato5.setIngreso(0);
                    dato5.setCirugia(0);
                    dato5.setAnastecia(0);
                    dato5.setEgreso(0);
                    dato5.setTipo_egreso(0);
                    dato5.setFallecido(0);
                    dato5.setFallecidom5(0);
                    dato5.setFallecidoy5(9);
                    dato5.setId_sala(0);
                    dato5.setId_cama(0);
                    dato5.setId_piso(0);
                    dato5.setId_por(cliente.getId_persona());
                    dato5.setCod_esta(cliente.getCod_esta());
                    this.mi.setCrearCuaderno5(dato5);

                    Historiales datoi = new Historiales();
                    datoi.setId_persona(Integer.parseInt(id_persona));
                    datoi.setAccion("R");
                    datoi.setId_historial(Integer.parseInt(id_reservacion));
                    datoi.setId_cargo(6);  ////internado por sistema observacion emergnecias
                    datoi.setId_ecivil(6);  ////internado por sistema observacion emergnecias
                    if (cliente.getId_especialidad() == 98) {
                        datoi.setId_cargo(7);  ////internado por sistema observacion emergnecias 
                        datoi.setId_ecivil(7);  ////internado por sistema observacion emergnecias 
                    }
                    datoi.setExpedido("B");
                    datoi.setCod_esta(cliente.getCod_esta());
                    this.mi.setModificarMorbilidad(datoi);
                    this.mi.setModificarInternado(datoi);

                    datoi.setId_consultorio(Integer.parseInt(request.getParameter("id_consultorio")));
                    datoi.setId_persona(Integer.parseInt(id_persona));
                    datoi.setDiagnostico("Internar Paciente");
                    datoi.setId_estado("A");
                    datoi.setId_cargo(0);
                    datoi.setId_sexo(0);
                    datoi.setAccion("A");
                    datoi.setId_por(cliente.getId_persona());
                    if (cliente.getId_especialidad() == 99 || cliente.getId_especialidad() == 98) {
                        datoi.setId_persona(datosHistorial.getId_persona());
                        datoi.setId_consultorio(datosHistorial.getId_consultorio());
                    }

                    this.mi.setCrearInternado(datoi);    /////setCrearInternado2   para los obreros
                    datoi.setAccion("X");
                    datoi.setDiagnostico(datosHistorial.getDiagnostico());
                    this.mi.setCrearInternado(datoi);    /////setCrearInternado   nuevamente con otro id_historia

                    List C52 = this.mi.getPacienteCuaderno5(datoc5);

                    if (C52.isEmpty() != true) {
                        Cuadernos Inter = (Cuadernos) C52.get(0);
                        modelo.put("inter", Integer.toString(Inter.getId_historia()));
                        modelo.put("id_historia", Integer.toString(Inter.getId_historia()));
                    }

                    datore.setId_estado("C");   //////getListarRecetasCaros
                    List listarRecetaCaro = this.mi.getListarRecetasCaros(datore);
                    if (listarRecetaCaro.size() > 0) {
                        modelo.put("restrig", "si");
                    }
                    modelo.put("listarRecetaCaro", listarRecetaCaro);

                    Medicamentos dato = new Medicamentos();
                    dato.setId_persona(Integer.parseInt(id_persona));
                    dato.setExpedido(expedido);
                    dato.setCod_esta(cliente.getCod_esta());
                    dato.setId_farmacia(cliente.getId_farmacia());
                    List listarMedicamentosCot = this.mi.getListarMedicamentosCotb1(dato);
                    modelo.put("listarMedicamentosCot", listarMedicamentosCot);

                    datore.setId_estado("U");
                    datore.setId_programa(cliente.getId_consultorio());
                    List listarRec = this.mi.getListarUltReceta(datore);  ////getListarUltReceta
                    modelo.put("nrecant", Integer.toString(listarRec.size()));

                    modelo.put("dias", dias2);
                    modelo.put("id_pedido", id_pedido);
                    modelo.put("id_seguro", id_seguro);
                    modelo.put("spam", "si");
                    modelo.put("sweco", sweco);
                    modelo.put("fecha", fecha);
                    modelo.put("expedido", expedido);

                    //Recetas datore=new Recetas();
                    datore.setId_historial(Integer.parseInt(id_reservacion));
                    datore.setCod_esta(cliente.getCod_esta());
                    datore.setId_farmacia(cliente.getId_farmacia());
                    datore.setId_estado("%");
                    List listarRecetasInt = this.mi.getListarRecetas(datore);
                    modelo.put("listarRecetas", listarRecetasInt);

                    return new ModelAndView("administrarhistoriales/RecetarPacienteExtInt", modelo);

                }/////fin if(datoestab.getCod_esta()==200010 && listarRecetas.size()>0){
            }

            datore.setId_estado("C");   //////getListarRecetasCaros
            List listarRecetaCaro = this.mi.getListarRecetasCaros(datore);
            if (listarRecetaCaro.size() > 0) {
                modelo.put("restrig", "si");
            }
            modelo.put("listarRecetaCaro", listarRecetaCaro);

            Medicamentos dato = new Medicamentos();
            dato.setId_persona(Integer.parseInt(id_persona));
            dato.setExpedido(expedido);
            dato.setCod_esta(cliente.getCod_esta());
            dato.setId_farmacia(cliente.getId_farmacia());
            List listarMedicamentosCot = this.mi.getListarMedicamentosCotb1(dato);
            modelo.put("listarMedicamentosCot", listarMedicamentosCot);

            datore.setId_estado("U");
            datore.setId_programa(cliente.getId_consultorio());
            List listarRec = this.mi.getListarUltReceta(datore);  ////getListarUltReceta
            modelo.put("nrecant", Integer.toString(listarRec.size()));

            modelo.put("dias", dias2);
            modelo.put("id_pedido", id_pedido);
            modelo.put("id_seguro", id_seguro);
            modelo.put("spam", "si");
            modelo.put("sweco", sweco);
            modelo.put("fecha", fecha);
            modelo.put("expedido", expedido);
            return new ModelAndView("administrarhistoriales/RecetarPaciente", modelo);
        }

        if ("Terminar".equals(accion)) {
            // mostramos la lista nueva de pacientes
            Personas persona = this.mi.getBuscarPersona(cliente.getId_persona()); // saca un registro a ser modificado
            List listarPersonas = this.mi.getListarPersonas(persona);
            modelo.put("listarPersonas", listarPersonas);

            if ("Enfer".equals(swenfer)) {
                Pacientes paciente = new Pacientes();
                paciente.setId_estado("A");
                paciente.setId_rubro(5);
                paciente.setId_localidad(cliente.getCod_esta());
                List listarCo = this.mi.getListarCobroRubro(paciente);
                modelo.put("milista", listarCo);

                // pacientes a cobrar enviados por odontologia
                paciente.setId_estado("C");
                List listarE = this.mi.getListarCobroRubro(paciente);
                modelo.put("listaOdon", listarE);

                Cuadernos cua6 = new Cuadernos();
                cua6.setCod_esta(cliente.getCod_esta());  ///16-01-2016
                cua6.setId_persona(Integer.parseInt(id_persona));
                List listarAtendidos = this.mi.getListaPacientesCuaderno6(cua6);
                modelo.put("listarAtendidos", listarAtendidos);
                List listarSeguros = this.mi.getListarSeguros("A");
                modelo.put("listaPacAfi", listarSeguros);
                return new ModelAndView("administrarenfermeria/ListarCobroReserva", modelo);
            }

            //Consultorios datosconsul = this.mi.getDatosConsultorio(persona.getId_consultorio()) ; // saca un registro a ser modificado
            Consultorios datosconsul = this.mi.getDatosConsultorio(cliente.getId_consultorio()); // para admisnitrador muestra todas las opciones
            modelo.put("tipo_medico", Integer.toString(datosconsul.getId_cargo()));

            Historiales datox = new Historiales();
            datox.setId_persona(cliente.getId_persona());
            datox.setId_por(cliente.getId_persona());
            datox.setCod_esta(cliente.getCod_esta());
            datox.setFecha_ini(fecha1);
            datox.setFecha_fin(fecha1);
            datox.setAccion("R");

            if (cliente.getId_almacen() == 1 && (cliente.getId_especialidad() != 99 && cliente.getId_especialidad() != 98)) {
                id_consultorio = Integer.toString(cliente.getId_consultorio());
                id_persona = Integer.toString(cliente.getId_persona());
                datox.setId_consultorio(cliente.getId_consultorio());
            }
            if (datosconsul.getId_especialidad() != 99 && datosconsul.getId_especialidad() != 98) {
                List listarAtendidos = this.mi.getHistorialAtendidos(datox);
                modelo.put("milistaAten", listarAtendidos);
            }
            if (datosconsul.getId_especialidad() == 99 || cliente.getId_almacen() == 1) {///para Obrero
                datox.setAccion("Q");  ////getHistorialAtendidosResid
                List listarAtendidos = this.mi.getHistorialAtendidosResid(datox);
                modelo.put("milistaAten", listarAtendidos);
            }
            if (datosconsul.getId_especialidad() == 98) {///para Obrero
                datox.setAccion("1");  ////getHistorialAtendidosHemo
                List listarAtendidos = this.mi.getHistorialAtendidosHemo(datox);
                modelo.put("milistaAten", listarAtendidos);
            }

            modelo.put("especialidad", Integer.toString(datosconsul.getId_especialidad()));

            datox.setAccion("W"); ////////getInternadosCajaObservacionPiso
            List listarObservacion = this.mi.getInternadosCajaObservacion(datox);   ////getInternadosCajaObservacion
            modelo.put("listarObservacion", listarObservacion);
            if (cliente.getId_especialidad() == 98) {///para Hemodalisis
                datox.setAccion("2");  ////getInternadosCajaObservacionHemo
                List listarObservacion2 = this.mi.getInternadosCajaObservacionHemo(datox);   ////getInternadosCajaObservacion
                modelo.put("listarObservacion", listarObservacion2);
            }

            datox.setId_consultorio(persona.getId_consultorio());
            datox.setId_departamento(persona.getId_consultorio());
            datox.setFecha_ini(fecha1);
            datox.setFecha_fin(fecha1);
            datox.setCod_esta(cliente.getCod_esta());
            datox.setId_localidad(cliente.getId_persona());
            datox.setAccion("T");
            datox.setId_estado("C");
            datox.setAccion("P");
            List listarResvFicha = this.mi.getListarReserFichas(datox);
            modelo.put("listaResvF", listarResvFicha);

            if ("3".equals(cliente.getTipo())) {///para HGSJDD
                datox.setId_persona(persona.getId_persona());
                datox.setAccion("P");
                if (datoest.getCod_esta() == 200010) {///para HGSJDD
                    datox.setAccion("V");///09/09/2014 
                }
                if (datosconsul.getId_especialidad() == 99) {///para Obrero
                    datox.setAccion("R");  ////getListarReservacionesResid
                }
                List listarPaises = this.mi.getListarReservaciones(datox);
                modelo.put("milista", listarPaises);
            } else {
                datox.setAccion("K");  ////getListarReservacionesMedico solo sector publico para sus reservaciones de medicos y no de afiliacion
                List listarPaises4 = this.mi.getListarReservaciones(datox);
                modelo.put("milista", listarPaises4);
            }

            modelo.put("id_persona", Integer.toString(cliente.getId_persona()));
            modelo.put("id_consultorio", Integer.toString(persona.getId_consultorio()));
            modelo.put("estimc", estimc);

            if (cliente.getId_almacen() == 1) {///para Obrero y demas
                return new ModelAndView("administrarhistoriales/ListarReservas99", modelo);
            } else {
                if (datosconsul.getId_especialidad() == 98) {///para Obrero
                    return new ModelAndView("administrarhistoriales/ListarReservasHemo", modelo);
                } else {
                    return new ModelAndView("administrarhistoriales/ListarReservas", modelo);
                }
            }
        }
        if ("cie10".equals(revision)) {
            modelo.put("id_consultorio", Integer.toString(cliente.getId_consultorio()));
            modelo.put("tipo_medico", "23");
            modelo.put("revision", revision);
        }
        return new ModelAndView("administrarhistoriales/PlanAccionPaciente", modelo);

    }
}
