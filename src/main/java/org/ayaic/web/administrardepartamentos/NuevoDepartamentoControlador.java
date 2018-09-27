package org.ayaic.web.administrardepartamentos;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Departamentos;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NuevoDepartamentoControlador {

    private final MiFacade mi;

    public NuevoDepartamentoControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/NuevoDepartamento.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");

        String accion = request.getParameter("accion");
        String id_departamento = request.getParameter("id_departamento");

        // listamos los paises
        List listarPaises = this.mi.getListarPaises();
        modelo.put("listarPaises", listarPaises);

        modelo.put("id_departamento", request.getParameter("id_departamento"));

        //Para la primera vez que entra a la pagina
        if ((request.getParameter("id_departamento") != null) && ("Modificar".equals(accion)) && (request.getParameter("recargado") == null)) {
            //Sacamos los datos del departamento
            Departamentos buscardepartamento = this.mi.getDatosDepartamento(Integer.parseInt(id_departamento)); // saca un registro a ser modificado
            modelo.put("datos", buscardepartamento);
            modelo.put("id_estado", buscardepartamento.getId_estado());
            modelo.put("sw", request.getParameter("sw"));
        }
        modelo.put("accion", accion);
        return new ModelAndView("administrardepartamentos/NuevoDepartamento", modelo);
    }
}
