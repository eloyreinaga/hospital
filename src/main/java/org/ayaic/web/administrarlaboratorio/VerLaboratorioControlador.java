package org.ayaic.web.administrarlaboratorio;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Date;
import java.io.File;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Consultorios;
import org.ayaic.domain.Costos;
import org.ayaic.domain.Cuadernos;
import org.ayaic.domain.Historiales;
import org.ayaic.domain.Localidades;
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.Personas;
import org.ayaic.domain.Sexos;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class VerLaboratorioControlador {

    private final MiFacade mi;

    public VerLaboratorioControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/VerLaboratorio.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        Personas persona = this.mi.getBuscarPersona(cliente.getId_persona());
        Consultorios datosconsul = this.mi.getDatosConsultorio(persona.getId_consultorio()); // saca un registro a ser modificado

        String accion = request.getParameter("accion");
        String boton = request.getParameter("boton");
        String accionpred = request.getParameter("accionpred");
        String accionl = request.getParameter("accionl");
        String id_historial = request.getParameter("id_historial");
        String id_laboratorio = request.getParameter("id_laboratorio");
        String id_cargo = request.getParameter("id_cargo");
        String id_paciente = request.getParameter("id_paciente");
        String id_persona = request.getParameter("id_persona");
        String expedido = request.getParameter("expedido");
        String id_pedido = request.getParameter("id_pedido");
        String id_rubro = request.getParameter("id_rubro");
        String id_costo = request.getParameter("id_costo");
        String cod_esta = request.getParameter("cod_esta");
        String tipo = "L";
        int id_o = 0, id_s = 0;

        Localidades local = new Localidades();
        List Estab1 = this.mi.getListarEsta(local);
        Localidades datoestab = (Localidades) Estab1.get(0);
        modelo.put("estab", datoestab);
        modelo.put("id_historial", id_historial);
        modelo.put("id_pedido", id_pedido);
        modelo.put("expedido", expedido);
        modelo.put("id_paciente", id_paciente);
        modelo.put("id_rubro", id_rubro);
        modelo.put("accion", accion);

        Pacientes paciente = new Pacientes();
        paciente.setTipo("U");
        paciente.setId_paciente(Integer.parseInt(id_paciente));
        List buscarPacEmp = this.mi.getListarPacientesCnsUnico(paciente);
        modelo.put("datosPacEmp", buscarPacEmp);

        List listarSeguros = this.mi.getListarSeguros("A");
        modelo.put("listaPacAfi", listarSeguros);

        Historiales datohi = new Historiales();
        datohi.setId_historial(Integer.parseInt(id_historial));
        datohi.setCod_esta(cliente.getCod_esta());
        Historiales datoHis = this.mi.getDatosHistorial(datohi);
        modelo.put("datosHis", datoHis);

        if ("Guardar".equals(boton)) {
            String exodoncia = request.getParameter("exodoncia");
            String periodoncia = request.getParameter("periodoncia");
            String cirugia = request.getParameter("cirugia");
            String endodoncia = request.getParameter("endodoncia");
            String rayosx = request.getParameter("rayosx");
            String preventiva = request.getParameter("preventiva");
            String primera = request.getParameter("primera");
            String numpieza = request.getParameter("numpieza");
            String suma1 = request.getParameter("suma1");
            String suma2 = request.getParameter("suma2");
            String suma3 = request.getParameter("suma3");
            String suma4 = request.getParameter("suma4");
            String suma5 = request.getParameter("suma5");
            String suma6 = request.getParameter("suma6");
            String suma7 = request.getParameter("suma7");
            String suma8 = request.getParameter("suma8");
            String suma9 = request.getParameter("suma9");
            String suma10 = request.getParameter("suma10");
            String fuma = request.getParameter("fuma");
            String alcohol = request.getParameter("alcohol");
            String violencia = request.getParameter("violencia");
            String auto = request.getParameter("auto");
            String urinaria = request.getParameter("urinaria");
            String sistemica = request.getParameter("sistemica");
            String semanas = request.getParameter("semanas");
            String parto = request.getParameter("parto");
            String sexo = request.getParameter("sexo");
            String imc = request.getParameter("imc");
            String nembarazo = request.getParameter("nembarazo");
            String eclam = request.getParameter("eclam");
            String orientacion = request.getParameter("orientacion");
            String diu = request.getParameter("diu");
            String inyectable = request.getParameter("inyectable");
            String nooplasia = request.getParameter("nooplasia");
            String nefro = request.getParameter("nefro");
            String disli = request.getParameter("disli");
            String hemo = request.getParameter("hemo");
            String anestesia = request.getParameter("anestesia");
            String controlpos = request.getParameter("controlpos");
            String sueros = request.getParameter("sueros");
            String curaciones = request.getParameter("curaciones");
            String condon = request.getParameter("condon");
            String tracto = request.getParameter("tracto");
            String lupus = request.getParameter("lupus");
            String litiasis = request.getParameter("litiasis");
            String otro = request.getParameter("otro");
            String cardio = request.getParameter("cardio");
            String reuma = request.getParameter("reuma");
            String eritro = request.getParameter("eritro");
            String renal = request.getParameter("renal");
            String tuberculo = request.getParameter("tuberculo");
            String ingreso = request.getParameter("ingreso");
            String anastecia = request.getParameter("anastecia");
            String servicio = request.getParameter("servicio");
            String frenal = request.getParameter("frenal");
            String bajopeso = request.getParameter("bajopeso");
            String prematuro = request.getParameter("prematuro");
            String epilepsia = request.getParameter("epilepsia");
            String psico = request.getParameter("psico");
            String discapa = request.getParameter("discapa");
            String cancer = request.getParameter("cancer");
            String cancero = request.getParameter("renal");
            String depre = request.getParameter("depre");
            String pildora = request.getParameter("pildora");
            String aqv = request.getParameter("aqv");
            String mnatural = request.getParameter("mnatural");
            String insumos = request.getParameter("insumos");
            String pap = request.getParameter("pap");
            String tabletas = request.getParameter("tabletas");
            String sfembarazada = request.getParameter("sfembarazada");
            String sfpuerpera = request.getParameter("sfpuerpera");
            String vitamina = request.getParameter("vitamina");
            String suma11 = request.getParameter("suma11");
            String suma12 = request.getParameter("suma12");
            String suma13 = request.getParameter("suma13");
            String suma14 = request.getParameter("suma14");
            String suma15 = request.getParameter("suma15");
            String suma16 = request.getParameter("suma16");
            String suma17 = request.getParameter("suma17");
            String suma18 = request.getParameter("suma18");
            String suma19 = request.getParameter("suma19");
            String suma20 = request.getParameter("suma20");
            String suma21 = request.getParameter("suma21");
            String suma22 = request.getParameter("suma22");
            String suma23 = request.getParameter("suma23");
            String suma24 = request.getParameter("suma24");
            String suma25 = request.getParameter("suma25");
            String suma26 = request.getParameter("suma26");
            String suma27 = request.getParameter("suma27");
            String suma28 = request.getParameter("suma28");
            String suma29 = request.getParameter("suma29");
            String suma30 = request.getParameter("suma30");
            String suma31 = request.getParameter("suma31");

            Cuadernos datol = new Cuadernos();
            datol.setId_historial(2);
            datol.setExodoncia(Integer.parseInt(exodoncia));
            datol.setPeriodoncia(Integer.parseInt(periodoncia));
            datol.setCirugia(Integer.parseInt(cirugia));
            datol.setEndodoncia(Integer.parseInt(endodoncia));
            datol.setRayosx(Integer.parseInt(rayosx));
            datol.setPreventiva(Integer.parseInt(preventiva));
            datol.setPrimera(Integer.parseInt(primera));
            datol.setNumpieza(Integer.parseInt(numpieza));
            datol.setSuma1(Integer.parseInt(suma1));
            datol.setSuma2(Integer.parseInt(suma2));
            datol.setSuma3(Integer.parseInt(suma3));
            datol.setSuma4(Integer.parseInt(suma4));
            datol.setSuma5(Integer.parseInt(suma5));
            datol.setSuma6(Integer.parseInt(suma6));
            datol.setSuma7(Integer.parseInt(suma7));
            datol.setSuma8(Integer.parseInt(suma8));
            datol.setSuma9(Integer.parseInt(suma9));
            datol.setSuma10(Integer.parseInt(suma10));
            datol.setFuma(Integer.parseInt(fuma));
            datol.setAlcohol(Integer.parseInt(alcohol));
            datol.setViolencia(Integer.parseInt(violencia));
            datol.setAuto(Integer.parseInt(auto));
            datol.setUrinaria(Integer.parseInt(urinaria));
            datol.setSistemica(Integer.parseInt(sistemica));
            datol.setSemanas(Integer.parseInt(semanas));
            datol.setParto(Integer.parseInt(parto));
            datol.setSexo(Integer.parseInt(sexo));
            datol.setImc(Integer.parseInt(imc));
            datol.setNembarazo(Integer.parseInt(nembarazo));
            datol.setEclam(Integer.parseInt(eclam));
            datol.setOrientacion(Integer.parseInt(orientacion));
            datol.setDiu(Integer.parseInt(diu));
            datol.setInyectable(Integer.parseInt(inyectable));
            datol.setNooplasia(Integer.parseInt(nooplasia));
            datol.setNefro(Integer.parseInt(nefro));
            datol.setDisli(Integer.parseInt(disli));
            datol.setHemo(Integer.parseInt(hemo));
            datol.setAnestesia(Integer.parseInt(anestesia));
            datol.setControlpos(Integer.parseInt(controlpos));
            datol.setSueros(Integer.parseInt(sueros));
            datol.setCuraciones(Integer.parseInt(curaciones));
            datol.setCondon(Integer.parseInt(condon));
            datol.setTracto(Integer.parseInt(tracto));
            datol.setLupus(Integer.parseInt(lupus));
            datol.setLitiasis(Integer.parseInt(litiasis));
            datol.setOtro(Integer.parseInt(otro));
            datol.setCardio(Integer.parseInt(cardio));
            datol.setReuma(Integer.parseInt(reuma));
            datol.setEritro(Integer.parseInt(eritro));
            datol.setRenal(Integer.parseInt(renal));
            datol.setTuberculo(Integer.parseInt(tuberculo));
            datol.setIngreso(Integer.parseInt(ingreso));
            datol.setAnastecia(Integer.parseInt(anastecia));
            datol.setServicio(Integer.parseInt(servicio));
            datol.setFrenal(Integer.parseInt(frenal));
            datol.setBajopeso(Integer.parseInt(bajopeso));
            datol.setPrematuro(Integer.parseInt(prematuro));
            datol.setEpilepsia(Integer.parseInt(epilepsia));
            datol.setPsico(Integer.parseInt(psico));
            datol.setDiscapa(Integer.parseInt(discapa));
            datol.setCancer(Integer.parseInt(cancer));
            datol.setCancero(Integer.parseInt(cancero));
            datol.setDepre(Integer.parseInt(depre));
            datol.setPildora(Integer.parseInt(pildora));
            datol.setAqv(Integer.parseInt(aqv));
            datol.setMnatural(Integer.parseInt(mnatural));
            datol.setInsumos(Integer.parseInt(insumos));
            datol.setPap(Integer.parseInt(pap));
            datol.setTabletas(Integer.parseInt(tabletas));
            datol.setSfembarazada(Integer.parseInt(sfembarazada));
            datol.setSfpuerpera(Integer.parseInt(sfpuerpera));
            datol.setVitamina(Integer.parseInt(vitamina));
            datol.setSuma11(Integer.parseInt(suma11));
            datol.setSuma12(Integer.parseInt(suma12));
            datol.setSuma13(Integer.parseInt(suma13));
            datol.setSuma14(Integer.parseInt(suma14));
            datol.setSuma15(Integer.parseInt(suma15));
            datol.setSuma16(Integer.parseInt(suma16));
            datol.setSuma17(Integer.parseInt(suma17));
            datol.setSuma18(Integer.parseInt(suma18));
            datol.setSuma19(Integer.parseInt(suma19));
            datol.setSuma20(Integer.parseInt(suma20));
            datol.setSuma21(Integer.parseInt(suma21));
            datol.setSuma22(Integer.parseInt(suma22));
            datol.setSuma23(Integer.parseInt(suma23));
            datol.setSuma24(Integer.parseInt(suma24));
            datol.setSuma25(Integer.parseInt(suma25));
            datol.setSuma26(Integer.parseInt(suma26));
            datol.setSuma27(Integer.parseInt(suma27));
            datol.setSuma28(Integer.parseInt(suma28));
            datol.setSuma29(Integer.parseInt(suma29));
            datol.setSuma30(Integer.parseInt(suma30));
            datol.setSuma31(Integer.parseInt(suma31));

            this.mi.setModificarImpOrina(datol);

            return new ModelAndView("Aviso", "mensaje", "Se grabo correctamente");
        }

        if ("VerEndo".equals(accion)) {
            String id_tipo = request.getParameter("id_tipo");
            String id_cuaderno = request.getParameter("id_cuaderno");
            Pacientes buscarPaciente = this.mi.getDatosPaciente(Integer.parseInt(id_paciente)); // saca un registro a ser modificado
            modelo.put("datos", buscarPaciente);
            Personas buscarEmpleado = this.mi.getDatosPersona(datoHis.getId_persona());
            modelo.put("datosMed", buscarEmpleado);

            Cuadernos dato = new Cuadernos();
            dato.setId_persona(Integer.parseInt(id_paciente));
            dato.setId_paciente(Integer.parseInt(id_paciente));
            dato.setTipoconsulta("A");
            List listasLab = this.mi.getPacienteResultadoLab(dato);
            List listalab1 = this.mi.getLabMedico(dato);
            dato.setTipoconsulta("D");  ////getPacienteResultadoLabEndo
            List listasLabEndo = this.mi.getPacienteResultadoLabEndo(dato);
            modelo.put("listalabEndo", listasLabEndo);
            modelo.put("listalab", listalab1);
            modelo.put("milista", listasLab);
            modelo.put("dato", cliente);

            File dir = new File("C:/opt/Tomcat/webapps/endoscopia/assets/img/imageneshistorial/" + id_historial + "/14");

            int contador = 0;
            if (numberOfFiles(dir) == 0) {
                contador = 1;
            } else {
                contador = numberOfFiles(dir);
                contador = contador + 1;
            }

            System.out.println("eloy");
            System.out.println(contador);

            return new ModelAndView("administrarlaboratorio/EndoscopiasPaciente", modelo);
        }

        if ("ImpEndo".equals(accion)) {
            String id_tipo = request.getParameter("id_tipo");
            String id_cuaderno = request.getParameter("id_cuaderno");

            Pacientes buscarPaciente = this.mi.getDatosPaciente(Integer.parseInt(id_paciente)); // saca un registro a ser modificado
            modelo.put("datos", buscarPaciente);
            Personas buscarEmpleado = this.mi.getDatosPersona(datoHis.getId_persona());
            modelo.put("datosMed", buscarEmpleado);

            Cuadernos dato = new Cuadernos();
            dato.setId_persona(Integer.parseInt(id_paciente));
            dato.setId_paciente(Integer.parseInt(id_paciente));
            dato.setTipoconsulta("A");
            List listasLab = this.mi.getPacienteResultadoLab(dato);
            List listalab1 = this.mi.getLabMedico(dato);
            dato.setTipoconsulta("D");  ////getPacienteResultadoLabEndo
            List listasLabEndo = this.mi.getPacienteResultadoLab(dato);
            modelo.put("listalabEndo", listasLabEndo);
            modelo.put("listalab", listalab1);
            modelo.put("milista", listasLab);
            dato.setTipoconsulta("F");  ////getDetalleFotosEndo
            dato.setId_cuaderno(Integer.parseInt(id_cuaderno));
            dato.setId_historial(Integer.parseInt(id_historial));
            List listasLabDetEndo = this.mi.getDetalleFotosEndo(dato);
            modelo.put("listalabDetEndo", listasLabDetEndo);
            modelo.put("dato", cliente);

            //File dir = new File("c:\winnt");
            //File dir = new File(hsr.getRealPath("")+"/assets/img/imageneshistorial/"+id_historial+"/"+"14");
            return new ModelAndView(new VerEndosPDF(), modelo);
        }

        if ("ImpOrinas".equals(accion)) {
            String id_tipo = request.getParameter("id_tipo");
            Cuadernos dato = new Cuadernos();
            dato.setTipo_egreso(1);
            dato.setId_laboratorio(2);
            List datosgral = this.mi.getConfigurarImpresion(dato);
            modelo.put("datosgral", datosgral);
            dato.setTipo_egreso(3);
            List datosImpOrin = this.mi.getImpresionOrina(dato);
            modelo.put("datosImpOrin", datosImpOrin);
            return new ModelAndView("administrarlaboratorios/ConfImpOrinas", modelo);
        }

        if ("imprimir".equals(accion)) {
            String fechaped = request.getParameter("fechaped");

            Pacientes buscarPaciente = this.mi.getDatosPaciente(Integer.parseInt(id_paciente)); // saca un registro a ser modificado
            modelo.put("datos", buscarPaciente);

            String id_sexo_e = Integer.toString(buscarPaciente.getId_tipo_sexo());
            Date fecha_nac = buscarPaciente.getFec_nacimiento();
            int xanio = fecha_nac.getYear() + 1900;
            int xmes = fecha_nac.getMonth() + 1;
            int xdia = fecha_nac.getDate();
            Sexos buscarsexo = this.mi.getDatosSexo(Integer.parseInt(id_sexo_e)); // saca un registro a ser modificado
            modelo.put("buscarSexo", buscarsexo);
            String a = "/";
            String fecha_nacimiento = Integer.toString(xdia) + a + Integer.toString(xmes) + a + Integer.toString(xanio);
            modelo.put("fec_nacimiento", fecha_nacimiento);
            modelo.put("fechaped", fechaped);

            Cuadernos datol1 = new Cuadernos();
            datol1.setTipo(tipo);
            datol1.setId_pedido(Integer.parseInt(id_pedido));
            datol1.setId_historial(Integer.parseInt(id_historial));
            datol1.setCod_esta(cliente.getCod_esta());
            if (!"".equals(cod_esta) && cod_esta != null) {
                datol1.setCod_esta(Integer.parseInt(cod_esta));
            }
            if (cliente.getId_laboratorio() == 0) {
                datol1.setId_cargo(0);
                datol1.setId_laboratorio(999);
            } else {
                datol1.setId_cargo(cliente.getId_laboratorio());
                datol1.setId_laboratorio(cliente.getId_laboratorio());
            }
            List listalab = this.mi.getLabPendiente(datol1); ////////los laboratorios
            String hemos = "0", quimi = "0", sero = "0", orin = "0", paras = "0", embar = "0", cultivo = "0", antibio = "0", basilo = "0", liqui = "0";
            String bacter = "0", cito = "0", hormo = "0", otr = "0", hemodet = "0", orindet = "0";

            for (int i = 0; i < listalab.size(); i++) {
                Cuadernos datoLab1 = (Cuadernos) listalab.get(i);
                switch (datoLab1.getId_laboratorio()) {
                    case 1:
                        hemos = "1";
                        break;
                    case 2:
                        quimi = "1";
                        break;
                    case 3:
                        sero = "1";
                        break;
                    case 4:
                        orin = "1";
                        break;
                    case 5:
                        paras = "1";
                        break;
                    case 6:
                        embar = "1";
                        break;
                    case 7:
                        cultivo = "1";
                        break;
                    case 8:
                        basilo = "1";
                        break;
                    case 9:
                        liqui = "1";
                        break;
                    case 10:
                        bacter = "1";
                        break;
                    case 11:
                        cito = "1";
                        break;
                    case 17:
                        hormo = "1";
                        break;
                    default:
                        otr = "1";
                }
            }
            modelo.put("hemos", hemos);
            modelo.put("quimi", quimi);
            modelo.put("sero", sero);
            modelo.put("orin", orin);
            modelo.put("paras", paras);
            modelo.put("embar", embar);
            modelo.put("cultivo", cultivo);
            modelo.put("basilo", basilo);
            modelo.put("liqui", liqui);
            modelo.put("bacter", bacter);
            modelo.put("cito", cito);
            modelo.put("hormo", hormo);
            modelo.put("otr", otr);

            datol1.setId_cargo(id_s);
            List hemo = this.mi.getHemograma(datol1);
            if (hemo.size() > 0) {
                hemodet = "1";
                modelo.put("hemo", hemo);
            }

            datol1.setId_cargo(id_o);
            List orina = this.mi.getOrina(datol1);
            if (orina.size() > 0) {
                orindet = "1";
                modelo.put("orina", orina);
            }
            return new ModelAndView("administrarlaboratorio/AdmImpresion", modelo);
        }

        modelo.put("dato", cliente);

        tipo = "I";

        if (datosconsul.getId_cargo() == 18 || datosconsul.getId_cargo() == 24) {
            modelo.put("consultorio", Integer.toString(10));
            tipo = "M";   /// si es desde medicos se pueda ver el reporte de laboratrorio
        } else {
            modelo.put("consultorio", Integer.toString(datosconsul.getId_cargo()));
        }

        if ("".equals(id_persona) || id_persona == null) {
            id_persona = Integer.toString(datoHis.getId_persona());
        }

        Cuadernos datol1 = new Cuadernos();
        datol1.setTipo(tipo);
        datol1.setId_pedido(Integer.parseInt(id_pedido));
        datol1.setId_historial(Integer.parseInt(id_historial));
        datol1.setCod_esta(cliente.getCod_esta());
        if (!"".equals(cod_esta) && cod_esta != null) {
            datol1.setCod_esta(Integer.parseInt(cod_esta));
        }
        if ("0".equals(cod_esta)) {
            datol1.setCod_esta(cliente.getCod_esta());
        }

        if (cliente.getId_laboratorio() == 0) {
            datol1.setId_cargo(0);
            datol1.setId_laboratorio(999);
        } else {
            datol1.setId_cargo(cliente.getId_laboratorio());
            datol1.setId_laboratorio(cliente.getId_laboratorio());
        }
        if (!"".equals(id_laboratorio) && id_laboratorio != null) {
            datol1.setId_cargo(Integer.parseInt(id_cargo));
            datol1.setId_laboratorio(Integer.parseInt(id_laboratorio));
        }

        List listalab = this.mi.getLabPendiente(datol1); ////////los laboratorios
        if (datosconsul.getId_cargo() != 12 && datosconsul.getId_cargo() != 11) {
            listalab = this.mi.getLabPendiente(datol1);////10-05-2015
            modelo.put("datosLab", listalab);
        }
        if (datosconsul.getId_cargo() == 11) {
            listalab = this.mi.getLabPendienteEco(datol1);
            modelo.put("datosLab", listalab);
        }
        if (datosconsul.getId_cargo() == 12) {
            listalab = this.mi.getLabPendienteRx(datol1);
            modelo.put("datosLab", listalab);
        }
        if ("imprimirEco".equals(accion)) {
            listalab = this.mi.getLabPendienteEco(datol1);
            modelo.put("datosLab", listalab);
            accionl = "general";
        }
        //List listalab = this.mi.getLabPendiente(datol1); ////////los laboratorios
        //modelo.put("datosLab", listalab);

        if (listalab.isEmpty()) {
            return new ModelAndView("Aviso", "mensaje", "No existe Laboratorios Realizados");
        }

        for (int i = 0; i < listalab.size(); i++) {
            Cuadernos datoLab1 = (Cuadernos) listalab.get(i);
            id_costo = Integer.toString(datoLab1.getId_costo());
            if (datoLab1.getId_costo() == 137) {
                id_s = datoLab1.getOtras();
            }
            if (datoLab1.getId_costo() == 121) {
                id_o = datoLab1.getOtras();
            }
        }

        Costos costo = new Costos();
        costo.setId_costo(Integer.parseInt(id_costo));
        costo.setCod_esta(cliente.getCod_esta());
        if (!"".equals(cod_esta) && cod_esta != null) {
            costo.setCod_esta(Integer.parseInt(cod_esta));
        }
        if ("0".equals(cod_esta)) {
            costo.setCod_esta(cliente.getCod_esta());
        }
        Costos buscarCosto = this.mi.getDatosCosto(costo);
        Costos datoq = new Costos();
        datoq.setId_rubro(buscarCosto.getId_rubro());
        datoq.setId_prestacion(buscarCosto.getId_rubro());
        datoq.setId_estado("%");
        datoq.setTipo(0);
        datoq.setId_persona(5000);
        datoq.setId_historial(Integer.parseInt(id_historial));
        datoq.setCod_esta(cliente.getCod_esta());///para todos 20-02-2017
        datoq.setMuestra("T");///para todos 18-05-2015
        datoq.setDefecto("%");///para todos 06-04-2016
        List listarLabo = this.mi.getListarTodos(datoq);
        modelo.put("listarLab", listarLabo);
        if (cliente.getId_cargo() == 11) {
            datoq.setMuestra("E");  ////para ecografia
            List listarLaboe = this.mi.getListarCostosEco(datoq);
            modelo.put("listarLab", listarLaboe);
        }
        if (cliente.getId_cargo() == 12) {
            datoq.setMuestra("V");  ////para RayosX
            List listarLabox = this.mi.getListarCostosRx(datoq);
            modelo.put("listarLab", listarLabox);
        }
        datoq.setEmergencias(0);
        if (!"".equals(id_laboratorio) && id_laboratorio != null) {
            datoq.setTipo(Integer.parseInt(id_cargo));
            datoq.setId_persona(Integer.parseInt(id_laboratorio));
        }
        Cuadernos dato = new Cuadernos();
        dato.setTipoconsulta("I");
        dato.setId_persona(Integer.parseInt(id_persona));
        dato.setId_pedido(Integer.parseInt(id_pedido));
        dato.setId_historial(Integer.parseInt(id_historial));
        dato.setCod_esta(cliente.getCod_esta());
        if (!"".equals(cod_esta) && cod_esta != null) {
            dato.setCod_esta(Integer.parseInt(cod_esta));
        }
        if (cliente.getId_laboratorio() == 0) {
            dato.setId_cargo(0);
            dato.setId_laboratorio(999);
        } else {
            dato.setId_cargo(cliente.getId_laboratorio());
            dato.setId_laboratorio(cliente.getId_laboratorio());
        }

        List lista = this.mi.getDatoPedidoLab(dato);   ////pedidos de tabla pedidoslab
        Cuadernos listal = (Cuadernos) lista.get(0);
        modelo.put("lista", listal);
        modelo.put("accionl", accionl);

        if ("imprimir".equals(accion)) {

        }

        dato.setId_cargo(id_s);
        List hemo = this.mi.getHemograma(dato);
        modelo.put("hemo", hemo);

        dato.setId_cargo(id_o);
        List orina = this.mi.getOrina(dato);
        modelo.put("orina", orina);

        Personas buscarEmpleado = this.mi.getDatosPersona(cliente.getId_persona());/////datoHis.getId_persona() ahora firma laboratorista
        modelo.put("datosMed", buscarEmpleado);

        Pacientes buscarPaciente = this.mi.getDatosPaciente(datoHis.getId_paciente());
        modelo.put("datos", buscarPaciente);

        if ("pred".equals(accionpred)) { ///para informes prediseï¿½ados
            if ("hemograma".equals(accionl)) {
                dato.setTipo_egreso(1);
                dato.setId_laboratorio(1);
                List datosgral = this.mi.getConfigurarImpresion(dato);
                modelo.put("datosgral", datosgral);
                dato.setTipo_egreso(2);
                List datosImpHemo = this.mi.getImpresionHemograma(dato);
                modelo.put("datosImpHemo", datosImpHemo);
            } else {
                if ("orinas".equals(accionl)) {
                    dato.setTipo_egreso(1);
                    dato.setId_laboratorio(2);
                    List datosgral = this.mi.getConfigurarImpresion(dato);
                    modelo.put("datosgral", datosgral);
                    dato.setTipo_egreso(3);
                    List datosImpOrin = this.mi.getImpresionOrina(dato);
                    modelo.put("datosImpOrin", datosImpOrin);
                } else {
                    if ("serologia".equals(accionl)) {
                        dato.setTipo_egreso(1);//Para el tipo de Impresion en tabla SqlMap, saca datos generales
                        dato.setId_laboratorio(6);
                        List datosgral = this.mi.getImpresionQuimicas(dato);
                        modelo.put("datosgral", datosgral);
                        dato.setTipo_egreso(7); //Para el tipo de Impresion en tabla conf_impresion
                        List datosImpOtro = this.mi.getImpresionEcografia(dato);
                        modelo.put("datosImpOtro", datosImpOtro);
                        tipo = "S";

                        dato.setTipoconsulta(tipo);
                        dato.setId_pedido(Integer.parseInt(id_pedido));
                        dato.setId_historial(Integer.parseInt(id_historial));
                        dato.setCod_esta(cliente.getCod_esta());
                        if (cliente.getId_laboratorio() == 0) {
                            dato.setId_cargo(0);
                            dato.setId_laboratorio(999);
                        } else {
                            dato.setId_cargo(cliente.getId_laboratorio());
                            dato.setId_laboratorio(cliente.getId_laboratorio());
                        }
                        List listalabq = this.mi.getLabPendiente(dato);
                        modelo.put("datosLab", listalabq);
                    } else {
                        if ("qsangre".equals(accionl)) {
                            dato.setTipo_egreso(1);
                            dato.setId_laboratorio(4);  //para elegir de la tabla Conf_impresion
                            List datosgral = this.mi.getImpresionOtros(dato);
                            modelo.put("datosgral", datosgral);
                            dato.setTipo_egreso(6);
                            List datosImpOtro = this.mi.getImpresionQuimicas(dato);
                            modelo.put("datosImpOtro", datosImpOtro);
                            tipo = "Q";
                            dato.setTipoconsulta(tipo);
                            dato.setId_pedido(Integer.parseInt(id_pedido));
                            dato.setId_historial(Integer.parseInt(id_historial));
                            dato.setCod_esta(cliente.getCod_esta());
                            if (cliente.getId_laboratorio() == 0) {
                                dato.setId_cargo(0);
                                dato.setId_laboratorio(999);
                            } else {
                                dato.setId_cargo(cliente.getId_laboratorio());
                                dato.setId_laboratorio(cliente.getId_laboratorio());
                            }
                            List listalabq = this.mi.getLabPendiente(dato);
                            modelo.put("datosLab", listalabq);
                        } else {
                            if ("embarazo".equals(accionl)) {
                                dato.setTipo_egreso(1);
                                dato.setId_laboratorio(5);
                                List datosgral = this.mi.getConfigurarImpresion(dato);
                                modelo.put("datosgral", datosgral);
                                dato.setTipo_egreso(5);
                                List datosEmba = this.mi.getImpresionEmbarazo(dato);
                                modelo.put("datosEmba", datosEmba);
                            } else {
                                if ("Ecos".equals(accionl)) {
                                    dato.setTipo_egreso(1);
                                    dato.setId_laboratorio(7);
                                    List datosgral = this.mi.getConfigurarImpresion(dato);
                                    modelo.put("datosgral", datosgral);
                                    dato.setTipo_egreso(7);
                                    List datosEco = this.mi.getImpresionEcografia(dato);
                                    modelo.put("datosEco", datosEco);
                                } else {
                                    if ("quimicas".equals(accionl)) {
                                        dato.setTipo_egreso(1);
                                        dato.setId_laboratorio(3);
                                        List datosgral = this.mi.getConfigurarImpresion(dato);
                                        modelo.put("datosgral", datosgral);
                                        dato.setTipo_egreso(4);
                                        List datosImpOtro = this.mi.getImpresionOtros(dato);
                                        modelo.put("datosImpOtro", datosImpOtro);
                                        tipo = "Q";
                                        dato.setTipoconsulta(tipo);
                                        dato.setId_pedido(Integer.parseInt(id_pedido));
                                        dato.setId_historial(Integer.parseInt(id_historial));
                                        dato.setCod_esta(cliente.getCod_esta());
                                        if (cliente.getId_laboratorio() == 0) {
                                            dato.setId_cargo(0);
                                            dato.setId_laboratorio(999);
                                        } else {
                                            dato.setId_cargo(cliente.getId_laboratorio());
                                            dato.setId_laboratorio(cliente.getId_laboratorio());
                                        }
                                        List listalabq = this.mi.getLabPendiente(dato);
                                        modelo.put("datosLab", listalabq);
                                    } else {
                                        dato.setTipo_egreso(1);
                                        dato.setId_laboratorio(3);
                                        List datosgral = this.mi.getConfigurarImpresion(dato);
                                        modelo.put("datosgral", datosgral);
                                        dato.setTipo_egreso(4);
                                        List datosImpOtro = this.mi.getImpresionOtros(dato);
                                        modelo.put("datosImpOtro", datosImpOtro);
                                        tipo = "O";
                                        dato.setTipoconsulta(tipo);
                                        dato.setId_pedido(Integer.parseInt(id_pedido));
                                        dato.setId_historial(Integer.parseInt(id_historial));
                                        dato.setCod_esta(cliente.getCod_esta());
                                        if (cliente.getId_laboratorio() == 0) {
                                            dato.setId_cargo(0);
                                            dato.setId_laboratorio(999);
                                        } else {
                                            dato.setId_cargo(cliente.getId_laboratorio());
                                            dato.setId_laboratorio(cliente.getId_laboratorio());
                                        }
                                        List listalabq = this.mi.getLabPendiente(dato);
                                        modelo.put("datosLab", listalabq);
                                    }

                                }
                            }
                        }
                    }
                }
            }

            return new ModelAndView(new ImpHemoPDF(), modelo);
        }
        if (persona.getUrgencias() == 1) {
            return new ModelAndView(new VerLaboratorioUrgCNSPDF(), modelo);
        }
        return new ModelAndView(new VerLaboratorioPDF(), modelo);
    }

    public int numberOfFiles(File srcDir) {
        int count = 0;
        File[] listFiles = srcDir.listFiles();
        for (int i = 0; i < listFiles.length; i++) {
            if (listFiles[i].isDirectory()) {
                count += numberOfFiles(listFiles[i]);
            } else if (listFiles[i].isFile()) {
                count++;
            }
        }
        return count;
    }

}
