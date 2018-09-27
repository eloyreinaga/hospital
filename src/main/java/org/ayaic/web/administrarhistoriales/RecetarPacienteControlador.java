package org.ayaic.web.administrarhistoriales;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Consultorios;
import org.ayaic.domain.Cuadernos;
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
public class RecetarPacienteControlador {

    private final MiFacade mi;

    public RecetarPacienteControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/RecetarPaciente.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String nombres = request.getParameter("nombres");
        String nombres2 = request.getParameter("nombres");
        String id_medicamento = request.getParameter("id_medicamento");
        String cantidad = request.getParameter("cantidad");
        String indicacion = request.getParameter("indicacion");
        String otromedica = request.getParameter("otromedica");
        String sig_centro = request.getParameter("sig_centro");
        String servicio = request.getParameter("servicio");
        String id_tipointer = request.getParameter("id_tipointer");
        String id_paciente = request.getParameter("id_paciente");
        String id_pedido = request.getParameter("id_pedido");
        String id_seguro = request.getParameter("id_seguro");
        String id_reservacion = request.getParameter("id_reservacion");
        String id_consultorio = request.getParameter("id_consultorio");
        String id_persona = request.getParameter("id_persona");
        String id_personafar = request.getParameter("id_personafar");
        String expedido = request.getParameter("expedido");
        String tipo_medico = request.getParameter("tipo_medico");
        String spam = request.getParameter("spam");
        String area = request.getParameter("area");
        String fecha = request.getParameter("fecha");
        String id_historial = request.getParameter("id_historial");
        String swinter = request.getParameter("swinter");
        String sweco = request.getParameter("sweco");
        String swfar = request.getParameter("swfar");
        String[] dias = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "20", "25", "30", "40", "50", "60", "70", "80", "90"};
        String ip = request.getRemoteAddr();
        String host = request.getRemoteHost();
        //InetAddress ip;
        //ip = InetAddress.getLocalHost();
        double total;
        int inter = 0, numreceta = 0;
        Date Fecha1 = new Date();

        Pacientes buscarPaciente = this.mi.getDatosPaciente(Integer.parseInt(id_paciente)); // saca un registro a ser modificado
        Consultorios datosconsultorio = this.mi.getDatosConsultorio(cliente.getId_consultorio()); // saca un registro a ser modificado
        modelo.put("datos", buscarPaciente);
        modelo.put("id_pedido", id_pedido);
        modelo.put("id_personafar", id_personafar);
        modelo.put("id_persona", id_persona);
        modelo.put("id_seguro", id_seguro);
        modelo.put("sweco", sweco);
        modelo.put("area", area);
        modelo.put("fecha", fecha);
        modelo.put("dias", dias);
        modelo.put("swfar", swfar);

        Localidades local = new Localidades();
        List Estab1 = this.mi.getListarEsta(local);
        Localidades datoestab = (Localidades) Estab1.get(0);
        modelo.put("datoestab", datoestab);
        modelo.put("codesta", Integer.toString(datoestab.getCod_esta()));
        modelo.put("cod_esta", Integer.toString(cliente.getCod_esta()));
        modelo.put("id_consultoriofar", Integer.toString(cliente.getId_consultorio()));
        modelo.put("id_tipointer", id_tipointer);
        modelo.put("sig_centro", sig_centro);
        modelo.put("servicio", servicio);
        modelo.put("urgencia", Integer.toString(cliente.getId_almacen()));
        modelo.put("tipo_medico", Integer.toString(cliente.getId_cargo()));

        if (tipo_medico == null || "".equals(tipo_medico)) {
            tipo_medico = Integer.toString(cliente.getId_cargo());
        }

        Cuadernos datoc5 = new Cuadernos();  ////saca datos de internados id_historia  15-09-2015
        datoc5.setId_historial(Integer.parseInt(id_reservacion));
        datoc5.setCod_esta(cliente.getCod_esta());
        List C5 = this.mi.getPacienteCuaderno5(datoc5);

        //Recetas datoresult=new Recetas();
        //datoresult.setId_historial(Integer.parseInt(id_historial));
        //datoresult.setId_paciente(Integer.parseInt(id_paciente));
        //datoresult.setId_estado("C");
        //List listarRecetaUltima = this.mi.getListarRecetaAnterior(datoresult); ////getListarRecetaAnteriorCNS
        //modelo.put("listarRecetaUltima", listarRecetaUltima);
        if (!C5.isEmpty()) {
            Cuadernos Inter = (Cuadernos) C5.get(0);
            id_persona = Integer.toString(Inter.getId_persona());
            id_consultorio = Integer.toString(Inter.getId_consultorio());
            inter = Inter.getId_historia();
            modelo.put("inter", Integer.toString(Inter.getId_historia()));
        }

        if ("RepetirReceta".equals(accion)) {
            Recetas datores = new Recetas();
            datores.setId_historial(Integer.parseInt(id_reservacion));
            datores.setId_paciente(Integer.parseInt(id_paciente));
            datores.setId_estado("U");   ////getListarUltReceta
            datores.setId_persona(Integer.parseInt(id_persona));
            datores.setId_programa(cliente.getId_consultorio());
            datores.setId_farmacia(cliente.getId_farmacia());
            datores.setCod_esta(cliente.getCod_esta());
            List listarRec = this.mi.getListarUltReceta(datores);

            if (listarRec == null) {
                return new ModelAndView("Aviso", "mensaje", "No existe recetas previas");
            }

            for (int i = 0; i < listarRec.size(); i++) {

                Recetas dato = (Recetas) listarRec.get(i);
                dato.setId_historial(Integer.parseInt(id_reservacion));
                dato.setId_historia(0);   ////// no no esta internado no debe colocarse esto 14/02/2016
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
                        //return new ModelAndView("Aviso","mensaje", "Existen Medicamentos dispensados por : \n Dr. " +datoMedicamentoPas.getUbicacion() + "\n Establecimiento : " +datoMedicamentoPas.getCodsumi() + "\n Consultorio :  " + datoMedicamentoPas.getTipo()+" \n    . " + "  \nEn fecha : " +ff  +";\n"+datoMedicamentoPas.getMedicamento()+": \nCantidad : "+datoMedicamentoPas.getMes()+" Unid.   por : "+datoMedicamentoPas.getGestion()+" dias");
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
            String dosifica = request.getParameter("dosifica");
            Medicamentos medic = new Medicamentos();
            medic.setId_medicamento(Integer.parseInt(id_medicamento));
            medic.setCodigo(cliente.getCod_esta());
            medic.setCod_esta(cliente.getCod_esta());
            medic.setId_farmacia(cliente.getId_farmacia());
            medic.setExpedido("Z");     /////getDatosMedicamento  "A" se cambia a Z 20/11/2017
            Medicamentos datoMedicamento = this.mi.getDatosMedicamento(medic);
            medic.setExpedido("H");     /////getDatosMedicamentoPasado
            medic.setId_programa(Integer.parseInt(id_paciente));
            Medicamentos datoMedicamentoPas = this.mi.getDatosMedicamentoPasado(medic);

            if ((dosifica == null || "0".equals(dosifica)) && "M".equals(datoMedicamento.getTipo())) {
                return new ModelAndView("Aviso", "mensaje", "Debe elegir el tiempo de Dosificacion del Medicamento");
            }
            if ((dosifica == null || "0".equals(dosifica))) {
                dosifica = "0";
            }

            if (otromedica != null) {
                if (indicacion == null || "".equals(indicacion)) {
                    indicacion = ".";
                }
                indicacion = otromedica + "!" + indicacion;
            }

            Historiales datohi = new Historiales();
            datohi.setCod_esta(cliente.getCod_esta());
            datohi.setId_historial(Integer.parseInt(id_reservacion));
            Historiales datosHis = this.mi.getDatosHistorial(datohi);
            modelo.put("fechamodifi", datosHis.getFecha().getDate() + "/" + (datosHis.getFecha().getMonth() + 1) + "/" + (datosHis.getFecha().getYear() + 1900) + "     " + datosHis.getFecha().getHours() + ":" + datosHis.getFecha().getMinutes() + ":" + datosHis.getFecha().getSeconds()); ///enviar la fechad e atencion
            if (datosHis.getId_cargo() < 2 && "B".equals(datosHis.getId_estado()) && (cliente.getId_cargo() != 7 || cliente.getId_cargo() != 33 || cliente.getId_cargo() != 33)) {
                return new ModelAndView("Aviso", "mensaje", "Los Medicamentos ya fueron despachados en Farmacia\n Si el paciente sigue en Observacion, Realize Internacion (Pre-internado o en evaluacion) y podra seguir con recetas ");
            }

            if (datosHis.getId_cargo() > 1) {
                List listasInter = this.mi.getHistoriaInter(datohi);
                if (!listasInter.isEmpty()) { ////14/01/2017 se aumenta para farmacia no daba internados que estaban metiendo receta
                    Historiales Inter = (Historiales) listasInter.get(0);
                    inter = Inter.getId_historia();
                    id_persona = Integer.toString(Inter.getId_persona());////03-07-2014 aumente para que salga el nombre 
                }
            } else {
                inter = 0;
            }
            //if (datoMedicamentoPas!=null){
            //     if(datoMedicamento.getId_medicamento()==datoMedicamentoPas.getId_medicamento() && datoMedicamentoPas.getSuma1()==Integer.parseInt(id_reservacion) ){
            //           return new ModelAndView("Aviso","mensaje","Este Medicamento YA fue seleccionado en esta receta "); 
            //      }
            // }      30/09/2016 porque mucho joden los medicos  

            /////////cantidades minimas a dispensar del medicamento
            if (!"P".equals(cliente.getArea())) {

                if (datoMedicamento.getMin_exter() == 0 && (datosHis.getId_cargo() < 2)) {
                    return new ModelAndView("Aviso", "mensaje", "El medicamento no se puede dispensar en Consulta Externa");
                }
                /////////cantidades maximas a dispensar del medicamento
                if (datosHis.getId_cargo() == 0) {  /////no internado no valido apara nefrologia 32 y terapia del dolor 52
                    /////////cantidades minimas a dispensar del medicamento
                    if (datoMedicamento.getMin_exter() == 0) {
                        return new ModelAndView("Aviso", "mensaje", "El medicamento no se puede dispensar en Consulta Externa");
                    }
                    if (datoMedicamento.getMax_exter() > 0 && datoMedicamento.getMax_exter() < Double.parseDouble(cantidad) && (cliente.getId_consultorio() != 52 && cliente.getId_consultorio() != 32)) {
                        return new ModelAndView("Aviso", "mensaje", "La cantidad Maxima en Consulta Externa de este medicamento es : " + datoMedicamento.getMax_exter() + "  Unid.");
                    }
                    if (datoMedicamentoPas != null) {
                        if (datoMedicamentoPas.getB1() < datoMedicamentoPas.getGestion()) {
                            String ff = (Integer.toString(datoMedicamentoPas.getFecha_ven().getDate())) + "-" + (Integer.toString(datoMedicamentoPas.getFecha_ven().getMonth() + 1)) + "-" + (Integer.toString(datoMedicamentoPas.getFecha_ven().getYear() + 1900));
                            modelo.put("ff", "El Medicamento " + datoMedicamentoPas.getMedicamento() + " fue dispensado por : \n Dr. " + datoMedicamentoPas.getUbicacion() + "\n Establecimiento : " + datoMedicamentoPas.getCodsumi() + "\n Consultorio :  " + datoMedicamentoPas.getTipo() + " \n    . " + "  \nEn fecha : " + ff + " \n Cantidad : " + datoMedicamentoPas.getMes() + " Unid.   por : " + datoMedicamentoPas.getGestion() + " dias,\n  esta receta sera cargada a su cuenta en caso de auditorias");
                            //return new ModelAndView("Aviso","mensaje", );
                        }
                    }
                } else {
                    if (datosHis.getId_cargo() == 1) {
                        if (datoMedicamento.getMin_emerg() == 0) {
                            return new ModelAndView("Aviso", "mensaje", "El medicamento no se puede dispensar en Emergencias");
                        }
                        if (datoMedicamento.getMax_emerg() > 0 && datoMedicamento.getMax_emerg() < Double.parseDouble(cantidad) && (cliente.getId_consultorio() != 52 || cliente.getId_consultorio() != 32)) {
                            return new ModelAndView("Aviso", "mensaje", "La cantidad Maxima en Emergnecias de este medicamento es : " + datoMedicamento.getMax_emerg() + "  Unid.");
                        }
                        if (datoMedicamentoPas != null) { ////26-09-2017 
                            if (datoMedicamentoPas.getB1() < datoMedicamentoPas.getGestion()) {
                                String ff = (Integer.toString(datoMedicamentoPas.getFecha_ven().getDate())) + "-" + (Integer.toString(datoMedicamentoPas.getFecha_ven().getMonth() + 1)) + "-" + (Integer.toString(datoMedicamentoPas.getFecha_ven().getYear() + 1900));
                                modelo.put("ff", "El Medicamento " + datoMedicamentoPas.getMedicamento() + " fue dispensado por : \n Dr. " + datoMedicamentoPas.getUbicacion() + "\n Establecimiento : " + datoMedicamentoPas.getCodsumi() + "\n Consultorio :  " + datoMedicamentoPas.getTipo() + " \n    . " + "  \nEn fecha : " + ff + " \n Cantidad : " + datoMedicamentoPas.getMes() + " Unid.   por : " + datoMedicamentoPas.getGestion() + " dias,\n  esta receta sera cargada a su cuenta en caso de auditorias");
                                //return new ModelAndView("Aviso","mensaje", );
                            }
                        }
                    }
                }
            }////fin del cliente.getArea()  
            if ("0".equals(id_persona)) {
                id_persona = Integer.toString(datosHis.getId_persona());
            }
            Recetas dato = new Recetas();
            dato.setId_historial(Integer.parseInt(id_reservacion));
            dato.setId_historia(inter);
            dato.setId_medicamento(Integer.parseInt(id_medicamento));
            dato.setSalida(Double.parseDouble(cantidad));
            dato.setIndicacion(indicacion);
            dato.setDosifica(Integer.parseInt(dosifica));
            dato.setId_persona(cliente.getId_persona());
            dato.setId_paciente(cliente.getId_persona());/////quien mete el dato
            if (cliente.getId_especialidad() == 99 || cliente.getId_cargo() == 33 || cliente.getId_cargo() == 34) {  //// si se hace desde farmacia debe colocar el nombre del medico elegido o si es un residente 
                dato.setId_persona(Integer.parseInt(id_persona)); ////05/02/2016 con 34 para cuando lo llena los datos desde cobranza para que llene nombre del doctor       
            }
            dato.setCod_esta(cliente.getCod_esta());
            //if(sig_centro!=null && !"".equals(sig_centro)){
            //    dato.setCod_esta(Integer.parseInt(sig_centro)) ;////
            /// }
            dato.setId_farmacia(cliente.getId_farmacia());
            // CAMBIA EL ESTADO A VENTA
            //if("E".equals(expedido))
            dato.setExpedido(expedido);
            //else
            //    dato.setExpedido("P");
            // try{
            this.mi.setCrearReceta(dato);
            //int iResultado = this.mi.setRegistrarKardex(dato);

            Pacientes datoped = new Pacientes();
            datoped.setId_rubro(1);
            datoped.setId_escolaridad(1);
            datoped.setId_persona(cliente.getId_persona());
            datoped.setId_provincia(Integer.parseInt(id_reservacion));
            List listaCobrosTots = this.mi.getDatosPedidoRubro(datoped);
            for (int j = 0; j < listaCobrosTots.size(); j++) {
                Pacientes datopedi = (Pacientes) listaCobrosTots.get(j);
                id_pedido = Integer.toString(datopedi.getId_pedido());
            }
            /* ya no puede meterse directo desde farmacia 18/05/2014
                 if(datosconsultorio.getId_cargo()==7 || datosconsultorio.getId_cargo()==34){ ////elimine || datosconsultorio.getId_cargo()==34   18/05/2014
                   if("".equals(id_pedido) || id_pedido==""){ ///Si no esta creado el pedido se crea
                       Pacientes paciente = new Pacientes();
                       paciente.setNombres(buscarPaciente.getNombres());
                       paciente.setPrecio_total(0);
                       paciente.setNum_cladoc("0"); 
                       paciente.setId_paciente(Integer.parseInt(id_paciente)); 
                       paciente.setId_carpeta(Integer.parseInt(id_reservacion));
                       paciente.setNit(this.mi.getNumRegistro(Integer.parseInt(id_paciente)) ); 
                       //paciente.setId_costo(0);
                       paciente.setId_persona(Integer.parseInt(id_persona));
                       paciente.setId_dispensa(Integer.parseInt(id_persona));
                           paciente.setId_rubro(1);
                           paciente.setId_estado(expedido);
                           paciente.setNit("0");
                     
                       paciente.setTipo("B") ;
                       paciente.setId_factura(0);/////0 sin factura, 1 con factura, 2, 3, 4, para num correlativo, entradas, ajuste+ y ajuste-
                       paciente.setFec_registro(Fecha1);
                       paciente.setCod_esta(cliente.getCod_esta());
                       this.mi.setCrearPeedido(paciente) ;
                       id_pedido= Integer.toString(this.mi.getNumPedido(paciente));  

                   }////else{ ///Si YA esta creado el pedido se actualiza
                       Historiales datoka= this.mi.getDatosHistorial(Integer.parseInt(id_reservacion)) ; 
                       List listarRecetas = this.mi.getListarRecetasUlt(Integer.parseInt(id_reservacion),Integer.parseInt(id_medicamento),Integer.parseInt(id_persona));
                       Recetas datomed = (Recetas) listarRecetas.get(0);
                       //Ahora empieza a lenar el kardex 
                       Medicamentos medic = new Medicamentos();
                       medic.setId_medicamento(Integer.parseInt(id_medicamento)) ; 
                       medic.setCodigo(cliente.getCod_esta()) ;
                       medic.setExpedido("A") ;
                       Medicamentos buscarMedicamento = this.mi.getDatosMedicamento(medic) ; 

                       Recetas datokardex = new Recetas();
                       datokardex.setId_pedido(Integer.parseInt(id_pedido)) ;
                       datokardex.setId_medicamento(Integer.parseInt(id_medicamento));
                       datokardex.setSalida(Double.parseDouble(cantidad));
                       datokardex.setEntrada(0);
                       datokardex.setCosto_unit(buscarMedicamento.getCosto_unit());
                       datokardex.setPrecio_venta(buscarMedicamento.getPrecio_venta());
                       datokardex.setNro_lote(buscarMedicamento.getNro_lote());
                       datokardex.setFecha_ven(buscarMedicamento.getFecha_ven());
                       datokardex.setId_receta(datomed.getId_detalle());
                     
                           if("E".equals(expedido))  expedido="V";
                           datokardex.setExpedido(expedido);
                           buscarMedicamento.setStocks(buscarMedicamento.getStocks()-datokardex.getSalida());   
                      
                       // entregamos el medicamento
                       datokardex.setId_prestacion(cliente.getCod_esta());
                       datokardex.setId_persona(cliente.getId_persona()) ; 
                       datokardex.setId_factura(0) ; 
                       ///this.mi.setCrearKardex(datokardex); 
                       int iResultado = this.mi.setRegistrarKardex(datore);
                       ///Busca ese medicamento y lo cambia de estado a entregado
                       datomed.setId_historial(Integer.parseInt(id_reservacion));
                       datomed.setId_medicamento(Integer.parseInt(id_medicamento));
                       datomed.setId_receta(datomed.getId_detalle());
                       datomed.setId_estado("B");
                       datomed.setCod_esta(cliente.getCod_esta());
                       this.mi.setModificarEstadoReceta(datomed);
                       // acutalizamos el stock del medicamento
                       buscarMedicamento.setCod_esta(cliente.getCod_esta());
                       buscarMedicamento.setStock(buscarMedicamento.getStock()-datokardex.getSalida());   
                       this.mi.setModificarMedicamento(buscarMedicamento);
                       Recetas midato = new Recetas();
                       midato.setId_pedido(Integer.parseInt(id_pedido));
                       midato.setId_prestacion(cliente.getCod_esta());
                        List listarKardex = this.mi.getListarKardex(midato);
                        modelo.put("listarKardex", listarKardex);
                        total=0;
                        for(int i=0;i<listarKardex.size();i++){
                             midato=(Recetas)listarKardex.get(i);
                             total=total+midato.getPrecio_total();
                        }        
                        // actualiza el precio total
                        Pacientes paciente = this.mi.getDatosPedido(Integer.parseInt(id_pedido)) ; 
                        paciente.setPrecio_total(total);
                        paciente.setFecha_ini(datoka.getFecha());
                        this.mi.setModificarPedidoAnt(paciente);
                        modelo.put("precio_total", Double.toString(total));
                   // }   
                  }
             */    ///// fin no puede meterse directo desde farmacia 18/05/2014
            //}catch (Exception e){ 
            //       return new ModelAndView("Aviso","mensaje", "La actualizacion no se cumplio");
            // }  
        }

        if ("eliminar".equals(accion)) {
            String id_detalle = request.getParameter("id_detalle");
            Recetas dato = new Recetas();
            dato.setId_historial(Integer.parseInt(id_reservacion));
            dato.setId_medicamento(Integer.parseInt(id_medicamento));
            dato.setId_detalle(Integer.parseInt(id_detalle));

            //dato.setMedico(ip.getHostAddress());///20/07/2014
            //dato.setMedicamento(ip.getHostName());///20/07/2014
            dato.setMedico(ip);///01/03/2016
            dato.setMedicamento(host);///01/03/2016
            dato.setIndicacion("E");///20/07/2014
            dato.setId_persona(cliente.getId_persona());
            dato.setId_paciente(cliente.getId_persona());///20/07/2014
            dato.setCod_esta(cliente.getCod_esta());///20/07/2014

            if (datosconsultorio.getId_cargo() == 7 && (!"".equals(id_pedido)) || id_pedido != "") {  ///solo para admisnitrador 
                Medicamentos medic = new Medicamentos();
                medic.setId_medicamento(Integer.parseInt(id_medicamento));
                medic.setCodigo(cliente.getCod_esta());
                medic.setCod_esta(cliente.getCod_esta());
                medic.setId_farmacia(cliente.getId_farmacia());
                medic.setExpedido("B");    /////getDatosMedicamentoB
                Medicamentos buscarMedicamento = this.mi.getDatosMedicamentoB(medic);

                Recetas datokelim = new Recetas();
                datokelim.setId_estado("K"); /////setEliminarKardexReceta
                datokelim.setId_factura(Integer.parseInt(id_detalle));
                datokelim.setCod_esta(cliente.getCod_esta());
                this.mi.setEliminarKardexReceta(datokelim);
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
            this.mi.setEliminarReceta(dato);
        }

        if ("N301".equals(accion) || "R303".equals(accion) || "RepRoja".equals(accion) || "Negra".equals(accion) || "Roja".equals(accion) || "Receta".equals(accion) || "I301".equals(accion) || "Multiple".equals(accion) || "UniDosis".equals(accion) || "protocolomed".equals(accion)) {
            String id_historia2 = request.getParameter("id_historia2");
            String repetirrec = "No";

            Historiales datohi = new Historiales();
            datohi.setCod_esta(cliente.getCod_esta());
            datohi.setId_historial(Integer.parseInt(id_reservacion));
            Historiales datoHis = this.mi.getDatosHistorial(datohi);

            modelo.put("datosHis", datoHis);

            datohi.setId_historia(inter);
            if ((id_historia2 != null && !"".equals(id_historia2))) {
                datohi.setId_historia(Integer.parseInt(id_historia2));
            }
            datohi.setAccion("R");/////getHistoriaInterMedicoReceta 
            List datoHisI = this.mi.getHistoriaInterMedicoReceta(datohi);
            if (datoHisI.size() > 0) {
                Historiales datoIntera = (Historiales) datoHisI.get(0);
                id_persona = Integer.toString(datoIntera.getId_persona());
                id_consultorio = Integer.toString(datoIntera.getId_consultorio());
                modelo.put("datoInterna", datoIntera);
            }

            modelo.put("datosHisI", datoHisI);

            Prestaciones prestac = new Prestaciones();
            prestac.setId_historial(Integer.parseInt(id_reservacion));
            prestac.setId_persona(datoHis.getId_persona());
            prestac.setCod_esta(cliente.getCod_esta());
            List listarPre = this.mi.getListarSumiRecetas(prestac);
            modelo.put("listarPres", listarPre);

            Personas buscarEmpleado = this.mi.getDatosPersona(Integer.parseInt(id_persona));
            modelo.put("datosMed", buscarEmpleado);

            Pacientes buscarPac = this.mi.getDatosPaciente(datoHis.getId_paciente());
            modelo.put("datosPac", buscarPac);

            Pacientes paciente = new Pacientes();
            paciente.setTipo("U");    //////getListarPacientesCnsUnico   lugarci as expedido
            paciente.setId_paciente(datoHis.getId_paciente());
            List buscarPacEmp = this.mi.getListarPacientesCnsUnico(paciente);
            modelo.put("datosPacEmp", buscarPacEmp);

            Recetas midato = new Recetas();
            midato.setId_historial(Integer.parseInt(id_reservacion));
            midato.setId_persona(cliente.getId_persona()); ////// muchas fallas revisar 30/12/2015
            if (cliente.getId_especialidad() == 99) {
                midato.setId_persona(Integer.parseInt(id_persona));
            }

            if (cliente.getId_piso() == 1) {
                if ("RepRoja".equals(accion)) {
                    accion = "Receta";
                    repetirrec = "Si";
                    midato.setId_estado("H"); ///getListarRepRecetaCNS
                    midato.setId_historia(Integer.parseInt(id_historia2));
                    modelo.put("inter", id_historia2);
                    inter = Integer.parseInt(id_historia2); ///23/02/2016 se crea para reimprimir la receta
                }
                if (!"UniDosis".equals(accion) && !"Negra".equals(accion) && !"Roja".equals(accion) && !"protocolomed".equals(accion)) {
                    accion = "Receta";
                    midato.setId_estado("M"); ////para piso 2 getListarRecetasMedico todas las recetas toda la internacion
                    midato.setId_historia(inter);
                }
            }
            if (inter > 0) { ///// si esta internado 04/02/2016
                midato.setId_historia(inter);
            }

            midato.setId_farmacia(cliente.getId_farmacia());
            midato.setCod_esta(cliente.getCod_esta());

            if ("P".equals(datoestab.getArea())) {
                midato.setId_estado("P");  //////////////para clinicas privadas ///sin estado 'A'
            }
            List listarRecetas = this.mi.getListarRecetasMedico(midato);
            modelo.put("listarRecetas", listarRecetas);

            if (datoestab.getCod_esta() == 200010) {
                midato.setId_estado("C"); ////getListarRecetasMedicoCNS
                if ("Si".equals(repetirrec)) {
                    midato.setId_estado("H"); ////getListarRepRecetaCNS
                }
                List listarRecetas2 = this.mi.getListarRepRecetaCNS(midato);
                modelo.put("listarRecetas", listarRecetas2);
            }

            if (listarRecetas.size() > 0) {   /////para contar numero de recetas y poder imprimir en otra hoja
                Recetas datoReceta = (Recetas) listarRecetas.get(0);
                numreceta = listarRecetas.size();
                modelo.put("numreceta", Integer.toString(datoReceta.getId_detalle() - 2700000));  ////ligado historiales getListarAtendidosFarNom
                //if(cliente.getCod_esta()==700241){
                //   modelo.put("numreceta", Integer.toString(datoReceta.getId_detalle()-200000)); 
                //}
            }
            if (listarRecetas.size() == 0 && !"RepRoja".equals(accion)) {
                return new ModelAndView("Aviso", "mensaje", " NO existe medicamentos para Imprimir");
            }

            List listarSeguros = this.mi.getListarSeguros("A");
            modelo.put("listarSeguros", listarSeguros);

            modelo.put("dato", cliente);
            modelo.put("idhistorial", id_reservacion);

            if ("I301".equals(accion)) {
                return new ModelAndView(new ListarRecetaCaja2PDF(), modelo);
            }
            if ("RepRoja".equals(accion) || "Negra".equals(accion) || "Roja".equals(accion) || "UniDosis".equals(accion) || "Multiple".equals(accion)) {
                midato.setId_historia(inter);
                midato.setId_estado("C");
                List listarRecetas3 = this.mi.getListarRecetasMedicoCNS(midato);
                modelo.put("listarRecetas", listarRecetas3);     ////ffffffffffffffffff
                if ("RepRoja".equals(accion)) {
                    midato.setId_estado("H");
                    midato.setId_historia(Integer.parseInt(id_historia2));
                    List listarRecetas31 = this.mi.getListarRepRecetaCNS(midato);
                    modelo.put("listarRecetas", listarRecetas31);     ////ffffffffffffffffff
                }

                if (listarRecetas3.isEmpty()) {   /////para contar numero de recetas y poder imprimir en otra hoja
                    return new ModelAndView("Aviso", "mensaje", " NO existe medicamentos para Imprimir");
                }
                Recetas datoReceta = (Recetas) listarRecetas3.get(0);
                if (listarRecetas3.size() > 8) {   /////para contar numero de recetas y poder imprimir en otra hoja
                    numreceta = listarRecetas.size();
                }
                modelo.put("numreceta", Integer.toString(datoReceta.getId_detalle() - 2700000)); ///ligado historiales getListarAtendidosFarNom
                //if(cliente.getCod_esta()==700241){
                //   modelo.put("numreceta", Integer.toString(datoReceta.getId_detalle()-200000)); 
                //}
                modelo.put("farma", Integer.toString(datoReceta.getId_farmacia()));

                if ("Multiple".equals(accion)) {
                    //if(numreceta>10){
                    //  return new ModelAndView(new ListarRecetaMedMultiCaja2PDF(), modelo);  
                    //}
                    return new ModelAndView(new ListarRecetaMedMultiCajaPDF(), modelo);
                }
                if ("Negra".equals(accion)) {
                    //if(numreceta>10){
                    //   return new ModelAndView(new ListarRecetaEmergNegra2PDF(), modelo);
                    //}
                    return new ModelAndView(new ListarRecetaEmergNegraPDF(), modelo);
                }
                if ("Roja".equals(accion) || "RepRoja".equals(accion)) {
                    //if(numreceta>10){
                    //   return new ModelAndView(new ListarRecetaEmergRoja2PDF(), modelo);
                    //}
                    return new ModelAndView(new ListarRecetaEmergRojaPDF(), modelo);
                }
                return new ModelAndView(new ListarRecetaMedCajaPDF(), modelo);
            }
            if ("protocolomed".equals(accion)) {
                midato.setId_estado("C");   //////getListarRecetasCaros
                List listarRecetaCaro = this.mi.getListarRecetasCaros(midato);
                modelo.put("listarRecetaCaro", listarRecetaCaro);
                return new ModelAndView(new RecetaProtocoloMedPDF(), modelo);
            }

            if ("P".equals(cliente.getArea())) {
                return new ModelAndView(new ListarRecetaMedPriv2PDF(), modelo);   /////clinicas privadas
            } else {
                if (datoestab.getCod_esta() == 200010) {////cajas
                    if (cliente.getId_rol2() == 29) {
                        return new ModelAndView(new ListarRecetaCarmeloPDF(), modelo);
                    }

                    if ("N301".equals(accion) || "R303".equals(accion)) {
                        if ("N301".equals(accion)) {
                            return new ModelAndView(new ListarRecetaCaja2PDF(), modelo);
                        } else {
                            return new ModelAndView(new ListarRecetaCajaEmergPDF(), modelo); ////roja
                        }
                    }

                    if (datoHis.getId_cargo() > 1 && cliente.getId_especialidad() != 98) {
                        return new ModelAndView(new ListarRecetaCajaIntPDF(), modelo);////receta roja internacion
                    } else {
                        if (cliente.getId_almacen() == 1) {
                            if (cliente.getCod_esta() == 200721) {
                                return new ModelAndView(new ListarRecetaCaja2PDF(), modelo); ///negra
                            }
                            return new ModelAndView(new ListarRecetaCajaEmergPDF(), modelo); ////roja
                        } else {
                            return new ModelAndView(new ListarRecetaCaja2PDF(), modelo);
                        }
                    }
                } else {/////sector publico
                    return new ModelAndView(new ListarRecetaMedPDF(), modelo);
                }
            }
        }

        Medicamentos dato = new Medicamentos();
        dato.setId_persona(Integer.parseInt(id_persona));
        dato.setExpedido(expedido);
        dato.setCod_esta(cliente.getCod_esta());
        dato.setId_farmacia(cliente.getId_farmacia());

        Recetas datore = new Recetas();
        datore.setId_historial(Integer.parseInt(id_reservacion));
        datore.setCod_esta(cliente.getCod_esta());
        datore.setId_farmacia(cliente.getId_farmacia());
        datore.setId_estado("%");
        if (nombres == null) {
            nombres = " ";
        }
        nombres = nombres.trim();
        nombres = nombres.replaceAll(" +", " ");
        //modelo.put("nombres2", nombres);
        modelo.put("nombres", nombres);
        nombres = nombres.replaceAll("\\s", ":*&");
        nombres = nombres.replaceAll("ñ", "n");
        nombres = nombres.replaceAll("Ñ", "N");
        if (nombres.length() > 0) {
            nombres = nombres + ":*";
        }

        if ("P".equals(datoestab.getArea())) {
            datore.setId_estado("P");  //////////////para clinicas privadas
        }
        datore.setId_estado("C");   //////getListarRecetasCaros
        List listarRecetaCaro = this.mi.getListarRecetasCaros(datore);
        if (listarRecetaCaro.size() > 0) {
            modelo.put("restrig", "si");
        }
        modelo.put("listarRecetaCaro", listarRecetaCaro);
        datore.setId_estado("%");
        if ("si".equals(spam)) {
            dato.setId_farmacia(cliente.getId_farmacia());
            List listarMedicamentosCot = this.mi.getListarMedicamentosCotb1(dato);
            modelo.put("listarMedicamentosCot", listarMedicamentosCot);

            List listarRecetas = this.mi.getListarRecetas(datore);
            modelo.put("listarRecetas", listarRecetas);

            dato.setMedicamento(nombres);
            List listarMedicamentos = this.mi.getListarMedicamentosb1(dato);
            modelo.put("listarMedicamentos", listarMedicamentos);
            modelo.put("spam", "si");
            modelo.put("expedido", "P");
            dato.setExpedido("X");
            if (cliente.getCod_esta() == 700168 || cliente.getCod_esta() == 700181) {///para pacientes postpagos que debe llenarse sus economico 30/06/2014 solo san ignacio
                dato.setCod_esta(cliente.getCod_esta());
                List listarMedicamentos3 = this.mi.getListarMedicamentos(dato);
                modelo.put("listarMedicamentos", listarMedicamentos3);
            }
        } else {
            dato.setExpedido(expedido);
            dato.setExpedido("V");
            dato.setId_farmacia(cliente.getId_farmacia());
            List listarMedicamentosCot = this.mi.getListarMedicamentosCot(dato);  ////getListarMedicamentosCot
            modelo.put("listarMedicamentosCot", listarMedicamentosCot);

            List listarRecetas = this.mi.getListarRecetas(datore);
            modelo.put("listarRecetas", listarRecetas);
            if ("P".equals(datoestab.getArea())) {
                Recetas midato = new Recetas();
                midato.setId_historial(Integer.parseInt(id_reservacion));
                midato.setId_persona(cliente.getId_persona()); ////// muchas fallas revisar 30/12/201
                midato.setId_historia(inter);
                midato.setId_farmacia(cliente.getId_farmacia());
                midato.setCod_esta(cliente.getCod_esta());
                List listarRecetas2 = this.mi.getListarRecetasMedico(midato);
                modelo.put("listarRecetas", listarRecetas2);
            }
            

            dato.setMedicamento(nombres);
            dato.setCod_esta(cliente.getCod_esta());
            dato.setId_farmacia(cliente.getId_farmacia());
            List listarMedicamentos = this.mi.getListarMedicamentos(dato);  ////getListarMedicamentos
            modelo.put("listarMedicamentos", listarMedicamentos);
            //modelo.put("expedido", "E");
        }

        modelo.put("id_paciente", id_paciente);
        modelo.put("id_reservacion", id_reservacion);
        modelo.put("id_consultorio", id_consultorio);
        modelo.put("id_persona", id_persona);
        modelo.put("swinter", swinter);
        modelo.put("expedido", expedido);
        modelo.put("tipo_medico", tipo_medico);
        // Calculamos el total a pagar
        List listarKardex = this.mi.getListarKardexPago(Integer.parseInt(id_reservacion));
        modelo.put("listarKardex", listarKardex);
        Recetas midato = new Recetas();
        total = 0;
        for (int i = 0; i < listarKardex.size(); i++) {
            midato = (Recetas) listarKardex.get(i);
            total = total + midato.getPrecio_total();
        }
        // actualiza el precio total
        return new ModelAndView("administrarhistoriales/RecetarPaciente", modelo);
    }
}
