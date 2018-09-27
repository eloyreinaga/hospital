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

public class VerCemergencias2014PDF extends AbstractPdfView {

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
        java.util.List cie = (java.util.List) model.get("listaCie");
        Clientes dato = (Clientes) model.get("dato");

        int[] sumas = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int[] sumbs = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
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

            int NumColumns = 46;
            PdfPTable table = new PdfPTable(NumColumns);
            int[] fwidths = {14, 30, 24, 36, 19, 90, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10};
            table.setWidths(fwidths);
            table.setWidthPercentage(100);

            cell = new PdfPCell(new Phrase("Cuaderno Emergencias", TITULO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(46);
            table.addCell(cell);

            String sCampos[] = {"No", "FECHA", "Numero de\nHistoria\nClinica", "Numero de\nCarnet de\nAsegurado", "Hora", "NOMBRE"};
            // coloca la cabecera de titulos
            for (int i = 0; i < 6; i++) {
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

                cell = new PdfPCell(new Phrase(format(datopac.getFechap(), "dd/MM/yyyy"), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(Integer.toString(datopac.getId_historial()), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(datopac.getRegistro(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(format(datopac.getFechap(), "HH:mm"), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(datopac.getNombres(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 2 && "N".equals(datopac.getTipoconsulta()) && datopac.getEdad() == 0 && datopac.getMes() < 6) ? Integer.toString(datopac.getMes()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[0] += (datopac.getId_cuaderno() == 2 && "N".equals(datopac.getTipoconsulta()) && datopac.getEdad() == 0 && datopac.getMes() < 6) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 1 && "N".equals(datopac.getTipoconsulta()) && datopac.getEdad() == 0 && datopac.getMes() < 6) ? Integer.toString(datopac.getMes()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[1] += (datopac.getId_cuaderno() == 1 && "N".equals(datopac.getTipoconsulta()) && datopac.getEdad() == 0 && datopac.getMes() < 6) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 2 && "N".equals(datopac.getTipoconsulta()) && datopac.getEdad() == 0 && datopac.getMes() > 5) ? Integer.toString(datopac.getMes()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[2] += (datopac.getId_cuaderno() == 2 && "N".equals(datopac.getTipoconsulta()) && datopac.getEdad() == 0 && datopac.getMes() > 5) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 1 && "N".equals(datopac.getTipoconsulta()) && datopac.getEdad() == 0 && datopac.getMes() > 5) ? Integer.toString(datopac.getMes()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[3] += (datopac.getId_cuaderno() == 1 && "N".equals(datopac.getTipoconsulta()) && datopac.getEdad() == 0 && datopac.getMes() > 5) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 2 && "N".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 0 && datopac.getEdad() < 5) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[4] += (datopac.getId_cuaderno() == 2 && "N".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 0 && datopac.getEdad() < 5) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 1 && "N".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 0 && datopac.getEdad() < 5) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[5] += (datopac.getId_cuaderno() == 1 && "N".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 0 && datopac.getEdad() < 5) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 2 && "N".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 4 && datopac.getEdad() < 10) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[6] += (datopac.getId_cuaderno() == 2 && "N".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 4 && datopac.getEdad() < 10) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 1 && "N".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 4 && datopac.getEdad() < 10) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[7] += (datopac.getId_cuaderno() == 1 && "N".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 4 && datopac.getEdad() < 10) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 2 && "N".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 9 && datopac.getEdad() < 15) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[8] += (datopac.getId_cuaderno() == 2 && "N".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 9 && datopac.getEdad() < 15) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 1 && "N".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 9 && datopac.getEdad() < 15) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[9] += (datopac.getId_cuaderno() == 1 && "N".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 9 && datopac.getEdad() < 15) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 2 && "N".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 14 && datopac.getEdad() < 20) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[10] += (datopac.getId_cuaderno() == 2 && "N".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 14 && datopac.getEdad() < 20) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 1 && "N".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 14 && datopac.getEdad() < 20) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[11] += (datopac.getId_cuaderno() == 1 && "N".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 14 && datopac.getEdad() < 20) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 2 && "N".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 19 && datopac.getEdad() < 40) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[12] += (datopac.getId_cuaderno() == 2 && "N".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 19 && datopac.getEdad() < 40) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 1 && "N".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 19 && datopac.getEdad() < 40) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[13] += (datopac.getId_cuaderno() == 1 && "N".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 19 && datopac.getEdad() < 40) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 2 && "N".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 39 && datopac.getEdad() < 50) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[14] += (datopac.getId_cuaderno() == 2 && "N".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 39 && datopac.getEdad() < 50) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 1 && "N".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 39 && datopac.getEdad() < 50) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[15] += (datopac.getId_cuaderno() == 1 && "N".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 39 && datopac.getEdad() < 50) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 2 && "N".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 49 && datopac.getEdad() < 60) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[16] += (datopac.getId_cuaderno() == 2 && "N".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 49 && datopac.getEdad() < 60) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 1 && "N".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 49 && datopac.getEdad() < 60) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[17] += (datopac.getId_cuaderno() == 1 && "N".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 49 && datopac.getEdad() < 60) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 2 && "N".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 59 && datopac.getEdad() < 120) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[18] += (datopac.getId_cuaderno() == 2 && "N".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 59 && datopac.getEdad() < 120) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 1 && "N".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 59 && datopac.getEdad() < 120) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[19] += (datopac.getId_cuaderno() == 1 && "N".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 59 && datopac.getEdad() < 120) ? 1 : 0;

