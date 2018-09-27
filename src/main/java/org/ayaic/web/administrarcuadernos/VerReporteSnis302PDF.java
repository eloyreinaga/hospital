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

public class VerReporteSnis302PDF extends AbstractPdfView {

    private static final Font TITULO_FONT = new Font(Font.HELVETICA, 12, Font.BOLD, Color.black);
    private static final Font CABEZA_COLUMNA_FONT = new Font(Font.TIMES_ROMAN, 7, Font.NORMAL, Color.black);
    private static final Font CABEZA_COLUMNAT_FONT = new Font(Font.ITALIC, 5, Font.NORMAL, Color.black);
    private static final Font DATO_FONT = new Font(Font.TIMES_ROMAN, 7, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_BOLD = new Font(Font.HELVETICA, 8, Font.BOLD, Color.black);
    private static final int MARGIN = 32;

    protected void buildPdfMetadata(Map model, Document document, HttpServletRequest request) {
        document.setPageSize(PageSize.LEGAL);
        document.setMargins(10, 10, 10, 10);
    }

    protected void buildPdfDocument(Map model, Document document, PdfWriter writer, HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Clientes dato = (Clientes) model.get("dato");
        java.util.List lista302 = (java.util.List) model.get("lista30");
        Cuadernos d5 = (Cuadernos) model.get("lista_5");
        Cuadernos dimc = (Cuadernos) model.get("listaImc");
        // coloca el titulo de la pagina
        Image escudo = Image.getInstance("/opt/imagenes/hoja2.bmp");
        Paragraph p = new Paragraph("NOTIFICACION PARA LA VIGILANCIA EPIDEMIOLOGICA\nCODIGO: R. A. SALUD INE No 302 (01/2014)", new Font(Font.HELVETICA, 10, Font.BOLDITALIC, new Color(0, 0, 0)));
        Paragraph h = new Paragraph("Semana Epidemiologica No_____\nCambie formulario el dia Domingo", new Font(Font.HELVETICA, 8, Font.BOLDITALIC, new Color(0, 0, 0)));
        p.setAlignment(Element.ALIGN_CENTER);
        h.setAlignment(Element.ALIGN_RIGHT);

        PdfPCell cell;

        PdfPTable tablex = new PdfPTable(3);
        int[] fxwidths = {10, 65, 25}; // percentage
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

        cell = new PdfPCell(h);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex.addCell(cell);

        document.add(tablex);

        PdfPTable table1 = new PdfPTable(3);
        int[] fmwidths = {33, 33, 33}; // percentage
        table1.setWidths(fmwidths);
        table1.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("SEDES : " + dato.getDepartamento() + "      Localidad:....................", DATO_FONT));
        cell.setBorder(Rectangle.NO_BORDER);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("Red de salud: " + dato.getRed(), DATO_FONT));
        cell.setBorder(Rectangle.NO_BORDER);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("Municipio : " + dato.getLocalidad(), DATO_FONT));
        cell.setBorder(Rectangle.NO_BORDER);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("Establecimiento : " + dato.getEstablecimiento(), DATO_FONT));
        cell.setBorder(Rectangle.NO_BORDER);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("Mes : " + format(new Date(), "MM") + "         " + "Año : " + format(new Date(), "yyyy"), DATO_FONT));
        cell.setBorder(Rectangle.NO_BORDER);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("Subsector: A B C D E F ", DATO_FONT));
        cell.setBorder(Rectangle.NO_BORDER);
        table1.addCell(cell);

        document.add(table1);

        PdfPTable table2 = new PdfPTable(1);
        int[] f2widths = {100}; // percentage
        table2.setWidths(f2widths);
        table2.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("Los datos siguientes deben ser consolidados semanalmente por la enfermera o el medico y certificados por el Medico Director ", new Font(Font.HELVETICA, 10)));
        cell.setBorder(Rectangle.NO_BORDER);
        table2.addCell(cell);

        cell = new PdfPCell(new Phrase("REGISTRO DE ENFERMEDADES DE NOTIFICACION INMEDIATA ", new Font(Font.HELVETICA, 10, Font.BOLDITALIC, new Color(0, 0, 0))));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table2.addCell(cell);
        document.add(table2);

        int NumColumns111 = 11;
        PdfPTable table = new PdfPTable(NumColumns111);
        int[] fwidths111 = {30, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8};
        table.setWidths(fwidths111);
        table.setWidthPercentage(100);

        String sCampos111[] = {"Sospecha Diagnostica", "< 6 meses", "6 m a 1 año", "1 a 4 años", "5 a 9 años", "10 a 14 años", "15 a 19 años", "20 a 39 años", "40 a 49 años", "50 a 59 años", "> 60 años"};

        for (int i = 0; i < NumColumns111; i++) {
            cell = new PdfPCell(new Phrase(sCampos111[i], CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setGrayFill(0.8f);
            table.addCell(cell);
        }

        document.add(table);

        int NumColumns112 = 21;
        PdfPTable table101 = new PdfPTable(NumColumns112);
        int[] fwidths112 = {30, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4};
        table101.setWidths(fwidths112);
        table101.setWidthPercentage(100);

        String sCampos112[] = {"", "M", "F", "M", "F", "M", "F", "M", "F", "M", "F", "M", "F", "M", "F", "M", "F", "M", "F", "M", "F"};

        for (int i = 0; i < NumColumns112; i++) {
            cell = new PdfPCell(new Phrase(sCampos112[i], CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setGrayFill(0.8f);
            table101.addCell(cell);
        }

        Cuadernos l01 = (Cuadernos) lista302.get(0);   ///saca del 1 
        if (l01.getSuma22() == 1 && l01.getSuma21() > 0) {
            addfila135(table101, "1. Sarampiï¿½n / Rubeola", l01.getSuma1(), l01.getSuma2(), l01.getSuma3(), l01.getSuma4(), l01.getSuma5(), l01.getSuma6(), l01.getSuma7(), l01.getSuma8(), l01.getSuma9(), l01.getSuma10(), l01.getSuma11(), l01.getSuma12(), l01.getSuma13(), l01.getSuma14(), l01.getSuma15(), l01.getSuma16(), l01.getSuma17(), l01.getSuma18(), l01.getSuma19(), l01.getSuma20());
        } else {
            addfila135(table101, "1. Sarampiï¿½n / Rubeola", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        }

        Cuadernos l02 = (Cuadernos) lista302.get(1);   ///saca del 1 
        if (l02.getSuma22() == 2 && l02.getSuma21() > 0) {
            addfila135(table101, "2. Sindrome de Rubeola congenita", l02.getSuma1(), l02.getSuma2(), l02.getSuma3(), l02.getSuma4(), l02.getSuma5(), l02.getSuma6(), l02.getSuma7(), l02.getSuma8(), l02.getSuma9(), l02.getSuma10(), l02.getSuma11(), l02.getSuma12(), l02.getSuma13(), l02.getSuma14(), l02.getSuma15(), l02.getSuma16(), l02.getSuma17(), l02.getSuma18(), l02.getSuma19(), l02.getSuma20());
        } else {
            addfila135(table101, "2. Sindrome de Rubeola congenita", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        }

        Cuadernos l03 = (Cuadernos) lista302.get(2);   ///saca del 1 
        if (l03.getSuma22() == 3 && l03.getSuma21() > 0) {
            addfila135(table101, "3. Tos ferina/Coqueluche", l03.getSuma1(), l03.getSuma2(), l03.getSuma3(), l03.getSuma4(), l03.getSuma5(), l03.getSuma6(), l03.getSuma7(), l03.getSuma8(), l03.getSuma9(), l03.getSuma10(), l03.getSuma11(), l03.getSuma12(), l03.getSuma13(), l03.getSuma14(), l03.getSuma15(), l03.getSuma16(), l03.getSuma17(), l03.getSuma18(), l03.getSuma19(), l03.getSuma20());
        } else {
            addfila135(table101, "3. Tos ferina/Coqueluche", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        }

        Cuadernos l04 = (Cuadernos) lista302.get(3);   ///saca del 1 
        if (l04.getSuma22() == 4 && l04.getSuma21() > 0) {
            addfila135(table101, "4. Difteria", l04.getSuma1(), l04.getSuma2(), l04.getSuma3(), l04.getSuma4(), l04.getSuma5(), l04.getSuma6(), l04.getSuma7(), l04.getSuma8(), l04.getSuma9(), l04.getSuma10(), l04.getSuma11(), l04.getSuma12(), l04.getSuma13(), l04.getSuma14(), l04.getSuma15(), l04.getSuma16(), l04.getSuma17(), l04.getSuma18(), l04.getSuma19(), l04.getSuma20());
        } else {
            addfila135(table101, "4. Difteria", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        }

        Cuadernos l05 = (Cuadernos) lista302.get(4);   ///saca del 1 
        if (l05.getSuma22() == 5 && l05.getSuma21() > 0) {
            addfila135(table101, "5. Paralisis flacida aguda (Polio)", l05.getSuma1(), l05.getSuma2(), l05.getSuma3(), l05.getSuma4(), l05.getSuma5(), l05.getSuma6(), l05.getSuma7(), l05.getSuma8(), l05.getSuma9(), l05.getSuma10(), l05.getSuma11(), l05.getSuma12(), l05.getSuma13(), l05.getSuma14(), l05.getSuma15(), l05.getSuma16(), l05.getSuma17(), l05.getSuma18(), l05.getSuma19(), l05.getSuma20());
        } else {
            addfila135(table101, "5. Paralisis flacida aguda (Polio)", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        }

        Cuadernos l06 = (Cuadernos) lista302.get(5);   ///saca del 1 
        if (l06.getSuma22() == 6 && l06.getSuma21() > 0) {
            addfila135(table101, "6. Fiebre amarilla", l06.getSuma1(), l06.getSuma2(), l06.getSuma3(), l06.getSuma4(), l06.getSuma5(), l06.getSuma6(), l06.getSuma7(), l06.getSuma8(), l06.getSuma9(), l06.getSuma10(), l06.getSuma11(), l06.getSuma12(), l06.getSuma13(), l06.getSuma14(), l06.getSuma15(), l06.getSuma16(), l06.getSuma17(), l06.getSuma18(), l06.getSuma19(), l06.getSuma20());
        } else {
            addfila135(table101, "6. Fiebre amarilla", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        }

        Cuadernos l07 = (Cuadernos) lista302.get(6);   ///saca del 1 
        if (l07.getSuma22() == 7 && l07.getSuma21() > 0) {
            addfila135(table101, "7. Dengue grave", l07.getSuma1(), l07.getSuma2(), l07.getSuma3(), l07.getSuma4(), l07.getSuma5(), l07.getSuma6(), l07.getSuma7(), l07.getSuma8(), l07.getSuma9(), l07.getSuma10(), l07.getSuma11(), l07.getSuma12(), l07.getSuma13(), l07.getSuma14(), l07.getSuma15(), l07.getSuma16(), l07.getSuma17(), l07.getSuma18(), l07.getSuma19(), l07.getSuma20());
        } else {
            addfila135(table101, "7. Dengue grave", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        }

        Cuadernos l08 = (Cuadernos) lista302.get(7);   ///saca del 1 
        if (l08.getSuma22() == 8 && l08.getSuma21() > 0) {
            addfila135(table101, "8. Fiebre Hemorragica Boliviana", l08.getSuma1(), l08.getSuma2(), l08.getSuma3(), l08.getSuma4(), l08.getSuma5(), l08.getSuma6(), l08.getSuma7(), l08.getSuma8(), l08.getSuma9(), l08.getSuma10(), l08.getSuma11(), l08.getSuma12(), l08.getSuma13(), l08.getSuma14(), l08.getSuma15(), l08.getSuma16(), l08.getSuma17(), l08.getSuma18(), l08.getSuma19(), l08.getSuma20());
        } else {
            addfila135(table101, "8. Fiebre Hemorragica Boliviana", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        }

        Cuadernos l09 = (Cuadernos) lista302.get(8);   ///saca del 1 
        if (l09.getSuma22() == 9 && l09.getSuma21() > 0) {
            addfila135(table101, "9. Peste    ", l09.getSuma1(), l09.getSuma2(), l09.getSuma3(), l09.getSuma4(), l09.getSuma5(), l09.getSuma6(), l09.getSuma7(), l09.getSuma8(), l09.getSuma9(), l09.getSuma10(), l09.getSuma11(), l09.getSuma12(), l09.getSuma13(), l09.getSuma14(), l09.getSuma15(), l09.getSuma16(), l09.getSuma17(), l09.getSuma18(), l09.getSuma19(), l09.getSuma20());
        } else {
            addfila135(table101, "9. Peste    ", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        }

        Cuadernos l10 = (Cuadernos) lista302.get(9);   ///Enfermedad Meningocï¿½cica
        if (l10.getSuma22() == 10 && l10.getSuma21() > 0) {
            addfila135(table101, "10. Enfermedad Meningococica", l10.getSuma1(), l10.getSuma2(), l10.getSuma3(), l10.getSuma4(), l10.getSuma5(), l10.getSuma6(), l10.getSuma7(), l10.getSuma8(), l10.getSuma9(), l10.getSuma10(), l10.getSuma11(), l10.getSuma12(), l10.getSuma13(), l10.getSuma14(), l10.getSuma15(), l10.getSuma16(), l10.getSuma17(), l10.getSuma18(), l10.getSuma19(), l10.getSuma20());
        } else {
            addfila135(table101, "10. Enfermedad Meningocï¿½cica", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        }

        Cuadernos l11 = (Cuadernos) lista302.get(10);   ///Colera
        if (l11.getSuma22() == 11 && l11.getSuma21() > 0) {
            addfila135(table101, "11. Colera", l11.getSuma1(), l11.getSuma2(), l11.getSuma3(), l11.getSuma4(), l11.getSuma5(), l11.getSuma6(), l11.getSuma7(), l11.getSuma8(), l11.getSuma9(), l11.getSuma10(), l11.getSuma11(), l11.getSuma12(), l11.getSuma13(), l11.getSuma14(), l11.getSuma15(), l11.getSuma16(), l11.getSuma17(), l11.getSuma18(), l11.getSuma19(), l11.getSuma20());
        } else {
            addfila135(table101, "11. Colera", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        }

        Cuadernos l12 = (Cuadernos) lista302.get(11);   ///Enfermedad por Hanta virus
        if (l12.getSuma22() == 12 && l12.getSuma21() > 0) {
            addfila135(table101, "12. Enfermedad por Hanta virus", l12.getSuma1(), l12.getSuma2(), l12.getSuma3(), l12.getSuma4(), l12.getSuma5(), l12.getSuma6(), l12.getSuma7(), l12.getSuma8(), l12.getSuma9(), l12.getSuma10(), l12.getSuma11(), l12.getSuma12(), l12.getSuma13(), l12.getSuma14(), l12.getSuma15(), l12.getSuma16(), l12.getSuma17(), l12.getSuma18(), l12.getSuma19(), l12.getSuma20());
        } else {
            addfila135(table101, "12. Enfermedad por Hanta virus", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        }

        Cuadernos l13 = (Cuadernos) lista302.get(11);   ///Rabia en perros 
        if (l13.getSuma22() == 13 && l13.getSuma21() > 0) {
            addfila135(table101, "13 Rabia en perros", l13.getSuma1(), l13.getSuma2(), l13.getSuma3(), l13.getSuma4(), l13.getSuma5(), l13.getSuma6(), l13.getSuma7(), l13.getSuma8(), l13.getSuma9(), l13.getSuma10(), l13.getSuma11(), l13.getSuma12(), l13.getSuma13(), l13.getSuma14(), l13.getSuma15(), l13.getSuma16(), l13.getSuma17(), l13.getSuma18(), l13.getSuma19(), l13.getSuma20());
        } else {
            addfila135(table101, "13 Rabia en perros", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        }

        Cuadernos l14 = (Cuadernos) lista302.get(12);   ///Rabia humana 
        if (l14.getSuma22() == 14 && l14.getSuma21() > 0) {
            addfila135(table101, "14. Rabia humana", l14.getSuma1(), l14.getSuma2(), l14.getSuma3(), l14.getSuma4(), l14.getSuma5(), l14.getSuma6(), l14.getSuma7(), l14.getSuma8(), l14.getSuma9(), l14.getSuma10(), l14.getSuma11(), l14.getSuma12(), l14.getSuma13(), l14.getSuma14(), l14.getSuma15(), l14.getSuma16(), l14.getSuma17(), l14.getSuma18(), l14.getSuma19(), l14.getSuma20());
        } else {
            addfila135(table101, "14. Rabia humana", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        }

        Cuadernos l15 = (Cuadernos) lista302.get(13);   ///saca del 1 
        if (l15.getSuma22() == 15 && l15.getSuma21() > 0) {
            addfila135(table101, "15. Influenza  (IRAG ,IRAGI y ETI)", l15.getSuma1(), l15.getSuma2(), l15.getSuma3(), l15.getSuma4(), l15.getSuma5(), l15.getSuma6(), l15.getSuma7(), l15.getSuma8(), l15.getSuma9(), l15.getSuma10(), l15.getSuma11(), l15.getSuma12(), l15.getSuma13(), l15.getSuma14(), l15.getSuma15(), l15.getSuma16(), l15.getSuma17(), l15.getSuma18(), l15.getSuma19(), l15.getSuma20());
        } else {
            addfila135(table101, "15. Influenza  (IRAG ,IRAGI y ETI)", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        }

        Cuadernos l16 = (Cuadernos) lista302.get(14);   ///saca del 1 
        if (l16.getSuma22() == 16 && l16.getSuma21() > 0) {
            addfila135(table101, "16. Leptospirosis", l03.getSuma1(), l16.getSuma2(), l16.getSuma3(), l16.getSuma4(), l16.getSuma5(), l16.getSuma6(), l16.getSuma7(), l16.getSuma8(), l16.getSuma9(), l16.getSuma10(), l16.getSuma11(), l16.getSuma12(), l16.getSuma13(), l16.getSuma14(), l16.getSuma15(), l16.getSuma16(), l16.getSuma17(), l16.getSuma18(), l16.getSuma19(), l16.getSuma20());
        } else {
            addfila135(table101, "16. Leptospirosis", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        }

        document.add(table101);

        int NumColumns11 = 11;
        PdfPTable table11 = new PdfPTable(NumColumns11);
        int[] fwidths11 = {25, 12, 12, 12, 0, 1, 0, 25, 12, 12, 12};
        table11.setWidths(fwidths11);
        table11.setWidthPercentage(100);
        String sCampos11[] = {"Evento", "No.", "No pers. afectadas", "No pers. fallecidas", "", "", "", "Evento", "No.", "No pers. afectadas", "No pers. fallecidas"};

        addtitulomain(table11, "REGISTRO DE EVENTOS DE NOTIFICACION INMEDIATA");

        for (int i = 0; i < NumColumns11; i++) {
            cell = new PdfPCell(new Phrase(sCampos11[i], CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setGrayFill(0.8f);
            table11.addCell(cell);
        }
        addfila11(table11, "17. Riada", 0, 0, 0, 0, "", 0, "20. Deslizamiento/Sismo/Terremoto", 0, 0, 0);
        addfila11(table11, "18. Helada/graniso/nevada", 0, 0, 0, 0, "", 0, "21. Inundacion", 0, 0, 0);
        addfila11(table11, "19. incendio", 0, 0, 0, 0, "", 0, "22. Otros de excepcion", 0, 0, 0);
        //addfila11(table11,"21. Parotiditis",       1,1,1,1,"",1,"18. Tï¿½tanos neonatal",1,1,1);
        addtitulomain(table11, "REGISTRO DE ENFERMEDADES DE NOTIFICACION SEMANAL");

        document.add(table11);

        //INMUNOPREVENIBLES 
        int NumColumns117 = 11;
        PdfPTable table117 = new PdfPTable(NumColumns117);
        int[] fwidths117 = {30, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8};
        table117.setWidths(fwidths117);
        table117.setWidthPercentage(100);

        String sCampos117[] = {"INMUNOPREVENIBLES", "< 6 meses", "6 m a 1 año", "1 a 4 años", "5 a 9 años", "10 a 14 años", "15 a 19 años", "20 a 39 años", "40 a 49 años", "50 a 59 años", "> 60 años"};

        for (int i = 0; i < NumColumns117; i++) {
            cell = new PdfPCell(new Phrase(sCampos117[i], CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setGrayFill(0.8f);
            table117.addCell(cell);
        }

        document.add(table117);

        int NumColumns136 = 21;
        PdfPTable table136 = new PdfPTable(NumColumns136);
        int[] fwidths136 = {30, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4};
        table136.setWidths(fwidths136);
        table136.setWidthPercentage(100);

        String sCampos136[] = {"", "M", "F", "M", "F", "M", "F", "M", "F", "M", "F", "M", "F", "M", "F", "M", "F", "M", "F", "M", "F"};

        for (int i = 0; i < NumColumns136; i++) {
            cell = new PdfPCell(new Phrase(sCampos136[i], CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setGrayFill(0.8f);
            table136.addCell(cell);
        }

        Cuadernos l24 = (Cuadernos) lista302.get(15);  /////Tï¿½tanos neonatal/adulto 
        if (l24.getSuma22() == 22 && l24.getSuma21() > 0) {
            addfila135(table136, "23. Tetanos neonatal/adulto", l24.getSuma1(), l24.getSuma2(), l24.getSuma3(), l24.getSuma4(), l24.getSuma5(), l24.getSuma6(), l24.getSuma7(), l24.getSuma8(), l24.getSuma9(), l24.getSuma10(), l24.getSuma11(), l24.getSuma12(), l24.getSuma13(), l24.getSuma14(), l24.getSuma15(), l24.getSuma16(), l24.getSuma17(), l24.getSuma18(), l24.getSuma19(), l24.getSuma20());
        } else {
            addfila135(table136, "23. Tetanos neonatal/adulto", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        }

        Cuadernos l25 = (Cuadernos) lista302.get(16);   /////Hepatitis viral A
        if (l25.getSuma22() == 23 && l25.getSuma21() > 0) {
            addfila135(table136, "24. Hepatitis viral A*", l25.getSuma1(), l25.getSuma2(), l25.getSuma3(), l25.getSuma4(), l25.getSuma5(), l25.getSuma6(), l25.getSuma7(), l25.getSuma8(), l25.getSuma9(), l25.getSuma10(), l25.getSuma11(), l25.getSuma12(), l25.getSuma13(), l25.getSuma14(), l25.getSuma15(), l25.getSuma16(), l25.getSuma17(), l25.getSuma18(), l25.getSuma19(), l25.getSuma20());
        } else {
            addfila135(table136, "24. Hepatitis viral A*", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        }

        Cuadernos l26 = (Cuadernos) lista302.get(17);  /////Hepatitis viral B o C 
        if (l26.getSuma22() == 24 && l26.getSuma21() > 0) {
            addfila135(table136, "25. Hepatitis viral B o C*", l26.getSuma1(), l26.getSuma2(), l26.getSuma3(), l26.getSuma4(), l26.getSuma5(), l26.getSuma6(), l26.getSuma7(), l26.getSuma8(), l26.getSuma9(), l26.getSuma10(), l26.getSuma11(), l26.getSuma12(), l26.getSuma13(), l26.getSuma14(), l26.getSuma15(), l26.getSuma16(), l26.getSuma17(), l26.getSuma18(), l26.getSuma19(), l26.getSuma20());
        } else {
            addfila135(table136, "25. Hepatitis viral B o C*", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        }

        Cuadernos l26c = (Cuadernos) lista302.get(19);   /////Parotiditis
        if (l26c.getSuma22() == 25 && l26c.getSuma21() > 0) {
            addfila135(table136, "26. Parotiditis", l26c.getSuma1(), l26c.getSuma2(), l26c.getSuma3(), l26c.getSuma4(), l26c.getSuma5(), l26c.getSuma6(), l26c.getSuma7(), l26c.getSuma8(), l26c.getSuma9(), l26c.getSuma10(), l26c.getSuma11(), l26c.getSuma12(), l26c.getSuma13(), l26c.getSuma14(), l26c.getSuma15(), l26c.getSuma16(), l26c.getSuma17(), l26c.getSuma18(), l26c.getSuma19(), l26c.getSuma20());
        } else {
            addfila135(table136, "26. Parotiditis", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        }

        Cuadernos l26d = (Cuadernos) lista302.get(20);   /////Varicela
        if (l26d.getSuma22() == 26 && l26d.getSuma21() > 0) {
            addfila135(table136, "27. Varicela", l26d.getSuma1(), l26d.getSuma2(), l26d.getSuma3(), l26d.getSuma4(), l26d.getSuma5(), l26d.getSuma6(), l26d.getSuma7(), l26d.getSuma8(), l26d.getSuma9(), l26d.getSuma10(), l26d.getSuma11(), l26d.getSuma12(), l26d.getSuma13(), l26d.getSuma14(), l26d.getSuma15(), l26d.getSuma16(), l26d.getSuma17(), l26d.getSuma18(), l26d.getSuma19(), l26d.getSuma20());
        } else {
            addfila135(table136, "27. Varicela", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        }

        document.add(table136);

        //INFECCIONES DE TRANSMISION SEXUAL
        int NumColumns122 = 11;
        PdfPTable table122 = new PdfPTable(NumColumns122);
        int[] fwidths122 = {30, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8};
        table122.setWidths(fwidths122);
        table122.setWidthPercentage(100);

        String sCampos122[] = {"INFECCIONES DE TRANSMISION SEXUAL", "< 6 meses", "6 m a 1 año", "1 a 4 años", "5 a 9 años", "10 a 14 años", "15 a 19 años", "20 a 39 años", "40 a 49 años", "50 a 59 años", "> 60 años"};

        for (int i = 0; i < NumColumns122; i++) {
            cell = new PdfPCell(new Phrase(sCampos122[i], CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setGrayFill(0.8f);
            table122.addCell(cell);
        }

        document.add(table122);

        int NumColumns130 = 21;
        PdfPTable table130 = new PdfPTable(NumColumns130);
        int[] fwidths130 = {30, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4};
        table130.setWidths(fwidths130);
        table130.setWidthPercentage(100);

        String sCampos130[] = {"", "M", "F", "M", "F", "M", "F", "M", "F", "M", "F", "M", "F", "M", "F", "M", "F", "M", "F", "M", "F"};
        // addfila7(table130,"K","","M","","M","","M","","M","","M","");
        for (int i = 0; i < NumColumns130; i++) {
            cell = new PdfPCell(new Phrase(sCampos130[i], CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setGrayFill(0.8f);
            table130.addCell(cell);
        }

        Cuadernos l27 = (Cuadernos) lista302.get(21);
        if (l27.getSuma22() == 28 && l27.getSuma21() > 0) {
            addfila135(table130, "28. ulcera genital ", l27.getSuma1(), l27.getSuma2(), l27.getSuma3(), l27.getSuma4(), l27.getSuma5(), l27.getSuma6(), l27.getSuma7(), l27.getSuma8(), l27.getSuma9(), l27.getSuma10(), l27.getSuma11(), l27.getSuma12(), l27.getSuma13(), l27.getSuma14(), l27.getSuma15(), l27.getSuma16(), l27.getSuma17(), l27.getSuma18(), l27.getSuma19(), l27.getSuma20());
        } else {
            addfila135(table130, "28. ulcera genital ", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        }

        Cuadernos l28 = (Cuadernos) lista302.get(22);
        if (l28.getSuma22() == 29 && l28.getSuma21() > 0) {
            addfila135(table130, "29. Sifilis Congï¿½nita", l28.getSuma1(), l28.getSuma2(), l28.getSuma3(), l28.getSuma4(), l28.getSuma5(), l28.getSuma6(), l28.getSuma7(), l28.getSuma8(), l28.getSuma9(), l28.getSuma10(), l28.getSuma11(), l28.getSuma12(), l28.getSuma13(), l28.getSuma14(), l28.getSuma15(), l28.getSuma16(), l28.getSuma17(), l28.getSuma18(), l28.getSuma19(), l28.getSuma20());
        } else {
            addfila135(table130, "29. Sifilis Congï¿½nita", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        }

        Cuadernos l29 = (Cuadernos) lista302.get(23);
        if (l29.getSuma22() == 30 && l29.getSuma21() > 0) {
            addfila135(table130, "30. Sifilis en la mujer embarazada*", l29.getSuma1(), l29.getSuma2(), l29.getSuma3(), l29.getSuma4(), l29.getSuma5(), l29.getSuma6(), l29.getSuma7(), l29.getSuma8(), l29.getSuma9(), l29.getSuma10(), l29.getSuma11(), l29.getSuma12(), l29.getSuma13(), l29.getSuma14(), l29.getSuma15(), l29.getSuma16(), l29.getSuma17(), l29.getSuma18(), l29.getSuma19(), l29.getSuma20());
        } else {
            addfila135(table130, "30. Sifilis en la mujer embarazada*", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        }

        Cuadernos l30 = (Cuadernos) lista302.get(24);
        if (l30.getSuma22() == 31 && l30.getSuma21() > 0) {
            addfila135(table130, "31. Flujo uretral/vaginal", l30.getSuma1(), l30.getSuma2(), l30.getSuma3(), l30.getSuma4(), l30.getSuma5(), l30.getSuma6(), l30.getSuma7(), l30.getSuma8(), l30.getSuma9(), l30.getSuma10(), l30.getSuma11(), l30.getSuma12(), l30.getSuma13(), l30.getSuma14(), l30.getSuma15(), l30.getSuma16(), l30.getSuma17(), l30.getSuma18(), l30.getSuma19(), l30.getSuma20());
        } else {
            addfila135(table130, "31. Flujo uretral/vaginal", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        }

        Cuadernos l31 = (Cuadernos) lista302.get(25);
        if (l31.getSuma22() == 32 && l31.getSuma21() > 0) {
            addfila135(table130, "32. Gonorrea", l31.getSuma1(), l31.getSuma2(), l31.getSuma3(), l31.getSuma4(), l31.getSuma5(), l31.getSuma6(), l31.getSuma7(), l31.getSuma8(), l31.getSuma9(), l31.getSuma10(), l31.getSuma11(), l31.getSuma12(), l31.getSuma13(), l31.getSuma14(), l31.getSuma15(), l31.getSuma16(), l31.getSuma17(), l31.getSuma18(), l31.getSuma19(), l31.getSuma20());
        } else {
            addfila135(table130, "32. Gonorrea", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        }

        Cuadernos l32 = (Cuadernos) lista302.get(26);
        if (l32.getSuma22() == 33 && l32.getSuma21() > 0) {
            addfila135(table130, "33. Verruga Genital", l32.getSuma1(), l32.getSuma2(), l32.getSuma3(), l32.getSuma4(), l32.getSuma5(), l32.getSuma6(), l32.getSuma7(), l32.getSuma8(), l32.getSuma9(), l32.getSuma10(), l32.getSuma11(), l32.getSuma12(), l32.getSuma13(), l32.getSuma14(), l32.getSuma15(), l32.getSuma16(), l32.getSuma17(), l32.getSuma18(), l32.getSuma19(), l32.getSuma20());
        } else {
            addfila135(table130, "33. Verruga Genital", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        }

        Cuadernos l33 = (Cuadernos) lista302.get(27);
        if (l33.getSuma22() == 34 && l33.getSuma21() > 0) {
            addfila135(table130, "34. Prueba rapida de VIH en Mujer Embarazada", l33.getSuma1(), l33.getSuma2(), l33.getSuma3(), l33.getSuma4(), l33.getSuma5(), l33.getSuma6(), l33.getSuma7(), l33.getSuma8(), l33.getSuma9(), l33.getSuma10(), l33.getSuma11(), l33.getSuma12(), l33.getSuma13(), l33.getSuma14(), l33.getSuma15(), l33.getSuma16(), l33.getSuma17(), l33.getSuma18(), l33.getSuma19(), l33.getSuma20());
        } else {
            addfila135(table130, "34. Prueba rapida de VIH en Mujer Embarazada", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        }

        Cuadernos l34 = (Cuadernos) lista302.get(28);
        if (l34.getSuma22() == 35 && l34.getSuma21() > 0) {
            addfila135(table130, "35. Prueba rapida de VIH en Poblacion General", l34.getSuma1(), l34.getSuma2(), l34.getSuma3(), l34.getSuma4(), l34.getSuma5(), l34.getSuma6(), l34.getSuma7(), l34.getSuma8(), l34.getSuma9(), l34.getSuma10(), l34.getSuma11(), l34.getSuma12(), l34.getSuma13(), l34.getSuma14(), l34.getSuma15(), l34.getSuma16(), l34.getSuma17(), l34.getSuma18(), l34.getSuma19(), l34.getSuma20());
        } else {
            addfila135(table130, "35. Prueba rapida de VIH en Poblacion General", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        }

        addtitulomain(table130, "* Registrar los casos confirmados por laboratorio");
        document.add(table130);

//OTRAS INFECCIONES
        int NumColumns118 = 11;
        PdfPTable table118 = new PdfPTable(NumColumns118);
        int[] fwidths118 = {30, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8};
        table118.setWidths(fwidths118);
        table118.setWidthPercentage(100);

        String sCampos118[] = {"OTRAS INFECCIONES", "< 6 meses", "6 m a 1 año", "1 a 4 años", "5 a 9 años", "10 a 14 años", "15 a 19 años", "20 a 39 años", "40 a 49 años", "50 a 59 años", "> 60 años"};

        for (int i = 0; i < NumColumns118; i++) {
            cell = new PdfPCell(new Phrase(sCampos118[i], CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setGrayFill(0.8f);
            table118.addCell(cell);
        }

        document.add(table118);

        int NumColumns131 = 21;
        PdfPTable table131 = new PdfPTable(NumColumns131);
        int[] fwidths131 = {30, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4};
        table131.setWidths(fwidths131);
        table131.setWidthPercentage(100);

        String sCampos131[] = {"", "M", "F", "M", "F", "M", "F", "M", "F", "M", "F", "M", "F", "M", "F", "M", "F", "M", "F", "M", "F"};
        for (int i = 0; i < NumColumns131; i++) {
            cell = new PdfPCell(new Phrase(sCampos131[i], CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setGrayFill(0.8f);
            table131.addCell(cell);
        }

        Cuadernos l36 = (Cuadernos) lista302.get(29);   ////era 23
        if (l36.getSuma22() == 36 && l36.getSuma21() > 0) {
            addfila135(table131, "36. Enfermedad Diarreica Aguda  ", l36.getSuma1(), l36.getSuma2(), l36.getSuma3(), l36.getSuma4(), l36.getSuma5(), l36.getSuma6(), l36.getSuma7(), l36.getSuma8(), l36.getSuma9(), l36.getSuma10(), l36.getSuma11(), l36.getSuma12(), l36.getSuma13(), l36.getSuma14(), l36.getSuma15(), l36.getSuma16(), l36.getSuma17(), l36.getSuma18(), l36.getSuma19(), l36.getSuma20());
        } else {
            addfila135(table131, "36. Enfermedad Diarreica Aguda  ", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        }

        Cuadernos l37 = (Cuadernos) lista302.get(30);   ///era24
        if (l37.getSuma22() == 37 && l37.getSuma21() > 0) {
            addfila135(table131, "37. IRA sin neumonia", l37.getSuma1(), l37.getSuma2(), l37.getSuma3(), l37.getSuma4(), l37.getSuma5(), l37.getSuma6(), l37.getSuma7(), l37.getSuma8(), l37.getSuma9(), l37.getSuma10(), l37.getSuma11(), l37.getSuma12(), l37.getSuma13(), l37.getSuma14(), l37.getSuma15(), l37.getSuma16(), l37.getSuma17(), l37.getSuma18(), l37.getSuma19(), l37.getSuma20());
        } else {
            addfila135(table131, "37. IRA sin neumonia", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        }

        Cuadernos l38 = (Cuadernos) lista302.get(31);
        if (l38.getSuma22() == 38 && l38.getSuma21() > 0) {
            addfila135(table131, "38. Neumonia", l38.getSuma1(), l38.getSuma2(), l38.getSuma3(), l38.getSuma4(), l38.getSuma5(), l38.getSuma6(), l38.getSuma7(), l38.getSuma8(), l38.getSuma9(), l38.getSuma10(), l38.getSuma11(), l38.getSuma12(), l38.getSuma13(), l38.getSuma14(), l38.getSuma15(), l38.getSuma16(), l38.getSuma17(), l38.getSuma18(), l38.getSuma19(), l38.getSuma20());
        } else {
            addfila135(table131, "38. Neumonia", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        }

        Cuadernos l39 = (Cuadernos) lista302.get(36);
        if (l39.getSuma22() == 43 && l39.getSuma21() > 0) {
            addfila135(table131, "39. No de personas expuestas al virus rabico ", l39.getSuma1(), l39.getSuma2(), l39.getSuma3(), l39.getSuma4(), l39.getSuma5(), l39.getSuma6(), l39.getSuma7(), l39.getSuma8(), l39.getSuma9(), l39.getSuma10(), l39.getSuma11(), l39.getSuma12(), l39.getSuma13(), l39.getSuma14(), l39.getSuma15(), l39.getSuma16(), l39.getSuma17(), l39.getSuma18(), l39.getSuma19(), l39.getSuma20());
        } else {
            addfila135(table131, "39. No de personas expuestas al virus rabico ", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        }

        Cuadernos l40 = (Cuadernos) lista302.get(58);
        if (l40.getSuma22() == 70 && l40.getSuma21() > 0) {
            addfila135(table131, "40. No de personas con Mordedura de Serpientes", l40.getSuma1(), l40.getSuma2(), l40.getSuma3(), l40.getSuma4(), l40.getSuma5(), l40.getSuma6(), l40.getSuma7(), l40.getSuma8(), l40.getSuma9(), l40.getSuma10(), l40.getSuma11(), l40.getSuma12(), l40.getSuma13(), l40.getSuma14(), l40.getSuma15(), l40.getSuma16(), l40.getSuma17(), l40.getSuma18(), l40.getSuma19(), l40.getSuma20());
        } else {
            addfila135(table131, "40. No de personas con Mordedura de Serpientes", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        }

        Cuadernos l41 = (Cuadernos) lista302.get(59);
        if (l41.getSuma22() == 71 && l41.getSuma21() > 0) {
            addfila135(table131, "41. No pers. con picadura animales ponzonosos", l41.getSuma1(), l41.getSuma2(), l41.getSuma3(), l41.getSuma4(), l41.getSuma5(), l41.getSuma6(), l41.getSuma7(), l41.getSuma8(), l41.getSuma9(), l41.getSuma10(), l41.getSuma11(), l41.getSuma12(), l41.getSuma13(), l41.getSuma14(), l41.getSuma15(), l41.getSuma16(), l41.getSuma17(), l41.getSuma18(), l41.getSuma19(), l41.getSuma20());
        } else {
            addfila135(table131, "41. No pers. con picadura animales ponzonosos", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        }

        document.add(table131);

//TUBERCULOSIS Y LEPRA
        int NumColumns119 = 11;
        PdfPTable table119 = new PdfPTable(NumColumns119);
        int[] fwidths119 = {30, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8};
        table119.setWidths(fwidths119);
        table119.setWidthPercentage(100);

        String sCampos119[] = {"TUBERCULOSIS Y LEPRA", "< 6 meses", "6 m a 1 año", "1 a 4 años", "5 a 9 años", "10 a 14 años", "15 a 19 años", "20 a 39 años", "40 a 49 años", "50 a 59 años", "> 60 años"};

        for (int i = 0; i < NumColumns119; i++) {
            cell = new PdfPCell(new Phrase(sCampos119[i], CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setGrayFill(0.8f);
            table119.addCell(cell);
        }

        document.add(table119);

        int NumColumns132 = 21;
        PdfPTable table132 = new PdfPTable(NumColumns132);
        int[] fwidths132 = {30, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4};
        table132.setWidths(fwidths132);
        table132.setWidthPercentage(100);

        String sCampos132[] = {"", "M", "F", "M", "F", "M", "F", "M", "F", "M", "F", "M", "F", "M", "F", "M", "F", "M", "F", "M", "F"};
        // addfila7(table132,"K","","M","","M","","M","","M","","M","");
        for (int i = 0; i < NumColumns132; i++) {
            cell = new PdfPCell(new Phrase(sCampos132[i], CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setGrayFill(0.8f);
            table132.addCell(cell);
        }

        Cuadernos l42 = (Cuadernos) lista302.get(38);
        if (l42.getSuma22() == 45 && l42.getSuma21() > 0) {
            addfila135(table132, "42. Tuberculosis meningea*", l42.getSuma1(), l42.getSuma2(), l42.getSuma3(), l42.getSuma4(), l42.getSuma5(), l42.getSuma6(), l42.getSuma7(), l42.getSuma8(), l42.getSuma9(), l42.getSuma10(), l42.getSuma11(), l42.getSuma12(), l42.getSuma13(), l42.getSuma14(), l42.getSuma15(), l42.getSuma16(), l42.getSuma17(), l42.getSuma18(), l42.getSuma19(), l42.getSuma20());
        } else {
            addfila135(table132, "42. Tuberculosis meningea*", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        }

        addfila135(table132, "43. RAFA (leves y moderadas)", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        addfila135(table132, "44. RAFA (graves)", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);

        Cuadernos l45 = (Cuadernos) lista302.get(39);
        if (l45.getSuma22() == 48 && l45.getSuma21() > 0) {
            addfila135(table132, "45. Lepra paucibacilar*", l45.getSuma1(), l45.getSuma2(), l45.getSuma3(), l45.getSuma4(), l45.getSuma5(), l45.getSuma6(), l45.getSuma7(), l45.getSuma8(), l45.getSuma9(), l45.getSuma10(), l45.getSuma11(), l45.getSuma12(), l45.getSuma13(), l45.getSuma14(), l45.getSuma15(), l45.getSuma16(), l45.getSuma17(), l45.getSuma18(), l45.getSuma19(), l45.getSuma20());
        } else {
            addfila135(table132, "45. Lepra paucibacilar*", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        }

        Cuadernos l46 = (Cuadernos) lista302.get(40);
        if (l46.getSuma22() == 49 && l46.getSuma21() > 0) {
            addfila135(table132, "46. Lepra multibacilar*", l46.getSuma1(), l46.getSuma2(), l46.getSuma3(), l46.getSuma4(), l46.getSuma5(), l46.getSuma6(), l46.getSuma7(), l46.getSuma8(), l46.getSuma9(), l46.getSuma10(), l46.getSuma11(), l46.getSuma12(), l46.getSuma13(), l46.getSuma14(), l46.getSuma15(), l46.getSuma16(), l46.getSuma17(), l46.getSuma18(), l46.getSuma19(), l46.getSuma20());
        } else {
            addfila135(table132, "46. Lepra multibacilar*", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        }

        document.add(table132);

//VIOLENCIA HECHOS DE TRANSITO Y ACCIDENTES
        int NumColumns128 = 11;
        PdfPTable table128 = new PdfPTable(NumColumns128);
        int[] fwidths128 = {30, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8};
        table128.setWidths(fwidths128);
        table128.setWidthPercentage(100);

        String sCampos128[] = {"VIOLENCIA, HECHOS DE TRANSITO Y ACCIDENTES", "< 6 meses", "6 m a 1 año", "1 a 4 años", "5 a 9 años", "10 a 14 años", "15 a 19 años", "20 a 39 años", "40 a 49 años", "50 a 59 años", "> 60 años"};

        for (int i = 0; i < NumColumns128; i++) {
            cell = new PdfPCell(new Phrase(sCampos128[i], CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setGrayFill(0.8f);
            table128.addCell(cell);
        }

        document.add(table128);

        int NumColumns133 = 21;
        PdfPTable table133 = new PdfPTable(NumColumns133);
        int[] fwidths133 = {30, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4};
        table133.setWidths(fwidths133);
        table133.setWidthPercentage(100);

        String sCampos133[] = {"", "M", "F", "M", "F", "M", "F", "M", "F", "M", "F", "M", "F", "M", "F", "M", "F", "M", "F", "M", "F"};
        // addfila7(table133,"K","","M","","M","","M","","M","","M","");
        for (int i = 0; i < NumColumns133; i++) {
            cell = new PdfPCell(new Phrase(sCampos133[i], CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setGrayFill(0.8f);
            table133.addCell(cell);
        }

        Cuadernos l47 = (Cuadernos) lista302.get(41);
        if (l47.getSuma22() == 50 && l47.getSuma21() > 0) {
            addfila135(table133, "47. Intrafamiliar / Domestica", l47.getSuma1(), l47.getSuma2(), l47.getSuma3(), l47.getSuma4(), l47.getSuma5(), l47.getSuma6(), l47.getSuma7(), l47.getSuma8(), l47.getSuma9(), l47.getSuma10(), l47.getSuma11(), l47.getSuma12(), l47.getSuma13(), l47.getSuma14(), l47.getSuma15(), l47.getSuma16(), l47.getSuma17(), l47.getSuma18(), l47.getSuma19(), l47.getSuma20());
        } else {
            addfila135(table133, "47. Intrafamiliar / Domestica", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        }

        Cuadernos l48 = (Cuadernos) lista302.get(32);
        if (l48.getSuma22() == 51 && l48.getSuma21() > 0) {
            addfila135(table133, "48. Otras violencias (sexual,fisica,psicologica,descuido)", l48.getSuma1(), l48.getSuma2(), l48.getSuma3(), l48.getSuma4(), l48.getSuma5(), l48.getSuma6(), l48.getSuma7(), l48.getSuma8(), l48.getSuma9(), l48.getSuma10(), l48.getSuma11(), l48.getSuma12(), l48.getSuma13(), l48.getSuma14(), l48.getSuma15(), l48.getSuma16(), l48.getSuma17(), l48.getSuma18(), l48.getSuma19(), l48.getSuma20());
        } else {
            addfila135(table133, "48. Otras violencias (sexual,fisica,psicologica,descuido)", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        }

        Cuadernos l49 = (Cuadernos) lista302.get(38);
        if (l49.getSuma22() == 49 && l49.getSuma21() > 0) {
            addfila135(table133, "49. No casos presentados transito", l49.getSuma1(), l49.getSuma2(), l49.getSuma3(), l49.getSuma4(), l49.getSuma5(), l49.getSuma6(), l49.getSuma7(), l49.getSuma8(), l49.getSuma9(), l49.getSuma10(), l49.getSuma11(), l49.getSuma12(), l49.getSuma13(), l49.getSuma14(), l49.getSuma15(), l49.getSuma16(), l49.getSuma17(), l49.getSuma18(), l49.getSuma19(), l49.getSuma20());
        } else {
            addfila135(table133, "49. No casos presentados transito", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        }

        Cuadernos l50 = (Cuadernos) lista302.get(39);
        if (l50.getSuma22() == 50 && l50.getSuma21() > 0) {
            addfila135(table133, "50. No casos otros accidentes", l50.getSuma1(), l50.getSuma2(), l50.getSuma3(), l50.getSuma4(), l50.getSuma5(), l50.getSuma6(), l50.getSuma7(), l50.getSuma8(), l50.getSuma9(), l50.getSuma10(), l50.getSuma11(), l50.getSuma12(), l50.getSuma13(), l50.getSuma14(), l50.getSuma15(), l50.getSuma16(), l50.getSuma17(), l50.getSuma18(), l50.getSuma19(), l50.getSuma20());
        } else {
            addfila135(table133, "50. No casos otros accidentes", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        }

        document.add(table133);

//INTOXICACIONES
        int NumColumns120 = 11;
        PdfPTable table120 = new PdfPTable(NumColumns120);
        int[] fwidths120 = {30, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8};
        table120.setWidths(fwidths120);
        table120.setWidthPercentage(100);

        String sCampos120[] = {"INTOXICACIONES", "< 6 meses", "6 m a 1 año", "1 a 4 años", "5 a 9 años", "10 a 14 años", "15 a 19 años", "20 a 39 años", "40 a 49 años", "50 a 59 años", "> 60 años"};

        for (int i = 0; i < NumColumns120; i++) {
            cell = new PdfPCell(new Phrase(sCampos120[i], CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setGrayFill(0.8f);
            table120.addCell(cell);
        }

        document.add(table120);

        int NumColumns134 = 21;
        PdfPTable table134 = new PdfPTable(NumColumns134);
        int[] fwidths134 = {30, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4};
        table134.setWidths(fwidths134);
        table134.setWidthPercentage(100);

        String sCampos134[] = {"", "M", "F", "M", "F", "M", "F", "M", "F", "M", "F", "M", "F", "M", "F", "M", "F", "M", "F", "M", "F"};

        for (int i = 0; i < NumColumns134; i++) {
            cell = new PdfPCell(new Phrase(sCampos134[i], CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setGrayFill(0.8f);
            table134.addCell(cell);
        }

        Cuadernos l51 = (Cuadernos) lista302.get(43);
        if (l51.getSuma22() == 54 && l51.getSuma21() > 0) {
            addfila135(table134, "51. Plaguicidas", l51.getSuma1(), l51.getSuma2(), l51.getSuma3(), l51.getSuma4(), l51.getSuma5(), l51.getSuma6(), l51.getSuma7(), l51.getSuma8(), l51.getSuma9(), l51.getSuma10(), l51.getSuma11(), l51.getSuma12(), l51.getSuma13(), l51.getSuma14(), l51.getSuma15(), l51.getSuma16(), l51.getSuma17(), l51.getSuma18(), l51.getSuma19(), l51.getSuma20());
        } else {
            addfila135(table134, "51. Plaguicidas", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        }

        Cuadernos l52 = (Cuadernos) lista302.get(44);
        if (l52.getSuma22() == 55 && l52.getSuma21() > 0) {
            addfila135(table134, "52. Enf. Transmitida por Alimentos (E.T.A.)", l52.getSuma1(), l52.getSuma2(), l52.getSuma3(), l52.getSuma4(), l52.getSuma5(), l52.getSuma6(), l52.getSuma7(), l52.getSuma8(), l52.getSuma9(), l52.getSuma10(), l52.getSuma11(), l52.getSuma12(), l52.getSuma13(), l52.getSuma14(), l52.getSuma15(), l52.getSuma16(), l52.getSuma17(), l52.getSuma18(), l52.getSuma19(), l52.getSuma20());
        } else {
            addfila135(table134, "52. Enf. Transmitida por Alimentos (E.T.A.)", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        }

        Cuadernos l53 = (Cuadernos) lista302.get(45);
        if (l53.getSuma22() == 56 && l53.getSuma21() > 0) {
            addfila135(table134, "53. Otras intoxicaciones (drogas,farmacos, alcohol,etc.)", l53.getSuma1(), l53.getSuma2(), l53.getSuma3(), l53.getSuma4(), l53.getSuma5(), l53.getSuma6(), l53.getSuma7(), l53.getSuma8(), l53.getSuma9(), l53.getSuma10(), l53.getSuma11(), l53.getSuma12(), l53.getSuma13(), l53.getSuma14(), l53.getSuma15(), l53.getSuma16(), l53.getSuma17(), l53.getSuma18(), l53.getSuma19(), l53.getSuma20());
        } else {
            addfila135(table134, "53. Otras intoxicaciones (drogas,farmacos, alcohol,etc.)", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        }

        document.add(table134);

// MORTALIDAD Y ESTADO NUTRICIONAL
        PdfPTable mitablacompleja = new PdfPTable(5);
        int[] filcoltami = {30, 8, 8, 1, 58};
        mitablacompleja.setWidths(filcoltami);
        mitablacompleja.setWidthPercentage(100);

        ///addtitulin(mitablacompleja,"","DENTRO","FUERA ","","ESTADO NUTRICIONAL");
        document.add(mitablacompleja);

        PdfPTable mitablacomplejas = new PdfPTable(7);
        int[] filcoltamis = {30, 8, 8, 1, 30, 24, 4};
        mitablacomplejas.setWidths(filcoltamis);
        mitablacomplejas.setWidthPercentage(100);
        addtitulin7(mitablacomplejas, "MORTALIDAD", "DENTRO", "FUERA", "", "ESTADO NUTRICIONAL", "PESO PARA LA TALLA (P/T)", "IMC");
        document.add(mitablacomplejas);

        PdfPTable mitablacomplejasa = new PdfPTable(11);
        int[] filcoltamisa = {30, 4, 4, 4, 4, 1, 30, 8, 8, 8, 4};
        mitablacomplejasa.setWidths(filcoltamisa);
        mitablacomplejasa.setWidthPercentage(100);
        addtitulin11(mitablacomplejasa, "", "M", "F", "M", "F", "", "CLASIFICACION", "< 1 año", "1 A < 2 añoS", "2 A < 5 añoS", "EMBA");
        document.add(mitablacomplejasa);

        PdfPTable mitablacomper = new PdfPTable(14);
        int[] filcoltamer = {30, 4, 4, 4, 4, 1, 30, 4, 4, 4, 4, 4, 4, 4};
        mitablacomper.setWidths(filcoltamer);
        mitablacomper.setWidthPercentage(100);
        addtitulin14(mitablacomper, "54. Muerte Materna", 0, 0, 0, 0, "", "", "M", "F", "M", "F", "M", "F", "RAZA");
        document.add(mitablacomper);

        PdfPTable mitablacomp = new PdfPTable(14);
        int[] filcoltam = {30, 4, 4, 4, 4, 1, 30, 4, 4, 4, 4, 4, 4, 4};
        mitablacomp.setWidths(filcoltam);
        mitablacomp.setWidthPercentage(100);
        addfila514(mitablacomp, "55. Muerte RN menor de 7 dias", 0, 0, 0, 0, "", "66. Obecidad (O)", d5.getSuma1(), d5.getSuma2(), d5.getSuma3(), d5.getSuma4(), d5.getSuma5(), d5.getSuma6(), dimc.getSuma4());
        addfila514(mitablacomp, "56. Muerte Menor de 1 año", 0, 0, 0, 0, "", "67. Sobrepeso (S)", d5.getSuma7(), d5.getSuma8(), d5.getSuma9(), d5.getSuma10(), d5.getSuma11(), d5.getSuma12(), dimc.getSuma3());
        addfila514(mitablacomp, "57. Muerte Menor de 5 años por diarrea", 0, 0, 0, 0, "", "68. Nutricion normal (N)", d5.getSuma13(), d5.getSuma14(), d5.getSuma15(), d5.getSuma16(), d5.getSuma17(), d5.getSuma18(), dimc.getSuma2());
        addfila514(mitablacomp, "58. Muerte Menor de 5 años por neumonia", 0, 0, 0, 0, "", "69. Desnutricion leve (L)", d5.getSuma19(), d5.getSuma20(), d5.getSuma21(), d5.getSuma22(), d5.getSuma23(), d5.getSuma24(), 0);
        addfila514(mitablacomp, "59. Muerte Menor de 5 años por desnutricion aguda grave", 0, 0, 0, 0, "", "70. Desnutricion Moderada (M)", d5.getSuma25(), d5.getSuma26(), d5.getSuma27(), d5.getSuma28(), d5.getSuma29(), d5.getSuma30(), 0);
        addfila514(mitablacomp, "60. Muerte Menor de 5 años por otras causas", 0, 0, 0, 0, "", "71. Desnutricion grave (G)", d5.getSuma31(), d5.getSuma32(), d5.getSuma33(), d5.getSuma34(), d5.getSuma35(), d5.getSuma36(), 0);
        addfila514(mitablacomp, "61. Otras muertes en mayores de 5 años", 0, 0, 0, 0, "", "72. Embarazada desnutrida", 0, 0, 0, 0, 0, 0, dimc.getSuma1());
        document.add(mitablacomp);

        PdfPTable mitablacompos = new PdfPTable(2);
        int[] filcoltamos = {30, 4};
        mitablacompos.setWidths(filcoltamos);
        mitablacompos.setWidthPercentage(50);
        mitablacompos.setHorizontalAlignment(mitablacompos.ALIGN_LEFT);
        addtitulo3(mitablacompos, "SEXUALIDAD REPRODUCTIVA", "");
        adddatos(mitablacompos, "62. Hemorragia de la 1ra mitad del embarazo (<22 sem) que acabaron en aborto", dimc.getSuma5());
        adddatos(mitablacompos, "63. Hemorragia de la 1ra mitad del embarazo (<22 sem) que continuaron con el embarazo", dimc.getSuma6());
        adddatos(mitablacompos, "64. Embarazo en adolescente (<20 años)", dimc.getSuma7());
        adddatos(mitablacompos, "65. Preeclampsia severa/eclampsia", dimc.getSuma8());

        document.add(mitablacompos);

        /*
     // final de la pagina
     table = new PdfPTable(2);
     int[] fwidthsxm = {50,50}; 
     table.setWidths(fwidthsxm);
     table.setWidthPercentage(100);
     cell = new PdfPCell(new Phrase("DECLARACION JURADA Yo : "+ dato.getNombres(), DATO_FONT));
     cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     cell.setBorder(Rectangle.NO_BORDER);
     table.addCell(cell);
     cell = new PdfPCell(new Phrase("Nombre y Apellidos", DATO_FONT));
     cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     cell.setBorder(Rectangle.NO_BORDER);
     table.addCell(cell);   
     cell = new PdfPCell(new Phrase("Declaro la veracidad de datos del presente formulario", DATO_FONT));
     cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     cell.setBorder(Rectangle.NO_BORDER);
     table.addCell(cell);   
     cell = new PdfPCell(new Phrase("Firma:  .................", DATO_FONT));
     cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     cell.setBorder(Rectangle.NO_BORDER);
     table.addCell(cell);
     document.add(table);
     p = new Paragraph("Fecha de emision : "+format(new Date(),"dd/MM/yyyy") , DATO_FONT );
     document.add(p);
         */
    }

    public static void addfila7(PdfPTable table, String a, String b, String c, String d, String e, String f, String g, String h, String i, String j, String k, String l) {

        PdfPCell cell;
        cell = new PdfPCell(new Phrase(a, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(b, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(c, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(d, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(e, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(f, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(g, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(g, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(h, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(i, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(j, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(k, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(l, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
    }

    public static void addfila4(PdfPTable table, String a, int c, int d) {

        PdfPCell cell;
        cell = new PdfPCell(new Phrase(a, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(Integer.toString(c), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(Integer.toString(d), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
    }

    public static void addfila13(PdfPTable table, String a, int c, int d, int e, int f, int g, int p, String h, String j, int k, int l, int m, int n, int o, int q) {
        DecimalFormat df = new DecimalFormat("###,##0");

        PdfPCell cell;
        cell = new PdfPCell(new Phrase(a, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase((c != 0) ? df.format(c) : "o", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase((d != 0) ? df.format(d) : "o", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase((e != 0) ? df.format(e) : "o", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase((f != 0) ? df.format(f) : "o", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase((g != 0) ? df.format(g) : "o", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase((p != 0) ? df.format(p) : "o", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(h, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(j, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase((k != 0) ? df.format(k) : "o", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase((l != 0) ? df.format(l) : "o", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase((m != 0) ? df.format(m) : "o", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase((n != 0) ? df.format(n) : "o", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase((o != 0) ? df.format(o) : "o", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase((q != 0) ? df.format(q) : "o", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
    }

    public static void addfila514(PdfPTable table, String a, int c, int d, int e, int f, String g, String h, int k, int l, int m, int n, int o, int p, int q) {
        DecimalFormat df = new DecimalFormat("###,##0");

        PdfPCell cell;
        cell = new PdfPCell(new Phrase(a, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase((c != 0) ? df.format(c) : "o", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase((d != 0) ? df.format(d) : "o", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase((e != 0) ? df.format(e) : "o", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase((f != 0) ? df.format(f) : "o", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(g, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(h, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase((k != 0) ? df.format(k) : "o", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase((l != 0) ? df.format(l) : "o", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase((m != 0) ? df.format(m) : "o", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase((n != 0) ? df.format(n) : "o", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase((o != 0) ? df.format(o) : "o", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase((p != 0) ? df.format(p) : "o", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase((q != 0) ? df.format(q) : "o", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
    }

    public static void addfila135(PdfPTable table, String a, int c, int d, int e, int f, int g, int k, int l, int m, int n, int o, int p, int q, int r, int s, int t, int u, int v, int w, int x, int y) {
        DecimalFormat df = new DecimalFormat("###,##0");

        PdfPCell cell;
        cell = new PdfPCell(new Phrase(a, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase((c != 0) ? df.format(c) : "o", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase((d != 0) ? df.format(d) : "o", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase((e != 0) ? df.format(e) : "o", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase((f != 0) ? df.format(f) : "o", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase((g != 0) ? df.format(g) : "o", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase((k != 0) ? df.format(k) : "o", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase((l != 0) ? df.format(l) : "o", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase((m != 0) ? df.format(m) : "o", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase((n != 0) ? df.format(n) : "o", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase((o != 0) ? df.format(o) : "o", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase((p != 0) ? df.format(p) : "o", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase((q != 0) ? df.format(q) : "o", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase((r != 0) ? df.format(r) : "o", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase((s != 0) ? df.format(s) : "o", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase((t != 0) ? df.format(t) : "o", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase((u != 0) ? df.format(u) : "o", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase((v != 0) ? df.format(v) : "o", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase((w != 0) ? df.format(w) : "o", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase((x != 0) ? df.format(x) : "o", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase((y != 0) ? df.format(y) : "o", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

    }

    public static void addfila11(PdfPTable table, String a, int c, int d, int e, int f, String g, int k, String l, int m, int n, int o) {
        DecimalFormat df = new DecimalFormat("###,##0");

        PdfPCell cell;
        cell = new PdfPCell(new Phrase(a, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase((c != 0) ? df.format(c) : "o", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase((d != 0) ? df.format(d) : "o", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase((e != 0) ? df.format(e) : "o", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase((f != 0) ? df.format(f) : "o", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(g, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(Integer.toString(k), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(l, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase((m != 0) ? df.format(m) : "o", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase((n != 0) ? df.format(n) : "o", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase((o != 0) ? df.format(o) : "o", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

    }

    public static void addfila3(PdfPTable table, String a, int c) {

        PdfPCell cell;
        cell = new PdfPCell(new Phrase(a, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(Integer.toString(c), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

    }

    public static void addtitulomain(PdfPTable table, String a) {

        PdfPCell cell;
        cell = new PdfPCell(new Phrase(a, CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(13);
        table.addCell(cell);
    }

    public static void addtitulo3(PdfPTable table, String a, String c) {

        PdfPCell cell;
        cell = new PdfPCell(new Phrase(a, CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setGrayFill(0.8f);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(c, CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.8f);
        table.addCell(cell);
    }

    public static void adddatos(PdfPTable table, String a, int c) {

        PdfPCell cell;
        cell = new PdfPCell(new Phrase(a, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);

        table.addCell(cell);
        cell = new PdfPCell(new Phrase(Integer.toString(c), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);

        table.addCell(cell);
    }

    public static void addtitulin(PdfPTable table, String a, String b, String c, String d, String e) {

        PdfPCell cell;
        cell = new PdfPCell(new Phrase(a, CABEZA_COLUMNAT_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.8f);

        table.addCell(cell);
        cell = new PdfPCell(new Phrase(b, CABEZA_COLUMNAT_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.8f);

        table.addCell(cell);
        cell = new PdfPCell(new Phrase(c, CABEZA_COLUMNAT_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.8f);

        table.addCell(cell);
        cell = new PdfPCell(new Phrase(d, CABEZA_COLUMNAT_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.8f);

        table.addCell(cell);
        cell = new PdfPCell(new Phrase(e, CABEZA_COLUMNAT_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.8f);

        table.addCell(cell);

    }

    public static void addtitulin7(PdfPTable table, String a, String b, String c, String d, String e, String f, String g) {

        PdfPCell cell;
        cell = new PdfPCell(new Phrase(a, CABEZA_COLUMNAT_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.8f);

        table.addCell(cell);
        cell = new PdfPCell(new Phrase(b, CABEZA_COLUMNAT_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.8f);

        table.addCell(cell);
        cell = new PdfPCell(new Phrase(c, CABEZA_COLUMNAT_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.8f);

        table.addCell(cell);
        cell = new PdfPCell(new Phrase(d, CABEZA_COLUMNAT_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.8f);

        table.addCell(cell);
        cell = new PdfPCell(new Phrase(e, CABEZA_COLUMNAT_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.8f);

        table.addCell(cell);
        cell = new PdfPCell(new Phrase(f, CABEZA_COLUMNAT_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.8f);

        table.addCell(cell);
        cell = new PdfPCell(new Phrase(g, CABEZA_COLUMNAT_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.8f);

        table.addCell(cell);
    }

    public static void addtitulin11(PdfPTable table, String a, String b, String c, String d, String e, String f, String g, String h, String i, String j, String k) {

        PdfPCell cell;
        cell = new PdfPCell(new Phrase(a, CABEZA_COLUMNAT_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.8f);

        table.addCell(cell);
        cell = new PdfPCell(new Phrase(b, CABEZA_COLUMNAT_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.8f);

        table.addCell(cell);
        cell = new PdfPCell(new Phrase(c, CABEZA_COLUMNAT_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.8f);

        table.addCell(cell);
        cell = new PdfPCell(new Phrase(d, CABEZA_COLUMNAT_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.8f);

        table.addCell(cell);
        cell = new PdfPCell(new Phrase(e, CABEZA_COLUMNAT_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.8f);

        table.addCell(cell);
        cell = new PdfPCell(new Phrase(f, CABEZA_COLUMNAT_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.8f);

        table.addCell(cell);
        cell = new PdfPCell(new Phrase(g, CABEZA_COLUMNAT_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.8f);

        table.addCell(cell);
        cell = new PdfPCell(new Phrase(h, CABEZA_COLUMNAT_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.8f);

        table.addCell(cell);
        cell = new PdfPCell(new Phrase(i, CABEZA_COLUMNAT_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.8f);

        table.addCell(cell);
        cell = new PdfPCell(new Phrase(j, CABEZA_COLUMNAT_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.8f);

        table.addCell(cell);
        cell = new PdfPCell(new Phrase(k, CABEZA_COLUMNAT_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.8f);

        table.addCell(cell);
    }

    public static void addtitulin14(PdfPTable table, String a, int c, int d, int e, int f, String g, String h, String j, String k, String l, String m, String n, String o, String q) {

        DecimalFormat df = new DecimalFormat("###,##0");

        PdfPCell cell;
        cell = new PdfPCell(new Phrase(a, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase((c != 0) ? df.format(c) : "", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase((d != 0) ? df.format(d) : "", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase((e != 0) ? df.format(e) : "", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase((f != 0) ? df.format(f) : "", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(g, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(h, CABEZA_COLUMNAT_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setGrayFill(0.8f);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(j, CABEZA_COLUMNAT_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setGrayFill(0.8f);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(k, CABEZA_COLUMNAT_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.8f);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(l, CABEZA_COLUMNAT_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.8f);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(m, CABEZA_COLUMNAT_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.8f);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(n, CABEZA_COLUMNAT_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.8f);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(o, CABEZA_COLUMNAT_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.8f);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(q, CABEZA_COLUMNAT_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
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
