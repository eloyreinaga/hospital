package org.ayaic.web.administrarenfermeria;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Costos;
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CobrarNPacienteEControlador {

    private final MiFacade mi;

    public CobrarNPacienteEControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/CobrarNPacienteEnfermeria.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String accion1 = request.getParameter("accion1");
        String sw = request.getParameter("sw");

        String id_rubro = request.getParameter("id_rubro");
        String nombres = request.getParameter("nombres");
        String num_cladoc = request.getParameter("num_cladoc");
        String id_costo = request.getParameter("id_costo");
        String nit = request.getParameter("nit");
        Date fecha1 = new Date();

        Costos datoq = new Costos();
        datoq.setId_rubro(1);
        datoq.setId_prestacion(1);
        datoq.setId_estado("%");
        datoq.setTipo(0);
        datoq.setId_persona(5000);
        datoq.setEmergencias(0);
        datoq.setCod_esta(cliente.getCod_esta());///para todos 20-02-2017    
        Pacientes paciente = new Pacientes();
        paciente.setCod_esta(cliente.getCod_esta());
        if ("Nuevo".equals(accion1)) {
            num_cladoc = Long.toString(this.mi.getNumClaDoc(paciente));
        }
        List listarRubros = this.mi.getListaRubro();
        modelo.put("listarRubros", listarRubros);
        if (request.getParameter("id_rubro") != null) {

            List listarCostos = this.mi.getListarCostos(datoq);
            modelo.put("listarCostos", listarCostos);
            if (request.getParameter("id_costo") != null) {
                Costos costo = new Costos();
                costo.setId_costo(Integer.parseInt(id_costo));
                costo.setCod_esta(cliente.getCod_esta());
                Costos buscarCosto = this.mi.getDatosCosto(costo);
                if (buscarCosto != null) {
                    modelo.put("precio", Double.toString(buscarCosto.getCosto_unit()));
                } else {
                    modelo.put("precio", "0");
                }
            }
        }
        modelo.put("accion", accion);
        modelo.put("sw", request.getParameter("sw"));

        modelo.put("nombres", nombres);
        modelo.put("num_cladoc", num_cladoc);
        modelo.put("id_rubro", id_rubro);
        modelo.put("id_costo", id_costo);
        modelo.put("nit", nit);

        if ("Terminar".equals(accion)) {
            // almacenar el monto de cobro
            String costo_unit = request.getParameter("precio");

            Pacientes dato = new Pacientes();
            dato.setNombres(nombres);
            dato.setPrecio_total(Float.parseFloat(costo_unit));
            dato.setId_paciente(0);
            dato.setId_estado("A");
            dato.setNum_cladoc(num_cladoc);
            dato.setNit(nit);
            //dato.setId_costo(Integer.parseInt(id_costo)) ;
            dato.setId_rubro(Integer.parseInt(id_rubro));
            dato.setId_persona(cliente.getId_persona());
            dato.setId_dispensa(cliente.getId_persona());
            dato.setTipo("C");
            dato.setId_factura(0);/////0 sin factura, 1 con factura, 2, 3, 4, para num correlativo, entradas, ajuste+ y ajuste-
            dato.setFec_registro(fecha1);
            dato.setCod_esta(cliente.getCod_esta());
            //dato.setNum_cladoc(num_recibo); 
            this.mi.setCrearPeedido(dato);

            // pacientes a cobrar enviados por odontologia
            //Pacientes paciente= new Pacientes() ;
            paciente.setId_estado("A");
            paciente.setId_rubro(5);
            paciente.setId_localidad(cliente.getCod_esta());
            List listarCo = this.mi.getListarCobroRubro(paciente);
            modelo.put("milista", listarCo);

            // pacientes a cobrar enviados por odontologia
            paciente.setId_estado("C");
            List listarOdon = this.mi.getListarCobroRubro(paciente);
            modelo.put("listaOdon", listarOdon);

            return new ModelAndView("administrarenfermeria/ListarCobroReserva", modelo);
        }
        return new ModelAndView("administrarenfermeria/CobrarNPaciente", modelo);

    }
}
