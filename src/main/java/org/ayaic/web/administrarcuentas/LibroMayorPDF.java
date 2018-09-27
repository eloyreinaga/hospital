package org.ayaic.web.administrarcuentas;

import java.awt.Color;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.view.document.AbstractPdfView;
import java.util.*;
import java.text.*;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Cuentas;

public class LibroMayorPDF extends AbstractPdfView {

    private static final Font TITULO_FONT = new Font(Font.HELVETICA, 12, Font.BOLD, Color.black);
    private static final Font CABEZA_COLUMNA_FONT = new Font(Font.HELVETICA, 8, Font.ITALIC, Color.black);
    private static final Font DATO_FONT = new Font(Font.HELVETICA, 8, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_BOLD = new Font(Font.HELVETICA, 8, Font.BOLD, Color.black);
    private static final int MARGIN = 32;

    protected void buildPdfMetadata(Map model, Document document, HttpServletRequest request) {
        document.setPageSize(PageSize.LEGAL);
    }

    protected void buildPdfDocument(Map model, Document document, PdfWriter writer, HttpServletRequest req, HttpServletResponse resp) throws Exception {

        java.util.List lFopos = (java.util.List) model.get("listaLibroMayor");

        Clientes dato = (Clientes) model.get("datos");
        // coloca el titulo de la pagina
        Image escudo = Image.getInstance("/opt/imagenes/lescudo.bmp");
        Paragraph p = new Paragraph("MOVIMIENTO ECONOMICO\n(FARMACIA)", new Font(Font.HELVETICA, 14, Font.BOLDITALIC, new Color(0, 0, 0)));
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
        cell = new PdfPCell(new Phrase("", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex.addCell(cell);

        document.add(tablex);

        PdfPTable table1 = new PdfPTable(2);
        int[] fmwidths = {50, 50}; // percentage
        table1.setWidths(fmwidths);
        table1.setWidthPercentage(100);

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

        cell = new PdfPCell(new Phrase("Mes : " + format(new Date(), "MM") + "         " + "Año : " + format(new Date(), "yyyy"), DATO_FONT));
        cell.setBorder(Rectangle.NO_BORDER);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("subsector : ...........  ", DATO_FONT));
        cell.setBorder(Rectangle.NO_BORDER);
        table1.addCell(cell);

        document.add(table1);

        int NumColumns = 5;
        PdfPTable table = new PdfPTable(NumColumns);
        int[] fwidths = {4, 10, 40, 10, 10};
        table.setWidths(fwidths);
        table.setWidthPercentage(100);

        String sCampos[] = {"No", "FECHA", "GLOSA", "DEBE", "HABER"};
        // coloca la cabecera de titulos
        for (int i = 0; i < NumColumns; i++) {
            cell = new PdfPCell(new Phrase(sCampos[i], CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setGrayFill(0.8f);
            table.addCell(cell);
        }

        // coloca el detalle de loS datos
        table.setHeaderRows(1);
        for (int i = 0; i < lFopos.size(); i++) {

            Cuentas datopac = (Cuentas) lFopos.get(i);

            cell = new PdfPCell(new Phrase(Integer.toString(i + 1), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(format(datopac.getFec_registro(), "dd/MM/yyyy"), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(datopac.getTransaccion(), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(Double.toString(datopac.getDebe()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(Double.toString(datopac.getHaber()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
        }

        document.add(table);

        p = new Paragraph("Fecha de emision : " + format(new Date(), "dd/MM/yyyy"), DATO_FONT);
        document.add(p);
        p = new Paragraph("Nombre completo responsable del llenado : " + dato.getNombres() + "         Firma:_______________________", DATO_FONT);
        document.add(p);
        p = new Paragraph("El arriba firmante certifica la veracidad de la informacion declarada en este formulario legal.", DATO_FONT);
        document.add(p);
        p = new Paragraph("Nota: - Llenar con letra de imprenta.", DATO_FONT);
        document.add(p);
        p = new Paragraph("      - Este documento debe tener el sello del establecimiento.                                   Sello", DATO_FONT);
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
