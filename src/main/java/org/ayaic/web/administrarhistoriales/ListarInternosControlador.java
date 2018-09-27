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
import org.ayaic.domain.Salas;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ListarInternosControlador {

    private final MiFacade mi;

    public ListarInternosControlador (MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/ListarInternados.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String nombres = request.getParameter("nombre");
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
        dato.setCod_esta(cliente.getCod_esta());
        dato.setId_persona(cliente.getId_persona());
        dato.setId_consultorio(persona.getId_consultorio());
        dato.setId_estado("C");

        Salas dsala = new Salas();
        dsala.setId_piso(0);
        List listarSalas = this.mi.getListarSalasLibres(dsala);
        modelo.put("listarSalas", listarSalas);
        dsala.setCod_esta(cliente.getCod_esta());
        dsala.setTipo("T");   /////getListarPisosTotal

        List listarPisos = this.mi.getListarPisosTotal(dsala);
        modelo.put("listarPisos", listarPisos);
        dato.setId_sexo(cliente.getId_piso());
        modelo.put("id_piso", "0");

        modelo.put("id_piso", Integer.toString(cliente.getId_piso()));
        id_piso = Integer.toString(cliente.getId_piso());

        if (id_piso2 == null) {
            id_piso2 = Integer.toString(cliente.getId_piso());////a aumnete recien 30/12/2015 no daba buscar pisos
        }
        dato.setSuma1(2);
        dato.setSuma2(2);
        List listarPacInter = this.mi.getInternados(dato);
        modelo.put("listarPacInter", listarPacInter);
        if (id_piso != null || Integer.parseInt(id_piso2) != cliente.getId_piso()) {
            dato.setAccion("P");   ////getInternadosPisoCaja
            dato.setId_sexo(Integer.parseInt(id_piso2));
            dato.setId_piso(Integer.parseInt(id_piso2));
            modelo.put("id_piso", id_piso2);
            dsala.setId_piso(Integer.parseInt(id_piso2));
            dsala.setTipo("T"); ////getListarSalaPisoTotal
            List listarSalas2 = this.mi.getListarSalaPisoTotal(dsala);
            modelo.put("listarSalas", listarSalas2);
            List listarPacInter2 = this.mi.getInternadosPisoCaja(dato);
            modelo.put("listarPacInter", listarPacInter2);
            if (id_sala != null && !"0".equals(id_sala)) {
                dato.setAccion("S");   ////getInternadosSala
                dato.setId_sala(Integer.parseInt(id_sala));
                modelo.put("id_sala", id_sala);
                List listarPacInter3 = this.mi.getInternadosSala(dato);
                modelo.put("listarPacInter", listarPacInter3);
            }
        }

        if ("BuscarP".equals(accion1)) {

            nombres = nombres.trim();
            nombres = nombres.replaceAll(" +", " ");///quita espacios en blanco entre cadenas
            nombres = nombres.replaceAll("\\s", ":*&");///:*&
            nombres = nombres.replaceAll("ñ", "n");
            nombres = nombres.replaceAll("Ñ", "N");
            if (nombres.length() > 0) {
                nombres = nombres + ":*";
            }
            dato.setCadena(nombres);
            dato.setCod_esta(cliente.getCod_esta());
            List listarPacInter2 = this.mi.getInternadosSalaCajaNombre(dato);
            modelo.put("listarPacInter", listarPacInter2);
        }

        Camas listcama = new Camas();
        List listarCamasSala = this.mi.getListarCamasSala(listcama);
        modelo.put("listarCamasSala", listarCamasSala);

        modelo.put("id_persona", Integer.toString(cliente.getId_persona()));
        modelo.put("id_consultorio", Integer.toString(persona.getId_consultorio()));

        return new ModelAndView("administrarhistoriales/ListarInternados", modelo);

    }
}
