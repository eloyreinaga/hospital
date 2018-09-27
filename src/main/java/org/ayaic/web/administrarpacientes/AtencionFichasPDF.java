package org.ayaic.web.administrarpacientes;

import java.awt.Color;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.view.document.AbstractPdfView;
import java.util.*;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import java.io.IOException;

import org.ayaic.domain.Clientes;
import org.ayaic.domain.Historiales;
import org.ayaic.domain.Localidades;

public class AtencionFichasPDF extends AbstractPdfView {

    private static final Font TITULO_FONT = new Font(Font.HELVETICA, 12, Font.BOLD, Color.black);
    private static final Font CABEZA_COLUMNA_FONT = new Font(Font.HELVETICA, 8, Font.ITALIC, Color.black);
    private static final Font DATO_FONT = new Font(Font.HELVETICA, 8, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_BOLD = new Font(Font.HELVETICA, 8, Font.BOLD, Color.black);
    private static final int MARGIN = 32;

    protected void buildPdfMetadata(Map model, Document document, HttpServletRequest request) {
        document.setPageSize(PageSize.A4);
        document.setMargins(10, 10, 10, 10);
    }

    protected void buildPdfDocument(Map model, Document document, PdfWriter writer, HttpServletRequest req, HttpServletResponse resp) throws DocumentException, IOException {

        Clientes dato = (Clientes) model.get("dato");
        java.util.List listaficha = (java.util.List) model.get("listaficha");
        Localidades local = (Localidades) model.get("buscarLocalidad");
        String estab = (String) model.get("establecimento");
        String gestion = (String) model.get("gestion");
        //Image escudo = Image.getInstance("/opt/imagenes/lescudo.bmp");
        String cod = (String) model.get("cod");
        Date fecha = new Date();
        cod = estab;

        BaseFont bf = BaseFont.createFont("Helvetica", "Cp1252", false);
        PdfContentByte cb = writer.getDirectContent();

        Historiales ficha = (Historiales) listaficha.get(0);  ////Configuracion de la impresion

        cb.beginText();
        cb.setFontAndSize(bf, 30);
        cb.setTextMatrix(40, 820);
        cb.showText(ficha.getCadena4());
        cb.endText();

        cb.beginText();
        cb.setFontAndSize(bf, 17);
        cb.setTextMatrix(20, 800);
        cb.showText(dato.getEstablecimiento());
        cb.endText();

        cb.beginText();
        cb.setFontAndSize(bf, 17);
        cb.setTextMatrix(50, 785);
        cb.showText(ficha.getConsultorio());
        cb.endText();

        cb.beginText();
        cb.setFontAndSize(bf, 15);
        cb.setTextMatrix(50, 770);
        cb.showText("Hcl No.: " + Integer.toString(ficha.getHcl()));
        cb.endText();
        
        cb.beginText();
        cb.setFontAndSize(bf, 15);
        cb.setTextMatrix(10, 755);
        cb.showText(ficha.getNombre());
        cb.endText();

        cb.beginText();
        cb.setFontAndSize(bf, 10);
        cb.setTextMatrix(10, 740);
        cb.showText("Pac.:" + ficha.getNombres());
        cb.endText();

        cb.beginText();
        cb.setFontAndSize(bf, 15);
        cb.setTextMatrix(30, 725);
        cb.showText(Integer.toString(ficha.getFecha().getDate()) + "-" + (Integer.toString(ficha.getFecha().getMonth() + 1)) + "-" + (Integer.toString(ficha.getFecha().getYear() + 1900)) + "  " + Integer.toString(ficha.getFecha().getHours()) + ":" + Integer.toString(ficha.getFecha().getMinutes()) + ":" + Integer.toString(ficha.getFecha().getSeconds()));
        cb.endText();

        cb.stroke();

        PdfAction action = new PdfAction(PdfAction.PRINTDIALOG);
        writer.setOpenAction(action);

    }

}
