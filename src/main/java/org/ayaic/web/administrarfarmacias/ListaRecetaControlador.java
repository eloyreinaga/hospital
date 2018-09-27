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
public class ListaRecetaControlador {

    private final MiFacade mi;

    public ListaRecetaControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/ListaReceta.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String accionm = request.getParameter("accionm");
        String fechar = request.getParameter("fechar");
        String id_historial = request.getParameter("id_historial");
        String id_historia = request.getParameter("id_historia");
        String id_kardex = request.getParameter("id_kardex");
        String id_pedido = request.getParameter("id_pedido");
        String id_pedido2 = request.getParameter("id_pedido");
        String id_tipointer = request.getParameter("id_tipointer");
        String id_riesgo = request.getParameter("id_riesgo");
        String id_persona = request.getParameter("id_persona");
        String sig_centro = request.getParameter("sig_centro");
        String id_farmacia2 = request.getParameter("id_farmacia2");
        String id_farmacia3 = request.getParameter("id_farmacia3");
        String id_receta = request.getParameter("id_receta");
        String id_doctor = request.getParameter("id_doctor");
        String expedido = request.getParameter("expedido");
        String tag = request.getParameter("tag");
        String id_paciente = request.getParameter("id_paciente");
        String id_medicamento = request.getParameter("id_medicamento");
        String id_programa = request.getParameter("id_programa");
        String ip = request.getRemoteAddr();
        String host = request.getRemoteHost();
        Date Fecha1 = new Date();
        int inter = 0;
        String mm = "";

        Localidades local = new Localidades();
        List Estab1 = this.mi.getListarEsta(local);
        Localidades datoestab = (Localidades) Estab1.get(0);
        modelo.put("codesta", Integer.toString(datoestab.getCod_esta()));
        modelo.put("urgencia", Integer.toString(cliente.getId_almacen()));
        modelo.put("id_tipointer", id_tipointer);
        modelo.put("id_farmacia2", id_farmacia2);
        modelo.put("sig_centro", sig_centro);
        modelo.put("id_riesgo", id_riesgo);
        modelo.put("tag", tag);
        modelo.put("accionm", accionm);

        if (cliente.getId_farmacia() > 0) {
            Farmacias datofar = new Farmacias();
            datofar.setCod_esta(cliente.getCod_esta());
            datofar.setId_farmacia(cliente.getId_farmacia());
            Farmacias buscarfarmacia = this.mi.getDatosFarmacia(datofar); // saca un registro a ser modificado
            modelo.put("farmacia", buscarfarmacia.getFarmacia());
        }

        if (id_farmacia2 == null || id_farmacia2 == "") {
            id_farmacia3 = id_farmacia2;
            id_farmacia2 = Integer.toString(cliente.getId_farmacia());
            modelo.put("id_farmacia2", id_farmacia2);
        }
        if (id_riesgo == null || id_riesgo == "") {
            id_riesgo = "0";
        }
        //if(id_historial==null || "".equals(id_historial))  id_historial="0";

        Historiales datohi = new Historiales();
        datohi.setId_historial(Integer.parseInt(id_historial));
        datohi.setCod_esta(cliente.getCod_esta());
        if (sig_centro != null && !"".equals(sig_centro)) {
            datohi.setCod_esta(Integer.parseInt(sig_centro));
        }
        Historiales datoHis = this.mi.getDatosHistorial(datohi);
        modelo.put("datosHis", datoHis);

        if (datoHis.getId_cargo() > 1) {
            List listasInter = this.mi.getHistoriaInter(datohi);
            if (!listasInter.isEmpty()) { ////14/01/2017 se aumenta para farmacia no daba internados que estaban metiendo receta
                Historiales Inter = (Historiales) listasInter.get(listasInter.size() - 1);
                inter = Inter.getId_historia();
                id_persona = Integer.toString(Inter.getId_persona());////03-07-2014 aumente para que salga el nombre 
            }
        } else {
            inter = 0;
        }

