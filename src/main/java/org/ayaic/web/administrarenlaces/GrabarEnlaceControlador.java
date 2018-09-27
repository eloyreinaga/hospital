package org.ayaic.web.administrarenlaces;

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
public class GrabarEnlaceControlador {

    private final MiFacade mi;

    public GrabarEnlaceControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/GrabarEnlace.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion1 = request.getParameter("accion1");
        String accion = request.getParameter("accion");

        String id_categoria = request.getParameter("id_categoria");
        String enlace = request.getParameter("enlace");
        String ruta = request.getParameter("ruta");
        String orden = request.getParameter("orden");
        String imagen = request.getParameter("imagen");
        String id_enlace = request.getParameter("id_enlace");

        if ("Adicionar".equals(accion) && "Aceptar".equals(accion1)) {
            if (("".equals(request.getParameter("id_categoria"))) || ("".equals(request.getParameter("enlace")))
                    || ("".equals(request.getParameter("ruta"))) || ("".equals(request.getParameter("orden")))
                    || ("".equals(request.getParameter("imagen")))) {
                return new ModelAndView("Aviso", "mensaje", "Faltan Introducir Datos");
            }
            Menues enla = new Menues();

            enla.setId_categoria(Integer.parseInt(request.getParameter("id_categoria")));
            enla.setEnlace(request.getParameter("enlace"));
            enla.setRuta(request.getParameter("ruta"));
            enla.setOrden(Integer.parseInt(request.getParameter("orden")));
            enla.setImagen(request.getParameter("imagen"));
            enla.setUlt_usuario(cliente.getId_usuario());

            //try{     	
            int valor = this.mi.setCrearEnlace(enla);
            //}catch (Exception e){ 
            //  return new ModelAndView("Aviso","mensaje","El Registro no se cumplio, verifique los datos");
            //}    

            //lista los enlaces
            List listarEnlaces = this.mi.getListar_Enlaces();
            modelo.put("listarEnlaces", listarEnlaces);
            return new ModelAndView("administrarenlaces/ListarEnlaces", modelo);
        }

        if ("Modificar".equals(accion) && "Aceptar".equals(accion1)) {
            if (("".equals(request.getParameter("id_enlace"))) || ("".equals(request.getParameter("id_categoria")))
                    || ("".equals(request.getParameter("enlace"))) || ("".equals(request.getParameter("orden")))
                    || ("".equals(request.getParameter("ruta"))) || ("".equals(request.getParameter("imagen")))) {
                return new ModelAndView("Aviso", "mensaje", "Faltan Introducir Datos");
            }
            Menues enla = new Menues();
            enla.setId_enlace(Integer.parseInt(request.getParameter("id_enlace")));
            enla.setOrden(Integer.parseInt(request.getParameter("orden")));
            enla.setEnlace(request.getParameter("enlace"));
            enla.setRuta(request.getParameter("ruta"));
            enla.setImagen(request.getParameter("imagen"));
            enla.setId_categoria(Integer.parseInt(request.getParameter("id_categoria")));
            if ("A".equals(request.getParameter("id_estado"))) {
                enla.setId_estado("A");
            } else {
                enla.setId_estado("B");
            }

            try {
                int valor = this.mi.setModificarEnlace(enla);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "La actualización no se cumplio, verifique los datos");
            }

            //lista los enlaces
            List listarEnlaces = this.mi.getListar_Enlaces();
            modelo.put("listarEnlaces", listarEnlaces);
            return new ModelAndView("administrarenlaces/ListarEnlaces", modelo);
        }

        if ("Eliminar".equals(accion) && "Aceptar".equals(accion1)) {
            if ("".equals(request.getParameter("id_enlace"))) {
                return new ModelAndView("Aviso", "mensaje", "Faltan datos");
            }
            Menues elimina = new Menues();
            elimina.setId_enlace(Integer.parseInt(request.getParameter("id_enlace")));

            int valor = this.mi.setEliminarEnlace(elimina);

            if (valor == 0) {
                return new ModelAndView("Aviso", "mensaje", "El enlace a eliminar tiene dependencias");
            }

            //lista los enlaces
            List listarEnlaces = this.mi.getListar_Enlaces();
            modelo.put("listarEnlaces", listarEnlaces);

            return new ModelAndView("administrarenlaces/ListarEnlaces", modelo);
        }
        return new ModelAndView("administrarenlaces/ListarEnlaces", modelo);
    }
}
