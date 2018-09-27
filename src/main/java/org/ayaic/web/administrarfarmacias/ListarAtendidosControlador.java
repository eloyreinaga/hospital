package org.ayaic.web.administrarfarmacias;

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
public class ListarAtendidosControlador {

    private final MiFacade mi;

    public ListarAtendidosControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/ListarAtendidos.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String id_historial = request.getParameter("id_historial");
        String id_historia = request.getParameter("id_historia");
        String id_paciente = request.getParameter("id_paciente");
        String id_persona = request.getParameter("id_persona");
        String id_personafar = request.getParameter("id_personafar");
        String cod_esta = request.getParameter("cod_esta");
        String suma1est = request.getParameter("suma1est");
        String id_consultorio = request.getParameter("id_consultorio");
        String especialidad = request.getParameter("especialidad");
        String sig_centro = request.getParameter("sig_centro");
        String id_riesgo = request.getParameter("id_riesgo");
        String servicio = request.getParameter("servicio");
        String id_tipointer = request.getParameter("id_tipointer");
        String id_farmacia2 = request.getParameter("id_farmacia2");
        String swb = request.getParameter("sw");
        String accion = request.getParameter("accion");
        String accione = request.getParameter("accione");
        String accionest = request.getParameter("accionest");
        String expedido = request.getParameter("expedido");
        String diaq1 = request.getParameter("mesq1");
        String mesq1 = request.getParameter("mesq1");
        String[] dias = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
        String[] meses = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        Date fecha1 = new Date();
        int anioq = fecha1.getYear() + 1900;
        String[] anios = {Integer.toString(anioq), Integer.toString(anioq - 1), Integer.toString(anioq - 2), Integer.toString(anioq - 3), Integer.toString(anioq - 4), Integer.toString(anioq - 5), Integer.toString(anioq - 6), Integer.toString(anioq - 7), Integer.toString(anioq - 8), Integer.toString(anioq - 9), Integer.toString(anioq - 10)};
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
        modelo.put("id_farmacia2", id_farmacia2);
        modelo.put("id_riesgo", id_riesgo);
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
        datom.setExpedido("I");    /////getDatosItem
        Medicamentos datoItem = this.mi.getDatosItem(datom);
        modelo.put("datoItem", datoItem);

        //Farmacias datofar = new Farmacias();
        //datofar.setCod_esta(cliente.getCod_esta()) ;
        //List listarFarmacia = this.mi.getListarFarmacias(datofar);
        //modelo.put("listarFarmacia", listarFarmacia);
        ///ya no mostrara farmacia mostrara establecimientos para buscar recetas otras
        Consultorios aa = new Consultorios();
        aa.setCod_esta(cliente.getCod_esta()); ////se cambia cod_esta mientras se crea una tabla donde se uniran cod_esta de farmacias
        List listarCentros = this.mi.getListarCentroCNSFar(aa); ////Debe mejorarse la busqueda de los establecimientos Unidos por comun     
        modelo.put("listarCentros", listarCentros);

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
        modelo.put("id_tipointer", id_tipointer);
        modelo.put("id_paciente", id_paciente);
        modelo.put("sig_centro", sig_centro);
        modelo.put("descrip", servicio);
        
        if ("adicionar".equals(accion)) {
            modelo.put("nombres", "NOMBRE");
            modelo.put("nit", "NIT");
            modelo.put("id_historial", "0");
            modelo.put("swb", request.getParameter("sw"));
            modelo.put("num_cladoc", "0");
            Consultorios a = new Consultorios();
            a.setCod_esta(cliente.getCod_esta());
            List listarCargos = this.mi.getListarConsultorios(a);

            a.setId_estado("R");      //////getListarTipoRecetaCNS
            List listarCargos2 = this.mi.getListarTipoRecetaCNS(a);
            modelo.put("listarCargos", listarCargos2);
            a.setId_estado("R");      //////getListarTipoRecetaCNS
            List listarTipReceta = this.mi.getListarTipoRecetaCNS(a);
            modelo.put("listarTipReceta", listarTipReceta);

            modelo.put("listaCargos", listarCargos);

            //Consultorios a = new Consultorios();
            a.setId_estado("I");
            List listarCod = mi.getListarCodCNS(a);
            modelo.put("listarCod", listarCod);
            modelo.put("cod", "ID");
            modelo.put("tipo", "I");
            modelo.put("tiporecet", "E");
            if (cliente.getCod_esta() == 200721) {
                modelo.put("tipo", "E");
            }
            return new ModelAndView("administrarfarmacias/EntregaNPaciente", modelo);
        }

