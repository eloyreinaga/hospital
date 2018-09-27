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
import org.ayaic.domain.Historiales;
import org.ayaic.domain.Localidades;
import org.ayaic.domain.Medicamentos;
import org.ayaic.domain.Provincias;

public class ListarMicronutrientesPDF extends AbstractPdfView {

    private static final Font TITULO_FONT = new Font(Font.HELVETICA, 12, Font.BOLD, Color.black);
    private static final Font CABEZA_COLUMNA_FONT = new Font(Font.HELVETICA, 8, Font.ITALIC, Color.black);
    private static final Font DATO_FONT = new Font(Font.HELVETICA, 8, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_A = new Font(Font.HELVETICA, 6, Font.NORMAL, Color.black);
    private static final int MARGIN = 32;

    protected void buildPdfMetadata(Map model, Document document, HttpServletRequest request) {
        document.setPageSize(PageSize.LETTER);
        document.setMargins(20, 20, 20, 20);
    }

    protected void buildPdfDocument(Map model, Document document, PdfWriter writer, HttpServletRequest req, HttpServletResponse resp) throws BadElementException, IOException, DocumentException {

        Clientes dato = (Clientes) model.get("dato");
        java.util.List KardexPac = (java.util.List) model.get("listaPacientes");
        Departamentos datoDep = (Departamentos) model.get("listaDep");
        Provincias datoProv = (Provincias) model.get("listaProv");
        Medicamentos medica = (Medicamentos) model.get("medica");
        Localidades datoLoc = (Localidades) model.get("listaLoc");
        Cuadernos listaIndi = (Cuadernos) model.get("ListaIndi");

        DecimalFormat df = new DecimalFormat("###,##0.00");
        Image escudo = Image.getInstance("/opt/imagenes/lescudo.bmp");
        Paragraph p = new Paragraph("INFORME MENSUAL DE MICRONUTRIENTES", new Font(Font.HELVETICA, 12, Font.BOLDITALIC, new Color(0, 0, 0)));
        p.setAlignment(Element.ALIGN_CENTER);
        String sCampos[] = {"Nro", "FECHA", "HCL", "Nombres \nApellidos", "Direccion", "Nro.\n Registro", "Nro.\n Receta", "Nro.\n Bolsas"};
        double sum = 0.0;

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

        cell = new PdfPCell(new Phrase("", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex.addCell(cell);

        cell = new PdfPCell(new Phrase("Establecimiento : " + dato.getEstablecimiento(), new Font(Font.HELVETICA, 12, Font.BOLDITALIC, new Color(0, 0, 0))));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex.addCell(cell);

        cell = new PdfPCell(new Phrase("Medicamento : " + medica.getMedicamento() + "                 Forma Famaceutica : " + medica.getForma_far() + "                   Concenrracion : " + medica.getConcentra(), DATO_FONT));
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(3);
        tablex.addCell(cell);

        document.add(tablex);

        int NumColumns = 8;
        PdfPTable table = new PdfPTable(NumColumns);
        int[] fwidths = {7, 12, 13, 40, 40, 15, 15, 10}; // percentage
        table.setWidths(fwidths);
        table.setWidthPercentage(100);

        // coloca la cabecera de titulos
        for (int i = 0; i < sCampos.length; i++) {
            cell = new PdfPCell(new Phrase(sCampos[i], CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setGrayFill(0.8f);
            table.addCell(cell);
        }

        for (int i = 0; i < KardexPac.size(); i++) {

            Historiales KardexPaciente = (Historiales) KardexPac.get(i);

            cell = new PdfPCell(new Phrase(Integer.toString(i + 1), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(format(KardexPaciente.getFecha(), "dd/MM/yy"), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(Integer.toString(KardexPaciente.getHcl()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(KardexPaciente.getNombres(), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(KardexPaciente.getDireccion(), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(KardexPaciente.getRegistro(), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(KardexPaciente.getCodigo(), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase((KardexPaciente.getPeso() != 0) ? df.format(KardexPaciente.getPeso()) : "", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);
            sum += KardexPaciente.getPeso();

        }

        cell = new PdfPCell(new Phrase("TOTAL", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setColspan(7);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase((sum != 0) ? df.format(sum) : "", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        table.addCell(cell);

        document.add(table);

        // coloca el detalle de los datos
        //   table.setHeaderRows(1);
    }

    public static String format(Date dia, String formato) {
        if (dia == null) {
            return "";
        }
        SimpleDateFormat formatDate = new SimpleDateFormat(formato);
        return formatDate.format(dia);
    }
}
