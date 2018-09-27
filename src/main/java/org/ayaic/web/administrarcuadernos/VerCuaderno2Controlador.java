package org.ayaic.web.administrarcuadernos;

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
import org.ayaic.domain.Consultorios;
import org.ayaic.domain.Cuadernos;
import org.ayaic.domain.Localidades;
import org.ayaic.domain.Personas;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.document.AbstractXlsView;

@Controller
public class VerCuaderno2Controlador {

    private final MiFacade mi;

    public VerCuaderno2Controlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/VerCuaderno2.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        Personas persona = this.mi.getBuscarPersona(cliente.getId_persona()); // saca un registro a ser modificado
        Consultorios datosconsul = this.mi.getDatosConsultorio(persona.getId_consultorio()); // saca un registro a ser modificado
        modelo.put("tipo_medico", Integer.toString(datosconsul.getId_cargo()));
        //Recuperamos variables del jsp
        String sId_estado = request.getParameter("id_estado");
        String sAccion = request.getParameter("accion");

        Localidades local = new Localidades();
        List Estab1 = this.mi.getListarEsta(local);
        Localidades datol = (Localidades) Estab1.get(0);
        modelo.put("localidades", datol);
        modelo.put("dato", cliente);

        if ("C.Prenatal-Parto-Puerperio-PF-xls".equals(sAccion) || "C.Prenatal-Parto-Puerperio-PF".equals(sAccion) || "Ginecologia".equals(sAccion) || "SNIS Consultas Prenatales".equals(sAccion)) {
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
                return new ModelAndView("administrarcuadernos/ListarLibros", modelo);
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

                Cuadernos dato = new Cuadernos();
                dato.setId_persona(cliente.getId_persona());
                dato.setFecha_ini(dFechaini1);
                dato.setFecha_fin(dFechafin1);
                dato.setCod_esta(cliente.getCod_esta());
                modelo.put("dFechafin1", anioi + "-" + mesi + "-" + diai + " " + horai + ":" + minutoi);
                modelo.put("persona", persona);
                dato.setTipo("3"); //U   getVerCuaderno2C3Uni 
                List listaCobros = this.mi.getVerCuaderno2C3Uni(dato);
                modelo.put("listaCobros", listaCobros);
                if (datosconsul.getId_cargo() == 7) {
                    dato.setTipo("2"); //T   getVerCuaderno2C3 total getVerCuaderno2C3
                    List listaCobros2 = this.mi.getVerCuaderno2C3(dato);
                    modelo.put("listaCobros", listaCobros2);
                }
                List listaCie = this.mi.getVerCuaderno1Cie(dato);
                modelo.put("listaCie", listaCie);
                if ("Ginecologia".equals(sAccion) || "SNIS Consultas Prenatales".equals(sAccion)) {
                    dato.setEstado("T");
                    List listaCobros2 = this.mi.getVerCuaderno2(dato);
                    modelo.put("listaCobros", listaCobros2);
                    if ("Ginecologia".equals(sAccion)) {
                        return new ModelAndView(new VerCuaderno22PDF(), modelo);
                    } else {
                        dato.setTipo("P");
                        List listaControles = this.mi.getReporteProduccionPrenatal(dato);
                        modelo.put("listaControles", listaControles);
                        return new ModelAndView(new VerConsultaPrenatalesPDF(), modelo);
                    }
                } else {
                    //List listaCobros2 = this.mi.getVerCuaderno2C3Uni(dato);
                    //modelo.put("listaCobros", listaCobros2);
                    if ("C.Prenatal-Parto-Puerperio-PF-xls".equals(sAccion)) {
                        return new ModelAndView(new excel2(), modelo);
                    }
                    return new ModelAndView(new VerCuaderno2_2014PDF(), modelo);
                }
            }
        }

        if ("Solo Partos".equals(sAccion)) {
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
                return new ModelAndView("administrarcuadernos/ListarLibros", modelo);
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

                Cuadernos dato = new Cuadernos();
                dato.setId_persona(cliente.getId_persona());
                dato.setFecha_ini(dFechaini1);
                dato.setFecha_fin(dFechafin1);
                dato.setCod_esta(cliente.getCod_esta());
                dato.setTipo("U");

                List listaCobros = this.mi.getVerCuaderno2Parto(dato);
                modelo.put("listaCobros", listaCobros);

                return new ModelAndView(new VerCuaderno2PDF(), modelo);
            }
        }

        if ("C.Prenatal-Parto-Puerperio-Planificacion".equals(sAccion)) {
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
                return new ModelAndView("administrarcuadernos/ListarLibros", modelo);
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

                Cuadernos dato = new Cuadernos();
                dato.setId_persona(cliente.getId_persona());
                dato.setFecha_ini(dFechaini1);
                dato.setFecha_fin(dFechafin1);
                dato.setCod_esta(cliente.getCod_esta());
                dato.setTipo("3");
                List listaCie = this.mi.getVerCuaderno1Cie(dato);
                modelo.put("listaCie", listaCie);
                List listaCobros = this.mi.getVerCuaderno2(dato);
                modelo.put("listaCobros", listaCobros);
                if (datosconsul.getId_cargo() == 7) {
                    dato.setTipo("2");
                    List listaCobros2 = this.mi.getVerCuaderno2C3(dato);
                    modelo.put("listaCobros", listaCobros2);
                }
                return new ModelAndView(new VerCuaderno2_2014PDF(), modelo);
            }
        }

        return new ModelAndView("administrarcuadernos/ListarLibros", modelo);
    }
    
    private static class excel2 extends AbstractXlsView {
        
        @Override
        protected void buildExcelDocument(Map<String, Object> map, Workbook wrkbk, HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
            
            Sheet sheet = wrkbk.createSheet("medicamentos");

            Row fila = sheet.createRow(0);
            Cell celda;

        String[] titulos = {"id", "fecha", "id_p", "hcl", "registros", "nombres", "sexo", "edad", "peso", "talla", "pa", "Imc", "ControlPos", "pap", "semanas", "fum", "parto", "anestecie", "diagnostico", "pesonino"};
        int i;

        for (i  = 0; i< titulos.length ; i++) {
                celda = fila.createCell(i);
            celda.setCellValue(titulos[i]);
        }
        // Nueva fila
        fila  = sheet.createRow(1);
        List listarKardexUsua = (List) map.get("listaCobros");
        for (i  = 0; i< listarKardexUsua.size (); i++) {
                Cuadernos dato = (Cuadernos) listarKardexUsua.get(i);
            Row filas = sheet.createRow(i + 5);
            for (int j = 0; j < 18; j++) {
                HSSFRow row = (HSSFRow) sheet.createRow(i + 1);
                row.createCell(0).setCellValue(dato.getId_historial());  ////id
                row.createCell(1).setCellValue(dato.getFecha().getDate() + "/" + (dato.getFecha().getMonth() + 1) + "/" + (dato.getFecha().getYear() + 1900) + " " + dato.getFecha().getHours() + ":" + dato.getFecha().getMinutes());  ///fecha
                row.createCell(2).setCellValue(dato.getId_persona());  ////id_persona
                row.createCell(3).setCellValue(dato.getHcl());  ////hcl
                row.createCell(4).setCellValue(dato.getRegistro());  ////registro
                row.createCell(5).setCellValue(dato.getNombres());    ////nombres
                if (dato.getId_tipo_sexo() == 1) {
                    row.createCell(6).setCellValue("Femenino");  ///sexo 
                } else {
                    row.createCell(6).setCellValue("Masculino");  ///sexo  
                }
                row.createCell(7).setCellValue(dato.getEdad());  ///edad
                row.createCell(8).setCellValue(dato.getPeso());  ///peso
                row.createCell(9).setCellValue(dato.getTalla());  ///talla
                row.createCell(10).setCellValue(dato.getPa());  ///pa

                if (dato.getImc() == 1) {
                    row.createCell(11).setCellValue("DESNUTRIDA");  ///imc 
                } else if (dato.getImc() == 2) {
                    row.createCell(11).setCellValue("normal");  ///imc   
                } else if (dato.getImc() == 3) {
                    row.createCell(11).setCellValue("sobrepeso");  ///imc   
                } else if (dato.getImc() == 4) {
                    row.createCell(11).setCellValue("obesidad");  ///imc    
                } else {
                    row.createCell(11).setCellValue("");  ///imc    
                }

                if (dato.getSuma1() == 1) {
                    row.createCell(12).setCellValue("1<5m");  ///control prenatal  
                }
                if (dato.getSuma2() == 1) {
                    row.createCell(12).setCellValue("1>5m");  ///control prenatal  
                }
                if (dato.getSuma3() == 1) {
                    row.createCell(12).setCellValue("Rep");  ///control prenatal   
                }
                if (dato.getSuma4() == 1) {
                    row.createCell(12).setCellValue("4to");  ///control prenatal    
                }

                row.createCell(13).setCellValue(dato.getPap());  ///pap
                row.createCell(14).setCellValue(dato.getSemanas());     ///semanas
                if (dato.getSemanas() == 0) {
                    row.createCell(15).setCellValue("0");     ///semanas 
                } else {
                    row.createCell(15).setCellValue(dato.getFum().getDate() + "/" + (dato.getFum().getMonth() + 1) + "/" + (dato.getFum().getYear() + 1900));  ///fum
                }
                row.createCell(16).setCellValue(dato.getParto());  ///parto
                row.createCell(17).setCellValue(dato.getAnestesia());  ///anestecie

                if (dato.getDiagnostico() != null && !"".equals(dato.getDiagnostico())) {
                    dato.setDiagnostico(dato.getDiagnostico().replaceAll("<p>", " "));
                    dato.setDiagnostico(dato.getDiagnostico().replaceAll("</p>", " "));
                    dato.setDiagnostico(dato.getDiagnostico().replaceAll("&nbsp;", ""));
                    dato.setDiagnostico(dato.getDiagnostico().replaceAll("<strong>", " "));
                    dato.setDiagnostico(dato.getDiagnostico().replaceAll("</strong>", " "));
                    dato.setDiagnostico(dato.getDiagnostico().replaceAll("<br />", " "));
                    dato.setDiagnostico(dato.getDiagnostico().replaceAll("<u>", " "));
                    dato.setDiagnostico(dato.getDiagnostico().replaceAll("</u>", " "));
                    dato.setDiagnostico(dato.getDiagnostico().replaceAll("<ul>", " "));
                    dato.setDiagnostico(dato.getDiagnostico().replaceAll("</ul>", " "));
                    dato.setDiagnostico(dato.getDiagnostico().replaceAll("<ol>", " "));
                    dato.setDiagnostico(dato.getDiagnostico().replaceAll("</ol>", " "));
                    dato.setDiagnostico(dato.getDiagnostico().replaceAll("<li>", " "));
                    dato.setDiagnostico(dato.getDiagnostico().replaceAll("</li>", " "));
                    dato.setDiagnostico(dato.getDiagnostico().replaceAll("&ntilde;", "n"));
                    dato.setDiagnostico(dato.getDiagnostico().replaceAll("&Ntilde;", "N"));
                    dato.setDiagnostico(dato.getDiagnostico().replaceAll("&Aacute;", "A"));
                    dato.setDiagnostico(dato.getDiagnostico().replaceAll("&Eacute;", "E"));
                    dato.setDiagnostico(dato.getDiagnostico().replaceAll("&Iacute;", "I"));
                    dato.setDiagnostico(dato.getDiagnostico().replaceAll("&Oacute;", "O"));
                    dato.setDiagnostico(dato.getDiagnostico().replaceAll("&Uacute;", "U"));
                    dato.setDiagnostico(dato.getDiagnostico().replaceAll("&aacute;", "a"));
                    dato.setDiagnostico(dato.getDiagnostico().replaceAll("&eacute;", "e"));
                    dato.setDiagnostico(dato.getDiagnostico().replaceAll("&iacute;", "i"));
                    dato.setDiagnostico(dato.getDiagnostico().replaceAll("&oacute;", "o"));
                    dato.setDiagnostico(dato.getDiagnostico().replaceAll("&uacute;", "u"));
                    dato.setDiagnostico(dato.getDiagnostico().replaceAll("&quot;", "'"));
                }
                row.createCell(18).setCellValue(dato.getDiagnostico());  ///diagnostico
                row.createCell(19).setCellValue(dato.getAuto());  ///pesonino
            }
        }
    }  

}

}
