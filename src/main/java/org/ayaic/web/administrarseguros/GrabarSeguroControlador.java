package org.ayaic.web.administrarseguros;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Seguros;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GrabarSeguroControlador {

    private final MiFacade mi;

    public GrabarSeguroControlador (MiFacade mi) {
        this.mi = mi;
    }
    
    @RequestMapping("/GrabarSeguro.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion1 = request.getParameter("accion1");
        String accion = request.getParameter("accion");
        String seguro = request.getParameter("seguro");
        String id_seguro = request.getParameter("id_seguro");

        if ("Adicionar".equals(accion) && "Aceptar".equals(accion1)) {
            Seguros repetida = this.mi.getDatosSeguro(Integer.parseInt(request.getParameter("id_seguro")));  // SI EL OBJETO EXISTE ENTONCES NO ES NULO
            if (repetida == null) {
                if (("".equals(id_seguro)) || ("".equals(seguro))) {
                    return new ModelAndView("Aviso", "mensaje", "Faltan Introducir Datos");
                }
                Seguros datoseguro = new Seguros();
//	   datoseguro.setId_seguro(Integer.parseInt(id_seguro)) ;
                datoseguro.setSeguro(seguro);
                datoseguro.setUlt_usuario(cliente.getId_usuario());

                try {
                    this.mi.setCrearSeguro(datoseguro);
                } catch (Exception e) {
                    return new ModelAndView("Aviso", "mensaje", "La actualización no se cumplio, verifique los datos");
                }
                //listar Seguros
                List listarSeguros = this.mi.getListarSeguros("A");
                modelo.put("listarSeguros", listarSeguros);
                return new ModelAndView("administrarseguros/ListarSeguros", modelo);

            } else {
                return new ModelAndView("Aviso", "mensaje", "Ya existe un id_seguro igual");
            }
        }

        if ("Modificar".equals(accion) && "Aceptar".equals(accion1)) {
            if (("".equals(id_seguro)) || ("".equals(seguro))) {
                return new ModelAndView("Aviso", "mensaje", "Faltan Introducir Datos");
            }
            Seguros categoria_m = new Seguros();
            categoria_m.setId_seguro(Integer.parseInt(request.getParameter("id_seguro")));
            categoria_m.setSeguro(request.getParameter("seguro"));
            categoria_m.setUlt_usuario(cliente.getId_usuario());

            if ("A".equals(request.getParameter("id_estado"))) {
                categoria_m.setId_estado("A");
            } else {
                categoria_m.setId_estado("B");
            }

            try {
                this.mi.setModificarSeguro(categoria_m);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "La actualización no se cumplio, verifique los datos");
            }

            //listar categorias
            List listarSeguros = this.mi.getListarSeguros("%");
            modelo.put("listarSeguros", listarSeguros);
            return new ModelAndView("administrarseguros/ListarSeguros", modelo);

        }

        if ("Eliminar".equals(accion) && "Aceptar".equals(accion1)) {
            if ("".equals(request.getParameter("id_seguro"))) {
                return new ModelAndView("Aviso", "mensaje", "Faltan datos");
            }
            Seguros elimina = new Seguros();
            elimina.setId_seguro(Integer.parseInt(request.getParameter("id_seguro")));

            try {
                this.mi.setEliminarSeguro(elimina);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "El registro a eliminar tiene dependencias");
            }

            //listar Seguros
            List listarSeguros = this.mi.getListarSeguros("%");
            modelo.put("listarSeguros", listarSeguros);

            return new ModelAndView("administrarseguros/ListarSeguros", modelo);
        }
        return new ModelAndView("administrarseguros/ListarSeguros", modelo);
    }
}
