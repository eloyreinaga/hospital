package org.ayaic.web.administrarlaboratorio;

import java.awt.Color;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.view.document.AbstractPdfView;
import java.util.*;
import java.text.*;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Costos;
import org.ayaic.domain.Cuadernos;
import org.ayaic.domain.Historiales;
import org.ayaic.domain.Localidades;
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.Personas;
import org.ayaic.domain.Seguros;

public class VerLaboratorioUrgCNSPDF extends AbstractPdfView {

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

        java.util.List ldatoLab = (java.util.List) model.get("datosLab");
        java.util.List ListaLab = (java.util.List) model.get("listarLab");
        java.util.List hemograma = (java.util.List) model.get("hemo");
        java.util.List orina = (java.util.List) model.get("orina");
        java.util.List listaPres = (java.util.List) model.get("listarPres");
        String consultorio = (String) model.get("consultorio");
        String accionl = (String) model.get("accionl");
        java.util.List seguros = (java.util.List) model.get("listaPacAfi");

        Historiales datoHis = (Historiales) model.get("datosHis");
        Cuadernos lista = (Cuadernos) model.get("lista");
        Localidades establecim = (Localidades) model.get("estab");
        Personas datoMed = (Personas) model.get("datosMed");
        Pacientes datoPac = (Pacientes) model.get("datosPac");
        Clientes dato = (Clientes) model.get("dato");
        Pacientes datoPaciente = (Pacientes) model.get("datos");
        String mes = (String) model.get("mes");
        String gestion = (String) model.get("gestion");
        DecimalFormat df = new DecimalFormat("###,##0");
        DecimalFormat dfx = new DecimalFormat("###,##0.00");

        // coloca el titulo de la pagina
        Image escudo = Image.getInstance("/opt/imagenes/lescudo.bmp");
        Paragraph p = new Paragraph("REPORTE DE RESULTADOS DE LABORATORIO\n EMERGENCIAS ", new Font(Font.HELVETICA, 12, Font.BOLDITALIC, new Color(0, 0, 0)));
        p.setAlignment(Element.ALIGN_CENTER);
        //Image sumi = Image.getInstance("/opt/imagenes/imglab.bmp");

        PdfPCell cell;

        PdfPTable tablex = new PdfPTable(3);
        int[] fxwidths = {20, 60, 20}; // percentage
        tablex.setWidths(fxwidths);
        tablex.setWidthPercentage(100);

