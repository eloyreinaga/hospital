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
public class ConfirmarTransaccionControlador {

    private final MiFacade mi;

    public ConfirmarTransaccionControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/ConfirmarTransaccion.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String accion1 = request.getParameter("accion1");
        String dia_r = request.getParameter("dia_r");
        String mes_r = request.getParameter("mes_r");
        String anio_r = request.getParameter("anio_r");
        String id_transaccion = request.getParameter("id_transaccion");
        String transaccion = request.getParameter("transaccion");
        String id_estado = request.getParameter("id_estado");
        Cuentas pai = new Cuentas();

        if ("Adicionar".equals(accion)) {

            pai.setTransaccion(transaccion);

            String a = "/";
            String fecha_registro = dia_r + a + mes_r + a + anio_r;
            modelo.put("fec_registro", fecha_registro);
            modelo.put("anio_r", request.getParameter("anio_r"));
            modelo.put("mes_r", request.getParameter("mes_r"));
            modelo.put("dia_r", request.getParameter("dia_r"));

            modelo.put("dato", pai);
            modelo.put("accion", accion);
            modelo.put("accion1", accion1);
        }

        if ("Modificar".equals(accion)) {

            pai.setTransaccion(transaccion);
            pai.setId_estado(id_estado);

            String a = "/";
            String fecha_registro = dia_r + a + mes_r + a + anio_r;
            modelo.put("fec_registro", fecha_registro);
            modelo.put("anio_r", request.getParameter("anio_r"));
            modelo.put("mes_r", request.getParameter("mes_r"));
            modelo.put("dia_r", request.getParameter("dia_r"));

            modelo.put("dato", pai);
            modelo.put("sw", request.getParameter("sw"));
            modelo.put("accion", accion);
            modelo.put("accion1", accion1);
        }

        if ("Eliminar".equals(accion)) {
            Cuentas buscarTransaccion = this.mi.getDatosTransaccion(Integer.parseInt(id_transaccion)); // saca un registro a ser modificado
            modelo.put("dato", buscarTransaccion);

            Date fecha_reg = buscarTransaccion.getFec_registro();
            int xanio_r = fecha_reg.getYear() + 1900;
            int xmes_r = fecha_reg.getMonth() + 1;
            int xdia_r = fecha_reg.getDate();

            String a = "/";
            String fecha_registro = Integer.toString(xdia_r) + a + Integer.toString(xmes_r) + a + Integer.toString(xanio_r);
            modelo.put("fec_registro", fecha_registro);
            modelo.put("anio_r", Integer.toString(xanio_r));
            modelo.put("mes_r", Integer.toString(xmes_r));
            modelo.put("dia_r", Integer.toString(xdia_r));

            modelo.put("accion", accion);
            modelo.put("sw1", request.getParameter("sw1"));
            modelo.put("id_transaccion", id_transaccion);
        }

        return new ModelAndView("administrarcuentas/ConfirmarTransaccion", modelo);

    }
}
