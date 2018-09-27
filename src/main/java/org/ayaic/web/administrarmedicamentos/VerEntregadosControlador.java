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
public class VerEntregadosControlador {

    private final MiFacade mi;

    public VerEntregadosControlador(MiFacade mi) {
        this.mi = mi;
    }
    
    @RequestMapping("/VerEntregados.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String inter = request.getParameter("inter");
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
        dato.setId_localidad(cliente.getCod_esta());
        dato.setCod_esta(cliente.getCod_esta());
        // lista de pacientes que no terminaron su receta
        List listarSinPago = this.mi.getListaMedEntregados(dato);
        modelo.put("listapago", listarSinPago);
        modelo.put("dato", cliente);

        if ("mostrar".equals(accion) || "botiquin".equals(accion)) {
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
            List listarKardex = this.mi.getListarKardex(midato);
            modelo.put("listarKardex", listarKardex);

            Medicamentos medid = new Medicamentos();
            medid.setCod_esta(cliente.getCod_esta());
            List listarprog = this.mi.getListarProgramas(medid);
            modelo.put("listarProg", listarprog);

            if ("botiquin".equals(accion)) {
                return new ModelAndView(new VerBotiquinPDF(), modelo);
            }

        }

        if ("receta".equals(accion)) {
            String id_pedido = request.getParameter("id_pedido");
            String id_historial = request.getParameter("id_historial");
            String id_persona = request.getParameter("id_persona");
            String id_doctor = request.getParameter("id_doctor");
            String swx = request.getParameter("swx");

            Historiales datohi = new Historiales();
            datohi.setId_historial(Integer.parseInt(id_historial));
            datohi.setCod_esta(cliente.getCod_esta());
            Historiales datoHis = this.mi.getDatosHistorial(datohi);
            modelo.put("datosHis", datoHis);

            if ("".equals(id_persona) || id_persona == null) {
                id_persona = Integer.toString(datoHis.getId_persona());
            }

            Personas buscarEmpleado = this.mi.getDatosPersonaInt(Integer.parseInt(id_persona));
            Personas buscarEmpleado2 = this.mi.getDatosPersona(datoHis.getId_persona());

            if (buscarEmpleado == null) {
                modelo.put("datosMed", buscarEmpleado2);
            } else {
                modelo.put("datosMed", buscarEmpleado);
            }

            Pacientes buscarPac = this.mi.getDatosPaciente(datoHis.getId_paciente());
            modelo.put("datosPac", buscarPac);

            List listarSeguros = this.mi.getListarSeguros("A");
            modelo.put("listarSeguros", listarSeguros);

            Prestaciones prestac = new Prestaciones();
            prestac.setId_historial(Integer.parseInt(id_historial));
            prestac.setId_persona(datoHis.getId_persona());
            prestac.setCod_esta(cliente.getCod_esta());
            Recetas midato = new Recetas();
            if ("individual".equals(swx)) {
                prestac.setId_persona(Integer.parseInt(id_persona));/////21-02-2018 se aumenta esta fila
                List listarPre = this.mi.getListarSumiRecetasIntImp(prestac);
                modelo.put("listarPres", listarPre);

                midato.setId_pedido(Integer.parseInt(id_pedido));
                List listarRecetas = this.mi.getListarRecetasIndi(midato);
                modelo.put("listarRecetas", listarRecetas);

                //Recetas midato = new Recetas();
                midato.setId_pedido(Integer.parseInt(id_pedido));
                midato.setId_prestacion(cliente.getCod_esta());
                midato.setCod_esta(cliente.getCod_esta());
                midato.setId_farmacia(cliente.getId_farmacia());
                List listarKardex = this.mi.getListarKardex(midato);
                modelo.put("listarKardex", listarKardex);

                Pacientes paciente1 = new Pacientes();
                paciente1.setId_pedido(Integer.parseInt(id_pedido));
                paciente1.setCod_esta(cliente.getCod_esta());
                Pacientes paciente = this.mi.getDatosPedido(paciente1);
                modelo.put("datos", paciente);
            } else {
                List listarPre = this.mi.getListarSumiRecetasI(prestac);
                modelo.put("listarPres", listarPre);

                midato.setId_historial(Integer.parseInt(id_historial));
                midato.setId_farmacia(cliente.getId_farmacia());
                List listarRecetas = this.mi.getListarRecetasInter(midato);
                modelo.put("listarRecetas", listarRecetas);

                Recetas datoi = new Recetas();
                datoi.setId_historial(Integer.parseInt(id_historial));
                List listarKardex = this.mi.getListarKardexInter(datoi);
                modelo.put("listarKardex", listarKardex);

                //id_pedido=id_historial;
                Pacientes paciente1 = new Pacientes();
                paciente1.setId_historial(Integer.parseInt(id_historial));
                paciente1.setCod_esta(cliente.getCod_esta());
                paciente1.setTipo("H");
                Pacientes paciente = this.mi.getDatosPedidoHisto(paciente1);  /////getDatosPedidoHisto
                modelo.put("datos", paciente);
            }

            modelo.put("dato", cliente);
            if (Integer.parseInt(inter) > 0) {
                return new ModelAndView(new ListarRecetaIntPDF(), modelo);
            } else {
                return new ModelAndView(new ListarRecetaPDF(), modelo);
            }
        }

        modelo.put("valor_1", sFecha_ini);
        modelo.put("valor_2", sFecha_fin);

        return new ModelAndView("administrarmedicamentos/VerEntregados", modelo);

    }
}
