package org.ayaic.web.administrardocumentos;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Documentos;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GrabarDocumentoControlador {

    private final MiFacade mi;

    public GrabarDocumentoControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/GrabarDocumento.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion1 = request.getParameter("accion1");
        String accion = request.getParameter("accion");
        String documento = request.getParameter("documento");
        String id_documento = request.getParameter("id_documento");

        if ("Adicionar".equals(accion) && "Aceptar".equals(accion1)) {
            Documentos repetida = this.mi.getDatosDocumento(Integer.parseInt(request.getParameter("id_documento")));  // SI EL OBJETO EXISTE ENTONCES NO ES NULO
            if (repetida == null) {
                if (("".equals(id_documento)) || ("".equals(documento))) {
                    return new ModelAndView("Aviso", "mensaje", "Faltan Introducir Datos");
                }
                Documentos datodocumento = new Documentos();
                datodocumento.setId_documento(Integer.parseInt(id_documento));
                datodocumento.setDocumento(documento);
                datodocumento.setUlt_usuario(cliente.getId_usuario());

                try {
                    this.mi.setCrearDocumento(datodocumento);
                } catch (Exception e) {
                    return new ModelAndView("Aviso", "mensaje", "La actualización no se cumplio, verifique los datos");
                }
                //listar Documentos
                List listarDocumentos = this.mi.getListarDocumentos();
                modelo.put("listarDocumentos", listarDocumentos);

                return new ModelAndView("administrardocumentos/ListarDocumentos", modelo);

            } else {
                return new ModelAndView("Aviso", "mensaje", "Ya existe un id_documento igual");
            }
        }

        if ("Modificar".equals(accion) && "Aceptar".equals(accion1)) {
            if (("".equals(id_documento)) || ("".equals(documento))) {
                return new ModelAndView("Aviso", "mensaje", "Faltan Introducir Datos");
            }
            Documentos categoria_m = new Documentos();
            categoria_m.setId_documento(Integer.parseInt(request.getParameter("id_documento")));
            categoria_m.setDocumento(request.getParameter("documento"));
            categoria_m.setUlt_usuario(cliente.getId_usuario());

            if ("A".equals(request.getParameter("id_estado"))) {
                categoria_m.setId_estado("A");
            } else {
                categoria_m.setId_estado("B");
            }

            try {
                this.mi.setModificarDocumento(categoria_m);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "La actualización no se cumplio, verifique los datos");
            }

            //listar categorias
            List listarDocumentos = this.mi.getListarDocumentos();
            modelo.put("listarDocumentos", listarDocumentos);

            return new ModelAndView("administrardocumentos/ListarDocumentos", modelo);

        }

        if ("Eliminar".equals(accion) && "Aceptar".equals(accion1)) {
            if ("".equals(request.getParameter("id_documento"))) {
                return new ModelAndView("Aviso", "mensaje", "Faltan datos");
            }
            Documentos elimina = new Documentos();
            elimina.setId_documento(Integer.parseInt(request.getParameter("id_documento")));

            try {
                this.mi.setEliminarDocumento(elimina);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "El registro a eliminar tiene dependencias");
            }

            //listar categorias
            List listarDocumentos = this.mi.getListarDocumentos();
            modelo.put("listarDocumentos", listarDocumentos);

            return new ModelAndView("administrardocumentos/ListarDocumentos", modelo);
        }
        return new ModelAndView("administrardocumentos/ListarDocumentos", modelo);
    }
}
