package org.ayaic.web.administrarfarmacias;

import java.awt.Color;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.view.document.AbstractPdfView;
import java.util.*;
import java.text.*;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Localidades;
import org.ayaic.domain.Medicamentos;
import org.ayaic.domain.Personas;

public class ListarConsultaPsicoPDF extends AbstractPdfView {

    private static final Font TITULO_FONT = new Font(Font.HELVETICA, 12, Font.BOLD, Color.black);
    private static final Font CABEZA_COLUMNA_FONT = new Font(Font.HELVETICA, 8, Font.ITALIC, Color.black);
    private static final Font DATO_FONT = new Font(Font.HELVETICA, 10, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_BOLD = new Font(Font.HELVETICA, 8, Font.BOLD, Color.black);
    private static final int MARGIN = 32;

    protected void buildPdfMetadata(Map model, Document document, HttpServletRequest request) {
        Localidades dl = (Localidades) model.get("localidades");
        document.setPageSize(PageSize.LETTER.rotate());
        document.setMargins(dl.getIzquierda(), dl.getDerecha(), dl.getSuperior(), dl.getInferior());
    }

    protected void buildPdfDocument(Map model, Document document, PdfWriter writer, HttpServletRequest req, HttpServletResponse resp) throws Exception {

        java.util.List listImm = (java.util.List) model.get("listarDatosImm");
        Localidades localidad = (Localidades) model.get("localidades");
        Clientes dato = (Clientes) model.get("dato");
        Personas datop = (Personas) model.get("datosJMed");
        String mes = (String) model.get("mes");
        String gestion = (String) model.get("gestion");
        DecimalFormat df = new DecimalFormat("###,##0");
        HeaderFooter footer = new HeaderFooter(new Phrase("Pagina No. "), new Phrase("."));
        String mes1 = "Enero", mes2 = "Febrero", mes3 = "Marzo";

        footer.setAlignment(Element.ALIGN_CENTER);
        footer.setBorder(Rectangle.NO_BORDER);
        document.setFooter(footer);
        // coloca el titulo de la pagina
        Image escudo = Image.getInstance("/opt/imagenes/lescudo.bmp");
        Paragraph p = new Paragraph("INFORME DE CONSUMO DE MEDICAMENTOS PSICOTROPICOS\nY ESTUPEFACIENTES TRIMESTRAL", new Font(Font.HELVETICA, 14, Font.BOLDITALIC, new Color(0, 0, 0)));
        p.setAlignment(Element.ALIGN_CENTER);

        PdfPCell cell;

        PdfPTable tablex = new PdfPTable(3);
        int[] fxwidths = {15, 70, 15}; // percentage
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
        cell = new PdfPCell(new Phrase("", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex.addCell(cell);

        document.add(tablex);

        PdfPTable table1 = new PdfPTable(2);
        int[] fmwidths = {50, 50}; // percentage
        table1.setWidths(fmwidths);
        table1.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("Establecimiento : " + dato.getEstablecimiento(), DATO_FONT));
        cell.setBorder(Rectangle.NO_BORDER);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("Municipio : " + dato.getLocalidad(), DATO_FONT));
        cell.setBorder(Rectangle.NO_BORDER);
        table1.addCell(cell);

        //cell = new PdfPCell(new Phrase("Red : "+dato.getRed() , DATO_FONT));
        //cell.setBorder(Rectangle.NO_BORDER);
        //table1.addCell(cell);
        //cell = new PdfPCell(new Phrase("SEDES : " +dato.getDepartamento()+"      Localidad:....................", DATO_FONT));
        //cell.setBorder(Rectangle.NO_BORDER);
        //table1.addCell(cell);
        cell = new PdfPCell(new Phrase("TRIMESTRE : " + mes + "         " + "Año : " + gestion, DATO_FONT));
        cell.setBorder(Rectangle.NO_BORDER);
        table1.addCell(cell);

        if ("2".equals(mes)) {
            mes1 = "Abril";
            mes2 = "Mayo";
            mes3 = "Junio";
        }
        if ("3".equals(mes)) {
            mes1 = "Julio";
            mes2 = "Agosto";
            mes3 = "Septiembre";
        }
        if ("4".equals(mes)) {
            mes1 = "Octubre";
            mes2 = "Noviembre";
            mes3 = "Diciembre";
        }

        cell = new PdfPCell(new Phrase(mes1 + ", " + mes2 + " y " + mes3 + "  " + gestion, DATO_FONT));
        cell.setBorder(Rectangle.NO_BORDER);
        table1.addCell(cell);

        document.add(table1);

        int NumColumns = 13;
        PdfPTable table = new PdfPTable(NumColumns);
        int[] fwidths = {4, 6, 20, 6, 10, 5, 5, 5, 5, 5, 5, 5, 5};
        table.setWidths(fwidths);
        table.setWidthPercentage(100);

        String sCampos[] = {"No", "COD", "MEDICAMENTO\nE INSUMOS", "FORMA\nFARMA", "CONCEN-\nTRACION", "Saldo\nAnterior", "Ingreso\nTriemstre", "Otros\nIngresos", mes1, mes2, mes3, "Total\nConsumo", "Saldo\nActual"};
        // coloca la cabecera de titulos
        for (int i = 0; i < NumColumns; i++) {
            cell = new PdfPCell(new Phrase(sCampos[i], CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            // cell.setGrayFill(0.8f);
            table.addCell(cell);
        }

        // coloca el detalle de loS datos
        table.setHeaderRows(1);
        for (int i = 0; i < listImm.size(); i++) {
            double proma, proms, promp, promv, sumprom, met, cmss, cmsp, cmsv;
            Medicamentos datosM = (Medicamentos) listImm.get(i);

            cell = new PdfPCell(new Phrase(Integer.toString(i + 1), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(datosM.getCodsumi(), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase((datosM.getMedicamento().length() > 25) ? datosM.getMedicamento().substring(0, 25) : datosM.getMedicamento(), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase((datosM.getForma_far().length() > 5) ? datosM.getForma_far().substring(0, 5) : datosM.getForma_far(), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase((datosM.getConcentra().length() > 12) ? datosM.getConcentra().substring(0, 12) : datosM.getConcentra(), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            proma = datosM.getStocks() + datosM.getStockp() + datosM.getStockv();
            cell = new PdfPCell(new Phrase(df.format(datosM.getStockp() + datosM.getStocks() + datosM.getStockv()), DATO_FONT)); ///para obrero empieza con 0
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);
            ////Ingresos

            cell = new PdfPCell(new Phrase(df.format(datosM.getSuma1() + datosM.getSuma2() + datosM.getSuma3()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);
            ////otros ingresos
            cell = new PdfPCell(new Phrase(df.format(datosM.getSuma4() + datosM.getSuma5() + datosM.getSuma6()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);
            ////primer mes enero
            cell = new PdfPCell(new Phrase(df.format(datosM.getAstockp()), DATO_FONT));////+datosM.getSuma1() se quita 12/04/2017
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);
            ////segundo mes febrero
            cell = new PdfPCell(new Phrase(df.format(datosM.getEstockp()), DATO_FONT));////+datosM.getSuma2() se quita 12/04/2017
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);
            ////tercer mes
            cell = new PdfPCell(new Phrase(df.format(datosM.getCstockp()), DATO_FONT)); ////+datosM.getSuma3() se quita 12/04/2017
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);
            ////total consumo
            cell = new PdfPCell(new Phrase(df.format(datosM.getAstockp() + datosM.getEstockp() + datosM.getCstockp()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(df.format(proma + datosM.getSumab1() + datosM.getSumab2() + datosM.getSumab3() - datosM.getAstockp() - datosM.getEstockp() - datosM.getCstockp() + datosM.getSuma1() + datosM.getSuma2() + datosM.getSuma3() + datosM.getSuma4() + datosM.getSuma5() + datosM.getSuma6()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);
        }
        document.add(table);

        p = new Paragraph("Fecha de emision : " + format(new Date(), "dd/MM/yyyy HH:mm"), DATO_FONT);
        document.add(p);
        p = new Paragraph("Nombre completo responsable del llenado : " + dato.getNombres() + "         Firma:_______________________", DATO_FONT);
        document.add(p);
        p = new Paragraph("El arriba firmante certifica la veracidad de la informacion declarada en este formulario legal.", DATO_FONT);
        document.add(p);
        p = new Paragraph("Nota: - Llenar con letra de imprenta.", DATO_FONT);
        document.add(p);
        p = new Paragraph("      - Este documento debe tener el sello del establecimiento.                                   Sello", DATO_FONT);
        document.add(p);

    }

    public static PdfPTable cuadrotitulo(String letra, String cadena) {
        // la primera fila

        PdfPCell cell;
        PdfPTable table1 = new PdfPTable(4);
        table1.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase(letra, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(4);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase(cadena, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(4);
        table1.addCell(cell);
        cell = new PdfPCell(new Phrase("SIIS\nI", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1.addCell(cell);
        cell = new PdfPCell(new Phrase("PROG\nII", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1.addCell(cell);
        cell = new PdfPCell(new Phrase("VENTA\nIII", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1.addCell(cell);
        cell = new PdfPCell(new Phrase("TOTAL\nI+II+III", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1.addCell(cell);
        return table1;
    }

    public static PdfPTable cuadrotituloimm(String letra, String cadena) {
        // la primera fila

        PdfPCell cell;
        PdfPTable table1 = new PdfPTable(4);
        table1.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase(letra, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(4);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase(cadena, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(4);
        table1.addCell(cell);
        cell = new PdfPCell(new Phrase("IMM\nI", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1.addCell(cell);
        cell = new PdfPCell(new Phrase("IMM\nII", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1.addCell(cell);
        cell = new PdfPCell(new Phrase("IMM\nIII", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1.addCell(cell);
        cell = new PdfPCell(new Phrase("TOTAL\nI+II+III", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1.addCell(cell);
        return table1;
    }

    public static String format(Date dia, String formato) {
        if (dia == null) {
            return "";
        }
        SimpleDateFormat formatDate = new SimpleDateFormat(formato);
        return formatDate.format(dia);
    }
}
