package org.ayaic.web.administrarusuarios;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Personas;
import org.ayaic.domain.Usuarios;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ConfirmarUsuarioControlador {

    private final MiFacade mi;

    public ConfirmarUsuarioControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/ConfirmarUsuario.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String accion1 = request.getParameter("accion1");

        String id_usuario = request.getParameter("id_usuario");
        String id_persona = request.getParameter("id_persona");
        String id_estado = request.getParameter("id_estado");
        String apodo = request.getParameter("apodo");
        String clave = request.getParameter("clave");
        String rep_clave = request.getParameter("rep_clave");
        String recordatorio = request.getParameter("recordatorio");

        Usuarios pai = new Usuarios();

        if ("Adicionar".equals(accion)) {

//      pai.setId_usuario(Integer.parseInt(id_usuario)) ;
            pai.setId_persona(Integer.parseInt(id_persona));
            pai.setApodo(apodo);
            pai.setClave(clave);
            pai.setRecordatorio(recordatorio);

            modelo.put("dato", pai);

            try {
                Personas buscarpersona = this.mi.getDatosPersona(Integer.parseInt(id_persona)); // saca un registro a ser modificado
                modelo.put("buscarpersona", buscarpersona);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "Faltan datos");
            }

            modelo.put("accion", accion);
            modelo.put("accion1", accion1);
            modelo.put("rep_clave", rep_clave);
        }

        if ("Modificar".equals(accion)) {

            pai.setId_usuario(Integer.parseInt(id_usuario));
            pai.setId_persona(Integer.parseInt(id_persona));
            pai.setId_estado(id_estado);
            pai.setApodo(apodo);
            pai.setClave(clave);
            pai.setRecordatorio(recordatorio);

            modelo.put("dato", pai);
            try {
                Personas buscarpersona = this.mi.getDatosPersona(Integer.parseInt(id_persona)); // saca un registro a ser modificado
                modelo.put("buscarpersona", buscarpersona);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "Faltan datos");
            }

            modelo.put("sw", request.getParameter("sw"));
            modelo.put("rep_clave", rep_clave);
            modelo.put("accion", accion);
            modelo.put("accion1", accion1);
        }

        if ("Eliminar".equals(accion)) {
            Usuarios buscarusuario = this.mi.getDatosUsuario(Integer.parseInt(id_usuario)); // saca un registro a ser modificado
            modelo.put("dato", buscarusuario);
            try {

                Personas buscarpersona = this.mi.getDatosPersona(buscarusuario.getId_persona()); // saca un registro a ser modificado
                modelo.put("buscarpersona", buscarpersona);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "Faltan datos");
            }

            modelo.put("accion", accion);
            modelo.put("sw1", request.getParameter("sw1"));
            modelo.put("id_usuario", id_usuario);
        }

        return new ModelAndView("administrarusuarios/ConfirmarUsuario", modelo);

    }
}
