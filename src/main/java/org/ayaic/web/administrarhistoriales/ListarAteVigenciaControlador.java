package org.ayaic.web.administrarhistoriales;

import java.util.HashMap;
import java.util.Map;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.ayaic.domain.Camas;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Consultorios;
import org.ayaic.domain.Cuadernos;
import org.ayaic.domain.Historiales;
import org.ayaic.domain.Medicamentos;
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.Personas;
import org.ayaic.domain.Recetas;
import org.ayaic.domain.Roles;
import org.ayaic.domain.Salas;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.document.AbstractXlsView;

@Controller
public class ListarAteVigenciaControlador {

    private final MiFacade mi;

    public ListarAteVigenciaControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/ListarAteVigencia.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response){
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accionr = request.getParameter("accionr");
        String id_sala = request.getParameter("id_sala");
        String accion = request.getParameter("boton");
        String[] dias = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
        String[] meses = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        String[] horas = {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"};
        String[] minutos = {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"};
        String diaq1 = request.getParameter("mesq1");
        String mesq1 = request.getParameter("mesq1");
        String ip = request.getRemoteAddr();
        String host = request.getRemoteHost();
        Date fechaact = new Date();
        Date fecha1 = new Date();
        int anioq = fecha1.getYear() + 1900;
        String[] anios = {Integer.toString(anioq), Integer.toString(anioq - 1), Integer.toString(anioq - 2), Integer.toString(anioq - 3), Integer.toString(anioq - 4), Integer.toString(anioq - 5), Integer.toString(anioq - 6), Integer.toString(anioq - 7), Integer.toString(anioq - 8), Integer.toString(anioq - 9), Integer.toString(anioq - 10)};

        modelo.put("anios", anios);
        modelo.put("meses", meses);
        modelo.put("dias", dias);
        modelo.put("horas", horas);
        modelo.put("minutos", minutos);
        modelo.put("anio", Integer.toString(fecha1.getYear() + 1900));
        if (fecha1.getMonth() + 1 < 10) {
            mesq1 = "0" + Integer.toString(fecha1.getMonth() + 1);
        } else {
            mesq1 = Integer.toString(fecha1.getMonth() + 1);
        }

        if (fecha1.getDate() < 10) {
            diaq1 = "0" + Integer.toString(fecha1.getDate());
        } else {
            diaq1 = Integer.toString(fecha1.getDate());
        }
        modelo.put("mes", mesq1);
        modelo.put("dia", diaq1);
        modelo.put("anio2", Integer.toString(fecha1.getYear() + 1900));
        modelo.put("mes2", mesq1);
        modelo.put("dia2", diaq1);
        modelo.put("hora", "00");
        modelo.put("minuto", "00");
        modelo.put("hora2", "23");
        modelo.put("minuto2", "59");

        Personas persona = this.mi.getBuscarPersona(cliente.getId_persona()); // saca un registro a ser modificado

        Consultorios datosconsul = this.mi.getDatosConsultorio(persona.getId_consultorio()); // saca un registro a ser modificado
        modelo.put("tipo_medico", Integer.toString(datosconsul.getId_cargo()));
        modelo.put("id_persona", Integer.toString(cliente.getId_persona()));
        modelo.put("seguro_estab", Integer.toString(cliente.getInst_id()));
        modelo.put("cod_esta", Integer.toString(cliente.getCod_esta()));

        Salas dsala = new Salas();
        dsala.setId_piso(0);
        List listarSalas = this.mi.getListarSalasLibres(dsala);
        modelo.put("listarSalas", listarSalas);

        Camas listcama = new Camas();
        List listarCamasSala = this.mi.getListarCamasSala(listcama);
        modelo.put("listarCamasSala", listarCamasSala);

        if ("RecetaLab".equals(accionr)) {
            String id_historial = request.getParameter("id_historial");
            String id_paciente = request.getParameter("id_paciente");
            String id_persona = request.getParameter("id_persona");
            String id_consultorio = request.getParameter("id_consultorio");
            String expedido = request.getParameter("expedido");
            String tipo = "C";
            int cuaderno = 0;

            Pacientes buscarPaciente = this.mi.getDatosPaciente(Integer.parseInt(id_paciente)); // saca un registro a ser modificado
            modelo.put("datos", buscarPaciente);

            Historiales datoc = new Historiales();
            datoc.setId_historial(Integer.parseInt(id_historial));
            List listaH = this.mi.getHistorialLibros(datoc);

            Historiales datopac = (Historiales) listaH.get(0);
            if (datopac.getId_historial() == Integer.parseInt(id_historial)) {
                cuaderno = datopac.getId_tipo_documento() + datopac.getId_tipo_parentesco() + datopac.getId_pais() + datopac.getId_departamento() + datopac.getNum() + datopac.getId_provincia() + datopac.getId_localidad() + datopac.getId_reservacion() + datopac.getEmbarazo();
            }

            Recetas datore = new Recetas();
            datore.setId_historial(Integer.parseInt(id_historial));
            datore.setId_farmacia(cliente.getId_farmacia());
            datore.setCod_esta(cliente.getCod_esta());
            datore.setId_estado("%");
            List listarRecetas = this.mi.getListarRecetas(datore);
            modelo.put("listarRecetas", listarRecetas);
            Medicamentos dato = new Medicamentos();
            dato.setId_persona(Integer.parseInt(id_persona));
            dato.setExpedido(expedido);
            dato.setCod_esta(cliente.getCod_esta());

            Cuadernos datoll = new Cuadernos();
            datoll.setId_historial(Integer.parseInt(id_historial));
            datoll.setCod_esta(cliente.getCod_esta());
            List listalab = this.mi.getPacienteLaboratorio(datoll);    ///26/04/2015
            //List listalab = this.mi.getPacienteLaboratorio(Integer.parseInt(id_historial));
            modelo.put("listalab", listalab);
            Historiales datohis = new Historiales();
            datohis.setId_historial(Integer.parseInt(id_historial));
            datohis.setCod_esta(cliente.getCod_esta());
            Historiales datoHis = this.mi.getDatosHistorial(datohis);
            modelo.put("datosHis", datoHis);

            Pacientes buscarPaciente2 = this.mi.getDatosPaciente(datoHis.getId_paciente());
            modelo.put("datos", buscarPaciente2);
            modelo.put("dato", cliente);

            modelo.put("expedido", expedido);
            modelo.put("accion", "Recetar Asegurado");
            modelo.put("sw1", request.getParameter("sw1"));
            modelo.put("id_paciente", id_paciente);
            modelo.put("id_historial", id_historial);
            modelo.put("id_reservacion", id_historial);
            modelo.put("id_consultorio", id_consultorio);
            modelo.put("id_persona", id_persona);
            return new ModelAndView("administrarhistoriales/RecetaLabosPaciente", modelo);
        }

        if ("Actualizarra".equals(accion)) {//// 08-10-2016 se quita mucho tarda actualizar
            String sFecha_ini = request.getParameter("valor_1");
            String sFecha_fin = request.getParameter("valor_2");
            if (("".equals(sFecha_ini)) || ("".equals(sFecha_fin))) {
                return new ModelAndView("administrarcuadernos/ReporteSnis", modelo);
            } else {
                String[] sFechaini = sFecha_ini.split("-");
                int iAnio1 = Integer.parseInt(sFechaini[0]) - 1900;
                int iMes1 = Integer.parseInt(sFechaini[1]) - 1;
                int iDia1 = Integer.parseInt(sFechaini[2]);

                String[] sFechafin = sFecha_fin.split("-");
                int iAnio2 = Integer.parseInt(sFechafin[0]) - 1900;
                int iMes2 = Integer.parseInt(sFechafin[1]) - 1;
                int iDia2 = Integer.parseInt(sFechafin[2]);

                Date dFechaini1 = new Date(iAnio1, iMes1, iDia1);
                Date dFechafin1 = new Date(iAnio2, iMes2, iDia2);
                modelo.put("dato", cliente);

                Historiales dato = new Historiales();
                dato.setFecha_ini(dFechaini1);
                dato.setFecha_fin(dFechafin1);
                dato.setCod_esta(cliente.getCod_esta());
                List listarAteVig = this.mi.getInternados(dato);
                modelo.put("listavig", listarAteVig);
                return new ModelAndView("Aviso", "mensaje", "Se actualizo correctamente");
                ///return new ModelAndView("administrarhistoriales/ListarAtenVigencia", modelo);
            }
        }

        if ("BuscarNombreFecha".equals(accion)) {
            String diai = request.getParameter("diai");
            String mesi = request.getParameter("mesi");
            String anioi = request.getParameter("anioi");
            String horai = request.getParameter("horai");
            String minutoi = request.getParameter("minutoi");
            String diaf = request.getParameter("diaf");
            String mesf = request.getParameter("mesf");
            String aniof = request.getParameter("aniof");
            String horaf = request.getParameter("horaf");
            String minutof = request.getParameter("minutof");
            if (("".equals(diai)) || ("".equals(mesi)) || ("".equals(anioi))) {
                return new ModelAndView("administrarcuadernos/ReporteSnis", modelo);
            } else {
                String nombres = request.getParameter("nombres");
                if (!"".equals(nombres) && nombres != null) {
                    nombres = nombres.trim();
                    nombres = nombres.replaceAll(" +", " ");///quita espacios en blanco entre cadenas
                    nombres = nombres.replaceAll("\\s", ":*&");///:*&
                    nombres = nombres.replaceAll("ñ", "n");
                    nombres = nombres.replaceAll("Ñ", "N");
                    nombres = nombres + ":*";
                    //nombres = nombres.trim().replaceAll("\\s", "%");
                    //nombres = "%" + nombres + "%";  
                }
                int iAnio1 = Integer.parseInt(anioi) - 1900;
                int iMes1 = Integer.parseInt(mesi) - 1;
                int iDia1 = Integer.parseInt(diai);
                int iHor1 = Integer.parseInt(horai);
                int iMin1 = Integer.parseInt(minutoi);

                int iAnio2 = Integer.parseInt(aniof) - 1900;
                int iMes2 = Integer.parseInt(mesf) - 1;
                int iDia2 = Integer.parseInt(diaf);
                int iHor2 = Integer.parseInt(horaf);
                int iMin2 = Integer.parseInt(minutof);

                Date dFechaini1 = new Date(iAnio1, iMes1, iDia1, iHor1, iMin1, 00);
                Date dFechafin1 = new Date(iAnio2, iMes2, iDia2, iHor2, iMin2, 59);

                modelo.put("dato", cliente);

                Historiales dato = new Historiales();
                dato.setFecha_ini(dFechaini1);
                dato.setFecha_fin(dFechafin1);
                dato.setAccion("F");/////getVigenciaFecha    
                dato.setCod_esta(cliente.getCod_esta());
                dato.setNombres(nombres);
                List listarAteVig = this.mi.getVigenciaFecha(dato);
                modelo.put("listavig", listarAteVig);

                return new ModelAndView("administrarhistoriales/ListarAtenVigencia", modelo);
            }
        }

        if ("Imprimir".equals(accion) || "ExportarExcel".equals(accion)) {
            String diai = request.getParameter("diai");
            String mesi = request.getParameter("mesi");
            String anioi = request.getParameter("anioi");
            String horai = request.getParameter("horai");
            String minutoi = request.getParameter("minutoi");
            String diaf = request.getParameter("diaf");
            String mesf = request.getParameter("mesf");
            String aniof = request.getParameter("aniof");
            String horaf = request.getParameter("horaf");
            String minutof = request.getParameter("minutof");
            if (("".equals(diai)) || ("".equals(mesi)) || ("".equals(anioi))) {
                return new ModelAndView("administrarcuadernos/ReporteSnis", modelo);
            } else {
                int iAnio1 = Integer.parseInt(anioi) - 1900;
                int iMes1 = Integer.parseInt(mesi) - 1;
                int iDia1 = Integer.parseInt(diai);
                int iHor1 = Integer.parseInt(horai);
                int iMin1 = Integer.parseInt(minutoi);

                int iAnio2 = Integer.parseInt(aniof) - 1900;
                int iMes2 = Integer.parseInt(mesf) - 1;
                int iDia2 = Integer.parseInt(diaf);
                int iHor2 = Integer.parseInt(horaf);
                int iMin2 = Integer.parseInt(minutof);

                Date dFechaini1 = new Date(iAnio1, iMes1, iDia1, iHor1, iMin1, 00);
                Date dFechafin1 = new Date(iAnio2, iMes2, iDia2, iHor2, iMin2, 59);
                modelo.put("dato", cliente);

                Historiales dato = new Historiales();
                Cuadernos datoc = new Cuadernos();
                Recetas dator = new Recetas();

                dato.setCod_esta(cliente.getCod_esta());
                dato.setFecha_ini(dFechaini1);
                dato.setFecha_fin(dFechafin1);

                datoc.setCod_esta(cliente.getCod_esta());
                datoc.setFecha_ini(dFechaini1);
                datoc.setFecha_fin(dFechafin1);

                dator.setCod_esta(cliente.getCod_esta());
                dator.setFecha_ini(dFechaini1);
                dator.setFecha_fin(dFechafin1);
                dato.setAccion("V");/////getVigencia_now
                Roles rol = new Roles();
                rol.setId_usuario(cliente.getId_usuario());
                List listaroles = this.mi.getListarRolesCliente(rol);

                List listarI = this.mi.getVigencia_now(dato);
                modelo.put("milistaI", listarI);
                if (!listaroles.isEmpty()) {
                    for (int i = 0; i < listaroles.size(); i++) {
                        Roles datorol = (Roles) listaroles.get(i);
                        if (datorol.getId_rol() == 23) {
                            dato.setAccion("T");/////getVigencia_now_triaje
                            List listarI2 = this.mi.getVigencia_now_triaje(dato);
                            modelo.put("milistaI", listarI2);
                        }
                    }
                }
                if ("ExportarExcel".equals(accion)) {
                    dato.setAccion("2");/////getVigenciaFecha2  emergencias
                }

                List listarMed = this.mi.getListaRecetaGen(dator);
                modelo.put("listarMed", listarMed);

                List listaLab = this.mi.getLabGen(datoc);
                modelo.put("listaLab", listaLab);

                List listaCie = this.mi.getVerCuaderno1CieMorbi(datoc);  ///datos del CIE10 
                modelo.put("listaCie", listaCie);

                return new ModelAndView(new AtendidosVigenciaPDF(), modelo);
            }
        }

        Historiales dato = new Historiales();
        dato.setId_persona(cliente.getId_persona());
        dato.setCod_esta(cliente.getCod_esta());
        dato.setId_consultorio(persona.getId_consultorio());
        dato.setId_estado("C");
        List listarPaises = this.mi.getListarReservaciones(dato);
        modelo.put("milista", listarPaises);

        if (id_sala == null) {
            dato.setAccion("R");
        } else {
            if ("Buscar".equals(accion)) {
                dato.setAccion("S");
                dato.setId_cargo(Integer.parseInt(id_sala));
            }
        }

        if (datosconsul.getId_cargo() == 50 && id_sala == null) {///solo para mostrar internados en pediatria si es pediatria
            dato.setAccion("S");
            dato.setId_cargo(2);
        }

        dato.setAccion("V");/////getVigencia_now
        Roles rol = new Roles();
        rol.setId_usuario(cliente.getId_usuario());
        List listaroles = this.mi.getListarRolesCliente(rol);
        dato.setCod_esta(cliente.getCod_esta());
        List listarI = this.mi.getVigencia_now(dato);
        modelo.put("listavig", listarI);
        if (!listaroles.isEmpty()) {
            for (int i = 0; i < listaroles.size(); i++) {
                Roles datorol = (Roles) listaroles.get(i);
                if (datorol.getId_rol() == 23) {
                    dato.setAccion("T");/////getVigencia_now_triaje
                    List listarI2 = this.mi.getVigencia_now_triaje(dato);
                    modelo.put("listavig", listarI2);
                }
            }
        }

        modelo.put("id_persona", Integer.toString(cliente.getId_persona()));
        modelo.put("id_consultorio", Integer.toString(persona.getId_consultorio()));

        return new ModelAndView("administrarhistoriales/ListarAtenVigencia", modelo);

    }

    private static class excel extends AbstractXlsView {

        @Override
        protected void buildExcelDocument(Map<String, Object> map, Workbook wrkbk, HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
            
            Sheet sheet = wrkbk.createSheet("atendidos");

            Row fila = sheet.createRow(0);
            Cell celda;

            String[] titulos = {"nro", "fecha", "hora", "matricula", "edad", "nombres paciente", "patronal", "empresa", "codcns", "nombre medico", "diagnostico", "plan accion", "cie10", "literal"};
            int i;

            for (i = 0; i < titulos.length; i++) {
                celda = fila.createCell(i);
                celda.setCellValue(titulos[i]);
            }
            // Nueva fila
            fila = sheet.createRow(1);
            List listar = (List) map.get("milistaI");
            for (i = 0; i < listar.size(); i++) {
                Historiales dato = (Historiales) listar.get(i);
                Row filas = sheet.createRow(i + 3);
                for (int j = 0; j < 14; j++) {
                    if (dato.getDiagnostico() != null) {
                        dato.setDiagnostico(dato.getDiagnostico().replaceAll("<p>", "\n"));
                        dato.setDiagnostico(dato.getDiagnostico().replaceAll("</p>", " "));
                        dato.setDiagnostico(dato.getDiagnostico().replaceAll("&nbsp;", ""));
                        dato.setDiagnostico(dato.getDiagnostico().replaceAll("<strong>", " "));
                        dato.setDiagnostico(dato.getDiagnostico().replaceAll("</strong>", " "));
                        dato.setDiagnostico(dato.getDiagnostico().replaceAll("<br />", "\n"));
                        dato.setDiagnostico(dato.getDiagnostico().replaceAll("<u>", " "));
                        dato.setDiagnostico(dato.getDiagnostico().replaceAll("</u>", " "));
                        dato.setDiagnostico(dato.getDiagnostico().replaceAll("&ntilde;", "n"));
                        dato.setDiagnostico(dato.getDiagnostico().replaceAll("&Ntilde;", "N"));
                        dato.setDiagnostico(dato.getDiagnostico().replaceAll("&Aacute;", "A"));
                        dato.setDiagnostico(dato.getDiagnostico().replaceAll("&Eacute;", "E"));
                        dato.setDiagnostico(dato.getDiagnostico().replaceAll("&Iacute;", "I"));
                        dato.setDiagnostico(dato.getDiagnostico().replaceAll("&Oacute;", "O"));
                        dato.setDiagnostico(dato.getDiagnostico().replaceAll("&Uacute;", "U"));
                        dato.setDiagnostico(dato.getDiagnostico().replaceAll("&aacute;", "a"));
                        dato.setDiagnostico(dato.getDiagnostico().replaceAll("&eacute;", "e"));
                        dato.setDiagnostico(dato.getDiagnostico().replaceAll("&iacute;", "i"));
                        dato.setDiagnostico(dato.getDiagnostico().replaceAll("&oacute;", "o"));
                        dato.setDiagnostico(dato.getDiagnostico().replaceAll("&uacute;", "u"));
                        dato.setDiagnostico(dato.getDiagnostico().replaceAll("&quot;", "'"));
                    }
                    HSSFRow row = (HSSFRow) sheet.createRow(i + 1);
                    row.createCell(0).setCellValue(i + 1);  ////num
                    row.createCell(1).setCellValue(dato.getCadena12());  ////fecha
                    row.createCell(2).setCellValue(dato.getCadena13());  ////hora
                    row.createCell(3).setCellValue(dato.getNro_registro());  ////matricula
                    row.createCell(4).setCellValue(dato.getCadena2());  ////edad
                    row.createCell(5).setCellValue(dato.getNombres());   ///paciente
                    row.createCell(6).setCellValue(dato.getCadena1());  ///patronal
                    row.createCell(7).setCellValue(dato.getNombre());  ///nombre empresa
                    row.createCell(8).setCellValue(dato.getCadena5());  ///codcns
                    row.createCell(9).setCellValue(dato.getCadena3());  ///medico
                    row.createCell(10).setCellValue(dato.getDiagnostico());  ///diagnostico
                    row.createCell(11).setCellValue(dato.getAccion());  ///plan de accion
                    row.createCell(12).setCellValue(dato.getCadena6());  ///cie10
                    row.createCell(13).setCellValue(dato.getCadena7());  ///literal
                }
            }
        }

    }

}
