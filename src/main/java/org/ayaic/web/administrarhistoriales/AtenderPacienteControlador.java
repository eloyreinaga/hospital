package org.ayaic.web.administrarhistoriales;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Carpetas;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Consultorios;
import org.ayaic.domain.Cuadernos;
import org.ayaic.domain.Departamentos;
import org.ayaic.domain.Eciviles;
import org.ayaic.domain.Empresas;
import org.ayaic.domain.Historiales;
import org.ayaic.domain.Localidades;
import org.ayaic.domain.Medicamentos;
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.Personas;
import org.ayaic.domain.Provincias;
import org.ayaic.domain.Sexos;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AtenderPacienteControlador {

    private final MiFacade mi;

    public AtenderPacienteControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/AtenderPaciente.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String accion1 = request.getParameter("accion1");
        String accionx = request.getParameter("accionx");
        //String suaccion       = request.getParameter("suaccion");
        String accionperi = request.getParameter("accionperi");
        String nombres = request.getParameter("nombres");
        String swinter = request.getParameter("swinter");
        String id_paciente = request.getParameter("id_paciente");
        String id_reservacion = request.getParameter("id_reservacion");
        String id_consultorio = request.getParameter("id_consultorio");
        String id_persona = request.getParameter("id_persona");
        String expedido = request.getParameter("expedido");
        String subjetivo = request.getParameter("subjetivo");
        String objetivo = request.getParameter("objetivo");
        String diagnostico = request.getParameter("diagnostico");
        String miaccion = request.getParameter("miaccion");
        String repetida = request.getParameter("repetida");
        String codigo = request.getParameter("nombres");
        String riesgovig = request.getParameter("riesgovig");
        String literal = request.getParameter("literal");
        String boton = request.getParameter("boton");
        String estimc = request.getParameter("estimc");
        String estimcn = request.getParameter("estimc");
        String valor1 = request.getParameter("valor1");
        String valorn1 = request.getParameter("valor1");
        String fechaenf = request.getParameter("fechaenf");
        String sw = request.getParameter("sw");
        String swcie = request.getParameter("swcie");
        String swemerg = request.getParameter("swemerg");
        String swreser = request.getParameter("swemerg");
        String[] numemba = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"};
        String[] diasg = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
        String[] mesesg = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        Date fumu = new Date();
        int anioq = fumu.getYear() + 1900;
        String[] aniosg = {(Integer.toString(anioq)), (Integer.toString(anioq - 1))};
        String ip = request.getRemoteAddr();
        String host = request.getRemoteHost();
        //InetAddress ip;
        //ip = InetAddress.getLocalHost();

        Localidades local = new Localidades();
        List Estab1 = this.mi.getListarEsta(local);
        Localidades datoestab = (Localidades) Estab1.get(0);
        Personas datosPero = this.mi.getDatosPersona(cliente.getId_persona());
        modelo.put("urgencias", Integer.toString(datosPero.getUrgencias()));
        modelo.put("swcie", swcie);
        modelo.put("literal", literal);
        modelo.put("codigo", codigo);

        if (subjetivo != null) {
            subjetivo = subjetivo.trim();
        } else {
            subjetivo = " ";
        }
        if (objetivo != null) {
            objetivo = objetivo.trim();
        } else {
            objetivo = " ";
        }
        if (diagnostico != null) {
            diagnostico = diagnostico.trim();
        } else {
            diagnostico = " ";
        }
        if (miaccion != null) {
            miaccion = miaccion.trim();
        } else {
            miaccion = " ";
        }
        if (riesgovig != null) {
            riesgovig = riesgovig.trim();
        } else {
            riesgovig = " ";
        }

        if (subjetivo.contains("class=") || subjetivo.contains("MsoNormal") || subjetivo.contains("Office") || objetivo.contains("MsoNormal") || objetivo.contains("Office") || diagnostico.contains("Office") || miaccion.contains("Office")
                || subjetivo.contains("Word") || objetivo.contains("class=") || objetivo.contains("Word") || diagnostico.contains("MsoNormal") || diagnostico.contains("Word") || miaccion.contains("Word")
                || subjetivo.contains("font-") || objetivo.contains("font-") || diagnostico.contains("class=") || diagnostico.contains("font-") || miaccion.contains("class=") || miaccion.contains("font-")) {
            return new ModelAndView("Aviso", "mensaje", "No puede realizar copias de Word o Excel");
        }

        modelo.put("nombres", nombres);
        modelo.put("anios", aniosg);
        modelo.put("meses", mesesg);
        modelo.put("dias", diasg);
        modelo.put("numemba", numemba);
        modelo.put("swinter", swinter);
        modelo.put("swemerg", swemerg);
        modelo.put("id_paciente", id_paciente);
        modelo.put("id_consultorio", id_consultorio);
        modelo.put("id_persona", id_persona);
        modelo.put("codesta", Integer.toString(datoestab.getCod_esta()));
        modelo.put("cod_esta", Integer.toString(cliente.getCod_esta()));

        if ("EliminarH".equals(accion)) {
            Historiales auxh = new Historiales();
            auxh.setId_historial(Integer.parseInt(id_reservacion));
            auxh.setId_persona(cliente.getId_persona());
            auxh.setCod_esta(cliente.getCod_esta());///20/07/2014
            auxh.setCama(ip);///20/07/2014
            auxh.setSala(host);///20/07/2014
            auxh.setEstimc("E");///20/07/2014
            auxh.setId_provincia(cliente.getId_persona());///20/07/2014
            try {
                this.mi.setEliminarHistoria(auxh);
                return new ModelAndView("Aviso", "mensaje", "El paciente fue eliminado correctamente");
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "El registro a eliminar tiene dependencias, Elimine cuadernos y prestaciones o medicamentos que agrego");
            }
        }

        if ("Hcl".equals(accion)) {
            String id_historial = request.getParameter("id_historial");

            Pacientes buscarPacienteh = this.mi.getDatosPaciente(Integer.parseInt(id_paciente)); // saca un registro a ser modificado
            modelo.put("datos", buscarPacienteh);
            Departamentos listaDep = this.mi.getDatosDepartamento(buscarPacienteh.getId_departamento());
            modelo.put("listaDep", listaDep);
            Provincias listaProv = this.mi.getDatosProvincia(buscarPacienteh.getId_provincia());
            modelo.put("listaProv", listaProv);
            Localidades localidad = new Localidades();
            localidad.setId_localidad(buscarPacienteh.getId_localidad());
            Localidades buscarLocalidad = this.mi.getDatosLocalidad(localidad);
            modelo.put("listaLoc", buscarLocalidad);
            Eciviles listaEciv = this.mi.getDatosEcivil(buscarPacienteh.getId_ecivil());
            modelo.put("listaEciv", listaEciv);
            Cuadernos dato1 = new Cuadernos();
            dato1.setTipo("U");
            dato1.setId_estado("%");
            dato1.setEstado("T");
            dato1.setAspecto("M");
            dato1.setId_historial(Integer.parseInt(id_historial));
            dato1.setId_paciente(Integer.parseInt(id_paciente));
            List listaCie = this.mi.getVerCuaderno1CieMorbi(dato1);
            modelo.put("listaCie", listaCie);
            List listarJefe = this.mi.getListarPacientesDJefe(buscarPacienteh.getId_carpeta());
            modelo.put("listaJefe", listarJefe);
            Historiales datohi = new Historiales();
            datohi.setCod_esta(cliente.getCod_esta());
            datohi.setId_historial(Integer.parseInt(id_historial));
            Historiales datosHist = this.mi.getDatosHistorial(datohi);
            modelo.put("listaH", datosHist);
            Personas datosPer = this.mi.getDatosPersona(datosHist.getId_persona());
            modelo.put("listaPer", datosPer);

            List listasInter = this.mi.getHistoriaInter(datohi);
            modelo.put("listaInter", listasInter);
            Cuadernos dato2 = new Cuadernos();
            dato2.setId_historial(Integer.parseInt(id_historial));   ///19-05-2015
            dato2.setCod_esta(cliente.getCod_esta());  ///19-05-2015
            List listac2 = this.mi.getPacienteCuaderno2(dato2);
            modelo.put("listac2", listac2);
            List lista5 = this.mi.getPacienteCuaderno5(dato2);
            modelo.put("listaC5", lista5);

            return new ModelAndView(new ListarHclInterPDF(), modelo);
        }

        if (id_persona == null || "".equals(id_persona)) {
            id_persona = "0";
        }
        
        if (!"Consultar".equals(accion)  ) {
           Medicamentos medid = new Medicamentos();
           medid.setCod_esta(cliente.getCod_esta());
           medid.setId_persona(Integer.parseInt(id_persona));
           List listarEnfermedadesCot = this.mi.getListarEnfermedadesCot(medid); 
           modelo.put("listarEnfermedadesCot", listarEnfermedadesCot);    
        }
        
        Pacientes buscarPaciente = this.mi.getDatosPaciente(Integer.parseInt(id_paciente)); // saca un registro a ser modificado
        modelo.put("datos", buscarPaciente);
        ////////muestra historiales en la parte abajo  17/01/2016  
        Historiales datoz = new Historiales();
        datoz.setId_paciente(Integer.parseInt(id_paciente));
        datoz.setCod_esta(cliente.getCod_esta());
        List listasz = this.mi.getListarHistoria(datoz);
        modelo.put("milista", listasz);
        if (datoestab.getCompartehcl() == 1) {  ///1 comparte la historia clinica
            datoz.setAccion("T");////getListarHistoriaTodo
            List listasz2 = this.mi.getListarHistoriaTodo(datoz);
            modelo.put("milista", listasz2);
        }

        modelo.put("estimc", estimc);

        if (datoestab.getCod_esta() == 200010) {
            Pacientes paciente = new Pacientes();
            paciente.setTipo("C"); /////getListarPacientesCaja
            paciente.setId_carpeta(buscarPaciente.getId_carpeta());
            paciente.setId_paciente(buscarPaciente.getId_paciente());
            //List listarPac = this.mi.getListarPacientesCaja(paciente);  ////30-06-2016 se elimina porque se debe concetar a otra base de datos de afiliacion
            //if(listarPac.size()!=0){
            //   Pacientes datopac = (Pacientes) listarPac.get(0);
            //   modelo.put("datopacv", datopac);
            //}    
            Carpetas carpe = new Carpetas();
            carpe.setId_estado("C");
            carpe.setId_carpeta(buscarPaciente.getId_carpeta());
            carpe.setId_paciente(buscarPaciente.getId_paciente());
            // List listarC = this.mi.getListarCarpetasCaja(carpe);/////getListarCarpetasCaja tarda muuuuucho 
            //modelo.put("listaPacientesD", listarC);

            Empresas empresa = new Empresas();
            empresa.setId_empresa(buscarPaciente.getId_empresa());
            empresa.setTipo("C");
            //List listarEmp = this.mi.getListarEmpresaCaja(empresa);
            // if(listarEmp.size()!=0){
            //   Empresas datoemp = (Empresas) listarEmp.get(0);
            //   modelo.put("empresa", datoemp);    
            // }
            modelo.put("cod_esta", Integer.toString(cliente.getCod_esta()));
        }

        Cuadernos dato21 = new Cuadernos();
        dato21.setCod_esta(cliente.getCod_esta());
        dato21.setId_paciente(Integer.parseInt(id_paciente));
        dato21.setTipo("0");   ////getVerCuaderno2Ult
        Cuadernos datosC2 = this.mi.getVerCuaderno2Ult(dato21);
        modelo.put("datosC2", datosC2);

        if ("Grabar.".equals(accionx)) {
            String friesgo = request.getParameter("friesgo");
            String grupo = request.getParameter("grupo");
            String factor = request.getParameter("factor");
            Pacientes factr = new Pacientes();
            factr.setId_paciente(Integer.parseInt(id_paciente));
            factr.setFactor_riesgo(friesgo.trim());
            factr.setTipo_sanguineo(grupo.trim());
            this.mi.setModificaPacienteFact(factr);
            Pacientes buscarFact = this.mi.getDatosPaciente(Integer.parseInt(id_paciente));
            modelo.put("datos", buscarFact);
        }

        if ("Grabar".equals(accionx)) {
            String dias = request.getParameter("dia");
            String meses = request.getParameter("mes");
            String anios = request.getParameter("anio");
            String nemba = request.getParameter("nembarazo");

            int diax = Integer.parseInt(dias);
            int mesx = Integer.parseInt(meses) - 1;
            int aniox = Integer.parseInt(anios) - 1900;
            Date fum = new Date(aniox, mesx, diax);

            datosC2.setId_cuaderno(datosC2.getId_cuaderno());
            datosC2.setFum(fum);
            datosC2.setNembarazo(Integer.parseInt(nemba));
            this.mi.setModificarC2(datosC2);
        }

        if ("Laboratorio".equals(accion)) {
            Cuadernos dato = new Cuadernos();
            dato.setCod_esta(cliente.getCod_esta());  ///16-01-2016
            dato.setId_persona(Integer.parseInt(id_paciente));
            dato.setId_paciente(Integer.parseInt(id_paciente));
            dato.setTipoconsulta("A");
            List listasLab = this.mi.getPacienteResultadoLab(dato);
            List listalab1 = this.mi.getLabMedico(dato);
            dato.setTipoconsulta("D");  ////getPacienteResultadoLabEndo
            List listasLabEndo = this.mi.getPacienteResultadoLabEco(dato);
            modelo.put("listalabEndo", listasLabEndo);
            modelo.put("listalab", listalab1);
            modelo.put("milista", listasLab);
            modelo.put("estimc", estimc);

            return new ModelAndView("administrarhistoriales/LaboratorioPaciente", modelo);
        }

        if (buscarPaciente.getEmbarazo() > 0) {
            String nemba = request.getParameter("nemba");
            int semges = 0;
            double peso1 = 0.0;
            Date fecha1 = new Date();
            Cuadernos dato2 = new Cuadernos();
            dato2.setId_historial(Integer.parseInt(id_reservacion));   ///19-05-2015
            dato2.setCod_esta(cliente.getCod_esta());  ///19-05-2015
            List C2 = this.mi.getPacienteCuaderno2(dato2);
            if (datosC2 != null) {
                fumu = datosC2.getFum();
                nemba = Integer.toString(datosC2.getNembarazo());
                if (fumu != null) {
                    long fechaInicialMs = fumu.getTime();
                    long fechaFinalMs = fecha1.getTime();
                    long diferencia = fechaFinalMs - fechaInicialMs;
                    double dias = Math.floor(diferencia / (1000 * 60 * 60 * 24));
                    semges = (int) (Math.round(((float) dias) / 7));
                    modelo.put("ndias", Integer.toString((int) dias));
                    if (semges > 45) {
                        Pacientes datoemb = new Pacientes();
                        datoemb.setId_paciente(Integer.parseInt(id_paciente));
                        datoemb.setId_carpeta(Integer.parseInt(id_reservacion));
                        datoemb.setEmbarazo(0);
                        datoemb.setId_factura(3);
                        semges = 0;
                        this.mi.setModificaPacienteEmb(datoemb);
                    }
                }
            }

            modelo.put("anio_fum", Integer.toString(fumu.getYear() + 1900));
            modelo.put("mes_fum", Integer.toString(fumu.getMonth() + 1));
            modelo.put("dia_fum", Integer.toString(fumu.getDate()));
            modelo.put("semanas", Integer.toString(semges));
            modelo.put("nemba", nemba);
        }

        Historiales datohi = new Historiales();
        datohi.setId_historial(Integer.parseInt(id_reservacion));
        datohi.setCod_esta(cliente.getCod_esta());
        Historiales datosHistorial = this.mi.getDatosHistorial(datohi);
        if (datosHistorial == null) {
            modelo.put("Historia", "SinHistoria");
        }
        datohi.setAccion("R");
        Historiales datosReserva = this.mi.getDatosReserva(datohi); //getDatosReserva 
        if (datosReserva == null) {
            swreser = "si";
        }
        modelo.put("swreser", swreser);
        if (datosHistorial == null) {
            datosHistorial = new Historiales();
            datosHistorial.setEdad(buscarPaciente.getEdad());
            datosHistorial.setTalla(0);
            datosHistorial.setPeso(0);
            datosHistorial.setTemperatura(0);
            datosHistorial.setFc("0");
            datosHistorial.setPa("0/0");
            datosHistorial.setFr("0");
            datosHistorial.setSo2("0");
            datosHistorial.setGlicemia("0");
            modelo.put("datoshisto", datosReserva);
        } else {
            String sw1 = request.getParameter("sw1");
            modelo.put("datoshisto", datosHistorial);
            repetida = datosHistorial.getRepetida();

            if ("actualiza".equals(sw1)) {
                subjetivo = datosHistorial.getSubjetivo();
                objetivo = datosHistorial.getObjetivo();
                diagnostico = datosHistorial.getDiagnostico();
                miaccion = datosHistorial.getAccion();

                if (subjetivo != null) {
                    subjetivo = subjetivo.trim();
                } else {
                    subjetivo = " ";
                }
                if (objetivo != null) {
                    objetivo = objetivo.trim();
                } else {
                    objetivo = " ";
                }
                if (diagnostico != null) {
                    diagnostico = diagnostico.trim();
                } else {
                    diagnostico = " ";
                }
                if (miaccion != null) {
                    miaccion = miaccion.trim();
                } else {
                    miaccion = " ";
                }

                if (subjetivo.contains("Office") || objetivo.contains("Office") || diagnostico.contains("Office") || miaccion.contains("Office")
                        || subjetivo.contains("Word") || objetivo.contains("Word") || diagnostico.contains("Word") || miaccion.contains("Word")
                        || subjetivo.contains("font-family") || objetivo.contains("font-family") || diagnostico.contains("font-family") || miaccion.contains("font-family")) {
                    return new ModelAndView("Aviso", "mensaje", "No puede realizar copias de Word o Excel");
                }

                codigo = datosHistorial.getCodigo();
                expedido = datosHistorial.getExpedido();
            }
        }

        Consultorios datosconsulto = this.mi.getDatosConsultorio(cliente.getId_consultorio());
        if ("".equals(id_consultorio) || id_consultorio == null) {
            id_consultorio = "0";
        }

        Consultorios datosconsulta = this.mi.getDatosConsultorio(Integer.parseInt(id_consultorio)); // saca un registro a ser modificado    
        modelo.put("tipo_medico", Integer.toString(datosconsulta.getId_cargo()));
        modelo.put("especialidad", Integer.toString(datosconsulta.getId_especialidad()));

        modelo.put("estab", datoestab.getArea());

        if ("ConsultarE".equals(accion)) {
            String id_sexo_e = Integer.toString(buscarPaciente.getId_tipo_sexo());          //Sacar id_sexo

            Date fecha_nac = buscarPaciente.getFec_nacimiento();
            int xanio = fecha_nac.getYear() + 1900;
            int xmes = fecha_nac.getMonth() + 1;
            int xdia = fecha_nac.getDate();

            Sexos buscarsexo = this.mi.getDatosSexo(Integer.parseInt(id_sexo_e)); // saca un registro a ser modificado
            modelo.put("buscarSexo", buscarsexo);

            String a = "/";
            String fecha_nacimiento = Integer.toString(xdia) + a + Integer.toString(xmes) + a + Integer.toString(xanio);
            modelo.put("fec_nacimiento", fecha_nacimiento);
            modelo.put("accion", accion);
            modelo.put("sw1", request.getParameter("sw1"));
            modelo.put("id_paciente", id_paciente);
            modelo.put("fechaenf", fechaenf);
            modelo.put("id_reservacion", id_reservacion);
            modelo.put("id_consultorio", id_consultorio);
            modelo.put("id_persona", id_persona);
            modelo.put("expedido", expedido);
            return new ModelAndView("administrarhistoriales/PlanAccionPaciente", modelo);
        }

        if (datosconsulta.getId_cargo() == 3 || datosconsulta.getId_cargo() == 4 || datosconsulta.getId_cargo() == 13 || datosconsulta.getId_cargo() == 10 || datosconsulta.getId_cargo() == 15 || datosconsulta.getId_cargo() == 11 || datosconsulta.getId_cargo() == 12 || datosconsulta.getId_cargo() == 19) //|| datosconsulta.getId_cargo()==16
        {
            if (!"DiagnosticosCIE10".equals(accion)) {/////para que funcione para estadistica y pueda cambiar cie10
                Historiales elimina = new Historiales();
                elimina.setId_reservacion(Integer.parseInt(id_reservacion));
                elimina.setAccion(ip);
                elimina.setCodigo(host);
                elimina.setExpedido("A");
                elimina.setId_persona(cliente.getId_persona());
                elimina.setCod_esta(cliente.getCod_esta());
                this.mi.setEliminarReserva(elimina);

                if (datosconsulta.getId_cargo() == 13) {  ////15/04/2013
                    datosHistorial.setSubjetivo("Vacunas");
                    datosHistorial.setDiagnostico("Vacunas Enfermeria");
                }
                datosHistorial.setId_historial(Integer.parseInt(id_reservacion));
                datosHistorial.setId_persona(Integer.parseInt(id_persona));
                datosHistorial.setId_paciente(Integer.parseInt(id_paciente));
                datosHistorial.setId_consultorio(Integer.parseInt(id_consultorio));
                datosHistorial.setExpedido(expedido);
                if (datosconsulta.getId_cargo() == 15 || datosconsulta.getId_cargo() == 13 || datosconsulta.getId_cargo() == 19) {
                    datosHistorial.setCodigo("Z000");
                }

                datosHistorial.setId_seguro(buscarPaciente.getId_seguro());
                datosHistorial.setRepetida("N");
                accion = "Terminar";
            }
        }
        String id_sexo_e = Integer.toString(buscarPaciente.getId_tipo_sexo());          //Sacar id_sexo

        Date fecha_nac = buscarPaciente.getFec_nacimiento();
        int xanio = fecha_nac.getYear() + 1900;
        int xmes = fecha_nac.getMonth() + 1;
        int xdia = fecha_nac.getDate();

        //Buscar Sexo
        Sexos buscarsexo = this.mi.getDatosSexo(Integer.parseInt(id_sexo_e)); // saca un registro a ser modificado
        modelo.put("buscarSexo", buscarsexo);

        String a = "/";
        String fecha_nacimiento = Integer.toString(xdia) + a + Integer.toString(xmes) + a + Integer.toString(xanio);
        modelo.put("fec_nacimiento", fecha_nacimiento);

        modelo.put("accion", accion);
        modelo.put("sw1", request.getParameter("sw1"));
        modelo.put("id_paciente", id_paciente);
        modelo.put("fechaenf", fechaenf);
        modelo.put("id_reservacion", id_reservacion);
        modelo.put("id_consultorio", id_consultorio);
        modelo.put("id_persona", id_persona);
        modelo.put("expedido", expedido);

        modelo.put("subjetivo", subjetivo);
        modelo.put("objetivo", objetivo);
        modelo.put("diagnostico", diagnostico);
        modelo.put("miaccion", miaccion);
        modelo.put("repetida", repetida);
        modelo.put("codigo", codigo);
        modelo.put("literal", literal);

        Historiales datomorbi = new Historiales();
        datomorbi.setId_reservacion(Integer.parseInt(id_reservacion));
        datomorbi.setCod_esta(cliente.getCod_esta());
        List listarmorbi1 = this.mi.getListaMorbi(datomorbi);
        modelo.put("cont", Integer.toString(listarmorbi1.size()));
        modelo.put("morbi", listarmorbi1);

        if (listarmorbi1.isEmpty() != true) {
            modelo.put("terminar", "si");
        }

        if ("Elegir".equals(accion)) {
            codigo = request.getParameter("ubicacion");
            literal = request.getParameter("concentra");
            int w = 0;
            int ws = 0;

            for (int i = 0; i < listarmorbi1.size(); i++) {
                Historiales datopac = (Historiales) listarmorbi1.get(i);
                if (i + 1 != datopac.getId_cargo() && w == 0) {
                    w = i + 1;
                }
                if (codigo.equals(datopac.getNombres())) {
                    ws = 1;
                }
            }
            if (ws == 1) {
                return new ModelAndView("Aviso", "mensaje", "Este Codigo CIE10 ya fue seleccionado");
            }
            Historiales morbi = new Historiales();
            morbi.setId_historial(Integer.parseInt(id_reservacion));
            morbi.setId_persona(cliente.getId_persona());
            if (cliente.getId_especialidad() == 99) {
                morbi.setId_persona(Integer.parseInt(id_persona));
            }
            morbi.setCodigo(codigo);
            morbi.setTipo("A");
            morbi.setCod_esta(cliente.getCod_esta());
            if (w == 0) {
                morbi.setId_cargo(1 + listarmorbi1.size());
            } else {
                morbi.setId_cargo(w);
            }

            this.mi.setCrearMorbilidad(morbi);

            //Historiales datomorbi= new Historiales();  
            datomorbi.setId_reservacion(Integer.parseInt(id_reservacion));
            datomorbi.setCod_esta(cliente.getCod_esta());
            List listarmorbi2 = this.mi.getListaMorbi(datomorbi);
            modelo.put("morbi", listarmorbi2);
            modelo.put("cont", Integer.toString(listarmorbi2.size()));
            if (listarmorbi2.size() == 1) {
                datosHistorial.setCodigo(codigo);
                datosHistorial.setCod_esta(cliente.getCod_esta());
                datosHistorial.setId_por(cliente.getId_persona());
                int iResultado = this.mi.setRegistrarHistorial(datosHistorial);
            }

            if (listarmorbi2.isEmpty() != true) {
                modelo.put("terminar", "si");
            }
            modelo.put("cont", Integer.toString(listarmorbi1.size() + 1));
            return new ModelAndView("administrarhistoriales/AtenderPaciente3", modelo);
        }

        if ("Buscar Enfermedad".equals(boton)) {
            literal= nombres;
            nombres = nombres.replaceAll("\\s", "%");
            nombres = "%" + nombres + "%";
            List listarEnfermedades = this.mi.getListarEnfermedades(nombres);
            modelo.put("listarEnfermedades", listarEnfermedades);
            modelo.put("estimc", estimc);
            modelo.put("literal", literal);
            modelo.put("codigo", "");
            return new ModelAndView("administrarhistoriales/AtenderPaciente3", modelo);
        }

        if ("BuscarE".equals(boton)) {
            String nombres2 = request.getParameter("nombres2");
            String nombres3 = request.getParameter("nombres3");
            String nombres4 = request.getParameter("nombres4");
            if (nombres2 == null) {
                nombres2 = "%";
            }
            if (nombres3 == null) {
                nombres3 = "%";
            }
            if (nombres4 == null) {
                nombres4 = "%";
            }
            nombres = nombres2 + "%" + nombres3 + "%" + nombres4;
            List listarEnfermedades = this.mi.getListarEnfermedadesOtra(nombres);
            modelo.put("listarEnfermedades", listarEnfermedades);
            modelo.put("estimc", estimc);
            modelo.put("literal2", nombres2);
            modelo.put("literal3", nombres3);
            modelo.put("literal4", nombres4);
            return new ModelAndView("administrarhistoriales/AtenderPaciente3", modelo);
        }

        if ("CambioCie".equals(boton)) {
            String general = request.getParameter("general");
            String codigo3 = request.getParameter("ubicacion");
            modelo.put("codigo", codigo3);
            modelo.put("literal5", general);
            modelo.put("cambiocie", "1");
            return new ModelAndView("administrarhistoriales/AtenderPaciente3", modelo);
        }

        if ("GrabarCie".equals(boton)) {
            String general2 = request.getParameter("nombres5");
            String codigo3 = request.getParameter("cie10");
            modelo.put("codigo", codigo3);
            modelo.put("literal5", general2);
            Medicamentos datocie = new Medicamentos();
            datocie.setCodsumi(codigo3);
            datocie.setForma_far(general2);
            this.mi.setModificarCie10(datocie);
            return new ModelAndView("administrarhistoriales/AtenderPaciente3", modelo);
        }

        if ("Buscar CIE10".equals(boton)) {
            List listarEnfermedades = this.mi.getListarEnfermedadesCod(nombres+"%");
            modelo.put("listarEnfermedades", listarEnfermedades);
            modelo.put("estimc", estimc);
            modelo.put("literal", "");
            return new ModelAndView("administrarhistoriales/AtenderPaciente3", modelo);
        }

        if ("Historial".equals(accion)) {
            Historiales dato = new Historiales();
            dato.setId_paciente(Integer.parseInt(id_paciente));
            dato.setCod_esta(cliente.getCod_esta());
            List listas = this.mi.getListarHistoria(dato);
            modelo.put("milista", listas);
            if (datoestab.getCompartehcl() == 1) {  //////1 comparte la historia clinica
                dato.setAccion("T");////getListarHistoriaTodo
                List listas2 = this.mi.getListarHistoriaTodo(dato);
                modelo.put("milista", listas2);
            }

            modelo.put("estimc", estimc);
            dato.setCod_esta(cliente.getCod_esta());
            dato.setAccion("V");////getListarHistoriaVig
            List listasvig = this.mi.getListarHistoria(dato);
            modelo.put("listavig", listasvig);
            List listarSeguros = this.mi.getListarSeguros("A");
            modelo.put("listaPacAfi", listarSeguros);
            return new ModelAndView("administrarhistoriales/HistoriaPaciente", modelo);
        }

        //if("Grabar Modificaciones".equals(accion) || "Crear Nuevo Registro".equals(accion) ) {
        if ("Siguientee".equals(accion) || "Guardarr".equals(accion)) {
            String id_historia = request.getParameter("id_historia");
            String antpato = request.getParameter("antpato");
            String suaccion = request.getParameter("suaccion");
            String antfami = request.getParameter("antfami");
            String antgineco = request.getParameter("antgineco");
            String historiaenfer = request.getParameter("historiaenfer");
            String evolucion = request.getParameter("evolucion");
            String conducta = request.getParameter("conducta");
            String condicion = request.getParameter("condicion");
            String observacion = request.getParameter("observacion");
            String paccion = request.getParameter("paccion");
            String pdiagnostico = request.getParameter("pdiagnostico");
            String introduccion = request.getParameter("introduccion");
            String ant_nopato = request.getParameter("ant_nopato");
            String fisico_cabeza = request.getParameter("fisico_cabeza");
            String fisico_cara = request.getParameter("fisico_cara");
            String fisico_cuello = request.getParameter("fisico_cuello");
            String fisico_torax = request.getParameter("fisico_torax");
            String fisico_corazon = request.getParameter("fisico_corazon");
            String fisico_pulmon = request.getParameter("fisico_pulmon");
            String fisico_abdomen = request.getParameter("fisico_abdomen");
            String fisico_extrem = request.getParameter("fisico_extrem");
            String fisico_genito = request.getParameter("fisico_genito");
            String fisico_neuro = request.getParameter("fisico_neuro");

            if (id_historia == null || "".equals(id_historia)) {
                id_historia = "0";
            }
            Historiales datohis = new Historiales();
            datohis.setId_historial(Integer.parseInt(id_reservacion));
            datohis.setId_consultorio(Integer.parseInt(id_consultorio));
            datohis.setId_historia(Integer.parseInt(id_historia));
            datohis.setId_persona(Integer.parseInt(id_persona));
            if (cliente.getId_especialidad() != 99) {
                datohis.setId_persona(cliente.getId_persona());
                datohis.setId_consultorio(cliente.getId_consultorio());
            }
            datohis.setCod_esta(cliente.getCod_esta());
            datohis.setAccion("O");   ///////getDatosEmergenciaSolo
            datohis.setId_tipo(1);
            if ("Historia Clinica".equals(suaccion)) {
                datohis.setId_tipo(2);
            }
            if ("Nota Internacion".equals(suaccion)) {
                datohis.setId_tipo(3);
            }
            Historiales datosEmerg = this.mi.getDatosEmergenciaSolo(datohis);   ////getDatosEmergenciaSolo

            if (datosEmerg != null) {////si no esta vacio la tabla

                if (antgineco == null) {
                    antgineco = datosEmerg.getPa();
                } else {
                    antgineco = antgineco.trim();
                }
                if (historiaenfer == null) {
                    historiaenfer = datosEmerg.getFc();
                } else {
                    historiaenfer = historiaenfer.trim();
                }
                if (evolucion == null) {
                    evolucion = datosEmerg.getRegistro();
                } else {
                    evolucion = evolucion.trim();
                }
                if (conducta == null) {
                    conducta = datosEmerg.getEstimc();
                } else {
                    conducta = conducta.trim();
                }
                if (condicion == null) {
                    condicion = datosEmerg.getFr();
                } else {
                    condicion = condicion.trim();
                }
                if (observacion == null) {
                    observacion = datosEmerg.getCadena15();
                } else {
                    observacion = observacion.trim();
                }
                if (introduccion == null) {
                    introduccion = datosEmerg.getCadena1();
                } else {
                    introduccion = introduccion.trim();
                }
                if (ant_nopato == null) {
                    ant_nopato = datosEmerg.getCadena2();
                } else {
                    ant_nopato = ant_nopato.trim();
                }
                if (fisico_cabeza == null) {
                    fisico_cabeza = datosEmerg.getCadena3();
                } else {
                    fisico_cabeza = fisico_cabeza.trim();
                }
                if (fisico_cara == null) {
                    fisico_cara = datosEmerg.getCadena4();
                } else {
                    fisico_cara = fisico_cara.trim();
                }
                if (fisico_cuello == null) {
                    fisico_cuello = datosEmerg.getCadena5();
                } else {
                    fisico_cuello = fisico_cuello.trim();
                }
                if (fisico_torax == null) {
                    fisico_torax = datosEmerg.getCadena6();
                } else {
                    fisico_torax = fisico_torax.trim();
                }
                if (fisico_corazon == null) {
                    fisico_corazon = datosEmerg.getCadena7();
                } else {
                    fisico_corazon = fisico_corazon.trim();
                }
                if (fisico_pulmon == null) {
                    fisico_pulmon = datosEmerg.getCadena8();
                } else {
                    fisico_pulmon = fisico_pulmon.trim();
                }
                if (fisico_abdomen == null) {
                    fisico_abdomen = datosEmerg.getCadena9();
                } else {
                    fisico_abdomen = fisico_abdomen.trim();
                }
                if (fisico_extrem == null) {
                    fisico_extrem = datosEmerg.getCadena10();
                } else {
                    fisico_extrem = fisico_extrem.trim();
                }
                if (fisico_genito == null) {
                    fisico_genito = datosEmerg.getCadena11();
                } else {
                    fisico_genito = fisico_genito.trim();
                }
                if (fisico_neuro == null) {
                    fisico_neuro = datosEmerg.getCadena12();
                } else {
                    fisico_neuro = fisico_neuro.trim();
                }
                if (antpato == null) {
                    antpato = datosEmerg.getCadena13();
                } else {
                    antpato = antpato.trim();
                }
                if (antfami == null) {
                    antfami = datosEmerg.getCadena14();
                } else {
                    antfami = antfami.trim();
                }
                if (paccion == null) {
                    paccion = datosHistorial.getAccion();
                } else {
                    paccion = paccion.trim();
                }
            } else {
                if (antpato != null) {
                    antpato = antpato.trim();
                } else {
                    antpato = " ";
                }
                if (antfami != null) {
                    antfami = antfami.trim();
                } else {
                    antfami = " ";
                }
                if (antgineco != null) {
                    antgineco = antgineco.trim();
                } else {
                    antgineco = " ";
                }
                if (historiaenfer != null) {
                    historiaenfer = historiaenfer.trim();
                } else {
                    historiaenfer = " ";
                }
                if (evolucion != null) {
                    evolucion = evolucion.trim();
                } else {
                    evolucion = " ";
                }
                if (conducta != null) {
                    conducta = conducta.trim();
                } else {
                    conducta = " ";
                }
                if (condicion != null) {
                    condicion = condicion.trim();
                } else {
                    condicion = " ";
                }
                if (observacion != null) {
                    observacion = observacion.trim();
                } else {
                    observacion = " ";
                }
                if (introduccion != null) {
                    introduccion = introduccion.trim();
                } else {
                    introduccion = " ";
                }
                if (ant_nopato != null) {
                    ant_nopato = ant_nopato.trim();
                } else {
                    ant_nopato = " ";
                }
                if (fisico_cabeza != null) {
                    fisico_cabeza = fisico_cabeza.trim();
                } else {
                    fisico_cabeza = " ";
                }
                if (fisico_cara != null) {
                    fisico_cara = fisico_cara.trim();
                } else {
                    fisico_cara = " ";
                }
                if (fisico_cuello != null) {
                    fisico_cuello = fisico_cuello.trim();
                } else {
                    fisico_cuello = " ";
                }
                if (fisico_torax != null) {
                    fisico_torax = fisico_torax.trim();
                } else {
                    fisico_torax = " ";
                }
                if (fisico_corazon != null) {
                    fisico_corazon = fisico_corazon.trim();
                } else {
                    fisico_corazon = " ";
                }
                if (fisico_pulmon != null) {
                    fisico_pulmon = fisico_pulmon.trim();
                } else {
                    fisico_pulmon = " ";
                }
                if (fisico_abdomen != null) {
                    fisico_abdomen = fisico_abdomen.trim();
                } else {
                    fisico_abdomen = " ";
                }
                if (fisico_extrem != null) {
                    fisico_extrem = fisico_extrem.trim();
                } else {
                    fisico_extrem = " ";
                }
                if (fisico_genito != null) {
                    fisico_genito = fisico_genito.trim();
                } else {
                    fisico_genito = " ";
                }
                if (fisico_neuro != null) {
                    fisico_neuro = fisico_neuro.trim();
                } else {
                    fisico_neuro = " ";
                }
                if (paccion != null) {
                    paccion = paccion.trim();
                } else {
                    paccion = " ";
                }
            }

            Historiales reserva = new Historiales();
            reserva.setCod_esta(cliente.getCod_esta());
            reserva.setId_historial(Integer.parseInt(id_reservacion));
            reserva.setId_historia(Integer.parseInt(id_historia));
            reserva.setId_consultorio(Integer.parseInt(id_consultorio));
            reserva.setId_persona(Integer.parseInt(id_persona));
            if (cliente.getId_especialidad() != 99) {
                reserva.setId_persona(cliente.getId_persona());
                reserva.setId_consultorio(cliente.getId_consultorio());
            }
            reserva.setPa(antgineco);
            reserva.setFc(historiaenfer);
            reserva.setRegistro(evolucion);
            reserva.setEstimc(conducta);
            reserva.setFr(condicion);
            //reserva.setNit(observacion);
            reserva.setDiagnostico(diagnostico);
            reserva.setSubjetivo(subjetivo);
            reserva.setObjetivo(objetivo);
            reserva.setCadena1(introduccion);///plan accion
            reserva.setCadena2(ant_nopato);///plan accion
            reserva.setCadena3(fisico_cabeza);///plan accion
            reserva.setCadena4(fisico_cara);///plan accion
            reserva.setCadena5(fisico_cuello);///plan accion
            reserva.setCadena6(fisico_torax);///plan accion
            reserva.setCadena7(fisico_pulmon);///plan accion
            reserva.setCadena8(fisico_corazon);///plan accion
            reserva.setCadena9(fisico_abdomen);///plan accion
            reserva.setCadena10(fisico_extrem);///plan accion
            reserva.setCadena11(fisico_genito);///plan accion
            reserva.setCadena12(fisico_neuro);///plan accion
            reserva.setCadena13(antpato);
            reserva.setCadena14(antfami);
            reserva.setCadena15(observacion);
            reserva.setCadena20(paccion);///plan accion
            reserva.setCadena21(pdiagnostico);///plan accion
            reserva.setCod_esta(cliente.getCod_esta());
            reserva.setId_por(cliente.getId_persona());
            reserva.setId_tipo(1);
            reserva.setAccion("E");////para emergencias
            if ("Nota Internacion".equals(suaccion)) {
                reserva.setId_tipo(3);
            }
            if ("Historia Clinica".equals(suaccion)) {
                reserva.setId_tipo(2);
            }

            if (cliente.getId_especialidad() == 99) {
                reserva.setId_persona(datosHistorial.getId_persona());
            }
            if (datosEmerg == null) {
                this.mi.setCrearInternadoEmerg(reserva);   /////setCrearInternadoEmerg 
            } else {
                this.mi.setModificarEmergencias(reserva);  /////setModificarEmergencias
            }

            //datohis.setId_historia(Integer.parseInt(id_historia)) ; //// busca el ultimo historiaemerg creado para mostrarlo 29-10-2016
            datohis.setAccion("F");
            Historiales datosEmergw = this.mi.getDatosEmergenciaUltimo(datohis);   ////getDatosEmergenciaUltimo
            datohis.setAccion("O");
            datohis.setId_historia(datosEmergw.getId_historia());
            Historiales datosEmerge = this.mi.getDatosEmergenciaSolo(datohis);   ////getDatosEmergenciaSolo

            modelo.put("antpato", datosEmerge.getCadena13());
            modelo.put("antfami", datosEmerge.getCadena14());
            modelo.put("antgineco", datosEmerge.getPa());
            modelo.put("historiaenfer", datosEmerge.getFc());
            modelo.put("evolucion", datosEmerge.getRegistro());
            modelo.put("conducta", datosEmerge.getEstimc());
            modelo.put("condicion", datosEmerge.getFr());
            modelo.put("cadena1", datosEmerge.getCadena1());
            modelo.put("cadena2", datosEmerge.getCadena2());
            modelo.put("cadena3", datosEmerge.getCadena3());
            modelo.put("cadena4", datosEmerge.getCadena4());
            modelo.put("cadena5", datosEmerge.getCadena5());
            modelo.put("cadena6", datosEmerge.getCadena6());
            modelo.put("cadena7", datosEmerge.getCadena7());
            modelo.put("cadena8", datosEmerge.getCadena8());
            modelo.put("cadena9", datosEmerge.getCadena9());
            modelo.put("cadena10", datosEmerge.getCadena10());
            modelo.put("cadena11", datosEmerge.getCadena11());
            modelo.put("cadena12", datosEmerge.getCadena12());
            modelo.put("observacion", datosEmerge.getCadena15());
            modelo.put("miaccion", datosEmerge.getCadena20());
            modelo.put("diagnostico", datosEmerge.getCadena21());
            modelo.put("id_historia", Integer.toString(datosEmergw.getId_historia()));

            //if("1".equals(swemerg)) {
            //  if("inter".equals(swinter)) {
            //     return new ModelAndView("administrarhistoriales/AtenderEmergencias", modelo);        
            //  }else{
            //      return new ModelAndView("administrarhistoriales/PlanAccionPaciente", modelo);        
            //   }
            // }
            Historiales dato = new Historiales();
            dato.setCod_esta(cliente.getCod_esta());////// creado obrero 25/08/2015
            dato.setId_historial(Integer.parseInt(id_reservacion));
            dato.setAccion("E");
            dato.setId_tipo(1);
            if ("Nota Internacion".equals(suaccion)) {
                dato.setId_tipo(3);
            }
            if ("Historia Clinica".equals(suaccion)) {
                dato.setId_tipo(2);
            }
            List listaemerg = this.mi.getListarHistoriaEmergen(dato);
            modelo.put("listaemerg", listaemerg);

            if ("Nota Internacion".equals(suaccion)) {
                return new ModelAndView("administrarhistoriales/AtenderNotaInter", modelo);
            }
            if ("Historia Clinica".equals(suaccion)) {
                return new ModelAndView("administrarhistoriales/AtenderHCL", modelo);
            }
            return new ModelAndView("administrarhistoriales/AtenderEmergencias", modelo);

            //if("1".equals(swemerg)) {
            //   return new ModelAndView("administrarhistoriales/AtenderEmergencias", modelo);            
            //}
        }

        if ("HCL Emergencias".equals(accion) || "Historia Clinica".equals(accion) || "Nota Internacion".equals(accion)) {
            String id_historia = request.getParameter("id_historia");
            String id_historial = request.getParameter("id_historial");
            String cod_esta = request.getParameter("cod_esta");

            Historiales datohis = new Historiales();
            datohis.setId_historial(Integer.parseInt(id_historial)); ///id_reservacion
            datohis.setCod_esta(cliente.getCod_esta());
            if (cod_esta != null && !"".equals(cod_esta)) {
                datohis.setCod_esta(Integer.parseInt(cod_esta));
            }
            datohis.setAccion("E");
            if ("HCL Emergencias".equals(accion)) {
                datohis.setId_tipo(1);
            }
            if ("Historia Clinica".equals(accion)) {
                datohis.setId_tipo(2);
            }
            if ("Nota Internacion".equals(accion)) {
                datohis.setId_tipo(3);
            }

            List listaemerg = this.mi.getListarHistoriaEmergen(datohis); ///getListarHistoriaEmergen
            if (!listaemerg.isEmpty()) {///tiene datos
                Historiales datoh = (Historiales) listaemerg.get(0);
                datohis.setId_historia(datoh.getId_historia());
                modelo.put("id_historia", Integer.toString(datoh.getId_historia()));
            }
            if (id_historia != null) {
                datohis.setId_historia(Integer.parseInt(id_historia));
                modelo.put("id_historia", id_historia);
            }
            datohis.setAccion("I");  ////getDatosEmergencia
            Historiales datosEmerg = this.mi.getDatosEmergencia(datohis);////getDatosEmergencia

            datosHistorial.setDiagnostico(datosHistorial.getDiagnostico().replaceAll("<p>", " "));
            datosHistorial.setDiagnostico(datosHistorial.getDiagnostico().replaceAll("</p>", " "));
            datosHistorial.setDiagnostico(datosHistorial.getDiagnostico().replaceAll("&nbsp;", ""));
            datosHistorial.setDiagnostico(datosHistorial.getDiagnostico().replaceAll("<strong>", " "));
            datosHistorial.setDiagnostico(datosHistorial.getDiagnostico().replaceAll("</strong>", " "));
            datosHistorial.setDiagnostico(datosHistorial.getDiagnostico().replaceAll("<br />", " "));
            datosHistorial.setDiagnostico(datosHistorial.getDiagnostico().replaceAll("<u>", " "));
            datosHistorial.setDiagnostico(datosHistorial.getDiagnostico().replaceAll("</u>", " "));
            datosHistorial.setDiagnostico(datosHistorial.getDiagnostico().replaceAll("<ul>", " "));
            datosHistorial.setDiagnostico(datosHistorial.getDiagnostico().replaceAll("</ul>", " "));
            datosHistorial.setDiagnostico(datosHistorial.getDiagnostico().replaceAll("<ol>", " "));
            datosHistorial.setDiagnostico(datosHistorial.getDiagnostico().replaceAll("</ol>", " "));
            datosHistorial.setDiagnostico(datosHistorial.getDiagnostico().replaceAll("<li>", " "));
            datosHistorial.setDiagnostico(datosHistorial.getDiagnostico().replaceAll("</li>", " "));
            datosHistorial.setDiagnostico(datosHistorial.getDiagnostico().replaceAll("&ntilde;", "n"));
            datosHistorial.setDiagnostico(datosHistorial.getDiagnostico().replaceAll("&Ntilde;", "N"));
            datosHistorial.setDiagnostico(datosHistorial.getDiagnostico().replaceAll("&Aacute;", "A"));
            datosHistorial.setDiagnostico(datosHistorial.getDiagnostico().replaceAll("&Eacute;", "E"));
            datosHistorial.setDiagnostico(datosHistorial.getDiagnostico().replaceAll("&Iacute;", "I"));
            datosHistorial.setDiagnostico(datosHistorial.getDiagnostico().replaceAll("&Oacute;", "O"));
            datosHistorial.setDiagnostico(datosHistorial.getDiagnostico().replaceAll("&Uacute;", "U"));
            datosHistorial.setDiagnostico(datosHistorial.getDiagnostico().replaceAll("&aacute;", "a"));
            datosHistorial.setDiagnostico(datosHistorial.getDiagnostico().replaceAll("&eacute;", "e"));
            datosHistorial.setDiagnostico(datosHistorial.getDiagnostico().replaceAll("&iacute;", "i"));
            datosHistorial.setDiagnostico(datosHistorial.getDiagnostico().replaceAll("&oacute;", "o"));
            datosHistorial.setDiagnostico(datosHistorial.getDiagnostico().replaceAll("&uacute;", "u"));
            datosHistorial.setDiagnostico(datosHistorial.getDiagnostico().replaceAll("&quot;", "'"));
            modelo.put("diagnostico", datosHistorial.getDiagnostico());
            datosHistorial.setSubjetivo(datosHistorial.getSubjetivo().replaceAll("<p>", " "));
            datosHistorial.setSubjetivo(datosHistorial.getSubjetivo().replaceAll("</p>", " "));
            datosHistorial.setSubjetivo(datosHistorial.getSubjetivo().replaceAll("&nbsp;", ""));
            datosHistorial.setSubjetivo(datosHistorial.getSubjetivo().replaceAll("<strong>", " "));
            datosHistorial.setSubjetivo(datosHistorial.getSubjetivo().replaceAll("</strong>", " "));
            datosHistorial.setSubjetivo(datosHistorial.getSubjetivo().replaceAll("<br />", " "));
            datosHistorial.setSubjetivo(datosHistorial.getSubjetivo().replaceAll("<u>", " "));
            datosHistorial.setSubjetivo(datosHistorial.getSubjetivo().replaceAll("</u>", " "));
            datosHistorial.setSubjetivo(datosHistorial.getSubjetivo().replaceAll("<ul>", " "));
            datosHistorial.setSubjetivo(datosHistorial.getSubjetivo().replaceAll("</ul>", " "));
            datosHistorial.setSubjetivo(datosHistorial.getSubjetivo().replaceAll("<ol>", " "));
            datosHistorial.setSubjetivo(datosHistorial.getSubjetivo().replaceAll("</ol>", " "));
            datosHistorial.setSubjetivo(datosHistorial.getSubjetivo().replaceAll("<li>", " "));
            datosHistorial.setSubjetivo(datosHistorial.getSubjetivo().replaceAll("</li>", " "));
            datosHistorial.setSubjetivo(datosHistorial.getSubjetivo().replaceAll("&ntilde;", "n"));
            datosHistorial.setSubjetivo(datosHistorial.getSubjetivo().replaceAll("&Ntilde;", "N"));
            datosHistorial.setSubjetivo(datosHistorial.getSubjetivo().replaceAll("&Aacute;", "A"));
            datosHistorial.setSubjetivo(datosHistorial.getSubjetivo().replaceAll("&Eacute;", "E"));
            datosHistorial.setSubjetivo(datosHistorial.getSubjetivo().replaceAll("&Iacute;", "I"));
            datosHistorial.setSubjetivo(datosHistorial.getSubjetivo().replaceAll("&Oacute;", "O"));
            datosHistorial.setSubjetivo(datosHistorial.getSubjetivo().replaceAll("&Uacute;", "U"));
            datosHistorial.setSubjetivo(datosHistorial.getSubjetivo().replaceAll("&aacute;", "a"));
            datosHistorial.setSubjetivo(datosHistorial.getSubjetivo().replaceAll("&eacute;", "e"));
            datosHistorial.setSubjetivo(datosHistorial.getSubjetivo().replaceAll("&iacute;", "i"));
            datosHistorial.setSubjetivo(datosHistorial.getSubjetivo().replaceAll("&oacute;", "o"));
            datosHistorial.setSubjetivo(datosHistorial.getSubjetivo().replaceAll("&uacute;", "u"));
            datosHistorial.setSubjetivo(datosHistorial.getSubjetivo().replaceAll("&quot;", "'"));
            modelo.put("subjetivo", datosHistorial.getSubjetivo());
            datosHistorial.setObjetivo(datosHistorial.getObjetivo().replaceAll("<p>", " "));
            datosHistorial.setObjetivo(datosHistorial.getObjetivo().replaceAll("</p>", " "));
            datosHistorial.setObjetivo(datosHistorial.getObjetivo().replaceAll("&nbsp;", ""));
            datosHistorial.setObjetivo(datosHistorial.getObjetivo().replaceAll("<strong>", " "));
            datosHistorial.setObjetivo(datosHistorial.getObjetivo().replaceAll("</strong>", " "));
            datosHistorial.setObjetivo(datosHistorial.getObjetivo().replaceAll("<br />", " "));
            datosHistorial.setObjetivo(datosHistorial.getObjetivo().replaceAll("<u>", " "));
            datosHistorial.setObjetivo(datosHistorial.getObjetivo().replaceAll("</u>", " "));
            datosHistorial.setObjetivo(datosHistorial.getObjetivo().replaceAll("<ul>", " "));
            datosHistorial.setObjetivo(datosHistorial.getObjetivo().replaceAll("</ul>", " "));
            datosHistorial.setObjetivo(datosHistorial.getObjetivo().replaceAll("<ol>", " "));
            datosHistorial.setObjetivo(datosHistorial.getObjetivo().replaceAll("</ol>", " "));
            datosHistorial.setObjetivo(datosHistorial.getObjetivo().replaceAll("<li>", " "));
            datosHistorial.setObjetivo(datosHistorial.getObjetivo().replaceAll("</li>", " "));
            datosHistorial.setObjetivo(datosHistorial.getObjetivo().replaceAll("&ntilde;", "n"));
            datosHistorial.setObjetivo(datosHistorial.getObjetivo().replaceAll("&Ntilde;", "N"));
            datosHistorial.setObjetivo(datosHistorial.getObjetivo().replaceAll("&Aacute;", "A"));
            datosHistorial.setObjetivo(datosHistorial.getObjetivo().replaceAll("&Eacute;", "E"));
            datosHistorial.setObjetivo(datosHistorial.getObjetivo().replaceAll("&Iacute;", "I"));
            datosHistorial.setObjetivo(datosHistorial.getObjetivo().replaceAll("&Oacute;", "O"));
            datosHistorial.setObjetivo(datosHistorial.getObjetivo().replaceAll("&Uacute;", "U"));
            datosHistorial.setObjetivo(datosHistorial.getObjetivo().replaceAll("&aacute;", "a"));
            datosHistorial.setObjetivo(datosHistorial.getObjetivo().replaceAll("&eacute;", "e"));
            datosHistorial.setObjetivo(datosHistorial.getObjetivo().replaceAll("&iacute;", "i"));
            datosHistorial.setObjetivo(datosHistorial.getObjetivo().replaceAll("&oacute;", "o"));
            datosHistorial.setObjetivo(datosHistorial.getObjetivo().replaceAll("&uacute;", "u"));
            datosHistorial.setObjetivo(datosHistorial.getObjetivo().replaceAll("&quot;", "'"));
            modelo.put("objetivo", datosHistorial.getObjetivo());
            datosHistorial.setAccion(datosHistorial.getAccion().replaceAll("<p>", " "));
            datosHistorial.setAccion(datosHistorial.getAccion().replaceAll("</p>", " "));
            datosHistorial.setAccion(datosHistorial.getAccion().replaceAll("&nbsp;", ""));
            datosHistorial.setAccion(datosHistorial.getAccion().replaceAll("<strong>", " "));
            datosHistorial.setAccion(datosHistorial.getAccion().replaceAll("</strong>", " "));
            datosHistorial.setAccion(datosHistorial.getAccion().replaceAll("<br />", " "));
            datosHistorial.setAccion(datosHistorial.getAccion().replaceAll("<u>", " "));
            datosHistorial.setAccion(datosHistorial.getAccion().replaceAll("</u>", " "));
            datosHistorial.setAccion(datosHistorial.getAccion().replaceAll("<ul>", " "));
            datosHistorial.setAccion(datosHistorial.getAccion().replaceAll("</ul>", " "));
            datosHistorial.setAccion(datosHistorial.getAccion().replaceAll("<ol>", " "));
            datosHistorial.setAccion(datosHistorial.getAccion().replaceAll("</ol>", " "));
            datosHistorial.setAccion(datosHistorial.getAccion().replaceAll("<li>", " "));
            datosHistorial.setAccion(datosHistorial.getAccion().replaceAll("</li>", " "));
            datosHistorial.setAccion(datosHistorial.getAccion().replaceAll("&ntilde;", "n"));
            datosHistorial.setAccion(datosHistorial.getAccion().replaceAll("&Ntilde;", "N"));
            datosHistorial.setAccion(datosHistorial.getAccion().replaceAll("&Aacute;", "A"));
            datosHistorial.setAccion(datosHistorial.getAccion().replaceAll("&Eacute;", "E"));
            datosHistorial.setAccion(datosHistorial.getAccion().replaceAll("&Iacute;", "I"));
            datosHistorial.setAccion(datosHistorial.getAccion().replaceAll("&Oacute;", "O"));
            datosHistorial.setAccion(datosHistorial.getAccion().replaceAll("&Uacute;", "U"));
            datosHistorial.setAccion(datosHistorial.getAccion().replaceAll("&aacute;", "a"));
            datosHistorial.setAccion(datosHistorial.getAccion().replaceAll("&eacute;", "e"));
            datosHistorial.setAccion(datosHistorial.getAccion().replaceAll("&iacute;", "i"));
            datosHistorial.setAccion(datosHistorial.getAccion().replaceAll("&oacute;", "o"));
            datosHistorial.setAccion(datosHistorial.getAccion().replaceAll("&uacute;", "u"));
            datosHistorial.setAccion(datosHistorial.getAccion().replaceAll("&quot;", "'"));
            modelo.put("miaccion", datosHistorial.getAccion());

            if (datosEmerg != null) {
                modelo.put("antpato", datosEmerg.getCadena13());
                modelo.put("antfami", datosEmerg.getCadena14());
                modelo.put("antgineco", datosEmerg.getPa());
                modelo.put("historiaenfer", datosEmerg.getFc());
                modelo.put("evolucion", datosEmerg.getRegistro());
                modelo.put("conducta", datosEmerg.getEstimc());
                modelo.put("condicion", datosEmerg.getFr());
                modelo.put("observacion", datosEmerg.getCadena15());
                modelo.put("cadena1", datosEmerg.getCadena1());
                modelo.put("cadena2", datosEmerg.getCadena2());
                modelo.put("cadena3", datosEmerg.getCadena3());
                modelo.put("cadena4", datosEmerg.getCadena4());
                modelo.put("cadena5", datosEmerg.getCadena5());
                modelo.put("cadena6", datosEmerg.getCadena6());
                modelo.put("cadena7", datosEmerg.getCadena7());
                modelo.put("cadena8", datosEmerg.getCadena8());
                modelo.put("cadena9", datosEmerg.getCadena9());
                modelo.put("cadena10", datosEmerg.getCadena10());
                modelo.put("cadena11", datosEmerg.getCadena11());
                modelo.put("cadena12", datosEmerg.getCadena12());
                modelo.put("miaccion", datosEmerg.getCadena20());
                modelo.put("diagnostico", datosEmerg.getCadena21());
            }

            Historiales dato = new Historiales();
            dato.setCod_esta(cliente.getCod_esta());////// creado obrero 29/03/2016
            dato.setId_historial(Integer.parseInt(id_reservacion));
            dato.setAccion("G");
            if ("HCL Emergencias".equals(accion)) {
                dato.setId_tipo(1);
            }
            if ("Historia Clinica".equals(accion)) {
                dato.setId_tipo(2);
            }
            if ("Nota Internacion".equals(accion)) {
                dato.setId_tipo(3);
            }
            List listaemerg2 = this.mi.getListarHistoriaEmergen(dato); ///getListarHistoriaEmergen
            modelo.put("listaemerg", listaemerg2);

            List listarSeguros = this.mi.getListarSeguros("A");
            modelo.put("listaPacAfi", listarSeguros);
            modelo.put("accion", "Siguientee");
            modelo.put("suaccion", accion);
            modelo.put("sw", sw);
            if ("Nota Internacion".equals(accion)) {
                return new ModelAndView("administrarhistoriales/AtenderNotaInter", modelo);
            }
            if ("Historia Clinica".equals(accion)) {
                return new ModelAndView("administrarhistoriales/AtenderHCL", modelo);
            }
            return new ModelAndView("administrarhistoriales/AtenderEmergencias", modelo);
        }

        if ("Oftalmologia".equals(accion)) {
            Historiales dato = new Historiales();
            dato.setId_paciente(Integer.parseInt(id_paciente));
            List listas = this.mi.getListarHistoria(dato);
            modelo.put("milista", listas);
            modelo.put("estimc", estimc);
            List listarSeguros = this.mi.getListarSeguros("A");
            modelo.put("listaPacAfi", listarSeguros);
            return new ModelAndView("administrarhistoriales/HistoriaOftalmologia", modelo);
        }

        Cuadernos dato2 = new Cuadernos();
        dato2.setCod_esta(cliente.getCod_esta());  ///16-01-2016
        dato2.setId_paciente(Integer.parseInt(id_paciente));
        List listac2 = this.mi.getVerCuaderno2Uni(dato2);
        modelo.put("listac2", listac2);

        if ("Perinatal".equals(accionperi)) {
            modelo.put("anio_r", Integer.toString(datosC2.getFum().getYear() + 1900));
            modelo.put("mes_r", Integer.toString(datosC2.getFum().getMonth() + 1));
            modelo.put("dia_r", Integer.toString(datosC2.getFum().getDate()));
            modelo.put("peso", Double.toString(datosC2.getPeso()));
            modelo.put("talla", Double.toString(datosC2.getTalla()));

            return new ModelAndView("administrarhistoriales/Perinatal", modelo);
        }

        if ("DiagnosticosCIE10".equals(accion)) {
            accion = "Siguiente";
            sw = "objetivo";
            swcie = "directo";
            modelo.put("swcie", "SI");
            modelo.put("swinter", "inter");
            modelo.put("accion", accion);
        }

        if ("Siguiente".equals(accion)) {
            // borramos de la lista de parcientes en espera

            if (subjetivo.contains("Office") || objetivo.contains("Office") || diagnostico.contains("Office") || miaccion.contains("Office")
                    || subjetivo.contains("Word") || objetivo.contains("Word") || diagnostico.contains("Word") || miaccion.contains("Word")) {
                return new ModelAndView("Aviso", "mensaje", "No puede realizar copias de Word o Excel, Regrese y vuelva a escribir");
            }
            if ("subjetivo".equals(sw)) {
                // modificamos el historial
                String talla = request.getParameter("talla");
                String peso = request.getParameter("peso");
                String temperatura = request.getParameter("temperatura");
                String fc = request.getParameter("fc");
                String pa = request.getParameter("pa");
                String fr = request.getParameter("fr");
                String so2 = request.getParameter("so2");
                String glicemia = request.getParameter("glicemia");

                try {
                    if (Float.parseFloat(talla) != 0 || Float.parseFloat(peso) != 0) {
                        if (Float.parseFloat(peso) > 200 && Float.parseFloat(peso) != 0) {
                            return new ModelAndView("Aviso", "mensaje", "El Peso debe estar en Kilogramos");
                        }
                        if (Float.parseFloat(talla) > 300) {
                            return new ModelAndView("Aviso", "mensaje", "La Talla debe estar en centimetros");
                        }
                        if (Float.parseFloat(peso) * 1 != Float.parseFloat(peso)) {
                            return new ModelAndView("Aviso", "mensaje", "El Peso no es un numero valido");
                        }
                        if (Float.parseFloat(talla) * 1 != Float.parseFloat(talla)) {
                            return new ModelAndView("Aviso", "mensaje", "La Talla no es un numero valido");
                        }
                        if (Float.parseFloat(talla) * 1 < 4) {
                            return new ModelAndView("Aviso", "mensaje", "La Talla debe estar en centimetros");
                        }
                        if (Float.parseFloat(talla) < Float.parseFloat(peso)) {
                            return new ModelAndView("Aviso", "mensaje", "La Talla y Peso estan invertidos");
                        }
                    }
                } catch (Exception e) {
                    return new ModelAndView("Aviso", "mensaje", "Los datos de peso y talla no son correctos");
                }

                Historiales dato = new Historiales();
                dato.setId_historial(Integer.parseInt(id_reservacion));
                dato.setId_persona(Integer.parseInt(id_persona));
                dato.setId_paciente(Integer.parseInt(id_paciente));
                dato.setEdad(buscarPaciente.getEdad());
                dato.setId_consultorio(Integer.parseInt(id_consultorio));
                dato.setTalla(Double.parseDouble(talla));
                dato.setPeso(Double.parseDouble(peso));
                dato.setEstimc(estimc);
                dato.setTemperatura(Double.parseDouble(temperatura));
                dato.setExpedido(expedido);
                dato.setFc(fc);
                dato.setPa(pa);
                dato.setFr(fr);
                dato.setSo2(so2);
                dato.setGlicemia(glicemia);
                dato.setCodigo(codigo);
                dato.setRepetida("N");
                dato.setSubjetivo(subjetivo);
                dato.setObjetivo(objetivo);
                dato.setFechalab(datosReserva.getFecha());
                if (datosReserva.getFecha().getTime() > datosReserva.getFechalab().getTime()) {
                    dato.setFechalab(datosReserva.getFechalab());
                }
                dato.setRegistro(riesgovig);/////21/04/2015 para riesgo de Obrero
                dato.setId_seguro(buscarPaciente.getId_seguro());
                dato.setCod_esta(cliente.getCod_esta());
                dato.setId_por(cliente.getId_persona());
                int iResultado = this.mi.setRegistrarHistorial(dato);
                modelo.put("datoshisto", dato);

                Historiales elimina = new Historiales();
                elimina.setId_reservacion(Integer.parseInt(id_reservacion));
                elimina.setCod_esta(cliente.getCod_esta());
                elimina.setExpedido("E");
                this.mi.setEliminarReserva(elimina);
                return new ModelAndView("administrarhistoriales/AtenderPaciente2", modelo);
            }

            if ("objetivo".equals(sw)) {
                String talla = request.getParameter("talla1");
                String peso = request.getParameter("peso1");
                String temperatura = request.getParameter("temperatura");
                String fc = request.getParameter("fc");
                String pa = request.getParameter("pa");
                String fr = request.getParameter("fr");
                String so2 = request.getParameter("so2");
                String glicemia = request.getParameter("glicemia");
                String triaje = request.getParameter("triaje");

                if ("directo".equals(swcie) && talla == null) {
                    talla = "0";
                }
                if ("directo".equals(swcie) && peso == null) {
                    peso = "0";
                }
                if ("directo".equals(swcie) && temperatura == null) {
                    temperatura = "0";
                }

                try {
                    if (Float.parseFloat(talla) != 0 || Float.parseFloat(peso) != 0) {
                        if (Float.parseFloat(peso) > 200 && Float.parseFloat(peso) != 0) {
                            return new ModelAndView("Aviso", "mensaje", "El Peso debe estar en Kilogramos");
                        }
                        if (Float.parseFloat(talla) > 300) {
                            return new ModelAndView("Aviso", "mensaje", "La Talla debe estar en centimetros");
                        }
                        if (Float.parseFloat(peso) * 1 != Float.parseFloat(peso)) {
                            return new ModelAndView("Aviso", "mensaje", "El Peso no es un numero valido");
                        }
                        if (Float.parseFloat(talla) * 1 != Float.parseFloat(talla)) {
                            return new ModelAndView("Aviso", "mensaje", "La Talla no es un numero valido");
                        }
                        if (Float.parseFloat(talla) * 1 < 4) {
                            return new ModelAndView("Aviso", "mensaje", "La Talla debe estar en centimetros");
                        }
                        if (Float.parseFloat(talla) < Float.parseFloat(peso)) {
                            return new ModelAndView("Aviso", "mensaje", "La Talla y Peso estan invertidos");
                        }
                    }
                } catch (Exception e) {
                    return new ModelAndView("Aviso", "mensaje", "Los datos de peso y talla no son correctos");
                }
                try {
                    if (Float.parseFloat(fc) * 1 < 0) {
                        return new ModelAndView("Aviso", "mensaje", "No puede ser un numero negativo");
                    }
                } catch (Exception e) {
                    fc = "0";
                }
                try {
                    if (Float.parseFloat(fr) * 1 < 0) {
                        return new ModelAndView("Aviso", "mensaje", "No puede ser un numero negativo");
                    }
                } catch (Exception e) {
                    fr = "0";
                }

                datosHistorial.setId_historial(Integer.parseInt(id_reservacion));
                //datosHistorial.setId_persona(Integer.parseInt(id_persona));//09/09/2014 
                //datosHistorial.setId_consultorio(Integer.parseInt(id_consultorio));   
                if (datosconsulto.getId_especialidad() == 99 && datosReserva != null) {///para Obrero
                    if (datosReserva != null) {///para Obrero
                        datosHistorial.setId_persona(datosReserva.getId_persona());//09/09/2014
                        datosHistorial.setId_consultorio(datosReserva.getId_consultorio());//09/09/2014  
                        datosHistorial.setTriaje(datosReserva.getTriaje());
                        datosHistorial.setFechalab(datosReserva.getFecha());
                    } else {
                        datosHistorial.setId_persona(Integer.parseInt(id_persona));//09/09/2014 
                        datosHistorial.setId_consultorio(Integer.parseInt(id_consultorio));
                        datosHistorial.setTriaje(datosHistorial.getTriaje());
                        datosHistorial.setFechalab(datosHistorial.getFechalab());
                    }
                } else {
                    if (datosReserva == null) {///para no Obrero
                        datosHistorial.setId_persona(datosHistorial.getId_persona());//09/09/2014
                        datosHistorial.setId_consultorio(datosHistorial.getId_consultorio());//09/09/2014   
                        datosHistorial.setTriaje(datosHistorial.getTriaje());
                        datosHistorial.setFechalab(datosHistorial.getFechalab());
                    } else {
                        datosHistorial.setId_persona(cliente.getId_persona());//02/06/2015 del usuario que llena los datos antes id_persona
                        datosHistorial.setId_consultorio(cliente.getId_consultorio());   //02/06/2015 del usuario que llena los dato antes id_consultorio
                        datosHistorial.setTriaje(datosReserva.getTriaje());
                        datosHistorial.setFechalab(datosReserva.getFecha());
                    }
                }

                datosHistorial.setId_paciente(Integer.parseInt(id_paciente));
                datosHistorial.setEdad(buscarPaciente.getEdad());
                datosHistorial.setTalla(Double.parseDouble(talla));
                datosHistorial.setPeso(Double.parseDouble(peso));
                datosHistorial.setEstimc(estimc);
                datosHistorial.setTemperatura(Double.parseDouble(temperatura));
                datosHistorial.setRegistro(riesgovig);/////21/04/2015 para riesgo de Obrero
                datosHistorial.setExpedido(expedido);
                datosHistorial.setFc(fc);
                datosHistorial.setPa(pa);
                datosHistorial.setFr(fr);
                datosHistorial.setSo2(so2);
                datosHistorial.setGlicemia(glicemia);

                if (Float.parseFloat(peso) > 200) {
                    return new ModelAndView("Aviso", "mensaje", "El Peso debe estar en Kilogramos");
                }

                if (buscarPaciente.getEmbarazo() > 0) {
                    String nemba = request.getParameter("nemba");
                    int semges = 0;
                    double peso1 = 0.0;
                    Date fecha1 = new Date();
                    Cuadernos dato = new Cuadernos();
                    dato2.setId_historial(Integer.parseInt(id_reservacion));   ///19-05-2015
                    dato2.setCod_esta(cliente.getCod_esta());  ///19-05-2015
                    List C2 = this.mi.getPacienteCuaderno2(dato2);
                    if (datosC2 != null) {
                        fumu = datosC2.getFum();
                        nemba = Integer.toString(datosC2.getNembarazo());
                        if (fumu != null) {
                            long fechaInicialMs = fumu.getTime();
                            long fechaFinalMs = fecha1.getTime();
                            long diferencia = fechaFinalMs - fechaInicialMs;
                            double dias = Math.floor(diferencia / (1000 * 60 * 60 * 24));
                            semges = (int) (Math.round(((float) dias) / 7));
                        }
                    }
                    if (semges >= 1 && semges <= 16) {
                        peso1 = Float.parseFloat(peso) - 1;
                        peso = Integer.toString((int) (peso1));
                    } else {
                        if (semges >= 17 && semges <= 20) {
                            peso1 = Float.parseFloat(peso) - 1;
                            peso = Integer.toString((int) (peso1));
                        } else {
                            if (semges >= 21 && semges <= 24) {
                                peso1 = Float.parseFloat(peso) - 5.5;
                                peso = Integer.toString((int) (peso1));
                            } else {
                                if (semges >= 25 && semges <= 28) {
                                    peso1 = Float.parseFloat(peso) - 7;
                                    peso = Integer.toString((int) (peso1));
                                } else {
                                    if (semges >= 29 && semges <= 32) {
                                        peso1 = Float.parseFloat(peso) - 8.5;
                                        peso = Integer.toString((int) (peso1));
                                    } else {
                                        if (semges >= 33 && semges <= 36) {
                                            peso1 = Float.parseFloat(peso) - 10;
                                            peso = Integer.toString((int) (peso1));
                                        } else {
                                            if (semges >= 37) {
                                                peso1 = Float.parseFloat(peso) - 11;
                                                peso = Integer.toString((int) (peso1));
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                valor1 = "000";
                if (Float.parseFloat(talla) == 0) {
                    talla = "0.0";
                } else {
                    if (Float.parseFloat(talla) < 3) {
                        return new ModelAndView("Aviso", "mensaje", "Talla debe estar en centimetros");
                    } else {
                        //calcula el IMC  
                        double valor = Float.parseFloat(peso) / ((Float.parseFloat(talla)) * (Float.parseFloat(talla)) / (100 * 100));
                        valor1 = String.valueOf(valor);
                        if (valor <= 20) {
                            estimc = valor1.substring(0, 4) + "\nDesnutricion";
                        } else if (valor > 20 && valor <= 25) {
                            estimc = valor1.substring(0, 4) + "\nNormal";
                        } else if (valor > 25 && valor <= 30) {
                            estimc = valor1.substring(0, 4) + "\nSobrepeso";
                        } else {
                            estimc = valor1.substring(0, 4) + "\nObesidad";
                        }
                        // fin de calcula      
                    }
                }
                if (repetida == null) {
                    repetida = "N";
                }

                datosHistorial.setCodigo(codigo);
                datosHistorial.setRepetida(repetida);
                datosHistorial.setSubjetivo(subjetivo);
                datosHistorial.setObjetivo(objetivo);

                modelo.put("estimc", estimc);
                modelo.put("valor1", valor1.substring(0, 2));
                datosHistorial.setDiagnostico(diagnostico);
                datosHistorial.setAccion(miaccion);
                datosHistorial.setRepetida(repetida);
                datosHistorial.setId_historial(Integer.parseInt(id_reservacion));
                datosHistorial.setEstimc(estimc);
                if (datosReserva != null) {
                    datosHistorial.setId_seguro(datosReserva.getId_seguro());
                    if (datosReserva.getVigencia() == 2 || datosReserva.getVigencia() == 0) {
                        datosHistorial.setVigencia(0);   //01/07/2015   los que son estadistica pasan con 0 como consulta externa
                    } else {
                        datosHistorial.setVigencia(1);   //01/07/2015  1 para emergencias que fueron atendidos por emergencias
                    }
                    datosHistorial.setId_seguro(datosHistorial.getId_seguro());
                    datosHistorial.setVigencia(datosHistorial.getVigencia());//01/07/2015   
                }
                datosHistorial.setCod_esta(cliente.getCod_esta());
                datosHistorial.setId_por(cliente.getId_persona());
                if (datosReserva == null) {
                    datosHistorial.setTipoconsult(datosHistorial.getTipoconsult());
                    datosHistorial.setId_riesgo(datosHistorial.getId_riesgo());
                } else {
                    datosHistorial.setTipoconsult(datosReserva.getTipoconsult());
                    datosHistorial.setId_riesgo(datosReserva.getId_riesgo());
                }

                int iResultado = this.mi.setRegistrarHistorial(datosHistorial);

                Historiales elimina = new Historiales();
                elimina.setId_reservacion(Integer.parseInt(id_reservacion));
                elimina.setCod_esta(cliente.getCod_esta());
                elimina.setAccion(ip);
                elimina.setCodigo(host);
                elimina.setExpedido("E");
                elimina.setId_persona(cliente.getId_persona());
                //if ("F".equals(datoestab.getArea()) && "E".equals(expedido)){  // para HGSJDD no lo borrara de la tabla reservaciones hasta que no pague
                //this.mi.setModificarPagoReserva(Integer.parseInt(id_reservacion),"X") ;  ///en reservaciones coloca los pagos en X
                //}else{
                this.mi.setEliminarReserva(elimina);
                //}
                return new ModelAndView("administrarhistoriales/AtenderPaciente3", modelo);
            }
        }

        if ("Graficar".equals(accionx)) {
            String talla = request.getParameter("talla1");
            String peso = request.getParameter("peso1");
            String peso3 = request.getParameter("peso1");
            String pa = request.getParameter("pa");
            String edad = request.getParameter("edad");
            String mes = request.getParameter("mes");

            //return new ModelAndView("Aviso","mensaje","Falta datos de Peso o Talla"); 
            Pacientes paciente = new Pacientes();
            paciente.setCod_esta(cliente.getCod_esta());
            paciente.setId_paciente(Integer.parseInt(id_paciente));
            paciente.setTipo("G");  ////getListarPacientesGrafica
            List listarPacG = this.mi.getListarPacientesGrafica(paciente);
            modelo.put("listaPacientesG", listarPacG);
            //System.out.println(listarPacG);
            return new ModelAndView("administrarhistoriales/GraficasAntro", modelo);
        }

        if ("Calcular".equals(accionx)) {
            String talla = request.getParameter("talla1");
            String peso = request.getParameter("peso1");
            String peso3 = request.getParameter("peso1");
            String temperatura = request.getParameter("temperatura");
            String fc = request.getParameter("fc");
            String fr = request.getParameter("fr");
            String so2 = request.getParameter("so2");
            String glicemia = request.getParameter("glicemia");
            String pa = request.getParameter("pa");
            String edad = request.getParameter("edad");
            String mes = request.getParameter("mes");
            modelo.put("peso", peso);
            modelo.put("talla", talla);
            double peson = 0;
            try {
                if (Float.parseFloat(talla) != 0 || Float.parseFloat(peso) != 0) {
                    if (Float.parseFloat(peso) > 200 && Float.parseFloat(peso) != 0) {
                        return new ModelAndView("Aviso", "mensaje", "El Peso debe estar en Kilogramos");
                    }
                    if (Float.parseFloat(talla) > 300) {
                        return new ModelAndView("Aviso", "mensaje", "La Talla debe estar en centimetros");
                    }
                    if (Float.parseFloat(peso) * 1 != Float.parseFloat(peso)) {
                        return new ModelAndView("Aviso", "mensaje", "El Peso no es un numero valido");
                    }
                    if (Float.parseFloat(talla) * 1 != Float.parseFloat(talla)) {
                        return new ModelAndView("Aviso", "mensaje", "La Talla no es un numero valido");
                    }
                    if (Float.parseFloat(talla) * 1 < 4) {
                        return new ModelAndView("Aviso", "mensaje", "La Talla debe estar en centimetros");
                    }
                    if (Float.parseFloat(talla) < Float.parseFloat(peso)) {
                        return new ModelAndView("Aviso", "mensaje", "La Talla y Peso estan invertidos");
                    }
                }
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "Los datos de peso y talla no son correctos");
            }

            if (Float.parseFloat(peso) > 0) {
                peson = Float.parseFloat(peso);
            }

            if (buscarPaciente.getEmbarazo() > 0) {
                String nemba = request.getParameter("nemba");
                int semges = 0;
                double peso1 = 0.0;
                Date fecha1 = new Date();
                Cuadernos dato = new Cuadernos();
                dato2.setId_historial(Integer.parseInt(id_reservacion));   ///19-05-2015
                dato2.setCod_esta(cliente.getCod_esta());  ///19-05-2015
                List C2 = this.mi.getPacienteCuaderno2(dato2);
                if (datosC2 != null) {
                    fumu = datosC2.getFum();
                    nemba = Integer.toString(datosC2.getNembarazo());
                    if (fumu != null) {
                        long fechaInicialMs = fumu.getTime();
                        long fechaFinalMs = fecha1.getTime();
                        long diferencia = fechaFinalMs - fechaInicialMs;
                        double dias = Math.floor(diferencia / (1000 * 60 * 60 * 24));
                        semges = (int) (Math.round(((float) dias) / 7));
                    }
                }

                if (semges >= 1 && semges <= 16) {
                    peso1 = Float.parseFloat(peso) - 2;
                    peso = Integer.toString((int) (peso1));
                } else {
                    if (semges >= 17 && semges <= 20) {
                        peso1 = Float.parseFloat(peso) - 4;
                        peso = Integer.toString((int) (peso1));
                    } else {
                        if (semges >= 21 && semges <= 24) {
                            peso1 = Float.parseFloat(peso) - 5.5;
                            peso = Integer.toString((int) (peso1));
                        } else {
                            if (semges >= 25 && semges <= 28) {
                                peso1 = Float.parseFloat(peso) - 7;
                                peso = Integer.toString((int) (peso1));
                            } else {
                                if (semges >= 29 && semges <= 32) {
                                    peso1 = Float.parseFloat(peso) - 8.5;
                                    peso = Integer.toString((int) (peso1));
                                } else {
                                    if (semges >= 33 && semges <= 36) {
                                        peso1 = Float.parseFloat(peso) - 10;
                                        peso = Integer.toString((int) (peso1));
                                    } else {
                                        if (semges >= 37) {
                                            peso1 = Float.parseFloat(peso) - 11;
                                            peso = Integer.toString((int) (peso1));
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            if (Float.parseFloat(talla) == 0) {
                talla = "0.0";
            } else {
                if (Float.parseFloat(talla) < 3) {
                    return new ModelAndView("Aviso", "mensaje", "Talla debe estar en centimetros");
                } else {

                    //calcula el IMC  
                    double valor = Float.parseFloat(peso) / ((Float.parseFloat(talla)) * (Float.parseFloat(talla)) / (100 * 100));
                    valor1 = String.valueOf(valor);
                    if (valor <= 20) {
                        estimc = valor1.substring(0, 4) + "\nDesnutricion";
                    } else if (valor > 20 && valor <= 25) {
                        estimc = valor1.substring(0, 4) + "\nNormal";
                    } else if (valor > 25 && valor <= 30) {
                        estimc = valor1.substring(0, 4) + "\nSobrepeso";
                    } else {
                        estimc = valor1.substring(0, 4) + "\nObesidad";
                    }

                    double valorn = Float.parseFloat(peso3) / ((Float.parseFloat(talla)) * (Float.parseFloat(talla)) / (100 * 100));
                    valorn1 = String.valueOf(valorn);
                    if (valorn <= 20) {
                        estimcn = valorn1.substring(0, 4) + "\nDesnutricion";
                    } else if (valorn > 20 && valorn <= 25) {
                        estimcn = valorn1.substring(0, 4) + "\nNormal";
                    } else if (valorn > 25 && valorn <= 30) {
                        estimcn = valorn1.substring(0, 4) + "\nSobrepeso";
                    } else {
                        estimcn = valorn1.substring(0, 4) + "\nObesidad";
                    }
                    modelo.put("estimcn", estimcn);
                    // fin de calcula      
                }
            }

            Historiales datohis = new Historiales();
            datohis.setId_historial(Integer.parseInt(id_reservacion));
            datohis.setCod_esta(cliente.getCod_esta());
            Historiales datosHisto = this.mi.getDatosHistorial(datohis);

            double p = Double.parseDouble(peso);
            double t = Double.parseDouble(talla);
            int e = 0;

            if (p == 0 || t == 0) {
                return new ModelAndView("Aviso", "mensaje", "Falta datos de Peso o Talla");
            }
            if (datosHisto != null) {
                e = datosHisto.getEdad();
            } else {
                e = Integer.parseInt(edad);
            }

            if (e < 5) {
                //Calculamos la nutricion Talla/Peso
                ///Cuadernos datoNu = this.mi.getDatosTallaPeso(t,e,buscarPaciente.getId_tipo_sexo()) ;
                Cuadernos datotp = new Cuadernos();
                datotp.setTalla(t);
                datotp.setEdad(e);
                Cuadernos datoNu = new Cuadernos();
                if (buscarPaciente.getId_tipo_sexo() == 1) {
                    if (e < 2) {
                        datoNu = this.mi.getDatosTallaPesoF02(datotp);
                    } else {
                        datoNu = this.mi.getDatosTallaPesoF25(datotp);
                    }
                } else {
                    if (e < 2) {
                        datoNu = this.mi.getDatosTallaPesoM02(datotp);
                    } else {
                        datoNu = this.mi.getDatosTallaPesoM25(datotp);
                    }
                }

                if (datoNu == null) {
                    return new ModelAndView("Aviso", "mensaje", "Los datos no son correctos");
                }

                String nutricion = "";

                if (p <= datoNu.getM3ds()) {
                    nutricion = "G";
                }
                if (p > datoNu.getM3ds() && p <= datoNu.getM2ds()) {
                    nutricion = "M";
                }
                if (p > datoNu.getM2ds() && p <= datoNu.getM1ds()) {
                    nutricion = "N";  ////L ya no es L cambio desnutricion
                }
                if (p > datoNu.getM1ds() && p <= datoNu.getDs2()) {
                    nutricion = "N";
                }
                if (p > datoNu.getDs2() && p <= datoNu.getDs3()) {
                    nutricion = "S";
                }
                if (p > datoNu.getDs3()) {
                    nutricion = "O";
                }

                modelo.put("nutricion", nutricion);
                //Calculamos la nutricion talla edad
                String tallaedad = "";
                //Cuadernos datoTEdad = this.mi.getDatosTallaEdad(e,Integer.parseInt(mes),buscarPaciente.getId_tipo_sexo()) ;

                Cuadernos datote = new Cuadernos();
                datote.setTalla(t);
                datote.setMes(e * 12 + Integer.parseInt(mes));
                Cuadernos datoTEdad = new Cuadernos();
                if (buscarPaciente.getId_tipo_sexo() == 1) {
                    if (e < 2) {
                        datoTEdad = this.mi.getDatosTallaEdadF02(datote);
                    } else {
                        datoTEdad = this.mi.getDatosTallaEdadF25(datote);
                    }
                } else {
                    if (e < 2) {
                        datoTEdad = this.mi.getDatosTallaEdadM02(datote);
                    } else {
                        datoTEdad = this.mi.getDatosTallaEdadM25(datote);
                    }
                }

                if (t <= datoTEdad.getM3ds()) {
                    tallaedad = "L";
                }
                if (t > datoTEdad.getM3ds() && t <= datoTEdad.getM2ds()) {
                    tallaedad = "L";
                }
                if (t > datoTEdad.getM2ds()) {
                    tallaedad = "N";
                }

                modelo.put("tallaedad", tallaedad);
                //desnutricion global
                Date fecha_naci = new Date();
                Date fecha1 = new Date();
                int semanas = 0;

                Pacientes buscarmenor = this.mi.getDatosPaciente(Integer.parseInt(id_paciente));
                fecha_naci = buscarmenor.getFec_nacimiento();

                long fechaInicialMs = fecha_naci.getTime();
                long fechaFinalMs = fecha1.getTime();
                long diferencia = fechaFinalMs - fechaInicialMs;
                double dias = Math.floor(diferencia / (1000 * 60 * 60 * 24));
                semanas = (int) (Math.round(((float) dias) / 7));
                String pesoedad = "";
                if (semanas < 24) {/////////falla menores de 2 meses y primera consulta
                    Cuadernos datoc = new Cuadernos();
                    datoc.setId_tipo_sexo(buscarPaciente.getId_tipo_sexo());
                    datoc.setMes(e);
                    datoc.setEdad(semanas);
                    if (buscarPaciente.getId_tipo_sexo() == 1) {
                        Cuadernos datoPEdad = this.mi.getDatosPesoEdadF02(datoc);
                        if (p <= datoPEdad.getM3ds()) {
                            pesoedad = "G";
                        }
                        if (p > datoPEdad.getM3ds() && p <= datoPEdad.getM2ds()) {
                            pesoedad = "L";
                        }
                        if (p > datoPEdad.getM2ds()) {
                            pesoedad = "N";
                        }
                        modelo.put("pesoedad", pesoedad);
                    } else {
                        Cuadernos datoPEdad = this.mi.getDatosPesoEdadM02(datoc);
                        if (p <= datoPEdad.getM3ds()) {
                            pesoedad = "G";
                        }
                        if (p > datoPEdad.getM3ds() && p <= datoPEdad.getM2ds()) {
                            pesoedad = "L";
                        }
                        if (p > datoPEdad.getM2ds()) {
                            pesoedad = "N";
                        }
                        modelo.put("pesoedad", pesoedad);
                    }

                } else {
                    pesoedad = "N";
                    modelo.put("pesoedad", pesoedad);
                }
            }

            Historiales dato = new Historiales();
            dato.setId_historial(Integer.parseInt(id_reservacion));
            dato.setId_persona(Integer.parseInt(id_persona));
            dato.setId_paciente(Integer.parseInt(id_paciente));
            dato.setEdad(buscarPaciente.getEdad());
            dato.setId_consultorio(Integer.parseInt(id_consultorio));
            dato.setTalla(t);
            dato.setPeso(Double.parseDouble(peso3)); ////10/10/2012  dato.setPeso(p);///
            dato.setTemperatura(Double.parseDouble(temperatura));
            dato.setExpedido(expedido);
            dato.setFc(fc);
            dato.setPa(pa);
            dato.setFr(fr);
            dato.setSo2(so2);
            dato.setGlicemia(glicemia);
            dato.setCod_esta(cliente.getCod_esta());
            this.mi.setModificarSignosReserva(dato);

            if (datosHisto != null) {
                Historiales datohist = new Historiales();
                datohist.setId_historial(Integer.parseInt(id_reservacion));
                datohist.setCod_esta(cliente.getCod_esta());
                Historiales datosHisto1 = this.mi.getDatosHistorial(datohist);
                modelo.put("datoshisto", datosHisto1);
            } else {
                dato.setAccion("R");   ////getDatosReserva
                dato.setCod_esta(cliente.getCod_esta());
                Historiales datosReserva2 = this.mi.getDatosHistorial(dato);
                modelo.put("datoshisto", datosReserva2);
            }
            modelo.put("estimc", estimc);
            modelo.put("estimcn", estimcn);
            modelo.put("datoshisto", dato);

            modelo.put("valor1", valor1.substring(0, 2));
            modelo.put("valorn1", valorn1.substring(0, 2));
            Cuadernos datoy = new Cuadernos();
            datoy.setCod_esta(cliente.getCod_esta());
            datoy.setId_paciente(Integer.parseInt(id_paciente));
            datoy.setTipo("P");
            List listaAten4 = this.mi.getVerCuaderno4(datoy);
            modelo.put("listaAten4", listaAten4);

            return new ModelAndView("administrarhistoriales/AtenderPaciente", modelo);
        }
        //muestra datos del cuaderno7
        Cuadernos dato = new Cuadernos();
        dato.setCod_esta(cliente.getCod_esta());  ///16-01-2016
        dato.setId_paciente(Integer.parseInt(id_paciente));
        dato.setTipo("P");
        List listaAten = this.mi.getVerCuaderno7Paci(dato);
        modelo.put("listaAten", listaAten);

        Cuadernos datoy = new Cuadernos();
        datoy.setCod_esta(cliente.getCod_esta());  ///16-01-2016
        datoy.setId_paciente(Integer.parseInt(id_paciente));
        datoy.setTipo("P");
        List listaAten4 = this.mi.getVerCuaderno4Paci(datoy);
        modelo.put("listaAten4", listaAten4);

        if ("Repetir Ult.Talla".equals(accion)) {
            //Historiales datohi= new Historiales() ;
            datohi.setId_historial(Integer.parseInt(id_paciente));
            datohi.setAccion("U");
            datohi.setCod_esta(cliente.getCod_esta());
            Historiales datosHistoUlt = this.mi.getDatosHistorialUlt(datohi);
            if (datosHistoUlt != null) {
                datohi.setId_historial(datosHistoUlt.getId_historial());
                datohi.setAccion("H"); ///
                datohi.setCod_esta(cliente.getCod_esta());
                Historiales datosH = this.mi.getDatosHistorial(datohi);

                Historiales daton = new Historiales();
                daton.setId_historial(Integer.parseInt(id_reservacion));
                daton.setId_persona(Integer.parseInt(id_persona));
                daton.setId_paciente(Integer.parseInt(id_paciente));
                daton.setEdad(buscarPaciente.getEdad());
                daton.setId_consultorio(Integer.parseInt(id_consultorio));
                daton.setTalla(datosH.getTalla());
                daton.setPeso(datosH.getPeso());
                daton.setTemperatura(datosH.getTemperatura());
                daton.setExpedido(expedido);
                daton.setFc(datosH.getFc());
                daton.setPa(datosH.getPa());
                daton.setFr(datosH.getFr());
                daton.setCod_esta(cliente.getCod_esta());
                this.mi.setModificarSignosReserva(daton);
                datohi.setAccion("R");
                datohi.setCod_esta(cliente.getCod_esta());
                datohi.setId_historial(Integer.parseInt(id_reservacion));
                Historiales datosReser = this.mi.getDatosReserva(datohi);   ////getDatosReserva
                modelo.put("datoshisto", datosReser);
                if (datosReser == null) {
                    //Historiales datohi= new Historiales() ;
                    datohi.setId_historial(Integer.parseInt(id_reservacion));
                    Historiales datosHisto1 = this.mi.getDatosHistorial(datohi);
                    modelo.put("datoshisto", datosHisto1);
                } else {
                    //Historiales datosReserva2 = this.mi.getDatosHistorial(datohi) ;   
                    //modelo.put("datoshisto", datosReser); 
                }
            } else {
                datohi.setId_historial(Integer.parseInt(id_reservacion));
                Historiales datosReserva2 = this.mi.getDatosReserva(datohi);
                modelo.put("datoshisto", datosReserva2);
            }
        }

        if ("Repetir Ult.Historial".equals(accion)) {
            //Historiales datohi= new Historiales() ;
            datohi.setId_paciente(Integer.parseInt(id_paciente));
            datohi.setCod_esta(cliente.getCod_esta());
            datohi.setId_persona(Integer.parseInt(id_persona));
            if (cliente.getId_especialidad() != 99) {
                datohi.setId_persona(cliente.getId_persona());
            }
            Historiales datosHistoUlt = this.mi.getDatosHistorialUltHisto(datohi);
            if (datosHistoUlt != null) {

                Historiales daton = new Historiales();
                daton.setTalla(datosHistoUlt.getTalla());
                daton.setPeso(datosHistoUlt.getPeso());
                daton.setTemperatura(datosHistoUlt.getTemperatura());
                daton.setFc(datosHistoUlt.getFc());
                daton.setPa(datosHistoUlt.getPa());
                daton.setFr(datosHistoUlt.getFr());
                daton.setSo2(datosHistoUlt.getSo2());
                daton.setGlicemia(datosHistoUlt.getGlicemia());
                daton.setSubjetivo(datosHistoUlt.getSubjetivo());
                daton.setObjetivo(datosHistoUlt.getObjetivo());
                daton.setDiagnostico(datosHistoUlt.getDiagnostico());
                daton.setAccion(datosHistoUlt.getAccion());
                if (datosReserva.getVigencia() == 2) {
                    datosHistorial.setVigencia(0);   //01/07/2015   los que son estadistica pasan con 0 como consulta externa
                } else {
                    datosHistorial.setVigencia(1);   //01/07/2015  1 para emergencias que fueron atendidos por emergencias
                }

                daton.setCod_esta(cliente.getCod_esta());
                daton.setId_por(cliente.getId_persona());
                //int iResultado = this.mi.setRegistrarHistorial(datosHistorial);  
                modelo.put("diagnostico", datosHistoUlt.getDiagnostico());
                modelo.put("subjetivo", datosHistoUlt.getSubjetivo());
                modelo.put("objetivo", datosHistoUlt.getObjetivo());
                modelo.put("miaccion", datosHistoUlt.getAccion());
                modelo.put("datoshisto", daton);

            }

        }

        if ("Terminar".equals(accion) || "Terminar.".equals(accion)) {

            if (!listarmorbi1.isEmpty()) {  ////if(listarmorbi1.size()!=0){
                Historiales datopac2 = (Historiales) listarmorbi1.get(0);
                datosHistorial.setCodigo(datopac2.getNombres());
                modelo.put("cont", Integer.toString(listarmorbi1.size()));
            }
            datosHistorial.setId_historial(Integer.parseInt(id_reservacion));
            modelo.put("estimc", estimc);
            if (datosconsulta.getId_cargo() == 10 || datosconsulta.getId_cargo() == 11 || datosconsulta.getId_cargo() == 12) {
                datosHistorial.setSubjetivo("Laboratorio, Imagenologia");
                datosHistorial.setObjetivo("Laboratorio, Imagenologia");
                datosHistorial.setDiagnostico("Laboratorio, Imagenologia");
                datosHistorial.setAccion("Pedido, Examenes");
            }
            datosHistorial.setCod_esta(cliente.getCod_esta());
            datosHistorial.setId_por(cliente.getId_persona());
            int iResultado = this.mi.setRegistrarHistorial(datosHistorial);
            //Consultorios datosconsul = this.mi.getDatosConsultorio(persona.getId_consultorio()) ; // saca un registro a ser modificado
            Consultorios datosconsul = this.mi.getDatosConsultorio(cliente.getId_consultorio()); // para admisnitrador muestra todas las opciones
            modelo.put("tipo_medico", Integer.toString(datosconsul.getId_cargo()));
            modelo.put("especialidad", Integer.toString(datosconsulta.getId_especialidad()));
            if ("inter".equals(swinter)) {
                return new ModelAndView("administrarhistoriales/PlanAccionPacienteI", modelo);
            } else {
                return new ModelAndView("administrarhistoriales/PlanAccionPaciente", modelo);
            }
        }

        if ("EliminarCie10".equals(boton)) {
            String id_historia = request.getParameter("morbilidad");

            Historiales morbielimina = new Historiales();
            morbielimina.setId_historia(Integer.parseInt(id_historia));
            morbielimina.setCod_esta(cliente.getCod_esta());
            this.mi.setEliminarMorbilidad(morbielimina);
            //Historiales datomorbi= new Historiales();  
            datomorbi.setId_reservacion(Integer.parseInt(id_reservacion));
            datomorbi.setCod_esta(cliente.getCod_esta());
            List listarmorbi = this.mi.getListaMorbi(datomorbi);
            modelo.put("morbi", listarmorbi);
            if (listarmorbi.isEmpty()) {
                datosHistorial.setCodigo("");
                datosHistorial.setCod_esta(cliente.getCod_esta());
                datosHistorial.setId_por(cliente.getId_persona());
                int iResultado = this.mi.setRegistrarHistorial(datosHistorial);
            }
            if (listarmorbi.isEmpty() == true) {
                modelo.put("terminar", "no");
            }
            modelo.put("cont", Integer.toString(listarmorbi.size()));
            return new ModelAndView("administrarhistoriales/AtenderPaciente3", modelo);
        }
        return new ModelAndView("administrarhistoriales/AtenderPaciente", modelo);
    }

}
