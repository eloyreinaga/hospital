package org.ayaic.web.administrarsumi;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Prestaciones;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GrabarPrestacionControlador {

    private final MiFacade mi;

    public GrabarPrestacionControlador(MiFacade mi) {
        this.mi = mi;
    }
    
    @RequestMapping("/GrabarPrestacion.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion1 = request.getParameter("accion1");
        String accion = request.getParameter("accion");
        String id_prestacion = request.getParameter("id_prestacion");
        String internado = request.getParameter("internado");
        String veces = request.getParameter("veces");
        String descripcion = request.getParameter("descripcion");
        String costo = request.getParameter("costo");
        String paquete = request.getParameter("paquete");

        if ("Adicionar".equals(accion) && "Aceptar".equals(accion1)) {
            Prestaciones repetida = this.mi.getDatosPrestacion(Integer.parseInt(request.getParameter("id_prestacion")));  // SI EL OBJETO EXISTE ENTONCES NO ES NULO
            if (repetida == null) {
                if (("".equals(descripcion)) || ("".equals(costo))) {
                    return new ModelAndView("Aviso", "mensaje", "Faltan Introducir Datos");
                }
                Prestaciones datopres = new Prestaciones();
                datopres.setDescripcion(descripcion);
                datopres.setCosto(Float.parseFloat(costo));
                datopres.setPaquete(paquete);

                try {
                    this.mi.setCrearPrestacion(datopres);
                } catch (Exception e) {
                    return new ModelAndView("Aviso", "mensaje", "La actualización no se cumplio, verifique los datos");
                }

                return new ModelAndView("administrarsumi/ListarPrestacionSumi", modelo);

            } else {
                return new ModelAndView("Aviso", "mensaje", "Ya existe un id_prestacion igual");
            }
        }

        if ("Modificar".equals(accion) && "Aceptar".equals(accion1)) {
            if (("".equals(id_prestacion)) || ("".equals(descripcion)) || ("".equals(costo))) {
                return new ModelAndView("Aviso", "mensaje", "Faltan Introducir Datos");
            }
            Prestaciones datopres = new Prestaciones();
            datopres.setId_prestacion(Integer.parseInt(request.getParameter("id_prestacion")));
            datopres.setSuma1(Integer.parseInt(internado));
            datopres.setSuma2(Integer.parseInt(veces));
            datopres.setDescripcion(descripcion);
            datopres.setCosto(Float.parseFloat(costo));
            datopres.setPaquete(paquete);

            try {
                this.mi.setModificarPrestacion(datopres);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "La actualización no se cumplio, verifique los datos");
            }

            return new ModelAndView("administrarsumi/ListarPrestacionSumi", modelo);

        }

        if ("Eliminar".equals(accion) && "Aceptar".equals(accion1)) {
            if ("".equals(request.getParameter("id_prestacion"))) {
                return new ModelAndView("Aviso", "mensaje", "Faltan datos");
            }
            Prestaciones elimina = new Prestaciones();
            elimina.setId_prestacion(Integer.parseInt(request.getParameter("id_prestacion")));

            try {
                this.mi.setEliminarPrestacion(elimina);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "El registro a eliminar tiene dependencias");
            }

            return new ModelAndView("administrarsumi/ListarPrestacionSumi", modelo);

        }
        return new ModelAndView("administrarsumi/ListarPrestacionSumi", modelo);

    }
}
