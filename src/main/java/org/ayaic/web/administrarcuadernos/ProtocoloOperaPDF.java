package org.ayaic.web.administrarcuadernos;

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
import org.ayaic.domain.Camas;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Cuadernos;
import org.ayaic.domain.Localidades;
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.Personas;
import org.ayaic.domain.Quirofanos;
import org.ayaic.domain.Seguros;

public class ProtocoloOperaPDF extends AbstractPdfView {

    private static final Font TITULO_FONT = new Font(Font.HELVETICA, 12, Font.BOLD, Color.black);
    private static final Font TITULO_FONT_B = new Font(Font.HELVETICA, 16, Font.BOLD, Color.black);
    private static final Font CABEZA_COLUMNA_FONT = new Font(Font.HELVETICA, 10, Font.ITALIC, Color.black);
    private static final Font DATO_FONT = new Font(Font.HELVETICA, 11, Font.NORMAL, Color.black);
    private static final Font DATO_FONT3 = new Font(Font.HELVETICA, 10, Font.NORMAL, Color.black);
    private static final Font DATO_FONT2 = new Font(Font.HELVETICA, 11, Font.NORMAL, Color.white);
    private static final Font DATO_FONT_BOLD = new Font(Font.COURIER, 10, Font.BOLD, Color.black);
    private static final Font marcagua = new Font(Font.HELVETICA, 26, Font.BOLD, Color.lightGray);
    private static final int MARGIN = 32;

    protected void buildPdfMetadata(Map model, Document document, HttpServletRequest request) {
        document.setPageSize(PageSize.LETTER);
        document.setMargins(20, 20, 25, 20);
    }

