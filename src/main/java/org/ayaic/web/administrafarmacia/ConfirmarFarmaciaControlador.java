package org.ayaic.web.administrafarmacia;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Farmacias;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ConfirmarFarmaciaControlador {

    private final MiFacade mi;

    public ConfirmarFarmaciaControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/ConfirmarFarmacia.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        HashMap modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String _nombres = cliente.getNombres();

        //Guardar Nuevo    
        String accion = request.getParameter("accion");
        String accion1 = request.getParameter("accion1");

        String id_farmacia = request.getParameter("id_farmacia");
        String farmacia = request.getParameter("farmacia");
        String id_estado = request.getParameter("id_estado");
        Farmacias pai = new Farmacias();

        if ("Adicionar".equals(accion)) {

            pai.setFarmacia(farmacia);

            modelo.put("dato", pai);
            modelo.put("accion", accion);
            modelo.put("accion1", accion1);

        }

        if ("Modificar".equals(accion)) {

            pai.setId_farmacia(Integer.parseInt(id_farmacia));
            pai.setFarmacia(farmacia);
            pai.setId_estado(id_estado);

            modelo.put("dato", pai);
            modelo.put("sw", request.getParameter("sw"));
            modelo.put("accion", accion);
            modelo.put("accion1", accion1);
        }

        if ("Eliminar".equals(accion)) {
            Farmacias datofar = new Farmacias();
            datofar.setCod_esta(cliente.getCod_esta());
            datofar.setId_farmacia(cliente.getId_farmacia());
            Farmacias buscarfarmacia = this.mi.getDatosFarmacia(datofar); // saca un registro a ser modificado
            modelo.put("dato", buscarfarmacia);

            modelo.put("accion", accion);
            modelo.put("sw1", request.getParameter("sw1"));
            modelo.put("id_farmacia", id_farmacia);
        }

        return new ModelAndView("administrafarmacias/ConfirmarFarmacia", modelo);

    }
}
