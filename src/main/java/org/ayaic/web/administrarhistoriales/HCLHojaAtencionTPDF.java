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

public class HCLHojaAtencionTPDF extends AbstractPdfView {

    private static final Font TITULO_FONT = new Font(Font.HELVETICA, 12, Font.BOLD, Color.black);
    private static final Font TITULO_FONT_B = new Font(Font.HELVETICA, 16, Font.BOLD, Color.black);
    private static final Font CABEZA_COLUMNA_FONT = new Font(Font.HELVETICA, 10, Font.ITALIC, Color.black);
    private static final Font DATO_FONT = new Font(Font.HELVETICA, 11, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_A = new Font(Font.HELVETICA, 9, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_BOLD = new Font(Font.COURIER, 10, Font.BOLD, Color.black);
    private static final int MARGIN = 32;

    protected void buildPdfMetadata(Map model, Document document, HttpServletRequest request) {
        document.setPageSize(PageSize.LETTER);
        document.setMargins(20, 20, 35, 20);
    }

    protected void buildPdfDocument(Map model, Document document, PdfWriter writer, HttpServletRequest req, HttpServletResponse resp) throws Exception {

        java.util.List ldatoLab = (java.util.List) model.get("datosLab");
        java.util.List ListaLab = (java.util.List) model.get("listalab");
        java.util.List hemograma = (java.util.List) model.get("hemo");
        java.util.List orina = (java.util.List) model.get("orina");
        Pacientes pacientes = (Pacientes) model.get("datosp");
        java.util.List cie = (java.util.List) model.get("listaCie");
        java.util.List med = (java.util.List) model.get("listarRecetaTotal");
        Localidades datoestab = (Localidades) model.get("datoestab");
        Historiales datopac = (Historiales) model.get("milistas");
        Clientes dato = (Clientes) model.get("dato");
        DecimalFormat df = new DecimalFormat("###,##0");
        PdfContentByte cb = writer.getDirectContent();
        String cod = (String) model.get("cod");
        Date fecha = new Date();
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
        Paragraph p1;

        ////Historiales datopac = (Historiales) HCL.get(i);	
        PdfPTable tablex = new PdfPTable(3);
        int[] fxwidths = {55, 55, 35}; // percentage
        tablex.setWidths(fxwidths);
        tablex.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("                " + datoestab.getNombreseg() + "\n                " + datoestab.getEstablecimiento() + "\n", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        //cell.setBorder(Rectangle.TOP);
        tablex.addCell(cell);

        cell = new PdfPCell(new Phrase("HISTORIA CLINICA\n    EMERGENCIA", TITULO_FONT_B));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        //cell.setBorder(Rectangle.TOP);
        tablex.addCell(cell);

        cell = new PdfPCell(new Phrase("\n\nNo.: " + Integer.toString(datopac.getId_historia()), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex.addCell(cell);

        document.add(tablex);

        PdfPTable tablex1 = new PdfPTable(4);
        int[] fxwidths1 = {25, 25, 25, 25}; // percentage
        tablex1.setWidths(fxwidths1);
        tablex1.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("Fecha Aten. : " + format(datopac.getFecha(), "dd/MM/yyyy"), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setGrayFill(0.9f);
        tablex1.addCell(cell);

        cell = new PdfPCell(new Phrase("Hora :  " + format(datopac.getFecha(), "HH:mm:ss"), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setGrayFill(0.9f);
        tablex1.addCell(cell);

        cell = new PdfPCell(new Phrase("Matricula : " + pacientes.getNro_registro(), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setGrayFill(0.9f);
        tablex1.addCell(cell);

        cell = new PdfPCell(new Phrase("Codigo  :  " + pacientes.getNro(), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setGrayFill(0.9f);
        tablex1.addCell(cell);

        if (datopac.getId_tipo_sexo() == 1) {
            cell = new PdfPCell(new Phrase("Nombres y Apellidos : " + pacientes.getNombres() + "              Sexo : FEMENINO  ", DATO_FONT));
        } else {
            cell = new PdfPCell(new Phrase("Nombres y Apellidos : " + pacientes.getNombres() + "              Sexo : MASCULINO  ", DATO_FONT));
        }
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setGrayFill(0.9f);
        cell.setColspan(4);
        tablex1.addCell(cell);

        document.add(tablex1);

        PdfPTable tablex3 = new PdfPTable(4);
        int[] fxwidths3 = {40, 20, 20, 20}; // percentage
        tablex3.setWidths(fxwidths3);
        tablex3.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("Patronal : " + pacientes.getRegistro() + "    [" + pacientes.getCadena1() + "]", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setGrayFill(0.9f);
        cell.setColspan(4);
        tablex3.addCell(cell);

        cell = new PdfPCell(new Phrase("Telefono :  " + pacientes.getTelefono(), DATO_FONT));
        cell = new PdfPCell(new Phrase("C.I. : " + pacientes.getCarnet() + "  " + pacientes.getExpedido() + "     Telefono :  " + pacientes.getTelefono() + "      Edad : " + datopac.getEdad() + " a " + datopac.getMes() + " m " + datopac.getDia() + " d" + "  Fecha Nacim. : " + format(pacientes.getFec_nacimiento(), "dd/MM/yyyy"), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setGrayFill(0.9f);
        cell.setColspan(4);
        tablex3.addCell(cell);

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
            datopac.setSubjetivo(datopac.getSubjetivo().replaceAll("<div>", " "));
            datopac.setSubjetivo(datopac.getSubjetivo().replaceAll("</div>", " "));
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
            datopac.setObjetivo(datopac.getObjetivo().replaceAll("<p>", "\n"));
            datopac.setObjetivo(datopac.getObjetivo().replaceAll("</p>", " "));
            datopac.setObjetivo(datopac.getObjetivo().replaceAll("<div>", " "));
            datopac.setObjetivo(datopac.getObjetivo().replaceAll("</div>", " "));
            datopac.setObjetivo(datopac.getObjetivo().replaceAll("&nbsp;", ""));
            datopac.setObjetivo(datopac.getObjetivo().replaceAll("<strong>", " "));
            datopac.setObjetivo(datopac.getObjetivo().replaceAll("</strong>", " "));
            datopac.setObjetivo(datopac.getObjetivo().replaceAll("<br />", "\n"));
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
            datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("<p>", "\n"));
            datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("</p>", " "));
            datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("<div>", " "));
            datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("</div>", " "));
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
        if (datopac.getAccion() == null || "null".equals(datopac.getAccion())) {
            datopac.setAccion("");
        } else {
            datopac.setAccion(datopac.getAccion().replaceAll("<p>", "\n"));
            datopac.setAccion(datopac.getAccion().replaceAll("</p>", " "));
            datopac.setAccion(datopac.getAccion().replaceAll("<div>", " "));
            datopac.setAccion(datopac.getAccion().replaceAll("</div>", " "));
            datopac.setAccion(datopac.getAccion().replaceAll("&nbsp;", ""));
            datopac.setAccion(datopac.getAccion().replaceAll("<strong>", " "));
            datopac.setAccion(datopac.getAccion().replaceAll("</strong>", " "));
            datopac.setAccion(datopac.getAccion().replaceAll("<br />", "\n"));
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

        if (datopac.getCadena2().length() > 3 && datopac.getCadena2() != null) {
            cell = new PdfPCell(new Phrase("Motivo de Consulta : \n                               " + datopac.getCadena2(), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(4);
            tablex3.addCell(cell);
        }

        if (datopac.getRepetida().length() > 3 && datopac.getRepetida() != null) {
            cell = new PdfPCell(new Phrase("Antecedentes Patologicos :\n                           " + datopac.getRepetida(), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(4);
            tablex3.addCell(cell);
        }

        if (datopac.getRegistro().length() > 3 && datopac.getRegistro() != null) {
            cell = new PdfPCell(new Phrase("Antecedentes Familiares (Importantes) : \n                        " + datopac.getRegistro(), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(4);
            tablex3.addCell(cell);
        }

        if (datopac.getCadena1().length() > 3 && datopac.getCadena1() != null) {
            cell = new PdfPCell(new Phrase("Antecedentes Gineco-obstetricos (Si corresponde) : \n                         " + datopac.getCadena1(), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(4);
            tablex3.addCell(cell);
        }

        if (datopac.getSubjetivo().length() > 3 && datopac.getSubjetivo() != null) {
            cell = new PdfPCell(new Phrase("Historia de la Enfermedad :  \n                        " + datopac.getSubjetivo(), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(4);
            tablex3.addCell(cell);
        }

        cell = new PdfPCell(new Phrase("  Signos Vitales  :  Talla : " + datopac.getTalla() + " Cm.   Peso : " + datopac.getPeso() + " Kg.   Temp. :" + datopac.getTemperatura() + " oC    F.C. : " + datopac.getFc() + "   P.A. : " + datopac.getPa() + "  F.R. : " + datopac.getFr() + "   SO2 : " + datopac.getSo2() + "  Glicemia : " + datopac.getGlicemia(), DATO_FONT_A));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setColspan(4);
        tablex3.addCell(cell);

        if (datopac.getObjetivo().length() > 3 && datopac.getObjetivo() != null) {
            cell = new PdfPCell(new Phrase("EXAMEN  FISICO  :   " + datopac.getObjetivo(), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(4);
            tablex3.addCell(cell);
        }

        String labos = "";
        for (int j = 0; j < ListaLab.size(); j++) {
            Cuadernos datoLab1 = (Cuadernos) ListaLab.get(j);
            labos = datoLab1.getLaboratorio() + "  ;  " + labos;
        }

        if (labos.length() > 3 && labos != null) {
            cell = new PdfPCell(new Phrase("EXAMEN  DE LABORATORIO Y  GABINETE SOLICITADOS\n\n" + labos + "\n\n", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(4);
            tablex3.addCell(cell);
        }

        if (datopac.getCadena21().length() > 3 && datopac.getCadena21() != null) {
            cell = new PdfPCell(new Phrase("DIAGNOSTICO PRESUNTIVO: \n                          " + datopac.getCadena21() + "\n\nDiagnostico CIE10  :  " + CIE10, DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(4);
            tablex3.addCell(cell);
        }

        if (datopac.getCadena3().length() > 3 && datopac.getCadena3() != null) {
            cell = new PdfPCell(new Phrase("EVOLUCION DURANTE LA OBSERVACION : \n                          " + datopac.getCadena3(), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(4);
            tablex3.addCell(cell);
        }

        if (datopac.getCadena4().length() > 3 && datopac.getCadena4() != null) {
            cell = new PdfPCell(new Phrase("CONDUCTA FINAL   INTERNACION              ALTA           TRANSFERENCIA A : \n                 " + datopac.getCadena4(), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(4);
            tablex3.addCell(cell);
        }

        if (datopac.getCadena5().length() > 3 && datopac.getCadena5() != null) {
            cell = new PdfPCell(new Phrase("CONDOCIONES DE ALTA Y/O TRANSFERENCIA : \n                          " + datopac.getCadena5(), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(4);
            tablex3.addCell(cell);
        }

        int NumCol = 2;
        PdfPTable table12 = new PdfPTable(NumCol);
        table12.setWidthPercentage(100);

        if (datopac.getCadena20().length() > 3 && datopac.getCadena20() != null) {
            cell = new PdfPCell(new Phrase("RECOMENDACIONES : \n                         " + datopac.getCadena20(), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(2);
            table12.addCell(cell);
        }

        for (int j = 0; j < med.size(); j++) {
            Recetas medica = (Recetas) med.get(j);
            if (j % 2 != 0) {
                if (datopac.getId_historial() == medica.getId_historial()) {
                    cell = new PdfPCell(new Phrase((j + 1) + ".- *" + df.format(medica.getSalida()) + "," + " " + ((medica.getMedicamento().length() > 20) ? medica.getMedicamento().substring(0, 20) : medica.getMedicamento()) + "<" + medica.getForma_far() + ">" + medica.getConcentra() + "[" + medica.getIndicacion() + "]\n", new Font(Font.COURIER, 7, Font.NORMAL, Color.black)));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setBorder(Rectangle.NO_BORDER);
                    // cell.setColspan(3);
                    table12.addCell(cell);
                }
            } else {
                if (datopac.getId_historial() == medica.getId_historial()) {
                    cell = new PdfPCell(new Phrase((j + 1) + ".- *" + df.format(medica.getSalida()) + "," + " " + ((medica.getMedicamento().length() > 20) ? medica.getMedicamento().substring(0, 20) : medica.getMedicamento()) + "<" + medica.getForma_far() + ">" + medica.getConcentra() + "[" + medica.getIndicacion() + "]\n", new Font(Font.COURIER, 7, Font.NORMAL, Color.black)));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setBorder(Rectangle.NO_BORDER);
                    //cell.setColspan(2);
                    table12.addCell(cell);
                }
            }
        }
        cell = new PdfPCell(table12);
        //cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(4);
        tablex3.addCell(cell);

        if (datopac.getCadena6().length() > 3 && datopac.getCadena6() != null) {
            cell = new PdfPCell(new Phrase("OBSERVACIONES : \n                " + datopac.getCadena6(), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(4);
            tablex3.addCell(cell);
        }

        cod = format(fecha, "dd/MM/yyyy HH:mm") + '|' + dato.getCod_esta() + '|' + dato.getId_usuario() + '|' + ip.getHostAddress() + '|' + datopac.getCod_esta() + '|' + datopac.getId_historial() + '|' + datopac.getId_historia();

        PdfContentByte cb1 = writer.getDirectContent();
        BarcodeQRCode qrcode = new BarcodeQRCode(cod, 90, 90, null);
        java.awt.Image qrImage = qrcode.createAwtImage(Color.black, Color.white);
        Image finalImage = Image.getInstance(writer, qrImage, 1);
        finalImage.setAbsolutePosition(20, 240);
        cb.addImage(finalImage, 80, 0, 0, 80, 525, 700, true);
        ///(imagen,tamañoAncho ,rotation_x ,angulorotation , amplitud-altura ,TamañoAlto ,PosicionX ,PosicionY(0=abajo, 280=arriba))

        escudo.setAbsolutePosition(50, 50);
        cb.addImage(escudo, 50, 0, 0, 60, 20, 720, true);

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
