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

public class VerCuaderno1CNSPDF extends AbstractPdfView {

    private static final Font TITULO_FONT = new Font(Font.HELVETICA, 12, Font.BOLD, Color.black);
    private static final Font CABEZA_COLUMNA_FONT = new Font(Font.HELVETICA, 8, Font.ITALIC, Color.black);
    private static final Font DATO_FONT = new Font(Font.HELVETICA, 8, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_P = new Font(Font.HELVETICA, 6, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_BOLD = new Font(Font.HELVETICA, 8, Font.BOLD, Color.black);
    private static final int MARGIN = 32;

    protected void buildPdfMetadata(Map model, Document document, HttpServletRequest request) {
        Localidades dl = (Localidades) model.get("localidades");
        document.setPageSize(PageSize.LEGAL.rotate());
        document.setMargins(dl.getIzquierda(), dl.getDerecha(), dl.getSuperior(), dl.getInferior());

    }

    protected void buildPdfDocument(Map model, Document document, PdfWriter writer, HttpServletRequest req, HttpServletResponse resp) throws Exception {

        java.util.List lFopos = (java.util.List) model.get("listaCobros");
        java.util.List cie = (java.util.List) model.get("listaCie");
        Clientes dato = (Clientes) model.get("dato");
        Image escudo = Image.getInstance("/opt/imagenes/lescudo.bmp");
        Paragraph p = new Paragraph("INFORME DIARIO DE CONSULTA EXTERNA", new Font(Font.HELVETICA, 14, Font.BOLDITALIC, new Color(0, 0, 0)));
        int[] sumas = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        //int[] sumbs={0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0};
        int una, filas = 30;

        if (lFopos.size() == 0) {
            Paragraph pp = new Paragraph("No existe Datos", new Font(Font.HELVETICA, 22, Font.BOLDITALIC, new Color(0, 0, 0)));
            document.add(pp);
        }

        if (lFopos.size() % filas == 0) {
            una = 0;
        } else {
            una = 1;
        }

        for (int pag = 0; pag < lFopos.size() / filas + una; pag++) {

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
            //cell = new PdfPCell(new Phrase(" " , DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            tablex.addCell(cell);

            cell = new PdfPCell(new Phrase("Form. EM 202", TITULO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setBorder(Rectangle.NO_BORDER);
            tablex.addCell(cell);

            document.add(tablex);

            PdfPTable table1 = new PdfPTable(2);
            int[] fmwidths = {50, 50}; // percentage
            table1.setWidths(fmwidths);
            table1.setWidthPercentage(100);

            cell = new PdfPCell(new Phrase("Regional : " + dato.getDepartamento() + "               Establecimiento : " + dato.getEstablecimiento() + "              Servicio : " + dato.getConsultorio() + "                   No Consultorio :            " + "                     Fecha  :  " + format(new Date(), "dd/MM/yyyy") + "                      Firma y Sello Medico : ", DATO_FONT));
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(2);
            table1.addCell(cell);

            cell = new PdfPCell(new Phrase("Medico : " + dato.getNombres() + "                                                Titular  :  |_|       Suplente  :  |_|" + "                                              Horas de Consulta   :   " + "                                                             Enfermera  :  ", DATO_FONT));
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(2);
            table1.addCell(cell);

            document.add(table1);

            int NumColumns = 51;
            PdfPTable table = new PdfPTable(NumColumns);
            int[] fwidths = {13, 40, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 85, 20};
            table.setWidths(fwidths);
            table.setWidthPercentage(100);

            // coloca la cabecera de titulos
            cell = new PdfPCell(new Phrase("No", CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Matricula", CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            addtitulofila2(table, "EDAD y SEXO", "M", "F");

            addfila12(table, "Tipo Asegurado");

            addtitulofila2(table, "Consulta", "N", "R");

            addtitulofila31(table, "Peso Talla", "Obesidad", "Sobrepeso", "Normal");
            addtitulofila31(table, "Desnutricion", "Leve", "Moderada", "Grave");
            addtitulofila21(table, "Talla/ Edad", "Normal", "Talla Baja");
            addtitulofila4(table, "Dosis de Huerro", "1ra", "2da", "de 2 años a 3", "de 3 a 5 años");
            addtitulofila31(table, "Vitamina A", "Dosis Unica", "1ra", "2da");

            addtitulofila31(table, "Aten. Mujer\nControl Prenatal", "Sem. Gest.", "No Control", "Dosis Hierro");

            addtitulofila4(table, "Estado Nutricional (IMC)", "Bajo Peso", "Normal", "SobrePeso", "Obesidad");
            addtitulofila21(table, "Cancer", "Cuello Uterino", "Mama");
            addtitulofila21(table, "Hipertension", "Prediebetes", "Diabetes");
            addtitulofila31(table, "Fact. Riesgo", "Hipert. Arterial", "ITS", "Sint. Resp.");

            addtitulofila21(table, "Enferm. Prevalen", "IRAs", "EDAs");
            addtitulofila21(table, "Examenes Compl.", "Laboratorio", "Imagen");
            addtitulofila5(table, "Otras Actividades", "Referencia", "Retorno", "Riesgo Profec.", "Incap. Temporal", "Nro.Prescrip.");

            cell = new PdfPCell(new Phrase("Diagnostico", CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Cie10", CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setRotation(90);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            for (int j = 1; j < 51; j++) {
                cell = new PdfPCell(new Phrase(Integer.toString(j), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
            }
            // coloca el detalle de loS datos
            for (int i = pag * filas + 0; i < pag * filas + filas && i < lFopos.size(); i++) {

                Cuadernos datopac = (Cuadernos) lFopos.get(i);

                cell = new PdfPCell(new Phrase(Integer.toString(i + 1), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                String CIE10 = "";
                for (int k = 0; k < cie.size(); k++) {
                    Cuadernos cie10 = (Cuadernos) cie.get(k);
                    if (cie10.getId_historial() == datopac.getId_laboratorio()) {
                        CIE10 = cie10.getTipodent() + "[" + (cie10.getObservaciones().toLowerCase().length() > 15 ? cie10.getObservaciones().toLowerCase().substring(0, 15) : cie10.getObservaciones().toLowerCase()) + "]" + ";" + CIE10;
                    }
                }

                if (datopac.getDiagnostico() != null) {
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("<p>", " "));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("</p>", " "));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("&nbsp;", ""));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("<strong>", " "));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("</strong>", " "));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("<br />", " "));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("<u>", " "));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("</u>", " "));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("<ul>", " "));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("</ul>", " "));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("<ol>", " "));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("</ol>", " "));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("<li>", " "));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("</li>", " "));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("&ntilde;", "n"));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("&Ntilde;", "N"));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("&Aacute;", "A"));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("&Eacute;", "E"));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("&Iacute;", "I"));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("&Oacute;", "O"));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("&Uacute;", "U"));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("&aacute;", "a"));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("&eacute;", "e"));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("&iacute;", "i"));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("&oacute;", "o"));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("&uacute;", "u"));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("&quot;", "'"));
                    datopac.getDiagnostico().trim();
                }

                cell = new PdfPCell(new Phrase(datopac.getRegistro(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 2) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[0] += (datopac.getId_cuaderno() == 2) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 1) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[1] += (datopac.getId_cuaderno() == 1) ? 1 : 0;

                if (datopac.getEdad() < 61 && datopac.getId_seguro() == 35) {
                    if ("ID".equals(datopac.getCadena1())) {
                        cell = new PdfPCell(new Phrase("A", DATO_FONT));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table.addCell(cell);
                        sumas[2] += (datopac.getId_seguro() == 35) ? 1 : 0;

                        cell = new PdfPCell(new Phrase("", DATO_FONT));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table.addCell(cell);
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

                        cell = new PdfPCell(new Phrase("B", DATO_FONT));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table.addCell(cell);
                        sumas[3] += (datopac.getId_seguro() == 35) ? 1 : 0;

                        cell = new PdfPCell(new Phrase("", DATO_FONT));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table.addCell(cell);
                        cell = new PdfPCell(new Phrase("", DATO_FONT));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table.addCell(cell);
                    }
                } else {
                    if (datopac.getId_seguro() == 35) {
                        if ("ID".equals(datopac.getCadena1())) {
                            cell = new PdfPCell(new Phrase("", DATO_FONT));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                            cell = new PdfPCell(new Phrase("", DATO_FONT));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);

                            cell = new PdfPCell(new Phrase("A", DATO_FONT));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                            sumas[4] += (datopac.getId_seguro() == 35) ? 1 : 0;

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

                            cell = new PdfPCell(new Phrase("B", DATO_FONT));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                            sumas[5] += (datopac.getId_seguro() == 35) ? 1 : 0;
                        }
                    }
                }

                if (datopac.getId_seguro() != 35) {////sumi

                    cell = new PdfPCell(new Phrase("", DATO_FONT)); ////seguro Otro
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase("", DATO_FONT)); ////seguro Otro
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase("", DATO_FONT)); ////seguro Otro
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase("", DATO_FONT)); ////seguro Otro
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase("X", DATO_FONT)); ////seguro Otro
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);
                    sumas[6] += (datopac.getId_seguro() != 35) ? 1 : 0;

                } else {
                    cell = new PdfPCell(new Phrase("", DATO_FONT)); ////seguro Otro
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);
                }

                cell = new PdfPCell(new Phrase(("N".equals(datopac.getTipoconsulta())) ? "X" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[7] += ("N".equals(datopac.getTipoconsulta())) ? 1 : 0;

                cell = new PdfPCell(new Phrase(("R".equals(datopac.getTipoconsulta())) ? "X" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[8] += ("R".equals(datopac.getTipoconsulta())) ? 1 : 0;

                if (datopac.getCadena2().equals("O")) {
                    cell = new PdfPCell(new Phrase((datopac.getCadena2().equals("O")) ? "X" : "", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                    sumas[9] += (datopac.getCadena2().equals("O")) ? 1 : 0;
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
                    cell = new PdfPCell(new Phrase("", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);
                } else {
                    if (datopac.getCadena2().equals("S")) {
                        cell = new PdfPCell(new Phrase("", DATO_FONT));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        table.addCell(cell);
                        cell = new PdfPCell(new Phrase((datopac.getCadena2().equals("S")) ? "X" : "", DATO_FONT));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table.addCell(cell);
                        sumas[10] += (datopac.getCadena2().equals("S")) ? 1 : 0;
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
                    } else {
                        if (datopac.getCadena2().equals("N")) {
                            cell = new PdfPCell(new Phrase("", DATO_FONT));
                            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                            table.addCell(cell);
                            cell = new PdfPCell(new Phrase("", DATO_FONT));
                            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                            table.addCell(cell);
                            cell = new PdfPCell(new Phrase((datopac.getCadena2().equals("N")) ? "X" : "", DATO_FONT));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                            sumas[11] += (datopac.getCadena2().equals("N")) ? 1 : 0;
                            cell = new PdfPCell(new Phrase("", DATO_FONT));
                            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                            table.addCell(cell);
                            cell = new PdfPCell(new Phrase("", DATO_FONT));
                            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                            table.addCell(cell);
                            cell = new PdfPCell(new Phrase("", DATO_FONT));
                            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                            table.addCell(cell);
                        } else {
                            if (datopac.getCadena2().equals("L")) {
                                cell = new PdfPCell(new Phrase("", DATO_FONT));
                                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                                table.addCell(cell);
                                cell = new PdfPCell(new Phrase("", DATO_FONT));
                                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                                table.addCell(cell);
                                cell = new PdfPCell(new Phrase("", DATO_FONT));
                                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                                table.addCell(cell);
                                cell = new PdfPCell(new Phrase((datopac.getCadena2().equals("L")) ? "X" : "", DATO_FONT));
                                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                table.addCell(cell);
                                sumas[12] += (datopac.getCadena2().equals("L")) ? 1 : 0;
                                cell = new PdfPCell(new Phrase("", DATO_FONT));
                                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                                table.addCell(cell);
                                cell = new PdfPCell(new Phrase("", DATO_FONT));
                                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                                table.addCell(cell);
                            } else {
                                if (datopac.getCadena2().equals("M")) {
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
                                    cell = new PdfPCell(new Phrase((datopac.getCadena2().equals("M")) ? "X" : "", DATO_FONT));
                                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    table.addCell(cell);
                                    sumas[13] += (datopac.getCadena2().equals("M")) ? 1 : 0;
                                    cell = new PdfPCell(new Phrase("", DATO_FONT));
                                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                                    table.addCell(cell);
                                } else {
                                    if (datopac.getCadena2().equals("G")) {
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
                                        cell = new PdfPCell(new Phrase("", DATO_FONT));
                                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                                        table.addCell(cell);
                                        cell = new PdfPCell(new Phrase((datopac.getCadena2().equals("G")) ? "X" : "", DATO_FONT));
                                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                        table.addCell(cell);
                                        sumas[14] += (datopac.getCadena2().equals("G")) ? 1 : 0;
                                    } else {
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
                                        cell = new PdfPCell(new Phrase("", DATO_FONT));
                                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                                        table.addCell(cell);
                                        cell = new PdfPCell(new Phrase("", DATO_FONT));
                                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                                        table.addCell(cell);
                                    }
                                }
                            }
                        }
                    }
                }

                if (datopac.getCadena3().equals("N")) {
                    cell = new PdfPCell(new Phrase((datopac.getCadena3().equals("N")) ? "X" : "", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                    sumas[15] += (datopac.getCadena3().equals("N")) ? 1 : 0;
                    cell = new PdfPCell(new Phrase("", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);
                } else {
                    if (datopac.getCadena3().equals("L")) {
                        cell = new PdfPCell(new Phrase("", DATO_FONT));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        table.addCell(cell);
                        cell = new PdfPCell(new Phrase((datopac.getCadena3().equals("L")) ? "X" : "", DATO_FONT));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table.addCell(cell);
                        sumas[16] += (datopac.getCadena3().equals("L")) ? 1 : 0;
                    } else {
                        cell = new PdfPCell(new Phrase("", DATO_FONT));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        table.addCell(cell);
                        cell = new PdfPCell(new Phrase("", DATO_FONT));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        table.addCell(cell);
                    }
                }

                for (int j = 1; j < 5; j++) {
                    cell = new PdfPCell(new Phrase("", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);
                }////////////esto es patra el hierro

                if (datopac.getCadena5().equals("U")) {
                    cell = new PdfPCell(new Phrase((datopac.getCadena5().equals("U")) ? "X" : "", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                    sumas[23] += (datopac.getCadena5().equals("U")) ? 1 : 0;
                    cell = new PdfPCell(new Phrase("", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);
                    cell = new PdfPCell(new Phrase("", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);
                } else {
                    if (datopac.getCadena5().equals("P")) {
                        cell = new PdfPCell(new Phrase("", DATO_FONT));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        table.addCell(cell);
                        cell = new PdfPCell(new Phrase((datopac.getCadena5().equals("P")) ? "X" : "", DATO_FONT));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table.addCell(cell);
                        sumas[24] += (datopac.getCadena5().equals("P")) ? 1 : 0;
                        cell = new PdfPCell(new Phrase("", DATO_FONT));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        table.addCell(cell);
                    } else {
                        if (datopac.getCadena5().equals("S")) {
                            cell = new PdfPCell(new Phrase("", DATO_FONT));
                            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                            table.addCell(cell);
                            cell = new PdfPCell(new Phrase("", DATO_FONT));
                            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                            table.addCell(cell);
                            cell = new PdfPCell(new Phrase((datopac.getCadena5().equals("S")) ? "X" : "", DATO_FONT));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                            sumas[25] += (datopac.getCadena5().equals("S")) ? 1 : 0;
                        } else {
                            cell = new PdfPCell(new Phrase("", DATO_FONT));
                            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                            table.addCell(cell);
                            cell = new PdfPCell(new Phrase("", DATO_FONT));
                            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                            table.addCell(cell);
                            cell = new PdfPCell(new Phrase("", DATO_FONT));
                            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                            table.addCell(cell);
                        }
                    }
                }

                cell = new PdfPCell(new Phrase((datopac.getSuma15() > 0) ? Integer.toString(datopac.getSuma15()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[24] += (datopac.getSuma15() > 0) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getSuma16() > 0) ? Integer.toString(datopac.getSuma16()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[25] += (datopac.getSuma16() > 0) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getCadena4().equals("1")) ? "X" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[26] += (datopac.getCadena4().equals("1")) ? 1 : 0;

                if (datopac.getTalla() > 0 && datopac.getEdad() > 5) {
                    double imc = datopac.getPeso() / ((datopac.getTalla()) * (datopac.getTalla()) / (100 * 100));

                    cell = new PdfPCell(new Phrase((imc < 20) ? "X" : "", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                    sumas[27] += (imc < 20) ? 1 : 0;
                    cell = new PdfPCell(new Phrase((imc >= 20 && imc < 25) ? "X" : "", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                    sumas[28] += (imc >= 20 && imc < 25) ? 1 : 0;
                    cell = new PdfPCell(new Phrase((imc >= 25 && imc < 30) ? "X" : "", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                    sumas[29] += (imc >= 25 && imc < 30) ? 1 : 0;
                    cell = new PdfPCell(new Phrase((imc >= 30) ? "X" : "", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                    sumas[30] += (imc >= 30) ? 1 : 0;
                } else {
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
                }

                cell = new PdfPCell(new Phrase((datopac.getCancer() == 1) ? "X" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[31] += (datopac.getCancer() == 1) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getCancero() == 1) ? "X" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[32] += (datopac.getCancero() == 1) ? 1 : 0;

                cell = new PdfPCell(new Phrase("", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                //sumas[33]+=()?1:0;

                cell = new PdfPCell(new Phrase((datopac.getDiabetes() == 1) ? "X" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[34] += (datopac.getDiabetes() == 1) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getArterial() == 1) ? "X" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[35] += (datopac.getArterial() == 1) ? 1 : 0;

                cell = new PdfPCell(new Phrase("", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                //sumas[36]+=()?1:0;

                cell = new PdfPCell(new Phrase("", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                //sumas[37]+=()?1:0;

                if ("J301".equals(datopac.getTipodent()) || "J05".equals(datopac.getTipodent()) || "J37".equals(datopac.getTipodent()) || "J300".equals(datopac.getTipodent()) || "J312".equals(datopac.getTipodent()) || "J320".equals(datopac.getTipodent()) || "J321".equals(datopac.getTipodent()) || "J310".equals(datopac.getTipodent()) || "J311".equals(datopac.getTipodent()) || "J304".equals(datopac.getTipodent()) || "J04".equals(datopac.getTipodent()) || "J040".equals(datopac.getTipodent()) || "J201".equals(datopac.getTipodent()) || "J32".equals(datopac.getTipodent()) || "J020".equals(datopac.getTipodent()) || "J200".equals(datopac.getTipodent()) || "J202".equals(datopac.getTipodent()) || "J207".equals(datopac.getTipodent()) || "J208".equals(datopac.getTipodent()) || "J328".equals(datopac.getTipodent()) || "J203".equals(datopac.getTipodent()) || "J209".equals(datopac.getTipodent()) || "J329".equals(datopac.getTipodent()) || "J370".equals(datopac.getTipodent()) || "J324".equals(datopac.getTipodent()) || "J21".equals(datopac.getTipodent()) || "J210".equals(datopac.getTipodent()) || "J218".equals(datopac.getTipodent()) || "J219".equals(datopac.getTipodent()) || "J22".equals(datopac.getTipodent()) || "J050".equals(datopac.getTipodent()) || "J039".equals(datopac.getTipodent()) || "J038".equals(datopac.getTipodent()) || "J030".equals(datopac.getTipodent()) || "J029".equals(datopac.getTipodent()) || "J028".equals(datopac.getTipodent()) || "J00".equals(datopac.getTipodent()) || "J30".equals(datopac.getTipodent()) || "J322".equals(datopac.getTipodent()) || "J060".equals(datopac.getTipodent()) || "J323".equals(datopac.getTipodent()) || "J204".equals(datopac.getTipodent()) || "J02".equals(datopac.getTipodent()) || "J303".equals(datopac.getTipodent()) || "J205".equals(datopac.getTipodent()) || "J20".equals(datopac.getTipodent()) || "J206".equals(datopac.getTipodent()) || "J302".equals(datopac.getTipodent()) || "J03".equals(datopac.getTipodent())) {
                    cell = new PdfPCell(new Phrase("X", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                    sumas[38] += ("J301".equals(datopac.getTipodent()) || "J05".equals(datopac.getTipodent()) || "J37".equals(datopac.getTipodent()) || "J300".equals(datopac.getTipodent()) || "J312".equals(datopac.getTipodent()) || "J320".equals(datopac.getTipodent()) || "J321".equals(datopac.getTipodent()) || "J310".equals(datopac.getTipodent()) || "J311".equals(datopac.getTipodent()) || "J304".equals(datopac.getTipodent()) || "J04".equals(datopac.getTipodent()) || "J040".equals(datopac.getTipodent()) || "J201".equals(datopac.getTipodent()) || "J32".equals(datopac.getTipodent()) || "J020".equals(datopac.getTipodent()) || "J200".equals(datopac.getTipodent()) || "J202".equals(datopac.getTipodent()) || "J207".equals(datopac.getTipodent()) || "J208".equals(datopac.getTipodent()) || "J328".equals(datopac.getTipodent()) || "J203".equals(datopac.getTipodent()) || "J209".equals(datopac.getTipodent()) || "J329".equals(datopac.getTipodent()) || "J370".equals(datopac.getTipodent()) || "J324".equals(datopac.getTipodent()) || "J21".equals(datopac.getTipodent()) || "J210".equals(datopac.getTipodent()) || "J218".equals(datopac.getTipodent()) || "J219".equals(datopac.getTipodent()) || "J22".equals(datopac.getTipodent()) || "J050".equals(datopac.getTipodent()) || "J039".equals(datopac.getTipodent()) || "J038".equals(datopac.getTipodent()) || "J030".equals(datopac.getTipodent()) || "J029".equals(datopac.getTipodent()) || "J028".equals(datopac.getTipodent()) || "J00".equals(datopac.getTipodent()) || "J30".equals(datopac.getTipodent()) || "J322".equals(datopac.getTipodent()) || "J060".equals(datopac.getTipodent()) || "J323".equals(datopac.getTipodent()) || "J204".equals(datopac.getTipodent()) || "J02".equals(datopac.getTipodent()) || "J303".equals(datopac.getTipodent()) || "J205".equals(datopac.getTipodent()) || "J20".equals(datopac.getTipodent()) || "J206".equals(datopac.getTipodent()) || "J302".equals(datopac.getTipodent()) || "J03".equals(datopac.getTipodent())) ? 1 : 0;
                } else {
                    cell = new PdfPCell(new Phrase("", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                }

                if ("A02".equals(datopac.getTipodent()) || "K591".equals(datopac.getTipodent()) || "A081".equals(datopac.getTipodent()) || "A09".equals(datopac.getTipodent()) || "A012".equals(datopac.getTipodent()) || "A085".equals(datopac.getTipodent()) || "A082".equals(datopac.getTipodent()) || "A011".equals(datopac.getTipodent()) || "A010".equals(datopac.getTipodent()) || "A03".equals(datopac.getTipodent()) || "A013".equals(datopac.getTipodent()) || "A084".equals(datopac.getTipodent()) || "A01".equals(datopac.getTipodent()) || "A083".equals(datopac.getTipodent()) || "A039".equals(datopac.getTipodent()) || "A038".equals(datopac.getTipodent()) || "A033".equals(datopac.getTipodent()) || "A032".equals(datopac.getTipodent()) || "A031".equals(datopac.getTipodent()) || "A04".equals(datopac.getTipodent()) || "A060".equals(datopac.getTipodent()) || "A030".equals(datopac.getTipodent()) || "A061".equals(datopac.getTipodent()) || "A014".equals(datopac.getTipodent())) {
                    cell = new PdfPCell(new Phrase("X", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                    sumas[39] += ("A02".equals(datopac.getTipodent()) || "K591".equals(datopac.getTipodent()) || "A081".equals(datopac.getTipodent()) || "A09".equals(datopac.getTipodent()) || "A012".equals(datopac.getTipodent()) || "A085".equals(datopac.getTipodent()) || "A082".equals(datopac.getTipodent()) || "A011".equals(datopac.getTipodent()) || "A010".equals(datopac.getTipodent()) || "A03".equals(datopac.getTipodent()) || "A013".equals(datopac.getTipodent()) || "A084".equals(datopac.getTipodent()) || "A01".equals(datopac.getTipodent()) || "A083".equals(datopac.getTipodent()) || "A039".equals(datopac.getTipodent()) || "A038".equals(datopac.getTipodent()) || "A033".equals(datopac.getTipodent()) || "A032".equals(datopac.getTipodent()) || "A031".equals(datopac.getTipodent()) || "A04".equals(datopac.getTipodent()) || "A060".equals(datopac.getTipodent()) || "A030".equals(datopac.getTipodent()) || "A061".equals(datopac.getTipodent()) || "A014".equals(datopac.getTipodent())) ? 1 : 0;
                } else {
                    cell = new PdfPCell(new Phrase("", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                }

                cell = new PdfPCell(new Phrase(datopac.getSuma11() > 0 ? Integer.toString(datopac.getSuma11()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[40] += (datopac.getSuma11() > 0) ? datopac.getSuma11() : 0;

                cell = new PdfPCell(new Phrase(datopac.getSuma12() > 0 ? Integer.toString(datopac.getSuma12()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[41] += (datopac.getSuma12() > 0) ? datopac.getSuma12() : 0;

                cell = new PdfPCell(new Phrase((datopac.getSuma30() > 0 || datopac.getSuma13() > 0) ? "X" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[42] += (datopac.getSuma30() > 0 || datopac.getSuma13() > 0) ? 1 : 0;

                cell = new PdfPCell(new Phrase("", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                //sumas[43]+=()?1:0;

                cell = new PdfPCell(new Phrase("", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                //sumas[44]+=()?1:0;

                cell = new PdfPCell(new Phrase("", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                //sumas[45]+=()?1:0;

                cell = new PdfPCell(new Phrase(datopac.getSuma10() > 0 ? Integer.toString(datopac.getSuma10()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[46] += (datopac.getSuma10() > 0) ? datopac.getSuma10() : 0;

                cell = new PdfPCell(new Phrase((datopac.getDiagnostico().length() > 30) ? datopac.getDiagnostico().substring(0, 30) : datopac.getDiagnostico(), DATO_FONT_P));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(datopac.getTipodent(), DATO_FONT));
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
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setColspan(2);
            table.addCell(cell);

            for (int j = 0; j < 47; j++) {
                cell = new PdfPCell(new Phrase(Integer.toString(sumas[j]), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(cell);
            }

            for (int j = 1; j < 47; j++) {
                cell = new PdfPCell(new Phrase("", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);
            }

            document.add(table);
            document.newPage();
        }

    }

    public static void addfila12(PdfPTable table, String a) {
        PdfPCell cell;
        PdfPTable table1 = new PdfPTable(5);

        cell = new PdfPCell(new Phrase(a, CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(5);
        table1.addCell(cell);

        addfila2(table1, "Activo", "A", "B");
        addfila2(table1, "Rentista", "A", "B");

        cell = new PdfPCell(new Phrase("Otros", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table1.addCell(cell);

        cell = new PdfPCell(table1);
        cell.setColspan(5);
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

    public static void addtitulofila21(PdfPTable table, String cadena, String a, String b) {
        PdfPCell cell;
        int NumColumns = 2;
        PdfPTable table11 = new PdfPTable(NumColumns);
        table11.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase(cadena, CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(2);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase(a, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setRotation(90);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase(b, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setRotation(90);
        table11.addCell(cell);

        cell = new PdfPCell(table11);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setColspan(2);
        table.addCell(cell);
    }

    public static void addtitulofila3(PdfPTable table, String cadena, String a, String b, String c) {
        PdfPCell cell;
        int NumColumns = 3;
        PdfPTable table11 = new PdfPTable(NumColumns);
        table11.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase(cadena, CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(3);
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

    public static void addtitulofila31(PdfPTable table, String cadena, String a, String b, String c) {
        PdfPCell cell;
        int NumColumns = 3;
        PdfPTable table11 = new PdfPTable(NumColumns);
        table11.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase(cadena, CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(3);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase(a, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setRotation(90);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase(b, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setRotation(90);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase(c, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setRotation(90);
        table11.addCell(cell);

        cell = new PdfPCell(table11);
        cell.setColspan(3);
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
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(5);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase(a, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setRotation(90);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase(b, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setRotation(90);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase(c, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setRotation(90);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase(d, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setRotation(90);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase(e, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setRotation(90);
        table11.addCell(cell);

        cell = new PdfPCell(table11);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setColspan(5);
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
