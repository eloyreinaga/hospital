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
public class CuadernoFisioControlador {

    private final MiFacade mi;

    public CuadernoFisioControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/CuadernoFisio.do")
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
        String[] ncontrol = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"};

        Localidades local = new Localidades();
        List Estab1 = this.mi.getListarEsta(local);
        Localidades datol = (Localidades) Estab1.get(0);
        modelo.put("localidades", datol);

        Pacientes buscarPaciente = this.mi.getDatosPaciente(Integer.parseInt(id_paciente)); // saca un registro a ser modificado
        modelo.put("datos", buscarPaciente);
        modelo.put("ncontrol", ncontrol);

        if ("CuadernoFisio".equals(accion)) {
            Cuadernos dato1 = new Cuadernos();
            dato1.setCod_esta(cliente.getCod_esta());
            dato1.setId_paciente(Integer.parseInt(id_paciente));
            List listaUni = this.mi.getVerCuadernoFisio(dato1);
            modelo.put("listaCobros", listaUni);
            return new ModelAndView(new VerCuadernoFisioPDF(), modelo);
        }
        Cuadernos datoreh = new Cuadernos();
        datoreh.setCod_esta(cliente.getCod_esta());
        datoreh.setId_historial(Integer.parseInt(id_reservacion));
        datoreh.setCod_esta(cliente.getCod_esta());
        List C1 = this.mi.getPacienteCuadernof(datoreh);

//Buscar Sexo
        Sexos buscarsexo = this.mi.getDatosSexo(buscarPaciente.getId_tipo_sexo()); // saca un registro a ser modificado
        modelo.put("buscarSexo", buscarsexo);

        modelo.put("id_paciente", id_paciente);
        modelo.put("id_reservacion", id_reservacion);
        modelo.put("id_consultorio", id_consultorio);
        modelo.put("id_persona", id_persona);
        modelo.put("expedido", expedido);
        modelo.put("tipo_medico", tipo_medico);
        
        Historiales datohi = new Historiales();
        datohi.setId_historial(Integer.parseInt(id_reservacion));
        datohi.setCod_esta(cliente.getCod_esta());
        Historiales datosHistorial = this.mi.getDatosHistorial(datohi);
        modelo.put("datoHisto", datosHistorial); 

