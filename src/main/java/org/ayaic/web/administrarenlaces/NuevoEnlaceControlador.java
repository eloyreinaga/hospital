package org.ayaic.web.administrarenlaces;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Menues;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NuevoEnlaceControlador {

    private final MiFacade mi;

    public NuevoEnlaceControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/NuevoEnlace.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String id_enlace = request.getParameter("id_enlace");

        //Listamos las categorias
        List listarCategorias = this.mi.getListaCategorias();
        modelo.put("listarCategorias", listarCategorias);

        modelo.put("id_enlace", request.getParameter("id_enlace"));

        //Para la primera vez que entra a la pagina
        if ((request.getParameter("id_enlace") != null) && ("Modificar".equals(accion)) && (request.getParameter("recargado") == null)) {
            //Sacamos los datos del enlace
            Menues buscarEnlace = this.mi.getEnlace(Integer.parseInt(id_enlace)); // saca un registro a ser modificado
            modelo.put("buscarEnlace", buscarEnlace);

            modelo.put("id_categoria", Integer.toString(buscarEnlace.getId_categoria()));
            modelo.put("id_enlace", Integer.toString(buscarEnlace.getId_enlace()));
            modelo.put("enlace", buscarEnlace.getEnlace());
            modelo.put("orden", Integer.toString(buscarEnlace.getOrden()));
            modelo.put("ruta", buscarEnlace.getRuta());
            modelo.put("imagen", buscarEnlace.getImagen());
            modelo.put("id_estado", buscarEnlace.getId_estado());
            modelo.put("sw", request.getParameter("sw"));
        }
        modelo.put("accion", accion);
        return new ModelAndView("administrarenlaces/NuevoEnlace", modelo);
    }
}
