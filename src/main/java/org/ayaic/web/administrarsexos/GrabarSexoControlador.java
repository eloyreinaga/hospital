package org.ayaic.web.administrarsexos;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Sexos;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GrabarSexoControlador {

    private final MiFacade mi;

    public GrabarSexoControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/GrabarSexo.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion1 = request.getParameter("accion1");
        String accion = request.getParameter("accion");
        String sexo = request.getParameter("sexo");
        String id_sexo = request.getParameter("id_sexo");

        if ("Adicionar".equals(accion) && "Aceptar".equals(accion1)) {
            Sexos repetida = this.mi.getDatosSexo(Integer.parseInt(request.getParameter("id_sexo")));  // SI EL OBJETO EXISTE ENTONCES NO ES NULO
            if (repetida == null) {
                if (("".equals(id_sexo)) || ("".equals(sexo))) {
                    return new ModelAndView("Aviso", "mensaje", "Faltan Introducir Datos");
                }
                Sexos datosexo = new Sexos();
                datosexo.setId_sexo(Integer.parseInt(id_sexo));
                datosexo.setSexo(sexo);
                datosexo.setUlt_usuario(cliente.getId_usuario());

                try {
                    this.mi.setCrearSexo(datosexo);
                } catch (Exception e) {
                    return new ModelAndView("Aviso", "mensaje", "La actualización no se cumplio, verifique los datos");
                }
                //listar Sexos
                List listarSexos = this.mi.getListarSexos();
                modelo.put("listarSexos", listarSexos);
                return new ModelAndView("administrarsexos/ListarSexos", modelo);

            } else {
                return new ModelAndView("Aviso", "mensaje", "Ya existe un id_sexo igual");
            }
        }

        if ("Modificar".equals(accion) && "Aceptar".equals(accion1)) {
            if (("".equals(id_sexo)) || ("".equals(sexo))) {
                return new ModelAndView("Aviso", "mensaje", "Faltan Introducir Datos");
            }
            Sexos categoria_m = new Sexos();
            categoria_m.setId_sexo(Integer.parseInt(request.getParameter("id_sexo")));
            categoria_m.setSexo(request.getParameter("sexo"));
            categoria_m.setUlt_usuario(cliente.getId_usuario());

            if ("A".equals(request.getParameter("id_estado"))) {
                categoria_m.setId_estado("A");
            } else {
                categoria_m.setId_estado("B");
            }

            try {
                this.mi.setModificarSexo(categoria_m);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "La actualización no se cumplio, verifique los datos");
            }

            //listar categorias
            List listarSexos = this.mi.getListarSexos();
            modelo.put("listarSexos", listarSexos);
            return new ModelAndView("administrarsexos/ListarSexos", modelo);

        }

        if ("Eliminar".equals(accion) && "Aceptar".equals(accion1)) {
            if ("".equals(request.getParameter("id_sexo"))) {
                return new ModelAndView("Aviso", "mensaje", "Faltan datos");
            }
            Sexos elimina = new Sexos();
            elimina.setId_sexo(Integer.parseInt(request.getParameter("id_sexo")));

            try {
                this.mi.setEliminarSexo(elimina);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "El registro a eliminar tiene dependencias");
            }

            //listar categorias
            List listarSexos = this.mi.getListarSexos();
            modelo.put("listarSexos", listarSexos);

            return new ModelAndView("administrarsexos/ListarSexos", modelo);
        }
        return new ModelAndView("administrarsexos/ListarSexos", modelo);
    }
}
