package org.ayaic.web.administrarcobranza.reporteeconomico;

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
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.Rubros;
//import web.administrarcobranza.reportefarmacia.codigoControl;

public class ReporteEconomicoHGPDF extends AbstractPdfView {

    private static final Font TITULO_FONT = new Font(Font.HELVETICA, 12, Font.BOLD, Color.black);
    private static final Font CABEZA_COLUMNA_FONT = new Font(Font.HELVETICA, 8, Font.ITALIC, Color.black);
    private static final Font DATO_FONT = new Font(Font.HELVETICA, 8, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_A = new Font(Font.HELVETICA, 10, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_BOLD = new Font(Font.HELVETICA, 8, Font.BOLD, Color.black);
    private static final int MARGIN = 32;

    protected void buildPdfMetadata(Map model, Document document, HttpServletRequest request) {
        document.setPageSize(PageSize.LEGAL.rotate());
        document.setMargins(50, 30, 10, 20);
    }

    protected void buildPdfDocument(Map model, Document document, PdfWriter writer, HttpServletRequest req, HttpServletResponse resp) throws BadElementException, IOException, DocumentException {

        java.util.List lCobros = (java.util.List) model.get("listaCobros");
        java.util.List listaFar = (java.util.List) model.get("listaCobrosFar");
        java.util.List lRubros = (java.util.List) model.get("listaRubros");
        java.util.List listaCobrosOtros = (java.util.List) model.get("listaCobrosOtros");

        Clientes dato = (Clientes) model.get("dato");
        Pacientes persona = (Pacientes) model.get("persona");
        DecimalFormat df = new DecimalFormat("#,##0.00");
        DecimalFormat df2 = new DecimalFormat("#,##0");
        // coloca el titulo de la pagina
        Image escudo = Image.getInstance("/opt/imagenes/lescudo.bmp");
        Paragraph p = new Paragraph("MOVIMIENTO ECONOMICO RECAUDACIONES HGSJDD\n", new Font(Font.HELVETICA, 14, Font.BOLDITALIC, new Color(0, 0, 0)));
        p.setAlignment(Element.ALIGN_CENTER);
        //Image sumi = Image.getInstance("/opt/imagenes/sumi.bmp");

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
        int[] fmwidths = {50, 50}; // percentage
        table1.setWidths(fmwidths);
        table1.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("Establecimiento : " + dato.getEstablecimiento(), DATO_FONT));
        cell.setBorder(Rectangle.NO_BORDER);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("Municipio : " + dato.getLocalidad(), DATO_FONT));
        cell.setBorder(Rectangle.NO_BORDER);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("Red : " + dato.getRed(), DATO_FONT));
        cell.setBorder(Rectangle.NO_BORDER);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("SEDES : " + dato.getDepartamento() + "      Localidad:....................", DATO_FONT));
        cell.setBorder(Rectangle.NO_BORDER);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("Fecha : " + format(persona.getFecha_ini(), "dd/MM/yyyy") + " a " + format(persona.getFecha_fin(), "dd/MM/yyyy"), DATO_FONT));
        cell.setBorder(Rectangle.NO_BORDER);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("subsector : ...........  ", DATO_FONT));
        cell.setBorder(Rectangle.NO_BORDER);
        table1.addCell(cell);

        document.add(table1);

        int NumColumns = 22;
        PdfPTable table = new PdfPTable(NumColumns);
        int[] fwidths = {3, 10, 7, 6, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5};
        table.setWidths(fwidths);
        table.setWidthPercentage(100);

        String sCampos[] = {"No", "Fecha", "Num ClaDoc"};
        // coloca la cabecera de titulos
        for (int i = 0; i < 3; i++) {
            cell = new PdfPCell(new Phrase(sCampos[i], CABEZA_COLUMNA_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setGrayFill(0.8f);
            table.addCell(cell);
        }

        for (int i = 0; i < lRubros.size(); i++) {
            Rubros datoc = (Rubros) lRubros.get(i);
            cell = new PdfPCell(new Phrase(datoc.getRubro(), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setGrayFill(0.8f);
            table.addCell(cell);
        }

        // coloca el detalle de loS datos
        float sumatotal[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        float sumatot = 0, sumatotf = 0;
        table.setHeaderRows(1);
        /////////Cobros de farmacia
        for (int m = 0; m < listaFar.size(); m++) {

            Pacientes datopac = (Pacientes) listaFar.get(m);

            cell = new PdfPCell(new Phrase(Integer.toString(m + 1), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(format(datopac.getFec_registro(), "dd/MM/yy HH:mm"), DATO_FONT_A));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(datopac.getNum_cladoc(), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            for (int j = 0; j < 18; j++) {
                Rubros datoc = (Rubros) lRubros.get(j);
                cell = new PdfPCell(new Phrase("", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
            }

            cell = new PdfPCell(new Phrase(Double.toString(datopac.getPrecio_total()), DATO_FONT_A));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            sumatotf += datopac.getPrecio_total();

        }
        /////////fin farmacia

        for (int i = 0; i < listaCobrosOtros.size(); i++) {
            Pacientes datotro = (Pacientes) listaCobrosOtros.get(i);
            for (int k = 0; k < lCobros.size(); k++) {
                Pacientes datopac = (Pacientes) lCobros.get(k);

                if (datotro.getId_pedido() == datopac.getId_pedido()) {
                    cell = new PdfPCell(new Phrase(Integer.toString(i + 1), DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase(format(datopac.getFec_registro(), "dd/MM/yy HH:mm"), DATO_FONT_A));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase(datopac.getNum_cladoc(), DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);

                    switch (datotro.getId_rubro()) {
                        case 2:
                            for (int j = 0; j < lRubros.size(); j++) {
                                Rubros datoc = (Rubros) lRubros.get(j);
                                if (datoc.getId_rubro() == datopac.getId_rubro()) {
                                    cell = new PdfPCell(new Phrase(df2.format(datotro.getPrecio_total()), DATO_FONT_A));  ///CobrosOtros
                                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    table.addCell(cell);
                                    sumatotal[j] += datotro.getPrecio_total();
                                } else {
                                    cell = new PdfPCell(new Phrase("", DATO_FONT));
                                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    table.addCell(cell);
                                }
                            }
                            break;
                        case 3:
                            for (int j = 0; j < lRubros.size(); j++) {
                                Rubros datoc = (Rubros) lRubros.get(j);
                                if (datoc.getId_rubro() == datopac.getId_rubro()) {
                                    cell = new PdfPCell(new Phrase(df2.format(datotro.getPrecio_total()), DATO_FONT_A));  ///CobrosOtros
                                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    table.addCell(cell);
                                    sumatotal[j] += datotro.getPrecio_total();
                                } else {
                                    cell = new PdfPCell(new Phrase("", DATO_FONT));
                                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    table.addCell(cell);
                                }
                            }
                            break;
                        case 4:
                            for (int j = 0; j < lRubros.size(); j++) {
                                Rubros datoc = (Rubros) lRubros.get(j);
                                if (datoc.getId_rubro() == datopac.getId_rubro()) {
                                    cell = new PdfPCell(new Phrase(df2.format(datotro.getPrecio_total()), DATO_FONT_A));  ///CobrosOtros
                                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    table.addCell(cell);
                                    sumatotal[j] += datotro.getPrecio_total();
                                } else {
                                    cell = new PdfPCell(new Phrase("", DATO_FONT));
                                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    table.addCell(cell);
                                }
                            }
                            break;
                        case 5:
                            for (int j = 0; j < lRubros.size(); j++) {
                                Rubros datoc = (Rubros) lRubros.get(j);
                                if (datoc.getId_rubro() == datopac.getId_rubro()) {
                                    cell = new PdfPCell(new Phrase(df2.format(datotro.getPrecio_total()), DATO_FONT_A));  ///CobrosOtros
                                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    table.addCell(cell);
                                    sumatotal[j] += datotro.getPrecio_total();
                                } else {
                                    cell = new PdfPCell(new Phrase("", DATO_FONT));
                                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    table.addCell(cell);
                                }
                            }
                            break;
                        case 6:
                            for (int j = 0; j < lRubros.size(); j++) {
                                Rubros datoc = (Rubros) lRubros.get(j);
                                if (datoc.getId_rubro() == datopac.getId_rubro()) {
                                    cell = new PdfPCell(new Phrase(df2.format(datotro.getPrecio_total()), DATO_FONT_A));  ///CobrosOtros
                                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    table.addCell(cell);
                                    sumatotal[j] += datotro.getPrecio_total();
                                } else {
                                    cell = new PdfPCell(new Phrase("", DATO_FONT));
                                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    table.addCell(cell);
                                }
                            }
                            break;
                        case 7:
                            for (int j = 0; j < lRubros.size(); j++) {
                                Rubros datoc = (Rubros) lRubros.get(j);
                                if (datoc.getId_rubro() == datopac.getId_rubro()) {
                                    cell = new PdfPCell(new Phrase(df2.format(datotro.getPrecio_total()), DATO_FONT_A));  ///CobrosOtros
                                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    table.addCell(cell);
                                    sumatotal[j] += datotro.getPrecio_total();
                                } else {
                                    cell = new PdfPCell(new Phrase("", DATO_FONT));
                                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    table.addCell(cell);
                                }
                            }
                            break;
                        case 8:
                            for (int j = 0; j < lRubros.size(); j++) {
                                Rubros datoc = (Rubros) lRubros.get(j);
                                if (datoc.getId_rubro() == datopac.getId_rubro()) {
                                    cell = new PdfPCell(new Phrase(df2.format(datotro.getPrecio_total()), DATO_FONT_A));  ///CobrosOtros
                                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    table.addCell(cell);
                                    sumatotal[j] += datotro.getPrecio_total();
                                } else {
                                    cell = new PdfPCell(new Phrase("", DATO_FONT));
                                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    table.addCell(cell);
                                }
                            }
                            break;
                        case 9:
                            for (int j = 0; j < lRubros.size(); j++) {
                                Rubros datoc = (Rubros) lRubros.get(j);
                                if (datoc.getId_rubro() == datopac.getId_rubro()) {
                                    cell = new PdfPCell(new Phrase(df2.format(datotro.getPrecio_total()), DATO_FONT_A));  ///CobrosOtros
                                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    table.addCell(cell);
                                    sumatotal[j] += datotro.getPrecio_total();
                                } else {
                                    cell = new PdfPCell(new Phrase("", DATO_FONT));
                                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    table.addCell(cell);
                                }
                            }
                            break;
                        case 10:
                            for (int j = 0; j < lRubros.size(); j++) {
                                Rubros datoc = (Rubros) lRubros.get(j);
                                if (datoc.getId_rubro() == datopac.getId_rubro()) {
                                    cell = new PdfPCell(new Phrase(df2.format(datotro.getPrecio_total()), DATO_FONT_A));  ///CobrosOtros
                                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    table.addCell(cell);
                                    sumatotal[j] += datotro.getPrecio_total();
                                } else {
                                    cell = new PdfPCell(new Phrase("", DATO_FONT));
                                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    table.addCell(cell);
                                }
                            }
                            break;
                        case 11:
                            for (int j = 0; j < lRubros.size(); j++) {
                                Rubros datoc = (Rubros) lRubros.get(j);
                                if (datoc.getId_rubro() == datopac.getId_rubro()) {
                                    cell = new PdfPCell(new Phrase(df2.format(datotro.getPrecio_total()), DATO_FONT_A));  ///CobrosOtros
                                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    table.addCell(cell);
                                    sumatotal[j] += datotro.getPrecio_total();
                                } else {
                                    cell = new PdfPCell(new Phrase("", DATO_FONT));
                                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    table.addCell(cell);
                                }
                            }
                            break;
                        case 12:
                            for (int j = 0; j < lRubros.size(); j++) {
                                Rubros datoc = (Rubros) lRubros.get(j);
                                if (datoc.getId_rubro() == datopac.getId_rubro()) {
                                    cell = new PdfPCell(new Phrase(df2.format(datotro.getPrecio_total()), DATO_FONT_A));  ///CobrosOtros
                                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    table.addCell(cell);
                                    sumatotal[j] += datotro.getPrecio_total();
                                } else {
                                    cell = new PdfPCell(new Phrase("", DATO_FONT));
                                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    table.addCell(cell);
                                }
                            }
                            break;
                        case 13:
                            for (int j = 0; j < lRubros.size(); j++) {
                                Rubros datoc = (Rubros) lRubros.get(j);
                                if (datoc.getId_rubro() == datopac.getId_rubro()) {
                                    cell = new PdfPCell(new Phrase(df2.format(datotro.getPrecio_total()), DATO_FONT_A));  ///CobrosOtros
                                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    table.addCell(cell);
                                    sumatotal[j] += datotro.getPrecio_total();
                                } else {
                                    cell = new PdfPCell(new Phrase("", DATO_FONT));
                                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    table.addCell(cell);
                                }
                            }
                            break;
                        case 14:
                            for (int j = 0; j < lRubros.size(); j++) {
                                Rubros datoc = (Rubros) lRubros.get(j);
                                if (datoc.getId_rubro() == datopac.getId_rubro()) {
                                    cell = new PdfPCell(new Phrase(df2.format(datotro.getPrecio_total()), DATO_FONT_A));  ///CobrosOtros
                                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    table.addCell(cell);
                                    sumatotal[j] += datotro.getPrecio_total();
                                } else {
                                    cell = new PdfPCell(new Phrase("", DATO_FONT));
                                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    table.addCell(cell);
                                }
                            }
                            break;
                        case 15:
                            for (int j = 0; j < lRubros.size(); j++) {
                                Rubros datoc = (Rubros) lRubros.get(j);
                                if (datoc.getId_rubro() == datopac.getId_rubro()) {
                                    cell = new PdfPCell(new Phrase(df2.format(datotro.getPrecio_total()), DATO_FONT_A));  ///CobrosOtros
                                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    table.addCell(cell);
                                    sumatotal[j] += datotro.getPrecio_total();
                                } else {
                                    cell = new PdfPCell(new Phrase("", DATO_FONT));
                                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    table.addCell(cell);
                                }
                            }
                            break;
                        case 16:
                            for (int j = 0; j < lRubros.size(); j++) {
                                Rubros datoc = (Rubros) lRubros.get(j);
                                if (datoc.getId_rubro() == datopac.getId_rubro()) {
                                    cell = new PdfPCell(new Phrase(df2.format(datotro.getPrecio_total()), DATO_FONT_A));  ///CobrosOtros
                                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    table.addCell(cell);
                                    sumatotal[j] += datotro.getPrecio_total();
                                } else {
                                    cell = new PdfPCell(new Phrase("", DATO_FONT));
                                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    table.addCell(cell);
                                }
                            }
                            break;
                        case 17:
                            for (int j = 0; j < lRubros.size(); j++) {
                                Rubros datoc = (Rubros) lRubros.get(j);
                                if (datoc.getId_rubro() == datopac.getId_rubro()) {
                                    cell = new PdfPCell(new Phrase(df2.format(datotro.getPrecio_total()), DATO_FONT_A));  ///CobrosOtros
                                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    table.addCell(cell);
                                    sumatotal[j] += datotro.getPrecio_total();
                                } else {
                                    cell = new PdfPCell(new Phrase("", DATO_FONT));
                                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    table.addCell(cell);
                                }
                            }
                            break;
                        case 18:
                            for (int j = 0; j < lRubros.size(); j++) {
                                Rubros datoc = (Rubros) lRubros.get(j);
                                if (datoc.getId_rubro() == datopac.getId_rubro()) {
                                    cell = new PdfPCell(new Phrase(df2.format(datotro.getPrecio_total()), DATO_FONT_A));  ///CobrosOtros
                                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    table.addCell(cell);
                                    sumatotal[j] += datotro.getPrecio_total();
                                } else {
                                    cell = new PdfPCell(new Phrase("", DATO_FONT));
                                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    table.addCell(cell);
                                }
                            }
                            break;
                        case 19:
                            for (int j = 0; j < lRubros.size(); j++) {
                                Rubros datoc = (Rubros) lRubros.get(j);
                                if (datoc.getId_rubro() == datopac.getId_rubro()) {
                                    cell = new PdfPCell(new Phrase(df2.format(datotro.getPrecio_total()), DATO_FONT_A));  ///CobrosOtros
                                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    table.addCell(cell);
                                    sumatotal[j] += datotro.getPrecio_total();
                                } else {
                                    cell = new PdfPCell(new Phrase("", DATO_FONT));
                                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    table.addCell(cell);
                                }
                            }
                            break;
                    }
                }
            }
            sumatot += datotro.getPrecio_total();
        }
        cell = new PdfPCell(new Phrase("Totales Parciales  Bs.", DATO_FONT_A));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setColspan(3);
        table.addCell(cell);

        for (int i = 0; i < 18; i++) {
            cell = new PdfPCell(new Phrase(df2.format(sumatotal[i]), DATO_FONT_A));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

        }

        cell = new PdfPCell(new Phrase(df.format(sumatotf), DATO_FONT_A));  ////la suma general de farmacia
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("GRAN TOTAL  Bs.", DATO_FONT_A));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setColspan(20);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(df.format(sumatot + sumatotf), DATO_FONT_A));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setColspan(2);
        table.addCell(cell);

        document.add(table);

        n2t numero = new n2t();

        p = new Paragraph("Monto Total Literal : Son   " + numero.convertirLetras((int) (sumatot + sumatotf)) + "    " + Integer.toString((int) ((sumatot - (int) sumatot) * 100)) + "/100  Bolivianos", new Font(Font.HELVETICA, 10, Font.NORMAL, new Color(0, 0, 0)));
        document.add(p);
        p = new Paragraph("Fecha de emision : " + format(new Date(), "dd/MM/yyyy"), DATO_FONT);
        document.add(p);
        p = new Paragraph("Nombre completo responsable del llenado : " + dato.getNombres() + "         Firma:_______________________", DATO_FONT);
        document.add(p);
        p = new Paragraph("El arriba firmante certifica la veracidad de la informacion declarada en este formulario legal.", DATO_FONT);
        document.add(p);
        p = new Paragraph("      - Este documento debe tener el sello del establecimiento.                                                               Sello", DATO_FONT);
        document.add(p);

    }

    public static String format(Date dia, String formato) {
        if (dia == null) {
            return "";
        }
        SimpleDateFormat formatDate = new SimpleDateFormat(formato);
        return formatDate.format(dia);
    }
}
