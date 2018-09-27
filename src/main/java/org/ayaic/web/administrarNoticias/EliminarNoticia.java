package org.ayaic.web.administrarNoticias;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Tableros;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EliminarNoticia {

    private final MiFacade mi;

    public EliminarNoticia(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/eliminarNoticia.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");

        String sId_tablero = request.getParameter("id_tablero");
        String sBoton = request.getParameter("boton");

        //Sacamos los datos del tablero
        Tableros datosTablero = new Tableros();
        datosTablero.setId_tablero(Integer.parseInt(sId_tablero));
        datosTablero = this.mi.getBuscarTablero(datosTablero);
        modelo.put("datosTablero", datosTablero);

        if ("Aceptar".equals(sBoton)) {
            int iResultado = this.mi.setEliminarTablero(datosTablero);
            if (iResultado == 0) {
                return new ModelAndView("Aviso", "mensaje", "La noticia no se eliminó");
            } else {
                return new ModelAndView("Aviso", "mensaje", "La noticia se eliminó correctamente");
            }
        }

        return new ModelAndView("administrarNoticias/EliminarNoticia", modelo);
    }
}
