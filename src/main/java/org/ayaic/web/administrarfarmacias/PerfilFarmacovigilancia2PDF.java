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
import org.ayaic.domain.Recetas;

public class PerfilFarmacovigilancia2PDF extends AbstractPdfView {

    private static final Font TITULO_FONT = new Font(Font.HELVETICA, 12, Font.BOLD, Color.black);
    private static final Font CABEZA_COLUMNA_FONT = new Font(Font.HELVETICA, 8, Font.ITALIC, Color.black);
    private static final Font CABEZA_COLUMNA_FONT_P = new Font(Font.HELVETICA, 5, Font.ITALIC, Color.black);
    private static final Font DATO_FONT = new Font(Font.HELVETICA, 9, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_P = new Font(Font.HELVETICA, 7, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_BOLD = new Font(Font.HELVETICA, 8, Font.BOLD, Color.black);
    private static final int MARGIN = 32;

    protected void buildPdfMetadata(Map model, Document document, HttpServletRequest request) {
        document.setPageSize(PageSize.LEGAL.rotate());
        document.setMargins(30, 30, 20, 10);
    }

    protected void buildPdfDocument(Map model, Document document, PdfWriter writer, HttpServletRequest req, HttpServletResponse resp) throws Exception {

        java.util.List lkardex = (java.util.List) model.get("listarKardexPerfil"); ////la lista de medicamentos del perfil inicial
        java.util.List lkardexDes = (java.util.List) model.get("listarKardexDes");
        java.util.List lkardexdet = (java.util.List) model.get("listarKardexPerfilDet");
        java.util.List listarInter = (java.util.List) model.get("listarAtendidosI");
        java.util.List listarFechaPerfil = (java.util.List) model.get("listarFechaPerfil");
        java.util.List listarPerfilDat = (java.util.List) model.get("listarPerfilDat");
        Clientes dato = (Clientes) model.get("dato");
        Pacientes datos = (Pacientes) model.get("datos");
        String soloedad = (String) model.get("soloedad");
        String dia = (String) model.get("dia");
        String mes = (String) model.get("mes");
        String anio = (String) model.get("anio");
        String nombres = (String) model.get("nombres");
        String hcl = (String) model.get("hcl");
        Image escudo = Image.getInstance("/opt/imagenes/lescudo.bmp");
        String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};

        Historiales datopacinter = new Historiales();
        int una;

        if (lkardex.size() == 0) {
            Paragraph p = new Paragraph("No existe Datos", new Font(Font.HELVETICA, 22, Font.BOLDITALIC, new Color(0, 0, 0)));
            document.add(p);
        }

        int[][] matriz = new int[lkardex.size()][33];

        for (int x = 0; x < matriz.length; x++) {
            Recetas datosS = (Recetas) lkardex.get(x);
            for (int y = 0; y < matriz[x].length; y++) {
                if (y == 0) {
                    matriz[x][y] = datosS.getId_medicamento();
                }
                if (y > 0) {
                    matriz[x][y] = 0;
                }
                for (int j = 0; j < listarPerfilDat.size(); j++) {
                    Recetas datosDat = (Recetas) listarPerfilDat.get(j);
                    if (datosDat.getId_medicamento() == datosS.getId_medicamento()) {
                        if (y == 1 && datosS.getSuma1() == 1) {
                            matriz[x][1] = datosDat.getSuma4();
                        }

                    }
                }
                //System.out.println ("[" + x + "," + y + "] = " + matriz[x][y]);
            }
        }

        if (!listarInter.isEmpty()) {
            datopacinter = (Historiales) listarInter.get(0);
        }//if(!listarFechaPerfil.isEmpty()){
        //  Recetas datoFecha = (Recetas) listarFechaPerfil.get(0);  
        //}

        if (lkardex.size() % 30 == 0) {
            una = 0;
        } else {
            una = 1;
        }

        for (int pag = 0; pag < lkardex.size() / 30 + una; pag++) {

            //Image escudo = Image.getInstance("/opt/imagenes/lescudo.bmp");
            Paragraph p = new Paragraph("Perfil Farmacologico del Paciente", new Font(Font.HELVETICA, 17, Font.BOLDITALIC, new Color(0, 0, 0)));
            p.setAlignment(Element.ALIGN_CENTER);
            //Image sumi = Image.getInstance("/opt/imagenes/sumi.bmp");

            PdfPCell cell;

            PdfPTable tablex = new PdfPTable(3);
            int[] fxwidths = {30, 70, 30}; // percentage
            tablex.setWidths(fxwidths);
            tablex.setWidthPercentage(100);

            cell = new PdfPCell(new Phrase(dato.getEstablecimiento(), DATO_FONT));
            cell.setBorder(Rectangle.NO_BORDER);
            tablex.addCell(cell);

            cell = new PdfPCell(p);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(2);
            tablex.addCell(cell);

            document.add(tablex);

            PdfPTable table1 = new PdfPTable(3);
            int[] fmwidths = {35, 35, 35}; // percentage
            table1.setWidths(fmwidths);
            table1.setWidthPercentage(100);

            cell = new PdfPCell(new Phrase("Paciente : " + datos.getNombres(), DATO_FONT));
            cell.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell);

            cell = new PdfPCell(new Phrase("Empresa : " + datos.getCadena1() + "      Cod :  " + datos.getNro(), DATO_FONT));
            cell.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell);

            if (datos.getId_tipo_sexo() == 1) {
                cell = new PdfPCell(new Phrase("Matricula  :  " + datos.getNro_registro() + "    Edad  :  " + datos.getEdad() + "a" + datos.getMes() + "m" + datos.getDia() + "d     Sexo : FEMENINO", DATO_FONT));
            } else {
                cell = new PdfPCell(new Phrase("Matricula  :  " + datos.getNro_registro() + "    Edad  :  " + datos.getEdad() + "a" + datos.getMes() + "m" + datos.getDia() + "d     Sexo : MASCULINO", DATO_FONT));
            }
            cell.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell);

