package org.ayaic.web.administrarhistoriales;

import java.awt.Color;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.view.document.AbstractPdfView;
import com.itextpdf.text.pdf.BarcodeQRCode;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.*;
import java.text.*;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Cuadernos;
import org.ayaic.domain.Historiales;
import org.ayaic.domain.Localidades;
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.Recetas;

public class HCLCompletaPDF extends AbstractPdfView {

    private static final Font TITULO_FONT = new Font(Font.HELVETICA, 12, Font.BOLD, Color.black);
    private static final Font TITULO_FONT_B = new Font(Font.HELVETICA, 16, Font.BOLD, Color.black);
    private static final Font CABEZA_COLUMNA_FONT = new Font(Font.HELVETICA, 10, Font.ITALIC, Color.black);
    private static final Font DATO_FONT = new Font(Font.HELVETICA, 11, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_A = new Font(Font.HELVETICA, 10, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_BOLD = new Font(Font.COURIER, 10, Font.BOLD, Color.black);
    private static final int MARGIN = 32;

    protected void buildPdfMetadata(Map model, Document document, HttpServletRequest request) {
        document.setPageSize(PageSize.LETTER);
        document.setMargins(20, 20, 30, 20);
    }

    protected void buildPdfDocument(Map model, Document document, PdfWriter writer, HttpServletRequest req, HttpServletResponse resp) throws Exception {

        java.util.List ldatoLab = (java.util.List) model.get("datosLab");
        java.util.List ListaLab = (java.util.List) model.get("listalab");
        java.util.List hemograma = (java.util.List) model.get("hemo");
        java.util.List orina = (java.util.List) model.get("orina");
        java.util.List cie = (java.util.List) model.get("listaCie");
        Pacientes pacientes = (Pacientes) model.get("datosp");
        java.util.List med = (java.util.List) model.get("listarRecetaTotal");
        Localidades datoestab = (Localidades) model.get("datoestab");
        Historiales datopac = (Historiales) model.get("milistas");
        Clientes dato = (Clientes) model.get("dato");
        String cod = (String) model.get("cod");
        Date fecha = new Date();
        DecimalFormat df = new DecimalFormat("###,##0");
        PdfContentByte cb = writer.getDirectContent();
        InetAddress ip;
        ip = InetAddress.getLocalHost();
        NetworkInterface network = NetworkInterface.getByInetAddress(ip);
        //byte[] mac = network.getHardwareAddress();

        Image escudo = Image.getInstance("/opt/imagenes/lescudo.bmp");
        Paragraph pp = new Paragraph("MOVIMIENTO ECONOMICO\n", new Font(Font.HELVETICA, 14, Font.BOLDITALIC, new Color(0, 0, 0)));
        pp.setAlignment(Element.ALIGN_CENTER);

        int[] sumas = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int NumColumns = 3;
        int una, filas = 30;

        PdfPCell cell;

        PdfPTable tablex = new PdfPTable(2);
        int[] fxwidths = {35, 65}; // percentage
        tablex.setWidths(fxwidths);
        tablex.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase(datoestab.getNombreseg() + "\n" + datoestab.getEstablecimiento() + "\n\n", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setBorder(Rectangle.TOP);
        cell.setColspan(2);
        tablex.addCell(cell);

        cell = new PdfPCell(new Phrase("Fecha Atencion : " + format(datopac.getFecha(), "dd/MM/yyyy") + "    Hora : " + format(datopac.getFecha(), "HH:mm:ss") + "    Matricula : " + pacientes.getNro_registro() + "     Codigo Benef. : " + pacientes.getNro(), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(2);
        tablex.addCell(cell);

        cell = new PdfPCell(new Phrase("Nombres :  " + pacientes.getNombre() + "    Paterno :  " + pacientes.getPaterno() + "     Materno :  " + pacientes.getMaterno(), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(2);
        tablex.addCell(cell);

        cell = new PdfPCell(new Phrase("EDAD :  " + datopac.getEdad() + " a " + datopac.getMes() + " m " + datopac.getDia() + " d", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex.addCell(cell);

        cell = new PdfPCell(new Phrase("Telefono :   " + pacientes.getTelefono() + "                   HCL : " + Integer.toString(pacientes.getHcl()), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(2);
        tablex.addCell(cell);

        document.add(tablex);

        String CIE10 = "";
        for (int k = 0; k < cie.size(); k++) {
            Cuadernos cie10 = (Cuadernos) cie.get(k);
            if (cie10.getId_historial() == datopac.getId_historial()) {
                CIE10 = cie10.getTipodent() + "  [" + (cie10.getObservaciones().toLowerCase()) + "]" + ";" + CIE10;
            }
        }

        if (datopac.getSubjetivo() == null || "null".equals(datopac.getSubjetivo())) {
            datopac.setSubjetivo("");
        } else {
            datopac.setSubjetivo(datopac.getSubjetivo().replaceAll("<p>", "\n"));
            datopac.setSubjetivo(datopac.getSubjetivo().replaceAll("</p>", " "));
            datopac.setSubjetivo(datopac.getSubjetivo().replaceAll("&nbsp;", ""));
            datopac.setSubjetivo(datopac.getSubjetivo().replaceAll("<strong>", " "));
            datopac.setSubjetivo(datopac.getSubjetivo().replaceAll("</strong>", " "));
            datopac.setSubjetivo(datopac.getSubjetivo().replaceAll("<br />", "\n"));
            datopac.setSubjetivo(datopac.getSubjetivo().replaceAll("<u>", " "));
            datopac.setSubjetivo(datopac.getSubjetivo().replaceAll("</u>", " "));
            datopac.setSubjetivo(datopac.getSubjetivo().replaceAll("<ul>", " "));
            datopac.setSubjetivo(datopac.getSubjetivo().replaceAll("</ul>", " "));
            datopac.setSubjetivo(datopac.getSubjetivo().replaceAll("<ol>", " "));
            datopac.setSubjetivo(datopac.getSubjetivo().replaceAll("</ol>", " "));
            datopac.setSubjetivo(datopac.getSubjetivo().replaceAll("<li>", " "));
            datopac.setSubjetivo(datopac.getSubjetivo().replaceAll("</li>", " "));
            datopac.setSubjetivo(datopac.getSubjetivo().replaceAll("&ordf;", " "));
            datopac.setSubjetivo(datopac.getSubjetivo().replaceAll("&ntilde;", "n"));
            datopac.setSubjetivo(datopac.getSubjetivo().replaceAll("&Ntilde;", "N"));
            datopac.setSubjetivo(datopac.getSubjetivo().replaceAll("&Aacute;", "A"));
            datopac.setSubjetivo(datopac.getSubjetivo().replaceAll("&Eacute;", "E"));
            datopac.setSubjetivo(datopac.getSubjetivo().replaceAll("&Iacute;", "I"));
            datopac.setSubjetivo(datopac.getSubjetivo().replaceAll("&Oacute;", "O"));
            datopac.setSubjetivo(datopac.getSubjetivo().replaceAll("&Uacute;", "U"));
            datopac.setSubjetivo(datopac.getSubjetivo().replaceAll("&aacute;", "a"));
            datopac.setSubjetivo(datopac.getSubjetivo().replaceAll("&eacute;", "e"));
            datopac.setSubjetivo(datopac.getSubjetivo().replaceAll("&iacute;", "i"));
            datopac.setSubjetivo(datopac.getSubjetivo().replaceAll("&oacute;", "o"));
            datopac.setSubjetivo(datopac.getSubjetivo().replaceAll("&uacute;", "u"));
            datopac.setSubjetivo(datopac.getSubjetivo().replaceAll("&quot;", "'"));
            datopac.setSubjetivo(datopac.getSubjetivo().replaceAll("&ordm;", "o"));
        }
        if (datopac.getObjetivo() == null || "null".equals(datopac.getObjetivo())) {
            datopac.setObjetivo("");
        } else {
            datopac.setObjetivo(datopac.getObjetivo().replaceAll("<p>", " "));
            datopac.setObjetivo(datopac.getObjetivo().replaceAll("</p>", " "));
            datopac.setObjetivo(datopac.getObjetivo().replaceAll("&nbsp;", ""));
            datopac.setObjetivo(datopac.getObjetivo().replaceAll("<strong>", " "));
            datopac.setObjetivo(datopac.getObjetivo().replaceAll("</strong>", " "));
            datopac.setObjetivo(datopac.getObjetivo().replaceAll("<br />", " "));
            datopac.setObjetivo(datopac.getObjetivo().replaceAll("<u>", " "));
            datopac.setObjetivo(datopac.getObjetivo().replaceAll("</u>", " "));
            datopac.setObjetivo(datopac.getObjetivo().replaceAll("<ul>", " "));
            datopac.setObjetivo(datopac.getObjetivo().replaceAll("</ul>", " "));
            datopac.setObjetivo(datopac.getObjetivo().replaceAll("<ol>", " "));
            datopac.setObjetivo(datopac.getObjetivo().replaceAll("</ol>", " "));
            datopac.setObjetivo(datopac.getObjetivo().replaceAll("<li>", " "));
            datopac.setObjetivo(datopac.getObjetivo().replaceAll("</li>", " "));
            datopac.setObjetivo(datopac.getObjetivo().replaceAll("&ordf;", " "));
            datopac.setObjetivo(datopac.getObjetivo().replaceAll("&ntilde;", "n"));
            datopac.setObjetivo(datopac.getObjetivo().replaceAll("&Ntilde;", "N"));
            datopac.setObjetivo(datopac.getObjetivo().replaceAll("&Aacute;", "A"));
            datopac.setObjetivo(datopac.getObjetivo().replaceAll("&Eacute;", "E"));
            datopac.setObjetivo(datopac.getObjetivo().replaceAll("&Iacute;", "I"));
            datopac.setObjetivo(datopac.getObjetivo().replaceAll("&Oacute;", "O"));
            datopac.setObjetivo(datopac.getObjetivo().replaceAll("&Uacute;", "U"));
            datopac.setObjetivo(datopac.getObjetivo().replaceAll("&aacute;", "a"));
            datopac.setObjetivo(datopac.getObjetivo().replaceAll("&eacute;", "e"));
            datopac.setObjetivo(datopac.getObjetivo().replaceAll("&iacute;", "i"));
            datopac.setObjetivo(datopac.getObjetivo().replaceAll("&oacute;", "o"));
            datopac.setObjetivo(datopac.getObjetivo().replaceAll("&uacute;", "u"));
            datopac.setObjetivo(datopac.getObjetivo().replaceAll("&quot;", "'"));
            datopac.setSubjetivo(datopac.getSubjetivo().replaceAll("&ordm;", "o"));
        }
        if (datopac.getDiagnostico() == null || "null".equals(datopac.getDiagnostico())) {
            datopac.setDiagnostico("");
        } else {
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
        if (datopac.getAccion() == null || "null".equals(datopac.getAccion())) {
            datopac.setAccion("");
        } else {
            datopac.setAccion(datopac.getAccion().replaceAll("<p>", " "));
            datopac.setAccion(datopac.getAccion().replaceAll("</p>", " "));
            datopac.setAccion(datopac.getAccion().replaceAll("&nbsp;", ""));
            datopac.setAccion(datopac.getAccion().replaceAll("<strong>", " "));
            datopac.setAccion(datopac.getAccion().replaceAll("</strong>", " "));
            datopac.setAccion(datopac.getAccion().replaceAll("<br />", " "));
            datopac.setAccion(datopac.getAccion().replaceAll("<u>", " "));
            datopac.setAccion(datopac.getAccion().replaceAll("</u>", " "));
            datopac.setAccion(datopac.getAccion().replaceAll("<ul>", " "));
            datopac.setAccion(datopac.getAccion().replaceAll("</ul>", " "));
            datopac.setAccion(datopac.getAccion().replaceAll("<ol>", " "));
            datopac.setAccion(datopac.getAccion().replaceAll("</ol>", " "));
            datopac.setAccion(datopac.getAccion().replaceAll("<li>", " "));
            datopac.setAccion(datopac.getAccion().replaceAll("</li>", " "));
            datopac.setAccion(datopac.getAccion().replaceAll("&ordf;", " "));
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

        PdfPTable tablex3 = new PdfPTable(4);
        int[] fxwidths3 = {6, 30, 30, 30}; // percentage
        tablex3.setWidths(fxwidths3);
        tablex3.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("HISTORIA CLINICA DEL PACIENTE\n\n", TITULO_FONT_B));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(4);
        tablex3.addCell(cell);

        cell = new PdfPCell(new Phrase(datopac.getCadena7(), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(4);
        tablex3.addCell(cell);

        cell = new PdfPCell(new Phrase("Antecedentes Patologicos :\n                          " + datopac.getRepetida(), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(4);
        tablex3.addCell(cell);

        cell = new PdfPCell(new Phrase("Antecedentes No Patologicos :\n                          " + datopac.getCadena8(), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(4);
        tablex3.addCell(cell);

        cell = new PdfPCell(new Phrase("Antecedentes Gineco-obstetricos  : \n                         " + datopac.getCadena1(), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(4);
        tablex3.addCell(cell);

        cell = new PdfPCell(new Phrase("Antecedentes Familiares (Importantes)  : \n                         " + datopac.getRegistro(), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(4);
        tablex3.addCell(cell);

        cell = new PdfPCell(new Phrase("Historia de la Enfermedad :  \n                   " + datopac.getSubjetivo(), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(4);
        tablex3.addCell(cell);

        cell = new PdfPCell(new Phrase("EXAMEN  FISICO  GENERAL :        " + datopac.getObjetivo(), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(4);
        tablex3.addCell(cell);

        cell = new PdfPCell(new Phrase("EXAMEN  FISICO  SEGMENTADO :     ", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(4);
        tablex3.addCell(cell);

        cell = new PdfPCell(new Phrase("\n", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex3.addCell(cell);

        cell = new PdfPCell(new Phrase("CABEZA   :    " + datopac.getCadena9(), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(3);
        tablex3.addCell(cell);

        cell = new PdfPCell(new Phrase("\n", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex3.addCell(cell);

        cell = new PdfPCell(new Phrase("CARA   :    " + datopac.getCadena10(), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(3);
        tablex3.addCell(cell);

        cell = new PdfPCell(new Phrase("\n", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex3.addCell(cell);

        cell = new PdfPCell(new Phrase("CUELLO   :    " + datopac.getCadena11(), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(3);
        tablex3.addCell(cell);

        cell = new PdfPCell(new Phrase("\n", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex3.addCell(cell);

        cell = new PdfPCell(new Phrase("TORAX   :    " + datopac.getCadena12(), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(3);
        tablex3.addCell(cell);

        cell = new PdfPCell(new Phrase("\n", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex3.addCell(cell);

        cell = new PdfPCell(new Phrase("PULMONES   :    " + datopac.getCadena13(), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(3);
        tablex3.addCell(cell);

        cell = new PdfPCell(new Phrase("\n", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex3.addCell(cell);

        cell = new PdfPCell(new Phrase("CORAZON   :    " + datopac.getCadena14(), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(3);
        tablex3.addCell(cell);

        cell = new PdfPCell(new Phrase("\n", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex3.addCell(cell);

        cell = new PdfPCell(new Phrase("ABMDOMEN   :    " + datopac.getCadena15(), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(3);
        tablex3.addCell(cell);

        cell = new PdfPCell(new Phrase("\n", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex3.addCell(cell);

        cell = new PdfPCell(new Phrase("EXTREMIDADES   :    " + datopac.getCadena16(), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(3);
        tablex3.addCell(cell);

        cell = new PdfPCell(new Phrase("\n", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex3.addCell(cell);

        cell = new PdfPCell(new Phrase("GENITO-URINARIO : " + datopac.getCadena17(), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(3);
        tablex3.addCell(cell);

        cell = new PdfPCell(new Phrase("\n", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex3.addCell(cell);

        cell = new PdfPCell(new Phrase("NEUROLOGICO   :    " + datopac.getCadena18(), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(3);
        tablex3.addCell(cell);

        cell = new PdfPCell(new Phrase(" Signos Vitales  :  Talla : " + datopac.getTalla() + " Cm.   PESO : " + datopac.getPeso() + " Kg.   TEMPERATURA :" + datopac.getTemperatura() + " oC    F.C. : " + datopac.getFc() + "   P.A. : " + datopac.getPa() + "  F.R. : " + datopac.getFr(), DATO_FONT_A));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setColspan(4);
        tablex3.addCell(cell);

        cell = new PdfPCell(new Phrase("IMPRESION DIAGNOSTICA : \n                          " + datopac.getCadena21() + "\n\nDiagnostico CIE10  :  " + CIE10, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(4);
        tablex3.addCell(cell);

        String labos = "";
        for (int j = 0; j < ListaLab.size(); j++) {
            Cuadernos datoLab1 = (Cuadernos) ListaLab.get(j);
            labos = datoLab1.getLaboratorio() + "  ;  " + labos;
        }

        cell = new PdfPCell(new Phrase("\nEXAMEN  DE LABORATORIO Y  GABINETE SOLICITADOS\n\n" + labos, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(4);
        tablex3.addCell(cell);

        int NumCol = 2;
        PdfPTable table12 = new PdfPTable(NumCol);
        table12.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("TRATAMIENTO : \n                         " + datopac.getAccion(), DATO_FONT));
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

        cod = format(fecha, "dd/MM/yyyy HH:mm") + '|' + dato.getCod_esta() + '|' + dato.getId_usuario() + '|' + ip.getHostAddress() + '|' + datopac.getCod_esta() + '|' + datopac.getId_historial() + '|' + datopac.getId_historia();

        PdfContentByte cb1 = writer.getDirectContent();
        BarcodeQRCode qrcode = new BarcodeQRCode(cod, 90, 90, null);
        java.awt.Image qrImage = qrcode.createAwtImage(Color.black, Color.white);
        Image finalImage = Image.getInstance(writer, qrImage, 1);
        finalImage.setAbsolutePosition(20, 240);
        cb.addImage(finalImage, 90, 0, 0, 90, 515, 650, true);
        ///(imagen,tamañoAncho ,rotation_x ,angulorotation , amplitud-altura ,TamañoAlto ,PosicionX ,PosicionY(0=abajo, 280=arriba))

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
