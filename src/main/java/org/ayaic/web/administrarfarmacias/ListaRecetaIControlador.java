package org.ayaic.web.administrarfarmacias;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Farmacias;
import org.ayaic.domain.Historiales;
import org.ayaic.domain.Localidades;
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
public class ListaRecetaIControlador {

    private final MiFacade mi;

    public ListaRecetaIControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/ListaRecetaI.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String accionm = request.getParameter("accionm");
        String id_historial = request.getParameter("id_historial");
        String id_historia = request.getParameter("id_historia");
        String id_paciente = request.getParameter("id_paciente");
        String id_persona = request.getParameter("id_persona");
        String id_kardex = request.getParameter("id_kardex");
        String id_tipointer = request.getParameter("id_tipointer");
        String id_pedido = request.getParameter("id_pedido");
        String id_receta = request.getParameter("id_receta");
        String id_doctor = request.getParameter("id_doctor");
        String tag = request.getParameter("tag");
        String id_programa = request.getParameter("id_programa");
        String fechar = request.getParameter("fechar");
        String id_medicamento = request.getParameter("id_medicamento");
        String ip = request.getRemoteAddr();
        String host = request.getRemoteHost();
        Date Fecha1 = new Date();
        Date Fecha2 = new Date();
        String Medim = "no";
        int inter = 0;

        Localidades localidad = new Localidades();
        List Estab1 = this.mi.getListarEsta(localidad);
        Localidades datoestab = (Localidades) Estab1.get(0);
        modelo.put("codesta", Integer.toString(datoestab.getCod_esta()));
        modelo.put("cod_esta", Integer.toString(datoestab.getCod_esta()));
        modelo.put("id_tipointer", id_tipointer);
        modelo.put("tag", tag);
        modelo.put("accionm", accionm);

        ///List listarSeguros = this.mi.getListarSeguros("A");
        ///modelo.put("listaPacAfi", listarSeguros);   
        if (cliente.getId_farmacia() > 0) {
            Farmacias datofar = new Farmacias();
            datofar.setCod_esta(cliente.getCod_esta());
            datofar.setId_farmacia(cliente.getId_farmacia());
            Farmacias buscarfarmacia = this.mi.getDatosFarmacia(datofar); // saca un registro a ser modificado
            modelo.put("farmacia", buscarfarmacia.getFarmacia());
        }

        Historiales datohi = new Historiales();
        datohi.setId_historial(Integer.parseInt(id_historial));
        datohi.setCod_esta(cliente.getCod_esta());
        Historiales datoHis = this.mi.getDatosHistorial(datohi);
        modelo.put("datosHis", datoHis);
        if (datohi.getId_seguro() == 0) {
            modelo.put("id_programa", "1");
        } else {
            modelo.put("id_programa", Integer.toString(datoHis.getId_seguro()));
        }

        if ((id_historia == null || "".equals(id_historia)) || (id_persona == null || "".equals(id_persona))) {////22/02/2016 no salia recetas del mismo dia el mismo paciente pero otros medicos, sacaba el maximo y no correspondia  
            if (datoHis != null) {
                if (datoHis.getId_cargo() > 1) {
                    List listasInter = this.mi.getHistoriaInter(datohi);
                    Historiales Inter = (Historiales) listasInter.get(0);
                    inter = Inter.getId_historia();
                    id_persona = Integer.toString(Inter.getId_persona());////03-07-2014 aumente para que salga el nombre 
                    //id_doctor=Integer.toString(Inter.getId_persona());////03-07-2014 aumente para que salga el nombre 
                } else {
                    inter = 0;
                }
            }
        } else {
            inter = Integer.parseInt(id_historia);
        }

        if (id_programa == null || id_programa == "") {
            id_programa = "0";
        }