            if (!listarInter.isEmpty()) {
                cell = new PdfPCell(new Phrase("Medico Tratante : " + datopacinter.getNombre() + "          Fec.Ingreso : " + format(datopacinter.getFecha(), "dd/MM/yyyy HH:mm") + "       Piso: " + datopacinter.getPiso() + "    Sala: " + datopacinter.getSala() + "    Cama: " + datopacinter.getCama(), DATO_FONT));
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setColspan(2);
                table1.addCell(cell);

                if (datopacinter.getDiagnostico() != null && "null".equals(datopacinter.getDiagnostico())) {

                    datopacinter.setDiagnostico(datopacinter.getDiagnostico().replaceAll("<p>", "\n"));
                    datopacinter.setDiagnostico(datopacinter.getDiagnostico().replaceAll("</p>", " "));
                    datopacinter.setDiagnostico(datopacinter.getDiagnostico().replaceAll("<div>", " "));
                    datopacinter.setDiagnostico(datopacinter.getDiagnostico().replaceAll("</div>", " "));
                    datopacinter.setDiagnostico(datopacinter.getDiagnostico().replaceAll("&nbsp;", ""));
                    datopacinter.setDiagnostico(datopacinter.getDiagnostico().replaceAll("<strong>", " "));
                    datopacinter.setDiagnostico(datopacinter.getDiagnostico().replaceAll("</strong>", " "));
                    datopacinter.setDiagnostico(datopacinter.getDiagnostico().replaceAll("<br>", " "));
                    datopacinter.setDiagnostico(datopacinter.getDiagnostico().replaceAll("<br />", "\n"));
                    datopacinter.setDiagnostico(datopacinter.getDiagnostico().replaceAll("<u>", " "));
                    datopacinter.setDiagnostico(datopacinter.getDiagnostico().replaceAll("</u>", " "));
                    datopacinter.setDiagnostico(datopacinter.getDiagnostico().replaceAll("<ul>", " "));
                    datopacinter.setDiagnostico(datopacinter.getDiagnostico().replaceAll("</ul>", " "));
                    datopacinter.setDiagnostico(datopacinter.getDiagnostico().replaceAll("<ol>", " "));
                    datopacinter.setDiagnostico(datopacinter.getDiagnostico().replaceAll("</ol>", " "));
                    datopacinter.setDiagnostico(datopacinter.getDiagnostico().replaceAll("<li>", " "));
                    datopacinter.setDiagnostico(datopacinter.getDiagnostico().replaceAll("</li>", " "));
                    datopacinter.setDiagnostico(datopacinter.getDiagnostico().replaceAll("&ntilde;", "n"));
                    datopacinter.setDiagnostico(datopacinter.getDiagnostico().replaceAll("&Ntilde;", "N"));
                    datopacinter.setDiagnostico(datopacinter.getDiagnostico().replaceAll("&Aacute;", "A"));
                    datopacinter.setDiagnostico(datopacinter.getDiagnostico().replaceAll("&Eacute;", "E"));
                    datopacinter.setDiagnostico(datopacinter.getDiagnostico().replaceAll("&Iacute;", "I"));
                    datopacinter.setDiagnostico(datopacinter.getDiagnostico().replaceAll("&Oacute;", "O"));
                    datopacinter.setDiagnostico(datopacinter.getDiagnostico().replaceAll("&Uacute;", "U"));
                    datopacinter.setDiagnostico(datopacinter.getDiagnostico().replaceAll("&aacute;", "a"));
                    datopacinter.setDiagnostico(datopacinter.getDiagnostico().replaceAll("&eacute;", "e"));
                    datopacinter.setDiagnostico(datopacinter.getDiagnostico().replaceAll("&iacute;", "i"));
                    datopacinter.setDiagnostico(datopacinter.getDiagnostico().replaceAll("&oacute;", "o"));
                    datopacinter.setDiagnostico(datopacinter.getDiagnostico().replaceAll("&uacute;", "u"));
                    datopacinter.setDiagnostico(datopacinter.getDiagnostico().replaceAll("&quot;", "'"));
                }

                cell = new PdfPCell(new Phrase((datopacinter.getDiagnostico().length() > 35) ? "Diagnostico : " + datopacinter.getDiagnostico().substring(0, 35) : "Diagnostico : " + datopacinter.getDiagnostico(), DATO_FONT));
                cell.setBorder(Rectangle.NO_BORDER);
                table1.addCell(cell);
            }

