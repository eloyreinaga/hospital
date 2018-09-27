package org.ayaic.web.administrarcarpetas;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Carpetas;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ConfirmarCarpetaControlador {

    private final MiFacade mi;

    public ConfirmarCarpetaControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/ConfirmarCarpeta.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String accion1 = request.getParameter("accion1");

        String id_carpeta = request.getParameter("id_carpeta");
        String carpeta = request.getParameter("carpeta");
        String id_estado = request.getParameter("id_estado");
        Carpetas pai = new Carpetas();
        String id_pacientej = request.getParameter("id_pacientej");

        modelo.put("id_pacientej", id_pacientej);
        if ("Adicionar".equals(accion)) {

            Pacientes buscarPaciente = this.mi.getDatosPaciente(Integer.parseInt(id_pacientej)); // saca un registro a ser modificado
            modelo.put("datos", buscarPaciente);

            modelo.put("sw", request.getParameter("sw"));
            modelo.put("accion", accion);
            modelo.put("accion1", accion1);
        }
        if ("ModificarCarpeta".equals(accion)) {
            Carpetas buscarCarpe = this.mi.getDatosCarpeta(Integer.parseInt(id_carpeta)); // saca un registro a ser modificado
            modelo.put("dato", buscarCarpe);

            Pacientes buscarPaciente = this.mi.getDatosPaciente(Integer.parseInt(id_pacientej)); // saca un registro a ser modificado
            modelo.put("datos", buscarPaciente);

            Carpetas buscarCarpeta = new Carpetas();
            buscarCarpeta.setId_paciente(Integer.parseInt(id_pacientej));
            buscarCarpeta.setId_carpeta(Integer.parseInt(id_carpeta));
            buscarCarpeta.setId_estado("A");
            modelo.put("id_carpeta2", id_carpeta);
            modelo.put("accion", "ModificarCarpeta");
        }
        if ("Eliminar".equals(accion)) {
            Carpetas buscarCarpeta = this.mi.getDatosCarpeta(Integer.parseInt(id_carpeta)); // saca un registro a ser modificado
            modelo.put("dato", buscarCarpeta);

            Pacientes buscarPaciente = this.mi.getDatosPaciente(Integer.parseInt(id_pacientej)); // saca un registro a ser modificado
            modelo.put("datos", buscarPaciente);

            //datos de los dependientes
            List listarP = this.mi.getListarPacientesD(Integer.parseInt(id_carpeta));
            modelo.put("listaPacientesD", listarP);

            modelo.put("accion", accion);
            modelo.put("sw1", request.getParameter("sw1"));
            modelo.put("id_carpeta", id_carpeta);
        }
        return new ModelAndView("administrarcarpetas/ConfirmarCarpeta", modelo);
    }
}
