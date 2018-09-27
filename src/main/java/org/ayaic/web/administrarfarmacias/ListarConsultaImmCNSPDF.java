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
import org.ayaic.domain.Localidades;
import org.ayaic.domain.Medicamentos;
import org.ayaic.domain.Personas;

public class ListarConsultaImmCNSPDF extends AbstractPdfView {

    private static final Font TITULO_FONT = new Font(Font.HELVETICA, 12, Font.BOLD, Color.black);
    private static final Font CABEZA_COLUMNA_FONT = new Font(Font.HELVETICA, 8, Font.ITALIC, Color.black);
    private static final Font DATO_FONT = new Font(Font.HELVETICA, 9, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_M = new Font(Font.HELVETICA, 10, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_P = new Font(Font.HELVETICA, 6, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_BOLD = new Font(Font.HELVETICA, 10, Font.BOLD, Color.black);
    private static final int MARGIN = 32;

    protected void buildPdfMetadata(Map model, Document document, HttpServletRequest request) {
        Localidades dl = (Localidades) model.get("localidades");
        document.setPageSize(PageSize.LETTER.rotate());
        document.setMargins(20, 20, 20, 20);
    }

    protected void buildPdfDocument(Map model, Document document, PdfWriter writer, HttpServletRequest req, HttpServletResponse resp) throws Exception {

        java.util.List listImm = (java.util.List) model.get("listarDatosImm");
        java.util.List listSaldos = (java.util.List) model.get("listarImmSaldos");
        Clientes dato = (Clientes) model.get("dato");
        Personas datop = (Personas) model.get("datosJMed");
        String mes = (String) model.get("mes");
        String gestion = (String) model.get("gestion");
        DecimalFormat df = new DecimalFormat("###,##0");
        String cod = (String) model.get("cod");
        //HeaderFooter footer =  new HeaderFooter(new Phrase("Pagina No. "), new Phrase("."));

        //footer.setAlignment(Element.ALIGN_CENTER);
        //footer.setBorder(Rectangle.NO_BORDER);
        //document.setFooter(footer);
        // coloca el titulo de la pagina
        int una;
        if (listImm.size() % 30 == 0) {
            una = 0;
        } else {
            una = 1;
        }

        for (int pag = 0; pag < listImm.size() / 30 + una; pag++) {

            Image escudo = Image.getInstance("/opt/imagenes/lescudo.bmp");
            Paragraph p = new Paragraph("INFORME MENSUAL DE MOVIMIENTO\nDE MEDICAMENTOS E INSUMOS (IMM)", new Font(Font.HELVETICA, 14, Font.BOLDITALIC, new Color(0, 0, 0)));
            p.setAlignment(Element.ALIGN_CENTER);

            PdfPCell cell;

            PdfPTable tablex = new PdfPTable(3);
            int[] fxwidths = {15, 70, 15}; // percentage
            tablex.setWidths(fxwidths);
            tablex.setWidthPercentage(100);

            //cell = new PdfPCell(escudo);
            cell = new PdfPCell(new Phrase("", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            tablex.addCell(cell);

            cell = new PdfPCell(p);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            tablex.addCell(cell);

            cell = new PdfPCell(new Phrase("", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setBorder(Rectangle.NO_BORDER);
            tablex.addCell(cell);

            document.add(tablex);

            PdfPTable table1 = new PdfPTable(3);
            int[] fmwidths = {35, 35, 35}; // percentage
            table1.setWidths(fmwidths);
            table1.setWidthPercentage(100);

            //cell = new PdfPCell(new Phrase("Servicio Departamental de Salud : "+ dato.getDepartamento() , DATO_FONT));
            //cell.setBorder(Rectangle.NO_BORDER);
            //table1.addCell(cell);
            //cell = new PdfPCell(new Phrase("Municipio : "+dato.getLocalidad() , DATO_FONT));
            cell = new PdfPCell(new Phrase("\n", DATO_FONT));
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(3);
            table1.addCell(cell);

            cell = new PdfPCell(new Phrase("                            Establecimiento : " + dato.getEstablecimiento(), DATO_FONT));
            cell.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell);

            cell = new PdfPCell(new Phrase("Responsable : " + datop.getNombres(), DATO_FONT));
            cell.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell);

            //cell = new PdfPCell(new Phrase("Red : "+dato.getRed() , DATO_FONT));
            //cell.setBorder(Rectangle.NO_BORDER);
            //table1.addCell(cell);
            cell = new PdfPCell(new Phrase("Mes : " + mes + "         " + "Año : " + gestion, DATO_FONT));
            cell.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell);

            document.add(table1);

            int NumColumns = 16;
            PdfPTable table = new PdfPTable(NumColumns);
            int[] fwidths = {4, 7, 35, 7, 9, 9, 7, 7, 7, 7, 9, 8, 8, 8, 9, 9};
            table.setWidths(fwidths);
            table.setWidthPercentage(100);

            cell = new PdfPCell(new Phrase("", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            //cell.setBorder(Rectangle.NO_BORDER);
            cell.setGrayFill(0.9f);
            cell.setColspan(6);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Ingresos", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setGrayFill(0.9f);
            cell.setColspan(4);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Egresos", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setGrayFill(0.9f);
            cell.setColspan(5);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setGrayFill(0.9f);
            table.addCell(cell);

            String sCampos[] = {"No", "COD", "MEDICAMENTO\nE INSUMOS", "Forma\nFarm", "Concen-\ntrac", "Saldo\nAnter.", "Almacen", "Caja\nChica", "Otros\nRevers.", "Total\nIngreso", "Externo", "Internados", "Unidosis", "Otros\nEgreso", "Total\nEgreso", "Saldo\nFinal"};
            cell.setColspan(NumColumns);
            //cell.setBorder(Rectangle.NO_BORDER);
            //cell.setGrayFill(80);
            //table.addCell(cell);

            // coloca la cabecera de titulos
            for (int i = 0; i < NumColumns; i++) {
                cell = new PdfPCell(new Phrase(sCampos[i], CABEZA_COLUMNA_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setGrayFill(0.9f);
                table.addCell(cell);
            }

            for (int i = pag * 30 + 0; i < pag * 30 + 30 && i < listImm.size(); i++) {

                Medicamentos datosI = (Medicamentos) listImm.get(i);

                for (int j = 0; j < listSaldos.size(); j++) {
                    Medicamentos datosS = (Medicamentos) listSaldos.get(j);
                    if (datosS.getId_medicamento() == datosI.getId_medicamento()) {
                        datosI.setSuma10(datosS.getSuma4()); ////es el valor individual
                        listImm.set(j, datosI); ////se mete a la lista general
                    }
                }

                cell = new PdfPCell(new Phrase(Integer.toString(i + 1), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(datosI.getCodsumi(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((datosI.getMedicamento().length() > 29) ? datosI.getMedicamento().substring(0, 29) : datosI.getMedicamento(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((datosI.getForma_far().length() > 3) ? datosI.getForma_far().substring(0, 3) : datosI.getForma_far(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((datosI.getConcentra().length() > 6) ? datosI.getConcentra().substring(0, 6) : datosI.getConcentra(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((datosI.getSuma10() != 0) ? df.format(datosI.getSuma10()) : "", DATO_FONT_M));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((datosI.getSuma1() != 0) ? df.format(datosI.getSuma1()) : "", DATO_FONT_M));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((datosI.getSuma2() != 0) ? df.format(datosI.getSuma2()) : "", DATO_FONT_M));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((datosI.getSuma3() != 0) ? df.format(datosI.getSuma3()) : "", DATO_FONT_M));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((datosI.getSuma1() + datosI.getSuma2() + datosI.getSuma3() != 0) ? df.format(datosI.getSuma1() + datosI.getSuma2() + datosI.getSuma3()) : "", DATO_FONT_M));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setGrayFill(0.9f);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((datosI.getSuma4() != 0) ? df.format(datosI.getSuma4()) : "", DATO_FONT_M));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((datosI.getSuma5() != 0) ? df.format(datosI.getSuma5()) : "", DATO_FONT_M));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((datosI.getSuma6() != 0) ? df.format(datosI.getSuma6()) : "", DATO_FONT_M));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(cell);
                /////////////7 son sumi pero no hay todavia
                cell = new PdfPCell(new Phrase((datosI.getSuma7() + datosI.getSuma8() != 0) ? df.format((1) * datosI.getSuma7() - datosI.getSuma8()) : "", DATO_FONT_M));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((datosI.getSuma4() + datosI.getSuma5() + datosI.getSuma6() + datosI.getSuma7() - datosI.getSuma8() != 0) ? df.format(datosI.getSuma4() + datosI.getSuma5() + datosI.getSuma6() + datosI.getSuma7() - datosI.getSuma8()) : "", DATO_FONT_M));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setGrayFill(0.9f);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((datosI.getSuma10() + datosI.getSuma1() + datosI.getSuma2() + datosI.getSuma3() - datosI.getSuma4() - datosI.getSuma5() - datosI.getSuma6() - datosI.getSuma7() + datosI.getSuma8() != 0) ? df.format(datosI.getSuma10() + datosI.getSuma1() + datosI.getSuma2() + datosI.getSuma3() - datosI.getSuma4() - datosI.getSuma5() - datosI.getSuma6() - datosI.getSuma7() + datosI.getSuma8()) : "", DATO_FONT_BOLD));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setGrayFill(0.9f);
                table.addCell(cell);
            }

            cell = new PdfPCell(new Phrase("Elaborado por : " + dato.getNombres(), DATO_FONT));
            cell.setColspan(10);
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Fecha de emision : " + format(new Date(), "dd/MM/yyyy HH:mm"), DATO_FONT));
            cell.setColspan(6);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Pagina No.  " + (pag + 1), TITULO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(16);
            table.addCell(cell);

            //cod=format(fecha,"dd/MM/yyyy HH:mm")+'|'+dato.getCod_esta()+'|'+dato.getId_usuario()+'|'+ip.getHostAddress()+'|'+datopac.getCod_esta()+'|'+datopac.getId_historial()+'|'+datopac.getId_historia();
            PdfContentByte cb = writer.getDirectContent();
            //PdfContentByte cb1 = writer.getDirectContent();
            //BarcodeQRCode qrcode = new BarcodeQRCode(cod, 90, 90, null);
            //java.awt.Image qrImage = qrcode.createAwtImage(Color.black, Color.white);
            //Image finalImage = Image.getInstance(writer, escudo, 1);
            escudo.setAbsolutePosition(50, 50);
            cb.addImage(escudo, 50, 0, 0, 60, 20, 535, true);
            ///(imagen,tamañoAncho ,rotation_x ,angulorotation , amplitud-altura ,TamañoAlto ,PosicionX ,PosicionY(0=abajo, 280=arriba))

            document.add(table);
            document.newPage();
        }
    }

    public static PdfPTable cuadrotitulo(String letra, String cadena) {
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
        cell.setColspan(4);
        table1.addCell(cell);
        cell = new PdfPCell(new Phrase("SUMI\nI", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1.addCell(cell);
        cell = new PdfPCell(new Phrase("PROG\nII", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1.addCell(cell);
        cell = new PdfPCell(new Phrase("VENTA\nIII", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1.addCell(cell);
        cell = new PdfPCell(new Phrase("TOTAL\nI+II+III", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
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
