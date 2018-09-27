package org.ayaic.web.administrarhistoriales;

import java.awt.Color;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.view.document.AbstractPdfView;
import java.util.*;
import java.text.*;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Historiales;
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.Personas;
import org.ayaic.domain.Recetas;

public class ListarRecetaMedPriv2PDF extends AbstractPdfView {

    private static final Font TITULO_FONT = new Font(Font.HELVETICA, 12, Font.BOLD, Color.black);
    private static final Font CABEZA_COLUMNA_FONT = new Font(Font.HELVETICA, 8, Font.ITALIC, Color.black);
    private static final Font DATO_FONT = new Font(Font.HELVETICA, 8, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_B = new Font(Font.HELVETICA, 8, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_A = new Font(Font.COURIER, 12, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_BOLD = new Font(Font.HELVETICA, 8, Font.BOLD, Color.black);
    private static final int MARGIN = 32;

    protected void buildPdfMetadata(Map model, Document document, HttpServletRequest request) {
        document.setPageSize(PageSize.LETTER);
        document.setMargins(50, 30, 140, 10);
    }

    protected void buildPdfDocument(Map model, Document document, PdfWriter writer, HttpServletRequest req, HttpServletResponse resp) throws Exception {

        java.util.List listaRecetas = (java.util.List) model.get("listarRecetas");
        java.util.List listaPres = (java.util.List) model.get("listarPres");
        java.util.List listaSeg = (java.util.List) model.get("listarSeguros");

        Historiales datoHis = (Historiales) model.get("datosHis");
        Personas datoMed = (Personas) model.get("datosMed");
        Pacientes datoPac = (Pacientes) model.get("datosPac");
        Clientes dato = (Clientes) model.get("dato");
        DecimalFormat df = new DecimalFormat("###,##0");

        // coloca el titulo de la pagina
        //Image escudo = Image.getInstance("/opt/imagenes/lescudo.bmp");
        //Paragraph  p = new Paragraph("RECETARIO DEL PACIENTE", new Font(Font.HELVETICA, 14, Font.BOLDITALIC, new Color(0, 0, 0)) );
        //p.setAlignment(Element.ALIGN_RIGHT);
        //Image sumi = Image.getInstance("/opt/imagenes/hoja1.bmp");
        PdfPCell cell;

        //AGREGAMOS TITULO
        /*  
      PdfPTable tablex = new PdfPTable(3);
      int[] fxwidths = {15,70,15}; // percentage
      tablex.setWidths(fxwidths);
      tablex.setWidthPercentage(100);
        
      cell = new PdfPCell(escudo);
      cell.setHorizontalAlignment(Element.ALIGN_LEFT);        
      cell.setBorder(Rectangle.NO_BORDER);
      tablex.addCell(cell);
      
      cell = new PdfPCell(p);
      cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
      cell.setBorder(Rectangle.NO_BORDER);
      cell.setColspan(2);
      tablex.addCell(cell);
      
      document.add(tablex); 
         */
        PdfPTable table1 = new PdfPTable(3);
        int[] fmwidths = {50, 40, 30}; // percentage
        table1.setWidths(fmwidths);
        table1.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("FECHA ATENCION : " + format(new Date(), "dd/MM/yyyy"), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(3);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("N. Paciente: " + datoPac.getNombres() + "       " + datoHis.getEdad() + "a" + datoHis.getMes() + "m" + datoHis.getDia() + "d\n", DATO_FONT_A));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(3);
        table1.addCell(cell);

        document.add(table1);

        PdfPTable table = new PdfPTable(7);
        int[] fxxwidths = {3, 36, 38, 6, 6, 6, 6}; // percentage
        table.setWidths(fxxwidths);
        table.setWidthPercentage(100);

        //AGREGAMOS DIAGNOSTICO
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
            datoHis.setDiagnostico(datoHis.getDiagnostico().replaceAll("<ol>", " "));
            datoHis.setDiagnostico(datoHis.getDiagnostico().replaceAll("</ol>", " "));
            datoHis.setDiagnostico(datoHis.getDiagnostico().replaceAll("<ul>", " "));
            datoHis.setDiagnostico(datoHis.getDiagnostico().replaceAll("</ul>", " "));
            datoHis.setDiagnostico(datoHis.getDiagnostico().replaceAll("<li>", " "));
            datoHis.setDiagnostico(datoHis.getDiagnostico().replaceAll("</li>", " "));
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
        cell = new PdfPCell(new Phrase("DIAGNOSTICO  : " + datoHis.getDiagnostico() + "\n\n", DATO_FONT_A));
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(7);
        table.addCell(cell);

        // coloca el detalle de loS datos
        for (int i = 0; i < listaRecetas.size(); i++) {

            Recetas datoReceta = (Recetas) listaRecetas.get(i);

            cell = new PdfPCell(new Phrase(Integer.toString(i + 1), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);

            String[] cadena = datoReceta.getIndicacion().split("!");

            cell = new PdfPCell(new Phrase(cadena[0] + "\n Dosif.:  " + df.format(datoReceta.getSalida()) + " Unid.\n        " + cadena[1], DATO_FONT_A));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(7);
            table.addCell(cell);

        }

        document.add(table);

    }

    public static void addfila2(PdfPTable table, String a, String b, String c) {
        PdfPCell cell;
        PdfPTable table1 = new PdfPTable(2);

        cell = new PdfPCell(new Phrase(a, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setColspan(2);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase(b, DATO_FONT_B));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase(c, DATO_FONT_B));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1.addCell(cell);

        cell = new PdfPCell(table1);
        cell.setColspan(2);
        table.addCell(cell);
    }

    public static String format(Date dia, String formato) {
        if (dia == null) {
            return "";
        }
        SimpleDateFormat formatDate = new SimpleDateFormat(formato);
        return formatDate.format(dia);
    }
}
