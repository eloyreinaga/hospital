package org.ayaic.web.administrarcobranza;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Departamentos;
import org.ayaic.domain.Detalle;
import org.ayaic.domain.Localidades;
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.Recetas;
import org.ayaic.domain.Rubros;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ListarRecibosControlador {

    private final MiFacade mi;

    public ListarRecibosControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/ListarRecibos.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String id_rubro = request.getParameter("id_rubro");
        String id_rubro1 = request.getParameter("id_rubro1");
        String accion = request.getParameter("accion");
        String sId_estado = request.getParameter("id_estado");
        String sAccion = request.getParameter("boton");
        String[] dias = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
        String[] meses = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        String[] horas = {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"};
        String[] minutos = {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"};
        String diaq1 = request.getParameter("diaq1");
        String mesq1 = request.getParameter("mesq1");
        String ip = request.getRemoteAddr();
        String host = request.getRemoteHost();
        Date fechaact = new Date();
        Date fecha1 = new Date();
        int anioq = fecha1.getYear() + 1900;
        String[] anios = {Integer.toString(anioq), Integer.toString(anioq - 1), Integer.toString(anioq - 2), Integer.toString(anioq - 3), Integer.toString(anioq - 4), Integer.toString(anioq - 5), Integer.toString(anioq - 6), Integer.toString(anioq - 7), Integer.toString(anioq - 8), Integer.toString(anioq - 9), Integer.toString(anioq - 10)};

        Localidades local = new Localidades();
        List Estab1 = this.mi.getListarEsta(local);
        Localidades datoestab = (Localidades) Estab1.get(0);
        modelo.put("Estab", Estab1);
        modelo.put("tipo_consul", Integer.toString(cliente.getId_consultorio()));
        modelo.put("permiso", Integer.toString(cliente.getId_rol()));

        //Recuperamos variables del jsp
        Rubros aux = new Rubros();
        List listarRubros = this.mi.getListarRubros(aux);
        modelo.put("listarRubros", listarRubros);
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

        aux.setRubro(accion);
        aux.setId_estado("T");
        List listarRubroT = this.mi.getListarRubrosT(aux);

        if ("Imprimir".equals(accion)) {
            String id_paciente = request.getParameter("id_paciente");
            String id_pedido = request.getParameter("id_pedido");
            String ciu = request.getParameter("ciu");
            String sumatot1 = request.getParameter("precio");
            String nombres = request.getParameter("nombres");
            String nit = request.getParameter("nit");

            modelo.put("dato", cliente);
            modelo.put("estab", Estab1);
            Pacientes datojef = mi.getDatosPaciente(Integer.parseInt(id_paciente));
            modelo.put("persona", datojef);

            Rubros dfact = this.mi.getDatosRubro(Integer.parseInt(id_rubro));
            modelo.put("rubro", dfact.getRubro());

            Detalle datodet = new Detalle();
            datodet.setId_pedido(Integer.parseInt(id_pedido));
            datodet.setId_carpeta(cliente.getCod_esta());
            datodet.setCod_esta(cliente.getCod_esta());
            List listaDet = this.mi.getListarCobroDetalle(datodet);
            modelo.put("costo", listaDet);

            Pacientes paciente1 = new Pacientes();
            paciente1.setId_pedido(Integer.parseInt(id_pedido));
            paciente1.setCod_esta(cliente.getCod_esta());
            Pacientes pedido = this.mi.getDatosPedido(paciente1); //////////////       
            modelo.put("pedido", pedido);

            Departamentos buscardepartamento = this.mi.getDatosDepartamento(datoestab.getId_departamento()); // saca un registro a ser modificado
            modelo.put("departamento", buscardepartamento);

            modelo.put("rubrocosto", Double.toString(pedido.getPrecio_total()));
            modelo.put("nombreusuario", nombres.toUpperCase());
            modelo.put("ciu", pedido.getNit());
            modelo.put("nit", pedido.getNum_cladoc());
            if (listaDet.size() == 0) {
                Recetas midato = new Recetas();
                midato.setId_pedido(Integer.parseInt(id_pedido));
                midato.setId_prestacion(cliente.getCod_esta());
                midato.setCod_esta(cliente.getCod_esta());
                midato.setId_farmacia(cliente.getId_farmacia());
                List listarKardex = this.mi.getListarKardex(midato);
                modelo.put("costo", listarKardex);
                return new ModelAndView(new Recibo2PDF(), modelo);
            } else {
                return new ModelAndView(new ReciboPDF(), modelo);
            }
        }

        if ("EliminarReb".equals(accion)) {
            String id_pedido = request.getParameter("id_pedido");
            String id_factura = request.getParameter("id_factura");
            String id_estado = request.getParameter("id_estado");
            String fecha = request.getParameter("fecha");
            String anio = request.getParameter("anio");
            String mes = request.getParameter("mes");
            String dia = request.getParameter("dia");

            dia = fecha.substring(0, 2);
            mes = fecha.substring(3, 5);
            anio = fecha.substring(6, 10);

            Pacientes paciente1 = new Pacientes();
            paciente1.setId_pedido(Integer.parseInt(id_pedido));
            paciente1.setCod_esta(cliente.getCod_esta());
            Pacientes buscarPaciente = this.mi.getDatosPedido(paciente1); //////////////
            modelo.put("datos", buscarPaciente);
            modelo.put("id_factura", id_factura);
            modelo.put("id_pedido", id_pedido);
            modelo.put("id_estado", id_estado);
            modelo.put("dia", dia);
            modelo.put("mes", mes);
            modelo.put("anio", anio);
            return new ModelAndView("administrarcobranza/EliminarRecibo", modelo);
        }

        if ("Eliminar".equals(accion)) {
            String id_pedido = request.getParameter("id_pedido");
            String id_factura = request.getParameter("id_factura");
            String id_estado = request.getParameter("id_estado");

            Pacientes paciente1 = new Pacientes();
            paciente1.setId_pedido(Integer.parseInt(id_pedido));
            paciente1.setCod_esta(cliente.getCod_esta());
            Pacientes buscarPaciente = this.mi.getDatosPedido(paciente1); //////////////

            Pacientes dato = new Pacientes();
            dato.setId_pedido(Integer.parseInt(id_pedido));
            dato.setId_dispensa(cliente.getId_persona());
            dato.setCod_esta(cliente.getCod_esta());
            dato.setCadena1(ip);
            dato.setCadena2(host);
            this.mi.setEliminarPedido(dato);

            return new ModelAndView("Aviso", "mensaje", "La transaccion fue Eliminada Correctamente");
        }

        if ("Modificar".equals(accion)) {
            String id_pedido = request.getParameter("id_pedido");
            String id_factura = request.getParameter("id_factura");
            String total = request.getParameter("total");
            String expedido = request.getParameter("expedido");
            String nombres = request.getParameter("nombres");
            String nit = request.getParameter("nit");
            String num_cladoc = request.getParameter("num_cladoc");
            String fecha = request.getParameter("fecha");
            String anio = request.getParameter("anio");
            String mes = request.getParameter("mes");
            String dia = request.getParameter("dia");

            dia = fecha.substring(0, 2);
            mes = fecha.substring(3, 5);
            anio = fecha.substring(6, 10);

            Pacientes paciente1 = new Pacientes();
            paciente1.setId_pedido(Integer.parseInt(id_pedido));
            paciente1.setCod_esta(cliente.getCod_esta());
            Pacientes buscarPaciente = this.mi.getDatosPedido(paciente1); //////////////
            modelo.put("datos", buscarPaciente);

            // recupera los medicamentos del paciente a entregar     
            Detalle datodet = new Detalle();
            datodet.setId_pedido(Integer.parseInt(id_pedido));
            datodet.setId_carpeta(cliente.getCod_esta());
            datodet.setCod_esta(cliente.getCod_esta());
            List listaDet = this.mi.getListarCobroDetalle(datodet);///26-02-2014
            modelo.put("listarDet", listaDet);

            modelo.put("dia", dia);
            modelo.put("mes", mes);
            modelo.put("anio", anio);
            modelo.put("id_pedido", id_pedido);
            modelo.put("nombres", nombres);
            modelo.put("expedido", expedido);
            //modelo.put("id_estado", id_estado);
            modelo.put("nit", nit);
            modelo.put("total", total);
            modelo.put("num_cladoc", num_cladoc);
            modelo.put("id_factura", id_factura);

            return new ModelAndView("administrarcobranza/VerRecibosT", modelo);
        }

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
                return new ModelAndView("administrarcobranza/ListarRecibos", modelo);
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

                Pacientes dato = new Pacientes();
                // fechas de cobro
                dato.setFecha_ini(dFechaini1);
                dato.setFecha_fin(dFechafin1);
                dato.setCod_esta(cliente.getCod_esta());
                // persona que cobro
                dato.setId_persona(cliente.getId_persona());

                modelo.put("persona", dato);

                List listaRubros = this.mi.getListaRubro();
                modelo.put("listaRubros", listaRubros);

                dato.setTipo("C");
                List listaCobrosTots = this.mi.getDatosPedidoDetRubro(dato);
                modelo.put("listaCobrosOtros", listaCobrosTots);

                //List listaCobrosfar = this.mi.getReporteCobroFarm(dato);
                //modelo.put("listaCobrosFar", listaCobrosfar);
                if (cliente.getId_rol() == 1 || cliente.getId_rol() == 5) {
                    if (Integer.parseInt(request.getParameter("id_rubro")) > 0) {
                        dato.setId_rubro(Integer.parseInt(id_rubro));
                        dato.setId_pais(Integer.parseInt(id_rubro));
                        List listaCobros = this.mi.getReporteEconomicoGenRubro(dato);
                        modelo.put("listaCobros", listaCobros);
                    } else {
                        // buscamos los montos de cobros de una persona
                        dato.setId_rubro(1);
                        dato.setId_pais(100);
                        List listaCobros = this.mi.getReporteEconomicoGenRubro(dato);
                        modelo.put("listaCobros", listaCobros);
                    }
                    //      return new ModelAndView(new ReporteEconomicoGenPDF(), modelo);
                } else {
                    if (Integer.parseInt(request.getParameter("id_rubro")) > 0) {
                        dato.setId_rubro(Integer.parseInt(id_rubro));
                        List listaCobros = this.mi.getReporteEconomicoRubro(dato);
                        modelo.put("listaCobros", listaCobros);
                    } else {
                        // buscamos los montos de cobros de una persona
                        List listaCobros = this.mi.getReporteEconomico(dato);
                        modelo.put("listaCobros", listaCobros);
                    }
                }
            }
        }

        if ("Plan de Pagos".equals(sAccion)) {
            String id_persona = request.getParameter("id_persona");
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
                return new ModelAndView("administrarcobranza/ListarRecibos", modelo);
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

                Pacientes dato = new Pacientes();
                // fechas de cobro
                dato.setFecha_ini(dFechaini1);
                dato.setFecha_fin(dFechafin1);
                // persona que cobro
                dato.setId_persona(cliente.getId_persona());

                modelo.put("persona", dato);
                List listaRubros = this.mi.getListaRubro();
                modelo.put("listaRubros", listaRubros);

                dato.setTipo("C");
                List listaCobrosTots = this.mi.getDatosPedidoDetRubro(dato);
                modelo.put("listaCobrosOtros", listaCobrosTots);

                dato.setTipo("D");////para lstar solo con facturas emtidas
                if ("Plan de Pagos".equals(sAccion)) {
                    dato.setTipo("P");
                    List listarPac = this.mi.getListarPacientesPPagos(dato);
                    modelo.put("listaPacientes", listarPac);
                    modelo.put("plan", sAccion);

                }
            }
        }
        return new ModelAndView("administrarcobranza/ListarRecibos", modelo);
    }
}