        if ("Establecimiento".equals(accionest) || "especialidad".equals(accione)) {
            String tag = request.getParameter("tag");

            Pacientes paciente = new Pacientes();
            paciente.setTipo("U");    //////getListarPacientesCnsUnico   lugarci as expedido
            paciente.setId_paciente(Integer.parseInt(id_paciente));
            List listarPac = this.mi.getListarPacientesCnsUnico(paciente);
            if (!listarPac.isEmpty()) {
                Pacientes datopac = (Pacientes) listarPac.get(0);
                modelo.put("datopaci", datopac);
            }
            Pacientes buscarPaciente = this.mi.getDatosPaciente(Integer.parseInt(id_paciente));
            modelo.put("nombres", buscarPaciente.getNombres());
            modelo.put("datos", buscarPaciente);

            aa.setCod_esta(Integer.parseInt(sig_centro));
            List listarCargos = this.mi.getListarConsultorios(aa);
            modelo.put("listarCargos", listarCargos);
            aa.setId_estado("R");      //////getListarTipoRecetaCNS
            List listarTipReceta = this.mi.getListarTipoRecetaCNS(aa);
            modelo.put("listarTipReceta", listarTipReceta);
            //a.setId_estado("C");     /////getListarCentroCNS
            //List listarCentros = this.mi.getListarCentroCNS(aa);    
            //modelo.put("listarCentros", listarCentros);
            aa.setId_estado("S");     /////getListarServicioCNS
            List listarServicio = this.mi.getListarServicioCNS(aa);
            modelo.put("listarServicio", listarServicio);

            Personas persona = new Personas();
            persona.setId_consultorio(Integer.parseInt(especialidad));
            persona.setCod_esta(Integer.parseInt(sig_centro));
            List buscarEmpleado = this.mi.getDatosPersonaConsul(persona);
            modelo.put("listaPersonas", buscarEmpleado);

            modelo.put("descrip", servicio);
            modelo.put("id_consultorio", especialidad);
            modelo.put("id_paciente", id_paciente);
            modelo.put("id_historial", id_historial);
            modelo.put("cod_esta", cod_esta);
            modelo.put("descripcionc", "78");
            modelo.put("tiporecet", tag);

            return new ModelAndView("administrarfarmacias/EntregaPacienteFarCNS", modelo);
        }

