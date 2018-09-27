package org.ayaic.web.administrarcuadernos;

import java.awt.Color;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.view.document.AbstractPdfView;
import java.util.*;
import java.text.*;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Cuadernos;
import org.ayaic.domain.Localidades;

public class VerSNISninos4PDF extends AbstractPdfView {

    private static final Font TITULO_FONT = new Font(Font.HELVETICA, 12, Font.BOLD, Color.black);
    private static final Font CABEZA_COLUMNA_FONT = new Font(Font.HELVETICA, 8, Font.ITALIC, Color.black);
    private static final Font DATO_FONT = new Font(Font.HELVETICA, 8, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_P = new Font(Font.HELVETICA, 7, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_BOLD = new Font(Font.HELVETICA, 10, Font.BOLD, Color.red);
    private static final int MARGIN = 32;

    protected void buildPdfMetadata(Map model, Document document, HttpServletRequest request) {
        Localidades dl = (Localidades) model.get("localidades");
        document.setPageSize(PageSize.LEGAL.rotate());
        document.setMargins(dl.getIzquierda(), dl.getDerecha(), dl.getSuperior(), dl.getInferior());
    }

    protected void buildPdfDocument(Map model, Document document, PdfWriter writer, HttpServletRequest req, HttpServletResponse resp) throws Exception {

        java.util.List listaCuaderno4 = (java.util.List) model.get("listaCuaderno4");
        java.util.List cie = (java.util.List) model.get("listaCie");
        Clientes dato = (Clientes) model.get("dato");
        int[] sumas = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int[] sumbs = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int una, filas = 30;

        if (listaCuaderno4.size() == 0) {
            Paragraph p = new Paragraph("No existe Datos", new Font(Font.HELVETICA, 22, Font.BOLDITALIC, new Color(0, 0, 0)));
            document.add(p);
        }

        if (listaCuaderno4.size() % filas == 0) {
            una = 0;
        } else {
            una = 1;
        }

        Paragraph p;
        PdfPCell cell;

        int NumColumns = 42;
        PdfPTable table = new PdfPTable(NumColumns);
        int[] fwidths = {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4};
        table.setWidths(fwidths);
        table.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("Cuaderno NO. 4                                                                                                                                    ATENCION INTEGRAL DEL NINIO(A) MENOR DE 5 añoS", TITULO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setColspan(NumColumns);
        table.addCell(cell);

        int NumCol = 18;
        PdfPTable tablea = new PdfPTable(NumCol);
        int[] mfwidths = {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4};
        tablea.setWidths(mfwidths);
        tablea.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("PESO PARA LA TALLA (PESO/TALLA)", DATO_FONT));
        cell.setColspan(18);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tablea.addCell(cell);

        addtitulofila33(tablea, "Obesidad(O)", "<1", "M", "F", "1<2", "M", "F", "2<5", "M", "F");
        addtitulofila33(tablea, "Sobrepeso(S)", "<1", "M", "F", "1<2", "M", "F", "2<5", "M", "F");
        addtitulofila33(tablea, "Normal(N)", "<1", "M", "F", "1<2", "M", "F", "2<5", "M", "F");

        cell = new PdfPCell(tablea);
        cell.setColspan(18);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);

        int NumColu = 12;
        PdfPTable tablea3 = new PdfPTable(NumColu);
        int[] gwidths3 = {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4};
        tablea3.setWidths(gwidths3);
        tablea3.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("DESNUTRUCION AGUDA", DATO_FONT));
        cell.setColspan(12);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tablea3.addCell(cell);

        addtitulofila33(tablea3, "Moderada(M)", "<1", "M", "F", "1<2", "M", "F", "2<5", "M", "F");
        addtitulofila33(tablea3, "Grave(G)", "<1", "M", "F", "1<2", "M", "F", "2<5", "M", "F");

        cell = new PdfPCell(tablea3);
        cell.setColspan(12);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);

