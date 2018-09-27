package org.ayaic.web.administrardepartamentos;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Departamentos;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GrabarDepartamentoControlador {

    private final MiFacade mi;

    public GrabarDepartamentoControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/GrabarDepartamento.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion1 = request.getParameter("accion1");
        String accion = request.getParameter("accion");

        String departamento = request.getParameter("departamento");
        String id_pais = request.getParameter("id_pais");
        String id_departamento = request.getParameter("id_departamento");

        if ("Adicionar".equals(accion) && "Aceptar".equals(accion1)) {
            Departamentos repetida = this.mi.getDatosDepartamento(Integer.parseInt(request.getParameter("id_departamento")));  // SI EL OBJETO EXISTE ENTONCES NO ES NULO
            if (repetida == null) {
                if (("".equals(id_departamento)) || ("".equals(departamento)) || ("".equals(id_pais))) {
                    return new ModelAndView("Aviso", "mensaje", "Faltan Introducir Datos");
                }
                Departamentos datodepartamento = new Departamentos();
                datodepartamento.setId_departamento(Integer.parseInt(id_departamento));
                datodepartamento.setDepartamento(departamento);
                datodepartamento.setId_pais(Integer.parseInt(id_pais));
                datodepartamento.setUlt_usuario(cliente.getId_usuario());

                try {
                    this.mi.setCrearDepartamento(datodepartamento);
                } catch (Exception e) {
                    return new ModelAndView("Aviso", "mensaje", "La actualización no se cumplio, verifique los datos");
                }
                //listar Departamentos
                List listarDepartamentos = this.mi.getListarDepartamentos();
                modelo.put("listarDepartamentos", listarDepartamentos);

                return new ModelAndView("administrardepartamentos/ListarDepartamentos", modelo);

            } else {
                return new ModelAndView("Aviso", "mensaje", "Ya existe un id_departamento igual");
            }
        }

        if ("Modificar".equals(accion) && "Aceptar".equals(accion1)) {
            if (("".equals(id_departamento)) || ("".equals(departamento)) || ("".equals(id_pais))) {
                return new ModelAndView("Aviso", "mensaje", "Faltan Introducir Datos");
            }
            Departamentos categoria_m = new Departamentos();
            categoria_m.setId_departamento(Integer.parseInt(request.getParameter("id_departamento")));
            categoria_m.setDepartamento(request.getParameter("departamento"));
            categoria_m.setId_pais(Integer.parseInt(request.getParameter("id_pais")));
            categoria_m.setUlt_usuario(cliente.getId_usuario());

            if ("A".equals(request.getParameter("id_estado"))) {
                categoria_m.setId_estado("A");
            } else {
                categoria_m.setId_estado("B");
            }

            try {
                this.mi.setModificarDepartamento(categoria_m);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "La actualización no se cumplio, verifique los datos");
            }

            //listar categorias
            List listarDepartamentos = this.mi.getListarDepartamentos();
            modelo.put("listarDepartamentos", listarDepartamentos);

            return new ModelAndView("administrardepartamentos/ListarDepartamentos", modelo);

        }

        if ("Eliminar".equals(accion) && "Aceptar".equals(accion1)) {
            if ("".equals(request.getParameter("id_departamento"))) {
                return new ModelAndView("Aviso", "mensaje", "Faltan datos");
            }
            Departamentos elimina = new Departamentos();
            elimina.setId_departamento(Integer.parseInt(request.getParameter("id_departamento")));

            try {
                this.mi.setEliminarDepartamento(elimina);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "El registro a eliminar tiene dependencias");
            }

            //listar categorias
            List listarDepartamentos = this.mi.getListarDepartamentos();
            modelo.put("listarDepartamentos", listarDepartamentos);

            return new ModelAndView("administrardepartamentos/ListarDepartamentos", modelo);
        }
        return new ModelAndView("administrardepartamentos/ListarDepartamentos", modelo);
    }
}