        if ("Receta".equals(accion)) {   ////imprime la receta       
            Prestaciones prestac = new Prestaciones();
            prestac.setId_historial(Integer.parseInt(id_historial));
            prestac.setId_persona(Integer.parseInt(id_doctor));////21-02-2018 lo cambie el id_persona por id_doctor
            prestac.setCod_esta(cliente.getCod_esta());
            List listarPre = this.mi.getListarSumiRecetasIntImp(prestac);
            modelo.put("listarPres", listarPre);

            Personas buscarEmpleado = this.mi.getDatosPersonaInt(Integer.parseInt(id_doctor));
            Personas buscarEmpleado2 = this.mi.getDatosPersona(datoHis.getId_persona());

            if (buscarEmpleado == null) {
                modelo.put("datosMed", buscarEmpleado2);
            } else {
                modelo.put("datosMed", buscarEmpleado);
            }

            Pacientes buscarPac = this.mi.getDatosPaciente(datoHis.getId_paciente());
            modelo.put("datosPac", buscarPac);

            Recetas datore = new Recetas();
            datore.setId_historial(Integer.parseInt(id_historial));
            datore.setId_historia(Integer.parseInt(id_historia));
            datore.setId_persona(Integer.parseInt(id_doctor));
            datore.setId_farmacia(cliente.getId_farmacia());
            datore.setCod_esta(cliente.getCod_esta());
            datore.setId_estado("I");  ///05/02/2016 getListarRecetasMedicoImp para impresion sin importar farmacia desde otro usuario
            List listarRecetas = this.mi.getListarRecetasMedicoImp(datore); ////las ya entregadas que cambian de estado 
            modelo.put("listarRecetas", listarRecetas);

            datore.setId_estado("I");
            List listarKardex = this.mi.getListarKardexIImpRec(datore);/////getListarKardexIImpRec
            modelo.put("listarKardex", listarKardex);

            //id_pedido=id_historial;
            Pacientes paciente1 = new Pacientes();
            paciente1.setId_pedido(Integer.parseInt(id_pedido));
            paciente1.setCod_esta(cliente.getCod_esta());
            Pacientes paciente = this.mi.getDatosPedido(paciente1);
            modelo.put("datos", paciente);
            modelo.put("dato", cliente);

            Historiales reserva = new Historiales();
            reserva.setId_estado("B");
            reserva.setId_historia(Integer.parseInt(id_historia));
            this.mi.setModificarInter(reserva);

            if (datoestab.getCod_esta() == 200010) {
                Historiales dato = new Historiales();
                dato.setId_estado("A");
                dato.setExpedido("%");
                dato.setId_farmacia(cliente.getId_farmacia());
                dato.setCod_esta(cliente.getCod_esta());

                List listarAtendidosI = this.mi.getListarAtendidosI(dato);///getListarAtendidosIIgn solo para san ignacio
                modelo.put("listarAtendidosI", listarAtendidosI);

                List listarAtendidos = this.mi.getListarAtendidos(dato);
                modelo.put("listarAtendidos", listarAtendidos);
                if (listarAtendidos.size() > 0) {
                    modelo.put("listafar", "1");
                }
                if (listarAtendidosI.size() > 0) {
                    modelo.put("listafari", "1");
                }
                return new ModelAndView("administrarfarmacias/ListarAtendidos", modelo);
            } else {
                return new ModelAndView(new ListarRecetaIntPDF(), modelo);
            }

        }
        double total;