            document.add(table1);

            int NumColumns = 20;
            PdfPTable table = new PdfPTable(NumColumns);
            int[] fwidths = {10, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5};
            table.setWidths(fwidths);
            table.setWidthPercentage(100);

            cell = new PdfPCell(new Phrase("Fecha", DATO_FONT));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setGrayFill(0.9f);
            table.addCell(cell);

            for (int i = 0; i < lkardex.size(); i++) {
                Recetas datoc = (Recetas) lkardex.get(i);
                //cell = new PdfPCell(new Phrase(datoc.getCodsumi()+"_"+datoc.getMedicamento() , DATO_FONT));
                cell = new PdfPCell(new Phrase((datoc.getMedicamento().length() > 20) ? datoc.getCodsumi() + "_" + datoc.getMedicamento().substring(0, 20) : datoc.getCodsumi() + "_" + datoc.getMedicamento(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_BOTTOM);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setRotation(90);
                cell.setGrayFill(0.9f);
                table.addCell(cell);
            }
            for (int i = 0; i < NumColumns - lkardex.size() - 1; i++) {
                cell = new PdfPCell(new Phrase("", DATO_FONT_P));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setGrayFill(0.9f);
                table.addCell(cell);
            }

            ////////////empieza el llenado
            //for (int i = pag*30+0; i<pag*30+30 && i < lkardex.size(); i++){
            Recetas datopac = (Recetas) lkardex.get(0);

            cell = new PdfPCell(new Phrase(format(datopac.getFecha(), "dd/MM/yyyy HH:mm"), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);

            for (int j = 0; j < lkardex.size(); j++) {
                Recetas datoc = (Recetas) lkardex.get(j);
                cell = new PdfPCell(new Phrase(Integer.toString(datoc.getSuma1()) + "/" + Integer.toString(datoc.getSuma1()), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
            }

            for (int m = 0; m < NumColumns - lkardex.size() - 1; m++) {
                cell = new PdfPCell(new Phrase("", DATO_FONT_P));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);
            }

            int n = 0;

            cell = new PdfPCell(new Phrase("09/09/2017 05:15", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("10/10", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("12/12", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("3/3", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("3   18/21", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);

            for (int k = 0; k < 15; k++) {
                cell = new PdfPCell(new Phrase("", DATO_FONT_P));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);
            }

            cell = new PdfPCell(new Phrase("10/09/2017 06:03", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("10/10", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("12/12", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("3/3", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("3   15/21", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);

            for (int k = 0; k < 15; k++) {
                cell = new PdfPCell(new Phrase("", DATO_FONT_P));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);
            }

            cell = new PdfPCell(new Phrase("11/09/2017 10:03", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("10/10", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("12/12", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("3/3", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("4   11/21", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);

            for (int k = 0; k < 15; k++) {
                cell = new PdfPCell(new Phrase("", DATO_FONT_P));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);
            }

            /*
                        for (int h = 0; h < lkardexDes.size(); h++) {
                            Recetas datod = (Recetas) lkardexDes.get(h);	 
                            
                            cell = new PdfPCell(new Phrase(format(datod.getFecha(),"dd/MM/yyyy HH:mm") , DATO_FONT));
                            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);       table.addCell(cell);

                            for (int j = 0+h; j < lkardexDes.size(); j++) {
                                Recetas datodm = (Recetas) lkardexDes.get(j);
                                if(j<=lkardex.size()+h-1){
                                    cell = new PdfPCell(new Phrase(Integer.toString(datodm.getSalidas())+"    "+Integer.toString(datodm.getSaldos())+"/"+Integer.toString(datodm.getSuma1()) , DATO_FONT));
                                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);   table.addCell(cell);
                                    n=j;
                                }
                            }
            
                            for(int m = 0; m <NumColumns-lkardex.size()-1; m++) {
                                cell = new PdfPCell(new Phrase("" , DATO_FONT_P)); cell.setHorizontalAlignment(Element.ALIGN_LEFT); table.addCell(cell);
                             }
                            //i=i+lkardex.size()-1;

                        }    
             */
            table.addCell(cell);

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
