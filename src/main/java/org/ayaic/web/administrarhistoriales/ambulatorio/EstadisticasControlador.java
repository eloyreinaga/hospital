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
import org.ayaic.domain.Localidades;
import org.ayaic.domain.Menues;
import org.ayaic.domain.Personas;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EstadisticasControlador {

    private final MiFacade mi;

    public EstadisticasControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/Estadisticas.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String sAccion = request.getParameter("boton");
        String accion = request.getParameter("boton");
        String sId_estado = request.getParameter("id_estado");
        String id_hospital = request.getParameter("id_hospital");
        String id_consultorio = request.getParameter("id_consultorio");
        String id_persona = request.getParameter("id_persona");
        String diaq1 = request.getParameter("mesq1");
        String mesq1 = request.getParameter("mesq1");
        String[] dias = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
        String[] meses = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        String[] horas = {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"};
        String[] minutos = {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"};
        Date fecha1 = new Date();
        int anioq = fecha1.getYear() + 1900;
        String[] anios = {Integer.toString(anioq), Integer.toString(anioq - 1), Integer.toString(anioq - 2)};

        if ("".equals(id_hospital) || id_hospital == null) {
            id_hospital = "0";
        }
        if ("".equals(id_consultorio) || id_consultorio == null) {
            id_consultorio = "0";
        }
        Localidades local = new Localidades();
        List Estab1 = this.mi.getListarEsta(local);
        Localidades datoestab = (Localidades) Estab1.get(0);
        modelo.put("codesta", Integer.toString(cliente.getCod_esta()));
        local.setArea("M");  ////getEstabHabiles
        local.setCod_esta(cliente.getCod_esta());
        List Estab = mi.getEstabHabiles(local);
        modelo.put("listarestable", Estab);
        Consultorios aa = new Consultorios();
        aa.setCod_esta(Integer.parseInt(id_hospital));
        if (!"".equals(id_hospital) && !"0".equals(id_hospital)) {
            aa.setCod_esta(Integer.parseInt(id_hospital));
            modelo.put("cod_esta", id_hospital);
        }
        List listarCargos = this.mi.getListarConsultorios(aa);
        modelo.put("listarCargos", listarCargos);

        List listarSeguros = this.mi.getListarSeguros("A");
        modelo.put("listarSeguros", listarSeguros);

        modelo.put("tipo_medico", Integer.toString(cliente.getId_cargo()));
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
        persona.setCod_esta(Integer.parseInt(id_hospital));
        Menues usuario_rol = new Menues();
        usuario_rol.setId_usuario(cliente.getId_usuario());
        List listaUsrRoles = this.mi.getListaUsrRoles(usuario_rol);
        for (int i = 0; i < listaUsrRoles.size(); i++) {
            Menues datorol = (Menues) listaUsrRoles.get(i);
            if (datorol.getId_rol() == 27) {
                persona.setDip("L");
            }
        }
        persona.setId_consultorio(Integer.parseInt(id_consultorio));
        List listarPaises = this.mi.getDatosPersonaConsul(persona);
        modelo.put("listaPersonas", listarPaises);
        modelo.put("estab", cliente.getArea());
        modelo.put("rol", Integer.toString(cliente.getId_rol()));
        modelo.put("dato", cliente);

        if (Integer.parseInt(id_hospital) > 0) {
            modelo.put("anio1", request.getParameter("anioi"));
            modelo.put("mes", request.getParameter("mesi"));
            modelo.put("dia", request.getParameter("diai"));
            modelo.put("hora", request.getParameter("horai"));
            modelo.put("minuto", request.getParameter("minutoi"));
            modelo.put("anio2", request.getParameter("aniof"));
            modelo.put("mes2", request.getParameter("mesf"));
            modelo.put("dia2", request.getParameter("diaf"));
            modelo.put("hora2", request.getParameter("horaf"));
            modelo.put("minuto2", request.getParameter("minutof"));
        }

        if ("Produccion/Especialidad".equals(sAccion) || "Produccion/Especialidad".equals(sAccion)) {
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
                return new ModelAndView("administrarmedicamentos/ReporteEntregados", modelo);
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

                if (cliente.getId_rol() == 36) {
                    dato.setTipoconsulta("U"); ////setCrearProduccionEmerg
                }
                List listaEstad = this.mi.getListarEstadisticas(dato);  ////getReporteProduccion
                modelo.put("listaCobros", listaEstad);
                if (Integer.parseInt(id_hospital) > 0) {
                    dato.setTipoconsulta("H");
                    dato.setCod_esta(Integer.parseInt(id_hospital));
                    List listaEstad2 = this.mi.getListarEstadisticasHospi(dato);  ////getReporteProduccion
                    modelo.put("listaCobros", listaEstad2);
                }

                modelo.put("dFechaini1", diai + "/" + mesi + "/" + anioi + "  " + horai + ":" + minutoi);
                modelo.put("dFechafin1", diaf + "/" + mesf + "/" + aniof + "  " + horaf + ":" + minutof);

                //if(Integer.parseInt(id_hospital)>0 ) {
                //   return new ModelAndView(new VerReporteProduccionHPDF(), modelo); 
                //}
                return new ModelAndView(new VerReporteProduccionPDF(), modelo);
            }
        }
        return new ModelAndView("administrarhistoriales/Estadisticas", modelo);
    }
}
