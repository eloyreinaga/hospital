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
import org.ayaic.domain.Camas;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Cuadernos;
import org.ayaic.domain.Historiales;
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.Personas;

public class SolSangrePDF extends AbstractPdfView {

    private static final Font TITULO_FONT = new Font(Font.HELVETICA, 12, Font.BOLD, Color.black);
    private static final Font CABEZA_COLUMNA_FONT = new Font(Font.HELVETICA, 8, Font.ITALIC, Color.black);
    private static final Font DATO_FONT = new Font(Font.HELVETICA, 8, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_B = new Font(Font.HELVETICA, 7, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_G = new Font(Font.HELVETICA, 6, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_A = new Font(Font.COURIER, 12, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_F = new Font(Font.HELVETICA, 11, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_C = new Font(Font.HELVETICA, 9, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_D = new Font(Font.HELVETICA, 35, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_E = new Font(Font.COURIER, 15, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_BOLD = new Font(Font.HELVETICA, 8, Font.BOLD, Color.black);
    private static final Font marcagua = new Font(Font.HELVETICA, 22, Font.BOLD, Color.lightGray);
    private static final int MARGIN = 32;

    protected void buildPdfMetadata(Map model, Document document, HttpServletRequest request) {
        document.setPageSize(PageSize.LETTER.rotate());
        document.setMargins(430, 10, 20, 10);
    }

    protected void buildPdfDocument(Map model, Document document, PdfWriter writer, HttpServletRequest req, HttpServletResponse resp) throws Exception {

        java.util.List datosSangre = (java.util.List) model.get("listasolsangre");

        Historiales datoHis = (Historiales) model.get("datosHis");
        Historiales datoInterna = (Historiales) model.get("datoInterna");
        Camas datocama = (Camas) model.get("licama");
        Personas datoMed = (Personas) model.get("datosMed");
        Pacientes datoPac = (Pacientes) model.get("datosp");
        Clientes dato = (Clientes) model.get("dato");
        DecimalFormat df = new DecimalFormat("###,##0");
        PdfContentByte cb = writer.getDirectContent();
        String inter = (String) model.get("inter");
        String idhistorial = (String) model.get("idhistorial");
        String nombpac = (String) model.get("nombpac");
        String cod = (String) model.get("cod");
        String nfarma = (String) model.get("farma");
        String codmed = (String) model.get("codmed");
        InetAddress ip;
        ip = InetAddress.getLocalHost();
        NetworkInterface network = NetworkInterface.getByInetAddress(ip);
        //byte[] mac = network.getHardwareAddress();
        int fijo = 0;
        String cadena = "";

        // coloca el titulo de la pagina
        //Image escudo = Image.getInstance("/opt/imagenes/lescudo.bmp");
        Paragraph p = new Paragraph("SERVICIO TRANSFUSIONAL", new Font(Font.HELVETICA, 13, Font.BOLDITALIC, new Color(0, 0, 0)));
        p.setAlignment(Element.ALIGN_CENTER);
        //Image sumi = Image.getInstance("/opt/imagenes/hoja1.bmp");

        PdfPCell cell;

        Cuadernos datoSangre = (Cuadernos) datosSangre.get(0);

        PdfPTable tablex = new PdfPTable(4);
        int[] fxwidths = {20, 20, 75, 30}; // percentage
        tablex.setWidths(fxwidths);
        tablex.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("CAJA NACIONAL DE SALUD \n " + dato.getEstablecimiento(), DATO_FONT_B));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(2);
        tablex.addCell(cell);

        cell = new PdfPCell(p);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex.addCell(cell);

        cell = new PdfPCell(new Phrase("Form. DM-002 ", DATO_FONT_B));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(2);
        tablex.addCell(cell);

        cell = new PdfPCell(new Phrase("SOLICITUD DE SANGRE", DATO_FONT_A));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex.addCell(cell);

        cell = new PdfPCell(new Phrase("Nro.: " + datoSangre.getId_laboratorio(), DATO_FONT_F));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex.addCell(cell);

        document.add(tablex);

        PdfPTable table1 = new PdfPTable(3);
        int[] fmwidths = {60, 20, 20}; // percentage
        table1.setWidths(fmwidths);
        table1.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("Servicio: " + datoSangre.getBacterias(), DATO_FONT_F));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(1);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase(".", DATO_FONT_D));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        //cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(2);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(Rectangle.NO_BORDER);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("Codigo", DATO_FONT_B));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(2);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(Rectangle.NO_BORDER);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase(format(datoSangre.getFecha(), "dd/MM/yyyy") + "  " + format(datoSangre.getFecha(), "HH:mm"), DATO_FONT_A));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(2);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("Sello", DATO_FONT_B));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(Rectangle.NO_BORDER);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("Fecha", DATO_FONT_B));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(2);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(Rectangle.NO_BORDER);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase(datoSangre.getNro_registro() + "  -  " + datoSangre.getNro(), DATO_FONT_A));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(2);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(Rectangle.NO_BORDER);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("No Asegurado                       Cod. Benf.", DATO_FONT_B));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(2);
        table1.addCell(cell);

        if (datocama != null) {
            cell = new PdfPCell(new Phrase("CI : " + datoPac.getCarnet() + "  Telef.:" + datoPac.getTelefono() + "      Sala : " + datocama.getSala() + "   Cama : " + datocama.getCama(), DATO_FONT_C));
        } else {
            cell = new PdfPCell(new Phrase("CI : " + datoPac.getCarnet() + "  Telef.:" + datoPac.getTelefono() + "      Sin Sala : " + "   Sin Cama : ", DATO_FONT_C));
        }
        ////cell = new PdfPCell(new Phrase("CI : "+datoPac.getCarnet()+"  Telef.:"+datoPac.getTelefono() , DATO_FONT_C));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setColspan(3);
        table1.addCell(cell);

        if (datoPac.getNombres().length() > 27) {
            nombpac = datoPac.getNombres().substring(0, 27);
        } else {
            nombpac = datoPac.getNombres();
        }

        cell = new PdfPCell(new Phrase("N. " + nombpac + "  (" + (datoPac.getId_tipo_sexo() == 1 ? "F" : "M") + ") FN: " + format(datoPac.getFec_nacimiento(), "dd/MM/yy") + " (" + datoPac.getEdad() + "a" + datoPac.getMes() + "m" + datoPac.getDia() + ")", DATO_FONT_F));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setColspan(3);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("Patronal : 0" + datoSangre.getNitritos() + "   [" + datoSangre.getTipo().toLowerCase() + "]", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setColspan(2);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("Seguro : " + datoSangre.getSeguro(), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("Domicilio:  " + ((datoPac.getDireccion().length() > 40) ? datoPac.getDireccion().substring(0, 40) : datoPac.getDireccion()), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setColspan(3);
        table1.addCell(cell);

        document.add(table1);

        PdfPTable table = new PdfPTable(7);
        int[] fxxwidths = {14, 60, 12, 12, 20, 12, 12}; // percentage
        table.setWidths(fxxwidths);
        table.setWidthPercentage(100);

        //AGREGAMOS DIAGNOSTICO
        if (datoSangre.getDiagnostico() == null || "null".equals(datoSangre.getDiagnostico())) {
            datoSangre.setDiagnostico("");
        } else {

            datoSangre.setDiagnostico(datoSangre.getDiagnostico().replaceAll("<p>", " "));
            datoSangre.setDiagnostico(datoSangre.getDiagnostico().replaceAll("</p>", " "));
            datoSangre.setDiagnostico(datoSangre.getDiagnostico().replaceAll("&nbsp;", ""));
            datoSangre.setDiagnostico(datoSangre.getDiagnostico().replaceAll("<strong>", " "));
            datoSangre.setDiagnostico(datoSangre.getDiagnostico().replaceAll("</strong>", " "));
            datoSangre.setDiagnostico(datoSangre.getDiagnostico().replaceAll("<br />", " "));
            datoSangre.setDiagnostico(datoSangre.getDiagnostico().replaceAll("<u>", " "));
            datoSangre.setDiagnostico(datoSangre.getDiagnostico().replaceAll("</u>", " "));
            datoSangre.setDiagnostico(datoSangre.getDiagnostico().replaceAll("<ol>", " "));
            datoSangre.setDiagnostico(datoSangre.getDiagnostico().replaceAll("</ol>", " "));
            datoSangre.setDiagnostico(datoSangre.getDiagnostico().replaceAll("<ul>", " "));
            datoSangre.setDiagnostico(datoSangre.getDiagnostico().replaceAll("</ul>", " "));
            datoSangre.setDiagnostico(datoSangre.getDiagnostico().replaceAll("<li>", " "));
            datoSangre.setDiagnostico(datoSangre.getDiagnostico().replaceAll("</li>", " "));
            datoSangre.setDiagnostico(datoSangre.getDiagnostico().replaceAll("&ntilde;", "n"));
            datoSangre.setDiagnostico(datoSangre.getDiagnostico().replaceAll("&Ntilde;", "N"));
            datoSangre.setDiagnostico(datoSangre.getDiagnostico().replaceAll("&Aacute;", "A"));
            datoSangre.setDiagnostico(datoSangre.getDiagnostico().replaceAll("&Eacute;", "E"));
            datoSangre.setDiagnostico(datoSangre.getDiagnostico().replaceAll("&Iacute;", "I"));
            datoSangre.setDiagnostico(datoSangre.getDiagnostico().replaceAll("&Oacute;", "O"));
            datoSangre.setDiagnostico(datoSangre.getDiagnostico().replaceAll("&Uacute;", "U"));
            datoSangre.setDiagnostico(datoSangre.getDiagnostico().replaceAll("&aacute;", "a"));
            datoSangre.setDiagnostico(datoSangre.getDiagnostico().replaceAll("&eacute;", "e"));
            datoSangre.setDiagnostico(datoSangre.getDiagnostico().replaceAll("&iacute;", "i"));
            datoSangre.setDiagnostico(datoSangre.getDiagnostico().replaceAll("&oacute;", "o"));
            datoSangre.setDiagnostico(datoSangre.getDiagnostico().replaceAll("&uacute;", "u"));
            datoSangre.setDiagnostico(datoSangre.getDiagnostico().replaceAll("&quot;", "'"));
        }
        cell = new PdfPCell(new Phrase("DIAGNOSTICO  : CIE10[" + datoSangre.getCodigo() + "];  " + ((datoSangre.getDiagnostico().length() > 40) ? datoSangre.getDiagnostico().substring(0, 40) : datoSangre.getDiagnostico()), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setColspan(7);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("MOTIVO DE TRANSFUSION :  " + datoSangre.getCadena1(), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setColspan(7);
        table.addCell(cell);

        PdfPTable xtable5 = new PdfPTable(9);
        int[] xfwidths5 = {30, 15, 15, 30, 15, 15, 30, 15, 15}; // percentage
        xtable5.setWidths(xfwidths5);
        xtable5.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("Est. Realizado", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.9f);
        xtable5.addCell(cell);

        cell = new PdfPCell(new Phrase("Resul.", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.9f);
        xtable5.addCell(cell);

        cell = new PdfPCell(new Phrase("Fecha", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.9f);
        xtable5.addCell(cell);

        cell = new PdfPCell(new Phrase("Est. Realizado", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.9f);
        xtable5.addCell(cell);

        cell = new PdfPCell(new Phrase("Resul.", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.9f);
        xtable5.addCell(cell);

        cell = new PdfPCell(new Phrase("Fecha", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.9f);
        xtable5.addCell(cell);

        cell = new PdfPCell(new Phrase("Est. Realizado", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.9f);
        xtable5.addCell(cell);

        cell = new PdfPCell(new Phrase("Resul.", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.9f);
        xtable5.addCell(cell);

        cell = new PdfPCell(new Phrase("Fecha", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.9f);
        xtable5.addCell(cell);

        cell = new PdfPCell(new Phrase("Hb ", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        xtable5.addCell(cell);

        cell = new PdfPCell(new Phrase(Integer.toString(datoSangre.getSuma5()), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        xtable5.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        xtable5.addCell(cell);

        cell = new PdfPCell(new Phrase("Plaquetas", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        xtable5.addCell(cell);

        cell = new PdfPCell(new Phrase(Integer.toString(datoSangre.getSuma6()), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        xtable5.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        xtable5.addCell(cell);

        cell = new PdfPCell(new Phrase("TTPA", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        xtable5.addCell(cell);

        cell = new PdfPCell(new Phrase(Integer.toString(datoSangre.getSuma7()), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        xtable5.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        xtable5.addCell(cell);

        cell = new PdfPCell(new Phrase("Hto", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        xtable5.addCell(cell);

        cell = new PdfPCell(new Phrase(Integer.toString(datoSangre.getSuma8()), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        xtable5.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        xtable5.addCell(cell);

        cell = new PdfPCell(new Phrase("Leucocitos", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        xtable5.addCell(cell);

        cell = new PdfPCell(new Phrase(Integer.toString(datoSangre.getSuma9()), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        xtable5.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        xtable5.addCell(cell);

        cell = new PdfPCell(new Phrase("T. Protrombina", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        xtable5.addCell(cell);

        cell = new PdfPCell(new Phrase(Integer.toString(datoSangre.getSuma10()), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        xtable5.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        xtable5.addCell(cell);

        cell = new PdfPCell(xtable5);
        //cell.setFixedHeight(150);
        cell.setColspan(7);
        table.addCell(cell);

        PdfPTable xtable6 = new PdfPTable(8);
        int[] xfwidths6 = {30, 10, 40, 10, 30, 10, 30, 10}; // percentage
        xtable6.setWidths(xfwidths6);
        xtable6.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("Urgencia", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        xtable6.addCell(cell);

        cell = new PdfPCell(new Phrase((datoSangre.getSuma1() == 0) ? "no" : "SI", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        xtable6.addCell(cell);

        cell = new PdfPCell(new Phrase("En el Dia", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        xtable6.addCell(cell);

        cell = new PdfPCell(new Phrase((datoSangre.getSuma2() == 0) ? "no" : "SI", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        xtable6.addCell(cell);

        cell = new PdfPCell(new Phrase("Programado", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        xtable6.addCell(cell);

        cell = new PdfPCell(new Phrase((datoSangre.getSuma3() == 0) ? "no" : "SI", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        xtable6.addCell(cell);

        cell = new PdfPCell(new Phrase("Quirï¿½fano", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        xtable6.addCell(cell);

        cell = new PdfPCell(new Phrase((datoSangre.getSuma4() == 0) ? "no" : "SI", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        xtable6.addCell(cell);
        //////////////////////////////////////////////////////    
        cell = new PdfPCell(new Phrase("Transf. Prev.=" + datoSangre.getSuma33(), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        xtable6.addCell(cell);

        if (datoSangre.getSuma11() == 1) {
            cell = new PdfPCell(new Phrase("SI", DATO_FONT));
        } else if (datoSangre.getSuma11() == 2) {
            cell = new PdfPCell(new Phrase("no", DATO_FONT));
        } else {
            cell = new PdfPCell(new Phrase("X", DATO_FONT));
        }
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        xtable6.addCell(cell);

        cell = new PdfPCell(new Phrase("Reac. Transf. Prev.", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        xtable6.addCell(cell);

        if (datoSangre.getSuma12() == 1) {
            cell = new PdfPCell(new Phrase("SI", DATO_FONT));
        } else if (datoSangre.getSuma12() == 2) {
            cell = new PdfPCell(new Phrase("no", DATO_FONT));
        } else {
            cell = new PdfPCell(new Phrase("X", DATO_FONT));
        }
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        xtable6.addCell(cell);

        cell = new PdfPCell(new Phrase("Embarazo Act.", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        xtable6.addCell(cell);

        if (datoSangre.getSuma13() == 1) {
            cell = new PdfPCell(new Phrase("SI", DATO_FONT));
        } else if (datoSangre.getSuma13() == 2) {
            cell = new PdfPCell(new Phrase("no", DATO_FONT));
        } else {
            cell = new PdfPCell(new Phrase("X", DATO_FONT));
        }
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        xtable6.addCell(cell);

        cell = new PdfPCell(new Phrase("Antec. EHRN", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        xtable6.addCell(cell);

        if (datoSangre.getSuma14() == 1) {
            cell = new PdfPCell(new Phrase("SI", DATO_FONT));
        } else if (datoSangre.getSuma14() == 2) {
            cell = new PdfPCell(new Phrase("no", DATO_FONT));
        } else {
            cell = new PdfPCell(new Phrase("X", DATO_FONT));
        }
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        xtable6.addCell(cell);

        cell = new PdfPCell(xtable6);
        //cell.setFixedHeight(150);
        cell.setColspan(7);
        table.addCell(cell);

        PdfPTable xtable3 = new PdfPTable(3);
        int[] xfwidths3 = {80, 15, 25}; // percentage
        xtable3.setWidths(xfwidths3);
        xtable3.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("Tipo de Hemocomponente Solicitado", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.9f);
        xtable3.addCell(cell);

        cell = new PdfPCell(new Phrase("No de unid.", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.9f);
        xtable3.addCell(cell);

        cell = new PdfPCell(new Phrase("vol. transfundir", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.9f);
        xtable3.addCell(cell);

        if (datoSangre.getSuma15() != 0) {
            cell = new PdfPCell(new Phrase("Sangre Total  (ST)", DATO_FONT_E));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setFixedHeight(70);
            xtable3.addCell(cell);

            cell = new PdfPCell(new Phrase(datoSangre.getSuma15() + " u.", DATO_FONT_E));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setFixedHeight(70);
            xtable3.addCell(cell);

            cell = new PdfPCell(new Phrase(datoSangre.getSuma16() + " ml", DATO_FONT_E));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setFixedHeight(70);
            xtable3.addCell(cell);

            cell = new PdfPCell(new Phrase("\nTodo hemocomponente debe ser compatibilizada antes de transfundirse, por lo que recomendamos enviarnos una muestra del receptor (paciente) 3 ml. de sangre con anticuagulante", DATO_FONT_B));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setFixedHeight(20);
            cell.setColspan(3);
            xtable3.addCell(cell);
        }

        if (datoSangre.getSuma17() != 0) {
            cell = new PdfPCell(new Phrase("Concentrado Globulos Rojos (Paquete Globular) (PG)", DATO_FONT_E));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setFixedHeight(70);
            xtable3.addCell(cell);

            cell = new PdfPCell(new Phrase(datoSangre.getSuma17() + " u.", DATO_FONT_E));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setFixedHeight(70);
            xtable3.addCell(cell);

            cell = new PdfPCell(new Phrase(datoSangre.getSuma18() + " ml", DATO_FONT_E));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setFixedHeight(70);
            xtable3.addCell(cell);

            cell = new PdfPCell(new Phrase("\nTodo hemocomponente debe ser compatibilizada antes de transfundirse, por lo que recomendamos enviarnos una muestra del receptor (paciente) 3 ml. de sangre con anticuagulante", DATO_FONT_G));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setFixedHeight(20);
            cell.setColspan(3);
            xtable3.addCell(cell);
        }

        if (datoSangre.getSuma19() != 0) {
            cell = new PdfPCell(new Phrase("Concentrado Globulos Rojos Lavados (Paquete Globular Lavado) (PGL)", DATO_FONT_E));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setFixedHeight(70);
            xtable3.addCell(cell);

            cell = new PdfPCell(new Phrase(datoSangre.getSuma19() + " u.", DATO_FONT_E));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setFixedHeight(70);
            xtable3.addCell(cell);

            cell = new PdfPCell(new Phrase(datoSangre.getSuma20() + " ml", DATO_FONT_E));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setFixedHeight(70);
            xtable3.addCell(cell);

            cell = new PdfPCell(new Phrase("\nTodo hemocomponente debe ser compatibilizada antes de transfundirse, por lo que recomendamos enviarnos una muestra del receptor (paciente) 3 ml. de sangre con anticuagulante", DATO_FONT_G));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setFixedHeight(20);
            cell.setColspan(3);
            xtable3.addCell(cell);
        }

        if (datoSangre.getSuma21() != 0) {
            cell = new PdfPCell(new Phrase("Concentrado de Plaquetas (CP)", DATO_FONT_E));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setFixedHeight(70);
            xtable3.addCell(cell);

            cell = new PdfPCell(new Phrase(datoSangre.getSuma21() + " u.", DATO_FONT_E));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setFixedHeight(70);
            xtable3.addCell(cell);

            cell = new PdfPCell(new Phrase(datoSangre.getSuma22() + " ml", DATO_FONT_E));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setFixedHeight(70);
            xtable3.addCell(cell);

            cell = new PdfPCell(new Phrase("\nTodo hemocomponente debe ser compatibilizada antes de transfundirse, por lo que recomendamos enviarnos una muestra del receptor (paciente) 3 ml. de sangre con anticuagulante", DATO_FONT_G));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setFixedHeight(20);
            cell.setColspan(3);
            xtable3.addCell(cell);
        }

        if (datoSangre.getSuma23() != 0) {
            cell = new PdfPCell(new Phrase("Plasma Freso Congenlado (FPC)", DATO_FONT_E));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setFixedHeight(70);
            xtable3.addCell(cell);

            cell = new PdfPCell(new Phrase(datoSangre.getSuma23() + " u.", DATO_FONT_E));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setFixedHeight(70);
            xtable3.addCell(cell);

            cell = new PdfPCell(new Phrase(datoSangre.getSuma24() + " ml", DATO_FONT_E));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setFixedHeight(70);
            xtable3.addCell(cell);

            cell = new PdfPCell(new Phrase("\nTodo hemocomponente debe ser compatibilizada antes de transfundirse, por lo que recomendamos enviarnos una muestra del receptor (paciente) 3 ml. de sangre con anticuagulante", DATO_FONT_G));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setFixedHeight(20);
            cell.setColspan(3);
            xtable3.addCell(cell);
        }

        if (datoSangre.getSuma25() != 0) {
            cell = new PdfPCell(new Phrase("Plasma Refrigerado (PR)", DATO_FONT_E));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setFixedHeight(70);
            xtable3.addCell(cell);

            cell = new PdfPCell(new Phrase(datoSangre.getSuma25() + " u.", DATO_FONT_E));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setFixedHeight(70);
            xtable3.addCell(cell);

            cell = new PdfPCell(new Phrase(datoSangre.getSuma26() + " ml", DATO_FONT_E));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setFixedHeight(70);
            xtable3.addCell(cell);

            cell = new PdfPCell(new Phrase("\nTodo hemocomponente debe ser compatibilizada antes de transfundirse, por lo que recomendamos enviarnos una muestra del receptor (paciente) 3 ml. de sangre con anticuagulante", DATO_FONT_G));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setFixedHeight(20);
            cell.setColspan(3);
            xtable3.addCell(cell);
        }

        if (datoSangre.getSuma27() != 0) {
            cell = new PdfPCell(new Phrase("Crioprecipitados (CRIO)", DATO_FONT_E));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setFixedHeight(70);
            xtable3.addCell(cell);

            cell = new PdfPCell(new Phrase(datoSangre.getSuma27() + " u.", DATO_FONT_E));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setFixedHeight(70);
            xtable3.addCell(cell);

            cell = new PdfPCell(new Phrase(datoSangre.getSuma28() + " ml", DATO_FONT_E));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setFixedHeight(70);
            xtable3.addCell(cell);

            cell = new PdfPCell(new Phrase("\nTodo hemocomponente debe ser compatibilizada antes de transfundirse, por lo que recomendamos enviarnos una muestra del receptor (paciente) 3 ml. de sangre con anticuagulante", DATO_FONT_G));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setFixedHeight(20);
            cell.setColspan(3);
            xtable3.addCell(cell);
        }

        if (datoSangre.getSuma29() != 0) {
            cell = new PdfPCell(new Phrase("Autotransfusion (AU)", DATO_FONT_E));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setFixedHeight(70);
            xtable3.addCell(cell);

            cell = new PdfPCell(new Phrase(datoSangre.getSuma29() + " u.", DATO_FONT_E));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setFixedHeight(70);
            xtable3.addCell(cell);

            cell = new PdfPCell(new Phrase(datoSangre.getSuma30() + " ml", DATO_FONT_E));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setFixedHeight(70);
            xtable3.addCell(cell);

            cell = new PdfPCell(new Phrase("\nTodo hemocomponente debe ser compatibilizada antes de transfundirse, por lo que recomendamos enviarnos una muestra del receptor (paciente) 3 ml. de sangre con anticuagulante", DATO_FONT_G));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setFixedHeight(20);
            cell.setColspan(3);
            xtable3.addCell(cell);
        }

        if (datoSangre.getSuma31() != 0) {
            cell = new PdfPCell(new Phrase("Otros", DATO_FONT_E));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setFixedHeight(70);
            xtable3.addCell(cell);

            cell = new PdfPCell(new Phrase(datoSangre.getSuma31() + " u.", DATO_FONT_E));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setFixedHeight(70);
            xtable3.addCell(cell);

            cell = new PdfPCell(new Phrase(datoSangre.getSuma32() + " ml", DATO_FONT_E));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setFixedHeight(70);
            xtable3.addCell(cell);

            cell = new PdfPCell(new Phrase("\nTodo hemocomponente debe ser compatibilizada antes de transfundirse, por lo que recomendamos enviarnos una muestra del receptor (paciente) 3 ml. de sangre con anticuagulante", DATO_FONT_G));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setFixedHeight(20);
            cell.setColspan(3);
            xtable3.addCell(cell);
        }

        cell = new PdfPCell(xtable3);
        cell.setFixedHeight(110);
        cell.setColspan(7);
        table.addCell(cell);

        PdfPTable xtable = new PdfPTable(5);
        int[] xfwidths = {24, 24, 24, 21, 10}; // percentage
        xtable.setWidths(xfwidths);
        xtable.setWidthPercentage(100);

        //cell = new PdfPCell(new Phrase(datoMed.getNombres()+"\n"+datoMed.getCodigoprof()+"\nFirma Medico", DATO_FONT_G));
        cell = new PdfPCell(new Phrase(datoSangre.getNombre() + "\n" + datoSangre.getCadena4(), DATO_FONT_G));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setFixedHeight(30);
        cell.setColspan(2);
        xtable.addCell(cell);

        cell = new PdfPCell(new Phrase("Sello y firma Medico", DATO_FONT_C));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setFixedHeight(30);
        cell.setColspan(3);
        xtable.addCell(cell);

        PdfPTable xtable4 = new PdfPTable(4);
        int[] xfwidths4 = {24, 24, 24, 50}; // percentage
        xtable4.setWidths(xfwidths4);
        xtable4.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("GRUPO SANGUINEO", DATO_FONT_B));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.9f);
        xtable4.addCell(cell);

        cell = new PdfPCell(new Phrase("RH", DATO_FONT_B));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.9f);
        xtable4.addCell(cell);

        cell = new PdfPCell(new Phrase("Pruebas de Compatibilidad", DATO_FONT_B));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.9f);
        xtable4.addCell(cell);

        cell = new PdfPCell(new Phrase("Firma/ Sello\nServ. Transf.", DATO_FONT_B));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.9f);
        xtable4.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT_B));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setFixedHeight(50);
        xtable4.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT_B));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setFixedHeight(50);
        xtable4.addCell(cell);

        cell = new PdfPCell(new Phrase("Albumina\nCoombs\nBromelina\nFisiologico\nLiss", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setFixedHeight(50);
        xtable4.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT_B));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setFixedHeight(50);
        xtable4.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT_B));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setFixedHeight(50);
        xtable4.addCell(cell);

        cell = new PdfPCell(xtable4);
        cell.setColspan(5);
        xtable.addCell(cell);

        PdfPTable xtable2 = new PdfPTable(5);
        int[] xfwidths2 = {24, 24, 24, 21, 10}; // percentage
        xtable2.setWidths(xfwidths2);
        xtable2.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("-------------------------------------------------------------------------------------------------------------------------------------------------", DATO_FONT_B));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setVerticalAlignment(Element.ALIGN_TOP);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(5);
        xtable2.addCell(cell);

        if (datoSangre.getSuma15() != 0) {
            cell = new PdfPCell(new Phrase("Para el Familiar\n El paciente  " + datoSangre.getNombres() + "  requiere para su tratamiento medico / operacion quirurgica : " + "  SANGRE TOTAL (ST)" + datoSangre.getSuma15() + "unid. " + datoSangre.getSuma16() + "ml. " + " Sus familiares, amigos o personas de buena voluntad, deben donar sangre para cumplir el pedido de sus medico. Acuda para ello al Banco de Sangre de la CNS.", DATO_FONT));
        }
        if (datoSangre.getSuma17() != 0) {
            cell = new PdfPCell(new Phrase("Para el Familiar\n El paciente  " + datoSangre.getNombres() + "  requiere para su tratamiento medico / operacion quirurgica : " + "  PAQUETE GLOBULAR (PG)" + datoSangre.getSuma17() + "unid. " + datoSangre.getSuma18() + "ml. " + " Sus familiares, amigos o personas de buena voluntad, deben donar sangre para cumplir el pedido de sus medico. Acuda para ello al Banco de Sangre de la CNS.", DATO_FONT));
        }
        if (datoSangre.getSuma19() != 0) {
            cell = new PdfPCell(new Phrase("Para el Familiar\n El paciente  " + datoSangre.getNombres() + "  requiere para su tratamiento medico / operacion quirurgica : " + "  PAQUETE GLOBULAR LAVADO (PGL)" + datoSangre.getSuma19() + "unid. " + datoSangre.getSuma20() + "ml. " + " Sus familiares, amigos o personas de buena voluntad, deben donar sangre para cumplir el pedido de sus medico. Acuda para ello al Banco de Sangre de la CNS.", DATO_FONT));
        }
        if (datoSangre.getSuma21() != 0) {
            cell = new PdfPCell(new Phrase("Para el Familiar\n El paciente  " + datoSangre.getNombres() + "  requiere para su tratamiento medico / operacion quirurgica : " + "  CONCENTRADO DE PLAQUETAS (CP)" + datoSangre.getSuma21() + "unid. " + datoSangre.getSuma22() + "ml. " + " Sus familiares, amigos o personas de buena voluntad, deben donar sangre para cumplir el pedido de sus medico. Acuda para ello al Banco de Sangre de la CNS.", DATO_FONT));
        }
        if (datoSangre.getSuma23() != 0) {
            cell = new PdfPCell(new Phrase("Para el Familiar\n El paciente  " + datoSangre.getNombres() + "  requiere para su tratamiento medico / operacion quirurgica : " + "  PLASMA FRESCO CONGELADO (FPC)" + datoSangre.getSuma23() + "unid. " + datoSangre.getSuma24() + "ml. " + " Sus familiares, amigos o personas de buena voluntad, deben donar sangre para cumplir el pedido de sus medico. Acuda para ello al Banco de Sangre de la CNS.", DATO_FONT));
        }
        if (datoSangre.getSuma25() != 0) {
            cell = new PdfPCell(new Phrase("Para el Familiar\n El paciente  " + datoSangre.getNombres() + "  requiere para su tratamiento medico / operacion quirurgica : " + "  PLASMA REFRIGERADO (PR)" + datoSangre.getSuma25() + "unid. " + datoSangre.getSuma26() + "ml. " + " Sus familiares, amigos o personas de buena voluntad, deben donar sangre para cumplir el pedido de sus medico. Acuda para ello al Banco de Sangre de la CNS.", DATO_FONT));
        }
        if (datoSangre.getSuma27() != 0) {
            cell = new PdfPCell(new Phrase("Para el Familiar\n El paciente  " + datoSangre.getNombres() + "  requiere para su tratamiento medico / operacion quirurgica : " + "  CRIOPRECIPITADOS (CRIO)" + datoSangre.getSuma27() + "unid. " + datoSangre.getSuma28() + "ml. " + " Sus familiares, amigos o personas de buena voluntad, deben donar sangre para cumplir el pedido de sus medico. Acuda para ello al Banco de Sangre de la CNS.", DATO_FONT));
        }
        if (datoSangre.getSuma29() != 0) {
            cell = new PdfPCell(new Phrase("Para el Familiar\n El paciente  " + datoSangre.getNombres() + "  requiere para su tratamiento medico / operacion quirurgica : " + "  AAUTOTRANSFUSION (AU)" + datoSangre.getSuma29() + "unid. " + datoSangre.getSuma30() + "ml. " + " Sus familiares, amigos o personas de buena voluntad, deben donar sangre para cumplir el pedido de sus medico. Acuda para ello al Banco de Sangre de la CNS.", DATO_FONT));
        }
        if (datoSangre.getSuma31() != 0) {
            cell = new PdfPCell(new Phrase("Para el Familiar\n El paciente  " + datoSangre.getNombres() + "  requiere para su tratamiento medico / operacion quirurgica : " + "  OTROS" + datoSangre.getSuma31() + "unid. " + datoSangre.getSuma32() + "ml. " + " Sus familiares, amigos o personas de buena voluntad, deben donar sangre para cumplir el pedido de sus medico. Acuda para ello al Banco de Sangre de la CNS.", DATO_FONT));
        }

        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(5);
        xtable2.addCell(cell);

        cell = new PdfPCell(new Phrase("Donar sangre no hace daño\nLa sangre no se vende, se dona.             GRACIAS\n Hoy por el, maï¿½ana por Ud.\n\n\n", DATO_FONT_B));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(2);
        xtable2.addCell(cell);

        PdfPTable xtable7 = new PdfPTable(3);
        int[] xfwidths7 = {24, 24, 24}; // percentage
        xtable7.setWidths(xfwidths7);
        xtable7.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("GRUPO SANG.", DATO_FONT_G));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.9f);
        xtable7.addCell(cell);

        cell = new PdfPCell(new Phrase("RH", DATO_FONT_G));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.9f);
        xtable7.addCell(cell);

        cell = new PdfPCell(new Phrase("Firma/ Sello", DATO_FONT_G));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.9f);
        xtable7.addCell(cell);

        cell = new PdfPCell(new Phrase("\n", DATO_FONT_G));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        xtable7.addCell(cell);

        cell = new PdfPCell(new Phrase("\n", DATO_FONT_G));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        xtable7.addCell(cell);

        cell = new PdfPCell(new Phrase("\n", DATO_FONT_G));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        xtable7.addCell(cell);

        cell = new PdfPCell(xtable7);
        cell.setColspan(3);
        xtable2.addCell(cell);

        cell = new PdfPCell(xtable2);
        //cell.setFixedHeight(30);
        cell.setColspan(5);
        xtable.addCell(cell);

        cell = new PdfPCell(xtable);
        cell.setColspan(7);
        table.addCell(cell);

        document.add(table);

        cod = idhistorial + '|' + inter + '|' + format(datoSangre.getFecha(), "dd/MM/yyyy HH:mm") + '|' + datoSangre.getCod_esta() + '|' + datoSangre.getId_persona() + '|' + ip.getHostAddress();

        PdfContentByte cb1 = writer.getDirectContent();
        BarcodeQRCode qrcode = new BarcodeQRCode(cod, 90, 90, null);
        java.awt.Image qrImage = qrcode.createAwtImage(Color.black, Color.white);
        Image finalImage = Image.getInstance(writer, qrImage, 1);
        finalImage.setAbsolutePosition(20, 240);
        cb.addImage(finalImage, 80, 0, 0, 80, 425, 455, true);
        ///(imagen,tamañoAncho ,rotation_x ,angulorotation , amplitud-altura ,TamañoAlto ,PosicionX ,PosicionY(0=abajo, 280=arriba))

        if (datoInterna != null) {
            ColumnText.showTextAligned(writer.getDirectContentUnder(), Element.ALIGN_RIGHT,
                    new Paragraph(datoInterna.getCodigo(), marcagua), 780, 450, 90);    ///marca de agua    
        } else {
            ColumnText.showTextAligned(writer.getDirectContentUnder(), Element.ALIGN_RIGHT,
                    new Paragraph(datoSangre.getBacterias(), marcagua), 780, 450, 90);    ///marca de agua    
        }

    }

    public static void addfila2(PdfPTable table, String a, String b, String c) {
        PdfPCell cell;
        PdfPTable table1 = new PdfPTable(2);

        cell = new PdfPCell(new Phrase(a, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setColspan(2);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase(b, DATO_FONT_B));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase(c, DATO_FONT_B));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1.addCell(cell);

        cell = new PdfPCell(table1);
        cell.setColspan(2);
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
