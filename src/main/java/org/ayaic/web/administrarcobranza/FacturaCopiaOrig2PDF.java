package org.ayaic.web.administrarcobranza;

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
import org.ayaic.domain.Detalle;
import org.ayaic.domain.Localidades;
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.Recetas;

public class FacturaCopiaOrig2PDF extends AbstractPdfView {

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

        Localidades destab = (Localidades) estab.get(0);
        Clientes dato = (Clientes) model.get("dato");
        Pacientes persona = (Pacientes) model.get("persona");
        Pacientes datfactura = (Pacientes) model.get("dfactura");
        Departamentos departamento = (Departamentos) model.get("departamento");
        Pacientes pedido = (Pacientes) model.get("pedido");
        DecimalFormat df = new DecimalFormat("#,##0.00");
        DecimalFormat df2 = new DecimalFormat("#,##0");
        String nombrecliente = (String) model.get("nombrecliente");
        String usuario = (String) model.get("usuario");
        String rubro = (String) model.get("rubro");
        String muchof = (String) model.get("taman");
        String id_pedido = (String) model.get("id_pedido");
        String costo1 = (String) model.get("costo1");
        String numfact = (String) model.get("numfact");
        String numauto = (String) model.get("numauto");
        String nit = (String) model.get("nit");
        String codigo = (String) model.get("codigo");
        String valida = (String) model.get("anular");
        String nombrepac = (String) model.get("nombrepac");
        String dia = (String) model.get("dia");
        String mes = (String) model.get("mes");
        String anio = (String) model.get("anio");
        String hora = (String) model.get("hora");
        String minuto = (String) model.get("minuto");
        String segundo = (String) model.get("segundo");
        String cod = (String) model.get("cod");
        double sumatot = (Double.parseDouble((String) model.get("rubrocosto")));
        String ciu = (String) model.get("ciu");
        // coloca el titulo de la pagina
        Image escudo = Image.getInstance("/opt/imagenes/lescudo.bmp");
        Paragraph p = new Paragraph("MOVIMIENTO ECONOMICO\n", new Font(Font.HELVETICA, 14, Font.BOLDITALIC, new Color(0, 0, 0)));
        p.setAlignment(Element.ALIGN_CENTER);

        if ("A".equals(valida)) {
            Font font = FontFactory.getFont(FontFactory.HELVETICA, 35, Font.BOLD, Color.lightGray);
            ColumnText.showTextAligned(writer.getDirectContentUnder(), Element.ALIGN_RIGHT,
                    new Paragraph("ANULADO", font), 300, 700, writer.getPageNumber() % 2 == 1 ? 30 : -90);
        }

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

        cell = new PdfPCell(new Phrase("Factura No           :  " + datfactura.getNum_fact(), DATO_FONT_A));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setGrayFill(0.9f);
        tablex.addCell(cell);

        cell = new PdfPCell(new Phrase("Telefono : " + destab.getTele1(), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex.addCell(cell);

        cod = destab.getNit() + '|' + datfactura.getNum_fact() + '|' + datfactura.getNum_auto() + '|' + format(datfactura.getFec_registro(), "dd/MM/yyyy") + '|' + sumatot + '|' + datfactura.getTotal() + '|' + codigo + '|' + ciu + '|' + '0' + '|' + '0' + '|' + '0' + '|' + '0';

        PdfContentByte cb = writer.getDirectContent();
        BarcodeQRCode qrcode = new BarcodeQRCode(cod, 90, 90, null);
        java.awt.Image qrImage = qrcode.createAwtImage(Color.black, Color.white);
        Image finalImage = Image.getInstance(writer, qrImage, 1);
        finalImage.setAbsolutePosition(497, 400);
        finalImage.setBorder(Rectangle.NO_BORDER);
        //qrImage.scalePercent(400);
        //cb.addImage(finalImage);
        document.add(finalImage);

        cell = new PdfPCell(new Phrase("", DATO_FONT_A));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex.addCell(cell);

        cell = new PdfPCell(new Phrase("No Autorizacion    :  " + datfactura.getNum_auto(), DATO_FONT_A));
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

        if ("01".equals(mes)) {
            cell = new PdfPCell(new Phrase(destab.getLocalidad() + " : " + dia + "  de  enero  del  " + anio, DATO_FONT_A));
        }
        if ("02".equals(mes)) {
            cell = new PdfPCell(new Phrase(destab.getLocalidad() + " : " + dia + "  de  febrero  del  " + anio, DATO_FONT_A));
        }
        if ("03".equals(mes)) {
            cell = new PdfPCell(new Phrase(destab.getLocalidad() + " : " + dia + "  de  marzo  del  " + anio, DATO_FONT_A));
        }
        if ("04".equals(mes)) {
            cell = new PdfPCell(new Phrase(destab.getLocalidad() + " : " + dia + "  de  abril  del  " + anio, DATO_FONT_A));
        }
        if ("05".equals(mes)) {
            cell = new PdfPCell(new Phrase(destab.getLocalidad() + " : " + dia + "  de  mayo  del  " + anio, DATO_FONT_A));
        }
        if ("06".equals(mes)) {
            cell = new PdfPCell(new Phrase(destab.getLocalidad() + " : " + dia + "  de junio  del  " + anio, DATO_FONT_A));
        }
        if ("07".equals(mes)) {
            cell = new PdfPCell(new Phrase(destab.getLocalidad() + " : " + dia + "  de  julio  del  " + anio, DATO_FONT_A));
        }
        if ("08".equals(mes)) {
            cell = new PdfPCell(new Phrase(destab.getLocalidad() + " : " + dia + "  de  agosto  del  " + anio, DATO_FONT_A));
        }
        if ("09".equals(mes)) {
            cell = new PdfPCell(new Phrase(destab.getLocalidad() + " : " + dia + "  de  septiembre  del  " + anio, DATO_FONT_A));
        }
        if ("10".equals(mes)) {
            cell = new PdfPCell(new Phrase(destab.getLocalidad() + " : " + dia + "  de  octubre  del  " + anio, DATO_FONT_A));
        }
        if ("11".equals(mes)) {
            cell = new PdfPCell(new Phrase(destab.getLocalidad() + " : " + dia + "  de  noviembre  del  " + anio, DATO_FONT_A));
        }
        if ("12".equals(mes)) {
            cell = new PdfPCell(new Phrase(destab.getLocalidad() + " : " + dia + "  de  diciembre  del  " + anio, DATO_FONT_A));
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

        cell = new PdfPCell(new Phrase("Nombre : " + nombrecliente, DATO_FONT_A));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("NIT/CI Cliente: " + ciu, DATO_FONT_A));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(Rectangle.NO_BORDER);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("Por concepto de : ", new Font(Font.HELVETICA, 7, Font.NORMAL, new Color(0, 0, 0))));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("Usua : " + usuario + "     H : " + hora + ":" + minuto + ":" + segundo, new Font(Font.HELVETICA, 6, Font.NORMAL, new Color(0, 0, 0))));
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

