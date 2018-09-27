package org.ayaic.web.administrarhistoriales;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.view.document.AbstractPdfView;
import com.itextpdf.text.pdf.BarcodeQRCode;
import java.awt.Color;
import java.util.*;
import java.text.*;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Cuadernos;
import org.ayaic.domain.Historiales;
import org.ayaic.domain.Localidades;
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.Personas;
import org.ayaic.domain.Recetas;

public class HojaEvolucion2PDF extends AbstractPdfView {

    private static final Font TITULO_FONT = new Font(Font.HELVETICA, 13, Font.BOLD, Color.black);
    private static final Font TITULO_FONT_B = new Font(Font.HELVETICA, 16, Font.BOLD, Color.black);
    private static final Font CABEZA_COLUMNA_FONT = new Font(Font.HELVETICA, 8, Font.ITALIC, Color.black);
    private static final Font DATO_FONT = new Font(Font.HELVETICA, 11, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_A = new Font(Font.COURIER, 9, Font.NORMAL, Color.black);
    private static final int MARGIN = 32;

    protected void buildPdfMetadata(Map model, Document document, HttpServletRequest request) {
        String fila = (String) model.get("id_fila");
        document.setPageSize(PageSize.LETTER);
        document.setMargins(40, 20, Integer.parseInt(fila), 20);
    }

    protected void buildPdfDocument(Map model, Document document, PdfWriter writer, HttpServletRequest req, HttpServletResponse resp) throws Exception {

        Clientes dato = (Clientes) model.get("dato");
        Localidades datoestab = (Localidades) model.get("datoestab");
        Pacientes pacientes = (Pacientes) model.get("datosp");
        Personas datomed = (Personas) model.get("persona");
        Personas datores = (Personas) model.get("personares");
        java.util.List HCL = (java.util.List) model.get("milistaInter");
        java.util.List med = (java.util.List) model.get("listarReceta");
        java.util.List ListaLab = (java.util.List) model.get("listalab");
        String cod = (String) model.get("cod");
        DecimalFormat df = new DecimalFormat("###,##0");
        String cadena = "";

        Image escudo = Image.getInstance("/opt/imagenes/lescudo.bmp");
        Paragraph pp = new Paragraph("MOVIMIENTO ECONOMICO\n", new Font(Font.HELVETICA, 14, Font.BOLDITALIC, new Color(0, 0, 0)));
        pp.setAlignment(Element.ALIGN_CENTER);

        PdfPCell cell;

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

            PdfPTable tablex = new PdfPTable(3);
            int[] fxwidths = {30, 55, 10}; // percentage
            tablex.setWidths(fxwidths);
            tablex.setWidthPercentage(100);

            cell = new PdfPCell(new Phrase(datoestab.getNombreseg() + "\n" + datoestab.getEstablecimiento() + "\n", DATO_FONT_A));
            //cell = new PdfPCell(new Phrase("", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setGrayFill(0.9f);
            tablex.addCell(cell);

            cell = new PdfPCell(new Phrase("NOTA DE EVOLUCION PACIENTE", TITULO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setGrayFill(0.9f);
            tablex.addCell(cell);

            cell = new PdfPCell(new Phrase("", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setGrayFill(0.9f);
            tablex.addCell(cell);

            document.add(tablex);

            PdfPTable table = new PdfPTable(NumColumns);
            int[] fwidths = {20, 70, 18};
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
                if (!"0".equals(datopac.getFc()) && datopac.getFc() != null) {
                    cadena = cadena + "\nF.C.: " + datopac.getFc() + " lpm";
                }
                if (!"0".equals(datopac.getPa()) && datopac.getPa() != null) {
                    cadena = cadena + "\nPA: " + datopac.getPa() + " mmHg";
                }
                if (!"0".equals(datopac.getFr()) && datopac.getFr() != null) {
                    cadena = cadena + "\nF.R.: " + datopac.getFr() + " cpm";
                }

                cod = Integer.toString(dato.getCod_esta()) + '|' + Integer.toString(datopac.getHcl()) + '|' + datopac.getId_paciente() + '|' + datopac.getId_historial() + '|' + datopac.getId_historia() + '|' + datopac.getId_persona() + '|' + format(datopac.getFecha(), "dd/MM/yyyy HH:mm");

                PdfContentByte cb = writer.getDirectContent();
                BarcodeQRCode qrcode = new BarcodeQRCode(cod, 82, 82, null);///90 mas tamaño
                java.awt.Image qrImage = qrcode.createAwtImage(Color.black, Color.white);
                Image finalImage = Image.getInstance(writer, qrImage, 1);

                PdfPTable tableq = new PdfPTable(1);
                int[] fwidthsq = {100};
                tableq.setWidths(fwidthsq);
                tableq.setWidthPercentage(100);

                if (datopac.getEdad() < 5) {
                    cell = new PdfPCell(new Phrase("FECHA\n" + format(datopac.getFecha(), "dd/MM/yyyy") + "\nHora : " + format(datopac.getFecha(), "HH:mm") + "\nEdad: " + datopac.getEdad() + "a" + datopac.getMes() + "m" + datopac.getDia() + "d " + cadena, DATO_FONT_A));
                } else {
                    cell = new PdfPCell(new Phrase("FECHA\n" + format(datopac.getFecha(), "dd/MM/yyyy") + "\nHora : " + format(datopac.getFecha(), "HH:mm") + "\nEdad: " + datopac.getEdad() + " años" + cadena, DATO_FONT_A));
                }
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
                tableq.addCell(cell);

                if (datopac.getDiagnostico().length() > 200) {
                    cell = new PdfPCell(finalImage);
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setVerticalAlignment(Element.ALIGN_TOP);
                    cell.setBorder(Rectangle.NO_BORDER);
                    tableq.addCell(cell);
                }

                cell = new PdfPCell(tableq);
                cell.setBorder(Rectangle.NO_BORDER);
                table.addCell(cell);

                if (datopac.getDiagnostico() == null || "null".equals(datopac.getDiagnostico())) {
                    datopac.setDiagnostico("");
                } else {
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("<p>", "\n "));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("</p>", " "));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("<div>", "\n"));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("</div>", " "));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("<br />", "\n"));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("&nbsp;", ""));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("<strong>", " "));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("</strong>", " "));
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
                }

                int NumCol = 3;
                PdfPTable table12 = new PdfPTable(NumCol);
                table12.setWidthPercentage(100);

                cell = new PdfPCell(new Phrase(datopac.getDiagnostico(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setColspan(3);
                table12.addCell(cell);

                ///////////////recetas
                PdfPTable tablex1 = new PdfPTable(1);
                int[] fxwidths1 = {40}; // percentage
                tablex1.setWidths(fxwidths1);
                tablex1.setWidthPercentage(100);

                for (int j = 0; j < med.size(); j++) {
                    Recetas medica = (Recetas) med.get(j);
                    if (datopac.getId_historial() == medica.getId_historial()) {
                        cell = new PdfPCell(new Phrase("*" + df.format(medica.getSalida()) + "," + " " + ((medica.getMedicamento().length() > 20) ? medica.getMedicamento().substring(0, 20) : medica.getMedicamento()) + "<" + medica.getForma_far() + ">" + medica.getConcentra() + "[" + medica.getIndicacion() + "]", new Font(Font.COURIER, 8, Font.NORMAL, Color.black)));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setBorder(Rectangle.NO_BORDER);
                        cell.setColspan(2);
                        tablex1.addCell(cell);
                    }
                }
                cell = new PdfPCell(tablex1);
                cell.setVerticalAlignment(Element.ALIGN_TOP);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setColspan(2);
                table12.addCell(cell);

                ///////////laboratorios
                PdfPTable tablex12 = new PdfPTable(1);
                int[] fxwidths12 = {60}; // percentage
                tablex1.setWidths(fxwidths12);
                tablex12.setWidthPercentage(100);

                String labos = "";
                for (int j = 0; j < ListaLab.size(); j++) {
                    Cuadernos datoLab1 = (Cuadernos) ListaLab.get(j);
                    labos = datoLab1.getLaboratorio() + "  ;  " + labos;
                }

                cell = new PdfPCell(new Phrase(labos, new Font(Font.COURIER, 6, Font.NORMAL, Color.black)));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
                tablex12.addCell(cell);

                cell = new PdfPCell(tablex12);
                cell.setVerticalAlignment(Element.ALIGN_TOP);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setColspan(1);
                table12.addCell(cell);

                PdfPTable xtable3 = new PdfPTable(3);
                int[] xfwidths3 = {30, 30, 30}; // percentage
                xtable3.setWidths(xfwidths3);
                xtable3.setWidthPercentage(100);

                cell = new PdfPCell(new Phrase("Pac.: " + pacientes.getNombres(), new Font(Font.COURIER, 7, Font.NORMAL, Color.black)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                xtable3.addCell(cell);

                if (datomed.getId_persona() != datores.getId_persona()) {
                    cell = new PdfPCell(new Phrase(datores.getNombres() + "\nElaborado por Resid./Inter.\n" + datopac.getCod_esta() + "." + datopac.getId_historial() + "." + datopac.getId_historia(), new Font(Font.COURIER, 7, Font.NORMAL, Color.black)));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                    cell.setBorder(Rectangle.NO_BORDER);
                    xtable3.addCell(cell);
                } else {
                    cell = new PdfPCell(new Phrase("\n", new Font(Font.COURIER, 7, Font.NORMAL, Color.black)));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                    cell.setBorder(Rectangle.NO_BORDER);
                    xtable3.addCell(cell);
                }

                cell = new PdfPCell(new Phrase(datomed.getNombres() + "\n" + datomed.getCodigoprof() + "\n" + datomed.getConsultorio(), new Font(Font.COURIER, 7, Font.NORMAL, Color.black)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                xtable3.addCell(cell);

                cell = new PdfPCell(xtable3);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setColspan(3);
                table12.addCell(cell);

                cell = new PdfPCell(table12);
                cell.setBorder(Rectangle.NO_BORDER);
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
