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
import org.ayaic.domain.Provincias;

public class ImpHemoPDF extends AbstractPdfView {

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
        Pacientes datoPaciente = (Pacientes) model.get("datos");
        java.util.List datosgral = (java.util.List) model.get("datosgral");
        java.util.List ListaLab = (java.util.List) model.get("listarLab");
        java.util.List datosImpHemo = (java.util.List) model.get("datosImpHemo");
        java.util.List datosImpOrin = (java.util.List) model.get("datosImpOrin");
        java.util.List datosImpOtro = (java.util.List) model.get("datosImpOtro");
        java.util.List datosEmba = (java.util.List) model.get("datosEmba");
        java.util.List datosEcos = (java.util.List) model.get("datosEco");
        java.util.List ldatoLab = (java.util.List) model.get("datosLab");
        java.util.List hemograma = (java.util.List) model.get("hemo");
        java.util.List orina = (java.util.List) model.get("orina");
        Personas datoMed = (Personas) model.get("datosMed");
        Cuadernos lista = (Cuadernos) model.get("lista");
        Historiales datoHis = (Historiales) model.get("datosHis");
        Provincias prov = (Provincias) model.get("buscarProvincia");
        Localidades local = (Localidades) model.get("buscarLocalidad");
        String accionl = (String) model.get("accionl");
        Date fecha = new Date();
        int alto = 780;

        BaseFont bf = BaseFont.createFont("Helvetica", "Cp1252", false);
        PdfContentByte cb = writer.getDirectContent();

        if (hemograma.isEmpty() && orina.isEmpty() && datosgral.isEmpty()) {
            Paragraph p = new Paragraph("No existe Datos", new Font(Font.HELVETICA, 22, Font.BOLDITALIC, new Color(0, 0, 0)));
            document.add(p);
        }

        if (hemograma.size() > 0 && "hemograma".equals(accionl)) { //Esto es es para la orden HEMOGRAMA

            Cuadernos gral = (Cuadernos) datosgral.get(0);

            ///Coloca datos de departamento
            cb.beginText();
            cb.setFontAndSize(bf, gral.getCirugia());
            cb.setTextMatrix(gral.getExodoncia(), alto - gral.getPeriodoncia());
            cb.showText(dato.getDepartamento());
            cb.endText();
            ///Coloca datos de red
            cb.beginText();
            cb.setFontAndSize(bf, gral.getPreventiva());
            cb.setTextMatrix(gral.getEndodoncia(), alto - gral.getRayosx());
            cb.showText(dato.getRed());
            cb.endText();
            ///Coloca datos de municipio
            cb.beginText();
            cb.setFontAndSize(bf, gral.getSuma1());
            cb.setTextMatrix(gral.getPrimera(), alto - gral.getNumpieza());
            cb.showText(dato.getLocalidad());
            cb.endText();
            ///Coloca datos de consultorio
            cb.beginText();
            cb.setFontAndSize(bf, gral.getSuma4());
            cb.setTextMatrix(gral.getSuma2(), alto - gral.getSuma3());
            cb.showText(datoMed.getConsultorio());
            cb.endText();
            ///Coloca datos de fechareporte
            Cuadernos datoLab = (Cuadernos) ldatoLab.get(0);
            cb.beginText();
            cb.setFontAndSize(bf, gral.getSuma7());
            cb.setTextMatrix(gral.getSuma5(), alto - gral.getSuma6());
            cb.showText(format(datoLab.getFechae(), "dd/MM/yyyy"));
            cb.endText();
            ///Coloca datos de numero registro
            cb.beginText();
            cb.setFontAndSize(bf, gral.getViolencia());
            cb.setTextMatrix(gral.getFuma(), alto - gral.getAlcohol());
            cb.showText(Integer.toString(datoLab.getId_cuaderno()));
            cb.endText();
            ///Coloca datos de HCL
            cb.beginText();
            cb.setFontAndSize(bf, gral.getSistemica());
            cb.setTextMatrix(gral.getAuto(), alto - gral.getUrinaria());
            cb.showText(Integer.toString(datoPaciente.getHcl()));
            cb.endText();
            ///Coloca datos de Nombe paciente
            cb.beginText();
            cb.setFontAndSize(bf, gral.getGlome());
            cb.setTextMatrix(gral.getArterial(), alto - gral.getDiabetes());
            cb.showText(datoPaciente.getNombres());
            cb.endText();
            ///Coloca datos de HCL
            cb.beginText();
            cb.setFontAndSize(bf, gral.getLitiasis());
            cb.setTextMatrix(gral.getTracto(), alto - gral.getLupus());
            cb.showText(Integer.toString(datoHis.getEdad()));
            cb.endText();
            ///Coloca datos de Sexo Paciente
            if (datoPaciente.getId_tipo_sexo() == 1) {
                cb.beginText();
                cb.setFontAndSize(bf, gral.getDisli());
                cb.setTextMatrix(gral.getNooplasia(), alto - gral.getNefro());
                cb.showText("Femenino");
                cb.endText();
            } else {
                cb.beginText();
                cb.setFontAndSize(bf, gral.getDisli());
                cb.setTextMatrix(gral.getNooplasia(), alto - gral.getNefro());
                cb.showText("Masculino");
                cb.endText();
            }
            ///Coloca datos de Nombre Establecimiento
            cb.beginText();
            cb.setFontAndSize(bf, gral.getPrematuro());
            cb.setTextMatrix(gral.getFrenal(), alto - gral.getBajopeso());
            cb.showText(lista.getNombre());
            cb.endText();
            ///Coloca datos de Tipo Seguro
            if ("S".equals(datoPaciente.getId_estado())) {
                cb.beginText();
                cb.setFontAndSize(bf, gral.getTuberculo());
                cb.setTextMatrix(gral.getEritro(), alto - gral.getRenal());
                cb.showText("SPS(SUMI)");
                cb.endText();
            } else {
                if ("E".equals(datoPaciente.getId_estado())) {
                    cb.beginText();
                    cb.setFontAndSize(bf, gral.getTuberculo());
                    cb.setTextMatrix(gral.getEritro(), alto - gral.getRenal());
                    cb.showText("Particular");
                    cb.endText();
                }
                cb.beginText();
                cb.setFontAndSize(bf, gral.getTuberculo());
                cb.setTextMatrix(gral.getEritro(), alto - gral.getRenal());
                cb.showText("Asegurado");
                cb.endText();
            }
            cb.beginText();
            cb.setFontAndSize(bf, gral.getTuberculo());
            cb.setTextMatrix(gral.getEritro(), alto - gral.getRenal());
            cb.showText(datoPaciente.getId_estado());
            cb.endText();
            ///Coloca datos de Nombre Medico
            cb.beginText();
            cb.setFontAndSize(bf, gral.getReuma());
            cb.setTextMatrix(gral.getOtro(), alto - gral.getCardio());
            cb.showText(lista.getNombres());
            cb.endText();
            ///Coloca datos de Nombre del Laboratorio

            cb.beginText();
            cb.setFontAndSize(bf, gral.getReuma());
            cb.setTextMatrix(gral.getOtro(), alto - gral.getCardio());
            cb.showText(lista.getNombres());
            cb.endText();

            cb.beginText();
            cb.setFontAndSize(bf, gral.getSuma13());
            cb.setTextMatrix(gral.getSuma11(), alto - gral.getSuma12());
            cb.showText("Hemograma");
            cb.endText();

            cb.beginText();
            cb.setFontAndSize(bf, gral.getSuma16());
            cb.setTextMatrix(gral.getSuma14(), alto - gral.getSuma15());
            cb.showText(Integer.toString(lista.getNumpieza()));
            cb.endText();

            ////llena los datos de el hemograma
            Cuadernos imphemo = (Cuadernos) datosImpHemo.get(0); //datos impresion Hemograma
            Cuadernos HEMO = (Cuadernos) hemograma.get(0);  //datos de hemograma

            ///Coloca datos de globulos rojos
            cb.beginText();
            cb.setFontAndSize(bf, imphemo.getCirugia());
            cb.setTextMatrix(imphemo.getExodoncia(), alto - imphemo.getPeriodoncia());
            if ("0".equals(HEMO.getSglorojos())) {
                cb.showText("");
                cb.endText();
            } else {
                cb.showText(HEMO.getSglorojos());
                cb.endText();
            }
            ///Coloca datos de globulos blancos
            cb.beginText();
            cb.setFontAndSize(bf, imphemo.getPreventiva());
            cb.setTextMatrix(imphemo.getEndodoncia(), alto - imphemo.getRayosx());
            if ("0".equals(HEMO.getSblancos())) {
                cb.showText("");
                cb.endText();
            } else {
                cb.showText(HEMO.getSblancos());
                cb.endText();
            }

            ///Coloca datos de plaquetas
            cb.beginText();
            cb.setFontAndSize(bf, imphemo.getSuma1());
            cb.setTextMatrix(imphemo.getPrimera(), alto - imphemo.getNumpieza());
            if ("0".equals(HEMO.getSplaquetas())) {
                cb.showText("");
                cb.endText();
            } else {
                cb.showText(HEMO.getSplaquetas());
                cb.endText();
            }

            ///Coloca datos de hemoglobina
            cb.beginText();
            cb.setFontAndSize(bf, imphemo.getSuma4());
            cb.setTextMatrix(imphemo.getSuma2(), alto - imphemo.getSuma3());
            if ("0".equals(HEMO.getShemoglo())) {
                cb.showText("");
                cb.endText();
            } else {
                cb.showText(HEMO.getShemoglo());
                cb.endText();
            }

            ///Coloca datos de hematocrito
            cb.beginText();
            cb.setFontAndSize(bf, imphemo.getSuma7());
            cb.setTextMatrix(imphemo.getSuma5(), alto - imphemo.getSuma6());
            if ("0".equals(HEMO.getShemato())) {
                cb.showText("");
                cb.endText();
            } else {
                cb.showText(HEMO.getShemato());
                cb.endText();
            }

            ///Coloca datos de VCM
            cb.beginText();
            cb.setFontAndSize(bf, imphemo.getViolencia());
            cb.setTextMatrix(imphemo.getFuma(), alto - imphemo.getAlcohol());
            if ("0".equals(HEMO.getSvcm())) {
                cb.showText("");
                cb.endText();
            } else {
                cb.showText(HEMO.getSvcm());
                cb.endText();
            }

            ///Coloca datos de HGM
            cb.beginText();
            cb.setFontAndSize(bf, imphemo.getSistemica());
            cb.setTextMatrix(imphemo.getAuto(), alto - imphemo.getUrinaria());
            if ("0".equals(HEMO.getShgm())) {
                cb.showText("");
                cb.endText();
            } else {
                cb.showText(HEMO.getShgm());
                cb.endText();
            }

            ///Coloca datos de CHCM
            cb.beginText();
            cb.setFontAndSize(bf, imphemo.getGlome());
            cb.setTextMatrix(imphemo.getArterial(), alto - imphemo.getDiabetes());
            if ("0".equals(HEMO.getSchcm())) {
                cb.showText("");
                cb.endText();
            } else {
                cb.showText(HEMO.getSchcm());
                cb.endText();
            }

            ///Coloca datos de BAS
            cb.beginText();
            cb.setFontAndSize(bf, imphemo.getLitiasis());
            cb.setTextMatrix(imphemo.getTracto(), alto - imphemo.getLupus());
            if ("0".equals(HEMO.getSbas())) {
                cb.showText("");
                cb.endText();
            } else {
                cb.showText(HEMO.getSbas());
                cb.endText();
            }

            ///Coloca datos de EOS
            cb.beginText();
            cb.setFontAndSize(bf, imphemo.getDisli());
            cb.setTextMatrix(imphemo.getNooplasia(), alto - imphemo.getNefro());
            if ("0".equals(HEMO.getSeos())) {
                cb.showText("");
                cb.endText();
            } else {
                cb.showText(HEMO.getSeos());
                cb.endText();
            }

            ///Coloca datos de MIELO
            cb.beginText();
            cb.setFontAndSize(bf, imphemo.getPrematuro());
            cb.setTextMatrix(imphemo.getFrenal(), alto - imphemo.getBajopeso());
            if ("0".equals(HEMO.getSmielo())) {
                cb.showText("");
                cb.endText();
            } else {
                cb.showText(HEMO.getSmielo());
                cb.endText();
            }

            ///Coloca datos de JUY
            cb.beginText();
            cb.setFontAndSize(bf, imphemo.getTuberculo());
            cb.setTextMatrix(imphemo.getEritro(), alto - imphemo.getRenal());
            if ("0".equals(HEMO.getSjuy())) {
                cb.showText("");
                cb.endText();
            } else {
                cb.showText(HEMO.getSjuy());
                cb.endText();
            }

            ///Coloca datos de CAY
            cb.beginText();
            cb.setFontAndSize(bf, imphemo.getReuma());
            cb.setTextMatrix(imphemo.getOtro(), alto - imphemo.getCardio());
            if ("0".equals(HEMO.getScay())) {
                cb.showText("");
                cb.endText();
            } else {
                cb.showText(HEMO.getScay());
                cb.endText();
            }

            ///Coloca datos de SEG
            cb.beginText();
            cb.setFontAndSize(bf, imphemo.getDepre());
            cb.setTextMatrix(imphemo.getCancer(), alto - imphemo.getCancero());
            if ("0".equals(HEMO.getSseg())) {
                cb.showText("");
                cb.endText();
            } else {
                cb.showText(HEMO.getSseg());
                cb.endText();
            }

            ///Coloca datos de LINF
            cb.beginText();
            cb.setFontAndSize(bf, imphemo.getDiscapa());
            cb.setTextMatrix(imphemo.getEpilepsia(), alto - imphemo.getPsico());
            if ("0".equals(HEMO.getSlinf())) {
                cb.showText("");
                cb.endText();
            } else {
                cb.showText(HEMO.getSlinf());
                cb.endText();
            }

            ///Coloca datos de MONO
            cb.beginText();
            cb.setFontAndSize(bf, imphemo.getInyectable());
            cb.setTextMatrix(imphemo.getOrientacion(), alto - imphemo.getDiu());
            if ("0".equals(HEMO.getSmono())) {
                cb.showText("");
                cb.endText();
            } else {
                cb.showText(HEMO.getSmono());
                cb.endText();
            }

            ///Coloca datos de Tiempo cuagulacion
            cb.beginText();
            cb.setFontAndSize(bf, imphemo.getCondon());
            cb.setTextMatrix(imphemo.getSueros(), alto - imphemo.getCuraciones());
            if ("0".equals(HEMO.getSangre())) {
                cb.showText("");
                cb.endText();
            } else {
                cb.showText(HEMO.getSangre());
                cb.endText();
            }

            ///Coloca datos de Tiempo de sangria
            cb.beginText();
            cb.setFontAndSize(bf, imphemo.getMnatural());
            cb.setTextMatrix(imphemo.getPildora(), alto - imphemo.getAqv());
            if ("0".equals(HEMO.getAspecto())) {
                cb.showText("");
                cb.endText();
            } else {
                cb.showText(HEMO.getAspecto());
                cb.endText();
            }

            ///Coloca datos de Protombina
            cb.beginText();
            cb.setFontAndSize(bf, imphemo.getTabletas());
            cb.setTextMatrix(imphemo.getInsumos(), alto - imphemo.getPap());
            if ("0".equals(HEMO.getCetonas())) {
                cb.showText("");
                cb.endText();
            } else {
                cb.showText(HEMO.getCetonas());
                cb.endText();
            }

            ///Coloca datos de Actividad
            cb.beginText();
            cb.setFontAndSize(bf, imphemo.getVitamina());
            cb.setTextMatrix(imphemo.getSfembarazada(), alto - imphemo.getSfpuerpera());
            if ("0".equals(HEMO.getReaccion())) {
                cb.showText("");
                cb.endText();
            } else {
                cb.showText(HEMO.getReaccion());
                cb.endText();
            }

            ///Coloca datos de Grupo Sanguineo
            cb.beginText();
            cb.setFontAndSize(bf, imphemo.getSexo());
            cb.setTextMatrix(imphemo.getSemanas(), alto - imphemo.getParto());
            if ("0".equals(HEMO.getColor())) {
                cb.showText("");
                cb.endText();
            } else {
                cb.showText("'" + HEMO.getColor() + "'");
                cb.endText();
            }

            ///Coloca datos de Factor RH
            cb.beginText();
            cb.setFontAndSize(bf, imphemo.getEclam());
            cb.setTextMatrix(imphemo.getImc(), alto - imphemo.getNembarazo());
            if ("0".equals(HEMO.getOlor())) {
                cb.showText("");
                cb.endText();
            } else {
                cb.showText(HEMO.getOlor());
                cb.endText();
            }

            ///Coloca datos Serie Roja
            cb.beginText();
            cb.setFontAndSize(bf, imphemo.getAnestesia());
            cb.setTextMatrix(imphemo.getAborto(), alto - imphemo.getHemo());
            if ("0".equals(HEMO.getSmroja())) {
                cb.showText("");
                cb.endText();
            } else {
                cb.showText(HEMO.getSmroja());
                cb.endText();
            }

            ///Coloca datos Serie Blanca
            cb.beginText();
            cb.setFontAndSize(bf, imphemo.getSeleccion());
            cb.setTextMatrix(imphemo.getControlpos(), alto - imphemo.getDesesti());
            if ("0".equals(HEMO.getSmblanca())) {
                cb.showText("");
                cb.endText();
            } else {
                cb.showText(HEMO.getSmblanca());
                cb.endText();
            }

            ///Coloca datos de Plaquetas
            cb.beginText();
            cb.setFontAndSize(bf, imphemo.getSeleccion());
            cb.setTextMatrix(imphemo.getIngreso(), alto - imphemo.getAnastecia());
            if ("0".equals(HEMO.getSmplaquetas())) {
                cb.showText("");
                cb.endText();
            } else {
                cb.showText(HEMO.getSmplaquetas());
                cb.endText();
            }

            ///Coloca datos de VES
            cb.beginText();
            cb.setFontAndSize(bf, imphemo.getDiasc());
            cb.setTextMatrix(imphemo.getEgreso(), alto - imphemo.getDiasi());
            if ("0".equals(HEMO.getSmves())) {
                cb.showText("");
                cb.endText();
            } else {
                cb.showText(HEMO.getSmves());
                cb.endText();
            }

            ///Coloca datos de Reticulositos
            cb.beginText();
            cb.setFontAndSize(bf, imphemo.getFallecidoy5());
            cb.setTextMatrix(imphemo.getFallecido(), alto - imphemo.getFallecidom5());
            if ("0".equals(HEMO.getSmreti())) {
                cb.showText("");
                cb.endText();
            } else {
                cb.showText(HEMO.getSmreti());
                cb.endText();
            }

            ///Coloca datos de Indice Reticulositos
            cb.beginText();
            cb.setFontAndSize(bf, imphemo.getSuma10());
            cb.setTextMatrix(imphemo.getSuma8(), alto - imphemo.getSuma9());
            if ("0".equals(HEMO.getSmindreti())) {
                cb.showText("");
                cb.endText();
            } else {
                cb.showText(HEMO.getSmindreti());
                cb.endText();
            }

            //Coloca datos de Otros
            if (HEMO.getSmotros().length() > 1) {
                String[] cadena = HEMO.getSmotros().split(";");
                for (int k = 0; k < cadena.length; k++) {
                    cb.beginText();
                    cb.setFontAndSize(bf, imphemo.getSuma10());
                    cb.setTextMatrix(imphemo.getSuma8(), alto - imphemo.getSuma9() + (k * 12));
                    cb.showText(cadena[cadena.length - 1 - k].trim());
                    cb.endText();
                }
            } else {
                cb.beginText();
                cb.setFontAndSize(bf, imphemo.getSuma10());
                cb.setTextMatrix(imphemo.getSuma8(), alto - imphemo.getSuma9());
                cb.showText(HEMO.getSmotros());
                cb.endText();
            }

            cb.stroke();

        } //Fin para la HEMOGRAMA

