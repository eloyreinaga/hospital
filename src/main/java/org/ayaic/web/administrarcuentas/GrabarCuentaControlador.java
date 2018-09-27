package org.ayaic.web.administrarcuentas;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Cuentas;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GrabarCuentaControlador {

    private final MiFacade mi;

    public GrabarCuentaControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/GrabarCuenta.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion1 = request.getParameter("accion1");
        String accion = request.getParameter("accion");
        String tipo_cuenta = request.getParameter("tipo_cuenta");
        String cuenta = request.getParameter("cuenta");
        String codigo = request.getParameter("codigo");
        String id_cuenta = request.getParameter("id_cuenta");

        if ("Adicionar".equals(accion) && "Aceptar".equals(accion1)) {

            Cuentas repetida = this.mi.getDatosCuenta(Integer.parseInt(request.getParameter("id_cuenta")));  // SI EL OBJETO EXISTE ENTONCES NO ES NULO
            if (repetida == null) {
                if (("".equals(id_cuenta)) || ("".equals(cuenta)) || ("".equals(codigo))) {
                    return new ModelAndView("Aviso", "mensaje", "Faltan Introducir Datos");
                }
                Cuentas datocuenta = new Cuentas();
                datocuenta.setCuenta(cuenta);
                datocuenta.setCodigo(codigo);
                datocuenta.setTipo_cuenta(Integer.parseInt(tipo_cuenta));

                try {
                    this.mi.setCrearCuenta(datocuenta);
                } catch (Exception e) {
                    return new ModelAndView("Aviso", "mensaje", "La actualización no se cumplio, verifique los datos");
                }
                //listar cuentas
                List listarCuentas = this.mi.getListarCuentas();
                modelo.put("listarCuentas", listarCuentas);
                return new ModelAndView("administrarcuentas/ListarCuentas", modelo);

            } else {
                return new ModelAndView("Aviso", "mensaje", "Ya existe un id_cuenta igual");
            }
        }

        if ("Modificar".equals(accion) && "Aceptar".equals(accion1)) {
            if (("".equals(id_cuenta)) || ("".equals(cuenta)) || ("".equals(codigo))) {
                return new ModelAndView("Aviso", "mensaje", "Faltan Introducir Datos");
            }
            Cuentas categoria_m = new Cuentas();
            categoria_m.setId_cuenta(Integer.parseInt(request.getParameter("id_cuenta")));
            categoria_m.setTipo_cuenta(Integer.parseInt(request.getParameter("tipo_cuenta")));
            categoria_m.setCuenta(request.getParameter("cuenta"));
            categoria_m.setCodigo(request.getParameter("codigo"));

            if ("A".equals(request.getParameter("id_estado"))) {
                categoria_m.setId_estado("A");
            } else {
                categoria_m.setId_estado("B");
            }

            try {
                this.mi.setModificarCuenta(categoria_m);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "La actualización no se cumplio, verifique los datos");
            }

            //listar categorias
            List listarCuentas = this.mi.getListarCuentas();
            modelo.put("listarCuentas", listarCuentas);

            return new ModelAndView("administrarcuentas/ListarCuentas", modelo);

        }

        if ("Eliminar".equals(accion) && "Aceptar".equals(accion1)) {
            if ("".equals(request.getParameter("id_cuenta"))) {
                return new ModelAndView("Aviso", "mensaje", "Faltan datos");
            }
            Cuentas elimina = new Cuentas();
            elimina.setId_cuenta(Integer.parseInt(request.getParameter("id_cuenta")));

            try {
                this.mi.setEliminarCuenta(elimina);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "El registro a eliminar tiene dependencias");
            }

            //listar categorias
            List listarCuentas = this.mi.getListarCuentas();
            modelo.put("listarCuentas", listarCuentas);

            return new ModelAndView("administrarcuentas/ListarCuentas", modelo);
        }
        return new ModelAndView("administrarcuentas/ListarCuentas", modelo);
    }
}