        if ("Receta".equals(accion)) {
            Prestaciones prestac = new Prestaciones();
            prestac.setId_historial(Integer.parseInt(id_historial));
            prestac.setId_persona(datoHis.getId_persona());
            prestac.setCod_esta(cliente.getCod_esta());
            List listarRecetas = this.mi.getListarSumiRecetas(prestac);
            modelo.put("listarPres", listarRecetas);

            Personas buscarEmpleado = this.mi.getDatosPersona(datoHis.getId_persona());
            modelo.put("datosMed", buscarEmpleado);

            Pacientes buscarPac = this.mi.getDatosPaciente(datoHis.getId_paciente());
            modelo.put("datosPac", buscarPac);

            Recetas midato = new Recetas();
            midato.setId_historial(Integer.parseInt(id_historial));
            midato.setCod_esta(cliente.getCod_esta());
            List listarRecetas2 = this.mi.getListarRecetasMedico(midato);
            modelo.put("listarRecetas", listarRecetas2);

            midato.setId_pedido(Integer.parseInt(id_pedido));
            midato.setCod_esta(cliente.getCod_esta());
            midato.setId_farmacia(cliente.getId_farmacia());
            List listarKardex = this.mi.getListarKardex(midato);
            modelo.put("listarKardex", listarKardex);

            Pacientes paciente1 = new Pacientes();
            paciente1.setId_pedido(Integer.parseInt(id_pedido));
            paciente1.setCod_esta(cliente.getCod_esta());
            Pacientes paciente = this.mi.getDatosPedido(paciente1);
            modelo.put("datos", paciente);

            if("E".equals(datoHis.getExpedido()) || "V".equals(datoHis.getExpedido())){   
               List listarKardexp = this.mi.getListarKardexProf(midato);
               modelo.put("listarKardex", listarKardexp); 
               Pacientes pacientep = this.mi.getDatosPedidoProf(paciente1);
               modelo.put("datos", pacientep);
            }
            modelo.put("dato", cliente);

            return new ModelAndView(new ListarRecetaPDF(), modelo);
        }

        double total;

