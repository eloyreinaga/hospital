package org.ayaic.web.administrarcuentas;

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
public class ListarTransaccionesControlador {

    private final MiFacade mi;

    public ListarTransaccionesControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/ListarTransacciones.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");

        List listarTransacciones = this.mi.getListarTransacciones();
        modelo.put("listarTransacciones", listarTransacciones);

        return new ModelAndView("administrarcuentas/ListarTransacciones", modelo);

    }
}