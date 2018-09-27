package org.ayaic.web.administrarhistoriales.ambulatorio;

import java.awt.Color;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.view.document.AbstractPdfView;
//import com.itextpdf.text.Image;
import java.util.*;
import java.text.*;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Cuadernos;

public class VerReporteProduccionHPDF extends AbstractPdfView {

    private static final Font TITULO_FONT = new Font(Font.HELVETICA, 12, Font.BOLD, Color.black);
    private static final Font CABEZA_COLUMNA_FONT = new Font(Font.HELVETICA, 8, Font.ITALIC, Color.black);
    private static final Font DATO_FONT = new Font(Font.HELVETICA, 7, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_BOLD = new Font(Font.HELVETICA, 8, Font.BOLD, Color.black);
    private static final int MARGIN = 32;

    protected void buildPdfMetadata(Map model, Document document, HttpServletRequest request) {
        document.setPageSize(PageSize.LETTER.rotate());
        document.setMargins(20, 20, 19, 10);
    }

    protected void buildPdfDocument(Map model, Document document, PdfWriter writer, HttpServletRequest req, HttpServletResponse resp) throws Exception {
        java.util.List lFopos = (java.util.List) model.get("listaCobros");

        Clientes dato = (Clientes) model.get("dato");
        String fec1 = (String) model.get("dFechaini1");
        String fec2 = (String) model.get("dFechafin1");
        PdfContentByte cb = writer.getDirectContent();
        // coloca el titulo de la pagina
        Image escudo = Image.getInstance("/opt/imagenes/lescudo.bmp");
        Paragraph p = new Paragraph("REPORTE DE PRODUCCION\nATENCION PACIENTES HOSPI ", new Font(Font.HELVETICA, 14, Font.BOLDITALIC, new Color(0, 0, 0)));
        p.setAlignment(Element.ALIGN_CENTER);
        int[] sumas = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        PdfPCell cell;

        //PdfWriter.getInstance(document, new FileOutputStream("c:/tmp_Siniestros/prueba.pdf"));
        document.open();
        Image img1 = Image.getInstance("/opt/imagenes/lescudo.bmp");//agrega imagen
        //            Image img1 = Image.getInstance(this.getClass().getClassLoader().getResource("/css/images/OXXOwm2.png"));//agrega imagen //FIXME FOR WEB
        //img1.scaleToFit(50, 50);//escala de la imagen
        img1.setAbsolutePosition(28f, 550f);
        img1.setAlignment(Image.MIDDLE | Image.UNDERLYING);//imagen centrada y de fondo.
        document.add(img1);

        PdfPTable table1 = new PdfPTable(3);
        int[] fmwidths = {10, 50, 50}; // percentage
        table1.setWidths(fmwidths);
        table1.setWidthPercentage(100);

        cell = new PdfPCell(p);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(3);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("", CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(Rectangle.NO_BORDER);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("Establecimiento : " + dato.getEstablecimiento() + "        Municipio : " + dato.getLocalidad(), DATO_FONT));
        cell.setBorder(Rectangle.NO_BORDER);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("            Del  : " + fec1 + "      a  : " + fec2, DATO_FONT));
        cell.setBorder(Rectangle.NO_BORDER);
        table1.addCell(cell);

        document.add(table1);

        int NumColumns = 44;
        PdfPTable table = new PdfPTable(NumColumns);
        int[] fwidths = {12, 60, 80, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 20};
        table.setWidths(fwidths);
        table.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("", CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(3);
        cell.setGrayFill(0.8f);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("< de 6 mes", CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(4);
        cell.setGrayFill(0.8f);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("6 a 11 mes", CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(4);
        cell.setGrayFill(0.8f);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("1 a 4 años", CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(4);
        cell.setGrayFill(0.8f);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("5 a 9 años", CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(4);
        cell.setGrayFill(0.8f);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("10 a 14 años", CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(4);
        cell.setGrayFill(0.8f);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("15 a 19 años", CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(4);
        cell.setGrayFill(0.8f);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("20 a 39 años", CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(4);
        cell.setGrayFill(0.8f);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("40 a 49 años", CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(4);
        cell.setGrayFill(0.8f);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("50 a 59 años", CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(4);
        cell.setGrayFill(0.8f);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("> 60 años", CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(4);
        cell.setGrayFill(0.8f);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("", CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.8f);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("", CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.8f);
        cell.setColspan(3);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Nuev", CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(2);
        cell.setGrayFill(0.8f);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Rept", CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(2);
        cell.setGrayFill(0.8f);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Nuev", CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(2);
        cell.setGrayFill(0.8f);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Rept", CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(2);
        cell.setGrayFill(0.8f);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Nuev", CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(2);
        cell.setGrayFill(0.8f);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Rept", CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(2);
        cell.setGrayFill(0.8f);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Nuev", CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(2);
        cell.setGrayFill(0.8f);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Rept", CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(2);
        cell.setGrayFill(0.8f);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Nuev", CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(2);
        cell.setGrayFill(0.8f);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Rept", CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(2);
        cell.setGrayFill(0.8f);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Nuev", CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(2);
        cell.setGrayFill(0.8f);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Rept", CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(2);
        cell.setGrayFill(0.8f);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Nuev", CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(2);
        cell.setGrayFill(0.8f);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Rept", CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(2);
        cell.setGrayFill(0.8f);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Nuev", CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(2);
        cell.setGrayFill(0.8f);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Rept", CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(2);
        cell.setGrayFill(0.8f);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Nuev", CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(2);
        cell.setGrayFill(0.8f);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Rept", CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(2);
        cell.setGrayFill(0.8f);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Nuev", CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(2);
        cell.setGrayFill(0.8f);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Rept", CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(2);
        cell.setGrayFill(0.8f);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("", CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.8f);
        table.addCell(cell);

        String sCampos[] = {"No", "Consultorio", "Medico", "M", "F", "M", "F", "M", "F", "M", "F", "M", "F", "M", "F", "M", "F", "M", "F", "M", "F", "M", "F", "M", "F", "M", "F", "M", "F", "M", "F", "M", "F", "M", "F", "M", "F", "M", "F", "M", "F", "M", "F", "Total"};
        // coloca la cabecera de titulos

        for (int i = 0; i < NumColumns; i++) {
            cell = new PdfPCell(new Phrase(sCampos[i], CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setGrayFill(0.8f);
            table.addCell(cell);
        }

        // coloca el detalle de loS datos
        for (int i = 0; i < lFopos.size(); i++) {

            Cuadernos datopac = (Cuadernos) lFopos.get(i);

            cell = new PdfPCell(new Phrase(Integer.toString(i + 1), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase((datopac.getTipo().length() > 20) ? datopac.getTipo().substring(0, 20) : datopac.getTipo(), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase((datopac.getTipoconsulta().length() > 20) ? datopac.getTipoconsulta().substring(0, 20) : datopac.getTipoconsulta(), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            //para  los nuevos atenciones 
            cell = new PdfPCell(new Phrase(Integer.toString(datopac.getSuma1()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            sumas[0] += datopac.getSuma1();

            cell = new PdfPCell(new Phrase(Integer.toString(datopac.getSuma2()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            sumas[1] += datopac.getSuma2();
            ////para  los repetidos     
            cell = new PdfPCell(new Phrase(Integer.toString(datopac.getSuma3()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            sumas[2] += datopac.getSuma3();

            cell = new PdfPCell(new Phrase(Integer.toString(datopac.getSuma4()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            sumas[3] += datopac.getSuma4();
            //para  los menores de 6 meses 
            cell = new PdfPCell(new Phrase(Integer.toString(datopac.getSuma5()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            sumas[4] += datopac.getSuma5();

            cell = new PdfPCell(new Phrase(Integer.toString(datopac.getSuma6()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            sumas[5] += datopac.getSuma6();
            //los de de 6 meses a 1 año  
            cell = new PdfPCell(new Phrase(Integer.toString(datopac.getSuma7()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            sumas[6] += datopac.getSuma7();

            cell = new PdfPCell(new Phrase(Integer.toString(datopac.getSuma8()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            sumas[7] += datopac.getSuma8();
            //para  los de 1 año a  menores de 4 años    
            cell = new PdfPCell(new Phrase(Integer.toString(datopac.getSuma9()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            sumas[8] += datopac.getSuma9();

            cell = new PdfPCell(new Phrase(Integer.toString(datopac.getSuma10()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            sumas[9] += datopac.getSuma10();
            //para  los de 5 año a  menores de 9 año     
            cell = new PdfPCell(new Phrase(Integer.toString(datopac.getSuma11()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            sumas[10] += datopac.getSuma11();

            cell = new PdfPCell(new Phrase(Integer.toString(datopac.getSuma12()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            sumas[11] += datopac.getSuma12();
            //para  los de 10 año a  menores de 14 año      
            cell = new PdfPCell(new Phrase(Integer.toString(datopac.getSuma13()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            sumas[12] += datopac.getSuma13();

            cell = new PdfPCell(new Phrase(Integer.toString(datopac.getSuma14()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            sumas[13] += datopac.getSuma14();
            //para  los de 15 años a  menores de 19 año        
            cell = new PdfPCell(new Phrase(Integer.toString(datopac.getSuma15()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            sumas[14] += datopac.getSuma15();

            cell = new PdfPCell(new Phrase(Integer.toString(datopac.getSuma16()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            sumas[15] += datopac.getSuma16();
            //para  los de 15 años a  menores de 19 año        
            cell = new PdfPCell(new Phrase(Integer.toString(datopac.getSuma17()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            sumas[16] += datopac.getSuma17();

            cell = new PdfPCell(new Phrase(Integer.toString(datopac.getSuma18()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            sumas[17] += datopac.getSuma18();

            //para  los de 20 año a  menores de 39 año        
            cell = new PdfPCell(new Phrase(Integer.toString(datopac.getSuma19()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            sumas[18] += datopac.getSuma19();

            cell = new PdfPCell(new Phrase(Integer.toString(datopac.getSuma20()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            sumas[19] += datopac.getSuma20();
            //para  los de 40 año a  menores de 49 año      
            cell = new PdfPCell(new Phrase(Integer.toString(datopac.getSuma21()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            sumas[20] += datopac.getSuma21();

            cell = new PdfPCell(new Phrase(Integer.toString(datopac.getSuma22()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            sumas[21] += datopac.getSuma22();
            //para  los de 50 año a  menores de 59 año       
            cell = new PdfPCell(new Phrase(Integer.toString(datopac.getSuma23()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            sumas[22] += datopac.getSuma23();

            cell = new PdfPCell(new Phrase(Integer.toString(datopac.getSuma24()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            sumas[23] += datopac.getSuma24();

            cell = new PdfPCell(new Phrase(Integer.toString(datopac.getSuma25()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            sumas[24] += datopac.getSuma25();

            cell = new PdfPCell(new Phrase(Integer.toString(datopac.getSuma26()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            sumas[25] += datopac.getSuma26();

            cell = new PdfPCell(new Phrase(Integer.toString(datopac.getSuma27()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            sumas[26] += datopac.getSuma27();

            cell = new PdfPCell(new Phrase(Integer.toString(datopac.getSuma28()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            sumas[27] += datopac.getSuma28();

            cell = new PdfPCell(new Phrase(Integer.toString(datopac.getSuma29()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            sumas[28] += datopac.getSuma29();

            cell = new PdfPCell(new Phrase(Integer.toString(datopac.getSuma30()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            sumas[29] += datopac.getSuma30();

            cell = new PdfPCell(new Phrase(Integer.toString(datopac.getSuma31()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            sumas[30] += datopac.getSuma31();

            cell = new PdfPCell(new Phrase(Integer.toString(datopac.getSuma32()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            sumas[31] += datopac.getSuma32();

            cell = new PdfPCell(new Phrase(Integer.toString(datopac.getSuma33()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            sumas[32] += datopac.getSuma33();

            cell = new PdfPCell(new Phrase(Integer.toString(datopac.getSuma34()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            sumas[33] += datopac.getSuma34();

            cell = new PdfPCell(new Phrase(Integer.toString(datopac.getSuma35()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            sumas[34] += datopac.getSuma35();

            cell = new PdfPCell(new Phrase(Integer.toString(datopac.getSuma36()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            sumas[35] += datopac.getSuma36();

            cell = new PdfPCell(new Phrase(Integer.toString(datopac.getSuma37()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            sumas[36] += datopac.getSuma37();

            cell = new PdfPCell(new Phrase(Integer.toString(datopac.getSuma38()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            sumas[37] += datopac.getSuma38();

            cell = new PdfPCell(new Phrase(Integer.toString(datopac.getSuma39()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            sumas[38] += datopac.getSuma39();

            cell = new PdfPCell(new Phrase(Integer.toString(datopac.getSuma40()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            sumas[29] += datopac.getSuma40();

            cell = new PdfPCell(new Phrase(Integer.toString(datopac.getSuma1() + datopac.getSuma2() + datopac.getSuma3() + datopac.getSuma4() + datopac.getSuma5() + datopac.getSuma6() + datopac.getSuma7() + datopac.getSuma8() + datopac.getSuma9() + datopac.getSuma10() + datopac.getSuma11() + datopac.getSuma12() + datopac.getSuma13() + datopac.getSuma14() + datopac.getSuma15() + datopac.getSuma16() + datopac.getSuma17() + datopac.getSuma18() + datopac.getSuma19() + datopac.getSuma20() + datopac.getSuma21() + datopac.getSuma22() + datopac.getSuma23() + datopac.getSuma24() + datopac.getSuma25() + datopac.getSuma26() + datopac.getSuma27() + datopac.getSuma28() + datopac.getSuma29() + datopac.getSuma30() + datopac.getSuma31() + datopac.getSuma32() + datopac.getSuma33() + datopac.getSuma34() + datopac.getSuma35() + datopac.getSuma36() + datopac.getSuma37() + datopac.getSuma38() + datopac.getSuma39() + datopac.getSuma40()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            sumas[40] += datopac.getSuma1() + datopac.getSuma2() + datopac.getSuma3() + datopac.getSuma4() + datopac.getSuma5() + datopac.getSuma6() + datopac.getSuma7() + datopac.getSuma8() + datopac.getSuma9() + datopac.getSuma10() + datopac.getSuma11() + datopac.getSuma12() + datopac.getSuma13() + datopac.getSuma14() + datopac.getSuma15() + datopac.getSuma16() + datopac.getSuma17() + datopac.getSuma18() + datopac.getSuma19() + datopac.getSuma20() + datopac.getSuma21() + datopac.getSuma22() + datopac.getSuma23() + datopac.getSuma24() + datopac.getSuma25() + datopac.getSuma26() + datopac.getSuma27() + datopac.getSuma28() + datopac.getSuma29() + datopac.getSuma30() + datopac.getSuma31() + datopac.getSuma32() + datopac.getSuma33() + datopac.getSuma34() + datopac.getSuma35() + datopac.getSuma36() + datopac.getSuma37() + datopac.getSuma38() + datopac.getSuma39() + datopac.getSuma40();

        }

        cell = new PdfPCell(new Phrase("TOTAL", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setColspan(3);
        table.addCell(cell);

        for (int j = 0; j < 42; j++) {
            cell = new PdfPCell(new Phrase(Integer.toString(sumas[j]), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
        }

        document.add(table);

        p = new Paragraph("Fecha de emision : " + format(new Date(), "dd/MM/yyyy  HH:mm"), DATO_FONT);
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
