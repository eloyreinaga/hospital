package org.ayaic.web.administrarhistoriales;

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
import org.ayaic.domain.Cuadernos;
import org.ayaic.domain.Departamentos;
import org.ayaic.domain.Localidades;
import org.ayaic.domain.Medicamentos;
import org.ayaic.domain.Provincias;
import org.ayaic.domain.Recetas;

public class ListarResumenMedicaPDF extends AbstractPdfView {

    private static final Font TITULO_FONT = new Font(Font.HELVETICA, 12, Font.BOLD, Color.black);
    private static final Font CABEZA_COLUMNA_FONT = new Font(Font.HELVETICA, 8, Font.ITALIC, Color.black);
    private static final Font DATO_FONT = new Font(Font.HELVETICA, 8, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_A = new Font(Font.HELVETICA, 6, Font.NORMAL, Color.black);
    private static final int MARGIN = 32;

    protected void buildPdfMetadata(Map model, Document document, HttpServletRequest request) {
        document.setPageSize(PageSize.LETTER);
        document.setMargins(20, 20, 10, 20);
    }

    protected void buildPdfDocument(Map model, Document document, PdfWriter writer, HttpServletRequest req, HttpServletResponse resp) throws DocumentException, BadElementException, IOException {

        Clientes dato = (Clientes) model.get("dato");
        java.util.List listarKardex = (java.util.List) model.get("listarKardex");
        java.util.List listarKardexSal = (java.util.List) model.get("listarKardexSal");
        Departamentos datoDep = (Departamentos) model.get("listaDep");
        Provincias datoProv = (Provincias) model.get("listaProv");
        Medicamentos medica = (Medicamentos) model.get("medica");
        Localidades datoLoc = (Localidades) model.get("listaLoc");
        Cuadernos listaIndi = (Cuadernos) model.get("ListaIndi");

        DecimalFormat df = new DecimalFormat("###,##0.00");
        Image escudo = Image.getInstance("/opt/imagenes/lescudo.bmp");
        Paragraph p = new Paragraph("INFORME GENERAL DE MEDICAMENTOS", new Font(Font.HELVETICA, 12, Font.BOLDITALIC, new Color(0, 0, 0)));
        p.setAlignment(Element.ALIGN_CENTER);
        //Image sumi = Image.getInstance("/opt/imagenes/sumi.bmp");
        int[] sumas = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        String sCampos[] = {"Establecimiento", "Codigo", "Medicamento", "FormaFar", "Saldo Anter", "Entradas", "Salidas", "Saldo Final"};
        double sum = 0.0;
        int suma = 0;

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

        cell = new PdfPCell(new Phrase("CAJA NACIONAL DE SALUD", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex.addCell(cell);

        cell = new PdfPCell(new Phrase("Medicamento : " + medica.getMedicamento() + "                 Forma Famaceutica : " + medica.getForma_far() + "                   Concenrracion : " + medica.getConcentra(), DATO_FONT));
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(3);
        tablex.addCell(cell);

        document.add(tablex);

        int NumColumns = 8;
        PdfPTable table = new PdfPTable(NumColumns);
        int[] fwidths = {35, 12, 48, 15, 15, 15, 15, 15}; // percentage
        table.setWidths(fwidths);
        table.setWidthPercentage(100);

        // coloca la cabecera de titulos
        for (int i = 0; i < sCampos.length; i++) {
            cell = new PdfPCell(new Phrase(sCampos[i], CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setGrayFill(0.8f);
            table.addCell(cell);
        }

        for (int i = 0; i < listarKardex.size(); i++) {
            Recetas KardexGen = (Recetas) listarKardex.get(i);

            cell = new PdfPCell(new Phrase((KardexGen.getCadena1().length() > 22) ? KardexGen.getCadena1().substring(0, 22) : KardexGen.getCadena1(), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(KardexGen.getCodsumi(), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase((KardexGen.getMedicamento().length() > 30) ? KardexGen.getMedicamento().substring(0, 30) : KardexGen.getMedicamento(), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(KardexGen.getForma_far(), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            int ddd = 0, fff = 0;
            cell = new PdfPCell(new Phrase("0", DATO_FONT));
            for (int j = 0; j < listarKardexSal.size(); j++) {
                Recetas KardexGenSal = (Recetas) listarKardexSal.get(j);
                ddd = KardexGen.getCod_esta();
                fff = KardexGenSal.getCod_esta();
                if (KardexGen.getCod_esta() == KardexGenSal.getCod_esta()) {
                    cell = new PdfPCell(new Phrase(Integer.toString(KardexGenSal.getSuma1() - KardexGenSal.getSuma2() + KardexGenSal.getSuma3()), DATO_FONT));
                    sumas[0] += (KardexGenSal.getSuma1() + KardexGenSal.getSuma2() + KardexGenSal.getSuma3());
                    suma = (KardexGenSal.getSuma1() - KardexGenSal.getSuma2() + KardexGenSal.getSuma3());
                }
            }
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(Integer.toString(KardexGen.getSuma1()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);
            sumas[1] += (KardexGen.getSuma1());

            cell = new PdfPCell(new Phrase(Integer.toString(KardexGen.getSuma2()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);
            sumas[2] += (KardexGen.getSuma2());

            cell = new PdfPCell(new Phrase(Integer.toString(suma + KardexGen.getSuma1() - KardexGen.getSuma2()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);
            sumas[3] += (suma + KardexGen.getSuma1() - KardexGen.getSuma2());

        }

        cell = new PdfPCell(new Phrase("TOTAL", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setColspan(4);
        table.addCell(cell);

        for (int j = 0; j < 4; j++) {
            cell = new PdfPCell(new Phrase(Integer.toString(sumas[j]), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);
        }

        cell = new PdfPCell(new Phrase((sum != 0) ? df.format(sum) : "", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setColspan(8);
        table.addCell(cell);

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
