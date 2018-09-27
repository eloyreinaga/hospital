package org.ayaic.web.administrarecografia;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Consultorios;
import org.ayaic.domain.Cuadernos;
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.Personas;
import org.ayaic.domain.Sexos;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LabPendienteEcoControlador {

    private final MiFacade mi;

    public LabPendienteEcoControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/LabPendienteEco.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        Personas persona = this.mi.getBuscarPersona(cliente.getId_persona()); // saca un registro a ser modificado
        Consultorios datosconsul = this.mi.getDatosConsultorio(persona.getId_consultorio()); // saca un registro a ser modificado

        String accion = request.getParameter("accion");
        String id_historial = request.getParameter("id_historial");
        String id_cuaderno = request.getParameter("id_cuaderno");
        String id_paciente = request.getParameter("id_paciente");
        String expedido = request.getParameter("expedido");
        String realizalab = "no";
        String tipo = "L";

        modelo.put("id_historial", id_historial);
        modelo.put("id_cuaderno", id_cuaderno);
        modelo.put("id_paciente", id_paciente);
        modelo.put("expedido", expedido);

        Cuadernos dato = new Cuadernos();
        dato.setId_cuaderno(Integer.parseInt(id_cuaderno));
        dato.setCod_esta(cliente.getCod_esta());

        if ("Aceptar".equals(accion)) {
            Cuadernos datoLab = this.mi.getDatosLaboratorioC(dato);
            modelo.put("datosLab", datoLab);
            String resultado = request.getParameter("resultado");
            datoLab.setResultado(resultado);
            datoLab.setId_cuaderno(Integer.parseInt(id_cuaderno));
            datoLab.setId_persona(cliente.getId_persona());
            datoLab.setEstado("B");
            datoLab.setCod_esta(cliente.getCod_esta());
            datoLab.setId_por(cliente.getId_persona());
            this.mi.setModificarLaboratorioC(datoLab);
        }

        if ("adicion".equals(accion)) {
            Cuadernos datoLab = this.mi.getDatosLaboratorioC(dato);
            modelo.put("datosLab", datoLab);
            realizalab = "si";
        }

        modelo.put("realizalab", realizalab);

        if (datosconsul.getId_cargo() == 11) {
            tipo = "E";
        }
        if (datosconsul.getId_cargo() == 12) {
            tipo = "R";
        }

        Pacientes buscarPaciente = this.mi.getDatosPaciente(Integer.parseInt(id_paciente));
        modelo.put("datos", buscarPaciente);
        //Buscar Sexo
        Sexos buscarsexo = this.mi.getDatosSexo(buscarPaciente.getId_tipo_sexo()); // saca un registro a ser modificado
        modelo.put("buscarSexo", buscarsexo);

        Cuadernos datol = new Cuadernos();
        datol.setTipo(tipo);
        datol.setId_pedido(Integer.parseInt(id_historial));
        datol.setId_historial(Integer.parseInt(id_historial));
        datol.setCod_esta(cliente.getCod_esta());
        if (cliente.getId_laboratorio() == 0) {
            datol.setId_cargo(0);
            datol.setId_laboratorio(999);
        } else {
            datol.setId_cargo(cliente.getId_laboratorio());
            datol.setId_laboratorio(cliente.getId_laboratorio());
        }
        if (datosconsul.getId_cargo() != 12 && datosconsul.getId_cargo() != 11) {
            List listalab = this.mi.getLabPendiente(datol);
            modelo.put("listalab", listalab);
        }
        if (datosconsul.getId_cargo() == 11) {
            List listalab = this.mi.getLabPendienteEco(datol);
            modelo.put("listalab", listalab);
        }
        if (datosconsul.getId_cargo() == 12) {
            List listalab = this.mi.getLabPendienteRx(datol);
            modelo.put("listalab", listalab);
        }

        return new ModelAndView("administrarecografia/LabPendiente", modelo);
    }
}
