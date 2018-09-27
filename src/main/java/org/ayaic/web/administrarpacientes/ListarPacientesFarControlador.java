package org.ayaic.web.administrarpacientes;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Localidades;
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ListarPacientesFarControlador {

    private final MiFacade mi;

    public ListarPacientesFarControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/ListarPacientesFar.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String codesta1 = request.getParameter("codesta1");
        String boton = request.getParameter("boton");
        String accion = request.getParameter("accion");

        Localidades localidad = new Localidades();
        List Estab1 = this.mi.getListarEsta(localidad);
        Localidades datoestab = (Localidades) Estab1.get(0);

//    String num= Integer.toString(this.mi.getNum(cliente.getId_usuario()));
//    modelo.put("num", num);
        if ("CobroReceta".equals(accion)) {
            String id_paciente = request.getParameter("id_paciente");
            Pacientes datopac = mi.getDatosPaciente(Integer.parseInt(id_paciente));
            modelo.put("datopac", datopac);
            modelo.put("nombres", datopac.getNombres());
            modelo.put("id_paciente", id_paciente);

            return new ModelAndView("administrarfarmacias/EntregaPacienteFar", modelo);
        }

        if ("BuscarN".equals(boton)) {
            String nombres = request.getParameter("nombre");
            nombres = nombres.trim();
            nombres = nombres.replaceAll(" +", " ");///quita espacios en blanco entre cadenas
            nombres = nombres.replaceAll("\\s", ":*&");////:*&
            if (nombres.length() > 0) {
                nombres = nombres + ":*";
            }
            //nombres = "%" + nombres + "%";
            Pacientes paciente = new Pacientes();
            paciente.setNombres(nombres);

            paciente.setId_estado(request.getParameter("id_estado"));

            List listarPaises = this.mi.getListarPacientes(paciente);
            modelo.put("listaPacientes", listarPaises);
        }
        if ("BuscarH".equals(boton)) {

            String hcl = request.getParameter("hcl");
            Pacientes paciente = new Pacientes();
            paciente.setHcl(Integer.parseInt(hcl));

            List listarPaises = this.mi.getListarPacientesHC(paciente);
            modelo.put("listaPacientes", listarPaises);
        }

        if ("BuscarF".equals(boton)) {
            String aux_dia = request.getParameter("dia");
            String aux_mes = request.getParameter("mes");
            String aux_anio = request.getParameter("anio");
            if ((aux_dia == null) || (aux_mes == null) || (aux_anio == null)) {
                return new ModelAndView("administrarpacientes/ListarPacientesFar", modelo);
            } else {
                int dia = Integer.parseInt(aux_dia);
                int mes = Integer.parseInt(aux_mes) - 1;
                int anio = Integer.parseInt(aux_anio) - 1900;

                Date fecha = new Date(anio, mes, dia);

                Pacientes paciente = new Pacientes();
                paciente.setFec_nacimiento(fecha);

                List listaDePacientes = this.mi.getListarPacientesFN(paciente);
                modelo.put("listaPacientes", listaDePacientes);
            }
        }

        return new ModelAndView("administrarpacientes/ListarPacientesFar", modelo);

    }
}
