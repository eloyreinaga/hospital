package org.ayaic.web.reporteprestacion;

import java.awt.Color;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.view.document.AbstractPdfView;
import java.util.*;
import java.text.*;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import java.io.IOException;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Prestaciones;

public class ListarConsultaPDF extends AbstractPdfView {

    private static final Font TITULO_FONT = new Font(Font.HELVETICA, 12, Font.BOLD, Color.black);
    private static final Font CABEZA_COLUMNA_FONT = new Font(Font.HELVETICA, 8, Font.ITALIC, Color.black);
    private static final Font DATO_FONT = new Font(Font.HELVETICA, 8, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_BOLD = new Font(Font.HELVETICA, 8, Font.BOLD, Color.black);
    private static final int MARGIN = 32;

    protected void buildPdfMetadata(Map model, Document document, HttpServletRequest request) {
        document.setPageSize(PageSize.LETTER);
    }

    protected void buildPdfDocument(Map model, Document document, PdfWriter writer, HttpServletRequest req, HttpServletResponse resp) throws BadElementException, IOException, DocumentException {

        java.util.List lFopos = (java.util.List) model.get("listaFopos");
        Clientes dato = (Clientes) model.get("dato");
        String mes = (String) model.get("mes");
        String gestion = (String) model.get("gestion");
        DecimalFormat df = new DecimalFormat("#,##0");
        float sumatotal = 0;
        int sumacant = 0;

        int una;
        if (lFopos.size() % 40 == 0) {
            una = 0;
        } else {
            una = 1;
        }

        for (int pag = 0; pag < lFopos.size() / 40 + una; pag++) {
            // coloca el titulo de la pagina
            Image escudo = Image.getInstance("/opt/imagenes/lescudo.bmp");
            Paragraph p = new Paragraph("Reporte de Prestaciones Establecidas\nde Seguros Publicos de Salud - REPES", new Font(Font.HELVETICA, 14, Font.BOLDITALIC, new Color(0, 0, 0)));
            p.setAlignment(Element.ALIGN_CENTER);

            PdfPCell cell;
            PdfPTable tablex = new PdfPTable(2);
            int[] fxwidths = {20, 80}; // percentage
            tablex.setWidths(fxwidths);
            tablex.setWidthPercentage(100);

            cell = new PdfPCell(escudo);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            tablex.addCell(cell);

            PdfPTable tablet1 = new PdfPTable(1);
            int[] ft1widths = {100}; // percentage
            tablet1.setWidths(ft1widths);
            tablet1.setWidthPercentage(100);

            cell = new PdfPCell(new Phrase("D-15", new Font(Font.HELVETICA, 10, Font.BOLDITALIC, new Color(0, 0, 0))));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setBorder(Rectangle.NO_BORDER);
            tablet1.addCell(cell);

            cell = new PdfPCell(p);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            tablet1.addCell(cell);

            cell = new PdfPCell(tablet1);
            cell.setBorder(Rectangle.NO_BORDER);
            tablex.addCell(cell);

            PdfPTable table1 = new PdfPTable(2);
            int[] fmwidths = {50, 50}; // percentage
            table1.setWidths(fmwidths);
            table1.setWidthPercentage(100);

            cell = new PdfPCell(new Phrase("SEDES : " + dato.getDepartamento(), DATO_FONT));
            cell.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell);

            cell = new PdfPCell(new Phrase("Municipio : " + dato.getLocalidad(), DATO_FONT));
            cell.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell);

            cell = new PdfPCell(new Phrase("Red : " + dato.getRed(), DATO_FONT));
            cell.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell);

            cell = new PdfPCell(new Phrase("Establecimiento : " + dato.getEstablecimiento(), DATO_FONT));
            cell.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell);

            cell = new PdfPCell(new Phrase("Responsable : " + dato.getNombres(), DATO_FONT));
            cell.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell);

            cell = new PdfPCell(new Phrase("Mes : " + mes + "         " + "Año : " + gestion, DATO_FONT));
            cell.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell);

            int NumColumns = 6;
            PdfPTable table = new PdfPTable(NumColumns);
            int[] fwidths = {4, 10, 56, 10, 10, 10};
            table.setWidths(fwidths);
            table.setWidthPercentage(100);

            String sCampos[] = {"No", "CODIGO (Prestaciones)", "CODIGO Y NOMBRE DE PAQUETE", "No. de Prestaciones", "Precio Unitario (Paquete)", "Monto Total (Bs.)"};

            cell = new PdfPCell(tablex);
            cell.setColspan(6);
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);

            cell = new PdfPCell(table1);
            cell.setColspan(6);
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);

            // coloca la cabecera de titulos
            for (int i = 0; i < NumColumns; i++) {
                cell = new PdfPCell(new Phrase(sCampos[i], CABEZA_COLUMNA_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                //cell.setGrayFill(0.8f);
                table.addCell(cell);
            }

            // coloca el detalle de loS datos
            table.setHeaderRows(1);
            table.setHeaderRows(2);
            table.setHeaderRows(3);
            for (int i = pag * 40 + 0; i < pag * 40 + 40 && i < lFopos.size(); i++) {

                Prestaciones datopac = (Prestaciones) lFopos.get(i);

                cell = new PdfPCell(new Phrase(Integer.toString(i + 1 - (pag * 40 + 0)), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(datopac.getPrestacion(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((datopac.getDescripcion().length() > 50) ? datopac.getDescripcion().substring(0, 50) : datopac.getDescripcion(), DATO_FONT));
                //cell = new PdfPCell(new Phrase(datopac.getDescripcion() , DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(Integer.toString(datopac.getCantidad()), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(df.format(datopac.getCosto()), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                sumatotal += datopac.getCosto() * datopac.getCantidad();
                sumacant += datopac.getCantidad();
                cell = new PdfPCell(new Phrase(df.format(datopac.getCosto() * datopac.getCantidad()), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(cell);

            }
            for (int i = lFopos.size(); i < pag * 40 + 40; i++) {
                cell = new PdfPCell(new Phrase(Integer.toString(i + 1 - (pag * 40 + 0)), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(cell);
                for (int j = 1; j < 6; j++) {
                    cell = new PdfPCell(new Phrase("", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);
                }
            }
            cell = new PdfPCell(new Phrase("TOTAL  Bs.", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setColspan(3);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(Integer.toString(sumacant), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("", CABEZA_COLUMNA_FONT));
            cell.setGrayFill(0.8f);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(Float.toString(sumatotal), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);

            document.add(table);

            n2t numero = new n2t();

            p = new Paragraph("Monto Literal : Son   " + numero.convertirLetras((int) sumatotal) + "    " + Integer.toString((int) ((sumatotal - (int) sumatotal) * 100)) + "/100  Bolivianos", new Font(Font.HELVETICA, 10, Font.NORMAL, new Color(0, 0, 0)));
            document.add(p);
            p = new Paragraph("Fecha de emision : " + format(new Date(), "dd/MM/yyyy"), DATO_FONT);
            document.add(p);
            p = new Paragraph("Nombre completo responsable del llenado : " + dato.getNombres() + "         Firma:_______________________", DATO_FONT);
            document.add(p);
            p = new Paragraph("El arriba firmante certifica la veracidad de la informacion declarada en este formulario legal.", DATO_FONT);
            document.add(p);
            p = new Paragraph("Nota: - Llenar con letra de imprenta.", DATO_FONT);
            document.add(p);
            p = new Paragraph("        - Este documento debe tener el sello del establecimiento.", DATO_FONT);
            document.add(p);
            document.newPage();
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
