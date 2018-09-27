package org.ayaic.web.administrarhistoriales;

import java.awt.Color;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import java.util.*;
import java.text.*;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Detalle;
import org.ayaic.domain.Historiales;
import org.ayaic.domain.Pacientes;

public class ReporteEconomicoIcPDF extends AbstractPdfView {

    private static final Font TITULO_FONT = new Font(Font.HELVETICA, 12, Font.BOLD, Color.black);
    private static final Font CABEZA_COLUMNA_FONT = new Font(Font.HELVETICA, 10, Font.ITALIC, Color.black);
    private static final Font DATO_FONT = new Font(Font.HELVETICA, 8, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_A = new Font(Font.HELVETICA, 6, Font.NORMAL, Color.black);
    private static final int MARGIN = 32;

    protected void buildPdfMetadata(Map model, Document document, HttpServletRequest request) {
        document.setPageSize(PageSize.LETTER);
        document.setMargins(10, 10, 5, 10);
    }

    protected void buildPdfDocument(Map model, Document document, PdfWriter writer, HttpServletRequest req, HttpServletResponse resp) throws Exception {

        Clientes datogen = (Clientes) model.get("cliente");
        Pacientes dato = (Pacientes) model.get("datos");
        Historiales historia = (Historiales) model.get("historia");
        java.util.List Otros = (java.util.List) model.get("listaCobrosOtros");
        java.util.List detalleCostos = (java.util.List) model.get("listarCostosT");
        java.util.List farmacias = (java.util.List) model.get("listaCobrosFar");
        java.util.List detallefar = (java.util.List) model.get("listarKardex");
        java.util.List listaSeguros = (java.util.List) model.get("listaPacAfi");
        String totfar = (String) model.get("totalfar");
        String totalfar = (String) model.get("totalfar");
        String totaldetf = (String) model.get("totaldetf");
        String totaldeudapag = (String) model.get("totaldeudapag");
        String total2 = (String) model.get("total2");
        String totalcfc = (String) model.get("totalcfc");
        String totalcff = (String) model.get("totalcff");
        String totalsfc = (String) model.get("totalsfc");
        String totalsff = (String) model.get("totalsff");
        String codigoseg = (String) model.get("codigoseg");
        String seguro = (String) model.get("seguro");
        DecimalFormat df = new DecimalFormat("###,##0.00");
        String tipo;

        Paragraph p = new Paragraph("Reporte Economico", new Font(Font.HELVETICA, 12, Font.BOLDITALIC, new Color(0, 0, 0)));
        p.setAlignment(Element.ALIGN_CENTER);

        if (totfar == null) {
            totfar = "0";
        }
        if (total2 == null) {
            total2 = "0";
        }
        if (totaldeudapag == null) {
            totaldeudapag = "0";
        }
        if (totaldetf == null) {
            totaldetf = "0";
        }

        PdfPCell cell;

        PdfPTable tablex = new PdfPTable(3);
        int[] fxwidths = {45, 40, 45}; // percentage
        tablex.setWidths(fxwidths);
        tablex.setWidthPercentage(100);

        cell = new PdfPCell(p);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(2);
        tablex.addCell(cell);

        document.add(tablex);

        PdfPTable table1n = new PdfPTable(4);
        int[] fmwidths = {30, 20, 20, 20}; // percentage
        table1n.setWidths(fmwidths);
        table1n.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("Paciente   :   " + dato.getNombres(), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        table1n.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(3);
        table1n.addCell(cell);

        cell = new PdfPCell(new Phrase("Fecha Atencion  :   " + format(historia.getFecha(), "dd/MM/yyyy HH:mm"), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        table1n.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(3);
        table1n.addCell(cell);

        if (totalfar == null) {
            totalfar = "0";
        }
        if (total2 == null) {
            total2 = "0";
        }
        if (totalcff == null) {
            totalcff = "0";
        }
        if (totalcfc == null) {
            totalcfc = "0";
        }
        if (totalsfc == null) {
            totalsfc = "0";
        }
        if (totalsff == null) {
            totalsff = "0";
        }
        if (totaldeudapag == null) {
            totaldeudapag = "0";
        }

        cell = new PdfPCell(new Phrase("Importe Total [Bs] :  " + df.format(Double.parseDouble(totalfar) + Double.parseDouble(total2) - Double.parseDouble(totaldeudapag)), CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setBorder(Rectangle.NO_BORDER);
        table1n.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(3);
        table1n.addCell(cell);

        cell = new PdfPCell(new Phrase("___________________________________________", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        table1n.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(3);
        table1n.addCell(cell);

        document.add(table1n);

        PdfPTable table = new PdfPTable(1);

        PdfPTable tablec = new PdfPTable(3);
        int[] fmwidtsc = {25, 10, 80}; // percentage
        tablec.setWidths(fmwidtsc);
        tablec.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("FARMACIA GENERAL", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablec.addCell(cell);

        cell = new PdfPCell(new Phrase(df.format(Double.parseDouble(totfar)), CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablec.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablec.addCell(cell);

        for (int i = 0; i < Otros.size(); i++) {

            Pacientes OtrosCostos = (Pacientes) Otros.get(i);

            cell = new PdfPCell(new Phrase(OtrosCostos.getNombre(), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            tablec.addCell(cell);

            cell = new PdfPCell(new Phrase(df.format(OtrosCostos.getTotal()), CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setBorder(Rectangle.NO_BORDER);
            tablec.addCell(cell);

            cell = new PdfPCell(new Phrase("", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            tablec.addCell(cell);

            PdfPTable tabled = new PdfPTable(5);
            int[] fmwidtsd = {15, 20, 10, 45, 45}; // percentage
            tabled.setWidths(fmwidtsd);
            tabled.setWidthPercentage(100);

            for (int j = 0; j < detalleCostos.size(); j++) {

                Detalle detac = (Detalle) detalleCostos.get(j);

                if (detac.getId_pedido() == OtrosCostos.getId_pedido() && detac.getId_rubro() == OtrosCostos.getId_rubro()) {

                    cell = new PdfPCell(new Phrase(format(detac.getFecha(), "dd/MM/yyyy HH:mm:ss"), DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setBorder(Rectangle.NO_BORDER);
                    tabled.addCell(cell);

                    if (detac.getId_empresa() == 0) {
                        cell = new PdfPCell(new Phrase(detac.getCosto() + "__" + detac.getIndicacion(), DATO_FONT));
                    } else {
                        cell = new PdfPCell(new Phrase("NoFact.:" + detac.getId_empresa() + ".-" + detac.getCosto() + "__" + detac.getIndicacion(), DATO_FONT));
                    }
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setBorder(Rectangle.NO_BORDER);
                    tabled.addCell(cell);

                    cell = new PdfPCell(new Phrase(df.format(detac.getEntrada()), DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    cell.setBorder(Rectangle.NO_BORDER);
                    tabled.addCell(cell);

                    cell = new PdfPCell(new Phrase("", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setBorder(Rectangle.NO_BORDER);
                    cell.setColspan(2);
                    tabled.addCell(cell);
                }
            }

            cell = new PdfPCell(tabled);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(3);
            tablec.addCell(cell);
        }
        ///ahora el detalle de farmacias
        /*
           for (int k = 0; k < farmacias.size(); k++) {
      
           Pacientes farma = (Pacientes) farmacias.get(k);	 
       
           cell = new PdfPCell(new Phrase(format(farma.getFec_registro(),"dd/MM/yyyy HH:mm:ss"), DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_LEFT);
           cell.setGrayFill(0.8f);
           tablec.addCell(cell);
           
           cell = new PdfPCell(new Phrase("Farmacia "+farma.getId_pedido(), DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_LEFT);
           cell.setGrayFill(0.8f);
           tablec.addCell(cell);

           cell = new PdfPCell(new Phrase(df.format(farma.getPrecio_total()), CABEZA_COLUMNA_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
           cell.setGrayFill(0.8f);
           tablec.addCell(cell);
           
           PdfPTable   tablef = new PdfPTable(8);
           int[] fmwidtsf = {5,20,35,20,20,10,10,15}; // percentage
           tablef.setWidths(fmwidtsf);
           tablef.setWidthPercentage(100);
           
           for (int l = 0; l < detallefar.size(); l++) {
      
               Recetas detafar = (Recetas) detallefar.get(l);	
               
                if(detafar.getId_pedido()==farma.getId_pedido() ){
                   
                   cell = new PdfPCell(new Phrase(Integer.toString(l+1) , DATO_FONT));
                   cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                   tablef.addCell(cell);
                   
                   cell = new PdfPCell(new Phrase(format(detafar.getFecha(),"dd/MM/yyyy HH:mm:ss"), DATO_FONT));
                   cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                   tablef.addCell(cell);
                   
                   cell = new PdfPCell(new Phrase(detafar.getMedicamento() , DATO_FONT));
                   cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                   tablef.addCell(cell);
                   
                   cell = new PdfPCell(new Phrase(detafar.getForma_far() , DATO_FONT));
                   cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                   tablef.addCell(cell);
                   
                   cell = new PdfPCell(new Phrase(detafar.getConcentra() , DATO_FONT));
                   cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                   tablef.addCell(cell);
                   
                   cell = new PdfPCell(new Phrase(df.format(detafar.getSalida()), DATO_FONT));
                   cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                   tablef.addCell(cell);
                   
                   cell = new PdfPCell(new Phrase(df.format(detafar.getPrecio_venta()), DATO_FONT));
                   cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                   tablef.addCell(cell);
                   
                   cell = new PdfPCell(new Phrase(df.format(detafar.getPrecio_total()), DATO_FONT));
                   cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                   tablef.addCell(cell);
                }
           }
           cell = new PdfPCell(tablef);
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
           cell.setColspan(3);
           tablec.addCell(cell);
       }
         */
        document.add(tablec);

    }

    public static String format(Date dia, String formato) {
        if (dia == null) {
            return "";
        }
        SimpleDateFormat formatDate = new SimpleDateFormat(formato);
        return formatDate.format(dia);
    }
}
