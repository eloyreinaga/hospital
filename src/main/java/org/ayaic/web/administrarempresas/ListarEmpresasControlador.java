package org.ayaic.web.administrarempresas;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Empresas;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ListarEmpresasControlador {

    private final MiFacade mi;

    public ListarEmpresasControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/ListarEmpresas.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String _nombres = cliente.getNombres();
        String boton = request.getParameter("boton");
        String nombres = request.getParameter("nombre");
        String id_estado = request.getParameter("id_estado");

        List listarEmpresas = this.mi.getListarEmpresas("%");
        modelo.put("listarEmpresas", listarEmpresas);

        if ("BuscarE".equals(boton)) {
            if (nombres != null || "".equals(nombres)) {
                Empresas empresa = new Empresas();
                nombres = nombres.replaceAll("\\s", "%");
                nombres = "%" + nombres + "%";
                empresa.setNombres(nombres);
                List listarEmp = this.mi.getListaEmpresa(empresa);
                modelo.put("listarEmpresas", listarEmp);
            }
        }

        return new ModelAndView("administrarempresas/ListarEmpresas", modelo);

    }
}
