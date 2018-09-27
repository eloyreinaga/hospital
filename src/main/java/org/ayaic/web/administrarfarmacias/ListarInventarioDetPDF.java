package org.ayaic.web.administrarfarmacias;

import java.awt.Color;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.view.document.AbstractPdfView;
import java.util.*;
import java.text.*;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Medicamentos;

public class ListarInventarioDetPDF extends AbstractPdfView {

    private static final Font TITULO_FONT = new Font(Font.HELVETICA, 12, Font.BOLD, Color.black);
    private static final Font CABEZA_COLUMNA_FONT = new Font(Font.HELVETICA, 8, Font.ITALIC, Color.black);
    private static final Font DATO_FONT = new Font(Font.HELVETICA, 7, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_P = new Font(Font.HELVETICA, 6, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_BOLD = new Font(Font.HELVETICA, 8, Font.BOLD, Color.black);
    private static final int MARGIN = 32;

    protected void buildPdfMetadata(Map model, Document document, HttpServletRequest request) {
        document.setPageSize(PageSize.LETTER);
        document.setMargins(20, 20, 10, 10);
    }

    protected void buildPdfDocument(Map model, Document document, PdfWriter writer, HttpServletRequest req, HttpServletResponse resp) throws Exception {

        java.util.List listImm = (java.util.List) model.get("listarDatosImm");

        Clientes dato = (Clientes) model.get("dato");
        String mes = (String) model.get("mes");
        String gestion = (String) model.get("gestion");
        String fecha = (String) model.get("fecha");
        DecimalFormat df = new DecimalFormat("###,##0");
        DecimalFormat dfx = new DecimalFormat("###,##0.0");

        // coloca el titulo de la pagina
        int una;
        float sumatotal = 0, sumatotal2 = 0;
        if (listImm.size() % 50 == 0) {
            una = 0;
        } else {
            una = 1;
        }

        for (int pag = 0; pag < listImm.size() / 50 + una; pag++) {

            Image escudo = Image.getInstance("/opt/imagenes/lescudo.bmp");
            Paragraph p = new Paragraph("INVENTARIO FISICO VALORADO \n A UN AFECHA DADA", new Font(Font.HELVETICA, 14, Font.BOLDITALIC, new Color(0, 0, 0)));
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
            cell.setVerticalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            tablex.addCell(cell);
            cell = new PdfPCell(new Phrase("", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setBorder(Rectangle.NO_BORDER);
            tablex.addCell(cell);

            PdfPTable table1 = new PdfPTable(3);
            int[] fmwidths = {35, 35, 35}; // percentage
            table1.setWidths(fmwidths);
            table1.setWidthPercentage(100);

            cell = new PdfPCell(new Phrase("Servicio Departamental de Salud : " + dato.getDepartamento(), DATO_FONT));
            cell.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell);

            cell = new PdfPCell(new Phrase("Municipio : " + dato.getLocalidad(), DATO_FONT));
            cell.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell);

            cell = new PdfPCell(new Phrase("Establecimiento : " + dato.getEstablecimiento(), DATO_FONT));
            cell.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell);

            cell = new PdfPCell(new Phrase("Responsable : " + dato.getNombres(), DATO_FONT));
            cell.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell);

            cell = new PdfPCell(new Phrase("Red : " + dato.getRed(), DATO_FONT));
            cell.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell);

            cell = new PdfPCell(new Phrase("Inventario al : " + fecha + "                 " + "Año : " + gestion, DATO_FONT));
            cell.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell);

            int NumColumns = 13;
            PdfPTable table = new PdfPTable(NumColumns);
            int[] fwidths = {5, 5, 15, 22, 9, 8, 7, 7, 7, 9, 7, 7, 7};
            table.setWidths(fwidths);
            table.setWidthPercentage(100);

