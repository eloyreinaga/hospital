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
import org.ayaic.domain.Historiales;
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.Personas;
import org.ayaic.domain.Prestaciones;
import org.ayaic.domain.Recetas;
import org.ayaic.domain.Seguros;

public class ListarRecetaPDF extends AbstractPdfView {

    private static final Font TITULO_FONT = new Font(Font.HELVETICA, 12, Font.BOLD, Color.black);
    private static final Font CABEZA_COLUMNA_FONT = new Font(Font.HELVETICA, 8, Font.ITALIC, Color.black);
    private static final Font DATO_FONT = new Font(Font.HELVETICA, 8, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_B = new Font(Font.HELVETICA, 8, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_A = new Font(Font.COURIER, 10, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_BOLD = new Font(Font.HELVETICA, 8, Font.BOLD, Color.black);
    private static final int MARGIN = 32;

    protected void buildPdfMetadata(Map model, Document document, HttpServletRequest request) {
        document.setPageSize(PageSize.LEGAL);
        document.setMargins(20, 30, 10, 5);
    }

    protected void buildPdfDocument(Map model, Document document, PdfWriter writer, HttpServletRequest req, HttpServletResponse resp) throws Exception {

        java.util.List listaRecetas = (java.util.List) model.get("listarRecetas");
        java.util.List listaKardex = (java.util.List) model.get("listarKardex");
        java.util.List listaPres = (java.util.List) model.get("listarPres");
        java.util.List listaSeg = (java.util.List) model.get("listarSeguros");

        Historiales datoHis = (Historiales) model.get("datosHis");
        Personas datoMed = (Personas) model.get("datosMed");
        Pacientes datoPac = (Pacientes) model.get("datosPac");
        Clientes dato = (Clientes) model.get("dato");
        Pacientes datoPaciente = (Pacientes) model.get("datos");
        String mes = (String) model.get("mes");
        String gestion = (String) model.get("gestion");
        DecimalFormat df = new DecimalFormat("###,##0");
        DecimalFormat dfx = new DecimalFormat("###,##0.0");
        double sumatot = 0;

        // coloca el titulo de la pagina
        Image escudo = Image.getInstance("/opt/imagenes/lescudo.bmp");
        Paragraph p = new Paragraph("RECETARIO / RECIBO\nATENCION AMBULATORIA", new Font(Font.HELVETICA, 14, Font.BOLDITALIC, new Color(0, 0, 0)));
        p.setAlignment(Element.ALIGN_CENTER);
        //Image sumi = Image.getInstance("/opt/imagenes/hoja1.bmp");

        PdfPCell cell;

        //AGREGAMOS TITULO
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

        if ("S".equals(datoHis.getExpedido())) {
            cell = new PdfPCell(new Phrase("No.:  " + datoPaciente.getNit(), TITULO_FONT));
        } else {
            cell = new PdfPCell(new Phrase("No.:  " + datoPaciente.getId_pedido(), TITULO_FONT));
        }
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(2);
        tablex.addCell(cell);

        cell = new PdfPCell(new Phrase("N. RECETA: " + Integer.toString(datoPaciente.getId_factura()) + "  ", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex.addCell(cell);

        document.add(tablex);

        PdfPTable table1 = new PdfPTable(3);
        int[] fmwidths = {58, 30, 12}; // percentage
        table1.setWidths(fmwidths);
        table1.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("SEDES : " + dato.getDepartamento() + "             Red : " + dato.getRed() + "                                        HCL:" + datoPac.getHcl(), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("SEGUROS PUBLICOS DE SALUD", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setBorder(Rectangle.NO_BORDER);
        table1.addCell(cell);

        if ("S".equals(datoHis.getExpedido())) {
            cell = new PdfPCell(new Phrase("Ley 475", DATO_FONT));
            if (datoPaciente.getId_tipo_far() == -2) { ///entrega nutribebe
                cell = new PdfPCell(new Phrase("", DATO_FONT));
            }
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table1.addCell(cell);
        } else {
            cell = new PdfPCell(new Phrase(".", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table1.addCell(cell);
        }

        cell = new PdfPCell(new Phrase("MUNICIPIO : " + dato.getLocalidad(), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("PROGRAMAS", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setBorder(Rectangle.NO_BORDER);
        table1.addCell(cell);

        if (datoPaciente.getId_tipo_far() == -2) { ///entrega nutribebe
            cell = new PdfPCell(new Phrase("PMDC", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table1.addCell(cell);
        } else {
            if ("P".equals(datoHis.getExpedido())) {
                for (int v = 0; v < listaSeg.size(); v++) {
                    Seguros datoSeg = (Seguros) listaSeg.get(v);
                    if (datoHis.getId_seguro() == datoSeg.getId_seguro()) {
                        cell = new PdfPCell(new Phrase(datoSeg.getSeguro(), DATO_FONT));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table1.addCell(cell);
                    }
                }
            } else {
                cell = new PdfPCell(new Phrase(".", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table1.addCell(cell);
            }
        }
        cell = new PdfPCell(new Phrase("Establecimiento : " + dato.getEstablecimiento() + "                  Peso:" + datoHis.getPeso() + " Kg.    Talla:" + datoHis.getTalla() + " cm.", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("VENTA", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setBorder(Rectangle.NO_BORDER);
        table1.addCell(cell);

        if ("E".equals(datoHis.getExpedido())) {
            cell = new PdfPCell(new Phrase("X", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table1.addCell(cell);
        } else {
            cell = new PdfPCell(new Phrase(".", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table1.addCell(cell);
        }

        cell = new PdfPCell(new Phrase("Nombre del Paciente: " + datoPac.getNombres() + "                 edad: " + datoPac.getEdad() + "a" + datoPac.getMes() + "m" + datoPac.getDia(), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("Peso:" + datoHis.getPeso() + " [Kg]  Talla:" + datoHis.getTalla() + " [Cm]  Fech Nac:", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase(format(datoPac.getFec_nacimiento(), "dd/MM/yyyy"), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("Domicilio: " + datoPac.getDireccion(), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table1.addCell(cell);

        PdfPTable tabley = new PdfPTable(3);
        int[] fywidths = {50, 10, 40}; // percentage
        tabley.setWidths(fywidths);
        tabley.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("Sexo : ", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setBorder(Rectangle.NO_BORDER);
        tabley.addCell(cell);

        cell = new PdfPCell(new Phrase(datoPac.getId_tipo_sexo() == 1 ? "F" : "M", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabley.addCell(cell);

        cell = new PdfPCell(new Phrase("FECHA : ", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setBorder(Rectangle.NO_BORDER);
        tabley.addCell(cell);

        cell = new PdfPCell(tabley);
        cell.setBorder(Rectangle.NO_BORDER);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase(format(new Date(), "dd/MM/yyyy HH:mm"), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1.addCell(cell);

        document.add(table1);

        PdfPTable table = new PdfPTable(7);
        int[] fxxwidths = {3, 36, 38, 6, 6, 6, 6}; // percentage
        table.setWidths(fxxwidths);
        table.setWidthPercentage(100);

        //AGREGAMOS DIAGNOSTICO
        if (datoHis.getDiagnostico() == null || "null".equals(datoHis.getDiagnostico())) {
            datoHis.setDiagnostico("");
        } else {

            datoHis.setDiagnostico(datoHis.getDiagnostico().replaceAll("<p>", " "));
            datoHis.setDiagnostico(datoHis.getDiagnostico().replaceAll("</p>", " "));
            datoHis.setDiagnostico(datoHis.getDiagnostico().replaceAll("&nbsp;", ""));
            datoHis.setDiagnostico(datoHis.getDiagnostico().replaceAll("<strong>", " "));
            datoHis.setDiagnostico(datoHis.getDiagnostico().replaceAll("</strong>", " "));
            datoHis.setDiagnostico(datoHis.getDiagnostico().replaceAll("<br />", " "));
            datoHis.setDiagnostico(datoHis.getDiagnostico().replaceAll("<u>", " "));
            datoHis.setDiagnostico(datoHis.getDiagnostico().replaceAll("</u>", " "));
            datoHis.setDiagnostico(datoHis.getDiagnostico().replaceAll("<ol>", " "));
            datoHis.setDiagnostico(datoHis.getDiagnostico().replaceAll("</ol>", " "));
            datoHis.setDiagnostico(datoHis.getDiagnostico().replaceAll("<ul>", " "));
            datoHis.setDiagnostico(datoHis.getDiagnostico().replaceAll("</ul>", " "));
            datoHis.setDiagnostico(datoHis.getDiagnostico().replaceAll("<li>", " "));
            datoHis.setDiagnostico(datoHis.getDiagnostico().replaceAll("</li>", " "));
            datoHis.setDiagnostico(datoHis.getDiagnostico().replaceAll("&ntilde;", "n"));
            datoHis.setDiagnostico(datoHis.getDiagnostico().replaceAll("&Ntilde;", "N"));
            datoHis.setDiagnostico(datoHis.getDiagnostico().replaceAll("&Aacute;", "A"));
            datoHis.setDiagnostico(datoHis.getDiagnostico().replaceAll("&Eacute;", "E"));
            datoHis.setDiagnostico(datoHis.getDiagnostico().replaceAll("&Iacute;", "I"));
            datoHis.setDiagnostico(datoHis.getDiagnostico().replaceAll("&Oacute;", "O"));
            datoHis.setDiagnostico(datoHis.getDiagnostico().replaceAll("&Uacute;", "U"));
            datoHis.setDiagnostico(datoHis.getDiagnostico().replaceAll("&aacute;", "a"));
            datoHis.setDiagnostico(datoHis.getDiagnostico().replaceAll("&eacute;", "e"));
            datoHis.setDiagnostico(datoHis.getDiagnostico().replaceAll("&iacute;", "i"));
            datoHis.setDiagnostico(datoHis.getDiagnostico().replaceAll("&oacute;", "o"));
            datoHis.setDiagnostico(datoHis.getDiagnostico().replaceAll("&uacute;", "u"));
            datoHis.setDiagnostico(datoHis.getDiagnostico().replaceAll("&quot;", "'"));
        }
        cell = new PdfPCell(new Phrase("DIAGNOSTICO  : CIE[" + datoHis.getCodigo() + "];  " + datoHis.getDiagnostico(), DATO_FONT_A));
        cell.setColspan(7);
        table.addCell(cell);
        //AGREGAMOS PRESTACIONES
        table1 = new PdfPTable(1);
        table1.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("PRESTACIONES :", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        table1.addCell(cell);
        // prestaciones dadas
        if (datoPaciente.getId_tipo_far() == -2) { ///entrega nutribebe
            cell = new PdfPCell(new Phrase("COMPLEMENTO NUTRICIONAL NUTRIBEBE", DATO_FONT_A));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell);
        } else {
            for (int i = 0; i < listaPres.size(); i++) {
                Prestaciones datoPres = (Prestaciones) listaPres.get(i);
                if (datoPres.getId_prestacion() != 3762) {
                    cell = new PdfPCell(new Phrase("          " + datoPres.getPrestacion() + "    " + datoPres.getDescripcion() + "    [" + datoPres.getCantidad() + "]", DATO_FONT_A));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setBorder(Rectangle.NO_BORDER);
                    table1.addCell(cell);
                }
            }
        }

        cell = new PdfPCell(table1);
        cell.setColspan(7);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("No", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("MEDICAMENTOS E INSUMOS\n(Nombre generico, Forma Farmaceutica y Concentra)", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("INDICACIONES PARA EL PACIENTE\n(Cantidad, Frecuencia, Tiempo de uso y Via de Administra)", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        addfila2(table, "CANTIDAD", "Receta", "Dispens");
        addfila2(table, "VALOR", "Unitario", "Total");

        // coloca el detalle de loS datos
        for (int i = 0; i < listaKardex.size(); i++) {

            Recetas datoReceta = (Recetas) listaKardex.get(i);

            cell = new PdfPCell(new Phrase(Integer.toString(i + 1), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(((datoReceta.getMedicamento().length() > 25) ? datoReceta.getMedicamento().substring(0, 25) : datoReceta.getMedicamento()) + "<" + datoReceta.getForma_far() + ">" + datoReceta.getConcentra(), DATO_FONT_A));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            for (int j = 0; j < listaRecetas.size(); j++) {

                Recetas datoKardex = (Recetas) listaRecetas.get(j);
                if (datoReceta.getId_detalle() == datoKardex.getId_receta() && datoReceta.getId_medicamento() == datoKardex.getId_medicamento()) {
                    cell = new PdfPCell(new Phrase(datoKardex.getIndicacion() + " [" + datoKardex.getDosifica() + " dias]", DATO_FONT_A));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase(df.format(datoKardex.getSalida()), DATO_FONT_A));
                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase(df.format(datoReceta.getSalida()), DATO_FONT_A));
                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    table.addCell(cell);

                    if ("S".equals(datoKardex.getExpedido())) {
                        cell = new PdfPCell(new Phrase(dfx.format(datoReceta.getCosto_unit()), DATO_FONT_A));
                        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                        table.addCell(cell);

                        cell = new PdfPCell(new Phrase(dfx.format(datoReceta.getSalida() * datoReceta.getCosto_unit()), DATO_FONT_A));
                        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                        table.addCell(cell);
                        sumatot += datoReceta.getSalida() * datoReceta.getCosto_unit();
                    } else {
                        cell = new PdfPCell(new Phrase(dfx.format(datoReceta.getPrecio_venta()), DATO_FONT_A));
                        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                        table.addCell(cell);

                        cell = new PdfPCell(new Phrase(dfx.format(datoReceta.getPrecio_total()), DATO_FONT_A));
                        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                        table.addCell(cell);
                        sumatot += datoReceta.getSalida() * datoReceta.getPrecio_venta();
                    }
                }
            }
        }

        PdfPTable xtable = new PdfPTable(5);
        int[] xfwidths = {24, 24, 24, 21, 7}; // percentage
        xtable.setWidths(xfwidths);
        xtable.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("COSTO TOTAL", DATO_FONT));
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(4);
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        xtable.addCell(cell);

        cell = new PdfPCell(new Phrase(dfx.format(sumatot), DATO_FONT_A));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        xtable.addCell(cell);

        cell = new PdfPCell(new Phrase("COSTO TOTAL AL USUARIO", DATO_FONT));
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(4);
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        xtable.addCell(cell);
        if ("S".equals(datoHis.getExpedido()) || "P".equals(datoHis.getExpedido())) {
            cell = new PdfPCell(new Phrase("0", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            xtable.addCell(cell);
        } else {
            cell = new PdfPCell(new Phrase(dfx.format(datoPaciente.getPrecio_total()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            xtable.addCell(cell);
        }

        String s = datoMed.getFirma();
        Image sumia = Image.getInstance(datoMed.getFirma());

        cell = new PdfPCell(sumia);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(5);
        xtable.addCell(cell);

        cell = new PdfPCell(new Phrase(datoMed.getNombres() + "\n" + datoMed.getMatricula() + "\nRecetado por:\nSello y firma", DATO_FONT));
        cell.setFixedHeight(30f);
        cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        xtable.addCell(cell);

        cell = new PdfPCell(new Phrase(dato.getNombres() + "\nDispensado por:\nSello y firma", DATO_FONT));
        cell.setFixedHeight(30f);
        cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        xtable.addCell(cell);

        cell = new PdfPCell(new Phrase("Sello", DATO_FONT));
        cell.setFixedHeight(30f);
        cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        xtable.addCell(cell);

        cell = new PdfPCell(new Phrase(datoPaciente.getNombres() + "\npaciente/acompaï¿½ante\nC.I.: " + datoPac.getCarnet(), DATO_FONT));
        cell.setFixedHeight(30f);
        cell.setColspan(2);
        cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        xtable.addCell(cell);

        cell = new PdfPCell(new Phrase("El prescriptor y dispensador certifican la veracidad de la informacion declarada en este documento medico legal\nEl usuario certifica haber recibido los medicamentos seï¿½alados en este documento medico legal", DATO_FONT));
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(5);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        xtable.addCell(cell);

        cell = new PdfPCell(xtable);
        cell.setColspan(7);
        table.addCell(cell);

        document.add(table);

    }

    public static void addfila2(PdfPTable table, String a, String b, String c) {
        PdfPCell cell;
        PdfPTable table1 = new PdfPTable(2);

        cell = new PdfPCell(new Phrase(a, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setColspan(2);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase(b, DATO_FONT_B));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase(c, DATO_FONT_B));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1.addCell(cell);

        cell = new PdfPCell(table1);
        cell.setColspan(2);
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