        if ("entregarya".equals(accion)) {

            String nombres = request.getParameter("nombres");
            String servicio = request.getParameter("servicio");
            String sig_centro = request.getParameter("sig_centro");
            String especialidad = request.getParameter("especialidad");
            String codigoprof = request.getParameter("codigoprof");
            String nit = request.getParameter("nit");

            if (nit == null || nit == "") {
                nit = "0";
            }
            if (sig_centro == null || sig_centro == "") {
                sig_centro = "0";
            }
            ///Localidades localidad= new Localidades() ;
            localidad.setArea("E");   ////getDatosEstable
            localidad.setCod_esta(Integer.parseInt(sig_centro));
            Localidades buscarestab = this.mi.getDatosEstable(localidad);

            Pacientes buscarPac = this.mi.getDatosPaciente(datoHis.getId_paciente());
            modelo.put("datosPac", buscarPac);

            Pacientes paciente = new Pacientes();
            paciente.setId_persona(Integer.parseInt(id_persona));  ////22/02/2016 datoHis.getId_persona()
            paciente.setId_dispensa(cliente.getId_persona());
            paciente.setCod_esta(cliente.getCod_esta());
            paciente.setNit(nit);
            paciente.setNum_cladoc("0");

            if ("S".equals(datoHis.getExpedido())) {
                paciente.setNum_cladoc(this.mi.getNumRegistro(datoHis.getId_paciente()));
                paciente.setId_rubro(1);
                paciente.setId_estado("S");
                paciente.setNit("0" + (Integer.toString(1 + Integer.parseInt(this.mi.getNumSumi("S", cliente.getCod_esta())))));
                if (datoHis.getId_seguro() == 0) {
                    modelo.put("id_programa", "1");
                } else {
                    modelo.put("id_programa", Integer.toString(datoHis.getId_seguro()));
                }
                modelo.put("expedido", "S");
            } else if ("P".equals(datoHis.getExpedido())) {
                paciente.setNum_cladoc(this.mi.getNumRegistro(datoHis.getId_paciente()));
                if (datoestab.getCod_esta() == 200010) {
                    paciente.setNum_cladoc(buscarPac.getNro_registro());
                }
                paciente.setId_rubro(1);
                paciente.setId_estado("P");
                modelo.put("expedido", "P");
                modelo.put("id_programa", "4");
            } else {
                paciente.setId_estado("A");
                paciente.setId_rubro(1);
                modelo.put("expedido", "V");
                modelo.put("id_programa", "0");
            }

            paciente.setNombres(nombres);
            paciente.setPrecio_total(0);
            paciente.setId_paciente(Integer.parseInt(id_historial));

            paciente.setId_paciente(datoHis.getId_paciente());
            paciente.setId_carpeta(Integer.parseInt(id_historial));
            paciente.setId_pais(Integer.parseInt(id_historia));
            paciente.setCadena1("E");
            paciente.setTipo("B");
            paciente.setCod_esta(cliente.getCod_esta());
            paciente.setId_factura(this.mi.getNumReceta(paciente));/////0 sin factura, 1 con factura, 2, 3, 4, para num correlativo, entradas, ajuste+ y ajuste-
            paciente.setFec_registro(Fecha1);
            if (datoestab.getCod_esta() == 200010) {
                if ("U".equals(tag) || "N".equals(tag) || "M".equals(tag)) {
                    Recetas midato = new Recetas();
                    midato.setId_historial(Integer.parseInt(id_historial));
                    midato.setId_farmacia(cliente.getId_farmacia());
                    midato.setCod_esta(cliente.getCod_esta());
                    midato.setId_historia(inter);
                    midato.setId_estado("N");
                    this.mi.setModificarRecetaNumera(midato);   ////setModificarRecetaNumera
                }
                paciente.setCadena1(tag);
                paciente.setCadena2(buscarestab.getCod_cen());
                paciente.setCadena3(servicio);
                paciente.setCadena4(especialidad);
                paciente.setCadena5(codigoprof);
                if (buscarPac.getNro_registro().length() > 9) {
                    paciente.setCadena6(buscarPac.getNro_registro().substring(6, 10));
                } else {
                    paciente.setCadena6(buscarPac.getNro_registro().substring(6, 9));
                }
                paciente.setCadena7(buscarPac.getNro_registro().substring(0, 6));
                paciente.setCadena8(buscarPac.getNro());
                paciente.setTipo("C");
            }
            this.mi.setCrearPeedido(paciente);

            id_pedido = Integer.toString(this.mi.getNumPedido(paciente));
            datoHis.setId_historial(Integer.parseInt(id_historial));
            datoHis.setId_estado("B");
            modelo.put("id_doctor", id_doctor);
            this.mi.setModificarEstadoHistoria(datoHis);
        }

