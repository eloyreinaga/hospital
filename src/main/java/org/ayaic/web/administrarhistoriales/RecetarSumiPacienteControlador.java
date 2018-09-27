package org.ayaic.web.administrarhistoriales;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Consultorios;
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
public class RecetarSumiPacienteControlador {

    private final MiFacade mi;

    public RecetarSumiPacienteControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/RecetarSumiPaciente.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String accionm = request.getParameter("accionm");
        String nombres = request.getParameter("nombres");
        String nombremed = request.getParameter("nombremed");
        String nombresPres = request.getParameter("nombresPres");
        String id_prestacion = request.getParameter("id_prestacion");
        String sig_centro = request.getParameter("sig_centro");
        String servicio = request.getParameter("servicio");
        String id_personafar = request.getParameter("id_personafar");
        String id_tipointer = request.getParameter("id_tipointer");
        String id_paciente = request.getParameter("id_paciente");
        String id_pedido = request.getParameter("id_pedido");
        String id_reservacion = request.getParameter("id_reservacion");
        String id_consultorio = request.getParameter("id_consultorio");
        String id_persona = request.getParameter("id_persona");
        String boton = request.getParameter("boton");
        String expedido = request.getParameter("expedido");
        String tipo_medico = request.getParameter("tipo_medico");
        String fechaenf = request.getParameter("fechaenf");
        String fechamodif = request.getParameter("fechamodif");
        String swenfer = request.getParameter("swenfer");
        String swinter = request.getParameter("swinter");
        String[] dias = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "20", "25", "30", "40", "50", "60", "70", "80", "90"};
        int interna = 0;
        double total;
        Date Fecha1 = new Date();
        String ip = request.getRemoteAddr();
        String host = request.getRemoteHost();
        //InetAddress ip;
        //ip = InetAddress.getLocalHost();

        Localidades local = new Localidades();
        List Estab1 = this.mi.getListarEsta(local);
        Localidades datoestab = (Localidades) Estab1.get(0);
        modelo.put("codesta", Integer.toString(datoestab.getCod_esta()));

        Consultorios datosconsultorio = this.mi.getDatosConsultorio(cliente.getId_consultorio()); // saca un registro a ser modificado

        String medica = "si";
        Pacientes buscarPaciente = this.mi.getDatosPaciente(Integer.parseInt(id_paciente)); // saca un registro a ser modificado
        modelo.put("datos", buscarPaciente);
        modelo.put("id_consultoriofar", Integer.toString(cliente.getId_consultorio()));
        modelo.put("id_tipointer", id_tipointer);
        modelo.put("tipo_medico", tipo_medico);
        modelo.put("sig_centro", sig_centro);
        modelo.put("swenfer", swenfer);
        modelo.put("fechaenf", fechaenf);
        modelo.put("dias", dias);
        if ("".equals(tipo_medico) || tipo_medico == null) {
            tipo_medico = Integer.toString(cliente.getId_cargo());
        }

        if (!"".equals(fechamodif) && fechamodif != null) {
            int dia = Integer.parseInt(fechamodif.substring(0, 2));
            int mes = Integer.parseInt(fechamodif.substring(3, 5));
            int anio = Integer.parseInt(fechamodif.substring(6, 10));
            Date fecha_ate = new Date(anio - 1900, mes - 1, dia);
            modelo.put("fechamodif", fecha_ate);
        }

        modelo.put("administra", Integer.toString(datosconsultorio.getId_cargo()));
        modelo.put("id_consultorio", Integer.toString(datosconsultorio.getId_consultorio()));

        Historiales datohi = new Historiales();
        datohi.setId_historial(Integer.parseInt(id_reservacion));
        datohi.setCod_esta(cliente.getCod_esta());
        Historiales datosHistorial = this.mi.getDatosHistorial(datohi);

        interna = datosHistorial.getId_cargo();
        // datos de una fecha pasada de atencion            
        Date fechaa = datosHistorial.getFecha();
        Recetas datore = new Recetas();
        datore.setId_historial(Integer.parseInt(id_reservacion));
        datore.setCod_esta(cliente.getCod_esta());
        datore.setId_estado("%");
        modelo.put("edad", Integer.toString(datosHistorial.getEdad()));

        if ("adicion".equals(accion)) {            //adiciona prestacion
            String internado = request.getParameter("internado");
            String canti = request.getParameter("canti");
            int presta = 0;
            if (canti == null) {
                canti = "1";
            }
            Prestaciones prestac = new Prestaciones();
            prestac.setId_historial(Integer.parseInt(id_reservacion));
            prestac.setId_prestacion(Integer.parseInt(id_prestacion));
            prestac.setId_persona(Integer.parseInt(id_persona));
            prestac.setCod_esta(cliente.getCod_esta());
            List listaYa = this.mi.getListarSumiRecetas(prestac);
            List listaPre = this.mi.getPrestacionExisteYa(prestac);
            prestac.setId_paciente(Integer.parseInt(id_paciente));
            prestac.setReferido("1"); ////getPrestacionExisteYaDia
            List listaPreDia = this.mi.getPrestacionExisteYaDia(prestac); ////getPrestacionExisteYaDia

            if (!listaPreDia.isEmpty()) { //busca la prestacion en el mismo dia para que no haya duplicados
                Prestaciones datopres = (Prestaciones) listaPreDia.get(0);
                return new ModelAndView("Aviso", "mensaje", "Esta Prestacion ya fue registrada para este paciente en este mismo dia por : " + datopres.getPaquete());
            }

            if (interna == 0 && Integer.parseInt(internado) == 1) {
                return new ModelAndView("Aviso", "mensaje", "Esta Prestacion es de paciente Internado,          interne primero al paciente");
            }
            if (listaPre.isEmpty()) { //no puede aï¿½adir la misma prestacion a no ser que sea inyectable 
                String prestacion = request.getParameter("prestacion");

                Prestaciones dato = new Prestaciones();
                dato.setId_historial(Integer.parseInt(id_reservacion));
                dato.setId_prestacion(Integer.parseInt(id_prestacion));
                dato.setFecha_ini(Fecha1);
                if (datosconsultorio.getId_cargo() == 33 || datosconsultorio.getId_cargo() == 7) {
                    dato.setId_persona(Integer.parseInt(id_persona)); /////13/04/2014 solo para farmacia 
                    dato.setFecha_ini(datosHistorial.getFecha());
                } else {
                    dato.setId_persona(Integer.parseInt(id_persona));////  cliente.getId_persona()        
                }

                dato.setPrestacion(prestacion);
                dato.setCantidad(Integer.parseInt(canti));

                dato.setCod_esta(cliente.getCod_esta());
                dato.setId_grupo(cliente.getId_persona());/////quien mete el dato
                this.mi.setCrearRecetaSumi(dato);

                medica = "no";
                if (datosconsultorio.getId_cargo() == 33) {  ///solo para farmacia llena el CIE10
                    int w = 0;
                    int ws = 0;
                    Historiales datomorbi = new Historiales();
                    datomorbi.setId_reservacion(Integer.parseInt(id_reservacion));
                    datomorbi.setCod_esta(cliente.getCod_esta());
                    List listarmorbi1 = this.mi.getListaMorbi(datomorbi);
                    List listarEnfermedades = this.mi.getListarEnfermedadesCod(prestacion);
                    Historiales morbi = new Historiales();
                    morbi.setId_historial(Integer.parseInt(id_reservacion));
                    morbi.setId_persona(cliente.getId_persona());
                    if (cliente.getId_especialidad() == 99) {
                        morbi.setId_persona(Integer.parseInt(id_persona));
                    }
                    morbi.setCodigo(prestacion);
                    morbi.setTipo("A");
                    morbi.setId_cargo(1 + listarmorbi1.size());
                    morbi.setCod_esta(cliente.getCod_esta());
                    if (listarEnfermedades.size() > 0) /// crea en detalle CIE10 solo si es codigo aceptable no los PC95
                    {
                        this.mi.setCrearMorbilidad(morbi);
                    }
                }
                Recetas datopre = new Recetas();
                datopre.setCod_esta(cliente.getCod_esta());
                datopre.setId_prestacion(Integer.parseInt(id_prestacion));
                datopre.setId_farmacia(cliente.getId_farmacia());
                List listarRecetas = this.mi.getListarMedPaquete(datopre);
                modelo.put("listarRecetasPres", listarRecetas);
            } else {
                return new ModelAndView("Aviso", "mensaje", "La prestacion ya fue Selecionada");
            }
        }

        if ("eliminar".equals(accion)) { ///Elimina Prestacion 
            String id_detalle = request.getParameter("id_detalle");
            String prestacion = request.getParameter("prestacion");

            //Historiales datohi= new Historiales() ;
            datohi.setId_historial(Integer.parseInt(id_reservacion));
            datohi.setCod_esta(cliente.getCod_esta());
            Historiales datosHcl = this.mi.getDatosHistorial(datohi);

            Personas persona = this.mi.getBuscarPersona(cliente.getId_persona());
            Consultorios datosadm = this.mi.getDatosConsultorio(persona.getId_consultorio());

            if (datosadm.getId_cargo() != 7) {
                if ("B".equals(datosHcl.getId_estado())) {
                    return new ModelAndView("Aviso", "mensaje", "Los Medicamentos ya fueron despachados en Farmacia\n Ya no puede modificar Receta");
                }
            }
            Prestaciones dato = new Prestaciones();
            dato.setId_historial(Integer.parseInt(id_reservacion));
            dato.setId_prestacion(Integer.parseInt(id_prestacion));
            dato.setId_persona(Integer.parseInt(id_persona));
            dato.setId_detalle(Integer.parseInt(id_detalle));
            dato.setPaquete(ip);///20/02/2016
            dato.setPrestacion(host);///20/2/2016
            dato.setReferido("E");///20/07/2014
            dato.setId_grupo(cliente.getId_persona());///20/07/2014
            dato.setCod_esta(cliente.getCod_esta());///20/07/2014

            if (datosconsultorio.getId_cargo() == 33) {  ///elimina los medicamentos del kardex
                dato.setDescripcion("K");
                this.mi.setEliminarRecetaMedSumi(dato);
                dato.setDescripcion("N");
                Historiales morbielimina = new Historiales();//Elimina de la tabla detallecie10
                morbielimina.setAccion("K");
                morbielimina.setId_historia(Integer.parseInt(id_reservacion));
                morbielimina.setCodigo(prestacion);
                morbielimina.setCod_esta(cliente.getCod_esta());
                this.mi.setEliminarMorbilidadFarma(morbielimina);
            }

            this.mi.setEliminarRecetaMedSumi(dato);
            if (datosadm.getId_cargo() == 7) {///si es el administrador puede eliminar prestaciones de cualquier usuario
                this.mi.setEliminarRecetaSumiI(dato);
            } else {
                dato.setId_persona(cliente.getId_persona());
                if (datosconsultorio.getId_cargo() == 33) {
                    dato.setId_persona(Integer.parseInt(id_persona));/////15/05/2014 no eliminaba desde farmacia prestaciones mal hechas
                }
                this.mi.setEliminarRecetaSumi(dato);
            }
        }

        if ("modificar".equals(accion)) {
            int presta = 0;
            Prestaciones prestac = new Prestaciones();
            prestac.setId_historial(Integer.parseInt(id_reservacion));
            prestac.setId_persona(Integer.parseInt(id_persona));
            prestac.setCod_esta(cliente.getCod_esta());
            List listaYa = this.mi.getListarSumiRecetas(prestac);
            medica = "no";
            Recetas datopre = new Recetas();
            datopre.setCod_esta(cliente.getCod_esta());
            datopre.setId_prestacion(Integer.parseInt(id_prestacion));
            datopre.setId_farmacia(cliente.getId_farmacia());
            List listarRecetas = this.mi.getListarMedPaquete(datopre);
            modelo.put("listarRecetasPres", listarRecetas);
            modelo.put("tipo_medico", tipo_medico);
        }

        if ("adicionsumi".equals(accion)) {      //adiciona medicamentos
            String id_medicamento = request.getParameter("id_medicamento");
            String entrada = request.getParameter("entrada");
            String indicacion = request.getParameter("indicacion");
            String dosifica = request.getParameter("dosifica");
            int entrad = (int) (Math.round((Double.parseDouble(entrada))));

            //Historiales datohi= new Historiales() ;
            datohi.setCod_esta(cliente.getCod_esta());
            datohi.setId_historial(Integer.parseInt(id_reservacion));
            Historiales datosHcl = this.mi.getDatosHistorial(datohi);

            Medicamentos medic = new Medicamentos();
            medic.setId_medicamento(Integer.parseInt(id_medicamento));
            medic.setCodigo(cliente.getCod_esta());
            medic.setCod_esta(cliente.getCod_esta());
            medic.setId_farmacia(cliente.getId_farmacia());
            medic.setExpedido("Z"); ////"A" se cambia a Z 20/11/2017
            Medicamentos datoMedicamento = this.mi.getDatosMedicamento(medic);
            medic.setExpedido("H");     /////getDatosMedicamentoPasado
            medic.setId_programa(Integer.parseInt(id_paciente));
            Medicamentos datoMedicamentoPas = this.mi.getDatosMedicamentoPasado(medic);
            medic.setExpedido("Y");     /////getDatosMedicamentoExiste
            medic.setB1(Integer.parseInt(id_reservacion));
            medic.setB2(0);
            Medicamentos datoMedicamentoYa = this.mi.getDatosMedicamentoExiste(medic);

            if (datoMedicamentoYa != null) {
                return new ModelAndView("Aviso", "mensaje", "Este medicamento Ya esta selecionado");
            }

            if ((dosifica == null || "".equals(dosifica) || "0".equals(dosifica)) && "M".equals(datoMedicamento.getTipo())) {
                return new ModelAndView("Aviso", "mensaje", "Debe elegir el tiempo de Dosificacion del Medicamento");
            }
            if ((dosifica == null || "".equals(dosifica) || "0".equals(dosifica))) {
                dosifica = "0";
            }

            if (datoMedicamento.getMax_exter() > 0 && datoMedicamento.getMax_exter() < Double.parseDouble(entrada)) {
                return new ModelAndView("Aviso", "mensaje", "La cantidad Maxima en Consulta Externa de este medicamento es : " + datoMedicamento.getMax_exter() + "  Unid.");
            }
            if (datoMedicamentoPas != null) {
                if (datoMedicamentoPas.getB1() < datoMedicamentoPas.getGestion()) {
                    String ff = (Integer.toString(datoMedicamentoPas.getFecha_ven().getDate())) + "-" + (Integer.toString(datoMedicamentoPas.getFecha_ven().getMonth() + 1)) + "-" + (Integer.toString(datoMedicamentoPas.getFecha_ven().getYear() + 1900));
                    modelo.put("ff", "El Medicamento " + datoMedicamentoPas.getMedicamento() + " fue dispensado por : \n Dr. " + datoMedicamentoPas.getUbicacion() + "\n Establecimiento : " + datoMedicamentoPas.getCodsumi() + "\n Consultorio :  " + datoMedicamentoPas.getTipo() + " \n    . " + "  \nEn fecha : " + ff + " \n Cantidad : " + datoMedicamentoPas.getMes() + " Unid.   por : " + datoMedicamentoPas.getGestion() + " dias,\n  esta receta sera cargada a su cuenta en caso de auditorias");
                    //return new ModelAndView("Aviso","mensaje", );
                }
            }

            int inter = 0;
            datohi.setCod_esta(cliente.getCod_esta());
            Historiales datosHis = this.mi.getDatosHistorial(datohi); // saca un registro a ser modificado
            if (datosHis.getId_cargo() > 1 && datosHis.getId_cargo() != 14) {
                List listasInter = this.mi.getHistoriaInter(datohi);
                Historiales Inter = (Historiales) listasInter.get(listasInter.size() - 1);
                inter = Inter.getId_historia();
            }
            if ("B".equals(datosHcl.getId_estado())) {
                return new ModelAndView("Aviso", "mensaje", "Los Medicamentos ya fueron despachados en Farmacia\n Ya no puede modificar Receta");
            }

            Recetas datores = new Recetas();
            datores.setId_historial(Integer.parseInt(id_reservacion));
            datores.setId_estado("A");
            datores.setId_medicamento(Integer.parseInt(id_medicamento));
            datores.setId_historia(inter);
            datores.setId_prestacion(Integer.parseInt(id_prestacion));
            datores.setId_farmacia(cliente.getId_farmacia());
            datore.setCod_esta(cliente.getCod_esta());
            List listaMedYa = this.mi.getListarRecetasYa(datores);
            if (listaMedYa.isEmpty()) {
                Recetas dato = new Recetas();
                dato.setId_historial(Integer.parseInt(id_reservacion));
                dato.setId_historia(inter);
                dato.setId_prestacion(Integer.parseInt(id_prestacion));
                dato.setId_medicamento(Integer.parseInt(id_medicamento));
                dato.setId_persona(Integer.parseInt(id_persona));
                dato.setId_paciente(cliente.getId_persona());////para saber quien mete los datos
                dato.setEntrada(entrad);
                dato.setIndicacion(indicacion);
                dato.setFecha(Fecha1);
                dato.setCod_esta(cliente.getCod_esta());
                dato.setDosifica(Integer.parseInt(dosifica));
                dato.setId_farmacia(cliente.getId_farmacia());
                this.mi.setCrearRecetaMedSumi(dato);
                medica = "no";
                /*
                se elimina para poder hacer desde farmacia SUMI obreros
               if(datosconsultorio.getId_cargo()==33 && !"swinter".equals(swinter)){  ///solo para farmacia cuando hace trabajo de medicos igual SALMI
                    if("".equals(id_pedido) || id_pedido==""){ ///Si no esta creado el pedido se crea
                       //Historiales datohi= new Historiales() ;
                       datohi.setId_historial(Integer.parseInt(id_reservacion)) ;
                       datohi.setCod_esta(cliente.getCod_esta());    
                       Historiales datoka= this.mi.getDatosHistorial(datohi) ;
                       Pacientes paciente = new Pacientes();
                       paciente.setNombres(buscarPaciente.getNombres());
                       paciente.setPrecio_total(0);
                       paciente.setNum_cladoc(this.mi.getNumRegistro(datoka.getId_paciente()) ); 
                       paciente.setId_paciente(datoka.getId_paciente()); 
                       paciente.setId_carpeta(Integer.parseInt(id_reservacion));
                       paciente.setNit(this.mi.getNumRegistro(datoka.getId_paciente()) ); 
                       //paciente.setId_costo(0);
                       paciente.setId_persona(Integer.parseInt(id_persona));
                       paciente.setId_dispensa(cliente.getId_persona());
                      
                           paciente.setId_rubro(1);
                           paciente.setId_estado("S");
                           paciente.setNit("0"+(Integer.toString(1+Integer.parseInt(this.mi.getNumSumi("S",cliente.getCod_esta())))));
                     
                       paciente.setTipo("B") ;
                       paciente.setCod_esta(cliente.getCod_esta());
                       paciente.setId_factura(this.mi.getNumReceta(paciente));/////0 sin factura, 1 con factura, 2, 3, 4, para num correlativo, entradas, ajuste+ y ajuste-
                       paciente.setFec_registro(Fecha1);
                       this.mi.setCrearPeedido(paciente) ;   
                       //******datoka.setId_historial(Integer.parseInt(id_reservacion)); //actualiza estado de entregado en la receta
                       //******datoka.setId_estado("B");
                       //******this.mi.setModificarEstadoHistoria(datoka);   
                       /////////busca en la tabla recetas el ultimo medicamento
                       ///Recetas datores=new Recetas();
                       datores.setId_historial(Integer.parseInt(id_reservacion));
                       datores.setId_medicamento(Integer.parseInt(id_medicamento));
                       datores.setId_persona(Integer.parseInt(id_persona));
                       datores.setId_farmacia(cliente.getId_farmacia());
                       List listarRecetas = this.mi.getListarRecetasUlt(datores);
                       
                       //if(!listarRecetas.isEmpty()){
                          Recetas datomed = (Recetas) listarRecetas.get(0);    
                       //}
                       
                       ////////
                       id_pedido= Integer.toString(this.mi.getNumPedido(paciente));  
                       //Ahora empieza a lenar el kardex 
                       //Medicamentos medic = new Medicamentos();
                       medic.setId_medicamento(Integer.parseInt(id_medicamento)) ; 
                       medic.setCodigo(cliente.getCod_esta()) ;
                       medic.setCod_esta(cliente.getCod_esta()) ;
                       medic.setId_farmacia(cliente.getId_farmacia()) ;
                       medic.setExpedido("B") ;    /////getDatosMedicamentoB
                       Medicamentos buscarMedicamento = this.mi.getDatosMedicamentoB(medic) ; 
           
                       Recetas datokardex = new Recetas();
                       datokardex.setId_pedido(Integer.parseInt(id_pedido)) ;
                       datokardex.setId_medicamento(Integer.parseInt(id_medicamento));
                       datokardex.setSalida(entrad);
                       datokardex.setEntrada(0);
                       datokardex.setCosto_unit(buscarMedicamento.getCosto_unit());
                       
                       datokardex.setNro_lote(buscarMedicamento.getNro_lote());
                       datokardex.setFecha_ven(buscarMedicamento.getFecha_ven());
                       datokardex.setId_receta(datomed.getId_detalle());
                      
                           datokardex.setExpedido("S");
                           datokardex.setPrecio_venta(buscarMedicamento.getCosto_unit());
                           buscarMedicamento.setStocks(buscarMedicamento.getStocks()-datokardex.getSalida());   
                       
                       // entregamos el medicamento
                       datokardex.setCod_esta(cliente.getCod_esta());
                       datokardex.setId_farmacia(cliente.getId_farmacia());
                       datokardex.setId_persona(cliente.getId_persona()) ; 
                       datokardex.setId_factura(0) ; 
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
                       buscarMedicamento.setCod_esta(cliente.getCod_esta());
                       buscarMedicamento.setStock(buscarMedicamento.getStock()-datokardex.getSalida());   
                       this.mi.setModificarMedicamento(buscarMedicamento);                
                    }else{ ///Si YA esta creado el pedido se actualiza
                       //Historiales datohi= new Historiales() ;
                       datohi.setId_historial(Integer.parseInt(id_reservacion)) ;
                       datohi.setCod_esta(cliente.getCod_esta());    
                       Historiales datoka= this.mi.getDatosHistorial(datohi) ; 
                       datore.setId_estado("A");
                       datore.setId_farmacia(cliente.getId_farmacia());
                       List listarRecetas = this.mi.getListarRecetas(datore);
                       Recetas datomed = (Recetas) listarRecetas.get(0);
                       //Ahora empieza a lenar el kardex 
                       ///Medicamentos medic = new Medicamentos();
                       medic.setId_medicamento(Integer.parseInt(id_medicamento)) ; 
                       medic.setCodigo(cliente.getCod_esta()) ;
                       medic.setCod_esta(cliente.getCod_esta()) ;
                       medic.setId_farmacia(cliente.getId_farmacia()) ;
                       medic.setExpedido("B") ;    /////getDatosMedicamentoB
                       Medicamentos buscarMedicamento = this.mi.getDatosMedicamentoB(medic) ;          
                       
                       Recetas datokardex = new Recetas();
                       datokardex.setId_pedido(Integer.parseInt(id_pedido)) ;
                       datokardex.setId_medicamento(Integer.parseInt(id_medicamento));
                       datokardex.setSalida(entrad);
                       datokardex.setEntrada(0);
                       datokardex.setCosto_unit(buscarMedicamento.getCosto_unit());
                       datokardex.setPrecio_venta(buscarMedicamento.getPrecio_venta());
                       datokardex.setNro_lote(buscarMedicamento.getNro_lote());
                       datokardex.setFecha_ven(buscarMedicamento.getFecha_ven());
                       datokardex.setId_receta(datomed.getId_detalle());
                       datokardex.setId_programa(1);
                     
                         datokardex.setExpedido("S");
                         buscarMedicamento.setStocks(buscarMedicamento.getStocks()-datokardex.getSalida());   
                      
                       // entregamos el medicamento
                       datokardex.setCod_esta(cliente.getCod_esta());
                       datokardex.setId_farmacia(cliente.getId_farmacia());
                       datokardex.setId_persona(cliente.getId_persona()) ; 
                       datokardex.setId_factura(0) ; 
                       datokardex.setFecha(fechaa);
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
                       buscarMedicamento.setCod_esta(cliente.getCod_esta());
                       buscarMedicamento.setStock(buscarMedicamento.getStock()-datokardex.getSalida());   
                       this.mi.setModificarMedicamento(buscarMedicamento);
                       //Pacientes paciente = this.mi.getDatosPedido(Integer.parseInt(id_pedido)) ; 
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
                    total=0;
                    for(int i=0;i<listarKardex.size();i++){
                         midato=(Recetas)listarKardex.get(i);
                         total=total+midato.getPrecio_total();
                    }        
                    // actualiza el precio total
                    Pacientes paciente1= new Pacientes() ;
                    paciente1.setId_pedido(Integer.parseInt(id_pedido));
                    paciente1.setCod_esta(cliente.getCod_esta());
                    Pacientes paciente = this.mi.getDatosPedido(paciente1);
                    //Pacientes paciente = this.mi.getDatosPedido(Integer.parseInt(id_pedido)) ; 
                    paciente.setPrecio_total(total);
                    paciente.setFecha_ini(fechaa);
                    this.mi.setModificarPedidoAnt(paciente);
               }
                 */
            } else {
                return new ModelAndView("Aviso", "mensaje", "El medicamento ya fue seleccionado en esta prestacion, elimine y aumente a la cantidad deseada");
            }

            Recetas datopre = new Recetas();
            datopre.setCod_esta(cliente.getCod_esta());
            datopre.setId_prestacion(Integer.parseInt(id_prestacion));
            datopre.setId_farmacia(cliente.getId_farmacia());
            List listarRecetas = this.mi.getListarMedPaquete(datopre);
            modelo.put("listarRecetasPres", listarRecetas);
        }

        if ("eliminarsumi".equals(accion)) {
            //Historiales datohi= new Historiales() ;
            datohi.setId_historial(Integer.parseInt(id_reservacion));
            datohi.setCod_esta(cliente.getCod_esta());
            Historiales datosHcl = this.mi.getDatosHistorial(datohi);
            String id_detalle = request.getParameter("id_detalle");

            if ("B".equals(datosHcl.getId_estado())) {
                return new ModelAndView("Aviso", "mensaje", "Los Medicamentos ya fueron despachados en Farmacia\n Ya no puede modificar Receta");
            }
            String id_medicamento = request.getParameter("id_medicamento");
            Recetas dato = new Recetas();
            dato.setId_detalle(Integer.parseInt(id_detalle));
            dato.setId_historial(Integer.parseInt(id_reservacion));
            dato.setId_prestacion(Integer.parseInt(id_prestacion));
            dato.setId_medicamento(Integer.parseInt(id_medicamento));

            dato.setMedico(ip);///20/07/2014
            dato.setMedicamento(host);///20/07/2014
            dato.setIndicacion("E");///20/07/2014
            dato.setId_paciente(cliente.getId_persona());///20/07/2014
            dato.setCod_esta(cliente.getCod_esta());///20/07/2014

            this.mi.setEliminarReceta(dato);
            medica = "no";
            /*
           if(datosconsultorio.getId_cargo()==33 && !"swinter".equals(swinter)){  ///
               
               Medicamentos medic = new Medicamentos();
               medic.setId_medicamento(Integer.parseInt(id_medicamento)) ; 
               medic.setCodigo(cliente.getCod_esta()) ;
               medic.setCod_esta(cliente.getCod_esta()) ;
               medic.setId_farmacia(cliente.getId_farmacia()) ;
               medic.setExpedido("B") ;    /////getDatosMedicamentoB
               Medicamentos buscarMedicamento = this.mi.getDatosMedicamentoB(medic) ; 
               
               Recetas midato = new Recetas();
               midato.setId_estado("K");   //////setEliminarKardexReceta
               midato.setId_factura(Integer.parseInt(id_detalle));
               midato.setCod_esta(cliente.getCod_esta()) ;
               this.mi.setEliminarKardex(midato);
               //Actualiza el costo total de la receta en la tabla pedidos
               midato.setId_pedido(Integer.parseInt(id_pedido));
               midato.setId_prestacion(cliente.getCod_esta());
               midato.setCod_esta(cliente.getCod_esta());
               midato.setId_farmacia(cliente.getId_farmacia());
               List listarKardex = this.mi.getListarKardex(midato);
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
                Pacientes paciente = this.mi.getDatosPedido(paciente1);
                //Pacientes paciente = this.mi.getDatosPedido(Integer.parseInt(id_pedido)) ; 
                paciente.setPrecio_total(total);
                paciente.setFecha_ini(Fecha1);
                this.mi.setModificarPedidoAnt(paciente);
           }
             */
            Recetas datopre = new Recetas();
            datopre.setCod_esta(cliente.getCod_esta());
            datopre.setId_prestacion(Integer.parseInt(id_prestacion));
            datopre.setId_farmacia(cliente.getId_farmacia());
            List listarRecetas = this.mi.getListarMedPaquete(datopre);
            modelo.put("listarRecetasPres", listarRecetas);
        }

        if ("Receta".equals(accion)) {
            //Historiales datohi= new Historiales() ;
            datohi.setId_historial(Integer.parseInt(id_reservacion));
            datohi.setCod_esta(cliente.getCod_esta());
            Historiales datoHis = this.mi.getDatosHistorial(datohi);
            modelo.put("datosHis", datoHis);

            Prestaciones prestac = new Prestaciones();
            prestac.setId_historial(Integer.parseInt(id_reservacion));
            prestac.setId_persona(datoHis.getId_persona());
            prestac.setCod_esta(cliente.getCod_esta());
            List listarPre = this.mi.getListarSumiRecetas(prestac);
            modelo.put("listarPres", listarPre);

            Personas buscarEmpleado = this.mi.getDatosPersona(datoHis.getId_persona());
            modelo.put("datosMed", buscarEmpleado);

            Pacientes buscarPac = this.mi.getDatosPaciente(datoHis.getId_paciente());
            modelo.put("datosPac", buscarPac);

            Recetas midato = new Recetas();
            midato.setId_historial(Integer.parseInt(id_reservacion));
            midato.setCod_esta(cliente.getCod_esta());
            midato.setId_farmacia(cliente.getId_farmacia());
            List listarRecetas = this.mi.getListarRecetasMedico(midato);
            modelo.put("listarRecetas", listarRecetas);

            List listarSeguros = this.mi.getListarSeguros("A");
            modelo.put("listarSeguros", listarSeguros);

            modelo.put("dato", cliente);

            return new ModelAndView(new ListarRecetaMedPDF(), modelo);
        }
        
        Prestaciones datopres = new Prestaciones();
        datopres.setCod_esta(cliente.getCod_esta());
        if ("BuscarCod".equals(boton)) {
            datopres.setDescripcion(nombres+"%");
            List listPrestacion = this.mi.getListarPrestaciones(datopres);
            modelo.put("listarPrestaciones", listPrestacion);
        }
        if ("BuscarMed".equals(boton)) {
            datopres.setDescripcion(nombremed+"%");
            List listPrestacion = this.mi.getListarMedicamentosSumi(datopres);
            modelo.put("listarPrestaciones", listPrestacion);
        }
        if ("BuscarNom".equals(boton)) {
            datopres.setDescripcion("%"+nombresPres.trim()+"%");
            List listPrestacion = this.mi.getListarPrestacionesDes(datopres);
            modelo.put("listarPrestaciones", listPrestacion);
        }
        
        Prestaciones prestac = new Prestaciones();
        prestac.setId_historial(Integer.parseInt(id_reservacion));
        prestac.setId_persona(cliente.getId_persona());
        prestac.setCod_esta(cliente.getCod_esta());
        if(cliente.getId_rol()==5 || cliente.getId_rol()==27 ){
           prestac.setId_persona(Integer.parseInt(id_persona));
        }
        List listPrestacionCot = this.mi.getListarPrestacionesCot(prestac);
        modelo.put("listarPrestacionesCot", listPrestacionCot);

        List listarRecetas = this.mi.getListarSumiRecetas(prestac);
        modelo.put("listarRecetas", listarRecetas);

        datore.setId_estado("%");
        datore.setId_farmacia(cliente.getId_farmacia());
        datore.setCod_esta(cliente.getCod_esta());
        List listarRecetaSumi = this.mi.getListarRecetas(datore);
        modelo.put("listarRecetaSumi", listarRecetaSumi);

        modelo.put("nombres", nombres);
        modelo.put("nombremed", nombremed);
        modelo.put("nombresPres", nombresPres);
        modelo.put("id_paciente", id_paciente);
        modelo.put("id_reservacion", id_reservacion);
        modelo.put("id_consultorio", id_consultorio);
        modelo.put("id_persona", id_persona);
        modelo.put("expedido", expedido);
        modelo.put("id_pedido", id_pedido);
        modelo.put("swinter", swinter);
        modelo.put("tipo_medico", tipo_medico);
        modelo.put("medica", medica);
        return new ModelAndView("administrarhistoriales/RecetarSumiPaciente", modelo);
    }
}
