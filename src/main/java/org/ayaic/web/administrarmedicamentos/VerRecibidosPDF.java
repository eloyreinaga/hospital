package org.ayaic.web.administrarmedicamentos;

import java.awt.Color;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.view.document.AbstractPdfView;
import java.util.*;
import java.text.*;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Medicamentos;
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.Personas;

public class VerRecibidosPDF extends AbstractPdfView {

    private static final Font TITULO_FONT = new Font(Font.HELVETICA, 12, Font.BOLD, Color.black);
    private static final Font CABEZA_COLUMNA_FONT = new Font(Font.HELVETICA, 8, Font.ITALIC, Color.black);
    private static final Font DATO_FONT = new Font(Font.HELVETICA, 8, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_BOLD = new Font(Font.HELVETICA, 8, Font.BOLD, Color.black);
    private static final int MARGIN = 32;

    protected void buildPdfMetadata(Map model, Document document, HttpServletRequest request) {
        document.setPageSize(PageSize.LETTER);
    }

    protected void buildPdfDocument(Map model, Document document, PdfWriter writer, HttpServletRequest req, HttpServletResponse resp) throws Exception {

        java.util.List lFopos = (java.util.List) model.get("listapago");
        java.util.List programas = (java.util.List) model.get("listarProg");

        Clientes dato = (Clientes) model.get("dato");
        Personas persona = (Personas) model.get("persona");
        String fecha = (String) model.get("dFechafin1");
        // coloca el titulo de la pagina
        Image escudo = Image.getInstance("/opt/imagenes/lescudo.bmp");
        Paragraph p = new Paragraph("LISTA MEDICAMENTOS RECIBIDOS PROVEEDORAS", new Font(Font.HELVETICA, 14, Font.BOLDITALIC, new Color(0, 0, 0)));
        p.setAlignment(Element.ALIGN_CENTER);
        //Image sumi = Image.getInstance("/opt/imagenes/sumi.bmp");
        DecimalFormat df = new DecimalFormat("###,##0.00");
        DecimalFormat dfx = new DecimalFormat("###,##0");
        double tot = 0;

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

        cell = new PdfPCell(new Phrase("SEDES : " + dato.getDepartamento(), DATO_FONT));
        cell.setBorder(Rectangle.NO_BORDER);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("Mes : " + fecha.substring(5, 7) + "         " + "Año : " + fecha.substring(0, 4), DATO_FONT));
        cell.setBorder(Rectangle.NO_BORDER);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("Responsable   : ", DATO_FONT));
        cell.setBorder(Rectangle.NO_BORDER);
        table1.addCell(cell);

        document.add(table1);

        int NumColumns = 7;
        PdfPTable table = new PdfPTable(NumColumns);
        int[] fwidths = {8, 12, 30, 10, 10, 10, 10};
        table.setWidths(fwidths);
        table.setWidthPercentage(100);

        String sCampos[] = {"Nro", "Fecha", "Proveedor / Empresa / Linea", "Fuente\nFinanciamiento", "No REQ", "NOM. DOC", "Monto"};
        // coloca la cabecera de titulos

        for (int i = 0; i < NumColumns; i++) {
            cell = new PdfPCell(new Phrase(sCampos[i], CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setGrayFill(0.8f);
            table.addCell(cell);
        }

        // coloca el detalle de loS datos
        for (int i = 0; i < lFopos.size(); i++) {

            Pacientes datopac = (Pacientes) lFopos.get(i);

            cell = new PdfPCell(new Phrase(Integer.toString(datopac.getId_factura()), CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(format(datopac.getFec_registro(), "dd/MM/yyyy HH:mm"), CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(datopac.getNombres(), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            if ("V".equals(datopac.getTipo())) {
                cell = new PdfPCell(new Phrase("VENTA", DATO_FONT));
            } else if ("S".equals(datopac.getTipo())) {
                cell = new PdfPCell(new Phrase("SIIS", DATO_FONT));
            } else {
                for (int k = 0; k < programas.size(); k++) {
                    Medicamentos prog = (Medicamentos) programas.get(k);
                    if (datopac.getId_persona() == prog.getId_programa()) {
                        cell = new PdfPCell(new Phrase(prog.getConcentra(), DATO_FONT));
                    }
                }
            }

            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(datopac.getNit(), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(datopac.getNum_cladoc(), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);

            ///cell = new PdfPCell(new Phrase(Integer.toString(datopac.getId_factura()), DATO_FONT));
            ///cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            ///table.addCell(cell);
            cell = new PdfPCell(new Phrase(df.format(datopac.getPrecio_total()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);

            tot += datopac.getPrecio_total();
        }

        cell = new PdfPCell(new Phrase("Total  [Bs]  :                " + df.format(tot), DATO_FONT_BOLD));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setColspan(7);
        table.addCell(cell);

        document.add(table);
        /*
     p = new Paragraph("Fecha de emision : "+format(new Date(),"dd/MM/yyyy") , DATO_FONT );
     document.add(p);
     p = new Paragraph("Nombre completo responsable del llenado : "+ dato.getNombres()+"         Firma:_______________________", DATO_FONT);
     document.add(p);
     p = new Paragraph("El arriba firmante certifica la veracidad de la informacion declarada en este formulario legal.", DATO_FONT);
     document.add(p);
     p = new Paragraph("Nota: - Llenar con letra de imprenta.", DATO_FONT);
     document.add(p);
     p = new Paragraph("      - Este documento debe tener el sello del establecimiento.                                   Sello", DATO_FONT);
     document.add(p);
         */
    }

    public static String format(Date dia, String formato) {
        if (dia == null) {
            return "";
        }
        SimpleDateFormat formatDate = new SimpleDateFormat(formato);
        return formatDate.format(dia);
    }
}
