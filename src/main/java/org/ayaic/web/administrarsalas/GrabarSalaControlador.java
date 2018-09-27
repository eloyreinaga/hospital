package org.ayaic.web.administrarsalas;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Salas;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GrabarSalaControlador {

    private final MiFacade mi;

    public GrabarSalaControlador(MiFacade mi) {
        this.mi = mi;
    }
    
    @RequestMapping("/GrabarSala.do\"")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion1 = request.getParameter("accion1");
        String accion = request.getParameter("accion");
        String sala = request.getParameter("sala");
        String id_sala = request.getParameter("id_sala");

        if ("Adicionar".equals(accion) && "Aceptar".equals(accion1)) {
            Salas repetida = this.mi.getDatosSala(Integer.parseInt(request.getParameter("id_sala")));  // SI EL OBJETO EXISTE ENTONCES NO ES NULO
            if (repetida == null) {
                if (("".equals(id_sala)) || ("".equals(sala))) {
                    return new ModelAndView("Aviso", "mensaje", "Faltan Introducir Datos");
                }
                Salas datosala = new Salas();
//	   datosala.setId_sala(Integer.parseInt(id_sala)) ;
                datosala.setSala(sala);
                datosala.setUlt_usuario(cliente.getId_usuario());

                try {
                    this.mi.setCrearSala(datosala);
                } catch (Exception e) {
                    return new ModelAndView("Aviso", "mensaje", "La actualización no se cumplio, verifique los datos");
                }
                //listar Salas
                Salas dsala = new Salas();
                dsala.setId_piso(0);
                List listarSalas = this.mi.getListarSalasLibres(dsala);	    //List listarSalas = this.mi.getListarSalasLibres();
                modelo.put("listarSalas", listarSalas);

                return new ModelAndView("administrarsalas/ListarSalas", modelo);

            } else {
                return new ModelAndView("Aviso", "mensaje", "Ya existe un id_sala igual");
            }
        }

        if ("Modificar".equals(accion) && "Aceptar".equals(accion1)) {
            if (("".equals(id_sala)) || ("".equals(sala))) {
                return new ModelAndView("Aviso", "mensaje", "Faltan Introducir Datos");
            }
            Salas categoria_m = new Salas();
            categoria_m.setId_sala(Integer.parseInt(request.getParameter("id_sala")));
            categoria_m.setSala(request.getParameter("sala"));
            categoria_m.setUlt_usuario(cliente.getId_usuario());

            if ("A".equals(request.getParameter("id_estado"))) {
                categoria_m.setId_estado("A");
            } else {
                categoria_m.setId_estado("B");
            }

            try {
                this.mi.setModificarSala(categoria_m);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "La actualización no se cumplio, verifique los datos");
            }

            //listar categorias
            List listarSalas = this.mi.getListarSalas();
            modelo.put("listarSalas", listarSalas);

            return new ModelAndView("administrarsalas/ListarSalas", modelo);

        }

        if ("Eliminar".equals(accion) && "Aceptar".equals(accion1)) {
            if ("".equals(request.getParameter("id_sala"))) {
                return new ModelAndView("Aviso", "mensaje", "Faltan datos");
            }
            Salas elimina = new Salas();
            elimina.setId_sala(Integer.parseInt(request.getParameter("id_sala")));

            try {
                this.mi.setEliminarSala(elimina);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "El registro a eliminar tiene dependencias");
            }

            //listar Salas
            List listarSalas = this.mi.getListarSalas();
            modelo.put("listarSalas", listarSalas);

            return new ModelAndView("administrarsalas/ListarSalas", modelo);
        }
        return new ModelAndView("administrarsalas/ListarSalas", modelo);
    }
}
