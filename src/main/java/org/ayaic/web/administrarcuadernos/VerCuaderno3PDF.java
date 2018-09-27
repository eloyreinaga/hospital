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

public class VerCuaderno3PDF extends AbstractPdfView {

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
        int[] sumas = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int[] sumbs = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
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
            int[] fwidths = {10, 11, 12, 15, 41, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10};
            table.setWidths(fwidths);
            table.setWidthPercentage(100);

            cell = new PdfPCell(new Phrase("Cuaderno No. 3   Anticoncepcion, Prevencion de las Infecciones de transmision sexual y del  Cï¿½ncer Cï¿½rvico Uterino ", TITULO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(17);
            table.addCell(cell);

            String sCampos[] = {"No", "FECHA", "Numero de\nHistoria\nClinica", "Numero de\n Asegurado", "Apellido Paterno, Apellido Materno y Nombres", "M", "F", "MENOR DE 20", "20 a 34", "35 o mas", "Orientacion", "DIU", "Inyectable\nTrimestral", "Condon", "Pildora", "AQV", "Otro"};
            // coloca la cabecera de titulos
            for (int i = 0; i < 5; i++) {
                cell = new PdfPCell(new Phrase(sCampos[i], CABEZA_COLUMNA_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
            }

            addfila2(table, "Sexo", "M", "F");

            addfila3(table, "Edad(en años)", "Menor a 20", "20 34", "35 o mas");

            cell = new PdfPCell(new Phrase("Orientacion", CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            addfila6(table);

            // coloca el detalle de loS datos
            for (int i = pag * filas + 0; i < pag * filas + filas && i < lFopos.size(); i++) {

                Cuadernos datopac = (Cuadernos) lFopos.get(i);

                cell = new PdfPCell(new Phrase(Integer.toString(i + 1), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(format(datopac.getFecha(), "dd/MM/yyyy"), DATO_FONT));
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

                cell = new PdfPCell(new Phrase((datopac.getId_tipo_sexo() == 2) ? "M" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[0] += (datopac.getId_tipo_sexo() == 2) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_tipo_sexo() == 1) ? "F" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[1] += (datopac.getId_tipo_sexo() == 1) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getEdad() < 20) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[2] += (datopac.getEdad() < 20) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getEdad() >= 20 && datopac.getEdad() < 35) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[3] += (datopac.getEdad() >= 20 && datopac.getEdad() < 35) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getEdad() > 34) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[4] += (datopac.getEdad() > 34) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getOrientacion() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[5] += datopac.getOrientacion();

                cell = new PdfPCell(new Phrase((datopac.getDiu() == 1 && "N".equals(datopac.getTipoconsulta())) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[6] += (datopac.getDiu() == 1 && "N".equals(datopac.getTipoconsulta())) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getDiu() == 1 && "C".equals(datopac.getTipoconsulta())) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[7] += (datopac.getDiu() == 1 && "C".equals(datopac.getTipoconsulta())) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getDiu() == 1 && datopac.getInsumos() != 0) ? Integer.toString(datopac.getInsumos()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[8] += (datopac.getDiu() == 1 && datopac.getInsumos() != 0) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getInyectable() == 1 && "N".equals(datopac.getTipoconsulta())) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[9] += (datopac.getInyectable() == 1 && "N".equals(datopac.getTipoconsulta())) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getInyectable() == 1 && "C".equals(datopac.getTipoconsulta())) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[10] += (datopac.getInyectable() == 1 && "C".equals(datopac.getTipoconsulta())) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getInyectable() == 1 && datopac.getInsumos() != 0) ? Integer.toString(datopac.getInsumos()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[11] += (datopac.getInyectable() == 1 && datopac.getInsumos() != 0) ? 1 : 0;

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
            cell.setColspan(5);
            table.addCell(cell);
            for (int j = 0; j < 12; j++) {
                cell = new PdfPCell(new Phrase(Integer.toString(sumas[j]), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(cell);
            }

            document.add(table);
            document.newPage();

            //pagina 2          
            NumColumns = 28;
            table = new PdfPTable(NumColumns);
            int[] fxwidths = {10, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 20, 20};
            table.setWidths(fxwidths);
            table.setWidthPercentage(100);

            cell = new PdfPCell(new Phrase("Cuaderno 3.  Anticoncepcion, Prevencion de las Infecciones de transmision sexual y del  Cï¿½ncer Cï¿½rvico Uterino", TITULO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(28);
            table.addCell(cell);

            String snCampos[] = {"No", "MENOR DE 20", "20 a 34", "35 o mas", "Orientacion", "DIU", "Inyectable\nTrimestral", "Condon", "", "", "", "", "", "", "Pildora", "Pild. Emerg.", "AQV", "Otro", "Metodos\nNaturales", "PAP1", "PAP2", "PAP3", "PAP4", "PAP5", "Tipo", "PAP", "Referido a: Establecimiento/Localidad (Pï¿½blico, Privado, ONGs, Cajas)", "Observaciones"};
            // coloca la cabecera de titulos

            cell = new PdfPCell(new Phrase("No", CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            addfila12(table);

            addfila13(table);

            for (int i = 25; i < NumColumns; i++) {
                cell = new PdfPCell(new Phrase(snCampos[i], CABEZA_COLUMNA_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
            }
            // coloca el detalle de loS datos
            for (int i = pag * filas + 0; i < pag * filas + filas && i < lFopos.size(); i++) {

                Cuadernos datopac = (Cuadernos) lFopos.get(i);

                cell = new PdfPCell(new Phrase(Integer.toString(i + 1), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((datopac.getCondon() == 1 && "N".equals(datopac.getTipoconsulta())) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[0] += (datopac.getCondon() == 1 && "N".equals(datopac.getTipoconsulta())) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getCondon() == 1 && "C".equals(datopac.getTipoconsulta())) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[1] += (datopac.getCondon() == 1 && "C".equals(datopac.getTipoconsulta())) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getCondon() == 1 && datopac.getInsumos() != 0) ? Integer.toString(datopac.getInsumos()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[2] += (datopac.getCondon() == 1 && datopac.getInsumos() != 0) ? datopac.getInsumos() : 0;

                cell = new PdfPCell(new Phrase((datopac.getOtras() == 5 && "N".equals(datopac.getTipoconsulta())) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[3] += (datopac.getOtras() == 5 && "N".equals(datopac.getTipoconsulta())) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getOtras() == 5 && "C".equals(datopac.getTipoconsulta())) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[4] += (datopac.getOtras() == 5 && "C".equals(datopac.getTipoconsulta())) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getOtras() == 5 && datopac.getInsumos() != 0) ? Integer.toString(datopac.getInsumos()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[5] += (datopac.getOtras() == 5 && datopac.getInsumos() != 0) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getPildora() == 1 && "N".equals(datopac.getTipoconsulta())) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[6] += (datopac.getPildora() == 1 && "N".equals(datopac.getTipoconsulta())) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getPildora() == 1 && "C".equals(datopac.getTipoconsulta())) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[7] += (datopac.getPildora() == 1 && "C".equals(datopac.getTipoconsulta())) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getPildora() == 1 && datopac.getInsumos() != 0) ? Integer.toString(datopac.getInsumos()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[8] += (datopac.getPildora() == 1 && datopac.getInsumos() != 0) ? datopac.getInsumos() : 0;

                cell = new PdfPCell(new Phrase((datopac.getOtras() == 6 && "N".equals(datopac.getTipoconsulta())) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[9] += (datopac.getOtras() == 6 && "N".equals(datopac.getTipoconsulta())) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getOtras() == 6 && "C".equals(datopac.getTipoconsulta())) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[10] += (datopac.getOtras() == 6 && "C".equals(datopac.getTipoconsulta())) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getOtras() == 6 && datopac.getInsumos() != 0) ? Integer.toString(datopac.getInsumos()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[11] += (datopac.getOtras() == 6 && datopac.getInsumos() != 0) ? 1 : 0;
//metodo pildora emergencia                
                cell = new PdfPCell(new Phrase((datopac.getPildora() == 2) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[12] += (datopac.getPildora() == 2) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getAqv() == 1 && datopac.getId_tipo_sexo() == 2) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[13] += (datopac.getAqv() == 1 && datopac.getId_tipo_sexo() == 2) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getAqv() == 1 && datopac.getId_tipo_sexo() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[14] += (datopac.getAqv() == 1 && datopac.getId_tipo_sexo() == 1) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getOtras() == 1 && "N".equals(datopac.getTipoconsulta())) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[15] += (datopac.getOtras() == 1 && "N".equals(datopac.getTipoconsulta())) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getOtras() == 1 && "C".equals(datopac.getTipoconsulta())) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[16] += (datopac.getOtras() == 1 && "C".equals(datopac.getTipoconsulta())) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getOtras() == 1 && datopac.getInsumos() != 0) ? Integer.toString(datopac.getInsumos()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[17] += (datopac.getOtras() == 1 && datopac.getInsumos() != 0) ? 1 : 0;
                //Arreglarmetodos naturales 

                cell = new PdfPCell(new Phrase((datopac.getMnatural() == 1 && "N".equals(datopac.getTipoconsulta())) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[18] += (datopac.getMnatural() == 1 && "N".equals(datopac.getTipoconsulta())) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getMnatural() == 1 && "C".equals(datopac.getTipoconsulta())) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[19] += (datopac.getMnatural() == 1 && "C".equals(datopac.getTipoconsulta())) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getMnatural() == 2 && "N".equals(datopac.getTipoconsulta())) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[20] += (datopac.getMnatural() == 2 && "N".equals(datopac.getTipoconsulta())) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getMnatural() == 2 && "C".equals(datopac.getTipoconsulta())) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[21] += (datopac.getMnatural() == 2 && "C".equals(datopac.getTipoconsulta())) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getMnatural() == 3 && "N".equals(datopac.getTipoconsulta())) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[22] += (datopac.getMnatural() == 3 && "N".equals(datopac.getTipoconsulta())) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getMnatural() == 3 && "C".equals(datopac.getTipoconsulta())) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[23] += (datopac.getMnatural() == 3 && "C".equals(datopac.getTipoconsulta())) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getPap() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[24] += datopac.getPap();

                cell = new PdfPCell(new Phrase(datopac.getReferido(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(datopac.getObserva(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
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

            cell = new PdfPCell(new Phrase("T.", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            for (int j = 0; j < 25; j++) {
                cell = new PdfPCell(new Phrase(Integer.toString(sumbs[j]), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(cell);
            }
            for (int j = 1; j < 19; j++) {
                cell = new PdfPCell(new Phrase("", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);
            }

            document.add(table);
            document.newPage();
        }

    }

    public static void addfila12(PdfPTable table) {

        PdfPCell cell;
        PdfPTable table1 = new PdfPTable(18);

        cell = new PdfPCell(new Phrase("Metodos Anticonceptivos Modernos", CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(18);
        table1.addCell(cell);

        addfila3(table1, "Condon", "N", "C", "Ins.");
        addfila3(table1, "Condon Femenino", "N", "C", "Ins.");
        addfila3(table1, "Pildora", "N", "C", "Ins.");
        addfila3(table1, "Implante Subcutaneo", "N", "C", "Ins.");
        addfila11(table1, "Pildora Emerg.");
        addfila2(table1, "AQV", "M", "F");
        addfila3(table1, "Otro", "N", "C", "Ins.");

        cell = new PdfPCell(table1);
        cell.setColspan(18);
        table.addCell(cell);
    }

    public static void addfila13(PdfPTable table) {

        PdfPCell cell;
        PdfPTable table1 = new PdfPTable(6);

        cell = new PdfPCell(new Phrase("Metodos Naturales", CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(6);
        table1.addCell(cell);

        addfila2(table1, "MELA", "N", "C");
        addfila2(table1, "Ritmo", "N", "C");
        addfila2(table1, "Dias Fijos", "N", "C");

        cell = new PdfPCell(table1);
        cell.setColspan(6);
        table.addCell(cell);
    }

    public static void addfila6(PdfPTable table) {

        PdfPCell cell;
        PdfPTable table1 = new PdfPTable(6);

        cell = new PdfPCell(new Phrase("Metodos Anticonceptivos Modernos", CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(6);
        table1.addCell(cell);

        addfila3(table1, "DIU", "N", "C", "Ins.");
        addfila3(table1, "Inyectable Trimestral", "N", "C", "Ins.");

        cell = new PdfPCell(table1);
        cell.setColspan(6);
        table.addCell(cell);
    }

    public static void addfila2(PdfPTable table, String a, String b, String c) {
        PdfPCell cell;
        PdfPTable table1 = new PdfPTable(2);

        cell = new PdfPCell(new Phrase(a, CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setColspan(2);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase(b, CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase(c, CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1.addCell(cell);

        cell = new PdfPCell(table1);
        cell.setColspan(2);
        table.addCell(cell);
    }

    public static void addfila11(PdfPTable table, String a) {
        PdfPCell cell;
        PdfPTable table11 = new PdfPTable(1);

        cell = new PdfPCell(new Phrase(a, CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setColspan(1);
        table11.addCell(cell);

        cell = new PdfPCell(table11);
        cell.setColspan(1);
        table.addCell(cell);
    }

    public static void addfila3(PdfPTable table, String a, String b, String c, String d) {
        PdfPCell cell;
        PdfPTable table1 = new PdfPTable(3);

        cell = new PdfPCell(new Phrase(a, CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setColspan(3);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase(b, CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase(c, CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase(d, CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1.addCell(cell);

        cell = new PdfPCell(table1);
        cell.setColspan(3);
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
