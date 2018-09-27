package org.ayaic.web.administrarmedicamentos;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Consultorios;
import org.ayaic.domain.Farmacias;
import org.ayaic.domain.Localidades;
import org.ayaic.domain.Medicamentos;
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.Personas;
import org.ayaic.domain.Recetas;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BuscarPedidosControlador {

    private final MiFacade mi;

    public BuscarPedidosControlador(MiFacade mi) {
        this.mi = mi;
    }
    
    @RequestMapping("/BuscarPedidos.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String id_paciente = request.getParameter("id_paciente");
        String[] dias = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
        String[] meses = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        String[] horas = {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"};
        String[] minutos = {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"};
        String diaq1 = request.getParameter("diaq1");
        String mesq1 = request.getParameter("mesq1");
        String ip = request.getRemoteAddr();
        String host = request.getRemoteHost();
        Date fechaact = new Date();
        Date fecha1 = new Date();
        int anioq = fecha1.getYear() + 1900;
        String[] anios = {Integer.toString(anioq), Integer.toString(anioq - 1), Integer.toString(anioq - 2), Integer.toString(anioq - 3), Integer.toString(anioq - 4), Integer.toString(anioq - 5), Integer.toString(anioq - 6), Integer.toString(anioq - 7), Integer.toString(anioq - 8), Integer.toString(anioq - 9), Integer.toString(anioq - 10)};

        //Recuperamos variables del jsp
        String sAccion = request.getParameter("boton");
        String id_estado = request.getParameter("id_estado");
        String id_tipo_far = request.getParameter("id_tipo_far");
        String sFecha_ini = request.getParameter("valor_1");
        String sFecha_fin = request.getParameter("valor_2");

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
        modelo.put("id_tipo_far", id_tipo_far);

        Localidades local = new Localidades();
        List Estab1 = this.mi.getListarEsta(local);
        Localidades datoestab = (Localidades) Estab1.get(0);
        modelo.put("codesta", Integer.toString(datoestab.getCod_esta()));

        if ("Buscar".equals(sAccion)) {
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
                return new ModelAndView("administrarmedicamentos/BuscarPedidos", modelo);
            } else {
                String nombres = request.getParameter("nombres");
                if (nombres != null) {
                    nombres = nombres.replaceAll("\\s", "%");
                    nombres = "%" + nombres + "%";
                }
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
                Date dFechafin2 = new Date(iAnio2, iMes2, iDia2, iHor2, iMin2, 59);

                Pacientes dato = new Pacientes();
                dato.setFecha_ini(dFechaini1);
                dato.setFecha_fin(dFechafin2);
                dato.setCod_esta(cliente.getCod_esta());
                dato.setId_farmacia(cliente.getId_farmacia());
                dato.setNombres(nombres);
                dato.setId_estado("%");
                dato.setTipo("2");
                if (cliente.getId_rol2() == 30) {
                    dato.setTipo("3");  /////getListaMedRecibidosAlmaGen
                    List listarSinPago = this.mi.getListaMedRecibidosAlmaGen(dato);
                    modelo.put("listapago", listarSinPago);
                } else {
                    List listarSinPago = this.mi.getListaMedRecibidos2(dato);
                    modelo.put("listapago", listarSinPago);
                }
                return new ModelAndView("administrarmedicamentos/BuscarPedidos", modelo);
            }
        }

        if ("T".equals(id_estado)) { //venta de medicamentos solo del sumi
            String nombres = request.getParameter("nombre");
            nombres = nombres.replaceAll("\\s", "%");
            nombres = "%" + nombres + "%";
            Pacientes dato = new Pacientes();
            dato.setNombres(nombres);
            dato.setId_estado("%");
            dato.setTipo("T");
            dato.setCod_esta(cliente.getCod_esta());
            dato.setId_farmacia(cliente.getId_farmacia());
            List listarSinPago = this.mi.getListaMedRecibidos(dato);
            modelo.put("listapago", listarSinPago);

            return new ModelAndView("administrarmedicamentos/BuscarPedidos", modelo);
        }

        if ("SS".equals(id_estado)) { //venta de medicamentos solo del sumi
            String nombres = request.getParameter("nombre");
            nombres = nombres.replaceAll("\\s", "%");
            nombres = "%" + nombres + "%";
            Pacientes dato = new Pacientes();
            dato.setNombres(nombres);
            dato.setId_estado("S");
            dato.setTipo("T");
            dato.setCod_esta(cliente.getCod_esta());
            dato.setId_farmacia(cliente.getId_farmacia());
            List listarSinPago = this.mi.getListaMedRecibidos(dato);
            modelo.put("listapago", listarSinPago);
            return new ModelAndView("administrarmedicamentos/BuscarPedidos", modelo);
        }

        if ("PP".equals(id_estado)) { //venta de medicamentos solo del Programas
            String nombres = request.getParameter("nombre");
            nombres = nombres.replaceAll("\\s", "%");
            nombres = "%" + nombres + "%";
            Pacientes dato = new Pacientes();
            dato.setNombres(nombres);
            dato.setId_estado("P");
            dato.setTipo("T");
            dato.setCod_esta(cliente.getCod_esta());
            dato.setId_farmacia(cliente.getId_farmacia());
            List listarSinPago = this.mi.getListaMedRecibidos(dato);
            modelo.put("listapago", listarSinPago);
            return new ModelAndView("administrarmedicamentos/BuscarPedidos", modelo);
        }

        if ("V".equals(id_estado)) { //venta de medicamentos solo del Ventas
            String nombres = request.getParameter("nombre");
            nombres = nombres.replaceAll("\\s", "%");
            nombres = "%" + nombres + "%";
            Pacientes dato = new Pacientes();
            dato.setNombres(nombres);
            dato.setId_estado("E");
            dato.setTipo("T");
            dato.setCod_esta(cliente.getCod_esta());
            dato.setId_farmacia(cliente.getId_farmacia());
            List listarSinPago = this.mi.getListaMedRecibidos(dato);
            modelo.put("listapago", listarSinPago);
            return new ModelAndView("administrarmedicamentos/BuscarPedidos", modelo);
        }

        if ("A".equals(id_estado) || "C".equals(id_estado)) { //venta de medicamentos solo del Ventas
            return new ModelAndView("Aviso", "mensaje", "Esta receta esta en Proceso");
        }

        if ("H".equals(id_estado)) { //entrega de medicamentos segun HCL
            String nombres = request.getParameter("nombre");
            if ("".equals(nombres)) {
                return new ModelAndView("Aviso", "mensaje", "Coloque un numero de historia");
            }
            Pacientes dato = new Pacientes();
            dato.setHcl(Integer.parseInt(nombres));
            dato.setId_estado("%");
            dato.setTipo("H");
            dato.setCod_esta(cliente.getCod_esta());
            dato.setId_farmacia(cliente.getId_farmacia());
            List listarSinPago = this.mi.getListaMedRecibidosHcl(dato);
            modelo.put("listapago", listarSinPago);
            return new ModelAndView("administrarmedicamentos/BuscarPedidos", modelo);
        }

        if ("mostrar".equals(sAccion)) {
            String id_pedido = request.getParameter("id_pedido");
            String id_factura = request.getParameter("id_factura");
            String total = request.getParameter("total");
            String expedido = request.getParameter("expedido");
            String nombres = request.getParameter("nombres");
            String nit = request.getParameter("nit");
            String num_cladoc = request.getParameter("num_cladoc");
            String fecha = request.getParameter("fecha");
            String anio = request.getParameter("anio");
            String tag = request.getParameter("tag");
            String mes = request.getParameter("mes");
            String dia = request.getParameter("dia");
            String hora = request.getParameter("hora");
            String minuto = request.getParameter("minuto");

            dia = fecha.substring(0, 2);
            mes = fecha.substring(3, 5);
            anio = fecha.substring(6, 10);
            hora = fecha.substring(11, 13);
            minuto = fecha.substring(14, 16);

            Medicamentos dato = new Medicamentos();
            dato.setCod_esta(cliente.getCod_esta());
            List listarprog = this.mi.getListarProgramas(dato);
            modelo.put("listarProg", listarprog);

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
            if (datoestab.getCod_esta() == 200010) {
                midato.setExpedido("S"); ///getListarKardexCodsumi
                List listarKardex2 = this.mi.getListarKardexCodsumi(midato);
                modelo.put("listarKardex", listarKardex2);
                if (buscarPaciente.getId_tipo_far() == 2) {
                    midato.setExpedido("A");
                    List listarKardex3 = this.mi.getListarKardexAjus(midato);
                    modelo.put("listarKardex", listarKardex3);
                }
            }

            Consultorios aa = new Consultorios();
            aa.setCod_esta(cliente.getCod_esta());
            aa.setId_estado("R");      //////getListarTipoRecetaCNS
            List listarTipReceta = this.mi.getListarConsultorios(aa);
            modelo.put("listarTipReceta", listarTipReceta);
            modelo.put("tiporecet", buscarPaciente.getCadena1());

            Personas persona = new Personas();
            persona.setCod_esta(cliente.getCod_esta());
            List listarPersonas = this.mi.getListarPersonasFarma(persona);
            modelo.put("listarPersonas", listarPersonas);
            modelo.put("id_persona", Integer.toString(buscarPaciente.getId_dispensa()));

            Farmacias datofar = new Farmacias();
            datofar.setCod_esta(cliente.getCod_esta());
            datofar.setTipo("H");
            List listarFarmacia = this.mi.getListarFarmaciasHosp(datofar);
            modelo.put("listarFarmacia", listarFarmacia);
            if (listarKardex.size() > 0) {
                Recetas datoReceta = (Recetas) listarKardex.get(0);
                modelo.put("id_farmacia", Integer.toString(datoReceta.getId_farmacia()));
            }

            modelo.put("dia", dia);
            modelo.put("mes", mes);
            modelo.put("anio", anio);
            modelo.put("hora", hora);
            modelo.put("minuto", minuto);
            modelo.put("id_pedido", id_pedido);
            modelo.put("nombres", nombres);
            modelo.put("expedido", expedido);
            modelo.put("id_estado", id_estado);
            modelo.put("nit", nit);
            modelo.put("tag", tag);
            modelo.put("total", total);
            modelo.put("num_cladoc", num_cladoc);
            modelo.put("id_factura", id_factura);

            return new ModelAndView("administrarmedicamentos/VerPedidoT", modelo);
        }

        if ("Arreglar".equals(sAccion)) {
            String id_medicamento = request.getParameter("id_medicamento");
            String id_medicamento2 = request.getParameter("id_medicamento2");
            String id_detalle = request.getParameter("id_detalle");
            String id_pedido = request.getParameter("id_pedido");
            String id_folio = request.getParameter("id_folio");
            String id_folio2 = request.getParameter("id_folio");
            String id_por = request.getParameter("id_persona");
            String id_factura = request.getParameter("id_factura");
            String id_programa = request.getParameter("id_programa");
            String id_farmacia = request.getParameter("id_farmacia");
            String total = request.getParameter("total");
            String expedido = request.getParameter("expedido");
            String nombres = request.getParameter("nombres");
            String nit = request.getParameter("nit");
            String num_cladoc = request.getParameter("num_cladoc");
            String anio = request.getParameter("anio");
            String mes = request.getParameter("mes");
            String dia = request.getParameter("dia");
            String hora = request.getParameter("hora");
            String minuto = request.getParameter("minuto");
            String entrada = request.getParameter("entrada");
            String salida = request.getParameter("salida");
            String ajuste = request.getParameter("ajuste");
            String costo = request.getParameter("costo");
            String precio = request.getParameter("precio");
            int tiempo = 0;

            if (id_programa == null || "".equals(id_programa)) {
                id_programa = "0";
            }

            Medicamentos datoz = new Medicamentos();
            datoz.setCod_esta(cliente.getCod_esta());
            List listarprog = this.mi.getListarProgramas(datoz);
            modelo.put("listarProg", listarprog);

            if (id_programa != null && !"".equals(id_programa)) {
                datoz.setId_programa(Integer.parseInt(id_programa));
                Medicamentos datoProg = this.mi.getDatoPrograma(datoz); // saca un registro a ser modificado 
            }

            Recetas dato = new Recetas();
            dato.setId_detalle(Integer.parseInt(id_detalle));
            dato.setCod_esta(cliente.getCod_esta());
            List listarKardexUni = this.mi.getKardexUnitario(dato);

            if (!listarKardexUni.isEmpty()) {
                Recetas datoin = (Recetas) listarKardexUni.get(0);/////11/02/2016 si ya paso mas de un dia no puede modificar
                id_folio = Integer.toString(datoin.getNum_recetak());
                long fechakardex = datoin.getFecha().getTime();
                long fechanow = fechaact.getTime();
                long diferencia = fechanow - fechakardex;
                double diast = Math.floor(diferencia / (1000 * 60 * 60 * 24));
                double horast = Math.floor(diferencia / (1000 * 60 * 60));
                double minut = Math.floor(diferencia / (1000 * 60)); //////25/08/2016 santa cruz  24 horas
                if (cliente.getCod_esta() != 700241 && datoestab.getCod_esta() == 200010 && horast > 40 && cliente.getId_rol2() != 30 && cliente.getId_rol2() != 32) { //// rol 30 solo almacen puede modificar
                    return new ModelAndView("Aviso", "mensaje", "Ud. Ya no puede modificar este regsitro de receta");
                }
            }
            ////solo para santa cruz mientras 25/08/2016
            // if(Integer.parseInt(id_folio)!=Integer.parseInt(id_folio2) && cliente.getId_rol()==7 ){
            //   return new ModelAndView("Aviso","mensaje","Ud. no esta autorizado para modificar este folio"); 
            // }

            if (Integer.parseInt(id_por) != cliente.getId_persona() && cliente.getId_rol2() != 30 && cliente.getId_rol2() != 32) { //// rol 30 solo almacen puede modificar
                Personas buscarEmpleado = this.mi.getDatosPersona(Integer.parseInt(id_por));
                return new ModelAndView("Aviso", "mensaje", "Ud. no esta autorizado para modificar este medicamento, solo : " + buscarEmpleado.getNombres());
            }

            ///arreglarobrero
            Medicamentos medic = new Medicamentos();
            medic.setId_medicamento(Integer.parseInt(id_medicamento));
            medic.setCodigo(cliente.getCod_esta());
            medic.setCod_esta(cliente.getCod_esta());
            medic.setId_farmacia(cliente.getId_farmacia());
            medic.setExpedido("B");    /////getDatosMedicamentoB
            Medicamentos buscarMedicamento = this.mi.getDatosMedicamentoB(medic);

            if ("".equals(entrada) || entrada == null) {
                entrada = "0";
            }
            if ("".equals(salida) || salida == null) {
                salida = "0";
            }
            if ("".equals(ajuste) || ajuste == null) {
                ajuste = "0";
            }
            if ("".equals(costo) || costo == null) {
                costo = Double.toString(buscarMedicamento.getCosto_unit());
            }
            if ("".equals(precio) || precio == null) {
                precio = Double.toString(buscarMedicamento.getPrecio_venta());
            }

            dato.setEntrada(Double.parseDouble(entrada));
            dato.setSalida(Double.parseDouble(salida));
            dato.setAjuste(Double.parseDouble(ajuste));
            dato.setCosto_unit(Double.parseDouble(costo));
            dato.setPrecio_venta(Double.parseDouble(precio));
            dato.setId_medicamento(Integer.parseInt(id_medicamento));
            dato.setId_pedido(Integer.parseInt(id_pedido));
            dato.setId_programa(Integer.parseInt(id_programa));
            dato.setId_paciente(cliente.getId_persona());
            dato.setId_estado(id_estado);
            if (!"R".equals(id_estado)) {
                dato.setId_estado("E");/////dato.setId_estado(id_estado);    cabiara a precios de venta todos
            }
            if ("E".equals(expedido)) {
                expedido = "V";
            }
            if (Integer.parseInt(id_programa) > 5) {
                expedido = "P";
            }
            if (!"id_medicamento".equals(id_medicamento2)) {
                dato.setCosto_unit(buscarMedicamento.getCosto_unit());
                dato.setPrecio_venta(buscarMedicamento.getPrecio_venta());
                dato.setNro_lote(buscarMedicamento.getNro_lote());
            }
            dato.setExpedido(expedido);
            dato.setNum_recetak(Integer.parseInt(id_folio2));///20/12/2015
            //dato.setCadena1(ip.getHostAddress());///01/09/2014
            //dato.setCadena2(ip.getHostName());///01/09/2014
            dato.setCadena1(ip);///01/03/2016
            dato.setCadena2(host);///01/03/2016
            dato.setId_farmacia(cliente.getId_farmacia());//08/01/2018
            dato.setCod_esta(cliente.getCod_esta());///28/10/2014
            this.mi.setModificarKardex(dato);////28/12/2017  ver los de id_estado R y E como se genera

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

            if (datoestab.getCod_esta() == 200010) {
                midato.setExpedido("S");
                if (buscarPaciente.getId_tipo_far() == 2) {
                    midato.setExpedido("A");
                }
            }

            List listarKardex = this.mi.getListarKardex(midato);
            modelo.put("listarKardex", listarKardex);

            Consultorios aa = new Consultorios();
            aa.setCod_esta(cliente.getCod_esta());
            aa.setId_estado("R");      //////getListarTipoRecetaCNS
            List listarTipReceta = this.mi.getListarConsultorios(aa);
            modelo.put("listarTipReceta", listarTipReceta);
            modelo.put("tiporecet", buscarPaciente.getCadena1());

            Personas persona = new Personas();
            persona.setCod_esta(cliente.getCod_esta());
            List listarPersonas = this.mi.getListarPersonasFarma(persona);
            modelo.put("listarPersonas", listarPersonas);
            modelo.put("id_persona", Integer.toString(buscarPaciente.getId_dispensa()));

            Farmacias datofar = new Farmacias();
            datofar.setCod_esta(cliente.getCod_esta());
            datofar.setTipo("H");
            List listarFarmacia = this.mi.getListarFarmaciasHosp(datofar);
            modelo.put("listarFarmacia", listarFarmacia);
            if (listarKardex.size() > 0) {
                Recetas datoReceta = (Recetas) listarKardex.get(0);
                modelo.put("id_farmacia", Integer.toString(datoReceta.getId_farmacia()));
            }

            modelo.put("dia", dia);
            modelo.put("mes", mes);
            modelo.put("anio", anio);
            modelo.put("id_pedido", id_pedido);
            modelo.put("id_historial", Integer.toString(buscarPaciente.getId_carpeta()));
            modelo.put("nombres", nombres);
            modelo.put("expedido", expedido);
            modelo.put("id_estado", id_estado);
            modelo.put("id_detalle", id_detalle);
            modelo.put("nit", nit);
            modelo.put("total", Double.toString(buscarPaciente.getPrecio_total()));
            modelo.put("num_cladoc", num_cladoc);
            modelo.put("id_factura", id_factura);

            return new ModelAndView("administrarmedicamentos/VerPedidoT", modelo);
        }

        if ("Cambiar".equals(sAccion)) {
            String id_pedido = request.getParameter("id_pedido");
            String id_factura = request.getParameter("id_factura");
            String id_farmacia = request.getParameter("id_farmacia");
            String id_persona = request.getParameter("id_persona");
            String tag = request.getParameter("tag");
            String taga = request.getParameter("taga");
            String total = request.getParameter("total");
            String nombres = request.getParameter("nombres");
            String nit = request.getParameter("nit");
            String num_cladoc = request.getParameter("num_cladoc");
            String anio = request.getParameter("anio");
            String mes = request.getParameter("mes");
            String dia = request.getParameter("dia");
            String hora = request.getParameter("hora");
            String minuto = request.getParameter("minuto");

            int iAnio = Integer.parseInt(anio) - 1900;
            int iMes = Integer.parseInt(mes) - 1;
            int iDia = Integer.parseInt(dia);
            int iHora = Integer.parseInt(hora);
            int iMinuto = Integer.parseInt(minuto);

            Date Fechai = new Date(iAnio, iMes, iDia, iHora, iMinuto);
            if ("".equals(id_farmacia) || id_farmacia == null) {
                id_farmacia = Integer.toString(cliente.getId_farmacia());
            }

            Pacientes dato = new Pacientes();
            dato.setId_pedido(Integer.parseInt(id_pedido));
            dato.setCod_esta(cliente.getCod_esta());
            dato.setRegistro("U");   //////getListaMedKEntregadosUni
            List listarSinPago = this.mi.getListaMedKEntregadosUni(dato);

            if (!listarSinPago.isEmpty()) {
                Pacientes datoin = (Pacientes) listarSinPago.get(0);/////11/02/2016 si ya paso mas de un dia no puede modificar
                long fechaped = datoin.getFec_registro().getTime();
                long fechaant = fechaact.getTime(); ////04/05/2016 si se pode fecha actual 'Fechai' saca decha de pedido y estaba al reves (fechaant - fechaped)
                long fechacam = Fechai.getTime();
                long diferencia = fechaant - fechaped;
                long diferencia2 = fechacam - fechaant;
                double mest = Math.floor(diferencia / (1000 * 60 * 60 * 24 * 12));
                double diast = Math.floor(diferencia / (1000 * 60 * 60 * 24));
                double horast = Math.floor(diferencia / (1000 * 60 * 60));
                double minut = Math.floor(diferencia / (1000 * 60));
                double tiempo = diast * 24 + horast;
                id_tipo_far = Integer.toString(datoin.getId_tipo_far());
                taga = datoin.getCadena1();
                if (diferencia2 > 0) {
                    return new ModelAndView("Aviso", "mensaje", "La fecha que desea ingresar NO es correcta, esta adelantada a HOY");
                }
                if (cliente.getCod_esta() != 700241 && datoestab.getCod_esta() == 200010 && horast > 44 && cliente.getId_rol2() != 30 && cliente.getId_rol2() != 32) { //// rol 30 solo almacen y 32 adminfarmacia puede modificar
                    return new ModelAndView("Aviso", "mensaje", "Ud. Ya no puede modificar este regsitro");
                }
            }

            dato.setId_factura(Integer.parseInt(id_factura));
            dato.setId_farmacia(Integer.parseInt(id_farmacia));
            dato.setNum_cladoc(num_cladoc);
            dato.setNit(nit);
            dato.setNombres(nombres);
            dato.setPrecio_total(Double.parseDouble(total));
            dato.setFec_registro(Fechai);
            //dato.setCadena1(ip.getHostAddress());
            //dato.setCadena2(ip.getHostName());
            dato.setCadena1(ip);///01/03/2016
            dato.setCadena2(host);///01/03/2016
            dato.setCadena5(tag);
            if ("".equals(id_persona) || id_persona == null) {
                id_persona = Integer.toString(cliente.getId_persona());
            }
            dato.setId_persona(Integer.parseInt(id_persona));

            try {
                this.mi.setModificarPedidos(dato);
                return new ModelAndView("Aviso", "mensaje", "Se Modifico Correctamente");
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "Existe datos erroneos");
            }
        }
        modelo.put("valor_1", sFecha_ini);
        modelo.put("valor_2", sFecha_fin);

        return new ModelAndView("administrarmedicamentos/BuscarPedidos", modelo);

    }
}
