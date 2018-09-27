package org.ayaic.web.administrarcobranza;

import java.awt.Color;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.view.document.AbstractPdfView;
import java.util.*;
import java.text.*;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import org.ayaic.domain.Pacientes;

public class VerCodigoBarrasPDF extends AbstractPdfView {

    private static final Font TITULO_FONT = new Font(Font.HELVETICA, 12, Font.BOLD, Color.black);
    private static final Font CABEZA_COLUMNA_FONT = new Font(Font.HELVETICA, 8, Font.ITALIC, Color.black);
    private static final Font DATO_FONT = new Font(Font.HELVETICA, 8, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_B = new Font(Font.HELVETICA, 11, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_A = new Font(Font.HELVETICA, 9, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_BOLD = new Font(Font.HELVETICA, 8, Font.BOLD, Color.black);
    private static final int MARGIN = 32;

    protected void buildPdfMetadata(Map model, Document document, HttpServletRequest request) {
        document.setPageSize(PageSize.LEGAL);
        document.setMargins(20, 20, 20, 20);
    }

    protected void buildPdfDocument(Map model, Document document, PdfWriter writer, HttpServletRequest req, HttpServletResponse resp) throws DocumentException {

        Pacientes datoPaciente = (Pacientes) model.get("datos");

        PdfPCell cell;

        PdfPTable tablez = new PdfPTable(4);
        int[] fzwidths = {25, 25, 25, 25}; // percentage
        tablez.setWidths(fzwidths);
        tablez.setWidthPercentage(100);

        //// codigo de barras
        //String archivo = getBarCode("000"+datoPaciente.getHcl());
        // Image codigoBarra = Image.getInstance(archivo); 
        //PdfPCell cellBarCode = new PdfPCell(codigoBarra);
        //cellBarCode.setHorizontalAlignment(Element.ALIGN_LEFT);        
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
    /* 
   public String getBarCode(String nroPedido){
      //Create the barcode bean
        Code128Bean bean = new Code128Bean();
        final int dpi = 150;
        //Configure the barcode generator
        bean.setModuleWidth(UnitConv.in2mm(1.0f / dpi)); //makes the narrow bar 
        bean.setHeight(10);
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
