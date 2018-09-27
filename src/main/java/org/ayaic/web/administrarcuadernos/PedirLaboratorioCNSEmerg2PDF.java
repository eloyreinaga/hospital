package org.ayaic.web.administrarcuadernos;

import java.awt.Color;
import com.itextpdf.text.pdf.BarcodeQRCode;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.view.document.AbstractPdfView;
import java.util.*;
import java.text.*;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Cuadernos;
import org.ayaic.domain.Historiales;
import org.ayaic.domain.Localidades;
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.Personas;

public class PedirLaboratorioCNSEmerg2PDF extends AbstractPdfView {

    private static final Font TITULO_FONT = new Font(Font.HELVETICA, 15, Font.BOLD, Color.black);
    private static final Font CABEZA_COLUMNA_FONT = new Font(Font.HELVETICA, 8, Font.ITALIC, Color.black);
    private static final Font DATO_FONT = new Font(Font.HELVETICA, 8, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_B = new Font(Font.HELVETICA, 12, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_C = new Font(Font.HELVETICA, 16, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_A = new Font(Font.HELVETICA, 8, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_A1 = new Font(Font.HELVETICA, 10, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_BOLD = new Font(Font.HELVETICA, 8, Font.BOLD, Color.black);
    private static final Font marcagua = new Font(Font.HELVETICA, 25, Font.BOLD, Color.lightGray);
    private static final int MARGIN = 32;

    protected void buildPdfMetadata(Map model, Document document, HttpServletRequest request) {
        document.setPageSize(PageSize.LETTER);
        document.setMargins(20, 20, 25, 20);
    }

    protected void buildPdfDocument(Map model, Document document, PdfWriter writer, HttpServletRequest req, HttpServletResponse resp) throws Exception {

        java.util.List datoLabp = (java.util.List) model.get("datosLab");
        java.util.List listaPres = (java.util.List) model.get("listarPres");
        java.util.List seguros = (java.util.List) model.get("listaPacAfi");
        java.util.List datosPacEmp = (java.util.List) model.get("datosPacEmp");
        Clientes dato = (Clientes) model.get("dato");
        Historiales datoHis = (Historiales) model.get("datosHis");
        Cuadernos datointer = (Cuadernos) model.get("salasInter");
        Personas datoMed = (Personas) model.get("datosMed");
        Pacientes datoPac = (Pacientes) model.get("datosPac");
        Localidades datoestab = (Localidades) model.get("datoestab");
        Pacientes datoPaciente = (Pacientes) model.get("datos");
        String id_pedido = (String) model.get("id_pedido");
        String marcaa = (String) model.get("marcaa");
        String cod = (String) model.get("cod");
        Date fech = new Date();
        int sw = 0;

        PdfPCell cell;
        //////////////////////////////////////////////////////////////////////////////////////////////
        Pacientes datoPacEmp = (Pacientes) datosPacEmp.get(0);

        if (datoLabp.isEmpty()) {
            Paragraph pf = new Paragraph("No existe Datos", new Font(Font.HELVETICA, 18, Font.BOLDITALIC, new Color(0, 0, 0)));
            document.add(pf);
        } else {

            String lab = "", tipo = "";

            for (int k = 0; k < datoLabp.size(); k++) {
                Cuadernos listaLab = (Cuadernos) datoLabp.get(k);
                /////une los laboratorios en una sola orden pero 121 orinas lo saca en otra orden
                if (listaLab.getId_costo() != 121 && listaLab.getId_laboratorio() != 12 && listaLab.getId_laboratorio() != 13 && listaLab.getId_laboratorio() != 14 && listaLab.getId_laboratorio() != 15) {
                    listaLab.setId_laboratorio(19);
                }
                if (sw == 0) {///laboratroios iguales
                    sw = listaLab.getId_laboratorio();
                }
                if (sw == listaLab.getId_laboratorio()) {///laboratroios iguales
                    lab = lab + listaLab.getLaboratorio() + "(" + listaLab.getTipoconsulta() + ")        ";
                    tipo = listaLab.getBacterias();
                    sw = listaLab.getId_laboratorio();
                } else { ////fin IF de laboratorios iguales
                    PdfPTable tablex = new PdfPTable(4);
                    int[] fxwidths = {10, 80, 10, 30}; // percentage
                    tablex.setWidths(fxwidths);
                    tablex.setWidthPercentage(100);

                    cell = new PdfPCell(new Phrase(datoestab.getNombreseg() + "\n" + dato.getEstablecimiento() + "\n\n", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setBorder(Rectangle.NO_BORDER);
                    cell.setColspan(2);
                    tablex.addCell(cell);

                    cell = new PdfPCell(new Phrase("No de\nAsegurado", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setBorder(Rectangle.NO_BORDER);
                    cell.setColspan(1);
                    tablex.addCell(cell);

                    cell = new PdfPCell(new Phrase(datoPaciente.getNro_registro(), DATO_FONT_B));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setColspan(1);
                    tablex.addCell(cell);

                    cell = new PdfPCell(new Phrase(".SOLICITUD DE EXAMENES", TITULO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBorder(Rectangle.NO_BORDER);
                    cell.setColspan(2);
                    tablex.addCell(cell);

                    cell = new PdfPCell(new Phrase("Codigo\nAsegurado", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setBorder(Rectangle.NO_BORDER);
                    cell.setColspan(1);
                    tablex.addCell(cell);

                    cell = new PdfPCell(new Phrase(datoPaciente.getNro(), DATO_FONT_B));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setColspan(1);
                    tablex.addCell(cell);

                    cell = new PdfPCell(new Phrase("COMPLEMENTARIOS", TITULO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBorder(Rectangle.NO_BORDER);
                    cell.setColspan(2);
                    tablex.addCell(cell);

                    cell = new PdfPCell(new Phrase("Patronal : " + datoPacEmp.getRegistro() + "   [" + datoPacEmp.getResultado().toLowerCase() + "]", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setBorder(Rectangle.NO_BORDER);
                    cell.setColspan(2);
                    tablex.addCell(cell);

                    cod = format(new Date(), "dd/MM/yyyy HH:mm") + '|' + datoPacEmp.getId_paciente() + '|' + datoHis.getId_historial() + '|' + id_pedido + '|' + datoMed.getId_persona();

                    PdfContentByte cb = writer.getDirectContent();
                    BarcodeQRCode qrcode = new BarcodeQRCode(cod, 90, 90, null);
                    java.awt.Image qrImage = qrcode.createAwtImage(Color.black, Color.white);
                    Image finalImage = Image.getInstance(writer, qrImage, 1);
                    finalImage.setAbsolutePosition(320, 685);  ///x, y
                    finalImage.setBorder(Rectangle.NO_BORDER);
                    //qrImage.scalePercent(400);
                    //cb.addImage(finalImage);
                    document.add(finalImage);

                    document.add(tablex);
///////////////////////
                    Cuadernos datoLab = (Cuadernos) datoLabp.get(0);

                    PdfPTable table2 = new PdfPTable(5);
                    int[] f2widths = {40, 40, 40, 25, 25};
                    table2.setWidths(f2widths);
                    table2.setWidthPercentage(100);

                    cell = new PdfPCell(new Phrase("\n", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setBorder(Rectangle.NO_BORDER);
                    cell.setColspan(5);
                    table2.addCell(cell);

                    cell = new PdfPCell(new Phrase(datoPaciente.getPaterno(), DATO_FONT_B));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBorder(Rectangle.NO_BORDER);
                    table2.addCell(cell);

                    cell = new PdfPCell(new Phrase(datoPaciente.getMaterno(), DATO_FONT_B));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBorder(Rectangle.NO_BORDER);
                    table2.addCell(cell);

                    cell = new PdfPCell(new Phrase(datoPaciente.getNombre(), DATO_FONT_B));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBorder(Rectangle.NO_BORDER);
                    table2.addCell(cell);

                    cell = new PdfPCell(new Phrase(Integer.toString(datoPaciente.getEdad()) + "a" + Integer.toString(datoPaciente.getMes()) + "m" + Integer.toString(datoPaciente.getDia()) + "d", DATO_FONT_B));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBorder(Rectangle.NO_BORDER);
                    table2.addCell(cell);

                    cell = new PdfPCell(new Phrase(datoLab.getId_tipo_sexo() == 1 ? "FEMENINO" : "MASCULINO", DATO_FONT_B));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBorder(Rectangle.NO_BORDER);
                    table2.addCell(cell);

                    cell = new PdfPCell(new Phrase("Apellido Paterno", DATO_FONT_A));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBorder(Rectangle.NO_BORDER);
                    table2.addCell(cell);

                    cell = new PdfPCell(new Phrase("Apellido Materno", DATO_FONT_A));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBorder(Rectangle.NO_BORDER);
                    table2.addCell(cell);

                    cell = new PdfPCell(new Phrase("Nombres", DATO_FONT_A));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBorder(Rectangle.NO_BORDER);
                    table2.addCell(cell);

                    cell = new PdfPCell(new Phrase("Edad", DATO_FONT_A));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBorder(Rectangle.NO_BORDER);
                    table2.addCell(cell);

                    cell = new PdfPCell(new Phrase("Sexo", DATO_FONT_A));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBorder(Rectangle.NO_BORDER);
                    table2.addCell(cell);

                    cell = new PdfPCell(new Phrase("C.I.: " + datoPaciente.getCarnet() + " " + datoPaciente.getExpedido(), DATO_FONT_A1));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBorder(Rectangle.NO_BORDER);
                    table2.addCell(cell);

                    cell = new PdfPCell(new Phrase("", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBorder(Rectangle.NO_BORDER);
                    cell.setColspan(4);
                    table2.addCell(cell);

                    cell = new PdfPCell(new Phrase("", DATO_FONT_B));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBorder(Rectangle.NO_BORDER);
                    table2.addCell(cell);

                    cell = new PdfPCell(new Phrase(dato.getEstablecimiento(), DATO_FONT_B));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBorder(Rectangle.NO_BORDER);
                    cell.setColspan(2);
                    table2.addCell(cell);

                    cell = new PdfPCell(new Phrase(datoMed.getConsultorio(), DATO_FONT_B));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBorder(Rectangle.NO_BORDER);
                    cell.setColspan(2);
                    table2.addCell(cell);

                    cell = new PdfPCell(new Phrase(datoMed.getCodigoprof() + " - " + datoMed.getNombres() + "\nClave Medico Solicitante", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBorder(Rectangle.NO_BORDER);
                    table2.addCell(cell);

                    cell = new PdfPCell(new Phrase("Centro Asistencial", DATO_FONT_A));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBorder(Rectangle.NO_BORDER);
                    cell.setColspan(2);
                    table2.addCell(cell);

                    cell = new PdfPCell(new Phrase("Consultorio de Servicio", DATO_FONT_A));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBorder(Rectangle.NO_BORDER);
                    cell.setColspan(2);
                    table2.addCell(cell);

                    cell = new PdfPCell(new Phrase("", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBorder(Rectangle.NO_BORDER);
                    cell.setColspan(5);
                    table2.addCell(cell);

                    cell = new PdfPCell(new Phrase("Sala", DATO_FONT_B));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBorder(Rectangle.NO_BORDER);
                    table2.addCell(cell);

                    cell = new PdfPCell(new Phrase("Cama", DATO_FONT_B));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBorder(Rectangle.NO_BORDER);
                    table2.addCell(cell);

                    cell = new PdfPCell(new Phrase(format(datoLab.getFechap(), "dd/MM/yyyy HH:mm"), DATO_FONT_B));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBorder(Rectangle.NO_BORDER);
                    table2.addCell(cell);

                    cell = new PdfPCell(new Phrase("Urgente     :   ", DATO_FONT_B));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBorder(Rectangle.NO_BORDER);
                    cell.setColspan(2);
                    table2.addCell(cell);

                    cell = new PdfPCell(new Phrase("SinSala", DATO_FONT_A));
                    if (datointer != null) {
                        cell = new PdfPCell(new Phrase(datointer.getCadena2(), DATO_FONT_A));
                    }
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBorder(Rectangle.NO_BORDER);
                    table2.addCell(cell);

                    cell = new PdfPCell(new Phrase("SinCama", DATO_FONT_A));
                    if (datointer != null) {
                        cell = new PdfPCell(new Phrase(datointer.getCadena1(), DATO_FONT_A));
                    }
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBorder(Rectangle.NO_BORDER);
                    table2.addCell(cell);

                    cell = new PdfPCell(new Phrase("Fecha Solicitud", DATO_FONT_A));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBorder(Rectangle.NO_BORDER);
                    table2.addCell(cell);

                    cell = new PdfPCell(new Phrase("", DATO_FONT_A));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBorder(Rectangle.NO_BORDER);
                    cell.setColspan(2);
                    table2.addCell(cell);

                    cell = new PdfPCell(new Phrase("", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBorder(Rectangle.NO_BORDER);
                    cell.setColspan(5);
                    table2.addCell(cell);

                    document.add(table2);
/////////////////////////   
                    PdfPTable table = new PdfPTable(4);
                    int[] fxxwidths = {30, 20, 30, 20}; // percentage
                    table.setWidths(fxxwidths);
                    table.setWidthPercentage(100);

                    if (datoHis.getDiagnostico() == null || "null".equals(datoHis.getDiagnostico())) {
                        datoHis.setDiagnostico("");
                    } else {
                        datoHis.setDiagnostico(datoHis.getDiagnostico().replaceAll("<p>", "\n"));
                        datoHis.setDiagnostico(datoHis.getDiagnostico().replaceAll("</p>", " "));
                        datoHis.setDiagnostico(datoHis.getDiagnostico().replaceAll("&nbsp;", ""));
                        datoHis.setDiagnostico(datoHis.getDiagnostico().replaceAll("<strong>", " "));
                        datoHis.setDiagnostico(datoHis.getDiagnostico().replaceAll("</strong>", " "));
                        datoHis.setDiagnostico(datoHis.getDiagnostico().replaceAll("<br />", "\n"));
                        datoHis.setDiagnostico(datoHis.getDiagnostico().replaceAll("<u>", " "));
                        datoHis.setDiagnostico(datoHis.getDiagnostico().replaceAll("</u>", " "));
                        datoHis.setDiagnostico(datoHis.getDiagnostico().replaceAll("&ntilde;", "n"));
                        datoHis.setDiagnostico(datoHis.getDiagnostico().replaceAll("&Ntilde;", "N"));
                        datoHis.setDiagnostico(datoHis.getDiagnostico().replaceAll("&Aacute;", "A"));
                        datoHis.setDiagnostico(datoHis.getDiagnostico().replaceAll("&Eacute;", "E"));
                        datoHis.setDiagnostico(datoHis.getDiagnostico().replaceAll("&Iacute;", "I"));
                        datoHis.setDiagnostico(datoHis.getDiagnostico().replaceAll("&Oacute;", "O"));
                        datoHis.setDiagnostico(datoHis.getDiagnostico().replaceAll("&Uacute;", "U"));
                        datoHis.setDiagnostico(datoHis.getDiagnostico().replaceAll("&aacute;", "a"));
                        datoHis.setDiagnostico(datoHis.getDiagnostico().replaceAll("&eacute;", "e"));
                        datoHis.setDiagnostico(datoHis.getDiagnostico().replaceAll("&iacute;", "i"));
                        datoHis.setDiagnostico(datoHis.getDiagnostico().replaceAll("&oacute;", "o"));
                        datoHis.setDiagnostico(datoHis.getDiagnostico().replaceAll("&uacute;", "u"));
                        datoHis.setDiagnostico(datoHis.getDiagnostico().replaceAll("&quot;", "'"));
                    }

                    cell = new PdfPCell(new Phrase(datoHis.getDiagnostico(), DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBorder(Rectangle.NO_BORDER);
                    cell.setColspan(3);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase("Muesta", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBorder(Rectangle.NO_BORDER);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase("Diagnostico Presuntivo", DATO_FONT_A));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBorder(Rectangle.NO_BORDER);
                    cell.setColspan(3);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase("Examinar", DATO_FONT_A));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBorder(Rectangle.NO_BORDER);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase("1) Examen Solicitado                                2) Datos de Orientacion Diagnostica                                                          3) Obtencion de la Muestra   \n  4) Tratamientos Efectuados                                            5) Firma y Aclaracion Medico Solicitante", DATO_FONT_A));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setBorder(Rectangle.NO_BORDER);
                    cell.setColspan(4);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase("", DATO_FONT_A));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setBorder(Rectangle.NO_BORDER);
                    cell.setColspan(4);
                    table.addCell(cell);
//////////////////////////////////

                    PdfPTable table1 = new PdfPTable(2);
                    int[] fmwidths = {70, 30}; // percentage
                    table1.setWidths(fmwidths);
                    table1.setWidthPercentage(100);

                    table1 = new PdfPTable(7);
                    int[] f1widths = {3, 3, 9, 35, 3, 3, 44}; // percentage
                    table1.setWidths(f1widths);
                    table1.setWidthPercentage(100);

                    cell = new PdfPCell(new Phrase("SIRVASE REALIZAR :                                      Area :   " + tipo, DATO_FONT_B));
                    cell.setHorizontalAlignment(Element.ALIGN_TOP);
                    cell.setBorder(Rectangle.NO_BORDER);
                    cell.setColspan(7);
                    cell.setGrayFill(0.9f);
                    table1.addCell(cell);

                    cell = new PdfPCell(new Phrase(lab, DATO_FONT_B));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setBorder(Rectangle.NO_BORDER);
                    cell.setColspan(7);
                    table1.addCell(cell);

                    lab = "";
                    lab = lab + listaLab.getLaboratorio() + "(" + listaLab.getTipoconsulta() + ")        ";
                    tipo = listaLab.getBacterias();
                    sw = listaLab.getId_laboratorio();

                    if (datoLabp.size() == k + 1) {
                        k = k - 1;
                        lab = "";
                    }

                    cell = new PdfPCell(new Phrase("Form. DM 148 - B", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setBorder(Rectangle.NO_BORDER);
                    cell.setColspan(2);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase(datoHis.getId_historial() + "|" + id_pedido + "|" + format(datoLab.getFechap(), "yyMMddHHmm"), DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    cell.setBorder(Rectangle.NO_BORDER);
                    cell.setColspan(2);
                    table.addCell(cell);

                    cell = new PdfPCell(table1);
                    cell.setColspan(4);
                    table.addCell(cell);

                    if (datoMed.getUrgencias() == 1) {
                        ColumnText.showTextAligned(writer.getDirectContentUnder(), Element.ALIGN_RIGHT,
                                new Paragraph("URGENCIAS", marcagua), 280, 680, 30);    ///marca de agua    
                    } else {
                        //if(!"".equals(marcaa)  && marcaa!=null){
                        //   ColumnText.showTextAligned(writer.getDirectContentUnder(),Element.ALIGN_RIGHT, new Paragraph( marcaa, marcagua), 40, 770, 90);    ///marca de agua 
                        //}else{
                        ColumnText.showTextAligned(writer.getDirectContentUnder(), Element.ALIGN_RIGHT, new Paragraph(dato.getConsultorio().toUpperCase(), marcagua), 40, 770, 90);    ///marca de agua
                        //}
                    }

                    document.add(table);
                    document.newPage();
                }

            }//fin de FOR  lista laboratorios  

            PdfPTable tablex = new PdfPTable(4);
            int[] fxwidths = {10, 80, 10, 30}; // percentage
            tablex.setWidths(fxwidths);
            tablex.setWidthPercentage(100);

            cell = new PdfPCell(new Phrase(datoestab.getNombreseg() + "\n" + dato.getEstablecimiento() + "\n\n", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(2);
            tablex.addCell(cell);

            cell = new PdfPCell(new Phrase("No de\nAsegurado", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(1);
            tablex.addCell(cell);

            cell = new PdfPCell(new Phrase(datoPaciente.getNro_registro(), DATO_FONT_B));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(1);
            tablex.addCell(cell);

            cell = new PdfPCell(new Phrase(".SOLICITUD DE EXAMENES", TITULO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(2);
            tablex.addCell(cell);

            cell = new PdfPCell(new Phrase("Codigo\nAsegurado", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(1);
            tablex.addCell(cell);

            cell = new PdfPCell(new Phrase(datoPaciente.getNro(), DATO_FONT_B));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(1);
            tablex.addCell(cell);

            cell = new PdfPCell(new Phrase("COMPLEMENTARIOS", TITULO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(2);
            tablex.addCell(cell);

            cell = new PdfPCell(new Phrase("Patronal : " + datoPacEmp.getRegistro() + "   [" + datoPacEmp.getResultado().toLowerCase() + "]", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(2);
            tablex.addCell(cell);

            cod = format(new Date(), "dd/MM/yyyy HH:mm") + '|' + datoPacEmp.getId_paciente() + '|' + datoHis.getId_historial() + '|' + id_pedido + '|' + datoMed.getId_persona();

            PdfContentByte cb = writer.getDirectContent();
            BarcodeQRCode qrcode = new BarcodeQRCode(cod, 90, 90, null);
            java.awt.Image qrImage = qrcode.createAwtImage(Color.black, Color.white);
            Image finalImage = Image.getInstance(writer, qrImage, 1);
            finalImage.setAbsolutePosition(320, 685);  ///x, y
            finalImage.setBorder(Rectangle.NO_BORDER);
            //qrImage.scalePercent(400);
            //cb.addImage(finalImage);
            document.add(finalImage);

            document.add(tablex);

            Cuadernos datoLab = (Cuadernos) datoLabp.get(0);

            PdfPTable table2 = new PdfPTable(5);
            int[] f2widths = {40, 40, 40, 25, 25};
            table2.setWidths(f2widths);
            table2.setWidthPercentage(100);

            cell = new PdfPCell(new Phrase("\n", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(5);
            table2.addCell(cell);

            cell = new PdfPCell(new Phrase(datoPaciente.getPaterno(), DATO_FONT_B));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell);

            cell = new PdfPCell(new Phrase(datoPaciente.getMaterno(), DATO_FONT_B));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell);

            cell = new PdfPCell(new Phrase(datoPaciente.getNombre(), DATO_FONT_B));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell);

            cell = new PdfPCell(new Phrase(Integer.toString(datoPaciente.getEdad()) + "a" + Integer.toString(datoPaciente.getMes()) + "m" + Integer.toString(datoPaciente.getDia()) + "d", DATO_FONT_B));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell);

            cell = new PdfPCell(new Phrase(datoLab.getId_tipo_sexo() == 1 ? "FEMENINO" : "MASCULINO", DATO_FONT_B));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell);

            cell = new PdfPCell(new Phrase("Apellido Paterno", DATO_FONT_A));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell);

            cell = new PdfPCell(new Phrase("Apellido Materno", DATO_FONT_A));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell);

            cell = new PdfPCell(new Phrase("Nombres", DATO_FONT_A));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell);

            cell = new PdfPCell(new Phrase("Edad", DATO_FONT_A));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell);

            cell = new PdfPCell(new Phrase("Sexo", DATO_FONT_A));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell);

            cell = new PdfPCell(new Phrase("C.I.: " + datoPaciente.getCarnet() + " " + datoPaciente.getExpedido(), DATO_FONT_A1));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell);

            cell = new PdfPCell(new Phrase("", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(4);
            table2.addCell(cell);

            cell = new PdfPCell(new Phrase("", DATO_FONT_B));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell);

            cell = new PdfPCell(new Phrase(dato.getEstablecimiento(), DATO_FONT_B));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(2);
            table2.addCell(cell);

            cell = new PdfPCell(new Phrase(datoMed.getConsultorio(), DATO_FONT_B));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(2);
            table2.addCell(cell);

            cell = new PdfPCell(new Phrase(datoMed.getCodigoprof() + " - " + datoMed.getNombres() + "\nClave Medico Solicitante", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell);

            cell = new PdfPCell(new Phrase("Centro Asistencial", DATO_FONT_A));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(2);
            table2.addCell(cell);

            cell = new PdfPCell(new Phrase("Consultorio de Servicio", DATO_FONT_A));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(2);
            table2.addCell(cell);

            cell = new PdfPCell(new Phrase("", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(5);
            table2.addCell(cell);

            cell = new PdfPCell(new Phrase("Sala", DATO_FONT_B));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell);

            cell = new PdfPCell(new Phrase("Cama", DATO_FONT_B));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell);

            cell = new PdfPCell(new Phrase(format(datoLab.getFechap(), "dd/MM/yyyy HH:mm"), DATO_FONT_B));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell);

            cell = new PdfPCell(new Phrase("Urgente     :   ", DATO_FONT_B));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(2);
            table2.addCell(cell);

            cell = new PdfPCell(new Phrase("SinSala", DATO_FONT_A));
            if (datointer != null) {
                cell = new PdfPCell(new Phrase(datointer.getCadena2(), DATO_FONT_A));
            }
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell);

            cell = new PdfPCell(new Phrase("SinCama", DATO_FONT_A));
            if (datointer != null) {
                cell = new PdfPCell(new Phrase(datointer.getCadena1(), DATO_FONT_A));
            }
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell);

            cell = new PdfPCell(new Phrase("Fecha Solicitud", DATO_FONT_A));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell);

            cell = new PdfPCell(new Phrase("", DATO_FONT_A));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(2);
            table2.addCell(cell);

            cell = new PdfPCell(new Phrase("", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(5);
            table2.addCell(cell);

            document.add(table2);

            PdfPTable table = new PdfPTable(4);
            int[] fxxwidths = {30, 20, 30, 20}; // percentage
            table.setWidths(fxxwidths);
            table.setWidthPercentage(100);

            if (datoHis.getDiagnostico() == null || "null".equals(datoHis.getDiagnostico())) {
                datoHis.setDiagnostico("");
            } else {
                datoHis.setDiagnostico(datoHis.getDiagnostico().replaceAll("<p>", " "));
                datoHis.setDiagnostico(datoHis.getDiagnostico().replaceAll("</p>", " "));
                datoHis.setDiagnostico(datoHis.getDiagnostico().replaceAll("&nbsp;", ""));
                datoHis.setDiagnostico(datoHis.getDiagnostico().replaceAll("<strong>", " "));
                datoHis.setDiagnostico(datoHis.getDiagnostico().replaceAll("</strong>", " "));
                datoHis.setDiagnostico(datoHis.getDiagnostico().replaceAll("<br />", " "));
                datoHis.setDiagnostico(datoHis.getDiagnostico().replaceAll("<u>", " "));
                datoHis.setDiagnostico(datoHis.getDiagnostico().replaceAll("</u>", " "));
                datoHis.setDiagnostico(datoHis.getDiagnostico().replaceAll("&ntilde;", "n"));
                datoHis.setDiagnostico(datoHis.getDiagnostico().replaceAll("&Ntilde;", "N"));
                datoHis.setDiagnostico(datoHis.getDiagnostico().replaceAll("&Aacute;", "A"));
                datoHis.setDiagnostico(datoHis.getDiagnostico().replaceAll("&Eacute;", "E"));
                datoHis.setDiagnostico(datoHis.getDiagnostico().replaceAll("&Iacute;", "I"));
                datoHis.setDiagnostico(datoHis.getDiagnostico().replaceAll("&Oacute;", "O"));
                datoHis.setDiagnostico(datoHis.getDiagnostico().replaceAll("&Uacute;", "U"));
                datoHis.setDiagnostico(datoHis.getDiagnostico().replaceAll("&aacute;", "a"));
                datoHis.setDiagnostico(datoHis.getDiagnostico().replaceAll("&eacute;", "e"));
                datoHis.setDiagnostico(datoHis.getDiagnostico().replaceAll("&iacute;", "i"));
                datoHis.setDiagnostico(datoHis.getDiagnostico().replaceAll("&oacute;", "o"));
                datoHis.setDiagnostico(datoHis.getDiagnostico().replaceAll("&uacute;", "u"));
                datoHis.setDiagnostico(datoHis.getDiagnostico().replaceAll("&quot;", "'"));
            }

            cell = new PdfPCell(new Phrase(datoHis.getDiagnostico(), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(3);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Muesta", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Diagnostico Presuntivo", DATO_FONT_A));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(3);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Examinar", DATO_FONT_A));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("1) Examen Solicitado                                2) Datos de Orientacion Diagnostica                                                          3) Obtencion de la Muestra   \n  4) Tratamientos Efectuados                                            5) Firma y Aclaracion Medico Solicitante", DATO_FONT_A));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(4);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("", DATO_FONT_A));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(4);
            table.addCell(cell);

            PdfPTable table1 = new PdfPTable(2);
            int[] fmwidths = {70, 30}; // percentage
            table1.setWidths(fmwidths);
            table1.setWidthPercentage(100);

            table1 = new PdfPTable(7);
            int[] f1widths = {3, 3, 9, 35, 3, 3, 44}; // percentage
            table1.setWidths(f1widths);
            table1.setWidthPercentage(100);

            cell = new PdfPCell(new Phrase("SIRVASE REALIZAR :                                      Area :   " + tipo, DATO_FONT_B));
            cell.setHorizontalAlignment(Element.ALIGN_TOP);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(7);
            cell.setGrayFill(0.9f);
            table1.addCell(cell);

            cell = new PdfPCell(new Phrase(lab, DATO_FONT_B));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(7);
            table1.addCell(cell);

            cell = new PdfPCell(new Phrase("Form. DM 148 - B", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(2);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(datoHis.getId_historial() + "|" + id_pedido + "|" + format(datoLab.getFechap(), "yyMMddHHmm"), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(2);
            table.addCell(cell);

            cell = new PdfPCell(table1);
            cell.setColspan(4);
            table.addCell(cell);

            if (datoMed.getUrgencias() == 1) {
                ColumnText.showTextAligned(writer.getDirectContentUnder(), Element.ALIGN_RIGHT,
                        new Paragraph("URGENCIAS", marcagua), 280, 680, 30);    ///marca de agua    
            } else {
                //if(!"".equals(marcaa)  && marcaa!=null){
                //   ColumnText.showTextAligned(writer.getDirectContentUnder(),Element.ALIGN_RIGHT, new Paragraph( marcaa, marcagua), 40, 770, 90);    ///marca de agua 
                //}else{
                ColumnText.showTextAligned(writer.getDirectContentUnder(), Element.ALIGN_RIGHT, new Paragraph(dato.getConsultorio().toUpperCase(), marcagua), 40, 770, 90);    ///marca de agua
                //}
            }

            document.add(table);

        }////fin de else vacio    
    }

    public static String format(Date dia, String formato) {
        if (dia == null) {
            return "";
        }
        SimpleDateFormat formatDate = new SimpleDateFormat(formato);
        return formatDate.format(dia);
    }
}
