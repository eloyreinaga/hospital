package org.ayaic.web.administrarhistoriales.ambulatorio;

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
import org.ayaic.domain.Prestaciones;

public class ListarConsultaLabPDF extends AbstractPdfView {

    private static final Font TITULO_FONT = new Font(Font.HELVETICA, 12, Font.BOLD, Color.black);
    private static final Font CABEZA_COLUMNA_FONT = new Font(Font.HELVETICA, 8, Font.ITALIC, Color.black);
    private static final Font CABEZA_COLUMNA_FONT_P = new Font(Font.HELVETICA, 5, Font.ITALIC, Color.black);
    private static final Font DATO_FONT = new Font(Font.HELVETICA, 8, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_P = new Font(Font.HELVETICA, 7, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_BOLD = new Font(Font.HELVETICA, 8, Font.BOLD, Color.black);
    private static final int MARGIN = 32;

    protected void buildPdfMetadata(Map model, Document document, HttpServletRequest request) {
        document.setPageSize(PageSize.LEGAL.rotate());
        document.setMargins(20, 10, 20, 10);
    }

    protected void buildPdfDocument(Map model, Document document, PdfWriter writer, HttpServletRequest req, HttpServletResponse resp) throws Exception {
        java.util.List lPacientes = (java.util.List) model.get("listaPacientes");
        java.util.List lPrestaciones = (java.util.List) model.get("prestacionesDadas");
        java.util.List lPacientePres = (java.util.List) model.get("pacientePrestacion");
        Clientes dato = (Clientes) model.get("dato");
        String soloedad = (String) model.get("soloedad");
        String mes = (String) model.get("mes");
        String annio = (String) model.get("annio");
        String nombres = (String) model.get("nombres");
        String hcl = (String) model.get("hcl");

        Image escudo = Image.getInstance("/opt/imagenes/lescudo.bmp");

        String sTitulo = (String) model.get("sTitulo");
        String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        if (sTitulo == null) {
            sTitulo = "Resumen de prestaciones realizadas II";
        }
        int una;
        if (lPacientes.size() == 0) {
            Paragraph p = new Paragraph("No existe Datos", new Font(Font.HELVETICA, 22, Font.BOLDITALIC, new Color(0, 0, 0)));
            document.add(p);
        }
        if (lPacientes.size() % 30 == 0) {
            una = 0;
        } else {
            una = 1;
        }

        for (int pag = 0; pag < lPacientes.size() / 30 + una; pag++) {
            PdfPCell cell;
            int NumColumns = 55;
            int cont = 0;
            PdfPTable table = new PdfPTable(NumColumns);
            int[] fwidths = {1, 4, 5, 5, 6, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}; // percentage
            table.setWidths(fwidths);
            table.setWidthPercentage(100);

            // inicializamos prestaciones
            for (int i = 0; i < lPrestaciones.size(); i++) {
                Prestaciones datopre = (Prestaciones) lPrestaciones.get(i);
                datopre.setCantidad(0);
                datopre.setHcl(0);
                lPrestaciones.set(i, datopre);
            }

            for (int i = pag * 30 + 0; i < pag * 30 + 30 && i < lPacientes.size(); i++) {
                Historiales datopac = (Historiales) lPacientes.get(i);
                for (int j = 0; j < lPrestaciones.size(); j++) {
                    Prestaciones datopre = (Prestaciones) lPrestaciones.get(j);
                    for (int k = 0; k < lPacientePres.size(); k++) {
                        Prestaciones datopacpre = (Prestaciones) lPacientePres.get(k);
                        if (datopac.getHcl() == datopacpre.getHcl() && datopac.getId_historial() == datopacpre.getId_historial() && datopre.getPrestacion().equals(datopacpre.getPrestacion())) {
                            datopre.setCantidad(datopre.getCantidad() + 1);
                            datopre.setHcl(1);
                            lPrestaciones.set(j, datopre);
                        }
                    }
                }
            }
            // la primera fila

            int NumDatos = 12;
            PdfPTable table1 = new PdfPTable(2);
            int[] fmwidths = {50, 50}; // percentage
            table1.setWidths(fmwidths);
            table1.setWidthPercentage(100);

            //colocamos el titulo
            PdfPTable tablet = new PdfPTable(2);
            int[] ftwidths = {20, 80}; // percentage
            tablet.setWidths(ftwidths);
            tablet.setWidthPercentage(100);

            cell = new PdfPCell(escudo);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setVerticalAlignment(Element.ALIGN_TOP);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            tablet.addCell(cell);

            PdfPTable tablet1 = new PdfPTable(1);
            int[] ft1widths = {100}; // percentage
            tablet1.setWidths(ft1widths);
            tablet1.setWidthPercentage(100);

            cell = new PdfPCell(new Phrase("D-14", new Font(Font.HELVETICA, 12, Font.BOLDITALIC, new Color(0, 0, 0))));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setBorder(Rectangle.NO_BORDER);
            tablet1.addCell(cell);

            cell = new PdfPCell(new Phrase("\n\n", DATO_FONT));
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(2);
            tablet1.addCell(cell);

            cell = new PdfPCell(new Phrase(sTitulo, new Font(Font.HELVETICA, 12, Font.BOLDITALIC, new Color(0, 0, 0))));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            tablet1.addCell(cell);

            cell = new PdfPCell(tablet1);
            cell.setColspan(1);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setVerticalAlignment(Element.ALIGN_TOP);
            tablet.addCell(cell);

            cell = new PdfPCell(tablet);
            cell.setColspan(2);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setVerticalAlignment(Element.ALIGN_TOP);
            table1.addCell(cell);

            cell = new PdfPCell(new Phrase("\n\n\n\n", DATO_FONT));
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(2);
            table1.addCell(cell);

            cell = new PdfPCell(new Phrase("Municipio : " + dato.getLocalidad(), DATO_FONT));
            cell.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell);

            cell = new PdfPCell(new Phrase("Localidad : " + dato.getLocalidad(), DATO_FONT));
            cell.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell);

            cell = new PdfPCell(new Phrase("Establecimiento : " + dato.getEstablecimiento(), DATO_FONT));
            cell.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell);

            cell = new PdfPCell(new Phrase("SEDES : " + dato.getDepartamento(), DATO_FONT));
            cell.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell);