        if ("Agregar".equals(accion)) {
            String consulta = request.getParameter("consulta"); ////
            String vendaje = request.getParameter("vendaje");  ////
            String electro = request.getParameter("electro");
            String ultra = request.getParameter("ultra");  ////
            String laser = request.getParameter("laser");  ////
            String magne = request.getParameter("magne");  ////
            String maso = request.getParameter("maso");
            String mecano = request.getParameter("mecano");
            String termo = request.getParameter("termo");
            String calorprof = request.getParameter("calorprof");  ////
            String lumino = request.getParameter("lumino");
            String electroana = request.getParameter("electroana");  ////
            String respira = request.getParameter("respira");
            String cinesi = request.getParameter("cinesi");  ////
            String lenguaje = request.getParameter("lenguaje");  ////
            String psico = request.getParameter("psico");  ////
            String tecneuro = request.getParameter("tecneuro");  ////
            String to1 = request.getParameter("to1");
            String teckine = request.getParameter("teckine");  ////
            String discapacidad = request.getParameter("discapacidad");
            String hidro = request.getParameter("hidro");
            String estimulacion = request.getParameter("estimulacion");
            String desarrollo = request.getParameter("desarrollo");
            String hidrocine = request.getParameter("hidrocine");  ////
            String evaluacion = request.getParameter("evaluacion");  ////
            String tanque = request.getParameter("tanque");  ////
            String psicolo = request.getParameter("psicolo");  ////
            String tipo = request.getParameter("tipo");
            String ref = request.getParameter("ref");
            String nsecion = request.getParameter("nsecion");

            if (C1.isEmpty() != true) {
                return new ModelAndView("Aviso", "mensaje", "YA HA REGISTRADO UNA VEZ");
            } else {

                datosHistorial.setRepetida(tipo);
                datosHistorial.setId_historial(Integer.parseInt(id_reservacion));
                datosHistorial.setCod_esta(cliente.getCod_esta());
                datosHistorial.setId_por(cliente.getId_persona());
                int iResultado = this.mi.setRegistrarHistorial(datosHistorial);

                Cuadernos dato = new Cuadernos();
                dato.setCod_esta(cliente.getCod_esta());
                dato.setId_persona(Integer.parseInt(id_persona));
                dato.setTipoconsulta(tipo);
                dato.setId_historial(Integer.parseInt(id_reservacion));
                dato.setReferido(request.getParameter("ref"));
                dato.setContraref(request.getParameter("cref"));

                if ("1".equals(nsecion)) {
                    dato.setNumpieza(Integer.parseInt(nsecion));
                }
                if ("1".equals(consulta)) {
                    dato.setSuma1(Integer.parseInt(consulta));
                }
                if ("1".equals(vendaje)) {
                    dato.setSuma2(Integer.parseInt(vendaje));
                }
                if ("1".equals(electro)) {
                    dato.setSuma3(Integer.parseInt(electro));
                }
                if ("1".equals(ultra)) {
                    dato.setSuma4(Integer.parseInt(ultra));
                }
                if ("1".equals(laser)) {
                    dato.setSuma5(Integer.parseInt(laser));
                }
                if ("1".equals(magne)) {
                    dato.setSuma6(Integer.parseInt(magne));
                }
                if ("1".equals(maso)) {
                    dato.setSuma7(Integer.parseInt(maso));
                }
                if ("1".equals(mecano)) {
                    dato.setSuma8(Integer.parseInt(mecano));
                }
                if ("1".equals(calorprof)) {
                    dato.setSuma9(Integer.parseInt(calorprof));
                }
                if ("1".equals(lumino)) {
                    dato.setSuma10(Integer.parseInt(lumino));
                }
                if ("1".equals(termo)) {
                    dato.setSuma11(Integer.parseInt(termo));
                }
                if ("1".equals(electroana)) {
                    dato.setSuma12(Integer.parseInt(electroana));
                }
                if ("1".equals(respira)) {
                    dato.setSuma13(Integer.parseInt(respira));
                }
                if ("1".equals(cinesi)) {
                    dato.setSuma14(Integer.parseInt(cinesi));
                }
                if ("1".equals(lenguaje)) {
                    dato.setSuma15(Integer.parseInt(lenguaje));
                }
                if ("1".equals(psico)) {
                    dato.setSuma16(Integer.parseInt(psico));
                }
                if ("1".equals(tecneuro)) {
                    dato.setSuma17(Integer.parseInt(tecneuro));
                }
                if ("1".equals(teckine)) {
                    dato.setSuma18(Integer.parseInt(teckine));
                }
                if ("1".equals(to1)) {
                    dato.setSuma19(Integer.parseInt(to1));
                }
                if ("1".equals(discapacidad)) {
                    dato.setSuma20(Integer.parseInt(discapacidad));
                }
                if ("1".equals(hidro)) {
                    dato.setSuma21(Integer.parseInt(hidro));
                }
                if ("1".equals(estimulacion)) {
                    dato.setSuma22(Integer.parseInt(estimulacion));
                }
                if ("1".equals(desarrollo)) {
                    dato.setSuma23(Integer.parseInt(desarrollo));
                }
                if ("1".equals(hidrocine)) {
                    dato.setSuma24(Integer.parseInt(hidrocine));
                }
                if ("1".equals(evaluacion)) {
                    dato.setSuma25(Integer.parseInt(evaluacion));
                }
                if ("1".equals(tanque)) {
                    dato.setSuma26(Integer.parseInt(tanque));
                }
                if ("1".equals(psicolo)) {
                    dato.setSuma27(Integer.parseInt(psicolo));
                }

                dato.setCod_esta(cliente.getCod_esta());
                this.mi.setCrearCuadernof(dato);
            }
        }

        if ("Eliminar".equals(accion)) {
            String id_cuaderno = request.getParameter("id_cuaderno");
            Cuadernos datox = new Cuadernos();
            datox.setId_cuaderno(Integer.parseInt(id_cuaderno));
            datox.setId_historial(Integer.parseInt(id_reservacion));
            datox.setCod_esta(cliente.getCod_esta());
            this.mi.setEliminarCuadernof(datox);
        }
        if ("Terminar".equals(accion)) {
            // grabar los datos introducidos en la ventana

            return new ModelAndView("administrarhistoriales/PlanAccionPaciente", modelo);
        }
        //Sacar los datos de odontologia
        //Cuadernos datoreh= new Cuadernos();
        datoreh.setId_historial(Integer.parseInt(id_reservacion));
        datoreh.setCod_esta(cliente.getCod_esta());
        List listaRehab = this.mi.getPacienteCuadernof(datoreh);
        modelo.put("listaExter", listaRehab);

        return new ModelAndView("administrarcuadernos/CuadernoFisio", modelo);

    }
}
