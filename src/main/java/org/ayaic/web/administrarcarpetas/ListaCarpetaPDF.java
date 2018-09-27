package org.ayaic.web.administrarcarpetas;

import java.awt.Color;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.view.document.AbstractPdfView;
import java.util.*;
import java.text.*;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import java.io.IOException;
import org.ayaic.domain.Carpetas;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Localidades;
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.Parentescos;

public class ListaCarpetaPDF extends AbstractPdfView {

    private static final Font TITULO_FONT = new Font(Font.HELVETICA, 12, Font.BOLD, Color.black);
    private static final Font CABEZA_COLUMNA_FONT = new Font(Font.HELVETICA, 8, Font.ITALIC, Color.black);
    private static final Font DATO_FONT = new Font(Font.HELVETICA, 8, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_BOLD = new Font(Font.HELVETICA, 8, Font.BOLD, Color.black);
    private static final int MARGIN = 32;

    protected void buildPdfMetadata(Map model, Document document, HttpServletRequest request) {
        document.setPageSize(PageSize.LEGAL.rotate());
        document.setMargins(60, 10, 10, 10);
    }

    protected void buildPdfDocument(Map model, Document document, PdfWriter writer, HttpServletRequest req, HttpServletResponse resp) throws BadElementException, IOException, DocumentException {

        java.util.List lDependientes = (java.util.List) model.get("listaPacientesD");
        Clientes dato = (Clientes) model.get("dato");
        Carpetas datocarpeta = (Carpetas) model.get("Carpeta");
        Localidades datolocalidad = (Localidades) model.get("localidad");
        Parentescos datoparentesco = (Parentescos) model.get("parentesco");
        Pacientes jefe = (Pacientes) model.get("responsable");
        DecimalFormat df = new DecimalFormat("###,##0.00");

        // coloca el titulo de la pagina
        Image escudo = Image.getInstance("/opt/imagenes/lescudo.bmp");
        Paragraph p = new Paragraph("SEGUROS PUBLICOS DE SALUD\nFORMULARIO DE PRE - AFILIACION", new Font(Font.HELVETICA, 14, Font.BOLDITALIC, new Color(0, 0, 0)));
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

        PdfPTable table1 = new PdfPTable(3);
        int[] fmwidths = {50, 10, 50}; // percentage
        table1.setWidths(fmwidths);
        table1.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("DATOS GENERALES", DATO_FONT));
        cell.setColspan(3);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setFixedHeight(20);
        cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("Establecimiento : " + dato.getEstablecimiento(), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT));
        cell.setBorder(Rectangle.NO_BORDER);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("Municipio : " + dato.getLocalidad(), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("Red : " + dato.getRed(), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT));
        cell.setBorder(Rectangle.NO_BORDER);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("SEDES : " + dato.getDepartamento() + "      Localidad:....................", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("Mes : " + format(new Date(), "MM") + "         " + "Año : " + format(new Date(), "yyyy"), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT));
        cell.setBorder(Rectangle.NO_BORDER);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("Sector :  " + datocarpeta.getCarpeta(), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table1.addCell(cell);

        int NumColumns = 13;
        PdfPTable table = new PdfPTable(NumColumns);
        int[] fwidths = {4, 10, 50, 5, 5, 15, 15, 15, 5, 5, 5, 10, 10};
        table.setWidths(fwidths);
        table.setWidthPercentage(100);

        String sCampos[] = {"No", "Historia Clinica", "Nombre Completo", "M", "F", "Dep.", "MUNICIPIO", "P.F.", "Fecha Nacimiento", "mes", "año", "Carnet", "doc"};

        cell = new PdfPCell(tablex);
        cell.setColspan(13);
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);

