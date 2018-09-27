package org.ayaic.web.administrarhistoriales;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Camas;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Cuadernos;
import org.ayaic.domain.Detalle;
import org.ayaic.domain.Historiales;
import org.ayaic.domain.Localidades;
import org.ayaic.domain.Medicamentos;
import org.ayaic.domain.Prestaciones;
import org.ayaic.domain.Recetas;
import org.ayaic.domain.Salas;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControlCalidadControlador {

    private final MiFacade mi;

    public ControlCalidadControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/ControlCalidad.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String id_paciente = request.getParameter("id_paciente");
        String accion = request.getParameter("accion");
        String accion1 = request.getParameter("accion1");
        String valor = request.getParameter("valor");
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

        //Recuperamos variables del jsp
        String sId_estado = request.getParameter("id_estado");
        String sAccion = request.getParameter("boton");
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

        Localidades local = new Localidades();
        List Estab1 = mi.getListarEsta(local);
        Localidades datoestab = (Localidades) Estab1.get(0);
        modelo.put("codesta", Integer.toString(datoestab.getCod_esta()));
        modelo.put("idrol2", Integer.toString(cliente.getId_rol2()));
        modelo.put("dato", cliente);

        if ("Censo Diario".equals(accion)) {
            Salas dsala = new Salas();
            dsala.setTipo("P");   ////getListarSalaPiso
            dsala.setCod_esta(cliente.getCod_esta());
            List listarSalas = this.mi.getListarSalaPiso(dsala);
            modelo.put("listarSalas", listarSalas);
            return new ModelAndView("administrarcuadernos/BuscarCenso", modelo);
        }

        if ("Buscar Censo".equals(accion)) {
            String mes = request.getParameter("mes");
            String anio = request.getParameter("anio");
            String sala = request.getParameter("id_sala");

            Cuadernos datoc = new Cuadernos();
            datoc.setCod_esta(cliente.getCod_esta());  ///16-01-2016
            datoc.setId_historial(Integer.parseInt(mes));
            datoc.setId_historia(Integer.parseInt(anio));
            datoc.setId_laboratorio(Integer.parseInt(sala));
            List listarcenso = this.mi.getCensoDiario(datoc);
            //Cuadernos ListaIndi = (Cuadernos) listarIndi.get(0);
            Camas lcama = new Camas();
            lcama.setId_sala(Integer.parseInt(sala));
            lcama.setTipo("T");
            List listarCama = this.mi.getListarCamasTotal(lcama);
            modelo.put("listarCama", listarCama);
            Salas dsala = new Salas();
            dsala.setId_piso(0);
            List listarSalas = this.mi.getListarSalasLibres(dsala);
            //List listarSalas = this.mi.getListarSalasLibres();
            modelo.put("listarSalas", listarSalas);
            modelo.put("ListaCenso", listarcenso);
            modelo.put("mes", mes);
            modelo.put("anio", anio);
            modelo.put("sala", sala);
            return new ModelAndView(new ListarCensoPDF(), modelo);
        }

        if ("Produccion/Medico".equals(accion)) {
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
                return new ModelAndView("administrarcuadernos/ListarLibros", modelo);
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

                Cuadernos dato = new Cuadernos();

                dato.setFecha_ini(dFechaini1);
                dato.setFecha_fin(dFechafin1);
                dato.setCod_esta(cliente.getCod_esta());
                dato.setTipoconsulta("P");
                dato.setAccion("P");

                if (cliente.getId_rol() == 36) {
                    dato.setTipoconsulta("U"); ////setCrearProduccionEmerg
                }

                this.mi.setCrearProduccion(dato); /////setCrearProduccion

                List listaCobros = this.mi.getReporteProduccion(dato);  ////getReporteProduccion
                modelo.put("listaCobros", listaCobros);
                modelo.put("dFechafin1", anioi + "-" + mesi + "-" + diai + " " + horai + ":" + minutoi);
                modelo.put("dFechafin1", aniof + "-" + mesf + "-" + diaf + " " + horaf + ":" + minutof);

                return new ModelAndView(new VerReporteProduccionPDF(), modelo);
            }
        }

        if ("Buscar".equals(sAccion)) {
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
                return new ModelAndView("administrarhistoriales/ReporteResumen", modelo);
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

                Historiales dato = new Historiales();
                Cuadernos datoc = new Cuadernos();
                datoc.setCod_esta(cliente.getCod_esta());  ///16-01-2016
                Recetas dator = new Recetas();
                Detalle detalle = new Detalle();
                Prestaciones prestac = new Prestaciones();
                prestac.setCod_esta(cliente.getCod_esta());
                
                prestac.setFecha_ini(dFechaini1);
                prestac.setFecha_fin(dFechafin1);
                dato.setFecha_ini(dFechaini1);
                dato.setFecha_fin(dFechafin1);
                datoc.setFecha_ini(dFechaini1);
                datoc.setFecha_fin(dFechafin1);
                dator.setFecha_ini(dFechaini1);
                dator.setFecha_fin(dFechafin1);
                detalle.setFecha_ini(dFechaini1);
                detalle.setFecha_fin(dFechafin1);
                dato.setCod_esta(cliente.getCod_esta());
                datoc.setCod_esta(cliente.getCod_esta());
                dator.setCod_esta(cliente.getCod_esta());
                detalle.setCod_esta(cliente.getCod_esta());

                List listas = this.mi.getListarHistoriaGen(dato);
                modelo.put("milista", listas);

                List listarAtendidos = this.mi.getAtendidosGeneral(dato);
                modelo.put("milistaAten", listarAtendidos);

                List listarSeguros = this.mi.getListarSeguros("A");
                modelo.put("listaPacAfi", listarSeguros);

                List listaPre = this.mi.getPrestacionGen(prestac);
                modelo.put("milistapre", listaPre);

                List listaC1 = this.mi.getCuadernoC1(datoc);
                modelo.put("listaC1", listaC1);
                List listaC2 = this.mi.getCuadernoC2(datoc);
                modelo.put("listaC2", listaC2);
                List listaC3 = this.mi.getCuadernoC3(datoc);
                modelo.put("listaC3", listaC3);
                List listaC4 = this.mi.getCuadernoC4(datoc);
                modelo.put("listaC4", listaC4);
                List listaC5 = this.mi.getCuadernoC5(datoc);
                modelo.put("listaC5", listaC5);
                List listaC6 = this.mi.getCuadernoC6(datoc);
                modelo.put("listaC6", listaC6);
                List listaC7 = this.mi.getCuadernoC7(datoc);
                modelo.put("listaC7", listaC7);
                List listaV = this.mi.getCuadernoV(datoc);
                modelo.put("listaV", listaV);
                List listaLab = this.mi.getLabGen(datoc);
                modelo.put("listaLab", listaLab);

                List listarMed = this.mi.getListaRecetaGen(dator);
                modelo.put("listarMed", listarMed);

                List listarCostos = this.mi.getListarDetalleGen(detalle);
                modelo.put("listarCostos", listarCostos);

                return new ModelAndView("administrarhistoriales/Resumen", modelo);
            }
        }

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
            return new ModelAndView("administrarhistoriales/ReporteResumen", modelo);
        } else {
            if (anioi == null && mesi == null && diai == null && horai == null && minutoi == null && aniof == null && mesf == null && diaf == null && horaf == null && minutof == null) {
                diai = "0";
                mesi = "0";
                anioi = "0";
                horai = "0";
                minutoi = "0";
                diaf = "0";
                mesf = "0";
                aniof = "0";
                horaf = "0";
                minutof = "0";
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

            Cuadernos cali = new Cuadernos();
            cali.setCod_esta(cliente.getCod_esta());  ///16-01-2016
            cali.setFecha_ini(dFechaini1);
            cali.setFecha_fin(dFechafin1);

            if ("O80>C.2".equals(accion)) {
                List lista = this.mi.getO80_C2(cali);
                modelo.put("calidad", lista);
                return new ModelAndView("administrarcuadernos/Calidad", modelo);
            }
            if ("PC42>C.2".equals(accion)) {
                List lista = this.mi.getPC42_C2(cali);
                modelo.put("calidad", lista);
                return new ModelAndView("administrarcuadernos/Calidad", modelo);
            }
            if ("PC64>C.2".equals(accion)) {
                List lista = this.mi.getPC64_C2(cali);
                modelo.put("calidad", lista);
                return new ModelAndView("administrarcuadernos/Calidad", modelo);
            }
            if ("Z34>C.2".equals(accion)) {
                List lista = this.mi.getZ34_C2(cali);
                modelo.put("calidad", lista);
                return new ModelAndView("administrarcuadernos/Calidad", modelo);
            }
            if ("Z39>C.2".equals(accion)) {
                List lista = this.mi.getZ39_C2(cali);
                modelo.put("calidad", lista);
                return new ModelAndView("administrarcuadernos/Calidad", modelo);
            }
            if ("Z301>C.3".equals(accion)) {
                List lista = this.mi.getZ301_C3(cali);
                modelo.put("calidad", lista);
                return new ModelAndView("administrarcuadernos/Calidad", modelo);
            }
            if ("Z305>C.3".equals(accion)) {
                List lista = this.mi.getZ305_C3(cali);
                modelo.put("calidad", lista);
                return new ModelAndView("administrarcuadernos/Calidad", modelo);
            }
            if ("Z124>C.3".equals(accion)) {
                List lista = this.mi.getZ124_C3(cali);
                modelo.put("calidad", lista);
                return new ModelAndView("administrarcuadernos/Calidad", modelo);
            }
            if ("C.2>O80".equals(accion)) {
                List lista = this.mi.getC2_O80(cali);
                modelo.put("calidad", lista);
                return new ModelAndView("administrarcuadernos/Calidad", modelo);
            }
            if ("C.2>PC42".equals(accion)) {
                List lista = this.mi.getC2_PC42(cali);
                modelo.put("calidad", lista);
                return new ModelAndView("administrarcuadernos/Calidad", modelo);
            }
            if ("C.2>PC64".equals(accion)) {
                List lista = this.mi.getC2_PC64(cali);
                modelo.put("calidad", lista);
                return new ModelAndView("administrarcuadernos/Calidad", modelo);
            }
            if ("C.2>Z34".equals(accion)) {
                List lista = this.mi.getC2_Z34(cali);
                modelo.put("calidad", lista);
                return new ModelAndView("administrarcuadernos/Calidad", modelo);
            }
            if ("C.2>Z39".equals(accion)) {
                List lista = this.mi.getC2_Z39(cali);
                modelo.put("calidad", lista);
                return new ModelAndView("administrarcuadernos/Calidad", modelo);
            }
            if ("C.3>Z301".equals(accion)) {
                List lista = this.mi.getC3_Z301(cali);
                modelo.put("calidad", lista);
                return new ModelAndView("administrarcuadernos/Calidad", modelo);
            }
            if ("C.3>Z305".equals(accion)) {
                List lista = this.mi.getC3_Z305(cali);
                modelo.put("calidad", lista);
                return new ModelAndView("administrarcuadernos/Calidad", modelo);
            }
            if ("C.3>Z124".equals(accion)) {
                List lista = this.mi.getC3_Z124(cali);
                modelo.put("calidad", lista);
                return new ModelAndView("administrarcuadernos/Calidad", modelo);
            }
            if ("Z301>DIU".equals(accion)) {
                List lista = this.mi.getZ301_DIU(cali);
                modelo.put("calidad", lista);
                return new ModelAndView("administrarcuadernos/Calidad", modelo);
            }
            if ("PC23>C.3".equals(accion)) {
                List lista = this.mi.getPC23_C3(cali);
                modelo.put("calidad", lista);
                return new ModelAndView("administrarcuadernos/Calidad", modelo);
            }
            if ("C.3>PC23".equals(accion)) {
                List lista = this.mi.getC3_PC23(cali);
                modelo.put("calidad", lista);
                return new ModelAndView("administrarcuadernos/Calidad", modelo);
            }
            if ("PC23>DEPO".equals(accion)) {
                List lista = this.mi.getPC23_DEPO(cali);
                modelo.put("calidad", lista);
                return new ModelAndView("administrarcuadernos/Calidad", modelo);
            }

            if ("ResumenMedicamento".equals(accion)) {
                return new ModelAndView("Aviso", "mensaje", "Faltan Introducir Datos");
            }

            if ("ResumenGralMedicamentos".equals(accion)) {
                String id_medicamento = request.getParameter("id_medicamento");

                Recetas dator = new Recetas();
                dator.setFecha_ini(dFechaini1);
                dator.setFecha_fin(dFechafin1);
                dator.setId_medicamento(Integer.parseInt(id_medicamento));
                dator.setId_estado("R");/////getKardexResumenXMedica
                List listarKardexUsua = this.mi.getKardexResumenXMedica(dator);
                modelo.put("listarKardex", listarKardexUsua);
                dator.setId_estado("S");/////getKardexResumenXMedicaSaldo
                List listarKardexUsuaSal = this.mi.getKardexResumenXMedicaSaldo(dator);
                modelo.put("listarKardexSal", listarKardexUsuaSal);
                //modelo.put("dFechafin1",sFecha_ini);
                //modelo.put("dFechafin2",sFecha_fin); 
                Medicamentos medic = new Medicamentos();
                medic.setId_medicamento(Integer.parseInt(id_medicamento));
                medic.setCodigo(cliente.getCod_esta());
                medic.setCod_esta(cliente.getCod_esta());
                medic.setId_farmacia(cliente.getId_farmacia());
                if (cliente.getId_rol2() == 29 || cliente.getId_rol2() == 30 || cliente.getId_rol2() == 31 || cliente.getId_rol2() == 32) {////29 carmelo
                    medic.setExpedido("U");    ////30 almacen   , 31 admin Regional  , 32 admin farmacialocal
                }
                Medicamentos buscarMedicamento = this.mi.getDatosMedicamento(medic);
                modelo.put("medica", buscarMedicamento);
                return new ModelAndView(new ListarResumenMedicaPDF(), modelo);
            }

            if ("Segun Medicamento".equals(accion)) {
                Medicamentos dato = new Medicamentos();
                dato.setId_farmacia(cliente.getId_farmacia());
                dato.setCodigo(-1);
                List listarMedicamentos = this.mi.getListarMedicamentosMicro(dato);
                modelo.put("listarMedicamentos", listarMedicamentos);
                return new ModelAndView("administrarcuadernos/Micronutriente", modelo);
            }
            if ("Resumen Micronutriente".equals(accion) || "Impresion".equals(accion)) {
                String id_medicamento = request.getParameter("id_medicamento");
                Historiales dato = new Historiales();
                Cuadernos datoc = new Cuadernos();
                Recetas dator = new Recetas();
                Detalle detalle = new Detalle();
                Prestaciones prestac = new Prestaciones();
                prestac.setCod_esta(cliente.getCod_esta());
                
                prestac.setFecha_ini(dFechaini1);
                prestac.setFecha_fin(dFechafin1);
                dato.setFecha_ini(dFechaini1);
                dato.setFecha_fin(dFechafin1);
                datoc.setFecha_ini(dFechaini1);
                datoc.setFecha_fin(dFechafin1);
                dator.setFecha_ini(dFechaini1);
                dator.setFecha_fin(dFechafin1);
                detalle.setFecha_ini(dFechaini1);
                detalle.setFecha_fin(dFechafin1);
                dato.setCod_esta(cliente.getCod_esta());
                datoc.setCod_esta(cliente.getCod_esta());
                dator.setCod_esta(cliente.getCod_esta());
                detalle.setCod_esta(cliente.getCod_esta());

                if ("Impresion".equals(accion)) {
                    Historiales datoKp = new Historiales();
                    datoKp.setFecha_ini(dFechaini1);
                    datoKp.setFecha_fin(dFechafin1);
                    datoKp.setId_localidad(cliente.getCod_esta());
                    datoKp.setId_cargo(Integer.parseInt(id_medicamento));
                    List listaPac = this.mi.getListarKardexPaciente(datoKp);
                    modelo.put("listaPacientes", listaPac);
                    Medicamentos medic = new Medicamentos();
                    medic.setId_medicamento(Integer.parseInt(id_medicamento));
                    medic.setCodigo(cliente.getCod_esta());
                    medic.setCod_esta(cliente.getCod_esta());
                    medic.setId_farmacia(cliente.getId_farmacia());
                    if (cliente.getId_rol2() == 31) {
                        medic.setExpedido("U");
                    }
                    Medicamentos buscarMedicamento = this.mi.getDatosMedicamento(medic);
                    modelo.put("medica", buscarMedicamento);
                    return new ModelAndView(new ListarMicronutrientesPDF(), modelo);
                }

                dato.setAccion("M");
                dato.setId_cargo(Integer.parseInt(id_medicamento));
                List listas = this.mi.getListarHistoriaMicro(dato);
                modelo.put("milista", listas);
                List listarAtendidos = this.mi.getAtendidosMicronutriente(dato);  /////M  getAtendidosMicronutriente
                modelo.put("milistaAten", listarAtendidos);

                List listarSeguros = this.mi.getListarSeguros("A");
                modelo.put("listaPacAfi", listarSeguros);

                List listaPre = this.mi.getPrestacionGen(prestac);
                modelo.put("milistapre", listaPre);

                List listaC1 = this.mi.getCuadernoC1(datoc);
                modelo.put("listaC1", listaC1);
                List listaC2 = this.mi.getCuadernoC2(datoc);
                modelo.put("listaC2", listaC2);
                List listaC3 = this.mi.getCuadernoC3(datoc);
                modelo.put("listaC3", listaC3);
                List listaC4 = this.mi.getCuadernoC4(datoc);
                modelo.put("listaC4", listaC4);
                List listaV = this.mi.getCuadernoV(datoc);
                modelo.put("listaV", listaV);

                List listarMed = this.mi.getListaRecetaGen(dator);
                modelo.put("listarMed", listarMed);

                dator.setId_factura(Integer.parseInt(id_medicamento));
                List listarKardex = this.mi.getListarKardexMicro(dator);
                modelo.put("listarKardex", listarKardex);
                Medicamentos datome = new Medicamentos();
                datome.setCodigo(-1);
                List listarMedicamentos = this.mi.getListarMedicamentosMicro(datome);
                modelo.put("listarMedicamentos", listarMedicamentos);
                modelo.put("id_medicamento", id_medicamento);
                return new ModelAndView("administrarcuadernos/Micronutriente", modelo);
            }
            if ("Indicadores Hospitalarios".equals(accion)) {
                List listarIndi = this.mi.getIndicadores();
                Cuadernos ListaIndi = (Cuadernos) listarIndi.get(0);
                modelo.put("ListaIndi", ListaIndi);
                return new ModelAndView(new ListarIndicadoresPDF(), modelo);
            }

        }
        return new ModelAndView("administrarhistoriales/ReporteResumen", modelo);
    }
}
