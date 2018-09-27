package org.ayaic.web.administrarcobranza;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Departamentos;
import org.ayaic.domain.Detalle;
import org.ayaic.domain.Localidades;
import org.ayaic.domain.Medicamentos;
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.Recetas;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CobrarFarmaciaControlador {
    
    private final MiFacade mi;
    
    public CobrarFarmaciaControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/CobrarFarmacia.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String precio_total = request.getParameter("precio_total");
        String accion = request.getParameter("accion");
        String accion1 = request.getParameter("accion1");
        String suma1est = request.getParameter("suma1est");
        String accionrec = request.getParameter("accionrec");
        String id_pedido = request.getParameter("id_pedido");
        String id_pedidoprof = request.getParameter("id_pedido");
        String id_persona = request.getParameter("id_persona");
        String nombres = request.getParameter("nombres");
        String ciucla = request.getParameter("ciucla");
        String nombres2 = request.getParameter("nombres2");
        String valor_1 = request.getParameter("valor_1");
        String ip = request.getRemoteAddr();
        String host = request.getRemoteHost();
        Date Fecha1 = new Date();
        Date fecha1 = new Date();

        Localidades local = new Localidades();
        List Estab1 = this.mi.getListarEsta(local);
        Localidades datoestab = (Localidades) Estab1.get(0);
        local.setCod_esta(cliente.getCod_esta());
        List Estab2 = this.mi.getListarEstaUsua(local);
        Localidades datoestab2 = (Localidades) Estab2.get(0);
        modelo.put("dato", cliente);

        if (valor_1 == null) {
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

        Pacientes paciente = new Pacientes();
        paciente.setId_pedido(Integer.parseInt(id_pedido));
        paciente.setCod_esta(cliente.getCod_esta());
        Pacientes buscarPaciente = this.mi.getDatosPedidoProf(paciente); // saca un registro a ser modificado
                      
        if("1".equals(suma1est)){
            buscarPaciente = this.mi.getDatosPedido(paciente); // saca un registro a ser modificado 
        }

        modelo.put("datos", buscarPaciente);
        modelo.put("suma1est", suma1est);

        // recupera los medicamentos del paciente a entregar  
        Recetas midato = new Recetas();
        midato.setId_pedido(Integer.parseInt(id_pedido));
        midato.setCod_esta(cliente.getCod_esta());
        midato.setId_farmacia(cliente.getId_farmacia());
        //List listarKardexant = this.mi.getListarKardexFactura(midato);///borrado 02/07/2018
        //if (!listarKardexant.isEmpty()){ ////saca la farmacia del pedido
        //    Recetas lkardex = (Recetas) listarKardexant.get(0);
        //    midato.setId_farmacia(lkardex.getId_farmacia());
        //    midato.setExpedido("A");
        //}
        List listarKardex = this.mi.getListarKardexProf(midato);///getListarKardex lo cambie 27-05-2018
        if("1".equals(suma1est) ){
            listarKardex = this.mi.getListarKardex(midato);///getListarKardex lo cambie 7-08-2018 para prepagos
        }
        modelo.put("listarKardex", listarKardex);

        modelo.put("num_cladoc", Long.toString(this.mi.getNumClaDoc(paciente)));
        modelo.put("accion", accion);
        modelo.put("id_pedido", id_pedido);
        id_pedidoprof=id_pedido;
        modelo.put("id_persona", id_persona);
        
        if ("VerFacturaRec".equals(accion) || "VerFacturaRecPed".equals(accion) ){
                String sumatot1 = request.getParameter("precio");
                
                Departamentos buscardepartamento = this.mi.getDatosDepartamento(datoestab2.getId_departamento()); // saca un registro a ser modificado
                modelo.put("departamento", buscardepartamento);
                
                if ("VerFacturaRecPed".equals(accion) ){
                    
                    buscarPaciente = this.mi.getDatosPedido(paciente); // saca un registro a ser modificado
                    modelo.put("datos", buscarPaciente);
                    listarKardex = this.mi.getListarKardex(midato);///getListarKardex lo cambie 27-05-2018
                    modelo.put("listarKardex", listarKardex);
                }
                
                modelo.put("costo", listarKardex);
                modelo.put("pedido", buscarPaciente);
                modelo.put("rubrocosto", sumatot1);
                modelo.put("nombreusuario", nombres);
                modelo.put("ciu", "0");
                modelo.put("nit", "0");
                modelo.put("codigo", "0");
                modelo.put("estab", Estab2);
                return new ModelAndView(new Recibo2PDF(), modelo); /////Recibo farmacia
        }
        
        if ("TerminarCobro".equals(accion)){
            // almacenar el monto de cobro
            String num_recibo = request.getParameter("num_recibo");
            String id_paciente = request.getParameter("id_paciente");
            String id_historial = request.getParameter("id_historial");
            if (Integer.parseInt(num_recibo) >= 0) {
            } else {
                return new ModelAndView("Aviso", "mensaje", "NO ES UN NUMERO VALIDO");
            }
            // borramos de la lista de parcientes en espera
            buscarPaciente.setId_estado("E");
            buscarPaciente.setNum_cladoc(num_recibo);
            buscarPaciente.setFecha_ini(Fecha1);
            buscarPaciente.setId_persona(cliente.getId_persona());

            Detalle detalle = new Detalle();
            detalle.setEntrada(Double.parseDouble(precio_total));
            detalle.setSalida(0);
            detalle.setId_costo(1);
            detalle.setId_pedido(Integer.parseInt(id_pedido));
            detalle.setUlt_usuario(cliente.getId_persona());
            detalle.setCod_esta(cliente.getCod_esta());
            try {
                this.mi.setModificarPedidoAnt(buscarPaciente);////creo no jala con pedidos
                //this.mi.setCrearDetalle(detalle) ;
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "La accion no se cumplio, verifique los datos");
            }

            return new ModelAndView("administrarcobranza/ReciboCobroFarmacia", modelo);
        }

        if ("TerminarFar".equals(accion) ||  "Terminar".equals(accion) || "Cobrar Items".equals(accion) || "Pre Pago".equals(accion) ) {////solo se verifica para farmacia, ver si es para detalles mas
            String sumatot1 = request.getParameter("precio");
            String num_recibo = request.getParameter("num_recibo");
            String swe = request.getParameter("swe");
            String ciu = request.getParameter("ciu");
            int swc = 0;
            
            if("P".equals(buscarPaciente.getId_estado())){
                return new ModelAndView("Aviso", "mensaje", "Esta Orden ya fue cobrada");
            }
            
            if("".equals(num_recibo) || num_recibo==null ) { 
                return new ModelAndView("Aviso", "mensaje", "NO ES UN NUMERO VALIDO");
            }
            
            if("Cobrar Items".equals(accion) || "Pre Pago".equals(accion) ) {
                paciente.setNombre("SN");
                paciente.setNombres(nombres);
                paciente.setId_tipo_far(0);////solo venta
                //paciente.setNum_cladoc(Long.toString(this.mi.getNumClaDoc(paciente)) );
                paciente.setNum_cladoc(buscarPaciente.getNum_cladoc());
                if(!"".equals(num_recibo)){////25-09-2018 para actualizar numero de factura o de comprobante
                   paciente.setNum_cladoc(num_recibo); 
                }
                paciente.setId_carpeta(buscarPaciente.getId_carpeta());
                paciente.setId_pais(buscarPaciente.getId_pais());  ////inter
                paciente.setId_paciente(buscarPaciente.getId_paciente());
                paciente.setNit(buscarPaciente.getNit()) ;
                paciente.setPrecio_total(buscarPaciente.getPrecio_total()) ;
                paciente.setId_persona(cliente.getId_persona()) ;
                paciente.setId_dispensa(buscarPaciente.getId_dispensa()) ; 
                paciente.setId_proveedor(Integer.parseInt(id_pedido));
                paciente.setTipo("B");
                paciente.setId_rubro(1);
                paciente.setId_estado("C");
                if("Pre Pago".equals(accion) ){
                   paciente.setId_estado("A"); 
                }
                paciente.setFec_registro(Fecha1);  ///cambia 7/08/2018 para que haya prepagos y descargue kardex
                //paciente.setFec_registro(buscarPaciente.getFec_registro());///elimina 13/08/2018  debe ser la fecha que se registra el pago
                paciente.setCod_esta(cliente.getCod_esta());
                paciente.setCadena1("E");
                paciente.setCadena2("00");
                paciente.setCadena3("00");
                paciente.setCadena4("00");
                paciente.setCadena5("00");
                paciente.setCadena6("00");
                paciente.setCadena7("00");
                paciente.setCadena8("0");
                paciente.setCadena10("P");////esta variable para poder cambiar el pedidoprof a P=pagado
                if(!"1".equals(suma1est) ){
                   this.mi.setCrearPeedido(paciente) ;   
                   
                  id_pedido= Integer.toString(this.mi.getNumPedido(paciente));

                  for (int i = 0; i < listarKardex.size(); i++) { ////ES EL KARDEX DE KARDEXPROF
                    Recetas datoReceta = (Recetas) listarKardex.get(i);

                    Medicamentos medic = new Medicamentos();
                    medic.setId_medicamento(datoReceta.getId_medicamento());
                    medic.setCod_esta(cliente.getCod_esta());
                    medic.setId_farmacia(cliente.getId_farmacia());
                    Medicamentos buscarMedicamento = this.mi.getDatosMedicamentoB(medic);
                    swe = "SI";

                    if (buscarMedicamento.getStock() < datoReceta.getSalida() && datoestab.getFar_sinstock() == 0) {
                        if (datoestab.getCod_esta() == 200010) {
                            swe = "NO";
                            swc = swc + 1;
                        }
                    }

                    if ("SI".equals(swe)) {

                        Recetas datoK = new Recetas();
                        datoK.setId_pedido(Integer.parseInt(id_pedido));
                        datoK.setId_medicamento(datoReceta.getId_medicamento());
                        datoK.setSalida(datoReceta.getSalida());
                        datoK.setEntrada(0);
                        datoK.setExpedido(datoReceta.getExpedido());
                        datoK.setId_receta(datoReceta.getId_factura());
                        datoK.setNumeracion(datoReceta.getNumeracion());
                        datoK.setNro_lote(buscarMedicamento.getNro_lote());
                        datoK.setFecha_ven(buscarMedicamento.getFecha_ven());
                            datoK.setCosto_unit(datoReceta.getCosto_unit()); ///estaba buscarMedicamento
                            datoK.setPrecio_venta(datoReceta.getPrecio_venta());  ///estaba buscarMedicamento pero si habian cmabiado a mano precio venta colocaba el precio de la tabla medica_select soin contemplar el precio a mano
                            datoK.setExpedido("V");
                            datoK.setId_programa(0);
                        datoK.setFecha(Fecha1);///cambia 7/08/2018 para que haya prepagos y descargue kardex
                        //datoK.setFecha(buscarPaciente.getFec_registro());///elimina 13/08/2018 se debe regisytrar la fecha que se hizo pago
                        datoK.setId_farmacia(cliente.getId_farmacia());
                        datoK.setId_persona(buscarPaciente.getId_dispensa());
                        datoK.setId_factura(0);
                        datoK.setId_historial(datoReceta.getId_historial());
                        datoK.setId_historia(datoReceta.getId_historia());
                        datoK.setId_medico(cliente.getId_persona());
                        datoK.setSaldos(0);
                        datoK.setDosifica(datoReceta.getDosifica());
                        datoK.setFecha_ini(datoReceta.getFecha()); ////si es de otro dia lo folia con fecha de hoy
                        datoK.setCadena1(ip);
                        datoK.setCadena2(host);
                        datoK.setCod_esta(cliente.getCod_esta());
                        int iResultado = this.mi.setRegistrarKardex(datoK);
                    }
                }//////////*******fin if(!"1".equals(suma1est) ){
                }else{
                   paciente.setId_estado("C"); 
                   paciente.setFecha_ini(Fecha1);
                   this.mi.setModificarPedidoAnt(paciente) ;   
                }
                     


               Pacientes dato = new Pacientes();////cra para facturas y para pedidos
//             if (cliente.getCod_esta() != 400010) {
//                 ////crea dato en facturas
//                 dato.setNit_fact(Long.parseLong(ciu));
//                 dato.setNombre(nombres.toUpperCase());
//                 dato.setNum_fact(datoestab2.getNum_fact());////para la tabla facturas
//                 dato.setNum_auto(Long.parseLong(datoestab2.getNum_auto().trim()));
//                 dato.setFec_registro(Fecha1);
//                 dato.setImporte(Double.parseDouble(sumatot1));
//                 dato.setIce(0);
//                 dato.setIme(0);
//                 dato.setPrecio_total(Double.parseDouble(sumatot1));
//                 dato.setTotal(Double.parseDouble(sumatot1) * 0.13);
//                 dato.setId_estado("V");
//                 dato.setCarnet(codigo);
//                 dato.setId_pedido(Integer.parseInt(id_pedido));
//                 dato.setId_persona(Integer.parseInt(id_persona));
//                 dato.setCod_esta(cliente.getCod_esta());
//                 dato.setId_carpeta(0);
//                 this.mi.setCrearFactura(dato);
//             }

                midato.setId_pedido(Integer.parseInt(id_pedido));
                midato.setCod_esta(cliente.getCod_esta());
                midato.setId_farmacia(cliente.getId_farmacia());

                List listarKardexi = this.mi.getListarKardex(midato);   
                if (listarKardexi.size() > 16) {
                    modelo.put("taman", "mucho");
                }
                Pacientes pedido = this.mi.getDatosPedido(paciente);
                paciente.setId_pedido(Integer.parseInt(id_pedido));
                paciente.setCod_esta(cliente.getCod_esta());
                pedido = this.mi.getDatosPedido(paciente); //saca nuevamente pedidos con datos num_cladoc actualizados
                modelo.put("costo", listarKardexi);  ////Manda el detalle de medicamentos
                modelo.put("pedido", pedido);
                modelo.put("rubrocosto", sumatot1);

                Departamentos buscardepartamento = this.mi.getDatosDepartamento(datoestab2.getId_departamento()); // saca un registro a ser modificado
                modelo.put("departamento", buscardepartamento);
                if("Pre Pago".equals(accion) ) {
                   return new ModelAndView("Aviso", "mensaje", "Se descargo al kardex, pero aun no se ha efectivizado el Pago"); 
                }
                return new ModelAndView("Aviso", "mensaje", "Cobrado exitosamente");
            }
            // borramos de la lista de parcientes en espera
            buscarPaciente.setId_estado("C");
            buscarPaciente.setNum_cladoc(num_recibo);
            buscarPaciente.setFecha_ini(Fecha1);
            buscarPaciente.setId_persona(cliente.getId_persona());
            Detalle detalle = new Detalle();
            detalle.setEntrada(Double.parseDouble(precio_total));
            detalle.setSalida(0);
            detalle.setId_costo(1);
            detalle.setId_pedido(Integer.parseInt(id_pedido));
            detalle.setUlt_usuario(cliente.getId_persona());
            try {
                this.mi.setModificarPedidoAnt(buscarPaciente);////creo no jala con pedidos
                //this.mi.setCrearDetalle(detalle) ;
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "La accion no se cumplio, verifique los datos");
            }
            if (datoestab2.getId_pais() == 1) {
                String nit = request.getParameter("nit");
                //String sumatot1 = request.getParameter("precio");

                Departamentos buscardepartamento = this.mi.getDatosDepartamento(datoestab2.getId_departamento()); // saca un registro a ser modificado
                modelo.put("departamento", buscardepartamento);

                modelo.put("pedido", buscarPaciente);
                modelo.put("costo", listarKardex);
                modelo.put("rubrocosto", sumatot1);
                modelo.put("nombreusuario", nombres);
                modelo.put("ciu", ciu);
                modelo.put("nit", nit);
                modelo.put("codigo", "0");
                modelo.put("estab", Estab2);
                return new ModelAndView(new Recibo2PDF(), modelo);
            } else {
                return new ModelAndView("administrarcobranza/ReciboCobroFarmacia", modelo);
            }
        }

        if ("VerFactura".equals(accion) || "VerFacturaRec".equals(accion)) {
            String sumatot1 = request.getParameter("precio");

            String nit = request.getParameter("nit");
            String fech = request.getParameter("fec");
            if ("".equals(nit) || nit == null) {
                nit = "0";
            }
            modelo.put("carnet", "0");
            modelo.put("estab", Estab2);
            modelo.put("carnet", nit);
            modelo.put("precio", sumatot1);
            modelo.put("nombres", nombres);
            modelo.put("accionrec", accion);
            modelo.put("numauto", datoestab2.getNum_auto());
            modelo.put("numfact", Integer.toString(datoestab2.getNum_fact()));

            return new ModelAndView("administrarcobranza/FacturaFar", modelo);
        }

        if ("Facturar".equals(accion)) {
            String sumatot1 = request.getParameter("precio");
            String fech = request.getParameter("fec");
            String ciu = request.getParameter("ciu");

            if ("".equals(nombres) || nombres == null) {
                nombres = "S/N";
            }
            nombres = nombres.toUpperCase();
            int sumatot = (int) (Double.parseDouble(sumatot1));

            try {
                long carne = Long.parseLong(ciu);
                if (carne < 0) {
                    return new ModelAndView("Aviso", "mensaje", "Carnet Identidad NO puede ser negativo");
                }
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "Carnet Identidad NO es un Numero valido");
            }
            long na = Long.parseLong(datoestab2.getNum_auto().trim());
            long fact = datoestab2.getNum_fact();
            long nit2 = Long.parseLong(datoestab2.getNit());
            String llave = datoestab2.getLlave();

            int anio1 = fecha1.getYear() + 1900;
            int mes1 = fecha1.getMonth() + 1;
            int dia1 = fecha1.getDate();
            String anio11 = Integer.toString(anio1);
            String mes11 = Integer.toString(mes1);
            String dia11 = Integer.toString(dia1);;
            if (mes1 < 10) {
                mes11 = "0" + mes11;
            }
            if (dia1 < 10) {
                dia11 = "0" + dia11;
            }
            String ff = anio11 + mes11 + dia11;

            long fec = Long.parseLong(ff);
            int total = (int) (Math.round(((float) (Double.parseDouble(sumatot1)))));
            long carne = Long.parseLong(ciu);
            codigoControl numer22 = new codigoControl(na, fact, carne, fec, total, llave);
            String codigo = numer22.verCodigoControl();
            modelo.put("estab", Estab2);
            modelo.put("carnet", ciu);
            modelo.put("nombres", nombres);
            modelo.put("nombres2", nombres2);
            modelo.put("precio", sumatot1);
            modelo.put("numauto", datoestab2.getNum_auto());
            modelo.put("numfact", Integer.toString(datoestab2.getNum_fact()));
            modelo.put("codigo", codigo);

            return new ModelAndView("administrarcobranza/FacturaFarmacia", modelo);
        }

        if ("Impresora/Inyeccion".equals(accion)) {
            String sumatot1 = request.getParameter("precio");
            String fech = request.getParameter("fec");
            String ciu = request.getParameter("ciu");
            String swe = request.getParameter("swe");
            int sumatot = (int) (Double.parseDouble(sumatot1));
            int swc = 0;

            long na = Long.parseLong(datoestab2.getNum_auto().trim());
            long fact = datoestab2.getNum_fact();
            long nit2 = Long.parseLong(datoestab2.getNit());
            String llave = datoestab2.getLlave();

            int anio1 = fecha1.getYear() + 1900;
            int mes1 = fecha1.getMonth() + 1;
            int dia1 = fecha1.getDate();
            String anio11 = Integer.toString(anio1);
            String mes11 = Integer.toString(mes1);
            String dia11 = Integer.toString(dia1);;
            if (mes1 < 10) {
                mes11 = "0" + mes11;
            }if (dia1 < 10) {
                dia11 = "0" + dia11;
            }

            long fec = Long.parseLong(anio11 + mes11 + dia11);
            int total = (int) (Math.round(((float) (Double.parseDouble(sumatot1)))));
            long carne = Long.parseLong(ciu);
            codigoControl numer22 = new codigoControl(na, fact, carne, fec, total, llave);
            String codigo = numer22.verCodigoControl();
            modelo.put("estab", Estab2);
            modelo.put("carnet", ciu);
            modelo.put("nombrepac", nombres2);
            modelo.put("numauto", datoestab2.getNum_auto());
            modelo.put("numfact", Integer.toString(datoestab2.getNum_fact()));
            modelo.put("codigo", codigo);

            //Pacientes paciente= new Pacientes() ;
            paciente.setId_pedido(Integer.parseInt(id_pedido));
            paciente.setCod_esta(cliente.getCod_esta());
            Pacientes pedido = this.mi.getDatosPedidoProf(paciente);

            if (pedido.getId_tipo_far()> 0) {
                return new ModelAndView("Aviso", "mensaje", "Esta Factura Ya fue Impresa");
            }

            modelo.put("rubro", "1");
            modelo.put("rubrocosto", Double.toString(pedido.getPrecio_total()));
            modelo.put("nombreusuario", nombres.toUpperCase());
            modelo.put("ciu", ciu);
            modelo.put("nit", pedido.getNum_cladoc());

//            buscarPaciente.setId_estado("C");
//            if (cliente.getCod_esta() == 400010 && cliente.getId_cargo() == 33) {
//                buscarPaciente.setId_estado("A");
//            }
//
//            buscarPaciente.setNum_cladoc(pedido.getNum_cladoc()); /////////////
//            buscarPaciente.setFecha_ini(Fecha1);
//            buscarPaciente.setId_persona(cliente.getId_persona());
              
            if (cliente.getCod_esta() != 400010) {

               paciente.setNombre("SN");
               paciente.setNombres(nombres);
               paciente.setId_tipo_far(0);////solo venta
               paciente.setNum_cladoc(Long.toString(this.mi.getNumClaDoc(paciente)) );
               paciente.setId_historial(buscarPaciente.getId_historial());
               paciente.setId_pais(buscarPaciente.getId_pais());  ////inter
               paciente.setId_paciente(buscarPaciente.getId_paciente());
               paciente.setId_persona(buscarPaciente.getId_persona()) ;
               paciente.setId_dispensa(cliente.getId_persona()) ;
               paciente.setNit(buscarPaciente.getNit()) ;
               paciente.setPrecio_total(buscarPaciente.getPrecio_total()) ;
               paciente.setId_proveedor(Integer.parseInt(id_pedido));
               paciente.setTipo("B");
               paciente.setId_rubro(1);
               paciente.setId_estado("C");
               paciente.setFec_registro(Fecha1);
               paciente.setCod_esta(cliente.getCod_esta());
               paciente.setCadena1("E");
               paciente.setCadena2("00");
               paciente.setCadena3("00");
               paciente.setCadena4("00");
               paciente.setCadena5("00");
               paciente.setCadena6("00");
               paciente.setCadena7("00");
               paciente.setCadena8("0");
               this.mi.setCrearPeedido(paciente) ;        
               id_pedido= Integer.toString(this.mi.getNumPedido(paciente));

            for (int i = 0; i < listarKardex.size(); i++) { ////ES EL KARDEX DE KARDEXPROF
                Recetas datoReceta = (Recetas) listarKardex.get(i);

                Medicamentos medic = new Medicamentos();
                medic.setId_medicamento(datoReceta.getId_medicamento());
                medic.setCod_esta(cliente.getCod_esta());
                medic.setId_farmacia(cliente.getId_farmacia());
                Medicamentos buscarMedicamento = this.mi.getDatosMedicamentoB(medic);
                swe = "SI";

                if (buscarMedicamento.getStock() < datoReceta.getSalida() && datoestab.getFar_sinstock() == 0) {
                    if (datoestab.getCod_esta() == 200010) {
                        swe = "NO";
                        swc = swc + 1;
                    }
                }

                if ("SI".equals(swe)) {

                    Recetas datoK = new Recetas();
                    datoK.setId_pedido(Integer.parseInt(id_pedido));
                    datoK.setId_medicamento(datoReceta.getId_medicamento());
                    datoK.setSalida(datoReceta.getSalida());
                    datoK.setEntrada(0);
                    datoK.setExpedido(datoReceta.getExpedido());
                    datoK.setId_receta(datoReceta.getId_factura());
                    datoK.setNumeracion(datoReceta.getNumeracion());
                    datoK.setNro_lote(buscarMedicamento.getNro_lote());
                    datoK.setFecha_ven(buscarMedicamento.getFecha_ven());
                        datoK.setCosto_unit(buscarMedicamento.getCosto_unit());
                        datoK.setPrecio_venta(buscarMedicamento.getPrecio_venta());
                        datoK.setExpedido("V");
                        datoK.setId_programa(0);
                    datoK.setFecha(Fecha1);
                    datoK.setId_farmacia(cliente.getId_farmacia());
                    datoK.setId_persona(cliente.getId_persona());
                    datoK.setId_factura(0);
                    datoK.setId_historial(datoReceta.getId_historial());
                    datoK.setId_historia(datoReceta.getId_historia());
                    datoK.setId_medico(cliente.getId_persona());
                    datoK.setSaldos(0);
                    datoK.setDosifica(datoReceta.getDosifica());
                    datoK.setFecha_ini(datoReceta.getFecha()); ////si es de otro dia lo folia con fecha de hoy
                    datoK.setCadena1(ip);
                    datoK.setCadena2(host);
                    datoK.setCod_esta(cliente.getCod_esta());
                    int iResultado = this.mi.setRegistrarKardex(datoK);
                    //buscarMedicamento.setStockv(buscarMedicamento.getStockv() - datoReceta.getSalida());
                }
            }
            }
              
            Pacientes dato = new Pacientes();////cra para facturas y para pedidos
            if (cliente.getCod_esta() != 400010 && cliente.getCod_esta() != 400009) {
                ////crea dato en facturas
                dato.setNit_fact(Long.parseLong(ciu));
                dato.setNombre(nombres.toUpperCase());
                dato.setNum_fact(datoestab2.getNum_fact());////para la tabla facturas
                dato.setNum_auto(Long.parseLong(datoestab2.getNum_auto().trim()));
                dato.setFec_registro(Fecha1);
                dato.setImporte(Double.parseDouble(sumatot1));
                dato.setIce(0);
                dato.setIme(0);
                dato.setPrecio_total(Double.parseDouble(sumatot1));
                dato.setTotal(Double.parseDouble(sumatot1) * 0.13);
                dato.setId_estado("V");
                dato.setCarnet(codigo);
                dato.setId_pedido(Integer.parseInt(id_pedido));
                dato.setId_persona(Integer.parseInt(id_persona));
                dato.setId_vigencia(Integer.parseInt(id_pedidoprof));
                dato.setCod_esta(cliente.getCod_esta());
                dato.setId_carpeta(0);
                this.mi.setCrearFactura(dato);
            }
            
            //Recetas midato = new Recetas();
            midato.setId_pedido(Integer.parseInt(id_pedido));
            midato.setCod_esta(cliente.getCod_esta());
            midato.setId_farmacia(cliente.getId_farmacia());
//            List listarKardexant = this.mi.getListarKardexFactura(midato);
//            if(!listarKardexant.isEmpty()){ ////saca la farmacia del pedido
//                 Recetas lkardex = (Recetas) listarKardexant.get(0); 
//                 midato.setId_farmacia(lkardex.getId_farmacia());
//                 midato.setExpedido("A");
//             }    
            List listarKardexi = this.mi.getListarKardex(midato);   
            if (listarKardexi.size() > 16) {
                modelo.put("taman", "mucho");
            }
            //Pacientes pedido = this.mi.getDatosPedido(paciente);
            paciente.setId_pedido(Integer.parseInt(id_pedido));
            paciente.setCod_esta(cliente.getCod_esta());
            pedido = this.mi.getDatosPedido(paciente); //saca nuevamente pedidos con datos num_cladoc actualizados
            modelo.put("costo", listarKardexi);  ////Manda el detalle de medicamentos
            modelo.put("pedido", pedido);
            modelo.put("rubrocosto", sumatot1);

            Departamentos buscardepartamento = this.mi.getDatosDepartamento(datoestab2.getId_departamento()); // saca un registro a ser modificado
            modelo.put("departamento", buscardepartamento);
            if (cliente.getCod_esta() == 400010 || cliente.getCod_esta() == 400009) {
                List listarKardexip = this.mi.getListarKardexProf(midato);   
                if (listarKardexip.size() > 16){
                    modelo.put("taman", "mucho");
                }
                pedido = this.mi.getDatosPedidoProf(paciente); //saca nuevamente pedidos con datos num_cladoc actualizados
                modelo.put("costo", listarKardexip);  ////Manda el detalle de medicamentos
                modelo.put("pedido", pedido);
                return new ModelAndView(new Recibo2PDF(), modelo); /////Recibo farmacia
            }
            return new ModelAndView(new Factura2PDF(), modelo);
        }

        return new ModelAndView("administrarcobranza/CobrarFarmacia", modelo);

    }
}
