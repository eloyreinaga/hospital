package org.ayaic.web.administrarpacientes;

import java.awt.Color;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.view.document.AbstractPdfView;
import java.util.*;
import java.text.*;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfWriter;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Localidades;
import org.ayaic.domain.Pacientes;

public class VigenciaRiesgosPDF extends AbstractPdfView {

    private static final Font TITULO_FONT = new Font(Font.HELVETICA, 11, Font.BOLD, Color.black);
    private static final Font TITULO_FONT_B = new Font(Font.HELVETICA, 16, Font.BOLD, Color.black);
    private static final Font CABEZA_COLUMNA_FONT = new Font(Font.HELVETICA, 8, Font.ITALIC, Color.black);
    private static final Font DATO_FONT = new Font(Font.HELVETICA, 10, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_BOLD = new Font(Font.COURIER, 8, Font.BOLD, Color.black);
    private static final int MARGIN = 32;

    protected void buildPdfMetadata(Map model, Document document, HttpServletRequest request) {
        document.setPageSize(PageSize.LETTER);
        document.setMargins(20, 20, 20, 10);
    }

    protected void buildPdfDocument(Map model, Document document, PdfWriter writer, HttpServletRequest req, HttpServletResponse resp) throws Exception {

        java.util.List listaVigencia = (java.util.List) model.get("listaVigencia");
        Pacientes pacientes = (Pacientes) model.get("datos");
        Localidades datoestab = (Localidades) model.get("datoestab");
        Clientes dato = (Clientes) model.get("dato");
        Date fecha1 = new Date();

        PdfPCell cell;

        Pacientes pac = (Pacientes) listaVigencia.get(0);

        PdfPTable tablex = new PdfPTable(3);
        int[] fxwidths = {60, 20, 20}; // percentage
        tablex.setWidths(fxwidths);
        tablex.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase(datoestab.getNombreseg() + "\nDEPARTAMENTO NACIONAL DE AFILIACION\nSECCION VIGENCIA DE DERECHOS", TITULO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex.addCell(cell);

        cell = new PdfPCell(new Phrase("Form. VD - 03\nNo. " + pac.getId_vigencia(), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(2);
        tablex.addCell(cell);

        cell = new PdfPCell(new Phrase("\nRIESGOS EXTRAORDINARIOS\nD.S. 20989, PARTICULARES\n\n", TITULO_FONT_B));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(3);
        tablex.addCell(cell);

        cell = new PdfPCell(new Phrase("Seï¿½ores  :  ......" + dato.getEstablecimiento(), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(3);
        tablex.addCell(cell);

        cell = new PdfPCell(new Phrase("Comunica a Ud(s) que en fecha           " + format(fecha1, "dd") + "      de      " + format(fecha1, "MM") + "      del      " + format(fecha1, "yyyy") + "              a horas       " + format(fecha1, "HH") + " : " + format(fecha1, "mm") + "            se ha registrado\n la atencion medica del Seï¿½or :  ", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(3);
        tablex.addCell(cell);

        cell = new PdfPCell(new Phrase("Nombre y Apellidos : " + pac.getNombres(), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex.addCell(cell);

        cell = new PdfPCell(new Phrase("Matricula:" + pac.getDireccion(), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(2);
        tablex.addCell(cell);

        cell = new PdfPCell(new Phrase("Beneficiario : " + pac.getNombres(), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex.addCell(cell);

        cell = new PdfPCell(new Phrase("Codigo : " + pac.getCarnet() + "                    Edad : " + Integer.toString(pacientes.getEdad()) + " a " + Integer.toString(pacientes.getMes()) + " m " + Integer.toString(pacientes.getDia()) + "d", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(2);
        tablex.addCell(cell);

        cell = new PdfPCell(new Phrase("Empresa : " + pac.getFactor_riesgo(), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex.addCell(cell);

        cell = new PdfPCell(new Phrase("No Patronal : " + pac.getId_estado(), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(2);
        tablex.addCell(cell);

        cell = new PdfPCell(new Phrase("Adscrito a Policlinico : " + pac.getMaterno(), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex.addCell(cell);

        cell = new PdfPCell(new Phrase("Consultorio : " + pac.getNit(), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(2);
        tablex.addCell(cell);

        cell = new PdfPCell(new Phrase("Zona : " + pac.getOcupacion() + "             Calle : " + pac.getPaterno() + "                Telefono : " + pac.getRegistro(), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(3);
        tablex.addCell(cell);

        cell = new PdfPCell(new Phrase("Clase de Riesgo   :   " + pac.getDocumento(), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(3);
        tablex.addCell(cell);

        cell = new PdfPCell(new Phrase("\nDATOS DEL CAUSANTE O GARANTE\n\n", TITULO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(3);
        tablex.addCell(cell);

        cell = new PdfPCell(new Phrase("Yo : " + pac.getNombre(), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex.addCell(cell);

        cell = new PdfPCell(new Phrase("C.I. : " + pac.getCadena2(), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(2);
        tablex.addCell(cell);

        cell = new PdfPCell(new Phrase("Zona : " + pac.getResultado() + "             Calle : " + pac.getCadena() + "  No " + pac.getNro() + "                Telefono : " + pac.getCadena1(), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(3);
        tablex.addCell(cell);

        cell = new PdfPCell(new Phrase("Me comprometo a cancelar los gastos de Prestacion Medicas:", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(3);
        tablex.addCell(cell);

        cell = new PdfPCell(new Phrase("Atendido en : " + pac.getNro_registro(), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex.addCell(cell);

        cell = new PdfPCell(new Phrase("Transferencia a : " + pac.getNum_cladoc(), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(2);
        tablex.addCell(cell);

        cell = new PdfPCell(new Phrase("Piso :     " + pac.getCadena6() + "              Sala :     " + pac.getCadena7() + "        Cama    :        " + pac.getCadena8(), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(3);
        tablex.addCell(cell);

        cell = new PdfPCell(new Phrase("\n\nFirma de Causante o Garante", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(1);
        tablex.addCell(cell);

        cell = new PdfPCell(new Phrase("\n\nFirma Encargado de Vigencia y Derechos", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(2);
        tablex.addCell(cell);

        cell = new PdfPCell(new Phrase("\n\nLugar y Fecha  :      " + dato.getDepartamento() + ",   " + format(fecha1, "dd") + "   de   " + format(fecha1, "MM") + "   del   " + format(fecha1, "yyyy") + "                                                ", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(3);
        tablex.addCell(cell);

        document.add(tablex);

    }

    public static String format(Date dia, String formato) {
        if (dia == null) {
            return "";
        }
        SimpleDateFormat formatDate = new SimpleDateFormat(formato);
        return formatDate.format(dia);
    }
}
