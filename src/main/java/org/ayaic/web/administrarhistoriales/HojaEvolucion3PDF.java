package org.ayaic.web.administrarhistoriales;

import java.awt.Color;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.view.document.AbstractPdfView;
import java.util.*;
import java.text.*;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Historiales;
import org.ayaic.domain.Localidades;
import org.ayaic.domain.Pacientes;

public class HojaEvolucion3PDF extends AbstractPdfView {

    private static final Font TITULO_FONT = new Font(Font.HELVETICA, 13, Font.BOLD, Color.black);
    private static final Font TITULO_FONT_B = new Font(Font.HELVETICA, 16, Font.BOLD, Color.black);
    private static final Font CABEZA_COLUMNA_FONT = new Font(Font.HELVETICA, 8, Font.ITALIC, Color.black);
    private static final Font DATO_FONT = new Font(Font.HELVETICA, 11, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_A = new Font(Font.COURIER, 9, Font.NORMAL, Color.black);
    private static final int MARGIN = 32;

    protected void buildPdfMetadata(Map model, Document document, HttpServletRequest request) {
        document.setPageSize(PageSize.LETTER);
        document.setMargins(40, 20, 500, 30);
    }

    protected void buildPdfDocument(Map model, Document document, PdfWriter writer, HttpServletRequest req, HttpServletResponse resp) throws Exception {

        java.util.List HCL = (java.util.List) model.get("milistaInter");
        Pacientes pacientes = (Pacientes) model.get("datosp");
        java.util.List med = (java.util.List) model.get("listarRecetaTotal");
        Localidades datoestab = (Localidades) model.get("datoestab");
        Clientes dato = (Clientes) model.get("dato");
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

            PdfPTable table = new PdfPTable(NumColumns);
            int[] fwidths = {16, 70, 18};
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
                    cell = new PdfPCell(new Phrase("FECHA\n" + format(datopac.getFecha(), "dd/MM/yyyy") + "\nHora : " + format(datopac.getFecha(), "HH:mm") + "\nEdad: " + datopac.getEdad() + "a" + datopac.getMes() + "m" + datopac.getDia() + "d " + cadena, DATO_FONT_A));
                } else {
                    cell = new PdfPCell(new Phrase("FECHA\n" + format(datopac.getFecha(), "dd/MM/yyyy") + "\nHora : " + format(datopac.getFecha(), "HH:mm") + "\nEdad: " + datopac.getEdad() + " años" + cadena, DATO_FONT_A));
                }
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
                table.addCell(cell);

                if (datopac.getDiagnostico() == null || "null".equals(datopac.getDiagnostico())) {
                    datopac.setDiagnostico("");
                } else {
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("<p>", "\n"));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("</p>", "\n "));
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
