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
public class ListarCamasInternadosControlador {

    private final MiFacade mi;

    public ListarCamasInternadosControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/ListarCamasInternados.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String id_sala = request.getParameter("id_sala");
        String id_piso = request.getParameter("id_piso");
        String estado = request.getParameter("estado");
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
        modelo.put("estado", estado);

        Historiales dato = new Historiales();
        dato.setId_persona(cliente.getId_persona());
        dato.setId_consultorio(persona.getId_consultorio());
        dato.setId_estado("C");
        List listarPaises = this.mi.getListarReservaciones(dato);
        modelo.put("milista", listarPaises);
        //Salas piso=new Salas();
        Salas dsala = new Salas();
        dsala.setCod_esta(cliente.getCod_esta());
        dsala.setId_piso(0);
        List listarSalas = this.mi.getListarSalasLibres(dsala);
        modelo.put("listarSalas", listarSalas);
        dsala.setCod_esta(cliente.getCod_esta());
        List listarPisos = this.mi.getListarPisos(dsala);
        modelo.put("listarPisos", listarPisos);
        modelo.put("id_piso", "0");

        Camas listcama = new Camas();
        listcama.setCod_esta(cliente.getCod_esta());
        listcama.setId_estado("V");  /////getListarCamasEstado
        List listarCamasSala = this.mi.getListarCamasEstado(listcama);
        modelo.put("listarCamasSala", listarCamasSala);

        if ("1".equals(estado)) {
            dato.setCod_esta(cliente.getCod_esta());
            dato.setSuma1(2);
            List listarPacInter = this.mi.getInternados(dato);
            modelo.put("listarPacInter", listarPacInter);
            if (id_piso != null) {
                dato.setAccion("S");   ////getInternadosSala
                List listarPacInter2 = this.mi.getInternadosSala(dato);
                modelo.put("listarPacInter", listarPacInter2);
                if (cliente.getCod_esta() == 200010l) {
                    dato.setAccion("P");   ////getInternadosPisoCaja
                    List listarPacInter3 = this.mi.getInternadosPisoCaja(dato);
                    modelo.put("listarPacInter", listarPacInter3);
                }
                dato.setId_sexo(Integer.parseInt(id_piso));
                modelo.put("id_piso", id_piso);
                dsala.setId_piso(Integer.parseInt(id_piso));
                List listarSalas2 = this.mi.getListarSalasLibres(dsala);
                modelo.put("listarSalas", listarSalas2);
                if (id_sala != null && !"0".equals(id_sala)) {
                    dato.setAccion("S");   ////getInternadosSala
                    dato.setId_cargo(Integer.parseInt(id_sala));
                    modelo.put("id_sala", id_sala);
                    List listarPacInter3 = this.mi.getInternadosSala(dato);
                    modelo.put("listarPacInter", listarPacInter3);
                }
            }

            if (listarPacInter.size() > 0) {
                modelo.put("listaocupada", "SI");
            }
            modelo.put("id_piso", id_piso);
            modelo.put("id_sala", id_sala);
            modelo.put("id_estado", estado);
        }

        if ("0".equals(estado)) {
            if (id_piso != null) {
                listcama.setId_piso0(Integer.parseInt(id_piso));
                listcama.setId_piso(Integer.parseInt(id_piso));
                dsala.setId_piso(Integer.parseInt(id_piso));

            } else {
                listcama.setId_piso0(0);
                listcama.setId_piso(5000);
            }
            if (id_sala != null && !"0".equals(id_sala)) {
                listcama.setId_sala0(Integer.parseInt(id_sala));
                listcama.setId_sala(Integer.parseInt(id_sala));
            } else {
                listcama.setId_sala0(0);
                listcama.setId_sala(5000);
            }

            List listarSalas2 = this.mi.getListarSalasLibres(dsala);
            modelo.put("listarSalas", listarSalas2);

            listcama.setId_estado("V");   /////getListarCamasEstado
            List listarCamasSala2 = this.mi.getListarCamasEstado(listcama);
            modelo.put("listarCamasSala", listarCamasSala2);
            modelo.put("id_piso", id_piso);
            modelo.put("id_sala", id_sala);
            modelo.put("id_estado", estado);
        }

        List listarSeguros = this.mi.getListarSeguros("A");
        modelo.put("listaPacAfi", listarSeguros);

        modelo.put("id_persona", Integer.toString(cliente.getId_persona()));
        modelo.put("id_consultorio", Integer.toString(persona.getId_consultorio()));

        return new ModelAndView("administrarhistoriales/ListarCamas", modelo);

    }
}
