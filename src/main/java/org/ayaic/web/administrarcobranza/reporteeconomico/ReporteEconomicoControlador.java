package org.ayaic.web.administrarcobranza.reporteeconomico;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Date;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Detalle;
import org.ayaic.domain.Localidades;
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.Rubros;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.document.AbstractXlsView;

@Controller
public class ReporteEconomicoControlador {

    private final MiFacade mi;

    public ReporteEconomicoControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/ReporteEconomico.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String id_rubro = request.getParameter("id_rubro");
        String id_rubro1 = request.getParameter("id_rubro1");
        String tipoe = request.getParameter("tipoe");
        String accion = request.getParameter("accion");
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

        String sId_estado = request.getParameter("id_estado");
        String sAccion = request.getParameter("boton");
        Rubros aux = new Rubros();
        List listarRubros = this.mi.getListarRubros(aux);
        modelo.put("listarRubros", listarRubros);

        Localidades local = new Localidades();
        List Estab1 = this.mi.getListarEsta(local);
        Localidades datoestab = (Localidades) Estab1.get(0);
        modelo.put("Estab", Estab1);        
        modelo.put("tipo_consul", Integer.toString(cliente.getId_consultorio()));
        modelo.put("dato", cliente);

        aux.setRubro(accion);
        aux.setId_estado("T");
        List listarRubroT = this.mi.getListarRubrosT(aux);
        if (listarRubroT.size() > 0) {
            Rubros rubrot = (Rubros) listarRubroT.get(0);
            id_rubro = Integer.toString(rubrot.getId_rubro());
            if (rubrot.getRubro().equals(accion) || (rubrot.getRubro() + ".xls").equals(accion)) {
                String tipoe2 = request.getParameter("tipoe2");
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
                    return new ModelAndView("administrarcobranza/ReporteEconomicoEntrada", modelo);
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
                    dato.setFecha_ini(dFechaini1);
                    dato.setFecha_fin(dFechafin1);
                    dato.setCod_esta(cliente.getCod_esta());
                    Detalle detalle = new Detalle();
                    detalle.setFecha_ini(dFechaini1);
                    detalle.setFecha_fin(dFechafin1);
                    detalle.setCod_esta(cliente.getCod_esta());
                    dato.setId_persona(cliente.getId_persona());
                    modelo.put("persona", dato);

                    List listaRubros = this.mi.getListaRubro();
                    modelo.put("listaRubros", listaRubros);
                    if (cliente.getId_consultorio() == 14 || cliente.getId_consultorio() == 7 || cliente.getId_consultorio() == 3) { //reporte general
                        if (Integer.parseInt(id_rubro) > 0) { ///16 y 20 para reorte odontologia riesgo compartido
                            dato.setId_rubro(Integer.parseInt(id_rubro));
                            dato.setId_pais(Integer.parseInt(id_rubro));
                            dato.setId_dispensa(cliente.getId_persona());
                            dato.setTipo("0");   ////getReporteEconomicoGenRubro tabla pedidos
                            List listaCobros = this.mi.getReporteEconomicoGenRubro(dato);
                            modelo.put("listaCobros", listaCobros);
                            if (cliente.getId_consultorio() == 3) {
                                List listaCobrosIndi = this.mi.getReporteEconomicoGenRubroDental(dato);
                                modelo.put("listaCobros", listaCobrosIndi);
                            }

                            detalle.setExpedido("T");
                            detalle.setCod_esta(cliente.getCod_esta());
                            detalle.setId_rubro(Integer.parseInt(id_rubro));
                            detalle.setId_empresa(Integer.parseInt(id_rubro));
                            List listarC = this.mi.getListarDetTotal(detalle); /////getListarDetTotal   no funciona farmacia porque no esta en la tabla detalle
                            modelo.put("listarCostos", listarC);
                            //   modelo.put("anio1", Integer.toString(Integer.parseInt(anio1)+1900));
                            //   modelo.put("mes1", Integer.toString(Integer.parseInt(mes1)+1));
                            //   modelo.put("dia1", dia1);
                            //   modelo.put("anio2", Integer.toString(Integer.parseInt(anio2)+1900));
                            //   modelo.put("mes2", Integer.toString(Integer.parseInt(mes2)+1));
                            //   modelo.put("dia2", dia2);
                        } else {
                            // buscamos los montos de cobros de una persona
                            dato.setId_rubro(1);
                            dato.setId_pais(100);
                            List listaCobros = this.mi.getReporteEconomicoGenRubro(dato); ///tabla pedidos 
                            modelo.put("listaCobros", listaCobros);
                        }
                        if ((rubrot.getRubro() + ".xls").equals(accion)) {
                            return new ModelAndView(new excel2(), modelo);
                        }
                        return new ModelAndView(new InformeEcoPDF(), modelo);
                    } else {   //reporte individual de la persona que cobra   
                        if (Integer.parseInt(id_rubro) > 0) {
                            dato.setId_rubro(Integer.parseInt(id_rubro));
                            List listaCobros = this.mi.getReporteEconomicoRubro(dato);
                            modelo.put("listaCobros", listaCobros);
                            detalle.setExpedido("T");
                            detalle.setId_detalle(cliente.getId_persona());
                            detalle.setId_rubro(Integer.parseInt(id_rubro));
                            detalle.setId_empresa(Integer.parseInt(id_rubro));
                            detalle.setId_costo(Integer.parseInt(id_rubro));
                            detalle.setCod_esta(cliente.getCod_esta());
                            List listarC = this.mi.getListarDetIndividual(detalle);
                            modelo.put("listarCostos", listarC);
                        } else {
                            // buscamos los montos de cobros de una persona
                            List listaCobros = this.mi.getReporteEconomico(dato);
                            modelo.put("listaCobros", listaCobros);
                        }
                        if ((rubrot.getRubro() + ".xls").equals(accion)) {
                            return new ModelAndView(new excel2(), modelo);
                        }
                        return new ModelAndView(new InformeEcoPDF(), modelo);
                    }
                }
            }
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
                return new ModelAndView("administrarcobranza/ReporteEconomicoEntrada", modelo);
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
                dato.setFecha_ini(dFechaini1);
                dato.setFecha_fin(dFechafin1);
                dato.setCod_esta(cliente.getCod_esta());
                dato.setId_persona(cliente.getId_persona());
                modelo.put("persona", dato);