/////para repetidos
                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 2 && "R".equals(datopac.getTipoconsulta()) && datopac.getEdad() == 0 && datopac.getEdad() < 6) ? Integer.toString(datopac.getMes()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[20] += (datopac.getId_cuaderno() == 2 && "R".equals(datopac.getTipoconsulta()) && datopac.getEdad() == 0 && datopac.getEdad() < 6) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 1 && "R".equals(datopac.getTipoconsulta()) && datopac.getEdad() == 0 && datopac.getEdad() < 6) ? Integer.toString(datopac.getMes()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[21] += (datopac.getId_cuaderno() == 1 && "R".equals(datopac.getTipoconsulta()) && datopac.getEdad() == 0 && datopac.getEdad() < 6) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 2 && "R".equals(datopac.getTipoconsulta()) && datopac.getEdad() == 0 && datopac.getEdad() > 5) ? Integer.toString(datopac.getMes()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[22] += (datopac.getId_cuaderno() == 2 && "R".equals(datopac.getTipoconsulta()) && datopac.getEdad() == 0 && datopac.getEdad() > 5) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 1 && "R".equals(datopac.getTipoconsulta()) && datopac.getEdad() == 0 && datopac.getEdad() > 5) ? Integer.toString(datopac.getMes()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[23] += (datopac.getId_cuaderno() == 1 && "R".equals(datopac.getTipoconsulta()) && datopac.getEdad() == 0 && datopac.getEdad() > 5) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 2 && "R".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 0 && datopac.getEdad() < 5) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[24] += (datopac.getId_cuaderno() == 2 && "R".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 0 && datopac.getEdad() < 5) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 1 && "R".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 0 && datopac.getEdad() < 5) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[25] += (datopac.getId_cuaderno() == 1 && "R".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 0 && datopac.getEdad() < 5) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 2 && "R".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 4 && datopac.getEdad() < 10) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[26] += (datopac.getId_cuaderno() == 2 && "R".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 4 && datopac.getEdad() < 10) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 1 && "R".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 4 && datopac.getEdad() < 10) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[27] += (datopac.getId_cuaderno() == 1 && "R".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 4 && datopac.getEdad() < 10) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 2 && "R".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 9 && datopac.getEdad() < 15) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[28] += (datopac.getId_cuaderno() == 2 && "R".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 9 && datopac.getEdad() < 15) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 1 && "R".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 9 && datopac.getEdad() < 15) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[29] += (datopac.getId_cuaderno() == 1 && "R".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 9 && datopac.getEdad() < 15) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 2 && "R".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 14 && datopac.getEdad() < 20) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[30] += (datopac.getId_cuaderno() == 2 && "R".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 14 && datopac.getEdad() < 20) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 1 && "R".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 14 && datopac.getEdad() < 20) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[31] += (datopac.getId_cuaderno() == 1 && "R".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 14 && datopac.getEdad() < 20) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 2 && "R".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 19 && datopac.getEdad() < 40) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[32] += (datopac.getId_cuaderno() == 2 && "R".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 19 && datopac.getEdad() < 40) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 1 && "R".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 19 && datopac.getEdad() < 40) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[33] += (datopac.getId_cuaderno() == 1 && "R".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 19 && datopac.getEdad() < 40) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 2 && "R".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 39 && datopac.getEdad() < 50) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[34] += (datopac.getId_cuaderno() == 2 && "R".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 39 && datopac.getEdad() < 50) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 1 && "R".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 39 && datopac.getEdad() < 50) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[35] += (datopac.getId_cuaderno() == 1 && "R".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 39 && datopac.getEdad() < 50) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 2 && "R".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 49 && datopac.getEdad() < 60) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[36] += (datopac.getId_cuaderno() == 2 && "R".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 49 && datopac.getEdad() < 60) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 1 && "R".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 49 && datopac.getEdad() < 60) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[37] += (datopac.getId_cuaderno() == 1 && "R".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 49 && datopac.getEdad() < 60) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 2 && "R".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 59 && datopac.getEdad() < 120) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[38] += (datopac.getId_cuaderno() == 2 && "R".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 59 && datopac.getEdad() < 120) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 1 && "R".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 59 && datopac.getEdad() < 120) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[39] += (datopac.getId_cuaderno() == 1 && "R".equals(datopac.getTipoconsulta()) && datopac.getEdad() > 59 && datopac.getEdad() < 120) ? 1 : 0;

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
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setColspan(6);
            table.addCell(cell);

            for (int j = 0; j < 40; j++) {
                cell = new PdfPCell(new Phrase(Integer.toString(sumas[j]), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(cell);
            }

            document.add(table);
            document.newPage();

            NumColumns = 10;
            table = new PdfPTable(NumColumns);
            int[] fwnidths = {10, 75, 10, 10, 10, 10, 83, 15, 15, 7};
            table.setWidths(fwnidths);
            table.setWidthPercentage(100);

            cell = new PdfPCell(new Phrase("Para Centros de Salud y Hospitales Basicos", TITULO_FONT));
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

            addtitulofila3(table, "Factores de Riesgo", "Sobrepeso", "Abuso Alcohol", "Habito de fumar", "Violencia Familiar");

            cell = new PdfPCell(new Phrase("Tratamiento", CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Referido a:", CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("No de Boleta de Ref.", CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Atendido por", CABEZA_COLUMNA_FONT));
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
                    if (cie10.getId_historial() == datopac.getId_laboratorio()) {
                        CIE10 = cie10.getTipodent() + "[" + (cie10.getObservaciones().toLowerCase().length() > 15 ? cie10.getObservaciones().toLowerCase().substring(0, 15) : cie10.getObservaciones().toLowerCase()) + "]" + ";" + CIE10;
                    }
                }

                if (datopac.getDiagnostico() == null || "null".equals(datopac.getDiagnostico())) {
                    datopac.setDiagnostico("");
                } else {
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("<p>", "\n"));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("</p>", " "));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("&nbsp;", ""));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("<strong>", " "));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("</strong>", " "));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("<br />", "\n"));
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

                cell = new PdfPCell(new Phrase((datopac.getDiagnostico().length() > 60) ? datopac.getDiagnostico().substring(0, 60) + "->" + CIE10.trim() : datopac.getDiagnostico() + "->" + CIE10.trim(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(datopac.getSpeso() == 1 ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);
                sumbs[0] += (datopac.getSpeso() == 1) ? 1 : 0;

                cell = new PdfPCell(new Phrase(datopac.getAlcohol() == 1 ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);
                sumbs[1] += (datopac.getAlcohol() == 1) ? 1 : 0;

                cell = new PdfPCell(new Phrase(datopac.getFuma() == 1 ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);
                sumbs[2] += (datopac.getFuma() == 1) ? 1 : 0;

                cell = new PdfPCell(new Phrase(datopac.getViolencia() == 1 ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);
                sumbs[3] += (datopac.getViolencia() == 1) ? 1 : 0;

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
                    datopac.setAccion(datopac.getAccion().replaceAll("<ol>", " "));
                    datopac.setAccion(datopac.getAccion().replaceAll("</ol>", " "));
                    datopac.setAccion(datopac.getAccion().replaceAll("<ul>", " "));
                    datopac.setAccion(datopac.getAccion().replaceAll("</ul>", " "));
                    datopac.setAccion(datopac.getAccion().replaceAll("<li>", " "));
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

                cell = new PdfPCell(new Phrase((datopac.getAccion().length() > 50) ? datopac.getAccion().substring(0, 50) : datopac.getAccion(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(datopac.getReferido(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(datopac.getContraref(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(Integer.toString(datopac.getId_persona()), DATO_FONT));
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
            cell.setColspan(2);
            table.addCell(cell);

            for (int j = 0; j < 4; j++) {
                cell = new PdfPCell(new Phrase(Integer.toString(sumbs[j]), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(cell);
            }

            for (int j = 1; j < 10; j++) {
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
        PdfPTable table1 = new PdfPTable(20);

        cell = new PdfPCell(new Phrase(a, CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(20);
        table1.addCell(cell);

        addfila2(table1, "< 6 mes", "M", "F");
        addfila2(table1, "6m a 1año", "M", "F");
        addfila2(table1, "1 a 4", "M", "F");
        addfila2(table1, "5 a 9", "M", "F");
        addfila2(table1, "10 a 14", "M", "F");
        addfila2(table1, "15 a 19", "M", "F");
        addfila2(table1, "20 a 39", "M", "F");
        addfila2(table1, "40 a 49", "M", "F");
        addfila2(table1, "50 a 59", "M", "F");
        addfila2(table1, "60 a mas", "M", "F");

        cell = new PdfPCell(table1);
        cell.setColspan(20);
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

    public static void addtitulofila3(PdfPTable table, String cadena, String a, String b, String c, String d) {
        PdfPCell cell;
        int NumColumns = 4;
        PdfPTable table11 = new PdfPTable(NumColumns);
        table11.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase(cadena, CABEZA_COLUMNA_FONT));
        cell.setColspan(4);
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

        cell = new PdfPCell(new Phrase(d, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        cell = new PdfPCell(table11);
        cell.setColspan(4);
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