        if ("entregar".equals(accion) || "entregarya".equals(accion)) {

            if ("entregar".equals(accion) && cod_esta == null) {
                cod_esta = Integer.toString(cliente.getCod_esta());
            }

            Pacientes buscarPaciente = this.mi.getDatosPaciente(Integer.parseInt(id_paciente));
            modelo.put("datos", buscarPaciente);
            modelo.put("carnet", buscarPaciente.getCarnet());
            modelo.put("nombres", buscarPaciente.getNombres());
            modelo.put("id_historial", id_historial);
            modelo.put("id_paciente", id_paciente);
            modelo.put("expedido", expedido);

            Historiales datohi = new Historiales();
            datohi.setId_historial(Integer.parseInt(id_historial));
            datohi.setCod_esta(cliente.getCod_esta());
            if (sig_centro != null && !"".equals(sig_centro)) {
                datohi.setCod_esta(Integer.parseInt(sig_centro));
                ///sig_centro=cod_esta;
            }
            Historiales datoHis = this.mi.getDatosHistorial(datohi);
            modelo.put("datosHis", datoHis);

            if (datoHis != null) {
                //Historiales Inter =  (Historiales) listasInt.get(0);
                id_persona = Integer.toString(datoHis.getId_persona());
                id_consultorio = Integer.toString(datoHis.getId_consultorio());
            }

            if (datoestab.getCod_esta() == 200010) {
                Pacientes paciente = new Pacientes();
                paciente.setTipo("U");    //////getListarPacientesCnsUnico   lugarci as expedido
                paciente.setId_paciente(Integer.parseInt(id_paciente));
                List listarPac = this.mi.getListarPacientesCnsUnico(paciente);
                if (!listarPac.isEmpty()) {
                    Pacientes datopac = (Pacientes) listarPac.get(0);
                    modelo.put("datopaci", datopac);
                }
                //Consultorios aa= new Consultorios() ;
                aa.setCod_esta(cliente.getCod_esta());
                if (sig_centro != null && !"".equals(sig_centro)) {
                    aa.setCod_esta(Integer.parseInt(sig_centro));
                }
                aa.setId_estado("");
                List listarCargos = this.mi.getListarConsultorios(aa);
                modelo.put("listarCargos", listarCargos);
                aa.setId_estado("R");      //////getListarTipoRecetaCNS
                List listarTipReceta = this.mi.getListarTipoRecetaCNS(aa);
                modelo.put("listarTipReceta", listarTipReceta);
                aa.setId_estado("S");     /////getListarServicioCNS
                List listarServicio = this.mi.getListarServicioCNS(aa);
                modelo.put("listarServicio", listarServicio);
                modelo.put("id_consultorio", Integer.toString(datoHis.getId_consultorio()));
                modelo.put("id_persona", Integer.toString(datoHis.getId_persona()));
                modelo.put("cod_esta", sig_centro);
                modelo.put("descrip", "78");
                modelo.put("tiporecet", "E");
                if ("1".equals(id_tipointer)) {
                    modelo.put("tiporecet", "I"); ////14/01/2017 para que muestre receta internado al entregar medicamentos
                }
                if ("S".equals(expedido)) {
                    modelo.put("tiporecet", "S"); ////14/06/2017 para que muestre receta SUMI
                }
                Personas persona = new Personas();
                persona.setId_consultorio(datoHis.getId_consultorio());
                persona.setCod_esta(cliente.getCod_esta());
                if (sig_centro != null && !"".equals(sig_centro)) {
                    persona.setCod_esta(Integer.parseInt(sig_centro));
                    if (id_personafar != null && !"".equals(id_personafar)) {
                        modelo.put("id_persona", id_personafar);
                    }
                }
                List buscarEmpleado = this.mi.getDatosPersonaConsul(persona);
                if (!buscarEmpleado.isEmpty()) {
                    Personas datoEmp = (Personas) buscarEmpleado.get(0);
                    modelo.put("descrip", datoEmp.getCadena1());
                    if (servicio != null && !"".equals(servicio)) {
                        modelo.put("descrip", servicio);
                    }
                }
                modelo.put("listaPersonas", buscarEmpleado);

                return new ModelAndView("administrarfarmacias/EntregaPacienteFarCNS", modelo);
            } else {
                return new ModelAndView("administrarfarmacias/EntregaPaciente", modelo);
            }
        }

