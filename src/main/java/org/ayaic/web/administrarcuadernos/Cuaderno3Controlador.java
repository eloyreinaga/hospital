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
public class Cuaderno3Controlador {

    private final MiFacade mi;

    public Cuaderno3Controlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/Cuaderno3.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String id_paciente = request.getParameter("id_paciente");
        String id_reservacion = request.getParameter("id_reservacion");
        String id_consultorio = request.getParameter("id_consultorio");
        String id_persona = request.getParameter("id_persona");
        String expedido = request.getParameter("expedido");
        String tipo_medico = request.getParameter("tipo_medico");
        String swinter = request.getParameter("swinter");

        Localidades local = new Localidades();
        List Estab1 = this.mi.getListarEsta(local);
        Localidades datol = (Localidades) Estab1.get(0);
        modelo.put("localidades", datol);

        Pacientes buscarPaciente = this.mi.getDatosPaciente(Integer.parseInt(id_paciente)); // saca un registro a ser modificado
        modelo.put("datos", buscarPaciente);

        //Buscar Sexo
        Sexos buscarsexo = this.mi.getDatosSexo(buscarPaciente.getId_tipo_sexo()); // saca un registro a ser modificado
        modelo.put("buscarSexo", buscarsexo);

        if ("Cuaderno3".equals(accion)) {
            Cuadernos dato3 = new Cuadernos();
            dato3.setCod_esta(cliente.getCod_esta());
            dato3.setId_paciente(Integer.parseInt(id_paciente));
            List listaUni = this.mi.getVerCuaderno3Uni(dato3);
            modelo.put("listaCobros", listaUni);
            return new ModelAndView(new VerCuaderno3PDF(), modelo);
        }
        if (id_reservacion == null || "".equals(id_reservacion)) {
            return new ModelAndView("Aviso", "mensaje", "LLene primero el cuaderno6 Enfermeria");
        }
        List C3 = this.mi.getPacienteCuaderno3(Integer.parseInt(id_reservacion));

        modelo.put("id_paciente", id_paciente);
        modelo.put("id_reservacion", id_reservacion);
        modelo.put("id_consultorio", id_consultorio);
        modelo.put("id_persona", id_persona);
        modelo.put("expedido", expedido);
        modelo.put("tipo_medico", tipo_medico);
        modelo.put("swinter", swinter);
        
        Historiales datohi = new Historiales();
        datohi.setId_historial(Integer.parseInt(id_reservacion));
        datohi.setCod_esta(cliente.getCod_esta());
        Historiales datosHistorial = this.mi.getDatosHistorial(datohi);
        modelo.put("datoHisto", datosHistorial); 

