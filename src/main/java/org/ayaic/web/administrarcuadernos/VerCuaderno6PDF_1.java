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

public class VerCuaderno6PDF_1 extends AbstractPdfView {

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
        Clientes dato = (Clientes) model.get("dato");
        int[] sumas = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int[] sumbs = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int una, filas = 30;

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

            int NumColumns = 17;
            PdfPTable table = new PdfPTable(NumColumns);
            int[] fwidths = {9, 10, 13, 14, 40, 5, 5, 7, 7, 7, 7, 7, 7, 20, 35, 25, 10};
            table.setWidths(fwidths);
            table.setWidthPercentage(100);

            cell = new PdfPCell(new Phrase("Cuaderno No. 6                                                                                                      Registro de Emergencias y Enfermeria para Centros de Salud y Hospitales Basicos", TITULO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(NumColumns);
            table.addCell(cell);

            String sCampos[] = {"No", "FECHA", "No Historia Clinica", "No Asegurado", "Apellidos y Nombre", "M", "F", "", "", "1 A 4", "5 A 14", "15 A 59", "60 O MAS", "Diagnostico Ingreso", "Tratamiento", "Diagnostico Egreso", "Responsable"};
            // coloca la cabecera de titulos
            for (int i = 0; i < 5; i++) {
                cell = new PdfPCell(new Phrase(sCampos[i], CABEZA_COLUMNA_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
            }

            addtitulofila2(table, "Sexo", "M", "F");
            addtitulofila4(table, "Edad(en años)", "< de 1 año", "1 a 4 años", "5 a 9 años", "10 a 20", "21 a 59", "60 a mas");

            for (int i = 13; i < NumColumns; i++) {
                cell = new PdfPCell(new Phrase(sCampos[i], CABEZA_COLUMNA_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
            }

            // coloca el detalle de loS datos
            for (int i = pag * filas + 0; i < pag * filas + filas && i < lFopos.size(); i++) {
                Cuadernos datopac = (Cuadernos) lFopos.get(i);

                cell = new PdfPCell(new Phrase(Integer.toString(i + 1), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(format(datopac.getFechap(), "dd/MM/yy"), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(Integer.toString(datopac.getHcl()), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(datopac.getRegistro(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(datopac.getNombres(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 2) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[0] += (datopac.getId_cuaderno() == 2) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[1] += (datopac.getId_cuaderno() == 1) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getSuma1() == 1) ? Integer.toString(datopac.getEdad()) + ":" + Integer.toString(datopac.getMes()) + ":" + Integer.toString(datopac.getDia()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[2] += (datopac.getSuma1() == 1) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getSuma2() == 1) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[3] += (datopac.getSuma2() == 1) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getSuma3() == 1) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[4] += (datopac.getSuma3() == 1) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getSuma4() == 1) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[5] += (datopac.getSuma4() == 1) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getSuma5() == 1) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[6] += (datopac.getSuma5() == 1) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getSuma6() == 1) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[7] += (datopac.getSuma6() == 1) ? 1 : 0;

                cell = new PdfPCell(new Phrase(datopac.getEstado(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(datopac.getLaboratorio(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(datopac.getResultado(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(Integer.toString(datopac.getId_persona()), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

            }
            for (int i = lFopos.size(); i < pag * filas + filas; i++) {
                cell = new PdfPCell(new Phrase(Integer.toString(i + 1), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                for (int j = 1; j < NumColumns; j++) {
                    cell = new PdfPCell(new Phrase("", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);
                }
            }

            cell = new PdfPCell(new Phrase("TOTAL", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(5);
            table.addCell(cell);
            for (int j = 0; j < 8; j++) {
                cell = new PdfPCell(new Phrase(Integer.toString(sumas[j]), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(cell);
            }
            for (int j = 6; j < 14; j++) {
                cell = new PdfPCell(new Phrase("", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(cell);
            }

            document.add(table);
            document.newPage();
//nueva hoja             
            NumColumns = 16;
            table = new PdfPTable(NumColumns);
            int[] fwdidths = {9, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 25, 15};
            table.setWidths(fwdidths);
            table.setWidthPercentage(100);

            cell = new PdfPCell(new Phrase("Cuaderno No. 6                                                                                                      Registro de Emergencias y Enfermeria para Centros de Salud y Hospitales Basicos", TITULO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(16);
            table.addCell(cell);

            String sdCampos[] = {"No", "FECHA", "Apellidos y Nombre", "Sexo", "Edad", "1 A 4", "5 A 14", "15 A 59", "60 O MAS", "D. Ingreso", "Tratamiento", "muerte", "D. Egreso", "Tipo", "Referido a: Establecimiento/Localidad", "No de Boleta"};
            // coloca la cabecera de titulos

            cell = new PdfPCell(new Phrase("No", CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            addtitulofila2(table, "Situacion al Egreso", "Vivo", "Muerto");
            addtitulofila7(table);
            addtitulofila3(table, "ACTIVIDADES DE ENFERMERIA", "Inyectables", "Sueros", "Curaciones y/o Sutura");
            for (int i = 14; i < NumColumns; i++) {
                cell = new PdfPCell(new Phrase(sdCampos[i], CABEZA_COLUMNA_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
            }

            // coloca el detalle de loS datos
            for (int i = pag * filas + 0; i < pag * filas + filas && i < lFopos.size(); i++) {

                Cuadernos datopac = (Cuadernos) lFopos.get(i);

                cell = new PdfPCell(new Phrase(Integer.toString(i + 1), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase("V".equals(datopac.getTipo()) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[0] += "V".equals(datopac.getTipo()) ? 1 : 0;

                cell = new PdfPCell(new Phrase("M".equals(datopac.getTipo()) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[1] += "M".equals(datopac.getTipo()) ? 1 : 0;

                cell = new PdfPCell(new Phrase("1".equals(datopac.getTipoconsulta()) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[2] += "1".equals(datopac.getTipoconsulta()) ? 1 : 0;

                cell = new PdfPCell(new Phrase("2".equals(datopac.getTipoconsulta()) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[3] += "2".equals(datopac.getTipoconsulta()) ? 1 : 0;

                cell = new PdfPCell(new Phrase("3".equals(datopac.getTipoconsulta()) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[4] += "3".equals(datopac.getTipoconsulta()) ? 1 : 0;

                cell = new PdfPCell(new Phrase("4".equals(datopac.getTipoconsulta()) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[5] += "4".equals(datopac.getTipoconsulta()) ? 1 : 0;

                cell = new PdfPCell(new Phrase("5".equals(datopac.getTipoconsulta()) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[6] += "5".equals(datopac.getTipoconsulta()) ? 1 : 0;

                cell = new PdfPCell(new Phrase("6".equals(datopac.getTipoconsulta()) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[7] += "6".equals(datopac.getTipoconsulta()) ? 1 : 0;

                cell = new PdfPCell(new Phrase("7".equals(datopac.getTipoconsulta()) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[8] += "7".equals(datopac.getTipoconsulta()) ? 1 : 0;

                cell = new PdfPCell(new Phrase("8".equals(datopac.getTipoconsulta()) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[9] += "8".equals(datopac.getTipoconsulta()) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getInyectable() > 0) ? Integer.toString(datopac.getInyectable()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[10] += (datopac.getInyectable() > 0) ? datopac.getInyectable() : 0;

                cell = new PdfPCell(new Phrase((datopac.getSueros() > 0) ? Integer.toString(datopac.getSueros()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[11] += (datopac.getSueros() > 0) ? datopac.getSueros() : 0;

                cell = new PdfPCell(new Phrase((datopac.getCuraciones()) > 0 ? Integer.toString(datopac.getCuraciones()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[12] += (datopac.getCuraciones()) > 0 ? datopac.getCuraciones() : 0;

                cell = new PdfPCell(new Phrase(datopac.getReferido(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(datopac.getContraref(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);
            }
            for (int i = lFopos.size(); i < pag * filas + filas; i++) {
                cell = new PdfPCell(new Phrase(Integer.toString(i + 1), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                for (int j = 1; j < NumColumns; j++) {
                    cell = new PdfPCell(new Phrase("", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);
                }
            }

            cell = new PdfPCell(new Phrase("T.", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);
            for (int j = 0; j < 13; j++) {
                cell = new PdfPCell(new Phrase(Integer.toString(sumbs[j]), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(cell);
            }
            cell = new PdfPCell(new Phrase("", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            document.add(table);
            document.newPage();
        }
    }

    public static void addtitulofila7(PdfPTable table) {
        PdfPCell cell;
        int NumColumns = 8;
        PdfPTable table11 = new PdfPTable(NumColumns);
        table11.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("Causas de Fallecimiento", CABEZA_COLUMNA_FONT));
        cell.setColspan(8);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase("de R.N. Menor de 7 dias", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase("de 7 diasa < 1 año", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase("Por Diarrea en Menor de 5 años", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase("Desnutricion Aguda grave < 5 años", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase("Neumonia en Menor de 5 años", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase("Otras causas < 5 años", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase("Otras causas en > de 5 años", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase("Muerte Materna", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        cell = new PdfPCell(table11);
        cell.setColspan(8);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
    }

    public static void addtitulofila4(PdfPTable table, String cadena, String a, String b, String c, String d, String e, String f) {
        PdfPCell cell;
        int NumColumns = 6;
        PdfPTable table11 = new PdfPTable(NumColumns);
        table11.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase(cadena, CABEZA_COLUMNA_FONT));
        cell.setColspan(6);
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

        cell = new PdfPCell(new Phrase(f, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        cell = new PdfPCell(table11);
        cell.setColspan(6);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
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
