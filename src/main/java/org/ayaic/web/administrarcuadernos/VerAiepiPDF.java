package org.ayaic.web.administrarcuadernos;

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
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.Personas;

public class VerAiepiPDF extends AbstractPdfView {

    private static final Font TITULO_FONT = new Font(Font.HELVETICA, 12, Font.BOLD, Color.black);
    private static final Font CABEZA_COLUMNA_FONT = new Font(Font.HELVETICA, 8, Font.ITALIC, Color.black);
    private static final Font DATO_FONT = new Font(Font.HELVETICA, 8, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_BOLD = new Font(Font.HELVETICA, 8, Font.BOLD, Color.black);
    private static final Font DATO_VERIFICADO = new Font(Font.TIMES_ROMAN, 9, Font.UNDERLINE, Color.BLUE);
    private static final int MARGIN = 32;

    protected void buildPdfMetadata(Map model, Document document, HttpServletRequest request) {
        document.setPageSize(PageSize.LETTER);
        document.setMargins(10, 10, 5, 5);
    }

    protected void buildPdfDocument(Map model, Document document, PdfWriter writer, HttpServletRequest req, HttpServletResponse resp) throws Exception {

        java.util.List lFopos = (java.util.List) model.get("listaCobros");
        java.util.List cie = (java.util.List) model.get("listaCie");
        java.util.List lvacunas = (java.util.List) model.get("vacunas");
        Clientes datos = (Clientes) model.get("datocli");
        java.util.List pacientess = (java.util.List) model.get("dato");
        Pacientes dato = (Pacientes) model.get("datosp");
        Personas medico = (Personas) model.get("datosPer");
        Historiales datosHis = (Historiales) model.get("datosHis");
        Cuadernos aiepi = (Cuadernos) model.get("aiepi");

        int bcg = 90, penta1 = 90, penta2 = 90, penta3 = 90, srp = 90, anti = 90;
        int una, filas = 30;

//      if(lFopos.size()==0){
//         Paragraph  p = new Paragraph("No existe Datos", new Font(Font.HELVETICA, 22, Font.BOLDITALIC, new Color(0, 0, 0)) );
        //        document.add(p);   
//     }    
        String tipo;

        if (datosHis.getMes() + datosHis.getEdad() * 12 < 2) {

            DecimalFormat df = new DecimalFormat("###,##0.00");
            Image escudo = Image.getInstance("/opt/imagenes/sumi.bmp");
            Paragraph p = new Paragraph("\nFORMULARIO DE REGISTRO \n AL NINIO/A DE 7 DIAS A MENOR DE 2 MESES DE EDAD", new Font(Font.HELVETICA, 14, Font.BOLDITALIC, new Color(0, 0, 0)));
            p.setAlignment(Element.ALIGN_CENTER);
            //Image sumi = Image.getInstance("/opt/imagenes/sumi.bmp");

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

            PdfPTable table1n = new PdfPTable(5);
            int[] fmwidths = {20, 20, 20, 20, 30}; // percentage
            table1n.setWidths(fmwidths);
            table1n.setWidthPercentage(100);

            cell = new PdfPCell(new Phrase("Nombres y Apellidos   :   " + dato.getNombres(), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(4);
            table1n.addCell(cell);

            cell = new PdfPCell(new Phrase(" Fecha  : " + format(datosHis.getFecha(), "dd/MM/yyyy"), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table1n.addCell(cell);

            cell = new PdfPCell(new Phrase("Edad : " + Integer.toString(datosHis.getEdad()) + " a " + Integer.toString(datosHis.getMes()) + " m " + Integer.toString(datosHis.getDia()) + " d", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table1n.addCell(cell);

            cell = new PdfPCell(new Phrase("Peso : " + Double.toString(datosHis.getPeso()) + "    Kg.", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table1n.addCell(cell);

            cell = new PdfPCell(new Phrase("Talla  :  " + Double.toString(datosHis.getTalla()) + "    cm.", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table1n.addCell(cell);

            cell = new PdfPCell(new Phrase("Temperatura : " + Double.toString(datosHis.getTemperatura()) + "    oC", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table1n.addCell(cell);

            if (datosHis.getId_sexo() == 1) {
                cell = new PdfPCell(new Phrase("Sexo :   FEMENINO", DATO_FONT));
            } else {
                cell = new PdfPCell(new Phrase("Sexo :   MASCULINO", DATO_FONT));
            }
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table1n.addCell(cell);

            cell = new PdfPCell(new Phrase("Porque trajo al niño /a?  :  " + aiepi.getTipodent() + "                                                                                        Esta inscrito /a en el BJA?", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(5);
            table1n.addCell(cell);

            PdfPTable table1x = new PdfPTable(6);
            int[] fxwid1 = {30, 3, 17, 30, 3, 17}; // percentage
            table1x.setWidths(fxwid1);
            table1x.setWidthPercentage(100);

            cell = new PdfPCell(new Phrase("Primera consulta  : ", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setBorder(Rectangle.NO_BORDER);
            table1x.addCell(cell);

            if ("N".equals(datosHis.getRepetida())) {
                cell = new PdfPCell(new Phrase("X", DATO_FONT));
            } else {
                cell = new PdfPCell(new Phrase("", DATO_FONT));
            }
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table1x.addCell(cell);

            cell = new PdfPCell(new Phrase("", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            table1x.addCell(cell);

            cell = new PdfPCell(new Phrase("Consulta de seguimiento  : ", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setBorder(Rectangle.NO_BORDER);
            table1x.addCell(cell);

            if ("R".equals(datosHis.getRepetida())) {
                cell = new PdfPCell(new Phrase("X", DATO_FONT));
            } else {
                cell = new PdfPCell(new Phrase("", DATO_FONT));
            }
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table1x.addCell(cell);

            cell = new PdfPCell(new Phrase("", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            table1x.addCell(cell);

            cell = new PdfPCell(table1x);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(5);
            table1n.addCell(cell);

            document.add(table1n);

            PdfPTable tablec = new PdfPTable(4);
            int[] fmwidtsc = {24, 40, 18, 18}; // percentage
            tablec.setWidths(fmwidtsc);
            tablec.setWidthPercentage(100);

            cell = new PdfPCell(new Phrase("Evaluar (Marcar con un circulo los signos presentes)", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(2);
            tablec.addCell(cell);

            cell = new PdfPCell(new Phrase("CALIFICAR", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            tablec.addCell(cell);

            cell = new PdfPCell(new Phrase("TRATAR", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            tablec.addCell(cell);

            cell = new PdfPCell(new Phrase("DETERMINAR SI SE TRATA DE UNA INFECCION BACTERIANA", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setGrayFill(0.8f);
            cell.setColspan(2);
            tablec.addCell(cell);

            cell = new PdfPCell(new Phrase("", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setGrayFill(0.8f);
            tablec.addCell(cell);

            cell = new PdfPCell(new Phrase("", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setGrayFill(0.8f);
            tablec.addCell(cell);

            cell = new PdfPCell(new Phrase("PREGUNTAR\nEl niño/a tiene\noConvulsiones o ataques?\nNo puede mamar o\nalimentarse?\nVomita todo lo que mama? \n\n\n\n\n\n\n\n\n\n\n\n\n Respiracion rapida es : \n Menor de 2 meses 60  o mas \n por minuto\n Respiracion lenta es \n Menor de 2 meses 30 o menos\npor minuto", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.LEFT);
            tablec.addCell(cell);

            PdfPTable table21 = new PdfPTable(1);

            cell = new PdfPCell(new Phrase("VERIFICAR", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            table21.addCell(cell);

            if ((aiepi.getSuma1() & 1) != 1) {
                cell = new PdfPCell(new Phrase("1.  Esta letargico inconciente hipoactivo?", DATO_FONT));
                cell.setBorder(Rectangle.NO_BORDER);
            } else {
                cell = new PdfPCell(new Phrase("1.  Esta letargico inconciente hipoactivo?", DATO_VERIFICADO));
            }
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table21.addCell(cell);

            if ((aiepi.getSuma1() & 2) != 2) {
                cell = new PdfPCell(new Phrase("2. Tiene dificultad para respirar?\n-Contar las respiraciones en un minuto ...resp/min.\n-Repetir si el recuento es alto o bajo\n-Hay tiraje subcostal severo?\n-Tiene aleteo nasal?\n-Tiene quejido espiratorio", DATO_FONT));
                cell.setBorder(Rectangle.NO_BORDER);
            } else {
                cell = new PdfPCell(new Phrase("2. Tiene dificultad para respirar?\n-Contar las respiraciones en un minuto ...resp/min.\n-Repetir si el recuento es alto o bajo\n-Hay tiraje subcostal severo?\n-Tiene aleteo nasal?\n-Tiene quejido espiratorio", DATO_VERIFICADO));
            }
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table21.addCell(cell);

            if ((aiepi.getSuma1() & 4) != 4) {
                cell = new PdfPCell(new Phrase("3.  Examinar y palpar la fontanela ?\n- Esta abombada?", DATO_FONT));
                cell.setBorder(Rectangle.NO_BORDER);
            } else {
                cell = new PdfPCell(new Phrase("3.  Examinar y palpar la fontanela ?\n- Esta abombada?", DATO_VERIFICADO));
            }
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table21.addCell(cell);

            if ((aiepi.getSuma1() & 8) != 8) {
                cell = new PdfPCell(new Phrase("4.  Examina el onbligo?\n-Esta enrojesido y presenta supuracion\n-Esta enrojesido se extiende a la piel", DATO_FONT));
                cell.setBorder(Rectangle.NO_BORDER);
            } else {
                cell = new PdfPCell(new Phrase("4.  Examina el onbligo?\n-Esta enrojesido y presenta supuracion\n-Esta enrojesido se extiende a la piel", DATO_VERIFICADO));
            }
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table21.addCell(cell);

            if ((aiepi.getSuma1() & 16) != 16) {
                cell = new PdfPCell(new Phrase("5. Tiene fiebre o esta muy frio/a (tomar la temperatura axilar o tocarlo para saber si esta muy caliente o muy frio)", DATO_FONT));
                cell.setBorder(Rectangle.NO_BORDER);
            } else {
                cell = new PdfPCell(new Phrase("5. Tiene fiebre o esta muy frio/a (tomar la temperatura axilar o tocarlo para saber si esta muy caliente o muy frio)", DATO_VERIFICADO));
            }
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table21.addCell(cell);

            if ((aiepi.getSuma1() & 32) != 32) {
                cell = new PdfPCell(new Phrase("6. Observar la piel para saber si tiene pustulas\n-Son muchas o extensas?", DATO_FONT));
                cell.setBorder(Rectangle.NO_BORDER);
            } else {
                cell = new PdfPCell(new Phrase("6. Observar la piel para saber si tiene pustulas\n-Son muchas o extensas?", DATO_VERIFICADO));
            }
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table21.addCell(cell);

            if ((aiepi.getSuma1() & 64) != 64) {
                cell = new PdfPCell(new Phrase("7. Tiene secresion ocular con o sin hinchazon de parpados?", DATO_FONT));
                cell.setBorder(Rectangle.NO_BORDER);
            } else {
                cell = new PdfPCell(new Phrase("7. Tiene secresion ocular con o sin hinchazon de parpados?", DATO_VERIFICADO));
            }
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table21.addCell(cell);

            if ((aiepi.getSuma1() & 128) != 128) {
                cell = new PdfPCell(new Phrase("8. Determinar el color de la piel\n-Tiene cianosis y palidez?\n-Tiene ictericia generalizada?", DATO_FONT));
                cell.setBorder(Rectangle.NO_BORDER);
            } else {
                cell = new PdfPCell(new Phrase("8. Determinar el color de la piel\n-Tiene cianosis y palidez?\n-Tiene ictericia generalizada?", DATO_VERIFICADO));
            }
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table21.addCell(cell);

            cell = new PdfPCell(table21);
            cell.setBorder(Rectangle.NO_BORDER);
            tablec.addCell(cell);

            cell = new PdfPCell(new Phrase(aiepi.getTipoconsulta(), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            tablec.addCell(cell);

            cell = new PdfPCell(new Phrase(aiepi.getResultado(), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            tablec.addCell(cell);
            ///////////se empieza a llenar la segunda fila del menor de 2 meses   
            if (aiepi.getAlcohol() == 0) {
                cell = new PdfPCell(new Phrase("EL NINIO/A MENOR DE 2 MESES TIENE DIARREA -------> SI ( )  NO  ( X )", DATO_FONT));
            } else {
                cell = new PdfPCell(new Phrase("EL NINIO/A MENOR DE 2 MESES TIENE DIARREA -------> SI ( X )  NO  ( )", DATO_FONT));
            }
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setGrayFill(0.8f);
            cell.setColspan(2);
            tablec.addCell(cell);

            cell = new PdfPCell(new Phrase("", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setGrayFill(0.8f);
            cell.setColspan(2);
            tablec.addCell(cell);

            cell = new PdfPCell(new Phrase("ïHace cuanto tiempo .." + aiepi.getAborto() + "..dias?\nHay sangre en las heces?\noCuantas veces a tenido diarrea en las ultimas 24 horas?", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            tablec.addCell(cell);

            PdfPTable table22 = new PdfPTable(1);

            if ((aiepi.getSuma2() & 1) != 1) {
                cell = new PdfPCell(new Phrase("1. Determinar el estado general del niño: \n -Esta letargico, o inconciente?\nInquieto o irritable?", DATO_FONT));
                cell.setBorder(Rectangle.NO_BORDER);
            } else {
                cell = new PdfPCell(new Phrase("1. Determinar el estado general del niño: \n -Esta letargico, o inconciente?\nInquieto o irritable?", DATO_VERIFICADO));
            }
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table22.addCell(cell);

            if ((aiepi.getSuma2() & 2) != 2) {
                cell = new PdfPCell(new Phrase("2. Tiene los ojos hundidos", DATO_FONT));
                cell.setBorder(Rectangle.NO_BORDER);
            } else {
                cell = new PdfPCell(new Phrase("2. Tiene los ojos hundidos", DATO_VERIFICADO));
            }
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table22.addCell(cell);

            if ((aiepi.getSuma2() & 4) != 4) {
                cell = new PdfPCell(new Phrase("3. Tiene signo del pliegue cutaneo?\n-La piel vuelve al estado anterior\nMuy lentamente (2 segundos o mas)\nLentamente (menos de 2 segundos)", DATO_FONT));
                cell.setBorder(Rectangle.NO_BORDER);
            } else {
                cell = new PdfPCell(new Phrase("3. Tiene signo del pliegue cutaneo?\n-La piel vuelve al estado anterior\nMuy lentamente (2 segundos o mas)\nLentamente (menos de 2 segundos)", DATO_VERIFICADO));
            }
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table22.addCell(cell);

            cell = new PdfPCell(table22);
            cell.setBorder(Rectangle.NO_BORDER);
            tablec.addCell(cell);

            cell = new PdfPCell(new Phrase(aiepi.getAccion(), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            tablec.addCell(cell);

            cell = new PdfPCell(new Phrase(aiepi.getBacterias(), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            tablec.addCell(cell);

            cell = new PdfPCell(new Phrase("EVALUAR SI HAY PROBLEMAS DE ALIMENTACION O BAJO PESO", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setGrayFill(0.8f);
            cell.setColspan(2);
            tablec.addCell(cell);

            cell = new PdfPCell(new Phrase("", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setGrayFill(0.8f);
            cell.setColspan(2);
            tablec.addCell(cell);

            cell = new PdfPCell(new Phrase("Recibe seno materno? SI ( ) NO ( ) Si la respuesta es afirmativa : \n-oCuantas veces en 24 horas? \n-Tiene alguna dificultad para mamar? \n Recibe otros alimentos, leche de vaca u otros liquidos? SI ( ) NO ( ) \nSi la respuesta es afirmativa \n-oCon que frecuencia? \n-Que alimentos o liquidos le da? \n-oCon que le da los otros alimentos o liquidos? \n-Recibe biberon (mamadera)?", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            tablec.addCell(cell);

            PdfPTable table23 = new PdfPTable(1);

            if ((aiepi.getSuma2() & 8) != 8) {
                cell = new PdfPCell(new Phrase("1. Tiene emaciacion visible?", DATO_FONT));
                cell.setBorder(Rectangle.NO_BORDER);
            } else {
                cell = new PdfPCell(new Phrase("1. Tiene emaciacion visible?", DATO_VERIFICADO));
            }
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table23.addCell(cell);

            if ((aiepi.getSuma2() & 16) != 16) {
                cell = new PdfPCell(new Phrase("2. El Peso / Edad en la curva, segun el sexo del niño/a esta: Por debajo de -3, segun el sexo Entre -2 y -3 segun el sexo Por encima de -2 segun el sexo", DATO_FONT));
                cell.setBorder(Rectangle.NO_BORDER);
            } else {
                cell = new PdfPCell(new Phrase("2. El Peso / Edad en la curva, segun el sexo del niño/a esta: Por debajo de -3, segun el sexo Entre -2 y -3 segun el sexo Por encima de -2 segun el sexo", DATO_VERIFICADO));
            }
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table23.addCell(cell);

            if ((aiepi.getSuma2() & 32) != 32) {
                cell = new PdfPCell(new Phrase("3. Tiene lesciones de moniliasis en la boca?", DATO_FONT));
                cell.setBorder(Rectangle.NO_BORDER);
            } else {
                cell = new PdfPCell(new Phrase("3. Tiene lesciones de moniliasis en la boca?", DATO_VERIFICADO));
            }
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table23.addCell(cell);

            if ((aiepi.getSuma2() & 64) != 64) {
                cell = new PdfPCell(new Phrase("4. Evaluar el amamantamiento (Posicion y agarre) ", DATO_FONT));
                cell.setBorder(Rectangle.NO_BORDER);
            } else {
                cell = new PdfPCell(new Phrase("4. Evaluar el amamantamiento (Posicion y agarre) ", DATO_VERIFICADO));
            }
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table23.addCell(cell);

            cell = new PdfPCell(table23);
            //cell.setBorder(Rectangle.NO_BORDER);
            tablec.addCell(cell);

            cell = new PdfPCell(new Phrase(aiepi.getAnti(), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            tablec.addCell(cell);

            cell = new PdfPCell(new Phrase(aiepi.getCetonas(), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            tablec.addCell(cell);

            cell = new PdfPCell(new Phrase("\nEVALUAR EL AMAMANTAMIENTO", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(4);
            tablec.addCell(cell);

            cell = new PdfPCell(new Phrase("ASPECTOS A EVALUAR", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setGrayFill(0.8f);
            tablec.addCell(cell);

            cell = new PdfPCell(new Phrase("PRACTICA IDEAL", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setGrayFill(0.8f);
            tablec.addCell(cell);

            cell = new PdfPCell(new Phrase("PRACTICA REAL", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setGrayFill(0.8f);
            tablec.addCell(cell);

            cell = new PdfPCell(new Phrase("CONDUCTA", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setGrayFill(0.8f);
            tablec.addCell(cell);

            cell = new PdfPCell(new Phrase("La posicion de mamar es correcta\n\n", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            tablec.addCell(cell);

            cell = new PdfPCell(new Phrase("La cabeza y el cuerpo del niño deben estar rectos\nLa nariz del niño debe estar frente al pezon\nEl cuerpo del niño debe estar pegado al de la madre\nLa madre debe sostener todo el cuerpo del niño\nBUENA POSICION - MALA POSICION", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            tablec.addCell(cell);

            cell = new PdfPCell(new Phrase("", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            tablec.addCell(cell);

            cell = new PdfPCell(new Phrase("", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            tablec.addCell(cell);

            cell = new PdfPCell(new Phrase(" El agarre es adecuado?\n\n", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            tablec.addCell(cell);

            cell = new PdfPCell(new Phrase("Toca la mama o pecho con el menton \ntiene la boca bien abierta\nTiene el labio inferior volteado hacia afuera\nSe ve mas areola por encima de la boca que por debajo\nBUEN AGARRE - MAL AGARRE", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            tablec.addCell(cell);

            cell = new PdfPCell(new Phrase("", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            tablec.addCell(cell);

            cell = new PdfPCell(new Phrase("", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            tablec.addCell(cell);

            cell = new PdfPCell(new Phrase("Succiona bien?\n\n", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            tablec.addCell(cell);

            cell = new PdfPCell(new Phrase("Succiona en forma lenta, profunda y con pausas ocacionales\nLa mama siente que el niño traga la leche\nBUENA SUCCION - MALA SUCCION", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            tablec.addCell(cell);

            cell = new PdfPCell(new Phrase("", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            tablec.addCell(cell);

            cell = new PdfPCell(new Phrase("", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            tablec.addCell(cell);

            cell = new PdfPCell(new Phrase("Tiene algun problema para darle de latar\n-oCual es el problema?\n", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            tablec.addCell(cell);

            cell = new PdfPCell(new Phrase("La mama debe comunicar al personal de salud cualquier problema con la lactancia (pesones adoloridos, llanto bebe, etc.)", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            tablec.addCell(cell);

            cell = new PdfPCell(new Phrase("", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            tablec.addCell(cell);

            cell = new PdfPCell(new Phrase("", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            tablec.addCell(cell);

            document.add(tablec);
            document.newPage();

            //empieza la nueva hoja
            PdfPTable table3 = new PdfPTable(4);
            int[] fmwid3 = {24, 40, 18, 18}; // percentage
            table3.setWidths(fmwid3);
            table3.setWidthPercentage(100);

            cell = new PdfPCell(new Phrase("\n\n", DATO_FONT));
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(4);
            table3.addCell(cell);

            PdfPTable table31 = new PdfPTable(6);
            int[] fxwid31 = {15, 17, 17, 17, 15, 18}; // percentage
            table31.setWidths(fxwid31);
            table31.setWidthPercentage(100);

            cell = new PdfPCell(new Phrase("VERIFCAR LOS ANTECEDENTES DE VACUNACIONES DEL NINIO /A ", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.ALIGN_TOP);
            cell.setColspan(6);
            table31.addCell(cell);

            for (int i = 0; i < lvacunas.size(); i++) {
                Cuadernos datov = (Cuadernos) lvacunas.get(i);
                if (datov.getBcg() == 1) {
                    bcg = i;
                }

            }
            if (bcg != 90) {
                Cuadernos datova = (Cuadernos) lvacunas.get(bcg);
                cell = new PdfPCell(new Phrase(format(datova.getFechap(), "dd/MM/yyyy") + "\n  BCG", DATO_FONT));
            } else {
                cell = new PdfPCell(new Phrase("_______\n  BCG", DATO_FONT));
            }
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(6);
            cell.setBorder(Rectangle.NO_BORDER);
            table31.addCell(cell);

            cell = new PdfPCell(table31);
            cell.setColspan(2);
            table3.addCell(cell);

            // if (aiepi.getEclam()==0)  
            //     cell = new PdfPCell(new Phrase("VACUNAS COMPLETAS PARA LA EDAD\n\n SI (  )  NO ( X )", DATO_FONT));
            //else
            cell = new PdfPCell(new Phrase("VACUNAS COMPLETAS PARA LA EDAD\n\n SI ( X )  NO (  )", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table3.addCell(cell);

            cell = new PdfPCell(new Phrase("VOLVER PARA LA PROXIMA VACUNA\n\n " + format(aiepi.getFec_egreso(), "dd/MM/yyyy"), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table3.addCell(cell);

            cell = new PdfPCell(new Phrase("EVALUAR EL DESARROLLO DEL NINIO\n\n Emplear el carnet de salud infantil", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(2);
            table3.addCell(cell);

            if (aiepi.getEndodoncia() == 0) {
                cell = new PdfPCell(new Phrase("Cumple con todos los hitos\n de desarrollo para la edad\n SI (  )  NO ( X )", DATO_FONT));
            } else {
                cell = new PdfPCell(new Phrase("Cumple con todos los hitos\n de desarrollo para la edad\n SI ( X )  NO (  )", DATO_FONT));
            }
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table3.addCell(cell);

            cell = new PdfPCell(new Phrase(aiepi.getGlucosa(), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table3.addCell(cell);

            cell = new PdfPCell(new Phrase("\nEVALUAR OTROS PROBLEMAS                                                                                                                       Calificar                                      Tratar", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(4);
            table3.addCell(cell);

            cell = new PdfPCell(new Phrase("Pregunte a la madre o cuidador si el niño/a tiene otros problemas\n\n", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(2);
            table3.addCell(cell);

            cell = new PdfPCell(new Phrase(aiepi.getSangre(), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table3.addCell(cell);

            cell = new PdfPCell(new Phrase(aiepi.getSbas(), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table3.addCell(cell);

            cell = new PdfPCell(new Phrase("Pregunte por la salud de la madre\n\n", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(2);
            table3.addCell(cell);

            cell = new PdfPCell(new Phrase(aiepi.getSblancos(), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table3.addCell(cell);

            cell = new PdfPCell(new Phrase(aiepi.getScay(), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table3.addCell(cell);

            cell = new PdfPCell(new Phrase("\n\nVOLVER PARA LA CONSULTA DE SEGUIMIENTO O CONTROL   : ___" + format(aiepi.getFec_ingreso(), "dd/MM/yyyy"), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(4);
            table3.addCell(cell);

            cell = new PdfPCell(new Phrase("\n\nNONBRES Y APELLIDOS DEL PERSONAL DE SALUD :____" + medico.getNombres() + " " + medico.getPaterno() + " " + medico.getMaterno(), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(4);
            table3.addCell(cell);

            document.add(table3);

        }   ///////fin de menores de 2 meses
///Para niños mayores de 2 meses

        if (datosHis.getMes() + datosHis.getEdad() * 12 >= 2) {

            Image escudo = Image.getInstance("/opt/imagenes/sumi.bmp");
            Paragraph p = new Paragraph("ATENCION DE NINIO/A DE 2 MESES A MENOR DE 5 añoS DE EDAD (AIEPII - NUT)", new Font(Font.HELVETICA, 14, Font.BOLDITALIC, new Color(0, 0, 0)));
            p.setAlignment(Element.ALIGN_CENTER);
            //Image sumi = Image.getInstance("/opt/imagenes/sumi.bmp");

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

            PdfPTable table1n = new PdfPTable(5);
            int[] fmwidths = {20, 20, 20, 20, 30}; // percentage
            table1n.setWidths(fmwidths);
            table1n.setWidthPercentage(100);

            cell = new PdfPCell(new Phrase("ESTABLECIMIENTO DE SALUD   :   " + datos.getEstablecimiento(), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(5);
            table1n.addCell(cell);

            cell = new PdfPCell(new Phrase("Nombres y Apellidos   :   " + dato.getNombres(), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(4);
            table1n.addCell(cell);

            cell = new PdfPCell(new Phrase(" Fecha  : " + format(datosHis.getFecha(), "dd/MM/yyyy"), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table1n.addCell(cell);

            cell = new PdfPCell(new Phrase("Edad : " + Integer.toString(datosHis.getEdad()) + " a " + Integer.toString(datosHis.getMes()) + " m " + Integer.toString(datosHis.getDia()) + " d", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table1n.addCell(cell);

            cell = new PdfPCell(new Phrase("Peso : " + Double.toString(datosHis.getPeso()) + "    Kg.", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table1n.addCell(cell);

            cell = new PdfPCell(new Phrase("Talla  :  " + Double.toString(datosHis.getTalla()) + "    cm.", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table1n.addCell(cell);

            cell = new PdfPCell(new Phrase("Temperatura : " + Double.toString(datosHis.getTemperatura()) + "    oC", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table1n.addCell(cell);

            if (datosHis.getId_sexo() == 1) {
                cell = new PdfPCell(new Phrase("Sexo :   FEMENINO", DATO_FONT));
            } else {
                cell = new PdfPCell(new Phrase("Sexo :   MASCULINO", DATO_FONT));
            }
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table1n.addCell(cell);

            cell = new PdfPCell(new Phrase("Porque trajo al niño /a?  :  " + aiepi.getTipodent() + "                                                                                        Esta inscrito /a en el BJA?", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(5);
            table1n.addCell(cell);

            PdfPTable table1x = new PdfPTable(6);
            int[] fxwid1 = {30, 3, 17, 30, 3, 17}; // percentage
            table1x.setWidths(fxwid1);
            table1x.setWidthPercentage(100);

            cell = new PdfPCell(new Phrase("Primera consulta  : ", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setBorder(Rectangle.NO_BORDER);
            table1x.addCell(cell);

            if ("N".equals(datosHis.getRepetida())) {
                cell = new PdfPCell(new Phrase("X", DATO_FONT));
            } else {
                cell = new PdfPCell(new Phrase("", DATO_FONT));
            }
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table1x.addCell(cell);

            cell = new PdfPCell(new Phrase("", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            table1x.addCell(cell);

            cell = new PdfPCell(new Phrase("Consulta de seguimiento  : ", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setBorder(Rectangle.NO_BORDER);
            table1x.addCell(cell);

            if ("R".equals(datosHis.getRepetida())) {
                cell = new PdfPCell(new Phrase("X", DATO_FONT));
            } else {
                cell = new PdfPCell(new Phrase("", DATO_FONT));
            }
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table1x.addCell(cell);

            cell = new PdfPCell(new Phrase("", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            table1x.addCell(cell);

            cell = new PdfPCell(table1x);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(5);
            table1n.addCell(cell);

            document.add(table1n);

            PdfPTable tablec = new PdfPTable(4);
            int[] fmwidtsc = {24, 40, 18, 18}; // percentage
            tablec.setWidths(fmwidtsc);
            tablec.setWidthPercentage(100);

            cell = new PdfPCell(new Phrase("Evaluar (Marcar con un circulo los signos presentes)", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(2);
            tablec.addCell(cell);

            cell = new PdfPCell(new Phrase("CALIFICAR", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            tablec.addCell(cell);

            cell = new PdfPCell(new Phrase("TRATAR", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            tablec.addCell(cell);

            cell = new PdfPCell(new Phrase("VERIFICAR SI PRESENTA SIGNOS DE PELIGRO EN GENERAL", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(2);
            cell.setGrayFill(0.8f);
            tablec.addCell(cell);

            cell = new PdfPCell(new Phrase("", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setGrayFill(0.8f);
            tablec.addCell(cell);

            cell = new PdfPCell(new Phrase("", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setGrayFill(0.8f);
            tablec.addCell(cell);

            cell = new PdfPCell(new Phrase("PREGUNTAR\n -El niño/a puede lactar o alimentarse?\n -Vomita todo lo que ingiere?\n -Ha tenido convulsiones o ataques", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.LEFT);
            tablec.addCell(cell);

            PdfPTable table21 = new PdfPTable(1);

            if (aiepi.getBajopeso() == 0) {
                cell = new PdfPCell(new Phrase("VERIFICAR", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table21.addCell(cell);

                cell = new PdfPCell(new Phrase("- Esta letargico o inconsiente?", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.LEFT);
                table21.addCell(cell);
            } else {
                cell = new PdfPCell(new Phrase("VERIFICAR", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.LEFT);
                table21.addCell(cell);

                cell = new PdfPCell(new Phrase("- Esta letargico o inconsiente?", DATO_VERIFICADO));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table21.addCell(cell);
            }

            cell = new PdfPCell(table21);
            cell.setBorder(Rectangle.NO_BORDER);
            //cell.setColspan(2);
            tablec.addCell(cell);

            if (aiepi.getBcg() == 0) {
                cell = new PdfPCell(new Phrase("Tiene algun signo de peligro en general \n\n SI (   )  NO ( X )", DATO_FONT));
            } else {
                cell = new PdfPCell(new Phrase("Tiene algun signo de peligro en general \n\n SI ( X )  NO (   )", DATO_FONT));
            }
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            tablec.addCell(cell);

            cell = new PdfPCell(new Phrase(aiepi.getBilirrubina(), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            tablec.addCell(cell);
            ///////////se empieza a llenar la segunda fila del menor de 2 meses   
            cell = new PdfPCell(new Phrase("DETERMINAR SI PRESENTA DESNUTRICION AGUDA Y/O ANEMIA O SOBREPESO", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(2);
            cell.setGrayFill(0.8f);
            tablec.addCell(cell);

            cell = new PdfPCell(new Phrase("", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.LEFT);
            cell.setGrayFill(0.8f);
            tablec.addCell(cell);

            cell = new PdfPCell(new Phrase("", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.RIGHT);
            cell.setGrayFill(0.8f);
            tablec.addCell(cell);

            ///EMPIEZA LA TERCERA FILA PARA DESNUTRICION CRONICA
            cell = new PdfPCell(new Phrase("-Tiene emaciacion visible\n -Tiene edema en ambos pies\n -Tiene palidez palmar intensa", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.LEFT);
            tablec.addCell(cell);

            PdfPTable table22 = new PdfPTable(1);

            if ("G".equals(aiepi.getDglobal())) {
                cell = new PdfPCell(new Phrase("1. Peso/Talla, el punto cae POR DEBAJO de la curva NEGRA (-3) segun el sexo del niño o ninia", DATO_VERIFICADO));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table22.addCell(cell);

                cell = new PdfPCell(new Phrase("2. Peso/Talla, el punto cae POR DEBAJO de la CURVA -2 HASTA LA -3 (INCLUYE SI EL UNTO CAE EN LA CURVA -3), Segun sexo del niño/a", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
                table22.addCell(cell);

                cell = new PdfPCell(new Phrase("3. Peso/Talla, el punto cae EN LA CURVA 2 O POR DEBAJO HASTA LA CURVA -2 (INCLUYE SI EL PUNTO CAE EN LA CURVA -2), segun del sexo del niño/a", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
                table22.addCell(cell);

                cell = new PdfPCell(new Phrase("4. Peso/Talla, el punto cae POR ENCIMA 2 HASTA LA CURVA 3 (INCLUYE SI EL PUNTO CAE EN LA CURVA 3), segun el sexo del niño/a", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
                table22.addCell(cell);

                cell = new PdfPCell(new Phrase("5. Peso/Talla, el punto cae POR ENCIMA de la curva 3, segun el sexo del niño/a", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
                table22.addCell(cell);
            }
            if ("M".equals(aiepi.getDglobal())) {
                cell = new PdfPCell(new Phrase("1. Peso/Talla, el punto cae POR DEBAJO de la curva NEGRA (-3) segun el sexo del niño o ninia", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
                table22.addCell(cell);

                cell = new PdfPCell(new Phrase("2. Peso/Talla, el punto cae POR DEBAJO de la CURVA -2 HASTA LA -3 (INCLUYE SI EL UNTO CAE EN LA CURVA -3), Segun sexo del niño/a", DATO_VERIFICADO));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table22.addCell(cell);

                cell = new PdfPCell(new Phrase("3. Peso/Talla, el punto cae EN LA CURVA 2 O POR DEBAJO HASTA LA CURVA -2 (INCLUYE SI EL PUNTO CAE EN LA CURVA -2), segun del sexo del niño/a", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
                table22.addCell(cell);

                cell = new PdfPCell(new Phrase("4. Peso/Talla, el punto cae POR ENCIMA 2 HASTA LA CURVA 3 (INCLUYE SI EL PUNTO CAE EN LA CURVA 3), segun el sexo del niño/a", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
                table22.addCell(cell);

                cell = new PdfPCell(new Phrase("5. Peso/Talla, el punto cae POR ENCIMA de la curva 3, segun el sexo del niño/a", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
                table22.addCell(cell);
            }
            if ("N".equals(aiepi.getDglobal()) || "L".equals(aiepi.getDglobal())) {
                cell = new PdfPCell(new Phrase("1. Peso/Talla, el punto cae POR DEBAJO de la curva NEGRA (-3) segun el sexo del niño o ninia", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
                table22.addCell(cell);

                cell = new PdfPCell(new Phrase("2. Peso/Talla, el punto cae POR DEBAJO de la CURVA -2 HASTA LA -3 (INCLUYE SI EL UNTO CAE EN LA CURVA -3), Segun sexo del niño/a", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
                table22.addCell(cell);

                cell = new PdfPCell(new Phrase("3. Peso/Talla, el punto cae EN LA CURVA 2 O POR DEBAJO HASTA LA CURVA -2 (INCLUYE SI EL PUNTO CAE EN LA CURVA -2), segun del sexo del niño/a", DATO_VERIFICADO));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table22.addCell(cell);

                cell = new PdfPCell(new Phrase("4. Peso/Talla, el punto cae POR ENCIMA 2 HASTA LA CURVA 3 (INCLUYE SI EL PUNTO CAE EN LA CURVA 3), segun el sexo del niño/a", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
                table22.addCell(cell);

                cell = new PdfPCell(new Phrase("5. Peso/Talla, el punto cae POR ENCIMA de la curva 3, segun el sexo del niño/a", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
                table22.addCell(cell);
            }
            if ("S".equals(aiepi.getDglobal())) {
                cell = new PdfPCell(new Phrase("1. Peso/Talla, el punto cae POR DEBAJO de la curva NEGRA (-3) segun el sexo del niño o ninia", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
                table22.addCell(cell);

                cell = new PdfPCell(new Phrase("2. Peso/Talla, el punto cae POR DEBAJO de la CURVA -2 HASTA LA -3 (INCLUYE SI EL UNTO CAE EN LA CURVA -3), Segun sexo del niño/a", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
                table22.addCell(cell);

                cell = new PdfPCell(new Phrase("3. Peso/Talla, el punto cae EN LA CURVA 2 O POR DEBAJO HASTA LA CURVA -2 (INCLUYE SI EL PUNTO CAE EN LA CURVA -2), segun del sexo del niño/a", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
                table22.addCell(cell);

                cell = new PdfPCell(new Phrase("4. Peso/Talla, el punto cae POR ENCIMA 2 HASTA LA CURVA 3 (INCLUYE SI EL PUNTO CAE EN LA CURVA 3), segun el sexo del niño/a", DATO_VERIFICADO));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table22.addCell(cell);

                cell = new PdfPCell(new Phrase("5. Peso/Talla, el punto cae POR ENCIMA de la curva 3, segun el sexo del niño/a", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
                table22.addCell(cell);
            }
            if ("O".equals(aiepi.getDglobal())) {
                cell = new PdfPCell(new Phrase("1. Peso/Talla, el punto cae POR DEBAJO de la curva NEGRA (-3) segun el sexo del niño o ninia", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
                table22.addCell(cell);

                cell = new PdfPCell(new Phrase("2. Peso/Talla, el punto cae POR DEBAJO de la CURVA -2 HASTA LA -3 (INCLUYE SI EL UNTO CAE EN LA CURVA -3), Segun sexo del niño/a", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
                table22.addCell(cell);

                cell = new PdfPCell(new Phrase("3. Peso/Talla, el punto cae EN LA CURVA 2 O POR DEBAJO HASTA LA CURVA -2 (INCLUYE SI EL PUNTO CAE EN LA CURVA -2), segun del sexo del niño/a", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
                table22.addCell(cell);

                cell = new PdfPCell(new Phrase("4. Peso/Talla, el punto cae POR ENCIMA 2 HASTA LA CURVA 3 (INCLUYE SI EL PUNTO CAE EN LA CURVA 3), segun el sexo del niño/a", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
                table22.addCell(cell);

                cell = new PdfPCell(new Phrase("5. Peso/Talla, el punto cae POR ENCIMA de la curva 3, segun el sexo del niño/a", DATO_VERIFICADO));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table22.addCell(cell);
            }
            if ("0".equals(aiepi.getDglobal())) {
                cell = new PdfPCell(new Phrase("1. Peso/Talla, el punto cae POR DEBAJO de la curva NEGRA (-3) segun el sexo del niño o ninia", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
                table22.addCell(cell);

                cell = new PdfPCell(new Phrase("2. Peso/Talla, el punto cae POR DEBAJO de la CURVA -2 HASTA LA -3 (INCLUYE SI EL UNTO CAE EN LA CURVA -3), Segun sexo del niño/a", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
                table22.addCell(cell);

                cell = new PdfPCell(new Phrase("3. Peso/Talla, el punto cae EN LA CURVA 2 O POR DEBAJO HASTA LA CURVA -2 (INCLUYE SI EL PUNTO CAE EN LA CURVA -2), segun del sexo del niño/a", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
                table22.addCell(cell);

                cell = new PdfPCell(new Phrase("4. Peso/Talla, el punto cae POR ENCIMA 2 HASTA LA CURVA 3 (INCLUYE SI EL PUNTO CAE EN LA CURVA 3), segun el sexo del niño/a", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
                table22.addCell(cell);

                cell = new PdfPCell(new Phrase("5. Peso/Talla, el punto cae POR ENCIMA de la curva 3, segun el sexo del niño/a", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
                table22.addCell(cell);
            }

            cell = new PdfPCell(table22);
            cell.setBorder(Rectangle.NO_BORDER);
            tablec.addCell(cell);

            if ("G".equals(aiepi.getDglobal())) {
                cell = new PdfPCell(new Phrase("DESNUTRICION AGUDA GRAVE Y/O ANEMIA GRAVE", DATO_FONT));
            }
            if ("M".equals(aiepi.getDglobal())) {
                cell = new PdfPCell(new Phrase("DESNUTRICION AGUDA MODERADA", DATO_FONT));
            }
            if ("N".equals(aiepi.getDglobal()) || "L".equals(aiepi.getDglobal())) {
                cell = new PdfPCell(new Phrase("NO TIENE DESNUTRICION AGUDA ", DATO_FONT));
            }
            if ("S".equals(aiepi.getDglobal())) {
                cell = new PdfPCell(new Phrase("SOBREPESO", DATO_FONT));
            }
            if ("O".equals(aiepi.getDglobal())) {
                cell = new PdfPCell(new Phrase("OBESIDAD", DATO_FONT));
            }
            if ("0".equals(aiepi.getDglobal())) {
                cell = new PdfPCell(new Phrase("No se puede evaluar", DATO_FONT));
            }

            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            tablec.addCell(cell);

            cell = new PdfPCell(new Phrase(aiepi.getCantidad(), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            tablec.addCell(cell);
            ///EMPIEZA LA CUARTA FILA

            cell = new PdfPCell(new Phrase("EVALUAR LA TALLA / LONGITUD", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setGrayFill(0.8f);
            cell.setColspan(2);
            tablec.addCell(cell);

            cell = new PdfPCell(new Phrase("", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.LEFT);
            cell.setGrayFill(0.8f);
            tablec.addCell(cell);

            cell = new PdfPCell(new Phrase("", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.RIGHT);
            cell.setGrayFill(0.8f);
            tablec.addCell(cell);

            PdfPTable table23 = new PdfPTable(1);

            if ("L".equals(aiepi.getDcronica())) {
                cell = new PdfPCell(new Phrase("-Talla/Edad, el punto cae por DEBAJO DE LA CURVA -2, de acuerdo al sexo", DATO_VERIFICADO));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table23.addCell(cell);

                cell = new PdfPCell(new Phrase("-Talla/Edad, el punto cae en POR ENCIMA DE LA CURVA ROJA -2 ", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.LEFT);
                table23.addCell(cell);
            }
            if ("N".equals(aiepi.getDcronica())) {
                cell = new PdfPCell(new Phrase("-Talla/Edad, el punto cae por DEBAJO DE LA CURVA -2, de acuerdo al sexo", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.LEFT);
                table23.addCell(cell);

                cell = new PdfPCell(new Phrase("-Talla/Edad, el punto cae en POR ENCIMA DE LA CURVA ROJA -2 ", DATO_VERIFICADO));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table23.addCell(cell);
            }
            if ("0".equals(aiepi.getDcronica())) {
                cell = new PdfPCell(new Phrase("-Talla/Edad, el punto cae por DEBAJO DE LA CURVA -2, de acuerdo al sexo", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.LEFT);
                table23.addCell(cell);

                cell = new PdfPCell(new Phrase("-Talla/Edad, el punto cae en POR ENCIMA DE LA CURVA ROJA -2 ", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.LEFT);
                table23.addCell(cell);
            }

            cell = new PdfPCell(table23);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(2);
            tablec.addCell(cell);

            if ("L".equals(aiepi.getDcronica())) {
                cell = new PdfPCell(new Phrase("TALLA BAJA", DATO_FONT));
            }
            if ("N".equals(aiepi.getDcronica())) {
                cell = new PdfPCell(new Phrase("NO TIENE TALLA BAJA", DATO_FONT));
            }
            if ("0".equals(aiepi.getDcronica())) {
                cell = new PdfPCell(new Phrase("No se puede evaluar", DATO_FONT));
            }

            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            tablec.addCell(cell);

            cell = new PdfPCell(new Phrase(aiepi.getColor(), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            tablec.addCell(cell);

            ///EMPIEZA LA QUINTA FILA
            if (datosHis.getMes() + datosHis.getEdad() * 12 >= 6 && datosHis.getMes() + datosHis.getEdad() * 12 <= 24) {

                cell = new PdfPCell(new Phrase("SI EL NINIO/A TIEN DE 6 A 24 MESES: EVALLUAR LA VELOCIDAD DE CRECIMIENTO LINEAL", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setColspan(3);
                cell.setGrayFill(0.8f);
                tablec.addCell(cell);

                cell = new PdfPCell(new Phrase("", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setGrayFill(0.8f);
                tablec.addCell(cell);

                PdfPTable table24 = new PdfPTable(1);

                if ("L".equals(aiepi.getDcronica())) {
                    cell = new PdfPCell(new Phrase("-La tendencia de crecimiento es horizontal y tiende a aproximarse a la curva inferior", DATO_VERIFICADO));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table24.addCell(cell);

                    cell = new PdfPCell(new Phrase("-La tendencia de crecimiento es paralela a las curvas", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setBorder(Rectangle.LEFT);
                    table24.addCell(cell);
                }
                if ("N".equals(aiepi.getDcronica())) {
                    cell = new PdfPCell(new Phrase("-La tendencia de crecimiento es horizontal y tiende a aproximarse a la curva inferior", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setBorder(Rectangle.LEFT);
                    table24.addCell(cell);

                    cell = new PdfPCell(new Phrase("-La tendencia de crecimiento es paralela a las curvas", DATO_VERIFICADO));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table24.addCell(cell);
                }
                if ("0".equals(aiepi.getDcronica())) {
                    cell = new PdfPCell(new Phrase("-La tendencia de crecimiento es horizontal y tiende a aproximarse a la curva inferior", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setBorder(Rectangle.LEFT);
                    table24.addCell(cell);

                    cell = new PdfPCell(new Phrase("-La tendencia de crecimiento es paralela a las curvas", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setBorder(Rectangle.LEFT);
                    table24.addCell(cell);
                }

                cell = new PdfPCell(table24);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setColspan(2);
                tablec.addCell(cell);

                if ("L".equals(aiepi.getDcronica())) {
                    cell = new PdfPCell(new Phrase("CRECIMIENTO LINEAL INAPROPIADO", DATO_FONT));
                }
                if ("N".equals(aiepi.getDcronica())) {
                    cell = new PdfPCell(new Phrase("CRECIMIENTO LINEAL APROPIADO", DATO_FONT));
                }
                if ("0".equals(aiepi.getDcronica())) {
                    cell = new PdfPCell(new Phrase("No se peude evaluar", DATO_FONT));
                }

                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                tablec.addCell(cell);

                cell = new PdfPCell(new Phrase(aiepi.getCilindros(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                tablec.addCell(cell);

            }

            if (aiepi.getDiasc() == 0) {
                cell = new PdfPCell(new Phrase("EL NINIO/A TIENE TOS O DIFICULTAD PARA RESPIRAR ----------------------> SI ( )  NO  ( X )", DATO_FONT));
            } else {
                cell = new PdfPCell(new Phrase("EL NINIO/A TIENE TOS O DIFICULTAD PARA RESPIRAR ----------------------> SI ( X )  NO  ( )", DATO_FONT));
            }
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(3);
            cell.setGrayFill(0.8f);
            tablec.addCell(cell);

            cell = new PdfPCell(new Phrase("", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setGrayFill(0.8f);
            tablec.addCell(cell);

            cell = new PdfPCell(new Phrase("-Hace cuanto tiempo .." + aiepi.getControlpos() + "..dias?\n-Tiene sibilancias?\n Es la primera vez que tiene sibilancias?\n\nRespiracion rapida es:\nDe dos a 11 meses : 50 o mas por minuto\nDe un año a menor de 5 años : 40 o mas por minito ", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            tablec.addCell(cell);

            PdfPTable table25 = new PdfPTable(1);

            cell = new PdfPCell(new Phrase("-oContar las respitaciones en un\nminuto..." + aiepi.getCuraciones() + "..resp./min.\n\n", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            table25.addCell(cell);

            if ((aiepi.getSuma4() & 1) == 1) {
                cell = new PdfPCell(new Phrase("1. -Tiene respiracion rapida?", DATO_VERIFICADO));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table25.addCell(cell);
            } else {
                cell = new PdfPCell(new Phrase("1. -Tiene respiracion rapida?", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
                table25.addCell(cell);
            }
            if ((aiepi.getSuma4() & 2) == 2) {
                cell = new PdfPCell(new Phrase("2. -Tiene tiraje subcostales", DATO_VERIFICADO));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table25.addCell(cell);
            } else {
                cell = new PdfPCell(new Phrase("2. -Tiene tiraje subcostales?", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
                table25.addCell(cell);
            }
            if ((aiepi.getSuma4() & 4) == 4) {
                cell = new PdfPCell(new Phrase("3. -Tiene estridor?", DATO_VERIFICADO));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table25.addCell(cell);
            } else {
                cell = new PdfPCell(new Phrase("3. -Tiene estridor?", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
                table25.addCell(cell);
            }

            cell = new PdfPCell(table25);
            cell.setBorder(Rectangle.NO_BORDER);
            tablec.addCell(cell);

            cell = new PdfPCell(new Phrase(aiepi.getEspuma(), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            tablec.addCell(cell);

            cell = new PdfPCell(new Phrase(aiepi.getContraref(), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            tablec.addCell(cell);
            ///la fila para la diarrea     
            if (aiepi.getDiasi() == 0) {
                cell = new PdfPCell(new Phrase("EL NINIO/A TIENE DIARREA ---------------------------------------------------------> SI ( )  NO  ( X )", DATO_FONT));
            } else {
                cell = new PdfPCell(new Phrase("EL NINIO/A TIENE DIARREA ---------------------------------------------------------> SI ( X )  NO  ( )", DATO_FONT));
            }
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setGrayFill(0.8f);
            cell.setColspan(4);
            tablec.addCell(cell);

            cell = new PdfPCell(new Phrase("Hace cuanto tiempo?..." + aiepi.getAlcohol() + "...dias\nHay sangre visible en las heces", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.LEFT);
            tablec.addCell(cell);

            PdfPTable table26 = new PdfPTable(1);

            if ((aiepi.getSuma4() & 8) == 8) {
                cell = new PdfPCell(new Phrase("1. Determinar el estado general del niño/a\n   -Esta letargico o inconsiente\n   -Esta inquieto / irritable\n   -Tiene los ojos hundidos", DATO_VERIFICADO));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table26.addCell(cell);
            } else {
                cell = new PdfPCell(new Phrase("1. Determinar el estado general del niño/a\n   -Esta letargico o inconsiente\n   -Esta inquieto / irritable\n   -Tiene los ojos hundidos", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
                table26.addCell(cell);
            }
            if ((aiepi.getSuma4() & 16) == 16) {
                cell = new PdfPCell(new Phrase("2. Ofrecer liquidos al niño/a:     -No puede beber o bebe mal\n   -Bebe avidamente, con sed", DATO_VERIFICADO));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table26.addCell(cell);
            } else {
                cell = new PdfPCell(new Phrase("2. Ofrecer liquidos al niño/a:     -No puede beber o bebe mal\n   -Bebe avidamente, con sed", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
                table26.addCell(cell);
            }
            if ((aiepi.getSuma4() & 32) == 32) {
                cell = new PdfPCell(new Phrase("3. Signo de pliegue cutaneo\n   -La piel vueve muy lentamente (mas de 2 segundos)\n   -La piel vueve lentamente (menos de 2 segundos)", DATO_VERIFICADO));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table26.addCell(cell);
            } else {
                cell = new PdfPCell(new Phrase("3. Signo de pliegue cutaneo\n   -La piel vueve muy lentamente (mas de 2 segundos)\n   -La piel vueve lentamente (menos de 2 segundos)", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
                table26.addCell(cell);
            }

            cell = new PdfPCell(table26);
            cell.setBorder(Rectangle.NO_BORDER);
            tablec.addCell(cell);

            cell = new PdfPCell(new Phrase(aiepi.getCristales(), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            tablec.addCell(cell);

            cell = new PdfPCell(new Phrase(aiepi.getFama(), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            tablec.addCell(cell);

            ///la fila para la FIEBRE     
            if (aiepi.getDiscapa() == 0) {
                cell = new PdfPCell(new Phrase("EL NINIO/A TIENE FIEBRE --------------------------------------------------------> SI ( )  NO  ( X )", DATO_FONT));
            } else {
                cell = new PdfPCell(new Phrase("EL NINIO/A TIENE FIEBRE --------------------------------------------------------> SI ( X )  NO  ( )", DATO_FONT));
            }
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setGrayFill(0.8f);
            cell.setColspan(4);
            tablec.addCell(cell);

            cell = new PdfPCell(new Phrase("(Referida por interrogatorio sesiente caliente al tacto y tiene temperatura axilar de 38C o mas)\n                                           Determinar el riesgo de malaria : \n                                Con riesgo de malaria - Sin riesgo de malaria", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.LEFT);
            cell.setColspan(2);
            tablec.addCell(cell);

            cell = new PdfPCell(new Phrase("", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.LEFT);
            tablec.addCell(cell);

            cell = new PdfPCell(new Phrase("", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.RIGHT);
            tablec.addCell(cell);

            PdfPTable table28 = new PdfPTable(1);

            cell = new PdfPCell(new Phrase("Hace cuanto tiempo tiene fiebre?..." + aiepi.getDesesti() + "...dias\nHa tenido fiebre todos los dias", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.LEFT);
            table28.addCell(cell);

            cell = new PdfPCell(table28);
            cell.setBorder(Rectangle.NO_BORDER);
            tablec.addCell(cell);

            PdfPTable table27 = new PdfPTable(1);

            if ((aiepi.getSuma4() & 64) == 64) {
                cell = new PdfPCell(new Phrase("1. Tiene rigidez de nuca", DATO_VERIFICADO));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table27.addCell(cell);
            } else {
                cell = new PdfPCell(new Phrase("1. Tiene rigidez de nuca", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
                table27.addCell(cell);
            }
            if ((aiepi.getSuma4() & 128) == 128) {
                cell = new PdfPCell(new Phrase("2. Determinar signos de sarampion \n   -Erupcion cutanea generalizada en uno de los siguintes\n   Signos: Tos, catarro u ojos enrojecidos", DATO_VERIFICADO));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table27.addCell(cell);
            } else {
                cell = new PdfPCell(new Phrase("2. Determinar signos de sarampion \n   -Erupcion cutanea generalizada en uno de los siguintes\n   Signos: Tos, catarro u ojos enrojecidos", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
                table27.addCell(cell);
            }

            cell = new PdfPCell(table27);
            cell.setBorder(Rectangle.NO_BORDER);
            tablec.addCell(cell);

            cell = new PdfPCell(new Phrase(aiepi.getDensidad(), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            tablec.addCell(cell);

            cell = new PdfPCell(new Phrase(aiepi.getEstado(), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            tablec.addCell(cell);

            ///la fila para la PROBLEMAS DE OIDO     
            if (aiepi.getDisli() == 0) {
                cell = new PdfPCell(new Phrase("EL NINIO/A TIENE PROBLEMA DE OIDO ----------------------------------------> SI ( )  NO  ( X )", DATO_FONT));
            } else {
                cell = new PdfPCell(new Phrase("EL NINIO/A TIENE PROBLEMA DE OIDO ----------------------------------------> SI ( X )  NO  ( )", DATO_FONT));
            }
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setGrayFill(0.8f);
            cell.setColspan(4);
            tablec.addCell(cell);

            cell = new PdfPCell(new Phrase("Tiene dolor de oido?\n-Tiene supuracion de oido?\n En caso afirmativo?\n\nHace cuanto tiempo..." + aiepi.getDiabetes() + "...dias", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            tablec.addCell(cell);

            PdfPTable table29 = new PdfPTable(1);

            if ((aiepi.getSuma4() & 256) == 256) {
                cell = new PdfPCell(new Phrase("1. Detertminar si hay supuracion en el oido", DATO_VERIFICADO));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table29.addCell(cell);
            } else {
                cell = new PdfPCell(new Phrase("1. Detertminar si hay supuracion en el oido", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
                table29.addCell(cell);
            }
            if ((aiepi.getSuma4() & 512) == 512) {
                cell = new PdfPCell(new Phrase("2. Palpar detras de la oreja para determinar si hayo\n tumefaccion dolorosa", DATO_VERIFICADO));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table29.addCell(cell);
            } else {
                cell = new PdfPCell(new Phrase("2. Palpar detras de la oreja para determinar si hayo\n tumefaccion dolorosa", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
                table29.addCell(cell);
            }

            cell = new PdfPCell(table29);
            tablec.addCell(cell);

            cell = new PdfPCell(new Phrase(aiepi.getDhierro(), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            tablec.addCell(cell);

            cell = new PdfPCell(new Phrase(aiepi.getDvitaa(), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            tablec.addCell(cell);

            ///la fila para la DESNUTRICION MODERADA 
            if ("M".equals(aiepi.getDglobal())) {

                if (aiepi.getDiu() == 0) {
                    cell = new PdfPCell(new Phrase("EL NINIO/A TIENE CLASIFICACION DE DESNUTRICION MODERADA ------------------------> SI ( )  NO  ( X )", DATO_FONT));
                } else {
                    cell = new PdfPCell(new Phrase("EL NINIO/A TIENE CLASIFICACION DE DESNUTRICION MODERADA ------------------------> SI ( X )  NO  ( )", DATO_FONT));
                }
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setGrayFill(0.8f);
                cell.setColspan(4);
                tablec.addCell(cell);

                cell = new PdfPCell(new Phrase("Definir conducta frente al niño/a con Desnutricion Moderada \nDeterminar si el niño con Desnutricion moderada tiene ademas Clasificacion de: \n       -Neumonia                                                                  -Diarrea Persistente\n       -Diarrea con deshidratacion                                        -Malaria \n       -Disenteria                                                                   -Sospecha de sarampion  ", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setColspan(2);
                tablec.addCell(cell);

                cell = new PdfPCell(new Phrase(aiepi.getEmaties(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                tablec.addCell(cell);

                cell = new PdfPCell(new Phrase(aiepi.getEpiteliales(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                tablec.addCell(cell);
            }

            document.add(tablec);
            document.newPage();

            //empieza la nueva hoja
            PdfPTable table3 = new PdfPTable(4);
            int[] fmwid3 = {24, 40, 18, 18}; // percentage
            table3.setWidths(fmwid3);
            table3.setWidthPercentage(100);

            PdfPTable table31 = new PdfPTable(6);
            int[] fxwid31 = {15, 17, 17, 17, 15, 18}; // percentage
            table31.setWidths(fxwid31);
            table31.setWidthPercentage(100);

            cell = new PdfPCell(new Phrase("VERIFCAR LOS ANTECEDENTES DE VACUNACIONES DEL NINIO /A ", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.ALIGN_TOP);
            cell.setColspan(6);
            table31.addCell(cell);

            for (int i = 0; i < lvacunas.size(); i++) {
                Cuadernos datov = (Cuadernos) lvacunas.get(i);
                if (datov.getBcg() == 1) {
                    bcg = i;
                }
                if ("P".equals(datov.getPenta())) {
                    penta1 = i;
                }
                if ("S".equals(datov.getPenta())) {
                    penta2 = i;
                }
                if ("T".equals(datov.getPenta())) {
                    penta3 = i;
                }
                if ("1".equals(datov.getSrp())) {
                    srp = i;
                }
                if ("1".equals(datov.getFama()) || "2".equals(datov.getFama())) {
                    anti = i;
                }
            }
            if (bcg != 90) {
                Cuadernos datova = (Cuadernos) lvacunas.get(bcg);
                cell = new PdfPCell(new Phrase(format(datova.getFechap(), "dd/MM/yyyy") + "\n  BCG", DATO_FONT));
            } else {
                cell = new PdfPCell(new Phrase("_______\n  BCG", DATO_FONT));
            }
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            table31.addCell(cell);

            if (penta1 != 90) {
                Cuadernos datova2 = (Cuadernos) lvacunas.get(penta1);
                cell = new PdfPCell(new Phrase(format(datova2.getFechap(), "dd/MM/yyyy") + "\n Pentavalente I\nAntipolioI", DATO_FONT));
            } else {
                cell = new PdfPCell(new Phrase("_____________\n Pentavalente I\nAntipolioI", DATO_FONT));
            }
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            table31.addCell(cell);

            if (penta2 != 90) {
                Cuadernos datova3 = (Cuadernos) lvacunas.get(penta2);
                cell = new PdfPCell(new Phrase(format(datova3.getFechap(), "dd/MM/yyyy") + "\n Pentavalente II\nAntipolioII", DATO_FONT));
            } else {
                cell = new PdfPCell(new Phrase("_____________\n  Pentavalente II\nAntipolioII", DATO_FONT));
            }
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            table31.addCell(cell);

            if (penta3 != 90) {
                Cuadernos datova4 = (Cuadernos) lvacunas.get(penta3);
                cell = new PdfPCell(new Phrase(format(datova4.getFechap(), "dd/MM/yyyy") + "\n Pentavalente III\nAntipolioIII", DATO_FONT));
            } else {
                cell = new PdfPCell(new Phrase("_____________\n  Pentavalente III\nAntipolioIII", DATO_FONT));
            }
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            table31.addCell(cell);

            if (srp != 90) {
                Cuadernos datova5 = (Cuadernos) lvacunas.get(srp);
                cell = new PdfPCell(new Phrase(format(datova5.getFechap(), "dd/MM/yyyy") + "\n SRP", DATO_FONT));
            } else {
                cell = new PdfPCell(new Phrase("_______\n  SRP", DATO_FONT));
            }
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            table31.addCell(cell);

            if (anti != 90) {
                Cuadernos datova6 = (Cuadernos) lvacunas.get(anti);
                cell = new PdfPCell(new Phrase(format(datova6.getFechap(), "dd/MM/yyyy") + "\n Antiamarilica", DATO_FONT));
            } else {
                cell = new PdfPCell(new Phrase("____________\n Antiamarilica", DATO_FONT));
            }
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.ALIGN_RIGHT);
            table31.addCell(cell);

            cell = new PdfPCell(table31);
            cell.setColspan(2);
            table3.addCell(cell);

            if (aiepi.getEclam() == 0) {
                cell = new PdfPCell(new Phrase("VACUNAS COMPLETAS PARA LA EDAD\n\n SI (  )  NO ( X )", DATO_FONT));
            } else {
                cell = new PdfPCell(new Phrase("VACUNAS COMPLETAS PARA LA EDAD\n\n SI ( X )  NO (  )", DATO_FONT));
            }
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table3.addCell(cell);

            cell = new PdfPCell(new Phrase("VOLVER PARA LA PROXIMA VACUNA\n\n " + format(aiepi.getFec_egreso(), "dd/MM/yyyy"), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table3.addCell(cell);

            cell = new PdfPCell(new Phrase("EVALUAR EL DESARROLLO DEL NINIO\n\n Emplear el carnet de salud infantil", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(2);
            table3.addCell(cell);

            if (aiepi.getEndodoncia() == 0) {
                cell = new PdfPCell(new Phrase("Cumple con todos los hitos\n de desarrollo para la edad\n SI (  )  NO ( X )", DATO_FONT));
            } else {
                cell = new PdfPCell(new Phrase("Cumple con todos los hitos\n de desarrollo para la edad\n SI ( X )  NO (  )", DATO_FONT));
            }
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table3.addCell(cell);

            cell = new PdfPCell(new Phrase(aiepi.getGlucosa(), DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table3.addCell(cell);

            cell = new PdfPCell(new Phrase("\nEVALUAR LAS PRACTICAS DE ALIMENTACION DE LOS NINIOS/AS DE 2 MESES A MENORES DE 5 añoS", DATO_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(4);
            table3.addCell(cell);

            if (datosHis.getMes() + datosHis.getEdad() * 12 >= 2 && datosHis.getMes() + datosHis.getEdad() * 12 < 6) {
                cell = new PdfPCell(new Phrase("A.  EVALUAR LA LACTANCIA MATERNA DE NINIOS DE 2 MESES A MENORES DE 6 MESES ", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setColspan(4);
                table3.addCell(cell);

                cell = new PdfPCell(new Phrase("ASPECTOS A EVALUAR", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setGrayFill(0.8f);
                table3.addCell(cell);

                cell = new PdfPCell(new Phrase("PRACTICA IDEAL", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setGrayFill(0.8f);
                table3.addCell(cell);

                cell = new PdfPCell(new Phrase("PRACTICA REAL", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setGrayFill(0.8f);
                table3.addCell(cell);

                cell = new PdfPCell(new Phrase("CONDUCTA", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setGrayFill(0.8f);
                table3.addCell(cell);

                ///pRIMERA fILA DE alimentacion        
                cell = new PdfPCell(new Phrase("-Recibe lactancia materna exclusiva?", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table3.addCell(cell);

                cell = new PdfPCell(new Phrase("El niño hasta los 6 meses debe recibir SOLO seno materno. No debe recibir NINGUN otro alimento o liquido (excepto vacuna antipolio o vitaminas) ", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table3.addCell(cell);

                cell = new PdfPCell(new Phrase(aiepi.getGranulosos(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table3.addCell(cell);

                cell = new PdfPCell(new Phrase(aiepi.getHialianos(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table3.addCell(cell);

                //segunda fILA DE alimentacion        
                cell = new PdfPCell(new Phrase("-oCuantas veces en 24 horas?", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table3.addCell(cell);

                cell = new PdfPCell(new Phrase("Al menos 10 veces en 24 horas", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table3.addCell(cell);

                cell = new PdfPCell(new Phrase(aiepi.getLaboratorio(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table3.addCell(cell);

                cell = new PdfPCell(new Phrase(aiepi.getLeucocitos(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table3.addCell(cell);

                //tercera fILA DE alimentacion        
                cell = new PdfPCell(new Phrase("-Recibe otros alimentos o liquidos?", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table3.addCell(cell);

                cell = new PdfPCell(new Phrase("Ningun alimento o liquido", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table3.addCell(cell);

                cell = new PdfPCell(new Phrase(aiepi.getLeucocitarios(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table3.addCell(cell);

                cell = new PdfPCell(new Phrase(aiepi.getLmexclu(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table3.addCell(cell);

                //cuarta fILA DE alimentacion        
                cell = new PdfPCell(new Phrase("-Recibe biberon (mamadera, chupete)?", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table3.addCell(cell);

                cell = new PdfPCell(new Phrase("No debe recibir biberon", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table3.addCell(cell);

                cell = new PdfPCell(new Phrase(aiepi.getMujerdt(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table3.addCell(cell);

                cell = new PdfPCell(new Phrase(aiepi.getNitritos(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table3.addCell(cell);

                //Quinta fILA DE alimentacion        
                cell = new PdfPCell(new Phrase("-La posicion para mamar es la correcta?", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table3.addCell(cell);

                PdfPTable table40 = new PdfPTable(1);

                if ((aiepi.getSuma5() & 1) == 1) {
                    cell = new PdfPCell(new Phrase("1. La cabeza y el cuerpo del niño deben estar rectos ", DATO_VERIFICADO));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table40.addCell(cell);
                } else {
                    cell = new PdfPCell(new Phrase("1. La cabeza y el cuerpo del niño deben estar rectos ", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setBorder(Rectangle.NO_BORDER);
                    table40.addCell(cell);
                }
                if ((aiepi.getSuma5() & 2) == 2) {
                    cell = new PdfPCell(new Phrase("2. La nariz del niño debe estar frente al pezon ", DATO_VERIFICADO));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table40.addCell(cell);
                } else {
                    cell = new PdfPCell(new Phrase("2. La nariz del niño debe estar frente al pezon ", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setBorder(Rectangle.NO_BORDER);
                    table40.addCell(cell);
                }
                if ((aiepi.getSuma5() & 4) == 4) {
                    cell = new PdfPCell(new Phrase("3. El cuerpo del niño debe estar pegados al cuerpo de la madre", DATO_VERIFICADO));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table40.addCell(cell);
                } else {
                    cell = new PdfPCell(new Phrase("3. El cuerpo del niño debe estar pegados al cuerpo de la madre", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setBorder(Rectangle.NO_BORDER);
                    table40.addCell(cell);
                }
                if ((aiepi.getSuma5() & 8) == 8) {
                    cell = new PdfPCell(new Phrase("4. La madre debe sostener todo el cuerpo del niño y no solo cuerpo y hombros", DATO_VERIFICADO));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table40.addCell(cell);
                } else {
                    cell = new PdfPCell(new Phrase("4. La madre debe sostener todo el cuerpo del niño y no solo cuerpo y hombros ", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setBorder(Rectangle.NO_BORDER);
                    table40.addCell(cell);
                }

                cell = new PdfPCell(table40);
                table3.addCell(cell);

                cell = new PdfPCell(new Phrase(aiepi.getObserva(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table3.addCell(cell);

                cell = new PdfPCell(new Phrase(aiepi.getObservaciones(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table3.addCell(cell);

                //Sexta fILA DE alimentacion        
                cell = new PdfPCell(new Phrase("- El agarre es adecuado?", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table3.addCell(cell);

                PdfPTable table41 = new PdfPTable(1);

                if ((aiepi.getSuma5() & 16) == 16) {
                    cell = new PdfPCell(new Phrase("5. Toca la mama o pecho con el menton  ", DATO_VERIFICADO));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table41.addCell(cell);
                } else {
                    cell = new PdfPCell(new Phrase("5. La cabeza y el cuerpo del niño deben estar rectos ", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setBorder(Rectangle.NO_BORDER);
                    table41.addCell(cell);
                }
                if ((aiepi.getSuma5() & 32) == 32) {
                    cell = new PdfPCell(new Phrase("6. Tiene la boca bien abierta ", DATO_VERIFICADO));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table41.addCell(cell);
                } else {
                    cell = new PdfPCell(new Phrase("6. Tiene la boca bien abierta ", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setBorder(Rectangle.NO_BORDER);
                    table41.addCell(cell);
                }
                if ((aiepi.getSuma5() & 64) == 64) {
                    cell = new PdfPCell(new Phrase("7. Tiene el labio inferior volteado hacia afuera ", DATO_VERIFICADO));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table41.addCell(cell);
                } else {
                    cell = new PdfPCell(new Phrase("7. Tiene el labio inferior volteado hacia afuera ", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setBorder(Rectangle.NO_BORDER);
                    table41.addCell(cell);
                }
                if ((aiepi.getSuma5() & 128) == 128) {
                    cell = new PdfPCell(new Phrase("8. Se ve mas areola por encima de la boca que por debajo", DATO_VERIFICADO));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table41.addCell(cell);
                } else {
                    cell = new PdfPCell(new Phrase("8. Se ve mas areola por encima de la boca que por debajo ", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setBorder(Rectangle.NO_BORDER);
                    table41.addCell(cell);
                }

                cell = new PdfPCell(table41);
                table3.addCell(cell);

                cell = new PdfPCell(new Phrase(aiepi.getOlor(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table3.addCell(cell);

                cell = new PdfPCell(new Phrase(aiepi.getOtros(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table3.addCell(cell);

                //Septima fILA DE alimentacion        
                cell = new PdfPCell(new Phrase("-Vacia los dos pechos?", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table3.addCell(cell);

                PdfPTable table42 = new PdfPTable(1);
                if ((aiepi.getSuma5() & 256) == 256) {
                    cell = new PdfPCell(new Phrase("9. La mama le debe dar ambos pechos hasta vaciarlos ", DATO_VERIFICADO));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table42.addCell(cell);
                } else {
                    cell = new PdfPCell(new Phrase("9. La mama le debe dar ambos pechos hasta vaciarlos", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setBorder(Rectangle.NO_BORDER);
                    table42.addCell(cell);
                }
                cell = new PdfPCell(table42);
                table3.addCell(cell);

                cell = new PdfPCell(new Phrase(aiepi.getPenta(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table3.addCell(cell);

                cell = new PdfPCell(new Phrase(aiepi.getPesonac(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table3.addCell(cell);

                //Octava fILA DE alimentacion        
                cell = new PdfPCell(new Phrase("-Tiene algun problema para darle de lactar? \n Cual es el problema", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table3.addCell(cell);

                PdfPTable table43 = new PdfPTable(1);
                if ((aiepi.getSuma5() & 512) == 512) {
                    cell = new PdfPCell(new Phrase("10. La mama debe comunicar al personal de salud cualquier problema con la lactancia(pezones adoloridos, llanto del bebe, etc.)", DATO_VERIFICADO));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table43.addCell(cell);
                } else {
                    cell = new PdfPCell(new Phrase("10. La mama debe comunicar al personal de salud cualquier problema con la lactancia(pezones adoloridos, llanto del bebe, etc.)", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setBorder(Rectangle.NO_BORDER);
                    table43.addCell(cell);
                }
                cell = new PdfPCell(table43);
                table3.addCell(cell);

                cell = new PdfPCell(new Phrase(aiepi.getPiocitos(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table3.addCell(cell);

                cell = new PdfPCell(new Phrase(aiepi.getProteinas(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table3.addCell(cell);

                cell = new PdfPCell(new Phrase("\nEVALUAR OTROS PROBLEMAS                                                                                                                    Calificar                                      Tratar", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setColspan(4);
                table3.addCell(cell);

                cell = new PdfPCell(new Phrase("Pregunte a la madre o cuidador si el niño/a tiene otros problemas\n\n", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setColspan(2);
                table3.addCell(cell);

                cell = new PdfPCell(new Phrase(aiepi.getSangre(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table3.addCell(cell);

                cell = new PdfPCell(new Phrase(aiepi.getSbas(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table3.addCell(cell);

                cell = new PdfPCell(new Phrase("Pregunte por la salud de la madre\n\n", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setColspan(2);
                table3.addCell(cell);

                cell = new PdfPCell(new Phrase(aiepi.getSblancos(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table3.addCell(cell);

                cell = new PdfPCell(new Phrase(aiepi.getScay(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table3.addCell(cell);

                cell = new PdfPCell(new Phrase("\n\nVOLVER PARA LA CONSULTA DE SEGUIMIENTO O CONTROL   : ___" + format(aiepi.getFec_ingreso(), "dd/MM/yyyy"), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setColspan(4);
                table3.addCell(cell);

                cell = new PdfPCell(new Phrase("\n\nNONBRES Y APELLIDOS DEL PERSONAL DE SALUD :____" + medico.getNombres() + " " + medico.getPaterno() + " " + medico.getMaterno(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setColspan(4);
                table3.addCell(cell);

            }///fn de alimentacion menores de 6 meses

            if (datosHis.getMes() + datosHis.getEdad() * 12 >= 6) {
                cell = new PdfPCell(new Phrase("B.  EVALUAR LA ALIMENTACION DEL NINIO DE 6 MESES A MENOER DE 5 añoS  ", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setGrayFill(0.8f);
                cell.setColspan(4);
                table3.addCell(cell);

                cell = new PdfPCell(new Phrase("ASPECTOS A EVALUAR", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setGrayFill(0.8f);
                table3.addCell(cell);

                cell = new PdfPCell(new Phrase("PRACTICA IDEAL", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setGrayFill(0.8f);
                table3.addCell(cell);

                cell = new PdfPCell(new Phrase("PRACTICA REAL", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setGrayFill(0.8f);
                table3.addCell(cell);

                cell = new PdfPCell(new Phrase("CONDUCTA", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setGrayFill(0.8f);
                table3.addCell(cell);
                ///pRIMERA fILA DE alimentacion        
                cell = new PdfPCell(new Phrase("-Le sigue dando lactancia materna", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table3.addCell(cell);

                PdfPTable table51 = new PdfPTable(1);
                if ((aiepi.getSuma5() & 1) == 1) {
                    cell = new PdfPCell(new Phrase("1. El niño debe recibir lactancia materna hasta 2 años o mas de edad", DATO_VERIFICADO));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table51.addCell(cell);
                } else {
                    cell = new PdfPCell(new Phrase("1. El niño debe recibir lactancia materna hasta 2 años o mas de edad", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setBorder(Rectangle.NO_BORDER);
                    table51.addCell(cell);
                }
                cell = new PdfPCell(table51);
                table3.addCell(cell);

                cell = new PdfPCell(new Phrase(aiepi.getGranulosos(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table3.addCell(cell);

                cell = new PdfPCell(new Phrase(aiepi.getHialianos(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table3.addCell(cell);

                //segunda fILA DE alimentacion        
                cell = new PdfPCell(new Phrase("-oCuantas veces en 24 horas?", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table3.addCell(cell);

                PdfPTable table52 = new PdfPTable(1);
                if ((aiepi.getSuma5() & 2) == 2) {
                    cell = new PdfPCell(new Phrase("2. Debe recibir 6 a 8 veces en 24 horas", DATO_VERIFICADO));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table52.addCell(cell);
                } else {
                    cell = new PdfPCell(new Phrase("2. Debe recibir 6 a 8 veces en 24 horas", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setBorder(Rectangle.NO_BORDER);
                    table52.addCell(cell);
                }
                cell = new PdfPCell(table52);
                table3.addCell(cell);

                cell = new PdfPCell(new Phrase(aiepi.getLaboratorio(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table3.addCell(cell);

                cell = new PdfPCell(new Phrase(aiepi.getLeucocitos(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table3.addCell(cell);

                //tercera fILA DE alimentacion        
                cell = new PdfPCell(new Phrase("-Ha iniciado la alimentacion complementaria?", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table3.addCell(cell);

                PdfPTable table53 = new PdfPTable(1);
                if ((aiepi.getSuma5() & 4) == 4) {
                    cell = new PdfPCell(new Phrase("3. La alimentacion complementaria debe iniciarse a los 6 meses de edad", DATO_VERIFICADO));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table53.addCell(cell);
                } else {
                    cell = new PdfPCell(new Phrase("3. La alimentacion complementaria debe iniciarse a los 6 meses de edad", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setBorder(Rectangle.NO_BORDER);
                    table53.addCell(cell);
                }
                cell = new PdfPCell(table53);
                table3.addCell(cell);

                cell = new PdfPCell(new Phrase(aiepi.getLeucocitarios(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table3.addCell(cell);

                cell = new PdfPCell(new Phrase(aiepi.getLmexclu(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table3.addCell(cell);

                //cuarta fILA DE alimentacion        
                cell = new PdfPCell(new Phrase("-Que alimentos de la al niño?", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table3.addCell(cell);

                PdfPTable table54 = new PdfPTable(1);
                if ((aiepi.getSuma5() & 8) == 8) {
                    cell = new PdfPCell(new Phrase("4. Debe recibir alimentos espesos, carnes, cereales,tuberculos,frutas, leguminosas y el complemento nutricional 6 meses de edad", DATO_VERIFICADO));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table54.addCell(cell);
                } else {
                    cell = new PdfPCell(new Phrase("4. Debe recibir alimentos espesos, carnes, cereales,tuberculos,frutas, leguminosas y el complemento nutricional 6 meses de edad", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setBorder(Rectangle.NO_BORDER);
                    table54.addCell(cell);
                }
                cell = new PdfPCell(table54);
                table3.addCell(cell);

                cell = new PdfPCell(new Phrase(aiepi.getMujerdt(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table3.addCell(cell);

                cell = new PdfPCell(new Phrase(aiepi.getNitritos(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table3.addCell(cell);

                //Quinta fILA DE alimentacion        
                cell = new PdfPCell(new Phrase("-oCuanto le da por vez?", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table3.addCell(cell);

                PdfPTable table55 = new PdfPTable(1);
                if ((aiepi.getSuma5() & 16) == 16) {
                    cell = new PdfPCell(new Phrase("5. Entre 6 a 12 cucharadas colmadas por vez", DATO_VERIFICADO));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table55.addCell(cell);
                } else {
                    cell = new PdfPCell(new Phrase("5. Entre 6 a 12 cucharadas colmadas por vez", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setBorder(Rectangle.NO_BORDER);
                    table55.addCell(cell);
                }
                cell = new PdfPCell(table55);
                table3.addCell(cell);

                cell = new PdfPCell(new Phrase(aiepi.getObserva(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table3.addCell(cell);

                cell = new PdfPCell(new Phrase(aiepi.getObservaciones(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table3.addCell(cell);

                //Sexta fILA DE alimentacion        
                cell = new PdfPCell(new Phrase("-oCuantas veces le da?", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table3.addCell(cell);

                PdfPTable table56 = new PdfPTable(1);
                if ((aiepi.getSuma5() & 32) == 32) {
                    cell = new PdfPCell(new Phrase("6. Debe comer 5 veces al dia, 3 comidas principales y dos entre comidas (alimento complementario)", DATO_VERIFICADO));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table56.addCell(cell);
                } else {
                    cell = new PdfPCell(new Phrase("6. Debe comer 5 veces al dia, 3 comidas principales y dos entre comidas (alimento complementario)", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setBorder(Rectangle.NO_BORDER);
                    table56.addCell(cell);
                }
                cell = new PdfPCell(table56);
                table3.addCell(cell);

                cell = new PdfPCell(new Phrase(aiepi.getOlor(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table3.addCell(cell);

                cell = new PdfPCell(new Phrase(aiepi.getOtros(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table3.addCell(cell);

                //Septima fILA DE alimentacion        
                cell = new PdfPCell(new Phrase("- El niño come su rpopio plato?", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table3.addCell(cell);

                PdfPTable table57 = new PdfPTable(1);
                if ((aiepi.getSuma5() & 64) == 64) {
                    cell = new PdfPCell(new Phrase("7. El niño debe tener su propio platore comidas y compartir con padres, hermanos u otros personas", DATO_VERIFICADO));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table57.addCell(cell);
                } else {
                    cell = new PdfPCell(new Phrase("7. El niño debe tener su propio platore comidas y compartir con padres, hermanos u otros personas", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setBorder(Rectangle.NO_BORDER);
                    table57.addCell(cell);
                }
                cell = new PdfPCell(table57);
                table3.addCell(cell);

                cell = new PdfPCell(new Phrase(aiepi.getPenta(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table3.addCell(cell);

                cell = new PdfPCell(new Phrase(aiepi.getPesonac(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table3.addCell(cell);

                //Octava fILA DE alimentacion        
                cell = new PdfPCell(new Phrase("-Quien le da de comer?", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table3.addCell(cell);

                PdfPTable table58 = new PdfPTable(1);
                if ((aiepi.getSuma5() & 128) == 128) {
                    cell = new PdfPCell(new Phrase("8. La madre o cuidador debe hacerle comer, con mucha paciencia y amor", DATO_VERIFICADO));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table58.addCell(cell);
                } else {
                    cell = new PdfPCell(new Phrase("8. La madre o cuidador debe hacerle comer, con mucha paciencia y amor", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setBorder(Rectangle.NO_BORDER);
                    table58.addCell(cell);
                }
                cell = new PdfPCell(table58);
                table3.addCell(cell);

                cell = new PdfPCell(new Phrase(aiepi.getPiocitos(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table3.addCell(cell);

                cell = new PdfPCell(new Phrase(aiepi.getProteinas(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table3.addCell(cell);

                //Novena fILA DE alimentacion        
                cell = new PdfPCell(new Phrase("-Si el niño esta enfermo durante la enfermedad Ha realizado algun cambio en la alimentacion?oCual fue?", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table3.addCell(cell);

                PdfPTable table59 = new PdfPTable(1);
                if ((aiepi.getSuma5() & 256) == 256) {
                    cell = new PdfPCell(new Phrase("9. Durante la enfermedad del niño se le debe continuar alimentando y se le debe dar mas liquidos", DATO_VERIFICADO));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table59.addCell(cell);
                } else {
                    cell = new PdfPCell(new Phrase("9. Durante la enfermedad del niño se le debe continuar alimentando y se le debe dar mas liquidos", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setBorder(Rectangle.NO_BORDER);
                    table59.addCell(cell);
                }
                if ((aiepi.getSuma5() & 512) == 512) {
                    cell = new PdfPCell(new Phrase("10. Despues de la enfermedad necesita comidad extras para que se recupere", DATO_VERIFICADO));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table59.addCell(cell);
                } else {
                    cell = new PdfPCell(new Phrase("10. Despues de la enfermedad necesita comidad extras para que se recupere", DATO_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setBorder(Rectangle.NO_BORDER);
                    table59.addCell(cell);
                }
                cell = new PdfPCell(table59);
                table3.addCell(cell);

                cell = new PdfPCell(new Phrase(aiepi.getReaccion(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table3.addCell(cell);

                cell = new PdfPCell(new Phrase(aiepi.getReferido(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table3.addCell(cell);

                cell = new PdfPCell(new Phrase("\nEVALUAR OTROS PROBLEMAS                                                                                                                       Calificar                                      Tratar", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setColspan(4);
                table3.addCell(cell);

                cell = new PdfPCell(new Phrase("Pregunte a la madre o cuidador si el niño/a tiene otros problemas\n\n", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setColspan(2);
                table3.addCell(cell);

                cell = new PdfPCell(new Phrase(aiepi.getSangre(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table3.addCell(cell);

                cell = new PdfPCell(new Phrase(aiepi.getSbas(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table3.addCell(cell);

                cell = new PdfPCell(new Phrase("Pregunte por la salud de la madre\n\n", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setColspan(2);
                table3.addCell(cell);

                cell = new PdfPCell(new Phrase(aiepi.getSblancos(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table3.addCell(cell);

                cell = new PdfPCell(new Phrase(aiepi.getScay(), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table3.addCell(cell);

                cell = new PdfPCell(new Phrase("\n\nVOLVER PARA LA CONSULTA DE SEGUIMIENTO O CONTROL   : ___" + format(aiepi.getFec_ingreso(), "dd/MM/yyyy"), DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setColspan(4);
                table3.addCell(cell);

                cell = new PdfPCell(new Phrase("\n\nNONBRES Y APELLIDOS DEL PERSONAL DE SALUD :____", DATO_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setColspan(4);
                table3.addCell(cell);
            }///fin de alimentaion mYOR DE 6 MESES

            document.add(table3);

        } ///fin de mayor de 2 meses

    }

    public static String format(Date dia, String formato) {
        if (dia == null) {
            return "";
        }
        SimpleDateFormat formatDate = new SimpleDateFormat(formato);
        return formatDate.format(dia);
    }
}
