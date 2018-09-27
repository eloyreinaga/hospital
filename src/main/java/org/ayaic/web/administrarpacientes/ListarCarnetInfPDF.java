package org.ayaic.web.administrarpacientes;

import java.awt.Color;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.view.document.AbstractPdfView;
import java.util.*;
import java.text.*;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Cuadernos;
import org.ayaic.domain.Pacientes;

public class ListarCarnetInfPDF extends AbstractPdfView {

    private static final Font TITULO_FONT = new Font(Font.HELVETICA, 12, Font.BOLD, Color.black);
    private static final Font CABEZA_COLUMNA_FONT = new Font(Font.HELVETICA, 8, Font.ITALIC, Color.black);
    private static final Font DATO_FONT = new Font(Font.HELVETICA, 8, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_BOLD = new Font(Font.HELVETICA, 8, Font.BOLD, Color.black);
    private static final int MARGIN = 32;

    protected void buildPdfMetadata(Map model, Document document, HttpServletRequest request) {
        document.setPageSize(PageSize.LEGAL.rotate());
        document.setMargins(10, 10, 10, 10);
    }

    protected void buildPdfDocument(Map model, Document document, PdfWriter writer, HttpServletRequest req, HttpServletResponse resp) throws Exception {

        Clientes dato = (Clientes) model.get("dato");
        Pacientes datoPaciente = (Pacientes) model.get("datodep");
        Pacientes datoJefe = (Pacientes) model.get("datojef");
        java.util.List datosgral = (java.util.List) model.get("datosgral");
        String mes = (String) model.get("mes");
        String gestion = (String) model.get("gestion");
        int alto = 780;

        BaseFont bf = BaseFont.createFont("Helvetica", "Cp1252", false);
        PdfContentByte cb = writer.getDirectContent();

        Cuadernos gral = (Cuadernos) datosgral.get(0);  ////Configuracion de la impresion

        cb.beginText();
        cb.setFontAndSize(bf, gral.getSuma3());
        cb.setTextMatrix(gral.getSuma1(), alto - gral.getSuma2());
        cb.showText(datoJefe.getDireccion());
        cb.endText();

        cb.beginText();
        cb.setFontAndSize(bf, gral.getSuma6());
        cb.setTextMatrix(gral.getSuma4(), alto - gral.getSuma5());
        cb.showText(dato.getLocalidad());
        cb.endText();

        cb.beginText();
        cb.setFontAndSize(bf, gral.getSuma9());
        cb.setTextMatrix(gral.getSuma7(), alto - gral.getSuma8());
        cb.showText(dato.getLocalidad());
        cb.endText();

        cb.beginText();
        cb.setFontAndSize(bf, gral.getSuma12());
        cb.setTextMatrix(gral.getSuma10(), alto - gral.getSuma11());
        cb.showText(dato.getEstablecimiento());
        cb.endText();

        cb.beginText();
        cb.setFontAndSize(bf, gral.getSuma15());
        cb.setTextMatrix(gral.getSuma13(), alto - gral.getSuma14());
        cb.showText(dato.getRed());
        cb.endText();

        cb.beginText();
        cb.setFontAndSize(bf, gral.getSuma18());
        cb.setTextMatrix(gral.getSuma16(), alto - gral.getSuma17());
        cb.showText(datoPaciente.getNro_registro());  //datos de numero de asegurado
        cb.endText();
        //datos del paciente

        cb.beginText();
        cb.setFontAndSize(bf, gral.getSuma21());
        cb.setTextMatrix(gral.getSuma19(), alto - gral.getSuma20());
        cb.showText(Integer.toString(datoPaciente.getHcl()));
        cb.endText();

        cb.beginText();
        cb.setFontAndSize(bf, gral.getSuma24());
        cb.setTextMatrix(gral.getSuma22(), alto - gral.getSuma23());
        cb.showText(datoPaciente.getNombre() + ' ' + datoPaciente.getPaterno() + ' ' + datoPaciente.getMaterno());
        cb.endText();

        cb.beginText();
        cb.setFontAndSize(bf, gral.getSuma27());
        cb.setTextMatrix(gral.getSuma25(), alto - gral.getSuma26());
        cb.showText(format(datoPaciente.getFec_nacimiento(), "dd/MM/yyyy"));
        cb.endText();

        cb.beginText();
        cb.setFontAndSize(bf, gral.getSuma30());
        cb.setTextMatrix(gral.getSuma28(), alto - gral.getSuma29());
        cb.showText((Integer.toString(datoPaciente.getEdad())) + " años " + (Integer.toString(datoPaciente.getMes())) + " Meses");
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
