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

public class VerCuaderno7_2014PDF extends AbstractPdfView {

    private static final Font TITULO_FONT = new Font(Font.HELVETICA, 12, Font.BOLD, Color.black);
    private static final Font CABEZA_COLUMNA_FONT = new Font(Font.HELVETICA, 7, Font.ITALIC, Color.black);
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
        Clientes dato = (Clientes) model.get("dato");
        int[] sumas = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int[] sumbs = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int NumColumns = 80;
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

            PdfPCell cell;
            PdfPTable table = new PdfPTable(NumColumns);
            int[] fwidths = {11, 22, 20, 70, 15, 8, 8, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 11};
            table.setWidths(fwidths);
            table.setWidthPercentage(100);

            cell = new PdfPCell(new Phrase("Cuaderno No. 4       CONSULTAS ODONTOLOGICAS", TITULO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(NumColumns);
            table.addCell(cell);
            // titulo no y fecha
            cell = new PdfPCell(new Phrase("No", CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Fecha", CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Historia Clinica", CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Apellidos y Nombre", CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            //cie10
            cell = new PdfPCell(new Phrase("Dagnostico", CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            //primera consulta

            addfila2(table, "Pieza Dentarea", "T", "P");
            addfila32(table, "Embarazada", "1r Consul", "C. Nueva", "C.Repet");
            addfila32(table, "Puerpera", "1r Consul", "C. Nueva", "C.Repet");
            addfila75(table, "Menores de 5 años");
            addfila74(table, "De 5 a 13 años");

            cell = new PdfPCell(new Phrase("por", CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setRotation(90);
            table.addCell(cell);

            // coloca el detalle de loS datos
            for (int i = pag * filas + 0; i < pag * filas + filas && i < lFopos.size(); i++) {

                Cuadernos datopac = (Cuadernos) lFopos.get(i);

                cell = new PdfPCell(new Phrase(Integer.toString(i + 1), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(format(datopac.getFechap(), "dd/MM/yy"), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(Integer.toString(datopac.getId_historial()), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((datopac.getNombres().length() > 22) ? datopac.getNombres().substring(0, 22) : datopac.getNombres(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(datopac.getCodigo(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);
////pieza dentarea Temporarea
                cell = new PdfPCell(new Phrase(("T".equals(datopac.getTipodent())) ? Integer.toString(datopac.getNumpieza()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[0] += ("T".equals(datopac.getTipodent())) ? 1 : 0;
////pieza dentarea Permanente 
                cell = new PdfPCell(new Phrase(("P".equals(datopac.getTipodent())) ? Integer.toString(datopac.getNumpieza()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[1] += ("P".equals(datopac.getTipodent())) ? 1 : 0;
////pieza dentarea embarazada Primera Consulta
                cell = new PdfPCell(new Phrase(("N".equals(datopac.getTipoconsulta()) && datopac.getPrimera() == 1 && datopac.getEmbarazo() == 1) ? "1" : "", DATO_FONT)); //?Integer.toString(datopac.getNumpieza()):
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[2] += ("N".equals(datopac.getTipoconsulta()) && datopac.getPrimera() == 1 && datopac.getEmbarazo() == 1) ? 1 : 0;
////pieza dentarea embarazada Nueva
                cell = new PdfPCell(new Phrase(("N".equals(datopac.getTipoconsulta()) && datopac.getEmbarazo() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[3] += ("N".equals(datopac.getTipoconsulta()) && datopac.getEmbarazo() == 1) ? 1 : 0;
////pieza dentarea embarazada Repetida           
                cell = new PdfPCell(new Phrase(("R".equals(datopac.getTipoconsulta()) && datopac.getEmbarazo() == 1) ? "1" : "", DATO_FONT)); //?Integer.toString(datopac.getNumpieza()):
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[4] += ("R".equals(datopac.getTipoconsulta()) && datopac.getEmbarazo() == 1) ? 1 : 0;
////pieza dentarea puerpera Primera Consulta
                cell = new PdfPCell(new Phrase(("N".equals(datopac.getTipoconsulta()) && datopac.getPrimera() == 1 && datopac.getEmbarazo() == 2) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[5] += ("N".equals(datopac.getTipoconsulta()) && datopac.getPrimera() == 1 && datopac.getEmbarazo() == 2) ? 1 : 0;
////pieza dentarea Puerpera Nueva           
                cell = new PdfPCell(new Phrase(("N".equals(datopac.getTipoconsulta()) && datopac.getEmbarazo() == 2) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[6] += ("N".equals(datopac.getTipoconsulta()) && datopac.getEmbarazo() == 2) ? 1 : 0;
////pieza dentarea Puerpera Repetida           
                cell = new PdfPCell(new Phrase(("R".equals(datopac.getTipoconsulta()) && datopac.getEmbarazo() == 2) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[7] += ("R".equals(datopac.getTipoconsulta()) && datopac.getEmbarazo() == 2) ? 1 : 0;

////////////////////////////////////////////////////////////           
////Menores de 5 años varones Primeras Consultas        
                cell = new PdfPCell(new Phrase((datopac.getEdad() < 5 && datopac.getId_cuaderno() == 2 && datopac.getPrimera() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[8] += (datopac.getEdad() < 5 && datopac.getId_cuaderno() == 2 && datopac.getPrimera() == 1) ? 1 : 0;
////Menores de 5 años mujeres Primeras Consultas           
                cell = new PdfPCell(new Phrase((datopac.getEdad() < 5 && datopac.getId_cuaderno() == 1 && datopac.getPrimera() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[9] += (datopac.getEdad() < 5 && datopac.getId_cuaderno() == 1 && datopac.getPrimera() == 1) ? 1 : 0;
////Menores de 5 años varones Nuevas          
                cell = new PdfPCell(new Phrase((datopac.getEdad() < 5 && datopac.getId_cuaderno() == 2 && "N".equals(datopac.getTipoconsulta())) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[10] += (datopac.getEdad() < 5 && datopac.getId_cuaderno() == 2 && "N".equals(datopac.getTipoconsulta())) ? 1 : 0;
////Menores de 5 años mujeres Nuevas          
                cell = new PdfPCell(new Phrase((datopac.getEdad() < 5 && datopac.getId_cuaderno() == 1 && "N".equals(datopac.getTipoconsulta())) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[11] += (datopac.getEdad() < 5 && datopac.getId_cuaderno() == 1 && "N".equals(datopac.getTipoconsulta())) ? 1 : 0;
////Menores de 5 años varones Repetidas           
                cell = new PdfPCell(new Phrase((datopac.getEdad() < 5 && datopac.getId_cuaderno() == 2 && "R".equals(datopac.getTipoconsulta())) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[12] += (datopac.getEdad() < 5 && datopac.getId_cuaderno() == 2 && "R".equals(datopac.getTipoconsulta())) ? 1 : 0;
////Menores de 5 años mujeres Repetidas           
                cell = new PdfPCell(new Phrase((datopac.getEdad() < 5 && datopac.getId_cuaderno() == 1 && "R".equals(datopac.getTipoconsulta())) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[13] += (datopac.getEdad() < 5 && datopac.getId_cuaderno() == 1 && "R".equals(datopac.getTipoconsulta())) ? 1 : 0;
///PROFILAXIS VARONES              
                cell = new PdfPCell(new Phrase((datopac.getEdad() < 5 && datopac.getId_cuaderno() == 2 && "B".equals(datopac.getTipo()) && datopac.getPreventiva() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[14] += (datopac.getEdad() < 5 && datopac.getId_cuaderno() == 2 && "B".equals(datopac.getTipo()) && datopac.getPreventiva() == 1) ? 1 : 0;
///PROFILAXIS MUEJRES            
                cell = new PdfPCell(new Phrase((datopac.getEdad() < 5 && datopac.getId_cuaderno() == 1 && "B".equals(datopac.getTipo()) && datopac.getPreventiva() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[15] += (datopac.getEdad() < 5 && datopac.getId_cuaderno() == 1 && "B".equals(datopac.getTipo()) && datopac.getPreventiva() == 1) ? 1 : 0;
///FLOURACION VARONES           
                cell = new PdfPCell(new Phrase((datopac.getEdad() < 5 && datopac.getId_cuaderno() == 2 && "A".equals(datopac.getTipo()) && datopac.getPreventiva() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[16] += (datopac.getEdad() < 5 && datopac.getId_cuaderno() == 2 && "A".equals(datopac.getTipo()) && datopac.getPreventiva() == 1) ? 1 : 0;
///FLOURACION MUJERES           
                cell = new PdfPCell(new Phrase((datopac.getEdad() < 5 && datopac.getId_cuaderno() == 1 && "A".equals(datopac.getTipo()) && datopac.getPreventiva() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[17] += (datopac.getEdad() < 5 && datopac.getId_cuaderno() == 1 && "A".equals(datopac.getTipo()) && datopac.getPreventiva() == 1) ? 1 : 0;
///SELLANTES VARONES           
                cell = new PdfPCell(new Phrase((datopac.getEdad() < 5 && datopac.getId_cuaderno() == 2 && "C".equals(datopac.getTipo()) && datopac.getPreventiva() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[18] += (datopac.getEdad() < 5 && datopac.getId_cuaderno() == 2 && "C".equals(datopac.getTipo()) && datopac.getPreventiva() == 1) ? 1 : 0;
///SELLANTES MUEJERES           
                cell = new PdfPCell(new Phrase((datopac.getEdad() < 5 && datopac.getId_cuaderno() == 1 && "C".equals(datopac.getTipo()) && datopac.getPreventiva() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[19] += (datopac.getEdad() < 5 && datopac.getId_cuaderno() == 1 && "C".equals(datopac.getTipo()) && datopac.getPreventiva() == 1) ? 1 : 0;
///CAROSTATICOS VARONES            
                cell = new PdfPCell(new Phrase((datopac.getEdad() < 5 && datopac.getId_cuaderno() == 2 && "D".equals(datopac.getTipo()) && datopac.getPreventiva() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[20] += (datopac.getEdad() < 5 && datopac.getId_cuaderno() == 2 && "D".equals(datopac.getTipo()) && datopac.getPreventiva() == 1) ? 1 : 0;
///CAROSTATICOS MUJERES           
                cell = new PdfPCell(new Phrase((datopac.getEdad() < 5 && datopac.getId_cuaderno() == 1 && "D".equals(datopac.getTipo()) && datopac.getPreventiva() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[21] += (datopac.getEdad() < 5 && datopac.getId_cuaderno() == 1 && "D".equals(datopac.getTipo()) && datopac.getPreventiva() == 1) ? 1 : 0;
///AMALGAMAS VARONES           
                cell = new PdfPCell(new Phrase((datopac.getEdad() < 5 && datopac.getId_cuaderno() == 2 && "A".equals(datopac.getTipo()) && datopac.getRestauracion() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[22] += (datopac.getEdad() < 5 && datopac.getId_cuaderno() == 2 && "A".equals(datopac.getTipo()) && datopac.getRestauracion() == 1) ? 1 : 0;
///AMALGAMAS MUJERES           
                cell = new PdfPCell(new Phrase((datopac.getEdad() < 5 && datopac.getId_cuaderno() == 1 && "A".equals(datopac.getTipo()) && datopac.getRestauracion() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[23] += (datopac.getEdad() < 5 && datopac.getId_cuaderno() == 1 && "A".equals(datopac.getTipo()) && datopac.getRestauracion() == 1) ? 1 : 0;
///RESINAS VARONES          
                cell = new PdfPCell(new Phrase((datopac.getEdad() < 5 && datopac.getId_cuaderno() == 2 && ("B".equals(datopac.getTipo()) || "C".equals(datopac.getTipo())) && datopac.getRestauracion() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[24] += (datopac.getEdad() < 5 && datopac.getId_cuaderno() == 2 && ("B".equals(datopac.getTipo()) || "C".equals(datopac.getTipo())) && datopac.getRestauracion() == 1) ? 1 : 0;
///RESINAS MUJERES           
                cell = new PdfPCell(new Phrase((datopac.getEdad() < 5 && datopac.getId_cuaderno() == 1 && ("B".equals(datopac.getTipo()) || "C".equals(datopac.getTipo())) && datopac.getRestauracion() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[25] += (datopac.getEdad() < 5 && datopac.getId_cuaderno() == 1 && ("B".equals(datopac.getTipo()) || "C".equals(datopac.getTipo())) && datopac.getRestauracion() == 1) ? 1 : 0;
///IONOMETRO VARONES           
                cell = new PdfPCell(new Phrase((datopac.getEdad() < 5 && datopac.getId_cuaderno() == 2 && "D".equals(datopac.getTipo()) && datopac.getRestauracion() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[26] += (datopac.getEdad() < 5 && datopac.getId_cuaderno() == 2 && "D".equals(datopac.getTipo()) && datopac.getRestauracion() == 1) ? 1 : 0;
///IONOMETRO MUJERES            
                cell = new PdfPCell(new Phrase((datopac.getEdad() < 5 && datopac.getId_cuaderno() == 1 && "D".equals(datopac.getTipo()) && datopac.getRestauracion() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[27] += (datopac.getEdad() < 5 && datopac.getId_cuaderno() == 1 && "D".equals(datopac.getTipo()) && datopac.getRestauracion() == 1) ? 1 : 0;
///PRAT VARONES          
                cell = new PdfPCell(new Phrase((datopac.getEdad() < 5 && datopac.getId_cuaderno() == 2 && "E".equals(datopac.getTipo()) && datopac.getRestauracion() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[28] += (datopac.getEdad() < 5 && datopac.getId_cuaderno() == 2 && "E".equals(datopac.getTipo()) && datopac.getRestauracion() == 1) ? 1 : 0;
///PRAT MUJERES       
                cell = new PdfPCell(new Phrase((datopac.getEdad() < 5 && datopac.getId_cuaderno() == 1 && "E".equals(datopac.getTipo()) && datopac.getRestauracion() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[29] += (datopac.getEdad() < 5 && datopac.getId_cuaderno() == 1 && "E".equals(datopac.getTipo()) && datopac.getRestauracion() == 1) ? 1 : 0;
///ENDODONCIA PULPOTOMIA VARONES          
                cell = new PdfPCell(new Phrase((datopac.getEdad() < 5 && datopac.getId_cuaderno() == 2 && "A".equals(datopac.getTipo()) && datopac.getEndodoncia() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[30] += (datopac.getEdad() < 5 && datopac.getId_cuaderno() == 2 && "A".equals(datopac.getTipo()) && datopac.getEndodoncia() == 1) ? 1 : 0;
///ENDODONCIA PULPOTOMIA MUJERES 
                cell = new PdfPCell(new Phrase((datopac.getEdad() < 5 && datopac.getId_cuaderno() == 1 && "A".equals(datopac.getTipo()) && datopac.getEndodoncia() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[31] += (datopac.getEdad() < 5 && datopac.getId_cuaderno() == 1 && "A".equals(datopac.getTipo()) && datopac.getEndodoncia() == 1) ? 1 : 0;
///ENDODONCIA TRATAMIENTO CONDUCTO VARONES          
                cell = new PdfPCell(new Phrase((datopac.getEdad() < 5 && datopac.getId_cuaderno() == 2 && ("B".equals(datopac.getTipo())) && datopac.getEndodoncia() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[32] += (datopac.getEdad() < 5 && datopac.getId_cuaderno() == 2 && ("B".equals(datopac.getTipo())) && datopac.getEndodoncia() == 1) ? 1 : 0;
///ENDODONCIA TRATAMIENTO CONDUCTO MUJERES         
                cell = new PdfPCell(new Phrase((datopac.getEdad() < 5 && datopac.getId_cuaderno() == 1 && ("B".equals(datopac.getTipo())) && datopac.getEndodoncia() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[33] += (datopac.getEdad() < 5 && datopac.getId_cuaderno() == 1 && ("B".equals(datopac.getTipo())) && datopac.getEndodoncia() == 1) ? 1 : 0;

///EXODONCIA VARONES          
                cell = new PdfPCell(new Phrase((datopac.getEdad() < 5 && datopac.getId_cuaderno() == 2 && "A".equals(datopac.getTipo()) && datopac.getExodoncia() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[34] += (datopac.getEdad() < 5 && datopac.getId_cuaderno() == 2 && "A".equals(datopac.getTipo()) && datopac.getExodoncia() == 1) ? 1 : 0;
///EXODONCIA MUJERES         
                cell = new PdfPCell(new Phrase((datopac.getEdad() < 5 && datopac.getId_cuaderno() == 1 && "A".equals(datopac.getTipo()) && datopac.getExodoncia() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[35] += (datopac.getEdad() < 5 && datopac.getId_cuaderno() == 1 && "A".equals(datopac.getTipo()) && datopac.getExodoncia() == 1) ? 1 : 0;
///ABSECO PERIAPICAL AGUDO VARONES          
                cell = new PdfPCell(new Phrase((datopac.getEdad() < 5 && datopac.getId_cuaderno() == 2 && "C".equals(datopac.getTipo()) && datopac.getExodoncia() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[36] += (datopac.getEdad() < 5 && datopac.getId_cuaderno() == 2 && "C".equals(datopac.getTipo()) && datopac.getExodoncia() == 1) ? 1 : 0;
///ABSECO PERIAPICAL AGUDO MUJERES          
                cell = new PdfPCell(new Phrase((datopac.getEdad() < 5 && datopac.getId_cuaderno() == 1 && "C".equals(datopac.getTipo()) && datopac.getExodoncia() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[37] += (datopac.getEdad() < 5 && datopac.getId_cuaderno() == 1 && "C".equals(datopac.getTipo()) && datopac.getExodoncia() == 1) ? 1 : 0;

////////////////////////////////////////////////////////////   DE 5 A 13 añoS        
////Menores de 5 años varones Primeras Consultas        
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 4 && datopac.getEdad() < 14 && datopac.getId_cuaderno() == 2 && datopac.getPrimera() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[38] += (datopac.getEdad() > 4 && datopac.getEdad() < 14 && datopac.getId_cuaderno() == 2 && datopac.getPrimera() == 1) ? 1 : 0;
////Menores de 5 años mujeres Primeras Consultas           
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 4 && datopac.getEdad() < 14 && datopac.getId_cuaderno() == 1 && datopac.getPrimera() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[39] += (datopac.getEdad() > 4 && datopac.getEdad() < 14 && datopac.getId_cuaderno() == 1 && datopac.getPrimera() == 1) ? 1 : 0;
////Menores de 5 años varones Nuevas          
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 4 && datopac.getEdad() < 14 && datopac.getId_cuaderno() == 2 && "N".equals(datopac.getTipoconsulta())) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[40] += (datopac.getEdad() > 4 && datopac.getEdad() < 14 && datopac.getId_cuaderno() == 2 && "N".equals(datopac.getTipoconsulta())) ? 1 : 0;
////Menores de 5 años mujeres Nuevas          
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 4 && datopac.getEdad() < 14 && datopac.getId_cuaderno() == 1 && "N".equals(datopac.getTipoconsulta())) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[41] += (datopac.getEdad() > 4 && datopac.getEdad() < 14 && datopac.getId_cuaderno() == 1 && "N".equals(datopac.getTipoconsulta())) ? 1 : 0;
////Menores de 5 años varones Repetidas           
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 4 && datopac.getEdad() < 14 && datopac.getId_cuaderno() == 2 && "R".equals(datopac.getTipoconsulta())) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[42] += (datopac.getEdad() > 4 && datopac.getEdad() < 14 && datopac.getId_cuaderno() == 2 && "R".equals(datopac.getTipoconsulta())) ? 1 : 0;
////Menores de 5 años mujeres Repetidas           
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 4 && datopac.getEdad() < 14 && datopac.getId_cuaderno() == 1 && "R".equals(datopac.getTipoconsulta())) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[43] += (datopac.getEdad() > 4 && datopac.getEdad() < 14 && datopac.getId_cuaderno() == 1 && "R".equals(datopac.getTipoconsulta())) ? 1 : 0;
///PROFILAXIS VARONES              
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 4 && datopac.getEdad() < 14 && datopac.getId_cuaderno() == 2 && "B".equals(datopac.getTipo()) && datopac.getPreventiva() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[44] += (datopac.getEdad() > 4 && datopac.getEdad() < 14 && datopac.getId_cuaderno() == 2 && "B".equals(datopac.getTipo()) && datopac.getPreventiva() == 1) ? 1 : 0;
///PROFILAXIS MUEJRES            
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 4 && datopac.getEdad() < 14 && datopac.getId_cuaderno() == 1 && "B".equals(datopac.getTipo()) && datopac.getPreventiva() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[45] += (datopac.getEdad() > 4 && datopac.getEdad() < 14 && datopac.getId_cuaderno() == 1 && "B".equals(datopac.getTipo()) && datopac.getPreventiva() == 1) ? 1 : 0;
///FLOURACION VARONES           
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 4 && datopac.getEdad() < 14 && datopac.getId_cuaderno() == 2 && "A".equals(datopac.getTipo()) && datopac.getPreventiva() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[46] += (datopac.getEdad() > 4 && datopac.getEdad() < 14 && datopac.getId_cuaderno() == 2 && "A".equals(datopac.getTipo()) && datopac.getPreventiva() == 1) ? 1 : 0;
///FLOURACION MUJERES           
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 4 && datopac.getEdad() < 14 && datopac.getId_cuaderno() == 1 && "A".equals(datopac.getTipo()) && datopac.getPreventiva() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[47] += (datopac.getEdad() > 4 && datopac.getEdad() < 14 && datopac.getId_cuaderno() == 1 && "A".equals(datopac.getTipo()) && datopac.getPreventiva() == 1) ? 1 : 0;
///SELLANTES VARONES           
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 4 && datopac.getEdad() < 14 && datopac.getId_cuaderno() == 2 && "C".equals(datopac.getTipo()) && datopac.getPreventiva() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[48] += (datopac.getEdad() > 4 && datopac.getEdad() < 14 && datopac.getId_cuaderno() == 2 && "C".equals(datopac.getTipo()) && datopac.getPreventiva() == 1) ? 1 : 0;
///SELLANTES MUEJERES           
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 4 && datopac.getEdad() < 14 && datopac.getId_cuaderno() == 1 && "C".equals(datopac.getTipo()) && datopac.getPreventiva() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[49] += (datopac.getEdad() > 4 && datopac.getEdad() < 14 && datopac.getId_cuaderno() == 1 && "C".equals(datopac.getTipo()) && datopac.getPreventiva() == 1) ? 1 : 0;

///AMALGAMAS VARONES           
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 4 && datopac.getEdad() < 14 && datopac.getId_cuaderno() == 2 && "A".equals(datopac.getTipo()) && datopac.getRestauracion() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[50] += (datopac.getEdad() > 4 && datopac.getEdad() < 14 && datopac.getId_cuaderno() == 2 && "A".equals(datopac.getTipo()) && datopac.getRestauracion() == 1) ? 1 : 0;
///AMALGAMAS MUJERES           
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 4 && datopac.getEdad() < 14 && datopac.getId_cuaderno() == 1 && "A".equals(datopac.getTipo()) && datopac.getRestauracion() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[51] += (datopac.getEdad() > 4 && datopac.getEdad() < 14 && datopac.getId_cuaderno() == 1 && "A".equals(datopac.getTipo()) && datopac.getRestauracion() == 1) ? 1 : 0;
///RESINAS VARONES          
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 4 && datopac.getEdad() < 14 && datopac.getId_cuaderno() == 2 && ("B".equals(datopac.getTipo()) || "C".equals(datopac.getTipo())) && datopac.getRestauracion() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[52] += (datopac.getEdad() > 4 && datopac.getEdad() < 14 && datopac.getId_cuaderno() == 2 && ("B".equals(datopac.getTipo()) || "C".equals(datopac.getTipo())) && datopac.getRestauracion() == 1) ? 1 : 0;
///RESINAS MUJERES           
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 4 && datopac.getEdad() < 14 && datopac.getId_cuaderno() == 1 && ("B".equals(datopac.getTipo()) || "C".equals(datopac.getTipo())) && datopac.getRestauracion() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[53] += (datopac.getEdad() > 4 && datopac.getEdad() < 14 && datopac.getId_cuaderno() == 1 && ("B".equals(datopac.getTipo()) || "C".equals(datopac.getTipo())) && datopac.getRestauracion() == 1) ? 1 : 0;
///IONOMETRO VARONES           
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 4 && datopac.getEdad() < 14 && datopac.getId_cuaderno() == 2 && "D".equals(datopac.getTipo()) && datopac.getRestauracion() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[54] += (datopac.getEdad() > 4 && datopac.getEdad() < 14 && datopac.getId_cuaderno() == 2 && "D".equals(datopac.getTipo()) && datopac.getRestauracion() == 1) ? 1 : 0;
///IONOMETRO MUJERES            
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 4 && datopac.getEdad() < 14 && datopac.getId_cuaderno() == 1 && "D".equals(datopac.getTipo()) && datopac.getRestauracion() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[55] += (datopac.getEdad() > 4 && datopac.getEdad() < 14 && datopac.getId_cuaderno() == 1 && "D".equals(datopac.getTipo()) && datopac.getRestauracion() == 1) ? 1 : 0;
///PRAT VARONES          
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 4 && datopac.getEdad() < 14 && datopac.getId_cuaderno() == 2 && "E".equals(datopac.getTipo()) && datopac.getRestauracion() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[56] += (datopac.getEdad() > 4 && datopac.getEdad() < 14 && datopac.getId_cuaderno() == 2 && "E".equals(datopac.getTipo()) && datopac.getRestauracion() == 1) ? 1 : 0;
///PRAT MUJERES       
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 4 && datopac.getEdad() < 14 && datopac.getId_cuaderno() == 1 && "E".equals(datopac.getTipo()) && datopac.getRestauracion() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[57] += (datopac.getEdad() > 4 && datopac.getEdad() < 14 && datopac.getId_cuaderno() == 1 && "E".equals(datopac.getTipo()) && datopac.getRestauracion() == 1) ? 1 : 0;
///ENDODONCIA PULPOTOMIA VARONES          
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 4 && datopac.getEdad() < 14 && datopac.getId_cuaderno() == 2 && "A".equals(datopac.getTipo()) && datopac.getEndodoncia() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[58] += (datopac.getEdad() > 4 && datopac.getEdad() < 14 && datopac.getId_cuaderno() == 2 && "A".equals(datopac.getTipo()) && datopac.getEndodoncia() == 1) ? 1 : 0;
///ENDODONCIA PULPOTOMIA MUJERES 
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 4 && datopac.getEdad() < 14 && datopac.getId_cuaderno() == 1 && "A".equals(datopac.getTipo()) && datopac.getEndodoncia() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[59] += (datopac.getEdad() > 4 && datopac.getEdad() < 14 && datopac.getId_cuaderno() == 1 && "A".equals(datopac.getTipo()) && datopac.getEndodoncia() == 1) ? 1 : 0;
///ENDODONCIA TRATAMIENTO CONDUCTO VARONES          
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 4 && datopac.getEdad() < 14 && datopac.getId_cuaderno() == 2 && ("B".equals(datopac.getTipo())) && datopac.getEndodoncia() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[60] += (datopac.getEdad() > 4 && datopac.getEdad() < 14 && datopac.getId_cuaderno() == 2 && ("B".equals(datopac.getTipo())) && datopac.getEndodoncia() == 1) ? 1 : 0;
///ENDODONCIA TRATAMIENTO CONDUCTO MUJERES         
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 4 && datopac.getEdad() < 14 && datopac.getId_cuaderno() == 1 && ("B".equals(datopac.getTipo())) && datopac.getEndodoncia() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[61] += (datopac.getEdad() > 4 && datopac.getEdad() < 14 && datopac.getId_cuaderno() == 1 && ("B".equals(datopac.getTipo())) && datopac.getEndodoncia() == 1) ? 1 : 0;

///PERIODONCIA TARTECTOMIA VARONES         
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 4 && datopac.getEdad() < 14 && datopac.getId_cuaderno() == 2 && ("A".equals(datopac.getTipo()) || "D".equals(datopac.getTipo()) || "E".equals(datopac.getTipo())) && datopac.getPeriodoncia() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[62] += (datopac.getEdad() > 4 && datopac.getEdad() < 14 && datopac.getId_cuaderno() == 2 && ("A".equals(datopac.getTipo()) || "D".equals(datopac.getTipo()) || "E".equals(datopac.getTipo())) && datopac.getPeriodoncia() == 1) ? 1 : 0;
///PERIODONCIA TARTECTOMIA MUJERES         
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 4 && datopac.getEdad() < 14 && datopac.getId_cuaderno() == 1 && ("A".equals(datopac.getTipo()) || "D".equals(datopac.getTipo()) || "E".equals(datopac.getTipo())) && datopac.getPeriodoncia() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[63] += (datopac.getEdad() > 4 && datopac.getEdad() < 14 && datopac.getId_cuaderno() == 1 && ("A".equals(datopac.getTipo()) || "D".equals(datopac.getTipo()) || "E".equals(datopac.getTipo())) && datopac.getPeriodoncia() == 1) ? 1 : 0;
///PERIODONCIA Gingivectomia VARONES         
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 4 && datopac.getEdad() < 14 && datopac.getId_cuaderno() == 2 && ("B".equals(datopac.getTipo()) || "F".equals(datopac.getTipo())) && datopac.getPeriodoncia() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[64] += (datopac.getEdad() > 4 && datopac.getEdad() < 14 && datopac.getId_cuaderno() == 2 && ("B".equals(datopac.getTipo()) || "F".equals(datopac.getTipo())) && datopac.getPeriodoncia() == 1) ? 1 : 0;
///PERIODONCIA Gingivectomia MUJERES         
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 4 && datopac.getEdad() < 14 && datopac.getId_cuaderno() == 1 && ("B".equals(datopac.getTipo()) || "F".equals(datopac.getTipo())) && datopac.getPeriodoncia() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[65] += (datopac.getEdad() > 4 && datopac.getEdad() < 14 && datopac.getId_cuaderno() == 1 && ("B".equals(datopac.getTipo()) || "F".equals(datopac.getTipo())) && datopac.getPeriodoncia() == 1) ? 1 : 0;
///PERIODONCIA Trat. No quirurgico VARONES         
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 4 && datopac.getEdad() < 14 && datopac.getId_cuaderno() == 2 && "C".equals(datopac.getTipo()) && datopac.getPeriodoncia() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[66] += (datopac.getEdad() > 4 && datopac.getEdad() < 14 && datopac.getId_cuaderno() == 2 && "C".equals(datopac.getTipo()) && datopac.getPeriodoncia() == 1) ? 1 : 0;
///PERIODONCIA Trat. No quirurgico MUJERES         
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 4 && datopac.getEdad() < 14 && datopac.getId_cuaderno() == 1 && "C".equals(datopac.getTipo()) && datopac.getPeriodoncia() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[67] += (datopac.getEdad() > 4 && datopac.getEdad() < 14 && datopac.getId_cuaderno() == 1 && "C".equals(datopac.getTipo()) && datopac.getPeriodoncia() == 1) ? 1 : 0;

///EXODONCIA VARONES          
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 4 && datopac.getEdad() < 14 && datopac.getId_cuaderno() == 2 && "A".equals(datopac.getTipo()) && datopac.getExodoncia() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[68] += (datopac.getEdad() > 4 && datopac.getEdad() < 14 && datopac.getId_cuaderno() == 2 && "A".equals(datopac.getTipo()) && datopac.getExodoncia() == 1) ? 1 : 0;
///EXODONCIA MUJERES         
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 4 && datopac.getEdad() < 14 && datopac.getId_cuaderno() == 1 && "A".equals(datopac.getTipo()) && datopac.getExodoncia() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[69] += (datopac.getEdad() > 4 && datopac.getEdad() < 14 && datopac.getId_cuaderno() == 1 && "A".equals(datopac.getTipo()) && datopac.getExodoncia() == 1) ? 1 : 0;
///TRATAMIENTO ALVEOLITIS VARONES          
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 4 && datopac.getEdad() < 14 && datopac.getId_cuaderno() == 2 && "B".equals(datopac.getTipo()) && datopac.getExodoncia() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[70] += (datopac.getEdad() > 4 && datopac.getEdad() < 14 && datopac.getId_cuaderno() == 2 && "B".equals(datopac.getTipo()) && datopac.getExodoncia() == 1) ? 1 : 0;
///TRATAMIENTO ALVEOLITIS MUJERES          
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 4 && datopac.getEdad() < 14 && datopac.getId_cuaderno() == 1 && "B".equals(datopac.getTipo()) && datopac.getExodoncia() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[71] += (datopac.getEdad() > 4 && datopac.getEdad() < 14 && datopac.getId_cuaderno() == 1 && "B".equals(datopac.getTipo()) && datopac.getExodoncia() == 1) ? 1 : 0;
///TRAT ABCESO PERIAPICAL VARONES          
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 4 && datopac.getEdad() < 14 && datopac.getId_cuaderno() == 2 && "C".equals(datopac.getTipo()) && datopac.getExodoncia() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[72] += (datopac.getEdad() > 4 && datopac.getEdad() < 14 && datopac.getId_cuaderno() == 2 && "C".equals(datopac.getTipo()) && datopac.getExodoncia() == 1) ? 1 : 0;
///TRAT ABCESO PERIAPICAL MUJERES          
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 4 && datopac.getEdad() < 14 && datopac.getId_cuaderno() == 1 && "C".equals(datopac.getTipo()) && datopac.getExodoncia() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[73] += (datopac.getEdad() > 4 && datopac.getEdad() < 14 && datopac.getId_cuaderno() == 1 && "C".equals(datopac.getTipo()) && datopac.getExodoncia() == 1) ? 1 : 0;

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
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setColspan(5);
            table.addCell(cell);

            for (int j = 0; j < 76; j++) {
                cell = new PdfPCell(new Phrase(Integer.toString(sumas[j]), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);
            }

            for (int k = 0; k < 6; k++) { //coloca filas vacias antes de colocar totales
                cell = new PdfPCell(new Phrase("", CABEZA_COLUMNA_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
            }

            document.add(table);
            document.newPage();

            ///Empieza la segunda hoja  
            int NumColumns2 = 104;
            table = new PdfPTable(NumColumns2);
            int[] fwdidths = {13, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5};
            table.setWidths(fwdidths);
            table.setWidthPercentage(100);

            cell = new PdfPCell(new Phrase("CUADERNO No 4.  CONSULTAS ODONTOLOGICAS", TITULO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(104);
            table.addCell(cell);

            // coloca la cabecera de titulos
            cell = new PdfPCell(new Phrase("No", CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            addfila76(table, "14 a 19 años");
            addfila77(table, "20 a 59 años");
            addfila77(table, "60 años y mas");

            cell = new PdfPCell(new Phrase("Otras Acc", CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            addfila2(table, "RX", "D", "F");  ///// para los rayos X

            cell = new PdfPCell(new Phrase("Referido", CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Contraref", CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            for (int i = pag * filas + 0; i < pag * filas + filas && i < lFopos.size(); i++) {

                Cuadernos datopac = (Cuadernos) lFopos.get(i);

                cell = new PdfPCell(new Phrase(Integer.toString(i + 1), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                ////////////////////////////////////////////////////////////   DE 14 A 19 añoS        
////Menores de 5 años varones Primeras Consultas        
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 13 && datopac.getEdad() < 20 && datopac.getId_cuaderno() == 2 && datopac.getPrimera() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[0] += (datopac.getEdad() > 13 && datopac.getEdad() < 20 && datopac.getId_cuaderno() == 2 && datopac.getPrimera() == 1) ? 1 : 0;
////Menores de 5 años mujeres Primeras Consultas           
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 13 && datopac.getEdad() < 20 && datopac.getId_cuaderno() == 1 && datopac.getPrimera() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[1] += (datopac.getEdad() > 13 && datopac.getEdad() < 20 && datopac.getId_cuaderno() == 1 && datopac.getPrimera() == 1) ? 1 : 0;
////Menores de 5 años varones Nuevas          
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 13 && datopac.getEdad() < 20 && datopac.getId_cuaderno() == 2 && "N".equals(datopac.getTipoconsulta())) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[2] += (datopac.getEdad() > 13 && datopac.getEdad() < 20 && datopac.getId_cuaderno() == 2 && "N".equals(datopac.getTipoconsulta())) ? 1 : 0;
////Menores de 5 años mujeres Nuevas          
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 13 && datopac.getEdad() < 20 && datopac.getId_cuaderno() == 1 && "N".equals(datopac.getTipoconsulta())) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[3] += (datopac.getEdad() > 13 && datopac.getEdad() < 20 && datopac.getId_cuaderno() == 1 && "N".equals(datopac.getTipoconsulta())) ? 1 : 0;
////Menores de 5 años varones Repetidas           
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 13 && datopac.getEdad() < 20 && datopac.getId_cuaderno() == 2 && "R".equals(datopac.getTipoconsulta())) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[4] += (datopac.getEdad() > 13 && datopac.getEdad() < 20 && datopac.getId_cuaderno() == 2 && "R".equals(datopac.getTipoconsulta())) ? 1 : 0;
////Menores de 5 años mujeres Repetidas           
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 13 && datopac.getEdad() < 20 && datopac.getId_cuaderno() == 1 && "R".equals(datopac.getTipoconsulta())) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[5] += (datopac.getEdad() > 13 && datopac.getEdad() < 20 && datopac.getId_cuaderno() == 1 && "R".equals(datopac.getTipoconsulta())) ? 1 : 0;
///PROFILAXIS VARONES              
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 13 && datopac.getEdad() < 20 && datopac.getId_cuaderno() == 2 && "B".equals(datopac.getTipo()) && datopac.getPreventiva() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[6] += (datopac.getEdad() > 13 && datopac.getEdad() < 20 && datopac.getId_cuaderno() == 2 && "B".equals(datopac.getTipo()) && datopac.getPreventiva() == 1) ? 1 : 0;
///PROFILAXIS MUEJRES            
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 13 && datopac.getEdad() < 20 && datopac.getId_cuaderno() == 1 && "B".equals(datopac.getTipo()) && datopac.getPreventiva() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[7] += (datopac.getEdad() > 13 && datopac.getEdad() < 20 && datopac.getId_cuaderno() == 1 && "B".equals(datopac.getTipo()) && datopac.getPreventiva() == 1) ? 1 : 0;
///FLOURACION VARONES           
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 13 && datopac.getEdad() < 20 && datopac.getId_cuaderno() == 2 && "A".equals(datopac.getTipo()) && datopac.getPreventiva() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[8] += (datopac.getEdad() > 13 && datopac.getEdad() < 20 && datopac.getId_cuaderno() == 2 && "A".equals(datopac.getTipo()) && datopac.getPreventiva() == 1) ? 1 : 0;
///FLOURACION MUJERES           
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 13 && datopac.getEdad() < 20 && datopac.getId_cuaderno() == 1 && "A".equals(datopac.getTipo()) && datopac.getPreventiva() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[9] += (datopac.getEdad() > 13 && datopac.getEdad() < 20 && datopac.getId_cuaderno() == 1 && "A".equals(datopac.getTipo()) && datopac.getPreventiva() == 1) ? 1 : 0;
///SELLANTES VARONES           
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 13 && datopac.getEdad() < 20 && datopac.getId_cuaderno() == 2 && "C".equals(datopac.getTipo()) && datopac.getPreventiva() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[10] += (datopac.getEdad() > 13 && datopac.getEdad() < 20 && datopac.getId_cuaderno() == 2 && "C".equals(datopac.getTipo()) && datopac.getPreventiva() == 1) ? 1 : 0;
///SELLANTES MUEJERES           
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 13 && datopac.getEdad() < 20 && datopac.getId_cuaderno() == 1 && "C".equals(datopac.getTipo()) && datopac.getPreventiva() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[11] += (datopac.getEdad() > 13 && datopac.getEdad() < 20 && datopac.getId_cuaderno() == 1 && "C".equals(datopac.getTipo()) && datopac.getPreventiva() == 1) ? 1 : 0;
///CAROSTATICOS VARONES            
                //cell = new PdfPCell(new Phrase((datopac.getEdad()>13 && datopac.getEdad()<20 && datopac.getId_cuaderno()==2 && "D".equals(datopac.getTipo()) && datopac.getPreventiva()==1)?"1":"" , DATO_FONT));
                //cell.setHorizontalAlignment(Element.ALIGN_CENTER);  table.addCell(cell);
                //sumbs[12]+=(datopac.getEdad()>13 && datopac.getEdad()<20 && datopac.getId_cuaderno()==2 && "D".equals(datopac.getTipo()) && datopac.getPreventiva()==1)?1:0;  
///CAROSTATICOS MUJERES           
                //cell = new PdfPCell(new Phrase((datopac.getEdad()>13 && datopac.getEdad()<20 && datopac.getId_cuaderno()==1 && "D".equals(datopac.getTipo()) && datopac.getPreventiva()==1)?"1":"" , DATO_FONT));
                //cell.setHorizontalAlignment(Element.ALIGN_CENTER);  table.addCell(cell);
                //sumbs[13]+=(datopac.getEdad()>13 && datopac.getEdad()<20 && datopac.getId_cuaderno()==1 && "D".equals(datopac.getTipo()) && datopac.getPreventiva()==1)?1:0;  
///AMALGAMAS VARONES           
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 13 && datopac.getEdad() < 20 && datopac.getId_cuaderno() == 2 && "A".equals(datopac.getTipo()) && datopac.getRestauracion() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[12] += (datopac.getEdad() > 13 && datopac.getEdad() < 20 && datopac.getId_cuaderno() == 2 && "A".equals(datopac.getTipo()) && datopac.getRestauracion() == 1) ? 1 : 0;
///AMALGAMAS MUJERES           
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 13 && datopac.getEdad() < 20 && datopac.getId_cuaderno() == 1 && "A".equals(datopac.getTipo()) && datopac.getRestauracion() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[13] += (datopac.getEdad() > 13 && datopac.getEdad() < 20 && datopac.getId_cuaderno() == 1 && "A".equals(datopac.getTipo()) && datopac.getRestauracion() == 1) ? 1 : 0;
///RESINAS VARONES          
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 13 && datopac.getEdad() < 20 && datopac.getId_cuaderno() == 2 && ("B".equals(datopac.getTipo()) || "C".equals(datopac.getTipo())) && datopac.getRestauracion() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[14] += (datopac.getEdad() > 13 && datopac.getEdad() < 20 && datopac.getId_cuaderno() == 2 && ("B".equals(datopac.getTipo()) || "C".equals(datopac.getTipo())) && datopac.getRestauracion() == 1) ? 1 : 0;
///RESINAS MUJERES           
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 13 && datopac.getEdad() < 20 && datopac.getId_cuaderno() == 1 && ("B".equals(datopac.getTipo()) || "C".equals(datopac.getTipo())) && datopac.getRestauracion() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[15] += (datopac.getEdad() > 13 && datopac.getEdad() < 20 && datopac.getId_cuaderno() == 1 && ("B".equals(datopac.getTipo()) || "C".equals(datopac.getTipo())) && datopac.getRestauracion() == 1) ? 1 : 0;
///IONOMETRO VARONES           
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 13 && datopac.getEdad() < 20 && datopac.getId_cuaderno() == 2 && "D".equals(datopac.getTipo()) && datopac.getRestauracion() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[16] += (datopac.getEdad() > 13 && datopac.getEdad() < 20 && datopac.getId_cuaderno() == 2 && "D".equals(datopac.getTipo()) && datopac.getRestauracion() == 1) ? 1 : 0;
///IONOMETRO MUJERES            
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 13 && datopac.getEdad() < 20 && datopac.getId_cuaderno() == 1 && "D".equals(datopac.getTipo()) && datopac.getRestauracion() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[17] += (datopac.getEdad() > 13 && datopac.getEdad() < 20 && datopac.getId_cuaderno() == 1 && "D".equals(datopac.getTipo()) && datopac.getRestauracion() == 1) ? 1 : 0;
///PRAT VARONES          
                //cell = new PdfPCell(new Phrase((datopac.getEdad()>13 && datopac.getEdad()<20 && datopac.getId_cuaderno()==2 && "E".equals(datopac.getTipo()) && datopac.getRestauracion()==1)?"1":"" , DATO_FONT));
                //cell.setHorizontalAlignment(Element.ALIGN_CENTER);  table.addCell(cell);
                //sumbs[20]+=(datopac.getEdad()>13 && datopac.getEdad()<20 && datopac.getId_cuaderno()==2 && "E".equals(datopac.getTipo()) && datopac.getRestauracion()==1)?1:0;  
///PRAT MUJERES       
                //cell = new PdfPCell(new Phrase((datopac.getEdad()>4 && datopac.getEdad()<14 && datopac.getId_cuaderno()==1 && "E".equals(datopac.getTipo()) && datopac.getRestauracion()==1)?"1":"" , DATO_FONT));
                //cell.setHorizontalAlignment(Element.ALIGN_CENTER);  table.addCell(cell);
                //sumbs[21]+=(datopac.getEdad()>4 && datopac.getEdad()<14 && datopac.getId_cuaderno()==1 && "E".equals(datopac.getTipo()) && datopac.getRestauracion()==1)?1:0;  
///ENDODONCIA PULPOTOMIA VARONES          
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 13 && datopac.getEdad() < 20 && datopac.getId_cuaderno() == 2 && "A".equals(datopac.getTipo()) && datopac.getEndodoncia() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[18] += (datopac.getEdad() > 13 && datopac.getEdad() < 20 && datopac.getId_cuaderno() == 2 && "A".equals(datopac.getTipo()) && datopac.getEndodoncia() == 1) ? 1 : 0;
///ENDODONCIA PULPOTOMIA MUJERES 
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 13 && datopac.getEdad() < 20 && datopac.getId_cuaderno() == 1 && "A".equals(datopac.getTipo()) && datopac.getEndodoncia() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[19] += (datopac.getEdad() > 13 && datopac.getEdad() < 20 && datopac.getId_cuaderno() == 1 && "A".equals(datopac.getTipo()) && datopac.getEndodoncia() == 1) ? 1 : 0;
///ENDODONCIA TRATAMIENTO CONDUCTO VARONES          
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 13 && datopac.getEdad() < 20 && datopac.getId_cuaderno() == 2 && ("B".equals(datopac.getTipo())) && datopac.getEndodoncia() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[20] += (datopac.getEdad() > 13 && datopac.getEdad() < 20 && datopac.getId_cuaderno() == 2 && ("B".equals(datopac.getTipo())) && datopac.getEndodoncia() == 1) ? 1 : 0;
///ENDODONCIA TRATMIENTO CONDUCTO MUJERES         
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 13 && datopac.getEdad() < 20 && datopac.getId_cuaderno() == 1 && ("B".equals(datopac.getTipo())) && datopac.getEndodoncia() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[21] += (datopac.getEdad() > 13 && datopac.getEdad() < 20 && datopac.getId_cuaderno() == 1 && ("B".equals(datopac.getTipo())) && datopac.getEndodoncia() == 1) ? 1 : 0;

///PERIODONCIA TARTECTOMIA VARONES         
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 13 && datopac.getEdad() < 20 && datopac.getId_cuaderno() == 2 && ("A".equals(datopac.getTipo()) || "D".equals(datopac.getTipo()) || "E".equals(datopac.getTipo())) && datopac.getPeriodoncia() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[22] += (datopac.getEdad() > 13 && datopac.getEdad() < 20 && datopac.getId_cuaderno() == 2 && ("A".equals(datopac.getTipo()) || "D".equals(datopac.getTipo()) || "E".equals(datopac.getTipo())) && datopac.getPeriodoncia() == 1) ? 1 : 0;
///PERIODONCIA TARTECTOMIA MUJERES         
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 13 && datopac.getEdad() < 20 && datopac.getId_cuaderno() == 1 && ("A".equals(datopac.getTipo()) || "D".equals(datopac.getTipo()) || "E".equals(datopac.getTipo())) && datopac.getPeriodoncia() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[23] += (datopac.getEdad() > 13 && datopac.getEdad() < 20 && datopac.getId_cuaderno() == 1 && ("A".equals(datopac.getTipo()) || "D".equals(datopac.getTipo()) || "E".equals(datopac.getTipo())) && datopac.getPeriodoncia() == 1) ? 1 : 0;
///PERIODONCIA Gingivectomia VARONES         
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 13 && datopac.getEdad() < 20 && datopac.getId_cuaderno() == 2 && ("B".equals(datopac.getTipo()) || "F".equals(datopac.getTipo())) && datopac.getPeriodoncia() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[24] += (datopac.getEdad() > 13 && datopac.getEdad() < 20 && datopac.getId_cuaderno() == 2 && ("B".equals(datopac.getTipo()) || "F".equals(datopac.getTipo())) && datopac.getPeriodoncia() == 1) ? 1 : 0;
///PERIODONCIA Gingivectomia MUJERES         
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 13 && datopac.getEdad() < 20 && datopac.getId_cuaderno() == 1 && ("B".equals(datopac.getTipo()) || "F".equals(datopac.getTipo())) && datopac.getPeriodoncia() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[25] += (datopac.getEdad() > 13 && datopac.getEdad() < 20 && datopac.getId_cuaderno() == 1 && ("B".equals(datopac.getTipo()) || "F".equals(datopac.getTipo())) && datopac.getPeriodoncia() == 1) ? 1 : 0;
///PERIODONCIA Trat. No quirurgico VARONES         
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 13 && datopac.getEdad() < 20 && datopac.getId_cuaderno() == 2 && "C".equals(datopac.getTipo()) && datopac.getPeriodoncia() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[26] += (datopac.getEdad() > 13 && datopac.getEdad() < 20 && datopac.getId_cuaderno() == 2 && "C".equals(datopac.getTipo()) && datopac.getPeriodoncia() == 1) ? 1 : 0;
///PERIODONCIA Trat. No quirurgico MUJERES         
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 13 && datopac.getEdad() < 20 && datopac.getId_cuaderno() == 1 && "C".equals(datopac.getTipo()) && datopac.getPeriodoncia() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[27] += (datopac.getEdad() > 13 && datopac.getEdad() < 20 && datopac.getId_cuaderno() == 1 && "C".equals(datopac.getTipo()) && datopac.getPeriodoncia() == 1) ? 1 : 0;

///EXODONCIA VARONES          
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 13 && datopac.getEdad() < 20 && datopac.getId_cuaderno() == 2 && "A".equals(datopac.getTipo()) && datopac.getExodoncia() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[28] += (datopac.getEdad() > 13 && datopac.getEdad() < 20 && datopac.getId_cuaderno() == 2 && "A".equals(datopac.getTipo()) && datopac.getExodoncia() == 1) ? 1 : 0;
///EXODONCIA MUJERES         
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 13 && datopac.getEdad() < 20 && datopac.getId_cuaderno() == 1 && "A".equals(datopac.getTipo()) && datopac.getExodoncia() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[29] += (datopac.getEdad() > 13 && datopac.getEdad() < 20 && datopac.getId_cuaderno() == 1 && "A".equals(datopac.getTipo()) && datopac.getExodoncia() == 1) ? 1 : 0;
///TRATAMIENTO ALVEOLITIS VARONES          
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 13 && datopac.getEdad() < 20 && datopac.getId_cuaderno() == 2 && "B".equals(datopac.getTipo()) && datopac.getExodoncia() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[30] += (datopac.getEdad() > 13 && datopac.getEdad() < 20 && datopac.getId_cuaderno() == 2 && "B".equals(datopac.getTipo()) && datopac.getExodoncia() == 1) ? 1 : 0;
///TRATAMIENTO ALVEOLITIS MUJERES          
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 13 && datopac.getEdad() < 20 && datopac.getId_cuaderno() == 1 && "B".equals(datopac.getTipo()) && datopac.getExodoncia() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[31] += (datopac.getEdad() > 13 && datopac.getEdad() < 20 && datopac.getId_cuaderno() == 1 && "B".equals(datopac.getTipo()) && datopac.getExodoncia() == 1) ? 1 : 0;
///TRAT ABCESO PERIAPICAL VARONES          
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 13 && datopac.getEdad() < 20 && datopac.getId_cuaderno() == 2 && "C".equals(datopac.getTipo()) && datopac.getExodoncia() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[32] += (datopac.getEdad() > 13 && datopac.getEdad() < 20 && datopac.getId_cuaderno() == 2 && "C".equals(datopac.getTipo()) && datopac.getExodoncia() == 1) ? 1 : 0;
///TRAT ABCESO PERIAPICAL MUJERES          
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 13 && datopac.getEdad() < 20 && datopac.getId_cuaderno() == 1 && "C".equals(datopac.getTipo()) && datopac.getExodoncia() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[33] += (datopac.getEdad() > 13 && datopac.getEdad() < 20 && datopac.getId_cuaderno() == 1 && "C".equals(datopac.getTipo()) && datopac.getExodoncia() == 1) ? 1 : 0;

////////////////////////////////////////////////////////////   DE 20 A 59 añoS        
////Menores de 5 años varones Primeras Consultas        
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 19 && datopac.getEdad() < 60 && datopac.getId_cuaderno() == 2 && datopac.getPrimera() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[34] += (datopac.getEdad() > 19 && datopac.getEdad() < 60 && datopac.getId_cuaderno() == 2 && datopac.getPrimera() == 1) ? 1 : 0;
////Menores de 5 años mujeres Primeras Consultas           
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 19 && datopac.getEdad() < 60 && datopac.getId_cuaderno() == 1 && datopac.getPrimera() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[35] += (datopac.getEdad() > 19 && datopac.getEdad() < 60 && datopac.getId_cuaderno() == 1 && datopac.getPrimera() == 1) ? 1 : 0;
////Menores de 5 años varones Nuevas          
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 19 && datopac.getEdad() < 60 && datopac.getId_cuaderno() == 2 && "N".equals(datopac.getTipoconsulta())) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[36] += (datopac.getEdad() > 19 && datopac.getEdad() < 60 && datopac.getId_cuaderno() == 2 && "N".equals(datopac.getTipoconsulta())) ? 1 : 0;
////Menores de 5 años mujeres Nuevas          
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 19 && datopac.getEdad() < 60 && datopac.getId_cuaderno() == 1 && "N".equals(datopac.getTipoconsulta())) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[37] += (datopac.getEdad() > 19 && datopac.getEdad() < 60 && datopac.getId_cuaderno() == 1 && "N".equals(datopac.getTipoconsulta())) ? 1 : 0;
////Menores de 5 años varones Repetidas           
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 19 && datopac.getEdad() < 60 && datopac.getId_cuaderno() == 2 && "R".equals(datopac.getTipoconsulta())) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[38] += (datopac.getEdad() > 19 && datopac.getEdad() < 60 && datopac.getId_cuaderno() == 2 && "R".equals(datopac.getTipoconsulta())) ? 1 : 0;
////Menores de 5 años mujeres Repetidas           
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 19 && datopac.getEdad() < 60 && datopac.getId_cuaderno() == 1 && "R".equals(datopac.getTipoconsulta())) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[39] += (datopac.getEdad() > 19 && datopac.getEdad() < 60 && datopac.getId_cuaderno() == 1 && "R".equals(datopac.getTipoconsulta())) ? 1 : 0;
///PROFILAXIS VARONES              
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 19 && datopac.getEdad() < 60 && datopac.getId_cuaderno() == 2 && "B".equals(datopac.getTipo()) && datopac.getPreventiva() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[40] += (datopac.getEdad() > 19 && datopac.getEdad() < 60 && datopac.getId_cuaderno() == 2 && "B".equals(datopac.getTipo()) && datopac.getPreventiva() == 1) ? 1 : 0;
///PROFILAXIS MUEJRES            
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 19 && datopac.getEdad() < 60 && datopac.getId_cuaderno() == 1 && "B".equals(datopac.getTipo()) && datopac.getPreventiva() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[41] += (datopac.getEdad() > 19 && datopac.getEdad() < 60 && datopac.getId_cuaderno() == 1 && "B".equals(datopac.getTipo()) && datopac.getPreventiva() == 1) ? 1 : 0;
///FLOURACION VARONES           
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 19 && datopac.getEdad() < 60 && datopac.getId_cuaderno() == 2 && "A".equals(datopac.getTipo()) && datopac.getPreventiva() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[42] += (datopac.getEdad() > 19 && datopac.getEdad() < 60 && datopac.getId_cuaderno() == 2 && "A".equals(datopac.getTipo()) && datopac.getPreventiva() == 1) ? 1 : 0;
///FLOURACION MUJERES           
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 19 && datopac.getEdad() < 60 && datopac.getId_cuaderno() == 1 && "A".equals(datopac.getTipo()) && datopac.getPreventiva() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[43] += (datopac.getEdad() > 19 && datopac.getEdad() < 60 && datopac.getId_cuaderno() == 1 && "A".equals(datopac.getTipo()) && datopac.getPreventiva() == 1) ? 1 : 0;
///SELLANTES VARONES           
                //cell = new PdfPCell(new Phrase((datopac.getEdad()>19 && datopac.getEdad()<60 && datopac.getId_cuaderno()==2 && "C".equals(datopac.getTipo()) && datopac.getPreventiva()==1)?"1":"" , DATO_FONT));
                //cell.setHorizontalAlignment(Element.ALIGN_CENTER);  table.addCell(cell);
                //sumbs[48]+=(datopac.getEdad()>19 && datopac.getEdad()<60 && datopac.getId_cuaderno()==2 && "C".equals(datopac.getTipo()) && datopac.getPreventiva()==1)?1:0;  
///SELLANTES MUEJERES           
                //cell = new PdfPCell(new Phrase((datopac.getEdad()>19 && datopac.getEdad()<60 && datopac.getId_cuaderno()==1 && "C".equals(datopac.getTipo()) && datopac.getPreventiva()==1)?"1":"" , DATO_FONT));
                //cell.setHorizontalAlignment(Element.ALIGN_CENTER);  table.addCell(cell);
                //sumbs[49]+=(datopac.getEdad()>19 && datopac.getEdad()<60 && datopac.getId_cuaderno()==1 && "C".equals(datopac.getTipo()) && datopac.getPreventiva()==1)?1:0;  
///CAROSTATICOS VARONES            
                //cell = new PdfPCell(new Phrase((datopac.getEdad()>19 && datopac.getEdad()<60 && datopac.getId_cuaderno()==2 && "D".equals(datopac.getTipo()) && datopac.getPreventiva()==1)?"1":"" , DATO_FONT));
                //cell.setHorizontalAlignment(Element.ALIGN_CENTER);  table.addCell(cell);
                //sumbs[50]+=(datopac.getEdad()>19 && datopac.getEdad()<60 && datopac.getId_cuaderno()==2 && "D".equals(datopac.getTipo()) && datopac.getPreventiva()==1)?1:0;  
///CAROSTATICOS MUJERES           
                //cell = new PdfPCell(new Phrase((datopac.getEdad()>19 && datopac.getEdad()<60 && datopac.getId_cuaderno()==1 && "D".equals(datopac.getTipo()) && datopac.getPreventiva()==1)?"1":"" , DATO_FONT));
                //cell.setHorizontalAlignment(Element.ALIGN_CENTER);  table.addCell(cell);
                //sumbs[51]+=(datopac.getEdad()>19 && datopac.getEdad()<60 && datopac.getId_cuaderno()==1 && "D".equals(datopac.getTipo()) && datopac.getPreventiva()==1)?1:0;  
///AMALGAMAS VARONES           
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 19 && datopac.getEdad() < 60 && datopac.getId_cuaderno() == 2 && "A".equals(datopac.getTipo()) && datopac.getRestauracion() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[44] += (datopac.getEdad() > 19 && datopac.getEdad() < 60 && datopac.getId_cuaderno() == 2 && "A".equals(datopac.getTipo()) && datopac.getRestauracion() == 1) ? 1 : 0;
///AMALGAMAS MUJERES           
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 19 && datopac.getEdad() < 60 && datopac.getId_cuaderno() == 1 && "A".equals(datopac.getTipo()) && datopac.getRestauracion() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[45] += (datopac.getEdad() > 19 && datopac.getEdad() < 60 && datopac.getId_cuaderno() == 1 && "A".equals(datopac.getTipo()) && datopac.getRestauracion() == 1) ? 1 : 0;
///RESINAS VARONES          
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 19 && datopac.getEdad() < 60 && datopac.getId_cuaderno() == 2 && ("B".equals(datopac.getTipo()) || "C".equals(datopac.getTipo())) && datopac.getRestauracion() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[46] += (datopac.getEdad() > 19 && datopac.getEdad() < 60 && datopac.getId_cuaderno() == 2 && ("B".equals(datopac.getTipo()) || "C".equals(datopac.getTipo())) && datopac.getRestauracion() == 1) ? 1 : 0;
///RESINAS MUJERES           
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 19 && datopac.getEdad() < 60 && datopac.getId_cuaderno() == 1 && ("B".equals(datopac.getTipo()) || "C".equals(datopac.getTipo())) && datopac.getRestauracion() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[47] += (datopac.getEdad() > 19 && datopac.getEdad() < 60 && datopac.getId_cuaderno() == 1 && ("B".equals(datopac.getTipo()) || "C".equals(datopac.getTipo())) && datopac.getRestauracion() == 1) ? 1 : 0;
///IONOMETRO VARONES           
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 19 && datopac.getEdad() < 60 && datopac.getId_cuaderno() == 2 && "D".equals(datopac.getTipo()) && datopac.getRestauracion() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[48] += (datopac.getEdad() > 19 && datopac.getEdad() < 60 && datopac.getId_cuaderno() == 2 && "D".equals(datopac.getTipo()) && datopac.getRestauracion() == 1) ? 1 : 0;
///IONOMETRO MUJERES            
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 19 && datopac.getEdad() < 60 && datopac.getId_cuaderno() == 1 && "D".equals(datopac.getTipo()) && datopac.getRestauracion() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[49] += (datopac.getEdad() > 19 && datopac.getEdad() < 60 && datopac.getId_cuaderno() == 1 && "D".equals(datopac.getTipo()) && datopac.getRestauracion() == 1) ? 1 : 0;
///PRAT VARONES          
                //cell = new PdfPCell(new Phrase((datopac.getEdad()>19 && datopac.getEdad()<60 && datopac.getId_cuaderno()==2 && "E".equals(datopac.getTipo()) && datopac.getRestauracion()==1)?"1":"" , DATO_FONT));
                //cell.setHorizontalAlignment(Element.ALIGN_CENTER);  table.addCell(cell);
                //sumbs[58]+=(datopac.getEdad()>19 && datopac.getEdad()<60 && datopac.getId_cuaderno()==2 && "E".equals(datopac.getTipo()) && datopac.getRestauracion()==1)?1:0;  
///PRAT MUJERES       
                //cell = new PdfPCell(new Phrase((datopac.getEdad()>4 && datopac.getEdad()<14 && datopac.getId_cuaderno()==1 && "E".equals(datopac.getTipo()) && datopac.getRestauracion()==1)?"1":"" , DATO_FONT));
                //cell.setHorizontalAlignment(Element.ALIGN_CENTER);  table.addCell(cell);
                //sumbs[59]+=(datopac.getEdad()>4 && datopac.getEdad()<14 && datopac.getId_cuaderno()==1 && "E".equals(datopac.getTipo()) && datopac.getRestauracion()==1)?1:0;  
///ENDODONCIA PULPOTOMIA VARONES          
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 19 && datopac.getEdad() < 60 && datopac.getId_cuaderno() == 2 && "A".equals(datopac.getTipo()) && datopac.getEndodoncia() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[50] += (datopac.getEdad() > 19 && datopac.getEdad() < 60 && datopac.getId_cuaderno() == 2 && "A".equals(datopac.getTipo()) && datopac.getEndodoncia() == 1) ? 1 : 0;
///ENDODONCIA PULPOTOMIA MUJERES 
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 19 && datopac.getEdad() < 60 && datopac.getId_cuaderno() == 1 && "A".equals(datopac.getTipo()) && datopac.getEndodoncia() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[51] += (datopac.getEdad() > 19 && datopac.getEdad() < 60 && datopac.getId_cuaderno() == 1 && "A".equals(datopac.getTipo()) && datopac.getEndodoncia() == 1) ? 1 : 0;
///ENDODONCIA TRATAMIENTO CONDUCTO VARONES          
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 19 && datopac.getEdad() < 60 && datopac.getId_cuaderno() == 2 && ("B".equals(datopac.getTipo())) && datopac.getEndodoncia() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[52] += (datopac.getEdad() > 19 && datopac.getEdad() < 60 && datopac.getId_cuaderno() == 2 && ("B".equals(datopac.getTipo())) && datopac.getEndodoncia() == 1) ? 1 : 0;
///ENDODONCIA TRATAMIENTO CONDUCTO MUJERES         
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 19 && datopac.getEdad() < 60 && datopac.getId_cuaderno() == 1 && ("B".equals(datopac.getTipo())) && datopac.getEndodoncia() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[53] += (datopac.getEdad() > 19 && datopac.getEdad() < 60 && datopac.getId_cuaderno() == 1 && ("B".equals(datopac.getTipo())) && datopac.getEndodoncia() == 1) ? 1 : 0;

///PERIODONCIA TARTECTOMIA VARONES         
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 19 && datopac.getEdad() < 60 && datopac.getId_cuaderno() == 2 && ("A".equals(datopac.getTipo()) || "D".equals(datopac.getTipo()) || "E".equals(datopac.getTipo())) && datopac.getPeriodoncia() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[54] += (datopac.getEdad() > 19 && datopac.getEdad() < 60 && datopac.getId_cuaderno() == 2 && ("A".equals(datopac.getTipo()) || "D".equals(datopac.getTipo()) || "E".equals(datopac.getTipo())) && datopac.getPeriodoncia() == 1) ? 1 : 0;
///PERIODONCIA TARTECTOMIA MUJERES         
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 19 && datopac.getEdad() < 60 && datopac.getId_cuaderno() == 1 && ("A".equals(datopac.getTipo()) || "D".equals(datopac.getTipo()) || "E".equals(datopac.getTipo())) && datopac.getPeriodoncia() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[55] += (datopac.getEdad() > 19 && datopac.getEdad() < 60 && datopac.getId_cuaderno() == 1 && ("A".equals(datopac.getTipo()) || "D".equals(datopac.getTipo()) || "E".equals(datopac.getTipo())) && datopac.getPeriodoncia() == 1) ? 1 : 0;
///PERIODONCIA Gingivectomia VARONES         
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 19 && datopac.getEdad() < 60 && datopac.getId_cuaderno() == 2 && ("B".equals(datopac.getTipo()) || "F".equals(datopac.getTipo())) && datopac.getPeriodoncia() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[56] += (datopac.getEdad() > 19 && datopac.getEdad() < 60 && datopac.getId_cuaderno() == 2 && ("B".equals(datopac.getTipo()) || "F".equals(datopac.getTipo())) && datopac.getPeriodoncia() == 1) ? 1 : 0;
///PERIODONCIA Gingivectomia MUJERES         
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 19 && datopac.getEdad() < 60 && datopac.getId_cuaderno() == 1 && ("B".equals(datopac.getTipo()) || "F".equals(datopac.getTipo())) && datopac.getPeriodoncia() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[57] += (datopac.getEdad() > 19 && datopac.getEdad() < 60 && datopac.getId_cuaderno() == 1 && ("B".equals(datopac.getTipo()) || "F".equals(datopac.getTipo())) && datopac.getPeriodoncia() == 1) ? 1 : 0;
///PERIODONCIA Trat. No quirurgico VARONES         
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 19 && datopac.getEdad() < 60 && datopac.getId_cuaderno() == 2 && "C".equals(datopac.getTipo()) && datopac.getPeriodoncia() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[58] += (datopac.getEdad() > 19 && datopac.getEdad() < 60 && datopac.getId_cuaderno() == 2 && "C".equals(datopac.getTipo()) && datopac.getPeriodoncia() == 1) ? 1 : 0;
///PERIODONCIA Trat. No quirurgico MUJERES         
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 19 && datopac.getEdad() < 60 && datopac.getId_cuaderno() == 1 && "C".equals(datopac.getTipo()) && datopac.getPeriodoncia() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[59] += (datopac.getEdad() > 19 && datopac.getEdad() < 60 && datopac.getId_cuaderno() == 1 && "C".equals(datopac.getTipo()) && datopac.getPeriodoncia() == 1) ? 1 : 0;

///EXODONCIA VARONES          
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 19 && datopac.getEdad() < 60 && datopac.getId_cuaderno() == 2 && "A".equals(datopac.getTipo()) && datopac.getExodoncia() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[60] += (datopac.getEdad() > 19 && datopac.getEdad() < 60 && datopac.getId_cuaderno() == 2 && "A".equals(datopac.getTipo()) && datopac.getExodoncia() == 1) ? 1 : 0;
///EXODONCIA MUJERES         
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 19 && datopac.getEdad() < 60 && datopac.getId_cuaderno() == 1 && "A".equals(datopac.getTipo()) && datopac.getExodoncia() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[61] += (datopac.getEdad() > 19 && datopac.getEdad() < 60 && datopac.getId_cuaderno() == 1 && "A".equals(datopac.getTipo()) && datopac.getExodoncia() == 1) ? 1 : 0;
///TRATAMIENTO ALVEOLITIS VARONES          
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 19 && datopac.getEdad() < 60 && datopac.getId_cuaderno() == 2 && "B".equals(datopac.getTipo()) && datopac.getExodoncia() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[62] += (datopac.getEdad() > 19 && datopac.getEdad() < 60 && datopac.getId_cuaderno() == 2 && "B".equals(datopac.getTipo()) && datopac.getExodoncia() == 1) ? 1 : 0;
///TRATAMIENTO ALVEOLITIS MUJERES          
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 19 && datopac.getEdad() < 60 && datopac.getId_cuaderno() == 1 && "B".equals(datopac.getTipo()) && datopac.getExodoncia() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[63] += (datopac.getEdad() > 19 && datopac.getEdad() < 60 && datopac.getId_cuaderno() == 1 && "B".equals(datopac.getTipo()) && datopac.getExodoncia() == 1) ? 1 : 0;
///TRAT ABCESO PERIAPICAL VARONES          
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 19 && datopac.getEdad() < 60 && datopac.getId_cuaderno() == 2 && "C".equals(datopac.getTipo()) && datopac.getExodoncia() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[64] += (datopac.getEdad() > 19 && datopac.getEdad() < 60 && datopac.getId_cuaderno() == 2 && "C".equals(datopac.getTipo()) && datopac.getExodoncia() == 1) ? 1 : 0;
///TRAT ABCESO PERIAPICAL MUJERES          
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 19 && datopac.getEdad() < 60 && datopac.getId_cuaderno() == 1 && "C".equals(datopac.getTipo()) && datopac.getExodoncia() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[65] += (datopac.getEdad() > 19 && datopac.getEdad() < 60 && datopac.getId_cuaderno() == 1 && "C".equals(datopac.getTipo()) && datopac.getExodoncia() == 1) ? 1 : 0;

////////////////////////////////////////////////////////////   mayores de 60 añoS             
                ////Menores de 5 años varones Primeras Consultas        
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 59 && datopac.getId_cuaderno() == 2 && datopac.getPrimera() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[66] += (datopac.getEdad() > 59 && datopac.getId_cuaderno() == 2 && datopac.getPrimera() == 1) ? 1 : 0;
////Menores de 5 años mujeres Primeras Consultas           
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 59 && datopac.getId_cuaderno() == 1 && datopac.getPrimera() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[67] += (datopac.getEdad() > 59 && datopac.getId_cuaderno() == 1 && datopac.getPrimera() == 1) ? 1 : 0;
////Menores de 5 años varones Nuevas          
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 59 && datopac.getId_cuaderno() == 2 && "N".equals(datopac.getTipoconsulta())) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[68] += (datopac.getEdad() > 59 && datopac.getId_cuaderno() == 2 && "N".equals(datopac.getTipoconsulta())) ? 1 : 0;
////Menores de 5 años mujeres Nuevas          
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 59 && datopac.getId_cuaderno() == 1 && "N".equals(datopac.getTipoconsulta())) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[69] += (datopac.getEdad() > 59 && datopac.getId_cuaderno() == 1 && "N".equals(datopac.getTipoconsulta())) ? 1 : 0;
////Menores de 5 años varones Repetidas           
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 59 && datopac.getId_cuaderno() == 2 && "R".equals(datopac.getTipoconsulta())) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[70] += (datopac.getEdad() > 59 && datopac.getId_cuaderno() == 2 && "R".equals(datopac.getTipoconsulta())) ? 1 : 0;
////Menores de 5 años mujeres Repetidas           
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 59 && datopac.getId_cuaderno() == 1 && "R".equals(datopac.getTipoconsulta())) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[71] += (datopac.getEdad() > 59 && datopac.getId_cuaderno() == 1 && "R".equals(datopac.getTipoconsulta())) ? 1 : 0;
///PROFILAXIS VARONES              
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 59 && datopac.getId_cuaderno() == 2 && "B".equals(datopac.getTipo()) && datopac.getPreventiva() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[72] += (datopac.getEdad() > 59 && datopac.getId_cuaderno() == 2 && "B".equals(datopac.getTipo()) && datopac.getPreventiva() == 1) ? 1 : 0;
///PROFILAXIS MUEJRES            
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 59 && datopac.getId_cuaderno() == 1 && "B".equals(datopac.getTipo()) && datopac.getPreventiva() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[73] += (datopac.getEdad() > 59 && datopac.getId_cuaderno() == 1 && "B".equals(datopac.getTipo()) && datopac.getPreventiva() == 1) ? 1 : 0;
///FLOURACION VARONES           
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 59 && datopac.getId_cuaderno() == 2 && "A".equals(datopac.getTipo()) && datopac.getPreventiva() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[74] += (datopac.getEdad() > 59 && datopac.getId_cuaderno() == 2 && "A".equals(datopac.getTipo()) && datopac.getPreventiva() == 1) ? 1 : 0;
///FLOURACION MUJERES           
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 59 && datopac.getId_cuaderno() == 1 && "A".equals(datopac.getTipo()) && datopac.getPreventiva() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[75] += (datopac.getEdad() > 59 && datopac.getId_cuaderno() == 1 && "A".equals(datopac.getTipo()) && datopac.getPreventiva() == 1) ? 1 : 0;
///SELLANTES VARONES           
                //cell = new PdfPCell(new Phrase(( datopac.getEdad()>59 && datopac.getId_cuaderno()==2 && "C".equals(datopac.getTipo()) && datopac.getPreventiva()==1)?"1":"" , DATO_FONT));
                //cell.setHorizontalAlignment(Element.ALIGN_CENTER);  table.addCell(cell);
                //sumbs[86]+=( datopac.getEdad()>59 && datopac.getId_cuaderno()==2 && "C".equals(datopac.getTipo()) && datopac.getPreventiva()==1)?1:0;  
///SELLANTES MUEJERES           
                //cell = new PdfPCell(new Phrase(( datopac.getEdad()>59 && datopac.getId_cuaderno()==1 && "C".equals(datopac.getTipo()) && datopac.getPreventiva()==1)?"1":"" , DATO_FONT));
                //cell.setHorizontalAlignment(Element.ALIGN_CENTER);  table.addCell(cell);
                //sumbs[87]+=( datopac.getEdad()>59 && datopac.getId_cuaderno()==1 && "C".equals(datopac.getTipo()) && datopac.getPreventiva()==1)?1:0;  
///CAROSTATICOS VARONES            
                //cell = new PdfPCell(new Phrase(( datopac.getEdad()>59 && datopac.getId_cuaderno()==2 && "D".equals(datopac.getTipo()) && datopac.getPreventiva()==1)?"1":"" , DATO_FONT));
                //cell.setHorizontalAlignment(Element.ALIGN_CENTER);  table.addCell(cell);
                //sumbs[76]+=( datopac.getEdad()>59 && datopac.getId_cuaderno()==2 && "D".equals(datopac.getTipo()) && datopac.getPreventiva()==1)?1:0;  
///CAROSTATICOS MUJERES           
                //cell = new PdfPCell(new Phrase(( datopac.getEdad()>59 && datopac.getId_cuaderno()==1 && "D".equals(datopac.getTipo()) && datopac.getPreventiva()==1)?"1":"" , DATO_FONT));
                //cell.setHorizontalAlignment(Element.ALIGN_CENTER);  table.addCell(cell);
                //sumbs[89]+=( datopac.getEdad()>59 && datopac.getId_cuaderno()==1 && "D".equals(datopac.getTipo()) && datopac.getPreventiva()==1)?1:0;  
///AMALGAMAS VARONES           
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 59 && datopac.getId_cuaderno() == 2 && "A".equals(datopac.getTipo()) && datopac.getRestauracion() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[76] += (datopac.getEdad() > 59 && datopac.getId_cuaderno() == 2 && "A".equals(datopac.getTipo()) && datopac.getRestauracion() == 1) ? 1 : 0;
///AMALGAMAS MUJERES           
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 59 && datopac.getId_cuaderno() == 1 && "A".equals(datopac.getTipo()) && datopac.getRestauracion() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[77] += (datopac.getEdad() > 59 && datopac.getId_cuaderno() == 1 && "A".equals(datopac.getTipo()) && datopac.getRestauracion() == 1) ? 1 : 0;
///RESINAS VARONES          
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 59 && datopac.getId_cuaderno() == 2 && ("B".equals(datopac.getTipo()) || "C".equals(datopac.getTipo())) && datopac.getRestauracion() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[78] += (datopac.getEdad() > 59 && datopac.getId_cuaderno() == 2 && ("B".equals(datopac.getTipo()) || "C".equals(datopac.getTipo())) && datopac.getRestauracion() == 1) ? 1 : 0;
///RESINAS MUJERES           
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 59 && datopac.getId_cuaderno() == 1 && ("B".equals(datopac.getTipo()) || "C".equals(datopac.getTipo())) && datopac.getRestauracion() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[79] += (datopac.getEdad() > 59 && datopac.getId_cuaderno() == 1 && ("B".equals(datopac.getTipo()) || "C".equals(datopac.getTipo())) && datopac.getRestauracion() == 1) ? 1 : 0;
///IONOMETRO VARONES           
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 59 && datopac.getId_cuaderno() == 2 && "D".equals(datopac.getTipo()) && datopac.getRestauracion() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[80] += (datopac.getEdad() > 59 && datopac.getId_cuaderno() == 2 && "D".equals(datopac.getTipo()) && datopac.getRestauracion() == 1) ? 1 : 0;
///IONOMETRO MUJERES            
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 59 && datopac.getId_cuaderno() == 1 && "D".equals(datopac.getTipo()) && datopac.getRestauracion() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[81] += (datopac.getEdad() > 59 && datopac.getId_cuaderno() == 1 && "D".equals(datopac.getTipo()) && datopac.getRestauracion() == 1) ? 1 : 0;
///PRAT VARONES          
                //cell = new PdfPCell(new Phrase(( datopac.getEdad()>59 && datopac.getId_cuaderno()==2 && "E".equals(datopac.getTipo()) && datopac.getRestauracion()==1)?"1":"" , DATO_FONT));
                //cell.setHorizontalAlignment(Element.ALIGN_CENTER);  table.addCell(cell);
                //sumbs[96]+=( datopac.getEdad()>59 && datopac.getId_cuaderno()==2 && "E".equals(datopac.getTipo()) && datopac.getRestauracion()==1)?1:0;  
///PRAT MUJERES       
                //cell = new PdfPCell(new Phrase((datopac.getEdad()>4 && datopac.getEdad()<14 && datopac.getId_cuaderno()==1 && "E".equals(datopac.getTipo()) && datopac.getRestauracion()==1)?"1":"" , DATO_FONT));
                //cell.setHorizontalAlignment(Element.ALIGN_CENTER);  table.addCell(cell);
                //sumbs[97]+=(datopac.getEdad()>4 && datopac.getEdad()<14 && datopac.getId_cuaderno()==1 && "E".equals(datopac.getTipo()) && datopac.getRestauracion()==1)?1:0;  
///ENDODONCIA PULPOTOMIA VARONES          
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 59 && datopac.getId_cuaderno() == 2 && "A".equals(datopac.getTipo()) && datopac.getEndodoncia() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[82] += (datopac.getEdad() > 59 && datopac.getId_cuaderno() == 2 && "A".equals(datopac.getTipo()) && datopac.getEndodoncia() == 1) ? 1 : 0;
///ENDODONCIA PULPOTOMIA MUJERES 
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 59 && datopac.getId_cuaderno() == 1 && "A".equals(datopac.getTipo()) && datopac.getEndodoncia() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[83] += (datopac.getEdad() > 59 && datopac.getId_cuaderno() == 1 && "A".equals(datopac.getTipo()) && datopac.getEndodoncia() == 1) ? 1 : 0;
///ENDODONCIA TRATAMIENTO CONDUCTO VARONES          
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 59 && datopac.getId_cuaderno() == 2 && ("B".equals(datopac.getTipo())) && datopac.getEndodoncia() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[84] += (datopac.getEdad() > 59 && datopac.getId_cuaderno() == 2 && ("B".equals(datopac.getTipo())) && datopac.getEndodoncia() == 1) ? 1 : 0;
///ENDODONCIA TRATAMIENTO CONDUCTO MUJERES         
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 59 && datopac.getId_cuaderno() == 1 && ("B".equals(datopac.getTipo())) && datopac.getEndodoncia() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[85] += (datopac.getEdad() > 59 && datopac.getId_cuaderno() == 1 && ("B".equals(datopac.getTipo())) && datopac.getEndodoncia() == 1) ? 1 : 0;

///PERIODONCIA TARTECTOMIA VARONES         
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 59 && datopac.getId_cuaderno() == 2 && ("A".equals(datopac.getTipo()) || "D".equals(datopac.getTipo()) || "E".equals(datopac.getTipo())) && datopac.getPeriodoncia() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[86] += (datopac.getEdad() > 59 && datopac.getId_cuaderno() == 2 && ("A".equals(datopac.getTipo()) || "D".equals(datopac.getTipo()) || "E".equals(datopac.getTipo())) && datopac.getPeriodoncia() == 1) ? 1 : 0;
///PERIODONCIA TARTECTOMIA MUJERES         
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 59 && datopac.getId_cuaderno() == 1 && ("A".equals(datopac.getTipo()) || "D".equals(datopac.getTipo()) || "E".equals(datopac.getTipo())) && datopac.getPeriodoncia() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[87] += (datopac.getEdad() > 59 && datopac.getId_cuaderno() == 1 && ("A".equals(datopac.getTipo()) || "D".equals(datopac.getTipo()) || "E".equals(datopac.getTipo())) && datopac.getPeriodoncia() == 1) ? 1 : 0;
///PERIODONCIA Gingivectomia VARONES         
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 59 && datopac.getId_cuaderno() == 2 && ("B".equals(datopac.getTipo()) || "F".equals(datopac.getTipo())) && datopac.getPeriodoncia() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[88] += (datopac.getEdad() > 59 && datopac.getId_cuaderno() == 2 && ("B".equals(datopac.getTipo()) || "F".equals(datopac.getTipo())) && datopac.getPeriodoncia() == 1) ? 1 : 0;
///PERIODONCIA Gingivectomia MUJERES         
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 59 && datopac.getId_cuaderno() == 1 && ("B".equals(datopac.getTipo()) || "F".equals(datopac.getTipo())) && datopac.getPeriodoncia() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[89] += (datopac.getEdad() > 59 && datopac.getId_cuaderno() == 1 && ("B".equals(datopac.getTipo()) || "F".equals(datopac.getTipo())) && datopac.getPeriodoncia() == 1) ? 1 : 0;
///PERIODONCIA Trat. No quirurgico VARONES         
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 59 && datopac.getId_cuaderno() == 2 && "C".equals(datopac.getTipo()) && datopac.getPeriodoncia() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[90] += (datopac.getEdad() > 59 && datopac.getId_cuaderno() == 2 && "C".equals(datopac.getTipo()) && datopac.getPeriodoncia() == 1) ? 1 : 0;
///PERIODONCIA Trat. No quirurgico MUJERES         
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 59 && datopac.getId_cuaderno() == 1 && "C".equals(datopac.getTipo()) && datopac.getPeriodoncia() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[91] += (datopac.getEdad() > 59 && datopac.getId_cuaderno() == 1 && "C".equals(datopac.getTipo()) && datopac.getPeriodoncia() == 1) ? 1 : 0;

///EXODONCIA VARONES          
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 59 && datopac.getId_cuaderno() == 2 && "A".equals(datopac.getTipo()) && datopac.getExodoncia() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[92] += (datopac.getEdad() > 59 && datopac.getId_cuaderno() == 2 && "A".equals(datopac.getTipo()) && datopac.getExodoncia() == 1) ? 1 : 0;
///EXODONCIA MUJERES         
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 59 && datopac.getId_cuaderno() == 1 && "A".equals(datopac.getTipo()) && datopac.getExodoncia() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[93] += (datopac.getEdad() > 59 && datopac.getId_cuaderno() == 1 && "A".equals(datopac.getTipo()) && datopac.getExodoncia() == 1) ? 1 : 0;
///TRATAMIENTO ALVEOLITIS VARONES          
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 59 && datopac.getId_cuaderno() == 2 && "B".equals(datopac.getTipo()) && datopac.getExodoncia() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[94] += (datopac.getEdad() > 59 && datopac.getId_cuaderno() == 2 && "B".equals(datopac.getTipo()) && datopac.getExodoncia() == 1) ? 1 : 0;
///TRATAMIENTO ALVEOLITIS MUJERES          
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 59 && datopac.getId_cuaderno() == 1 && "B".equals(datopac.getTipo()) && datopac.getExodoncia() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[95] += (datopac.getEdad() > 59 && datopac.getId_cuaderno() == 1 && "B".equals(datopac.getTipo()) && datopac.getExodoncia() == 1) ? 1 : 0;
///TRAT ABCESO PERIAPICAL VARONES          
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 59 && datopac.getId_cuaderno() == 2 && "C".equals(datopac.getTipo()) && datopac.getExodoncia() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[96] += (datopac.getEdad() > 59 && datopac.getId_cuaderno() == 2 && "C".equals(datopac.getTipo()) && datopac.getExodoncia() == 1) ? 1 : 0;
///TRAT ABCESO PERIAPICAL MUJERES          
                cell = new PdfPCell(new Phrase((datopac.getEdad() > 59 && datopac.getId_cuaderno() == 1 && "C".equals(datopac.getTipo()) && datopac.getExodoncia() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[97] += (datopac.getEdad() > 59 && datopac.getId_cuaderno() == 1 && "C".equals(datopac.getTipo()) && datopac.getExodoncia() == 1) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getOtras() == 1) ? datopac.getTipo() : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[98] += (datopac.getOtras() == 1) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getRayosx() == 1 && "D".equals(datopac.getTipo())) ? "X" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);
                sumbs[99] += (datopac.getRayosx() == 1 && "D".equals(datopac.getTipo())) ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getRayosx() == 1 && "F".equals(datopac.getTipo())) ? "X" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);
                sumbs[100] += (datopac.getRayosx() == 1 && "F".equals(datopac.getTipo())) ? 1 : 0;
                ///referencias
                cell = new PdfPCell(new Phrase("", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);
                ///contrareferencias
                cell = new PdfPCell(new Phrase("", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

            }

            for (int i = lFopos.size(); i < pag * filas + filas; i++) {
                cell = new PdfPCell(new Phrase(Integer.toString(i + 1), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);
                for (int j = 1; j < NumColumns2; j++) {
                    cell = new PdfPCell(new Phrase("", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);
                }
            }

            cell = new PdfPCell(new Phrase("T.", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);

            for (int j = 0; j < 104; j++) {
                cell = new PdfPCell(new Phrase(Integer.toString(sumbs[j]), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(cell);
            }

            for (int j = 1; j < 21; j++) {
                cell = new PdfPCell(new Phrase("", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);
            }

            document.add(table);
            document.newPage();
        }
    }
//nueva hoja para   

    public static void addfila74(PdfPTable table, String tit) {
        PdfPCell cell;
        PdfPTable table1 = new PdfPTable(36);

        cell = new PdfPCell(new Phrase(tit, CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(36);
        table1.addCell(cell);

        addfila23(table1, "Consulta", "1ra consulta", "Nueva", "Repetida");
        addfila23(table1, "Medidas Preventivas", "Profilaxis", "Fluor topico", "Sella Fosas fisur");
        addfila16(table1);

        cell = new PdfPCell(table1);
        cell.setColspan(36);
        table.addCell(cell);
    }

    public static void addfila76(PdfPTable table, String tit) {
        PdfPCell cell;
        PdfPTable table1 = new PdfPTable(34);

        cell = new PdfPCell(new Phrase(tit, CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(34);
        table1.addCell(cell);

        addfila23(table1, "Consulta", "1ra consulta", "Nueva", "Repetida");
        addfila23(table1, "Medidas Preventivas", "Profilaxis", "Fluor topico", "Sella Fosas fisur");
        addfila162(table1);

        cell = new PdfPCell(table1);
        cell.setColspan(34);
        table.addCell(cell);
    }

    public static void addfila77(PdfPTable table, String tit) {
        PdfPCell cell;
        PdfPTable table1 = new PdfPTable(32);

        cell = new PdfPCell(new Phrase(tit, CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(32);
        table1.addCell(cell);

        addfila23(table1, "Consulta", "1ra consulta", "Nueva", "Repetida");
        addfila24(table1, "Medidas Preventivas", "Profilaxis", "Fluor topico");
        addfila162(table1);

        cell = new PdfPCell(table1);
        cell.setColspan(32);
        table.addCell(cell);
    }

    public static void addfila75(PdfPTable table, String tit) {
        PdfPCell cell;
        PdfPTable table1 = new PdfPTable(30);

        cell = new PdfPCell(new Phrase(tit, CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(30);
        table1.addCell(cell);

        addfila23(table1, "Consulta", "1ra consulta", "Nueva", "Repetida");
        addfila4(table1, "Medidas Preventivas", "Profilaxis", "Fluor topico", "Sella Fosas fisur", "Apl cariostatico");
        addfila161(table1);

        cell = new PdfPCell(table1);
        cell.setColspan(30);
        table.addCell(cell);
    }

    public static void addfila161(PdfPTable table) {
        PdfPCell cell;
        PdfPTable table1 = new PdfPTable(16);

        cell = new PdfPCell(new Phrase("Acciones curativas", CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(16);
        table1.addCell(cell);

        addfila4(table1, "Restauraciones", "Amalgama", "Resina", "Ionume Vdr obt", "PRAT-TRA");
        addfila24(table1, "Endodon", "Pulpotomia", "Trat Endod");
        addfila24(table1, "Cir Bucal", "Exod", "Trt Abs Per");

        cell = new PdfPCell(table1);
        cell.setColspan(16);
        table.addCell(cell);
    }

    public static void addfila16(PdfPTable table) {
        PdfPCell cell;
        PdfPTable table1 = new PdfPTable(24);

        cell = new PdfPCell(new Phrase("Acciones curativas", CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(24);
        table1.addCell(cell);

        addfila4(table1, "Restauraciones", "Amalga", "Resina", "Ionume", "Prat-Tr");
        addfila24(table1, "Endodon", "Pulpot", "Tr End Uni");
        addfila23(table1, "Period", "Tartrec.", "Gingiv. S.", "Trat. No Q.");
        addfila23(table1, "Cir Buc", "Exodon", "Trat Alveol", "Tr Abs Per");

        cell = new PdfPCell(table1);
        cell.setColspan(24);
        table.addCell(cell);
    }

    public static void addfila162(PdfPTable table) {
        PdfPCell cell;
        PdfPTable table1 = new PdfPTable(22);

        cell = new PdfPCell(new Phrase("Acciones curativas", CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(22);
        table1.addCell(cell);

        addfila23(table1, "Restauraciones", "Amalga", "Resina", "Ionume");
        addfila24(table1, "Endodon", "Pulpot", "Tr End Uni");
        addfila23(table1, "Period", "Tartrec.", "Gingiv. S.", "Trat. No Q.");
        addfila23(table1, "Cir Buc", "Exodon", "Trat Alveol", "Tr Abs Per");

        cell = new PdfPCell(table1);
        cell.setColspan(22);
        table.addCell(cell);
    }

    public static void addfila4(PdfPTable table, String tit, String a, String b, String c, String d) {
        PdfPCell cell;
        PdfPTable table1 = new PdfPTable(8);

        cell = new PdfPCell(new Phrase(tit, CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(8);
        table1.addCell(cell);

        addfila2(table1, a, "M", "F");
        addfila2(table1, b, "M", "F");
        addfila2(table1, c, "M", "F");
        addfila2(table1, d, "M", "F");

        cell = new PdfPCell(table1);
        cell.setColspan(8);
        table.addCell(cell);
    }

    public static void addfila23(PdfPTable table, String tit, String a, String b, String c) {
        PdfPCell cell;
        PdfPTable table1 = new PdfPTable(6);

        cell = new PdfPCell(new Phrase(tit, CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(6);
        table1.addCell(cell);

        addfila2(table1, a, "M", "F");
        addfila2(table1, b, "M", "F");
        addfila2(table1, c, "M", "F");

        cell = new PdfPCell(table1);
        cell.setColspan(6);
        table.addCell(cell);
    }

    public static void addfila24(PdfPTable table, String tit, String a, String b) {
        PdfPCell cell;
        PdfPTable table1 = new PdfPTable(4);

        cell = new PdfPCell(new Phrase(tit, CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(4);
        table1.addCell(cell);

        addfila2(table1, a, "M", "F");
        addfila2(table1, b, "M", "F");

        cell = new PdfPCell(table1);
        cell.setColspan(4);
        table.addCell(cell);
    }

    public static void addfila2(PdfPTable table, String a, String b, String c) {
        PdfPCell cell;
        PdfPTable table1 = new PdfPTable(2);

        cell = new PdfPCell(new Phrase(a, CABEZA_COLUMNA_FONT));
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

    public static void addfila32(PdfPTable table, String tit, String a, String b, String c) {
        PdfPCell cell;
        PdfPTable table1 = new PdfPTable(3);

        cell = new PdfPCell(new Phrase(tit, CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setColspan(3);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase(a, CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase(b, CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase(c, CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1.addCell(cell);

        cell = new PdfPCell(table1);
        cell.setColspan(3);
        table.addCell(cell);
    }

    public static void addfila23(PdfPTable table, String a, String b, String c) {
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

    public static String format(Date dia, String formato) {
        if (dia == null) {
            return "";
        }
        SimpleDateFormat formatDate = new SimpleDateFormat(formato);
        return formatDate.format(dia);
    }
}
