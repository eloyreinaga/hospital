package org.ayaic.web.administrarenfermeria;

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
import org.ayaic.domain.Recetas;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EntregaMedicamentoPacienteControlador {

    private final MiFacade mi;

    public EntregaMedicamentoPacienteControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/EntregaMedicamentoPaciente.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();
        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String id_kardex = request.getParameter("id_kardex");
        String id_pedido = request.getParameter("id_pedido");
        String id_receta = request.getParameter("id_receta");
        String id_medicamento = request.getParameter("id_medicamento");
        String nombremed = request.getParameter("nombremed");
        String ip = request.getRemoteAddr();
        String host = request.getRemoteHost();
        String sw = request.getParameter("sw");
        modelo.put("sw", sw);
        modelo.put("nombremed", nombremed);
        Date Fecha1 = new Date();
        double total;

        if ("entregarya".equals(accion)) {

            String nombres = request.getParameter("nombres");
            String num_cladoc = request.getParameter("num_cladoc");
            Pacientes paciente = new Pacientes();
            paciente.setNombres(nombres);
            paciente.setPrecio_total(0);
            paciente.setNum_cladoc(num_cladoc);
            paciente.setId_paciente(0);
            paciente.setId_persona(cliente.getId_persona());
            paciente.setId_dispensa(cliente.getId_persona());
            paciente.setNit("");

            if ("VENTA".equals(sw)) {
                paciente.setNum_cladoc("0");
                paciente.setId_rubro(1);
                paciente.setId_estado("X");
                //paciente.setId_costo(0);
            } else {
                paciente.setId_rubro(0);
                paciente.setId_estado("S");
                paciente.setId_costo(0);
            }
            paciente.setId_factura(0);/////0 sin factura, 1 con factura, 2, 3, 4, para num correlativo, entradas, ajuste+ y ajuste-
            paciente.setTipo("B");
            paciente.setCadena1("E");
            paciente.setFec_registro(Fecha1);
            paciente.setCod_esta(cliente.getCod_esta());
            this.mi.setCrearPeedido(paciente);
            id_pedido = Integer.toString(this.mi.getNumPedido(paciente));

        }
        if ("adicion".equals(accion)) {
            String cantidad = request.getParameter("cantidad");
            String id_programa = request.getParameter("id_programa");

            Medicamentos medic = new Medicamentos();
            medic.setId_medicamento(Integer.parseInt(id_medicamento));
            medic.setCodigo(cliente.getCod_esta());
            medic.setCod_esta(cliente.getCod_esta());
            medic.setId_farmacia(cliente.getId_farmacia());
            medic.setExpedido("B");    /////getDatosMedicamentoB
            Medicamentos buscarMedicamento = this.mi.getDatosMedicamentoB(medic);
            Recetas dato = new Recetas();
            dato.setId_pedido(Integer.parseInt(id_pedido));
            dato.setId_medicamento(Integer.parseInt(id_medicamento));
            dato.setSalida(Double.parseDouble(cantidad));
            dato.setEntrada(0);
            dato.setCosto_unit(buscarMedicamento.getCosto_unit());
            dato.setNro_lote(buscarMedicamento.getNro_lote());
            dato.setFecha_ven(buscarMedicamento.getFecha_ven());
            if ("VENTA".equals(sw)) {
                dato.setExpedido("V");
                buscarMedicamento.setStockv(buscarMedicamento.getStockv() - dato.getSalida());
                dato.setPrecio_venta(buscarMedicamento.getPrecio_venta());
                dato.setId_programa(0);
            } else {
                dato.setExpedido("S");
                buscarMedicamento.setStocks(buscarMedicamento.getStocks() - dato.getSalida());
                dato.setPrecio_venta(buscarMedicamento.getPrecio_venta());/// 8/08/2018 se cambio buscarMedicamento.getCosto_unit()
                dato.setId_programa(Integer.parseInt(id_programa));////20-01-2014
            }
            // entregamos el medicamento
            dato.setCod_esta(cliente.getCod_esta());
            dato.setId_farmacia(cliente.getId_farmacia());
            dato.setId_persona(cliente.getId_persona());
            dato.setId_factura(0);
            dato.setId_historial(0);
            dato.setId_historia(0);
            dato.setCadena1(ip);///01/03/2016
            dato.setCadena2(host);///01/03/2016

            //this.mi.setCrearKardex(dato) ;     
            int iResultado = this.mi.setRegistrarKardex(dato);
            // acutalizamos el stock del medicamento
//            buscarMedicamento.setCod_esta(cliente.getCod_esta());
//            buscarMedicamento.setStock(buscarMedicamento.getStock() - dato.getSalida());
//            this.mi.setModificarMedicamentoStock(buscarMedicamento);
        }

        if ("eliminar".equals(accion)) {
            String cantidad = request.getParameter("salida");
            String expedido = request.getParameter("expedido");

            Medicamentos medic = new Medicamentos();
            medic.setId_medicamento(Integer.parseInt(id_medicamento));
            medic.setCodigo(cliente.getCod_esta());
            medic.setCod_esta(cliente.getCod_esta());
            medic.setId_farmacia(cliente.getId_farmacia());
            Medicamentos buscarMedicamento = this.mi.getDatosMedicamento(medic);

            Recetas dato = new Recetas();
            dato.setId_pedido(Integer.parseInt(id_pedido));
            dato.setId_factura(Integer.parseInt(id_kardex));
            dato.setId_medicamento(Integer.parseInt(id_medicamento));
            dato.setSalida(Double.parseDouble(cantidad));
            // quitamos el medicamento
            dato.setCod_esta(cliente.getCod_esta());
            this.mi.setEliminarKardex(dato);
            // acutalizamos el stock del medicamento
            if ("V".equals(expedido)) {
                buscarMedicamento.setStockv(buscarMedicamento.getStockv() + dato.getSalida());
            } else {
                buscarMedicamento.setStocks(buscarMedicamento.getStocks() + dato.getSalida());
            }

            buscarMedicamento.setCod_esta(cliente.getCod_esta());
            buscarMedicamento.setStock(buscarMedicamento.getStock() + dato.getSalida());
            this.mi.setModificarMedicamentoStock(buscarMedicamento);

        }

        if ("terminar".equals(accion)) {
            Pacientes paciente1 = new Pacientes();
            paciente1.setId_pedido(Integer.parseInt(id_pedido));
            paciente1.setCod_esta(cliente.getCod_esta());
            Pacientes paciente = this.mi.getDatosPedido(paciente1); //////////////
            if ("VENTA".equals(sw)) {
                paciente.setId_estado("E");
            } else {
                paciente.setId_estado("S");
            }

            this.mi.setModificarPedidoAnt(paciente);

            //lista de paciente que fueron atendidos con el medico
            Historiales dato = new Historiales();
            dato.setId_estado("A");
            dato.setExpedido("%");
            dato.setCod_esta(cliente.getCod_esta());
            List listarAtendidos = this.mi.getListarAtendidos(dato);
            modelo.put("listarAtendidos", listarAtendidos);

            // lista de pacientes que aun no pagaron en seccion cobranza
            //Pacientes paciente= new Pacientes() ;
            paciente.setId_estado("A");
            paciente.setId_rubro(1);
            paciente.setId_farmacia(cliente.getId_farmacia());
            paciente.setCod_esta(cliente.getCod_esta());
            List listarSinPago = this.mi.getListarCobroRubroFar(paciente);
            modelo.put("listapago", listarSinPago);

            // lista de pacientes que no terminaron su receta
            paciente.setId_estado("X");
            List listarSinPago1 = this.mi.getListarCobroRubroFar(paciente);
            modelo.put("listapago1", listarSinPago1);

            // lista de pacientes que pagaron en seccion cobranza
            paciente.setId_estado("C");
            List listarPaises = this.mi.getListarCobroRubroFar(paciente);
            modelo.put("milista", listarPaises);

            return new ModelAndView("administrarenfermeria/EntregaMedicamentos", modelo);
        }

        Medicamentos dato = new Medicamentos();
        dato.setId_persona(cliente.getId_persona());
        dato.setId_farmacia(cliente.getId_farmacia());
        if ("VENTA".equals(sw)) {
            dato.setExpedido("V");
        } else if ("SUMI".equals(sw)) {
            dato.setExpedido("S");
        } else {
            dato.setExpedido("P");
        }
        List listarMedicamentosCot = this.mi.getListarMedicamentosCot(dato);
        modelo.put("listarMedicamentosCot", listarMedicamentosCot);

        nombremed = "%" + nombremed + "%";
        dato.setCod_esta(cliente.getCod_esta());
        dato.setMedicamento(nombremed);
        List listarMedicamentos = this.mi.getListarMedicamentos(dato);
        modelo.put("listarMedicamentos", listarMedicamentos);

        Recetas datore = new Recetas();
        datore.setId_historial(Integer.parseInt(id_pedido));////en todos los lugares es id_historial?
        datore.setId_estado("%");
        datore.setId_farmacia(cliente.getId_farmacia());
        datore.setCod_esta(cliente.getCod_esta());
        List ListarRecetas = this.mi.getListarRecetas(datore);
        modelo.put("ListarRecetas", ListarRecetas);

        dato.setCod_esta(cliente.getCod_esta());
        List listarprog = this.mi.getListarProgramas(dato);
        modelo.put("listarProg", listarprog);

        // Calculamos el total a pagar
        Recetas midato = new Recetas();
        total = 0;
        for (int i = 0; i < ListarRecetas.size(); i++) {
            midato = (Recetas) ListarRecetas.get(i);
            total = total + midato.getPrecio_total();
        }
        // actualiza el precio total
        Pacientes paciente1 = new Pacientes();
        paciente1.setId_pedido(Integer.parseInt(id_pedido));
        paciente1.setCod_esta(cliente.getCod_esta());
        Pacientes paciente = this.mi.getDatosPedido(paciente1); //////////////
        paciente.setPrecio_total(total);
        this.mi.setModificarPedido(paciente);
        modelo.put("datos", paciente);

        modelo.put("id_pedido", id_pedido);

        return new ModelAndView("administrarenfermeria/EntregaMedicamentoPaciente", modelo);
    }
}
