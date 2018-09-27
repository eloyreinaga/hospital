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
public class Cuaderno7Controlador {

    private final MiFacade mi;

    public Cuaderno7Controlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/Cuaderno7.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String id_paciente = request.getParameter("id_paciente");
        String id_reservacion = request.getParameter("id_reservacion");
        String id_consultorio = request.getParameter("id_consultorio");
        String id_persona = request.getParameter("id_persona");
        String expedido = request.getParameter("expedido");
        String embara = request.getParameter("embara");
        String tipo_medico = request.getParameter("tipo_medico");
        String[] npieza = {"00", "11", "12", "13", "14", "15", "16", "17", "18", "21", "22", "23", "24", "25", "26", "27", "28", "31", "32", "33", "34", "35", "36", "37", "38", "41", "42", "43", "44", "45", "46", "47", "48", "51", "52", "53", "54", "55", "61", "62", "63", "64", "65", "71", "72", "73", "74", "75", "81", "82", "83", "84", "85"};

        Localidades local = new Localidades();
        List Estab1 = this.mi.getListarEsta(local);
        Localidades datol = (Localidades) Estab1.get(0);
        modelo.put("localidades", datol);
        modelo.put("cod_esta", Integer.toString(cliente.getCod_esta()));

        Pacientes buscarPaciente = this.mi.getDatosPaciente(Integer.parseInt(id_paciente)); // saca un registro a ser modificado
        modelo.put("datos", buscarPaciente);

        Cuadernos dato2 = new Cuadernos();
        dato2.setId_paciente(Integer.parseInt(id_paciente));
        dato2.setCod_esta(cliente.getCod_esta());
        dato2.setTipo("0");   ////getVerCuaderno2Ult
        Cuadernos datosC2 = this.mi.getVerCuaderno2Ult(dato2);
        if (datosC2 != null) {///// si la paciente esta embarazada o puerperio
            if (datosC2.getSuma1() == 1 || datosC2.getSuma2() == 1 || datosC2.getSuma3() == 1 || datosC2.getSuma4() == 1) {
                modelo.put("embara", "embara");
            }
            if (datosC2.getControlpos() > 0) {
                modelo.put("embara", "puerpera");
            }
        }

        if ("Cuaderno7".equals(accion)) {
            Cuadernos dato7 = new Cuadernos();
            dato7.setCod_esta(cliente.getCod_esta());
            dato7.setId_paciente(Integer.parseInt(id_paciente));
            List listaUni = this.mi.getVerCuaderno7Uni(dato7);
            modelo.put("listaCobros", listaUni);
            return new ModelAndView(new VerCuaderno7PDF(), modelo);
        }
        //Buscar Sexo
        Sexos buscarsexo = this.mi.getDatosSexo(buscarPaciente.getId_tipo_sexo()); // saca un registro a ser modificado
        modelo.put("buscarSexo", buscarsexo);

        modelo.put("npieza", npieza);
        modelo.put("id_paciente", id_paciente);
        modelo.put("tipo_medico", tipo_medico);
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
            String accioncurativa = request.getParameter("accioncurativa");
            String codmorbi = request.getParameter("codmorbi");
            String tipo = request.getParameter("tipo");
            String emba = request.getParameter("emba");
            String tratcon = request.getParameter("tratcon");
            String periot = request.getParameter("periot");
            String periog = request.getParameter("periog");
            String primera = request.getParameter("primera");
            String numpieza = request.getParameter("numpieza");
            String tratamiento = request.getParameter("tratamiento");

            datosHistorial.setRepetida(tipo);
            datosHistorial.setId_historial(Integer.parseInt(id_reservacion));
            datosHistorial.setCod_esta(cliente.getCod_esta());
            datosHistorial.setId_por(cliente.getId_persona());
            int iResultado = this.mi.setRegistrarHistorial(datosHistorial);

