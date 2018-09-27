package org.ayaic.web.administrarcargos;

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
public class ConfirmarCargoControlador {
    
    private final MiFacade mi;
    
    public ConfirmarCargoControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/ConfirmarCargo.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String accion1 = request.getParameter("accion1");

        String id_cargo = request.getParameter("id_cargo");
        String cargo = request.getParameter("cargo");
        String descripcion = request.getParameter("descripcion");
        String id_estado = request.getParameter("id_estado");
        Consultorios pai = new Consultorios();

        if ("Adicionar".equals(accion)) {

            pai.setId_cargo(Integer.parseInt(id_cargo));
            pai.setCargo(cargo);
            pai.setDescripcion(descripcion);

            modelo.put("dato", pai);
            modelo.put("accion", accion);
            modelo.put("accion1", accion1);
        }

        if ("Modificar".equals(accion)) {

            pai.setId_cargo(Integer.parseInt(id_cargo));
            pai.setCargo(cargo);
            pai.setDescripcion(descripcion);
            pai.setId_estado(id_estado);

            modelo.put("dato", pai);
            modelo.put("sw", request.getParameter("sw"));
            modelo.put("accion", accion);
            modelo.put("accion1", accion1);
        }

        if ("Eliminar".equals(accion)) {
            Consultorios buscarCargo = this.mi.getDatosCargo(Integer.parseInt(id_cargo)); // saca un registro a ser modificado
            modelo.put("dato", buscarCargo);

            modelo.put("accion", accion);
            modelo.put("sw1", request.getParameter("sw1"));
            modelo.put("id_cargo", id_cargo);
        }

        return new ModelAndView("administrarcargos/ConfirmarCargo", modelo);

    }
}
