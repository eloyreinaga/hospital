package org.ayaic.web.administrarcuadernos;

import java.awt.Color;
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
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.Personas;

public class PedirLaboratorioPrivPDF extends AbstractPdfView {

    private static final Font TITULO_FONT = new Font(Font.HELVETICA, 12, Font.BOLD, Color.black);
    private static final Font CABEZA_COLUMNA_FONT = new Font(Font.HELVETICA, 8, Font.ITALIC, Color.black);
    private static final Font DATO_FONT = new Font(Font.HELVETICA, 8, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_B = new Font(Font.HELVETICA, 13, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_A = new Font(Font.HELVETICA, 8, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_BOLD = new Font(Font.HELVETICA, 8, Font.BOLD, Color.black);
    private static final int MARGIN = 32;

    protected void buildPdfMetadata(Map model, Document document, HttpServletRequest request) {
        document.setPageSize(PageSize.LETTER);
        document.setMargins(60, 20, 100, 20);
    }

    protected void buildPdfDocument(Map model, Document document, PdfWriter writer, HttpServletRequest req, HttpServletResponse resp) throws Exception {

        java.util.List datoLabp = (java.util.List) model.get("datosLab");
        java.util.List listaPres = (java.util.List) model.get("listarPres");
        java.util.List seguros = (java.util.List) model.get("listaPacAfi");

        Historiales datoHis = (Historiales) model.get("datosHis");
        Personas datoMed = (Personas) model.get("datosMed");
        Pacientes datoPac = (Pacientes) model.get("datosPac");
        Clientes dato = (Clientes) model.get("dato");
        Pacientes datoPaciente = (Pacientes) model.get("datos");
        String mes = (String) model.get("mes");
        String gestion = (String) model.get("gestion");
        DecimalFormat df = new DecimalFormat("###,##0");
        DecimalFormat dfx = new DecimalFormat("###,##0.00");

        // coloca el titulo de la pagina
        //Image escudo = Image.getInstance("/opt/imagenes/lescudo.bmp");
        //Paragraph  p = new Paragraph("SOLICITUD DE EXAMENES DE LABORATORIO, IMAGENOLOGIA / GABINETE\n Y SERVICIO DE SANGRE SEGURA\n", new Font(Font.HELVETICA, 12, Font.BOLDITALIC, new Color(0, 0, 0)) );
        //p.setAlignment(Element.ALIGN_CENTER);
        Image sumi = Image.getInstance("/opt/imagenes/imglab1.bmp");
        PdfPCell cell;

        if (datoLabp.isEmpty()) {
            Paragraph pf = new Paragraph("No existe Datos", new Font(Font.HELVETICA, 18, Font.BOLDITALIC, new Color(0, 0, 0)));
            document.add(pf);
        } else {
            /*
      PdfPTable tablex = new PdfPTable(3);
      int[] fxwidths = {10,80,10}; // percentage
      tablex.setWidths(fxwidths);
      tablex.setWidthPercentage(100);
        
      cell = new PdfPCell(escudo);
      cell.setHorizontalAlignment(Element.ALIGN_LEFT);        
      cell.setBorder(Rectangle.NO_BORDER);
      tablex.addCell(cell);
      
      cell = new PdfPCell(p);
      cell.setHorizontalAlignment(Element.ALIGN_CENTER);
      cell.setBorder(Rectangle.NO_BORDER);
      tablex.addCell(cell);
      
      cell = new PdfPCell(sumi);
      cell.setHorizontalAlignment(Element.ALIGN_RIGHT);        
      cell.setBorder(Rectangle.NO_BORDER);
      tablex.addCell(cell);
      
      document.add(tablex); 
             */

            PdfPTable table1 = new PdfPTable(2);
            int[] fmwidths = {70, 30}; // percentage
            table1.setWidths(fmwidths);
            table1.setWidthPercentage(100);

            Cuadernos datoLab = (Cuadernos) datoLabp.get(0);
            cell = new PdfPCell(new Phrase("FECHA DE SOLICITUD   :    " + format(datoLab.getFechap(), "dd/MM/yyyy"), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell);

            cell = new PdfPCell(new Phrase("", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell);

            document.add(table1);

            PdfPTable table2 = new PdfPTable(3);
            int[] f2widths = {60, 15, 25}; // percentage
            table2.setWidths(f2widths);
            table2.setWidthPercentage(100);

            cell = new PdfPCell(new Phrase("NOMBRE PACIENTE       :    " + datoPaciente.getNombres(), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell);

            cell = new PdfPCell(new Phrase("", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell);

            cell = new PdfPCell(new Phrase("", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell);

            document.add(table2);

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

            cell = new PdfPCell(new Phrase("DIAGNOSTICO CLINICO PRINCIPAL :    " + datoHis.getDiagnostico(), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(4);
            table.addCell(cell);

            //AGREGAMOS PRESTACIONES
            table1 = new PdfPTable(7);
            int[] f1widths = {3, 3, 9, 35, 3, 3, 44}; // percentage
            table1.setWidths(f1widths);
            table1.setWidthPercentage(100);

            cell = new PdfPCell(new Phrase("SIRVASE REALIZAR:", DATO_FONT_A));
            cell.setHorizontalAlignment(Element.ALIGN_TOP);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(4);
            cell.setGrayFill(0.9f);
            table1.addCell(cell);

            cell = new PdfPCell(new Phrase(" ", DATO_FONT_B));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(3);
            table1.addCell(cell);

            // laboratorioss dados
            for (int i = 0; i < datoLabp.size(); i++) {

                Cuadernos datoLab1 = (Cuadernos) datoLabp.get(i);

                cell = new PdfPCell(new Phrase(" ", DATO_FONT_B));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBorder(Rectangle.NO_BORDER);
                table1.addCell(cell);

                cell = new PdfPCell(new Phrase("X", DATO_FONT_B));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table1.addCell(cell);

                cell = new PdfPCell(new Phrase(datoLab1.getLaboratorio() + "(" + datoLab1.getTipoconsulta() + ")", DATO_FONT_B));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setColspan(2);
                table1.addCell(cell);

                cell = new PdfPCell(new Phrase(" ", DATO_FONT_B));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setColspan(3);
                table1.addCell(cell);

            }

            //       cell = new PdfPCell(new Phrase("\nIMAGENOLOGIA.- SIRVASE REALIZAR:", DATO_FONT));
            cell = new PdfPCell(new Phrase("\n", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(4);
            table1.addCell(cell);

            cell = new PdfPCell(table1);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(4);
            table.addCell(cell);

            document.add(table);

        }
    }

    public static String format(Date dia, String formato) {
        if (dia == null) {
            return "";
        }
        SimpleDateFormat formatDate = new SimpleDateFormat(formato);
        return formatDate.format(dia);
    }
}
