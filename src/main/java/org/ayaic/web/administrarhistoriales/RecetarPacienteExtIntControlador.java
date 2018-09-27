package org.ayaic.web.administrarhistoriales;

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
import org.ayaic.domain.Recetas;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RecetarPacienteExtIntControlador {

    private final MiFacade mi;

    public RecetarPacienteExtIntControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/RecetarPacienteExtInt.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String nombres = request.getParameter("nombres");
        String nombres2 = request.getParameter("nombres");
        String id_medicamento = request.getParameter("id_medicamento");
        String cantidad = request.getParameter("cantidad");
        String indicacion = request.getParameter("indicacion");
        String id_pedido = request.getParameter("id_pedido");
        String id_paciente = request.getParameter("id_paciente");
        String id_reservacion = request.getParameter("id_reservacion");
        String id_consultorio = request.getParameter("id_consultorio");
        String id_persona = request.getParameter("id_persona");
        String expedido = request.getParameter("expedido");
        String modify = request.getParameter("modify");
        String tipo_medico = request.getParameter("tipo_medico");
        String spam = request.getParameter("spam");
        String swinter = request.getParameter("swinter");
        String id_historia = request.getParameter("id_historia");
        String id_historial = request.getParameter("id_historial");
        String[] dias = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "20", "25", "30", "40", "50", "60", "70", "80", "90"};
        String ip = request.getRemoteAddr();
        String host = request.getRemoteHost();
        //InetAddress ip;
        //ip = InetAddress.getLocalHost();
        int inter = 0;
        double total;
        Date Fecha1 = new Date();

        Localidades local = new Localidades();
        List Estab1 = mi.getListarEsta(local);
        Localidades datoestab = (Localidades) Estab1.get(0);
        modelo.put("codesta", Integer.toString(datoestab.getCod_esta()));
        modelo.put("cod_esta", Integer.toString(cliente.getCod_esta()));
        modelo.put("urgencia", Integer.toString(cliente.getId_almacen()));
        modelo.put("id_consultoriofar", Integer.toString(cliente.getId_consultorio()));

        Pacientes buscarPaciente = this.mi.getDatosPaciente(Integer.parseInt(id_paciente)); // saca un registro a ser modificado
        modelo.put("datos", buscarPaciente);
        modelo.put("modify", modify);
        modelo.put("dias", dias);

        if ("".equals(id_reservacion)) {
            id_reservacion = id_historial;
        }

        Consultorios datosconsultorio = this.mi.getDatosConsultorio(cliente.getId_consultorio()); // saca un registro a ser modificado
        Historiales datohi = new Historiales();
        datohi.setCod_esta(cliente.getCod_esta());
        datohi.setId_historial(Integer.parseInt(id_reservacion));
        Historiales datosHis = this.mi.getDatosHistorial(datohi);

        Cuadernos datoc5 = new Cuadernos();  ////saca datos de internados id_historia  15-09-2015
        datoc5.setId_historial(Integer.parseInt(id_reservacion));
        datoc5.setCod_esta(cliente.getCod_esta());
        List C5 = this.mi.getPacienteCuaderno5(datoc5);

        if (!C5.isEmpty()) {
            Cuadernos Inter = (Cuadernos) C5.get(0);
            id_persona = Integer.toString(Inter.getId_persona());
            id_consultorio = Integer.toString(Inter.getId_consultorio());
            inter = Inter.getId_historia();
            modelo.put("inter", Integer.toString(Inter.getId_historia()));////para que no 
        }

        Recetas datore = new Recetas();
        datore.setId_historial(Integer.parseInt(id_reservacion));
        datore.setCod_esta(cliente.getCod_esta());
        datore.setId_estado("%");

        if ("RepetirRecetaI".equals(accion)) {
            Recetas datores = new Recetas();
            datores.setId_historial(Integer.parseInt(id_reservacion));
            datores.setId_historia(inter);
            datores.setId_paciente(Integer.parseInt(id_paciente));
            datores.setId_persona(Integer.parseInt(id_persona));
            datores.setId_programa(cliente.getId_consultorio());
            datores.setId_farmacia(cliente.getId_farmacia());
            datores.setCod_esta(cliente.getCod_esta());
            datores.setId_estado("I");   ////getListarUltRecetaI
            List listarRec = this.mi.getListarUltRecetaI(datores);

            if (listarRec == null) {
                return new ModelAndView("Aviso", "mensaje", "No existe recetas previas");
            }

            for (int i = 0; i < listarRec.size(); i++) {

                Recetas dato = (Recetas) listarRec.get(i);
                dato.setId_historial(Integer.parseInt(id_reservacion));
                dato.setId_historia(inter);   ////// no no esta internado no debe colocarse esto 14/02/2016
                dato.setId_paciente(Integer.parseInt(id_persona));
                dato.setId_medicamento(dato.getId_medicamento());

                Medicamentos medic = new Medicamentos();
                medic.setId_medicamento(dato.getId_medicamento());
                medic.setCodigo(cliente.getCod_esta());
                medic.setCod_esta(cliente.getCod_esta());
                medic.setId_farmacia(cliente.getId_farmacia());
                medic.setExpedido("H");     /////getDatosMedicamentoPasado
                medic.setId_programa(Integer.parseInt(id_paciente));
                Medicamentos datoMedicamentoPas = this.mi.getDatosMedicamentoPasado(medic); ///repite ultima receta

                if (datoMedicamentoPas != null) {
                    if (datoMedicamentoPas.getB1() < datoMedicamentoPas.getGestion()) {
                        String ff = (Integer.toString(datoMedicamentoPas.getFecha_ven().getDate())) + "-" + (Integer.toString(datoMedicamentoPas.getFecha_ven().getMonth() + 1)) + "-" + (Integer.toString(datoMedicamentoPas.getFecha_ven().getYear() + 1900));
                        modelo.put("ff", "El Medicamento " + datoMedicamentoPas.getMedicamento() + " fue dispensado por : \n Dr. " + datoMedicamentoPas.getUbicacion() + "\n Establecimiento : " + datoMedicamentoPas.getCodsumi() + "\n Consultorio :  " + datoMedicamentoPas.getTipo() + " \n    . " + "  \nEn fecha : " + ff + " \n Cantidad : " + datoMedicamentoPas.getMes() + " Unid.   por : " + datoMedicamentoPas.getGestion() + " dias,\n  esta receta sera cargada a su cuenta en caso de auditorias");

                    }
                }

                dato.setSalida(dato.getSalida());
                dato.setExpedido(dato.getExpedido());
                dato.setIndicacion(dato.getIndicacion());
                dato.setDosifica(dato.getDosifica());
                dato.setId_persona(Integer.parseInt(id_persona));
                dato.setId_farmacia(cliente.getId_farmacia());
                dato.setCod_esta(cliente.getCod_esta());
                this.mi.setCrearReceta(dato);
            }
        }

        if ("adicion".equals(accion)) {
            String id_programa = request.getParameter("id_programa");
            String dosifica = request.getParameter("dosifica");
            Medicamentos medics = new Medicamentos();
            medics.setId_medicamento(Integer.parseInt(id_medicamento));
            medics.setCodigo(cliente.getCod_esta());
            medics.setCod_esta(cliente.getCod_esta());
            medics.setId_farmacia(cliente.getId_farmacia());
            medics.setExpedido("A");
            Medicamentos datoMedicamento = this.mi.getDatosMedicamento(medics);
            if ((dosifica == null || "".equals(dosifica) || "0".equals(dosifica)) && "M".equals(datoMedicamento.getTipo())) {
                return new ModelAndView("Aviso", "mensaje", "Debe elegir el tiempo de Dosificacion del Medicamento");
            }
            if ((dosifica == null || "".equals(dosifica) || "0".equals(dosifica))) {
                dosifica = "0";
            }

            Recetas dato = new Recetas();
            dato.setId_historial(Integer.parseInt(id_reservacion));
            dato.setId_historia(inter);
            dato.setId_medicamento(Integer.parseInt(id_medicamento));
            dato.setSalida(Double.parseDouble(cantidad));
            dato.setIndicacion(indicacion);
            dato.setDosifica(Integer.parseInt(dosifica));
            dato.setId_persona(cliente.getId_persona());
            dato.setId_paciente(cliente.getId_persona());
            if (cliente.getId_especialidad() == 99) {
                dato.setId_persona(Integer.parseInt(id_persona));
            }
            // CAMBIA EL ESTADO A VENTA
            dato.setExpedido(expedido);
            datore.setId_estado("B");
            datore.setId_farmacia(cliente.getId_farmacia());
            datore.setCod_esta(cliente.getCod_esta());
            dato.setCod_esta(cliente.getCod_esta());
            dato.setId_farmacia(cliente.getId_farmacia());
            List listarRece = this.mi.getListarRecetas(datore);
            for (int i = 0; i < listarRece.size(); i++) {
                Recetas datoReceta = (Recetas) listarRece.get(i);
                dato.setId_estado("C");
                dato.setId_detalle(datoReceta.getId_detalle());
                dato.setCod_esta(cliente.getCod_esta());
                this.mi.setModificarEstadoInter(dato);
            }
            try {
                this.mi.setCrearReceta(dato);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "La actualizacion no se cumplio");
            }

            if (datosconsultorio.getId_cargo() == 33 && !"modify".equals(modify)) {  ///solo para farmacia cuando hace trabajo de medicos igual SALMI
                if ("".equals(id_pedido) || id_pedido == "" || id_pedido == null) { ///Si no esta creado el pedido se crea
                    //Historiales datohi= new Historiales() ;
                    datohi.setId_historial(Integer.parseInt(id_reservacion));
                    datohi.setCod_esta(cliente.getCod_esta());
                    Historiales datoka = this.mi.getDatosHistorial(datohi);

                    Pacientes paciente = new Pacientes();
                    paciente.setNombres(buscarPaciente.getNombres());
                    paciente.setPrecio_total(0);
                    paciente.setNum_cladoc(this.mi.getNumRegistro(datoka.getId_paciente()));
                    paciente.setId_paciente(datoka.getId_paciente());
                    paciente.setId_carpeta(Integer.parseInt(id_reservacion));
                    paciente.setNit(this.mi.getNumRegistro(datoka.getId_paciente()));
                    //paciente.setId_costo(0);
                    paciente.setId_persona(Integer.parseInt(id_persona));
                    paciente.setId_dispensa(cliente.getId_persona());
                    paciente.setId_pais(inter);
                    if ("E".equals(datoka.getExpedido())) {
                        paciente.setNum_cladoc("0");
                        paciente.setId_rubro(1);
                        paciente.setId_estado("X");
                    } else {
                        paciente.setId_rubro(1);
                        paciente.setId_estado("P");
                    }
                    paciente.setTipo("B");
                    paciente.setCod_esta(cliente.getCod_esta());
                    paciente.setId_factura(this.mi.getNumReceta(paciente));/////0 sin factura, 1 con factura, 2, 3, 4, para num correlativo, entradas, ajuste+ y ajuste-
                    paciente.setFec_registro(Fecha1);
                    this.mi.setCrearPeedido(paciente);
                    //******datoka.setId_historial(Integer.parseInt(id_reservacion)); //actualiza estado de entregado en la receta
                    //******datoka.setId_estado("B");
                    //******this.mi.setModificarEstadoHistoria(datoka);   
                    /////////busca en la tabla recetas el ultimo medicamento
                    Recetas datores = new Recetas();
                    datores.setId_historial(Integer.parseInt(id_reservacion));
                    datores.setId_estado("A");
                    datores.setId_persona(Integer.parseInt(id_persona));
                    datores.setId_historia(inter);
                    datores.setId_farmacia(cliente.getId_farmacia());
                    datores.setCod_esta(cliente.getCod_esta());
                    List listarRecetas = this.mi.getListarRecetasInt(datores);

                    Recetas datomed = (Recetas) listarRecetas.get(0);
                    ////////
                    id_pedido = Integer.toString(this.mi.getNumPedido(paciente));
                    //Ahora empieza a lenar el kardex 
                    Medicamentos medic = new Medicamentos();
                    medic.setId_medicamento(Integer.parseInt(id_medicamento));
                    medic.setCodigo(cliente.getCod_esta());
                    medic.setCod_esta(cliente.getCod_esta());
                    medic.setId_farmacia(cliente.getId_farmacia());
                    medic.setExpedido("B");    /////getDatosMedicamentoB
                    Medicamentos buscarMedicamento = this.mi.getDatosMedicamentoB(medic);

                    Recetas datokardex = new Recetas();
                    datokardex.setId_pedido(Integer.parseInt(id_pedido));
                    datokardex.setId_medicamento(Integer.parseInt(id_medicamento));
                    datokardex.setSalida(Double.parseDouble(cantidad));
                    datokardex.setEntrada(0);
                    datokardex.setCosto_unit(buscarMedicamento.getCosto_unit());

                    datokardex.setNro_lote(buscarMedicamento.getNro_lote());
                    datokardex.setFecha_ven(buscarMedicamento.getFecha_ven());
                    datokardex.setId_receta(datomed.getId_detalle());
                    datokardex.setPrecio_venta(buscarMedicamento.getPrecio_venta());
                    datokardex.setId_programa(1);
                    if ("E".equals(datoka.getExpedido())) {
                        datokardex.setExpedido("V");
                        datokardex.setId_programa(0);
                        buscarMedicamento.setStockv(buscarMedicamento.getStockv() - datokardex.getSalida());
                    } else {
                        datokardex.setExpedido("P");
                        if (id_programa == null) {
                            id_programa = "4";  ////03-04-2014
                        }
                        dato.setId_programa(Integer.parseInt(id_programa));////20-01-2014
                        buscarMedicamento.setStockp(buscarMedicamento.getStockp() - datokardex.getSalida());
                    }
                    // entregamos el medicamento
                    datokardex.setCod_esta(cliente.getCod_esta());
                    datokardex.setId_farmacia(cliente.getId_farmacia());
                    datokardex.setId_persona(cliente.getId_persona());
                    datokardex.setId_factura(0);
                    datokardex.setId_historial(Integer.parseInt(id_reservacion));
                    datokardex.setId_historia(inter);
                    datokardex.setCadena1(ip);///01/03/2016
                    datokardex.setCadena2(host);///01/03/2016
                    ///this.mi.setCrearKardex(datokardex);
                    int iResultado = this.mi.setRegistrarKardex(dato);
                    ///Busca ese medicamento y lo cambia de estado a entregado
                    datomed.setId_historial(Integer.parseInt(id_reservacion));
                    datomed.setId_medicamento(Integer.parseInt(id_medicamento));
                    datomed.setId_receta(datomed.getId_detalle());
                    datomed.setId_estado("B");
                    datomed.setCod_esta(cliente.getCod_esta());
                    this.mi.setModificarEstadoReceta(datomed);
                    // acutalizamos el stock del medicamento
//                    buscarMedicamento.setCod_esta(cliente.getCod_esta());
//                    buscarMedicamento.setStock(buscarMedicamento.getStock() - datokardex.getSalida());
//                    this.mi.setModificarMedicamentoStock(buscarMedicamento);
                } else { ///Si YA esta creado el pedido se actualiza
                    //Historiales datohi= new Historiales() ;
                    datohi.setId_historial(Integer.parseInt(id_reservacion));
                    datohi.setCod_esta(cliente.getCod_esta());
                    Historiales datoka = this.mi.getDatosHistorial(datohi);

                    datore.setId_estado("A");
                    datore.setCod_esta(cliente.getCod_esta());
                    datore.setId_farmacia(cliente.getId_farmacia());
                    List listarRecetas = this.mi.getListarRecetas(datore);
                    Recetas datomed = (Recetas) listarRecetas.get(0);
                    //Ahora empieza a lenar el kardex 

                    Medicamentos medic = new Medicamentos();
                    medic.setId_medicamento(Integer.parseInt(id_medicamento));
                    medic.setCodigo(cliente.getCod_esta());
                    medic.setCod_esta(cliente.getCod_esta());
                    medic.setId_farmacia(cliente.getId_farmacia());
                    medic.setExpedido("B");    /////getDatosMedicamentoB
                    Medicamentos buscarMedicamento = this.mi.getDatosMedicamentoB(medic);

                    Recetas datokardex = new Recetas();
                    datokardex.setId_pedido(Integer.parseInt(id_pedido));
                    datokardex.setId_medicamento(Integer.parseInt(id_medicamento));
                    datokardex.setSalida(Double.parseDouble(cantidad));
                    datokardex.setEntrada(0);
                    datokardex.setCosto_unit(buscarMedicamento.getCosto_unit());
                    datokardex.setPrecio_venta(buscarMedicamento.getPrecio_venta());
                    datokardex.setNro_lote(buscarMedicamento.getNro_lote());
                    datokardex.setFecha_ven(buscarMedicamento.getFecha_ven());
                    datokardex.setId_receta(datomed.getId_detalle());
                    datokardex.setId_programa(1);

                    if ("E".equals(datoka.getExpedido())) {
                        datokardex.setExpedido("V");
                        datokardex.setId_programa(2);
                        buscarMedicamento.setStockv(buscarMedicamento.getStockv() - datokardex.getSalida());
                    } else {
                        datokardex.setExpedido("P");
                        datokardex.setId_programa(3);
                        buscarMedicamento.setStockp(buscarMedicamento.getStockp() - datokardex.getSalida());
                    }
                    // entregamos el medicamento
                    datokardex.setCod_esta(cliente.getCod_esta());
                    datokardex.setId_farmacia(cliente.getId_farmacia());
                    datokardex.setId_persona(cliente.getId_persona());
                    datokardex.setId_factura(0);
                    datokardex.setId_historial(Integer.parseInt(id_reservacion));
                    datokardex.setId_historia(inter);
                    datokardex.setCadena1(ip);///01/03/2016
                    datokardex.setCadena2(host);///01/03/2016
                    ///this.mi.setCrearKardex(datokardex); 
                    int iResultado = this.mi.setRegistrarKardex(datokardex);
                    ///Busca ese medicamento y lo cambia de estado a entregado
                    datomed.setId_historial(Integer.parseInt(id_reservacion));
                    datomed.setId_medicamento(Integer.parseInt(id_medicamento));
                    datomed.setId_receta(datomed.getId_detalle());
                    datomed.setId_estado("B");
                    datomed.setCod_esta(cliente.getCod_esta());
                    this.mi.setModificarEstadoReceta(datomed);
                    // acutalizamos el stock del medicamento
//                    buscarMedicamento.setCod_esta(cliente.getCod_esta());
//                    buscarMedicamento.setStock(buscarMedicamento.getStock() - datokardex.getSalida());
//                    this.mi.setModificarMedicamentoStock(buscarMedicamento);
                    //paciente.setFecha_ini(Fecha1); 
                }
                //Actualiza el costo total de la receta en la tabla pedidos
                Recetas midato = new Recetas();
                midato.setId_pedido(Integer.parseInt(id_pedido));
                midato.setId_prestacion(cliente.getCod_esta());
                midato.setCod_esta(cliente.getCod_esta());
                midato.setId_farmacia(cliente.getId_farmacia());
                List listarKardex = this.mi.getListarKardex(midato);
                modelo.put("listarKardex", listarKardex);
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
            }
        }

        if ("eliminar".equals(accion)) {
            String id_detalle = request.getParameter("id_detalle");
            datore.setId_estado("B");
            List listary = this.mi.getListarRecetas(datore);
            //for (int i = 0; i < listary.size(); i++) {///////////revisar no ppodia borrar despues de repetir receta
            //    Recetas datopre = (Recetas) listary.get(i);	 
            //    if (datopre.getId_medicamento()==Integer.parseInt(id_medicamento)){
            //         return new ModelAndView("Aviso","mensaje", "El medicamento ya fua entregado, no puede eliminar");
            //     }
            //}
            Recetas dato = new Recetas();
            dato.setId_historial(Integer.parseInt(id_reservacion));
            dato.setId_medicamento(Integer.parseInt(id_medicamento));
            dato.setId_detalle(Integer.parseInt(id_detalle));

            dato.setMedico(ip);///20/07/2014
            dato.setMedicamento(host);///20/07/2014
            dato.setIndicacion("E");///20/07/2014
            dato.setId_paciente(cliente.getId_persona());///20/07/2014
            dato.setCod_esta(cliente.getCod_esta());///20/07/2014
            this.mi.setEliminarReceta(dato);

        }

        Medicamentos dato = new Medicamentos();
        dato.setId_persona(Integer.parseInt(id_persona));
        dato.setExpedido(expedido);
        dato.setCod_esta(cliente.getCod_esta());
        dato.setId_farmacia(cliente.getId_farmacia());

        Medicamentos medid = new Medicamentos();
        medid.setCod_esta(cliente.getCod_esta());
        List listarprog = this.mi.getListarProgramas(medid);
        modelo.put("listarProg", listarprog);

        if (nombres != null) {
            nombres = nombres.replaceAll("\\s", "%");
            nombres2 = "%" + nombres + "%";
        } else {
            nombres2 = "";
        }

        //Recetas datore=new Recetas();
        datore.setId_historial(Integer.parseInt(id_reservacion));
        datore.setCod_esta(cliente.getCod_esta());
        datore.setId_farmacia(cliente.getId_farmacia());
        datore.setId_estado("%");
        datore.setId_estado("C");   //////getListarRecetasCaros
        List listarRecetaCaro = this.mi.getListarRecetasCaros(datore);
        if (listarRecetaCaro.size() > 0) {
            modelo.put("restrig", "si");
        }
        datore.setId_estado("%");

        if ("si".equals(spam)) {
            dato.setId_farmacia(cliente.getId_farmacia());
            List listarMedicamentosCot = this.mi.getListarMedicamentosCotb1(dato);
            modelo.put("listarMedicamentosCot", listarMedicamentosCot);

            datore.setId_estado("%");
            datore.setCod_esta(cliente.getCod_esta());
            datore.setId_farmacia(cliente.getId_farmacia());
            List listarRecetas = this.mi.getListarRecetas(datore);
            modelo.put("listarRecetas", listarRecetas);

            dato.setCod_esta(cliente.getCod_esta());
            dato.setMedicamento(nombres2);
            List listarMedicamentos = this.mi.getListarMedicamentosb1(dato);
            modelo.put("listarMedicamentos", listarMedicamentos);
            modelo.put("spam", "si");
        } else {
            dato.setId_farmacia(cliente.getId_farmacia());
            List listarMedicamentosCot = this.mi.getListarMedicamentosCot(dato);
            modelo.put("listarMedicamentosCot", listarMedicamentosCot);

            datore.setId_estado("%");
            datore.setCod_esta(cliente.getCod_esta());
            datore.setId_farmacia(cliente.getId_farmacia());
            List listarRecetas = this.mi.getListarRecetas(datore);
            modelo.put("listarRecetas", listarRecetas);

            dato.setCod_esta(cliente.getCod_esta());
            dato.setMedicamento(nombres2);
            List listarMedicamentos = this.mi.getListarMedicamentos(dato);
            modelo.put("listarMedicamentos", listarMedicamentos);
        }

        modelo.put("nombres", nombres);
        modelo.put("id_paciente", id_paciente);
        modelo.put("id_reservacion", id_reservacion);
        modelo.put("id_historia", id_historia);
        modelo.put("id_consultorio", id_consultorio);
        modelo.put("id_persona", id_persona);
        modelo.put("modify", modify);
        modelo.put("expedido", expedido);
        modelo.put("id_pedido", id_pedido);
        modelo.put("tipo_medico", tipo_medico);
        return new ModelAndView("administrarhistoriales/RecetarPacienteExtInt", modelo);
    }
}
