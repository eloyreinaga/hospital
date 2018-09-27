package org.ayaic.web.administrarhistoriales;

import java.awt.Color;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import java.util.*;
import java.text.*;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Pacientes;

public class ListarHCLCaraPDF extends AbstractPdfView {

    private static final Font TITULO_FONT = new Font(Font.HELVETICA, 12, Font.BOLD, Color.black);
    private static final Font CABEZA_COLUMNA_FONT = new Font(Font.HELVETICA, 8, Font.ITALIC, Color.black);
    private static final Font DATO_FONT = new Font(Font.HELVETICA, 8, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_A = new Font(Font.HELVETICA, 6, Font.NORMAL, Color.black);
    private static final int MARGIN = 32;

    protected void buildPdfMetadata(Map model, Document document, HttpServletRequest request) {
        document.setPageSize(PageSize.LETTER);
        document.setMargins(20, 20, 20, 20);
    }

    protected void buildPdfDocument(Map model, Document document, PdfWriter writer, HttpServletRequest req, HttpServletResponse resp) throws Exception {

        Clientes dato = (Clientes) model.get("dato");
        Pacientes datopac = (Pacientes) model.get("datosp");
        String tipo;

        DecimalFormat df = new DecimalFormat("###,##0.00");
        Image escudo = Image.getInstance("/opt/imagenes/lescudo.bmp");
        Paragraph p = new Paragraph("HISTORIA CLINICA", new Font(Font.HELVETICA, 14, Font.BOLDITALIC, new Color(0, 0, 0)));
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

        PdfPTable table1n = new PdfPTable(4);
        int[] fmwidths = {20, 20, 20, 40}; // percentage
        table1n.setWidths(fmwidths);
        table1n.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("Ap. Materno: \n", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table1n.addCell(cell);

        cell = new PdfPCell(new Phrase("Nombres : \n", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table1n.addCell(cell);

        cell = new PdfPCell(new Phrase("2. No HCL : \n", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1n.addCell(cell);

        document.add(table1n);

        PdfPTable table1s = new PdfPTable(8);
        int[] fmwidtss = {15, 15, 15, 20, 15, 15, 20, 15}; // percentage
        table1s.setWidths(fmwidtss);
        table1s.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("3. Edad: \n", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table1s.addCell(cell);

        cell = new PdfPCell(new Phrase("4. Sexo :\n Femenino", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1s.addCell(cell);

        cell = new PdfPCell(new Phrase("5. Lugar Nac : \n", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1s.addCell(cell);

        cell = new PdfPCell(new Phrase("Provincia : \n", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1s.addCell(cell);

        cell = new PdfPCell(new Phrase("Depto. : \n", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1s.addCell(cell);

        cell = new PdfPCell(new Phrase("6. Procedencia : \n", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1s.addCell(cell);

        cell = new PdfPCell(new Phrase("Provincia : \n", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1s.addCell(cell);

        cell = new PdfPCell(new Phrase("Depto. : \n", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1s.addCell(cell);

        document.add(table1s);

        PdfPTable tablec = new PdfPTable(3);
        int[] fmwidtsc = {30, 40, 30}; // percentage
        tablec.setWidths(fmwidtsc);
        tablec.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("7. Estado Civil : \n", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tablec.addCell(cell);

        cell = new PdfPCell(new Phrase("8. Ocupacion Habitual: \n", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tablec.addCell(cell);

        cell = new PdfPCell(new Phrase("9. Grado Instruccion : \n Basico", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tablec.addCell(cell);

        document.add(tablec);

        PdfPTable tableh = new PdfPTable(2);
        int[] fmwidthh = {60, 40}; // percentage
        tableh.setWidths(fmwidthh);
        tableh.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("10. Diagnostico : \n", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        tableh.addCell(cell);

        cell = new PdfPCell(new Phrase("11. Medico que Ordena Internacion: \n         Dr. ", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        tableh.addCell(cell);

        document.add(tableh);

        PdfPTable tableh2 = new PdfPTable(2);
        int[] fmwidth2 = {60, 40}; // percentage
        tableh2.setWidths(fmwidth2);
        tableh2.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("12. Direccion del paciente : /Telefono\n", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        tableh2.addCell(cell);

        cell = new PdfPCell(new Phrase("13. Enviado de: \n     ", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        tableh2.addCell(cell);

        cell = new PdfPCell(new Phrase("14. INGRESO - TRASLADO DURANTE LA HOSPITALIZACION - EGRESO", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(2);
        tableh2.addCell(cell);

        document.add(tableh2);

        PdfPTable tablehr = new PdfPTable(8);
        int[] fmwidthr = {8, 6, 50, 13, 11, 9, 20, 5}; // percentage
        tablehr.setWidths(fmwidthr);
        tablehr.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("FECHA", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.9f);
        tablehr.addCell(cell);

        cell = new PdfPCell(new Phrase("HORA", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.9f);
        tablehr.addCell(cell);

        cell = new PdfPCell(new Phrase("ACCION", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.9f);
        tablehr.addCell(cell);

        cell = new PdfPCell(new Phrase("SERVICIO", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.9f);
        tablehr.addCell(cell);

        cell = new PdfPCell(new Phrase("SALA", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.9f);
        tablehr.addCell(cell);

        cell = new PdfPCell(new Phrase("CAMA", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.9f);
        tablehr.addCell(cell);

        cell = new PdfPCell(new Phrase("POR", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.9f);
        tablehr.addCell(cell);

        cell = new PdfPCell(new Phrase("ID", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.9f);
        tablehr.addCell(cell);

        document.add(tablehr);

        PdfPTable tableo = new PdfPTable(2);
        int[] fmwidto = {80, 20}; // percentage
        tableo.setWidths(fmwidto);
        tableo.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("15. Operaciones: ", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        tableo.addCell(cell);

        cell = new PdfPCell(new Phrase("CODIGO", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tableo.addCell(cell);

        cell = new PdfPCell(new Phrase(" ", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        tableo.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tableo.addCell(cell);

        cell = new PdfPCell(new Phrase(" ", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        tableo.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tableo.addCell(cell);

        cell = new PdfPCell(new Phrase("16. DIagnostico Principal: ", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        tableo.addCell(cell);

        cell = new PdfPCell(new Phrase("CODIGO", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        tableo.addCell(cell);

        cell = new PdfPCell(new Phrase("17. Otros DIagnosticos: ", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        tableo.addCell(cell);

        cell = new PdfPCell(new Phrase("CODIGO", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        tableo.addCell(cell);

        document.add(tableo);

        PdfPTable tablee = new PdfPTable(3);
        int[] fmwidte = {30, 30, 40}; // percentage
        tablee.setWidths(fmwidte);
        tablee.setWidthPercentage(100);

        document.add(tablee);

        PdfPTable tablen = new PdfPTable(9);
        int[] fmwidtn = {11, 11, 11, 11, 11, 11, 11, 11, 11}; // percentage
        tablen.setWidths(fmwidtn);
        tablen.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("23. Recien Nacido\n", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        tablen.addCell(cell);

        cell = new PdfPCell(new Phrase("Tipo: \n  ", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        tablen.addCell(cell);

        addtitulofila2(tablen, "Sexo", "M", "F");

        addtitulofila2(tablen, "Condicion al Nacer", "Vivo", "Muerto");

        cell = new PdfPCell(new Phrase("Peso en gramos", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tablen.addCell(cell);

        addtitulofila2(tablen, "Condicion al Egresar", "Vivo", "Muerto");

        document.add(tablen);

        PdfPTable tablep = new PdfPTable(3);
        int[] fmwidtp = {30, 30, 40}; // percentage
        tablep.setWidths(fmwidtp);
        tablep.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("21. Nombre Medico\n", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        tablep.addCell(cell);

        cell = new PdfPCell(new Phrase("Numero de Matricula\n", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        tablep.addCell(cell);

        cell = new PdfPCell(new Phrase("FIRMA\n", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        tablep.addCell(cell);

        document.add(tablep);

        // coloca el detalle de los datos
        //   table.setHeaderRows(1);
    }

    public static void addtitulofila2(PdfPTable table, String cadena, String a, String b) {
        PdfPCell cell;
        int NumColumns = 2;
        PdfPTable table11 = new PdfPTable(NumColumns);
        table11.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase(cadena, CABEZA_COLUMNA_FONT));
        cell.setColspan(2);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase(a, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase(b, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        cell = new PdfPCell(table11);
        cell.setColspan(2);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
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
