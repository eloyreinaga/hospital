package org.ayaic.web.administrarusuarios;

import java.util.HashMap;
import java.util.Map;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Menues;
import org.ayaic.domain.Roles;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ConfirmarRolUsuarioControlador {

    private final MiFacade mi;

    public ConfirmarRolUsuarioControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/ConfirmarRolUsuario.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String accion1 = request.getParameter("accion1");
        String id_usuario = request.getParameter("id_usuario");
        String id_rol = request.getParameter("id_rol");
        String id_estado = request.getParameter("id_estado");
        String diax = request.getParameter("dia");
        String mesx = request.getParameter("mes");
        String aniox = request.getParameter("anio");

        Menues pai = new Menues();
        if ("Adicionar".equals(accion)) {

            pai.setId_usuario(Integer.parseInt(request.getParameter("id_usuario")));

            String a = "/";
            String fecha_reg = diax + a + mesx + a + aniox;
            modelo.put("fec_expiracion", fecha_reg);

            //Busca rol
            try {
                Roles aux = new Roles();
                aux.setId_rol(Integer.parseInt(id_rol));

                Roles buscarRol = this.mi.getBuscarRol(aux);
                modelo.put("buscarRol", buscarRol);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "Faltan datos");
            }

            modelo.put("urol", pai);
            modelo.put("id_usuario", id_usuario);
            modelo.put("anio", aniox);
            modelo.put("mes", mesx);
            modelo.put("dia", diax);
            modelo.put("accion", accion);
            modelo.put("accion1", accion1);

        }

        if ("Modificar".equals(accion)) {

            pai.setId_usuario(Integer.parseInt(request.getParameter("id_usuario")));

            String a = "/";
            String fecha_reg = diax + a + mesx + a + aniox;
            modelo.put("fec_expiracion", fecha_reg);

            //Busca rol
            try {
                Roles aux = new Roles();
                aux.setId_rol(Integer.parseInt(id_rol));

                Roles buscarRol = this.mi.getBuscarRol(aux);
                modelo.put("buscarRol", buscarRol);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "Faltan datos");
            }

            modelo.put("urol", pai);
            modelo.put("id_usuario", id_usuario);
            modelo.put("anio", aniox);
            modelo.put("mes", mesx);
            modelo.put("dia", diax);
            modelo.put("id_estado", id_estado);
            modelo.put("sw", request.getParameter("sw"));
            modelo.put("accion", accion);
            modelo.put("accion1", accion1);
        }

        if ("Eliminar".equals(accion)) {
            Menues modifica = new Menues();
            modifica.setId_usuario(Integer.parseInt(id_usuario));
            modifica.setId_rol(Integer.parseInt(id_rol));
            Menues buscarRolUsuario = this.mi.getUsrRol(modifica);   // saca un registro
            String id_rol_e = Integer.toString(buscarRolUsuario.getId_rol());  //Saca id_rol
            modelo.put("urol", buscarRolUsuario);
            //Busca rol
            Roles aux = new Roles();
            aux.setId_rol(Integer.parseInt(id_rol_e));

            Roles buscarRol = this.mi.getBuscarRol(aux);

            modelo.put("buscarRol", buscarRol);

            Date fecha_reg = buscarRolUsuario.getFec_expiracion();
            int xanio = fecha_reg.getYear() + 1900;
            int xmes = fecha_reg.getMonth() + 1;
            int xdia = fecha_reg.getDate();

            String a = "/";
            String fecha_reg_aux = xdia + a + xmes + a + xanio;
            modelo.put("fec_expiracion", fecha_reg_aux);
            modelo.put("accion", accion);
            modelo.put("sw1", request.getParameter("sw1"));
            modelo.put("id_usuario", request.getParameter("id_usuario"));
        }

        return new ModelAndView("administrarusuarios/ConfirmarRolUsuario", modelo);

    }
}
