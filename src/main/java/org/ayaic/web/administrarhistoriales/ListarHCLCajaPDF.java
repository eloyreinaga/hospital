package org.ayaic.web.administrarhistoriales;

import java.awt.Color;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.view.document.AbstractPdfView;
import java.util.*;
import java.text.*;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import com.itextpdf.text.pdf.BarcodeQRCode;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Historiales;
import org.ayaic.domain.Localidades;
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.Recetas;

public class ListarHCLCajaPDF extends AbstractPdfView {

    private static final Font TITULO_FONT = new Font(Font.HELVETICA, 12, Font.BOLD, Color.black);
    private static final Font TITULO_FONT_B = new Font(Font.HELVETICA, 16, Font.BOLD, Color.black);
    private static final Font CABEZA_COLUMNA_FONT = new Font(Font.HELVETICA, 8, Font.ITALIC, Color.black);
    private static final Font DATO_FONT = new Font(Font.HELVETICA, 8, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_BOLD = new Font(Font.COURIER, 8, Font.BOLD, Color.black);
    private static final int MARGIN = 32;

    protected void buildPdfMetadata(Map model, Document document, HttpServletRequest request) {
        document.setPageSize(PageSize.LETTER);
        document.setMargins(10, 10, 10, 10);
    }

    protected void buildPdfDocument(Map model, Document document, PdfWriter writer, HttpServletRequest req, HttpServletResponse resp) throws Exception {

        java.util.List HCL = (java.util.List) model.get("milistas");
        Pacientes pacientes = (Pacientes) model.get("datosp");
        java.util.List med = (java.util.List) model.get("listarRecetaTotal");
        Localidades datoestab = (Localidades) model.get("datoestab");
        Clientes dato = (Clientes) model.get("dato");
        DecimalFormat df = new DecimalFormat("###,##0");
        String cod = (String) model.get("cod");
        String cadena = "";
        Image escudo = Image.getInstance("/opt/imagenes/lescudo.bmp");
        Paragraph pp = new Paragraph("MOVIMIENTO ECONOMICO\n", new Font(Font.HELVETICA, 14, Font.BOLDITALIC, new Color(0, 0, 0)));
        pp.setAlignment(Element.ALIGN_CENTER);

        PdfPCell cell;

        PdfPTable tablex = new PdfPTable(3);
        int[] fxwidths = {50, 25, 25}; // percentage
        tablex.setWidths(fxwidths);
        tablex.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase(datoestab.getNombreseg(), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex.addCell(cell);

        cell = new PdfPCell(new Phrase("\n", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(2);
        tablex.addCell(cell);

        cell = new PdfPCell(new Phrase("\n", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex.addCell(cell);

        cell = new PdfPCell(new Phrase("No de asegurado:", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex.addCell(cell);

        cell = new PdfPCell(new Phrase(pacientes.getNro_registro(), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tablex.addCell(cell);

        cell = new PdfPCell(new Phrase("EVOLUCION Y \nTRATAMIENTOO", TITULO_FONT_B));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex.addCell(cell);

        cell = new PdfPCell(new Phrase("Codigo de Beneficiario:", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex.addCell(cell);

        cell = new PdfPCell(new Phrase(pacientes.getNro_registro(), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tablex.addCell(cell);

        cell = new PdfPCell(new Phrase("Nombres y Apellidos : " + pacientes.getNombres(), TITULO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(3);
        tablex.addCell(cell);

        if (pacientes.getId_tipo_sexo() == 1) {
            cell = new PdfPCell(new Phrase("Edad   :  " + Integer.toString(pacientes.getEdad()) + "            Sexo  : FEMENINO", TITULO_FONT));
        } else {
            cell = new PdfPCell(new Phrase("Edad   :  " + Integer.toString(pacientes.getEdad()) + "            Sexo  : MASCULINO", TITULO_FONT));
        }

        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(3);
        tablex.addCell(cell);

        document.add(tablex);

        int[] sumas = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int NumColumns = 3;
        int una, filas = 30;

        if (HCL.size() == 0) {
            Paragraph p1 = new Paragraph("No existe Datos", new Font(Font.HELVETICA, 22, Font.BOLDITALIC, new Color(0, 0, 0)));
            document.add(p1);
        }

        if (HCL.size() % filas == 0) {
            una = 0;
        } else {
            una = 1;
        }

        for (int pag = 0; pag < HCL.size() / filas + una; pag++) {
            Paragraph p1;
            //        PdfPCell cell;    

            PdfPTable table = new PdfPTable(NumColumns);
            int[] fwidths = {12, 70, 18};
            table.setWidths(fwidths);
            table.setWidthPercentage(100);

            // coloca el detalle de loS datos
            for (int i = pag * filas + 0; i < pag * filas + filas && i < HCL.size(); i++) {

                Historiales datopac = (Historiales) HCL.get(i);

                if (datopac.getTalla() != 0) {
                    cadena = "\nTalla: " + datopac.getTalla() + " Cm.";
                }
                if (datopac.getPeso() != 0) {
                    cadena = cadena + "\nPeso: " + datopac.getPeso() + " Kg.";
                }
                if (datopac.getTemperatura() != 0) {
                    cadena = cadena + "\nTemp.: " + datopac.getTemperatura() + "oC";
                }
                if (!"0".equals(datopac.getFc())) {
                    cadena = cadena + "\nF.C.: " + datopac.getFc() + " lpm";
                }
                if (!"0".equals(datopac.getPa())) {
                    cadena = cadena + "\nPA: " + datopac.getPa() + " mmHg";
                }
                if (!"0".equals(datopac.getFr())) {
                    cadena = cadena + "\nF.R.: " + datopac.getFr() + " cpm";
                }

                if (datopac.getEdad() < 5) {
                    cell = new PdfPCell(new Phrase("FECHA\n" + format(datopac.getFecha(), "dd/MM/yyyy") + "\nHora : " + format(datopac.getFecha(), "HH:mm") + "\nEdad: " + datopac.getEdad() + "a" + datopac.getMes() + "m" + datopac.getDia() + "d " + cadena, DATO_FONT));
                } else {
                    cell = new PdfPCell(new Phrase("FECHA\n" + format(datopac.getFecha(), "dd/MM/yyyy") + "\nHora : " + format(datopac.getFecha(), "HH:mm") + "\nEdad: " + datopac.getEdad() + " años" + cadena, DATO_FONT));
                }
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                if (datopac.getSubjetivo() == null || "null".equals(datopac.getSubjetivo())) {
                    datopac.setSubjetivo("");
                } else {
                    datopac.setSubjetivo(datopac.getSubjetivo().replaceAll("<p>", "\n"));
                    datopac.setSubjetivo(datopac.getSubjetivo().replaceAll("</p>", " "));
                    datopac.setSubjetivo(datopac.getSubjetivo().replaceAll("&nbsp;", ""));
                    datopac.setSubjetivo(datopac.getSubjetivo().replaceAll("<strong>", " "));
                    datopac.setSubjetivo(datopac.getSubjetivo().replaceAll("</strong>", " "));
                    datopac.setSubjetivo(datopac.getSubjetivo().replaceAll("<br />", "\n"));
                    datopac.setSubjetivo(datopac.getSubjetivo().replaceAll("<u>", " "));
                    datopac.setSubjetivo(datopac.getSubjetivo().replaceAll("</u>", " "));
                    datopac.setSubjetivo(datopac.getSubjetivo().replaceAll("<ul>", " "));
                    datopac.setSubjetivo(datopac.getSubjetivo().replaceAll("</ul>", " "));
                    datopac.setSubjetivo(datopac.getSubjetivo().replaceAll("<ol>", " "));
                    datopac.setSubjetivo(datopac.getSubjetivo().replaceAll("</ol>", " "));
                    datopac.setSubjetivo(datopac.getSubjetivo().replaceAll("<li>", " "));
                    datopac.setSubjetivo(datopac.getSubjetivo().replaceAll("</li>", " "));
                    datopac.setSubjetivo(datopac.getSubjetivo().replaceAll("&ordf;", " "));
                    datopac.setSubjetivo(datopac.getSubjetivo().replaceAll("&ntilde;", "n"));
                    datopac.setSubjetivo(datopac.getSubjetivo().replaceAll("&Ntilde;", "N"));
                    datopac.setSubjetivo(datopac.getSubjetivo().replaceAll("&Aacute;", "A"));
                    datopac.setSubjetivo(datopac.getSubjetivo().replaceAll("&Eacute;", "E"));
                    datopac.setSubjetivo(datopac.getSubjetivo().replaceAll("&Iacute;", "I"));
                    datopac.setSubjetivo(datopac.getSubjetivo().replaceAll("&Oacute;", "O"));
                    datopac.setSubjetivo(datopac.getSubjetivo().replaceAll("&Uacute;", "U"));
                    datopac.setSubjetivo(datopac.getSubjetivo().replaceAll("&aacute;", "a"));
                    datopac.setSubjetivo(datopac.getSubjetivo().replaceAll("&eacute;", "e"));
                    datopac.setSubjetivo(datopac.getSubjetivo().replaceAll("&iacute;", "i"));
                    datopac.setSubjetivo(datopac.getSubjetivo().replaceAll("&oacute;", "o"));
                    datopac.setSubjetivo(datopac.getSubjetivo().replaceAll("&uacute;", "u"));
                    datopac.setSubjetivo(datopac.getSubjetivo().replaceAll("&quot;", "'"));
                    datopac.setSubjetivo(datopac.getSubjetivo().replaceAll("&ordm;", "o"));
                }
                if (datopac.getObjetivo() == null || "null".equals(datopac.getObjetivo())) {
                    datopac.setObjetivo("");
                } else {
                    datopac.setObjetivo(datopac.getObjetivo().replaceAll("<p>", " "));
                    datopac.setObjetivo(datopac.getObjetivo().replaceAll("</p>", " "));
                    datopac.setObjetivo(datopac.getObjetivo().replaceAll("&nbsp;", ""));
                    datopac.setObjetivo(datopac.getObjetivo().replaceAll("<strong>", " "));
                    datopac.setObjetivo(datopac.getObjetivo().replaceAll("</strong>", " "));
                    datopac.setObjetivo(datopac.getObjetivo().replaceAll("<br />", " "));
                    datopac.setObjetivo(datopac.getObjetivo().replaceAll("<u>", " "));
                    datopac.setObjetivo(datopac.getObjetivo().replaceAll("</u>", " "));
                    datopac.setObjetivo(datopac.getObjetivo().replaceAll("<ul>", " "));
                    datopac.setObjetivo(datopac.getObjetivo().replaceAll("</ul>", " "));
                    datopac.setObjetivo(datopac.getObjetivo().replaceAll("<ol>", " "));
                    datopac.setObjetivo(datopac.getObjetivo().replaceAll("</ol>", " "));
                    datopac.setObjetivo(datopac.getObjetivo().replaceAll("<li>", " "));
                    datopac.setObjetivo(datopac.getObjetivo().replaceAll("</li>", " "));
                    datopac.setObjetivo(datopac.getObjetivo().replaceAll("&ordf;", " "));
                    datopac.setObjetivo(datopac.getObjetivo().replaceAll("&ntilde;", "n"));
                    datopac.setObjetivo(datopac.getObjetivo().replaceAll("&Ntilde;", "N"));
                    datopac.setObjetivo(datopac.getObjetivo().replaceAll("&Aacute;", "A"));
                    datopac.setObjetivo(datopac.getObjetivo().replaceAll("&Eacute;", "E"));
                    datopac.setObjetivo(datopac.getObjetivo().replaceAll("&Iacute;", "I"));
                    datopac.setObjetivo(datopac.getObjetivo().replaceAll("&Oacute;", "O"));
                    datopac.setObjetivo(datopac.getObjetivo().replaceAll("&Uacute;", "U"));
                    datopac.setObjetivo(datopac.getObjetivo().replaceAll("&aacute;", "a"));
                    datopac.setObjetivo(datopac.getObjetivo().replaceAll("&eacute;", "e"));
                    datopac.setObjetivo(datopac.getObjetivo().replaceAll("&iacute;", "i"));
                    datopac.setObjetivo(datopac.getObjetivo().replaceAll("&oacute;", "o"));
                    datopac.setObjetivo(datopac.getObjetivo().replaceAll("&uacute;", "u"));
                    datopac.setObjetivo(datopac.getObjetivo().replaceAll("&quot;", "'"));
                    datopac.setSubjetivo(datopac.getSubjetivo().replaceAll("&ordm;", "o"));
                }
                if (datopac.getDiagnostico() == null || "null".equals(datopac.getDiagnostico())) {
                    datopac.setDiagnostico("");
                } else {
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("<p>", " "));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("</p>", " "));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("&nbsp;", ""));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("<strong>", " "));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("</strong>", " "));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("<br />", " "));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("<u>", " "));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("</u>", " "));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("<ul>", " "));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("</ul>", " "));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("<ol>", " "));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("</ol>", " "));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("<li>", " "));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("</li>", " "));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("&ordf;", " "));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("&ntilde;", "n"));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("&Ntilde;", "N"));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("&Aacute;", "A"));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("&Eacute;", "E"));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("&Iacute;", "I"));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("&Oacute;", "O"));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("&Uacute;", "U"));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("&aacute;", "a"));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("&eacute;", "e"));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("&iacute;", "i"));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("&oacute;", "o"));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("&uacute;", "u"));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("&quot;", "'"));
                    datopac.setSubjetivo(datopac.getSubjetivo().replaceAll("&ordm;", "o"));
                }
                if (datopac.getAccion() == null || "null".equals(datopac.getAccion())) {
                    datopac.setAccion("");
                } else {
                    datopac.setAccion(datopac.getAccion().replaceAll("<p>", " "));
                    datopac.setAccion(datopac.getAccion().replaceAll("</p>", " "));
                    datopac.setAccion(datopac.getAccion().replaceAll("&nbsp;", ""));
                    datopac.setAccion(datopac.getAccion().replaceAll("<strong>", " "));
                    datopac.setAccion(datopac.getAccion().replaceAll("</strong>", " "));
                    datopac.setAccion(datopac.getAccion().replaceAll("<br />", " "));
                    datopac.setAccion(datopac.getAccion().replaceAll("<u>", " "));
                    datopac.setAccion(datopac.getAccion().replaceAll("</u>", " "));
                    datopac.setAccion(datopac.getAccion().replaceAll("<ul>", " "));
                    datopac.setAccion(datopac.getAccion().replaceAll("</ul>", " "));
                    datopac.setAccion(datopac.getAccion().replaceAll("<ol>", " "));
                    datopac.setAccion(datopac.getAccion().replaceAll("</ol>", " "));
                    datopac.setAccion(datopac.getAccion().replaceAll("<li>", " "));
                    datopac.setAccion(datopac.getAccion().replaceAll("</li>", " "));
                    datopac.setAccion(datopac.getAccion().replaceAll("&ordf;", " "));
                    datopac.setAccion(datopac.getAccion().replaceAll("&ntilde;", "n"));
                    datopac.setAccion(datopac.getAccion().replaceAll("&Ntilde;", "N"));
                    datopac.setAccion(datopac.getAccion().replaceAll("&Uacute;", "U"));
                    datopac.setAccion(datopac.getAccion().replaceAll("&Oacute;", "O"));
                    datopac.setAccion(datopac.getAccion().replaceAll("&Iacute;", "I"));
                    datopac.setAccion(datopac.getAccion().replaceAll("&Aacute;", "A"));
                    datopac.setAccion(datopac.getAccion().replaceAll("&Eacute;", "E"));
                    datopac.setAccion(datopac.getAccion().replaceAll("&uacute;", "u"));
                    datopac.setAccion(datopac.getAccion().replaceAll("&oacute;", "o"));
                    datopac.setAccion(datopac.getAccion().replaceAll("&iacute;", "i"));
                    datopac.setAccion(datopac.getAccion().replaceAll("&aacute;", "a"));
                    datopac.setAccion(datopac.getAccion().replaceAll("&eacute;", "e"));
                    datopac.setAccion(datopac.getAccion().replaceAll("&quot;", "'"));
                }

                String s = datopac.getExpedido();
                Image sumia = Image.getInstance(datopac.getEstimc());

                int NumCol = 3;
                PdfPTable table12 = new PdfPTable(NumCol);
                table12.setWidthPercentage(100);

                cell = new PdfPCell(new Phrase("SUBJETIVO :\n                              " + datopac.getSubjetivo() + "\nOBJETIVO :\n                              " + datopac.getObjetivo() + "\nDIAGNOSTICO :\n                               " + "CIE10: [" + datopac.getCodigo() + "]  " + datopac.getDiagnostico() + "\nPLAN DE ACCION :\n                               " + datopac.getAccion(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setColspan(3);
                table12.addCell(cell);

                for (int j = 0; j < med.size(); j++) {
                    Recetas medica = (Recetas) med.get(j);

                    if (datopac.getId_historial() == medica.getId_historial()) {
                        cell = new PdfPCell(new Phrase("*" + df.format(medica.getSalida()) + "," + " " + ((medica.getMedicamento().length() > 20) ? medica.getMedicamento().substring(0, 20) : medica.getMedicamento()) + "<" + medica.getForma_far() + ">" + medica.getConcentra() + "[" + medica.getIndicacion() + "]", new Font(Font.COURIER, 8, Font.NORMAL, Color.black)));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setBorder(Rectangle.NO_BORDER);
                        cell.setColspan(2);
                        table12.addCell(cell);

                        cell = new PdfPCell(new Phrase("", DATO_FONT_BOLD));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setVerticalAlignment(Element.ALIGN_TOP);
                        cell.setBorder(Rectangle.NO_BORDER);
                        table12.addCell(cell);
                    }
                }

                cod = Integer.toString(datopac.getHcl()) + '|' + datopac.getNombres() + '|' + datopac.getId_paciente() + '|' + datopac.getId_historial() + '|' + format(datopac.getFecha(), "dd/MM/yyyy HH:mm");

                PdfContentByte cb = writer.getDirectContent();
                BarcodeQRCode qrcode = new BarcodeQRCode(cod, 82, 82, null);///90 mas tamaño
                java.awt.Image qrImage = qrcode.createAwtImage(Color.black, Color.white);
                Image finalImage = Image.getInstance(writer, qrImage, 1);

                PdfPTable xtable = new PdfPTable(3);
                int[] xfwidths = {20, 40, 40}; // percentage
                xtable.setWidths(xfwidths);
                xtable.setWidthPercentage(100);

                PdfPTable xtable1 = new PdfPTable(1);
                int[] xfwidths1 = {100}; // percentage
                xtable1.setWidths(xfwidths1);
                xtable1.setWidthPercentage(100);

                cell = new PdfPCell(finalImage);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                xtable1.addCell(cell);

                cell = new PdfPCell(xtable1);
                cell.setBorder(Rectangle.NO_BORDER);
                xtable.addCell(cell);

                PdfPTable xtable2 = new PdfPTable(1);
                int[] xfwidths2 = {100}; // percentage
                xtable2.setWidths(xfwidths2);
                xtable2.setWidthPercentage(100);

                cell = new PdfPCell(new Phrase("Paciente:   " + datopac.getNombre() + "\nHcl : " + datopac.getHcl(), new Font(Font.COURIER, 7, Font.NORMAL, Color.black)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                //cell.setFixedHeight(20f);   
                xtable2.addCell(cell);

                cell = new PdfPCell(xtable2);
                cell.setBorder(Rectangle.NO_BORDER);
                xtable.addCell(cell);

                PdfPTable xtable3 = new PdfPTable(1);
                int[] xfwidths3 = {100}; // percentage
                xtable3.setWidths(xfwidths3);
                xtable3.setWidthPercentage(100);

                cell = new PdfPCell(sumia);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.NO_BORDER);
                xtable3.addCell(cell);

                cell = new PdfPCell(new Phrase("Atendido por:\n" + datopac.getNombres(), new Font(Font.COURIER, 7, Font.NORMAL, Color.black)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                //cell.setFixedHeight(20f);   
                xtable3.addCell(cell);

                cell = new PdfPCell(xtable3);
                cell.setBorder(Rectangle.NO_BORDER);
                xtable.addCell(cell);

                cell = new PdfPCell(xtable);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setColspan(3);
                table12.addCell(cell);

                cell = new PdfPCell(table12);
                cell.setColspan(2);
                table.addCell(cell);

            }

            document.add(table);

            document.newPage();
            document.setPageCount(1);
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