        if ("EntregarTodo".equals(accion)) {////se tiene quen reviar para entregar recetas otros hopsitales
            String swe = request.getParameter("swe");
            int swc = 0;

            Recetas datore = new Recetas();
            datore.setId_historial(Integer.parseInt(id_historial));
            datore.setId_estado("A");//// "A" solo los que estan sin entregar en recetas
            datore.setId_persona(Integer.parseInt(id_doctor));
            datore.setId_historia(Integer.parseInt(id_historia));
            datore.setId_farmacia(cliente.getId_farmacia());
            datore.setCod_esta(cliente.getCod_esta());
            if ("U".equals(tag) || "N".equals(tag) || "M".equals(tag)) {
                datore.setId_historia(inter);
                datore.setId_estado("N");
                this.mi.setModificarRecetaNumera(datore);   ////setModificarRecetaNumera
            }
            List listarRecetasS = this.mi.getListarRecetasInt(datore);

            for (int i = 0; i < listarRecetasS.size(); i++) {
                Recetas datoReceta = (Recetas) listarRecetasS.get(i);

                Medicamentos medic = new Medicamentos();
                medic.setId_medicamento(datoReceta.getId_medicamento());
                medic.setCodigo(cliente.getCod_esta());
                medic.setCod_esta(cliente.getCod_esta());
                medic.setId_farmacia(cliente.getId_farmacia());
                medic.setExpedido("B");    /////getDatosMedicamentoB
                Medicamentos buscarMedicamento = this.mi.getDatosMedicamentoB(medic);

                swe = "SI";

                if (buscarMedicamento.getStock() < datoReceta.getSalida() && datoestab.getFar_sinstock() == 0) {
                    if (datoestab.getCod_esta() == 200010) {
                        swe = "NO";
                        swc = swc + 1;
                    }
                }

                if ("SI".equals(swe)) {

                    Recetas dato = new Recetas();
                    dato.setId_pedido(Integer.parseInt(id_pedido));
                    dato.setId_medicamento(datoReceta.getId_medicamento());
                    dato.setSalida(datoReceta.getSalida());
                    dato.setEntrada(0);
                    dato.setCosto_unit(buscarMedicamento.getCosto_unit());
                    dato.setPrecio_venta(buscarMedicamento.getPrecio_venta());
                    dato.setExpedido(datoReceta.getExpedido());
                    dato.setId_receta(datoReceta.getId_detalle());
                    dato.setNumeracion(datoReceta.getNumeracion());
                    dato.setNro_lote(buscarMedicamento.getNro_lote());
                    dato.setFecha_ven(buscarMedicamento.getFecha_ven());
                    dato.setId_programa(Integer.parseInt(id_programa));////02-07-2015

                    if ("E".equals(datoHis.getExpedido()) || "V".equals(datoHis.getExpedido())) {
                        buscarMedicamento.setStockv(buscarMedicamento.getStockv() - dato.getSalida());
                        dato.setExpedido("V");
                        dato.setId_programa(0);
                    } else if ("P".equals(datoHis.getExpedido())) {
                        buscarMedicamento.setStockp(buscarMedicamento.getStockp() - dato.getSalida());
                    } else {
                        buscarMedicamento.setStocks(buscarMedicamento.getStocks() - dato.getSalida());
                    }

                    dato.setFecha(Fecha1);

                    if ("Modificar".equals(accionm)) {
                        int diax = Integer.parseInt(fechar.substring(0, 2));
                        int mesx = Integer.parseInt(fechar.substring(3, 5));
                        int aniox = Integer.parseInt(fechar.substring(6, 10));
                        int horax = Fecha1.getHours();
                        int minutox = Fecha1.getMinutes();

                        Date fechaa = new Date(aniox - 1900, mesx - 1, diax, horax, minutox, 00);
                        dato.setFecha(fechaa);
                        dato.setFecha_ini(Fecha1);
                    } else {
                        dato.setFecha(Fecha1);
                    }

                    dato.setCod_esta(cliente.getCod_esta());
                    dato.setId_farmacia(cliente.getId_farmacia());
                    dato.setId_persona(cliente.getId_persona());
                    dato.setId_factura(0);
                    dato.setId_historial(datoReceta.getId_historial());
                    dato.setId_historia(datoReceta.getId_historia());
                    dato.setId_medico(Integer.parseInt(id_doctor));
                    dato.setSaldos(0);
                    dato.setDosifica(datoReceta.getDosifica());
                    dato.setFecha_ini(datoReceta.getFecha()); ////si es de otro dia lo folia con fecha de hoy
                    dato.setCadena1(ip);///01/03/2016
                    dato.setCadena2(host);///01/03/2016
                    //this.mi.setCrearKardex(dato) ;     
                    int iResultado = this.mi.setRegistrarKardex(dato);
//                    buscarMedicamento.setCod_esta(cliente.getCod_esta());
//                    buscarMedicamento.setStock(buscarMedicamento.getStock() - dato.getSalida());
//                    this.mi.setModificarMedicamentoStock(buscarMedicamento);

                    ///Recetas datore=new Recetas();
                    datore.setId_historial(datoReceta.getId_historial());
                    datore.setId_historia(datoReceta.getId_historia());
                    datore.setId_persona(datoReceta.getId_persona());
                    datore.setId_estado("B");
                    datore.setId_farmacia(cliente.getId_farmacia());
                    datore.setCod_esta(cliente.getCod_esta());
                    List listaRece = this.mi.getListarRecetas(datore);
                    datore.setId_estado("A");
                    List listaRece2 = this.mi.getListarRecetasS(datore);
                    for (int m = 0; m < listaRece.size(); m++) {
                        Recetas datoRecetas = (Recetas) listaRece.get(m);
                        dato.setId_estado("C");
                        dato.setId_detalle(datoRecetas.getId_detalle());
                        dato.setId_medicamento(datoReceta.getId_medicamento());
                        dato.setCod_esta(cliente.getCod_esta());
                        //if(sig_centro!=null && !"".equals(sig_centro) && id_farmacia2!=null ){////se cambia de id_farmacia3 a 2 no funcionaba para otros hospitales
                        //    dato.setCod_esta(Integer.parseInt(sig_centro)) ; 
                        //}
                        this.mi.setModificarEstadoInter(dato);
                    }
                    for (int n = 0; n < listaRece2.size(); n++) {
                        Recetas datoReceta3 = (Recetas) listaRece2.get(n);
                        if (datoReceta3.getId_detalle() == datoReceta.getId_detalle()) {
                            dato.setId_estado("B");
                            dato.setId_detalle(datoReceta3.getId_detalle());
                            dato.setId_medicamento(datoReceta.getId_medicamento());
                            dato.setCod_esta(cliente.getCod_esta());
                            this.mi.setModificarEstadoInter(dato);
                        }
                    }

                }
            }

            datore.setId_estado("A");
            List listaRece12 = this.mi.getListarRecetasS(datore);
            if (listaRece12.size() - swc == 0) {
                datoHis.setId_historial(Integer.parseInt(id_historial));
                datoHis.setId_historia(Integer.parseInt(id_historia));
                datoHis.setId_estado("B");
                datoHis.setAccion("R");  ////setModificarInterReceta
                this.mi.setModificarInterReceta(datoHis);
            }

        }