        if ("entregarya".equals(accion)) {
            String nombres = request.getParameter("nombres");
            String servicio = request.getParameter("servicio");
            String especialidad = request.getParameter("especialidad");
            String codigoprof = request.getParameter("codigoprof");
            String nit = request.getParameter("nit");

            if (nit == null || nit == "") {
                nit = "0";
            }
            if (sig_centro == null || "".equals(sig_centro)) {
                sig_centro = "0";
            }
            if (id_programa == null || "".equals(id_programa)) {
                id_programa = "0"; ////talvez seria mejor que busque el programa del seguro en la tabla programas
            }
            Localidades localidad = new Localidades();
            localidad.setArea("E");   ////getDatosEstable
            localidad.setCod_esta(Integer.parseInt(sig_centro));
            Localidades buscarestab = this.mi.getDatosEstable(localidad);

            Pacientes buscarPac = this.mi.getDatosPaciente(datoHis.getId_paciente());
            modelo.put("datosPac", buscarPac);

            Pacientes paciente = new Pacientes();
            paciente.setId_persona(datoHis.getId_persona());
            paciente.setId_dispensa(cliente.getId_persona());
            paciente.setCod_esta(cliente.getCod_esta());
            paciente.setNit(nit);
            paciente.setNum_cladoc("0");

            if ("S".equals(datoHis.getExpedido())) {
                paciente.setNum_cladoc(this.mi.getNumRegistro(datoHis.getId_paciente()));
                paciente.setId_rubro(1);
                paciente.setId_estado("S");
                if (this.mi.getNumSumi("S", cliente.getCod_esta()) == null) {
                    nit = "0";
                } else {
                    nit = this.mi.getNumSumi("S", cliente.getCod_esta());
                }
                if (Integer.parseInt(id_riesgo) == 103) {
                    nit = this.mi.getNumSumi("P", cliente.getCod_esta()); ///22-08-2018 "P" se cambia por "S"
                }
                try{//// falla cuando es programa "P" y descargaron botiquin con id_tipo_far=5
                   paciente.setNit("0" + (Integer.toString(1 + Integer.parseInt(nit))));
                }catch (Exception e){ 
                    paciente.setNit("0");
                }  
                if (datoHis.getId_seguro() == 0) {
                    modelo.put("id_programa", "1");
                } else {
                    modelo.put("id_programa", Integer.toString(datoHis.getId_seguro()));
                }
                modelo.put("expedido", "S");
            } else if ("P".equals(datoHis.getExpedido())) {
                paciente.setNum_cladoc(this.mi.getNumRegistro(datoHis.getId_paciente()));
                if (datoestab.getCod_esta() == 200010) { ////Cajas
                    paciente.setNum_cladoc(buscarPac.getNro_registro());
                    modelo.put("id_programa", "13");
                }
                paciente.setId_rubro(1);
                paciente.setId_estado("P");
                modelo.put("expedido", "P");
            } else {
                modelo.put("expedido", "V");
               paciente.setCod_esta(cliente.getCod_esta());
               paciente.setNombre("SN");
               paciente.setNombres(nombres);
               paciente.setPrecio_total(0);
               paciente.setId_tipo_far(0);////solo venta
               paciente.setNum_cladoc(Long.toString(this.mi.getNumClaDocProf(paciente)) );///de la tabla pedidos saca numeracion
               paciente.setId_historial(Integer.parseInt(id_historial));
               paciente.setId_pais(inter);
               paciente.setId_paciente(buscarPac.getId_paciente());
               paciente.setId_persona(datoHis.getId_persona()) ;
               paciente.setId_dispensa(cliente.getId_persona()) ;
               paciente.setTipo("B");
               paciente.setId_estado("A");
               paciente.setFec_registro(Fecha1);
               paciente.setCod_esta(cliente.getCod_esta());
               this.mi.setCrearProforma(paciente) ;        
               id_pedido= Integer.toString(this.mi.getNumPedidoProf(paciente));
 
               Recetas midato = new Recetas();
               midato.setId_historial(Integer.parseInt(id_historial));
               midato.setId_historia(inter);
               //midato.setId_persona(Integer.parseInt(id_persona));
               midato.setId_estado("A");
               midato.setCod_esta(cliente.getCod_esta());
               midato.setId_farmacia(cliente.getId_farmacia());
               List ListarRecetas = this.mi.getListarRecetas(midato);
               modelo.put("listarRecetas", ListarRecetas);
               modelo.put("datos", paciente);
               modelo.put("expedido", expedido);
               modelo.put("accionm", accionm);
               modelo.put("fechar", fechar);
               modelo.put("id_historial", id_historial);
               modelo.put("id_pedido", id_pedido);
               return new ModelAndView("administrarfarmacias/ListaRecProforma",modelo);
            }

            paciente.setNombres(nombres);
            paciente.setPrecio_total(0);
            paciente.setId_paciente(Integer.parseInt(id_historial));
            //paciente.setId_costo(0);
            paciente.setTipo("B");
            paciente.setCod_esta(cliente.getCod_esta());
            paciente.setId_factura(this.mi.getNumReceta(paciente));/////0 sin factura, 1 con factura, 2, 3, 4, para num correlativo, entradas, ajuste+ y ajuste-
            paciente.setFec_registro(Fecha1);
            paciente.setCadena1("E");
            if (datoestab.getCod_esta() == 200010) {
                if ("U".equals(tag) || "N".equals(tag) || "M".equals(tag)) {
                    Recetas midato = new Recetas();
                    midato.setId_historial(Integer.parseInt(id_historial));
                    midato.setId_farmacia(cliente.getId_farmacia());
                    midato.setCod_esta(cliente.getCod_esta());
                    midato.setId_historia(inter);
                    midato.setId_estado("N");
                    if (sig_centro != null && !"".equals(sig_centro) && id_farmacia2 != null) {
                        midato.setCod_esta(Integer.parseInt(sig_centro));
                        midato.setId_farmacia(Integer.parseInt(id_farmacia2));
                    }
                    this.mi.setModificarRecetaNumera(midato);
                }
                paciente.setCadena1(tag);
                paciente.setCadena2(buscarestab.getCod_cen());
                paciente.setCadena3(servicio);
                paciente.setCadena4(especialidad);
                paciente.setCadena5(codigoprof);
                try {
                    if (buscarPac.getNro_registro().length() > 9) {
                        paciente.setCadena6(buscarPac.getNro_registro().substring(6, 10));
                    } else {
                        paciente.setCadena6(buscarPac.getNro_registro().substring(6, 9));
                    }
                } catch (Exception e) {
                    return new ModelAndView("Aviso", "mensaje", "La matricula del paciente es incorrecta");
                }

                paciente.setCadena7(buscarPac.getNro_registro().substring(0, 6));
                paciente.setCadena8(buscarPac.getNro());
                paciente.setTipo("C");
            }
            if (Integer.parseInt(id_riesgo) == 103) {///para el nutribebe se entrega doble
                paciente.setId_estado("P");
                paciente.setId_tipo_far(-2);///entrega nutribebe
            }
            this.mi.setCrearPeedido(paciente);
            id_pedido = Integer.toString(this.mi.getNumPedido(paciente)); /////puede fallar porque solo saca el max de cod_esta deberia sacar con otros datos mas como id_persona     
        }

