package org.ayaic.web.administrarlaboratorios;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Consultorios;
import org.ayaic.domain.Cuadernos;
import org.ayaic.domain.Historiales;
import org.ayaic.domain.Laboratorios;
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.Personas;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ConfirmarLaboratorioControlador {

    private final MiFacade mi;

    public ConfirmarLaboratorioControlador(MiFacade mi) {
        this.mi = mi;
    }
    
    @RequestMapping("/ConfirmarLaboratorio.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String accion1 = request.getParameter("accion1");

        String id_laboratorio = request.getParameter("id_laboratorio");
        String laboratorio = request.getParameter("laboratorio");
        String id_estado = request.getParameter("id_estado");
        Laboratorios pai = new Laboratorios();

        Personas persona = this.mi.getBuscarPersona(cliente.getId_persona());
        Consultorios datosconsul = this.mi.getDatosConsultorio(persona.getId_consultorio()); // saca un registro a ser modificado

        if ("Adicionar".equals(accion)) {

            pai.setLaboratorio(laboratorio);

            modelo.put("dato", pai);
            modelo.put("accion", accion);
            modelo.put("accion1", accion1);
        }
        if ("Modificar".equals(accion)) {

            pai.setId_laboratorio(Integer.parseInt(id_laboratorio));
            pai.setLaboratorio(laboratorio);
            pai.setId_estado(id_estado);

            modelo.put("dato", pai);
            modelo.put("sw", request.getParameter("sw"));
            modelo.put("accion", accion);
            modelo.put("accion1", accion1);
        }
        if ("Imprimir".equals(accion)) {

            String tipo = "L";
            String id_historial = request.getParameter("id_historial");
            String id_reservacion = request.getParameter("id_reservacion");
            String id_paciente = request.getParameter("id_paciente");
            String expedido = request.getParameter("expedido");
            modelo.put("dato", cliente);

            Cuadernos dato = new Cuadernos();
            dato.setTipoconsulta(tipo);
            dato.setId_pedido(Integer.parseInt(id_historial));
            dato.setId_historial(Integer.parseInt(id_historial));
            dato.setCod_esta(cliente.getCod_esta());
            if (cliente.getId_laboratorio() == 0) {
                dato.setId_cargo(0);
                dato.setId_laboratorio(999);
            } else {
                dato.setId_cargo(cliente.getId_laboratorio());
                dato.setId_laboratorio(cliente.getId_laboratorio());
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

            Personas buscarEmpleado = this.mi.getDatosPersona(datoHis.getId_persona());
            modelo.put("datosMed", buscarEmpleado);

            Pacientes buscarPaciente = this.mi.getDatosPaciente(datoHis.getId_paciente());
            modelo.put("datos", buscarPaciente);
            return new ModelAndView("administrarlaboratorio/ImprimirLab", modelo);
        }
        if ("Eliminar".equals(accion)) {
            //Laboratorios pai = new Laboratorios();  
            pai.setId_laboratorio(Integer.parseInt(id_laboratorio));
            Laboratorios buscarLaboratorio = this.mi.getDatosLaboratorio(pai); // saca un registro a ser modificado
            modelo.put("dato", buscarLaboratorio);

            modelo.put("accion", accion);
            modelo.put("sw1", request.getParameter("sw1"));
            modelo.put("id_laboratorio", id_laboratorio);
        }

        return new ModelAndView("administrarlaboratorios/ConfirmarLaboratorio", modelo);

    }
}
