package org.ayaic.web.administrarenfermeria;

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
public class ListarSignosReservaControlador {

    private final MiFacade mi;

    public ListarSignosReservaControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/ListarSignosReserva.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String id_consultorio = request.getParameter("id_consultorio");
        String id_persona = request.getParameter("id_persona");

        Localidades local = new Localidades();
        List Estab1 = this.mi.getListarEsta(local);
        Localidades datoestab = (Localidades) Estab1.get(0);

        Consultorios datosconsultorio = this.mi.getDatosConsultorio(cliente.getId_consultorio()); // saca un registro a ser modificado
        Consultorios a = new Consultorios();
        a.setCod_esta(cliente.getCod_esta());
        a.setSelec(1);
        List listarCargos = this.mi.getListarConsultorios(a);
        modelo.put("listarCargos", listarCargos);
        if (cliente.getId_almacen() == 1) {
            a.setId_estado("U");////getListarConsultoriosUrgen 08/01/2017
            a.setId_especialidad(1);////
            List listarCargos2 = this.mi.getListarConsultoriosUrgen(a);
            modelo.put("listarCargos", listarCargos2);
        }

        modelo.put("id_cargo", Integer.toString(datosconsultorio.getId_cargo()));
        modelo.put("dato", cliente);

        Historiales datoh = new Historiales();
        datoh.setId_estado("C");
        datoh.setAccion("A");
        datoh.setCod_esta(cliente.getCod_esta());
        datoh.setId_consultorio(0);
        datoh.setId_localidad(999);
        datoh.setId_persona(0);
        datoh.setId_departamento(99999);
        datoh.setAccion("C");  ////getListarCobroReservaSignos
        List listarPaises = this.mi.getListarCobroReservaSignos(datoh);
        modelo.put("milistasig", listarPaises);
        if (id_consultorio != null) {
            Personas persona = new Personas();
            persona.setCod_esta(cliente.getCod_esta());
            persona.setId_consultorio(Integer.parseInt(id_consultorio));
            datoh.setId_consultorio(Integer.parseInt(id_consultorio));
            datoh.setId_localidad(Integer.parseInt(id_consultorio));
            List buscarEmpleado = this.mi.getDatosPersonaConsul(persona);
            modelo.put("listaPersonas", buscarEmpleado);
            if (datoestab.getCod_esta() != 200010) {/////11-11-2015 sector publico
                List listarRes = this.mi.getListarReservacionesConsulPublico(datoh);
                modelo.put("milistasig", listarRes);
            }

            if (id_persona != null && !"0".equals(id_persona)) {
                datoh.setId_persona(Integer.parseInt(id_persona));
                datoh.setId_departamento(Integer.parseInt(id_persona));
                List listarRes2 = this.mi.getListarReservacionesConsulMedicoPublico(datoh);
                modelo.put("milistasig", listarRes2);
            }
        }

        if (cliente.getInst_id() == 10) { ////para CNS
            datoh.setAccion("S");  ////getListarReservaSignosCNS_SC
            List listarSign = this.mi.getListarReservaSignosCNS_SC(datoh);
            modelo.put("milistasig", listarSign);
            if (cliente.getId_almacen() == 1) {
                datoh.setAccion("U");  ////getListarReservaSignosCNS_SCUrg
                List listarSig = this.mi.getListarReservaSignosCNS_SCUrg(datoh);
                modelo.put("milistasig", listarSig);
            }
        }
        modelo.put("id_persona", id_persona);
        modelo.put("id_consultorio", id_consultorio);

        return new ModelAndView("administrarenfermeria/ListarSignosReserva", modelo);

    }
}
