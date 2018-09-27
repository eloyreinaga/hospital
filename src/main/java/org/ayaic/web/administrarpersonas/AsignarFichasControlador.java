package org.ayaic.web.administrarpersonas;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Personas;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AsignarFichasControlador {

    private final MiFacade mi;

    public AsignarFichasControlador(MiFacade mi) {
        this.mi = mi;
    }
    
    @RequestMapping("/AsignarFichas.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String id_persona = request.getParameter("id_persona");
        String id_consultorio = request.getParameter("id_consultorio");

        Personas persona = new Personas();
        persona.setCod_esta(cliente.getCod_esta());

        if ("adicion".equals(accion) || "eliminar".equals(accion) ) {
            String id_ficha = request.getParameter("id_ficha");
            
            persona.setId_persona(Integer.parseInt(id_persona));
            persona.setId_consultorio(Integer.parseInt(id_consultorio));
            persona.setNropac(5);
            persona.setCadena1("1");
            persona.setCadena2("2");
            if("eliminar".equals(accion) ){
                persona.setId_medico(Integer.parseInt(id_ficha));
                this.mi.setEliminarPersonaInter(persona);
            }else{
               this.mi.setCrearPersonaInter(persona); 
            }
        }
        
        List listarPersonas = this.mi.getListarPersonasEstab(persona);
        modelo.put("listarPersonas", listarPersonas);
        
        if ((id_persona == null) && ("Buscar".equals(accion)) ) {
            String nombre = request.getParameter("nombre");
            
            persona.setNombres("%"+nombre+"%");
            List listarPer2 = this.mi.getListarPersonasNombreLocal(persona);
            modelo.put("listarPersonas", listarPer2);
        }
        
        List listarPersonasInter = this.mi.getListarPersonasInter(persona);
        modelo.put("listarPersonasInter", listarPersonasInter);
        modelo.put("accion", accion);
        return new ModelAndView("administrarpersonas/AsignarFichas", modelo);
    }
}
