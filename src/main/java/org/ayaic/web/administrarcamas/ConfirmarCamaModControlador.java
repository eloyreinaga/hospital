package org.ayaic.web.administrarcamas;

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
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.Salas;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ConfirmarCamaModControlador {

    private final MiFacade mi;

    public ConfirmarCamaModControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/ConfirmarCamaMod.do")
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

        Pacientes buscarPaciente = this.mi.getDatosPaciente(Integer.parseInt(id_paciente)); // saca un registro a ser modificado

        modelo.put("id_persona", id_persona);
        modelo.put("id_paciente", id_paciente);
        modelo.put("camaactual", camaactual);
        modelo.put("id_cama", id_cama);
        modelo.put("id_sala", id_sala);
        modelo.put("id_piso", id_piso);
        modelo.put("id_historial", id_historial);
        modelo.put("id_historia", id_historia);

        if ("".equals(id_persona)) {
            id_persona = "0";
        }

        if ("Guardar".equals(accion2) && cliente.getId_almacen() != 1 && ("0".equals(id_piso))) {
            return new ModelAndView("Aviso", "mensaje", "Debe Elegir todos las variables : Piso, Sala y Cama");
        }

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

        if ("Guardar".equals(accion2)) {
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
