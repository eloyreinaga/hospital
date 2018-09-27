package org.ayaic.web.administrarhistoriales.ambulatorio;

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

public class VerReporteSpam1PDF extends AbstractPdfView {

    private static final Font TITULO_FONT = new Font(Font.HELVETICA, 12, Font.BOLD, Color.black);
    private static final Font CABEZA_COLUMNA_FONT = new Font(Font.HELVETICA, 8, Font.ITALIC, Color.black);
    private static final Font DATO_FONT = new Font(Font.HELVETICA, 8, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_BOLD = new Font(Font.HELVETICA, 8, Font.BOLD, Color.black);
    private static final int MARGIN = 32;

    protected void buildPdfMetadata(Map model, Document document, HttpServletRequest request) {
        document.setPageSize(PageSize.LETTER);
        document.setMargins(20, 20, 10, 10);
    }

    protected void buildPdfDocument(Map model, Document document, PdfWriter writer, HttpServletRequest req, HttpServletResponse resp) throws Exception {

        java.util.List lFopos = (java.util.List) model.get("milistaAten");
        HeaderFooter footer = new HeaderFooter(new Phrase("Pagina No. "), new Phrase("."));

        footer.setAlignment(Element.ALIGN_CENTER);
        footer.setBorder(Rectangle.NO_BORDER);
        document.setFooter(footer);
        String fec1 = (String) model.get("fecha1");
        String fec2 = (String) model.get("fecha2");
        String seguro = (String) model.get("seguro");

        Clientes dato = (Clientes) model.get("dato");
        // coloca el titulo de la pagina
        Image escudo = Image.getInstance("/opt/imagenes/lescudo.bmp");
        Paragraph p = new Paragraph("LISTADO DE PACIENTES ATENDIDOS", new Font(Font.HELVETICA, 14, Font.BOLDITALIC, new Color(0, 0, 0)));
        p.setAlignment(Element.ALIGN_CENTER);
        //Image sumi = Image.getInstance("/opt/imagenes/sumi.bmp");
        DecimalFormat df = new DecimalFormat("###,##0.00");
        DecimalFormat dfx = new DecimalFormat("###,##0");
        float sumatotal[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

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
        cell = new PdfPCell(new Phrase(seguro, TITULO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(3);
        tablex.addCell(cell);

        document.add(tablex);

        PdfPTable table1 = new PdfPTable(2);
        int[] fmwidths = {50, 50}; // percentage
        table1.setWidths(fmwidths);
        table1.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("INFORME ECONOMICO", TITULO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(2);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("Establecimiento : " + dato.getEstablecimiento(), DATO_FONT));
        cell.setBorder(Rectangle.NO_BORDER);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("Municipio : " + dato.getLocalidad(), DATO_FONT));
        cell.setBorder(Rectangle.NO_BORDER);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("Red : " + dato.getRed(), DATO_FONT));
        cell.setBorder(Rectangle.NO_BORDER);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("SEDES : " + dato.getDepartamento() + "      Localidad:....................", DATO_FONT));
        cell.setBorder(Rectangle.NO_BORDER);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("Mes : " + fec2.substring(3, 5) + "         " + "Año : " + fec2.substring(6, 10), DATO_FONT));
        cell.setBorder(Rectangle.NO_BORDER);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("Desde fecha :  " + fec1 + "  a  :  " + fec2, DATO_FONT));
        cell.setBorder(Rectangle.NO_BORDER);
        table1.addCell(cell);

        document.add(table1);

        int NumColumns = 16;
        PdfPTable table = new PdfPTable(NumColumns);
        int[] fwidths = {7, 14, 45, 19, 6, 11, 10, 10, 10, 10, 10, 10, 10, 10, 10, 13};
        table.setWidths(fwidths);
        table.setWidthPercentage(100);

