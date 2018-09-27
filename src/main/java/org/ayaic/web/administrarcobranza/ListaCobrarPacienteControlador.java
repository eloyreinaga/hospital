package org.ayaic.web.administrarcobranza;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Consultorios;
import org.ayaic.domain.Costos;
import org.ayaic.domain.Cuadernos;
import org.ayaic.domain.Departamentos;
import org.ayaic.domain.Detalle;
import org.ayaic.domain.Historiales;
import org.ayaic.domain.Laboratorios;
import org.ayaic.domain.Localidades;
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.Personas;
import org.ayaic.domain.Rubros;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ListaCobrarPacienteControlador {

    private final MiFacade mi;

    public ListaCobrarPacienteControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/ListaCobrarPaciente.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();
        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String accion1 = request.getParameter("accion1");
        String accioncobros = request.getParameter("accioncobros");
        String accioncob = request.getParameter("accioncob");
        String accionFact = request.getParameter("accionFact");
        String id_pedido = request.getParameter("id_pedido");
        String id_seguro = request.getParameter("id_seguro");
        String id_persona = request.getParameter("id_persona");
        String id_paciente = request.getParameter("id_paciente");
        String id_reservacion = request.getParameter("id_reservacion");
        String id_consultorio = request.getParameter("id_consultorio");
        String id_laboratorio = request.getParameter("id_laboratorio");
        String id_rubro = request.getParameter("id_rubro");
        String id_costo = request.getParameter("id_costo");
        String cantidad = request.getParameter("cantidad");
        String indicacion = request.getParameter("indicacion");
        String costo = request.getParameter("costo");
        String expedido = request.getParameter("expedido");
        String sw = request.getParameter("sw");
        String sweco = request.getParameter("sweco");
        String nombres = request.getParameter("nombres");
        String nombrecos = request.getParameter("nombrecos");
        String num_cladoc = request.getParameter("num_cladoc");
        String costo_unit = request.getParameter("precio");
        String nit = request.getParameter("nit");
        String tipo_medico = request.getParameter("tipo_medico");
        String acuenta = request.getParameter("acuenta");
        String id_historia = request.getParameter("id_historia");
        String fecha = request.getParameter("fecha");
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String ip = request.getRemoteAddr();
        String host = request.getRemoteHost();
        String[] dias = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
        String[] meses = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        Date fecha1 = new Date();
        Date fechaact = new Date();
        int anioq = fecha1.getYear() + 1900;
        String[] anios = {Integer.toString(anioq), Integer.toString(anioq - 1), Integer.toString(anioq - 2), Integer.toString(anioq - 3), Integer.toString(anioq - 4), Integer.toString(anioq - 5), Integer.toString(anioq - 6), Integer.toString(anioq - 7), Integer.toString(anioq - 8), Integer.toString(anioq - 9), Integer.toString(anioq - 10), Integer.toString(anioq - 11), Integer.toString(anioq - 12), Integer.toString(anioq - 13), Integer.toString(anioq - 14), Integer.toString(anioq - 15), Integer.toString(anioq - 16), Integer.toString(anioq - 17), Integer.toString(anioq - 18), Integer.toString(anioq - 19), Integer.toString(anioq - 20), Integer.toString(anioq - 21), Integer.toString(anioq - 22), Integer.toString(anioq - 23), Integer.toString(anioq - 24), Integer.toString(anioq - 25), Integer.toString(anioq - 26), Integer.toString(anioq - 27), Integer.toString(anioq - 28), Integer.toString(anioq - 29), Integer.toString(anioq - 30), Integer.toString(anioq - 31), Integer.toString(anioq - 32), Integer.toString(anioq - 33), Integer.toString(anioq - 34), Integer.toString(anioq - 35), Integer.toString(anioq - 36), Integer.toString(anioq - 37), Integer.toString(anioq - 38), Integer.toString(anioq - 39), Integer.toString(anioq - 40), Integer.toString(anioq - 41), Integer.toString(anioq - 42), Integer.toString(anioq - 43), Integer.toString(anioq - 44), Integer.toString(anioq - 45), Integer.toString(anioq - 46), Integer.toString(anioq - 47), Integer.toString(anioq - 48), Integer.toString(anioq - 49), Integer.toString(anioq - 50), Integer.toString(anioq - 51), Integer.toString(anioq - 52), Integer.toString(anioq - 53), Integer.toString(anioq - 54), Integer.toString(anioq - 55), Integer.toString(anioq - 56), Integer.toString(anioq - 57), Integer.toString(anioq - 58), Integer.toString(anioq - 59), Integer.toString(anioq - 60), Integer.toString(anioq - 61), Integer.toString(anioq - 62), Integer.toString(anioq - 63), Integer.toString(anioq - 64), Integer.toString(anioq - 65), Integer.toString(anioq - 66), Integer.toString(anioq - 67), Integer.toString(anioq - 68), Integer.toString(anioq - 69), Integer.toString(anioq - 70), Integer.toString(anioq - 71), Integer.toString(anioq - 72), Integer.toString(anioq - 73), Integer.toString(anioq - 74), Integer.toString(anioq - 75), Integer.toString(anioq - 76), Integer.toString(anioq - 77), Integer.toString(anioq - 78), Integer.toString(anioq - 79), Integer.toString(anioq - 80), Integer.toString(anioq - 81), Integer.toString(anioq - 82), Integer.toString(anioq - 83), Integer.toString(anioq - 84), Integer.toString(anioq - 85), Integer.toString(anioq - 86), Integer.toString(anioq - 87), Integer.toString(anioq - 88), Integer.toString(anioq - 89), Integer.toString(anioq - 90), Integer.toString(anioq - 91), Integer.toString(anioq - 92), Integer.toString(anioq - 93), Integer.toString(anioq - 94), Integer.toString(anioq - 95), Integer.toString(anioq - 96), Integer.toString(anioq - 97), Integer.toString(anioq - 98), Integer.toString(anioq - 99), Integer.toString(anioq - 100), Integer.toString(anioq - 101), Integer.toString(anioq - 102), Integer.toString(anioq - 103), Integer.toString(anioq - 104), Integer.toString(anioq - 105), Integer.toString(anioq - 106), Integer.toString(anioq - 107), Integer.toString(anioq - 108), Integer.toString(anioq - 109), Integer.toString(anioq - 110)};
        double total = 0;
        int inter = 0, sw2 = 0;

        Localidades local = new Localidades();
        List Estab1 = this.mi.getListarEsta(local);
        Localidades datoestab = (Localidades) Estab1.get(0);
        local.setArea("U");  ////getListarEstaUsua
        local.setCod_esta(cliente.getCod_esta());
        List Estab2 = this.mi.getListarEstaUsua(local);
        Localidades datoestab2 = (Localidades) Estab2.get(0);

        modelo.put("anio", Integer.toString(fecha1.getYear() + 1900));
        modelo.put("mes", Integer.toString(fecha1.getMonth() + 1));
        modelo.put("dia", Integer.toString(fecha1.getDate()));
        modelo.put("anios", anios);
        modelo.put("meses", meses);
        modelo.put("dias", dias);

        modelo.put("id_paciente", id_paciente);
        modelo.put("nombres", nombres);
        modelo.put("id_reservacion", id_reservacion);
        modelo.put("id_consultorio", id_consultorio);
        modelo.put("id_laboratorio", id_laboratorio);
        modelo.put("accioncobros", accioncobros);
        modelo.put("accionFact", accionFact);
        modelo.put("accioncob", accioncob);
        modelo.put("id_persona", id_persona);
        modelo.put("id_seguro", id_seguro);
        modelo.put("expedido", expedido);
        modelo.put("tipo_medico", tipo_medico);
        modelo.put("sweco", sweco);
        modelo.put("fecha", fecha);
        modelo.put("nit", nit);
        modelo.put("sw", sw);
        modelo.put("costo", costo);

        if (!"".equals(id_pedido) && id_pedido != null) {
            Pacientes paciente1 = new Pacientes();
            paciente1.setId_pedido(Integer.parseInt(id_pedido));
            paciente1.setCod_esta(cliente.getCod_esta());
            Pacientes paciente = this.mi.getDatosPedido(paciente1); //////////////    
        }

        Consultorios datosconsultorio = this.mi.getDatosConsultorio(cliente.getId_consultorio()); // saca un registro a ser modificado
        Pacientes buscarPaciente = this.mi.getDatosPaciente(Integer.parseInt(id_paciente)); // saca un registro a ser modificado
        if ("".equals(nombres) || nombres == null) {
            nombres = "S/N";
        } else {
            nombres = nombres.toUpperCase();
        }

        if ("Buscar".equals(accion1)) {
            nombrecos = nombrecos.replaceAll("\\s", "%");
            nombrecos = "%" + nombrecos.toUpperCase() + "%";

            Costos datoq = new Costos();
            if (id_rubro == null || id_rubro == "" || "0".equals(id_rubro)) {
                datoq.setId_rubro(0);
                datoq.setId_prestacion(5000);
            } else {
                datoq.setId_rubro(Integer.parseInt(id_rubro));
                datoq.setId_prestacion(Integer.parseInt(id_rubro));
            }

            datoq.setId_estado("%");
            datoq.setTipo(0);
            datoq.setId_persona(5000);
            //datoq.setMuestra("N");
            datoq.setId_nivel(0);
            datoq.setEmergencias(1);
            datoq.setId_laboratorio(0);
            datoq.setId_historial(5000);
            datoq.setCod_esta(cliente.getCod_esta());///para todos 20-02-2017
            datoq.setNormales(nombrecos.toUpperCase());
            datoq.setMuestra("U");
            List listarCostos = this.mi.getListarNombreCosto(datoq);  ////getListarNombreCosto
            modelo.put("listarCostos", listarCostos);
            modelo.put("id_pedido", id_pedido);
            modelo.put("id_rubro", id_rubro);
            List listarRubros = this.mi.getListaRubro();
            modelo.put("listarRubros", listarRubros);
            Detalle datodet = new Detalle();
            datodet.setId_pedido(Integer.parseInt(id_pedido));
            datodet.setId_carpeta(cliente.getCod_esta());
            datodet.setCod_esta(cliente.getCod_esta());
            List listarcobros = this.mi.getListarCobroDetalle(datodet);
            modelo.put("listarcobros", listarcobros);
            return new ModelAndView("administrarcobranza/ListaCobroPaciente", modelo);
        }

        if ("Contado".equals(accion) || "Factura".equals(accion)) {

            Pacientes paciente = new Pacientes();
            paciente.setId_paciente(Integer.parseInt(id_paciente));

            modelo.put("accion", accion);
            if ("Factura".equals(accion)) {
                modelo.put("accionFact", accion);
            }

            accioncobros = "Cobros";
            modelo.put("accioncobros", accioncobros);
            modelo.put("sw", "3");
            modelo.put("id_pedido", id_pedido);
            modelo.put("id_rubro", id_rubro);

            List listarRubros = this.mi.getListaRubro();
            modelo.put("listarRubros", listarRubros);
            if (!"0".equals(nit) && nit != null) {
                if (!buscarPaciente.getCarnet().equals(nit) && !buscarPaciente.getNombres().equals(nombres)) {
                    Pacientes pacnit = new Pacientes();
                    pacnit.setId_paciente(Integer.parseInt(id_paciente));
                    pacnit.setCadena1(nit.trim());
                    pacnit.setCadena2(nombres.trim());
                    this.mi.setModificaPacienteNit(pacnit);
                }
            }
            paciente.setNombres(nombres);
            paciente.setPrecio_total(0);
            paciente.setNum_cladoc(num_cladoc);
            paciente.setNit(nit);
            //colocamos en la tabla pedidos el id_paciente para saber a que paciente se le esta cobrando, tambien en id_historal
            paciente.setId_dispensa(cliente.getId_persona());
            paciente.setId_paciente(Integer.parseInt(id_paciente));
            paciente.setTipo("C");
            paciente.setId_factura(0);/////  0 sin factura, 1 con factura, 2, 3, 4, para num correlativo, entradas, ajuste+ y ajuste-
            paciente.setId_estado("E");
            modelo.put("datos", paciente);
            //this.mi.setCrearPedido(paciente) ;        
            //id_pedido= Integer.toString(this.mi.getNumPedido(paciente));
            //modelo.put("id_pedido", id_pedido);      
            return new ModelAndView("administrarcobranza/ListaCobroPaciente", modelo);
        }

        if ("Credito".equals(accion)) {

            Pacientes paciente = new Pacientes();
            paciente.setId_dispensa(cliente.getId_persona());
            paciente.setId_paciente(Integer.parseInt(id_paciente));
            modelo.put("accion", accion);
            modelo.put("sw", "3");
            modelo.put("id_pedido", id_pedido);
            modelo.put("id_rubro", id_rubro);
            modelo.put("acuenta", "0");

            List listarRubros = this.mi.getListaRubro();
            modelo.put("listarRubros", listarRubros);

            paciente.setNombres(nombres);
            paciente.setPrecio_total(0);
            paciente.setNum_cladoc(num_cladoc);
            paciente.setNit(nit);
            paciente.setTipo("C");
            paciente.setId_factura(0);/////  0 sin factura, 1 con factura, 2, 3, 4, para num correlativo, entradas, ajuste+ y ajuste-

            paciente.setId_estado("C");
            modelo.put("datos", paciente);
            //this.mi.setCrearPedido(paciente) ;        
            //id_pedido= Integer.toString(this.mi.getNumPedido());
            //modelo.put("id_pedido", id_pedido);

            return new ModelAndView("administrarcobranza/ListaCobroPaciente", modelo);
        }

        if ("Adicionar".equals(accion)) {
            if (id_rubro == null || id_rubro == "" || "".equals(id_rubro)) {
                id_rubro = "2";
            }
            Pacientes dato = new Pacientes();
            dato.setId_rubro(Integer.parseInt(id_rubro));
            dato.setId_persona(100);
            dato.setId_provincia(Integer.parseInt(id_reservacion));
            List listaCobrosTots = this.mi.getDatosPedidoRubro(dato);
            for (int j = 0; j < listaCobrosTots.size(); j++) {
                Pacientes datoped = (Pacientes) listaCobrosTots.get(j);
                total += datoped.getPrecio_total();
                //id_pedido=Integer.toString(datoped.getId_pedido());
            }
            // en caso de no haber nada en pedidos crea el pedid y los detalle

            Costos datoq = new Costos();
            datoq.setId_rubro(Integer.parseInt(id_rubro));
            datoq.setId_prestacion(Integer.parseInt(id_rubro));
            datoq.setId_estado("%");
            datoq.setTipo(0);
            datoq.setId_persona(5000);
            datoq.setEmergencias(0);
            datoq.setCod_esta(cliente.getCod_esta());///para todos 20-02-2017
            List listarCostos = this.mi.getListarCostos(datoq);
            modelo.put("listarCostos", listarCostos);
            List listarRubros = this.mi.getListaRubro();
            modelo.put("listarRubros", listarRubros);

        }

        if ("Cobros".equals(accion)) {
            accioncobros = "Cobros";

            if (request.getParameter("id_rubro") != null) {
                Costos datoq = new Costos();
                datoq.setId_rubro(Integer.parseInt(id_rubro));
                datoq.setId_prestacion(Integer.parseInt(id_rubro));
                datoq.setId_estado("%");
                datoq.setTipo(0);
                datoq.setId_persona(5000);
                //datoq.setTipo(Integer.parseInt(id_laboratorio));
                //datoq.setId_persona(Integer.parseInt(id_laboratorio));    
                datoq.setEmergencias(0);
                datoq.setCod_esta(cliente.getCod_esta());///para todos 20-02-2017
                List listarCostos = this.mi.getListarCostos(datoq);
                modelo.put("listarCostos", listarCostos);
                modelo.put("accion", accion);
//        modelo.put("acuenta", acuenta);
                modelo.put("sw", request.getParameter("sw"));
                modelo.put("id_pedido", id_pedido);
                modelo.put("id_rubro", id_rubro);
                modelo.put("expedido", expedido);
                modelo.put("id_paciente", id_paciente);
                modelo.put("id_reservacion", id_reservacion);
                modelo.put("id_consultorio", id_consultorio);
                modelo.put("id_persona", id_persona);
                modelo.put("tipo_medico", tipo_medico);
//        modelo.put("datos", paciente);
            }
            Pacientes paciente = new Pacientes();
            paciente.setCod_esta(cliente.getCod_esta());
            num_cladoc = Long.toString(this.mi.getNumClaDoc(paciente));
            paciente.setId_dispensa(cliente.getId_persona());
            paciente.setId_paciente(Integer.parseInt(id_paciente));
            paciente.setId_carpeta(Integer.parseInt(id_reservacion));
            modelo.put("accioncobros", accioncobros);
            modelo.put("sw", "2");
            modelo.put("id_pedido", id_pedido);
            modelo.put("id_rubro", id_rubro);
            modelo.put("acuenta", "0");
            modelo.put("datos", paciente);
            modelo.put("id_paciente", id_paciente);
            modelo.put("id_reservacion", id_reservacion);
            modelo.put("id_consultorio", id_consultorio);
            modelo.put("id_persona", id_persona);
            modelo.put("expedido", expedido);
            modelo.put("tipo_medico", tipo_medico);

            List listarRubros = this.mi.getListaRubro();
            modelo.put("listarRubros", listarRubros);
            nit = "0";

            paciente.setNombres(nombres);
            paciente.setPrecio_total(0);
            paciente.setNum_cladoc(num_cladoc);
            paciente.setNit(nit);
            paciente.setTipo("C");
            paciente.setId_factura(0);/////  0 sin factura, 1 con factura, 2, 3, 4, para num correlativo, entradas, ajuste+ y ajuste-
            paciente.setFec_registro(fecha1);
            paciente.setId_estado("A");
            paciente.setCod_esta(cliente.getCod_esta());
            this.mi.setCrearPeedido(paciente);
            id_pedido = Integer.toString(this.mi.getNumPedido(paciente));
            modelo.put("id_pedido", id_pedido);

            return new ModelAndView("administrarcobranza/ListaCobroPaciente", modelo);
        }

        if (("".equals(id_laboratorio) || id_laboratorio == null) || Integer.parseInt(id_rubro) != 6) {
            id_laboratorio = "0";
        }
        if (("".equals(id_rubro) || id_rubro == null)) {
            id_rubro = "0";
        }

        Costos datoq = new Costos();
        datoq.setId_rubro(Integer.parseInt(id_rubro));
        datoq.setId_prestacion(5000);
        datoq.setId_estado("%");
        datoq.setTipo(Integer.parseInt(id_laboratorio));
        datoq.setId_persona(Integer.parseInt(id_laboratorio));
        if ("0".equals(id_laboratorio)) {
            datoq.setId_persona(9999);
        }
        datoq.setId_nivel(0);
        datoq.setEmergencias(1);
        if ("2".equals(tipo_medico)) {
            id_rubro = "3";
            datoq.setId_rubro(3);
            datoq.setId_prestacion(3);
        }
        String swenfer = id_rubro;
        datoq.setCod_esta(cliente.getCod_esta());///para todos 20-02-2017

        List listarRubros = this.mi.getListaRubro();
        modelo.put("listarRubros", listarRubros);
        if (request.getParameter("id_rubro") != null) {
            datoq.setId_rubro(Integer.parseInt(id_rubro));
            datoq.setId_prestacion(Integer.parseInt(id_rubro));

            List listarCostos = this.mi.getListarCostos(datoq);
            modelo.put("listarCostos", listarCostos);

            Laboratorios pai = new Laboratorios();
            pai.setId_estado("A");
            List listarLabo1 = this.mi.getListarLaboratorios(pai);
            modelo.put("listarLabo1", listarLabo1);
            modelo.put("accion", accion);
//        modelo.put("acuenta", acuenta);
            modelo.put("sw", request.getParameter("sw"));
            modelo.put("id_pedido", id_pedido);
            modelo.put("id_rubro", id_rubro);
            modelo.put("expedido", expedido);
            modelo.put("id_paciente", id_paciente);
            modelo.put("id_reservacion", id_reservacion);
            modelo.put("id_consultorio", id_consultorio);
            modelo.put("id_persona", id_persona);
            modelo.put("tipo_medico", tipo_medico);
//        modelo.put("datos", paciente);
            if ("6".equals(id_rubro) && id_costo != null && !"".equals(id_costo)) {
                datoq.setId_rubro(6);
                datoq.setId_prestacion(6);
                datoq.setId_estado("%");
                datoq.setTipo(Integer.parseInt(id_laboratorio));
                datoq.setId_persona(Integer.parseInt(id_laboratorio));
                datoq.setCod_esta(cliente.getCod_esta());///para todos 20-02-2017
                List listarLabo = this.mi.getListarCostos(datoq);
                modelo.put("listarCostos", listarLabo);
                modelo.put("id_costo", id_costo);
            }
        }

        if ("adicion".equals(accion)) {
            if (id_historia == null) {
                id_historia = "0";
            }
            if (id_reservacion == null || "".equals(id_reservacion)) {
                id_reservacion = "0";
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
            Cuadernos datoc5 = new Cuadernos();  ////saca datos de internados id_historia  15-09-2015
            datoc5.setId_historial(Integer.parseInt(id_reservacion));
            datoc5.setCod_esta(cliente.getCod_esta());
            List C5 = this.mi.getPacienteCuaderno5(datoc5);

            if (!C5.isEmpty()) {
                Cuadernos Inter = (Cuadernos) C5.get(0);
                inter = Inter.getId_historia();
                id_persona = Integer.toString(Inter.getId_persona());
                modelo.put("salasInter", Inter);
            }

            /////////// no se ddebe crear reservaciones ni historiales esto esta duplicnado historias  
            if ((id_reservacion == null || "0".equals(id_reservacion)) && ("6".equals(id_rubro) || "7".equals(id_rubro))) {

                if ((listasi.size() == 0 && listas.size() == 0)) {
                    Personas buscarEmpleado = this.mi.getDatosPersona(cliente.getId_persona());
                    Historiales reserva = new Historiales();
                    reserva.setId_paciente(Integer.parseInt(id_paciente));
                    reserva.setId_consultorio(buscarEmpleado.getId_consultorio());
                    reserva.setEdad(buscarPaciente.getEdad());
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
                    reserva.setAccion("Z");////aumento 04/05/2014 
                    List listarHG = this.mi.getListarCobroReservaUni(reserva);
                    Historiales listaHG = (Historiales) listarHG.get(listarHG.size() - 1);
                    id_reservacion = Integer.toString(listaHG.getId_reservacion());
                    modelo.put("id_reservacion", id_reservacion);
                    ///if ("6".equals(id_rubro)) { 
                    Historiales dato = new Historiales();
                    dato.setId_historial(listaHG.getId_reservacion());
                    if (id_persona == null || "".equals(id_persona)) {
                        dato.setId_persona(cliente.getId_persona());
                    } else {
                        dato.setId_persona(Integer.parseInt(id_persona));
                    }

                    dato.setId_paciente(buscarPaciente.getId_paciente());
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
                    dato.setSubjetivo("Desde Cobranza");
                    if ("5".equals(id_rubro)) {
                        dato.setDiagnostico("Enfermeria");
                    }
                    if ("6".equals(id_rubro)) {
                        dato.setDiagnostico("Laboratorio");
                    }
                    dato.setObjetivo("");
                    dato.setId_seguro(buscarPaciente.getId_seguro());
                    dato.setCod_esta(cliente.getCod_esta());
                    dato.setId_por(cliente.getId_persona());
                    dato.setSo2("0");
                    dato.setGlicemia("0");
                    dato.setFechalab(fecha1);
                    //if(listaHG.getFecha().getTime()>listaHG.getFechalab().getTime() ){
                    //  dato.setFechalab(listaHG.getFechalab()); 
                    //}
                    ////id_reservacion=Integer.toString(listaHG.getId_reservacion());
                    if (!"5".equals(id_rubro)) {////////03/05/2014
                        int iResultado = this.mi.setRegistrarHistorial(dato);
                        Historiales elimina = new Historiales();
                        elimina.setCod_esta(cliente.getCod_esta());
                        elimina.setId_reservacion(Integer.parseInt(id_reservacion));
                        elimina.setId_persona(cliente.getId_persona());///01/09/2014
                        elimina.setAccion(ip);
                        elimina.setCodigo(host);
                        elimina.setExpedido("A");

                        this.mi.setEliminarReserva(elimina);
                    }

                    ////}          
                }///fin en caso que no haya id_historial  

                Pacientes datoped = new Pacientes();
                datoped.setId_rubro(2);
                datoped.setId_escolaridad(1000);
                datoped.setId_persona(cliente.getId_persona());
                datoped.setId_provincia(Integer.parseInt(id_reservacion));
                List listaCobrosTots = this.mi.getDatosPedidoRubro(datoped);
                if (listaCobrosTots.size() != 0) {
                    Pacientes datopedix = (Pacientes) listaCobrosTots.get(0);
                    id_pedido = Integer.toString(datopedix.getId_pedido());
                    modelo.put("id_pedido", id_pedido);
                }
            }

            Pacientes datoped = new Pacientes();
            datoped.setId_rubro(2);
            datoped.setId_persona(100);
            datoped.setId_provincia(Integer.parseInt(id_reservacion));
            List listaCobrosTots = this.mi.getDatosPedidoRubro(datoped);

            if (listaCobrosTots.size() != 0) {
                Pacientes datopedix = (Pacientes) listaCobrosTots.get(0);
                id_pedido = Integer.toString(datopedix.getId_pedido());
                modelo.put("id_pedido", id_pedido);
            }
            if (datosconsultorio.getId_cargo() == 2) {///2 de odontologia busca id pedido
                datoped.setId_rubro(3);
                datoped.setId_persona(cliente.getId_persona());
                datoped.setTipo("D");
                List listaCobrosden = this.mi.getDatosPedidoRubroDental(datoped);
                if (listaCobrosden.size() != 0) {
                    Pacientes datopedi = (Pacientes) listaCobrosden.get(listaCobrosden.size() - 1);
                    id_pedido = Integer.toString(datopedi.getId_pedido());
                }

            }

            if (("".equals(id_pedido) || id_pedido == "" || id_pedido == null) && listaCobrosTots.size() == 0) { ///Si no esta creado el pedido se crea
                Pacientes paciente = new Pacientes();
                paciente.setCod_esta(cliente.getCod_esta());
                try {
                    num_cladoc = Long.toString(this.mi.getNumClaDoc(paciente));
                } catch (Exception e) {
                    num_cladoc = Long.toString(0);
                }
                Historiales datohi = new Historiales();
                datohi.setId_historial(Integer.parseInt(id_reservacion));
                datohi.setCod_esta(cliente.getCod_esta());
                Historiales datoka = this.mi.getDatosHistorial(datohi);

                paciente.setNombres(buscarPaciente.getNombres());
                paciente.setPrecio_total(0);
                paciente.setNum_cladoc(num_cladoc);
                paciente.setId_paciente(buscarPaciente.getId_paciente());
                paciente.setId_carpeta(Integer.parseInt(id_reservacion));
                paciente.setNit(nit);
                paciente.setId_costo(0);
                paciente.setId_persona(cliente.getId_persona());
                paciente.setId_dispensa(cliente.getId_persona());
                paciente.setId_pais(inter);
                paciente.setFec_registro(fecha1);
                if ("5".equals(id_rubro)) {
                    paciente.setId_estado("C");
                }
                if (!"5".equals(id_rubro)) {
                    paciente.setId_estado("C");  ////datoka.getExpedido
                }
                paciente.setTipo("C");
                paciente.setId_factura(0);/////   0 sin factura, 1 con factura, 2, 3, 4, para num correlativo, entradas, ajuste+ y ajuste-
                paciente.setCod_esta(cliente.getCod_esta());
                if (listaCobrosTots.size() == 0) {
                    this.mi.setCrearPeedido(paciente);
                }

                ////////
                Pacientes datopedz = new Pacientes();
                datopedz.setId_rubro(2);
                datopedz.setId_escolaridad(1000);
                datopedz.setId_persona(cliente.getId_persona());
                datopedz.setCod_esta(cliente.getCod_esta());
                datopedz.setId_provincia(Integer.parseInt(id_reservacion));
                List listaCobrosTotsz = this.mi.getDatosPedidoRubro(datopedz);
                if (listaCobrosTotsz.size() != 0) {
                    Pacientes datopedix = (Pacientes) listaCobrosTotsz.get(0);
                    id_pedido = Integer.toString(datopedix.getId_pedido());
                    modelo.put("id_pedido", id_pedido);
                }

                id_pedido = Integer.toString(this.mi.getNumPedido(datopedz));

            } else { ///Si YA esta creado el pedido se actualiza

                // entregamos el medicamento
                Pacientes paciente1 = new Pacientes();
                paciente1.setId_pedido(Integer.parseInt(id_pedido));
                paciente1.setCod_esta(cliente.getCod_esta());
                Pacientes paciente = this.mi.getDatosPedido(paciente1); //////////////  
                if (paciente.getId_factura() > 0) {
                    paciente.setNombres(buscarPaciente.getNombres());
                    paciente.setPrecio_total(0);
                    paciente.setNum_cladoc(num_cladoc);
                    paciente.setNit(nit);
                    paciente.setTipo("C");
                    paciente.setId_factura(0);/////  0 sin factura, 1 con factura, 2, 3, 4, para num correlativo, entradas, ajuste+ y ajuste-
                    paciente.setFec_registro(fecha1);
                    paciente.setId_estado("A");
                    paciente.setCadena1("E");
                    paciente.setCod_esta(cliente.getCod_esta());
                    this.mi.setCrearPeedido(paciente);
                    id_pedido = Integer.toString(this.mi.getNumPedido(paciente));
                    modelo.put("id_pedido", id_pedido);
                }

            }//fin de id _pedido nullo

            Detalle detalle = new Detalle();
            if (request.getParameter("id_rubro") != null) {
                datoq.setCod_esta(cliente.getCod_esta());///para todos 20-02-2017   
                List listarCostos = this.mi.getListarCostos(datoq);
                modelo.put("listarCostos", listarCostos);
                modelo.put("id_rubro", id_rubro);
                if (request.getParameter("id_costo") != null) {
                    Costos costo1 = new Costos();
                    costo1.setId_costo(Integer.parseInt(id_costo));
                    costo1.setCod_esta(cliente.getCod_esta());
                    Costos buscarCosto = this.mi.getDatosCosto(costo1);

                    if (buscarCosto != null) {
                        if ("1".equals(sw)) {  //se verifica si es al credito o al contado
                            detalle.setEntrada(buscarCosto.getCosto_unit());
                            detalle.setSalida(buscarCosto.getCosto_unit());
                        } else {
                            if ("Cobros".equals(accioncobros)) {
                                detalle.setEntrada(Double.parseDouble(cantidad));
                                detalle.setSalida(Double.parseDouble(cantidad));

                                if ("5".equals(id_rubro)) {

                                }
                                if ("6".equals(id_rubro) || "7".equals(id_rubro)) {
                                    Cuadernos dato = new Cuadernos();
                                    dato.setResultado("");
                                    dato.setId_historial(Integer.parseInt(id_reservacion));
                                    dato.setId_costo(Integer.parseInt(id_costo));
                                    dato.setNombres("NOMBRE MEDICO");
                                    dato.setLaboratorio(buscarCosto.getCosto());
                                    dato.setTipoconsulta(indicacion);
                                    dato.setId_pedido(Integer.parseInt(id_pedido));
                                    if (cliente.getId_laboratorio() == 0) {
                                        dato.setId_cargo(0);
                                        dato.setId_laboratorio(999);
                                    } else {
                                        dato.setId_cargo(cliente.getId_laboratorio());
                                        dato.setId_laboratorio(cliente.getId_laboratorio());
                                    }
                                    dato.setNombre(cliente.getEstablecimiento());
                                    dato.setId_historia(inter);
                                    dato.setFechap(fecha1);
                                    dato.setFechae(fecha1);
                                    dato.setId_persona(cliente.getId_persona());
                                    dato.setId_dispensa(cliente.getId_persona());
                                    dato.setTipo(buscarPaciente.getId_estado());  ////04/05/2014 datosHis.getExpedido()
                                    dato.setId_paciente(buscarPaciente.getId_paciente());
                                    dato.setSeleccion(0);   ////10-05-2015
                                    dato.setId_laboratorio(buscarCosto.getId_laboratorio());   ////20-05-2015
                                    dato.setCod_esta(cliente.getCod_esta());
                                    dato.setEstado("A");
                                    dato.setId_estado("A"); ////se borra X y Y ecos y rayos X 01-10-2015

                                    List listaP = this.mi.getPedidoLab(dato);

                                    if (listaP.isEmpty() == true) {
                                        this.mi.setCrearPedidoLab(dato);
                                    } else {

                                        Cuadernos datoin = (Cuadernos) listaP.get(listaP.size() - 1);/////17/04/2015 para que se cree otro pedido en pedidolab
                                        long fechalab = datoin.getFechap().getTime();
                                        long fechanow = fechaact.getTime();
                                        long diferencia = fechanow - fechalab;
                                        String reportDate = df.format(datoin.getFechap());
                                        double horas = Math.floor(diferencia / (1000 * 60 * 60));
                                        double minut = Math.floor(diferencia / (1000 * 60));
                                        if (inter == 0) {
                                            sw2 = 1;
                                        }
                                        //if(datoin.getId_historia()!=inter)
                                        //    this.mi.setCrearPedidoLab(dato);  borrado 10-05-2015 estaba generando doble para internado
                                        if (minut > 7 && sw2 == 0) {////31/12/2015 se cambia e hora a minitos si es mas de 10 min crea nuevo
                                            sw2 = 1;
                                            this.mi.setCrearPedidoLab(dato);
                                        }
                                        ///if(datoin.getId_historia()!=inter)
                                        ///    this.mi.setCrearPedidoLab(dato);                   
                                    }
                                    List listaP2 = this.mi.getPedidoLab(dato);
                                    int g = listaP2.size();
                                    Cuadernos datoin2 = (Cuadernos) listaP2.get(0);
                                    dato.setId_pedido(datoin2.getId_pedido());
                                    dato.setCod_esta(cliente.getCod_esta());
                                    dato.setId_por(cliente.getId_persona());
                                    this.mi.setCrearLaboratorioC(dato);
                                    /// fin de llena la tabla laboratorios
                                }
                            } else {
                                detalle.setEntrada(buscarCosto.getCosto_unit());
                            }
                        }
                    } else {
                        modelo.put("precio", "0");
                    }
                }
            }
//            modelo.put("acuenta", acuenta);
            detalle.setId_costo(Integer.parseInt(id_costo));
            detalle.setId_pedido(Integer.parseInt(id_pedido));
            detalle.setUlt_usuario(cliente.getId_persona());
            detalle.setId_rubro(Integer.parseInt(id_rubro));
            detalle.setIndicacion(indicacion);
            detalle.setCod_esta(cliente.getCod_esta());
            this.mi.setCrearDetalle(detalle);
        }

        if ("eliminar".equals(accion)) {
            String id_detalle = request.getParameter("id_detalle");
            int id_factura = 0;

            Detalle datodet = new Detalle();
            datodet.setId_pedido(Integer.parseInt(id_pedido));
            datodet.setId_carpeta(cliente.getCod_esta());
            datodet.setCod_esta(cliente.getCod_esta());
            List listarcobros = this.mi.getListarCobroDetalle(datodet);
            for (int j = 0; j < listarcobros.size(); j++) {
                Detalle datoc = (Detalle) listarcobros.get(j);
                id_factura = datoc.getId_empresa();
            }
            if (id_factura > 0) {
                return new ModelAndView("Aviso", "mensaje", "Ya no puede eliminar, esta Factura Ya fue Impresa");
            }
            Costos costo1 = new Costos();
            costo1.setId_costo(Integer.parseInt(id_costo));
            costo1.setCod_esta(cliente.getCod_esta());
            Costos buscarCosto = this.mi.getDatosCosto(costo1);
            Detalle dato = new Detalle();
            dato.setId_detalle(Integer.parseInt(id_detalle));
            dato.setId_pedido(Integer.parseInt(id_pedido));
            dato.setId_costo(Integer.parseInt(id_costo));
            dato.setId_dispensa(cliente.getId_persona());
            dato.setId_carpeta(Integer.parseInt(id_reservacion));
            dato.setCadena1(ip);
            dato.setCadena2(host);
            this.mi.setEliminarDetalle(dato);
            modelo.put("accioncobros", accioncobros);
            modelo.put("expedido", expedido);
            modelo.put("id_paciente", id_paciente);
            modelo.put("id_reservacion", id_reservacion);
            modelo.put("id_consultorio", id_consultorio);
            modelo.put("id_persona", id_persona);
            modelo.put("tipo_medico", tipo_medico);
        }

        if ("cancelar".equals(accion)) {
            Detalle detalles = new Detalle();
            detalles.setEntrada(0);
            detalles.setSalida(Integer.parseInt(cantidad));
            detalles.setId_costo(0);
            detalles.setId_pedido(Integer.parseInt(id_pedido));
            detalles.setUlt_usuario(cliente.getId_persona());
            detalles.setCod_esta(cliente.getCod_esta());
            this.mi.setCrearDetalle(detalles);

            Pacientes paciente1 = new Pacientes();
            paciente1.setId_pedido(Integer.parseInt(id_pedido));
            paciente1.setCod_esta(cliente.getCod_esta());
            Pacientes paciente = this.mi.getDatosPedido(paciente1); ////////////// 
            paciente.setNum_cladoc(num_cladoc);
            paciente.setNit(nit);
            paciente.setId_estado("E");
            paciente.setId_persona(cliente.getId_persona());
            paciente.setPrecio_total(Integer.parseInt(cantidad));
            paciente.setFecha_fin(fecha1);
            this.mi.setModificarPedido(paciente);  ///setModificarPedidoFactura       

            return new ModelAndView("administrarcobranza/ListarCobroReserva", modelo);
        }

        if ("terminar".equals(accion) && (!"".equals(id_pedido) && id_pedido != null)) {
            Pacientes paciente1 = new Pacientes();
            paciente1.setId_pedido(Integer.parseInt(id_pedido));
            paciente1.setCod_esta(cliente.getCod_esta());
            Pacientes paciente = this.mi.getDatosPedido(paciente1); //////////////

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
            }
            if (listas.size() > 0) {
                Historiales listaH = (Historiales) listas.get(listas.size() - 1);
                id_reservacion = Integer.toString(listaH.getId_historial());
            }
            if ("5".equals(swenfer)) {
                if (id_reservacion == null || id_reservacion == "" || Integer.parseInt(id_reservacion) == 0) {
                    Historiales reserva = new Historiales();
                    reserva.setId_paciente(Integer.parseInt(id_paciente));
                    reserva.setId_consultorio(cliente.getId_consultorio());
                    reserva.setEdad(buscarPaciente.getEdad());
                    reserva.setFecha(fecha1);
                    reserva.setId_estado("C");
                    reserva.setExpedido("E");
                    reserva.setId_persona(0);
                    reserva.setUlt_usuario(cliente.getId_usuario());
                    this.mi.setCrearReservacion(reserva); ///crea una reserva en tabla reservaciones
                    reserva.setId_estado("%");
                    reserva.setAccion("Z");
                    List listarHG = this.mi.getListarCobroReservaUni(reserva);
                    Historiales listaHG = (Historiales) listarHG.get(listarHG.size() - 1);
                    id_reservacion = Integer.toString(listaHG.getId_reservacion());
                }
                paciente.setId_carpeta(Integer.parseInt(id_reservacion));
                paciente.setId_estado("C");
            }
            paciente.setFecha_fin(fecha1);
            this.mi.setModificarPedido(paciente);

            if (datoestab2.getId_pais() == 1) {////////imprime recibo
                String ciu = request.getParameter("ciu");
                String sumatot1 = request.getParameter("precio");
                int sumatot = (int) (Double.parseDouble(sumatot1));
                Pacientes datojef = mi.getDatosPaciente(Integer.parseInt(id_paciente));
                modelo.put("persona", datojef);

                Rubros dfact = this.mi.getDatosRubro(Integer.parseInt(id_rubro));
                modelo.put("rubro", dfact.getRubro());
                if ("".equals(id_pedido)) {
                    Costos costo1 = new Costos();
                    costo1.setId_costo(Integer.parseInt(id_costo));
                    costo1.setCod_esta(cliente.getCod_esta());
                    Costos listaDet = this.mi.getDatosCosto(costo1);
                    modelo.put("costo1", listaDet.getCosto());
                } else {
                    Detalle datodet = new Detalle();
                    datodet.setId_pedido(Integer.parseInt(id_pedido));
                    datodet.setId_carpeta(cliente.getCod_esta());
                    datodet.setCod_esta(cliente.getCod_esta());
                    List listaDet = this.mi.getListarCobroDetalle(datodet);
                    modelo.put("costo", listaDet);
                }

                //Pacientes paciente1= new Pacientes() ;
                paciente1.setId_pedido(Integer.parseInt(id_pedido));
                paciente1.setCod_esta(cliente.getCod_esta());
                Pacientes pedido = this.mi.getDatosPedido(paciente1); //////////////
                modelo.put("pedido", pedido);

                Departamentos buscardepartamento = this.mi.getDatosDepartamento(datoestab2.getId_departamento()); // saca un registro a ser modificado
                modelo.put("departamento", buscardepartamento);
                modelo.put("estab", Estab1);
                modelo.put("dato", cliente);

                modelo.put("rubrocosto", sumatot1);
                modelo.put("nombreusuario", paciente.getNombres());
                modelo.put("ciu", buscarPaciente.getCarnet());

                return new ModelAndView(new ReciboPDF(), modelo);
            }

            ///////////imprime facturas
            if (datoestab2.getId_pais() == 2 && "Factura".equals(accionFact)) {////////2 imprime factura
                String ciu = request.getParameter("nit");
                String sumatot1 = request.getParameter("precio");
                int sumatot = (int) (Double.parseDouble(sumatot1));

                long na = Long.parseLong(datoestab2.getNum_auto().trim());
                long fact = datoestab2.getNum_fact();
                long nit2 = Long.parseLong(datoestab2.getNit());
                String llave = datoestab2.getLlave();

                //String ff=(Integer.toString(fechaact.getYear()+1900))+""+(fechaact.getMonth()+1)+""+(fechaact.getDate());
                int anio1 = fecha1.getYear() + 1900;
                int mes1 = fecha1.getMonth() + 1;
                int dia1 = fecha1.getDate();
                String anio11 = Integer.toString(anio1);
                String mes11 = Integer.toString(mes1);
                String dia11 = Integer.toString(dia1);;
                if (mes1 < 10) {
                    mes11 = "0" + mes11;
                }
                if (dia1 < 10) {
                    dia11 = "0" + dia11;
                }
                String ff = anio11 + mes11 + dia11;

                long fec = Long.parseLong(ff);
                int total2 = (int) (Math.round(((float) (Double.parseDouble(sumatot1)))));
                long carne = Long.parseLong(ciu);
                codigoControl numer22 = new codigoControl(na, fact, carne, fec, total2, llave);
                String codigo = numer22.verCodigoControl();
                modelo.put("dato", cliente);
                modelo.put("estab", Estab2);
                modelo.put("carnet", ciu);
                modelo.put("numauto", datoestab2.getNum_auto());
                modelo.put("numfact", Integer.toString(datoestab2.getNum_fact()));
                modelo.put("codigo", codigo);

                //Pacientes paciente1= new Pacientes() ;
                paciente1.setId_pedido(Integer.parseInt(id_pedido));
                paciente1.setCod_esta(cliente.getCod_esta());
                Pacientes pedido = this.mi.getDatosPedido(paciente1); //////////////

                if (pedido.getId_factura() > 1) {///////parace que no jala
                    return new ModelAndView("Aviso", "mensaje", "Esta Factura Ya fue Impresa");
                }

                if ("".equals(id_pedido)) {
                    Costos costo1 = new Costos();
                    costo1.setId_costo(Integer.parseInt(id_costo));
                    costo1.setCod_esta(cliente.getCod_esta());
                    Costos listaDet = this.mi.getDatosCosto(costo1);
                    modelo.put("costo1", listaDet.getCosto());
                } else {
                    Detalle datodet = new Detalle();
                    datodet.setId_pedido(Integer.parseInt(id_pedido));
                    datodet.setId_carpeta(cliente.getCod_esta());
                    datodet.setCod_esta(cliente.getCod_esta());
                    List listaDet = this.mi.getListarCobroDetalle(datodet);
                    modelo.put("costo", listaDet);
                    if (listaDet.size() > 17) {
                        modelo.put("taman", "mucho");
                    }
                }

                modelo.put("rubrocosto", sumatot1);
                modelo.put("nombres", nombres);
                modelo.put("nombrepac", pedido.getNombres());
                modelo.put("nombreusuario", nombres.toUpperCase());
                modelo.put("ciu", ciu);
                modelo.put("codigo", codigo);
                modelo.put("rubro", id_rubro);

                Pacientes dato = new Pacientes();  ////crea dato en facturas
                dato.setNit_fact(Long.parseLong(ciu));
                dato.setNombre(nombres.toUpperCase());
                dato.setNum_fact(datoestab2.getNum_fact());////para la tabla facturas
                dato.setNum_auto(Long.parseLong(datoestab2.getNum_auto().trim()));
                dato.setFec_registro(fecha1);
                dato.setImporte(Double.parseDouble(sumatot1));
                dato.setIce(0);
                dato.setIme(0);
                dato.setPrecio_total(Double.parseDouble(sumatot1));
                dato.setTotal(Double.parseDouble(sumatot1) * 0.13);
                dato.setId_estado("V");
                dato.setCarnet(codigo);
                dato.setId_pedido(Integer.parseInt(id_pedido));
                dato.setId_persona(cliente.getId_persona());
                dato.setCod_esta(cliente.getCod_esta());
                dato.setId_carpeta(0);
                this.mi.setCrearFactura(dato);

                buscarPaciente.setId_factura(0);
                this.mi.setModificarPedidoAnt(buscarPaciente);////modifica el pedido a con factura id_factura=1

                return new ModelAndView(new Factura2_1copiaPDF(), modelo);
            }
            /*//////////////INLASA
          modelo.put("datos", buscarPaciente);    ////para INLASA
          if ("A".equals(buscarPaciente.getId_estado())) {
                
             
             long na=Long.parseLong(de.getNum_auto());
             long fact=de.getNum_fact();
             long nit2=Long.parseLong(de.getNit());
             String llave=de.getLlave();
             Pacientes paciente3 = this.mi.getDatosPedido(Integer.parseInt(id_pedido)); 
             String a = Integer.toString(fecha1.getYear()+ 1900);
             String m = Integer.toString(fecha1.getMonth()+ 1);
             String d = Integer.toString(fecha1.getDate());

             a= a.concat(m);
             a= a.concat(d);
             long fec = Long.parseLong(a);
             int total2= ((int)paciente3.getPrecio_total());
             codigoControl numer22=new codigoControl(na,fact,nit2,fec,total2,llave);
             String codigo=numer22.verCodigoControl();
             modelo.put("dato", cliente);
             modelo.put("estab", estab);
             Pacientes datojef =  mi.getDatosPaciente(Integer.parseInt(id_paciente)) ;
             modelo.put("persona",datojef);

             Rubros dfact = this.mi.getDatosRubro(Integer.parseInt(id_rubro)) ; 
             modelo.put("rubro",dfact.getRubro());
             if ("".equals(id_pedido)){
                Costos listaDet = this.mi.getDatosCosto(Integer.parseInt(id_costo)); 
                modelo.put("costo1",listaDet.getCosto()); 
             }
             else{
                List listaDet = this.mi.getListarCobroDetalle(Integer.parseInt(id_pedido));    
                modelo.put("costo",listaDet);
             }

             modelo.put("rubrocosto",Double.toString(paciente3.getPrecio_total()));
             modelo.put("nombreusuario",buscarPaciente.getNombres());
             modelo.put("ciu",buscarPaciente.getCarnet());
             modelo.put("codigo",codigo);
     
              return new ModelAndView(new FacturaPDF(), modelo);   ////para INLASA
          }else{
            return new ModelAndView(new VerCodigoBarrasPDF(), modelo);   ////para INLASA    
          }
             *//////////FIN INLASA
            return new ModelAndView("administrarcobranza/ListarCobroReserva", modelo);
        }

        if ("Interconsulta".equals(accion)) {

            Pacientes paciente1 = new Pacientes();
            paciente1.setId_pedido(Integer.parseInt(id_pedido));
            paciente1.setCod_esta(cliente.getCod_esta());
            Pacientes paciente = this.mi.getDatosPedido(paciente1); ////////////// 
            paciente.setFecha_fin(fecha1);
            this.mi.setModificarPedido(paciente);
            return new ModelAndView("administrarcobranza/ListarCobroReserva", modelo);
        }

        if (id_pedido != null && !"".equals(id_pedido)) {
            Detalle datodet = new Detalle();
            datodet.setId_pedido(Integer.parseInt(id_pedido));
            datodet.setId_carpeta(cliente.getCod_esta());
            datodet.setCod_esta(cliente.getCod_esta());
            List listarcobros = this.mi.getListarCobroDetalle(datodet);
            modelo.put("listarcobros", listarcobros);
            Detalle midato = new Detalle();
            total = 0;
            if ("Cobros".equals(accioncobros)) {
                for (int i = 0; i < listarcobros.size(); i++) {
                    midato = (Detalle) listarcobros.get(i);
                    total = total + midato.getEntrada();
                }
            } else {
                for (int i = 0; i < listarcobros.size(); i++) {
                    midato = (Detalle) listarcobros.get(i);
                    total = total + midato.getCosto_unit();
                }
            }
        }
        // Calculamos el total a pagar
        Consultorios a = new Consultorios();
        a.setCod_esta(cliente.getCod_esta());
        List listarCargos = this.mi.getListarConsultorios(a);
        modelo.put("listarCargos", listarCargos);
        if ("".equals(nombres) || nombres == null) {
            nombres = "S/N";

        }
        nombres = nombres.toUpperCase();

        // actualiza el precio total
        if (id_pedido != null && !"".equals(id_pedido)) {
            Pacientes paciente1 = new Pacientes();
            paciente1.setId_pedido(Integer.parseInt(id_pedido));
            paciente1.setCod_esta(cliente.getCod_esta());
            Pacientes paciente = this.mi.getDatosPedido(paciente1); //////////////
            paciente.setPrecio_total(total);
            paciente.setId_rubro(Integer.parseInt(id_rubro));
            paciente.setId_persona(cliente.getId_persona());
            paciente.setFecha_fin(fecha1);
            this.mi.setModificarPedido(paciente);
            modelo.put("datos", paciente);
            modelo.put("id_paciente", Integer.toString(paciente.getId_paciente()));
        } else {
            modelo.put("datos", buscarPaciente);
            modelo.put("id_paciente", id_paciente);
        }

        modelo.put("id_pedido", id_pedido);
        modelo.put("id_rubro", id_rubro);
        modelo.put("id_costo", id_costo);
        modelo.put("id_laboratorio", id_laboratorio);///20-05-2015
        modelo.put("accioncobros", accioncobros);
        modelo.put("id_reservacion", id_reservacion);
        modelo.put("id_consultorio", id_consultorio);
        modelo.put("id_persona", id_persona);
        modelo.put("expedido", expedido);
        modelo.put("tipo_medico", tipo_medico);

        if ("Modificar".equals(accioncob)) {
            return new ModelAndView("administrarcobranza/ListaCobroPacienteMod", modelo);
        }

        return new ModelAndView("administrarcobranza/ListaCobroPaciente", modelo);
    }

}
