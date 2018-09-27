package org.ayaic.web.administrarlaboratorio;

import com.itextpdf.text.pdf.BarcodeQRCode;
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
import org.ayaic.domain.Historiales;
import org.ayaic.domain.Localidades;
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.Personas;

public class VerEndosPDF extends AbstractPdfView {

    private static final Font TITULO_FONT = new Font(Font.HELVETICA, 12, Font.BOLD, Color.black);
    private static final Font CABEZA_COLUMNA_FONT = new Font(Font.HELVETICA, 8, Font.ITALIC, Color.black);
    private static final Font DATO_FONT = new Font(Font.HELVETICA, 8, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_B = new Font(Font.HELVETICA, 11, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_C = new Font(Font.COURIER, 11, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_A = new Font(Font.HELVETICA, 9, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_BOLD = new Font(Font.HELVETICA, 8, Font.BOLD, Color.black);
    private static final int MARGIN = 32;

    protected void buildPdfMetadata(Map model, Document document, HttpServletRequest request) {
        document.setPageSize(PageSize.LEGAL);
        document.setMargins(20, 20, 10, 20);
    }

    protected void buildPdfDocument(Map model, Document document, PdfWriter writer, HttpServletRequest req, HttpServletResponse resp) throws Exception {

        java.util.List ldatoLab = (java.util.List) model.get("listalabEndo"); ////listalabEndo
        java.util.List ListaLab = (java.util.List) model.get("listarLab");
        java.util.List listaDetEndo = (java.util.List) model.get("listalabDetEndo");

        String consultorio = (String) model.get("consultorio");
        String accionl = (String) model.get("accionl");
        java.util.List seguros = (java.util.List) model.get("listaPacAfi");
        java.util.List datosPacEmp = (java.util.List) model.get("datosPacEmp");

        Historiales datoHis = (Historiales) model.get("datosHis");
        Cuadernos lista = (Cuadernos) model.get("lista");
        Localidades establecim = (Localidades) model.get("estab");
        Personas datoMed = (Personas) model.get("datosMed");
        Pacientes datoPac = (Pacientes) model.get("datosPac");
        Clientes dato = (Clientes) model.get("dato");
        String cod = (String) model.get("cod");
        Pacientes datoPaciente = (Pacientes) model.get("datos");
        DecimalFormat df = new DecimalFormat("###,##0");
        DecimalFormat dfx = new DecimalFormat("###,##0.00");

        // coloca el titulo de la pagina
        Image escudo = Image.getInstance("/opt/imagenes/lescudo.bmp");
        Paragraph p = new Paragraph("REPORTE DE RESULTADOS DE ENDOSCOPIA ", new Font(Font.HELVETICA, 12, Font.BOLDITALIC, new Color(0, 0, 0)));
        p.setAlignment(Element.ALIGN_CENTER);
        //Image sumi = Image.getInstance("/opt/imagenes/sumi.bmp");

        //if(!datosPacEmp.isEmpty()){ ///debe estar creado empresas para poder funcionar
        Pacientes datoPacEmp = (Pacientes) datosPacEmp.get(0);
        //}

        PdfPCell cell;

        PdfPTable tablex = new PdfPTable(3);
        int[] fxwidths = {10, 80, 10}; // percentage
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

        cod = Integer.toString(200010) + '|' + format(new Date(), "dd/MM/yyyy HH:mm:ss") + '|' + Integer.toString(10);

        PdfContentByte cb = writer.getDirectContent();
        BarcodeQRCode qrcode = new BarcodeQRCode(cod, 90, 90, null);
        java.awt.Image qrImage = qrcode.createAwtImage(Color.black, Color.white);
        Image finalImage = Image.getInstance(writer, qrImage, 1);

        cell = new PdfPCell(finalImage);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex.addCell(cell);

        document.add(tablex);

        PdfPTable table1 = new PdfPTable(2);
        int[] fmwidths = {70, 30}; // percentage
        table1.setWidths(fmwidths);
        table1.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase(establecim.getCodigolab(), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(2);
        table1.addCell(cell);

        if (establecim.getCod_esta() != 200010) {
            cell = new PdfPCell(new Phrase("SEDES                         :    " + dato.getDepartamento() + "                                    RED    :    " + dato.getRed(), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table1.addCell(cell);

            cell = new PdfPCell(new Phrase("Municipio    :     " + dato.getLocalidad(), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table1.addCell(cell);

            cell = new PdfPCell(new Phrase("Direccion   :    " + establecim.getDireccion() + "       Telefono  :    " + establecim.getTele1() + " ; " + establecim.getTele2(), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table1.addCell(cell);
        } else {
            cell = new PdfPCell(new Phrase("Empresa    :   " + datoPacEmp.getResultado().toLowerCase() + "         Patronal   :   " + datoPacEmp.getRegistro(), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table1.addCell(cell);

            cell = new PdfPCell(new Phrase("Municipio    :     " + dato.getLocalidad(), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table1.addCell(cell);

            cell = new PdfPCell(new Phrase("Direccion   :    " + ((datoPaciente.getDireccion().length() > 27) ? datoPaciente.getDireccion().substring(0, 27) : datoPaciente.getDireccion()) + "       Telefono  :    " + datoPaciente.getTelefono(), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table1.addCell(cell);
        }

        cell = new PdfPCell(new Phrase("Servicio    :     " + datoMed.getConsultorio(), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table1.addCell(cell);

        Cuadernos datoLab = (Cuadernos) ldatoLab.get(0);
        cell = new PdfPCell(new Phrase("Fecha Pedido :    " + format(datoLab.getFechap(), "dd/MM/yyyy        HH:mm") + "                       Fecha Realizacion :    " + format(datoLab.getFechae(), "dd/MM/yyyy       HH:mm"), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("Num. Registro:" + datoLab.getId_cuaderno() + ";---;HCL:" + datoPaciente.getHcl(), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table1.addCell(cell);

        document.add(table1);

        PdfPTable table2 = new PdfPTable(3);
        int[] f2widths = {60, 15, 25}; // percentage
        table2.setWidths(f2widths);
        table2.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("Nombre Paciente      :  " + datoPaciente.getNombres() + "         [ " + datoPaciente.getNro_registro() + "]", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table2.addCell(cell);

        cell = new PdfPCell(new Phrase("Edad   :  " + "  años", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table2.addCell(cell);

        cell = new PdfPCell(new Phrase(datoLab.getId_tipo_sexo() == 1 ? "Sexo : Femenino" : "SEXO : Masculino", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table2.addCell(cell);
        /*
     if("E".equals(datoHis.getExpedido())){
        cell = new PdfPCell(new Phrase("Establecimiento Solicitante :    "+ lista.getNombre()+"                                                          Tipo Seguro:  EXTERNO", DATO_FONT));    
     }else{
         if("S".equals(datoHis.getExpedido())){
             cell = new PdfPCell(new Phrase("Establecimiento Solicitante :    "+ lista.getNombre()+"                                                          Tipo Seguro:  SUMI", DATO_FONT));    
         }else{
             if("P".equals(datoHis.getExpedido())){
                 for (int i = 0; i < seguros.size(); i++) {
                    Seguros prog = (Seguros) seguros.get(i);	
                     if(datoHis.getId_seguro()==prog.getId_seguro()){
                        cell = new PdfPCell(new Phrase("Establecimiento Solicitante :    "+ lista.getNombre()+"                                                          Tipo Seguro : "+  prog.getSeguro(), DATO_FONT));        
                     } 
                  }
               
             }
         }
     }
         */
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setColspan(3);
        table2.addCell(cell);

        document.add(table2);

        PdfPTable table = new PdfPTable(4);
        int[] fxxwidths = {30, 20, 30, 20}; // percentage
        table.setWidths(fxxwidths);
        table.setWidthPercentage(100);

        document.add(table);

        //AGREGAMOS LABORATORIOS GENERALES
        PdfPTable table7 = new PdfPTable(4);
        int[] fxxwidths7 = {30, 20, 30, 20}; // percentage
        table7.setWidths(fxxwidths7);
        table7.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("RESULTADO", DATO_FONT_B));
        cell.setHorizontalAlignment(Element.ALIGN_TOP);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        //cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(4);
        cell.setGrayFill(0.9f);
        table7.addCell(cell);

        // laboratorioss dados
        for (int i = 0; i < ldatoLab.size(); i++) {
            Cuadernos datoLab1 = (Cuadernos) ldatoLab.get(i);
            if (datoLab1.getResultado() == null || "null".equals(datoLab1.getResultado())) {
                datoLab1.setResultado("");
            } else {
                //cadena = datoLab1.getResultado().split("<p>"); 
                datoLab1.setResultado(datoLab1.getResultado().replaceAll("<p style=", ""));
                datoLab1.setResultado(datoLab1.getResultado().replaceAll("font-family: Verdana;", ""));
                datoLab1.setResultado(datoLab1.getResultado().replaceAll("font-size:", ""));
                datoLab1.setResultado(datoLab1.getResultado().replaceAll("11px;", ""));
                datoLab1.setResultado(datoLab1.getResultado().replaceAll("color:", ""));
                datoLab1.setResultado(datoLab1.getResultado().replaceAll("rgb(81, 81, 81);", ""));
                datoLab1.setResultado(datoLab1.getResultado().replaceAll("line-height:", ""));
                datoLab1.setResultado(datoLab1.getResultado().replaceAll("14.3px;", ""));
                datoLab1.setResultado(datoLab1.getResultado().replaceAll("text-align:", ""));
                datoLab1.setResultado(datoLab1.getResultado().replaceAll("justify;", ""));
                datoLab1.setResultado(datoLab1.getResultado().replaceAll("rgb(81, 81, 81);", "\n"));
                datoLab1.setResultado(datoLab1.getResultado().replaceAll("<p>", " "));
                datoLab1.setResultado(datoLab1.getResultado().replaceAll("</p>", "!"));
                datoLab1.setResultado(datoLab1.getResultado().replaceAll("&nbsp;", ""));
                datoLab1.setResultado(datoLab1.getResultado().replaceAll("<strong>", ""));
                datoLab1.setResultado(datoLab1.getResultado().replaceAll("</strong>", ""));
                datoLab1.setResultado(datoLab1.getResultado().replaceAll("<br />", ""));
                datoLab1.setResultado(datoLab1.getResultado().replaceAll("<u>", ""));
                datoLab1.setResultado(datoLab1.getResultado().replaceAll("</u>", ""));
                datoLab1.setResultado(datoLab1.getResultado().replaceAll("<ol>", ""));
                datoLab1.setResultado(datoLab1.getResultado().replaceAll("</ol>", ""));
                datoLab1.setResultado(datoLab1.getResultado().replaceAll("<ul>", ""));
                datoLab1.setResultado(datoLab1.getResultado().replaceAll("</ul>", ""));
                datoLab1.setResultado(datoLab1.getResultado().replaceAll("<li>", ""));
                datoLab1.setResultado(datoLab1.getResultado().replaceAll("</li>", ""));
                datoLab1.setResultado(datoLab1.getResultado().replaceAll("&ntilde;", "n"));
                datoLab1.setResultado(datoLab1.getResultado().replaceAll("&Ntilde;", "N"));
                datoLab1.setResultado(datoLab1.getResultado().replaceAll("&Aacute;", "A"));
                datoLab1.setResultado(datoLab1.getResultado().replaceAll("&Eacute;", "E"));
                datoLab1.setResultado(datoLab1.getResultado().replaceAll("&Iacute;", "I"));
                datoLab1.setResultado(datoLab1.getResultado().replaceAll("&Oacute;", "O"));
                datoLab1.setResultado(datoLab1.getResultado().replaceAll("&Uacute;", "U"));
                datoLab1.setResultado(datoLab1.getResultado().replaceAll("&Ugrave;", "U"));
                datoLab1.setResultado(datoLab1.getResultado().replaceAll("&Ograve;", "O"));
                datoLab1.setResultado(datoLab1.getResultado().replaceAll("&Agrave;", "A"));
                datoLab1.setResultado(datoLab1.getResultado().replaceAll("&Egrave;", "E"));
                datoLab1.setResultado(datoLab1.getResultado().replaceAll("&Igrave;", "I"));
                datoLab1.setResultado(datoLab1.getResultado().replaceAll("&aacute;", "a"));
                datoLab1.setResultado(datoLab1.getResultado().replaceAll("&eacute;", "e"));
                datoLab1.setResultado(datoLab1.getResultado().replaceAll("&iacute;", "i"));
                datoLab1.setResultado(datoLab1.getResultado().replaceAll("&oacute;", "o"));
                datoLab1.setResultado(datoLab1.getResultado().replaceAll("&uacute;", "u"));
                datoLab1.setResultado(datoLab1.getResultado().replaceAll("<em>", " "));
                datoLab1.setResultado(datoLab1.getResultado().replaceAll("</em>", " "));
                datoLab1.setResultado(datoLab1.getResultado().replaceAll("&quot;", "'"));
                datoLab1.setResultado(datoLab1.getResultado().replaceAll("<span style=", ""));
                datoLab1.setResultado(datoLab1.getResultado().replaceAll("font-size: 7.5pt;", ""));
                datoLab1.setResultado(datoLab1.getResultado().replaceAll("</span>", ""));
                datoLab1.setResultado(datoLab1.getResultado().replaceAll("<span>", ""));
                datoLab1.setResultado(datoLab1.getResultado().replaceAll("</strong", ""));
                datoLab1.setResultado(datoLab1.getResultado().replaceAll("<strong", ""));
                datoLab1.setResultado(datoLab1.getResultado().replaceAll("font-weight: normal;", ""));
                datoLab1.setResultado(datoLab1.getResultado().replaceAll("font-family: Verdana", ""));
                datoLab1.setResultado(datoLab1.getResultado().replaceAll("''", ""));
                datoLab1.setResultado(datoLab1.getResultado().replaceAll(">", ""));

            }

            cell = new PdfPCell(new Phrase(datoLab1.getResultado(), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(4);
            table7.addCell(cell);

        }

        for (int j = 0; j < listaDetEndo.size(); j++) {
            Cuadernos listaDetE = (Cuadernos) listaDetEndo.get(j);
            Image img = Image.getInstance("/var/lib/tomcat8/webapps/endoscopia" + listaDetE.getResultado());

            cell = new PdfPCell(img);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            //cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(2);
            table7.addCell(cell);
        }

        document.add(table7);

    }

    public static String format(Date dia, String formato) {
        if (dia == null) {
            return "";
        }
        SimpleDateFormat formatDate = new SimpleDateFormat(formato);
        return formatDate.format(dia);
    }

}
