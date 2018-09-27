package org.ayaic.web.administrarhistoriales.ambulatorio;

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

public class ListarPacientes19PDF extends AbstractPdfView {

    private static final Font TITULO_FONT = new Font(Font.HELVETICA, 12, Font.BOLD, Color.black);
    private static final Font CABEZA_COLUMNA_FONT = new Font(Font.HELVETICA, 8, Font.ITALIC, Color.black);
    private static final Font DATO_FONT = new Font(Font.HELVETICA, 8, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_BOLD = new Font(Font.HELVETICA, 8, Font.BOLD, Color.black);
    private static final int MARGIN = 32;

    protected void buildPdfMetadata(Map model, Document document, HttpServletRequest request) {
        document.setPageSize(PageSize.LEGAL.rotate());
        document.setMargins(70, 20, 20, 20);
    }

    protected void buildPdfDocument(Map model, Document document, PdfWriter writer, HttpServletRequest req, HttpServletResponse resp) throws Exception {

        java.util.List lFopos = (java.util.List) model.get("milista19");
        Clientes dato = (Clientes) model.get("dato");

        Image escudo = Image.getInstance("/opt/imagenes/lescudo.bmp");
        Paragraph p = new Paragraph("MOVIMIENTO ECONOMICO\n", new Font(Font.HELVETICA, 14, Font.BOLDITALIC, new Color(0, 0, 0)));
        p.setAlignment(Element.ALIGN_CENTER);

        PdfPCell cell;

        PdfPTable tablex = new PdfPTable(3);
        int[] fxwidths = {15, 70, 15}; // percentage
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

        document.add(tablex);

        int[] sumas = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int NumColumns = 12;
        int una, filas = 30;

        if (lFopos.size() == 0) {
            Paragraph p1 = new Paragraph("No existe Datos", new Font(Font.HELVETICA, 22, Font.BOLDITALIC, new Color(0, 0, 0)));
            document.add(p1);
        }

        if (lFopos.size() % filas == 0) {
            una = 0;
        } else {
            una = 1;
        }

        for (int pag = 0; pag < lFopos.size() / filas + una; pag++) {
            Paragraph p1;
            //        PdfPCell cell;    

            PdfPTable table = new PdfPTable(NumColumns);
            int[] fwidths = {6, 10, 10, 5, 5, 5, 5, 40, 40, 8, 8, 8};
            table.setWidths(fwidths);
            table.setWidthPercentage(100);

            cell = new PdfPCell(new Phrase("REGISTRO ADOLECENTES", TITULO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(NumColumns);
            table.addCell(cell);

            String sCampos[] = {"No", "FECHA", "Numero de\nCarnet de\nAsegurado", "Edad", "M", "F", "CONSULTA", "N", "R", "DIAGNOSTICO", "ORIENTACION", "", ""};
            // coloca la cabecera de titulos
            for (int i = 0; i < 3; i++) {
                cell = new PdfPCell(new Phrase(sCampos[i], CABEZA_COLUMNA_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
            }
            PdfPTable table1 = new PdfPTable(2);
            int[] fmwidths = {50, 50}; // percentage
            table1.setWidths(fmwidths);
            table1.setWidthPercentage(100);

            cell = new PdfPCell(new Phrase("EDAD", CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(2);
            table1.addCell(cell);

            cell = new PdfPCell(new Phrase("M", CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table1.addCell(cell);

            cell = new PdfPCell(new Phrase("F", CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table1.addCell(cell);

            cell = new PdfPCell(table1);
            cell.setColspan(2);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            PdfPTable table12 = new PdfPTable(2);
            int[] fmwidths2 = {50, 50}; // percentage
            table12.setWidths(fmwidths2);
            table12.setWidthPercentage(100);

            cell = new PdfPCell(new Phrase("CONSULTA", CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(2);
            table12.addCell(cell);

            cell = new PdfPCell(new Phrase("N", CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table12.addCell(cell);

            cell = new PdfPCell(new Phrase("R", CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table12.addCell(cell);

            cell = new PdfPCell(table12);
            cell.setColspan(2);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("DIAGNOSTICO", CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("OBSERVACION", CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("MAC", CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("REFERENCIA", CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("OBSERVACION", CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            // coloca el detalle de loS datos
            for (int i = pag * filas + 0; i < pag * filas + filas && i < lFopos.size(); i++) {

                Historiales datopac = (Historiales) lFopos.get(i);

                cell = new PdfPCell(new Phrase(Integer.toString(i + 1), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(format(datopac.getFecha(), "dd/MM/yyyy"), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(datopac.getRegistro(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((datopac.getId_sexo() == 2) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((datopac.getId_sexo() == 1) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(("N".equals(datopac.getRepetida())) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(("R".equals(datopac.getRepetida())) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

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
                cell = new PdfPCell(new Phrase((datopac.getDiagnostico()), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

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

                cell = new PdfPCell(new Phrase((datopac.getAccion()), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(("R".equals(datopac.getRepetida())) ? "" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(("R".equals(datopac.getRepetida())) ? "" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(("R".equals(datopac.getRepetida())) ? "" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

            }
            for (int i = lFopos.size(); i < pag * filas + filas; i++) {
                cell = new PdfPCell(new Phrase(Integer.toString(i + 1), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);
                for (int j = 1; j < NumColumns; j++) {
                    cell = new PdfPCell(new Phrase("", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);
                }
            }

            cell = new PdfPCell(new Phrase("", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            document.add(table);
            document.newPage();
        }

    }

    public static void addtitulofila3(PdfPTable table, String cadena, String a, String b, String c) {
        PdfPCell cell;
        int NumColumns = 3;
        PdfPTable table11 = new PdfPTable(NumColumns);
        table11.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase(cadena, CABEZA_COLUMNA_FONT));
        cell.setColspan(3);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase(a, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase(b, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase(c, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        cell = new PdfPCell(table11);
        cell.setColspan(3);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
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
