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
public class ListarEmpresasPagoControlador {

    private final MiFacade mi;

    public ListarEmpresasPagoControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/ListarEmpresasPago.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String boton = request.getParameter("boton");
        String nombres = request.getParameter("nombre");
        String id_estado = request.getParameter("id_estado");

        List listarEmpresas = this.mi.getListarEmpresas("A");
        modelo.put("listarEmpresas", listarEmpresas);

        if ("BuscarE".equals(boton)) {
            nombres = nombres.replaceAll("\\s", "%");
            nombres = "%" + nombres + "%";
            Empresas empresa = new Empresas();
            empresa.setNombres(nombres);
            empresa.setId_estado("A");
            if ("C".equals(id_estado)) {
                empresa.setTipo("L");
                List listarEmp = this.mi.getListaEmpresaCod(empresa);
                modelo.put("listarEmpresas", listarEmp);
            } else {
                List listarEmp = this.mi.getListaEmpresa(empresa);
                modelo.put("listarEmpresas", listarEmp);
            }
        }
        if ("BuscarA".equals(boton)) {
            nombres = nombres.replaceAll("\\s", "%");
            nombres = "%" + nombres + "%";
            Empresas empresa = new Empresas();
            empresa.setNombres(nombres);
            empresa.setId_estado("A");
            if ("C".equals(id_estado)) {
                empresa.setTipo("L");
                List listarEmp = this.mi.getListaEmpEmpresaCod(empresa);
                modelo.put("listarEmpresas", listarEmp);
            } else {
                List listarEmp = this.mi.getListaEmpEmpresa(empresa);
                modelo.put("listarEmpresas", listarEmp);
            }
        }

        return new ModelAndView("administrarempresas/ListarEmpresasPago", modelo);

    }
}
