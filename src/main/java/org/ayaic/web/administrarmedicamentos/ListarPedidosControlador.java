package org.ayaic.web.administrarmedicamentos;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ListarPedidosControlador {

    private final MiFacade mi;

    public ListarPedidosControlador(MiFacade mi) {
        this.mi = mi;
    }
    
    @RequestMapping("/ListarPedidos.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String sAccion = request.getParameter("boton");
        String sFecha_ini = request.getParameter("valor_1");
        String sFecha_fin = request.getParameter("valor_2");

        if ("Buscar".equals(sAccion)) {
            if (("".equals(sFecha_ini)) || ("".equals(sFecha_fin))) {
                return new ModelAndView("administrarmedicamentos/ListarPedidos", modelo);
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

                Pacientes dato = new Pacientes();
                dato.setFecha_ini(dFechaini1);
                dato.setFecha_fin(dFechafin1);
                dato.setId_localidad(cliente.getCod_esta());
                dato.setCod_esta(cliente.getCod_esta());
                // lista de pacientes que no terminaron su receta
                List listarSinPago = this.mi.getListaMedEntregados(dato);
                modelo.put("listapago", listarSinPago);
                modelo.put("valor_1", sFecha_ini);
                modelo.put("valor_2", sFecha_fin);

                return new ModelAndView("administrarmedicamentos/VerPedidos", modelo);
            }
        }

        modelo.put("valor_1", sFecha_ini);
        modelo.put("valor_2", sFecha_fin);

        return new ModelAndView("administrarmedicamentos/ListarPedidos", modelo);

    }
}
