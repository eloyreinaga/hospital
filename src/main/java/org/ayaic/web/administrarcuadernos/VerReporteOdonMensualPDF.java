package org.ayaic.web.administrarcuadernos;

import java.awt.Color;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.view.document.AbstractPdfView;
import java.util.*;
import java.text.*;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import org.ayaic.domain.Actividad;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Cuadernos;
import org.ayaic.domain.Prestaciones;

public class VerReporteOdonMensualPDF extends AbstractPdfView {

    private static final Font TITULO_FONT = new Font(Font.HELVETICA, 12, Font.BOLD, Color.black);
    private static final Font CABEZA_COLUMNA_FONT = new Font(Font.TIMES_ROMAN, 7, Font.NORMAL, Color.black);
    private static final Font DATO_FONT = new Font(Font.TIMES_ROMAN, 7, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_BOLD = new Font(Font.HELVETICA, 8, Font.BOLD, Color.black);
    private static final int MARGIN = 32;

    protected void buildPdfMetadata(Map model, Document document, HttpServletRequest request) {
        document.setPageSize(PageSize.LETTER);
        document.setMargins(10, 10, 10, 10);
    }

    protected void buildPdfDocument(Map model, Document document, PdfWriter writer, HttpServletRequest req, HttpServletResponse resp) throws Exception {

        java.util.List lFopos = (java.util.List) model.get("listaOdon");
        java.util.List lactividad = (java.util.List) model.get("listaractividad");
        java.util.List lPresOdon = (java.util.List) model.get("listarPresOdon");

        String fecha1 = (String) model.get("fecha1");
        String fecha2 = (String) model.get("fecha2");
        String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        Clientes dato = (Clientes) model.get("dato");
        // coloca el titulo de la pagina
        Image escudo = Image.getInstance("/opt/imagenes/mescudo.bmp");
        Paragraph p = new Paragraph("INFORME MENSUAL DE ACTIVIDADES", new Font(Font.HELVETICA, 10, Font.BOLDITALIC, new Color(0, 0, 0)));
        p.setAlignment(Element.ALIGN_CENTER);

        PdfPCell cell;

        PdfPTable tablex = new PdfPTable(2);
        int[] fxwidths = {15, 70}; // percentage
        tablex.setWidths(fxwidths);
        tablex.setWidthPercentage(100);

        cell = new PdfPCell(escudo);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex.addCell(cell);

        cell = new PdfPCell(p);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex.addCell(cell);

        document.add(tablex);

        PdfPTable table1 = new PdfPTable(3);
        int[] fmwidths = {33, 33, 33}; // percentage
        table1.setWidths(fmwidths);
        table1.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("SEDES : " + dato.getDepartamento(), DATO_FONT));
        cell.setBorder(Rectangle.NO_BORDER);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("Red de salud: " + dato.getRed(), DATO_FONT));
        cell.setBorder(Rectangle.NO_BORDER);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("Municipio : " + dato.getLocalidad(), DATO_FONT));
        cell.setBorder(Rectangle.NO_BORDER);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("Establecimiento : " + dato.getEstablecimiento(), DATO_FONT));
        cell.setBorder(Rectangle.NO_BORDER);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("Del : " + fecha1 + "  al  " + fecha2, DATO_FONT));
        cell.setBorder(Rectangle.NO_BORDER);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("Resposable : " + dato.getNombres(), DATO_FONT));
        cell.setBorder(Rectangle.NO_BORDER);
        table1.addCell(cell);
        document.add(table1);

        int NumColumnsa = 16;
        PdfPTable tablez = new PdfPTable(NumColumnsa);
        int[] fwidthsz = {69, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 20};
        tablez.setWidths(fwidthsz);
        tablez.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase(".......", CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setGrayFill(0.7f);
        tablez.addCell(cell);

        cell = new PdfPCell(new Phrase("Menores 5 años", CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.7f);
        cell.setColspan(2);
        tablez.addCell(cell);

        cell = new PdfPCell(new Phrase("de 5 a 13 años", CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.7f);
        cell.setColspan(2);
        tablez.addCell(cell);

        cell = new PdfPCell(new Phrase("de 14 a 19 años", CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.7f);
        cell.setColspan(2);
        tablez.addCell(cell);

        cell = new PdfPCell(new Phrase("de 20 a 59 años", CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.7f);
        cell.setColspan(2);
        tablez.addCell(cell);

        cell = new PdfPCell(new Phrase("Mayor 60 años", CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.7f);
        cell.setColspan(2);
        tablez.addCell(cell);

        cell = new PdfPCell(new Phrase("Mujer Embar / Puerp", CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.7f);
        cell.setColspan(2);
        tablez.addCell(cell);

        cell = new PdfPCell(new Phrase("TOTAL", CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.7f);
        cell.setColspan(2);
        tablez.addCell(cell);

        cell = new PdfPCell(new Phrase("Observaciones", CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.7f);
        tablez.addCell(cell);

        document.add(tablez);

        int NumCol = 2;
        PdfPTable tabletotal = new PdfPTable(NumCol);
        int[] fancho = {30, 70};
        tabletotal.setWidths(fancho);
        tabletotal.setWidthPercentage(100);

        int NumColumns = 15;
        PdfPTable table = new PdfPTable(NumColumns);
        int[] fwidths = {10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 20};
        table.setWidths(fwidths);
        table.setWidthPercentage(100);

        String sCampos[] = {"F", "M", "F", "M", "F", "M", "F", "M", "F", "M", "E", "P", "F", "M", ""};
        // coloca la cabecera de titulos
        for (int i = 0; i < NumColumns; i++) {
            cell = new PdfPCell(new Phrase(sCampos[i], CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            //cell.setColspan(2);
            table.addCell(cell);
        }

        for (int i = 0; i < lFopos.size(); i++) {
            Cuadernos datopac = (Cuadernos) lFopos.get(i);
            cell = new PdfPCell(new Phrase(Integer.toString(datopac.getSuma1()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(Integer.toString(datopac.getSuma2()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(Integer.toString(datopac.getSuma3()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(Integer.toString(datopac.getSuma4()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(Integer.toString(datopac.getSuma5()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(Integer.toString(datopac.getSuma6()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(Integer.toString(datopac.getSuma7()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(Integer.toString(datopac.getSuma8()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(Integer.toString(datopac.getSuma9()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(Integer.toString(datopac.getSuma10()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(Integer.toString(datopac.getSuma11()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(Integer.toString(datopac.getSuma12()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(Integer.toString(datopac.getSuma13()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(Integer.toString(datopac.getSuma14()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
        }

        // los titulos
        PdfPTable table11 = new PdfPTable(4);
        int[] fywidths = {15, 15, 60, 10}; // percentage
        table11.setWidths(fywidths);
        table11.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("SEXO", DATO_FONT));
        cell.setColspan(4);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase("Primera Consulta", DATO_FONT));
        cell.setColspan(4);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase("Consultas Nuevas", DATO_FONT));
        cell.setColspan(4);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase("Primera Repetidas", DATO_FONT));
        cell.setColspan(4);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase("TOTAL CONSULTAS NUEVAS Y REPETIDAS", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.7f);
        cell.setColspan(4);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase("Medidas Preventivas", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setFixedHeight(40f);
        cell.setRotation(90);
        table11.addCell(cell);

        PdfPTable table51 = new PdfPTable(3);
        int[] f5widths = {17, 70, 13};
        table51.setWidths(f5widths);
        table51.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("Profilaxis", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(2);
        table51.addCell(cell);

        cell = new PdfPCell(new Phrase("D", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table51.addCell(cell);

        cell = new PdfPCell(new Phrase("Fluor Topico", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(2);
        table51.addCell(cell);

        cell = new PdfPCell(new Phrase("D", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table51.addCell(cell);

        cell = new PdfPCell(new Phrase("Sellado fosas y fisuras", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(2);
        table51.addCell(cell);

        cell = new PdfPCell(new Phrase("D", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table51.addCell(cell);

        cell = new PdfPCell(new Phrase("Aplicacion Cariostatico", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(2);
        table51.addCell(cell);

        cell = new PdfPCell(new Phrase("D", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table51.addCell(cell);

        cell = new PdfPCell(table51);
        cell.setColspan(3);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase("TOTAL MEDIDAS PREVENTIVAS", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.7f);
        cell.setColspan(4);
        table11.addCell(cell);

        PdfPTable table21 = new PdfPTable(1);

        cell = new PdfPCell(new Phrase("P", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table21.addCell(cell);

        cell = new PdfPCell(new Phrase("T", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table21.addCell(cell);

        cell = new PdfPCell(new Phrase("Acciones Curativas", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setRotation(90);
        table11.addCell(cell);

        PdfPTable table41 = new PdfPTable(2);
        int[] f4widths = {17, 83}; // percentage
        table41.setWidths(f4widths);
        table41.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("Restauraciones", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setRotation(90);
        table41.addCell(cell);

        PdfPTable table31 = new PdfPTable(2);
        int[] f3widths = {86, 17}; // percentage
        table31.setWidths(f3widths);
        table31.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("Amalgama", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table31.addCell(cell);

        cell = new PdfPCell(new Phrase("P", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table31.addCell(cell);

        cell = new PdfPCell(new Phrase("Resina Fotocurable", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table31.addCell(cell);

        cell = new PdfPCell(table21);
        table31.addCell(cell);

        cell = new PdfPCell(new Phrase("Ionomero de Vidrio", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table31.addCell(cell);

        cell = new PdfPCell(table21);
        table31.addCell(cell);

        cell = new PdfPCell(table31);
        cell.setColspan(2);
        table41.addCell(cell);

        cell = new PdfPCell(new Phrase("Endodoncia", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setRotation(90);
        table41.addCell(cell);

        table31 = new PdfPTable(2);
        int[] f33widths = {86, 17}; // percentage
        table31.setWidths(f33widths);
        table31.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("Pulpotomia", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        //cell.setColspan(2);
        table31.addCell(cell);

        cell = new PdfPCell(table21);
        table31.addCell(cell);

        cell = new PdfPCell(new Phrase("Trat. Endodontico", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table31.addCell(cell);

        PdfPTable table211 = new PdfPTable(1);

        cell = new PdfPCell(new Phrase("AC", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table211.addCell(cell);

        cell = new PdfPCell(new Phrase("LC", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table211.addCell(cell);

        cell = new PdfPCell(new Phrase("TC", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table211.addCell(cell);

        cell = new PdfPCell(table211);
        table31.addCell(cell);

        cell = new PdfPCell(table31);
        cell.setColspan(2);
        table41.addCell(cell);

        cell = new PdfPCell(new Phrase("Periodoncia", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setRotation(90);
        table41.addCell(cell);

        table31 = new PdfPTable(2);
        //int[] f33widths = {86,14}; // percentage
        table31.setWidths(f33widths);
        table31.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("Tartrectomia", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        //cell.setColspan(2);
        table31.addCell(cell);

        PdfPTable table212 = new PdfPTable(1);

        cell = new PdfPCell(new Phrase("1a S", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table212.addCell(cell);

        cell = new PdfPCell(new Phrase("2a S", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table212.addCell(cell);

        cell = new PdfPCell(new Phrase("3a S", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table212.addCell(cell);

        cell = new PdfPCell(table212);
        table31.addCell(cell);

        cell = new PdfPCell(new Phrase("Gingivectomia Simple", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table31.addCell(cell);

        PdfPTable table213 = new PdfPTable(1);

        cell = new PdfPCell(new Phrase("CPD", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table213.addCell(cell);

        cell = new PdfPCell(new Phrase("RCQ", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table213.addCell(cell);

        cell = new PdfPCell(table213);
        table31.addCell(cell);

        cell = new PdfPCell(new Phrase("Trat no quirugico (Ejm GUNA)", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table31.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table31.addCell(cell);

        cell = new PdfPCell(table31);
        cell.setColspan(2);
        table41.addCell(cell);

        cell = new PdfPCell(new Phrase("Cir. Bucal Menor", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setRotation(90);
        table41.addCell(cell);

        table31 = new PdfPTable(2);
        //int[] f34widths = {86,14}; // percentage
        table31.setWidths(f33widths);
        table31.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("Exodoncia", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table31.addCell(cell);

        cell = new PdfPCell(table21);
        table31.addCell(cell);

        cell = new PdfPCell(new Phrase("Tratamiento Alveolitis", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table31.addCell(cell);

        cell = new PdfPCell(new Phrase("P", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table31.addCell(cell);

        cell = new PdfPCell(new Phrase("Trat. de Absceso Periapical", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table31.addCell(cell);

        cell = new PdfPCell(table21);
        table31.addCell(cell);

        cell = new PdfPCell(new Phrase("Trat de Fractura dentoalveolar Simple", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table31.addCell(cell);

        cell = new PdfPCell(table21);
        table31.addCell(cell);

        cell = new PdfPCell(table31);
        cell.setColspan(2);
        table41.addCell(cell);

        cell = new PdfPCell(table41);
        cell.setColspan(3);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase("TOTAL  ACCIONES CURATIVAS", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.7f);
        cell.setColspan(4);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase("Otras Acciones", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setColspan(4);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase("TOTALES", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setColspan(4);
        table11.addCell(cell);

        // se agrega las 2 partes a la table
        cell = new PdfPCell(table11);
        tabletotal.addCell(cell);

        cell = new PdfPCell(table);
        tabletotal.addCell(cell);

        document.add(tabletotal);

        PdfPTable table72 = new PdfPTable(4);
        int[] f72widths = {60, 10, 30, 10};
        table72.setWidths(f72widths);
        table72.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("Acciones de Prevencion y Educacion en Salud Oral", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.7f);
        table72.addCell(cell);
        cell = new PdfPCell(new Phrase("No Beneficiarios", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.7f);
        table72.addCell(cell);
        cell = new PdfPCell(new Phrase("Tema Aplicado", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.7f);
        table72.addCell(cell);
        cell = new PdfPCell(new Phrase("Fecha", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.7f);
        table72.addCell(cell);
        ////     
        for (int i = 0; i < lactividad.size(); i++) {
            Actividad acti = (Actividad) lactividad.get(i);

            cell = new PdfPCell(new Phrase(acti.getActividad(), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table72.addCell(cell);

            cell = new PdfPCell(new Phrase(Integer.toString(acti.getNumero()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table72.addCell(cell);
            cell = new PdfPCell(new Phrase(acti.getTema(), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table72.addCell(cell);
            cell = new PdfPCell(new Phrase(format(acti.getFecha(), "dd/MM/yyyy"), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table72.addCell(cell);
        }
        for (int i = 0; i < 2; i++) {
            cell = new PdfPCell(new Phrase(".", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table72.addCell(cell);
            cell = new PdfPCell(new Phrase("", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table72.addCell(cell);
            cell = new PdfPCell(new Phrase("", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table72.addCell(cell);
            cell = new PdfPCell(new Phrase("", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table72.addCell(cell);
        }

        document.add(table72);

        PdfPTable table52 = new PdfPTable(6);
        int[] f52widths = {30, 10, 10, 30, 10, 10};
        table52.setWidths(f52widths);
        table52.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("INFORMACION SIIS", DATO_FONT));
        cell.setColspan(6);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.7f);
        table52.addCell(cell);

        cell = new PdfPCell(new Phrase("MADRE - NINIO", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table52.addCell(cell);
        cell = new PdfPCell(new Phrase("CODIGO", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table52.addCell(cell);
        cell = new PdfPCell(new Phrase("TOTAL", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table52.addCell(cell);
        cell = new PdfPCell(new Phrase("MADRE - NINIO", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table52.addCell(cell);
        cell = new PdfPCell(new Phrase("CODIGO", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table52.addCell(cell);
        cell = new PdfPCell(new Phrase("TOTAL", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table52.addCell(cell);

        cell = new PdfPCell(new Phrase("Consulta Odontologica", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table52.addCell(cell);
        cell = new PdfPCell(new Phrase("Z012", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table52.addCell(cell);
        cell = new PdfPCell(new Phrase(Integer.toString(cantidad(lPresOdon, "Z012")), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table52.addCell(cell);

        cell = new PdfPCell(new Phrase("Fluoracion Topica", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table52.addCell(cell);
        cell = new PdfPCell(new Phrase("PC26", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table52.addCell(cell);
        cell = new PdfPCell(new Phrase(Integer.toString(cantidad(lPresOdon, "PC26")), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table52.addCell(cell);

        cell = new PdfPCell(new Phrase("Profilaxis", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table52.addCell(cell);
        cell = new PdfPCell(new Phrase("PC28", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table52.addCell(cell);
        cell = new PdfPCell(new Phrase(Integer.toString(cantidad(lPresOdon, "PC28")), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table52.addCell(cell);

        cell = new PdfPCell(new Phrase("Caries Rampante", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table52.addCell(cell);
        cell = new PdfPCell(new Phrase("K029", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table52.addCell(cell);
        cell = new PdfPCell(new Phrase(Integer.toString(cantidad(lPresOdon, "K029")), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table52.addCell(cell);

        cell = new PdfPCell(new Phrase("Exodoncia", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table52.addCell(cell);
        cell = new PdfPCell(new Phrase("PC25", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table52.addCell(cell);
        cell = new PdfPCell(new Phrase(Integer.toString(cantidad(lPresOdon, "PC25")), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table52.addCell(cell);

        cell = new PdfPCell(new Phrase("Sellado de Fosas y Fisuras", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table52.addCell(cell);
        cell = new PdfPCell(new Phrase("PC29", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table52.addCell(cell);
        cell = new PdfPCell(new Phrase(Integer.toString(cantidad(lPresOdon, "PC29")), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table52.addCell(cell);

        cell = new PdfPCell(new Phrase("Caries de Dentina", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table52.addCell(cell);
        cell = new PdfPCell(new Phrase("K021", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table52.addCell(cell);
        cell = new PdfPCell(new Phrase(Integer.toString(cantidad(lPresOdon, "K021")), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table52.addCell(cell);

        cell = new PdfPCell(new Phrase("Alveolitis", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table52.addCell(cell);
        cell = new PdfPCell(new Phrase("K103", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table52.addCell(cell);
        cell = new PdfPCell(new Phrase(Integer.toString(cantidad(lPresOdon, "K103")), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table52.addCell(cell);

        cell = new PdfPCell(new Phrase("Absceso periapial agudo", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table52.addCell(cell);
        cell = new PdfPCell(new Phrase("K047", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table52.addCell(cell);
        cell = new PdfPCell(new Phrase(Integer.toString(cantidad(lPresOdon, "K047")), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table52.addCell(cell);

        cell = new PdfPCell(new Phrase("Pulpotomia", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table52.addCell(cell);
        cell = new PdfPCell(new Phrase("K040", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table52.addCell(cell);
        cell = new PdfPCell(new Phrase(Integer.toString(cantidad(lPresOdon, "K040")), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table52.addCell(cell);

        cell = new PdfPCell(new Phrase("Caries de Esmalte", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table52.addCell(cell);
        cell = new PdfPCell(new Phrase("K020", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table52.addCell(cell);
        cell = new PdfPCell(new Phrase(Integer.toString(cantidad(lPresOdon, "K020")), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table52.addCell(cell);

        cell = new PdfPCell(new Phrase("Granulomas (Cirugia ambulatoria maxilo facial", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table52.addCell(cell);
        cell = new PdfPCell(new Phrase("PC103", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table52.addCell(cell);
        cell = new PdfPCell(new Phrase(Integer.toString(cantidad(lPresOdon, "PC103")), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table52.addCell(cell);

        cell = new PdfPCell(new Phrase("PRAT", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table52.addCell(cell);
        cell = new PdfPCell(new Phrase("PC27", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table52.addCell(cell);
        cell = new PdfPCell(new Phrase(Integer.toString(cantidad(lPresOdon, "PC27")), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table52.addCell(cell);

        cell = new PdfPCell(new Phrase("Fractura dentoalveolar S", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table52.addCell(cell);
        cell = new PdfPCell(new Phrase("PC68", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table52.addCell(cell);
        cell = new PdfPCell(new Phrase(Integer.toString(cantidad(lPresOdon, "PC68")), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table52.addCell(cell);

        cell = new PdfPCell(new Phrase("Obturacion con amalgama y resina", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table52.addCell(cell);
        cell = new PdfPCell(new Phrase("PC141", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table52.addCell(cell);
        cell = new PdfPCell(new Phrase(Integer.toString(cantidad(lPresOdon, "PC141")), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table52.addCell(cell);

        cell = new PdfPCell(new Phrase("Caries Limitada al Esmalete - Resina Fotopolimerizable", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table52.addCell(cell);
        cell = new PdfPCell(new Phrase("PC134", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table52.addCell(cell);
        cell = new PdfPCell(new Phrase(Integer.toString(cantidad(lPresOdon, "PC134")), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table52.addCell(cell);

        cell = new PdfPCell(new Phrase("Caries de la Dentina - Resina Fotopolimerizable", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table52.addCell(cell);
        cell = new PdfPCell(new Phrase("PC135", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table52.addCell(cell);
        cell = new PdfPCell(new Phrase(Integer.toString(cantidad(lPresOdon, "PC134")), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table52.addCell(cell);

        cell = new PdfPCell(new Phrase("DIAS TRABAJADOS AL MES", DATO_FONT));
        cell.setColspan(2);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table52.addCell(cell);
        cell = new PdfPCell(new Phrase("", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table52.addCell(cell);

        cell = new PdfPCell(new Phrase("Caries de la Dentina - Amalgama", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table52.addCell(cell);
        cell = new PdfPCell(new Phrase("PC136", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table52.addCell(cell);
        cell = new PdfPCell(new Phrase(Integer.toString(cantidad(lPresOdon, "PC136")), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table52.addCell(cell);

        cell = new PdfPCell(new Phrase("HORAS TRABAJADAS AL MES", DATO_FONT));
        cell.setColspan(2);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table52.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table52.addCell(cell);

        document.add(table52);
    }

    public static int cantidad(java.util.List lPresOdon, String prestacion) {
        int valor = 0;

        for (int i = 0; i < lPresOdon.size(); i++) {
            Prestaciones datopac = (Prestaciones) lPresOdon.get(i);
            if (prestacion.equals(datopac.getPrestacion())) {
                valor = datopac.getCantidad();
            }
        }
        return valor;
    }

    public static String format(Date dia, String formato) {
        if (dia == null) {
            return "";
        }
        SimpleDateFormat formatDate = new SimpleDateFormat(formato);
        return formatDate.format(dia);
    }
}