        String sCampos[] = {"No", "FECHA", "NOMBRE DEL PACIENTE", "No SEGURO", "Edad", "Farmacia\n[Bs]", "Consultas\n[Bs]", "Dental\n[Bs]", "Serv.Med\n[Bs]", "Enf.yEmerg.\n[Bs]", "Laboratorios\n[Bs]", "Imagenologia\n[Bs]", "Otro Serv.\n[Bs]", "Pago\nDeuda", "Factura\nPagada", "TOTAL\n[Bs]"};
        // coloca la cabecera de titulos
        for (int i = 0; i < NumColumns; i++) {
            cell = new PdfPCell(new Phrase(sCampos[i], CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setGrayFill(0.8f);
            table.addCell(cell);
        }
        double num = 0;
        // coloca el detalle de loS datos
        for (int i = 0; i < lFopos.size(); i++) {

            Historiales datopac = (Historiales) lFopos.get(i);

            cell = new PdfPCell(new Phrase(Integer.toString(i + 1), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(format(datopac.getFecha(), "dd/MM/yy"), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(datopac.getNombres(), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(datopac.getNro_registro(), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(Integer.toString(datopac.getEdad()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(df.format((datopac.getPeso())), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);
            sumatotal[0] += (datopac.getPeso());

            cell = new PdfPCell(new Phrase(dfx.format((datopac.getSuma2())), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);
            sumatotal[1] += (datopac.getSuma2());

            cell = new PdfPCell(new Phrase(dfx.format((datopac.getSuma3())), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);
            sumatotal[2] += (datopac.getSuma3());

            cell = new PdfPCell(new Phrase(dfx.format((datopac.getSuma4())), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);
            sumatotal[3] += (datopac.getSuma4());

            cell = new PdfPCell(new Phrase(dfx.format((datopac.getSuma5())), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);
            sumatotal[4] += (datopac.getSuma5());

            cell = new PdfPCell(new Phrase(dfx.format((datopac.getSuma6())), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);
            sumatotal[5] += (datopac.getSuma6());

            cell = new PdfPCell(new Phrase(dfx.format((datopac.getSuma7())), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);
            sumatotal[6] += (datopac.getSuma7());

            cell = new PdfPCell(new Phrase(dfx.format((datopac.getSuma8())), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);
            sumatotal[7] += (datopac.getSuma8());

            cell = new PdfPCell(new Phrase(dfx.format((datopac.getSuma1())), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);
            sumatotal[8] += (datopac.getSuma1());

            cell = new PdfPCell(new Phrase(dfx.format((datopac.getTemperatura())), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);
            sumatotal[9] += (datopac.getTemperatura());

            cell = new PdfPCell(new Phrase(df.format((datopac.getPeso() + datopac.getSuma8() + datopac.getSuma7() + datopac.getSuma6() + datopac.getSuma5() + datopac.getSuma4() + datopac.getSuma3() + datopac.getSuma2())), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);
            sumatotal[10] += (datopac.getPeso() + datopac.getSuma8() + datopac.getSuma7() + datopac.getSuma6() + datopac.getSuma5() + datopac.getSuma4() + datopac.getSuma3() + datopac.getSuma2());

            num = num + datopac.getSuma8() + datopac.getSuma7() + datopac.getSuma6() + datopac.getSuma5() + datopac.getSuma4() + datopac.getSuma3() + datopac.getSuma2() + datopac.getPeso();
        }

        cell = new PdfPCell(new Phrase("SUMAS PARCIALES", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setColspan(5);
        table.addCell(cell);

        for (int i = 0; i < 11; i++) {
            cell = new PdfPCell(new Phrase(df.format(sumatotal[i]), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setGrayFill(0.9f);
            table.addCell(cell);
        }

        cell = new PdfPCell(new Phrase("TOTAL [Bs.]", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setColspan(14);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(df.format(num), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setColspan(2);
        table.addCell(cell);

        document.add(table);

        p = new Paragraph("Fecha de emision : " + format(new Date(), "dd/MM/yyyy"), DATO_FONT);
        document.add(p);
        p = new Paragraph("Nombre completo responsable del llenado : " + dato.getNombres() + "         Firma:_______________________", DATO_FONT);
        document.add(p);

        p = new Paragraph("El arriba firmante certifica la veracidad de la informacion declarada en este documento legal.", DATO_FONT);
        document.add(p);

    }

    public static String format(Date dia, String formato) {
        if (dia == null) {
            return "";
        }
        SimpleDateFormat formatDate = new SimpleDateFormat(formato);
        return formatDate.format(dia);
    }
}
