package org.ayaic.web.administrarhistoriales;

import java.awt.Color;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.view.document.AbstractPdfView;
import java.util.*;
import java.text.*;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Cuadernos;
import org.ayaic.domain.Personas;
import org.ayaic.domain.Salas;

public class ListarCensoPDF extends AbstractPdfView {

    private static final Font TITULO_FONT = new Font(Font.HELVETICA, 12, Font.BOLD, Color.black);
    private static final Font CABEZA_COLUMNA_FONT = new Font(Font.HELVETICA, 8, Font.ITALIC, Color.black);
    private static final Font DATO_FONT = new Font(Font.HELVETICA, 7, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_P = new Font(Font.HELVETICA, 6, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_BOLD = new Font(Font.HELVETICA, 8, Font.BOLD, Color.black);
    private static final int MARGIN = 32;

    protected void buildPdfMetadata(Map model, Document document, HttpServletRequest request) {
        document.setPageSize(PageSize.LEGAL.rotate());
        document.setMargins(70, 20, 20, 20);
    }

    protected void buildPdfDocument(Map model, Document document, PdfWriter writer, HttpServletRequest req, HttpServletResponse resp) throws Exception {

        java.util.List listCenso = (java.util.List) model.get("ListaCenso");
        java.util.List listaSala = (java.util.List) model.get("listarSalas");
        java.util.List listaCama = (java.util.List) model.get("listarCama");
        Clientes dato = (Clientes) model.get("dato");
        Personas datop = (Personas) model.get("datosJMed");
        String mes = (String) model.get("mes");
        String gestion = (String) model.get("anio");
        String sala = (String) model.get("sala");
        DecimalFormat df = new DecimalFormat("###,##0");
        HeaderFooter footer = new HeaderFooter(new Phrase("Pagina No. "), new Phrase("."));

        footer.setAlignment(Element.ALIGN_CENTER);
        footer.setBorder(Rectangle.NO_BORDER);
        document.setFooter(footer);
        int ncama = listaCama.size();
        // coloca el titulo de la pagina
        int[] sumas = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int una;

        if (listCenso.size() == 0) {
            Paragraph p = new Paragraph("No existe Datos", new Font(Font.HELVETICA, 22, Font.BOLDITALIC, new Color(0, 0, 0)));
            document.add(p);
        }

        if (listCenso.size() % 30 == 0) {
            una = 0;
        } else {
            una = 1;
        }

        for (int pag = 0; pag < listCenso.size() / 30 + una; pag++) {

            //Image escudo = Image.getInstance("/opt/imagenes/lescudo.bmp");
            Paragraph p = new Paragraph("REGISTRO DE CENSO DIARIO", new Font(Font.HELVETICA, 14, Font.BOLDITALIC, new Color(0, 0, 0)));
            p.setAlignment(Element.ALIGN_CENTER);

            PdfPCell cell;

            PdfPTable tablex = new PdfPTable(3);
            int[] fxwidths = {15, 70, 15}; // percentage
            tablex.setWidths(fxwidths);
            tablex.setWidthPercentage(100);

            cell = new PdfPCell(p);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(3);
            tablex.addCell(cell);

            document.add(tablex);

            PdfPTable table1 = new PdfPTable(3);
            int[] fmwidths = {35, 35, 35}; // percentage
            table1.setWidths(fmwidths);
            table1.setWidthPercentage(100);

            cell = new PdfPCell(new Phrase("Servicio Departamental de Salud : " + "Oruro", DATO_FONT));
            cell.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell);

            cell = new PdfPCell(new Phrase("Municipio : " + dato.getLocalidad(), DATO_FONT));
            cell.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell);

            cell = new PdfPCell(new Phrase("Establecimiento : " + dato.getEstablecimiento(), DATO_FONT));
            cell.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell);

            for (int j = pag * 30 + 0; j < pag * 30 + 30 && j < listaSala.size(); j++) {
                Salas ListaSala = (Salas) listaSala.get(j);
                if (ListaSala.getId_sala() == Integer.parseInt(sala)) {
                    cell = new PdfPCell(new Phrase("Servicio : " + ListaSala.getSala() + "         No. Camas : " + Integer.toString(ncama), DATO_FONT));
                    cell.setBorder(Rectangle.NO_BORDER);
                    table1.addCell(cell);
                }//Coloca el nombre de es servicio de internacion
            }

            cell = new PdfPCell(new Phrase("Red : " + dato.getRed(), DATO_FONT));
            cell.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell);

