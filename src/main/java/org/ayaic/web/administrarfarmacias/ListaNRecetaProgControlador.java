package org.ayaic.web.administrarfarmacias;

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
public class ListaNRecetaProgControlador {

    private final MiFacade mi;

    public ListaNRecetaProgControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/ListaNRecetaP.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();
        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String id_pedido = request.getParameter("id_pedido");
        String id_medicamento = request.getParameter("id_medicamento");
        String nombremed = request.getParameter("nombremed");
        String id_programa = request.getParameter("id_programa");
        String programa = request.getParameter("programa");
        String valor_1 = request.getParameter("valor_1");
        String sw = request.getParameter("sw");
        String ip = request.getRemoteAddr();
        String host = request.getRemoteHost();
        Date Fecha1 = new Date();
        Date fecha1 = new Date();
        modelo.put("sw", sw);
        modelo.put("nombremed", nombremed);

        if (valor_1 == null) {
            valor_1 = (Integer.toString(Fecha1.getYear() + 1900)) + "-" + (Fecha1.getMonth() + 1) + "-" + (Fecha1.getDate());
        } else {
            String[] Fechaini = valor_1.split("-");
            int iAnio1 = Integer.parseInt(Fechaini[0]) - 1900;
            int iMes1 = Integer.parseInt(Fechaini[1]) - 1;
            int iDia1 = Integer.parseInt(Fechaini[2]);
            int ihora1 = Fecha1.getHours();
            int iminuto1 = Fecha1.getMinutes();
            int isegundo1 = Fecha1.getSeconds();
            Fecha1 = new Date(iAnio1, iMes1, iDia1, ihora1, iminuto1, isegundo1);
        }