            cell = new PdfPCell(new Phrase("Red : " + dato.getRed(), DATO_FONT));
            cell.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell);

            cell = new PdfPCell(new Phrase("NIVEL DE ATENCION: " + dato.getTipo(), DATO_FONT));
            cell.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell);

            cell = new PdfPCell(table1);
            cell.setColspan(NumDatos - 2);
            cell.setFixedHeight(160f);
            cell.setVerticalAlignment(Element.ALIGN_TOP);
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);

            table1 = new PdfPTable(45);
            int[] fmpwidths = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}; // percentage
            table1.setWidths(fmpwidths);
            table1.setWidthPercentage(100);

            cell = new PdfPCell(new Phrase("PRESTACION", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(2);
            cell.setFixedHeight(110f);
            cell.setRotation(90);
            table1.addCell(cell);

            cont = 0;
            for (int i = 0; i < lPrestaciones.size(); i++) {
                Prestaciones datopre = (Prestaciones) lPrestaciones.get(i);
                if (datopre.getHcl() == 1) {
                    cell = new PdfPCell(new Phrase(datopre.getDescripcion().toLowerCase(), DATO_FONT_P));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setFixedHeight(110f);
                    cell.setRotation(90);
                    table1.addCell(cell);
                    cont++;
                }
            }
            for (int i = cont + NumDatos; i < NumColumns; i++) {
                cell = new PdfPCell(new Phrase("", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table1.addCell(cell);
            }
            cell = new PdfPCell(table1);
            cell.setColspan(45);
            cell.setFixedHeight(160f);
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);

            // la segunda fila
            table1 = new PdfPTable(6);
            int[] fxwidths = {1, 4, 5, 5, 6, 2}; // percentage
            table1.setWidths(fxwidths);
            table1.setWidthPercentage(100);

            String sCampos[] = {"No", "No de\nRegistro", "APELLIDO\nPATERNO", "APELLIDO\nMATERNO", "NOMBRES", "Dia fecha"};
            // coloca la cabecera de titulos
            for (int i = 0; i < 6; i++) {
                cell = new PdfPCell(new Phrase(sCampos[i], CABEZA_COLUMNA_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table1.addCell(cell);
            }
            cell = new PdfPCell(table1);
            cell.setColspan(6);
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);
            //edad
            table1 = new PdfPTable(2);
            int[] fxxxywidths = {1, 1}; // percentage
            table1.setWidths(fxxxywidths);
            table1.setWidthPercentage(100);
            cell = new PdfPCell(new Phrase("EDAD", CABEZA_COLUMNA_FONT));
            cell.setColspan(2);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table1.addCell(cell);

            String sxxyCampos[] = {"años", "Meses"};
            // coloca la cabecera de titulos
            for (int i = 0; i < 2; i++) {
                cell = new PdfPCell(new Phrase(sxxyCampos[i], CABEZA_COLUMNA_FONT_P));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table1.addCell(cell);
            }
            cell = new PdfPCell(table1);
            cell.setColspan(2);
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);
            //sexo
            table1 = new PdfPTable(2);
            int[] fxxxwidths = {1, 1}; // percentage
            table1.setWidths(fxxxwidths);
            table1.setWidthPercentage(100);
            cell = new PdfPCell(new Phrase("SEXO", CABEZA_COLUMNA_FONT));
            cell.setColspan(2);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table1.addCell(cell);

            String sxxCampos[] = {"M", "F"};
            // coloca la cabecera de titulos
            for (int i = 0; i < 2; i++) {
                cell = new PdfPCell(new Phrase(sxxCampos[i], CABEZA_COLUMNA_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table1.addCell(cell);
            }
            cell = new PdfPCell(table1);
            cell.setColspan(2);
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);

            // consulta
            table1 = new PdfPTable(2);
            int[] fxxwidths = {1, 1}; // percentage
            table1.setWidths(fxxwidths);
            table1.setWidthPercentage(100);

            cell = new PdfPCell(new Phrase("CONSULT", CABEZA_COLUMNA_FONT));
            cell.setColspan(2);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table1.addCell(cell);

            String sxCampos[] = {"N", "R"};
            // coloca la cabecera de titulos
            for (int i = 0; i < 2; i++) {
                cell = new PdfPCell(new Phrase(sxCampos[i], CABEZA_COLUMNA_FONT_P));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table1.addCell(cell);
            }
            cell = new PdfPCell(table1);
            cell.setColspan(2);
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);

            cont = 0;
            for (int i = 0; i < lPrestaciones.size(); i++) {
                Prestaciones datopre = (Prestaciones) lPrestaciones.get(i);
                if (datopre.getHcl() == 1) {
                    cell = new PdfPCell(new Phrase(datopre.getPrestacion(), DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setRotation(90);
                    table.addCell(cell);
                    cont++;
                }
            }
            for (int i = cont + NumDatos; i < NumColumns; i++) {
                cell = new PdfPCell(new Phrase("", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);
            }
            int contnueva = 0, contrepetida = 0;

            for (int i = pag * 30 + 0; i < pag * 30 + 30 && i < lPacientes.size(); i++) {

                Historiales datopac = (Historiales) lPacientes.get(i);

                cell = new PdfPCell(new Phrase(Integer.toString(i + 1 - (pag * 30 + 0)), DATO_FONT_P));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(datopac.getRegistro(), DATO_FONT_P));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(datopac.getPaterno(), DATO_FONT_P));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(datopac.getMaterno(), DATO_FONT_P));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                String nombre = datopac.getNombre();
                if (datopac.getNombre().length() > 14) {
                    nombre = nombre.substring(0, 14);
                }
                cell = new PdfPCell(new Phrase(nombre, DATO_FONT_P));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(Integer.toString(datopac.getDia()), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(Integer.toString(datopac.getEdad()), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                if ("si".equals(soloedad)) {
                    cell = new PdfPCell(new Phrase(Integer.toString(datopac.getEdad_ini()) + "d", DATO_FONT_P));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                } else {
                    cell = new PdfPCell(new Phrase(Integer.toString(datopac.getMes()), DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                }
                if (datopac.getId_sexo() == 1) {
                    cell = new PdfPCell(new Phrase("", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);
                    cell = new PdfPCell(new Phrase("X", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);
                } else {
                    cell = new PdfPCell(new Phrase("X", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);
                    cell = new PdfPCell(new Phrase("", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);
                }
                if ("N".equals(datopac.getRepetida())) {
                    cell = new PdfPCell(new Phrase(datopac.getRepetida(), DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);
                    cell = new PdfPCell(new Phrase("", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);
                    contnueva++;
                } else {
                    cell = new PdfPCell(new Phrase("", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);
                    cell = new PdfPCell(new Phrase(datopac.getRepetida(), DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);
                    contrepetida++;
                }

                cont = 0;
                for (int j = 0; j < lPrestaciones.size(); j++) {
                    Prestaciones datopre = (Prestaciones) lPrestaciones.get(j);
                    if (datopre.getHcl() == 1) {
                        String esta = "";
                        for (int k = 0; k < lPacientePres.size(); k++) {
                            Prestaciones datopacpre = (Prestaciones) lPacientePres.get(k);
                            if (datopac.getHcl() == datopacpre.getHcl() && datopac.getId_historial() == datopacpre.getId_historial()
                                    && datopre.getPrestacion().equals(datopacpre.getPrestacion())) {
                                esta = "X";
                            }
                        }
                        cell = new PdfPCell(new Phrase(esta, DATO_FONT));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        table.addCell(cell);
                        cont++;
                    }
                }

                for (int j = cont + NumDatos; j < NumColumns; j++) {
                    cell = new PdfPCell(new Phrase("", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);
                }

            }

            for (int i = lPacientes.size(); i < pag * 30 + 30; i++) {
                cell = new PdfPCell(new Phrase(Integer.toString(i + 1 - (pag * 30 + 0)), DATO_FONT_P));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(cell);
                for (int j = 1; j < NumColumns; j++) {
                    cell = new PdfPCell(new Phrase("", DATO_FONT_P));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);
                }
            }

            cell = new PdfPCell(new Phrase("", DATO_FONT));
            cell.setColspan(6);
            cell.setFixedHeight(20f);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Total Pagina", DATO_FONT));
            cell.setColspan(4);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(Integer.toString(contnueva), DATO_FONT_P));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(Integer.toString(contrepetida), DATO_FONT_P));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            cont = 0;
            for (int i = 0; i < lPrestaciones.size(); i++) {
                Prestaciones datopre = (Prestaciones) lPrestaciones.get(i);
                if (datopre.getHcl() == 1) {
                    cell = new PdfPCell(new Phrase(Integer.toString(datopre.getCantidad()), DATO_FONT_P));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);
                    cont++;
                }
            }

            document.add(table);
            document.newPage();
        }
    }

    public static String format(Date dia, String formato) {
        if (dia == null) {
            return "";
        }
        SimpleDateFormat formatDate = new SimpleDateFormat(formato);
        return formatDate.format(dia);
    }
}
