package org.ayaic.web.administrarmedicamentos;

import java.awt.Color;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.view.document.AbstractPdfView;
import java.util.*;
import java.text.*;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Personas;
import org.ayaic.domain.Recetas;

public class ListarKardexPsicoPDF extends AbstractPdfView {

    private static final Font TITULO_FONT = new Font(Font.HELVETICA, 12, Font.BOLD, Color.black);
    private static final Font CABEZA_COLUMNA_FONT = new Font(Font.HELVETICA, 8, Font.ITALIC, Color.black);
    private static final Font DATO_FONT = new Font(Font.HELVETICA, 8, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_B = new Font(Font.HELVETICA, 6, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_BOLD = new Font(Font.HELVETICA, 8, Font.BOLD, Color.black);
    private static final int MARGIN = 32;

    protected void buildPdfMetadata(Map model, Document document, HttpServletRequest request) {
        document.setPageSize(PageSize.LETTER.rotate());
        document.setMargins(20, 20, 20, 20);
    }

    protected void buildPdfDocument(Map model, Document document, PdfWriter writer, HttpServletRequest req, HttpServletResponse resp) throws Exception {

        java.util.List kardexMed = (java.util.List) model.get("datokardex");
        Clientes dato = (Clientes) model.get("cliente");
        Personas persona = (Personas) model.get("persona");
        String fecha = (String) model.get("dFechafin1");
        String fecha2 = (String) model.get("dFechafin2");
        DecimalFormat df = new DecimalFormat("###,##0");
        String fechaq = (String) model.get("fecha");
        String tipo;
        String sCampos[] = {"Nro", "Fecha", "Patronal", "Matricula", "Nombre Paciente", "C.I.", "Telefono", "Direccion", "Codigo\nMed", "Medicamento", "Forma\nfar", "Cantidad", "Prescriptor"};

        float sumatotal = 0;
        int sumacant = 0;

        if (kardexMed.size() == 0) {
            Paragraph p = new Paragraph("No existe Datos", new Font(Font.HELVETICA, 22, Font.BOLDITALIC, new Color(0, 0, 0)));
            document.add(p);
        }

        int una;
        if (kardexMed.size() % 35 == 0) {
            una = 0;
        } else {
            una = 1;
        }

        for (int pag = 0; pag < kardexMed.size() / 35 + una; pag++) {

            Image escudo = Image.getInstance("/opt/imagenes/lescudo.bmp");
            Paragraph p = new Paragraph("Detalle Medicamentos Restringidos", new Font(Font.HELVETICA, 14, Font.BOLDITALIC, new Color(0, 0, 0)));
            p.setAlignment(Element.ALIGN_CENTER);

            PdfPCell cell;

            PdfPTable tablex = new PdfPTable(3);
            int[] fxwidths = {15, 70, 15}; // percentage
            tablex.setWidths(fxwidths);
            tablex.setWidthPercentage(100);

            cell = new PdfPCell(new Phrase("", DATO_FONT));
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

            cell = new PdfPCell(new Phrase("\n                          Establecimiento : " + dato.getEstablecimiento(), DATO_FONT));
            cell.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell);

            cell = new PdfPCell(new Phrase("\nMunicipio : " + dato.getLocalidad(), DATO_FONT));
            cell.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell);

            cell = new PdfPCell(new Phrase("                          Fecha Reporte :   " + fecha.substring(8, 10) + " / " + fecha.substring(5, 7) + " / " + fecha.substring(0, 4) + "   al   " + fecha2.substring(8, 10) + " / " + fecha2.substring(5, 7) + " / " + fecha2.substring(0, 4), DATO_FONT));
            cell.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell);

            cell = new PdfPCell(new Phrase("Responsable   : " + persona.getNombres(), DATO_FONT));
            cell.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell);

            document.add(table1);

            int NumColumns = 13;
            PdfPTable table = new PdfPTable(NumColumns);
            int[] fwidths = {12, 35, 23, 26, 55, 20, 20, 50, 15, 42, 13, 10, 45}; // percentage
            table.setWidths(fwidths);
            table.setWidthPercentage(100);

            // coloca la cabecera de titulos
            for (int i = 0; i < sCampos.length; i++) {
                cell = new PdfPCell(new Phrase(sCampos[i], CABEZA_COLUMNA_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setGrayFill(0.8f);
                table.addCell(cell);
            }

            table.setHeaderRows(1);

            for (int i = pag * 35 + 0; i < pag * 35 + 35 && i < kardexMed.size(); i++) {

                Recetas dator = (Recetas) kardexMed.get(i);

                cell = new PdfPCell(new Phrase(Integer.toString(i + 1), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(format(dator.getFecha(), "dd/MM/yy HH:mm:ss"), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(dator.getCadena6(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(dator.getCadena5(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((dator.getCadena1().length() > 27) ? dator.getCadena1().substring(0, 27) : dator.getCadena1(), DATO_FONT_B));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(dator.getCadena2(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(dator.getCadena3(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((dator.getCadena4().length() > 25) ? dator.getCadena4().substring(0, 25) : dator.getCadena4(), DATO_FONT_B));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(dator.getCodsumi(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((dator.getMedicamento().length() > 22) ? dator.getMedicamento().substring(0, 22) : dator.getMedicamento(), DATO_FONT_B));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((dator.getForma_far().length() > 3) ? dator.getForma_far().substring(0, 3) : dator.getForma_far(), DATO_FONT_B));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(df.format(dator.getSalida()), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((dator.getCadena7().length() > 22) ? dator.getCadena7().substring(0, 22) : dator.getCadena7(), DATO_FONT_B));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

            }

            cell = new PdfPCell(new Phrase("Elaborado por : " + dato.getNombres(), DATO_FONT));
            cell.setColspan(8);
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Fecha de emision : " + format(new Date(), "dd/MM/yyyy HH:mm"), DATO_FONT));
            cell.setColspan(5);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Pagina No.  " + (pag + 1), TITULO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(13);
            table.addCell(cell);

            //cod=format(fecha,"dd/MM/yyyy HH:mm")+'|'+dato.getCod_esta()+'|'+dato.getId_usuario()+'|'+ip.getHostAddress()+'|'+datopac.getCod_esta()+'|'+datopac.getId_historial()+'|'+datopac.getId_historia();
            PdfContentByte cb = writer.getDirectContent();
            //PdfContentByte cb1 = writer.getDirectContent();
            //BarcodeQRCode qrcode = new BarcodeQRCode(cod, 90, 90, null);
            //java.awt.Image qrImage = qrcode.createAwtImage(Color.black, Color.white);
            //Image finalImage = Image.getInstance(writer, escudo, 1);
            escudo.setAbsolutePosition(50, 50);
            cb.addImage(escudo, 50, 0, 0, 60, 20, 535, true);
            ///(imagen,tamañoAncho ,rotation_x ,angulorotation , amplitud-altura ,TamañoAlto ,PosicionX ,PosicionY(0=abajo, 280=arriba))

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
