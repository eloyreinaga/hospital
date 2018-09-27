package org.ayaic.web.administrarcobranza.reporteeconomico;

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
import org.ayaic.domain.Detalle;
import org.ayaic.domain.Pacientes;

public class InformeEcoPDF extends AbstractPdfView {

    private static final Font TITULO_FONT = new Font(Font.HELVETICA, 12, Font.BOLD, Color.black);
    private static final Font CABEZA_COLUMNA_FONT = new Font(Font.HELVETICA, 10, Font.ITALIC, Color.black);
    private static final Font DATO_FONT = new Font(Font.HELVETICA, 8, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_BOLD = new Font(Font.HELVETICA, 12, Font.BOLD, Color.black);
    private static final int MARGIN = 32;

    protected void buildPdfMetadata(Map model, Document document, HttpServletRequest request) {
        document.setPageSize(PageSize.LETTER);
        document.setMargins(10, 10, 10, 15);
    }

    protected void buildPdfDocument(Map model, Document document, PdfWriter writer, HttpServletRequest req, HttpServletResponse resp) throws BadElementException, IOException, DocumentException {

        java.util.List lRubros = (java.util.List) model.get("listaRubros");
        java.util.List lCobros = (java.util.List) model.get("listaCobros");
        java.util.List Costos = (java.util.List) model.get("listarCostos");
        java.util.List estab = (java.util.List) model.get("Estab");
        String anio1 = (String) model.get("anio1");
        String mes1 = (String) model.get("mes1");
        String dia1 = (String) model.get("dia1");
        String anio2 = (String) model.get("anio2");
        String mes2 = (String) model.get("mes2");
        String dia2 = (String) model.get("dia2");

        Clientes dato = (Clientes) model.get("dato");
        Pacientes persona = (Pacientes) model.get("persona");
        DecimalFormat df = new DecimalFormat("#,##0.00");
        DecimalFormat dfx = new DecimalFormat("###,##0.0");
        //    Medicamentos dato = (Medicamentos)model.get("dato");
        String tipo;
        String sCampos[] = {"No", "Cobrado\npor:", "NOMBRE", "Fecha", "Num ClaDoc", "Otro Doc.\n(Reconsulta)", "Total \nCobro"};
        int num = 0;

        Image escudo = Image.getInstance("/opt/imagenes/lescudo.bmp");
        Paragraph p = new Paragraph("Informe Economico por Rubro", new Font(Font.HELVETICA, 14, Font.BOLDITALIC, new Color(0, 0, 0)));
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

        PdfPTable table1 = new PdfPTable(4);
        int[] fmwidths = {30, 10, 15, 45}; // percentage
        table1.setWidths(fmwidths);
        table1.setWidthPercentage(100);

        //cell = new PdfPCell(new Phrase("FECHA DEL REPORTE :    "+dia1+"-"+mes1+"-"+anio1, DATO_FONT));
        cell = new PdfPCell(new Phrase("FECHA DEL REPORTE :    ", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setBorder(Rectangle.NO_BORDER);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("A : ", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(Rectangle.NO_BORDER);
        table1.addCell(cell);

        //cell = new PdfPCell(new Phrase(dia2+"-"+mes2+"-"+anio2 , DATO_FONT));
        cell = new PdfPCell(new Phrase("-", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        table1.addCell(cell);

        double total = 0.0;
        for (int i = 0; i < lCobros.size(); i++) {
            Pacientes datopac = (Pacientes) lCobros.get(i);
            total += datopac.getPrecio_total();
        }
        cell = new PdfPCell(new Phrase(" TOTAL RECAUDADO BS.:   " + total, DATO_FONT_BOLD));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        table1.addCell(cell);

        document.add(table1);

        int NumColumns = 7;
        PdfPTable table = new PdfPTable(NumColumns);
        int[] fwidths = {5, 10, 40, 18, 10, 10, 18}; // percentage
        table.setWidths(fwidths);
        table.setWidthPercentage(100);

        // coloca la cabecera de titulos
        for (int i = 0; i < sCampos.length; i++) {
            cell = new PdfPCell(new Phrase(sCampos[i], CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setGrayFill(0.95f);
            table.addCell(cell);
        }
        // coloca el detalle de los datos
        table.setHeaderRows(1);
        for (int i = 0; i < lCobros.size(); i++) {

            Pacientes datopac = (Pacientes) lCobros.get(i);

            cell = new PdfPCell(new Phrase(Integer.toString(i + 1), CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setGrayFill(0.95f);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(Integer.toString(datopac.getId_persona()), CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setGrayFill(0.95f);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(datopac.getNombres(), CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setGrayFill(0.95f);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(format(datopac.getFec_registro(), "dd/MM/yy HH:mm"), CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setGrayFill(0.95f);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(datopac.getNum_cladoc(), CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setGrayFill(0.95f);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(datopac.getNit(), CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setGrayFill(0.95f);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(df.format(datopac.getPrecio_total()), CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setGrayFill(0.95f);
            table.addCell(cell);

            table1 = new PdfPTable(5);
            int[] fmpwidths = {6, 10, 50, 10, 10}; // percentage
            table1.setWidths(fmpwidths);

            table1.setWidthPercentage(100);

            cell = new PdfPCell(new Phrase("No.", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setGrayFill(0.95f);
            cell.setFixedHeight(20f);
            table1.addCell(cell);

            cell = new PdfPCell(new Phrase("Fecha", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setGrayFill(0.95f);
            cell.setFixedHeight(20f);
            table1.addCell(cell);

            cell = new PdfPCell(new Phrase("Detalle", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setGrayFill(0.95f);
            cell.setFixedHeight(20f);
            table1.addCell(cell);

            cell = new PdfPCell(new Phrase("Costo\nUnit.", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setGrayFill(0.95f);
            cell.setFixedHeight(20f);
            table1.addCell(cell);

            cell = new PdfPCell(new Phrase("Monto", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setGrayFill(0.95f);
            cell.setFixedHeight(20f);
            table1.addCell(cell);

            double tot = 0.0;
            for (int j = 0; j < Costos.size(); j++) {

                Detalle datocosto = (Detalle) Costos.get(j);

                if (datopac.getId_pedido() == datocosto.getId_pedido()) {

                    cell = new PdfPCell(new Phrase(Integer.toString(j + 1), CABEZA_COLUMNA_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table1.addCell(cell);

                    cell = new PdfPCell(new Phrase(format(datocosto.getFecha(), "dd/MM/yy HH:mm"), DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table1.addCell(cell);

                    cell = new PdfPCell(new Phrase(datocosto.getCosto() + "      " + datocosto.getIndicacion(), DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table1.addCell(cell);

                    cell = new PdfPCell(new Phrase(df.format(datocosto.getCosto_unit()), DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table1.addCell(cell);

                    cell = new PdfPCell(new Phrase(df.format(datocosto.getEntrada()), DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table1.addCell(cell);
                }
            }
            cell = new PdfPCell(table1);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(7);
            //  cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);
        }
        // document.getPageNumber();
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
