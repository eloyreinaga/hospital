package org.ayaic.web.administrarfarmacias;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Consultorios;
import org.ayaic.domain.Cuadernos;
import org.ayaic.domain.Historiales;
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
public class ListaNRecetaControlador {

    private final MiFacade mi;

    public ListaNRecetaControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/ListaNReceta.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();
        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String accionb = request.getParameter("accionb");
        String id_pedido = request.getParameter("id_pedido");
        String id_persona = request.getParameter("id_persona");
        String id_personafar = request.getParameter("id_personafar");
        String id_farmacia2 = request.getParameter("id_farmacia2");
        String id_medicamento = request.getParameter("id_medicamento");
        String nombremed = request.getParameter("nombremed");
        String id_riesgo = request.getParameter("id_riesgo");
        String sig_centro = request.getParameter("sig_centro");
        String servicio = request.getParameter("servicio");
        String id_tipointer = request.getParameter("id_tipointer");
        String medico = request.getParameter("medico");
        String tipo = request.getParameter("tipo");
        String sw = request.getParameter("sw");
        String swb = request.getParameter("swb");
        String expedido = request.getParameter("expedido");
        String folio = request.getParameter("folio");
        String valor_1 = request.getParameter("valor_1");
        String id_consultorio = request.getParameter("id_consultorio");
        String diaq1 = request.getParameter("mesq1");
        String mesq1 = request.getParameter("mesq1");
        String[] dias = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
        String[] meses = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        Date fecha1 = new Date();
        int anioq = fecha1.getYear() + 1900;
        String[] anios = {Integer.toString(anioq), Integer.toString(anioq - 1), Integer.toString(anioq - 2), Integer.toString(anioq - 3), Integer.toString(anioq - 4), Integer.toString(anioq - 5), Integer.toString(anioq - 6), Integer.toString(anioq - 7), Integer.toString(anioq - 8), Integer.toString(anioq - 9), Integer.toString(anioq - 10)};
        String ip = request.getRemoteAddr();
        String host = request.getRemoteHost();
        Date Fecha1 = new Date();
        double total = 0;

        Localidades local = new Localidades();
        List Estab1 = this.mi.getListarEsta(local);
        Localidades datoestab = (Localidades) Estab1.get(0);
        modelo.put("cod_esta", Integer.toString(datoestab.getCod_esta()));
        modelo.put("seguro_estab", Integer.toString(cliente.getInst_id()));
        modelo.put("id_personafar", id_personafar);
        modelo.put("id_farmacia2", id_farmacia2);
        modelo.put("id_tipointer", id_tipointer);
        modelo.put("nombremed", nombremed);
        modelo.put("id_riesgo", id_riesgo);
        modelo.put("anios", anios);
        modelo.put("meses", meses);
        modelo.put("dias", dias);
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
        modelo.put("folio", folio);
        modelo.put("swb", swb);
        modelo.put("sw", sw);

        Medicamentos datom = new Medicamentos();
        datom.setCod_esta(cliente.getCod_esta());
        datom.setId_farmacia(cliente.getId_farmacia());
        datom.setExpedido("I");    /////getDatosItem
        Medicamentos datoItem = this.mi.getDatosItem(datom);
        modelo.put("datoItem", datoItem);

        if (id_persona == null || "".equals(id_persona)) {
            id_persona = "0";
        }
        if (id_consultorio == null || "".equals(id_consultorio)) {
            id_consultorio = "0";
        }
        if (expedido == null || "".equals(expedido)) {
            expedido = "P";
            modelo.put("id_programa", "13");
        }

