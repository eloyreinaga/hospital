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
import org.ayaic.domain.Localidades;

public class VerCuadernoFisioPDF extends AbstractPdfView {

    private static final Font TITULO_FONT = new Font(Font.HELVETICA, 12, Font.BOLD, Color.black);
    private static final Font CABEZA_COLUMNA_FONT = new Font(Font.HELVETICA, 8, Font.ITALIC, Color.black);
    private static final Font DATO_FONT = new Font(Font.HELVETICA, 8, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_BOLD = new Font(Font.HELVETICA, 8, Font.BOLD, Color.black);
    private static final int MARGIN = 32;

    protected void buildPdfMetadata(Map model, Document document, HttpServletRequest request) {
        Localidades dl = (Localidades) model.get("localidades");
        document.setPageSize(PageSize.LEGAL.rotate());
        document.setMargins(dl.getIzquierda(), dl.getDerecha(), dl.getSuperior(), dl.getInferior());

    }

    protected void buildPdfDocument(Map model, Document document, PdfWriter writer, HttpServletRequest req, HttpServletResponse resp) throws Exception {

        java.util.List lFopos = (java.util.List) model.get("listaCobros");
        Clientes dato = (Clientes) model.get("dato");
        java.util.List cie = (java.util.List) model.get("listaCie");
        int[] sumas = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int[] sumbs = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int una, filas = 30;

        if (lFopos.size() == 0) {
            Paragraph p = new Paragraph("No existe Datos", new Font(Font.HELVETICA, 22, Font.BOLDITALIC, new Color(0, 0, 0)));
            document.add(p);
        }

        if (lFopos.size() % filas == 0) {
            una = 0;
        } else {
            una = 1;
        }

        for (int pag = 0; pag < lFopos.size() / filas + una; pag++) {
            Paragraph p;
            PdfPCell cell;

            int NumColumns = 41;
            PdfPTable table = new PdfPTable(NumColumns);
            int[] fwidths = {14, 22, 20, 31, 90, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10};
            table.setWidths(fwidths);
            table.setWidthPercentage(100);

            cell = new PdfPCell(new Phrase("Cuaderno Medicina Fisica y Rehabilitacion", TITULO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(41);
            table.addCell(cell);

            String sCampos[] = {"No", "FECHA", "Numero de\nHistoria\nClinica", "Numero de\nCarnet de\nAsegurado", "NOMBRE"};
            // coloca la cabecera de titulos
            for (int i = 0; i < 5; i++) {
                cell = new PdfPCell(new Phrase(sCampos[i], CABEZA_COLUMNA_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
            }

            addfila12(table, "Consulta Nueva (Anotar la Edad)");
            addfila12(table, "Consulta Repetida (Anotar la Edad)");

            // coloca el detalle de loS datos
            for (int i = pag * filas + 0; i < pag * filas + filas && i < lFopos.size(); i++) {

                Cuadernos datopac = (Cuadernos) lFopos.get(i);

                cell = new PdfPCell(new Phrase(Integer.toString(i + 1), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(format(datopac.getFechap(), "dd/MM/yy"), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(Integer.toString(datopac.getId_historial()), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(datopac.getRegistro(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(datopac.getNombres(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 2 && "N".equals(datopac.getTipoconsulta()) && datopac.getEdad() == 0) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[0] += (datopac.getId_cuaderno() == 2 && "N".equals(datopac.getTipoconsulta()) && datopac.getEdad() == 0) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 1 && "N".equals(datopac.getTipoconsulta()) && datopac.getEdad() == 0) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[1] += (datopac.getId_cuaderno() == 1 && "N".equals(datopac.getTipoconsulta()) && datopac.getEdad() == 0) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 2 && "N".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 0 && datopac.getEdad() < 5) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[2] += (datopac.getId_cuaderno() == 2 && "N".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 0 && datopac.getEdad() < 5) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 1 && "N".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 0 && datopac.getEdad() < 5) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[3] += (datopac.getId_cuaderno() == 1 && "N".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 0 && datopac.getEdad() < 5) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 2 && "N".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 4 && datopac.getEdad() < 10) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[4] += (datopac.getId_cuaderno() == 2 && "N".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 4 && datopac.getEdad() < 10) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 1 && "N".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 4 && datopac.getEdad() < 10) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[6] += (datopac.getId_cuaderno() == 1 && "N".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 4 && datopac.getEdad() < 10) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 2 && "N".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 9 && datopac.getEdad() < 15) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[7] += (datopac.getId_cuaderno() == 2 && "N".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 9 && datopac.getEdad() < 15) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 1 && "N".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 9 && datopac.getEdad() < 15) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[8] += (datopac.getId_cuaderno() == 1 && "N".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 9 && datopac.getEdad() < 15) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 2 && "N".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 14 && datopac.getEdad() < 20) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[9] += (datopac.getId_cuaderno() == 2 && "N".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 14 && datopac.getEdad() < 20) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 1 && "N".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 14 && datopac.getEdad() < 20) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[10] += (datopac.getId_cuaderno() == 1 && "N".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 14 && datopac.getEdad() < 20) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 2 && "N".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 19 && datopac.getEdad() < 40) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[11] += (datopac.getId_cuaderno() == 2 && "N".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 19 && datopac.getEdad() < 40) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 1 && "N".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 19 && datopac.getEdad() < 40) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[12] += (datopac.getId_cuaderno() == 1 && "N".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 19 && datopac.getEdad() < 40) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 2 && "N".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 39 && datopac.getEdad() < 50) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[13] += (datopac.getId_cuaderno() == 2 && "N".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 39 && datopac.getEdad() < 50) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 1 && "N".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 39 && datopac.getEdad() < 50) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[14] += (datopac.getId_cuaderno() == 1 && "N".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 39 && datopac.getEdad() < 50) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 2 && "N".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 49 && datopac.getEdad() < 60) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[15] += (datopac.getId_cuaderno() == 2 && "N".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 49 && datopac.getEdad() < 60) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 1 && "N".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 49 && datopac.getEdad() < 60) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[16] += (datopac.getId_cuaderno() == 1 && "N".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 49 && datopac.getEdad() < 60) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 2 && "N".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 59 && datopac.getEdad() < 120) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[17] += (datopac.getId_cuaderno() == 2 && "N".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 59 && datopac.getEdad() < 120) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 1 && "N".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 59 && datopac.getEdad() < 120) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[18] += (datopac.getId_cuaderno() == 1 && "N".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 59 && datopac.getEdad() < 120) ? 1 : 0;

