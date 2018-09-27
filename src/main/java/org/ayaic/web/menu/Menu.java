package org.ayaic.web.menu;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.ayaic.domain.Categorias;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Enlaces;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Menu {

    private final MiFacade mi;

    public Menu(MiFacade mi) {
        this.mi = mi;
    }
    
   @RequestMapping("/menu.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        Categorias categoria = new Categorias();
        categoria.setId_rol(cliente.getId_rol());
        List listaCategorias = this.mi.getListarCategorias(categoria);
        for (int i = 0; i < listaCategorias.size(); i++) {
            Categorias aux = (Categorias) listaCategorias.get(i);
            Enlaces enlace = new Enlaces();
            enlace.setId_categoria(aux.getId_categoria());
            enlace.setId_rol(cliente.getId_rol());
            aux.setEnlaces(this.mi.getListarEnlaces(enlace));
        }
        modelo.put("listaCategorias", listaCategorias);
        return new ModelAndView("menu/Menu", modelo);
    }
}
