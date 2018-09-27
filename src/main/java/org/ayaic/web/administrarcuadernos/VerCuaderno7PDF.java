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

public class VerCuaderno7PDF extends AbstractPdfView {

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
        int[] sumas = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int NumColumns = 45;
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
            PdfPTable table = new PdfPTable(NumColumns);
            int[] fwidths = {9, 10, 10, 3, 3, 43, 7, 5, 5, 4, 5, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 6, 7, 7, 5};
            table.setWidths(fwidths);
            table.setWidthPercentage(100);

            cell = new PdfPCell(new Phrase("Cuaderno No. 7      Odontologia", TITULO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(45);
            table.addCell(cell);
            // titulo no y fecha
            cell = new PdfPCell(new Phrase("No", CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Fecha", CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Historia Clinica", CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);

            // titulo sexo
            addfila2(table, "Sexo", "M", "F");

            //nombre
            cell = new PdfPCell(new Phrase("Apellidos y Nombre", CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            //cie10
            cell = new PdfPCell(new Phrase("CIE 10", CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            //primera consulta
            cell = new PdfPCell(new Phrase("Primera Consulta", CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setRotation(90);
            table.addCell(cell);
            // consulta nueva
            addfila2(table, "Consulta < 5 años", "N", "R");

            // consulta repetida
            addfila2(table, "Consulta > 5 años", "N", "R");
            //endodoncia
            addfila2(table, "Consulta > 60 años", "N", "R");

            addfila2(table, "Consulta Mujer Embarazada", "N", "R");
            //        addfila2(table, "Exodoncia","P","T");
            addfila12(table);

            addfila2(table, "Rayos X", "O", "R");
            addfila2(table, "Fluorinizacion", "D", "F");
            addfila1(table, "Profilaxis");
            addfila2(table, "Aplicacion de Sellantes", "D", "F");
            addfila2(table, "Aplicacion de Cariostï¿½ticos", "D", "F");
            addfila2(table, "Educacion en Salud Oral", "D", "F");

            cell = new PdfPCell(new Phrase("Otras Acciones", CABEZA_COLUMNA_FONT));
            cell.setRotation(90);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("Refererido a", CABEZA_COLUMNA_FONT));
            cell.setRotation(90);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("Retornado a", CABEZA_COLUMNA_FONT));
            cell.setRotation(90);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("Atendido por", CABEZA_COLUMNA_FONT));
            cell.setRotation(90);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
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

                if (datopac.getId_cuaderno() == 1) {
                    cell = new PdfPCell(new Phrase(" ", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase("F", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);
                } else {
                    cell = new PdfPCell(new Phrase("M", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase("", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);
                }
                cell = new PdfPCell(new Phrase((datopac.getNombres().length() > 28) ? datopac.getNombres().substring(0, 28) : datopac.getNombres(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(datopac.getDglobal(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((datopac.getPrimera() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[0] += datopac.getPrimera();

                if ("N".equals(datopac.getTipoconsulta())) {
                    if (datopac.getEdad() < 5) {
                        cell = new PdfPCell(new Phrase(Integer.toString(datopac.getEdad()), DATO_FONT));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table.addCell(cell);
                        sumas[1] += 1;

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
                    } else if (datopac.getEdad() >= 5 && datopac.getEdad() < 60) {
                        cell = new PdfPCell(new Phrase("", DATO_FONT));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table.addCell(cell);

                        cell = new PdfPCell(new Phrase("", DATO_FONT));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table.addCell(cell);

                        cell = new PdfPCell(new Phrase((datopac.getEmbarazo() == 0) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        table.addCell(cell);
                        sumas[3] += (datopac.getEmbarazo() == 0) ? 1 : 0;

                        cell = new PdfPCell(new Phrase("", DATO_FONT));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table.addCell(cell);

                        cell = new PdfPCell(new Phrase("", DATO_FONT));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table.addCell(cell);
                    } else {
                        cell = new PdfPCell(new Phrase("", DATO_FONT));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table.addCell(cell);

                        cell = new PdfPCell(new Phrase("", DATO_FONT));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table.addCell(cell);

                        cell = new PdfPCell(new Phrase("", DATO_FONT));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table.addCell(cell);

                        cell = new PdfPCell(new Phrase("", DATO_FONT));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table.addCell(cell);

                        cell = new PdfPCell(new Phrase(Integer.toString(datopac.getEdad()), DATO_FONT));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        table.addCell(cell);
                        sumas[5] += 1;
                    }

                    cell = new PdfPCell(new Phrase("", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                } else //para repetidas "R"
                if ("R".equals(datopac.getTipoconsulta())) {

                    cell = new PdfPCell(new Phrase("", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);

                    if (datopac.getEdad() < 5) {
                        cell = new PdfPCell(new Phrase(Integer.toString(datopac.getEdad()), DATO_FONT));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table.addCell(cell);
                        sumas[2] += 1;

                        cell = new PdfPCell(new Phrase("", DATO_FONT));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        table.addCell(cell);

                        cell = new PdfPCell(new Phrase("", DATO_FONT));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table.addCell(cell);

                        cell = new PdfPCell(new Phrase("", DATO_FONT));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table.addCell(cell);

                        cell = new PdfPCell(new Phrase("", DATO_FONT));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table.addCell(cell);
                    } else if (datopac.getEdad() >= 5 && datopac.getEdad() < 60) {
                        cell = new PdfPCell(new Phrase("", DATO_FONT));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table.addCell(cell);

                        cell = new PdfPCell(new Phrase("", DATO_FONT));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table.addCell(cell);

                        //cell = new PdfPCell(new Phrase(Integer.toString(datopac.getEdad()) , DATO_FONT));
                        cell = new PdfPCell(new Phrase((datopac.getEmbarazo() == 0) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        table.addCell(cell);
                        sumas[4] += (datopac.getEmbarazo() == 0) ? 1 : 0;
                        //sumas[4]+=1;

                        cell = new PdfPCell(new Phrase("", DATO_FONT));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table.addCell(cell);

                        cell = new PdfPCell(new Phrase("", DATO_FONT));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table.addCell(cell);
                    } else {
                        cell = new PdfPCell(new Phrase("", DATO_FONT));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table.addCell(cell);

                        cell = new PdfPCell(new Phrase("", DATO_FONT));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table.addCell(cell);

                        cell = new PdfPCell(new Phrase("", DATO_FONT));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table.addCell(cell);

                        cell = new PdfPCell(new Phrase("", DATO_FONT));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table.addCell(cell);

                        cell = new PdfPCell(new Phrase(Integer.toString(datopac.getEdad()), DATO_FONT));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        table.addCell(cell);
                        sumas[6] += 1;
                    }
                }
                //mujeres embarazadas
                cell = new PdfPCell(new Phrase(("N".equals(datopac.getTipoconsulta()) && datopac.getEmbarazo() > 0) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT)); //?Integer.toString(datopac.getNumpieza()):
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[7] += ("N".equals(datopac.getTipoconsulta()) && datopac.getEmbarazo() == 1) ? 1 : 0;

                cell = new PdfPCell(new Phrase(("R".equals(datopac.getTipoconsulta()) && datopac.getEmbarazo() > 0) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[8] += ("R".equals(datopac.getTipoconsulta()) && datopac.getEmbarazo() == 1) ? 1 : 0;
                //fin mujeres embarazadas

                if (datopac.getExodoncia() == 1) {

                    cell = new PdfPCell(new Phrase(("P".equals(datopac.getTipodent()) && "A".equals(datopac.getTipo()) && datopac.getExodoncia() == 1) ? Integer.toString(datopac.getNumpieza()) : "", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                    sumas[9] += ("P".equals(datopac.getTipodent()) && "A".equals(datopac.getTipo()) && datopac.getExodoncia() == 1) ? 1 : 0;

                    cell = new PdfPCell(new Phrase(("T".equals(datopac.getTipodent()) && "A".equals(datopac.getTipo()) && datopac.getExodoncia() == 1) ? Integer.toString(datopac.getNumpieza()) : "", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                    sumas[10] += ("T".equals(datopac.getTipodent()) && "A".equals(datopac.getTipo()) && datopac.getExodoncia() == 1) ? 1 : 0;

                    cell = new PdfPCell(new Phrase(("B".equals(datopac.getTipo()) && datopac.getExodoncia() == 1) ? Integer.toString(datopac.getNumpieza()) : "", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                    sumas[11] += ("B".equals(datopac.getTipo()) && datopac.getExodoncia() == 1) ? 1 : 0;

                    cell = new PdfPCell(new Phrase(("C".equals(datopac.getTipo()) && datopac.getExodoncia() == 1) ? Integer.toString(datopac.getNumpieza()) : "", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                    sumas[12] += ("C".equals(datopac.getTipo()) && datopac.getExodoncia() == 1) ? 1 : 0;
                } else//aumenta 4 columnas vacias
                {
                    cell = new PdfPCell(new Phrase("", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                    cell = new PdfPCell(new Phrase("", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                    cell = new PdfPCell(new Phrase("", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                    cell = new PdfPCell(new Phrase("", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                }
                if (datopac.getRestauracion() == 1) {
                    cell = new PdfPCell(new Phrase(("A".equals(datopac.getTipo()) && datopac.getRestauracion() == 1) ? Integer.toString(datopac.getNumpieza()) : "", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                    sumas[13] += ("A".equals(datopac.getTipo()) && datopac.getRestauracion() == 1) ? 1 : 0;

                    cell = new PdfPCell(new Phrase(("B".equals(datopac.getTipo()) && datopac.getRestauracion() == 1) ? Integer.toString(datopac.getNumpieza()) : "", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                    sumas[14] += ("B".equals(datopac.getTipo()) && datopac.getRestauracion() == 1) ? 1 : 0;

                    cell = new PdfPCell(new Phrase(("C".equals(datopac.getTipo()) && datopac.getRestauracion() == 1) ? Integer.toString(datopac.getNumpieza()) : "", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                    sumas[15] += ("C".equals(datopac.getTipo()) && datopac.getRestauracion() == 1) ? 1 : 0;

                    cell = new PdfPCell(new Phrase(("D".equals(datopac.getTipo()) && datopac.getRestauracion() == 1) ? Integer.toString(datopac.getNumpieza()) : "", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                    sumas[16] += ("D".equals(datopac.getTipo()) && datopac.getRestauracion() == 1) ? 1 : 0;

                    cell = new PdfPCell(new Phrase(("E".equals(datopac.getTipo()) && datopac.getRestauracion() == 1) ? Integer.toString(datopac.getNumpieza()) : "", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                    sumas[17] += ("E".equals(datopac.getTipo()) && datopac.getRestauracion() == 1) ? 1 : 0;
                } else//aumenta 5 columnas vacias
                {
                    cell = new PdfPCell(new Phrase("", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                    cell = new PdfPCell(new Phrase("", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                    cell = new PdfPCell(new Phrase("", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                    cell = new PdfPCell(new Phrase("", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                    cell = new PdfPCell(new Phrase("", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                }
                if (datopac.getPeriodoncia() == 1) {
                    cell = new PdfPCell(new Phrase(("A".equals(datopac.getTipo()) && datopac.getPeriodoncia() == 1) ? Integer.toString(datopac.getNumpieza()) : "", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                    sumas[18] += ("A".equals(datopac.getTipo()) && datopac.getPeriodoncia() == 1) ? 1 : 0;

                    cell = new PdfPCell(new Phrase(("B".equals(datopac.getTipo()) && datopac.getPeriodoncia() == 1) ? Integer.toString(datopac.getNumpieza()) : "", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                    sumas[19] += ("B".equals(datopac.getTipo()) && datopac.getPeriodoncia() == 1) ? 1 : 0;

                    cell = new PdfPCell(new Phrase(("C".equals(datopac.getTipo()) && datopac.getPeriodoncia() == 1) ? Integer.toString(datopac.getNumpieza()) : "", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                    sumas[20] += ("C".equals(datopac.getTipo()) && datopac.getPeriodoncia() == 1) ? 1 : 0;
                } else {
                    cell = new PdfPCell(new Phrase("", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                    cell = new PdfPCell(new Phrase("", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                    cell = new PdfPCell(new Phrase("", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                }
                if (datopac.getEndodoncia() == 1) {

                    cell = new PdfPCell(new Phrase(("A".equals(datopac.getTipo()) && datopac.getEndodoncia() == 1) ? Integer.toString(datopac.getNumpieza()) : "", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                    sumas[21] += ("A".equals(datopac.getTipo()) && datopac.getEndodoncia() == 1) ? 1 : 0;

                    cell = new PdfPCell(new Phrase(("B".equals(datopac.getTipo()) && datopac.getEndodoncia() == 1) ? Integer.toString(datopac.getNumpieza()) : "", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                    sumas[22] += ("B".equals(datopac.getTipo()) && datopac.getEndodoncia() == 1) ? 1 : 0;
                } else {
                    cell = new PdfPCell(new Phrase("", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                    cell = new PdfPCell(new Phrase("", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);

                }
                if ("O".equals(datopac.getTipo())) {
                    cell = new PdfPCell(new Phrase((datopac.getRayosx() == 1) ? Integer.toString(datopac.getNumpieza()) : "", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                    sumas[23] += datopac.getRayosx();

                    cell = new PdfPCell(new Phrase("", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                } else {
                    cell = new PdfPCell(new Phrase("", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase((datopac.getRayosx() == 1) ? Integer.toString(datopac.getNumpieza()) : "", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                    sumas[24] += datopac.getRayosx();
                }
//Acciones preventivas    A flour, B profilaxis, C sellantes, D Carostaticos       
                if ("A".equals(datopac.getTipo()) && datopac.getPreventiva() == 1) {
                    cell = new PdfPCell(new Phrase(("A".equals(datopac.getTipo()) && datopac.getPreventiva() == 1) ? Integer.toString(datopac.getNumpieza()) : "", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                    sumas[25] += ("A".equals(datopac.getTipo()) && datopac.getPreventiva() == 1) ? 1 : 0;

                    cell = new PdfPCell(new Phrase("", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                } else {
                    cell = new PdfPCell(new Phrase("", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                    sumas[26] += 0;
                    cell = new PdfPCell(new Phrase("", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                }
                if ("B".equals(datopac.getTipo()) && datopac.getPreventiva() == 1) {
                    cell = new PdfPCell(new Phrase(("B".equals(datopac.getTipo()) && datopac.getPreventiva() == 1) ? Integer.toString(datopac.getNumpieza()) : "", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                    sumas[27] += ("B".equals(datopac.getTipo()) && datopac.getPreventiva() == 1) ? 1 : 0;
                } else {
                    cell = new PdfPCell(new Phrase("", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                }
                if ("C".equals(datopac.getTipo()) && datopac.getPreventiva() == 1) {
                    cell = new PdfPCell(new Phrase(("C".equals(datopac.getTipo()) && datopac.getPreventiva() == 1) ? Integer.toString(datopac.getNumpieza()) : "", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                    sumas[28] += ("C".equals(datopac.getTipo()) && datopac.getPreventiva() == 1) ? 1 : 0;

                    cell = new PdfPCell(new Phrase("", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                } else {
                    cell = new PdfPCell(new Phrase("", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                    sumas[29] += 0;
                    cell = new PdfPCell(new Phrase("", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                }
                if ("D".equals(datopac.getTipo()) && datopac.getPreventiva() == 1) {
                    cell = new PdfPCell(new Phrase(("D".equals(datopac.getTipo()) && datopac.getPreventiva() == 1) ? Integer.toString(datopac.getNumpieza()) : "", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                    sumas[30] += ("D".equals(datopac.getTipo()) && datopac.getPreventiva() == 1) ? 1 : 0;

                    cell = new PdfPCell(new Phrase("", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                } else {
                    cell = new PdfPCell(new Phrase("", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                    sumas[31] += 0;
                    cell = new PdfPCell(new Phrase("", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                }
                if ("E".equals(datopac.getTipo()) && datopac.getPreventiva() == 1) {
                    cell = new PdfPCell(new Phrase(("E".equals(datopac.getTipo()) && datopac.getPreventiva() == 1) ? Integer.toString(datopac.getNumpieza()) : "", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                    sumas[32] += ("E".equals(datopac.getTipo()) && datopac.getPreventiva() == 1) ? 1 : 0;

                    cell = new PdfPCell(new Phrase("", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                } else {
                    cell = new PdfPCell(new Phrase("", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                    sumas[33] += 0;
                    cell = new PdfPCell(new Phrase("", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                }
//fin preventivas           
                // if (datopac.getOtras()==0){           
                //     cell = new PdfPCell(new Phrase((datopac.getOtras()==0)?"CO":"" , DATO_FONT));
                //     cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                //     table.addCell(cell);
                //     sumas[34]+=(datopac.getOtras()==0)?1:0;
                // }
                // else{

                if ("A".equals(datopac.getTipo())) {
                    cell = new PdfPCell(new Phrase(("A".equals(datopac.getTipo()) && datopac.getOtras() == 1) ? "P" : "", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                    sumas[34] += ("A".equals(datopac.getTipo()) && datopac.getOtras() == 1) ? 1 : 0;
                } else if ("B".equals(datopac.getTipo())) {
                    cell = new PdfPCell(new Phrase(("B".equals(datopac.getTipo()) && datopac.getOtras() == 1) ? "DS" : "", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                    sumas[34] += ("B".equals(datopac.getTipo()) && datopac.getOtras() == 1) ? 1 : 0;
                } else if ("C".equals(datopac.getTipo())) {
                    cell = new PdfPCell(new Phrase(("C".equals(datopac.getTipo()) && datopac.getOtras() == 1) ? "A" : "", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                    sumas[34] += ("C".equals(datopac.getTipo()) && datopac.getOtras() == 1) ? 1 : 0;
                } else if ("D".equals(datopac.getTipo())) {
                    cell = new PdfPCell(new Phrase(("D".equals(datopac.getTipo()) && datopac.getOtras() == 1) ? "RP" : "", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                    sumas[34] += ("D".equals(datopac.getTipo()) && datopac.getOtras() == 1) ? 1 : 0;
                } else if ("E".equals(datopac.getTipo())) {
                    cell = new PdfPCell(new Phrase(("E".equals(datopac.getTipo()) && datopac.getOtras() == 1) ? "OT" : "", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                    sumas[34] += ("E".equals(datopac.getTipo()) && datopac.getOtras() == 1) ? 1 : 0;
                } else if ("F".equals(datopac.getTipo())) {
                    cell = new PdfPCell(new Phrase(("F".equals(datopac.getTipo()) && datopac.getOtras() == 1) ? "PCA" : "", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                    sumas[34] += ("F".equals(datopac.getTipo()) && datopac.getOtras() == 1) ? 1 : 0;
                } else if ("G".equals(datopac.getTipo())) {
                    cell = new PdfPCell(new Phrase(("G".equals(datopac.getTipo()) && datopac.getOtras() == 1) ? "CO" : "", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                    sumas[34] += ("G".equals(datopac.getTipo()) && datopac.getOtras() == 1) ? 1 : 0;
                } else {
                    cell = new PdfPCell(new Phrase(("H".equals(datopac.getTipo()) && datopac.getOtras() == 1) ? "O" : "", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                    sumas[34] += ("H".equals(datopac.getTipo()) && datopac.getOtras() == 1) ? 1 : 0;
                }
                //}
                cell = new PdfPCell(new Phrase(datopac.getReferido(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);
                sumas[35] += 0;

                cell = new PdfPCell(new Phrase(datopac.getContraref(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);
                sumas[36] += 0;

                cell = new PdfPCell(new Phrase(Integer.toString(datopac.getId_persona()), DATO_FONT));
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

            cell = new PdfPCell(new Phrase("TOTAL", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(7);
            table.addCell(cell);

            for (int j = 0; j < 36; j++) {
                cell = new PdfPCell(new Phrase(Integer.toString(sumas[j]), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);
            }
            for (int k = 0; k < 9; k++) { //coloca filas vacias entes de colocar totales
                cell = new PdfPCell(new Phrase("", CABEZA_COLUMNA_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
            }

            document.add(table);
            document.newPage();
        }
    }
//nueva hoja para   

    public static void addfila12(PdfPTable table) {

        PdfPCell cell;
        PdfPTable table1 = new PdfPTable(14);

        cell = new PdfPCell(new Phrase("Acciones Curativas", CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(14);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("Cirugia Bucal Menor", CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(4);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("Restauraciones", CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(5);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("Especialidades", CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(6);
        table1.addCell(cell);
        //Cirugia Bucal Menor
        addfila23(table1, "Exodoncia", "Permanente", "Temporaria");
        addfila1(table1, "Trat. Alveolitis");
        addfila1(table1, "Trat. Abs. Peripic");
        //restauraciones
        addfila1(table1, "Amalgama");
        addfila1(table1, "Resina Fot.");
        addfila1(table1, "Resina Aut.");
        addfila1(table1, "Ionometro");
        addfila1(table1, "PRAT-TRA");
        //Especialidades
        addfila23(table1, "Periodoncia", "Tartrectomia", "Gingiv. simple");
        addfila1(table1, "Cir.Maxilo-Facial");
        addfila23(table1, "Endodoncia", "Pulpotomia", "Trat. conducto");

        cell = new PdfPCell(table1);
        cell.setColspan(14);
        table.addCell(cell);
    }

    public static void addfila1(PdfPTable table, String a) {

        PdfPCell cell;
        PdfPTable table1 = new PdfPTable(1);

        cell = new PdfPCell(new Phrase(a, CABEZA_COLUMNA_FONT));
        cell.setRotation(90);
        cell.setFixedHeight(70);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("", CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1.addCell(cell);

        cell = new PdfPCell(table1);
        cell.setColspan(1);
        table.addCell(cell);

    }

    public static void addfila2(PdfPTable table, String a, String b, String c) {

        PdfPCell cell;
        PdfPTable table1 = new PdfPTable(2);

        cell = new PdfPCell(new Phrase(a, CABEZA_COLUMNA_FONT));
        cell.setRotation(90);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setFixedHeight(94);
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

    public static void addfila22(PdfPTable table, String a, String b, String c) {

        PdfPCell cell;
        PdfPTable table1 = new PdfPTable(2);

        cell = new PdfPCell(new Phrase(a, CABEZA_COLUMNA_FONT));
        cell.setRotation(90);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setFixedHeight(70);
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

    public static void addfila23(PdfPTable table, String a, String b, String c) {

        PdfPCell cell;
        PdfPTable table1 = new PdfPTable(2);

        cell = new PdfPCell(new Phrase(a, CABEZA_COLUMNA_FONT));
//     cell.setRotation(90);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//     cell.setFixedHeight(70);
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

    public static void addfila4(PdfPTable table) {

        PdfPCell cell;
        PdfPTable table1 = new PdfPTable(4);

        cell = new PdfPCell(new Phrase("Acciones Preventivas", CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(4);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("Fluor", CABEZA_COLUMNA_FONT));
        cell.setRotation(90);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("Profilaxis", CABEZA_COLUMNA_FONT));
        cell.setRotation(90);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("Sellantes", CABEZA_COLUMNA_FONT));
        cell.setRotation(90);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("TRA", CABEZA_COLUMNA_FONT));
        cell.setRotation(90);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1.addCell(cell);

        cell = new PdfPCell(table1);
        cell.setColspan(4);
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