                List listaRubros = this.mi.getListaRubro();
                modelo.put("listaRubros", listaRubros);

                dato.setTipo("C");
                List listaCobrosTots = this.mi.getDatosPedidoDetRubro(dato);
                modelo.put("listaCobrosOtros", listaCobrosTots);

                //List listaCobrosfar = this.mi.getReporteCobroFarm(dato);
                //modelo.put("listaCobrosFar", listaCobrosfar);
                if (cliente.getId_rol() == 1 || cliente.getId_rol() == 5 || cliente.getId_rol() == 60 || cliente.getId_rol() == 27) {
                    if (Integer.parseInt(request.getParameter("id_rubro")) > 0) {
                        dato.setId_rubro(Integer.parseInt(id_rubro));
                        dato.setId_pais(Integer.parseInt(id_rubro));
                        List listaCobros = this.mi.getReporteEconomicoGenRubro(dato);
                        modelo.put("listaCobros", listaCobros);
                    } else {
                        // buscamos los montos de cobros de una persona
                        dato.setId_rubro(0);
                        dato.setId_pais(100);
                        List listaCobros = this.mi.getReporteEconomicoGenRubro(dato);
                        modelo.put("listaCobros", listaCobros);
                    }
                    return new ModelAndView(new ReporteEconomicoGenPDF(), modelo);
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
                    if ("F".equals(datoestab.getArea())) {
                        modelo.put("listaRubros", listarRubros);
                        return new ModelAndView(new ReporteEconomicoHGPDF(), modelo);
                    } else {
                        return new ModelAndView(new ReporteEconomicoPDF(), modelo);
                    }
                }
            }
        }

        return new ModelAndView("administrarcobranza/ReporteEconomicoEntrada", modelo);
    }

    private static class excel2 extends AbstractXlsView {
        
        @Override
        protected void buildExcelDocument(Map<String, Object> map, Workbook wrkbk, HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
            
            Sheet sheet = wrkbk.createSheet("economico");

            Row fila = sheet.createRow(0);
            Cell celda;

            String[] titulos = {"Nombre Paciente", "fecha", "idCosto", "Nombre Costo", "Costo_unit", "Monto Cobrado", "IdCaja", "IdMedico", "detalle"};
            int i;

            for (i = 0; i < titulos.length; i++) {
                celda = fila.createCell(i);
                celda.setCellValue(titulos[i]);
            }
            // Nueva fila
            fila = sheet.createRow(1);
            List listarcobros = (List) map.get("listarCostos");
            for (i = 0; i < listarcobros.size(); i++) {
                Detalle dato = (Detalle) listarcobros.get(i);
                Row filas = sheet.createRow(i + 5);
                for (int j = 0; j < 7; j++) {
                    HSSFRow row = (HSSFRow) sheet.createRow(i + 1);
                    row.createCell(0).setCellValue(dato.getCadena1());  ////Nombres
                    row.createCell(1).setCellValue(dato.getCadena2());  ////fecha
                    row.createCell(2).setCellValue(dato.getId_costo());  ////Id_costo
                    row.createCell(3).setCellValue(dato.getCosto());  ////Ccsto
                    row.createCell(4).setCellValue(dato.getCosto_unit());  ////costo
                    row.createCell(5).setCellValue(dato.getEntrada());  ////entrada
                    row.createCell(6).setCellValue(dato.getId_persona());  ////Id_persona
                    row.createCell(7).setCellValue(dato.getId_dispensa());  ////Id_persona
                    row.createCell(8).setCellValue(dato.getIndicacion());  ////detalle
                }
            }
        }

    }

}
