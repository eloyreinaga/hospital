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
import org.ayaic.domain.Farmacias;
import org.ayaic.domain.Medicamentos;
import org.ayaic.domain.Personas;

public class ListarInventarioAlmaFechaCNSPDF extends AbstractPdfView {

    private static final Font TITULO_FONT = new Font(Font.HELVETICA, 12, Font.BOLD, Color.black);
    private static final Font CABEZA_COLUMNA_FONT = new Font(Font.HELVETICA, 8, Font.ITALIC, Color.black);
    private static final Font DATO_FONT = new Font(Font.HELVETICA, 10, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_BOLD = new Font(Font.HELVETICA, 8, Font.BOLD, Color.black);
    private static final int MARGIN = 32;

    protected void buildPdfMetadata(Map model, Document document, HttpServletRequest request) {
        document.setPageSize(PageSize.LETTER.rotate());
    }

    protected void buildPdfDocument(Map model, Document document, PdfWriter writer, HttpServletRequest req, HttpServletResponse resp) throws Exception {

        java.util.List listamed = (java.util.List) model.get("listarMedicamentos");
        java.util.List listamedgral = (java.util.List) model.get("listarMedicamentosGral");
        java.util.List listafar = (java.util.List) model.get("listafarAsig");

        Clientes dato = (Clientes) model.get("dato");
        Personas persona = (Personas) model.get("persona");
        String dia = (String) model.get("dia");
        String mes = (String) model.get("mes");
        String gestion = (String) model.get("gestion");
        String fecha = (String) model.get("dFechafin1");
        String fecha2 = (String) model.get("dFechafin2");
        DecimalFormat df = new DecimalFormat("###,##0");
        DecimalFormat dfx = new DecimalFormat("###,##0.0");
        Date hoy = new Date();
        double sumatot = 0;

        int una;
        if (listamedgral.size() % 30 == 0) {
            una = 0;
        } else {
            una = 1;
        }

        for (int pag = 0; pag < listamedgral.size() / 30 + una; pag++) {

            Image escudo = Image.getInstance("/opt/imagenes/lescudo.bmp");
            Paragraph p = new Paragraph("INVETARIO MEDICAMENTOS DETALLADO FARMACIAS", new Font(Font.HELVETICA, 14, Font.BOLDITALIC, new Color(0, 0, 0)));
            p.setAlignment(Element.ALIGN_CENTER);

            PdfPCell cell;

            PdfPTable tablex = new PdfPTable(3);
            int[] fxwidths = {15, 70, 15}; // percentage
            tablex.setWidths(fxwidths);
            tablex.setWidthPercentage(100);

            cell = new PdfPCell(new Phrase("", DATO_FONT));
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

            PdfPTable table1 = new PdfPTable(3);
            int[] fmwidths = {10, 50, 50}; // percentage
            table1.setWidths(fmwidths);
            table1.setWidthPercentage(100);

            cell = new PdfPCell(new Phrase("", DATO_FONT));
            cell.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell);

            cell = new PdfPCell(new Phrase("Establecimiento : " + dato.getEstablecimiento(), DATO_FONT));
            cell.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell);

            cell = new PdfPCell(new Phrase("Municipio : " + dato.getLocalidad(), DATO_FONT));
            cell.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell);

