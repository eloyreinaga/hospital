package org.ayaic.web.administrarhistoriales;

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
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ListarHistoriaGrabaControlador {

    private final MiFacade mi;

    public ListarHistoriaGrabaControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/ListarHistoriaGraba.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String accionv = request.getParameter("accionv");
        String accion1 = request.getParameter("accion1");
        String accion3 = request.getParameter("accion3");
        String nombres = request.getParameter("nombres");
        String id_paciente = request.getParameter("id_paciente");
        String id_historial = request.getParameter("id_historial");
        String id_reservacion = request.getParameter("id_historial");
        String id_consultorio = request.getParameter("id_consultorio");
        String id_persona = request.getParameter("id_persona");

        //InetAddress ip;
        //ip = InetAddress.getLocalHost();
        String ip = request.getRemoteAddr();
        String host = request.getRemoteHost();
        Date ahora = new Date();

        Localidades local = new Localidades();
        List Estab1 = this.mi.getListarEsta(local);
        Localidades datoestab = (Localidades) Estab1.get(0);
        modelo.put("codesta", Integer.toString(datoestab.getCod_esta()));
        modelo.put("estab", datoestab.getArea());

        modelo.put("nombres", nombres);
        modelo.put("id_paciente", id_paciente);

        if ("Cambiar".equals(accion3)) {
            String dia = request.getParameter("dia");
            String mes = request.getParameter("mes");
            String anio = request.getParameter("anio");
            String hora = request.getParameter("hora");
            String minuto = request.getParameter("minuto");
            String expedido = request.getParameter("expedido");
            String id_estado = request.getParameter("id_estado");
            String edad = request.getParameter("edad");
            String tipo = request.getParameter("tipo");
            String internar = request.getParameter("internar");

            if (Integer.parseInt(anio) > (ahora.getYear() + 1900) || Integer.parseInt(mes) > 12 || Integer.parseInt(dia) > 31
                    || Integer.parseInt(anio) < 1920) {
                return new ModelAndView("Aviso", "mensaje", "La fecha no es correcta");
            }
            Historiales dato = new Historiales();
            dato.setId_historial(Integer.parseInt(id_historial));
            dato.setCod_esta(cliente.getCod_esta());
            Historiales datosHistorial = this.mi.getDatosHistorial(dato);

            if (datosHistorial.getId_cargo() < 2 && Integer.parseInt(internar) > 1) {
                return new ModelAndView("Aviso", "mensaje", "No puede cambiar a Internado si nunca ha estado Internado, elija internar con una Sala y cama");
            }

            if (expedido == null) {
                expedido = dato.getExpedido();
            }
            if (id_estado == null) {
                id_estado = dato.getId_estado();
            }

            int diax = Integer.parseInt(dia);
            int mesx = Integer.parseInt(mes) - 1;
            int aniox = Integer.parseInt(anio) - 1900;
            int horax = Integer.parseInt(hora);
            int minutox = Integer.parseInt(minuto);
            Date fechaa = new Date(aniox, mesx, diax, horax, minutox, 00);
            dato.setEdad(Integer.parseInt(edad));
            if (fechaa.getDate() != datosHistorial.getFecha().getDate() || fechaa.getMonth() != datosHistorial.getFecha().getMonth() || fechaa.getHours() != datosHistorial.getFecha().getHours()) {
                dato.setFecha(fechaa);
            } else {
                dato.setFecha(datosHistorial.getFecha());
            }

            dato.setId_estado(id_estado);
            if ("1".equals(tipo) || "2".equals(tipo) || "3".equals(tipo) || "4".equals(tipo)) {
                expedido = "S";
            } else {
                if ("0".equals(tipo)) {
                    expedido = "E";
                } else {
                    expedido = "P";
                }
            }
            dato.setExpedido(expedido);
            dato.setId_cargo(Integer.parseInt(internar));
            dato.setId_historial(Integer.parseInt(id_historial));
            dato.setId_persona(Integer.parseInt(id_persona));
            dato.setId_consultorio(Integer.parseInt(id_consultorio));
            dato.setId_seguro(Integer.parseInt(tipo));
            dato.setCod_esta(cliente.getCod_esta());
            dato.setCama(ip);///20/07/2014
            dato.setSala(host);///20/07/2014
            dato.setEstimc("M");///20/07/2014
            dato.setId_provincia(cliente.getId_persona());///20/07/2014

            try {
                this.mi.setModificarEstadoHistorial(dato);
                return new ModelAndView("Aviso", "mensaje", "La actualizacion se realiza con EXITO..!");
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "La actualizacion del historial no se cumplio, verifique los datos");
            }

        }

        Historiales dato = new Historiales();
        dato.setId_paciente(Integer.parseInt(id_paciente));
        dato.setCod_esta(cliente.getCod_esta());
        List listas = this.mi.getListarHistoria(dato);
        modelo.put("milista", listas);

        Consultorios datosconsul = this.mi.getDatosConsultorio(cliente.getId_consultorio()); // para admisnitrador muestra todas las opciones

        Pacientes buscarPaciente = this.mi.getDatosPaciente(Integer.parseInt(id_paciente)); // saca un registro a ser modificado
        modelo.put("datos", buscarPaciente);
        modelo.put("id_paciente", id_paciente);
        modelo.put("tipo_medico", Integer.toString(datosconsul.getId_cargo()));
        return new ModelAndView("administrarhistoriales/HistoriaPaciente", modelo);
    }
}
