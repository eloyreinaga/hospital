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
import org.ayaic.domain.Medicamentos;
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.Personas;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ListarReservasCIE10Controlador {

    private final MiFacade mi;

    public ListarReservasCIE10Controlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/ListarReservasCIE10.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String sFecha_ini = request.getParameter("valor_1");
        String sFecha_fin = request.getParameter("valor_2");
        //InetAddress ip;
        //ip = InetAddress.getLocalHost();
        String ip = request.getRemoteAddr();
        String host = request.getRemoteHost();

        String accion1 = request.getParameter("accion1");
        String accion = request.getParameter("accion");
        String id_consultorio = request.getParameter("id_consultorio");
        String id_persona = request.getParameter("id_persona");
        String expedido = request.getParameter("expedido");
        Date fecha1 = new Date();
        //lista de consultorios
        Consultorios datosconsultorio = this.mi.getDatosConsultorio(cliente.getId_consultorio()); // saca un registro a ser modificado
        Consultorios a = new Consultorios();
        a.setCod_esta(cliente.getCod_esta());
        List listarCargos = this.mi.getListarConsultorios(a);

        modelo.put("listarCargos", listarCargos);
        modelo.put("id_cargo", Integer.toString(datosconsultorio.getId_cargo()));

        //lista de pacientes en el consultorio
        if ("Reportar".equals(accion)) {
            String id_paciente = request.getParameter("id_paciente");
            String id_historial = request.getParameter("id_reservacion");
            Pacientes buscarPaciente = this.mi.getDatosPaciente(Integer.parseInt(id_paciente)); // saca un registro a ser modificado

            //List listarHG = this.mi.getListarCobroReserva("%");
            //Historiales listaHG = (Historiales) listarHG.get(listarHG.size()-1);
            Historiales datohi = new Historiales();
            datohi.setId_historial(Integer.parseInt(id_historial));
            datohi.setCod_esta(cliente.getCod_esta());
            Historiales datosHist = this.mi.getDatosHistorial(datohi);

            if (datosHist == null) {

                Historiales dato = new Historiales();
                dato.setId_historial(Integer.parseInt(id_historial));
                dato.setAccion("R");
                dato.setCod_esta(cliente.getCod_esta());
                Historiales datosReserva = this.mi.getDatosHistorial(dato); // saca un registro a ser modificado
                dato.setId_persona(datosReserva.getId_persona());
                dato.setId_paciente(datosReserva.getId_paciente());
                dato.setEdad(datosReserva.getEdad());
                dato.setId_consultorio(datosReserva.getId_consultorio());
                dato.setTalla(0);
                dato.setPeso(0);
                dato.setEstimc("0");
                dato.setTemperatura(0);
                dato.setExpedido(datosReserva.getExpedido());
                dato.setFc("0");
                dato.setPa("0");
                dato.setFr("0");
                dato.setCodigo("");
                dato.setRepetida("N");
                dato.setSubjetivo("Estadistica Morbilidad");
                dato.setDiagnostico("Estadistica Morbilidad");
                dato.setObjetivo("");
                dato.setId_seguro(buscarPaciente.getId_seguro());
                dato.setCod_esta(cliente.getCod_esta());
                dato.setId_por(cliente.getId_persona());
                dato.setSo2("0");
                dato.setGlicemia("0");
                dato.setFechalab(datosReserva.getFecha());

                int iResultado = this.mi.setRegistrarHistorial(dato);
                //crea el historial nuevo en la tabla y elimina el de reservaciones
                Historiales elimina = new Historiales();
                elimina.setId_reservacion(Integer.parseInt(id_historial));
                elimina.setAccion(ip);
                elimina.setCodigo(host);
                elimina.setExpedido("A");
                elimina.setId_persona(cliente.getId_persona());
                elimina.setCod_esta(cliente.getCod_esta());
                this.mi.setEliminarReserva(elimina);
                modelo.put("id_persona", Integer.toString(datosReserva.getId_persona()));
                modelo.put("id_paciente", Integer.toString(datosReserva.getId_paciente()));
                modelo.put("id_reservacion", id_historial);
                modelo.put("id_consultorio", Integer.toString(datosReserva.getId_consultorio()));
                Pacientes buscarPacienteh = this.mi.getDatosPaciente(datosReserva.getId_paciente()); // saca un registro a ser modificado
                modelo.put("datos", buscarPacienteh);
                Medicamentos medid = new Medicamentos();
                medid.setCod_esta(cliente.getCod_esta());
                medid.setId_persona(datosReserva.getId_persona());
                List listarEnfermedadesCot = this.mi.getListarEnfermedadesCot(medid);
                modelo.put("listarEnfermedadesCot", listarEnfermedadesCot);
            } else {
                Historiales dato = new Historiales();
                dato.setId_historial(Integer.parseInt(id_historial));
                dato.setId_persona(datosHist.getId_persona());
                dato.setId_paciente(datosHist.getId_paciente());
                dato.setEdad(datosHist.getEdad());
                dato.setId_consultorio(datosHist.getId_consultorio());
                dato.setTalla(0);
                dato.setPeso(0);
                dato.setEstimc("0");
                dato.setTemperatura(0);
                dato.setExpedido(datosHist.getExpedido());
                dato.setFc("0");
                dato.setPa("0");
                dato.setFr("0");
                dato.setCodigo("");
                dato.setRepetida("N");
                dato.setSubjetivo("Estadistica Morbilidad");
                dato.setDiagnostico("Estadistica Morbilidad");
                dato.setObjetivo("");
                dato.setId_seguro(buscarPaciente.getId_seguro());
                dato.setCod_esta(cliente.getCod_esta());
                dato.setId_por(cliente.getId_persona());
                int iResultado = this.mi.setRegistrarHistorial(dato);
                //crea el historial nuevo en la tabla y elimina el de reservaciones
                Historiales elimina = new Historiales();
                elimina.setId_reservacion(Integer.parseInt(id_historial));
                this.mi.setEliminarReserva(elimina);
                modelo.put("id_persona", Integer.toString(datosHist.getId_persona()));
                modelo.put("id_paciente", Integer.toString(datosHist.getId_paciente()));
                modelo.put("id_reservacion", id_historial);
                modelo.put("id_consultorio", Integer.toString(datosHist.getId_consultorio()));
                Pacientes buscarPacienteh = this.mi.getDatosPaciente(datosHist.getId_paciente()); // saca un registro a ser modificado
                modelo.put("datos", buscarPacienteh);
                Medicamentos medid = new Medicamentos();
                medid.setCod_esta(cliente.getCod_esta());
                medid.setId_persona(datosHist.getId_persona());
                List listarEnfermedadesCot = this.mi.getListarEnfermedadesCot(medid);
                modelo.put("listarEnfermedadesCot", listarEnfermedadesCot);
            }
            Historiales datomorbi = new Historiales();
            datomorbi.setId_reservacion(Integer.parseInt(id_historial));
            datomorbi.setCod_esta(cliente.getCod_esta());
            List listarmorbi = this.mi.getListaMorbi(datomorbi);
            modelo.put("morbi", listarmorbi);

            if (listarmorbi.isEmpty() != true) {
                modelo.put("terminar", "si");
            }

            //Historiales datohi= new Historiales() ;
            datohi.setId_historial(Integer.parseInt(id_historial));
            datohi.setCod_esta(cliente.getCod_esta());
            Historiales datosHistorial = this.mi.getDatosHistorial(datohi);

            modelo.put("objetivo", datosHistorial.getObjetivo());
            modelo.put("subhetivo", datosHistorial.getSubjetivo());
            modelo.put("diagnostico", datosHistorial.getDiagnostico());
            modelo.put("expedido", datosHistorial.getExpedido());
            modelo.put("miaccion", datosHistorial.getAccion());
            return new ModelAndView("administrarhistoriales/AtenderPaciente3", modelo);
        }

        if (request.getParameter("id_consultorio") != null && (!"".equals(request.getParameter("id_consultorio")))) {
            Localidades local = new Localidades();
            List Estab1 = this.mi.getListarEsta(local);
            Localidades datoestab = (Localidades) Estab1.get(0);

            Historiales dato = new Historiales();
            dato.setId_persona(cliente.getId_usuario());
            dato.setId_consultorio(Integer.parseInt(id_consultorio));
            dato.setAccion("L");
            dato.setId_estado("%");
            List listarPaises = this.mi.getListarReservaciones(dato);
            modelo.put("milista", listarPaises);

            Personas persona = new Personas();
            persona.setId_consultorio(Integer.parseInt(id_consultorio));
            persona.setCod_esta(cliente.getCod_esta());
            List buscarEmpleado = this.mi.getDatosPersonaConsul(persona);
            modelo.put("listaPersonas", buscarEmpleado);

            if (request.getParameter("id_persona") != null) {
                dato.setId_persona(Integer.parseInt(id_persona));
                dato.setId_consultorio(Integer.parseInt(id_consultorio));
                dato.setAccion("E");
                dato.setId_estado("%");
                List listarMedicos = this.mi.getListarReservaciones(dato);
                modelo.put("milista", listarMedicos);
            }
        } else {
            if (sFecha_ini != null || sFecha_fin != null) {
                String[] sFechaini = sFecha_ini.split("-");
                int iAnio1 = Integer.parseInt(sFechaini[0]) - 1900;
                int iMes1 = Integer.parseInt(sFechaini[1]) - 1;
                int iDia1 = Integer.parseInt(sFechaini[2]);

                String[] sFechafin = sFecha_fin.split("-");
                int iAnio2 = Integer.parseInt(sFechafin[0]) - 1900;
                int iMes2 = Integer.parseInt(sFechafin[1]) - 1;
                int iDia2 = Integer.parseInt(sFechafin[2]);

                Date dFechaini1 = new Date(iAnio1, iMes1, iDia1);
                Date dFechafin1 = new Date(iAnio2, iMes2, iDia2);
                Historiales dato = new Historiales();
                dato.setAccion("C");
                dato.setFecha_ini(dFechaini1);
                dato.setFecha_fin(dFechafin1);
                List listarConsul = this.mi.getListarReservaciones(dato);
                modelo.put("milista", listarConsul);
            } else {
                return new ModelAndView("administrarpacientes/ListarReservasCIE10", modelo);
            }
        }
        List listarSeguros = this.mi.getListarSeguros("A");
        modelo.put("listaPacAfi", listarSeguros);

        modelo.put("id_consultorio", id_consultorio);
        modelo.put("id_persona", id_persona);
        return new ModelAndView("administrarpacientes/ListarReservasCIE10", modelo);
    }
}
