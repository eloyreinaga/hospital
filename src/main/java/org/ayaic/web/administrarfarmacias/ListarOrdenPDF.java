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
import org.ayaic.domain.Cuadernos;
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.Recetas;

public class ListarOrdenPDF extends AbstractPdfView {

    private static final Font TITULO_FONT = new Font(Font.HELVETICA, 12, Font.BOLD, Color.black);
    private static final Font CABEZA_COLUMNA_FONT = new Font(Font.HELVETICA, 8, Font.ITALIC, Color.black);
    private static final Font DATO_FONT = new Font(Font.HELVETICA, 8, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_BOLD = new Font(Font.HELVETICA, 8, Font.BOLD, Color.black);
    private static final int MARGIN = 32;

    protected void buildPdfMetadata(Map model, Document document, HttpServletRequest request) {
        document.setPageSize(PageSize.LETTER);
        document.setMargins(10, 10, 10, 10);
    }

    protected void buildPdfDocument(Map model, Document document, PdfWriter writer, HttpServletRequest req, HttpServletResponse resp) throws DocumentException, IOException {

        Clientes dato = (Clientes) model.get("dato");
        Pacientes datoPaciente = (Pacientes) model.get("datos");
        Pacientes datoJefe = (Pacientes) model.get("datojef");
        java.util.List listaRecetas = (java.util.List) model.get("listarRecetas");
        java.util.List listaKardex = (java.util.List) model.get("listarKardex");
        java.util.List nombres = (java.util.List) model.get("nombres");
        java.util.List datosgral = (java.util.List) model.get("datosgral");
        String mes = (String) model.get("mes");
        String gestion = (String) model.get("gestion");
        DecimalFormat df = new DecimalFormat("###,##0");
        DecimalFormat dfx = new DecimalFormat("###,##0.00");
        Date fecha = new Date();
        String p;
        double total = 0, total2 = 0;
        int alto = 780;

        BaseFont bf = BaseFont.createFont("Helvetica", "Cp1252", false);
        PdfContentByte cb = writer.getDirectContent();

        Cuadernos gral = (Cuadernos) datosgral.get(0);  ////Configuracion de la impresion

        cb.beginText();
        cb.setFontAndSize(bf, gral.getSuma3());
        cb.setTextMatrix(gral.getSuma1(), alto - gral.getSuma2());
        cb.showText(Integer.toString(fecha.getDate()));
        cb.endText();

        cb.beginText();
        cb.setFontAndSize(bf, gral.getSuma6());
        cb.setTextMatrix(gral.getSuma4(), alto - gral.getSuma5());
        cb.showText(Integer.toString(fecha.getMonth() + 1));
        cb.endText();

        cb.beginText();
        cb.setFontAndSize(bf, gral.getSuma9());
        cb.setTextMatrix(gral.getSuma7(), alto - gral.getSuma8());
        cb.showText(Integer.toString(fecha.getYear() + 1900));
        cb.endText();

        cb.beginText();
        cb.setFontAndSize(bf, gral.getSuma12());
        cb.setTextMatrix(gral.getSuma10(), alto - gral.getSuma11());
        cb.showText(datoPaciente.getNombres());
        cb.endText();

        cb.beginText();
        cb.setFontAndSize(bf, gral.getSuma15());
        cb.setTextMatrix(gral.getSuma13(), alto - gral.getSuma14());
        cb.showText((dato.getNombres().substring(0, 7)));
        cb.endText();

        cb.beginText();
        cb.setFontAndSize(bf, gral.getSuma18());
        cb.setTextMatrix(gral.getSuma16(), alto - gral.getSuma17());
        cb.showText(Integer.toString(fecha.getHours()) + ':' + Integer.toString(fecha.getMinutes()) + ':' + Integer.toString(fecha.getSeconds()));
        cb.endText();

        for (int i = 0; i < listaKardex.size(); i++) {
            Recetas datoReceta = (Recetas) listaKardex.get(i);

            cb.beginText();
            cb.setFontAndSize(bf, gral.getSuma21());
            cb.setTextMatrix(gral.getSuma19(), alto - (gral.getSuma20() + i * 14));
            cb.showText(((datoReceta.getMedicamento().length() > 20) ? datoReceta.getMedicamento().substring(0, 20) : datoReceta.getMedicamento()));
            cb.endText();

            cb.beginText();
            cb.setFontAndSize(bf, gral.getSuma24());
            cb.setTextMatrix(gral.getSuma22(), alto - (gral.getSuma23() + i * 14));
            cb.showText(df.format(datoReceta.getSalida()));
            cb.endText();

            cb.beginText();
            cb.setFontAndSize(bf, gral.getSuma27());
            cb.setTextMatrix(gral.getSuma25(), alto - (gral.getSuma26() + i * 14));
            cb.showText(((datoReceta.getForma_far().length() > 6) ? datoReceta.getForma_far().substring(0, 6) : datoReceta.getForma_far()));
            cb.endText();

            cb.beginText();
            cb.setFontAndSize(bf, gral.getSuma30());
            cb.setTextMatrix(gral.getSuma28(), alto - (gral.getSuma29() + i * 14));
            cb.showText(((datoReceta.getConcentra().length() > 4) ? datoReceta.getConcentra().substring(0, 4) : datoReceta.getConcentra()));
            cb.endText();

            cb.beginText();
            cb.setFontAndSize(bf, gral.getSuma33());
            cb.setTextMatrix(gral.getSuma31(), alto - (gral.getSuma32() + i * 14));
            //cb.showText(Double.toString(datoReceta.getPrecio_venta()));
            cb.showTextAligned(PdfContentByte.ALIGN_RIGHT, Double.toString(datoReceta.getPrecio_venta()), gral.getSuma31(), alto - (gral.getSuma32() + i * 14), 0);
            cb.endText();

            cb.beginText();
            cb.setFontAndSize(bf, gral.getSuma36());
            cb.setTextMatrix(gral.getSuma34(), alto - (gral.getSuma35() + i * 14));
            //cb.showText(dfx.format((datoReceta.getSalida()*datoReceta.getPrecio_venta())));
            cb.showTextAligned(PdfContentByte.ALIGN_RIGHT, dfx.format((datoReceta.getSalida() * datoReceta.getPrecio_venta())), gral.getSuma34(), alto - (gral.getSuma35() + i * 14), 0);
            //cb.showTextAligned(PdfContentByte.ALIGN_CENTER, text + " Center", x, y_line1, 0);  ///x,y,rotate
            cb.endText();

            total = total + (datoReceta.getSalida() * datoReceta.getPrecio_venta());
        }
        int f, v, b, n;
        f = (int) total;
        total2 = total;
        v = (int) (((total) - f) * 100);
        b = v / 2;
        if (v == 99) {
            v = 0;
            total2 = total + 1;
            b = 0;
        }
        if (b == 4 || b == 9 || b == 14 || b == 19 || b == 24 || b == 29 || b == 34 || b == 39 || b == 44 || b == 49) {
            v = v + 1;
        }
        n2t numero = new n2t();
        p = numero.convertirLetras((int) total2) + "    " + Integer.toString((v)) + "/100";

        cb.beginText();
        cb.setFontAndSize(bf, gral.getSuma39());
        cb.setTextMatrix(gral.getSuma37(), alto - gral.getSuma38());
        cb.showText(p);
        cb.endText();

        cb.beginText();
        cb.setFontAndSize(bf, gral.getSuma42());
        cb.setTextMatrix(gral.getSuma40(), alto - gral.getSuma41());
        cb.showText(dfx.format((total)));
        cb.endText();

        cb.stroke();

    }

    public static String format(Date dia, String formato) {
        if (dia == null) {
            return "";
        }
        SimpleDateFormat formatDate = new SimpleDateFormat(formato);
        return formatDate.format(dia);
    }
}
