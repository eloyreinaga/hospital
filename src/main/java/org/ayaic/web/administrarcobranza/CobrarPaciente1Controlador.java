package org.ayaic.web.administrarcobranza;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Costos;
import org.ayaic.domain.Cuadernos;
import org.ayaic.domain.Departamentos;
import org.ayaic.domain.Detalle;
import org.ayaic.domain.Historiales;
import org.ayaic.domain.Localidades;
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.Rubros;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CobrarPaciente1Controlador {

    private final MiFacade mi;

    public CobrarPaciente1Controlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/CobrarPaciente1.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String sw = request.getParameter("sw");
        String id_paciente = request.getParameter("id_paciente");
        String nombres = request.getParameter("nombres");
        String medico = request.getParameter("medico");
        String carnet = request.getParameter("carnet");
        String observa = request.getParameter("observa");
        String id_reservacion = request.getParameter("id_reservacion");
        String id_consultorio = request.getParameter("id_consultorio");
        String id_persona = request.getParameter("id_persona");
        String id_rubro = request.getParameter("id_rubro");
        String id_costo = request.getParameter("id_costo");
        String num_cladoc = request.getParameter("num_cladoc");
        String id_pedido = request.getParameter("id_pedido");
        String precio_total = request.getParameter("precio_total");
        String nit = request.getParameter("nit");
        String ciu = request.getParameter("ciu");
        String swenfer = request.getParameter("swenfer");
        String swe = request.getParameter("swe");
        String[] dias = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
        String[] meses = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        Date fecha1 = new Date();
        int anioq = fecha1.getYear() + 1900;
        String[] anios = {Integer.toString(anioq), Integer.toString(anioq - 1), Integer.toString(anioq - 2), Integer.toString(anioq - 3), Integer.toString(anioq - 4), Integer.toString(anioq - 5), Integer.toString(anioq - 6), Integer.toString(anioq - 7), Integer.toString(anioq - 8), Integer.toString(anioq - 9), Integer.toString(anioq - 10), Integer.toString(anioq - 11), Integer.toString(anioq - 12), Integer.toString(anioq - 13), Integer.toString(anioq - 14), Integer.toString(anioq - 15), Integer.toString(anioq - 16), Integer.toString(anioq - 17), Integer.toString(anioq - 18), Integer.toString(anioq - 19), Integer.toString(anioq - 20), Integer.toString(anioq - 21), Integer.toString(anioq - 22), Integer.toString(anioq - 23), Integer.toString(anioq - 24), Integer.toString(anioq - 25), Integer.toString(anioq - 26), Integer.toString(anioq - 27), Integer.toString(anioq - 28), Integer.toString(anioq - 29), Integer.toString(anioq - 30), Integer.toString(anioq - 31), Integer.toString(anioq - 32), Integer.toString(anioq - 33), Integer.toString(anioq - 34), Integer.toString(anioq - 35), Integer.toString(anioq - 36), Integer.toString(anioq - 37), Integer.toString(anioq - 38), Integer.toString(anioq - 39), Integer.toString(anioq - 40), Integer.toString(anioq - 41), Integer.toString(anioq - 42), Integer.toString(anioq - 43), Integer.toString(anioq - 44), Integer.toString(anioq - 45), Integer.toString(anioq - 46), Integer.toString(anioq - 47), Integer.toString(anioq - 48), Integer.toString(anioq - 49), Integer.toString(anioq - 50), Integer.toString(anioq - 51), Integer.toString(anioq - 52), Integer.toString(anioq - 53), Integer.toString(anioq - 54), Integer.toString(anioq - 55), Integer.toString(anioq - 56), Integer.toString(anioq - 57), Integer.toString(anioq - 58), Integer.toString(anioq - 59), Integer.toString(anioq - 60), Integer.toString(anioq - 61), Integer.toString(anioq - 62), Integer.toString(anioq - 63), Integer.toString(anioq - 64), Integer.toString(anioq - 65), Integer.toString(anioq - 66), Integer.toString(anioq - 67), Integer.toString(anioq - 68), Integer.toString(anioq - 69), Integer.toString(anioq - 70), Integer.toString(anioq - 71), Integer.toString(anioq - 72), Integer.toString(anioq - 73), Integer.toString(anioq - 74), Integer.toString(anioq - 75), Integer.toString(anioq - 76), Integer.toString(anioq - 77), Integer.toString(anioq - 78), Integer.toString(anioq - 79), Integer.toString(anioq - 80), Integer.toString(anioq - 81), Integer.toString(anioq - 82), Integer.toString(anioq - 83), Integer.toString(anioq - 84), Integer.toString(anioq - 85), Integer.toString(anioq - 86), Integer.toString(anioq - 87), Integer.toString(anioq - 88), Integer.toString(anioq - 89), Integer.toString(anioq - 90), Integer.toString(anioq - 91), Integer.toString(anioq - 92), Integer.toString(anioq - 93), Integer.toString(anioq - 94), Integer.toString(anioq - 95), Integer.toString(anioq - 96), Integer.toString(anioq - 97), Integer.toString(anioq - 98), Integer.toString(anioq - 99), Integer.toString(anioq - 100), Integer.toString(anioq - 101), Integer.toString(anioq - 102), Integer.toString(anioq - 103), Integer.toString(anioq - 104), Integer.toString(anioq - 105), Integer.toString(anioq - 106), Integer.toString(anioq - 107), Integer.toString(anioq - 108), Integer.toString(anioq - 109), Integer.toString(anioq - 110)};

        modelo.put("anio", Integer.toString(fecha1.getYear() + 1900));
        modelo.put("mes", Integer.toString(fecha1.getMonth() + 1));
        modelo.put("dia", Integer.toString(fecha1.getDate()));
        modelo.put("anios", anios);
        modelo.put("meses", meses);
        modelo.put("dias", dias);

        Localidades local = new Localidades();
        local.setCod_esta(cliente.getCod_esta());
        local.setArea("U");  ///getListarEstaUsua el estab del usuario
        List Estab1 = this.mi.getListarEstaUsua(local);
        Localidades datoest = (Localidades) Estab1.get(0);
        modelo.put("Factura", Integer.toString(datoest.getId_pais()));
        if (datoest.getCodesta() != cliente.getCod_esta()) {
            local.setCod_esta(datoest.getCodesta());
        }
        local.setArea("U");  ///getListarEstaUsua el estab del usuario
        List Estab12 = this.mi.getListarEstaUsua(local);
        Localidades datoestab = (Localidades) Estab12.get(0); //// este el el usuario con elque se trabaja
        modelo.put("Factura", Integer.toString(datoestab.getId_pais()));
        modelo.put("medico", medico);

        if ("".equals(id_persona) || id_persona == null) {
            id_persona = Integer.toString(cliente.getId_usuario());
        }
        if ("".equals(id_rubro) || id_rubro == null) {
            id_rubro = "2";/////consultas
        }
        if ("".equals(observa) || observa == null) {
            observa = " ";
        }

        Pacientes buscarPaciente = new Pacientes();
        buscarPaciente = this.mi.getDatosPaciente(Integer.parseInt(id_paciente)); // saca un registro a ser modificado

        Costos datoq = new Costos();
        datoq.setId_rubro(Integer.parseInt(id_rubro));
        datoq.setId_prestacion(Integer.parseInt(id_rubro));
        datoq.setId_estado("%");
        datoq.setTipo(0);
        datoq.setId_persona(5000);
        datoq.setEmergencias(0);
        datoq.setCod_esta(cliente.getCod_esta());///para todos 20-02-2017
        if ("Nuevo".equals(accion)) {
            buscarPaciente.setCod_esta(cliente.getCod_esta());
            num_cladoc = Long.toString(this.mi.getNumClaDoc(buscarPaciente));
        }

        Rubros aux = new Rubros();
        List listarRubros = this.mi.getListarRubros(aux);
        modelo.put("listarRubros", listarRubros);
        if ("0".equals(id_costo) && !"0".equals(id_rubro) && !"3".equals(id_rubro) && !"5".equals(id_rubro)) {/// !"5".equals(id_rubro) aumente 11-04-2014
            datoq.setId_rubro(Integer.parseInt(id_rubro));
            List listarCostos = this.mi.getListarCostos(datoq);
            modelo.put("listarCostos", listarCostos);
            return new ModelAndView("Aviso", "mensaje", "Ud. debe elegir un Item de este Rubro");
        }
        if (id_rubro != null) {
            datoq.setId_rubro(Integer.parseInt(id_rubro));
            List listarCostos = this.mi.getListarCostos(datoq);
            modelo.put("listarCostos", listarCostos);
            if (request.getParameter("id_costo") != null) {
                Costos costo = new Costos();
                costo.setId_costo(Integer.parseInt(id_costo));
                costo.setCod_esta(cliente.getCod_esta());
                Costos buscarCosto = this.mi.getDatosCosto(costo);
                if (request.getParameter("precio_total") != null) {
                    modelo.put("precio", precio_total);
                } else if (buscarCosto != null) {
                    modelo.put("precio", Double.toString(buscarCosto.getCosto_unit()));
                    id_rubro = Integer.toString(buscarCosto.getId_rubro());
                } else {
                    modelo.put("precio", "0");
                }
            }
        }
        if (!"".equals(id_pedido) && id_pedido != null) {
            Detalle datodet = new Detalle();
            datodet.setId_pedido(Integer.parseInt(id_pedido));
            datodet.setId_carpeta(cliente.getCod_esta());
            datodet.setCod_esta(cliente.getCod_esta());
            List listaDet = this.mi.getListarCobroDetalle(datodet); ;////tardaba mucho se comenta <!--  and costos.cod_esta=#cod_esta#-->
            if (!listaDet.isEmpty()) {
                Detalle datodet1 = (Detalle) listaDet.get(0);
                modelo.put("indica", datodet1.getIndicacion());
                id_costo=Integer.toString(datodet1.getId_costo());
            }

        }
        modelo.put("accion", accion);
        modelo.put("sw", request.getParameter("sw"));
        modelo.put("swe", request.getParameter("swe"));
        modelo.put("swenfer", swenfer);
        modelo.put("id_paciente", id_paciente);
        modelo.put("nombres", nombres);
        modelo.put("id_reservacion", id_reservacion);
        modelo.put("id_consultorio", id_consultorio);
        modelo.put("id_persona", id_persona);
        modelo.put("id_rubro", id_rubro);
        modelo.put("id_costo", id_costo);
        modelo.put("num_cladoc", num_cladoc);
        modelo.put("id_pedido", id_pedido);
        modelo.put("observa", observa);
        if ("".equals(carnet) || ".".equals(carnet) || carnet == null || "12345".equals(carnet)) {
            carnet = "0";
            if (!"0".equals(ciu) && ciu != null) {
                carnet = ciu;
            }
        } else {
            carnet = buscarPaciente.getCarnet();
        }
        modelo.put("carnet", carnet);
        modelo.put("nit", nit);

        if ("Factura".equals(accion)) {
            String sumatot1 = request.getParameter("precio");
            String fech = request.getParameter("fec");
            int sumatot = (int) (Double.parseDouble(sumatot1));
            
            if(datoestab.getSuma()==2){
                return new ModelAndView("Aviso", "mensaje", "Ya no puede EMITIR mas facturas, hasta que cambie datos dosificacion");
            }if(datoestab.getSuma()==1){
                modelo.put("dosifica", "solicitar");
            }
                    
            try {
                long carne = Long.parseLong(ciu);
                if (carne < 0) {
                    return new ModelAndView("Aviso", "mensaje", "Carnet Identidad NO puede ser negativo");
                }
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "Carnet Identidad NO es un Numero valido");
            }
            try {
                double total4 = Double.parseDouble(sumatot1);
                if (total4 <= 0) {
                    return new ModelAndView("Aviso", "mensaje", "Total NO puede ser negativo o cero");
                }
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "Total NO es un Numero valido");
            }

            long na = Long.parseLong(datoestab.getNum_auto().trim());
            long fact = datoestab.getNum_fact();
            long nit2 = Long.parseLong(datoestab.getNit());
            String llave = datoestab.getLlave();

            int anio1 = fecha1.getYear() + 1900;
            int mes1 = fecha1.getMonth() + 1;
            int dia1 = fecha1.getDate();
            String anio11 = Integer.toString(anio1);
            String mes11 = Integer.toString(mes1);
            String dia11 = Integer.toString(dia1);;
            if (mes1 < 10) {
                mes11 = "0" + mes11;
            }if (dia1 < 10) {
                dia11 = "0" + dia11;
            }

            long fec = Long.parseLong(anio11 + mes11 + dia11);
            int total = (int) (Math.round(((float) (Double.parseDouble(sumatot1)))));
            long carne = Long.parseLong(ciu);
            codigoControl numer22 = new codigoControl(na, fact, carne, fec, total, llave);
            String codigo = numer22.verCodigoControl();
            modelo.put("dato", cliente);
            modelo.put("estab", Estab12);
            modelo.put("carnet", ciu);
            modelo.put("precio", Double.toString(total));
            modelo.put("numauto", datoestab.getNum_auto().trim());
            modelo.put("numfact", Integer.toString(datoestab.getNum_fact()));
            modelo.put("codigo", codigo);
            Pacientes datojef = mi.getDatosPaciente(Integer.parseInt(id_paciente));
            modelo.put("persona", datojef);

            Rubros dfact = this.mi.getDatosRubro(Integer.parseInt(id_rubro));
            modelo.put("rubro", dfact.getRubro());
            if ("".equals(id_pedido)) {
                Costos costo = new Costos();
                costo.setId_costo(Integer.parseInt(id_costo));
                costo.setCod_esta(cliente.getCod_esta());
                Costos listaDet = this.mi.getDatosCosto(costo);
                modelo.put("costo1", listaDet.getCosto());
            } else {
                Detalle datodet = new Detalle();
                datodet.setId_pedido(Integer.parseInt(id_pedido));
                datodet.setId_carpeta(cliente.getCod_esta());
                datodet.setCod_esta(cliente.getCod_esta());
                List listaDet = this.mi.getListarCobroDetalle(datodet);
                modelo.put("costo", listaDet);
            }

            modelo.put("rubrocosto", sumatot1);
            modelo.put("nombreusuario", nombres.toUpperCase());
            modelo.put("ciu", ciu);
            modelo.put("codigo", codigo);
            return new ModelAndView("administrarcobranza/FacturaPaciente", modelo);
        }

        if ("Impresora/Inyeccion".equals(accion)) {
            String costo_unit = request.getParameter("precio");
            String sumatot1 = request.getParameter("precio");
            String fech = request.getParameter("fec");
            int sumatot = (int) (Double.parseDouble(sumatot1));

            long na = Long.parseLong(datoestab.getNum_auto().trim());
            long fact = datoestab.getNum_fact();
            long nit2 = Long.parseLong(datoestab.getNit());
            String llave = datoestab.getLlave();

            int anio1 = fecha1.getYear() + 1900;
            int mes1 = fecha1.getMonth() + 1;
            int dia1 = fecha1.getDate();
            String anio11 = Integer.toString(anio1);
            String mes11 = Integer.toString(mes1);
            String dia11 = Integer.toString(dia1);;
            if (mes1 < 10) {
                mes11 = "0" + mes11;
            }if (dia1 < 10) {
                dia11 = "0" + dia11;
            }

            long fec = Long.parseLong(anio11 + mes11 + dia11);
            int total = (int) (Math.round(((float) (Double.parseDouble(sumatot1)))));
            long carne = Long.parseLong(ciu);
            codigoControl numer22 = new codigoControl(na, fact, carne, fec, total, llave);
            String codigo = numer22.verCodigoControl();
            modelo.put("dato", cliente);
            modelo.put("estab", Estab12);
            modelo.put("carnet", ciu);
            modelo.put("numauto", datoestab.getNum_auto().trim());
            modelo.put("numfact", Integer.toString(datoestab.getNum_fact()));
            modelo.put("codigo", codigo);
            Pacientes datojef = mi.getDatosPaciente(Integer.parseInt(id_paciente));
            modelo.put("persona", datojef);

            Rubros dfact = this.mi.getDatosRubro(Integer.parseInt(id_rubro));
            modelo.put("rubro", dfact.getRubro());
            if ("".equals(id_pedido)) {
                Costos costo = new Costos();
                costo.setId_costo(Integer.parseInt(id_costo));
                costo.setCod_esta(cliente.getCod_esta());
                Costos listaDet = this.mi.getDatosCosto(costo);
                modelo.put("costo1", listaDet.getCosto());
            } else {
                Detalle datodet = new Detalle();
                datodet.setId_pedido(Integer.parseInt(id_pedido));
                datodet.setId_carpeta(cliente.getCod_esta());
                datodet.setCod_esta(cliente.getCod_esta());
                List listaDet = this.mi.getListarCobroDetalle(datodet);
                modelo.put("costo", listaDet);
				if(listaDet.size()==1){
					Detalle costover = (Detalle) listaDet.get(0);
					if(Double.parseDouble(sumatot1)!=costover.getEntrada()){
					   datodet.setCosto_unit(Float.parseFloat(sumatot1));
					   this.mi.setModificarDetalle(datodet);  ///actualiza el precio total si varia el costo_unit con lo que se cobra
					   List listaDet2 = this.mi.getListarCobroDetalle(datodet);
					   modelo.put("costo",listaDet2);
					}
				}
            }

            modelo.put("rubrocosto", sumatot1);
            modelo.put("nombrepac", datojef.getNombres());
            modelo.put("nombreusuario", nombres.toUpperCase());
            modelo.put("ciu", ciu);
            modelo.put("codigo", codigo);

            Historiales datohi = new Historiales();
            datohi.setId_historial(Integer.parseInt(id_reservacion));
            datohi.setCod_esta(cliente.getCod_esta());
            datohi.setAccion("R");   ///getDatosReserva
            Historiales datosHistorialRe = this.mi.getDatosReserva(datohi);

            Pacientes paciente1 = new Pacientes();
            paciente1.setId_pedido(Integer.parseInt(id_reservacion));
            paciente1.setCod_esta(cliente.getCod_esta());
            Pacientes pacHis = this.mi.getDatosPedidoHis(paciente1);///saca id_pedido de hoy

            try {
                if (pacHis != null) {
                    if (pacHis.getId_pais() == cliente.getCod_esta() && pacHis.getId_factura() > 0 && pacHis.getPrecio_total() == Float.parseFloat(costo_unit) && "C".equals(datosHistorialRe.getId_estado())) {
                        return new ModelAndView("Aviso", "mensaje", "Esta Factura Yaaaaa fue Impresa");
                    }
                }
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "Esta factura ya fue emitida, consulte Administrador");
            }

            Pacientes dato = new Pacientes();
            if("".equals(id_pedido) || id_pedido == null){
                dato.setNombres(nombres);
                dato.setPrecio_total(Float.parseFloat(costo_unit));
                dato.setId_paciente(0);
                dato.setId_estado("E");
                dato.setNum_cladoc(num_cladoc);
                dato.setNit(ciu);
                dato.setId_rubro(Integer.parseInt(id_rubro));
                dato.setId_persona(Integer.parseInt(id_persona));
                if(datosHistorialRe != null){
                    dato.setId_dispensa(datosHistorialRe.getId_persona());
                }else{
                    dato.setId_dispensa(Integer.parseInt(id_persona));
                }
                dato.setId_carpeta(Integer.parseInt(id_reservacion));
                dato.setId_paciente(Integer.parseInt(id_paciente));
                dato.setTipo("C");
                dato.setId_factura(0);/////0 sin factura, 1 con factura, 2, 3, 4, para num correlativo, entradas, ajuste+ y ajuste-
                dato.setFec_registro(fecha1);
                dato.setCod_esta(cliente.getCod_esta());
                this.mi.setCrearPeedido(dato);

                id_pedido = Integer.toString(this.mi.getNumPedido(dato));
                Detalle detalle = new Detalle();
                detalle.setId_costo(Integer.parseInt(id_costo));
                detalle.setId_pedido(Integer.parseInt(id_pedido));
                detalle.setEntrada(Float.parseFloat(costo_unit));
                detalle.setSalida(Float.parseFloat(costo_unit));
                detalle.setId_rubro(Integer.parseInt(id_rubro));
                detalle.setIndicacion(observa.trim());
                detalle.setUlt_usuario(Integer.parseInt(id_persona));
                detalle.setCod_esta(cliente.getCod_esta());
                this.mi.setCrearDetalle(detalle);
            } else {
                Pacientes paciente11= new Pacientes() ;
                paciente11.setId_pedido(Integer.parseInt(id_pedido));
                paciente11.setCod_esta(cliente.getCod_esta());
                Pacientes paciente = this.mi.getDatosPedido(paciente11) ; //////////////
                dato.setId_pedido(Integer.parseInt(id_pedido));
                dato.setCod_esta(cliente.getCod_esta());
                dato.setNum_cladoc(paciente.getNum_cladoc());
                dato.setNombres(paciente.getNombres());
                dato.setNit(ciu);
                dato.setId_estado("C"); ///19-02-2018 no actualiza el estado de pago solo historial
                dato.setId_persona(cliente.getId_persona());////se cobrara con una persona despues se cambiaba con el de enfermeria
                dato.setId_dispensa(paciente.getId_dispensa());
                dato.setId_paciente(paciente.getId_paciente());
                dato.setId_rubro(paciente.getId_rubro());
                dato.setPrecio_total(paciente.getPrecio_total());
                dato.setFecha_fin(paciente.getFec_registro()); 
                dato.setId_carpeta(paciente.getId_carpeta());
                this.mi.setModificarPedido(dato);
                ///////////////se quita comentario para que actualice quien cobro en caja
            }
            ////crea dato en facturas
            dato.setNit_fact(Long.parseLong(ciu));
            dato.setNombre(nombres.toUpperCase());
            dato.setNum_fact(datoestab.getNum_fact());////para la tabla facturas
            dato.setNum_auto(Long.parseLong(datoestab.getNum_auto().trim()));
            dato.setFec_registro(fecha1);
            dato.setImporte(Double.parseDouble(sumatot1));
            dato.setIce(0);
            dato.setIme(0);
            dato.setPrecio_total(Double.parseDouble(sumatot1));
            dato.setTotal(Double.parseDouble(sumatot1) * 0.13);
            dato.setId_estado("V");
            dato.setCarnet(codigo);
            dato.setId_pedido(Integer.parseInt(id_pedido));
            dato.setId_persona(Integer.parseInt(id_persona));
            dato.setCod_esta(cliente.getCod_esta());
            dato.setVeces(datoestab.getCod_esta());
            this.mi.setCrearFactura(dato);  ///setCrearFactura y actualiza en pedidos id_estado='E'

            ///Historiales dato= new Historiales(); 
            ///creo que esta demas ya se elimino desde enfermeria 
            datohi.setId_reservacion(Integer.parseInt(id_reservacion));
            datohi.setCod_esta(cliente.getCod_esta());
            datohi.setId_estado("C");
            this.mi.setModificarPagoReserva(datohi);

            return new ModelAndView(new Factura_1copiaPDF(), modelo);
        }

        if ("Terminar".equals(accion)) {
            // almacenar el monto de cobro
            String costo_unit = request.getParameter("precio");
            buscarPaciente.setCod_esta(cliente.getCod_esta());
            num_cladoc = Long.toString(this.mi.getNumClaDoc(buscarPaciente));

            if ("3".equals(sw) || "5".equals(sw)) {
                //busca el Id_Reservacion si ya existe alguna atencion en el dia de hoy 
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
                /*if("Enfer".equals(swenfer)){/////fallas apublica
                if(!(listasi.size()>0 || listas.size()>0)){
                   Personas buscarEmpleado = this.mi.getDatosPersona(Integer.parseInt(id_persona));
                   Historiales reserva = new Historiales(); 
                   reserva.setId_paciente(Integer.parseInt(id_paciente) );
                   reserva.setId_consultorio(buscarEmpleado.getId_consultorio());       
                   reserva.setEdad(buscarPaciente.getEdad());
                   reserva.setFecha(fecha1);
                   reserva.setId_estado("C");
                   reserva.setExpedido("E");
                   reserva.setId_persona(0);
                   reserva.setUlt_usuario(usuario.getId_usuario());
                   reserva.setCod_esta(cliente.getCod_esta());////29-03-2014
                   this.mi.setCrearReservacion(reserva); ///crea una reserva en tabla reservaciones
                   reserva.setId_estado("%");
                   reserva.setAccion("Z");

                   List listarHG = this.mi.getListarCobroReservaUni(reserva);
                   Historiales listaHG = (Historiales) listarHG.get(listarHG.size()-1); 
                   id_reservacion=Integer.toString(listaHG.getId_reservacion());
                 }
          }
                 */

                Pacientes paciente1 = new Pacientes();
                paciente1.setId_pedido(Integer.parseInt(id_pedido));
                paciente1.setCod_esta(cliente.getCod_esta());
                Pacientes paciente = this.mi.getDatosPedido(paciente1); //////////////

                paciente.setId_estado("C");
                paciente.setFecha_fin(paciente.getFec_registro());
                paciente.setId_persona(Integer.parseInt(id_persona));
                paciente.setId_carpeta(paciente.getId_carpeta());
                paciente.setPrecio_total(Float.parseFloat(costo_unit));
                this.mi.setModificarPedido(paciente);

                /*Detalle detalles = new Detalle();
        detalles.setEntrada(Float.parseFloat(costo_unit));
        detalles.setSalida(Float.parseFloat(costo_unit)) ; 
        detalles.setId_costo(0);
        detalles.setId_pedido(Integer.parseInt(id_pedido));
        detalles.setUlt_usuario(Integer.parseInt(id_persona));
        detalle.setCod_esta(cliente.getCod_esta());
        this.mi.setCrearDetalle(detalles) ; */
                modelo.put("sw", request.getParameter("sw"));
            } else {
                Historiales datohi = new Historiales();
                datohi.setId_historial(Integer.parseInt(id_reservacion));
                datohi.setAccion("R");
                datohi.setCod_esta(cliente.getCod_esta());
                Historiales datosHistorialRe = this.mi.getDatosReserva(datohi);

                Pacientes dato = new Pacientes();
                dato.setNombres(nombres);
                dato.setPrecio_total(Float.parseFloat(costo_unit));
                dato.setId_paciente(0);
                dato.setId_estado("E");
                dato.setNum_cladoc(num_cladoc);
                dato.setNit(ciu);
                dato.setId_costo(Integer.parseInt(id_costo));
                dato.setId_rubro(Integer.parseInt(id_rubro));
                dato.setId_persona(Integer.parseInt(id_persona));
                if (datosHistorialRe != null) {
                    dato.setId_dispensa(datosHistorialRe.getId_persona());
                } else {
                    dato.setId_dispensa(Integer.parseInt(id_persona));
                }
                dato.setId_carpeta(Integer.parseInt(id_reservacion));
                dato.setId_paciente(Integer.parseInt(id_paciente));
                dato.setTipo("C");
                dato.setId_factura(0);
                dato.setFec_registro(fecha1);
                dato.setCod_esta(cliente.getCod_esta());
                this.mi.setCrearPeedido(dato);

                id_pedido = Integer.toString(this.mi.getNumPedido(dato));
                Detalle detalle = new Detalle();
                detalle.setId_costo(Integer.parseInt(id_costo));
                detalle.setId_pedido(Integer.parseInt(id_pedido));
                detalle.setEntrada(Float.parseFloat(costo_unit));
                detalle.setSalida(Float.parseFloat(costo_unit));
                detalle.setId_rubro(Integer.parseInt(id_rubro));
                detalle.setIndicacion(observa.trim());
                detalle.setUlt_usuario(Integer.parseInt(id_persona));
                detalle.setCod_esta(cliente.getCod_esta());
                this.mi.setCrearDetalle(detalle);
                modelo.put("id_pedido", id_pedido);

                if ("2".equals(sw)) {
                    //if ("F".equals(datoestab.getArea())){  // para HGSJDD no lo borrara de la tabla reservaciones hasta que no pague
                    //    Historiales elimina = new Historiales();
                    //    elimina.setId_reservacion(Integer.parseInt(id_reservacion) );
                    ///    elimina.setCod_esta(cliente.getCod_esta());
                    //    this.mi.setEliminarReserva(elimina);     ///en reservaciones coloca los pagos en X
                    //    List listarPaises = this.mi.getListarCobroReserva("X");
                    //    modelo.put("milista", listarPaises);
                    //}else{
                    ///Historiales dato= new Historiales();  
                    datohi.setId_reservacion(Integer.parseInt(id_reservacion));
                    datohi.setCod_esta(cliente.getCod_esta());
                    datohi.setId_estado("C");
                    this.mi.setModificarPagoReserva(datohi);
                    Historiales datoh = new Historiales();
                    datoh.setId_estado("P");
                    datoh.setCod_esta(cliente.getCod_esta());
                    List listarPaises = this.mi.getListarCobroReserva(datoh);
                    modelo.put("milista", listarPaises);

                    //} 
                }
            }
            // pacientes a cobrar enviados por recepcion
            // pacientes a cobrar enviados por odontologia
            Pacientes paciente = new Pacientes();
            paciente.setId_estado("A");
            paciente.setId_rubro(3);
            paciente.setId_localidad(cliente.getCod_esta());
            List listarOdon = this.mi.getListarCobroRubroOdon(paciente);
            modelo.put("listaOdon", listarOdon);

            // pacientes a cobrar enviados por enfermeria
            paciente.setId_rubro(5);
            List listarenfer = this.mi.getListarCobroRubro(paciente);
            modelo.put("listaEnfer", listarenfer);

            if ("Enfer".equals(swenfer) && !("1".equals(swe))) {
                List listarCo = this.mi.getListarCobroRubro(paciente);
                modelo.put("milista", listarCo);

                // pacientes a cobrar enviados por odontologia
                paciente.setId_estado("C");
                List listarOdon2 = this.mi.getListarCobroRubro(paciente);
                modelo.put("listaOdon", listarOdon2);

                Cuadernos cua6 = new Cuadernos();
                cua6.setId_persona(cliente.getId_persona());
                cua6.setCod_esta(cliente.getCod_esta());
                List listarAtendidos = this.mi.getListaPacientesCuaderno6(cua6);
                modelo.put("listarAtendidos", listarAtendidos);

                return new ModelAndView("administrarenfermeria/ListarCobroReserva", modelo);

            } else if ("Terminar".equals(accion) && datoestab.getId_pais() == 1) {
                String sumatot1 = request.getParameter("precio");
                int sumatot = (int) (Double.parseDouble(sumatot1));
                long na = Long.parseLong(datoestab.getNum_auto().trim());
                long fact = datoestab.getNum_fact();
                long nit2 = Long.parseLong(datoestab.getNit());
                String llave = datoestab.getLlave();
                Date fecha = new Date();

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
                int total = ((int) sumatot);
                codigoControl numer22 = new codigoControl(na, fact, nit2, fec, total, llave);
                String codigo = numer22.verCodigoControl();
                modelo.put("dato", cliente);
                modelo.put("estab", Estab12);
                Pacientes datojef = mi.getDatosPaciente(Integer.parseInt(id_paciente));
                modelo.put("persona", datojef);

                Rubros dfact = this.mi.getDatosRubro(Integer.parseInt(id_rubro));
                modelo.put("rubro", dfact.getRubro());
                if ("".equals(id_pedido)) {
                    Costos costo = new Costos();
                    costo.setId_costo(Integer.parseInt(id_costo));
                    costo.setCod_esta(cliente.getCod_esta());
                    Costos listaDet = this.mi.getDatosCosto(costo);
                    modelo.put("costo1", listaDet.getCosto());
                } else {
                    Detalle datodet = new Detalle();
                    datodet.setId_pedido(Integer.parseInt(id_pedido));
                    datodet.setId_carpeta(cliente.getCod_esta());
                    datodet.setCod_esta(cliente.getCod_esta());
                    List listaDet = this.mi.getListarCobroDetalle(datodet);
                    modelo.put("costo", listaDet);
                }

                Pacientes paciente1 = new Pacientes();
                paciente1.setId_pedido(Integer.parseInt(id_pedido));
                paciente1.setCod_esta(cliente.getCod_esta());
                Pacientes pedido = this.mi.getDatosPedido(paciente1); //////////////
                modelo.put("pedido", pedido);

                Departamentos buscardepartamento = this.mi.getDatosDepartamento(datoestab.getId_departamento()); // saca un registro a ser modificado
                modelo.put("departamento", buscardepartamento);

                modelo.put("rubrocosto", sumatot1);
                modelo.put("nombreusuario", nombres.toUpperCase());
                modelo.put("ciu", ciu);
                modelo.put("nit", nit);
                modelo.put("codigo", codigo);
                return new ModelAndView(new ReciboPDF(), modelo);
            } else {
                return new ModelAndView("administrarcobranza/ListarCobroReserva", modelo);
            }

        }
        return new ModelAndView("administrarcobranza/CobrarPaciente1", modelo);
    }
}