/////para repetidos         
                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 2 && "R".equals(datopac.getTipoconsulta()) && datopac.getEdad() == 0) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[19] += (datopac.getId_cuaderno() == 2 && "R".equals(datopac.getTipoconsulta()) && datopac.getEdad() == 0) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 1 && "R".equals(datopac.getTipoconsulta()) && datopac.getEdad() == 0) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[20] += (datopac.getId_cuaderno() == 1 && "R".equals(datopac.getTipoconsulta()) && datopac.getEdad() == 0) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 2 && "R".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 0 && datopac.getEdad() < 5) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[21] += (datopac.getId_cuaderno() == 2 && "R".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 0 && datopac.getEdad() < 5) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 1 && "R".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 0 && datopac.getEdad() < 5) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[22] += (datopac.getId_cuaderno() == 1 && "R".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 0 && datopac.getEdad() < 5) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 2 && "R".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 4 && datopac.getEdad() < 10) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[23] += (datopac.getId_cuaderno() == 2 && "R".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 4 && datopac.getEdad() < 10) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 1 && "R".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 4 && datopac.getEdad() < 10) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[24] += (datopac.getId_cuaderno() == 1 && "R".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 4 && datopac.getEdad() < 10) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 2 && "R".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 9 && datopac.getEdad() < 15) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[25] += (datopac.getId_cuaderno() == 2 && "R".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 9 && datopac.getEdad() < 15) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 1 && "R".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 9 && datopac.getEdad() < 15) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[26] += (datopac.getId_cuaderno() == 1 && "R".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 9 && datopac.getEdad() < 15) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 2 && "R".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 14 && datopac.getEdad() < 20) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[27] += (datopac.getId_cuaderno() == 2 && "R".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 14 && datopac.getEdad() < 20) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 1 && "R".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 14 && datopac.getEdad() < 20) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[28] += (datopac.getId_cuaderno() == 1 && "R".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 14 && datopac.getEdad() < 20) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 2 && "R".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 19 && datopac.getEdad() < 40) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[29] += (datopac.getId_cuaderno() == 2 && "R".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 19 && datopac.getEdad() < 40) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 1 && "R".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 19 && datopac.getEdad() < 40) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[30] += (datopac.getId_cuaderno() == 1 && "R".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 19 && datopac.getEdad() < 40) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 2 && "R".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 39 && datopac.getEdad() < 50) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[31] += (datopac.getId_cuaderno() == 2 && "R".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 39 && datopac.getEdad() < 50) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 1 && "R".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 39 && datopac.getEdad() < 50) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[32] += (datopac.getId_cuaderno() == 1 && "R".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 39 && datopac.getEdad() < 50) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 2 && "R".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 49 && datopac.getEdad() < 60) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[33] += (datopac.getId_cuaderno() == 2 && "R".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 49 && datopac.getEdad() < 60) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 1 && "R".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 49 && datopac.getEdad() < 60) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[34] += (datopac.getId_cuaderno() == 1 && "R".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 49 && datopac.getEdad() < 60) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 2 && "R".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 59 && datopac.getEdad() < 120) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[35] += (datopac.getId_cuaderno() == 2 && "R".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 59 && datopac.getEdad() < 120) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 1 && "R".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 59 && datopac.getEdad() < 120) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[36] += (datopac.getId_cuaderno() == 1 && "R".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 59 && datopac.getEdad() < 120) ? 1 : 0;

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

            cell = new PdfPCell(new Phrase("TOTAL", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(5);
            table.addCell(cell);

            for (int j = 0; j < 37; j++) {
                cell = new PdfPCell(new Phrase(Integer.toString(sumas[j]), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(cell);
            }

            document.add(table);
            document.newPage();

            //////nueva hoja     
            NumColumns = 32;
            table = new PdfPTable(NumColumns);
            int[] fwnidths = {10, 50, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 60, 15, 15};
            table.setWidths(fwnidths);
            table.setWidthPercentage(100);

            cell = new PdfPCell(new Phrase("Para Centros de Salud y Hospitales", TITULO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(NumColumns);
            table.addCell(cell);

            // coloca la cabecera de titulos
            cell = new PdfPCell(new Phrase("No", CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Diagnostico(s)", CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Nro Sesion", CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setRotation(90);
            table.addCell(cell);

            addtitulofila12(table, "TRATAMIENTO", "Consulta Ext.", "Vendaje Neurom", "Eelctroterap", "Ultrasonote", "Laserterapia", "Magnetoterapia", "Masoterapaia", "Mecanoterapia", "Calor Superfi", "Calor Profun", "Luminiterapia", "Electroanalgesia", "Kinesi Respira", "Cinesiterapia", "Terapia Lengua", "Psicomotricidad", "Tecnicas Neuro", "Tecnicas Kinesica", "Trabajo Ocupac", "Discapacidad", "Hidroterapia", "Estimulacion Temp", "Pisina", "Eval, Kinesica", "Tanque Remolino", "Psicologia");

            cell = new PdfPCell(new Phrase("Tratamiento", CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Referido a:", CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("No de Boleta de Ref.", CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            // coloca el detalle de loS datos
            for (int i = pag * filas + 0; i < pag * filas + filas && i < lFopos.size(); i++) {

                Cuadernos datopac = (Cuadernos) lFopos.get(i);

                cell = new PdfPCell(new Phrase(Integer.toString(i + 1), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                String CIE10 = "";
                for (int k = 0; k < cie.size(); k++) {
                    Cuadernos cie10 = (Cuadernos) cie.get(k);
                    if (cie10.getId_historial() == datopac.getId_historial()) {
                        CIE10 = cie10.getTipodent() + "[" + (cie10.getObservaciones().toLowerCase().length() > 25 ? cie10.getObservaciones().toLowerCase().substring(0, 25) : cie10.getObservaciones().toLowerCase()) + "]" + ";" + CIE10;
                    }
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
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("<li>", " "));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("</ul>", " "));
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

                cell = new PdfPCell(new Phrase((datopac.getDiagnostico().length() > 60) ? datopac.getDiagnostico().substring(0, 60) + "->" + CIE10.trim() : datopac.getDiagnostico() + "->" + CIE10.trim(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(Integer.toString(datopac.getNumpieza()), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(datopac.getSuma1() == 1 ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[0] += (datopac.getSuma1() == 1) ? 1 : 0;

                cell = new PdfPCell(new Phrase(datopac.getSuma2() == 1 ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[1] += (datopac.getSuma2() == 1) ? 1 : 0;

                cell = new PdfPCell(new Phrase(datopac.getSuma3() == 1 ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);
                sumbs[2] += (datopac.getSuma3() == 1) ? 1 : 0;

                cell = new PdfPCell(new Phrase(datopac.getSuma4() == 1 ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[3] += (datopac.getSuma4() == 1) ? 1 : 0;

                cell = new PdfPCell(new Phrase(datopac.getSuma5() == 1 ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[4] += (datopac.getSuma5() == 1) ? 1 : 0;

                cell = new PdfPCell(new Phrase(datopac.getSuma6() == 1 ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[5] += (datopac.getSuma6() == 1) ? 1 : 0;

                cell = new PdfPCell(new Phrase(datopac.getSuma7() == 1 ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[6] += (datopac.getSuma7() == 1) ? 1 : 0;

                cell = new PdfPCell(new Phrase(datopac.getSuma8() == 1 ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[7] += (datopac.getSuma8() == 1) ? 1 : 0;

                cell = new PdfPCell(new Phrase(datopac.getSuma9() == 1 ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[8] += (datopac.getSuma9() == 1) ? 1 : 0;

                cell = new PdfPCell(new Phrase(datopac.getSuma10() == 1 ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[9] += (datopac.getSuma10() == 1) ? 1 : 0;

                cell = new PdfPCell(new Phrase(datopac.getSuma11() == 1 ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[10] += (datopac.getSuma11() == 1) ? 1 : 0;

                cell = new PdfPCell(new Phrase(datopac.getSuma12() == 1 ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[11] += (datopac.getSuma12() == 1) ? 1 : 0;

                cell = new PdfPCell(new Phrase(datopac.getSuma13() == 1 ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[12] += (datopac.getSuma13() == 1) ? 1 : 0;

                cell = new PdfPCell(new Phrase(datopac.getSuma14() == 1 ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[13] += (datopac.getSuma14() == 1) ? 1 : 0;

                cell = new PdfPCell(new Phrase(datopac.getSuma15() == 1 ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[14] += (datopac.getSuma15() == 1) ? 1 : 0;

                cell = new PdfPCell(new Phrase(datopac.getSuma16() == 1 ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[15] += (datopac.getSuma16() == 1) ? 1 : 0;

                cell = new PdfPCell(new Phrase(datopac.getSuma17() == 1 ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[16] += (datopac.getSuma17() == 1) ? 1 : 0;

                cell = new PdfPCell(new Phrase(datopac.getSuma18() == 1 ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[17] += (datopac.getSuma18() == 1) ? 1 : 0;

                cell = new PdfPCell(new Phrase(datopac.getSuma19() == 1 ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[18] += (datopac.getSuma19() == 1) ? 1 : 0;

                cell = new PdfPCell(new Phrase(datopac.getSuma20() == 1 ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[19] += (datopac.getSuma20() == 1) ? 1 : 0;

                cell = new PdfPCell(new Phrase(datopac.getSuma21() == 1 ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[20] += (datopac.getSuma21() == 1) ? 1 : 0;

                cell = new PdfPCell(new Phrase(datopac.getSuma22() == 1 ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[21] += (datopac.getSuma22() == 1) ? 1 : 0;

                cell = new PdfPCell(new Phrase(datopac.getSuma23() == 1 ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[22] += (datopac.getSuma23() == 1) ? 1 : 0;

                cell = new PdfPCell(new Phrase(datopac.getSuma24() == 1 ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[23] += (datopac.getSuma24() == 1) ? 1 : 0;

                cell = new PdfPCell(new Phrase(datopac.getSuma25() == 1 ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[24] += (datopac.getSuma25() == 1) ? 1 : 0;

                cell = new PdfPCell(new Phrase(datopac.getSuma26() == 1 ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[25] += (datopac.getSuma26() == 1) ? 1 : 0;

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
                    datopac.setAccion(datopac.getAccion().replaceAll("<li>", " "));
                    datopac.setAccion(datopac.getAccion().replaceAll("</ul>", " "));
                    datopac.setAccion(datopac.getAccion().replaceAll("</li>", " "));
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

                cell = new PdfPCell(new Phrase(datopac.getAccion(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(datopac.getReferido(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(datopac.getContraref(), DATO_FONT));
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

            cell = new PdfPCell(new Phrase("TOTAL", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(3);
            table.addCell(cell);

            for (int j = 0; j < 26; j++) {
                cell = new PdfPCell(new Phrase(Integer.toString(sumbs[j]), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(cell);
            }

            for (int j = 1; j < 26; j++) {
                cell = new PdfPCell(new Phrase("", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);
            }

            document.add(table);
            document.newPage();
        }
    }

    public static void addfila12(PdfPTable table, String a) {

        PdfPCell cell;
        PdfPTable table1 = new PdfPTable(18);

        cell = new PdfPCell(new Phrase(a, CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(18);
        table1.addCell(cell);

        addfila2(table1, "0 a 1", "M", "F");
        addfila2(table1, "1 a 4", "M", "F");
        addfila2(table1, "5 a 9", "M", "F");
        addfila2(table1, "10 a 14", "M", "F");
        addfila2(table1, "15 a 19", "M", "F");
        addfila2(table1, "20 a 39", "M", "F");
        addfila2(table1, "40 a 49", "M", "F");
        addfila2(table1, "50 a 59", "M", "F");
        addfila2(table1, "60 a mas", "M", "F");

        cell = new PdfPCell(table1);
        cell.setColspan(18);
        table.addCell(cell);

    }

    public static void addfila2(PdfPTable table, String a, String b, String c) {
        PdfPCell cell;
        PdfPTable table1 = new PdfPTable(2);

        cell = new PdfPCell(new Phrase(a, CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setColspan(2);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase(b, CABEZA_COLUMNA_FONT));

        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase(c, CABEZA_COLUMNA_FONT));

        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1.addCell(cell);

        cell = new PdfPCell(table1);
        cell.setColspan(2);
        table.addCell(cell);
    }

    public static void addtitulofila12(PdfPTable table, String cadena, String a, String b, String c, String d, String e, String f, String g, String h, String i, String j, String k, String l, String m, String n, String o, String p, String q, String r, String s, String t, String u, String v, String w, String x, String y, String z) {
        PdfPCell cell;
        int NumColumns = 26;
        PdfPTable table11 = new PdfPTable(NumColumns);
        table11.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase(cadena, CABEZA_COLUMNA_FONT));
        cell.setColspan(26);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase(a, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setRotation(90);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase(b, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setRotation(90);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase(c, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setRotation(90);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase(d, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setRotation(90);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase(e, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setRotation(90);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase(f, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setRotation(90);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase(g, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setRotation(90);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase(h, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setRotation(90);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase(i, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setRotation(90);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase(j, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setRotation(90);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase(k, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setRotation(90);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase(l, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setRotation(90);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase(m, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setRotation(90);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase(n, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setRotation(90);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase(o, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setRotation(90);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase(p, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setRotation(90);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase(q, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setRotation(90);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase(r, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setRotation(90);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase(s, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setRotation(90);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase(t, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setRotation(90);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase(u, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setRotation(90);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase(v, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setRotation(90);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase(w, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setRotation(90);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase(x, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setRotation(90);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase(y, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setRotation(90);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase(z, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setRotation(90);
        table11.addCell(cell);

        cell = new PdfPCell(table11);
        cell.setColspan(26);
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