        if ("adicion".equals(accion)) {
            String expedido = request.getParameter("expedido");
            String cantidad = request.getParameter("cantidad");
            String id_detalle = request.getParameter("id_detalle");
            String numeracion = request.getParameter("numeracion");
            String stock = request.getParameter("stock");

            Recetas midato = new Recetas();
            midato.setId_pedido(Integer.parseInt(id_pedido));
            midato.setId_prestacion(cliente.getCod_esta());
            midato.setCod_esta(cliente.getCod_esta());
            midato.setId_farmacia(cliente.getId_farmacia());
            midato.setId_detalle(Integer.parseInt(id_detalle));
            midato.setId_medicamento(Integer.parseInt(id_medicamento));
            List listarKardexrev = this.mi.getListarKardexComprueba(midato);

            if (Double.parseDouble(stock) < Double.parseDouble(cantidad) && datoestab.getFar_sinstock() == 0) {
                cantidad = "0";
                if (datoestab.getCod_esta() == 200010) {
                    return new ModelAndView("Aviso", "mensaje", "No puede entregar esta cantidad, el Saldo es menor al que quiere entregar");
                }
            }

            //Recetas datore=new Recetas();
            midato.setId_historial(Integer.parseInt(id_historial));
            midato.setId_estado("A");
            List listarRecetasS = this.mi.getListarRecetas(midato);

            Recetas datoRecetare = (Recetas) listarRecetasS.get(0); ////busca el primer dato de la receta del medico

            if (Double.parseDouble(stock) < Double.parseDouble(cantidad) && datoestab.getFar_sinstock() == 0) {
                cantidad = "0";
            }
            Medicamentos medic = new Medicamentos();
            medic.setId_medicamento(Integer.parseInt(id_medicamento));
            medic.setCodigo(cliente.getCod_esta());
            medic.setCod_esta(cliente.getCod_esta());
            medic.setId_farmacia(cliente.getId_farmacia());
            medic.setExpedido("B");/// getDatosMedicamentoB 27-02-2014 no funciona si no existe el medicamento en esta farmacia
            Medicamentos buscarMedicamento = this.mi.getDatosMedicamentoB(medic); ///saca el medicamento buscado

            Recetas dato = new Recetas();
            dato.setId_pedido(Integer.parseInt(id_pedido));
            dato.setId_medicamento(Integer.parseInt(id_medicamento));
            dato.setSalida(Double.parseDouble(cantidad));
            dato.setEntrada(0);
            dato.setCosto_unit(buscarMedicamento.getCosto_unit());
            dato.setPrecio_venta(buscarMedicamento.getPrecio_venta());
            dato.setExpedido(expedido);
            dato.setId_receta(Integer.parseInt(id_detalle));
            dato.setNro_lote(buscarMedicamento.getNro_lote());
            dato.setFecha_ven(buscarMedicamento.getFecha_ven());
            dato.setNumeracion(Integer.parseInt(numeracion));

            // actualizamos el stock del medicamento
            if ("E".equals(expedido) || "V".equals(expedido)) {
                buscarMedicamento.setStockv(buscarMedicamento.getStockv() - dato.getSalida());
                dato.setExpedido("V");
                dato.setId_programa(0);
            } else if ("P".equals(expedido)) {
                dato.setId_programa(3);
                buscarMedicamento.setStockp(buscarMedicamento.getStockp() - dato.getSalida());
                dato.setId_programa(Integer.parseInt(id_programa));////20-01-2014
            } else {
                buscarMedicamento.setStocks(buscarMedicamento.getStocks() - dato.getSalida());
                dato.setId_programa(Integer.parseInt(id_programa));////20-01-2014
            }

            dato.setFecha(Fecha1);
            dato.setCod_esta(cliente.getCod_esta());
            dato.setId_farmacia(cliente.getId_farmacia());
            dato.setId_persona(cliente.getId_persona());
            dato.setId_factura(0);
            dato.setId_historial(Integer.parseInt(id_historial));
            dato.setId_historia(datoHis.getId_cargo());
            dato.setId_medico(Integer.parseInt(id_doctor));
            dato.setSaldos(0);
            dato.setDosifica(datoRecetare.getDosifica());
            dato.setFecha_ini(datoRecetare.getFecha()); ////si es de otro dia lo folia con fecha de hoy
            dato.setCadena1(ip);///01/03/2016
            dato.setCadena2(host);///01/03/2016
            //}
            if (listarKardexrev.size() == 0) {
                // this.mi.setCrearKardex(dato);  
                int iResultado = this.mi.setRegistrarKardex(dato);
            }

//            buscarMedicamento.setCod_esta(cliente.getCod_esta());
//            buscarMedicamento.setStock(buscarMedicamento.getStock() - dato.getSalida());
//            this.mi.setModificarMedicamentoStock(buscarMedicamento);
            // eliminamos de la lista de recetas
            modelo.put("expedido", expedido);
            modelo.put("id_programa", id_programa);
            dato.setId_historial(Integer.parseInt(id_historial));

            Recetas datore = new Recetas();
            datore.setId_historial(Integer.parseInt(id_historial));
            datore.setId_historia(Integer.parseInt(id_historia));
            datore.setId_persona(Integer.parseInt(id_doctor));
            datore.setId_estado("B");
            datore.setId_farmacia(cliente.getId_farmacia());
            datore.setCod_esta(cliente.getCod_esta());
            List listarRece = this.mi.getListarRecetas(datore);
            datore.setId_estado("A");
            List listarRece2 = this.mi.getListarRecetasS(datore);
            for (int i = 0; i < listarRece.size(); i++) {
                Recetas datoReceta = (Recetas) listarRece.get(i);
                // if(datoReceta.getId_detalle()==Integer.parseInt(id_detalle)){
                dato.setId_estado("C");
                dato.setId_detalle(datoReceta.getId_detalle());
                dato.setId_medicamento(Integer.parseInt(id_medicamento));
                dato.setCod_esta(cliente.getCod_esta());
                this.mi.setModificarEstadoInter(dato);
            }
            // }
            for (int i = 0; i < listarRece2.size(); i++) {
                Recetas datoReceta = (Recetas) listarRece2.get(i);
                if (datoReceta.getId_detalle() == Integer.parseInt(id_detalle)) {
                    dato.setId_estado("B");
                    dato.setId_detalle(datoReceta.getId_detalle());
                    dato.setCod_esta(cliente.getCod_esta());
                    this.mi.setModificarEstadoInter(dato);
                }
            }
        }

