package org.ayaic.web.administrarparentescos;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Parentescos;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GrabarParentescoControlador {

    private final MiFacade mi;

    public GrabarParentescoControlador(MiFacade mi) {
        this.mi = mi;
    }
   
    @RequestMapping("/GrabarParentesco.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion1 = request.getParameter("accion1");
        String accion = request.getParameter("accion");
        String parentesco = request.getParameter("parentesco");
        String id_parentesco = request.getParameter("id_parentesco");

        if ("Adicionar".equals(accion) && "Aceptar".equals(accion1)) {
            Parentescos repetida = this.mi.getDatosParentesco(Integer.parseInt(request.getParameter("id_parentesco")));  // SI EL OBJETO EXISTE ENTONCES NO ES NULO
            if (repetida == null) {
                if (("".equals(id_parentesco)) || ("".equals(parentesco))) {
                    return new ModelAndView("Aviso", "mensaje", "Faltan Introducir Datos");
                }
                Parentescos datoparentesco = new Parentescos();
                datoparentesco.setId_parentesco(Integer.parseInt(id_parentesco));
                datoparentesco.setParentesco(parentesco);
                datoparentesco.setUlt_usuario(cliente.getId_usuario());

                try {
                    this.mi.setCrearParentesco(datoparentesco);
                } catch (Exception e) {
                    return new ModelAndView("Aviso", "mensaje", "La actualización no se cumplio, verifique los datos");
                }
                //listar Parentescos
                List listarParentescos = this.mi.getListarParentescos();
                modelo.put("listarParentescos", listarParentescos);

                return new ModelAndView("administrarparentescos/ListarParentescos", modelo);

            } else {
                return new ModelAndView("Aviso", "mensaje", "Ya existe un id_parentesco igual");
            }
        }

        if ("Modificar".equals(accion) && "Aceptar".equals(accion1)) {
            if (("".equals(id_parentesco)) || ("".equals(parentesco))) {
                return new ModelAndView("Aviso", "mensaje", "Faltan Introducir Datos");
            }
            Parentescos categoria_m = new Parentescos();
            categoria_m.setId_parentesco(Integer.parseInt(request.getParameter("id_parentesco")));
            categoria_m.setParentesco(request.getParameter("parentesco"));
            categoria_m.setUlt_usuario(cliente.getId_usuario());

            if ("A".equals(request.getParameter("id_estado"))) {
                categoria_m.setId_estado("A");
            } else {
                categoria_m.setId_estado("B");
            }

            try {
                this.mi.setModificarParentesco(categoria_m);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "La actualización no se cumplio, verifique los datos");
            }

            //listar categorias
            List listarParentescos = this.mi.getListarParentescos();
            modelo.put("listarParentescos", listarParentescos);
            return new ModelAndView("administrarparentescos/ListarParentescos", modelo);

        }

        if ("Eliminar".equals(accion) && "Aceptar".equals(accion1)) {
            if ("".equals(request.getParameter("id_parentesco"))) {
                return new ModelAndView("Aviso", "mensaje", "Faltan datos");
            }
            Parentescos elimina = new Parentescos();
            elimina.setId_parentesco(Integer.parseInt(request.getParameter("id_parentesco")));

            try {
                this.mi.setEliminarParentesco(elimina);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "El registro a eliminar tiene dependencias");
            }

            //listar categorias
            List listarParentescos = this.mi.getListarParentescos();
            modelo.put("listarParentescos", listarParentescos);

            return new ModelAndView("administrarparentescos/ListarParentescos", modelo);
        }
        return new ModelAndView("administrarparentescos/ListarParentescos", modelo);
    }
}
