package org.ayaic.web.administrarcuadernos;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Cuadernos;
import org.ayaic.domain.Historiales;
import org.ayaic.domain.Localidades;
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.Sexos;
import org.ayaic.domain.Usuarios;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Cuaderno1Controlador {

    private final MiFacade mi;

    public Cuaderno1Controlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/Cuaderno1.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        Usuarios usuario = this.mi.getDatosUsuario(cliente.getId_usuario()); // saca un registro a ser modificado
        String accion = request.getParameter("accion");
        String id_paciente = request.getParameter("id_paciente");
        String id_reservacion = request.getParameter("id_reservacion");
        String id_consultorio = request.getParameter("id_consultorio");
        String id_persona = request.getParameter("id_persona");
        String expedido = request.getParameter("expedido");
        String estimc = request.getParameter("estimc");
        String tipo_medico = request.getParameter("tipo_medico");
        String enfer = request.getParameter("enfer");
        String swinter = request.getParameter("swinter");
        String valor1 = request.getParameter("valor1");
        double total = 0;

        Localidades local = new Localidades();
        List Estab1 = this.mi.getListarEsta(local);
        Localidades datol = (Localidades) Estab1.get(0);
        modelo.put("localidades", datol);
        local.setArea("R");
        local.setId_persona(1);
        List EstabRef = this.mi.getListarEstabRef(local);
        modelo.put("listarestab", EstabRef);
        local.setId_persona(2);
        List EstabCRef = this.mi.getListarEstabRef(local);
        modelo.put("listarestabC", EstabCRef);

        Pacientes buscarPaciente = this.mi.getDatosPaciente(Integer.parseInt(id_paciente)); // saca un registro a ser modificado
        modelo.put("urgencias", Integer.toString(cliente.getId_almacen()));
        modelo.put("datos", buscarPaciente);

        if ("Cuaderno1".equals(accion)) {
            Cuadernos dato1 = new Cuadernos();
            dato1.setTipo("U");
            dato1.setId_estado("%");
            dato1.setEstado("T");
            dato1.setAspecto("A");
            dato1.setCod_esta(cliente.getCod_esta());
            dato1.setId_persona(cliente.getId_persona());  ///13-02-2017
            dato1.setId_paciente(Integer.parseInt(id_paciente));
            List listaCie = this.mi.getVerCuaderno1CieUni(dato1);
            modelo.put("listaCie", listaCie);
            List listaUni = this.mi.getVerCuaderno1Uni(dato1);
            modelo.put("listaCobros", listaUni);
            return new ModelAndView(new VerCuaderno1PDF(), modelo);
        }

        if ("C.Consulta Externa".equals(accion)) {
            enfer = "Transmisible";
            modelo.put("enfer", enfer);
        }
        List C1 = this.mi.getPacienteCuaderno1(Integer.parseInt(id_reservacion));

        //Buscar Sexo
        Sexos buscarsexo = this.mi.getDatosSexo(buscarPaciente.getId_tipo_sexo()); // saca un registro a ser modificado
        modelo.put("buscarSexo", buscarsexo);

        modelo.put("id_paciente", id_paciente);
        modelo.put("id_reservacion", id_reservacion);
        modelo.put("id_consultorio", id_consultorio);
        modelo.put("id_persona", id_persona);
        modelo.put("expedido", expedido);
        modelo.put("tipo_medico", tipo_medico);
        modelo.put("swinter", swinter);
        
        Historiales datohi = new Historiales();
        datohi.setId_historial(Integer.parseInt(id_reservacion));
        datohi.setCod_esta(cliente.getCod_esta());
        Historiales datosHistorial = this.mi.getDatosHistorial(datohi);
        modelo.put("datoHisto", datosHistorial); 
                
        if ("Agregar".equals(accion)) {
            String tipo = request.getParameter("tipo");

            if (C1.isEmpty() != true) {
                return new ModelAndView("Aviso", "mensaje", "YA HA REGISTRADO UNA VEZ");
            } else {

                datosHistorial.setRepetida(tipo);
                datosHistorial.setId_historial(Integer.parseInt(id_reservacion));
                datosHistorial.setCod_esta(cliente.getCod_esta());
                datosHistorial.setId_por(cliente.getId_persona());

                int iResultado = this.mi.setRegistrarHistorial(datosHistorial);
                String id_hospital = request.getParameter("id_hospital");
                String id_hospital2 = request.getParameter("id_hospital2");
                String speso = request.getParameter("speso");
                String alcohol = request.getParameter("alcohol");
                String fuma = request.getParameter("fuma");
                String violencia = request.getParameter("violencia");
                String auto = request.getParameter("auto");
                String urinaria = request.getParameter("urinaria");
                String sistemica = request.getParameter("sistemica");
                String arterial = request.getParameter("arterial");
                String diabetes = request.getParameter("diabetes");
                String glome = request.getParameter("glome");
                String tracto = request.getParameter("tracto");
                String lupus = request.getParameter("lupus");
                String litiasis = request.getParameter("litiasis");
                String nooplasia = request.getParameter("nooplasia");
                String nefro = request.getParameter("nefro");
                String disli = request.getParameter("disli");
                String frenal = request.getParameter("frenal");
                String bajopeso = request.getParameter("bajopeso");
                String prematuro = request.getParameter("prematuro");
                String eritro = request.getParameter("eritro");
                String renal = request.getParameter("renal");
                String tuberculo = request.getParameter("tuberculo");
                String otro = request.getParameter("otro");
                String cardio = request.getParameter("cardio");
                String reuma = request.getParameter("reuma");
                String mortalidad = request.getParameter("mortalidad");
                String cancer = request.getParameter("cancer");
                String cancero = request.getParameter("cancero");
                String depre = request.getParameter("depre");
                String epilepsia = request.getParameter("epilepsia");
                String psico = request.getParameter("psico");
                String discapa = request.getParameter("discapa");
                String resultado = request.getParameter("resultado");
                String hba = request.getParameter("hba");
                String gfr = request.getParameter("gfr");
                String creatinina = request.getParameter("creatinina");
                String colesterol = request.getParameter("colesterol");
                String imc = request.getParameter("imc");
                String pies = request.getParameter("pies");
                String ojos = request.getParameter("ojos");
                String dieta = request.getParameter("dieta");
                String ejercicios = request.getParameter("ejercicios");
                String observa = request.getParameter("observa");
                String id_refer = request.getParameter("id_refer");
                String carmelo = request.getParameter("carmelo");
                String tipoegreso = request.getParameter("tipoegreso");

                if (carmelo == null || "".equals(carmelo)) {
                    carmelo = "0";
                }
                if (id_hospital == null || "".equals(id_hospital)) {
                    id_hospital = "0";
                }
                if (id_hospital2 == null || "".equals(id_hospital2)) {
                    id_hospital2 = "0";
                }

                Cuadernos dato = new Cuadernos();
                dato.setCod_esta(cliente.getCod_esta());
                dato.setId_persona(Integer.parseInt(id_persona));
                dato.setTipoconsulta(tipo);
                dato.setSuma30(Integer.parseInt(id_hospital));
                dato.setSuma31(Integer.parseInt(id_hospital2));
                dato.setSuma14(Integer.parseInt(id_refer));
                dato.setId_historial(Integer.parseInt(id_reservacion));
                dato.setReferido(request.getParameter("ref"));
                dato.setContraref(request.getParameter("cref"));
                dato.setAspecto(hba);
                dato.setBacterias(gfr);
                dato.setBilirrubina(creatinina);
                dato.setCantidad(colesterol);
                dato.setCetonas(imc);
                dato.setObserva(observa);

                if ("1".equals(speso)) {
                    dato.setSpeso(Integer.parseInt(speso));
                }
                if ("1".equals(alcohol)) {
                    dato.setAlcohol(Integer.parseInt(alcohol));
                }
                if ("1".equals(fuma)) {
                    dato.setFuma(Integer.parseInt(fuma));
                }
                if ("1".equals(violencia)) {
                    dato.setViolencia(Integer.parseInt(violencia));
                }
                if ("1".equals(auto)) {
                    dato.setAuto(Integer.parseInt(auto));
                }
                if ("1".equals(urinaria)) {
                    dato.setUrinaria(Integer.parseInt(urinaria));
                }
                if ("1".equals(sistemica)) {
                    dato.setSistemica(Integer.parseInt(sistemica));
                }
                if ("1".equals(arterial)) {
                    dato.setArterial(Integer.parseInt(arterial));
                }
                if ("1".equals(diabetes)) {
                    dato.setDiabetes(Integer.parseInt(diabetes));
                }
                if ("1".equals(glome)) {
                    dato.setGlome(Integer.parseInt(glome));
                }
                if ("1".equals(tracto)) {
                    dato.setTracto(Integer.parseInt(tracto));
                }
                if ("1".equals(lupus)) {
                    dato.setLupus(Integer.parseInt(lupus));
                }
                if ("1".equals(litiasis)) {
                    dato.setLitiasis(Integer.parseInt(litiasis));
                }
                if ("1".equals(nooplasia)) {
                    dato.setNooplasia(Integer.parseInt(nooplasia));
                }
                if ("1".equals(nefro)) {
                    dato.setNefro(Integer.parseInt(nefro));
                }
                if ("1".equals(disli)) {
                    dato.setDisli(Integer.parseInt(disli));
                }
                if ("1".equals(frenal)) {
                    dato.setFrenal(Integer.parseInt(frenal));
                }
                if ("1".equals(bajopeso)) {
                    dato.setBajopeso(Integer.parseInt(bajopeso));
                }
                if ("1".equals(prematuro)) {
                    dato.setPrematuro(Integer.parseInt(prematuro));
                }
                if ("1".equals(eritro)) {
                    dato.setEritro(Integer.parseInt(eritro));
                }
                if ("1".equals(renal)) {
                    dato.setRenal(Integer.parseInt(renal));
                }
                if ("1".equals(tuberculo)) {
                    dato.setTuberculo(Integer.parseInt(tuberculo));
                }
                if ("1".equals(otro)) {
                    dato.setOtro(Integer.parseInt(otro));
                }
                if ("1".equals(cardio)) {
                    dato.setCardio(Integer.parseInt(cardio));
                }
                if ("1".equals(reuma)) {
                    dato.setReuma(Integer.parseInt(reuma));
                }
                if ("1".equals(mortalidad)) {
                    dato.setNooplasia(Integer.parseInt(mortalidad));
                }
                if ("1".equals(cancer)) {
                    dato.setCancer(Integer.parseInt(cancer));
                }
                if ("1".equals(cancero)) {
                    dato.setCancero(Integer.parseInt(cancero));
                }
                if ("1".equals(depre)) {
                    dato.setDepre(Integer.parseInt(depre));
                }
                if ("1".equals(epilepsia)) {
                    dato.setEpilepsia(Integer.parseInt(epilepsia));
                }
                if ("1".equals(psico)) {
                    dato.setPsico(Integer.parseInt(psico));
                }
                if ("1".equals(discapa)) {
                    dato.setDiscapa(Integer.parseInt(discapa));
                }
                if ("1".equals(pies)) {
                    dato.setAqv(Integer.parseInt(pies));
                }
                if ("1".equals(ojos)) {
                    dato.setBcg(Integer.parseInt(ojos));
                }
                if ("1".equals(dieta)) {
                    dato.setCondon(Integer.parseInt(dieta));
                }
                if ("1".equals(ejercicios)) {
                    dato.setControlpos(Integer.parseInt(ejercicios));
                }
                if ("1".equals(carmelo)) {
                    dato.setSuma13(Integer.parseInt(carmelo));
                }

                if (buscarPaciente.getEdad() < 5) {
                    dato.setSuma1(1);
                }
                if (buscarPaciente.getEdad() >= 5 && buscarPaciente.getEdad() <= 9) {
                    dato.setSuma2(1);
                }
                if (buscarPaciente.getEdad() >= 10 && buscarPaciente.getEdad() <= 20) {
                    dato.setSuma3(1);
                }
                if (buscarPaciente.getEdad() >= 21 && buscarPaciente.getEdad() <= 59) {
                    dato.setSuma4(1);
                }
                if (buscarPaciente.getEdad() > 59) {
                    dato.setSuma5(1);
                }
                // grabar los datos introducidos en la ventana    
                dato.setCod_esta(cliente.getCod_esta());
                this.mi.setCrearCuaderno1(dato);
            }
            /*
       if(!"E".equals(expedido)) {   ////&& !"P".equals(datol.getArea())
          String id_pedido    = request.getParameter("id_pedido");  
          String num_cladoc   = request.getParameter("num_cladoc");
          
          num_cladoc=Integer.toString(this.mi.getNumClaDoc()) ;    

          Historiales datoka= this.mi.getDatosHistorial(Integer.parseInt(id_reservacion)) ;
               Pacientes paciente = new Pacientes();
               paciente.setNombres(buscarPaciente.getNombres());
               paciente.setPrecio_total(10);
               paciente.setNum_cladoc(num_cladoc); 
               paciente.setId_paciente(datoka.getId_paciente()); 
               paciente.setId_rubro(2); 
               paciente.setId_carpeta(Integer.parseInt(id_reservacion));
               paciente.setNit("SEGURO"); 
               paciente.setId_costo(2);
               paciente.setId_persona(1);
               paciente.setId_dispensa(usuario.getId_persona());
               paciente.setId_pais(0);
               paciente.setId_estado("P");    
               paciente.setTipo("P") ;    
               paciente.setId_factura(0);/////0 sin factura, 1 con factura, 2, 3, 4, para num correlativo, entradas, ajuste+ y ajuste-
               this.mi.setCrearPeedido(paciente) ;   
               ////////
               id_pedido= Integer.toString(this.mi.getNumPedido());  
         
            Detalle detalle = new Detalle();
            detalle.setEntrada(10);
            detalle.setSalida(0);
            detalle.setId_costo(2);
            detalle.setId_pedido(Integer.parseInt(id_pedido));
            detalle.setUlt_usuario(1);
            detalle.setId_rubro(2) ;
            detalle.setIndicacion("SEGURO");
            this.mi.setCrearDetalle(detalle) ; 
          
       }
             */

        }/////////////////////////

        /*
    if("Actualizar".equals(accion)) {
        String tipo   = request.getParameter("tipo");
        
        //if(C1.isEmpty() != true){
        //    return new ModelAndView("Aviso","mensaje","YA HA REGISTRADO UNA VEZ");    
        //   }
        
        Historiales datosHistorial = this.mi.getDatosHistorial(Integer.parseInt(id_reservacion)) ; // saca un registro a ser modificado
        datosHistorial.setRepetida(tipo);
        datosHistorial.setId_historial(Integer.parseInt(id_reservacion));
        int iResultado = this.mi.setRegistrarHistorial(datosHistorial); 
        String speso       = request.getParameter("speso");
        String alcohol     = request.getParameter("alcohol");
        String fuma        = request.getParameter("fuma");
        String violencia   = request.getParameter("violencia");
        String auto        = request.getParameter("auto");
        String urinaria    = request.getParameter("urinaria");
        String sistemica   = request.getParameter("sistemica");
        String arterial    = request.getParameter("arterial");
        String diabetes    = request.getParameter("diabetes");
        String glome       = request.getParameter("glome");
        String tracto      = request.getParameter("tracto");
        String lupus       = request.getParameter("lupus");
        String litiasis    = request.getParameter("litiasis");
        String nooplasia   = request.getParameter("nooplasia");
        String nefro       = request.getParameter("nefro");
        String disli       = request.getParameter("disli");
        String frenal      = request.getParameter("frenal");
        String bajopeso    = request.getParameter("bajopeso");
        String prematuro   = request.getParameter("prematuro");
        String eritro      = request.getParameter("eritro");
        String renal       = request.getParameter("renal");
        String tuberculo   = request.getParameter("tuberculo");
        String otro        = request.getParameter("otro");
        String cardio      = request.getParameter("cardio");
        String reuma       = request.getParameter("reuma");
        String cancer      = request.getParameter("cancer");
        String cancero     = request.getParameter("cancero");
        String depre       = request.getParameter("depre");
        String epilepsia   = request.getParameter("epilepsia");
        String psico       = request.getParameter("psico");
        String discapa     = request.getParameter("discapa");
        String resultado   = request.getParameter("resultado");
        String hba         = request.getParameter("hba");
        String gfr         = request.getParameter("gfr");
        String creatinina  = request.getParameter("creatinina");
        String colesterol  = request.getParameter("colesterol");
        String imc         = request.getParameter("imc");
        String pies        = request.getParameter("pies");
        String ojos        = request.getParameter("ojos");
        String dieta       = request.getParameter("dieta");
        String ejercicios  = request.getParameter("ejercicios");
        String observa     = request.getParameter("observa");
                
        Cuadernos dato= new Cuadernos();
        dato.setTipoconsulta(tipo);
        dato.setId_historial(Integer.parseInt(id_reservacion));
        dato.setReferido(request.getParameter("ref"));
        dato.setContraref(request.getParameter("cref"));
        dato.setAspecto(hba);
        dato.setBacterias(gfr);
        dato.setBilirrubina(creatinina);
        dato.setCantidad(colesterol);
        dato.setCetonas(imc);
        dato.setObserva(observa);
        
        if("1".equals(speso))      dato.setSpeso(Integer.parseInt(speso)); 
        if("1".equals(alcohol))    dato.setAlcohol(Integer.parseInt(alcohol)); 
        if("1".equals(fuma))       dato.setFuma(Integer.parseInt(fuma)); 
        if("1".equals(violencia))  dato.setViolencia(Integer.parseInt(violencia)); 
        if("1".equals(auto))       dato.setAuto(Integer.parseInt(auto)); 
        if("1".equals(urinaria))   dato.setUrinaria(Integer.parseInt(urinaria)); 
        if("1".equals(sistemica))  dato.setSistemica(Integer.parseInt(sistemica)); 
        if("1".equals(arterial))   dato.setArterial(Integer.parseInt(arterial)); 
        if("1".equals(diabetes))   dato.setDiabetes(Integer.parseInt(diabetes)); 
        if("1".equals(glome))      dato.setGlome(Integer.parseInt(glome)); 
        if("1".equals(tracto))     dato.setTracto(Integer.parseInt(tracto)); 
        if("1".equals(lupus))      dato.setLupus(Integer.parseInt(lupus)); 
        if("1".equals(litiasis))   dato.setLitiasis(Integer.parseInt(litiasis)); 
        if("1".equals(nooplasia))  dato.setNooplasia(Integer.parseInt(nooplasia)); 
        if("1".equals(nefro))      dato.setNefro(Integer.parseInt(nefro)); 
        if("1".equals(disli))      dato.setDisli(Integer.parseInt(disli)); 
        if("1".equals(frenal))     dato.setFrenal(Integer.parseInt(frenal)); 
        if("1".equals(bajopeso))   dato.setBajopeso(Integer.parseInt(bajopeso)); 
        if("1".equals(prematuro))  dato.setPrematuro(Integer.parseInt(prematuro)); 
        if("1".equals(eritro))     dato.setEritro(Integer.parseInt(eritro)); 
        if("1".equals(renal))      dato.setRenal(Integer.parseInt(renal)); 
        if("1".equals(tuberculo))  dato.setTuberculo(Integer.parseInt(tuberculo)); 
        if("1".equals(otro))       dato.setOtro(Integer.parseInt(otro)); 
        if("1".equals(cardio))     dato.setCardio(Integer.parseInt(cardio)); 
        if("1".equals(reuma))      dato.setReuma(Integer.parseInt(reuma)); 
        if("1".equals(cancer))     dato.setCancer(Integer.parseInt(cancer)); 
        if("1".equals(cancero))    dato.setCancero(Integer.parseInt(cancero)); 
        if("1".equals(depre))      dato.setDepre(Integer.parseInt(depre)); 
        if("1".equals(epilepsia))  dato.setEpilepsia(Integer.parseInt(epilepsia)); 
        if("1".equals(psico))      dato.setPsico(Integer.parseInt(psico)); 
        if("1".equals(discapa))    dato.setDiscapa(Integer.parseInt(discapa)); 
        if("1".equals(pies))       dato.setAqv(Integer.parseInt(pies)); 
        if("1".equals(ojos))       dato.setBcg(Integer.parseInt(ojos)); 
        if("1".equals(dieta))      dato.setCondon(Integer.parseInt(dieta)); 
        if("1".equals(ejercicios))  dato.setControlpos(Integer.parseInt(ejercicios)); 
           
        this.mi.setModificarCuaderno1(dato); 
    }
         */
        if ("Enfer. Renales".equals(accion)) {
            enfer = "renal";
            modelo.put("estimc", estimc);
            modelo.put("enfer", enfer);
            //  return new ModelAndView("administrarcuadernos/Cuaderno1", modelo);
        }
        if ("Diabetes".equals(accion)) {
            enfer = "diabetes";
            modelo.put("estimc", estimc);
            modelo.put("enfer", enfer);
            if (id_reservacion == null) {
                id_reservacion = "0";
            } else {
                //Historiales datohi = new Historiales();
                datohi.setId_historial(Integer.parseInt(id_reservacion));
                datohi.setCod_esta(cliente.getCod_esta());
                Historiales datosHistorialP = this.mi.getDatosHistorial(datohi);

                modelo.put("peso", Double.toString(datosHistorialP.getPeso()));
                modelo.put("talla", Double.toString(datosHistorialP.getTalla()));
                modelo.put("presion", datosHistorialP.getPa());

                if (datosHistorialP.getTalla() == 0 || datosHistorialP.getPeso() == 0) {
                    return new ModelAndView("Aviso", "mensaje", "Coloque datos de Peso y Talla Correctos");
                } else {
                    double valor = datosHistorialP.getPeso() / ((datosHistorialP.getTalla() * datosHistorialP.getTalla()) / (100 * 100));
                    valor1 = String.valueOf(valor);
                    modelo.put("imc", valor1.substring(0, 4));
                }
            }
        }
        if ("Todos".equals(accion)) {
            enfer = "todos";
            modelo.put("estimc", estimc);
            modelo.put("enfer", enfer);
            if (id_reservacion == null) {
                id_reservacion = "0";
            } else {
                //Historiales datohi = new Historiales();
                datohi.setId_historial(Integer.parseInt(id_reservacion));
                datohi.setCod_esta(cliente.getCod_esta());
                Historiales datosHistorialP = this.mi.getDatosHistorial(datohi);

                modelo.put("peso", Double.toString(datosHistorialP.getPeso()));
                modelo.put("talla", Double.toString(datosHistorialP.getTalla()));
                modelo.put("presion", datosHistorialP.getPa());

                if (datosHistorialP.getTalla() == 0 || datosHistorialP.getPeso() == 0) {
                    return new ModelAndView("Aviso", "mensaje", "Coloque datos de Peso y Talla Correctos");
                } else {
                    double valor = datosHistorialP.getPeso() / ((datosHistorialP.getTalla() * datosHistorialP.getTalla()) / (100 * 100));
                    valor1 = String.valueOf(valor);
                    modelo.put("imc", valor1.substring(0, 4));
                }
            }
        }
        if ("Enfer. no Trasmisibles".equals(accion)) {
            enfer = "Transmisible";
            modelo.put("enfer", enfer);
            modelo.put("estimc", estimc);
            // return new ModelAndView("administrarcuadernos/Cuaderno1", modelo);
        }
        if ("Eliminar".equals(accion)) {
            String id_cuaderno = request.getParameter("id_cuaderno");
            Cuadernos datox = new Cuadernos();
            datox.setId_cuaderno(Integer.parseInt(id_cuaderno));
            datox.setId_historial(Integer.parseInt(id_reservacion));
            datox.setCod_esta(cliente.getCod_esta());
            this.mi.setEliminarCuaderno1(datox);
        }
        if ("Terminar".equals(accion)) {
            if ("inter".equals(swinter)) {
                return new ModelAndView("administrarhistoriales/PlanAccionPacienteI", modelo);
            } else {
                return new ModelAndView("administrarhistoriales/PlanAccionPaciente", modelo);
            }
        }
        //Sacar los datos de odontologia

        List listaOdon = this.mi.getPacienteCuaderno1(Integer.parseInt(id_reservacion));
        modelo.put("listaExter", listaOdon);

        Cuadernos dato1 = new Cuadernos();
        dato1.setCod_esta(cliente.getCod_esta());
        dato1.setId_paciente(Integer.parseInt(id_paciente));
        dato1.setTipo("U");
        dato1.setEstado("T");
        List listac1 = this.mi.getVerCuaderno1Uni(dato1);  ///getVerCuaderno1Uni
        modelo.put("listac1", listac1);

        return new ModelAndView("administrarcuadernos/Cuaderno1", modelo);

    }
}
