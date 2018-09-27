package org.ayaic.web.administrarcuadernos;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Consultorios;
import org.ayaic.domain.Costos;
import org.ayaic.domain.Cuadernos;
import org.ayaic.domain.Historiales;
import org.ayaic.domain.Laboratorios;
import org.ayaic.domain.Localidades;
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.Personas;
import org.ayaic.domain.Sexos;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LaboratorioControlador {

    private final MiFacade mi;

    public LaboratorioControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/Laboratorio.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String accionl = request.getParameter("accionl");
        String accionc = request.getParameter("accionc");
        String accion2 = request.getParameter("accion2");
        String id_labgrupo = request.getParameter("labgrupos");
        String labgrupo = request.getParameter("labgrupo");
        String id_paciente = request.getParameter("id_paciente");
        String id_reservacion = request.getParameter("id_reservacion");
        String id_consultorio = request.getParameter("id_consultorio");
        String id_laboratorio = request.getParameter("id_laboratorio");
        String id_laboratoriog = request.getParameter("id_laboratoriog");
        String id_historial = request.getParameter("id_historial");
        String id_historia = request.getParameter("id_historia");
        String id_persona = request.getParameter("id_persona");
        String id_cama = request.getParameter("id_cama");
        String id_pedido = request.getParameter("id_pedido");
        String expedido = request.getParameter("expedido");
        String labox = request.getParameter("labox");
        String laboxn = request.getParameter("laboxn");
        String marcaa = request.getParameter("marcaa");
        String nombrelab = request.getParameter("nombrelab");
        String nombrepac = request.getParameter("nombrepac");
        String tipo_medico = request.getParameter("tipo_medico");
        String sw = request.getParameter("sw");
        String swinter = request.getParameter("swinter");
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date fechaact = new Date();
        Date fecha = new Date();
        String tipo = "L";
        int inter = 0, sw2 = 0;

        Localidades local = new Localidades();
        List Estab2 = this.mi.getListarEsta(local);
        local.setCod_esta(cliente.getCod_esta());
        local.setArea("U");  ///getListarEstaUsua el estab del usuario
        List Estab1 = this.mi.getListarEstaUsua(local);
        Localidades datoest = (Localidades) Estab2.get(0);

        Localidades datoestab = (Localidades) Estab1.get(0);
        modelo.put("datoestab", datoestab);
        modelo.put("id_paciente", id_paciente);
        modelo.put("id_reservacion", id_reservacion);
        modelo.put("id_laboratorio", id_laboratorio);
        modelo.put("id_consultorio", id_consultorio);
        modelo.put("id_persona", id_persona);
        modelo.put("expedido", expedido);
        modelo.put("tipo_medico", tipo_medico);
        modelo.put("sw", sw);
        modelo.put("swinter", swinter);
        modelo.put("id_pedido", id_pedido);

        if ("".equals(id_laboratorio) || id_laboratorio == null) {
            id_laboratorio = "19";
        }
        if (!"0".equals(id_labgrupo) && !"".equals(id_labgrupo) && id_labgrupo != null && !"0".equals(accionl) && !"".equals(accionl) && accionl != null) {
            id_labgrupo = "0";
            accionl = "0";
        }
        if (!"0".equals(id_labgrupo) && !"".equals(id_labgrupo) && id_labgrupo != null) {
            accionl = "0";
        }

        if (!"0".equals(accionl) && !"".equals(accionl) && accionl != null) {
            id_labgrupo = "0";
        }

        Laboratorios lab = new Laboratorios();
        lab.setId_laboratorio(Integer.parseInt(id_laboratorio));
        lab.setId_estado("X");   /////getDatosLaboratorio
        Laboratorios lislab = this.mi.getDatosLaboratorio(lab);
        lab.setCod_esta(cliente.getCod_esta());
        lab.setId_costo(cliente.getId_consultorio());
        lab.setId_estado("G");  /////getListarLabGrupo
        List listarLabGr = this.mi.getListarLabGrupo(lab);
        modelo.put("listarGrupoLab", listarLabGr);

        Personas persona = this.mi.getBuscarPersona(cliente.getId_persona()); // saca un registro a ser modificado
        Pacientes buscarPaciente = this.mi.getDatosPaciente(Integer.parseInt(id_paciente)); // saca un registro a ser modificado
        modelo.put("urgencias", Integer.toString(cliente.getId_almacen()));
        modelo.put("datos", buscarPaciente);

        if ("EliminarGrupoLab".equals(accionc)) {////14-07-2017
            Laboratorios elimina = new Laboratorios();
            elimina.setCod_esta(cliente.getCod_esta());
            elimina.setId_consultorio(cliente.getId_consultorio());
            elimina.setId_laboratoriog(Integer.parseInt(id_laboratoriog));
            elimina.setId_estado("G");  ////setEliminarLaboratorioGrupo
            try {
                this.mi.setEliminarLaboratorioGrupo(elimina);
                accionc = "Congifurar";
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "El registro a eliminar tiene dependencias");
            }
        }

        if ("EliminarGrupoLabDet".equals(accionc)) {////14-07-2017
            String id_detallelab = request.getParameter("id_laboratoriodet");
            String id_costo = request.getParameter("id_costo");

            Laboratorios elimina = new Laboratorios();
            elimina.setCod_esta(cliente.getCod_esta());
            elimina.setId_costo(Integer.parseInt(id_costo));
            elimina.setId_laboratoriog(Integer.parseInt(id_detallelab));
            elimina.setId_estado("D");  ////setEliminarLaboratorioGrupo
            try {
                this.mi.setEliminarLaboratorioGrupoDet(elimina);
                accionc = "ModificarGrupoLab";
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "El registro a eliminar detallelabos tiene dependencias");
            }
        }

        if ("Aumentar".equals(accionc)) {////14-07-2017
            String id_costo = request.getParameter("id_costo");
            String costo = request.getParameter("costo");

            Laboratorios datolaboratorio = new Laboratorios();
            datolaboratorio.setUlt_usuario(cliente.getId_persona());
            datolaboratorio.setId_consultorio(cliente.getId_consultorio());
            datolaboratorio.setId_laboratoriog(Integer.parseInt(id_laboratoriog));
            datolaboratorio.setId_costo(Integer.parseInt(id_costo));
            datolaboratorio.setCod_esta(cliente.getCod_esta());
            datolaboratorio.setCadena(costo);
            datolaboratorio.setId_estado("D");  ///setCrearLaboratorioGrupoDet
            try {
                this.mi.setCrearLaboratorioGrupoDet(datolaboratorio);
                accionc = "ModificarGrupoLab";
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "La actualizacion detalle laboratorio no se cumplio, verifique los datos");
            }
        }

        if ("ModificarGrupoLab".equals(accionc)) {////25-06-2017
            String laboratoriog = request.getParameter("laboratoriog");
            String id_lab = request.getParameter("id_lab");

            Laboratorios pai = new Laboratorios();
            pai.setCod_esta(cliente.getCod_esta());
            pai.setId_costo(cliente.getId_consultorio());
            pai.setId_estado("G");  /////getListarLabGrupo
            List listarLabG = this.mi.getListarLabGrupo(pai);
            modelo.put("listarGrupoLab", listarLabG);
            pai.setId_laboratoriog(Integer.parseInt(id_laboratoriog));
            pai.setId_estado("D");  /////getListarLabGrupoDet
            List listarLabD = this.mi.getListarLabGrupoDet(pai);
            modelo.put("listarGrupoLabDet", listarLabD);

            Cuadernos datoll = new Cuadernos();
            datoll.setId_historial(Integer.parseInt(id_reservacion));
            datoll.setCod_esta(cliente.getCod_esta());
            List listalab = this.mi.getPacienteLaboratorio(datoll);
            modelo.put("listalab", listalab);
            pai.setId_estado("A");
            List listarLabo1 = this.mi.getListarLaboratorios(pai);
            modelo.put("listarLabo1", listarLabo1);
            modelo.put("id_laboratoriog", id_laboratoriog);
            modelo.put("laboratoriog", laboratoriog);
            modelo.put("verlabos", "SI");

            if ("".equals(id_lab) || id_lab == null) {
                id_lab = "1";
            }
            if (!"".equals(accionl) && accionl != null) {////15-07-2017
                pai.setLaboratorio(accionl);
                pai.setId_estado("N");   /////getDatosLaboratorioNombre busca por nombre el laboratorio
                Laboratorios labon = this.mi.getDatosLaboratorioNombre(pai);  ////getDatosLaboratorioNombre 
                id_lab = Integer.toString(labon.getId_laboratorio());
                modelo.put("accionl", accionl);
            }
            //Laboratorios pai = new Laboratorios();  

            Costos datoq = new Costos();
            datoq.setId_rubro(6);
            datoq.setId_prestacion(6);
            datoq.setId_estado("%");
            datoq.setTipo(0);
            datoq.setId_nivel(0);
            datoq.setEmergencias(1);
            datoq.setId_laboratorio(Integer.parseInt(id_lab));
            datoq.setId_historial(Integer.parseInt(id_lab));
            datoq.setId_persona(99999);
            datoq.setNormales("%");
            datoq.setMuestra("N"); ////getListarLabosMedico
            datoq.setCod_esta(cliente.getCod_esta());
            List listarLaboratorios = this.mi.getListarLabosMedico(datoq);
            modelo.put("listarLab", listarLaboratorios);

            return new ModelAndView("administrarcuadernos/LaboratorioConf", modelo);
        }

        if ("CrearNuevo".equals(accionc)) {////14-07-2017
            String nombrelabg = request.getParameter("nombrelabg");

            Laboratorios datolaboratorio = new Laboratorios();
            datolaboratorio.setLaboratorio(nombrelabg.toUpperCase());
            datolaboratorio.setUlt_usuario(cliente.getId_persona());
            datolaboratorio.setId_consultorio(cliente.getId_consultorio());
            datolaboratorio.setCod_esta(cliente.getCod_esta());
            datolaboratorio.setCadena(cliente.getConsultorio());
            datolaboratorio.setId_estado("G");  ///setCrearLaboratorioGrupo
            try {
                this.mi.setCrearLaboratorioGrupo(datolaboratorio);
                accionc = "Congifurar";
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "La actualización crear grupo Laboratorio no se cumplio, verifique los datos");
            }
        }

        if ("Congifurar".equals(accionc) || "Regresar".equals(accionc) || "Nuevo Grupo".equals(accionc)) {////25-06-2017
            Laboratorios pai = new Laboratorios();
            pai.setCod_esta(cliente.getCod_esta());
            pai.setId_costo(cliente.getId_consultorio());
            pai.setId_estado("G");  /////getListarLabGrupo
            List listarLabG = this.mi.getListarLabGrupo(pai);
            modelo.put("listarGrupoLab", listarLabG);
            if ("Nuevo Grupo".equals(accionc)) { ////14-07-2017
                modelo.put("nuevoGrupo", "SI");
            }
            return new ModelAndView("administrarcuadernos/LaboratorioConf", modelo);
        }

        if ("".equals(id_persona) || id_persona == null) {
            id_persona = Integer.toString(cliente.getId_persona());
        }
        Personas buscarEmpleado = this.mi.getBuscarPersona(Integer.parseInt(id_persona));
        modelo.put("cod_esta", Integer.toString(cliente.getCod_esta()));

        Sexos buscarsexo = this.mi.getDatosSexo(buscarPaciente.getId_tipo_sexo()); // saca un registro a ser modificado
        modelo.put("buscarSexo", buscarsexo);

        Historiales datohi = new Historiales();
        datohi.setCod_esta(cliente.getCod_esta());
        datohi.setId_historial(Integer.parseInt(id_reservacion));
        Historiales datosHis = this.mi.getDatosHistorial(datohi);
        id_persona = Integer.toString(datosHis.getId_persona());
        modelo.put("tipo_medico", Integer.toString(cliente.getId_cargo()));

        Cuadernos datoc5 = new Cuadernos();  ////saca datos de internados id_historia  15-09-2015
        datoc5.setId_historial(Integer.parseInt(id_reservacion));
        datoc5.setCod_esta(cliente.getCod_esta());
        List C5 = this.mi.getPacienteCuaderno5(datoc5);

        if (!C5.isEmpty()) {
            Cuadernos Inter = (Cuadernos) C5.get(0);
            inter = Inter.getId_historia();
            id_persona = Integer.toString(Inter.getId_persona());
            id_cama = Integer.toString(Inter.getId_cama());
            marcaa = Inter.getCadena3();
            modelo.put("salasInter", Inter);

        }

        Personas buscarEmpleado2 = this.mi.getDatosPersona(datosHis.getId_persona());
        modelo.put("datosMed", buscarEmpleado2);

        if ("".equals(id_laboratorio) || id_laboratorio == null) {
            if (!"".equals(labox) && labox != null) {
                id_laboratorio = "13";
                accionl = "Rayos X";
            } else {
                id_laboratorio = "0";
            }
        }

        Costos datoq = new Costos();

        datoq.setId_rubro(6);
        datoq.setId_prestacion(6);
        datoq.setId_estado("S");
        datoq.setTipo(0);
        //datoq.setId_persona(5000);  
        //datoq.setMuestra("N");
        datoq.setId_nivel(0);
        datoq.setEmergencias(1);
        datoq.setId_laboratorio(0);
        datoq.setId_historial(5000);

        if (cliente.getId_almacen() == 1) {
            datoq.setId_nivel(1);
            datoq.setEmergencias(1);
        }
        datoq.setId_persona(cliente.getId_persona());
        datoq.setCod_esta(cliente.getCod_esta());
        datoq.setNormales("%");
        datoq.setMuestra("C");  ////getListarLabosMedicoConf
        datoq.setCod_esta(cliente.getCod_esta());///para todos 20-02-2017
        List listarLaboratorios = this.mi.getListarLabosMedicoConf(datoq);
        modelo.put("listarLab", listarLaboratorios);
        if (listarLaboratorios.isEmpty()) {
            datoq.setId_persona(5000);
            datoq.setNormales("%");
            datoq.setMuestra("N");  ////getListarLabosMedico
            List listarLaboratorios2 = this.mi.getListarLabosMedico(datoq);
            modelo.put("listarLab", listarLaboratorios2);
        }

        Laboratorios pai = new Laboratorios();
        pai.setLaboratorio(accionl);
        pai.setId_estado("N");   /////getDatosLaboratorioNombre busca por nombre el laboratorio
        Laboratorios labon = this.mi.getDatosLaboratorioNombre(pai);  ////getDatosLaboratorioNombre

        if ("RepetirLabos".equals(accion) || "Agregar Todo".equals(accion)) {////10-09-2015

            Cuadernos datol = new Cuadernos();
            datol.setTipoconsulta("P");  ////getLabPacPasados
            datol.setId_historial(Integer.parseInt(id_reservacion));
            datol.setId_paciente(Integer.parseInt(id_paciente));
            datol.setCod_esta(cliente.getCod_esta());
            List listalab = this.mi.getLabPacPasados(datol);
            modelo.put("listalab", listalab);
            //Cuadernos dato = new Cuadernos();

            datol.setResultado("");
            datol.setId_persona(Integer.parseInt(id_persona));
            datol.setId_dispensa(cliente.getId_persona());
            datol.setTipo(datosHis.getExpedido());
            datol.setCod_esta(cliente.getCod_esta());
            datol.setTipoconsulta("");
            datol.setNombres(cliente.getNombres());
            datol.setNombre(cliente.getEstablecimiento());
            datol.setSeleccion(0);
            datol.setId_historia(inter);
            datol.setFechap(fecha);
            datol.setFechae(fecha);
            datol.setTipo(datosHis.getExpedido());
            if ("E".equals(expedido)) {
                datol.setId_estado("G");
            } else {
                datol.setId_estado("A");
            }

            List listaP = this.mi.getPedidoLab(datol);// saca datos de pedidoslab

            if (listaP.isEmpty() == true) {
                this.mi.setCrearPedidoLab(datol);
                List listaPs = this.mi.getPedidoLab(datol);
                Cuadernos datoped = (Cuadernos) listaPs.get(0);
                id_pedido = Integer.toString(datoped.getId_pedido());
            } else {
                Cuadernos datoped = (Cuadernos) listaP.get(0);
                id_pedido = Integer.toString(datoped.getId_pedido());
            }

            if ("Agregar Todo".equals(accion)) {////25-07-2017
                Laboratorios lai = new Laboratorios();
                lai.setCod_esta(cliente.getCod_esta());
                lai.setId_costo(cliente.getId_consultorio());
                lai.setId_laboratoriog(Integer.parseInt(id_labgrupo));
                lai.setId_estado("D");  /////getListarLabGrupoDet
                List listarLabD = this.mi.getListarLabGrupoDet(lai);
                Cuadernos dato = new Cuadernos();
                for (int i = 0; i < listarLabD.size(); i++) {
                    Laboratorios datolg = (Laboratorios) listarLabD.get(i);
                    dato.setId_historial(Integer.parseInt(id_reservacion));
                    dato.setId_historia(dato.getId_historia());
                    dato.setId_paciente(Integer.parseInt(id_persona));
                    dato.setId_persona(Integer.parseInt(id_persona));
                    dato.setId_pedido(Integer.parseInt(id_pedido));
                    dato.setLaboratorio(datolg.getId_estado());
                    dato.setId_costo(datolg.getId_costo());
                    dato.setId_laboratorio(datolg.getId_laboratorio());
                    dato.setResultado("");
                    dato.setTipoconsulta(".");
                    dato.setId_estado("A");
                    dato.setId_por(cliente.getId_persona());
                    dato.setCod_esta(cliente.getCod_esta());
                    this.mi.setCrearLaboratorioC(dato);
                }
            } else {
                for (int i = 0; i < listalab.size(); i++) {
                    Cuadernos dato = (Cuadernos) listalab.get(i);
                    dato.setId_historial(Integer.parseInt(id_reservacion));
                    dato.setId_historia(dato.getId_historia());
                    dato.setId_paciente(Integer.parseInt(id_persona));
                    dato.setId_persona(Integer.parseInt(id_persona));
                    dato.setId_pedido(Integer.parseInt(id_pedido));
                    dato.setLaboratorio(dato.getLaboratorio());
                    dato.setId_costo(dato.getId_costo());
                    dato.setId_laboratorio(dato.getId_laboratorio());
                    dato.setResultado("");
                    dato.setTipoconsulta(".");
                    dato.setId_estado("A");
                    dato.setId_por(cliente.getId_persona());
                    dato.setCod_esta(cliente.getCod_esta());
                    this.mi.setCrearLaboratorioC(dato);
                }
            }
        }

        if ("Volver".equals(accion)) {

            Cuadernos datoll = new Cuadernos();
            datoll.setId_historial(Integer.parseInt(id_reservacion));
            datoll.setCod_esta(cliente.getCod_esta());
            List listalab = this.mi.getPacienteLaboratorio(datoll);
            modelo.put("listalab", listalab);
            pai.setId_estado("A");
            List listarLabo1 = this.mi.getListarLaboratorios(pai);
            modelo.put("listarLabo1", listarLabo1);
            return new ModelAndView("administrarcuadernos/Laboratorio", modelo);
        }

        if (!"0".equals(id_labgrupo) && id_labgrupo != null) {
            Laboratorios lai = new Laboratorios();
            lai.setCod_esta(cliente.getCod_esta());
            lai.setId_costo(cliente.getId_consultorio());
            lai.setId_laboratoriog(Integer.parseInt(id_labgrupo));
            lai.setId_estado("D");  /////getListarLabGrupoDet
            List listarLabD = this.mi.getListarLabGrupoDet(lai);
            Laboratorios datulabd = (Laboratorios) listarLabD.get(0);
            modelo.put("nombrelabd", datulabd.getCadena());
            modelo.put("listarGrupoLabDet", listarLabD);
            modelo.put("id_labgrupo", id_labgrupo);
        }

        if ((("".equals(accionl) || accionl == null))) {
            datoq.setId_laboratorio(0);
            datoq.setId_historial(5000);
            datoq.setId_estado("%");
        } else {
            if (labon != null) {
                datoq.setId_estado("%");
                datoq.setMuestra("N");
                datoq.setId_laboratorio(labon.getId_laboratorio());
                datoq.setId_historial(labon.getId_laboratorio());
                datoq.setId_persona(99999);
                datoq.setCod_esta(cliente.getCod_esta());///para todos 20-02-2017
                List listarLab = this.mi.getListarLabosMedico(datoq);  ////getDatosLaboratorioRayos
                modelo.put("listarLab", listarLab);
                id_laboratorio = Integer.toString(labon.getId_laboratorio());
                if (labon.getId_laboratorio() == 13 && datoest.getCod_esta() == 200010) {
                    if ("".equals(labox) || labox == null) {
                        labox = "CABEZA";
                    }
                    datoq.setTipo(0);
                    datoq.setId_persona(5000);
                    pai.setId_estado("R");
                    datoq.setDefecto(labox);
                    List laborx = this.mi.getDatosLaboratorioRayos(pai);  ////getDatosLaboratorioRayos
                    modelo.put("laborx", laborx);
                    modelo.put("Rayos", "SI");
                    datoq.setTipo(100);
                    datoq.setId_estado("%");
                    datoq.setMuestra("V");
                    datoq.setDefecto(labox);
                    datoq.setCod_esta(cliente.getCod_esta());///para todos 20-02-2017
                    List listarLab2 = this.mi.getListarCostosRx(datoq);  ////getListarCostosRx
                    modelo.put("listarLab", listarLab2);
                    modelo.put("labox", labox);
                    Cuadernos datoll = new Cuadernos();
                    datoll.setId_historial(Integer.parseInt(id_reservacion));
                    datoll.setCod_esta(cliente.getCod_esta());
                    List listalab = this.mi.getPacienteLaboratorio(datoll);
                    modelo.put("listalab", listalab);
                    if (!"Agregar".equals(accion2) && !"".equals(accion2) && accion2 != null) {
                        return new ModelAndView("administrarcuadernos/Laboratorio", modelo);
                    }
                }
            }
        }

        if ("Buscar".equals(accionl)) {
            nombrelab = nombrelab.replaceAll("\\s", "%");
            nombrelab = "%" + nombrelab + "%";
            datoq.setNormales(nombrelab.toUpperCase());
            datoq.setId_persona(cliente.getId_persona());
            datoq.setId_estado("%");
            datoq.setId_rubro(6);
            datoq.setId_prestacion(6);
            datoq.setMuestra("W");   ///getListarNombreCosto22
            datoq.setCod_esta(cliente.getCod_esta());///para todos 20-02-2017
            List listarLaboratorios3 = this.mi.getListarNombreCosto22(datoq);  ////getListarNombreCosto
            modelo.put("listarLab", listarLaboratorios3);
            if (listarLaboratorios.isEmpty()) {
                datoq.setId_persona(5000);
                datoq.setNormales("%");
                datoq.setMuestra("U");  ////getListarNombreCosto
                List listarLaboratorios2 = this.mi.getListarNombreCosto(datoq);
                modelo.put("listarLab", listarLaboratorios2);
            }
            //return new ModelAndView("administrarcuadernos/Laboratorio", modelo);
        }

        if ("".equals(id_reservacion)) {
            id_reservacion = id_historial;
        }

        if ("ImprimirLab".equals(accion) || "ImprimirLabT".equals(accion)) {

            Cuadernos datoll = new Cuadernos();
            datoll.setId_historial(Integer.parseInt(id_reservacion));
            datoll.setId_pedido(Integer.parseInt(id_reservacion));
            datoll.setCod_esta(cliente.getCod_esta());
            datoll.setTipo("M");
            if (cliente.getId_laboratorio() == 0) {
                datoll.setId_cargo(0);
                datoll.setId_laboratorio(999);
            } else {
                datoll.setId_cargo(cliente.getId_laboratorio());
                datoll.setId_laboratorio(cliente.getId_laboratorio());
            }
            List listalab = this.mi.getLabPendiente(datoll);
            modelo.put("datosLab", listalab);
            if (datoest.getCod_esta() == 200010) {
                datoll.setTipo("C");  //// tipo="C";   20/04/2015  getLabPendienteCNS   
                List listalab2 = this.mi.getLabPendienteCNS(datoll);
                modelo.put("datosLab", listalab2);
            }
            if ("ImprimirLabT".equals(accion)) {
                datoll.setTipo("U");   ////17/07/2015  getLabPendienteCNSUlt
                List listalab2 = this.mi.getLabPendienteCNSUlt(datoll);
                modelo.put("datosLab", listalab2);
            }
            modelo.put("id_pedido", id_pedido);/////
            Historiales datohis = new Historiales();
            datohis.setId_historial(Integer.parseInt(id_reservacion));
            datohis.setCod_esta(cliente.getCod_esta());
            Historiales datoHis = this.mi.getDatosHistorial(datohis);
            modelo.put("datosHis", datoHis);

            Pacientes paciente = new Pacientes();
            paciente.setTipo("U");
            paciente.setId_paciente(datoHis.getId_paciente());
            List buscarPacEmp = this.mi.getListarPacientesCnsUnico(paciente);
            modelo.put("datosPacEmp", buscarPacEmp);

            Pacientes buscarPaciente2 = this.mi.getDatosPaciente(datoHis.getId_paciente());
            modelo.put("datos", buscarPaciente2);
            modelo.put("dato", cliente);

            if (cliente.getId_especialidad() == 99 || cliente.getId_cargo() == 10) {
                Personas buscarEmpleado21 = this.mi.getDatosPersona(Integer.parseInt(id_persona));
                modelo.put("datosMed", buscarEmpleado21);
            } else {
                Personas buscarEmpleado21 = this.mi.getDatosPersona(cliente.getId_persona());
                modelo.put("datosMed", buscarEmpleado21);
            }
            if (!"".equals(id_cama) && !"0".equals(id_cama)) {
                modelo.put("marcaa", marcaa);
            }
            if (datoest.getCod_esta() == 200010) {
                if (cliente.getId_almacen() == 1) {
                    return new ModelAndView(new PedirLaboratorioCNSEmerg2PDF(), modelo);
                } else {
                    if ("ImprimirLab".equals(accion)) {
                        return new ModelAndView(new PedirLaboratorioCNSTodosPDF(), modelo);
                    }
                    return new ModelAndView(new PedirLaboratorioCNSPDF(), modelo);
                }
            } else {
                if ("T".equals(datoestab.getArea()) || "P".equals(datoestab.getArea())) {
                    return new ModelAndView(new PedirLaboratorioPrivPDF(), modelo); ////clinicas privadas
                } else {
                    return new ModelAndView(new PedirLaboratorioPDF(), modelo);
                }
            }
        }

        if ("Agregar".equals(accion2)) {
            String accioncurativa[] = request.getParameterValues("accioncurativa");
            String indicacion = request.getParameter("indicacion");
            String internado = request.getParameter("internado");
            String laboratorio = request.getParameter("laboratorio");

            Consultorios datosconsulta = this.mi.getDatosConsultorio(persona.getId_consultorio()); // saca un registro a ser modificado
            Cuadernos dato = new Cuadernos();
            dato.setResultado("");

            if (accioncurativa != null) {
                for (int i = 0; i < accioncurativa.length; i++) {

                    if ("143".equals(accioncurativa[i]) && indicacion.length() > 1) {
                        dato.setId_historial(Integer.parseInt(id_reservacion));
                        dato.setId_costo(Integer.parseInt(accioncurativa[i]));
                        Costos costo = new Costos();
                        costo.setId_costo(Integer.parseInt(accioncurativa[i]));
                        costo.setCod_esta(cliente.getCod_esta());
                        Costos buscarCosto = this.mi.getDatosCosto(costo);
                        dato.setLaboratorio("OTRO");
                        dato.setTipoconsulta(indicacion);
                        ///id_laboratorio=Integer.toString(buscarCosto.getId_laboratorio());
                        if (datosconsulta.getId_cargo() == 10) {
                            dato.setNombres("NOMBRE MEDICO");
                        } else {
                            dato.setNombres(buscarEmpleado2.getNombres());
                        }

                        dato.setNombre(cliente.getEstablecimiento());
                        dato.setId_historial(Integer.parseInt(id_reservacion));

                        if (cliente.getId_laboratorio() == 0) {
                            dato.setId_cargo(0);
                            dato.setId_laboratorio(999);
                        } else {
                            dato.setId_cargo(cliente.getId_laboratorio());
                            dato.setId_laboratorio(cliente.getId_laboratorio());
                        }
                        dato.setId_historia(inter);
                        dato.setFechap(fecha);
                        dato.setFechae(fecha);
                        if ("E".equals(expedido)) {
                            dato.setId_estado("G");
                        } else {
                            dato.setId_estado("A");
                        }
                        dato.setId_persona(Integer.parseInt(id_persona));
                        dato.setId_dispensa(cliente.getId_persona());
                        dato.setTipo(datosHis.getExpedido());
                        dato.setId_paciente(datosHis.getId_paciente());
                        dato.setCod_esta(cliente.getCod_esta());
                        dato.setSeleccion(cliente.getId_almacen());
                        //dato.setId_pedido(Integer.parseInt(id_pedido));/////hay fallas pedidos nuevos directo otro 143
                        List listaP = this.mi.getPedidoLab(dato);// saca datos de pedidoslab

                        ///////////////NO FUNCIONABA PEDIDOS nuevos en otros ahora siiii creo 21/01/2016 
                        if (listaP.isEmpty() == true) {
                            this.mi.setCrearPedidoLab(dato);
                        } else {
                            Cuadernos datoin = (Cuadernos) listaP.get(listaP.size() - 1);/////17/04/2015 para que se cree otro pedido en pedidolab
                            long fechalab = datoin.getFechap().getTime();
                            long fechanow = fechaact.getTime();
                            long diferencia = fechanow - fechalab;
                            String reportDate = df.format(datoin.getFechap());
                            double horas = Math.floor(diferencia / (1000 * 60 * 60));
                            double minut = Math.floor(diferencia / (1000 * 60));
                            if (datoest.getCod_esta() != 200010 && inter == 0) {
                                sw2 = 1;
                            }
                            //if(datoin.getId_historia()!=inter)
                            //    this.mi.setCrearPedidoLab(dato);  borrado 10-05-2015 estaba generando doble para internado
                            if (minut > 7 && sw2 == 0) {////31/12/2015 se cambia e hora a minitos si es mas de 10 min crea nuevo
                                sw2 = 1;
                                this.mi.setCrearPedidoLab(dato);
                            }
                        }

                        List listaP2 = this.mi.getPedidoLab(dato);
                        Cuadernos datoin2 = (Cuadernos) listaP2.get(listaP2.size() - 1);
                        dato.setId_pedido(datoin2.getId_pedido());
                        dato.setId_laboratorio(Integer.parseInt(id_laboratorio));
                        //if(dato.getId_costo()==143){
                        //    dato.setId_laboratorio(16);////solo cuando es otros debe guardarse con eso
                        //}
                        dato.setCod_esta(cliente.getCod_esta());
                        dato.setId_por(cliente.getId_persona());
                        this.mi.setCrearLaboratorioC(dato);
                        modelo.put("id_pedido", Integer.toString(datoin2.getId_pedido()));
                        //fin de if cuando es 143
                    } else {  //else de accion curativa 143

                        dato.setId_historial(Integer.parseInt(id_reservacion));
                        dato.setId_costo(Integer.parseInt(accioncurativa[i]));

                        Costos costo = new Costos();
                        costo.setId_costo(Integer.parseInt(accioncurativa[i]));
                        costo.setCod_esta(cliente.getCod_esta());
                        Costos buscarCosto = this.mi.getDatosCosto(costo);
                        dato.setLaboratorio(buscarCosto.getCosto());
                        if (cliente.getId_especialidad() == 99 || cliente.getId_cargo() == 10) {
                            Personas buscarEmpleado21 = this.mi.getDatosPersona(Integer.parseInt(id_persona));
                            nombrepac = buscarEmpleado21.getNombres();
                            modelo.put("datosMed", buscarEmpleado21);
                        } else {
                            Personas buscarEmpleado21 = this.mi.getDatosPersona(cliente.getId_persona());
                            nombrepac = buscarEmpleado21.getNombres();
                            modelo.put("datosMed", buscarEmpleado21);
                        }
                        if ("13".equals(id_laboratorio)) {
                            dato.setLaboratorio(buscarCosto.getNormales() + "-" + buscarCosto.getCosto());
                        }
                        dato.setTipoconsulta(indicacion);
                        dato.setNombres(nombrepac);

                        dato.setNombre(cliente.getEstablecimiento());
                        ///dato.setId_pedido(Integer.parseInt(id_pedido));  17/06/2015
                        if (cliente.getId_laboratorio() == 0) {
                            dato.setId_cargo(0);
                            dato.setId_laboratorio(999);
                        } else {
                            dato.setId_cargo(cliente.getId_laboratorio());
                            dato.setId_laboratorio(cliente.getId_laboratorio());
                        }
                        dato.setId_historia(inter);
                        dato.setFechap(fecha);
                        dato.setFechae(fecha);
                        dato.setId_estado("A");

                        dato.setId_persona(Integer.parseInt(id_persona));
                        if (cliente.getId_especialidad() != 99 && cliente.getId_cargo() != 10) {
                            dato.setId_persona(cliente.getId_persona());
                        }
                        dato.setId_dispensa(cliente.getId_persona());
                        dato.setId_paciente(Integer.parseInt(id_paciente));
                        dato.setTipo(datosHis.getExpedido());
                        dato.setSeleccion(cliente.getId_almacen());
                        dato.setCod_esta(cliente.getCod_esta());
                        dato.setEstado("A");
                        dato.setId_estado("A");
                        //if("E".equals(expedido)){  ////se borra X y Y ecos y rayos X 01-10-2015
                        //       dato.setEstado("G");  se comenta no esta funcioando para pacientes externos
                        //       dato.setId_estado("G");   
                        //}
                        List listaP = this.mi.getPedidoLab(dato);

                        if (datosconsulta.getId_cargo() == 10 && listaP.isEmpty()) {
                            dato.setNombres("NOMBRE MEDICO");
                        }

                        if (listaP.isEmpty() == true) {
                            this.mi.setCrearPedidoLab(dato);
                        } else {
                            Cuadernos datoin = (Cuadernos) listaP.get(listaP.size() - 1);/////17/04/2015 para que se cree otro pedido en pedidolab
                            long fechalab = datoin.getFechap().getTime();
                            long fechanow = fechaact.getTime();
                            long diferencia = fechanow - fechalab;
                            String reportDate = df.format(datoin.getFechap());
                            double horas = Math.floor(diferencia / (1000 * 60 * 60));
                            double minut = Math.floor(diferencia / (1000 * 60));
                            if (datoest.getCod_esta() != 200010 && inter == 0) {
                                sw2 = 1;
                            }
                            //if(datoin.getId_historia()!=inter)
                            //    this.mi.setCrearPedidoLab(dato);  borrado 10-05-2015 estaba generando doble para internado
                            if (minut > 10 && sw2 == 0) { ////31/12/2015 se cambia e hora a minitos si es mas de 10 min crea nuevo
                                sw2 = 1;
                                this.mi.setCrearPedidoLab(dato);
                            }
                        }

                        List listaP2 = this.mi.getPedidoLab(dato);
                        int g = listaP2.size();
                        Cuadernos datoin2 = (Cuadernos) listaP2.get(listaP2.size() - 1);
                        dato.setId_pedido(datoin2.getId_pedido());
                        dato.setCod_esta(cliente.getCod_esta());
                        dato.setId_laboratorio(buscarCosto.getId_laboratorio());
                        dato.setId_por(cliente.getId_persona());
                        this.mi.setCrearLaboratorioC(dato);
                        modelo.put("id_pedido", Integer.toString(datoin2.getId_pedido()));
                        //ahora para ver en detalle de precios

                    }//fin de if de accioncurativa 
                }//fin de for

            }
        }

        if ("Eliminar".equals(accion)) {
            String id_cuaderno = request.getParameter("id_cuaderno");
            Cuadernos datox = new Cuadernos();
            datox.setId_cuaderno(Integer.parseInt(id_cuaderno));
            datox.setId_historial(Integer.parseInt(id_reservacion));
            datox.setCod_esta(cliente.getCod_esta());
            this.mi.setEliminarLaboratorioC(datox);
        }

        if ("TodosGral".equals(accionl)) {
            Costos datoqq = new Costos();
            datoqq.setId_rubro(6);
            datoqq.setId_prestacion(6);
            datoqq.setId_estado("S");
            datoqq.setTipo(0);
            datoqq.setId_persona(5000);
            datoqq.setMuestra("N");
            datoqq.setId_nivel(0);
            datoqq.setEmergencias(1);
            datoqq.setId_laboratorio(0);
            datoqq.setId_historial(5000);
            datoqq.setCod_esta(cliente.getCod_esta());///para todos 20-02-2017
            List listarLabo = this.mi.getListarLabosMedico(datoqq);
            modelo.put("listarLab", listarLabo);
            if (cliente.getId_almacen() == 1) {  ///urgencias as id_almacen
                datoqq.setId_nivel(1);
                datoqq.setEmergencias(1);
                datoqq.setMuestra("N");
                List listarLabo2 = this.mi.getListarLabosMedico(datoqq);
                modelo.put("listarLab", listarLabo2);
            }

        }
        ///Laboratorios pai = new Laboratorios();  
        pai.setId_estado("A");
        List listarLabo1 = this.mi.getListarLaboratorios(pai);   ////lista tipos laboratorios
        modelo.put("listarLabo1", listarLabo1);

        modelo.put("accionl", accionl);
        if ("Terminar".equals(accion)) {
            String[] dias = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
            String[] meses = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
            String[] horas = {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"};
            String[] minutos = {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"};
            Date fecha1 = new Date();
            int anioq = fecha1.getYear() + 1900;
            String diaq1 = request.getParameter("diaq1");
            String mesq1 = request.getParameter("mesq1");
            String[] anios = {Integer.toString(anioq), Integer.toString(anioq - 1), Integer.toString(anioq - 2), Integer.toString(anioq - 3), Integer.toString(anioq - 4), Integer.toString(anioq - 5), Integer.toString(anioq - 6), Integer.toString(anioq - 7), Integer.toString(anioq - 8), Integer.toString(anioq - 9), Integer.toString(anioq - 10)};
            modelo.put("anios", anios);
            modelo.put("meses", meses);
            modelo.put("dias", dias);
            modelo.put("horas", horas);
            modelo.put("minutos", minutos);
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
            modelo.put("anio2", Integer.toString(fecha1.getYear() + 1900));
            modelo.put("mes", mesq1);
            modelo.put("dia", diaq1);
            modelo.put("anio2", Integer.toString(fecha1.getYear() + 1900));
            modelo.put("mes2", mesq1);
            modelo.put("dia2", diaq1);
            modelo.put("hora", "00");
            modelo.put("minuto", "00");
            modelo.put("hora2", "23");
            modelo.put("minuto2", "59");
            modelo.put("cod_esta", Integer.toString(cliente.getCod_esta()));
            Cuadernos datol = new Cuadernos();////10-05-2015
            datol.setTipoconsulta(tipo);////10-05-2015
            datol.setCod_esta(cliente.getCod_esta());////19-12-2016
            datol.setSeleccion(cliente.getId_almacen());   ////10-05-2015
            if (cliente.getId_laboratorio() > 0) {
                datol.setId_cargo(cliente.getId_laboratorio());   ////12-06-2015
                datol.setId_laboratorio(cliente.getId_laboratorio());   ////12-06-2015 
            } else {
                datol.setId_cargo(0);   ////12-06-2015
                datol.setId_laboratorio(9999);   ////12-06-2015 
            }
            if (cliente.getId_cargo() != 12 && cliente.getId_cargo() != 11) {
                List listalab = this.mi.getLabPacPendiente(datol);////10-05-2015
                modelo.put("listalab", listalab);
            }
            if (cliente.getId_cargo() == 11) {
                List listalab = this.mi.getLabPacPendienteEco(datol);
                modelo.put("listalab", listalab);
            }
            if (cliente.getId_cargo() == 12) {
                List listalab = this.mi.getLabPacPendienteRx(datol);
                modelo.put("listalab", listalab);
            }
            if ("lab".equals(sw)) {
                return new ModelAndView("administrarlaboratorio/LabRealizado", modelo);
            } // grabar los datos introducidos en la ventana       
            else if ("inter".equals(swinter)) {
                return new ModelAndView("administrarhistoriales/PlanAccionPacienteI", modelo);
            } else {
                return new ModelAndView("administrarhistoriales/PlanAccionPaciente", modelo);
            }
        }

        //Sacar los datos de laboratorios
        Cuadernos datoll = new Cuadernos();
        datoll.setId_historial(Integer.parseInt(id_reservacion));
        datoll.setCod_esta(cliente.getCod_esta());
        List listalab = this.mi.getPacienteLaboratorio(datoll);
        modelo.put("listalab", listalab);

        datoll.setTipoconsulta("P");  ////getLabPacPasados
        datoll.setId_paciente(Integer.parseInt(id_paciente));
        List listalabpas = this.mi.getLabPacPasados(datoll);
        modelo.put("nrecantlab", Integer.toString(listalabpas.size()));
        modelo.put("codesta", Integer.toString(cliente.getCod_esta()));

        return new ModelAndView("administrarcuadernos/Laboratorio", modelo);

    }
}
