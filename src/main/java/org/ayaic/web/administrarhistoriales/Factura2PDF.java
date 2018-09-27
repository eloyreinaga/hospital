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
import org.ayaic.domain.Departamentos;
import org.ayaic.domain.Detalle;
import org.ayaic.domain.Localidades;
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.Recetas;

public class Factura2PDF extends AbstractPdfView {

    private static final Font TITULO_FONT = new Font(Font.HELVETICA, 18, Font.BOLD, Color.black);
    private static final Font CABEZA_COLUMNA_FONT = new Font(Font.HELVETICA, 12, Font.ITALIC, Color.black);
    private static final Font DATO_FONT = new Font(Font.HELVETICA, 8, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_A = new Font(Font.HELVETICA, 10, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_BOLD = new Font(Font.HELVETICA, 8, Font.BOLD, Color.black);
    private static final int MARGIN = 32;

    protected void buildPdfMetadata(Map model, Document document, HttpServletRequest request) {
        document.setMargins(20, 20, 5, 20);
    }

    protected void buildPdfDocument(Map model, Document document, PdfWriter writer, HttpServletRequest req, HttpServletResponse resp) throws Exception {

        java.util.List lRubros = (java.util.List) model.get("listaRubros");
        java.util.List lCobros = (java.util.List) model.get("listaCobros");
        java.util.List estab = (java.util.List) model.get("estab");
        java.util.List costo = (java.util.List) model.get("costo");
        java.util.List listaKf = (java.util.List) model.get("listaKf");
        java.util.List Otros = (java.util.List) model.get("listaCobrosOtros");
        java.util.List detalleCostos = (java.util.List) model.get("listarCostosT");
        java.util.List farmacias = (java.util.List) model.get("listaCobrosFar");

        Localidades destab = (Localidades) estab.get(0);
        Clientes dato = (Clientes) model.get("dato");
        Pacientes persona = (Pacientes) model.get("persona");
        Departamentos departamento = (Departamentos) model.get("departamento");
        Pacientes pedido = (Pacientes) model.get("pedido");
        DecimalFormat df = new DecimalFormat("#,##0.00");
        DecimalFormat df2 = new DecimalFormat("#,##0");
        String nombreusua = (String) model.get("nombreusuario");
        String nombrepac = (String) model.get("nombrepac");
        String numfact2 = (String) model.get("numfact2");
        String rubro = (String) model.get("rubro");
        String muchof = (String) model.get("taman");
        String id_pedido = (String) model.get("id_pedido");
        String costo1 = (String) model.get("costo1");
        String nit = (String) model.get("nit");
        String codigo1 = (String) model.get("codigo1");
        String codigo2 = (String) model.get("codigo2");
        String cc = (String) model.get("rubrocosto");
        String totfar = (String) model.get("totalfar");
        String total2 = (String) model.get("total2");
        String cod = (String) model.get("cod");
        String cod2 = (String) model.get("cod");
        double sumatot1 = (Double.parseDouble((String) model.get("valortotal1")));
        double sumatot2 = (Double.parseDouble((String) model.get("valortotal2")));
        double debito1 = 0.13 * sumatot1;
        double debito2 = 0.13 * sumatot2;
        String ciu = (String) model.get("ciu");
        // coloca el titulo de la pagina
        Image escudo = Image.getInstance("/opt/imagenes/lescudo.bmp");
        Paragraph p = new Paragraph("MOVIMIENTO ECONOMICO\n", new Font(Font.HELVETICA, 14, Font.BOLDITALIC, new Color(0, 0, 0)));
        p.setAlignment(Element.ALIGN_CENTER);

        PdfPCell cell;

        PdfPTable tablex = new PdfPTable(3);
        int[] fxwidths = {50, 40, 45}; // percentage
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

        cod = destab.getNit() + '|' + destab.getNum_fact() + '|' + destab.getNum_auto() + '|' + format(new Date(), "dd/MM/yyyy") + '|' + sumatot1 + '|' + df.format(debito1) + '|' + codigo1 + '|' + ciu + '|' + '0' + '|' + '0' + '|' + '0' + '|' + '0';

        PdfContentByte cb = writer.getDirectContent();
        BarcodeQRCode qrcode = new BarcodeQRCode(cod, 90, 90, null);
        java.awt.Image qrImage = qrcode.createAwtImage(Color.black, Color.white);
        Image finalImage = Image.getInstance(writer, qrImage, 1);
        finalImage.setAbsolutePosition(497, 396);
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
        PdfPTable table11c = new PdfPTable(4);
        int[] fmwidthss = {4, 60, 8, 8};  // percentage
        table11c.setWidths(fmwidthss);
        table11c.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("", DATO_FONT_A));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        table11c.addCell(cell);

        cell = new PdfPCell(new Phrase("FARMACIA  (Medicamentos)", DATO_FONT_A));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.BOTTOM);
        table11c.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT_A));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(2);
        table11c.addCell(cell);

        if ("mucho".equals(muchof)) {

            cell = new PdfPCell(new Phrase("", DATO_FONT_A));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            table11c.addCell(cell);

            if (!"1".equals(rubro)) {
                cell = new PdfPCell(new Phrase("VENTA DE VARIOS MEDICAMENTOS", DATO_FONT_A));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setColspan(2);
                table11c.addCell(cell);
            } else {
                cell = new PdfPCell(new Phrase("VENTA DE VARIOS MEDICAMENTOS", DATO_FONT_A));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setColspan(2);
                table11c.addCell(cell);
            }

            cell = new PdfPCell(new Phrase(df.format(sumatot1), CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            table11c.addCell(cell);
        } else {

            for (int j = 0; j < listaKf.size(); j++) {
                Recetas datoc = (Recetas) listaKf.get(j);

                cell = new PdfPCell(new Phrase(df2.format(datoc.getSalida()), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.NO_BORDER);
                table11c.addCell(cell);

                cell = new PdfPCell(new Phrase("  " + datoc.getMedicamento() + "   [" + datoc.getForma_far() + "]   " + datoc.getConcentra(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
                table11c.addCell(cell);

                cell = new PdfPCell(new Phrase(df.format(datoc.getPrecio_venta()), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.NO_BORDER);
                table11c.addCell(cell);

                cell = new PdfPCell(new Phrase(df.format(datoc.getPrecio_venta() * datoc.getSalida()), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.NO_BORDER);
                table11c.addCell(cell);

            }

        }///para else muchos medcamentos
        cell = new PdfPCell(table11c);
        cell.setFixedHeight(210f);
        cell.setColspan(4);
        table.addCell(cell);

        ///fin detalle de factura    
        cell = new PdfPCell(new Phrase("TOTAL  Bs.", DATO_FONT_A));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setColspan(3);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(df.format(sumatot1), CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        document.add(table);

        n2t numero = new n2t();

        p = new Paragraph("Son  : " + numero.convertirLetras((int) sumatot1) + "    " + Integer.toString((int) (Math.round(((sumatot1 - (int) sumatot1) * 100)))) + "/100  Bolivianos", new Font(Font.HELVETICA, 10, Font.BOLD, new Color(0, 0, 0)));
        document.add(p);

        p = new Paragraph("CODIGO DE CONTROL  :  " + codigo1, DATO_FONT_BOLD);
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
        int[] fxwidths2 = {50, 40, 45}; // percentage
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

        finalImage.setAbsolutePosition(497, 396);
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
                cell = new PdfPCell(new Phrase("VENTA DE VARIOS MEDICAMENTOS", DATO_FONT_A));
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

            cell = new PdfPCell(new Phrase(df.format(sumatot1), CABEZA_COLUMNA_FONT));
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

        cell = new PdfPCell(new Phrase(df.format(sumatot1), CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table2.addCell(cell);

        document.add(table2);

        p = new Paragraph("Son  : " + numero.convertirLetras((int) sumatot1) + "    " + Integer.toString((int) (Math.round(((sumatot1 - (int) sumatot1) * 100)))) + "/100  Bolivianos", new Font(Font.HELVETICA, 10, Font.BOLD, new Color(0, 0, 0)));
        document.add(p);

        p = new Paragraph("CODIGO DE CONTROL  :  " + codigo1, DATO_FONT_BOLD);
        p.setAlignment(Element.ALIGN_LEFT);
        document.add(p);

        p = new Paragraph("FECHA LIMITE DE EMISION  : " + format(destab.getFecha(), "dd/MM/yyyy"), DATO_FONT_BOLD);
        p.setAlignment(Element.ALIGN_LEFT);
        document.add(p);

        p = new Paragraph("ESTA FACTURA CONTRIBUYE AL DESARROLLO DEL PAÍS. EL USO ILÍCITO DE ÉSTA SERÁ SANCIONADO DE ACUERDO A LEY", new Font(Font.HELVETICA, 8, Font.NORMAL, new Color(0, 0, 0)));
        document.add(p);

        p = new Paragraph("Ley No 453: El Proveedor debera dar cumplimineto a las condiciones ofertadas.", new Font(Font.HELVETICA, 6, Font.NORMAL, new Color(0, 0, 0)));
        document.add(p);

        ///////////////////////////////////////////factura 2 de costos
        document.newPage();

        PdfPTable tablexc = new PdfPTable(3);
        int[] fxwidthsc = {50, 40, 45}; // percentage
        tablexc.setWidths(fxwidthsc);
        tablexc.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase(destab.getNombrefact() + "\nCasa Matriz", DATO_FONT_A));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablexc.addCell(cell);

        cell = new PdfPCell(new Phrase("FACTURA", TITULO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(Rectangle.NO_BORDER);
        tablexc.addCell(cell);

        cell = new PdfPCell(new Phrase("NIT                      :  " + destab.getNit(), DATO_FONT_A));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
        cell.setGrayFill(0.9f);
        tablexc.addCell(cell);

        cell = new PdfPCell(new Phrase(destab.getDireccion(), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablexc.addCell(cell);

        cell = new PdfPCell(new Phrase("ORIGINAL", DATO_FONT_A));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(Rectangle.NO_BORDER);
        tablexc.addCell(cell);

        cell = new PdfPCell(new Phrase("Factura No           :  " + numfact2, DATO_FONT_A));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setGrayFill(0.9f);
        tablexc.addCell(cell);

        cell = new PdfPCell(new Phrase("Telefono : " + destab.getTele1(), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablexc.addCell(cell);

        cod2 = destab.getNit() + '|' + numfact2 + '|' + destab.getNum_auto() + '|' + format(new Date(), "dd/MM/yyyy") + '|' + sumatot2 + '|' + df.format(debito2) + '|' + codigo2 + '|' + ciu + '|' + '0' + '|' + '0' + '|' + '0' + '|' + '0';

        PdfContentByte cb2 = writer.getDirectContent();
        BarcodeQRCode qrcode2 = new BarcodeQRCode(cod2, 90, 90, null);
        java.awt.Image qrImage2 = qrcode2.createAwtImage(Color.black, Color.white);
        Image finalImage2 = Image.getInstance(writer, qrImage2, 1);
        finalImage2.setAbsolutePosition(497, 398);
        finalImage2.setBorder(Rectangle.NO_BORDER);
        document.add(finalImage2);

        cell = new PdfPCell(new Phrase("", DATO_FONT_A));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(Rectangle.NO_BORDER);
        tablexc.addCell(cell);

        cell = new PdfPCell(new Phrase("No Autorizacion    :  " + destab.getNum_auto(), DATO_FONT_A));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setGrayFill(0.9f);
        tablexc.addCell(cell);

        cell = new PdfPCell(new Phrase("Fax : " + destab.getTele2(), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(2);
        tablexc.addCell(cell);

        cell = new PdfPCell(new Phrase("SERVICIOS Y/O ACTIVIDADES SUJETAS AL IVA", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablexc.addCell(cell);

        cell = new PdfPCell(new Phrase(destab.getDepartamento() + " - Bolivia", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablexc.addCell(cell);

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
        tablexc.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(3);
        tablexc.addCell(cell);

        document.add(tablexc);

        PdfPTable table1c = new PdfPTable(2);
        int[] fmwidthsc = {50, 50}; // percentage
        table1c.setWidths(fmwidthsc);
        table1c.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("Nombre : " + nombreusua, DATO_FONT_A));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        table1c.addCell(cell);

        cell = new PdfPCell(new Phrase("NIT/CI Cliente: " + ciu, DATO_FONT_A));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(Rectangle.NO_BORDER);
        table1c.addCell(cell);

        cell = new PdfPCell(new Phrase("Por concepto de : ", new Font(Font.HELVETICA, 7, Font.NORMAL, new Color(0, 0, 0))));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        table1c.addCell(cell);

        cell = new PdfPCell(new Phrase("Usua : " + dato.getId_usuario() + "     H : " + hora24 + ":" + minutos + ":" + segundos, new Font(Font.HELVETICA, 6, Font.NORMAL, new Color(0, 0, 0))));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setBorder(Rectangle.NO_BORDER);
        table1c.addCell(cell);

        cell = new PdfPCell(new Phrase("Paciente : " + nombrepac, new Font(Font.HELVETICA, 6, Font.NORMAL, new Color(0, 0, 0))));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(2);
        table1c.addCell(cell);

        document.add(table1c);

        int NumColumnsc = 4;
        PdfPTable tablec = new PdfPTable(NumColumns);
        int[] fwidthscc = {4, 60, 8, 8};
        tablec.setWidths(fwidthscc);
        tablec.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("Cant.", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.9f);
        tablec.addCell(cell);

        cell = new PdfPCell(new Phrase("DETALLE", CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.9f);
        tablec.addCell(cell);

        cell = new PdfPCell(new Phrase("P/ Unit.", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.9f);
        tablec.addCell(cell);

        cell = new PdfPCell(new Phrase("TOTAL", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.9f);
        tablec.addCell(cell);

        //llena los espacios del detalle de la factura 
        PdfPTable table11cc = new PdfPTable(4);
        int[] fmwidthssc = {4, 60, 8, 8};  // percentage
        table11cc.setWidths(fmwidthssc);
        table11cc.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("", DATO_FONT_A));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        table11cc.addCell(cell);

        cell = new PdfPCell(new Phrase(rubro, DATO_FONT_A));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.BOTTOM);
        table11cc.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT_A));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        table11cc.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT_A));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        table11cc.addCell(cell);

        if (costo != null) {
            for (int j = 0; j < costo.size(); j++) {
                Detalle datoc = (Detalle) costo.get(j);

                //cell = new PdfPCell(new Phrase(Integer.toString(j+1) , DATO_FONT));
                cell = new PdfPCell(new Phrase("1", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.NO_BORDER);
                table11cc.addCell(cell);

                cell = new PdfPCell(new Phrase("        " + datoc.getCosto(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
                table11cc.addCell(cell);

                cell = new PdfPCell(new Phrase(df.format(datoc.getSalida()), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.NO_BORDER);
                table11cc.addCell(cell);

                cell = new PdfPCell(new Phrase(df.format(datoc.getSalida()), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.NO_BORDER);
                table11cc.addCell(cell);
            }
        } else {
            cell = new PdfPCell(new Phrase("1", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            table11cc.addCell(cell);

            cell = new PdfPCell(new Phrase("        " + costo1, DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            table11cc.addCell(cell);

            cell = new PdfPCell(new Phrase(df.format(sumatot2), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            table11cc.addCell(cell);

            cell = new PdfPCell(new Phrase(df.format(sumatot2), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            table11cc.addCell(cell);
        }
        cell = new PdfPCell(table11cc);
        cell.setFixedHeight(210f);
        cell.setColspan(4);
        tablec.addCell(cell);

        ///fin detalle de factura    
        cell = new PdfPCell(new Phrase("GRAN TOTAL  Bs.", DATO_FONT_A));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setColspan(3);
        tablec.addCell(cell);

        cell = new PdfPCell(new Phrase(df.format(sumatot2), CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tablec.addCell(cell);

        document.add(tablec);

        p = new Paragraph("Son  : " + numero.convertirLetras((int) sumatot2) + "    " + Integer.toString((int) (Math.round(((sumatot2 - (int) sumatot2) * 100)))) + "/100  Bolivianos", new Font(Font.HELVETICA, 10, Font.BOLD, new Color(0, 0, 0)));
        document.add(p);

        p = new Paragraph("CODIGO DE CONTROL  :  " + codigo2, DATO_FONT_BOLD);
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

        PdfPTable tablex2c = new PdfPTable(3);
        int[] fxwidths2c = {50, 40, 45}; // percentage
        tablex2c.setWidths(fxwidths2c);
        tablex2c.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase(destab.getNombrefact() + "\nCasa Matriz", DATO_FONT_A));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex2c.addCell(cell);

        cell = new PdfPCell(new Phrase("FACTURA", TITULO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex2c.addCell(cell);

        cell = new PdfPCell(new Phrase("NIT                      :  " + destab.getNit(), DATO_FONT_A));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
        cell.setGrayFill(0.9f);
        tablex2c.addCell(cell);

        cell = new PdfPCell(new Phrase(destab.getDireccion(), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex2c.addCell(cell);

        cell = new PdfPCell(new Phrase("COPIA", DATO_FONT_A));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex2c.addCell(cell);

        cell = new PdfPCell(new Phrase("Factura No           :  " + numfact2, DATO_FONT_A));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setGrayFill(0.9f);
        tablex2c.addCell(cell);

        cell = new PdfPCell(new Phrase("Telefono : " + destab.getTele1(), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex2c.addCell(cell);

        finalImage2.setAbsolutePosition(497, 398);
        finalImage2.setBorder(Rectangle.NO_BORDER);
        document.add(finalImage2);

        cell = new PdfPCell(new Phrase("", DATO_FONT_A));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex2c.addCell(cell);

        cell = new PdfPCell(new Phrase("No Autorizacion    :  " + destab.getNum_auto(), DATO_FONT_A));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setGrayFill(0.9f);
        tablex2c.addCell(cell);

        cell = new PdfPCell(new Phrase("Fax : " + destab.getTele2(), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(2);
        tablex2c.addCell(cell);

        cell = new PdfPCell(new Phrase("SERVICIOS Y/O ACTIVIDADES SUJETAS AL IVA", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex2c.addCell(cell);

        cell = new PdfPCell(new Phrase(destab.getDepartamento() + " - Bolivia", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex2c.addCell(cell);

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
        tablex2c.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(3);
        tablex2c.addCell(cell);

        document.add(tablex2c);

        PdfPTable table12c = new PdfPTable(2);
        int[] fmwidths2c = {50, 50}; // percentage
        table12c.setWidths(fmwidths2c);
        table12c.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("Nombre : " + nombreusua, DATO_FONT_A));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        table12c.addCell(cell);

        cell = new PdfPCell(new Phrase("NIT/CI : " + ciu, DATO_FONT_A));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(Rectangle.NO_BORDER);
        table12c.addCell(cell);

        cell = new PdfPCell(new Phrase("Por concepto de : ", new Font(Font.HELVETICA, 7, Font.NORMAL, new Color(0, 0, 0))));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        table12c.addCell(cell);

        cell = new PdfPCell(new Phrase("Usua : " + dato.getId_usuario() + "     H : " + hora24 + ":" + minutos + ":" + segundos, new Font(Font.HELVETICA, 6, Font.NORMAL, new Color(0, 0, 0))));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setBorder(Rectangle.NO_BORDER);
        table12c.addCell(cell);

        cell = new PdfPCell(new Phrase("Paciente : " + nombrepac, new Font(Font.HELVETICA, 6, Font.NORMAL, new Color(0, 0, 0))));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(2);
        table12c.addCell(cell);

        document.add(table12c);

        int NumColumns2c = 4;
        PdfPTable table2c = new PdfPTable(NumColumns2);
        int[] fwidths2c = {4, 60, 8, 8};
        table2c.setWidths(fwidths2c);
        table2c.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("Cant.", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.9f);
        table2c.addCell(cell);

        cell = new PdfPCell(new Phrase("DETALLE", CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.9f);
        table2c.addCell(cell);

        cell = new PdfPCell(new Phrase("P/ Unit.", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.9f);
        table2c.addCell(cell);

        cell = new PdfPCell(new Phrase("TOTAL", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.9f);
        table2c.addCell(cell);

        //llena los espacios del detalle de la factura 
        PdfPTable table112c = new PdfPTable(4);
        int[] fmwidthss2c = {4, 60, 8, 8};  // percentage
        table112c.setWidths(fmwidthss2c);
        table112c.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("", DATO_FONT_A));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        table112c.addCell(cell);

        cell = new PdfPCell(new Phrase(rubro, DATO_FONT_A));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.BOTTOM);
        table112c.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT_A));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        table112c.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT_A));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        table112c.addCell(cell);

        if (costo != null) {
            for (int j = 0; j < costo.size(); j++) {
                Detalle datoc = (Detalle) costo.get(j);

                //cell = new PdfPCell(new Phrase(Integer.toString(j+1) , DATO_FONT));
                cell = new PdfPCell(new Phrase("1", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.NO_BORDER);
                table112c.addCell(cell);

                cell = new PdfPCell(new Phrase("        " + datoc.getCosto(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
                table112c.addCell(cell);

                cell = new PdfPCell(new Phrase(df.format(datoc.getSalida()), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.NO_BORDER);
                table112c.addCell(cell);

                cell = new PdfPCell(new Phrase(df.format(datoc.getSalida()), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.NO_BORDER);
                table112c.addCell(cell);
            }
        } else {
            cell = new PdfPCell(new Phrase("1", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            table112c.addCell(cell);

            cell = new PdfPCell(new Phrase("        " + costo1, DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            table112c.addCell(cell);

            cell = new PdfPCell(new Phrase(df.format(sumatot2), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            table112c.addCell(cell);

            cell = new PdfPCell(new Phrase(df.format(sumatot2), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            table112c.addCell(cell);
        }

        cell = new PdfPCell(table112c);
        cell.setFixedHeight(210f);
        cell.setColspan(4);
        table2c.addCell(cell);

        ///fin detalle de factura    
        cell = new PdfPCell(new Phrase("GRAN TOTAL  Bs.", DATO_FONT_A));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setColspan(3);
        table2c.addCell(cell);

        cell = new PdfPCell(new Phrase(df.format(sumatot2), CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table2c.addCell(cell);

        document.add(table2c);

        p = new Paragraph("Son  : " + numero.convertirLetras((int) sumatot2) + "    " + Integer.toString((int) (Math.round(((sumatot2 - (int) sumatot2) * 100)))) + "/100  Bolivianos", new Font(Font.HELVETICA, 10, Font.BOLD, new Color(0, 0, 0)));
        document.add(p);

        p = new Paragraph("CODIGO DE CONTROL  :  " + codigo2, DATO_FONT_BOLD);
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
