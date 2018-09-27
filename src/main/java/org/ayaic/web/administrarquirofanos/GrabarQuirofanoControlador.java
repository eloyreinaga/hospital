package org.ayaic.web.administrarquirofanos;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Quirofanos;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GrabarQuirofanoControlador {

    private final MiFacade mi;

    public GrabarQuirofanoControlador(MiFacade mi) {
        this.mi = mi;
    }
    
    @RequestMapping("/GrabarQuirofano.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion1 = request.getParameter("accion1");
        String accion = request.getParameter("accion");
        String quirofano = request.getParameter("quirofano");
        String id_quirofano = request.getParameter("id_quirofano");

        if ("Adicionar".equals(accion) && "Aceptar".equals(accion1)) {

            Quirofanos datoqui = new Quirofanos();
            datoqui.setId_quirofano(Integer.parseInt(id_quirofano));
            Quirofanos repetida = this.mi.getDatosQuirofano(datoqui);  // SI EL OBJETO EXISTE ENTONCES NO ES NULO
            if (repetida == null) {
                if (("".equals(id_quirofano)) || ("".equals(quirofano))) {
                    return new ModelAndView("Aviso", "mensaje", "Faltan Introducir Datos");
                }
                Quirofanos datoquirofano = new Quirofanos();
                datoquirofano.setQuirofano(quirofano.toUpperCase());
                datoquirofano.setUlt_usuario(cliente.getId_usuario());
                datoquirofano.setCod_esta(cliente.getCod_esta());

                try {
                    this.mi.setCrearQuirofano(datoquirofano);
                } catch (Exception e) {
                    return new ModelAndView("Aviso", "mensaje", "La actualización no se cumplio, verifique los datos");
                }
                //listar Quirofanos
                datoquirofano.setCadena11("L");
                List listarQuirofanos = this.mi.getListarQuirofanosLibres(datoquirofano);
                modelo.put("listarQuirofanos", listarQuirofanos);

                return new ModelAndView("administrarquirofanos/ListarQuirofanos", modelo);

            } else {
                return new ModelAndView("Aviso", "mensaje", "Ya existe un id_quirofano igual");
            }
        }

        if ("Modificar".equals(accion) && "Aceptar".equals(accion1)) {
            if (("".equals(id_quirofano)) || ("".equals(quirofano))) {
                return new ModelAndView("Aviso", "mensaje", "Faltan Introducir Datos");
            }
            Quirofanos categoria_m = new Quirofanos();
            categoria_m.setId_quirofano(Integer.parseInt(request.getParameter("id_quirofano")));
            categoria_m.setQuirofano(request.getParameter("quirofano"));
            categoria_m.setUlt_usuario(cliente.getId_usuario());

            if ("A".equals(request.getParameter("id_estado"))) {
                categoria_m.setId_estado("A");
            } else {
                categoria_m.setId_estado("B");
            }

            try {
                this.mi.setModificarQuirofano(categoria_m);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "La actualización no se cumplio, verifique los datos");
            }

            //listar categorias
            Quirofanos datoqui = new Quirofanos();
            datoqui.setCod_esta(cliente.getCod_esta());
            List listarQuirofanos = this.mi.getListarQuirofanos(datoqui);
            modelo.put("listarQuirofanos", listarQuirofanos);

            return new ModelAndView("administrarquirofanos/ListarQuirofanos", modelo);

        }

        if ("Eliminar".equals(accion) && "Aceptar".equals(accion1)) {
            if ("".equals(request.getParameter("id_quirofano"))) {
                return new ModelAndView("Aviso", "mensaje", "Faltan datos");
            }
            Quirofanos elimina = new Quirofanos();
            elimina.setId_quirofano(Integer.parseInt(request.getParameter("id_quirofano")));

            try {
                this.mi.setEliminarQuirofano(elimina);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "El registro a eliminar tiene dependencias");
            }

            //listar Quirofanos
            Quirofanos datoqui = new Quirofanos();
            datoqui.setCod_esta(cliente.getCod_esta());
            List listarQuirofanos = this.mi.getListarQuirofanos(datoqui);
            modelo.put("listarQuirofanos", listarQuirofanos);

            return new ModelAndView("administrarquirofanos/ListarQuirofanos", modelo);
        }
        return new ModelAndView("administrarquirofanos/ListarQuirofanos", modelo);
    }
}
