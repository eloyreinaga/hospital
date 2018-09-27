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
import org.ayaic.domain.Localidades;
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.Provincias;

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

    protected void buildPdfDocument(Map model, Document document, PdfWriter writer, HttpServletRequest req, HttpServletResponse resp) throws Exception {

        Clientes dato = (Clientes) model.get("dato");
        Pacientes datoPaciente = (Pacientes) model.get("datodep");
        Pacientes datoJefe = (Pacientes) model.get("datojef");
        Provincias prov = (Provincias) model.get("buscarProvincia");
        Localidades local = (Localidades) model.get("buscarLocalidad");
        java.util.List datosgral = (java.util.List) model.get("datosgral");
        String mes = (String) model.get("mes");
        String gestion = (String) model.get("gestion");
        Date fecha = new Date();
        int alto = 780;

        BaseFont bf = BaseFont.createFont("Helvetica", "Cp1252", false);
        PdfContentByte cb = writer.getDirectContent();

        //Provincias datoprov = (Provincias) prov.get(0);
        if ("3".equals(dato.getTipo())) { //Esto es es para la orden HGSJDD

            cb.beginText();
            cb.setFontAndSize(bf, 10);
            cb.setTextMatrix(130, alto - 40);
            cb.showText(dato.getEstablecimiento());
            cb.endText();

            cb.beginText();
            cb.setFontAndSize(bf, 10);
            cb.setTextMatrix(130, alto - 60);
            cb.showText(dato.getLocalidad());
            cb.endText();

            cb.beginText();
            cb.setFontAndSize(bf, 25);
            cb.setTextMatrix(500, alto - 100);
            cb.showText(Integer.toString(datoPaciente.getHcl()));
            cb.endText();

            cb.beginText();
            cb.setFontAndSize(bf, 12);
            cb.setTextMatrix(70, alto - 100);
            cb.showText(datoPaciente.getPaterno());
            cb.endText();

            cb.beginText();
            cb.setFontAndSize(bf, 12);
            cb.setTextMatrix(200, alto - 100);
            cb.showText(datoPaciente.getMaterno());
            cb.endText();

            cb.beginText();
            cb.setFontAndSize(bf, 12);
            cb.setTextMatrix(355, alto - 100);
            cb.showText(datoPaciente.getNombre());
            cb.endText();

            if (datoPaciente.getId_tipo_sexo() == 1) {
                cb.beginText();
                cb.setFontAndSize(bf, 12);
                cb.setTextMatrix(50, alto - 130);
                cb.showText("Femenino");
                cb.endText();
            } else {
                cb.beginText();
                cb.setFontAndSize(bf, 12);
                cb.setTextMatrix(50, alto - 130);
                cb.showText("Masculino");
                cb.endText();
            }

            cb.beginText();
            cb.setFontAndSize(bf, 12);
            cb.setTextMatrix(135, alto - 130);
            cb.showText(format(datoPaciente.getFec_nacimiento(), "dd/MM/yyyy"));
            cb.endText();

            cb.beginText();
            cb.setFontAndSize(bf, 10);
            cb.setTextMatrix(300, alto - 130);
            cb.showText(prov.getProvincia() + " - " + local.getLocalidad());
            cb.endText();

            switch (datoPaciente.getId_ecivil()) {
                case 1:
                    cb.beginText();
                    cb.setFontAndSize(bf, 12);
                    cb.setTextMatrix(455, alto - 130);
                    cb.showText("Soltero");
                    cb.endText();
                    break;  ////soltero        
                case 2:
                    cb.beginText();
                    cb.setFontAndSize(bf, 12);
                    cb.setTextMatrix(455, alto - 130);
                    cb.showText("Union Libre");
                    cb.endText();
                    break;  /////UL
                case 3:
                    cb.beginText();
                    cb.setFontAndSize(bf, 12);
                    cb.setTextMatrix(455, alto - 130);
                    cb.showText("Casado");
                    cb.endText();
                    break;  ////casado
                case 4:
                    cb.beginText();
                    cb.setFontAndSize(bf, 12);
                    cb.setTextMatrix(455, alto - 130);
                    cb.showText("Viudo");
                    cb.endText();
                    break;   ////viudo     
                case 5:
                    cb.beginText();
                    cb.setFontAndSize(bf, 12);
                    cb.setTextMatrix(455, alto - 130);
                    cb.showText("Divorciado");
                    cb.endText();
                    break;  /////divorciado
                case 6:
                    cb.beginText();
                    cb.setFontAndSize(bf, 12);
                    cb.setTextMatrix(455, alto - 130);
                    cb.showText("Menor");
                    cb.endText();
                    break;   /////Menor     
            }

            cb.beginText();
            cb.setFontAndSize(bf, 10);
            cb.setTextMatrix(70, alto - 170);
            cb.showText(datoPaciente.getOcupacion());
            cb.endText();

            cb.beginText();
            cb.setFontAndSize(bf, 10);
            cb.setTextMatrix(380, alto - 170);
            cb.showText("CI.: " + datoPaciente.getCarnet());
            cb.endText();

            cb.beginText();
            cb.setFontAndSize(bf, 10);
            cb.setTextMatrix(480, alto - 170);
            cb.showText("Telf.: " + datoPaciente.getTelefono());
            cb.endText();

            cb.beginText();
            cb.setFontAndSize(bf, 10);
            cb.setTextMatrix(70, alto - 200);
            cb.showText(datoJefe.getDireccion());
            cb.endText();

            cb.beginText();
            cb.setFontAndSize(bf, 10);
            cb.setTextMatrix(510, alto - 205);
            cb.showText(format(datoPaciente.getFec_registro(), "dd/MM/yyyy"));
            cb.endText();

            cb.beginText();
            cb.setFontAndSize(bf, 10);
            cb.setTextMatrix(510, alto - 230);
            cb.showText(Integer.toString(fecha.getHours()) + ':' + Integer.toString(fecha.getMinutes()) + ':' + Integer.toString(fecha.getSeconds()));
            cb.endText();

            cb.beginText();
            cb.setFontAndSize(bf, 12);
            cb.setTextMatrix(30, alto - 380);
            cb.showText(Integer.toString(fecha.getDate()) + '/' + Integer.toString(fecha.getMonth() + 1) + '/' + Integer.toString(fecha.getYear() + 1900));
            cb.endText();

            cb.stroke();

        } //Fin para la orden HGSJDD
        else {//Empieza oden de AP   

            Cuadernos gral = (Cuadernos) datosgral.get(0);  ////Configuracion de la impresion

            cb.beginText();
            cb.setFontAndSize(bf, gral.getGlome());
            cb.setTextMatrix(gral.getArterial(), alto - gral.getDiabetes());
            if (datoJefe != null) {
                cb.showText(datoJefe.getPaterno());
            } else {
                cb.showText(datoPaciente.getPaterno());
            }
            cb.endText();

            cb.beginText();
            cb.setFontAndSize(bf, gral.getLitiasis());
            cb.setTextMatrix(gral.getTracto(), alto - gral.getLupus());
            if (datoJefe != null) {
                cb.showText(datoJefe.getMaterno());
            } else {
                cb.showText(datoPaciente.getMaterno());
            }
            cb.endText();

            cb.beginText();
            cb.setFontAndSize(bf, gral.getDisli());
            cb.setTextMatrix(gral.getNooplasia(), alto - gral.getNefro());
            if (datoJefe != null) {
                cb.showText(datoJefe.getNombre());
            } else {
                cb.showText(datoPaciente.getNombre());
            }
            cb.endText();

            cb.beginText();
            cb.setFontAndSize(bf, gral.getPrematuro());
            cb.setTextMatrix(gral.getFrenal(), alto - gral.getBajopeso());
            if (datoJefe != null) {
                cb.showText(format(datoJefe.getFec_nacimiento(), "dd/MM/yyyy"));
            } else {
                cb.showText(format(datoPaciente.getFec_nacimiento(), "dd/MM/yyyy"));
            }
            cb.endText();

            cb.beginText();
            cb.setFontAndSize(bf, gral.getSuma3());
            cb.setTextMatrix(gral.getSuma1(), alto - gral.getSuma2());
            if (datoJefe != null) {
                cb.showText(datoJefe.getDireccion());
            } else {
                cb.showText(datoPaciente.getDireccion());
            }
            cb.endText();

            cb.beginText();
            cb.setFontAndSize(bf, gral.getSuma6());
            cb.setTextMatrix(gral.getSuma4(), alto - gral.getSuma5());
            cb.showText(dato.getEstablecimiento());
            cb.endText();

            cb.beginText();
            cb.setFontAndSize(bf, gral.getSuma9());
            cb.setTextMatrix(gral.getSuma7(), alto - gral.getSuma8());
            cb.showText(dato.getDepartamento());
            cb.endText();

            cb.beginText();
            cb.setFontAndSize(bf, gral.getSuma12());
            cb.setTextMatrix(gral.getSuma10(), alto - gral.getSuma11());
            cb.showText(dato.getLocalidad());
            cb.endText();

            cb.beginText();
            cb.setFontAndSize(bf, gral.getSistemica());
            cb.setTextMatrix(gral.getAuto(), alto - gral.getUrinaria());
            cb.showText(prov.getProvincia());
            cb.endText();

            cb.beginText();
            cb.setFontAndSize(bf, gral.getSuma15());
            cb.setTextMatrix(gral.getSuma13(), alto - gral.getSuma14());
            cb.showText(dato.getRed());
            cb.endText();

            //datos del paciente
            cb.beginText();
            cb.setFontAndSize(bf, gral.getSuma18());
            cb.setTextMatrix(gral.getSuma16(), alto - gral.getSuma17());
            cb.showText(Integer.toString(datoPaciente.getHcl()));
            cb.endText();

            cb.beginText();
            cb.setFontAndSize(bf, gral.getSuma21());
            cb.setTextMatrix(gral.getSuma19(), alto - gral.getSuma20());
            cb.showText(datoPaciente.getPaterno());
            cb.endText();

            cb.beginText();
            cb.setFontAndSize(bf, gral.getSuma24());
            cb.setTextMatrix(gral.getSuma22(), alto - gral.getSuma23());
            cb.showText(datoPaciente.getMaterno());
            cb.endText();

            cb.beginText();
            cb.setFontAndSize(bf, gral.getSuma27());
            cb.setTextMatrix(gral.getSuma25(), alto - gral.getSuma26());
            cb.showText(datoPaciente.getNombre());
            cb.endText();

            cb.beginText();
            cb.setFontAndSize(bf, gral.getSuma30());
            cb.setTextMatrix(gral.getSuma28(), alto - gral.getSuma29());
            cb.showText(format(datoPaciente.getFec_nacimiento(), "dd/MM/yyyy"));
            cb.endText();

            if (datoPaciente.getId_tipo_sexo() == 1) {
                cb.beginText();
                cb.setFontAndSize(bf, gral.getSuma33());
                cb.setTextMatrix(gral.getSuma31(), alto - gral.getSuma32());
                cb.showText("F");
                cb.endText();
            } else {
                cb.beginText();
                cb.setFontAndSize(bf, gral.getSuma33());
                cb.setTextMatrix(gral.getSuma31(), alto - gral.getSuma32());
                cb.showText("M");
                cb.endText();
            }

            cb.beginText();
            cb.setFontAndSize(bf, gral.getSuma36());
            cb.setTextMatrix(gral.getSuma34(), alto - gral.getSuma35());
            cb.showText(datoPaciente.getOcupacion());
            cb.endText();

            cb.beginText();
            cb.setFontAndSize(bf, gral.getSuma39());
            cb.setTextMatrix(gral.getSuma37(), alto - gral.getSuma38());
            cb.showText(format(datoPaciente.getFec_registro(), "dd/MM/yyyy"));
            cb.endText();

            cb.beginText();
            cb.setFontAndSize(bf, gral.getSuma48());
            cb.setTextMatrix(gral.getSuma46(), alto - gral.getSuma47());
            cb.showText((Integer.toString(datoPaciente.getEdad())) + " años " + (Integer.toString(datoPaciente.getMes())) + " Meses");
            cb.endText();

            switch (datoPaciente.getId_ecivil()) {
                case 1:
                    cb.beginText();
                    cb.setFontAndSize(bf, gral.getSuma42());
                    cb.setTextMatrix(gral.getSuma40(), alto - gral.getSuma41());
                    cb.showText("Soltero");
                    cb.endText();
                    break;  ////soltero        
                case 2:
                    cb.beginText();
                    cb.setFontAndSize(bf, gral.getSuma42());
                    cb.setTextMatrix(gral.getSuma40(), alto - gral.getSuma41());
                    cb.showText("Union Libre");
                    cb.endText();
                    break;  /////UL
                case 3:
                    cb.beginText();
                    cb.setFontAndSize(bf, gral.getSuma42());
                    cb.setTextMatrix(gral.getSuma40(), alto - gral.getSuma41());
                    cb.showText("Casado");
                    cb.endText();
                    break;  ////casado
                case 4:
                    cb.beginText();
                    cb.setFontAndSize(bf, gral.getSuma42());
                    cb.setTextMatrix(gral.getSuma40(), alto - gral.getSuma41());
                    cb.showText("Viudo");
                    cb.endText();
                    break;   ////viudo     
                case 5:
                    cb.beginText();
                    cb.setFontAndSize(bf, gral.getSuma42());
                    cb.setTextMatrix(gral.getSuma40(), alto - gral.getSuma41());
                    cb.showText("Divorciado");
                    cb.endText();
                    break;  /////divorciado
                case 6:
                    cb.beginText();
                    cb.setFontAndSize(bf, gral.getSuma42());
                    cb.setTextMatrix(gral.getSuma40(), alto - gral.getSuma41());
                    cb.showText("Menor");
                    cb.endText();
                    break;   /////Menor     
            }

            switch (datoPaciente.getId_escolaridad()) {
                case 1:
                    cb.circle(gral.getSuma43(), alto - gral.getSuma44(), gral.getSuma45());
                    break;
                case 2:
                    cb.circle(gral.getSuma43() + 20, alto - gral.getSuma44(), gral.getSuma45());
                    break;
                case 3:
                    cb.circle(gral.getSuma43() + 40, alto - gral.getSuma44(), gral.getSuma45());
                    break;
                case 4:
                    cb.circle(gral.getSuma43() + 60, alto - gral.getSuma44(), gral.getSuma45());
                    break;
            }

            cb.beginText();
            cb.setFontAndSize(bf, gral.getSuma51());
            cb.setTextMatrix(gral.getSuma49(), alto - gral.getSuma50());
            cb.showText(datoPaciente.getTelefono());
            cb.endText();

            cb.beginText();
            cb.setFontAndSize(bf, gral.getSpeso());
            cb.setTextMatrix(gral.getSuma52(), alto - gral.getSuma53());
            cb.showText(datoPaciente.getCarnet());
            cb.endText();

            cb.beginText();
            cb.setFontAndSize(bf, gral.getViolencia());
            cb.setTextMatrix(gral.getFuma(), alto - gral.getAlcohol());
            switch (datoPaciente.getId_tipo_documento()) {
                case 0:
                    cb.showText("Ninguno");
                    break;
                case 1:
                    cb.showText("C.I.");
                    break;
                case 2:
                    cb.showText("R.U.N.");
                    break;
                case 3:
                    cb.showText("Pasaporte");
                    break;
                case 4:
                    cb.showText("NIT");
                    break;
                case 5:
                    cb.showText("Libreta Militar");
                    break;
                case 6:
                    cb.showText("Hojas Testgo");
                    break;
                case 7:
                    cb.showText("Cert. Nacim.");
                    break;
                case 8:
                    cb.showText("Lib. Bautizo");
                    break;
                case 9:
                    cb.showText("Lib. Familia");
                    break;
                case 10:
                    cb.showText("Cert. Nacido Vivo");
                    break;
            }
            cb.endText();

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
