package org.ayaic.web.administrarfarmacias;

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
import org.ayaic.domain.Farmacias;
import org.ayaic.domain.Localidades;
import org.ayaic.domain.Medicamentos;
import org.ayaic.domain.Personas;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.document.AbstractXlsView;

@Controller
public class ListarImmControlador {

    private final MiFacade mi;

    public ListarImmControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/ListarImm.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String sAccion = request.getParameter("boton");
        String cod_esta = request.getParameter("cod_esta");
        String id_farmacia = request.getParameter("id_farmacia");
        String[] dias = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
        String[] meses = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        String[] horas = {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"};
        String[] minutos = {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"};
        String diaq1 = request.getParameter("mesq1");
        String mesq1 = request.getParameter("mesq1");
        String ip = request.getRemoteAddr();
        String host = request.getRemoteHost();
        Date fechaact = new Date();
        Date fecha1 = new Date();
        int anioq = fecha1.getYear() + 1900;
        String[] anios = {Integer.toString(anioq), Integer.toString(anioq - 1), Integer.toString(anioq - 2)};

        Localidades local = new Localidades();
        List Estab1 = this.mi.getListarEsta(local);
        Localidades datoper = (Localidades) Estab1.get(0);
        modelo.put("codesta", Integer.toString(datoper.getCod_esta()));
        modelo.put("cod_esta", Integer.toString(cliente.getCod_esta()));
        Personas buscarEmpleado = this.mi.getDatosPersona(datoper.getId_persona());
        modelo.put("datosJMed", buscarEmpleado);
        modelo.put("rol", Integer.toString(cliente.getId_rol2()));
        modelo.put("localidades", datoper);
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

        Farmacias datofar = new Farmacias();
        datofar.setCod_esta(cliente.getCod_esta());
        datofar.setId_persona(cliente.getId_persona());
        datofar.setTipo("A");
        datofar.setId_estado("%");
        List listarFarmaciaAsig = this.mi.getListarFarmaciasAsig(datofar); // getListarFarmaciasAsig
        modelo.put("listafarAsig", listarFarmaciaAsig);
        modelo.put("dato", cliente);
        
        Medicamentos medic = new Medicamentos();
        List listarTipo = this.mi.getListarTipos(medic);  ///getListarTipos
        modelo.put("listarTipo", listarTipo);
        modelo.put("listipo", listarTipo.size());

        // if(cliente.getId_rol2()==7 || cliente.getId_rol2()==31 || cliente.getId_rol2()==30 ){
        local.setArea("M");  ////getEstabHabiles
        local.setCod_esta(cliente.getCod_esta());
        List Estab = mi.getEstabHabiles(local);
        modelo.put("listaEstab", Estab);

        //Farmacias datofar = new Farmacias();
        datofar.setCod_esta(cliente.getCod_esta());
        datofar.setId_farmacia(cliente.getId_farmacia());
        if (!"".equals(cod_esta) && cod_esta != null) {
            datofar.setCod_esta(Integer.parseInt(cod_esta));
            modelo.put("cod_esta", cod_esta);
        }
        if (!"".equals(id_farmacia) && !"0".equals(id_farmacia) && id_farmacia != null) {
            datofar.setCod_esta(Integer.parseInt(cod_esta));
            modelo.put("cod_esta", cod_esta);
        }
        List listarFarmacia = this.mi.getListarFarmacias(datofar);
        modelo.put("listarFarmacia", listarFarmacia);
        //}

        if ("Modificar".equals(accion)) {
            String mes = request.getParameter("mes");
            String gestion = request.getParameter("gestion");

            Medicamentos datos = new Medicamentos();
            datos.setMes(Integer.parseInt(mes));
            datos.setGestion(Integer.parseInt(gestion));
            datos.setCod_esta(cliente.getCod_esta());

            List listarDatosImm = this.mi.getListarActImm(datos);
            modelo.put("listarDatosImm", listarDatosImm);

            modelo.put("gestion", gestion);
            modelo.put("mes", mes);

            return new ModelAndView(new ListarConsultaImmPDF(), modelo);
        }

        if ("Mostrar".equals(accion)) {
            String mes = request.getParameter("mes");
            String gestion = request.getParameter("gestion");

            Medicamentos datos = new Medicamentos();
            datos.setMes(Integer.parseInt(mes));
            datos.setGestion(Integer.parseInt(gestion));
            datos.setCod_esta(cliente.getCod_esta());
            List listarDatosImm = this.mi.getListarDatosImm(datos);
            modelo.put("listarDatosImm", listarDatosImm);
            if (datoper.getCod_esta() == 200010) {
                datos.setExpedido("I"); ///getListarDatosImmCNS2
                List listarDatosImm2 = this.mi.getListarDatosImmCNS2(datos);
                modelo.put("listarDatosImm", listarDatosImm2);
            }
            modelo.put("gestion", gestion);
            modelo.put("mes", mes);

            return new ModelAndView(new ListarConsultaImmPDF(), modelo);
        }

        if ("Adicionar".equals(accion)) {
            Medicamentos datos = new Medicamentos();
            datos.setCod_esta(cliente.getCod_esta());
            List listarImm = this.mi.getListarFaltanImm(datos);
            modelo.put("listarImm", listarImm);

            modelo.put("accion", accion);
            return new ModelAndView("administrarfarmacias/ListarImm", modelo);
        }

        if ("InventarioFisicoValorado".equals(sAccion) || "InvFisicoValoradoDetallado".equals(sAccion)) {
            String sFecha_fin = request.getParameter("valor_2");
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
                return new ModelAndView("administrarfarmacias/ListarImm", modelo);
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

                Medicamentos datos = new Medicamentos();
                datos.setMes(Integer.parseInt(mesf));
                datos.setGestion(Integer.parseInt(aniof));
                datos.setExpedido("I");
                datos.setFecha_ini(dFechaini1);
                datos.setFecha_fin(dFechafin1);
                datos.setCod_esta(cliente.getCod_esta());
                datos.setId_persona(cliente.getId_persona());
                datos.setId_farmacia(cliente.getId_farmacia());
                List listarMedicamentosUsua = this.mi.getListarMedicamentosAsignados(datos);
                if (listarMedicamentosUsua.isEmpty()) {
                    datos.setExpedido("W"); ////getListarImmFechaDada
                    if (datoper.getCod_esta() == 200010) {
                        datos.setExpedido("C");  /////getListarImmFechaDadaCNS
                        List listarDatosImm = this.mi.getListarImmFechaDadaCNS(datos);
                        modelo.put("listarDatosImm", listarDatosImm);
                    }
                } else {
                    datos.setExpedido("U"); ////getListarImmFechaDadaUsua
                    List listarDatosImm = this.mi.getListarImmFechaDadaUsua(datos);
                    modelo.put("listarDatosImm", listarDatosImm);
                }

                if ("InvFisicoValoradoDetallado".equals(sAccion)) {
                    datos.setExpedido("D"); ////getListarImmFechaDet
                    List listarDatosImm = this.mi.getListarImmFechaDet(datos);   ///getListarImmFechaDet
                    modelo.put("listarDatosImm", listarDatosImm);
                } else {
                    List listarDatosImm = this.mi.getListarImmFechaDada(datos);   ///getListarImmFechaDada
                    modelo.put("listarDatosImm", listarDatosImm);
                }

                modelo.put("gestion", aniof);
                modelo.put("mes", mesf);
                modelo.put("fecha", diaf + "-" + mesf + "-" + aniof);

                if ("InvFisicoValoradoDetallado".equals(sAccion)) {
                    return new ModelAndView(new ListarInventarioDetPDF(), modelo);
                }
                if (datoper.getCod_esta() == 200010) {
                    return new ModelAndView(new ListarInventarioFechaCNSPDF(), modelo);
                }
                if ("T".equals(datoper.getArea())) {
                    return new ModelAndView(new ListarInventarioPrivPDF(), modelo);
                }
                return new ModelAndView(new ListarInventarioFechaPDF(), modelo);
            }
        }

        if ("ListarAlmacen".equals(sAccion)) {
            String sFecha_fin = request.getParameter("valor_2");
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
                return new ModelAndView("administrarfarmacias/ListarImm", modelo);
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

                Medicamentos datos = new Medicamentos();
                datos.setMes(Integer.parseInt(mesf));
                datos.setGestion(Integer.parseInt(aniof));
                datos.setCod_esta(cliente.getCod_esta());
                datos.setId_farmacia(cliente.getId_farmacia());
                datos.setMedicamento("%");
                datos.setId_persona(cliente.getId_persona());
                datos.setFecha_ini(dFechaini1);
                datos.setFecha_fin(dFechafin1);
                List listarMedicamentos = this.mi.getListarInvAlma(datos);///getListarInvAlma todos los medicamentos de todas farmacia
                modelo.put("listarMedicamentos", listarMedicamentos);

                List listarMedicamentosGral = this.mi.getListarMedicamentosAlmaGral(datos);
                modelo.put("listarMedicamentosGral", listarMedicamentosGral);

                modelo.put("gestion", aniof);
                modelo.put("mes", mesf);
                modelo.put("dia", diaf);
                Personas buscarEmpleado2 = this.mi.getDatosPersona(cliente.getId_persona());
                modelo.put("persona", buscarEmpleado2);

                return new ModelAndView(new ListarInventarioAlmaFechaCNSPDF(), modelo);
            }
        }

