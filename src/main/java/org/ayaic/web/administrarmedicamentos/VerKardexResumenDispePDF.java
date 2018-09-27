package org.ayaic.web.administrarmedicamentos;

import java.awt.Color;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.view.document.AbstractPdfView;
import java.util.*;
import java.text.*;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Personas;
import org.ayaic.domain.Recetas;

public class VerKardexResumenDispePDF extends AbstractPdfView {

    private static final Font TITULO_FONT = new Font(Font.HELVETICA, 12, Font.BOLD, Color.black);
    private static final Font CABEZA_COLUMNA_FONT = new Font(Font.HELVETICA, 8, Font.ITALIC, Color.black);
    private static final Font DATO_FONT = new Font(Font.HELVETICA, 9, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_BOLD = new Font(Font.HELVETICA, 8, Font.BOLD, Color.black);
    private static final int MARGIN = 32;

    protected void buildPdfMetadata(Map model, Document document, HttpServletRequest request) {
        document.setPageSize(PageSize.LETTER.rotate());
        document.setMargins(20, 20, 30, 30);
    }

    protected void buildPdfDocument(Map model, Document document, PdfWriter writer, HttpServletRequest req, HttpServletResponse resp) throws Exception {

        java.util.List listaremi = (java.util.List) model.get("listarKardexRemi");

        Clientes dato = (Clientes) model.get("dato");
        Personas persona = (Personas) model.get("persona");
        String fecha = (String) model.get("dFechafin1");
        String fecha2 = (String) model.get("dFechafin2");
        // coloca el titulo de la pagina

        int[] sumas = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int[] sumbs = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        if (listaremi.size() == 0 || listaremi == null) {
            Paragraph pp = new Paragraph("No existe Datos", new Font(Font.HELVETICA, 22, Font.BOLDITALIC, new Color(0, 0, 0)));
            document.add(pp);
        }

        int una;

        if (listaremi.size() % 30 == 0) {
            una = 0;
        } else {
            una = 1;
        }

        for (int pag = 0; pag < listaremi.size() / 30 + una; pag++) {

            Image escudo = Image.getInstance("/opt/imagenes/lescudo.bmp");
            Paragraph p = new Paragraph("RESUMEN RECETAS DISPENSADAS", new Font(Font.HELVETICA, 14, Font.BOLDITALIC, new Color(0, 0, 0)));
            p.setAlignment(Element.ALIGN_CENTER);
            //Image sumi = Image.getInstance("/opt/imagenes/sumi.bmp");

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

            cell = new PdfPCell(new Phrase("\n                         Establecimiento : " + dato.getEstablecimiento(), DATO_FONT));
            cell.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell);

            cell = new PdfPCell(new Phrase("\nMunicipio : " + dato.getLocalidad(), DATO_FONT));
            cell.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell);

            cell = new PdfPCell(new Phrase("                        Fecha Reporte :   " + fecha.substring(8, 10) + " / " + fecha.substring(5, 7) + " / " + fecha.substring(0, 4) + "   al   " + fecha2.substring(8, 10) + " / " + fecha2.substring(5, 7) + " / " + fecha2.substring(0, 4), DATO_FONT));
            cell.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell);