        if ("entregarint".equals(accion)) {
            String id_doctor = request.getParameter("id_persona");

            if ("entregarint".equals(accion) && cod_esta == null) {
                cod_esta = Integer.toString(cliente.getCod_esta());
            }

            Pacientes buscarPaciente = this.mi.getDatosPaciente(Integer.parseInt(id_paciente));
            modelo.put("nombres", buscarPaciente.getNombres());
            modelo.put("carnet", buscarPaciente.getCarnet());
            modelo.put("id_historial", id_historial);
            modelo.put("id_historia", id_historia);
            modelo.put("id_doctor", id_doctor);
            modelo.put("expedido", expedido);
            modelo.put("entragarint", "entragarint");

            Historiales datohi = new Historiales();
            datohi.setId_historial(Integer.parseInt(id_historial));
            datohi.setCod_esta(cliente.getCod_esta());
            if (sig_centro != null && !"".equals(sig_centro)) {
                datohi.setCod_esta(Integer.parseInt(sig_centro));
            }
            Historiales datoHis = this.mi.getDatosHistorial(datohi);
            id_consultorio = Integer.toString(datoHis.getId_consultorio());
            id_persona = Integer.toString(datoHis.getId_persona());
            modelo.put("datosHis", datoHis);

            datohi.setAccion("I");
            datohi.setId_historia(Integer.parseInt(id_historia));
            List listasInt = this.mi.getHistoriaInterIndi(datohi);    ////getHistoriaInterIndi

            if (!listasInt.isEmpty()) {
                Historiales Inter = (Historiales) listasInt.get(0);
                id_persona = Integer.toString(Inter.getId_persona());
                id_consultorio = Integer.toString(Inter.getId_carpeta());
            }

            if (datoestab.getCod_esta() == 200010) {
                Pacientes paciente = new Pacientes();
                paciente.setTipo("U");    //////getListarPacientesCnsUnico   lugarci as expedido
                paciente.setId_paciente(Integer.parseInt(id_paciente));
                List listarPac = this.mi.getListarPacientesCnsUnico(paciente);
                if (!listarPac.isEmpty()) {
                    Pacientes datopac = (Pacientes) listarPac.get(0);
                    modelo.put("datopaci", datopac);
                }
                //Consultorios aa= new Consultorios() ;
                aa.setCod_esta(cliente.getCod_esta());
                if (cod_esta != null) {
                    aa.setCod_esta(Integer.parseInt(cod_esta));
                }
                aa.setId_estado("");
                List listarCargos = this.mi.getListarConsultorios(aa);
                modelo.put("listarCargos", listarCargos);
                aa.setId_estado("R");      //////getListarTipoRecetaCNS
                List listarTipReceta = this.mi.getListarTipoRecetaCNS(aa);
                modelo.put("listarTipReceta", listarTipReceta);
                aa.setId_estado("S");     /////getListarServicioCNS
                List listarServicio = this.mi.getListarServicioCNS(aa);
                modelo.put("listarServicio", listarServicio);
                modelo.put("id_consultorio", id_consultorio);
                modelo.put("id_persona", id_persona);
                modelo.put("cod_esta", cod_esta);
                modelo.put("tiporecet", "I");
                modelo.put("descrip", "78");
                if (cliente.getId_farmacia() > 2) {
                    modelo.put("tiporecet", "U");
                }
                if (cliente.getCod_esta() == 200075 || cliente.getCod_esta() == 200115) {
                    modelo.put("tiporecet", "I");
                }
                Personas persona = new Personas();
                persona.setId_consultorio(Integer.parseInt(id_consultorio));
                persona.setCod_esta(cliente.getCod_esta());
                List buscarEmpleado = this.mi.getDatosPersonaConsul(persona);
                if (!buscarEmpleado.isEmpty()) {
                    Personas datoEmp = (Personas) buscarEmpleado.get(0);
                    modelo.put("descrip", datoEmp.getCadena1());
                }
                modelo.put("listaPersonas", buscarEmpleado);
                modelo.put("interfar", "interfar");

                return new ModelAndView("administrarfarmacias/EntregaPacienteFarCNS", modelo);
            } else {
                return new ModelAndView("administrarfarmacias/EntregaPacienteI", modelo);
            }
        }

        if ("dispensar".equals(accion)) {

            Pacientes buscarPaciente = this.mi.getDatosPaciente(Integer.parseInt(id_paciente));
            modelo.put("nombres", buscarPaciente.getNombres());
            modelo.put("id_historial", id_historial);
            modelo.put("dispensar", "dispensar");
            modelo.put("expedido", expedido);
            return new ModelAndView("administrarfarmacias/EntregaPacienteI", modelo);
        }
        
        if ("pagarmed".equals(accion)) {
            String sumatot1 = request.getParameter("precio");
            String id_pedido = request.getParameter("id_pedido");
            
            if("1".equals(suma1est)){
                return new ModelAndView("Aviso", "mensaje", "Este Pedido ya se ha descargado en el Kardex");
            }

            Pacientes paciente1 = new Pacientes();
            paciente1.setId_pedido(Integer.parseInt(id_pedido));
            paciente1.setCod_esta(cliente.getCod_esta());
            Pacientes buscarPaciente = this.mi.getDatosPedidoProf(paciente1);
            modelo.put("datos", buscarPaciente);

            Recetas midato = new Recetas();
            midato.setId_pedido(Integer.parseInt(id_pedido));
            midato.setId_prestacion(cliente.getCod_esta());
            midato.setCod_esta(cliente.getCod_esta());
            midato.setId_farmacia(cliente.getId_farmacia());
            List listarKardex = this.mi.getListarKardexProf(midato);
            modelo.put("listarKardex", listarKardex);
            modelo.put("num_recibo", buscarPaciente.getNum_cladoc());
            modelo.put("sumatot1", sumatot1);
            return new ModelAndView("administrarfarmacias/CobrarMedPaciente", modelo);
        }

