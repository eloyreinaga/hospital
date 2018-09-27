package org.ayaic.web.administrardocumentos;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Documentos;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NuevoDocumentoControlador {

    private final MiFacade mi;

    public NuevoDocumentoControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/NuevoDocumento.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String id_documento = request.getParameter("id_documento");

        modelo.put("id_documento", request.getParameter("id_documento"));

        //Para la primera vez que entra a la pagina
        if ((request.getParameter("id_documento") != null) && ("Modificar".equals(accion)) && (request.getParameter("recargado") == null)) {
            //Sacamos los datos del documento
            Documentos buscardocumento = this.mi.getDatosDocumento(Integer.parseInt(id_documento)); // saca un registro a ser modificado
            modelo.put("buscardocumento", buscardocumento);
            modelo.put("id_estado", buscardocumento.getId_estado());
            modelo.put("sw", request.getParameter("sw"));
        }
        modelo.put("accion", accion);
        return new ModelAndView("administrardocumentos/NuevoDocumento", modelo);
    }
}
