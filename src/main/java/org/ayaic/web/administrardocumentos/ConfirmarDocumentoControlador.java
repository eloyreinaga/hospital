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
public class ConfirmarDocumentoControlador {

    private final MiFacade mi;

    public ConfirmarDocumentoControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/ConfirmarDocumento.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String accion1 = request.getParameter("accion1");

        String id_documento = request.getParameter("id_documento");
        String documento = request.getParameter("documento");
        String id_estado = request.getParameter("id_estado");
        Documentos pai = new Documentos();

        if ("Adicionar".equals(accion)) {

            pai.setId_documento(Integer.parseInt(id_documento));
            pai.setDocumento(documento);

            modelo.put("dato", pai);
            modelo.put("accion", accion);
            modelo.put("accion1", accion1);

        }

        if ("Modificar".equals(accion)) {

            pai.setId_documento(Integer.parseInt(id_documento));
            pai.setDocumento(documento);
            pai.setId_estado(id_estado);

            modelo.put("dato", pai);
            modelo.put("sw", request.getParameter("sw"));
            modelo.put("accion", accion);
            modelo.put("accion1", accion1);
        }

        if ("Eliminar".equals(accion)) {
            Documentos buscardocumento = this.mi.getDatosDocumento(Integer.parseInt(id_documento)); // saca un registro a ser modificado
            modelo.put("dato", buscardocumento);

            modelo.put("accion", accion);
            modelo.put("sw1", request.getParameter("sw1"));
            modelo.put("id_documento", id_documento);
        }

        return new ModelAndView("administrardocumentos/ConfirmarDocumento", modelo);

    }
}
