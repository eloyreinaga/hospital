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

public class VerReporteCIE10Spam4PDF extends AbstractPdfView {

    private static final Font TITULO_FONT = new Font(Font.HELVETICA, 12, Font.BOLD, Color.black);
    private static final Font CABEZA_COLUMNA_FONT = new Font(Font.HELVETICA, 8, Font.ITALIC, Color.black);
    private static final Font DATO_FONT = new Font(Font.HELVETICA, 8, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_BOLD = new Font(Font.HELVETICA, 8, Font.BOLD, Color.black);
    private static final int MARGIN = 32;

    protected void buildPdfMetadata(Map model, Document document, HttpServletRequest request) {
        document.setPageSize(PageSize.LETTER);
    }

    protected void buildPdfDocument(Map model, Document document, PdfWriter writer, HttpServletRequest req, HttpServletResponse resp) throws Exception {

        java.util.List lFopos = (java.util.List) model.get("listaCobros");
        java.util.List cie = (java.util.List) model.get("listaCie");
        HeaderFooter footer = new HeaderFooter(new Phrase("Pagina No. "), new Phrase("."));

        footer.setAlignment(Element.ALIGN_CENTER);
        footer.setBorder(Rectangle.NO_BORDER);
        document.setFooter(footer);
        String fec1 = (String) model.get("fecha1");
        String fec2 = (String) model.get("fecha2");

        Clientes dato = (Clientes) model.get("dato");
        // coloca el titulo de la pagina
        Image escudo = Image.getInstance("/opt/imagenes/lescudo.bmp");
        Paragraph p = new Paragraph("MINISTERIO DE SALUD Y DEPORTES\nSEGURO DE SALUD\nPARA EL ADULTO MAYOR", new Font(Font.HELVETICA, 14, Font.BOLDITALIC, new Color(0, 0, 0)));
        p.setAlignment(Element.ALIGN_CENTER);
        //Image sumi = Image.getInstance("/opt/imagenes/sumi.bmp");

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
        cell = new PdfPCell(new Phrase("", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex.addCell(cell);

        document.add(tablex);

        PdfPTable table1 = new PdfPTable(2);
        int[] fmwidths = {50, 50}; // percentage
        table1.setWidths(fmwidths);
        table1.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("Formulario de Atenciones Enfermeria", TITULO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(2);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("Establecimiento : " + dato.getEstablecimiento(), DATO_FONT));
        cell.setBorder(Rectangle.NO_BORDER);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("Municipio : " + dato.getLocalidad(), DATO_FONT));
        cell.setBorder(Rectangle.NO_BORDER);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("Red : " + dato.getRed(), DATO_FONT));
        cell.setBorder(Rectangle.NO_BORDER);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("SEDES : " + dato.getDepartamento() + "      Localidad:....................", DATO_FONT));
        cell.setBorder(Rectangle.NO_BORDER);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("Mes : " + fec2.substring(5, 7) + "         " + "Año : " + fec2.substring(0, 4), DATO_FONT));
        cell.setBorder(Rectangle.NO_BORDER);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("Desde fecha :  " + fec1 + "  a  :  " + fec2, DATO_FONT));
        cell.setBorder(Rectangle.NO_BORDER);
        table1.addCell(cell);

        document.add(table1);

        int NumColumns = 6;
        PdfPTable table = new PdfPTable(NumColumns);
        int[] fwidths = {4, 7, 30, 5, 25, 25};
        table.setWidths(fwidths);
        table.setWidthPercentage(100);

        String sCampos[] = {"No", "FECHA", "NOMBRES Y APELLIDOS", "EDAD", "SERVICIO", "ACCON"};
        // coloca la cabecera de titulos
        for (int i = 0; i < NumColumns; i++) {
            cell = new PdfPCell(new Phrase(sCampos[i], CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setGrayFill(0.8f);
            table.addCell(cell);
        }
        int num = lFopos.size();
        // coloca el detalle de loS datos
        for (int i = 0; i < lFopos.size(); i++) {

            Cuadernos datopac = (Cuadernos) lFopos.get(i);

            cell = new PdfPCell(new Phrase(Integer.toString(i + 1), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(format(datopac.getFechap(), "dd/MM/yy"), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(datopac.getNombres(), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(Integer.toString(datopac.getEdad()), DATO_FONT));
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
                datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("<ul>", " "));
                datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("</ul>", " "));
                datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("<ol>", " "));
                datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("</ol>", " "));
                datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("<li>", " "));
                datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("</li>", " "));
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

            cell = new PdfPCell(new Phrase(datopac.getDiagnostico(), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Inyectable", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

        }
        cell = new PdfPCell(new Phrase("TOTAL ", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setColspan(5);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(Integer.toString(num), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        document.add(table);

        p = new Paragraph("Fecha de emision : " + format(new Date(), "dd/MM/yyyy"), DATO_FONT);
        document.add(p);
        p = new Paragraph("Nombre completo responsable del llenado : " + dato.getNombres() + "         Firma:_______________________", DATO_FONT);
        document.add(p);

        p = new Paragraph("El arriba firmante certifica la veracidad de la informacion declarada en este documento legal.", DATO_FONT);
        document.add(p);

    }

    public static String format(Date dia, String formato) {
        if (dia == null) {
            return "";
        }
        SimpleDateFormat formatDate = new SimpleDateFormat(formato);
        return formatDate.format(dia);
    }
}
