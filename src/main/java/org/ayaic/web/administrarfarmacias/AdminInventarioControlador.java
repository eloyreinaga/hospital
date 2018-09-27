package org.ayaic.web.administrarfarmacias;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Personas;
import org.ayaic.domain.Medicamentos;
import org.ayaic.domain.Localidades;
import org.ayaic.domain.Consultorios;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminInventarioControlador {
    
   private final MiFacade mi;
   
    public AdminInventarioControlador(MiFacade mi) {
        this.mi = mi;
    }
   
   @RequestMapping("/AdminInventario.do")
   public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response){
   Map modelo = new HashMap();
    
    Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
    String id_historial   = request.getParameter("id_historial"); 
    String id_historia    = request.getParameter("id_historia");
    String id_paciente    = request.getParameter("id_paciente");
    String id_persona     = request.getParameter("id_persona");
    String id_personafar  = request.getParameter("id_personafar");
    String cod_esta       = request.getParameter("cod_esta");
    String id_consultorio = request.getParameter("id_consultorio");
    String especialidad   = request.getParameter("especialidad");
    String sig_centro     = request.getParameter("sig_centro");
    String id_riesgo      = request.getParameter("id_riesgo");
    String servicio       = request.getParameter("servicio");
    String id_tipointer   = request.getParameter("id_tipointer");
    String id_farmacia2   = request.getParameter("id_farmacia2");
    String swb            = request.getParameter("sw");
    String accion    = request.getParameter("accion");
    String accione   = request.getParameter("accione");
    String accionest   = request.getParameter("accionest");
    String expedido   = request.getParameter("expedido");
    String diaq1 =   request.getParameter("mesq1");
    String mesq1 =   request.getParameter("mesq1");
    String[] dias = {"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
    String[] meses = {"01","02","03","04","05","06","07","08","09","10","11","12"};
    Date fecha1 = new Date ();
    int anioq =fecha1.getYear()+ 1900;
    String[] anios = {Integer.toString(anioq),Integer.toString(anioq-1),Integer.toString(anioq-2),Integer.toString(anioq-3),Integer.toString(anioq-4),Integer.toString(anioq-5),Integer.toString(anioq-6),Integer.toString(anioq-7),Integer.toString(anioq-8),Integer.toString(anioq-9),Integer.toString(anioq-10)};
    String ip = request.getRemoteAddr();
    Date Fecha1 = new Date();
    
    Localidades local = new Localidades();
    List Estab1 = this.mi.getListarEsta(local);
    Localidades datoestab = (Localidades) Estab1.get(0);
    modelo.put("factur", Integer.toString(datoestab.getId_pais()));
    modelo.put("cod_esta", Integer.toString(datoestab.getCod_esta()));
    modelo.put("codesta", Integer.toString(cliente.getCod_esta()));
    modelo.put("seguro_estab", Integer.toString(cliente.getInst_id()));
    modelo.put("id_personafar", id_personafar);
    modelo.put("id_farmacia2",id_farmacia2); 
    modelo.put("id_riesgo",id_riesgo); 
    modelo.put("localidad", Integer.toString(cliente.getCod_esta()));
    Medicamentos medid = new Medicamentos();
    medid.setCod_esta(cliente.getCod_esta());
    List listarprog = this.mi.getListarProgramas(medid);
    modelo.put("listarProg", listarprog);
    
    Medicamentos datom = new Medicamentos();
    datom.setCod_esta(cliente.getCod_esta()); 
    datom.setId_farmacia(cliente.getId_farmacia());
    datom.setId_persona(cliente.getId_persona());
    List listarGrupo = this.mi.getListarGrupos(datom);  ///getListarGrupos
    modelo.put("listarGrupo", listarGrupo); 
    datom.setExpedido("I") ;    /////getDatosItem
    Medicamentos datoItem = this.mi.getDatosItem(datom) ;    
    modelo.put("datoItem",datoItem);
    
    Consultorios aa= new Consultorios() ;
    aa.setId_especialidad(Integer.parseInt(Integer.toString(cliente.getCod_esta()).substring(0,1))); 
    aa.setCod_esta(cliente.getCod_esta()); ////se cambia cod_esta mientras se crea una tabla donde se uniran cod_esta de farmacias
    List listarCentros = this.mi.getListarCentroCNSFar(aa); ////Debe mejorarse la busqueda de los establecimientos Unidos por comun   
    modelo.put("listarCentros", listarCentros);      
    
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
    modelo.put("id_tipointer", id_tipointer);
    modelo.put("id_paciente",id_paciente); 
    modelo.put("sig_centro",sig_centro);
    modelo.put("descrip", servicio);
    
    if ("adicionar".equals(accion)){
       modelo.put("nombres","NOMBRE");       
       modelo.put("nit","NIT");              
       modelo.put("swb",request.getParameter("sw"));  
       modelo.put("num_cladoc", "0");
       return new ModelAndView("administrarfarmacias/EntregaProforma", modelo);
    }
    
    if ("Establecimiento".equals(accionest) || "especialidad".equals(accione) ){
        String tag          = request.getParameter("tag");
        
        
        modelo.put("descrip", servicio);
        modelo.put("id_consultorio", especialidad);
        modelo.put("id_paciente",id_paciente); 
        modelo.put("id_historial",id_historial);    
        modelo.put("cod_esta", cod_esta);
        modelo.put("descripcionc", "78");
        modelo.put("tiporecet", tag);
       
        return new ModelAndView("administrarfarmacias/EntregaPacienteFarCNS", modelo);
    }
    
    if ("Terminar".equals(accion)) {
       String id_pedido    = request.getParameter("id_pedido");
       // recupera el paciente a entregar
       Pacientes paciente1= new Pacientes() ;
       paciente1.setId_pedido(Integer.parseInt(id_pedido));
       paciente1.setCod_esta(cliente.getCod_esta());
       Pacientes buscarPaciente = this.mi.getDatosPedido(paciente1);
       modelo.put("datos", buscarPaciente);
       buscarPaciente.setId_estado("E");
       buscarPaciente.setFecha_fin(buscarPaciente.getFec_registro()); 
       this.mi.setModificarPedido(buscarPaciente) ; //// 
    }
    
    if ("Buscar".equals(accion) || "previafarma".equals(accion)){  
        String nombres = request.getParameter("nombres");
        String inicial = request.getParameter("inicial");
        
        if(nombres.length()>0){
            if("P".equals(nombres.substring(0,1))){
               nombres= nombres.replaceFirst("P", "");
            }
            
        }//else{
         //   return new ModelAndView("Aviso","mensaje","Minimo 2 caracteres"); 
        //}
        nombres = nombres.replaceAll("\\s", "%");
        nombres = "%" + nombres + "%"; 
        
        Pacientes paciente= new Pacientes() ;
        paciente.setId_estado("A");
        paciente.setId_rubro(1);
        paciente.setCod_esta(cliente.getCod_esta());
        paciente.setNombre(nombres);
        List listarProformas = this.mi.getListarProformasNom(paciente);
        modelo.put("listaprof", listarProformas);
        modelo.put("nombres", request.getParameter("nombres")); 
        if(listarProformas.size()>0 ){
           modelo.put("mostrarprof", "1"); 
        }
        return new ModelAndView("administrarfarmacias/AdminInventarios", modelo);
    }
    
    if ("Gral".equals(accion)){
        String id_farmacia = request.getParameter("id_farmacia"); 
        String nombres = request.getParameter("nombres");
        nombres = nombres.replaceAll("\\s", "%");
        nombres = "%" + nombres + "%"; 
        
        Personas persona = this.mi.getBuscarPersona(cliente.getId_persona()) ;
        Consultorios datosconsulta = this.mi.getDatosConsultorio(persona.getId_consultorio()) ; // saca un registro a ser modificado
        modelo.put("tipo_medico", Integer.toString(datosconsulta.getId_cargo()));
//        Historiales dato= new Historiales(); 
//        dato.setId_estado("A");
//        dato.setExpedido("%");
//        dato.setCod_esta(cliente.getCod_esta());
//        List listarAtendidos = this.mi.getListarAtendidos(dato);
//        modelo.put("listarAtendidos", listarAtendidos);

      return new ModelAndView("administrarfarmacias/ListarAtendidos", modelo);  
    }
    
    Personas persona = this.mi.getBuscarPersona(cliente.getId_persona()) ;
    Consultorios datosconsulta = this.mi.getDatosConsultorio(persona.getId_consultorio()) ; // saca un registro a ser modificado
    modelo.put("tipo_medico", Integer.toString(datosconsulta.getId_cargo()));
    modelo.put("id_farmacia", Integer.toString(persona.getId_farmacia()));

    // lista de pacientes que aun no pagaron en seccion cobranza
      Pacientes paciente= new Pacientes() ;
      paciente.setId_estado("A");
      paciente.setId_rubro(1);
      paciente.setId_farmacia(cliente.getId_farmacia());
      paciente.setCod_esta(cliente.getCod_esta());
      List listarSinPago = this.mi.getListarProformas(paciente);
      modelo.put("listapago", listarSinPago);

      // lista de pacientes que pagaron en seccion cobranza
      paciente.setId_estado("C");
      List listarPagados = this.mi.getListarCobroRubroFar(paciente);
      modelo.put("milista", listarPagados);
    
    if(listarSinPago.size()>0 ){
      modelo.put("listaporcancel", "1");
      modelo.put("mostrarpagos", "0"); 
    
      if("mostrarPagos".equals(accione)){
          modelo.put("mostrarpagos", "1");
      }   
    }
    // lista de pacientes que pagaron en seccion cobranza
    if(listarPagados.size()>0){
      modelo.put("listacancelados", "1");
    }
      
    return new ModelAndView("administrarfarmacias/AdminInventarios", modelo);     
   }
}