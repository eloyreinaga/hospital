package org.ayaic.web.administrarpacientes;

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
import org.ayaic.domain.Localidades;
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.Provincias;

public class ListarCarnetPDF extends AbstractPdfView {

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
        Provincias prov = (Provincias) model.get("buscarProvincia");
        Localidades local = (Localidades) model.get("buscarLocalidad");
        String mes = (String) model.get("mes");
        String gestion = (String) model.get("gestion");
        Date fecha = new Date();
        int alto = 780;

        BaseFont bf = BaseFont.createFont("Helvetica", "Cp1252", false);
        PdfContentByte cb = writer.getDirectContent();

        if ("3".equals(dato.getTipo())) { //Esto es es para la orden HGSJDD

            cb.beginText();
            cb.setFontAndSize(bf, 12);
            cb.setTextMatrix(260, alto - 80);
            cb.showText(datoPaciente.getPaterno());
            cb.endText();

            cb.beginText();
            cb.setFontAndSize(bf, 12);
            cb.setTextMatrix(250, alto - 100);
            cb.showText(datoPaciente.getMaterno());
            cb.endText();

            cb.beginText();
            cb.setFontAndSize(bf, 12);
            cb.setTextMatrix(260, alto - 125);
            cb.showText(datoPaciente.getNombre());
            cb.endText();

            cb.beginText();
            cb.setFontAndSize(bf, 10);
            cb.setTextMatrix(260, alto - 150);
            cb.showText(datoJefe.getDireccion());
            cb.endText();

            cb.beginText();
            cb.setFontAndSize(bf, 10);
            cb.setTextMatrix(270, alto - 175);
            cb.showText(datoPaciente.getTelefono());
            cb.endText();

            cb.beginText();
            cb.setFontAndSize(bf, 17);
            cb.setTextMatrix(275, alto - 200);
            cb.showText(Integer.toString(datoPaciente.getHcl()));
            cb.endText();

            cb.stroke();

        } //Fin para la orden HGSJDD
        else {//Empieza oden de AP    
            cb.beginText();
            cb.setFontAndSize(bf, 10);
            cb.setTextMatrix(170, alto - 190);
            cb.showText(datoJefe.getDireccion());
            cb.endText();

            cb.beginText();
            cb.setFontAndSize(bf, 10);
            cb.setTextMatrix(40, alto - 210);
            cb.showText(dato.getEstablecimiento());
            cb.endText();

            cb.beginText();
            cb.setFontAndSize(bf, 10);
            cb.setTextMatrix(150, alto - 220);
            cb.showText(dato.getDepartamento());
            cb.endText();

            cb.beginText();
            cb.setFontAndSize(bf, 10);
            cb.setTextMatrix(190, alto - 200);
            cb.showText(dato.getLocalidad());
            cb.endText();

            cb.beginText();
            cb.setFontAndSize(bf, 10);
            cb.setTextMatrix(80, alto - 220);
            cb.showText(dato.getRed());
            cb.endText();

            //datos del paciente
            cb.beginText();
            cb.setFontAndSize(bf, 35);
            cb.setTextMatrix(480, alto - 190);
            cb.showText(Integer.toString(datoPaciente.getHcl()));
            cb.endText();

            cb.beginText();
            cb.setFontAndSize(bf, 12);
            cb.setTextMatrix(120, alto - 255);
            cb.showText(datoPaciente.getPaterno());
            cb.endText();

            cb.beginText();
            cb.setFontAndSize(bf, 12);
            cb.setTextMatrix(285, alto - 255);
            cb.showText(datoPaciente.getMaterno());
            cb.endText();

            cb.beginText();
            cb.setFontAndSize(bf, 12);
            cb.setTextMatrix(445, alto - 255);
            cb.showText(datoPaciente.getNombre());
            cb.endText();

            cb.beginText();
            cb.setFontAndSize(bf, 12);
            cb.setTextMatrix(130, alto - 270);
            cb.showText(format(datoPaciente.getFec_nacimiento(), "dd/MM/yyyy"));
            cb.endText();

            if (datoPaciente.getId_tipo_sexo() == 1) {
                cb.circle(240, alto - 270, 5);
            } else {
                cb.circle(250, alto - 270, 5);
            }
            cb.stroke();

            cb.beginText();
            cb.setFontAndSize(bf, 12);
            cb.setTextMatrix(310, alto - 270);
            cb.showText(datoPaciente.getOcupacion());
            cb.endText();

            cb.beginText();
            cb.setFontAndSize(bf, 12);
            cb.setTextMatrix(510, alto - 270);
            cb.showText(format(datoPaciente.getFec_registro(), "dd/MM/yyyy"));
            cb.endText();

            switch (datoPaciente.getId_ecivil()) {
                case 1:
                    cb.circle(100, alto - 280, 5);
                    break;
                case 2:
                    cb.circle(130, alto - 280, 5);
                    break;
                case 3:
                    cb.circle(170, alto - 280, 5);
                    break;
                case 4:
                    cb.circle(200, alto - 280, 5);
                    break;
                case 5:
                    cb.circle(230, alto - 280, 5);
                    break;
                case 6:
                    cb.circle(270, alto - 280, 5);
                    break;

            }

            switch (datoPaciente.getId_escolaridad()) {
                case 1:
                    cb.circle(350, alto - 280, 5);
                    break;
                case 2:
                    cb.circle(410, alto - 280, 5);
                    break;
                case 3:
                    cb.circle(430, alto - 280, 5);
                    break;
                case 4:
                    cb.circle(460, alto - 280, 5);
                    break;
            }
            cb.stroke();

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
