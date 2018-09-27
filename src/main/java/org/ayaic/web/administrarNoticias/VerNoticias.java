package org.ayaic.web.administrarNoticias;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class VerNoticias {

    private final MiFacade mi;

    public VerNoticias(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/verNoticias.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");

        //Listamos las noticias
        List lNoticias = this.mi.getListarNoticias();
        modelo.put("lNoticias", lNoticias);
        //Fin - Listamos las noticias

        modelo.put("id_usuario", Integer.toString(cliente.getId_usuario()));

        return new ModelAndView("administrarNoticias/VerNoticias", modelo);
    }
}