            cell = new PdfPCell(new Phrase("Mes : " + mes + "         " + "Año : " + gestion, DATO_FONT));
            cell.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell);

            document.add(table1);

            int NumColumns = 47;
            PdfPTable table = new PdfPTable(NumColumns);
            int[] fwidths = {6, 15, 45, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 12, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5};
            table.setWidths(fwidths);
            table.setWidthPercentage(100);

            cell = new PdfPCell(new Phrase("No", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("No HCL", DATO_FONT));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Nombres y Apellidos", DATO_FONT));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(cuadrotitulo("INGRESOS", "NUEVO", "Traslado \nServicio"));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setColspan(4);
            table.addCell(cell);

            cell = new PdfPCell(cuadrotitulo("EGRESOS", "ALTA", "Traslado\nServicio"));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setColspan(4);
            table.addCell(cell);

            cell = new PdfPCell(cuadrotitulo("EGRESOS", "Defuncion\nAntes 48h", "Defuncion\nDesps 48h"));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setColspan(4);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("INGRES EGRES EN EL MISMO DIA", DATO_FONT));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            int NumCol = 31;
            PdfPTable tablea = new PdfPTable(NumCol);
            int[] mfwidths = {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5};
            tablea.setWidths(mfwidths);
            tablea.setWidthPercentage(100);

            cell = new PdfPCell(new Phrase("CAMAS        OCUPADAS      D1ARIAS", DATO_FONT));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(31);
            tablea.addCell(cell);

            for (int v = 0; v < 31; v++) { //coloca los numeros del 1 al 31
                cell = new PdfPCell(new Phrase(Integer.toString(v + 1), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                tablea.addCell(cell);
            }
            cell = new PdfPCell(tablea);
            cell.setColspan(31);
            table.addCell(cell);

            for (int j = pag * 30 + 0; j < pag * 30 + 30 && j < listCenso.size(); j++) {
                Cuadernos datoCenso = (Cuadernos) listCenso.get(j);

                cell = new PdfPCell(new Phrase(Integer.toString(j + 1), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(Integer.toString(datoCenso.getHcl()), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(datoCenso.getNombres(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(format(datoCenso.getFec_ingreso(), "dd/MM"), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[0] += 1;

                cell = new PdfPCell(new Phrase(format(datoCenso.getFec_ingreso(), "HH:mm"), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);
                sumas[1] += 1;

                cell = new PdfPCell(new Phrase("", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);
                sumas[2] += 0;

                cell = new PdfPCell(new Phrase("", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);
                sumas[3] += 0;

                if (datoCenso.getVitamina() == 2) {
                    cell = new PdfPCell(new Phrase(format(datoCenso.getFec_egreso(), "dd/MM"), DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                    sumas[4] += (datoCenso.getVitamina() == 2) ? 1 : 0;

                    cell = new PdfPCell(new Phrase(format(datoCenso.getFec_egreso(), "HH:mm"), DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);
                    sumas[5] += (datoCenso.getVitamina() == 2) ? 1 : 0;
                } else {
                    cell = new PdfPCell(new Phrase("", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase("", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);
                }

                cell = new PdfPCell(new Phrase("", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[6] += 0;

                cell = new PdfPCell(new Phrase("", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);
                sumas[7] += 0;

                cell = new PdfPCell(new Phrase("", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[8] += 0;

                cell = new PdfPCell(new Phrase("", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);
                sumas[9] += 0;

                cell = new PdfPCell(new Phrase("", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[10] += 0;

                cell = new PdfPCell(new Phrase("", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);
                sumas[11] += 0;

                cell = new PdfPCell(new Phrase("", DATO_FONT));//salidas en el mismo dia
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[12] += 0;

                cell = new PdfPCell(new Phrase((datoCenso.getSuma1() != 0) ? Integer.toString(datoCenso.getSuma1()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);    ///dia1
                sumas[13] += (datoCenso.getSuma1() != 0) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datoCenso.getSuma2() != 0) ? Integer.toString(datoCenso.getSuma2()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);    ///dia2 
                sumas[14] += (datoCenso.getSuma2() != 0) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datoCenso.getSuma3() != 0) ? Integer.toString(datoCenso.getSuma3()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);    ///dia3
                sumas[15] += (datoCenso.getSuma3() != 0) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datoCenso.getSuma4() != 0) ? Integer.toString(datoCenso.getSuma4()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);    ///dia4
                sumas[16] += (datoCenso.getSuma4() != 0) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datoCenso.getSuma5() != 0) ? Integer.toString(datoCenso.getSuma5()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);    ///dia5
                sumas[17] += (datoCenso.getSuma5() != 0) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datoCenso.getSuma6() != 0) ? Integer.toString(datoCenso.getSuma6()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);    ///dia6
                sumas[18] += (datoCenso.getSuma6() != 0) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datoCenso.getSuma7() != 0) ? Integer.toString(datoCenso.getSuma7()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);    ///dia7
                sumas[19] += (datoCenso.getSuma7() != 0) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datoCenso.getSpeso() != 0) ? Integer.toString(datoCenso.getSpeso()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);    ///dia8
                sumas[20] += (datoCenso.getSpeso() != 0) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datoCenso.getFuma() != 0) ? Integer.toString(datoCenso.getFuma()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);    ///dia9
                sumas[21] += (datoCenso.getFuma() != 0) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datoCenso.getAlcohol() != 0) ? Integer.toString(datoCenso.getAlcohol()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);    ///dia10
                sumas[22] += (datoCenso.getAlcohol() != 0) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datoCenso.getViolencia() != 0) ? Integer.toString(datoCenso.getViolencia()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);    ///dia11
                sumas[23] += (datoCenso.getViolencia() != 0) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datoCenso.getAuto() != 0) ? Integer.toString(datoCenso.getAuto()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);    ///dia12
                sumas[24] += (datoCenso.getAuto() != 0) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datoCenso.getUrinaria() != 0) ? Integer.toString(datoCenso.getUrinaria()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);    ///dia13
                sumas[25] += (datoCenso.getUrinaria() != 0) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datoCenso.getSistemica() != 0) ? Integer.toString(datoCenso.getSistemica()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);    ///dia14
                sumas[26] += (datoCenso.getSistemica() != 0) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datoCenso.getArterial() != 0) ? Integer.toString(datoCenso.getArterial()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);    ///dia15
                sumas[27] += (datoCenso.getArterial() != 0) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datoCenso.getDiabetes() != 0) ? Integer.toString(datoCenso.getDiabetes()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);    ///dia16
                sumas[28] += (datoCenso.getDiabetes() != 0) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datoCenso.getGlome() != 0) ? Integer.toString(datoCenso.getGlome()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);    ///dia17
                sumas[29] += (datoCenso.getGlome() != 0) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datoCenso.getTracto() != 0) ? Integer.toString(datoCenso.getTracto()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);    ///dia18
                sumas[30] += (datoCenso.getTracto() != 0) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datoCenso.getLupus() != 0) ? Integer.toString(datoCenso.getLupus()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);    ///dia19
                sumas[31] += (datoCenso.getLupus() != 0) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datoCenso.getLitiasis() != 0) ? Integer.toString(datoCenso.getLitiasis()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);    ///dia20
                sumas[32] += (datoCenso.getLitiasis() != 0) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datoCenso.getNooplasia() != 0) ? Integer.toString(datoCenso.getNooplasia()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);    ///dia21
                sumas[33] += (datoCenso.getNooplasia() != 0) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datoCenso.getNefro() != 0) ? Integer.toString(datoCenso.getNefro()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);    ///dia22
                sumas[34] += (datoCenso.getNefro() != 0) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datoCenso.getDisli() != 0) ? Integer.toString(datoCenso.getDisli()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);    ///dia23
                sumas[35] += (datoCenso.getDisli() != 0) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datoCenso.getFrenal() != 0) ? Integer.toString(datoCenso.getFrenal()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);    ///dia24
                sumas[36] += (datoCenso.getFrenal() != 0) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datoCenso.getBajopeso() != 0) ? Integer.toString(datoCenso.getBajopeso()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);    ///dia25
                sumas[37] += (datoCenso.getBajopeso() != 0) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datoCenso.getPrematuro() != 0) ? Integer.toString(datoCenso.getPrematuro()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);    ///dia26
                sumas[38] += (datoCenso.getPrematuro() != 0) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datoCenso.getEritro() != 0) ? Integer.toString(datoCenso.getEritro()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);    ///dia27
                sumas[39] += (datoCenso.getEritro() != 0) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datoCenso.getRenal() != 0) ? Integer.toString(datoCenso.getRenal()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);    ///dia28
                sumas[40] += (datoCenso.getRenal() != 0) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datoCenso.getTuberculo() != 0) ? Integer.toString(datoCenso.getTuberculo()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);    ///dia29
                sumas[41] += (datoCenso.getTuberculo() != 0) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datoCenso.getOtro() != 0) ? Integer.toString(datoCenso.getOtro()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);    ///dia30
                sumas[42] += (datoCenso.getOtro() != 0) ? 1 : 0;

                table.addCell(cell);
            }

            for (int i = listCenso.size(); i < pag * 30 + 30; i++) {
                cell = new PdfPCell(new Phrase(Integer.toString(i + 1), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(cell);
                for (int j = 1; j < 47; j++) {
                    cell = new PdfPCell(new Phrase("", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);
                }
            }

            cell = new PdfPCell(new Phrase("EXSTENCIA", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setColspan(3);
            table.addCell(cell);

            for (int z = 0; z < 44; z++) {
                cell = new PdfPCell(new Phrase(Integer.toString(sumas[z]), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);
            }

            cell = new PdfPCell(new Phrase("CAMAS OCUPADAS DIARIAS", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setColspan(3);
            table.addCell(cell);

            for (int z = 0; z < 44; z++) {
                cell = new PdfPCell(new Phrase(Integer.toString(sumas[z]), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);
            }

            for (int k = 0; k < 2; k++) { //coloca filas vacias entes de colocar totales
                cell = new PdfPCell(new Phrase("", CABEZA_COLUMNA_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
            }

            document.add(table);
            document.newPage();
        }
    }

    public static PdfPTable cuadrotitulo(String letra, String cadena, String cadena2) {
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
        cell.setColspan(2);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase(cadena2, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(2);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("Fech", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1.addCell(cell);
        cell = new PdfPCell(new Phrase("Hora", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1.addCell(cell);
        cell = new PdfPCell(new Phrase("Fech", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1.addCell(cell);
        cell = new PdfPCell(new Phrase("Hora", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1.addCell(cell);
        return table1;
    }

    public static PdfPTable cuadrotitulo2(String letra) {
        // la primera fila

        PdfPCell cell;
        PdfPTable table1 = new PdfPTable(31);
        table1.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase(letra, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(31);
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