        if ("entregarmed".equals(accion)) {

            String id_pedido = request.getParameter("id_pedido");
            // recupera el paciente a entregar
            Pacientes paciente1 = new Pacientes();
            paciente1.setId_pedido(Integer.parseInt(id_pedido));
            paciente1.setCod_esta(cliente.getCod_esta());
            Pacientes buscarPaciente = this.mi.getDatosPedido(paciente1);
            modelo.put("datos", buscarPaciente);
 
            Recetas midato = new Recetas();
            midato.setId_pedido(Integer.parseInt(id_pedido));
            midato.setId_prestacion(cliente.getCod_esta());
            midato.setCod_esta(cliente.getCod_esta());
            midato.setId_farmacia(cliente.getId_farmacia());
            List listarKardex = this.mi.getListarKardex(midato);
            modelo.put("listarKardex", listarKardex);
            return new ModelAndView("administrarfarmacias/EntregaMedPaciente", modelo);
        }

        if ("previa".equals(accion)) {
            String nombres = request.getParameter("nombres");
            String nombre = request.getParameter("nombre");

            String id_pedido = request.getParameter("id_pedido");
            String id_prestacion = request.getParameter("id_prestacion");

            Consultorios datosconsulta = this.mi.getDatosConsultorio(cliente.getId_consultorio());
            Pacientes buscarPaciente = this.mi.getDatosPaciente(Integer.parseInt(id_paciente));
            modelo.put("id_riesgo", id_riesgo);

            Recetas datores = new Recetas();
            datores.setId_historial(Integer.parseInt(id_historial));
            datores.setId_paciente(Integer.parseInt(id_paciente));
            datores.setId_estado("%");
            datores.setCod_esta(cliente.getCod_esta());
            datores.setId_farmacia(cliente.getId_farmacia());
            //datores.setId_historia(Integer.parseInt(id_historia)); 
            if (cod_esta != null) {
                if (cliente.getCod_esta() != Integer.parseInt(cod_esta)) {
                    datores.setCod_esta(Integer.parseInt(cod_esta));
                    datores.setId_farmacia(Integer.parseInt(id_farmacia2));
                }
            }
            List listarRecetaSumi = this.mi.getListarRecetaAnterior(datores);
            modelo.put("listarRecetaSumi", listarRecetaSumi);
            if (cliente.getId_rol2() == 29) {
                datores.setId_estado("M");  ////getListarRecetaAnteriorCarmelo para mostrar todos sus entregas
                List listarRecetaSumi2 = this.mi.getListarRecetaAnteriorCarmelo(datores);
                modelo.put("listarRecetaSumi", listarRecetaSumi2);
            }

            Recetas datore = new Recetas();
            datore.setId_historial(Integer.parseInt(id_historial));
            datore.setId_estado("A");
            datore.setCod_esta(cliente.getCod_esta());
            datore.setId_farmacia(cliente.getId_farmacia());
            if (cod_esta != null) {
                if (cliente.getCod_esta() != Integer.parseInt(cod_esta)) {
                    datore.setCod_esta(Integer.parseInt(cod_esta));
                    datore.setId_farmacia(Integer.parseInt(id_farmacia2));
                }
            }
            List listarRecetasP = this.mi.getListarRecetas(datore);
            modelo.put("listarRecetasP", listarRecetasP);

            Prestaciones prestac = new Prestaciones();
            prestac.setId_historial(Integer.parseInt(id_historial));
            prestac.setId_persona(Integer.parseInt(id_persona));
            prestac.setCod_esta(cliente.getCod_esta());
            List listarRecetasPres = this.mi.getListarSumiRecetas(prestac);
            modelo.put("listarRecetasPres", listarRecetasPres);

            List listarRecetas = this.mi.getListarSumiRecetasI(prestac);
            modelo.put("listarRecetas", listarRecetas);

            modelo.put("nombres", nombres);
            modelo.put("tipo_medico", Integer.toString(datosconsulta.getId_cargo()));
            modelo.put("nombre", nombre);
            modelo.put("expedido", expedido);
            modelo.put("id_historial", id_historial);
            modelo.put("id_paciente", id_paciente);
            modelo.put("id_persona", id_persona);
            modelo.put("edad", Integer.toString(buscarPaciente.getEdad()));
            modelo.put("mes", Integer.toString(buscarPaciente.getMes()));
            modelo.put("dia", Integer.toString(buscarPaciente.getDia()));

            return new ModelAndView("administrarfarmacias/EntregaRecetaPrevia", modelo);
        }

