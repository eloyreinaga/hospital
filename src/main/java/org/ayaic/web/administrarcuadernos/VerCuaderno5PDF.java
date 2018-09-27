package org.ayaic.web.administrarcuadernos;

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
import org.ayaic.domain.Localidades;

public class VerCuaderno5PDF extends AbstractPdfView {

    private static final Font TITULO_FONT = new Font(Font.HELVETICA, 12, Font.BOLD, Color.black);
    private static final Font CABEZA_COLUMNA_FONT = new Font(Font.HELVETICA, 8, Font.ITALIC, Color.black);
    private static final Font DATO_FONT = new Font(Font.HELVETICA, 8, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_BOLD = new Font(Font.HELVETICA, 8, Font.BOLD, Color.black);
    private static final int MARGIN = 32;

    protected void buildPdfMetadata(Map model, Document document, HttpServletRequest request) {
        Localidades dl = (Localidades) model.get("localidades");
        document.setPageSize(PageSize.LEGAL.rotate());
        document.setMargins(dl.getIzquierda(), dl.getDerecha(), dl.getSuperior(), dl.getInferior());
    }

    protected void buildPdfDocument(Map model, Document document, PdfWriter writer, HttpServletRequest req, HttpServletResponse resp) throws Exception {

        java.util.List lFopos = (java.util.List) model.get("listaCobros");
        java.util.List cie = (java.util.List) model.get("listaCie");
        Clientes dato = (Clientes) model.get("dato");
        int[] sumas = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int[] sumbs = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int una, filas = 30;

        if (lFopos.size() == 0) {
            Paragraph p = new Paragraph("No existe Datos", new Font(Font.HELVETICA, 22, Font.BOLDITALIC, new Color(0, 0, 0)));
            document.add(p);
        }

        if (lFopos.size() % filas == 0) {
            una = 0;
        } else {
            una = 1;
        }

        for (int pag = 0; pag < lFopos.size() / filas + una; pag++) {
            Paragraph p;
            PdfPCell cell;

            int NumColumns = 34;
            PdfPTable table = new PdfPTable(NumColumns);
            int[] fwidths = {8, 9, 10, 13, 35, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 40, 3, 3, 3, 3, 3, 3, 3, 3, 9, 5};
            table.setWidths(fwidths);
            table.setWidthPercentage(100);

            cell = new PdfPCell(new Phrase("Cuaderno No. 5                                                                                                                                Registro para el seguimiento de Internaciones en Hospitales Basicos", TITULO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(NumColumns);
            table.addCell(cell);

            String sCampos[] = {"No", "Fecha Ingreso", "No Historia Clinica", "No Asegurado", "Apellidos y Nombre", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""};
            // coloca la cabecera de titulos
            for (int i = 0; i < 5; i++) {
                cell = new PdfPCell(new Phrase(sCampos[i], CABEZA_COLUMNA_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
            }
            addtitulofila2(table, "Sexo", "M", "F");
            addtitulo32(table, "Edad en años", "< 1 año", "M", "F", "1 a 4 años", "M", "F", "5 a 9 años", "M", "F", "10 a 20", "M", "F", "21 a 59", "M", "F", "60 a mas", "M", "F");
            addtitulo31(table, "Ingreso", "Referido", "M", "F", "Expontan", "M", "F");

            cell = new PdfPCell(new Phrase("Diagnostico Ingreso", CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            addtitulo31(table, "Cirugia", "Mediana", "M", "F", "Mayor..", "M", "F");
            addtitulo31(table, "Anestecia", "General", "M", "F", "Regional", "M", "F");

            cell = new PdfPCell(new Phrase("Fecha Egreso", CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Atendido por", CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setRotation(90);
            table.addCell(cell);

            for (int i = 34; i < NumColumns; i++) {
                cell = new PdfPCell(new Phrase(sCampos[i], CABEZA_COLUMNA_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
            }
            // coloca el detalle de loS datos
            for (int i = pag * filas + 0; i < pag * filas + filas && i < lFopos.size(); i++) {
                Cuadernos datopac = (Cuadernos) lFopos.get(i);

                cell = new PdfPCell(new Phrase(Integer.toString(i + 1), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(format(datopac.getFechap(), "dd/MM/yy"), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(Integer.toString(datopac.getId_historial()), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(datopac.getRegistro(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(datopac.getNombres(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 2) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[0] += (datopac.getId_cuaderno() == 2) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[1] += (datopac.getId_cuaderno() == 1) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 2 && datopac.getEdad() == 0) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT)); //+":"+Integer.toString(datopac.getMes())
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[2] += (datopac.getId_cuaderno() == 2 && datopac.getEdad() == 0) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 1 && datopac.getEdad() == 0) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT)); //+":"+Integer.toString(datopac.getMes())
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[3] += (datopac.getId_cuaderno() == 1 && datopac.getEdad() == 0) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 2 && datopac.getEdad() > 0 && datopac.getEdad() < 5) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[4] += (datopac.getId_cuaderno() == 2 && datopac.getEdad() > 0 && datopac.getEdad() < 5) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 1 && datopac.getEdad() > 0 && datopac.getEdad() < 5) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[5] += (datopac.getId_cuaderno() == 1 && datopac.getEdad() > 0 && datopac.getEdad() < 5) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 2 && datopac.getEdad() > 4 && datopac.getEdad() < 10) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[6] += (datopac.getId_cuaderno() == 2 && datopac.getEdad() > 4 && datopac.getEdad() < 10) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 1 && datopac.getEdad() > 4 && datopac.getEdad() < 10) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[7] += (datopac.getId_cuaderno() == 1 && datopac.getEdad() > 4 && datopac.getEdad() < 10) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 2 && datopac.getEdad() > 9 && datopac.getEdad() < 21) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[8] += (datopac.getId_cuaderno() == 2 && datopac.getEdad() > 9 && datopac.getEdad() < 21) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 1 && datopac.getEdad() > 9 && datopac.getEdad() < 21) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[9] += (datopac.getId_cuaderno() == 1 && datopac.getEdad() > 9 && datopac.getEdad() < 21) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 2 && datopac.getEdad() > 20 && datopac.getEdad() < 60) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[10] += (datopac.getId_cuaderno() == 2 && datopac.getEdad() > 20 && datopac.getEdad() < 60) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 1 && datopac.getEdad() > 20 && datopac.getEdad() < 60) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[11] += (datopac.getId_cuaderno() == 1 && datopac.getEdad() > 20 && datopac.getEdad() < 60) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 2 && datopac.getEdad() > 60) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[12] += (datopac.getId_cuaderno() == 2 && datopac.getEdad() > 60) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 1 && datopac.getEdad() > 60) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[13] += (datopac.getId_cuaderno() == 1 && datopac.getEdad() > 60) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 2 && datopac.getIngreso() == 0) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[14] += (datopac.getId_cuaderno() == 2 && datopac.getIngreso() == 0) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 1 && datopac.getIngreso() == 0) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[15] += (datopac.getId_cuaderno() == 1 && datopac.getIngreso() == 0) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 2 && datopac.getIngreso() == 1) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[16] += (datopac.getId_cuaderno() == 2 && datopac.getIngreso() == 1) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 1 && datopac.getIngreso() == 1) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[17] += (datopac.getId_cuaderno() == 1 && datopac.getIngreso() == 1) ? 1 : 0;

                String CIE10 = "";
                for (int k = 0; k < cie.size(); k++) {
                    Cuadernos cie10 = (Cuadernos) cie.get(k);
                    if (cie10.getId_historial() == datopac.getId_laboratorio()) {
                        CIE10 = cie10.getTipodent() + "[" + (cie10.getObservaciones().toLowerCase().length() > 15 ? cie10.getObservaciones().toLowerCase().substring(0, 15) : cie10.getObservaciones().toLowerCase()) + "]" + ";" + CIE10;
                    }
                }

                cell = new PdfPCell(new Phrase((datopac.getDiagnostico_ing().length() > 60) ? datopac.getDiagnostico_ing().substring(0, 60) + "->" + CIE10.trim() : datopac.getDiagnostico_ing() + "->" + CIE10.trim(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 2 && datopac.getCirugia() == 1) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[18] += (datopac.getId_cuaderno() == 2 && datopac.getCirugia() == 1) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 1 && datopac.getCirugia() == 1) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[19] += (datopac.getId_cuaderno() == 1 && datopac.getCirugia() == 1) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 2 && datopac.getCirugia() == 2) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[20] += (datopac.getId_cuaderno() == 2 && datopac.getCirugia() == 2) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 1 && datopac.getCirugia() == 2) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[21] += (datopac.getId_cuaderno() == 1 && datopac.getCirugia() == 2) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 2 && datopac.getAnastecia() == 1) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[22] += (datopac.getId_cuaderno() == 2 && datopac.getAnastecia() == 1) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 1 && datopac.getAnastecia() == 1) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[23] += (datopac.getId_cuaderno() == 1 && datopac.getAnastecia() == 1) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 2 && datopac.getAnastecia() == 2) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[24] += (datopac.getId_cuaderno() == 2 && datopac.getAnastecia() == 2) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 1 && datopac.getAnastecia() == 2) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[25] += (datopac.getId_cuaderno() == 1 && datopac.getAnastecia() == 2) ? 1 : 0;

                cell = new PdfPCell(new Phrase(format(datopac.getFec_egreso(), "dd/MM/yy"), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(Integer.toString(datopac.getId_persona()), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);
            }
            for (int i = lFopos.size(); i < pag * filas + filas; i++) {
                cell = new PdfPCell(new Phrase(Integer.toString(i + 1), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                for (int j = 1; j < NumColumns; j++) {
                    cell = new PdfPCell(new Phrase("", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);
                }
            }

            cell = new PdfPCell(new Phrase("TOTAL", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(5);
            table.addCell(cell);
            for (int j = 0; j < 18; j++) {
                cell = new PdfPCell(new Phrase(Integer.toString(sumas[j]), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(cell);
            }
            cell = new PdfPCell(new Phrase("", DATO_FONT)); // espacio en blanco de diagnostico de ingreso
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);
            for (int j = 18; j < 26; j++) {
                cell = new PdfPCell(new Phrase(Integer.toString(sumas[j]), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(cell);
            }
            for (int j = 6; j < 30; j++) {
                cell = new PdfPCell(new Phrase("", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(cell);
            }

            document.add(table);
            document.newPage();
//nueva hoja             
            NumColumns = 41;
            table = new PdfPTable(NumColumns);
            int[] fwdidths = {8, 35, 4, 4, 4, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 8, 8, 8, 8};
            table.setWidths(fwdidths);
            table.setWidthPercentage(100);

            cell = new PdfPCell(new Phrase("Cuaderno No. 5                                                                                                                                Registro para el seguimiento de Internaciones en Hospitales Basicos", TITULO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(NumColumns);
            table.addCell(cell);

            String sdCampos[] = {"No", "Fecha Egreso", "Diagnostico Egreso", "Sexo", "Edad", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""};
            // coloca la cabecera de titulos

            cell = new PdfPCell(new Phrase("No", CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Diagnostico Egreso", CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            addtitulofila3(table);
            addtitulo36(table, "Causa de Egreso menor de 5 años", "Diarrea", "M", "F", "Neumonia", "M", "F", "Otros", "M", "F");
            addtitulo35(table, "Tipo de Egreso", "Alta Medica", "M", "F", "Alta Solicitada", "M", "F", "Fuga", "M", "F", "< 48 horas", "M", "F", "> 48 horas", "M", "F");
            addtitulo38(table, "Fallecidos", "RN <7 dias", "M", "F", "Muerte <1año", "M", "F", "Diarrea", "M", "F", "Neumonia", "M", "F", "Desnutricion", "M", "F", "Otras Cuasas", "M", "F", "Muerte materna", "M", "F", "Otros > 5 años", "M", "F");
            addtitulofila2(table, "A otros centros ", "Refer", "Retor");
            addtitulofila2(table, "A nuestr Hospital", "Refer", "Retor");

            for (int i = 41; i < NumColumns; i++) {
                cell = new PdfPCell(new Phrase(sdCampos[i], CABEZA_COLUMNA_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
            }
            // coloca el detalle de loS datos

            for (int i = pag * filas + 0; i < pag * filas + filas && i < lFopos.size(); i++) {

                Cuadernos datopac = (Cuadernos) lFopos.get(i);

                cell = new PdfPCell(new Phrase(Integer.toString(i + 1), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                String CIE102 = "";
                for (int m = 0; m < cie.size(); m++) {
                    Cuadernos cie102 = (Cuadernos) cie.get(m);
                    if (cie102.getId_historial() == datopac.getId_laboratorio()) {
                        CIE102 = cie102.getTipodent() + "[" + (cie102.getObservaciones().toLowerCase().length() > 15 ? cie102.getObservaciones().toLowerCase().substring(0, 15) : cie102.getObservaciones().toLowerCase()) + "]" + ";" + CIE102;
                    }
                }

                cell = new PdfPCell(new Phrase((datopac.getDiagnostico().length() > 60) ? datopac.getDiagnostico().substring(0, 60) + "->" + CIE102.trim() : datopac.getDiagnostico() + "->" + CIE102.trim(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(Integer.toString(datopac.getId_consultorio()), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);
                //dias de estancia
                cell = new PdfPCell(new Phrase(Integer.toString(datopac.getDiasc()), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);
                //dias cama internado
                cell = new PdfPCell(new Phrase(Integer.toString(datopac.getDiasi()), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 2 && datopac.getEgreso() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[0] += (datopac.getId_cuaderno() == 2 && datopac.getEgreso() == 1) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 1 && datopac.getEgreso() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[1] += (datopac.getId_cuaderno() == 1 && datopac.getEgreso() == 1) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 2 && datopac.getEgreso() == 2) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[2] += (datopac.getId_cuaderno() == 2 && datopac.getEgreso() == 2) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 1 && datopac.getEgreso() == 2) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[3] += (datopac.getId_cuaderno() == 1 && datopac.getEgreso() == 2) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 2 && datopac.getEgreso() == 3) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[4] += (datopac.getId_cuaderno() == 1 && datopac.getEgreso() == 3) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 1 && datopac.getEgreso() == 3) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[5] += (datopac.getId_cuaderno() == 2 && datopac.getEgreso() == 3) ? 1 : 0;
                // causa de egreso
                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 2 && datopac.getTipo_egreso() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[6] += (datopac.getId_cuaderno() == 2 && datopac.getTipo_egreso() == 1) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 1 && datopac.getTipo_egreso() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[7] += (datopac.getId_cuaderno() == 1 && datopac.getTipo_egreso() == 1) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 2 && datopac.getTipo_egreso() == 2) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[8] += (datopac.getId_cuaderno() == 2 && datopac.getTipo_egreso() == 2) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 1 && datopac.getTipo_egreso() == 2) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[9] += (datopac.getId_cuaderno() == 1 && datopac.getTipo_egreso() == 2) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 2 && datopac.getTipo_egreso() == 3) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[10] += (datopac.getId_cuaderno() == 1 && datopac.getTipo_egreso() == 3) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 1 && datopac.getTipo_egreso() == 3) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[11] += (datopac.getId_cuaderno() == 2 && datopac.getTipo_egreso() == 3) ? 1 : 0;
//Egreso antes de 48 horas y despues de 48 horas
                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 2 && datopac.getFallecido() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[12] += (datopac.getId_cuaderno() == 2 && datopac.getFallecido() == 1) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 1 && datopac.getFallecido() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[13] += (datopac.getId_cuaderno() == 1 && datopac.getFallecido() == 1) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 2 && datopac.getFallecido() == 2) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[14] += (datopac.getId_cuaderno() == 1 && datopac.getFallecido() == 2) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 1 && datopac.getFallecido() == 2) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[15] += (datopac.getId_cuaderno() == 2 && datopac.getFallecido() == 2) ? 1 : 0;
//Fallecidos menores de 5 años 
                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 2 && datopac.getFallecidom5() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[16] += (datopac.getId_cuaderno() == 2 && datopac.getFallecidom5() == 1) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 1 && datopac.getFallecidom5() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[17] += (datopac.getId_cuaderno() == 1 && datopac.getFallecidom5() == 1) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 2 && datopac.getFallecidom5() == 2) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[18] += (datopac.getId_cuaderno() == 2 && datopac.getFallecidom5() == 2) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 1 && datopac.getFallecidom5() == 2) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[19] += (datopac.getId_cuaderno() == 1 && datopac.getFallecidom5() == 2) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 2 && datopac.getFallecidom5() == 3) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[20] += (datopac.getId_cuaderno() == 2 && datopac.getFallecidom5() == 3) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 1 && datopac.getFallecidom5() == 3) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[21] += (datopac.getId_cuaderno() == 1 && datopac.getFallecidom5() == 3) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 2 && datopac.getFallecidom5() == 4) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[22] += (datopac.getId_cuaderno() == 2 && datopac.getFallecidom5() == 4) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 1 && datopac.getFallecidom5() == 4) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[23] += (datopac.getId_cuaderno() == 1 && datopac.getFallecidom5() == 4) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 2 && datopac.getFallecidom5() == 5) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[24] += (datopac.getId_cuaderno() == 2 && datopac.getFallecidom5() == 5) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 1 && datopac.getFallecidom5() == 5) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[25] += (datopac.getId_cuaderno() == 1 && datopac.getFallecidom5() == 5) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 2 && datopac.getFallecidom5() == 6) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[26] += (datopac.getId_cuaderno() == 2 && datopac.getFallecidom5() == 6) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 1 && datopac.getFallecidom5() == 6) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[27] += (datopac.getId_cuaderno() == 1 && datopac.getFallecidom5() == 6) ? 1 : 0;
//muerte mayores de 5 años
                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 2 && datopac.getFallecidoy5() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[28] += (datopac.getId_cuaderno() == 2 && datopac.getFallecidoy5() == 1) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 1 && datopac.getFallecidoy5() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[29] += (datopac.getId_cuaderno() == 1 && datopac.getFallecidoy5() == 1) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 2 && datopac.getFallecidoy5() == 2) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[30] += (datopac.getId_cuaderno() == 2 && datopac.getFallecidoy5() == 2) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getId_cuaderno() == 1 && datopac.getFallecidoy5() == 2) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[31] += (datopac.getId_cuaderno() == 1 && datopac.getFallecidoy5() == 2) ? 1 : 0;

                cell = new PdfPCell(new Phrase(datopac.getReferido(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(datopac.getContraref(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(datopac.getReferido(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(datopac.getContraref(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

            }

            for (int i = lFopos.size(); i < pag * filas + filas; i++) {
                cell = new PdfPCell(new Phrase(Integer.toString(i + 1), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                for (int j = 1; j < NumColumns; j++) {
                    cell = new PdfPCell(new Phrase("", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);
                }
            }

            cell = new PdfPCell(new Phrase("TOTAL", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(5);
            table.addCell(cell);
            for (int j = 0; j < 32; j++) {
                cell = new PdfPCell(new Phrase(Integer.toString(sumbs[j]), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(cell);
            }
            // llena datos vacios para poner las sumas
            for (int j = 1; j < 20; j++) {
                cell = new PdfPCell(new Phrase("", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);
            }

            document.add(table);
            document.newPage();
        }
    }

    public static void addtitulofila3(PdfPTable table) {
        PdfPCell cell;
        int NumColumns = 3;
        PdfPTable table11 = new PdfPTable(NumColumns);
        int[] fwdidths = {10, 3, 3};
        //   table11.setWidths(fwdidths);
        table11.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("Estancia Hospitalaria", CABEZA_COLUMNA_FONT));
        cell.setColspan(3);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase("Servicio", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase("Dia de Esta", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase("Dia Cam Ocup", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        cell = new PdfPCell(table11);
        cell.setColspan(3);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
    }

    public static void addtitulo36(PdfPTable table, String cadena, String tit1, String a, String b, String tit2, String c, String d, String tit3, String e, String f) {

        PdfPCell cell;
        int NumColumns = 6;
        PdfPTable table11 = new PdfPTable(NumColumns);
        table11.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase(cadena, CABEZA_COLUMNA_FONT));
        cell.setColspan(6);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        int NumCol1 = 2;
        PdfPTable table12 = new PdfPTable(NumCol1);
        table12.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase(tit1, DATO_FONT));
        cell.setColspan(2);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table12.addCell(cell);

        cell = new PdfPCell(new Phrase(a, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table12.addCell(cell);

        cell = new PdfPCell(new Phrase(b, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table12.addCell(cell);

        cell = new PdfPCell(table12);
        cell.setColspan(2);
        table11.addCell(cell);

        table12 = new PdfPTable(NumCol1);
        table12.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase(tit2, DATO_FONT));
        cell.setColspan(2);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table12.addCell(cell);

        cell = new PdfPCell(new Phrase(c, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table12.addCell(cell);

        cell = new PdfPCell(new Phrase(d, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table12.addCell(cell);

        cell = new PdfPCell(table12);
        cell.setColspan(2);
        table11.addCell(cell);

        table12 = new PdfPTable(NumCol1);
        table12.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase(tit3, DATO_FONT));
        cell.setColspan(2);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table12.addCell(cell);

        cell = new PdfPCell(new Phrase(e, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table12.addCell(cell);

        cell = new PdfPCell(new Phrase(f, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table12.addCell(cell);

        cell = new PdfPCell(table12);
        cell.setColspan(2);
        table11.addCell(cell);

        cell = new PdfPCell(table11);
        cell.setColspan(6);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
    }

    public static void addtitulofila4(PdfPTable table, String cadena, String a, String b, String c, String d, String e, String f) {
        PdfPCell cell;
        int NumColumns = 6;
        PdfPTable table11 = new PdfPTable(NumColumns);
        table11.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase(cadena, CABEZA_COLUMNA_FONT));
        cell.setColspan(6);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase(a, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase(b, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase(c, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase(d, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase(e, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase(f, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        cell = new PdfPCell(table11);
        cell.setColspan(6);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
    }

    public static void addtitulofila3(PdfPTable table, String cadena, String a, String b, String c) {
        PdfPCell cell;
        int NumColumns = 3;
        PdfPTable table11 = new PdfPTable(NumColumns);
        table11.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase(cadena, CABEZA_COLUMNA_FONT));
        cell.setColspan(3);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase(a, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase(b, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase(c, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        cell = new PdfPCell(table11);
        cell.setColspan(3);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
    }

    public static void addtitulo31(PdfPTable table, String cadena, String tit1, String a, String b, String tit2, String c, String d) {

        PdfPCell cell;
        int NumColumns = 4;
        PdfPTable table11 = new PdfPTable(NumColumns);
        table11.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase(cadena, CABEZA_COLUMNA_FONT));
        cell.setColspan(4);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        int NumCol1 = 2;
        PdfPTable table12 = new PdfPTable(NumCol1);
        table12.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase(tit1, DATO_FONT));
        cell.setColspan(2);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table12.addCell(cell);

        cell = new PdfPCell(new Phrase(a, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table12.addCell(cell);

        cell = new PdfPCell(new Phrase(b, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table12.addCell(cell);

        cell = new PdfPCell(table12);
        cell.setColspan(2);
        table11.addCell(cell);

        table12 = new PdfPTable(NumCol1);
        table12.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase(tit2, DATO_FONT));
        cell.setColspan(2);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table12.addCell(cell);

        cell = new PdfPCell(new Phrase(c, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table12.addCell(cell);

        cell = new PdfPCell(new Phrase(d, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table12.addCell(cell);

        cell = new PdfPCell(table12);
        cell.setColspan(2);
        table11.addCell(cell);

        cell = new PdfPCell(table11);
        cell.setColspan(4);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
    }

    public static void addtitulo32(PdfPTable table, String cadena, String tit1, String a, String b, String tit2, String c, String d, String tit3, String e, String f, String tit4, String g, String h, String tit5, String i, String j, String tit6, String k, String l) {

        PdfPCell cell;
        int NumColumns = 12;
        PdfPTable table11 = new PdfPTable(NumColumns);
        table11.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase(cadena, CABEZA_COLUMNA_FONT));
        cell.setColspan(12);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);
//primera columna de 2     
        int NumCol1 = 2;
        PdfPTable table12 = new PdfPTable(NumCol1);
        table12.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase(tit1, DATO_FONT));
        cell.setColspan(2);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table12.addCell(cell);

        cell = new PdfPCell(new Phrase(a, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table12.addCell(cell);

        cell = new PdfPCell(new Phrase(b, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table12.addCell(cell);

        cell = new PdfPCell(table12);
        cell.setColspan(2);
        table11.addCell(cell);
//segunda columna de 2       
        table12 = new PdfPTable(NumCol1);
        table12.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase(tit2, DATO_FONT));
        cell.setColspan(2);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table12.addCell(cell);

        cell = new PdfPCell(new Phrase(c, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table12.addCell(cell);

        cell = new PdfPCell(new Phrase(d, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table12.addCell(cell);

        cell = new PdfPCell(table12);
        cell.setColspan(2);
        table11.addCell(cell);

//tercera columna de 2       
        table12 = new PdfPTable(NumCol1);
        table12.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase(tit3, DATO_FONT));
        cell.setColspan(2);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table12.addCell(cell);

        cell = new PdfPCell(new Phrase(e, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table12.addCell(cell);

        cell = new PdfPCell(new Phrase(f, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table12.addCell(cell);

        cell = new PdfPCell(table12);
        cell.setColspan(2);
        table11.addCell(cell);

        //cuarta columna de 2       
        table12 = new PdfPTable(NumCol1);
        table12.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase(tit4, DATO_FONT));
        cell.setColspan(2);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table12.addCell(cell);

        cell = new PdfPCell(new Phrase(g, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table12.addCell(cell);

        cell = new PdfPCell(new Phrase(h, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table12.addCell(cell);

        cell = new PdfPCell(table12);
        cell.setColspan(2);
        table11.addCell(cell);

        //quinta columna de 2       
        table12 = new PdfPTable(NumCol1);
        table12.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase(tit5, DATO_FONT));
        cell.setColspan(2);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table12.addCell(cell);

        cell = new PdfPCell(new Phrase(i, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table12.addCell(cell);

        cell = new PdfPCell(new Phrase(j, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table12.addCell(cell);

        cell = new PdfPCell(table12);
        cell.setColspan(2);
        table11.addCell(cell);

        //sexta columna de 2       
        table12 = new PdfPTable(NumCol1);
        table12.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase(tit6, DATO_FONT));
        cell.setColspan(2);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table12.addCell(cell);

        cell = new PdfPCell(new Phrase(k, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table12.addCell(cell);

        cell = new PdfPCell(new Phrase(l, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table12.addCell(cell);

        cell = new PdfPCell(table12);
        cell.setColspan(2);
        table11.addCell(cell);

        cell = new PdfPCell(table11);
        cell.setColspan(12);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
    }

    public static void addtitulo38(PdfPTable table, String cadena, String tit1, String a, String b, String tit2, String c, String d, String tit3, String e, String f, String tit4, String g, String h, String tit5, String i, String j, String tit6, String k, String l, String tit7, String m, String n, String tit8, String o, String p) {

        PdfPCell cell;
        int NumColumns = 16;
        PdfPTable table11 = new PdfPTable(NumColumns);
        table11.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase(cadena, CABEZA_COLUMNA_FONT));
        cell.setColspan(16);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);
//primera columna de 2     
        int NumCol1 = 2;
        PdfPTable table12 = new PdfPTable(NumCol1);
        table12.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase(tit1, DATO_FONT));
        cell.setColspan(2);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table12.addCell(cell);

        cell = new PdfPCell(new Phrase(a, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table12.addCell(cell);

        cell = new PdfPCell(new Phrase(b, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table12.addCell(cell);

        cell = new PdfPCell(table12);
        cell.setColspan(2);
        table11.addCell(cell);
//segunda columna de 2       
        table12 = new PdfPTable(NumCol1);
        table12.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase(tit2, DATO_FONT));
        cell.setColspan(2);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table12.addCell(cell);

        cell = new PdfPCell(new Phrase(c, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table12.addCell(cell);

        cell = new PdfPCell(new Phrase(d, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table12.addCell(cell);

        cell = new PdfPCell(table12);
        cell.setColspan(2);
        table11.addCell(cell);

//tercera columna de 2       
        table12 = new PdfPTable(NumCol1);
        table12.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase(tit3, DATO_FONT));
        cell.setColspan(2);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table12.addCell(cell);

        cell = new PdfPCell(new Phrase(e, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table12.addCell(cell);

        cell = new PdfPCell(new Phrase(f, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table12.addCell(cell);

        cell = new PdfPCell(table12);
        cell.setColspan(2);
        table11.addCell(cell);

        //cuarta columna de 2       
        table12 = new PdfPTable(NumCol1);
        table12.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase(tit4, DATO_FONT));
        cell.setColspan(2);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table12.addCell(cell);

        cell = new PdfPCell(new Phrase(g, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table12.addCell(cell);

        cell = new PdfPCell(new Phrase(h, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table12.addCell(cell);

        cell = new PdfPCell(table12);
        cell.setColspan(2);
        table11.addCell(cell);

        //quinta columna de 2       
        table12 = new PdfPTable(NumCol1);
        table12.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase(tit5, DATO_FONT));
        cell.setColspan(2);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table12.addCell(cell);

        cell = new PdfPCell(new Phrase(i, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table12.addCell(cell);

        cell = new PdfPCell(new Phrase(j, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table12.addCell(cell);

        cell = new PdfPCell(table12);
        cell.setColspan(2);
        table11.addCell(cell);

        //sexta columna de 2       
        table12 = new PdfPTable(NumCol1);
        table12.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase(tit6, DATO_FONT));
        cell.setColspan(2);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table12.addCell(cell);

        cell = new PdfPCell(new Phrase(k, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table12.addCell(cell);

        cell = new PdfPCell(new Phrase(l, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table12.addCell(cell);

        cell = new PdfPCell(table12);
        cell.setColspan(2);
        table11.addCell(cell);

        //septima columna de 2       
        table12 = new PdfPTable(NumCol1);
        table12.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase(tit7, DATO_FONT));
        cell.setColspan(2);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table12.addCell(cell);

        cell = new PdfPCell(new Phrase(m, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table12.addCell(cell);

        cell = new PdfPCell(new Phrase(n, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table12.addCell(cell);

        cell = new PdfPCell(table12);
        cell.setColspan(2);
        table11.addCell(cell);

        //octava columna de 2       
        table12 = new PdfPTable(NumCol1);
        table12.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase(tit8, DATO_FONT));
        cell.setColspan(2);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table12.addCell(cell);

        cell = new PdfPCell(new Phrase(o, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table12.addCell(cell);

        cell = new PdfPCell(new Phrase(p, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table12.addCell(cell);

        cell = new PdfPCell(table12);
        cell.setColspan(2);
        table11.addCell(cell);

        cell = new PdfPCell(table11);
        cell.setColspan(16);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
    }

    public static void addtitulo35(PdfPTable table, String cadena, String tit1, String a, String b, String tit2, String c, String d, String tit3, String e, String f, String tit4, String g, String h, String tit5, String i, String j) {

        PdfPCell cell;
        int NumColumns = 10;
        PdfPTable table11 = new PdfPTable(NumColumns);
        table11.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase(cadena, CABEZA_COLUMNA_FONT));
        cell.setColspan(10);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);
//primera columna de 2     
        int NumCol1 = 2;
        PdfPTable table12 = new PdfPTable(NumCol1);
        table12.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase(tit1, DATO_FONT));
        cell.setColspan(2);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table12.addCell(cell);

        cell = new PdfPCell(new Phrase(a, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table12.addCell(cell);

        cell = new PdfPCell(new Phrase(b, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table12.addCell(cell);

        cell = new PdfPCell(table12);
        cell.setColspan(2);
        table11.addCell(cell);
//segunda columna de 2       
        table12 = new PdfPTable(NumCol1);
        table12.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase(tit2, DATO_FONT));
        cell.setColspan(2);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table12.addCell(cell);

        cell = new PdfPCell(new Phrase(c, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table12.addCell(cell);

        cell = new PdfPCell(new Phrase(d, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table12.addCell(cell);

        cell = new PdfPCell(table12);
        cell.setColspan(2);
        table11.addCell(cell);

//tercera columna de 2       
        table12 = new PdfPTable(NumCol1);
        table12.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase(tit3, DATO_FONT));
        cell.setColspan(2);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table12.addCell(cell);

        cell = new PdfPCell(new Phrase(e, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table12.addCell(cell);

        cell = new PdfPCell(new Phrase(f, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table12.addCell(cell);

        cell = new PdfPCell(table12);
        cell.setColspan(2);
        table11.addCell(cell);

        //cuarta columna de 2       
        table12 = new PdfPTable(NumCol1);
        table12.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase(tit4, DATO_FONT));
        cell.setColspan(2);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table12.addCell(cell);

        cell = new PdfPCell(new Phrase(g, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table12.addCell(cell);

        cell = new PdfPCell(new Phrase(h, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table12.addCell(cell);

        cell = new PdfPCell(table12);
        cell.setColspan(2);
        table11.addCell(cell);

        //quinta columna de 2       
        table12 = new PdfPTable(NumCol1);
        table12.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase(tit5, DATO_FONT));
        cell.setColspan(2);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table12.addCell(cell);

        cell = new PdfPCell(new Phrase(i, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table12.addCell(cell);

        cell = new PdfPCell(new Phrase(j, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table12.addCell(cell);

        cell = new PdfPCell(table12);
        cell.setColspan(2);
        table11.addCell(cell);

        cell = new PdfPCell(table11);
        cell.setColspan(10);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
    }

    public static void addfila12(PdfPTable table, String a) {

        PdfPCell cell;
        PdfPTable table1 = new PdfPTable(12);

        cell = new PdfPCell(new Phrase(a, CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(12);
        table1.addCell(cell);

        addtitulofila2(table1, "< 1 año", "M", "F");
        addtitulofila2(table1, "1 a 4 años", "M", "F");
        addtitulofila2(table1, "5 a 9 años", "M", "F");
        addtitulofila2(table1, "10 a 20", "M", "F");
        addtitulofila2(table1, "21 a 59", "M", "F");
        addtitulofila2(table1, "60 a mas", "M", "F");

        cell = new PdfPCell(table1);
        cell.setColspan(12);
        table.addCell(cell);
    }

    public static void addfila42(PdfPTable table, String a) {

        PdfPCell cell;
        PdfPTable table4 = new PdfPTable(4);

        cell = new PdfPCell(new Phrase(a, CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(4);
        table4.addCell(cell);

        addtitulofila2(table4, "Referido", "M", "F");
        addtitulofila2(table4, "Expontaneo", "M", "F");

        cell = new PdfPCell(table4);
        cell.setColspan(4);
        table.addCell(cell);
    }

    public static void addtitulofila2(PdfPTable table, String cadena, String a, String b) {
        PdfPCell cell;
        int NumColumns = 2;
        PdfPTable table11 = new PdfPTable(NumColumns);
        table11.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase(cadena, CABEZA_COLUMNA_FONT));
        cell.setColspan(2);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase(a, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase(b, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        cell = new PdfPCell(table11);
        cell.setColspan(2);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
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
