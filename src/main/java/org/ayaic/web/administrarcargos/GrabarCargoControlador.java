package org.ayaic.web.administrarcargos;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Consultorios;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GrabarCargoControlador {

    private final MiFacade mi;

    public GrabarCargoControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/GrabarCargo.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion1 = request.getParameter("accion1");
        String accion = request.getParameter("accion");

        String cargo = request.getParameter("cargo");
        String descripcion = request.getParameter("descripcion");
        String id_cargo = request.getParameter("id_cargo");

        if ("Adicionar".equals(accion) && "Aceptar".equals(accion1)) {
            Consultorios repetida = this.mi.getDatosCargo(Integer.parseInt(request.getParameter("id_cargo")));  // SI EL OBJETO EXISTE ENTONCES NO ES NULO
            if (repetida == null) {
                if (("".equals(id_cargo)) || ("".equals(cargo)) || ("".equals(descripcion))) {
                    return new ModelAndView("Aviso", "mensaje", "Faltan Introducir Datos");
                }
                Consultorios datopais = new Consultorios();
                datopais.setId_cargo(Integer.parseInt(id_cargo));
                datopais.setCargo(cargo);
                datopais.setDescripcion(descripcion);
                datopais.setUlt_usuario(cliente.getId_usuario());

                try {
                    this.mi.setCrearCargo(datopais);
                } catch (Exception e) {
                    return new ModelAndView("Aviso", "mensaje", "La actualización no se cumplio, verifique los datos");
                }
                //listar Emptelefonica
                List listarcargos = this.mi.getListarCargos();
                modelo.put("listarCargos", listarcargos);

                return new ModelAndView("administrarcargos/ListarCargos", modelo);

            } else {
                return new ModelAndView("Aviso", "mensaje", "Ya existe un id_cargo igual");
            }
        }

        if ("Modificar".equals(accion) && "Aceptar".equals(accion1)) {
            if (("".equals(id_cargo)) || ("".equals(cargo)) || ("".equals(descripcion))) {
                return new ModelAndView("Aviso", "mensaje", "Faltan Introducir Datos");
            }
            Consultorios categoria_m = new Consultorios();
            categoria_m.setId_cargo(Integer.parseInt(id_cargo));
            categoria_m.setCargo(cargo);
            categoria_m.setDescripcion(descripcion);
            categoria_m.setUlt_usuario(cliente.getId_usuario());

            if ("A".equals(request.getParameter("id_estado"))) {
                categoria_m.setId_estado("A");
            } else {
                categoria_m.setId_estado("B");
            }

            try {
                this.mi.setModificarCargo(categoria_m);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "La actualización no se cumplio, verifique los datos");
            }

            //listar categorias
            List listarcargos = this.mi.getListarCargos();
            modelo.put("listarCargos", listarcargos);

            return new ModelAndView("administrarcargos/ListarCargos", modelo);

        }

        if ("Eliminar".equals(accion) && "Aceptar".equals(accion1)) {
            if ("".equals(request.getParameter("id_cargo"))) {
                return new ModelAndView("Aviso", "mensaje", "Faltan datos");
            }
            Consultorios elimina = new Consultorios();
            elimina.setId_cargo(Integer.parseInt(request.getParameter("id_cargo")));

            try {
                this.mi.setEliminarCargo(elimina);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "El registro a eliminar tiene dependencias");
            }

            //listar categorias
            List listarcargos = this.mi.getListarCargos();
            modelo.put("listarCargos", listarcargos);

            return new ModelAndView("administrarcargos/ListarCargos", modelo);
        }
        return new ModelAndView("administrarcargos/ListarCargos", modelo);
    }
}