        if (!"1".equals(rubro)) {

        } else {
            cell = new PdfPCell(new Phrase("", DATO_FONT_A));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            table11.addCell(cell);

            cell = new PdfPCell(new Phrase("FARMACIA  (Medicamentos)", DATO_FONT_A));///
            if ("I".equals(dato.getArea())) {
                cell = new PdfPCell(new Phrase("Almacenes", DATO_FONT_A));///FARMACIA  (Medicamentos) 
            }
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.BOTTOM);
            table11.addCell(cell);

            cell = new PdfPCell(new Phrase("", DATO_FONT_A));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(2);
            table11.addCell(cell);
        }

        if ("mucho".equals(muchof)) {

            cell = new PdfPCell(new Phrase("", DATO_FONT_A));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            table11.addCell(cell);

            if (!"1".equals(rubro)) {
                cell = new PdfPCell(new Phrase("VENTA DE SERVICIOS VARIOS", DATO_FONT_A));
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

            if (costo != null) {
                if (!"1".equals(rubro)) {
                    for (int j = 0; j < costo.size(); j++) {
                        Detalle datoc = (Detalle) costo.get(j);

                        //cell = new PdfPCell(new Phrase(Integer.toString(j+1) , DATO_FONT));
                        cell = new PdfPCell(new Phrase("1", DATO_FONT));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setBorder(Rectangle.NO_BORDER);
                        table11.addCell(cell);

                        cell = new PdfPCell(new Phrase(datoc.getRubro() + ";_" + datoc.getCosto() + "-  " + datoc.getIndicacion(), DATO_FONT));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setBorder(Rectangle.NO_BORDER);
                        table11.addCell(cell);

                        cell = new PdfPCell(new Phrase(df.format(datoc.getSalida()), DATO_FONT));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setBorder(Rectangle.NO_BORDER);
                        table11.addCell(cell);

                        cell = new PdfPCell(new Phrase(df.format(datoc.getSalida()), DATO_FONT));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setBorder(Rectangle.NO_BORDER);
                        table11.addCell(cell);
                    }
                } else {
                    for (int j = 0; j < costo.size(); j++) {
                        Recetas datoc = (Recetas) costo.get(j);

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
                }

            } else {
                cell = new PdfPCell(new Phrase("1", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.NO_BORDER);
                table11.addCell(cell);

                cell = new PdfPCell(new Phrase("        " + costo1, DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
                table11.addCell(cell);

                cell = new PdfPCell(new Phrase(df.format(sumatot), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.NO_BORDER);
                table11.addCell(cell);

                cell = new PdfPCell(new Phrase(df.format(sumatot), DATO_FONT));
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

    }

    public static String format(Date dia, String formato) {
        if (dia == null) {
            return "";
        }
        SimpleDateFormat formatDate = new SimpleDateFormat(formato);
        return formatDate.format(dia);
    }

}
