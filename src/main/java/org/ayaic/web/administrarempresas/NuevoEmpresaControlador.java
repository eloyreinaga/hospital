package org.ayaic.web.administrarempresas;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Empresas;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NuevoEmpresaControlador {

    private final MiFacade mi;

    public NuevoEmpresaControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/NuevoEmpresa.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String id_empresa = request.getParameter("id_empresa");

        modelo.put("id_empresa", request.getParameter("id_empresa"));

        //Para la primera vez que entra a la pagina
        if ((request.getParameter("id_empresa") != null) && ("Modificar".equals(accion)) && (request.getParameter("recargado") == null)) {
            //Sacamos los datos del empresa
            Empresas buscarempresa = this.mi.getDatosEmpresa(Integer.parseInt(id_empresa)); // saca un registro a ser modificado
            modelo.put("buscarempresa", buscarempresa);
            modelo.put("id_estado", "1");
            modelo.put("sw", request.getParameter("sw"));
        }
        modelo.put("accion", accion);
        return new ModelAndView("administrarempresas/NuevoEmpresa", modelo);
    }
}