        if ("EntregarTodo".equals(accion)) {
            String cantidad = request.getParameter("cantidad");
            String swe = request.getParameter("swe");
            int swc = 0;

            if ("".equals(id_doctor) || id_doctor == null) {
                id_doctor = "0";
            }
            if (sig_centro == null || "".equals(sig_centro)) {
                sig_centro = "0";
            }
            if (tag == null || "".equals(tag)) {
                tag = "E";
            }
            if (id_programa == null || "".equals(id_programa)) {
                id_programa = "0";
                if (datoestab.getCod_esta() == 200010) {
                    id_programa = "13";
                }
            }
            Recetas dato = new Recetas();
            if ("E".equals(datoHis.getExpedido()) || "V".equals(datoHis.getExpedido())) {
                    dato.setId_pedido(Integer.parseInt(id_pedido)) ;
                    dato.setId_historial(datoHis.getId_historial());
                    dato.setId_historial(Integer.parseInt(id_historial));
                    dato.setId_persona(cliente.getId_persona()) ; 
                    dato.setCod_esta(cliente.getCod_esta());
                    dato.setId_farmacia(cliente.getId_farmacia());
                    dato.setCadena1(ip);
                    dato.setCadena2(host);
                    this.mi.setCrearKardexProf2(dato); 
            }else{
                
            
            
            Recetas datore = new Recetas();
            datore.setId_historial(Integer.parseInt(id_historial));
            datore.setId_estado("A");
            datore.setId_persona(Integer.parseInt(id_doctor));
            datore.setId_historia(0);
            datore.setId_farmacia(cliente.getId_farmacia());
            datore.setCod_esta(cliente.getCod_esta());
            if (sig_centro != null && !"".equals(sig_centro) && !"0".equals(sig_centro) && id_farmacia2 != null) {////se cambia de id_farmacia3 a 2 no funcionaba para otros hospitales
                datore.setCod_esta(Integer.parseInt(sig_centro));
                datore.setId_farmacia(Integer.parseInt(id_farmacia2));
            }
            if ("U".equals(tag) || "N".equals(tag) || "M".equals(tag)) {
                datore.setId_historia(0);
                datore.setId_estado("N");
                this.mi.setModificarRecetaNumera(datore);   ////setModificarRecetaNumera
            }

            List listarRecetasS = this.mi.getListarRecetas(datore);

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

                    //Recetas dato = new Recetas();
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
                        if (Integer.parseInt(id_programa) == 6 && Integer.parseInt(id_medicamento) == 574) {
                            dato.setExpedido("P");
                        }
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
                    if (buscarMedicamento.getId_medicamento() != 574) {
                        int iResultado = this.mi.setRegistrarKardex(dato);
                    }

                    //buscarMedicamento.setCod_esta(cliente.getCod_esta());
                    //buscarMedicamento.setId_farmacia(cliente.getId_farmacia());
                    // buscarMedicamento.setStock(buscarMedicamento.getStock()-dato.getSalida());   
                    //this.mi.setModificarMedicamento(buscarMedicamento);
                    ///Recetas datore=new Recetas();
                    modelo.put("id_programa", id_programa);
                    datore.setId_historial(datoReceta.getId_historial());
                    datore.setId_historia(datoReceta.getId_historia());
                    datore.setId_persona(datoReceta.getId_persona());
                    datore.setId_estado("B");
                    datore.setId_farmacia(cliente.getId_farmacia());
                    datore.setCod_esta(cliente.getCod_esta());
                    if (sig_centro != null && !"".equals(sig_centro) && id_farmacia2 != null) {  ////se cambia de id_farmacia3 a 2 no funcionaba para otros hospitales
                        datore.setCod_esta(Integer.parseInt(sig_centro)); ///si fuera otro centro hace el cambio de estado receta del otro centro
                    }
                    List listaRece = this.mi.getListarRecetas(datore);
                    datore.setId_estado("A");
                    List listaRece2 = this.mi.getListarRecetasS(datore);
                    for (int m = 0; m < listaRece.size(); m++) {
                        Recetas datoRecetas = (Recetas) listaRece.get(m);
                        dato.setId_estado("C");
                        dato.setId_detalle(datoRecetas.getId_detalle());
                        dato.setId_medicamento(datoReceta.getId_medicamento());
                        dato.setCod_esta(cliente.getCod_esta());
                        if (sig_centro != null && !"".equals(sig_centro) && !"0".equals(sig_centro) && id_farmacia2 != null) {////se cambia de id_farmacia3 a 2 no funcionaba para otros hospitales
                            dato.setCod_esta(Integer.parseInt(sig_centro));
                        }
                        if (buscarMedicamento.getId_medicamento() != 574) {
                            this.mi.setModificarEstadoInter(dato);
                        }
                    }

                    for (int n = 0; n < listaRece2.size(); n++) {
                        Recetas datoReceta3 = (Recetas) listaRece2.get(n);
                        if (datoReceta3.getId_detalle() == datoReceta.getId_detalle()) {
                            dato.setId_estado("B");
                            dato.setId_detalle(datoReceta3.getId_detalle());
                            dato.setId_medicamento(datoReceta.getId_medicamento());
                            dato.setCod_esta(cliente.getCod_esta());
                            if (sig_centro != null && !"".equals(sig_centro) && !"0".equals(sig_centro) && id_farmacia2 != null) {////se cambia de id_farmacia3 a 2 no funcionaba para otros hospitales
                                dato.setCod_esta(Integer.parseInt(sig_centro));
                            }
                            if (buscarMedicamento.getId_medicamento() != 574) {
                                this.mi.setModificarEstadoInter(dato);
                            }
                        }

                    }

                    if (listaRece2.size() - swc == 1) {
                        datoHis.setId_historial(Integer.parseInt(id_historial));
                        datoHis.setId_estado("B");
                        this.mi.setModificarEstadoHistoria(datoHis);   ////12-09-2016 cuando no hay medicamentos cambia estado receta  
                      }
                }
                }
            }
        }

        if ("adicion".equals(accion)) {
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
            if (sig_centro != null && !"".equals(sig_centro) && id_farmacia2 != null) {
                midato.setCod_esta(Integer.parseInt(sig_centro));
                midato.setId_farmacia(Integer.parseInt(id_farmacia2));
            }
            midato.setId_medicamento(Integer.parseInt(id_medicamento));
            List listarKardexrev = this.mi.getListarKardexComprueba(midato);

            if (Double.parseDouble(stock) < Double.parseDouble(cantidad) && datoestab.getFar_sinstock() == 0) {
                cantidad = "0";
                if (datoestab.getCod_esta() == 200010) {
                    return new ModelAndView("Aviso", "mensaje", "No puede entregar esta cantidad, el Saldo es menor al que quiere entregar");
                }
            }

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

            Recetas dato = new Recetas();
            
            if("E".equals(expedido) || "A".equals(expedido) ){     
               //Recetas dato = new Recetas();
               dato.setId_pedido(Integer.parseInt(id_pedido)) ;
               dato.setId_medicamento(Integer.parseInt(id_medicamento));
               dato.setSalida(Double.parseDouble(cantidad));
               dato.setEntrada(0);
               dato.setCosto_unit(buscarMedicamento.getCosto_unit());
               dato.setPrecio_venta(buscarMedicamento.getPrecio_venta());
               dato.setNro_lote(buscarMedicamento.getNro_lote());
               dato.setFecha_ven(buscarMedicamento.getFecha_ven());
               dato.setExpedido("V");
               dato.setId_programa(0);
               dato.setFecha(Fecha1);
               dato.setCod_esta(cliente.getCod_esta());
               dato.setId_farmacia(cliente.getId_farmacia());
               dato.setId_receta(Integer.parseInt(id_receta));
               dato.setId_persona(cliente.getId_persona()) ; 
               dato.setId_factura(0) ; 
               dato.setCadena1(ip);
               dato.setCadena2(host);
               this.mi.setCrearKardexProf(dato);       
               
               dato.setId_historial(Integer.parseInt(id_historial));
               dato.setId_receta(Integer.parseInt(id_receta));
               dato.setId_estado("B");
               dato.setId_medicamento(Integer.parseInt(id_medicamento));
               dato.setCod_esta(cliente.getCod_esta());
               modelo.put("expedido", expedido);
               this.mi.setModificarEstadoReceta(dato);
               
               ///Recetas midato = new Recetas();
                midato.setId_historial(Integer.parseInt(id_historial));
                midato.setId_historia(Integer.parseInt(id_historia));
                midato.setId_estado("A");
                midato.setCod_esta(cliente.getCod_esta());
                midato.setCodigo(cliente.getCod_esta());//// si fuera otro hospitalestito cambia
                midato.setId_farmacia(cliente.getId_farmacia());
                List ListarRecetas = this.mi.getListarRecetas(midato);
                modelo.put("listarRecetas", ListarRecetas);
  
                midato.setId_pedido(Integer.parseInt(id_pedido));
                midato.setId_prestacion(cliente.getCod_esta());
                midato.setCod_esta(cliente.getCod_esta());
                midato.setId_farmacia(cliente.getId_farmacia());
                List listarKardex = this.mi.getListarKardexProf(midato);
                modelo.put("listarKardex", listarKardex);
                
                total=0;
                for(int i=0;i<listarKardex.size();i++){
                     midato=(Recetas)listarKardex.get(i);
                     total=total+midato.getPrecio_total();
                } 
                // actualiza el precio total

                Pacientes paciente1= new Pacientes() ;
                paciente1.setId_pedido(Integer.parseInt(id_pedido));
                paciente1.setCod_esta(cliente.getCod_esta());
                Pacientes paciente = this.mi.getDatosPedidoProf(paciente1);////10-07-2018 de getDatosPedido a getDatosPedidoProf  

                paciente.setPrecio_total(total);
                paciente.setId_carpeta(Integer.parseInt(id_historial));
                paciente.setId_paciente(datoHis.getId_paciente());
                
                ///aumente 10-07-2018 apara que actualice precio pedidoProf
                if ("E".equals(datoHis.getExpedido()) || "V".equals(datoHis.getExpedido())) {///busca si es particular
                    this.mi.setModificarPedidoProf(paciente);///actualiza solo pedidoprof
                }else{
                    this.mi.setModificarPedido(paciente);///actualiza el pedido
                }
    
                modelo.put("datos", paciente);
                modelo.put("expedido", expedido);
                modelo.put("accionm", accionm);
                modelo.put("fechar", fechar);
                modelo.put("id_historial", id_historial);
                modelo.put("id_pedido", id_pedido);
                
                
                
               return new ModelAndView("administrarfarmacias/ListaReceta",modelo);
           }
           
           dato.setId_pedido(Integer.parseInt(id_pedido)) ;
           dato.setId_medicamento(Integer.parseInt(id_medicamento));
           dato.setSalida(Double.parseDouble(cantidad));
           dato.setEntrada(0);
           dato.setCosto_unit(buscarMedicamento.getCosto_unit());
           dato.setPrecio_venta(buscarMedicamento.getPrecio_venta());
           dato.setExpedido(expedido);
           dato.setId_receta(Integer.parseInt(id_detalle));
           dato.setNumeracion(Integer.parseInt(numeracion));
           dato.setNro_lote(buscarMedicamento.getNro_lote());
           dato.setFecha_ven(buscarMedicamento.getFecha_ven());
           dato.setFecha(Fecha1);
            // entregamos el medicamento
            if (id_programa == null || id_programa == "") {
                id_programa = "0";
                if (datoestab.getCod_esta() == 200010) {
                    id_programa = "13";
                }
            }
            // actualizamos el stock del medicamento
            if ("E".equals(expedido) || "A".equals(expedido)) {
                buscarMedicamento.setStockv(buscarMedicamento.getStockv() - dato.getSalida());
                dato.setExpedido("V");
                dato.setId_programa(0);
            } else if ("P".equals(expedido)) {
                dato.setId_programa(Integer.parseInt(id_programa));////20-01-2014
                buscarMedicamento.setStockp(buscarMedicamento.getStockp() - dato.getSalida());
                dato.setId_programa(Integer.parseInt(id_programa));////20-01-2014
            } else {
                buscarMedicamento.setStocks(buscarMedicamento.getStocks() - dato.getSalida());
                dato.setId_programa(Integer.parseInt(id_programa));////20-01-2014
                if (Integer.parseInt(id_programa) == 6 && Integer.parseInt(id_medicamento) == 574) {
                    dato.setExpedido("P");
                }
            }

            midato.setId_historial(Integer.parseInt(id_historial));
            midato.setId_estado("A");
            midato.setCod_esta(cliente.getCod_esta());
            midato.setId_farmacia(cliente.getId_farmacia());
            List ListarRecetas = this.mi.getListarRecetas(midato); ////los medicamentos del medico tabla recetas
            if (ListarRecetas.size() > 0) {
                Recetas recetamed = (Recetas) ListarRecetas.get(0);
                dato.setFecha_ini(recetamed.getFecha()); ////si es de otro dia lo folia con fecha de hoy
                if (ListarRecetas.size() == 1) {
                    datoHis.setId_historial(Integer.parseInt(id_historial));
                    datoHis.setId_estado("B");
                    this.mi.setModificarEstadoHistoria(datoHis);   ////12-09-2016 cuando no hay medicamentos cambia estado receta  
                }
            }

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
            dato.setId_historial(Integer.parseInt(id_historial));
            dato.setId_historia(datoHis.getId_cargo());
            dato.setId_medico(0);
            dato.setSaldos(0);
            dato.setDosifica(0);
            dato.setCadena1(ip);///01/03/2016
            dato.setCadena2(host);///01/03/2016
            if (buscarMedicamento.getId_medicamento() == 574) {///////nutribebe para crear nuevo pedido
                //Pacientes pedido= new Pacientes() ;
                //pedido.setId_pedido(Integer.parseInt(id_pedido));
                //pedido.setCod_esta(cliente.getCod_esta());
                //this.mi.setCrearPedidoNutri(pedido) ; ////crea el pedido para nutribebe
                //Pacientes pedido1 = this.mi.getDatosPedido(pedido); 
                //id_pedido2= Integer.toString(this.mi.getNumPedido(pedido1));
                //dato.setId_pedido(Integer.parseInt(id_pedido2)) ;
                if (cliente.getId_persona() != 269) {
                    dato.setId_programa(6);
                    dato.setExpedido("P");
                }
            }
            if (listarKardexrev.size() == 0) {////se verificaba si ya existia el medicamento 27/12/2016 se comenta para CNS dar varias recetas a la vez
                ///this.mi.setCrearKardex(dato);  
                int iResultado = this.mi.setRegistrarKardex(dato);///23/06/2017 se vuelve a colcar que no pueda dar dos veces el mismo medicamento, hacen doble clic y se coloca varias veces el mismo 
            }

            dato.setId_prestacion(cliente.getCod_esta());
            dato.setId_factura(cliente.getId_persona());
//            buscarMedicamento.setCod_esta(cliente.getCod_esta());
//            buscarMedicamento.setId_farmacia(cliente.getId_farmacia());
//            buscarMedicamento.setStock(buscarMedicamento.getStock() - dato.getSalida());
            //this.mi.setModificarMedicamentoStock(buscarMedicamento);  se elimina ya esta descontando el stock en funcion postgres setRegistrarKardex 
            // eliminamos de la lista de recetas
            dato.setId_historial(Integer.parseInt(id_historial));
            dato.setId_receta(Integer.parseInt(id_receta));
            dato.setId_estado("B");
            dato.setId_medicamento(Integer.parseInt(id_medicamento));
            dato.setCod_esta(cliente.getCod_esta());
            if (sig_centro != null && !"".equals(sig_centro) && id_farmacia2 != null) {////se cambia de id_farmacia3 a 2 no funcionaba para otros hospitales
                dato.setCod_esta(Integer.parseInt(sig_centro));
            }
            modelo.put("expedido", expedido);
            this.mi.setModificarEstadoReceta(dato);
        }

        if ("eliminar".equals(accion)) {
            String cantidad = request.getParameter("salida");
            String id_por = request.getParameter("id_por");

            if (Integer.parseInt(id_por) != cliente.getId_persona() && cliente.getId_cargo() != 7) {
                Personas buscarEmpleado = this.mi.getDatosPersona(Integer.parseInt(id_por));
                return new ModelAndView("Aviso", "mensaje", "Ud. no esta autorizado para eliminar este medicamento, solo : " + buscarEmpleado.getNombres());
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
            if ("E".equals(expedido) || "V".equals(expedido) ) {
               dato.setId_receta(Integer.parseInt(id_receta)); 
               this.mi.setEliminarKardexProf(dato);    
            }else{
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
                buscarMedicamento.setId_farmacia(cliente.getId_farmacia());
                buscarMedicamento.setStock(buscarMedicamento.getStock() + dato.getSalida());
                this.mi.setModificarMedicamentoStock(buscarMedicamento);
                // activamos en la lista de recetas
                dato.setId_historial(Integer.parseInt(id_historial));
                dato.setId_medicamento(Integer.parseInt(id_medicamento));
                dato.setId_receta(Integer.parseInt(id_receta));
                dato.setId_estado("A");
                dato.setCod_esta(cliente.getCod_esta());
                if (sig_centro != null && !"".equals(sig_centro) && id_farmacia2 != null) {////se cambia de id_farmacia3 a 2 no funcionaba para otros hospitales
                    dato.setCod_esta(Integer.parseInt(sig_centro));
                }
                modelo.put("expedido", expedido);
                this.mi.setModificarEstadoReceta(dato);
            }
        }

        if (Integer.parseInt(id_historial) == 0) {
            return new ModelAndView("Aviso", "mensaje", "Este Paciente no tiene Receta Impresa");
        }
        if (id_historia == null || id_historia == "") {
            id_historia = "0";
        }

        Recetas midato = new Recetas();
        midato.setId_historial(Integer.parseInt(id_historial));
        midato.setId_historia(Integer.parseInt(id_historia));
        //midato.setId_persona(Integer.parseInt(id_persona));
        midato.setId_estado("A");
        midato.setCod_esta(cliente.getCod_esta());
        midato.setCodigo(cliente.getCod_esta());//// si fuera otro hospitalestito cambia
        midato.setId_farmacia(cliente.getId_farmacia());
        List ListarRecetas = this.mi.getListarRecetas(midato);
        modelo.put("listarRecetas", ListarRecetas);
        if (datoestab.getCod_esta() == 200010) {
            midato.setId_estado("C");
            List ListarRecetas2 = this.mi.getListarRecetasCNS(midato);
            modelo.put("listarRecetas", ListarRecetas2);
        }

//////para la enrega de otro centro
        if (sig_centro != null && !"".equals(sig_centro) && id_farmacia2 != null) {/// si son recetas de otro centro
            //midato.setCod_esta(Integer.parseInt(sig_centro)) ; //// 07-02-2017 se aumento id_farmacia3 cuando hacia obrero de otro centro no salia al entregar
            //midato.setId_farmacia(Integer.parseInt(id_farmacia2));
            midato.setCodigo(Integer.parseInt(sig_centro));
            //if(cliente.getCod_esta()!=Integer.parseInt(sig_centro)){
            //        midato.setCod_esta(Integer.parseInt(sig_centro));
            //        midato.setId_farmacia(Integer.parseInt(id_farmacia2));
            //} 
        }

        midato.setId_pedido(Integer.parseInt(id_pedido));
        midato.setId_prestacion(cliente.getCod_esta());
        midato.setCod_esta(cliente.getCod_esta());
        midato.setId_farmacia(cliente.getId_farmacia());
        List listarKardex = this.mi.getListarKardex(midato);
        modelo.put("listarKardex", listarKardex);
        
        if ("E".equals(datoHis.getExpedido()) || "V".equals(datoHis.getExpedido())) {
            listarKardex = this.mi.getListarKardexProf(midato);
            modelo.put("listarKardex", listarKardex);
        }
        
        Medicamentos medid = new Medicamentos();
        medid.setCod_esta(cliente.getCod_esta());
        List listarprog = this.mi.getListarProgramas(medid);
        modelo.put("listarProg", listarprog);
        modelo.put("id_programa", id_programa);

        if (datoHis.getId_seguro() < 6) {
            modelo.put("id_programa", Integer.toString(datoHis.getId_seguro()));
        } else {
            if (datoHis.getId_seguro() > 5) {
                for (int i = 0; i < listarprog.size(); i++) {
                    Medicamentos listaPrg = (Medicamentos) listarprog.get(i);
                    if (datoHis.getId_seguro() == listaPrg.getB1()) {
                        modelo.put("id_programa", Integer.toString(listaPrg.getId_programa()));
                    }
                }
            }
        }

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
        
        if ("E".equals(datoHis.getExpedido()) || "V".equals(datoHis.getExpedido())) {
            paciente = this.mi.getDatosPedidoProf(paciente1);
        }
        
        paciente.setPrecio_total(total);
        paciente.setId_carpeta(Integer.parseInt(id_historial));
        paciente.setId_paciente(datoHis.getId_paciente());
        if ("Modificar".equals(accionm)) {
            int diax = Integer.parseInt(fechar.substring(0, 2));
            int mesx = Integer.parseInt(fechar.substring(3, 5));
            int aniox = Integer.parseInt(fechar.substring(6, 10));
            int horax = datoHis.getFecha().getHours();
            int minutox = datoHis.getFecha().getMinutes();
            Date fechaa = new Date(aniox - 1900, mesx - 1, diax, horax, minutox, 00);
            paciente.setFecha_fin(fechaa); ////cuando se modifica las receta repite la hora de la atencion
            paciente.setTipo("O");   ///setModificarPedidoSinFecha 06-04-2016
            this.mi.setModificarPedidoSinFecha(paciente);
        } else {
            paciente.setFecha_fin(Fecha1);
            if ("E".equals(datoHis.getExpedido()) || "V".equals(datoHis.getExpedido())) {///busca si es particular
                this.mi.setModificarPedidoProf(paciente);///actualiza solo pedidoprof
            }else{
                this.mi.setModificarPedido(paciente);///actualiza el pedido
            }
            
        }

        modelo.put("datos", paciente);
        modelo.put("expedido", expedido);
        modelo.put("accionm", accionm);
        modelo.put("fechar", fechar);
        modelo.put("id_historial", id_historial);
        modelo.put("id_pedido", id_pedido);

        return new ModelAndView("administrarfarmacias/ListaReceta", modelo);
    }
}
