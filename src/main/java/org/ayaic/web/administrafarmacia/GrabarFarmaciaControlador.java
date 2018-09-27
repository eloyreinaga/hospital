package org.ayaic.web.administrafarmacia;

import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Farmacias;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GrabarFarmaciaControlador {

    private final MiFacade mi;

    public GrabarFarmaciaControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/GrabarFarmacia.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        HashMap modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String _id_usuario = Integer.toString(cliente.getId_usuario());
        String __ult_usuario = Integer.toString(cliente.getId_usuario());
        String accion1 = request.getParameter("accion1");
        String accion = request.getParameter("accion");
        String farmacia = request.getParameter("farmacia");
        String id_farmacia = request.getParameter("id_farmacia");

        if ("Adicionar".equals(accion) && "Aceptar".equals(accion1)) {
            if (id_farmacia == null || "Adicionar".equals(id_farmacia)) {
                id_farmacia = "0";
            }

            if (("".equals(id_farmacia)) || ("".equals(farmacia))) {
                return new ModelAndView("Aviso", "mensaje", "Faltan Introducir Datos");
            }
            Farmacias datofarmacia = new Farmacias();
            datofarmacia.setCod_esta(cliente.getCod_esta());

            datofarmacia.setFarmacia(farmacia);
            datofarmacia.setUlt_usuario(Integer.parseInt(__ult_usuario));

            try {
                this.mi.setCrearFarmacia(datofarmacia);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "La actualización no se cumplio, verifique los datos");
            }
            //listar Farmacias
            List listarFarmacias = this.mi.getListarFarmacias(datofarmacia);
            modelo.put("listarFarmacia", listarFarmacias);
            return new ModelAndView("administrafarmacias/ListarFarmacias", modelo);
        }

        if ("Modificar".equals(accion) && "Aceptar".equals(accion1)) {
            if (("".equals(id_farmacia)) || ("".equals(farmacia))) {
                return new ModelAndView("Aviso", "mensaje", "Faltan Introducir Datos");
            }
            if (("0".equals(id_farmacia))) {
                return new ModelAndView("Aviso", "mensaje", "Esta Dato no puede Modificar");
            }
            Farmacias categoria_m = new Farmacias();
            categoria_m.setFarmacia(request.getParameter("farmacia"));
            categoria_m.setUlt_usuario(Integer.parseInt(__ult_usuario));
            categoria_m.setCod_esta(cliente.getCod_esta());
            if ("A".equals(request.getParameter("id_estado"))) {
                categoria_m.setId_estado("A");
            } else {
                categoria_m.setId_estado("B");
            }

            try {
                this.mi.setModificarFarmacia(categoria_m);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "La actualización no se cumplio, verifique los datos");
            }

            //listar categorias
            Farmacias datofarmacia = new Farmacias();
            datofarmacia.setCod_esta(cliente.getCod_esta());
            List listarFarmacias = this.mi.getListarFarmacias(datofarmacia);
            modelo.put("listarFarmacia", listarFarmacias);

            return new ModelAndView("administrafarmacias/ListarFarmacias", modelo);

        }

        if ("Eliminar".equals(accion) && "Aceptar".equals(accion1)) {
            if ("".equals(request.getParameter("id_farmacia"))) {
                return new ModelAndView("Aviso", "mensaje", "Faltan datos");
            }
            if (("0".equals(id_farmacia))) {
                return new ModelAndView("Aviso", "mensaje", "Esta Dato no puede Eliminar");
            }
            Farmacias elimina = new Farmacias();
            elimina.setId_farmacia(Integer.parseInt("id_farmacia"));
            elimina.setCod_esta(cliente.getCod_esta());
            try {
                this.mi.setEliminarFarmacia(elimina);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "El registro a eliminar tiene dependencias");
            }

            //listar categorias
            Farmacias datofarmacia = new Farmacias();
            datofarmacia.setCod_esta(cliente.getCod_esta());
            List listarFarmacias = this.mi.getListarFarmacias(datofarmacia);
            modelo.put("listarFarmacias", listarFarmacias);

            return new ModelAndView("administrafarmacias/ListarFarmacias", modelo);
        }
        return new ModelAndView("administrafarmacias/ListarFarmacias", modelo);
    }
}
