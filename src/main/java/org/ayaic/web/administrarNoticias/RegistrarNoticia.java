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
public class RegistrarNoticia {

    private final MiFacade mi;

    public RegistrarNoticia(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/registrarNoticia.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");

        Tableros tableros;
        String sId_tablero = request.getParameter("id_tablero");
        String sId_tipo_aviso = request.getParameter("id_tipo_aviso");
        String sId_tipo_tablero = request.getParameter("id_tipo_tablero");
        String sId_rol = request.getParameter("id_rol");
        String sNoticia = request.getParameter("noticia");
        String sMensaje = request.getParameter("mensaje");

        if ((!"".equals(sId_tipo_tablero)) && (!"".equals(sId_tipo_aviso)) && (!"".equals(sId_rol)) && (!"".equals(sNoticia)) && (!"".equals(sMensaje))) {
            Tableros datosTablero = new Tableros();
            if ((sId_tablero != null) && (!"".equals(sId_tablero))) {
                datosTablero.setId_tablero(Integer.parseInt(sId_tablero));
            }
            datosTablero.setId_tipo_aviso(Integer.parseInt(sId_tipo_aviso));
            datosTablero.setId_tipo_tablero(Integer.parseInt(sId_tipo_tablero));
            datosTablero.setId_rol(Integer.parseInt(sId_rol));
            datosTablero.setNoticia(sNoticia);
            datosTablero.setMensaje(sMensaje);
            datosTablero.setUlt_usuario(cliente.getId_usuario());
            int iResultado = this.mi.setRegistrarTablero(datosTablero);
            if (iResultado == 1) {
                List lNoticias = this.mi.getListarNoticias();
                modelo.put("lNoticias", lNoticias);
                modelo.put("id_usuario", Integer.toString(cliente.getId_usuario()));
                return new ModelAndView("administrarNoticias/VerNoticias", modelo);
            }
        }
        return new ModelAndView("Aviso", "mensaje", "Faltan datos");
    }
}
