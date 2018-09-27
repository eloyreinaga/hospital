package org.ayaic.web.administrarfarmacias;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Medicamentos;
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.Recetas;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RecetaPacInternadoControlador {

    private final MiFacade mi;

    public RecetaPacInternadoControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("RecetarPacInternado.do")////no existe el *.do se aumento asi no mas
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws UnknownHostException {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");

        String nombres = request.getParameter("nombres");
        String nombres2 = request.getParameter("nombres");
        String id_medicamento = request.getParameter("id_medicamento");
        String cantidad = request.getParameter("cantidad");
        String indicacion = request.getParameter("indicacion");

        String id_paciente = request.getParameter("id_paciente");
        String id_reservacion = request.getParameter("id_reservacion");
        String id_consultorio = request.getParameter("id_consultorio");
        String id_persona = request.getParameter("id_persona");
        String expedido = request.getParameter("expedido");
        String tipo_medico = request.getParameter("tipo_medico");
        String spam = request.getParameter("spam");
        InetAddress ip;
        ip = InetAddress.getLocalHost();

        Pacientes buscarPaciente = this.mi.getDatosPaciente(Integer.parseInt(id_paciente)); // saca un registro a ser modificado
        modelo.put("datos", buscarPaciente);

        if ("adicion".equals(accion)) {
            String dosifica = request.getParameter("dosifica");
            Medicamentos medic = new Medicamentos();
            medic.setId_medicamento(Integer.parseInt(id_medicamento));
            medic.setCodigo(cliente.getCod_esta());
            medic.setCod_esta(cliente.getCod_esta());
            medic.setId_farmacia(cliente.getId_farmacia());
            medic.setExpedido("A");
            Medicamentos datoMedicamento = this.mi.getDatosMedicamento(medic);
            if ((dosifica == null || "".equals(dosifica)) && "M".equals(datoMedicamento.getTipo())) {
                return new ModelAndView("Aviso", "mensaje", "Debe elegir el tiempo de Dosificacion del Medicamento");
            }
            if ((dosifica == null || "".equals(dosifica))) {
                dosifica = "0";
            }

            Recetas dato = new Recetas();
            dato.setId_historial(Integer.parseInt(id_reservacion));
            dato.setId_medicamento(Integer.parseInt(id_medicamento));
            dato.setId_historia(0);
            dato.setSalida(Double.parseDouble(cantidad));
            dato.setIndicacion(indicacion);
            dato.setDosifica(Integer.parseInt(dosifica));
            dato.setCod_esta(cliente.getCod_esta());
            // CAMBIA EL ESTADO A VENTA
            dato.setExpedido("E".equals(expedido) ? "V" : expedido);
            try {
                this.mi.setCrearReceta(dato);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "La actualizacion no se cumplio");
            }
        }
        if ("eliminar".equals(accion)) {
            String id_detalle = request.getParameter("id_detalle");
            Recetas dato = new Recetas();
            dato.setId_historial(Integer.parseInt(id_reservacion));
            dato.setId_medicamento(Integer.parseInt(id_medicamento));
            dato.setId_detalle(Integer.parseInt(id_detalle));

            dato.setMedico(ip.getHostAddress());///20/07/2014
            dato.setMedicamento(ip.getHostName());///20/07/2014
            dato.setIndicacion("E");///20/07/2014
            dato.setId_paciente(cliente.getId_persona());///20/07/2014
            dato.setCod_esta(cliente.getCod_esta());///20/07/2014
            this.mi.setEliminarReceta(dato);
        }
        Medicamentos dato = new Medicamentos();
        dato.setId_persona(Integer.parseInt(id_persona));
        dato.setExpedido(expedido);
        dato.setCod_esta(cliente.getCod_esta());
        dato.setId_farmacia(cliente.getId_farmacia());

        Recetas datore = new Recetas();
        datore.setId_historial(Integer.parseInt(id_reservacion));
        datore.setId_farmacia(cliente.getId_farmacia());
        datore.setCod_esta(cliente.getCod_esta());
        datore.setId_estado("%");
        if (nombres != null) {
            nombres = nombres.replaceAll("\\s", "%");
            nombres2 = "%" + nombres + "%";
        } else {
            nombres2 = "";
        }

        if ("si".equals(spam)) {
            List listarMedicamentosCot = this.mi.getListarMedicamentosCotb1(dato);
            modelo.put("listarMedicamentosCot", listarMedicamentosCot);

            List listarRecetas = this.mi.getListarRecetas(datore);
            modelo.put("listarRecetas", listarRecetas);

            dato.setCod_esta(cliente.getCod_esta());
            dato.setMedicamento(nombres2);
            List listarMedicamentos = this.mi.getListarMedicamentosb1(dato);
            modelo.put("listarMedicamentos", listarMedicamentos);
            modelo.put("spam", "si");
        } else {
            List listarMedicamentosCot = this.mi.getListarMedicamentosCot(dato);
            modelo.put("listarMedicamentosCot", listarMedicamentosCot);

            List listarRecetas = this.mi.getListarRecetas(datore);
            modelo.put("listarRecetas", listarRecetas);

            dato.setCod_esta(cliente.getCod_esta());
            dato.setMedicamento(nombres2);
            List listarMedicamentos = this.mi.getListarMedicamentos(dato);
            modelo.put("listarMedicamentos", listarMedicamentos);
        }

        modelo.put("nombres", nombres);
        modelo.put("id_paciente", id_paciente);
        modelo.put("id_reservacion", id_reservacion);
        modelo.put("id_consultorio", id_consultorio);
        modelo.put("id_persona", id_persona);
        modelo.put("expedido", expedido);
        modelo.put("tipo_medico", tipo_medico);
        return new ModelAndView("administrarhistoriales/RecetarPaciente", modelo);
    }
}
