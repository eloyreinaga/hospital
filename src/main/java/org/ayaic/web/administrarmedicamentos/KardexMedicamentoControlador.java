package org.ayaic.web.administrarmedicamentos;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Calendar;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Consultorios;
import org.ayaic.domain.Localidades;
import org.ayaic.domain.Medicamentos;
import org.ayaic.domain.Personas;
import org.ayaic.domain.Recetas;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class KardexMedicamentoControlador {

    private final MiFacade mi;

    public KardexMedicamentoControlador(MiFacade mi) {
        this.mi = mi;
    }
    
    @RequestMapping("/KardexMedicamento.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String id_medicamento = request.getParameter("id_medicamento");
        String cod_esta = request.getParameter("cod_esta");
        String id_farmacia = request.getParameter("id_farmacia");
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

        Localidades local = new Localidades();
        List Estab1 = this.mi.getListarEsta(local);
        Localidades datoestab = (Localidades) Estab1.get(0);
        modelo.put("datoestab", datoestab);
        modelo.put("seguro_estab", Integer.toString(cliente.getInst_id()));

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
        modelo.put("id_medicamento", id_medicamento);
        modelo.put("id_farmacia", id_farmacia);
        modelo.put("cod_esta", cod_esta);

        Medicamentos medic = new Medicamentos();
        medic.setId_medicamento(Integer.parseInt(id_medicamento));
        medic.setCodigo(cliente.getCod_esta());
        medic.setCod_esta(cliente.getCod_esta());
        medic.setId_farmacia(cliente.getId_farmacia());
        medic.setExpedido("B");    /////getDatosMedicamentoB
        if (!"".equals(cod_esta) && cod_esta != null) {///
            medic.setCod_esta(Integer.parseInt(cod_esta));
        }
        if (!"".equals(id_farmacia) && id_farmacia != null) {
            medic.setId_farmacia(Integer.parseInt(id_farmacia));
        }
        Medicamentos buscarMedicamentos = this.mi.getDatosMedicamentoB(medic);
        modelo.put("dato", buscarMedicamentos);
        ///Listar Personas
        List listarPersonas = this.mi.getListarPersonas(persona);
        modelo.put("listaPersonas", listarPersonas);

        medic.setExpedido("I");    /////getDatosItem
        Medicamentos datoItem = this.mi.getDatosItem(medic);
        modelo.put("datoItem", datoItem);

        List listarprog = this.mi.getListarProgramas(medic);
        modelo.put("listarProg", listarprog);
        String sId_estado = request.getParameter("id_estado");
        String sAccion = request.getParameter("boton");
        modelo.put("Farma", Integer.toString(cliente.getCod_esta()) + ":" + Integer.toString(cliente.getId_farmacia()) + ":  " + cliente.getFarmacia());
        modelo.put("cliente", cliente);

        Recetas datoreceta = new Recetas();
        datoreceta.setId_medicamento(Integer.parseInt(id_medicamento));
        datoreceta.setCod_esta(cliente.getCod_esta());  /////23-01-2014
        datoreceta.setId_farmacia(cliente.getId_farmacia());/////16-05-2015
        if (!"".equals(cod_esta) && cod_esta != null) {
            datoreceta.setCod_esta(Integer.parseInt(cod_esta));
        }
        if (!"".equals(id_farmacia) && id_farmacia != null) {
            datoreceta.setId_farmacia(Integer.parseInt(id_farmacia));
        }

        if ("Dispensado por Especialidad".equals(sAccion) || "Dispensado por Medicos".equals(sAccion) || "Kardex Dispensado Detallado".equals(sAccion) || "TODOS s/0".equals(sAccion) || "SUMI s/0".equals(sAccion) || "VENTAS s/0".equals(sAccion) || "PROGRAMAS s/0".equals(sAccion)) {
            String sFecha_ini = request.getParameter("valor_1");
            String sFecha_fin = request.getParameter("valor_2");
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
                return new ModelAndView("administrarmedicamentos/KardexMedicamento", modelo);
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

                modelo.put("mes", Integer.toString(iMes2 + 1));
                modelo.put("annio", Integer.toString(iAnio2 + 1900));

                datoreceta.setFecha_ini(dFechaini1);
                datoreceta.setFecha_fin(dFechafin1);
                datoreceta.setExpedido("%");
                datoreceta.setId_estado("S");

                if ("Kardex Dispensado Detallado".equals(sAccion)) {
                    datoreceta.setId_estado("4");
                    List listarKardexUsua = this.mi.getKardexRestringido4(datoreceta);
                    modelo.put("datokardex", listarKardexUsua);
                    if (cliente.getId_rol2() == 30) {
                        datoreceta.setId_estado("5");  ///getKardexRestringido5
                        List listarKardexUsua2 = this.mi.getKardexRestringido5(datoreceta);
                        modelo.put("datokardex", listarKardexUsua2);
                    }

                    Personas buscarEmpleado = this.mi.getDatosPersona(cliente.getId_persona());
                    modelo.put("persona", buscarEmpleado);
                    modelo.put("dFechafin1", anioi + "-" + mesi + "-" + diai + " " + horai + " " + minutoi);
                    modelo.put("dFechafin2", aniof + "-" + mesf + "-" + diaf + " " + horaf + " " + minutof);
                    return new ModelAndView(new ListarKardexPsicoPDF(), modelo);
                }

                if ("Dispensado por Medicos".equals(sAccion)) {
                    List listarKardexUsua = this.mi.getKardexRestringido6(datoreceta);
                    modelo.put("listarKardexRemi", listarKardexUsua);
                    Personas buscarEmpleado = this.mi.getDatosPersona(cliente.getId_persona());
                    modelo.put("persona", buscarEmpleado);
                    modelo.put("dFechafin1", anioi + "-" + mesi + "-" + diai + " " + horai + " " + minutoi);
                    modelo.put("dFechafin2", aniof + "-" + mesf + "-" + diaf + " " + horaf + " " + minutof);
                    return new ModelAndView(new VerResumenXmedicoPDF(), modelo);
                }
                if ("Dispensado por Especialidad".equals(sAccion)) {
                    List listarKardexUsua = this.mi.getKardexRestringido7(datoreceta);
                    modelo.put("listarKardexRemi", listarKardexUsua);

                    Personas buscarEmpleado = this.mi.getDatosPersona(cliente.getId_persona());
                    modelo.put("persona", buscarEmpleado);
                    modelo.put("dFechafin1", anioi + "-" + mesi + "-" + diai + " " + horai + " " + minutoi);
                    modelo.put("dFechafin2", aniof + "-" + mesf + "-" + diaf + " " + horaf + " " + minutof);
                    return new ModelAndView(new VerResumenXmedicoPDF(), modelo);
                }

                if ("SUMI s/0".equals(sAccion)) {
                    datoreceta.setExpedido("S");
                }
                if ("VENTAS s/0".equals(sAccion)) {
                    datoreceta.setExpedido("V");
                }
                if ("PROGRAMAS s/0".equals(sAccion)) {
                    datoreceta.setExpedido("P");
                }

                List listarKardexMedicamento = this.mi.getListarKardexMedicamentoSin0(datoreceta);
                modelo.put("datos", listarKardexMedicamento);
                modelo.put("sw1", request.getParameter("sw1"));

                modelo.put("id_medicamento", id_medicamento);
                return new ModelAndView(new ListarConsultaPDF(), modelo);
            }
        }

        if ("T General s/0".equals(sAccion) || "S General s/0".equals(sAccion) || "V General s/0".equals(sAccion) || "P General s/0".equals(sAccion)) {
            String sFecha_ini = request.getParameter("valor_1");
            String sFecha_fin = request.getParameter("valor_2");
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
                return new ModelAndView("administrarmedicamentos/KardexMedicamento", modelo);
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
                modelo.put("mes", Integer.toString(iMes2 + 1));
                modelo.put("annio", Integer.toString(iAnio2 + 1900));

                datoreceta.setFecha_ini(dFechaini1);
                datoreceta.setFecha_fin(dFechafin1);
                datoreceta.setExpedido("%");
                datoreceta.setId_estado("S");
                if ("S General s/0".equals(sAccion)) {
                    datoreceta.setExpedido("S");
                }
                if ("V General s/0".equals(sAccion)) {
                    datoreceta.setExpedido("V");
                }
                if ("P General s/0".equals(sAccion)) {
                    datoreceta.setExpedido("P");
                }

                List listarKardexMedicamento = this.mi.getListarKardexMedicamentoSin0(datoreceta);
                modelo.put("datos", listarKardexMedicamento);
                modelo.put("sw1", request.getParameter("sw1"));

                int diferenciaEnDias = 1;
                Date fechaActual = Calendar.getInstance().getTime();
                //long tiempoActual = fechaActual.getTime();
                long tiempoActual = dFechaini1.getTime();
                long unDia = diferenciaEnDias * 24 * 60 * 60 * 1000;
                Date fechaAyer = new Date(tiempoActual - unDia);
                modelo.put("fecha", Integer.toString(fechaAyer.getDate()) + "/" + Integer.toString(fechaAyer.getMonth() + 1) + "/" + Integer.toString(fechaAyer.getYear() + 1900));
                modelo.put("id_medicamento", id_medicamento);
                return new ModelAndView(new ListarConsulta2PDF(), modelo);
            }
        }
        if ("TODOS solo/0".equals(sAccion) || "SUMI solo/0".equals(sAccion) || "VENTAS solo/0".equals(sAccion) || "PROGRAMAS solo/0".equals(sAccion)) {
            String sFecha_ini = request.getParameter("valor_1");
            String sFecha_fin = request.getParameter("valor_2");
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
                return new ModelAndView("administrarmedicamentos/KardexMedicamento", modelo);
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

                modelo.put("mes", Integer.toString(iMes2 + 1));
                modelo.put("annio", Integer.toString(iAnio2 + 1900));

                datoreceta.setFecha_ini(dFechaini1);
                datoreceta.setFecha_fin(dFechafin1);
                datoreceta.setExpedido("%");
                datoreceta.setId_estado("C");
                if ("SUMI solo/0".equals(sAccion)) {
                    datoreceta.setExpedido("S");
                }
                if ("VENTAS solo/0".equals(sAccion)) {
                    datoreceta.setExpedido("V");
                }
                if ("PROGRAMAS solo/0".equals(sAccion)) {
                    datoreceta.setExpedido("P");
                }

                List listarKardexMedicamento = this.mi.getListarKardexMedicamentoSolo0(datoreceta);
                modelo.put("datos", listarKardexMedicamento);
                modelo.put("sw1", request.getParameter("sw1"));
                modelo.put("id_medicamento", id_medicamento);
                return new ModelAndView(new ListarConsultaPDF(), modelo);
            }
        }

        if ("TODOS c/0".equals(sAccion) || "TODOS c/0".equals(sAccion) || "SUMI c/0".equals(sAccion) || "VENTAS c/0".equals(sAccion) || "PROGRAMAS c/0".equals(sAccion)) {
            String sFecha_ini = request.getParameter("valor_1");
            String sFecha_fin = request.getParameter("valor_2");
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
                return new ModelAndView("administrarmedicamentos/KardexMedicamento", modelo);
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

                modelo.put("mes", Integer.toString(iMes2 + 1));
                modelo.put("annio", Integer.toString(iAnio2 + 1900));

                datoreceta.setFecha_ini(dFechaini1);
                datoreceta.setFecha_fin(dFechafin1);
                datoreceta.setExpedido("%");
                datoreceta.setId_estado("%");
                if ("SUMI c/0".equals(sAccion)) {
                    datoreceta.setExpedido("S");
                }
                if ("VENTAS c/0".equals(sAccion)) {
                    datoreceta.setExpedido("V");
                }
                if ("PROGRAMAS c/0".equals(sAccion)) {
                    datoreceta.setExpedido("P");
                }

                List listarKardexMedicamento = this.mi.getListarKardexMedicamento(datoreceta);
                modelo.put("datos", listarKardexMedicamento);

                modelo.put("sw1", request.getParameter("sw1"));
                int diferenciaEnDias = 1;
                Date fechaActual = Calendar.getInstance().getTime();
                //long tiempoActual = fechaActual.getTime();
                long tiempoActual = dFechaini1.getTime();
                long unDia = diferenciaEnDias * 24 * 60 * 60 * 1000;
                Date fechaAyer = new Date(tiempoActual - unDia);
                modelo.put("fecha", Integer.toString(fechaAyer.getDate()) + "/" + Integer.toString(fechaAyer.getMonth() + 1) + "/" + Integer.toString(fechaAyer.getYear() + 1900));
                modelo.put("id_medicamento", id_medicamento);
                if ("I".equals(cliente.getArea())) {
                    return new ModelAndView(new ListarKardexAlmaPDF(), modelo);
                }
                return new ModelAndView(new ListarConsultaPDF(), modelo);
            }
        }

        if ("TODOS E".equals(sAccion) || "SUMI E".equals(sAccion) || "VENTAS E".equals(sAccion) || "PROGRAMAS E".equals(sAccion)) {
            String sFecha_ini = request.getParameter("valor_1");
            String sFecha_fin = request.getParameter("valor_2");
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
                return new ModelAndView("administrarmedicamentos/KardexMedicamento", modelo);
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

                modelo.put("mes", Integer.toString(iMes2 + 1));
                modelo.put("annio", Integer.toString(iAnio2 + 1900));

                datoreceta.setFecha_ini(dFechaini1);
                datoreceta.setFecha_fin(dFechafin1);
                datoreceta.setExpedido("%");
                datoreceta.setId_estado("E");
                if ("SUMI E".equals(sAccion)) {
                    datoreceta.setExpedido("S");
                }
                if ("VENTAS E".equals(sAccion)) {
                    datoreceta.setExpedido("V");
                }
                if ("PROGRAMAS E".equals(sAccion)) {
                    datoreceta.setExpedido("P");
                }

                List listarKardexMedicamento = this.mi.getListarKardexMedicamentoSoloE(datoreceta);
                modelo.put("datos", listarKardexMedicamento);

                modelo.put("sw1", request.getParameter("sw1"));
                modelo.put("id_medicamento", id_medicamento);
                return new ModelAndView(new ListarConsultaPDF(), modelo);
            }
        }

        if ("TODOS S".equals(sAccion) || "SUMI S".equals(sAccion) || "VENTAS S".equals(sAccion) || "PROGRAMAS S".equals(sAccion)) {
            String sFecha_ini = request.getParameter("valor_1");
            String sFecha_fin = request.getParameter("valor_2");
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
                return new ModelAndView("administrarmedicamentos/KardexMedicamento", modelo);
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

                modelo.put("mes", Integer.toString(iMes2 + 1));
                modelo.put("annio", Integer.toString(iAnio2 + 1900));

                datoreceta.setFecha_ini(dFechaini1);
                datoreceta.setFecha_fin(dFechafin1);
                datoreceta.setExpedido("%");
                datoreceta.setId_estado("D");
                if ("SUMI S".equals(sAccion)) {
                    datoreceta.setExpedido("S");
                }
                if ("VENTAS S".equals(sAccion)) {
                    datoreceta.setExpedido("V");
                }
                if ("PROGRAMAS S".equals(sAccion)) {
                    datoreceta.setExpedido("P");
                }

                List listarKardexMedicamento = this.mi.getListarKardexMedicamentoSoloS(datoreceta);
                modelo.put("datos", listarKardexMedicamento);

                modelo.put("sw1", request.getParameter("sw1"));
                modelo.put("id_medicamento", id_medicamento);
                return new ModelAndView(new ListarConsultaPDF(), modelo);
            }
        }

        if ("TODOS A".equals(sAccion) || "SUMI A".equals(sAccion) || "VENTAS A".equals(sAccion) || "PROGRAMAS A".equals(sAccion)) {
            String sFecha_ini = request.getParameter("valor_1");
            String sFecha_fin = request.getParameter("valor_2");
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
                return new ModelAndView("administrarmedicamentos/KardexMedicamento", modelo);
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

                modelo.put("mes", Integer.toString(iMes2 + 1));
                modelo.put("annio", Integer.toString(iAnio2 + 1900));

                datoreceta.setFecha_ini(dFechaini1);
                datoreceta.setFecha_fin(dFechafin1);
                datoreceta.setExpedido("%");
                datoreceta.setId_estado("A");
                if ("SUMI A".equals(sAccion)) {
                    datoreceta.setExpedido("S");
                }
                if ("VENTAS A".equals(sAccion)) {
                    datoreceta.setExpedido("V");
                }
                if ("PROGRAMAS A".equals(sAccion)) {
                    datoreceta.setExpedido("P");
                }

                List listarKardexMedicamento = this.mi.getListarKardexMedicamentoSoloA(datoreceta);
                modelo.put("datos", listarKardexMedicamento);

                modelo.put("sw1", request.getParameter("sw1"));
                modelo.put("id_medicamento", id_medicamento);
                return new ModelAndView(new ListarConsultaPDF(), modelo);
            }
        }

        if ("Segun Programa".equals(sAccion)) {/////23-010-2014
            String sFecha_ini = request.getParameter("valor_1");
            String sFecha_fin = request.getParameter("valor_2");
            String id_programa = request.getParameter("id_programa");
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
                return new ModelAndView("administrarmedicamentos/KardexMedicamento", modelo);
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

                modelo.put("mes", Integer.toString(iMes2 + 1));
                modelo.put("annio", Integer.toString(iAnio2 + 1900));

                datoreceta.setFecha_ini(dFechaini1);
                datoreceta.setFecha_fin(dFechafin1);
                datoreceta.setExpedido("%");
                datoreceta.setId_estado("P");
                datoreceta.setId_programa(Integer.parseInt(id_programa));
                datoreceta.setExpedido("P");/////23-010-2014    getListarKardexProg

                List listarKardexMedicamento = this.mi.getListarKardexProg(datoreceta);
                modelo.put("datos", listarKardexMedicamento);

                modelo.put("sw1", request.getParameter("sw1"));

                modelo.put("id_medicamento", id_medicamento);
                return new ModelAndView(new ListarConsultaPDF(), modelo);
            }
        }

        return new ModelAndView("administrarmedicamentos/KardexMedicamento", modelo);
    }
}
