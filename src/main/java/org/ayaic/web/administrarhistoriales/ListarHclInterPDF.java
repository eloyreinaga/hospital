package org.ayaic.web.administrarhistoriales;

import java.awt.Color;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.view.document.AbstractPdfView;
import java.util.*;
import java.text.*;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import org.ayaic.domain.Carpetas;
import org.ayaic.domain.Cuadernos;
import org.ayaic.domain.Departamentos;
import org.ayaic.domain.Eciviles;
import org.ayaic.domain.Historiales;
import org.ayaic.domain.Localidades;
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.Personas;
import org.ayaic.domain.Provincias;

public class ListarHclInterPDF extends AbstractPdfView {

    private static final Font TITULO_FONT = new Font(Font.HELVETICA, 12, Font.BOLD, Color.black);
    private static final Font CABEZA_COLUMNA_FONT = new Font(Font.HELVETICA, 8, Font.ITALIC, Color.black);
    private static final Font DATO_FONT = new Font(Font.HELVETICA, 8, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_A = new Font(Font.HELVETICA, 6, Font.NORMAL, Color.black);
    private static final int MARGIN = 32;

    protected void buildPdfMetadata(Map model, Document document, HttpServletRequest request) {
        document.setPageSize(PageSize.LETTER);
        document.setMargins(20, 20, 20, 20);
    }

    protected void buildPdfDocument(Map model, Document document, PdfWriter writer, HttpServletRequest req, HttpServletResponse resp) throws Exception {

        java.util.List pacientess = (java.util.List) model.get("dato");
        java.util.List listaJefe = (java.util.List) model.get("listaJefe");
        java.util.List cie = (java.util.List) model.get("listaCie");
        java.util.List ListaInter = (java.util.List) model.get("listaInter");
        java.util.List ListaC2 = (java.util.List) model.get("listac2");
        java.util.List ListaC5 = (java.util.List) model.get("listaC5");
        Departamentos datoDep = (Departamentos) model.get("listaDep");
        Provincias datoProv = (Provincias) model.get("listaProv");
        Localidades datoLoc = (Localidades) model.get("listaLoc");
        Eciviles datoCiv = (Eciviles) model.get("listaEciv");
        Pacientes dato = (Pacientes) model.get("datos");
        Personas datoPer = (Personas) model.get("listaPer");
        Historiales datoH = (Historiales) model.get("listaH");

        String tipo;
        String sCampos[] = {"FECHA", "ENTRADAS", "SALIDAS", "AJUSTES(+/-)", "SALDO", "No. Doc.", "Otro", "N.- y\nClave Doc", "NOMBRES", "Recib:\nExpedido a:", "COST\nUNIT", "SALDO\nValorizado", "FECHA\nVTO", "No\nLOTE"};

        DecimalFormat df = new DecimalFormat("###,##0.00");
        Image escudo = Image.getInstance("/opt/imagenes/lescudo.bmp");
        Paragraph p = new Paragraph("Formulario de Hospitalizacion\n(Paciente Internado)", new Font(Font.HELVETICA, 14, Font.BOLDITALIC, new Color(0, 0, 0)));
        p.setAlignment(Element.ALIGN_CENTER);

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

        PdfPTable table1n = new PdfPTable(4);
        int[] fmwidths = {20, 20, 20, 40}; // percentage
        table1n.setWidths(fmwidths);
        table1n.setWidthPercentage(100);

        if (dato.getEdad() < 10 && listaJefe.size() > 0) {
            Carpetas datoJ = (Carpetas) listaJefe.get(0);
            cell = new PdfPCell(new Phrase("Resp.:" + datoJ.getNombres() + "\n1. AP. Paterno : " + dato.getPaterno(), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table1n.addCell(cell);
        } else {
            cell = new PdfPCell(new Phrase("1. AP. Paterno : \n" + dato.getPaterno(), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table1n.addCell(cell);
        }
        cell = new PdfPCell(new Phrase("Ap. Materno: \n" + dato.getMaterno(), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table1n.addCell(cell);

        cell = new PdfPCell(new Phrase("Nombres : \n" + dato.getNombre(), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table1n.addCell(cell);

        cell = new PdfPCell(new Phrase("2. No HCL : \n" + dato.getHcl(), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1n.addCell(cell);

        document.add(table1n);

        PdfPTable table1s = new PdfPTable(8);
        int[] fmwidtss = {15, 15, 15, 20, 15, 15, 20, 15}; // percentage
        table1s.setWidths(fmwidtss);
        table1s.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("3. Edad: \n" + Integer.toString(dato.getEdad()) + "a" + Integer.toString(dato.getMes()) + "m" + Integer.toString(dato.getDia()) + "d", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table1s.addCell(cell);

        if (dato.getId_tipo_sexo() == 1) {
            cell = new PdfPCell(new Phrase("4. Sexo :\n Femenino", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table1s.addCell(cell);
        } else {
            cell = new PdfPCell(new Phrase("Sexo :\n Masculino", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table1s.addCell(cell);
        }

        cell = new PdfPCell(new Phrase("5. Lugar Nac : \n" + datoLoc.getLocalidad(), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1s.addCell(cell);

        cell = new PdfPCell(new Phrase("Provincia : \n" + datoProv.getProvincia(), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1s.addCell(cell);

        cell = new PdfPCell(new Phrase("Depto. : \n" + datoDep.getDepartamento(), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1s.addCell(cell);

        cell = new PdfPCell(new Phrase("6. Procedencia : \n" + datoLoc.getLocalidad(), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1s.addCell(cell);

        cell = new PdfPCell(new Phrase("Provincia : \n" + datoProv.getProvincia(), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1s.addCell(cell);

        cell = new PdfPCell(new Phrase("Depto. : \n" + datoDep.getDepartamento(), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1s.addCell(cell);

        document.add(table1s);

        PdfPTable tablec = new PdfPTable(3);
        int[] fmwidtsc = {30, 40, 30}; // percentage
        tablec.setWidths(fmwidtsc);
        tablec.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("7. Estado Civil : \n" + datoCiv.getEcivil(), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tablec.addCell(cell);

        cell = new PdfPCell(new Phrase("8. Ocupacion Habitual: \n" + dato.getOcupacion(), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tablec.addCell(cell);

        switch (dato.getId_escolaridad()) {
            case 1:
                cell = new PdfPCell(new Phrase("9. Grado Instruccion : \n Basico", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tablec.addCell(cell);
                break;
            case 2:
                cell = new PdfPCell(new Phrase("9. Grado Instruccion : \n Intermedio", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tablec.addCell(cell);
                break;
            case 3:
                cell = new PdfPCell(new Phrase("9. Grado Instruccion : \n Medio o Mayor", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tablec.addCell(cell);
                break;
            case 4:
                cell = new PdfPCell(new Phrase("9. Grado Instruccion : \n Sin Instruccion", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tablec.addCell(cell);
                break;
        }

        document.add(tablec);

        PdfPTable tableh = new PdfPTable(2);
        int[] fmwidthh = {60, 40}; // percentage
        tableh.setWidths(fmwidthh);
        tableh.setWidthPercentage(100);

        if (datoH.getDiagnostico() == null || "null".equals(datoH.getDiagnostico())) {
            datoH.setDiagnostico("");
        } else {
            datoH.setDiagnostico(datoH.getDiagnostico().replaceAll("<p>", "\n"));
            datoH.setDiagnostico(datoH.getDiagnostico().replaceAll("</p>", " "));
            datoH.setDiagnostico(datoH.getDiagnostico().replaceAll("<ul>", " "));
            datoH.setDiagnostico(datoH.getDiagnostico().replaceAll("</ul>", " "));
            datoH.setDiagnostico(datoH.getDiagnostico().replaceAll("<li>", " "));
            datoH.setDiagnostico(datoH.getDiagnostico().replaceAll("</li>", " "));
            datoH.setDiagnostico(datoH.getDiagnostico().replaceAll("&nbsp;", ""));
            datoH.setDiagnostico(datoH.getDiagnostico().replaceAll("<strong>", " "));
            datoH.setDiagnostico(datoH.getDiagnostico().replaceAll("</strong>", " "));
            datoH.setDiagnostico(datoH.getDiagnostico().replaceAll("<br />", "\n"));
            datoH.setDiagnostico(datoH.getDiagnostico().replaceAll("<u>", " "));
            datoH.setDiagnostico(datoH.getDiagnostico().replaceAll("</u>", " "));
            datoH.setDiagnostico(datoH.getDiagnostico().replaceAll("&ntilde;", "n"));
            datoH.setDiagnostico(datoH.getDiagnostico().replaceAll("&Ntilde;", "N"));
            datoH.setDiagnostico(datoH.getDiagnostico().replaceAll("&Aacute;", "A"));
            datoH.setDiagnostico(datoH.getDiagnostico().replaceAll("&Eacute;", "E"));
            datoH.setDiagnostico(datoH.getDiagnostico().replaceAll("&Iacute;", "I"));
            datoH.setDiagnostico(datoH.getDiagnostico().replaceAll("&Oacute;", "O"));
            datoH.setDiagnostico(datoH.getDiagnostico().replaceAll("&Uacute;", "U"));
            datoH.setDiagnostico(datoH.getDiagnostico().replaceAll("&aacute;", "a"));
            datoH.setDiagnostico(datoH.getDiagnostico().replaceAll("&eacute;", "e"));
            datoH.setDiagnostico(datoH.getDiagnostico().replaceAll("&iacute;", "i"));
            datoH.setDiagnostico(datoH.getDiagnostico().replaceAll("&oacute;", "o"));
            datoH.setDiagnostico(datoH.getDiagnostico().replaceAll("&uacute;", "u"));
            datoH.setDiagnostico(datoH.getDiagnostico().replaceAll("&quot;", "'"));
        }

        cell = new PdfPCell(new Phrase("10. Diagnostico : \n" + datoH.getDiagnostico() + "-->" + datoH.getCodigo(), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        tableh.addCell(cell);

        cell = new PdfPCell(new Phrase("11. Medico que Ordena Internacion: \n         Dr. " + datoPer.getNombres() + "\n Matricula: " + datoPer.getDip(), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        tableh.addCell(cell);

        document.add(tableh);

        PdfPTable tableh2 = new PdfPTable(2);
        int[] fmwidth2 = {60, 40}; // percentage
        tableh2.setWidths(fmwidth2);
        tableh2.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("12. Direccion del paciente : /Telefono\n" + dato.getDireccion() + "-->" + dato.getTelefono(), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        tableh2.addCell(cell);

        cell = new PdfPCell(new Phrase("13. Enviado de: \n     ", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        tableh2.addCell(cell);

        cell = new PdfPCell(new Phrase("14. INGRESO - TRASLADO DURANTE LA HOSPITALIZACION - EGRESO", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(2);
        tableh2.addCell(cell);

        document.add(tableh2);

        PdfPTable tablehr = new PdfPTable(8);
        int[] fmwidthr = {8, 6, 50, 13, 11, 9, 20, 5}; // percentage
        tablehr.setWidths(fmwidthr);
        tablehr.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("FECHA", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.9f);
        tablehr.addCell(cell);

        cell = new PdfPCell(new Phrase("HORA", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.9f);
        tablehr.addCell(cell);

        cell = new PdfPCell(new Phrase("ACCION", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.9f);
        tablehr.addCell(cell);

        cell = new PdfPCell(new Phrase("SERVICIO", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.9f);
        tablehr.addCell(cell);

        cell = new PdfPCell(new Phrase("SALA", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.9f);
        tablehr.addCell(cell);

        cell = new PdfPCell(new Phrase("CAMA", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.9f);
        tablehr.addCell(cell);

        cell = new PdfPCell(new Phrase("POR", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.9f);
        tablehr.addCell(cell);

        cell = new PdfPCell(new Phrase("ID", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.9f);
        tablehr.addCell(cell);

        for (int i = 0; i < ListaInter.size(); i++) {

            Historiales Inter = (Historiales) ListaInter.get(i);

            cell = new PdfPCell(new Phrase(format(Inter.getFecha(), "dd/MM/yy"), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablehr.addCell(cell);

            cell = new PdfPCell(new Phrase(format(Inter.getFecha(), "HH:MM"), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablehr.addCell(cell);

            if (Inter.getDiagnostico() == null || "null".equals(Inter.getDiagnostico())) {
                Inter.setDiagnostico("");
            } else {
                Inter.setDiagnostico(Inter.getDiagnostico().replaceAll("<p>", " "));
                Inter.setDiagnostico(Inter.getDiagnostico().replaceAll("</p>", " "));
                datoH.setDiagnostico(datoH.getDiagnostico().replaceAll("<ul>", " "));
                datoH.setDiagnostico(datoH.getDiagnostico().replaceAll("</ul>", " "));
                datoH.setDiagnostico(datoH.getDiagnostico().replaceAll("<li>", " "));
                datoH.setDiagnostico(datoH.getDiagnostico().replaceAll("</li>", " "));
                Inter.setDiagnostico(Inter.getDiagnostico().replaceAll("&nbsp;", ""));
                Inter.setDiagnostico(Inter.getDiagnostico().replaceAll("<strong>", " "));
                Inter.setDiagnostico(Inter.getDiagnostico().replaceAll("</strong>", " "));
                Inter.setDiagnostico(Inter.getDiagnostico().replaceAll("<br />", " "));
                Inter.setDiagnostico(Inter.getDiagnostico().replaceAll("<u>", " "));
                Inter.setDiagnostico(Inter.getDiagnostico().replaceAll("</u>", " "));
                Inter.setDiagnostico(Inter.getDiagnostico().replaceAll("&ordm;", "o"));
                Inter.setDiagnostico(Inter.getDiagnostico().replaceAll("&ntilde;", "n"));
                Inter.setDiagnostico(Inter.getDiagnostico().replaceAll("&Ntilde;", "N"));
                Inter.setDiagnostico(Inter.getDiagnostico().replaceAll("&Aacute;", "A"));
                Inter.setDiagnostico(Inter.getDiagnostico().replaceAll("&Eacute;", "E"));
                Inter.setDiagnostico(Inter.getDiagnostico().replaceAll("&Iacute;", "I"));
                Inter.setDiagnostico(Inter.getDiagnostico().replaceAll("&Oacute;", "O"));
                Inter.setDiagnostico(Inter.getDiagnostico().replaceAll("&Uacute;", "U"));
                Inter.setDiagnostico(Inter.getDiagnostico().replaceAll("&aacute;", "a"));
                Inter.setDiagnostico(Inter.getDiagnostico().replaceAll("&eacute;", "e"));
                Inter.setDiagnostico(Inter.getDiagnostico().replaceAll("&iacute;", "i"));
                Inter.setDiagnostico(Inter.getDiagnostico().replaceAll("&oacute;", "o"));
                Inter.setDiagnostico(Inter.getDiagnostico().replaceAll("&uacute;", "u"));
                Inter.setDiagnostico(Inter.getDiagnostico().replaceAll("&quot;", "'"));
                Inter.setDiagnostico(Inter.getDiagnostico().replaceAll("&ograve;", "o"));
            }

            cell = new PdfPCell(new Phrase(Inter.getDiagnostico(), DATO_FONT_A));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            tablehr.addCell(cell);

            cell = new PdfPCell(new Phrase(Inter.getConsultorio(), DATO_FONT_A));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            tablehr.addCell(cell);

            cell = new PdfPCell(new Phrase(Inter.getCodigo(), DATO_FONT_A));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablehr.addCell(cell);

            cell = new PdfPCell(new Phrase(Inter.getAccion(), DATO_FONT_A));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablehr.addCell(cell);

            cell = new PdfPCell(new Phrase(Inter.getNombres(), DATO_FONT_A));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            tablehr.addCell(cell);

            cell = new PdfPCell(new Phrase(Integer.toString(Inter.getId_persona()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tablehr.addCell(cell);
        }
        document.add(tablehr);

        PdfPTable tableo = new PdfPTable(2);
        int[] fmwidto = {80, 20}; // percentage
        tableo.setWidths(fmwidto);
        tableo.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("15. Operaciones: ", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        tableo.addCell(cell);

        cell = new PdfPCell(new Phrase("CODIGO", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tableo.addCell(cell);

        cell = new PdfPCell(new Phrase(" ", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        tableo.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tableo.addCell(cell);

        cell = new PdfPCell(new Phrase(" ", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        tableo.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tableo.addCell(cell);

        cell = new PdfPCell(new Phrase("16. DIagnostico Principal: ", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        tableo.addCell(cell);

        cell = new PdfPCell(new Phrase("CODIGO", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        tableo.addCell(cell);

        for (int i = 0; i < cie.size(); i++) {
            Cuadernos cie10 = (Cuadernos) cie.get(i);
            if ("B".equals(cie10.getRegistro())) {

                cell = new PdfPCell(new Phrase("                            " + cie10.getObservaciones(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                tableo.addCell(cell);

                cell = new PdfPCell(new Phrase(cie10.getTipodent(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tableo.addCell(cell);
            }
        }
        cell = new PdfPCell(new Phrase("17. Otros DIagnosticos: ", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        tableo.addCell(cell);

        cell = new PdfPCell(new Phrase("CODIGO", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        tableo.addCell(cell);

        for (int i = 0; i < cie.size(); i++) {
            Cuadernos cie10 = (Cuadernos) cie.get(i);
            if ("C".equals(cie10.getRegistro())) {

                cell = new PdfPCell(new Phrase("                            " + cie10.getObservaciones(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                tableo.addCell(cell);

                cell = new PdfPCell(new Phrase(cie10.getTipodent(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tableo.addCell(cell);
            }
        }

        document.add(tableo);

        PdfPTable tablee = new PdfPTable(3);
        int[] fmwidte = {30, 30, 40}; // percentage
        tablee.setWidths(fmwidte);
        tablee.setWidthPercentage(100);

        for (int i = 0; i < ListaC5.size(); i++) {
            Cuadernos C5 = (Cuadernos) ListaC5.get(i);

            cell = new PdfPCell(new Phrase("19. No. dias de estadia\n                           " + C5.getDiasi(), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            tablee.addCell(cell);

            cell = new PdfPCell(new Phrase("20. Condicion de egreso: \n                   VIVO ", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            tablee.addCell(cell);

            if (C5.getTipo_egreso() == 1) {
                cell = new PdfPCell(new Phrase("21. CAUSA DE EGRESO\n Alta Medica ", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tablee.addCell(cell);
            } else {
                if (C5.getTipo_egreso() == 2) {
                    cell = new PdfPCell(new Phrase("21. CAUSA DE EGRESO\n Alta Solicitada", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    tablee.addCell(cell);
                } else {
                    cell = new PdfPCell(new Phrase("21. CAUSA DE EGRESO\n FUGA", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    tablee.addCell(cell);
                }
            }
        }

        document.add(tablee);

        PdfPTable tablen = new PdfPTable(9);
        int[] fmwidtn = {11, 11, 11, 11, 11, 11, 11, 11, 11}; // percentage
        tablen.setWidths(fmwidtn);
        tablen.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("23. Recien Nacido\n", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        tablen.addCell(cell);

        cell = new PdfPCell(new Phrase("Tipo: \n  ", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        tablen.addCell(cell);

        addtitulofila2(tablen, "Sexo", "M", "F");

        addtitulofila2(tablen, "Condicion al Nacer", "Vivo", "Muerto");

        cell = new PdfPCell(new Phrase("Peso en gramos", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tablen.addCell(cell);

        addtitulofila2(tablen, "Condicion al Egresar", "Vivo", "Muerto");

        for (int i = 0; i < ListaC2.size(); i++) {
            Cuadernos C2 = (Cuadernos) ListaC2.get(i);

            cell = new PdfPCell(new Phrase("", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            tablen.addCell(cell);

            cell = new PdfPCell(new Phrase((ListaC2.size() > 1) ? "GEMELOS" : "NORMAL(1)", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablen.addCell(cell);

            cell = new PdfPCell(new Phrase((C2.getSexo() == 1) ? "Masculino" : "Femenino", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(2);
            tablen.addCell(cell);

            cell = new PdfPCell(new Phrase("VIVO", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablen.addCell(cell);

            cell = new PdfPCell(new Phrase("", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablen.addCell(cell);

            cell = new PdfPCell(new Phrase(Integer.toString(C2.getAuto()), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablen.addCell(cell);

            cell = new PdfPCell(new Phrase("VIVO", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablen.addCell(cell);

            cell = new PdfPCell(new Phrase("", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablen.addCell(cell);

        }

        document.add(tablen);

        PdfPTable tablep = new PdfPTable(3);
        int[] fmwidtp = {30, 30, 40}; // percentage
        tablep.setWidths(fmwidtp);
        tablep.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("21. Nombre Medico\n", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        tablep.addCell(cell);

        cell = new PdfPCell(new Phrase("Numero de Matricula\n", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        tablep.addCell(cell);

        cell = new PdfPCell(new Phrase("FIRMA\n", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        tablep.addCell(cell);

        document.add(tablep);

        // coloca el detalle de los datos
        //   table.setHeaderRows(1);
    }

    public static void addtitulofila2(PdfPTable table, String cadena, String a, String b) {
        PdfPCell cell;
        int NumColumns = 2;
        PdfPTable table11 = new PdfPTable(NumColumns);
        table11.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase(cadena, CABEZA_COLUMNA_FONT));
        cell.setColspan(2);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase(a, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        cell = new PdfPCell(new Phrase(b, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell(cell);

        cell = new PdfPCell(table11);
        cell.setColspan(2);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
    }

    public static String format(Date dia, String formato) {
        if (dia == null) {
            return "";
        }
        SimpleDateFormat formatDate = new SimpleDateFormat(formato);
        return formatDate.format(dia);
    }
}
