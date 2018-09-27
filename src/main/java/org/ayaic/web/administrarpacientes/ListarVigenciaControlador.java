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
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ListarVigenciaControlador {

    private final MiFacade mi;

    public ListarVigenciaControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/ListarVigencia.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String _nombres = cliente.getNombres();
        String accion1 = request.getParameter("accion1");
        String accion = request.getParameter("accion");
        String id_consultorio = request.getParameter("id_consultorio");
        String id_persona = request.getParameter("id_persona");
        String sw = request.getParameter("sw");

        //lista de consultorios
        Consultorios datosconsultorio = this.mi.getDatosConsultorio(cliente.getId_consultorio()); // saca un registro a ser modificado
        Consultorios a = new Consultorios();
        a.setCod_esta(cliente.getCod_esta());
        List listarCargos = this.mi.getListarConsultorios(a);

        modelo.put("listarCargos", listarCargos);
        modelo.put("id_cargo", Integer.toString(datosconsultorio.getId_cargo()));

        Localidades local = new Localidades();
        List Estab1 = this.mi.getListarEsta(local);
        Localidades datoestab = (Localidades) Estab1.get(0);
        modelo.put("establecimento", datoestab.getEstablecimiento());
        modelo.put("dato", cliente);

        if ("DarFicha".equals(accion)) {
            String id_reservacion = request.getParameter("id_reservacion");

            Historiales dato = new Historiales();
            dato.setAccion("F"); ////getListarVigenciaFicha
            dato.setCod_esta(cliente.getCod_esta());
            dato.setId_reservacion(Integer.parseInt(id_reservacion));
            List listarFicha = this.mi.getListarVigenciaFicha(dato);
            if (listarFicha.isEmpty()) {
                return new ModelAndView("Aviso", "mensaje", "Antes de imrpimir, debe asignar medico");
            }
            modelo.put("listaficha", listarFicha);
            return new ModelAndView(new AtencionFichasPDF(), modelo);
        }

        if ("VigenciaH".equals(accion) || "VigenciaD".equals(accion)) {
            String id_reservacion = request.getParameter("id_reservacion");
            String tipoconsult = request.getParameter("tipoconsult");
            String id_riesgo = request.getParameter("id_riesgo");

            if ("".equals(id_riesgo) || id_riesgo == null) {
                id_riesgo = "0";
            }
            if ("".equals(tipoconsult) || tipoconsult == null) {
                tipoconsult = "0";
            }

            Historiales dato = new Historiales();
            dato.setId_cargo(Integer.parseInt(sw));
            dato.setCod_esta(cliente.getCod_esta());
            dato.setId_riesgo(Integer.parseInt(id_riesgo));
            dato.setTipoconsult(Integer.parseInt(tipoconsult));
            dato.setId_reservacion(Integer.parseInt(id_reservacion));
            this.mi.setModificaVigencia(dato);
        }
        //lista de pacientes en el consultorio

        Historiales dato = new Historiales();

        dato.setAccion("V");
        dato.setId_estado("%");
        dato.setCod_esta(cliente.getCod_esta());
        if ("".equals(sw) || sw == null) {
            dato.setId_historia(0);
            dato.setId_historial(2);
        } else {
            dato.setId_historia(Integer.parseInt(sw));  ////vigencia =0 NO habilitados vigencia
            dato.setId_historial(Integer.parseInt(sw));  ////vigencia =1 habilitados emergencia =2 consulta externa
        }

        List listarPaises = this.mi.getListarVigencia(dato);
        modelo.put("milista", listarPaises);

        List listarSeguros = this.mi.getListarSeguros("A");
        modelo.put("listaPacAfi", listarSeguros);

        return new ModelAndView("administrarpacientes/ListarVigencias", modelo);
    }
}
