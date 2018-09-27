package org.ayaic.web.administrarmedicamentos;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Consultorios;
import org.ayaic.domain.Localidades;
import org.ayaic.domain.Medicamentos;
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.Personas;
import org.ayaic.domain.Recetas;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.document.AbstractXlsView;


@Controller
public class ReporteEntregadosControlador {

    private final MiFacade mi;

    public ReporteEntregadosControlador(MiFacade mi) {
        this.mi = mi;
    }
    
    @RequestMapping("/ReporteEntregados.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response){
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String id_paciente = request.getParameter("id_paciente");
        String sAccion = request.getParameter("boton");
        String sFecha_ini = request.getParameter("valor_1");
        String sFecha_fin = request.getParameter("valor_2");
        String accion1 = request.getParameter("accion1");
        String accion = request.getParameter("accion");
        String diaq1 = request.getParameter("mesq1");
        String mesq1 = request.getParameter("mesq1");
        String[] dias = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
        String[] meses = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        String[] horas = {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"};
        String[] minutos = {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"};
        //InetAddress ip;
        //ip = InetAddress.getLocalHost();
        String ip = request.getRemoteAddr();
        String host = request.getRemoteHost();
        Date fechaact = new Date();
        Date fecha1 = new Date();
        int anioq = fecha1.getYear() + 1900;
        String[] anios = {Integer.toString(anioq), Integer.toString(anioq - 1), Integer.toString(anioq - 2), Integer.toString(anioq - 3), Integer.toString(anioq - 4), Integer.toString(anioq - 5), Integer.toString(anioq - 6), Integer.toString(anioq - 7), Integer.toString(anioq - 8), Integer.toString(anioq - 9), Integer.toString(anioq - 10)};

        Localidades local = new Localidades();
        List Estab1 = this.mi.getListarEsta(local);
        Localidades datoestab = (Localidades) Estab1.get(0);
        modelo.put("codesta", Integer.toString(cliente.getCod_esta()));
        modelo.put("cod_esta", Integer.toString(datoestab.getCod_esta()));
        modelo.put("area", cliente.getArea());

        Medicamentos datom = new Medicamentos();
        datom.setCod_esta(cliente.getCod_esta());
        datom.setId_farmacia(cliente.getId_farmacia());
        datom.setExpedido("I");    /////getDatosItem
        Medicamentos datoItem = this.mi.getDatosItem(datom);
        modelo.put("datoItem", datoItem);

        modelo.put("id_paciente", id_paciente);
        Personas persona = this.mi.getBuscarPersona(cliente.getId_persona());
        Consultorios datosconsulta = this.mi.getDatosConsultorio(persona.getId_consultorio()); // saca un registro a ser modificado
        modelo.put("tipo_medico", Integer.toString(datosconsulta.getId_cargo()));
        modelo.put("anios", anios);
        modelo.put("meses", meses);
        modelo.put("dias", dias);
        modelo.put("horas", horas);
        modelo.put("minutos", minutos);
        modelo.put("anio", Integer.toString(fecha1.getYear() + 1900));

        if (fecha1.getMonth() + 1 < 10) {
            mesq1 = "0" + Integer.toString(fecha1.getMonth() + 1);
        } else {
            mesq1 = Integer.toString(fecha1.getMonth() + 1);
        }

        if (fecha1.getDate() < 10) {
            diaq1 = "0" + Integer.toString(fecha1.getDate());
        } else {
            diaq1 = Integer.toString(fecha1.getDate());
        }

        modelo.put("mes", mesq1);
        modelo.put("dia", diaq1);
        modelo.put("anio2", Integer.toString(fecha1.getYear() + 1900));
        modelo.put("mes2", mesq1);
        modelo.put("dia2", diaq1);
        modelo.put("hora", "00");
        modelo.put("minuto", "00");
        modelo.put("hora2", "23");
        modelo.put("minuto2", "59");
        modelo.put("dato", cliente);

        if ("Modificar".equals(accion)) {
            String id_pedido = request.getParameter("id_pedido");
            String id_factura = request.getParameter("id_factura");
            String total = request.getParameter("total");
            String nombres = request.getParameter("nombres");
            String nit = request.getParameter("nit");
            String num_cladoc = request.getParameter("num_cladoc");
            String fecha = request.getParameter("fecha");
            String anio = request.getParameter("anio");
            String mes = request.getParameter("mes");
            String dia = request.getParameter("dia");

            dia = fecha.substring(0, 2);
            mes = fecha.substring(3, 5);
            anio = fecha.substring(6, 10);

            modelo.put("dia", dia);
            modelo.put("mes", mes);
            modelo.put("anio", anio);
            modelo.put("id_pedido", id_pedido);
            modelo.put("nombres", nombres);
            modelo.put("nit", nit);
            modelo.put("total", total);
            modelo.put("num_cladoc", num_cladoc);
            modelo.put("id_factura", id_factura);

            return new ModelAndView("administrarmedicamentos/CambiarPedido", modelo);
        }

        if ("Cambiar".equals(accion)) {
            String id_pedido = request.getParameter("id_pedido");
            String id_factura = request.getParameter("id_factura");
            String total = request.getParameter("total");
            String nombres = request.getParameter("nombres");
            String nit = request.getParameter("nit");
            String num_cladoc = request.getParameter("num_cladoc");
            String anio = request.getParameter("anio");
            String mes = request.getParameter("mes");
            String dia = request.getParameter("dia");

            int iAnio = Integer.parseInt(anio) - 1900;
            int iMes = Integer.parseInt(mes) - 1;
            int iDia = Integer.parseInt(dia);

            Date Fechai = new Date(iAnio, iMes, iDia);

            Pacientes dato = new Pacientes();
            dato.setId_pedido(Integer.parseInt(id_pedido));
            //dato.setId_factura(Integer.parseInt(id_factura));
            dato.setId_persona(cliente.getId_persona());
            dato.setNum_cladoc(num_cladoc);
            dato.setNit(nit);
            dato.setNombres(nombres);
            dato.setPrecio_total(Double.parseDouble(total));
            dato.setFec_registro(Fechai);
            dato.setCadena1(ip);
            dato.setCadena2(host);
            dato.setCod_esta(cliente.getCod_esta());
            dato.setId_farmacia(cliente.getId_farmacia());
            try {
                this.mi.setModificarPedidos(dato);
                return new ModelAndView("Aviso", "mensaje", "Se Modifico Correctamente");
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "Exste datos erroneos");
            }
        }

        if ("EliminarP".equals(accion)) {
            String id_pedido = request.getParameter("id_pedido");
            String id_factura = request.getParameter("id_factura");
            String id_estado = request.getParameter("id_estado");
            String total = request.getParameter("total");
            String nombres = request.getParameter("nombres");
            String nit = request.getParameter("nit");
            String num_cladoc = request.getParameter("num_cladoc");
            String fecha = request.getParameter("fecha");
            String id_por = request.getParameter("id_por");

            modelo.put("fecha", fecha);
            modelo.put("id_pedido", id_pedido);
            modelo.put("id_estado", id_estado);
            modelo.put("nombres", nombres);
            modelo.put("nit", nit);
            modelo.put("total", total);
            modelo.put("num_cladoc", num_cladoc);
            modelo.put("id_factura", id_factura);
            modelo.put("id_por", id_por);

            Recetas datope = new Recetas();
            datope.setId_pedido(Integer.parseInt(id_pedido));
            datope.setCod_esta(cliente.getCod_esta());
            datope.setId_farmacia(cliente.getId_farmacia());
            List listarKardexp = this.mi.getListaMedKardexXPedido(datope);////getListaMedKardexEntregados
            modelo.put("listarKardex", listarKardexp);

            if (!listarKardexp.isEmpty()) {
                Recetas datoin = (Recetas) listarKardexp.get(0);/////01/05/2016 si ya paso mas de un dia no puede modificar
                long fechakardex = datoin.getFecha().getTime();
                long fechanow = fechaact.getTime();
                long diferencia = fechanow - fechakardex;
                double dias3 = Math.floor(diferencia / (1000 * 60 * 60 * 24));
                double horas3 = Math.floor(diferencia / (1000 * 60 * 60));
                double minut = Math.floor(diferencia / (1000 * 60));
                if (datoestab.getCod_esta() == 200010 && horas3 > 24 && cliente.getId_rol2() != 30 && cliente.getId_rol2() != 32) { //// rol 30 solo almacen puede modificar
                    return new ModelAndView("Aviso", "mensaje", "Ud. NO puede eliminar este regsitro");
                }
            }
            return new ModelAndView("administrarmedicamentos/EliminarEntregados", modelo);
        }

        if("Eliminar".equals(accion)){////elimina de PedidosProf
            String id_pedido = request.getParameter("id_pedido");
            String id_por = request.getParameter("id_por");
            Recetas datope = new Recetas();
            datope.setId_pedido(Integer.parseInt(id_pedido));
            datope.setCod_esta(cliente.getCod_esta());
            datope.setId_farmacia(cliente.getId_farmacia());
            List listarKardexp = mi.getListarKardexProf(datope);
            if(Integer.parseInt(id_por) != cliente.getId_persona() && cliente.getId_rol2() != 30 && cliente.getId_rol2() != 32 && cliente.getId_cargo() != 7){
                Personas buscarEmpleado = mi.getDatosPersona(Integer.parseInt(id_por));
                if(!listarKardexp.isEmpty())
                    return new ModelAndView("Aviso", "mensaje", (new StringBuilder()).append("Ud. no esta autorizado para eliminar esta receta, solo : ").append(buscarEmpleado.getNombres()).toString());
            }
            Pacientes dato = new Pacientes();
            dato.setId_pedido(Integer.parseInt(id_pedido));
            dato.setCod_esta(cliente.getCod_esta());
            Pacientes buscarPaciente = mi.getDatosPedidoProf(dato);
            if(buscarPaciente.getId_factura() > 0 && ("E".equals(buscarPaciente.getId_estado()) || "C".equals(buscarPaciente.getId_estado()))){
                return new ModelAndView("Aviso", "mensaje", "Esta receta no se puede eliminar, tiene factura emitida");
            }else{
                dato.setId_pedido(Integer.parseInt(id_pedido));
                dato.setId_persona(cliente.getId_persona());
                dato.setCod_esta(cliente.getCod_esta());
                dato.setId_farmacia(cliente.getId_farmacia());
                dato.setCadena1(ip);
                dato.setCadena2(host);
                mi.setEliminarPedidoProf(dato);
                return new ModelAndView("Aviso", "mensaje", "Se Elimino Correctamente");
            }
        }
        
        if("EliminarPed".equals(accion)){////elimina de Pedidos
            String id_pedido = request.getParameter("id_pedido");
            String id_por = request.getParameter("id_por");
            Recetas datope = new Recetas();
            datope.setId_pedido(Integer.parseInt(id_pedido));
            datope.setCod_esta(cliente.getCod_esta());
            datope.setId_farmacia(cliente.getId_farmacia());
            List listarKardexp = mi.getListaMedKardexXPedido(datope);
            if(Integer.parseInt(id_por) != cliente.getId_persona() && cliente.getId_rol2() != 30 && cliente.getId_rol2() != 32 && cliente.getId_cargo() != 7){
                Personas buscarEmpleado = mi.getDatosPersona(Integer.parseInt(id_por));
                if(!listarKardexp.isEmpty())
                    return new ModelAndView("Aviso", "mensaje", (new StringBuilder()).append("Ud. no esta autorizado para eliminar esta receta, solo : ").append(buscarEmpleado.getNombres()).toString());
            }
            Pacientes dato = new Pacientes();
            dato.setId_pedido(Integer.parseInt(id_pedido));
            dato.setCod_esta(cliente.getCod_esta());
            Pacientes buscarPaciente = mi.getDatosPedido(dato);
            if(buscarPaciente.getId_factura() > 0 && ("E".equals(buscarPaciente.getId_estado()) || "C".equals(buscarPaciente.getId_estado()))){
                return new ModelAndView("Aviso", "mensaje", "Esta receta no se puede eliminar, tiene factura emitida");
            }else{
                dato.setId_pedido(Integer.parseInt(id_pedido));
                dato.setId_persona(cliente.getId_persona());
                dato.setCod_esta(cliente.getCod_esta());
                dato.setId_farmacia(cliente.getId_farmacia());
                dato.setCadena1(ip);
                dato.setCadena2(host);
                mi.setEliminarPedido(dato);
                return new ModelAndView("Aviso", "mensaje", "Se Elimino Correctamente");
            }
        }

        if ("Imprimir".equals(accion)) {
            String anio1 = request.getParameter("anio1");
            String mes1 = request.getParameter("mes1");
            String dia1 = request.getParameter("dia1");
            String anio2 = request.getParameter("anio2");
            String mes2 = request.getParameter("mes2");
            String dia2 = request.getParameter("dia2");
            String hora1 = request.getParameter("hora1");
            String minuto1 = request.getParameter("minuto1");
            String hora2 = request.getParameter("hora2");
            String minuto2 = request.getParameter("minuto2");
            String AccionImp = request.getParameter("accionimp");

            Date Fecha1 = new Date(Integer.parseInt(anio1), Integer.parseInt(mes1), Integer.parseInt(dia1), Integer.parseInt(hora1), Integer.parseInt(minuto1), 00);
            Date Fecha2 = new Date(Integer.parseInt(anio2), Integer.parseInt(mes2), Integer.parseInt(dia2), Integer.parseInt(hora2), Integer.parseInt(minuto2), 59);

            Pacientes dato = new Pacientes();
            dato.setFecha_ini(Fecha1);
            dato.setFecha_fin(Fecha2);
            dato.setCod_esta(cliente.getCod_esta());
            dato.setId_farmacia(cliente.getId_farmacia());
            if ("VENTA".equals(AccionImp)) {
                dato.setId_estado("E");
                dato.setTipo("B");
                dato.setId_rubro(1);
            } else {
                if ("Ley475".equals(AccionImp)) {
                    dato.setId_estado("S");
                    dato.setTipo("B");
                    dato.setId_rubro(1);
                } else {
                    dato.setId_estado("P");
                    dato.setTipo("B");
                    dato.setId_rubro(1);
                }
            }

            if (datoestab.getCod_esta() == 200010) {
                dato.setTipo("C");
            }
            // lista de pacientes que no terminaron su receta
            List listarSinPago = this.mi.getListaMedKEntregados(dato);
            modelo.put("listapago", listarSinPago);
            if (datosconsulta.getId_cargo() == 3 || datosconsulta.getId_cargo() == 13 || datosconsulta.getId_cargo() == 14 || datosconsulta.getId_cargo() == 15 || datosconsulta.getId_cargo() == 19 || datosconsulta.getId_cargo() == 23) {
                dato.setRegistro("T");
                dato.setId_dispensa(cliente.getId_persona());
                List listarSinPago2 = this.mi.getListaMedKEntregadosEnfer(dato);
                modelo.put("listapago", listarSinPago2);
            }
            double total = 0;
            for (int i = 0; i < listarSinPago.size(); i++) {
                Pacientes datoxx = (Pacientes) listarSinPago.get(i);
                total += datoxx.getPrecio_total();
            }
            modelo.put("montototal", Double.toString(total));
            //List listartotal = this.mi.getListaCobroTotal(dato);
            //modelo.put("listatotal", listartotal);
            Recetas dator = new Recetas();
            dator.setFecha_ini(Fecha1);
            dator.setFecha_fin(Fecha2);
            if ("VENTA".equals(AccionImp)) {
                dator.setExpedido("V");
            } else {
                if ("Ley475".equals(AccionImp)) {
                    dator.setExpedido("S");
                } else {
                    dator.setExpedido("P");
                }
            }
            dator.setCod_esta(cliente.getCod_esta());
            dator.setId_farmacia(cliente.getId_farmacia());
            dator.setId_persona(cliente.getId_persona());
            List listarKardex = this.mi.getListaMedKardexEntregados(dator);
            modelo.put("anio1", Integer.toString(Integer.parseInt(anio1) + 1900));
            modelo.put("mes1", Integer.toString(Integer.parseInt(mes1) + 1));
            modelo.put("dia1", dia1);
            modelo.put("anio2", Integer.toString(Integer.parseInt(anio2) + 1900));
            modelo.put("mes2", Integer.toString(Integer.parseInt(mes2) + 1));
            modelo.put("dia2", dia2);
            modelo.put("hora1", hora1);
            modelo.put("hora2", hora2);
            modelo.put("minuto1", minuto1);
            modelo.put("minuto2", minuto2);
            modelo.put("AccionImp", AccionImp);
            modelo.put("listarKardex", listarKardex);
            return new ModelAndView(new ListarInfEcoPDF(), modelo);
        }

        if ("VENTA".equals(sAccion)) {
            String diai = request.getParameter("diai");
            String mesi = request.getParameter("mesi");
            String anioi = request.getParameter("anioi");
            String horai = request.getParameter("horai");
            String minutoi = request.getParameter("minutoi");
            String diaf = request.getParameter("diaf");
            String mesf = request.getParameter("mesf");
            String aniof = request.getParameter("aniof");
            String horaf = request.getParameter("horaf");
            String minutof = request.getParameter("minutof");

            if (("".equals(diai)) || ("".equals(mesi)) || ("".equals(anioi))) {
                return new ModelAndView("administrarmedicamentos/ReporteEntregados", modelo);
            } else {

                int iAnio1 = Integer.parseInt(anioi) - 1900;
                int iMes1 = Integer.parseInt(mesi) - 1;
                int iDia1 = Integer.parseInt(diai);
                int iHor1 = Integer.parseInt(horai);
                int iMin1 = Integer.parseInt(minutoi);

                int iAnio2 = Integer.parseInt(aniof) - 1900;
                int iMes2 = Integer.parseInt(mesf) - 1;
                int iDia2 = Integer.parseInt(diaf);
                int iHor2 = Integer.parseInt(horaf);
                int iMin2 = Integer.parseInt(minutof);

                Date dFechaini1 = new Date(iAnio1, iMes1, iDia1, iHor1, iMin1, 00);
                Date dFechafin1 = new Date(iAnio2, iMes2, iDia2, iHor2, iMin2, 59);

                Pacientes dato = new Pacientes();
                dato.setFecha_ini(dFechaini1);
                dato.setFecha_fin(dFechafin1);
                dato.setId_estado("E");
                dato.setTipo("B");
                dato.setId_rubro(1);
                dato.setCod_esta(cliente.getCod_esta());
                dato.setId_farmacia(cliente.getId_farmacia());
                List listarSinPago = this.mi.getListaMedKEntregados(dato);///pedidos de la tabla pedidos
                modelo.put("listapago", listarSinPago);
                if (datoestab.getCod_esta() != 400007) {     ///ordena por nit solo para AP
                    List listarSinPago2 = this.mi.getListaMedKEntregadosFecha(dato);
                    modelo.put("listapago", listarSinPago2);
                }
                if (datosconsulta.getId_cargo() == 3 || datosconsulta.getId_cargo() == 13 || datosconsulta.getId_cargo() == 14 || datosconsulta.getId_cargo() == 15 || datosconsulta.getId_cargo() == 19 || datosconsulta.getId_cargo() == 23) {
                    dato.setId_dispensa(cliente.getId_persona());
                    List listarSinPago2 = this.mi.getListaMedKEntregadosEnfer(dato);
                    modelo.put("listapago", listarSinPago2);
                }
                // lista de pacientes que no terminaron su receta

                double total = 0;
                for (int i = 0; i < listarSinPago.size(); i++) {
                    Pacientes datoxx = (Pacientes) listarSinPago.get(i);
                    total += datoxx.getPrecio_total();
                }
                modelo.put("montototal", Double.toString(total));
                //List listartotal = this.mi.getListaCobroTotal(dato);
                //modelo.put("listatotal", listartotal);
                Recetas dator = new Recetas();
                dator.setFecha_ini(dFechaini1);
                dator.setFecha_fin(dFechafin1);
                dator.setExpedido("V");
                dator.setCod_esta(cliente.getCod_esta());
                dator.setId_farmacia(cliente.getId_farmacia());
                dator.setId_persona(cliente.getId_persona());
                List listarKardex = this.mi.getListaMedKardexEntregados(dator);///detalle kardex de la tabla kardex
                modelo.put("listarKardex", listarKardex);
                modelo.put("valor_1", Integer.toString(iDia1) + '-' + Integer.toString(iMes1 + 1) + '-' + Integer.toString(iAnio1 + 1900) + ' ' + Integer.toString(iHor1) + ':' + Integer.toString(iMin1) + ":00");
                modelo.put("valor_2", Integer.toString(iDia2) + '-' + Integer.toString(iMes2 + 1) + '-' + Integer.toString(iAnio2 + 1900) + ' ' + Integer.toString(iHor2) + ':' + Integer.toString(iMin2) + ":00");
                modelo.put("anio1", Integer.toString(iAnio1));
                modelo.put("mes1", Integer.toString(iMes1));
                modelo.put("dia1", Integer.toString(iDia1));
                modelo.put("anio2", Integer.toString(iAnio2));
                modelo.put("mes2", Integer.toString(iMes2));
                modelo.put("dia2", Integer.toString(iDia2));
                modelo.put("hora1", Integer.toString(iHor1));
                modelo.put("hora2", Integer.toString(iHor2));
                modelo.put("minuto1", Integer.toString(iMin1));
                modelo.put("minuto2", Integer.toString(iMin2));
                modelo.put("sAccion", sAccion);
                modelo.put("Financiamiento", "VENTA");
                return new ModelAndView("administrarmedicamentos/ListarReporteEntregados", modelo);
            }
        }

        if ("Ley475".equals(sAccion)) {
            String diai = request.getParameter("diai");
            String mesi = request.getParameter("mesi");
            String anioi = request.getParameter("anioi");
            String horai = request.getParameter("horai");
            String minutoi = request.getParameter("minutoi");
            String diaf = request.getParameter("diaf");
            String mesf = request.getParameter("mesf");
            String aniof = request.getParameter("aniof");
            String horaf = request.getParameter("horaf");
            String minutof = request.getParameter("minutof");

            if (("".equals(diai)) || ("".equals(mesi)) || ("".equals(anioi))) {
                return new ModelAndView("administrarmedicamentos/ReporteEntregados", modelo);
            } else {

                int iAnio1 = Integer.parseInt(anioi) - 1900;
                int iMes1 = Integer.parseInt(mesi) - 1;
                int iDia1 = Integer.parseInt(diai);
                int iHor1 = Integer.parseInt(horai);
                int iMin1 = Integer.parseInt(minutoi);

                int iAnio2 = Integer.parseInt(aniof) - 1900;
                int iMes2 = Integer.parseInt(mesf) - 1;
                int iDia2 = Integer.parseInt(diaf);
                int iHor2 = Integer.parseInt(horaf);
                int iMin2 = Integer.parseInt(minutof);

                Date dFechaini1 = new Date(iAnio1, iMes1, iDia1, iHor1, iMin1, 00);
                Date dFechafin1 = new Date(iAnio2, iMes2, iDia2, iHor2, iMin2, 59);

                Pacientes dato = new Pacientes();
                dato.setFecha_ini(dFechaini1);
                dato.setFecha_fin(dFechafin1);
                dato.setId_estado("S");
                dato.setTipo("B");
                dato.setId_rubro(1);
                dato.setCod_esta(cliente.getCod_esta());
                dato.setId_farmacia(cliente.getId_farmacia());
                // lista de pacientes que no terminaron su receta
                List listarSinPago = this.mi.getListaMedKEntregados(dato);
                modelo.put("listapago", listarSinPago);
                double total = 0;
                for (int i = 0; i < listarSinPago.size(); i++) {
                    Pacientes datoxx = (Pacientes) listarSinPago.get(i);
                    total += datoxx.getPrecio_total();
                }
                modelo.put("montototal", Double.toString(total));
                //List listartotal = this.mi.getListaCobroTotal(dato);
                //modelo.put("listatotal", listartotal);
                Recetas dator = new Recetas();
                dator.setFecha_ini(dFechaini1);
                dator.setFecha_fin(dFechafin1);
                dator.setExpedido("S");
                dator.setCod_esta(cliente.getCod_esta());
                dator.setId_farmacia(cliente.getId_farmacia());
                dator.setId_persona(cliente.getId_persona());
                List listarKardex = this.mi.getListaMedKardexEntregados(dator);
                modelo.put("listarKardex", listarKardex);
                modelo.put("valor_1", sFecha_ini);
                modelo.put("valor_2", sFecha_fin);
                modelo.put("anio1", Integer.toString(iAnio1));
                modelo.put("mes1", Integer.toString(iMes1));
                modelo.put("dia1", Integer.toString(iDia1));
                modelo.put("anio2", Integer.toString(iAnio2));
                modelo.put("mes2", Integer.toString(iMes2));
                modelo.put("dia2", Integer.toString(iDia2));
                modelo.put("sAccion", sAccion);
                modelo.put("Financiamiento", "Ley475");
                return new ModelAndView("administrarmedicamentos/ListarReporteEntregados", modelo);
            }
        }

        if ("ReporteXespecialidad".equals(sAccion) || "ReporteXmedico".equals(sAccion) || "ReporteXservicio".equals(sAccion)
                || "ReporGeneral".equals(sAccion) || "ReporGeneralFarmacia".equals(sAccion) || "ReporGeneralUsuario".equals(sAccion)
                || "ReversionExcel".equals(sAccion) || "ExportarExcel".equals(sAccion) || "OTROS".equals(sAccion) || "ExportSifarUsua".equals(sAccion)
                || "ExportSIFAR".equals(sAccion) || "DetalleMed".equals(sAccion) || "RemisionFarmacia".equals(sAccion) || "RemisionUsuario".equals(sAccion)
                || "RemisionGeneral".equals(sAccion) || "ResumenDispensadas".equals(sAccion) || "DetalleRestringidos".equals(sAccion)
                || "Restringidos".equals(sAccion) || "ExportSifarFarmacia".equals(sAccion) || "DetalleMedFar".equals(sAccion)
                || "ReporEco".equals(sAccion) || "Vencimientos".equals(sAccion) || "ResumenDiarioDispensadas".equals(sAccion)) {

            String diai = request.getParameter("diai");
            String mesi = request.getParameter("mesi");
            String anioi = request.getParameter("anioi");
            String horai = request.getParameter("horai");
            String minutoi = request.getParameter("minutoi");
            String diaf = request.getParameter("diaf");
            String mesf = request.getParameter("mesf");
            String aniof = request.getParameter("aniof");
            String horaf = request.getParameter("horaf");
            String minutof = request.getParameter("minutof");

            if (("".equals(diai)) || ("".equals(mesi)) || ("".equals(anioi))) {
                return new ModelAndView("administrarmedicamentos/ReporteEntregados", modelo);
            } else {

                int iAnio1 = Integer.parseInt(anioi) - 1900;
                int iMes1 = Integer.parseInt(mesi) - 1;
                int iDia1 = Integer.parseInt(diai);
                int iHor1 = Integer.parseInt(horai);
                int iMin1 = Integer.parseInt(minutoi);

                int iAnio2 = Integer.parseInt(aniof) - 1900;
                int iMes2 = Integer.parseInt(mesf) - 1;
                int iDia2 = Integer.parseInt(diaf);
                int iHor2 = Integer.parseInt(horaf);
                int iMin2 = Integer.parseInt(minutof);

                Date dFechaini1 = new Date(iAnio1, iMes1, iDia1, iHor1, iMin1, 00);
                Date dFechafin1 = new Date(iAnio2, iMes2, iDia2, iHor2, iMin2, 59);

                modelo.put("dFechafin1", anioi + "-" + mesi + "-" + diai + " " + horai + " " + minutoi);
                modelo.put("dFechafin2", aniof + "-" + mesf + "-" + diaf + " " + horaf + " " + minutof);

                Pacientes dato = new Pacientes();

                dato.setFecha_ini(dFechaini1);
                dato.setFecha_fin(dFechafin1);
                dato.setCod_esta(cliente.getCod_esta());
                dato.setId_persona(cliente.getId_persona());
                modelo.put("persona", dato);

                Recetas dator = new Recetas();
                dator.setFecha_ini(dFechaini1);
                dator.setFecha_fin(dFechafin1);
                dator.setCod_esta(cliente.getCod_esta());
                dator.setId_farmacia(cliente.getId_farmacia());
                dator.setId_programa(cliente.getId_farmacia());
                dator.setId_persona(cliente.getId_persona());
                dator.setId_paciente(cliente.getId_persona());

                if ("ReporEco".equals(sAccion)) {
                    List listaCobros = this.mi.getReporteCobroFarmGen(dato);
                    modelo.put("listaCobros", listaCobros);
                    return new ModelAndView(new ReporteFarmaciaPDF(), modelo);
                }

                if ("Vencimientos".equals(sAccion)) {
                    dator.setId_estado("V");
                    List listarKardexUsua = this.mi.getVencimientos(dator);  ///
                    modelo.put("listarKardex", listarKardexUsua);

                    Personas buscarEmpleado = this.mi.getDatosPersona(cliente.getId_persona());
                    modelo.put("persona", buscarEmpleado);
                    return new ModelAndView(new VerVencimientosPDF(), modelo);
                }

                if ("DetalleMed".equals(sAccion) || "DetalleMedFar".equals(sAccion)) {
                    dator.setId_estado("D");
                    List listarKardexUsua = this.mi.getKardexFarmaciaCNSDet(dator);
                    modelo.put("listarKardex", listarKardexUsua);
                    if ("DetalleMedFar".equals(sAccion)) {
                        dator.setId_estado("F");   ///getKardexFarmaciaCNSDetFar
                        List listarKardexUsua2 = this.mi.getKardexFarmaciaCNSDetFar(dator);
                        modelo.put("listarKardex", listarKardexUsua2);
                    }

                    Personas buscarEmpleado = this.mi.getDatosPersona(cliente.getId_persona());
                    modelo.put("persona", buscarEmpleado);
                    return new ModelAndView(new VerKardexUsuarioDetPDF(), modelo);
                }

                if ("ExportarExcel".equals(sAccion)) {
                    List listarKardexUsua = this.mi.getKardexExport(dator);
                    modelo.put("listarKardex", listarKardexUsua);
                    return new ModelAndView(new excel2(), modelo);
                }
                if ("ReversionExcel".equals(sAccion)) {
                    List listarReversion = this.mi.getRevercionExcel(dator);
                    modelo.put("listarReversion", listarReversion);
                    return new ModelAndView(new excel3(), modelo);
                }

                if ("ReporteXespecialidad".equals(sAccion) || "ReporteXmedico".equals(sAccion) || "ReporteXservicio".equals(sAccion)
                        || "ResumenDispensadas".equals(sAccion) || "ResumenDiarioDispensadas".equals(sAccion)) {
                    dator.setId_estado("A");  ///getKardexResumenDispensa
                    List listarKardexRemi = this.mi.getKardexResumenDispensa(dator);
                    modelo.put("listarKardexRemi", listarKardexRemi);
                    if ("ResumenDiarioDispensadas".equals(sAccion)) {
                        dator.setId_estado("P");  ///getKardexResumenDispensaDia  
                        List listarKardexRemi2 = this.mi.getKardexResumenDispensaDia(dator);
                        modelo.put("listarKardexRemi", listarKardexRemi2);
                    }
                    Personas buscarEmpleado = this.mi.getDatosPersona(cliente.getId_persona());
                    modelo.put("persona", buscarEmpleado);
                    if ("ReporteXespecialidad".equals(sAccion)) {
                        List listarKardexRemi2 = this.mi.getResumenXespecialidad(dator);
                        modelo.put("listarKardexRemi", listarKardexRemi2);
                        return new ModelAndView(new VerResumenXespecilidadPDF(), modelo);
                    }
                    if ("ReporteXmedico".equals(sAccion)) {
                        List listarKardexRemi2 = this.mi.getResumenXmedico(dator);
                        modelo.put("listarKardexRemi", listarKardexRemi2);
                        return new ModelAndView(new VerResumenXmedicoPDF(), modelo);
                    }
                    if ("ReporteXservicio".equals(sAccion)) {
                        List listarKardexRemi2 = this.mi.getResumenXservicio(dator);
                        modelo.put("listarKardexRemi", listarKardexRemi2);
                        return new ModelAndView(new VerResumenXservicioPDF(), modelo);
                    }

                    if ("ResumenDiarioDispensadas".equals(sAccion)) {
                        return new ModelAndView(new VerKardexResumenDispeDiaPDF(), modelo);
                    }
                    return new ModelAndView(new VerKardexResumenDispePDF(), modelo);
                }

                if ("ReporGeneralUsuario".equals(sAccion) || "RemisionUsuario".equals(sAccion)) {
                    dator.setId_farmacia(0);
                    dator.setId_programa(99999);
                    List listarKardexRemi = this.mi.getKardexRemision(dator);
                    modelo.put("listarKardexRemi", listarKardexRemi);

                    Personas buscarEmpleado = this.mi.getDatosPersona(cliente.getId_persona());
                    modelo.put("persona", buscarEmpleado);
                    if ("ReporGeneralUsuario".equals(sAccion)) {
                        return new ModelAndView(new VerKardexMedGeneralPDF(), modelo);
                    }

                    return new ModelAndView(new VerKardexRemisionPDF(), modelo);
                }

                if ("ReporGeneralFarmacia".equals(sAccion) || "RemisionFarmacia".equals(sAccion)) {
                    dator.setId_persona(1);
                    dator.setId_paciente(99999);
                    dator.setId_farmacia(cliente.getId_farmacia());
                    dator.setId_programa(cliente.getId_farmacia());
                    dator.setId_estado("Q");  ///getKardexRemision
                    List listarKardexRemi = this.mi.getKardexRemision(dator);  ///getKardexRemision
                    modelo.put("listarKardexRemi", listarKardexRemi);
                    Personas buscarEmpleado = this.mi.getDatosPersona(cliente.getId_persona());
                    modelo.put("persona", buscarEmpleado);
                    if ("ReporGeneralFarmacia".equals(sAccion)) {
                        return new ModelAndView(new VerKardexMedGeneralPDF(), modelo);
                    }
                    return new ModelAndView(new VerKardexRemisionPDF(), modelo);
                }

                if ("ReporGeneral".equals(sAccion) || "RemisionGeneral".equals(sAccion)) {
                    dator.setId_persona(0);
                    dator.setId_paciente(99999);
                    dator.setId_farmacia(0);
                    dator.setId_programa(99999);
                    dator.setId_estado("Q");  ///getKardexRemision
                    List listarKardexRemi = this.mi.getKardexRemision(dator);  ///getKardexRemision
                    modelo.put("listarKardexRemi", listarKardexRemi);
                    Personas buscarEmpleado = this.mi.getDatosPersona(cliente.getId_persona());
                    modelo.put("persona", buscarEmpleado);
                    if ("ReporGeneral".equals(sAccion)) {
                        return new ModelAndView(new VerKardexMedGeneralPDF(), modelo);
                    }
                    return new ModelAndView(new VerKardexRemisionPDF(), modelo);
                }

                if ("Restringidos".equals(sAccion)) {
                    dator.setId_estado("R");
                    List listarKardexUsua = this.mi.getKardexRestringido(dator);  ///getKardexRestringido
                    modelo.put("listarKardex", listarKardexUsua);
                    Personas buscarEmpleado = this.mi.getDatosPersona(cliente.getId_persona());
                    modelo.put("persona", buscarEmpleado);
                    return new ModelAndView(new VerKardexUsuarioPDF(), modelo);
                }

                if ("DetalleRestringidos".equals(sAccion)) {
                    dator.setId_estado("3");
                    List listarKardexUsua = this.mi.getKardexRestringido(dator);  ///getKardexRestringido2
                    modelo.put("datokardex", listarKardexUsua);
                    Personas buscarEmpleado = this.mi.getDatosPersona(cliente.getId_persona());
                    modelo.put("persona", buscarEmpleado);
                    return new ModelAndView(new ListarKardexPsicoPDF(), modelo);
                }

                if ("ExportSIFAR".equals(sAccion) || "ExportSifarUsua".equals(sAccion) || "ExportSifarFarmacia".equals(sAccion)) {
                    dator.setId_estado("C");   ////getKardexFarmaciaCNS
                    List listarKardexUsua = this.mi.getKardexFarmaciaCNS(dator);  ///
                    modelo.put("listarKardex", listarKardexUsua);
                    if (cliente.getCod_esta() == 700241) {
                        dator.setId_estado("1"); ////getKardexFarmaciaCNS_SC
                        List listarKardexUsua2 = this.mi.getKardexFarmaciaCNS_SC(dator);  ///
                        modelo.put("listarKardex", listarKardexUsua2);
                    }
                    if ("ExportSifarUsua".equals(sAccion)) {
                        dator.setId_estado("D");   ///getKardexFarmaciaCNSDet
                        List listarKardexUsua2 = this.mi.getKardexFarmaciaCNSDet(dator);  ///
                        modelo.put("listarKardex", listarKardexUsua2);
                        if (cliente.getCod_esta() == 700241) {
                            dator.setId_estado("2"); ////getKardexFarmaciaCNSDet_SC
                            List listarKardexUsua3 = this.mi.getKardexFarmaciaCNSDet_SC(dator);  ///
                            modelo.put("listarKardex", listarKardexUsua3);
                        }
                    }
                    if ("ExportSifarFarmacia".equals(sAccion)) {
                        dator.setId_estado("F");   ///getKardexFarmaciaCNSDetFar
                        List listarKardexUsua3 = this.mi.getKardexFarmaciaCNSDetFar(dator);  ///
                        modelo.put("listarKardex", listarKardexUsua3);
                    }

                    Personas buscarEmpleado = this.mi.getDatosPersona(cliente.getId_persona());
                    modelo.put("persona", buscarEmpleado);
                    return new ModelAndView("administrarmedicamentos/ListarEntregadosCNS", modelo);
                }

                //Pacientes dato=new Pacientes();
                dato.setFecha_ini(dFechaini1);
                dato.setFecha_fin(dFechafin1);
                dato.setId_estado("P");
                dato.setTipo("B");
                if (datoestab.getCod_esta() == 200010) {
                    dato.setTipo("C");
                }
                dato.setId_rubro(1);
                dato.setCod_esta(cliente.getCod_esta());
                dato.setId_farmacia(cliente.getId_farmacia());
                // lista de pacientes que no terminaron su receta
                List listarSinPago = this.mi.getListaMedKEntregados(dato);
                modelo.put("listapago", listarSinPago);
                double total = 0;
                for (int i = 0; i < listarSinPago.size(); i++) {
                    Pacientes datoxx = (Pacientes) listarSinPago.get(i);
                    total += datoxx.getPrecio_total();
                }
                modelo.put("montototal", Double.toString(total));
                //List listartotal = this.mi.getListaCobroTotal(dato);
                //modelo.put("listatotal", listartotal);
                //Recetas dator=new Recetas();
                //dator.setFecha_ini(dFechaini1);
                //dator.setFecha_fin(dFechafin1);
                dator.setExpedido("P");
                dator.setId_farmacia(cliente.getId_farmacia());
                List listarKardex = this.mi.getListaMedKardexEntregados(dator);
                modelo.put("listarKardex", listarKardex);
                Medicamentos medid = new Medicamentos();
                medid.setCod_esta(cliente.getCod_esta());
                List listarprog = this.mi.getListarProgramas(medid);
                modelo.put("listarProg", listarprog);
                modelo.put("valor_1", sFecha_ini);
                modelo.put("valor_2", sFecha_fin);
                modelo.put("anio1", Integer.toString(iAnio1));
                modelo.put("mes1", Integer.toString(iMes1));
                modelo.put("dia1", Integer.toString(iDia1));
                modelo.put("anio2", Integer.toString(iAnio2));
                modelo.put("mes2", Integer.toString(iMes2));
                modelo.put("dia2", Integer.toString(iDia2));
                modelo.put("sAccion", "Otros Seguros");
                modelo.put("Financiamiento", "PROGRAMAS");
                return new ModelAndView("administrarmedicamentos/ListarReporteEntregados", modelo);
            }
        }

        modelo.put("valor_1", sFecha_ini);
        modelo.put("valor_2", sFecha_fin);

        return new ModelAndView("administrarmedicamentos/ReporteEntregados", modelo);

    }

    private static class excel3 extends AbstractXlsView {
        
        @Override
        protected void buildExcelDocument(Map<String, Object> map, Workbook wrkbk, HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
            
            Sheet sheet = wrkbk.createSheet("reversion");

            Row fila = sheet.createRow(0);
            Cell celda;

            String[] titulos = {"tag", "dia", "mes", "anio", "almacen", "numdoc", "cargo_cta", "cargo_sub", "cargo_sub1", "cargo_analisis", "abono_cta", "abono_sub", "abono_sub1", "abono_analisis", "factura", "reparti", "cod_cta", "cod_tipo", "cod_esp", "cod_material", "uniman", "cantidad", "valor", "preuni", "fechatrans", "usuario", "numreg", "tagpen"};
            int i;

            for (i = 0; i < titulos.length; i++) {
                celda = fila.createCell(i);
                celda.setCellValue(titulos[i]);
            }
            // Nueva fila
            fila = sheet.createRow(1);
            List listarRever = (List) map.get("listarReversion");
            for (i = 0; i < listarRever.size(); i++) {
                Recetas dato = (Recetas) listarRever.get(i);
                Row filas = sheet.createRow(i + 5);
                for (int j = 0; j < 18; j++) {
                    HSSFRow row = (HSSFRow) sheet.createRow(i + 1);
                    row.createCell(0).setCellValue("R");  ////tag
                    row.createCell(1).setCellValue(dato.getCadena3());  ////mes
                    row.createCell(2).setCellValue(dato.getCadena4());  ////mes
                    row.createCell(3).setCellValue(dato.getCadena5());  ////anio
                    row.createCell(4).setCellValue(dato.getCadena6());  ////almacen
                    row.createCell(5).setCellValue(dato.getCadena7());  ////numdoc
                    row.createCell(6).setCellValue("0");  ///cargo_cta
                    row.createCell(7).setCellValue("0");  ///cargo_sub
                    row.createCell(8).setCellValue("0");  ///cargo_sub1
                    row.createCell(9).setCellValue("0");  ///cargo_analisis
                    row.createCell(10).setCellValue("0");  ///abono_cta
                    row.createCell(11).setCellValue("0");  ///abono_sub
                    row.createCell(12).setCellValue("0");  ///abono_sub1
                    row.createCell(13).setCellValue("0");  ///abono_analisis
                    row.createCell(14).setCellValue(dato.getCadena7());  ///factura
                    row.createCell(15).setCellValue("88250000");  ///reparti
                    row.createCell(16).setCellValue(dato.getCadena8());  ///cod_cta
                    row.createCell(17).setCellValue(dato.getCadena9());  ///cod_tipo
                    row.createCell(18).setCellValue(dato.getCadena10());  ///cod_esp
                    row.createCell(19).setCellValue(dato.getCadena11());  ///cod_material
                    row.createCell(20).setCellValue("02");  ///uniman
                    row.createCell(21).setCellValue(dato.getSuma1());  ///cantidad
                    row.createCell(22).setCellValue(0);  ///valor
                    row.createCell(23).setCellValue(0);  ///preuni
                    row.createCell(24).setCellValue(dato.getCadena12());   ///fechatrans
                    row.createCell(25).setCellValue(dato.getCadena13());  ///usuario
                    row.createCell(26).setCellValue(dato.getSuma3());  ///numreg
                    row.createCell(27).setCellValue("0");  ///tagpen
                }
            }
        }

    }

    private static class excel2 extends AbstractXlsView {
        
        @Override
        protected void buildExcelDocument(Map<String, Object> map, Workbook wrkbk, HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
            
            Sheet sheet = wrkbk.createSheet("medicamentos");

            Row fila = sheet.createRow(0);
            Cell celda;

            String[] titulos = {"lote", "tag", "dia", "mes", "anio", "num_receta", "matricula", "sigla", "c_ben", "cod_cta", "cod_tipo", "cod_espec", "cod_material", "cantidad", "cod_medico", "serv", "cen_sol", "fecha", "usuario"};
            int i;

            for (i = 0; i < titulos.length; i++) {
                celda = fila.createCell(i);
                celda.setCellValue(titulos[i]);
            }
            // Nueva fila
            fila = sheet.createRow(1);
            List listarKardexUsua = (List) map.get("listarKardex");
            for (i = 0; i < listarKardexUsua.size(); i++) {
                Recetas dato = (Recetas) listarKardexUsua.get(i);
                Row filas = sheet.createRow(i + 5);
                for (int j = 0; j < 18; j++) {
                    HSSFRow row = (HSSFRow) sheet.createRow(i + 1);
                    row.createCell(0).setCellValue(dato.getCadena1());  ////lote
                    row.createCell(1).setCellValue(dato.getCadena2());  ////tag
                    row.createCell(2).setCellValue(dato.getCadena3());  ////dia
                    row.createCell(3).setCellValue(dato.getCadena4());  ////mes
                    row.createCell(4).setCellValue(dato.getCadena5());  ////año
                    row.createCell(5).setCellValue(dato.getSuma1());    ////numreceta
                    row.createCell(6).setCellValue(dato.getCadena6());  ///matricula
                    row.createCell(7).setCellValue(dato.getCadena7());  ///sigla
                    row.createCell(8).setCellValue(dato.getCadena8());  ///codigo
                    row.createCell(9).setCellValue(dato.getCadena9());  ///cta_cta
                    row.createCell(10).setCellValue(dato.getCadena10());  ///cod_tipo
                    row.createCell(11).setCellValue(dato.getCadena11());  ///cod_espe
                    row.createCell(12).setCellValue(dato.getCadena12());  ///cod_matrial
                    row.createCell(13).setCellValue(dato.getSuma2());     ///salida
                    row.createCell(14).setCellValue(dato.getCadena13());  ///medico
                    row.createCell(15).setCellValue(dato.getCadena14());  ///especialida
                    row.createCell(16).setCellValue(dato.getCadena15());  ///centro
                    row.createCell(17).setCellValue(dato.getCadena16());  ///fecha
                    row.createCell(18).setCellValue(dato.getCadena17());  ///usuario
                }
            }
        }

    }

}
