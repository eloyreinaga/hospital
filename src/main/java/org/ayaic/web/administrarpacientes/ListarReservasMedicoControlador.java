package org.ayaic.web.administrarpacientes;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Consultorios;
import org.ayaic.domain.Historiales;
import org.ayaic.domain.Localidades;
import org.ayaic.domain.Personas;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ListarReservasMedicoControlador {

    private final MiFacade mi;

    public ListarReservasMedicoControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/ListarReservasMedico.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion1 = request.getParameter("accion1");
        String accion = request.getParameter("accion");
        String id_consultorio = request.getParameter("id_consultorio");
        String id_persona = request.getParameter("id_persona");
        String dia = request.getParameter("diai");
        String mes = request.getParameter("mesi");
        String anio = request.getParameter("anioi");
        String hora1 = request.getParameter("horai1");
        String minuto1 = request.getParameter("minutoi1");
        String hora2 = request.getParameter("horai2");
        String minuto2 = request.getParameter("minutoi2");
        String[] dias = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
        String[] meses = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        String[] horas = {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"};
        String[] minutos = {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"};
        Date fecha1 = new Date();
        Date dFechaini1 = new Date();
        Date dFechafin1 = new Date();
        int anioq = fecha1.getYear() + 1900;
        String[] anios = {Integer.toString(anioq), Integer.toString(anioq + 1)};

        Localidades local = new Localidades();
        List Estab1 = this.mi.getListarEsta(local);
        Localidades datoestab = (Localidades) Estab1.get(0);

        modelo.put("anios", anios);
        modelo.put("meses", meses);
        modelo.put("dias", dias);
        modelo.put("horas", horas);
        modelo.put("minutos", minutos);
        modelo.put("anio", Integer.toString(fecha1.getYear() + 1900));
        modelo.put("mes", Integer.toString(fecha1.getMonth() + 1));
        modelo.put("dia", Integer.toString(fecha1.getDate()));
        modelo.put("anio2", Integer.toString(fecha1.getYear() + 1900));
        modelo.put("mes2", Integer.toString(fecha1.getMonth() + 1));
        modelo.put("dia2", Integer.toString(fecha1.getDate()));
        modelo.put("hora", Integer.toString(fecha1.getHours()));
        modelo.put("minuto", Integer.toString(fecha1.getMinutes()));
        modelo.put("hora", "00");
        modelo.put("minuto", "00");
        modelo.put("hora2", "23");
        modelo.put("minuto2", "59");

        //lista de consultorios
        Consultorios datosconsultorio = this.mi.getDatosConsultorio(cliente.getId_consultorio()); // saca un registro a ser modificado
        Consultorios a = new Consultorios();
        a.setCod_esta(cliente.getCod_esta());
        List listarCargos = this.mi.getListarConsultorios(a);
        modelo.put("listarCargos", listarCargos);
        modelo.put("id_cargo", Integer.toString(datosconsultorio.getId_cargo()));
        modelo.put("dato", cliente);

        List listarSeguros = this.mi.getListarSeguros("A");
        modelo.put("listaPacAfi", listarSeguros);

        //lista de pacientes en el consultorio
        if ("Buscar".equals(accion) || "Imprimir".equals(accion)) {
            String diai = request.getParameter("diai");
            String mesi = request.getParameter("mesi");
            String anioi = request.getParameter("anioi");
            String horai1 = request.getParameter("horai1");
            String minutoi1 = request.getParameter("minutoi1");

            String diai2 = request.getParameter("diai2");
            String mesi2 = request.getParameter("mesi2");
            String anioi2 = request.getParameter("anioi2");
            String horai2 = request.getParameter("horai2");
            String minutoi2 = request.getParameter("minutoi2");

            if (request.getParameter("diai") != null) {
                int iAnio1 = Integer.parseInt(anioi) - 1900;
                int iMes1 = Integer.parseInt(mesi) - 1;
                int iDia1 = Integer.parseInt(diai);
                int iHor1 = Integer.parseInt(horai1);
                int iMin1 = Integer.parseInt(minutoi1);

                int iAnio2 = Integer.parseInt(anioi2) - 1900;
                int iMes2 = Integer.parseInt(mesi2) - 1;
                int iDia2 = Integer.parseInt(diai2);
                int iHor2 = Integer.parseInt(horai2);
                int iMin2 = Integer.parseInt(minutoi2);

                dFechaini1 = new Date(iAnio1, iMes1, iDia1, iHor1, iMin1, 00);
                dFechafin1 = new Date(iAnio2, iMes2, iDia2, iHor2, iMin2, 59);
            }

            Historiales dato = new Historiales();
            dato.setCod_esta(cliente.getCod_esta());
            dato.setId_consultorio(cliente.getId_consultorio());
            dato.setId_persona(cliente.getId_persona());
            dato.setFecha_ini(dFechaini1);
            dato.setFecha_fin(dFechafin1);
            dato.setId_historia(0);
            dato.setId_historial(2);
            dato.setAccion("F");  ////getListarReservacionesEstadFecha  01-09-2016  se cambio para ver medico anterior getListarReservacionesMedico
            List listarPaises = this.mi.getListarReservaciones1(dato);///getListarReservaciones1
            modelo.put("milista", listarPaises);
            if (cliente.getCod_esta() != 200010) {/////05-11-2015 sector publico
                dato.setId_historia(0);
                dato.setId_historial(0);
                dato.setAccion("T");
                List listarRes = this.mi.getListarReservaciones(dato);
                modelo.put("milista", listarRes);
            }
            dato.setId_estado("%");
            if (cliente.getCod_esta() == 200115 || cliente.getCod_esta() == 200075 || cliente.getCod_esta() == 200010 || cliente.getCod_esta() == 700241) {
                dato.setAccion("F");  ////getListarReservacionesEstadFecha
                List listarRes = this.mi.getListarReservacionesEstadFecha(dato);
                modelo.put("milista", listarRes);
            }

            Personas Empleado = this.mi.getDatosPersona(cliente.getId_persona());
            modelo.put("persona", Empleado);

            modelo.put("estab", datoestab.getArea());
            modelo.put("dia", diai);
            modelo.put("mes", mesi);
            modelo.put("anio", anioi);
            modelo.put("hora", horai1);
            modelo.put("minuto", minutoi1);

            modelo.put("dia2", diai2);
            modelo.put("mes2", mesi2);
            modelo.put("anio2", anioi2);
            modelo.put("hora2", horai2);
            modelo.put("minuto2", minutoi2);
            if ("Imprimir".equals(accion)) {
                return new ModelAndView(new VerReservacionesPacientesPDF(), modelo);
            }
            return new ModelAndView("administrarpacientes/ListarReservasMedico", modelo);
        }

        if (("".equals(dia)) || ("".equals(mes)) || ("".equals(anio)) || dia == null || mes == null || anio == null) {
            return new ModelAndView("administrarpacientes/ListarReservasMedico", modelo);
        } else {
            int iAnio1 = Integer.parseInt(anio) - 1900;
            int iMes1 = Integer.parseInt(mes) - 1;
            int iDia1 = Integer.parseInt(dia);
            int iHor1 = Integer.parseInt(hora1);
            int iMin1 = Integer.parseInt(minuto1);
            int iHor2 = Integer.parseInt(hora2);
            int iMin2 = Integer.parseInt(minuto2);

            dFechaini1 = new Date(iAnio1, iMes1, iDia1, iHor1, iMin1, 00);
            dFechafin1 = new Date(iAnio1, iMes1, iDia1, iHor2, iMin2, 59);

            modelo.put("id_consultorio", id_consultorio);
            modelo.put("id_persona", id_persona);

            return new ModelAndView("administrarpacientes/ListarReservasMedico", modelo);

        }

    }
}