            Cuadernos dato = new Cuadernos();
            dato.setCod_esta(cliente.getCod_esta());
            dato.setTipoconsulta(tipo);
            dato.setId_historial(Integer.parseInt(id_reservacion));
            if ("1".equals(primera)) {
                dato.setPrimera(Integer.parseInt(primera));
            }
            dato.setTipodent(request.getParameter("pieza"));
            dato.setReferido(request.getParameter("ref"));
            dato.setContraref(request.getParameter("cref"));
            dato.setNumpieza(Integer.parseInt(numpieza));
            if (emba == null) {
                emba = "0";
            }
            dato.setAuto(Integer.parseInt(emba));
            dato.setNombre(request.getParameter("tratamiento"));
            if (accioncurativa == null) {
                return new ModelAndView("Aviso", "mensaje", "Debe elegir una opcion de tratamiento Odontoligico");
            }
            switch (Integer.parseInt(accioncurativa)) {
                case 1:
                case 2:
                case 3:
                    switch (Integer.parseInt(accioncurativa)) {
                        case 1:
                            dato.setTipo("A");
                            break;
                        case 2:
                            dato.setTipo("B");
                            break;
                        case 3:
                            dato.setTipo("C");
                            break;
                    }
                    dato.setExodoncia(1);
                    break;
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                    switch (Integer.parseInt(accioncurativa)) {
                        case 4:
                            dato.setTipo("A");
                            break;
                        case 5:
                            dato.setTipo("B");
                            break;
                        case 6:
                            dato.setTipo("C");
                            break;
                        case 7:
                            dato.setTipo("D");
                            break;
                        case 8:
                            dato.setTipo("E");
                            break;
                    }
                    dato.setRestauracion(1);
                    break;
                case 9:
                case 10:
                case 11:
                    switch (Integer.parseInt(accioncurativa)) {
                        case 9:
                            dato.setTipo("A");
                            if ("A".equals(periot) || "D".equals(periot) || "E".equals(periot)) {
                                dato.setTipo(periot);
                            }
                            break;
                        case 10:
                            dato.setTipo("B");
                            if ("B".equals(periog) || "F".equals(periog)) {
                                dato.setTipo(periog);
                            }
                            break;
                        case 11:
                            dato.setTipo("C");
                            break;
                    }

                    dato.setPeriodoncia(1);
                    break;
                case 12:
                case 13:
                    switch (Integer.parseInt(accioncurativa)) {
                        case 12:
                            dato.setTipo("A");
                            break;
                        case 13:
                            dato.setTipo("B");
                            if ("B".equals(tratcon) || "C".equals(tratcon) || "D".equals(tratcon)) {
                                dato.setTipo(tratcon);
                            }
                            break;
                    }

                    dato.setEndodoncia(1);
                    break;
                case 14:
                    dato.setTipo(request.getParameter("tiporayo"));
                    dato.setRayosx(1);
                    break;
                case 15:
                case 16:
                case 17:
                case 18:
                case 19:
                    switch (Integer.parseInt(accioncurativa)) {
                        case 15:
                            dato.setTipo("A");
                            break;
                        case 16:
                            dato.setTipo("B");
                            break;
                        case 17:
                            dato.setTipo("C");
                            break;
                        case 18:
                            dato.setTipo("D");
                            break;
                        case 19:
                            dato.setTipo("E");
                            break;
                    }
                    dato.setPreventiva(1);
                    break;
                case 20:
                    dato.setTipo(request.getParameter("otras"));
                    dato.setOtras(1);
                    break;
            }
            // grabar los datos introducidos en la ventana 
            dato.setId_persona(Integer.parseInt(id_persona));
            dato.setCodigo(codmorbi);
            dato.setCod_esta(cliente.getCod_esta());
            this.mi.setCrearCuaderno7(dato);
        }

        if ("Eliminar".equals(accion)) {
            String id_cuaderno = request.getParameter("id_cuaderno");
            Cuadernos datox = new Cuadernos();
            datox.setCod_esta(cliente.getCod_esta());
            datox.setId_cuaderno(Integer.parseInt(id_cuaderno));
            datox.setId_historial(Integer.parseInt(id_reservacion));
            this.mi.setEliminarCuaderno7(datox);
        }

        if ("Terminar".equals(accion)) {
            // grabar los datos introducidos en la ventana       
            return new ModelAndView("administrarhistoriales/PlanAccionPaciente", modelo);
        }
        //Sacar los datos de odontologia

        List listaOdon = this.mi.getPacienteCuaderno7(Integer.parseInt(id_reservacion));
        modelo.put("listaOdon", listaOdon);
        // sacar lista de datos personales de odontologia

        Cuadernos dato = new Cuadernos();
        dato.setCod_esta(cliente.getCod_esta());
        dato.setId_paciente(Integer.parseInt(id_paciente));
        dato.setTipo("P");
        List listaAten = this.mi.getVerCuaderno7Paci(dato);
        modelo.put("listaAten", listaAten);
        /////lista cie10
        datohi.setId_reservacion(Integer.parseInt(id_reservacion));
        List listarmorbi1 = this.mi.getListaMorbi(datohi);
        modelo.put("cont", Integer.toString(listarmorbi1.size()));
        modelo.put("listamorbi", listarmorbi1);

        return new ModelAndView("administrarcuadernos/Cuaderno7", modelo);

    }
}
