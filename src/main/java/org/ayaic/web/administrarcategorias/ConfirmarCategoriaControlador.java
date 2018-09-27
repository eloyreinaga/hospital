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
public class ConfirmarCategoriaControlador {

    private final MiFacade mi;

    public ConfirmarCategoriaControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/ConfirmarCategoria.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String accion1 = request.getParameter("accion1");

        String id_categoria = request.getParameter("id_categoria");
        String categoria = request.getParameter("categoria");
        String imagen = request.getParameter("imagen");

        Menues pai = new Menues();

        if ("Adicionar".equals(accion)) {

            //    pai.setId_categoria(Integer.parseInt(request.getParameter("id_categoria"))) ;
            pai.setCategoria(request.getParameter("categoria"));
            pai.setImagen(request.getParameter("imagen"));

            modelo.put("cate", pai);
            modelo.put("accion", accion);
            modelo.put("accion1", accion1);

        }

        if ("Modificar".equals(accion)) {

            pai.setId_categoria(Integer.parseInt(request.getParameter("id_categoria")));
            pai.setCategoria(request.getParameter("categoria"));
            pai.setImagen(request.getParameter("imagen"));
            pai.setId_estado(request.getParameter("id_estado"));

            modelo.put("cate", pai);
            modelo.put("sw", request.getParameter("sw"));
            modelo.put("accion", accion);
            modelo.put("accion1", accion1);
        }

        if ("Eliminar".equals(accion)) {
            Menues buscarCat = this.mi.getCategoria(id_categoria);   // saca un registro a ser modificado
            modelo.put("cate", buscarCat);

            modelo.put("accion", accion);
            modelo.put("sw1", request.getParameter("sw1"));
            modelo.put("id_categoria", request.getParameter("id_categoria"));
        }

        return new ModelAndView("administrarcategorias/ConfirmarCategoria", modelo);

    }
}
