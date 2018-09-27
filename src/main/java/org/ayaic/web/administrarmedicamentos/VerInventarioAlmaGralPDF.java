package org.ayaic.web.administrarmedicamentos;

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
import org.ayaic.domain.Farmacias;
import org.ayaic.domain.Medicamentos;
import org.ayaic.domain.Personas;

public class VerInventarioAlmaGralPDF extends AbstractPdfView {

    private static final Font TITULO_FONT = new Font(Font.HELVETICA, 12, Font.BOLD, Color.black);
    private static final Font CABEZA_COLUMNA_FONT = new Font(Font.HELVETICA, 8, Font.ITALIC, Color.black);
    private static final Font DATO_FONT = new Font(Font.HELVETICA, 8, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_BOLD = new Font(Font.HELVETICA, 8, Font.BOLD, Color.black);
    private static final int MARGIN = 32;

    protected void buildPdfMetadata(Map model, Document document, HttpServletRequest request) {
        document.setPageSize(PageSize.LETTER.rotate());
    }

    protected void buildPdfDocument(Map model, Document document, PdfWriter writer, HttpServletRequest req, HttpServletResponse resp) throws BadElementException, IOException, DocumentException {

        java.util.List listamed = (java.util.List) model.get("listarMedicamentos");
        java.util.List listamedgral = (java.util.List) model.get("listarMedicamentosGral");
        java.util.List listafar = (java.util.List) model.get("listafarAsig");

        Clientes dato = (Clientes) model.get("dato");
        Personas persona = (Personas) model.get("persona");
        String fecha = (String) model.get("dFechafin1");
        String fecha2 = (String) model.get("dFechafin2");
        DecimalFormat df = new DecimalFormat("###,##0");
        DecimalFormat dfx = new DecimalFormat("###,##0.0");
        Image escudo = Image.getInstance("/opt/imagenes/lescudo.bmp");
        Paragraph p = new Paragraph("INFORME DETALLE MEDICAMENTOS", new Font(Font.HELVETICA, 14, Font.BOLDITALIC, new Color(0, 0, 0)));
        p.setAlignment(Element.ALIGN_CENTER);
        //Image sumi = Image.getInstance("/opt/imagenes/sumi.bmp");
        double sumatot = 0;

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

        cell = new PdfPCell(new Phrase("Fecha Reporte :  ", DATO_FONT));
        cell.setBorder(Rectangle.NO_BORDER);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("Responsable   : " + persona.getNombres(), DATO_FONT));
        cell.setBorder(Rectangle.NO_BORDER);
        table1.addCell(cell);

        document.add(table1);

        int num = listafar.size();
        int NumColumns = 5 + num + 1;
        PdfPTable table = new PdfPTable(NumColumns);

        switch (num) {
            case 1:
                int[] fwidths1 = {8, 20, 40, 20, 20, 20, 20};
                table.setWidths(fwidths1);
                break;
            case 2:
                int[] fwidths2 = {8, 20, 40, 20, 20, 20, 20, 20};
                table.setWidths(fwidths2);
                break;
            case 3:
                int[] fwidths3 = {8, 20, 40, 20, 20, 20, 20, 20, 20};
                table.setWidths(fwidths3);
                break;
            case 4:
                int[] fwidths4 = {8, 20, 40, 20, 20, 20, 20, 20, 20, 20};
                table.setWidths(fwidths4);
                break;
            case 5:
                int[] fwidths5 = {8, 20, 40, 20, 20, 20, 20, 20, 20, 20, 20};
                table.setWidths(fwidths5);
                break;
            case 6:
                int[] fwidths6 = {8, 20, 40, 20, 20, 20, 20, 20, 20, 20, 20, 20};
                table.setWidths(fwidths6);
                break;
            case 7:
                int[] fwidths7 = {8, 20, 40, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20};
                table.setWidths(fwidths7);
                break;
            case 8:
                int[] fwidths8 = {8, 20, 40, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20};
                table.setWidths(fwidths8);
                break;
            case 9:
                int[] fwidths9 = {8, 20, 40, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20};
                table.setWidths(fwidths9);
                break;
            case 10:
                int[] fwidths10 = {8, 20, 40, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20};
                table.setWidths(fwidths10);
                break;
            default:
                int[] fwidths0 = {8, 20, 40, 20, 20};
                table.setWidths(fwidths0);
                break;
        }

        table.setWidthPercentage(100);

        String sCampos[] = {"Nro", "Codigo", "Medicamnto", "Forma Far", "Concentracion"};
        // coloca la cabecera de titulos

        for (int i = 0; i < NumColumns - num - 1; i++) {
            cell = new PdfPCell(new Phrase(sCampos[i], CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setGrayFill(0.8f);
            table.addCell(cell);
        }
        for (int i = 0; i < listafar.size(); i++) {
            Farmacias datof = (Farmacias) listafar.get(i);
            cell = new PdfPCell(new Phrase(datof.getFarmacia(), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setGrayFill(0.8f);
            table.addCell(cell);
        }
        cell = new PdfPCell(new Phrase("Total", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.8f);
        table.addCell(cell);
        // coloca el detalle de loS datos
        for (int i = 0; i < listamedgral.size(); i++) {

            Medicamentos datos = (Medicamentos) listamedgral.get(i);

            cell = new PdfPCell(new Phrase(Integer.toString(i + 1), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(datos.getCodsumi(), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase((datos.getMedicamento().length() > 20) ? datos.getMedicamento().substring(0, 20) : datos.getMedicamento(), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase((datos.getForma_far().length() > 15) ? datos.getForma_far().substring(0, 15) : datos.getForma_far(), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase((datos.getConcentra().length() > 12) ? datos.getConcentra().substring(0, 12) : datos.getConcentra(), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            sumatot = 0;
            for (int k = 0; k < listamed.size(); k++) {
                Medicamentos listamedi = (Medicamentos) listamed.get(k);
                for (int l = 0; l < listafar.size(); l++) {
                    Farmacias listafa = (Farmacias) listafar.get(l);
                    if (listamedi.getId_farmacia() == listafa.getId_farmacia() && datos.getId_medicamento() == listamedi.getId_medicamento()) {
                        cell = new PdfPCell(new Phrase(df.format(listamedi.getB1()), DATO_FONT));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table.addCell(cell);
                        sumatot += listamedi.getB1();
                    }
                }
            }

            cell = new PdfPCell(new Phrase(df.format(sumatot), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
        }

        document.add(table);

    }

    public static String format(Date dia, String formato) {
        if (dia == null) {
            return "";
        }
        SimpleDateFormat formatDate = new SimpleDateFormat(formato);
        return formatDate.format(dia);
    }
}