        if ("previaint".equals(accion)) {
            String nombres = request.getParameter("nombres");
            String nombre = request.getParameter("nombre");
            String id_pedido = request.getParameter("id_pedido");
            String id_doctor = request.getParameter("id_persona");

            Pacientes buscarPaciente = this.mi.getDatosPaciente(Integer.parseInt(id_paciente));
            if ("".equals(id_historia)) {
                Historiales datohi = new Historiales();
                datohi.setCod_esta(cliente.getCod_esta());
                datohi.setId_historial(Integer.parseInt(id_historial));
                List listasInter2 = this.mi.getHistoriaInter(datohi);
                Historiales datoin2 = (Historiales) listasInter2.get(listasInter2.size() - 1);
                id_historia = Integer.toString(datoin2.getId_historia());
            }
            Recetas datore = new Recetas();
            datore.setId_historial(Integer.parseInt(id_historial));
            datore.setId_estado("A");
            datore.setId_persona(Integer.parseInt(id_persona));
            datore.setId_historia(Integer.parseInt(id_historia));
            datore.setId_farmacia(cliente.getId_farmacia());
            datore.setCod_esta(cliente.getCod_esta());
            if (cod_esta != null) {
                if (cliente.getCod_esta() != Integer.parseInt(cod_esta)) {
                    datore.setCod_esta(Integer.parseInt(cod_esta));
                    datore.setId_farmacia(Integer.parseInt(id_farmacia2));
                }
            }
            List listarRecetasP = this.mi.getListarRecetasInt(datore);
            modelo.put("listarRecetasP", listarRecetasP);

            if (listarRecetasP.isEmpty() == true) {
                Historiales reserva = new Historiales();
                reserva.setId_estado("B");
                reserva.setId_historial(Integer.parseInt(id_historia));
                this.mi.setModificarInter(reserva);
            }

            modelo.put("nombres", nombres);
            modelo.put("nombre", nombre);
            modelo.put("modify", accion);
            modelo.put("id_historial", id_historial);
            modelo.put("id_historia", id_historia);
            modelo.put("expedido", expedido);
            modelo.put("id_paciente", id_paciente);
            modelo.put("id_doctor", id_doctor);
            modelo.put("id_persona", id_persona);
            modelo.put("edad", Integer.toString(buscarPaciente.getEdad()));
            modelo.put("mes", Integer.toString(buscarPaciente.getMes()));
            modelo.put("dia", Integer.toString(buscarPaciente.getDia()));

            //List listarRecetasPres = this.mi.getListarSumiRecetas(Integer.parseInt(id_historial),Integer.parseInt(id_persona));
            //modelo.put("listarRecetasPres", listarRecetasPres);
            //if (listarRecetasPres.size()==0){
            Prestaciones prestac = new Prestaciones();
            prestac.setId_historial(Integer.parseInt(id_historial));
            prestac.setCod_esta(cliente.getCod_esta());
            List listarRecetasPres2 = this.mi.getListarSumiRecetasI(prestac);
            modelo.put("listarRecetasPres", listarRecetasPres2);
            //}

            Recetas datores = new Recetas();
            datores.setId_historial(Integer.parseInt(id_historial));
            datores.setId_estado("%");
            //datores.setId_historia(Integer.parseInt(id_historia));  
            List listarRecetaSumi = this.mi.getListarRecetaAnterior(datores);
            modelo.put("listarRecetaSumi", listarRecetaSumi);

            return new ModelAndView("administrarfarmacias/EntregaRecetaPreviaI", modelo);
        }

