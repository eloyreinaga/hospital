package org.ayaic.web.administrareciviles;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Eciviles;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GrabarEcivilControlador {

    private final MiFacade mi;

    public GrabarEcivilControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/GrabarEcivil.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion1 = request.getParameter("accion1");
        String accion = request.getParameter("accion");

        String ecivil = request.getParameter("ecivil");
        String id_ecivil = request.getParameter("id_ecivil");

        if ("Adicionar".equals(accion) && "Aceptar".equals(accion1)) {
            Eciviles repetida = this.mi.getDatosEcivil(Integer.parseInt(request.getParameter("id_ecivil")));  // SI EL OBJETO EXISTE ENTONCES NO ES NULO
            if (repetida == null) {
                if (("".equals(id_ecivil)) || ("".equals(ecivil))) {
                    return new ModelAndView("Aviso", "mensaje", "Faltan Introducir Datos");
                }
                Eciviles datoecivil = new Eciviles();
                datoecivil.setId_ecivil(Integer.parseInt(id_ecivil));
                datoecivil.setEcivil(ecivil);
                datoecivil.setUlt_usuario(cliente.getId_usuario());

                try {
                    this.mi.setCrearEcivil(datoecivil);
                } catch (Exception e) {
                    return new ModelAndView("Aviso", "mensaje", "La actualización no se cumplio, verifique los datos");
                }
                //listar Eciviles
                List listarEciviles = this.mi.getListarEciviles();
                modelo.put("listarEciviles", listarEciviles);
                return new ModelAndView("administrareciviles/ListarEciviles", modelo);

            } else {
                return new ModelAndView("Aviso", "mensaje", "Ya existe un id_ecivil igual");
            }
        }

        if ("Modificar".equals(accion) && "Aceptar".equals(accion1)) {
            if (("".equals(id_ecivil)) || ("".equals(ecivil))) {
                return new ModelAndView("Aviso", "mensaje", "Faltan Introducir Datos");
            }
            Eciviles categoria_m = new Eciviles();
            categoria_m.setId_ecivil(Integer.parseInt(request.getParameter("id_ecivil")));
            categoria_m.setEcivil(request.getParameter("ecivil"));
            categoria_m.setUlt_usuario(cliente.getId_usuario());

            if ("A".equals(request.getParameter("id_estado"))) {
                categoria_m.setId_estado("A");
            } else {
                categoria_m.setId_estado("B");
            }

            try {
                this.mi.setModificarEcivil(categoria_m);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "La actualización no se cumplio, verifique los datos");
            }

            //listar categorias
            List listarEciviles = this.mi.getListarEciviles();
            modelo.put("listarEciviles", listarEciviles);

            return new ModelAndView("administrareciviles/ListarEciviles", modelo);

        }

        if ("Eliminar".equals(accion) && "Aceptar".equals(accion1)) {
            if ("".equals(request.getParameter("id_ecivil"))) {
                return new ModelAndView("Aviso", "mensaje", "Faltan datos");
            }
            Eciviles elimina = new Eciviles();
            elimina.setId_ecivil(Integer.parseInt(request.getParameter("id_ecivil")));

            try {
                this.mi.setEliminarEcivil(elimina);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "El registro a eliminar tiene dependencias");
            }

            //listar categorias
            List listarEciviles = this.mi.getListarEciviles();
            modelo.put("listarEciviles", listarEciviles);

            return new ModelAndView("administrareciviles/ListarEciviles", modelo);
        }
        return new ModelAndView("administrarEciviles/ListarEciviles", modelo);
    }
}
