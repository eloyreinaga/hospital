package org.ayaic.web.administrarconsultorios;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Consultorios;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ConfirmarConsultorioControlador {

    private final MiFacade mi;

    public ConfirmarConsultorioControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/ConfirmarConsultorio.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String accion1 = request.getParameter("accion1");

        String id_consultorio = request.getParameter("id_consultorio");
        String cargo = request.getParameter("consultorio");
        String descripcion = request.getParameter("descripcion");
        String id_estado = request.getParameter("id_estado");
        String id_cargo = request.getParameter("id_cargo");
        Consultorios pai = new Consultorios();

        if ("Adicionar".equals(accion)) {
            //pai.setId_consultorio(Integer.parseInt(id_consultorio)) ;
            pai.setConsultorio(cargo);
//      pai.setId_cargo(Integer.parseInt(id_cargo));
            pai.setDescripcion(descripcion);

            modelo.put("dato", pai);
            modelo.put("accion", accion);
            modelo.put("accion1", accion1);
        }

        if ("Modificar".equals(accion)) {
            pai.setId_consultorio(Integer.parseInt(id_consultorio));
            pai.setConsultorio(cargo);
            //    pai.setId_cargo(Integer.parseInt(id_cargo));
            pai.setDescripcion(descripcion);
            pai.setId_estado(id_estado);

            modelo.put("dato", pai);
            modelo.put("sw", request.getParameter("sw"));
            modelo.put("accion", accion);
            modelo.put("accion1", accion1);
            modelo.put("id_cargo", id_cargo);
        }
        if ("Eliminar".equals(accion)) {
            Consultorios buscarCargo = this.mi.getDatosConsultorio(Integer.parseInt(id_consultorio)); // saca un registro a ser modificado
            modelo.put("dato", buscarCargo);

            modelo.put("accion", accion);
            modelo.put("sw1", request.getParameter("sw1"));
            modelo.put("id_consultorio", id_consultorio);

        }

        return new ModelAndView("administrarconsultorios/ConfirmarConsultorio", modelo);

    }
}