        double total;
        //modelo.put("id_programa", "4");
        if ("".equals(id_programa) || id_programa == null) {
            id_programa = "0";
        }
        Medicamentos medid = new Medicamentos();
        medid.setCod_esta(cliente.getCod_esta());
        List listarprog = this.mi.getListarProgramas(medid);
        modelo.put("listarProg", listarprog);
        medid.setExpedido("D");
        medid.setId_programa(Integer.parseInt(id_programa));
        List datoprog = this.mi.getListarProgramas(medid);
        Medicamentos datop = (Medicamentos) datoprog.get(0);
        modelo.put("programa", datop.getUbicacion());
        modelo.put("id_programa", id_programa);

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
            Pacientes paciente = this.mi.getDatosPedido(paciente1);
            modelo.put("datos", paciente);
            modelo.put("dato", cliente);
            return new ModelAndView(new ListarOrdenPDF(), modelo);
        }

        if ("entregarya".equals(accion)) {

            String nombres = request.getParameter("nombres");
            String num_cladoc = request.getParameter("num_cladoc");

            long fechaInicial = Fecha1.getTime();
            long fechaFinal = fecha1.getTime();
            long diferencia =  (fechaFinal - fechaInicial)+50;
            double diass = Math.floor(diferencia / (1000 * 60 * 60 * 24));
            if (fechaInicial > fechaFinal) {
                return new ModelAndView("Aviso", "mensaje", "La fecha que desea ingresar NO es correcta, esta adelantada a HOY");
            }
            if (diass > 90) {
                return new ModelAndView("Aviso", "mensaje", "La fecha que desea ingresar, NO puede ser mayor a 90 dias antes");
            }
            Pacientes paciente = new Pacientes();
            paciente.setNombres(nombres);
            paciente.setPrecio_total(0);
            paciente.setNum_cladoc(num_cladoc);
            paciente.setId_paciente(0);
            paciente.setId_carpeta(0);
            paciente.setNit(num_cladoc);
            paciente.setCadena1("E");
            paciente.setId_dispensa(cliente.getId_persona());
            paciente.setId_persona(cliente.getId_persona());
            if ("SSPAM".equals(sw)) {
                paciente.setNum_cladoc("0");
                paciente.setId_rubro(1);
                paciente.setId_estado("P");
            }
            paciente.setTipo("B");
            paciente.setId_pais(Integer.parseInt(id_programa));
            paciente.setCod_esta(cliente.getCod_esta());
            paciente.setId_factura(this.mi.getNumReceta(paciente));/////0 sin factura, 1 con factura, 2, 3, 4, para num correlativo, entradas, ajuste+ y ajuste-
            paciente.setFec_registro(fecha1);
            this.mi.setCrearPeedido(paciente);
            id_pedido = Integer.toString(this.mi.getNumPedido(paciente));

            //Medicamentos medid = new Medicamentos();
        }

        if ("adicion".equals(accion)) {
            String cantidad = request.getParameter("cantidad");
            String precio = request.getParameter("precio");

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
            dato.setNro_lote(buscarMedicamento.getNro_lote());
            dato.setFecha_ven(buscarMedicamento.getFecha_ven());
            dato.setId_programa(0);
            if (Double.parseDouble(precio) < buscarMedicamento.getCosto_unit()) {
                dato.setPrecio_venta(buscarMedicamento.getCosto_unit());
            } else {
                dato.setPrecio_venta(Double.parseDouble(precio));
            }
            dato.setNro_lote(buscarMedicamento.getNro_lote());
            dato.setFecha_ven(buscarMedicamento.getFecha_ven());
            if ("SSPAM".equals(sw)) {
                dato.setExpedido("P");
                dato.setId_programa(Integer.parseInt(id_programa));////20-01-2014
                buscarMedicamento.setStockp(buscarMedicamento.getStockp() - dato.getSalida());
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
            Medicamentos medi = new Medicamentos();
            medi.setId_medicamento(Integer.parseInt(id_medicamento));
            medi.setCodigo(cliente.getCod_esta());
            medi.setCod_esta(cliente.getCod_esta());
            medi.setId_farmacia(cliente.getId_farmacia());
            medi.setExpedido("B");    /////getDatosMedicamentoB
            Medicamentos buscarMedicamento = this.mi.getDatosMedicamentoB(medi);

            Recetas dato = new Recetas();
            dato.setId_pedido(Integer.parseInt(id_pedido));
            dato.setId_factura(Integer.parseInt(id_kardex));
            dato.setId_medicamento(Integer.parseInt(id_medicamento));
            dato.setSalida(Double.parseDouble(cantidad));
            // quitamos el medicamento
            dato.setCod_esta(cliente.getCod_esta());
            this.mi.setEliminarKardex(dato);
            // acutalizamos el stock del medicamento
            if ("P".equals(expedido)) {
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
            Pacientes paciente = this.mi.getDatosPedido(paciente1);
            paciente.setFecha_ini(Fecha1);
            if ("SSPAM".equals(sw)) {
                paciente.setId_estado("P");
            }
            paciente.setFecha_fin(Fecha1);
            this.mi.setModificarPedidoAnt(paciente);

            //lista de paciente que fueron atendidos con el medico
            Historiales dato = new Historiales();
            dato.setId_estado("A");
            dato.setExpedido("P");
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

            return new ModelAndView("administrarfarmacias/ListarAtendidosProg", modelo);
        }
        Medicamentos dato = new Medicamentos();
        dato.setId_farmacia(cliente.getId_farmacia());
        dato.setId_persona(cliente.getId_persona());
        //if("SSPAM".equals(sw)){    borrado 09/07/2016 
        dato.setExpedido("P");
        modelo.put("expedido", "P");
        //}

        List listarMedicamentosCot = this.mi.getListarMedicamentosCot(dato);
        modelo.put("listarMedicamentosCot", listarMedicamentosCot);

        nombremed = "%" + nombremed + "%";
        dato.setCod_esta(cliente.getCod_esta());
        dato.setMedicamento(nombremed);
        dato.setExpedido("W");  /////01-07-2016 no funciona HG
        List listarMedicamentos = this.mi.getListarMedicamentos(dato);
        modelo.put("listarMedicamentos", listarMedicamentos);

        Recetas midato = new Recetas();
        midato.setId_pedido(Integer.parseInt(id_pedido));
        midato.setId_prestacion(cliente.getCod_esta());
        midato.setCod_esta(cliente.getCod_esta());
        midato.setId_farmacia(cliente.getId_farmacia());
        List listarKardex = this.mi.getListarKardex(midato);
        modelo.put("listarKardex", listarKardex);

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
        Pacientes paciente = this.mi.getDatosPedido(paciente1);
        paciente.setPrecio_total(total);
        paciente.setFecha_ini(Fecha1);
        this.mi.setModificarPedidoAnt(paciente);
        modelo.put("datos", paciente);
        modelo.put("valor_1", valor_1);
        modelo.put("id_pedido", id_pedido);

        return new ModelAndView("administrarfarmacias/ListaNRecetaP", modelo);
    }
}
