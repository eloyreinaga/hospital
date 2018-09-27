package org.ayaic.web.administrarfarmacias;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Medicamentos;
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.Recetas;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RecepcionMedicamento2Controlador {

    private final MiFacade mi;

    public RecepcionMedicamento2Controlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/RecepcionMedicamento2.do")/////RecepcionMedicamento.do se aumento 2 porque no existe
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String expedido = request.getParameter("expedido");
        String id_pedido = request.getParameter("id_pedido");
        String id_medicamento = request.getParameter("id_medicamento");
        String nombremed = request.getParameter("nombremed");
        String valor_1 = request.getParameter("valor_1");
        String tipo = request.getParameter("tipo");
        String recepcion = "no";
        String[] dias = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
        String[] meses = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        String[] anios = {"2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025"};
        String ip = request.getRemoteAddr();
        String host = request.getRemoteHost();
        Date fecha1 = new Date();

        double total;

        String[] Fechaini = valor_1.split("-");
        int iAnio1 = Integer.parseInt(Fechaini[0]) - 1900;
        int iMes1 = Integer.parseInt(Fechaini[1]) - 1;
        int iDia1 = Integer.parseInt(Fechaini[2]);
        int ihora1 = fecha1.getHours();
        int iminuto1 = fecha1.getMinutes();
        int isegundo1 = fecha1.getSeconds();
        Date Fecha1 = new Date(iAnio1, iMes1, iDia1, ihora1, iminuto1, isegundo1);
        modelo.put("tipodato", tipo);
        modelo.put("id_programa", "4");
        Medicamentos medid = new Medicamentos();
        medid.setCod_esta(cliente.getCod_esta());
        List listarprog = this.mi.getListarProgramas(medid);
        modelo.put("listarProg", listarprog);

        if ("entregarya".equals(accion)) {
            String nombres = request.getParameter("nombres");
            String num_cladoc = request.getParameter("num_cladoc");
            String orden = request.getParameter("orden");

            long fechaInicial = Fecha1.getTime();
            long fechaFinal = fecha1.getTime();
            long diferencia = fechaFinal - fechaInicial;
            double diass = Math.floor(diferencia / (1000 * 60 * 60 * 24));
            if (fechaInicial > fechaFinal) {
                return new ModelAndView("Aviso", "mensaje", "La fecha que desea ingresar NO es correcta, esta adelantada a HOY");
            }
            if (!"C.S. DISP. JESUS DE NAZARETH".equals(cliente.getEstablecimiento())) {
                if (diass > 90) {
                    return new ModelAndView("Aviso", "mensaje", "La fecha que desea ingresar, NO puede ser mayor a 90 dias antes");
                }
            }
            Pacientes paciente = new Pacientes();
            paciente.setNombres(nombres);
            paciente.setId_dispensa(cliente.getId_persona());
            paciente.setNit(orden);
            paciente.setId_paciente(0);
            paciente.setNum_cladoc(num_cladoc);
            paciente.setId_estado("R");
            paciente.setId_rubro(1);
            paciente.setId_factura(0);/////0 sin factura, 1 con factura, 2, 3, 4, para num correlativo, entradas, ajuste+ y ajuste-
            paciente.setTipo("D");
            paciente.setFec_registro(fecha1);
            paciente.setCod_esta(cliente.getCod_esta());
            this.mi.setCrearPeedido(paciente);
            id_pedido = Integer.toString(this.mi.getNumPedido(paciente));
            if ("".equals(id_medicamento) || id_medicamento == null) {
                id_medicamento = "0";
            }
        }
        if ("Aceptar".equals(accion)) {
            String cantidad = request.getParameter("cantidad");
            String costo_unit = request.getParameter("costo_unit");
            String costo_venta = request.getParameter("precio_venta");
            String id_programa = request.getParameter("id_programa");

            String nro_lote = request.getParameter("nro_lote");
            String dia_r = request.getParameter("dia_r");
            String mes_r = request.getParameter("mes_r");
            String anio_r = request.getParameter("anio_r");

            if ("".equals(id_programa) || id_programa == null) {
                id_programa = "0";
            }

            int diax_r = Integer.parseInt(dia_r);
            int mesx_r = Integer.parseInt(mes_r) - 1;
            int aniox_r = Integer.parseInt(anio_r) - 1900;
            Date fec_reg = new Date(aniox_r, mesx_r, diax_r);

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
            dato.setEntrada(0);
            dato.setSalida(0);

            dato.setAjuste(0);
            dato.setNro_lote(nro_lote);
            dato.setFecha_ven(fec_reg);
            if ("".equals(cantidad) || cantidad == null) {
                return new ModelAndView("Aviso", "mensaje", "Coloque un numero valido");
            }
            if ("0".equals(tipo)) {
                dato.setEntrada(Double.parseDouble(cantidad));
            } else {
                dato.setAjuste(Double.parseDouble(tipo) * Double.parseDouble(cantidad));
            }

            dato.setPrecio_venta(Double.parseDouble(costo_venta));
            dato.setCosto_unit(Double.parseDouble(costo_unit));
            dato.setFecha(fecha1);
            dato.setExpedido(expedido);
            dato.setId_programa(Integer.parseInt(id_programa));
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
            if (buscarMedicamento == null) {

            }
            //this.mi.setCrearKardex(dato);        
            int iResultado = this.mi.setRegistrarKardex(dato);
            // acutalizamos el stock del medicamento
            if (buscarMedicamento == null) {
                medic.setExpedido("A");
                Medicamentos buscarMedicamentox = this.mi.getDatosMedicamento(medic);
                buscarMedicamentox.setStock(buscarMedicamentox.getStock() + dato.getEntrada() + dato.getAjuste());
                buscarMedicamentox.setCosto_unit(Double.parseDouble(costo_unit));
                if ("S".equals(expedido) || "P".equals(expedido)) {
                    buscarMedicamentox.setPrecio_venta(buscarMedicamentox.getPrecio_venta());
                } else {
                    buscarMedicamentox.setPrecio_venta(Double.parseDouble(costo_venta));
                }
                buscarMedicamentox.setNro_lote(dato.getNro_lote());
                buscarMedicamentox.setFecha_ven(dato.getFecha_ven());
                buscarMedicamentox.setCodigo(-1);
                if ("0".equals(tipo)) {
                    dato.setEntrada(Double.parseDouble(cantidad));
                } else {
                    dato.setEntrada(Double.parseDouble(tipo) * Double.parseDouble(cantidad));
                }

                if ("V".equals(expedido)) {
                    buscarMedicamentox.setStockv(buscarMedicamentox.getStockv() + dato.getEntrada());
                } else if ("S".equals(expedido)) {
                    buscarMedicamentox.setStocks(buscarMedicamentox.getStocks() + dato.getEntrada());
                } else {
                    buscarMedicamentox.setStockp(buscarMedicamento.getStockp() + dato.getEntrada());
                }
                buscarMedicamentox.setCod_esta(cliente.getCod_esta());
                this.mi.setModificarMedicamentoStock(buscarMedicamentox);
            } else {
                buscarMedicamento.setStock(buscarMedicamento.getStock() + dato.getEntrada() + dato.getAjuste());
                buscarMedicamento.setCosto_unit(Double.parseDouble(costo_unit));
                if ("S".equals(expedido) || "P".equals(expedido)) {
                    buscarMedicamento.setPrecio_venta(buscarMedicamento.getPrecio_venta());
                } else {
                    buscarMedicamento.setPrecio_venta(Double.parseDouble(costo_venta));
                }
                buscarMedicamento.setNro_lote(dato.getNro_lote());
                buscarMedicamento.setFecha_ven(dato.getFecha_ven());
                buscarMedicamento.setCodigo(-1);
                if ("0".equals(tipo)) {
                    dato.setEntrada(Double.parseDouble(cantidad));
                } else {
                    dato.setEntrada(Double.parseDouble(tipo) * Double.parseDouble(cantidad));
                }

                if ("V".equals(expedido)) {
                    buscarMedicamento.setStockv(buscarMedicamento.getStockv() + dato.getEntrada());
                } else if ("S".equals(expedido)) {
                    buscarMedicamento.setStocks(buscarMedicamento.getStocks() + dato.getEntrada());
                } else {
                    buscarMedicamento.setStockp(buscarMedicamento.getStockp() + dato.getEntrada());
                }
                buscarMedicamento.setCod_esta(cliente.getCod_esta());
                this.mi.setModificarMedicamentoStock(buscarMedicamento);
                modelo.put("id_programa", id_programa);
            }
        }
        if ("adicion".equals(accion)) {
            String id_consultorio = request.getParameter("id_consultorio");
            String id_persona = request.getParameter("id_persona");

            Medicamentos medic = new Medicamentos();
            medic.setId_medicamento(Integer.parseInt(id_medicamento));
            medic.setCodigo(cliente.getCod_esta());
            medic.setCod_esta(cliente.getCod_esta());
            medic.setId_farmacia(cliente.getId_farmacia());
            medic.setExpedido("B");    /////getDatosMedicamentoB
            Medicamentos buscarMedicamento = this.mi.getDatosMedicamentoB(medic);
            if (buscarMedicamento == null) {
                medic.setExpedido("A");
                Medicamentos buscarMedicamentox = this.mi.getDatosMedicamento(medic);
                modelo.put("dato", buscarMedicamentox);
                modelo.put("anio_r", Integer.toString(buscarMedicamentox.getFecha_ven().getYear() + 1900));
                modelo.put("mes_r", Integer.toString(buscarMedicamentox.getFecha_ven().getMonth() + 1));
                modelo.put("dia_r", Integer.toString(buscarMedicamentox.getFecha_ven().getDate()));
            } else {
                modelo.put("dato", buscarMedicamento);
                modelo.put("anio_r", Integer.toString(buscarMedicamento.getFecha_ven().getYear() + 1900));
                modelo.put("mes_r", Integer.toString(buscarMedicamento.getFecha_ven().getMonth() + 1));
                modelo.put("dia_r", Integer.toString(buscarMedicamento.getFecha_ven().getDate()));
            }

            modelo.put("id_medicamento", id_medicamento);
            modelo.put("id_pedido", id_pedido);
            modelo.put("id_persona", id_persona);
            modelo.put("id_consultorio", id_consultorio);
            modelo.put("anios", anios);
            modelo.put("meses", meses);
            modelo.put("dias", dias);
            modelo.put("cantidad", "0");
            recepcion = "si";
        }
        if ("P".equals(expedido) && !"Regresar".equals(accion)) {
            String id_consultorio = request.getParameter("id_consultorio");
            String id_persona = request.getParameter("id_persona");
            String cantidad = request.getParameter("cantidad");

            Medicamentos medic = new Medicamentos();
            medic.setId_medicamento(Integer.parseInt(id_medicamento));
            medic.setCodigo(cliente.getCod_esta());
            medic.setCod_esta(cliente.getCod_esta());
            medic.setId_farmacia(cliente.getId_farmacia());
            Medicamentos buscarMedicamento = this.mi.getDatosMedicamento(medic);

            modelo.put("dato", buscarMedicamento);
            modelo.put("id_medicamento", id_medicamento);
            modelo.put("id_pedido", id_pedido);
            modelo.put("id_persona", id_persona);
            modelo.put("id_consultorio", id_consultorio);

            modelo.put("anio_r", Integer.toString(buscarMedicamento.getFecha_ven().getYear() + 1900));
            modelo.put("mes_r", Integer.toString(buscarMedicamento.getFecha_ven().getMonth() + 1));
            modelo.put("dia_r", Integer.toString(buscarMedicamento.getFecha_ven().getDate()));
            modelo.put("anios", anios);
            modelo.put("meses", meses);
            modelo.put("dias", dias);
            recepcion = "si";
            modelo.put("programa", "progra");
            modelo.put("expedido", expedido);
            modelo.put("cantidad", cantidad);

        }
        if (("V".equals(expedido) || "S".equals(expedido)) && !"Regresar".equals(accion)) {
            String id_consultorio = request.getParameter("id_consultorio");
            String id_persona = request.getParameter("id_persona");
            String cantidad = request.getParameter("cantidad");

            Medicamentos medic = new Medicamentos();
            medic.setId_medicamento(Integer.parseInt(id_medicamento));
            medic.setCodigo(cliente.getCod_esta());
            medic.setCod_esta(cliente.getCod_esta());
            medic.setId_farmacia(cliente.getId_farmacia());
            medic.setExpedido("B");    /////getDatosMedicamentoB
            Medicamentos buscarMedicamento = this.mi.getDatosMedicamentoB(medic);
            if (buscarMedicamento == null) {
                medic.setExpedido("A");
                Medicamentos buscarMedicamentox = this.mi.getDatosMedicamento(medic);
                modelo.put("dato", buscarMedicamentox);
                modelo.put("anio_r", Integer.toString(buscarMedicamentox.getFecha_ven().getYear() + 1900));
                modelo.put("mes_r", Integer.toString(buscarMedicamentox.getFecha_ven().getMonth() + 1));
                modelo.put("dia_r", Integer.toString(buscarMedicamentox.getFecha_ven().getDate()));
            } else {
                modelo.put("dato", buscarMedicamento);
                modelo.put("anio_r", Integer.toString(buscarMedicamento.getFecha_ven().getYear() + 1900));
                modelo.put("mes_r", Integer.toString(buscarMedicamento.getFecha_ven().getMonth() + 1));
                modelo.put("dia_r", Integer.toString(buscarMedicamento.getFecha_ven().getDate()));
            }

            modelo.put("id_medicamento", id_medicamento);
            modelo.put("id_pedido", id_pedido);
            modelo.put("id_persona", id_persona);
            modelo.put("id_consultorio", id_consultorio);
            modelo.put("anios", anios);
            modelo.put("meses", meses);
            modelo.put("dias", dias);
            recepcion = "si";
            modelo.put("expedido", expedido);
            modelo.put("cantidad", cantidad);
        }
        if ("eliminar".equals(accion)) {
            String ajuste = request.getParameter("ajuste");
            String cantidad = request.getParameter("entrada");
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
            dato.setId_medicamento(Integer.parseInt(id_medicamento));
            dato.setId_factura(Integer.parseInt(id_kardex));
            dato.setSalida(0);
            dato.setEntrada(Double.parseDouble(cantidad));
            dato.setAjuste(Double.parseDouble(ajuste));
            // quitamos el medicamento
            dato.setCod_esta(cliente.getCod_esta());
            this.mi.setEliminarKardex(dato);
            // acutalizamos el stock del medicamento
            if (buscarMedicamento == null) {
                medic.setExpedido("A");
                Medicamentos buscarMedicamentox = this.mi.getDatosMedicamento(medic);
                buscarMedicamentox.setStock(buscarMedicamentox.getStock() - dato.getEntrada() - dato.getAjuste());
                if (dato.getAjuste() != 0) {
                    dato.setEntrada(dato.getAjuste());
                }

                if ("V".equals(expedido)) {
                    buscarMedicamentox.setStockv(buscarMedicamentox.getStockv() - dato.getEntrada());
                } else if ("S".equals(expedido)) {
                    buscarMedicamentox.setStocks(buscarMedicamentox.getStocks() - dato.getEntrada());
                } else {
                    buscarMedicamentox.setStockp(buscarMedicamentox.getStockp() - dato.getEntrada());
                }

                buscarMedicamentox.setCod_esta(cliente.getCod_esta());
                this.mi.setModificarMedicamentoStock(buscarMedicamentox);
            } else {
                buscarMedicamento.setStock(buscarMedicamento.getStock() - dato.getEntrada() - dato.getAjuste());
                if (dato.getAjuste() != 0) {
                    dato.setEntrada(dato.getAjuste());
                }

                if ("V".equals(expedido)) {
                    buscarMedicamento.setStockv(buscarMedicamento.getStockv() - dato.getEntrada());
                } else if ("S".equals(expedido)) {
                    buscarMedicamento.setStocks(buscarMedicamento.getStocks() - dato.getEntrada());
                } else {
                    buscarMedicamento.setStockp(buscarMedicamento.getStockp() - dato.getEntrada());
                }

                buscarMedicamento.setCod_esta(cliente.getCod_esta());
                this.mi.setModificarMedicamentoStock(buscarMedicamento);

            }

        }
        if (nombremed != null) {
            Medicamentos nommed = new Medicamentos();
            //dato.setId_farmacia(cliente.getId_farmacia());
            nombremed = "%" + nombremed + "%";
            nommed.setMedicamento(nombremed);
            nommed.setId_programa(cliente.getCod_esta());
            List listarMedicamentos = this.mi.getListarMedicamentosRe(nommed);
            modelo.put("listarMedicamentos", listarMedicamentos);
        }

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
            total = total + midato.getPrecio_totalc();
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

        modelo.put("recepcion", recepcion);
        return new ModelAndView("administrarfarmacias/RecepcionMedicamento", modelo);
    }
}
