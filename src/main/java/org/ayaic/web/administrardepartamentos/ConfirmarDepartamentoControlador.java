package org.ayaic.web.administrardepartamentos;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Departamentos;
import org.ayaic.domain.Paises;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ConfirmarDepartamentoControlador {

    private final MiFacade mi;

    public ConfirmarDepartamentoControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/ConfirmarDepartamento.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String accion1 = request.getParameter("accion1");

        String id_departamento = request.getParameter("id_departamento");
        String departamento = request.getParameter("departamento");
        String id_pais = request.getParameter("id_pais");
        String id_estado = request.getParameter("id_estado");
        Departamentos pai = new Departamentos();

        if ("Adicionar".equals(accion)) {

            pai.setId_departamento(Integer.parseInt(id_departamento));
            pai.setDepartamento(departamento);
            pai.setId_pais(Integer.parseInt(id_pais));
            modelo.put("dato", pai);

            try {
                Paises buscarPais = this.mi.getDatosPais(Integer.parseInt(id_pais)); // saca un registro a ser modificado
                modelo.put("buscarPais", buscarPais);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "Faltan datos");
            }

            modelo.put("accion", accion);
            modelo.put("accion1", accion1);

        }

        if ("Modificar".equals(accion)) {

            pai.setId_departamento(Integer.parseInt(id_departamento));
            pai.setDepartamento(departamento);
            pai.setId_pais(Integer.parseInt(id_pais));
            pai.setId_estado(id_estado);
            modelo.put("dato", pai);
            try {
                Paises buscarPais = this.mi.getDatosPais(Integer.parseInt(id_pais)); // saca un registro a ser modificado
                modelo.put("buscarPais", buscarPais);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "Faltan datos");
            }

            modelo.put("sw", request.getParameter("sw"));
            modelo.put("accion", accion);
            modelo.put("accion1", accion1);
        }

        if ("Eliminar".equals(accion)) {
            Departamentos buscardepartamento = this.mi.getDatosDepartamento(Integer.parseInt(id_departamento)); // saca un registro a ser modificado
            modelo.put("dato", buscardepartamento);
            try {

                Paises buscarPais = this.mi.getDatosPais(buscardepartamento.getId_pais()); // saca un registro a ser modificado
                modelo.put("buscarPais", buscarPais);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "Faltan datos");
            }

            modelo.put("accion", accion);
            modelo.put("sw1", request.getParameter("sw1"));
            modelo.put("id_departamento", id_departamento);
        }

        return new ModelAndView("administrardepartamentos/ConfirmarDepartamento", modelo);

    }
}
