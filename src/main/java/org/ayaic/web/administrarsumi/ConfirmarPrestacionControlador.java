package org.ayaic.web.administrarsumi;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ayaic.domain.Clientes;
import org.ayaic.domain.Prestaciones;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ConfirmarPrestacionControlador {

    private final MiFacade mi;

    public ConfirmarPrestacionControlador(MiFacade mi) {
        this.mi = mi;
    }
    
    @RequestMapping("/ConfirmarPrestacion.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");

        String accion = request.getParameter("accion");
        String accion1 = request.getParameter("accion1");
        String id_prestacion = request.getParameter("id_prestacion");
        String internado = request.getParameter("internado");
        String veces = request.getParameter("veces");
        String costo = request.getParameter("costo");
        String descripcion = request.getParameter("descripcion");
        String paquete = request.getParameter("paquete");

        Prestaciones pai = new Prestaciones();

        if ("Adicionar".equals(accion)) {

            pai.setDescripcion(descripcion);
            pai.setCosto(Float.parseFloat(costo));
            pai.setPaquete(paquete);

            modelo.put("dato", pai);
            modelo.put("accion", accion);
            modelo.put("accion1", accion1);
        }

        if ("Modificar".equals(accion)) {

            pai.setId_prestacion(Integer.parseInt(id_prestacion));
            pai.setSuma1(Integer.parseInt(internado));
            pai.setSuma2(Integer.parseInt(veces));
            pai.setDescripcion(descripcion);
            pai.setCosto(Float.parseFloat(costo));
            pai.setPaquete(paquete);

            modelo.put("dato", pai);
            modelo.put("sw", request.getParameter("sw"));
            modelo.put("accion", accion);
            modelo.put("accion1", accion1);
        }

        if ("Eliminar".equals(accion)) {
            Prestaciones buscarPres = this.mi.getDatosPrestacion(Integer.parseInt(id_prestacion)); // saca un registro a ser modificado
            modelo.put("dato", buscarPres);

            modelo.put("accion", accion);
            modelo.put("sw1", request.getParameter("sw1"));
            modelo.put("id_prestacion", id_prestacion);
        }

        return new ModelAndView("administrarsumi/ConfirmarPrestacion", modelo);

    }
}
