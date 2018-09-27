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

public class ImprimirMoraPDF extends AbstractPdfView {

    private static final Font TITULO_FONT = new Font(Font.HELVETICA, 12, Font.BOLD, Color.black);
    private static final Font CABEZA_COLUMNA_FONT = new Font(Font.HELVETICA, 8, Font.ITALIC, Color.black);
    private static final Font DATO_FONT = new Font(Font.HELVETICA, 8, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_BOLD = new Font(Font.HELVETICA, 8, Font.BOLD, Color.black);
    private static final int MARGIN = 32;

    protected void buildPdfMetadata(Map model, Document document, HttpServletRequest request) {
        document.setPageSize(PageSize.LETTER);
        document.setMargins(10, 10, 10, 10);
    }

    protected void buildPdfDocument(Map model, Document document, PdfWriter writer, HttpServletRequest req, HttpServletResponse resp) throws Exception {

        Clientes dato = (Clientes) model.get("dato");
        Pacientes datoPaciente = (Pacientes) model.get("datodep");
        Pacientes pacientes = (Pacientes) model.get("datos");
        Pacientes datoJefe = (Pacientes) model.get("datojef");
        java.util.List listaVigencia = (java.util.List) model.get("listaVigencia");
        java.util.List datosgral = (java.util.List) model.get("datosgral");
        String mes = (String) model.get("mes");
        String gestion = (String) model.get("gestion");
        BaseFont bf = BaseFont.createFont("Helvetica", "Cp1252", false);
        PdfContentByte cb = writer.getDirectContent();
        int alto = 780;

        Cuadernos gral = (Cuadernos) datosgral.get(0);  ////Configuracion de la impresion   
        Pacientes pac = (Pacientes) listaVigencia.get(0);

        cb.beginText();
        cb.setFontAndSize(bf, gral.getSuma66());
        cb.setTextMatrix(gral.getSuma64(), alto - gral.getSuma65());
        cb.showText(Integer.toString(pac.getVeces()));
        cb.endText();

        cb.beginText();
        cb.setFontAndSize(bf, gral.getSuma3());
        cb.setTextMatrix(gral.getSuma1(), alto - gral.getSuma2());
        cb.showText(dato.getEstablecimiento());
        cb.endText();

        cb.beginText();
        cb.setFontAndSize(bf, gral.getSuma6());
        cb.setTextMatrix(gral.getSuma4(), alto - gral.getSuma5());
        cb.showText(format(pac.getFec_registro(), "dd") + "      de      " + format(pac.getFec_registro(), "MM") + "      del      " + format(pac.getFec_registro(), "yyyy") + "                                                                " + format(pac.getFec_registro(), "HH") + " : " + format(pac.getFec_registro(), "mm"));
        cb.endText();

        cb.beginText();
        cb.setFontAndSize(bf, gral.getSuma9());
        cb.setTextMatrix(gral.getSuma7(), alto - gral.getSuma8());
        cb.showText(pac.getNombres());
        cb.endText();

        cb.beginText();
        cb.setFontAndSize(bf, gral.getSuma12());
        cb.setTextMatrix(gral.getSuma10(), alto - gral.getSuma11());
        cb.showText(pac.getDireccion() + "      CI : " + pac.getCarnet());
        cb.endText();

        cb.beginText();
        cb.setFontAndSize(bf, gral.getSuma15());
        cb.setTextMatrix(gral.getSuma13(), alto - gral.getSuma14());
        cb.showText(dato.getNombres());
        cb.endText();

        cb.beginText();
        cb.setFontAndSize(bf, gral.getSuma18());
        cb.setTextMatrix(gral.getSuma16(), alto - gral.getSuma17());
        cb.showText(pac.getCarnet() + "          Edad : " + Integer.toString(pacientes.getEdad()) + " a " + Integer.toString(pacientes.getMes()) + " m " + Integer.toString(pacientes.getDia()) + "d");
        cb.endText();

        cb.beginText();
        cb.setFontAndSize(bf, gral.getSuma21());
        cb.setTextMatrix(gral.getSuma19(), alto - gral.getSuma20());
        cb.showText(pac.getFactor_riesgo());
        cb.endText();

        cb.beginText();
        cb.setFontAndSize(bf, gral.getSuma24());
        cb.setTextMatrix(gral.getSuma22(), alto - gral.getSuma23());
        cb.showText(pac.getId_estado());
        cb.endText();

        cb.beginText();
        cb.setFontAndSize(bf, gral.getSuma27());
        cb.setTextMatrix(gral.getSuma25(), alto - gral.getSuma26());
        cb.showText(pac.getMaterno() + "                            Consultorio : " + pac.getNit());
        cb.endText();

        cb.beginText();
        cb.setFontAndSize(bf, gral.getSuma33());
        cb.setTextMatrix(gral.getSuma31(), alto - gral.getSuma32());
        cb.showText((pac.getOcupacion().length() > 30 ? pac.getOcupacion().substring(0, 30) : pac.getOcupacion()) + "             Calle : " + pac.getPaterno() + "                Telefono : " + pac.getRegistro());
        cb.endText();

        cb.beginText();
        cb.setFontAndSize(bf, gral.getSuma30());
        cb.setTextMatrix(gral.getSuma28(), alto - gral.getSuma29());
        cb.showText(pac.getDocumento());
        cb.endText();

        cb.beginText();
        cb.setFontAndSize(bf, gral.getSuma36());
        cb.setTextMatrix(gral.getSuma34(), alto - gral.getSuma35());
        cb.showText(pac.getNombre());
        cb.endText();

        cb.beginText();
        cb.setFontAndSize(bf, gral.getSuma39());
        cb.setTextMatrix(gral.getSuma37(), alto - gral.getSuma38());
        cb.showText(pac.getCadena2());
        cb.endText();

        cb.beginText();
        cb.setFontAndSize(bf, gral.getSuma42());
        cb.setTextMatrix(gral.getSuma40(), alto - gral.getSuma41());
        cb.showText(pac.getResultado() + "             Calle : " + pac.getCadena() + "  No " + pac.getNro() + "                Telefono : " + pac.getCadena1());
        cb.endText();

        cb.beginText();
        cb.setFontAndSize(bf, gral.getSuma45());
        cb.setTextMatrix(gral.getSuma43(), alto - gral.getSuma44());
        cb.showText(pac.getNro_registro());
        cb.endText();

        cb.beginText();
        cb.setFontAndSize(bf, gral.getSuma48());
        cb.setTextMatrix(gral.getSuma46(), alto - gral.getSuma47());
        cb.showText(pac.getNum_cladoc());
        cb.endText();

        cb.beginText();
        cb.setFontAndSize(bf, gral.getSuma51());
        cb.setTextMatrix(gral.getSuma49(), alto - gral.getSuma50());
        cb.showText(pac.getCadena6() + "              Sala :     " + pac.getCadena7() + "        Cama    :        " + pac.getCadena8());
        cb.endText();

        cb.beginText();
        cb.setFontAndSize(bf, gral.getSuma54());
        cb.setTextMatrix(gral.getSuma52(), alto - gral.getSuma53());
        cb.showText(pac.getNum_cladoc());
        cb.endText();

        cb.beginText();
        cb.setFontAndSize(bf, gral.getSuma57());
        cb.setTextMatrix(gral.getSuma55(), alto - gral.getSuma56());
        cb.showText(pac.getCadena9());
        cb.endText();

        cb.beginText();
        cb.setFontAndSize(bf, gral.getSuma60());
        cb.setTextMatrix(gral.getSuma58(), alto - gral.getSuma59());
        cb.showText(pac.getCadena10());
        cb.endText();

        cb.beginText();
        cb.setFontAndSize(bf, gral.getSuma63());
        cb.setTextMatrix(gral.getSuma61(), alto - gral.getSuma62());
        cb.showText(format(pac.getFecha_ini(), "dd") + "      de      " + format(pac.getFecha_ini(), "MM") + "      del      " + format(pac.getFecha_ini(), "yyyy"));
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
