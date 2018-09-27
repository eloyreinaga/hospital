package org.ayaic.web.administrarcuadernos;

import java.awt.Color;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.view.document.AbstractPdfView;
import com.itextpdf.text.pdf.BarcodeQRCode;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.*;
import java.text.*;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;
import org.ayaic.domain.Camas;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Historiales;
import org.ayaic.domain.Localidades;
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.Personas;

public class ConsentimientoPDF extends AbstractPdfView {

    private static final Font TITULO_FONT = new Font(Font.HELVETICA, 12, Font.BOLD, Color.black);
    private static final Font TITULO_FONT_B = new Font(Font.HELVETICA, 13, Font.BOLD, Color.black);
    private static final Font CABEZA_COLUMNA_FONT = new Font(Font.HELVETICA, 10, Font.ITALIC, Color.black);
    private static final Font DATO_FONT = new Font(Font.HELVETICA, 11, Font.NORMAL, Color.black);
    private static final Font DATO_FONT2 = new Font(Font.HELVETICA, 11, Font.NORMAL, Color.white);
    private static final Font DATO_FONT_BOLD = new Font(Font.COURIER, 10, Font.BOLD, Color.black);
    private static final Font marcagua = new Font(Font.HELVETICA, 26, Font.BOLD, Color.lightGray);
    private static final int MARGIN = 32;

    protected void buildPdfMetadata(Map model, Document document, HttpServletRequest request) {
        document.setPageSize(PageSize.LETTER);
        document.setMargins(20, 20, 25, 20);
    }