        //cell = new PdfPCell(escudo);
        //cell.setHorizontalAlignment(Element.ALIGN_LEFT);        
        //cell.setBorder(Rectangle.NO_BORDER);
        //tablex.addCell(cell);
        cell = new PdfPCell(new Phrase(establecim.getEstablecimiento(), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex.addCell(cell);

        cell = new PdfPCell(p);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex.addCell(cell);

        cell = new PdfPCell(new Phrase("Form. DM 138 \n\nNum. Pedido :  " + lista.getNumpieza(), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex.addCell(cell);

        //cell = new PdfPCell(sumi);
        //cell.setHorizontalAlignment(Element.ALIGN_RIGHT);        
        //cell.setBorder(Rectangle.NO_BORDER);
        //tablex.addCell(cell);
        document.add(tablex);

        PdfPTable table1 = new PdfPTable(2);
        int[] fmwidths = {70, 30}; // percentage
        table1.setWidths(fmwidths);
        table1.setWidthPercentage(100);
        /*
     cell = new PdfPCell(new Phrase(establecim.getCodigolab(), DATO_FONT));
     cell.setHorizontalAlignment(Element.ALIGN_RIGHT); 
     cell.setBorder(Rectangle.NO_BORDER);
     cell.setColspan(2);
     table1.addCell(cell); 
     
     cell = new PdfPCell(new Phrase("SEDES                         :    " +dato.getDepartamento()+"                                    RED    :    "+dato.getRed(), DATO_FONT));
     cell.setHorizontalAlignment(Element.ALIGN_LEFT);     
     table1.addCell(cell);

     cell = new PdfPCell(new Phrase("Municipio    :     "+dato.getLocalidad() , DATO_FONT));
     cell.setHorizontalAlignment(Element.ALIGN_LEFT);               
     table1.addCell(cell);
    
     cell = new PdfPCell(new Phrase("Direccion   :    " +establecim.getDireccion()+"       Telefono  :    "+establecim.getTele1()+" ; "+establecim.getTele2(), DATO_FONT));
     cell.setHorizontalAlignment(Element.ALIGN_LEFT);     
     table1.addCell(cell);

     cell = new PdfPCell(new Phrase("Servicio    :     "+datoMed.getConsultorio() , DATO_FONT));
     cell.setHorizontalAlignment(Element.ALIGN_LEFT);               
     table1.addCell(cell);
         */
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

        cell = new PdfPCell(new Phrase("Edad   :  " + Integer.toString(datoHis.getEdad()) + "  años", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table2.addCell(cell);

        cell = new PdfPCell(new Phrase(datoLab.getId_tipo_sexo() == 1 ? "Sexo : Femenino" : "SEXO : Masculino", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table2.addCell(cell);

        if ("E".equals(datoHis.getExpedido())) {
            cell = new PdfPCell(new Phrase("Establecimiento Solicitante :    " + lista.getNombre() + "                                                          Tipo Seguro:  EXTERNO", DATO_FONT));
        } else {
            if ("S".equals(datoHis.getExpedido())) {
                cell = new PdfPCell(new Phrase("Establecimiento Solicitante :    " + lista.getNombre() + "                                                          Tipo Seguro:  SUMI", DATO_FONT));
            } else {
                if ("P".equals(datoHis.getExpedido())) {
                    for (int i = 0; i < seguros.size(); i++) {
                        Seguros prog = (Seguros) seguros.get(i);
                        if (datoHis.getId_seguro() == prog.getId_seguro()) {
                            cell = new PdfPCell(new Phrase("Establecimiento Solicitante :    " + lista.getNombre() + "                                                          Tipo Seguro : " + prog.getSeguro(), DATO_FONT));
                        }
                    }

                }
            }
        }
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setColspan(3);
        table2.addCell(cell);

        document.add(table2);

        PdfPTable table = new PdfPTable(4);
        int[] fxxwidths = {30, 20, 30, 20}; // percentage
        table.setWidths(fxxwidths);
        table.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("Medico Solicitante              :    " + lista.getNombres() + "                Servicio   :   " + datoMed.getConsultorio(), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setColspan(4);
        table.addCell(cell);

        //cell = new PdfPCell(new Phrase("INFORME DE LABORATORIO O DEL SERVICIO DE IMAGENOLOGï¿½A/GABINETE:", DATO_FONT_A));
        //cell.setHorizontalAlignment(Element.ALIGN_TOP);
        //cell.setBorder(Rectangle.NO_BORDER);
        //cell.setColspan(4);
        //cell.setGrayFill(0.9f);
        //table.addCell(cell);  
        document.add(table);
        ///if(hemograma.size()>0  && Integer.parseInt(consultorio)==10 && ("general".equals(accionl) || "hemograma".equals(accionl))){ 
        if (hemograma.size() > 0 && ("general".equals(accionl) || "hemograma".equals(accionl))) {

            Cuadernos HEMO = (Cuadernos) hemograma.get(0);

            PdfPTable tableH = new PdfPTable(4);
            int[] fmwidthsh = {25, 25, 25, 25}; // percentage
            tableH.setWidths(fmwidthsh);
            tableH.setWidthPercentage(100);

            cell = new PdfPCell(new Phrase("HEMOGRAMA ", DATO_FONT_A));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setGrayFill(0.9f);
            cell.setColspan(4);
            tableH.addCell(cell);

            cell = new PdfPCell(new Phrase("Globulos Rojos :        " + HEMO.getSglorojos(), DATO_FONT_A));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            tableH.addCell(cell);

            cell = new PdfPCell(new Phrase("xuL                 4.6 - 5.4", DATO_FONT_A));  ////5.8 - 6.8
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setBorder(Rectangle.NO_BORDER);
            tableH.addCell(cell);

            cell = new PdfPCell(new Phrase("Globulos Blancos :         " + HEMO.getSblancos(), DATO_FONT_A));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            tableH.addCell(cell);

            cell = new PdfPCell(new Phrase("xuL                 5,0 - 8,0", DATO_FONT_A));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setBorder(Rectangle.RIGHT);
            tableH.addCell(cell);

            cell = new PdfPCell(new Phrase("Hematocrito :             " + HEMO.getShemato(), DATO_FONT_A));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            tableH.addCell(cell);

            cell = new PdfPCell(new Phrase("%    Var(49-57) Muj(44-53)", DATO_FONT_A));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setBorder(Rectangle.NO_BORDER);
            tableH.addCell(cell);

            cell = new PdfPCell(new Phrase("Hemoglobina :               " + HEMO.getShemoglo(), DATO_FONT_A));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            tableH.addCell(cell);

            cell = new PdfPCell(new Phrase("g/dL  Var(169-184) Muj(144-174)", DATO_FONT_A));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setBorder(Rectangle.RIGHT);
            tableH.addCell(cell);

            cell = new PdfPCell(new Phrase("RECUENTO DIFERENCIAL DE LEUCOCITOS PORCENTUAL                                                                                                                                   ", DATO_FONT_A));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setGrayFill(0.9f);
            cell.setColspan(4);
            tableH.addCell(cell);

            cell = new PdfPCell(new Phrase("Seg :         " + HEMO.getSseg() + "                 (55 - 65)", DATO_FONT_A));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            tableH.addCell(cell);

            cell = new PdfPCell(new Phrase("Linf :          " + HEMO.getSlinf() + "                  (25 - 35)", DATO_FONT_A));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            tableH.addCell(cell);

            cell = new PdfPCell(new Phrase("Mono :        " + HEMO.getSmono() + "                     (2 - 6)", DATO_FONT_A));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            tableH.addCell(cell);

            cell = new PdfPCell(new Phrase("Eos :           " + HEMO.getSeos() + "                    (0 - 4)", DATO_FONT_A));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            tableH.addCell(cell);

            cell = new PdfPCell(new Phrase("Cay :         " + HEMO.getScay() + "                        (0 - 3)", DATO_FONT_A));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            tableH.addCell(cell);

            cell = new PdfPCell(new Phrase("Bas :          " + HEMO.getSbas() + "                    ( 0 - 1 )", DATO_FONT_A));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            tableH.addCell(cell);

            cell = new PdfPCell(new Phrase("Juy :           " + HEMO.getSjuy() + "                     ( 0 )", DATO_FONT_A));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            tableH.addCell(cell);

            cell = new PdfPCell(new Phrase("Mielo :         " + HEMO.getSmielo() + "                    ( 0 )", DATO_FONT_A));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            tableH.addCell(cell);

            cell = new PdfPCell(new Phrase("MORFOLOGIA SANGUINEA", DATO_FONT_A));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setGrayFill(0.9f);
            cell.setColspan(2);
            tableH.addCell(cell);

            cell = new PdfPCell(new Phrase("PARAMETROS", DATO_FONT_A));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setGrayFill(0.9f);
            cell.setColspan(2);
            tableH.addCell(cell);

            cell = new PdfPCell(new Phrase("Plaquetas :              " + HEMO.getSmplaquetas() + "    x uL", DATO_FONT_A));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(2);
            tableH.addCell(cell);

            cell = new PdfPCell(new Phrase("T. Protombina   :   " + HEMO.getCetonas() + "    x uL", DATO_FONT_A));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(2);
            tableH.addCell(cell);

            cell = new PdfPCell(new Phrase("% Actividad :  " + HEMO.getReaccion(), DATO_FONT_A));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(2);
            tableH.addCell(cell);

            cell = new PdfPCell(new Phrase("I.N.R.  :     " + HEMO.getSmindreti() + "             % ", DATO_FONT_A));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(2);
            tableH.addCell(cell);

            cell = new PdfPCell(new Phrase("OTROS :                 " + HEMO.getSmotros(), DATO_FONT_A));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(4);
            tableH.addCell(cell);

            document.add(tableH);
        }
        //ANALISIS DE ORINA     
        ///if(orina.size()>0 && Integer.parseInt(consultorio)==10 && ("general".equals(accionl) || "orinas".equals(accionl))){ 
        if (orina.size() > 0 && ("general".equals(accionl) || "orinas".equals(accionl))) {

            Cuadernos ORINA = (Cuadernos) orina.get(0);

            PdfPTable tableO = new PdfPTable(6);
            int[] fmwidthsh = {13, 13, 13, 13, 13, 13}; // percentage
            tableO.setWidths(fmwidthsh);
            tableO.setWidthPercentage(100);

            cell = new PdfPCell(new Phrase("A  N  A  L  I  S  I  S    D  E    O  R  I  N  A", DATO_FONT_A));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setGrayFill(0.9f);
            cell.setColspan(6);
            tableO.addCell(cell);

            cell = new PdfPCell(new Phrase("Cel. Epiteliales:" + ORINA.getEpiteliales() + "  xc", DATO_FONT_A));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(3);
            tableO.addCell(cell);

            cell = new PdfPCell(new Phrase("Cilindros :          " + ORINA.getCilindros() + "      xc", DATO_FONT_A));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(3);
            tableO.addCell(cell);

            cell = new PdfPCell(new Phrase("Leucocitos :    " + ORINA.getLeucocitos() + "      xc", DATO_FONT_A));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(3);
            tableO.addCell(cell);

            cell = new PdfPCell(new Phrase("Granulosos :      " + ORINA.getGranulosos() + "      xc", DATO_FONT_A));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(3);
            tableO.addCell(cell);

            cell = new PdfPCell(new Phrase("Hematies :      " + ORINA.getEmaties() + "     xc", DATO_FONT_A));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(3);
            tableO.addCell(cell);

            cell = new PdfPCell(new Phrase("Hialinos :           " + ORINA.getHialianos() + "      xc", DATO_FONT_A));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(3);
            tableO.addCell(cell);

            cell = new PdfPCell(new Phrase("Piocitos :        " + ORINA.getPiocitos() + "     xc", DATO_FONT_A));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(3);
            tableO.addCell(cell);

            cell = new PdfPCell(new Phrase("Leucocitarios:     " + ORINA.getLeucocitarios() + "     xc", DATO_FONT_A));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(3);
            tableO.addCell(cell);

            cell = new PdfPCell(new Phrase("Bacterias :      " + ORINA.getBacterias(), DATO_FONT_A));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(3);
            tableO.addCell(cell);

            cell = new PdfPCell(new Phrase("Cristales :           " + ORINA.getCristales() + "      xc", DATO_FONT_A));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(3);
            tableO.addCell(cell);

            cell = new PdfPCell(new Phrase("Otros:    " + ORINA.getSmotros(), DATO_FONT_A));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(6);
            tableO.addCell(cell);

            cell = new PdfPCell(new Phrase("Observaciones :    " + ORINA.getObservaciones(), DATO_FONT_A));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(6);
            tableO.addCell(cell);

            document.add(tableO);
        }

        if ("hematologia".equals(accionl) || "quimicas".equals(accionl) || "serologia".equals(accionl) || "orinas2".equals(accionl) || "copros".equals(accionl)) { //imprime solo hematologia

            PdfPTable table7 = new PdfPTable(4);
            int[] fxxwidths7 = {30, 20, 30, 20}; // percentage
            table7.setWidths(fxxwidths7);
            table7.setWidthPercentage(100);

            if ("hematologia".equals(accionl)) {
                cell = new PdfPCell(new Phrase("RESULTADOS EXAMEN DE LABORATORIO SOLO HEMATOLOGA", DATO_FONT_B));
            }
            if ("quimicas".equals(accionl)) {
                cell = new PdfPCell(new Phrase("RESULTADOS EXAMEN DE LABORATORIO SOLO QUIMICA SANGUINEA", DATO_FONT_B));
            }
            if ("serologia".equals(accionl)) {
                cell = new PdfPCell(new Phrase("RESULTADOS EXAMEN DE LABORATORIO SOLO SEROLOGIA", DATO_FONT_B));
            }
            if ("orinas2".equals(accionl)) {
                cell = new PdfPCell(new Phrase("RESULTADOS EXAMEN DE LABORATORIO SOLO ORINAS", DATO_FONT_B));
            }
            if ("copros".equals(accionl)) {
                cell = new PdfPCell(new Phrase("RESULTADOS EXAMEN DE LABORATORIO SOLO COPROPARACITOLOGIA", DATO_FONT_B));
            }

            cell.setHorizontalAlignment(Element.ALIGN_TOP);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(4);
            cell.setGrayFill(0.9f);
            table7.addCell(cell);

            String[] cadena = "".split("");

            // laboratorioss dados
            for (int i = 0; i < ldatoLab.size(); i++) {
                Cuadernos datoLab1 = (Cuadernos) ldatoLab.get(i);
                if (datoLab1.getResultado() == null || "null".equals(datoLab1.getResultado())) {
                    datoLab1.setResultado("");
                } else {
                    //cadena = datoLab1.getResultado().split("<p>"); 
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
                cadena = datoLab1.getResultado().trim().split("!");
                String normal = "";
                if (datoLab1.getId_costo() != 137 && datoLab1.getId_costo() != 121) {
                    int dd = ListaLab.size();
                    for (int j = 0; j < ListaLab.size(); j++) {
                        Costos listaLab = (Costos) ListaLab.get(j);
                        if (listaLab.getId_costo() == datoLab1.getId_costo()) {
                            if (listaLab.getNormales().length() > 2) {
                                cell = new PdfPCell(new Phrase(datoLab1.getLaboratorio() + "(" + datoLab1.getTipoconsulta() + ")" + ":      " + datoLab1.getResultado().trim(), DATO_FONT_B));
                                cell.setHorizontalAlignment(Element.ALIGN_TOP);
                                cell.setColspan(2);
                                table7.addCell(cell);

                                cell = new PdfPCell(new Phrase(listaLab.getNormales(), DATO_FONT_B));
                                cell.setHorizontalAlignment(Element.ALIGN_TOP);
                                cell.setColspan(2);
                                table7.addCell(cell);
                            } else {
                                if (datoLab1.getId_costo() == 129) {
                                    cell = new PdfPCell(new Phrase(datoLab1.getLaboratorio() + "(" + datoLab1.getTipoconsulta() + ")" + ":    '" + datoLab1.getResultado().substring(0, 2).trim() + "'  (RH) " + datoLab1.getResultado().substring(3, 11).trim(), DATO_FONT_B));
                                    cell.setHorizontalAlignment(Element.ALIGN_TOP);
                                    cell.setColspan(4);
                                    table7.addCell(cell);
                                } else {
                                    if (cadena.length > 1) {
                                        cell = new PdfPCell(new Phrase(datoLab1.getLaboratorio() + "(" + datoLab1.getTipoconsulta() + ")", DATO_FONT_B));
                                        cell.setHorizontalAlignment(Element.ALIGN_TOP);
                                        cell.setColspan(4);
                                        table7.addCell(cell);

                                        PdfPTable tablet = new PdfPTable(1);
                                        int[] ftwidths = {100}; // percentage
                                        tablet.setWidths(ftwidths);
                                        tablet.setWidthPercentage(100);
                                        ////para ecografias y otros
                                        for (int k = 0; k < cadena.length; k++) {
                                            cell = new PdfPCell(new Phrase("  " + cadena[k].trim(), DATO_FONT_B));
                                            cell.setBorder(Rectangle.NO_BORDER);
                                            tablet.addCell(cell);
                                        }
                                        cell = new PdfPCell(tablet);
                                        cell.setColspan(4);
                                        table7.addCell(cell);
                                    } else {
                                        cell = new PdfPCell(new Phrase(datoLab1.getLaboratorio() + "(" + datoLab1.getTipoconsulta() + ");    " + cadena[0].trim(), DATO_FONT_B));
                                        cell.setHorizontalAlignment(Element.ALIGN_TOP);
                                        cell.setColspan(4);
                                        table7.addCell(cell);
                                    }
                                }
                            }
                        }
                    }
                } else {
                    if (hemograma.isEmpty() && orina.isEmpty()) {
                        cell = new PdfPCell(new Phrase(datoLab1.getLaboratorio() + "(" + datoLab1.getTipoconsulta() + ")" + "  :==>>  " + datoLab1.getResultado(), DATO_FONT_B));
                        cell.setHorizontalAlignment(Element.ALIGN_TOP);
                        cell.setBorder(Rectangle.NO_BORDER);
                        table7.addCell(cell);
                    }
                }
                /*
      if("general".equals(accionl) && datoLab.getId_laboratorio()==136){
          String s=datoMed.getFirma();
         Image sumia = Image.getInstance(datoLab1.getOtros()); 

         cell = new PdfPCell(sumia);
         cell.setHorizontalAlignment(Element.ALIGN_CENTER);        
         cell.setBorder(Rectangle.NO_BORDER);
         cell.setColspan(4);
         table7.addCell(cell);
      } 
                 */

            }
            document.add(table7);
        }   //fin de impresion laboraotrio general

        //AGREGAMOS LABORATORIOS GENERALES
        if ("general".equals(accionl) || "otros".equals(accionl) || "cito".equals(accionl)) { //imprime GENERALES Y OTROS

            PdfPTable table7 = new PdfPTable(4);
            int[] fxxwidths7 = {30, 20, 30, 20}; // percentage
            table7.setWidths(fxxwidths7);
            table7.setWidthPercentage(100);

            cell = new PdfPCell(new Phrase("QUIMICA SANGUINEA, SEROLOGIA Y OTROS", DATO_FONT_B));
            cell.setHorizontalAlignment(Element.ALIGN_TOP);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            //cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(4);
            cell.setGrayFill(0.9f);
            table7.addCell(cell);

            String[] cadena = "".split("");

            // laboratorioss dados
            for (int i = 0; i < ldatoLab.size(); i++) {
                Cuadernos datoLab1 = (Cuadernos) ldatoLab.get(i);
                if (datoLab1.getResultado() == null || "null".equals(datoLab1.getResultado())) {
                    datoLab1.setResultado("");
                } else {
                    //cadena = datoLab1.getResultado().split("<p>"); 
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
                cadena = datoLab1.getResultado().trim().split("!");
                String normal = "";
                if (datoLab1.getId_costo() != 137 && datoLab1.getId_costo() != 121) {
                    int dd = ListaLab.size();
                    for (int j = 0; j < ListaLab.size(); j++) {
                        Costos listaLab = (Costos) ListaLab.get(j);
                        if (listaLab.getId_costo() == datoLab1.getId_costo()) {
                            if (listaLab.getNormales().length() > 2) {
                                cell = new PdfPCell(new Phrase(datoLab1.getLaboratorio() + "(" + datoLab1.getTipoconsulta() + ")" + ": " + datoLab1.getResultado().trim(), DATO_FONT_B));
                                cell.setHorizontalAlignment(Element.ALIGN_TOP);
                                cell.setColspan(2);
                                table7.addCell(cell);

                                cell = new PdfPCell(new Phrase(listaLab.getNormales(), DATO_FONT_B));
                                cell.setHorizontalAlignment(Element.ALIGN_TOP);
                                cell.setColspan(2);
                                table7.addCell(cell);
                            } else {
                                if (datoLab1.getId_costo() == 129 && datoLab1.getResultado().length() > 1) {
                                    cell = new PdfPCell(new Phrase(datoLab1.getLaboratorio() + "(" + datoLab1.getTipoconsulta() + ")" + ": '" + datoLab1.getResultado().substring(0, 2).trim() + "'  (RH) " + datoLab1.getResultado().substring(3, 11).trim(), DATO_FONT_B));
                                    cell.setHorizontalAlignment(Element.ALIGN_TOP);
                                    cell.setColspan(4);
                                    table7.addCell(cell);
                                } else {
                                    if (cadena.length > 1) {
                                        cell = new PdfPCell(new Phrase(datoLab1.getLaboratorio() + "(" + datoLab1.getTipoconsulta() + ")", DATO_FONT_B));
                                        cell.setHorizontalAlignment(Element.ALIGN_TOP);
                                        cell.setColspan(4);
                                        table7.addCell(cell);

                                        PdfPTable tablet = new PdfPTable(1);
                                        int[] ftwidths = {100}; // percentage
                                        tablet.setWidths(ftwidths);
                                        tablet.setWidthPercentage(100);
                                        ////para ecografias y otros
                                        for (int k = 0; k < cadena.length; k++) {
                                            cell = new PdfPCell(new Phrase("  " + cadena[k].trim(), DATO_FONT_B));
                                            cell.setBorder(Rectangle.NO_BORDER);
                                            tablet.addCell(cell);
                                        }
                                        cell = new PdfPCell(tablet);
                                        cell.setColspan(4);
                                        table7.addCell(cell);
                                    } else {
                                        cell = new PdfPCell(new Phrase(datoLab1.getLaboratorio() + "(" + datoLab1.getTipoconsulta() + ") =   " + cadena[0].trim(), DATO_FONT_B));
                                        cell.setHorizontalAlignment(Element.ALIGN_TOP);
                                        cell.setColspan(4);
                                        table7.addCell(cell);
                                    }
                                }
                            }
                        }
                    }
                } else {
                    if (hemograma.isEmpty() && orina.isEmpty()) {
                        cell = new PdfPCell(new Phrase(datoLab1.getLaboratorio() + "(" + datoLab1.getTipoconsulta() + ")" + "  :==>>  " + datoLab1.getResultado(), DATO_FONT_B));
                        cell.setHorizontalAlignment(Element.ALIGN_TOP);
                        cell.setBorder(Rectangle.NO_BORDER);
                        table7.addCell(cell);
                    }
                }
                /*
      if("general".equals(accionl) && datoLab.getId_costo()==136){
          String s=datoMed.getFirma();
         Image sumia = Image.getInstance(datoLab1.getOtros()); 

         cell = new PdfPCell(sumia);
         cell.setHorizontalAlignment(Element.ALIGN_CENTER);        
         cell.setBorder(Rectangle.NO_BORDER);
         cell.setColspan(4);
         table7.addCell(cell);
      } 
                 */

            }
            document.add(table7);
        }   //fin de impresion laboraotrio general

        PdfPTable tablez = new PdfPTable(4);
        int[] fzwidths = {25, 25, 25, 25}; // percentage
        tablez.setWidths(fzwidths);
        tablez.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase(dato.getNombres() + "\nFirma y Sello del responsable \n", DATO_FONT));
        cell.setFixedHeight(35f);
        cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tablez.addCell(cell);

        cell = new PdfPCell(new Phrase("\nSELLO SERVICIO", DATO_FONT));
        cell.setFixedHeight(35f);
        cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(3);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tablez.addCell(cell);

        document.add(tablez);

    }

    public static String format(Date dia, String formato) {
        if (dia == null) {
            return "";
        }
        SimpleDateFormat formatDate = new SimpleDateFormat(formato);
        return formatDate.format(dia);
    }

}
