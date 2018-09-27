package org.ayaic.web.administrarrubros;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Rubros;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GrabarRubroControlador {

    private final MiFacade mi;

    public GrabarRubroControlador(MiFacade mi) {
        this.mi = mi;
    }
    
    @RequestMapping("/GrabarRubro.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion1 = request.getParameter("accion1");
        String accion = request.getParameter("accion");

        String rubro = request.getParameter("rubro");
        String id_rubro = request.getParameter("id_rubro");

        if ("Adicionar".equals(accion) && "Aceptar".equals(accion1)) {
            Rubros repetida = this.mi.getDatosRubro(Integer.parseInt(request.getParameter("id_rubro")));  // SI EL OBJETO EXISTE ENTONCES NO ES NULO
            if (repetida == null) {
                if (("".equals(id_rubro)) || ("".equals(rubro))) {
                    return new ModelAndView("Aviso", "mensaje", "Faltan Introducir Datos");
                }
                Rubros datorubro = new Rubros();
//	   datorubro.setId_rubro(Integer.parseInt(id_rubro)) ;
                datorubro.setRubro(rubro);
                datorubro.setUlt_usuario(cliente.getId_usuario());

                try {
                    this.mi.setCrearRubro(datorubro);
                } catch (Exception e) {
                    return new ModelAndView("Aviso", "mensaje", "La actualización no se cumplio, verifique los datos");
                }
                //listar Rubros
                Rubros aux = new Rubros();
                List listarRubros = this.mi.getListarRubros(aux);
                modelo.put("listarRubros", listarRubros);
                return new ModelAndView("administrarrubros/ListarRubros", modelo);

            } else {
                return new ModelAndView("Aviso", "mensaje", "Ya existe un id_rubro igual");
            }
        }

        if ("Modificar".equals(accion) && "Aceptar".equals(accion1)) {
            if (("".equals(id_rubro)) || ("".equals(rubro))) {
                return new ModelAndView("Aviso", "mensaje", "Faltan Introducir Datos");
            }
            Rubros categoria_m = new Rubros();
            categoria_m.setId_rubro(Integer.parseInt(request.getParameter("id_rubro")));
            categoria_m.setRubro(request.getParameter("rubro"));
            categoria_m.setUlt_usuario(cliente.getId_usuario());

            if ("A".equals(request.getParameter("id_estado"))) {
                categoria_m.setId_estado("A");
            } else {
                categoria_m.setId_estado("B");
            }

            try {
                this.mi.setModificarRubro(categoria_m);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "La actualización no se cumplio, verifique los datos");
            }

            //listar categorias
            Rubros aux = new Rubros();
            List listarRubros = this.mi.getListarRubros(aux);
            modelo.put("listarRubros", listarRubros);
            return new ModelAndView("administrarrubros/ListarRubros", modelo);

        }

        if ("Eliminar".equals(accion) && "Aceptar".equals(accion1)) {
            if ("".equals(request.getParameter("id_rubro"))) {
                return new ModelAndView("Aviso", "mensaje", "Faltan datos");
            }
            Rubros elimina = new Rubros();
            elimina.setId_rubro(Integer.parseInt(request.getParameter("id_rubro")));

            try {
                this.mi.setEliminarRubro(elimina);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "El registro a eliminar tiene dependencias");
            }

            //listar Rubros
            Rubros aux = new Rubros();
            List listarRubros = this.mi.getListarRubros(aux);
            modelo.put("listarRubros", listarRubros);

            return new ModelAndView("administrarrubros/ListarRubros", modelo);
        }
        return new ModelAndView("administrarrubros/ListarRubros", modelo);
    }
}
