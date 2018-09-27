package org.ayaic.web.administrarfarmacias;

import java.awt.Color;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.view.document.AbstractPdfView;
import java.util.*;
import java.text.*;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import java.io.IOException;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Medicamentos;
import org.ayaic.domain.Personas;

public class ListarConsultaMesCNSPDF extends AbstractPdfView {

    private static final Font TITULO_FONT = new Font(Font.HELVETICA, 12, Font.BOLD, Color.black);
    private static final Font CABEZA_COLUMNA_FONT = new Font(Font.HELVETICA, 8, Font.ITALIC, Color.black);
    private static final Font DATO_FONT = new Font(Font.HELVETICA, 7, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_P = new Font(Font.HELVETICA, 6, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_BOLD = new Font(Font.HELVETICA, 9, Font.BOLD, Color.black);
    private static final int MARGIN = 32;

    protected void buildPdfMetadata(Map model, Document document, HttpServletRequest request) {
        //Localidades dl = (Localidades)model.get("localidades");
        document.setPageSize(PageSize.LETTER.rotate());
        document.setMargins(30, 30, 10, 10);
    }

    protected void buildPdfDocument(Map model, Document document, PdfWriter writer, HttpServletRequest req, HttpServletResponse resp) throws BadElementException, IOException, DocumentException {

        java.util.List listImm = (java.util.List) model.get("listarDatosImm");
        java.util.List listSaldos = (java.util.List) model.get("listarImmSaldos");
        Clientes dato = (Clientes) model.get("dato");
        Personas datop = (Personas) model.get("datosJMed");
        String mes = (String) model.get("mes");
        String f1 = (String) model.get("f1");
        String f2 = (String) model.get("f2");
        String gestion = (String) model.get("gestion");
        DecimalFormat df = new DecimalFormat("###,##0");
        int[] sumas = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        int num = 40;
        int una;
        if (listImm.size() % num == 0) {
            una = 0;
        } else {
            una = 1;
        }

        int[][] matriz = new int[listImm.size()][13];

        for (int x = 0; x < matriz.length; x++) {
            Medicamentos datosS = (Medicamentos) listImm.get(x);
            for (int y = 0; y < matriz[x].length; y++) {
                if (y == 0) {
                    matriz[x][y] = datosS.getId_medicamento();
                }
                if (y > 0) {
                    matriz[x][y] = 0;
                }

                for (int j = 0; j < listSaldos.size(); j++) {
                    Medicamentos datosI = (Medicamentos) listSaldos.get(j);
                    if (datosS.getId_medicamento() == datosI.getId_medicamento()) {
                        if (y == 1 && datosI.getSuma2() == 1) {
                            matriz[x][1] = datosI.getSuma1();
                        }
                        if (y == 2 && datosI.getSuma2() == 2) {
                            matriz[x][2] = datosI.getSuma1();
                        }
                        if (y == 3 && datosI.getSuma2() == 3) {
                            matriz[x][3] = datosI.getSuma1();
                        }
                        if (y == 4 && datosI.getSuma2() == 4) {
                            matriz[x][4] = datosI.getSuma1();
                        }
                        if (y == 5 && datosI.getSuma2() == 5) {
                            matriz[x][5] = datosI.getSuma1();
                        }
                        if (y == 6 && datosI.getSuma2() == 6) {
                            matriz[x][6] = datosI.getSuma1();
                        }
                        if (y == 7 && datosI.getSuma2() == 7) {
                            matriz[x][7] = datosI.getSuma1();
                        }
                        if (y == 8 && datosI.getSuma2() == 8) {
                            matriz[x][8] = datosI.getSuma1();
                        }
                        if (y == 9 && datosI.getSuma2() == 9) {
                            matriz[x][9] = datosI.getSuma1();
                        }
                        if (y == 10 && datosI.getSuma2() == 10) {
                            matriz[x][10] = datosI.getSuma1();
                        }
                        if (y == 11 && datosI.getSuma2() == 11) {
                            matriz[x][11] = datosI.getSuma1();
                        }
                        if (y == 12 && datosI.getSuma2() == 12) {
                            matriz[x][12] = datosI.getSuma1();
                        }
                    }
                }
                //System.out.println ("[" + x + "," + y + "] = " + matriz[x][y]);
            }
        }

        for (int pag = 0; pag < listImm.size() / num + una; pag++) {

            Image escudo = Image.getInstance("/opt/imagenes/lescudo.bmp");
            Paragraph p = new Paragraph("INFORME DE MOVIMIENTO\nDE MEDICAMENTOS E INSUMOS", new Font(Font.HELVETICA, 14, Font.BOLDITALIC, new Color(0, 0, 0)));
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

            cell = new PdfPCell(new Phrase("Servicio Departamental de Salud : " + dato.getDepartamento(), DATO_FONT));
            cell.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell);

            cell = new PdfPCell(new Phrase("Municipio : " + dato.getLocalidad(), DATO_FONT));
            cell.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell);

            cell = new PdfPCell(new Phrase("Establecimiento : " + dato.getEstablecimiento(), DATO_FONT));
            cell.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell);

            cell = new PdfPCell(new Phrase("Responsable : " + datop.getNombres(), DATO_FONT));
            cell.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell);

