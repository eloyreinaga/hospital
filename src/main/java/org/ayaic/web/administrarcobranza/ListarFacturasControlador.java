package org.ayaic.web.administrarcobranza;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Date;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Departamentos;
import org.ayaic.domain.Detalle;
import org.ayaic.domain.Historiales;
import org.ayaic.domain.Localidades;
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.Personas;
import org.ayaic.domain.Recetas;
import org.ayaic.domain.Rubros;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.document.AbstractXlsView;

@Controller
public class ListarFacturasControlador {

    private final MiFacade mi;

    public ListarFacturasControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/ListarFacturas.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String id_rubro = request.getParameter("id_rubro");
        String id_rubro1 = request.getParameter("id_rubro1");
        String accion = request.getParameter("accion");
        String id_farmacia = request.getParameter("id_farmacia");
        String sId_estado = request.getParameter("id_estado");
        String sAccion = request.getParameter("boton");
        String[] dias = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
        String[] meses = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        String[] horas = {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"};
        String[] minutos = {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"};
        String ip = request.getRemoteAddr();
        String host = request.getRemoteHost();
        Date fecha1 = new Date();
        int anioq = fecha1.getYear() + 1900;
        String[] anios = {Integer.toString(anioq), Integer.toString(anioq - 1), Integer.toString(anioq - 2)};

        Rubros aux = new Rubros();
        List listarRubros = this.mi.getListarRubros(aux);
        modelo.put("listarRubros", listarRubros);
        Personas persona = new Personas();
        persona.setCod_esta(cliente.getCod_esta());
        List listarPersonas = this.mi.getListarPersonasFacturas(persona);
        modelo.put("listarPersonas", listarPersonas);

        Localidades local = new Localidades();
        List Estab1 = this.mi.getListarEsta(local);
        Localidades datoestab = (Localidades) Estab1.get(0);
        modelo.put("Estab", Estab1);
        modelo.put("tipo_consul", Integer.toString(cliente.getId_consultorio()));
        modelo.put("permiso", Integer.toString(cliente.getId_rol()));
        modelo.put("dato", cliente);
        modelo.put("anios", anios);
        modelo.put("meses", meses);
        modelo.put("dias", dias);
        modelo.put("horas", horas);
        modelo.put("minutos", minutos);
        modelo.put("anio", Integer.toString(fecha1.getYear() + 1900));
        modelo.put("mes", Integer.toString(fecha1.getMonth() + 1));
        modelo.put("dia", Integer.toString(fecha1.getDate()));
        modelo.put("anio2", Integer.toString(fecha1.getYear() + 1900));
        modelo.put("mes2", Integer.toString(fecha1.getMonth() + 1));
        modelo.put("dia2", Integer.toString(fecha1.getDate()));
        modelo.put("hora", "00");
        modelo.put("minuto", "00");
        modelo.put("hora2", "23");
        modelo.put("minuto2", "59");
        aux.setRubro(accion);
        aux.setId_estado("T");
        List listarRubroT = this.mi.getListarRubrosT(aux);

        Date dFechaini1f = new Date(fecha1.getYear(), fecha1.getMonth(), fecha1.getDate(), 00, 00, 00);
        Date dFechafin1f = new Date(fecha1.getYear(), fecha1.getMonth(), fecha1.getDate(), 23, 59, 59);

        Pacientes datof = new Pacientes();
        datof.setFecha_ini(dFechaini1f);
        datof.setFecha_fin(dFechafin1f);
        datof.setCod_esta(cliente.getCod_esta());
        datof.setId_rubro(0);///rango de rubros
        datof.setId_pais(1000);///rango de rubros
        datof.setId_provincia(cliente.getId_persona());
        datof.setId_tipo_parentesco(cliente.getId_persona());
        if (cliente.getId_cargo() == 7) {
            datof.setId_provincia(0);
            datof.setId_tipo_parentesco(99999);
        }
        List listaCobrosf = this.mi.getReporteEconomicoGenFac(datof);
        modelo.put("listaCobros", listaCobrosf);

        List listaRubros = this.mi.getListaRubro();
        modelo.put("listaRubros", listaRubros);

        if ("ImprimirFact".equals(accion) || "ImprimirOrig".equals(accion)) {
            String id_pedido = request.getParameter("id_pedido");
            String id_factura = request.getParameter("id_factura");
            String sumatot1 = request.getParameter("total");
            String fech = request.getParameter("fec");
            String ciu = request.getParameter("ciu");
            String nombres = request.getParameter("nombres");
            String anio = request.getParameter("anio");
            String mes = request.getParameter("mes");
            String dia = request.getParameter("dia");
            String hora = request.getParameter("hora");
            String minuto = request.getParameter("minuto");
            String segundo = request.getParameter("segundo");

            dia = fech.substring(0, 2);
            mes = fech.substring(3, 5);
            anio = fech.substring(6, 10);
            hora = fech.substring(11, 13);
            minuto = fech.substring(14, 16);
            segundo = fech.substring(17, 19);

            Pacientes paciente1 = new Pacientes();
            paciente1.setId_pedido(Integer.parseInt(id_pedido));
            paciente1.setCod_esta(cliente.getCod_esta());
            Pacientes pedido = this.mi.getDatosPedido(paciente1); ////////////// 
            paciente1.setId_factura(Integer.parseInt(id_factura));
            Pacientes factura = this.mi.getDatosFactura(paciente1);

            if (pedido.getId_carpeta() > 0) {
                Historiales datohi = new Historiales();
                datohi.setCod_esta(cliente.getCod_esta());
                datohi.setId_historial(pedido.getId_pais());
                Historiales datosHist = this.mi.getDatosHistorial(datohi);
            }

            modelo.put("estab", Estab1);
            modelo.put("dfactura", factura);
            modelo.put("carnet", Long.toString(factura.getNit_fact()));
            modelo.put("numauto", Long.toString(factura.getNum_auto()));
            modelo.put("numfact", Integer.toString(factura.getId_pais()));
            modelo.put("codigo", factura.getCarnet());
            modelo.put("anular", factura.getId_estado());
            modelo.put("nombrecliente", factura.getNombres());
            modelo.put("usuario", Integer.toString(factura.getId_persona()));
            modelo.put("ciu", Long.toString(factura.getNit_fact()));
            modelo.put("rubrocosto", sumatot1);
            modelo.put("nombrepac", pedido.getNombres());
            modelo.put("dia", dia);
            modelo.put("mes", mes);
            modelo.put("anio", anio);
            modelo.put("anio", anio);
            modelo.put("hora", hora);
            modelo.put("minuto", minuto);
            modelo.put("segundo", segundo);

            Rubros dfact = this.mi.getDatosRubro(Integer.parseInt(id_rubro));
            modelo.put("rubro", id_rubro);

            if ("1".equals(id_rubro)) {
                Recetas midato = new Recetas();
                midato.setId_pedido(Integer.parseInt(id_pedido));
                midato.setId_prestacion(cliente.getCod_esta());
                midato.setCod_esta(cliente.getCod_esta());
                //midato.setId_farmacia(Integer.parseInt(id_farmacia));
                midato.setExpedido("F");
                if (factura.getId_carpeta() > 0) {
                    midato.setId_pedido(factura.getId_carpeta());
                    midato.setExpedido("I");
                }
                List listarKardexant = this.mi.getListarKardex(midato);
                if (!listarKardexant.isEmpty()) { ////saca la farmacia del pedido
                    Recetas lkardex = (Recetas) listarKardexant.get(0);
                    midato.setId_farmacia(lkardex.getId_farmacia());
                    midato.setExpedido("A");
                }
                List listarKardex = this.mi.getListarKardex(midato);
                modelo.put("costo", listarKardex);
                if (listarKardex.size() > 15) {
                    modelo.put("taman", "mucho");
                }
                modelo.put("costo", listarKardex);  ////Manda el detalle de medicamentos

                if ("ImprimirOrig".equals(accion)) {
                    return new ModelAndView(new FacturaCopiaOrigPDF(), modelo);
                } else {
                    return new ModelAndView(new FacturaCopiaPDF(), modelo);
                }
            } else {
                Detalle datodet = new Detalle();
                datodet.setId_pedido(Integer.parseInt(id_pedido));
                datodet.setId_carpeta(cliente.getCod_esta());
                datodet.setCod_esta(cliente.getCod_esta());
                List listaDet = this.mi.getListarCobroDetalle(datodet);
                modelo.put("costo", listaDet);
                if (factura.getId_carpeta() > 0) {
                    datodet.setId_carpeta(factura.getId_carpeta());   ////29/09/2014 cjuando estuvo internado
                    datodet.setId_empresa(factura.getId_factura());
                    datodet.setExpedido("I");
                    List listaDet2 = this.mi.getListarCobroDetalleInterFact(datodet);
                    modelo.put("costo", listaDet2);
                }
                if (listaDet.size() != 0) {
                    Detalle costos = (Detalle) listaDet.get(0);
                    Personas buscarEmpleado = this.mi.getBuscarPersona(costos.getId_dispensa());
                    if (listaDet.size() > 17) {
                        modelo.put("taman", "mucho");
                    }
                    modelo.put("costo", listaDet);  ////Manda el detalle de medicamentos

                    if ("ImprimirOrig".equals(accion)) {
                        return new ModelAndView(new FacturaCopiaOrig2PDF(), modelo);
                    } else {
                        return new ModelAndView(new FacturaCopia2PDF(), modelo);
                    }
                }
            }
        }

        if ("Imprimir".equals(accion)) {
            String id_paciente = request.getParameter("id_paciente");
            String id_pedido = request.getParameter("id_pedido");
            String ciu = request.getParameter("ciu");
            String sumatot1 = request.getParameter("precio");
            String nombres = request.getParameter("nombres");
            String nit = request.getParameter("nit");

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
                midato.setId_farmacia(Integer.parseInt(id_farmacia));
                midato.setExpedido("F");  ////getListarKardexFactura
                List listarKardex = this.mi.getListarKardexFactura(midato);
                modelo.put("costo", listarKardex);
                return new ModelAndView(new Recibo2PDF(), modelo);
            } else {
                return new ModelAndView(new ReciboPDF(), modelo);
            }

        }

        if ("Anulara".equals(accion)) {
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
            return new ModelAndView("administrarcobranza/AnularFactura", modelo);
        }

        if ("Anular".equals(accion) || "Validar".equals(accion)) {
            String id_pedido = request.getParameter("id_pedido");
            String id_factura = request.getParameter("id_factura");

            Pacientes dato = new Pacientes();
            dato.setId_estado("E");
            dato.setId_dispensa(cliente.getId_persona());
            if ("Anular".equals(accion)) {
                dato.setNombre("A");
            } else {
                dato.setNombre("V");
            }
            dato.setId_pedido(Integer.parseInt(id_pedido));
            dato.setId_factura(Integer.parseInt(id_factura));
            dato.setCod_esta(cliente.getCod_esta());
            dato.setCadena1(ip);
            dato.setCadena2(host);
            modelo.put("accion", accion);
            this.mi.setModificarPedidoFactura(dato);
        }

        if ("Buscar Facturas".equals(sAccion) || "Exportar Excel".equals(sAccion) || "Imprimir Facturas".equals(sAccion)
                || "Plan de Pagos".equals(sAccion) || "Imprimir General".equals(sAccion)) {
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

            modelo.put("dia", diai);
            modelo.put("mes", mesi);
            modelo.put("anio", anioi);
            modelo.put("hora", horai);
            modelo.put("minuto", minutoi);
            modelo.put("dia2", diaf);
            modelo.put("mes2", mesf);
            modelo.put("anio2", aniof);
            modelo.put("hora2", horaf);
            modelo.put("minuto2", minutof);

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

                Pacientes dato = new Pacientes();
                // fechas de cobro
                dato.setFecha_ini(dFechaini1);
                dato.setFecha_fin(dFechafin1);
                dato.setCod_esta(cliente.getCod_esta());
                modelo.put("persona", dato);

                List listaCobrosTots = this.mi.getDatosPedidoDetRubro(dato);
                modelo.put("listaCobrosOtros", listaCobrosTots);

                dato.setTipo("D");////para lstar solo con facturas emtidas
                if ("Plan de Pagos".equals(sAccion)) {
                    dato.setTipo("P");   ////getListarPacientesPPagos  para listar plan de pagos
                    List listarPac = this.mi.getListarPacientesPPagos(dato);
                    modelo.put("listaPacientes", listarPac);
                    modelo.put("plan", sAccion);

                }
                if ("".equals(id_persona) || id_persona == null) {
                    id_persona = "0";
                }
                if ("".equals(id_rubro) || id_rubro == null) {
                    id_rubro = "0";
                }

                if (cliente.getId_rol() == 1 || cliente.getId_rol() == 5 || cliente.getId_rol() == 27 || cliente.getId_rol() == 60) {
                    if (Integer.parseInt(id_rubro) > 0) {
                        dato.setId_rubro(Integer.parseInt(id_rubro));
                        dato.setId_pais(Integer.parseInt(id_rubro));
                        if ("0".equals(id_persona)) {
                            dato.setId_provincia(1);///rango de personas
                            dato.setId_tipo_parentesco(100000);///rango de personas
                            modelo.put("NomCaja", "CAJA GENERAL");
                        } else {
                            dato.setId_provincia(Integer.parseInt(id_persona));///rango de personas
                            dato.setId_tipo_parentesco(Integer.parseInt(id_persona));///rango de personas
                            Personas buscarEmpleado = this.mi.getDatosPersona(Integer.parseInt(id_persona));
                            modelo.put("NomCaja", buscarEmpleado.getNombres());
                        }
                        List listaCobros = this.mi.getReporteEconomicoFac(dato);///general facturas
                        modelo.put("listaCobros", listaCobros);
                        dato.setTipo("G");
                        List listaGenRub = this.mi.getReporteEconomicoGenFacRub(dato);
                        modelo.put("listaGenRub", listaGenRub);
                    } else {
                        // buscamos los montos de cobros de una persona
                        if ("0".equals(id_persona)) {
                            dato.setId_provincia(1);///rango de personas
                            dato.setId_tipo_parentesco(100000);///rango de personas
                            modelo.put("NomCaja", "CAJA GENERAL");
                        } else {
                            dato.setId_provincia(Integer.parseInt(id_persona));///rango de personas
                            dato.setId_tipo_parentesco(Integer.parseInt(id_persona));///rango de personas
                            Personas buscarEmpleado = this.mi.getDatosPersona(Integer.parseInt(id_persona));
                            modelo.put("NomCaja", buscarEmpleado.getNombres());
                        }
                        dato.setId_rubro(0);///rango de rubros
                        dato.setId_pais(1000);///rango de rubros
                        List listaCobros = this.mi.getReporteEconomicoGenFac(dato); ////antes 16-04-2014 getReporteEconomicoGenRubro
                        modelo.put("listaCobros", listaCobros);
                        dato.setTipo("G");
                        List listaGenRub = this.mi.getReporteEconomicoGenFacRub(dato);
                        modelo.put("listaGenRub", listaGenRub);
                    }
                    //      return new ModelAndView(new ReporteEconomicoGenPDF(), modelo);
                } else {//////////solo para reporte por usuarios individuales
                    Personas buscarEmpleado = this.mi.getDatosPersona(cliente.getId_persona());
                    modelo.put("NomCaja", buscarEmpleado.getNombres());
                    if (!"0".equals(id_rubro)) {
                        dato.setId_rubro(Integer.parseInt(id_rubro));
                        dato.setId_pais(Integer.parseInt(id_rubro));
                        dato.setId_provincia(cliente.getId_persona());
                        dato.setId_tipo_parentesco(cliente.getId_persona());
                        List listaCobros = this.mi.getReporteEconomicoGenFac(dato);
                        modelo.put("listaCobros", listaCobros);
                        dato.setTipo("G");
                        List listaGenRub = this.mi.getReporteEconomicoGenFacRub(dato);
                        modelo.put("listaGenRub", listaGenRub);
                    } else {
                        // buscamos los montos de cobros de una persona
                        dato.setId_rubro(0);///rango de rubros
                        dato.setId_pais(1000);///rango de rubros
                        dato.setId_provincia(cliente.getId_persona());
                        dato.setId_tipo_parentesco(cliente.getId_persona());
                        List listaCobros = this.mi.getReporteEconomicoGenFac(dato);
                        modelo.put("listaCobros", listaCobros);
                        dato.setTipo("G");
                        List listaGenRub = this.mi.getReporteEconomicoGenFacRub(dato);///general por rubros
                        modelo.put("listaGenRub", listaGenRub);
                    }
                }
                ///////}     
                if ("Exportar Excel".equals(sAccion)) {
                    return new ModelAndView((View) new excel2(), modelo);
                }

                if ("Imprimir Facturas".equals(sAccion)) {
                    return new ModelAndView(new ReporteFacturasPDF(), modelo);
                }
                if ("Imprimir General".equals(sAccion)) {
                    return new ModelAndView(new ReporteGeneralRubPDF(), modelo);
                }
            }
        }
        return new ModelAndView("administrarcobranza/ListarFacturas", modelo);
    }

    private static class excel2 extends AbstractXlsView {
        
        @Override
        protected void buildExcelDocument(Map<String, Object> map, Workbook wrkbk, HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
            
            Sheet sheet = wrkbk.createSheet("facturacion");

            Row fila = sheet.createRow(0);
            Cell celda;

            String[] titulos = {"fecha", "numfact", "NIT", "CodControl", "Estad", "ID", "Nombre", "Monto"};
            int i;

            for (i = 0; i < titulos.length; i++) {
                celda = fila.createCell(i);
                celda.setCellValue(titulos[i]);
            }
            // Nueva fila
            // code that assigns a cell from an HSSFSheet to 'myCell' would go here...

            fila = sheet.createRow(1);
            List listarKardexUsua = (List) map.get("listaCobros");
            for (i = 0; i < listarKardexUsua.size(); i++) {
                Pacientes dato = (Pacientes) listarKardexUsua.get(i);
                Row filas = sheet.createRow(i + 5);
                for (int j = 0; j < 8; j++) {
                    HSSFRow row = (HSSFRow) sheet.createRow(i + 1);
                    int anio1 = dato.getFec_registro().getYear() + 1900;
                    int mes1 = dato.getFec_registro().getMonth() + 1;
                    int dia1 = dato.getFec_registro().getDate();
                    String anio11 = Integer.toString(anio1);
                    String mes11 = Integer.toString(mes1);
                    String dia11 = Integer.toString(dia1);
                    if (mes1 < 10) {
                        mes11 = "0" + mes11;
                    }
                    if (dia1 < 10) {
                        dia11 = "0" + dia11;
                    }

                    row.createCell(0).setCellValue(dia11 + "-" + mes11 + "-" + anio11);  ////fecha
                    row.createCell(1).setCellValue(dato.getId_pais());  ////numfact
                    row.createCell(2).setCellValue(dato.getNit());  ////NIT
                    row.createCell(3).setCellValue(dato.getNombre());  ////CodControl
                    row.createCell(4).setCellValue(dato.getId_estado());  ////Estado
                    row.createCell(5).setCellValue(dato.getId_persona());    ////ID
                    row.createCell(6).setCellValue(dato.getNombres());  ///Nombre
                    row.createCell(7).setCellValue(dato.getPrecio_total());  ///Monto
                }
            }
        }
    }
}
