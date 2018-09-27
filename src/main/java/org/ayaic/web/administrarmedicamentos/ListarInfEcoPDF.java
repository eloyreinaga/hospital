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
import org.ayaic.domain.Medicamentos;
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.Recetas;

public class ListarInfEcoPDF extends AbstractPdfView {

    private static final Font TITULO_FONT = new Font(Font.HELVETICA, 12, Font.BOLD, Color.black);
    private static final Font CABEZA_COLUMNA_FONT = new Font(Font.HELVETICA, 10, Font.ITALIC, Color.black);
    private static final Font DATO_FONT = new Font(Font.HELVETICA, 8, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_BOLD = new Font(Font.HELVETICA, 12, Font.BOLD, Color.black);
    private static final int MARGIN = 32;

    protected void buildPdfMetadata(Map model, Document document, HttpServletRequest request) {
        document.setPageSize(PageSize.LETTER);
        document.setMargins(10, 10, 10, 10);
    }

    protected void buildPdfDocument(Map model, Document document, PdfWriter writer, HttpServletRequest req, HttpServletResponse resp) throws BadElementException, DocumentException, IOException {

        java.util.List listaPago = (java.util.List) model.get("listapago");
        java.util.List listaMed = (java.util.List) model.get("listarKardex");
        java.util.List listaTotal = (java.util.List) model.get("listatotal");
        Medicamentos datoItem = (Medicamentos) model.get("datoItem");
        String mtotal = (String) model.get("montototal");
        String anio1 = (String) model.get("anio1");
        String mes1 = (String) model.get("mes1");
        String dia1 = (String) model.get("dia1");
        String anio2 = (String) model.get("anio2");
        String mes2 = (String) model.get("mes2");
        String dia2 = (String) model.get("dia2");
        String hora1 = (String) model.get("hora1");
        String hora2 = (String) model.get("hora2");
        String minuto1 = (String) model.get("minuto1");
        String minuto2 = (String) model.get("minuto2");
        String Accionimp = (String) model.get("AccionImp");
        //    Medicamentos dato = (Medicamentos)model.get("dato");
        String tipo;
        String sCampos[] = {"No", "No_Doc.", "Fecha", "Nombre Cliente", "No_Pedido", "No_Factura", "Monto Total"};
        int num = 0;
        double sumacostototal = 0;
        double tot = 0.0;

        DecimalFormat df = new DecimalFormat("###,##0.00");
        DecimalFormat dfx = new DecimalFormat("###,##0");
        HeaderFooter footer = new HeaderFooter(new Phrase("Pagina No. "), new Phrase("."));

        footer.setAlignment(Element.ALIGN_CENTER);
        footer.setBorder(Rectangle.NO_BORDER);
        document.setFooter(footer);

        Image escudo = Image.getInstance("/opt/imagenes/lescudo.bmp");
        Paragraph p = new Paragraph("Informe Productos Entregados\n" + Accionimp, new Font(Font.HELVETICA, 14, Font.BOLDITALIC, new Color(0, 0, 0)));
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

        cell = new PdfPCell(new Phrase("FECHA DEL REPORTE :    " + dia1 + "-" + mes1 + "-" + anio1 + " " + hora1 + ":" + minuto1 + ":00", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setBorder(Rectangle.NO_BORDER);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("A : ", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(Rectangle.NO_BORDER);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase(dia2 + "-" + mes2 + "-" + anio2 + " " + hora2 + ":" + minuto2 + ":00", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase(" TOTAL RECAUDADO BS.:   " + df.format(Double.parseDouble(mtotal)), DATO_FONT_BOLD));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        table1.addCell(cell);

        document.add(table1);

        int NumColumns = 7;
        PdfPTable table = new PdfPTable(NumColumns);

        int[] fwidths = {5, 10, 15, 40, 10, 10, 10}; // percentage
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
        for (int i = 0; i < listaPago.size(); i++) {

            Pacientes dato = (Pacientes) listaPago.get(i);

            cell = new PdfPCell(new Phrase(Integer.toString(i + 1), CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setGrayFill(0.95f);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(Integer.toString(dato.getId_factura()), CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setGrayFill(0.95f);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(format(dato.getFec_registro(), "dd/MM/yyyy HH:mm"), CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setGrayFill(0.95f);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(dato.getNombres(), CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setGrayFill(0.95f);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(dato.getNit(), CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setGrayFill(0.95f);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(dato.getNum_cladoc(), CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setGrayFill(0.95f);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Bs.: " + df.format(dato.getPrecio_total()), CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setGrayFill(0.95f);
            table.addCell(cell);

            table1 = new PdfPTable(8);
            int[] fmpwidths = {5, 30, 15, 15, 5, 5, 5, 5}; // percentage
            table1.setWidths(fmpwidths);

            table1.setWidthPercentage(100);

            cell = new PdfPCell(new Phrase("No.", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setFixedHeight(20f);
            table1.addCell(cell);

            cell = new PdfPCell(new Phrase(datoItem.getMedicamento(), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setFixedHeight(20f);
            table1.addCell(cell);

            cell = new PdfPCell(new Phrase(datoItem.getCadena1(), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setFixedHeight(20f);
            table1.addCell(cell);

            cell = new PdfPCell(new Phrase(datoItem.getCadena2(), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setFixedHeight(20f);
            table1.addCell(cell);

            cell = new PdfPCell(new Phrase("Catidad", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setFixedHeight(20f);
            table1.addCell(cell);

            cell = new PdfPCell(new Phrase("Costo Unit.", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setFixedHeight(20f);
            table1.addCell(cell);

            cell = new PdfPCell(new Phrase("Precio Venta", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setFixedHeight(20f);
            table1.addCell(cell);

            cell = new PdfPCell(new Phrase("Total", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setFixedHeight(20f);
            table1.addCell(cell);

            for (int j = 0; j < listaMed.size(); j++) {

                Recetas datopre = (Recetas) listaMed.get(j);

                if (dato.getId_pedido() == datopre.getId_pedido()) {

                    cell = new PdfPCell(new Phrase(Integer.toString(j + 1), CABEZA_COLUMNA_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table1.addCell(cell);

                    cell = new PdfPCell(new Phrase(datopre.getMedicamento(), DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table1.addCell(cell);

                    cell = new PdfPCell(new Phrase(datopre.getForma_far(), DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table1.addCell(cell);

                    cell = new PdfPCell(new Phrase(datopre.getConcentra(), DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table1.addCell(cell);

                    cell = new PdfPCell(new Phrase(dfx.format(datopre.getSalida()), DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table1.addCell(cell);

                    cell = new PdfPCell(new Phrase(df.format(datopre.getCosto_unit()), DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    table1.addCell(cell);

                    cell = new PdfPCell(new Phrase(df.format(datopre.getPrecio_venta()), DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    table1.addCell(cell);

                    cell = new PdfPCell(new Phrase(df.format(datopre.getPrecio_total()), DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    table1.addCell(cell);
                    sumacostototal += (datopre.getSalida() * datopre.getCosto_unit());
                    tot += (datopre.getSalida() * datopre.getPrecio_venta());
                }
            }
            cell = new PdfPCell(table1);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(8);
            //  cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);
        }

        cell = new PdfPCell(new Phrase("TOTAL  Bs. Precio Venta", CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setFixedHeight(20f);
        cell.setGrayFill(0.95f);
        cell.setColspan(6);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(df.format(tot), CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setFixedHeight(20f);
        cell.setGrayFill(0.95f);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("TOTAL  Bs. Precio Costo", CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setFixedHeight(20f);
        cell.setGrayFill(0.95f);
        cell.setColspan(6);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(df.format(sumacostototal), CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setFixedHeight(20f);
        cell.setGrayFill(0.95f);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Diferencia (Ganancia) Bs.", CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setFixedHeight(20f);
        cell.setGrayFill(0.95f);
        cell.setColspan(6);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(df.format(tot - sumacostototal), CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setFixedHeight(20f);
        cell.setGrayFill(0.95f);
        table.addCell(cell);
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
