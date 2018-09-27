package org.ayaic.web.administrarmedicamentos;

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
import org.ayaic.domain.Personas;
import org.ayaic.domain.Recetas;

public class VerKardexMedGeneralPDF extends AbstractPdfView {

    private static final Font TITULO_FONT = new Font(Font.HELVETICA, 12, Font.BOLD, Color.black);
    private static final Font CABEZA_COLUMNA_FONT = new Font(Font.HELVETICA, 8, Font.ITALIC, Color.black);
    private static final Font DATO_FONT = new Font(Font.HELVETICA, 11, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_BOLD = new Font(Font.HELVETICA, 8, Font.BOLD, Color.black);
    private static final int MARGIN = 32;

    protected void buildPdfMetadata(Map model, Document document, HttpServletRequest request) {
        document.setPageSize(PageSize.LETTER);
        document.setMargins(20, 20, 20, 20);
    }

    protected void buildPdfDocument(Map model, Document document, PdfWriter writer, HttpServletRequest req, HttpServletResponse resp) throws BadElementException, IOException, DocumentException {

        java.util.List listaremi = (java.util.List) model.get("listarKardexRemi");

        Clientes dato = (Clientes) model.get("dato");
        Personas persona = (Personas) model.get("persona");
        String fecha = (String) model.get("dFechafin1");
        String fecha2 = (String) model.get("dFechafin2");
        // coloca el titulo de la pagina
        Image escudo = Image.getInstance("/opt/imagenes/lescudo.bmp");
        Paragraph p = new Paragraph("REPORTE GENERAL DE MEDICAMENTOS", new Font(Font.HELVETICA, 14, Font.BOLDITALIC, new Color(0, 0, 0)));
        p.setAlignment(Element.ALIGN_CENTER);
        //Image sumi = Image.getInstance("/opt/imagenes/sumi.bmp");
        int[] sumas = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int[] sumbs = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        if (listaremi.size() == 0 || listaremi == null) {
            Paragraph pp = new Paragraph("No existe Datos", new Font(Font.HELVETICA, 22, Font.BOLDITALIC, new Color(0, 0, 0)));
            document.add(pp);
        }

        int mun = 160;
        int una;
        if (listaremi.size() % mun == 0) {
            una = 0;
        } else {
            una = 1;
        }

        for (int pag = 0; pag < listaremi.size() / mun + una; pag++) {

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

            cell = new PdfPCell(new Phrase("Establecimiento : " + dato.getEstablecimiento(), DATO_FONT_BOLD));
            cell.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell);

            cell = new PdfPCell(new Phrase("Municipio : " + dato.getLocalidad(), DATO_FONT_BOLD));
            cell.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell);

            cell = new PdfPCell(new Phrase("Fecha Reporte :   " + fecha.substring(8, 10) + " / " + fecha.substring(5, 7) + " / " + fecha.substring(0, 4) + "   al   " + fecha2.substring(8, 10) + " / " + fecha2.substring(5, 7) + " / " + fecha2.substring(0, 4), DATO_FONT_BOLD));
            cell.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell);

