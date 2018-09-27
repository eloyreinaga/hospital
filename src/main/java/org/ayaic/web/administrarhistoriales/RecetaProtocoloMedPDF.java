package org.ayaic.web.administrarhistoriales;

import com.itextpdf.text.pdf.BarcodeQRCode;
import java.awt.Color;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.view.document.AbstractPdfView;
import java.util.*;
import java.text.*;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Historiales;
import org.ayaic.domain.Localidades;
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.Personas;
import org.ayaic.domain.Recetas;
import org.ayaic.domain.Seguros;

public class RecetaProtocoloMedPDF extends AbstractPdfView {

    private static final Font TITULO_FONT = new Font(Font.HELVETICA, 11, Font.BOLD, Color.black);
    private static final Font CABEZA_COLUMNA_FONT = new Font(Font.HELVETICA, 8, Font.ITALIC, Color.black);
    private static final Font DATO_FONT = new Font(Font.HELVETICA, 7, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_C = new Font(Font.HELVETICA, 9, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_D = new Font(Font.HELVETICA, 18, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_B = new Font(Font.HELVETICA, 7, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_A = new Font(Font.COURIER, 10, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_BOLD = new Font(Font.HELVETICA, 8, Font.BOLD, Color.black);
    private static final Font marcagua = new Font(Font.HELVETICA, 26, Font.BOLD, Color.lightGray);
    private static final int MARGIN = 32;

    protected void buildPdfMetadata(Map model, Document document, HttpServletRequest request) {
        document.setPageSize(PageSize.LETTER.rotate());
        document.setMargins(450, 20, 20, 10);
    }

    protected void buildPdfDocument(Map model, Document document, PdfWriter writer, HttpServletRequest req, HttpServletResponse resp) throws Exception {
        java.util.List listaRecetas = (java.util.List) model.get("listarRecetaCaro");
        java.util.List listaPres = (java.util.List) model.get("listarPres");
        java.util.List listaSeg = (java.util.List) model.get("listarSeguros");
        java.util.List datosPacEmp = (java.util.List) model.get("datosPacEmp");

        Historiales datoHis = (Historiales) model.get("datosHis");
        Localidades datoestab = (Localidades) model.get("datoestab");
        Personas datoMed = (Personas) model.get("datosMed");
        Pacientes datoPac = (Pacientes) model.get("datosPac");
        Clientes dato = (Clientes) model.get("dato");
        String cod = (String) model.get("cod");
        DecimalFormat df = new DecimalFormat("#,##0.00");
        DecimalFormat df2 = new DecimalFormat("#,##0");
        Image escudo = Image.getInstance("/opt/imagenes/lescudo.bmp");

        PdfPCell cell;

        Pacientes datoPacEmp = (Pacientes) datosPacEmp.get(0);

        for (int i = 0; i < listaRecetas.size(); i++) {

            Recetas datoReceta = (Recetas) listaRecetas.get(i);

            PdfPTable tablex = new PdfPTable(4);
            int[] fxwidths = {15, 35, 15, 35}; // percentage
            tablex.setWidths(fxwidths);
            tablex.setWidthPercentage(100);

            cell = new PdfPCell(escudo);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            tablex.addCell(cell);

            cell = new PdfPCell(new Phrase("CAJA NACIONAL SALUD", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(1);
            tablex.addCell(cell);

            cell = new PdfPCell(new Phrase(dato.getEstablecimiento() + "", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(2);
            tablex.addCell(cell);

            cell = new PdfPCell(new Phrase("PROTOCOLO PRESCRIPCION DE MEDICAMENTOS", TITULO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(4);
            tablex.addCell(cell);

            cell = new PdfPCell(new Phrase("(Documento que acompaï¿½a a la receta)", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(4);
            tablex.addCell(cell);

            if (datoReceta.getId_medicamento() == 1006) {
                cell = new PdfPCell(new Phrase("\n\nIMIPEMEN", TITULO_FONT));
            }
            if (datoReceta.getId_medicamento() == 445) {
                cell = new PdfPCell(new Phrase("\n\nVANCOMICINA", TITULO_FONT));
            }
            if (datoReceta.getId_medicamento() == 25) {
                cell = new PdfPCell(new Phrase("\n\nALBUMINA HUMANA", TITULO_FONT));
            }
            if (datoReceta.getId_medicamento() == 1025) {
                cell = new PdfPCell(new Phrase("\n\nINMUNOGLOBULINA", TITULO_FONT));
            }
            if (datoReceta.getId_medicamento() == 1312) {
                cell = new PdfPCell(new Phrase("\n\nENOXAPARINA", TITULO_FONT));
            }

            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(2);
            tablex.addCell(cell);

            cell = new PdfPCell(new Phrase("\n\nX", TITULO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            tablex.addCell(cell);

            cod = Integer.toString(datoHis.getCod_esta()) + '|' + format(new Date(), "dd/MM/yyyy HH:mm:ss") + '|' + datoHis.getId_historial() + '|' + datoReceta.getId_medicamento() + '|' + datoReceta.getSalida() + '|' + datoReceta.getNum_cladoc();

            PdfContentByte cb = writer.getDirectContent();
            BarcodeQRCode qrcode = new BarcodeQRCode(cod, 90, 90, null);
            java.awt.Image qrImage = qrcode.createAwtImage(Color.black, Color.white);
            Image finalImage = Image.getInstance(writer, qrImage, 1);

            cell = new PdfPCell(finalImage);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setBorder(Rectangle.NO_BORDER);
            tablex.addCell(cell);

            cell = new PdfPCell(new Phrase("Paciente : " + datoPac.getNombres(), TITULO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(4);
            tablex.addCell(cell);

            if (datoPac.getNro_registro().length() > 6) {
                cell = new PdfPCell(new Phrase("Matricula : " + datoPac.getNro_registro().substring(0, 6) + " - " + datoPac.getNro_registro().substring(6, 9) + "      Cod :  " + datoPac.getNro(), TITULO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setColspan(3);
                tablex.addCell(cell);
            } else {
                cell = new PdfPCell(new Phrase("Matricula : " + datoPac.getNro_registro() + "      Cod :  " + datoPac.getNro(), TITULO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setColspan(3);
                tablex.addCell(cell);
            }

            if ("P".equals(datoHis.getExpedido())) {
                for (int v = 0; v < listaSeg.size(); v++) {
                    Seguros datoSeg = (Seguros) listaSeg.get(v);
                    if (datoHis.getId_seguro() == datoSeg.getId_seguro()) {
                        cell = new PdfPCell(new Phrase("Seguro: " + datoSeg.getSeguro(), DATO_FONT));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        tablex.addCell(cell);
                    }
                }
            } else {
                if ("S".equals(datoHis.getExpedido())) {
                    cell = new PdfPCell(new Phrase("Seguro: Ley475 (Sumi)", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    tablex.addCell(cell);
                } else {
                    cell = new PdfPCell(new Phrase("Seguro: Externo", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    tablex.addCell(cell);
                }
            }

            String direcc = "";

            if (datoPac.getDireccion().length() > 33) {
                direcc = datoPac.getDireccion().substring(0, 33).toLowerCase();
            } else {
                direcc = datoPac.getDireccion().toLowerCase();
            }

            cell = new PdfPCell(new Phrase("CI : " + datoPac.getCarnet() + " " + datoPac.getExpedido() + "   Telef.:" + datoPac.getTelefono() + ";   Direccion : " + direcc, DATO_FONT_C));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setFixedHeight(30f);
            cell.setColspan(4);
            tablex.addCell(cell);

            cell = new PdfPCell(new Phrase("Patronal : " + datoPacEmp.getRegistro() + " [" + ((datoPacEmp.getResultado().length() > 28) ? datoPacEmp.getResultado().substring(0, 28) : datoPacEmp.getResultado()) + "]", DATO_FONT_A));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setFixedHeight(30f);
            cell.setColspan(4);
            tablex.addCell(cell);

            cell = new PdfPCell(new Phrase("Servicio de: " + dato.getConsultorio() + "  Fecha : " + format(new Date(), "dd/MM/yyyy"), DATO_FONT_A));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setFixedHeight(15f);
            cell.setColspan(4);
            tablex.addCell(cell);

            cell = new PdfPCell(new Phrase("Edad: " + datoHis.getEdad() + "       Peso : " + datoHis.getPeso(), DATO_FONT_A));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setFixedHeight(15f);
            cell.setColspan(4);
            tablex.addCell(cell);

            cell = new PdfPCell(new Phrase("\n\n", TITULO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(4);
            tablex.addCell(cell);///////////esta es la pagina blanca

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

            cell = new PdfPCell(new Phrase("DIAGNOSTICO  : CIE[" + datoHis.getCodigo() + "];  " + datoHis.getDiagnostico(), DATO_FONT_A));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setFixedHeight(60f);
            cell.setColspan(4);
            tablex.addCell(cell);

            cell = new PdfPCell(new Phrase("Dosis a utilizar : " + df2.format(datoReceta.getSalida()) + "  unid.   {" + datoReceta.getIndicacion() + "}", DATO_FONT_A));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setFixedHeight(40f);
            cell.setColspan(4);
            tablex.addCell(cell);

            cell = new PdfPCell(new Phrase("Duracion del tratamiento : " + datoReceta.getDosifica() + "     dias ", DATO_FONT_A));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setFixedHeight(40f);
            cell.setColspan(4);
            tablex.addCell(cell);

            cell = new PdfPCell(new Phrase("Observaciones  :  " + datoHis.getAccion(), DATO_FONT_A));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setFixedHeight(30f);
            cell.setColspan(4);
            tablex.addCell(cell);

            cell = new PdfPCell(new Phrase("\n\n\n", TITULO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(4);
            tablex.addCell(cell);///////////esta es la pagina blanca

            cell = new PdfPCell(new Phrase("Firma Medico\n" + datoMed.getNombres() + "\nClave:" + datoMed.getCodigoprof(), TITULO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setFixedHeight(50f);
            cell.setColspan(2);
            tablex.addCell(cell);

            cell = new PdfPCell(new Phrase("* Vancomicina, Imipemen y Amoxi.+ Sulbactam adjuntar fotocopia del Cultivo (Antibiograma)", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setFixedHeight(40f);
            cell.setColspan(4);
            tablex.addCell(cell);

            document.add(tablex);

            if (dato.getId_almacen() == 1 || dato.getId_especialidad() == 99) {
                ColumnText.showTextAligned(writer.getDirectContentUnder(), Element.ALIGN_RIGHT,
                        new Paragraph("EMERGENCIAS", marcagua), 460, 390, 90);    ///marca de agua   x.y.rotation 
            } else {   ////////////////////////////////////,del margen, de altura
                ColumnText.showTextAligned(writer.getDirectContentUnder(), Element.ALIGN_RIGHT,
                        new Paragraph(dato.getConsultorio().toUpperCase(), marcagua), 460, 390, 90);    ///marca de agua
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