            cell = new PdfPCell(new Phrase("", DATO_FONT));
            cell.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell);

            cell = new PdfPCell(new Phrase("Reporte al :  " + dia + "-" + mes + "-" + gestion + "                    Fecha Imp.: " + format(hoy, "dd/MM/yy HH:mm"), DATO_FONT));
            cell.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell);

            cell = new PdfPCell(new Phrase("Responsable   : " + persona.getNombres(), DATO_FONT));
            cell.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell);

            document.add(table1);

            int num = listafar.size();
            int NumColumns = 5 + num + 1;
            PdfPTable table = new PdfPTable(NumColumns);

            switch (num) {
                case 1:
                    int[] fwidths1 = {8, 20, 40, 20, 20, 20, 20};
                    table.setWidths(fwidths1);
                    break;
                case 2:
                    int[] fwidths2 = {8, 20, 40, 20, 20, 20, 20, 20};
                    table.setWidths(fwidths2);
                    break;
                case 3:
                    int[] fwidths3 = {8, 20, 40, 20, 20, 20, 20, 20, 20};
                    table.setWidths(fwidths3);
                    break;
                case 4:
                    int[] fwidths4 = {8, 20, 40, 20, 20, 20, 20, 20, 20, 20};
                    table.setWidths(fwidths4);
                    break;
                case 5:
                    int[] fwidths5 = {8, 20, 40, 20, 20, 20, 20, 20, 20, 20, 20};
                    table.setWidths(fwidths5);
                    break;
                case 6:
                    int[] fwidths6 = {8, 20, 40, 20, 20, 20, 20, 20, 20, 20, 20, 20};
                    table.setWidths(fwidths6);
                    break;
                case 7:
                    int[] fwidths7 = {8, 20, 40, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20};
                    table.setWidths(fwidths7);
                    break;
                case 8:
                    int[] fwidths8 = {8, 20, 40, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20};
                    table.setWidths(fwidths8);
                    break;
                case 9:
                    int[] fwidths9 = {8, 20, 40, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20};
                    table.setWidths(fwidths9);
                    break;
                case 10:
                    int[] fwidths10 = {8, 20, 40, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20};
                    table.setWidths(fwidths10);
                    break;
                default:
                    int[] fwidths0 = {8, 20, 40, 20, 20};
                    table.setWidths(fwidths0);
                    break;
            }

            table.setWidthPercentage(100);

            String sCampos[] = {"Nro", "Codigo", "Medicamnto", "Forma Far", "Concentracion"};
            // coloca la cabecera de titulos

            for (int i = 0; i < NumColumns - num - 1; i++) {
                cell = new PdfPCell(new Phrase(sCampos[i], CABEZA_COLUMNA_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setGrayFill(0.8f);
                table.addCell(cell);
            }
            for (int i = 0; i < listafar.size(); i++) {
                Farmacias datof = (Farmacias) listafar.get(i);
                cell = new PdfPCell(new Phrase(datof.getFarmacia(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setGrayFill(0.8f);
                table.addCell(cell);
            }
            cell = new PdfPCell(new Phrase("Total", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setGrayFill(0.8f);
            table.addCell(cell);
            // coloca el detalle de loS datos
            for (int i = pag * 30 + 0; i < pag * 30 + 30 && i < listamedgral.size(); i++) {

                Medicamentos datos = (Medicamentos) listamedgral.get(i);

                cell = new PdfPCell(new Phrase(Integer.toString(i + 1), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(datos.getCodsumi(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((datos.getMedicamento().length() > 20) ? datos.getMedicamento().substring(0, 20) : datos.getMedicamento(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((datos.getForma_far().length() > 3) ? datos.getForma_far().substring(0, 3) : datos.getForma_far(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase((datos.getConcentra().length() > 9) ? datos.getConcentra().substring(0, 9) : datos.getConcentra(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                sumatot = 0;
                for (int k = 0; k < listamed.size(); k++) {
                    Medicamentos listamedi = (Medicamentos) listamed.get(k);
                    for (int l = 0; l < listafar.size(); l++) {
                        Farmacias listafa = (Farmacias) listafar.get(l);
                        if (listamedi.getId_farmacia() == listafa.getId_farmacia() && datos.getId_medicamento() == listamedi.getId_medicamento()) {
                            cell = new PdfPCell(new Phrase(df.format(listamedi.getB1() + listamedi.getMes() + listamedi.getGestion()), DATO_FONT));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                            sumatot += listamedi.getB1() + listamedi.getMes() + listamedi.getGestion();
                        }
                    }
                }

                cell = new PdfPCell(new Phrase(df.format(sumatot), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
            }

            cell = new PdfPCell(new Phrase("Elaborado por : " + dato.getNombres(), DATO_FONT));
            cell.setColspan(num);
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Fecha de emision : " + format(new Date(), "dd/MM/yyyy HH:mm"), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(num);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Pagina No.  " + (pag + 1), TITULO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(num);
            table.addCell(cell);

            PdfContentByte cb = writer.getDirectContent();
            //PdfContentByte cb1 = writer.getDirectContent();
            //BarcodeQRCode qrcode = new BarcodeQRCode(cod, 90, 90, null);
            //java.awt.Image qrImage = qrcode.createAwtImage(Color.black, Color.white);
            //Image finalImage = Image.getInstance(writer, escudo, 1);
            escudo.setAbsolutePosition(50, 50);
            cb.addImage(escudo, 50, 0, 0, 60, 30, 532, true);
            ///(imagen,tamañoAncho ,rotation_x ,angulorotation , amplitud-altura ,TamañoAlto ,PosicionX ,PosicionY(0=abajo, 280=arriba))

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
