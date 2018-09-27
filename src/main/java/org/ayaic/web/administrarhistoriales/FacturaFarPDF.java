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
import java.io.IOException;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Departamentos;
import org.ayaic.domain.Localidades;
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.Recetas;

public class FacturaFarPDF extends AbstractPdfView {

    private static final Font TITULO_FONT = new Font(Font.HELVETICA, 18, Font.BOLD, Color.black);
    private static final Font CABEZA_COLUMNA_FONT = new Font(Font.HELVETICA, 12, Font.ITALIC, Color.black);
    private static final Font DATO_FONT = new Font(Font.HELVETICA, 8, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_A = new Font(Font.HELVETICA, 10, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_BOLD = new Font(Font.HELVETICA, 8, Font.BOLD, Color.black);
    private static final int MARGIN = 32;

    protected void buildPdfMetadata(Map model, Document document, HttpServletRequest request) {
        document.setMargins(20, 20, 5, 20);
    }

    protected void buildPdfDocument(Map model, Document document, PdfWriter writer, HttpServletRequest req, HttpServletResponse resp) throws BadElementException, IOException, DocumentException {

        java.util.List lRubros = (java.util.List) model.get("listaRubros");
        java.util.List lCobros = (java.util.List) model.get("listaCobros");
        java.util.List estab = (java.util.List) model.get("estab");
        java.util.List costo = (java.util.List) model.get("costo");
        java.util.List listaKf = (java.util.List) model.get("listaKf");

        Localidades destab = (Localidades) estab.get(0);
        Clientes dato = (Clientes) model.get("dato");
        Pacientes persona = (Pacientes) model.get("persona");
        Departamentos departamento = (Departamentos) model.get("departamento");
        Pacientes pedido = (Pacientes) model.get("pedido");
        DecimalFormat df = new DecimalFormat("#,##0.00");
        DecimalFormat df2 = new DecimalFormat("#,##0");
        String nombreusua = (String) model.get("nombreusuario");
        String nombres = (String) model.get("nombres");
        String nombrepac = (String) model.get("nombrepac");
        String rubro = (String) model.get("rubro");
        String muchof = (String) model.get("taman");
        String id_pedido = (String) model.get("id_pedido");
        String costo1 = (String) model.get("costo1");
        String nit = (String) model.get("nit");
        String codigo = (String) model.get("codigo");
        String cc = (String) model.get("rubrocosto");
        String cod = (String) model.get("cod");
        double sumatot = (Double.parseDouble((String) model.get("totalfar")));
        double debito = 0.13 * sumatot;
        String ciu = (String) model.get("ciu");
        // coloca el titulo de la pagina
        Image escudo = Image.getInstance("/opt/imagenes/lescudo.bmp");
        Paragraph p = new Paragraph("MOVIMIENTO ECONOMICO\n", new Font(Font.HELVETICA, 14, Font.BOLDITALIC, new Color(0, 0, 0)));
        p.setAlignment(Element.ALIGN_CENTER);

        PdfPCell cell;

        PdfPTable tablex = new PdfPTable(3);
        int[] fxwidths = {50, 40, 40}; // percentage
        tablex.setWidths(fxwidths);
        tablex.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase(destab.getNombrefact() + "\nCasa Matriz", DATO_FONT_A));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex.addCell(cell);

        cell = new PdfPCell(new Phrase("FACTURA", TITULO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex.addCell(cell);

        cell = new PdfPCell(new Phrase("NIT                      :  " + destab.getNit(), DATO_FONT_A));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
        cell.setGrayFill(0.9f);
        tablex.addCell(cell);

        cell = new PdfPCell(new Phrase(destab.getDireccion(), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex.addCell(cell);

        cell = new PdfPCell(new Phrase("ORIGINAL", DATO_FONT_A));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex.addCell(cell);

        cell = new PdfPCell(new Phrase("Factura No           :  " + destab.getNum_fact(), DATO_FONT_A));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setGrayFill(0.9f);
        tablex.addCell(cell);

        cell = new PdfPCell(new Phrase("Telefono : " + destab.getTele1(), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex.addCell(cell);

        cod = destab.getNit() + '|' + destab.getNum_fact() + '|' + destab.getNum_auto() + '|' + format(new Date(), "dd/MM/yyyy") + '|' + sumatot + '|' + df.format(debito) + '|' + codigo + '|' + ciu + '|' + '0' + '|' + '0' + '|' + '0' + '|' + '0';

        PdfContentByte cb = writer.getDirectContent();
        BarcodeQRCode qrcode = new BarcodeQRCode(cod, 90, 90, null);
        java.awt.Image qrImage = qrcode.createAwtImage(Color.black, Color.white);
        Image finalImage = Image.getInstance(writer, qrImage, 1);
        finalImage.setAbsolutePosition(497, 398);
        finalImage.setBorder(Rectangle.NO_BORDER);
        document.add(finalImage);

        cell = new PdfPCell(new Phrase("", DATO_FONT_A));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex.addCell(cell);

        cell = new PdfPCell(new Phrase("No Autorizacion    :  " + destab.getNum_auto(), DATO_FONT_A));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setGrayFill(0.9f);
        tablex.addCell(cell);

        cell = new PdfPCell(new Phrase("Fax : " + destab.getTele2(), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(2);
        tablex.addCell(cell);

        cell = new PdfPCell(new Phrase("SERVICIOS Y/O ACTIVIDADES SUJETAS AL IVA", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex.addCell(cell);

        cell = new PdfPCell(new Phrase(destab.getDepartamento() + " - Bolivia", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex.addCell(cell);

        if ("01".equals(format(new Date(), "MM"))) {
            cell = new PdfPCell(new Phrase(destab.getLocalidad() + " : " + format(new Date(), "dd") + "  de  enero  del  " + format(new Date(), "yyyy"), DATO_FONT_A));
        }
        if ("02".equals(format(new Date(), "MM"))) {
            cell = new PdfPCell(new Phrase(destab.getLocalidad() + " : " + format(new Date(), "dd") + "  de  febrero  del  " + format(new Date(), "yyyy"), DATO_FONT_A));
        }
        if ("03".equals(format(new Date(), "MM"))) {
            cell = new PdfPCell(new Phrase(destab.getLocalidad() + " : " + format(new Date(), "dd") + "  de  marzo  del  " + format(new Date(), "yyyy"), DATO_FONT_A));
        }
        if ("04".equals(format(new Date(), "MM"))) {
            cell = new PdfPCell(new Phrase(destab.getLocalidad() + " : " + format(new Date(), "dd") + "  de  abril  del  " + format(new Date(), "yyyy"), DATO_FONT_A));
        }
        if ("05".equals(format(new Date(), "MM"))) {
            cell = new PdfPCell(new Phrase(destab.getLocalidad() + " : " + format(new Date(), "dd") + "  de  mayo  del  " + format(new Date(), "yyyy"), DATO_FONT_A));
        }
        if ("06".equals(format(new Date(), "MM"))) {
            cell = new PdfPCell(new Phrase(destab.getLocalidad() + " : " + format(new Date(), "dd") + "  de  junio  del  " + format(new Date(), "yyyy"), DATO_FONT_A));
        }
        if ("07".equals(format(new Date(), "MM"))) {
            cell = new PdfPCell(new Phrase(destab.getLocalidad() + " : " + format(new Date(), "dd") + "  de  julio  del  " + format(new Date(), "yyyy"), DATO_FONT_A));
        }
        if ("08".equals(format(new Date(), "MM"))) {
            cell = new PdfPCell(new Phrase(destab.getLocalidad() + " : " + format(new Date(), "dd") + "  de  agosto  del  " + format(new Date(), "yyyy"), DATO_FONT_A));
        }
        if ("09".equals(format(new Date(), "MM"))) {
            cell = new PdfPCell(new Phrase(destab.getLocalidad() + " : " + format(new Date(), "dd") + "  de  septiembre  del  " + format(new Date(), "yyyy"), DATO_FONT_A));
        }
        if ("10".equals(format(new Date(), "MM"))) {
            cell = new PdfPCell(new Phrase(destab.getLocalidad() + " : " + format(new Date(), "dd") + "  de  octubre  del  " + format(new Date(), "yyyy"), DATO_FONT_A));
        }
        if ("11".equals(format(new Date(), "MM"))) {
            cell = new PdfPCell(new Phrase(destab.getLocalidad() + " : " + format(new Date(), "dd") + "  de  noviembre  del  " + format(new Date(), "yyyy"), DATO_FONT_A));
        }
        if ("12".equals(format(new Date(), "MM"))) {
            cell = new PdfPCell(new Phrase(destab.getLocalidad() + " : " + format(new Date(), "dd") + "  de  diciembre  del  " + format(new Date(), "yyyy"), DATO_FONT_A));
        }
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(2);
        tablex.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(3);
        tablex.addCell(cell);

        document.add(tablex);

        PdfPTable table1 = new PdfPTable(2);
        int[] fmwidths = {50, 50}; // percentage
        table1.setWidths(fmwidths);
        table1.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("Nombre : " + nombreusua, DATO_FONT_A));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("NIT/CI Cliente: " + ciu, DATO_FONT_A));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(Rectangle.NO_BORDER);
        table1.addCell(cell);

        Calendar calendar = Calendar.getInstance();
        int hora24 = calendar.get(Calendar.HOUR_OF_DAY);
        int minutos = calendar.get(Calendar.MINUTE);
        int segundos = calendar.get(Calendar.SECOND);
        int milisegundos = calendar.get(Calendar.MILLISECOND);

        cell = new PdfPCell(new Phrase("Por concepto de : ", new Font(Font.HELVETICA, 7, Font.NORMAL, new Color(0, 0, 0))));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("Usua : " + dato.getId_usuario() + "     H : " + hora24 + ":" + minutos + ":" + segundos, new Font(Font.HELVETICA, 6, Font.NORMAL, new Color(0, 0, 0))));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setBorder(Rectangle.NO_BORDER);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("Paciente : " + nombrepac, new Font(Font.HELVETICA, 6, Font.NORMAL, new Color(0, 0, 0))));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(2);
        table1.addCell(cell);

        document.add(table1);

        int NumColumns = 4;
        PdfPTable table = new PdfPTable(NumColumns);
        int[] fwidths = {4, 60, 8, 8};
        table.setWidths(fwidths);
        table.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("Cant.", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.9f);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("DETALLE", CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.9f);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("P/ Unit.", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.9f);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("TOTAL", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.9f);
        table.addCell(cell);

        //llena los espacios del detalle de la factura 
        PdfPTable table11 = new PdfPTable(4);
        int[] fmwidthss = {4, 60, 8, 8};  // percentage
        table11.setWidths(fmwidthss);
        table11.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("", DATO_FONT_A));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase("FARMACIA  (Medicamentos)", DATO_FONT_A));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.BOTTOM);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT_A));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(2);
        table11.addCell(cell);

        if ("mucho".equals(muchof)) {

            cell = new PdfPCell(new Phrase("", DATO_FONT_A));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            table11.addCell(cell);

            if (!"1".equals(rubro)) {
                cell = new PdfPCell(new Phrase("VENTA DE MEDICAMENTOS", DATO_FONT_A));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setColspan(2);
                table11.addCell(cell);
            } else {
                cell = new PdfPCell(new Phrase("VENTA DE VARIOS MEDICAMENTOS", DATO_FONT_A));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setColspan(2);
                table11.addCell(cell);
            }

            cell = new PdfPCell(new Phrase(df.format(sumatot), CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            table11.addCell(cell);
        } else {
            for (int j = 0; j < listaKf.size(); j++) {
                Recetas datoc = (Recetas) listaKf.get(j);

                cell = new PdfPCell(new Phrase(df2.format(datoc.getSalida()), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.NO_BORDER);
                table11.addCell(cell);

                cell = new PdfPCell(new Phrase("  " + datoc.getMedicamento() + "   [" + datoc.getForma_far() + "]   " + datoc.getConcentra(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
                table11.addCell(cell);

                cell = new PdfPCell(new Phrase(df.format(datoc.getPrecio_venta()), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.NO_BORDER);
                table11.addCell(cell);

                cell = new PdfPCell(new Phrase(df.format(datoc.getPrecio_venta() * datoc.getSalida()), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.NO_BORDER);
                table11.addCell(cell);
            }

        }///para else muchos medcamentos

        cell = new PdfPCell(table11);
        cell.setFixedHeight(210f);
        cell.setColspan(4);
        table.addCell(cell);

        ///fin detalle de factura    
        cell = new PdfPCell(new Phrase("TOTAL  Bs.", DATO_FONT_A));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setColspan(3);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(df.format(sumatot), CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        document.add(table);

        n2t numero = new n2t();

        p = new Paragraph("Son  : " + numero.convertirLetras((int) sumatot) + "    " + Integer.toString((int) (Math.round(((sumatot - (int) sumatot) * 100)))) + "/100  Bolivianos", new Font(Font.HELVETICA, 10, Font.BOLD, new Color(0, 0, 0)));
        document.add(p);

        p = new Paragraph("CODIGO DE CONTROL  :  " + codigo, DATO_FONT_BOLD);
        p.setAlignment(Element.ALIGN_LEFT);
        document.add(p);

        p = new Paragraph("FECHA LIMITE DE EMISION  : " + format(destab.getFecha(), "dd/MM/yyyy"), DATO_FONT_BOLD);
        p.setAlignment(Element.ALIGN_LEFT);
        document.add(p);

        p = new Paragraph("ESTA FACTURA CONTRIBUYE AL DESARROLLO DEL PAÍS. EL USO ILÍCITO DE ÉSTA SERÁ SANCIONADO DE ACUERDO A LEY", new Font(Font.HELVETICA, 8, Font.NORMAL, new Color(0, 0, 0)));
        document.add(p);

        p = new Paragraph("Ley No 453: El Proveedor debera dar cumplimineto a las condiciones ofertadas.", new Font(Font.HELVETICA, 6, Font.NORMAL, new Color(0, 0, 0)));
        document.add(p);

        ///////////////factura copia   
        document.newPage();

        PdfPTable tablex2 = new PdfPTable(3);
        int[] fxwidths2 = {50, 40, 40}; // percentage
        tablex2.setWidths(fxwidths2);
        tablex2.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase(destab.getNombrefact() + "\nCasa Matriz", DATO_FONT_A));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex2.addCell(cell);

        cell = new PdfPCell(new Phrase("FACTURA", TITULO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex2.addCell(cell);

        cell = new PdfPCell(new Phrase("NIT                      :  " + destab.getNit(), DATO_FONT_A));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
        cell.setGrayFill(0.9f);
        tablex2.addCell(cell);

        cell = new PdfPCell(new Phrase(destab.getDireccion(), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex2.addCell(cell);

        cell = new PdfPCell(new Phrase("COPIA", DATO_FONT_A));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex2.addCell(cell);

        cell = new PdfPCell(new Phrase("Factura No           :  " + destab.getNum_fact(), DATO_FONT_A));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setGrayFill(0.9f);
        tablex2.addCell(cell);

        cell = new PdfPCell(new Phrase("Telefono : " + destab.getTele1(), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex2.addCell(cell);

        finalImage.setAbsolutePosition(497, 398);
        finalImage.setBorder(Rectangle.NO_BORDER);
        document.add(finalImage);

        cell = new PdfPCell(new Phrase("", DATO_FONT_A));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex2.addCell(cell);

        cell = new PdfPCell(new Phrase("No Autorizacion    :  " + destab.getNum_auto(), DATO_FONT_A));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setGrayFill(0.9f);
        tablex2.addCell(cell);

        cell = new PdfPCell(new Phrase("Fax : " + destab.getTele2(), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(2);
        tablex2.addCell(cell);

        cell = new PdfPCell(new Phrase("SERVICIOS Y/O ACTIVIDADES SUJETAS AL IVA", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex2.addCell(cell);

        cell = new PdfPCell(new Phrase(destab.getDepartamento() + " - Bolivia", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex2.addCell(cell);

        if ("01".equals(format(new Date(), "MM"))) {
            cell = new PdfPCell(new Phrase(destab.getLocalidad() + " : " + format(new Date(), "dd") + "  de  enero  del  " + format(new Date(), "yyyy"), DATO_FONT_A));
        }
        if ("02".equals(format(new Date(), "MM"))) {
            cell = new PdfPCell(new Phrase(destab.getLocalidad() + " : " + format(new Date(), "dd") + "  de  febrero  del  " + format(new Date(), "yyyy"), DATO_FONT_A));
        }
        if ("03".equals(format(new Date(), "MM"))) {
            cell = new PdfPCell(new Phrase(destab.getLocalidad() + " : " + format(new Date(), "dd") + "  de  marzo  del  " + format(new Date(), "yyyy"), DATO_FONT_A));
        }
        if ("04".equals(format(new Date(), "MM"))) {
            cell = new PdfPCell(new Phrase(destab.getLocalidad() + " : " + format(new Date(), "dd") + "  de  abril  del  " + format(new Date(), "yyyy"), DATO_FONT_A));
        }
        if ("05".equals(format(new Date(), "MM"))) {
            cell = new PdfPCell(new Phrase(destab.getLocalidad() + " : " + format(new Date(), "dd") + "  de  mayo  del  " + format(new Date(), "yyyy"), DATO_FONT_A));
        }
        if ("06".equals(format(new Date(), "MM"))) {
            cell = new PdfPCell(new Phrase(destab.getLocalidad() + " : " + format(new Date(), "dd") + "  de  junio  del  " + format(new Date(), "yyyy"), DATO_FONT_A));
        }
        if ("07".equals(format(new Date(), "MM"))) {
            cell = new PdfPCell(new Phrase(destab.getLocalidad() + " : " + format(new Date(), "dd") + "  de  julio  del  " + format(new Date(), "yyyy"), DATO_FONT_A));
        }
        if ("08".equals(format(new Date(), "MM"))) {
            cell = new PdfPCell(new Phrase(destab.getLocalidad() + " : " + format(new Date(), "dd") + "  de  agosto  del  " + format(new Date(), "yyyy"), DATO_FONT_A));
        }
        if ("09".equals(format(new Date(), "MM"))) {
            cell = new PdfPCell(new Phrase(destab.getLocalidad() + " : " + format(new Date(), "dd") + "  de  septiembre  del  " + format(new Date(), "yyyy"), DATO_FONT_A));
        }
        if ("10".equals(format(new Date(), "MM"))) {
            cell = new PdfPCell(new Phrase(destab.getLocalidad() + " : " + format(new Date(), "dd") + "  de  octubre  del  " + format(new Date(), "yyyy"), DATO_FONT_A));
        }
        if ("11".equals(format(new Date(), "MM"))) {
            cell = new PdfPCell(new Phrase(destab.getLocalidad() + " : " + format(new Date(), "dd") + "  de  noviembre  del  " + format(new Date(), "yyyy"), DATO_FONT_A));
        }
        if ("12".equals(format(new Date(), "MM"))) {
            cell = new PdfPCell(new Phrase(destab.getLocalidad() + " : " + format(new Date(), "dd") + "  de  diciembre  del  " + format(new Date(), "yyyy"), DATO_FONT_A));
        }
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(2);
        tablex2.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(3);
        tablex2.addCell(cell);

        document.add(tablex2);

        PdfPTable table12 = new PdfPTable(2);
        int[] fmwidths2 = {50, 50}; // percentage
        table12.setWidths(fmwidths2);
        table12.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("Nombre : " + nombreusua, DATO_FONT_A));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        table12.addCell(cell);

        cell = new PdfPCell(new Phrase("NIT/CI : " + ciu, DATO_FONT_A));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(Rectangle.NO_BORDER);
        table12.addCell(cell);

        cell = new PdfPCell(new Phrase("Por concepto de : ", new Font(Font.HELVETICA, 7, Font.NORMAL, new Color(0, 0, 0))));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        table12.addCell(cell);

        cell = new PdfPCell(new Phrase("Usua : " + dato.getId_usuario() + "     H : " + hora24 + ":" + minutos + ":" + segundos, new Font(Font.HELVETICA, 6, Font.NORMAL, new Color(0, 0, 0))));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setBorder(Rectangle.NO_BORDER);
        table12.addCell(cell);

        cell = new PdfPCell(new Phrase("Paciente : " + nombrepac, new Font(Font.HELVETICA, 6, Font.NORMAL, new Color(0, 0, 0))));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(2);
        table12.addCell(cell);

        document.add(table12);

        int NumColumns2 = 4;
        PdfPTable table2 = new PdfPTable(NumColumns2);
        int[] fwidths2 = {4, 60, 8, 8};
        table2.setWidths(fwidths2);
        table2.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("Cant.", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.9f);
        table2.addCell(cell);

        cell = new PdfPCell(new Phrase("DETALLE", CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.9f);
        table2.addCell(cell);

        cell = new PdfPCell(new Phrase("P/ Unit.", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.9f);
        table2.addCell(cell);

        cell = new PdfPCell(new Phrase("TOTAL", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.9f);
        table2.addCell(cell);

        //llena los espacios del detalle de la factura 
        PdfPTable table112 = new PdfPTable(4);
        int[] fmwidthss2 = {4, 60, 8, 8};  // percentage
        table112.setWidths(fmwidthss2);
        table112.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("", DATO_FONT_A));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        table112.addCell(cell);

        cell = new PdfPCell(new Phrase("FARMACIA  (Medicamentos)", DATO_FONT_A));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.BOTTOM);
        table112.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT_A));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(2);
        table112.addCell(cell);

        if ("mucho".equals(muchof)) {

            cell = new PdfPCell(new Phrase("", DATO_FONT_A));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            table112.addCell(cell);

            if (!"1".equals(rubro)) {
                cell = new PdfPCell(new Phrase("VENTA DE MEDICAMENTOS", DATO_FONT_A));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setColspan(2);
                table112.addCell(cell);
            } else {
                cell = new PdfPCell(new Phrase("VENTA DE VARIOS MEDICAMENTOS", DATO_FONT_A));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setColspan(2);
                table112.addCell(cell);
            }

            cell = new PdfPCell(new Phrase(df.format(sumatot), CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            table112.addCell(cell);
        } else {

            for (int j = 0; j < listaKf.size(); j++) {
                Recetas datoc = (Recetas) listaKf.get(j);

                cell = new PdfPCell(new Phrase(df2.format(datoc.getSalida()), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.NO_BORDER);
                table112.addCell(cell);

                cell = new PdfPCell(new Phrase("  " + datoc.getMedicamento() + "   [" + datoc.getForma_far() + "]   " + datoc.getConcentra(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
                table112.addCell(cell);

                cell = new PdfPCell(new Phrase(df.format(datoc.getPrecio_venta()), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.NO_BORDER);
                table112.addCell(cell);

                cell = new PdfPCell(new Phrase(df.format(datoc.getPrecio_venta() * datoc.getSalida()), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.NO_BORDER);
                table112.addCell(cell);
            }

        }///para else muchos medcamentos
        cell = new PdfPCell(table112);
        cell.setFixedHeight(210f);
        cell.setColspan(4);
        table2.addCell(cell);

        ///fin detalle de factura    
        cell = new PdfPCell(new Phrase("TOTAL  Bs.", DATO_FONT_A));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setColspan(3);
        table2.addCell(cell);

        cell = new PdfPCell(new Phrase(df.format(sumatot), CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table2.addCell(cell);

        document.add(table2);

        p = new Paragraph("Son  : " + numero.convertirLetras((int) sumatot) + "    " + Integer.toString((int) (Math.round(((sumatot - (int) sumatot) * 100)))) + "/100  Bolivianos", new Font(Font.HELVETICA, 10, Font.BOLD, new Color(0, 0, 0)));
        document.add(p);

        p = new Paragraph("CODIGO DE CONTROL  :  " + codigo, DATO_FONT_BOLD);
        p.setAlignment(Element.ALIGN_LEFT);
        document.add(p);

        p = new Paragraph("FECHA LIMITE DE EMISION  : " + format(destab.getFecha(), "dd/MM/yyyy"), DATO_FONT_BOLD);
        p.setAlignment(Element.ALIGN_LEFT);
        document.add(p);

        p = new Paragraph("ESTA FACTURA CONTRIBUYE AL DESARROLLO DEL PAÍS. EL USO ILÍCITO DE ÉSTA SERÁ SANCIONADO DE ACUERDO A LEY", new Font(Font.HELVETICA, 8, Font.NORMAL, new Color(0, 0, 0)));
        document.add(p);

        p = new Paragraph("Ley No 453: El Proveedor debera dar cumplimineto a las condiciones ofertadas.", new Font(Font.HELVETICA, 6, Font.NORMAL, new Color(0, 0, 0)));
        document.add(p);

    }

    public static String format(Date dia, String formato) {
        if (dia == null) {
            return "";
        }
        SimpleDateFormat formatDate = new SimpleDateFormat(formato);
        return formatDate.format(dia);
    }

}
