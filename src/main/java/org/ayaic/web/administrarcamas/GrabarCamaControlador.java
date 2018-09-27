package org.ayaic.web.administrarcamas;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Camas;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GrabarCamaControlador {

    private final MiFacade mi;

    public GrabarCamaControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/GrabarCama.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion1 = request.getParameter("accion1");
        String accion = request.getParameter("accion");

        String id_cama = request.getParameter("id_cama");
        String cama = request.getParameter("cama");
        String cama_unit = request.getParameter("cama_unit");
        String id_sala = request.getParameter("id_sala");

        if ("Adicionar".equals(accion) && "Aceptar".equals(accion1)) {

            if (("".equals(cama)) || ("".equals(cama_unit))) {
                return new ModelAndView("Aviso", "mensaje", "Faltan Introducir Datos");
            }
            Camas datocama = new Camas();
            //          datocama.setId_cama(Integer.parseInt(id_cama));
            datocama.setCama(cama);
            datocama.setCama_unit(Float.parseFloat(cama_unit));
            datocama.setId_sala(Integer.parseInt(id_sala));

            try {
                this.mi.setCrearCama(datocama);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "La actualización no se cumplio, verifique los datos");
            }
            //listar camas
//	    List listarCamas = this.mi.getListarCamas(Integer.parseInt(id_rubro));
//            modelo.put("listarCamas", listarCamas);

            return new ModelAndView("administrarcamas/ListarCamas", modelo);
        }

        if ("Modificar".equals(accion) && "Aceptar".equals(accion1)) {
            if (("".equals(id_cama)) || ("".equals(cama)) || ("".equals(cama_unit))) {
                return new ModelAndView("Aviso", "mensaje", "Faltan Introducir Datos");
            }
            Camas categoria_m = new Camas();
            categoria_m.setId_cama(Integer.parseInt(request.getParameter("id_cama")));
            categoria_m.setCama(request.getParameter("cama"));
            categoria_m.setCama_unit(Float.parseFloat(cama_unit));
            categoria_m.setEstado(0);

            try {
                this.mi.setModificarCama(categoria_m);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "La actualización no se cumplio, verifique los datos");
            }

            //listar categorias
            Camas lcama = new Camas();
            lcama.setId_sala(1);
            lcama.setTipo("T");
            List listarCamas = this.mi.getListarCamas(lcama);
            modelo.put("listarCamas", listarCamas);

            return new ModelAndView("administrarcamas/ListarCamas", modelo);

        }

        if ("Eliminar".equals(accion) && "Aceptar".equals(accion1)) {
            if ("".equals(request.getParameter("id_cama"))) {
                return new ModelAndView("Aviso", "mensaje", "Faltan datos");
            }
            Camas elimina = new Camas();
            elimina.setId_cama(Integer.parseInt(request.getParameter("id_cama")));

            try {
                this.mi.setEliminarCama(elimina);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "El registro a eliminar tiene dependencias");
            }

            return new ModelAndView("administrarcamas/ListarCamas", modelo);
        }

        return new ModelAndView("administrarcamas/ListarCamas", modelo);
    }
}
