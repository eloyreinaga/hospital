package org.ayaic.web.administrarmedicamentos;

import java.awt.Color;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.view.document.AbstractPdfView;
import java.util.*;
import java.text.*;
import com.lowagie.text.*;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Localidades;
import org.ayaic.domain.Medicamentos;
import org.ayaic.domain.Recetas;

public class ListarConsultaPDF extends AbstractPdfView {

    private static final Font TITULO_FONT = new Font(Font.HELVETICA, 12, Font.BOLD, Color.black);
    private static final Font CABEZA_COLUMNA_FONT = new Font(Font.HELVETICA, 8, Font.ITALIC, Color.black);
    private static final Font DATO_FONT = new Font(Font.HELVETICA, 8, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_R = new Font(Font.HELVETICA, 8, Font.NORMAL, Color.red);
    private static final Font DATO_FONT_BOLD = new Font(Font.HELVETICA, 8, Font.BOLD, Color.black);
    private static final int MARGIN = 32;

    protected void buildPdfMetadata(Map model, Document document, HttpServletRequest request) {
        document.setPageSize(PageSize.LETTER);
        document.setMargins(20, 20, 5, 10);
    }

    protected void buildPdfDocument(Map model, Document document, PdfWriter writer, HttpServletRequest req, HttpServletResponse resp) throws Exception {

        java.util.List kardexMed = (java.util.List) model.get("datos");
        Medicamentos dato = (Medicamentos) model.get("dato");
        Clientes cliente = (Clientes) model.get("cliente");
        Localidades datoestab = (Localidades) model.get("datoestab");
        //String titulo = request.getParameter("descripcion");
        DecimalFormat df = new DecimalFormat("###,##0");
        String dfarma = (String) model.get("Farma");
        String fechaq = (String) model.get("fecha");
        int count_e = 0, count_s = 0, count_a = 0;

        String tipo;
        String sCampos[] = {"FECHA", "ENTRADAS", "SALIDAS", "AJUSTES(+/-)", "SALDO", "No. Otro", "No. \nOrden", "N.- y\nClave Doc", "NOMBRES", "Recib:\nExped a:", "COST\nUNIT", "SALDO\nValorizado", "FECHA\nVTO", "No\nLOTE"};

        if (cliente.getCod_esta() == 200010) {
            String sCampos2[] = {"FECHA", "ENTRADAS", "SALIDAS", "AJUSTES(+/-)", "SALDO", "Num.rect\nTAG", "Usuario", "N.- y\nClave Doc", "NOMBRES", "Recib:\nExped a:", "COST\nUNIT", "SALDO\nValorizado", "FECHA\nVTO", "No\nLOTE"};
        }

        float sumatotal = 0;
        int sumacant = 0;

        if (kardexMed.size() == 0) {
            Paragraph p = new Paragraph("No existe Datos", new Font(Font.HELVETICA, 22, Font.BOLDITALIC, new Color(0, 0, 0)));
            document.add(p);
        }

        int una;
        if (kardexMed.size() % 47 == 0) {
            una = 0;
        } else {
            una = 1;
        }

        for (int pag = 0; pag < kardexMed.size() / 47 + una; pag++) {

            Image escudo = Image.getInstance("/opt/imagenes/lescudo.bmp");
            Paragraph p = new Paragraph("Formulario de Registro de existencias\n(Kardex Valorado)", new Font(Font.HELVETICA, 14, Font.BOLDITALIC, new Color(0, 0, 0)));
            p.setAlignment(Element.ALIGN_CENTER);;

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

            PdfPTable table1 = new PdfPTable(2);
            int[] fmwidths = {60, 40}; // percentage
            table1.setWidths(fmwidths);
            table1.setWidthPercentage(100);

            cell = new PdfPCell(new Phrase("Nombre del Producto : " + dato.getMedicamento(), DATO_FONT));
            cell.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell);

            cell = new PdfPCell(new Phrase("Codigo : " + dato.getCodsumi() + "         Codigo Int: " + dato.getId_medicamento(), DATO_FONT));
            cell.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell);

            cell = new PdfPCell(new Phrase("Forma Farmaceutica : " + dato.getForma_far(), DATO_FONT));
            cell.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell);

