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
import org.ayaic.domain.Seguros;

public class VerCuadernosPDF extends AbstractPdfView {

    private static final Font TITULO_FONT = new Font(Font.HELVETICA, 12, Font.BOLD, Color.black);
    private static final Font CABEZA_COLUMNA_FONT = new Font(Font.HELVETICA, 8, Font.ITALIC, Color.black);
    private static final Font DATO_FONT = new Font(Font.HELVETICA, 8, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_P = new Font(Font.HELVETICA, 6, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_BOLD = new Font(Font.HELVETICA, 10, Font.BOLD, Color.red);
    private static final int MARGIN = 32;

    protected void buildPdfMetadata(Map model, Document document, HttpServletRequest request) {
        Localidades dl = (Localidades) model.get("localidades");
        document.setPageSize(PageSize.LEGAL.rotate());
        document.setMargins(dl.getIzquierda(), dl.getDerecha(), dl.getSuperior(), dl.getInferior());
    }

    protected void buildPdfDocument(Map model, Document document, PdfWriter writer, HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Clientes dato = (Clientes) model.get("dato");
        java.util.List listaCuaderno1 = (java.util.List) model.get("listaCuadernos");
        java.util.List cie = (java.util.List) model.get("listaCie");
        java.util.List listaSeg = (java.util.List) model.get("listaPacAfi");
        java.util.List confvariables = (java.util.List) model.get("confvariables");

        int[] sumas = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int[] sumbs = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int una, filas = 30;
        String Fecha = "";

        if (listaCuaderno1.size() == 0) {
            Paragraph p = new Paragraph("No existe Datos", new Font(Font.HELVETICA, 22, Font.BOLDITALIC, new Color(0, 0, 0)));
            document.add(p);
        }

        if (listaCuaderno1.size() % filas == 0) {
            una = 0;
        } else {
            una = 1;
        }

        for (int pag = 0; pag < listaCuaderno1.size() / filas + una; pag++) {
            Paragraph p;
            PdfPCell cell;
            int NumColumns = 0;

            for (int k = 0; k < confvariables.size(); k++) {
                Cuadernos imp = (Cuadernos) confvariables.get(k);
                NumColumns += imp.getSuma3();
            }
            int fwidths[] = new int[NumColumns];
            String Campos[] = new String[NumColumns];
            int c = 0;
            for (int k = 0; k < confvariables.size(); k++) {
                Cuadernos imp = (Cuadernos) confvariables.get(k);
                if (imp.getSuma3() > 1) {
                    for (int i = 0; i < imp.getSuma3(); i++) {
                        fwidths[k + c + i] = imp.getSuma2();
                        Campos[k + c + i] = imp.getBilirrubina();
                        c = c + i;
                    }
                } else {
                    if (imp.getId_cuaderno() == 2) {
                        Fecha = imp.getBacterias();
                    }
                    fwidths[k + c] = imp.getSuma2();
                    Campos[k + c] = imp.getBilirrubina();
                }
            }

            PdfPTable table = new PdfPTable(NumColumns);
            table.setWidths(fwidths);
            table.setWidthPercentage(100);

            cell = new PdfPCell(new Phrase("Cuaderno No. XXXXX                                                                                                                                                       REGISTRO DE ATENCION  EN EMERGENCIAS", TITULO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(NumColumns);
            table.addCell(cell);

            for (int k = 0; k < confvariables.size(); k++) {
                Cuadernos imp = (Cuadernos) confvariables.get(k);
                switch (imp.getSuma3()) {
                    case 1:
                        cell = new PdfPCell(new Phrase(imp.getBilirrubina(), CABEZA_COLUMNA_FONT));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table.addCell(cell);
                        break;
                    case 2:
                        addtitulofila20(table, imp.getBilirrubina(), imp.getContraref(), imp.getCristales());
                        break;
                    case 3:
                        cell = new PdfPCell(new Phrase(imp.getBilirrubina(), CABEZA_COLUMNA_FONT));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setColspan(3);
                        table.addCell(cell);
                        break;
                    case 4:
                        cell = new PdfPCell(new Phrase(imp.getBilirrubina(), CABEZA_COLUMNA_FONT));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setColspan(4);
                        table.addCell(cell);
                        break;

                }
            }

            // coloca el detalle de loS datos
            for (int i = pag * filas + 0; i < pag * filas + filas && i < listaCuaderno1.size(); i++) {

                Cuadernos datopac = (Cuadernos) listaCuaderno1.get(i);

                cell = new PdfPCell(new Phrase(Integer.toString(i + 1), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(format(datopac.getFechap(), Fecha), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(Integer.toString(datopac.getId_historial()), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                if ("S".equals(datopac.getTipoconsulta())) {
                    cell = new PdfPCell(new Phrase("SIIS", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                } else {
                    if ("E".equals(datopac.getTipoconsulta())) {
                        cell = new PdfPCell(new Phrase("Externo", DATO_FONT));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        table.addCell(cell);
                    } else {
                        for (int j = 0; j < listaSeg.size(); j++) {
                            Seguros datoseg = (Seguros) listaSeg.get(j);
                            if (datopac.getId_seguro() == datoseg.getId_seguro()) {
                                cell = new PdfPCell(new Phrase(datoseg.getSeguro(), DATO_FONT));
                                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                                table.addCell(cell);
                            }
                        }
                    }
                }

                cell = new PdfPCell(new Phrase(datopac.getRegistro(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((datopac.getNombres().length() > 30) ? datopac.getNombres().substring(0, 30) : datopac.getNombres(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                if (datopac.getId_tipo_sexo() == 2) {
                    cell = new PdfPCell(new Phrase("M", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase("", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);
                } else {
                    cell = new PdfPCell(new Phrase("", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase("F", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
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
//coloca nuevos y repetidos         
                String cuno = "", cdos = "", ctres = "";

                cell = new PdfPCell(new Phrase(Integer.toString(datopac.getId_persona()), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);
            }

            for (int i = listaCuaderno1.size(); i < pag * filas + filas; i++) {
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
            for (int j = 0; j < 5; j++) {
                cell = new PdfPCell(new Phrase(Integer.toString(sumas[j]), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(cell);
            }

            for (int k = 0; k < 20; k++) { //coloca filas vacias entes de colocar totales
                cell = new PdfPCell(new Phrase("", CABEZA_COLUMNA_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
            }

            document.add(table);

            //comienza nueva hoja 2 
            document.newPage();

            int NumCol = 6;
            PdfPTable tablea = new PdfPTable(NumCol);
            int[] mfwidths = {4, 4, 4, 4, 4, 4};
            tablea.setWidths(mfwidths);
            tablea.setWidthPercentage(100);

            NumColumns = 10;
            table = new PdfPTable(NumColumns);
            int[] fwidthx = {7, 40, 20, 30, 5, 15, 5, 15, 15, 7};
            table.setWidths(fwidthx);
            table.setWidthPercentage(100);

            cell = new PdfPCell(new Phrase("Cuaderno No. 1                                                                                                                                                       REGISTRO DE ATENCION  EN EMERGENCIAS", TITULO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(NumColumns);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("No", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Diagnostico    \n-->[CIE10]", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Examanes\nComplemtarios", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Tratamiento", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Ambulatorio", DATO_FONT));
            cell.setVerticalAlignment(Element.ALIGN_CENTER);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setRotation(90);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Internado\nServicio", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Rechaza\nInternacion", DATO_FONT));
            cell.setVerticalAlignment(Element.ALIGN_CENTER);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setRotation(90);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Referido\n a:", DATO_FONT));
            cell.setVerticalAlignment(Element.ALIGN_CENTER);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Retornado\n a:", DATO_FONT));
            cell.setVerticalAlignment(Element.ALIGN_CENTER);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Respons.", CABEZA_COLUMNA_FONT));
            cell.setVerticalAlignment(Element.ALIGN_CENTER);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setRotation(90);
            table.addCell(cell);

            for (int i = pag * filas + 0; i < pag * filas + filas && i < listaCuaderno1.size(); i++) {

                Cuadernos datopac = (Cuadernos) listaCuaderno1.get(i);

                cell = new PdfPCell(new Phrase(Integer.toString(i + 1), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                if (datopac.getDiagnostico() == null || "null".equals(datopac.getDiagnostico())) {
                    datopac.setDiagnostico("");
                } else {
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("<p>", ""));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("</p>", ""));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("&nbsp;", ""));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("<strong>", ""));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("</strong>", ""));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("<br />", " "));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("<u>", " "));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("</u>", " "));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("<ol>", " "));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("</ol>", " "));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("<ul>", " "));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("</ul>", " "));
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
                }

                String CIE10 = "";
                for (int k = 0; k < cie.size(); k++) {
                    Cuadernos cie10 = (Cuadernos) cie.get(k);
                    if (cie10.getId_historial() == datopac.getId_laboratorio()) {
                        CIE10 = cie10.getTipodent() + "[" + cie10.getObservaciones().toLowerCase() + "]" + ";" + CIE10;
                    }
                }

                cell = new PdfPCell(new Phrase(datopac.getDiagnostico() + "->" + CIE10.trim(), DATO_FONT_P));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                if (datopac.getAccion() == null) {
                } else {
                    datopac.setAccion(datopac.getAccion().replaceAll("<p>", ""));
                    datopac.setAccion(datopac.getAccion().replaceAll("</p>", ""));
                    datopac.setAccion(datopac.getAccion().replaceAll("&nbsp;", ""));
                    datopac.setAccion(datopac.getAccion().replaceAll("<strong>", " "));
                    datopac.setAccion(datopac.getAccion().replaceAll("</strong>", " "));
                    datopac.setAccion(datopac.getAccion().replaceAll("<br />", " "));
                    datopac.setAccion(datopac.getAccion().replaceAll("<u>", " "));
                    datopac.setAccion(datopac.getAccion().replaceAll("</u>", " "));
                    datopac.setAccion(datopac.getAccion().replaceAll("<ol>", " "));
                    datopac.setAccion(datopac.getAccion().replaceAll("</ol>", " "));
                    datopac.setAccion(datopac.getAccion().replaceAll("<ul>", " "));
                    datopac.setAccion(datopac.getAccion().replaceAll("</ul>", " "));
                    datopac.setAccion(datopac.getAccion().replaceAll("<li>", " "));
                    datopac.setAccion(datopac.getAccion().replaceAll("</li>", " "));
                    datopac.setAccion(datopac.getAccion().replaceAll("&ntilde;", "n"));
                    datopac.setAccion(datopac.getAccion().replaceAll("&Ntilde;", "N"));
                    datopac.setAccion(datopac.getAccion().replaceAll("&Uacute;", "U"));
                    datopac.setAccion(datopac.getAccion().replaceAll("&Oacute;", "O"));
                    datopac.setAccion(datopac.getAccion().replaceAll("&Iacute;", "I"));
                    datopac.setAccion(datopac.getAccion().replaceAll("&Aacute;", "A"));
                    datopac.setAccion(datopac.getAccion().replaceAll("&Eacute;", "E"));
                    datopac.setAccion(datopac.getAccion().replaceAll("&uacute;", "u"));
                    datopac.setAccion(datopac.getAccion().replaceAll("&oacute;", "o"));
                    datopac.setAccion(datopac.getAccion().replaceAll("&iacute;", "i"));
                    datopac.setAccion(datopac.getAccion().replaceAll("&aacute;", "a"));
                    datopac.setAccion(datopac.getAccion().replaceAll("&eacute;", "e"));
                    datopac.setAccion(datopac.getAccion().replaceAll("&quot;", "'"));
                }
                //cell = new PdfPCell(new Phrase((datopac.getAccion().length()>26)?datopac.getAccion().substring(0,26):datopac.getAccion() , DATO_FONT));

                cell = new PdfPCell(new Phrase(datopac.getAccion(), DATO_FONT_P));
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

                cell = new PdfPCell(new Phrase(datopac.getReferido(), DATO_FONT_P));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(datopac.getContraref(), DATO_FONT_P));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(Integer.toString(datopac.getId_persona()), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

            }
            for (int i = listaCuaderno1.size(); i < pag * filas + filas; i++) {
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

            for (int j = 0; j < 16; j++) {
                cell = new PdfPCell(new Phrase(Integer.toString(sumbs[j]), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(cell);
            }

            for (int k = 0; k < 15; k++) { //coloca filas vacias entes de colocar el dato
                cell = new PdfPCell(new Phrase("", CABEZA_COLUMNA_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
            }

            document.add(table);
            document.newPage();

        }

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
        cell.setRotation(90);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase(b, DATO_FONT_P));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setRotation(90);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase(c, DATO_FONT_P));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setRotation(90);
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
        cell.setRotation(90);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase(b, DATO_FONT_P));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setRotation(90);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase(c, DATO_FONT_P));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setRotation(90);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase(d, DATO_FONT_P));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setRotation(90);
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
