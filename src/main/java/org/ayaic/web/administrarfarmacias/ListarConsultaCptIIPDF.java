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

public class ListarConsultaCptIIPDF extends AbstractPdfView {

    private static final Font TITULO_FONT = new Font(Font.HELVETICA, 12, Font.BOLD, Color.black);
    private static final Font CABEZA_COLUMNA_FONT = new Font(Font.HELVETICA, 8, Font.ITALIC, Color.black);
    private static final Font DATO_FONT = new Font(Font.HELVETICA, 6, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_BOLD = new Font(Font.HELVETICA, 8, Font.BOLD, Color.black);
    private static final int MARGIN = 32;

    protected void buildPdfMetadata(Map model, Document document, HttpServletRequest request) {
        Localidades dl = (Localidades) model.get("localidades");
        document.setPageSize(PageSize.LEGAL.rotate());
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

        footer.setAlignment(Element.ALIGN_CENTER);
        footer.setBorder(Rectangle.NO_BORDER);
        document.setFooter(footer);
        // coloca el titulo de la pagina
        Image escudo = Image.getInstance("/opt/imagenes/lescudo.bmp");
        Paragraph p = new Paragraph("CONSOLIDADO DE PEDIDO TRIMESTRAL\nDE MEDICAMENTOS E INSUMOS (CPT)", new Font(Font.HELVETICA, 14, Font.BOLDITALIC, new Color(0, 0, 0)));
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

        cell = new PdfPCell(new Phrase("Red : " + dato.getRed(), DATO_FONT));
        cell.setBorder(Rectangle.NO_BORDER);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("SEDES : " + dato.getDepartamento() + "      Localidad:....................", DATO_FONT));
        cell.setBorder(Rectangle.NO_BORDER);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("TRIMESTRE : " + mes + "         " + "Año : " + gestion, DATO_FONT));
        cell.setBorder(Rectangle.NO_BORDER);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("subsector : ...........  ", DATO_FONT));
        cell.setBorder(Rectangle.NO_BORDER);
        table1.addCell(cell);

        document.add(table1);

        int NumColumns = 30;
        PdfPTable table = new PdfPTable(NumColumns);
        int[] fwidths = {4, 20, 10, 10, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5};
        table.setWidths(fwidths);
        table.setWidthPercentage(100);

        String sCampos[] = {"No", "MEDICAMENTO\nE INSUMOS", "FORMA\nFARMACEUTICA", "CONCEN-\nTRACION", "SIIS", "PROG", "VENTA", "TOTAL", "SIIS", "PROG", "VENTA", "TOTAL", "SIIS", "PROG", "VENTA", "TOTAL", "A. NEG.", "A. POST.", "SIIS", "PROG", "VENTA", "TOTAL"};
        // coloca la cabecera de titulos
        for (int i = 0; i < 4; i++) {
            cell = new PdfPCell(new Phrase(sCampos[i], CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setGrayFill(0.8f);
            table.addCell(cell);
        }

        cell = new PdfPCell(cuadrotitulo("A", "SALDO DISPONIBLE"));
        cell.setGrayFill(0.8f);
        cell.setColspan(4);
        table.addCell(cell);

        cell = new PdfPCell(cuadrotituloimm("B", "SIIS"));
        cell.setGrayFill(0.8f);
        cell.setColspan(4);
        table.addCell(cell);

        cell = new PdfPCell(cuadrotituloimm("C", "PROGRAMA"));
        cell.setGrayFill(0.8f);
        cell.setColspan(4);
        table.addCell(cell);

        cell = new PdfPCell(cuadrotituloimm("D", "VENTA"));
        cell.setGrayFill(0.8f);
        cell.setColspan(4);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("SUM PROM", CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.8f);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("MED", CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.8f);
        table.addCell(cell);

        cell = new PdfPCell(cuadrotitulo("E", "CANTIDAD MAXIMA"));
        cell.setGrayFill(0.8f);
        cell.setColspan(4);
        table.addCell(cell);

        cell = new PdfPCell(cuadrotitulo("F", "CANTIDAD A SOLICITAR"));
        cell.setGrayFill(0.8f);
        cell.setColspan(4);
        table.addCell(cell);
        // coloca el detalle de loS datos
        table.setHeaderRows(1);
        for (int i = 0; i < listImm.size(); i++) {
            double proma, proms, promp, promv, sumprom, met, cmss, cmsp, cmsv;
            Medicamentos datosM = (Medicamentos) listImm.get(i);

            cell = new PdfPCell(new Phrase(Integer.toString(i + 1), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase((datosM.getMedicamento().length() > 25) ? datosM.getMedicamento().substring(0, 25) : datosM.getMedicamento(), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase((datosM.getForma_far().length() > 12) ? datosM.getForma_far().substring(0, 12) : datosM.getForma_far(), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase((datosM.getConcentra().length() > 12) ? datosM.getConcentra().substring(0, 12) : datosM.getConcentra(), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase((datosM.getStocks() != 0) ? df.format(datosM.getStocks()) : "", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase((datosM.getStockp() != 0) ? df.format(datosM.getStockp()) : "", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase((datosM.getStockv() != 0) ? df.format(datosM.getStockv()) : "", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);
            proma = datosM.getStocks() + datosM.getStockp() + datosM.getStockv();
            cell = new PdfPCell(new Phrase(df.format(proma), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase((datosM.getAstocks() != 0) ? df.format(datosM.getAstocks()) : "", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase((datosM.getEstocks() != 0) ? df.format(datosM.getEstocks()) : "", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase((datosM.getCstocks() != 0) ? df.format(datosM.getCstocks()) : "", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);
            proms = Math.ceil((datosM.getAstocks() + datosM.getEstocks() + datosM.getCstocks()) / 3);
            cell = new PdfPCell(new Phrase(df.format(proms), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase((datosM.getAstockp() != 0) ? df.format(datosM.getAstockp()) : "", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase((datosM.getEstockp() != 0) ? df.format(datosM.getEstockp()) : "", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase((datosM.getCstockp() != 0) ? df.format(datosM.getCstockp()) : "", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);
            promp = Math.ceil((datosM.getAstockp() + datosM.getEstockp() + datosM.getCstockp()) / 3);
            cell = new PdfPCell(new Phrase(df.format(promp), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase((datosM.getAstockv() != 0) ? df.format(datosM.getAstockv()) : "", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase((datosM.getEstockv() != 0) ? df.format(datosM.getEstockv()) : "", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase((datosM.getCstockv() != 0) ? df.format(datosM.getCstockv()) : "", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);
            promv = Math.ceil((datosM.getAstockv() + datosM.getEstockv() + datosM.getCstockv()) / 3);
            cell = new PdfPCell(new Phrase(df.format(promv), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);

            // suma de promedios
            sumprom = promv + proms + promp;
            cell = new PdfPCell(new Phrase(df.format(sumprom), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);

            // meses existencia disponibles
            if (sumprom == 0) {
                met = 0;
            } else {
                met = Math.ceil(proma / sumprom);
            }
            cell = new PdfPCell(new Phrase(df.format(met), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);

            // Cantidad maxima a solicitar
            cmss = localidad.getMaximo() * proms;
            cell = new PdfPCell(new Phrase((cmss != 0) ? df.format(cmss) : "", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);
            cmsp = localidad.getMaximo() * promp;
            cell = new PdfPCell(new Phrase((cmsp != 0) ? df.format(cmsp) : "", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);
            cmsv = localidad.getMaximo() * promv;
            cell = new PdfPCell(new Phrase((cmsv != 0) ? df.format(cmsv) : "", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(df.format(cmsv + cmss + cmsp), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);

            // cantidad a solicitad
            cell = new PdfPCell(new Phrase((cmss - datosM.getStocks() != 0) ? df.format(cmss - datosM.getStocks()) : "", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase((cmsp - datosM.getStockp() != 0) ? df.format(cmsp - datosM.getStockp()) : "", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase((cmsv - datosM.getStockv() != 0) ? df.format(cmsv - datosM.getStockv()) : "", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(df.format(cmsv + cmss + cmsp - proma), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);
        }
        document.add(table);

        p = new Paragraph("Fecha de emision : " + format(new Date(), "dd/MM/yyyy"), DATO_FONT);
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
