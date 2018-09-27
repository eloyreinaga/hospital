package org.ayaic.web.administrarcobranza;

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

public class ReciboPDF extends AbstractPdfView {

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
        Departamentos departamento = (Departamentos) model.get("departamento");
        Pacientes pedido = (Pacientes) model.get("pedido");
        DecimalFormat df = new DecimalFormat("#,##0.00");
        String nombreusua = (String) model.get("nombreusuario");
        String rubro = (String) model.get("rubro");
        String id_pedido = (String) model.get("id_pedido");
        String costo1 = (String) model.get("costo1");
        String nit = (String) model.get("nit");
        String numcladoc = (String) model.get("codigo");
        double sumatot = (Double.parseDouble((String) model.get("rubrocosto")));
        String ciu = (String) model.get("ciu");
        // coloca el titulo de la pagina
        Image escudo = Image.getInstance("/opt/imagenes/lescudo.bmp");
        Paragraph p = new Paragraph("MOVIMIENTO ECONOMICO\n", new Font(Font.HELVETICA, 14, Font.BOLDITALIC, new Color(0, 0, 0)));
        p.setAlignment(Element.ALIGN_CENTER);
        //Image sumi = Image.getInstance("/opt/imagenes/sumi.bmp");

        PdfPCell cell;

        PdfPTable tablex = new PdfPTable(3);
        int[] fxwidths = {40, 50, 40}; // percentage
        tablex.setWidths(fxwidths);
        tablex.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase(destab.getEstablecimiento(), CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex.addCell(cell);

        cell = new PdfPCell(new Phrase("RECIBO", TITULO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT_A));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex.addCell(cell);

        cell = new PdfPCell(new Phrase(destab.getDireccion(), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(2);
        tablex.addCell(cell);

        cell = new PdfPCell(new Phrase("Recibo No :      000" + pedido.getNum_cladoc(), DATO_FONT_A));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        tablex.addCell(cell);

        cell = new PdfPCell(new Phrase("Telf : " + destab.getTele1(), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(3);
        tablex.addCell(cell);

        cell = new PdfPCell(new Phrase(departamento.getDepartamento() + " - BOLIVIA", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(2);
        tablex.addCell(cell);

        cell = new PdfPCell(new Phrase("FECHA :  " + format(pedido.getFec_registro(), "dd/MM/yyyy"), DATO_FONT_A));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
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

        cell = new PdfPCell(new Phrase("NIT/CI : " + ciu, DATO_FONT_A));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(Rectangle.NO_BORDER);
        table1.addCell(cell);

        Calendar calendar = Calendar.getInstance();
        int hora24 = calendar.get(Calendar.HOUR_OF_DAY);
        int minutos = calendar.get(Calendar.MINUTE);
        int segundos = calendar.get(Calendar.SECOND);
        int milisegundos = calendar.get(Calendar.MILLISECOND);

        cell = new PdfPCell(new Phrase("Por concepto de :     ", new Font(Font.HELVETICA, 7, Font.NORMAL, new Color(0, 0, 0))));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("No. :    " + nit + "                                                            Usua : " + dato.getId_usuario() + "     Hora : " + format(pedido.getFec_registro(), "HH:mm:ss"), new Font(Font.HELVETICA, 6, Font.NORMAL, new Color(0, 0, 0))));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setBorder(Rectangle.NO_BORDER);
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

        cell = new PdfPCell(new Phrase(rubro, DATO_FONT_A));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.BOTTOM);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT_A));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT_A));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        table11.addCell(cell);

        if (costo != null) {
            for (int j = 0; j < costo.size(); j++) {
                Detalle datoc = (Detalle) costo.get(j);

                //cell = new PdfPCell(new Phrase(Integer.toString(j+1) , DATO_FONT));
                cell = new PdfPCell(new Phrase("1", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.NO_BORDER);
                table11.addCell(cell);

                cell = new PdfPCell(new Phrase("        " + datoc.getCosto() + "-  " + datoc.getIndicacion(), DATO_FONT));
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
        cell = new PdfPCell(table11);
        //  cell.setFixedHeight(180f); 
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
        /*
     p = new Paragraph("CODIGO DE CONTROL  :  "+codigo+ "                                                                                                            FECHA LIMITE DE EMISION  : "+format(destab.getFecha(),"dd/MM/yyyy") , DATO_FONT_BOLD);
     p.setAlignment(Element.ALIGN_LEFT);
     document.add(p);
         */
        p = new Paragraph("Son  : " + numero.convertirLetras((int) sumatot) + "    " + Integer.toString((int) (Math.round(((sumatot - (int) sumatot) * 100)))) + "/100  Bolivianos", new Font(Font.HELVETICA, 10, Font.BOLD, new Color(0, 0, 0)));
        document.add(p);
        /*
     p = new Paragraph("ESTA FACTURA CONTRIBUYE AL DESARROLLO DEL PAÍS. EL USO ILÍCITO DE ÉSTA SERÁ SANCIONADO DE ACUERDO A LEY", new Font(Font.HELVETICA, 7, Font.NORMAL, new Color(0, 0, 0)));
     document.add(p);
         */
        PdfPTable tablez = new PdfPTable(4);
        int[] fzwidths = {25, 25, 25, 25}; // percentage
        tablez.setWidths(fzwidths);
        tablez.setWidthPercentage(100);

        //// codigo de barras
        ///String archivo = getBarCode("000"+persona.getHcl());
        //Image codigoBarra = Image.getInstance(archivo); 
        //PdfPCell cellBarCode = new PdfPCell(codigoBarra);
        //cellBarCode.setHorizontalAlignment(Element.ALIGN_RIGHT);        
        //cellBarCode.setBorder(Rectangle.NO_BORDER);
        //cellBarCode.setColspan(4);
        //tablez.addCell(cellBarCode);
        document.add(tablez);

    }

    public static String format(Date dia, String formato) {
        if (dia == null) {
            return "";
        }
        SimpleDateFormat formatDate = new SimpleDateFormat(formato);
        return formatDate.format(dia);
    }
    /*public String getBarCode(String nroPedido){
      //Create the barcode bean
        Code128Bean bean = new Code128Bean();
        final int dpi = 150;
        //Configure the barcode generator
        bean.setModuleWidth(UnitConv.in2mm(1.0f / dpi)); //makes the narrow bar 
        bean.setHeight(8);
        bean.setMsgPosition(HumanReadablePlacement.HRP_NONE);  //pone el numero debajo
        bean.doQuietZone(false);
        
        String pathCodeBar = "c:/codigoBarra.jpg"; 
        
        try {
            
            		////*getServletContext().getRealPath("/")+ */ /*"war/barcode/"+ (secuencia) + ".jpg";
            File aux = new File(pathCodeBar);
            aux.delete();
            File outputFile = new File(pathCodeBar);
            java.io.OutputStream out = new FileOutputStream(outputFile);
           
            //Set up the canvas provider for monochrome JPEG output 
            BitmapCanvasProvider canvas = new BitmapCanvasProvider(out, "image/jpeg", dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);   
            //Generate the barcode
            bean.generateBarcode(canvas, nroPedido);
            //Signal end of generation
            canvas.finish();
        
        } catch (Exception e) {
        	System.out.println("");
        } finally {
           // out.close();
        }
        
        return pathCodeBar;  
  }  
     */
}
