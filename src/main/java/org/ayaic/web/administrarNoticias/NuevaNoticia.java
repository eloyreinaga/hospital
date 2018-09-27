package org.ayaic.web.administrarNoticias;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Tableros;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NuevaNoticia {

    private final MiFacade mi;

    public NuevaNoticia(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/nuevaNoticia.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        int iId_usuario = cliente.getId_usuario();
        String sNombres = cliente.getNombres();

        Tableros tableros;
        String sId_tablero = request.getParameter("id_tablero");
        String sBoton = request.getParameter("boton");

        //Listar tipos avisos
        List lTiposAvisos = this.mi.getListarTiposAvisos();
        modelo.put("lTiposAvisos", lTiposAvisos);

        //Listar tipos tableros
        List lTiposTableros = this.mi.getListarTiposTableros();
        modelo.put("lTiposTableros", lTiposTableros);

        //Listar roles
        List lRoles = this.mi.getListarRoles();
        modelo.put("lRoles", lRoles);

        if ("Modificar".equals(sBoton)) {
            Tableros datosTablero = new Tableros();
            datosTablero.setId_tablero(Integer.parseInt(sId_tablero));
            datosTablero = this.mi.getBuscarTablero(datosTablero);
            datosTablero.setMensaje(datosTablero.getMensaje().replace("\r\n", ""));
            modelo.put("datosTablero", datosTablero);
            modelo.put("id_tablero", sId_tablero);
        }

        modelo.put("boton", sBoton);
        modelo.put("nombres", sNombres);

        return new ModelAndView("administrarNoticias/NuevaNoticia", modelo);
    }
}
