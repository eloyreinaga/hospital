package org.ayaic.web.administrarcuentas;

import java.util.HashMap;
import java.util.Map;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Cuentas;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NuevoTransaccionControlador {

    private final MiFacade mi;

    public NuevoTransaccionControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/NuevoTransaccion.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        Date fecha1 = new Date();
        String accion = request.getParameter("accion");
        String id_transaccion = request.getParameter("id_transaccion");

        modelo.put("id_transaccion", request.getParameter("id_transaccion"));

        //Para la primera vez que entra a la pagina
        if ((request.getParameter("id_transaccion") != null) && ("Modificar".equals(accion)) && (request.getParameter("recargado") == null)) {
            //Sacamos los datos del transaccion
            Cuentas buscarTransaccion = this.mi.getDatosTransaccion(Integer.parseInt(id_transaccion)); // saca un registro a ser modificado
            modelo.put("buscarTransaccion", buscarTransaccion);
            modelo.put("id_estado", buscarTransaccion.getId_estado());
            modelo.put("sw", request.getParameter("sw"));
        }
        modelo.put("accion", accion);
        modelo.put("anio_r", Integer.toString(fecha1.getYear() + 1900));
        modelo.put("mes_r", Integer.toString(fecha1.getMonth() + 1));
        modelo.put("dia_r", Integer.toString(fecha1.getDate()));

        return new ModelAndView("administrarcuentas/NuevoTransaccion", modelo);
    }
}
