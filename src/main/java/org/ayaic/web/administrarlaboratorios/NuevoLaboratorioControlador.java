package org.ayaic.web.administrarlaboratorios;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Laboratorios;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NuevoLaboratorioControlador {

    private final MiFacade mi;

    public NuevoLaboratorioControlador(MiFacade mi) {
        this.mi = mi;
    }
    
    @RequestMapping("/NuevoLaboratorio.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");

        String accion = request.getParameter("accion");
        String id_laboratorio = request.getParameter("id_laboratorio");

        modelo.put("id_laboratorio", request.getParameter("id_laboratorio"));

        //Para la primera vez que entra a la pagina
        if ((request.getParameter("id_laboratorio") != null) && ("Modificar".equals(accion)) && (request.getParameter("recargado") == null)) {
            //Sacamos los datos del laboratorio
            Laboratorios pai = new Laboratorios();
            pai.setId_laboratorio(Integer.parseInt(id_laboratorio));
            Laboratorios buscarLaboratorio = this.mi.getDatosLaboratorio(pai); // saca un registro a ser modificado
            modelo.put("buscarLaboratorio", buscarLaboratorio);
            modelo.put("id_estado", buscarLaboratorio.getId_estado());
            modelo.put("sw", request.getParameter("sw"));
        }
        modelo.put("accion", accion);
        return new ModelAndView("administrarlaboratorios/NuevoLaboratorio", modelo);
    }
}
