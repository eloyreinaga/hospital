package org.ayaic.web.administrarcuadernos;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Cuadernos;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GraficasControlador {

    private final MiFacade mi;

    public GraficasControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/Graficas.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String[] dias = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
        String[] meses = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        String[] horas = {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"};
        String[] minutos = {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"};
        Date fechaact = new Date();
        Date fecha1 = new Date();
        Date fecha2 = new Date();
        int anioq = fecha1.getYear() + 1900;
        String[] anios = {Integer.toString(anioq), Integer.toString(anioq - 1), Integer.toString(anioq - 2), Integer.toString(anioq - 3), Integer.toString(anioq - 4), Integer.toString(anioq - 5), Integer.toString(anioq - 6), Integer.toString(anioq - 7), Integer.toString(anioq - 8), Integer.toString(anioq - 9), Integer.toString(anioq - 10)};
        String diaq1 = request.getParameter("diaq1");
        String mesq1 = request.getParameter("mesq1");
        String ip = request.getRemoteAddr();
        String host = request.getRemoteHost();

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
        modelo.put("fecha1", fecha1);
        modelo.put("fecha2", fecha2);
        modelo.put("rol", Integer.toString(cliente.getId_rol()));

        if ("NINOS".equals(sAccion) || "Poblacion Atendida Especialidad".equals(sAccion) || "Produccion Servicios".equals(sAccion) || "Vacunacion".equals(sAccion) || "Poblacion Atendida".equals(sAccion)) {
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
                return new ModelAndView("administrarcuadernos/Graficas1", modelo);
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
                modelo.put("fecha1", dFechaini1);
                modelo.put("fecha2", dFechafin1);

                Cuadernos dato = new Cuadernos();
                dato.setFecha_ini(dFechaini1);
                dato.setFecha_fin(dFechafin1);
                dato.setCod_esta(cliente.getCod_esta());

                if ("Produccion Servicios".equals(sAccion)) {
                    dato.setTipo("2");
                    // try{
                    dato.setAccion("R");
                    if (cliente.getId_rol() == 36) {
                        dato.setAccion("9");
                    }
                    List listaCobros = this.mi.getReporteProduccion2(dato);
                    modelo.put("grafica1", listaCobros);
                    //}catch (Exception e){ 
                    //   return new ModelAndView("","mensaje","NO existe datos para estas fechas");
                    //}   

                    return new ModelAndView("administrarcuadernos/Graficas3", modelo);
                }

                if ("Poblacion Atendida".equals(sAccion)) {
                    try {
                        List listag1 = this.mi.getGrafica1(dato);  ////getGrafica3
                        modelo.put("grafica1", listag1);
                        if (cliente.getId_rol() == 36) {
                            dato.setTipo("3");
                            List listag3 = this.mi.getGrafica3(dato);  ////getGrafica3
                            modelo.put("grafica1", listag3);
                        }

                    } catch (Exception e) {
                        return new ModelAndView("Aviso", "mensaje", "NO existe datos para estas fechas");
                    }
                    return new ModelAndView("administrarcuadernos/Graficas1", modelo);
                }

                if ("NINOS".equals(sAccion)) {
                    return new ModelAndView("administrarcuadernos/Graficas7", modelo);
                }

                if ("Poblacion Atendida Especialidad".equals(sAccion)) {
                    try {
                        List listag1 = this.mi.getGrafica1(dato);
                        modelo.put("grafica1", listag1);
                        if (cliente.getId_rol() == 36) {
                            dato.setTipo("2");
                            List listag2 = this.mi.getGrafica2(dato);  ////getGrafica2
                            modelo.put("grafica1", listag2);
                        }

                    } catch (Exception e) {
                        return new ModelAndView("Aviso", "mensaje", "NO existe datos para estas fechas");
                    }
                    return new ModelAndView("administrarcuadernos/Graficas4", modelo);
                }

                if ("Vacunacion".equals(sAccion)) {
                    dato.setTipo("4");
                    try {
                        List listag1 = this.mi.getGrafica4(dato); ////getGrafica4
                        modelo.put("grafica1", listag1);
                    } catch (Exception e) {
                        return new ModelAndView("Aviso", "mensaje", "NO existe datos vacunacion para estas fechas");
                    }
                    return new ModelAndView("administrarcuadernos/Graficas2", modelo);
                }

            }
        }
        return new ModelAndView("administrarcuadernos/Graficas1", modelo);
    }
}
