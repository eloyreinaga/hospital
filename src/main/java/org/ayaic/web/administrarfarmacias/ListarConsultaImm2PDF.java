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

public class ListarConsultaImm2PDF extends AbstractPdfView {

    private static final Font TITULO_FONT = new Font(Font.HELVETICA, 12, Font.BOLD, Color.black);
    private static final Font CABEZA_COLUMNA_FONT = new Font(Font.HELVETICA, 8, Font.ITALIC, Color.black);
    private static final Font DATO_FONT = new Font(Font.HELVETICA, 7, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_P = new Font(Font.HELVETICA, 6, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_BOLD = new Font(Font.HELVETICA, 8, Font.BOLD, Color.black);
    private static final int MARGIN = 32;

    protected void buildPdfMetadata(Map model, Document document, HttpServletRequest request) {
        Localidades dl = (Localidades) model.get("localidades");
        document.setPageSize(PageSize.LEGAL.rotate());
        document.setMargins(dl.getIzquierda(), dl.getDerecha(), dl.getSuperior(), dl.getInferior());
    }

    protected void buildPdfDocument(Map model, Document document, PdfWriter writer, HttpServletRequest req, HttpServletResponse resp) throws Exception {

        java.util.List listImm = (java.util.List) model.get("listarDatosImm");
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
        int una;
        if (listImm.size() % 30 == 0) {
            una = 0;
        } else {
            una = 1;
        }

        for (int pag = 0; pag < listImm.size() / 30 + una; pag++) {

            Image escudo = Image.getInstance("/opt/imagenes/lescudo.bmp");
            Paragraph p = new Paragraph("INFORME \nDE MEDICAMENTOS PROGRAMAS", new Font(Font.HELVETICA, 14, Font.BOLDITALIC, new Color(0, 0, 0)));
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
            cell.setVerticalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            tablex.addCell(cell);
            cell = new PdfPCell(new Phrase("", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setBorder(Rectangle.NO_BORDER);
            tablex.addCell(cell);

            PdfPTable table1 = new PdfPTable(3);
            int[] fmwidths = {35, 35, 35}; // percentage
            table1.setWidths(fmwidths);
            table1.setWidthPercentage(100);

            cell = new PdfPCell(new Phrase("Servicio Departamental de Salud : " + dato.getDepartamento(), DATO_FONT));
            cell.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell);

            cell = new PdfPCell(new Phrase("Municipio : " + dato.getLocalidad(), DATO_FONT));
            cell.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell);

            cell = new PdfPCell(new Phrase("Establecimiento : " + dato.getEstablecimiento(), DATO_FONT));
            cell.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell);

            cell = new PdfPCell(new Phrase("Responsable : " + datop.getNombres(), DATO_FONT));
            cell.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell);

            cell = new PdfPCell(new Phrase("Red : " + dato.getRed(), DATO_FONT));
            cell.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell);

            cell = new PdfPCell(new Phrase("Mes : " + mes + "         " + "Año : " + gestion, DATO_FONT));
            cell.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell);

            int NumColumns = 22;
            PdfPTable table = new PdfPTable(NumColumns);
            int[] fwidths = {4, 25, 12, 12, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5};
            table.setWidths(fwidths);
            table.setWidthPercentage(100);

