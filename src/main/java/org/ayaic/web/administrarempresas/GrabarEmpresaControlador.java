package org.ayaic.web.administrarempresas;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Empresas;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GrabarEmpresaControlador {

    private final MiFacade mi;

    public GrabarEmpresaControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/GrabarEmpresa.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion1 = request.getParameter("accion1");
        String accion = request.getParameter("accion");

        String empresa = request.getParameter("empresa");
        String nit = request.getParameter("nit");
        String direccion = request.getParameter("direccion");
        String telefonos = request.getParameter("telefonos");
        String responsable = request.getParameter("responsable");
        String id_empresa = request.getParameter("id_empresa");
        String codpatronal = request.getParameter("codpatronal");

        if ("Adicionar".equals(accion) && "Aceptar".equals(accion1)) {
            Empresas repetida = this.mi.getDatosEmpresa(Integer.parseInt(request.getParameter("id_empresa")));  // SI EL OBJETO EXISTE ENTONCES NO ES NULO
            if (repetida == null) {
                if (("".equals(id_empresa)) || ("".equals(empresa))) {
                    return new ModelAndView("Aviso", "mensaje", "Faltan Introducir Datos");
                }
                Empresas datoempresa = new Empresas();
//	   datoempresa.setId_empresa(Integer.parseInt(id_empresa)) ;
                datoempresa.setEmpresa(empresa);
                datoempresa.setNit(nit);
                datoempresa.setDireccion(direccion);
                datoempresa.setTelefonos(telefonos);
                datoempresa.setResponsable(responsable);
                datoempresa.setUlt_usuario(cliente.getId_usuario());

                try {
                    this.mi.setCrearEmpresa(datoempresa);
                } catch (Exception e) {
                    return new ModelAndView("Aviso", "mensaje", "La actualización no se cumplio, verifique los datos");
                }
                //listar Empresas
                List listarEmpresas = this.mi.getListarEmpresas("%");
                modelo.put("listarEmpresas", listarEmpresas);
                return new ModelAndView("administrarempresas/ListarEmpresas", modelo);

            } else {
                return new ModelAndView("Aviso", "mensaje", "Ya existe un id_empresa igual");
            }
        }

        if ("Modificar".equals(accion) && "Aceptar".equals(accion1)) {
            if (("".equals(id_empresa)) || ("".equals(empresa))) {
                return new ModelAndView("Aviso", "mensaje", "Faltan Introducir Datos");
            }
            Empresas categoria_m = new Empresas();
            categoria_m.setId_empresa(Integer.parseInt(request.getParameter("id_empresa")));
            categoria_m.setEmpresa(request.getParameter("empresa"));
            categoria_m.setNit(nit);
            categoria_m.setDireccion(direccion);
            categoria_m.setCod_patronal(Long.parseLong(codpatronal));
            categoria_m.setTelefonos(telefonos);
            categoria_m.setResponsable(responsable);
            categoria_m.setUlt_usuario(cliente.getId_usuario());

            if ("A".equals(request.getParameter("id_estado"))) {
                categoria_m.setId_estado("A");
            } else {
                categoria_m.setId_estado("B");
            }

            //try{
            this.mi.setModificarEmpresa(categoria_m);
            //}catch (Exception e){ 
            //  return new ModelAndView("Aviso","mensaje", "La actualización no se cumplio, verifique los datos");
            //}    

            //listar categorias
            List listarEmpresas = this.mi.getListarEmpresas("%");
            modelo.put("listarEmpresas", listarEmpresas);
            return new ModelAndView("administrarempresas/ListarEmpresas", modelo);

        }

        if ("Eliminar".equals(accion) && "Aceptar".equals(accion1)) {
            if ("".equals(request.getParameter("id_empresa"))) {
                return new ModelAndView("Aviso", "mensaje", "Faltan datos");
            }
            Empresas elimina = new Empresas();
            elimina.setId_empresa(Integer.parseInt(request.getParameter("id_empresa")));

            try {
                this.mi.setEliminarEmpresa(elimina);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "El registro a eliminar tiene dependencias");
            }

            //listar Empresas
            List listarEmpresas = this.mi.getListarEmpresas("%");
            modelo.put("listarEmpresas", listarEmpresas);

            return new ModelAndView("administrarempresas/ListarEmpresas", modelo);
        }
        return new ModelAndView("administrarempresas/ListarEmpresas", modelo);
    }
}
