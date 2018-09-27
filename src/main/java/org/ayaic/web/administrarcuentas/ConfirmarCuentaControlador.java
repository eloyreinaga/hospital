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
public class ConfirmarCuentaControlador {

    private final MiFacade mi;

    public ConfirmarCuentaControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/ConfirmarCuenta.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String accion1 = request.getParameter("accion1");

        String id_cuenta = request.getParameter("id_cuenta");
        String cuenta = request.getParameter("cuenta");
        String codigo = request.getParameter("codigo");
        String id_estado = request.getParameter("id_estado");
        String tipo_cuenta = request.getParameter("tipo_cuenta");
        Cuentas pai = new Cuentas();

        if ("Adicionar".equals(accion)) {

            pai.setTipo_cuenta(Integer.parseInt(tipo_cuenta));
            pai.setCuenta(cuenta);
            pai.setCodigo(codigo);

            modelo.put("dato", pai);
            modelo.put("accion", accion);
            modelo.put("accion1", accion1);

        }

        if ("Modificar".equals(accion)) {

            pai.setId_cuenta(Integer.parseInt(id_cuenta));
            pai.setTipo_cuenta(Integer.parseInt(tipo_cuenta));
            pai.setCuenta(cuenta);
            pai.setCodigo(codigo);
            pai.setId_estado(id_estado);

            modelo.put("dato", pai);
            modelo.put("sw", request.getParameter("sw"));
            modelo.put("accion", accion);
            modelo.put("accion1", accion1);
        }

        if ("Eliminar".equals(accion)) {
            Cuentas buscarCuenta = this.mi.getDatosCuenta(Integer.parseInt(id_cuenta)); // saca un registro a ser modificado
            modelo.put("dato", buscarCuenta);

            modelo.put("accion", accion);
            modelo.put("sw1", request.getParameter("sw1"));
            modelo.put("id_cuenta", id_cuenta);
        }
        if ("LibroMayor".equals(accion)) {
            Cuentas buscarCuenta = this.mi.getDatosCuenta(Integer.parseInt(id_cuenta)); // saca un registro a ser modificado
            modelo.put("dato", buscarCuenta);

            modelo.put("id_cuenta", id_cuenta);
            return new ModelAndView("administrarcuentas/LibroMayor", modelo);

        }
        return new ModelAndView("administrarcuentas/ConfirmarCuenta", modelo);

    }
}
