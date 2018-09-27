package org.ayaic.web.administrarhistoriales;

import java.awt.Color;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.view.document.AbstractPdfView;
import java.util.*;
import java.text.*;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Cuadernos;
import org.ayaic.domain.Historiales;
import org.ayaic.domain.Localidades;
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.Personas;
import org.ayaic.domain.Recetas;

public class HCLHojaAtencionReversoPDF extends AbstractPdfView {

    private static final Font TITULO_FONT = new Font(Font.HELVETICA, 12, Font.BOLD, Color.black);
    private static final Font TITULO_FONT_B = new Font(Font.HELVETICA, 16, Font.BOLD, Color.black);
    private static final Font CABEZA_COLUMNA_FONT = new Font(Font.HELVETICA, 10, Font.ITALIC, Color.black);
    private static final Font DATO_FONT = new Font(Font.HELVETICA, 11, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_BOLD = new Font(Font.COURIER, 10, Font.BOLD, Color.black);
    private static final int MARGIN = 32;

    protected void buildPdfMetadata(Map model, Document document, HttpServletRequest request) {
        document.setPageSize(PageSize.LETTER);
        document.setMargins(30, 30, 30, 20);
    }

    protected void buildPdfDocument(Map model, Document document, PdfWriter writer, HttpServletRequest req, HttpServletResponse resp) throws Exception {

        java.util.List ldatoLab = (java.util.List) model.get("datosLab");
        java.util.List ListaLab = (java.util.List) model.get("listalab");
        java.util.List hemograma = (java.util.List) model.get("hemo");
        java.util.List orina = (java.util.List) model.get("orina");
        java.util.List cie = (java.util.List) model.get("listaCie");
        java.util.List med = (java.util.List) model.get("listarRecetaTotal");
        Pacientes pacientes = (Pacientes) model.get("datosp");
        Personas persona = (Personas) model.get("persona");
        Localidades datoestab = (Localidades) model.get("datoestab");
        Historiales datopac = (Historiales) model.get("milistas");
        Clientes dato = (Clientes) model.get("dato");
        DecimalFormat df = new DecimalFormat("###,##0");

        Image escudo = Image.getInstance("/opt/imagenes/lescudo.bmp");
        Paragraph pp = new Paragraph("MOVIMIENTO ECONOMICO\n", new Font(Font.HELVETICA, 14, Font.BOLDITALIC, new Color(0, 0, 0)));
        pp.setAlignment(Element.ALIGN_CENTER);

        int[] sumas = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int NumColumns = 3;
        int una, filas = 30;

        PdfPCell cell;

        PdfPTable tablex3 = new PdfPTable(4);
        int[] fxwidths3 = {40, 20, 20, 20}; // percentage
        tablex3.setWidths(fxwidths3);
        tablex3.setWidthPercentage(100);

        String CIE10 = "";
        for (int k = 0; k < cie.size(); k++) {
            Cuadernos cie10 = (Cuadernos) cie.get(k);
            if (cie10.getId_historial() == datopac.getId_historial()) {
                CIE10 = cie10.getTipodent() + "  [" + (cie10.getObservaciones().toLowerCase()) + "]" + ";" + CIE10;
            }
        }

        if (datopac.getDiagnostico() == null || "null".equals(datopac.getDiagnostico())) {
            datopac.setDiagnostico("");
        } else {
            datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("<p>", "\n"));
            datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("</p>", " "));
            datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("&nbsp;", ""));
            datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("<strong>", " "));
            datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("</strong>", " "));
            datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("<br />", "\n"));
            datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("<u>", " "));
            datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("</u>", " "));
            datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("<ul>", " "));
            datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("</ul>", " "));
            datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("<ol>", " "));
            datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("</ol>", " "));
            datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("<li>", " "));
            datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("</li>", " "));
            datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("&ordf;", " "));
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
            datopac.setSubjetivo(datopac.getSubjetivo().replaceAll("&ordm;", "o"));
        }

        String labos = "";
        for (int j = 0; j < ListaLab.size(); j++) {
            Cuadernos datoLab1 = (Cuadernos) ListaLab.get(j);
            labos = datoLab1.getLaboratorio() + "  ;  " + labos;
        }

        ///CONTINUA EXAMEN  FISICO  :
        String cadena2 = "";
        String cadena3 = "";

        if (datopac.getObjetivo().length() > 1800) {
            cadena2 = datopac.getObjetivo().substring(1, 1800);
            cadena3 = datopac.getObjetivo().substring(1800, datopac.getObjetivo().length());
        }

        cell = new PdfPCell(new Phrase("///..." + cadena3, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setFixedHeight(60f);
        cell.setColspan(4);
        tablex3.addCell(cell);

        cell = new PdfPCell(new Phrase("\n\n\n\nEXAMEN  DE LABORATORIO Y  GABINETE SOLICITADOS\n\n" + labos + "\n\n", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(4);
        tablex3.addCell(cell);
        ///DIAGNOSTICO PRESUNTIVO:
        cell = new PdfPCell(new Phrase(" \n\n\n\n\n\n" + datopac.getDiagnostico() + "\nDiagnostico CIE10  :  " + CIE10, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(4);
        tablex3.addCell(cell);
        ///EVOLUCION DURANTE LA OBSERVACION :
        cell = new PdfPCell(new Phrase("                          " + datopac.getCadena3(), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(4);
        tablex3.addCell(cell);
        ///CONDUCTA FINAL   INTERNACION              ALTA           TRANSFERENCIA A :
        cell = new PdfPCell(new Phrase("  \n\n\n                " + datopac.getCadena4(), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(4);
        tablex3.addCell(cell);
        ///CONDOCIONES DE ALTA Y/O TRANSFERENCIA :
        cell = new PdfPCell(new Phrase(" \n\n\n                          " + datopac.getCadena5(), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(4);
        tablex3.addCell(cell);
        ///RECOMENDACIONES :

        int NumCol = 2;
        PdfPTable table12 = new PdfPTable(NumCol);
        table12.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("TRATAMIENTO : \n                       " + datopac.getAccion(), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(2);
        table12.addCell(cell);

        for (int j = 0; j < med.size(); j++) {
            Recetas medica = (Recetas) med.get(j);
            if (j % 2 != 0) {
                if (datopac.getId_historial() == medica.getId_historial()) {
                    cell = new PdfPCell(new Phrase((j + 1) + ".- *" + df.format(medica.getSalida()) + "," + " " + ((medica.getMedicamento().length() > 20) ? medica.getMedicamento().substring(0, 20) : medica.getMedicamento()) + "<" + medica.getForma_far() + ">" + medica.getConcentra() + "[" + medica.getIndicacion() + "]\n", new Font(Font.COURIER, 8, Font.NORMAL, Color.black)));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setBorder(Rectangle.NO_BORDER);
                    // cell.setColspan(3);
                    table12.addCell(cell);
                }
            } else {
                if (datopac.getId_historial() == medica.getId_historial()) {
                    cell = new PdfPCell(new Phrase((j + 1) + ".- *" + df.format(medica.getSalida()) + "," + " " + ((medica.getMedicamento().length() > 20) ? medica.getMedicamento().substring(0, 20) : medica.getMedicamento()) + "<" + medica.getForma_far() + ">" + medica.getConcentra() + "[" + medica.getIndicacion() + "]\n", new Font(Font.COURIER, 8, Font.NORMAL, Color.black)));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setBorder(Rectangle.NO_BORDER);
                    //cell.setColspan(2);
                    table12.addCell(cell);
                }
            }
        }
        cell = new PdfPCell(table12);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(4);
        tablex3.addCell(cell);

        ///OBSERVACIONES :
        cell = new PdfPCell(new Phrase("  \n\n\n               " + datopac.getCadena6(), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(4);
        tablex3.addCell(cell);

        cell = new PdfPCell(new Phrase("              " + persona.getNombres() + " " + persona.getPaterno() + " " + persona.getMaterno() + "          ", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(4);
        tablex3.addCell(cell);

        document.add(tablex3);

    }

    public static String format(Date dia, String formato) {
        if (dia == null) {
            return "";
        }
        SimpleDateFormat formatDate = new SimpleDateFormat(formato);
        return formatDate.format(dia);
    }
}
