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
import org.ayaic.domain.Prestaciones;
import org.ayaic.domain.Recetas;

public class ListarRecetaMedPrivPDF extends AbstractPdfView {

    private static final Font TITULO_FONT = new Font(Font.HELVETICA, 12, Font.BOLD, Color.black);
    private static final Font CABEZA_COLUMNA_FONT = new Font(Font.HELVETICA, 8, Font.ITALIC, Color.black);
    private static final Font DATO_FONT = new Font(Font.HELVETICA, 8, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_B = new Font(Font.HELVETICA, 8, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_A = new Font(Font.COURIER, 10, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_BOLD = new Font(Font.HELVETICA, 8, Font.BOLD, Color.black);
    private static final int MARGIN = 32;

    protected void buildPdfMetadata(Map model, Document document, HttpServletRequest request) {
        document.setPageSize(PageSize.LETTER);
        document.setMargins(20, 30, 10, 5);
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
        Image escudo = Image.getInstance("/opt/imagenes/lescudo.bmp");
        Paragraph p = new Paragraph("RECETARIO DEL PACIENTE", new Font(Font.HELVETICA, 14, Font.BOLDITALIC, new Color(0, 0, 0)));
        p.setAlignment(Element.ALIGN_RIGHT);
        //Image sumi = Image.getInstance("/opt/imagenes/hoja1.bmp");

        PdfPCell cell;

        //AGREGAMOS TITULO
        PdfPTable tablex = new PdfPTable(3);
        int[] fxwidths = {15, 70, 15}; // percentage
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

        PdfPTable table1 = new PdfPTable(3);
        int[] fmwidths = {50, 40, 10}; // percentage
        table1.setWidths(fmwidths);
        table1.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("Establecimiento : " + dato.getEstablecimiento(), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("FECHA ATENCION : ", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setBorder(Rectangle.NO_BORDER);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase(format(new Date(), "dd/MM/yyyy") + "  " + format(new Date(), "HH:mm"), DATO_FONT_A));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("Nombre del Paciente: " + datoPac.getNombres(), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("Edad:" + datoHis.getEdad() + "a" + datoHis.getMes() + "m" + datoHis.getDia() + "d" + "  Peso: " + datoHis.getPeso() + " [Kg]  Talla: " + datoHis.getTalla() + " [Cm]  " + "T :" + datoHis.getTemperatura() + "[oC]", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setColspan(2);
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
        cell = new PdfPCell(new Phrase("DIAGNOSTICO  : CIE[" + datoHis.getCodigo() + "];  " + datoHis.getDiagnostico(), DATO_FONT_A));
        cell.setColspan(7);
        table.addCell(cell);
        //AGREGAMOS PRESTACIONES
        table1 = new PdfPTable(1);
        table1.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("PRESTACIONES :", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        table1.addCell(cell);
        // prestaciones dadas
        for (int i = 0; i < listaPres.size(); i++) {
            Prestaciones datoPres = (Prestaciones) listaPres.get(i);
            cell = new PdfPCell(new Phrase("          " + datoPres.getPrestacion() + "    " + datoPres.getDescripcion() + "    [" + datoPres.getCantidad() + "]", DATO_FONT_A));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell);
        }

        cell = new PdfPCell(table1);
        cell.setColspan(7);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("No", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("MEDICAMENTOS E INSUMOS\n(Nombre generico, Forma Farmaceutica y Concentra)", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("INDICACIONES PARA EL PACIENTE\n(Cantidad, Frecuencia, Tiempo de uso y Via de Administra)", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        addfila2(table, "CANTIDAD", "Receta", "Dispens");
        addfila2(table, "", "", "");

        // coloca el detalle de loS datos
        for (int i = 0; i < listaRecetas.size(); i++) {

            Recetas datoReceta = (Recetas) listaRecetas.get(i);

            cell = new PdfPCell(new Phrase(Integer.toString(i + 1), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);

            String[] cadena = datoReceta.getIndicacion().split("!");

            cell = new PdfPCell(new Phrase(cadena[0], DATO_FONT_A));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(cadena[1], DATO_FONT_A));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(df.format(datoReceta.getSalida()), DATO_FONT_A));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("", DATO_FONT_A));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("", DATO_FONT_A));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("", DATO_FONT_A));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);
        }

        PdfPTable xtable = new PdfPTable(5);
        int[] xfwidths = {24, 24, 24, 21, 7}; // percentage
        xtable.setWidths(xfwidths);
        xtable.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("", DATO_FONT));
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(4);
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        xtable.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT_A));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        xtable.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT));
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(4);
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        xtable.addCell(cell);

        if ("S".equals(datoHis.getExpedido()) || "P".equals(datoHis.getExpedido())) {
            cell = new PdfPCell(new Phrase("0", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            xtable.addCell(cell);
        } else {
            cell = new PdfPCell(new Phrase("", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            xtable.addCell(cell);
        }

        cell = new PdfPCell(new Phrase(datoMed.getNombres() + "\nR.M.: " + datoMed.getDip() + "\nSello y firma", DATO_FONT));
        cell.setFixedHeight(40f);
        cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        xtable.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT));
        cell.setFixedHeight(40f);
        cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        xtable.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT));
        cell.setFixedHeight(40f);
        cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        xtable.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT));
        cell.setFixedHeight(40f);
        cell.setColspan(2);
        cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        xtable.addCell(cell);

        cell = new PdfPCell(new Phrase("Favor no cambiar la receta", DATO_FONT));
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(5);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        xtable.addCell(cell);

        cell = new PdfPCell(xtable);
        cell.setColspan(7);
        table.addCell(cell);

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