        if (orina.size() > 0 && "orinas".equals(accionl)) { //Esto es es para la orden ORINAS

            Cuadernos gral = (Cuadernos) datosgral.get(0);

            ///Coloca datos de departamento
            cb.beginText();
            cb.setFontAndSize(bf, 10);
            cb.setTextMatrix(gral.getExodoncia(), alto - gral.getPeriodoncia());
            cb.showText(dato.getDepartamento());
            cb.endText();
            ///Coloca datos de red
            cb.beginText();
            cb.setFontAndSize(bf, gral.getPreventiva());
            cb.setTextMatrix(gral.getEndodoncia(), alto - gral.getRayosx());
            cb.showText(dato.getRed());
            cb.endText();
            ///Coloca datos de municipio
            cb.beginText();
            cb.setFontAndSize(bf, gral.getSuma1());
            cb.setTextMatrix(gral.getPrimera(), alto - gral.getNumpieza());
            cb.showText(dato.getLocalidad());
            cb.endText();
            ///Coloca datos de consultorio
            cb.beginText();
            cb.setFontAndSize(bf, gral.getSuma4());
            cb.setTextMatrix(gral.getSuma2(), alto - gral.getSuma3());
            cb.showText(datoMed.getConsultorio());
            cb.endText();
            ///Coloca datos de fechareporte
            Cuadernos datoLabo = (Cuadernos) ldatoLab.get(0);
            cb.beginText();
            cb.setFontAndSize(bf, gral.getSuma7());
            cb.setTextMatrix(gral.getSuma5(), alto - gral.getSuma6());
            cb.showText(format(datoLabo.getFechae(), "dd/MM/yyyy"));
            cb.endText();
            ///Coloca datos de numero registro
            cb.beginText();
            cb.setFontAndSize(bf, gral.getViolencia());
            cb.setTextMatrix(gral.getFuma(), alto - gral.getAlcohol());
            cb.showText(Integer.toString(datoLabo.getId_cuaderno()));
            cb.endText();
            ///Coloca datos de HCL
            cb.beginText();
            cb.setFontAndSize(bf, gral.getSistemica());
            cb.setTextMatrix(gral.getAuto(), alto - gral.getUrinaria());
            cb.showText(Integer.toString(datoPaciente.getHcl()));
            cb.endText();
            ///Coloca datos de Nombe paciente
            cb.beginText();
            cb.setFontAndSize(bf, gral.getGlome());
            cb.setTextMatrix(gral.getArterial(), alto - gral.getDiabetes());
            cb.showText(datoPaciente.getNombres());
            cb.endText();
            ///Coloca datos de HCL
            cb.beginText();
            cb.setFontAndSize(bf, gral.getLitiasis());
            cb.setTextMatrix(gral.getTracto(), alto - gral.getLupus());
            cb.showText(Integer.toString(datoHis.getEdad()));
            cb.endText();
            ///Coloca datos de Sexo Paciente
            if (datoPaciente.getId_tipo_sexo() == 1) {
                cb.beginText();
                cb.setFontAndSize(bf, gral.getDisli());
                cb.setTextMatrix(gral.getNooplasia(), alto - gral.getNefro());
                cb.showText("Femenino");
                cb.endText();
            } else {
                cb.beginText();
                cb.setFontAndSize(bf, gral.getDisli());
                cb.setTextMatrix(gral.getNooplasia(), alto - gral.getNefro());
                cb.showText("Masculino");
                cb.endText();
            }
            ///Coloca datos de Nombre Establecimiento
            cb.beginText();
            cb.setFontAndSize(bf, gral.getPrematuro());
            cb.setTextMatrix(gral.getFrenal(), alto - gral.getBajopeso());
            cb.showText(lista.getNombre());
            cb.endText();
            ///Coloca datos de Tipo Seguro
            if ("S".equals(datoPaciente.getId_estado())) {
                cb.beginText();
                cb.setFontAndSize(bf, gral.getTuberculo());
                cb.setTextMatrix(gral.getEritro(), alto - gral.getRenal());
                cb.showText("SPS(SUMI)");
                cb.endText();
            } else {
                if ("E".equals(datoPaciente.getId_estado())) {
                    cb.beginText();
                    cb.setFontAndSize(bf, gral.getTuberculo());
                    cb.setTextMatrix(gral.getEritro(), alto - gral.getRenal());
                    cb.showText("Particular");
                    cb.endText();
                }
                cb.beginText();
                cb.setFontAndSize(bf, gral.getTuberculo());
                cb.setTextMatrix(gral.getEritro(), alto - gral.getRenal());
                cb.showText("Asegurado");
                cb.endText();
            }
            ///Coloca datos de Nombre Medico
            cb.beginText();
            cb.setFontAndSize(bf, gral.getReuma());
            cb.setTextMatrix(gral.getOtro(), alto - gral.getCardio());
            cb.showText(lista.getNombres());
            cb.endText();
            ///Coloca datos de nombre de Laboratorio
            cb.beginText();
            cb.setFontAndSize(bf, gral.getSuma13());
            cb.setTextMatrix(gral.getSuma11(), alto - gral.getSuma12());
            cb.showText("Examen de Orina");
            cb.endText();

            cb.beginText();
            cb.setFontAndSize(bf, gral.getSuma16());
            cb.setTextMatrix(gral.getSuma14(), alto - gral.getSuma15());
            cb.showText(Integer.toString(lista.getNumpieza()));
            cb.endText();

            ////llena los datos de el orinas
            Cuadernos impori = (Cuadernos) datosImpOrin.get(0); //datos impresion Hemograma
            Cuadernos ORINA = (Cuadernos) orina.get(0);
            ///Coloca datos de globulos cantidad volumen
            cb.beginText();
            cb.setFontAndSize(bf, impori.getCirugia());
            cb.setTextMatrix(impori.getExodoncia(), alto - impori.getPeriodoncia());
            cb.showText(ORINA.getCantidad());
            cb.endText();
            ///Coloca datos de Color
            cb.beginText();
            cb.setFontAndSize(bf, impori.getPreventiva());
            cb.setTextMatrix(impori.getEndodoncia(), alto - impori.getRayosx());
            cb.showText(ORINA.getColor());
            cb.endText();
            ///Coloca datos de Olor
            cb.beginText();
            cb.setFontAndSize(bf, impori.getSuma1());
            cb.setTextMatrix(impori.getPrimera(), alto - impori.getNumpieza());
            cb.showText(ORINA.getOlor());
            cb.endText();
            ///Coloca datos de Aspecto
            cb.beginText();
            cb.setFontAndSize(bf, impori.getSuma4());
            cb.setTextMatrix(impori.getSuma2(), alto - impori.getSuma3());
            cb.showText(ORINA.getAspecto());
            cb.endText();
            ///Coloca datos de Raccion
            cb.beginText();
            cb.setFontAndSize(bf, impori.getSuma7());
            cb.setTextMatrix(impori.getSuma5(), alto - impori.getSuma6());
            cb.showText(ORINA.getReaccion());
            cb.endText();
            ///Coloca datos de Densidad
            cb.beginText();
            cb.setFontAndSize(bf, impori.getSuma10());
            cb.setTextMatrix(impori.getSuma8(), alto - impori.getSuma9());
            cb.showText(ORINA.getDensidad());
            cb.endText();
            ///Coloca datos de Espuma
            cb.beginText();
            cb.setFontAndSize(bf, impori.getViolencia());
            cb.setTextMatrix(impori.getFuma(), alto - impori.getAlcohol());
            cb.showText(ORINA.getEspuma());
            cb.endText();
            ///Coloca datos de Sedimento Deposito
            cb.beginText();
            cb.setFontAndSize(bf, impori.getSistemica());
            cb.setTextMatrix(impori.getAuto(), alto - impori.getUrinaria());
            cb.showText(ORINA.getSedimento());
            cb.endText();

            ///Coloca datos de Nitritos
            cb.beginText();
            cb.setFontAndSize(bf, impori.getSexo());
            cb.setTextMatrix(impori.getSemanas(), alto - impori.getParto());
            cb.showText(ORINA.getNitritos());
            cb.endText();
            ///Coloca datos de Glucosa
            cb.beginText();
            cb.setFontAndSize(bf, impori.getEclam());
            cb.setTextMatrix(impori.getImc(), alto - impori.getNembarazo());
            cb.showText(ORINA.getGlucosa());
            cb.endText();
            ///Coloca datos de Sangre
            cb.beginText();
            cb.setFontAndSize(bf, impori.getInyectable());
            cb.setTextMatrix(impori.getOrientacion(), alto - impori.getDiu());
            cb.showText(ORINA.getSangre());
            cb.endText();
            ///Coloca datos de Cetonas
            cb.beginText();
            cb.setFontAndSize(bf, impori.getDisli());
            cb.setTextMatrix(impori.getNooplasia(), alto - impori.getNefro());
            cb.showText(ORINA.getCetonas());
            cb.endText();
            ///Coloca datos de Bilirrubina
            cb.beginText();
            cb.setFontAndSize(bf, impori.getControlpos());
            cb.setTextMatrix(impori.getHemo(), alto - impori.getAnestesia());
            cb.showText(ORINA.getBilirrubina());
            cb.endText();
            ///Coloca datos de Urabilinogeno
            cb.beginText();
            cb.setFontAndSize(bf, impori.getCondon());
            cb.setTextMatrix(impori.getSueros(), alto - impori.getCuraciones());
            cb.showText(ORINA.getUrabilinogeno());
            cb.endText();
            ///Coloca datos de albumina
            cb.beginText();
            cb.setFontAndSize(bf, impori.getLitiasis());
            cb.setTextMatrix(impori.getTracto(), alto - impori.getLupus());
            cb.showText(ORINA.getSmono());
            cb.endText();
            ///Coloca datos de Pigmentos Biliares
            cb.beginText();
            cb.setFontAndSize(bf, impori.getReuma());
            cb.setTextMatrix(impori.getOtro(), alto - impori.getCardio());
            cb.showText(ORINA.getSmroja());
            cb.endText();
            ///Coloca datos de Sales Biliares
            cb.beginText();
            cb.setFontAndSize(bf, impori.getTuberculo());
            cb.setTextMatrix(impori.getEritro(), alto - impori.getRenal());
            cb.showText(ORINA.getSmreti());
            cb.endText();
            ///Coloca datos de Proteinas
            cb.beginText();
            cb.setFontAndSize(bf, impori.getServicio());
            cb.setTextMatrix(impori.getIngreso(), alto - impori.getAnastecia());
            cb.showText(ORINA.getProteinas());
            cb.endText();
            ///Coloca datos de Acido Diecetico
            cb.beginText();
            cb.setFontAndSize(bf, impori.getPrematuro());
            cb.setTextMatrix(impori.getFrenal(), alto - impori.getBajopeso());
            cb.showText(ORINA.getSmblanca());
            cb.endText();
            //Analisis microscopico////////////////////////////
            ///Coloca datos de Cel Epiteliales
            cb.beginText();
            cb.setFontAndSize(bf, impori.getDiscapa());
            cb.setTextMatrix(impori.getEpilepsia(), alto - impori.getPsico());
            if ("0".equals(ORINA.getEpiteliales())) {
                cb.showText("");
                cb.endText();
            } else {
                cb.showText(ORINA.getEpiteliales());
                cb.endText();
            }

            ///Coloca datos de Leucocito
            cb.beginText();
            cb.setFontAndSize(bf, impori.getDepre());
            cb.setTextMatrix(impori.getCancer(), alto - impori.getCancero());
            if ("0".equals(ORINA.getLeucocitos())) {
                cb.showText("");
                cb.endText();
            } else {
                cb.showText(ORINA.getLeucocitos());
                cb.endText();
            }

            ///Coloca datos de Hematies
            cb.beginText();
            cb.setFontAndSize(bf, impori.getMnatural());
            cb.setTextMatrix(impori.getPildora(), alto - impori.getAqv());
            if ("0".equals(ORINA.getEmaties())) {
                cb.showText("");
                cb.endText();
            } else {
                cb.showText(ORINA.getEmaties());
                cb.endText();
            }

            ///Coloca datos de Piocito
            cb.beginText();
            cb.setFontAndSize(bf, impori.getTabletas());
            cb.setTextMatrix(impori.getInsumos(), alto - impori.getPap());
            if ("0".equals(ORINA.getPiocitos())) {
                cb.showText("");
                cb.endText();
            } else {
                cb.showText(ORINA.getPiocitos());
                cb.endText();
            }

            ///Coloca datos de Bacteria
            cb.beginText();
            cb.setFontAndSize(bf, impori.getVitamina());
            cb.setTextMatrix(impori.getSfembarazada(), alto - impori.getSfpuerpera());
            if ("0".equals(ORINA.getBacterias())) {
                cb.showText("");
                cb.endText();
            } else {
                cb.showText(ORINA.getBacterias());
                cb.endText();
            }

            ///Coloca datos de Cilindros
            cb.beginText();
            cb.setFontAndSize(bf, impori.getSuma13());
            cb.setTextMatrix(impori.getSuma11(), alto - impori.getSuma12());
            if ("0".equals(ORINA.getCilindros())) {
                cb.showText("");
                cb.endText();
            } else {
                cb.showText(ORINA.getCilindros());
                cb.endText();
            }

            ///Coloca datos de Granulosos
            cb.beginText();
            cb.setFontAndSize(bf, impori.getSuma16());
            cb.setTextMatrix(impori.getSuma14(), alto - impori.getSuma15());
            if ("0".equals(ORINA.getGranulosos())) {
                cb.showText("");
                cb.endText();
            } else {
                cb.showText(ORINA.getGranulosos());
                cb.endText();
            }

            ///Coloca datos de Hilianos
            cb.beginText();
            cb.setFontAndSize(bf, impori.getSuma19());
            cb.setTextMatrix(impori.getSuma17(), alto - impori.getSuma18());
            if ("0".equals(ORINA.getHialianos())) {
                cb.showText("");
                cb.endText();
            } else {
                cb.showText(ORINA.getHialianos());
                cb.endText();
            }

            ///Coloca datos de Leucocitarios
            cb.beginText();
            cb.setFontAndSize(bf, impori.getSuma22());
            cb.setTextMatrix(impori.getSuma20(), alto - impori.getSuma21());
            if ("0".equals(ORINA.getLeucocitarios())) {
                cb.showText("");
                cb.endText();
            } else {
                cb.showText(ORINA.getLeucocitarios());
                cb.endText();
            }

            ///Coloca datos de Cristales
            cb.beginText();
            cb.setFontAndSize(bf, impori.getSuma25());
            cb.setTextMatrix(impori.getSuma23(), alto - impori.getSuma24());
            if ("0".equals(ORINA.getCristales())) {
                cb.showText("");
                cb.endText();
            } else {
                cb.showText(ORINA.getCristales());
                cb.endText();
            }

            ///Coloca datos de Otros1
            cb.beginText();
            cb.setFontAndSize(bf, impori.getSuma28());
            cb.setTextMatrix(impori.getSuma26(), alto - impori.getSuma27());
            if ("0".equals(ORINA.getSmotros())) {
                cb.showText("");
                cb.endText();
            } else {
                cb.showText(ORINA.getSmotros());
                cb.endText();
            }

            ///Coloca datos de Otros2
            cb.beginText();
            cb.setFontAndSize(bf, impori.getSuma31());
            cb.setTextMatrix(impori.getSuma29(), alto - impori.getSuma30());
            if ("0".equals(ORINA.getObservaciones())) {
                cb.showText("");
                cb.endText();
            } else {
                cb.showText(ORINA.getObservaciones());
                cb.endText();
            }

            cb.stroke();

        } //Fin para la orden ORINAS

