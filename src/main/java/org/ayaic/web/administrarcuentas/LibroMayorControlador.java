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
public class LibroMayorControlador {

    private final MiFacade mi;

    public LibroMayorControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/LibroMayor.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String sId_estado = request.getParameter("id_estado");
        String sAccion = request.getParameter("boton");
        String id_cuenta = request.getParameter("id_cuenta");

        Cuentas buscarCuenta = this.mi.getDatosCuenta(Integer.parseInt(id_cuenta)); // saca un registro a ser modificado
        modelo.put("dato", buscarCuenta);

        modelo.put("id_cuenta", id_cuenta);

        if ("Buscar".equals(sAccion)) {
            String sFecha_ini = request.getParameter("valor_1");
            String sFecha_fin = request.getParameter("valor_2");
            if (("".equals(sFecha_ini)) || ("".equals(sFecha_fin))) {
                return new ModelAndView("administrarcuentas/LibroMayor", modelo);
            } else {
                String[] sFechaini = sFecha_ini.split("-");
                int iAnio1 = Integer.parseInt(sFechaini[0]) - 1900;
                int iMes1 = Integer.parseInt(sFechaini[1]) - 1;
                int iDia1 = Integer.parseInt(sFechaini[2]);

                String[] sFechafin = sFecha_fin.split("-");
                int iAnio2 = Integer.parseInt(sFechafin[0]) - 1900;
                int iMes2 = Integer.parseInt(sFechafin[1]) - 1;
                int iDia2 = Integer.parseInt(sFechafin[2]);

                Date dFechaini1 = new Date(iAnio1, iMes1, iDia1);
                Date dFechafin1 = new Date(iAnio2, iMes2, iDia2);

                modelo.put("datos", cliente);

                Cuentas dato = new Cuentas();

                dato.setFecha_ini(dFechaini1);
                dato.setFecha_fin(dFechafin1);
                dato.setId_cuenta(Integer.parseInt(id_cuenta));

                List listaCobros = this.mi.getListarLibroMayor(dato);
                modelo.put("listaLibroMayor", listaCobros);

                return new ModelAndView(new LibroMayorPDF(), modelo);
            }
        }
        return new ModelAndView("administrarcuentas/LibroMayor", modelo);
    }
}
