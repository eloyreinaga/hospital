package org.ayaic.web.administrarcostos;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Costos;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GrabarCostoControlador {

    private final MiFacade mi;

    public GrabarCostoControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/GrabarCosto.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion1 = request.getParameter("accion1");
        String accion = request.getParameter("accion");

        String id_costo = request.getParameter("id_costo");
        String costo = request.getParameter("costo");
        String costo_unit = request.getParameter("costo_unit");
        String id_rubro = request.getParameter("id_rubro");
        String normales = request.getParameter("normales");
        String defecto = request.getParameter("defecto");
        String muestra = request.getParameter("muestra");
        String id_laboratorio = request.getParameter("id_laboratorio");
        String id_estado = request.getParameter("id_estado");
        String urgencias = request.getParameter("urgencias");
        String emergencias = request.getParameter("emergencias");
        String id_prestacion = request.getParameter("id_prestacion");
        String id_nivel = request.getParameter("id_nivel");

        if ("Adicionar".equals(accion) && "Aceptar".equals(accion1)) {

            if (("".equals(costo)) || ("".equals(costo_unit))) {
                return new ModelAndView("Aviso", "mensaje", "Faltan Introducir Datos");
            }
            Costos datocosto = new Costos();
            //          datocosto.setId_costo(Integer.parseInt(id_costo));
            datocosto.setCosto(costo);
            datocosto.setNormales(normales);
            datocosto.setDefecto(defecto);
            datocosto.setMuestra(muestra);
            datocosto.setCod_esta(cliente.getCod_esta());
            if ("6".equals(id_rubro)) {
                datocosto.setId_laboratorio(Integer.parseInt(id_laboratorio));
                datocosto.setId_estado(id_estado);
                datocosto.setId_prestacion(Integer.parseInt(id_prestacion));
                datocosto.setId_nivel(Integer.parseInt(id_nivel));
                datocosto.setEmergencias(Integer.parseInt(emergencias));
            } else {
                datocosto.setId_laboratorio(0);
                datocosto.setId_estado("A");
                datocosto.setId_prestacion(0);
                datocosto.setId_nivel(0);
                datocosto.setEmergencias(0);
            }
            datocosto.setCosto_unit(Float.parseFloat(costo_unit));
            datocosto.setId_rubro(Integer.parseInt(id_rubro));

            try {
                this.mi.setCrearCosto(datocosto);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "La creacion costos no se cumplio, verifique los datos");
            }
            //listar costos
//	    List listarCostos = this.mi.getListarCostos(Integer.parseInt(id_rubro));
//            modelo.put("listarCostos", listarCostos);

            return new ModelAndView("administrarcostos/ListarCostos", modelo);
        }

        if ("Modificar".equals(accion) && "Aceptar".equals(accion1)) {
            if (("".equals(id_costo)) || ("".equals(costo)) || ("".equals(costo_unit))) {
                return new ModelAndView("Aviso", "mensaje", "Faltan Introducir Datos");
            }
            Costos categoria_m = new Costos();
            categoria_m.setId_costo(Integer.parseInt(request.getParameter("id_costo")));
            categoria_m.setCosto(request.getParameter("costo"));
            categoria_m.setNormales(normales);
            categoria_m.setDefecto(defecto);
            categoria_m.setMuestra(muestra);
            categoria_m.setCod_esta(cliente.getCod_esta());
            if ("6".equals(id_rubro)) {
                categoria_m.setId_laboratorio(Integer.parseInt(id_laboratorio));
                categoria_m.setId_estado(id_estado);
                categoria_m.setId_prestacion(Integer.parseInt(id_prestacion));
                categoria_m.setId_nivel(Integer.parseInt(id_nivel));
                categoria_m.setEmergencias(Integer.parseInt(emergencias));
            } else {
                categoria_m.setId_laboratorio(0);
                categoria_m.setId_estado("A");
                categoria_m.setId_prestacion(0);
                categoria_m.setId_nivel(0);
                categoria_m.setEmergencias(0);
            }
            categoria_m.setCosto_unit(Float.parseFloat(costo_unit));

            try {
                this.mi.setModificarCosto(categoria_m);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "La actualizacion del costos no se cumplio, verifique los datos");
            }
            Costos datoq = new Costos();
            datoq.setId_rubro(1);
            datoq.setId_prestacion(1);
            datoq.setId_estado("%");
            datoq.setTipo(0);
            datoq.setId_persona(5000);
            datoq.setEmergencias(0);
            datoq.setCod_esta(cliente.getCod_esta());///para todos 20-02-2017
            //listar categorias
            List listarCostos = this.mi.getListarCostos(datoq);
            modelo.put("listarCostos", listarCostos);
            return new ModelAndView("administrarcostos/ListarCostos", modelo);

        }

        if ("Eliminar".equals(accion) && "Aceptar".equals(accion1)) {
            if ("".equals(request.getParameter("id_costo"))) {
                return new ModelAndView("Aviso", "mensaje", "Faltan datos");
            }
            Costos elimina = new Costos();
            elimina.setId_costo(Integer.parseInt(request.getParameter("id_costo")));

            try {
                this.mi.setEliminarCosto(elimina);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "El registro costos a eliminar tiene dependencias");
            }

            //listar costos
//       List listarCostos = this.mi.getListarCostos(3);
//       modelo.put("listarCostos", listarCostos);    
            return new ModelAndView("administrarcostos/ListarCostos", modelo);
        }
        return new ModelAndView("administrarcostos/ListarCostos", modelo);
    }
}
