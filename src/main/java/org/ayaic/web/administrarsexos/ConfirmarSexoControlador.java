package org.ayaic.web.administrarsexos;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Sexos;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ConfirmarSexoControlador {

    private final MiFacade mi;

    public ConfirmarSexoControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/ConfirmarSexo.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String accion1 = request.getParameter("accion1");

        String id_sexo = request.getParameter("id_sexo");
        String sexo = request.getParameter("sexo");
        String id_estado = request.getParameter("id_estado");
        Sexos pai = new Sexos();

        if ("Adicionar".equals(accion)) {

            pai.setId_sexo(Integer.parseInt(id_sexo));
            pai.setSexo(sexo);

            modelo.put("dato", pai);
            modelo.put("accion", accion);
            modelo.put("accion1", accion1);

        }

        if ("Modificar".equals(accion)) {

            pai.setId_sexo(Integer.parseInt(id_sexo));
            pai.setSexo(sexo);
            pai.setId_estado(id_estado);

            modelo.put("dato", pai);
            modelo.put("sw", request.getParameter("sw"));
            modelo.put("accion", accion);
            modelo.put("accion1", accion1);
        }

        if ("Eliminar".equals(accion)) {
            Sexos buscarsexo = this.mi.getDatosSexo(Integer.parseInt(id_sexo)); // saca un registro a ser modificado
            modelo.put("dato", buscarsexo);

            modelo.put("accion", accion);
            modelo.put("sw1", request.getParameter("sw1"));
            modelo.put("id_sexo", id_sexo);
        }

        return new ModelAndView("administrarsexos/ConfirmarSexo", modelo);

    }
}