        if ("Terminar".equals(accion)) {

            String id_pedido = request.getParameter("id_pedido");
            // recupera el paciente a entregar
            Pacientes paciente1 = new Pacientes();
            paciente1.setId_pedido(Integer.parseInt(id_pedido));
            paciente1.setCod_esta(cliente.getCod_esta());
            Pacientes buscarPaciente = this.mi.getDatosPedido(paciente1);
            modelo.put("datos", buscarPaciente);
            buscarPaciente.setId_estado("E");
            buscarPaciente.setFecha_fin(buscarPaciente.getFec_registro());
            //buscarPaciente.setId_dispensa(usuario.getId_persona());
            this.mi.setModificarPedido(buscarPaciente); //// 
        }

        if ("Buscar".equals(accion) || "previafarma".equals(accion)) {
            String nombres = request.getParameter("nombres");
            nombres = nombres.replaceAll("\\s", "%");
            nombres = "%" + nombres + "%";

            if ("".equals(sig_centro) || sig_centro == null) {
                sig_centro = Integer.toString(cliente.getCod_esta());
            }
            Historiales dato = new Historiales();
            dato.setId_estado("A");
            dato.setExpedido("%");
            dato.setNombres(nombres);
            dato.setAccion("N");
            dato.setCod_esta(cliente.getCod_esta());
            if (cliente.getCod_esta() != Integer.parseInt(sig_centro)) {
                dato.setCod_esta(Integer.parseInt(sig_centro));
            }
            List listarAtendidos = this.mi.getListarAtendidosFarNom(dato);  ///getListarAtendidosFarNom
            modelo.put("listarAtendidos", listarAtendidos);
            if (listarAtendidos.size() > 0) {
                modelo.put("listafar", "1");
            }
            List listarAtendidosI = this.mi.getListarAtendidosIFarNom(dato);  /////getListarAtendidosIFarNom
            modelo.put("listarAtendidosI", listarAtendidosI);
            //if(datoestab.getCod_esta()==200010){
            //  dato.setAccion("C");  
            //}
            if (listarAtendidosI.size() > 0) {
                modelo.put("listafari", "1");
            }
            modelo.put("usuario", Integer.toString(cliente.getId_persona()));
            return new ModelAndView("administrarfarmacias/ListarAtendidos", modelo);
        }

        if ("Gral".equals(accion)) {
            String id_farmacia = request.getParameter("id_farmacia");
            String nombres = request.getParameter("nombres");
            nombres = nombres.replaceAll("\\s", "%");
            nombres = "%" + nombres + "%";

            Personas persona = this.mi.getBuscarPersona(cliente.getId_persona());
            Consultorios datosconsulta = this.mi.getDatosConsultorio(persona.getId_consultorio()); // saca un registro a ser modificado
            modelo.put("tipo_medico", Integer.toString(datosconsulta.getId_cargo()));
            Historiales dato = new Historiales();
            dato.setId_estado("A");
            dato.setExpedido("%");
            dato.setCod_esta(cliente.getCod_esta());
            //dato.setId_farmacia(Integer.parseInt(id_farmacia)) ; ////busca los pacientes de otras farmacias 10-05-2015
            if (datoestab.getCod_esta() == 200010) {
                dato.setAccion("N");  ///getListarAtendidosFarNom
            }
            if (cliente.getCod_esta() != Integer.parseInt(sig_centro)) {
                dato.setCod_esta(Integer.parseInt(sig_centro));
            }
            List listarAtendidos = this.mi.getListarAtendidos(dato);
            modelo.put("listarAtendidos", listarAtendidos);
            if (listarAtendidos.size() > 0) {
                modelo.put("listafar", "1");
            }
            List listarAtendidosI = this.mi.getListarAtendidosI(dato);
            modelo.put("listarAtendidosI", listarAtendidosI);

            if (listarAtendidosI.size() > 0) {
                modelo.put("listafari", "1");
            }
            modelo.put("usuario", Integer.toString(cliente.getId_persona()));
            List listarSeguros = this.mi.getListarSeguros("A");
            modelo.put("listaPacAfi", listarSeguros);
            modelo.put("id_farmacia", id_farmacia);
            return new ModelAndView("administrarfarmacias/ListarAtendidos", modelo);
        }

