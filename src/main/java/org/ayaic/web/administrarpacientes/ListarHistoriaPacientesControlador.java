package org.ayaic.web.administrarpacientes;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Historiales;
import org.ayaic.domain.Personas;
import org.ayaic.domain.Recetas;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ListarHistoriaPacientesControlador {

    private final MiFacade mi;

    public ListarHistoriaPacientesControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/ListarHistoPacientes.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String boton = request.getParameter("boton");
        String accion = request.getParameter("accion");

        Personas persona = new Personas();
        persona.setDip("S");
        persona.setCod_esta(cliente.getCod_esta());
        List buscarEmpleado = this.mi.getListarPersonasSoloAten(persona);
        modelo.put("listaPersonas", buscarEmpleado);

        if ("Imprimir HCL".equals(accion)) {
            String id_persona = request.getParameter("id_persona");
            String sFecha_ini = request.getParameter("valor_1");
            String sFecha_fin = request.getParameter("valor_2");

            if (("".equals(sFecha_ini)) || ("".equals(sFecha_fin))) {
                return new ModelAndView("administrarpacientes/ListarAfiliadosSumi", modelo);
            } else {
                String[] sFechaini = sFecha_ini.split("-");
                int iAnio1 = Integer.parseInt(sFechaini[0]) - 1900;
                int iMes1 = Integer.parseInt(sFechaini[1]) - 1;
                int iDia1 = Integer.parseInt(sFechaini[2]);

                String[] sFechafin = sFecha_fin.split("-");
                int iAnio2 = Integer.parseInt(sFechafin[0]) - 1900;
                int iMes2 = Integer.parseInt(sFechafin[1]) - 1;
                int iDia2 = Integer.parseInt(sFechafin[2]);

                Date dFechaini1 = new Date(iAnio1, iMes1, iDia1);
                Date dFechafin1 = new Date(iAnio2, iMes2, iDia2);

                modelo.put("dato", cliente);

                Historiales dato = new Historiales();
                dato.setAccion("M");
                dato.setFecha_ini(dFechaini1);
                dato.setFecha_fin(dFechafin1);
                dato.setId_persona(Integer.parseInt(id_persona));
                dato.setCod_esta(cliente.getCod_esta());
                List listas = this.mi.getListarHistoria(dato);
                modelo.put("milistas", listas);
                Recetas datort = new Recetas();
                datort.setId_estado("M");
                datort.setFecha_ini(dFechaini1);
                datort.setFecha_fin(dFechafin1);
                datort.setId_persona(Integer.parseInt(id_persona));
                List listarRecetaTotal = this.mi.getListarRecetasTotalMed(datort);
                modelo.put("listarRecetaTotal", listarRecetaTotal);

                return new ModelAndView(new ListarHCLMedPDF(), modelo);

            }
        }

        if ("imprimir".equals(accion)) {
            String id_historial = request.getParameter("id_historial");
            String hcl = request.getParameter("hcl");
            String nombres = request.getParameter("nombres");
            String nombre = request.getParameter("nombre");

            Historiales datohi = new Historiales();
            datohi.setId_historial(Integer.parseInt(id_historial));
            datohi.setCod_esta(cliente.getCod_esta());
            Historiales datosHistorial = this.mi.getDatosHistorial(datohi);
            // Cambiamos el estado a historia impresa
            datosHistorial.setId_historial(Integer.parseInt(id_historial));
            datosHistorial.setRango(1);
            this.mi.setModificarRangoHistoria(datosHistorial);
            modelo.put("lista", datosHistorial);
            modelo.put("hcl", hcl);
            modelo.put("nombres", nombres);
            modelo.put("nombre", nombre);
            Recetas datore = new Recetas();
            datore.setId_historial(Integer.parseInt(id_historial));
            datore.setId_farmacia(cliente.getId_farmacia());
            datore.setCod_esta(cliente.getCod_esta());
            datore.setId_estado("%");
            List listarReceta = this.mi.getListarRecetas(datore);
            modelo.put("listarReceta", listarReceta);
            return new ModelAndView("administrarpacientes/HistoriaPaciente", modelo);
        }

        List listaHistoPacientes = this.mi.getListaHistoriaImp(0);
        modelo.put("listaHistoPacientes", listaHistoPacientes);

        List listaHistoPacientesImp = this.mi.getListaHistoriaImp(1);
        modelo.put("listaHistoPacientesImp", listaHistoPacientesImp);

        return new ModelAndView("administrarpacientes/ListarHistoriaPacientes", modelo);

    }
}
