package org.ayaic.web.administrarlaboratorios;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Laboratorios;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GrabarLaboratorioControlador {

    private final MiFacade mi;

    public GrabarLaboratorioControlador(MiFacade mi) {
        this.mi = mi;
    }
    
    @RequestMapping("/GrabarLaboratorio.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion1 = request.getParameter("accion1");
        String accion = request.getParameter("accion");

        String laboratorio = request.getParameter("laboratorio");
        String id_laboratorio = request.getParameter("id_laboratorio");

        if ("Adicionar".equals(accion) && "Aceptar".equals(accion1)) {
            Laboratorios pai = new Laboratorios();
            pai.setId_laboratorio(Integer.parseInt(id_laboratorio));
            Laboratorios repetida = this.mi.getDatosLaboratorio(pai);  // SI EL OBJETO EXISTE ENTONCES NO ES NULO
            if (repetida == null) {
                if (("".equals(id_laboratorio)) || ("".equals(laboratorio))) {
                    return new ModelAndView("Aviso", "mensaje", "Faltan Introducir Datos");
                }
                Laboratorios datolaboratorio = new Laboratorios();
                datolaboratorio.setLaboratorio(laboratorio);
                datolaboratorio.setUlt_usuario(cliente.getId_usuario());

                try {
                    this.mi.setCrearLaboratorio(datolaboratorio);
                } catch (Exception e) {
                    return new ModelAndView("Aviso", "mensaje", "La actualización no se cumplio, verifique los datos");
                }
                //listar laboratorios
                Laboratorios pail = new Laboratorios();
                pail.setId_estado("A");
                List listarLaboratorios = this.mi.getListarLaboratorios(pail);
                modelo.put("listarLaboratorios", listarLaboratorios);
                return new ModelAndView("administrarlaboratorios/ListarLaboratorios", modelo);

            } else {
                return new ModelAndView("Aviso", "mensaje", "Ya existe un id_laboratorio igual");
            }
        }

        if ("Modificar".equals(accion) && "Aceptar".equals(accion1)) {
            if (("".equals(id_laboratorio)) || ("".equals(laboratorio))) {
                return new ModelAndView("Aviso", "mensaje", "Faltan Introducir Datos");
            }
            Laboratorios categoria_m = new Laboratorios();
            categoria_m.setId_laboratorio(Integer.parseInt(request.getParameter("id_laboratorio")));
            categoria_m.setLaboratorio(request.getParameter("laboratorio"));
            categoria_m.setUlt_usuario(cliente.getId_usuario());

            if ("A".equals(request.getParameter("id_estado"))) {
                categoria_m.setId_estado("A");
            } else {
                categoria_m.setId_estado("B");
            }

            try {
                this.mi.setModificarLaboratorio(categoria_m);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "La actualización no se cumplio, verifique los datos");
            }

            Laboratorios pail = new Laboratorios();
            pail.setId_estado("A");
            List listarLaboratorios = this.mi.getListarLaboratorios(pail);
            //List listarLaboratorios = this.mi.getListarLaboratorios("%");
            modelo.put("listarLaboratorios", listarLaboratorios);
            return new ModelAndView("administrarlaboratorios/ListarLaboratorios", modelo);

        }

        if ("Eliminar".equals(accion) && "Aceptar".equals(accion1)) {
            if ("".equals(request.getParameter("id_laboratorio"))) {
                return new ModelAndView("Aviso", "mensaje", "Faltan datos");
            }
            Laboratorios elimina = new Laboratorios();
            elimina.setId_laboratorio(Integer.parseInt(request.getParameter("id_laboratorio")));

            try {
                this.mi.setEliminarLaboratorio(elimina);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "El registro a eliminar tiene dependencias");
            }

            //listar categorias
            Laboratorios pail = new Laboratorios();
            pail.setId_estado("%");
            List listarLaboratorios = this.mi.getListarLaboratorios(pail);
            //List listarLaboratorios = this.mi.getListarLaboratorios("%");
            modelo.put("listarLaboratorios", listarLaboratorios);

            return new ModelAndView("administrarlaboratorios/ListarLaboratorios", modelo);
        }
        return new ModelAndView("administrarlaboratorios/ListarLaboratorios", modelo);
    }
}
