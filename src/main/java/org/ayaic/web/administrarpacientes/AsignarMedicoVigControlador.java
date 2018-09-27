package org.ayaic.web.administrarpacientes;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Consultorios;
import org.ayaic.domain.Historiales;
import org.ayaic.domain.Localidades;
import org.ayaic.domain.Personas;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AsignarMedicoVigControlador {

    private final MiFacade mi;

    public AsignarMedicoVigControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/AsignarMedVigencia.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion1 = request.getParameter("accion1");
        String accion = request.getParameter("accion");
        String id_consultorio = request.getParameter("id_consultorio");
        String id_persona = request.getParameter("id_persona");

        //lista de consultorios
        Consultorios datosconsultorio = this.mi.getDatosConsultorio(cliente.getId_consultorio()); // saca un registro a ser modificado
        Consultorios a = new Consultorios();
        a.setCod_esta(cliente.getCod_esta());
        a.setSelec(1);
        a.setId_especialidad(1);////
        a.setId_estado("U");  ////getListarConsultoriosUrgen  08/01/2017
        List listarCargos = this.mi.getListarConsultoriosUrgen(a);

        modelo.put("listarCargos", listarCargos);
        modelo.put("id_cargo", Integer.toString(datosconsultorio.getId_cargo()));

        Localidades local = new Localidades();
        List Estab1 = this.mi.getListarEsta(local);
        Localidades datoestab = (Localidades) Estab1.get(0);

        List listarSeguros = this.mi.getListarSeguros("A");
        modelo.put("listaPacAfi", listarSeguros);

        Historiales dato = new Historiales();
        dato.setId_estado("%");
        if (cliente.getId_rol() == 2 || cliente.getId_rol() == 26) {
            dato.setId_historia(2);  ////vigencia =1 habilitados consulta externa
            dato.setId_historial(2);  ////vigencia =1 habilitados consulta externa
        } else {
            dato.setId_historia(1);  ////vigencia =1 habilitados emergencias
            dato.setId_historial(1);  ////vigencia =1 habilitados emergencias
        }

        dato.setCod_esta(cliente.getCod_esta());
        dato.setAccion("H");   ///getListarVigenciaHab
        List listarVig = this.mi.getListarVigenciaHab(dato);
        modelo.put("milista", listarVig);

        if (request.getParameter("id_consultorio") != null) {

            dato.setId_consultorio(Integer.parseInt(id_consultorio));
            dato.setId_estado("%");
            List listarVig2 = this.mi.getListarVigencia(dato);
            modelo.put("milista", listarVig2);
            if ("0".equals(id_consultorio)) {
                dato.setAccion("C");  ////getListarVigenciaHabCambioConsul
                List listarVig21 = this.mi.getListarVigenciaHabCambioConsul(dato);
                modelo.put("milista", listarVig21);
            } else {
                dato.setAccion("M");   ////getListarVigenciaMedico
                List listarVig21 = this.mi.getListarVigenciaMedico(dato);
                modelo.put("milista", listarVig21);
                if ("0".equals(id_persona)) {
                    dato.setAccion("S");   ///getListarVigenciaConsul
                    List listarVig22 = this.mi.getListarVigenciaConsul(dato);
                    modelo.put("milista", listarVig22);
                } else {
                    dato.setId_persona(Integer.parseInt(id_persona));
                    List listarVig22 = this.mi.getListarVigencia(dato);
                    modelo.put("milista", listarVig22);
                }
            }

            Personas persona = new Personas();
            persona.setId_consultorio(Integer.parseInt(id_consultorio));
            persona.setCod_esta(cliente.getCod_esta());
            persona.setDip("U");   ////getDatosPersonaConsulUrgen
            List buscarEmpleado = this.mi.getDatosPersonaConsulUrgen(persona);
            modelo.put("listaPersonas", buscarEmpleado);
            modelo.put("estab", datoestab.getArea());
            modelo.put("id_consultorio", id_consultorio);

        }

        return new ModelAndView("administrarpacientes/AsignarMedVigencia", modelo);
    }
}
