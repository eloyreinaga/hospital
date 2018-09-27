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

public class VerCuaderno2PDF extends AbstractPdfView {

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

        int[] sumas = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int[] sumbs = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
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

            int NumColumns = 27;
            PdfPTable table = new PdfPTable(NumColumns);
            int[] fwidths = {10, 13, 12, 16, 40, 8, 8, 8, 8, 8, 12, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 6};
            table.setWidths(fwidths);
            table.setWidthPercentage(100);

            cell = new PdfPCell(new Phrase("CUADERNO No. 2 CONTROL PRENATAL, PARTO, PUERPERIO Y RECIEN NACIDO ( ABORTO Y MICRONUTRIENTES ) ", TITULO_FONT));
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
            addtitulofila3(table, "Edad (en años)", "<20", "20-34", "35 y +");

            cell = new PdfPCell(new Phrase("Peso\n(Kg)", CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Talla\n(cm)", CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("F.U.M.", CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("No de Embarazo", CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Semanas de Gestacion", CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            addtituloestadonutri(table);

            addtituloprenatal(table);

            for (int i = 21; i < 23; i++) {
                cell = new PdfPCell(new Phrase(sCampos[i], CABEZA_COLUMNA_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
            }

            addtitulofila2(table, "Hemorragia < 22 semanas", "acabaron aborto", "sigue embarazo");

            for (int i = 25; i < NumColumns; i++) {
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

                cell = new PdfPCell(new Phrase(format(datopac.getFechap(), "dd/MM/yy"), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(Integer.toString(datopac.getHcl()), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(datopac.getRegistro(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((datopac.getNombres().length() > 27) ? datopac.getNombres().substring(0, 27) : datopac.getNombres(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((datopac.getEdad() < 20) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[0] += (datopac.getEdad() < 20) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getEdad() >= 20 && datopac.getEdad() < 35) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[1] += (datopac.getEdad() >= 20 && datopac.getEdad() < 35) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getEdad() > 34) ? Integer.toString(datopac.getEdad()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[2] += (datopac.getEdad() > 34) ? 1 : 0;

                cell = new PdfPCell(new Phrase(Double.toString(datopac.getPeso()), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[3] += 1;

                cell = new PdfPCell(new Phrase(Double.toString(datopac.getTalla()), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[4] += 1;
                //FUM         
                cell = new PdfPCell(new Phrase(format(datopac.getFum(), "dd/MM/yy"), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);
                sumas[5] += 1;

                cell = new PdfPCell(new Phrase(Integer.toString(datopac.getNembarazo()), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[6] += 1;

                cell = new PdfPCell(new Phrase(Integer.toString(datopac.getSemanas()), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[7] += 1;

                cell = new PdfPCell(new Phrase((datopac.getImc() == 4) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[8] += (datopac.getImc() == 4 && (datopac.getSuma1() == 1 || datopac.getSuma2() == 1) && datopac.getTalla() > 0 && datopac.getPeso() > 0) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getImc() == 3) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[9] += (datopac.getImc() == 3 && (datopac.getSuma1() == 1 || datopac.getSuma2() == 1) && datopac.getTalla() > 0 && datopac.getPeso() > 0) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getImc() == 2) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[10] += (datopac.getImc() == 2 && (datopac.getSuma1() == 1 || datopac.getSuma2() == 1) && datopac.getTalla() > 0 && datopac.getPeso() > 0) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getImc() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[11] += (datopac.getImc() == 1 && (datopac.getSuma1() == 1 || datopac.getSuma2() == 1) && datopac.getTalla() > 0 && datopac.getPeso() > 0) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getSuma1() == 1) ? Integer.toString(datopac.getNumpieza()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[12] += datopac.getSuma1();

                cell = new PdfPCell(new Phrase((datopac.getSuma2() == 1) ? Integer.toString(datopac.getNumpieza()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[13] += datopac.getSuma2();

                cell = new PdfPCell(new Phrase((datopac.getSuma3() == 1) ? Integer.toString(datopac.getNumpieza()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[14] += datopac.getSuma3();

                cell = new PdfPCell(new Phrase((datopac.getSuma4() == 1) ? Integer.toString(datopac.getNumpieza()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[15] += datopac.getSuma4();

                cell = new PdfPCell(new Phrase((datopac.getPap() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[16] += datopac.getPap();

                cell = new PdfPCell(new Phrase((datopac.getEclam() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[17] += datopac.getEclam();

                cell = new PdfPCell(new Phrase((datopac.getAborto() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[18] += (datopac.getAborto() == 1) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getAborto() == 2) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[19] += (datopac.getAborto() == 2) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getHemo() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[20] += (datopac.getHemo() == 1) ? 1 : 0;

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
            cell = new PdfPCell(new Phrase("TOTAL", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(5);
            table.addCell(cell);
            for (int j = 0; j < 21; j++) {
                cell = new PdfPCell(new Phrase(Integer.toString(sumas[j]), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(cell);
            }

            for (int k = 0; k < 20; k++) { //coloca filas vacias entes de colocar totales
                cell = new PdfPCell(new Phrase("", CABEZA_COLUMNA_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
            }

            document.add(table);
            document.newPage();

            NumColumns = 31;
            table = new PdfPTable(NumColumns);
            int[] fwdidths = {10, 8, 8, 8, 8, 8, 8, 8, 8, 4, 4, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8};
            table.setWidths(fwdidths);
            table.setWidthPercentage(100);

            cell = new PdfPCell(new Phrase("CUADERNO No 2.  CONTROL PRENATAL, PARTO, PUERPERIO Y RECION NACIDO (ABORTO Y MICRONUTRIENTES)", TITULO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(31);
            table.addCell(cell);

            // coloca la cabecera de titulos
            cell = new PdfPCell(new Phrase("No", CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            addtituloparto(table, "PARTO EN", "Tipo de parto", "Vaginal", "Cesarea", "Tipo Anestesia", "General", "Local o Region");
            addtituloparto(table, "SERVICIO", "< 2500gr", "Nacido Vivo", "Nacido Muerto", "> 2500gr", "Nacido Vivo", "Nacido Muerto");

            addtitulofila2(table, "SEXO", "M", "F");

            addtitulopardomi(table, "PARTO DOMICILIO", "P. S.", "Parto", "P. Salud <2500", "N. Vivo", "Muerto", "P. Salud >2500", "N.Vivo", "Muerto", "Partera Capacitada", "Parto", "N.Vivo", "Muerto");

            addtitulofila2(table, "CONTROL POST-PARTO", "1er", "2do o mas");

            addtitulofila2(table, "Dosis de hierro (90 tabletas sulfato ferroso)", "Embarazadas", "Puerperas");

            cell = new PdfPCell(new Phrase("Vitamin A (Puerperas)", CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("ALIM. COMP. Mujer embar. desnut", CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            addtitulofila2(table, "Complica causa basica muerte", "Materna", "Rec Nac <7dias");

            addtitulofila2(table, "Derivado de: Comunidad partera", "ARO", "Recien Nacido");

            addtitulofila2(table, "Refer.: Est/Loc. (Public. Privado", "ARO", "Recien Nacido");

            // coloca el detalle de loS datos
            for (int i = pag * filas + 0; i < pag * filas + filas && i < lFopos.size(); i++) {

                Cuadernos datopac = (Cuadernos) lFopos.get(i);

                cell = new PdfPCell(new Phrase(Integer.toString(i + 1), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((datopac.getParto() == 1) ? "1" : "", DATO_FONT));///vaginal
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[0] += (datopac.getParto() == 1) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getParto() == 2) ? "1" : "", DATO_FONT));///cesarea
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[1] += (datopac.getParto() == 2) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getAnestesia() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[2] += (datopac.getAnestesia() == 1) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getAnestesia() == 2) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[3] += (datopac.getAnestesia() == 2) ? 1 : 0;

                cell = new PdfPCell(new Phrase((("V".equals(datopac.getEstado())) && datopac.getParto() < 3 && ("1".equals(datopac.getPesonac()))) ? Integer.toString(datopac.getAuto()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[4] += (("V".equals(datopac.getEstado())) && ("1".equals(datopac.getPesonac()))) ? 1 : 0;

                cell = new PdfPCell(new Phrase((("M".equals(datopac.getEstado())) && datopac.getParto() < 3 && ("1".equals(datopac.getPesonac()))) ? Integer.toString(datopac.getAuto()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[5] += (("M".equals(datopac.getEstado())) && ("1".equals(datopac.getPesonac()))) ? 1 : 0;

                cell = new PdfPCell(new Phrase((("V".equals(datopac.getEstado())) && datopac.getParto() < 3 && ("2".equals(datopac.getPesonac()))) ? Integer.toString(datopac.getAuto()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[6] += (("V".equals(datopac.getEstado())) && ("2".equals(datopac.getPesonac()))) ? 1 : 0;

                cell = new PdfPCell(new Phrase((("M".equals(datopac.getEstado())) && datopac.getParto() < 3 && ("2".equals(datopac.getPesonac()))) ? Integer.toString(datopac.getAuto()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[7] += (("M".equals(datopac.getEstado())) && ("2".equals(datopac.getPesonac()))) ? 1 : 0;
                //sexo    
                cell = new PdfPCell(new Phrase((datopac.getSexo() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[8] += (datopac.getSexo() == 1) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getSexo() == 2) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[9] += (datopac.getSexo() == 2) ? 1 : 0;
//parto por personal de salud                                  
                cell = new PdfPCell(new Phrase(((datopac.getParto() == 3)) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[10] += ((datopac.getParto() == 3)) ? 1 : 0;

                cell = new PdfPCell(new Phrase(("V".equals(datopac.getEstado()) && (datopac.getParto() == 3) && ("1".equals(datopac.getPesonac()))) ? Integer.toString(datopac.getAuto()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[11] += ("V".equals(datopac.getEstado()) && (datopac.getParto() == 3) && ("1".equals(datopac.getPesonac()))) ? 1 : 0;

                cell = new PdfPCell(new Phrase(("M".equals(datopac.getEstado()) && (datopac.getParto() == 3) && ("1".equals(datopac.getPesonac()))) ? Integer.toString(datopac.getAuto()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[12] += ("M".equals(datopac.getEstado()) && (datopac.getParto() == 3) && ("1".equals(datopac.getPesonac()))) ? 1 : 0;

                cell = new PdfPCell(new Phrase(("V".equals(datopac.getEstado()) && (datopac.getParto() == 3) && ("2".equals(datopac.getPesonac()))) ? Integer.toString(datopac.getAuto()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[13] += ("V".equals(datopac.getEstado()) && (datopac.getParto() == 3) && ("2".equals(datopac.getPesonac()))) ? 1 : 0;

                cell = new PdfPCell(new Phrase(("M".equals(datopac.getEstado()) && (datopac.getParto() == 3) && ("2".equals(datopac.getPesonac()))) ? Integer.toString(datopac.getAuto()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[14] += ("M".equals(datopac.getEstado()) && (datopac.getParto() == 3) && ("2".equals(datopac.getPesonac()))) ? 1 : 0;

                cell = new PdfPCell(new Phrase(((datopac.getParto() == 4)) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[15] += ((datopac.getParto() == 4)) ? 1 : 0;

                cell = new PdfPCell(new Phrase(("V".equals(datopac.getEstado()) && (datopac.getParto() == 4)) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[16] += ("V".equals(datopac.getEstado()) && (datopac.getParto() == 4)) ? 1 : 0;

                cell = new PdfPCell(new Phrase(("M".equals(datopac.getEstado()) && (datopac.getParto() == 4)) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[17] += ("M".equals(datopac.getEstado()) && (datopac.getParto() == 4)) ? 1 : 0;

                cell = new PdfPCell(new Phrase(((datopac.getControlpos() == 1)) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[18] += ((datopac.getControlpos() == 1)) ? 1 : 0;

                cell = new PdfPCell(new Phrase(((datopac.getControlpos() == 2)) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[19] += ((datopac.getControlpos() == 2)) ? 1 : 0;
                //sulfatos ferrosos          
                cell = new PdfPCell(new Phrase((datopac.getSfembarazada() == 1) ? Integer.toString(datopac.getTabletas()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[20] += (datopac.getSfembarazada() == 1) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getSfpuerpera() == 1) ? Integer.toString(datopac.getTabletas()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[21] += (datopac.getSfpuerpera() == 1) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getVitamina() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[22] += (datopac.getVitamina() == 1) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getVitamina() == 2) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[23] += (datopac.getVitamina() == 2) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getParto() == 5) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);
                sumbs[24] += (datopac.getParto() == 5) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getParto() == 6) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);
                sumbs[25] += (datopac.getParto() == 6) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getParto() == 7) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);
                sumbs[26] += (datopac.getParto() == 7) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getParto() == 8) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);
                sumbs[27] += (datopac.getParto() == 8) ? 1 : 0;

                cell = new PdfPCell(new Phrase(datopac.getReferido(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(datopac.getContraref(), DATO_FONT));
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
            table.addCell(cell);

            for (int j = 0; j < 28; j++) {
                cell = new PdfPCell(new Phrase(Integer.toString(sumbs[j]), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
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

    public static void addtituloestadonutri(PdfPTable table) {
        PdfPCell cell;
        int NumColumns = 4;
        PdfPTable table11 = new PdfPTable(NumColumns);
        table11.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("ESTADO NUTRICIONAL", CABEZA_COLUMNA_FONT));
        cell.setColspan(4);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase("Obesidad(O)", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase("Sobrepeso(s)", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase("Normal(N)", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase("Desnutrida(D)", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        cell = new PdfPCell(table11);
        cell.setColspan(4);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
    }

    public static void addtituloprenatal(PdfPTable table) {
        PdfPCell cell;
        int NumColumns = 4;
        PdfPTable table11 = new PdfPTable(NumColumns);
        table11.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("CONTROL PRENATAL", CABEZA_COLUMNA_FONT));
        cell.setColspan(4);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        int NumCol = 2;
        PdfPTable table12 = new PdfPTable(NumCol);
        table12.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("NUEVOS", DATO_FONT));
        cell.setColspan(2);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table12.addCell(cell);

        cell = new PdfPCell(new Phrase("< del 5 Mes", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table12.addCell(cell);

        cell = new PdfPCell(new Phrase("> del 5 Mes", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table12.addCell(cell);

        cell = new PdfPCell(table12);
        cell.setColspan(2);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase("Repetidos", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase("Con 4 Controles", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        cell = new PdfPCell(table11);
        cell.setColspan(4);
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

    public static void addtitulopardomi(PdfPTable table, String cadena, String tit, String aa, String tit1, String a, String b, String tit2, String c, String d, String tit3, String e, String f, String g) {

        PdfPCell cell;
        int NumColumns = 8;
        PdfPTable table11 = new PdfPTable(NumColumns);
        table11.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase(cadena, CABEZA_COLUMNA_FONT));
        cell.setColspan(8);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        int NumCol = 1;
        PdfPTable table112 = new PdfPTable(NumCol);
        table112.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase(tit, DATO_FONT));
        cell.setColspan(1);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table112.addCell(cell);

        cell = new PdfPCell(new Phrase(aa, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table112.addCell(cell);

        cell = new PdfPCell(table112);
        cell.setColspan(1);
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

        int NumCol2 = 3;
        PdfPTable table113 = new PdfPTable(NumCol2);
        table113.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase(tit3, DATO_FONT));
        cell.setColspan(3);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table113.addCell(cell);

        cell = new PdfPCell(new Phrase(e, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table113.addCell(cell);

        cell = new PdfPCell(new Phrase(f, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table113.addCell(cell);

        cell = new PdfPCell(new Phrase(g, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table113.addCell(cell);

        cell = new PdfPCell(table113);
        cell.setColspan(3);
        table11.addCell(cell);

        cell = new PdfPCell(table11);
        cell.setColspan(8);
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
