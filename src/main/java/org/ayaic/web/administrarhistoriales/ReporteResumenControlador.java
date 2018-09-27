package org.ayaic.web.administrarhistoriales;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Cuadernos;
import org.ayaic.domain.Detalle;
import org.ayaic.domain.Historiales;
import org.ayaic.domain.Localidades;
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.Prestaciones;
import org.ayaic.domain.Recetas;
import org.ayaic.domain.Rubros;
import org.ayaic.domain.Seguros;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ReporteResumenControlador {

    private final MiFacade mi;

    public ReporteResumenControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/ReporteResumen.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String id_historial = request.getParameter("id_historial");
        String id_paciente = request.getParameter("id_paciente");
        String id_persona = request.getParameter("id_persona");
        String accion = request.getParameter("accion");
        String[] dias = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
        String[] meses = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        String[] horas = {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"};
        String[] minutos = {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"};
        String diaq1 = request.getParameter("diaq1");
        String mesq1 = request.getParameter("mesq1");
        Date fechaact = new Date();
        Date fecha1 = new Date();
        int anioq = fecha1.getYear() + 1900;
        String[] anios = {Integer.toString(anioq), Integer.toString(anioq - 1), Integer.toString(anioq - 2), Integer.toString(anioq - 3), Integer.toString(anioq - 4), Integer.toString(anioq - 5), Integer.toString(anioq - 6), Integer.toString(anioq - 7), Integer.toString(anioq - 8), Integer.toString(anioq - 9), Integer.toString(anioq - 10)};

        //InetAddress ip;
        //ip = InetAddress.getLocalHost();
        String ip = request.getRemoteAddr();
        String host = request.getRemoteHost();
        double tot = 0.0, total = 0.0, total2 = 0.0, total3 = 0.0, totalcfc = 0.0, totalcff = 0.0, totalsfc = 0.0, totalsff = 0.0, totaldetf = 0.0, totaldeudapag = 0.0;
        //Recuperamos variables del jsp
        Localidades local = new Localidades();
        List Estab1 = this.mi.getListarEsta(local);
        Localidades datoestab = (Localidades) Estab1.get(0);
        local.setArea("U");  ////getListarEstaUsua
        local.setCod_esta(cliente.getCod_esta());
        List Estab2 = this.mi.getListarEstaUsua(local);
        Localidades datoestab2 = (Localidades) Estab2.get(0);
        modelo.put("Factura", Integer.toString(datoestab.getId_pais()));

        String sId_estado = request.getParameter("id_estado");
        String sAccion = request.getParameter("boton");
        modelo.put("anios", anios);
        modelo.put("meses", meses);
        modelo.put("dias", dias);
        modelo.put("horas", horas);
        modelo.put("minutos", minutos);
        modelo.put("anio", Integer.toString(fecha1.getYear() + 1900));
        if (fecha1.getMonth() + 1 < 10) {
            mesq1 = "0" + Integer.toString(fecha1.getMonth() + 1);
        } else {
            mesq1 = Integer.toString(fecha1.getMonth() + 1);
        }

        if (fecha1.getDate() < 10) {
            diaq1 = "0" + Integer.toString(fecha1.getDate());
        } else {
            diaq1 = Integer.toString(fecha1.getDate());
        }
        modelo.put("mes", mesq1);
        modelo.put("dia", diaq1);
        modelo.put("anio2", Integer.toString(fecha1.getYear() + 1900));
        modelo.put("mes2", mesq1);
        modelo.put("dia2", diaq1);
        modelo.put("hora", "00");
        modelo.put("minuto", "00");
        modelo.put("hora2", "23");
        modelo.put("minuto2", "59");

        if ("Buscar".equals(sAccion)) {
            String diai = request.getParameter("diai");
            String mesi = request.getParameter("mesi");
            String anioi = request.getParameter("anioi");
            String horai = request.getParameter("horai");
            String minutoi = request.getParameter("minutoi");
            String diaf = request.getParameter("diaf");
            String mesf = request.getParameter("mesf");
            String aniof = request.getParameter("aniof");
            String horaf = request.getParameter("horaf");
            String minutof = request.getParameter("minutof");
            if (("".equals(diai)) || ("".equals(mesi)) || ("".equals(anioi))) {
                return new ModelAndView("administrarhistoriales/ReporteResumen", modelo);
            } else {
                int iAnio1 = Integer.parseInt(anioi) - 1900;
                int iMes1 = Integer.parseInt(mesi) - 1;
                int iDia1 = Integer.parseInt(diai);
                int iHor1 = Integer.parseInt(horai);
                int iMin1 = Integer.parseInt(minutoi);

                int iAnio2 = Integer.parseInt(aniof) - 1900;
                int iMes2 = Integer.parseInt(mesf) - 1;
                int iDia2 = Integer.parseInt(diaf);
                int iHor2 = Integer.parseInt(horaf);
                int iMin2 = Integer.parseInt(minutof);

                Date dFechaini1 = new Date(iAnio1, iMes1, iDia1, iHor1, iMin1, 00);
                Date dFechafin1 = new Date(iAnio2, iMes2, iDia2, iHor2, iMin2, 59);

                modelo.put("dato", cliente);

                Historiales dato = new Historiales();
                Cuadernos datoc = new Cuadernos();
                Recetas dator = new Recetas();
                Detalle detalle = new Detalle();
                Prestaciones prestac = new Prestaciones();
                prestac.setCod_esta(cliente.getCod_esta());
                
                prestac.setFecha_ini(dFechaini1);
                prestac.setFecha_fin(dFechafin1);
                dato.setFecha_ini(dFechaini1);
                dato.setFecha_fin(dFechafin1);
                datoc.setFecha_ini(dFechaini1);
                datoc.setFecha_fin(dFechafin1);
                dator.setFecha_ini(dFechaini1);
                dator.setFecha_fin(dFechafin1);
                detalle.setFecha_ini(dFechaini1);
                detalle.setFecha_fin(dFechafin1);
                dato.setCod_esta(cliente.getCod_esta());
                datoc.setCod_esta(cliente.getCod_esta());
                dator.setCod_esta(cliente.getCod_esta());
                detalle.setCod_esta(cliente.getCod_esta());

                List listas = this.mi.getListarHistoriaGen(dato);
                modelo.put("milista", listas);

                List listarAtendidos = this.mi.getAtendidosGeneral(dato);
                modelo.put("milistaAten", listarAtendidos);

                List listarSeguros = this.mi.getListarSeguros("A");
                modelo.put("listaPacAfi", listarSeguros);

                List listaPre = this.mi.getPrestacionGen(prestac);
                modelo.put("milistapre", listaPre);

                List listaC1 = this.mi.getCuadernoC1(datoc);
                modelo.put("listaC1", listaC1);
                List listaC2 = this.mi.getCuadernoC2(datoc);
                modelo.put("listaC2", listaC2);
                List listaC3 = this.mi.getCuadernoC3(datoc);
                modelo.put("listaC3", listaC3);
                List listaC4 = this.mi.getCuadernoC4(datoc);
                modelo.put("listaC4", listaC4);
                List listaC5 = this.mi.getCuadernoC5(datoc);
                modelo.put("listaC5", listaC5);
                List listaC6 = this.mi.getCuadernoC6(datoc);
                modelo.put("listaC6", listaC6);
                List listaC7 = this.mi.getCuadernoC7(datoc);
                modelo.put("listaC7", listaC7);
                List listaV = this.mi.getCuadernoV(datoc);
                modelo.put("listaV", listaV);
                List listaLab = this.mi.getLabGen(datoc);
                modelo.put("listaLab", listaLab);

                List listarMed = this.mi.getListaRecetaGen(dator);
                modelo.put("listarMed", listarMed);

                List listarCostos = this.mi.getListarDetalleGen(detalle);
                modelo.put("listarCostos", listarCostos);

                return new ModelAndView("administrarhistoriales/Resumen", modelo);
            }
        }

        if ("Imprimir Economico".equals(accion) || "ImprimirEconomicoContinua".equals(accion)) {
            String id_pedido = request.getParameter("id_pedido");
            String id_seguro = request.getParameter("id_seguro");

            Pacientes buscarPaciente = this.mi.getDatosPaciente(Integer.parseInt(id_paciente)); // saca un registro a ser modificado
            modelo.put("datos", buscarPaciente);
            Historiales datohi = new Historiales();
            datohi.setCod_esta(cliente.getCod_esta());
            datohi.setId_historial(Integer.parseInt(id_historial));
            Historiales datosHistorial = this.mi.getDatosHistorial(datohi);

            modelo.put("historia", datosHistorial);
            modelo.put("cliente", cliente);
            List listarKardex = this.mi.getListarKardexPago(Integer.parseInt(id_historial));
            modelo.put("listarKardex", listarKardex);

            Pacientes dato = new Pacientes();
            dato.setId_provincia(Integer.parseInt(id_historial));
            dato.setId_historial(Integer.parseInt(id_historial));
            dato.setCod_esta(cliente.getCod_esta());
            dato.setId_rubro(1);
            dato.setId_escolaridad(1);
            dato.setTipo("G");
            List listaCobrosFar = this.mi.getDatosPedidoRubroGeneral(dato);////getDatosPedidoRubroGeneral
            modelo.put("listaCobrosFar", listaCobrosFar);
            dato.setTipo("T");
            List listaCobrosTots = this.mi.getDatosPedidoRubrosDet(dato);/////getDatosPedidoRubrosDet
            modelo.put("listaCobrosOtros", listaCobrosTots);

            if (datosHistorial.getId_seguro() == 0) {
                modelo.put("codigoseg", "Externo");
                modelo.put("seguro", "Particular");
            } else {
                Seguros buscarseguro = this.mi.getDatosSeguro(datosHistorial.getId_seguro());   // 30/06/2014
                modelo.put("codigoseg", buscarseguro.getTipo());
                modelo.put("seguro", buscarseguro.getSeguro());
            }

            Detalle detalle = new Detalle();
            detalle.setExpedido("F");
            detalle.setId_pedido(Integer.parseInt(id_historial));
            detalle.setCod_esta(cliente.getCod_esta());
            List listarC = this.mi.getListarDetHistorial(detalle);////getListarDetHistorial
            modelo.put("listarCostosT", listarC);

            for (int j = 0; j < listaCobrosTots.size(); j++) {
                Pacientes datoped = (Pacientes) listaCobrosTots.get(j);
                total += datoped.getPrecio_total();
                total2 += datoped.getTotal();
                if (datoped.getId_factura() == 0) {
                    totalsfc += datoped.getTotal();
                } else {
                    totalcfc += datoped.getTotal();
                }
                if ("PAGOS DE DEUDAS".equals(datoped.getNombre())) {
                    totaldeudapag += datoped.getTotal();
                }
                modelo.put("total", Double.toString(total));
                modelo.put("totalsfc", Double.toString(totalsfc));
                modelo.put("totalcfc", Double.toString(totalcfc));
                modelo.put("total2", Double.toString(total2));
                modelo.put("totaldeudapag", Double.toString(totaldeudapag));
            }

            for (int i = 0; i < listaCobrosFar.size(); i++) {
                Pacientes datoped = (Pacientes) listaCobrosFar.get(i);
                tot += datoped.getPrecio_total();
                if (datoped.getId_factura() == 0) {
                    totalsff += datoped.getPrecio_total();
                } else {
                    totalcff += datoped.getPrecio_total();
                }
                modelo.put("totalfar", Double.toString(tot));
                modelo.put("totalsff", Double.toString(totalsff));
                modelo.put("totalcff", Double.toString(totalcff));
                id_pedido = Integer.toString(datoped.getId_pedido());
            }

            for (int k = 0; k < listarC.size(); k++) {
                Detalle datodet = (Detalle) listarC.get(k);
                if (datodet.getId_empresa() > 0 && datodet.getId_costo() != 0) {
                    totaldetf += datodet.getEntrada();
                }
                modelo.put("totaldetf", Double.toString(totaldetf));
            }

            if ("ImprimirEconomicoContinua".equals(accion)) {
                return new ModelAndView(new ReporteEconomicoIcPDF(), modelo);
            } else {
                return new ModelAndView(new ReporteEconomicoIPDF(), modelo);
            }

        }

        if ("Valorar".equals(accion)) {
            String id_pedido = request.getParameter("id_pedido");

            Recetas dato = new Recetas();
            dato.setId_pedido(Integer.parseInt(id_pedido));
            dato.setId_farmacia(cliente.getId_farmacia());//08/01/2018
            dato.setCod_esta(cliente.getCod_esta());
            dato.setId_estado("V");/////setModificarKardexVenta   recalcula el pedido a precios de venta los medicamentos
            this.mi.setModificarKardexVenta(dato);
            return new ModelAndView("Aviso", "mensaje", "Se Actualizo correctamente a Precios de Venta"); ////02/09/2014
        }

        if ("Pasarlo Plan de Pagos".equals(accion)) {
            String id_seguro = request.getParameter("id_seguro");
            String id_pedido = request.getParameter("id_pedido");
            String nombres = request.getParameter("nombrespac");
            String nombres2 = request.getParameter("nombres");
            String ciu = request.getParameter("ciu");
            String hcl = request.getParameter("hcl");
            String sumatot1 = request.getParameter("totalT");
            String fech = request.getParameter("fec");

            Pacientes buscarPaciente = this.mi.getDatosPaciente(Integer.parseInt(id_paciente)); // saca un registro a ser modificado
            modelo.put("datos", buscarPaciente);

            Historiales datohi = new Historiales();
            datohi.setCod_esta(cliente.getCod_esta());
            datohi.setId_historial(Integer.parseInt(id_historial));
            Historiales datosHistorial = this.mi.getDatosHistorial(datohi);

            modelo.put("historia", datosHistorial);
            modelo.put("cliente", cliente);
            modelo.put("nombres", buscarPaciente.getNombres());
            modelo.put("hcl", Integer.toString(buscarPaciente.getHcl()));
            modelo.put("id_paciente", id_paciente);
            modelo.put("id_historial", id_historial);
            List listarSeguros = this.mi.getListarSeguros("A");
            modelo.put("listaPacAfi", listarSeguros);
            List listarKardex = this.mi.getListarKardexPago(Integer.parseInt(id_historial));
            modelo.put("listarKardex", listarKardex);

            Pacientes dato = new Pacientes();
            dato.setId_provincia(Integer.parseInt(id_historial));
            dato.setCod_esta(cliente.getCod_esta());
            dato.setId_rubro(1);
            dato.setId_escolaridad(1);
            dato.setTipo("G");
            List listaCobrosFar = this.mi.getDatosPedidoRubro(dato);////getDatosPedidoRubroGeneral
            modelo.put("listaCobrosFar", listaCobrosFar);
            dato.setTipo("T");
            List listaCobrosTots = this.mi.getDatosPedidoRubro(dato);////getDatosPedidoRubrosDet
            modelo.put("listaCobrosOtros", listaCobrosTots);

            Detalle detalle = new Detalle();
            detalle.setExpedido("F");
            detalle.setId_pedido(Integer.parseInt(id_historial));
            detalle.setCod_esta(cliente.getCod_esta());
            List listarC = this.mi.getListarDetHistorial(detalle);////getListarDetHistorial
            modelo.put("listarCostosT", listarC);
            id_pedido = "0";
            String estad = "0";
            int fac = 0;
            for (int j = 0; j < listaCobrosTots.size(); j++) {
                Pacientes datoped = (Pacientes) listaCobrosTots.get(j);
                total += datoped.getPrecio_total();
                total2 += datoped.getTotal();
                modelo.put("total", Double.toString(total));
                modelo.put("total2", Double.toString(total2));
                id_pedido = Integer.toString(datoped.getId_pedido());
                estad = datoped.getId_estado();
                fac = datoped.getId_factura();
            }
            for (int i = 0; i < listaCobrosFar.size(); i++) {
                Pacientes datoped = (Pacientes) listaCobrosFar.get(i);
                tot += datoped.getPrecio_total();
                modelo.put("totalfar", Double.toString(tot));
                id_pedido = Integer.toString(datoped.getId_pedido());
                estad = datoped.getId_estado();
                fac = datoped.getId_factura();
            }

            if ("".equals(ciu) || ciu == null) {
                ciu = "0";
            }
            if ("".equals(nombres2) || nombres2 == null) {
                nombres2 = "S/N";
            } else {
                modelo.put("nombres", nombres2.toUpperCase());
            }

            modelo.put("dato", cliente);
            modelo.put("estab", Estab1);
            modelo.put("ciu", ciu);
            modelo.put("numauto", datoestab.getNum_auto());
            modelo.put("numfact", Integer.toString(datoestab.getNum_fact()));
            modelo.put("valortotal", sumatot1);

            return new ModelAndView("administrarhistoriales/PasarPlanPagos", modelo);

        }

        if ("Eliminar Plan de Pagos".equals(accion)) {
            Pacientes datopp = new Pacientes();
            datopp.setTipo("E");
            datopp.setId_estado("Q");  ////elimina el paln de pagos  
            datopp.setId_carpeta(Integer.parseInt(id_historial));
            datopp.setCod_esta(cliente.getCod_esta());
            datopp.setCadena1(ip);
            datopp.setCadena2(host);
            datopp.setId_persona(cliente.getId_persona());///01/09/2014
            this.mi.setModificarPedidoElimPP(datopp); ///setModificarPedidoElimPP

            return new ModelAndView("Aviso", "mensaje", "Se Elimino Correctamente el plan de pagos existente"); ////26/07/2014
        }

        if ("Plan de Pagos".equals(accion)) {
            ///////////lo pasa a plan de pagos al paciemnte 23/05/2014
            Pacientes datopp = new Pacientes();
            datopp.setTipo("M");
            datopp.setId_estado("M");  ///plan de pagos
            datopp.setId_carpeta(Integer.parseInt(id_historial));
            datopp.setCod_esta(cliente.getCod_esta());
            datopp.setCadena1(ip);
            datopp.setCadena2(host);
            datopp.setId_persona(cliente.getId_persona());///01/09/2014
            this.mi.setModificarPedidoPPago(datopp); ////setModificarPedidoPPago

            return new ModelAndView("Aviso", "mensaje", "Se paso Correctamente a plan de pagos, busquelo en Plan de Pagos"); ////31/05/2014
        }

        if ("Plan de Pagos2".equals(accion)) {
            String id_seguro = request.getParameter("id_seguro");
            String id_pedido = request.getParameter("id_pedido");

            Pacientes buscarPaciente = this.mi.getDatosPaciente(Integer.parseInt(id_paciente)); // saca un registro a ser modificado
            modelo.put("datos", buscarPaciente);
            Historiales datohi = new Historiales();
            datohi.setCod_esta(cliente.getCod_esta());
            datohi.setId_historial(Integer.parseInt(id_historial));
            Historiales datosHistorial = this.mi.getDatosHistorial(datohi);

            modelo.put("historia", datosHistorial);
            modelo.put("cliente", cliente);
            modelo.put("precio", "10");
            modelo.put("nombres", buscarPaciente.getNombres());
            modelo.put("hcl", Integer.toString(buscarPaciente.getHcl()));
            modelo.put("id_paciente", id_paciente);
            modelo.put("id_historial", id_historial);
            List listarSeguros = this.mi.getListarSeguros("A");
            modelo.put("listaPacAfi", listarSeguros);
            List listarKardex = this.mi.getListarKardexPago(Integer.parseInt(id_historial));
            modelo.put("listarKardex", listarKardex);

            if ("".equals(buscarPaciente.getCarnet()) || buscarPaciente.getCarnet() == null) {
                modelo.put("ciu", "0");
            } else {
                modelo.put("ciu", buscarPaciente.getCarnet());
            }

            Pacientes dato = new Pacientes();
            dato.setId_provincia(Integer.parseInt(id_historial));
            dato.setCod_esta(cliente.getCod_esta());
            dato.setId_rubro(0);
            dato.setId_escolaridad(1);
            dato.setTipo("G");
            List listaCobrosFar = this.mi.getDatosPedidoRubro(dato);////getDatosPedidoRubroGeneral
            modelo.put("listaCobrosFar", listaCobrosFar);
            dato.setTipo("O");
            List listaCobrosTots = this.mi.getDatosPedidoRubro(dato);
            modelo.put("listaCobrosOtros", listaCobrosTots);
            dato.setTipo("P");
            List listapagos = this.mi.getReporteEconomicoPPago(dato);
            modelo.put("listafacturas", listapagos);

            id_pedido = "0";
            String estad = "0";
            int fac = 0;
            for (int j = 0; j < listapagos.size(); j++) {
                Pacientes datoped = (Pacientes) listapagos.get(j);
                if ("V".equals(datoped.getId_estado())) {
                    total3 += datoped.getPrecio_total();
                    //total3+=datoped.getTotal();
                    modelo.put("total", Double.toString(total));
                    modelo.put("total3", Double.toString(total3));
                    id_pedido = Integer.toString(datoped.getId_pedido());
                    estad = datoped.getId_estado();
                    fac = datoped.getId_factura();
                }

            }

            Detalle detalle = new Detalle();
            detalle.setExpedido("F");
            detalle.setId_pedido(Integer.parseInt(id_historial));
            detalle.setCod_esta(cliente.getCod_esta());
            List listarC = this.mi.getListarDetHistorial(detalle);////getListarDetHistorial
            modelo.put("listarFac", listarC);

            for (int j = 0; j < listaCobrosTots.size(); j++) {
                Pacientes datoped = (Pacientes) listaCobrosTots.get(j);
                if (datoped.getId_rubro() != 1) {
                    total += datoped.getPrecio_total();
                    total2 += datoped.getTotal();
                    modelo.put("total", Double.toString(total));
                    modelo.put("total2", Double.toString(total2));
                    id_pedido = Integer.toString(datoped.getId_pedido());
                    estad = datoped.getId_estado();
                    fac = datoped.getId_factura();
                }

            }
            for (int i = 0; i < listaCobrosFar.size(); i++) {
                Pacientes datoped = (Pacientes) listaCobrosFar.get(i);
                if (datoped.getId_rubro() == 1) {
                    tot += datoped.getPrecio_total();
                    modelo.put("totalfar", Double.toString(tot));
                    id_pedido = Integer.toString(datoped.getId_pedido());
                    estad = datoped.getId_estado();
                    fac = datoped.getId_factura();
                }

            }
            //modelo.put("total2", Double.toString(total2));
            //modelo.put("total3", Double.toString(total3));///
            //modelo.put("totalfar", Double.toString(tot));

            return new ModelAndView("administrarhistoriales/VerPlanPagos", modelo);
        }

        if ("Emitir FACTURA".equals(accion)) {
            String nombres = request.getParameter("nombres");
            String id_pedido = request.getParameter("id_pedido");
            String ciu = request.getParameter("ciu");
            String sumatot1 = request.getParameter("precio");
            String fech = request.getParameter("fec");
            int sumatot = (int) (Double.parseDouble(sumatot1));

            Pacientes buscarPaciente = this.mi.getDatosPaciente(Integer.parseInt(id_paciente)); // saca un registro a ser modificado
            modelo.put("datos", buscarPaciente);
            Historiales datohi = new Historiales();
            datohi.setCod_esta(cliente.getCod_esta());
            datohi.setId_historial(Integer.parseInt(id_historial));
            Historiales datosHistorial = this.mi.getDatosHistorial(datohi);

            modelo.put("historia", datosHistorial);
            modelo.put("cliente", cliente);
            modelo.put("nombres", nombres);
            modelo.put("id_paciente", id_paciente);
            modelo.put("id_historial", id_historial);
            List listarSeguros = this.mi.getListarSeguros("A");
            modelo.put("listaPacAfi", listarSeguros);
            List listarKardex = this.mi.getListarKardexPago(Integer.parseInt(id_historial));
            modelo.put("listarKardex", listarKardex);

            Pacientes dato = new Pacientes();
            dato.setId_provincia(Integer.parseInt(id_historial));
            dato.setCod_esta(cliente.getCod_esta());
            dato.setId_rubro(1);
            dato.setId_escolaridad(1);
            dato.setTipo("G");
            List listaCobrosFar = this.mi.getDatosPedidoRubro(dato);////getDatosPedidoRubroGeneral
            modelo.put("listaCobrosFar", listaCobrosFar);
            dato.setTipo("T");
            List listaCobrosTots = this.mi.getDatosPedidoRubrosDet(dato);////getDatosPedidoRubrosDet
            modelo.put("listaCobrosOtros", listaCobrosTots);

            Detalle detalle = new Detalle();
            detalle.setExpedido("F");
            detalle.setId_pedido(Integer.parseInt(id_historial));
            detalle.setCod_esta(cliente.getCod_esta());
            List listarC = this.mi.getListarDetHistorial(detalle);////getListarDetHistorial
            modelo.put("listarCostosT", listarC);
            id_pedido = "0";
            String estad = "0";
            int fac = 0;

            for (int k = 0; k < listarC.size(); k++) {
                Detalle datodet = (Detalle) listarC.get(k);
                if (datodet.getId_empresa() > 0) {////saca el monto de las facturas ya emitidas
                    totaldetf += datodet.getEntrada();
                }
                if (datodet.getId_empresa() > 0 && datodet.getId_costo() == 0) {////saca el monto de las facturas ya emitidas
                    totaldeudapag += datodet.getEntrada();
                }
                modelo.put("totaldetf", Double.toString(totaldetf));
            }

            for (int j = 0; j < listaCobrosTots.size(); j++) {
                Pacientes datoped = (Pacientes) listaCobrosTots.get(j);
                total += datoped.getPrecio_total();
                total2 += datoped.getTotal();
                modelo.put("total", Double.toString(total));
                modelo.put("total2", Double.toString(total2));
                id_pedido = Integer.toString(datoped.getId_pedido());
                estad = datoped.getId_estado();
                fac = datoped.getId_factura();
            }
            for (int i = 0; i < listaCobrosFar.size(); i++) {
                Pacientes datoped = (Pacientes) listaCobrosFar.get(i);
                tot += datoped.getPrecio_total();
                modelo.put("totalfar", Double.toString(tot));
                id_pedido = Integer.toString(datoped.getId_pedido());
                estad = datoped.getId_estado();
                fac = datoped.getId_factura();
            }

            if ("E".equals(estad) && fac > 0) {
                modelo.put("estadfact", "impresa");
                return new ModelAndView("Aviso", "mensaje", "Esta Factura Ya fue Impresa");
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
            }
            if (dia1 < 10) {
                dia11 = "0" + dia11;
            }
            String ff = anio11 + mes11 + dia11;

            long fec = Long.parseLong(ff);
            int total2f = (int) (Math.round(((float) (Double.parseDouble(sumatot1)))));
            long carne = Long.parseLong(ciu);
            codigoControl numer22 = new codigoControl(na, fact, carne, fec, total2f, llave);
            String codigo = numer22.verCodigoControl();//////////////////////////////////
            modelo.put("dato", cliente);
            modelo.put("estab", Estab1);
            modelo.put("ciu", ciu);
            modelo.put("numauto", datoestab.getNum_auto());
            modelo.put("numfact", Integer.toString(datoestab.getNum_fact()));
            modelo.put("codigo", codigo);
            modelo.put("valortotal", sumatot1);

            if ("".equals(nombres) || nombres == null) {
                nombres = buscarPaciente.getNombres();
            } else {
                modelo.put("nombres", nombres.toUpperCase());
            }
            //Pacientes dato = new Pacientes();
            dato.setNombres(buscarPaciente.getNombres());
            dato.setPrecio_total(Float.parseFloat(sumatot1));
            dato.setId_paciente(0);
            dato.setId_estado("E");
            dato.setNum_cladoc(ciu);
            dato.setNit(ciu);
            dato.setId_rubro(0);
            dato.setId_persona(cliente.getId_persona());
            dato.setId_dispensa(cliente.getId_persona());
            dato.setId_carpeta(Integer.parseInt(id_historial));
            dato.setId_paciente(Integer.parseInt(id_paciente));
            dato.setTipo("C");
            dato.setId_factura(0);/////0 sin factura, 1 con factura, 2, 3, 4, para num correlativo, entradas, ajuste+ y ajuste-
            dato.setFec_registro(fecha1);
            dato.setCod_esta(cliente.getCod_esta());
            this.mi.setCrearPeedido(dato);

            id_pedido = Integer.toString(this.mi.getNumPedido(dato));
            detalle.setId_costo(0);
            detalle.setId_pedido(Integer.parseInt(id_pedido));
            detalle.setEntrada(Float.parseFloat(sumatot1));
            detalle.setSalida(Float.parseFloat(sumatot1));
            detalle.setId_rubro(0);
            detalle.setUlt_usuario(cliente.getId_persona());
            detalle.setCod_esta(cliente.getCod_esta());
            this.mi.setCrearDetalle(detalle);

            Pacientes datofac = new Pacientes();  ////crea dato en facturas
            datofac.setNit_fact(Long.parseLong(ciu));
            datofac.setNombre(nombres);
            datofac.setNum_fact(datoestab.getNum_fact());////para la tabla facturas
            datofac.setNum_auto(Long.parseLong(datoestab.getNum_auto().trim()));
            datofac.setFec_registro(fecha1);
            datofac.setImporte(Double.parseDouble(sumatot1));
            datofac.setIce(0);
            datofac.setIme(0);
            datofac.setPrecio_total(Double.parseDouble(sumatot1));
            datofac.setTotal(Double.parseDouble(sumatot1) * 0.13);
            datofac.setId_estado("V");
            datofac.setCarnet(codigo);
            datofac.setId_pedido(Integer.parseInt(id_pedido));
            datofac.setId_persona(cliente.getId_persona());
            datofac.setCod_esta(cliente.getCod_esta());
            datofac.setId_carpeta(Integer.parseInt(id_historial));
            this.mi.setCrearFactura(datofac);
            totalsff = tot + total2 + total3 - totaldetf - (totaldeudapag);
            if ((float) (Double.parseDouble(sumatot1)) >= tot + total2 - totaldetf - (totaldeudapag)) {
                Pacientes datopp = new Pacientes();
                datopp.setTipo("E");
                datopp.setId_estado("Q");  ////elimina el plan de pagos  
                datopp.setId_carpeta(Integer.parseInt(id_historial));
                datopp.setCod_esta(cliente.getCod_esta());
                datopp.setCadena1(ip);///01/09/2014
                datopp.setCadena2(host);///01/09/2014
                datopp.setId_persona(cliente.getId_persona());///01/09/2014
                this.mi.setModificarPedidoElimPP(datopp);     ///setModificarPedidoElimPP
            }

            return new ModelAndView(new FacturaPPagoPDF(), modelo);
        }

        if ("Imprimir Factura".equals(accion)) {
            String id_seguro = request.getParameter("id_seguro");
            String id_pedido = request.getParameter("id_pedido");
            String nombres = request.getParameter("nombrespac");
            String nombres2 = request.getParameter("nombres");
            String ciu = request.getParameter("ciu");
            String hcl = request.getParameter("hcl");
            String sumatot1 = request.getParameter("totalT");
            String fech = request.getParameter("fec");
            int sumatot = (int) (Double.parseDouble(sumatot1));

            Pacientes buscarPaciente = this.mi.getDatosPaciente(Integer.parseInt(id_paciente)); // saca un registro a ser modificado
            modelo.put("datos", buscarPaciente);
            Historiales datohi = new Historiales();
            datohi.setCod_esta(cliente.getCod_esta());
            datohi.setId_historial(Integer.parseInt(id_historial));
            Historiales datosHistorial = this.mi.getDatosHistorial(datohi);

            modelo.put("historia", datosHistorial);
            modelo.put("cliente", cliente);
            modelo.put("nombres", nombres2);
            modelo.put("hcl", hcl);
            modelo.put("id_paciente", id_paciente);
            modelo.put("id_historial", id_historial);
            List listarSeguros = this.mi.getListarSeguros("A");
            modelo.put("listaPacAfi", listarSeguros);
            List listarKardex = this.mi.getListarKardexPago(Integer.parseInt(id_historial));
            modelo.put("listarKardex", listarKardex);

            Pacientes dato = new Pacientes();
            dato.setId_provincia(Integer.parseInt(id_historial));
            dato.setCod_esta(cliente.getCod_esta());
            dato.setId_rubro(1);
            dato.setId_escolaridad(1);
            dato.setTipo("N");   ////G
            List listaCobrosFar = this.mi.getDatosPedidoRubroGeneralSf(dato);  ////getDatosPedidoRubroGeneralSf
            modelo.put("listaCobrosFar", listaCobrosFar);
            dato.setTipo("F");  ////T
            List listaCobrosTots = this.mi.getDatosPedidoRubrosDetSf(dato);   ///getDatosPedidoRubrosDetSf id_factura=0 sin facturar
            modelo.put("listaCobrosOtros", listaCobrosTots);
            dato.setTipo("P");
            List listaCobrosTotsPag = this.mi.getDatosPedidoRubrosDetSfPag(dato);   ////getDatosPedidoRubrosDetSfPag
            modelo.put("listaCobrosOtrosPag", listaCobrosTotsPag);

            Detalle detalle = new Detalle();
            detalle.setExpedido("F");
            detalle.setId_pedido(Integer.parseInt(id_historial));
            detalle.setCod_esta(cliente.getCod_esta());
            List listarC = this.mi.getListarDetHistorial(detalle);
            modelo.put("listarCostosT", listarC);
            id_pedido = "0";
            String estad = "0";
            int fac = 0;
            totaldeudapag = 0;
            for (int k = 0; k < listaCobrosTotsPag.size(); k++) {////////para deudas 15-03-2015
                Pacientes datoped = (Pacientes) listaCobrosTotsPag.get(k);
                total += datoped.getPrecio_total();
                total2 += datoped.getTotal();
                if (datoped.getId_factura() == 0) {
                    totalsfc += datoped.getTotal(); /////totalsfc+totalsff-totaldeudapag lo que muestra otra pagina
                } else {
                    totalsfc += datoped.getTotal();
                }
                if ("PAGOS DE DEUDAS".equals(datoped.getNombre())) {
                    totaldeudapag += datoped.getTotal();
                }
                modelo.put("totaldeudapag", Double.toString(totaldeudapag));
            }////////para deudas 15-03-2015
            totalsfc = 0;
            totalsfc = 0;  /////no se para que estaba de arriba el for esta duplicando 19-11-2015
            for (int j = 0; j < listaCobrosTots.size(); j++) {
                Pacientes datoped = (Pacientes) listaCobrosTots.get(j);
                total += datoped.getPrecio_total();
                total2 += datoped.getTotal();
                if (datoped.getId_factura() == 0) {
                    totalsfc += datoped.getTotal();
                } else {
                    totalcfc += datoped.getTotal();
                }
                if ("PAGOS DE DEUDAS".equals(datoped.getNombre())) {
                    totaldeudapag += datoped.getTotal();
                }
                modelo.put("total", Double.toString(total));
                modelo.put("total2", Double.toString(total2));
                modelo.put("totalsfc", Double.toString(totalsfc));
                modelo.put("totalcfc", Double.toString(totalcfc));
                modelo.put("totaldeudapag", Double.toString(totaldeudapag));
                id_pedido = Integer.toString(datoped.getId_pedido());
                estad = datoped.getId_estado();
                fac = datoped.getId_factura();
            }
            for (int i = 0; i < listaCobrosFar.size(); i++) {
                Pacientes datoped = (Pacientes) listaCobrosFar.get(i);
                tot += datoped.getPrecio_total();
                if (datoped.getId_factura() == 0) {
                    totalsff += datoped.getPrecio_total();
                } else {
                    totalcff += datoped.getPrecio_total();
                }
                modelo.put("totalfar", Double.toString(tot));
                modelo.put("totalsff", Double.toString(totalsff));
                modelo.put("totalcff", Double.toString(totalcff));
                id_pedido = Integer.toString(datoped.getId_pedido());
                estad = datoped.getId_estado();
                fac = datoped.getId_factura();

                Recetas midato = new Recetas();
                midato.setId_pedido(Integer.parseInt(id_pedido));
                midato.setId_historial(Integer.parseInt(id_historial));///// 10/02/2016 se aumenta no jalaba SI
                midato.setId_prestacion(cliente.getCod_esta());
                midato.setCod_esta(cliente.getCod_esta());
                midato.setId_farmacia(cliente.getId_farmacia());
                ///List listarKardexf = this.mi.getListarKardex(midato);
                List listarKardexf = this.mi.getListarKardexI(midato);

                if (listarKardexf.size() > 16) {
                    modelo.put("taman", "mucho");
                }
                modelo.put("listaKf", listarKardexf);  ////Manda el detalle de medicamentos

            }

            if ("E".equals(estad) && fac > 0 && totalsfc == 0 && totalsff == 0) {
                modelo.put("estadfact", "impresa");
                return new ModelAndView("Aviso", "mensaje", "Esta Factura Ya fue Impresa");
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
            }
            if (dia1 < 10) {
                dia11 = "0" + dia11;
            }
            String ff = anio11 + mes11 + dia11;

            if ("".equals(ciu) || ciu == null) {
                ciu = "0";
            }
            if ("".equals(nombres2) || nombres2 == null) {
                nombres2 = "S/N";
            } else {
                modelo.put("nombres", nombres2.toUpperCase());
            }

            long fec = Long.parseLong(ff);
            int total2f = (int) (Math.round(((float) (Double.parseDouble(sumatot1)))));
            long carne = Long.parseLong(ciu);
            codigoControl numer22 = new codigoControl(na, fact, carne, fec, total2f, llave);
            String codigo = numer22.verCodigoControl();
            modelo.put("dato", cliente);
            modelo.put("estab", Estab1);
            modelo.put("ciu", ciu);
            modelo.put("numauto", datoestab.getNum_auto());
            modelo.put("numfact", Integer.toString(datoestab.getNum_fact()));
            modelo.put("codigo", codigo);
            modelo.put("valortotal", sumatot1);

            return new ModelAndView("administrarhistoriales/ResumenFactura", modelo);
        }

        if ("Impresora/Inyeccion".equals(accion)) {
            String id_seguro = request.getParameter("id_seguro");
            String id_pedido = request.getParameter("id_pedido");
            String nombres = request.getParameter("nombrespac");
            String ciu = request.getParameter("ciu");
            String sumatot1 = request.getParameter("totalT");
            String fech = request.getParameter("fec");
            int sumatot = (int) (Double.parseDouble(sumatot1));

            Pacientes buscarPaciente = this.mi.getDatosPaciente(Integer.parseInt(id_paciente)); // saca un registro a ser modificado
            modelo.put("datos", buscarPaciente);
            Historiales datohi = new Historiales();
            datohi.setCod_esta(cliente.getCod_esta());
            datohi.setId_historial(Integer.parseInt(id_historial));
            Historiales datosHistorial = this.mi.getDatosHistorial(datohi);

            modelo.put("historia", datosHistorial);
            modelo.put("cliente", cliente);
            modelo.put("nombreusuario", nombres);
            List listarSeguros = this.mi.getListarSeguros("A");
            modelo.put("listaPacAfi", listarSeguros);
            List listarKardex = this.mi.getListarKardexPago(Integer.parseInt(id_historial));
            modelo.put("listarKardex", listarKardex);

            Pacientes dato = new Pacientes();
            dato.setId_provincia(Integer.parseInt(id_historial));
            dato.setCod_esta(cliente.getCod_esta());
            dato.setId_rubro(1);
            dato.setId_escolaridad(1);
            dato.setTipo("N");
            List listaCobrosFar = this.mi.getDatosPedidoRubroGeneralSf(dato);   ///getDatosPedidoRubroGeneralSf
            modelo.put("listaCobrosFar", listaCobrosFar);
            dato.setTipo("F");
            List listaCobrosTots = this.mi.getDatosPedidoRubrosDetSf(dato);   ///getDatosPedidoRubrosDetSf id_factura=0 los que no facturaron
            modelo.put("listaCobrosOtros", listaCobrosTots);
            dato.setTipo("P");
            List listaCobrosTotsPag = this.mi.getDatosPedidoRubrosDetSfPag(dato);   ///getDatosPedidoRubrosDetSfPag
            modelo.put("listaCobrosOtrosPag", listaCobrosTotsPag);

            Detalle detalle = new Detalle();
            detalle.setExpedido("F");
            detalle.setId_pedido(Integer.parseInt(id_historial));
            detalle.setCod_esta(cliente.getCod_esta());
            List listarC = this.mi.getListarDetHistorial(detalle);    ////getListarDetHistorial
            modelo.put("listarCostosT", listarC);
            modelo.put("nombrepac", buscarPaciente.getNombres());
            String estad = "0";
            String id_pedido2 = "0";
            int fac = 0;
            if (!listarC.isEmpty()) {  ////19-11-2015 busca la factura si existe 
                Detalle datodet = (Detalle) listarC.get(0);
                Pacientes paciente1 = new Pacientes();
                paciente1.setId_pedido(datodet.getId_pedido());
                paciente1.setCod_esta(cliente.getCod_esta());  ///debia sacar el cod_esta del la consulta
                paciente1.setId_factura(datodet.getId_empresa());
                Pacientes factura = this.mi.getDatosFactura(paciente1);
                if (factura != null) {
                    estad = "E";
                    fac = factura.getId_factura();
                }
            }  ////19-11-2015 busca la factura si existe 
            id_pedido = "0";

            /*
        for (int k = 0; k< listaCobrosTotsPag.size(); k++) {////////para deudas 15-03-2015
            Pacientes datoped = (Pacientes) listaCobrosTotsPag.get(k);
            total+=datoped.getPrecio_total();
                total2+=datoped.getTotal();
                if(datoped.getId_factura()==0){
                    totalsfc+=datoped.getTotal(); ////sin facturar costos
                }else{
                    totalcfc+=datoped.getTotal();  ///con facturas costos
                }
                if ("PAGOS DE DEUDAS".equals(datoped.getNombre())) {
                   totaldeudapag+=datoped.getTotal(); 
                }
                modelo.put("totaldeudapag", Double.toString(totaldeudapag));
        }////////para deudas 15-03-2015
             */
            totalsfc = 0;
            totalsfc = 0;   //////no se para que estaba de arriba el for esta duplicando 19-11-2015
            for (int j = 0; j < listaCobrosTots.size(); j++) {
                Pacientes datoped = (Pacientes) listaCobrosTots.get(j);
                total += datoped.getPrecio_total();
                total2 += datoped.getTotal();
                modelo.put("total", Double.toString(total));
                modelo.put("total2", Double.toString(total2));
                id_pedido = Integer.toString(datoped.getId_pedido());
                id_pedido2 = Integer.toString(datoped.getId_pedido());
                estad = datoped.getId_estado();
                fac = datoped.getId_factura();

                Detalle datodet = new Detalle();
                datodet.setId_pedido(Integer.parseInt(id_pedido));
                datodet.setId_carpeta(cliente.getCod_esta());
                datodet.setCod_esta(cliente.getCod_esta());
                datodet.setId_dispensa(Integer.parseInt(id_historial));
                datodet.setExpedido("N");  ////getListarCobroDetalleInt  07/01/2014 busca todos los pedidos con un historiAL
                List listaDet = this.mi.getListarCobroDetalleInt(datodet);
                modelo.put("costo", listaDet);
                if (listaDet.size() > 15) {
                    modelo.put("taman", "mucho");
                }

            }
            for (int i = 0; i < listaCobrosFar.size(); i++) {
                Pacientes datoped = (Pacientes) listaCobrosFar.get(i);
                tot += datoped.getPrecio_total();
                modelo.put("totalfar", Double.toString(tot));
                id_pedido = Integer.toString(datoped.getId_pedido());
                estad = datoped.getId_estado();
                fac = datoped.getId_factura();

                Recetas midato = new Recetas();
                midato.setId_pedido(Integer.parseInt(id_pedido));
                midato.setId_prestacion(cliente.getCod_esta());
                midato.setCod_esta(cliente.getCod_esta());
                midato.setId_farmacia(cliente.getId_farmacia());
                midato.setId_historial(Integer.parseInt(id_historial));///// 10/02/2016 se aumenta no jalaba SI
                ///List listarKardexf = this.mi.getListarKardex(midato);
                List listarKardexf = this.mi.getListarKardexI(midato);

                if (listarKardexf.size() > 15) {
                    modelo.put("taman", "mucho");
                }
                modelo.put("listaKf", listarKardexf);  ////Manda el detalle de medicamentos
            }

            if ("E".equals(estad) && fac > 0) {
                modelo.put("estadfact", "impresa");
                return new ModelAndView("Aviso", "mensaje", "Esta Factura Ya fue Impresa");
            }

            if (listaCobrosFar.size() > 0 && listaCobrosTots.size() > 0) {
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
                }
                if (dia1 < 10) {
                    dia11 = "0" + dia11;
                }
                String ff = anio11 + mes11 + dia11;

                long fec = Long.parseLong(ff);
                int total2f = (int) (Math.round(((float) (tot))));
                int total2f2 = (int) (Math.round(((float) (total))));
                long carne = Long.parseLong(ciu);
                codigoControl numer22 = new codigoControl(na, fact, carne, fec, total2f, llave);
                String codigo1 = numer22.verCodigoControl();

                modelo.put("dato", cliente);
                modelo.put("estab", Estab1);
                modelo.put("ciu", ciu);
                modelo.put("numauto", datoestab.getNum_auto());
                modelo.put("numfact", Integer.toString(datoestab.getNum_fact()));
                modelo.put("codigo1", codigo1);

                modelo.put("valortotal1", Double.toString(tot));
                modelo.put("valortotal2", Double.toString(total));

                Pacientes datofac = new Pacientes();  ////crea dato en facturas
                datofac.setNit_fact(Long.parseLong(ciu));
                datofac.setNombre(nombres);
                datofac.setNum_fact(datoestab.getNum_fact());////para la tabla facturas
                datofac.setNum_auto(Long.parseLong(datoestab.getNum_auto().trim()));
                datofac.setFec_registro(fecha1);
                datofac.setImporte(tot);
                datofac.setIce(0);
                datofac.setIme(0);
                datofac.setPrecio_total(tot);
                datofac.setTotal(tot * 0.13);
                datofac.setId_estado("V");
                datofac.setCarnet(codigo1);
                datofac.setId_pedido(Integer.parseInt(id_pedido));
                datofac.setId_persona(cliente.getId_persona());
                datofac.setCod_esta(cliente.getCod_esta());
                datofac.setTipo("I");
                datofac.setId_carpeta(Integer.parseInt(id_historial));
                this.mi.setCrearFacturaInter(datofac);   ////setCrearFacturaInter

                /////////////la otra factura de costos
                fact = datoestab.getNum_fact() + 1;
                codigoControl numer222 = new codigoControl(na, fact, carne, fec, total2f2, llave);
                String codigo2 = numer222.verCodigoControl();

                datofac.setNit_fact(Long.parseLong(ciu));
                datofac.setNombre(nombres);
                datofac.setNum_fact(datoestab.getNum_fact() + 1);////para la tabla facturas
                datofac.setNum_auto(Long.parseLong(datoestab.getNum_auto().trim()));
                datofac.setFec_registro(fecha1);
                datofac.setImporte(total);
                datofac.setIce(0);
                datofac.setIme(0);
                datofac.setPrecio_total(total);
                datofac.setTotal(total * 0.13);
                datofac.setId_estado("V");
                datofac.setCarnet(codigo2);
                datofac.setId_pedido(Integer.parseInt(id_pedido2));
                datofac.setId_persona(cliente.getId_persona());
                datofac.setCod_esta(cliente.getCod_esta());
                datofac.setTipo("W");
                datofac.setId_carpeta(Integer.parseInt(id_historial));
                this.mi.setCrearFacturaInter2(datofac);   ////setCrearFacturaInter2   se creo 17/10/2014 no guardaba id_factura en detalle

                modelo.put("numfact2", Integer.toString(datoestab.getNum_fact() + 1));
                modelo.put("codigo2", codigo2);
                return new ModelAndView(new Factura2PDF(), modelo);
            } else {
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
                }
                if (dia1 < 10) {
                    dia11 = "0" + dia11;
                }
                String ff = anio11 + mes11 + dia11;

                long fec = Long.parseLong(ff);
                int total2f = (int) (Math.round(((float) (tot + total))));
                long carne = Long.parseLong(ciu);
                codigoControl numer22 = new codigoControl(na, fact, carne, fec, total2f, llave);
                String codigo = numer22.verCodigoControl();
                modelo.put("dato", cliente);
                modelo.put("estab", Estab1);
                modelo.put("ciu", ciu);
                modelo.put("numauto", datoestab.getNum_auto());
                modelo.put("numfact", Integer.toString(datoestab.getNum_fact()));
                modelo.put("codigo", codigo);
                modelo.put("valortotal", Integer.toString(total2f));

                Pacientes datofac = new Pacientes();  ////crea dato en facturas
                datofac.setNit_fact(Long.parseLong(ciu));
                datofac.setNombre(nombres);
                datofac.setNum_fact(datoestab.getNum_fact());////para la tabla facturas
                datofac.setNum_auto(Long.parseLong(datoestab.getNum_auto().trim()));
                datofac.setFec_registro(fecha1);
                datofac.setImporte(Double.parseDouble(sumatot1));
                datofac.setIce(0);
                datofac.setIme(0);
                datofac.setPrecio_total(Double.parseDouble(sumatot1));
                datofac.setTotal(Double.parseDouble(sumatot1) * 0.13);
                datofac.setId_estado("V");
                datofac.setCarnet(codigo);
                datofac.setId_pedido(Integer.parseInt(id_pedido));
                datofac.setId_persona(cliente.getId_persona());
                datofac.setCod_esta(cliente.getCod_esta());
                if (listaCobrosFar.size() > 0 && listaCobrosTots.size() == 0) {
                    datofac.setTipo("I");////setCrearFacturaInter
                    datofac.setId_carpeta(Integer.parseInt(id_historial));
                    this.mi.setCrearFacturaInter(datofac);
                }
                if (listaCobrosFar.size() == 0 && listaCobrosTots.size() > 0) {
                    datofac.setTipo("W");////setCrearFacturaInter2    se creo 17/10/2014 no guardaba id_factura en detalle
                    datofac.setId_carpeta(Integer.parseInt(id_historial));
                    this.mi.setCrearFacturaInter(datofac);
                }

                if (listaCobrosFar.size() > 0 && listaCobrosTots.size() == 0) {
                    return new ModelAndView(new FacturaFarPDF(), modelo);
                } else {
                    if (listaCobrosFar.size() == 0 && listaCobrosTots.size() > 0) {
                        return new ModelAndView(new FacturaCostosPDF(), modelo);
                    } else {
                        return new ModelAndView("Aviso", "mensaje", "No existe factura que emitir");
                    }
                }
            }
        }
        if ("Economico".equals(accion)) {
            String expedido = request.getParameter("expedido");
            String id_estado = request.getParameter("id_estado");
            String id_pedido = request.getParameter("id_pedido");
            String id_seguro = request.getParameter("id_seguro");
            String id_historia = request.getParameter("id_historia");
            String fecha = request.getParameter("fecha");

            Pacientes buscarPaciente = this.mi.getDatosPaciente(Integer.parseInt(id_paciente)); // saca un registro a ser modificado
            Historiales datohi = new Historiales();
            datohi.setCod_esta(cliente.getCod_esta());
            datohi.setId_historial(Integer.parseInt(id_historial));
            Historiales datosHistorial = this.mi.getDatosHistorial(datohi);

            if (datosHistorial.getCod_esta() != cliente.getCod_esta()) {
                return new ModelAndView("Aviso", "mensaje", "Ud. No esta autorizado para modificar el estado econimico de este Paciente");
            }

            int dia = Integer.parseInt(fecha.substring(0, 2));
            int mes = Integer.parseInt(fecha.substring(3, 5));
            int anio = Integer.parseInt(fecha.substring(6, 10));

            if ("".equals(buscarPaciente.getCarnet()) || buscarPaciente.getCarnet() == null) {
                modelo.put("ciu", "0");
            } else {
                modelo.put("ciu", buscarPaciente.getCarnet());
            }

            List listarKardex = this.mi.getListarKardexPago(Integer.parseInt(id_historial));
            modelo.put("listarKardex", listarKardex);
            Rubros aux = new Rubros();
            List listarRubros = this.mi.getListarRubros(aux);
            modelo.put("listarRubros", listarRubros);

            Pacientes dato = new Pacientes();
            dato.setId_provincia(Integer.parseInt(id_historial));
            dato.setCod_esta(cliente.getCod_esta());
            dato.setId_rubro(1);
            dato.setId_escolaridad(1);
            dato.setTipo("G");
            List listaCobrosFar = this.mi.getDatosPedidoRubroGeneral(dato);   /////getDatosPedidoRubroGeneral
            modelo.put("listaCobrosFar", listaCobrosFar);
            dato.setTipo("T");
            List listaCobrosTots = this.mi.getDatosPedidoRubrosDet(dato);   /////getDatosPedidoRubrosDet
            modelo.put("listaCobrosOtros", listaCobrosTots);
            dato.setTipo("E");
            List factemitidas = this.mi.getReporteFacturasEmitidasHcl(dato);   /////getReporteEconomicoFac
            modelo.put("factemitidas", factemitidas);
            totaldeudapag = 0;
            for (int j = 0; j < listaCobrosTots.size(); j++) {
                Pacientes datoped = (Pacientes) listaCobrosTots.get(j);
                total += datoped.getPrecio_total();
                total2 += datoped.getTotal();
                if (datoped.getId_factura() == 0) {
                    totalsfc += datoped.getTotal();
                } else {
                    totalcfc += datoped.getTotal();
                }
                if ("PAGOS DE DEUDAS".equals(datoped.getNombre())) {
                    totaldeudapag += datoped.getTotal();
                }
                modelo.put("total", Double.toString(total));
                modelo.put("totalsfc", Double.toString(totalsfc));
                modelo.put("totalcfc", Double.toString(totalcfc));
                modelo.put("total2", Double.toString(total2));
                modelo.put("totaldeudapag", Double.toString(totaldeudapag));
            }
            if (listaCobrosTots.isEmpty()) {
                modelo.put("add", "nada");
            }
            if (!listaCobrosFar.isEmpty()) {
                for (int i = 0; i < listaCobrosFar.size(); i++) {
                    Pacientes datoped = (Pacientes) listaCobrosFar.get(i);
                    tot += datoped.getPrecio_total();
                    if (datoped.getId_factura() == 0) {
                        totalsff += datoped.getPrecio_total();
                    } else {
                        totalcff += datoped.getPrecio_total();
                    }
                    modelo.put("deuda", datoped.getId_estado());
                    modelo.put("totalfar", Double.toString(tot));
                    modelo.put("totalsff", Double.toString(totalsff));
                    modelo.put("totalcff", Double.toString(totalcff));
                }
            } else {
                modelo.put("addfar", "nada");
            }
            List listasInter = this.mi.getHistoriaInter(datohi);
            if (!listasInter.isEmpty()) {
                Historiales Inter = (Historiales) listasInter.get(listasInter.size() - 1);
                // id_historia=Integer.parseInt(Inter.getId_historia());
                modelo.put("id_historia", Integer.toString(Inter.getId_historia()));
                //persona=Inter.getId_persona();
            }
            Detalle detalle = new Detalle();
            detalle.setExpedido("F");
            detalle.setId_pedido(Integer.parseInt(id_historial));
            detalle.setCod_esta(cliente.getCod_esta());
            List listarC = this.mi.getListarDetHistorial(detalle);
            modelo.put("listarCostosT", listarC);

            List listarSeguros = this.mi.getListarSeguros("A");
            modelo.put("listaPacAfi", listarSeguros);

            Cuadernos datol = new Cuadernos();
            datol.setTipo("%");
            datol.setId_pedido(Integer.parseInt(id_historial));
            datol.setId_historial(Integer.parseInt(id_historial));
            datol.setCod_esta(cliente.getCod_esta());
            if (cliente.getId_laboratorio() == 0) {
                datol.setId_cargo(0);
                datol.setId_laboratorio(999);
            } else {
                datol.setId_cargo(cliente.getId_laboratorio());
                datol.setId_laboratorio(cliente.getId_laboratorio());
            }
            List listalab = this.mi.getLabPendiente(datol);
            modelo.put("listalab", listalab);

            Detalle datod2 = new Detalle();
            datod2.setId_pedido(Integer.parseInt(id_paciente));
            datod2.setId_carpeta(cliente.getCod_esta());
            datod2.setCod_esta(cliente.getCod_esta());
            List listarcobrosKardex = this.mi.getListarDetalle(datod2);
            modelo.put("listarcobroskardex", listarcobrosKardex);

            modelo.put("anio", Integer.toString(anio));
            modelo.put("mes", Integer.toString(mes));
            modelo.put("dia", Integer.toString(dia));
            modelo.put("expedido", datosHistorial.getExpedido());
            modelo.put("id_historial", id_historial);
            modelo.put("id_paciente", id_paciente);
            modelo.put("id_persona", id_persona);
            modelo.put("id_seguro", id_seguro);
            modelo.put("fecha", fecha);
            modelo.put("nombres", buscarPaciente.getNombres());
            modelo.put("hcl", Integer.toString(buscarPaciente.getHcl()));
            return new ModelAndView("administrarhistoriales/ResumenEconomico", modelo);
        }
        return new ModelAndView("administrarhistoriales/ReporteResumen", modelo);
    }
}
