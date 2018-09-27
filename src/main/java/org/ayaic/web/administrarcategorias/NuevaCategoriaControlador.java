package org.ayaic.web.administrarcategorias;

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
public class NuevaCategoriaControlador {

    private final MiFacade mi;

    public NuevaCategoriaControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/NuevaCategoria.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String id_categoria = request.getParameter("id_categoria");

        modelo.put("id_categoria", request.getParameter("id_categoria"));

        //Para la primera vez que entra a la pagina
        if ((request.getParameter("id_categoria") != null) && ("Modificar".equals(accion)) && (request.getParameter("recargado") == null)) {
            //Sacamos los datos del usuario
            Menues buscarCat = this.mi.getCategoria(id_categoria); // saca un registro a ser modificado
            modelo.put("buscarCat", buscarCat);

            modelo.put("id_categoria", Integer.toString(buscarCat.getId_categoria()));
            modelo.put("categoria", buscarCat.getCategoria());
            modelo.put("imagen", buscarCat.getImagen());
            modelo.put("id_estado", buscarCat.getId_estado());
            modelo.put("sw", request.getParameter("sw"));
        }
        modelo.put("accion", accion);
        return new ModelAndView("administrarcategorias/NuevaCategoria", modelo);
    }
}
