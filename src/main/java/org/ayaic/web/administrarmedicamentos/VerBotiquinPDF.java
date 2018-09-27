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
import org.ayaic.domain.Recetas;

public class VerBotiquinPDF extends AbstractPdfView {

    private static final Font TITULO_FONT = new Font(Font.HELVETICA, 12, Font.BOLD, Color.black);
    private static final Font CABEZA_COLUMNA_FONT = new Font(Font.HELVETICA, 8, Font.ITALIC, Color.black);
    private static final Font DATO_FONT = new Font(Font.HELVETICA, 8, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_R = new Font(Font.HELVETICA, 8, Font.NORMAL, Color.red);
    private static final Font DATO_FONT_D = new Font(Font.HELVETICA, 22, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_BOLD = new Font(Font.HELVETICA, 10, Font.BOLD, Color.black);
    private static final int MARGIN = 32;

    protected void buildPdfMetadata(Map model, Document document, HttpServletRequest request) {
        document.setPageSize(PageSize.LETTER);
    }

    protected void buildPdfDocument(Map model, Document document, PdfWriter writer, HttpServletRequest req, HttpServletResponse resp) throws Exception {

        java.util.List lFopos = (java.util.List) model.get("listarKardex");
        java.util.List programas = (java.util.List) model.get("listarProg");

        Clientes dato = (Clientes) model.get("dato");
        Personas persona = (Personas) model.get("persona");
        Pacientes datos = (Pacientes) model.get("datos");
        String fecha = (String) model.get("dFechafin1");
        // coloca el titulo de la pagina
        Image escudo = Image.getInstance("/opt/imagenes/lescudo.bmp");
        Paragraph p = new Paragraph("REPORTE BOTIQUIN DE SERVICIOS", new Font(Font.HELVETICA, 14, Font.BOLDITALIC, new Color(0, 0, 0)));
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

        cell = new PdfPCell(new Phrase("No: " + datos.getId_factura(), DATO_FONT_D));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex.addCell(cell);

        document.add(tablex);

        PdfPTable table1 = new PdfPTable(3);
        int[] fmwidths = {45, 45, 10}; // percentage
        table1.setWidths(fmwidths);
        table1.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("Establecimiento : " + dato.getEstablecimiento(), DATO_FONT));
        cell.setBorder(Rectangle.NO_BORDER);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("Municipio : " + dato.getLocalidad(), DATO_FONT));
        cell.setBorder(Rectangle.NO_BORDER);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT));
        cell.setBorder(Rectangle.NO_BORDER);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("Fecha : " + (format(datos.getFec_registro(), "dd/MM/yyyy")), DATO_FONT));
        cell.setBorder(Rectangle.NO_BORDER);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase((datos.getNombres().length() > 30) ? "Distribuidora / Proveedor   : " + datos.getNombres().substring(0, 30) : "Distribuidora / Proveedor   : " + datos.getNombres(), DATO_FONT));
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(2);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase(" No.  : " + datos.getNum_cladoc() + "                 No. Resp : " + datos.getNit(), DATO_FONT));
        cell.setBorder(Rectangle.NO_BORDER);
        table1.addCell(cell);

        if ("V".equals(datos.getTipo())) {
            cell = new PdfPCell(new Phrase("Fte/Reposicion :   VENTA", DATO_FONT));
        } else if ("S".equals(datos.getTipo())) {
            cell = new PdfPCell(new Phrase("Fte/Reposicion :   SUMI", DATO_FONT));
        } else {
            for (int i = 0; i < programas.size(); i++) {
                Medicamentos prog = (Medicamentos) programas.get(i);
                if (datos.getId_persona() == prog.getId_programa()) {
                    cell = new PdfPCell(new Phrase("Fte/Reposicion :   PROGR.  " + prog.getConcentra(), DATO_FONT));
                }
            }
        }

        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(2);
        table1.addCell(cell);

        document.add(table1);

        int NumColumns = 11;
        PdfPTable table = new PdfPTable(NumColumns);
        int[] fwidths = {8, 12, 50, 20, 20, 12, 16, 10, 10, 10, 14};
        table.setWidths(fwidths);
        table.setWidthPercentage(100);

        String sCampos[] = {"Nro", "COD", "Producto / Medicamento", "Forma Far", "Concentra", "Fecha\nVencim", "Nro\nLote", "Tipo", "Entrada\nAjuste", "Costo\nUnit", "Total"};
        // coloca la cabecera de titulos

        for (int i = 0; i < NumColumns; i++) {
            cell = new PdfPCell(new Phrase(sCampos[i], CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setGrayFill(0.8f);
            table.addCell(cell);
        }

        // coloca el detalle de loS datos
        for (int i = 0; i < lFopos.size(); i++) {

            Recetas datopac = (Recetas) lFopos.get(i);

            cell = new PdfPCell(new Phrase(Integer.toString(i + 1), CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(datopac.getCodsumi(), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase((datopac.getMedicamento().length() > 25) ? datopac.getMedicamento().substring(0, 25) + "_" + datopac.getId_farmacia() : datopac.getMedicamento() + "_" + datopac.getId_farmacia(), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(datopac.getForma_far(), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase((datopac.getConcentra().length() > 10) ? datopac.getConcentra().substring(0, 10) : datopac.getConcentra(), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase((format(datopac.getFecha_ven(), "MM/yyyy")), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(datopac.getNro_lote(), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            if ("S".equals(datopac.getExpedido())) {
                cell = new PdfPCell(new Phrase("SIIS", DATO_FONT));
            } else if ("V".equals(datopac.getExpedido())) {
                cell = new PdfPCell(new Phrase("Venta", DATO_FONT));
            } else {
                for (int j = 0; j < programas.size(); j++) {
                    Medicamentos prog = (Medicamentos) programas.get(j);
                    if (datos.getId_persona() == prog.getId_programa()) {
                        cell = new PdfPCell(new Phrase(prog.getConcentra(), DATO_FONT));
                    }
                }

            }

            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(dfx.format(datopac.getEntrada()), DATO_FONT));
            if (datopac.getEntrada() == 0 && datopac.getSalida() == 0) {
                cell = new PdfPCell(new Phrase(dfx.format(datopac.getAjuste()), DATO_FONT));
            }
            if (datopac.getEntrada() == 0 && datopac.getAjuste() == 0) {
                cell = new PdfPCell(new Phrase(dfx.format(datopac.getSalida()), DATO_FONT_R));
            }
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(df.format(datopac.getCosto_unit()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(df.format(datopac.getPrecio_totalc()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);

            tot += datopac.getPrecio_totalc();
        }

        cell = new PdfPCell(new Phrase("Total  [Bs]  :                " + df.format(tot), DATO_FONT_BOLD));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setColspan(11);
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
