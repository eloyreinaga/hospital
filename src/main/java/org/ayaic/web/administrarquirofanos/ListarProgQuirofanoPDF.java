package org.ayaic.web.administrarquirofanos;

import java.awt.Color;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.view.document.AbstractPdfView;
import com.itextpdf.text.pdf.BarcodeQRCode;
import java.util.*;
import java.text.*;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Historiales;
import org.ayaic.domain.Localidades;
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.Personas;
import org.ayaic.domain.Quirofanos;

public class ListarProgQuirofanoPDF extends AbstractPdfView {

    private static final Font TITULO_FONT = new Font(Font.HELVETICA, 11, Font.NORMAL, Color.black);
    private static final Font TITULO_FONT_B = new Font(Font.HELVETICA, 16, Font.BOLD, Color.black);
    private static final Font CABEZA_COLUMNA_FONT = new Font(Font.HELVETICA, 8, Font.ITALIC, Color.black);
    private static final Font DATO_FONT = new Font(Font.HELVETICA, 8, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_BOLD = new Font(Font.COURIER, 8, Font.BOLD, Color.black);
    private static final int MARGIN = 32;

    protected void buildPdfMetadata(Map model, Document document, HttpServletRequest request) {
        document.setPageSize(PageSize.LETTER);
        document.setMargins(20, 20, 20, 10);
    }

    protected void buildPdfDocument(Map model, Document document, PdfWriter writer, HttpServletRequest req, HttpServletResponse resp) throws Exception {

        java.util.List listaQuiro = (java.util.List) model.get("listarQuirofanos");
        Pacientes pacientes = (Pacientes) model.get("datosp");
        Personas ayucirujano = (Personas) model.get("ayucirujano");
        Personas anastecista = (Personas) model.get("anastecista");
        Personas ayuanastecista = (Personas) model.get("ayuanastecista");
        Historiales histointer = (Historiales) model.get("historia");
        Localidades datoestab = (Localidades) model.get("datoestab");
        java.util.List med = (java.util.List) model.get("listarRecetaTotal");
        Clientes dato = (Clientes) model.get("dato");
        String cod = (String) model.get("cod");
        DecimalFormat df = new DecimalFormat("###,##0");

        PdfPCell cell;

        Quirofanos quiro = (Quirofanos) listaQuiro.get(0);

        PdfPTable tablex = new PdfPTable(3);
        int[] fxwidths = {50, 25, 25}; // percentage
        tablex.setWidths(fxwidths);
        tablex.setWidthPercentage(100);

        //cell = new PdfPCell(new Phrase(datoestab.getNombreseg()+"\n", DATO_FONT));
        cell = new PdfPCell(new Phrase(datoestab.getNombreseg() + "\n" + datoestab.getEstablecimiento() + "\n", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex.addCell(cell);

        cell = new PdfPCell(new Phrase("Form. DM 155", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(2);
        tablex.addCell(cell);

        cell = new PdfPCell(new Phrase("\n", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex.addCell(cell);

        cell = new PdfPCell(new Phrase("No de asegurado:", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex.addCell(cell);

        cell = new PdfPCell(new Phrase(pacientes.getNro_registro(), TITULO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tablex.addCell(cell);

        cell = new PdfPCell(new Phrase("SOLICITUD DE QUIROFANO", TITULO_FONT_B));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex.addCell(cell);

        cell = new PdfPCell(new Phrase("Codigo\nAsegurado", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex.addCell(cell);

        cell = new PdfPCell(new Phrase(pacientes.getNro(), TITULO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tablex.addCell(cell);

        cell = new PdfPCell(new Phrase(" \n\n", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(1);
        tablex.addCell(cell);

        cell = new PdfPCell(new Phrase("Patronal : " + quiro.getCadena11() + "   [" + quiro.getCadena10().toLowerCase() + "]", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(2);
        tablex.addCell(cell);

        cod = format(new Date(), "dd/MM/yyyy HH:mm") + '|' + pacientes.getId_paciente() + '|' + quiro.getId_historial() + '|' + quiro.getId_quirofano();

        PdfContentByte cb = writer.getDirectContent();
        BarcodeQRCode qrcode = new BarcodeQRCode(cod, 90, 90, null);
        java.awt.Image qrImage = qrcode.createAwtImage(Color.black, Color.white);
        Image finalImage = Image.getInstance(writer, qrImage, 1);
        finalImage.setAbsolutePosition(260, 685);  ///x, y
        finalImage.setBorder(Rectangle.NO_BORDER);
        //qrImage.scalePercent(400);
        //cb.addImage(finalImage);
        document.add(finalImage);

        cell = new PdfPCell(new Phrase("Nombres y Apellidos : " + pacientes.getNombres(), TITULO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        //cell.setBorder(Rectangle.NO_BORDER);
        tablex.addCell(cell);

        if (pacientes.getId_tipo_sexo() == 1) {
            cell = new PdfPCell(new Phrase("Edad : " + Integer.toString(pacientes.getEdad()) + " a " + Integer.toString(pacientes.getMes()) + " m " + Integer.toString(pacientes.getDia()) + " d        Sexo : Femenino", TITULO_FONT));
        } else {
            cell = new PdfPCell(new Phrase("Edad : " + Integer.toString(pacientes.getEdad()) + " a " + Integer.toString(pacientes.getMes()) + " m " + Integer.toString(pacientes.getDia()) + " d        Sexo : Masculino", TITULO_FONT));
        }
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        //cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(2);
        tablex.addCell(cell);

        if (histointer == null) {
            cell = new PdfPCell(new Phrase("Servicio : " + "   Piso :      " + "     Cama :      " + "     Sala :      ", TITULO_FONT));
        } else {
            cell = new PdfPCell(new Phrase("Servicio : " + "   Piso :    3 " + "     Cama :   " + histointer.getCodigo() + "     Sala :   " + histointer.getAccion(), TITULO_FONT));
        }
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        //cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(3);
        tablex.addCell(cell);

        if (quiro.getCadena14() == null || "null".equals(quiro.getCadena14())) {
            quiro.setCadena14("");
        } else {
            quiro.setCadena14(quiro.getCadena14().replaceAll("<p>", " "));
            quiro.setCadena14(quiro.getCadena14().replaceAll("</p>", " "));
            quiro.setCadena14(quiro.getCadena14().replaceAll("&nbsp;", ""));
            quiro.setCadena14(quiro.getCadena14().replaceAll("<strong>", " "));
            quiro.setCadena14(quiro.getCadena14().replaceAll("</strong>", " "));
            quiro.setCadena14(quiro.getCadena14().replaceAll("<br />", " "));
            quiro.setCadena14(quiro.getCadena14().replaceAll("<u>", " "));
            quiro.setCadena14(quiro.getCadena14().replaceAll("</u>", " "));
            quiro.setCadena14(quiro.getCadena14().replaceAll("<ul>", " "));
            quiro.setCadena14(quiro.getCadena14().replaceAll("</ul>", " "));
            quiro.setCadena14(quiro.getCadena14().replaceAll("<ol>", " "));
            quiro.setCadena14(quiro.getCadena14().replaceAll("</ol>", " "));
            quiro.setCadena14(quiro.getCadena14().replaceAll("<li>", " "));
            quiro.setCadena14(quiro.getCadena14().replaceAll("</li>", " "));
            quiro.setCadena14(quiro.getCadena14().replaceAll("&ordf;", " "));
            quiro.setCadena14(quiro.getCadena14().replaceAll("&ntilde;", "n"));
            quiro.setCadena14(quiro.getCadena14().replaceAll("&Ntilde;", "N"));
            quiro.setCadena14(quiro.getCadena14().replaceAll("&Aacute;", "A"));
            quiro.setCadena14(quiro.getCadena14().replaceAll("&Eacute;", "E"));
            quiro.setCadena14(quiro.getCadena14().replaceAll("&Iacute;", "I"));
            quiro.setCadena14(quiro.getCadena14().replaceAll("&Oacute;", "O"));
            quiro.setCadena14(quiro.getCadena14().replaceAll("&Uacute;", "U"));
            quiro.setCadena14(quiro.getCadena14().replaceAll("&aacute;", "a"));
            quiro.setCadena14(quiro.getCadena14().replaceAll("&eacute;", "e"));
            quiro.setCadena14(quiro.getCadena14().replaceAll("&iacute;", "i"));
            quiro.setCadena14(quiro.getCadena14().replaceAll("&oacute;", "o"));
            quiro.setCadena14(quiro.getCadena14().replaceAll("&uacute;", "u"));
            quiro.setCadena14(quiro.getCadena14().replaceAll("&quot;", "'"));
        }

        cell = new PdfPCell(new Phrase("Diagnostico   :   " + quiro.getCadena14(), TITULO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        //cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(3);
        tablex.addCell(cell);

        cell = new PdfPCell(new Phrase("Cirugia   :   " + quiro.getCadena3(), TITULO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        //cell.setBorder(Rectangle.NO_BORDER);
        tablex.addCell(cell);

        cell = new PdfPCell(new Phrase("Quirofano   :   " + quiro.getQuirofano(), TITULO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        //cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(2);
        tablex.addCell(cell);

        cell = new PdfPCell(new Phrase("Cirujano   :   " + quiro.getCadena2(), TITULO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        //cell.setBorder(Rectangle.NO_BORDER);
        tablex.addCell(cell);

        cell = new PdfPCell(new Phrase("Ayudante Cirujano   :   " + ayucirujano.getNombres() + " " + ayucirujano.getPaterno(), TITULO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        //cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(2);
        tablex.addCell(cell);

        cell = new PdfPCell(new Phrase("Anastecista   :   " + anastecista.getNombres() + " " + anastecista.getPaterno(), TITULO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        //cell.setBorder(Rectangle.NO_BORDER);
        tablex.addCell(cell);

        cell = new PdfPCell(new Phrase("Ayudante   :   " + ayuanastecista.getNombres() + " " + ayuanastecista.getPaterno(), TITULO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        //cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(2);
        tablex.addCell(cell);

        cell = new PdfPCell(new Phrase("Pulso   :   " + quiro.getCadena5() + "         Temperatura   :   " + quiro.getCadena6() + "           Presion Art.   :   " + quiro.getCadena7() + "          Corazon   :   " + quiro.getCadena8() + "            Pulmones   :   " + quiro.getCadena9(), TITULO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        //cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(3);
        tablex.addCell(cell);

        cell = new PdfPCell(new Phrase("Observaciones   :   " + quiro.getCadena4(), TITULO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        //cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(3);
        tablex.addCell(cell);

        cell = new PdfPCell(new Phrase("Fecha  Propuesta\npara Operacion       :    " + format(quiro.getFechap(), "dd/MM/yyyy"), TITULO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex.addCell(cell);

        cell = new PdfPCell(new Phrase("Hora  Propuesta\npara Operacion       :    " + format(quiro.getFechap(), "HH:mm"), TITULO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(2);
        tablex.addCell(cell);

        cell = new PdfPCell(new Phrase(" \n\n\nFIRMA DEL SOLICITANTE", TITULO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(3);
        tablex.addCell(cell);

        cell = new PdfPCell(new Phrase("Fecha Realizacon Solicitud de Quirofano : " + format(quiro.getFecha(), "dd/MM/yyyy HH:mm") + "\nPara ser atendida esta solicitud debe formularse por lo menos 1 dia antes de la fecha propuesta entregandose a la \n Enfermera Jefe de Quirofano hasta horas 11 a.m. \n La enfermera Jefe de Quirofanos debe conformar de inmediato al Cirujano solicitante o en ausencia de este a la  \n Enfermera Jefe de Planta del Servicio correspondiente, el dia y la hora de operacion", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(3);
        tablex.addCell(cell);

        document.add(tablex);

    }

    public static String format(Date dia, String formato) {
        if (dia == null) {
            return "";
        }
        SimpleDateFormat formatDate = new SimpleDateFormat(formato);
        return formatDate.format(dia);
    }
}
