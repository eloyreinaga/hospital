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
public class ReporteSnis302Controlador {

    private final MiFacade mi;

    public ReporteSnis302Controlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/ReporteSnis.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
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
        String sAccion = request.getParameter("botonvv");
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

        if ("Snis 302".equals(sAccion)) {
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
                return new ModelAndView("administrarmedicamentos/BuscarPedidos", modelo);
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

                Cuadernos dato = new Cuadernos();

                dato.setFecha_ini(dFechaini1);
                dato.setFecha_fin(dFechafin1);
                dato.setCod_esta(cliente.getCod_esta());

                List lista302 = this.mi.getVerSnis302(dato);
                modelo.put("lista30", lista302);
         
                Cuadernos lista_5 = this.mi.getDatos302_5(dato);
                modelo.put("lista_5", lista_5);
                if(lista_5==null){
                    Cuadernos lista_51 = new Cuadernos();
                    lista_51.setCod_esta(cliente.getCod_esta());  ///16-01-2016
                    lista_51.setSuma1(0);  lista_51.setSuma2(0);  lista_51.setSuma3(0);  lista_51.setSuma4(0);
                    lista_51.setSuma5(0);  lista_51.setSuma6(0);  lista_51.setSuma7(0);  lista_51.setSuma8(0);
                    lista_51.setSuma9(0);  lista_51.setSuma10(0); lista_51.setSuma11(0); lista_51.setSuma12(0);
                    lista_51.setSuma13(0); lista_51.setSuma14(0); lista_51.setSuma15(0); lista_51.setSuma16(0);
                    lista_51.setSuma17(0); lista_51.setSuma18(0); lista_51.setSuma19(0); lista_51.setSuma20(0);
                    lista_51.setSuma21(0); lista_51.setSuma22(0); lista_51.setSuma23(0); lista_51.setSuma24(0);
                    lista_51.setSuma25(0); lista_51.setSuma26(0); lista_51.setSuma27(0); lista_51.setSuma28(0);
                    lista_51.setSuma29(0); lista_51.setSuma30(0); lista_51.setSuma31(0); lista_51.setSuma32(0);
                    lista_51.setSuma33(0); lista_51.setSuma34(0); lista_51.setSuma35(0); lista_51.setSuma36(0);
                    modelo.put("lista_5", lista_51);
                 }
                    Cuadernos listaImc = this.mi.getDatos302imc(dato);
                    modelo.put("listaImc", listaImc);
                if(listaImc==null){
                    Cuadernos listaImc1 = new Cuadernos();
                    listaImc1.setCod_esta(cliente.getCod_esta());  ///16-01-2016
                    listaImc1.setSuma1(0);  listaImc1.setSuma2(0);  listaImc1.setSuma3(0);  listaImc1.setSuma4(0);
                    listaImc1.setSuma5(0);  listaImc1.setSuma6(0);  listaImc1.setSuma7(0);  listaImc1.setSuma8(0);
                    modelo.put("listaImc", listaImc1);
                }

                return new ModelAndView(new VerReporteSnis302PDF(), modelo);
            }
        }
        return new ModelAndView("administrarcuadernos/ReporteSnis", modelo);
    }
}
