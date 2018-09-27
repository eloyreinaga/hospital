package org.ayaic.web.administrarcuadernos;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Cuadernos;
import org.ayaic.domain.Historiales;
import org.ayaic.domain.Localidades;
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.Sexos;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CemergenciasControlador {

    private final MiFacade mi;

    public CemergenciasControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/Cemergencias.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String id_paciente = request.getParameter("id_paciente");
        String id_reservacion = request.getParameter("id_reservacion");
        String id_consultorio = request.getParameter("id_consultorio");
        String id_persona = request.getParameter("id_persona");
        String expedido = request.getParameter("expedido");
        String estimc = request.getParameter("estimc");
        String tipo_medico = request.getParameter("tipo_medico");
        String enfer = request.getParameter("enfer");
        String swinter = request.getParameter("swinter");
        String valor1 = request.getParameter("valor1");
        double total = 0;

        Localidades local = new Localidades();
        List Estab1 = this.mi.getListarEsta(local);
        Localidades datol = (Localidades) Estab1.get(0);
        modelo.put("localidades", datol);
        local.setArea("R");
        local.setId_persona(1);
        List EstabRef = this.mi.getListarEstabRef(local);
        modelo.put("listarestab", EstabRef);
        local.setId_persona(2);
        List EstabCRef = this.mi.getListarEstabRef(local);
        modelo.put("listarestabC", EstabCRef);

        Pacientes buscarPaciente = this.mi.getDatosPaciente(Integer.parseInt(id_paciente)); // saca un registro a ser modificado
        modelo.put("urgencias", Integer.toString(cliente.getId_almacen()));
        modelo.put("datos", buscarPaciente);

        if ("Cuaderno1".equals(accion)) {
            Cuadernos dato1 = new Cuadernos();
            dato1.setCod_esta(cliente.getCod_esta());  ///16-01-2016
            dato1.setId_persona(cliente.getId_persona());  ///13-02-2017
            dato1.setTipo("U");
            dato1.setId_estado("%");
            dato1.setEstado("T");
            dato1.setAspecto("A");
            dato1.setId_paciente(Integer.parseInt(id_paciente));
            List listaCie = this.mi.getVerCuaderno1CieUni(dato1);
            modelo.put("listaCie", listaCie);
            List listaUni = this.mi.getVerCuaderno1Uni(dato1);
            modelo.put("listaCobros", listaUni);
            return new ModelAndView(new VerCuaderno1PDF(), modelo);
        }
        List C1 = this.mi.getPacienteCuaderno1(Integer.parseInt(id_reservacion));

        //Buscar Sexo
        Sexos buscarsexo = this.mi.getDatosSexo(buscarPaciente.getId_tipo_sexo()); // saca un registro a ser modificado
        modelo.put("buscarSexo", buscarsexo);

        Historiales datohi = new Historiales();
        datohi.setId_historial(Integer.parseInt(id_reservacion));
        datohi.setCod_esta(cliente.getCod_esta());
        Historiales datosHistorial = this.mi.getDatosHistorial(datohi);
        modelo.put("datoHisto", datosHistorial);
        
        modelo.put("id_paciente", id_paciente);
        modelo.put("id_reservacion", id_reservacion);
        modelo.put("id_consultorio", id_consultorio);
        modelo.put("id_persona", id_persona);
        modelo.put("expedido", expedido);
        modelo.put("tipo_medico", tipo_medico);
        modelo.put("swinter", swinter);

        if ("Agregar".equals(accion)) {
            String tipo = request.getParameter("tipo");

            if (C1.isEmpty() != true) {
                return new ModelAndView("Aviso", "mensaje", "YA HA REGISTRADO UNA VEZ");
            } else {

                //Historiales datohi= new Historiales() ;
                //datohi.setId_historial(Integer.parseInt(id_reservacion)) ;
                //datohi.setCod_esta(cliente.getCod_esta());
                //Historiales datosHistorial= this.mi.getDatosHistorial(datohi) ;    
                datosHistorial.setRepetida(tipo);
                datosHistorial.setId_historial(Integer.parseInt(id_reservacion));
                datosHistorial.setCod_esta(cliente.getCod_esta());
                datosHistorial.setId_por(cliente.getId_persona());

                int iResultado = this.mi.setRegistrarHistorial(datosHistorial);
                String id_hospital = request.getParameter("id_hospital");
                String id_hospital2 = request.getParameter("id_hospital2");
                String tingreso = request.getParameter("tingreso");
                String motivo = request.getParameter("motivo");
                String violencia = request.getParameter("violencia");
                String fallecido = request.getParameter("fallecido");
                String tipoegreso = request.getParameter("tipoegreso");

                if (id_hospital == null || id_hospital == "" || "".equals(id_hospital)) {
                    id_hospital = "0";
                }
                if (id_hospital2 == null || id_hospital2 == "" || "".equals(id_hospital2)) {
                    id_hospital2 = "0";
                }

                Cuadernos dato = new Cuadernos();
                dato.setCod_esta(cliente.getCod_esta());  ///16-01-2016
                dato.setId_persona(Integer.parseInt(id_persona));
                dato.setTipoconsulta(tipo);
                dato.setSuma30(Integer.parseInt(id_hospital));
                dato.setSuma31(Integer.parseInt(id_hospital2));
                dato.setId_historial(Integer.parseInt(id_reservacion));
                dato.setReferido(request.getParameter("ref"));
                dato.setContraref(request.getParameter("cref"));

                dato.setViolencia(Integer.parseInt(violencia));
                dato.setSuma32(Integer.parseInt(tipoegreso));
                dato.setSuma50(Integer.parseInt(tingreso));
                dato.setSuma51(Integer.parseInt(motivo));
                dato.setSuma52(Integer.parseInt(fallecido));
                dato.setSuma53(1);

                if (buscarPaciente.getEdad() < 5) {
                    dato.setSuma1(1);
                }
                if (buscarPaciente.getEdad() >= 5 && buscarPaciente.getEdad() <= 9) {
                    dato.setSuma2(1);
                }
                if (buscarPaciente.getEdad() >= 10 && buscarPaciente.getEdad() <= 20) {
                    dato.setSuma3(1);
                }
                if (buscarPaciente.getEdad() >= 21 && buscarPaciente.getEdad() <= 59) {
                    dato.setSuma4(1);
                }
                if (buscarPaciente.getEdad() > 59) {
                    dato.setSuma5(1);
                }
                // grabar los datos introducidos en la ventana  
                dato.setCod_esta(cliente.getCod_esta());
                this.mi.setCrearCuaderno1(dato);
            }

            ///Ahora modifica el historial para que sea emergencias = 1 
            if (datosHistorial.getId_cargo() > 0) {
                datohi.setId_cargo(datosHistorial.getId_cargo());
            }
            this.mi.setModificarInternado(datohi);
        }

        if ("Eliminar".equals(accion)) {
            String id_cuaderno = request.getParameter("id_cuaderno");
            Cuadernos datox = new Cuadernos();
            datox.setId_cuaderno(Integer.parseInt(id_cuaderno));
            datox.setId_historial(Integer.parseInt(id_reservacion));
            datox.setCod_esta(cliente.getCod_esta());
            this.mi.setEliminarCuaderno1(datox);
            Historiales datoi = new Historiales();
            datoi.setId_persona(Integer.parseInt(id_persona));
            datoi.setAccion("R");
            datoi.setId_historial(Integer.parseInt(id_reservacion));
            datoi.setId_cargo(0);
            datoi.setCod_esta(cliente.getCod_esta());
            if (datosHistorial.getId_cargo() > 0) {
                datoi.setId_cargo(datosHistorial.getId_cargo());
            }
            this.mi.setModificarInternado(datoi);
        }

        if ("Terminar".equals(accion)) {
            if ("inter".equals(swinter)) {
                return new ModelAndView("administrarhistoriales/PlanAccionPacienteI", modelo);
            } else {
                return new ModelAndView("administrarhistoriales/PlanAccionPaciente", modelo);
            }
        }
        //Sacar los datos de odontologia

        List listaOdon = this.mi.getPacienteCemergencia(Integer.parseInt(id_reservacion));
        modelo.put("listaExter", listaOdon);

        Cuadernos dato1 = new Cuadernos();
        dato1.setCod_esta(cliente.getCod_esta());  ///16-01-2016
        dato1.setId_paciente(Integer.parseInt(id_paciente));
        dato1.setTipo("U");
        dato1.setEstado("T");
        List listac1 = this.mi.getVerCuaderno1Uni(dato1);  ///getVerCuaderno1Uni
        modelo.put("listac1", listac1);

        return new ModelAndView("administrarcuadernos/Cemergencias", modelo);

    }
}
