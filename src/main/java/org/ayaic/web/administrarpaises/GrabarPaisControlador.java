package org.ayaic.web.administrarpaises;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Paises;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GrabarPaisControlador {

    private final MiFacade mi;

    public GrabarPaisControlador(MiFacade mi) {
        this.mi = mi;
    }
  
    @RequestMapping("/GrabarPais.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion1 = request.getParameter("accion1");
        String accion = request.getParameter("accion");
        String pais = request.getParameter("pais");
        String nacionalidad = request.getParameter("nacionalidad");
        String id_pais = request.getParameter("id_pais");

        if ("Adicionar".equals(accion) && "Aceptar".equals(accion1)) {
            Paises repetida = this.mi.getDatosPais(Integer.parseInt(request.getParameter("id_pais")));  // SI EL OBJETO EXISTE ENTONCES NO ES NULO
            if (repetida == null) {
                if (("".equals(id_pais)) || ("".equals(pais)) || ("".equals(nacionalidad))) {
                    return new ModelAndView("Aviso", "mensaje", "Faltan Introducir Datos");
                }
                Paises datopais = new Paises();
                datopais.setId_pais(Integer.parseInt(id_pais));
                datopais.setPais(pais);
                datopais.setNacionalidad(nacionalidad);
                datopais.setUlt_usuario(cliente.getId_usuario());

                try {
                    this.mi.setCrearPais(datopais);
                } catch (Exception e) {
                    return new ModelAndView("Aviso", "mensaje", "La actualización no se cumplio, verifique los datos");
                }
                //listar paises
                List listarPaises = this.mi.getListarPaises();
                modelo.put("listarPaises", listarPaises);

                return new ModelAndView("administrarpaises/ListarPaises", modelo);

            } else {
                return new ModelAndView("Aviso", "mensaje", "Ya existe un id_pais igual");
            }
        }

        if ("Modificar".equals(accion) && "Aceptar".equals(accion1)) {
            if (("".equals(id_pais)) || ("".equals(pais)) || ("".equals(nacionalidad))) {
                return new ModelAndView("Aviso", "mensaje", "Faltan Introducir Datos");
            }
            Paises categoria_m = new Paises();
            categoria_m.setId_pais(Integer.parseInt(request.getParameter("id_pais")));
            categoria_m.setPais(request.getParameter("pais"));
            categoria_m.setNacionalidad(request.getParameter("nacionalidad"));
            categoria_m.setUlt_usuario(cliente.getId_usuario());

            if ("A".equals(request.getParameter("id_estado"))) {
                categoria_m.setId_estado("A");
            } else {
                categoria_m.setId_estado("B");
            }

            try {
                this.mi.setModificarPais(categoria_m);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "La actualización no se cumplio, verifique los datos");
            }

            //listar categorias
            List listarPaises = this.mi.getListarPaises();
            modelo.put("listarPaises", listarPaises);

            return new ModelAndView("administrarpaises/ListarPaises", modelo);

        }

        if ("Eliminar".equals(accion) && "Aceptar".equals(accion1)) {
            if ("".equals(request.getParameter("id_pais"))) {
                return new ModelAndView("Aviso", "mensaje", "Faltan datos");
            }
            Paises elimina = new Paises();
            elimina.setId_pais(Integer.parseInt(request.getParameter("id_pais")));

            try {
                this.mi.setEliminarPais(elimina);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "El registro a eliminar tiene dependencias");
            }

            //listar categorias
            List listarPaises = this.mi.getListarPaises();
            modelo.put("listarPaises", listarPaises);

            return new ModelAndView("administrarpaises/ListarPaises", modelo);
        }
        return new ModelAndView("administrarpaises/ListarPaises", modelo);
    }
}
