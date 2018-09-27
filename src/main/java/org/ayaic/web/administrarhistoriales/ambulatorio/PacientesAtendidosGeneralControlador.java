package org.ayaic.web.administrarhistoriales.ambulatorio;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Consultorios;
import org.ayaic.domain.Historiales;
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.Personas;
import org.ayaic.domain.Prestaciones;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PacientesAtendidosGeneralControlador {

    private final MiFacade mi;

    public PacientesAtendidosGeneralControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/ListarAmbulatorioGen.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String id_paciente = request.getParameter("id_paciente");
        Personas persona = this.mi.getBuscarPersona(cliente.getId_persona()); // saca un registro a ser modificado
        Consultorios datosconsul = this.mi.getDatosConsultorio(persona.getId_consultorio()); // saca un registro a ser modificado
        modelo.put("tipo_medico", Integer.toString(datosconsul.getId_cargo()));
        //Listar Paises
        List listarPaises = this.mi.getListarPersonas(persona);
        modelo.put("listaPersonas", listarPaises);
        //Recuperamos variables del jsp
        Pacientes buscarPaciente = this.mi.getDatosPaciente(Integer.parseInt(id_paciente)); // saca un registro a ser modificado
        modelo.put("datos", buscarPaciente);

        String sId_estado = request.getParameter("id_estado");
        String sAccion = request.getParameter("boton");
        String id_persona = request.getParameter("id_persona");

        Prestaciones datopre = new Prestaciones();
        if (id_persona == null) {
            datopre.setId_persona(cliente.getId_persona());
        } else {
            datopre.setId_persona(Integer.parseInt(id_persona));
        }

        Historiales datohis = new Historiales();
        if (id_persona == null) {
            datohis.setId_persona(cliente.getId_persona());
        } else {
            datohis.setId_persona(Integer.parseInt(id_persona));
        }

        if ("Resumen".equals(sAccion)) {
            datohis.setAccion("T");
            List listarAtendidos = this.mi.getHistorialAtendidos(datohis);
            modelo.put("milistaAten", listarAtendidos);
            return new ModelAndView("administrarhistoriales/ListarAtendidos", modelo);
        }
        modelo.put("nombres", buscarPaciente.getNombres());
        modelo.put("hcl", Integer.toString(buscarPaciente.getHcl()));
        modelo.put("dato", cliente);
        datohis.setId_paciente(Integer.parseInt(id_paciente));
        datopre.setId_persona(Integer.parseInt(id_paciente));
        List listaConsultorios = this.mi.getListarPacientesHistoGeneral(datohis);
        modelo.put("listaPacientes", listaConsultorios);

        List prestacionesDadas = this.mi.getListarPrestacionesGen(datopre);
        modelo.put("prestacionesDadas", prestacionesDadas);

        List pacientePrestacion = this.mi.getListarPacientesPrestacionesGen(datopre);
        modelo.put("pacientePrestacion", pacientePrestacion);

        return new ModelAndView(new ListarConsultaGeneralPDF(), modelo);
    }
}
