package org.ayaic.web.administrarenlaces;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Menues;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ConfirmarEnlaceControlador {

    private final MiFacade mi;

    public ConfirmarEnlaceControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/ConfirmarEnlace.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String accion1 = request.getParameter("accion1");

        String id_enlace = request.getParameter("id_enlace");
        String id_categoria = request.getParameter("id_categoria");
        String enlace = request.getParameter("enlace");
        String ruta = request.getParameter("ruta");
        String orden = request.getParameter("orden");
        String imagen = request.getParameter("imagen");

        Menues pai = new Menues();

        if ("Adicionar".equals(accion)) {

            pai.setEnlace(request.getParameter("enlace"));
            pai.setRuta(request.getParameter("ruta"));
            pai.setOrden(Integer.parseInt(request.getParameter("orden")));
            pai.setImagen(request.getParameter("imagen"));

            //Busca categoria
            try {
                Menues buscarCat = this.mi.getCategoria(id_categoria);
                modelo.put("buscarCat", buscarCat);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "Faltan datos");
            }

            modelo.put("enlaces", pai);
            modelo.put("id_categoria", request.getParameter("id_categoria"));
            modelo.put("accion", accion);
            modelo.put("accion1", accion1);

        }

        if ("Modificar".equals(accion)) {

            pai.setId_enlace(Integer.parseInt(request.getParameter("id_enlace")));
            pai.setEnlace(request.getParameter("enlace"));
            pai.setRuta(request.getParameter("ruta"));
            pai.setOrden(Integer.parseInt(request.getParameter("orden")));
            pai.setImagen(request.getParameter("imagen"));
            pai.setId_estado(request.getParameter("id_estado"));

            //Busca categoria
            try {
                Menues buscarCat = this.mi.getCategoria(id_categoria);
                modelo.put("buscarCat", buscarCat);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "Faltan datos");
            }

            modelo.put("enlaces", pai);
            modelo.put("id_categoria", request.getParameter("id_categoria"));
            modelo.put("sw", request.getParameter("sw"));
            modelo.put("accion", accion);
            modelo.put("accion1", accion1);
        }

        if ("Eliminar".equals(accion)) {
            Menues buscarEnlace = this.mi.getEnlace(Integer.parseInt(id_enlace));
            String id_categoria_e = Integer.toString(buscarEnlace.getId_categoria());  //Saca id_categoria
            modelo.put("enlaces", buscarEnlace);
            //Buscar Empleado
            Menues buscarCat = this.mi.getCategoria(id_categoria_e);
            modelo.put("buscarCat", buscarCat);

            modelo.put("accion", accion);
            modelo.put("sw1", request.getParameter("sw1"));
            modelo.put("id_enlace", request.getParameter("id_enlace"));
        }

        return new ModelAndView("administrarenlaces/ConfirmarEnlace", modelo);

    }
}
