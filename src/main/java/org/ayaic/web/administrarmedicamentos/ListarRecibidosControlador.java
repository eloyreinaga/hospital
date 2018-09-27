package org.ayaic.web.administrarmedicamentos;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Localidades;
import org.ayaic.domain.Medicamentos;
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.Personas;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ListarRecibidosControlador {

    private final MiFacade mi;

    public ListarRecibidosControlador(MiFacade mi) {
        this.mi = mi;
    }
    
    @RequestMapping("/ListarRecibidos.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        Personas persona = this.mi.getBuscarPersona(cliente.getId_persona()); // saca un registro a ser modificado
        String sAccion = request.getParameter("boton");
        String sFecha_ini = request.getParameter("valor_1");
        String sFecha_fin = request.getParameter("valor_2");
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

        Localidades local = new Localidades();
        List Estab1 = this.mi.getListarEsta(local);
        Localidades datoestab = (Localidades) Estab1.get(0);
        modelo.put("codesta", Integer.toString(datoestab.getCod_esta()));

        if ("Ver Traspasos".equals(sAccion) || "Ver Ajustes(-)".equals(sAccion) || "Ver Ajustes(+)".equals(sAccion) || "Ver Recepcion".equals(sAccion) || "Traspasos".equals(sAccion) || "Entradas-Recepcion".equals(sAccion) || "Ajustes(+)".equals(sAccion) || "Ajustes(-)".equals(sAccion)) {
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
                return new ModelAndView("administrarmedicamentos/ListarRecibidos", modelo);
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

                Pacientes dato = new Pacientes();
                dato.setFecha_ini(dFechaini1);
                dato.setFecha_fin(dFechafin1);
                dato.setNombres("%");
                dato.setId_estado("R");
                dato.setId_tipo_far(1);
                if ("Entradas-Recepcion".equals(sAccion) || "Ver Recepcion".equals(sAccion)) {
                    dato.setId_tipo_far(1);//// solo para entradas 
                } else if ("Ajustes(+)".equals(sAccion) || "Ver Ajustes(+)".equals(sAccion)) {
                    dato.setId_tipo_far(2);//// para ajustes + 
                } else if ("Ajustes(-)".equals(sAccion) || "Ver Ajustes(-)".equals(sAccion)) {
                    dato.setId_tipo_far(3);//// para ajustes -
                } else if ("Traspasos".equals(sAccion) || "Ver Traspasos".equals(sAccion)) {
                    dato.setId_tipo_far(4);//// para Traspasos
                }

                dato.setCod_esta(cliente.getCod_esta());
                dato.setId_farmacia(cliente.getId_farmacia());
                dato.setNombres("%");
                List listarSinPago = this.mi.getListaMedRecibidos(dato);  ///getListaMedRecibidos
                modelo.put("listapago", listarSinPago);
                modelo.put("valor_1", anioi + "-" + mesi + "-" + diai);
                modelo.put("valor_2", aniof + "-" + mesf + "-" + diaf);
                modelo.put("persona", persona);
                modelo.put("dFechafin1", aniof + "-" + mesf + "-" + diaf);
                modelo.put("dato", cliente);
                Medicamentos medid = new Medicamentos();
                medid.setCod_esta(cliente.getCod_esta());
                List listarprog = this.mi.getListarProgramas(medid);
                modelo.put("listarProg", listarprog);

                if ("Ver Traspasos".equals(sAccion) || "Ver Ajustes(-)".equals(sAccion) || "Ver Ajustes(+)".equals(sAccion) || "Ver Recepcion".equals(sAccion)) {
                    return new ModelAndView("administrarmedicamentos/VerRecibidos", modelo);
                } else {
                    return new ModelAndView(new VerRecibidosPDF(), modelo);
                }
            }
        }

        return new ModelAndView("administrarmedicamentos/ListarRecibidos", modelo);

    }
}