            cell = new PdfPCell(new Phrase("Ubicacion : " + dato.getUbicacion(), DATO_FONT));
            cell.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell);

            cell = new PdfPCell(new Phrase("Concentracion : " + dato.getConcentra(), DATO_FONT));
            cell.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell);

            cell = new PdfPCell(new Phrase("Datos Almacen: " + dfarma, DATO_FONT));
            cell.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell);

            document.add(table1);

            int NumColumns = 14;
            PdfPTable table = new PdfPTable(NumColumns);

            ///// {"FECHA", "ENTRADAS", "SALIDAS", "AJUSTES(+/-)", "SALDO", "No. Doc.","Otro", "N.- y\nClave Doc", "NOMBRES", "Recib:\nExpedido a:", "COST\nUNIT","SALDO\nValorizado","FECHA\nVTO","No\nLOTE"};
            int[] fwidths = {20, 14, 14, 14, 20, 18, 24, 28, 48, 20, 15, 20, 19, 25}; // percentage
            table.setWidths(fwidths);
            table.setWidthPercentage(100);

            // coloca la cabecera de titulos
            for (int i = 0; i < sCampos.length; i++) {
                cell = new PdfPCell(new Phrase(sCampos[i], CABEZA_COLUMNA_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setGrayFill(0.8f);
                table.addCell(cell);
            }
            // coloca el detalle de los datos
            table.setHeaderRows(1);

            for (int i = pag * 47 + 0; i < pag * 47 + 47 && i < kardexMed.size(); i++) {

                Recetas dator = (Recetas) kardexMed.get(i);

                if (i == 0) { ///&& dator.getEntrada()>0
                    cell = new PdfPCell(new Phrase(fechaq, DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setColspan(2);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase("", new Font(Font.HELVETICA, 7, Font.NORMAL, Color.black)));
                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase("", new Font(Font.HELVETICA, 7, Font.NORMAL, Color.black)));
                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase(df.format(dator.getEntrada() + dator.getSalida() - dator.getAjuste()), DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase("Saldo al " + fechaq, new Font(Font.HELVETICA, 7, Font.NORMAL, Color.black)));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setColspan(5);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase(Double.toString(dator.getPrecio_venta()), DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase(df.format(dator.getStock_f() * dator.getPrecio_venta()), DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase(format(dator.getFecha_ven(), "dd/MM/yy"), DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase(dator.getNro_lote(), DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);

                } else {

                    cell = new PdfPCell(new Phrase(format(dator.getFecha(), "dd/MM/yy"), DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);

                    //cell = new PdfPCell(new Phrase(Double.toString(dator.getEntrada()) , DATO_FONT));
                    cell = new PdfPCell(new Phrase((dator.getEntrada() != 0) ? df.format(dator.getEntrada()) : "", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                    count_e += dator.getEntrada();

                    cell = new PdfPCell(new Phrase((dator.getSalida() != 0) ? df.format(dator.getSalida()) : "", DATO_FONT));
                    if ((dator.getSalida() == 0 && dator.getEntrada() == 0 && dator.getAjuste() == 0)) {
                        cell = new PdfPCell(new Phrase((dator.getSalida() != 0) ? df.format(dator.getSalida()) : "0", DATO_FONT_R));
                    }
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                    count_s += dator.getSalida();

                    cell = new PdfPCell(new Phrase((dator.getAjuste() != 0) ? df.format(dator.getAjuste()) : "", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                    count_a += dator.getAjuste();

                    cell = new PdfPCell(new Phrase(df.format(dator.getStock_f()), DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);

                    if (dator.getId_factura() > 0) {///esto cuando hay factura, erpo tambien guarda el autonumertable de ajustes
                         if (dator.getAjuste()!= 0) {            
                             cell = new PdfPCell(new Phrase(Integer.toString(dator.getId_dispensa()) + "_" + dator.getCadena2(), new Font(Font.HELVETICA, 5, Font.NORMAL, Color.black)));
                         }else{
                             cell = new PdfPCell(new Phrase(Integer.toString(dator.getId_factura()), new Font(Font.HELVETICA, 6, Font.NORMAL, Color.black))); //No. Otro    
                         }                     
                    } else {
                        cell = new PdfPCell(new Phrase(Integer.toString(dator.getId_persona()) + "_" + dator.getCadena2(), new Font(Font.HELVETICA, 5, Font.NORMAL, Color.black)));
                    }
                    if (datoestab.getCod_esta() == 200010) {
                        cell = new PdfPCell(new Phrase(Integer.toString(dator.getNum_recetak()) + "__" + dator.getCadena3(), new Font(Font.HELVETICA, 6, Font.NORMAL, Color.black)));
                    }
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                    
                    if (dator.getNit().equals("0") || dator.getNit()== null ) {
                        cell = new PdfPCell(new Phrase(Integer.toString(dator.getNum_recetak()) + "__" + dator.getId_factura(), new Font(Font.HELVETICA, 6, Font.NORMAL, Color.black)));
                    }else{
                        cell = new PdfPCell(new Phrase(dator.getNit(), new Font(Font.HELVETICA, 6, Font.NORMAL, Color.black)));
                    }
                    if (datoestab.getCod_esta() == 200010) {
                        cell = new PdfPCell(new Phrase(Integer.toString(dator.getId_persona()) + "_" + dator.getCadena2(), new Font(Font.HELVETICA, 6, Font.NORMAL, Color.black)));
                    }
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase(dator.getNum_cladoc(), new Font(Font.HELVETICA, 6, Font.NORMAL, Color.black)));
                    if (datoestab.getCod_esta() == 200010) {
                        cell = new PdfPCell(new Phrase(dator.getNum_cladoc() + "_" + dator.getCadena5(), new Font(Font.HELVETICA, 6, Font.NORMAL, Color.black)));
                    }
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase(dator.getMedico(), new Font(Font.HELVETICA, 5, Font.NORMAL, Color.black)));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);

                    if (dator.getExpedido().equals("S")) {
                        tipo = "SUMI";
                    } else if (dator.getExpedido().equals("V")) {
                        tipo = "VENTA";
                    } else if (dator.getExpedido().equals("P")) {
                        tipo = dator.getId_programa() + ".Prog";
                    } else {
                        tipo = "XXXX.";
                    }
                    cell = new PdfPCell(new Phrase(tipo, DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase(Double.toString(dator.getPrecio_venta()), DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase(df.format(dator.getStock_f() * dator.getPrecio_venta()), DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase(format(dator.getFecha_ven(), "dd/MM/yy"), DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase(dator.getNro_lote(), DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);
                }

            }

            cell = new PdfPCell(new Phrase("Totales", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setGrayFill(0.8f);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(df.format(count_e), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setGrayFill(0.8f);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(df.format(count_s), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setGrayFill(0.8f);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(df.format(count_a), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setGrayFill(0.8f);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setGrayFill(0.8f);
            cell.setColspan(9);
            table.addCell(cell);

            for (int i = kardexMed.size(); i < pag * 47 + 47; i++) {
                cell = new PdfPCell(new Phrase(" ", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBorder(Rectangle.NO_BORDER);
                table.addCell(cell);
                for (int j = 1; j < 14; j++) {
                    cell = new PdfPCell(new Phrase(" ", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setBorder(Rectangle.NO_BORDER);
                    table.addCell(cell);
                }
            }

            cell = new PdfPCell(new Phrase(format(new Date(), "dd/MM/yyyy HH:mm"), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(3);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Pagina No.  " + (pag + 1), TITULO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(8);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(cliente.getNombres(), new Font(Font.HELVETICA, 6, Font.NORMAL, new Color(0, 0, 0))));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(3);
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
