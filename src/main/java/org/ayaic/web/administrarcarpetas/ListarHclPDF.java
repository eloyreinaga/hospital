package org.ayaic.web.administrarcarpetas;

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

public class ListarHclPDF extends AbstractPdfView {

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
        Pacientes datoPaciente = (Pacientes) model.get("datodep");
        Pacientes datoJefe = (Pacientes) model.get("datojef");
        String mes = (String) model.get("mes");
        String gestion = (String) model.get("gestion");
        int alto = 780;

        BaseFont bf = BaseFont.createFont("Helvetica", "Cp1252", false);
        PdfContentByte cb = writer.getDirectContent();

        cb.beginText();
        cb.setFontAndSize(bf, 8);
        cb.setTextMatrix(210, alto - 55);
        cb.showText(datoJefe.getPaterno());
        cb.endText();

        cb.beginText();
        cb.setFontAndSize(bf, 8);
        cb.setTextMatrix(210, alto - 70);
        cb.showText(datoJefe.getMaterno());
        cb.endText();

        cb.beginText();
        cb.setFontAndSize(bf, 8);
        cb.setTextMatrix(185, alto - 85);
        cb.showText(datoJefe.getNombre());
        cb.endText();

        cb.beginText();
        cb.setFontAndSize(bf, 8);
        cb.setTextMatrix(220, alto - 115);
        cb.showText(format(datoJefe.getFec_nacimiento(), "dd/MM/yyyy"));
        cb.endText();
        if (datoJefe.getId_tipo_sexo() == 1) {
            cb.circle(320, alto - 112, 5);
        } else {
            cb.circle(310, alto - 112, 5);
        }
        cb.stroke();

        cb.beginText();
        cb.setFontAndSize(bf, 8);
        cb.setTextMatrix(190, alto - 130);
        cb.showText(datoJefe.getOcupacion());
        cb.endText();

        cb.beginText();
        cb.setFontAndSize(bf, 8);
        cb.setTextMatrix(190, alto - 145);
        cb.showText(datoJefe.getDireccion());
        cb.endText();

        cb.beginText();
        cb.setFontAndSize(bf, 8);
        cb.setTextMatrix(60, alto - 160);
        cb.showText(dato.getEstablecimiento());
        cb.endText();

        cb.beginText();
        cb.setFontAndSize(bf, 8);
        cb.setTextMatrix(80, alto - 175);
        cb.showText(dato.getDepartamento());
        cb.endText();

        cb.beginText();
        cb.setFontAndSize(bf, 8);
        cb.setTextMatrix(190, alto - 175);
        cb.showText(dato.getLocalidad());
        cb.endText();

        cb.beginText();
        cb.setFontAndSize(bf, 8);
        cb.setTextMatrix(270, alto - 175);
        cb.showText(dato.getRed());
        cb.endText();

        //datos del paciente
        cb.beginText();
        cb.setFontAndSize(bf, 12);
        cb.setTextMatrix(450, alto - 40);
        cb.showText(Integer.toString(datoPaciente.getHcl()));
        cb.endText();

        cb.beginText();
        cb.setFontAndSize(bf, 8);
        cb.setTextMatrix(120, alto - 225);
        cb.showText(datoPaciente.getPaterno());
        cb.endText();

        cb.beginText();
        cb.setFontAndSize(bf, 8);
        cb.setTextMatrix(250, alto - 225);
        cb.showText(datoPaciente.getMaterno());
        cb.endText();

        cb.beginText();
        cb.setFontAndSize(bf, 8);
        cb.setTextMatrix(385, alto - 225);
        cb.showText(datoPaciente.getNombre());
        cb.endText();

        cb.beginText();
        cb.setFontAndSize(bf, 8);
        cb.setTextMatrix(130, alto - 240);
        cb.showText(format(datoPaciente.getFec_nacimiento(), "dd/MM/yyyy"));
        cb.endText();

        if (datoPaciente.getId_tipo_sexo() == 1) {
            cb.circle(240, alto - 237, 5);
        } else {
            cb.circle(250, alto - 237, 5);
        }
        cb.stroke();

        cb.beginText();
        cb.setFontAndSize(bf, 8);
        cb.setTextMatrix(310, alto - 240);
        cb.showText(datoPaciente.getOcupacion());
        cb.endText();

        cb.beginText();
        cb.setFontAndSize(bf, 8);
        cb.setTextMatrix(500, alto - 240);
        cb.showText(format(datoPaciente.getFec_registro(), "dd/MM/yyyy"));
        cb.endText();

        switch (datoPaciente.getId_ecivil()) {
            case 1:
                cb.circle(100, alto - 252, 5);
                break;
            case 2:
                cb.circle(130, alto - 252, 5);
                break;
            case 3:
                cb.circle(170, alto - 252, 5);
                break;
            case 4:
                cb.circle(200, alto - 252, 5);
                break;
            case 5:
                cb.circle(230, alto - 252, 5);
                break;
            case 6:
                cb.circle(270, alto - 252, 5);
                break;

        }

        switch (datoPaciente.getId_escolaridad()) {
            case 1:
                cb.circle(350, alto - 252, 5);
                break;
            case 2:
                cb.circle(410, alto - 252, 5);
                break;
            case 3:
                cb.circle(430, alto - 252, 5);
                break;
            case 4:
                cb.circle(460, alto - 252, 5);
                break;
        }
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