        if ("ReporteCarmeloExcel".equals(sAccion)) {
            String sFecha_ini = request.getParameter("valor_1");
            String sFecha_fin = request.getParameter("valor_2");
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
                return new ModelAndView("administrarfarmacias/ListarImm", modelo);
            }
//            } else {
//                int iAnio1 = Integer.parseInt(anioi) - 1900;
//                int iMes1 = Integer.parseInt(mesi) - 1;
//                int iDia1 = Integer.parseInt(diai);
//                int iHor1 = Integer.parseInt(horai);
//                int iMin1 = Integer.parseInt(minutoi);
//                int iAnio2 = Integer.parseInt(aniof) - 1900;
//                int iMes2 = Integer.parseInt(mesf) - 1;
//                int iDia2 = Integer.parseInt(diaf);
//                int iHor2 = Integer.parseInt(horaf);
//                int iMin2 = Integer.parseInt(minutof);
//
//                Date dFechaini1 = new Date(iAnio1, iMes1, iDia1, iHor1, iMin1, 00);
//                Date dFechafin1 = new Date(iAnio2, iMes2, iDia2, iHor2, iMin2, 59);
//
//                Medicamentos datos = new Medicamentos();
//                datos.setCod_esta(cliente.getCod_esta());
//                datos.setId_farmacia(cliente.getId_farmacia());
//                datos.setFecha_ini(dFechaini1);
//                datos.setFecha_fin(dFechafin1);
//                List listarCarmeloExcel = this.mi.getListarCarmelosExel(datos);
//                modelo.put("listarCarmelos", listarCarmeloExcel);
//
//                return new ModelAndView(new excel(), modelo);
//            }
        }

        if ("BuscarProg".equals(sAccion)) {
            String sFecha_fin = request.getParameter("valor_2");
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
                return new ModelAndView("administrarfarmacias/ListarImm", modelo);
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

                Medicamentos datos = new Medicamentos();
                datos.setMes(Integer.parseInt(mesf));
                datos.setGestion(Integer.parseInt(aniof));
                datos.setCod_esta(cliente.getCod_esta());
                datos.setFecha_ini(dFechaini1);
                datos.setFecha_fin(dFechafin1);
                datos.setExpedido("P");

                List listarDatosImm = this.mi.getListarImmFechaDada(datos);///getListarImmFechaDadaProg
                modelo.put("listarDatosImm", listarDatosImm);

                modelo.put("gestion", aniof);
                modelo.put("mes", mesf);

                Medicamentos medid = new Medicamentos();
                medid.setCod_esta(cliente.getCod_esta());
                List listarprog = this.mi.getListarProgramas(medid);
                modelo.put("listarProg", listarprog);

                return new ModelAndView(new ListarInventarioProgPDF(), modelo);
            }
        }

        if ("GenerarIMM".equals(sAccion) || "ConsumoMeses".equals(sAccion) || "IMMaExcel".equals(sAccion)) {
            String sFecha_ini = request.getParameter("valor_1");
            String sFecha_fin = request.getParameter("valor_2");
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
                return new ModelAndView("administrarfarmacias/ListarImm", modelo);
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

                modelo.put("f1", sFecha_ini);
                modelo.put("f2", sFecha_fin);
                Medicamentos datos = new Medicamentos();
                datos.setCod_esta(cliente.getCod_esta());
                datos.setFecha_ini(dFechaini1);
                datos.setFecha_fin(dFechafin1);
                datos.setExpedido("C");
                List listarDatosImmCNS = this.mi.getListarImmCNS(datos);
                modelo.put("listarDatosImm", listarDatosImmCNS);
                if ("ConsumoMeses".equals(sAccion)) {
                    datos.setExpedido("D"); ////getListarImmCNS2
                    List listarDatosImmCNS2 = this.mi.getListarImmCNS2(datos);
                    modelo.put("listarDatosImm", listarDatosImmCNS2);
                }

                datos.setExpedido("S");
                List listarDatosImmSaldo = this.mi.getListarImmCNSsaldo(datos);
                modelo.put("listarImmSaldos", listarDatosImmSaldo);
                if ("ConsumoMeses".equals(sAccion)) {
                    datos.setExpedido("M"); ////getListarImmCNSsaldo2
                    List listarDatosImmSaldo2 = this.mi.getListarImmCNSsaldo2(datos);
                    modelo.put("listarImmSaldos", listarDatosImmSaldo2);
                }

                modelo.put("gestion", anioi);
                modelo.put("mes", mesi);

                Medicamentos medid = new Medicamentos();
                medid.setCod_esta(cliente.getCod_esta());
                List listarprog = this.mi.getListarProgramas(medid);
                modelo.put("listarProg", listarprog);

                if ("ConsumoMeses".equals(sAccion)) {
                    return new ModelAndView(new ListarConsultaMesCNSPDF(), modelo);
                }

                if (datoper.getCod_esta() == 200010) {
//                    if ("IMMaExcel".equals(sAccion)) {
//                        for (int i = 0; i < listarDatosImmCNS.size(); i++) {/// carga en una matrix lista las 2 tablas con los respectivos saldos
//                            Medicamentos datosI = (Medicamentos) listarDatosImmCNS.get(i);
//                            for (int j = 0; j < listarDatosImmSaldo.size(); j++) {
//                                Medicamentos datosS = (Medicamentos) listarDatosImmSaldo.get(j);
//                                if (datosS.getId_medicamento() == datosI.getId_medicamento()) {
//                                    datosI.setSuma10(datosS.getSuma4()); ////es el valor individual
//                                    listarDatosImmCNS.set(j, datosI); ////se mete a la lista general
//                                }
//                            }
//                        }
//                        modelo.put("listarDatosImm", listarDatosImmCNS);
//                        return new ModelAndView(new excel2(), modelo);
//                    }
                    return new ModelAndView(new ListarConsultaImmCNSPDF(), modelo);
                }
            }
        }

        Medicamentos datos = new Medicamentos();
        datos.setCod_esta(cliente.getCod_esta());
        datos.setId_farmacia(cliente.getId_farmacia());
        datos.setExpedido("F");
        if (cliente.getId_rol2() == 27 || cliente.getId_rol2() == 30) {
            datos.setExpedido("%");
        }
        List listarImm = this.mi.getListarImm(datos);
        modelo.put("listarImm", listarImm);
        modelo.put("accion", "Mostrar");

        return new ModelAndView("administrarfarmacias/ListarImm", modelo);

    }

    private static class excel2 extends AbstractXlsView {
        
        @Override
        protected void buildExcelDocument(Map<String, Object> map, Workbook wrkbk, HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
            
            Sheet sheet = wrkbk.createSheet("IMM");

            Row fila = sheet.createRow(0);
            Cell celda;

            String[] titulos = {"No", "COD", "MEDICAMENTO\nE INSUMOS", "FORMA\nFARMACEUTICA", "CONCEN-\nTRACION", "Saldo\nAnter.", "Almacen", "Caja\nChica", "Otros\nRevers.", "Total\nIngreso", "Externo", "Internados", "Unidosis", "Otros\nEgreso", "Total\nEgreso", "Saldo\nFinal"};
            int i;

            for (i = 0; i < titulos.length; i++) {
                celda = fila.createCell(i);
                celda.setCellValue(titulos[i]);
            }
            // Nueva fila
            fila = sheet.createRow(1);
            List listarMed = (List) map.get("listarDatosImm");
            for (i = 0; i < listarMed.size(); i++) {
                Medicamentos dato = (Medicamentos) listarMed.get(i);
                Row filas = sheet.createRow(i + 5);
                for (int j = 0; j < 16; j++) {
                    HSSFRow row = (HSSFRow) sheet.createRow(i + 1);
                    row.createCell(0).setCellValue(i + 1);  ////Numeracion
                    row.createCell(1).setCellValue(dato.getCodsumi());  ////codsumi
                    row.createCell(2).setCellValue(dato.getMedicamento());  ////Medicamento
                    row.createCell(3).setCellValue(dato.getForma_far());  ////Forma_far
                    row.createCell(4).setCellValue(dato.getConcentra());  ///Concentra
                    row.createCell(5).setCellValue(dato.getSuma10());  ///suma10
                    row.createCell(6).setCellValue(dato.getSuma1());  ///
                    row.createCell(7).setCellValue(dato.getSuma2());  ///
                    row.createCell(8).setCellValue(dato.getSuma3());  ///
                    row.createCell(9).setCellValue(dato.getSuma1() + dato.getSuma2() + dato.getSuma3());  ///
                    row.createCell(10).setCellValue(dato.getSuma4());  ///
                    row.createCell(11).setCellValue(dato.getSuma5());  ///
                    row.createCell(12).setCellValue(dato.getSuma6());  ///
                    row.createCell(13).setCellValue(dato.getSuma8());  ///
                    row.createCell(14).setCellValue(dato.getSuma4() + dato.getSuma5() + dato.getSuma6() + dato.getSuma8());  ///
                    row.createCell(15).setCellValue(dato.getSuma10() + dato.getSuma1() + dato.getSuma2() + dato.getSuma3() - dato.getSuma4() - dato.getSuma5() - dato.getSuma6() - dato.getSuma7() + dato.getSuma8());  ///

                }
            }
        }

    }

    private static class excel extends AbstractXlsView {

        @Override
        protected void buildExcelDocument(Map<String, Object> map, Workbook wrkbk, HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
            
            Sheet sheet = wrkbk.createSheet("medicamentos");

            Row fila = sheet.createRow(0);
            Cell celda;

            String[] titulos = {"matricula", "paterno", "materno", "nombre", "CI", "expedido", "fecha_nac", "CodCNS", "matricula", "Establecimiento", "Cie10", "Fecha Dotacion", "Cantidad", "Usuario"};
            int i;

            for (i = 0; i < titulos.length; i++) {
                celda = fila.createCell(i);
                celda.setCellValue(titulos[i]);
            }
            // Nueva fila
            fila = sheet.createRow(1);
            List listarCarmelo = (List) map.get("listarCarmelos");
            for (i = 0; i < listarCarmelo.size(); i++) {
                Medicamentos dato = (Medicamentos) listarCarmelo.get(i);
                Row filas = sheet.createRow(i + 5);
                for (int j = 0; j < 14; j++) {
                    HSSFRow row = (HSSFRow) sheet.createRow(i + 1);
                    row.createCell(0).setCellValue(dato.getMedicamento());  ////matricula
                    row.createCell(1).setCellValue(dato.getForma_far());  ////patrerno
                    row.createCell(2).setCellValue(dato.getConcentra());  ////materno
                    row.createCell(3).setCellValue(dato.getCodsumi());  ////nombre
                    row.createCell(4).setCellValue(dato.getNro_lote());  ////carnet
                    row.createCell(5).setCellValue(dato.getExpedido());    ////expedido
                    row.createCell(6).setCellValue(dato.getCod_tipo());  ///fecha_nac
                    row.createCell(7).setCellValue(dato.getTipo());  ///codCNS
                    row.createCell(8).setCellValue(dato.getB1());  ///Codestab
                    row.createCell(9).setCellValue(dato.getCod_cta());  ///Cie10
                    row.createCell(10).setCellValue(dato.getCod_espe());  ///fechadotacion
                    row.createCell(11).setCellValue(dato.getB2());  ///Cantidad
                    row.createCell(12).setCellValue(dato.getB3());  ///Usuario
                    row.createCell(13).setCellValue(dato.getCod_material());  ///Establecimiento
                }
            }
        }

    }

}
