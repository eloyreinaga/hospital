package org.ayaic.web.administrarmedicamentos;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Historiales;
import org.ayaic.domain.Medicamentos;
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.Personas;
import org.ayaic.domain.Prestaciones;
import org.ayaic.domain.Recetas;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class VerRecibidosControlador {

    private final MiFacade mi;

    public VerRecibidosControlador(MiFacade mi) {
        this.mi = mi;
    }
    
    @RequestMapping("/VerRecibidos.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");

        Personas persona = this.mi.getBuscarPersona(cliente.getId_persona()); // saca un registro a ser modificado
        String accion = request.getParameter("accion");

        //Recuperamos variables del jsp
        String sFecha_ini = request.getParameter("valor_1");
        String sFecha_fin = request.getParameter("valor_2");

        String[] sFechaini = sFecha_ini.split("-");
        int iAnio1 = Integer.parseInt(sFechaini[0]) - 1900;
        int iMes1 = Integer.parseInt(sFechaini[1]) - 1;
        int iDia1 = Integer.parseInt(sFechaini[2]);

        String[] sFechafin = sFecha_fin.split("-");
        int iAnio2 = Integer.parseInt(sFechafin[0]) - 1900;
        int iMes2 = Integer.parseInt(sFechafin[1]) - 1;
        int iDia2 = Integer.parseInt(sFechafin[2]);

        Date dFechaini1 = new Date(iAnio1, iMes1, iDia1);
        Date dFechafin1 = new Date(iAnio2, iMes2, iDia2);

        Pacientes dato = new Pacientes();
        dato.setFecha_ini(dFechaini1);
        dato.setFecha_fin(dFechafin1);
        dato.setId_estado("R");
        dato.setNombres("%");
        dato.setCod_esta(cliente.getCod_esta());
        dato.setId_farmacia(cliente.getId_farmacia());
        // lista de pacientes que no terminaron su receta
        List listarSinPago = this.mi.getListaMedRecibidos(dato);
        modelo.put("listapago", listarSinPago);

        Medicamentos medid = new Medicamentos();
        medid.setCod_esta(cliente.getCod_esta());
        List listarprog = this.mi.getListarProgramas(medid);
        modelo.put("listarProg", listarprog);

        if ("mostrar".equals(accion) || "imprimir".equals(accion)) {
            String id_pedido = request.getParameter("id_pedido");
            Pacientes paciente1 = new Pacientes();
            paciente1.setId_pedido(Integer.parseInt(id_pedido));
            paciente1.setCod_esta(cliente.getCod_esta());
            Pacientes buscarPaciente = this.mi.getDatosPedido(paciente1);
            modelo.put("datos", buscarPaciente);

            // recupera los medicamentos del paciente a entregar       
            Recetas midato = new Recetas();
            midato.setId_pedido(Integer.parseInt(id_pedido));
            midato.setId_prestacion(cliente.getCod_esta());
            midato.setCod_esta(cliente.getCod_esta());
            midato.setId_farmacia(cliente.getId_farmacia());
            midato.setExpedido("A");
            List listarKardex = this.mi.getListarKardex(midato);
            modelo.put("listarKardex", listarKardex);

            if ("imprimir".equals(accion)) {
                modelo.put("dato", cliente);
                modelo.put("persona", persona);
                modelo.put("dFechafin1", sFecha_fin);

                return new ModelAndView(new VerRecibidos2PDF(), modelo);
            }
        }
        
        if ("receta".equals(accion)) {
            String id_pedido = request.getParameter("id_pedido");
            String id_historial = request.getParameter("id_historial");

            Historiales datohi = new Historiales();
            datohi.setId_historial(Integer.parseInt(id_historial));
            datohi.setCod_esta(cliente.getCod_esta());
            Historiales datoHis = this.mi.getDatosHistorial(datohi);
            modelo.put("datosHis", datoHis);

            Prestaciones prestac = new Prestaciones();
            prestac.setId_historial(Integer.parseInt(id_historial));
            prestac.setId_persona(datoHis.getId_persona());
            prestac.setCod_esta(cliente.getCod_esta());
            List listarPre = this.mi.getListarSumiRecetas(prestac);
            modelo.put("listarPres", listarPre);

            Personas buscarEmpleado = this.mi.getDatosPersona(datoHis.getId_persona());
            modelo.put("datosMed", buscarEmpleado);

            Pacientes buscarPac = this.mi.getDatosPaciente(datoHis.getId_paciente());
            modelo.put("datosPac", buscarPac);

            Recetas midato = new Recetas();
            midato.setId_historial(Integer.parseInt(id_historial));
            midato.setCod_esta(cliente.getCod_esta());
            List listarRecetas = this.mi.getListarRecetasMedico(midato);
            modelo.put("listarRecetas", listarRecetas);

            Pacientes paciente1 = new Pacientes();
            paciente1.setId_pedido(Integer.parseInt(id_pedido));
            paciente1.setCod_esta(cliente.getCod_esta());
            Pacientes buscarPaciente = this.mi.getDatosPedido(paciente1);
            modelo.put("datos", buscarPaciente);

            // recupera los medicamentos del paciente a entregar   
            ///Recetas midato = new Recetas();
            midato.setId_pedido(Integer.parseInt(id_pedido));
            midato.setId_prestacion(cliente.getCod_esta());
            midato.setCod_esta(cliente.getCod_esta());
            midato.setId_farmacia(cliente.getId_farmacia());
            List listarKardex = this.mi.getListarKardex(midato);
            modelo.put("listarKardex", listarKardex);

            List listarSeguros = this.mi.getListarSeguros("A");
            modelo.put("listarSeguros", listarSeguros);

            modelo.put("dato", cliente);

            return new ModelAndView(new ListarRecetaPDF(), modelo);
        }

        modelo.put("valor_1", sFecha_ini);
        modelo.put("valor_2", sFecha_fin);
        modelo.put("dato", cliente);

        return new ModelAndView("administrarmedicamentos/VerRecibidos", modelo);

    }
}
