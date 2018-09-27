package org.ayaic.web.administrarecografia;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Historiales;
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.Prestaciones;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PrestacionSumiPacienteECOControlador {

    private final MiFacade mi;

    public PrestacionSumiPacienteECOControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/LabSumiPacienteEco.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");

        String nombres = request.getParameter("nombres");
        String nombresPres = request.getParameter("nombresPres");
        String id_prestacion = request.getParameter("id_prestacion");
        String id_paciente = request.getParameter("id_paciente");
        String id_reservacion = request.getParameter("id_reservacion");
        String id_consultorio = request.getParameter("id_consultorio");
        String id_persona = request.getParameter("id_persona");
        String boton = request.getParameter("boton");
        String expedido = request.getParameter("expedido");
        String tipo_medico = request.getParameter("tipo_medico");
        String sw = request.getParameter("sw");
        String ip = request.getRemoteAddr();
        String host = request.getRemoteHost();

        Pacientes buscarPaciente = this.mi.getDatosPaciente(Integer.parseInt(id_paciente)); // saca un registro a ser modificado
        modelo.put("datos", buscarPaciente);

        Historiales datohi = new Historiales();
        datohi.setId_historial(Integer.parseInt(id_reservacion));
        datohi.setCod_esta(cliente.getCod_esta());
        Historiales datosHistorial = this.mi.getDatosHistorial(datohi);

        // datos de una fecha pasada de atencion            
        Date fechaa = datosHistorial.getFecha();

        if ("adicion".equals(accion)) {
            String prestacion = request.getParameter("prestacion");
            Prestaciones dato = new Prestaciones();
            dato.setId_historial(Integer.parseInt(id_reservacion));
            dato.setId_prestacion(Integer.parseInt(id_prestacion));
            dato.setId_persona(cliente.getId_persona());
            dato.setFecha_ini(fechaa);
            dato.setPrestacion(prestacion);
            dato.setCod_esta(cliente.getCod_esta());
            this.mi.setCrearRecetaSumi(dato);

        }
        if ("eliminar".equals(accion)) {
            Prestaciones dato = new Prestaciones();
            dato.setId_historial(Integer.parseInt(id_reservacion));
            dato.setId_prestacion(Integer.parseInt(id_prestacion));
            dato.setId_persona(cliente.getId_persona());
            //dato.setId_detalle(Integer.parseInt(id_detalle)); 
            dato.setPaquete(ip);///20/07/2014
            dato.setPrestacion(host);///20/07/2014
            dato.setReferido("E");///20/07/2014
            dato.setId_grupo(cliente.getId_persona());///20/07/2014
            dato.setCod_esta(cliente.getCod_esta());///20/07/2014
            this.mi.setEliminarRecetaSumi(dato);
        }
        Prestaciones datopres = new Prestaciones();
        datopres.setCod_esta(cliente.getCod_esta());
        if ("BuscarCod".equals(boton)) {
            datopres.setDescripcion(nombres+"%");
            List listPrestacion = this.mi.getListarPrestaciones(datopres);
            modelo.put("listarPrestaciones", listPrestacion);
        }
        if ("BuscarNom".equals(boton)) {
            datopres.setDescripcion(nombresPres+"%");
            List listPrestacion = this.mi.getListarPrestacionesDes(datopres);
            modelo.put("listarPrestaciones", listPrestacion);
        }
        
        Prestaciones prestac = new Prestaciones();
        prestac.setId_historial(Integer.parseInt(id_reservacion));
        prestac.setId_persona(cliente.getId_persona());
        prestac.setCod_esta(cliente.getCod_esta());
        List listPrestacionCot = this.mi.getListarPrestacionesCot(prestac);
        modelo.put("listarPrestacionesCot", listPrestacionCot);

        List listarRecetas = this.mi.getListarSumiRecetas(prestac);
        modelo.put("listarRecetas", listarRecetas);

        modelo.put("nombres", nombres);
        modelo.put("nombresPres", nombresPres);
        modelo.put("id_paciente", id_paciente);
        modelo.put("id_reservacion", id_reservacion);
        modelo.put("id_consultorio", id_consultorio);
        modelo.put("id_persona", id_persona);
        modelo.put("expedido", expedido);
        modelo.put("tipo_medico", tipo_medico);
        modelo.put("sw", sw);
        return new ModelAndView("administrarecografia/PrestacionSumiPaciente", modelo);
    }
}
