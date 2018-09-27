package org.ayaic.web.administrarquirofanos;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ayaic.domain.Clientes;
import org.ayaic.domain.Consultorios;
import org.ayaic.domain.Historiales;
import org.ayaic.domain.Localidades;
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.Personas;
import org.ayaic.domain.Quirofanos;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NuevoQuirofanoControlador {

    private final MiFacade mi;

    public NuevoQuirofanoControlador(MiFacade mi) {
        this.mi = mi;
    }
    
    @RequestMapping("/NuevoQuirofano.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String accion1 = request.getParameter("accion1");
        String swinter = request.getParameter("swinter");
        String id_paciente = request.getParameter("id_paciente");
        String id_quirofano = request.getParameter("id_quirofano");
        String[] dias = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
        String[] meses = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        Date fecha1 = new Date();
        int anioq = fecha1.getYear() + 1900;
        String[] anios = {Integer.toString(anioq), Integer.toString(anioq + 1)};

        String[] horas = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24"};
        String[] minutos = {"00", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"};
        String[] aniosq = {(Integer.toString(anioq)), (Integer.toString(anioq - 1))};
        String[] aniosqq = {(Integer.toString(anioq)), (Integer.toString(anioq + 1))};

        modelo.put("anios", anios);
        modelo.put("meses", meses);
        modelo.put("dias", dias);
        modelo.put("horas", horas);
        modelo.put("minutos", minutos);
        modelo.put("anio", Integer.toString(fecha1.getYear() + 1900));
        modelo.put("mes", Integer.toString(fecha1.getMonth() + 1));
        modelo.put("dia", Integer.toString(fecha1.getDate()));
        modelo.put("hora", Integer.toString(fecha1.getHours()));
        modelo.put("minuto", Integer.toString(fecha1.getMinutes()));
        modelo.put("anioe", Integer.toString(fecha1.getYear() + 1900));
        modelo.put("mese", Integer.toString(fecha1.getMonth() + 1));
        modelo.put("diae", Integer.toString(fecha1.getDate()));
        modelo.put("horae", Integer.toString(fecha1.getHours()));
        modelo.put("minutoe", Integer.toString(fecha1.getMinutes()));

        Localidades local = new Localidades();
        List Estab1 = this.mi.getListarEsta(local);
        Localidades datoestab = (Localidades) Estab1.get(0);
        modelo.put("datoestab", datoestab);
        modelo.put("urgencias", Integer.toString(cliente.getId_almacen()));

        if (id_paciente == null || "".equals(id_paciente)) {
            id_paciente = "0";
        }
        Pacientes buscarPaciente = this.mi.getDatosPaciente(Integer.parseInt(id_paciente)); // saca un registro a ser modificado
        modelo.put("datos", buscarPaciente);

        if ("GuardarQ".equals(accion1)) {
            String id_persona1 = request.getParameter("id_persona1");
            String id_persona2 = request.getParameter("id_persona2");
            String id_persona3 = request.getParameter("id_persona3");
            String id_persona4 = request.getParameter("id_persona4");
            String id_consultorio = request.getParameter("id_consultorio");
            String id_consultorio2 = request.getParameter("id_consultorio2");
            String id_reservacion = request.getParameter("id_reservacion");
            String id_historial = request.getParameter("id_reservacion");
            String diagnosticopre = request.getParameter("diagnosticopre");
            String observa = request.getParameter("observa");
            String pulso = request.getParameter("pulso");
            String tempera = request.getParameter("tempera");
            String presion = request.getParameter("presion");
            String corazon = request.getParameter("corazon");
            String pulmon = request.getParameter("pulmon");
            String tipo = request.getParameter("tipo");
            String diai = request.getParameter("diai");
            String mesi = request.getParameter("mesi");
            String anioi = request.getParameter("anioi");
            String horai = request.getParameter("horai");
            String minutoi = request.getParameter("minutoi");
            Date fec_res = new Date();

            int diax = Integer.parseInt(diai);
            int mesx = Integer.parseInt(mesi) - 1;
            int aniox = Integer.parseInt(anioi) - 1900;
            int horax = Integer.parseInt(horai);
            int minutox = Integer.parseInt(minutoi);
            fec_res = new Date(aniox, mesx, diax, horax, minutox, 00);

            Pacientes bPaciente = this.mi.getDatosPaciente(Integer.parseInt(id_paciente));
            Historiales reserva = new Historiales();
            reserva.setId_paciente(Integer.parseInt(id_paciente));

            if (("".equals(id_quirofano)) && id_quirofano == null) {
                return new ModelAndView("Aviso", "mensaje", "Eliga un Quirofano");
            }

            Quirofanos datores = new Quirofanos();
            datores.setId_quirofano(Integer.parseInt(id_quirofano));
            datores.setId_historial(Integer.parseInt(id_historial));
            datores.setId_empresa(bPaciente.getId_empresa());
            datores.setId_paciente(Integer.parseInt(id_paciente));
            datores.setFecha(fec_res);
            datores.setSuma1(Integer.parseInt(id_persona1));
            datores.setSuma2(Integer.parseInt(id_persona2));
            datores.setSuma3(Integer.parseInt(id_persona3));
            datores.setSuma4(Integer.parseInt(id_persona4));
            datores.setUlt_usuario(cliente.getId_persona());
            datores.setCod_esta(cliente.getCod_esta());
            datores.setCadena1(tipo);
            datores.setCadena2(observa);
            datores.setCadena3(pulso);
            datores.setCadena4(tempera);
            datores.setCadena5(presion);
            datores.setCadena6(corazon);
            datores.setCadena7(pulmon);
            datores.setCadena15(diagnosticopre);
            datores.setId_estado("Q");
            try {
                this.mi.setCrearReservaQuirofano(datores);
                datores.setCadena11("L");
                List listarQuirofanos = this.mi.getListarQuirofanosLibres(datores);
                modelo.put("listarQuirofanosR", listarQuirofanos);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "La actualización no se cumplio, verifique los datos");
            }

        }

        if ("ImprimirQuiro".equals(accion1)) {
            String id_historial = request.getParameter("id_historial");

            Pacientes buscarPacienteh = this.mi.getDatosPaciente(Integer.parseInt(id_paciente)); // saca un registro a ser modificado
            modelo.put("datosp", buscarPacienteh);
            Historiales dato = new Historiales();
            dato.setId_paciente(Integer.parseInt(id_paciente));
            List listas = this.mi.getListarHistoria(dato);
            modelo.put("milistas", listas);
            modelo.put("dato", cliente);

            Historiales datoh = new Historiales();
            datoh.setId_historial(Integer.parseInt(id_historial));
            datoh.setAccion("I");
            List listasi = this.mi.getHistoriaInter(datoh);
            if (listasi.size() > 0) {
                Historiales listaHi = (Historiales) listasi.get(listasi.size() - 1);
                modelo.put("historia", listaHi);
            }

            Quirofanos datores = new Quirofanos();
            datores.setCod_esta(cliente.getCod_esta());
            datores.setId_quirofano(Integer.parseInt(id_quirofano));
            datores.setId_historial(Integer.parseInt(id_historial));
            datores.setCadena11("I");
            List listarQuirofanos = this.mi.getListarQuirofanosLibres2(datores);////lista de quirofanos total
            if (!listarQuirofanos.isEmpty()) {
                Quirofanos quiro = (Quirofanos) listarQuirofanos.get(0);
                Personas ayucocirujano = this.mi.getBuscarPersona(quiro.getSuma3());
                modelo.put("ayucirujano", ayucocirujano);
                Personas anastecista = this.mi.getBuscarPersona(quiro.getSuma4());
                modelo.put("anastecista", anastecista);
                Personas ayuanastecista = this.mi.getBuscarPersona(quiro.getSuma5());
                modelo.put("ayuanastecista", ayuanastecista);
            }
            modelo.put("listarQuirofanos", listarQuirofanos);///

            return new ModelAndView(new ListarProgQuirofanoPDF(), modelo);
        }

        if ("Modificar".equals(accion1)) {
            String id_historial = request.getParameter("id_historial");

            Pacientes buscarPacienteh = this.mi.getDatosPaciente(Integer.parseInt(id_paciente)); // saca un registro a ser modificado
            modelo.put("datosp", buscarPacienteh);
            Historiales dato = new Historiales();
            dato.setId_paciente(Integer.parseInt(id_paciente));
            List listas = this.mi.getListarHistoria(dato);
            modelo.put("milistas", listas);
            modelo.put("dato", cliente);

            Historiales datoh = new Historiales();
            datoh.setId_historial(Integer.parseInt(id_historial));
            datoh.setAccion("I");
            List listasi = this.mi.getHistoriaInter(datoh);
            if (listasi.size() > 0) {
                Historiales listaHi = (Historiales) listasi.get(listasi.size() - 1);
                modelo.put("historia", listaHi);
            }

            Quirofanos datores = new Quirofanos();
            datores.setCod_esta(cliente.getCod_esta());
            datores.setId_quirofano(Integer.parseInt(id_quirofano));
            datores.setId_historial(Integer.parseInt(id_historial));
            datores.setCadena11("I");
            List listarQuirofanos = this.mi.getListarQuirofanosLibres2(datores);////lista de quirofanos total
            if (!listarQuirofanos.isEmpty()) {
                Quirofanos quiro = (Quirofanos) listarQuirofanos.get(0);
                Personas ayucocirujano = this.mi.getBuscarPersona(quiro.getSuma3());
                modelo.put("ayucirujano", ayucocirujano);
                Personas anastecista = this.mi.getBuscarPersona(quiro.getSuma4());
                modelo.put("anastecista", anastecista);
                Personas ayuanastecista = this.mi.getBuscarPersona(quiro.getSuma5());
                modelo.put("ayuanastecista", ayuanastecista);
            }
            modelo.put("listarQuirofanos", listarQuirofanos);///

            return new ModelAndView("administrarquirofanos/NuevoQuirofano", modelo);
        }

        if ("SolicitudQuirofano".equals(accion)) {
            String id_reservacion = request.getParameter("id_reservacion");
            String id_consultorio = request.getParameter("id_consultorio");
            String id_consultorio2 = request.getParameter("id_consultorio2");
            String id_historial = request.getParameter("id_reservacion");
            String id_persona1 = request.getParameter("id_persona1");
            String id_persona2 = request.getParameter("id_persona2");
            String id_persona3 = request.getParameter("id_persona3");
            String id_persona4 = request.getParameter("id_persona4");
            String expedido = request.getParameter("expedido");
            String tipo_medico = request.getParameter("tipo_medico");

            Quirofanos datores = new Quirofanos();
            datores.setCod_esta(cliente.getCod_esta());

            Historiales datohi = new Historiales();
            datohi.setId_historial(Integer.parseInt(id_historial));
            datohi.setCod_esta(cliente.getCod_esta());
            Historiales datosHistorial = mi.getDatosHistorial(datohi);

            List listarQuirofanos = this.mi.getListarQuirofanos(datores);////lista de quirofanos total
            modelo.put("listarQuirofanos", listarQuirofanos);///
            datores.setCadena11("L");
            List listarQuirofanosR = this.mi.getListarQuirofanosLibres(datores);
            modelo.put("listarQuirofanosR", listarQuirofanosR);
            if ((!"".equals(id_quirofano)) && id_quirofano != null) {
                datores.setCadena11("Q");
                datores.setId_quirofano(Integer.parseInt(id_quirofano));
                List listarQuirofanosR2 = this.mi.getListarQuirofanosLibres1(datores);
                modelo.put("listarQuirofanosR", listarQuirofanosR2);
            }

            Consultorios a = new Consultorios();
            a.setCod_esta(cliente.getCod_esta());
            a.setId_estado("T");
            List listarCargos = this.mi.getListarConsultoriosTransf(a);
            modelo.put("listarCargos", listarCargos);

            if (("".equals(id_consultorio)) || id_consultorio == null) {
                id_consultorio = "0";
            }
            Personas persona = new Personas();
            persona.setId_consultorio(Integer.parseInt(id_consultorio));
            persona.setCod_esta(cliente.getCod_esta());
            List buscarEmpleado = this.mi.getDatosPersonaConsul(persona);
            modelo.put("listaPersonas", buscarEmpleado);
            ////para anasteciologia
            persona.setId_consultorio(25);
            List buscarEmpleado2 = this.mi.getDatosPersonaConsul(persona);
            modelo.put("listaPersonas2", buscarEmpleado2);
            persona.setId_consultorio(46);
            List buscarEmpleado3 = this.mi.getDatosPersonaConsul(persona);
            modelo.put("listaPersonas3", buscarEmpleado3);

            modelo.put("id_paciente", id_paciente);
            modelo.put("id_reservacion", id_reservacion);
            modelo.put("id_quirofano", id_quirofano);
            modelo.put("id_historial", id_historial);
            modelo.put("diagnosticopre", datosHistorial.getDiagnostico());
            modelo.put("id_consultorio", id_consultorio);
            modelo.put("id_consultorio2", id_consultorio2);
            modelo.put("id_persona1", id_persona1); ////Integer.toString(cliente.getId_persona()));
            modelo.put("id_persona2", id_persona2);
            modelo.put("id_persona3", id_persona3);
            modelo.put("id_persona4", id_persona4);
            modelo.put("expedido", expedido);
            modelo.put("tipo_medico", tipo_medico);
            modelo.put("swinter", swinter);
            modelo.put("accion", accion);
            return new ModelAndView("administrarquirofanos/ProgramarQuirofano", modelo);
        }

        if ("Guardar".equals(accion)) {
            String actividad = request.getParameter("actividad");
            String tema = request.getParameter("tema");
            String numero = request.getParameter("numero");
            String valor_1 = request.getParameter("valor_1");
            String[] Fechaini = valor_1.split("-");
            int iAnio1 = Integer.parseInt(Fechaini[0]) - 1900;
            int iMes1 = Integer.parseInt(Fechaini[1]) - 1;
            int iDia1 = Integer.parseInt(Fechaini[2]);

            return new ModelAndView("administrarquirofanos/NuevoQuirofano", modelo);
        }

        modelo.put("id_quirofano", request.getParameter("id_quirofano"));

        //Para la primera vez que entra a la pagina
        if ((request.getParameter("id_quirofano") != null) && ("Modificar".equals(accion)) && (request.getParameter("recargado") == null)) {
            //Sacamos los datos del quirofano
            Quirofanos datoqui = new Quirofanos();
            datoqui.setId_quirofano(Integer.parseInt(id_quirofano));
            Quirofanos buscarquirofano = this.mi.getDatosQuirofano(datoqui);
            modelo.put("buscarquirofano", buscarquirofano);
            modelo.put("id_estado", buscarquirofano.getId_estado());
            modelo.put("sw", request.getParameter("sw"));
        }

        if ("Terminar".equals(accion)) {
            String id_reservacion = request.getParameter("id_reservacion");
            String id_consultorio = request.getParameter("id_consultorio");
            String id_historial = request.getParameter("id_reservacion");
            String id_persona = request.getParameter("id_persona");
            String expedido = request.getParameter("expedido");
            String tipo_medico = request.getParameter("tipo_medico");

            modelo.put("id_paciente", id_paciente);
            modelo.put("id_reservacion", id_reservacion);
            modelo.put("id_historial", id_historial);
            modelo.put("id_consultorio", id_consultorio);
            modelo.put("id_persona", id_persona);
            modelo.put("expedido", expedido);
            modelo.put("tipo_medico", tipo_medico);

            if ("inter".equals(swinter)) {
                return new ModelAndView("administrarhistoriales/PlanAccionPacienteI", modelo);
            } else {
                return new ModelAndView("administrarhistoriales/PlanAccionPaciente", modelo);
            }
        }
        modelo.put("accion", accion);
        return new ModelAndView("administrarquirofanos/NuevoQuirofano", modelo);
    }
}
