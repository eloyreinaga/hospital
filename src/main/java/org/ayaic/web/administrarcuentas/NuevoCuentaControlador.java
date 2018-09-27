package org.ayaic.web.administrarcuentas;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Cuentas;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NuevoCuentaControlador {

    private final MiFacade mi;

    public NuevoCuentaControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/NuevoCuenta.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String id_cuenta = request.getParameter("id_cuenta");

        modelo.put("id_cuenta", request.getParameter("id_cuenta"));

        //Para la primera vez que entra a la pagina
        if ((request.getParameter("id_cuenta") != null) && ("Modificar".equals(accion)) && (request.getParameter("recargado") == null)) {
            //Sacamos los datos del cuenta
            Cuentas buscarCuenta = this.mi.getDatosCuenta(Integer.parseInt(id_cuenta)); // saca un registro a ser modificado
            modelo.put("buscarCuenta", buscarCuenta);
            modelo.put("id_estado", buscarCuenta.getId_estado());
            modelo.put("sw", request.getParameter("sw"));
        }
        modelo.put("accion", accion);
        return new ModelAndView("administrarcuentas/NuevoCuenta", modelo);
    }
}