        if ("eliminar".equals(accion)) {
            String cantidad = request.getParameter("salida");
            String expedido = request.getParameter("expedido");
            String id_por = request.getParameter("id_por");

            if (Integer.parseInt(id_por) != cliente.getId_persona() && cliente.getId_cargo() != 7) {
                Personas buscarEmpleado = this.mi.getDatosPersona(Integer.parseInt(id_por));
                return new ModelAndView("Aviso", "mensaje", "Ud. no esta autorizado para eliminar esta medicamento, solo : " + buscarEmpleado.getNombres());
            }

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
            // actualizamos el stock del medicamento
            if ("V".equals(expedido)) {
                buscarMedicamento.setStockv(buscarMedicamento.getStockv() + dato.getSalida());
            } else if ("P".equals(expedido)) {
                buscarMedicamento.setStockp(buscarMedicamento.getStockp() + dato.getSalida());
            } else {
                buscarMedicamento.setStocks(buscarMedicamento.getStocks() + dato.getSalida());
            }

            buscarMedicamento.setCod_esta(cliente.getCod_esta());
            buscarMedicamento.setStock(buscarMedicamento.getStock() + dato.getSalida());
            this.mi.setModificarMedicamentoStock(buscarMedicamento);
            // activamos en la lista de recetas
            dato.setId_historial(Integer.parseInt(id_historial));
            dato.setId_medicamento(Integer.parseInt(id_medicamento));
            dato.setId_receta(Integer.parseInt(id_receta));
            dato.setId_estado("A");
            modelo.put("expedido", expedido);
            dato.setCod_esta(cliente.getCod_esta());
            this.mi.setModificarEstadoReceta(dato);
        }