        if ("serologia".equals(accionl)) { //Esto es es para la orden SEROLOGIA

            Cuadernos gral = (Cuadernos) datosgral.get(0);

            ///Coloca datos de departamento
            cb.beginText();
            cb.setFontAndSize(bf, gral.getCirugia());
            cb.setTextMatrix(gral.getExodoncia(), alto - gral.getPeriodoncia());
            cb.showText(dato.getDepartamento());
            cb.endText();
            ///Coloca datos de red
            cb.beginText();
            cb.setFontAndSize(bf, gral.getPreventiva());
            cb.setTextMatrix(gral.getEndodoncia(), alto - gral.getRayosx());
            cb.showText(dato.getRed());
            cb.endText();
            ///Coloca datos de municipio
            cb.beginText();
            cb.setFontAndSize(bf, gral.getSuma1());
            cb.setTextMatrix(gral.getPrimera(), alto - gral.getNumpieza());
            cb.showText(dato.getLocalidad());
            cb.endText();
            ///Coloca datos de consultorio
            cb.beginText();
            cb.setFontAndSize(bf, gral.getSuma4());
            cb.setTextMatrix(gral.getSuma2(), alto - gral.getSuma3());
            cb.showText(datoMed.getConsultorio());
            cb.endText();
            ///Coloca datos de fechareporte
            Cuadernos datoLabo = (Cuadernos) ldatoLab.get(0);
            cb.beginText();
            cb.setFontAndSize(bf, gral.getSuma7());
            cb.setTextMatrix(gral.getSuma5(), alto - gral.getSuma6());
            cb.showText(format(datoLabo.getFechae(), "dd/MM/yyyy"));
            cb.endText();
            ///Coloca datos de numero registro
            cb.beginText();
            cb.setFontAndSize(bf, gral.getViolencia());
            cb.setTextMatrix(gral.getFuma(), alto - gral.getAlcohol());
            cb.showText(Integer.toString(datoLabo.getId_cuaderno()));
            cb.endText();
            ///Coloca datos de HCL
            cb.beginText();
            cb.setFontAndSize(bf, gral.getSistemica());
            cb.setTextMatrix(gral.getAuto(), alto - gral.getUrinaria());
            cb.showText(Integer.toString(datoPaciente.getHcl()));
            cb.endText();
            ///Coloca datos de Nombe paciente
            cb.beginText();
            cb.setFontAndSize(bf, gral.getGlome());
            cb.setTextMatrix(gral.getArterial(), alto - gral.getDiabetes());
            cb.showText(datoPaciente.getNombres());
            cb.endText();
            ///Coloca datos de HCL
            cb.beginText();
            cb.setFontAndSize(bf, gral.getLitiasis());
            cb.setTextMatrix(gral.getTracto(), alto - gral.getLupus());
            cb.showText(Integer.toString(datoHis.getEdad()));
            cb.endText();
            ///Coloca datos de Sexo Paciente
            if (datoPaciente.getId_tipo_sexo() == 1) {
                cb.beginText();
                cb.setFontAndSize(bf, gral.getDisli());
                cb.setTextMatrix(gral.getNooplasia(), alto - gral.getNefro());
                cb.showText("Femenino");
                cb.endText();
            } else {
                cb.beginText();
                cb.setFontAndSize(bf, gral.getDisli());
                cb.setTextMatrix(gral.getNooplasia(), alto - gral.getNefro());
                cb.showText("Masculino");
                cb.endText();
            }
            ///Coloca datos de Nombre Establecimiento
            cb.beginText();
            cb.setFontAndSize(bf, gral.getPrematuro());
            cb.setTextMatrix(gral.getFrenal(), alto - gral.getBajopeso());
            cb.showText(lista.getNombre());
            cb.endText();
            ///Coloca datos de Tipo Seguro
            if ("S".equals(datoPaciente.getId_estado())) {
                cb.beginText();
                cb.setFontAndSize(bf, gral.getTuberculo());
                cb.setTextMatrix(gral.getEritro(), alto - gral.getRenal());
                cb.showText("SPS(SUMI)");
                cb.endText();
            } else {
                if ("E".equals(datoPaciente.getId_estado())) {
                    cb.beginText();
                    cb.setFontAndSize(bf, gral.getTuberculo());
                    cb.setTextMatrix(gral.getEritro(), alto - gral.getRenal());
                    cb.showText("Particular");
                    cb.endText();
                }
                cb.beginText();
                cb.setFontAndSize(bf, gral.getTuberculo());
                cb.setTextMatrix(gral.getEritro(), alto - gral.getRenal());
                cb.showText("Asegurado");
                cb.endText();
            }
            ///Coloca datos de Nombre Medico
            cb.beginText();
            cb.setFontAndSize(bf, gral.getReuma());
            cb.setTextMatrix(gral.getOtro(), alto - gral.getCardio());
            cb.showText(lista.getNombres());
            cb.endText();

            cb.beginText();
            cb.setFontAndSize(bf, gral.getSuma16());
            cb.setTextMatrix(gral.getSuma14(), alto - gral.getSuma15());
            cb.showText(Integer.toString(lista.getNumpieza()));
            cb.endText();

            //////////////////// laboratorioss datos de general serologia
            for (int i = 0; i < ldatoLab.size(); i++) {
                Cuadernos datoLab1 = (Cuadernos) ldatoLab.get(i);
                if (datoLab1.getResultado() == null || "null".equals(datoLab1.getResultado())) {
                    datoLab1.setResultado("");
                } else {
                    datoLab1.setResultado(datoLab1.getResultado().replaceAll("<p>", " "));
                    datoLab1.setResultado(datoLab1.getResultado().replaceAll("</p>", " "));
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
                String normal = "";
                Cuadernos impotro = (Cuadernos) datosImpOtro.get(0); //datos impresion Otros
                switch (datoLab1.getId_laboratorio()) {
                    case 112: ///Coloca datos de proteina C reactiva
                        cb.beginText();
                        cb.setFontAndSize(bf, impotro.getCirugia());
                        cb.setTextMatrix(impotro.getExodoncia(), alto - (impotro.getPeriodoncia()));
                        cb.showText(datoLab1.getResultado());
                        cb.endText();
                        break;
                    case 123: ///Coloca datos de factor reumatoide
                        cb.beginText();
                        cb.setFontAndSize(bf, impotro.getPreventiva());
                        cb.setTextMatrix(impotro.getEndodoncia(), alto - (impotro.getRayosx()));
                        cb.showText(datoLab1.getResultado());
                        cb.endText();
                        break;
                    case 153: ///Coloca datos de ASTO
                        cb.beginText();
                        cb.setFontAndSize(bf, impotro.getSuma3());
                        cb.setTextMatrix(impotro.getSuma1(), alto - (impotro.getSuma2()));
                        cb.showText(datoLab1.getResultado());
                        cb.endText();
                        break;
                    case 132: ///Coloca datos de Widal
                        cb.beginText();
                        cb.setFontAndSize(bf, impotro.getSuma6());
                        cb.setTextMatrix(impotro.getSuma4(), alto - (impotro.getSuma5()));
                        cb.showText(datoLab1.getResultado());
                        cb.endText();
                        break;
                    case 134: ///Coloca datos de RPR - para sifilis VDRL 
                        cb.beginText();
                        cb.setFontAndSize(bf, impotro.getSuma9());
                        cb.setTextMatrix(impotro.getSuma7(), alto - (impotro.getSuma8()));
                        cb.showText(datoLab1.getResultado());
                        cb.endText();
                        break;
                    case 154: ///Coloca datos de Prueba rapida para VIH/SIDA
                        cb.beginText();
                        cb.setFontAndSize(bf, impotro.getSuma12());
                        cb.setTextMatrix(impotro.getSuma10(), alto - (impotro.getSuma11()));
                        cb.showText(datoLab1.getResultado());
                        cb.endText();
                        break;
                    case 157: ///Coloca datos de H Pylon
                        cb.beginText();
                        cb.setFontAndSize(bf, impotro.getSuma15());
                        cb.setTextMatrix(impotro.getSuma13(), alto - (impotro.getSuma14()));
                        cb.showText(datoLab1.getLaboratorio() + " :   " + datoLab1.getResultado());
                        cb.endText();
                        break;
                    case 221: ///Coloca datos de Hepatitis A
                        cb.beginText();
                        cb.setFontAndSize(bf, impotro.getSuma18());
                        cb.setTextMatrix(impotro.getSuma16(), alto - (impotro.getSuma17()));
                        cb.showText(datoLab1.getLaboratorio() + " :   " + datoLab1.getResultado());
                        cb.endText();
                        break;
                    case 222: ///Coloca datos de Hepatitis B
                        cb.beginText();
                        cb.setFontAndSize(bf, impotro.getSuma21());
                        cb.setTextMatrix(impotro.getSuma19(), alto - (impotro.getSuma20()));
                        cb.showText(datoLab1.getLaboratorio() + " :   " + datoLab1.getResultado());
                        cb.endText();
                        break;
                    case 223: ///Coloca datos de Hepatitis C
                        cb.beginText();
                        cb.setFontAndSize(bf, impotro.getSuma24());
                        cb.setTextMatrix(impotro.getSuma22(), alto - (impotro.getSuma23()));
                        cb.showText(datoLab1.getLaboratorio() + " :   " + datoLab1.getResultado());
                        cb.endText();
                        break;
                    case 224: ///Coloca datos de PSA
                        cb.beginText();
                        cb.setFontAndSize(bf, impotro.getSuma27());
                        cb.setTextMatrix(impotro.getSuma25(), alto - (impotro.getSuma26()));
                        cb.showText(datoLab1.getLaboratorio() + " :   " + datoLab1.getResultado());
                        cb.endText();
                        break;
                    case 225: ///Coloca datos de Dosificacion DGC
                        cb.beginText();
                        cb.setFontAndSize(bf, impotro.getSuma30());
                        cb.setTextMatrix(impotro.getSuma28(), alto - (impotro.getSuma29()));
                        cb.showText(datoLab1.getLaboratorio() + " :   " + datoLab1.getResultado());
                        cb.endText();
                        break;
                    case 226: ///Coloca datos de Troponina
                        cb.beginText();
                        cb.setFontAndSize(bf, impotro.getSuma33());
                        cb.setTextMatrix(impotro.getSuma31(), alto - (impotro.getSuma32()));
                        cb.showText(datoLab1.getLaboratorio() + " :   " + datoLab1.getResultado());
                        cb.endText();
                        break;
                    case 227: ///Coloca datos de Elisa Chagas
                        cb.beginText();
                        cb.setFontAndSize(bf, impotro.getSuma36());
                        cb.setTextMatrix(impotro.getSuma34(), alto - (impotro.getSuma35()));
                        cb.showText(datoLab1.getLaboratorio() + " :   " + datoLab1.getResultado());
                        cb.endText();
                        break;
                    case 228: ///Coloca datos de Elisa toxo
                        cb.beginText();
                        cb.setFontAndSize(bf, impotro.getSuma39());
                        cb.setTextMatrix(impotro.getSuma37(), alto - (impotro.getSuma38()));
                        cb.showText(datoLab1.getLaboratorio() + " :   " + datoLab1.getResultado());
                        cb.endText();
                        break;
                    case 229: ///Coloca datos de ELISA Cisticir
                        cb.beginText();
                        cb.setFontAndSize(bf, impotro.getSuma42());
                        cb.setTextMatrix(impotro.getSuma40(), alto - (impotro.getSuma41()));
                        cb.showText(datoLab1.getLaboratorio() + " :   " + datoLab1.getResultado());
                        cb.endText();
                        break;
                    case 234: ///Coloca datos de T3
                        cb.beginText();
                        cb.setFontAndSize(bf, impotro.getFuma());
                        cb.setTextMatrix(impotro.getPrimera(), alto - (impotro.getNumpieza()));
                        cb.showText(datoLab1.getLaboratorio() + " :   " + datoLab1.getResultado());
                        cb.endText();
                        break;
                    case 235: ///Coloca datos de T4
                        cb.beginText();
                        cb.setFontAndSize(bf, impotro.getAuto());
                        cb.setTextMatrix(impotro.getAlcohol(), alto - (impotro.getViolencia()));
                        cb.showText(datoLab1.getLaboratorio() + " :   " + datoLab1.getResultado());
                        cb.endText();
                        break;
                    case 236: ///Coloca datos de T4 Libre
                        cb.beginText();
                        cb.setFontAndSize(bf, impotro.getSemanas());
                        cb.setTextMatrix(impotro.getUrinaria(), alto - (impotro.getSistemica()));
                        cb.showText(datoLab1.getLaboratorio() + " :   " + datoLab1.getResultado());
                        cb.endText();
                        break;
                    case 237: ///Coloca datos de TSH
                        cb.beginText();
                        cb.setFontAndSize(bf, impotro.getImc());
                        cb.setTextMatrix(impotro.getParto(), alto - (impotro.getSexo()));
                        cb.showText(datoLab1.getLaboratorio() + " :   " + datoLab1.getResultado());
                        cb.endText();
                        break;
                }
            }   //fin de impresion laboraotrio Serologia
        }

        if ("qsangre".equals(accionl)) { //Esto es es para la orden QUIMICA SANGUINEA 

            Cuadernos gral = (Cuadernos) datosgral.get(0);

            ///Coloca datos de departamento
            cb.beginText();
            cb.setFontAndSize(bf, gral.getCirugia());
            cb.setTextMatrix(gral.getExodoncia(), alto - gral.getPeriodoncia());
            cb.showText(dato.getDepartamento());
            cb.endText();
            ///Coloca datos de red
            cb.beginText();
            cb.setFontAndSize(bf, gral.getPreventiva());
            cb.setTextMatrix(gral.getEndodoncia(), alto - gral.getRayosx());
            cb.showText(dato.getRed());
            cb.endText();
            ///Coloca datos de municipio
            cb.beginText();
            cb.setFontAndSize(bf, gral.getSuma1());
            cb.setTextMatrix(gral.getPrimera(), alto - gral.getNumpieza());
            cb.showText(dato.getLocalidad());
            cb.endText();
            ///Coloca datos de consultorio
            cb.beginText();
            cb.setFontAndSize(bf, gral.getSuma4());
            cb.setTextMatrix(gral.getSuma2(), alto - gral.getSuma3());
            cb.showText(datoMed.getConsultorio());
            cb.endText();
            ///Coloca datos de fechareporte
            Cuadernos datoLabo = (Cuadernos) ldatoLab.get(0);
            cb.beginText();
            cb.setFontAndSize(bf, gral.getSuma7());
            cb.setTextMatrix(gral.getSuma5(), alto - gral.getSuma6());
            cb.showText(format(datoLabo.getFechae(), "dd/MM/yyyy"));
            cb.endText();
            ///Coloca datos de numero registro
            cb.beginText();
            cb.setFontAndSize(bf, gral.getViolencia());
            cb.setTextMatrix(gral.getFuma(), alto - gral.getAlcohol());
            cb.showText(Integer.toString(datoLabo.getId_cuaderno()));
            cb.endText();
            ///Coloca datos de HCL
            cb.beginText();
            cb.setFontAndSize(bf, gral.getSistemica());
            cb.setTextMatrix(gral.getAuto(), alto - gral.getUrinaria());
            cb.showText(Integer.toString(datoPaciente.getHcl()));
            cb.endText();
            ///Coloca datos de Nombe paciente
            cb.beginText();
            cb.setFontAndSize(bf, gral.getGlome());
            cb.setTextMatrix(gral.getArterial(), alto - gral.getDiabetes());
            cb.showText(datoPaciente.getNombres());
            cb.endText();
            ///Coloca datos de HCL
            cb.beginText();
            cb.setFontAndSize(bf, gral.getLitiasis());
            cb.setTextMatrix(gral.getTracto(), alto - gral.getLupus());
            cb.showText(Integer.toString(datoHis.getEdad()));
            cb.endText();
            ///Coloca datos de Sexo Paciente
            if (datoPaciente.getId_tipo_sexo() == 1) {
                cb.beginText();
                cb.setFontAndSize(bf, gral.getDisli());
                cb.setTextMatrix(gral.getNooplasia(), alto - gral.getNefro());
                cb.showText("Femenino");
                cb.endText();
            } else {
                cb.beginText();
                cb.setFontAndSize(bf, gral.getDisli());
                cb.setTextMatrix(gral.getNooplasia(), alto - gral.getNefro());
                cb.showText("Masculino");
                cb.endText();
            }
            ///Coloca datos de Nombre Establecimiento
            cb.beginText();
            cb.setFontAndSize(bf, gral.getPrematuro());
            cb.setTextMatrix(gral.getFrenal(), alto - gral.getBajopeso());
            cb.showText(lista.getNombre());
            cb.endText();
            ///Coloca datos de Tipo Seguro
            if ("S".equals(datoPaciente.getId_estado())) {
                cb.beginText();
                cb.setFontAndSize(bf, gral.getTuberculo());
                cb.setTextMatrix(gral.getEritro(), alto - gral.getRenal());
                cb.showText("SPS(SUMI)");
                cb.endText();
            } else {
                if ("E".equals(datoPaciente.getId_estado())) {
                    cb.beginText();
                    cb.setFontAndSize(bf, gral.getTuberculo());
                    cb.setTextMatrix(gral.getEritro(), alto - gral.getRenal());
                    cb.showText("Particular");
                    cb.endText();
                }
                cb.beginText();
                cb.setFontAndSize(bf, gral.getTuberculo());
                cb.setTextMatrix(gral.getEritro(), alto - gral.getRenal());
                cb.showText("Asegurado");
                cb.endText();
            }
            ///Coloca datos de Nombre Medico
            cb.beginText();
            cb.setFontAndSize(bf, gral.getReuma());
            cb.setTextMatrix(gral.getOtro(), alto - gral.getCardio());
            cb.showText(lista.getNombres());
            cb.endText();

            cb.beginText();
            cb.setFontAndSize(bf, gral.getSuma16());
            cb.setTextMatrix(gral.getSuma14(), alto - gral.getSuma15());
            cb.showText(Integer.toString(lista.getNumpieza()));
            cb.endText();

            // laboratorioss datos de general quimicas
            for (int i = 0; i < ldatoLab.size(); i++) {
                Cuadernos datoLab1 = (Cuadernos) ldatoLab.get(i);
                if (datoLab1.getResultado() == null || "null".equals(datoLab1.getResultado())) {
                    datoLab1.setResultado("");
                } else {
                    datoLab1.setResultado(datoLab1.getResultado().replaceAll("<p>", " "));
                    datoLab1.setResultado(datoLab1.getResultado().replaceAll("</p>", " "));
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
                String normal = "";
                Cuadernos impotro = (Cuadernos) datosImpOtro.get(0); //datos impresion Otros
                switch (datoLab1.getId_laboratorio()) {
                    case 141: ///Coloca datos de acido urico
                        cb.beginText();
                        cb.setFontAndSize(bf, impotro.getCirugia());
                        cb.setTextMatrix(impotro.getExodoncia(), alto - (impotro.getPeriodoncia()));
                        cb.showText(datoLab1.getResultado());
                        cb.endText();
                        break;
                    case 147: ///Coloca datos de Albumina
                        cb.beginText();
                        cb.setFontAndSize(bf, impotro.getPreventiva());
                        cb.setTextMatrix(impotro.getEndodoncia(), alto - (impotro.getRayosx()));
                        cb.showText(datoLab1.getResultado());
                        cb.endText();
                        break;
                    case 163: ///Coloca datos de Amilasa
                        cb.beginText();
                        cb.setFontAndSize(bf, impotro.getSuma3());
                        cb.setTextMatrix(impotro.getSuma1(), alto - (impotro.getSuma2()));
                        cb.showText(datoLab1.getResultado());
                        cb.endText();
                        break;
                    case 200: ///Coloca datos de Bilirrubina directa
                        cb.beginText();
                        cb.setFontAndSize(bf, impotro.getSuma6());
                        cb.setTextMatrix(impotro.getSuma4(), alto - (impotro.getSuma5()));
                        cb.showText(datoLab1.getResultado());
                        cb.endText();
                        break;
                    case 201: ///Coloca datos de Bilirrubina Indirecta
                        cb.beginText();
                        cb.setFontAndSize(bf, impotro.getSuma9());
                        cb.setTextMatrix(impotro.getSuma7(), alto - (impotro.getSuma8()));
                        cb.showText(datoLab1.getResultado());
                        cb.endText();
                        break;
                    case 109: ///Coloca datos de Bilirrubina Totales
                        cb.beginText();
                        cb.setFontAndSize(bf, impotro.getSuma12());
                        cb.setTextMatrix(impotro.getSuma10(), alto - (impotro.getSuma11()));
                        cb.showText(datoLab1.getResultado());
                        cb.endText();
                        break;
                    case 164: ///Coloca datos de Calcio
                        cb.beginText();
                        cb.setFontAndSize(bf, impotro.getSuma15());
                        cb.setTextMatrix(impotro.getSuma13(), alto - (impotro.getSuma14()));
                        cb.showText(datoLab1.getResultado());
                        cb.endText();
                        break;
                    case 148: ///Coloca datos de Colesterol
                        cb.beginText();
                        cb.setFontAndSize(bf, impotro.getSuma18());
                        cb.setTextMatrix(impotro.getSuma16(), alto - (impotro.getSuma17()));
                        cb.showText(datoLab1.getResultado());
                        cb.endText();
                        break;
                    case 202: ///Coloca datos de Creatina
                        cb.beginText();
                        cb.setFontAndSize(bf, impotro.getSuma21());
                        cb.setTextMatrix(impotro.getSuma19(), alto - (impotro.getSuma20()));
                        cb.showText(datoLab1.getResultado());
                        cb.endText();
                        break;
                    case 125: ///Coloca datos de Fosfatasa Acida
                        cb.beginText();
                        cb.setFontAndSize(bf, impotro.getSuma24());
                        cb.setTextMatrix(impotro.getSuma22(), alto - (impotro.getSuma23()));
                        cb.showText(datoLab1.getResultado());
                        cb.endText();
                        break;
                    case 203: ///Coloca datos de Fosfatasa Alcalina
                        cb.beginText();
                        cb.setFontAndSize(bf, impotro.getSuma27());
                        cb.setTextMatrix(impotro.getSuma25(), alto - (impotro.getSuma26()));
                        cb.showText(datoLab1.getResultado());
                        cb.endText();
                        break;
                    case 204: ///Coloca datos de Fosfatasa Prostatica
                        cb.beginText();
                        cb.setFontAndSize(bf, impotro.getSuma30());
                        cb.setTextMatrix(impotro.getSuma28(), alto - (impotro.getSuma29()));
                        cb.showText(datoLab1.getResultado());
                        cb.endText();
                        break;
                    case 205: ///Coloca datos de Fosforo
                        cb.beginText();
                        cb.setFontAndSize(bf, impotro.getFuma());
                        cb.setTextMatrix(impotro.getPrimera(), alto - (impotro.getNumpieza()));
                        cb.showText(datoLab1.getResultado());
                        cb.endText();
                        break;
                    case 131: ///Coloca datos de Glucosa
                        cb.beginText();
                        cb.setFontAndSize(bf, impotro.getAuto());
                        cb.setTextMatrix(impotro.getAlcohol(), alto - (impotro.getViolencia()));
                        cb.showText(datoLab1.getResultado());
                        cb.endText();
                        break;
                    case 206: ///Coloca datos de GOT
                        cb.beginText();
                        cb.setFontAndSize(bf, impotro.getSemanas());
                        cb.setTextMatrix(impotro.getUrinaria(), alto - (impotro.getSistemica()));
                        cb.showText(datoLab1.getResultado());
                        cb.endText();
                        break;
                    case 207: ///Coloca datos de GPT
                        cb.beginText();
                        cb.setFontAndSize(bf, impotro.getImc());
                        cb.setTextMatrix(impotro.getParto(), alto - (impotro.getSexo()));
                        cb.showText(datoLab1.getResultado());
                        cb.endText();
                        break;
                    case 208: ///Coloca datos de Proteinas Totales
                        cb.beginText();
                        cb.setFontAndSize(bf, impotro.getOrientacion());
                        cb.setTextMatrix(impotro.getNembarazo(), alto - (impotro.getEclam()));
                        cb.showText(datoLab1.getResultado());
                        cb.endText();
                        break;
                    case 150: ///Coloca datos de Urea
                        cb.beginText();
                        cb.setFontAndSize(bf, impotro.getNooplasia());
                        cb.setTextMatrix(impotro.getDiu(), alto - (impotro.getInyectable()));
                        cb.showText(datoLab1.getResultado());
                        cb.endText();
                        break;
                    case 168: ///Coloca datos de LDLc
                        cb.beginText();
                        cb.setFontAndSize(bf, impotro.getHemo());
                        cb.setTextMatrix(impotro.getNefro(), alto - (impotro.getDisli()));
                        cb.showText(datoLab1.getResultado());
                        cb.endText();
                        break;
                    case 167: ///Coloca datos de HDLc
                        cb.beginText();
                        cb.setFontAndSize(bf, impotro.getSueros());
                        cb.setTextMatrix(impotro.getAnestesia(), alto - (impotro.getControlpos()));
                        cb.showText(datoLab1.getResultado());
                        cb.endText();
                        break;
                    case 209: ///Coloca datos de VLDl
                        cb.beginText();
                        cb.setFontAndSize(bf, impotro.getTracto());
                        cb.setTextMatrix(impotro.getCuraciones(), alto - (impotro.getCondon()));
                        cb.showText(datoLab1.getResultado());
                        cb.endText();
                        break;
                    case 149: ///Coloca datos de Triglicoridos
                        cb.beginText();
                        cb.setFontAndSize(bf, impotro.getOtro());
                        cb.setTextMatrix(impotro.getLupus(), alto - (impotro.getLitiasis()));
                        cb.showText(datoLab1.getResultado());
                        cb.endText();
                        break;
                    case 210: ///Coloca datos de Sodio
                        cb.beginText();
                        cb.setFontAndSize(bf, impotro.getEritro());
                        cb.setTextMatrix(impotro.getCardio(), alto - (impotro.getReuma()));
                        cb.showText(datoLab1.getResultado());
                        cb.endText();
                        break;
                    case 211: ///Coloca datos de Potasio
                        cb.beginText();
                        cb.setFontAndSize(bf, impotro.getIngreso());
                        cb.setTextMatrix(impotro.getRenal(), alto - (impotro.getTuberculo()));
                        cb.showText(datoLab1.getResultado());
                        cb.endText();
                        break;
                    case 212: ///Coloca datos de Cloro
                        cb.beginText();
                        cb.setFontAndSize(bf, impotro.getFrenal());
                        cb.setTextMatrix(impotro.getAnastecia(), alto - (impotro.getServicio()));
                        cb.showText(datoLab1.getResultado());
                        cb.endText();
                        break;
                    case 213: ///Coloca datos de Magnecio
                        cb.beginText();
                        cb.setFontAndSize(bf, impotro.getEpilepsia());
                        cb.setTextMatrix(impotro.getBajopeso(), alto - (impotro.getPrematuro()));
                        cb.showText(datoLab1.getResultado());
                        cb.endText();
                        break;
                    case 214: ///Coloca datos de CK
                        cb.beginText();
                        cb.setFontAndSize(bf, impotro.getCancer());
                        cb.setTextMatrix(impotro.getPsico(), alto - (impotro.getDiscapa()));
                        cb.showText(datoLab1.getResultado());
                        cb.endText();
                        break;
                    case 215: ///Coloca datos de GGT
                        cb.beginText();
                        cb.setFontAndSize(bf, impotro.getPildora());
                        cb.setTextMatrix(impotro.getCancero(), alto - (impotro.getDepre()));
                        cb.showText(datoLab1.getResultado());
                        cb.endText();
                        break;
                    case 216: ///Coloca datos de Lipasa
                        cb.beginText();
                        cb.setFontAndSize(bf, impotro.getInsumos());
                        cb.setTextMatrix(impotro.getAqv(), alto - (impotro.getMnatural()));
                        cb.showText(datoLab1.getResultado());
                        cb.endText();
                        break;
                    case 217: ///Coloca datos de Nus
                        cb.beginText();
                        cb.setFontAndSize(bf, impotro.getSfembarazada());
                        cb.setTextMatrix(impotro.getPap(), alto - (impotro.getTabletas()));
                        cb.showText(datoLab1.getResultado());
                        cb.endText();
                        break;
                    case 218: ///Coloca datos de Hb Glicosilada
                        cb.beginText();
                        cb.setFontAndSize(bf, impotro.getSuma31());
                        cb.setTextMatrix(impotro.getSfpuerpera(), alto - (impotro.getVitamina()));
                        cb.showText(datoLab1.getResultado());
                        cb.endText();
                        break;
                    case 114: ///Coloca datos de Proteinuria 24 horas
                        cb.beginText();
                        cb.setFontAndSize(bf, impotro.getSuma34());
                        cb.setTextMatrix(impotro.getSuma32(), alto - (impotro.getSuma33()));
                        cb.showText(datoLab1.getResultado());
                        cb.endText();
                        break;
                    case 219: ///Coloca datos de Creatinuria 24 horas
                        cb.beginText();
                        cb.setFontAndSize(bf, impotro.getSuma37());
                        cb.setTextMatrix(impotro.getSuma35(), alto - (impotro.getSuma36()));
                        cb.showText(datoLab1.getResultado());
                        cb.endText();
                        break;
                    case 220: ///Coloca datos de DCE
                        cb.beginText();
                        cb.setFontAndSize(bf, impotro.getSuma40());
                        cb.setTextMatrix(impotro.getSuma38(), alto - (impotro.getSuma39()));
                        cb.showText(datoLab1.getResultado());
                        cb.endText();
                        break;
                    case 221: ///Coloca datos de LDH
                        cb.beginText();
                        cb.setFontAndSize(bf, impotro.getSuma43());
                        cb.setTextMatrix(impotro.getSuma41(), alto - (impotro.getSuma42()));
                        cb.showText(datoLab1.getResultado());
                        cb.endText();
                        break;
                }
            }   //fin de impresion laboraotrio quimicas
        }

        if ("embarazo".equals(accionl)) { //Esto es es para impresion Test EMbarazos

            Cuadernos gral = (Cuadernos) datosgral.get(0);

            ///Coloca datos de departamento
            cb.beginText();
            cb.setFontAndSize(bf, gral.getCirugia());
            cb.setTextMatrix(gral.getExodoncia(), alto - gral.getPeriodoncia());
            cb.showText(dato.getDepartamento());
            cb.endText();
            ///Coloca datos de red
            cb.beginText();
            cb.setFontAndSize(bf, gral.getPreventiva());
            cb.setTextMatrix(gral.getEndodoncia(), alto - gral.getRayosx());
            cb.showText(dato.getRed());
            cb.endText();
            ///Coloca datos de municipio
            cb.beginText();
            cb.setFontAndSize(bf, gral.getSuma1());
            cb.setTextMatrix(gral.getPrimera(), alto - gral.getNumpieza());
            cb.showText(dato.getLocalidad());
            cb.endText();
            ///Coloca datos de consultorio
            cb.beginText();
            cb.setFontAndSize(bf, gral.getSuma4());
            cb.setTextMatrix(gral.getSuma2(), alto - gral.getSuma3());
            cb.showText(datoMed.getConsultorio());
            cb.endText();
            ///Coloca datos de fechareporte
            Cuadernos datoLabo = (Cuadernos) ldatoLab.get(0);
            cb.beginText();
            cb.setFontAndSize(bf, gral.getSuma7());
            cb.setTextMatrix(gral.getSuma5(), alto - gral.getSuma6());
            cb.showText(format(datoLabo.getFechae(), "dd/MM/yyyy"));
            cb.endText();
            ///Coloca datos de numero registro
            cb.beginText();
            cb.setFontAndSize(bf, gral.getViolencia());
            cb.setTextMatrix(gral.getFuma(), alto - gral.getAlcohol());
            cb.showText(Integer.toString(datoLabo.getId_cuaderno()));
            cb.endText();
            ///Coloca datos de HCL
            cb.beginText();
            cb.setFontAndSize(bf, gral.getSistemica());
            cb.setTextMatrix(gral.getAuto(), alto - gral.getUrinaria());
            cb.showText(Integer.toString(datoPaciente.getHcl()));
            cb.endText();
            ///Coloca datos de Nombe paciente
            cb.beginText();
            cb.setFontAndSize(bf, gral.getGlome());
            cb.setTextMatrix(gral.getArterial(), alto - gral.getDiabetes());
            cb.showText(datoPaciente.getNombres());
            cb.endText();
            ///Coloca datos de HCL
            cb.beginText();
            cb.setFontAndSize(bf, gral.getLitiasis());
            cb.setTextMatrix(gral.getTracto(), alto - gral.getLupus());
            cb.showText(Integer.toString(datoHis.getEdad()));
            cb.endText();
            ///Coloca datos de Sexo Paciente
            if (datoPaciente.getId_tipo_sexo() == 1) {
                cb.beginText();
                cb.setFontAndSize(bf, gral.getDisli());
                cb.setTextMatrix(gral.getNooplasia(), alto - gral.getNefro());
                cb.showText("Femenino");
                cb.endText();
            } else {
                cb.beginText();
                cb.setFontAndSize(bf, gral.getDisli());
                cb.setTextMatrix(gral.getNooplasia(), alto - gral.getNefro());
                cb.showText("Masculino");
                cb.endText();
            }
            ///Coloca datos de Nombre Establecimiento
            cb.beginText();
            cb.setFontAndSize(bf, gral.getPrematuro());
            cb.setTextMatrix(gral.getFrenal(), alto - gral.getBajopeso());
            cb.showText(lista.getNombre());
            cb.endText();
            ///Coloca datos de Tipo Seguro
            if ("S".equals(datoPaciente.getId_estado())) {
                cb.beginText();
                cb.setFontAndSize(bf, gral.getTuberculo());
                cb.setTextMatrix(gral.getEritro(), alto - gral.getRenal());
                cb.showText("SPS(SUMI)");
                cb.endText();
            } else {
                if ("E".equals(datoPaciente.getId_estado())) {
                    cb.beginText();
                    cb.setFontAndSize(bf, gral.getTuberculo());
                    cb.setTextMatrix(gral.getEritro(), alto - gral.getRenal());
                    cb.showText("Particular");
                    cb.endText();
                }
                cb.beginText();
                cb.setFontAndSize(bf, gral.getTuberculo());
                cb.setTextMatrix(gral.getEritro(), alto - gral.getRenal());
                cb.showText("Asegurado");
                cb.endText();
            }
            ///Coloca datos de Nombre Medico
            cb.beginText();
            cb.setFontAndSize(bf, gral.getReuma());
            cb.setTextMatrix(gral.getOtro(), alto - gral.getCardio());
            cb.showText(lista.getNombres());
            cb.endText();

            cb.beginText();
            cb.setFontAndSize(bf, gral.getSuma16());
            cb.setTextMatrix(gral.getSuma14(), alto - gral.getSuma15());
            cb.showText(Integer.toString(lista.getNumpieza()));
            cb.endText();

            // laboratorioss datos de general otros
            for (int i = 0; i < ldatoLab.size(); i++) {
                Cuadernos datoLab1 = (Cuadernos) ldatoLab.get(i);

                Cuadernos impemba = (Cuadernos) datosEmba.get(0); //datos impresion Otros
                if (datoLab1.getId_laboratorio() == 128) {
                    ///Coloca datos del nombre laboratorio
                    cb.beginText();
                    cb.setFontAndSize(bf, impemba.getSuma3());
                    cb.setTextMatrix(impemba.getSuma1(), alto - (impemba.getSuma2()));
                    cb.showText("SANGRE");
                    cb.endText();

                    cb.beginText();
                    cb.setFontAndSize(bf, impemba.getCirugia());
                    cb.setTextMatrix(impemba.getExodoncia(), alto - (impemba.getPeriodoncia()));
                    cb.showText(datoLab1.getResultado().substring(0, 8));
                    cb.endText();

                    ///Coloca datos del resultado laboratorio
                    cb.beginText();
                    cb.setFontAndSize(bf, impemba.getPreventiva());
                    cb.setTextMatrix(impemba.getEndodoncia(), alto - (impemba.getRayosx()));
                    cb.showText(datoLab1.getResultado().substring(10, 27));
                    cb.endText();
                }
                if (datoLab1.getId_laboratorio() == 144) {
                    ///Coloca datos del nombre laboratorio

                    cb.beginText();
                    cb.setFontAndSize(bf, impemba.getSuma3());
                    cb.setTextMatrix(impemba.getSuma1(), alto - (impemba.getSuma2()));
                    cb.showText("ORINA");
                    cb.endText();

                    cb.beginText();
                    cb.setFontAndSize(bf, impemba.getCirugia());
                    cb.setTextMatrix(impemba.getExodoncia(), alto - (impemba.getPeriodoncia()));
                    cb.showText(datoLab1.getResultado().substring(0, 8));
                    cb.endText();

                    ///Coloca datos del resultado laboratorio
                    cb.beginText();
                    cb.setFontAndSize(bf, impemba.getPreventiva());
                    cb.setTextMatrix(impemba.getEndodoncia(), alto - (impemba.getRayosx()));
                    cb.showText(datoLab1.getResultado().substring(9, 28));
                    cb.endText();
                }
            }
        }   //fin laboratorio impresion embarazos

        if ("otros".equals(accionl)) { //Esto es es para la orden OTROS

            Cuadernos gral = (Cuadernos) datosgral.get(0);

            ///Coloca datos de departamento
            cb.beginText();
            cb.setFontAndSize(bf, gral.getCirugia());
            cb.setTextMatrix(gral.getExodoncia(), alto - gral.getPeriodoncia());
            cb.showText(dato.getDepartamento());
            cb.endText();
            ///Coloca datos de red
            cb.beginText();
            cb.setFontAndSize(bf, gral.getPreventiva());
            cb.setTextMatrix(gral.getEndodoncia(), alto - gral.getRayosx());
            cb.showText(dato.getRed());
            cb.endText();
            ///Coloca datos de municipio
            cb.beginText();
            cb.setFontAndSize(bf, gral.getSuma1());
            cb.setTextMatrix(gral.getPrimera(), alto - gral.getNumpieza());
            cb.showText(dato.getLocalidad());
            cb.endText();
            ///Coloca datos de consultorio
            cb.beginText();
            cb.setFontAndSize(bf, gral.getSuma4());
            cb.setTextMatrix(gral.getSuma2(), alto - gral.getSuma3());
            cb.showText(datoMed.getConsultorio());
            cb.endText();
            ///Coloca datos de fechareporte
            Cuadernos datoLabo = (Cuadernos) ldatoLab.get(0);
            cb.beginText();
            cb.setFontAndSize(bf, gral.getSuma7());
            cb.setTextMatrix(gral.getSuma5(), alto - gral.getSuma6());
            cb.showText(format(datoLabo.getFechae(), "dd/MM/yyyy"));
            cb.endText();
            ///Coloca datos de numero registro
            cb.beginText();
            cb.setFontAndSize(bf, gral.getViolencia());
            cb.setTextMatrix(gral.getFuma(), alto - gral.getAlcohol());
            cb.showText(Integer.toString(datoLabo.getId_cuaderno()));
            cb.endText();
            ///Coloca datos de HCL
            cb.beginText();
            cb.setFontAndSize(bf, gral.getSistemica());
            cb.setTextMatrix(gral.getAuto(), alto - gral.getUrinaria());
            cb.showText(Integer.toString(datoPaciente.getHcl()));
            cb.endText();
            ///Coloca datos de Nombe paciente
            cb.beginText();
            cb.setFontAndSize(bf, gral.getGlome());
            cb.setTextMatrix(gral.getArterial(), alto - gral.getDiabetes());
            cb.showText(datoPaciente.getNombres());
            cb.endText();
            ///Coloca datos de HCL
            cb.beginText();
            cb.setFontAndSize(bf, gral.getLitiasis());
            cb.setTextMatrix(gral.getTracto(), alto - gral.getLupus());
            cb.showText(Integer.toString(datoHis.getEdad()));
            cb.endText();
            ///Coloca datos de Sexo Paciente
            if (datoPaciente.getId_tipo_sexo() == 1) {
                cb.beginText();
                cb.setFontAndSize(bf, gral.getDisli());
                cb.setTextMatrix(gral.getNooplasia(), alto - gral.getNefro());
                cb.showText("Femenino");
                cb.endText();
            } else {
                cb.beginText();
                cb.setFontAndSize(bf, gral.getDisli());
                cb.setTextMatrix(gral.getNooplasia(), alto - gral.getNefro());
                cb.showText("Masculino");
                cb.endText();
            }
            ///Coloca datos de Nombre Establecimiento
            cb.beginText();
            cb.setFontAndSize(bf, gral.getPrematuro());
            cb.setTextMatrix(gral.getFrenal(), alto - gral.getBajopeso());
            cb.showText(lista.getNombre());
            cb.endText();
            ///Coloca datos de Tipo Seguro
            if ("S".equals(datoPaciente.getId_estado())) {
                cb.beginText();
                cb.setFontAndSize(bf, gral.getTuberculo());
                cb.setTextMatrix(gral.getEritro(), alto - gral.getRenal());
                cb.showText("SPS(SUMI)");
                cb.endText();
            } else {
                if ("E".equals(datoPaciente.getId_estado())) {
                    cb.beginText();
                    cb.setFontAndSize(bf, gral.getTuberculo());
                    cb.setTextMatrix(gral.getEritro(), alto - gral.getRenal());
                    cb.showText("Particular");
                    cb.endText();
                }
                cb.beginText();
                cb.setFontAndSize(bf, gral.getTuberculo());
                cb.setTextMatrix(gral.getEritro(), alto - gral.getRenal());
                cb.showText("Asegurado");
                cb.endText();
            }
            ///Coloca datos de Nombre Medico
            cb.beginText();
            cb.setFontAndSize(bf, gral.getReuma());
            cb.setTextMatrix(gral.getOtro(), alto - gral.getCardio());
            cb.showText(lista.getNombres());
            cb.endText();

            cb.beginText();
            cb.setFontAndSize(bf, gral.getSuma16());
            cb.setTextMatrix(gral.getSuma14(), alto - gral.getSuma15());
            cb.showText(Integer.toString(lista.getNumpieza()));
            cb.endText();

            // laboratorioss datos de general otros
            int cont = 0;
            for (int i = 0; i < ldatoLab.size(); i++) {
                Cuadernos datoLab1 = (Cuadernos) ldatoLab.get(i);
                if (datoLab1.getResultado() == null || "null".equals(datoLab1.getResultado())) {
                    datoLab1.setResultado("");
                } else {
                    datoLab1.setResultado(datoLab1.getResultado().replaceAll("<p>", ""));
                    datoLab1.setResultado(datoLab1.getResultado().replaceAll("</p>", "#"));
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
                String normal = "";

                Cuadernos impotro = (Cuadernos) datosImpOtro.get(0); //datos impresion Otros
                for (int j = 0; j < ListaLab.size(); j++) {
                    Costos listaLab = (Costos) ListaLab.get(j);
                    if (listaLab.getId_costo() == datoLab1.getId_laboratorio()) {
                        ///Coloca datos del nombre laboratorio
                        String[] cadena = datoLab1.getResultado().split("#");

                        cb.beginText();
                        cb.setFontAndSize(bf, impotro.getCirugia());
                        cb.setTextMatrix(impotro.getExodoncia(), alto - (impotro.getPeriodoncia() + (cont * 12)));
                        cb.showText(datoLab1.getLaboratorio() + " : ");
                        cb.endText();
                        ///Coloca datos del resultado laboratorio
                        for (int k = 0; k < cadena.length; k++) {
                            if (datoLab1.getId_laboratorio() == 129) {
                                cb.beginText();
                                cb.setFontAndSize(bf, impotro.getPreventiva());
                                cb.setTextMatrix(impotro.getEndodoncia(), alto - (impotro.getRayosx() + (cont * 12)));
                                cb.showText("'" + cadena[k].trim() + "'");
                                cb.endText();
                            } else {
                                cb.beginText();
                                cb.setFontAndSize(bf, impotro.getPreventiva());
                                cb.setTextMatrix(impotro.getEndodoncia(), alto - (impotro.getRayosx() + (cont * 12)));
                                cb.showText(cadena[k].trim());
                                cb.endText();
                            }
                            cont++;
                        }
                        cont++;
                    }
                }
            }   //fin de impresion laboraotrio general
        }   //fin laboratorio Otros

        if ("Ecos".equals(accionl)) { //Esto es es para la orden Ecos

            Cuadernos gral = (Cuadernos) datosgral.get(0);

            ///Coloca datos de departamento
            cb.beginText();
            cb.setFontAndSize(bf, gral.getCirugia());
            cb.setTextMatrix(gral.getExodoncia(), alto - gral.getPeriodoncia());
            cb.showText(dato.getDepartamento());
            cb.endText();
            ///Coloca datos de red
            cb.beginText();
            cb.setFontAndSize(bf, gral.getPreventiva());
            cb.setTextMatrix(gral.getEndodoncia(), alto - gral.getRayosx());
            cb.showText(dato.getRed());
            cb.endText();
            ///Coloca datos de municipio
            cb.beginText();
            cb.setFontAndSize(bf, gral.getSuma1());
            cb.setTextMatrix(gral.getPrimera(), alto - gral.getNumpieza());
            cb.showText(dato.getLocalidad());
            cb.endText();
            ///Coloca datos de consultorio
            cb.beginText();
            cb.setFontAndSize(bf, gral.getSuma4());
            cb.setTextMatrix(gral.getSuma2(), alto - gral.getSuma3());
            cb.showText(datoMed.getConsultorio());
            cb.endText();
            ///Coloca datos de fechareporte
            Cuadernos datoLabo = (Cuadernos) ldatoLab.get(0);
            cb.beginText();
            cb.setFontAndSize(bf, gral.getSuma7());
            cb.setTextMatrix(gral.getSuma5(), alto - gral.getSuma6());
            cb.showText(format(datoLabo.getFechae(), "dd/MM/yyyy"));
            cb.endText();
            ///Coloca datos de numero registro
            cb.beginText();
            cb.setFontAndSize(bf, gral.getViolencia());
            cb.setTextMatrix(gral.getFuma(), alto - gral.getAlcohol());
            cb.showText(Integer.toString(datoLabo.getId_cuaderno()));
            cb.endText();
            ///Coloca datos de HCL
            cb.beginText();
            cb.setFontAndSize(bf, gral.getSistemica());
            cb.setTextMatrix(gral.getAuto(), alto - gral.getUrinaria());
            cb.showText(Integer.toString(datoPaciente.getHcl()));
            cb.endText();
            ///Coloca datos de Nombe paciente
            cb.beginText();
            cb.setFontAndSize(bf, gral.getGlome());
            cb.setTextMatrix(gral.getArterial(), alto - gral.getDiabetes());
            cb.showText(datoPaciente.getNombres());
            cb.endText();
            ///Coloca datos de HCL
            cb.beginText();
            cb.setFontAndSize(bf, gral.getLitiasis());
            cb.setTextMatrix(gral.getTracto(), alto - gral.getLupus());
            cb.showText(Integer.toString(datoHis.getEdad()));
            cb.endText();
            ///Coloca datos de Sexo Paciente
            if (datoPaciente.getId_tipo_sexo() == 1) {
                cb.beginText();
                cb.setFontAndSize(bf, gral.getDisli());
                cb.setTextMatrix(gral.getNooplasia(), alto - gral.getNefro());
                cb.showText("Femenino");
                cb.endText();
            } else {
                cb.beginText();
                cb.setFontAndSize(bf, gral.getDisli());
                cb.setTextMatrix(gral.getNooplasia(), alto - gral.getNefro());
                cb.showText("Masculino");
                cb.endText();
            }
            ///Coloca datos de Nombre Establecimiento
            cb.beginText();
            cb.setFontAndSize(bf, gral.getPrematuro());
            cb.setTextMatrix(gral.getFrenal(), alto - gral.getBajopeso());
            cb.showText(lista.getNombre());
            cb.endText();
            ///Coloca datos de Tipo Seguro
            if ("S".equals(datoPaciente.getId_estado())) {
                cb.beginText();
                cb.setFontAndSize(bf, gral.getTuberculo());
                cb.setTextMatrix(gral.getEritro(), alto - gral.getRenal());
                cb.showText("SPS(SUMI)");
                cb.endText();
            } else {
                if ("E".equals(datoPaciente.getId_estado())) {
                    cb.beginText();
                    cb.setFontAndSize(bf, gral.getTuberculo());
                    cb.setTextMatrix(gral.getEritro(), alto - gral.getRenal());
                    cb.showText("Particular");
                    cb.endText();
                }
                cb.beginText();
                cb.setFontAndSize(bf, gral.getTuberculo());
                cb.setTextMatrix(gral.getEritro(), alto - gral.getRenal());
                cb.showText("Asegurado");
                cb.endText();
            }
            ///Coloca datos de Nombre Medico
            cb.beginText();
            cb.setFontAndSize(bf, gral.getReuma());
            cb.setTextMatrix(gral.getOtro(), alto - gral.getCardio());
            cb.showText(lista.getNombres());
            cb.endText();

            cb.beginText();
            cb.setFontAndSize(bf, gral.getSuma16());
            cb.setTextMatrix(gral.getSuma14(), alto - gral.getSuma15());
            cb.showText(Integer.toString(lista.getNumpieza()));
            cb.endText();

            // laboratorioss datos de general otros
            int cont = 0;
            for (int i = 0; i < ldatoLab.size(); i++) {
                Cuadernos datoLab1 = (Cuadernos) ldatoLab.get(i);
                if (datoLab1.getResultado() == null || "null".equals(datoLab1.getResultado())) {
                    datoLab1.setResultado("");
                } else {
                    datoLab1.setResultado(datoLab1.getResultado().replaceAll("<p>", ""));
                    datoLab1.setResultado(datoLab1.getResultado().replaceAll("</p>", "#"));
                    //datoLab1.setResultado(datoLab1.getResultado().replaceAll("<br>","-"));
                    datoLab1.setResultado(datoLab1.getResultado().replaceAll("&nbsp;", ""));
                    datoLab1.setResultado(datoLab1.getResultado().replaceAll("<strong>", ""));
                    datoLab1.setResultado(datoLab1.getResultado().replaceAll("</strong>", ""));
                    datoLab1.setResultado(datoLab1.getResultado().replaceAll("<br />", "#"));
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
                String normal = "";

                Cuadernos impotro = (Cuadernos) datosEcos.get(0); //datos impresion Otros
                for (int j = 0; j < ListaLab.size(); j++) {
                    Costos listaLab = (Costos) ListaLab.get(j);
                    if (listaLab.getId_costo() == datoLab1.getId_laboratorio()) {
                        ///Coloca datos del nombre laboratorio
                        String[] cadena = datoLab1.getResultado().split("#");

                        cb.beginText();
                        cb.setFontAndSize(bf, impotro.getCirugia());
                        cb.setTextMatrix(impotro.getExodoncia(), alto - (impotro.getPeriodoncia() + (cont * 14)));
                        cb.showText(datoLab1.getLaboratorio() + " : ");
                        cb.endText();
                        ///Coloca datos del resultado laboratorio
                        for (int k = 0; k < cadena.length; k++) {
                            if (datoLab1.getId_laboratorio() == 136) {
                                cb.beginText();
                                cb.setFontAndSize(bf, impotro.getPreventiva());
                                cb.setTextMatrix(impotro.getEndodoncia(), alto - (impotro.getRayosx() + (cont * 14)));
                                cb.showText(cadena[k].trim());
                                cb.endText();
                            }
                            cont++;
                        }
                        cont++;
                    }
                }
            }   //fin de impresion laboraotrio Ecos
        }   //fin laboratorio Ecos

    }

    public static String format(Date dia, String formato) {
        if (dia == null) {
            return "";
        }
        SimpleDateFormat formatDate = new SimpleDateFormat(formato);
        return formatDate.format(dia);
    }
}