    protected void buildPdfDocument(Map model, Document document, PdfWriter writer, HttpServletRequest req, HttpServletResponse resp) throws Exception {

        java.util.List HCL = (java.util.List) model.get("listaProt");
        java.util.List listaSeg = (java.util.List) model.get("listarSeguros");
        java.util.List ldatoLab = (java.util.List) model.get("datosLab");
        java.util.List ListaLab = (java.util.List) model.get("listalab");
        java.util.List cie = (java.util.List) model.get("listaCie");
        Personas datoMed = (Personas) model.get("empleado");
        Personas datoResid = (Personas) model.get("emplResid");
        Quirofanos datoQuir = (Quirofanos) model.get("buscarquirofano");
        Camas buscarCama = (Camas) model.get("buscarCama");
        Pacientes pacientes = (Pacientes) model.get("datosp");
        java.util.List med = (java.util.List) model.get("listarRecetaTotal");
        Localidades datoestab = (Localidades) model.get("datoestab");
        Clientes dato = (Clientes) model.get("dato");
        DecimalFormat df = new DecimalFormat("###,##0");
        PdfContentByte cb = writer.getDirectContent();
        String cod = (String) model.get("cod");
        Date fecha = new Date();
        InetAddress ip;
        ip = InetAddress.getLocalHost();
        NetworkInterface network = NetworkInterface.getByInetAddress(ip);
        //byte[] mac = network.getHardwareAddress();

        Paragraph pp = new Paragraph("\n", new Font(Font.HELVETICA, 14, Font.BOLDITALIC, new Color(0, 0, 0)));
        pp.setAlignment(Element.ALIGN_CENTER);

        int[] sumas = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int NumColumns = 3;
        int una, filas = 30;

        if (HCL.size() == 0) {
            Paragraph p1 = new Paragraph("No existe Datos", new Font(Font.HELVETICA, 22, Font.BOLDITALIC, new Color(0, 0, 0)));
            document.add(p1);
        }

        if (HCL.size() % filas == 0) {
            una = 0;
        } else {
            una = 1;
        }

        PdfPCell cell;

        for (int pag = 0; pag < HCL.size() / filas + una; pag++) {
            Paragraph p1;

            for (int i = pag * filas + 0; i < pag * filas + filas && i < HCL.size(); i++) {

                Cuadernos datopac = (Cuadernos) HCL.get(i);

                PdfPTable tablex = new PdfPTable(3);
                int[] fxwidths = {40, 65, 20}; // percentage
                tablex.setWidths(fxwidths);
                tablex.setWidthPercentage(100);

                cell = new PdfPCell(new Phrase(datoestab.getNombreseg() + "\n" + dato.getEstablecimiento() + "\n\n", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
                tablex.addCell(cell);

                cell = new PdfPCell(new Phrase("\nPROTOCOLO OPERATORIO\n\n", TITULO_FONT_B));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.NO_BORDER);
                tablex.addCell(cell);

                cell = new PdfPCell(new Phrase("Form. DM. 149,\nNo.: " + datopac.getId_laboratorio(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBorder(Rectangle.NO_BORDER);
                tablex.addCell(cell);

                cell = new PdfPCell(new Phrase("   Matricula : " + pacientes.getNro_registro() + ",\n     Codigo Benef. : " + pacientes.getNro(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setColspan(3);
                tablex.addCell(cell);

                if (buscarCama != null) {
                    cell = new PdfPCell(new Phrase("Piso :  " + buscarCama.getPiso() + "   Sala :  " + buscarCama.getSala() + "    Cama :  " + buscarCama.getCama(), DATO_FONT));
                } else {
                    cell = new PdfPCell(new Phrase("Piso :  Sin" + "   Sala :  Sin" + "    Cama :  Sin", DATO_FONT));
                }
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setColspan(3);
                tablex.addCell(cell);

                cell = new PdfPCell(new Phrase("---------------------------------------------------------------------------------------------------------------------------", TITULO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setColspan(3);
                tablex.addCell(cell);

                cell = new PdfPCell(new Phrase("Nombres :  " + pacientes.getNombre() + "    Paterno :  " + pacientes.getPaterno() + "     Materno :  " + pacientes.getMaterno(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setColspan(3);
                tablex.addCell(cell);

                String seg = "";
                for (int v = 0; v < listaSeg.size(); v++) {
                    Seguros datoSeg = (Seguros) listaSeg.get(v);
                    if (datopac.getId_seguro() == datoSeg.getId_seguro()) {
                        seg = datoSeg.getSeguro();
                    }
                }

                cell = new PdfPCell(new Phrase("C.I. : " + pacientes.getCarnet() + "       HCL : " + Integer.toString(pacientes.getHcl()) + "       EDAD :  " + datopac.getEdad() + " a " + datopac.getMes() + " m " + datopac.getDia() + " d" + "         Sexo : " + datopac.getSeos() + "       Seguro : " + seg, DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setColspan(3);
                tablex.addCell(cell);

                cell = new PdfPCell(new Phrase("Direccion : " + pacientes.getDireccion() + "     Telefono : " + pacientes.getTelefono(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setColspan(3);
                tablex.addCell(cell);

                if (datopac.getNitritos().length() == 9) {
                    cell = new PdfPCell(new Phrase("Empresa :   " + datopac.getTipo() + "                   Patronal : 0" + datopac.getNitritos().substring(0, 1) + "-" + datopac.getNitritos().substring(1, 4) + "-" + datopac.getNitritos().substring(4, 9), DATO_FONT));
                }
                if (datopac.getNitritos().length() == 10) {
                    cell = new PdfPCell(new Phrase("Empresa :   " + datopac.getTipo() + "                   Patronal : " + datopac.getNitritos().substring(0, 1) + "-" + datopac.getNitritos().substring(1, 4) + "-" + datopac.getNitritos().substring(4, 9), DATO_FONT));
                }
                if (datopac.getNitritos().length() != 9 && datopac.getNitritos().length() != 10) {
                    cell = new PdfPCell(new Phrase("Empresa :   " + datopac.getTipo() + "                   Patronal :  " + datopac.getNitritos(), DATO_FONT));
                }
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setColspan(3);
                tablex.addCell(cell);

                cell = new PdfPCell(new Phrase("--------------------------------------------------------------------------------------------------------------------------------------------", TITULO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setColspan(3);
                tablex.addCell(cell);

                document.add(tablex);

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

                PdfPTable tablex3 = new PdfPTable(4);
                int[] fxwidths3 = {10, 30, 30, 30}; // percentage
                tablex3.setWidths(fxwidths3);
                tablex3.setWidthPercentage(100);

                cell = new PdfPCell(new Phrase("Quirofano :   " + datoQuir.getQuirofano() + "            Fecha Admision :    " + format(datopac.getFecha(), "dd/MM/yyyy") + "           Hora : " + format(datopac.getFecha(), "HH:mm:ss"), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setColspan(4);
                tablex3.addCell(cell);

                PdfPTable tablex3r = new PdfPTable(7);
                int[] fxwidths3r = {30, 30, 10, 30, 10, 30, 10}; // percentage
                tablex3r.setWidths(fxwidths3r);
                tablex3r.setWidthPercentage(100);

                cell = new PdfPCell(new Phrase("\nRiesgo Operatorio                                Leve:    ", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setColspan(2);
                tablex3r.addCell(cell);

                if (datopac.getSuma2() == 1) {
                    cell = new PdfPCell(new Phrase("\nX", DATO_FONT));
                } else {
                    cell = new PdfPCell(new Phrase("\n", DATO_FONT));
                }
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tablex3r.addCell(cell);

                cell = new PdfPCell(new Phrase("\nMediano:   ", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBorder(Rectangle.NO_BORDER);
                tablex3r.addCell(cell);

                if (datopac.getSuma2() == 2) {
                    cell = new PdfPCell(new Phrase("\nX", DATO_FONT));
                } else {
                    cell = new PdfPCell(new Phrase("\n", DATO_FONT));
                }
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tablex3r.addCell(cell);

                cell = new PdfPCell(new Phrase("\nGrave   :   ", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBorder(Rectangle.NO_BORDER);
                tablex3r.addCell(cell);

                if (datopac.getSuma2() == 3) {
                    cell = new PdfPCell(new Phrase("\nX", DATO_FONT));
                } else {
                    cell = new PdfPCell(new Phrase("\n", DATO_FONT));
                }
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tablex3r.addCell(cell);

                cell = new PdfPCell(tablex3r);
                //cell.setFixedHeight(210f); 
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setColspan(4);
                tablex3.addCell(cell);

                cell = new PdfPCell(new Phrase("\nDiagnostico Post-Operatorio :           " + datopac.getCadena1(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setColspan(4);
                tablex3.addCell(cell);

                cell = new PdfPCell(new Phrase("\nCirujano  :      " + datopac.getCadena2(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setColspan(2);
                tablex3.addCell(cell);

                cell = new PdfPCell(new Phrase("\nInstrumentador :      " + datopac.getCadena6(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setColspan(2);
                tablex3.addCell(cell);

                cell = new PdfPCell(new Phrase("\nAyudante 1  :      " + datopac.getCadena3(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setColspan(2);
                tablex3.addCell(cell);

                cell = new PdfPCell(new Phrase("\nCirculante :      " + datopac.getCadena7(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setColspan(2);
                tablex3.addCell(cell);

                cell = new PdfPCell(new Phrase("\nAyudante 2  :      " + datopac.getCadena4(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setColspan(2);
                tablex3.addCell(cell);

                cell = new PdfPCell(new Phrase("\nAnestesista :      " + datopac.getCadena8(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setColspan(2);
                tablex3.addCell(cell);

                cell = new PdfPCell(new Phrase("\nAyudante 3  :      " + datopac.getCadena5(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setColspan(2);
                tablex3.addCell(cell);

                cell = new PdfPCell(new Phrase("\nAyudante :      " + datopac.getCadena9(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setColspan(2);
                tablex3.addCell(cell);

                cell = new PdfPCell(new Phrase("\nMaterial de Sutura Empleado  :                 " + datopac.getCadena10(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setColspan(4);
                tablex3.addCell(cell);

                cell = new PdfPCell(new Phrase("\nNombre del Tecnico de la Operacion  :  " + datopac.getCadena18() + "             Duracion : " + datopac.getCadena19(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setColspan(4);
                tablex3.addCell(cell);

                cell = new PdfPCell(new Phrase("\nMedicacion durante la Operacion,  Sangre : " + datopac.getCadena20() + "  cc           Plasma : " + datopac.getCadena21() + "   cc", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setColspan(4);
                tablex3.addCell(cell);

                cell = new PdfPCell(new Phrase("\nSuero : " + datopac.getCadena22() + "  cc           Otras : " + datopac.getCadena23() + "   cc", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setColspan(4);
                tablex3.addCell(cell);

                if (datopac.getSuma3() == 1) {
                    cell = new PdfPCell(new Phrase("\nTipo de Anastecia :      GENERAL  (X) ", DATO_FONT3));
                }
                if (datopac.getSuma3() == 2) {
                    cell = new PdfPCell(new Phrase("\nTipo de Anastecia :      LOCAL  (X) ", DATO_FONT3));
                }
                if (datopac.getSuma3() == 3) {
                    cell = new PdfPCell(new Phrase("\nTipo de Anastecia :      REGIONAL  (X) ", DATO_FONT3));
                }
                if (datopac.getSuma3() == 4) {
                    cell = new PdfPCell(new Phrase("\nTipo de Anastecia :      RAQUIDEA  (X) ", DATO_FONT3));
                }
                if (datopac.getSuma3() == 5) {
                    cell = new PdfPCell(new Phrase("\nTipo de Anastecia :      OTRO  (X) ", DATO_FONT3));
                }
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setColspan(2);
                tablex3.addCell(cell);

                if (datopac.getSuma4() == 1) {
                    cell = new PdfPCell(new Phrase("\nInstrumental Esteril :  AL SECO  (X) ", DATO_FONT3));
                }
                if (datopac.getSuma4() == 2) {
                    cell = new PdfPCell(new Phrase("\nInstrumental Esteril :  HERVIDO  (X) ", DATO_FONT3));
                }
                if (datopac.getSuma4() == 3) {
                    cell = new PdfPCell(new Phrase("\nInstrumental Esteril :  VAPOR  (X) ", DATO_FONT3));
                }
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setColspan(1);
                tablex3.addCell(cell);

                if (datopac.getSuma5() == 1) {
                    cell = new PdfPCell(new Phrase("\nGuantes :  AL SECO  (X) ", DATO_FONT3));
                }
                if (datopac.getSuma5() == 2) {
                    cell = new PdfPCell(new Phrase("\nGuantes :  HERVIDO  (X) ", DATO_FONT3));
                }
                if (datopac.getSuma5() == 3) {
                    cell = new PdfPCell(new Phrase("\nGuantes :  VAPOR  (X) ", DATO_FONT3));
                }
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setColspan(1);
                tablex3.addCell(cell);

                cell = new PdfPCell(new Phrase("\nIncision :      " + datopac.getCadena11(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setColspan(2);
                tablex3.addCell(cell);

                cell = new PdfPCell(new Phrase("\nCierre :      " + datopac.getCadena12(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setColspan(2);
                tablex3.addCell(cell);

                cell = new PdfPCell(new Phrase("\nQuien Cerro :      " + datopac.getCadena13(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setColspan(2);
                tablex3.addCell(cell);

                cell = new PdfPCell(new Phrase("\nDrenaje :      " + datopac.getCadena14(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setColspan(2);
                tablex3.addCell(cell);

                cell = new PdfPCell(new Phrase("\nExploracio y Hallazgos :      " + datopac.getCadena15(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setColspan(4);
                tablex3.addCell(cell);

                cell = new PdfPCell(new Phrase("\nDiagnostico Pre-operatorio :      " + datopac.getCadena16(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setColspan(4);
                tablex3.addCell(cell);

                cell = new PdfPCell(new Phrase("\nDescripcion de la Opracion :      " + datopac.getCadena17(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setColspan(4);
                tablex3.addCell(cell);

                cell = new PdfPCell(new Phrase("\n\n" + datoMed.getNombres() + "\n" + datoMed.getConsultorio() + "\n" + datoMed.getCodigoprof(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setColspan(2);
                tablex3.addCell(cell);

                if (datoResid.getId_persona() != datoMed.getId_persona()) {
                    cell = new PdfPCell(new Phrase("\n\n" + datoResid.getNombres() + "\nRealizado por Resid.", DATO_FONT));
                } else {
                    cell = new PdfPCell(new Phrase("\n\n", DATO_FONT));
                }
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setColspan(2);
                tablex3.addCell(cell);

                cod = format(fecha, "dd/MM/yyyy HH:mm") + '|' + dato.getCod_esta() + '|' + dato.getId_usuario() + '|' + ip.getHostAddress() + '|' + datopac.getCod_esta() + '|' + datopac.getId_historial() + '|' + datopac.getId_historia();

                PdfContentByte cb1 = writer.getDirectContent();
                BarcodeQRCode qrcode = new BarcodeQRCode(cod, 90, 90, null);
                java.awt.Image qrImage = qrcode.createAwtImage(Color.black, Color.white);
                Image finalImage = Image.getInstance(writer, qrImage, 1);
                finalImage.setAbsolutePosition(20, 240);
                cb.addImage(finalImage, 90, 0, 0, 90, 20, 654, true);
                ///(imagen,tamañoAncho ,rotation_x ,angulorotation , amplitud-altura ,TamañoAlto ,PosicionX ,PosicionY(0=abajo, 280=arriba))

                document.add(tablex3);

                if (dato.getId_almacen() == 1) {
                    ColumnText.showTextAligned(writer.getDirectContentUnder(), Element.ALIGN_RIGHT,
                            new Paragraph("URGENCIAS", marcagua), 580, 490, 90);    ///marca de agua   x.y.rotation 
                } else {   ////////////////////////////////////,del margen, de altura
                    ColumnText.showTextAligned(writer.getDirectContentUnder(), Element.ALIGN_RIGHT,
                            new Paragraph(datopac.getBacterias().toUpperCase(), marcagua), 580, 490, 90);    ///marca de agua
                }
            }
        }
    }

    public static String format(Date dia, String formato) {
        if (dia == null) {
            return "";
        }
        SimpleDateFormat formatDate = new SimpleDateFormat(formato);
        return formatDate.format(dia);
    }
}
