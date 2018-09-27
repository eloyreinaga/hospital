package org.ayaic.web.administrarcuentas;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
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
public class GrabarTransaccionControlador {

    private final MiFacade mi;

    public GrabarTransaccionControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/GrabarTransaccion.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion1 = request.getParameter("accion1");
        String accion = request.getParameter("accion");
        String dia_r = request.getParameter("dia_r");
        String mes_r = request.getParameter("mes_r");
        String anio_r = request.getParameter("anio_r");
        String transaccion = request.getParameter("transaccion");
        String id_transaccion = request.getParameter("id_transaccion");

        if ("Adicionar".equals(accion) && "Aceptar".equals(accion1)) {
            int max_transaccion;
            Cuentas repetida = this.mi.getDatosTransaccion(Integer.parseInt(request.getParameter("id_transaccion")));  // SI EL OBJETO EXISTE ENTONCES NO ES NULO
            if (("".equals(transaccion))) {
                return new ModelAndView("Aviso", "mensaje", "Faltan Introducir Datos");
            }
            int diax_r = Integer.parseInt(dia_r);
            int mesx_r = Integer.parseInt(mes_r) - 1;
            int aniox_r = Integer.parseInt(anio_r) - 1900;
            Date fec_registro = new Date(aniox_r, mesx_r, diax_r);

            Cuentas datotransaccion = new Cuentas();
            datotransaccion.setTransaccion(transaccion);
            datotransaccion.setFec_registro(fec_registro);
            try {
                this.mi.setCrearTransaccion(datotransaccion);

            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "La actualización no se cumplio, verifique los datos");
            }
            max_transaccion = this.mi.getNumLibroDiario();

            // agregar el libro diario
            Cuentas buscarTransaccion = this.mi.getDatosTransaccion(max_transaccion); // saca un registro a ser modificado
            modelo.put("datos", buscarTransaccion);

            List listarCuentasCot = this.mi.getListarCuentasCot();
            modelo.put("listarCuentasCot", listarCuentasCot);

            modelo.put("id_transaccion", Integer.toString(max_transaccion));

            return new ModelAndView("administrarcuentas/LibroDiario", modelo);

        }

        if ("Modificar".equals(accion) && "Aceptar".equals(accion1)) {
            if (("".equals(id_transaccion)) || ("".equals(transaccion))) {
                return new ModelAndView("Aviso", "mensaje", "Faltan Introducir Datos");
            }
            int diax_r = Integer.parseInt(dia_r);
            int mesx_r = Integer.parseInt(mes_r) - 1;
            int aniox_r = Integer.parseInt(anio_r) - 1900;
            Date fec_registro = new Date(aniox_r, mesx_r, diax_r);

            Cuentas categoria_m = new Cuentas();
            categoria_m.setId_transaccion(Integer.parseInt(request.getParameter("id_transaccion")));
            categoria_m.setTransaccion(request.getParameter("transaccion"));
            categoria_m.setFec_registro(fec_registro);

            if ("A".equals(request.getParameter("id_estado"))) {
                categoria_m.setId_estado("A");
            } else {
                categoria_m.setId_estado("B");
            }

            try {
                this.mi.setModificarTransaccion(categoria_m);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "La actualización no se cumplio, verifique los datos");
            }

            //listar categorias
            List listarTransacciones = this.mi.getListarTransacciones();
            modelo.put("listarTransacciones", listarTransacciones);

            return new ModelAndView("administrarcuentas/ListarTransacciones", modelo);

        }

        if ("Eliminar".equals(accion) && "Aceptar".equals(accion1)) {
            if ("".equals(request.getParameter("id_transaccion"))) {
                return new ModelAndView("Aviso", "mensaje", "Faltan datos");
            }
            Cuentas elimina = new Cuentas();
            elimina.setId_transaccion(Integer.parseInt(request.getParameter("id_transaccion")));

            try {
                this.mi.setEliminarTransaccion(elimina);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "El registro a eliminar tiene dependencias");
            }

            //listar categorias
            List listarTransacciones = this.mi.getListarTransacciones();
            modelo.put("listarTransacciones", listarTransacciones);

            return new ModelAndView("administrarcuentas/ListarTransacciones", modelo);
        }
        return new ModelAndView("administrarcuentas/ListarTransacciones", modelo);
    }
}
