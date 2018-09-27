package org.ayaic.web.administrarfarmacias;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Historiales;
import org.ayaic.domain.Localidades;
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.Recetas;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ListarAtendidosVControlador {

    private final MiFacade mi;

    public ListarAtendidosVControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/ListarAtendidosVenta.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String diaq1 = request.getParameter("mesq1");
        String mesq1 = request.getParameter("mesq1");
        String id_tipointer = request.getParameter("id_tipointer");
        String sig_centro = request.getParameter("sig_centro");
        String[] dias = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
        String[] meses = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        Date fecha1 = new Date();
        int anioq = fecha1.getYear() + 1900;
        String[] anios = {Integer.toString(anioq), Integer.toString(anioq - 1), Integer.toString(anioq - 2), Integer.toString(anioq - 3), Integer.toString(anioq - 4), Integer.toString(anioq - 5), Integer.toString(anioq - 6), Integer.toString(anioq - 7), Integer.toString(anioq - 8), Integer.toString(anioq - 9), Integer.toString(anioq - 10)};
        String ip = request.getRemoteAddr();
        Date Fecha1 = new Date();

        Localidades local = new Localidades();
        List Estab1 = this.mi.getListarEsta(local);
        Localidades datoestab = (Localidades) Estab1.get(0);
        modelo.put("cod_esta", Integer.toString(datoestab.getCod_esta()));
        modelo.put("id_tipointer", id_tipointer);

        modelo.put("anios", anios);
        modelo.put("meses", meses);
        modelo.put("dias", dias);
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

        if ("adicionar".equals(accion)) {
            modelo.put("nombres", "NOMBRE");
            modelo.put("nit", "NIT");
            modelo.put("id_historial", "0");
            modelo.put("sw", request.getParameter("sw"));
            modelo.put("num_cladoc", "0");
            return new ModelAndView("administrarfarmacias/EntregaNPacienteV", modelo);
        }

        if ("entregar".equals(accion)) {
            String id_paciente = request.getParameter("id_paciente");
            String id_historial = request.getParameter("id_historial");

            Pacientes buscarPaciente = this.mi.getDatosPaciente(Integer.parseInt(id_paciente));
            modelo.put("nombres", buscarPaciente.getNombres());
            modelo.put("id_historial", id_historial);
            modelo.put("id_paciente", id_paciente);
            return new ModelAndView("administrarfarmacias/EntregaPaciente", modelo);
        }

        if ("entregarmed".equals(accion)) {
            String id_pedido = request.getParameter("id_pedido");
            // recupera el paciente a entregar
            Pacientes paciente1 = new Pacientes();
            paciente1.setId_pedido(Integer.parseInt(id_pedido));
            paciente1.setCod_esta(cliente.getCod_esta());
            Pacientes buscarPaciente = this.mi.getDatosPedido(paciente1);
            modelo.put("datos", buscarPaciente);
            // recupera los medicamentos del paciente a entregar   
            Recetas midato = new Recetas();
            midato.setId_pedido(Integer.parseInt(id_pedido));
            midato.setId_prestacion(cliente.getCod_esta());
            midato.setCod_esta(cliente.getCod_esta());
            midato.setId_farmacia(cliente.getId_farmacia());
            List listarKardex = this.mi.getListarKardex(midato);
            modelo.put("listarKardex", listarKardex);
            return new ModelAndView("administrarfarmacias/EntregaMedPaciente", modelo);
        }

        if ("previa".equals(accion)) {
            String id_historial = request.getParameter("id_historial");
            String id_paciente = request.getParameter("id_paciente");
            String nombres = request.getParameter("nombres");
            String nombre = request.getParameter("nombre");
            String id_pedido = request.getParameter("id_pedido");

            Pacientes buscarPaciente = this.mi.getDatosPaciente(Integer.parseInt(id_paciente));

            Recetas datore = new Recetas();
            datore.setId_historial(Integer.parseInt(id_historial));
            datore.setId_estado("A");
            datore.setId_farmacia(cliente.getId_farmacia());
            datore.setCod_esta(cliente.getCod_esta());
            List listarRecetasP = this.mi.getListarRecetas(datore);
            modelo.put("listarRecetasP", listarRecetasP);
            modelo.put("nombres", nombres);
            modelo.put("nombre", nombre);
            modelo.put("id_paciente", id_paciente);
            modelo.put("id_historial", id_historial);
            modelo.put("edad", Integer.toString(buscarPaciente.getEdad()));
            modelo.put("mes", Integer.toString(buscarPaciente.getMes()));
            modelo.put("dia", Integer.toString(buscarPaciente.getDia()));
            return new ModelAndView("administrarfarmacias/EntregaRecetaPrevia", modelo);
        }

        if ("Terminar".equals(accion)) {
            String id_pedido = request.getParameter("id_pedido");
            // recupera el paciente a entregar
            Pacientes paciente1 = new Pacientes();
            paciente1.setId_pedido(Integer.parseInt(id_pedido));
            paciente1.setCod_esta(cliente.getCod_esta());
            Pacientes buscarPaciente = this.mi.getDatosPedido(paciente1);
            modelo.put("datos", buscarPaciente);
            buscarPaciente.setId_estado("E");
            buscarPaciente.setId_dispensa(cliente.getId_persona());
            buscarPaciente.setFecha_fin(Fecha1);
            this.mi.setModificarPedido(buscarPaciente);
        }

        //lista de paciente que fueron atendidos con el medico
        Historiales dato = new Historiales();
        dato.setId_estado("A");
        dato.setExpedido("E");
        dato.setCod_esta(cliente.getCod_esta());
        List listarAtendidos = this.mi.getListarAtendidos(dato);
        modelo.put("listarAtendidos", listarAtendidos);

        List listarAtendidosI = this.mi.getListarAtendidosI(dato);
        modelo.put("listarAtendidosI", listarAtendidosI);

        // lista de pacientes que aun no pagaron en seccion cobranza
        Pacientes paciente = new Pacientes();
        paciente.setId_estado("A");///estado sin pago
        paciente.setId_rubro(1);///rubro 1 de farmacia
        paciente.setId_farmacia(cliente.getId_farmacia());
        paciente.setCod_esta(cliente.getCod_esta());
        List listarSinPago = this.mi.getListarCobroRubroFar(paciente);
        modelo.put("listapago", listarSinPago);

        // lista de pacientes que no terminaron su receta
        paciente.setId_estado("X");
        List listarSinPago1 = this.mi.getListarCobroRubroFar(paciente);
        modelo.put("listapago1", listarSinPago1);

        // lista de pacientes que pagaron en seccion cobranza
        paciente.setId_estado("C");
        List listarPaises = this.mi.getListarCobroRubroFar(paciente);
        modelo.put("milista", listarPaises);

        List listarSeguros = this.mi.getListarSeguros("A");
        modelo.put("listaPacAfi", listarSeguros);

        if (listarAtendidos.size() > 0) {
            modelo.put("listafar", "1");
        }
        if (listarPaises.size() > 0) {
            modelo.put("listacancelados", "1");
        }
        if (listarSinPago.size() > 0 || listarSinPago1.size() > 0) {
            modelo.put("listaporcancel", "1");
        }
        if (listarAtendidosI.size() > 0) {
            modelo.put("listafari", "1");
        }

        return new ModelAndView("administrarfarmacias/ListarAtendidosVenta", modelo);

    }
}
