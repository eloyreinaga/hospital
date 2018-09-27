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
import org.ayaic.domain.Personas;
import org.ayaic.domain.Prestaciones;
import org.ayaic.domain.Recetas;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RecetarInternadoControlador {

    private final MiFacade mi;

    public RecetarInternadoControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/RecetarInternado.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String accionm = request.getParameter("accionm");
        String nombres = request.getParameter("nombres");
        String nombremed = request.getParameter("nombremed");
        String nombresPres = request.getParameter("nombresPres");
        String id_prestacion = request.getParameter("id_prestacion");
        String id_paciente = request.getParameter("id_paciente");
        String id_pedido = request.getParameter("id_pedido");
        String id_reservacion = request.getParameter("id_reservacion");
        String id_consultorio = request.getParameter("id_consultorio");
        String id_persona = request.getParameter("id_persona");
        String id_persona2 = request.getParameter("id_persona");
        String id_historiax = request.getParameter("id_historia");
        String boton = request.getParameter("boton");
        String expedido = request.getParameter("expedido");
        String tipo_medico = request.getParameter("tipo_medico");
        String id_historia = request.getParameter("id_historia");
        String swinter = request.getParameter("swinter");
        String[] dias = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "20", "25", "30", "40", "50", "60", "70", "80", "90"};
        String swx = request.getParameter("swx");
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
        modelo.put("urgencia", Integer.toString(cliente.getId_almacen()));

        String medica = "si";
        Consultorios datosconsultorio = this.mi.getDatosConsultorio(cliente.getId_consultorio()); // saca un registro a ser modificado
        modelo.put("id_consultorio", Integer.toString(datosconsultorio.getId_consultorio()));

        Pacientes buscarPaciente = this.mi.getDatosPaciente(Integer.parseInt(id_paciente)); // saca un registro a ser modificado
        modelo.put("datos", buscarPaciente);
        modelo.put("administra", Integer.toString(datosconsultorio.getId_cargo()));
        modelo.put("tipo_medico", Integer.toString(datosconsultorio.getId_cargo()));
        modelo.put("dias", dias);

        Historiales datohi = new Historiales();
        datohi.setId_historial(Integer.parseInt(id_reservacion));
        datohi.setCod_esta(cliente.getCod_esta());
        Historiales datosHistorial = this.mi.getDatosHistorial(datohi);

        Cuadernos datoc5 = new Cuadernos();  ////saca datos de internados id_historia  15-09-2015
        datoc5.setId_historial(Integer.parseInt(id_reservacion));
        datoc5.setCod_esta(cliente.getCod_esta());
        List C5 = this.mi.getPacienteCuaderno5(datoc5);

        if (!C5.isEmpty()) {
            Cuadernos Inter = (Cuadernos) C5.get(0);
            id_persona = Integer.toString(Inter.getId_persona());
            id_consultorio = Integer.toString(Inter.getId_consultorio());
            inter = Inter.getId_historia();
            modelo.put("inter", Integer.toString(Inter.getId_historia()));
        }

        //datos de una fecha pasada de atencion            
        Date fechaa = datosHistorial.getFecha();
        modelo.put("fechamod", buscarPaciente);

        if ("adicion".equals(accion)) {
            String canti = request.getParameter("cantid");
            int presta = 0;
            if (canti == null) {
                canti = "1";
            }

            Prestaciones prestac = new Prestaciones();
            prestac.setId_historial(Integer.parseInt(id_reservacion));
            prestac.setId_persona(Integer.parseInt(id_persona));
            prestac.setCod_esta(cliente.getCod_esta());
            List listaYa = this.mi.getListarSumiRecetas(prestac);
            String prestacion = request.getParameter("prestacion");

            prestac.setId_prestacion(Integer.parseInt(id_prestacion));
            List listaPre = this.mi.getPrestacionExisteYa(prestac);
            if (listaPre.isEmpty()) {
                Prestaciones dato = new Prestaciones();
                dato.setId_historial(Integer.parseInt(id_reservacion));
                dato.setId_prestacion(Integer.parseInt(id_prestacion));

                if (datosconsultorio.getId_cargo() == 33 || datosconsultorio.getId_cargo() == 7) {  ///crea con el codigo del dr que lo interno
                    dato.setId_persona(datosHistorial.getId_persona());
                } else {
                    dato.setId_persona(cliente.getId_persona());  //10-07-2014 otra vez debe colocarse el nombre de la persona que esta haciendo            
                }

                if (datosconsultorio.getId_cargo() == 7) {  ///solo el administrador coloca con fechas pasadas
                    dato.setFecha_ini(fechaa);
                } else {
                    dato.setFecha_ini(Fecha1);  //13-08-2014 se coloca la fecha actual            
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
                    morbi.setTipo("B");
                    morbi.setId_cargo(1 + listarmorbi1.size());
                    morbi.setCod_esta(cliente.getCod_esta());
                    modelo.put("tipo_medico", Integer.toString(datosconsultorio.getId_cargo()));
                    if (listarEnfermedades.size() > 0) /// crea en detalle CIE10 solo si es codigo aceptable no los PC95
                    {
                        this.mi.setCrearMorbilidad(morbi);
                    }
                }
                Recetas datomedi = new Recetas();
                datomedi.setCod_esta(cliente.getCod_esta());
                datomedi.setId_prestacion(Integer.parseInt(id_prestacion));
                datomedi.setId_farmacia(cliente.getId_farmacia());
                List listarRecetas = this.mi.getListarMedPaquete(datomedi);
                modelo.put("listarRecetasPres", listarRecetas);
            } else {
                return new ModelAndView("Aviso", "mensaje", "La prestacion ya fue Selecionada, solo puede modificar y agregar medicamentos");
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
            Recetas datomedi = new Recetas();
            datomedi.setCod_esta(cliente.getCod_esta());
            datomedi.setId_prestacion(Integer.parseInt(id_prestacion));
            datomedi.setId_farmacia(cliente.getId_farmacia());
            List listarRecetas = this.mi.getListarMedPaquete(datomedi);
            modelo.put("id_historia", id_historiax);
            modelo.put("listarRecetasPres", listarRecetas);

        }
        if ("eliminar".equals(accion) && "swinter".equals(swinter)) {

            String id_historial = request.getParameter("id_historial");
            String prestacion = request.getParameter("prestacion");
            int persona = 0, swq = 0;

            Personas personax = this.mi.getBuscarPersona(cliente.getId_persona());
            Consultorios datosconsul = this.mi.getDatosConsultorio(personax.getId_consultorio()); // saca un registro a ser modificado
            //Historiales datohi= new Historiales() ;
            datohi.setId_historial(Integer.parseInt(id_reservacion));
            datohi.setCod_esta(cliente.getCod_esta());
            Historiales datosHis = this.mi.getDatosHistorial(datohi);

            if (datosHis.getId_cargo() > 0) {
                List listasInter = this.mi.getHistoriaInter(datohi);
                if (listasInter.size() > 0) {
                    Historiales Inter = (Historiales) listasInter.get(listasInter.size() - 1);
                    inter = Inter.getId_historia();
                    persona = Inter.getId_persona();
                }

            }//Registro de Historia inter si existe y saca datos
            if ("".equals(id_reservacion)) {
                id_reservacion = id_historial;
            }
            Recetas datore = new Recetas();
            datore.setId_historial(Integer.parseInt(id_reservacion));
            datore.setId_farmacia(cliente.getId_farmacia());
            datore.setCod_esta(cliente.getCod_esta());
            datore.setId_estado("B");
            List listarRece = this.mi.getListarRecetas(datore);
            for (int i = 0; i < listarRece.size(); i++) {
                Recetas datoReceta = (Recetas) listarRece.get(i);
                if (Integer.parseInt(id_prestacion) == datoReceta.getId_prestacion()) {
                    swq = 1;
                }
            }//busca medicamentos ya entegados C o B en la prestacion y no podra borrar la prestacion por haber ya entregados
            datore.setId_estado("C");
            List listarRecei = this.mi.getListarRecetasCNS(datore);
            for (int i = 0; i < listarRecei.size(); i++) {
                Recetas datoRecetai = (Recetas) listarRecei.get(i);
                if (Integer.parseInt(id_prestacion) == datoRecetai.getId_prestacion()) {
                    swq = 1;
                }
            }
            //Historiales datohi= new Historiales() ;
            datohi.setId_historial(Integer.parseInt(id_reservacion));
            datohi.setCod_esta(cliente.getCod_esta());
            Historiales datosHcl = this.mi.getDatosHistorial(datohi);

            Prestaciones dato = new Prestaciones();
            dato.setId_historial(Integer.parseInt(id_reservacion));
            dato.setId_prestacion(Integer.parseInt(id_prestacion));
            dato.setId_persona(cliente.getId_persona());  ///11-05-2016 elimino Integer.parseInt(id_persona)); 
            //dato.setId_detalle(Integer.parseInt(id_detalle)); 
            //dato.setPaquete(ip.getHostAddress());///20/07/2014
            //dato.setPrestacion(ip.getHostName());///20/07/2014
            dato.setPaquete(ip);///01/03/2016
            dato.setPrestacion(host);///01/03/2016
            dato.setReferido("E");///20/07/2014
            dato.setId_grupo(cliente.getId_persona());///20/07/2014
            dato.setCod_esta(cliente.getCod_esta());///20/07/2014
            if (datosconsultorio.getId_cargo() == 33) {///03-03-2014 solo para poder eliminar rapido desde farmacia
                persona = Integer.parseInt(id_persona);
            }

            Historiales datoSumi = new Historiales();
            datoSumi.setId_provincia(Integer.parseInt(id_prestacion));
            datoSumi.setId_historial(Integer.parseInt(id_reservacion));
            datoSumi.setCod_esta(cliente.getCod_esta());
            datoSumi.setAccion("Y");   ////getListarPrestacionYa  11-05-2016
            List listarPacientes = this.mi.getListarResumenPrestacion(datoSumi);

            if (!listarPacientes.isEmpty()) {/////11-05-2016  esto es para ver si la prestacion fue dada por este usurioa
                Historiales datopac = (Historiales) listarPacientes.get(0);
                id_persona = Integer.toString(datopac.getRango());
            }

            if ((persona == (Integer.parseInt(id_persona)) && swq == 0) || datosconsul.getId_cargo() == 7) {
                if (datosconsultorio.getId_cargo() == 33) {  ///elimina los medicamentos del kardex
                    dato.setDescripcion("K");
                    this.mi.setEliminarRecetaMedSumi(dato);
                    dato.setDescripcion("N");
                    Historiales morbielimina = new Historiales();//Elimina de la tabla detallecie10
                    morbielimina.setAccion("K");
                    morbielimina.setId_historia(Integer.parseInt(id_reservacion));
                    morbielimina.setCodigo(prestacion);
                    morbielimina.setCod_esta(cliente.getCod_esta());
                    modelo.put("tipo_medico", Integer.toString(datosconsultorio.getId_cargo()));
                    this.mi.setEliminarMorbilidadFarma(morbielimina);
                }

                this.mi.setEliminarRecetaMedSumi(dato);
                this.mi.setEliminarRecetaSumiI(dato);
            } else {
                return new ModelAndView("Aviso", "mensaje", "Esta prestacion fue dada por otro prescriptor   o   Existe medicamentos que ya fueron dispensados");
            }
        }
        if ("adicionsumi".equals(accion)) {///adiciona medicamentos
            String id_medicamento = request.getParameter("id_medicamento");
            String entrada = request.getParameter("entrada");
            String indicacion = request.getParameter("indicacion");
            String dosifica = request.getParameter("dosifica");
            String id_historial = request.getParameter("id_historial");
            String id_programa = request.getParameter("id_programa");

            Medicamentos medic = new Medicamentos();
            medic.setId_medicamento(Integer.parseInt(id_medicamento));
            medic.setCodigo(cliente.getCod_esta());
            medic.setCod_esta(cliente.getCod_esta());
            medic.setId_farmacia(cliente.getId_farmacia());
            medic.setExpedido("A");
            Medicamentos datoMedicamento = this.mi.getDatosMedicamento(medic);

            if ((dosifica == null || "".equals(dosifica) || "0".equals(dosifica)) && "M".equals(datoMedicamento.getTipo())) {
                return new ModelAndView("Aviso", "mensaje", "Debe elegir el tiempo de Dosificacion del Medicamento");
            }
            if ((dosifica == null || "".equals(dosifica) || "0".equals(dosifica))) {
                dosifica = "0";
            }
            //Historiales datohi= new Historiales() ;
            datohi.setId_historial(Integer.parseInt(id_reservacion));
            datohi.setCod_esta(cliente.getCod_esta());
            Historiales datosHcl = this.mi.getDatosHistorial(datohi);

            int entrad = (int) (Math.round((Double.parseDouble(entrada))));

            if (!"".equals(id_historia)) {
                inter = Integer.parseInt(id_historia);
            } else {
                id_historia = "0";
            }
            //Historiales datohi= new Historiales() ;
            datohi.setCod_esta(cliente.getCod_esta());
            datohi.setId_historial(Integer.parseInt(id_reservacion));
            Historiales datosHis = this.mi.getDatosHistorial(datohi);

            if (datosHis.getId_cargo() > 0 && inter == 0) {
                List listasInter = this.mi.getHistoriaInter(datohi);
                Historiales Inter = (Historiales) listasInter.get(listasInter.size() - 1);
                inter = Inter.getId_historia();
            }
            if ("".equals(id_reservacion)) {
                id_reservacion = id_historial;
            }

            medic.setExpedido("Y");     /////getDatosMedicamentoExiste
            medic.setB1(Integer.parseInt(id_reservacion));
            medic.setB2(inter);
            Medicamentos datoMedicamentoYa = this.mi.getDatosMedicamentoExiste(medic);

            if (datoMedicamentoYa != null) {
                return new ModelAndView("Aviso", "mensaje", "Este medicamento Ya esta selecionado en esta receta");
            }

            Recetas datore = new Recetas();
            datore.setId_historial(Integer.parseInt(id_reservacion));
            datore.setId_estado("A");
            datore.setId_medicamento(Integer.parseInt(id_medicamento));
            datore.setId_historia(inter);
            datore.setCod_esta(cliente.getCod_esta());
            datore.setId_prestacion(Integer.parseInt(id_prestacion));
            List listaMedYa = this.mi.getListarRecetasYa(datore);
            if (listaMedYa.isEmpty()) {

                Recetas dato = new Recetas();
                dato.setId_historial(Integer.parseInt(id_reservacion));
                dato.setId_historia(inter);
                dato.setId_prestacion(Integer.parseInt(id_prestacion));
                dato.setId_medicamento(Integer.parseInt(id_medicamento));
                ///if(datosconsultorio.getId_cargo()==33){  ///crea con el codigo del dc que lo interno
                ///    dato.setId_persona(datosHistorial.getId_persona());       
                ///}else{
                dato.setId_persona(cliente.getId_persona());
                if (datoestab.getCod_esta() == 200010) {////////verpara internados debe sacar del cuaderno5
                    dato.setId_persona(Integer.parseInt(id_persona));
                }

                ///}
                dato.setEntrada(entrad);
                dato.setIndicacion(indicacion);
                if (datosconsultorio.getId_cargo() == 7) {  ///solo el administrador coloca con fechas pasadas
                    dato.setFecha(fechaa);
                } else {
                    dato.setFecha(Fecha1);  //13-08-2014 se coloca la fecha actual            
                }
                Recetas datores = new Recetas();
                datores.setId_historial(Integer.parseInt(id_reservacion));
                datore.setId_farmacia(cliente.getId_farmacia());
                datore.setCod_esta(cliente.getCod_esta());
                datores.setId_estado("B");
                List listarRece = this.mi.getListarRecetas(datores);
                for (int i = 0; i < listarRece.size(); i++) {
                    Recetas datoReceta = (Recetas) listarRece.get(i);
                    dato.setId_estado("C");
                    dato.setId_detalle(datoReceta.getId_detalle());
                    dato.setCod_esta(cliente.getCod_esta());
                    this.mi.setModificarEstadoInter(dato);
                }
                datore.setCod_esta(cliente.getCod_esta());
                dato.setCod_esta(cliente.getCod_esta());
                dato.setId_paciente(cliente.getId_persona());/////09/06/2014    
                dato.setDosifica(Integer.parseInt(dosifica));
                dato.setId_farmacia(cliente.getId_farmacia());
                this.mi.setCrearRecetaMedSumi(dato);
                medica = "no";
                if (datosconsultorio.getId_cargo() == 33 && !"swx".equals(swx) && cliente.getCod_esta() == 700181) {  ///solo para farmacia cuendo hace trabajo de medicos igual SALMI
                    if ("".equals(id_pedido) || id_pedido == "" || id_pedido == null || "null".equals(id_pedido)) { ///Si no esta creado el pedido se crea
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
                        modelo.put("tipo_medico", Integer.toString(datosconsultorio.getId_cargo()));
                        if ("E".equals(datoka.getExpedido())) {
                            paciente.setNum_cladoc("0");
                            paciente.setId_rubro(1);
                            paciente.setId_estado("X");
                        } else if ("S".equals(datoka.getExpedido())) {
                            paciente.setId_rubro(1);
                            paciente.setId_estado("S");
                            paciente.setNit("0");
                            //paciente.setNit("0"+(Integer.toString(1+Integer.parseInt(this.mi.getNumSumi("S"))))); borrado 11/09/2017
                        } else {
                            paciente.setId_rubro(1);
                            paciente.setId_estado("P");
                        }
                        if ("swx".equals(swx)) {
                            paciente.setTipo("A");
                        } else {
                            paciente.setTipo("B");
                        }
                        paciente.setFec_registro(Fecha1);
                        paciente.setCod_esta(cliente.getCod_esta());
                        paciente.setId_factura(this.mi.getNumReceta(paciente));/////0 sin factura, 1 con factura, 2, 3, 4, para num correlativo, entradas, ajuste+ y ajuste-
                        this.mi.setCrearPeedido(paciente);
                        //******datoka.setId_historial(Integer.parseInt(id_reservacion)); //actualiza estado de entregado en la receta
                        //******datoka.setId_estado("B");
                        //******this.mi.setModificarEstadoHistoria(datoka);   
                        /////////busca en la tabla recetas el ultimo medicamento
                        datore.setId_estado("A");
                        List listarRecetas = this.mi.getListarRecetas(datore);
                        Recetas datomed = (Recetas) listarRecetas.get(0);
                        ////////
                        id_pedido = Integer.toString(this.mi.getNumPedido(paciente));
                        //Ahora empieza a lenar el kardex 
                        //Medicamentos medic = new Medicamentos();
                        medic.setId_medicamento(Integer.parseInt(id_medicamento));
                        medic.setCodigo(cliente.getCod_esta());
                        medic.setCod_esta(cliente.getCod_esta());
                        medic.setId_farmacia(cliente.getId_farmacia());
                        medic.setExpedido("B");    /////getDatosMedicamentoB
                        Medicamentos buscarMedicamento = this.mi.getDatosMedicamentoB(medic);

                        Recetas datokardex = new Recetas();///para llenar datos al kardex
                        datokardex.setId_pedido(Integer.parseInt(id_pedido));
                        datokardex.setId_medicamento(Integer.parseInt(id_medicamento));
                        datokardex.setSalida(entrad);
                        datokardex.setEntrada(0);
                        datokardex.setCosto_unit(buscarMedicamento.getCosto_unit());
                        datokardex.setPrecio_venta(buscarMedicamento.getPrecio_venta());
                        datokardex.setNro_lote(buscarMedicamento.getNro_lote());
                        datokardex.setFecha_ven(buscarMedicamento.getFecha_ven());
                        datokardex.setId_receta(datomed.getId_detalle());
                        if ("E".equals(datoka.getExpedido())) {
                            datokardex.setExpedido("V");
                            datokardex.setId_programa(0);
                            buscarMedicamento.setStockv(buscarMedicamento.getStockv() - datokardex.getSalida());
                        } else if ("S".equals(datoka.getExpedido())) {
                            datokardex.setExpedido("S");
                            datokardex.setId_programa(datosHistorial.getId_seguro());////20-01-2014
                            buscarMedicamento.setStocks(buscarMedicamento.getStocks() - datokardex.getSalida());
                        } else {
                            datokardex.setExpedido("P");
                            datokardex.setId_programa(datosHistorial.getId_seguro());////20-01-2014
                            buscarMedicamento.setStockp(buscarMedicamento.getStockp() - datokardex.getSalida());
                        }
                        // entregamos el medicamento
                        if ("swx".equals(swx)) {
                            datokardex.setCod_esta(cliente.getCod_esta());
                            datokardex.setId_farmacia(cliente.getId_farmacia());
                            datokardex.setId_persona(cliente.getId_persona());
                            datokardex.setId_factura(0);
                            datokardex.setId_programa(Integer.parseInt(id_programa));////20-05-2014
                            datokardex.setId_historial(Integer.parseInt(id_reservacion));
                            datokardex.setId_historia(inter);
                            datokardex.setCadena1(ip);///01/03/2016
                            datokardex.setCadena2(host);///01/03/2016
                            ////this.mi.setCrearKardex(datokardex); 
                            int iResultado = this.mi.setRegistrarKardex(dato);
                            ///Busca ese medicamento y lo cambia de estado a entregado
                            datomed.setId_historial(Integer.parseInt(id_reservacion));
                            datomed.setId_medicamento(Integer.parseInt(id_medicamento));
                            datomed.setId_receta(datomed.getId_detalle());
                            datomed.setId_estado("B");
                            datomed.setCod_esta(cliente.getCod_esta());
                            this.mi.setModificarEstadoReceta(datomed);
                            // acutalizamos el stock del medicamento
//                            buscarMedicamento.setCod_esta(cliente.getCod_esta());
//                            buscarMedicamento.setStock(buscarMedicamento.getStock() - datokardex.getSalida());
//                            this.mi.setModificarMedicamentoStock(buscarMedicamento);
                        }
                        if (datosconsultorio.getId_cargo() == 33) {
                            datokardex.setId_prestacion(cliente.getCod_esta());
                            datokardex.setId_persona(cliente.getId_persona());
                            datokardex.setId_factura(0);
                            datokardex.setFecha(fechaa);
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
//                            buscarMedicamento.setCod_esta(cliente.getCod_esta());
//                            buscarMedicamento.setStock(buscarMedicamento.getStock() - datokardex.getSalida());
//                            this.mi.setModificarMedicamentoStock(buscarMedicamento);
                        }

                    } else { ///Si YA esta creado el pedido se actualiza
                        //Historiales datohi= new Historiales() ;
                        datohi.setId_historial(Integer.parseInt(id_reservacion));
                        datohi.setCod_esta(cliente.getCod_esta());
                        Historiales datoka = this.mi.getDatosHistorial(datohi);

                        //Recetas datore=new Recetas();
                        //datore.setId_historial(Integer.parseInt(id_reservacion));
                        datore.setId_estado("A");
                        List listarRecetas = this.mi.getListarRecetas(datore);
                        Recetas datomed = (Recetas) listarRecetas.get(0);
                        //Ahora empieza a lenar el kardex 
                        //Medicamentos medic = new Medicamentos();
                        medic.setId_medicamento(Integer.parseInt(id_medicamento));
                        medic.setCodigo(cliente.getCod_esta());
                        medic.setCod_esta(cliente.getCod_esta());
                        medic.setId_farmacia(cliente.getId_farmacia());
                        medic.setExpedido("B");    /////getDatosMedicamentoB
                        Medicamentos buscarMedicamento = this.mi.getDatosMedicamentoB(medic);
                        Recetas datokardex = new Recetas();
                        datokardex.setId_pedido(Integer.parseInt(id_pedido));
                        datokardex.setId_medicamento(Integer.parseInt(id_medicamento));
                        datokardex.setSalida(entrad);
                        datokardex.setEntrada(0);
                        datokardex.setCosto_unit(buscarMedicamento.getCosto_unit());
                        datokardex.setPrecio_venta(buscarMedicamento.getPrecio_venta());
                        datokardex.setNro_lote(buscarMedicamento.getNro_lote());
                        datokardex.setFecha_ven(buscarMedicamento.getFecha_ven());
                        datokardex.setId_receta(datomed.getId_detalle());
                        if ("E".equals(datoka.getExpedido())) {
                            datokardex.setExpedido("V");
                            datokardex.setId_programa(0);
                            buscarMedicamento.setStockv(buscarMedicamento.getStockv() - datokardex.getSalida());
                        } else if ("S".equals(datoka.getExpedido())) {
                            if (datosHistorial.getId_seguro() == 0) {
                                datokardex.setId_programa(datosHistorial.getId_seguro());////09-06-2014    
                            } else {
                                datokardex.setId_programa(1);////09-06-2014     
                            }
                            datokardex.setExpedido("S");
                            buscarMedicamento.setStocks(buscarMedicamento.getStocks() - datokardex.getSalida());
                        } else {
                            datokardex.setExpedido("P");
                            datokardex.setId_programa(Integer.parseInt(id_programa));////20-01-2014
                            buscarMedicamento.setStockp(buscarMedicamento.getStockp() - datokardex.getSalida());
                        }
                        // entregamos el medicamento
                        datokardex.setFecha(Fecha1);
                        datokardex.setId_prestacion(cliente.getCod_esta());
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
//                        buscarMedicamento.setCod_esta(cliente.getCod_esta());
//                        buscarMedicamento.setStock(buscarMedicamento.getStock() - datokardex.getSalida());
//                        this.mi.setModificarMedicamentoStock(buscarMedicamento);
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
                    if (datosconsultorio.getId_cargo() == 33) {
                        paciente.setFecha_ini(fechaa);
                    } else {
                        paciente.setFecha_ini(Fecha1);
                    }
                    this.mi.setModificarPedidoAnt(paciente);
                    Historiales reserva = new Historiales();///coloca en B como entregado la receta internado
                    reserva.setId_estado("B");
                    reserva.setId_historial(inter);  // en la variable inter se capturo el id_historia
                    this.mi.setModificarInter(reserva);
                }
            } else {
                return new ModelAndView("Aviso", "mensaje", "El medicamento ya fue seleccionado en esta prestacion, elimine y aumente a la cantidad deseada");
            }
            Recetas datomedi = new Recetas();
            datomedi.setCod_esta(cliente.getCod_esta());
            datomedi.setId_prestacion(Integer.parseInt(id_prestacion));
            datomedi.setId_farmacia(cliente.getId_farmacia());
            List listarRecetas = this.mi.getListarMedPaquete(datomedi);
            modelo.put("listarRecetasPres", listarRecetas);
        }

        if ("eliminarsumi".equals(accion)) {
            String id_medicamento = request.getParameter("id_medicamento");
            String id_detalle = request.getParameter("id_detalle");
            String id_historial = request.getParameter("id_historial");
            int persona = 0;
            //Historiales datohi= new Historiales() ;
            datohi.setCod_esta(cliente.getCod_esta());
            datohi.setId_historial(Integer.parseInt(id_reservacion));
            Historiales datosHis = this.mi.getDatosHistorial(datohi);

            if (datosHis.getId_cargo() > 0) {
                List listasInter = this.mi.getHistoriaInter(datohi);
                Historiales Inter = (Historiales) listasInter.get(listasInter.size() - 1);
                persona = Inter.getId_persona();
                inter = Inter.getId_historia();
            }
            if ("".equals(id_reservacion)) {
                id_reservacion = id_historial;
            }
            Recetas datore = new Recetas();
            datore.setId_historial(Integer.parseInt(id_reservacion));
            datore.setId_farmacia(cliente.getId_farmacia());
            datore.setCod_esta(cliente.getCod_esta());
            if (datosconsultorio.getId_cargo() == 33) {
                datore.setId_estado("D");
                List listary = this.mi.getListarRecetas(datore);
                for (int i = 0; i < listary.size(); i++) {
                    Recetas datopre = (Recetas) listary.get(i);
                    if (datopre.getId_medicamento() == Integer.parseInt(id_medicamento)) {
                        return new ModelAndView("Aviso", "mensaje", "El medicamento ya fua entregado, no puede eliminar");
                    }
                }
            } else {
                datore.setId_estado("B");
                List listary = this.mi.getListarRecetas(datore);
                for (int i = 0; i < listary.size(); i++) {
                    Recetas datopre = (Recetas) listary.get(i);
                    if (datopre.getId_medicamento() == Integer.parseInt(id_medicamento)) {
                        return new ModelAndView("Aviso", "mensaje", "El medicamento ya fua entregado, no puede eliminar");
                    }
                }
            }

            Recetas dato = new Recetas();
            dato.setId_detalle(Integer.parseInt(id_detalle));
            dato.setId_estado("I");
            dato.setId_historia(inter);
            dato.setId_prestacion(Integer.parseInt(id_prestacion));
            dato.setId_persona(Integer.parseInt(id_persona));
            dato.setId_medicamento(Integer.parseInt(id_medicamento));
            //dato.setMedico(ip.getHostAddress());///20/07/2014
            //dato.setMedicamento(ip.getHostName());///20/07/2014
            dato.setMedico(ip);///01/03/2016
            dato.setMedicamento(host);///01/03/2016
            dato.setIndicacion("E");///20/07/2014
            dato.setId_paciente(cliente.getId_persona());///20/07/2014
            dato.setCod_esta(cliente.getCod_esta());///20/07/2014

            if (persona == (Integer.parseInt(id_persona2)) || datosconsultorio.getId_cargo() == 33) // try{
            {
                this.mi.setEliminarReceta(dato);
            } else {
                return new ModelAndView("Aviso", "mensaje", "El medicamento pertenece a otra Prescriptor");
            }
            // }catch (Exception e){ 
            //    return new ModelAndView("Aviso","mensaje","Este medicamento pertenece a otra receta");
            // }   
            medica = "no";
            if (datosconsultorio.getId_cargo() == 33) {  ///
                if ("".equals(id_pedido)) {
                    Pacientes paciente1 = new Pacientes();
                    paciente1.setId_detalle(Integer.parseInt(id_detalle));
                    paciente1.setCod_esta(cliente.getCod_esta());
                    Pacientes pedidos = this.mi.getDatosPedido(paciente1);
                    id_pedido = Integer.toString(pedidos.getId_pedido());
                }
                Medicamentos medic = new Medicamentos();
                medic.setId_medicamento(Integer.parseInt(id_medicamento));
                medic.setCodigo(cliente.getCod_esta());
                medic.setCod_esta(cliente.getCod_esta());
                medic.setId_farmacia(cliente.getId_farmacia());
                medic.setExpedido("B");    /////getDatosMedicamentoB
                Medicamentos buscarMedicamento = this.mi.getDatosMedicamentoB(medic);

                Recetas datokelim = new Recetas();
                datokelim.setId_estado("K");  //////setEliminarKardexReceta
                datokelim.setId_factura(Integer.parseInt(id_detalle));
                datokelim.setCod_esta(cliente.getCod_esta());
                this.mi.setEliminarKardexReceta(datokelim);
                modelo.put("tipo_medico", Integer.toString(datosconsultorio.getId_cargo()));
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
            Recetas datomedi = new Recetas();
            datomedi.setCod_esta(cliente.getCod_esta());
            datomedi.setId_prestacion(Integer.parseInt(id_prestacion));
            datomedi.setId_farmacia(cliente.getId_farmacia());
            List listarRecetas = this.mi.getListarMedPaquete(datomedi);
            modelo.put("listarRecetasPres", listarRecetas);
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
        if (datosconsultorio.getId_cargo() == 33) {
            prestac.setId_persona(datosHistorial.getId_persona());
            List listPrestacionCot = this.mi.getListarPrestacionesCot(prestac);
            modelo.put("listarPrestacionesCot", listPrestacionCot);
        } else {
            prestac.setId_persona(Integer.parseInt(id_persona));
            List listPrestacionCot = this.mi.getListarPrestacionesCot(prestac);
            modelo.put("listarPrestacionesCot", listPrestacionCot);
        }

        prestac.setId_historial(Integer.parseInt(id_reservacion));
        prestac.setCod_esta(cliente.getCod_esta());
        List listarRecetas = this.mi.getListarSumiRecetasI(prestac);
        modelo.put("listarRecetas", listarRecetas);

        Recetas datore = new Recetas();
        datore.setId_historial(Integer.parseInt(id_reservacion));
        datore.setCod_esta(cliente.getCod_esta());
        datore.setId_farmacia(cliente.getId_farmacia());
        datore.setId_estado("%");
        List listarRecetaSumi = this.mi.getListarRecetas(datore);
        modelo.put("listarRecetaSumi", listarRecetaSumi);

        datore.setId_estado("C");   //////getListarRecetasCaros
        List listarRecetaCaro = this.mi.getListarRecetasCaros(datore);
        if (listarRecetaCaro.size() > 0) {
            modelo.put("restrig", "si");
        }
        modelo.put("listarRecetaCaro", listarRecetaCaro);
        datore.setId_estado("%");

        Medicamentos medid = new Medicamentos();
        medid.setCod_esta(cliente.getCod_esta());
        List listarprog = this.mi.getListarProgramas(medid);
        modelo.put("listarProg", listarprog);

        modelo.put("nombres", nombres);
        modelo.put("nombresPres", nombresPres);
        modelo.put("nombremed", nombremed);
        modelo.put("id_paciente", id_paciente);
        modelo.put("id_reservacion", id_reservacion);
        modelo.put("id_historia", id_historiax);
        modelo.put("id_consultorio", id_consultorio);
        modelo.put("id_persona", id_persona);
        modelo.put("id_pedido", id_pedido);
        modelo.put("expedido", expedido);
        modelo.put("medica", medica);
        modelo.put("swx", swx);
        return new ModelAndView("administrarhistoriales/RecetarInternado", modelo);
    }
}
