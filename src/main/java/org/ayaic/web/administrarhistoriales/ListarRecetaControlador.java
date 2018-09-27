package org.ayaic.web.administrarhistoriales;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.Prestaciones;
import org.ayaic.domain.Recetas;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ListarRecetaControlador {

    private final MiFacade mi;

    public ListarRecetaControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/ListarReceta.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");

        String id_historial = request.getParameter("id_historial");
        String id_paciente = request.getParameter("id_paciente");
        String id_reservacion = request.getParameter("id_reservacion");
        String id_consultorio = request.getParameter("id_consultorio");
        String id_persona = request.getParameter("id_persona");
        String boton = request.getParameter("boton");
        String expedido = request.getParameter("expedido");
        String tipo_medico = request.getParameter("tipo_medico");

        String medica = "si";
        Pacientes buscarPaciente = this.mi.getDatosPaciente(Integer.parseInt(id_paciente)); // saca un registro a ser modificado
        modelo.put("datos", buscarPaciente);

        if ("RecetaT".equals(accion)) {
            Recetas dato = new Recetas();
            dato.setId_paciente(Integer.parseInt(id_paciente));
            List listarRecetaTotal = this.mi.getListarRecetasTotal(dato);
            modelo.put("listarRecetaTotal", listarRecetaTotal);

            dato.setId_paciente(Integer.parseInt(id_paciente));
            dato.setId_estado("T");
            List listarKardex = this.mi.getListarKardexTotal(dato);
            modelo.put("listarKardex", listarKardex);

            modelo.put("id_paciente", id_paciente);
            modelo.put("id_reservacion", id_reservacion);
            modelo.put("id_consultorio", id_consultorio);
            modelo.put("id_persona", id_persona);
            modelo.put("expedido", expedido);
            modelo.put("tipo_medico", tipo_medico);
            modelo.put("medica", medica);
            return new ModelAndView("administrarhistoriales/ListarRecetaTotal", modelo);
        }

        if ("ModDosifica".equals(accion)) {
            modelo.put("Modosif", "SI");
        }

        if ("GuardarModDosifica".equals(accion)) {
            String id_medicamento = request.getParameter("id_medicamento");
            String id_detalle = request.getParameter("id_detalle");
            String tdosif = request.getParameter("tdosif");

            Recetas midato = new Recetas();
            midato.setId_medicamento(Integer.parseInt(id_medicamento));
            midato.setId_receta(Integer.parseInt(id_detalle));
            midato.setDosifica(Integer.parseInt(tdosif));
            midato.setId_paciente(cliente.getId_persona());
            //midato.setPaquete(ip.getHostAddress()); ///20/07/2014
            //midato.setPrestacion(ip.getHostName()); ///20/07/2014
            midato.setCod_esta(cliente.getCod_esta());
            this.mi.setModificarRecetaDosifi(midato);

            modelo.put("Modosif", "NO");
        }

        Prestaciones prestac = new Prestaciones();
        prestac.setId_historial(Integer.parseInt(id_historial));
        prestac.setId_persona(cliente.getId_persona());
        prestac.setCod_esta(cliente.getCod_esta());
        List listarRecetas = this.mi.getListarSumiRecetasI(prestac);
        modelo.put("listarRecetas", listarRecetas);

        Recetas datore = new Recetas();
        datore.setId_historial(Integer.parseInt(id_historial));
        datore.setId_estado("H");
        datore.setCod_esta(cliente.getCod_esta());
        datore.setId_farmacia(cliente.getId_farmacia());
        List listarRecetaSumi = this.mi.getListarRecetasHistorial(datore);
        modelo.put("listarRecetaSumi", listarRecetaSumi);

        modelo.put("id_paciente", id_paciente);
        modelo.put("id_reservacion", id_reservacion);
        modelo.put("id_consultorio", id_consultorio);
        modelo.put("id_persona", id_persona);
        modelo.put("id_historial", id_historial);
        modelo.put("expedido", expedido);
        modelo.put("tipo_medico", tipo_medico);
        modelo.put("medica", medica);
        return new ModelAndView("administrarhistoriales/ListarReceta", modelo);

    }
}