            String sCampos[] = {"No", "No", "LINEA", "MEDICAMENTO\nE INSUMOS", "Forma\nFarmaceutica", "Concen-\ntracion", "Precio\nCosto", "Precio\nVenta", "Fecha\nVencim.", "LOTE", "SALDO", "SALDO\nFISICO", "Valorado\nCosto"};
            cell = new PdfPCell(tablex);
            cell.setColspan(13);
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);

            cell = new PdfPCell(table1);
            cell.setColspan(13);
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);

            // coloca la cabecera de titulos
            for (int i = 0; i < 10; i++) {
                cell = new PdfPCell(new Phrase(sCampos[i], CABEZA_COLUMNA_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);

                table.addCell(cell);
            }

            cell = new PdfPCell(cuadrotitulo("SALDOS"));
            cell.setColspan(3);
            table.addCell(cell);

            // coloca el detalle de loS datos
            table.setHeaderRows(1);
            table.setHeaderRows(2);
            table.setHeaderRows(3);
            for (int i = pag * 50 + 0; i < pag * 50 + 50 && i < listImm.size(); i++) {

                Medicamentos datosM = (Medicamentos) listImm.get(i);

                //cell = new PdfPCell(new Phrase(Integer.toString(datosM.getId_medicamento()), DATO_FONT));
                cell = new PdfPCell(new Phrase(Integer.toString(i + 1), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(Integer.toString(datosM.getId_medicamento()), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((datosM.getUbicacion().length() > 15) ? datosM.getUbicacion().substring(0, 15) : datosM.getUbicacion(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((datosM.getMedicamento().length() > 20) ? datosM.getMedicamento().substring(0, 20) : datosM.getMedicamento(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((datosM.getForma_far().length() > 9) ? datosM.getForma_far().substring(0, 9) : datosM.getForma_far(), DATO_FONT_P));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((datosM.getConcentra().length() > 9) ? datosM.getConcentra().substring(0, 9) : datosM.getConcentra(), DATO_FONT_P));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(dfx.format(datosM.getCosto_unit()), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(dfx.format(datosM.getPrecio_venta()), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(format(datosM.getFecha_ven(), "MM/yy"), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((datosM.getNro_lote().length() > 9) ? datosM.getNro_lote().substring(0, 9) : datosM.getNro_lote(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(df.format(datosM.getSumab2()), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(dfx.format((datosM.getSumab2()) * datosM.getCosto_unit()), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(dfx.format((datosM.getSumab2()) * datosM.getPrecio_venta()), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(cell);

                sumatotal += (datosM.getSumab2()) * datosM.getCosto_unit();
                sumatotal2 += (datosM.getSumab2()) * datosM.getPrecio_venta();

            }
            for (int i = listImm.size(); i < pag * 40 + 50; i++) {
                cell = new PdfPCell(new Phrase(Integer.toString(i + 1), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(cell);
                for (int j = 1; j < 13; j++) {
                    cell = new PdfPCell(new Phrase("", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);
                }
            }
            cell = new PdfPCell(new Phrase("TOTAL INVENTARIO", DATO_FONT_BOLD));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setColspan(11);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(dfx.format(sumatotal), DATO_FONT_BOLD));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(dfx.format(sumatotal2), DATO_FONT_BOLD));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Observaciones : ___________________________________________________________________________", DATO_FONT));
            cell.setColspan(13);
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Elaborado por : " + dato.getNombres(), DATO_FONT));
            cell.setColspan(13);
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Fecha de emision : " + format(new Date(), "dd/MM/yyyy"), DATO_FONT));
            cell.setColspan(13);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Firma : _________________________________________", DATO_FONT));
            cell.setColspan(13);
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Pagina No.  " + (pag + 1), TITULO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(13);
            table.addCell(cell);

            document.add(table);
            document.newPage();
        }
    }

    public static PdfPTable cuadrotitulo(String letra) {
        // la primera fila

        PdfPCell cell;
        PdfPTable table1 = new PdfPTable(3);
        table1.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase(letra, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(3);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("SALDO", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1.addCell(cell);
        cell = new PdfPCell(new Phrase("VAL\nCOSTO", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1.addCell(cell);
        cell = new PdfPCell(new Phrase("VAL\nVENTA", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1.addCell(cell);
        return table1;
    }

    public static String format(Date dia, String formato) {
        if (dia == null) {
            return "";
        }
        SimpleDateFormat formatDate = new SimpleDateFormat(formato);
        return formatDate.format(dia);
    }
}
