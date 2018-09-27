package org.ayaic.web.reporteprestacion;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Prestaciones;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ListarRepesControlador {

    private final MiFacade mi;

    public ListarRepesControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/reporteFopos.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String sAccion = request.getParameter("boton");

        if ("Modificar".equals(accion)) {
            String mes = request.getParameter("mes");
            String gestion = request.getParameter("gestion");
            modelo.put("dato", cliente);

            Prestaciones datos = new Prestaciones();
            datos.setMes(Integer.parseInt(mes));
            datos.setGestion(Integer.parseInt(gestion));

            List listarDatosImm = this.mi.getListarActRepes(datos);
            //List listarMorbilidad = this.mi.getListarMorbilidad(datos);
            modelo.put("listaFopos", listarDatosImm);

            modelo.put("gestion", gestion);
            modelo.put("mes", mes);

            return new ModelAndView(new ListarConsultaPDF(), modelo);
        }
        if ("Mostrar".equals(accion)) {
            String mes = request.getParameter("mes");
            String gestion = request.getParameter("gestion");
            modelo.put("dato", cliente);

            List listaConsultorios = this.mi.getListarFopos(Integer.parseInt(mes), Integer.parseInt(gestion));
            modelo.put("listaFopos", listaConsultorios);

            modelo.put("gestion", gestion);
            modelo.put("mes", mes);

            return new ModelAndView(new ListarConsultaPDF(), modelo);
        }
        if ("Adicionar".equals(accion)) {

            List listarImm = this.mi.getListarFaltanRepes();
            modelo.put("listarImm", listarImm);
            modelo.put("accion", accion);
            return new ModelAndView("reporteprestacion/ListarRepes", modelo);
        }

        List listarImm = this.mi.getListarRepes();
        modelo.put("listarImm", listarImm);
        modelo.put("accion", "Mostrar");

        return new ModelAndView("reporteprestacion/ListarRepes", modelo);

    }
}
