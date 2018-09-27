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
public class ConfirmarPaisControlador {

    private final MiFacade mi;

    public ConfirmarPaisControlador (MiFacade mi) {
        this.mi = mi;
    }
    
    @RequestMapping("/ConfirmarPais.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String accion1 = request.getParameter("accion1");

        String id_pais = request.getParameter("id_pais");
        String pais = request.getParameter("pais");
        String nacionalidad = request.getParameter("nacionalidad");
        String id_estado = request.getParameter("id_estado");
        Paises pai = new Paises();

        if ("Adicionar".equals(accion)) {

            pai.setId_pais(Integer.parseInt(id_pais));
            pai.setPais(pais);
            pai.setNacionalidad(nacionalidad);

            modelo.put("dato", pai);
            modelo.put("accion", accion);
            modelo.put("accion1", accion1);

        }

        if ("Modificar".equals(accion)) {

            pai.setId_pais(Integer.parseInt(id_pais));
            pai.setPais(pais);
            pai.setNacionalidad(nacionalidad);
            pai.setId_estado(id_estado);

            modelo.put("dato", pai);
            modelo.put("sw", request.getParameter("sw"));
            modelo.put("accion", accion);
            modelo.put("accion1", accion1);
        }

        if ("Eliminar".equals(accion)) {
            Paises buscarPais = this.mi.getDatosPais(Integer.parseInt(id_pais)); // saca un registro a ser modificado
            modelo.put("dato", buscarPais);

            modelo.put("accion", accion);
            modelo.put("sw1", request.getParameter("sw1"));
            modelo.put("id_pais", id_pais);
        }

        return new ModelAndView("administrarpaises/ConfirmarPais", modelo);

    }
}
