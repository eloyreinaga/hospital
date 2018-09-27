package org.ayaic.web.administrarhistoriales;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Camas;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Cuadernos;
import org.ayaic.domain.Historiales;
import org.ayaic.domain.Localidades;
import org.ayaic.domain.Medicamentos;
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.Salas;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class InternarPacControlador {

    private final MiFacade mi;

    public InternarPacControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/InternarPac.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String swinter = request.getParameter("swinter");
        String accionc = request.getParameter("accionc");
        String boton = request.getParameter("boton");
        String nombres = request.getParameter("nombres");
        String id_paciente = request.getParameter("id_paciente");
        String id_reservacion = request.getParameter("id_reservacion");
        String id_consultorio = request.getParameter("id_consultorio");
        String id_persona = request.getParameter("id_persona");
        String codigo = request.getParameter("codigo");
        String literal = request.getParameter("literal");
        String id_cama = request.getParameter("id_cama");
        String id_historial = request.getParameter("id_historial");
        String id_historia = request.getParameter("id_historia");
        String cama = request.getParameter("cama");
        String cama_unit = request.getParameter("cama_unit");
        String id_sala = request.getParameter("id_sala");
        String id_piso = request.getParameter("id_piso");
        String camaactual = request.getParameter("camaactual");

        Localidades local = new Localidades();
        List Estab1 = this.mi.getListarEsta(local);
        Localidades datoestab = (Localidades) Estab1.get(0);
        modelo.put("codesta", Integer.toString(datoestab.getCod_esta()));
        
        Medicamentos medid = new Medicamentos();
        medid.setCod_esta(cliente.getCod_esta());
        medid.setId_persona(Integer.parseInt(id_persona));
        List listarEnfermedadesCot = this.mi.getListarEnfermedadesCot(medid);
        modelo.put("listarEnfermedadesCot", listarEnfermedadesCot);

        Pacientes buscarPaciente = this.mi.getDatosPaciente(Integer.parseInt(id_paciente)); // saca un registro a ser modificado
        modelo.put("datos", buscarPaciente);

        modelo.put("accion", accion);
        modelo.put("sw1", request.getParameter("sw1"));
        modelo.put("id_paciente", id_paciente);
        modelo.put("id_reservacion", id_reservacion);
        modelo.put("id_consultorio", id_consultorio);
        modelo.put("id_persona", id_persona);
        modelo.put("codigo", codigo);
        modelo.put("literal", literal);
        modelo.put("id_sala", id_sala);
        modelo.put("id_cama", id_cama);
        modelo.put("id_piso", id_piso);

        if (id_reservacion == null || "".equals(id_reservacion)) {
            id_reservacion = id_historial;
        }

        Historiales datohi = new Historiales();
        datohi.setId_historial(Integer.parseInt(id_reservacion));
        datohi.setCod_esta(cliente.getCod_esta());
        Historiales datosHistorial = this.mi.getDatosHistorial(datohi);

        Historiales datomorbi = new Historiales();
        datomorbi.setId_reservacion(Integer.parseInt(id_reservacion));
        datomorbi.setCod_esta(cliente.getCod_esta());
        List listarmorbi1 = this.mi.getListaMorbi(datomorbi);
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
            morbi.setTipo("B");
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
            List listarmorbi = this.mi.getListaMorbi(datomorbi);
            modelo.put("morbi", listarmorbi);
            modelo.put("accion", "Otro");

            if (listarmorbi.isEmpty() != true) {
                modelo.put("terminar", "si");
            }
        }

        if ("Buscar Enfermedad".equals(boton)) {
            List listarEnfermedades = this.mi.getListarEnfermedades(nombres);
            modelo.put("listarEnfermedades", listarEnfermedades);
        }

        if ("Buscar CIE10".equals(boton)) {
            List listarEnfermedades = this.mi.getListarEnfermedadesCod(nombres);
            modelo.put("listarEnfermedades", listarEnfermedades);
        }

        if ("Terminar".equals(accion)) {
            String tingreso = request.getParameter("tingreso");
            Cuadernos datoc5 = new Cuadernos();
            datoc5.setId_historial(Integer.parseInt(id_reservacion));
            datoc5.setCod_esta(cliente.getCod_esta());
            List C5 = this.mi.getPacienteCuaderno5(datoc5);
            if (C5.isEmpty() != true) {
                datoc5.setTipo("P");
                this.mi.setModificarAdmision2(datoc5);  ///setModificarAdmision2 cambia tabla admsiones estado=1 internado
                return new ModelAndView("Aviso", "mensaje", "EL PACIENTE YA HA SIDO INTERNADO ANTERIORMENTE");
            } else {
                if ((Integer.parseInt(id_sala) == 0 || Integer.parseInt(id_cama) == 0) && datoestab.getCod_esta() != 200010) {
                    return new ModelAndView("Aviso", "mensaje", "Debe Seleccionar una Sala y una Cama para el Internar al Paciente");
                } else {
                    Cuadernos dato5 = new Cuadernos();
                    dato5.setCod_esta(cliente.getCod_esta());  ///16-01-2016
                    dato5.setAspecto("M");
                    dato5.setId_historial(Integer.parseInt(id_reservacion));
                    List listaCie = this.mi.getVerCuaderno1CieMorbi(dato5);
                    String CIE10i = "";
                    String CIE10e = "";
                    for (int i = 0; i < listaCie.size(); i++) {
                        Cuadernos cie10 = (Cuadernos) listaCie.get(i);
                        if ("B".equals(cie10.getRegistro())) {
                            CIE10i = cie10.getTipodent() + ";" + CIE10i;
                            modelo.put("CieI", CIE10i);
                        } else {
                            CIE10e = cie10.getTipodent() + ";" + CIE10e;
                            modelo.put("CieE", CIE10e);
                        }
                    }
                    if ("0".equals(id_persona)) {
                        id_persona = Integer.toString(datosHistorial.getId_persona());
                    }
                    if ("0".equals(id_consultorio)) {
                        id_consultorio = Integer.toString(datosHistorial.getId_consultorio());
                    }
                    if (datoestab.getCod_esta() == 200010) {  /////31-08-2015
                        dato5.setId_persona(Integer.parseInt(id_persona));
                        dato5.setId_consultorio(Integer.parseInt(id_consultorio));
                    } else {
                        dato5.setId_persona(cliente.getId_persona());
                    }
                    dato5.setId_historial(Integer.parseInt(id_reservacion));
                    dato5.setServicio(Integer.parseInt(id_consultorio));
                    dato5.setReferido("");
                    dato5.setContraref("");
                    dato5.setDiagnostico_ing(CIE10i);
                    dato5.setDiagnostico("");
                    dato5.setDiasi(0);
                    dato5.setDiasc(0);
                    dato5.setId_estado("A");
                    if ("".equals(id_piso) || id_piso == null) {
                        id_piso = "0";
                    }
                    if ("R".equals(tingreso)) {
                        dato5.setIngreso(0);
                    }
                    if ("E".equals(tingreso)) {
                        dato5.setIngreso(1);
                    }
                    dato5.setCirugia(0);
                    dato5.setAnastecia(0);
                    dato5.setEgreso(0);
                    dato5.setTipo_egreso(0);
                    dato5.setFallecido(0);
                    dato5.setFallecidom5(0);
                    dato5.setFallecidoy5(9);
                    dato5.setId_sala(Integer.parseInt(id_sala));
                    dato5.setId_cama(Integer.parseInt(id_cama));
                    dato5.setId_piso(Integer.parseInt(id_piso));
                    dato5.setId_por(cliente.getId_persona());
                    dato5.setCod_esta(cliente.getCod_esta());
                    this.mi.setCrearCuaderno5(dato5);

                    Historiales datoi = new Historiales();
                    datoi.setId_persona(Integer.parseInt(id_persona));
                    datoi.setAccion("R"); ////setModificarInternado
                    datoi.setId_historial(Integer.parseInt(id_reservacion));
                    datoi.setId_cargo(2);
                    datoi.setId_ecivil(2);////internado por medico o personal designado
                    datoi.setExpedido("B");
                    datoi.setCod_esta(cliente.getCod_esta());
                    this.mi.setModificarMorbilidad(datoi);
                    this.mi.setModificarInternado(datoi); ////setModificarInternado

                    datoi.setId_consultorio(Integer.parseInt(request.getParameter("id_consultorio")));
                    datoi.setId_persona(Integer.parseInt(id_persona));
                    datoi.setDiagnostico("Internar Paciente");
                    datoi.setId_estado("A");
                    datoi.setId_cargo(Integer.parseInt(id_sala));
                    datoi.setId_sexo(Integer.parseInt(id_cama));
                    datoi.setId_piso(Integer.parseInt(id_piso));
                    datoi.setAccion("A");
                    datoi.setId_por(cliente.getId_persona());
                    if (cliente.getId_especialidad() == 99) {
                        datoi.setId_persona(datosHistorial.getId_persona());
                    }
                    try {
                        this.mi.setCrearInternado2(datoi);    /////setCrearInternado2   para los obreros

                        datoi.setId_persona(cliente.getId_persona());
                        datoi.setAccion("R");

                        datoi.setCadena(buscarPaciente.getNombres().replaceAll(" +", " ").replaceAll("\\s", ":*&").replaceAll("Ñ", "N") + ":*");

                        datoi.setAccion("M");  ////getInternadosSalaCajaNombre
                        datoi.setSuma1(2);  ////para buscar solo en internados
                        datoi.setSuma2(2);
                        datoi.setCod_esta(cliente.getCod_esta());
                        List listarPacInter = this.mi.getInternadosSalaCajaNombre(datoi);
                        modelo.put("listarPacInter", listarPacInter);
                    } catch (Exception e) {
                        return new ModelAndView("Aviso", "mensaje", "El registro no se grabo");
                    }

                    List listarSeguros = this.mi.getListarSeguros("A");
                    modelo.put("listaPacAfi", listarSeguros);

                    Camas listcama = new Camas();
                    List listarCamasSala = this.mi.getListarCamasSala(listcama);
                    //List listarCamasSala = this.mi.getListarCamasSala();
                    modelo.put("listarCamasSala", listarCamasSala);
                    return new ModelAndView("administrarhistoriales/ListarInternados", modelo);
                    //return new ModelAndView("Aviso","mensaje","El Paciente fue Internado Satisfactoriamente");
                }
            }
        }

        if ("EliminarCie10".equals(boton)) {
            String id_historiahh = request.getParameter("morbilidad");

            Historiales morbielimina = new Historiales();
            morbielimina.setId_historia(Integer.parseInt(id_historiahh));
            morbielimina.setCod_esta(cliente.getCod_esta());
            this.mi.setEliminarMorbilidad(morbielimina);
            //Historiales datomorbi= new Historiales();  
            datomorbi.setId_reservacion(Integer.parseInt(id_reservacion));
            datomorbi.setCod_esta(cliente.getCod_esta());
            List listarmorbi = this.mi.getListaMorbi(datomorbi);
            modelo.put("morbi", listarmorbi);

            if (listarmorbi.isEmpty() == true) {
                modelo.put("terminar", "no");
            }
        }

        if ("".equals(id_piso) || id_piso == null) {
            id_piso = "0";
            if (datoestab.getCod_esta() == 200010) {
                id_piso = "1";
            }
        }
        if ("".equals(id_sala) || id_sala == null) {
            id_sala = "0";
        }
        if ("".equals(id_cama) || id_cama == null) {
            id_cama = "0";
        }

        Salas dsala = new Salas();
        dsala.setId_piso(Integer.parseInt(id_piso));
        dsala.setCod_esta(cliente.getCod_esta());
        List listarPisos = this.mi.getListarPisos(dsala);
        modelo.put("listarPisos", listarPisos);

        List listarSalas = this.mi.getListarSalasLibres(dsala);
        modelo.put("listarSalas", listarSalas);
        //List listarSalas = this.mi.getListarSalas();                            
        //modelo.put("listarSalas", listarSalas);
        if (request.getParameter("id_sala") != null) {
            Camas lcama = new Camas();
            lcama.setId_sala(Integer.parseInt(id_sala));
            List listarCama = this.mi.getListarCamas(lcama);
            //List listarCama = this.mi.getListarCamas(Integer.parseInt(id_sala));
            modelo.put("listarCama", listarCama);
        }
        modelo.put("nombres", nombres);
        modelo.put("accionc", accionc);
        modelo.put("id_sala", id_sala);
        modelo.put("id_sala", id_sala);
        modelo.put("id_cama", id_cama);
        modelo.put("id_historial", id_historial);
        modelo.put("id_historia", id_historia);

        return new ModelAndView("administrarhistoriales/InternarPaciente", modelo);
    }
}