        if ("Agregar".equals(accion)) {
            if (C3.isEmpty() != true) {
                return new ModelAndView("Aviso", "mensaje", "YA HA REGISTRADO UNA VEZ");
            } else {
                String accioncurativa = request.getParameter("accioncurativa");
                String tipo = request.getParameter("tipo");
                String fuera = request.getParameter("fuera");
                String insumos = request.getParameter("insumos");
                String insumos2 = request.getParameter("insumos2");
                String insumos3 = request.getParameter("insumos3");
                String orientacion = request.getParameter("orientacion");
                String pap = request.getParameter("pap");
                String numepap = request.getParameter("numepap");
                String respap = request.getParameter("respap");
                String ivaa = request.getParameter("ivaa");
                String numeivaa = request.getParameter("numeivaa");
                String resivaa = request.getParameter("resivaa");
                String mama = request.getParameter("mama");
                String resmama = request.getParameter("resmama");

                Cuadernos dato = new Cuadernos();
                dato.setCod_esta(cliente.getCod_esta());
                dato.setId_historial(Integer.parseInt(id_reservacion));

                if ("1".equals(orientacion)) {
                    dato.setOrientacion(Integer.parseInt(orientacion));
                }
                if (fuera == null || fuera == "" || "".equals(fuera)) {
                    fuera = "0";
                }
                if (accioncurativa == null) {
                    accioncurativa = "0";
                    tipo = "X";
                    insumos = "0";
                    insumos2 = "0";
                    insumos3 = "0";
                }

                if (numepap == null) {
                    numepap = "0";
                }
                if (respap == null) {
                    respap = "0";
                }
                if (ivaa == null) {
                    ivaa = "0";
                }
                if (numeivaa == null) {
                    numeivaa = "0";
                }
                if (resivaa == null) {
                    resivaa = "0";
                }
                if (mama == null) {
                    mama = "0";
                }
                if (resmama == null) {
                    resmama = "0";
                }
                if ("1".equals(pap) && "0".equals(numepap)) {
                    numepap = "1";
                }
                if ("1".equals(ivaa) && "0".equals(numeivaa)) {
                    numeivaa = "1";
                }

                dato.setTipoconsulta(tipo);
                dato.setInsumos(Integer.parseInt(insumos));

                if ("1".equals(pap)) {
                    dato.setPap(Integer.parseInt(pap));
                }

                if (accioncurativa == null) {
                    accioncurativa = "0";
                }

                dato.setReferido(request.getParameter("ref"));
                dato.setContraref(request.getParameter("cref"));
                dato.setObserva(request.getParameter("observa"));
                dato.setSuma10(Integer.parseInt(numepap));
                dato.setSuma11(Integer.parseInt(respap));
                dato.setSuma12(Integer.parseInt(ivaa));
                dato.setSuma13(Integer.parseInt(numeivaa));
                dato.setSuma14(Integer.parseInt(resivaa));
                dato.setSuma15(Integer.parseInt(mama));
                dato.setSuma16(Integer.parseInt(resmama));
                dato.setTipo_egreso(Integer.parseInt(fuera));
                dato.setId_persona(Integer.parseInt(id_persona));
                dato.setCod_esta(cliente.getCod_esta());

                switch (Integer.parseInt(accioncurativa)) {
                    case 1:
                        dato.setDiu(1);
                        if ("C".equals(tipo)) {
                            dato.setInsumos(0);
                        } else {
                            dato.setInsumos(1);
                        }
                        break;
                    case 2:
                        dato.setInyectable(1);
                        dato.setInsumos(1);
                        break;
                    case 3:
                        dato.setCondon(1);
                        if (Integer.parseInt(insumos) == 0) {
                            dato.setInsumos(12);
                        } else {
                            dato.setInsumos(Integer.parseInt(insumos));
                        }
                        break;
                    case 4:
                        dato.setPildora(1);
                        if (Integer.parseInt(insumos3) == 0) {
                            dato.setInsumos(3);
                        } else {
                            dato.setInsumos(Integer.parseInt(insumos3));
                        }
                        break;
                    case 5:
                        dato.setPildora(2);
                        dato.setInsumos(1);
                        dato.setTipoconsulta("N");
                        break;
                    case 6:
                        if (buscarPaciente.getId_tipo_sexo() == 1) {
                            dato.setTipoconsulta("N");
                            dato.setAqv(1);
                            dato.setInsumos(0);
                        } else {
                            dato.setTipoconsulta("C");
                            dato.setAqv(1);
                            dato.setInsumos(0);
                        }
                        break;
                    case 7:
                        dato.setOtras(1);
                        dato.setInsumos(0);
                        break;
                    case 8:
                        dato.setMnatural(1);
                        dato.setInsumos(0);
                        break;
                    case 9:
                        dato.setMnatural(2);
                        dato.setInsumos(0);
                        break;
                    case 10:
                        dato.setMnatural(3);
                        dato.setInsumos(0);
                        break;
                    case 11:
                        dato.setOtras(5);  ////para condon femenino
                        dato.setInsumos(Integer.parseInt(insumos2));
                        break;
                    case 12:
                        dato.setOtras(6);  //para implante subcutaneo 
                        if ("C".equals(tipo)) {
                            dato.setInsumos(0);
                        } else {
                            dato.setInsumos(1);
                        }
                        break;
                }
                if (("0".equals(accioncurativa)) && resivaa == null && ivaa == null && respap == null && pap == null && orientacion == null && ("0".equals(insumos))) {
                    return new ModelAndView("Aviso", "mensaje", "Debe Elegir un Metodo Anticonceptivo o PAP");
                }
                // grabar los datos introducidos en la ventana       
                this.mi.setCrearCuaderno3(dato);
            }
        }

        if ("Eliminar".equals(accion)) {
            String id_cuaderno = request.getParameter("id_cuaderno");
            Cuadernos datox = new Cuadernos();
            datox.setCod_esta(cliente.getCod_esta());
            datox.setId_cuaderno(Integer.parseInt(id_cuaderno));
            datox.setId_historial(Integer.parseInt(id_reservacion));
            this.mi.setEliminarCuaderno3(datox);
        }
        if ("Terminar".equals(accion)) {
            if ("inter".equals(swinter)) {
                return new ModelAndView("administrarhistoriales/PlanAccionPacienteI", modelo);
            } else {
                return new ModelAndView("administrarhistoriales/PlanAccionPaciente", modelo);
            }
        }
        //Sacar los datos de odontologia

        List listaOdon = this.mi.getPacienteCuaderno3(Integer.parseInt(id_reservacion));
        modelo.put("listaPrev", listaOdon);

        Cuadernos dato3 = new Cuadernos();
        dato3.setId_paciente(Integer.parseInt(id_paciente));
        dato3.setCod_esta(cliente.getCod_esta());
        dato3.setTipo("3");   ////getVerCuaderno2Ult
        Cuadernos datosC32 = this.mi.getVerCuaderno3Count(dato3);
        if (datosC32 != null) {
            modelo.put("numpap", Integer.toString(1 + datosC32.getSuma1()));
        } else {
            modelo.put("numpap", Integer.toString(0));
        }

        return new ModelAndView("administrarcuadernos/Cuaderno3", modelo);

    }
}
