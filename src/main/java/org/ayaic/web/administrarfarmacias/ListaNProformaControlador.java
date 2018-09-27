package org.ayaic.web.administrarfarmacias;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Personas;
import org.ayaic.domain.Medicamentos;
import org.ayaic.domain.Consultorios;
import org.ayaic.domain.Cuadernos;
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.Recetas;
import org.ayaic.domain.Localidades;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ListaNProformaControlador {
  
    private final MiFacade mi;

    public ListaNProformaControlador(MiFacade mi) {
        this.mi = mi;
    }

   @RequestMapping("/ListaNProforma.do")
  public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response){
    Map modelo = new HashMap();
    Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
    String accion          = request.getParameter("accion");
    String accionb         = request.getParameter("accionb");
    String id_pedido       = request.getParameter("id_pedido"); 
    String id_persona      = request.getParameter("id_persona"); 
    String id_personafar   = request.getParameter("id_personafar");
    String id_farmacia2    = request.getParameter("id_farmacia2");
    String id_medicamento  = request.getParameter("id_medicamento");    
    String nombremed       = request.getParameter("nombremed");
    String id_riesgo      = request.getParameter("id_riesgo");
    String sig_centro      = request.getParameter("sig_centro");
    String servicio        = request.getParameter("servicio");
    String id_tipointer    = request.getParameter("id_tipointer");
    String medico          = request.getParameter("medico");
    String tipo            = request.getParameter("tipo");
    String sw              = request.getParameter("sw");
    String swb              = request.getParameter("swb");
    String expedido        = request.getParameter("expedido");
    String folio           = request.getParameter("folio");
    String valor_1         = request.getParameter("valor_1");
    String id_consultorio  = request.getParameter("id_consultorio");
    String diaq1 =   request.getParameter("mesq1");
    String mesq1 =   request.getParameter("mesq1");
    String[] dias = {"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
    String[] meses = {"01","02","03","04","05","06","07","08","09","10","11","12"};
    Date fecha1 = new Date ();
    int anioq =fecha1.getYear()+ 1900;
    String[] anios = {Integer.toString(anioq),Integer.toString(anioq-1),Integer.toString(anioq-2),Integer.toString(anioq-3),Integer.toString(anioq-4),Integer.toString(anioq-5),Integer.toString(anioq-6),Integer.toString(anioq-7),Integer.toString(anioq-8),Integer.toString(anioq-9),Integer.toString(anioq-10)};
    String ip = request.getRemoteAddr();
    String host = request.getRemoteHost();
    Date Fecha1 = new Date();
    double total=0; 
    
    Localidades local = new Localidades();
    List Estab1 = this.mi.getListarEsta(local);
    Localidades datoestab = (Localidades) Estab1.get(0);
    modelo.put("cod_esta",Integer.toString(datoestab.getCod_esta()));
    modelo.put("seguro_estab", Integer.toString(cliente.getInst_id()));
    modelo.put("id_personafar", id_personafar);
    modelo.put("id_farmacia2",id_farmacia2); 
    modelo.put("id_tipointer", id_tipointer);
    modelo.put("nombremed", nombremed);
    modelo.put("id_riesgo",id_riesgo); 
    modelo.put("anios",anios);
    modelo.put("meses", meses);
    modelo.put("dias", dias);
    modelo.put("anio", Integer.toString(fecha1.getYear() + 1900));
    if(fecha1.getMonth() + 1<10)  mesq1="0"+Integer.toString(fecha1.getMonth() + 1);
    else        mesq1=Integer.toString(fecha1.getMonth() + 1);
    
    if(fecha1.getDate()<10)   diaq1="0"+Integer.toString(fecha1.getDate());
    else        diaq1=Integer.toString(fecha1.getDate());
    modelo.put("mes", mesq1);
    modelo.put("dia", diaq1);
    modelo.put("folio",folio);
    modelo.put("swb", swb);
    modelo.put("sw", sw);
    
    Medicamentos datom = new Medicamentos();
    datom.setCod_esta(cliente.getCod_esta());  
    datom.setId_farmacia(cliente.getId_farmacia());  
    datom.setExpedido("I") ;    /////getDatosItem
    Medicamentos datoItem = this.mi.getDatosItem(datom) ;    
    modelo.put("datoItem",datoItem);
    
    if(id_persona==null || "".equals(id_persona) ){
        id_persona="0";
    }if(id_consultorio==null || "".equals(id_consultorio) ){
        id_consultorio="0";
    }if(expedido==null || "".equals(expedido) ){
             expedido="P";
             modelo.put("id_programa", "13");  
    }
    
    if (valor_1==null || "".equals(valor_1) ){
         valor_1 = (Integer.toString(Fecha1.getYear()+1900))+"-"+(Fecha1.getMonth()+1)+"-"+(Fecha1.getDate());
    }else{
         String[] Fechaini = valor_1.split("-");
         int iAnio1 = Integer.parseInt(Fechaini[0]) - 1900;
         int iMes1 = Integer.parseInt(Fechaini[1]) -1;
         int iDia1 = Integer.parseInt(Fechaini[2]);
         int ihora1 = Fecha1.getHours();
         int iminuto1 = Fecha1.getMinutes();
         int isegundo1 = Fecha1.getSeconds();
         Fecha1 = new Date(iAnio1, iMes1, iDia1,ihora1,iminuto1,isegundo1);
    }
    
    Personas persona = this.mi.getBuscarPersona(cliente.getId_persona()) ;
    Consultorios datosconsulta = this.mi.getDatosConsultorio(persona.getId_consultorio()) ; // saca un registro a ser modificado
    modelo.put("tipo_medico", Integer.toString(datosconsulta.getId_cargo()));

    
   if(!"0".equals(id_consultorio) && !"XXX".equals(accionb) ) {
        String nombres            = request.getParameter("nombres");

        Consultorios a= new Consultorios() ;
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
        return new ModelAndView("administrarfarmacias/EntregaNPaciente",modelo);
    }
    
    if ("ImprimirOrden".equals(accion)) {
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
   
    if("Imprimir".equals(accion) || "TerminarCobro".equals(accion) || "TerminarCobroP".equals(accion) || "prepago".equals(accion) ) {
        String id_pedidos    = request.getParameter("id_pedido");
        String swe = request.getParameter("swe"); 
        
        Recetas midato = new Recetas();
        midato.setId_pedido(Integer.parseInt(id_pedido));
        midato.setCod_esta(cliente.getCod_esta());
        midato.setId_farmacia(cliente.getId_farmacia());
        List listarKardex = this.mi.getListarKardexProf(midato);
        modelo.put("listarKardex", listarKardex);
        
        Pacientes paciente1= new Pacientes() ;
        paciente1.setId_pedido(Integer.parseInt(id_pedido));
        paciente1.setCod_esta(cliente.getCod_esta());
        Pacientes paciente = this.mi.getDatosPedidoProf(paciente1) ; //////////////
        modelo.put("datos", paciente);     
        modelo.put("dato", cliente);
        
        if("prepago".equals(accion) ) {
           paciente1.setNombres(paciente.getNombres());
           paciente1.setPrecio_total(paciente.getPrecio_total());
           paciente1.setNum_cladoc(Long.toString(this.mi.getNumClaDoc(paciente)) );
           paciente1.setId_persona(cliente.getId_persona());
           paciente1.setId_dispensa(cliente.getId_persona());
           paciente1.setId_paciente(paciente.getId_paciente());
           paciente.setId_factura(this.mi.getNumReceta(paciente));
           paciente.setId_pais(paciente.getId_pedido());
           paciente1.setNit(paciente.getNit());
           paciente1.setId_estado("C");
           paciente1.setId_tipo_far(0); 
           paciente1.setId_carpeta(0);
           paciente1.setId_rubro(1);
           paciente1.setTipo("B");  
           paciente1.setCadena1("E");  
           paciente1.setCadena2("00");
           paciente1.setCadena3("000");
           paciente1.setCadena4("00");
           paciente1.setCadena5("00");
           paciente1.setCadena6("00");
           paciente1.setCadena7("00");
           paciente1.setCadena8("00");
           paciente1.setFec_registro(fecha1);
           paciente1.setCod_esta(cliente.getCod_esta());
           this.mi.setCrearPeedido(paciente1) ;        
           id_pedido= Integer.toString(this.mi.getNumPedido(paciente));
           
           for (int i = 0; i < listarKardex.size(); i++) {
            Recetas datoReceta = (Recetas) listarKardex.get(i);
            
            Medicamentos medic = new Medicamentos();
            medic.setId_medicamento(datoReceta.getId_medicamento());
            medic.setCodigo(cliente.getCod_esta()) ;
            medic.setCod_esta(cliente.getCod_esta()) ;
            medic.setId_farmacia(cliente.getId_farmacia()) ;
            medic.setExpedido("B") ;    /////getDatosMedicamentoB
            Medicamentos buscarMedicamento = this.mi.getDatosMedicamentoB(medic) ;  
            swe="SI";
            
            if(buscarMedicamento.getStock()< datoReceta.getSalida() && datoestab.getFar_sinstock()==0){
                  swe="NO"; 
                  //swc=swc+1; 
           } 
           
             if ("SI".equals(swe)) {
                
                Recetas dato = new Recetas();
                dato.setId_pedido(Integer.parseInt(id_pedido)) ;
                dato.setId_medicamento(datoReceta.getId_medicamento());
                dato.setSalida(datoReceta.getSalida());
                dato.setEntrada(0);
                dato.setCosto_unit(datoReceta.getCosto_unit());
                dato.setExpedido(datoReceta.getExpedido());
                dato.setId_receta(datoReceta.getId_factura()) ; 
                dato.setNumeracion(datoReceta.getNumeracion());
                dato.setNro_lote(datoReceta.getNro_lote());
                dato.setFecha_ven(datoReceta.getFecha_ven());
                dato.setId_programa(0);
                    buscarMedicamento.setStockv(buscarMedicamento.getStockv()-dato.getSalida());   
                    dato.setPrecio_venta(datoReceta.getPrecio_venta());
                    dato.setExpedido("V");
                dato.setFecha(Fecha1) ; 
                
                dato.setId_farmacia(cliente.getId_farmacia()) ;
                dato.setId_persona(cliente.getId_persona()) ; 
                dato.setId_factura(0) ; 
                dato.setId_historial(datoReceta.getId_historial()) ; 
                dato.setId_historia(datoReceta.getId_factura()) ; 
                dato.setId_medico(0);
                dato.setSaldos(0);
                dato.setDosifica(datoReceta.getDosifica());
                dato.setFecha_ini(datoReceta.getFecha()); 
                dato.setCadena1(ip);
                dato.setCadena2(host);
                dato.setCod_esta(cliente.getCod_esta()) ;
                int iResultado = this.mi.setRegistrarKardex(dato);

            }   
          } 
           
            paciente1.setId_pedido(paciente.getId_pedido());
            paciente1.setCod_esta(cliente.getCod_esta());
            Pacientes pacientep = this.mi.getDatosPedidoProf(paciente1) ; //////////////
            paciente1.setPrecio_total(pacientep.getPrecio_total());  
            paciente1.setId_estado("X"); 
            if("PROFORMA".equals(swb) ) {
               paciente1.setId_estado("B");  
            }
            this.mi.setModificarPedidoProf(paciente1);
            return new ModelAndView("Aviso","mensaje","Se registro Coorectamente");
        }
        if("TerminarCobro".equals(accion) || "TerminarCobroP".equals(accion) ) {
           if("TerminarCobroP".equals(accion) ) {
              modelo.put("swb", "PROFORMA"); 
              modelo.put("num_prof", paciente.getNum_cladoc()); 
           } 
           return new ModelAndView("administrarfarmacias/CobrarPrepago", modelo); 
        }
        return new ModelAndView(new VerProformaPDF(), modelo);
    }
    
    if ("entregarya".equals(accion)) {
       String nombres    = request.getParameter("nombres");
       String num_cladoc    = request.getParameter("num_cladoc");
       String institucion    = request.getParameter("institucion");
       String cod            = request.getParameter("cod");

       if("".equals(num_cladoc) || num_cladoc==null) {
         num_cladoc="0";  
       }if("".equals(institucion) || institucion==null) {
         institucion="SN";  
       }if(("".equals(nombres) || nombres==null) ) {
           medico="SN"; 
       }if("cod".equals(cod) || cod==null) {
         cod="0";  
       }

       long fechaInicial = Fecha1.getTime();
       long fechaFinal = fecha1.getTime();
       long diferencia = (fechaFinal - fechaInicial)+50;
       double diass = Math.floor(diferencia /(1000*60*60*24));
       if(fechaInicial>fechaFinal){
           return new ModelAndView("Aviso","mensaje","La fecha que desea ingresar NO es correcta, esta adelantada a HOY");
       }

       //Localidades localidad= new Localidades() ;
       //localidad.setArea("E");   ////getDatosEstable
       //localidad.setCod_esta(cliente.getCod_esta());
       //Localidades buscarestab = this.mi.getDatosEstable(localidad);
       
       
       Pacientes paciente = new Pacientes();
       paciente.setCod_esta(cliente.getCod_esta());
       paciente.setNombre(institucion);
       paciente.setNombres(nombres);
       paciente.setPrecio_total(0);
       paciente.setNum_cladoc("0");
       paciente.setId_tipo_far(0);////solo venta
       paciente.setNum_cladoc(Long.toString(this.mi.getNumClaDocProf(paciente)) );
       if("PROFORMA".equals(swb)) {
          paciente.setId_tipo_far(1);////proformas 
          paciente.setNum_cladoc(this.mi.getNumProforma(paciente));
       }
       paciente.setId_paciente(0);
       paciente.setNit(num_cladoc);
       paciente.setId_persona(cliente.getId_persona());
       paciente.setId_dispensa(cliente.getId_persona());
       paciente.setTipo("B");
       paciente.setId_estado("A");
       paciente.setFec_registro(fecha1);
       paciente.setCod_esta(cliente.getCod_esta());
       this.mi.setCrearProforma(paciente) ;        
       id_pedido= Integer.toString(this.mi.getNumPedidoProf(paciente));
       //sw=swb;
       //modelo.put("sw", swb);
    }
    
    if ("adicion".equals(accion)) {
           String cantidad = request.getParameter("cantidad"); 
           String saldo    = request.getParameter("saldo"); 
           String precio   = request.getParameter("precio");
           String id_programa = request.getParameter("id_programa");
           
           Pacientes paciente1= new Pacientes() ;
           paciente1.setId_pedido(Integer.parseInt(id_pedido));
           paciente1.setCod_esta(cliente.getCod_esta());
           Pacientes paciente = this.mi.getDatosPedido(paciente1) ; //////////////
           
           Medicamentos medic = new Medicamentos();
           medic.setId_medicamento(Integer.parseInt(id_medicamento)) ; 
           medic.setCodigo(cliente.getCod_esta()) ;
           medic.setCod_esta(cliente.getCod_esta()) ;
           medic.setId_farmacia(cliente.getId_farmacia()) ;
           medic.setExpedido("B") ;    /////getDatosMedicamentoB
           Medicamentos buscarMedicamento = this.mi.getDatosMedicamentoB(medic) ; 

           if(precio==null){
               precio = Double.toString(buscarMedicamento.getCosto_unit());
           }
           Recetas dato = new Recetas();
           dato.setId_pedido(Integer.parseInt(id_pedido)) ;
           dato.setId_medicamento(Integer.parseInt(id_medicamento));
           dato.setSalida(Double.parseDouble(cantidad));
           dato.setEntrada(0);
           dato.setCosto_unit(buscarMedicamento.getCosto_unit());
           dato.setPrecio_venta(buscarMedicamento.getPrecio_venta());
           if (Double.parseDouble(precio)<buscarMedicamento.getCosto_unit()) {
               dato.setPrecio_venta(buscarMedicamento.getCosto_unit());
           }else
               dato.setPrecio_venta(Double.parseDouble(precio));
           dato.setNro_lote(buscarMedicamento.getNro_lote());
           dato.setFecha_ven(buscarMedicamento.getFecha_ven());
           dato.setExpedido("V");
           dato.setId_programa(0);
           dato.setFecha(Fecha1);
           dato.setCod_esta(cliente.getCod_esta());
           dato.setId_farmacia(cliente.getId_farmacia());
           dato.setId_persona(cliente.getId_persona()) ; 
           dato.setId_factura(0) ; 
           dato.setCadena1(ip);
           dato.setCadena2(host);
           this.mi.setCrearKardexProf(dato);        
           //modelo.put("folio", Integer.toString(paciente.getId_factura()));
    }
    
    if ("eliminar".equals(accion)) {
           String cantidad = request.getParameter("salida");             
           String id_kardex  = request.getParameter("id_kardex");
           
           Recetas dato = new Recetas();
           dato.setId_pedido(Integer.parseInt(id_pedido)) ;
           dato.setId_factura(Integer.parseInt(id_kardex)) ;
           dato.setId_medicamento(Integer.parseInt(id_medicamento));           
           dato.setSalida(Double.parseDouble(cantidad));           
           dato.setCod_esta(cliente.getCod_esta()) ;
           this.mi.setEliminarKardexProf(dato);
    }
    
    if ("terminar".equals(accion)){
          Recetas midato = new Recetas();
            midato.setId_pedido(Integer.parseInt(id_pedido));
            midato.setId_prestacion(cliente.getCod_esta());
            midato.setCod_esta(cliente.getCod_esta());
            midato.setId_farmacia(cliente.getId_farmacia());
            List listarKardex = this.mi.getListarKardex(midato);
            total=0;
            for(int i=0;i<listarKardex.size();i++){
                 midato=(Recetas)listarKardex.get(i);
                 total=total+midato.getPrecio_total();
                 Fecha1=midato.getFecha();////12/06/2016 para que no cambie la fecha del pedido
            }        

          Pacientes paciente1= new Pacientes() ;
          paciente1.setId_pedido(Integer.parseInt(id_pedido));
          paciente1.setCod_esta(cliente.getCod_esta());
          Pacientes paciente = this.mi.getDatosPedido(paciente1) ; //////////////    
          //paciente.setId_estado("A");
          //paciente.setNum_cladoc(Long.toString(this.mi.getNumClaDoc(paciente)) );
         // this.mi.setModificarPedidoAnt(paciente);
              

          //paciente.setId_estado("A");
          //paciente.setId_rubro(1);
          paciente.setId_farmacia(cliente.getId_farmacia());
          paciente.setCod_esta(cliente.getCod_esta());
          List listarSinPago = this.mi.getListarProformas(paciente);
          modelo.put("listapago", listarSinPago);

          // lista de pacientes que no terminaron su receta
          paciente.setId_estado("X");
          List listarSinPago1 = this.mi.getListarCobroRubroFar(paciente);
          modelo.put("listapago1", listarSinPago1);
    
          // lista de pacientes que pagaron en seccion cobranza
          paciente.setId_estado("C");
          List listarPaises = this.mi.getListarCobroRubroFar(paciente);
          modelo.put("milista", listarPaises);
          
          if(listarPaises.size()>0){
             modelo.put("listacancelados", "1");
          }
          if(listarSinPago.size()>0 || listarSinPago1.size()>0){
             modelo.put("listaporcancel", "1");
          }

          return new ModelAndView("administrarfarmacias/ListarAtendidos", modelo);
    }
    
    Medicamentos dato = new Medicamentos();
    dato.setId_persona(cliente.getId_persona());
    dato.setCod_esta(cliente.getCod_esta());
    dato.setId_farmacia(cliente.getId_farmacia());   
    dato.setExpedido("V");
    modelo.put("expedido", "E");
    List listarMedicamentosCot = this.mi.getListarMedicamentosCot(dato);
    modelo.put("listarMedicamentosCot", listarMedicamentosCot);
    
    if(nombremed != null){
            nombremed = nombremed.trim();
            nombremed = nombremed.replaceAll(" +", " ");
            modelo.put("nombremed", nombremed);
            nombremed = nombremed.replaceAll("\\s", ":*&");
            nombremed = nombremed.replaceAll("�", "n");
            nombremed = nombremed.replaceAll("�", "N");
            if(nombremed.length() > 0)
                nombremed = nombremed+":*";
    }else{
        nombremed = "";
    }
    dato.setCod_esta(cliente.getCod_esta()); 
    dato.setMedicamento(nombremed);
    dato.setId_farmacia(cliente.getId_farmacia());
    if("".equals(nombremed)){
        List listarMedicamentos = mi.getListarMedicamentosVacio(dato);
        modelo.put("listarMedicamentos", listarMedicamentos);
    }else{
        List listarMedicamentos = this.mi.getListarMedicamentos(dato);
        modelo.put("listarMedicamentos", listarMedicamentos);
    }
    
    
    Recetas midato = new Recetas();
    midato.setId_pedido(Integer.parseInt(id_pedido));
    midato.setCod_esta(cliente.getCod_esta());
    midato.setId_farmacia(cliente.getId_farmacia());
    List listarKardex = this.mi.getListarKardexProf(midato);
    modelo.put("listarKardex", listarKardex);

    total=0;
    for(int i=0;i<listarKardex.size();i++){
         midato=(Recetas)listarKardex.get(i);
         total=total+midato.getPrecio_total();
         //Fecha1=midato.getFecha();////12/06/2016 para que no cambie la fecha del pedido
    }        
    // actualiza el precio total
    Pacientes paciente1= new Pacientes() ;
    paciente1.setId_pedido(Integer.parseInt(id_pedido));
    paciente1.setCod_esta(cliente.getCod_esta());
    Pacientes paciente = this.mi.getDatosPedidoProf(paciente1) ; //////////////saca dato de id_estado
    paciente1.setPrecio_total(total);  
    paciente1.setId_estado(paciente.getId_estado()); 
    this.mi.setModificarPedidoProf(paciente1);////modifica el datos
    paciente = this.mi.getDatosPedidoProf(paciente1) ; //////////////muestra el dato para el JSP
    modelo.put("datos", paciente);
    
    modelo.put("valor_1", valor_1);    
    modelo.put("id_pedido", id_pedido);
    modelo.put("num_prof", paciente.getNum_cladoc());
    return new ModelAndView("administrarfarmacias/ListaNProforma",modelo);
  }
}