    protected void buildPdfDocument(Map model, Document document, PdfWriter writer, HttpServletRequest req, HttpServletResponse resp) throws Exception {

        Historiales datopac = (Historiales) model.get("Historia");
        java.util.List cie = (java.util.List) model.get("listaCie");
        Personas datoMed = (Personas) model.get("empleado");
        Camas buscarCama = (Camas) model.get("buscarCama");
        Pacientes pacientes = (Pacientes) model.get("datosp");
        java.util.List med = (java.util.List) model.get("listarRecetaTotal");
        Localidades datoestab = (Localidades) model.get("datoestab");
        Clientes dato = (Clientes) model.get("dato");
        DecimalFormat df = new DecimalFormat("###,##0");
        PdfContentByte cb = writer.getDirectContent();
        String cod = (String) model.get("cod");
        Date fecha = new Date();
        InetAddress ip;
        ip = InetAddress.getLocalHost();
        NetworkInterface network = NetworkInterface.getByInetAddress(ip);
        //byte[] mac = network.getHardwareAddress();

        Paragraph pp = new Paragraph("\n", new Font(Font.HELVETICA, 14, Font.BOLDITALIC, new Color(0, 0, 0)));
        pp.setAlignment(Element.ALIGN_CENTER);

        int[] sumas = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int NumColumns = 3;
        int una, filas = 30;

        PdfPCell cell;

        Paragraph p1;

        PdfPTable tablex = new PdfPTable(3);
        int[] fxwidths = {35, 75, 20}; // percentage
        tablex.setWidths(fxwidths);
        tablex.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase(datoestab.getNombreseg() + "\n" + dato.getEstablecimiento() + "\n\n", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex.addCell(cell);

        cell = new PdfPCell(new Phrase("\nCONSENTIMIENTO INFORMADO DE INTERNACION\nPOR SERVICIO DE EMERGENCIA \n" + dato.getEstablecimiento() + "\n", TITULO_FONT_B));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex.addCell(cell);

        cell = new PdfPCell(new Phrase("Form. DM. 00", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex.addCell(cell);

        cell = new PdfPCell(new Phrase("\n\n\n\n\nMediante la presente, el paciente ...." + pacientes.getNombre() + " " + pacientes.getPaterno() + "  " + pacientes.getMaterno() + "...con numero de seguro..." + pacientes.getNro_registro() + "ï¿½y sus familiares han sido informados sobre su necesidad de Internacion por emergencias, por los diagnï¿½sticos presuntivos de: ", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(3);
        tablex.addCell(cell);

        if (datopac.getDiagnostico() == null || "null".equals(datopac.getDiagnostico())) {
            datopac.setDiagnostico("");
        } else {
            datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("<p>", "\n"));
            datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("</p>", " "));
            datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("&nbsp;", ""));
            datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("<strong>", " "));
            datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("</strong>", " "));
            datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("<br />", "\n"));
            datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("<u>", " "));
            datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("</u>", " "));
            datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("<ul>", " "));
            datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("</ul>", " "));
            datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("<ol>", " "));
            datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("</ol>", " "));
            datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("<li>", " "));
            datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("</li>", " "));
            datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("&ordf;", " "));
            datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("&ntilde;", "n"));
            datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("&Ntilde;", "N"));
            datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("&Aacute;", "A"));
            datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("&Eacute;", "E"));
            datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("&Iacute;", "I"));
            datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("&Oacute;", "O"));
            datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("&Uacute;", "U"));
            datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("&aacute;", "a"));
            datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("&eacute;", "e"));
            datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("&iacute;", "i"));
            datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("&oacute;", "o"));
            datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("&uacute;", "u"));
            datopac.setDiagnostico(datopac.getDiagnostico().replaceAll("&quot;", "'"));
            datopac.setSubjetivo(datopac.getSubjetivo().replaceAll("&ordm;", "o"));
        }

        cell = new PdfPCell(new Phrase("\nDiagnostico : " + datopac.getDiagnostico(), DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setFixedHeight(70f);
        cell.setColspan(3);
        tablex.addCell(cell);

        PdfPTable tablex2 = new PdfPTable(7);
        int[] fxwidths2 = {100, 5, 5, 5, 5, 5, 5}; // percentage
        tablex2.setWidths(fxwidths2);
        tablex2.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("\nEstimado paciente y/o familiar: ", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(7);
        tablex2.addCell(cell);

        cell = new PdfPCell(new Phrase("Lea cuidadosamente este formulario y marque con una X la decision que tome ", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(7);
        tablex2.addCell(cell);

        cell = new PdfPCell(new Phrase("\nAcepta ser internado voluntariamente por emergencia? ", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex2.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex2.addCell(cell);

        cell = new PdfPCell(new Phrase("SI", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex2.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        tablex2.addCell(cell);

        cell = new PdfPCell(new Phrase("NO", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex2.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        tablex2.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex2.addCell(cell);

        cell = new PdfPCell(new Phrase("\nUsted tiene la decision de aceptar en forma libre y sin presiones, que por necesidad diagnostica o de tratamiento, despuï¿½s de la explicacion que se le ha dado, que durante su internacion puede ser necesario realizarle exï¿½menes clï¿½nicos, complementarios de laboratorios, imagenolï¿½gicos como ecografias, radiografias, tomografias y otros segï¿½n sean solicitados, con fines de seguimiento, valoracion de la evolucion, o aclaracion diagnostica; en numero y frecuencia que fuesen necesarios. ", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(7);
        tablex2.addCell(cell);

        cell = new PdfPCell(new Phrase("\nAcepta que le realicen los exï¿½menes, Anteriormente citados si fueran necesarios?", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex2.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex2.addCell(cell);

        cell = new PdfPCell(new Phrase("SI", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex2.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        tablex2.addCell(cell);

        cell = new PdfPCell(new Phrase("NO", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex2.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        tablex2.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex2.addCell(cell);

        cell = new PdfPCell(new Phrase("\nDurante su internacion puede que sea necesarios procedimientos invasivos como instalacion de sondas vesicales, sondas nasogï¿½stricas, sondas rectales, vï¿½as venosas centrales, puncion lumbar, y otros que sean necesarios para ayuda diagnostica o de tratamiento, procedimientos que pudiesen realizarse en ausencia de familiares por la urgencia que el caso lo amerite, indicando que los mismos no estï¿½n exentos de riesgos y complicaciones. (Se le darï¿½ la explicacion correspondiente en caso de requerirlos y usted puede realizar las preguntas que vea conveniente) ", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(7);
        tablex2.addCell(cell);

        cell = new PdfPCell(new Phrase("\nAcepta que le realicen los procedimientos, Anteriormente citados si fueran necesarios?", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex2.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex2.addCell(cell);

        cell = new PdfPCell(new Phrase("SI", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex2.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        tablex2.addCell(cell);

        cell = new PdfPCell(new Phrase("NO", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex2.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        tablex2.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex2.addCell(cell);

        cell = new PdfPCell(new Phrase("\nDurante su atencion e internacion puede ser necesaria la colaboracion de sueros e inyectables y, el uso de agujas o catï¿½teres puede provocarle alguna molestia o dolor, asi como complicaciones locales, es decir, en lugar de la puncion (como irritacion, equimosis, abscesos hematomas) y complicaciones alï¿½rgicas generales de manifestacion variable.", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(7);
        tablex2.addCell(cell);

        cell = new PdfPCell(new Phrase("\nAcepta la colocacion de sueros e inyectables, Anteriormente citados si fueran necesarios", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex2.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex2.addCell(cell);

        cell = new PdfPCell(new Phrase("SI", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex2.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        tablex2.addCell(cell);

        cell = new PdfPCell(new Phrase("NO", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex2.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        tablex2.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex2.addCell(cell);

        cell = new PdfPCell(new Phrase("\nSu estancia en el servicio de Emergencias, puede variar de acuerdo a la enfermedad o diagnostico que se definan y que necesite cuidados, controles consecutivos y tambiï¿½n de acuerdo a la disponibilidad de camas en la institucion, es decir en los diferentes Servicios del Hospital, donde el personal mï¿½dico y de enfermerï¿½a del Servicio de Emergencias, no tiene tuicion sobre estos y que en ocasiones pueden ser de varios dias.", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(7);
        tablex2.addCell(cell);

        cell = new PdfPCell(new Phrase("\nAcepta quedarse en el Servicio de Emergencias, el tiempo necesario hasta que se defina que no requiere manejo por emergencias, o tambiï¿½n porque no hay camas libres en el servicio que le corresponde?", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(7);
        tablex2.addCell(cell);

        cell = new PdfPCell(new Phrase("\n", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex2.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex2.addCell(cell);

        cell = new PdfPCell(new Phrase("SI", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex2.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        tablex2.addCell(cell);

        cell = new PdfPCell(new Phrase("NO", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex2.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        tablex2.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex2.addCell(cell);

        cell = new PdfPCell(tablex2);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(3);
        tablex.addCell(cell);

        cod = format(fecha, "dd/MM/yyyy HH:mm") + '|' + dato.getCod_esta() + '|' + dato.getId_usuario() + '|' + ip.getHostAddress() + '|' + datopac.getCod_esta() + '|' + datopac.getId_historial() + '|' + datopac.getId_historia();

        PdfContentByte cb1 = writer.getDirectContent();
        BarcodeQRCode qrcode = new BarcodeQRCode(cod, 90, 90, null);
        java.awt.Image qrImage = qrcode.createAwtImage(Color.black, Color.white);
        Image finalImage = Image.getInstance(writer, qrImage, 1);
        finalImage.setAbsolutePosition(20, 240);
        cb.addImage(finalImage, 90, 0, 0, 90, 25, 660, true);
        ///(imagen,tamañoAncho ,rotation_x ,angulorotation , amplitud-altura ,TamañoAlto ,PosicionX ,PosicionY(0=abajo, 280=arriba))

        document.add(tablex);
        document.newPage();

        ////////////////nueva pagina    
        PdfPTable tablex22 = new PdfPTable(7);
        int[] fxwidths22 = {100, 5, 5, 5, 5, 5, 5}; // percentage
        tablex22.setWidths(fxwidths22);
        tablex22.setWidthPercentage(100);

        cell = new PdfPCell(new Phrase("\nEn caso de necesidad de valoraciones por otras especialidades, a veces la institucion no cuenta con las mismas en ciertos horarios, por lo que SERÁn diferidas.", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(7);
        tablex22.addCell(cell);

        cell = new PdfPCell(new Phrase("\nAcepta que se le a informado de esta situacion?", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex22.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex22.addCell(cell);

        cell = new PdfPCell(new Phrase("SI", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex22.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        tablex22.addCell(cell);

        cell = new PdfPCell(new Phrase("NO", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex22.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        tablex22.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex22.addCell(cell);

        cell = new PdfPCell(new Phrase("\nUsted debe conocer que una ves que pase al Servicio que le corresponda, SERÁn otros profesionales los que estarï¿½n a cargo de su atencion, valoracion y seguimiento", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(7);
        tablex22.addCell(cell);

        cell = new PdfPCell(new Phrase("\nAcepta que se le ha informado de esta situacion?", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex22.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex22.addCell(cell);

        cell = new PdfPCell(new Phrase("SI", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex22.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        tablex22.addCell(cell);

        cell = new PdfPCell(new Phrase("NO", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex22.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        tablex22.addCell(cell);

        cell = new PdfPCell(new Phrase("", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        tablex22.addCell(cell);

        cell = new PdfPCell(new Phrase("\nSegï¿½n el control, exï¿½menes complementarios y/o evolucion, puede que su diagnostico o el de su familiar cambie en relacion a su ingreso o se pueda aï¿½adir otro e incluso requerir una intervencion quirurgica de urgencia, procedimiento que SERÁ informado a su debido momento y para esto el paciente y/o familiar deberï¿½n dar la autorizacion o Consentimiento informado respectivo.", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(7);
        tablex22.addCell(cell);

        cell = new PdfPCell(new Phrase("\nHabiendo entendido el cuadro, aclarado dudas y conceptos mï¿½dicos, paciente y familiares quedan informados, y dan su consentimiento para los procedimientos anteriormente mencionados, conociendo que el presente documento, esta basado en los documentos Tï¿½cnicos Normativos, para Hospitalizacion, establecido por el Ministerio de Salud el INASES, y la Caja Nacional de Salud.", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(7);
        tablex22.addCell(cell);

        cell = new PdfPCell(new Phrase("\n\n\n\n\n                          .........................                                                  ................................               ", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(7);
        tablex22.addCell(cell);

        cell = new PdfPCell(new Phrase("                              PACIENTE                                       FAMILIAR COMO REPRESENTANTE LEGAL                       ", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(7);
        tablex22.addCell(cell);

        cell = new PdfPCell(new Phrase("\n\n\n\n\n                                                                                                       ................................               ", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(7);
        tablex22.addCell(cell);

        cell = new PdfPCell(new Phrase("                   " + dato.getDepartamento() + ",      " + format(new Date(), "dd/MM/yyyy") + "                                        FIRMA DEL MEDICO QUE INFORMA                       ", DATO_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(7);
        tablex22.addCell(cell);

        document.add(tablex22);

        if (datoMed.getUrgencias() == 1) {
            ColumnText.showTextAligned(writer.getDirectContentUnder(), Element.ALIGN_RIGHT,
                    new Paragraph("URGENCIAS", marcagua), 580, 490, 90);    ///marca de agua   x.y.rotation 
        } else {   ////////////////////////////////////,del margen, de altura
            ColumnText.showTextAligned(writer.getDirectContentUnder(), Element.ALIGN_RIGHT,
                    new Paragraph(datoMed.getConsultorio().toUpperCase(), marcagua), 580, 490, 90);    ///marca de agua
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