            cell = new PdfPCell(new Phrase("Responsable   : " + persona.getNombres(), DATO_FONT_BOLD));
            cell.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell);

            document.add(table1);

            int NumColumns = 12;
            PdfPTable table = new PdfPTable(NumColumns);
            int[] fwidths = {10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10};
            table.setWidths(fwidths);
            table.setWidthPercentage(100);

            String sCamposs[] = {"Codigo", "No.Recetas", "Nro.Medicam", "Codigo", "No.Recetas", "Nro.Medicam", "Codigo", "No.Recetas", "Nro.Medicam", "Codigo", "No.Recetas", "Nro.Medicam"};
            // coloca la cabecera de titulos

            for (int i = 0; i < 12; i++) {
                cell = new PdfPCell(new Phrase(sCamposs[i], CABEZA_COLUMNA_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setGrayFill(0.8f);
                table.addCell(cell);
            }

            // coloca el detalle de loS datos
            for (int i = pag * mun + 0; i < pag * mun + mun && i < listaremi.size(); i++) {

                Recetas datoremi = (Recetas) listaremi.get(i);

                if (i < pag * mun + 50) {
                    cell = new PdfPCell(new Phrase(datoremi.getCodsumi(), DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase((datoremi.getSuma1() + datoremi.getSuma3() + datoremi.getSuma5() + datoremi.getSuma7() + datoremi.getSuma9() + datoremi.getSuma11() == 0) ? "" : Integer.toString(datoremi.getSuma1() + datoremi.getSuma3() + datoremi.getSuma5() + datoremi.getSuma7() + datoremi.getSuma9() + datoremi.getSuma11()), DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                    sumas[0] += (datoremi.getSuma1() + datoremi.getSuma3() + datoremi.getSuma5() + datoremi.getSuma7() + datoremi.getSuma9() + datoremi.getSuma11());

                    cell = new PdfPCell(new Phrase((datoremi.getSuma2() + datoremi.getSuma4() + datoremi.getSuma6() + datoremi.getSuma8() + datoremi.getSuma10() + datoremi.getSuma12() == 0) ? "" : Integer.toString(datoremi.getSuma2() + datoremi.getSuma4() + datoremi.getSuma6() + datoremi.getSuma8() + datoremi.getSuma10() + datoremi.getSuma12()), DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                    sumas[1] += (datoremi.getSuma2() + datoremi.getSuma4() + datoremi.getSuma6() + datoremi.getSuma8() + datoremi.getSuma10() + datoremi.getSuma12());
                }
                if (i >= pag * mun + 50 && i < pag * mun + 100) {
                    cell = new PdfPCell(new Phrase(datoremi.getCodsumi(), DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase((datoremi.getSuma1() + datoremi.getSuma3() + datoremi.getSuma5() + datoremi.getSuma7() + datoremi.getSuma9() + datoremi.getSuma11() == 0) ? "" : Integer.toString(datoremi.getSuma1() + datoremi.getSuma3() + datoremi.getSuma5() + datoremi.getSuma7() + datoremi.getSuma9() + datoremi.getSuma11()), DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                    sumas[0] += (datoremi.getSuma1() + datoremi.getSuma3() + datoremi.getSuma5() + datoremi.getSuma7() + datoremi.getSuma9() + datoremi.getSuma11());

                    cell = new PdfPCell(new Phrase((datoremi.getSuma2() + datoremi.getSuma4() + datoremi.getSuma6() + datoremi.getSuma8() + datoremi.getSuma10() + datoremi.getSuma12() == 0) ? "" : Integer.toString(datoremi.getSuma2() + datoremi.getSuma4() + datoremi.getSuma6() + datoremi.getSuma8() + datoremi.getSuma10() + datoremi.getSuma12()), DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                    sumas[1] += (datoremi.getSuma2() + datoremi.getSuma4() + datoremi.getSuma6() + datoremi.getSuma8() + datoremi.getSuma10() + datoremi.getSuma12());
                }
                if (i >= pag * mun + 100 && i < pag * mun + 150) {
                    cell = new PdfPCell(new Phrase(datoremi.getCodsumi(), DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase((datoremi.getSuma1() + datoremi.getSuma3() + datoremi.getSuma5() + datoremi.getSuma7() + datoremi.getSuma9() + datoremi.getSuma11() == 0) ? "" : Integer.toString(datoremi.getSuma1() + datoremi.getSuma3() + datoremi.getSuma5() + datoremi.getSuma7() + datoremi.getSuma9() + datoremi.getSuma11()), DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                    sumas[0] += (datoremi.getSuma1() + datoremi.getSuma3() + datoremi.getSuma5() + datoremi.getSuma7() + datoremi.getSuma9() + datoremi.getSuma11());

                    cell = new PdfPCell(new Phrase((datoremi.getSuma2() + datoremi.getSuma4() + datoremi.getSuma6() + datoremi.getSuma8() + datoremi.getSuma10() + datoremi.getSuma12() == 0) ? "" : Integer.toString(datoremi.getSuma2() + datoremi.getSuma4() + datoremi.getSuma6() + datoremi.getSuma8() + datoremi.getSuma10() + datoremi.getSuma12()), DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                    sumas[1] += (datoremi.getSuma2() + datoremi.getSuma4() + datoremi.getSuma6() + datoremi.getSuma8() + datoremi.getSuma10() + datoremi.getSuma12());
                }
                if (i >= pag * mun + 150 && i < pag * mun + 200) {
                    cell = new PdfPCell(new Phrase(datoremi.getCodsumi(), DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase((datoremi.getSuma1() + datoremi.getSuma3() + datoremi.getSuma5() + datoremi.getSuma7() + datoremi.getSuma9() + datoremi.getSuma11() == 0) ? "" : Integer.toString(datoremi.getSuma1() + datoremi.getSuma3() + datoremi.getSuma5() + datoremi.getSuma7() + datoremi.getSuma9() + datoremi.getSuma11()), DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                    sumas[0] += (datoremi.getSuma1() + datoremi.getSuma3() + datoremi.getSuma5() + datoremi.getSuma7() + datoremi.getSuma9() + datoremi.getSuma11());

                    cell = new PdfPCell(new Phrase((datoremi.getSuma2() + datoremi.getSuma4() + datoremi.getSuma6() + datoremi.getSuma8() + datoremi.getSuma10() + datoremi.getSuma12() == 0) ? "" : Integer.toString(datoremi.getSuma2() + datoremi.getSuma4() + datoremi.getSuma6() + datoremi.getSuma8() + datoremi.getSuma10() + datoremi.getSuma12()), DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                    sumas[1] += (datoremi.getSuma2() + datoremi.getSuma4() + datoremi.getSuma6() + datoremi.getSuma8() + datoremi.getSuma10() + datoremi.getSuma12());
                }

            }
            /*
        for (int i = listaremi.size(); i<pag*mun+mun ; i++) {
                     cell = new PdfPCell(new Phrase(" ", DATO_FONT));
                     cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                     cell.setBorder(Rectangle.NO_BORDER);
                     table.addCell(cell);
                     for (int j =  1;j<9; j++) {
                         cell = new PdfPCell(new Phrase(" " , DATO_FONT));
                         cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                         cell.setBorder(Rectangle.NO_BORDER);
                         table.addCell(cell);
                     }
             }
             */
            cell = new PdfPCell(new Phrase(" ", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(12);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Medicamentos  :  " + listaremi.size() + "            Nro.  Recetas  :  " + sumas[0] + "            Nro. Medicamentos  :  " + sumas[1], DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(12);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Pagina No.  " + (pag + 1), TITULO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            //cell.setFixedHeight(30f); 
            cell.setColspan(12);
            table.addCell(cell);

            document.add(table);
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