        if ("CrearPerfil".equals(accion)) {

            Recetas datore = new Recetas();
            datore.setId_historial(Integer.parseInt(id_historial));
            datore.setId_historia(Integer.parseInt(id_historia));
            datore.setCod_esta(cliente.getCod_esta());
            datore.setId_estado("K");//// getListarKardexPac
            List listarRecetasS = this.mi.getListarKardexPac(datore);

            if (listarRecetasS.isEmpty()) {  ////verifica si no tiene para poder generar despues de entregado
                datore.setExpedido("K");//// setCrearKardexPacInsert
                this.mi.setCrearKardexPacInsert(datore);
            }
            List listarRecetasK = this.mi.getListarKardexPac(datore); ///27/12/2017 estaba antes getListarRecetasMedico
            modelo.put("listaKarp", listarRecetasK);
        }

        Personas persona = this.mi.getBuscarPersona(cliente.getId_persona());
        Consultorios datosconsulta = this.mi.getDatosConsultorio(persona.getId_consultorio()); // saca un registro a ser modificado
        modelo.put("tipo_medico", Integer.toString(datosconsulta.getId_cargo()));
        modelo.put("id_farmacia", Integer.toString(persona.getId_farmacia()));
        //lista de paciente que fueron atendidos con el medico
        Historiales dato = new Historiales();
        dato.setId_estado("A");
        dato.setExpedido("%");
        dato.setId_farmacia(persona.getId_farmacia());
        dato.setCod_esta(cliente.getCod_esta());

        //if(datosconsulta.getId_cargo()==3 || datosconsulta.getId_cargo()==13 || datosconsulta.getId_cargo()==15 || datosconsulta.getId_cargo()==19   ) {//|| datosconsulta.getId_cargo()==16
        // List listarAtendidos = this.mi.getListarAtendidosEnf(dato);////para enfermeras que entregan medicamentos
        //  modelo.put("listarAtendidos", listarAtendidos);
        // if(listarAtendidos.size()>0){
        //     modelo.put("listafar", "1");
        //  }
        // }else{
        // if(datoestab.getInst_id()==10){ ////para CNS
        //  List listarAtendidos2 = this.mi.getListarAtendidosCNS(dato);
        //   modelo.put("listarAtendidos", listarAtendidos2);
        // }else{
        List listarAtendidos = this.mi.getListarAtendidos(dato);///getListarAtendidos
        modelo.put("listarAtendidos", listarAtendidos);

        if (listarAtendidos.size() > 0) {
            modelo.put("listafar", "1");
        }
        // }
        //}

        //if(datoestab.getInst_id()==10){ ////para CNS
        //  dato.setAccion("C");  ////getListarAtendidosCNS para ambulatorios y getListarAtendidosICNS para internados
        //   List listarAtendidosI2 = this.mi.getListarAtendidosICNS(dato);
        //   modelo.put("listarAtendidosI", listarAtendidosI2);
        // }else{
        List listarAtendidosI = this.mi.getListarAtendidosI(dato);///getListarAtendidosI
        modelo.put("listarAtendidosI", listarAtendidosI);

        if (listarAtendidosI.size() > 0) {
            modelo.put("listafari", "1");
        }

        Pacientes paciente = new Pacientes();
        paciente.setId_estado("A");
        paciente.setId_rubro(1);
        paciente.setId_farmacia(cliente.getId_farmacia());
        paciente.setCod_esta(cliente.getCod_esta());
        List listarSinPago = this.mi.getListarProformas(paciente);  ///getListarCobroRubroFar
        modelo.put("listapago", listarSinPago);

        // lista de pacientes que no terminaron su receta
        paciente.setId_estado("X");
        List listarSinPago1 = this.mi.getListarCobroRubroFar(paciente);
        modelo.put("listapago1", listarSinPago1);

        // lista de pacientes que pagaron en seccion cobranza
        paciente.setId_estado("C");
        List listarPaises = this.mi.getListarCobroRubroFar(paciente);
        modelo.put("milista", listarPaises);

        if (listarSinPago.size() > 0 || listarSinPago1.size() > 0) {
            modelo.put("listaporcancel", "1");
            modelo.put("mostrarpagos", "0");

            if ("mostrarPagos".equals(accione)) {
                modelo.put("mostrarpagos", "1");
            }
        }
        // lista de pacientes que pagaron en seccion cobranza
        if (listarPaises.size() > 0) {
            modelo.put("listacancelados", "1");
        }

        return new ModelAndView("administrarfarmacias/ListarAtendidos", modelo);
    }
}