            cell = new PdfPCell(new Phrase("Red : " + dato.getRed(), DATO_FONT));
            cell.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell);

            cell = new PdfPCell(new Phrase("Desde : " + f1 + "         " + "Hasta : " + f2, DATO_FONT));
            cell.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell);

            document.add(table1);

            int NumColumns = 18;
            PdfPTable table = new PdfPTable(NumColumns);
            int[] fwidths = {4, 5, 30, 8, 10, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 7};
            table.setWidths(fwidths);
            table.setWidthPercentage(100);

            String sCampos[] = {"No", "COD", "MEDICAMENTO\nE INSUMOS", "FORMA\nFARMACEUTICA", "CONCEN-\nTRACION", "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre", "TOTAL"};
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

            for (int i = pag * num + 0; i < pag * num + num && i < listImm.size(); i++) {

                Medicamentos datosI = (Medicamentos) listImm.get(i);

                cell = new PdfPCell(new Phrase(Integer.toString(i + 1), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(datosI.getCodsumi(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((datosI.getMedicamento().length() > 31) ? datosI.getMedicamento().substring(0, 31) : datosI.getMedicamento(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((datosI.getForma_far().length() > 4) ? datosI.getForma_far().substring(0, 4) : datosI.getForma_far(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((datosI.getConcentra().length() > 9) ? datosI.getConcentra().substring(0, 9) : datosI.getConcentra(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                int sums = 0;
                for (int x = 0; x < matriz.length; x++) {
                    if (datosI.getId_medicamento() == matriz[x][0]) {

                        cell = new PdfPCell(new Phrase(df.format(matriz[x][1]), DATO_FONT));
                        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                        table.addCell(cell);
                        sums += matriz[x][1];

                        cell = new PdfPCell(new Phrase(df.format(matriz[x][2]), DATO_FONT));
                        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                        table.addCell(cell);
                        sums += matriz[x][2];

                        cell = new PdfPCell(new Phrase(df.format(matriz[x][3]), DATO_FONT));
                        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                        table.addCell(cell);
                        sums += matriz[x][3];

                        cell = new PdfPCell(new Phrase(df.format(matriz[x][4]), DATO_FONT));
                        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                        table.addCell(cell);
                        sums += matriz[x][4];

                        cell = new PdfPCell(new Phrase(df.format(matriz[x][5]), DATO_FONT));
                        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                        table.addCell(cell);
                        sums += matriz[x][5];

                        cell = new PdfPCell(new Phrase(df.format(matriz[x][6]), DATO_FONT));
                        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                        table.addCell(cell);
                        sums += matriz[x][6];

                        cell = new PdfPCell(new Phrase(df.format(matriz[x][7]), DATO_FONT));
                        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                        table.addCell(cell);
                        sums += matriz[x][7];

                        cell = new PdfPCell(new Phrase(df.format(matriz[x][8]), DATO_FONT));
                        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                        table.addCell(cell);
                        sums += matriz[x][8];

                        cell = new PdfPCell(new Phrase(df.format(matriz[x][9]), DATO_FONT));
                        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                        table.addCell(cell);
                        sums += matriz[x][9];

                        cell = new PdfPCell(new Phrase(df.format(matriz[x][10]), DATO_FONT));
                        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                        table.addCell(cell);
                        sums += matriz[x][10];

                        cell = new PdfPCell(new Phrase(df.format(matriz[x][11]), DATO_FONT));
                        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                        table.addCell(cell);
                        sums += matriz[x][11];

                        cell = new PdfPCell(new Phrase(df.format(matriz[x][12]), DATO_FONT));
                        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                        table.addCell(cell);
                        sums += matriz[x][12];
                    }
                }

                cell = new PdfPCell(new Phrase(df.format(sums), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setGrayFill(0.9f);
                table.addCell(cell);

            }

            for (int i = listSaldos.size(); i < pag * num + num; i++) {
                cell = new PdfPCell(new Phrase(Integer.toString(i + 1), DATO_FONT_P));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(cell);
                for (int j = 1; j < 18; j++) {
                    cell = new PdfPCell(new Phrase("", DATO_FONT_P));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);
                }
            }

            cell = new PdfPCell(new Phrase("Elaborado por : " + dato.getNombres(), DATO_FONT));
            cell.setColspan(4);
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Pagina No.  " + (pag + 1), TITULO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(10);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Fecha de emision : " + format(new Date(), "dd/MM/yyyy HH:mm"), DATO_FONT));
            cell.setColspan(4);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setBorder(Rectangle.NO_BORDER);
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