        int NumColu2 = 12;
        PdfPTable tablea2 = new PdfPTable(NumColu);
        int[] gwidths2 = {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4};
        tablea2.setWidths(gwidths2);
        tablea2.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("TALLA PARA LA EDAD (T/E)", DATO_FONT));
        cell.setColspan(12);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tablea2.addCell(cell);

        addtitulofila33(tablea2, "Talla normal (TN)", "<1", "M", "F", "1<2", "M", "F", "2<5", "M", "F");
        addtitulofila33(tablea2, "Talla baja (TB)", "<1", "M", "F", "1<2", "M", "F", "2<5", "M", "F");

        cell = new PdfPCell(tablea2);
        cell.setColspan(12);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);

        // coloca el detalle de loS datos          
        for (int i = 0; i < listaCuaderno4.size(); i++) {

            Cuadernos datopac = (Cuadernos) listaCuaderno4.get(i);
            ///seleccion = 0 crecimiento
            ///seleccion = 1 consulta externa
            sumas[0] += ("O".equals(datopac.getDglobal()) && datopac.getEdad() == 0 && datopac.getId_tipo_sexo() == 2 && (datopac.getId_cuaderno() == 1 || datopac.getId_cuaderno() == 3)) ? 1 : 0;
            sumas[1] += ("O".equals(datopac.getDglobal()) && datopac.getEdad() == 0 && datopac.getId_tipo_sexo() == 1 && (datopac.getId_cuaderno() == 1 || datopac.getId_cuaderno() == 3)) ? 1 : 0;
            sumas[2] += ("O".equals(datopac.getDglobal()) && datopac.getEdad() == 1 && datopac.getId_tipo_sexo() == 2 && (datopac.getId_cuaderno() == 1 || datopac.getId_cuaderno() == 3)) ? 1 : 0;
            sumas[3] += ("O".equals(datopac.getDglobal()) && datopac.getEdad() == 1 && datopac.getId_tipo_sexo() == 1 && (datopac.getId_cuaderno() == 1 || datopac.getId_cuaderno() == 3)) ? 1 : 0;
            sumas[4] += ("O".equals(datopac.getDglobal()) && datopac.getEdad() > 1 && datopac.getId_tipo_sexo() == 2 && (datopac.getId_cuaderno() == 1 || datopac.getId_cuaderno() == 3)) ? 1 : 0;
            sumas[5] += ("O".equals(datopac.getDglobal()) && datopac.getEdad() > 1 && datopac.getId_tipo_sexo() == 1 && (datopac.getId_cuaderno() == 1 || datopac.getId_cuaderno() == 3)) ? 1 : 0;

            sumas[6] += ("S".equals(datopac.getDglobal()) && datopac.getEdad() == 0 && datopac.getId_tipo_sexo() == 2 && (datopac.getId_cuaderno() == 1 || datopac.getId_cuaderno() == 3)) ? 1 : 0;
            sumas[7] += ("S".equals(datopac.getDglobal()) && datopac.getEdad() == 0 && datopac.getId_tipo_sexo() == 1 && (datopac.getId_cuaderno() == 1 || datopac.getId_cuaderno() == 3)) ? 1 : 0;
            sumas[8] += ("S".equals(datopac.getDglobal()) && datopac.getEdad() == 1 && datopac.getId_tipo_sexo() == 2 && (datopac.getId_cuaderno() == 1 || datopac.getId_cuaderno() == 3)) ? 1 : 0;
            sumas[9] += ("S".equals(datopac.getDglobal()) && datopac.getEdad() == 1 && datopac.getId_tipo_sexo() == 1 && (datopac.getId_cuaderno() == 1 || datopac.getId_cuaderno() == 3)) ? 1 : 0;
            sumas[10] += ("S".equals(datopac.getDglobal()) && datopac.getEdad() > 1 && datopac.getId_tipo_sexo() == 2 && (datopac.getId_cuaderno() == 1 || datopac.getId_cuaderno() == 3)) ? 1 : 0;
            sumas[11] += ("S".equals(datopac.getDglobal()) && datopac.getEdad() > 1 && datopac.getId_tipo_sexo() == 1 && (datopac.getId_cuaderno() == 1 || datopac.getId_cuaderno() == 3)) ? 1 : 0;

            sumas[12] += ("N".equals(datopac.getDglobal()) && datopac.getEdad() == 0 && datopac.getId_tipo_sexo() == 2 && (datopac.getId_cuaderno() == 1 || datopac.getId_cuaderno() == 3)) ? 1 : 0;
            sumas[13] += ("N".equals(datopac.getDglobal()) && datopac.getEdad() == 0 && datopac.getId_tipo_sexo() == 1 && (datopac.getId_cuaderno() == 1 || datopac.getId_cuaderno() == 3)) ? 1 : 0;
            sumas[14] += ("N".equals(datopac.getDglobal()) && datopac.getEdad() == 1 && datopac.getId_tipo_sexo() == 2 && (datopac.getId_cuaderno() == 1 || datopac.getId_cuaderno() == 3)) ? 1 : 0;
            sumas[15] += ("N".equals(datopac.getDglobal()) && datopac.getEdad() == 1 && datopac.getId_tipo_sexo() == 1 && (datopac.getId_cuaderno() == 1 || datopac.getId_cuaderno() == 3)) ? 1 : 0;
            sumas[16] += ("N".equals(datopac.getDglobal()) && datopac.getEdad() > 1 && datopac.getId_tipo_sexo() == 2 && (datopac.getId_cuaderno() == 1 || datopac.getId_cuaderno() == 3)) ? 1 : 0;
            sumas[17] += ("N".equals(datopac.getDglobal()) && datopac.getEdad() > 1 && datopac.getId_tipo_sexo() == 1 && (datopac.getId_cuaderno() == 1 || datopac.getId_cuaderno() == 3)) ? 1 : 0;

            sumas[18] += ("M".equals(datopac.getDglobal()) && datopac.getEdad() == 0 && datopac.getId_tipo_sexo() == 2 && (datopac.getId_cuaderno() == 1 || datopac.getId_cuaderno() == 3)) ? 1 : 0;
            sumas[19] += ("M".equals(datopac.getDglobal()) && datopac.getEdad() == 0 && datopac.getId_tipo_sexo() == 1 && (datopac.getId_cuaderno() == 1 || datopac.getId_cuaderno() == 3)) ? 1 : 0;
            sumas[20] += ("M".equals(datopac.getDglobal()) && datopac.getEdad() == 1 && datopac.getId_tipo_sexo() == 2 && (datopac.getId_cuaderno() == 1 || datopac.getId_cuaderno() == 3)) ? 1 : 0;
            sumas[21] += ("M".equals(datopac.getDglobal()) && datopac.getEdad() == 1 && datopac.getId_tipo_sexo() == 1 && (datopac.getId_cuaderno() == 1 || datopac.getId_cuaderno() == 3)) ? 1 : 0;
            sumas[22] += ("M".equals(datopac.getDglobal()) && datopac.getEdad() > 1 && datopac.getId_tipo_sexo() == 2 && (datopac.getId_cuaderno() == 1 || datopac.getId_cuaderno() == 3)) ? 1 : 0;
            sumas[23] += ("M".equals(datopac.getDglobal()) && datopac.getEdad() > 1 && datopac.getId_tipo_sexo() == 1 && (datopac.getId_cuaderno() == 1 || datopac.getId_cuaderno() == 3)) ? 1 : 0;

            sumas[24] += ("G".equals(datopac.getDglobal()) && datopac.getEdad() == 0 && datopac.getId_tipo_sexo() == 2 && (datopac.getId_cuaderno() == 1 || datopac.getId_cuaderno() == 3)) ? 1 : 0;
            sumas[25] += ("G".equals(datopac.getDglobal()) && datopac.getEdad() == 0 && datopac.getId_tipo_sexo() == 1 && (datopac.getId_cuaderno() == 1 || datopac.getId_cuaderno() == 3)) ? 1 : 0;
            sumas[26] += ("G".equals(datopac.getDglobal()) && datopac.getEdad() == 1 && datopac.getId_tipo_sexo() == 2 && (datopac.getId_cuaderno() == 1 || datopac.getId_cuaderno() == 3)) ? 1 : 0;
            sumas[27] += ("G".equals(datopac.getDglobal()) && datopac.getEdad() == 1 && datopac.getId_tipo_sexo() == 1 && (datopac.getId_cuaderno() == 1 || datopac.getId_cuaderno() == 3)) ? 1 : 0;
            sumas[28] += ("G".equals(datopac.getDglobal()) && datopac.getEdad() > 1 && datopac.getId_tipo_sexo() == 2 && (datopac.getId_cuaderno() == 1 || datopac.getId_cuaderno() == 3)) ? 1 : 0;
            sumas[29] += ("G".equals(datopac.getDglobal()) && datopac.getEdad() > 1 && datopac.getId_tipo_sexo() == 1 && (datopac.getId_cuaderno() == 1 || datopac.getId_cuaderno() == 3)) ? 1 : 0;

            sumas[30] += ("N".equals(datopac.getDcronica()) && datopac.getEdad() == 0 && datopac.getId_tipo_sexo() == 2 && (datopac.getId_cuaderno() == 2 || datopac.getId_cuaderno() == 3)) ? 1 : 0;
            sumas[31] += ("N".equals(datopac.getDcronica()) && datopac.getEdad() == 0 && datopac.getId_tipo_sexo() == 1 && (datopac.getId_cuaderno() == 2 || datopac.getId_cuaderno() == 3)) ? 1 : 0;
            sumas[32] += ("N".equals(datopac.getDcronica()) && datopac.getEdad() == 1 && datopac.getId_tipo_sexo() == 2 && (datopac.getId_cuaderno() == 2 || datopac.getId_cuaderno() == 3)) ? 1 : 0;
            sumas[33] += ("N".equals(datopac.getDcronica()) && datopac.getEdad() == 1 && datopac.getId_tipo_sexo() == 1 && (datopac.getId_cuaderno() == 2 || datopac.getId_cuaderno() == 3)) ? 1 : 0;
            sumas[34] += ("N".equals(datopac.getDcronica()) && datopac.getEdad() > 1 && datopac.getId_tipo_sexo() == 2 && (datopac.getId_cuaderno() == 2 || datopac.getId_cuaderno() == 3)) ? 1 : 0;
            sumas[35] += ("N".equals(datopac.getDcronica()) && datopac.getEdad() > 1 && datopac.getId_tipo_sexo() == 1 && (datopac.getId_cuaderno() == 2 || datopac.getId_cuaderno() == 3)) ? 1 : 0;

            sumas[36] += ("L".equals(datopac.getDcronica()) && datopac.getEdad() == 0 && datopac.getId_tipo_sexo() == 2 && (datopac.getId_cuaderno() == 2 || datopac.getId_cuaderno() == 3)) ? 1 : 0;
            sumas[37] += ("L".equals(datopac.getDcronica()) && datopac.getEdad() == 0 && datopac.getId_tipo_sexo() == 1 && (datopac.getId_cuaderno() == 2 || datopac.getId_cuaderno() == 3)) ? 1 : 0;
            sumas[38] += ("L".equals(datopac.getDcronica()) && datopac.getEdad() == 1 && datopac.getId_tipo_sexo() == 2 && (datopac.getId_cuaderno() == 2 || datopac.getId_cuaderno() == 3)) ? 1 : 0;
            sumas[39] += ("L".equals(datopac.getDcronica()) && datopac.getEdad() == 1 && datopac.getId_tipo_sexo() == 1 && (datopac.getId_cuaderno() == 2 || datopac.getId_cuaderno() == 3)) ? 1 : 0;
            sumas[40] += ("L".equals(datopac.getDcronica()) && datopac.getEdad() > 1 && datopac.getId_tipo_sexo() == 2 && (datopac.getId_cuaderno() == 2 || datopac.getId_cuaderno() == 3)) ? 1 : 0;
            sumas[41] += ("L".equals(datopac.getDcronica()) && datopac.getEdad() > 1 && datopac.getId_tipo_sexo() == 1 && (datopac.getId_cuaderno() == 2 || datopac.getId_cuaderno() == 3)) ? 1 : 0;
        }

        for (int j = 0; j < 48; j++) {
            cell = new PdfPCell(new Phrase(Integer.toString(sumas[j]), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);
        }

        document.add(table);
        document.newPage();

    }

    public static void addfila3(PdfPTable table, String tipo, String dato, String a, String b, String c) {

        PdfPCell cell;
        if (tipo.equals(dato)) {
            cell = new PdfPCell(new Phrase(a, DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(b, DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(c, DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
        } else {
            cell = new PdfPCell(new Phrase("", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
        }

    }

    public static void addtitulofila3(PdfPTable table, String cadena) {
        PdfPCell cell;
        String sCampos[] = {"<1 año", "1 a <2 años", "2 a <5 años"};
        int NumColumns = 3;
        PdfPTable table11 = new PdfPTable(NumColumns);
        table11.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase(cadena, CABEZA_COLUMNA_FONT));
        cell.setColspan(3);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);
        for (int i = 0; i < 3; i++) {
            cell = new PdfPCell(new Phrase(sCampos[i], DATO_FONT_P));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table11.addCell(cell);
        }
        cell = new PdfPCell(table11);
        cell.setColspan(3);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
    }

    public static void addtitulofila33(PdfPTable table, String cadena, String tit1, String a, String b, String tit2, String c, String d, String tit3, String e, String f) {
        PdfPCell cell;
        int NumColumns = 6;
        PdfPTable table11 = new PdfPTable(NumColumns);
        table11.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase(cadena, CABEZA_COLUMNA_FONT));
        cell.setColspan(6);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        int NumCol = 2;
        PdfPTable table12 = new PdfPTable(NumCol);
        table12.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase(tit1, DATO_FONT));
        cell.setColspan(2);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table12.addCell(cell);

        cell = new PdfPCell(new Phrase(a, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table12.addCell(cell);

        cell = new PdfPCell(new Phrase(b, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table12.addCell(cell);

        cell = new PdfPCell(table12);
        cell.setColspan(2);
        table11.addCell(cell);

        table12 = new PdfPTable(NumCol);
        table12.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase(tit2, DATO_FONT));
        cell.setColspan(2);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table12.addCell(cell);

        cell = new PdfPCell(new Phrase(c, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table12.addCell(cell);

        cell = new PdfPCell(new Phrase(d, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table12.addCell(cell);

        cell = new PdfPCell(table12);
        cell.setColspan(2);
        table11.addCell(cell);

//tercera columna  
        table12 = new PdfPTable(NumCol);
        table12.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase(tit3, DATO_FONT));
        cell.setColspan(2);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table12.addCell(cell);

        cell = new PdfPCell(new Phrase(e, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table12.addCell(cell);

        cell = new PdfPCell(new Phrase(f, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table12.addCell(cell);

        cell = new PdfPCell(table12);
        cell.setColspan(2);
        table11.addCell(cell);

        cell = new PdfPCell(table11);
        cell.setColspan(6);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
    }

    public static void addtitulofila30(PdfPTable table, String cadena, String a, String b, String c) {
        PdfPCell cell;
        int NumColumns = 3;
        PdfPTable table11 = new PdfPTable(NumColumns);
        table11.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase(cadena, CABEZA_COLUMNA_FONT));
        cell.setColspan(3);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase(a, DATO_FONT_P));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase(b, DATO_FONT_P));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase(c, DATO_FONT_P));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        cell = new PdfPCell(table11);
        cell.setColspan(3);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
    }

    public static void addtitulofila40(PdfPTable table, String cadena, String a, String b, String c, String d) {
        PdfPCell cell;
        int NumColumns = 4;
        PdfPTable table11 = new PdfPTable(NumColumns);
        table11.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase(cadena, CABEZA_COLUMNA_FONT));
        cell.setColspan(4);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase(a, DATO_FONT_P));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase(b, DATO_FONT_P));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase(c, DATO_FONT_P));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase(d, DATO_FONT_P));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        cell = new PdfPCell(table11);
        cell.setColspan(4);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
    }

    public static void addtitulofila20(PdfPTable table, String cadena, String a, String b) {
        PdfPCell cell;
        int NumColumns = 2;
        PdfPTable table11 = new PdfPTable(NumColumns);
        table11.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase(cadena, CABEZA_COLUMNA_FONT));
        cell.setColspan(2);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase(a, DATO_FONT_P));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase(b, DATO_FONT_P));
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