        if (valor_1 == null || "".equals(valor_1)) {
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

        Personas persona = this.mi.getBuscarPersona(cliente.getId_persona());
        Consultorios datosconsulta = this.mi.getDatosConsultorio(persona.getId_consultorio()); // saca un registro a ser modificado
        modelo.put("tipo_medico", Integer.toString(datosconsulta.getId_cargo()));
        if ("SUMI".equals(expedido)) {
            modelo.put("id_programa", "1");
        } else { ////falta para fondo rotatorio 
            if (datoestab.getCod_esta() == 200010) {
                modelo.put("id_programa", "13");
            }
        }

        if (!"0".equals(id_consultorio) && !"XXX".equals(accionb)) {
            String nombres = request.getParameter("nombres");

            Consultorios a = new Consultorios();
            a.setCod_esta(cliente.getCod_esta());
            List listaCargos = this.mi.getListarConsultorios(a);
            modelo.put("listaCargos", listaCargos);
            modelo.put("id_consultorio", id_consultorio);
            //Personas persona= new Personas();
            persona.setId_consultorio(Integer.parseInt(id_consultorio));
            persona.setCod_esta(cliente.getCod_esta());
            List buscarEmpleado = this.mi.getDatosPersonaConsul(persona);
            modelo.put("listaPersonas", buscarEmpleado);
            modelo.put("estab", datoestab.getArea());
            modelo.put("num_cladoc", "0");
            modelo.put("nombre", nombres);
            modelo.put("accionb", "XXX");
            modelo.put("tipo", tipo);
            return new ModelAndView("administrarfarmacias/EntregaNPaciente", modelo);
        }

        if ("Imprimir".equals(accion)) {
            Recetas midato = new Recetas();
            midato.setId_pedido(Integer.parseInt(id_pedido));
            midato.setId_prestacion(cliente.getCod_esta());
            midato.setCod_esta(cliente.getCod_esta());
            midato.setId_farmacia(cliente.getId_farmacia());
            List listarKardexImp = this.mi.getListarKardexProf(midato);
            modelo.put("listarKardex", listarKardexImp);
            Pacientes paciente1 = new Pacientes();
            paciente1.setId_pedido(Integer.parseInt(id_pedido));
            paciente1.setCod_esta(cliente.getCod_esta());
            Pacientes paciente = this.mi.getDatosPedidoProf(paciente1); //////////////
            modelo.put("datos", paciente);
            modelo.put("dato", cliente);
            Cuadernos dato = new Cuadernos();
            dato.setTipoconsulta("O");
            dato.setTipo_egreso(10);  ///para orden de compra
            List datosgral = this.mi.getConfigurarImpresionGral(dato);
            modelo.put("datosgral", datosgral);
            return new ModelAndView(new ListarOrdenPDF(), modelo);
        }

        if ("entregarya".equals(accion)) {
            String nombres = request.getParameter("nombres");
            String num_cladoc = request.getParameter("num_cladoc");
            String cod = request.getParameter("cod");
            String consultorio = request.getParameter("consultorio");
            Consultorios datosconsul = this.mi.getDatosConsultorio(Integer.parseInt(id_consultorio));
            if ("".equals(num_cladoc) || num_cladoc == null) {
                num_cladoc = "0";
            }
            if ("".equals(medico) || medico == null) {
                medico = "0";
            }
            if (("".equals(nombres) || nombres == null)) {
                medico = "SN";
            }
            if ("cod".equals(cod) || cod == null) {
                cod = "0";
            }

            if ("Botiquin".equals(swb)) {
                nombres = "BOTIQUIN " + datosconsul.getConsultorio() + " " + nombres;
            }
            long fechaInicial = Fecha1.getTime();
            long fechaFinal = fecha1.getTime();
            long diferencia =  (fechaFinal - fechaInicial)+50;
            double diass = Math.floor(diferencia / (1000 * 60 * 60 * 24));
            if (fechaInicial > fechaFinal) {
                return new ModelAndView("Aviso", "mensaje", "La fecha que desea ingresar NO es correcta, esta adelantada a HOY");
            }
            if (!"C.S. DISP. JESUS DE NAZARETH".equals(cliente.getEstablecimiento())) {
                if (diass > 90) {
                    return new ModelAndView("Aviso", "mensaje", "La fecha que desea ingresar, NO puede ser mayor a 90 dias antes");
                }
            }
            Consultorios a = new Consultorios();
            a.setCod_esta(cliente.getCod_esta());
            a.setId_estado("2");   ////getListarServicioCNS2
            a.setId_consultorio(Integer.parseInt(id_consultorio));
            List listarServicio = mi.getListarServicioCNS2(a);
            Consultorios buscarEspec = this.mi.getDatosConsultorio(Integer.parseInt(id_consultorio));
            Personas buscarEmpleado = this.mi.getDatosPersona(Integer.parseInt(id_persona));
            Localidades localidad = new Localidades();
            localidad.setArea("E");   ////getDatosEstable
            localidad.setCod_esta(cliente.getCod_esta());
            Localidades buscarestab = this.mi.getDatosEstable(localidad);

            if (("".equals(nombres) || nombres == null)) {
                nombres = "0";
            }

            Pacientes paciente = new Pacientes();
            paciente.setNombres(nombres);
            paciente.setPrecio_total(0);
            paciente.setNum_cladoc(num_cladoc);
            paciente.setId_paciente(0);
            paciente.setId_carpeta(0);
            paciente.setId_rubro(1);
            paciente.setCod_esta(cliente.getCod_esta());
            if ("Botiquin".equals(swb)) {
                paciente.setId_carpeta(Integer.parseInt(id_consultorio));
                paciente.setId_persona(Integer.parseInt(id_persona));
                paciente.setId_tipo_far(5);
            }
            paciente.setNit(num_cladoc);
            paciente.setId_dispensa(cliente.getId_persona());
            if ("VENTA".equals(swb)) {    ///era sw
                paciente.setNum_cladoc("0");
                paciente.setId_rubro(1);
                modelo.put("id_programa", "0");
                modelo.put("expedido", "E");
                if ("Botiquin".equals(swb)) {
                    paciente.setId_estado("E");
                } else {
                    paciente.setId_estado("X");  ////para cuando se hace la receta y no se ha terminado         
                }

            } else if ("SUMI".equals(swb)) {    ///era sw
                paciente.setId_estado("S");
                paciente.setNit("0" + (Integer.toString(1 + Integer.parseInt(this.mi.getNumSumi("S", cliente.getCod_esta())))));
            } else {
                paciente.setId_estado("P");
                if ("SSPAM".equals(swb)) {
                    modelo.put("expedido", "P");
                }
                if ("SUMI".equals(expedido)) {
                    paciente.setId_estado("S");
                    modelo.put("expedido", "S");
                    swb = expedido;
                    try {
                        paciente.setNit("0" + (Integer.toString(1 + Integer.parseInt(this.mi.getNumSumi("S", cliente.getCod_esta())))));
                    } catch (Exception e) {
                        paciente.setNit("0");
                    }
                }
            }

            paciente.setTipo("B");
            paciente.setCod_esta(cliente.getCod_esta());
            try {
                paciente.setId_factura(this.mi.getNumReceta(paciente));/////0 sin factura, 1 con factura, 2, 3, 4, para num correlativo, entradas, ajuste+ y ajuste- 
            } catch (Exception e) {
                paciente.setId_factura(1);
            }
            paciente.setCadena1("E");
            paciente.setCadena2("00");
            paciente.setCadena3("000");
            paciente.setCadena4("00");
            paciente.setCadena5("00");
            paciente.setCadena6("00");
            paciente.setCadena7("00");
            paciente.setCadena8("00");
            if (datoestab.getCod_esta() == 200010) {
                paciente.setTipo("C");  /////setCrearPeedidoCNS
                paciente.setId_estado("P");
                paciente.setId_tipo_far(0);
                if ("Botiquin".equals(swb)) {
                    paciente.setId_tipo_far(5); ///// 5 para botiquin de servicio 
                }
                paciente.setId_factura(0);
                paciente.setCadena1(tipo);
                paciente.setId_persona(Integer.parseInt(id_persona));
                paciente.setCadena2(buscarestab.getCod_cen());/////14/03/2016
                paciente.setCadena4(buscarEspec.getDescripcion());/////14/03/2016
                paciente.setCadena5(buscarEmpleado.getCodigoprof());/////14/03/2016
                if ("SSPAM".equals(swb)) {//////////llenando de la caja metodo corto de recetas a mano
                    if (num_cladoc.length() > 10 || num_cladoc.length() < 9) {
                        return new ModelAndView("Aviso", "mensaje", "La Matricula del paciente NO es correcta");
                    }
                    //Pacientes paciente = new Pacientes(); 

                    paciente.setNombres(num_cladoc);
                    paciente.setCodigo(cod);
                    paciente.setTipo("M");  ///getListarPacienteFar
                    paciente.setCod_esta(cliente.getCod_esta());
                    List listarPac = this.mi.getListarPacienteFar(paciente);
                    if (listarPac.isEmpty()) {
                        if (("0".equals(nombres))) {
                            return new ModelAndView("Aviso", "mensaje", "Debe colocarse el Nombre del Paciente");
                        } else {
                            //return new ModelAndView("Aviso","mensaje","El Paciente NO se encuentra en la Base de Datos");  
                            paciente.setNombres(nombres);
                        } ////esto deberia ser provisional para que oblique a buscar paciete que si existe

                    } else {
                        Pacientes datoPac = (Pacientes) listarPac.get(0);
                        paciente.setId_paciente(datoPac.getId_paciente());
                        paciente.setNombres(datoPac.getNombres());
                    }
                    paciente.setCadena5(medico.toUpperCase());
                    paciente.setCadena6(num_cladoc.substring(6, 9));
                    paciente.setCadena7(num_cladoc.substring(0, 6));
                    if (num_cladoc.length() == 10) {
                        paciente.setCadena6(num_cladoc.substring(6, 10));
                    }
                    ////llena del medico
                    persona.setDip("1");  ///getListarPersonasNombreMat
                    persona.setNombres(medico.toUpperCase().trim());  ///getListarPersonasNombre
                    List listarMed = this.mi.getListarPersonasNombreMat(persona);
                    if (listarMed.isEmpty()) {
                        if (("0".equals(medico) || medico == null)) {
                            return new ModelAndView("Aviso", "mensaje", "Debe colocarse el Matricula del Medico");
                        } else {
                            return new ModelAndView("Aviso", "mensaje", "El Medico NO se encuentra en la Base de Datos");
                            ///persona.setNombres(medico.toUpperCase().trim());  ///getListarPersonasNombreMat
                        } ////esto deberia ser provisional para que oblique a buscar paciete que si existe
                        //
                    } else {
                        Personas datoPer = (Personas) listarMed.get(0);
                        paciente.setId_persona(datoPer.getId_persona());
                        paciente.setCadena3(datoPer.getCadena1());
                        paciente.setCadena4(Integer.toString(datoPer.getId_consultorio()));
                    }
                    paciente.setCadena8(cod);
                    paciente.setCadena3(datosconsulta.getDescripcion());
                }
                paciente.setTipo("C");
            }

            paciente.setFec_registro(fecha1);
            paciente.setCod_esta(cliente.getCod_esta());
            this.mi.setCrearPeedido(paciente);
            id_pedido = Integer.toString(this.mi.getNumPedido(paciente));
            sw = swb;
            modelo.put("sw", swb);
        }

        if ("adicion".equals(accion)) {
            String cantidad = request.getParameter("cantidad");
            String saldo = request.getParameter("saldo");
            String precio = request.getParameter("precio");
            String id_programa = request.getParameter("id_programa");

            Pacientes paciente1 = new Pacientes();
            paciente1.setId_pedido(Integer.parseInt(id_pedido));
            paciente1.setCod_esta(cliente.getCod_esta());
            Pacientes paciente = this.mi.getDatosPedido(paciente1); //////////////

            Medicamentos medic = new Medicamentos();
            medic.setId_medicamento(Integer.parseInt(id_medicamento));
            medic.setCodigo(cliente.getCod_esta());
            medic.setCod_esta(cliente.getCod_esta());
            medic.setId_farmacia(cliente.getId_farmacia());
            medic.setExpedido("B");    /////getDatosMedicamentoB
            Medicamentos buscarMedicamento = this.mi.getDatosMedicamentoB(medic);

            if ("".equals(id_programa) || id_programa == null) {
                id_programa = "0";
            }
            if (cantidad == null) {
                return new ModelAndView("Aviso", "mensaje", "El stock NO es correcta, no existe Saldo Suficiente");
            }
            if (datoestab.getFar_sinstock() == 0) {
                if (Integer.parseInt(cantidad) > buscarMedicamento.getStock()) {////25-08-2016 Obrero Santa Cruz
                    return new ModelAndView("Aviso", "mensaje", "El stock NO es correcta, no existe Saldo Suficiente del medicamento");
                }
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
            if ("VENTA".equals(sw)) {
                dato.setExpedido("V");
                dato.setId_programa(0);
                buscarMedicamento.setStockv(buscarMedicamento.getStockv() - dato.getSalida());
                if (Double.parseDouble(precio) > buscarMedicamento.getPrecio_venta()) {///18/02/2016 se aumenta para que se actualice el precio venta con el ultimo dato mayor
                    buscarMedicamento.setPrecio_venta(Double.parseDouble(precio));
                }

            } else if ("SUMI".equals(sw)) {
                dato.setExpedido("S");
                dato.setId_programa(Integer.parseInt(id_programa));////20-01-2014
                buscarMedicamento.setStocks(buscarMedicamento.getStocks() - dato.getSalida());
            } else {
                dato.setExpedido("P");
                dato.setId_programa(Integer.parseInt(id_programa));////20-01-2014
                buscarMedicamento.setStockp(buscarMedicamento.getStockp() - dato.getSalida());
            }
            if (datoestab.getCod_esta() == 200010) {
                dato.setId_programa(13);////16-12-2015
                dato.setExpedido("P");
                id_programa = "13";
            }
            // entregamos el medicamento
            dato.setFecha(Fecha1);
            dato.setCod_esta(cliente.getCod_esta());
            dato.setId_farmacia(cliente.getId_farmacia());
            dato.setId_persona(cliente.getId_persona());
            dato.setId_factura(0);
            dato.setId_historial(0);
            dato.setId_historia(0);
            if (paciente.getId_tipo_far() == 5) {
                dato.setId_receta(1); ////16/12/2015 para foliar a (-2) botiquin de servicio CNS
            }                        ////26/01/2016 para foliar a (1) botiquin de servicio CNS
            dato.setCadena1(ip);///01/03/2016
            dato.setCadena2(host);///01/03/2016
            //this.mi.setCrearKardex(dato);        
            int iResultado = this.mi.setRegistrarKardex(dato);
            // acutalizamos el stock del medicamento
//            buscarMedicamento.setCod_esta(cliente.getCod_esta());
//            buscarMedicamento.setStock(buscarMedicamento.getStock() - dato.getSalida());
//            this.mi.setModificarMedicamentoStock(buscarMedicamento);
            modelo.put("id_programa", id_programa);
            modelo.put("folio", Integer.toString(paciente.getId_factura()));
        }

        if ("eliminar".equals(accion)) {
            String cantidad = request.getParameter("salida");
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

            buscarMedicamento.setStock(buscarMedicamento.getStock() + dato.getSalida());
            buscarMedicamento.setCod_esta(cliente.getCod_esta());
            buscarMedicamento.setSuma10(0);
            buscarMedicamento.setCadena10("");
            this.mi.setModificarMedicamentoStock(buscarMedicamento);
        }

        if ("terminar".equals(accion)) {
            Recetas midato = new Recetas();
            midato.setId_pedido(Integer.parseInt(id_pedido));
            midato.setId_prestacion(cliente.getCod_esta());
            midato.setCod_esta(cliente.getCod_esta());
            midato.setId_farmacia(cliente.getId_farmacia());
            List listarKardex = this.mi.getListarKardex(midato);
            total = 0;
            for (int i = 0; i < listarKardex.size(); i++) {
                midato = (Recetas) listarKardex.get(i);
                total = total + midato.getPrecio_total();
                Fecha1 = midato.getFecha();////12/06/2016 para que no cambie la fecha del pedido
            }

            Pacientes paciente1 = new Pacientes();
            paciente1.setId_pedido(Integer.parseInt(id_pedido));
            paciente1.setCod_esta(cliente.getCod_esta());
            Pacientes paciente = this.mi.getDatosPedido(paciente1); ////////////// 
            paciente.setFecha_ini(Fecha1);
            if ("VENTA".equals(sw)) {
                paciente.setId_estado("A");
                paciente.setNum_cladoc(Long.toString(this.mi.getNumClaDoc(paciente)));
            } else if ("SUMI".equals(sw)) {
                paciente.setId_estado("S");
            } else {
                paciente.setId_estado("P");
            }
            this.mi.setModificarPedidoAnt(paciente);

            //lista de paciente que fueron atendidos con el medico
            Historiales dato = new Historiales();
            dato.setId_estado("A");
            dato.setExpedido("%");
            dato.setCod_esta(cliente.getCod_esta());
            List listarAtendidos = this.mi.getListarAtendidos(dato);
            modelo.put("listarAtendidos", listarAtendidos);

            //List listarSeguros = this.mi.getListarSeguros("A");
            //modelo.put("listaPacAfi", listarSeguros);
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
        dato.setCod_esta(cliente.getCod_esta());
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

        if (cliente.getId_rol() == 7) {
            dato.setExpedido("F");
            List listarMedicamentosCot2 = this.mi.getListarMedicamentosCotFar(dato);
            modelo.put("listarMedicamentosCot", listarMedicamentosCot2);
        }

        if (nombremed != null) {
            nombremed = nombremed.trim();
            nombremed = nombremed.replaceAll(" +", " ");
            modelo.put("nombremed", nombremed);
            nombremed = nombremed.replaceAll("\\s", ":*&");
            nombremed = nombremed.replaceAll("ñ", "n");
            nombremed = nombremed.replaceAll("Ñ", "N");
            if (nombremed.length() > 0) {
                nombremed = nombremed + ":*";
            }
        } else {
            nombremed = "";
        }
        dato.setCod_esta(cliente.getCod_esta());
        dato.setMedicamento(nombremed);
        dato.setId_farmacia(cliente.getId_farmacia());
        if ("".equals(nombremed)) {
            List listarMedicamentos = mi.getListarMedicamentosVacio(dato);
            modelo.put("listarMedicamentos", listarMedicamentos);
        } else {
            List listarMedicamentos = this.mi.getListarMedicamentos(dato);
            modelo.put("listarMedicamentos", listarMedicamentos);
        }

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
            //Fecha1=midato.getFecha();////12/06/2016 para que no cambie la fecha del pedido
        }
        // actualiza el precio total
        Pacientes paciente1 = new Pacientes();
        paciente1.setId_pedido(Integer.parseInt(id_pedido));
        paciente1.setCod_esta(cliente.getCod_esta());
        Pacientes paciente = this.mi.getDatosPedido(paciente1); //////////////
        paciente.setCod_esta(cliente.getCod_esta());
        paciente.setPrecio_total(total);
        paciente.setFecha_ini(Fecha1);
        if (datosconsulta.getId_cargo() == 3) {
            paciente.setId_estado("E");
        }
        if (!"entregar".equals(accion)) {///13/03/2016 si solo ve no se actualiza la fecha de pedido
            this.mi.setModificarPedidoAnt(paciente);
        }

        modelo.put("datos", paciente);
        modelo.put("valor_1", valor_1);
        modelo.put("id_pedido", id_pedido);

        return new ModelAndView("administrarfarmacias/ListaNReceta", modelo);
    }
}
