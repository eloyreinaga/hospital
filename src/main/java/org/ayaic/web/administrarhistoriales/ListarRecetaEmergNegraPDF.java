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
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Historiales;
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.Personas;
import org.ayaic.domain.Recetas;
import org.ayaic.domain.Seguros;

public class ListarRecetaEmergNegraPDF extends AbstractPdfView {

    private static final Font TITULO_FONT = new Font(Font.HELVETICA, 12, Font.BOLD, Color.black);
    private static final Font CABEZA_COLUMNA_FONT = new Font(Font.HELVETICA, 8, Font.ITALIC, Color.black);
    private static final Font DATO_FONT = new Font(Font.HELVETICA, 8, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_B = new Font(Font.HELVETICA, 7, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_A = new Font(Font.COURIER, 12, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_G = new Font(Font.HELVETICA, 6, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_F = new Font(Font.HELVETICA, 10, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_C = new Font(Font.HELVETICA, 9, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_D = new Font(Font.HELVETICA, 45, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_E = new Font(Font.COURIER, 19, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_BOLD = new Font(Font.HELVETICA, 8, Font.BOLD, Color.black);
    private static final Font marcagua = new Font(Font.HELVETICA, 22, Font.BOLD, Color.lightGray);
    private static final int MARGIN = 32;

    protected void buildPdfMetadata(Map model, Document document, HttpServletRequest request) {
        document.setPageSize(PageSize.LETTER.rotate());
        document.setMargins(440, 10, 20, 10);
    }

    protected void buildPdfDocument(Map model, Document document, PdfWriter writer, HttpServletRequest req, HttpServletResponse resp) throws Exception {

        java.util.List listaRecetas = (java.util.List) model.get("listarRecetas");
        java.util.List listaPres = (java.util.List) model.get("listarPres");
        java.util.List listaSeg = (java.util.List) model.get("listarSeguros");
        java.util.List datosPacEmp = (java.util.List) model.get("datosPacEmp");

        Historiales datoHis = (Historiales) model.get("datosHis");
        Historiales datoInterna = (Historiales) model.get("datoInterna");
        Personas datoMed = (Personas) model.get("datosMed");
        Pacientes datoPac = (Pacientes) model.get("datosPac");
        Clientes dato = (Clientes) model.get("dato");
        DecimalFormat df = new DecimalFormat("###,##0");
        PdfContentByte cb = writer.getDirectContent();
        String inter = (String) model.get("inter");
        String idhistorial = (String) model.get("idhistorial");
        String numerec = (String) model.get("numreceta");
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
        Date Fechact = new Date();

        int una;
        if (listaRecetas.size() % 8 == 0) {
            una = 0;
        } else {
            una = 1;
        }

        Pacientes datoPacEmp = (Pacientes) datosPacEmp.get(0);

        for (int pag = 0; pag < listaRecetas.size() / 8 + una; pag++) {

            Paragraph p = new Paragraph("RECETARIO", new Font(Font.HELVETICA, 14, Font.BOLDITALIC, new Color(0, 0, 0)));
            p.setAlignment(Element.ALIGN_CENTER);

            PdfPCell cell;

            PdfPTable tablex = new PdfPTable(4);
            int[] fxwidths = {20, 35, 70, 35}; // percentage
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

            cell = new PdfPCell(new Phrase("Form. DM-301-E \n ID: " + numerec, DATO_FONT_B));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setBorder(Rectangle.NO_BORDER);
            tablex.addCell(cell);

            cell = new PdfPCell(new Phrase("", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(2);
            tablex.addCell(cell);

            cell = new PdfPCell(new Phrase("RECETA MULTIPLE", DATO_FONT_A));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            tablex.addCell(cell);

            cell = new PdfPCell(new Phrase("Folio:             .", DATO_FONT_F));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setBorder(Rectangle.NO_BORDER);
            tablex.addCell(cell);

            document.add(tablex);

            PdfPTable table1 = new PdfPTable(3);
            int[] fmwidths = {60, 20, 20}; // percentage
            table1.setWidths(fmwidths);
            table1.setWidthPercentage(100);

            cell = new PdfPCell(new Phrase(datoMed.getConsultorio(), DATO_FONT_E));
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

            cell = new PdfPCell(new Phrase("Sello Farmaceutico", DATO_FONT_B));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(2);
            table1.addCell(cell);

            cell = new PdfPCell(new Phrase("", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell);

            cell = new PdfPCell(new Phrase(format(new Date(), "dd/MM/yyyy") + "  " + format(new Date(), "HH:mm"), DATO_FONT_A));
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

            cell = new PdfPCell(new Phrase(datoPac.getNro_registro() + "       " + datoPac.getNro(), DATO_FONT_A));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(2);
            table1.addCell(cell);

            cell = new PdfPCell(new Phrase("", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell);

            cell = new PdfPCell(new Phrase("No Asegurado (" + nfarma + ")              Cod. Benf.", DATO_FONT_B));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(2);
            table1.addCell(cell);

            if (datoInterna != null) {
                cell = new PdfPCell(new Phrase("CI : " + datoPac.getCarnet() + "  Telef.:" + datoPac.getTelefono() + "      Sala : " + datoInterna.getCodigo() + "   Cama : " + datoInterna.getAccion(), DATO_FONT_C));
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

            cell = new PdfPCell(new Phrase("N.: " + nombpac + "  (" + (datoPac.getId_tipo_sexo() == 1 ? "F" : "M") + ") Nac." + format(datoPac.getFec_nacimiento(), "dd/MM/yy") + " (" + datoPac.getEdad() + "a" + datoPac.getMes() + "m" + datoPac.getDia() + ")", DATO_FONT_F));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(3);
            table1.addCell(cell);

            cell = new PdfPCell(new Phrase("Patronal : " + datoPacEmp.getRegistro() + "   [" + datoPacEmp.getResultado().toLowerCase() + "]", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(2);
            table1.addCell(cell);

            if ("P".equals(datoHis.getExpedido())) {
                for (int v = 0; v < listaSeg.size(); v++) {
                    Seguros datoSeg = (Seguros) listaSeg.get(v);
                    if (datoHis.getId_seguro() == datoSeg.getId_seguro()) {
                        cell = new PdfPCell(new Phrase("Prog.: " + datoSeg.getSeguro(), DATO_FONT));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table1.addCell(cell);
                    }
                }
            } else {
                if ("S".equals(datoHis.getExpedido())) {
                    cell = new PdfPCell(new Phrase("Ley475", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table1.addCell(cell);
                } else {
                    cell = new PdfPCell(new Phrase("Externo", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table1.addCell(cell);
                }
            }

            cell = new PdfPCell(new Phrase("Domicilio: " + ((datoPac.getDireccion().length() > 40) ? datoPac.getDireccion().substring(0, 40) : datoPac.getDireccion()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(3);
            table1.addCell(cell);

            document.add(table1);

            PdfPTable table = new PdfPTable(7);
            int[] fxxwidths = {14, 60, 12, 9, 40, 12, 12}; // percentage
            table.setWidths(fxxwidths);
            table.setWidthPercentage(100);

            //AGREGAMOS DIAGNOSTICO
            if (datoHis.getDiagnostico() == null || "null".equals(datoHis.getDiagnostico())) {
                datoHis.setDiagnostico("");
            } else {

                datoHis.setDiagnostico(datoHis.getDiagnostico().replaceAll("<p>", " "));
                datoHis.setDiagnostico(datoHis.getDiagnostico().replaceAll("</p>", " "));
                datoHis.setDiagnostico(datoHis.getDiagnostico().replaceAll("&nbsp;", ""));
                datoHis.setDiagnostico(datoHis.getDiagnostico().replaceAll("<strong>", " "));
                datoHis.setDiagnostico(datoHis.getDiagnostico().replaceAll("</strong>", " "));
                datoHis.setDiagnostico(datoHis.getDiagnostico().replaceAll("<br />", " "));
                datoHis.setDiagnostico(datoHis.getDiagnostico().replaceAll("<u>", " "));
                datoHis.setDiagnostico(datoHis.getDiagnostico().replaceAll("</u>", " "));
                datoHis.setDiagnostico(datoHis.getDiagnostico().replaceAll("<ol>", " "));
                datoHis.setDiagnostico(datoHis.getDiagnostico().replaceAll("</ol>", " "));
                datoHis.setDiagnostico(datoHis.getDiagnostico().replaceAll("<ul>", " "));
                datoHis.setDiagnostico(datoHis.getDiagnostico().replaceAll("</ul>", " "));
                datoHis.setDiagnostico(datoHis.getDiagnostico().replaceAll("<li>", " "));
                datoHis.setDiagnostico(datoHis.getDiagnostico().replaceAll("</li>", " "));
                datoHis.setDiagnostico(datoHis.getDiagnostico().replaceAll("&ntilde;", "n"));
                datoHis.setDiagnostico(datoHis.getDiagnostico().replaceAll("&Ntilde;", "N"));
                datoHis.setDiagnostico(datoHis.getDiagnostico().replaceAll("&Aacute;", "A"));
                datoHis.setDiagnostico(datoHis.getDiagnostico().replaceAll("&Eacute;", "E"));
                datoHis.setDiagnostico(datoHis.getDiagnostico().replaceAll("&Iacute;", "I"));
                datoHis.setDiagnostico(datoHis.getDiagnostico().replaceAll("&Oacute;", "O"));
                datoHis.setDiagnostico(datoHis.getDiagnostico().replaceAll("&Uacute;", "U"));
                datoHis.setDiagnostico(datoHis.getDiagnostico().replaceAll("&aacute;", "a"));
                datoHis.setDiagnostico(datoHis.getDiagnostico().replaceAll("&eacute;", "e"));
                datoHis.setDiagnostico(datoHis.getDiagnostico().replaceAll("&iacute;", "i"));
                datoHis.setDiagnostico(datoHis.getDiagnostico().replaceAll("&oacute;", "o"));
                datoHis.setDiagnostico(datoHis.getDiagnostico().replaceAll("&uacute;", "u"));
                datoHis.setDiagnostico(datoHis.getDiagnostico().replaceAll("&quot;", "'"));
            }
            cell = new PdfPCell(new Phrase("DIAGNOSTICO  : CIE10[" + datoHis.getCodigo() + "];  " + ((datoHis.getDiagnostico().length() > 35) ? datoHis.getDiagnostico().substring(0, 35) : datoHis.getDiagnostico()), DATO_FONT));
            cell.setColspan(7);
            table.addCell(cell);
            //AGREGAMOS PRESTACIONES
            table1 = new PdfPTable(1);
            table1.setWidthPercentage(100);

            cell = new PdfPCell(table1);
            cell.setColspan(7);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Cod.", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setGrayFill(0.9f);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("MEDICAMENTO", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setGrayFill(0.9f);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Unid.", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setGrayFill(0.9f);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("TD\ndia", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setGrayFill(0.9f);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("INDICACION", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setGrayFill(0.9f);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Cant.", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setGrayFill(0.9f);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Cant.\n Disp.", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setGrayFill(0.9f);
            table.addCell(cell);

            codmed = "";
            cadena = "";
            // coloca el detalle de loS datos
            for (int i = pag * 8 + 0; i < pag * 8 + 8 && i < listaRecetas.size(); i++) {

                Recetas datoReceta = (Recetas) listaRecetas.get(i);

                nombpac = ((datoReceta.getMedicamento().length() > 22) ? datoReceta.getMedicamento().substring(0, 22) : datoReceta.getMedicamento()) + "<" + datoReceta.getForma_far() + ">" + "(" + datoReceta.getId_farmacia() + ")";
                if (nombpac.length() > 45) {
                    nombpac = nombpac.substring(0, 45);
                }

                if (datoInterna != null) {
                    // if(datoReceta.getId_historia()==Integer.parseInt(inter)){
                    cell = new PdfPCell(new Phrase(datoReceta.getCodsumi(), DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase(nombpac, DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setColspan(2);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase(df.format(datoReceta.getDosifica()), DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase(((datoReceta.getIndicacion().length() > 17) ? datoReceta.getIndicacion().substring(0, 17) : datoReceta.getIndicacion()), DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase(df.format(datoReceta.getSalida()), DATO_FONT_A));
                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase("", DATO_FONT_A));
                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    table.addCell(cell);

                    codmed = datoReceta.getId_medicamento() + "(" + df.format(datoReceta.getSalida()) + ")" + "|" + codmed;
                    cadena = cadena + "* " + df.format(datoReceta.getSalida()) + "_" + datoReceta.getMedicamento() + "_" + datoReceta.getIndicacion() + "\n";
                    // }
                } else {
                    cell = new PdfPCell(new Phrase(datoReceta.getCodsumi(), DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase(nombpac, DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setColspan(2);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase(df.format(datoReceta.getDosifica()), DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase(((datoReceta.getIndicacion().length() > 17) ? datoReceta.getIndicacion().substring(0, 17) : datoReceta.getIndicacion()), DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase(df.format(datoReceta.getSalida()), DATO_FONT_A));
                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase("", DATO_FONT_A));
                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    table.addCell(cell);

                    codmed = datoReceta.getId_medicamento() + "(" + df.format(datoReceta.getSalida()) + ")" + "|" + codmed;
                    cadena = cadena + "* " + df.format(datoReceta.getSalida()) + "_" + datoReceta.getMedicamento() + "_" + datoReceta.getIndicacion() + "\n";
                }

            }

            PdfPTable xtable = new PdfPTable(5);
            int[] xfwidths = {24, 24, 24, 21, 10}; // percentage
            xtable.setWidths(xfwidths);
            xtable.setWidthPercentage(100);

            cell = new PdfPCell(new Phrase("COSTO TOTAL", DATO_FONT));
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setColspan(4);
            xtable.addCell(cell);

            cell = new PdfPCell(new Phrase("", DATO_FONT_A));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            xtable.addCell(cell);

            switch (listaRecetas.size()) {
                case 1:
                    fijo = 180;
                    break;
                case 2:
                    fijo = 170;
                    break;
                case 3:
                    fijo = 160;
                    break;
                case 4:
                    fijo = 150;
                    break;
                case 5:
                    fijo = 130;
                    break;
                case 6:
                    fijo = 110;
                    break;
                case 7:
                    fijo = 90;
                    break;
                default:
                    fijo = 90;
                    break;
            }

            cell = new PdfPCell(new Phrase(datoMed.getNombres() + "\n" + datoMed.getCodigoprof() + "\nFirma Medico", DATO_FONT_G));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setFixedHeight(fijo);
            cell.setColspan(2);
            xtable.addCell(cell);

            cell = new PdfPCell(new Phrase("\nRecetado Por:\nSello y firma", DATO_FONT_C));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setFixedHeight(fijo);
            xtable.addCell(cell);

            cell = new PdfPCell(new Phrase("\nEnfermera de Sala:\nSello y firma", DATO_FONT_C));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setFixedHeight(fijo);
            cell.setColspan(2);
            xtable.addCell(cell);

            cell = new PdfPCell(new Phrase("\n", DATO_FONT_C));
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(5);
            xtable.addCell(cell);

            cell = new PdfPCell(new Phrase("\nIndicacion : \n" + cadena, DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
            //cell.setBorder(Rectangle.NO_BORDER);
            cell.setFixedHeight(100);
            cell.setColspan(5);
            xtable.addCell(cell);

            cell = new PdfPCell(xtable);
            cell.setColspan(7);
            table.addCell(cell);

            document.add(table);

            cod = idhistorial + '|' + inter + '|' + format(datoHis.getFecha(), "dd/MM/yyyy HH:mm") + '|' + dato.getCod_esta() + '|' + dato.getId_usuario() + '|' + codmed + '|' + ip.getHostAddress();

            PdfContentByte cb1 = writer.getDirectContent();
            BarcodeQRCode qrcode = new BarcodeQRCode(cod, 90, 90, null);
            java.awt.Image qrImage = qrcode.createAwtImage(Color.black, Color.white);
            Image finalImage = Image.getInstance(writer, qrImage, 1);
            finalImage.setAbsolutePosition(20, 240);
            cb.addImage(finalImage, 90, 0, 0, 90, 425, 445, true);
            ///(imagen,tamañoAncho ,rotation_x ,angulorotation , amplitud-altura ,TamañoAlto ,PosicionX ,PosicionY(0=abajo, 280=arriba))

            if (datoInterna != null) {
                ColumnText.showTextAligned(writer.getDirectContentUnder(), Element.ALIGN_RIGHT,
                        new Paragraph(datoInterna.getCodigo(), marcagua), 780, 450, 90);    ///marca de agua    
            } else {
                ColumnText.showTextAligned(writer.getDirectContentUnder(), Element.ALIGN_RIGHT,
                        new Paragraph(datoMed.getConsultorio(), marcagua), 780, 450, 90);    ///marca de agua    
            }

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
