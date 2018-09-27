package org.ayaic.web.administrarfarmacias;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Historiales;
import org.ayaic.domain.Localidades;
import org.ayaic.domain.Medicamentos;
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.Recetas;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ListaNRecetaIControlador {

    private final MiFacade mi;

    public ListaNRecetaIControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/ListaNRecetaI.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();
        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String id_pedido = request.getParameter("id_pedido");
        String id_medicamento = request.getParameter("id_medicamento");
        String nombremed = request.getParameter("nombremed");
        String sw = request.getParameter("sw");
        String ip = request.getRemoteAddr();
        String host = request.getRemoteHost();
        Date Fecha1 = new Date();
        modelo.put("sw", sw);
        modelo.put("nombremed", nombremed);
        modelo.put("id_programa", "4");

        Localidades local = new Localidades();
        List Estab1 = this.mi.getListarEsta(local);
        Localidades datoestab = (Localidades) Estab1.get(0);
        modelo.put("cod_esta", Integer.toString(datoestab.getCod_esta()));

        double total;
        if ("Imprimir".equals(accion)) {
            Recetas midato = new Recetas();
            midato.setId_pedido(Integer.parseInt(id_pedido));
            midato.setId_prestacion(cliente.getCod_esta());
            midato.setCod_esta(cliente.getCod_esta());
            midato.setId_farmacia(cliente.getId_farmacia());
            List listarKardexImp = this.mi.getListarKardex(midato);
            modelo.put("listarKardex", listarKardexImp);
            Pacientes paciente1 = new Pacientes();
            paciente1.setId_pedido(Integer.parseInt(id_pedido));
            paciente1.setCod_esta(cliente.getCod_esta());
            Pacientes paciente = this.mi.getDatosPedido(paciente1); //////////////
            modelo.put("datos", paciente);
            modelo.put("dato", cliente);
            return new ModelAndView(new ListarOrdenPDF(), modelo);
        }
        if ("entregarya".equals(accion)) {

            String nombres = request.getParameter("nombres");
            String num_cladoc = request.getParameter("num_cladoc");
            String id_historial = request.getParameter("id_historial");
            Pacientes paciente = new Pacientes();
            paciente.setCod_esta(cliente.getCod_esta());
            paciente.setNombres(nombres);
            paciente.setPrecio_total(0);
            paciente.setNum_cladoc(Long.toString(this.mi.getNumClaDoc(paciente)));
            paciente.setId_paciente(Integer.parseInt(id_historial));
            paciente.setId_carpeta(Integer.parseInt(id_historial));
            paciente.setNit("");
            paciente.setCadena1("E");
            //paciente.setId_costo(0);
            paciente.setId_dispensa(cliente.getId_persona());
            paciente.setId_persona(cliente.getId_persona());
            if ("V".equals(sw)) {
                paciente.setNum_cladoc("0");
                paciente.setId_rubro(1);
                paciente.setId_estado("X");
            } else if ("S".equals(sw)) {
                paciente.setId_rubro(1);
                paciente.setId_estado("S");
            } else {
                paciente.setId_rubro(1);
                paciente.setId_estado("P");
            }
            paciente.setTipo("B");
            paciente.setCod_esta(cliente.getCod_esta());
            paciente.setId_factura(this.mi.getNumReceta(paciente));/////0 sin factura, 1 con factura, 2, 3, 4, para num correlativo, entradas, ajuste+ y ajuste-
            paciente.setFec_registro(Fecha1);
            this.mi.setCrearPeedido(paciente);
            id_pedido = Integer.toString(this.mi.getNumPedido(paciente));
        }

        if ("adicion".equals(accion)) {
            String cantidad = request.getParameter("cantidad");
            String precio = request.getParameter("precio");
            String id_programa = request.getParameter("id_programa");

            Medicamentos medic = new Medicamentos();
            medic.setId_medicamento(Integer.parseInt(id_medicamento));
            medic.setCodigo(cliente.getCod_esta());
            medic.setCod_esta(cliente.getCod_esta());
            medic.setId_farmacia(cliente.getId_farmacia());
            medic.setExpedido("B");    /////getDatosMedicamentoB
            Medicamentos buscarMedicamento = this.mi.getDatosMedicamentoB(medic);

            if (cantidad == null) {
                return new ModelAndView("Aviso", "mensaje", "El stock NO es correcta, no existe Saldo Suficiente");
            }
            if (precio == null) {
                precio = Double.toString(buscarMedicamento.getCosto_unit());
            }
            Recetas dato = new Recetas();
            dato.setId_pedido(Integer.parseInt(id_pedido));
            dato.setId_medicamento(Integer.parseInt(id_medicamento));
            dato.setSalida(Double.parseDouble(cantidad));
            dato.setEntrada(0);
            dato.setCosto_unit(buscarMedicamento.getCosto_unit());
            dato.setPrecio_venta(buscarMedicamento.getPrecio_venta());
            if (Double.parseDouble(precio) < buscarMedicamento.getCosto_unit()) {
                dato.setPrecio_venta(buscarMedicamento.getCosto_unit());
            } else {
                dato.setPrecio_venta(Double.parseDouble(precio));
            }
            dato.setNro_lote(buscarMedicamento.getNro_lote());
            dato.setFecha_ven(buscarMedicamento.getFecha_ven());
            if ("V".equals(sw)) {
                dato.setExpedido("V");
                dato.setId_programa(0);
                buscarMedicamento.setStockv(buscarMedicamento.getStockv() - dato.getSalida());
            } else if ("S".equals(sw)) {
                dato.setExpedido("S");
                dato.setId_programa(1);
                buscarMedicamento.setStocks(buscarMedicamento.getStocks() - dato.getSalida());
                dato.setId_programa(Integer.parseInt(id_programa));////20-01-2014
            } else {
                dato.setExpedido("P");
                dato.setId_programa(3);
                buscarMedicamento.setStockp(buscarMedicamento.getStockp() - dato.getSalida());
                dato.setId_programa(Integer.parseInt(id_programa));////20-01-2014
            }
            // entregamos el medicamento
            dato.setFecha(Fecha1);
            dato.setCod_esta(cliente.getCod_esta());
            dato.setId_farmacia(cliente.getId_farmacia());
            dato.setId_persona(cliente.getId_persona());
            dato.setId_factura(0);
            dato.setId_historial(0);
            dato.setId_historia(0);
            dato.setCadena1(ip);///01/03/2016
            dato.setCadena2(host);///01/03/2016
            //this.mi.setCrearKardex(dato);   
            int iResultado = this.mi.setRegistrarKardex(dato);
            // acutalizamos el stock del medicamento
//            buscarMedicamento.setCod_esta(cliente.getCod_esta());
//            buscarMedicamento.setStock(buscarMedicamento.getStock() - dato.getSalida());
//            this.mi.setModificarMedicamentoStock(buscarMedicamento);
            modelo.put("id_programa", id_programa);
        }
        if ("eliminar".equals(accion)) {
            String cantidad = request.getParameter("salida");
            String expedido = request.getParameter("expedido");
            String id_kardex = request.getParameter("id_kardex");

            Medicamentos medic = new Medicamentos();
            medic.setId_medicamento(Integer.parseInt(id_medicamento));
            medic.setCodigo(cliente.getCod_esta());
            medic.setCod_esta(cliente.getCod_esta());
            medic.setId_farmacia(cliente.getId_farmacia());
            medic.setExpedido("B");    /////getDatosMedicamentoB
            Medicamentos buscarMedicamento = this.mi.getDatosMedicamentoB(medic);

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
            } else if ("S".equals(expedido)) {
                buscarMedicamento.setStocks(buscarMedicamento.getStocks() + dato.getSalida());
            } else {
                buscarMedicamento.setStockp(buscarMedicamento.getStockp() + dato.getSalida());
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
            if ("V".equals(sw)) {
                paciente.setId_estado("A");
            } else if ("S".equals(sw)) {
                paciente.setId_estado("S");
            } else {
                paciente.setId_estado("P");
            }
            paciente.setFecha_fin(Fecha1);
            this.mi.setModificarPedido(paciente);

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

            if (listarAtendidos.size() > 0) {
                modelo.put("listafar", "1");
            }
            if (listarPaises.size() > 0) {
                modelo.put("listacancelados", "1");
            }
            if (listarSinPago.size() > 0 || listarSinPago1.size() > 0) {
                modelo.put("listaporcancel", "1");
            }

            return new ModelAndView("administrarfarmacias/ListarAtendidos", modelo);
        }

        Medicamentos dato = new Medicamentos();
        dato.setId_persona(cliente.getId_persona());
        dato.setId_farmacia(cliente.getId_farmacia());
        if ("VENTA".equals(sw)) {
            dato.setExpedido("V");
            modelo.put("expedido", "E");
        } else if ("SUMI".equals(sw)) {
            dato.setExpedido("S");
            modelo.put("expedido", "S");
        } else {
            dato.setExpedido("P");
            modelo.put("expedido", "P");
        }
        List listarMedicamentosCot = this.mi.getListarMedicamentosCot(dato);
        modelo.put("listarMedicamentosCot", listarMedicamentosCot);

        nombremed = "%" + nombremed + "%";
        dato.setCod_esta(cliente.getCod_esta());
        dato.setMedicamento(nombremed);
        List listarMedicamentos = this.mi.getListarMedicamentos(dato);
        modelo.put("listarMedicamentos", listarMedicamentos);

        Recetas midato = new Recetas();
        midato.setId_pedido(Integer.parseInt(id_pedido));
        midato.setId_prestacion(cliente.getCod_esta());
        midato.setCod_esta(cliente.getCod_esta());
        midato.setId_farmacia(cliente.getId_farmacia());
        List listarKardex = this.mi.getListarKardex(midato);
        modelo.put("listarKardex", listarKardex);

        List listarprog = this.mi.getListarProgramas(dato);
        modelo.put("listarProg", listarprog);
        // Calculamos el total a pagar
        total = 0;
        for (int i = 0; i < listarKardex.size(); i++) {
            midato = (Recetas) listarKardex.get(i);
            total = total + midato.getPrecio_total();
        }
        // actualiza el precio total
        Pacientes paciente1 = new Pacientes();
        paciente1.setId_pedido(Integer.parseInt(id_pedido));
        paciente1.setCod_esta(cliente.getCod_esta());
        Pacientes paciente = this.mi.getDatosPedido(paciente1); //////////////
        paciente.setPrecio_total(total);
        paciente.setFecha_fin(Fecha1);
        this.mi.setModificarPedido(paciente);
        modelo.put("datos", paciente);

        modelo.put("id_pedido", id_pedido);

        return new ModelAndView("administrarfarmacias/ListaNReceta", modelo);
    }
}