        cell = new PdfPCell(table1);
        cell.setColspan(13);
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("RESPONSABLE DE FAMILIA", DATO_FONT));
        cell.setColspan(13);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setFixedHeight(20);
        cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
        table.addCell(cell);

        // coloca la cabecera de titulos
        cell = new PdfPCell(new Phrase("Numero Carpeta", CABEZA_COLUMNA_FONT));
        cell.setColspan(2);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("NOMBRE COMPLETO", CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        addfila2(table, "SEXO", "F", "M");
        addfila2(table, "LUGAR DE NACIMIENTO", "DEP", "MUNICIPIO");

        cell = new PdfPCell(new Phrase("P.F.", CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        addfila3(table, "FECHA NACIMIENTO", "DIA", "MES", "año");
        addfila2(table, "DOCUMENTO", "NUMERO", "TIPO");

        cell = new PdfPCell(new Phrase(Integer.toString(datocarpeta.getId_carpeta()), DATO_FONT));
        cell.setColspan(2);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(jefe.getNombres(), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(jefe.getId_tipo_sexo() == 1 ? "F" : "", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(jefe.getId_tipo_sexo() == 2 ? "M" : "", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(Integer.toString(jefe.getId_departamento()), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(datolocalidad.getLocalidad(), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(datoparentesco.getParentesco(), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(format(jefe.getFec_nacimiento(), "dd"), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(format(jefe.getFec_nacimiento(), "MM"), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(format(jefe.getFec_nacimiento(), "yyyy"), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(jefe.getCarnet(), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(Integer.toString(jefe.getId_tipo_documento()), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("COMPONENTES DE LA FAMILIA", DATO_FONT));
        cell.setColspan(13);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setFixedHeight(20);
        cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
        table.addCell(cell);

        // coloca la cabecera de titulos
        cell = new PdfPCell(new Phrase("NUM.", CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("HISTORIA CLINICA", CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("NOMBRE COMPLETO", CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        addfila2(table, "SEXO", "F", "M");
        addfila2(table, "LUGAR DE NACIMIENTO", "DEP", "MUNICIPIO");

        cell = new PdfPCell(new Phrase("P.F.", CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        addfila3(table, "FECHA NACIMIENTO", "DIA", "MES", "año");
        addfila2(table, "DOCUMENTO", "NUMERO", "TIPO");

        for (int i = 0; i < lDependientes.size(); i++) {

            Carpetas datopac = (Carpetas) lDependientes.get(i);

            cell = new PdfPCell(new Phrase(Integer.toString(i + 1), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(Integer.toString(datopac.getHcl()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(datopac.getNombres(), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(datopac.getId_tipo_sexo() == 1 ? "F" : "", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(datopac.getId_tipo_sexo() == 2 ? "M" : "", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(Integer.toString(datopac.getId_departamento()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(datopac.getDireccion(), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(datopac.getTipo(), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(format(datopac.getFec_nacimiento(), "dd"), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(format(datopac.getFec_nacimiento(), "MM"), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(format(datopac.getFec_nacimiento(), "yyyy"), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(datopac.getCarnet(), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(Integer.toString(datopac.getId_tipo_documento()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
        }
        for (int i = lDependientes.size(); i < 15; i++) {

            cell = new PdfPCell(new Phrase(Integer.toString(i + 1), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);
        }

        cell = new PdfPCell(new Phrase("", DATO_FONT));
        cell.setColspan(13);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setFixedHeight(10);
        cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
        table.addCell(cell);

        document.add(table);

        PdfPTable tabled = new PdfPTable(9);
        int[] fdwidths = {10, 10, 10, 20, 20, 20, 10, 10, 10}; // percentage
        tabled.setWidths(fdwidths);
        tabled.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("DEPARTAMENTOS(DEP)", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(3);
        tabled.addCell(cell);
        cell = new PdfPCell(new Phrase("TIPOS DE DECUMENTOS DE IDENTIDAD", DATO_FONT));
        cell.setColspan(3);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabled.addCell(cell);
        cell = new PdfPCell(new Phrase("PARENTESCO FAMILIAR(P.F.)", DATO_FONT));
        cell.setColspan(3);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabled.addCell(cell);

        cell = new PdfPCell(new Phrase("01 = Chuquisaca\n"
                + "02 = La Paz\n"
                + "03 = Cochabamaba\n"
                + "04 = Oruro", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        tabled.addCell(cell);

        cell = new PdfPCell(new Phrase("05 = Potosi\n"
                + "06 = Tarija\n"
                + "07 = Santa Cruz\n"
                + "08 = Beni", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        tabled.addCell(cell);

        cell = new PdfPCell(new Phrase("09 = Pando\n"
                + "10 = Extranjero\n"
                + "11 = Nacionalizado\n", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        tabled.addCell(cell);

        cell = new PdfPCell(new Phrase("01 = Cedula de Identidad\n"
                + "02 = Registro Unico Nacional\n"
                + "03 = Pasaporte\n"
                + "04 = Numero de Identificacion Tributaria", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        tabled.addCell(cell);

        cell = new PdfPCell(new Phrase("05 = Libreta de Servicio Militar\n"
                + "06 = Hoja de Dos Testigos\n"
                + "07 = Certificado de Nacimiento \n"
                + "08 =Certificado de Bautizo", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        tabled.addCell(cell);

        cell = new PdfPCell(new Phrase("09 = Libreta de Familia\n"
                + "10 = Certificado Nacido Vivo\n", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        tabled.addCell(cell);

        cell = new PdfPCell(new Phrase("01 = Responsable\n"
                + "02 = Esposo(a)\n"
                + "03 = Hijo(a)\n"
                + "04 = Padre", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        tabled.addCell(cell);

        cell = new PdfPCell(new Phrase("05 = Madre\n"
                + "06 = Nieto(a)\n"
                + "07 = Abuelo(a)\n"
                + "08 = Hermano(a)", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        tabled.addCell(cell);

        cell = new PdfPCell(new Phrase("09 = Sobrino\n"
                + "10 = Tio(a)\n"
                + "11 = Primo(a)\n"
                + "12 = Otro", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        tabled.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT));
        cell.setColspan(9);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setFixedHeight(10);
        cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
        tabled.addCell(cell);

        document.add(tabled);

        PdfPTable table11 = new PdfPTable(5);
        int[] fmnwidths = {20, 50, 10, 20, 50}; // percentage
        table11.setWidths(fmnwidths);
        table11.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("NOMBRES Y \nAPELLIDOS DEL\n AFILIADOR", DATO_FONT));
        cell.setFixedHeight(30);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT));
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase("FIRMA DEL\n AFILIADOR", DATO_FONT));
        cell.setVerticalAlignment(Element.ALIGN_CENTER);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table11.addCell(cell);
        document.add(table11);

    }

    public static void addfila2(PdfPTable table, String a, String b, String c) {
        PdfPCell cell;
        PdfPTable table1 = new PdfPTable(2);

        cell = new PdfPCell(new Phrase(a, CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setColspan(2);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase(b, CABEZA_COLUMNA_FONT));

        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase(c, CABEZA_COLUMNA_FONT));

        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1.addCell(cell);

        cell = new PdfPCell(table1);
        cell.setColspan(2);
        table.addCell(cell);
    }

    public static void addfila3(PdfPTable table, String a, String b, String c, String d) {
        PdfPCell cell;
        PdfPTable table1x = new PdfPTable(3);

        cell = new PdfPCell(new Phrase(a, DATO_FONT));
        cell.setColspan(3);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1x.addCell(cell);

        cell = new PdfPCell(new Phrase(b, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1x.addCell(cell);

        cell = new PdfPCell(new Phrase(c, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1x.addCell(cell);

        cell = new PdfPCell(new Phrase(d, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1x.addCell(cell);

        cell = new PdfPCell(table1x);
        cell.setColspan(3);
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        table.addCell(cell);
    }

    public static String format(Date dia, String formato) {
        if (dia == null) {
            return "";
        }
        SimpleDateFormat formatDate = new SimpleDateFormat(formato);
        return formatDate.format(dia);
    }
}
