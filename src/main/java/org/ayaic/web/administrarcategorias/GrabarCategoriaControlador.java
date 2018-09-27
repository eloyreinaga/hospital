package org.ayaic.web.administrarcategorias;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Menues;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GrabarCategoriaControlador {

    private final MiFacade mi;

    public GrabarCategoriaControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/GrabarCategoria.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion1 = request.getParameter("accion1");
        String accion = request.getParameter("accion");

        String categoria = request.getParameter("categoria");
        String imagen = request.getParameter("imagen");
        String id_categoria = request.getParameter("id_categoria");

        if ("Adicionar".equals(accion) && "Aceptar".equals(accion1)) {
            //     Menues repetida= this.mi.getCategoria(request.getParameter("id_categoria"));  // SI EL OBJETO EXISTE ENTONCES NO ES NULO
            //    if (repetida == null) {
            if (("".equals(request.getParameter("id_categoria"))) || ("".equals(request.getParameter("categoria")))
                    || ("".equals(request.getParameter("imagen")))) {
                return new ModelAndView("Aviso", "mensaje", "Faltan Introducir Datos");
            }
            Menues categoria_c = new Menues();
            //   categoria_c.setId_categoria(Integer.parseInt(request.getParameter("id_categoria"))) ;
            //   int valorbus = this.mi.getBuscarIdCategoriaRepetido(categoria_c); //Verifica si existe la categoria
            //  if(valorbus ==0){
            categoria_c.setCategoria(request.getParameter("categoria"));
            categoria_c.setImagen(request.getParameter("imagen"));
            categoria_c.setUlt_usuario(cliente.getId_usuario());

            try {
                int valor = this.mi.setCrearCategoria(categoria_c);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "La actualización categoria no se cumplio, verifique los datos");
            }
            //listar categorias
            List listarCategorias = this.mi.getListaCategorias();
            modelo.put("listarCategorias", listarCategorias);
            return new ModelAndView("administrarcategorias/ListarCategorias", modelo);
            //  }    
            //   }
            //	else {
            //     return new ModelAndView("Aviso","mensaje","Ya existe un id_categoria igual");	
            //   }
        }

        if ("Modificar".equals(accion) && "Aceptar".equals(accion1)) {
            if (("".equals(request.getParameter("id_categoria"))) || ("".equals(request.getParameter("categoria")))
                    || ("".equals(request.getParameter("imagen")))) {
                return new ModelAndView("Aviso", "mensaje", "Faltan Introducir Datos");
            }
            Menues categoria_m = new Menues();
            categoria_m.setId_categoria(Integer.parseInt(request.getParameter("id_categoria")));
            categoria_m.setCategoria(request.getParameter("categoria"));
            categoria_m.setImagen(request.getParameter("imagen"));
            categoria_m.setUlt_usuario(cliente.getId_usuario());
            if ("A".equals(request.getParameter("id_estado"))) {
                categoria_m.setId_estado("A");
            } else {
                categoria_m.setId_estado("B");
            }

            try {
                int valor = this.mi.setModificarCategoria(categoria_m);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "La actualización no se cumplio, verifique los datos");
            }

            //listar categorias
            List listarCategorias = this.mi.getListaCategorias();
            modelo.put("listarCategorias", listarCategorias);
            return new ModelAndView("administrarcategorias/ListarCategorias", modelo);

        }

        if ("Eliminar".equals(accion) && "Aceptar".equals(accion1)) {
            if ("".equals(request.getParameter("id_categoria"))) {
                return new ModelAndView("Aviso", "mensaje", "Faltan datos");
            }
            Menues elimina = new Menues();
            elimina.setId_categoria(Integer.parseInt(request.getParameter("id_categoria")));

            int valor = this.mi.setEliminarCategoria(elimina);

            if (valor == 0) {
                return new ModelAndView("Aviso", "mensaje", "El registro a eliminar tiene dependencias");
            }

            //listar categorias
            List listarCategorias = this.mi.getListaCategorias();
            modelo.put("listarCategorias", listarCategorias);

            return new ModelAndView("administrarcategorias/ListarCategorias", modelo);
        }
        return new ModelAndView("administrarcategorias/ListarCategorias", modelo);
    }
}
