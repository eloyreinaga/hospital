package org.ayaic.web.administrarhistoriales;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Date;
import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Consultorios;
import org.ayaic.domain.Historiales;
import org.ayaic.domain.Localidades;
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.Personas;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ListarReservasControlador {

    private final MiFacade mi;

    public ListarReservasControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("ListarReservas.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws UnknownHostException {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String id_reservacion = request.getParameter("id_reservacion");
        String id_consultorio = request.getParameter("id_consultorio");
        String id_persona = request.getParameter("id_persona");
        String accion1 = request.getParameter("accion1");
        Date fecha1 = new Date();
        InetAddress ip;
        ip = InetAddress.getLocalHost();

        Localidades local = new Localidades();
        List Estab1 = this.mi.getListarEsta(local);
        Localidades datoestab = (Localidades) Estab1.get(0);
        modelo.put("seguro_estab", Integer.toString(cliente.getInst_id()));

        Personas persona = this.mi.getBuscarPersona(cliente.getId_persona()); // saca un registro a ser modificado

        Consultorios datosconsul = this.mi.getDatosConsultorio(persona.getId_consultorio()); // saca un registro a ser modificado
        modelo.put("tipo_medico", Integer.toString(datosconsul.getId_cargo()));
        modelo.put("especialidad", Integer.toString(datosconsul.getId_especialidad()));

        if (cliente.getId_almacen() == 1 && (datosconsul.getId_especialidad() != 99 && datosconsul.getId_especialidad() != 98)) {
            id_consultorio = Integer.toString(cliente.getId_consultorio());
            id_persona = Integer.toString(cliente.getId_persona());
        }

        Consultorios a = new Consultorios();
        a.setCod_esta(cliente.getCod_esta());
        a.setSelec(1);
        a.setId_estado("U");////getListarConsultoriosUrgen 08/01/2017
        a.setId_especialidad(1);////
        List listarCargos = this.mi.getListarConsultoriosUrgen(a);
        modelo.put("listarCargos", listarCargos);
        modelo.put("id_cargo", Integer.toString(datosconsul.getId_cargo()));
        modelo.put("id_consultorio", id_consultorio);

        Historiales dato = new Historiales();
        dato.setId_persona(cliente.getId_persona());
        dato.setId_por(cliente.getId_persona());
        dato.setAccion("R");                ////getHistorialAtendidos
        dato.setFecha_ini(fecha1);
        dato.setFecha_fin(fecha1);
        dato.setCod_esta(cliente.getCod_esta());

        if ("LlamarPac".equals(accion1)) {
            String id_paciente = request.getParameter("id_paciente");

            Personas buscarEmpleado = this.mi.getDatosPersona(cliente.getId_persona());

            Pacientes carpeta = this.mi.getDatosPaciente(Integer.parseInt(id_paciente));
            carpeta.setId_historial(Integer.parseInt(id_reservacion));
            carpeta.setUlt_usuario(cliente.getId_persona());
            carpeta.setCadena2(buscarEmpleado.getCadena2());
            carpeta.setCadena3(buscarEmpleado.getCadena3());
            carpeta.setCod_esta(cliente.getCod_esta());
            carpeta.setId_estado("T");
            this.mi.setCrearPacienteTicket(carpeta); ///setCrearPacienteTicket   
        }

        if ("BuscarP".equals(accion1)) {
            String nombres = request.getParameter("nombre");
            nombres = nombres.trim();
            nombres = nombres.replaceAll(" +", " ");///quita espacios en blanco entre cadenas
            nombres = nombres.replaceAll("\\s", ":*&");///:*&
            nombres = nombres.replaceAll("ñ", "n");
            nombres = nombres.replaceAll("Ñ", "N");
            if (nombres.length() > 0) {
                nombres = nombres + ":*";
            }
            dato.setCadena(nombres);
            ///Historiales dato=new Historiales();
            dato.setAccion("B");
            dato.setCod_esta(cliente.getCod_esta());
            List listarReserv = this.mi.getListarReservacionesNombreCaja(dato);  ////getListarReservacionesNombreCaja
            modelo.put("milista", listarReserv);
            List listarAtendidosC = this.mi.getHistorialAtendidosResidNombre(dato);  ///getHistorialAtendidosResidNombre
            modelo.put("milistaAten", listarAtendidosC);
            dato.setSuma1(2);
            dato.setSuma2(2);
            dato.setAccion("U");
            List listarObservacion = this.mi.getHistorialAtendidos(dato);   ////_verifica_internados_observacion_busca
            modelo.put("listarObservacion", listarObservacion);

            //if(datosconsul.getId_especialidad()==99) {///para Obrero
            return new ModelAndView("administrarhistoriales/ListarReservas99", modelo);
            //}//else{
            //   return new ModelAndView("administrarhistoriales/ListarReservas", modelo);
            //}
        }
        if (datosconsul.getId_especialidad() != 99 && datosconsul.getId_especialidad() != 98) {
            List listarAtendidos = this.mi.getHistorialAtendidos(dato);
            modelo.put("milistaAten", listarAtendidos);
        }
        if (datosconsul.getId_especialidad() == 99) {///para Obrero
            dato.setAccion("T");  ////getHistorialAtendidosResid
            List listarAtendidos = this.mi.getHistorialAtendidosResid(dato);
            modelo.put("milistaAten", listarAtendidos);
        }
        if (datosconsul.getId_especialidad() == 98) {///para Obrero
            dato.setAccion("1");  ////getHistorialAtendidosHemo
            List listarAtendidos = this.mi.getHistorialAtendidosHemo(dato);
            modelo.put("milistaAten", listarAtendidos);
        }
        dato.setId_consultorio(persona.getId_consultorio());
        dato.setCod_esta(cliente.getCod_esta());

        dato.setAccion("P");
        List listarResvFicha = this.mi.getListarReserFichas(dato);
        modelo.put("listaResvF", listarResvFicha);

        dato.setId_estado("C");
        dato.setAccion("T");

        if ("3".equals(cliente.getTipo())) {///para HGSJDD
            dato.setId_persona(persona.getId_persona());
            dato.setAccion("P");
            if (datoestab.getCod_esta() == 200010) {///para Obrero
                dato.setAccion("V");///09/09/2014 
            }
            List listarPaises = this.mi.getListarReservaciones(dato);  ///para obrero solo campo vigencia=1 
            modelo.put("milista", listarPaises);
            if (datosconsul.getId_especialidad() == 99) {///para Obrero
                dato.setAccion("R");  ////getListarReservacionesResid
                List listarRes = this.mi.getListarReservacionesResid(dato);  ///para obrero solo campo vigencia=1 
                modelo.put("milista", listarRes);
            }
            if (datosconsul.getId_especialidad() == 98) {///para Hemodalisis
                dato.setAccion("1");  ////getListarReservacionesHemo
                List listarRes = this.mi.getListarReservacionesHemo(dato);  ///para obrero solo campo vigencia=1 
                modelo.put("milista", listarRes);
            }

            dato.setAccion("P");
            List listarObservacion = this.mi.getInternadosCajaObservacion(dato);
            modelo.put("listarObservacion", listarObservacion);
            if (datosconsul.getId_especialidad() == 98) {///para Hemodalisis
                dato.setAccion("2");  ////getInternadosCajaObservacionHemo
                List listarObservacion2 = this.mi.getInternadosCajaObservacionHemo(dato);
                modelo.put("listarObservacion", listarObservacion2);
            }

        } else {
            dato.setAccion("K");  ////getListarReservaciones solo sector publico para sus reservaciones de medicos y no de afiliacion
            dato.setId_departamento(persona.getId_consultorio());
            dato.setId_localidad(cliente.getId_persona());
            dato.setCod_esta(cliente.getCod_esta());
            List listarPaises4 = this.mi.getListarReservaciones(dato);
            modelo.put("milista", listarPaises4);
        }

        if (id_consultorio != null && !"".equals(id_consultorio) && cliente.getInst_id() == 10) {

            Personas personal = new Personas();
            personal.setId_consultorio(Integer.parseInt(id_consultorio));
            personal.setCod_esta(cliente.getCod_esta());
            personal.setDip("E"); ///getDatosPersonaConsulEmerg 08/01/2017
            List buscarEmpleado = this.mi.getDatosPersonaConsulEmerg(personal);
            modelo.put("listaPersonas", buscarEmpleado);
            if (cliente.getId_especialidad() == 99) {
                personal.setDip("U");   ////getDatosPersonaConsulUrgen
                List buscarEmpleado2 = this.mi.getDatosPersonaConsulUrgen(personal);
                modelo.put("listaPersonas", buscarEmpleado2);
            }

            modelo.put("estab", datoestab.getArea());

            dato.setAccion("O"); ////getHistorialAtendidosResidConsul
            dato.setId_consultorio(Integer.parseInt(id_consultorio));
            dato.setCod_esta(cliente.getCod_esta());
            List listarAtendidosC = this.mi.getHistorialAtendidosResidConsul(dato);
            modelo.put("milistaAten", listarAtendidosC);

            dato.setAccion("V");
            List listarRes = this.mi.getListarReservaciones(dato);
            modelo.put("milista", listarRes);

            List listarObservacion = this.mi.getListarReservacionesCaja(dato);   ////getListarReservacionesCaja
            modelo.put("listarObservacion", listarObservacion);

            if (id_persona != null && !"0".equals(id_persona)) {

                dato.setAccion("Q");
                dato.setId_consultorio(Integer.parseInt(id_consultorio));
                dato.setId_persona(Integer.parseInt(id_persona));
                dato.setCod_esta(cliente.getCod_esta());
                List listarAtendidosCp = this.mi.getHistorialAtendidos(dato);
                modelo.put("milistaAten", listarAtendidosCp);

                dato.setAccion("V");
                List listarResPer = this.mi.getListarReservaciones(dato);
                modelo.put("milista", listarResPer);

                dato.setAccion("W");
                List listarObservacionper = this.mi.getInternadosCajaObservacionPiso(dato);   ////getInternadosCajaObservacionPiso
                modelo.put("listarObservacion", listarObservacionper);
                modelo.put("id_persona", id_persona);
            }
        }

        if ("EliminarReserva".equals(accion1)) {
            Historiales elimina = new Historiales();
            elimina.setId_reservacion(Integer.parseInt(id_reservacion));
            elimina.setAccion(ip.getHostAddress());
            elimina.setCodigo(ip.getHostName());
            elimina.setExpedido("E");
            elimina.setId_persona(cliente.getId_persona());
            elimina.setCod_esta(cliente.getCod_esta());
            this.mi.setEliminarReserva(elimina);
            return new ModelAndView("Aviso", "mensaje", "Se elimino correctamente");
        }

        if (datosconsul.getId_cargo() == 10 || datosconsul.getId_cargo() == 11 || datosconsul.getId_cargo() == 12) {///para laboratorios
            dato.setAccion("X"); ///("S"); para ecografias y hospitales publicos 08/05/2015
            dato.setId_persona(cliente.getId_persona());
            dato.setCod_esta(cliente.getCod_esta());
            List listarPaises = this.mi.getListarReservaciones(dato);
            modelo.put("milista", listarPaises);
        }

        if (cliente.getId_almacen() == 1) {///para Obrero y demas

            return new ModelAndView("administrarhistoriales/ListarReservas99", modelo);
        } else {
            if (datosconsul.getId_especialidad() == 98) {///para Obrero
                return new ModelAndView("administrarhistoriales/ListarReservasHemo", modelo);
            } else {
                return new ModelAndView("administrarhistoriales/ListarReservas", modelo);
            }
        }

    }
}
