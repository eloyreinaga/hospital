package org.ayaic.web.administrarecografia;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Consultorios;
import org.ayaic.domain.Historiales;
import org.ayaic.domain.Personas;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ListarReservasEcoControlador {

    private final MiFacade mi;

    public ListarReservasEcoControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/ListarReservasEco.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        Personas persona = this.mi.getBuscarPersona(cliente.getId_persona()); // saca un registro a ser modificado

        Consultorios datosconsul = this.mi.getDatosConsultorio(persona.getId_consultorio()); // saca un registro a ser modificado
        modelo.put("tipo_medico", Integer.toString(datosconsul.getId_cargo()));

        Historiales dato = new Historiales();
        dato.setId_persona(cliente.getId_persona());
        dato.setAccion("E");
        List listarAtendidos = this.mi.getHistorialAtendidos(dato);
        modelo.put("milistaAten", listarAtendidos);

        dato.setId_consultorio(persona.getId_consultorio());
        dato.setId_estado("C");
        List listarPaises = this.mi.getListarReservaciones(dato);
        modelo.put("milista", listarPaises);

        modelo.put("id_persona", Integer.toString(cliente.getId_persona()));
        modelo.put("id_consultorio", Integer.toString(persona.getId_consultorio()));

        return new ModelAndView("administrarecografia/ListarReservas", modelo);

    }
}
