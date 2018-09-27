package org.ayaic.web.administrarlaboratorio;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Consultorios;
import org.ayaic.domain.Cuadernos;
import org.ayaic.domain.Historiales;
import org.ayaic.domain.Localidades;
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.Personas;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PedirLaboratorioControlador {

    private final MiFacade mi;

    public PedirLaboratorioControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/PedirLaboratorio.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        Personas persona = this.mi.getBuscarPersona(cliente.getId_persona());
        Consultorios datosconsul = this.mi.getDatosConsultorio(persona.getId_consultorio()); // saca un registro a ser modificado

        String tipo = "L";
        String accion = request.getParameter("accion");
        String id_historial = request.getParameter("id_historial");
        String id_pedido = request.getParameter("id_pedido");
        String id_paciente = request.getParameter("id_paciente");
        String expedido = request.getParameter("expedido");

        Localidades local = new Localidades();
        List Estab1 = this.mi.getListarEsta(local);
        Localidades datoestab = (Localidades) Estab1.get(0);
        modelo.put("datoestab", datoestab);
        modelo.put("dato", cliente);

        List listarSeguros = this.mi.getListarSeguros("A");
        modelo.put("listaPacAfi", listarSeguros);
        modelo.put("id_pedido", id_pedido);

        Cuadernos dato = new Cuadernos();
        dato.setTipo(tipo);
        dato.setId_pedido(Integer.parseInt(id_pedido));
        dato.setId_historial(Integer.parseInt(id_historial));
        dato.setCod_esta(cliente.getCod_esta());
        if (cliente.getId_laboratorio() == 0) {
            dato.setId_cargo(0);
            dato.setId_laboratorio(999);
        } else {
            dato.setId_cargo(cliente.getId_laboratorio());
            dato.setId_laboratorio(cliente.getId_laboratorio());
        }

        if (datosconsul.getId_cargo() == 18) {
            modelo.put("consultorio", Integer.toString(10));
            tipo = "M";   /// si es desde medicos se pueda ver el reporte de laboratrorio
            List listalab = this.mi.getLabPendienteMed(dato);
            modelo.put("datosLab", listalab);
        } else {
            modelo.put("consultorio", Integer.toString(datosconsul.getId_cargo()));
        }
        if (datosconsul.getId_cargo() != 12 && datosconsul.getId_cargo() != 11) {
            List listalab = this.mi.getLabPendiente(dato);
            modelo.put("datosLab", listalab);
        }
        if (datosconsul.getId_cargo() == 11) {
            List listalab = this.mi.getLabPendienteEco(dato);
            modelo.put("datosLab", listalab);
        }
        if (datosconsul.getId_cargo() == 12) {
            List listalab = this.mi.getLabPendienteRx(dato);
            modelo.put("datosLab", listalab);
        }

        Historiales datohi = new Historiales();
        datohi.setId_historial(Integer.parseInt(id_historial));
        datohi.setCod_esta(cliente.getCod_esta());
        Historiales datoHis = this.mi.getDatosHistorial(datohi);
        modelo.put("datosHis", datoHis);

        Pacientes paciente = new Pacientes();
        paciente.setTipo("U");
        paciente.setId_paciente(datoHis.getId_paciente());
        List buscarPacEmp = this.mi.getListarPacientesCnsUnico(paciente);
        modelo.put("datosPacEmp", buscarPacEmp);

        Personas buscarEmpleado = this.mi.getDatosPersona(datoHis.getId_persona());
        modelo.put("datosMed", buscarEmpleado);

        Pacientes buscarPaciente = this.mi.getDatosPaciente(datoHis.getId_paciente());
        modelo.put("datos", buscarPaciente);
        if (datoestab.getCod_esta() == 200010) {
            return new ModelAndView(new PedirLaboratorioCNSPDF(), modelo);
        } else {
            return new ModelAndView(new PedirLaboratorioPDF(), modelo);
        }
    }
}
