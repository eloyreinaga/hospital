package org.ayaic.web.administrarcobranza;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Localidades;
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.Personas;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ListarCobroFarmaciaControlador {

    private final MiFacade mi;

    public ListarCobroFarmaciaControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/ListarCobroFarmacia.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        Personas persona = this.mi.getBuscarPersona(cliente.getId_persona()); // saca un registro a ser modificado

        Localidades local = new Localidades();
        local.setCod_esta(cliente.getCod_esta());
        List Estab1 = this.mi.getListarEsta(local);
        Localidades datoestab = (Localidades) Estab1.get(0);
        modelo.put("Factura", Integer.toString(datoestab.getId_pais()));
        local.setArea("U");  ///getListarEstaUsua el estab del usuario
        List Estab2 = this.mi.getListarEstaUsua(local);
        Localidades datoest = (Localidades) Estab2.get(0);
        modelo.put("Factura", Integer.toString(datoest.getId_pais()));
        modelo.put("cod_esta", Integer.toString(cliente.getCod_esta()));

        Pacientes paciente = new Pacientes();
        paciente.setId_estado("A");
        paciente.setId_rubro(1);
        paciente.setId_farmacia(cliente.getId_farmacia());
        paciente.setCod_esta(cliente.getCod_esta());
        List listarCobros = this.mi.getListarProformas(paciente);////getListarCobroRubroFar
        modelo.put("milista", listarCobros);

        modelo.put("id_persona", Integer.toString(cliente.getId_persona()));
        modelo.put("id_consultorio", Integer.toString(persona.getId_consultorio()));

        return new ModelAndView("administrarcobranza/ListarCobroFarmacia", modelo);

    }
}
