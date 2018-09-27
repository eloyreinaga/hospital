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

public class VerReporteSnis301PDF extends AbstractPdfView {

    private static final Font TITULO_FONT = new Font(Font.HELVETICA, 12, Font.BOLD, Color.black);
    private static final Font CABEZA_COLUMNA_FONT = new Font(Font.TIMES_ROMAN, 7, Font.NORMAL, Color.black);
    private static final Font DATO_FONT = new Font(Font.TIMES_ROMAN, 7, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_BOLD = new Font(Font.HELVETICA, 8, Font.BOLD, Color.black);
    private static final int MARGIN = 32;

    protected void buildPdfMetadata(Map model, Document document, HttpServletRequest request) {
        document.setPageSize(PageSize.LEGAL);
        document.setMargins(10, 10, 10, 10);
    }

    protected void buildPdfDocument(Map model, Document document, PdfWriter writer, HttpServletRequest req, HttpServletResponse resp) throws Exception {

        //java.util.List lFopos = (java.util.List)model.get("listaCobros");
        Cuadernos datoOdon = (Cuadernos) model.get("datoOdon");//x
        Cuadernos datoNutri = (Cuadernos) model.get("datoNutri");//x
        Cuadernos datovac1 = (Cuadernos) model.get("datoVac1");
        Cuadernos datovac2 = (Cuadernos) model.get("datoVac2");
        Cuadernos datovac3 = (Cuadernos) model.get("datoVac3");
        Cuadernos datovac4 = (Cuadernos) model.get("datoVac4");

        Cuadernos datoenfer = (Cuadernos) model.get("datoEnfer");//x

        Cuadernos datoe1 = (Cuadernos) model.get("datoExt1");//x
        Cuadernos datoe2 = (Cuadernos) model.get("datoExt2");//x
        Cuadernos datoe3 = (Cuadernos) model.get("datoExt3");//x
        Cuadernos datoe4 = (Cuadernos) model.get("datoExt4");//x
        Cuadernos datoNPrev = (Cuadernos) model.get("datoNPrev");//x
        Cuadernos datoCPrev = (Cuadernos) model.get("datoCPrev");//x
        Cuadernos datoUPrev = (Cuadernos) model.get("datoUPrev");//x
        Cuadernos datoControlPre = (Cuadernos) model.get("datoControlPre");//x
        Cuadernos datoPartoServ = (Cuadernos) model.get("datoPartoServ");//x
        Cuadernos datoPartoDomi = (Cuadernos) model.get("datoPartoDomi");//x

        String gestion = (String) model.get("gestion");
        String mes = (String) model.get("mes");
        String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        Clientes dato = (Clientes) model.get("dato");
        // coloca el titulo de la pagina
        Image escudo = Image.getInstance("/opt/imagenes/mescudo.bmp");
        Paragraph p = new Paragraph("INFORME MENSUAL DE PRODUCCION DE SERVICIOS\nCODIGO R.A. SALUD INE-301 (1/2007)", new Font(Font.HELVETICA, 10, Font.BOLDITALIC, new Color(0, 0, 0)));
        p.setAlignment(Element.ALIGN_CENTER);

        PdfPCell cell;

        PdfPTable tablex = new PdfPTable(2);
        int[] fxwidths = {15, 70}; // percentage
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

        document.add(tablex);

        PdfPTable table1 = new PdfPTable(3);
        int[] fmwidths = {33, 33, 33}; // percentage
        table1.setWidths(fmwidths);
        table1.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("SEDES : " + dato.getDepartamento() + "      Localidad:....................", DATO_FONT));
        cell.setBorder(Rectangle.NO_BORDER);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("Red de salud: " + dato.getRed(), DATO_FONT));
        cell.setBorder(Rectangle.NO_BORDER);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("Municipio : " + dato.getLocalidad(), DATO_FONT));
        cell.setBorder(Rectangle.NO_BORDER);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("Establecimiento : " + dato.getEstablecimiento(), DATO_FONT));
        cell.setBorder(Rectangle.NO_BORDER);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("Mes : " + meses[Integer.parseInt(mes) - 1] + "         " + "Año : " + gestion, DATO_FONT));
        cell.setBorder(Rectangle.NO_BORDER);
        table1.addCell(cell);

        cell = new PdfPCell(new Phrase("subsector : ...........  ", DATO_FONT));
        cell.setBorder(Rectangle.NO_BORDER);
        table1.addCell(cell);

        document.add(table1);

        // tabla final
        int NumColumnsfin = 3;
        PdfPTable tablefin = new PdfPTable(NumColumnsfin);
        int[] fwidthsfin = {49, 2, 49};
        tablefin.setWidths(fwidthsfin);
        tablefin.setWidthPercentage(100);

        //tabla de la izquierda
        int NumColumnsI = 2;
        PdfPTable tablei = new PdfPTable(NumColumnsI);
        int[] fwidthsI = {90, 10};
        tablei.setWidths(fwidthsI);
        tablei.setWidthPercentage(100);

        // consulta externa
        int NumColumns = 5;
        PdfPTable table = new PdfPTable(NumColumns);
        int[] fwidths = {40, 15, 15, 15, 15};
        table.setWidths(fwidths);
        table.setWidthPercentage(100);
        String sCampos[] = {"1 CONSULTA EXTERNA", "NUEVAS\nM", "\nF", "REPETIDAS\nM", "\nF"};
        for (int i = 0; i < NumColumns; i++) {
            cell = new PdfPCell(new Phrase(sCampos[i], CABEZA_COLUMNA_FONT));
            if (i > 0) {
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            } else {
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            }
            cell.setGrayFill(0.8f);
            table.addCell(cell);
        }
        addfila6(table, "1.1 Menores de 5 años", datoe1.getSuma1(), datoe3.getSuma1(), datoe2.getSuma1(), datoe4.getSuma1());
        addfila6(table, "1.2 Personas de 5 a 9 años", datoe1.getSuma2(), datoe3.getSuma2(), datoe2.getSuma2(), datoe4.getSuma2());
        addfila6(table, "1.3 Personas de 10 a 20 años", datoe1.getSuma3(), datoe3.getSuma3(), datoe2.getSuma3(), datoe4.getSuma3());
        addfila6(table, "1.4 Personas de 21 a 59 años", datoe1.getSuma4(), datoe3.getSuma4(), datoe2.getSuma4(), datoe4.getSuma4());
        addfila6(table, "1.5 Personas de 60 años o mas", datoe1.getSuma5(), datoe3.getSuma5(), datoe2.getSuma5(), datoe4.getSuma5());
        //agregar a la tabla final
        cell = new PdfPCell(table);
        cell.setColspan(2);
        tablei.addCell(cell);

        addtitulo3(tablei, "2 REFERENCIAS", "");
        addfila3(tablei, "2.1 Pacientes referidos a otros establecimientos", 1);

        addtitulo3(tablei, "3 CONSULTAS ODONTOLOGICAS", "");
        addfila3(tablei, "3.1 Primeras consultas", datoOdon.getPrimera());
        addfila3(tablei, "3.2 Consultas nuevas en menores de 5 años", datoOdon.getSuma1());
        addfila3(tablei, "3.3 Consultas repetidas en menores de 5 años", datoOdon.getSuma2());
        addfila3(tablei, "3.4 Consultas nuevas en personas de 5 años o mas", datoOdon.getSuma3());
        addfila3(tablei, "3.5 Consultas repetidas en personas de 5 años o mas", datoOdon.getSuma4());
        addfila3(tablei, "3.6 Restauraciones", datoOdon.getRestauracion());
        addfila3(tablei, "3.7 Exodoncias", datoOdon.getExodoncia());
        addfila3(tablei, "3.8 Endodoncias", datoOdon.getEndodoncia());
        addfila3(tablei, "3.9 Periodoncias", datoOdon.getPeriodoncia());
        addfila3(tablei, "3.10 Cirugias maxilo faciales", datoOdon.getCirugia());
        addfila3(tablei, "3.11 Acciones preventivas", datoOdon.getPreventiva());
        addfila3(tablei, "3.12 Actividades Odontologicas con la Comunidad", 0);

        addtitulo3(tablei, "4 CONSULTAS PRENATALES", "");
        addfila3(tablei, "4.1 Nuevas antes del 5to mes de embarazo", datoControlPre.getSuma1());
        addfila3(tablei, "4.2 Nuevas a partir del 5to mes de embarazo", datoControlPre.getSuma2());
        addfila3(tablei, "4.3 Repetidas", datoControlPre.getSuma3());
        addfila3(tablei, "4.4 Mujeres con 4ta consulta prenatal", datoControlPre.getSuma4());

        addtitulo3(tablei, "5 Anticoncepcion,Prevencion de ITS y de Cancer de Cuello Uterino", "");
        addfila3(tablei, "5.1 DIU: Usuarias nuevas", datoNPrev.getDiu());
        addfila3(tablei, "5.2 DIU: Usuarias Continuas", datoCPrev.getDiu());
        addfila3(tablei, "5.3 Numero de DIU insertados", datoUPrev.getDiu());
        addfila3(tablei, "5.4 Inyectable trimestral: Usuarias Nuevas", datoNPrev.getInyectable());
        addfila3(tablei, "5.5 Inyectable trimestral: Usuarias Continuas", datoCPrev.getInyectable());
        addfila3(tablei, "5.6 Numero de Inyectables trimestrales suministrados", datoUPrev.getInyectable());
        addfila3(tablei, "5.7 Condon: Usuarias(os) Nuevas(os)", datoNPrev.getCondon());
        addfila3(tablei, "5.8 Condon: Usuarias(os) Continuas(os)", datoCPrev.getCondon());
        addfila3(tablei, "5.9 Numero de condones entregados", datoUPrev.getCondon());
        addfila3(tablei, "5.10 Pildora: Usuarias Nuevas", datoNPrev.getPildora());
        addfila3(tablei, "5.11 Pildora: Usuarias Continuas", datoCPrev.getPildora());
        addfila3(tablei, "5.12 Numero de ciclos entregados", datoUPrev.getPildora());
        addfila3(tablei, "5.13 Metodos naturales: Usuarias Nuevas", datoNPrev.getMnatural());
        addfila3(tablei, "5.14 Metodos naturales: Usuarias Continuas", datoCPrev.getMnatural());
        addfila3(tablei, "5.15 AQV femenino", datoNPrev.getAqv());
        addfila3(tablei, "5.16 AQV masculino", datoCPrev.getAqv());
        addfila3(tablei, "5.17 Numero de personas que recibieron orientacion en anticoncepcion", datoUPrev.getAqv());
        addfila3(tablei, "5.18 Mujeres con muestra de PAP tomada", datoUPrev.getMnatural());

        // consulta externa
        int NumColumnsx = 3;
        table = new PdfPTable(NumColumnsx);
        int[] fwidthsx = {70, 15, 15};
        table.setWidths(fwidthsx);
        table.setWidthPercentage(100);

        addtitulo4(table, "6 CONTROL DE CRECIMIENTO INFANTIL", "NUEVOS", "REPETIDOS");
        addfila4(table, "6.1 Control del niño(a) menor de 1 año", datoNutri.getSuma1(), datoNutri.getSuma2());
        addfila4(table, "6.2 Control del niño(a) menor de 1 año a menor de 2 años", datoNutri.getSuma3(), datoNutri.getSuma4());
        addfila4(table, "6.3 Control del niño(a) menor de 2 años a menor de 5 años", datoNutri.getSuma5(), datoNutri.getSuma6());

        //agregar a la tabla final
        cell = new PdfPCell(table);
        cell.setColspan(2);
        tablei.addCell(cell);

        addtitulo3(tablei, "7 OTRAS ACTIVIDADES DE ENFERMERIA", "");
        addfila3(tablei, "7.1 Sueros", datoenfer.getSuma1());
        addfila3(tablei, "7.2 Inyectables", datoenfer.getSuma2());
        addfila3(tablei, "7.3 Curaciones y/o suturas", datoenfer.getSuma3());

        addtitulo3(tablei, "8 NUTRICION Y MICRONUTRIENTES", "");
        addfila3(tablei, "8.1 Mujeres embarazadas que recibieron 90 tabletas de hierro", datoControlPre.getSfembarazada());
        addfila3(tablei, "8.2 Mujeres puerperas que recibieron 90 tabletas de hierro", datoControlPre.getSfpuerpera());
        addfila3(tablei, "8.3 Niños(as) de 6 meses a menores de 2 años con dosis completa de hierro", datoNutri.getRestauracion());
        addfila3(tablei, "8.4 Niños(as) de 2 a menores de 5 años con dosis completa de hierro", datoNutri.getExodoncia());
        addfila3(tablei, "8.5 Mujeres puerperas con dosis unica de vitamina A", datoControlPre.getVitamina());
        addfila3(tablei, "8.6 Niños(as) de 6 a menores de 1 año con dosis unica de vitamina A", datoNutri.getEndodoncia());
        addfila3(tablei, "8.7 Niños(as) de 1 año a menores de 5 años con 1ra dosis de vitamina A", datoNutri.getPeriodoncia());
        addfila3(tablei, "8.8 Niños(as) de 1 año a menores de 5 años con 2da dosis de vitamina A", datoNutri.getCirugia());
        addfila3(tablei, "8.9 Niños(as) de 6 a 23 meses que reciben alimento Complementario (Nutribebe)", datoNutri.getPreventiva());

        addtitulo3(tablei, "9 ACTIVIDADES CON LA COMUNIDAD", "");
        addfila3(tablei, "9.1 N. de actividades realizadas con participacion de la comunidad", 0);
        addfila3(tablei, "9.2 N. CAI de sector realizados", 0);
        addfila3(tablei, "9.3 N. de comunidades que participaron en el CAI", 0);

        addtitulo3(tablei, "10 ATENCION DE PARTOS DE SERVICIO", "");
        addfila3(tablei, "10.1 Vaginales", datoPartoServ.getSuma1());
        addfila3(tablei, "10.2 Cesareas", datoPartoServ.getSuma2());
        addfila3(tablei, "10.3 Nacidos vivos", datoPartoServ.getSuma3());
        addfila3(tablei, "10.4 Nacidos muertos", datoPartoServ.getSuma4());
        addfila3(tablei, "10.5 Nacidos vivos con peso menor a 2500g", datoPartoServ.getSfembarazada());
        addfila3(tablei, "10.6 Nacidos muertos con peso menor a 2500g ", datoPartoServ.getSfpuerpera());
        addfila3(tablei, "10.7 Mujeres con primer control post parto", datoPartoServ.getVitamina());

        cell = new PdfPCell(new Phrase("DECLARACION JURADA ", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setColspan(2);
        cell.setBorder(Rectangle.NO_BORDER);
        tablei.addCell(cell);
        cell = new PdfPCell(new Phrase(" Yo : " + dato.getNombres(), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setColspan(2);
        cell.setBorder(Rectangle.NO_BORDER);
        tablei.addCell(cell);
        cell = new PdfPCell(new Phrase("                  Nombre y Apellidos", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setColspan(2);
        cell.setBorder(Rectangle.NO_BORDER);
        tablei.addCell(cell);

        cell = new PdfPCell(new Phrase("Declaro la veracidad de datos del presente formulario", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setColspan(2);
        cell.setBorder(Rectangle.NO_BORDER);
        tablei.addCell(cell);

        //agregar a la tabla final
        cell = new PdfPCell(tablei);
        cell.setBorder(Rectangle.NO_BORDER);
        tablefin.addCell(cell);

        cell = new PdfPCell(new Phrase("", CABEZA_COLUMNA_FONT));
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tablefin.addCell(cell);

        // atencion partos en domicilio
        int NumColumnsD = 2;
        PdfPTable tabled = new PdfPTable(NumColumnsD);
        int[] fwidthsD = {90, 10};
        tabled.setWidths(fwidthsD);
        tabled.setWidthPercentage(100);

        addtitulo3(tabled, "11 ATENCION DE PARTOS EN DOMICILIO", "");
        addfila3(tabled, "11.1 Partos atendidos por el personal de salud", datoPartoDomi.getSuma1());
        addfila3(tabled, "11.2 Nacidos vivos atendidos por el personal de salud", datoPartoDomi.getSuma2());
        addfila3(tabled, "11.3 Nacidos muertos atendidos por el personal de salud", datoPartoDomi.getSuma3());
        addfila3(tabled, "11.4 Nacidos vivos con peso menor a 2500g", datoPartoDomi.getVitamina());
        addfila3(tabled, "11.5 Nacidos muertos con peso menor a 2500g", datoPartoDomi.getSemanas());
        addfila3(tabled, "11.6 Partos atendidos por partera capacitada", datoPartoDomi.getSuma4());
        addfila3(tabled, "11.7 Nacidos vivos atendidos por partera capacitada", datoPartoDomi.getSfembarazada());
        addfila3(tabled, "11.8 Nacidos muertos atendidos por partera capacitada", datoPartoDomi.getSfpuerpera());

        addtitulo3(tabled, "12 INTERNACIONES", "");
        addfila3(tabled, "12.1 Ingresos referidos de otros establecimientos de salud", 0);
        addfila3(tabled, "12.2 Ingresos espontaneos", 0);
        addfila3(tabled, "12.3 Egresos", 0);
        addfila3(tabled, "12.4 Fallecidos antes de las 48 horas", 0);
        addfila3(tabled, "12.5 Fallecidos a partir de las 48 horas", 0);
        addfila3(tabled, "12.6 Dias camas ocupadas maternidad", 0);
        addfila3(tabled, "12.7 Dias camas ocupadas otros servicios", 0);
        addfila3(tabled, "12.8 Dias camas disponibles maternidad", 0);
        addfila3(tabled, "12.9 Dias camas disponibles otros servicios", 0);
        addfila3(tabled, "12.10 Constrareferidos", 0);

        addtitulo3(tabled, "13 DIAGNOSTICOS DE EGRESO", "");
        addfila3(tabled, "13.1 Diarreas en menores de 5 años", 0);
        addfila3(tabled, "13.2 Neumonias en menores de 5 años", 0);
        addfila3(tabled, "13.3 Otros en menores de 5 años", 0);
        addfila3(tabled, "13.4 Hipertension Arterial en mayores de 5 años", 0);
        addfila3(tabled, "13.5 Diabetes en mayores de 5 años", 0);
        addfila3(tabled, "13.6 Discapacitados", 0);

        addtitulo3(tabled, "14 CIRUGIA Y ANESTESIAS", "");
        addfila3(tabled, "14.1 Cirugias medianas y mayores", 0);
        addfila3(tabled, "14.2 Anestecias generales, locales o regionales", 0);

        int NumColunsx = 3;
        table = new PdfPTable(NumColunsx);
        int[] fwidtsx = {70, 15, 15};
        table.setWidths(fwidtsx);
        table.setWidthPercentage(100);

        addtitulo4(table, "15 VACUNACIONES EN MENORES DE 1 año", "DENTRO", "FUERA");
        addfila4(table, "15.1 BCG", datovac1.getSuma5(), 0);
        addfila4(table, "15.2 Pentavalente 1ra dosis", datovac1.getSuma1(), 0);
        addfila4(table, "15.3 Pentavalente 2ra dosis", datovac2.getSuma1(), 0);
        addfila4(table, "15.4 Pentavalente 3ra dosis", datovac3.getSuma1(), 0);
        addfila4(table, "15.5 O.P.V. Antipolio 1ra dosis", datovac1.getSuma2(), 0);
        addfila4(table, "15.6 O.P.V. Antipolio 2ra dosis", datovac2.getSuma2(), 0);
        addfila4(table, "15.7 O.P.V. Antipolio 3ra dosis", datovac3.getSuma2(), 0);
        addfila4(table, "15.8 Antirotavirus 1ra dosis", datovac1.getSuma3(), 0);
        addfila4(table, "15.9 Antirotavirus 2ra dosis", datovac2.getSuma3(), 0);
        addfila4(table, "15.10 Antirotavirus 3ra dosis", datovac3.getSuma3(), 0);

        addtitulo4(table, "16 VACUNACIONES (Niños de 12 a 23 meses)", "DENTRO", "FUERA");
        addfila4(table, "16.1 Vacunaciones SRP", datovac2.getSuma5(), 0);
        addfila4(table, "16.2 Vacunaciones Fiebre Amarilla", datovac3.getSuma5(), 0);

        //agregar a la tabla final
        cell = new PdfPCell(table);
        cell.setColspan(2);
        tabled.addCell(cell);

        addtitulo3(tabled, "17 OTRAS VACUNACIONES", "");
        addfila3(tabled, "17.1 Mujeres de 15 a 49 años 1ra dosis de dT", datovac1.getSuma4());
        addfila3(tabled, "17.2 Mujeres de 15 a 49 años 2ra dosis de dT", datovac2.getSuma4());
        addfila3(tabled, "17.3 Mujeres de 15 a 49 años 3ra dosis de dT", datovac3.getSuma4());
        addfila3(tabled, "17.4 Mujeres de 15 a 49 años 4ra dosis de dT", datovac4.getSuma1());
        addfila3(tabled, "17.5 Mujeres de 15 a 49 años 5ra dosis de dT", datovac4.getSuma2());
        addfila3(tabled, "17.6 Antirrabica humana esquema completo", datovac4.getSuma3());
        addfila3(tabled, "17.7 Antirrabica canina", datovac4.getSuma4());

        addtitulo3(tabled, "18 TUBERCULOSIS", "");
        addfila3(tabled, "18.1 N. de Sintematicos Respiratorios", 0);
        addfila3(tabled, "18.2 N. de TBP BAAR (+) nuevos", 0);
        addfila3(tabled, "18.3 N. de TBP BAAR (-) nuevos", 0);
        addfila3(tabled, "18.4 N. de TB extrapulmorar nuevos", 0);
        addfila3(tabled, "18.5 Tratamientos iniciados con esquemas I", 0);
        addfila3(tabled, "18.6 Tratamientos iniciados con esquemas II", 0);
        addfila3(tabled, "18.7 Tratamientos iniciados con esquemas III", 0);
        addfila3(tabled, "18.8 N. de quimioprofilaxis en menores de 5 años", 0);

        addtitulo3(tabled, "19 MALARIA", "");
        addfila3(tabled, "19.1 Numero de muestras hematicas tomadas", 0);
        addfila3(tabled, "19.2 Total de casos confirmados", 0);
        addfila3(tabled, "19.3 Tratamientos iniciados por sospecha", 0);

        addtitulo3(tabled, "20 CHAGAS", "");
        addfila3(tabled, "20.1 Viviendas evaluadas", 0);
        addfila3(tabled, "20.2 Viviendas positivas", 0);
        addfila3(tabled, "20.3 Tratamientos iniciados de recien nacido a menores de 1 año", 0);
        addfila3(tabled, "20.4 Tratamientos terminados de recien nacido a menores de 1 año", 0);
        addfila3(tabled, "20.5 Tratamientos iniciados en ninos(as) de 1 año a 14 años de edad", 0);
        addfila3(tabled, "20.6 Tratamientos terminados en ninos(as) de 1 año a 14 años de edad", 0);

        addtitulo3(tabled, "21 CONTROL VECTORIAL DE DENGUE", "");
        addfila3(tabled, "21.1 Numero de actividades realizadas con la comunidad", 1);

        cell = new PdfPCell(new Phrase("                          Firma:  .................", DATO_FONT));
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(2);

        tabled.addCell(cell);
        cell = new PdfPCell(new Phrase("\n\nFecha de emision : " + format(new Date(), "dd/MM/yyyy"), DATO_FONT));
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(2);
        tabled.addCell(cell);

        // agregar a la tabla final
        cell = new PdfPCell(tabled);
        cell.setBorder(Rectangle.NO_BORDER);
        tablefin.addCell(cell);
        // agregar al documento  la tabla final
        document.add(tablefin);

    }

    public static void addfila4(PdfPTable table, String a, int c, int d) {

        PdfPCell cell;
        cell = new PdfPCell(new Phrase(a, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(Integer.toString(c), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(Integer.toString(d), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

    }

    public static void addtitulo4(PdfPTable table, String a, String b, String c) {

        PdfPCell cell;
        cell = new PdfPCell(new Phrase(a, CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setGrayFill(0.8f);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(b, CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.8f);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(c, CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.8f);
        table.addCell(cell);
    }

    public static void addfila6(PdfPTable table, String a, int c, int d, int x, int y) {

        PdfPCell cell;
        cell = new PdfPCell(new Phrase(a, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(Integer.toString(c), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(Integer.toString(d), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(Integer.toString(x), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(Integer.toString(y), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
    }

    public static void addfila3(PdfPTable table, String a, int c) {

        PdfPCell cell;
        cell = new PdfPCell(new Phrase(a, DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(Integer.toString(c), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

    }

    public static void addtitulo3(PdfPTable table, String a, String c) {

        PdfPCell cell;
        cell = new PdfPCell(new Phrase(a, CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setGrayFill(0.8f);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(c, CABEZA_COLUMNA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setGrayFill(0.8f);
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
