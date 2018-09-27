package org.ayaic.web.administrarhistoriales;

import com.itextpdf.text.pdf.BarcodeQRCode;
import java.awt.Color;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.view.document.AbstractPdfView;
import java.util.*;
import java.text.*;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Historiales;
import org.ayaic.domain.Localidades;
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.Personas;
import org.ayaic.domain.Recetas;
import org.ayaic.domain.Seguros;

public class ListarRecetaCajaPDF extends AbstractPdfView {

    private static final Font TITULO_FONT = new Font(Font.HELVETICA, 11, Font.BOLD, Color.black);
    private static final Font CABEZA_COLUMNA_FONT = new Font(Font.HELVETICA, 8, Font.ITALIC, Color.black);
    private static final Font DATO_FONT = new Font(Font.HELVETICA, 7, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_B = new Font(Font.HELVETICA, 7, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_D = new Font(Font.HELVETICA, 15, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_A = new Font(Font.COURIER, 10, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_BOLD = new Font(Font.HELVETICA, 8, Font.BOLD, Color.black);
    private static final Font marcagua = new Font(Font.HELVETICA, 30, Font.BOLD, Color.lightGray);
    private static final int MARGIN = 32;

    protected void buildPdfMetadata(Map model, Document document, HttpServletRequest request) {
        document.setPageSize(PageSize.A5);
        document.setMargins(50, 30, 10, 40);
    }

    protected void buildPdfDocument(Map model, Document document, PdfWriter writer, HttpServletRequest req, HttpServletResponse resp) throws Exception {

        java.util.List listaRecetas = (java.util.List) model.get("listarRecetas");
        java.util.List listaPres = (java.util.List) model.get("listarPres");
        java.util.List listaSeg = (java.util.List) model.get("listarSeguros");

        Historiales datoHis = (Historiales) model.get("datosHis");
        Localidades datoestab = (Localidades) model.get("datoestab");
        String idhistorial = (String) model.get("idhistorial");
        Personas datoMed = (Personas) model.get("datosMed");
        Pacientes datoPac = (Pacientes) model.get("datosPac");
        Clientes dato = (Clientes) model.get("dato");
        String cod = (String) model.get("cod");
        DecimalFormat df = new DecimalFormat("###,##0");

        Paragraph p = new Paragraph("RECETA MEDICA", new Font(Font.HELVETICA, 18, Font.BOLDITALIC, new Color(0, 0, 0)));
        p.setAlignment(Element.ALIGN_CENTER);

        if (listaRecetas.size() == 0) {
            Paragraph p2 = new Paragraph("No existe Datos", new Font(Font.HELVETICA, 22, Font.BOLDITALIC, new Color(0, 0, 0)));
            document.add(p2);
        }

        PdfPCell cell;

        for (int i = 0; i < listaRecetas.size(); i++) {

            Recetas datoReceta = (Recetas) listaRecetas.get(i);

            PdfPTable tablex = new PdfPTable(4);
            int[] fxwidths = {50, 20, 15, 15}; // percentage
            tablex.setWidths(fxwidths);
            tablex.setWidthPercentage(100);

            cell = new PdfPCell(new Phrase(datoestab.getNombreseg() + "", TITULO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            tablex.addCell(cell);

            cell = new PdfPCell(new Phrase("FOLIO:......     Form DM 301", TITULO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(3);
            tablex.addCell(cell);

            cell = new PdfPCell(new Phrase("\n\n\n", TITULO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(4);
            tablex.addCell(cell);///////////esta es la pagina blanca

            cell = new PdfPCell(p);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            tablex.addCell(cell);

            cell = new PdfPCell(new Phrase("No. :     ", DATO_FONT_D));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(3);
            tablex.addCell(cell);

            cod = idhistorial + '|' + Integer.toString(datoHis.getCod_esta()) + '|' + format(new Date(), "dd/MM/yyyy HH:mm:ss") + '|' + datoMed.getCodigoprof() + '|' + datoHis.getId_historial() + '|' + datoReceta.getId_medicamento() + '|' + datoReceta.getSalida() + '|' + datoReceta.getNum_cladoc();

            PdfContentByte cb = writer.getDirectContent();
            BarcodeQRCode qrcode = new BarcodeQRCode(cod, 90, 90, null);
            java.awt.Image qrImage = qrcode.createAwtImage(Color.black, Color.white);
            Image finalImage = Image.getInstance(writer, qrImage, 1);

            cell = new PdfPCell(finalImage);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setBorder(Rectangle.NO_BORDER);
            tablex.addCell(cell);

            PdfPTable table51 = new PdfPTable(3);
            int[] f5widths = {33, 33, 33};
            table51.setWidths(f5widths);
            table51.setWidthPercentage(100);

            cell = new PdfPCell(new Phrase("Clave Farmaceutico", TITULO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(3);
            table51.addCell(cell);

            cell = new PdfPCell(new Phrase(format(new Date(), "dd/MM/yyyy") + "  " + format(new Date(), "HH:mm"), TITULO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(3);
            table51.addCell(cell);

            if ("P".equals(datoHis.getExpedido())) {
                for (int v = 0; v < listaSeg.size(); v++) {
                    Seguros datoSeg = (Seguros) listaSeg.get(v);
                    if (datoHis.getId_seguro() == datoSeg.getId_seguro()) {
                        cell = new PdfPCell(new Phrase("Fecha\n\nSeguro: " + datoSeg.getSeguro() + "  pol:" + datoPac.getId_policlinico(), DATO_FONT));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setBorder(Rectangle.NO_BORDER);
                        cell.setColspan(3);
                        table51.addCell(cell);
                    }
                }
            } else {
                if ("S".equals(datoHis.getExpedido())) {
                    cell = new PdfPCell(new Phrase("Fecha\n\nSeguro: Ley475 (Sumi)", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBorder(Rectangle.NO_BORDER);
                    cell.setColspan(3);
                    table51.addCell(cell);
                } else {
                    cell = new PdfPCell(new Phrase("Fecha\n\nSeguro: Externo", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBorder(Rectangle.NO_BORDER);
                    cell.setColspan(3);
                    table51.addCell(cell);
                }
            }

            cell = new PdfPCell(table51);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(3);
            tablex.addCell(cell);

            if (datoPac.getNro_registro().length() > 6) {
                cell = new PdfPCell(new Phrase(datoPac.getNro_registro().substring(0, 6) + " - " + datoPac.getNro_registro().substring(6, 9) + "      Cod :  " + datoPac.getNro(), TITULO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tablex.addCell(cell);
            } else {
                cell = new PdfPCell(new Phrase(datoPac.getNro_registro() + "      Cod :  " + datoPac.getNro(), TITULO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tablex.addCell(cell);
            }

            cell = new PdfPCell(new Phrase("\n", TITULO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablex.addCell(cell);

            cell = new PdfPCell(new Phrase("\n", TITULO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(2);
            tablex.addCell(cell);

            cell = new PdfPCell(new Phrase("No ASEGURADO", DATO_FONT_A));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            tablex.addCell(cell);

            cell = new PdfPCell(new Phrase("Clave Unidad\nSanitaria", DATO_FONT_A));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            tablex.addCell(cell);

            cell = new PdfPCell(new Phrase("Clave\nFarmacia", DATO_FONT_A));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(2);
            tablex.addCell(cell);

            cell = new PdfPCell(new Phrase("\n\n", TITULO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(4);
            tablex.addCell(cell);///////////esta es la pagina blanca

            cell = new PdfPCell(new Phrase(datoPac.getPaterno() + "            " + datoPac.getMaterno() + "           " + datoPac.getNombre(), TITULO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(4);
            tablex.addCell(cell);

            cell = new PdfPCell(new Phrase("--------------------------------------------------------------------------", TITULO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(4);
            tablex.addCell(cell);

            cell = new PdfPCell(new Phrase("Apellido Paterno                Apellido Materno                             Nombres                                 ", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(4);
            tablex.addCell(cell);

            cell = new PdfPCell(new Phrase("\n__________________________________________\n", TITULO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(4);
            tablex.addCell(cell);

            cell = new PdfPCell(new Phrase("\n", TITULO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(4);
            tablex.addCell(cell);///////////esta es la pagina blanca

            cell = new PdfPCell(new Phrase("Rp./        " + datoReceta.getMedicamento() + "   {" + datoReceta.getForma_far() + "}   " + datoReceta.getConcentra(), TITULO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(4);
            tablex.addCell(cell);

            cell = new PdfPCell(new Phrase("Cantidad   :  " + datoReceta.getSalida() + "     Unid.", TITULO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(2);
            tablex.addCell(cell);

            cell = new PdfPCell(new Phrase("No Codigo Vademecum", TITULO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(2);
            tablex.addCell(cell);

            cell = new PdfPCell(new Phrase("", TITULO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(2);
            tablex.addCell(cell);

            cell = new PdfPCell(new Phrase(datoReceta.getNum_cladoc().substring(0, 1) + " - " + datoReceta.getNum_cladoc().substring(1, 3) + " - " + datoReceta.getNum_cladoc().substring(3, 5), TITULO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(2);
            tablex.addCell(cell);

            cell = new PdfPCell(new Phrase("\n\n\n", TITULO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(4);
            tablex.addCell(cell);///////////esta es la pagina blanca

            cell = new PdfPCell(new Phrase("Firma Medico", TITULO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            tablex.addCell(cell);

            cell = new PdfPCell(new Phrase("Clave Medico", TITULO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(3);
            tablex.addCell(cell);

            cell = new PdfPCell(new Phrase("\n\nModo de usarse    :     " + datoReceta.getIndicacion(), TITULO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setFixedHeight(80f);
            cell.setColspan(4);
            tablex.addCell(cell);

            document.add(tablex);

            if (datoMed.getUrgencias() == 1) {
                ColumnText.showTextAligned(writer.getDirectContentUnder(), Element.ALIGN_RIGHT,
                        new Paragraph("URGENCIAS", marcagua), 70, 400, 90);    ///marca de agua    
            } else {
                ColumnText.showTextAligned(writer.getDirectContentUnder(), Element.ALIGN_RIGHT,
                        new Paragraph(dato.getConsultorio().toUpperCase(), marcagua), 70, 400, 90);    ///marca de agua
            }

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
