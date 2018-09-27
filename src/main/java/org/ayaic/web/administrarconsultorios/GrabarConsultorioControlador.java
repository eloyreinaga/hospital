package org.ayaic.web.administrarconsultorios;

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
public class GrabarConsultorioControlador {

    private final MiFacade mi;

    public GrabarConsultorioControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/GrabarConsultorio.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String id_cargo = request.getParameter("id_cargo");
        String accion1 = request.getParameter("accion1");
        String accion = request.getParameter("accion");

        String cargo = request.getParameter("consultorio");
        String descripcion = request.getParameter("descripcion");
        String id_consultorio = request.getParameter("id_consultorio");

        if ("Adicionar".equals(accion) && "Aceptar".equals(accion1)) {
            //     Consultorios repetida= this.mi.getDatosConsultorio(Integer.parseInt(request.getParameter("id_consultorio")) );  // SI EL OBJETO EXISTE ENTONCES NO ES NULO
            //     if (repetida == null) {
            if (("".equals(id_consultorio)) || ("".equals(cargo)) || ("".equals(descripcion))) {
                return new ModelAndView("Aviso", "mensaje", "Faltan Introducir Datos");
            }
            Consultorios datopais = new Consultorios();
            datopais.setId_consultorio(Integer.parseInt(id_consultorio));
            datopais.setConsultorio(cargo);

            datopais.setDescripcion(descripcion);
            datopais.setUlt_usuario(cliente.getId_usuario());

            try {
                this.mi.setCrearConsultorio(datopais);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "La actualización no se cumplio, verifique los datos");
            }
            Consultorios a = new Consultorios();
            a.setCod_esta(cliente.getCod_esta());
            List listarCargos = this.mi.getListarConsultorios(a);

            modelo.put("listarCargos", listarCargos);
            return new ModelAndView("administrarconsultorios/ListarConsultorios", modelo);

            //     }
//	else {
            //        return new ModelAndView("Aviso","mensaje","Ya existe un id_consultorio igual");	
            //     }
        }

        if ("Modificar".equals(accion) && "Aceptar".equals(accion1)) {
            if (("".equals(cargo)) || ("".equals(descripcion))) {
                return new ModelAndView("Aviso", "mensaje", "Faltan Introducir Datos");
            }
            Consultorios categoria_m = new Consultorios();
            categoria_m.setId_consultorio(Integer.parseInt(id_consultorio));
            categoria_m.setConsultorio(cargo);
            categoria_m.setId_cargo(Integer.parseInt(id_cargo));
            categoria_m.setDescripcion(descripcion);
            categoria_m.setUlt_usuario(cliente.getId_usuario());

            if ("A".equals(request.getParameter("id_estado"))) {
                categoria_m.setId_estado("A");
            } else {
                categoria_m.setId_estado("B");
            }

            try {
                this.mi.setModificarConsultorio(categoria_m);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "La actualización no se cumplio, verifique los datos");
            }

            //listar categorias
            Consultorios a = new Consultorios();
            a.setCod_esta(cliente.getCod_esta());
            List listarCargos = this.mi.getListarConsultorios(a);

            modelo.put("listarCargos", listarCargos);
            return new ModelAndView("administrarconsultorios/ListarConsultorios", modelo);

        }

        if ("Eliminar".equals(accion) && "Aceptar".equals(accion1)) {
            if ("".equals(request.getParameter("id_consultorio"))) {
                return new ModelAndView("Aviso", "mensaje", "Faltan datos");
            }
            Consultorios elimina = new Consultorios();
            elimina.setId_consultorio(Integer.parseInt(request.getParameter("id_consultorio")));

            try {
                this.mi.setEliminarConsultorio(elimina);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "El registro a eliminar tiene dependencias");
            }

            //listar categorias
            Consultorios a = new Consultorios();
            a.setCod_esta(cliente.getCod_esta());
            List listarCargos = this.mi.getListarConsultorios(a);

            modelo.put("listarCargos", listarCargos);

            return new ModelAndView("administrarconsultorios/ListarConsultorios", modelo);
        }
        return new ModelAndView("administrarconsultorios/ListarConsultorios", modelo);
    }
}
