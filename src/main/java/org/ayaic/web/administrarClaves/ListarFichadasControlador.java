package org.ayaic.web.administrarClaves;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Localidades;
import org.ayaic.domain.Personas;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ListarFichadasControlador {

    private final MiFacade mi;

    public ListarFichadasControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/ListarFichadas.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        Personas persona = this.mi.getBuscarPersona(cliente.getId_persona());

        Localidades local = new Localidades();
        List Estab1 = this.mi.getListarEsta(local);
        Localidades datoestab = (Localidades) Estab1.get(0);
        modelo.put("Factura", Integer.toString(datoestab.getId_pais()));

        Personas personau = new Personas();
        personau.setId_persona(cliente.getId_persona());
        List listarFichas = this.mi.getListarFichadas(personau);
        modelo.put("listabio", listarFichas);

        modelo.put("id_persona", Integer.toString(cliente.getId_persona()));
        modelo.put("id_consultorio", Integer.toString(persona.getId_consultorio()));

        return new ModelAndView("administrarClaves/ListarFichadas", modelo);
    }
}
