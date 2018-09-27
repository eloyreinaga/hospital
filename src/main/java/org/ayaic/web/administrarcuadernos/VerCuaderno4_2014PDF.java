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

public class VerCuaderno4_2014PDF extends AbstractPdfView {

    private static final Font TITULO_FONT = new Font(Font.HELVETICA, 12, Font.BOLD, Color.black);
    private static final Font CABEZA_COLUMNA_FONT = new Font(Font.HELVETICA, 8, Font.ITALIC, Color.black);
    private static final Font DATO_FONT = new Font(Font.HELVETICA, 8, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_P = new Font(Font.HELVETICA, 6, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_BOLD = new Font(Font.HELVETICA, 10, Font.BOLD, Color.red);
    private static final int MARGIN = 32;

    protected void buildPdfMetadata(Map model, Document document, HttpServletRequest request) {
        Localidades dl = (Localidades) model.get("localidades");
        document.setPageSize(PageSize.LEGAL.rotate());
        document.setMargins(dl.getIzquierda(), dl.getDerecha(), dl.getSuperior(), dl.getInferior());
    }

    protected void buildPdfDocument(Map model, Document document, PdfWriter writer, HttpServletRequest req, HttpServletResponse resp) throws Exception {

        java.util.List listaCuaderno4 = (java.util.List) model.get("listaCuaderno4");
        java.util.List libro6 = (java.util.List) model.get("listalibro6");
        java.util.List cie = (java.util.List) model.get("listaCie");
        Clientes dato = (Clientes) model.get("dato");
        int[] sumas = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int[] sumas2 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int[] sumbs = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int[] sumbs2 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int[] sumcs = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int una, filas = 30;

        if (listaCuaderno4.size() == 0) {
            Paragraph p = new Paragraph("No existe Datos", new Font(Font.HELVETICA, 22, Font.BOLDITALIC, new Color(0, 0, 0)));
            document.add(p);
        }

        if (listaCuaderno4.size() % filas == 0) {
            una = 0;
        } else {
            una = 1;
        }

        for (int pag = 0; pag < listaCuaderno4.size() / filas + una; pag++) {
            Paragraph p;
            PdfPCell cell;

            int NumColumns = 52;
            PdfPTable table = new PdfPTable(NumColumns);
            int[] fwidths = {10, 14, 14, 20, 50, 16, 8, 9, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 7};
            table.setWidths(fwidths);
            table.setWidthPercentage(100);

            cell = new PdfPCell(new Phrase("Cuaderno No. 2                                        HOJA 1                                                             ATENCION INTEGRAL DEL NINIO(A) MENOR DE 5 añoS", TITULO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(NumColumns);
            table.addCell(cell);

            String sCampos[] = {"No", "FECHA", "Numero de\nHistoria\nClinica", "Numero Asegurado", "Apellidos y Nombre"};
            // coloca la cabecera de titulos
            for (int i = 0; i < 5; i++) {
                cell = new PdfPCell(new Phrase(sCampos[i], CABEZA_COLUMNA_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
            }
            
            addtitulofila30(table, ".", "  Edad  ", "Peso Kg.", "Talla cm.");
            
            cell = new PdfPCell(new Phrase("Ate BJA", CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            //cell.setRotation(90);
            table.addCell(cell);
            
            addtitulofila33(table, "Control Nuevo", "<1 año", "M", "F", "1<2 años", "M", "F", "2<5 años", "M", "F");
            addtitulofila33(table, "Control Repetido", "<1 año", "M", "F", "1<2 años", "M", "F", "2<5 años", "M", "F");

            int NumCol = 18;
            PdfPTable tablea = new PdfPTable(NumCol);
            int[] mfwidths = {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4};
            tablea.setWidths(mfwidths);
            tablea.setWidthPercentage(100);

            cell = new PdfPCell(new Phrase("PESO PARA LA TALLA (PESO/TALLA)", DATO_FONT));
            cell.setColspan(18);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablea.addCell(cell);

            addtitulofila33(tablea, "Obesidad(O)", "<1", "M", "F", "1<2", "M", "F", "2<5", "M", "F");
            addtitulofila33(tablea, "Sobrepeso(S)", "<1", "M", "F", "1<2", "M", "F", "2<5", "M", "F");
            addtitulofila33(tablea, "Normal(N)", "<1", "M", "F", "1<2", "M", "F", "2<5", "M", "F");

            cell = new PdfPCell(tablea);
            cell.setColspan(18);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            tablea = new PdfPTable(12);
            int[] mnfwidths = {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4};
            tablea.setWidths(mnfwidths);
            tablea.setWidthPercentage(100);

            cell = new PdfPCell(new Phrase("DESNUTRUCION AGUDA", DATO_FONT));
            cell.setColspan(12);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablea.addCell(cell);

            addtitulofila33(tablea, "Moderada(M)", "<1", "M", "F", "1<2", "M", "F", "2<5", "M", "F");
            addtitulofila33(tablea, "Grave(G)", "<1", "M", "F", "1<2", "M", "F", "2<5", "M", "F");

            cell = new PdfPCell(tablea);
            cell.setColspan(12);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Responsable", CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setRotation(90);
            table.addCell(cell);

            // coloca el detalle de loS datos          
            for (int i = pag * filas + 0; i < pag * filas + filas && i < listaCuaderno4.size(); i++) {

                Cuadernos datopac = (Cuadernos) listaCuaderno4.get(i);

                cell = new PdfPCell(new Phrase(Integer.toString(i + 1), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(format(datopac.getFecha(), "dd/MM/yy"), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(Integer.toString(datopac.getHcl()), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(datopac.getRegistro(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((datopac.getNombres().length() > 23) ? datopac.getNombres().substring(0, 23) : datopac.getNombres(), DATO_FONT));
                //cell = new PdfPCell(new Phrase(datopac.getNombres() , DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(Integer.toString(datopac.getEdad()) + "a" + Integer.toString(datopac.getMes()) + "m" + Integer.toString(datopac.getDia()) + "d", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(Double.toString(datopac.getPeso()), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(Double.toString(datopac.getTalla()), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);
                ///BONO JUANA AZURDUY
                cell = new PdfPCell(new Phrase((datopac.getSuma10() == 1 ) ? Integer.toString(datopac.getSuma10()) : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumas[0] += (datopac.getSuma10() == 1 ) ? 1 : 0;

//coloca nuevos y repetidos         
                String cuno = "", cdos = "", ctres = "";
                if (datopac.getEdad() < 1) {
                    if (datopac.getTipoconsulta().equals("N")) {
                        cell = new PdfPCell(new Phrase((datopac.getId_tipo_sexo() == 2 && datopac.getSeleccion() == 0) ? "1" : "", DATO_FONT));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table.addCell(cell);
                        sumas[1] += (datopac.getId_tipo_sexo() == 2 && datopac.getSeleccion() == 0) ? 1 : 0;

                        cell = new PdfPCell(new Phrase((datopac.getId_tipo_sexo() == 1 && datopac.getSeleccion() == 0) ? "1" : "", DATO_FONT));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table.addCell(cell);
                        sumas[2] += (datopac.getId_tipo_sexo() == 1 && datopac.getSeleccion() == 0) ? 1 : 0;

                        for (int k = 0; k < 10; k++) { //coloca filas vacias entes de colocar el dato
                            cell = new PdfPCell(new Phrase("", CABEZA_COLUMNA_FONT));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                        }
                    } else {
                        if (!datopac.getTipoconsulta().equals("0")) {
                            for (int k = 0; k < 6; k++) { //coloca filas vacias entes de colocar el dato
                                cell = new PdfPCell(new Phrase("", CABEZA_COLUMNA_FONT));
                                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                table.addCell(cell);
                            }

                            cell = new PdfPCell(new Phrase((datopac.getId_tipo_sexo() == 2 && datopac.getSeleccion() == 0) ? "1" : "", DATO_FONT));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                            sumas[7] += (datopac.getId_tipo_sexo() == 2 && datopac.getSeleccion() == 0) ? 1 : 0;

                            cell = new PdfPCell(new Phrase((datopac.getId_tipo_sexo() == 1 && datopac.getSeleccion() == 0) ? "1" : "", DATO_FONT));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                            sumas[8] += (datopac.getId_tipo_sexo() == 1 && datopac.getSeleccion() == 0) ? 1 : 0;

                            for (int k = 0; k < 4; k++) { //coloca filas vacias entes de colocar el dato
                                cell = new PdfPCell(new Phrase("", CABEZA_COLUMNA_FONT));
                                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                table.addCell(cell);
                            }
                        } else {
                            for (int k = 0; k < 12; k++) { //coloca filas vacias entes de colocar el dato
                                cell = new PdfPCell(new Phrase("", CABEZA_COLUMNA_FONT));
                                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                table.addCell(cell);
                            }
                        }///fin if del 0 
                    }
                    cuno = "1";
                } else if (datopac.getEdad() >= 1 && datopac.getEdad() < 2) {
                    if (datopac.getTipoconsulta().equals("N")) {
                        for (int k = 0; k < 2; k++) { //coloca filas vacias entes de colocar el dato
                            cell = new PdfPCell(new Phrase("", CABEZA_COLUMNA_FONT));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                        }

                        cell = new PdfPCell(new Phrase((datopac.getId_tipo_sexo() == 2 && datopac.getSeleccion() == 0) ? "1" : "", DATO_FONT));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table.addCell(cell);
                        sumas[3] += (datopac.getId_tipo_sexo() == 2 && datopac.getSeleccion() == 0) ? 1 : 0;

                        cell = new PdfPCell(new Phrase((datopac.getId_tipo_sexo() == 1 && datopac.getSeleccion() == 0) ? "1" : "", DATO_FONT));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table.addCell(cell);
                        sumas[4] += (datopac.getId_tipo_sexo() == 1 && datopac.getSeleccion() == 0) ? 1 : 0;

                        for (int k = 0; k < 8; k++) { //coloca filas vacias entes de colocar el dato
                            cell = new PdfPCell(new Phrase("", CABEZA_COLUMNA_FONT));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                        }
                    } else {
                        for (int k = 0; k < 8; k++) { //coloca filas vacias antes de colocar el dato
                            cell = new PdfPCell(new Phrase("", CABEZA_COLUMNA_FONT));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                        }
                        if (!datopac.getTipoconsulta().equals("0")) {
                            cell = new PdfPCell(new Phrase((datopac.getId_tipo_sexo() == 2 && datopac.getSeleccion() == 0) ? "1" : "", DATO_FONT));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                            sumas[9] += (datopac.getId_tipo_sexo() == 2 && datopac.getSeleccion() == 0) ? 1 : 0;

                            cell = new PdfPCell(new Phrase((datopac.getId_tipo_sexo() == 1 && datopac.getSeleccion() == 0) ? "1" : "", DATO_FONT));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                            sumas[10] += (datopac.getId_tipo_sexo() == 1 && datopac.getSeleccion() == 0) ? 1 : 0;

                            for (int k = 0; k < 2; k++) { //coloca filas vacias entes de colocar el dato
                                cell = new PdfPCell(new Phrase("", CABEZA_COLUMNA_FONT));
                                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                table.addCell(cell);
                            }
                        } else {
                            for (int k = 0; k < 4; k++) { //coloca filas vacias entes de colocar el dato
                                cell = new PdfPCell(new Phrase("", CABEZA_COLUMNA_FONT));
                                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                table.addCell(cell);
                            }
                        }///fin if del 0 
                    }
                    cdos = "1";
                } else if (datopac.getEdad() >= 2 && datopac.getEdad() < 5) {
                    if (datopac.getTipoconsulta().equals("N")) {
                        for (int k = 0; k < 4; k++) { //coloca filas vacias entes de colocar el dato
                            cell = new PdfPCell(new Phrase("", CABEZA_COLUMNA_FONT));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                        }

                        cell = new PdfPCell(new Phrase((datopac.getId_tipo_sexo() == 2 && datopac.getSeleccion() == 0) ? "1" : "", DATO_FONT));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table.addCell(cell);
                        sumas[5] += (datopac.getId_tipo_sexo() == 2 && datopac.getSeleccion() == 0) ? 1 : 0;

                        cell = new PdfPCell(new Phrase((datopac.getId_tipo_sexo() == 1 && datopac.getSeleccion() == 0) ? "1" : "", DATO_FONT));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table.addCell(cell);
                        sumas[6] += (datopac.getId_tipo_sexo() == 1 && datopac.getSeleccion() == 0) ? 1 : 0;

                        for (int k = 0; k < 6; k++) { //coloca filas vacias entes de colocar el dato
                            cell = new PdfPCell(new Phrase("", CABEZA_COLUMNA_FONT));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                        }
                    } else {
                        for (int k = 0; k < 10; k++) { //coloca filas vacias entes de colocar el dato
                            cell = new PdfPCell(new Phrase("", CABEZA_COLUMNA_FONT));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                        }
                        if (!datopac.getTipoconsulta().equals("0")) {
                            cell = new PdfPCell(new Phrase((datopac.getId_tipo_sexo() == 2 && datopac.getSeleccion() == 0) ? "1" : "", DATO_FONT));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                            sumas[11] += (datopac.getId_tipo_sexo() == 2 && datopac.getSeleccion() == 0) ? 1 : 0;

                            cell = new PdfPCell(new Phrase((datopac.getId_tipo_sexo() == 1 && datopac.getSeleccion() == 0) ? "l" : "", DATO_FONT));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                            sumas[12] += (datopac.getId_tipo_sexo() == 1 && datopac.getSeleccion() == 0) ? 1 : 0;
                        } else {
                            for (int k = 0; k < 2; k++) { //coloca filas vacias entes de colocar el dato
                                cell = new PdfPCell(new Phrase("", CABEZA_COLUMNA_FONT));
                                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                table.addCell(cell);
                            }
                        } ///fin if del 0    
                    }
                    ctres = "1";
                }
                //fin nuevos y repetidos              
//coloca datos en peso/talla 
                if ("O".equals(datopac.getDglobal())) {//datos en la parte de Obesidad
                    if (datopac.getEdad() < 1) {
                        if (datopac.getId_cuaderno() == 1 || datopac.getId_cuaderno() == 3) {
                            cell = new PdfPCell(new Phrase((datopac.getId_tipo_sexo() == 2) ? "1" : "", DATO_FONT_BOLD));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                            sumas[13] += (datopac.getId_tipo_sexo() == 2) ? 1 : 0;
                            sumas2[13] += (datopac.getId_tipo_sexo() == 2) ? 1 : 0;

                            cell = new PdfPCell(new Phrase((datopac.getId_tipo_sexo() == 1) ? "l" : "", DATO_FONT_BOLD));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                            sumas[14] += (datopac.getId_tipo_sexo() == 1) ? 1 : 0;
                            sumas2[14] += (datopac.getId_tipo_sexo() == 1) ? 1 : 0;
                        } else {
                            cell = new PdfPCell(new Phrase((datopac.getId_tipo_sexo() == 2) ? "1" : "", DATO_FONT));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                            sumas[13] += (datopac.getId_tipo_sexo() == 2) ? 1 : 0;

                            cell = new PdfPCell(new Phrase((datopac.getId_tipo_sexo() == 1) ? "l" : "", DATO_FONT));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                            sumas[14] += (datopac.getId_tipo_sexo() == 1) ? 1 : 0;
                        }

                        for (int k = 0; k < 4; k++) { //coloca filas vacias entes de colocar el dato
                            cell = new PdfPCell(new Phrase("", CABEZA_COLUMNA_FONT));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                        }

                    } else if (datopac.getEdad() >= 1 && datopac.getEdad() < 2) {
                        for (int k = 0; k < 2; k++) { //coloca filas vacias entes de colocar el dato
                            cell = new PdfPCell(new Phrase("", CABEZA_COLUMNA_FONT));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                        }
                        if (datopac.getId_cuaderno() == 1 || datopac.getId_cuaderno() == 3) {
                            cell = new PdfPCell(new Phrase((datopac.getId_tipo_sexo() == 2) ? "1" : "", DATO_FONT_BOLD));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                            sumas[15] += (datopac.getId_tipo_sexo() == 2) ? 1 : 0;
                            sumas2[15] += (datopac.getId_tipo_sexo() == 2) ? 1 : 0;

                            cell = new PdfPCell(new Phrase((datopac.getId_tipo_sexo() == 1) ? "l" : "", DATO_FONT_BOLD));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                            sumas[16] += (datopac.getId_tipo_sexo() == 1) ? 1 : 0;
                            sumas2[16] += (datopac.getId_tipo_sexo() == 1) ? 1 : 0;
                        } else {
                            cell = new PdfPCell(new Phrase((datopac.getId_tipo_sexo() == 2) ? "1" : "", DATO_FONT));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                            sumas[15] += (datopac.getId_tipo_sexo() == 2) ? 1 : 0;

                            cell = new PdfPCell(new Phrase((datopac.getId_tipo_sexo() == 1) ? "l" : "", DATO_FONT));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                            sumas[16] += (datopac.getId_tipo_sexo() == 1) ? 1 : 0;
                        }

                        for (int k = 0; k < 2; k++) { //coloca filas vacias entes de colocar el dato
                            cell = new PdfPCell(new Phrase("", CABEZA_COLUMNA_FONT));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                        }
                    } else if (datopac.getEdad() >= 2 && datopac.getEdad() < 5) {
                        for (int k = 0; k < 4; k++) { //coloca filas vacias entes de colocar el dato
                            cell = new PdfPCell(new Phrase("", CABEZA_COLUMNA_FONT));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                        }
                        if (datopac.getId_cuaderno() == 1 || datopac.getId_cuaderno() == 3) {
                            cell = new PdfPCell(new Phrase((datopac.getId_tipo_sexo() == 2) ? "1" : "", DATO_FONT_BOLD));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                            sumas[17] += (datopac.getId_tipo_sexo() == 2) ? 1 : 0;
                            sumas2[17] += (datopac.getId_tipo_sexo() == 2) ? 1 : 0;

                            cell = new PdfPCell(new Phrase((datopac.getId_tipo_sexo() == 1) ? "l" : "", DATO_FONT_BOLD));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                            sumas[18] += (datopac.getId_tipo_sexo() == 1) ? 1 : 0;
                            sumas2[18] += (datopac.getId_tipo_sexo() == 1) ? 1 : 0;
                        } else {
                            cell = new PdfPCell(new Phrase((datopac.getId_tipo_sexo() == 2) ? "1" : "", DATO_FONT));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                            sumas[17] += (datopac.getId_tipo_sexo() == 2) ? 1 : 0;

                            cell = new PdfPCell(new Phrase((datopac.getId_tipo_sexo() == 1) ? "l" : "", DATO_FONT));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                            sumas[18] += (datopac.getId_tipo_sexo() == 1) ? 1 : 0;
                        }
                    }
                } else {
                    for (int k = 0; k < 6; k++) { //coloca filas vacias entes de colocar el dato
                        cell = new PdfPCell(new Phrase("", CABEZA_COLUMNA_FONT));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table.addCell(cell);
                    }
                } // termina de colocar datos en Obesidad

                if ("S".equals(datopac.getDglobal())) {
                    if (datopac.getEdad() < 1) {
                        if (datopac.getId_cuaderno() == 1 || datopac.getId_cuaderno() == 3) {
                            cell = new PdfPCell(new Phrase((datopac.getId_tipo_sexo() == 2) ? "1" : "", DATO_FONT_BOLD));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                            sumas[19] += (datopac.getId_tipo_sexo() == 2) ? 1 : 0;
                            sumas2[19] += (datopac.getId_tipo_sexo() == 2) ? 1 : 0;

                            cell = new PdfPCell(new Phrase((datopac.getId_tipo_sexo() == 1) ? "l" : "", DATO_FONT_BOLD));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                            sumas[20] += (datopac.getId_tipo_sexo() == 1) ? 1 : 0;
                            sumas2[20] += (datopac.getId_tipo_sexo() == 1) ? 1 : 0;
                        } else {
                            cell = new PdfPCell(new Phrase((datopac.getId_tipo_sexo() == 2) ? "1" : "", DATO_FONT));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                            sumas[19] += (datopac.getId_tipo_sexo() == 2) ? 1 : 0;

                            cell = new PdfPCell(new Phrase((datopac.getId_tipo_sexo() == 1) ? "l" : "", DATO_FONT));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                            sumas[20] += (datopac.getId_tipo_sexo() == 1) ? 1 : 0;
                        }

                        for (int k = 0; k < 4; k++) { //coloca filas vacias entes de colocar el dato
                            cell = new PdfPCell(new Phrase("", CABEZA_COLUMNA_FONT));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                        }

                    } else if (datopac.getEdad() >= 1 && datopac.getEdad() < 2) {
                        for (int k = 0; k < 2; k++) { //coloca filas vacias entes de colocar el dato
                            cell = new PdfPCell(new Phrase("", CABEZA_COLUMNA_FONT));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                        }
                        if (datopac.getId_cuaderno() == 1 || datopac.getId_cuaderno() == 3) {
                            cell = new PdfPCell(new Phrase((datopac.getId_tipo_sexo() == 2) ? "1" : "", DATO_FONT_BOLD));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                            sumas[21] += (datopac.getId_tipo_sexo() == 2) ? 1 : 0;
                            sumas2[21] += (datopac.getId_tipo_sexo() == 2) ? 1 : 0;

                            cell = new PdfPCell(new Phrase((datopac.getId_tipo_sexo() == 1) ? "l" : "", DATO_FONT_BOLD));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                            sumas[22] += (datopac.getId_tipo_sexo() == 1) ? 1 : 0;
                            sumas2[22] += (datopac.getId_tipo_sexo() == 1) ? 1 : 0;
                        } else {
                            cell = new PdfPCell(new Phrase((datopac.getId_tipo_sexo() == 2) ? "1" : "", DATO_FONT));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                            sumas[21] += (datopac.getId_tipo_sexo() == 2) ? 1 : 0;

                            cell = new PdfPCell(new Phrase((datopac.getId_tipo_sexo() == 1) ? "l" : "", DATO_FONT));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                            sumas[22] += (datopac.getId_tipo_sexo() == 1) ? 1 : 0;
                        }

                        for (int k = 0; k < 2; k++) { //coloca filas vacias entes de colocar el dato
                            cell = new PdfPCell(new Phrase("", CABEZA_COLUMNA_FONT));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                        }
                    } else if (datopac.getEdad() >= 2 && datopac.getEdad() < 5) {
                        for (int k = 0; k < 4; k++) { //coloca filas vacias entes de colocar el dato
                            cell = new PdfPCell(new Phrase("", CABEZA_COLUMNA_FONT));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                        }
                        if (datopac.getId_cuaderno() == 1 || datopac.getId_cuaderno() == 3) {
                            cell = new PdfPCell(new Phrase((datopac.getId_tipo_sexo() == 2) ? "1" : "", DATO_FONT_BOLD));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                            sumas[23] += (datopac.getId_tipo_sexo() == 2) ? 1 : 0;
                            sumas2[23] += (datopac.getId_tipo_sexo() == 2) ? 1 : 0;

                            cell = new PdfPCell(new Phrase((datopac.getId_tipo_sexo() == 1) ? "l" : "", DATO_FONT_BOLD));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                            sumas[24] += (datopac.getId_tipo_sexo() == 1) ? 1 : 0;
                            sumas2[24] += (datopac.getId_tipo_sexo() == 1) ? 1 : 0;
                        } else {
                            cell = new PdfPCell(new Phrase((datopac.getId_tipo_sexo() == 2) ? "1" : "", DATO_FONT));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                            sumas[23] += (datopac.getId_tipo_sexo() == 2) ? 1 : 0;

                            cell = new PdfPCell(new Phrase((datopac.getId_tipo_sexo() == 1) ? "l" : "", DATO_FONT));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                            sumas[24] += (datopac.getId_tipo_sexo() == 1) ? 1 : 0;
                        }
                    }
                } else {
                    for (int k = 0; k < 6; k++) { //coloca filas vacias entes de colocar el dato
                        cell = new PdfPCell(new Phrase("", CABEZA_COLUMNA_FONT));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table.addCell(cell);
                    }
                }

//datos para desnutricion NORMAL               
                if ("N".equals(datopac.getDglobal())) {
                    if (datopac.getEdad() < 1) {
                        if (datopac.getId_cuaderno() == 1 || datopac.getId_cuaderno() == 3) {
                            cell = new PdfPCell(new Phrase((datopac.getId_tipo_sexo() == 2) ? "1" : "", DATO_FONT_BOLD));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                            sumas[25] += (datopac.getId_tipo_sexo() == 2) ? 1 : 0;
                            sumas2[25] += (datopac.getId_tipo_sexo() == 2) ? 1 : 0;

                            cell = new PdfPCell(new Phrase((datopac.getId_tipo_sexo() == 1) ? "l" : "", DATO_FONT_BOLD));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                            sumas[26] += (datopac.getId_tipo_sexo() == 1) ? 1 : 0;
                            sumas2[26] += (datopac.getId_tipo_sexo() == 1) ? 1 : 0;
                        } else {
                            cell = new PdfPCell(new Phrase((datopac.getId_tipo_sexo() == 2) ? "1" : "", DATO_FONT));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                            sumas[25] += (datopac.getId_tipo_sexo() == 2) ? 1 : 0;

                            cell = new PdfPCell(new Phrase((datopac.getId_tipo_sexo() == 1) ? "l" : "", DATO_FONT));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                            sumas[26] += (datopac.getId_tipo_sexo() == 1) ? 1 : 0;
                        }

                        for (int k = 0; k < 4; k++) { //coloca filas vacias entes de colocar el dato
                            cell = new PdfPCell(new Phrase("", CABEZA_COLUMNA_FONT));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                        }

                    } else if (datopac.getEdad() >= 1 && datopac.getEdad() < 2) {
                        for (int k = 0; k < 2; k++) { //coloca filas vacias entes de colocar el dato
                            cell = new PdfPCell(new Phrase("", CABEZA_COLUMNA_FONT));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                        }
                        if (datopac.getId_cuaderno() == 1 || datopac.getId_cuaderno() == 3) {
                            cell = new PdfPCell(new Phrase((datopac.getId_tipo_sexo() == 2) ? "1" : "", DATO_FONT_BOLD));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                            sumas[27] += (datopac.getId_tipo_sexo() == 2) ? 1 : 0;
                            sumas2[27] += (datopac.getId_tipo_sexo() == 2) ? 1 : 0;

                            cell = new PdfPCell(new Phrase((datopac.getId_tipo_sexo() == 1) ? "l" : "", DATO_FONT_BOLD));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                            sumas[28] += (datopac.getId_tipo_sexo() == 1) ? 1 : 0;
                            sumas2[28] += (datopac.getId_tipo_sexo() == 1) ? 1 : 0;
                        } else {
                            cell = new PdfPCell(new Phrase((datopac.getId_tipo_sexo() == 2) ? "1" : "", DATO_FONT));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                            sumas[27] += (datopac.getId_tipo_sexo() == 2) ? 1 : 0;

                            cell = new PdfPCell(new Phrase((datopac.getId_tipo_sexo() == 1) ? "l" : "", DATO_FONT));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                            sumas[28] += (datopac.getId_tipo_sexo() == 1) ? 1 : 0;
                        }

                        for (int k = 0; k < 2; k++) { //coloca filas vacias entes de colocar el dato
                            cell = new PdfPCell(new Phrase("", CABEZA_COLUMNA_FONT));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                        }
                    } else if (datopac.getEdad() >= 2 && datopac.getEdad() < 5) {
                        for (int k = 0; k < 4; k++) { //coloca filas vacias entes de colocar el dato
                            cell = new PdfPCell(new Phrase("", CABEZA_COLUMNA_FONT));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                        }
                        if (datopac.getId_cuaderno() == 1 || datopac.getId_cuaderno() == 3) {
                            cell = new PdfPCell(new Phrase((datopac.getId_tipo_sexo() == 2) ? "1" : "", DATO_FONT_BOLD));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                            sumas[29] += (datopac.getId_tipo_sexo() == 2) ? 1 : 0;
                            sumas2[29] += (datopac.getId_tipo_sexo() == 2) ? 1 : 0;

                            cell = new PdfPCell(new Phrase((datopac.getId_tipo_sexo() == 1) ? "l" : "", DATO_FONT_BOLD));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                            sumas[30] += (datopac.getId_tipo_sexo() == 1) ? 1 : 0;
                            sumas2[30] += (datopac.getId_tipo_sexo() == 1) ? 1 : 0;
                        } else {
                            cell = new PdfPCell(new Phrase((datopac.getId_tipo_sexo() == 2) ? "1" : "", DATO_FONT));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                            sumas[29] += (datopac.getId_tipo_sexo() == 2) ? 1 : 0;

                            cell = new PdfPCell(new Phrase((datopac.getId_tipo_sexo() == 1) ? "l" : "", DATO_FONT));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                            sumas[30] += (datopac.getId_tipo_sexo() == 1) ? 1 : 0;
                        }
                    }
                } else {
                    for (int k = 0; k < 6; k++) { //coloca filas vacias entes de colocar el dato
                        cell = new PdfPCell(new Phrase("", CABEZA_COLUMNA_FONT));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table.addCell(cell);
                    }
                }

                if ("M".equals(datopac.getDglobal())) {
                    if (datopac.getEdad() < 1) {
                        if (datopac.getId_cuaderno() == 1 || datopac.getId_cuaderno() == 3) {
                            cell = new PdfPCell(new Phrase((datopac.getId_tipo_sexo() == 2) ? "1" : "", DATO_FONT_BOLD));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                            sumas[31] += (datopac.getId_tipo_sexo() == 2) ? 1 : 0;
                            sumas2[31] += (datopac.getId_tipo_sexo() == 2) ? 1 : 0;

                            cell = new PdfPCell(new Phrase((datopac.getId_tipo_sexo() == 1) ? "l" : "", DATO_FONT_BOLD));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                            sumas[32] += (datopac.getId_tipo_sexo() == 1) ? 1 : 0;
                            sumas2[32] += (datopac.getId_tipo_sexo() == 1) ? 1 : 0;
                        } else {
                            cell = new PdfPCell(new Phrase((datopac.getId_tipo_sexo() == 2) ? "1" : "", DATO_FONT));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                            sumas[31] += (datopac.getId_tipo_sexo() == 2) ? 1 : 0;

                            cell = new PdfPCell(new Phrase((datopac.getId_tipo_sexo() == 1) ? "l" : "", DATO_FONT));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                            sumas[32] += (datopac.getId_tipo_sexo() == 1) ? 1 : 0;
                        }

                        for (int k = 0; k < 4; k++) { //coloca filas vacias entes de colocar el dato
                            cell = new PdfPCell(new Phrase("", CABEZA_COLUMNA_FONT));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                        }

                    } else if (datopac.getEdad() >= 1 && datopac.getEdad() < 2) {
                        for (int k = 0; k < 2; k++) { //coloca filas vacias entes de colocar el dato
                            cell = new PdfPCell(new Phrase("", CABEZA_COLUMNA_FONT));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                        }
                        if (datopac.getId_cuaderno() == 1 || datopac.getId_cuaderno() == 3) {
                            cell = new PdfPCell(new Phrase((datopac.getId_tipo_sexo() == 2) ? "1" : "", DATO_FONT_BOLD));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                            sumas[33] += (datopac.getId_tipo_sexo() == 2) ? 1 : 0;
                            sumas2[33] += (datopac.getId_tipo_sexo() == 2) ? 1 : 0;

                            cell = new PdfPCell(new Phrase((datopac.getId_tipo_sexo() == 1) ? "l" : "", DATO_FONT_BOLD));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                            sumas[34] += (datopac.getId_tipo_sexo() == 1) ? 1 : 0;
                            sumas2[34] += (datopac.getId_tipo_sexo() == 1) ? 1 : 0;
                        } else {

                            cell = new PdfPCell(new Phrase((datopac.getId_tipo_sexo() == 2) ? "1" : "", DATO_FONT));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                            sumas[33] += (datopac.getId_tipo_sexo() == 2) ? 1 : 0;

                            cell = new PdfPCell(new Phrase((datopac.getId_tipo_sexo() == 1) ? "l" : "", DATO_FONT));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                            sumas[34] += (datopac.getId_tipo_sexo() == 1) ? 1 : 0;
                        }

                        for (int k = 0; k < 2; k++) { //coloca filas vacias entes de colocar el dato
                            cell = new PdfPCell(new Phrase("", CABEZA_COLUMNA_FONT));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                        }
                    } else if (datopac.getEdad() >= 2 && datopac.getEdad() < 5) {
                        for (int k = 0; k < 4; k++) { //coloca filas vacias entes de colocar el dato
                            cell = new PdfPCell(new Phrase("", CABEZA_COLUMNA_FONT));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                        }
                        if (datopac.getId_cuaderno() == 1 || datopac.getId_cuaderno() == 3) {

                            cell = new PdfPCell(new Phrase((datopac.getId_tipo_sexo() == 2) ? "1" : "", DATO_FONT_BOLD));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                            sumas[35] += (datopac.getId_tipo_sexo() == 2) ? 1 : 0;
                            sumas2[35] += (datopac.getId_tipo_sexo() == 2) ? 1 : 0;

                            cell = new PdfPCell(new Phrase((datopac.getId_tipo_sexo() == 1) ? "l" : "", DATO_FONT_BOLD));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                            sumas[36] += (datopac.getId_tipo_sexo() == 1) ? 1 : 0;
                            sumas2[36] += (datopac.getId_tipo_sexo() == 1) ? 1 : 0;
                        } else {
                            cell = new PdfPCell(new Phrase((datopac.getId_tipo_sexo() == 2) ? "1" : "", DATO_FONT));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                            sumas[35] += (datopac.getId_tipo_sexo() == 2) ? 1 : 0;

                            cell = new PdfPCell(new Phrase((datopac.getId_tipo_sexo() == 1) ? "l" : "", DATO_FONT));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                            sumas[36] += (datopac.getId_tipo_sexo() == 1) ? 1 : 0;
                        }
                    }
                } else {
                    for (int k = 0; k < 6; k++) { //coloca filas vacias entes de colocar el dato
                        cell = new PdfPCell(new Phrase("", CABEZA_COLUMNA_FONT));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table.addCell(cell);
                    }
                }

                if ("G".equals(datopac.getDglobal())) {
                    if (datopac.getEdad() < 1) {
                        if (datopac.getId_cuaderno() == 1 || datopac.getId_cuaderno() == 3) {
                            cell = new PdfPCell(new Phrase((datopac.getId_tipo_sexo() == 2) ? "1" : "", DATO_FONT_BOLD));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                            sumas[37] += (datopac.getId_tipo_sexo() == 2) ? 1 : 0;
                            sumas2[37] += (datopac.getId_tipo_sexo() == 2) ? 1 : 0;

                            cell = new PdfPCell(new Phrase((datopac.getId_tipo_sexo() == 1) ? "l" : "", DATO_FONT_BOLD));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                            sumas[38] += (datopac.getId_tipo_sexo() == 1) ? 1 : 0;
                            sumas2[38] += (datopac.getId_tipo_sexo() == 1) ? 1 : 0;
                        } else {
                            cell = new PdfPCell(new Phrase((datopac.getId_tipo_sexo() == 2) ? "1" : "", DATO_FONT));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                            sumas[37] += (datopac.getId_tipo_sexo() == 2) ? 1 : 0;

                            cell = new PdfPCell(new Phrase((datopac.getId_tipo_sexo() == 1) ? "l" : "", DATO_FONT));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                            sumas[38] += (datopac.getId_tipo_sexo() == 1) ? 1 : 0;
                        }

                        for (int k = 0; k < 4; k++) { //coloca filas vacias entes de colocar el dato
                            cell = new PdfPCell(new Phrase("", CABEZA_COLUMNA_FONT));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                        }

                    } else if (datopac.getEdad() >= 1 && datopac.getEdad() < 2) {
                        for (int k = 0; k < 2; k++) { //coloca filas vacias entes de colocar el dato
                            cell = new PdfPCell(new Phrase("", CABEZA_COLUMNA_FONT));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                        }
                        if (datopac.getId_cuaderno() == 1 || datopac.getId_cuaderno() == 3) {
                            cell = new PdfPCell(new Phrase((datopac.getId_tipo_sexo() == 2) ? "1" : "", DATO_FONT_BOLD));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                            sumas[39] += (datopac.getId_tipo_sexo() == 2) ? 1 : 0;
                            sumas2[39] += (datopac.getId_tipo_sexo() == 2) ? 1 : 0;

                            cell = new PdfPCell(new Phrase((datopac.getId_tipo_sexo() == 1) ? "l" : "", DATO_FONT_BOLD));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                            sumas[40] += (datopac.getId_tipo_sexo() == 1) ? 1 : 0;
                            sumas2[40] += (datopac.getId_tipo_sexo() == 1) ? 1 : 0;
                        } else {
                            cell = new PdfPCell(new Phrase((datopac.getId_tipo_sexo() == 2) ? "1" : "", DATO_FONT));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                            sumas[39] += (datopac.getId_tipo_sexo() == 2) ? 1 : 0;

                            cell = new PdfPCell(new Phrase((datopac.getId_tipo_sexo() == 1) ? "l" : "", DATO_FONT));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                            sumas[40] += (datopac.getId_tipo_sexo() == 1) ? 1 : 0;
                        }

                        for (int k = 0; k < 2; k++) { //coloca filas vacias entes de colocar el dato
                            cell = new PdfPCell(new Phrase("", CABEZA_COLUMNA_FONT));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                        }
                    } else if (datopac.getEdad() >= 2 && datopac.getEdad() < 5) {
                        for (int k = 0; k < 4; k++) { //coloca filas vacias entes de colocar el dato
                            cell = new PdfPCell(new Phrase("", CABEZA_COLUMNA_FONT));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                        }
                        if (datopac.getId_cuaderno() == 1 || datopac.getId_cuaderno() == 3) {
                            cell = new PdfPCell(new Phrase((datopac.getId_tipo_sexo() == 2) ? "1" : "", DATO_FONT_BOLD));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                            sumas[41] += (datopac.getId_tipo_sexo() == 2) ? 1 : 0;
                            sumas2[41] += (datopac.getId_tipo_sexo() == 2) ? 1 : 0;

                            cell = new PdfPCell(new Phrase((datopac.getId_tipo_sexo() == 1) ? "l" : "", DATO_FONT_BOLD));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                            sumas[42] += (datopac.getId_tipo_sexo() == 1) ? 1 : 0;
                            sumas2[42] += (datopac.getId_tipo_sexo() == 1) ? 1 : 0;
                        } else {
                            cell = new PdfPCell(new Phrase((datopac.getId_tipo_sexo() == 2) ? "1" : "", DATO_FONT));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                            sumas[41] += (datopac.getId_tipo_sexo() == 2) ? 1 : 0;

                            cell = new PdfPCell(new Phrase((datopac.getId_tipo_sexo() == 1) ? "l" : "", DATO_FONT));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                            sumas[42] += (datopac.getId_tipo_sexo() == 1) ? 1 : 0;
                        }
                    }
                } else {
                    for (int k = 0; k < 6; k++) { //coloca filas vacias entes de colocar el dato
                        cell = new PdfPCell(new Phrase("", CABEZA_COLUMNA_FONT));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table.addCell(cell);
                    }
                }

                cell = new PdfPCell(new Phrase(Integer.toString(datopac.getId_persona()), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

            }
            for (int i = listaCuaderno4.size(); i < pag * filas + filas; i++) {
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
            cell.setColspan(8);
            table.addCell(cell);
            for (int j = 0; j < 48; j++) {
                cell = new PdfPCell(new Phrase(Integer.toString(sumas[j]), DATO_FONT));
                if (j > 12) {
                    cell = new PdfPCell(new Phrase(sumas[j] + "\n." + sumas2[j], DATO_FONT));
                }
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(cell);
            }

            for (int k = 0; k < 36; k++) { //coloca filas vacias entes de colocar totales
                cell = new PdfPCell(new Phrase("", CABEZA_COLUMNA_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
            }

            document.add(table);

            //comienza nueva hoja 2 
            document.newPage();

            int NumColumns2 = 50;
            table = new PdfPTable(NumColumns2);
            int[] fwidthx = {10, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5};
            table.setWidths(fwidthx);
            table.setWidthPercentage(100);

            cell = new PdfPCell(new Phrase("Cuaderno No. 2                                                         HOJA 2                                                                               ATENCION INTEGRAL DEL NINIO(A) MENOR DE 5 añoS", TITULO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(NumColumns);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("No", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            int NumColu = 12;
            tablea = new PdfPTable(NumColu);
            int[] gwidths = {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4};
            tablea.setWidths(gwidths);
            tablea.setWidthPercentage(100);

            cell = new PdfPCell(new Phrase("TALLA PARA LA EDAD (T/E)", DATO_FONT));
            cell.setColspan(12);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablea.addCell(cell);

            addtitulofila33(tablea, "Talla normal (TN)", "<1", "M", "F", "1<2", "M", "F", "2<5", "M", "F");
            addtitulofila33(tablea, "Talla baja (TB)", "<1", "M", "F", "1<2", "M", "F", "2<5", "M", "F");

            cell = new PdfPCell(tablea);
            cell.setColspan(12);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            int NumColum = 11;
            tablea = new PdfPTable(NumColum);
            int[] xwidths = {6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6};
            tablea.setWidths(xwidths);
            tablea.setWidthPercentage(100);

            cell = new PdfPCell(new Phrase("SUPLEMENTACION CON MICRONUTRIENTES", DATO_FONT));
            cell.setColspan(11);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablea.addCell(cell);

            addtitulofila42(tablea, "Chispitas", "< 1a", "1a", "2a", "3f", "3a <5", "4f");
            addtitulofila52(tablea, "D. de vitamina 'A'", "< 1a", "1a", "1ra", "2da", "2a <5a", "1ra", "2da");
            addtitulofila20(tablea, "D. cpl. Zinc", "< 1a", "1 a");

            cell = new PdfPCell(tablea);
            cell.setColspan(11);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            addtitulofila20(table, "Dosis Mebendazol 1<5 años", "1ra.", "2da.");

            cell = new PdfPCell(new Phrase("Conj. Lact. Mat", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Lact. Mat.a Excl.", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Cnj alm cpl 6m-2a", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Nutribebe <1a", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Nutribebe 1a", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            addtitulofila40(table, "Desarrollo y Estimulacion del Niño", "MG", "MF", "AL", "PS");

            cell = new PdfPCell(new Phrase("ATLU", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Zinc > 2 años", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Ac Folico", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            int NumColumcc = 12;
            PdfPTable tablea2 = new PdfPTable(NumColumcc);
            int[] xwidthsc = {6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6};
            tablea2.setWidths(xwidthsc);
            tablea2.setWidthPercentage(100);

            cell = new PdfPCell(new Phrase("Consulta Externa", DATO_FONT));
            cell.setColspan(12);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablea2.addCell(cell);

            addtitulofila40(tablea2, "Menores 6 meses\n Nuevo|Repetid", "M", "F", "M", "F");
            addtitulofila40(tablea2, "6 a 12 meses\n Nuevo|Repetid", "M", "F", "M", "F");
            addtitulofila40(tablea2, "1 - 4 años\n Nuevo|Repetid", "M", "F", "M", "F");

            cell = new PdfPCell(tablea2);
            cell.setColspan(12);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            for (int i = pag * filas + 0; i < pag * filas + filas && i < listaCuaderno4.size(); i++) {

                Cuadernos datopac = (Cuadernos) listaCuaderno4.get(i);

                cell = new PdfPCell(new Phrase(Integer.toString(i + 1), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                //coloca los datos para talla/edad              
                if ("N".equals(datopac.getDcronica())) {
                    if (datopac.getEdad() < 1) {
                        if (datopac.getId_cuaderno() == 3 || datopac.getId_cuaderno() == 2) {
                            cell = new PdfPCell(new Phrase((datopac.getId_tipo_sexo() == 2) ? "1" : "", DATO_FONT_BOLD));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                            sumbs[0] += (datopac.getId_tipo_sexo() == 2) ? 1 : 0;
                            sumbs2[0] += (datopac.getId_tipo_sexo() == 2) ? 1 : 0;

                            cell = new PdfPCell(new Phrase((datopac.getId_tipo_sexo() == 1) ? "l" : "", DATO_FONT_BOLD));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                            sumbs[1] += (datopac.getId_tipo_sexo() == 1) ? 1 : 0;
                            sumbs2[1] += (datopac.getId_tipo_sexo() == 1) ? 1 : 0;
                        } else {
                            cell = new PdfPCell(new Phrase((datopac.getId_tipo_sexo() == 2) ? "1" : "", DATO_FONT));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                            sumbs[0] += (datopac.getId_tipo_sexo() == 2) ? 1 : 0;

                            cell = new PdfPCell(new Phrase((datopac.getId_tipo_sexo() == 1) ? "l" : "", DATO_FONT));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                            sumbs[1] += (datopac.getId_tipo_sexo() == 1) ? 1 : 0;
                        }

                        for (int k = 0; k < 4; k++) { //coloca filas vacias entes de colocar el dato
                            cell = new PdfPCell(new Phrase("", CABEZA_COLUMNA_FONT));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                        }
                    } else if (datopac.getEdad() >= 1 && datopac.getEdad() < 2) {
                        for (int k = 0; k < 2; k++) { //coloca filas vacias entes de colocar el dato
                            cell = new PdfPCell(new Phrase("", CABEZA_COLUMNA_FONT));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                        }
                        if (datopac.getId_cuaderno() == 3 || datopac.getId_cuaderno() == 2) {
                            cell = new PdfPCell(new Phrase((datopac.getId_tipo_sexo() == 2) ? "1" : "", DATO_FONT_BOLD));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                            sumbs[2] += (datopac.getId_tipo_sexo() == 2) ? 1 : 0;
                            sumbs2[2] += (datopac.getId_tipo_sexo() == 2) ? 1 : 0;

                            cell = new PdfPCell(new Phrase((datopac.getId_tipo_sexo() == 1) ? "l" : "", DATO_FONT_BOLD));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                            sumbs[3] += (datopac.getId_tipo_sexo() == 1) ? 1 : 0;
                            sumbs2[3] += (datopac.getId_tipo_sexo() == 1) ? 1 : 0;
                        } else {
                            cell = new PdfPCell(new Phrase((datopac.getId_tipo_sexo() == 2) ? "1" : "", DATO_FONT));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                            sumbs[2] += (datopac.getId_tipo_sexo() == 2) ? 1 : 0;

                            cell = new PdfPCell(new Phrase((datopac.getId_tipo_sexo() == 1) ? "l" : "", DATO_FONT));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                            sumbs[3] += (datopac.getId_tipo_sexo() == 1) ? 1 : 0;
                        }

                        for (int k = 0; k < 2; k++) { //coloca filas vacias entes de colocar el dato
                            cell = new PdfPCell(new Phrase("", CABEZA_COLUMNA_FONT));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                        }
                    } else if (datopac.getEdad() >= 2 && datopac.getEdad() < 5) {
                        for (int k = 0; k < 4; k++) { //coloca filas vacias entes de colocar el dato
                            cell = new PdfPCell(new Phrase("", CABEZA_COLUMNA_FONT));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                        }
                        if (datopac.getId_cuaderno() == 3 || datopac.getId_cuaderno() == 2) {
                            cell = new PdfPCell(new Phrase((datopac.getId_tipo_sexo() == 2) ? "1" : "", DATO_FONT_BOLD));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                            sumbs[4] += (datopac.getId_tipo_sexo() == 2) ? 1 : 0;
                            sumbs2[4] += (datopac.getId_tipo_sexo() == 2) ? 1 : 0;

                            cell = new PdfPCell(new Phrase((datopac.getId_tipo_sexo() == 1) ? "l" : "", DATO_FONT_BOLD));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                            sumbs[5] += (datopac.getId_tipo_sexo() == 1) ? 1 : 0;
                            sumbs2[5] += (datopac.getId_tipo_sexo() == 1) ? 1 : 0;
                        } else {
                            cell = new PdfPCell(new Phrase((datopac.getId_tipo_sexo() == 2) ? "1" : "", DATO_FONT));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                            sumbs[4] += (datopac.getId_tipo_sexo() == 2) ? 1 : 0;

                            cell = new PdfPCell(new Phrase((datopac.getId_tipo_sexo() == 1) ? "l" : "", DATO_FONT));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                            sumbs[5] += (datopac.getId_tipo_sexo() == 1) ? 1 : 0;
                        }

                    }
                } else {
                    for (int k = 0; k < 6; k++) { //coloca filas vacias entes de colocar el dato
                        cell = new PdfPCell(new Phrase("", CABEZA_COLUMNA_FONT));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table.addCell(cell);
                    }
                }

                if ("L".equals(datopac.getDcronica()) || "G".equals(datopac.getDcronica()) || "M".equals(datopac.getDcronica())) {
                    if (datopac.getEdad() < 1) {
                        if (datopac.getId_cuaderno() == 3 || datopac.getId_cuaderno() == 2) {
                            cell = new PdfPCell(new Phrase((datopac.getId_tipo_sexo() == 2) ? "1" : "", DATO_FONT_BOLD));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                            sumbs[6] += (datopac.getId_tipo_sexo() == 2) ? 1 : 0;
                            sumbs2[6] += (datopac.getId_tipo_sexo() == 2) ? 1 : 0;

                            cell = new PdfPCell(new Phrase((datopac.getId_tipo_sexo() == 1) ? "l" : "", DATO_FONT_BOLD));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                            sumbs[7] += (datopac.getId_tipo_sexo() == 1) ? 1 : 0;
                            sumbs2[7] += (datopac.getId_tipo_sexo() == 1) ? 1 : 0;
                        } else {
                            cell = new PdfPCell(new Phrase((datopac.getId_tipo_sexo() == 2) ? "1" : "", DATO_FONT));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                            sumbs[6] += (datopac.getId_tipo_sexo() == 2) ? 1 : 0;

                            cell = new PdfPCell(new Phrase((datopac.getId_tipo_sexo() == 1) ? "l" : "", DATO_FONT));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                            sumbs[7] += (datopac.getId_tipo_sexo() == 1) ? 1 : 0;
                        }

                        for (int k = 0; k < 4; k++) { //coloca filas vacias entes de colocar el dato
                            cell = new PdfPCell(new Phrase("", CABEZA_COLUMNA_FONT));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                        }
                    } else if (datopac.getEdad() >= 1 && datopac.getEdad() < 2) {
                        for (int k = 0; k < 2; k++) { //coloca filas vacias entes de colocar el dato
                            cell = new PdfPCell(new Phrase("", CABEZA_COLUMNA_FONT));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                        }
                        if (datopac.getId_cuaderno() == 3 || datopac.getId_cuaderno() == 2) {
                            cell = new PdfPCell(new Phrase((datopac.getId_tipo_sexo() == 2) ? "1" : "", DATO_FONT_BOLD));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                            sumbs[8] += (datopac.getId_tipo_sexo() == 2) ? 1 : 0;
                            sumbs2[8] += (datopac.getId_tipo_sexo() == 2) ? 1 : 0;

                            cell = new PdfPCell(new Phrase((datopac.getId_tipo_sexo() == 1) ? "l" : "", DATO_FONT_BOLD));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                            sumbs[9] += (datopac.getId_tipo_sexo() == 1) ? 1 : 0;
                            sumbs2[9] += (datopac.getId_tipo_sexo() == 1) ? 1 : 0;
                        } else {
                            cell = new PdfPCell(new Phrase((datopac.getId_tipo_sexo() == 2) ? "1" : "", DATO_FONT));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                            sumbs[8] += (datopac.getId_tipo_sexo() == 2) ? 1 : 0;

                            cell = new PdfPCell(new Phrase((datopac.getId_tipo_sexo() == 1) ? "l" : "", DATO_FONT));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                            sumbs[9] += (datopac.getId_tipo_sexo() == 1) ? 1 : 0;
                        }

                        for (int k = 0; k < 2; k++) { //coloca filas vacias entes de colocar el dato
                            cell = new PdfPCell(new Phrase("", CABEZA_COLUMNA_FONT));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                        }
                    } else if (datopac.getEdad() >= 2 && datopac.getEdad() < 5) {
                        for (int k = 0; k < 4; k++) { //coloca filas vacias entes de colocar el dato
                            cell = new PdfPCell(new Phrase("", CABEZA_COLUMNA_FONT));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                        }
                        if (datopac.getId_cuaderno() == 3 || datopac.getId_cuaderno() == 2) {
                            cell = new PdfPCell(new Phrase((datopac.getId_tipo_sexo() == 2) ? "1" : "", DATO_FONT_BOLD));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                            sumbs[10] += (datopac.getId_tipo_sexo() == 2) ? 1 : 0;
                            sumbs2[10] += (datopac.getId_tipo_sexo() == 2) ? 1 : 0;

                            cell = new PdfPCell(new Phrase((datopac.getId_tipo_sexo() == 1) ? "l" : "", DATO_FONT_BOLD));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                            sumbs[11] += (datopac.getId_tipo_sexo() == 1) ? 1 : 0;
                            sumbs2[11] += (datopac.getId_tipo_sexo() == 1) ? 1 : 0;
                        } else {
                            cell = new PdfPCell(new Phrase((datopac.getId_tipo_sexo() == 2) ? "1" : "", DATO_FONT));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                            sumbs[10] += (datopac.getId_tipo_sexo() == 2) ? 1 : 0;

                            cell = new PdfPCell(new Phrase((datopac.getId_tipo_sexo() == 1) ? "l" : "", DATO_FONT));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                            sumbs[11] += (datopac.getId_tipo_sexo() == 1) ? 1 : 0;
                        }

                    }
                } else {
                    for (int k = 0; k < 6; k++) { //coloca filas vacias entes de colocar el dato
                        cell = new PdfPCell(new Phrase("", CABEZA_COLUMNA_FONT));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table.addCell(cell);
                    }
                }
//fin de talla/edad

//coloca datos para hierro               
                if ("P".equals(datopac.getDhierro())) {   ////desde 5/05/2015 desaparce ferrosol y solo hay chispitas

                    cell = new PdfPCell(new Phrase(("P".equals(datopac.getDhierro()) && datopac.getEdad() == 0) ? "1" : "", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                    sumbs[12] += ("P".equals(datopac.getDhierro()) && datopac.getEdad() == 0) ? 1 : 0;

                    cell = new PdfPCell(new Phrase(("P".equals(datopac.getDhierro()) && datopac.getEdad() == 1) ? "1" : "", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                    sumbs[13] += ("P".equals(datopac.getDhierro()) && datopac.getEdad() == 1) ? 1 : 0;

                    cell = new PdfPCell(new Phrase(("P".equals(datopac.getDhierro()) && datopac.getEdad() == 2) ? "1" : "", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                    sumbs[14] += ("P".equals(datopac.getDhierro()) && datopac.getEdad() == 2) ? 1 : 0;

                    cell = new PdfPCell(new Phrase(("P".equals(datopac.getDhierro()) && datopac.getEdad() > 2) ? "1" : "", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                    sumbs[15] += ("P".equals(datopac.getDhierro()) && datopac.getEdad() > 2) ? 1 : 0;
                } else {
                    if (!"P".equals(datopac.getDhierro())) {
                        for (int k = 0; k < 4; k++) { //coloca filas vacias entes de colocar el dato
                            cell = new PdfPCell(new Phrase("", CABEZA_COLUMNA_FONT));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                        }
                    }
                }

                //fin del hierro
                //EMPIEZA a colocar datos de vitamina A              
                if ("U".equals(datopac.getDvitaa())) {
                    cell = new PdfPCell(new Phrase(("U".equals(datopac.getDvitaa()) && datopac.getEdad() == 0) ? "1" : "", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                    sumbs[16] += ("U".equals(datopac.getDvitaa()) && datopac.getEdad() == 0) ? 1 : 0;

                    for (int k = 0; k < 4; k++) { //coloca filas vacias entes de colocar el dato
                        cell = new PdfPCell(new Phrase("", CABEZA_COLUMNA_FONT));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table.addCell(cell);
                    }
                } else if ("P".equals(datopac.getDvitaa()) && datopac.getEdad() == 1) {
                    cell = new PdfPCell(new Phrase("", CABEZA_COLUMNA_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase(("P".equals(datopac.getDvitaa()) && datopac.getEdad() == 1) ? "1" : "", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                    sumbs[17] += ("P".equals(datopac.getDvitaa()) && datopac.getEdad() == 1) ? 1 : 0;

                    for (int k = 0; k < 3; k++) { //coloca filas vacias entes de colocar el dato
                        cell = new PdfPCell(new Phrase("", CABEZA_COLUMNA_FONT));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table.addCell(cell);
                    }
                } else if ("S".equals(datopac.getDvitaa()) && datopac.getEdad() == 1) {
                    for (int k = 0; k < 2; k++) { //coloca filas vacias entes de colocar el dato
                        cell = new PdfPCell(new Phrase("", CABEZA_COLUMNA_FONT));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table.addCell(cell);
                    }

                    cell = new PdfPCell(new Phrase(("S".equals(datopac.getDvitaa()) && datopac.getEdad() == 1) ? "1" : "", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                    sumbs[18] += ("S".equals(datopac.getDvitaa()) && datopac.getEdad() == 1) ? 1 : 0;

                    for (int k = 0; k < 2; k++) { //coloca filas vacias entes de colocar el dato
                        cell = new PdfPCell(new Phrase("", CABEZA_COLUMNA_FONT));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table.addCell(cell);
                    }
                } else if ("P".equals(datopac.getDvitaa()) && datopac.getEdad() > 1) {
                    for (int k = 0; k < 3; k++) { //coloca filas vacias entes de colocar el dato
                        cell = new PdfPCell(new Phrase("", CABEZA_COLUMNA_FONT));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table.addCell(cell);
                    }

                    cell = new PdfPCell(new Phrase(("P".equals(datopac.getDvitaa()) && datopac.getEdad() > 1) ? "1" : "", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                    sumbs[19] += ("P".equals(datopac.getDvitaa()) && datopac.getEdad() > 1) ? 1 : 0;

                    cell = new PdfPCell(new Phrase("", CABEZA_COLUMNA_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);

                } else if ("S".equals(datopac.getDvitaa()) && datopac.getEdad() > 1) {
                    for (int k = 0; k < 4; k++) { //coloca filas vacias entes de colocar el dato
                        cell = new PdfPCell(new Phrase("", CABEZA_COLUMNA_FONT));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table.addCell(cell);
                    }

                    cell = new PdfPCell(new Phrase(("S".equals(datopac.getDvitaa()) && datopac.getEdad() > 1) ? "1" : "", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                    sumbs[20] += ("S".equals(datopac.getDvitaa()) && datopac.getEdad() > 1) ? 1 : 0;

                } else if ("X".equals(datopac.getDvitaa())) {
                    for (int k = 0; k < 5; k++) { //coloca filas vacias entes de colocar el dato
                        cell = new PdfPCell(new Phrase("", CABEZA_COLUMNA_FONT));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table.addCell(cell);
                    }
                }
                //fin de vitamina A              

                cell = new PdfPCell(new Phrase((((datopac.getDesesti() & 64) == 64 || (datopac.getDesesti() & 32) == 32) && datopac.getEdad() == 0) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[21] += (((datopac.getDesesti() & 64) == 64 || (datopac.getDesesti() & 32) == 32) && datopac.getEdad() == 0) ? 1 : 0;///Zinc jarabe

                cell = new PdfPCell(new Phrase((((datopac.getDesesti() & 64) == 64 || (datopac.getDesesti() & 32) == 32) && datopac.getEdad() == 1) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[22] += (((datopac.getDesesti() & 64) == 64 || (datopac.getDesesti() & 32) == 32) && datopac.getEdad() == 1) ? 1 : 0;///Zinc jarabe

                ////fin de zinc
////mebendazol 
                if ("P".equals(datopac.getLmexclu())) {
                    cell = new PdfPCell(new Phrase(("P".equals(datopac.getLmexclu()) ? "1" : ""), DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                    sumbs[23] += ("P".equals(datopac.getLmexclu())) ? 1 : 0;

                    cell = new PdfPCell(new Phrase(" ", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                } else if ("S".equals(datopac.getLmexclu())) {
                    cell = new PdfPCell(new Phrase(" ", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase(("S".equals(datopac.getLmexclu()) ? "1" : ""), DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                    sumbs[24] += ("S".equals(datopac.getLmexclu())) ? 1 : 0;
                } else {
                    cell = new PdfPCell(new Phrase(" ", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                    cell = new PdfPCell(new Phrase(" ", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                }

///lactancia materna
                cell = new PdfPCell(new Phrase((datopac.getSuma4() == 1 ? "1" : ""), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[25] += datopac.getSuma4();

                cell = new PdfPCell(new Phrase((datopac.getSuma5() == 1 ? "1" : ""), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[26] += datopac.getSuma5();

                cell = new PdfPCell(new Phrase((datopac.getSuma1() == 1 ? "1" : ""), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[27] += datopac.getSuma1();
                ////NUtribebe menores 1 año y de 1 año              
                cell = new PdfPCell(new Phrase(((datopac.getSuma2() == 1 && datopac.getEdad() == 0) ? "1" : ""), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[28] += (datopac.getSuma2() == 1 && datopac.getEdad() == 0) ? 1 : 0;

                cell = new PdfPCell(new Phrase(((datopac.getSuma2() == 1 && datopac.getEdad() == 1) ? "1" : ""), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[29] += (datopac.getSuma2() == 1 && datopac.getEdad() == 1) ? 1 : 0;

                //se empieza con desarrollo y estimulacion             
                cell = new PdfPCell(new Phrase((datopac.getDesesti() & 1) == 1 ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[30] += (datopac.getDesesti() & 1) == 1 ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getDesesti() & 2) == 2 ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[31] += (datopac.getDesesti() & 2) == 2 ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getDesesti() & 4) == 4 ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[32] += (datopac.getDesesti() & 4) == 4 ? 1 : 0;

                cell = new PdfPCell(new Phrase((datopac.getDesesti() & 8) == 8 ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[33] += (datopac.getDesesti() & 8) == 8 ? 1 : 0;/////ATLU

                cell = new PdfPCell(new Phrase((datopac.getDesesti() & 16) == 16 ? "1" : "", DATO_FONT));////ATLU
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[34] += (datopac.getDesesti() & 16) == 16 ? 1 : 0;///Zinc jarabe

                cell = new PdfPCell(new Phrase((((datopac.getDesesti() & 64) == 64 || (datopac.getDesesti() & 32) == 32) && datopac.getEdad() > 1 && datopac.getEdad() < 5) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);///zinc taleta
                table.addCell(cell);
                sumbs[35] += (((datopac.getDesesti() & 64) == 64 || (datopac.getDesesti() & 32) == 32) && datopac.getEdad() > 1 && datopac.getEdad() < 5) ? 1 : 0;///Zinc jarabe

                cell = new PdfPCell(new Phrase((datopac.getDesesti() & 128) == 128 ? "1" : "", DATO_FONT));///acido folico
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[36] += (datopac.getDesesti() & 128) == 128 ? 1 : 0;///Acido folico
//se termina con desarrollo y estimulacion              
//empieza lo controles nuevos y repetidos
                cell = new PdfPCell(new Phrase(("N".equals(datopac.getTipoconsulta()) && datopac.getId_tipo_sexo() == 2 && datopac.getSeleccion() == 1 && datopac.getEdad() == 0 && datopac.getMes() < 6) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[37] += ("N".equals(datopac.getTipoconsulta()) && datopac.getId_tipo_sexo() == 2 && datopac.getSeleccion() == 1 && datopac.getEdad() == 0 && datopac.getMes() < 6) ? 1 : 0;

                cell = new PdfPCell(new Phrase(("N".equals(datopac.getTipoconsulta()) && datopac.getId_tipo_sexo() == 1 && datopac.getSeleccion() == 1 && datopac.getEdad() == 0 && datopac.getMes() < 6) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[38] += ("N".equals(datopac.getTipoconsulta()) && datopac.getId_tipo_sexo() == 1 && datopac.getSeleccion() == 1 && datopac.getEdad() == 0 && datopac.getMes() < 6) ? 1 : 0;

                cell = new PdfPCell(new Phrase(("R".equals(datopac.getTipoconsulta()) && datopac.getId_tipo_sexo() == 2 && datopac.getSeleccion() == 1 && datopac.getEdad() == 0 && datopac.getMes() < 6) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[39] += ("R".equals(datopac.getTipoconsulta()) && datopac.getId_tipo_sexo() == 2 && datopac.getSeleccion() == 1 && datopac.getEdad() == 0 && datopac.getMes() < 6) ? 1 : 0;

                cell = new PdfPCell(new Phrase(("R".equals(datopac.getTipoconsulta()) && datopac.getId_tipo_sexo() == 1 && datopac.getSeleccion() == 1 && datopac.getEdad() == 0 && datopac.getMes() < 6) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[40] += ("R".equals(datopac.getTipoconsulta()) && datopac.getId_tipo_sexo() == 1 && datopac.getSeleccion() == 1 && datopac.getEdad() == 0 && datopac.getMes() < 6) ? 1 : 0;
//termina tipo de consulta por sexo
                ///////////7de 6 meses a 1 año    
                cell = new PdfPCell(new Phrase(("N".equals(datopac.getTipoconsulta()) && datopac.getId_tipo_sexo() == 2 && datopac.getSeleccion() == 1 && datopac.getEdad() == 0 && datopac.getMes() > 5) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[41] += ("N".equals(datopac.getTipoconsulta()) && datopac.getId_tipo_sexo() == 2 && datopac.getSeleccion() == 1 && datopac.getEdad() == 0 && datopac.getMes() > 5) ? 1 : 0;

                cell = new PdfPCell(new Phrase(("N".equals(datopac.getTipoconsulta()) && datopac.getId_tipo_sexo() == 1 && datopac.getSeleccion() == 1 && datopac.getEdad() == 0 && datopac.getMes() > 5) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[42] += ("N".equals(datopac.getTipoconsulta()) && datopac.getId_tipo_sexo() == 1 && datopac.getSeleccion() == 1 && datopac.getEdad() == 0 && datopac.getMes() > 5) ? 1 : 0;

                cell = new PdfPCell(new Phrase(("R".equals(datopac.getTipoconsulta()) && datopac.getId_tipo_sexo() == 2 && datopac.getSeleccion() == 1 && datopac.getEdad() == 0 && datopac.getMes() > 5) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[43] += ("R".equals(datopac.getTipoconsulta()) && datopac.getId_tipo_sexo() == 2 && datopac.getSeleccion() == 1 && datopac.getEdad() == 0 && datopac.getMes() > 5) ? 1 : 0;

                cell = new PdfPCell(new Phrase(("R".equals(datopac.getTipoconsulta()) && datopac.getId_tipo_sexo() == 1 && datopac.getSeleccion() == 1 && datopac.getEdad() == 0 && datopac.getMes() > 5) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[44] += ("R".equals(datopac.getTipoconsulta()) && datopac.getId_tipo_sexo() == 1 && datopac.getSeleccion() == 1 && datopac.getEdad() == 0 && datopac.getMes() > 5) ? 1 : 0;

                /////////////de 1 año a 4 años     
                cell = new PdfPCell(new Phrase(("N".equals(datopac.getTipoconsulta()) && datopac.getId_tipo_sexo() == 2 && datopac.getSeleccion() == 1 && datopac.getEdad() > 0) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[45] += ("N".equals(datopac.getTipoconsulta()) && datopac.getId_tipo_sexo() == 2 && datopac.getSeleccion() == 1 && datopac.getEdad() > 0) ? 1 : 0;

                cell = new PdfPCell(new Phrase(("N".equals(datopac.getTipoconsulta()) && datopac.getId_tipo_sexo() == 1 && datopac.getSeleccion() == 1 && datopac.getEdad() > 0) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[46] += ("N".equals(datopac.getTipoconsulta()) && datopac.getId_tipo_sexo() == 1 && datopac.getSeleccion() == 1 && datopac.getEdad() > 0) ? 1 : 0;

                cell = new PdfPCell(new Phrase(("R".equals(datopac.getTipoconsulta()) && datopac.getId_tipo_sexo() == 2 && datopac.getSeleccion() == 1 && datopac.getEdad() > 0) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[47] += ("R".equals(datopac.getTipoconsulta()) && datopac.getId_tipo_sexo() == 2 && datopac.getSeleccion() == 1 && datopac.getEdad() > 0) ? 1 : 0;

                cell = new PdfPCell(new Phrase(("R".equals(datopac.getTipoconsulta()) && datopac.getId_tipo_sexo() == 1 && datopac.getSeleccion() == 1 && datopac.getEdad() > 0) ? "1" : "", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumbs[48] += ("R".equals(datopac.getTipoconsulta()) && datopac.getId_tipo_sexo() == 1 && datopac.getSeleccion() == 1 && datopac.getEdad() > 0) ? 1 : 0;
            }

            for (int i = listaCuaderno4.size(); i < pag * filas + filas; i++) {
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
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            for (int j = 0; j < 50; j++) {
                cell = new PdfPCell(new Phrase(Integer.toString(sumbs[j]), DATO_FONT));
                if (j < 12) {
                    cell = new PdfPCell(new Phrase(sumbs[j] + "\n." + sumbs2[j], DATO_FONT));
                }
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(cell);
            }

            for (int k = 0; k < 20; k++) { //coloca filas vacias entes de colocar el dato
                cell = new PdfPCell(new Phrase("", CABEZA_COLUMNA_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
            }

            document.add(table);
            document.newPage();

            ////nueva pagina 3
            document.newPage();

            int NumColumns3 = 20;
            table = new PdfPTable(NumColumns3);
            int[] fwidthx3 = {8, 5, 5, 5, 40, 40, 5, 5, 5, 5, 5, 5, 5, 5, 5, 7, 7, 7, 7, 7};
            table.setWidths(fwidthx3);
            table.setWidthPercentage(100);

            cell = new PdfPCell(new Phrase("Cuaderno No. 2                                                           HOJA 3                          ATENCION INTEGRAL DEL NINIO(A) MENOR DE 5 añoS", TITULO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(NumColumns);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("No", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            addtitulofila30(table, "Actividades Enfermeria", "Inyectable", "Sueros", "Curacones");

            cell = new PdfPCell(new Phrase("Diagnostico", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Tratamiento", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Responsable", CABEZA_COLUMNA_FONT));
            //cell.setRotation(90);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            int NumColum3 = 8;
            PdfPTable tablea23 = new PdfPTable(NumColum3);
            int[] xwidthsc3 = {6, 6, 6, 6, 6, 6, 6, 6};
            tablea23.setWidths(xwidthsc3);
            tablea23.setWidthPercentage(100);

            cell = new PdfPCell(new Phrase("Registro de la mortalidad y causas en el menor de 5 años (Fuera del establecimiento)", DATO_FONT));
            cell.setColspan(8);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablea23.addCell(cell);

            cell = new PdfPCell(new Phrase("M. neonatal  tardia (7-27 dias)", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablea23.addCell(cell);
            cell = new PdfPCell(new Phrase("M<28 dias por sepsis", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablea23.addCell(cell);
            cell = new PdfPCell(new Phrase("M<28 dias asfixia perinatal", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablea23.addCell(cell);
            cell = new PdfPCell(new Phrase("M 28 dias a < 1 año", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablea23.addCell(cell);
            cell = new PdfPCell(new Phrase("M<5 años por Diarrea", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablea23.addCell(cell);
            cell = new PdfPCell(new Phrase("M<5 años por Neumonia", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablea23.addCell(cell);
            cell = new PdfPCell(new Phrase("M<5 años Desnt. agud grave", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablea23.addCell(cell);
            cell = new PdfPCell(new Phrase("Otras Causas Muerte", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablea23.addCell(cell);

            cell = new PdfPCell(tablea23);
            cell.setColspan(8);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            int NumColum3a = 5;
            PdfPTable table23 = new PdfPTable(NumColum3a);
            int[] xwidthsc3a = {6, 6, 6, 6, 6};
            table23.setWidths(xwidthsc3a);
            table23.setWidthPercentage(100);

            cell = new PdfPCell(new Phrase("Referencias y Contrareferencias", DATO_FONT));
            cell.setColspan(5);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table23.addCell(cell);

            cell = new PdfPCell(new Phrase("Pac. ref. a otros estab.", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table23.addCell(cell);
            cell = new PdfPCell(new Phrase("PCD ref. a Und. Calif. Discap.", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table23.addCell(cell);
            cell = new PdfPCell(new Phrase("Pac. contrareferidos al estab.", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table23.addCell(cell);
            cell = new PdfPCell(new Phrase("Pac. ref. de la comdad o med. trad.", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table23.addCell(cell);
            cell = new PdfPCell(new Phrase("Pac. ref. a la medicina trad.", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table23.addCell(cell);

            cell = new PdfPCell(table23);
            cell.setColspan(5);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            for (int i = pag * filas + 0; i < pag * filas + filas && i < listaCuaderno4.size(); i++) {

                Cuadernos datopac = (Cuadernos) listaCuaderno4.get(i);

                cell = new PdfPCell(new Phrase(Integer.toString(i + 1), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                String in = "0";
                String su = "0";
                String cu = "0";
                for (int j = 0; j < libro6.size(); j++) {
                    Cuadernos Libro6 = (Cuadernos) libro6.get(j);
                    if (Libro6.getId_historial() == datopac.getId_laboratorio()) {
                        if (Libro6.getInyectable() > 0) {
                            in = Integer.toString(Libro6.getInyectable());
                        }
                        if (Libro6.getSueros() > 0) {
                            su = Integer.toString(Libro6.getSueros());
                        }
                        if (Libro6.getCuraciones() > 0) {
                            cu = Integer.toString(Libro6.getCuraciones());
                        }

                    }
                }

                if (!"0".equals(in)) {
                    cell = new PdfPCell(new Phrase(in, DATO_FONT));
                } else {
                    cell = new PdfPCell(new Phrase("", DATO_FONT));
                }
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumcs[0] += (Integer.parseInt(in) > 0) ? Integer.parseInt(in) : 0;

                if (!"0".equals(su)) {
                    cell = new PdfPCell(new Phrase(su, DATO_FONT));
                } else {
                    cell = new PdfPCell(new Phrase("", DATO_FONT));
                }
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumcs[1] += (Integer.parseInt(su) > 0) ? Integer.parseInt(su) : 0;

                if (!"0".equals(cu)) {
                    cell = new PdfPCell(new Phrase(cu, DATO_FONT));
                } else {
                    cell = new PdfPCell(new Phrase("", DATO_FONT));
                }
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                sumcs[2] += (Integer.parseInt(cu) > 0) ? Integer.parseInt(cu) : 0;

                if (datopac.getDiagnostico() == null || "null".equals(datopac.getDiagnostico())) {
                    datopac.setDiagnostico("");
                } else {
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("<p>", ""));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("</p>", ""));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("&nbsp;", ""));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("<strong>", ""));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("</strong>", ""));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("<br />", " "));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("<u>", " "));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("</u>", " "));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("<ol>", " "));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("</ol>", " "));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("<ul>", " "));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("</ul>", " "));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("<li>", " "));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("</li>", " "));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("&ntilde;", "n"));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("&Ntilde;", "N"));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("&Aacute;", "A"));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("&Eacute;", "E"));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("&Iacute;", "I"));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("&Oacute;", "O"));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("&Uacute;", "U"));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("&aacute;", "a"));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("&eacute;", "e"));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("&iacute;", "i"));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("&oacute;", "o"));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("&uacute;", "u"));
                    datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("&quot;", "'"));
                }

                String CIE10 = "";
                for (int k = 0; k < cie.size(); k++) {
                    Cuadernos cie10 = (Cuadernos) cie.get(k);
                    if (cie10.getId_historial() == datopac.getId_laboratorio()) {
                        CIE10 = cie10.getTipodent() + "[" + (cie10.getObservaciones().toLowerCase().length() > 15 ? cie10.getObservaciones().toLowerCase().substring(0, 15) : cie10.getObservaciones().toLowerCase()) + "]" + ";" + CIE10;
                    }
                }

                cell = new PdfPCell(new Phrase((datopac.getDiagnostico().length() > 30) ? datopac.getDiagnostico().toLowerCase().substring(0, 30) + "->" + CIE10.trim() : datopac.getDiagnostico() + "->" + CIE10.trim(), DATO_FONT_P));
                //cell = new PdfPCell(new Phrase(datopac.getDiagnostico() +"->"+CIE10.trim() , DATO_FONT_P));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);
                sumcs[3] += 0;/////diagnostico

                if (datopac.getAccion() == null) {
                } else {
                    datopac.setAccion(datopac.getAccion().replaceAll("<p>", ""));
                    datopac.setAccion(datopac.getAccion().replaceAll("</p>", ""));
                    datopac.setAccion(datopac.getAccion().replaceAll("&nbsp;", ""));
                    datopac.setAccion(datopac.getAccion().replaceAll("<strong>", " "));
                    datopac.setAccion(datopac.getAccion().replaceAll("</strong>", " "));
                    datopac.setAccion(datopac.getAccion().replaceAll("<br />", " "));
                    datopac.setAccion(datopac.getAccion().replaceAll("<u>", " "));
                    datopac.setAccion(datopac.getAccion().replaceAll("</u>", " "));
                    datopac.setAccion(datopac.getAccion().replaceAll("<ol>", " "));
                    datopac.setAccion(datopac.getAccion().replaceAll("</ol>", " "));
                    datopac.setAccion(datopac.getAccion().replaceAll("<ul>", " "));
                    datopac.setAccion(datopac.getAccion().replaceAll("</ul>", " "));
                    datopac.setAccion(datopac.getAccion().replaceAll("<li>", " "));
                    datopac.setAccion(datopac.getAccion().replaceAll("</li>", " "));
                    datopac.setAccion(datopac.getAccion().replaceAll("&ntilde;", "n"));
                    datopac.setAccion(datopac.getAccion().replaceAll("&Ntilde;", "N"));
                    datopac.setAccion(datopac.getAccion().replaceAll("&Uacute;", "U"));
                    datopac.setAccion(datopac.getAccion().replaceAll("&Oacute;", "O"));
                    datopac.setAccion(datopac.getAccion().replaceAll("&Iacute;", "I"));
                    datopac.setAccion(datopac.getAccion().replaceAll("&Aacute;", "A"));
                    datopac.setAccion(datopac.getAccion().replaceAll("&Eacute;", "E"));
                    datopac.setAccion(datopac.getAccion().replaceAll("&uacute;", "u"));
                    datopac.setAccion(datopac.getAccion().replaceAll("&oacute;", "o"));
                    datopac.setAccion(datopac.getAccion().replaceAll("&iacute;", "i"));
                    datopac.setAccion(datopac.getAccion().replaceAll("&aacute;", "a"));
                    datopac.setAccion(datopac.getAccion().replaceAll("&eacute;", "e"));
                    datopac.setAccion(datopac.getAccion().replaceAll("&quot;", "'"));
                }
                cell = new PdfPCell(new Phrase((datopac.getAccion().length() > 40) ? datopac.getAccion().toLowerCase().substring(0, 40) : datopac.getAccion(), DATO_FONT_P));
                //cell = new PdfPCell(new Phrase(datopac.getAccion() , DATO_FONT_P));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);
                sumcs[4] += 0;/////plande accion

                cell = new PdfPCell(new Phrase(Integer.toString(datopac.getId_persona()), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);
                sumcs[5] += 0;/////usuario

                if (datopac.getSuma12() == 1) { ///Muerte Neonatal tardia (7-27 dias)
                    cell = new PdfPCell(new Phrase("1", DATO_FONT_P));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);
                    sumcs[6] += (datopac.getSuma12() == 1) ? 1 : 0;
                } else {
                    cell = new PdfPCell(new Phrase("", DATO_FONT_P));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);
                }
                if (datopac.getSuma12() == 2) {///Muerte Menor 28 dias por sepsis
                    cell = new PdfPCell(new Phrase("1", DATO_FONT_P));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);
                    sumcs[7] += (datopac.getSuma12() == 2) ? 1 : 0;
                } else {
                    cell = new PdfPCell(new Phrase("", DATO_FONT_P));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);
                }
                if (datopac.getSuma12() == 3) {///Muerte Menor 28 dias por asfixia perinatal
                    cell = new PdfPCell(new Phrase("1", DATO_FONT_P));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);
                    sumcs[8] += (datopac.getSuma12() == 3) ? 1 : 0;
                } else {
                    cell = new PdfPCell(new Phrase("", DATO_FONT_P));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);
                }
                if (datopac.getSuma12() == 4) {///Muerte de 28 dias a menor 1 año
                    cell = new PdfPCell(new Phrase("1", DATO_FONT_P));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);
                    sumcs[9] += (datopac.getSuma12() == 4) ? 1 : 0;
                } else {
                    cell = new PdfPCell(new Phrase("", DATO_FONT_P));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);
                }
                if (datopac.getSuma12() == 5) {///Muerte Menor 5 años por Diarrea
                    cell = new PdfPCell(new Phrase("1", DATO_FONT_P));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);
                    sumcs[10] += (datopac.getSuma12() == 5) ? 1 : 0;
                } else {
                    cell = new PdfPCell(new Phrase("", DATO_FONT_P));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);
                }
                if (datopac.getSuma12() == 6) {///Muerte Menor 5 años por Neumonia
                    cell = new PdfPCell(new Phrase("1", DATO_FONT_P));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);
                    sumcs[11] += (datopac.getSuma12() == 6) ? 1 : 0;
                } else {
                    cell = new PdfPCell(new Phrase("", DATO_FONT_P));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);
                }
                if (datopac.getSuma12() == 7) {///Muerte Menor 5 años Desnutricion aguda grave
                    cell = new PdfPCell(new Phrase("1", DATO_FONT_P));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);
                    sumcs[12] += (datopac.getSuma12() == 7) ? 1 : 0;
                } else {
                    cell = new PdfPCell(new Phrase("", DATO_FONT_P));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);
                }
                if (datopac.getSuma12() == 8) {///Muerte Menor 5 años por otras cusas
                    cell = new PdfPCell(new Phrase("1", DATO_FONT_P));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);
                    sumcs[13] += (datopac.getSuma12() == 8) ? 1 : 0;
                } else {
                    cell = new PdfPCell(new Phrase("", DATO_FONT_P));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);
                }
                //// id_refer
                if (datopac.getSuma11() == 1) { ////Pac. ref. recibidos por el establecimiento
                    cell = new PdfPCell(new Phrase("1", DATO_FONT_P));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);
                    sumcs[14] += (datopac.getSuma11() == 1) ? 1 : 0;
                } else {
                    cell = new PdfPCell(new Phrase("", DATO_FONT_P));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);
                }
                if (datopac.getSuma11() == 2) {///Pac. ref. a otros establecimiento
                    cell = new PdfPCell(new Phrase(datopac.getReferido()+"\n"+datopac.getSuma30(), DATO_FONT_P));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);
                    sumcs[15] += (datopac.getSuma11() == 2) ? 1 : 0;
                } else {
                    cell = new PdfPCell(new Phrase("", DATO_FONT_P));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);
                }
                if (datopac.getSuma11() == 3) {////PCD ref. a Unid. Calificacion. Discapacidad
                    cell = new PdfPCell(new Phrase("1", DATO_FONT_P));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);
                    sumcs[16] += (datopac.getSuma11() == 3) ? 1 : 0;
                } else {
                    cell = new PdfPCell(new Phrase("", DATO_FONT_P));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);
                }
                if (datopac.getSuma11() == 4) {///Pac. contraref. al establecimiento
                    cell = new PdfPCell(new Phrase("1", DATO_FONT_P));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);
                    sumcs[17] += (datopac.getSuma11() == 4) ? 1 : 0;
                } else {
                    cell = new PdfPCell(new Phrase("", DATO_FONT_P));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);
                }
                if (datopac.getSuma11() == 5) {////Pac. ref. comunidad o medicina tradicional
                    cell = new PdfPCell(new Phrase("1", DATO_FONT_P));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);
                    sumcs[18] += (datopac.getSuma11() == 5) ? 1 : 0;
                } else {
                    cell = new PdfPCell(new Phrase("", DATO_FONT_P));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);
                }

            }

            for (int i = listaCuaderno4.size(); i < pag * filas + filas; i++) {
                cell = new PdfPCell(new Phrase(Integer.toString(i + 1), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);
                for (int j = 1; j < NumColumns3; j++) {
                    cell = new PdfPCell(new Phrase("", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);
                }
            }

            cell = new PdfPCell(new Phrase("Total", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            for (int j = 0; j < 18; j++) {
                cell = new PdfPCell(new Phrase(Integer.toString(sumcs[j]), DATO_FONT));
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

    public static void addfila3(PdfPTable table, String tipo, String dato, String a, String b, String c) {

        PdfPCell cell;
        if (tipo.equals(dato)) {
            cell = new PdfPCell(new Phrase(a, DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(b, DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(c, DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
        } else {
            cell = new PdfPCell(new Phrase("", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
        }

    }

    public static void addtitulofila3(PdfPTable table, String cadena) {
        PdfPCell cell;
        String sCampos[] = {"<1 año", "1 a <2 años", "2 a <5 años"};
        int NumColumns = 3;
        PdfPTable table11 = new PdfPTable(NumColumns);
        table11.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase(cadena, CABEZA_COLUMNA_FONT));
        cell.setColspan(3);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);
        for (int i = 0; i < 3; i++) {
            cell = new PdfPCell(new Phrase(sCampos[i], DATO_FONT_P));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table11.addCell(cell);
        }
        cell = new PdfPCell(table11);
        cell.setColspan(3);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
    }

    public static void addtitulofila33(PdfPTable table, String cadena, String tit1, String a, String b, String tit2, String c, String d, String tit3, String e, String f) {
        PdfPCell cell;
        int NumColumns = 6;
        PdfPTable table11 = new PdfPTable(NumColumns);
        table11.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase(cadena, CABEZA_COLUMNA_FONT));
        cell.setColspan(6);
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

//tercera columna  
        table12 = new PdfPTable(NumCol);
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

    public static void addtitulofila42(PdfPTable table, String cadena, String a, String b, String t1, String c, String t2, String d) {
        PdfPCell cell;
        int NumColumns = 4;
        PdfPTable table11 = new PdfPTable(NumColumns);
        table11.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase(cadena, CABEZA_COLUMNA_FONT));
        cell.setColspan(4);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase(a, DATO_FONT_P));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase(b, DATO_FONT_P));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        addtitulofila10(table11, t1, c);
        addtitulofila10(table11, t2, d);

        cell = new PdfPCell(table11);
        cell.setColspan(4);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
    }

    public static void addtitulofila52(PdfPTable table, String cadena, String a, String t1, String b, String c, String t2, String d, String e) {
        PdfPCell cell;
        int NumColumns = 5;
        PdfPTable table11 = new PdfPTable(NumColumns);
        table11.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase(cadena, CABEZA_COLUMNA_FONT));
        cell.setColspan(5);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase(a, DATO_FONT_P));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        addtitulofila20(table11, t1, b, c);
        addtitulofila20(table11, t2, d, e);

        cell = new PdfPCell(table11);
        cell.setColspan(5);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
    }

    public static void addtitulofila30(PdfPTable table, String cadena, String a, String b, String c) {
        PdfPCell cell;
        int NumColumns = 3;
        PdfPTable table11 = new PdfPTable(NumColumns);
        table11.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase(cadena, CABEZA_COLUMNA_FONT));
        cell.setColspan(3);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase(a, DATO_FONT_P));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase(b, DATO_FONT_P));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase(c, DATO_FONT_P));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        cell = new PdfPCell(table11);
        cell.setColspan(3);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
    }

    public static void addtitulofila40(PdfPTable table, String cadena, String a, String b, String c, String d) {
        PdfPCell cell;
        int NumColumns = 4;
        PdfPTable table11 = new PdfPTable(NumColumns);
        table11.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase(cadena, CABEZA_COLUMNA_FONT));
        cell.setColspan(4);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase(a, DATO_FONT_P));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase(b, DATO_FONT_P));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase(c, DATO_FONT_P));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase(d, DATO_FONT_P));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        cell = new PdfPCell(table11);
        cell.setColspan(4);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
    }

    public static void addtitulofila20(PdfPTable table, String cadena, String a, String b) {
        PdfPCell cell;
        int NumColumns = 2;
        PdfPTable table11 = new PdfPTable(NumColumns);
        table11.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase(cadena, CABEZA_COLUMNA_FONT));
        cell.setColspan(2);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase(a, DATO_FONT_P));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase(b, DATO_FONT_P));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        cell = new PdfPCell(table11);
        cell.setColspan(2);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
    }

    public static void addtitulofila10(PdfPTable table, String cadena, String a) {
        PdfPCell cell;
        int NumColumns = 1;
        PdfPTable table11 = new PdfPTable(NumColumns);
        table11.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase(cadena, CABEZA_COLUMNA_FONT));
        cell.setColspan(1);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase(a, DATO_FONT_P));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        cell = new PdfPCell(table11);
        cell.setColspan(1);
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
