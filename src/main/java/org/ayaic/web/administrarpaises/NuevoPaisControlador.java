package org.ayaic.web.administrarpaises;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Paises;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NuevoPaisControlador {

    private final MiFacade mi;

    public NuevoPaisControlador(MiFacade mi) {
        this.mi = mi;
    }
    
    @RequestMapping("/NuevoPais.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String id_pais = request.getParameter("id_pais");

        modelo.put("id_pais", request.getParameter("id_pais"));

        //Para la primera vez que entra a la pagina
        if ((request.getParameter("id_pais") != null) && ("Modificar".equals(accion)) && (request.getParameter("recargado") == null)) {
            //Sacamos los datos del pais
            Paises buscarPais = this.mi.getDatosPais(Integer.parseInt(id_pais)); // saca un registro a ser modificado
            modelo.put("buscarPais", buscarPais);
            modelo.put("id_estado", buscarPais.getId_estado());
            modelo.put("sw", request.getParameter("sw"));
        }
        modelo.put("accion", accion);
        return new ModelAndView("administrarpaises/NuevoPais", modelo);
    }
}
