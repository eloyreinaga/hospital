package org.ayaic.web.administrarcuentas;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Cuentas;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LibroDiarioControlador {

    private final MiFacade mi;

    public LibroDiarioControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/LibroDiario.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        String accion = request.getParameter("accion");

        String nombres = request.getParameter("nombres");
        String id_cuenta = request.getParameter("id_cuenta");
        String cantidad = request.getParameter("cantidad");
        String tipo = request.getParameter("tipo");
        String id_transaccion = request.getParameter("id_transaccion");
        String id_persona = request.getParameter("id_persona");

        Cuentas buscarTransaccion = this.mi.getDatosTransaccion(Integer.parseInt(id_transaccion)); // saca un registro a ser modificado
        modelo.put("datos", buscarTransaccion);

        if ("adicion".equals(accion)) {
            Cuentas dato = new Cuentas();
            dato.setId_transaccion(Integer.parseInt(id_transaccion));
            dato.setId_cuenta(Integer.parseInt(id_cuenta));
            if ("1".equals(tipo)) {
                dato.setDebe(Double.parseDouble(cantidad));
                dato.setHaber(0);
            } else {
                dato.setDebe(0);
                dato.setHaber(Double.parseDouble(cantidad));
            }

            try {
                this.mi.setCrearLibroDiario(dato);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "La actualizacion no se cumplio");
            }

        }
        if ("eliminar".equals(accion)) {
            Cuentas dato = new Cuentas();
            dato.setId_transaccion(Integer.parseInt(id_transaccion));
            dato.setId_cuenta(Integer.parseInt(id_cuenta));

            this.mi.setEliminarLibroDiario(dato);
        }

        List listarCuentasCot = this.mi.getListarCuentasCot();
        modelo.put("listarCuentasCot", listarCuentasCot);

        List listarLibro = this.mi.getListarLibroDiario(Integer.parseInt(id_transaccion));
        modelo.put("listarLibro", listarLibro);

        List listarCuentas = this.mi.getListarCuentasNom(nombres);
        modelo.put("listarCuentas", listarCuentas);

        modelo.put("nombres", nombres);
        modelo.put("id_transaccion", id_transaccion);
        modelo.put("id_persona", id_persona);

        return new ModelAndView("administrarcuentas/LibroDiario", modelo);
    }
}
