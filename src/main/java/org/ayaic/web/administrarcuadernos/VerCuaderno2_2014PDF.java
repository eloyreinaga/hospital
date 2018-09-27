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

public class VerCuaderno2_2014PDF extends AbstractPdfView {

    private static final Font TITULO_FONT = new Font(Font.HELVETICA, 12, Font.BOLD, Color.black);
    private static final Font CABEZA_COLUMNA_FONT = new Font(Font.HELVETICA, 8, Font.ITALIC, Color.black);
    private static final Font DATO_FONT = new Font(Font.HELVETICA, 8, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_P = new Font(Font.HELVETICA, 6, Font.NORMAL, Color.black);
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
        DecimalFormat df = new DecimalFormat("###,##0");
        DecimalFormat dfx = new DecimalFormat("###,##0.0");

        int[] sumas = {0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0};
        int[] sumbs = {0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0};
        int[] sumcs = {0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0};
        int una, filas = 30;

        if (lFopos.size() == 0) {
            Paragraph p = new Paragraph("No existe Datos C.Prenatal-Parto-Puerperio-PF 2018", new Font(Font.HELVETICA, 22, Font.BOLDITALIC, new Color(0, 0, 0)));
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

            int NumColumns = 36;
            PdfPTable table = new PdfPTable(NumColumns);
            int[] fwidths = {10, 16, 17, 22, 57, 8, 8, 8, 8, 8,  8, 8, 8, 8, 8, 8, 8, 8, 8, 8,  8, 8, 8, 8, 8, 8, 8, 8, 8, 8,  8, 8, 8, 8, 8, 10};
            table.setWidths(fwidths);
            table.setWidthPercentage(100);

            cell = new PdfPCell(new Phrase("CUADERNO No. 3 CONTROL PRENATAL, PARTO, PUERPERIO, RECIEN NACIDO ( ABORTO Y MICRONUTRIENTES ) Y PLANIFICACION FAMILIAR ", TITULO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(NumColumns);
            table.addCell(cell);

            String sCampos[] = {"No", "FECHA", "Numero de\nHistoria\nClinica", "Numero de\nCarnet de\nAsegurado", "NOMBRE", "Edad1", "Edad2", "Edad3", "Peso", "Talla", "FUM", "No", "Semanas de\nGestacion", "1", "2", "3", "4", "Antes del 5 mes", "A partir del 5 mes", "Repetidos", "Con 4 Controles", "P.A.P", "Preeclampsia Sev. Eclampsia", "Hemorragias acabaron", "Hemorragias Siguen", "Hemorragia parto post parto", "Atendico por"};
            // coloca la cabecera de titulos
            for (int i = 0; i < 5; i++) {
                cell = new PdfPCell(new Phrase(sCampos[i], CABEZA_COLUMNA_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
            }
            addtitulofila5(table, "Edad (en años)", "<15", "15-19", "20-34", "35-49", "50y+");

            cell = new PdfPCell(new Phrase("Atencion \nBJA", CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Peso\n(Kg)", CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Talla\n(cm)", CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            addtituloestadonutri(table, "ESTADO NUTRICIONAL", "Obesidad(O)", "Sobrepeso(S)", "Normal(N)", "Desnutrida(D)");

            addtituloprenatal(table);

            cell = new PdfPCell(new Phrase("Muestra PAP tomadas", CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Resultados PAP\n(+)", CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("IVAA", CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("IVAA\n(+)", CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Ex ma sosp. nod neopl", CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Hmr 1/2 embzo <22s abto", CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Hmr parto post-parto", CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Gestates puerper sepsis", CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Preeclampsia severa", CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Eclampsia", CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("ID", CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            for (int i = 36; i < NumColumns; i++) {
                cell = new PdfPCell(new Phrase(sCampos[i], CABEZA_COLUMNA_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
            }

            // coloca el detalle de loS datos
            for (int i = pag * filas + 0; i < pag * filas + filas && i < lFopos.size(); i++) {

                Cuadernos datopac = (Cuadernos) lFopos.get(i);

                cell = new PdfPCell(new Phrase(Integer.toString(i + 1), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(format(datopac.getFecha(), "dd/MM/yy"), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(Integer.toString(datopac.getHcl()), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(datopac.getRegistro(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((datopac.getNombres().length() > 25) ? datopac.getNombres().substring(0, 25) : datopac.getNombres(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((datopac.getEdad() < 15) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[0] += (datopac.getEdad() < 15) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getEdad() >= 15 && datopac.getEdad() < 20) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[1] += (datopac.getEdad() >= 15 && datopac.getEdad() < 20) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getEdad() >= 20 && datopac.getEdad() < 35) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[2] += (datopac.getEdad() >= 20 && datopac.getEdad() < 35) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getEdad() >= 35 && datopac.getEdad() < 50) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[3] += (datopac.getEdad() >= 35 && datopac.getEdad() < 50) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getEdad() > 49) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[4] += (datopac.getEdad() > 49) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getSuma20() == 1 ) ? Integer.toString(datopac.getSuma20()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[5] += (datopac.getSuma20() == 1 ) ? 1 : 0;

                cell = new PdfPCell(new Phrase(df.format(datopac.getPeso()), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[6] += 1;

                cell = new PdfPCell(new Phrase(df.format(datopac.getTalla()), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[7] += 1;
                //FUM         
                cell = new PdfPCell(new Phrase((datopac.getImc() == 4) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[8] += (datopac.getImc() == 4 && (datopac.getSuma1() == 1 || datopac.getSuma2() == 1)) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getImc() == 3) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[9] += (datopac.getImc() == 3 && (datopac.getSuma1() == 1 || datopac.getSuma2() == 1)) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getImc() == 2) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[10] += (datopac.getImc() == 2 && (datopac.getSuma1() == 1 || datopac.getSuma2() == 1)) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getImc() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[11] += (datopac.getImc() == 1 && (datopac.getSuma1() == 1 || datopac.getSuma2() == 1)) ? 1 : 0;
                ////controles prenatales  
                /////nuevos dentro < 5
                cell = new PdfPCell(new Phrase((datopac.getSuma1() == 1 && datopac.getTipo_egreso() == 0) ? Integer.toString(datopac.getNumpieza()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[12] += (datopac.getSuma1() == 1 && datopac.getTipo_egreso() == 0) ? 1 : 0;
                /////nuevos fuera < 5
                cell = new PdfPCell(new Phrase((datopac.getSuma1() == 1 && datopac.getTipo_egreso() == 1) ? Integer.toString(datopac.getNumpieza()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[13] += (datopac.getSuma1() == 1 && datopac.getTipo_egreso() == 1) ? 1 : 0;
                /////nuevos dentro > 5
                cell = new PdfPCell(new Phrase((datopac.getSuma2() == 1 && datopac.getTipo_egreso() == 0) ? Integer.toString(datopac.getNumpieza()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[14] += (datopac.getSuma2() == 1 && datopac.getTipo_egreso() == 0) ? 1 : 0;
                /////nuevos fuera > 5
                cell = new PdfPCell(new Phrase((datopac.getSuma2() == 1 && datopac.getTipo_egreso() == 1) ? Integer.toString(datopac.getNumpieza()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[15] += (datopac.getSuma2() == 1 && datopac.getTipo_egreso() == 1) ? 1 : 0;
                /////repetido dentro 
                cell = new PdfPCell(new Phrase((datopac.getSuma3() == 1 && datopac.getTipo_egreso() == 0) ? Integer.toString(datopac.getNumpieza()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[16] += (datopac.getSuma3() == 1 && datopac.getTipo_egreso() == 0) ? 1 : 0;
                /////repetido fuera
                cell = new PdfPCell(new Phrase((datopac.getSuma3() == 1 && datopac.getTipo_egreso() == 1) ? Integer.toString(datopac.getNumpieza()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[17] += (datopac.getSuma3() == 1 && datopac.getTipo_egreso() == 1) ? 1 : 0;
                /////cuarto dentro 
                cell = new PdfPCell(new Phrase((datopac.getSuma4() == 1 && datopac.getTipo_egreso() == 0) ? Integer.toString(datopac.getNumpieza()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[18] += (datopac.getSuma4() == 1 && datopac.getTipo_egreso() == 0) ? 1 : 0;
                /////cuarto fuera
                cell = new PdfPCell(new Phrase((datopac.getSuma4() == 1 && datopac.getTipo_egreso() == 1) ? Integer.toString(datopac.getNumpieza()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[19] += (datopac.getSuma4() == 1 && datopac.getTipo_egreso() == 1) ? 1 : 0;
/////fin de controles prenatales
                cell = new PdfPCell(new Phrase((datopac.getPap() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[20] += datopac.getPap();
                /////res_pap
                cell = new PdfPCell(new Phrase((datopac.getSuma12() == 2) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[21] += (datopac.getSuma12() == 2) ? 1 : 0;
                /////ivaa
                cell = new PdfPCell(new Phrase((datopac.getSuma13() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[22] += (datopac.getSuma13() == 1) ? 1 : 0;
                /////res_ivaa
                cell = new PdfPCell(new Phrase((datopac.getSuma15() == 2) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[23] += (datopac.getSuma15() == 2) ? 1 : 0;
                /////examen mamas
                cell = new PdfPCell(new Phrase((datopac.getSuma16() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[24] += (datopac.getSuma16() == 1) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getAborto() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[25] += (datopac.getAborto() == 1) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getAborto() == 2) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[26] += (datopac.getAborto() == 2) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getAborto() == 3) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[27] += (datopac.getAborto() == 3) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getEclam() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[28] += (datopac.getEclam() == 1) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getEclam() == 2) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[29] += (datopac.getEclam() == 2) ? 1 : 0;

                cell = new PdfPCell(new Phrase(Integer.toString(datopac.getId_persona()), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

            }

            for (int i = lFopos.size(); i < pag * filas + filas; i++) {
                cell = new PdfPCell(new Phrase(Integer.toString(i + 1), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);
                for (int j = 1; j < NumColumns; j++) {
                    cell = new PdfPCell(new Phrase("", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);
                }
            }
            cell = new PdfPCell(new Phrase("Total", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(5);
            table.addCell(cell);
            for (int j = 0; j < 30; j++) {
                cell = new PdfPCell(new Phrase(Integer.toString(sumas[j]), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
            }

            for (int k = 0; k < 20; k++) { //coloca filas vacias entes de colocar totales
                cell = new PdfPCell(new Phrase("", CABEZA_COLUMNA_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
            }

            document.add(table);
            document.newPage();

            ///Empieza la segunda hoja  
            NumColumns = 42;
            table = new PdfPTable(NumColumns);
            int[] fwdidths = {10, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 6, 6, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8};
            table.setWidths(fwdidths);
            table.setWidthPercentage(100);

            cell = new PdfPCell(new Phrase("CUADERNO No 3.  PARTO Y RECIEN NACIDO                                             HOJA 2", TITULO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(42);
            table.addCell(cell);

            // coloca la cabecera de titulos
            cell = new PdfPCell(new Phrase("No", CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            addtitulofila5(table, "Atencion del Parto", "Proveedor calificado", "Personal salud calificado", "Partera Capacitada", "Partera Empï¿½rica", "Por Otros");

            addtituloparto(table, "PARTO EN", "Tipo de parto", "Vaginal", "Cesarea", "Tipo Anestesia", "General", "Local Region");
            addtituloparto(table, "SERVICIO", "< 2500gr", "Nacido Vivo", "Nacido Muerto", "> 2500gr", "Nacido Vivo", "Nacido Muerto");

            addtitulofila2(table, "SEXO", "M", "F");

            addtitulopardomi(table, "PARTO DOMICILIO", "RN <2500 gr.", "N. Vivo", "N. Muerto", "RN >2500 gr.", "N. Vivo", "N. Muerto", "Atendido Partera", "N. Vivo", "N. Muerto", "Atendido por Otros", "N.Vivo", "N. Muerto");

            cell = new PdfPCell(new Phrase("RN con malformacion congï¿½nita", CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(" Nac vivs mujs 15 a 49 años 4 CPN", CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_TOP);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Lactacia materna inmediata", CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("RN con apego precoz", CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            addtitulofila3(table, "CONTROL POST-PARTO", "RN visita <48 hrs nacd", "Mujeres ctr.<48 hrs parto", "2do o mas");

            addtitulofila2(table, "Dosis de hierro (90 tabletas sulfato ferroso)", "Embarazadas", "Puerperas");

            cell = new PdfPCell(new Phrase("Vitamin A (Puerperas)", CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Alim. Comp. Mujr embar. desnt", CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            addtitulofila3(table, "Regtro de Mortalidad Fuera del Estab.", "Materna", "M. fetal (obito)", "M. neonatal (0-6 dias)");

            addtituloestadonutri(table, "Referencias Contrareferencias", "Pac refr otrs estb", "Pac contref estb", "Pac refr comund med trad", "Pac refr medcina trad");

            // coloca el detalle de loS datos
            for (int i = pag * filas + 0; i < pag * filas + filas && i < lFopos.size(); i++) {

                Cuadernos datopac = (Cuadernos) lFopos.get(i);

                cell = new PdfPCell(new Phrase(Integer.toString(i + 1), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((datopac.getParto() == 1 || datopac.getParto() == 2 || datopac.getParto() == 3) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[0] += (datopac.getParto() == 1 || datopac.getParto() == 2 || datopac.getParto() == 3) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getParto() == 4) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[1] += (datopac.getParto() == 4) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getParto() == 5) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[2] += (datopac.getParto() == 5) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getParto() == 6) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[3] += (datopac.getParto() == 6) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getParto() == 7) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[4] += (datopac.getParto() == 7) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getParto() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[5] += (datopac.getParto() == 1) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getParto() == 2) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[6] += (datopac.getParto() == 2) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getAnestesia() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[7] += (datopac.getAnestesia() == 1) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getAnestesia() == 2) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[8] += (datopac.getAnestesia() == 2) ? 1 : 0;

                cell = new PdfPCell(new Phrase((("V".equals(datopac.getEstado())) && datopac.getParto() < 3 && ("1".equals(datopac.getPesonac()))) ? Integer.toString(datopac.getAuto()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[9] += (("V".equals(datopac.getEstado())) && ("1".equals(datopac.getPesonac()))) ? 1 : 0;

                cell = new PdfPCell(new Phrase((("M".equals(datopac.getEstado())) && datopac.getParto() < 3 && ("1".equals(datopac.getPesonac()))) ? Integer.toString(datopac.getAuto()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[10] += (("M".equals(datopac.getEstado())) && ("1".equals(datopac.getPesonac()))) ? 1 : 0;

                cell = new PdfPCell(new Phrase((("V".equals(datopac.getEstado())) && datopac.getParto() < 3 && ("2".equals(datopac.getPesonac()))) ? Integer.toString(datopac.getAuto()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[11] += (("V".equals(datopac.getEstado())) && datopac.getParto() < 3 && ("2".equals(datopac.getPesonac()))) ? 1 : 0;

                cell = new PdfPCell(new Phrase((("M".equals(datopac.getEstado())) && datopac.getParto() < 3 && ("2".equals(datopac.getPesonac()))) ? Integer.toString(datopac.getAuto()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[12] += (("M".equals(datopac.getEstado())) && datopac.getParto() < 3 && ("2".equals(datopac.getPesonac()))) ? 1 : 0;
                //sexo    
                cell = new PdfPCell(new Phrase((datopac.getSexo() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[13] += (datopac.getSexo() == 1) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getSexo() == 2) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[14] += (datopac.getSexo() == 2) ? 1 : 0;
//parto por personal de salud                                  
                cell = new PdfPCell(new Phrase((("V".equals(datopac.getEstado())) && (datopac.getParto() == 3 || datopac.getParto() == 4 || datopac.getParto() == 5 || datopac.getParto() == 6) && ("1".equals(datopac.getPesonac()))) ? Integer.toString(datopac.getAuto()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[15] += ("V".equals(datopac.getEstado())) && (datopac.getParto() == 3 || datopac.getParto() == 4 || datopac.getParto() == 5 || datopac.getParto() == 6) && ("1".equals(datopac.getPesonac())) ? 1 : 0;

                cell = new PdfPCell(new Phrase((("M".equals(datopac.getEstado())) && (datopac.getParto() == 3 || datopac.getParto() == 4 || datopac.getParto() == 5 || datopac.getParto() == 6) && ("1".equals(datopac.getPesonac()))) ? Integer.toString(datopac.getAuto()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[16] += ("M".equals(datopac.getEstado())) && (datopac.getParto() == 3 || datopac.getParto() == 4 || datopac.getParto() == 5 || datopac.getParto() == 6) && ("1".equals(datopac.getPesonac())) ? 1 : 0;

                cell = new PdfPCell(new Phrase((("V".equals(datopac.getEstado())) && (datopac.getParto() == 3 || datopac.getParto() == 4 || datopac.getParto() == 5 || datopac.getParto() == 6) && ("2".equals(datopac.getPesonac()))) ? Integer.toString(datopac.getAuto()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[17] += ("V".equals(datopac.getEstado())) && (datopac.getParto() == 3 || datopac.getParto() == 4 || datopac.getParto() == 5 || datopac.getParto() == 6) && ("2".equals(datopac.getPesonac())) ? 1 : 0;

                cell = new PdfPCell(new Phrase((("M".equals(datopac.getEstado())) && (datopac.getParto() == 3 || datopac.getParto() == 4 || datopac.getParto() == 5 || datopac.getParto() == 6) && ("2".equals(datopac.getPesonac()))) ? Integer.toString(datopac.getAuto()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[18] += ("M".equals(datopac.getEstado())) && (datopac.getParto() == 3 || datopac.getParto() == 4 || datopac.getParto() == 5 || datopac.getParto() == 6) && ("2".equals(datopac.getPesonac())) ? 1 : 0;

                cell = new PdfPCell(new Phrase(("V".equals(datopac.getEstado()) && (datopac.getParto() == 4)) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[19] += ("V".equals(datopac.getEstado()) && (datopac.getParto() == 4)) ? 1 : 0;

                cell = new PdfPCell(new Phrase(("M".equals(datopac.getEstado()) && (datopac.getParto() == 4)) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[20] += ("M".equals(datopac.getEstado()) && (datopac.getParto() == 4)) ? 1 : 0;

                cell = new PdfPCell(new Phrase(("V".equals(datopac.getEstado()) && (datopac.getParto() == 3)) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[21] += ("V".equals(datopac.getEstado()) && (datopac.getParto() == 3)) ? 1 : 0;

                cell = new PdfPCell(new Phrase(("M".equals(datopac.getEstado()) && (datopac.getParto() == 3)) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[22] += ("M".equals(datopac.getEstado()) && (datopac.getParto() == 3)) ? 1 : 0;
                ////malformacon congenita     
                cell = new PdfPCell(new Phrase((datopac.getSuma51() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);
                sumbs[23] += (datopac.getSuma51() == 1) ? 1 : 0;
                ////
                cell = new PdfPCell(new Phrase((datopac.getNumpieza() > 3 && datopac.getParto() > 0) ? Integer.toString(datopac.getNumpieza()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[24] += (datopac.getNumpieza() > 3 && datopac.getParto() > 0) ? 1 : 0;
                ////Lactacia materna inmediata      
                cell = new PdfPCell(new Phrase(((datopac.getSuma18() == 1)) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[25] += (datopac.getSuma18() == 1) ? 1 : 0;
                ///RN con apego precoz   
                cell = new PdfPCell(new Phrase(((datopac.getSuma19() == 1)) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[26] += (datopac.getSuma19() == 1) ? 1 : 0;
                ///RN con visita de control en las 48 hrs siguientes al nacimiento 
                cell = new PdfPCell(new Phrase((datopac.getSfpuerpera() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[27] += (datopac.getSfpuerpera() == 1) ? 1 : 0;

                cell = new PdfPCell(new Phrase(((datopac.getControlpos() == 1)) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[28] += ((datopac.getControlpos() == 1)) ? 1 : 0;

                cell = new PdfPCell(new Phrase(((datopac.getControlpos() == 2)) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[29] += ((datopac.getControlpos() == 2)) ? 1 : 0;
                //sulfatos ferrosos          
                cell = new PdfPCell(new Phrase((datopac.getSfembarazada() == 1) ? Integer.toString(datopac.getTabletas()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[30] += (datopac.getSfembarazada() == 1) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getSfpuerpera() == 1) ? Integer.toString(datopac.getTabletas()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[31] += (datopac.getSfpuerpera() == 1) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getVitamina() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[32] += (datopac.getVitamina() == 1) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getVitamina() == 2) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[33] += (datopac.getVitamina() == 2) ? 1 : 0;
                ////registro mortalidad fuera establecimiento  
                cell = new PdfPCell(new Phrase("", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);
                sumbs[34] += (datopac.getSuma51() == 1) ? 1 : 0;

                cell = new PdfPCell(new Phrase("", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);
                sumbs[35] += (datopac.getParto() == 10) ? 1 : 0;

                cell = new PdfPCell(new Phrase("", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);
                sumbs[36] += (datopac.getParto() == 10) ? 1 : 0;

                ///referencias y contrareferencias 
                if (datopac.getSuma51() == 1) {
                    cell = new PdfPCell(new Phrase("1", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);
                    sumbs[37] += (datopac.getSuma51() == 1) ? 1 : 0;
                } else {
                    cell = new PdfPCell(new Phrase("", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);
                }
                if (datopac.getSuma51() == 2) {
                    cell = new PdfPCell(new Phrase(datopac.getReferido()+"\n"+datopac.getSuma30(), DATO_FONT_P));
                    cell = new PdfPCell(new Phrase(datopac.getReferido(), DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);
                    sumbs[38] += (datopac.getSuma51() == 2) ? 1 : 0;
                } else {
                    cell = new PdfPCell(new Phrase("", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);
                }
                if (datopac.getSuma51() == 3) {
                    cell = new PdfPCell(new Phrase("1", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);
                    sumbs[39] += (datopac.getSuma51() == 3) ? 1 : 0;
                } else {
                    cell = new PdfPCell(new Phrase("", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);
                }
                if (datopac.getSuma51() == 4) {
                    cell = new PdfPCell(new Phrase("1", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);
                    sumbs[40] += (datopac.getSuma51() == 4) ? 1 : 0;
                } else {
                    cell = new PdfPCell(new Phrase("", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);
                }
            }

            for (int i = lFopos.size(); i < pag * filas + filas; i++) {
                cell = new PdfPCell(new Phrase(Integer.toString(i + 1), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);
                for (int j = 1; j < NumColumns; j++) {
                    cell = new PdfPCell(new Phrase("", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);
                }
            }

            cell = new PdfPCell(new Phrase("Total", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            for (int j = 0; j < 40; j++) {
                cell = new PdfPCell(new Phrase(Integer.toString(sumbs[j]), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
            }

            for (int j = 1; j < 15; j++) {
                cell = new PdfPCell(new Phrase("", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);
            }

            document.add(table);
            document.newPage();

            ////em peza la tercera Hoja
            NumColumns = 33;
            table = new PdfPTable(NumColumns);
            int[] fwdidths3 = {10, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8};
            table.setWidths(fwdidths3);
            table.setWidthPercentage(100);

            cell = new PdfPCell(new Phrase("CUADERNO No 3.  PLANIFICACION FAMILIAR                                            HOJA 3", TITULO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(33);
            table.addCell(cell);

            // coloca la cabecera de titulos
            cell = new PdfPCell(new Phrase("No", CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Orientacion", CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            addtitulofila6(table, "Metodos Anticonceptivos Modernos", "DIU", "N", "C", "Ins", "Inyectable Trimestral", "N", "C", "Ins");

            addtitulofila18(table, "Metodos Anticonceptivos Modernos", "Condon Masculino", "N", "C", "Ins", "Condon Femenino", "N", "C", "Ins", "Pildora", "N", "C", "Ins", "Implante subdermico", "N", "C", "Ins", "Pildra Emerg", "AQV", "M", "F", "Otros mï¿½todos", "N", "C", "Ins");

            addtitulofila6b(table, "Metodos Naturales", "Mela", "N", "C", "Ritmo", "N", "C", "Dias Fijos", "N", "C");

            cell = new PdfPCell(new Phrase("ID", CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            // coloca el detalle de loS datos
            for (int i = pag * filas + 0; i < pag * filas + filas && i < lFopos.size(); i++) {

                Cuadernos datopac = (Cuadernos) lFopos.get(i);

                cell = new PdfPCell(new Phrase(Integer.toString(i + 1), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((datopac.getOrientacion() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumcs[0] += datopac.getOrientacion();

                cell = new PdfPCell(new Phrase((datopac.getDiu() == 1 && "N".equals(datopac.getTipoconsulta())) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumcs[1] += (datopac.getDiu() == 1 && "N".equals(datopac.getTipoconsulta())) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getDiu() == 1 && "C".equals(datopac.getTipoconsulta())) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumcs[2] += (datopac.getDiu() == 1 && "C".equals(datopac.getTipoconsulta())) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getDiu() == 1 && datopac.getInsumos() != 0) ? Integer.toString(datopac.getInsumos()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumcs[3] += (datopac.getDiu() == 1 && datopac.getInsumos() != 0) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getInyectable() == 1 && "N".equals(datopac.getTipoconsulta())) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumcs[4] += (datopac.getInyectable() == 1 && "N".equals(datopac.getTipoconsulta())) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getInyectable() == 1 && "C".equals(datopac.getTipoconsulta())) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumcs[5] += (datopac.getInyectable() == 1 && "C".equals(datopac.getTipoconsulta())) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getInyectable() == 1 && datopac.getInsumos() != 0) ? Integer.toString(datopac.getInsumos()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumcs[6] += (datopac.getInyectable() == 1 && datopac.getInsumos() != 0) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getCondon() == 1 && "N".equals(datopac.getTipoconsulta())) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumcs[7] += (datopac.getCondon() == 1 && "N".equals(datopac.getTipoconsulta())) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getCondon() == 1 && "C".equals(datopac.getTipoconsulta())) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumcs[8] += (datopac.getCondon() == 1 && "C".equals(datopac.getTipoconsulta())) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getCondon() == 1 && datopac.getInsumos() != 0) ? Integer.toString(datopac.getInsumos()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumcs[9] += (datopac.getCondon() == 1 && datopac.getInsumos() != 0) ? datopac.getInsumos() : 0;

                cell = new PdfPCell(new Phrase((datopac.getOtras() == 5 && "N".equals(datopac.getTipoconsulta())) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumcs[10] += (datopac.getOtras() == 5 && "N".equals(datopac.getTipoconsulta())) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getOtras() == 5 && "C".equals(datopac.getTipoconsulta())) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumcs[11] += (datopac.getOtras() == 5 && "C".equals(datopac.getTipoconsulta())) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getOtras() == 5 && datopac.getInsumos() != 0) ? Integer.toString(datopac.getInsumos()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumcs[12] += (datopac.getOtras() == 5 && datopac.getInsumos() != 0) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getPildora() == 1 && "N".equals(datopac.getTipoconsulta())) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumcs[13] += (datopac.getPildora() == 1 && "N".equals(datopac.getTipoconsulta())) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getPildora() == 1 && "C".equals(datopac.getTipoconsulta())) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumcs[14] += (datopac.getPildora() == 1 && "C".equals(datopac.getTipoconsulta())) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getPildora() == 1 && datopac.getInsumos() != 0) ? Integer.toString(datopac.getInsumos()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumcs[15] += (datopac.getPildora() == 1 && datopac.getInsumos() != 0) ? datopac.getInsumos() : 0;
                ///implante subcutaneo      
                cell = new PdfPCell(new Phrase((datopac.getOtras() == 6 && "N".equals(datopac.getTipoconsulta())) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumcs[16] += (datopac.getOtras() == 6 && "N".equals(datopac.getTipoconsulta())) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getOtras() == 6 && "C".equals(datopac.getTipoconsulta())) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumcs[17] += (datopac.getOtras() == 6 && "C".equals(datopac.getTipoconsulta())) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getOtras() == 6 && datopac.getInsumos() != 0) ? Integer.toString(datopac.getInsumos()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumcs[18] += (datopac.getOtras() == 6 && datopac.getInsumos() != 0) ? 1 : 0;
//metodo pildora emergencia                
                cell = new PdfPCell(new Phrase((datopac.getPildora() == 2) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumcs[19] += (datopac.getPildora() == 2) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getAqv() == 1 && datopac.getId_tipo_sexo() == 2) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumcs[20] += (datopac.getAqv() == 1 && datopac.getId_tipo_sexo() == 2) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getAqv() == 1 && datopac.getId_tipo_sexo() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumcs[21] += (datopac.getAqv() == 1 && datopac.getId_tipo_sexo() == 1) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getOtras() == 1 && "N".equals(datopac.getTipoconsulta())) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumcs[22] += (datopac.getOtras() == 1 && "N".equals(datopac.getTipoconsulta())) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getOtras() == 1 && "C".equals(datopac.getTipoconsulta())) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumcs[23] += (datopac.getOtras() == 1 && "C".equals(datopac.getTipoconsulta())) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getOtras() == 1 && datopac.getInsumos() != 0) ? Integer.toString(datopac.getInsumos()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumcs[24] += (datopac.getOtras() == 1 && datopac.getInsumos() != 0) ? 1 : 0;
//Arreglar metodos naturales 

                cell = new PdfPCell(new Phrase((datopac.getMnatural() == 1 && "N".equals(datopac.getTipoconsulta())) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumcs[25] += (datopac.getMnatural() == 1 && "N".equals(datopac.getTipoconsulta())) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getMnatural() == 1 && "C".equals(datopac.getTipoconsulta())) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumcs[26] += (datopac.getMnatural() == 1 && "C".equals(datopac.getTipoconsulta())) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getMnatural() == 2 && "N".equals(datopac.getTipoconsulta())) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumcs[27] += (datopac.getMnatural() == 2 && "N".equals(datopac.getTipoconsulta())) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getMnatural() == 2 && "C".equals(datopac.getTipoconsulta())) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumcs[28] += (datopac.getMnatural() == 2 && "C".equals(datopac.getTipoconsulta())) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getMnatural() == 3 && "N".equals(datopac.getTipoconsulta())) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumcs[29] += (datopac.getMnatural() == 3 && "N".equals(datopac.getTipoconsulta())) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getMnatural() == 3 && "C".equals(datopac.getTipoconsulta())) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumcs[30] += (datopac.getMnatural() == 3 && "C".equals(datopac.getTipoconsulta())) ? 1 : 0;

                cell = new PdfPCell(new Phrase(Integer.toString(datopac.getId_persona()), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(cell);
            }

            for (int i = lFopos.size(); i < pag * filas + filas; i++) {
                cell = new PdfPCell(new Phrase(Integer.toString(i + 1), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);
                for (int j = 1; j < NumColumns; j++) {
                    cell = new PdfPCell(new Phrase("", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);
                }
            }

            cell = new PdfPCell(new Phrase("Total", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            for (int j = 0; j < 31; j++) {
                cell = new PdfPCell(new Phrase(Integer.toString(sumcs[j]), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
            }

            for (int j = 1; j < 15; j++) {
                cell = new PdfPCell(new Phrase("", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);
            }

            document.add(table);
            document.newPage();

        }
    }

    public static void addtituloestadonutri(PdfPTable table, String tit, String a, String b, String c, String d) {
        PdfPCell cell;
        int NumColumns = 4;
        PdfPTable table11 = new PdfPTable(NumColumns);
        table11.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase(tit, CABEZA_COLUMNA_FONT));
        cell.setColspan(4);
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

        cell = new PdfPCell(table11);
        cell.setColspan(4);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
    }

    public static void addtituloprenatal(PdfPTable table) {
        PdfPCell cell;
        int NumColumns = 8;
        PdfPTable table11 = new PdfPTable(NumColumns);
        table11.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("CONTROL PRENATAL", CABEZA_COLUMNA_FONT));
        cell.setColspan(8);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        int NumCol = 4;
        PdfPTable table12 = new PdfPTable(NumCol);
        table12.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("NUEVOS", DATO_FONT));
        cell.setColspan(4);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table12.addCell(cell);

        addtitulofila2(table12, "< del 5 Mes", "D", "F");
        addtitulofila2(table12, "> del 5 Mes", "D", "F");

        cell = new PdfPCell(table12);
        cell.setColspan(4);
        table11.addCell(cell);

        addtitulofila2(table11, "Repetidos", "D", "F");
        addtitulofila2(table11, "4 Controles", "D", "F");

        cell = new PdfPCell(table11);
        cell.setColspan(8);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
    }

    public static void addtituloparto(PdfPTable table, String cadena, String tit1, String a, String b, String tit2, String c, String d) {
        PdfPCell cell;
        int NumColumns = 4;
        PdfPTable table11 = new PdfPTable(NumColumns);
        table11.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase(cadena, CABEZA_COLUMNA_FONT));
        cell.setColspan(4);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        int NumCol = 2;
        PdfPTable table12 = new PdfPTable(NumCol);
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

        table12 = new PdfPTable(NumCol);
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

    public static void addtitulopardomi(PdfPTable table, String cadena, String tit1, String a, String b, String tit2, String c, String d, String tit3, String e, String f, String tit4, String g, String h) {

        PdfPCell cell;
        int NumColumns = 8;
        PdfPTable table11 = new PdfPTable(NumColumns);
        table11.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase(cadena, CABEZA_COLUMNA_FONT));
        cell.setColspan(8);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        int NumCol = 2;
        PdfPTable table112 = new PdfPTable(NumCol);
        table112.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase(tit1, DATO_FONT));
        cell.setColspan(2);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table112.addCell(cell);

        cell = new PdfPCell(new Phrase(a, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table112.addCell(cell);

        cell = new PdfPCell(new Phrase(b, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table112.addCell(cell);

        cell = new PdfPCell(table112);
        cell.setColspan(2);
        table11.addCell(cell);

        int NumCol1 = 2;
        PdfPTable table12 = new PdfPTable(NumCol1);
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

        int NumCol2 = 2;
        PdfPTable table113 = new PdfPTable(NumCol2);
        table113.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase(tit4, DATO_FONT));
        cell.setColspan(2);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table113.addCell(cell);

        cell = new PdfPCell(new Phrase(g, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table113.addCell(cell);

        cell = new PdfPCell(new Phrase(h, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table113.addCell(cell);

        cell = new PdfPCell(table113);
        cell.setColspan(2);
        table11.addCell(cell);

        cell = new PdfPCell(table11);
        cell.setColspan(8);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
    }

    public static void addtitulofila18(PdfPTable table, String cadena, String tit1, String a, String b, String c, String tit2, String d, String e, String f, String tit3, String g, String h, String i, String tit4, String j, String k, String l, String m, String tit5, String n, String o, String tit6, String p, String q, String r) {
        PdfPCell cell;
        int NumColumns = 18;
        PdfPTable table11 = new PdfPTable(NumColumns);
        table11.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase(cadena, CABEZA_COLUMNA_FONT));
        cell.setColspan(18);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        addtitulofila3(table11, tit1, a, b, c);

        addtitulofila3(table11, tit2, d, e, f);

        addtitulofila3(table11, tit3, g, h, i);

        addtitulofila3(table11, tit4, j, k, l);

        cell = new PdfPCell(new Phrase(m, CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        addtitulofila2(table11, tit5, n, o);

        addtitulofila3(table11, tit6, p, q, r);

        cell = new PdfPCell(table11);
        cell.setColspan(18);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
    }

    public static void addtitulofila6(PdfPTable table, String cadena, String tit1, String a, String b, String c, String tit2, String d, String e, String f) {
        PdfPCell cell;
        int NumColumns = 6;
        PdfPTable table11 = new PdfPTable(NumColumns);
        table11.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase(cadena, CABEZA_COLUMNA_FONT));
        cell.setColspan(6);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        addtitulofila3(table11, tit1, a, b, c);

        addtitulofila3(table11, tit2, d, e, f);

        cell = new PdfPCell(table11);
        cell.setColspan(6);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);

    }

    public static void addtitulofila6b(PdfPTable table, String cadena, String tit1, String a, String b, String tit2, String c, String d, String tit3, String e, String f) {
        PdfPCell cell;
        int NumColumns = 6;
        PdfPTable table11 = new PdfPTable(NumColumns);
        table11.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase(cadena, CABEZA_COLUMNA_FONT));
        cell.setColspan(6);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        addtitulofila2(table11, tit1, a, b);

        addtitulofila2(table11, tit2, c, d);

        addtitulofila2(table11, tit3, e, f);

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

    public static void addtitulofila5(PdfPTable table, String cadena, String a, String b, String c, String d, String e) {
        PdfPCell cell;
        int NumColumns = 5;
        PdfPTable table11 = new PdfPTable(NumColumns);
        table11.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase(cadena, CABEZA_COLUMNA_FONT));
        cell.setColspan(5);
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

        cell = new PdfPCell(table11);
        cell.setColspan(5);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
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