        Recetas datore = new Recetas();
        datore.setId_historial(Integer.parseInt(id_historial));
        datore.setId_estado("A");
        datore.setId_persona(Integer.parseInt(id_doctor));   ///23/02/2016 no daba WK cion id_persona datore.setId_persona(Integer.parseInt(id_persona));
        datore.setId_historia(Integer.parseInt(id_historia));
        datore.setId_farmacia(cliente.getId_farmacia());
        datore.setCod_esta(cliente.getCod_esta());
        if (datoestab.getCod_esta() == 200010) {
            datore.setId_estado("C");
            List ListarRecetas = this.mi.getListarRecetasMedicoCNS(datore);
            modelo.put("listarRecetas", ListarRecetas);
        } else {
            List listarRecetasS = this.mi.getListarRecetasInt(datore);
            modelo.put("listarRecetas", listarRecetasS);
        }

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

        if (datoHis != null) {
            if (datoHis.getId_seguro() == 1 || datoHis.getId_seguro() == 2 || datoHis.getId_seguro() == 3 || datoHis.getId_seguro() == 4) {
                modelo.put("id_programa", Integer.toString(datoHis.getId_seguro()));
            } else {
                if (datoHis.getId_seguro() > 4) {
                    for (int i = 0; i < listarprog.size(); i++) {
                        Medicamentos listaPrg = (Medicamentos) listarprog.get(i);
                        if (datoHis.getId_seguro() == listaPrg.getB1()) {
                            modelo.put("id_programa", Integer.toString(listaPrg.getId_programa()));
                        }
                    }
                }
            }
        }

        // Calculamos el total a pagar
        total = 0;
        for (int i = 0; i < listarKardex.size(); i++) {
            midato = (Recetas) listarKardex.get(i);
            total = total + midato.getPrecio_total();
            Fecha2 = midato.getFecha();
        }
        // actualiza el precio total
        Pacientes paciente1 = new Pacientes();
        paciente1.setId_pedido(Integer.parseInt(id_pedido));
        paciente1.setCod_esta(cliente.getCod_esta());
        Pacientes paciente = this.mi.getDatosPedido(paciente1);

        paciente.setPrecio_total(total);

        if ("Modificar".equals(accionm)) {
            paciente.setFecha_fin(Fecha2); ////toma la fecha anterior si solo es modificacion
            paciente.setTipo("O");   ///setModificarPedidoSinFecha 06-04-2016
            this.mi.setModificarPedidoSinFecha(paciente);
        } else {
            paciente.setFecha_fin(Fecha1); ///actualiza la fecha al dia de hoy 
            this.mi.setModificarPedido(paciente);
        }

        modelo.put("datos", paciente);

        modelo.put("id_historial", id_historial);
        modelo.put("id_historia", id_historia);
        modelo.put("id_doctor", id_doctor);
        modelo.put("id_pedido", id_pedido);

        return new ModelAndView("administrarfarmacias/ListaRecetaI", modelo);
    }
}
