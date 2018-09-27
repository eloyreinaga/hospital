package org.ayaic.web.administrarcobranza;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Costos;
import org.ayaic.domain.Cuadernos;
import org.ayaic.domain.Departamentos;
import org.ayaic.domain.Detalle;
import org.ayaic.domain.Localidades;
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CobrarLabControlador {

    private final MiFacade mi;

    public CobrarLabControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/CobrarLab.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String _nombres = cliente.getNombres();
        String accion = request.getParameter("accion");
        String sw = request.getParameter("sw");

        String id_paciente = request.getParameter("id_paciente");
        String nombres = request.getParameter("nombres");
        String carnet = request.getParameter("carnet");
        String id_reservacion = request.getParameter("id_reservacion");
        String id_cuaderno = request.getParameter("id_cuaderno");
        String id_pedidolab = request.getParameter("id_pedidol");
        String id_persona = request.getParameter("id_persona");
        String num_cladoc = request.getParameter("num_cladoc");
        String id_costo = request.getParameter("id_costo");
        String id_rubro = request.getParameter("id_rubro");
        String id_pedido = request.getParameter("id_pedido");
        String cantidad = request.getParameter("cantidad");
        String ip = request.getRemoteAddr();
        String host = request.getRemoteHost();

        String[] dias = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
        String[] meses = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        Date fecha1 = new Date();
        int anioq = fecha1.getYear() + 1900;
        String[] anios = {Integer.toString(anioq), Integer.toString(anioq - 1), Integer.toString(anioq - 2), Integer.toString(anioq - 3), Integer.toString(anioq - 4), Integer.toString(anioq - 5), Integer.toString(anioq - 6), Integer.toString(anioq - 7), Integer.toString(anioq - 8), Integer.toString(anioq - 9), Integer.toString(anioq - 10), Integer.toString(anioq - 11), Integer.toString(anioq - 12), Integer.toString(anioq - 13), Integer.toString(anioq - 14), Integer.toString(anioq - 15), Integer.toString(anioq - 16), Integer.toString(anioq - 17), Integer.toString(anioq - 18), Integer.toString(anioq - 19), Integer.toString(anioq - 20), Integer.toString(anioq - 21), Integer.toString(anioq - 22), Integer.toString(anioq - 23), Integer.toString(anioq - 24), Integer.toString(anioq - 25), Integer.toString(anioq - 26), Integer.toString(anioq - 27), Integer.toString(anioq - 28), Integer.toString(anioq - 29), Integer.toString(anioq - 30), Integer.toString(anioq - 31), Integer.toString(anioq - 32), Integer.toString(anioq - 33), Integer.toString(anioq - 34), Integer.toString(anioq - 35), Integer.toString(anioq - 36), Integer.toString(anioq - 37), Integer.toString(anioq - 38), Integer.toString(anioq - 39), Integer.toString(anioq - 40), Integer.toString(anioq - 41), Integer.toString(anioq - 42), Integer.toString(anioq - 43), Integer.toString(anioq - 44), Integer.toString(anioq - 45), Integer.toString(anioq - 46), Integer.toString(anioq - 47), Integer.toString(anioq - 48), Integer.toString(anioq - 49), Integer.toString(anioq - 50), Integer.toString(anioq - 51), Integer.toString(anioq - 52), Integer.toString(anioq - 53), Integer.toString(anioq - 54), Integer.toString(anioq - 55), Integer.toString(anioq - 56), Integer.toString(anioq - 57), Integer.toString(anioq - 58), Integer.toString(anioq - 59), Integer.toString(anioq - 60), Integer.toString(anioq - 61), Integer.toString(anioq - 62), Integer.toString(anioq - 63), Integer.toString(anioq - 64), Integer.toString(anioq - 65), Integer.toString(anioq - 66), Integer.toString(anioq - 67), Integer.toString(anioq - 68), Integer.toString(anioq - 69), Integer.toString(anioq - 70), Integer.toString(anioq - 71), Integer.toString(anioq - 72), Integer.toString(anioq - 73), Integer.toString(anioq - 74), Integer.toString(anioq - 75), Integer.toString(anioq - 76), Integer.toString(anioq - 77), Integer.toString(anioq - 78), Integer.toString(anioq - 79), Integer.toString(anioq - 80), Integer.toString(anioq - 81), Integer.toString(anioq - 82), Integer.toString(anioq - 83), Integer.toString(anioq - 84), Integer.toString(anioq - 85), Integer.toString(anioq - 86), Integer.toString(anioq - 87), Integer.toString(anioq - 88), Integer.toString(anioq - 89), Integer.toString(anioq - 90), Integer.toString(anioq - 91), Integer.toString(anioq - 92), Integer.toString(anioq - 93), Integer.toString(anioq - 94), Integer.toString(anioq - 95), Integer.toString(anioq - 96), Integer.toString(anioq - 97), Integer.toString(anioq - 98), Integer.toString(anioq - 99), Integer.toString(anioq - 100), Integer.toString(anioq - 101), Integer.toString(anioq - 102), Integer.toString(anioq - 103), Integer.toString(anioq - 104), Integer.toString(anioq - 105), Integer.toString(anioq - 106), Integer.toString(anioq - 107), Integer.toString(anioq - 108), Integer.toString(anioq - 109), Integer.toString(anioq - 110)};
        double total = 0;

        Localidades local = new Localidades();
        List Estab1 = this.mi.getListarEsta(local);
        Localidades datoestab = (Localidades) Estab1.get(0);

        Pacientes buscarPaciente = new Pacientes();
        buscarPaciente = this.mi.getDatosPaciente(Integer.parseInt(id_paciente)); // saca un registro a ser modificado

        modelo.put("nombres", buscarPaciente.getNombres());
        modelo.put("id_paciente", id_paciente);
        modelo.put("id_persona", id_persona);
        modelo.put("id_pedidolab", id_pedidolab);
        modelo.put("id_reservacion", id_reservacion);

        if ("CobroLab".equals(accion)) {
            Cuadernos dato = new Cuadernos();
            dato.setId_historial(Integer.parseInt(id_reservacion));
            dato.setCod_esta(cliente.getCod_esta());
            List listalabcob = this.mi.getDatosLaboratorios(dato);
            modelo.put("listalabcob", listalabcob);
        }
        if ("Retornar".equals(accion)) {
            String id_pedidola = request.getParameter("id_pedidolab");
            String sumatot1 = request.getParameter("precio");
            Cuadernos datol = new Cuadernos();
            datol.setId_pedido(Integer.parseInt(id_pedidola));
            datol.setAccion("L");
            datol.setEstado("A");
            datol.setCod_esta(cliente.getCod_esta());
            this.mi.setModificarPedidoLabo(datol); ////setModificarPedidoLab

            Pacientes paciente = new Pacientes();
            paciente.setId_estado("A");
            paciente.setId_rubro(5);
            paciente.setId_localidad(cliente.getCod_esta());
            List listarenfer = this.mi.getListarCobroRubro(paciente);
            modelo.put("listaEnfer", listarenfer);
            paciente.setId_rubro(3);
            List listarO = this.mi.getListarCobroRubroOdon(paciente);
            modelo.put("listaOdon", listarO);

            Detalle datodet = new Detalle();
            datodet.setId_pedido(Integer.parseInt(id_pedido));
            datodet.setCod_esta(cliente.getCod_esta());
            List listarcobros = this.mi.getListarCobroDetalle(datodet);
            modelo.put("costo", listarcobros);
            Detalle midato = new Detalle();
            total = 0;
            for (int i = 0; i < listarcobros.size(); i++) {
                midato = (Detalle) listarcobros.get(i);
                total = total + midato.getEntrada();
            }

            Pacientes datojef = mi.getDatosPaciente(Integer.parseInt(id_paciente));
            modelo.put("persona", datojef);

            //Cuadernos datol= new Cuadernos();
            datol.setTipoconsulta("A");
            datol.setCod_esta(cliente.getCod_esta());////19-12-2016
            datol.setSeleccion(cliente.getId_almacen());
            if (cliente.getId_laboratorio() > 0) {
                datol.setId_cargo(cliente.getId_laboratorio());   ////12-06-2015
                datol.setId_laboratorio(cliente.getId_laboratorio());   ////12-06-2015 
            } else {
                datol.setId_cargo(0);   ////12-06-2015
                datol.setId_laboratorio(999);   ////12-06-2015 
            }
            List listalab = this.mi.getLabPacPendientePago(datol);
            modelo.put("listalab", listalab);

            if (listarenfer.size() > 0) {
                modelo.put("listaenfermeria", "1");
            }
            if (listarO.size() > 0) {
                modelo.put("listaodonto", "1");
            }
            if (listalab.size() > 0) {
                modelo.put("listalabo", "1");
            }
            modelo.put("anio", Integer.toString(fecha1.getYear() + 1900));
            modelo.put("mes", Integer.toString(fecha1.getMonth() + 1));
            modelo.put("dia", Integer.toString(fecha1.getDate()));
            modelo.put("anios", anios);
            modelo.put("meses", meses);
            modelo.put("dias", dias);

            Pacientes paciente1 = new Pacientes();
            paciente1.setId_pedido(Integer.parseInt(id_pedido));
            paciente1.setCod_esta(cliente.getCod_esta());
            Pacientes pedido = this.mi.getDatosPedido(paciente1); ////////////// 
            modelo.put("pedido", pedido);

            Departamentos buscardepartamento = this.mi.getDatosDepartamento(datoestab.getId_departamento()); // saca un registro a ser modificado
            modelo.put("departamento", buscardepartamento);
            modelo.put("rubrocosto", Double.toString(total));
            modelo.put("estab", Estab1);
            modelo.put("dato", cliente);

            if (datoestab.getId_pais() == 1) {
                return new ModelAndView(new ReciboPDF(), modelo);
            } else {
                return new ModelAndView("administrarcobranza/ListarCobroReserva", modelo);
            }
        }
        if ("eliminar".equals(accion)) {
            String id_detalle = request.getParameter("id_detalle");
            Costos costo = new Costos();
            costo.setId_costo(Integer.parseInt(id_costo));
            Costos buscarCosto = this.mi.getDatosCosto(costo);
            Detalle dato = new Detalle();
            dato.setId_detalle(Integer.parseInt(id_detalle));
            dato.setId_pedido(Integer.parseInt(id_pedido));
            dato.setId_costo(Integer.parseInt(id_costo));
            dato.setId_carpeta(Integer.parseInt(id_reservacion));
            dato.setId_dispensa(cliente.getId_persona());
            dato.setCadena1(ip);
            dato.setCadena2(host);
            dato.setExpedido("L");
            this.mi.setEliminarDetalleLab(dato);
            Cuadernos datoc = new Cuadernos();
            datoc.setId_historial(Integer.parseInt(id_reservacion));
            datoc.setAccion("N");
            datoc.setId_estado("G");
            datoc.setId_costo(Integer.parseInt(id_costo));
            datoc.setCod_esta(cliente.getCod_esta());
            this.mi.setModificarLaboratorioCobrar(datoc);

            Detalle datodet = new Detalle();
            datodet.setId_pedido(Integer.parseInt(id_pedido));
            datodet.setCod_esta(cliente.getCod_esta());
            List listarcobros = this.mi.getListarCobroDetalle(datodet);
            modelo.put("listarcobros", listarcobros);
            Detalle midato = new Detalle();
            total = 0;
            for (int i = 0; i < listarcobros.size(); i++) {
                midato = (Detalle) listarcobros.get(i);
                total = total + midato.getEntrada();
            }
            Pacientes paciente1 = new Pacientes();
            paciente1.setId_pedido(Integer.parseInt(id_pedido));
            paciente1.setCod_esta(cliente.getCod_esta());
            Pacientes paciente = this.mi.getDatosPedido(paciente1); //////////////
            paciente.setPrecio_total(total);
            paciente.setId_rubro(Integer.parseInt(id_rubro));
            paciente.setId_persona(cliente.getId_persona());
            paciente.setFecha_fin(fecha1);
            this.mi.setModificarPedido(paciente);
            modelo.put("datos", paciente);
            Cuadernos datol = new Cuadernos();
            datol.setId_historial(Integer.parseInt(id_reservacion));
            datol.setCod_esta(cliente.getCod_esta());
            List listalab = this.mi.getDatosLaboratorios(datol);
            modelo.put("listalab", listalab);
            modelo.put("id_pedido", id_pedido);
        }

        if ("Cobrar".equals(accion)) {
            //String id_cuaderno    = request.getParameter("id_costo");
            buscarPaciente.setCod_esta(cliente.getCod_esta());
            num_cladoc = Long.toString(this.mi.getNumClaDoc(buscarPaciente));

            if (id_pedido == null || "".equals(id_pedido)) {
                Pacientes dato = new Pacientes();
                dato.setNombres(nombres);
                dato.setPrecio_total(Float.parseFloat(cantidad));
                dato.setId_estado("E");
                dato.setNum_cladoc(num_cladoc);
                dato.setNit(buscarPaciente.getCarnet());
                //dato.setId_costo(Integer.parseInt(id_costo));
                dato.setId_rubro(Integer.parseInt(id_rubro));
                dato.setId_persona(buscarPaciente.getId_persona());
                dato.setId_dispensa(cliente.getId_persona());
                dato.setId_carpeta(Integer.parseInt(id_reservacion));
                dato.setId_paciente(Integer.parseInt(id_paciente));
                dato.setTipo("C");
                dato.setId_factura(0);/////0 sin factura, 1 con factura, 2, 3, 4, para num correlativo, entradas, ajuste+ y ajuste-
                dato.setFec_registro(fecha1);
                dato.setCod_esta(cliente.getCod_esta());
                this.mi.setCrearPeedido(dato);
                id_pedido = Integer.toString(this.mi.getNumPedido(dato));
            }

            Detalle detalle = new Detalle();
            detalle.setId_costo(Integer.parseInt(id_costo));
            detalle.setId_pedido(Integer.parseInt(id_pedido));
            detalle.setEntrada(Float.parseFloat(cantidad));
            detalle.setSalida(Float.parseFloat(cantidad));
            detalle.setId_rubro(Integer.parseInt(id_rubro));
            detalle.setUlt_usuario(cliente.getId_persona());
            detalle.setIndicacion("");
            detalle.setCod_esta(cliente.getCod_esta());
            this.mi.setCrearDetalle(detalle);
            Cuadernos datoc = new Cuadernos();
            datoc.setId_historial(Integer.parseInt(id_reservacion));
            datoc.setId_cuaderno(Integer.parseInt(id_cuaderno));
            datoc.setAccion("N");
            datoc.setId_estado("A");
            Costos costo = new Costos();
            costo.setId_costo(Integer.parseInt(id_costo));
            costo.setCod_esta(cliente.getCod_esta());
            Costos buscarCosto = this.mi.getDatosCosto(costo);
            if (buscarCosto.getId_paciente() == 12) {
                datoc.setId_estado("Y");
            }
            if (buscarCosto.getId_paciente() == 13) {
                datoc.setId_estado("X");
            }
            datoc.setId_costo(Integer.parseInt(id_costo));
            datoc.setCod_esta(cliente.getCod_esta());
            datoc.setId_por(cliente.getId_persona());
            this.mi.setModificarLaboratorioC(datoc);
            modelo.put("id_pedido", id_pedido);

            Detalle datodet = new Detalle();
            datodet.setId_pedido(Integer.parseInt(id_pedido));
            datodet.setCod_esta(cliente.getCod_esta());
            List listarcobros = this.mi.getListarCobroDetalle(datodet);
            modelo.put("listarcobros", listarcobros);
            Detalle midato = new Detalle();
            total = 0;
            for (int i = 0; i < listarcobros.size(); i++) {
                midato = (Detalle) listarcobros.get(i);
                total = total + midato.getEntrada();
            }

            Pacientes paciente1 = new Pacientes();
            paciente1.setId_pedido(Integer.parseInt(id_pedido));
            paciente1.setCod_esta(cliente.getCod_esta());
            Pacientes paciente = this.mi.getDatosPedido(paciente1); //////////////
            paciente.setPrecio_total(total);
            paciente.setId_rubro(Integer.parseInt(id_rubro));
            paciente.setId_persona(cliente.getId_persona());
            paciente.setFecha_fin(fecha1);
            this.mi.setModificarPedido(paciente);
            modelo.put("datos", paciente);

            Cuadernos datol = new Cuadernos();
            datol.setId_historial(Integer.parseInt(id_reservacion));
            datol.setCod_esta(cliente.getCod_esta());
            List listalab = this.mi.getDatosLaboratorios(datol);
            modelo.put("listalabcob", listalab);
            modelo.put("id_pedido", id_pedido);
        }

        return new ModelAndView("administrarcobranza/ListaCobroPacienteLab", modelo);
    }
}
