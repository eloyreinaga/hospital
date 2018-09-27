package org.ayaic.web.administrarcamas;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Camas;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Consultorios;
import org.ayaic.domain.Cuadernos;
import org.ayaic.domain.Historiales;
import org.ayaic.domain.Localidades;
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.Personas;
import org.ayaic.domain.Salas;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ConfirmarCamaControlador {

    private final MiFacade mi;

    public ConfirmarCamaControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/ConfirmarCama.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String accion2 = request.getParameter("accion2");
        String accionc = request.getParameter("accionc");
        String accion1 = request.getParameter("accion1");
        String swv = request.getParameter("swv");
        String nombres = request.getParameter("nombres");
        String id_paciente = request.getParameter("id_paciente");
        String id_historial = request.getParameter("id_historial");
        String id_historia = request.getParameter("id_historia");
        String id_consultorio = request.getParameter("id_consultorio");
        String id_consul = request.getParameter("id_consultorio");
        String id_persona = request.getParameter("id_persona");
        String id_pers = request.getParameter("id_pers");
        String cama = request.getParameter("cama");
        String sala = request.getParameter("sala");
        String cama_unit = request.getParameter("cama_unit");
        String id_cama = request.getParameter("id_cama");
        String id_sala = request.getParameter("id_sala");
        String id_piso = request.getParameter("id_piso");
        String id_cama2 = request.getParameter("id_cama2");
        String id_sala2 = request.getParameter("id_sala2");
        String id_piso2 = request.getParameter("id_piso2");
        String camaactual = request.getParameter("camaactual");
        String tipo_inter = request.getParameter("tipo_inter");

        Localidades local = new Localidades();
        List Estab1 = this.mi.getListarEsta(local);
        Localidades datoestab = (Localidades) Estab1.get(0);
        modelo.put("cod_esta", Integer.toString(cliente.getCod_esta()));

        if (id_paciente == null || "".equals(id_paciente)) {
            id_paciente = "0";
        }
        if (id_historial == null || "".equals(id_historial)) {
            id_historial = "0";
        }
        if (id_persona == null || "".equals(id_persona)) {
            id_persona = "0";
        }

        Pacientes buscarPaciente = this.mi.getDatosPaciente(Integer.parseInt(id_paciente)); // saca un registro a ser modificado

        modelo.put("id_persona", id_persona);
        modelo.put("id_paciente", id_paciente);
        modelo.put("camaactual", camaactual);
        modelo.put("id_cama", id_cama);
        modelo.put("id_sala", id_sala);
        modelo.put("sala", sala);
        modelo.put("id_piso", id_piso);
        modelo.put("id_historial", id_historial);
        modelo.put("id_historia", id_historia);

        Cuadernos datoc5 = new Cuadernos();
        datoc5.setId_historial(Integer.parseInt(id_historial));
        datoc5.setCod_esta(cliente.getCod_esta());
        List C5 = this.mi.getPacienteCuaderno5(datoc5);

        if (C5.size() > 0) {
            Cuadernos cuaderno5 = (Cuadernos) C5.get(0);
            id_pers = Integer.toString(cuaderno5.getId_persona());
            id_consul = Integer.toString(cuaderno5.getId_consultorio());
            id_cama2 = Integer.toString(cuaderno5.getId_cama());
            id_sala2 = Integer.toString(cuaderno5.getId_sala());
            id_piso2 = Integer.toString(cuaderno5.getId_piso());
            Camas modcama = new Camas();  ////16-06-2016 se libera la cama para que muestre como libre al momento cambiar o modificar
            modcama.setId_cama(Integer.parseInt(id_cama2));
            modcama.setCod_esta(cliente.getCod_esta());
            modcama.setTipo("M");  ////setModificarCamaVacia
            modcama.setEstado(0);
            this.mi.setModificarCamaVacia(modcama);
        }

        Camas pai = new Camas();

        if ("Adicionar".equals(accion)) {
            pai.setId_sala(Integer.parseInt(id_sala));
            //    pai.setId_cama(Integer.parseInt(id_cama));
            pai.setCama(cama);
            pai.setCama_unit(Float.parseFloat(cama_unit));
            modelo.put("dato", pai);
            modelo.put("accion", accion);
            modelo.put("accion1", accion1);
        }

        if ("Modificar".equals(accion)) {

            pai.setId_cama(Integer.parseInt(id_cama));
            pai.setCama(cama);
            pai.setCama_unit(Float.parseFloat(cama_unit));

            modelo.put("dato", pai);
            modelo.put("sw", request.getParameter("sw"));
            modelo.put("accion", accion);
            modelo.put("accion1", accion1);
        }

        if ("Eliminar".equals(accion)) {
            Camas buscarCama = this.mi.getDatosCama(Integer.parseInt(id_cama)); // saca un registro a ser modificado
            modelo.put("dato", buscarCama);

            modelo.put("accion", accion);
            modelo.put("sw1", request.getParameter("sw1"));
            modelo.put("id_cama", id_cama);
        }

        if (!"0".equals(id_consultorio) && !"Guardar".equals(accion1)) {
            if (id_consultorio != id_consul) {
                accion1 = "";
                accionc = "cama";
                id_cama = "0";
            }
        }
        if ("".equals(id_cama)) {
            id_cama = "0";
        }
        if (!"0".equals(id_cama) && accion1 != null) {
            Camas lcama = new Camas();
            lcama.setId_sala(Integer.parseInt(id_sala));
            lcama.setId_cama(Integer.parseInt(id_cama));
            lcama.setTipo("U");   ///getListarCamaUnit 
            List listarCama = this.mi.getListarCamaUnit(lcama);  ///getListarCamas
            Camas licama = (Camas) listarCama.get(0);

        }

        if ("cama".equals(accionc)) { ///
            String cod_esta = request.getParameter("cod_esta");

            Historiales datohi = new Historiales();
            datohi.setId_historial(Integer.parseInt(id_historial));
            datohi.setCod_esta(cliente.getCod_esta());
            Historiales datoHis = this.mi.getDatosHistorial(datohi);
            id_cama = id_cama2;
            if ("".equals(id_piso)) {
                id_piso = "0";
            }
            if ("".equals(id_sala)) {
                id_sala = "0";
            }
            if ("".equals(id_cama)) {
                id_cama = id_cama2;
            }

            Salas dsala = new Salas();
            dsala.setId_piso(Integer.parseInt(id_piso));
            dsala.setCod_esta(cliente.getCod_esta());
            List listarPisos = this.mi.getListarPisos(dsala);
            modelo.put("listarPisos", listarPisos);
            List listarSalas = this.mi.getListarSalasLibres(dsala);
            modelo.put("listarSalas", listarSalas);

            Consultorios aa = new Consultorios();
            aa.setCod_esta(cliente.getCod_esta());
            if (cod_esta != null) {
                aa.setCod_esta(Integer.parseInt(cod_esta));
            }
            List listarCargos = this.mi.getListarConsultorios(aa);
            modelo.put("listarCargos", listarCargos);

            Personas persona = new Personas();
            if (!"0".equals(id_consultorio) && id_consultorio != null) {
                persona.setId_consultorio(Integer.parseInt(id_consultorio));
            } else {
                persona.setId_consultorio(datoHis.getId_consultorio());
            }

            persona.setCod_esta(cliente.getCod_esta());
            List buscarEmpleado = this.mi.getDatosPersonaConsul(persona);
            modelo.put("listaPersonas", buscarEmpleado);
            modelo.put("interfar", "interfar");

            if ("0".equals(id_piso) || id_piso != null) {
                dsala.setId_piso(Integer.parseInt(id_piso));
                List listarPisos2 = this.mi.getListarPisos(dsala);
                modelo.put("listarPisos", listarPisos2);
                List listarSalas2 = this.mi.getListarSalasLibres(dsala);
                modelo.put("listarSalas", listarSalas2);
                modelo.put("id_piso", id_piso);
            }

            if (id_sala != null && !"0".equals(id_sala)) {
                dsala.setId_sala(Integer.parseInt(id_sala));
                List listarSalas2 = this.mi.getListarSalasLibres(dsala);
                modelo.put("listarSalas", listarSalas2);
                Camas lcama = new Camas();
                lcama.setId_sala(Integer.parseInt(id_sala));
                lcama.setId_cama(Integer.parseInt(id_cama));
                lcama.setTipo("Q");   ///getListarCamas
                if (Integer.parseInt(id_sala) != Integer.parseInt(id_sala2)) {
                    lcama.setTipo("Z");  ///getListarCama
                }
                if (!"0".equals(id_sala) && !"0".equals(id_piso) && "0".equals(id_cama)) {
                    lcama.setTipo("Z");   ///getListarCama
                }
                List listarCama = this.mi.getListarCamas(lcama);  ///getListarCamas
                modelo.put("listarCama", listarCama);
                modelo.put("id_sala", id_sala);
                modelo.put("id_cama", id_cama);
            }
            if ("0".equals(id_piso)) {   ////14-10-2015
                modelo.put("id_sala", "0");
                modelo.put("id_cama", "0");
            }
            modelo.put("nombres", nombres);
            modelo.put("accionc", accionc);
            modelo.put("id_historial", id_historial);
            modelo.put("id_historia", id_historia);
            modelo.put("id_consultorio", id_consultorio);
            modelo.put("id_persona", id_persona);
            modelo.put("accion2", "1");
            //if( cliente.getId_almacen()==1 ){
            //       accionc="0";  
            //}
            return new ModelAndView("administrarcamas/AsignarCama", modelo);
        }

        if ("Guardarrrr".equals(accion1) && !"0".equals(id_cama)) {  //// se elimina casi para poder gurdar en otro controlador no daba en el mismo
            Historiales reserva = new Historiales();
            reserva.setId_estado("B");
            reserva.setId_cama(Integer.parseInt(camaactual));
            reserva.setId_carpeta(Integer.parseInt(id_cama));
            reserva.setId_sala(Integer.parseInt(id_sala));
            reserva.setId_piso(Integer.parseInt(id_piso));
            reserva.setId_persona(Integer.parseInt(id_persona));
            reserva.setId_consultorio(Integer.parseInt(id_consultorio));
            reserva.setId_historia(Integer.parseInt(id_historia));
            reserva.setId_historial(Integer.parseInt(id_historial));
            reserva.setId_tipo(Integer.parseInt(tipo_inter));
            reserva.setCod_esta(cliente.getCod_esta());
            //if(!"0".equals(id_sala) && "0".equals(id_piso)  && "0".equals(id_cama)){
            //   return new ModelAndView("Aviso","mensaje","Debe Elegir una SALA");   
            // }
            this.mi.setModificarInterDat(reserva);

            Historiales datox = new Historiales();
            datox.setId_persona(cliente.getId_persona());
            datox.setAccion("R");
            datox.setCadena(buscarPaciente.getNombres());
            datox.setCadena(datox.getCadena().replaceAll(" ", ":*&"));
            datox.setCadena(datox.getCadena().replaceAll("Ñ", "N"));
            datox.setCadena(datox.getCadena().replaceAll("ñ", "n"));
            datox.setCadena(datox.getCadena().toLowerCase());
            datox.setAccion("M");  ////getInternadosSalaCajaNombre
            datox.setSuma1(2);   ///para los internados
            datox.setSuma2(2);   ///para los internados
            datox.setCod_esta(cliente.getCod_esta());
            List listarPacInter = this.mi.getInternadosSalaCajaNombre(datox);
            modelo.put("listarPacInter", listarPacInter);

            Salas dsala = new Salas();
            dsala.setId_piso(Integer.parseInt(id_piso));
            dsala.setCod_esta(cliente.getCod_esta());
            List listarPisos = this.mi.getListarPisos(dsala);
            modelo.put("listarPisos", listarPisos);
            modelo.put("id_persona", Integer.toString(cliente.getId_persona()));

            return new ModelAndView("administrarhistoriales/ListarInternados", modelo);
        }

        return new ModelAndView("administrarcamas/ConfirmarCama", modelo);

    }
}
