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

public class ListarPacientesSumiPDF extends AbstractPdfView {

    private static final Font TITULO_FONT = new Font(Font.HELVETICA, 14, Font.BOLD, Color.black);
    private static final Font CABEZA_COLUMNA_FONT = new Font(Font.HELVETICA, 10, Font.ITALIC, Color.black);
    private static final Font DATO_FONT = new Font(Font.HELVETICA, 11, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_BOLD = new Font(Font.HELVETICA, 8, Font.BOLD, Color.black);
    private static final int MARGIN = 32;

    protected void buildPdfMetadata(Map model, Document document, HttpServletRequest request) {
        document.setPageSize(PageSize.LETTER);
        document.setMargins(10, 10, 10, 10);
    }

    protected void buildPdfDocument(Map model, Document document, PdfWriter writer, HttpServletRequest req, HttpServletResponse resp) throws Exception {

        java.util.List lFopos = (java.util.List) model.get("milistaSumi");
        Clientes dato = (Clientes) model.get("dato");
        String anio2 = (String) model.get("anio2");
        String mes2 = (String) model.get("mes2");

        // coloca el titulo de la pagina
        Image escudo = Image.getInstance("/opt/imagenes/lescudo.bmp");
        Paragraph p = new Paragraph("INFORME PRESTACIONES SUMI", new Font(Font.HELVETICA, 14, Font.BOLDITALIC, new Color(0, 0, 0)));
        String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        p.setAlignment(Element.ALIGN_CENTER);

        PdfPCell cell;

        //AGREGAMOS TITULO
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

        cell = new PdfPCell(escudo);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(3);
        tablex.addCell(cell);

        document.add(tablex);

        PdfPTable table1 = new PdfPTable(4);
        int[] fmwidths = {30, 10, 15, 45}; // percentage
        table1.setWidths(fmwidths);
        table1.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("Mes : " + meses[Integer.parseInt(mes2) - 1], DATO_FONT));
        cell.setBorder(Rectangle.NO_BORDER);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("Año : " + anio2, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(Rectangle.NO_BORDER);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("ESTABLECIMIENTO:  " + dato.getEstablecimiento(), DATO_FONT_BOLD));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        table1.addCell(cell);

        document.add(table1);

        int[] sumas = {0, 0, 0, 0, 0, 0, 0, 0};
        int NumColumns = 9;
        PdfPTable table = new PdfPTable(NumColumns);
        int una, filas = 30;

        if (lFopos.size() == 0) {
            Paragraph p1 = new Paragraph("No existe Datos", new Font(Font.HELVETICA, 22, Font.BOLDITALIC, new Color(0, 0, 0)));
            document.add(p1);
        }

        int[] fwidths = {10, 10, 10, 10, 10, 10, 10, 10, 10}; // percentage
        table.setWidths(fwidths);
        table.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("FECHA", DATO_FONT_BOLD));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.8f);
        table.addCell(cell);

        addtitulofila3(table, "ATENCION A LA MUJER EMBARAZADA", "CPN", "PUERPERIO", "PARTO", "A. MEDICA", "A. ODONTOLO");

        addtitulofila33(table, "ATENCION < DE 5 añoS", "A MEDICA", "A. ODONTO");

        cell = new PdfPCell(new Phrase("TOTAL", DATO_FONT_BOLD));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.8f);
        table.addCell(cell);

        for (int i = 0; i < lFopos.size(); i++) {

            Historiales datoh = (Historiales) lFopos.get(i);

            cell = new PdfPCell(new Phrase(format(datoh.getFecha(), "dd/MM/yyyy"), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(Integer.toString(datoh.getId_paciente()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            sumas[0] += (datoh.getId_paciente());

            cell = new PdfPCell(new Phrase(Integer.toString(datoh.getEdad_ini()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            sumas[1] += (datoh.getEdad_ini());

            cell = new PdfPCell(new Phrase(Integer.toString(datoh.getEdad_fin()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            sumas[2] += (datoh.getEdad_fin());

            cell = new PdfPCell(new Phrase(Integer.toString(datoh.getId_provincia()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            sumas[3] += (datoh.getId_provincia());

            cell = new PdfPCell(new Phrase(Integer.toString(datoh.getId_localidad()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            sumas[4] += (datoh.getId_localidad());

            cell = new PdfPCell(new Phrase(Integer.toString(datoh.getNum()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            sumas[5] += (datoh.getNum());

            cell = new PdfPCell(new Phrase(Integer.toString(datoh.getRango()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            sumas[6] += (datoh.getRango());

            cell = new PdfPCell(new Phrase(Integer.toString(datoh.getRango() + datoh.getNum() + datoh.getId_localidad() + datoh.getId_provincia() + datoh.getEdad_fin() + datoh.getEdad_ini() + datoh.getId_paciente()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            sumas[7] += (datoh.getRango() + datoh.getNum() + datoh.getId_localidad() + datoh.getId_provincia() + datoh.getEdad_fin() + datoh.getEdad_ini() + datoh.getId_paciente());
        }

        cell = new PdfPCell(new Phrase("TOTAL", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        table.addCell(cell);

        for (int i = 0; i < 8; i++) {
            cell = new PdfPCell(new Phrase(Integer.toString(sumas[i]), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
        }
        //comienza nueva hoja 2
        document.add(table);
        document.newPage();
    }

    public static void addtitulofila33(PdfPTable table, String cadena, String a, String b) {
        PdfPCell cell;
        int NumColumns = 2;
        PdfPTable table112 = new PdfPTable(NumColumns);
        table112.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase(cadena, CABEZA_COLUMNA_FONT));
        cell.setColspan(2);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table112.addCell(cell);

        cell = new PdfPCell(new Phrase(a, DATO_FONT_BOLD));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table112.addCell(cell);

        cell = new PdfPCell(new Phrase(b, DATO_FONT_BOLD));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table112.addCell(cell);

        cell = new PdfPCell(table112);
        cell.setColspan(2);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setGrayFill(0.8f);
        table.addCell(cell);
    }

    public static void addtitulofila3(PdfPTable table, String cadena, String a, String b, String c, String d, String e) {
        PdfPCell cell;
        int NumColumns = 5;
        PdfPTable table11 = new PdfPTable(NumColumns);
        table11.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase(cadena, CABEZA_COLUMNA_FONT));
        cell.setColspan(5);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase(a, DATO_FONT_BOLD));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase(b, DATO_FONT_BOLD));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase(c, DATO_FONT_BOLD));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase(d, DATO_FONT_BOLD));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase(e, DATO_FONT_BOLD));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        cell = new PdfPCell(table11);
        cell.setColspan(5);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setGrayFill(0.8f);
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
