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

public class VerKardexResumenDispeDiaPDF extends AbstractPdfView {

    private static final Font TITULO_FONT = new Font(Font.HELVETICA, 12, Font.BOLD, Color.black);
    private static final Font CABEZA_COLUMNA_FONT = new Font(Font.HELVETICA, 8, Font.ITALIC, Color.black);
    private static final Font DATO_FONT = new Font(Font.HELVETICA, 8, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_BOLD = new Font(Font.HELVETICA, 8, Font.BOLD, Color.black);
    private static final int MARGIN = 32;

    protected void buildPdfMetadata(Map model, Document document, HttpServletRequest request) {
        document.setPageSize(PageSize.LEGAL.rotate());
        document.setMargins(20, 20, 30, 30);
    }

    protected void buildPdfDocument(Map model, Document document, PdfWriter writer, HttpServletRequest req, HttpServletResponse resp) throws BadElementException, IOException, DocumentException {

        java.util.List listaremi = (java.util.List) model.get("listarKardexRemi");

        Clientes dato = (Clientes) model.get("dato");
        Personas persona = (Personas) model.get("persona");
        String fecha = (String) model.get("dFechafin1");
        String fecha2 = (String) model.get("dFechafin2");
        // coloca el titulo de la pagina
        Image escudo = Image.getInstance("/opt/imagenes/lescudo.bmp");
        Paragraph p = new Paragraph("RESUMEN RECETAS DISPENSADAS DIARIAS", new Font(Font.HELVETICA, 14, Font.BOLDITALIC, new Color(0, 0, 0)));
        p.setAlignment(Element.ALIGN_CENTER);
        //Image sumi = Image.getInstance("/opt/imagenes/sumi.bmp");
        int[] sumas = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        if (listaremi.size() == 0 || listaremi == null) {
            Paragraph pp = new Paragraph("No existe Datos", new Font(Font.HELVETICA, 22, Font.BOLDITALIC, new Color(0, 0, 0)));
            document.add(pp);
        }

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

        cell = new PdfPCell(new Phrase("Fecha Reporte :   " + fecha.substring(8, 10) + " / " + fecha.substring(5, 7) + " / " + fecha.substring(0, 4) + "   al   " + fecha2.substring(8, 10) + " / " + fecha2.substring(5, 7) + " / " + fecha2.substring(0, 4), DATO_FONT));
        cell.setBorder(Rectangle.NO_BORDER);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("Responsable   : " + persona.getNombres(), DATO_FONT));
        cell.setBorder(Rectangle.NO_BORDER);
        table1.addCell(cell);

        document.add(table1);

        int NumColumns = 34;
        PdfPTable table = new PdfPTable(NumColumns);
        int[] fwidths = {8, 55, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10};
        table.setWidths(fwidths);
        table.setWidthPercentage(100);

        String sCampos[] = {"Nro", "Nombres", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "Tot"};
        // coloca la cabecera de titulos

        for (int i = 0; i < NumColumns; i++) {
            cell = new PdfPCell(new Phrase(sCampos[i], CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setGrayFill(0.8f);
            table.addCell(cell);
        }

        // coloca el detalle de loS datos
        for (int j = 0; j < listaremi.size(); j++) {
            Recetas datoremi = (Recetas) listaremi.get(j);

            cell = new PdfPCell(new Phrase(Integer.toString(j + 1), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase((datoremi.getMedicamento().length() > 23) ? datoremi.getMedicamento().substring(0, 23) + "_" + datoremi.getB1() : datoremi.getMedicamento() + "_" + datoremi.getB1(), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(Integer.toString(datoremi.getSuma1()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            sumas[0] += (datoremi.getSuma1());

            cell = new PdfPCell(new Phrase(Integer.toString(datoremi.getSuma2()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            sumas[1] += (datoremi.getSuma2());

            cell = new PdfPCell(new Phrase(Integer.toString(datoremi.getSuma3()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            sumas[2] += (datoremi.getSuma3());

            cell = new PdfPCell(new Phrase(Integer.toString(datoremi.getSuma4()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            sumas[3] += (datoremi.getSuma4());

            cell = new PdfPCell(new Phrase(Integer.toString(datoremi.getSuma5()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            sumas[4] += (datoremi.getSuma5());

            cell = new PdfPCell(new Phrase(Integer.toString(datoremi.getSuma6()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            sumas[5] += (datoremi.getSuma6());

            cell = new PdfPCell(new Phrase(Integer.toString(datoremi.getSuma7()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            sumas[6] += (datoremi.getSuma7());

            cell = new PdfPCell(new Phrase(Integer.toString(datoremi.getSuma8()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            sumas[7] += (datoremi.getSuma8());

            cell = new PdfPCell(new Phrase(Integer.toString(datoremi.getSuma9()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            sumas[8] += (datoremi.getSuma9());

            cell = new PdfPCell(new Phrase(Integer.toString(datoremi.getSuma10()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            sumas[9] += (datoremi.getSuma10());

            cell = new PdfPCell(new Phrase(Integer.toString(datoremi.getSuma11()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            sumas[10] += (datoremi.getSuma11());

            cell = new PdfPCell(new Phrase(Integer.toString(datoremi.getSuma12()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            sumas[11] += (datoremi.getSuma12());

            cell = new PdfPCell(new Phrase(Integer.toString(datoremi.getSuma13()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            sumas[12] += (datoremi.getSuma13());

            cell = new PdfPCell(new Phrase(Integer.toString(datoremi.getSuma14()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            sumas[13] += (datoremi.getSuma14());

            cell = new PdfPCell(new Phrase(Integer.toString(datoremi.getSuma15()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            sumas[14] += (datoremi.getSuma15());

            cell = new PdfPCell(new Phrase(Integer.toString(datoremi.getSuma16()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            sumas[15] += (datoremi.getSuma16());

            cell = new PdfPCell(new Phrase(Integer.toString(datoremi.getSuma17()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            sumas[16] += (datoremi.getSuma17());

            cell = new PdfPCell(new Phrase(Integer.toString(datoremi.getSuma18()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            sumas[17] += (datoremi.getSuma18());

            cell = new PdfPCell(new Phrase(Integer.toString(datoremi.getSuma19()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            sumas[18] += (datoremi.getSuma19());

            cell = new PdfPCell(new Phrase(Integer.toString(datoremi.getSuma20()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            sumas[19] += (datoremi.getSuma20());

            cell = new PdfPCell(new Phrase(Integer.toString(datoremi.getSuma21()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            sumas[20] += (datoremi.getSuma21());

            cell = new PdfPCell(new Phrase(Integer.toString(datoremi.getSuma22()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            sumas[21] += (datoremi.getSuma22());

            cell = new PdfPCell(new Phrase(Integer.toString(datoremi.getSuma23()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            sumas[22] += (datoremi.getSuma23());

            cell = new PdfPCell(new Phrase(Integer.toString(datoremi.getSuma24()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            sumas[23] += (datoremi.getSuma24());

            cell = new PdfPCell(new Phrase(Integer.toString(datoremi.getSuma25()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            sumas[24] += (datoremi.getSuma25());

            cell = new PdfPCell(new Phrase(Integer.toString(datoremi.getSuma26()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            sumas[25] += (datoremi.getSuma26());

            cell = new PdfPCell(new Phrase(Integer.toString(datoremi.getSuma27()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            sumas[26] += (datoremi.getSuma27());

            cell = new PdfPCell(new Phrase(Integer.toString(datoremi.getSuma28()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            sumas[27] += (datoremi.getSuma28());

            cell = new PdfPCell(new Phrase(Integer.toString(datoremi.getSuma29()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            sumas[28] += (datoremi.getSuma29());

            cell = new PdfPCell(new Phrase(Integer.toString(datoremi.getSuma30()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            sumas[29] += (datoremi.getSuma30());

            cell = new PdfPCell(new Phrase(Integer.toString(datoremi.getSuma31()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            sumas[30] += (datoremi.getSuma31());

            cell = new PdfPCell(new Phrase(Integer.toString(datoremi.getSaldos()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            sumas[31] += (datoremi.getSaldos());
        }

        cell = new PdfPCell(new Phrase("TOTAL", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setColspan(2);
        table.addCell(cell);
        for (int j = 0; j < 32; j++) {
            cell = new PdfPCell(new Phrase(Integer.toString(sumas[j]), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
        }
        // llena datos vacios para poner las sumas
        for (int j = 1; j < 5; j++) {
            cell = new PdfPCell(new Phrase("", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);
        }

        document.add(table);

    }

    public static String format(Date dia, String formato) {
        if (dia == null) {
            return "";
        }
        SimpleDateFormat formatDate = new SimpleDateFormat(formato);
        return formatDate.format(dia);
    }
}
