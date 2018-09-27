package org.ayaic.web.administrarhistoriales;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Camas;
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
public class ListarInternosAltaControlador {

    private final MiFacade mi;

    public ListarInternosAltaControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/ListarAltaInternados.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String id_sala = request.getParameter("id_sala");
        String id_piso = request.getParameter("id_piso");
        String id_piso2 = request.getParameter("id_piso");
        String accion = request.getParameter("boton");
        String accion1 = request.getParameter("accion1");

        Localidades local = new Localidades();
        List Estab1 = this.mi.getListarEsta(local);
        Localidades datoestab = (Localidades) Estab1.get(0);
        modelo.put("cod_esta", Integer.toString(datoestab.getCod_esta()));

        Personas persona = this.mi.getBuscarPersona(cliente.getId_persona()); // saca un registro a ser modificado

        Consultorios datosconsul = this.mi.getDatosConsultorio(persona.getId_consultorio()); // saca un registro a ser modificado
        modelo.put("tipo_medico", Integer.toString(datosconsul.getId_cargo()));
        modelo.put("id_persona", Integer.toString(cliente.getId_persona()));
        modelo.put("id_especialidad", Integer.toString(cliente.getId_especialidad()));

        Historiales dato = new Historiales();
        dato.setId_persona(cliente.getId_persona());
        dato.setId_consultorio(persona.getId_consultorio());
        dato.setId_estado("C");

        if ("BuscarP".equals(accion1)) {
            String nombres = request.getParameter("nombre");
            nombres = nombres.trim();
            nombres = nombres.replaceAll(" +", " ");///quita espacios en blanco entre cadenas
            nombres = nombres.replaceAll("\\s", ":*&");///:*&
            nombres = nombres.replaceAll("ñ", "n");
            nombres = nombres.replaceAll("Ñ", "N");
            if (nombres.length() > 0) {
                nombres = nombres + ":*";
            }
            dato.setCadena(nombres);
            dato.setAccion("B");                ////getInternadosSalaCajaAltaNombre
        }

        dato.setCod_esta(cliente.getCod_esta());
        dato.setSuma1(3);
        dato.setSuma2(3);   ////datos de alta
        List listarPacInter = this.mi.getInternadosSalaCajaAltaNombre(dato);
        modelo.put("listarPacInter", listarPacInter);

        Camas listcama = new Camas();
        List listarCamasSala = this.mi.getListarCamasSala(listcama);
        modelo.put("listarCamasSala", listarCamasSala);

        modelo.put("id_persona", Integer.toString(cliente.getId_persona()));
        modelo.put("id_consultorio", Integer.toString(persona.getId_consultorio()));

        return new ModelAndView("administrarhistoriales/ListarAltaInternados", modelo);

    }
}
