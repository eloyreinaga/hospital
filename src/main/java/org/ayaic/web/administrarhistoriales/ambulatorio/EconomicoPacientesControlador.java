package org.ayaic.web.administrarhistoriales.ambulatorio;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Consultorios;
import org.ayaic.domain.Cuadernos;
import org.ayaic.domain.Historiales;
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.Personas;
import org.ayaic.domain.Recetas;
import org.ayaic.domain.Seguros;
import org.ayaic.domain.logic.MiFacade;
import static org.ayaic.web.administrarfarmacias.ListarOrdenPDF.format;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EconomicoPacientesControlador {

    private final MiFacade mi;

    public EconomicoPacientesControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/EconomicoPacientes.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        Personas persona = this.mi.getBuscarPersona(cliente.getId_persona()); // saca un registro a ser modificado
        Consultorios datosconsul = this.mi.getDatosConsultorio(persona.getId_consultorio()); // saca un registro a ser modificado
        modelo.put("tipo_medico", Integer.toString(datosconsul.getId_cargo()));
        String radio = request.getParameter("radio");
        String[] dias = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
        String[] meses = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        String[] horas = {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"};
        String[] minutos = {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"};
        String diaq1 = request.getParameter("diaq1");
        String mesq1 = request.getParameter("mesq1");
        String ip = request.getRemoteAddr();
        String host = request.getRemoteHost();
        Date fechaact = new Date();
        Date fecha1 = new Date();
        int anioq = fecha1.getYear() + 1900;
        String[] anios = {Integer.toString(anioq), Integer.toString(anioq - 1), Integer.toString(anioq - 2), Integer.toString(anioq - 3), Integer.toString(anioq - 4), Integer.toString(anioq - 5), Integer.toString(anioq - 6), Integer.toString(anioq - 7), Integer.toString(anioq - 8), Integer.toString(anioq - 9), Integer.toString(anioq - 10)};

        List listarSeguros = this.mi.getListarSeguros("A");
        modelo.put("listaSeguro", listarSeguros);
        modelo.put("listaPacAfi", listarSeguros);
        modelo.put("estab", cliente.getArea());
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

        //Recuperamos variables del jsp
        String sId_estado = request.getParameter("id_estado");
        String id_persona = request.getParameter("id_persona");
        String sAccion = request.getParameter("boton");
        String accion = request.getParameter("boton");
        if (radio == null) {
            radio = "4";
        }

        if ("Buscar Resumen".equals(accion)) {
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
            String id_seguro = request.getParameter("id_seguro");
            String internado = request.getParameter("radio");
            if (("".equals(diai)) || ("".equals(mesi)) || ("".equals(anioi))) {
                return new ModelAndView("administrarhistoriales/EconomicoPacientes", modelo);
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
                Date dFechafin2 = new Date(iAnio2, iMes2, iDia2, iHor2, iMin2, 59);

                Historiales dato = new Historiales();
                dato.setFecha_ini(dFechaini1);
                dato.setFecha_fin(dFechafin2);
                dato.setCod_esta(cliente.getCod_esta());
                dato.setSuma1(Integer.parseInt(radio));
                dato.setSuma2(Integer.parseInt(radio));
                dato.setId_seguro(Integer.parseInt(id_seguro));
                dato.setSuma3(Integer.parseInt(radio));
                dato.setSuma4(Integer.parseInt(id_seguro));
                dato.setInternado(Integer.parseInt(internado));        

                if ("4".equals(radio)) {
                    dato.setInternado(0);
                    dato.setSuma1(0);  ///tipo internado 0 ambulatorios
                    dato.setSuma2(3);  ///1 emergencias, 2 internado, 3 dado alta
                }

                if ("0".equals(id_seguro) || "1000".equals(id_seguro)) {
                    dato.setExpedido("E");
                    dato.setSuma3(0);
                    dato.setSuma4(0);
                    modelo.put("seguro", "Pacientes Externos");
                    if ("1000".equals(id_seguro)) {
                        dato.setExpedido("S");
                        dato.setSuma3(1);
                        dato.setSuma4(5);
                        modelo.put("seguro", "Pacientes Ley 475");
                    }
                } else {

                    Seguros buscarseguro = this.mi.getDatosSeguro(Integer.parseInt(id_seguro)); // saca un registro a ser modificado
                    modelo.put("seguro", buscarseguro.getSeguro());
                }

                List listarAtendidos = this.mi.getResumenEconomico(dato);
                modelo.put("milistaAten", listarAtendidos);

                modelo.put("id_seguro", id_seguro);
                modelo.put("radio", radio);
                modelo.put("anio", Integer.toString(dFechaini1.getYear() + 1900));
                modelo.put("mes", Integer.toString(dFechaini1.getMonth() + 1));
                modelo.put("dia", Integer.toString(dFechaini1.getDate()));
                return new ModelAndView("administrarhistoriales/EconomicoPacientes", modelo);
            }
        }

        if ("LaboratorioGeneral".equals(accion)) {
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
            String id_seguro = request.getParameter("id_seguro");
            String internado = request.getParameter("radio");
            if (("".equals(diai)) || ("".equals(mesi)) || ("".equals(anioi))) {
                return new ModelAndView("administrarhistoriales/EconomicoPacientes", modelo);
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
                Date dFechafin2 = new Date(iAnio2, iMes2, iDia2, iHor2, iMin2, 59);

                Cuadernos dato = new Cuadernos();
                // fechas de cobro
                dato.setFecha_ini(dFechaini1);
                dato.setFecha_fin(dFechafin2);

                dato.setId_persona(cliente.getId_persona());
                dato.setId_seguro(Integer.parseInt(id_seguro));
                dato.setId_cuaderno(Integer.parseInt(id_seguro));
                dato.setTipoconsulta("I");
                List listalabT = this.mi.getLabRealizadoTotal(dato);
                modelo.put("listalabT", listalabT);

                modelo.put("dato", cliente);
                modelo.put("id_seguro", id_seguro);
                modelo.put("radio", radio);
                //modelo.put("valor_1", sFecha_ini);
                //modelo.put("valor_2", sFecha_fin);
                //modelo.put("fecha1", sFecha_ini);
                //modelo.put("fecha2", sFecha_fin);
                modelo.put("anio", Integer.toString(dFechaini1.getYear() + 1900));
                modelo.put("mes", Integer.toString(dFechaini1.getMonth() + 1));
                modelo.put("dia", Integer.toString(dFechaini1.getDate()));
                return new ModelAndView(new VerReporteSpam4PDF(), modelo);
            }
        }

        if ("Farmacia Eco".equals(accion)) {
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
            String id_seguro = request.getParameter("id_seguro");
            String internado = request.getParameter("radio");
            if (("".equals(diai)) || ("".equals(mesi)) || ("".equals(anioi))) {
                return new ModelAndView("administrarhistoriales/EconomicoPacientes", modelo);
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
                Date dFechafin2 = new Date(iAnio2, iMes2, iDia2, iHor2, iMin2, 59);

                Pacientes dato = new Pacientes();
                dato.setFecha_ini(dFechaini1);
                dato.setFecha_fin(dFechafin2);
                dato.setId_estado("P");
                dato.setTipo("B");
                dato.setCod_esta(cliente.getCod_esta());
                dato.setId_rubro(Integer.parseInt(id_seguro));
                dato.setRegistro("S");
                List listarSinPago = this.mi.getListaMedKEntregadosSPAM(dato); ///23/12/2017 se cambia era getListaMedKEntregados
                modelo.put("listapago", listarSinPago);

                Recetas dator = new Recetas();
                dator.setFecha_ini(dFechaini1);
                dator.setFecha_fin(dFechafin2);
                dator.setExpedido("P");
                List listarKardex = this.mi.getListaMedKardexEntregados(dator);
                modelo.put("listarKardex", listarKardex);

                modelo.put("dato", cliente);
                modelo.put("id_seguro", id_seguro);
                modelo.put("radio", radio);
                //modelo.put("valor_1", sFecha_ini);
                //modelo.put("valor_2", sFecha_fin);
                //modelo.put("fecha1", sFecha_ini);
                //modelo.put("fecha2", sFecha_fin);
                modelo.put("anio", Integer.toString(dFechaini1.getYear() + 1900));
                modelo.put("mes", Integer.toString(dFechaini1.getMonth() + 1));
                modelo.put("dia", Integer.toString(dFechaini1.getDate()));
                return new ModelAndView(new VerReporteFarmaPDF(), modelo);
            }
        }

        if ("Laboratorios".equals(accion)) {
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
            String id_seguro = request.getParameter("id_seguro");
            String internado = request.getParameter("radio");
            if (("".equals(diai)) || ("".equals(mesi)) || ("".equals(anioi))) {
                return new ModelAndView("administrarhistoriales/EconomicoPacientes", modelo);
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
                Date dFechafin2 = new Date(iAnio2, iMes2, iDia2, iHor2, iMin2, 59);

                Historiales dato = new Historiales();
                dato.setFecha_ini(dFechaini1);
                dato.setFecha_fin(dFechafin2);
                dato.setAccion("L");
                dato.setInternado(Integer.parseInt(radio));

                if ("0".equals(id_seguro)) {
                    dato.setExpedido("E");
                } else {
                    dato.setId_seguro(Integer.parseInt(id_seguro));
                }
                List listarAtendidos = this.mi.getResumenEconomicoLab(dato);
                modelo.put("milistaAten", listarAtendidos);

                Cuadernos datol = new Cuadernos();
                datol.setFecha_ini(dFechaini1);
                datol.setFecha_fin(dFechafin2);
                List listalabos = this.mi.getLabSSPAM(datol);
                modelo.put("listalab", listalabos);

                modelo.put("dato", cliente);
                modelo.put("id_seguro", id_seguro);
                modelo.put("radio", radio);
                //modelo.put("valor_1", sFecha_ini);
                //modelo.put("valor_2", sFecha_fin);
                //modelo.put("fecha1", sFecha_ini);
                //modelo.put("fecha2", sFecha_fin);
                modelo.put("anio", Integer.toString(dFechaini1.getYear() + 1900));
                modelo.put("mes", Integer.toString(dFechaini1.getMonth() + 1));
                modelo.put("dia", Integer.toString(dFechaini1.getDate()));
                return new ModelAndView(new VerReporteSpam2PDF(), modelo);
            }
        }

        if ("Farmacia".equals(accion)) {
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
            String id_seguro = request.getParameter("id_seguro");
            String internado = request.getParameter("radio");
            if (("".equals(diai)) || ("".equals(mesi)) || ("".equals(anioi))) {
                return new ModelAndView("administrarhistoriales/EconomicoPacientes", modelo);
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
                Date dFechafin2 = new Date(iAnio2, iMes2, iDia2, iHor2, iMin2, 59);

                Pacientes dato = new Pacientes();
                dato.setFecha_ini(dFechaini1);
                dato.setFecha_fin(dFechafin2);
                dato.setId_estado("P");
                dato.setTipo("B");
                dato.setId_rubro(Integer.parseInt(id_seguro));
                dato.setCod_esta(cliente.getCod_esta());
                dato.setRegistro("S");
                List listarSinPago = this.mi.getListaMedKEntregadosSPAM(dato);///23/12/2017 se cambia era getListaMedKEntregados
                modelo.put("listapago", listarSinPago);

                Recetas dator = new Recetas();
                dator.setFecha_ini(dFechaini1);
                dator.setFecha_fin(dFechafin2);
                dator.setExpedido("P");
                List listarKardex = this.mi.getListaMedKardexEntregados(dator);
                modelo.put("listarKardex", listarKardex);
                modelo.put("dato", cliente);
                modelo.put("id_seguro", id_seguro);
                modelo.put("radio", radio);
                //modelo.put("valor_1", sFecha_ini);
                //modelo.put("valor_2", sFecha_fin);
                //modelo.put("fecha1", sFecha_ini);
                //modelo.put("fecha2", sFecha_fin);
                modelo.put("anio", Integer.toString(dFechaini1.getYear() + 1900));
                modelo.put("mes", Integer.toString(dFechaini1.getMonth() + 1));
                modelo.put("dia", Integer.toString(dFechaini1.getDate()));
                return new ModelAndView(new VerReporteSpam5PDF(), modelo);
            }
        }

        if ("Enfermeria".equals(accion)) {
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
            String id_seguro = request.getParameter("id_seguro");
            if (("".equals(diai)) || ("".equals(mesi)) || ("".equals(anioi))) {
                return new ModelAndView("administrarhistoriales/EconomicoPacientes", modelo);
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
                Date dFechafin2 = new Date(iAnio2, iMes2, iDia2, iHor2, iMin2, 59);

                Historiales dato = new Historiales();
                dato.setFecha_ini(dFechaini1);
                dato.setFecha_fin(dFechafin2);
                dato.setAccion("E");
                dato.setInternado(Integer.parseInt(radio));

                if ("0".equals(id_seguro)) {
                    dato.setExpedido("E");
                } else {
                    dato.setId_seguro(Integer.parseInt(id_seguro));
                }

                List listarAtendidos = this.mi.getResumenEconomicoEco(dato);
                modelo.put("milistaAten", listarAtendidos);

                modelo.put("dato", cliente);
                modelo.put("id_seguro", id_seguro);
                modelo.put("radio", radio);
                //modelo.put("valor_1", sFecha_ini);
                //modelo.put("valor_2", sFecha_fin);
                //modelo.put("fecha1", sFecha_ini);
                //modelo.put("fecha2", sFecha_fin);
                modelo.put("anio", Integer.toString(dFechaini1.getYear() + 1900));
                modelo.put("mes", Integer.toString(dFechaini1.getMonth() + 1));
                modelo.put("dia", Integer.toString(dFechaini1.getDate()));
                return new ModelAndView(new VerReporteSpam3PDF(), modelo);
            }
        }

        if ("Imprimir".equals(accion)) {
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
            String id_seguro = request.getParameter("id_seguro");
            if (("".equals(diai)) || ("".equals(mesi)) || ("".equals(anioi))) {
                return new ModelAndView("administrarhistoriales/EconomicoPacientes", modelo);
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
                Date dFechafin2 = new Date(iAnio2, iMes2, iDia2, iHor2, iMin2, 59);

                Historiales dato = new Historiales();
                dato.setFecha_ini(dFechaini1);
                dato.setFecha_fin(dFechafin2);
                dato.setInternado(Integer.parseInt(radio));
                if ("0".equals(id_seguro)) {
                    dato.setExpedido("E");
                    modelo.put("seguro", "Pacientes Externos");
                } else {
                    dato.setId_seguro(Integer.parseInt(id_seguro));
                    Seguros buscarseguro = this.mi.getDatosSeguro(Integer.parseInt(id_seguro)); // saca un registro a ser modificado
                    modelo.put("seguro", buscarseguro.getSeguro());
                }

                List listarAtendidos = this.mi.getResumenEconomico(dato);
                modelo.put("milistaAten", listarAtendidos);

                if ("4".equals(radio)) {
                    dato.setAccion("T");
                    List listarAtendidos2 = this.mi.getResumenEconomicoTot(dato);
                    modelo.put("milistaAten", listarAtendidos2);
                }

                modelo.put("id_seguro", id_seguro);
                modelo.put("radio", radio);
                modelo.put("dato", cliente);
                modelo.put("fecha1", format(dFechaini1, "dd/MM/yyyy"));
                modelo.put("fecha2", format(dFechafin2, "dd/MM/yyyy"));

                return new ModelAndView(new VerReporteSpam1PDF(), modelo);
            }
        }

        modelo.put("radio", radio);

        return new ModelAndView("administrarhistoriales/EconomicoPacientes", modelo);
    }
}
