package org.ayaic.web.administrarhistoriales;

import java.awt.Color;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.view.document.AbstractPdfView;
import java.util.*;
import java.text.*;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import org.ayaic.domain.Cuadernos;
import org.ayaic.domain.Departamentos;
import org.ayaic.domain.Localidades;
import org.ayaic.domain.Provincias;

public class ListarIndicadoresPDF extends AbstractPdfView {

    private static final Font TITULO_FONT = new Font(Font.HELVETICA, 12, Font.BOLD, Color.black);
    private static final Font CABEZA_COLUMNA_FONT = new Font(Font.HELVETICA, 8, Font.ITALIC, Color.black);
    private static final Font DATO_FONT = new Font(Font.HELVETICA, 8, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_A = new Font(Font.HELVETICA, 6, Font.NORMAL, Color.black);
    private static final int MARGIN = 32;

    protected void buildPdfMetadata(Map model, Document document, HttpServletRequest request) {
        document.setPageSize(PageSize.LETTER);
        document.setMargins(20, 20, 20, 20);
    }

    protected void buildPdfDocument(Map model, Document document, PdfWriter writer, HttpServletRequest req, HttpServletResponse resp) throws Exception {

        java.util.List pacientess = (java.util.List) model.get("dato");
        Departamentos datoDep = (Departamentos) model.get("listaDep");
        Provincias datoProv = (Provincias) model.get("listaProv");
        Localidades datoLoc = (Localidades) model.get("listaLoc");
        Cuadernos listaIndi = (Cuadernos) model.get("ListaIndi");

        DecimalFormat df = new DecimalFormat("###,##0.00");
        Image escudo = Image.getInstance("/opt/imagenes/lescudo.bmp");
        Paragraph p = new Paragraph("INFORME SEMESTRAL DE DESEMPEï¿½O GERENCIAL HOSPITALARIO\n  ACOPIO DE INFORMACION \n     CODIGO:R.A. SALUD INE - 309-A(01/2010) ", new Font(Font.HELVETICA, 14, Font.BOLDITALIC, new Color(0, 0, 0)));
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

        document.add(tablex);

        PdfPTable tableo = new PdfPTable(4);
        int[] fxwidtho = {10, 50, 30, 10}; // percentage
        tableo.setWidths(fxwidtho);
        tableo.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("SEDES : ORURO                                                                              RED :  ORURO                                                          MUNICIPIO : ORURO \n\nESTABLECIMIENTO :    RAFAEL PABON                                                                        Año : 2011", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setColspan(4);
        tableo.addCell(cell);

        cell = new PdfPCell(new Phrase("Clase", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.9f);
        tableo.addCell(cell);

        cell = new PdfPCell(new Phrase("Indicadores de accesibilidad a los\n   servicios,disponibilidad de recursos \n   sustentabilidad financiera", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.9f);
        tableo.addCell(cell);

        cell = new PdfPCell(new Phrase("Variables", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.9f);
        tableo.addCell(cell);

        cell = new PdfPCell(new Phrase("Valor", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.9f);
        tableo.addCell(cell);

        document.add(tableo);

        PdfPTable table = new PdfPTable(5);
        int[] fxwidt = {10, 25, 25, 30, 10}; // percentage
        table.setWidths(fxwidt);
        table.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("ESTRUCTURA", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setRotation(90);
        table.addCell(cell);
        //COLOCA LA PRIMERA FILA DE VARIABLES 
        PdfPTable table1 = new PdfPTable(4);
        int[] fxwidt1 = {25, 25, 30, 10}; // percentage
        table1.setWidths(fxwidt1);
        table1.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("No. De camas por medico", DATO_FONT));
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("Disponibilidad", DATO_FONT));
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1.addCell(cell);

        PdfPTable table2 = new PdfPTable(2);
        int[] fxwidt2 = {75, 25}; // percentage
        table2.setWidths(fxwidt2);
        table2.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("Camas censables", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table2.addCell(cell);

        cell = new PdfPCell(new Phrase(Integer.toString(listaIndi.getAuto()), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table2.addCell(cell);

        cell = new PdfPCell(new Phrase("No. Medicos Hospit. (TC)(denominador)", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table2.addCell(cell);

        cell = new PdfPCell(new Phrase("5", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table2.addCell(cell);

        cell = new PdfPCell(table2);
        cell.setVerticalAlignment(Element.ALIGN_TOP);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(2);
        table1.addCell(cell);
        ///FIN DE LAS PRIMERAS FILAS
        ///COMIENZA NUEVA FILA            
        cell = new PdfPCell(new Phrase("No. De camas por enfermeras", DATO_FONT));
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("Disponibilidad", DATO_FONT));
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1.addCell(cell);

        PdfPTable table22 = new PdfPTable(2);
        int[] fxwidt22 = {75, 25}; // percentage
        table22.setWidths(fxwidt22);
        table22.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("Camas censables", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table22.addCell(cell);

        cell = new PdfPCell(new Phrase(Integer.toString(listaIndi.getAuto()), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table22.addCell(cell);

        cell = new PdfPCell(new Phrase("Enfermeras de hospitalizacion (deniminador)", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table22.addCell(cell);

        cell = new PdfPCell(new Phrase("6", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table22.addCell(cell);

        cell = new PdfPCell(table22);
        cell.setVerticalAlignment(Element.ALIGN_TOP);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(2);
        table1.addCell(cell);
        ///FIN DE LAS SEGUNDAS FILAS
        ///COMIENZA LA TERCERA FILA             
        cell = new PdfPCell(new Phrase("No. De camas x aux. de enfermeria", DATO_FONT));
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("Disponibilidad", DATO_FONT));
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1.addCell(cell);

        PdfPTable table23 = new PdfPTable(2);
        int[] fxwidt23 = {75, 25}; // percentage
        table23.setWidths(fxwidt23);
        table23.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("Camas censables", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table23.addCell(cell);

        cell = new PdfPCell(new Phrase(Integer.toString(listaIndi.getAuto()), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table23.addCell(cell);

        cell = new PdfPCell(new Phrase("Aux. de enfermeria de hosp. (deniminador)", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table23.addCell(cell);

        cell = new PdfPCell(new Phrase("12", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table23.addCell(cell);

        cell = new PdfPCell(table23);
        cell.setVerticalAlignment(Element.ALIGN_TOP);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(2);
        table1.addCell(cell);

        //FIN DE LA TERCERA FILA      
        ///COMIENZA LA CUARTA FILA             
        cell = new PdfPCell(new Phrase("No. De enfermeras por medico", DATO_FONT));
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("Disponibilidad", DATO_FONT));
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1.addCell(cell);

        PdfPTable table24 = new PdfPTable(2);
        int[] fxwidt24 = {75, 25}; // percentage
        table24.setWidths(fxwidt24);
        table24.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("Camas censables", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table24.addCell(cell);

        cell = new PdfPCell(new Phrase(Integer.toString(listaIndi.getAuto()), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table24.addCell(cell);

        cell = new PdfPCell(new Phrase("Medicos (TC) (denominador)", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table24.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table24.addCell(cell);

        cell = new PdfPCell(table24);
        cell.setVerticalAlignment(Element.ALIGN_TOP);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(2);
        table1.addCell(cell);

        //FIN DE LA CUARTA FILA   
        ///COMIENZA LA QUINTA FILA             
        cell = new PdfPCell(new Phrase("Dotacion de recurso Humano", DATO_FONT));
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("Disponibilidad", DATO_FONT));
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1.addCell(cell);

        PdfPTable table25 = new PdfPTable(2);
        int[] fxwidt25 = {75, 25}; // percentage
        table25.setWidths(fxwidt25);
        table25.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("No. De horas medicas en CE", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table25.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table25.addCell(cell);

        cell = new PdfPCell(new Phrase("No. De horas medicas en emergencia", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table25.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table25.addCell(cell);

        cell = new PdfPCell(new Phrase("No. De horas medicas en Hospitalizacion", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table25.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table25.addCell(cell);

        cell = new PdfPCell(new Phrase("No. De horas enfermera en CE", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table25.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table25.addCell(cell);

        cell = new PdfPCell(new Phrase("No. De horas Enfermera en Emergencia", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table25.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table25.addCell(cell);

        cell = new PdfPCell(new Phrase("No. De horas Enfermeras en hospitalizacion", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table25.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table25.addCell(cell);

        cell = new PdfPCell(new Phrase("No. De horas de auxiliar de enfermeria", DATO_FONT));
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table25.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table25.addCell(cell);

        cell = new PdfPCell(new Phrase("No. De horas de tecnicos calificados", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table25.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table25.addCell(cell);

        cell = new PdfPCell(new Phrase("No. De horas de otros profesionales", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table25.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table25.addCell(cell);

        cell = new PdfPCell(new Phrase("No. De horas de administrativos", DATO_FONT));
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table25.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table25.addCell(cell);

        cell = new PdfPCell(new Phrase("No. De horas de personal de servicio", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table25.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table25.addCell(cell);

        cell = new PdfPCell(new Phrase("Total Horas Medicas (Incluye servicios de apoyo, ce, hospitalizacion y otros servicios mï¿½dicos)", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table25.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table25.addCell(cell);

        cell = new PdfPCell(new Phrase("Total Horas Administrativas", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table25.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table25.addCell(cell);

        cell = new PdfPCell(table25);
        cell.setVerticalAlignment(Element.ALIGN_TOP);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(2);
        table1.addCell(cell);

        //FIN DE LA QUINTA FILA                
        ///COMIENZA LA SEXTA FILA            
        cell = new PdfPCell(new Phrase("Perfil Institucional", DATO_FONT));
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("Disponibilidad", DATO_FONT));
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1.addCell(cell);

        PdfPTable table26 = new PdfPTable(2);
        int[] fxwidt26 = {75, 25}; // percentage
        table26.setWidths(fxwidt26);
        table26.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("No. de camas de medicina Interna", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table26.addCell(cell);

        cell = new PdfPCell(new Phrase(Integer.toString(listaIndi.getBcg()), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table26.addCell(cell);

        cell = new PdfPCell(new Phrase("No. de camas de pediatria", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table26.addCell(cell);

        cell = new PdfPCell(new Phrase(Integer.toString(listaIndi.getCancer()), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table26.addCell(cell);

        cell = new PdfPCell(new Phrase("No. de camas de Ginecologia", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table26.addCell(cell);

        cell = new PdfPCell(new Phrase(Integer.toString(listaIndi.getCancero()), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table26.addCell(cell);

        cell = new PdfPCell(new Phrase("No. de camas de obstetricia", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table26.addCell(cell);

        cell = new PdfPCell(new Phrase(Integer.toString(listaIndi.getCardio()), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table26.addCell(cell);

        cell = new PdfPCell(new Phrase("No. de cunas en neonatologia", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table26.addCell(cell);

        cell = new PdfPCell(new Phrase(Integer.toString(listaIndi.getDepre()), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table26.addCell(cell);

        cell = new PdfPCell(new Phrase("No. de camas en cirugia", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table26.addCell(cell);

        cell = new PdfPCell(new Phrase(Integer.toString(listaIndi.getDesesti()), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table26.addCell(cell);

        cell = new PdfPCell(new Phrase("No. de camas de otros servicios", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table26.addCell(cell);

        cell = new PdfPCell(new Phrase(Integer.toString(listaIndi.getDiasi()), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table26.addCell(cell);

        cell = new PdfPCell(new Phrase("No. Total de camas (denominador)", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table26.addCell(cell);

        cell = new PdfPCell(new Phrase(Integer.toString(listaIndi.getAuto()), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table26.addCell(cell);

        cell = new PdfPCell(table26);
        cell.setVerticalAlignment(Element.ALIGN_TOP);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(2);
        table1.addCell(cell);
        //FIN DE LA SEXTA FILA         
        ///COMIENZA LA SEPTIMA FILA             
        cell = new PdfPCell(new Phrase("Indice de capital de trabajo, Contabilidad", DATO_FONT));
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("Sustentabilidad", DATO_FONT));
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1.addCell(cell);

        PdfPTable table27 = new PdfPTable(2);
        int[] fxwidt27 = {75, 25}; // percentage
        table27.setWidths(fxwidt27);
        table27.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("Activo corriente", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table27.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table27.addCell(cell);

        cell = new PdfPCell(new Phrase("Pasivo corriente (denominador)", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table27.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table27.addCell(cell);

        cell = new PdfPCell(table27);
        cell.setVerticalAlignment(Element.ALIGN_TOP);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(2);
        table1.addCell(cell);
        //FIN DE LA SEPTIMA FILA   
        ///COMIENZA LA OCTAVA FILA             
        cell = new PdfPCell(new Phrase("Margen de seguridad, Contabilidad", DATO_FONT));
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("Sustentabilidad", DATO_FONT));
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1.addCell(cell);

        PdfPTable table28 = new PdfPTable(2);
        int[] fxwidt28 = {75, 25}; // percentage
        table28.setWidths(fxwidt28);
        table28.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("Capital de trabajo", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table28.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table28.addCell(cell);

        cell = new PdfPCell(new Phrase("Pasivo corriente (denominador)", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table28.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table28.addCell(cell);

        cell = new PdfPCell(table28);
        cell.setVerticalAlignment(Element.ALIGN_TOP);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(2);
        table1.addCell(cell);
        //FIN DE LA OCTAVA FILA             
        ///COMIENZA LA NOVENA FILA             
        cell = new PdfPCell(new Phrase("Financiamiento del activo fijo con recursos propios Contabilidad", DATO_FONT));
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("Sustentabilidad", DATO_FONT));
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1.addCell(cell);

        PdfPTable table29 = new PdfPTable(2);
        int[] fxwidt29 = {75, 25}; // percentage
        table29.setWidths(fxwidt29);
        table29.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("(Patrimonio neto+pasivo no corriente", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table29.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table29.addCell(cell);

        cell = new PdfPCell(new Phrase("Activo no corriente", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table29.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table29.addCell(cell);

        cell = new PdfPCell(table29);
        cell.setVerticalAlignment(Element.ALIGN_TOP);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(2);
        table1.addCell(cell);
        //FIN DE LA NOVENA FILA             
        ///COMIENZA LA DECIMA FILA             
        cell = new PdfPCell(new Phrase("Dotacion de normas ", DATO_FONT));
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("Sustentabilidad", DATO_FONT));
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1.addCell(cell);

        PdfPTable table210 = new PdfPTable(2);
        int[] fxwidt210 = {75, 25}; // percentage
        table210.setWidths(fxwidt29);
        table210.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("No. de normas de acreditacion cumplidas ", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table210.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table210.addCell(cell);

        cell = new PdfPCell(new Phrase("Total de normas de acreditacion exigibles al nivel", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table210.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table210.addCell(cell);

        cell = new PdfPCell(table210);
        cell.setVerticalAlignment(Element.ALIGN_TOP);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(2);
        table1.addCell(cell);
        //FIN DE LA DECIMA FILA                        

        cell = new PdfPCell(table1);
        cell.setVerticalAlignment(Element.ALIGN_TOP);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(4);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("NOMBRE Y APELLIDO DEL COORDINADOR DEL CAIH  :  ", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setColspan(5);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("DIRECCION DEL ESTABLECIMIENTO DE SALUD  :  ", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setColspan(5);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("CORREO ELECTRONICO, FAX, TELEFONO  :  ", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setColspan(5);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("NOMBRE DE LOS MIEMBROS DEL CAIH  :  ", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setColspan(5);
        table.addCell(cell);

        document.add(table);

        // coloca el detalle de los datos
        //   table.setHeaderRows(1);
    }

    public static String format(Date dia, String formato) {
        if (dia == null) {
            return "";
        }
        SimpleDateFormat formatDate = new SimpleDateFormat(formato);
        return formatDate.format(dia);
    }
}
