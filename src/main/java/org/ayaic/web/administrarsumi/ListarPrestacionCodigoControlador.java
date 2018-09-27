package org.ayaic.web.administrarsumi;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Prestaciones;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ListarPrestacionCodigoControlador {

    private final MiFacade mi;

    public ListarPrestacionCodigoControlador(MiFacade mi) {
        this.mi = mi;
    }
    
    @RequestMapping("/ListarPrestacionCodigo.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();
        
        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String boton = request.getParameter("boton");
        String accion = request.getParameter("accion");
        String nombresPres = request.getParameter("nombresPres");
        String expedido = request.getParameter("expedido");

        if (nombresPres == null) {
            nombresPres = "";
        }
        Prestaciones datopres = new Prestaciones();
        datopres.setCod_esta(cliente.getCod_esta());
        if ("Pormedica".equals(accion)) {
            String nombremed = request.getParameter("nombremed");
            return new ModelAndView("administrarsumi/ListarPrestacionMedica", modelo);
        }

        if ("BuscarMed".equals(boton)) {
            String nombremed = request.getParameter("nombremed");
            datopres.setDescripcion(nombremed+"%");
            List listPrestacion = this.mi.getListarMedicamentosSumi(datopres);
            modelo.put("listarPrestaciones", listPrestacion);

            return new ModelAndView("administrarsumi/ListarPrestacionMedica", modelo);
        }
        datopres.setDescripcion(nombresPres+"%");
        List listPrestacion = this.mi.getListarPrestaciones(datopres);
        modelo.put("listarPrestaciones", listPrestacion);

        modelo.put("nombresPres", nombresPres);
        modelo.put("expedido", expedido);

        return new ModelAndView("administrarsumi/ListarPrestacionCodigo", modelo);
    }
}