            String sCampos[] = {"No", "MEDICAMENTO\nE INSUMOS", "FORMA\nFARMACEUTICA", "CONCEN-\nTRACION", "SSPAM", "ESCOLAR", "OTRO", "TOTAL", "SSPAM", "ESCOLAR", "OTRO", "TOTAL", "SSPAM", "ESCOLAR", "OTRO", "TOTAL", "A. NEG.", "A. POST.", "SSPAM", "ESCOLAR", "OTRO", "TOTAL"};
            cell = new PdfPCell(tablex);
            cell.setColspan(22);
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);

            cell = new PdfPCell(table1);
            cell.setColspan(22);
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);

            // coloca la cabecera de titulos
            for (int i = 0; i < 4; i++) {
                cell = new PdfPCell(new Phrase(sCampos[i], CABEZA_COLUMNA_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);

                table.addCell(cell);
            }

            cell = new PdfPCell(cuadrotitulo("A", "SALDO INICIO DEL MES"));

            cell.setColspan(4);
            table.addCell(cell);
            cell = new PdfPCell(cuadrotitulo("B", "RECIBIDO EN EL MES"));

            cell.setColspan(4);
            table.addCell(cell);
            cell = new PdfPCell(cuadrotitulo("C", "CONSUMO DEL MES"));

            cell.setColspan(4);
            table.addCell(cell);

            table1 = new PdfPTable(2);
            table1.setWidthPercentage(100);

            cell = new PdfPCell(new Phrase("E", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(2);
            table1.addCell(cell);

            cell = new PdfPCell(new Phrase("AJUSTES", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(2);
            table1.addCell(cell);

            cell = new PdfPCell(new Phrase("Positivos(+)", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table1.addCell(cell);

            cell = new PdfPCell(new Phrase("Negativos(-)", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table1.addCell(cell);

            cell = new PdfPCell(table1);
            cell.setColspan(2);
            table.addCell(cell);

            cell = new PdfPCell(cuadrotitulo("D", "SALDO FINAL DEL MES"));
            cell.setColspan(4);
            table.addCell(cell);

            // coloca el detalle de loS datos
            table.setHeaderRows(1);
            table.setHeaderRows(2);
            table.setHeaderRows(3);
            for (int i = pag * 30 + 0; i < pag * 30 + 30 && i < listImm.size(); i++) {

                Medicamentos datosM = (Medicamentos) listImm.get(i);

                cell = new PdfPCell(new Phrase(Integer.toString(i + 1), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((datosM.getMedicamento().length() > 33) ? datosM.getMedicamento().substring(0, 33) : datosM.getMedicamento(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((datosM.getForma_far().length() > 15) ? datosM.getForma_far().substring(0, 15) : datosM.getForma_far(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((datosM.getConcentra().length() > 15) ? datosM.getConcentra().substring(0, 15) : datosM.getConcentra(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((datosM.getAstocks() != 0) ? df.format(datosM.getAstocks()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((datosM.getAstockp() != 0) ? df.format(datosM.getAstockp()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((datosM.getAstockv() != 0) ? df.format(datosM.getAstockv()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(df.format(datosM.getAstocks() + datosM.getAstockp() + datosM.getAstockv()), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((datosM.getEstocks() != 0) ? df.format(datosM.getEstocks()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((datosM.getEstockp() != 0) ? df.format(datosM.getEstockp()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((datosM.getEstockv() != 0) ? df.format(datosM.getEstockv()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(df.format(datosM.getEstocks() + datosM.getEstockp() + datosM.getEstockv()), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((datosM.getCstocks() != 0) ? df.format(datosM.getCstocks()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((datosM.getCstockp() != 0) ? df.format(datosM.getCstockp()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((datosM.getCstockv() != 0) ? df.format(datosM.getCstockv()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(df.format(datosM.getCstocks() + datosM.getCstockp() + datosM.getCstockv()), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((datosM.getApost() != 0) ? df.format(datosM.getApost()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((datosM.getAneg() != 0) ? df.format(datosM.getAneg()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
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

                cell = new PdfPCell(new Phrase(df.format(datosM.getStocks() + datosM.getStockp() + datosM.getStockv()), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(cell);

            }
            for (int i = listImm.size(); i < pag * 30 + 30; i++) {
                cell = new PdfPCell(new Phrase(Integer.toString(i + 1), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(cell);
                for (int j = 1; j < 22; j++) {
                    cell = new PdfPCell(new Phrase("", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);
                }
            }
            cell = new PdfPCell(new Phrase("Observaciones : ___________________________________________________________________________", DATO_FONT));
            cell.setColspan(22);
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Elaborado por : " + dato.getNombres(), DATO_FONT));
            cell.setColspan(12);
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Fecha de emision : " + format(new Date(), "dd/MM/yyyy"), DATO_FONT));
            cell.setColspan(10);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Firma : _________________________________________", DATO_FONT));
            cell.setColspan(22);
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);

            document.add(table);
            document.newPage();
        }
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
        cell = new PdfPCell(new Phrase("SSPAM", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1.addCell(cell);
        cell = new PdfPCell(new Phrase("Escolar", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1.addCell(cell);
        cell = new PdfPCell(new Phrase("Otros", DATO_FONT));
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