            cell = new PdfPCell(new Phrase("Responsable   : " + persona.getNombres(), DATO_FONT));
            cell.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell);

            document.add(table1);

            int NumColumns = 16;
            PdfPTable table = new PdfPTable(NumColumns);
            int[] fwidths = {7, 80, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10};
            table.setWidths(fwidths);
            table.setWidthPercentage(100);

            String sCamposs[] = {"", "Rec. Externas", "Rec. Internacion", "Dosis unitaria", "Rec. SUMI", "Emerg. 303", "Emerg. 301", "TOTALES"};
            // coloca la cabecera de titulos

            for (int i = 0; i < 8; i++) {
                cell = new PdfPCell(new Phrase(sCamposs[i], CABEZA_COLUMNA_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setGrayFill(0.8f);
                cell.setColspan(2);
                table.addCell(cell);
            }

            String sCampos[] = {"Nro", "Nombres", "Recetas", "Cant.", "Recetas", "Cant.", "Recetas", "Cant.", "Recetas", "Cant.", "Recetas", "Cant.", "Recetas", "Cant.", "Recetas", "Cant."};
            // coloca la cabecera de titulos

            for (int i = 0; i < NumColumns; i++) {
                cell = new PdfPCell(new Phrase(sCampos[i], CABEZA_COLUMNA_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setGrayFill(0.8f);
                table.addCell(cell);
            }

            // coloca el detalle de loS datos
            for (int j = pag * 30 + 0; j < pag * 30 + 30 && j < listaremi.size(); j++) {
                Recetas datoremi = (Recetas) listaremi.get(j);

                cell = new PdfPCell(new Phrase(Integer.toString(j + 1), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(datoremi.getMedicamento() + "_" + datoremi.getForma_far(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((datoremi.getSuma1() == 0) ? "" : Integer.toString(datoremi.getSuma1()), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[0] += (datoremi.getSuma1());

                cell = new PdfPCell(new Phrase((datoremi.getSuma2() == 0) ? "" : Integer.toString(datoremi.getSuma2()), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[1] += (datoremi.getSuma2());

                cell = new PdfPCell(new Phrase((datoremi.getSuma3() == 0) ? "" : Integer.toString(datoremi.getSuma3()), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[2] += (datoremi.getSuma3());

                cell = new PdfPCell(new Phrase((datoremi.getSuma4() == 0) ? "" : Integer.toString(datoremi.getSuma4()), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[3] += (datoremi.getSuma4());

                cell = new PdfPCell(new Phrase((datoremi.getSuma5() == 0) ? "" : Integer.toString(datoremi.getSuma5()), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[4] += (datoremi.getSuma5());

                cell = new PdfPCell(new Phrase((datoremi.getSuma6() == 0) ? "" : Integer.toString(datoremi.getSuma6()), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[5] += (datoremi.getSuma6());

                cell = new PdfPCell(new Phrase((datoremi.getSuma7() == 0) ? "" : Integer.toString(datoremi.getSuma7()), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[6] += (datoremi.getSuma7());

                cell = new PdfPCell(new Phrase((datoremi.getSuma8() == 0) ? "" : Integer.toString(datoremi.getSuma8()), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[7] += (datoremi.getSuma8());

                cell = new PdfPCell(new Phrase((datoremi.getSuma9() == 0) ? "" : Integer.toString(datoremi.getSuma9()), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[8] += (datoremi.getSuma9());

                cell = new PdfPCell(new Phrase((datoremi.getSuma10() == 0) ? "" : Integer.toString(datoremi.getSuma10()), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[9] += (datoremi.getSuma10());

                cell = new PdfPCell(new Phrase((datoremi.getSuma11() == 0) ? "" : Integer.toString(datoremi.getSuma11()), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[10] += (datoremi.getSuma11());

                cell = new PdfPCell(new Phrase((datoremi.getSuma12() == 0) ? "" : Integer.toString(datoremi.getSuma12()), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[11] += (datoremi.getSuma12());

                cell = new PdfPCell(new Phrase((datoremi.getSuma1() + datoremi.getSuma3() + datoremi.getSuma5() + datoremi.getSuma7() + datoremi.getSuma9() + datoremi.getSuma11() == 0) ? "" : Integer.toString(datoremi.getSuma1() + datoremi.getSuma3() + datoremi.getSuma5() + datoremi.getSuma7() + datoremi.getSuma9() + datoremi.getSuma11()), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[12] += (datoremi.getSuma1() + datoremi.getSuma3() + datoremi.getSuma5() + datoremi.getSuma7() + datoremi.getSuma9() + datoremi.getSuma11());

                cell = new PdfPCell(new Phrase((datoremi.getSuma2() + datoremi.getSuma4() + datoremi.getSuma6() + datoremi.getSuma8() + datoremi.getSuma10() + datoremi.getSuma12() == 0) ? "" : Integer.toString(datoremi.getSuma2() + datoremi.getSuma4() + datoremi.getSuma6() + datoremi.getSuma8() + datoremi.getSuma10() + datoremi.getSuma12()), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[13] += (datoremi.getSuma2() + datoremi.getSuma4() + datoremi.getSuma6() + datoremi.getSuma8() + datoremi.getSuma10() + datoremi.getSuma12());
            }

            cell = new PdfPCell(new Phrase("TOTAL", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setColspan(2);
            table.addCell(cell);
            for (int j = 0; j < 14; j++) {
                cell = new PdfPCell(new Phrase(Integer.toString(sumas[j]), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
            }
            // llena datos vacios para poner las sumas

            cell = new PdfPCell(new Phrase("Elaborado por : " + dato.getNombres(), DATO_FONT));
            cell.setColspan(10);
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Fecha de emision : " + format(new Date(), "dd/MM/yyyy HH:mm"), DATO_FONT));
            cell.setColspan(6);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Pagina No.  " + (pag + 1), TITULO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(16);
            table.addCell(cell);

            //cod=format(fecha,"dd/MM/yyyy HH:mm")+'|'+dato.getCod_esta()+'|'+dato.getId_usuario()+'|'+ip.getHostAddress()+'|'+datopac.getCod_esta()+'|'+datopac.getId_historial()+'|'+datopac.getId_historia();
            //Image escudo = Image.getInstance("/opt/imagenes/lescudo.bmp");
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
