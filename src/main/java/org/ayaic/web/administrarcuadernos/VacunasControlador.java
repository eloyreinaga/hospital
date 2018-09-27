package org.ayaic.web.administrarcuadernos;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Consultorios;
import org.ayaic.domain.Cuadernos;
import org.ayaic.domain.Historiales;
import org.ayaic.domain.Localidades;
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.Personas;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class VacunasControlador {

    private final MiFacade mi;

    public VacunasControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/Vacunas.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String id_paciente = request.getParameter("id_paciente");
        String id_persona = request.getParameter("id_persona");
        String id_reservacion = request.getParameter("id_reservacion");
        String id_consultorio = request.getParameter("id_consultorio");
        String id_pedido = request.getParameter("id_pedido");
        String expedido = request.getParameter("expedido");
        String tipo_medico = request.getParameter("tipo_medico");
        Date fecha1 = new Date();
        String ip = request.getRemoteAddr();
        String host = request.getRemoteHost();

        String sw = request.getParameter("sw");

        Localidades local = new Localidades();
        List Estab1 = this.mi.getListarEsta(local);
        Localidades datol = (Localidades) Estab1.get(0);
        modelo.put("localidades", datol);

        Pacientes buscarPaciente = new Pacientes();
        buscarPaciente = this.mi.getDatosPaciente(Integer.parseInt(id_paciente));
        Personas persona = this.mi.getBuscarPersona(cliente.getId_persona()); // saca un registro a ser modificado
        Consultorios datosconsultorio = this.mi.getDatosConsultorio(cliente.getId_consultorio()); // saca un registro a ser modificado

        if ("Vacunas1".equals(accion)) {
            Cuadernos datov = new Cuadernos();
            datov.setCod_esta(cliente.getCod_esta());
            datov.setId_paciente(Integer.parseInt(id_paciente));
            List listaUni = this.mi.getVerVacunasUni(datov);
            modelo.put("listaCobros", listaUni);
            return new ModelAndView(new VerVacunasPDF(), modelo);
        }

        if (id_reservacion == null || "".equals(id_reservacion)) {
            return new ModelAndView("Aviso", "mensaje", "LLene primero el cuaderno6 Enfermeria");
        }
        Cuadernos datov = new Cuadernos();
        datov.setCod_esta(cliente.getCod_esta());
        datov.setId_historial(Integer.parseInt(id_reservacion));
        datov.setCod_esta(cliente.getCod_esta());
        List V1 = this.mi.getPacienteVacunas(datov);

        modelo.put("datos", buscarPaciente);
        modelo.put("id_paciente", id_paciente);
        modelo.put("id_persona", id_persona);
        modelo.put("id_pedido", id_pedido);
        modelo.put("id_reservacion", id_reservacion);
        modelo.put("id_consultorio", id_consultorio);
        modelo.put("expedido", expedido);
        modelo.put("tipo_medico", tipo_medico);
        modelo.put("sw", sw);
        
        Historiales datohi = new Historiales();
        datohi.setId_historial(Integer.parseInt(id_reservacion));
        datohi.setCod_esta(cliente.getCod_esta());
        Historiales datosHistorial = this.mi.getDatosHistorial(datohi);
        modelo.put("datoHisto", datosHistorial); 

        if ("Agregar".equals(accion)) {
            if (V1.isEmpty() != true) {
                return new ModelAndView("Aviso", "mensaje", "YA HA REGISTRADO UNA VEZ");
            } else {
                String tipo = request.getParameter("tipo");
                String polio = request.getParameter("polio");
                String penta = request.getParameter("penta");
                String polio1 = request.getParameter("polio1");
                String penta1 = request.getParameter("penta1");
                String anti = request.getParameter("anti");
                String neumo = request.getParameter("neumo");
                String dpt = request.getParameter("dpt");
                String influenza = request.getParameter("influenza");
                String mujerdt = request.getParameter("mujerdt");
                String bcg = request.getParameter("bcg");
                String hepatitis = request.getParameter("hepatitis");
                String rcanina = request.getParameter("rcanina");
                String rhumana = request.getParameter("rhumana");
                String srp = request.getParameter("srp");
                String fama = request.getParameter("fama");
                String dvitaa = request.getParameter("dvitaa");
                String sr = request.getParameter("sr");
                String vph = request.getParameter("vph");

                Cuadernos dato = new Cuadernos();
                dato.setCod_esta(cliente.getCod_esta());
                dato.setId_persona(Integer.parseInt(id_persona));
                dato.setTipo_egreso(Integer.parseInt(tipo));
                if ("1".equals(bcg)) {
                    dato.setBcg(Integer.parseInt(bcg));
                }
                if (!"0".equals(hepatitis) && !"".equals(hepatitis) && hepatitis!=null){
                    dato.setAuto(Integer.parseInt(hepatitis));
                }
                
                if ("P".equals(dpt))           dato.setCancero(1);
                if ("S".equals(dpt))           dato.setCancero(2);
                if ("T".equals(dpt))           dato.setCancero(3);
                if ("C".equals(dpt))           dato.setCancero(4);
                if ("Q".equals(dpt))           dato.setCancero(5);

                //dato.setRhumana(Integer.parseInt(rhumana));
                if (rhumana != null) {
                    dato.setRhumana(Integer.parseInt(rhumana));
                }
                if (sr != null) {
                    dato.setSuma1(Integer.parseInt(sr));
                }
                if (rcanina != null && !"0".equals(rcanina)) {
                    dato.setRcanina(Integer.parseInt(rcanina));
                }
                dato.setSrp(srp);
                dato.setFama(fama);
                dato.setDvitaa(dvitaa);

                dato.setPolio(polio);
                dato.setPenta(penta);
                dato.setAnti(anti);
                dato.setBacterias(neumo);
                if ("1".equals(influenza) || "2".equals(influenza) || "3".equals(influenza) || "4".equals(influenza) || "5".equals(influenza) || "6".equals(influenza) || "7".equals(influenza) || "8".equals(influenza) || "9".equals(influenza))  
                    dato.setCancer(Integer.parseInt(influenza));

                if ("1".equals(vph))        dato.setSuma2(Integer.parseInt(vph));
                if ("2".equals(vph))        dato.setSuma2(Integer.parseInt(vph));
                
                dato.setMujerdt(mujerdt);
                if (datosconsultorio.getId_cargo() == 7) {
                    dato.setId_persona(datosHistorial.getId_persona());
                    dato.setFechap(datosHistorial.getFecha());
                } else {
                    dato.setId_persona(cliente.getId_persona());
                    dato.setFechap(fecha1);
                }
                dato.setId_historial(Integer.parseInt(id_reservacion));
                dato.setCod_esta(cliente.getCod_esta());
                try {
                    this.mi.setCrearVacunas(dato);
                } catch (Exception e) {
                    return new ModelAndView("Aviso", "mensaje", "No se creo Vacunas, Elija una opcion");
                }

            }
        }

        if ("Eliminar".equals(accion)) {
            String id_cuaderno = request.getParameter("id_cuaderno");
            Cuadernos datox = new Cuadernos();
            datox.setId_cuaderno(Integer.parseInt(id_cuaderno));
            datox.setId_historial(Integer.parseInt(id_reservacion));
            datox.setCod_esta(cliente.getCod_esta());
            this.mi.setEliminarVacunas(datox);
        }

        if ("Terminar".equals(accion)) {
            return new ModelAndView("administrarhistoriales/PlanAccionPaciente", modelo);
        }

        //Cuadernos datov=new Cuadernos();
        datov.setId_historial(Integer.parseInt(id_reservacion));
        datov.setCod_esta(cliente.getCod_esta());
        List listaVac = this.mi.getPacienteVacunas(datov);
        modelo.put("listaExter", listaVac);

        return new ModelAndView("administrarcuadernos/Vacunas", modelo);
    }
}
