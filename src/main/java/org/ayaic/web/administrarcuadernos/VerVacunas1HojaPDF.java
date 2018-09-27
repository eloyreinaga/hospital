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

public class VerVacunas1HojaPDF extends AbstractPdfView {

    private static final Font TITULO_FONT = new Font(Font.HELVETICA, 12, Font.BOLD, Color.black);
    private static final Font CABEZA_COLUMNA_FONT = new Font(Font.HELVETICA, 8, Font.ITALIC, Color.black);
    private static final Font DATO_FONT = new Font(Font.HELVETICA, 8, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_BOLD = new Font(Font.HELVETICA, 8, Font.BOLD, Color.black);
    private static final int MARGIN = 32;

    protected void buildPdfMetadata(Map model, Document document, HttpServletRequest request) {
        Localidades dl = (Localidades) model.get("localidades");
        document.setPageSize(PageSize.LEGAL.rotate());
        document.setMargins(dl.getIzquierda(), dl.getDerecha(), dl.getSuperior(), dl.getInferior());
    }

    protected void buildPdfDocument(Map model, Document document, PdfWriter writer, HttpServletRequest req, HttpServletResponse resp) throws Exception {

        java.util.List lFopos = (java.util.List) model.get("listaCobros");
        java.util.List estab = (java.util.List) model.get("estab");
        Clientes dato = (Clientes) model.get("dato");
        int[] sumas = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int una, filas = 30;

        //Localidades Estable = (Localidades) estab.get(0);    
        if (lFopos.size() == 0) {
            Paragraph p = new Paragraph("No existe Datos", new Font(Font.HELVETICA, 22, Font.BOLDITALIC, new Color(0, 0, 0)));
            document.add(p);
        }

        if (lFopos.size() % filas == 0) {
            una = 0;
        } else {
            una = 1;
        }

        for (int pag = 0; pag < lFopos.size() / filas + una; pag++) {
            Paragraph p;
            PdfPCell cell;

            int NumColumns = 65;
            PdfPTable table = new PdfPTable(NumColumns);
            int[] fwidths = {17, 36, 40, 52, 110, 42, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 18};
            table.setWidths(fwidths);
            table.setWidthPercentage(100);

            cell = new PdfPCell(new Phrase("REGISTRO DIARIO DE VACUNACION", TITULO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(65);
            table.addCell(cell);

            String sCampos[] = {"No", "FECHA", "Numero \nHistoria\nClinica", "Numero de\nCarnet de\nAsegurado", "NOMBRE", "Edad", "M", "F", "BCG\nUnica"};
            // coloca la cabecera de titulos
            for (int i = 0; i < 6; i++) {
                cell = new PdfPCell(new Phrase(sCampos[i], CABEZA_COLUMNA_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
            }

            PdfPTable table1 = new PdfPTable(2);
            int[] fmwidths = {50, 50}; // percentage
            table1.setWidths(fmwidths);
            table1.setWidthPercentage(100);

            cell = new PdfPCell(new Phrase("SEXO", CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(2);
            table1.addCell(cell);

            cell = new PdfPCell(new Phrase("M", CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table1.addCell(cell);

            cell = new PdfPCell(new Phrase("F", CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table1.addCell(cell);

            cell = new PdfPCell(table1);
            cell.setColspan(2);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            table1 = new PdfPTable(13);
            int[] xwidths = {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5}; // percentage
            table1.setWidths(xwidths);
            table1.setWidthPercentage(100);

            cell = new PdfPCell(new Phrase("MENORES DE 1 año", CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(13);
            table1.addCell(cell);

            cell = new PdfPCell(new Phrase("BCG\nUnica ", CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table1.addCell(cell);

            addtitulofila3(table1, "Pentavalente", "1a", "2a", "3a");
            addtitulofila3(table1, "OPV(Antipolio)", "1a", "2a", "3a");
            addtitulofila3(table1, "Anti-rotavirus", "1a", "2a", "3a");
            addtitulofila3(table1, "Neumococica", "1a", "2a", "3a");

            cell = new PdfPCell(table1);
            cell.setColspan(13);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            table1 = new PdfPTable(3);
            int[] xwidths2 = {5, 5, 5}; // percentage
            table1.setWidths(xwidths2);
            table1.setWidthPercentage(100);

            cell = new PdfPCell(new Phrase("Influenza AH1N1", CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(3);
            table1.addCell(cell);

            addtitulofila2(table1, "6 m - 11 m", "1a", "2a");

            cell = new PdfPCell(new Phrase("12-23", CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table1.addCell(cell);

            cell = new PdfPCell(table1);
            cell.setColspan(3);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            table1 = new PdfPTable(10);
            int[] xwidths3 = {5, 5, 5, 5, 5, 5, 5, 5, 5, 5}; // percentage
            table1.setWidths(xwidths3);
            table1.setWidthPercentage(100);

            cell = new PdfPCell(new Phrase("De 12 a 23 meses", CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(10);
            table1.addCell(cell);

            cell = new PdfPCell(new Phrase("SRP", CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table1.addCell(cell);

            cell = new PdfPCell(new Phrase("Anti\nAmari", CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table1.addCell(cell);

            addtitulofila4(table1, "Pentavelente", "1a", "2a", "3a", "1R");
            addtitulofila4(table1, "OPV Antipolio", "1a", "2a", "3a", "1R");

            cell = new PdfPCell(table1);
            cell.setColspan(10);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            table1 = new PdfPTable(10);
            int[] xwidths4 = {5, 5, 5, 5, 5, 5, 5, 5, 5, 5}; // percentage
            table1.setWidths(xwidths4);
            table1.setWidthPercentage(100);

            cell = new PdfPCell(new Phrase("De 2 a 4 años", CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(10);
            table1.addCell(cell);

            addtitulofila4(table1, "Pentavelente", "1a", "2a", "3a", "1R");
            addtitulofila4(table1, "OPV Antipolio", "1a", "2a", "3a", "1R");

            cell = new PdfPCell(new Phrase("SRP", CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table1.addCell(cell);

            cell = new PdfPCell(new Phrase("Anti\nAmari", CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table1.addCell(cell);

            cell = new PdfPCell(table1);
            cell.setColspan(10);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            addtitulofila2(table, "V. niño\n 4 a", "Pent\n2R", "\npol\n2R");

            table1 = new PdfPTable(16);
            int[] xwidths5 = {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5}; // percentage
            table1.setWidths(xwidths5);
            table1.setWidthPercentage(100);

            cell = new PdfPCell(new Phrase("OTRAS VACUNACIONES", CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(16);
            table1.addCell(cell);

            addtitulofila5(table1, "Mujeres edad fertil DT", "1a", "2a", "3a", "4a", "5a");
            addtitulofila3(table1, "Hepatitis B", "1a", "2a", "3a");
            addtitulofila3(table1, "Antirrï¿½bica", "huma", "H Esq", "anim");
            addtitulofila5(table1, "Varones DT", "1a", "2a", "3a", "4a", "5a");

            cell = new PdfPCell(table1);
            cell.setColspan(16);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Infl >65", CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("F. Ama >5 ", CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Atendido \npor", CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setRotation(90);
            table.addCell(cell);

// coloca el detalle de loS datos
            for (int i = pag * filas + 0; i < pag * filas + filas && i < lFopos.size(); i++) {

                Cuadernos datopac = (Cuadernos) lFopos.get(i);

                cell = new PdfPCell(new Phrase(Integer.toString(i + 1), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(format(datopac.getFechap(), "dd/MM/yy"), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(Integer.toString(datopac.getId_historial()), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(datopac.getRegistro(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((datopac.getNombres().length() > 20) ? datopac.getNombres().substring(0, 20) : datopac.getNombres(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(Integer.toString(datopac.getEdad()) + "a" + Integer.toString(datopac.getMes()) + "m" + Integer.toString(datopac.getDia()) + "d", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                if (datopac.getId_tipo_sexo() == 1) {
                    cell = new PdfPCell(new Phrase("", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase("F", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);
                    sumas[1]++;
                } else {
                    cell = new PdfPCell(new Phrase("M", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase("", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);
                    sumas[0]++;
                }

                cell = new PdfPCell(new Phrase((datopac.getBcg() == 0 ? "" : "1"), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[2] += datopac.getBcg();

                int puno = 0, pdos = 0, ptres = 0, pcuatro = 0, pcinco = 0;
                if ("P".equals(datopac.getPenta()) && datopac.getEdad() < 1) {
                    puno++;
                }
                if ("S".equals(datopac.getPenta()) && datopac.getEdad() < 1) {
                    pdos++;
                }
                if ("T".equals(datopac.getPenta()) && datopac.getEdad() < 1) {
                    ptres++;
                }
                sumas[3] += puno;
                sumas[4] += pdos;
                sumas[5] += ptres;

                cell = new PdfPCell(new Phrase((puno == 0 ? "" : "1"), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((pdos == 0 ? "" : "1"), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((ptres == 0 ? "" : "1"), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                puno = 0;
                pdos = 0;
                ptres = 0;
                if ("P".equals(datopac.getPolio()) && datopac.getEdad() < 1) {
                    puno++;////////
                }
                if ("S".equals(datopac.getPolio()) && datopac.getEdad() < 1) {
                    pdos++;
                }
                if ("T".equals(datopac.getPolio()) && datopac.getEdad() < 1) {
                    ptres++;
                }
                sumas[6] += puno;
                sumas[7] += pdos;
                sumas[8] += ptres;

                cell = new PdfPCell(new Phrase((puno == 0 ? "" : "1"), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((pdos == 0 ? "" : "1"), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((ptres == 0 ? "" : "1"), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                puno = 0;
                pdos = 0;
                ptres = 0;
                if ("P".equals(datopac.getAnti()) && datopac.getEdad() < 1) {
                    puno++;
                }
                if ("S".equals(datopac.getAnti()) && datopac.getEdad() < 1) {
                    pdos++;
                }
                if ("T".equals(datopac.getAnti()) && datopac.getEdad() < 1) {
                    ptres++;
                }
                sumas[9] += puno;
                sumas[10] += pdos;
                sumas[11] += ptres;

                cell = new PdfPCell(new Phrase((puno == 0 ? "" : "1"), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((pdos == 0 ? "" : "1"), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((ptres == 0 ? "" : "1"), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
///////Neumococica  
                puno = 0;
                pdos = 0;
                ptres = 0;
                if ("P".equals(datopac.getBacterias()) && datopac.getEdad() < 5) {
                    puno++;
                }
                if ("S".equals(datopac.getBacterias()) && datopac.getEdad() < 5) {
                    pdos++;
                }
                if ("T".equals(datopac.getBacterias()) && datopac.getEdad() < 5) {
                    ptres++;
                }
                sumas[12] += puno;
                sumas[13] += pdos;
                sumas[14] += ptres;

                cell = new PdfPCell(new Phrase((puno == 0 ? "" : "1"), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((pdos == 0 ? "" : "1"), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((ptres == 0 ? "" : "1"), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                puno = 0;
                pdos = 0;
                ptres = 0;
                if (datopac.getCancer() == 1 && datopac.getEdad() < 1) {
                    puno++;
                }
                if (datopac.getCancer() == 2 && datopac.getEdad() < 1) {
                    pdos++;
                }
                if (datopac.getCancer() == 1 && datopac.getEdad() >= 1 && datopac.getEdad() < 2) {
                    ptres++;
                }
                sumas[15] += puno;
                sumas[16] += pdos;
                sumas[17] += ptres;

                cell = new PdfPCell(new Phrase((puno == 0 ? "" : "1"), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((pdos == 0 ? "" : "1"), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((ptres == 0 ? "" : "1"), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

////////////////////////////////////////           
                puno = 0;
                pdos = 0;
                ptres = 0;
                pcuatro = 0;
                pcinco = 0;
                if ("1".equals(datopac.getSrp()) && datopac.getEdad() >= 1 && datopac.getEdad() < 2) {
                    puno++;
                }
                if ("1".equals(datopac.getFama()) && datopac.getEdad() >= 1 && datopac.getEdad() < 2) {
                    pdos++;
                }
                sumas[18] += puno;
                sumas[19] += pdos;

                cell = new PdfPCell(new Phrase((puno == 0 ? "" : "1"), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((pdos == 0 ? "" : "1"), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                //calculos de DPT de 12 a 23 meses de edad      
                puno = 0;
                pdos = 0;
                ptres = 0;
                pcuatro = 0;
                pcinco = 0;
                if ("P".equals(datopac.getPenta()) && datopac.getEdad() >= 1 && datopac.getEdad() < 2) {
                    puno++;
                }
                if ("S".equals(datopac.getPenta()) && datopac.getEdad() >= 1 && datopac.getEdad() < 2) {
                    pdos++;
                }
                if ("T".equals(datopac.getPenta()) && datopac.getEdad() >= 1 && datopac.getEdad() < 2) {
                    ptres++;
                }
                if ("C".equals(datopac.getPenta()) && datopac.getEdad() >= 1 && datopac.getEdad() < 2) {
                    pcuatro++;
                }
                sumas[20] += puno;
                sumas[21] += pdos;
                sumas[22] += ptres;
                sumas[23] += pcuatro;

                cell = new PdfPCell(new Phrase((puno == 0 ? "" : "1"), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((pdos == 0 ? "" : "1"), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((ptres == 0 ? "" : "1"), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((pcuatro == 0 ? "" : "1"), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                puno = 0;
                pdos = 0;
                ptres = 0;
                pcuatro = 0;
                pcinco = 0;
                if ("P".equals(datopac.getPolio()) && datopac.getEdad() >= 1 && datopac.getEdad() < 2) {
                    puno++;
                }
                if ("S".equals(datopac.getPolio()) && datopac.getEdad() >= 1 && datopac.getEdad() < 2) {
                    pdos++;
                }
                if ("T".equals(datopac.getPolio()) && datopac.getEdad() >= 1 && datopac.getEdad() < 2) {
                    ptres++;
                }
                if ("C".equals(datopac.getPolio()) && datopac.getEdad() >= 1 && datopac.getEdad() < 2) {
                    pcuatro++;
                }
                sumas[24] += puno;
                sumas[25] += pdos;
                sumas[26] += ptres;
                sumas[27] += pcuatro;

                cell = new PdfPCell(new Phrase((puno == 0 ? "" : "1"), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((pdos == 0 ? "" : "1"), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((ptres == 0 ? "" : "1"), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((pcuatro == 0 ? "" : "1"), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                //calculos de DPT de 2 a 4 años de edad      
                puno = 0;
                pdos = 0;
                ptres = 0;
                pcuatro = 0;
                pcinco = 0;
                if ("P".equals(datopac.getPenta()) && datopac.getEdad() >= 2 && datopac.getEdad() < 5) {
                    puno++;
                }
                if ("S".equals(datopac.getPenta()) && datopac.getEdad() >= 2 && datopac.getEdad() < 5) {
                    pdos++;
                }
                if ("T".equals(datopac.getPenta()) && datopac.getEdad() >= 2 && datopac.getEdad() < 5) {
                    ptres++;
                }
                if ("C".equals(datopac.getPenta()) && datopac.getEdad() >= 2 && datopac.getEdad() < 5) {
                    pcuatro++;
                }

                sumas[28] += puno;
                sumas[29] += pdos;
                sumas[30] += ptres;
                sumas[31] += pcuatro;

                cell = new PdfPCell(new Phrase((puno == 0 ? "" : "1"), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((pdos == 0 ? "" : "1"), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((ptres == 0 ? "" : "1"), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((pcuatro == 0 ? "" : "1"), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                puno = 0;
                pdos = 0;
                ptres = 0;
                pcuatro = 0;
                pcinco = 0;
                if ("P".equals(datopac.getPolio()) && datopac.getEdad() >= 2 && datopac.getEdad() < 5) {
                    puno++;
                }
                if ("S".equals(datopac.getPolio()) && datopac.getEdad() >= 2 && datopac.getEdad() < 5) {
                    pdos++;
                }
                if ("T".equals(datopac.getPolio()) && datopac.getEdad() >= 2 && datopac.getEdad() < 5) {
                    ptres++;
                }
                if ("C".equals(datopac.getPolio()) && datopac.getEdad() >= 2 && datopac.getEdad() < 5) {
                    pcuatro++;
                }
                sumas[32] += puno;
                sumas[33] += pdos;
                sumas[34] += ptres;
                sumas[35] += pcuatro;

                cell = new PdfPCell(new Phrase((puno == 0 ? "" : "1"), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((pdos == 0 ? "" : "1"), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((ptres == 0 ? "" : "1"), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((pcuatro == 0 ? "" : "1"), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                puno = 0;
                pdos = 0;
                if ("1".equals(datopac.getSrp()) && datopac.getEdad() >= 2 && datopac.getEdad() < 5) {
                    puno++;
                }
                if ("1".equals(datopac.getFama()) && datopac.getEdad() >= 2 && datopac.getEdad() < 5) {
                    pdos++;
                }
                sumas[36] += puno;
                sumas[37] += pdos;

                cell = new PdfPCell(new Phrase((puno == 0 ? "" : "1"), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((pdos == 0 ? "" : "1"), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                puno = 0;
                pdos = 0;
                if ("Q".equals(datopac.getPenta()) && datopac.getEdad() >= 4 && datopac.getEdad() < 6) {
                    puno++;
                }
                if ("Q".equals(datopac.getPolio()) && datopac.getEdad() >= 4 && datopac.getEdad() < 6) {
                    pdos++;
                }
                sumas[38] += puno;
                sumas[39] += pdos;
                ////penta y polio 2da Refuerzo
                cell = new PdfPCell(new Phrase((puno == 0 ? "" : "1"), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((pdos == 0 ? "" : "1"), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                puno = 0;
                pdos = 0;
                ptres = 0;
                pcuatro = 0;
                pcinco = 0;
                if ("P".equals(datopac.getMujerdt()) && datopac.getId_tipo_sexo() == 1) {
                    puno++;
                }
                if ("S".equals(datopac.getMujerdt()) && datopac.getId_tipo_sexo() == 1) {
                    pdos++;
                }
                if ("T".equals(datopac.getMujerdt()) && datopac.getId_tipo_sexo() == 1) {
                    ptres++;
                }
                if ("C".equals(datopac.getMujerdt()) && datopac.getId_tipo_sexo() == 1) {
                    pcuatro++;
                }
                if ("Q".equals(datopac.getMujerdt()) && datopac.getId_tipo_sexo() == 1) {
                    pcinco++;
                }
                sumas[40] += puno;
                sumas[41] += pdos;
                sumas[42] += ptres;
                sumas[43] += pcuatro;
                sumas[44] += pcinco;

                cell = new PdfPCell(new Phrase((puno == 0 ? "" : "1"), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((pdos == 0 ? "" : "1"), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((ptres == 0 ? "" : "1"), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((pcuatro == 0 ? "" : "1"), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((pcinco == 0 ? "" : "1"), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
//HEpatitis
                puno = 0;
                pdos = 0;
                ptres = 0;
                if (datopac.getAuto() == 1) {
                    puno++;
                }
                if (datopac.getAuto() == 2) {
                    pdos++;
                }
                if (datopac.getAuto() == 3) {
                    ptres++;
                }
                sumas[45] += puno;
                sumas[46] += pdos;
                sumas[47] += ptres;

                cell = new PdfPCell(new Phrase((puno == 0 ? "" : "1"), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((pdos == 0 ? "" : "1"), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((ptres == 0 ? "" : "1"), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                //Antirrabicas
                puno = 0;
                pdos = 0;
                ptres = 0;
                if (datopac.getRhumana() == 1) {
                    puno++;
                }
                if (datopac.getRhumana() == 2) {
                    pdos++;
                }
                if (datopac.getRhumana() == 3) {
                    ptres++;
                }
                sumas[48] += puno;
                sumas[49] += pdos;
                sumas[50] += ptres;

                cell = new PdfPCell(new Phrase((puno == 0 ? "" : "1"), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((pdos == 0 ? "" : "1"), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((ptres == 0 ? "" : "1"), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                //DT para varones        
                puno = 0;
                pdos = 0;
                ptres = 0;
                pcuatro = 0;
                pcinco = 0;
                if ("P".equals(datopac.getMujerdt()) && datopac.getId_tipo_sexo() == 2) {
                    puno++;
                }
                if ("S".equals(datopac.getMujerdt()) && datopac.getId_tipo_sexo() == 2) {
                    pdos++;
                }
                if ("T".equals(datopac.getMujerdt()) && datopac.getId_tipo_sexo() == 2) {
                    ptres++;
                }
                if ("C".equals(datopac.getMujerdt()) && datopac.getId_tipo_sexo() == 2) {
                    pcuatro++;
                }
                if ("Q".equals(datopac.getMujerdt()) && datopac.getId_tipo_sexo() == 2) {
                    pcinco++;
                }
                sumas[51] += puno;
                sumas[52] += pdos;
                sumas[53] += ptres;
                sumas[54] += pcuatro;
                sumas[55] += pcinco;

                cell = new PdfPCell(new Phrase((puno == 0 ? "" : "1"), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((pdos == 0 ? "" : "1"), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((ptres == 0 ? "" : "1"), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((pcuatro == 0 ? "" : "1"), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((pcinco == 0 ? "" : "1"), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                puno = 0;
                if (datopac.getCancer() > 0 && datopac.getEdad() > 5) {
                    puno++;  /////influenza H1N1
                }
                sumas[56] += puno;

                cell = new PdfPCell(new Phrase((puno == 0 ? "" : "1"), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                ///Febre amarilla > de 5 años
                puno = 0;
                if ("1".equals(datopac.getFama()) && datopac.getEdad() >= 5) {
                    puno++;
                }
                sumas[57] += puno;

                cell = new PdfPCell(new Phrase((puno == 0 ? "" : "1"), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(Integer.toString(datopac.getId_persona()), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

            }
            for (int i = lFopos.size(); i < pag * filas + filas; i++) {
                cell = new PdfPCell(new Phrase(Integer.toString(i + 1), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);
                for (int j = 1; j < NumColumns; j++) {
                    cell = new PdfPCell(new Phrase("", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);
                }
            }

            cell = new PdfPCell(new Phrase("TOTAL", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(6);
            table.addCell(cell);

            for (int j = 0; j < 58; j++) {
                cell = new PdfPCell(new Phrase(Integer.toString(sumas[j]), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(cell);
            }
            for (int j = 1; j < 50; j++) {
                cell = new PdfPCell(new Phrase("", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);
            }
            //// NUEVO HOJA         
            document.add(table);
            document.newPage();

        }

    }

    public static void addtitulofila3(PdfPTable table, String cadena, String a, String b, String c) {
        PdfPCell cell;
        int NumColumns = 3;
        PdfPTable table11 = new PdfPTable(NumColumns);
        table11.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase(cadena, CABEZA_COLUMNA_FONT));
        cell.setColspan(3);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase(a, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase(b, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase(c, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        cell = new PdfPCell(table11);
        cell.setColspan(3);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
    }

    public static void addtitulofila2(PdfPTable table, String cadena, String a, String b) {
        PdfPCell cell;
        int NumColumns = 2;
        PdfPTable table12 = new PdfPTable(NumColumns);
        table12.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase(cadena, CABEZA_COLUMNA_FONT));
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
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
    }

    public static void addtitulofila4(PdfPTable table, String cadena, String a, String b, String c, String d) {
        PdfPCell cell;
        int NumColumns = 4;
        PdfPTable table11 = new PdfPTable(NumColumns);
        table11.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase(cadena, CABEZA_COLUMNA_FONT));
        cell.setColspan(4);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase(a, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase(b, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase(c, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase(d, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        cell = new PdfPCell(table11);
        cell.setColspan(4);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
    }

    public static void addtitulofila5(PdfPTable table, String cadena, String a, String b, String c, String d, String e) {
        PdfPCell cell;
        int NumColumns = 5;
        PdfPTable table11 = new PdfPTable(NumColumns);
        table11.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase(cadena, CABEZA_COLUMNA_FONT));
        cell.setColspan(5);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase(a, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase(b, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase(c, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase(d, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase(e, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        cell = new PdfPCell(table11);
        cell.setColspan(5);
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
